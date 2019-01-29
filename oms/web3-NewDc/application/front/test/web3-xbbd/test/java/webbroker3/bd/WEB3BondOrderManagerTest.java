head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondOrderManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3BondOrderManagerTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/08 徐宏偉 (中訊) 新規作成
Revesion History : 2007/07/05  【WEB3】【CITIフロント導入（債券）】案件取消，注掉代碼
*/
package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondOrderAcceptActionParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3PaymentDateDetDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3BondOrderManagerTest extends TestBaseForMock
{

    public WEB3BondOrderManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderManagerTest.class);

    WEB3BondOrderManager l_bondOrdermanager = new WEB3BondOrderManager();
    WEB3BondOrderManagerForTest l_bondOrdermanagerFortest = new WEB3BondOrderManagerForTest();

    public void testTest()
    {

    }

    /**
     * 補助口座 == null<BR>
     * 債券銘柄 == null<BR>
     */
    public void test_validateCorporationAccount_0001()
    {
        final String STR_METHOD_NAME =
            " test_validateCorporationAccount_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_bondOrdermanager.validateCorporationAccount(null, null);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 補助口座 != null<BR>
     * 債券銘柄 != null<BR>
     * MainAccountParams sex = 0
     */
    public void test_validateCorporationAccount_0002()
    {
        final String STR_METHOD_NAME =
            " test_validateCorporationAccount_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setBondType(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountRow);
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateCorporationAccount(l_subAccount, l_bondProduct);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02884,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 補助口座 != null<BR>
     * 債券銘柄 != null<BR>
     * MainAccountParams sex = 0
     */
    public void test_validateCorporationAccount_0004()
    {
        final String STR_METHOD_NAME =
            " test_validateCorporationAccount_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setBondType(BondTypeEnum.CB);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountRow);
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateCorporationAccount(l_subAccount, l_bondProduct);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 補助口座 != null<BR>
     * 債券銘柄 != null<BR>
     * MainAccountParams sex = 1
     */
    public void test_validateCorporationAccount_0003()
    {
        final String STR_METHOD_NAME =
            " test_validateCorporationAccount_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountRow);
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateCorporationAccount(l_subAccount, l_bondProduct);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券銘柄 != null<BR>
     */
    public void test_createBondDomesticExecutionDateInfo_0001()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            WEB3BondExecuteDateInfo l_bondExecuteDateInfo =
                l_bondOrdermanager.createBondDomesticExecutionDateInfo(l_bondProduct);
            assertEquals(WEB3DateUtility.getDate("20070731", "yyyyMMdd"), l_bondExecuteDateInfo.getBizDate());
            assertEquals(WEB3DateUtility.getDate("20070731", "yyyyMMdd"),
                l_bondExecuteDateInfo.getExecuteDate());
            assertEquals(WEB3DateUtility.getDate("20070730", "yyyyMMdd"),
                l_bondExecuteDateInfo.getDeliveryDate());
            assertEquals(WEB3DateUtility.getDate("20070730", "yyyyMMdd"),
                l_bondExecuteDateInfo.getPaymentDate());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券銘柄 == null<BR>
     */
    public void test_createBondDomesticExecutionDateInfo_0002()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_bondOrdermanager.createBondDomesticExecutionDateInfo(null);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券銘柄 == null<BR>
     */
    public void test_validateAccountHandlingPossibleProductBondDomestic_0001()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_bondOrdermanager.validateAccountHandlingPossibleProductBondDomestic(null, "1");
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券銘柄 != null<BR>
     */
    public void test_validateAccountHandlingPossibleProductBondDomestic_0002()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setTradeHandleDiv("2");

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(-1));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateAccountHandlingPossibleProductBondDomestic(l_bondProduct, "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02661,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券銘柄 != null<BR>
     */
    public void test_validateAccountHandlingPossibleProductBondDomestic_0003()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setTradeHandleDiv("2");

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(1));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("1");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateAccountHandlingPossibleProductBondDomestic(l_bondProduct, "1");
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02612,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券銘柄 != null<BR>
     */
    public void test_validateAccountHandlingPossibleProductBondDomestic_0004()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setTradeHandleDiv("2");

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(1));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("2");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateAccountHandlingPossibleProductBondDomestic(l_bondProduct, "2");
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02613,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券銘柄 != null<BR>
     */
    public void test_validateAccountHandlingPossibleProductBondDomestic_0005()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setTradeHandleDiv("2");

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(1));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("1");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateAccountHandlingPossibleProductBondDomestic(l_bondProduct, "3");
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02614,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }


    /**
     * 債券銘柄 != null<BR>
     */
    public void test_validateAccountHandlingPossibleProductBondDomestic_0006()
    {
        final String STR_METHOD_NAME =
            " test_createBondDomesticExecutionDateInfo_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setTradeHandleDiv("2");

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(1));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("1");
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateAccountHandlingPossibleProductBondDomestic(l_bondProduct, "4");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 補助口座 != null<BR>
     * 債券銘柄 != null<BR>
     * MainAccountParams sex = 1
     */
    public void test_validateBondDomesticApplyOrder_0001()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                WEB3ChannelDef.CALL_CENTER);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setProductId(3304140763000L);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setBranchCode("---");
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(6.6D);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(1001L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv(
                WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(3304140763000L);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeEndDate(l_gentradeBizDate.roll(2));
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20070730", "yyyyMMdd"));
            l_bondProductParams.setTradeType("3");
            l_bondProductParams.setMinFaceAmount(1.1D);
            l_bondProductParams.setTradeUnit(1.1D);
            TestDBUtility.insertWithDel(l_bondProductParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountRow);
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);

            l_bondOrdermanager.validateBondDomesticApplyOrder(l_subAccount, l_bondProduct, 1.1D);

            String l_strMethodReturnValue = (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class}).asObject();
            assertEquals("2", l_strMethodReturnValue);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 補助口座 == null<BR>
     * 債券銘柄 == null<BR>
     */
    public void test_validateBondDomesticApplyOrder_0002()
    {
        final String STR_METHOD_NAME =
            " test_validateBondDomesticApplyOrder_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_bondOrdermanager.validateBondDomesticApplyOrder(null, null, 1.1D);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 
     */
    public void test_calcBondDomesticOrderAmountTotal_0001()
    {
        final String STR_METHOD_NAME =
            " test_calcBondDomesticOrderAmountTotal_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_bondOrdermanager.calcBondDomesticOrderAmountTotal(1L, null, null);
            fail();
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 債券注文受付履歴テーブル<BR>
     * 債券注文単位テーブル<BR>
     */
    public void test_calcBondDomesticOrderAmountTotal_0002()
    {
        final String STR_METHOD_NAME =
            " test_calcBondDomesticOrderAmountTotal_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            TestDBUtility.insertWithDel(l_branchParams);

            double l_dbl =
                l_bondOrdermanager.calcBondDomesticOrderAmountTotal(1001L, "0D", "123");

            BigDecimal l_bdBondDomesticOrderAmountTotal = new BigDecimal(l_dbl);
            BigDecimal l_bdEquals = new BigDecimal(1.1D);
            assertEquals(l_bdEquals, l_bdBondDomesticOrderAmountTotal);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * 
     */
    public void test_calcBondDomesticOrderAmountTotal_0003()
    {
        final String STR_METHOD_NAME =
            " test_calcBondDomesticOrderAmountTotal_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(-1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            double l_dbl =
                l_bondOrdermanager.calcBondDomesticOrderAmountTotal(1001L, "0D", "123");

            BigDecimal l_bdBondDomesticOrderAmountTotal = new BigDecimal(l_dbl);
            BigDecimal l_bdEquals = new BigDecimal(1.1D);
            assertEquals(l_bdEquals, l_bdBondDomesticOrderAmountTotal);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     *
     */
    public void test_calcBondDomesticOrderAmountTotal_0004()
    {
        final String STR_METHOD_NAME =
            " test_calcBondDomesticOrderAmountTotal_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            l_bondOrderAcceptActionParams.setBranchCode("---");
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_bondOrderUnitParams.setQuantity(2.2D);
            TestDBUtility.insertWithDel(l_branchParams);

            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(-1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            double l_dbl =
                l_bondOrdermanager.calcBondDomesticOrderAmountTotal(1001L, "0D", "---");

            BigDecimal l_bdBondDomesticOrderAmountTotal = new BigDecimal(l_dbl);
            BigDecimal l_bdEquals = new BigDecimal(0D);
            assertEquals(l_bdEquals, l_bdBondDomesticOrderAmountTotal);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     *
     */
    public void test_calcBondDomesticOrderAmountTotal_0005()
    {
        final String STR_METHOD_NAME =
            " test_calcBondDomesticOrderAmountTotal_0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_bondOrderUnitParams.setQuantity(2.2D);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            double l_dbl =
                l_bondOrdermanager.calcBondDomesticOrderAmountTotal(1001L, "0D", "---");

            BigDecimal l_bdBondDomesticOrderAmountTotal = new BigDecimal(l_dbl);
            BigDecimal l_bdEquals = new BigDecimal(3.3D);
            assertEquals(l_bdEquals, l_bdBondDomesticOrderAmountTotal);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     *
     */
    public void test_calcBondDomesticOrderAmountTotal_0006()
    {
        final String STR_METHOD_NAME =
            " test_calcBondDomesticOrderAmountTotal_0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderAcceptActionParams l_bondOrderAcceptActionParams =
                this.getBondOrderAcceptActionRow();
//            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            l_bondOrderUnitParams.setOrderUnitId(123457L);
            l_branchParams.setBranchCode("123");
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_bondOrderUnitParams.setQuantity(2.2D);
            TestDBUtility.insertWithDel(l_branchParams);

            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(1));
            l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_bondOrderUnitParams.setLastUpdatedTimestamp(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            double l_dbl =
                l_bondOrdermanager.calcBondDomesticOrderAmountTotal(1001L, "0D", "123");

            BigDecimal l_bdBondDomesticOrderAmountTotal = new BigDecimal(l_dbl);
            BigDecimal l_bdEquals = new BigDecimal(3.3D);
            assertEquals(l_bdEquals, l_bdBondDomesticOrderAmountTotal);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    public BondOrderAcceptActionParams getBondOrderAcceptActionRow()
    {
        BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();

        l_bondOrderAcceptActionParams.setProductId(1001L);
        l_bondOrderAcceptActionParams.setBranchCode("123");
        l_bondOrderAcceptActionParams.setInstitutionCode("0D");
        l_bondOrderAcceptActionParams.setTotalOrderAmount(1.1D);
        l_bondOrderAcceptActionParams.setOrderAcceptDate(GtlUtils.getSystemTimestamp());
        l_bondOrderAcceptActionParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_bondOrderAcceptActionParams;
    }

    public class WEB3BondOrderManagerForTest extends WEB3BondOrderManager
    {
        public WEB3BondExecuteDateInfo createBondExecutionDateInfo(Date l_datBizDate,
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge,
            WEB3BondProduct l_bondProduct, String l_strSettleDiv, Branch l_branch) throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                " createBondExecutionDateInfo(Date, WEB3BondOrderTypeJudge, WEB3BondProduct, String, Branch) " ;
            log.entering(STR_METHOD_NAME);

            assertEquals("20080801", WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"));
            assertEquals(3304140763000L, l_bondProduct.getProductId());
            assertEquals("1", l_strSettleDiv);
            assertEquals(123, l_branch.getBranchId());
            log.exiting(STR_METHOD_NAME);

            if (l_bondProduct == null)
            {
                log.debug("パラメータ値不正。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータ値不正。");
            }
            WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();

            //１）約定日を取得する。
            //[引数.債券銘柄.get約定日()に渡す引数]
            //発注日：　@引数.発注日
            Date l_datExecDate = l_bondProduct.getExecDate(l_datBizDate);
            l_bondExecuteDateInfo.setExecuteDate(l_datExecDate);

            //２）受渡日を取得する。
            //[引数.債券銘柄.get受渡日()に渡す引数]
            //約定日：　@取得した約定日
            //債券注文種別判定：　@引数.債券注文種別判定
            Date l_datDeliveryDate =
                l_bondProduct.getDeliveryDate(l_datExecDate, l_bondOrderTypeJudge);
            l_bondExecuteDateInfo.setDeliveryDate(l_datDeliveryDate);

            //３）入金日を取得する。
            // [引数.債券銘柄.get入金日()に渡す引数]
            //  約定日：　@取得した約定日
            //  債券注文種別判定：　@引数.債券注文種別判定
            //  決済区分：　@引数.決済区分
            //  部店：　@引数.部店
            Date l_datPaymentDate =
                l_bondProduct.getPaymentDate(
                    l_datExecDate, l_bondOrderTypeJudge, l_strSettleDiv, l_branch);
            l_bondExecuteDateInfo.setPaymentDate(l_datPaymentDate);

            //４）引数.債券銘柄.is外国債券()の戻り値 == true の場合
            if (l_bondProduct.isForeignBond())
            {
                //４－１）現地発注日を取得する。
                //[引数.債券銘柄.get現地発注日()に渡す引数]
                //発注日：　@引数.発注日
                Date l_datForeignBizDate = l_bondProduct.getForeignBizDate(l_datBizDate);
                l_bondExecuteDateInfo.setForeignBizDate(l_datForeignBizDate);

                //４－２）現地約定日を取得する。
                //[引数.債券銘柄.get現地約定日()に渡す引数]
                //現地発注日：　@取得した現地発注日
                Date l_datForeignExecDate =
                    l_bondProduct.getForeignExecDate(l_datForeignBizDate);
                l_bondExecuteDateInfo.setForeignExecuteDate(l_datForeignExecDate);

                //４－３）現地受渡日を取得する。
                //[引数.債券銘柄.get現地受渡日()に渡す引数]
                //現地約定日：　@取得した現地約定日
                //債券注文種別判定：　@引数.債券注文種別判定
                Date LdatForeignDeliveryDate =
                    l_bondProduct.getForeignDeliveryDate(
                        l_datForeignExecDate, l_bondOrderTypeJudge);
                l_bondExecuteDateInfo.setForeignDeliveryDate(LdatForeignDeliveryDate);
            }

            //５）債券約定日情報を生成し、取得した
            //日付情報と引数の発注日をそれぞれセットして返却する。
            l_bondExecuteDateInfo.setBizDate(l_datBizDate);
            
            log.exiting(STR_METHOD_NAME);
            return l_bondExecuteDateInfo;
        }
        
        public WEB3BondExecuteDateInfo resetBondExecutionDateInfo(
            Date l_datExecutionDate,
            Date l_datForeignExecutionDate,
            Date l_datDeliveryDate,
            Date l_datForeignDeliveryDate,
            Date l_datPaymentDate,
            WEB3BondExecuteDateInfo l_bondExecutionDateInfo,
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge,
            WEB3BondProduct l_bondProduct,
            String l_strSettleDiv,
            Branch l_branch) throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                " resetBondExecutionDateInfo(Date, Date, Date, Date, Date, "
                + "WEB3BondExecuteDateInfo, WEB3BondOrderTypeJudge, WEB3BondProduct, String, Branch) ";
            log.entering(STR_METHOD_NAME);

            assertEquals("20080801", WEB3DateUtility.formatDate(l_datExecutionDate, "yyyyMMdd"));
            assertEquals("20080801", WEB3DateUtility.formatDate(l_datForeignExecutionDate, "yyyyMMdd"));
            assertEquals("20080801", WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"));
            assertEquals("20080801", WEB3DateUtility.formatDate(l_datForeignDeliveryDate, "yyyyMMdd"));
            assertEquals("20080801", WEB3DateUtility.formatDate(l_datPaymentDate, "yyyyMMdd"));
            assertEquals("20080801", WEB3DateUtility.formatDate(l_datPaymentDate, "yyyyMMdd"));
            assertEquals(3304140763000L, l_bondProduct.getProductId());
            assertEquals("1", l_strSettleDiv);
            assertEquals(123, l_branch.getBranchId());

            //１）引数.約定日　@!= nullの場合、
            //引数.債券約定日情報.約定日＝引数.約定日
            if (l_datExecutionDate != null)
            {
                l_bondExecutionDateInfo.setExecuteDate(l_datExecutionDate);
            }

            //２）引数.現地約定日　@!= nullの場合、
            //　@引数.債券約定日情報.現地約定日＝引数.現地約定日
            if (l_datForeignExecutionDate != null)
            {
                l_bondExecutionDateInfo.setForeignExecuteDate(l_datForeignExecutionDate);
            }

            //３）引数.受渡日　@!= nullの場合、
            //引数.債券約定日情報.受渡日＝引数.受渡日
            if (l_datDeliveryDate != null)
            {
                l_bondExecutionDateInfo.setDeliveryDate(l_datDeliveryDate);
            }

            //４）引数.約定日 != null かつ、引数.受渡日 == nullの場合、
            //　@引数.債券約定日情報.受渡日＝引数.債券銘柄.get受渡日()
            //　@[引数]
            //　@約定日：　@引数.債券約定日情報.約定日
            //　@債券注文種別判定：　@引数.債券注文種別判定
            else if (l_datExecutionDate != null)
            {
                l_bondExecutionDateInfo.setDeliveryDate(l_bondProduct.getDeliveryDate(
                    l_datExecutionDate, l_bondOrderTypeJudge));
            }

            //５）引数.現地受渡日　@!= nullの場合、
            //引数.債券約定日情報.現地受渡日＝引数.現地受渡日
            if (l_datForeignDeliveryDate != null)
            {
                l_bondExecutionDateInfo.setForeignDeliveryDate(l_datForeignDeliveryDate);
            }

            //６）引数.入金日　@!= nullの場合、
            //　@引数.債券約定日情報.入金日＝引数.入金日
            if (l_datPaymentDate != null)
            {
                l_bondExecutionDateInfo.setPaymentDate(l_datPaymentDate);
            }
            //７）引数.入金日　@== nullの場合、
            else
            {
                //７－１）債券部店別条件を生成する。
                //[引数]
                //部店ID：引数.部店.部店ID
                WEB3BondBranchCondition l_branchCondition =
                    new WEB3BondBranchCondition(l_branch.getBranchId());

                //７－２）債券部店別条件.get入金日設定区分＝＝'約定日基準' かつ、
                //引数.債券注文種別判定.is応募＝＝true　@の場合、
                if (WEB3PaymentDateDetDivDef.EXECUTE_DATE_BASE.equals(
                    l_branchCondition.getPaymentDateSetDiv()) &&
                        l_bondOrderTypeJudge.isRecruitOrder())
                {
                    //引数.債券約定日情報.入金日＝引数.債券銘柄.get入金日
                    //[引数]
                    //約定日：引数.債券約定日情報.約定日
                    //債券注文種別判定：引数.債券注文種別判定
                    //決済区分：引数.決済区分
                    //部店：引数.部店
                    l_bondExecutionDateInfo.setPaymentDate(l_bondProduct.getPaymentDate(
                        l_bondExecutionDateInfo.getExecuteDate(),
                        l_bondOrderTypeJudge,
                        l_strSettleDiv,
                        l_branch));
                }
                //７－３）上記以外の場合、
                //引数.債券約定日情報.入金日＝引数.債券約定日情報.受渡日
                else
                {
                    l_bondExecutionDateInfo.setPaymentDate(l_bondExecutionDateInfo.getDeliveryDate());
                }

            }

            //８）債券約定日情報を返す。
            log.exiting(STR_METHOD_NAME);
            return l_bondExecutionDateInfo;

        }
    }
}

@
