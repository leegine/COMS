head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundProductManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信銘柄マネージャForMock(WEB3MutualFundProductManagerForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信銘柄マネージャForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundProductManagerForMock extends WEB3MutualFundProductManager
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductManagerForMock.class);

    /**
     * (to銘柄(Mock))<BR>
     * （toProductのオーバーライド）<BR>
     * <BR>
     * @@param l_rowObject - (Rowオブジェクト)<BR>
     * 銘柄Rowオブジェクト<BR>
     * @@return Product
     */
    public Product toProduct(Row l_rowObject)
    {
        final String STR_METHOD_NAME = "toProduct(Row l_rowObject)-->ForMock";
        log.entering(STR_METHOD_NAME);

        if (l_rowObject == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3MutualFundProductForMock l_web3MutualFundProduct = null;
        try
        {
            //１）　@引数.RowオブジェクトがProductRowクラスのインスタンスでなく、
            //　@かつMutualFundProductRowクラスのインスタンスでない場合
            if (!(l_rowObject instanceof ProductRow)
                && !(l_rowObject instanceof MutualFundProductRow))
            {
                log.debug("__an IllegalArgumentException__");
                log.exiting(STR_METHOD_NAME);
                throw new IllegalArgumentException(
                    this.getClass().getName() + "." + STR_METHOD_NAME );
            }
            
            //２）　@引数.RowがProductRowクラスのインスタンスである場合
            if (l_rowObject instanceof ProductRow)
            {
                l_web3MutualFundProduct =
                    new WEB3MutualFundProductForMock((ProductRow) l_rowObject);
            }
            
            //２）引数.RowがMutualFundProductRowクラスのインスタンスである場合
            if (l_rowObject instanceof MutualFundProductRow)
            {
                l_web3MutualFundProduct =
                    new WEB3MutualFundProductForMock(
                        (MutualFundProductRow) l_rowObject);
            }
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }

        //３）　@生成した拡張投信銘柄オブジェクトを返す
        return l_web3MutualFundProduct;
    }

    /**
     * (to取引銘柄(Mock))<BR>
     * <BR>
     * @@param l_rowObject - 取引銘柄Rowオブジェクト
     * @@return radedProduct
     */
    public TradedProduct toTradedProduct(Row l_rowObject)
    {
        final String STR_METHOD_NAME = "toTradedProduct(Row l_rowObject)-->ForMock";
        log.entering(STR_METHOD_NAME);

        if (l_rowObject == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3MutualFundTradedProductForMock l_web3MutualFundTradedProduct = null;
        try
        {
            //１）　@引数.RowオブジェクトがTradedProductRowクラスのインスタンスでなく、
            // 　@かつMutualFundTradedProductRowクラスのインスタンスでない場合は、
            if (!(l_rowObject instanceof TradedProductRow)
                && !(l_rowObject instanceof MutualFundTradedProductRow))
            {
                log.debug("__an IllegalArgumentException__");
                log.exiting(STR_METHOD_NAME);
                String l_strmsg = this.getClass().getName() + "." + STR_METHOD_NAME;
                throw new IllegalArgumentException(l_strmsg);
            }

            //２）　@引数.RowオブジェクトがTradedProductRowクラスのインスタンスである場合
            if (l_rowObject instanceof TradedProductRow)
            {
                l_web3MutualFundTradedProduct =
                    new WEB3MutualFundTradedProductForMock(
                        (TradedProductRow) l_rowObject);
            }

            //２）　@引数.RowオブジェクトがTradedProductRowクラスのインスタンスである場合
            if (l_rowObject instanceof MutualFundTradedProductRow)
            {
                l_web3MutualFundTradedProduct =
                    new WEB3MutualFundTradedProductForMock(
                        (MutualFundTradedProductRow) l_rowObject);
            }

        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }

        //３）　@生成した拡張投信取引銘柄オブジェクトを返す
        return l_web3MutualFundTradedProduct;
    }

    /**
     * (get投信銘柄(Mock))<BR>
     * （getMutualFundProductのオーバーライド）<BR>
     * @@param l_institution - 証券会社
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strProductIssueCode - 回号コード
     * @@return com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException
     */
    public MutualFundProduct getMutualFundProduct(
        Institution l_institution,
        String l_strProductCode,
        String l_strProductIssueCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME =
            "getMutualFundProduct(Institution, String, String)-->ForMock";
                
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getMutualFundProduct",
            new Class[] {Institution.class, String.class, String.class},
            new Object[]{l_institution, l_strProductCode, l_strProductIssueCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getMutualFundProduct",
            new Class[] {Institution.class, String.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);

            //3)MockFor --〉 asObject
            return (MutualFundProduct)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getMutualFundProduct(l_institution, l_strProductCode, l_strProductIssueCode);
    }

    
    /**
     * (get約定日(Mock))<BR>
     * 引数.発注日に対する約定日を返す。<BR>
     * @@param l_institution - 証券会社
     * @@param l_strProductCode - 銘柄コード
     * @@param l_datCurrentBizDate - 発注日
     * @@return Timestamp
     */
    public Timestamp getExecutedDate(
        Institution l_institution,
        String l_strProductCode,
        Date l_datCurrentBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getExecutedDate(Institution, String, Date)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getExecutedDate",
            new Class[] {Institution.class, String.class, Date.class},
            new Object[]{l_institution, l_strProductCode, l_datCurrentBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getExecutedDate",
            new Class[] {Institution.class, String.class, Date.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getExecutedDate",
                new Class[]{Institution.class, String.class, Date.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (Timestamp)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getExecutedDate",
                new Class[] {Institution.class, String.class, Date.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getExecutedDate(l_institution, l_strProductCode, l_datCurrentBizDate);
    }

    /**
     * (get受渡日(Mock))<BR>
     * 引数.約定日に対する受渡日を返す。<BR>
     * @@param l_institution - 証券会社<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * @@param l_blnIsAcquired - (is買付)<BR>
     * @@param l_datSwtExecutedDate - 約定日<BR>
     * 買付の場合はtrueを、解約の場合はfalseを指定する<BR>
     * @@return Timestamp
     */
    public Timestamp getDeliveryDate(
        Institution l_institution,
        String l_strProductCode,
        boolean l_blnIsAcquired,
        Date l_datSwtExecutedDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate(Institution, String, boolean, Date)";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getDeliveryDate",
            new Class[] {Institution.class, String.class, boolean.class, Date.class},
            new Object[]{
                l_institution,
                l_strProductCode,
                new Boolean(l_blnIsAcquired),
                l_datSwtExecutedDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getDeliveryDate",
            new Class[] {Institution.class, String.class, boolean.class, Date.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getDeliveryDate",
                new Class[] {
                    Institution.class,
                    String.class, boolean.class, Date.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (Timestamp)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getDeliveryDate",
                new Class[] {Institution.class, String.class, boolean.class, Date.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getDeliveryDate(l_institution, l_strProductCode, l_blnIsAcquired, l_datSwtExecutedDate);
    }

    /**
     * (get投信取引銘柄(Moc))<BR>
     * （getMutualFundTradedProductのオーバーロード）<BR>
     * @@param l_institution - 証券会社
     * @@param l_strProductCode - 銘柄コード
     */
    public MutualFundTradedProduct getMutualFundTradedProduct(
        Institution l_institution,
        String l_strProductCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMutualFundTradedProduct( Institution, String)-->ForMock";
        log.entering(STR_METHOD_NAME);


        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getMutualFundTradedProduct",
            new Class[] {Institution.class, String.class},
            new Object[]{l_institution, l_strProductCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "getMutualFundTradedProduct",
            new Class[] {Institution.class, String.class}))
        {
            //2）MockFor --〉 asWEB3BaseException
            log.exiting(STR_METHOD_NAME);

            //3)MockFor --〉 asObject
            return (MutualFundTradedProduct)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundTradedProduct",
                new Class[] {Institution.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getMutualFundTradedProduct(l_institution, l_strProductCode);
    }
    
    /**
     * (validate銘柄条件(Mock))<BR>
     * （validateProductCondのオーバーロード）<BR>
     * @@param l_institution - 証券会社
     * @@param l_productCondSpec - 銘柄条件内容
     */
    public void validateProductCond(
        Institution l_institution,
        WEB3MutualFundProductCondSpec l_productCondSpec)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCond( Institution, validateProductCond)-->ForMock";
        log.entering(STR_METHOD_NAME);


        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "validateProductCond",
            new Class[] {Institution.class, WEB3MutualFundProductCondSpec.class},
            new Object[]{l_institution, l_productCondSpec});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProductManager",
            "validateProductCond",
            new Class[] {Institution.class, WEB3MutualFundProductCondSpec.class}))
        {
            
            log.exiting(STR_METHOD_NAME);
            
            // 2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager",
                    "validateProductCond",
                    new Class[] {Institution.class, WEB3MutualFundProductCondSpec.class}).asWEB3BaseException();
            //3)MockFor --〉 asObject
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "validateProductCond",
                new Class[] {Institution.class, WEB3MutualFundProductCondSpec.class}).asObject();
            return;
        }

        log.exiting(STR_METHOD_NAME);
        super.validateProductCond(l_institution, l_productCondSpec);
    }
    
    public MutualFundProduct getMutualFundProduct(Institution l_institution, String l_strProductCode)
            throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct", 
                new Class[]
                {Institution.class, 
                 String.class }, 
                new Object[]
                {l_institution, 
                 l_strProductCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct", new Class[]
                { Institution.class, String.class }))
        {
            log.debug("webbroker3.mf.WEB3MutualFundProductManagerForMock.getMutualFundProduct()");

            return (MutualFundProduct) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getMutualFundProduct", new Class[]
                    { Institution.class, String.class }).asObject();
        }
        return super.getMutualFundProduct(l_institution, l_strProductCode);
    }
    
    public List getFixedBuyPossibleProductList(String l_strInstitutionCode, String[] l_strProductCodes,
            List l_lisCategoryList) throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFundProductManager",
                "getFixedBuyPossibleProductList", new Class[]
                {String.class, String[].class, List.class}, new Object[]
                {l_strInstitutionCode, l_strProductCodes, l_lisCategoryList});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFundProductManager",
                "getFixedBuyPossibleProductList", new Class[]
                {String.class, String[].class, List.class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFundProductManagerForMock.getFixedBuyPossibleProductList()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFundProductManager",
                    "getFixedBuyPossibleProductList", new Class[]
                    {String.class, String[].class, List.class}).asWEB3BaseException();
            return (List) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getFixedBuyPossibleProductList", new Class[]
                    {String.class, String[].class, List.class}).asObject();
        }
        return super.getFixedBuyPossibleProductList(l_strInstitutionCode, l_strProductCodes, l_lisCategoryList);
    }
    
    public String getUpMutualFundProductCategoryCode(String l_strCategoryCode, String l_strInstitutionCode)
            throws WEB3BaseException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mf.WEB3MutualFundProductManager",
                "getUpMutualFundProductCategoryCode", new Class[]
                {String.class, String.class}, new Object[]
                {l_strCategoryCode, l_strInstitutionCode});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed("webbroker3.mf.WEB3MutualFundProductManager",
                "getUpMutualFundProductCategoryCode", new Class[]
                {String.class, String.class}))
        {
            log.debug("webbroker3.mf.WEB3MutualFundProductManagerForMock.getUpMutualFundProductCategoryCode()");
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mf.WEB3MutualFundProductManager",
                    "getUpMutualFundProductCategoryCode", new Class[]
                    {String.class, String.class}).asWEB3BaseException();
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundProductManager", "getUpMutualFundProductCategoryCode", new Class[]
                    {String.class, String.class}).asObject();
        }
        return super.getUpMutualFundProductCategoryCode(l_strCategoryCode, l_strInstitutionCode);
    }

}
@
