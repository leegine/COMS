head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondOrderManagerReusableValidationsCheckTest_wubo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondOrderManagerReusableValidationsCheckTest_wubo extends TestBaseForMock
{
    public WEB3BondOrderManagerReusableValidationsCheckTest_wubo(String arg0)
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
        WEB3LogUtility.getInstance(WEB3BondOrderManagerReusableValidationsCheckTest_wubo.class);

    /**
     * <BR>
     */
    public void test_validateTransferedPossibleDays_0001()
    {
        final String STR_METHOD_NAME =
            " test_validateTransferedPossibleDays_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderExecStatus("0");
            l_bondOrderUnitParams.setLockStatus("1");
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            l_bondOrderUnitParams.setBizDate("20070701");
            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(l_bondOrderUnitParams);
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateTransferedPossibleDays(l_bondOrderUnit);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * <BR>
     */
    public void test_validateTransferedPossibleDays_0002()
    {
        final String STR_METHOD_NAME =
            " test_validateTransferedPossibleDays_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderExecStatus("0");
            l_bondOrderUnitParams.setLockStatus("1");
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setBondType(BondTypeEnum.CB);
            l_bondOrderUnitParams.setBizDate("20070701");
            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(l_bondOrderUnitParams);
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateTransferedPossibleDays(l_bondOrderUnit);
            fail();
        }
        catch (WEB3BusinessLayerException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00155,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * <BR>
     */
    public void test_validateTransferedPossibleDays_0003()
    {
        final String STR_METHOD_NAME =
            " test_validateTransferedPossibleDays_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderExecStatus("0");
            l_bondOrderUnitParams.setLockStatus("1");
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setBondType(BondTypeEnum.CB);

            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setBizDate(
                WEB3DateUtility.formatDate(
                    GtlUtils.getSystemTimestamp(), "yyyyMMdd"));
            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(l_bondOrderUnitParams);
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateTransferedPossibleDays(l_bondOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * <BR>
     */
    public void test_validateTransferedPossibleDays_0004()
    {
        final String STR_METHOD_NAME =
            " test_validateTransferedPossibleDays_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);

            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
            l_bondOrderUnitParams.setOrderUnitId(123456L);
            l_bondOrderUnitParams.setBranchId(33381);
            l_bondOrderUnitParams.setProductId(1001L);
            l_bondOrderUnitParams.setQuantity(1.1D);
            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            l_bondOrderUnitParams.setOrderExecStatus("0");
            l_bondOrderUnitParams.setLockStatus("1");
            l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
            l_bondOrderUnitParams.setBizDate(
                    WEB3DateUtility.formatDate(
                        GtlUtils.getSystemTimestamp(), "yyyyMMdd"));
            Timestamp l_datSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_datSystemTimestamp);

            l_bondOrderUnitParams.setReceivedDateTime(l_gentradeBizDate.roll(2));
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);

            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(l_bondOrderUnitParams);
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateTransferedPossibleDays(l_bondOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    
    public void testValidateDomesticBondRecruitLimit_0001()
    {
        final String STR_METHOD_NAME =
            " testValidateDomesticBondRecruitLimit_0001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.bd.WEB3BondProductManager",
            "createAdminBondDomesticRecruitLimitInfo",
            new Class[]{ long.class, String.class, String.class },
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME));
        
        try
        {
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("1");
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            long l_lngBranchId = 33381L;
            double l_dblQuantity = 123D;
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(l_lngBranchId, l_bondProduct, l_dblQuantity);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            WEB3MockObjectParamsValue l_returnValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "createAdminBondDomesticRecruitLimitInfo",
                    new Class[]{ long.class, String.class, String.class });
            
            assertEquals(String.valueOf(3304148080000L), String.valueOf(l_returnValue.getFirstCalled()[0]));
            assertEquals("0D", l_returnValue.getFirstCalled()[1]);
            assertEquals("381", l_returnValue.getFirstCalled()[2]);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateDomesticBondRecruitLimit_0002()
    {
        final String STR_METHOD_NAME =
            " testValidateDomesticBondRecruitLimit_0002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.bd.WEB3BondProductManager",
            "createAdminBondDomesticRecruitLimitInfo",
            new Class[]{ long.class, String.class, String.class },
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME));
        
        try
        {
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("0");
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            long l_lngBranchId = 33381L;
            double l_dblQuantity = 123D;
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(l_lngBranchId, l_bondProduct, l_dblQuantity);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            WEB3MockObjectParamsValue l_returnValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "createAdminBondDomesticRecruitLimitInfo",
                    new Class[]{ long.class, String.class, String.class });
            
            assertEquals(String.valueOf(3304148080000L), String.valueOf(l_returnValue.getFirstCalled()[0]));
            assertEquals("0D", l_returnValue.getFirstCalled()[1]);
            assertEquals("---", l_returnValue.getFirstCalled()[2]);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateDomesticBondRecruitLimit_0003()
    {
        final String STR_METHOD_NAME =
            " testValidateDomesticBondRecruitLimit_0003()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3BondDomesticBranchRecruitLimitInfo l_bondDomesticBranchRecruitLimitInfo = new WEB3BondDomesticBranchRecruitLimitInfo();
        l_bondDomesticBranchRecruitLimitInfo.web3RecruitLimit = "100";
        l_bondDomesticBranchRecruitLimitInfo.orderAmountTotal = "110";

        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos = new WEB3BondDomesticBranchRecruitLimitInfo[1];
        l_bondDomesticBranchRecruitLimitInfos[0] = l_bondDomesticBranchRecruitLimitInfo;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.bd.WEB3BondProductManager",
            "createAdminBondDomesticRecruitLimitInfo",
            new Class[]{ long.class, String.class, String.class },
            l_bondDomesticBranchRecruitLimitInfos);
        
        try
        {
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("1");
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            long l_lngBranchId = 33381L;
            double l_dblQuantity = 0D;
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(l_lngBranchId, l_bondProduct, l_dblQuantity);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            WEB3MockObjectParamsValue l_returnValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "createAdminBondDomesticRecruitLimitInfo",
                    new Class[]{ long.class, String.class, String.class });
            
            assertEquals(String.valueOf(3304148080000L), String.valueOf(l_returnValue.getFirstCalled()[0]));
            assertEquals("0D", l_returnValue.getFirstCalled()[1]);
            assertEquals("381", l_returnValue.getFirstCalled()[2]);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02888, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateDomesticBondRecruitLimit_0004()
    {
        final String STR_METHOD_NAME =
            " testValidateDomesticBondRecruitLimit_0004()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3BondDomesticBranchRecruitLimitInfo l_bondDomesticBranchRecruitLimitInfo = new WEB3BondDomesticBranchRecruitLimitInfo();
        l_bondDomesticBranchRecruitLimitInfo.web3RecruitLimit = "100";
        l_bondDomesticBranchRecruitLimitInfo.orderAmountTotal = "110";

        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos = new WEB3BondDomesticBranchRecruitLimitInfo[1];
        l_bondDomesticBranchRecruitLimitInfos[0] = l_bondDomesticBranchRecruitLimitInfo;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.bd.WEB3BondProductManager",
            "createAdminBondDomesticRecruitLimitInfo",
            new Class[]{ long.class, String.class, String.class },
            l_bondDomesticBranchRecruitLimitInfos);
        
        try
        {
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("1");
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            long l_lngBranchId = 33381L;
            double l_dblQuantity = 123D;
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(l_lngBranchId, l_bondProduct, l_dblQuantity);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            WEB3MockObjectParamsValue l_returnValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "createAdminBondDomesticRecruitLimitInfo",
                    new Class[]{ long.class, String.class, String.class });
            
            assertEquals(String.valueOf(3304148080000L), String.valueOf(l_returnValue.getFirstCalled()[0]));
            assertEquals("0D", l_returnValue.getFirstCalled()[1]);
            assertEquals("381", l_returnValue.getFirstCalled()[2]);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02889, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateDomesticBondRecruitLimit_0005()
    {
        final String STR_METHOD_NAME =
            " testValidateDomesticBondRecruitLimit_0005()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos = new WEB3BondDomesticBranchRecruitLimitInfo[0];
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.bd.WEB3BondProductManager",
            "createAdminBondDomesticRecruitLimitInfo",
            new Class[]{ long.class, String.class, String.class },
            l_bondDomesticBranchRecruitLimitInfos);
        
        try
        {
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("1");
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            long l_lngBranchId = 33381L;
            double l_dblQuantity = 123D;
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.insertWithDel(l_productParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setBuyPrice(12.32D);
            l_bondProductParams.setProductId(3304148080000L);
            
            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(l_lngBranchId, l_bondProduct, l_dblQuantity);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            WEB3MockObjectParamsValue l_returnValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "createAdminBondDomesticRecruitLimitInfo",
                    new Class[]{ long.class, String.class, String.class });
            
            assertEquals(String.valueOf(3304148080000L), String.valueOf(l_returnValue.getFirstCalled()[0]));
            assertEquals("0D", l_returnValue.getFirstCalled()[1]);
            assertEquals("381", l_returnValue.getFirstCalled()[2]);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateDomesticBondRecruitLimit_0006()
    {
        final String STR_METHOD_NAME =
            " testValidateDomesticBondRecruitLimit_0006()";
        log.entering(STR_METHOD_NAME);
        
        long l_lngBranchId = 33381L;
        double l_dblQuantity = 123D;
        
        try
        {
            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(l_lngBranchId, null, l_dblQuantity);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testValidateDomesticBondRecruitLimit_0007
     * 引数.注文数量 == 0 の場合、以下のチェックを行なう。
     * 国内債券部店別応募枠情報[0].WEB3応募枠 - 国内債券部店別応募枠情報[0].注文金額合計
     *  <= 0 の場合、
     * 例外：「すでに応募枠に達しています。」をスローする。
     */
    public void testValidateDomesticBondRecruitLimit_0007()
    {
        final String STR_METHOD_NAME = " testValidateDomesticBondRecruitLimit_0007()";
        log.entering(STR_METHOD_NAME);

        try
        {

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.getProductId());
            TestDBUtility.insertWithDel(l_bondProductParams);

            long l_lngBranchId = 33381L;
            double l_dblQuantity = 123D;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3BondProductManager l_bondProductManager =
             (WEB3BondProductManager)l_finApp.getTradingModule(
                 ProductTypeEnum.BOND).getProductManager();

            WEB3BondProduct l_bondProduct =
                (WEB3BondProduct)l_bondProductManager.getBondProduct(l_productParams.getProductId());

            WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
                new WEB3BondOrderManagerReusableValidationsCheck();
            l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(
                l_lngBranchId,
                l_bondProduct,
                l_dblQuantity);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(BondProductParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
