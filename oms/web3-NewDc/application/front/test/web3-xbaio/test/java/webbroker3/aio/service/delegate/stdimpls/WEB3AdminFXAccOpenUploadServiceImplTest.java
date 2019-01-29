head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccOpenUploadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

public class WEB3AdminFXAccOpenUploadServiceImplTest extends JunitTestBase
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadServiceImplTest.class);

    WEB3AdminFXAccOpenUploadServiceImpl l_impl;

    public WEB3AdminFXAccOpenUploadServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3AdminFXAccOpenUploadServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testSubmitUpload_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitUpload_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFXAccOpenUploadCompleteRequest l_request =
                new WEB3AdminFXAccOpenUploadCompleteRequest();
            l_request.uploadId = "123456789";
            
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //AdministratorParams
            l_queryProcessor.doDeleteAllQuery(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("624");
            l_administratorParams.setAdministratorCode("321654");
            l_queryProcessor.doInsertQuery(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //AdminPermissionRow
            l_queryProcessor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administrator.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administrator.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory("B0401");
            l_queryProcessor.doInsertQuery(l_adminPermissionParams);

            //AdministratorTypeParams
            l_queryProcessor.doDeleteAllQuery(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_administratorTypeParams =
                TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_queryProcessor.doInsertQuery(l_administratorTypeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {},
                new Long(123456));

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
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            //AdministratorUploadParams
            TestDBUtility.deleteAll(AdministratorUploadParams.TYPE);
            AdministratorUploadParams l_administratorUploadParams=
                TestDBUtility.getAdministratorUploadRow();
            l_administratorUploadParams.setAdministratorUploadId(123456789);
            TestDBUtility.insertWithDel(l_administratorUploadParams);
            
            //AdministratorUploadTempParams
            TestDBUtility.deleteAll(AdministratorUploadTempParams.TYPE);
            AdministratorUploadTempParams l_administratorUploadTempParams =
                TestDBUtility.getAdministratorUploadTempRow();
            l_administratorUploadTempParams.setAdministratorUploadId(123456789);
            l_administratorUploadTempParams.setLineNumber(1);
            l_administratorUploadTempParams.setCsvLineValue("market,product,branch");
            l_queryProcessor.doInsertQuery(l_administratorUploadTempParams);
            
            l_administratorUploadTempParams.setLineNumber(2);
            String jiddk = "11111073123,jidk,11073123,";
            for (int i=0; i<64 ;i++)
            {
                if (i == 63)
                {
                    jiddk += "market";
                }
                else
                {
                    jiddk += "market,";
                }
            }
            l_administratorUploadTempParams.setCsvLineValue(jiddk);
            l_queryProcessor.doInsertQuery(l_administratorUploadTempParams);
            
            //CompFxConditionParams
            l_queryProcessor.doDeleteAllQuery(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setAccType("01");
            l_compFxConditionParams.setAccOpenRealUpdate("0");
            l_compFxConditionParams.setFxHeadOfLoginId("111");
            l_queryProcessor.doInsertQuery(l_compFxConditionParams);
            
            l_queryProcessor.doDeleteAllQuery(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("073123");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("123");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("0");
            l_gftAccountOpenStatusParams.setAgreementDiv("2");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            l_gftAccountOpenStatusParams.setLoginId("111307123");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("market");
            l_gftAccountOpenStatusParams.setLoginId("111111111073123");
            l_gftAccountOpenStatusParams.setAccountOpenStatusDiv("3");
            l_gftAccountOpenStatusParams.setAgreementDiv("2");
            l_gftAccountOpenStatusParams.setLastName("jidk");
            l_gftAccountOpenStatusParams.setMailAddress("market");
            l_queryProcessor.doInsertQuery(l_gftAccountOpenStatusParams);
            
            //MainAccountParams
            l_queryProcessor.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("073123");
            l_queryProcessor.doInsertQuery(l_mainAccountParams);

            l_queryProcessor.doDeleteAllQuery(FxAccountRow.TYPE);
            l_queryProcessor.doDeleteAllQuery(AccOpenDivRow.TYPE);
            l_impl.submitUpload(l_request);
            List l_lisResult = l_queryProcessor.doFindAllQuery(AccOpenDivRow.TYPE);
            assertEquals(l_lisResult.size(), 1);
            AccOpenDivRow l_accOpenDivRow =
                (AccOpenDivRow)l_lisResult.get(0);
            assertEquals(l_accOpenDivRow.getAccountId(), l_mainAccountParams.getAccountId());
            assertEquals(l_accOpenDivRow.getAccType(), "01");
            assertEquals(l_accOpenDivRow.getAccOpenDiv(), "1");
            assertEquals(l_accOpenDivRow.getLastUpdater(), "321654");
            
            List l_lisResult1 = l_queryProcessor.doFindAllQuery(MainAccountParams.TYPE);
            assertEquals(l_lisResult1.size(), 1);
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_lisResult1.get(0);
            assertEquals(l_mainAccountRow.getFxAccOpenDiv(), "1");
            assertEquals(l_mainAccountRow.getFxAccOpenDivLastUpdater(), "321654");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
