head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadServiceImplTest_submitUploadFile.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadServiceImplTest_submitUploadFile.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/12 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteRequest;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadServiceImplTest_submitUploadFile extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadServiceImplTest_submitUploadFile.class);

    WEB3AdminSrvRegiAccountDataUploadServiceImpl impl = new WEB3AdminSrvRegiAccountDataUploadServiceImpl();

    public WEB3AdminSrvRegiAccountDataUploadServiceImplTest_submitUploadFile(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Services.overrideService(OpLoginSecurityService.class,
            new OpLoginSecurityServiceImplForMock());
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSubmitUploadFile01()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext",
            l_clendarContext);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "validateAppliPeriod",
            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
            "insertSrvApplyAttribute",
            new Class[] {String.class, String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class},
            new WEB3MockObjectRuntimeException(STR_METHOD_NAME));            

        try
        {
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("3");
            l_srvRegiMasterParams.setSrvDiv("5");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("3");
            l_mainAccountParams.setBranchCode("4");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setAdministratorUploadId(123L);
            l_administratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);

            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
            l_processor.doDeleteAllQuery(AdministratorUploadTempRow.TYPE);
            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue("3,2,3,4,5,6,7,8,20070506,20070506,20070507,12,13,14,20070507");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);
            l_administratorUploadTempParams.setLineNumber(67890);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 4-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
            l_request.uploadId = "123";
            l_request.password = null;
            impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            String l_strResult = l_ex.getMessage();
            int l_intLength = l_strResult.length();
            assertEquals(STR_METHOD_NAME, l_strResult.substring(l_intLength-24, l_intLength));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitUploadFile04()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile04()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext",
            l_clendarContext);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "validateAppliPeriod",
            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
            "insertSrvApplyAttribute",
            new Class[] {String.class, String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class},
            new WEB3MockObjectRuntimeException(STR_METHOD_NAME));            

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("3");
            l_srvRegiMasterParams.setSrvDiv("5");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("3");
            l_mainAccountParams.setBranchCode("4");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setAdministratorUploadId(123L);
            l_administratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);

            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
            l_processor.doDeleteAllQuery(AdministratorUploadTempRow.TYPE);
            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue("0,2,3,4,5,6,7,8,20070506,20070506,20070507,12,13,14,20070507");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);
            l_administratorUploadTempParams.setLineNumber(67890);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 4-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
            l_request.uploadId = "123";
            l_request.password = null;
            impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03019);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitUploadFile05()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile05()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext",
            l_clendarContext);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "validateAppliPeriod",
            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
            "insertSrvApplyAttribute",
            new Class[] {String.class, String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class},
            new WEB3MockObjectRuntimeException(STR_METHOD_NAME));            

        try
        {
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("3");
            l_srvRegiMasterParams.setSrvDiv("5");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("3");
            l_mainAccountParams.setBranchCode("4");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setAdministratorUploadId(123L);
            l_administratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);

            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
            l_processor.doDeleteAllQuery(AdministratorUploadTempRow.TYPE);
            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue("0,2,3,4,5,6,7,8,20070506,20070506,20070507,12,13,14,20070507");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);
            l_administratorUploadTempParams.setLineNumber(67890);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 4-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("5");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("1111111");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
            l_request.uploadId = "123";
            l_request.password = null;
            impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
//            String l_strResult = l_ex.getMessage();
//            int l_intLength = l_strResult.length();
//            assertEquals(STR_METHOD_NAME, l_strResult.substring(l_intLength-24, l_intLength));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitUploadFile06()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile06()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext",
            l_clendarContext);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "validateAppliPeriod",
            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
            "insertSrvApplyAttribute",
            new Class[] {String.class, String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class},
            new WEB3MockObjectRuntimeException(STR_METHOD_NAME));            

        try
        {
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("3");
            l_srvRegiMasterParams.setSrvDiv("5");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("3");
            l_mainAccountParams.setBranchCode("4");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setAdministratorUploadId(123L);
            l_administratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);

            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
            l_processor.doDeleteAllQuery(AdministratorUploadTempRow.TYPE);
            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue("1,2,3,4,5,6,7,8,20070506,20070506,20070507,12,13,14,20070507");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);
            l_administratorUploadTempParams.setLineNumber(67890);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 4-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
            l_request.uploadId = "123";
            l_request.password = null;
            impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03019);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitUploadFile07()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile07()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext",
            l_clendarContext);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "validateAppliPeriod",
            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
            "insertSrvApplyAttribute",
            new Class[] {String.class, String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class},
            new WEB3MockObjectRuntimeException(STR_METHOD_NAME));            

        try
        {
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("3");
            l_srvRegiMasterParams.setSrvDiv("5");
            l_srvRegiMasterParams.setSpecialProcessDiv("1");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("3");
            l_mainAccountParams.setBranchCode("4");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setAdministratorUploadId(123L);
            l_administratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);

            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
            l_processor.doDeleteAllQuery(AdministratorUploadTempRow.TYPE);
            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue("1,2,3,4,5,6,7,8,20070506,20070506,20070507,12,13,14,20070507");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);
            l_administratorUploadTempParams.setLineNumber(67890);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 4-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams = new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSrvDiv("5");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setBranchCode("381");
            l_otherOrgInfoAdminParams.setAccountCode("1111111");
            l_otherOrgInfoAdminParams.setStatus("0");
            l_otherOrgInfoAdminParams.setSequenceNumber(1);
            l_otherOrgInfoAdminParams.setId("1001");
            l_otherOrgInfoAdminParams.setPassword("123");
            l_otherOrgInfoAdminParams.setLastUpdater("1");
            l_otherOrgInfoAdminParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
            l_request.uploadId = "123";
            l_request.password = null;
            impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
