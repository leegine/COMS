head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.31.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9c24957ec8;
filename	WEB3AccInfoOccupationChangeRegistVoucherCreatedTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 職業変更申込伝票作成テスト (WEB3AccInfoOccupationChangeRegistVoucherCreatedTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/12 李木子 (中訊) 新規作成
*/
package webbroker3.accountinfo;



import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountopen.data.TradeConditionVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstParams;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (職業変更申込伝票作成テスト)<BR>
 * 職業変更申込伝票作成クラステスト<BR>
 * <BR>
 * @@author 李木子<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoOccupationChangeRegistVoucherCreatedTest extends
        TestBaseForMock {

    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoOccupationChangeRegistVoucherCreatedTest.class);

    public WEB3AccInfoOccupationChangeRegistVoucherCreatedTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * is伝票作成<BR>
     * 伝票作成の可否を判別する。<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0001()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();

        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setOccupationDiv("999999999");

        WEB3AccInfoMaster l_accInfoMaster = null;

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is伝票作成<BR>
     * 伝票作成の可否を判別する。<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0002()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();
        AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams();

        l_accountInfoMstParams.setAccountCode("2450007");

        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setOccupationDiv("999999999");

        WEB3AccInfoMaster l_accInfoMaster = new WEB3AccInfoMaster(l_accountInfoMstParams);

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is伝票作成<BR>
     * 伝票作成の可否を判別する。<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0003()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();
        AccountInfoMstParams l_accountInfoMstParams = new AccountInfoMstParams();

        l_accountInfoMstParams.setOccupationDiv("888888888");

        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setOccupationDiv("999999999");

        WEB3AccInfoMaster l_accInfoMaster = new WEB3AccInfoMaster(l_accountInfoMstParams);

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * is伝票作成<BR>
     * 伝票作成の可否を判別する。<BR>
     * <BR>
     */
    public void test_isVoucherCreated_0004()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated();
        MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams = new MobileOfficeInfoRegistParams();
        
        l_mobileOfficeInfoRegistParams.setMobileOfficeInfoRegistId(123456789L);
        l_mobileOfficeInfoRegistParams.setInstitutionCode("0D");
        l_mobileOfficeInfoRegistParams.setBranchId(33381L);
        l_mobileOfficeInfoRegistParams.setAccountId(333812512246L);
        l_mobileOfficeInfoRegistParams.setAccountCode("2450007");
        l_mobileOfficeInfoRegistParams.setRegistUpdater("333812512246");
        l_mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_mobileOfficeInfoRegistParams.setLastUpdater("2450007");
        l_mobileOfficeInfoRegistParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        WEB3AccInfoMaster l_accInfoMaster = null;

        try
        {
            boolean l_blnVoucherCreated = l_accInfoOccupationChangeRegistVoucherCreated.isVoucherCreated(
                l_mobileOfficeInfoRegistParams, l_accInfoMaster);
            assertTrue(!l_blnVoucherCreated);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create職業変更伝票)<BR>
     * 職業変更に伴う取残・電子交付・特定口座伝票（GI844）を作成する。<BR>
     */
    public void test_createOccupationChangeVoucher_0001()
    {
        final String STR_METHOD_NAME = ".test_isVoucherCreated_0003()";
        log.entering(STR_METHOD_NAME);

        MainAccountAllRow l_mainAccountAllRow = null;
        WEB3AccInfoOccupationChangeRegistVoucherCreated l_accInfoOccupationChangeRegistVoucherCreated =
            new WEB3AccInfoOccupationChangeRegistVoucherCreated(l_mainAccountAllRow);
        MainAccountParams l_mainAccountRow = this.getMainAccountRow();
        TradeConditionVoucherParams l_tradeConditionVoucherParams = new TradeConditionVoucherParams();

        try
        {
            TestDBUtility.deleteAll(l_tradeConditionVoucherParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_mainAccountRow);
        }
        catch (Exception e)
        {
            fail();
        }

        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(33L, "38", "2512246");

            l_accInfoOccupationChangeRegistVoucherCreated.createOccupationChangeVoucher(11111111L, "88", l_mainAccount);

            QueryProcessor l_qp = Processors.getDefaultProcessor();

            List l_listTradeConditionVoucher = l_qp.doFindAllQuery(l_tradeConditionVoucherParams.getRowType());

            TradeConditionVoucherParams l_tradeConditionVoucherParamsForTest =
                (TradeConditionVoucherParams)l_listTradeConditionVoucher.get(0);

            assertEquals("0D", l_tradeConditionVoucherParamsForTest.getInstitutionCode());
            assertEquals("38", l_tradeConditionVoucherParamsForTest.getBranchCode());
            assertEquals("2512246", l_tradeConditionVoucherParamsForTest.getAccountCode());
            assertEquals("11124", l_tradeConditionVoucherParamsForTest.getTraderCode());
            assertNull(l_tradeConditionVoucherParamsForTest.getTradingEReportDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getInvEReportDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportTermDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportCycleDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportCertifDepoDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getPosReportAccStateDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getEquityTaxDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getEquityTaxDivNext());
            assertNull(l_tradeConditionVoucherParamsForTest.getEquitySpAccOpenDat());
            assertNull(l_tradeConditionVoucherParamsForTest.getMarginTaxDiv());
            assertNull(l_tradeConditionVoucherParamsForTest.getMarginTaxDivNext());
            assertNull(l_tradeConditionVoucherParamsForTest.getMarginSpAccOpenDat());
            assertNull(l_tradeConditionVoucherParamsForTest.getSpMngAccOpenDiv());
            assertEquals("88", l_tradeConditionVoucherParamsForTest.getOccupationDiv());
            assertEquals("1", l_tradeConditionVoucherParamsForTest.getRegistDiv());
            assertEquals(11111111L, l_tradeConditionVoucherParamsForTest.getMobileOfficeInfoRegistId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 顧客マスターRowを作成.<BR>
     */
    public MainAccountParams getMainAccountRow()
    {
        MainAccountParams l_mainAccountParams = new MainAccountParams();

        // 口座ＩＤ
        l_mainAccountParams.setAccountId(333812512246L);
        // 証券会社コード
        l_mainAccountParams.setInstitutionCode("0D");
        // 証券会社ID
        l_mainAccountParams.setInstitutionId(33L);
        // 口座コード
        l_mainAccountParams.setAccountCode("2512246");
        // 部店ＩＤ
        l_mainAccountParams.setBranchId(33381L);
        // 部店コード
        l_mainAccountParams.setBranchCode("38");
        // 扱者コード（SONAR）
        l_mainAccountParams.setSonarTraderCode("11124");
        // 口座タイプ
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
        // 名前（苗字）
        l_mainAccountParams.setFamilyName("内藤　@四郎");
        // 名前（苗字1）
        l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
        // 名前（名前1）
        l_mainAccountParams.setGivenNameAlt1("Siro");
        // 郵便番号
        l_mainAccountParams.setZipCode("1001238");
        // （補助）郵便番号
        l_mainAccountParams.setSubZipCode("1001238");
        // 住所１
        l_mainAccountParams.setAddressLine1("東京都");
        // 住所２
        l_mainAccountParams.setAddressLine2("江東区");
        // 住所３
        l_mainAccountParams.setAddressLine3("深川５");
        // 電話番号
        l_mainAccountParams.setTelephone("38201115");
        // 連絡先電話番号（携帯）
        l_mainAccountParams.setMobile("901115");
        // ＦＡＸ番号
        l_mainAccountParams.setFax("38202226");
        // 株式約定メール送信フラグ
        l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
        // 株式未約定メール送信フラグ
        l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
        // 先物OP約定メール送信フラグ
        l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
        // 先物OP未約定メール送信フラグ
        l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
        // 案内メール送信フラグ
        l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
        // 居住／非居住区分
        l_mainAccountParams.setResident("0");
        // 新規口座区分
        l_mainAccountParams.setNewAccountDiv("0");
        // 信託経由区分
        l_mainAccountParams.setViaTrustBankDiv("0");
        // emailアドレス
        l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
        // 取引パスワード
        l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
        // 口座登録日
        l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        // 口座閉鎖日
        l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
        // 本人確認区分
        l_mainAccountParams.setPersonIdentify("1");
        // 生年月日年号
        l_mainAccountParams.setEraBorn("3");
        // 生年月日
        l_mainAccountParams.setBornDate("101013");
        // 性別
        l_mainAccountParams.setSex("1");
        // Ｙ客区分
        l_mainAccountParams.setYellowCustomer("0");
        // ホームトレード決済方法@
        l_mainAccountParams.setHtSettlementWay("0");
        // 振込先（銀行口座）登録区分
        l_mainAccountParams.setBankAccountRegi("0");
        // 口座ステータス
        l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
        // 考査ロック
        l_mainAccountParams.setExaminLockFlag("0");
        // 管理ロック
        l_mainAccountParams.setMngLockFlag("0");
        // 管理ロック理由フラグ（立替金）
        l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
        // 管理ロック理由フラグ（保証金未入）
        l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
        // 管理ロック理由フラグ（適格担保不足）
        l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
        // 管理ロック理由フラグ（預り証長期未差換）
        l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
        // 支店ロック
        l_mainAccountParams.setBranchLock("0");
        // 注文認可
        l_mainAccountParams.setOrderPermission("0");
        // 税区分
        l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
        // 税区分（次年）
        l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
        // 信用取引税区分
        l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
        // 信用取引税区分（次年）
        l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
        // 適格機@関投資家区分
        l_mainAccountParams.setQualifiedInstInvestorDiv("0");
        // 制度信用取引口座開設区分
        l_mainAccountParams.setMarginSysAccOpenDiv("0");
        // 一般信用取引口座開設区分
        l_mainAccountParams.setMarginGenAccOpenDiv("0");
        // 現物株式特定口座開設日
        l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        // 外国証券口座開設区分
        l_mainAccountParams.setForeignSecAccOpenDiv("1");
        // 先物OP口座開設区分（東証）
        l_mainAccountParams.setIfoAccOpenDivTokyo("0");
        // 先物OP口座開設区分（大証）
        l_mainAccountParams.setIfoAccOpenDivOsaka("0");
        // 先物OP口座開設区分（名証）
        l_mainAccountParams.setIfoAccOpenDivNagoya("0");
        // 累投口座開設区分
        l_mainAccountParams.setRuitoAccOpenDiv("0");
        // ＭＲＦ口座開設区分
        l_mainAccountParams.setMrfAccOpenDiv("0");
        // ＦＸ口座開設区分
        l_mainAccountParams.setFxAccOpenDiv("0");
        // 外国株式連携口座開設区分
        l_mainAccountParams.setFeqConAccOpenDiv("0");
        // 先頭画面ID
        l_mainAccountParams.setTopPageId("0");
        // 時価取得区分
        l_mainAccountParams.setQuotoType("0");
        // 取引報告書交付区分
        l_mainAccountParams.setTradingReportDiv("1");
        // 取引残高報告書交付区分
        l_mainAccountParams.setPositionReportDiv("9");
        // 取引残高報告書作成周期区分
        l_mainAccountParams.setPositionReportCycleDiv("1");
        // 取引残高報告書預り証作成フラグ
        l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
        // 取引残高報告書計算書作成フラグ
        l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
        // emailアドレス更新者コード
        l_mainAccountParams.setEmailLastUpdater("2512246");
        // emailアドレス更新日時
        l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
        // 取引パスワード更新者コード
        l_mainAccountParams.setTradingPasswordUpdater("2512246");
        // 取引パスワード更新日時
        l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
        // 携帯番号・勤務先情報更新者コード
        l_mainAccountParams.setMbOffLastUpdater("2512246");
        // 携帯番号・勤務先情報更新日時
        l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
        // 停止状況更新者コード
        l_mainAccountParams.setEnableOrderLastUpdater("2512246");
        // 停止状況更新日時
        l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
        // ＦＸ口座開設区分更新者コード
        l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
        // ＦＸ口座開設区分更新日時
        l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
        // 外国株式連携口座開設区分更新者コード
        l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
        // 外国株式連携口座開設区分更新日時
        l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
        // ＭＲＦ設定会社
        l_mainAccountParams.setMrfFundCode("1");
        // 作成日時
        l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        // 更新日時
        l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        // 特定管理口座開設区分
        l_mainAccountParams.setSpMngAccOpenDiv("0");

        return l_mainAccountParams;
    }
}
@
