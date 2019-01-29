head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.52.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticRecruitLimitManageHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.handler;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputResponse;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticRecruitLimitManageService;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticRecruitLimitManageServiceImpl;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondDomesticRecruitLimitManageHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageHandlerTest.class);

    WEB3AdminBondDomesticRecruitLimitManageHandler l_handler;
    public WEB3AdminBondDomesticRecruitLimitManageHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3AdminBondDomesticRecruitLimitManageHandler();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testInputRecruitLimitManage_T01()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminBondDomesticRecruitLimitManageService.class);
            WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
                l_handler.inputRecruitLimitManage(new WEB3AdminBondDomesticRecruitLimitManageInputRequest());
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
                WEB3AdminBondDomesticRecruitLimitManageService.class,
                new WEB3AdminBondDomesticRecruitLimitManageServiceImpl());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testInputRecruitLimitManage_T02()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long("123456789"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3AdminBondDomesticRecruitLimitManageInputRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageInputRequest();
            l_request.productId = null;
            
            WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
                l_handler.inputRecruitLimitManage(l_request);
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
    
    public void testInputRecruitLimitManage_T03()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageInputRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageInputRequest();
            l_request.productId = "123456";
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
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

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = 
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456l);
            TestDBUtility.insertWithDel(l_productParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            TestDBUtility.insertWithDel(l_bondProductParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //BondBranchConditionParams
            TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
            BondBranchConditionParams l_conditionParams =
                TestDBUtility.getBondBranchConditionRow();
            l_conditionParams.setBranchId(l_branchParams.getBranchId());
            l_conditionParams.setBranchRecruitLimitDiv("0");
            TestDBUtility.insertWithDel(l_conditionParams);

            //interceptor
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long("123456789"));

            //WEB3BondDomesticBranchRecruitLimitInfo
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                new WEB3BondDomesticBranchRecruitLimitInfo[0]);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("624");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            
            WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
                l_handler.inputRecruitLimitManage(l_request);

            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "createAdminBondDomesticRecruitLimitInfo", 
                    new Class[] {long.class, String.class, String.class});
           assertEquals(new Long("123456"), l_paramsValue.getFirstCalled()[0]);
           assertEquals("0D", l_paramsValue.getFirstCalled()[1]);
           assertEquals("---", l_paramsValue.getFirstCalled()[2]);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitManage_T01()
    {
        final String STR_METHOD_NAME = "testValidateRecruitLimitManage_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminBondDomesticRecruitLimitManageService.class);
            WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response =
                l_handler.validateRecruitLimitManage(new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest());
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
                WEB3AdminBondDomesticRecruitLimitManageService.class,
                new WEB3AdminBondDomesticRecruitLimitManageServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitManage_T02()
    {
        final String STR_METHOD_NAME = "testValidateRecruitLimitManage_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = null;

            //interceptor
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long("123456789"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("624");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response =
                l_handler.validateRecruitLimitManage(l_request);
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

    public void testValidateRecruitLimitManage_T03()
    {
        final String STR_METHOD_NAME = "testValidateRecruitLimitManage_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";
            l_request.bondDomesticBranchRecruitLimitInfo = null;

            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456);
            l_bondProductParams.setTradeHandleDiv("2");
            l_bondProductParams.setTradeStartDate(WEB3DateUtility.getDate("20070501", "yyyyMMdd"));
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("99990101", "yyyyMMdd"));
//            l_bondProductParams.setProspectusCheckDiv("1");
            l_bondProductParams.setTradeType(WEB3BondTradeTypeDef.RECRUIT);
            l_bondProductParams.setRecruitStartDate(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondProductParams.setRecruitEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setInstitutionCode("0D");
            l_bondProductParams.setTradeEndDate(WEB3DateUtility.getDate("20071201", "yyyyMMdd"));
            l_bondProductParams.setProductName("zhoumoyang");
            l_bondProductParams.setCoupon(0.01D);
            l_bondProductParams.setYearlyInterestPayments(3);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20100503", "yyyyMMdd"));
            l_bondProductParams.setBuyPrice(30D);
            TestDBUtility.insertWithDel(l_bondProductParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("624");
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

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = 
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //WEB3BondDomesticBranchRecruitLimitInfo
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo[3];
            l_limitInfo1[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[0].branchCode = "624";
            l_limitInfo1[0].web3RecruitLimit = "15.48";
            l_limitInfo1[0].orderAmountTotal = "10";
            
            l_limitInfo1[1] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[1].branchCode = "625";
            l_limitInfo1[1].web3RecruitLimit = "10";
            l_limitInfo1[1].orderAmountTotal = "10";
            
            l_limitInfo1[2] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[2].branchCode = "---";
            l_limitInfo1[2].web3RecruitLimit = "40";
            l_limitInfo1[2].orderAmountTotal = "20";

            //2222222222222
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo2 =
                new WEB3BondDomesticBranchRecruitLimitInfo[3];
            l_limitInfo2[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[0].branchCode = "624";
            l_limitInfo2[0].web3RecruitLimit = "20";
            
            l_limitInfo2[1] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[1].branchCode = "623";
            l_limitInfo2[1].web3RecruitLimit = "10";
            
            l_limitInfo2[2] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[2].branchCode = "625";
            l_limitInfo2[2].web3RecruitLimit = "20";
            l_request.bondDomesticBranchRecruitLimitInfo = l_limitInfo2;

            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo =
                new WEB3BondDomesticBranchRecruitLimitInfo[0];
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                l_limitInfo1);

            //interceptor
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long("123456789"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("624");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            
            WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response =
                l_handler.validateRecruitLimitManage(l_request);
            
            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "createAdminBondDomesticRecruitLimitInfo", 
                    new Class[] {long.class, String.class, String.class});
            assertEquals(new Long(123456), l_paramsValue.getFirstCalled()[0]);
            assertEquals("0D", l_paramsValue.getFirstCalled()[1]);
            assertEquals(null, l_paramsValue.getFirstCalled()[2]);

            assertEquals(l_limitInfo1, l_response.bondDomesticBranchRecruitLimitInfo);

        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitRecruitLimitManage_T01()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Services.unregisterService(WEB3AdminBondDomesticRecruitLimitManageService.class);
            WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
                l_handler.submitRecruitLimitManage(new WEB3AdminBondDomesticRecruitLimitManageCompleteRequest());
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
                WEB3AdminBondDomesticRecruitLimitManageService.class,
                new WEB3AdminBondDomesticRecruitLimitManageServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitRecruitLimitManage_T02()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageCompleteRequest();
            l_request.productId = null;

            //interceptor
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long("123456789"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("624");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
                l_handler.submitRecruitLimitManage(l_request);
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
    
    public void testSubmitRecruitLimitManage_T03()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageCompleteRequest();
            l_request.productId = "123456";
            l_request.password = "321654";

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

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = 
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            WEB3AdministratorForMock.mockValidateTradingPassword("321654", true);

            //WEB3BondDomesticBranchRecruitLimitInfo
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo[3];
            l_limitInfo1[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[0].branchCode = "624";
            l_limitInfo1[0].web3RecruitLimit = "15.48";
            l_limitInfo1[0].orderAmountTotal = "10";
            
            l_limitInfo1[1] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[1].branchCode = "625";
            l_limitInfo1[1].web3RecruitLimit = "10";
            l_limitInfo1[1].orderAmountTotal = "10";
            
            l_limitInfo1[2] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[2].branchCode = "---";
            l_limitInfo1[2].web3RecruitLimit = "40";
            l_limitInfo1[2].orderAmountTotal = "20";

            //2222222222222
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo2 =
                new WEB3BondDomesticBranchRecruitLimitInfo[3];
            l_limitInfo2[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[0].branchCode = "624";
            l_limitInfo2[0].web3RecruitLimit = "20";
            
            l_limitInfo2[1] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[1].branchCode = "623";
            l_limitInfo2[1].web3RecruitLimit = "20";

            l_limitInfo2[2] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[2].branchCode = "625";
            l_limitInfo2[2].web3RecruitLimit = "20";
            l_request.bondDomesticBranchRecruitLimitInfo = l_limitInfo2;

            //WEB3BondDomesticBranchRecruitLimitInfo
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                l_limitInfo1);

            //BondBranchRecruitLimitParams
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            BondBranchRecruitLimitParams l_limitParams = this.getBondBranchRecruitLimitParams();
            l_limitParams.setBranchCode("624");
            l_limitParams.setProductId(123456l);
            l_limitParams.setInstitutionCode(l_administrator.getInstitutionCode());
            TestDBUtility.insertWithDel(l_limitParams);

            //QueryProcessor
            //2222222
            l_limitParams.setBranchCode("623");
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_limitParams);

            //33333333333
            l_limitParams.setBranchCode("625");
            l_queryProcessor.doInsertQuery(l_limitParams);

            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
            
            //interceptor
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long("123456789"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456789L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBranchCode("624");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
                l_handler.submitRecruitLimitManage(l_request);
            
            List l_lisResult = l_queryProcessor.doFindAllQuery(BondBranchRecruitLimitParams.TYPE);
            String l_datTime1 = WEB3DateUtility.formatDate(l_tsTime, "yyyyMMdd");
            
            assertEquals(3, l_lisResult.size());
            
            //11111111111
            BondBranchRecruitLimitRow l_limitRow1 =
                (BondBranchRecruitLimitRow)l_lisResult.get(0);
            assertEquals(l_administrator.getAdministratorCode(), l_limitRow1.getLastUpdater());
            assertEquals(l_limitRow1.getRecruitLimit(), 20, 0);
            String l_datTime2 = WEB3DateUtility.formatDate(l_limitRow1.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime2);
            
            //222222222222
            BondBranchRecruitLimitRow l_limitRow2 =
                (BondBranchRecruitLimitRow)l_lisResult.get(1);
            assertEquals(l_administrator.getAdministratorCode(), l_limitRow2.getLastUpdater());
            assertEquals(l_limitRow2.getRecruitLimit(), 20, 0);
            String l_datTime3 = WEB3DateUtility.formatDate(l_limitRow2.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime3);
            
            //3333333
            BondBranchRecruitLimitRow l_limitRow3 =
                (BondBranchRecruitLimitRow)l_lisResult.get(1);
            assertEquals(l_administrator.getAdministratorCode(), l_limitRow3.getLastUpdater());
            assertEquals(l_limitRow3.getRecruitLimit(), 20, 0);
            String l_datTime4 = WEB3DateUtility.formatDate(l_limitRow3.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime4);
            
            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "createAdminBondDomesticRecruitLimitInfo", 
                    new Class[] {long.class, String.class, String.class});
           assertEquals(new Long("123456"), l_paramsValue.getFirstCalled()[0]);
           assertEquals("0D", l_paramsValue.getFirstCalled()[1]);
           assertEquals(null, l_paramsValue.getFirstCalled()[2]);

           //response
           assertEquals(l_limitInfo1, l_response.bondDomesticBranchRecruitLimitInfo);
            
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public BondBranchRecruitLimitParams getBondBranchRecruitLimitParams()
    {
        BondBranchRecruitLimitParams l_params = new BondBranchRecruitLimitParams();
        //銘柄ID    product_idNUMBER18  NotNull
        l_params.setProductId(123456789l);

        //証券会社コード institution_codeVARCHAR23   NotNull
        l_params.setInstitutionCode("0D");

        //部店コード   branch_codeVARCHAR23    NotNull
        l_params.setBranchCode("624");

        //応募枠 recruit_limitDECIMAL18  12  6   NULL
        l_params.setRecruitLimit(12.45);

        //最終更新者コード    last_updaterVARCHAR220  NULL
        l_params.setLastUpdater("456");

        //作成日付    created_timestampDATENotNull
        Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
        l_params.setCreatedTimestamp(l_tsTime);

        //更新日付    last_updated_timestamp DATENotNull
        l_params.setLastUpdatedTimestamp(l_tsTime);

        return l_params;
    }
}
@
