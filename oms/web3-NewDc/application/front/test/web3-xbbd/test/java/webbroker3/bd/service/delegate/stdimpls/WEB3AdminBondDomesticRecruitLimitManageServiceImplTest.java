head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticRecruitLimitManageServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
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
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondDomesticRecruitLimitManageServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageServiceImplTest.class);

    WEB3AdminBondDomesticRecruitLimitManageServiceImpl l_impl = null;

    public WEB3AdminBondDomesticRecruitLimitManageServiceImplTest(String arg0)
    {
        super(arg0);
        l_impl = new WEB3AdminBondDomesticRecruitLimitManageServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecute_T01()
    {
        final String STR_METHOD_NAME = "testExecute_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_T02()
    {
        final String STR_METHOD_NAME = "testExecute_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            l_impl.execute(new WEB3AdminBondExecCancelCompleteRequest());
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecute_T03()
    {
        final String STR_METHOD_NAME = "testExecute_T03()";
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
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
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

            //WEB3BondDomesticBranchRecruitLimitInfo
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                new WEB3BondDomesticBranchRecruitLimitInfo[0]);

            WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
                (WEB3AdminBondDomesticRecruitLimitManageInputResponse)l_impl.execute(l_request);

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
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_T04()
    {
        final String STR_METHOD_NAME = "testExecute_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";
            l_request.bondDomesticBranchRecruitLimitInfo = null;

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

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response =
                (WEB3AdminBondDomesticRecruitLimitManageConfirmResponse)l_impl.execute(l_request);

            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "createAdminBondDomesticRecruitLimitInfo", 
                    new Class[] {long.class, String.class, String.class});
            assertEquals(new Long(123456), l_paramsValue.getFirstCalled()[0]);
            assertEquals("0D", l_paramsValue.getFirstCalled()[1]);
            assertEquals(null, l_paramsValue.getFirstCalled()[2]);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_T05()
    {
        final String STR_METHOD_NAME = "testExecute_T05()";
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

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
            WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
                (WEB3AdminBondDomesticRecruitLimitManageCompleteResponse)l_impl.execute(l_request);
            
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

    public void testInputRecruitLimitManage_T01()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageInputRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageInputRequest();
            l_request.productId = null;
            l_impl.inputRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputRecruitLimitManage_T02()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageInputRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageInputRequest();
            
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
            l_adminPermissionParams.setTransactionCategory("C1102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_request.productId = "123456";
            l_impl.inputRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
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

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams = 
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //BondProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            l_request.productId = "123456";
            l_impl.inputRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputRecruitLimitManage_T04()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T04()";
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
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
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
            
            l_impl.inputRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testInputRecruitLimitManage_T05()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T05()";
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
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
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

            l_impl.inputRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testInputRecruitLimitManage_T06()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T06()";
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
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
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
            TestDBUtility.insertWithDel(l_conditionParams);

            //WEB3BondDomesticBranchRecruitLimitInfo
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                new WEB3BondDomesticBranchRecruitLimitInfo[0]);

            WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
                l_impl.inputRecruitLimitManage(l_request);

            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "createAdminBondDomesticRecruitLimitInfo", 
                    new Class[] {long.class, String.class, String.class});
           assertEquals(new Long("123456"), l_paramsValue.getFirstCalled()[0]);
           assertEquals("0D", l_paramsValue.getFirstCalled()[1]);
           assertEquals(null, l_paramsValue.getFirstCalled()[2]);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInputRecruitLimitManage_T07()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T07()";
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

            //WEB3BondDomesticBranchRecruitLimitInfo
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                new WEB3BondDomesticBranchRecruitLimitInfo[0]);

            WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
                l_impl.inputRecruitLimitManage(l_request);

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
    
    public void testInputRecruitLimitManage_T08()
    {
        final String STR_METHOD_NAME = "testInputRecruitLimitManage_T08()";
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
            l_conditionParams.setBranchRecruitLimitDiv("1");
            TestDBUtility.insertWithDel(l_conditionParams);

            //WEB3BondDomesticBranchRecruitLimitInfo
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo =
                new WEB3BondDomesticBranchRecruitLimitInfo[0];
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                l_limitInfo);

            WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response =
                l_impl.inputRecruitLimitManage(l_request);

            //驗證被mock方法@
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.bd.WEB3BondProductManager", 
                    "createAdminBondDomesticRecruitLimitInfo", 
                    new Class[] {long.class, String.class, String.class});
           assertEquals(new Long("123456"), l_paramsValue.getFirstCalled()[0]);
           assertEquals("0D", l_paramsValue.getFirstCalled()[1]);
           assertEquals("624", l_paramsValue.getFirstCalled()[2]);

           //response check
           assertEquals(l_bondProductParams.getHostProductCode(), l_response.productCode);
           assertEquals(l_bondProductParams.getHostProductIssueCode(), l_response.productIssueCode);
           assertEquals(l_bondProductParams.getProductName(), l_response.productName);
           assertEquals(l_bondProductParams.getBondCategCode(), l_response.bondCategCode);
           assertEquals(l_bondProductParams.getIssueDate(), l_response.issueDate);
           assertEquals(l_bondProductParams.getIssuePrice(), Double.parseDouble(l_response.issuePrice), 0);
           assertEquals(l_bondProductParams.getCoupon(), Double.parseDouble(l_response.annualRate), 0);
           assertEquals(l_bondProductParams.getInterestPaymentDay1st(), l_response.couponPaymentDate1);
           assertEquals(l_bondProductParams.getInterestPaymentDay2nd(), l_response.couponPaymentDate2);
           assertEquals(l_bondProductParams.getMaturityDate(), l_response.maturityDate);
           assertEquals(l_bondProductParams.getRecruitStartDate(), l_response.recruitStartDate);
           assertEquals(l_bondProductParams.getRecruitEndDate(), WEB3DateUtility.toDay(l_response.recruitEndDate));
           assertEquals(l_limitInfo, l_response.bondDomesticBranchRecruitLimitInfo);
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
        final String STR_METHOD_NAME = " testValidateRecruitLimitManage_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = null;
            l_impl.validateRecruitLimitManage(l_request);
            fail();
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateRecruitLimitManage_T02()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitManage_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";

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

            l_impl.validateRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
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
        final String STR_METHOD_NAME = " testValidateRecruitLimitManage_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";

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
            l_adminPermissionParams.setTransactionCategory("C1102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_impl.validateRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitManage_T04()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitManage_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";

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
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            l_impl.validateRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01380, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitManage_T05()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitManage_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";
            l_request.bondDomesticBranchRecruitLimitInfo = null;

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

            //WEB3BondDomesticBranchRecruitLimitInfo
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo =
                new WEB3BondDomesticBranchRecruitLimitInfo[0];
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                l_limitInfo);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            l_impl.validateRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitManage_T06()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitManage_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";
            l_request.bondDomesticBranchRecruitLimitInfo = null;

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

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response =
                l_impl.validateRecruitLimitManage(l_request);

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

    public void testValidateRecruitLimitManage_T07()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitManage_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageConfirmRequest();
            l_request.productId = "123456";
            l_request.bondDomesticBranchRecruitLimitInfo = null;

            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2097,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                l_timestamp);

            l_impl.validateRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02901, l_exBE.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
            log.error(l_exBE.getMessage(), l_exBE);
        }
        catch(Exception l_exE)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_exE.getMessage(), l_exE);

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
            WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageCompleteRequest();
            l_request.productId = null;
            l_impl.submitRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02229, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
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
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            l_impl.submitRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
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
            l_adminPermissionParams.setTransactionCategory("C1102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_impl.submitRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitRecruitLimitManage_T04()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T04()";
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
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_impl.submitRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01380, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitRecruitLimitManage_T05()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T05()";
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

            WEB3AdministratorForMock.mockValidateTradingPassword("321654", false);

            l_impl.submitRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testSubmitRecruitLimitManage_T06()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request =
                new WEB3AdminBondDomesticRecruitLimitManageCompleteRequest();
            l_request.productId = "123456";
            l_request.password = "321654";
            l_request.bondDomesticBranchRecruitLimitInfo = null;

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
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo =
                new WEB3BondDomesticBranchRecruitLimitInfo[0];
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.bd.WEB3BondProductManager",
                "createAdminBondDomesticRecruitLimitInfo", 
                new Class[] {long.class, String.class, String.class}, 
                l_limitInfo);

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            l_impl.submitRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitRecruitLimitManage_T07()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T07()";
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

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
            WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
                l_impl.submitRecruitLimitManage(l_request);
            
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

    public void testSubmitRecruitLimitManage_T08()
    {
        final String STR_METHOD_NAME = "testSubmitRecruitLimitManage_T08()";
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

            //BondProductParams
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123456l);
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070901", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2097,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                l_timestamp);

            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
            WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response =
                l_impl.submitRecruitLimitManage(l_request);
            fail();
        }
        catch(WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02901, l_exBE.getErrorInfo());
            log.error(l_exBE.getMessage(), l_exBE);
        }
        catch(Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBranchRecruitLimit_T01()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.validateBranchRecruitLimit(null, new WEB3BondDomesticBranchRecruitLimitInfo[]{null});
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBranchRecruitLimit_T02()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.validateBranchRecruitLimit(new WEB3BondDomesticBranchRecruitLimitInfo[]{null}, null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBranchRecruitLimit_T03()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //11111111111
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            l_limitInfo1[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[0].branchCode = "624";
            l_limitInfo1[0].web3RecruitLimit = "15.48";
            l_limitInfo1[0].orderAmountTotal = "12.46";

            //2222222222222
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo2 =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            l_limitInfo2[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[0].branchCode = "624";
            l_limitInfo2[0].web3RecruitLimit = "12.45";

            l_impl.validateBranchRecruitLimit(
                l_limitInfo1,
                l_limitInfo2);

            l_impl.validateBranchRecruitLimit(new WEB3BondDomesticBranchRecruitLimitInfo[]{null}, null);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02885, l_ex.getErrorInfo());
            assertEquals(l_ex.getErrorMessage(), "624");
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testValidateBranchRecruitLimit_T04()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //11111111111
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            l_limitInfo1[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[0].branchCode = "624";
            l_limitInfo1[0].web3RecruitLimit = "15.48";
            l_limitInfo1[0].orderAmountTotal = "12.44";

            //2222222222222
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo2 =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            l_limitInfo2[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[0].branchCode = "624";
            l_limitInfo2[0].web3RecruitLimit = "12.45";

            l_impl.validateBranchRecruitLimit(
                l_limitInfo1,
                l_limitInfo2);

            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02886, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBranchRecruitLimit_T05()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //11111111111
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            l_limitInfo1[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1[0].branchCode = "624";
            l_limitInfo1[0].web3RecruitLimit = "15.48";
            l_limitInfo1[0].orderAmountTotal = "12.44";

            //2222222222222
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfo2 =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            l_limitInfo2[0] = new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2[0].branchCode = "625";
            l_limitInfo2[0].web3RecruitLimit = "12.45";

            l_impl.validateBranchRecruitLimit(
                l_limitInfo1,
                l_limitInfo2);

            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02886, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBranchRecruitLimit_T06()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //11111111111
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
            l_limitInfo1[2].web3RecruitLimit = "15.48";
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
            l_limitInfo2[2].branchCode = "---";
            l_limitInfo2[2].web3RecruitLimit = "40";

            l_impl.validateBranchRecruitLimit(
                l_limitInfo1,
                l_limitInfo2);

        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBranchRecruitLimit_T07()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //11111111111
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

            l_impl.validateBranchRecruitLimit(
                l_limitInfo1,
                l_limitInfo2);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBranchRecruitLimit_T08()
    {
        final String STR_METHOD_NAME = "testValidateBranchRecruitLimit_T08()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.validateBranchRecruitLimit(
                new WEB3BondDomesticBranchRecruitLimitInfo[0],
                new WEB3BondDomesticBranchRecruitLimitInfo[0]);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateRecruitLimit_T01()
    {
        final String STR_METHOD_NAME = "testUpdateRecruitLimit_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            l_impl.updateRecruitLimit(
                l_administrator,
                "789456",
                null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateRecruitLimit_T02()
    {
        final String STR_METHOD_NAME = "testUpdateRecruitLimit_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123");
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            //WEB3BondDomesticBranchRecruitLimitInfo
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfos =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            WEB3BondDomesticBranchRecruitLimitInfo l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1.branchCode = "624";
            l_limitInfo1.web3RecruitLimit = "jiddk";
            l_limitInfos[0] = l_limitInfo1;

            //BondBranchRecruitLimitParams
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            BondBranchRecruitLimitParams l_limitParams = this.getBondBranchRecruitLimitParams();
            l_limitParams.setBranchCode("623");
            TestDBUtility.insertWithDel(l_limitParams);

            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
            l_impl.updateRecruitLimit(
                l_administrator,
                "789456",
                l_limitInfos);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(BondBranchRecruitLimitParams.TYPE);
            assertEquals(1, l_lisResult.size());
            BondBranchRecruitLimitRow l_limitRow =
                (BondBranchRecruitLimitRow)l_lisResult.get(0);
            assertEquals(l_limitRow.getLastUpdater(), l_limitParams.getLastUpdater());
            assertEquals(l_limitRow.getRecruitLimit(), l_limitParams.getRecruitLimit(), 0);
            String l_datTime1 = WEB3DateUtility.formatDate(l_tsTime, "yyyyMMdd");
            String l_datTime2 = WEB3DateUtility.formatDate(l_limitRow.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime2);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateRecruitLimit_T03()
    {
        final String STR_METHOD_NAME = "testUpdateRecruitLimit_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123");
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            //WEB3BondDomesticBranchRecruitLimitInfo
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfos =
                new WEB3BondDomesticBranchRecruitLimitInfo[1];
            WEB3BondDomesticBranchRecruitLimitInfo l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1.branchCode = "624";
            l_limitInfo1.web3RecruitLimit = "963.45";
            l_limitInfos[0] = l_limitInfo1;

            //BondBranchRecruitLimitParams
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            BondBranchRecruitLimitParams l_limitParams = this.getBondBranchRecruitLimitParams();
            l_limitParams.setBranchCode("624");
            l_limitParams.setProductId(789456123l);
            l_limitParams.setInstitutionCode(l_administrator.getInstitutionCode());
            TestDBUtility.insertWithDel(l_limitParams);

            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
            l_impl.updateRecruitLimit(
                l_administrator,
                "789456123",
                l_limitInfos);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(BondBranchRecruitLimitParams.TYPE);
            assertEquals(1, l_lisResult.size());
            BondBranchRecruitLimitRow l_limitRow =
                (BondBranchRecruitLimitRow)l_lisResult.get(0);
            assertEquals(l_administrator.getAdministratorCode(), l_limitRow.getLastUpdater());
            assertEquals(l_limitRow.getRecruitLimit(), 963.45, 0);
            String l_datTime1 = WEB3DateUtility.formatDate(l_tsTime, "yyyyMMdd");
            String l_datTime2 = WEB3DateUtility.formatDate(l_limitRow.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime2);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateRecruitLimit_T04()
    {
        final String STR_METHOD_NAME = "testUpdateRecruitLimit_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("123");
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            //WEB3BondDomesticBranchRecruitLimitInfo
            WEB3BondDomesticBranchRecruitLimitInfo[] l_limitInfos =
                new WEB3BondDomesticBranchRecruitLimitInfo[3];

            //11111111111
            WEB3BondDomesticBranchRecruitLimitInfo l_limitInfo1 =
                new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo1.branchCode = "624";
            l_limitInfo1.web3RecruitLimit = "963.45";

            //22222222222
            WEB3BondDomesticBranchRecruitLimitInfo l_limitInfo2 =
                new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo2.branchCode = "623";
            l_limitInfo2.web3RecruitLimit = "963.45";

            //33333333
            WEB3BondDomesticBranchRecruitLimitInfo l_limitInfo3 =
                new WEB3BondDomesticBranchRecruitLimitInfo();
            l_limitInfo3.branchCode = "622";
            l_limitInfo3.web3RecruitLimit = "963.45";

            l_limitInfos[0] = l_limitInfo1;
            l_limitInfos[1] = l_limitInfo2;
            l_limitInfos[2] = l_limitInfo3;

            //BondBranchRecruitLimitParams
            TestDBUtility.deleteAll(BondBranchRecruitLimitParams.TYPE);
            BondBranchRecruitLimitParams l_limitParams = this.getBondBranchRecruitLimitParams();
            l_limitParams.setBranchCode("624");
            l_limitParams.setProductId(789456123l);
            l_limitParams.setInstitutionCode(l_administrator.getInstitutionCode());
            TestDBUtility.insertWithDel(l_limitParams);

            //QueryProcessor
            //2222222
            l_limitParams.setBranchCode("623");
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_limitParams);
            
            //33333333333
            l_limitParams.setBranchCode("622");
            l_queryProcessor.doInsertQuery(l_limitParams);
            
            Timestamp l_tsTime = GtlUtils.getSystemTimestamp();
            l_impl.updateRecruitLimit(
                l_administrator,
                "789456123",
                l_limitInfos);

            List l_lisResult = l_queryProcessor.doFindAllQuery(BondBranchRecruitLimitParams.TYPE);
            String l_datTime1 = WEB3DateUtility.formatDate(l_tsTime, "yyyyMMdd");
            
            assertEquals(3, l_lisResult.size());
            
            //11111111111
            BondBranchRecruitLimitRow l_limitRow1 =
                (BondBranchRecruitLimitRow)l_lisResult.get(0);
            assertEquals(l_administrator.getAdministratorCode(), l_limitRow1.getLastUpdater());
            assertEquals(l_limitRow1.getRecruitLimit(), 963.45, 0);
            String l_datTime2 = WEB3DateUtility.formatDate(l_limitRow1.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime2);
            
            //222222222222
            BondBranchRecruitLimitRow l_limitRow2 =
                (BondBranchRecruitLimitRow)l_lisResult.get(1);
            assertEquals(l_administrator.getAdministratorCode(), l_limitRow2.getLastUpdater());
            assertEquals(l_limitRow2.getRecruitLimit(), 963.45, 0);
            String l_datTime3 = WEB3DateUtility.formatDate(l_limitRow2.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime3);
            
            //3333333
            BondBranchRecruitLimitRow l_limitRow3 =
                (BondBranchRecruitLimitRow)l_lisResult.get(1);
            assertEquals(l_administrator.getAdministratorCode(), l_limitRow3.getLastUpdater());
            assertEquals(l_limitRow3.getRecruitLimit(), 963.45, 0);
            String l_datTime4 = WEB3DateUtility.formatDate(l_limitRow3.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(l_datTime1, l_datTime4);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitChangePossibility_Case0001()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitChangePossibility_Case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
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
            l_bondProductParams.setProductId(l_productParams.getProductId());
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                l_timestamp);

            l_impl = new WEB3AdminBondDomesticRecruitLimitManageServiceImpl();
            l_impl.validateRecruitLimitChangePossibility(l_productParams.getProductId() + "");
            assertTrue(true);
        }
        catch(WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02901, l_exBE.getErrorInfo());
            log.error(l_exBE.getMessage(), l_exBE);
        }
        catch(Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitChangePossibility_Case0002()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitChangePossibility_Case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
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
            l_bondProductParams.setProductId(l_productParams.getProductId());
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20970101", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                l_timestamp);

            l_impl = new WEB3AdminBondDomesticRecruitLimitManageServiceImpl();
            l_impl.validateRecruitLimitChangePossibility(l_productParams.getProductId() + "");
            assertTrue(true);
        }
        catch(WEB3BaseException l_exBE)
        {
            log.error(l_exBE.getMessage(), l_exBE);
            fail();
        }
        catch(Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateRecruitLimitChangePossibility_Case0003()
    {
        final String STR_METHOD_NAME = " testValidateRecruitLimitChangePossibility_Case0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
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
            l_bondProductParams.setProductId(l_productParams.getProductId());
            l_bondProductParams.setHostRecruitEndDate(WEB3DateUtility.getDate("20070612", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondProductParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,5,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                l_timestamp);

            l_impl = new WEB3AdminBondDomesticRecruitLimitManageServiceImpl();
            l_impl.validateRecruitLimitChangePossibility(l_productParams.getProductId() + "");
            assertTrue(true);
        }
        catch(WEB3BaseException l_exBE)
        {
            log.error(l_exBE.getMessage(), l_exBE);
            fail();
        }
        catch(Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
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
