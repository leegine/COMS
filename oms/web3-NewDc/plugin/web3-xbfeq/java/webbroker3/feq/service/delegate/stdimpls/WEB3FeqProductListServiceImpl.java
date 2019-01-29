head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄一覧サービスImpl(WEB3FeqProductListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 黄建 (中訊) 新規作成
                 : 2005/08/03 鄭海良(中訊) レビュー
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqClientRequestService;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3FeqProductListRequest;
import webbroker3.feq.message.WEB3FeqProductListResponse;
import webbroker3.feq.message.WEB3FeqProductUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3FeqProductListService;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
/**
 * (外国株式銘柄一覧サービスImpl)<BR>
 * 外国株式銘柄一覧サービス実装クラス
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqProductListServiceImpl extends WEB3FeqClientRequestService
    implements WEB3FeqProductListService
{

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductListServiceImpl.class);

    /**
     * @@roseuid 42CE39F90000
     */
    public WEB3FeqProductListServiceImpl()
    {

    }

    /**
     * 外国株式銘柄一覧処理を実施する。<BR>
     * <BR>
     * get銘柄一覧()をコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 429602060304
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3FeqProductListRequest)
        {
            //get銘柄情報一覧
            l_response =
                this.getProductInformationList(
                    (WEB3FeqProductListRequest) l_request);
        }
        else
        {
            log.debug(
                "リクエストデータが"
                    + " WEB3FeqProductListRequest 以外である, but is " +
                    l_request.getClass().getName());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get銘柄情報一覧)<BR>
     * 外国株式銘柄一覧処理を実施する。<BR>
     * <BR>
     * シーケンス図「（銘柄一覧）get銘柄情報一覧」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式銘柄一覧リクエスト
     * @@return webbroker3.feq.message.WEB3FeqProductListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4297136E0167
     */
    protected WEB3FeqProductListResponse getProductInformationList(WEB3FeqProductListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getProductInformationList(WEB3FeqProductListRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //1.1) validate( )
        l_request.validate();

        //1.2 validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get証券会社コード( )
        String l_strInstitutionCode = this.getInstitutionCode();

        //1.4 createソート条件(外国株式ソートキー[])
        //ソート条件文字列を編集する。
        //[createソートキー()に指定する引数]
        //ソートキー：　@リクエストデータ.ソートキー
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.5 get銘柄(String, String, String, String, boolean, String)
        //表示対象銘柄を取得する。
        //[get銘柄()に指定する引数]
        //証券会社コード：　@get証券会社コード()
        //市場コード：　@リクエストデータ.市場コード
        //銘柄コード：　@リクエストデータ.銘柄コード
        //銘柄名：　@リクエストデータ.銘柄名
        //is取引可能銘柄：　@true
        //ソート条件：　@createソート条件()
        WEB3FeqProductManager l_feqProductManager =
            (WEB3FeqProductManager) GtlUtils.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
        WEB3FeqProduct[] l_feqProducts =
            l_feqProductManager.getProduct(
                l_strInstitutionCode,
                l_request.marketCode,
                l_request.productCode,
                l_request.productName,
                true,
                l_strSortCond);

        if (l_feqProducts == null)
        {
            log.debug("外国株式銘柄が取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6 WEB3PageIndexInfo(l_objs（=get有効銘柄()の戻り値） : Object[],
        //   l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //ページング処理オブジェクトを生成する。
        //[コンストラクタの引数]
        //l_obj：　@get有効銘柄()
        //l_intRequestPageIndex：　@リクエストデータ.要求ページ番号
        //l_intRequestPageSize：　@リクエストデータ.ページ内表示行数
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_feqProducts,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));

        //1.7 getArrayReturned( )
        WEB3FeqProduct[] l_feqProductes =
            (WEB3FeqProduct[]) l_pageIndexInfo.getArrayReturned(WEB3FeqProduct.class);

        //外国株式銘柄明細( )
        WEB3FeqProductUnit l_feqProductUnit = null;
        List l_lisFeqProductUnits = new ArrayList();

        //1.8 (*) 表示対象ページに該当するオブジェクト要素分LOOP処理
        for (int i = 0; i < l_feqProductes.length; i++)
        {
            //1.8.1 外国株式銘柄明細( )
            //外国株式銘柄明細メッセージデータオブジェクトを生成する。
            l_feqProductUnit = new WEB3FeqProductUnit();

            //1.18.2 create外国株式銘柄明細(外国株式銘柄明細, 外国株式銘柄)
            //外国株式銘柄明細メッセージデータオブジェクトにプロパティをセットする。
            //[create外国株式銘柄明細()に指定する引数]
            //外国株式銘柄明細：　@生成したオブジェクト
            //銘柄：　@getArrayReturned()[index]
            this.createFeqProductDetails(l_feqProductUnit, l_feqProductes[i]);
            l_lisFeqProductUnits.add(l_feqProductUnit);
         }

         //1.9 get取扱可能外株市場(証券会社コード : String)
         //証券会社に該当する外株市場を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market[] l_markets =
            l_finObjectManager.getOpenFeqMarkets(l_strInstitutionCode);

        //1.10 getTotalPages( ) 総ページ数を取得する。
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();

        //1.11 getTotalRecords( ) 総レコード数を取得する。
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        //1.12 getPageIndex( ) 表示ページ番号を取得する。
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();

        //1.13 createResponse( )
        WEB3FeqProductListResponse l_response =
            (WEB3FeqProductListResponse)l_request.createResponse();

        //1.14 (*) プロパティセット
        //レスポンスデータプロパティに以下の通り値をセットする。
        //市場コード一覧：　@get取扱可能外株市場で取得した各市場.getMarketCode()

        //市場コード一覧
        String[] l_strMarketCodeList = null;
        if (l_markets != null)
        {
            l_strMarketCodeList = new String[l_markets.length];
            for (int i = 0; i < l_markets.length; i++)
            {
                l_strMarketCodeList[i] = l_markets[i].getMarketCode();
            }
        }
        l_response.marketList = l_strMarketCodeList;

        //銘柄情報一覧：　@生成した外国株式銘柄明細[]
        WEB3FeqProductUnit[] l_feqProductCodeNames = null;
        if (l_lisFeqProductUnits != null)
        {
            l_feqProductCodeNames =
                new WEB3FeqProductUnit[l_lisFeqProductUnits.size()];
            l_lisFeqProductUnits.toArray(l_feqProductCodeNames);
        }
        l_response.productCodeNames = l_feqProductCodeNames;

        //総ページ数：　@getTotalPages()
        l_response.totalPages = l_intTotalPages + "";

        //総レコード数：　@getTotalRecords()
        l_response.totalRecords = l_intTotalRecords + "";

        //表示ページ番号：　@getPageIndex()
        l_response.pageIndex = l_intPageIndex + "";

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create外国株式銘柄明細)<BR>
     * 指定銘柄の内容で、<BR>
     * 外国株式銘柄明細メッセージオブジェクトプロパティに値をセットする。<BR>
     * <BR>
     * 外国株式銘柄明細の各プロパティに以下の通り値をセットする。<BR>
     * <BR>
     * 　@銘柄コード：　@銘柄.getProductCode()<BR>
     * 　@銘柄名：　@銘柄.get表示銘柄名()<BR>
     * 　@買付可能：　@（取引銘柄.is買停止() == true）の場合、false。<BR>
     * 以外true。<BR>
     * 　@売付可能：　@（取引銘柄.is売停止() == true）の場合、false。<BR>
     * 以外true。<BR>
     * 　@現地銘柄コード：　@銘柄.現地銘柄コード<BR>
     * 　@買付単位：　@取引銘柄.買付単位<BR>
     * 　@最低買付単位：　@取引銘柄.最低買注文数量<BR>
     * 　@売付単位：　@取引銘柄.売付単位<BR>
     * 　@最低売付単位：　@取引銘柄.最低売注文数量<BR>
     * 　@上場登録日：　@取引銘柄.上場（登録）日<BR>
     * 　@上場廃止日：　@取引銘柄.上場（登録）廃止日<BR>
     * <BR>
     * ※　@取引銘柄の取得：　@銘柄.get取引銘柄()にて取得する。<BR>
     * @@param l_feqProductDetail - (外国株式銘柄明細)<BR>
     * 外国株式銘柄明細メッセージ
     * @@param l_product - (銘柄)<BR>
     * 外国株式銘柄オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 4297136E016B
     */
    protected void createFeqProductDetails(
        WEB3FeqProductUnit l_feqProductDetail, WEB3FeqProduct l_product)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createFeqProductDetails(WEB3FeqProductUnit, WEB3FeqProduct)";
        log.entering(STR_METHOD_NAME);

        //指定銘柄の内容で、外国株式銘柄明細メッセージオブジェクトプロパティに値をセットする。
        //外国株式銘柄明細の各プロパティに以下の通り値をセットする。

        //銘柄コード：　@銘柄.getProductCode()
        l_feqProductDetail.productCode = l_product.getProductCode();

        //銘柄名：　@銘柄.get表示銘柄名()
        l_feqProductDetail.productName = l_product.getDisplayProductName();

        //買付可能：　@（取引銘柄.is買停止() == true）の場合、false。以外true。

        //※　@取引銘柄の取得：　@銘柄.get取引銘柄()にて取得する。
        //修正　@劉　@begin
//        WEB3FeqTradedProduct l_feqTradedProduct =
//            l_product.getFeqTradedProduct();
       FeqTradedProductRow l_feq_tp_row=null;
       if(l_product.l_feqTPRow==null){
           try
           {
               l_feq_tp_row = FeqTradedProductDao.
                   findRowByProductIdMarketId(l_product.getProductId(),
                                              l_product.getMarket().getMarketId());
           }
           catch (Exception l_ex)
           {

               log.error(l_ex.getMessage(), l_ex);
               throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.toString());

           }
       }
       else {
           l_feq_tp_row=l_product.l_feqTPRow;
       }

        if (l_feq_tp_row == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (WEB3FeqTradedProduct.isBuyStop(l_feq_tp_row))
        {
            l_feqProductDetail.buyPossFlag = false;
        }
        else
        {
            l_feqProductDetail.buyPossFlag = true;
        }

        //売付可能：　@（取引銘柄.is売停止() == true）の場合、false。以外true。
        if (WEB3FeqTradedProduct.isSellStop(l_feq_tp_row))
        {
            l_feqProductDetail.sellPossFlag = false;
        }
        else
        {
            l_feqProductDetail.sellPossFlag = true;
        }

        //現地銘柄コード：　@銘柄.現地銘柄コード
        l_feqProductDetail.localProductCode =
            l_product.getOffshoreProductCode();

        //買付単位：　@取引銘柄.買付単位
        l_feqProductDetail.buyUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getBuyLotSize());

        //最低買付単位：　@取引銘柄.最低買注文数量
        l_feqProductDetail.minBuyUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getBuyMinQty());

        //売付単位：　@取引銘柄.売付単位
        l_feqProductDetail.sellUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getSellLotSize());

        //最低売付単位：　@取引銘柄.最低売注文数量
        l_feqProductDetail.minSellUnit =
            WEB3StringTypeUtility.formatNumber(
                l_feq_tp_row.getSellMinQty());

        //上場登録日：　@取引銘柄.上場（登録）日
        l_feqProductDetail.listedDate = l_feq_tp_row.getListedDate();

        //上場廃止日：　@取引銘柄.上場（登録）廃止日
        l_feqProductDetail.unlistedDate = l_feq_tp_row.getUnlistedDate();

        //市場コード
        l_feqProductDetail.marketCode = l_product.getMarketCode();

        //end


        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createソート条件)<BR>
     * 指定の内容で、<BR>
     * 指定の内容で、外国株式銘柄テーブルに対するソート条件文字列（order by句）<BR>
     * を編集し返却する。<BR>
     * ※キーとして「銘柄名」が指定されている場合、ソート条件とするテーブルの項目は、<BR>
     * 「銘柄名（カナ）」とする。 <BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー
     * @@return String
     * @@roseuid 429722B7030D
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME =
            "createSortCond(WEB3ForeignSortKey[] l_sortKeys)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_strSortKeys = new StringBuffer();
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" product_code ASC");
                }
                else
                {
                    l_strSortKeys.append(" product_code DESC");
                }
            }
            else if (WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" offshore_product_code ASC");
                }
                else
                {
                    l_strSortKeys.append(" offshore_product_code DESC");
                }
            }
            else if (WEB3FeqSortKeyItemNameDef.PRODUCT_NAME.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" standard_name_kana ASC");
                }
                else
                {
                    l_strSortKeys.append(" standard_name_kana DESC");
                }
            }
            if (i < l_sortKeys.length - 1)
            {
                l_strSortKeys.append(" , ");
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortKeys.toString();
    }
}
@
