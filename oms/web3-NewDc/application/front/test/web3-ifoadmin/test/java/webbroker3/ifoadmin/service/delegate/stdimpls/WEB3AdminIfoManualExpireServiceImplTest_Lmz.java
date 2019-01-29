head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualExpireServiceImplTest_Lmz.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifoadmin.message.WEB3AdminIfoLapseTargetOrderCondition;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoManualExpireServiceImplTest_Lmz extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    		WEB3AdminIfoManualExpireServiceImplTest_Lmz.class);

    public WEB3AdminIfoManualExpireServiceImplTest_Lmz(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public class WEB3AdminIfoManualExpireServiceImplForTest extends WEB3AdminIfoManualExpireServiceImpl
    {
        protected WEB3AdminIfoManualLapseConfirmResponse validateManualExpire(WEB3AdminIfoManualLapseConfirmRequest l_request) throws WEB3BaseException
        {
            return super.validateManualExpire(l_request);
        }

        
        protected void validateManualExpirePossibility(
            WEB3Administrator l_administrator, 
            WEB3GenRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "validateManualExpirePossibility(WEB3Administrator, WEB3GenRequest)";
            log.entering(STR_METHOD_NAME);

            if ("222222".equals(((WEB3AdminIfoManualLapseConfirmRequest)l_request).ifoLapseTargetOrderCondition.accountCode))
            {
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "パラメータ値不正。");
            }
        }
    }
    
    public void test_validateManualExpire_0001()
    {
        final String STR_METHOD_NAME = ".test_validateManualExpire_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();

        WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition =
            new WEB3AdminIfoLapseTargetOrderCondition();

        l_ifoLapseTargetOrderCondition.branchCode = new String[] {"381"};
        l_ifoLapseTargetOrderCondition.fuOpDiv = "1";
        l_ifoLapseTargetOrderCondition.targetProductCode = "0005";
        l_ifoLapseTargetOrderCondition.delivaryMonth = "200701";
//        l_ifoLapseTargetOrderCondition.strikePrice = "1";
//        l_ifoLapseTargetOrderCondition.opProductType = "P";
        l_ifoLapseTargetOrderCondition.tradingTypeList = new String[] {"601"};
        l_ifoLapseTargetOrderCondition.accountCode = "251224";

        l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;

        AdministratorParams l_administratorRow = this.getAdministratorRow();
        InstitutionParams l_institutionRow = TestDBUtility.getInstitutionRow();
        DaemonTriggerParams l_daemonTriggerParams = this.getDaemonTriggerRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        ProductParams l_productParams = this.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountCode("251224");

        IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllRow = this.getHostFotypeOrderAll();

        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorRow);
            TestDBUtility.insertWithDel(l_institutionRow);
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllRow);

            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0304", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3IfoProductImpl l_WEB3IfoProductImpl = null;

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoProduct",
            new Class[] {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                double.class, String.class, String.class},
                l_WEB3IfoProductImpl);
        try
        {
            WEB3AdminIfoManualExpireServiceImplForTest l_WEB3AdminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImplForTest();
            WEB3AdminIfoManualLapseConfirmResponse l_response =
                l_WEB3AdminIfoManualExpireServiceImpl.validateManualExpire(l_request);

            assertEquals("1", l_response.lapseTargetOrderNumber);
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_response.currentTime, "yyyyMMdd"));
            assertNull(l_response.productName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateManualExpire_0002()
    {
        final String STR_METHOD_NAME = ".test_validateManualExpire_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();

        WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition =
            new WEB3AdminIfoLapseTargetOrderCondition();

        l_ifoLapseTargetOrderCondition.branchCode = new String[] {"381"};
        l_ifoLapseTargetOrderCondition.fuOpDiv = "1";
        l_ifoLapseTargetOrderCondition.targetProductCode = "0005";
        l_ifoLapseTargetOrderCondition.delivaryMonth = "200701";
//        l_ifoLapseTargetOrderCondition.strikePrice = "1";
//        l_ifoLapseTargetOrderCondition.opProductType = "P";
        l_ifoLapseTargetOrderCondition.tradingTypeList = new String[] {"601"};
        l_ifoLapseTargetOrderCondition.accountCode = "251224";

        l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;

        AdministratorParams l_administratorRow = this.getAdministratorRow();
        InstitutionParams l_institutionRow = TestDBUtility.getInstitutionRow();
        DaemonTriggerParams l_daemonTriggerParams = this.getDaemonTriggerRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setSplitType("000");
        l_ifoProductParams.setStrikePrice(0D);
        l_ifoProductParams.setMonthOfDelivery("200701");

        ProductParams l_productParams = this.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountCode("251224");

        IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllRow = this.getHostFotypeOrderAll();

        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorRow);
            TestDBUtility.insertWithDel(l_institutionRow);
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllRow);

            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0304", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3IfoProductImpl l_WEB3IfoProductImpl = null;

        try
        {
            l_WEB3IfoProductImpl = new WEB3IfoProductImpl(3304148080000L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoProduct",
            new Class[] {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                double.class, String.class, String.class},
                l_WEB3IfoProductImpl);
        try
        {
            WEB3AdminIfoManualExpireServiceImplForTest l_WEB3AdminIfoManualExpireServiceImpl = new WEB3AdminIfoManualExpireServiceImplForTest();
            WEB3AdminIfoManualLapseConfirmResponse l_response = l_WEB3AdminIfoManualExpireServiceImpl.validateManualExpire(l_request);

            assertEquals("1", l_response.lapseTargetOrderNumber);
            assertEquals(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_response.currentTime, "yyyyMMdd"));
            assertEquals("シンセンテルス", l_response.productName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateManualExpire_0003()
    {
        final String STR_METHOD_NAME = ".test_validateManualExpire_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();
        l_request.ifoLapseTargetOrderCondition = null;

        try
        {
            WEB3AdminIfoManualExpireServiceImplForTest l_WEB3AdminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImplForTest();

            l_WEB3AdminIfoManualExpireServiceImpl.validateManualExpire(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02420, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateManualExpire_0004()
    {
        final String STR_METHOD_NAME = ".test_validateManualExpire_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();

        WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition =
            new WEB3AdminIfoLapseTargetOrderCondition();

        l_ifoLapseTargetOrderCondition.branchCode = new String[] {"381"};
        l_ifoLapseTargetOrderCondition.fuOpDiv = "1";
        l_ifoLapseTargetOrderCondition.targetProductCode = "0005";
        l_ifoLapseTargetOrderCondition.delivaryMonth = "200701";
//        l_ifoLapseTargetOrderCondition.strikePrice = "1";
//        l_ifoLapseTargetOrderCondition.opProductType = "P";
        l_ifoLapseTargetOrderCondition.tradingTypeList = new String[] {"601"};
        l_ifoLapseTargetOrderCondition.accountCode = "222222";

        l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;

        AdministratorParams l_administratorRow = this.getAdministratorRow();

        try
        {
            TestDBUtility.insertWithDel(l_administratorRow);

            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0304", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3AdminIfoManualExpireServiceImplForTest l_WEB3AdminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImplForTest();

            l_WEB3AdminIfoManualExpireServiceImpl.validateManualExpire(l_request);

            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_validateManualExpire_0005()
    {
        final String STR_METHOD_NAME = ".test_validateManualExpire_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoManualLapseConfirmRequest l_request = new WEB3AdminIfoManualLapseConfirmRequest();

        WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition =
            new WEB3AdminIfoLapseTargetOrderCondition();

        l_ifoLapseTargetOrderCondition.branchCode = new String[] {"381"};
        l_ifoLapseTargetOrderCondition.fuOpDiv = "1";
        l_ifoLapseTargetOrderCondition.targetProductCode = "0005";
        l_ifoLapseTargetOrderCondition.delivaryMonth = "200701";
//        l_ifoLapseTargetOrderCondition.strikePrice = "1";
//        l_ifoLapseTargetOrderCondition.opProductType = "P";
        l_ifoLapseTargetOrderCondition.tradingTypeList = new String[] {"601"};
        l_ifoLapseTargetOrderCondition.accountCode = "251224";

        l_request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;

        AdministratorParams l_administratorRow = this.getAdministratorRow();
        InstitutionParams l_institutionRow = TestDBUtility.getInstitutionRow();
        DaemonTriggerParams l_daemonTriggerParams = this.getDaemonTriggerRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        ProductParams l_productParams = this.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountCode("251224");

        IfoOrderUnitParams l_ifoOrderUnitParams = this.getIfoOrderUnitRow();

        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_administratorRow);
            TestDBUtility.insertWithDel(l_institutionRow);
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorRow);

            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            WEB3AdministratorForMock.mockValidateAuthority(l_administratorSet, "C0304", true, true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3IfoProductImpl l_WEB3IfoProductImpl = null;

        try
        {
            l_WEB3IfoProductImpl = new WEB3IfoProductImpl(3304148080000L);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoProduct",
            new Class[] {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                double.class, String.class, String.class},
                l_WEB3IfoProductImpl);
        try
        {
            WEB3AdminIfoManualExpireServiceImplForTest l_WEB3AdminIfoManualExpireServiceImpl =
                new WEB3AdminIfoManualExpireServiceImplForTest();

               l_WEB3AdminIfoManualExpireServiceImpl.validateManualExpire(l_request);

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 注文単位テーブル(ifo_order_unit)
     */
    public HostFotypeOrderAllParams getHostFotypeOrderAll()
    {
        HostFotypeOrderAllParams l_hostFotypeOrderAllRow = new HostFotypeOrderAllParams();

        l_hostFotypeOrderAllRow.setAccountId(333812512246L);
        l_hostFotypeOrderAllRow.setInstitutionCode("0D");
        l_hostFotypeOrderAllRow.setBranchCode("381");
        l_hostFotypeOrderAllRow.setAccountCode("251224");
        l_hostFotypeOrderAllRow.setCancelDiv("1");
        l_hostFotypeOrderAllRow.setCorpCode("1");
        l_hostFotypeOrderAllRow.setAllOrderChangeDiv("1");
        l_hostFotypeOrderAllRow.setStatus("0");
        l_hostFotypeOrderAllRow.setOrderRequestNumber("000003006");

        return l_hostFotypeOrderAllRow;
    }

    /**
     * 注文単位テーブル(ifo_order_unit)
     */
    public IfoOrderUnitParams getIfoOrderUnitRow()
    {
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();

        l_ifoOrderUnitParams.setOrderUnitId(1001);
        l_ifoOrderUnitParams.setAccountId(333812512246L);
        l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
        l_ifoOrderUnitParams.setBranchId(33381);
        l_ifoOrderUnitParams.setTraderId(null);
        l_ifoOrderUnitParams.setOrderId(1001);
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
        l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderUnitParams.setFutureOptionDiv("1");
        l_ifoOrderUnitParams.setMarketId(1002);
        l_ifoOrderUnitParams.setQuantity(100);
        l_ifoOrderUnitParams.setLimitPrice(0);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_ifoOrderUnitParams.setOrderConditionType("0");
        l_ifoOrderUnitParams.setOrderCondOperator(null);
        l_ifoOrderUnitParams.setStopPriceType(null);
        l_ifoOrderUnitParams.setStopOrderPrice(null);
        l_ifoOrderUnitParams.setWLimitPrice(null);
        l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
        l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_ifoOrderUnitParams.setBizDate("20040101");
        l_ifoOrderUnitParams.setProductId(3304148080000L);
        l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderRequestNumber("000003006");

        return l_ifoOrderUnitParams;
    }

    /**
     * 銘柄Rowを作成.<BR>
     */
    public ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(3304148080000L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }

    /**
     * 先物OP銘柄テーブル (ifo_product)
     */
    public IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(3304148080000L);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setProductCode("N8080");
        l_ifoProductParams.setStandardName("シンセンテルス");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }

    /**
     * 管理者テーブルRowを作成.<BR>
     */
    public AdministratorParams getAdministratorRow()
    {
        AdministratorParams l_administratorParams = new AdministratorParams();

        l_administratorParams.setAdministratorId(110120119L);
        l_administratorParams.setAdministratorCode("123456789");
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setInstitutionId(33);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setLoginId(3338111123L);
        l_administratorParams.setPermissionLevel("123");

        return l_administratorParams;
    }

    public DaemonTriggerParams getDaemonTriggerRow()
    {
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();

        l_daemonTriggerParams.setTriggerType("W");
        l_daemonTriggerParams.setThreadNo(1);
        l_daemonTriggerParams.setOrderRequestNumber("11");
        l_daemonTriggerParams.setRangeFrom(0L);
        l_daemonTriggerParams.setRangeTo(999999999999999999L);
        l_daemonTriggerParams.setTriggerStatus("1");
        l_daemonTriggerParams.setTriggerDate(WEB3DateUtility.getDate("200701010101","yyyyMMddHH24mm"));

        return l_daemonTriggerParams;
    }

}
@
