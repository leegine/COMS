head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張累投銘柄マネージャクラス(WEB3RuitoProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/9  韋念瓊 (中訊) 新規作成
                   2004/12/01 韋念瓊 (中訊) 残対応
*/

package webbroker3.xbruito;

import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoProductManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張累投銘柄マネージャクラス<BR>
 */
public class WEB3RuitoProductManager extends RuitoProductManagerImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoProductManager.class);

    /**
     * （getRuitoProductのオーバーライド）<BR>
     * <BR>
     * 拡張累投銘柄を取得する。<BR>
     * <BR>
     * １）　@累投銘柄Rowオブジェクトを取得する。<BR>
     * 　@－累投銘柄Dao.findRowByInstitutionCodeProductCodeProductIssueCode()をコールし、<BR>
     *     累投銘柄Rowオブジェクトを取得する。<BR>
     * 　@［findRowByInstitutionCodeProductCodeProductIssueCodeに渡すパラメタ］<BR>
     *      証券会社コード :  引数.証券会社コード <BR>
     * 　@　@　@銘柄コード： 引数.銘柄コード<BR>
     * 　@　@　@回号コード： 引数.回号コード<BR>
     * 　@－findRowByInstitutionCodeProductCodeProductIssueCodeメソッドがnullを返した場合は<BR>
     *     NotFoundExceptionをすスローする。<BR>
     * <BR>
     * ２）　@拡張累投銘柄オブジェクトを生成して返す。<BR>
     * 　@－拡張累投銘柄オブジェクトを生成する。<BR>
     * 　@　@［コンストラクタに渡すパラメタ］<BR>
     * 　@　@　@累投銘柄Row： 取得した累投銘柄オブジェクト<BR>
     * 　@－生成した拡張累投銘柄オブジェクトを返す。<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strProductIssueCode - 回号コード<BR>
     * @@return RuitoProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 406D08660379
     */
    public RuitoProduct getRuitoProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strProductIssueCode)
        throws NotFoundException
    {
        String STR_METHOD_NAME =
            "getRuitoProduct(Institution l_institution, String l_strProductCode,"
                + "String l_strProductIssueCode)";
        log.entering(STR_METHOD_NAME);
        if (l_institution == null)
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }          
        RuitoProductRow l_ruitoProductRow = null;
        WEB3RuitoProduct l_ruitoProduct = null;
        try
        {
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            
            log.debug("証券会社コード = " + l_strInstitutionCode);
            log.debug("銘柄コード = " + l_strProductCode);
            log.debug("回号コード = " + Long.parseLong(l_strProductIssueCode));
            //1)累投銘柄Rowオブジェクトを取得する。
            l_ruitoProductRow =
                RuitoProductDao.findRowByInstitutionCodeProductCodeProductIssueCode(
                    l_strInstitutionCode,
                    l_strProductCode,
                    l_strProductIssueCode);
            
            if (l_ruitoProductRow == null)
            {
                log.error("__NotFoundException__");
                throw new NotFoundException(
                    "テーブルに該当するデータがありません。");
            }
            //2)拡張累投銘柄オブジェクトを生成して返す。
            l_ruitoProduct = new WEB3RuitoProduct(l_ruitoProductRow);
            //生成した拡張累投銘柄オブジェクトを返す。
            log.debug("===return l_ruitoProduct===");
            log.exiting(STR_METHOD_NAME);
            return l_ruitoProduct;
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
    }

    /**
     * （getRuitoTradedProductのオーバーライド）<BR>
     * <BR>
     * 拡張累投取引銘柄を取得する。<BR>
     * <BR>
     *１）　@累投取引銘柄オブジェクトを取得する。 <BR>                                                                                                                  
     *  super.getRuitoTradedProduct()をコールして、<BR>
     *  累投取引銘柄オブジェクトを取得する。<BR>                                                                                                                     
     *  [getRuitoTradedProductに渡すパラメタ]<BR>                                                                                                                     
     *　@　@証券会社： 引数.証券会社 <BR>                                                                                                                     
     *　@　@銘柄コード： 引数.銘柄コード <BR>                                                                                                                      
     *　@  回号コード： 引数.回号コード<BR>                                                                                                                       
     *　@  市場コード： 引数.市場コード <BR>                                                                                                                      
     *２）　@拡張累投取引銘柄オブジェクトを取得する。<BR>                                                                                                                     
     *　@this.to取引銘柄()をコールして、拡張累投取引銘柄オブジェクトを取得する。<BR>                                                                                                                       
     *  [to取引銘柄に渡すパラメタ］<BR>                                                                                                                        
     *　@　@Rowオブジェクト： 取得した累投取引銘柄オブジェクト<BR>
     *    .getDataSourceObject()の戻り値<BR>                                                                                                                     
     *３）　@取得した拡張累投取引銘柄オブジェクトを返す。<BR>                                                                                                                  

     * @@param l_institution - 証券会社<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_strProductIssueCode - 回号コード<BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * @@return RuitoTradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 406D08660389
     */
    public RuitoTradedProduct getRuitoTradedProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strProductIssueCode,
        String l_strMarketCode)
        throws NotFoundException
    {
        String STR_METHOD_NAME =
            "getRuitoTradedProduct(Institution l_institution, "
                + "String l_strProductCode, String l_strProductIssueCode, "
                + "String l_strMarketCode)";
        log.entering(STR_METHOD_NAME);
        
        WEB3RuitoTradedProduct l_webRuitoTradedProduct = null;
        RuitoTradedProductRow l_ruitoTradeProductRow = null;       
        
        if (l_institution == null)
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }   
        //1)累投取引銘柄Rowオブジェクトを取得する。
        RuitoTradedProduct l_ruitoTradedProduct = null;
        //NotFoundException
        l_ruitoTradedProduct = super.getRuitoTradedProduct(
                l_institution,
                l_strProductCode,
                l_strProductIssueCode,
                l_strMarketCode);
        
        //２）拡張累投取引銘柄オブジェクトを生成して返す。
        l_ruitoTradeProductRow = (RuitoTradedProductRow)
                l_ruitoTradedProduct.getDataSourceObject();
        l_webRuitoTradedProduct = (WEB3RuitoTradedProduct)
                this.toTradedProduct(l_ruitoTradeProductRow);

        log.exiting(STR_METHOD_NAME);
        //３）取得した拡張累投取引銘柄オブジェクトを返す。
        return l_webRuitoTradedProduct;
    }

    /**
     * （toProductのオーバーライド）<BR>
     * <BR>
     * 指定の銘柄Rowオブジェクトから拡張累投銘柄オブジェクトを作成して返す。<BR>
     * <BR>
     * １）引数.RowオブジェクトがProductRowクラスのインスタンスでなく、<BR>
     *  かつRuitoProductRowクラスのインスタンスでない場合、<BR>
     *  IllegalArgumentExceptionをスローする。<BR>
     * <BR>
     * ２）拡張累投銘柄オブジェクトを生成する。<BR>
     * 　@－引数.RowオブジェクトがProductRowクラスのインスタンスの場合<BR>
     * 　@［コンストラクタに渡すパラメタ］<BR>
     *    銘柄Row： 引数.RowオブジェクトをProductRowクラス
     *                  にキャストしたもの<BR>
     *   －引数.RowオブジェクトがRuitoProductRowクラスのインスタンス<BR>
     *      の場合 <BR>
     *   ［コンストラクタに渡すパラメタ］
     *    累投銘柄Row： 引数.RowオブジェクトをRuitoProductRowクラス<BR>
     *                      にキャストしたもの <BR>
     * <BR>
     * ３）生成した拡張累投銘柄オブジェクトを返す。<BR>
     * @@param l_row - Rowオブジェクト<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Product
     * @@roseuid 406D0889009B
     */
    public Product toProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toProduct(Row l_Row)";
        log.entering(STR_METHOD_NAME);

        Product l_product = null;
        try
        {
            if ((l_row instanceof ProductRow) == false
                && (l_row instanceof RuitoProductRow) == false)
            {
                throw new IllegalArgumentException(
                    this.getClass().getName() + "toProduct(Row l_row)");
            }
            if (l_row instanceof ProductRow)
            {
                ProductParams l_productParams = (ProductParams) l_row;
                //拡張累投銘柄オブジェクトを生成する
                WEB3RuitoProduct l_ruitoProduct = new WEB3RuitoProduct(l_productParams);
                l_product = (Product) l_ruitoProduct;
            }
            if (l_row instanceof RuitoProductRow)
            {
                RuitoProductParams l_ruitoProductParams = (RuitoProductParams) l_row;
                //拡張累投銘柄オブジェクトを生成する
                WEB3RuitoProduct l_ruitoProduct = new WEB3RuitoProduct(l_ruitoProductParams);
                l_product = (Product) l_ruitoProduct;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        log.exiting(STR_METHOD_NAME);
        //３）生成した拡張累投銘柄オブジェクトを返す。
        return l_product;
    }

    /**
     * （toTradedProductのオーバーライド）<BR>
     * <BR>
     * 指定のRowオブジェクトから拡張累投取引銘柄オブジェクトを作成して返す。<BR>
     * <BR>
     * １）引数.RowオブジェクトがTradedProductRowクラスのインスタンスでなく、<BR>
     *  かつRuitoTradedProductRowクラスのインスタンスでない場合、<BR>
     *  IllegalArgumentExceptionをスローする。<BR>
     * <BR>
     * ２）拡張累投取引銘柄オブジェクトを生成する。<BR>
     * 　@－引数.RowオブジェクトがTradedProductRowクラスのインスタンスの場合<BR>
     * 　@［コンストラクタに渡すパラメタ］<BR>
     *    取引銘柄Row： 引数.RowオブジェクトをTradedProductRowクラス
     *                  にキャストしたもの<BR>
     *   －引数.RowオブジェクトがRuitoTradedProductRowクラスのインスタンス<BR>
     *      の場合 <BR>
     *   ［コンストラクタに渡すパラメタ］
     *    累投取引銘柄Row： 引数.RowオブジェクトをRuitoTradedProductRowクラス<BR>
     *                      にキャストしたもの <BR>
     * <BR>
     * ３）生成した拡張累投銘柄オブジェクトを返す。<BR>
     * @@param l_row - Rowオブジェクト<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct
     * @@roseuid 406D088900AB
     */
    public TradedProduct toTradedProduct(Row l_row)
    {
        String STR_METHOD_NAME = "toTradedProduct(Row l_Row)";
        log.entering(STR_METHOD_NAME);

        TradedProduct l_tradedProduct = null;
        WEB3RuitoTradedProduct l_ruitoTradedProduct = null;

        try
        {
            if ((l_row instanceof TradedProductRow) == false 
                && (l_row instanceof RuitoTradedProductRow) == false)
            {
                throw new IllegalArgumentException(
                    "Expected TradedProductRow or RuitoTradedProductRow."
                        + "But the given Row is the type of: "
                        + l_row.getClass());
            }
            if (l_row instanceof TradedProductRow)
            {
                TradedProductRow l_tradeProductRow = (TradedProductRow) l_row;
                //拡張累投銘柄オブジェクトを生成する
                //DataQueryException,DataNetworkException
                l_ruitoTradedProduct = new WEB3RuitoTradedProduct(l_tradeProductRow);
                l_tradedProduct = (TradedProduct) l_ruitoTradedProduct;
            }
            if (l_row instanceof RuitoTradedProductRow)
            {
                RuitoTradedProductRow l_ruitoTradeProductRow = (RuitoTradedProductRow) l_row;
                //拡張累投銘柄オブジェクトを生成する
                //DataQueryException,DataNetworkException
                l_ruitoTradedProduct = new WEB3RuitoTradedProduct(l_ruitoTradeProductRow);
                l_tradedProduct = (TradedProduct) l_ruitoTradedProduct;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        log.exiting(STR_METHOD_NAME);
        //３）生成した拡張累投銘柄オブジェクトを返す。
        return l_tradedProduct;
    }

    /**
     * 引数.MRFコードに対応する拡張累投銘柄オブジェクトを返す。<BR>
     * <BR>
     * １）　@以下の条件で累投銘柄テーブルを検索し、累投銘柄Paramsを取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード = 引数.証券会社.getInstitutionCode()の戻り値 AND<BR>
     * 　@　@MRFコード = 引数.MRFコード<BR>
     * <BR>
     * 　@対象レコードがない、または対象レコードが複数ある場合は[データ不整合]の<BR>
     *       例外をスローする。<BR>
     *  　@　@エラータグ：   BUSINESS_ERROR_00193<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * 
     * <BR>
     * ２）　@拡張累投銘柄オブジェクトの生成<BR>
     * <BR>
     * 　@[拡張累投銘柄のコンストラクタに渡すパラメタ]<BR>
     * 　@　@累投銘柄Row： １）で取得した累投銘柄Params<BR>
     * <BR>
     * ３）　@拡張累投銘柄オブジェクトを返す。<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strMRFCode - MRFコード<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 4076337B0280
     */
    public WEB3RuitoProduct getMRFProduct(
            Institution l_institution, 
            String l_strMRFCode)
    {
        String STR_METHOD_NAME =
            "getMRFProduct(Institution l_institution," + "String l_strMRFCode)";
        log.entering(STR_METHOD_NAME);
        if (l_institution == null)
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        
        try
        {    
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            String l_strWhereClause = 
                "institution_code = ? and mrf_fund_code = ?";
            //DataQueryException,DataNetworkException
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhereClause,
                    new Object[] { l_strInstitutionCode, l_strMRFCode });

            if (l_lisRows == null || l_lisRows.size() > 1)
            {
                log.debug("拡張累投銘柄がない、または拡張累投銘柄がデータ不整合です");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00193,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "拡張累投銘柄がない、または拡張累投銘柄がデータ不整合です");               
            }
            RuitoProductRow l_ruitoProductRow = null;
                        
            l_ruitoProductRow = (RuitoProductRow) l_lisRows.get(0);
            
            WEB3RuitoProduct l_ruitoProduct = 
                new WEB3RuitoProduct(l_ruitoProductRow);
                
            log.exiting(STR_METHOD_NAME);
            return l_ruitoProduct;
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * （getRuitoProductのオーバーロード）<BR>
     * <BR>
     * 拡張累投銘柄を取得する。<BR>
     * <BR>
     * １）　@this.get拡張累投銘柄()をコールし、その戻り値を返す。<BR>
     * 　@［get拡張累投銘柄に渡すパラメタ］<BR>
     * 　@　@証券会社： 引数.証券会社<BR>
     * 　@　@銘柄コード： 引数銘柄コード<BR>
     * 　@　@回号コード： ”0：DEFAULT”<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@return RuitoProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 407B719F0095
     */
    public RuitoProduct getRuitoProduct(
            Institution l_institution, String l_strProductCode)
        throws NotFoundException
    {       
        return this.getRuitoProduct(l_institution, l_strProductCode, "0");
    }

    /**
     * （getRuitoTradedProductのオーバーロード）<BR>
     * <BR>
     * 拡張累投取引銘柄を取得する。<BR>
     * <BR>
     * １）　@this.get拡張累投取引銘柄()をコールし、その戻り値を返す。<BR>
     * 　@［get拡張累投取引銘柄に渡すパラメタ］<BR>
     * 　@　@証券会社： 引数.証券会社<BR>
     * 　@　@銘柄コード： 引数銘柄コード<BR>
     * 　@　@回号コード： ”0：DEFAULT”<BR>
     * 　@　@市場コード： ”0：DEFAULT”<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@return RuitoTradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 407B75B202A8
     */
    public RuitoTradedProduct getRuitoTradedProduct(
        Institution l_institution,
        String l_strProductCode)
        throws NotFoundException
    {
        return this.getRuitoTradedProduct(
            l_institution,
            l_strProductCode,
            "0",
            WEB3MarketCodeDef.DEFAULT);
    }

    /**
     * get受渡日<BR>
     * <BR>
     * 受渡日を返す。<BR>
     * <BR>
     * １）　@拡張累投取引銘柄オブジェクトを取得する。<BR>
     * 　@this.get累投取引銘柄()をコールして、<BR>
     *       拡張累投取引銘柄オブジェクトを取得する。<BR>
     * 　@［get累投取引銘柄に渡すパラメタ］<BR>
     * 　@　@証券会社： 引数.証券会社<BR>
     * 　@　@銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * ２）　@受渡日を取得する。<BR>
     * 　@拡張累投取引銘柄.get受渡日()をコールして、受渡日を取得する。<BR>
     * 　@［get受渡日に渡すパラメタ］<BR>
     * 　@　@is買付： 引数.is買付<BR>
     * <BR>
     * ３）　@取得した受渡日を返す。<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param isBuy - 買付の場合はtrueを、解約の場合はfalseを指定する<BR>
     * @@return Date
     * @@roseuid 407BCD620392
     */
    public Date getDeliveryDate(
            Institution l_institution, String l_strProductCode, boolean isBuy)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getDeliveryDate(Institution l_institution, "
                + "String l_strProductCode, boolean isBuy) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //拡張累投取引銘柄オブジェクトを取得する。
        RuitoTradedProduct l_ruitoTradedProduct = null;
        RuitoTradedProductRow l_ruitoTradedProductRow = null;
        Date l_datDeliveryDate = null;
        try
        {
            l_ruitoTradedProduct = this.getRuitoTradedProduct(
                l_institution, l_strProductCode);
            l_ruitoTradedProductRow = (RuitoTradedProductRow) 
                l_ruitoTradedProduct.getDataSourceObject();
            
            WEB3RuitoTradedProduct l_web3ruitoTradedProduct =
                new WEB3RuitoTradedProduct(l_ruitoTradedProductRow);
            //受渡日を取得する。
            //NotFoundException,DataQueryException,DataNetworkException
            l_datDeliveryDate = 
                l_web3ruitoTradedProduct.getDailyDeliveryDate(isBuy);

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        //取得した受渡日を返す。
        return l_datDeliveryDate;

    }

    /**
     * (拡張累投銘柄 get累投銘柄（withコースandプラン）)
     * 
     * 指定されたコース、プランに対応する拡張累投銘柄オブジェクトを返す。<BR>
     * <BR>
     * １）　@以下の条件で累投銘柄テーブルを検索し、累投銘柄Paramsを取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード = 引数.証券会社.getInstitutionCode()の戻り値 AND<BR>
     * 　@　@コース = 引数.コース AND<BR>
     * 　@　@プラン = 引数.プラン<BR>
     * <BR>
     * 　@対象レコードがない、または対象レコードが複数ある場合は[データ不整合]の<BR>
     *       例外をスローする。<BR>
     *     　@エラータグ：   BUSINESS_ERROR_00193<BR>
     *   　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * ２）　@拡張累投銘柄オブジェクトの生成<BR>
     * <BR>
     * 　@[拡張累投銘柄のコンストラクタに渡すパラメタ]<BR>
     * 　@　@累投銘柄Row： １）で取得した累投銘柄Params<BR>
     * <BR>
     * ３）　@拡張累投銘柄オブジェクトを返す。<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strCourse - コース<BR>
     * @@param l_strPlan - プラン<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 4086524D00AB
     */
    public WEB3RuitoProduct getRuitoProductWithCoursePlan(
        Institution l_institution,
        String l_strCourse,
        String l_strPlan)
    {
        String STR_METHOD_NAME =
            "getRuitoProductWithCoursePlan "
                + "(Institution l_institution, "
                + "String l_strCourse, String l_strPlan)";
        log.entering(STR_METHOD_NAME);
        if (l_institution == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        String l_institutionCode;
        l_institutionCode = l_institution.getInstitutionCode();
        List l_lisRows = null;
        WEB3RuitoProduct l_ruitoProduct = null;

        try
        {
            String l_strWhereClause = "institution_code = ? " + 
                                      "and course = ? " + "and plan = ?";
            log.debug("l_strWhereClause = " + l_strWhereClause);
            //DataQueryException,DataNetworkException
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhereClause,
                    new Object[] { l_institutionCode, l_strCourse, l_strPlan });

            if (l_lisRows.isEmpty() || l_lisRows.size() > 1)
            {
                log.debug("拡張累投銘柄がない、または拡張累投銘柄がデータ不整合です");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);               
            }            
            RuitoProductRow l_ruitoProductRow = null;
            l_ruitoProductRow = (RuitoProductRow) l_lisRows.get(0);
            
            l_ruitoProduct = new WEB3RuitoProduct(l_ruitoProductRow);

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ruitoProduct;
    }

    /**
     * 指定された銘柄IDに対応する拡張累投銘柄オブジェクトを返す。<BR>
     * <BR>
     * １）　@this.getProduct()をコールしProductオブジェクトを取得する。<BR>                                                                                                                      
     *　@　@［getProductに渡すパラメタ］<BR>                                                                                                                     
     *　@　@銘柄ID： 引数.銘柄ID <BR>                                                                                                                     
     *                                                                                                                   
     *２）　@this.to銘柄()をコールし、拡張累投銘柄オブジェクトを取得する。<BR>                                                                                                                       
     *　@　@［to銘柄に渡すパラメタ］<BR>                                                                                                                       
     *　@　@　@銘柄Row： 取得したProductオブジェクト.getDataSourceObject()<BR>
     *      の戻り値 <BR>                                                                                                                    
     * <BR>                                                                                                                  
     *３）　@取得した拡張累投銘柄オブジェクトを返す。<BR>                                                                                                                      

     * @@param l_lngProductID - 銘柄ID<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 40886EDD01E3
     */
    public WEB3RuitoProduct getRuitoProduct(long l_lngProductID)
        throws NotFoundException
    {
        String STR_METHOD_NAME = "getRuitoProduct(long l_lngProductID)";
        log.entering(STR_METHOD_NAME);

        WEB3RuitoProduct l_ruitoProduct = null;

        //１）this.getProduct()をコールしProductオブジェクトを取得する。
        l_ruitoProduct = (WEB3RuitoProduct)this.getProduct(l_lngProductID);
        
        log.debug("l_product.getProductId = " + l_ruitoProduct.getProductId());
        log.debug("l_ruitoProduct.getProductType() = " + l_ruitoProduct.getProductType());
        log.debug("l_ruitoProduct.getProductId() = " + l_ruitoProduct.getProductId());

        log.exiting(STR_METHOD_NAME);
        //３）　@取得した拡張累投銘柄オブジェクトを返す。
        return l_ruitoProduct;
    }
    
    /**
     * (get累投銘柄一覧)
     * 以下の条件で累投銘柄テーブルを検索し、累投銘柄ParamsのList を取得する。<BR>
     * <BR>
     * ［検索条件］ <BR>                                                                                                                      
     *　@　@証券会社コード = 引数.証券会社コード AND <BR>                                                                                                                     
     *　@　@(累投タイプ　@= RuitoTypeEnum.中期国債ファ@ンド OR 累投タイプ = RuitoTypeEnum.MMF) <BR>                                                                                                                     
     * <BR>
     * ［orderBy］<BR>                                                                                                                       
     *　@　@”コース asc, プラン desc” <BR>    
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoProductImpl
     * @@roseuid 40886EDD01E3
     */
    public List getRuitoProductList(String l_strInstitutionCode)
    {
        String STR_METHOD_NAME = "getRuitoProductList(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);

        List l_lisProductRows = null;
        
        try
        {
            String l_strWhereClause = 
                    "institution_code = ? and " +
                    "(ruito_type = ? or ruito_type = ?)";
            String l_strOrderBy = "course asc, plan desc";
            
            //［検索条件］ 
            //証券会社コード = 引数.証券会社コード AND 
            //(累投タイプ　@= RuitoTypeEnum.中期国債ファ@ンド OR 累投タイプ = RuitoTypeEnum.MMF) 
            //［orderBy］ 
            //”コース asc, プラン desc” 
            l_lisProductRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoProductRow.TYPE,
                    l_strWhereClause,
                    l_strOrderBy,
                    null,
                    new Object[] { 
                        l_strInstitutionCode, 
                        RuitoTypeEnum.CHUUKOKU_FUND,
                        RuitoTypeEnum.MMF });
            
            log.debug("l_lisProductRows.size = " + l_lisProductRows.size());
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisProductRows;
    }
}
@
