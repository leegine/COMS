head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiServiceStartServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3SexDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.WEB3SrvRegiServiceStartServiceInterceptor;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiFutOpTaxDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMarginTaxDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiSrvRegiExecErrDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiExecRequest;
import webbroker3.srvregi.message.WEB3SrvRegiExecResponse;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountChangeServiceImplTest.LoginInfoTest;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiServiceStartServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3SrvRegiServiceStartServiceImplTest.class);

    public WEB3SrvRegiServiceStartServiceImplTest(String arg0)
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

    public void testExecuteCase0001()
    {
        final String STR_METHOD_NAME = "testExecuteCase0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceStartServiceImpl l_impl = new WEB3SrvRegiServiceStartServiceImpl();

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1001));
            WEB3SrvRegiExecRequest l_request = new WEB3SrvRegiExecRequest();
            l_request.serviceDiv = "12";
            l_request.marginTaxDiv = WEB3SrvRegiMarginTaxDivDef.NOT_OPEN;
            l_request.futOpTaxDiv = WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN;
            l_request.institutionCode = "0D";
            l_request.branchCode = "456";

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setOfferingDiv("0");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("456");
            l_tradingTimeParams.setTradingTimeType("27");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setBranchId(381);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("456");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3SrvRegiServiceStartServiceInterceptor l_interceptor =
                new WEB3SrvRegiServiceStartServiceInterceptor();
            l_interceptor.onCall(null, new Object[]{l_request});
            l_impl.execute(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase0002()
    {
        final String STR_METHOD_NAME = "testExecuteCase0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceStartServiceImpl l_impl = new WEB3SrvRegiServiceStartServiceImpl();

        WEB3SrvRegiServiceStartServiceInterceptor l_interceptor =
            new WEB3SrvRegiServiceStartServiceInterceptor();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));

            WEB3SrvRegiExecRequest l_request = new WEB3SrvRegiExecRequest();
            l_request.serviceDiv = "12";
            l_request.marginTaxDiv = WEB3SrvRegiMarginTaxDivDef.NOT_OPEN;
            l_request.futOpTaxDiv = WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN;
            l_request.institutionCode = "0D";
            l_request.branchCode = "456";

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setOfferingDiv("0");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("456");
            l_tradingTimeParams.setTradingTimeType("27");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setBranchId(381);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("456");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("111");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_interceptor.onCall(null, new Object[]{l_request});
            WEB3SrvRegiExecResponse l_response = (WEB3SrvRegiExecResponse)l_impl.execute(l_request);
            assertNull(l_response.srvRegiExecErrDiv);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase0003()
    {
        final String STR_METHOD_NAME = "testExecuteCase0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceStartServiceImpl l_impl = new WEB3SrvRegiServiceStartServiceImpl();

        WEB3SrvRegiServiceStartServiceInterceptor l_interceptor =
            new WEB3SrvRegiServiceStartServiceInterceptor();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));

            WEB3SrvRegiExecRequest l_request = new WEB3SrvRegiExecRequest();
            l_request.serviceDiv = "12";
            l_request.marginTaxDiv = WEB3SrvRegiMarginTaxDivDef.NOT_OPEN;
            l_request.futOpTaxDiv = WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN;
            l_request.institutionCode = "0D";
            l_request.branchCode = "456";
            l_request.applyCheckDiv = true;
            l_request.mobileFlag = null;

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("456");
            l_tradingTimeParams.setTradingTimeType("27");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setBranchId(381);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("456");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("111");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_interceptor.onCall(null, new Object[]{l_request});
            l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00908, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase0004()
    {
        final String STR_METHOD_NAME = "testExecuteCase0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceStartServiceImpl l_impl = new WEB3SrvRegiServiceStartServiceImpl();

        WEB3SrvRegiServiceStartServiceInterceptor l_interceptor =
            new WEB3SrvRegiServiceStartServiceInterceptor();
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));

            WEB3SrvRegiExecRequest l_request = new WEB3SrvRegiExecRequest();
            l_request.serviceDiv = "12";
            l_request.marginTaxDiv = WEB3SrvRegiMarginTaxDivDef.NOT_OPEN;
            l_request.futOpTaxDiv = WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN;
            l_request.institutionCode = "0D";
            l_request.branchCode = "456";
            l_request.applyCheckDiv = true;
            l_request.mobileFlag = "1";

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("456");
            l_tradingTimeParams.setTradingTimeType("27");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setBranchId(381);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("456");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("111");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_interceptor.onCall(null, new Object[]{l_request});
            WEB3SrvRegiExecResponse l_response = (WEB3SrvRegiExecResponse)l_impl.execute(l_request);

            assertEquals("#", l_response.url);
            assertEquals("#", l_response.sendHowToDiv);
            assertEquals("#", l_response.sendParamList[0]);
            assertEquals("1", l_response.srvRegiExecErrDiv);

        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase0005()
    {
        final String STR_METHOD_NAME = "testExecuteCase0005()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceStartServiceImpl l_impl = new WEB3SrvRegiServiceStartServiceImpl();

        WEB3SrvRegiServiceStartServiceInterceptor l_interceptor =
            new WEB3SrvRegiServiceStartServiceInterceptor();
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("111");
            l_mainAccountParams.setSex(WEB3SexDef.CORPORATE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
             "getLoginInfo",
              new Class[] {},
              new LoginInfoTest());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getSessionProperty",
            new Class[] {String.class},
            "1");
            
            
            WEB3SrvRegiExecRequest l_request = new WEB3SrvRegiExecRequest();
            l_request.serviceDiv = "12";
            l_request.marginTaxDiv = WEB3SrvRegiMarginTaxDivDef.NOT_OPEN;
            l_request.futOpTaxDiv = WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN;
            
            l_request.institutionCode = null;
            l_request.branchCode = null;
            
            l_request.applyCheckDiv = true;
            l_request.mobileFlag = "1";

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSpecialProcessDiv(WEB3SpecialProcessDivDef.STREAM);
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("456");
            l_tradingTimeParams.setTradingTimeType("27");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setBranchId(381);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("456");
            TestDBUtility.insertWithDel(l_branchParams);

         
            l_interceptor.onCall(null, new Object[]{l_request});
            WEB3SrvRegiExecResponse l_response = (WEB3SrvRegiExecResponse)l_impl.execute(l_request);

            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02884, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase0006()
    {
        final String STR_METHOD_NAME = "testExecuteCase0006()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceStartServiceImpl l_impl = new WEB3SrvRegiServiceStartServiceImpl();

        WEB3SrvRegiServiceStartServiceInterceptor l_interceptor =
            new WEB3SrvRegiServiceStartServiceInterceptor();
        try
        {
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("111");
            l_mainAccountParams.setSex(WEB3SexDef.MALE);
            l_mainAccountParams.setResident(WEB3ResidentDef.NON_RESIDENT);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
             "getLoginInfo",
              new Class[] {},
              new LoginInfoTest());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getSessionProperty",
            new Class[] {String.class},
            "1");
            
            
            WEB3SrvRegiExecRequest l_request = new WEB3SrvRegiExecRequest();
            l_request.serviceDiv = "12";
            l_request.marginTaxDiv = WEB3SrvRegiMarginTaxDivDef.NOT_OPEN;
            l_request.futOpTaxDiv = WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN;
            
            l_request.institutionCode = null;
            l_request.branchCode = null;
            
            l_request.applyCheckDiv = true;
            l_request.mobileFlag = "1";

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSpecialProcessDiv(WEB3SpecialProcessDivDef.STREAM);
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("456");
            l_tradingTimeParams.setTradingTimeType("27");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setBranchId(381);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("456");
            TestDBUtility.insertWithDel(l_branchParams);

         
            l_interceptor.onCall(null, new Object[]{l_request});
            WEB3SrvRegiExecResponse l_response = (WEB3SrvRegiExecResponse)l_impl.execute(l_request);

            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02708, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase0007()
    {
        final String STR_METHOD_NAME = "testExecuteCase0007()";
        log.entering(STR_METHOD_NAME);

        WEB3SrvRegiServiceStartServiceImpl l_impl = new WEB3SrvRegiServiceStartServiceImpl();

        WEB3SrvRegiServiceStartServiceInterceptor l_interceptor =
            new WEB3SrvRegiServiceStartServiceInterceptor();
        try
        {
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_traderParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setAccountCode("111");
            l_mainAccountParams.setSex(WEB3SexDef.CORPORATE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
             "getLoginInfo",
              new Class[] {},
              new LoginInfoTest());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getSessionProperty",
            new Class[] {String.class},
            "1");
            
            
            WEB3SrvRegiExecRequest l_request = new WEB3SrvRegiExecRequest();
            l_request.serviceDiv = "12";
            l_request.marginTaxDiv = WEB3SrvRegiMarginTaxDivDef.NOT_OPEN;
            l_request.futOpTaxDiv = WEB3SrvRegiFutOpTaxDivDef.FUTURE_OP_NO_OPEN;
            
            l_request.institutionCode = null;
            l_request.branchCode = null;
            
            l_request.applyCheckDiv = true;
            l_request.mobileFlag = "1";

            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvDiv("12");
            l_srvRegiMasterParams.setSrvStatus("1");
            l_srvRegiMasterParams.setInstitutionCode("0D");
            l_srvRegiMasterParams.setSpecialProcessDiv(WEB3SpecialProcessDivDef.STREAM);
            l_srvRegiMasterParams.setOfferingDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("12");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("456");
            l_otherOrgInfoAdminParams.setAccountCode("111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("456");
            l_tradingTimeParams.setTradingTimeType("27");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setBranchId(381);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33L);
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setBranchCode("456");
            TestDBUtility.insertWithDel(l_branchParams);

         
            l_interceptor.onCall(null, new Object[]{l_request});
            WEB3SrvRegiExecResponse l_response = (WEB3SrvRegiExecResponse)l_impl.execute(l_request);
          
 
            assertEquals(l_response.url,"#");
            assertEquals(l_response.sendHowToDiv,"#");
            assertEquals(l_response.sendParamList[0],"#");
            assertEquals(l_response.srvRegiExecErrDiv,WEB3SrvRegiSrvRegiExecErrDivDef.UNAPPLY_ERROR);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
     
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertQuestionAnswerCase0001()
    {
        String STR_METHOD_NAME =
            "insertQuestionAnswer(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //証券会社コード
        String l_strInstitutionCode = "0D";
        
        //部店コード
        String l_strBranchCode = "381";
        
        //識別コード
        String l_strOrderRequestNumber = "000003006";
        
        //質問区分
        String l_strQuestionDiv = "0001";
        
        Object[] obj = new Object[]{
            l_strInstitutionCode,
            l_strBranchCode,
            l_strOrderRequestNumber,
            l_strQuestionDiv};
        try
        {
            TestDBUtility.deleteAll(QuestionAnswerRow.TYPE);
            
            WEB3SrvRegiServiceStartServiceImpl l_impl =
                new WEB3SrvRegiServiceStartServiceImpl();
            
            Method method = WEB3SrvRegiServiceStartServiceImpl.class.getDeclaredMethod(
                "insertQuestionAnswer",
                new Class[]{String.class, String.class, String.class, String.class});
            
            method.setAccessible(true);
            
            Object l_return = method.invoke(l_impl, obj);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public class LoginInfoTest extends LoginInfoImpl
    {
        /**
         *
         * @@return l_lngLoginId
         */
        public long getLoginId()
        {
            long l_lngLoginId = 33381330003L;
            return l_lngLoginId;
        }
    }
}
@