//            String l_strResult = l_ex.getMessage();
//            int l_intLength = l_strResult.length();
//            assertEquals(STR_METHOD_NAME, l_strResult.substring(l_intLength-24, l_intLength));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitUploadFile02()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "validateAppliPeriod",
            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
            "updateSrvApplyAttribute",
            new Class[] {String.class, String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class},
            new WEB3MockObjectRuntimeException(STR_METHOD_NAME));            

        try
        {
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("3");
            l_srvRegiMasterParams.setSrvDiv("5");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("3");
            l_mainAccountParams.setBranchCode("4");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setAdministratorUploadId(123L);
            l_administratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);

            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
            l_processor.doDeleteAllQuery(AdministratorUploadTempRow.TYPE);
            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue("3,2,3,4,5,6,7,8,20070506,20070506,20070507,12,13,14,20070507");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);
            l_administratorUploadTempParams.setLineNumber(67890);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            SrvAppliAttributeParams l_srvAppliAttributeParams =  new SrvAppliAttributeParams();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setInstitutionCode("3");
            l_srvAppliAttributeParams.setBranchCode("4");
            l_srvAppliAttributeParams.setAccountCode("6");
            l_srvAppliAttributeParams.setSrvDiv("5");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 4-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
            l_request.uploadId = "123";
            l_request.password = null;
            impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            String l_strResult = l_ex.getMessage();
            int l_intLength = l_strResult.length();
            assertEquals(STR_METHOD_NAME, l_strResult.substring(l_intLength-24, l_intLength));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitUploadFile03()
    {
        final String STR_METHOD_NAME = "testSubmitUploadFile03()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));

        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "validateAppliPeriod",
            new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
            "updateAppliRegist",
            new Class[] {WEB3GentradeSubAccount.class, String.class, Long.class, String.class, String.class, String.class,
                String.class, Timestamp.class, Timestamp.class, Timestamp.class, String.class, String.class, Double.class,
                Timestamp.class, String.class},
            new WEB3MockObjectRuntimeException(STR_METHOD_NAME));            

        try
        {
            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setInstitutionCode("3");
            l_srvRegiMasterParams.setSrvDiv("5");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("3");
            l_mainAccountParams.setBranchCode("4");
            l_mainAccountParams.setAccountCode("6");
            l_mainAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(33381330003L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0602");
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_administratorTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
            TestDBUtility.deleteAllAndCommit(AdministratorUploadParams.TYPE);
            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setBranchCode("381");
            l_administratorUploadParams.setAdministratorUploadId(123L);
            l_administratorUploadParams.setUploadEndTimestamp(null);
            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);

            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
            l_processor.doDeleteAllQuery(AdministratorUploadTempRow.TYPE);
            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123L);
            l_administratorUploadTempParams.setCsvLineValue("1,2,3,4,5,6,7,8,20070506,20070506,20070507,12,13,14,20070507");
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);
            l_administratorUploadTempParams.setLineNumber(67890);
            TestDBUtility.insertWithDel(l_administratorUploadTempParams);

            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 4-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
            l_request.uploadId = "123";
            l_request.password = null;
            impl.submitUploadFile(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            String l_strResult = l_ex.getMessage();
            int l_intLength = l_strResult.length();
            assertEquals(STR_METHOD_NAME, l_strResult.substring(l_intLength-24, l_intLength));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
//    public void testSubmitUploadFile001()
//    {
//        final String STR_METHOD_NAME = "testSubmitUploadFile001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//            
//
//        try
//        {
//            TestDBUtility.deleteAll(SrvRegiMasterParams.TYPE);
//            SrvRegiMasterParams l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
//            l_srvRegiMasterParams.setInstitutionCode("3");
//            l_srvRegiMasterParams.setSrvDiv("5");
//            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
//            MainAccountParams l_mainAccountParams = new MainAccountParams();
//            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
//            l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setInstitutionCode("3");
//            l_mainAccountParams.setBranchCode("4");
//            l_mainAccountParams.setAccountCode("6");
//            l_mainAccountParams.setAccountId(33381330003L);
//            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
//            log.debug("1");
//
//            
//            SubAccountParams l_subAccountParams = new SubAccountParams();
//            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
//            l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            l_subAccountParams.setAccountId(33381330003L);
//            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
//            log.debug("2");
//            
//            
//            AdministratorParams l_administratorParams = new AdministratorParams();
//            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
//            l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
//            log.debug("3");
//            
//            
//            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
//            TestDBUtility.deleteAllAndCommit(AdminPermissionRow.TYPE);
//            l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setInstitutionCode("0D");
//            l_adminPermissionParams.setPermissionLevel("331");
//            l_adminPermissionParams.setTransactionCategory("C0602");
//            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);
//            log.debug("4");
//
//            AdministratorTypeParams l_administratorTypeParams = new AdministratorTypeParams();
//            TestDBUtility.deleteAllAndCommit(AdministratorTypeRow.TYPE);
//            l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
//            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
//            l_administratorTypeParams.setDirAdminFlag(1);
//            TestDBUtility.insertWithDelAndCommit(l_administratorTypeParams);
//
//            AdministratorUploadParams l_administratorUploadParams = new AdministratorUploadParams();
//            TestDBUtility.deleteAllAndCommit(AdministratorUploadRow.TYPE);
//            l_administratorUploadParams = TestDBUtility.getAdministratorUploadRow();
//            l_administratorUploadParams.setBranchCode("381");
//            l_administratorUploadParams.setAdministratorUploadId(123L);
//            l_administratorUploadParams.setUploadEndTimestamp(null);
//            TestDBUtility.insertWithDelAndCommit(l_administratorUploadParams);
//
//            AdministratorUploadTempParams l_administratorUploadTempParams = new AdministratorUploadTempParams();
//            TestDBUtility.deleteAllAndCommit(AdministratorUploadTempRow.TYPE);
//            l_administratorUploadTempParams = TestDBUtility.getAdministratorUploadTempRow();
//            l_administratorUploadTempParams.setAdministratorUploadId(123L);
//            l_administratorUploadTempParams.setCsvLineValue("3,2,3,4,5,6,7,3,20070506,20070506,20070507,12,13,14,20070507");
//            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams);
//            l_administratorUploadTempParams.setLineNumber(67890);
//            TestDBUtility.insertWithDelAndCommit(l_administratorUploadTempParams);
//
////            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
////            TestDBUtility.deleteAllAndCommit(SrvAppliAttributeRow.TYPE);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getLoginInfo",
//                    new Class[] {},
//                    new LoginInfoImplForMock());
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
//                    "getLoginTypeId",
//                    new Class[] {},
//                    new Long(0L));
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(33381330003L));
//
//                HashMap l_map = new HashMap();
//                l_map.put("TRADING_PWD_ENV", "0");
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
//                    "getLoginTypeAttributes",
//                    new Class[] {long.class},
//                    l_map);
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "checkPassword",
//                    new Class[] {String.class},
//                    Boolean.TRUE);
//                WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//                l_clendarContext.setInstitutionCode("0D");
//                l_clendarContext.setBranchCode("381");
//                ThreadLocalSystemAttributesRegistry.setAttribute(
//                    "web3.tradingcalendarcontext",
//                    l_clendarContext);
//
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
//                    "validateAppliPeriod",
//                    new Class[] {String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class, Long.class},
//                    null);
//
//                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl",
//                    "insertSrvApplyAttribute",
//                    new Class[] {String.class, String.class, String.class, String.class, String.class, Timestamp.class, Timestamp.class},
//                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));       
//                
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 4-1, 14);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);
//
//            WEB3AdminSrvRegiUploadCompleteRequest l_request = new WEB3AdminSrvRegiUploadCompleteRequestForTest();
//            l_request.uploadId = "123";
//            l_request.password = null;
//            impl.submitUploadFile(l_request);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug("***************************");
//            log.debug(STR_METHOD_NAME,l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02784, l_ex.getErrorInfo());
//        }
//        catch (WEB3MockObjectRuntimeException l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            String l_strResult = l_ex.getMessage();
//            int l_intLength = l_strResult.length();
//            assertEquals(STR_METHOD_NAME, l_strResult.substring(l_intLength-24, l_intLength));
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    class WEB3AdminSrvRegiUploadCompleteRequestForTest extends WEB3AdminSrvRegiUploadCompleteRequest
    {
        public void validate()
        {
            return;
        }
    }

}
@
