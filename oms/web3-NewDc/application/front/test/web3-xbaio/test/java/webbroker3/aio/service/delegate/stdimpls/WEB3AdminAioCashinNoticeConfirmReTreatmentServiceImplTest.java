head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeChangeInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashinNoticeSearchRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeUnit2;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.EraParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImplTest.class);
    WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl l_impl;
    public WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_impl = new WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetCashinNoticeChangeInputScreen_T01()
    {
        final String STR_METHOD_NAME = "testGetCashinNoticeChangeInputScreen_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAioCashinNoticeChangeInputRequest l_request =
                new WEB3AdminAioCashinNoticeChangeInputRequest();
            String l_strTableID = "123456789";
            l_request.cashinNoticeTableId = new String[]{l_strTableID};

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
            l_adminPermissionParams.setTransactionCategory("B0102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //BankDepositNotifyParams
            TestDBUtility.deleteAll(BankDepositNotifyParams.TYPE);
            BankDepositNotifyParams l_notifyParams = this.getBankDepositNotifyParams();
            l_notifyParams.setBankDepositNotifyId(23456789l);
            l_notifyParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_notifyParams.setDataLoadDiv("1");
            l_notifyParams.setStatus("1");
            TestDBUtility.insertWithDel(l_notifyParams);

            l_impl.getCashinNoticeChangeInputScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02839, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "---------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCashinNoticeChange_T01()
    {
        final String STR_METHOD_NAME = "testValidateCashinNoticeChange_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAioCashinNoticeChangeConfirmRequest l_request =
                new WEB3AdminAioCashinNoticeChangeConfirmRequest();
            WEB3AioCashinNoticeUnit2[] l_unit = new WEB3AioCashinNoticeUnit2[1];
            l_unit[0] = new WEB3AioCashinNoticeUnit2();
            l_unit[0].branchCode = "624";
            l_unit[0].accountCode = "123456";
            l_unit[0].cashinNoticeTableId = "123456789";
            l_request.cashinNoticeList = l_unit;
            
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
            l_adminPermissionParams.setTransactionCategory("B0102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //BankDepositNotifyParams
            TestDBUtility.deleteAll(BankDepositNotifyParams.TYPE);
            BankDepositNotifyParams l_notifyParams = this.getBankDepositNotifyParams();
            l_notifyParams.setBankDepositNotifyId(23456789l);
            l_notifyParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_notifyParams.setDataLoadDiv("1");
            l_notifyParams.setStatus("1");
            TestDBUtility.insertWithDel(l_notifyParams);
            
            l_impl.validateCashinNoticeChange(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02839, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitCashinNoticeChange_T01()
    {
        final String STR_METHOD_NAME = "testSubmitCashinNoticeChange_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminAioCashinNoticeChangeCompleteRequest l_request =
                new WEB3AdminAioCashinNoticeChangeCompleteRequest();
            WEB3AioCashinNoticeUnit2[] l_unit = new WEB3AioCashinNoticeUnit2[1];
            l_unit[0] = new WEB3AioCashinNoticeUnit2();
            l_unit[0].branchCode = "624";
            l_unit[0].accountCode = "123456";
            l_unit[0].cashinNoticeTableId = "123456789";
            l_request.cashinNoticeList = l_unit;
            
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
            l_adminPermissionParams.setTransactionCategory("B0102");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //BankDepositNotifyParams
            TestDBUtility.deleteAll(BankDepositNotifyParams.TYPE);
            BankDepositNotifyParams l_notifyParams = this.getBankDepositNotifyParams();
            l_notifyParams.setBankDepositNotifyId(23456789l);
            l_notifyParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_notifyParams.setDataLoadDiv("1");
            l_notifyParams.setStatus("1");
            TestDBUtility.insertWithDel(l_notifyParams);
            
            l_impl.submitCashinNoticeChange(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02839, l_exc.getErrorInfo());
            log.debug(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateCashinNoticeUnit_Case001()
    {
        final String STR_METHOD_NAME = "testCreateCashinNoticeUnit_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BankDepositNotifyParams.TYPE);
            BankDepositNotifyParams l_params = this.getBankDepositNotifyParams();
            l_params.setDepositDataAccountDate("01w107");
            l_params.setAccountCode("251224");
            l_params.setBranchCode("624");
            l_params.setDepositDataDepositAmount("12");
            TestDBUtility.insertWithDel(l_params);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode(l_params.getInstitutionCode());
            l_mainAccountParams.setBranchCode(l_params.getBranchCode());
            l_mainAccountParams.setAccountCode(l_params.getAccountCode());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount =
                l_accountManager.getMainAccount(
                    l_params.getInstitutionCode(),
                    l_params.getBranchCode(),
                    l_params.getAccountCode());

            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AioCashinNoticeUnit2 l_noticeUnit =
                l_impl.createCashinNoticeUnit(l_params, null);
            assertNull(l_noticeUnit.settlementDate);

        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateCashinNoticeUnit_Case002()
    {
        final String STR_METHOD_NAME = "testCreateCashinNoticeUnit_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BankDepositNotifyParams.TYPE);
            BankDepositNotifyParams l_params = this.getBankDepositNotifyParams();
            l_params.setDepositDataAccountDate("010107");
            l_params.setAccountCode("251224");
            l_params.setBranchCode("624");
            l_params.setDepositDataDepositAmount("12");
            TestDBUtility.insertWithDel(l_params);

            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AioCashinNoticeUnit2 l_noticeUnit =
                l_impl.createCashinNoticeUnit(l_params, null);
            assertEquals("20080107",
                WEB3DateUtility.formatDate(l_noticeUnit.settlementDate, "yyyyMMdd"));

        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateQueryContainer_Case001()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AdminAioCashinNoticeSearchRequest l_request =
                new WEB3AdminAioCashinNoticeSearchRequest();
            l_request.branchCode = new String[]{"624", "625"};
            l_request.settlementDate =
                WEB3DateUtility.getDate("20080107", "yyyyMMdd");

            Object[] l_result =
                l_impl.createQueryContainer(l_request, "0D");
            assertEquals(4, l_result.length);
            assertEquals("010107", l_result[3]);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateQueryContainer_Case002()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EraParams.TYPE);
            EraParams l_eraParams = this.getEraParams();
            l_eraParams.setJapaneseEraDiv(1);
            TestDBUtility.insertWithDel(l_eraParams);

            WEB3AdminAioCashinNoticeSearchRequest l_request =
                new WEB3AdminAioCashinNoticeSearchRequest();
            l_request.branchCode = new String[]{"624", "625"};
            l_request.settlementDate =
                WEB3DateUtility.getDate("20100107", "yyyyMMdd");

            Object[] l_result =
                l_impl.createQueryContainer(l_request, "0D");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            l_ex.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03153, l_ex.getErrorInfo());
            assertEquals("勘定日の指定が正しくありません。", l_ex.getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public BankDepositNotifyParams getBankDepositNotifyParams()
    {
        BankDepositNotifyParams l_params = new BankDepositNotifyParams();
        //銀行入金通知テーブル IDbank_deposit_notify_id  NUMBER  18
        l_params.setBankDepositNotifyId(123456789l);
        //証券会社コード institution_code VARCHAR2 3
        l_params.setInstitutionCode("0D");
        //銀行コード bank_code  VARCHAR2 4
        l_params.setBankCode("624");
        //銀行支店コード bank_branch_code  VARCHAR2 3
        l_params.setBankBranchCode("12");
        //口座番号 bank_account_no  VARCHAR2  10
        l_params.setBankAccountNo("789456");
        //照会番号 deposit_data_reference_no  VARCHAR2  22
        l_params.setDepositDataReferenceNo("963852741");
        //勘定日 deposit_data_account_date  VARCHAR26
        l_params.setDepositDataAccountDate("32165");
        //入払区分     cash_transfer_div  VARCHAR2  1
        l_params.setCashTransferDiv("1");
        //エラー履歴最終通番  last_error_history_serial_no NUMBER 8
        l_params.setLastErrorHistorySerialNo(1001);
        //データ取込区分 data_load_div   VARCHAR2 1
        l_params.setDataLoadDiv("2");
        return l_params;
    }
    
    public EraParams getEraParams()
    {
        EraParams l_eraParams = new EraParams();
        //年号区分    japanese_era_div    NUMBER    1    NotNull    1：明治、2：大正、3：昭和、4：平成、9：不明
        l_eraParams.setJapaneseEraDiv(1);
        //年号    japanese_era    VARCHAR2    20    NotNull    1：明治、2：大正、3：昭和、4：平成、9：不明
        l_eraParams.setJapaneseEra("1");
        //開始年(和暦)    start_year_jap    VARCHAR2    2    NotNull    和暦(YY)
        l_eraParams.setStartYearJap("01");
        //開始年(西暦)    start_year    VARCHAR2    4    NotNull    西暦(YYYY)
        l_eraParams.setStartYear("2008");
        //開始月日    start_date    VARCHAR2    4    NotNull    MMDD
        l_eraParams.setStartDate("0002");
        //終了年(和暦)    end_year_jap    VARCHAR2    2    NotNull    和暦(YY)
        l_eraParams.setEndYearJap("02");
        //終了年(西暦)    end_year    VARCHAR2    4    NotNull    西暦(YYYY)
        l_eraParams.setEndYear("2009");
        //終了月日    end_date    VARCHAR2    4    NotNull    MMDD
        l_eraParams.setEndDate("0102");
        //作成日時    created_timestamp    DATE        NotNull    DEFAULT sysdate
        l_eraParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日時    last_updated_timestamp    DATE        NotNull    DEFAULT sysdate
        l_eraParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_eraParams;
    }
}
@
