head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : AIOプロダクトマネージャ(WEB3AioProductManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 屈陽 (中訊) 新規作成
                   2005/03/31 王蘭芬(中訊) 仕様変更の対応
                   2006/10/25 車進 (中訊) モデルNo.662
                   2006/10/26 車進 (中訊) モデルNo.663
*/

package webbroker3.aio;

import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProductManager;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (AIOプロダクトマネージャ)<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioProductManager implements ProductManager
{
    /**
    * ログユーティリティ
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioProductManager.class);

    /**
     * (get銘柄)<BR>
     * (getProductのオーバーロード)<BR>
     * 銘柄タイプ、銘柄IDに合致する銘柄オブジェクトを取得する。<BR>
     * <BR>
     * １）トレーディングモジュールの取得<BR>
     * <BR>
     * 　@FinApp.getTradingModule(arg0)<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@arg0： 引数.銘柄タイプ<BR>
     * <BR>
     * ２）プロダクトマネージャの取得<BR>
     * <BR>
     * 　@TradingModule.getProductManager()<BR>
     * <BR>
     * ３）銘柄オブジェクトの取得<BR>
     * <BR>
     * 　@ProductManager.getProduct(arg0)<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@arg0： 引数.銘柄ID<BR>
     * @@param l_productType - 銘柄タイプ
     * @@param l_lngProductId - 銘柄ID
     * @@return Product
     * @@throws WEB3BaseException
     * @@roseuid 4164A37F02E9
     */
    public Product getProduct(ProductTypeEnum l_productType, long l_lngProductId)
        throws WEB3BaseException
    {
        String l_strMethodName =
            "getProduct(ProductTypeEnum l_productType, long l_lngProductId)";
        log.entering(l_strMethodName);

        // 1）トレーディングモジュールの取得
        //FinApp.getTradingModule(arg0)
        //[引数]
        //arg0： 引数.銘柄タイプ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(l_productType);

        // ２）プロダクトマネージャの取得
        //TradingModule.getProductManager()
        ProductManager l_productManager = l_tradingModule.getProductManager();

        // 3）銘柄オブジェクトの取得
        //ProductManager.getProduct(arg0)
        //[引数]
        //arg0： 引数.銘柄ID
        Product l_product = null;
        try
        {
            // test log
            log.debug("l_productId = " + l_lngProductId);
            
            l_product = l_productManager.getProduct(l_lngProductId);

            // test log
            log.debug("l_product = " + l_product.getDataSourceObject());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in 銘柄オブジェクトの取得__", l_ex);
            log.exiting(l_strMethodName);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(l_strMethodName);

        return l_product;
    }

    /**
     * (get銘柄)<BR>
     * (getProductのオーバーロード)<BR>
     * 銘柄タイプ、銘柄コードに合致する銘柄オブジェクトを取得する。<BR>
     * <BR>
     * １）引数.銘柄タイプ == ”債券” の場合<BR>
     * <BR>
     * １－１）引数.銘柄コードから、債券の銘柄コード(SONAR)と回号コード(SONAR)を取得する。<BR>
     * <BR>
     * 　@回号コード(SONAR) = 引数.銘柄コードの先頭から5文字の文字列<BR>
     * 　@銘柄コード(SONAR) = 引数.銘柄コードの6文字目以降の文字列<BR>
     * <BR>
     * １－２）以下の条件で、債券銘柄マスタテーブルからレコードを取得する。<BR>
     * <BR>
     * 　@[取得条件]<BR>
     * 　@証券会社コード = 引数.証券会社.getInstitutionCode()の戻り値<BR>
     * 　@銘柄コード(SONAR) like '銘柄コード(SONAR)%'<BR>
     * 　@回号コード(SONAR) = 回号コード(SONAR)<BR>
     * <BR>
     * １－３）取得したレコードから債券銘柄マスタオブジェクトを生成して、返却する。<BR>
     * <BR>
     * ２）引数.銘柄タイプ != ”債券” の場合<BR>
     * <BR>
     * ２－１）トレーディングモジュールの取得<BR>
     * <BR>
     * 　@FinApp.getTradingModule(arg0)<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@arg0： 引数.銘柄タイプ<BR>
     * <BR>
     * ２－２）プロダクトマネージャの取得<BR>
     * <BR>
     * 　@TradingModule.getProductManager()<BR> 
     * <BR>
     * ２－３）銘柄オブジェクトの取得<BR>
     * <BR>
     * ２－３－１）引数.銘柄タイプ=”株式” の場合<BR>
     * <BR>
     * 　@ProductManager.getProduct(arg0, arg1)<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@arg0： 引数.証券会社<BR>
     * 　@arg1： 引数.銘柄コード<BR>
     * <BR>
     * ２－３－２）引数.銘柄タイプ=”投信” の場合<BR>
     * <BR>
     * 　@ProductManager.getMutualFundProduct(arg0, arg1, arg2)<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@arg0： 引数.証券会社<BR>
     * 　@arg1： 引数.銘柄コード<BR>
     * 　@arg2： 回号コード(=0)<BR>
     * @@param l_productType - 銘柄タイプ
     * @@param l_strProductCode - 銘柄コード
     * @@param l_institusion - 証券会社オブジェクト
     * @@return Product
     * @@throws WEB3BaseException
     * @@roseuid 4164A37F02EC
     */
    public Product getProduct(ProductTypeEnum l_productType, String l_strProductCode, Institution l_institusion)
        throws WEB3BaseException
    {
        String l_strMethodName =
            "getProduct(ProductTypeEnum l_productType, String l_strProductCode, Institution l_institusion)";
        log.entering(l_strMethodName);

        // １）引数.銘柄タイプ == ”債券” の場合
        if (ProductTypeEnum.BOND.equals(l_productType))
        {
            //１－１）引数.銘柄コードから、債券の銘柄コード(SONAR)と回号コード(SONAR)を取得する。
            //回号コード(SONAR) = 引数.銘柄コードの先頭から5文字の文字列
            //銘柄コード(SONAR) = 引数.銘柄コードの6文字目以降の文字列

            String l_strHostProductIssueCode = l_strProductCode.substring(0, 5);
            String l_strHostProductCode = l_strProductCode.substring(5);

            // test log
            log.debug("l_strHostProductIssueCode = " + l_strHostProductIssueCode);
            log.debug("l_strHostProductCode = " + l_strHostProductCode);

            //１－２）以下の条件で、債券銘柄マスタテーブルからレコードを取得する。
            //[取得条件]
            //証券会社コード = 引数.証券会社.getInstitutionCode()の戻り値
            //銘柄コード(SONAR) like '銘柄コード(SONAR)%'
            //回号コード(SONAR) = 回号コード(SONAR)
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lislist = l_queryProcessor.doFindAllQuery(
                     BondProductRow.TYPE,
                    "institution_code = ? and host_product_code like ? ||'%' and host_product_issue_code = ?",
                    null,
                    new Object[]{l_institusion.getInstitutionCode(), l_strHostProductCode, l_strHostProductIssueCode});
                switch (l_lislist.size())
                {
                    //１－３）取得したレコードから債券銘柄マスタオブジェクトを生成して、返却する。
                    case 1: return new WEB3BondProduct((BondProductRow)(l_lislist.get(0)));
                    default:
                        throw new DataFindException(
                        "too many or no results in query WEB3AioProductManager.getProduct("
                        + "ProductTypeEnum l_productType, String l_strProductCode, Institution l_institusion): "
                        + l_lislist.size());
                }
            }
            catch (DataException l_ex)
            {
                log.error("__error in 銘柄オブジェクト（債券)の取得__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }

        }

        //２）引数.銘柄タイプ != ”債券” の場合
        //
        //２－１）トレーディングモジュールの取得
        //
        //FinApp.getTradingModule(arg0)
        //
        //[引数]
        //arg0： 引数.銘柄タイプ
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(l_productType);

        //２－２）プロダクトマネージャの取得
        //
        //TradingModule.getProductManager()
        ProductManager l_productManager = l_tradingModule.getProductManager();

        //２－３）銘柄オブジェクトの取得
        //
        Product l_product = null;

        //２－３－１）引数.銘柄タイプ=”株式” の場合
        //
        //ProductManager.getProduct(arg0, arg1)
        //
        //[引数]
        //arg0： 引数.証券会社
        //arg1： 引数.銘柄コード
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {

            //Cast of the productManager
            EqTypeProductManager l_eqTypeProductManager = (EqTypeProductManager)l_productManager;
            try
            {
                l_product =
                    l_eqTypeProductManager.getProduct(
                        l_institusion,
                        l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in 銘柄オブジェクト（株式)の取得__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //２－３－２）引数.銘柄タイプ=”投信” の場合
        //
        //ProductManager.getMutualFundProduct(arg0, arg1, arg2)
        //
        //[引数]
        //arg0： 引数.証券会社
        //arg1： 引数.銘柄コード
        //arg2： 回号コード(=0)
        else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productType))
        {
            //Cast of the productManager
            MutualFundProductManager l_mutualFundProductManager =
                (MutualFundProductManager)l_productManager;
            try
            {
                l_product =
                    l_mutualFundProductManager.getMutualFundProduct(
                        l_institusion,
                        l_strProductCode,
                        "0");
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in 銘柄オブジェクト（投信)の取得__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(l_strMethodName);
        return l_product;
    }

    /**
     * @@param arg0
     * @@return Product
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B0255F0177
     */
    public Product getProduct(long arg0) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B0255F0280
     */
    public TradedProduct getTradedProduct(Product arg0, Market arg1) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@return TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B02560005D
     */
    public TradedProduct getTradedProduct(long arg0, long arg1) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@return TradedProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     * @@roseuid 41B025600213
     */
    public TradedProduct getTradedProduct(long arg0) throws NotFoundException 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@return Product
     * @@roseuid 41B02560032C
     */
    public Product toProduct(Row arg0) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@return TradedProduct
     * @@roseuid 41B02561004E
     */
    public TradedProduct toTradedProduct(Row arg0) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@return java.util.List
     * @@roseuid 41B025610157
     */
    public List searchProducts(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@return java.util.List
     * @@roseuid 41B025620167
     */
    public List searchProductsBeginningWith(Institution arg0, ProductTypeEnum arg1, String arg2, PaginationQueryParamsSpec arg3, SortKeySpec arg4) 
    {
     return null;
    }
    
    /**
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@return java.util.List
     * @@roseuid 41B025630148
     */
    public List searchProducts(ProductTypeEnum arg0, Market arg1, PaginationQueryParamsSpec arg2, SortKeySpec arg3) 
    {
     return null;
    }
}
@
