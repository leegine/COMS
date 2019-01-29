head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoMobileOfficeChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更サービスImpl(WEB3AdminAccInfoMobileOfficeChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 謝旋 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistPK;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoMobileOfficeChangeServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeChangeServiceImplTest.class);

    public WEB3AdminAccInfoMobileOfficeChangeServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testSubmitChange_0001()
    {
        String STR_METHOD_NAME = "testSubmitChange_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistCompleteRequestForTest l_request = 
            new WEB3AdminAccInfoMobileOfficeRegistCompleteRequestForTest();
        WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();
        changedMobileOfficeInfo.contactPriority1 = null;
        changedMobileOfficeInfo.contactPriority2 = null;
        changedMobileOfficeInfo.mobileTelephone = "1";
        changedMobileOfficeInfo.officeName = "1";
        changedMobileOfficeInfo.officeZipCode = "1";
        changedMobileOfficeInfo.officeAdress = "1";
        changedMobileOfficeInfo.officeTelephone = "1";
        changedMobileOfficeInfo.position = "1";
        changedMobileOfficeInfo.contactPriority3 = "1";
        changedMobileOfficeInfo.accountRealName1 = "1";
        changedMobileOfficeInfo.accountRealName2 = "1";
        changedMobileOfficeInfo.occupationDiv = "1";
        changedMobileOfficeInfo.department = "1";
        changedMobileOfficeInfo.nationality = "1";
        changedMobileOfficeInfo.nationalityOther = "1";
        changedMobileOfficeInfo.representFamilyName = "1";
        changedMobileOfficeInfo.representName = "1";
        changedMobileOfficeInfo.representFamilyNameKana = "1";
        changedMobileOfficeInfo.representNameKana = "1";
        changedMobileOfficeInfo.representPower = "1";
        changedMobileOfficeInfo.directorFamilyName = "1";
        changedMobileOfficeInfo.directorName = "1";
        changedMobileOfficeInfo.directorFamilyNameKana = "1";
        changedMobileOfficeInfo.directorNameKana = "1";
        changedMobileOfficeInfo.directorDepartment = "1";
        changedMobileOfficeInfo.directorPosition = "1";
        changedMobileOfficeInfo.directorZipCode = "1";
        changedMobileOfficeInfo.directorAddress1 = "1";
        changedMobileOfficeInfo.directorAddress2 = "1";
        changedMobileOfficeInfo.directorAddress3 = "1";
        changedMobileOfficeInfo.directorEraBorn = "1";
        changedMobileOfficeInfo.directorBornDate = "1";
        changedMobileOfficeInfo.directorCorpDirect = "1";
        changedMobileOfficeInfo.directorOtherContact = "1";
        changedMobileOfficeInfo.faxTelephone = "1";
        changedMobileOfficeInfo.annualIncomeDiv = "1";
        changedMobileOfficeInfo.assetValueDiv = "1";
        changedMobileOfficeInfo.fundBundgetAmountDiv = "1";
        changedMobileOfficeInfo.investPurposeDiv = "1";
        changedMobileOfficeInfo.investPlanPeriodDiv = "1";
        changedMobileOfficeInfo.experienceDiv1 = "1";
        changedMobileOfficeInfo.experienceDiv2 = "1";
        changedMobileOfficeInfo.experienceDiv3 = "1";
        changedMobileOfficeInfo.experienceDiv4 = "1";
        changedMobileOfficeInfo.experienceDiv5 = "1";
        changedMobileOfficeInfo.experienceDiv6 = "1";
        changedMobileOfficeInfo.experienceDiv7 = "1";
        changedMobileOfficeInfo.experienceDiv8 = "1";
        changedMobileOfficeInfo.experienceDiv9 = "1";
        changedMobileOfficeInfo.experienceDiv10 = "1";
        changedMobileOfficeInfo.appliMotivatDiv = "1";
        changedMobileOfficeInfo.appliMotivatDetail = "1";
        changedMobileOfficeInfo.useInstitutionDiv = "1";
        changedMobileOfficeInfo.internetTradeDiv = "1";
        changedMobileOfficeInfo.introduceBranch = "1";
        l_request.changedMobileOfficeInfo = changedMobileOfficeInfo;
        l_request.password = "1111";
        l_request.branchCode = "381";
        l_request.accountCode = "";
        l_request.judgmentResultDiv = "1";
        
        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setAdministratorCode("1234567");
        l_administratorParams.setAdministratorId(123456L);
        l_administratorParams.setInstitutionId(33L);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setBranchId(33381);
        l_administratorParams.setLoginId(123L);
        l_administratorParams.setPermissionLevel("770");
        WEB3AdministratorForTest l_web3AdministratorForTest = 
            new WEB3AdministratorForTest(l_administratorParams);
        
        AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setTransactionCategory("A0101");
        
        AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("770");
        
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = TestDBUtility.getMobileOfficeInfoRegistRow();
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setInstitutionId(33L);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountId(333812512246L);//333812512246L
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
        try
        {
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_web3AdministratorForTest);
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(l_administratorTypeParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(l_mobileOfficeInfoRegistParams.getRowType());
            TestDBUtility.insertWithDel(l_mobileOfficeInfoRegistParams);
            
            TestDBUtility.deleteAll(l_subAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams();
            TestDBUtility.deleteAll(l_accountInfoMstParams.getRowType());
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_mainAccount);
        WEB3AdministratorForMock.mockValidateTradingPassword("1111" , true);
        try
        {
            WEB3AdminAccInfoMobileOfficeChangeServiceImpl l_web3AdminAccInfoMobileOfficeChangeServiceImpl = 
                new WEB3AdminAccInfoMobileOfficeChangeServiceImpl();
            WEB3AdminAccInfoMobileOfficeRegistCompleteResponse response = 
                l_web3AdminAccInfoMobileOfficeChangeServiceImpl.submitChange(l_request);
            
            assertEquals(WEB3AdminAccInfoMobileOfficeRegistCompleteResponse.class , response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testSubmitChange_0002()
    {
        String STR_METHOD_NAME = "testSubmitChange_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistCompleteRequestForTest l_request = 
            new WEB3AdminAccInfoMobileOfficeRegistCompleteRequestForTest();
        WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();
        changedMobileOfficeInfo.contactPriority1 = null;
        changedMobileOfficeInfo.contactPriority2 = null;
        changedMobileOfficeInfo.mobileTelephone = "1";
        changedMobileOfficeInfo.officeName = "1";
        changedMobileOfficeInfo.officeZipCode = "1";
        changedMobileOfficeInfo.officeAdress = "1";
        changedMobileOfficeInfo.officeTelephone = "1";
        changedMobileOfficeInfo.position = "1";
        changedMobileOfficeInfo.contactPriority3 = "1";
        changedMobileOfficeInfo.accountRealName1 = "1";
        changedMobileOfficeInfo.accountRealName2 = "1";
        changedMobileOfficeInfo.occupationDiv = "2";
        changedMobileOfficeInfo.department = "1";
        changedMobileOfficeInfo.nationality = "1";
        changedMobileOfficeInfo.nationalityOther = "1";
        changedMobileOfficeInfo.representFamilyName = "1";
        changedMobileOfficeInfo.representName = "1";
        changedMobileOfficeInfo.representFamilyNameKana = "1";
        changedMobileOfficeInfo.representNameKana = "1";
        changedMobileOfficeInfo.representPower = "1";
        changedMobileOfficeInfo.directorFamilyName = "1";
        changedMobileOfficeInfo.directorName = "1";
        changedMobileOfficeInfo.directorFamilyNameKana = "1";
        changedMobileOfficeInfo.directorNameKana = "1";
        changedMobileOfficeInfo.directorDepartment = "1";
        changedMobileOfficeInfo.directorPosition = "1";
        changedMobileOfficeInfo.directorZipCode = "1";
        changedMobileOfficeInfo.directorAddress1 = "1";
        changedMobileOfficeInfo.directorAddress2 = "1";
        changedMobileOfficeInfo.directorAddress3 = "1";
        changedMobileOfficeInfo.directorEraBorn = "1";
        changedMobileOfficeInfo.directorBornDate = "1";
        changedMobileOfficeInfo.directorCorpDirect = "1";
        changedMobileOfficeInfo.directorOtherContact = "1";
        changedMobileOfficeInfo.faxTelephone = "1";
        changedMobileOfficeInfo.annualIncomeDiv = "1";
        changedMobileOfficeInfo.assetValueDiv = "1";
        changedMobileOfficeInfo.fundBundgetAmountDiv = "1";
        changedMobileOfficeInfo.investPurposeDiv = "1";
        changedMobileOfficeInfo.investPlanPeriodDiv = "1";
        changedMobileOfficeInfo.experienceDiv1 = "1";
        changedMobileOfficeInfo.experienceDiv2 = "1";
        changedMobileOfficeInfo.experienceDiv3 = "1";
        changedMobileOfficeInfo.experienceDiv4 = "1";
        changedMobileOfficeInfo.experienceDiv5 = "1";
        changedMobileOfficeInfo.experienceDiv6 = "1";
        changedMobileOfficeInfo.experienceDiv7 = "1";
        changedMobileOfficeInfo.experienceDiv8 = "1";
        changedMobileOfficeInfo.experienceDiv9 = "1";
        changedMobileOfficeInfo.experienceDiv10 = "1";
        changedMobileOfficeInfo.appliMotivatDiv = "1";
        changedMobileOfficeInfo.appliMotivatDetail = "1";
        changedMobileOfficeInfo.useInstitutionDiv = "1";
        changedMobileOfficeInfo.internetTradeDiv = "1";
        changedMobileOfficeInfo.introduceBranch = "1";
        l_request.changedMobileOfficeInfo = changedMobileOfficeInfo;
        l_request.password = "1111";
        l_request.branchCode = "381";
        l_request.accountCode = "";
        l_request.judgmentResultDiv = "1";
        
        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setAdministratorCode("1234567");
        l_administratorParams.setAdministratorId(123456L);
        l_administratorParams.setInstitutionId(33L);
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setBranchCode("381");
        l_administratorParams.setBranchId(33381);
        l_administratorParams.setLoginId(123L);
        l_administratorParams.setPermissionLevel("770");
        WEB3AdministratorForTest l_web3AdministratorForTest = 
            new WEB3AdministratorForTest(l_administratorParams);
        
        AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setTransactionCategory("A0101");
        
        AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
        l_administratorTypeParams.setInstitutionCode("0D");
        l_administratorTypeParams.setPermissionLevel("770");
        
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = TestDBUtility.getMobileOfficeInfoRegistRow();
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setInstitutionId(33L);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountId(333812512246L);//333812512246L
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
        
        BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
        l_branchPreferencesParams.setBranchId(33381);
        l_branchPreferencesParams.setName("occupationcode.update");
        l_branchPreferencesParams.setNameSerialNo(1);
        
        AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams();
        l_accountInfoMstParams.setAccountId(333812512246L);
        l_accountInfoMstParams.setInstitutionCode("0D");
        l_accountInfoMstParams.setBranchCode("381");
        l_accountInfoMstParams.setAccountCode("2512246");
        l_accountInfoMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20060811","yyyyMMdd"));
        l_accountInfoMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        try
        {
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_web3AdministratorForTest);
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(l_adminPermissionParams.getRowType());
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            TestDBUtility.deleteAll(l_administratorTypeParams.getRowType());
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            TestDBUtility.deleteAll(l_mobileOfficeInfoRegistParams.getRowType());
            TestDBUtility.insertWithDel(l_mobileOfficeInfoRegistParams);
            
            TestDBUtility.deleteAll(l_subAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(l_accountInfoMstParams.getRowType());
            TestDBUtility.insertWithDel(l_accountInfoMstParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_mainAccount);
        WEB3AdministratorForMock.mockValidateTradingPassword("1111" , true);
        try
        {
            WEB3AdminAccInfoMobileOfficeChangeServiceImpl l_web3AdminAccInfoMobileOfficeChangeServiceImpl = 
                new WEB3AdminAccInfoMobileOfficeChangeServiceImpl();
            l_web3AdminAccInfoMobileOfficeChangeServiceImpl.submitChange(l_request);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            MobileOfficeInfoRegistRow l_MobileOfficeInfoRegistRow = 
                (MobileOfficeInfoRegistRow) l_processor.doFindByPrimaryKeyQuery(new MobileOfficeInfoRegistPK(1001L));
            assertEquals(null , l_MobileOfficeInfoRegistRow.getAcceptStatus());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public class WEB3AdministratorForTest extends WEB3Administrator
    {

        public WEB3AdministratorForTest(AdministratorParams l_administratorParams)
        {
            super(l_administratorParams);
        }
        
        public void validateAuthority(String l_strTransactionCategory,boolean l_isUpdate)
        throws WEB3BaseException
        {
                
        }
        
        public void validateTradingPassword(String l_strPassword)
        throws WEB3BaseException
        {
                
        }
        
        public String getInstitutionCode()
        {
            return "12";
        }
        
        public void validateBranchPermission(String l_strBranchCode)
        throws WEB3BaseException
        {
                
        }
        
        public String getAdministratorCode()
        {
            return "12";
        }
    }
    
    public class WEB3AdminAccInfoMobileOfficeRegistCompleteRequestForTest extends WEB3AdminAccInfoMobileOfficeRegistCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }

}
@
