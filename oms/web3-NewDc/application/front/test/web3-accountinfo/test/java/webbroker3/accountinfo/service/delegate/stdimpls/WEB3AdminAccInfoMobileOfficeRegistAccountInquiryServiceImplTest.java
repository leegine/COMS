head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/12 吉麗ナ (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistPK;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest;
import webbroker3.accountopen.data.TradeConditionVoucherParams;
import webbroker3.accountopen.data.TradeConditionVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstPK;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImplTest extends TestBaseForMock
{
    /**
     * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスImpl<BR>
     */
    private WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl l_adminAccInfoMobileOfficeRegistAccountInquiryServiceImpl = 
        new WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl();


    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImplTest(String arg0)
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

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImplTest.class);

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.submitJudgement(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)'
     */
    public void test_submitJudgement_C0001()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest l_request =
            new WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest();

        String[] l_strBranchCode = new String[1];
        l_strBranchCode[0] = "000";
        String[] l_strAccountCode = new String[1];
        l_strAccountCode[0] = "5384010";
        l_request.password = "123";
        l_request.branchCode = l_strBranchCode;
        l_request.accountCode = l_strAccountCode;
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
        WEB3Administrator l_administrator =
            new WEB3Administrator(l_administratorParams);

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33L);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountId(333812512246L);
        l_mainAccountParams.setAccountCode("123456");
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        try
        {
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            this.dataBasePrepare();

            AccountInfoMstParams l_AccountInfoMstParams = new AccountInfoMstParams();
            TestDBUtility.deleteAll(l_AccountInfoMstParams.getRowType());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }


        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_mainAccount);
        WEB3AdministratorForMock.mockValidateTradingPassword("1111" , true);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
        try
        {
            l_adminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.submitJudgement(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.submitJudgement(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)'
     */
    public void test_submitJudgement_C0002()
    {

        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
            new Class[] {},
            this.getTimeStamp(2007, 1, 1));
        WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest l_request =
            new WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest();

        String[] l_strBranchCode = new String[1];
        l_strBranchCode[0] = "000";
        String[] l_strAccountCode = new String[1];
        l_strAccountCode[0] = "5384010";
        l_request.password = "123";
        l_request.branchCode = l_strBranchCode;
        l_request.accountCode = l_strAccountCode;
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
        WEB3Administrator l_web3AdministratorForTest =
            new WEB3Administrator(l_administratorParams);

        MainAccountParams l_mainAccountParams = new MainAccountParams();
        l_mainAccountParams.setInstitutionId(33L);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setBranchId(33381);
        l_mainAccountParams.setAccountId(333812512246L);
        l_mainAccountParams.setAccountCode("123456");
        l_mainAccountParams.setInstitutionCode("0D");
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
        WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_web3AdministratorForTest);
            this.dataBasePrepare();

            //BranchPreferencesParams
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("occupationcode.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            AccountInfoMstParams l_AccountInfoMstParams = this.getAccountInfoMstRows();
            TestDBUtility.deleteAll(l_AccountInfoMstParams.getRowType());
            TestDBUtility.insertWithDel(l_AccountInfoMstParams);

            TradeConditionVoucherParams l_tradeConditionVoucherParams = new TradeConditionVoucherParams();
            l_tradeConditionVoucherParams.acc_open_request_number = "1001";
            TestDBUtility.deleteAll(l_tradeConditionVoucherParams.getRowType());

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        try
        {
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_mainAccount);
        WEB3AdministratorForMock.mockValidateTradingPassword("1111" , true);
        try
        {
            //submit一括判定
            l_adminAccInfoMobileOfficeRegistAccountInquiryServiceImpl.submitJudgement(l_request);
            //受付結果区分
            MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams =
                (MobileOfficeInfoRegistParams) Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(
                    new MobileOfficeInfoRegistPK(1001L));
            assertEquals("0", l_mobileOfficeInfoRegistParams.accept_status);

            //職業更新日時
            AccountInfoMstParams l_accountInfoMstParams =
                (AccountInfoMstParams) Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(
                    new AccountInfoMstPK(333812512246L));
            WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate("20070101","yyyyMMdd"),
                l_accountInfoMstParams.occupation_updated_timestamp);
            //create検索条件文字列
            String l_strSearchCondition = " request_code = ? " +
                    "and institution_code = ? and branch_code = ? and account_code = ?";
            //create検索条件データコンテナ
            Object[] l_strSearchConditionContainers = new Object[4];
            l_strSearchConditionContainers[0] = "GI844";
            l_strSearchConditionContainers[1] = "0D";
            l_strSearchConditionContainers[2] = "381";
            l_strSearchConditionContainers[3] = "123456";
            
            List l_lisTradeConditionVoucherList = Processors.getDefaultProcessor().doFindAllQuery(TradeConditionVoucherRow.TYPE,
                    l_strSearchCondition,
                    null,
                    l_strSearchConditionContainers);
            assertEquals(1, l_lisTradeConditionVoucherList.size());
            TradeConditionVoucherRow l_TradeConditionVoucher = (TradeConditionVoucherRow)l_lisTradeConditionVoucherList.get(0);
            assertEquals("GI844", l_TradeConditionVoucher.getRequestCode());
            assertEquals("0D", l_TradeConditionVoucher.getInstitutionCode());
            assertEquals("381", l_TradeConditionVoucher.getBranchCode());
            assertEquals("123456", l_TradeConditionVoucher.getAccountCode());
            assertEquals(null, l_TradeConditionVoucher.getTraderCode());
            assertEquals("0", l_TradeConditionVoucher.getAccOpenRequestNumber());
            assertEquals("0", l_TradeConditionVoucher.getSerialNo());
            assertEquals("02", l_TradeConditionVoucher.getDataClass());
            assertEquals(null, l_TradeConditionVoucher.getTradingEReportDiv());
            assertEquals(null, l_TradeConditionVoucher.getInvEReportDiv());
            assertEquals("0", l_TradeConditionVoucher.getRefundEReportDiv());
            assertEquals(null, l_TradeConditionVoucher.getEReportDiv1());
            assertEquals(null, l_TradeConditionVoucher.getEReportDiv2());
            assertEquals(null, l_TradeConditionVoucher.getEReportDiv3());
            assertEquals(null, l_TradeConditionVoucher.getPosReportTermDiv());
            assertEquals(null, l_TradeConditionVoucher.getPosReportCycleDiv());
            assertEquals(null, l_TradeConditionVoucher.getPosReportCertifDepoDiv());
            assertEquals(null, l_TradeConditionVoucher.getPosReportAccStateDiv());
            assertEquals(null, l_TradeConditionVoucher.getEquityTaxDiv());
            assertEquals(null, l_TradeConditionVoucher.getEquitySpAccOpenDat());
            assertEquals(null, l_TradeConditionVoucher.getEquityTaxDivNext());
            assertEquals(null, l_TradeConditionVoucher.getMarginTaxDiv());
            assertEquals(null, l_TradeConditionVoucher.getMarginTaxDivNext());
            assertEquals(null, l_TradeConditionVoucher.getMarginSpAccOpenDat());
            assertEquals(null, l_TradeConditionVoucher.getSpMngAccOpenDiv());
            assertEquals(null, l_TradeConditionVoucher.getSendTimestamp());
            assertEquals("0", l_TradeConditionVoucher.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    private void dataBasePrepare()
    {
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

        try
        {
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

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

    }

    /**AccountInfoMst*/
    private AccountInfoMstParams getAccountInfoMstRows()
    {
        AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams();

        l_accountInfoMstParams.setAccountId(333812512246L);
        l_accountInfoMstParams.setInstitutionCode("0D");
        l_accountInfoMstParams.setBranchCode("381");
        l_accountInfoMstParams.setAccountCode("123456");
        l_accountInfoMstParams.setOccupationDiv("2");
        l_accountInfoMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
        l_accountInfoMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
        return l_accountInfoMstParams;
    }

    /**Timestamp*/
    private Timestamp getTimeStamp(int year, int month, int day)
    {
        Calendar l_canlendar = Calendar.getInstance();
        l_canlendar.set(Calendar.YEAR, year);
        l_canlendar.set(Calendar.DAY_OF_MONTH, month);
        l_canlendar.set(Calendar.DAY_OF_YEAR, day);
        Timestamp l_tsTimeStamp = new Timestamp(l_canlendar.getTimeInMillis());
        return l_tsTimeStamp;
    }

}
@
