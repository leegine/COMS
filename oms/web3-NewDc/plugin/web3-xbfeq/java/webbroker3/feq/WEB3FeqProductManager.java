head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式プロダクトマネージャ(WEB3FeqProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
                   2005/07/27 王亞洲(中訊) レビュー
Revesion History : 2007/11/16 馮海涛(中訊) モデルNo.362 No.364 No.366 No.367 実装.006
Revesion History : 2008/01/14 柴双紅(中訊) モデルNo.368、No.386
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
*/
package webbroker3.feq;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.data.FeqLimitPriceRangeMstRow;
import webbroker3.feq.data.FeqTickValuesMstRow;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3LogUtility;
import java.util.Hashtable;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductDao;
import java.util.Date;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.FeqLastClosingPriceRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

/**
 * (外国株式プロダクトマネージャ) <BR>
 * 外国株式プロダクトマネージャ
 *
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3FeqProductManager extends FeqProductManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqProductManager.class);
    /**
     * @@roseuid 42CE39E8030D
     */
    public WEB3FeqProductManager()
    {

    }

    /**
     * (get表示用時価情報) <BR>
     * 入力画面に表示する時価情報を取得する。 <BR>
     *  <BR>
     * １）時価を取得する。 <BR>
     *  <BR>
     *    （＊＊＊ 詳細未定 ＊＊＊） <BR>
     *  <BR>
     * ２）取得した時価情報を以下の優先順位で評価し、 <BR>
     * 　@　@値が付いている（==0でない）単価を時価として、 <BR>
     * 　@　@戻り値オブジェクト.時価 にセットする。 <BR>
     *  <BR>
     *     １．現在値（１）の詳細が確定するまでは、0） <BR>
     *     ２．売気配値（１）の詳細が確定するまでは、0） <BR>
     *     ３．買気配値（１）の詳細が確定するまでは、0） <BR>
     *     ４．取引銘柄.基準値（終値）（引数の取引銘柄.getLastClosingPrice( )） <BR>
     *     （＊＊＊ １）の詳細確定後に変更あり ＊＊＊） <BR>
     *  <BR>
     * 　@　@また、時価とした単価に該当する時価区分 <BR>
     * 　@　@（現在値／売気配値／買気配値／前日終値）を、 <BR>
     * 　@　@戻り値オブジェクト.時価区分 にセットする。 <BR>
     *  <BR>
     * ３）戻り値オブジェクト.前日比に、0をセットする。 <BR>
     *     （＊＊＊ １）の詳細確定後に変更あり ＊＊＊） <BR>
     *  <BR>
     * ４）戻り値オブジェクト.時価発表時間に、２）で時価とした単価 <BR>
     * 　@　@に対応する時価発表時間をセットする。 <BR>
     *     （＊＊＊ １）の詳細確定後に変更あり ＊＊＊） <BR>
     *  <BR>
     * ５）戻り値オブジェクト.時価取得区分に、"時価"をセットする。 <BR>
     *  <BR>
     * ６）戻り値オブジェクト.市場コードに、引数の取引銘柄.市場ID <BR>
     * 　@　@に該当する市場.市場コードをセットする。 <BR>
     *  <BR>
     * ７）作成した戻り値オブジェクトを返す。 <BR>
     * @@param l_feqTradedProduct - (取引銘柄) <BR>
     * 外国株式取引銘柄オブジェクト
     * @@param l_subAccount - (補助口座) <BR>
     * 補助口座オブジェクト
     * @@return webbroker3.feq.WEB3FeqProductQuote
     * @@roseuid 428B2962033B
     */
    public WEB3FeqProductQuote getIndicationCurrentPriceUnit(WEB3FeqTradedProduct l_feqTradedProduct, WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "getIndicationCurrentPriceUnit";
        log.entering(STR_METHOD_NAME);
        //１）時価を取得する。
        //
        //   （＊＊＊ 詳細未定 ＊＊＊）
        //
        double l_dblCurrentPrice = 0;
        //２）取得した時価情報を以下の優先順位で評価し、
        //　@　@値が付いている（==0でない）単価を時価として、
        //　@　@戻り値オブジェクト.時価 にセットする。
        //
        //    １．現在値（１）の詳細が確定するまでは、0）
        //    ２．売気配値（１）の詳細が確定するまでは、0）
        //    ３．買気配値（１）の詳細が確定するまでは、0）
        //    ４．取引銘柄.基準値（終値）（引数の取引銘柄.getLastClosingPrice( )）
        //    （＊＊＊ １）の詳細確定後に変更あり ＊＊＊）
        //
        //　@　@また、時価とした単価に該当する時価区分
        //　@　@（現在値／売気配値／買気配値／前日終値）を、
        //　@　@戻り値オブジェクト.時価区分 にセットする。
//        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//        TradingModule l_tradingModule =
//            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
//        WEB3QuoteDataSupplierService l_quoteSupplier =
//            (WEB3QuoteDataSupplierService)l_tradingModule.getQuoteDataSupplierService();
//        // 時価情報(EqTypeQuoteData）を取得する
//        //throw NotFoundException
//        QuoteData l_eqtypeQuoteData =
//            l_quoteSupplier.getQuote(l_feqTradedProduct);
//        l_dblCurrentPrice = l_productQuote.getCurrentPrice();
//        if (l_dblCurrentPrice == 0)
//        {
//            //l_productQuote.get
//        }
        //l_productQuote.setCurrentPrice(l_feqTradedProduct.getLastClosingPrice());
        //
        //３）戻り値オブジェクト.前日比に、0をセットする。
        //    （＊＊＊ １）の詳細確定後に変更あり ＊＊＊）
        //
        //４）戻り値オブジェクト.時価発表時間に、２）で時価とした単価
        //　@　@に対応する時価発表時間をセットする。
        //    （＊＊＊ １）の詳細確定後に変更あり ＊＊＊）
        //
        //５）戻り値オブジェクト.時価取得区分に、"時価"をセットする。
        //
        //６）戻り値オブジェクト.市場コードに、引数の取引銘柄.市場ID
        //　@　@に該当する市場.市場コードをセットする。
        //
        //７）作成した戻り値オブジェクトを返す。
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get時価情報) <BR>
     * 指定内容に応じた時価情報を取得する。 <BR>
     *  <BR>
     *１）　@時価を取得する。<BR>
     *　@－１）　@外株終値テーブルを以下の条件で検索する。<BR>
     *　@　@　@　@　@　@銘柄ID　@　@　@==　@（引数）取引銘柄．銘柄ID<BR>
     *　@　@　@　@　@　@市場コード　@==　@（引数）取引銘柄．市場IDに該当する市場コード<BR>
     *<BR>
     *　@　@　@　@　@　@※該当するレコードが複数件存在する場合は、基準日が最大値のレコードを返す。<BR>
     *<BR>
     *　@－２）　@－１）でデータが取得できなかった場合<BR>
     *　@　@　@　@　@　@nullを返却する。<BR>
     *<BR>
     *２）　@プロパティをセットする <BR>
     *  <BR>
     * 　@　@戻り値オブジェクトのプロパティを以下の通りに設定し、返却する。 <BR>
     * 　@　@-----------------------------------------------------------------<BR>
     * 　@　@＜戻り値プロパティ設定＞ <BR>
     *  <BR>
     * 　@　@外国株式銘柄時価情報.時価 = １）で取得した時価 <BR>
     * 　@　@外国株式銘柄時価情報.前日比 = 0 <BR>
     * 　@　@外国株式銘柄時価情報.時価発表時間 = 外株終値テーブル．基準日<BR>
     * 　@　@外国株式銘柄時価情報.時価取得区分 = "前日終値" <BR>
     * 　@　@外国株式銘柄時価情報.時価区分 = "前日終値" <BR>
     * 　@　@外国株式銘柄時価情報.市場コード = （引数）取引銘柄.市場IDに該当する市場コード <BR>
     * 　@　@----------------------------------------------------------------- <BR>
     * @@param l_feqTradedProduct - (取引銘柄)
     *
     * @@param l_realType - リアルタイプ
     * （時価取得区分）
     *
     * @@return webbroker3.feq.WEB3FeqProductQuote
     * @@throws WEB3BaseException
     * @@roseuid 4292B1E4007C
     */
    public WEB3FeqProductQuote getCurrentPriceUnit(FeqTradedProduct l_feqTradedProduct, RealType l_realType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrentPriceUnit(FeqTradedProduct, RealType)";
        log.entering(STR_METHOD_NAME);

        double l_dblCurrentPrice = 0;
        Timestamp l_tsBaseDate = null;

        WEB3FeqTradedProduct l_web3FeqTradedProduct = (WEB3FeqTradedProduct)l_feqTradedProduct;
    	FeqTradedProductRow l_feqTradedProductRow =
    		(FeqTradedProductRow)(l_web3FeqTradedProduct.getDataSourceObject());

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "product_id = ? and feq_closing_price_mrkt_code_s = ? ";
            Object[] l_whereValues = new Object[2];

            l_whereValues[0] = new Long(l_feqTradedProductRow.getProductId());
            l_whereValues[1] = l_feqTradedProduct.getMarket().getMarketCode();

            String l_strSort = " base_date DESC ";

            List l_lisFeqProducts =
                l_processor.doFindAllQuery(
                    FeqLastClosingPriceRow.TYPE,
                    l_strWhere,
                    l_strSort,
                    null,
                    l_whereValues);
            if (l_lisFeqProducts.isEmpty())
            {
                //データが取得できなかった場合nullを返却する。
            	log.exiting(STR_METHOD_NAME);
            	return null;
            }
            else
            {
                FeqLastClosingPriceRow l_feqLastClosingPriceRow = (FeqLastClosingPriceRow)l_lisFeqProducts.get(0);
                l_dblCurrentPrice = l_feqLastClosingPriceRow.getFeqClosingPrice();
                l_tsBaseDate = l_feqLastClosingPriceRow.getBaseDate();
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());
        }

        WEB3FeqProductQuote l_feqProductQuote = new WEB3FeqProductQuote();
        //外国株式銘柄時価情報.時価 =  １）で取得した時価
        l_feqProductQuote.setCurrentPrice(l_dblCurrentPrice);
        //外国株式銘柄時価情報.前日比 = 0
        l_feqProductQuote.setComparedPreviousDay(0);
        //外国株式銘柄時価情報.時価発表時間 = 外株終値テーブル．基準日
        l_feqProductQuote.setCurrentPricePublicTime(l_tsBaseDate);
        //外国株式銘柄時価情報.時価取得区分 = "前日終値"
        l_feqProductQuote.setCurrentPriceGetDiv("前日終値");
        //外国株式銘柄時価情報.時価区分 = "前日終値"
        l_feqProductQuote.setCurrentPriceDiv("前日終値");
        //外国株式銘柄時価情報.市場コード = (引数）取引銘柄.市場IDに該当する市場コード
        l_feqProductQuote.setMarketCode(l_feqTradedProduct.getMarket().getMarketCode());

        log.exiting(STR_METHOD_NAME);
        return l_feqProductQuote;
    }

    /**
     * (get時価) <BR>
     * 時価を取得する。 <BR>
     * 取得順は以下の通り。 <BR>
     * ----------------------------------------------- <BR>
     * 場中：　@　@時価サーバ＞外国株式取引銘柄テーブル <BR>
     * 引け後：　@終値テーブル＞時価サーバ＞外国株式取引銘柄テーブル <BR>
     * ----------------------------------------------- <BR>
     *  <BR>
     * １）　@this.get時価情報( )をコールする。 <BR>
     *  <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@＜get時価情報( )：引数設定仕様＞ <BR>
     *  <BR>
     * 　@　@　@取引銘柄：　@引数の取引銘柄 <BR>
     * 　@　@　@RealType：　@"リアル" <BR>
     * 　@　@　@---------------------------------------------------------- <BR>
     *  <BR>
     * ２）　@戻り値オブジェクト.時価 を返却する。 <BR>
     * 　@　@　@　@※戻り値オブジェクト == null の場合は、0を返却する。<BR>
     * @@param l_feqTradedProduct - (取引銘柄)
     *
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4292B55B02CE
     */
    public double getCurrentPrice(FeqTradedProduct l_feqTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrentPrice(FeqTradedProduct l_feqTradedProduct)";
        log.entering(STR_METHOD_NAME);
        WEB3FeqProductQuote l_quote =  this.getCurrentPriceUnit(l_feqTradedProduct, RealType.REAL);
        log.exiting(STR_METHOD_NAME);

        if (l_quote == null)
        {
            //※戻り値オブジェクト == null の場合は、0を返却する。
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        log.exiting(STR_METHOD_NAME);
        return l_quote.getCurrentPrice();
    }

    /**
     * (get外国株式銘柄) <BR>
     * 外国株式銘柄オブジェクトを取得する。 <BR>
     *  <BR>
     * １）以下の条件で外国株式銘柄テーブルからデータを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    銘柄ID： 引数.銘柄ID <BR>
     *  <BR>
     * ２）取得したデータから外国株式銘柄オブジェクトを生成し、返却する。 <BR>
     * @@param l_lngProductId - (銘柄ID)
     *
     * @@return WEB3FeqProduct
     * @@throws WEB3BaseException
     * @@roseuid 4292D06600DA
     */
    public WEB3FeqProduct getFeqProduct(long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFeqProduct(long l_lngProductId) ";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqProduct l_feqProduct =
                (WEB3FeqProduct)this.getProduct(l_lngProductId);
            log.exiting(STR_METHOD_NAME);
            return l_feqProduct;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (get外国株式銘柄) <BR>
     * （getFeqProduct()のオーバーライド） <BR>
     * 外国株式銘柄オブジェクトを取得する。 <BR>
     *  <BR>
     * 以下の検索条件にて外株銘柄テーブルを検索する。 <BR>
     *  <BR>
     *  [条件] <BR>
     * 　@証券会社コード = パラメータ.証券会社.証券会社コード And <BR>
     * 　@（銘柄コード = パラメータ.銘柄コード Or <BR>
     * 　@ 現地銘柄コード = パラメータ.銘柄コード） <BR>
     *  <BR>
     * 検索結果が取得できた場合、検索結果を返却する。 <BR>
     * @@param l_institution - (証券会社) <BR>
     * 証券会社オブジェクト
     * @@param l_strProductCode - 銘柄コード <BR>
     *  <BR>
     * ※外国株式銘柄.銘柄コード or 現地銘柄コードの値を指定する。
     * @@return webbroker3.feq.WEB3FeqProduct
     * @@roseuid 42ACE6730165
     */
    public FeqProduct getFeqProduct(Institution l_institution, String l_strProductCode) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getFeqProduct(Institution l_institution, String l_strProductCode) ";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ? and (product_code = ? or offshore_product_code = ?)";
            Object[] l_objWhereValues = new Object[3];
            l_objWhereValues[0] = l_institution.getInstitutionCode();
            l_objWhereValues[1] = l_strProductCode;
            l_objWhereValues[2] = l_strProductCode;
            List l_lisFeqProduct =
                l_processor.doFindAllQuery(FeqProductRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            if (l_lisFeqProduct == null || l_lisFeqProduct.isEmpty())
            {
                String msg = "No FeqProduct found with institutionCode, productCode : " +
                    l_institution.getInstitutionCode() + "," + l_strProductCode;
                throw new NotFoundException(msg);
            }
            if (l_lisFeqProduct.size() > 1)
            {
                log.debug("外国株式銘柄テーブルに重複する該当データが存在します。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            FeqProductRow l_feqProductRow = (FeqProductRow)l_lisFeqProduct.get(0);
            return (FeqProduct)this.toProduct(l_feqProductRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString());
        }
    }

    /**
     * (get銘柄) <BR>
     * 指定条件の銘柄オブジェクトを取得する。 <BR>
     *  <BR>
     * １）　@銘柄テーブル検索 <BR>
     * 　@以下の条件で外国株式銘柄テーブルを検索し、 <BR>
     * 　@該当行オブジェクトにて外国株式銘柄オブジェクトを生成する。 <BR>
     * 　@該当行がない場合は、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02142<BR>
     *  <BR>
     * 　@[条件] <BR>
     * 　@外国株式銘柄.証券会社コード = 証券会社コード And <BR>
     * 　@外国株式銘柄.市場コード = 市場コード And  <BR>
     *   ※市場コード != nullの場合のみ <BR>
     * 　@（外国株式銘柄.銘柄コード = 銘柄コード Or <BR>
     * 　@　@外国株式銘柄.銘柄コード = 現地銘柄コード） And  <BR>
     *   ※銘柄コード != nullの場合のみ <BR>
     * 　@（外国株式銘柄.銘柄名（カナ） like 銘柄名 Or
     *    外国株式銘柄.銘柄名（漢字） like 銘柄名）
     * ※銘柄名 != nullの場合のみ。"%"はPR層にて銘柄名に付加されてくるため、APでの付加は不要。
     *  <BR>
     * 　@[取得順] <BR>
     * 　@ソート条件 <BR>
     *  <BR>
     * ２）　@取引可能の判定 <BR>
     * 　@－（is取引可能銘柄 == true）の場合、１）で取得した要素のうち、 <BR>
     * 　@以下の条件に当てはまるものを配列にて返却する。 <BR>
     *  <BR>
     * 　@　@　@[条件] <BR>
     * 　@　@　@（外国株式銘柄.get取引銘柄().is上場期間内() == true） And <BR>
     * 　@　@　@（外国株式銘柄.get取引銘柄().is買停止() == false Or <BR>
     * 　@　@　@　@外国株式銘柄.get取引銘柄().is売停止() == false） <BR>
     *  <BR>モデル131仕様変更　@　@　@　@2005/8/29  芦露
     *  ※引数.市場コード==null の場合は、以下の条件にも当てはまるかをチェックする。
     *    外国株式銘柄.get市場().is取引停止 == false
     * 　@－（is取引可能銘柄 == false）の場合、 <BR>
     * 　@　@　@１）で取得したオブジェクトを配列にて返却する。 <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strMarketCode - (市場コード)
     *
     * ※　@null指定の場合は全市場
     * @@param l_strProductCode - 銘柄コード（銘柄コード／現地銘柄コード） <BR>
     *  <BR>
     * ※　@完全一致 <BR>
     * @@param l_strProductName - (銘柄名) <BR>
     *  <BR>
     * ※　@前方／後方一致
     * @@param l_blnIsTradedPossProduct - is取引可能銘柄 <BR>
     *  <BR>
     * true：　@取引可能な銘柄のみ検索 <BR>
     * false：　@取引可能／不可に関わらず検索 <BR>
     *
     *
     * @@param l_strSortCond - (ソート条件)
     * @@return webbroker3.feq.WEB3FeqProduct[]
     * @@throws WEB3BaseException
     * @@roseuid 4295E6C800C1
     */
    public WEB3FeqProduct[] getProduct(String l_strInstitutionCode,
        String l_strMarketCode, String l_strProductCode, String l_strProductName,
        boolean l_blnIsTradedPossProduct, String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProduct";
        log.entering(STR_METHOD_NAME);
        // １）　@銘柄テーブル検索
        // 　@以下の条件で外国株式銘柄テーブルを検索し、
        // 　@該当行オブジェクトにて外国株式銘柄オブジェクトを生成する。
        // 　@該当行がない場合は、例外をスローする。
        //
        // 　@[条件]
        // 　@外国株式銘柄.証券会社コード = 証券会社コード And
        // 　@外国株式銘柄.市場コード = 市場コード And
        //   ※市場コード != nullの場合のみ
        // 　@（外国株式銘柄.銘柄コード = 銘柄コード Or
        // 　@　@外国株式銘柄.銘柄コード = 現地銘柄コード） And
        //   ※銘柄コード != nullの場合のみ
        // 　@（外国株式銘柄.銘柄名（カナ） like 銘柄名 Or
        //    外国株式銘柄.銘柄名（漢字） like 銘柄名）
        // ※銘柄名 != nullの場合のみ。"%"はPR層にて銘柄名に付加されてくるため、APでの付加は不要。
        //
        // 　@[取得順]
        // 　@ソート条件
        List l_lisFeqProduct = null;
        try
        {
            List l_lisWhereValue = new ArrayList();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ?";
            l_lisWhereValue.add(l_strInstitutionCode);

            if (l_strMarketCode != null)
            {
                l_strWhere += " and market_code = ?";
                l_lisWhereValue.add(l_strMarketCode);
            }

            if (l_strProductCode != null)
            {
                l_strWhere += " and (product_code = ? or offshore_product_code = ?) ";
                l_lisWhereValue.add(l_strProductCode);
                l_lisWhereValue.add(l_strProductCode);

            }
            if (l_strProductName != null)
            {
                l_strWhere += " and (standard_name_kana like ? or standard_name_kanji like ?)";
                l_lisWhereValue.add(l_strProductName);
                l_lisWhereValue.add(l_strProductName);
            }
            Object[] l_objWhereValues = new Object[l_lisWhereValue.size()];
            l_lisWhereValue.toArray(l_objWhereValues);
            l_lisFeqProduct = l_processor.doFindAllQuery(FeqProductRow.TYPE,
                l_strWhere,
                l_strSortCond,
                null,
                l_objWhereValues);
            if (l_lisFeqProduct == null || l_lisFeqProduct.isEmpty())
            {
                log.error("外国株式銘柄が存在しない。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            // ２）　@取引可能の判定
            // 　@－（is取引可能銘柄 == true）の場合、１）で取得した要素のうち、
            // 　@以下の条件に当てはまるものを配列にて返却する。
            //
            // 　@　@　@[条件]
            // 　@　@　@（外国株式銘柄.get取引銘柄().is上場期間内() == true） And
            // 　@　@　@（外国株式銘柄.get取引銘柄().is買停止() == false Or
            // 　@　@　@　@外国株式銘柄.get取引銘柄().is売停止() == false）
            //
            // 　@－（is取引可能銘柄 == false）の場合、
            // 　@　@　@１）で取得したオブジェクトを配列にて返却する。
            List l_listFeqProductRow = new ArrayList();

            //修正劉追加　@begin
            Hashtable l_tblBizDate=new Hashtable(3);
            Hashtable l_tblmarket=new Hashtable(3);
            //end

            if ((l_blnIsTradedPossProduct))
            {
                Iterator l_iterator = l_lisFeqProduct.iterator();
                int i = 0;
                while (l_iterator.hasNext())
                {
                    FeqProductRow l_feqProductRow = (FeqProductRow)l_iterator.next();

                    //修正劉追加　@begin

                    WEB3FeqProduct l_feqProduct =
                        (WEB3FeqProduct)this.toProduct(l_feqProductRow);
                    String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
                    //取引時間コンテキストの取得
                    WEB3GentradeTradingClendarContext l_clendarContext =
                        (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                            TRADING_CAL_CONTEXT_PATH);
                    String l_strMarketKey=l_clendarContext.getInstitutionCode().trim()+l_feqProduct.getMarketCode();
                    WEB3GentradeMarket l_marker =(WEB3GentradeMarket)l_tblmarket.get(l_strMarketKey);
                    if(l_marker==null){
                        l_marker = (WEB3GentradeMarket) l_feqProduct.getMarket();
                        l_tblmarket.put(l_strMarketKey,l_marker);
                    }
                   WEB3GentradeTradingTimeManagement.resetMarketCode(l_marker.getMarketCode());
                    Date l_datBizDate=(Date)l_tblBizDate.get(l_marker.getMarketCode());
                    if(l_datBizDate==null){
                        l_datBizDate = WEB3GentradeTradingTimeManagement.
                            getOrderBizDate();
                       l_tblBizDate.put(l_marker.getMarketCode(),l_datBizDate);
                    }

                    FeqTradedProductRow l_feq_tp_row = FeqTradedProductDao.
                        findRowByProductIdMarketId(l_feqProduct.getProductId(),
                        l_marker.getMarketId());
                    l_feqProduct.l_feqTPRow=l_feq_tp_row;
                    //end

                    //モデル131仕様変更　@　@　@　@2005/8/29  芦露
                    // 引数.市場コード==null の場合は、以下の条件にも当てはまるかをチェックする。
                    // 外国株式銘柄.get市場().is取引停止 == false

                    //修正　@劉　@訂正　@begin
                    if (l_strMarketCode == null)
                    {

                        if (!WEB3FeqTradedProduct.isOpen(l_datBizDate,l_feq_tp_row) ||
                            (WEB3FeqTradedProduct.isBuyStop(l_feq_tp_row) && WEB3FeqTradedProduct.isSellStop(l_feq_tp_row)) ||
                            l_marker.isSuspension())
                        {
                        }
                        else
                        {
//                            WEB3FeqProduct l_product = new WEB3FeqProduct( (FeqProductRow)
//                                l_lisFeqProduct.get(i));
                            l_listFeqProductRow.add(l_feqProduct);
                        }
                    }
                    else
                    {
                        if (!WEB3FeqTradedProduct.isOpen(l_datBizDate,l_feq_tp_row) ||
                            (WEB3FeqTradedProduct.isBuyStop(l_feq_tp_row) && WEB3FeqTradedProduct.isSellStop(l_feq_tp_row)))
                        {
                        }
                        else
                        {
//                            WEB3FeqProduct l_product = new WEB3FeqProduct((FeqProductRow)l_lisFeqProduct.get(i));
//                            l_listFeqProductRow.add(l_product);
                            l_listFeqProductRow.add(l_feqProduct);

                        }
                    //end
               //end

                    }
                    i++;
                }
            }
            else
            {
                for (int i = 0; i< l_lisFeqProduct.size(); i++)
                {
                    WEB3FeqProduct l_product = new WEB3FeqProduct((FeqProductRow)l_lisFeqProduct.get(i));
                    l_listFeqProductRow.add(l_product);
                }
            }
            if (l_listFeqProductRow == null || l_listFeqProductRow.isEmpty())
            {
                return null;
            }

            WEB3FeqProduct[] l_feqProduct = new WEB3FeqProduct[l_listFeqProductRow.size()];
            l_listFeqProductRow.toArray(l_feqProduct);

            log.exiting(STR_METHOD_NAME);
            return l_feqProduct;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (get刻み値) <BR>
     * 呼値テーブルから、該当する刻み値を取得する。 <BR>
     *  <BR>
     * １）対象行取得 <BR>
     *  <BR>
     *    外株呼値テーブルを以下の条件で取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    市場コード = 引数.外国株式銘柄.市場コード <BR>
     *  <BR>
     * 　@条件に一致する行より、 <BR>
     * 　@（外株呼値テーブル.下限値　@< 引数.単価 <= 外株呼値テーブル.上限値） <BR>
     * 　@ に該当する行の行オブジェクトを取得する。 <BR>
     *  <BR>
     * ２）刻み値返却 <BR>
     *  <BR>
     *    取得した行.刻み値を返却する。 <BR>
     * @@param l_feqPrduct - (外国株式銘柄) <BR>
     * 外国株式銘柄オブジェクト
     *
     * @@param l_dblPrice - (単価)
     *
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 42A8276F027F
     */
    public double getTickValue(WEB3FeqProduct l_feqPrduct, double l_dblPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTickValue(WEB3FeqProduct l_feqPrduct, double l_dblPrice) ";
        log.entering(STR_METHOD_NAME);

        if (l_feqPrduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １）対象行取得
        //
        //    外株呼値テーブルを以下の条件で取得する。
        //
        //    [条件]
        //    市場コード = 引数.外国株式銘柄.市場コード
        //
        // 　@条件に一致する行より、
        // 　@（外株呼値テーブル.下限値　@< 引数.単価 <= 外株呼値テーブル.上限値）
        // 　@ に該当する行の行オブジェクトを取得する。
        //
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "market_code = ? and low_price < ? and cap_price >= ?";
            Object[] l_objWhereValues = new Object[3];
            l_objWhereValues[0] = l_feqPrduct.getMarketCode();
            l_objWhereValues[1] = new Double(l_dblPrice);
            l_objWhereValues[2] = new Double(l_dblPrice);
            List l_lisFeqTickValueMst =
                l_processor.doFindAllQuery(FeqTickValuesMstRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            // ２）刻み値返却
            //
            //    取得した行.刻み値を返却する。
            if (l_lisFeqTickValueMst == null || l_lisFeqTickValueMst.isEmpty())
            {
                log.debug("FeqTickValuesMstRow取得しない");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_lisFeqTickValueMst.size() > 1)
            {
                log.debug("FeqTickValuesMstRow取得>1");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            FeqTickValuesMstRow l_feqTickValuesMstRow = (FeqTickValuesMstRow)l_lisFeqTickValueMst.get(0);
            return l_feqTickValuesMstRow.getTickValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (get制限値幅) <BR>
     * 値幅テーブルから、該当する制限値幅を取得する。 <BR>
     *  <BR>
     * １）対象行取得 <BR>
     *  <BR>
     *    外株値幅テーブルを以下の条件で取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    市場コード = 引数.外国株式銘柄.市場コード <BR>
     *  <BR>
     *    条件に一致する行より、 <BR>
     * 　@（外株値幅テーブル.下限値　@< 引数.単価 <= 外株値幅テーブル.上限値） <BR>
     * 　@ に該当する行の行オブジェクトを取得する。 <BR>
     *  <BR>
     * ２）値幅返却 <BR>
     *  <BR>
     *    取得した行.値幅を返却する。 <BR>
     * @@param l_feqPrduct - (外国株式銘柄) <BR>
     * 外国株式銘柄オブジェクト
     *
     * @@param l_dblPrice - (単価)
     *
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 42A82862028E
     */
    public double getLimitRange(WEB3FeqProduct l_feqPrduct, double l_dblPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLimitRange(WEB3FeqProduct l_feqPrduct, double l_dblPrice) ";
        log.entering(STR_METHOD_NAME);

        if (l_feqPrduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // １）対象行取得
        //
        //    外株値幅テーブルを以下の条件で取得する。
        //
        //    [条件]
        //    市場コード = 引数.外国株式銘柄.市場コード
        //
        //    条件に一致する行より、
        // 　@（外株値幅テーブル.下限値　@< 引数.単価 <= 外株値幅テーブル.上限値）
        // 　@ に該当する行の行オブジェクトを取得する。
        //
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "market_code = ? and low_price < ? and cap_price >= ?";
            Object[] l_objWhereValues = new Object[3];
            l_objWhereValues[0] = l_feqPrduct.getMarketCode();
            l_objWhereValues[1] = new Double(l_dblPrice);
            l_objWhereValues[2] = new Double(l_dblPrice);
            List l_lisFeqLimitPriceRangeMst =
                l_processor.doFindAllQuery(FeqLimitPriceRangeMstRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            // ２）値幅返却
            //
            //    取得した行.値幅を返却する。

            if (l_lisFeqLimitPriceRangeMst == null || l_lisFeqLimitPriceRangeMst.isEmpty())
            {
                log.debug("外株値幅テーブル取得した行 == null");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (l_lisFeqLimitPriceRangeMst.size() > 1)
            {
                log.debug("外株値幅テーブルは不整合ある。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            FeqLimitPriceRangeMstRow l_feqLimitPriceRangeMst =
                (FeqLimitPriceRangeMstRow)l_lisFeqLimitPriceRangeMst.get(0);
            log.exiting(STR_METHOD_NAME);
            return l_feqLimitPriceRangeMst.getRange();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }

    }

    /**
     * (get通貨) <BR>
     * 定証券会社が登録している通貨をすべて取得する。<BR>
     * <BR>
     * （共通）通貨テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@（共通）通貨テーブル.証券会社コード = 証券会社コード<BR>
     * <BR>
     * 該当行にて（共通）通貨オブジェクトを生成し、配列にて返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 42AFD30101C4
     */
    public WEB3GentradeCurrency[] getCurrency(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCurrency(String l_strInstitutionCode) ";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            Object[] l_objWhereValues = {l_strInstitutionCode};
            List l_lisCurrencys =
                l_processor.doFindAllQuery(GenCurrencyRow.TYPE,
                    "institution_code = ?",
                    l_objWhereValues);
            if (l_lisCurrencys == null || l_lisCurrencys.isEmpty())
            {
                log.debug("通貨オブジェクト == null");
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            GenCurrencyRow[] l_currencyRow = new GenCurrencyRow[l_lisCurrencys.size()];
            l_lisCurrencys.toArray(l_currencyRow);

            List l_lisGenCurrencys = new ArrayList();
            for (int i = 0; i < l_currencyRow.length; i++)
            {
                WEB3GentradeCurrency l_currency =
                    new WEB3GentradeCurrency(new GenCurrencyParams(l_currencyRow[i]));
                l_lisGenCurrencys.add(l_currency);
            }
            WEB3GentradeCurrency[] l_genCurrency = new WEB3GentradeCurrency[l_lisGenCurrencys.size()];
            l_lisGenCurrencys.toArray(l_genCurrency);

            return l_genCurrency;
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    public Product toProduct(Row l_prow)
    {
        try
        {
            if (l_prow instanceof ProductRow)
            {
                return new WEB3FeqProduct((ProductRow)l_prow);
            }
            else
            {
                return new WEB3FeqProduct((FeqProductRow)l_prow);
            }

        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating WEB3FeqProduct for product id: " + ((ProductRow)l_prow).getProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);
        }

    }

    public TradedProduct toTradedProduct(Row l_tprow)
    {
        try
        {
            if (l_tprow instanceof TradedProductRow)
            {
                return new WEB3FeqTradedProduct((TradedProductRow)l_tprow);
            }
            else
            {
                return new WEB3FeqTradedProduct((FeqTradedProductRow)l_tprow);
            }

        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating FeqTradedProduct for traded product id: " + ((TradedProductRow)l_tprow).getTradedProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);
        }
    }

    public FeqTradedProduct getFeqTradedProduct(Institution inst, String productCode, String marketCode)
        throws NotFoundException
    {
        Product l_product;
        Market l_market;

        FeqTradedProduct l_feqTradedProduct =
            super.getFeqTradedProduct(inst, productCode, marketCode);

        return (FeqTradedProduct)toTradedProduct((FeqTradedProductRow)l_feqTradedProduct.getDataSourceObject());
    }
}
@
