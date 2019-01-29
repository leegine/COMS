head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondOrderReceiveHistoryHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.data.BondOrderAcceptActionParams;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderReceiveHistoryService;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderReceiveHistoryServiceImpl;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondOrderReceiveHistoryHandlerTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryHandlerTest.class);

    WEB3AdminBondOrderReceiveHistoryHandler l_handler = null;

    public WEB3AdminBondOrderReceiveHistoryHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3AdminBondOrderReceiveHistoryHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testOrderReceiveHistory_T01()
    {
        final String STR_METHOD_NAME = "testOrderReceiveHistory_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminBondOrderReceiveHistoryService.class);
            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                l_handler.orderReceiveHistory(new WEB3AdminBondOrderReceiveHistoryRequest());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                WEB3AdminBondOrderReceiveHistoryService.class,
                new WEB3AdminBondOrderReceiveHistoryServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOrderReceiveHistory_T02()
    {
        final String STR_METHOD_NAME = "testOrderReceiveHistory_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_request.productID = null;

            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                l_handler.orderReceiveHistory(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testOrderReceiveHistory_T03()
    {
        final String STR_METHOD_NAME = "testOrderReceiveHistory_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123);
            l_administratorParams.setBranchCode("361");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C1101");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070501", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
            l_bondProductParams.setProspectusCheckDiv("1");
            l_bondProductParams.setTradeType(WEB3BondTradeTypeDef.RECRUIT);
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setProductName("zhoumoyang");
            l_bondProductParams.setCoupon(0.01D);
            l_bondProductParams.setYearlyInterestPayments(3);
            l_bondProductParams.setBuyPrice(30D);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setBranchCode("361");
            l_bondBranchRecruitLimitParams.setProductId(123);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10D);
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10000000L);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("361");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_insitutionParams);
            
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams.setBranchCode("361");
            l_bondOrderAcceptActionParams.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams.setProductId(123);
            l_bondOrderAcceptActionParams.setOrderCount(10);
            l_bondOrderAcceptActionParams.setOrderAmount(10);
            l_bondOrderAcceptActionParams.setOrderAcceptDate(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);
            
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams1 = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams1.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams1.setBranchCode("361");
            l_bondOrderAcceptActionParams1.setTotalOrderAmount(40);
            l_bondOrderAcceptActionParams1.setProductId(123);
            l_bondOrderAcceptActionParams1.setOrderCount(20);
            l_bondOrderAcceptActionParams1.setOrderAmount(20);
            l_bondOrderAcceptActionParams1.setOrderAcceptDate(WEB3DateUtility.getDate("20070608", "yyyyMMdd"));
            l_bondOrderAcceptActionParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams1);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_request.productID = "123";
            l_request.branchCode = "361";
            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                l_handler.orderReceiveHistory(l_request);
            assertEquals("zhoumoyang", l_response.productName);
            assertEquals("20070501", WEB3DateUtility.formatDate(l_response.recruitStartDate,"yyyyMMdd"));
            assertEquals("20071201", WEB3DateUtility.formatDate(l_response.recruitEndDate,"yyyyMMdd"));
            assertEquals(2, l_response.orderAcceptHistory.length);
            assertEquals("10", l_response.orderAcceptHistory[0].orderAmount);
            assertEquals("10", l_response.orderAcceptHistory[0].orderNumber);
            assertEquals("20", l_response.orderAcceptHistory[0].accumulatedTotal);
            assertEquals("20", l_response.orderAcceptHistory[1].orderAmount);
            assertEquals("20", l_response.orderAcceptHistory[1].orderNumber);
            assertEquals("40", l_response.orderAcceptHistory[1].accumulatedTotal);
            assertEquals("361", l_response.bondDomesticBranchRecruitLimitInfo.branchCode);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
