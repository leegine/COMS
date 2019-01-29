head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticProductRegistHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputResponse;
import webbroker3.bd.message.WEB3BondDomesticProductBasicInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductRegistService;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductRegistServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductRegistServiceImplTest;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondDomesticProductRegistHandlerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistServiceImplTest.class);
    WEB3AdminBondDomesticProductRegistHandler l_handler =
        new WEB3AdminBondDomesticProductRegistHandler();

    public WEB3AdminBondDomesticProductRegistHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testInputProductRegistT01()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistService l_service =
                (WEB3AdminBondDomesticProductRegistService)Services.getService(
                    WEB3AdminBondDomesticProductRegistService.class);
            Services.unregisterService(WEB3AdminBondDomesticProductRegistService.class);

            WEB3AdminBondDomesticProductRegistInputRequest l_reqeust =
                new WEB3AdminBondDomesticProductRegistInputRequest();
            WEB3AdminBondDomesticProductRegistInputResponse l_response =
                l_handler.inputProductRegist(l_reqeust);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminBondDomesticProductRegistService.class,
                 new WEB3AdminBondDomesticProductRegistServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputProductRegistT02()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistInputRequest l_reqeust =
                new WEB3AdminBondDomesticProductRegistInputRequest();
            l_reqeust.productId = null;

            WEB3AdminBondDomesticProductRegistInputResponse l_response =
                l_handler.inputProductRegist(l_reqeust);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_response.errorInfo);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testInputProductRegistT03()
    {
        final String STR_METHOD_NAME = "testInputProductRegistT03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistInputRequest l_request =
                new WEB3AdminBondDomesticProductRegistInputRequest();

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            WEB3AdminBondDomesticProductRegistInputResponse l_response =
                l_handler.inputProductRegist(l_request);
            
            WEB3BondDomesticProductBasicInfo l_baseInfo = l_response.productBasicInfo;
            assertEquals("0763", l_baseInfo.productCode);
            assertEquals("101", l_baseInfo.productIssueCode);
            assertEquals("jiddk", l_baseInfo.productNameHost);
            assertEquals("123", l_baseInfo.bondCategCode);
            assertEquals(0, l_baseInfo.issueCouponType.length);
            assertEquals(WEB3DateUtility.getDate("2004/01/01", "yyyy/MM/dd"), l_baseInfo.issueDate);
            assertEquals(null, l_baseInfo.applyPrice);
            assertEquals("0.08", l_baseInfo.coupon);
            assertEquals("2", l_baseInfo.yearlyInterestPayments);
            assertNull(l_baseInfo.couponPaymentDate1);
            assertEquals(WEB3DateUtility.getDate("2009/07/01", "yyyy/MM/dd"), l_baseInfo.maturityDate);
            assertEquals(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"), l_baseInfo.recruitStartDateSONAR);
            assertEquals(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"),l_baseInfo.recruitEndDateSONAR);
            
            WEB3BondDomesticProductUpdateInfo l_updateInfo = l_response.productUpdateInfo;
            assertEquals("2", l_updateInfo.tradeHandleDiv);
            assertEquals("6", l_updateInfo.dealingType);
            assertEquals(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"), l_updateInfo.recruitStartDateWEB3);
            assertEquals(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"), l_updateInfo.recruitEndDateWEB3);
            assertEquals(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"), l_updateInfo.recruitStartDateInterNet);
            assertEquals(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"), l_updateInfo.recruitEndDateInterNet);
            assertEquals(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"), l_updateInfo.deliveryDate);
            assertEquals("chai024", l_updateInfo.productNameWEB3);
            assertEquals("100", l_updateInfo.applyUnit);
            assertEquals("100", l_updateInfo.minFaceAmount);
            assertEquals(null, l_updateInfo.maxFaceAmount);
            assertEquals("1", l_updateInfo.prospectusCheckDiv);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductRegistT_01()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistService l_service =
                (WEB3AdminBondDomesticProductRegistService)Services.getService(
                    WEB3AdminBondDomesticProductRegistService.class);
            Services.unregisterService(WEB3AdminBondDomesticProductRegistService.class);

            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();
            WEB3AdminBondDomesticProductRegistConfirmResponse l_response =
                l_handler.validateProductRegist(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminBondDomesticProductRegistService.class,
                 new WEB3AdminBondDomesticProductRegistServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateProductRegistT_02()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();
            l_request.productId = null;
            WEB3AdminBondDomesticProductRegistConfirmResponse l_response =
                l_handler.validateProductRegist(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_response.errorInfo);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateProductRegistT_03()
    {
        final String STR_METHOD_NAME = "testValidateProductRegistT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistConfirmRequest l_request =
                new WEB3AdminBondDomesticProductRegistConfirmRequest();

            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            //WEB3BondProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "validateProductSpec", 
                new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class}, 
                null);

            WEB3AdminBondDomesticProductRegistConfirmResponse l_respose =
                l_handler.validateProductRegist(l_request);
            
            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "validateProductSpec", 
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class});
           assertEquals(l_request.productId, l_paramsValue.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue.getFirstCalled()[1]);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitProductRegistT_01()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistService l_service =
                (WEB3AdminBondDomesticProductRegistService)Services.getService(
                    WEB3AdminBondDomesticProductRegistService.class);
            Services.unregisterService(WEB3AdminBondDomesticProductRegistService.class);

            WEB3AdminBondDomesticProductRegistCompleteRequest l_reqeust =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            WEB3AdminBondDomesticProductRegistCompleteResponse l_response =
                l_handler.submitProductRegist(l_reqeust);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminBondDomesticProductRegistService.class,
                 new WEB3AdminBondDomesticProductRegistServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitProductRegistT_02()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_reqeust =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            l_reqeust.productId = null;
            WEB3AdminBondDomesticProductRegistCompleteResponse l_response =
                l_handler.submitProductRegist(l_reqeust);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_response.errorInfo);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitProductRegistT_03()
    {
        final String STR_METHOD_NAME = "testSubmitProductRegistT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticProductRegistCompleteRequest l_request =
                new WEB3AdminBondDomesticProductRegistCompleteRequest();
            l_request.password = "123456";
            WEB3BondDomesticProductUpdateInfo l_updateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_updateInfo.tradeHandleDiv = "0";
            l_updateInfo.dealingType = "3";
            l_updateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("2008/08/01", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("2008/08/02", "yyyy/MM/dd");
            l_updateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("2008/08/03", "yyyy/MM/dd");
            l_updateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("2008/08/04", "yyyy/MM/dd");
            l_updateInfo.deliveryDate = WEB3DateUtility.getDate("2008/08/05", "yyyy/MM/dd");
            l_updateInfo.productNameWEB3 = "jiddk";
            l_updateInfo.applyUnit = "123456";
            l_updateInfo.maxFaceAmount = "456123";
            l_updateInfo.minFaceAmount = "10";
            l_updateInfo.prospectusCheckDiv = "1";
            l_request.bondDomesticProductUpdateInfo = l_updateInfo;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456", true);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("C1101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(l_productParams.product_id);
            l_bondProductParams.setHostProductName1("jiddk");
            l_bondProductParams.setBondCategCode("123");
            l_bondProductParams.setHostRecruitStartDate(WEB3DateUtility.getDate("2009/10/01", "yyyy/MM/dd"));
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("2009/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeType("6");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("2003/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("2004/11/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("2005/02/01", "yyyy/MM/dd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("2004/08/11", "yyyy/MM/dd"));
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("2008/08/12", "yyyy/MM/dd"));
            l_bondProductParams.setProductName("chai024");
            l_bondProductParams.setProspectusCheckDiv("1");
            TestDBUtility.insertWithDel(l_bondProductParams);
            l_request.productId = l_productParams.product_id + "";

            //WEB3BondProductManager
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "validateProductSpec", 
                new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class}, 
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.bd.WEB3BondProductManager",
                    "updateBondProductContent",
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class, String.class},
                    null);

            WEB3AdminBondDomesticProductRegistCompleteResponse l_response =
                l_handler.submitProductRegist(l_request);

            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "validateProductSpec", 
                    new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class});

           assertEquals(l_request.productId, l_paramsValue1.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue1.getFirstCalled()[1]);
           
           WEB3MockObjectParamsValue l_paramsValue2 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.bd.WEB3BondProductManager",
                   "updateBondProductContent",
                   new Class[] {String.class, WEB3BondDomesticProductUpdateInfo.class, String.class});

           assertEquals(l_request.productId, l_paramsValue2.getFirstCalled()[0]);
           assertEquals(l_request.bondDomesticProductUpdateInfo, l_paramsValue2.getFirstCalled()[1]);
           assertEquals(l_administratorParams.getAdministratorCode(), l_paramsValue2.getFirstCalled()[2]);

           //返回?驗證
           assertEquals(l_administratorParams.getAdministratorCode(), l_response.updaterCode);
           String l_strDate1 = WEB3DateUtility.formatDate(l_response.updateTimeStamp, "yyyy/MM/dd");
           String l_strDate2 = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd");
           assertEquals(l_strDate1, l_strDate2);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
