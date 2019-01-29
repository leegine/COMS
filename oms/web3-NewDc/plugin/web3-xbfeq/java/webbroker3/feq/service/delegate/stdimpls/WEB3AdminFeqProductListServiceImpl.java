head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式銘柄一覧サービスImpl(WEB3AdminFeqProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/24 郭英 (中訊) 新規作成
                 : 2005/08/01 韋念瓊(中訊) レビュー   
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqProductListRequest;
import webbroker3.feq.message.WEB3AdminFeqProductListResponse;
import webbroker3.feq.message.WEB3FeqProductUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductListService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式銘柄一覧サービスImpl)<BR>
 * 管理者外国株式銘柄一覧サービス実装クラス<BR>
 */
public class WEB3AdminFeqProductListServiceImpl implements WEB3AdminFeqProductListService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductListServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F203D8
     */
    public WEB3AdminFeqProductListServiceImpl() 
    {
     
    }
    
    /**
     * 管理者外国株式銘柄一覧サービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AA20C0057
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqProductListRequest)
        {
            l_response = 
                this.getProductInfoList((WEB3AdminFeqProductListRequest)l_request);
        }        
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get銘柄情報一覧)<BR>
     * 管理者外国株式銘柄一覧処理を実施する。<BR>
     * <BR>
     * シーケンス図「（(管)銘柄一覧）get銘柄情報一覧」 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式銘柄情報一覧リクエスト<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqProductListResponse
     * @@throws WEB3BaseException
     * @@roseuid 429725DA031C
     */
    protected WEB3AdminFeqProductListResponse getProductInfoList(WEB3AdminFeqProductListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductInfoList(WEB3AdminFeqProductListRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1:validate()
        l_request.validate();
        
        //1.2:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "管理者のログイン情報が存在しない。");
        }
    
        //1.3:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, false);
        
        //1.4:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5:createソート条件(外国株式ソートキー[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.6:get銘柄(String, String, String, String, boolean, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "FinAppが存在しない。");
        }
        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        if (l_tradingModule == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "TradingModuleが存在しない。");
        }
        
        WEB3FeqProductManager l_productMgr = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productMgr == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式プロダクトマネージが存在しない。");
        }
        
        WEB3FeqProduct[] l_products = l_productMgr.getProduct(
            l_strInstitutionCode,
            l_request.marketCode,
            l_request.productCode,
            l_request.productName,
            false,
            l_strSortCond);
            
        //1.7:WEB3PageIndexInfo(l_objs（=get有効銘柄()の戻り値） : Object[], 
        //    l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_products, 
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
        
        //1.8:getArrayReturned( )    
        WEB3FeqProduct[] l_feqProducts = (WEB3FeqProduct[])
            l_pageIndexInfo.getArrayReturned(WEB3FeqProduct.class);
        
        WEB3FeqProductUnit[] l_productUnits = null;
        
        //1.9:(*) 表示対象ページに該当するオブジェクト要素分LOOP処理
        if (l_feqProducts != null && l_feqProducts.length > 0)
        {
            int l_intCnt = l_feqProducts.length;
            
            l_productUnits = new WEB3FeqProductUnit[l_intCnt];
            
            for (int i = 0; i < l_intCnt; i++)
            {
                //1.9.1:外国株式銘柄明細( )
                l_productUnits[i] = new WEB3FeqProductUnit();
                
                //1.9.2:create外国株式銘柄明細(外国株式銘柄明細, 外国株式銘柄)
                this.createFeqProductDetail(l_productUnits[i], l_feqProducts[i]);
            }
        }
        
        //1.10:get外株市場(String)
        WEB3GentradeFinObjectManager l_finObjManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        if (l_finObjManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "拡張金融オブジェクトマネージャが存在しない。");
        }
        
        Market[] l_markets = l_finObjManager.getFeqMarkets(l_strInstitutionCode);
        
        //1.11:getTotalPages( )
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.12:getTotalRecords( )
        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.13:getPageIndex( )
        int l_intPageIndex = l_pageIndexInfo.getPageIndex();
        
        //1.14:createResponse( )
        WEB3AdminFeqProductListResponse l_response = 
            (WEB3AdminFeqProductListResponse)l_request.createResponse();
            
        //1.15:(*) プロパティセット
        //レスポンスデータプロパティに以下の通り値をセットする。
        //市場コード一覧：　@get市場()で取得した各市場.getMarketCode()
        String[] l_strMarketCodes = null;
        
        if (l_markets != null && l_markets.length > 0)
        {
            List l_lisMarketCodes = new ArrayList();
            int l_intCnt = l_markets.length;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                Market l_market = l_markets[i];
                
                if (l_market != null)
                {
                    l_lisMarketCodes.add(l_market.getMarketCode());
                }                    
            }
            
            if (!l_lisMarketCodes.isEmpty())
            {
                l_strMarketCodes = new String[l_lisMarketCodes.size()];
                l_lisMarketCodes.toArray(l_strMarketCodes);
            }                        
        }
        l_response.marketList = l_strMarketCodes;
        
        //銘柄情報一覧：　@生成した外国株式銘柄明細[]
        l_response.productCodeNames = l_productUnits;
        
        //総ページ数：　@getTotalPages()
        l_response.totalPages = Integer.toString(l_intTotalPages);
        
        //総レコード数：　@getTotalRecords()
        l_response.totalRecords = Integer.toString(l_intTotalRecords);
        
        //表示ページ番号：　@getPageIndex
        l_response.pageIndex = Integer.toString(l_intPageIndex);
               
        log.exiting(STR_METHOD_NAME);
        
        return l_response;

        
    }
    
    /**
     * (create外国株式銘柄明細)<BR>
     * 指定銘柄の内容で、外国株式銘柄明細メッセージオブジェクトプロパティに値<BR>
     * をセットする。<BR>
     * <BR>
     * 外国株式銘柄明細の各プロパティに以下の通り値をセットする。<BR>
     * <BR>
     * 　@銘柄コード：　@銘柄.getProductCode()<BR>
     * 　@銘柄名：　@銘柄.get表示銘柄名()<BR>
     * 　@買付可能：　@（取引銘柄.is買停止() == true）の場合、false。以外true。<BR>
     * 　@売付可能：　@（取引銘柄.is売停止() == true）の場合、false。以外true。<BR>
     * 　@現地銘柄コード：　@銘柄.現地銘柄コード<BR>
     * 　@買付単位：　@取引銘柄.買付単位<BR>
     * 　@最低買付単位：　@取引銘柄.最低買注文数量<BR>
     * 　@売付単位：　@取引銘柄.売付単位<BR>
     * 　@最低売付単位：　@取引銘柄.最低売注文数量<BR>
     *   市場コード：　@銘柄.get市場コード()<BR>
     * 　@上場登録日：　@取引銘柄.上場（登録）日<BR>
     * 　@上場廃止日：　@取引銘柄.上場（登録）廃止日<BR>
     * <BR>
     * ※　@取引銘柄の取得：　@銘柄.get取引銘柄()にて取得する。<BR>
     * @@param l_feqProductDetail - (外国株式銘柄明細)<BR>
     * 外国株式銘柄明細メッセージ<BR>
     * @@param l_product - (銘柄)<BR>
     * 外国株式銘柄オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429725DA033C
     */
    protected void createFeqProductDetail(WEB3FeqProductUnit l_feqProductDetail, WEB3FeqProduct l_product) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createFeqProductDetail(WEB3FeqProductUnit, WEB3FeqProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqProductDetail == null || l_product == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータが未指定(null)です。");
        }
        
        //外国株式銘柄明細の各プロパティに以下の通り値をセットする。
        //銘柄コード：　@銘柄.getProductCode()
        l_feqProductDetail.productCode = l_product.getProductCode();
        
        //銘柄名：　@銘柄.get表示銘柄名()
        l_feqProductDetail.productName = l_product.getDisplayProductName();
        
        //買付可能：　@（取引銘柄.is買停止() == true）の場合、false。以外true。
        WEB3FeqTradedProduct l_tradedProduct = l_product.getFeqTradedProduct();
        if (l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "取引銘柄が存在しない。");
        }
        
        if (l_tradedProduct.isBuyStop())
        {
            l_feqProductDetail.buyPossFlag = false;
        }
        else
        {
            l_feqProductDetail.buyPossFlag = true;
        }
        
        //売付可能：　@（取引銘柄.is売停止() == true）の場合、false。以外true。
        if (l_tradedProduct.isSellStop())
        {
            l_feqProductDetail.sellPossFlag = false;
        }
        else
        {
            l_feqProductDetail.sellPossFlag = true;
        }
        
        //現地銘柄コード：　@銘柄.現地銘柄コード
        l_feqProductDetail.localProductCode = l_product.getOffshoreProductCode();
        
        //買付単位：　@取引銘柄.買付単位
        l_feqProductDetail.buyUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getBuyOrderLotSize());
        
        //最低買付単位：　@取引銘柄.最低買注文数量
        l_feqProductDetail.minBuyUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getBuyOrderMinimumQuantity());
            
        //売付単位：　@取引銘柄.売付単位
        l_feqProductDetail.sellUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getSellOrderLotSize());               
        
        //最低売付単位：　@取引銘柄.最低売注文数量
        l_feqProductDetail.minSellUnit = 
            WEB3StringTypeUtility.formatNumber(l_tradedProduct.getSellOrderMinimumQuantity());
        
        //市場コード：　@銘柄.get市場コード()
        l_feqProductDetail.marketCode = l_product.getMarketCode();
             
        //上場登録日：　@取引銘柄.上場（登録）日
        l_feqProductDetail.listedDate = WEB3DateUtility.toDay(l_tradedProduct.getListedDate());
           
        //上場廃止日：　@取引銘柄.上場（登録）廃止日
        l_feqProductDetail.unlistedDate = WEB3DateUtility.toDay(l_tradedProduct.getUnlistedDate());
        
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createソート条件)<BR>
     * 指定の内容で、外国株式銘柄テーブルに対するソート条件文字列<BR>
     * （order by句）を編集し返却する。<BR>
     * ※キーとして「銘柄名」が指定されている場合、ソート条件とするテーブル<BR>
     * の項目は、「銘柄名（カナ）」とする。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 429725DA034B
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createSortCond(WEB3ForeignSortKey[]) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKeys == null || l_sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータが未指定(null)です。");
        }
        
        StringBuffer l_sbOrderBy = new StringBuffer();
        
        int l_intCnt = l_sortKeys.length;
        
        List l_listKeys = new ArrayList();
        for (int i = 0; i < l_intCnt; i++)
        {
            String l_strKeyItem = l_sortKeys[i].keyItem;
            String l_strAOrD = l_sortKeys[i].ascDesc;
            
            if (!l_listKeys.contains(l_strKeyItem))
            {
                l_listKeys.add(l_sortKeys[i].keyItem);
                
                //外国株式銘柄明細.銘柄コード
                if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_strKeyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_strAOrD))
                    {
                        l_sbOrderBy.append("product_code asc, ");
                    }
                    else
                    {
                        l_sbOrderBy.append("product_code desc, ");
                    }
                    
                }
                //外国株式銘柄明細.現地銘柄コード
                else if (WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(l_strKeyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_strAOrD))
                    {
                        l_sbOrderBy.append("offshore_product_code asc, ");
                    }
                    else
                    {
                        l_sbOrderBy.append("offshore_product_code desc, ");
                    }
                    
                }//キーとして「銘柄名」が指定されている場合、ソート条件とするテーブル
                //の項目は、「銘柄名（カナ）」とする。
                else if (WEB3FeqSortKeyItemNameDef.PRODUCT_NAME.equals(l_strKeyItem))
                {
                    if (WEB3AscDescDef.ASC.equals(l_strAOrD))
                    {
                        l_sbOrderBy.append("standard_name_kana asc, ");
                    }
                    else
                    {
                        l_sbOrderBy.append("standard_name_kana desc, ");
                    }                    
                }
            }            
        }
        
        String l_strOrderBy = null;
        
        if (l_sbOrderBy.length() > 0)
        {
            l_strOrderBy = l_sbOrderBy.toString();
            l_strOrderBy = l_strOrderBy.substring(0, l_strOrderBy.length() - 2) + " ";
        }
           
        log.exiting(STR_METHOD_NAME);
            
        return " " + l_strOrderBy;        
    }
}
@
