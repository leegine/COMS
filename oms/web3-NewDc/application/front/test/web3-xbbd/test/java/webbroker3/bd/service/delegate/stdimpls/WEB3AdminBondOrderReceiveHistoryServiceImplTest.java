head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondOrderReceiveHistoryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.data.BondOrderAcceptActionParams;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryResponse;
import webbroker3.common.WEB3BaseException;
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

/**
 * (管理者注文受付履歴照会サービスImpl)<BR>
 * 管理者注文受付履歴照会サービス実装クラス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryServiceImplTest.class);
    WEB3AdminBondOrderReceiveHistoryServiceImpl l_impl =
        new WEB3AdminBondOrderReceiveHistoryServiceImpl();
    public WEB3AdminBondOrderReceiveHistoryServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecute_case0001()
    {
        final String STR_METHOD_NAME = "testExecute_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            l_impl.execute(null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0002()
    {
        final String STR_METHOD_NAME = "testExecute_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageCompleteRequest();
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0003()
    {
        final String STR_METHOD_NAME = "testExecute_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0004()
    {
        final String STR_METHOD_NAME = "testExecute_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_request.productID = "123";
            l_request.branchCode = "361";
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_case0005()
    {
        final String STR_METHOD_NAME = "testExecute_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(123);
            l_administratorParams.setBranchCode("1");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C1101");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            BondBranchConditionParams l_bondBranchConditionParams = TestDBUtility.getBondBranchConditionRow();
            l_bondBranchConditionParams.setBranchId(33381L);
            l_bondBranchConditionParams.setBranchRecruitLimitDiv("1");
            TestDBUtility.insertWithDel(l_bondBranchConditionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setBranchCode("1");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_request.productID = "123";
            l_request.branchCode = "361";
            l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0006()
    {
        final String STR_METHOD_NAME = "testExecute_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
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
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_request.productID = "123";
            l_request.branchCode = "361";
            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_impl.execute(l_request);
            assertEquals("zhoumoyang", l_response.productName);
            assertEquals("20070501", WEB3DateUtility.formatDate(l_response.recruitStartDate,"yyyyMMdd"));
            assertEquals("20071201", WEB3DateUtility.formatDate(l_response.recruitEndDate,"yyyyMMdd"));
            assertEquals(0, l_response.orderAcceptHistory.length);
            assertEquals("361", l_response.bondDomesticBranchRecruitLimitInfo.branchCode);
        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
//        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0007()
    {
        final String STR_METHOD_NAME = "testExecute_case0007()";
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
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_request.productID = "123";
            l_request.branchCode = "361";
            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_impl.execute(l_request);
            assertEquals("zhoumoyang", l_response.productName);
            assertEquals("20070501", WEB3DateUtility.formatDate(l_response.recruitStartDate,"yyyyMMdd"));
            assertEquals("20071201", WEB3DateUtility.formatDate(l_response.recruitEndDate,"yyyyMMdd"));
            assertEquals(1, l_response.orderAcceptHistory.length);
            assertEquals("10", l_response.orderAcceptHistory[0].orderAmount);
            assertEquals("10", l_response.orderAcceptHistory[0].orderNumber);
            assertEquals("20", l_response.orderAcceptHistory[0].accumulatedTotal);
            assertEquals("361", l_response.bondDomesticBranchRecruitLimitInfo.branchCode);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0008()
    {
        final String STR_METHOD_NAME = "testExecute_case0008()";
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
            
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams.setBranchCode("361");
            l_bondOrderAcceptActionParams.setProductId(123);
            l_bondOrderAcceptActionParams.setOrderAcceptDate(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);
            
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            WEB3AdminBondOrderReceiveHistoryRequest l_request =
                new WEB3AdminBondOrderReceiveHistoryRequest();
            l_request.productID = "123";
            l_request.branchCode = "361";
            WEB3AdminBondOrderReceiveHistoryResponse l_response =
                (WEB3AdminBondOrderReceiveHistoryResponse)l_impl.execute(l_request);
            assertEquals("zhoumoyang", l_response.productName);
            assertEquals("20070501", WEB3DateUtility.formatDate(l_response.recruitStartDate,"yyyyMMdd"));
            assertEquals("20071201", WEB3DateUtility.formatDate(l_response.recruitEndDate,"yyyyMMdd"));
            assertEquals(1, l_response.orderAcceptHistory.length);
            assertEquals("", l_response.orderAcceptHistory[0].orderAmount);
            assertEquals("", l_response.orderAcceptHistory[0].orderNumber);
            assertEquals("", l_response.orderAcceptHistory[0].accumulatedTotal);
            assertEquals("361", l_response.bondDomesticBranchRecruitLimitInfo.branchCode);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_case0009()
    {
        final String STR_METHOD_NAME = "testExecute_case0009()";
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
//            
//            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams1 = new BondBranchRecruitLimitParams();
//            l_bondBranchRecruitLimitParams1.setBranchCode("361");
//            l_bondBranchRecruitLimitParams1.setProductId(123);
//            l_bondBranchRecruitLimitParams1.setInstitutionCode("0C");
//            l_bondBranchRecruitLimitParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
//            l_bondBranchRecruitLimitParams1.setRecruitLimit(10D);
//            l_bondBranchRecruitLimitParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
//            l_bondBranchRecruitLimitParams1.setRecruitLimit(10000000L);
//            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams1);
            
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
                (WEB3AdminBondOrderReceiveHistoryResponse)l_impl.execute(l_request);
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
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
