head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerServiceImplTest2.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3TPTradingPowerServiceImplTest1.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/5 謝旋 (中訊) 新規作成
*/

package webbroker3.tradingpower;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultEquityDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3TPTradingPowerServiceImplTest2 extends TestBaseForMock
{
    public Date[] bizDate = new Date[8];
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImplTest2.class);

    public WEB3TPTradingPowerServiceImplTest2(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();
    
        //業務日付を取得する。
        Date l_bizDate0 = tradingSystem.getBizDate();
        
        //業務日付をプロパティにセットする。
        bizDate[1] = l_bizDate0;
    
        //TimeStamp型の業務日付を生成する。
        Timestamp l_bizDateStamp = new Timestamp(l_bizDate0.getTime());
    
        //営業日計算クラスを生成する。
        WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_bizDateStamp);
        //営業日配列の要素数を取得する。
        int l_intLength = bizDate.length;
    
        //項番を（-1～要素数-2）でループ
        for (int index = -1; index < l_intLength - 1; index++)
        {
            Timestamp l_timeStamp;
            try
            {
                l_timeStamp = l_genBizDate.roll(index);
            }
            catch (WEB3SystemLayerException e)
            {
                throw new WEB3BaseRuntimeException(
                     e.getErrorInfo(),
                     e.getErrorMethod(),
                     e.getErrorMessage(),
                     e.getException());
             }

            //属性：営業日[T-1..+6]にセットする。
           bizDate[index + 1] = new Date(l_timeStamp.getTime());
        }
        
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * 部店Rowを作成.<BR>
     */
    public BranchParams getBranchRow()
    {
        BranchParams l_branchParams = new BranchParams();

        l_branchParams.setBranchId(33381);
        l_branchParams.setInstitutionCode("0D");
        l_branchParams.setInstitutionId(33);
        l_branchParams.setBranchCode("381");
        l_branchParams.setBranchName("東京支店");
        l_branchParams.setBranchNameAlt1("TOKYO BRANCH");
        l_branchParams.setBranchType(BranchTypeEnum.REGULAR_LOCAL_BRANCH);
        l_branchParams.setMaxHandlingPriceInd(150000000);
        l_branchParams.setMaxHandlingPriceCorp(600000000);
        l_branchParams.setMaxHandlingPriceIndOption(600000000);
        l_branchParams.setMaxHandlingPriceCorpOption(600000000);
        l_branchParams.setMaxHandlingPriceIndFuture(600000000);
        l_branchParams.setMaxHandlingPriceCorpFuture(600000000);
        l_branchParams.setMaxContPriceAllInd(600000000.000000);
        l_branchParams.setMaxContPriceAllCorp(150000000.000000);
        l_branchParams.setMaxContPriceProductInd(600000000.000000);
        l_branchParams.setMaxContPriceProductCorp(150000000.000000);
        l_branchParams.setMaxContPrice1dayInd(600000000.000000D);
        l_branchParams.setMaxContPrice1dayCorp(1.000000D);
        l_branchParams.setHandlingMarketMake(0);
        l_branchParams.setHandlingNotLoanTransStock(0);
        l_branchParams.setEmailAddress("info@@naitou-sec.co.jp");
        l_branchParams.setLoginStopDiv("1");
        l_branchParams.setAccountCodeMin(6);
        l_branchParams.setAccountCodeMax(20);
        l_branchParams.setAccountCodeCheckMode("2");
        l_branchParams.setInsiderDefaultRegistDiv("0");
        l_branchParams.setMarginSysDiv("1");
        l_branchParams.setMarginGenDiv("1");
        l_branchParams.setFstkDiv("1");
        l_branchParams.setMstkDiv("1");
        l_branchParams.setOptionDiv("1");
        l_branchParams.setFutureDiv("1");
        l_branchParams.setMfDiv("1");
        l_branchParams.setRuitoDiv("0");
        l_branchParams.setQualifiedInvestorConfirmDiv("0");
        l_branchParams.setMarginDepositRate(0.000000D);
        l_branchParams.setCashMarginDepositRate(0.000000D);
        l_branchParams.setMarginDepositRate(0.000000D);
        l_branchParams.setMinMarginDeposit(0.000000D);
        l_branchParams.setMinIfoDeposit(0.000000D);
        l_branchParams.setCalcSubstituteRate(0.000000D);
        l_branchParams.setMarginSecCheckRate(0.000000D);
        l_branchParams.setShortMarginRestrainDiv("0");
        l_branchParams.setShortMarginRestrainUnit(0.000000D);
        l_branchParams.setShortSellOrderValidMinute(0);
        l_branchParams.setMarginSecTransferMaxCount(5);
        l_branchParams.setCloseWorngEquityMargin(10);
        l_branchParams.setCloseWorngOption(5);
        l_branchParams.setCloseWorngFeq(0);
        l_branchParams.setLastUpdater("administrator");
        l_branchParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_branchParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_branchParams.setMaxHandlingPriceCloseDiv("1");
        l_branchParams.setOffFloorDiv("1");
        l_branchParams.setCloseWorngFeq(5);

        return l_branchParams;
    }
    
    public TpCashBalanceParams getTpCashBalanceRow()
    {
        TpCashBalanceParams tpCashBalanceParams = new TpCashBalanceParams();
        tpCashBalanceParams.setTpCashBalanceId(123456789L);
        tpCashBalanceParams.setAccountId(333812512203L);
        tpCashBalanceParams.setSubAccountId(33381251220301L);
        tpCashBalanceParams.setCashBalance0(111D);
        tpCashBalanceParams.setCashBalance1(222D);
        tpCashBalanceParams.setCashBalance2(333D);
        tpCashBalanceParams.setCashBalance3(444D);
        tpCashBalanceParams.setCashBalance4(555D);
        tpCashBalanceParams.setCashBalance5(666D);
        tpCashBalanceParams.setMrfBalance(777D);
        tpCashBalanceParams.setCreatedTimestamp(new Date(888L));
        tpCashBalanceParams.setLastUpdatedTimestamp(new Date(999L));

        return tpCashBalanceParams;
    }
    
    public TradingpowerCalcConditionParams getTradingpowerCalcConditionRow()
    {
        SimpleDateFormat l_dateFormat = new SimpleDateFormat("yyyyMMdd");
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
        l_tradingpowerCalcConditionParams.setCalcConditionId(333812512223L);
        l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
        l_tradingpowerCalcConditionParams.setBranchId(33381);
        l_tradingpowerCalcConditionParams.setAssetEvaluationDiv("a");
        l_tradingpowerCalcConditionParams.setSpecialDebitAmount(123456D);
        l_tradingpowerCalcConditionParams.setTradingStop("s");
        l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("m");
        l_tradingpowerCalcConditionParams.setIfoOpenPositionStop("i");
        l_tradingpowerCalcConditionParams.setPaymentStop("p");
        l_tradingpowerCalcConditionParams.setOtherTradingStop("o");
        l_tradingpowerCalcConditionParams.setLastUpdater("123456");
        try
        {
            l_tradingpowerCalcConditionParams.setCreatedTimestamp(
                new Date(l_dateFormat.parse("20060606").getTime()));
            l_tradingpowerCalcConditionParams.setLastUpdatedTimestamp(
                new Date(l_dateFormat.parse("20060606").getTime()));
        } catch (ParseException e)
        {
            fail();
        }
        return l_tradingpowerCalcConditionParams;
    }
    
    public SubAccountParams getSubAccountRow1()
    {
        SubAccountParams l_subAccountParams = new SubAccountParams();
        //口座ＩＤ]
        l_subAccountParams.setAccountId(333812512203L);
        //補助口座ＩＤ
        l_subAccountParams.setSubAccountId(33381251220301L);
        //補助口座タイプ
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        //証券会社コード
        l_subAccountParams.setInstitutionCode("0D");
        //証券会社ID
        l_subAccountParams.setInstitutionId(33);
        //部店ＩＤ
        l_subAccountParams.setBranchId(33381L);
        //補助口座ステータス
        l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
        //口座登録日
        l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //口座閉鎖日
        l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //残高(当日）
        l_subAccountParams.setCashBalance(13456.0);
        //作成日付
        l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_subAccountParams;
    }
    
    public SubAccountParams getSubAccountRow2()
    {
        SubAccountParams l_subAccountParams = new SubAccountParams();
        //口座ＩＤ]
        l_subAccountParams.setAccountId(333812512203L);
        //補助口座ＩＤ
        l_subAccountParams.setSubAccountId(33381251220301L);
        //補助口座タイプ
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.BOND_SUB_ACCOUNT);
        //証券会社コード
        l_subAccountParams.setInstitutionCode("0D");
        //証券会社ID
        l_subAccountParams.setInstitutionId(33);
        //部店ＩＤ
        l_subAccountParams.setBranchId(33381L);
        //補助口座ステータス
        l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
        //口座登録日
        l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //口座閉鎖日
        l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        //残高(当日）
        l_subAccountParams.setCashBalance(13456.0);
        //作成日付
        l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付
        l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_subAccountParams;
    }
    
    /**
     * 顧客マスターRowを作成.<BR>
     */
    public static MainAccountParams getMainAccountRow1()
    {
        MainAccountParams l_mainAccountParams = new MainAccountParams();

        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionCode("0D");
        l_mainAccountParams.setInstitutionId(33L);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBranchId(33381L);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setSonarTraderCode("11124");
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
        l_mainAccountParams.setFamilyName("内藤　@四郎");
        l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
        l_mainAccountParams.setGivenNameAlt1("Siro");
        l_mainAccountParams.setZipCode("1001238");
        l_mainAccountParams.setSubZipCode("1001238");
        l_mainAccountParams.setAddressLine1("東京都");
        l_mainAccountParams.setAddressLine2("江東区");
        l_mainAccountParams.setAddressLine3("深川５");
        l_mainAccountParams.setTelephone("38201115");
        l_mainAccountParams.setMobile("901115");
        l_mainAccountParams.setFax("38202226");
        l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setResident("0");
        l_mainAccountParams.setNewAccountDiv("0");
        l_mainAccountParams.setViaTrustBankDiv("0");
        l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
        l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
        l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
        l_mainAccountParams.setPersonIdentify("1");
        l_mainAccountParams.setEraBorn("3");
        l_mainAccountParams.setBornDate("101013");
        l_mainAccountParams.setSex("1");
        l_mainAccountParams.setYellowCustomer("0");
        l_mainAccountParams.setHtSettlementWay("0");
        l_mainAccountParams.setBankAccountRegi("0");
        l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
        l_mainAccountParams.setExaminLockFlag("0");
        l_mainAccountParams.setMngLockFlag("0");
        l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
        l_mainAccountParams.setBranchLock("0");
        l_mainAccountParams.setOrderPermission("0");
        l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
        l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
        l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
        l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
        l_mainAccountParams.setQualifiedInstInvestorDiv("0");
        l_mainAccountParams.setMarginSysAccOpenDiv("0");
        l_mainAccountParams.setMarginGenAccOpenDiv("0");
        l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        l_mainAccountParams.setForeignSecAccOpenDiv("1");
        l_mainAccountParams.setIfoAccOpenDivTokyo("0");
        l_mainAccountParams.setIfoAccOpenDivOsaka("0");
        l_mainAccountParams.setIfoAccOpenDivNagoya("0");
        l_mainAccountParams.setRuitoAccOpenDiv("0");
        l_mainAccountParams.setMrfAccOpenDiv("0");
        l_mainAccountParams.setFxAccOpenDiv("0");
        l_mainAccountParams.setFeqConAccOpenDiv("0");
        l_mainAccountParams.setTopPageId("0");
        l_mainAccountParams.setQuotoType("0");
        l_mainAccountParams.setTradingReportDiv("1");
        l_mainAccountParams.setPositionReportDiv("9");
        l_mainAccountParams.setPositionReportCycleDiv("1");
        l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setEmailLastUpdater("2512246");
        l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setTradingPasswordUpdater("2512246");
        l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setMbOffLastUpdater("2512246");
        l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setEnableOrderLastUpdater("2512246");
        l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
        l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
        l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setMrfFundCode("1");
        l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setSpMngAccOpenDiv("0");

        return l_mainAccountParams;
    }
    
    /**
     * 顧客マスターRowを作成.<BR>
     */
    public static MainAccountParams getMainAccountRow2()
    {
        MainAccountParams l_mainAccountParams = new MainAccountParams();

        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionCode("0D");
        l_mainAccountParams.setInstitutionId(33L);
        l_mainAccountParams.setAccountCode("2512246");
        l_mainAccountParams.setBranchId(33381L);
        l_mainAccountParams.setBranchCode("381");
        l_mainAccountParams.setSonarTraderCode("11124");
        l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
        l_mainAccountParams.setFamilyName("内藤　@四郎");
        l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
        l_mainAccountParams.setGivenNameAlt1("Siro");
        l_mainAccountParams.setZipCode("1001238");
        l_mainAccountParams.setSubZipCode("1001238");
        l_mainAccountParams.setAddressLine1("東京都");
        l_mainAccountParams.setAddressLine2("江東区");
        l_mainAccountParams.setAddressLine3("深川５");
        l_mainAccountParams.setTelephone("38201115");
        l_mainAccountParams.setMobile("901115");
        l_mainAccountParams.setFax("38202226");
        l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setResident("0");
        l_mainAccountParams.setNewAccountDiv("0");
        l_mainAccountParams.setViaTrustBankDiv("0");
        l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
        l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
        l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
        l_mainAccountParams.setPersonIdentify("1");
        l_mainAccountParams.setEraBorn("3");
        l_mainAccountParams.setBornDate("101013");
        l_mainAccountParams.setSex("1");
        l_mainAccountParams.setYellowCustomer("0");
        l_mainAccountParams.setHtSettlementWay("0");
        l_mainAccountParams.setBankAccountRegi("0");
        l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
        l_mainAccountParams.setExaminLockFlag("0");
        l_mainAccountParams.setMngLockFlag("0");
        l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
        l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
        l_mainAccountParams.setBranchLock("0");
        l_mainAccountParams.setOrderPermission("0");
        l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
        l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
        l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
        l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
        l_mainAccountParams.setQualifiedInstInvestorDiv("0");
        l_mainAccountParams.setMarginSysAccOpenDiv("1");
        l_mainAccountParams.setMarginGenAccOpenDiv("1");
        l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
        l_mainAccountParams.setForeignSecAccOpenDiv("1");
        l_mainAccountParams.setIfoAccOpenDivTokyo("0");
        l_mainAccountParams.setIfoAccOpenDivOsaka("0");
        l_mainAccountParams.setIfoAccOpenDivNagoya("0");
        l_mainAccountParams.setRuitoAccOpenDiv("0");
        l_mainAccountParams.setMrfAccOpenDiv("0");
        l_mainAccountParams.setFxAccOpenDiv("0");
        l_mainAccountParams.setFeqConAccOpenDiv("0");
        l_mainAccountParams.setTopPageId("0");
        l_mainAccountParams.setQuotoType("0");
        l_mainAccountParams.setTradingReportDiv("1");
        l_mainAccountParams.setPositionReportDiv("9");
        l_mainAccountParams.setPositionReportCycleDiv("1");
        l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
        l_mainAccountParams.setEmailLastUpdater("2512246");
        l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setTradingPasswordUpdater("2512246");
        l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setMbOffLastUpdater("2512246");
        l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setEnableOrderLastUpdater("2512246");
        l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
        l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
        l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setMrfFundCode("1");
        l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mainAccountParams.setSpMngAccOpenDiv("0");

        return l_mainAccountParams;
    }
    
    public EqtypeTradedProductParams getEqtypeTradedProductRow()
    {
        EqtypeTradedProductParams l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
        l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
        l_eqtypeTradedProductParams.setInstitutionCode("0D");
        l_eqtypeTradedProductParams.setProductId(3304148080000L);
        l_eqtypeTradedProductParams.setMarketId(3303L);
        l_eqtypeTradedProductParams.setListFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setListType("0");
        l_eqtypeTradedProductParams.setNewListType("0");
        l_eqtypeTradedProductParams.setListedDate(Calendar.getInstance().getTime());
        l_eqtypeTradedProductParams.setMarginableFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setShortableFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setMiniStockCanDealt(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setLastClosingPrice(465D);
        l_eqtypeTradedProductParams.setLotSize(123D);
        l_eqtypeTradedProductParams.setMiniStockFlag(BooleanEnum.TRUE);
        l_eqtypeTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeTradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeTradedProductParams.setBasePrice(1D);
        return l_eqtypeTradedProductParams;
    }
    
    /**
     * 株式銘柄Rowを作成.<BR>
     */
    public EqtypeProductParams getEqtypeProductRow()
    {
        EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
        l_eqtypeProductParams.setProductId(3304148080000L);
        l_eqtypeProductParams.setInstitutionCode("78");
        l_eqtypeProductParams.setProductCode("123456");
        l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
        l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_eqtypeProductParams;
    }
    
    /**
     * 銘柄Rowを作成.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(3304148080000L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }
    
    /**
     * 市場Rowを作成.<BR>
     */
    public static MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("0D");
        l_marketParams.setMarketCode("2");
        l_marketParams.setMarketName("23");
        l_marketParams.setOpenTime("2323");
        l_marketParams.setCloseTime("4555");
        l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
         return l_marketParams;
    }
    
    /**
     * 取引銘柄マスターテーブルRowを作成.<BR>
     */
    public TradedProductParams getTradedProductRow()
    {
        TradedProductParams l_tradedProductParams = new TradedProductParams();
        
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setInstitutionCode("0D");
        l_tradedProductParams.setProductId(3304148080000L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
        l_tradedProductParams.setBaseDate(Calendar.getInstance().getTime());
        l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
        l_tradedProductParams.setCollateralFlag(BooleanEnum.TRUE);
        l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_tradedProductParams;
    }
    
    public void testvalidateTradingPowerEquitySell_C0001() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow1());

            TestDBUtility.insertWithDel(getMainAccountRow1());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[1]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,false);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals("3",l_result.getAttentionObjectionType());
            assertEquals(12D,l_result.getLackAccountBalance(), 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0002() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow1());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[2]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,false);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0003() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow1());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[3]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,false);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0004() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow1());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[4]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            
            TpCalcResultEquityParams calcParams = new TpCalcResultEquityParams();
            TpCalcResultEquityDao tpCalcResultEquityDao = 
                (TpCalcResultEquityDao) TpCalcResultEquityDao.FACTORY.newInstance(calcParams);
            TpCalcResultEquityRow tpCalcResultEquityRow = tpCalcResultEquityDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityRow.getAccountId());
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(), 0);
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(),0);
            assertEquals(5678921.154,tpCalcResultEquityRow.getTodayExecutedAmount0(), 0);
            assertEquals(36451.258,tpCalcResultEquityRow.getTodayUnexecutedAmount0(), 0);
            assertEquals(456.12345,tpCalcResultEquityRow.getDayTradeRestraint0(),0);
            assertEquals(7841235.876,tpCalcResultEquityRow.getOtherRestraint0(),0);
            assertEquals(79854.5465,tpCalcResultEquityRow.getTrustSecurityAsset0(),0);
            
            TpCalcResultEquityDetailParams tpCalcResultEquityDetailParams = new TpCalcResultEquityDetailParams();
            TpCalcResultEquityDetailDao tpCalcResultEquityDetailDao = 
                (TpCalcResultEquityDetailDao) TpCalcResultEquityDetailDao.FACTORY.newInstance(tpCalcResultEquityDetailParams);
            TpCalcResultEquityDetailRow tpCalcResultEquityDetailRow = tpCalcResultEquityDetailDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityDetailRow.getAccountId());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0005() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow1());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[5]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            
            TpCalcResultEquityParams calcParams = new TpCalcResultEquityParams();
            TpCalcResultEquityDao tpCalcResultEquityDao = 
                (TpCalcResultEquityDao) TpCalcResultEquityDao.FACTORY.newInstance(calcParams);
            TpCalcResultEquityRow tpCalcResultEquityRow = tpCalcResultEquityDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityRow.getAccountId());
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(), 0);
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(),0);
            assertEquals(5678921.154,tpCalcResultEquityRow.getTodayExecutedAmount0(), 0);
            assertEquals(36451.258,tpCalcResultEquityRow.getTodayUnexecutedAmount0(), 0);
            assertEquals(456.12345,tpCalcResultEquityRow.getDayTradeRestraint0(),0);
            assertEquals(7841235.876,tpCalcResultEquityRow.getOtherRestraint0(),0);
            assertEquals(79854.5465,tpCalcResultEquityRow.getTrustSecurityAsset0(),0);
            
            TpCalcResultEquityDetailParams tpCalcResultEquityDetailParams = new TpCalcResultEquityDetailParams();
            TpCalcResultEquityDetailDao tpCalcResultEquityDetailDao = 
                (TpCalcResultEquityDetailDao) TpCalcResultEquityDetailDao.FACTORY.newInstance(tpCalcResultEquityDetailParams);
            TpCalcResultEquityDetailRow tpCalcResultEquityDetailRow = tpCalcResultEquityDetailDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityDetailRow.getAccountId());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0006() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.insertWithDel(getMarketRow());
            TestDBUtility.insertWithDel(getEqtypeTradedProductRow());
            TestDBUtility.insertWithDel(getProductRow());
            TestDBUtility.insertWithDel(getEqtypeProductRow());
            TestDBUtility.insertWithDel(getTradedProductRow());
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());

            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow1());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            BranchParams l_branchParams = getBranchRow();
            l_branchParams.setBranchType(BranchTypeEnum.OTHER_BRANCH);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(3304148080000L);
            tPNewOrderSpec.setMarketId(3303L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[1]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(false,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getAttentionObjectionType());
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = l_result.getTpErrorInfo();
            assertEquals("1",l_tpErrorInfo.tradinPowerErrorDiv);
            assertEquals(12D,l_tpErrorInfo.lackAccountBalance,0);
            assertEquals(0D,l_tpErrorInfo.buyOrderPossibleAmount,0);
            assertEquals(11D,l_tpErrorInfo.sellOrderPossibleQuantity,0);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0007() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow1());

            TestDBUtility.insertWithDel(getMainAccountRow2());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[1]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,false);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals("3",l_result.getAttentionObjectionType());
            assertEquals(12D,l_result.getLackAccountBalance(), 0);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0008() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow2());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[2]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            
            TpCalcResultEquityParams calcParams = new TpCalcResultEquityParams();
            TpCalcResultEquityDao tpCalcResultEquityDao = 
                (TpCalcResultEquityDao) TpCalcResultEquityDao.FACTORY.newInstance(calcParams);
            TpCalcResultEquityRow tpCalcResultEquityRow = tpCalcResultEquityDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityRow.getAccountId());
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(), 0);
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(),0);
            assertEquals(5678921.154,tpCalcResultEquityRow.getTodayExecutedAmount0(), 0);
            assertEquals(36451.258,tpCalcResultEquityRow.getTodayUnexecutedAmount0(), 0);
            assertEquals(456.12345,tpCalcResultEquityRow.getDayTradeRestraint0(),0);
            assertEquals(7841235.876,tpCalcResultEquityRow.getOtherRestraint0(),0);
            assertEquals(79854.5465,tpCalcResultEquityRow.getTrustSecurityAsset0(),0);
            
            TpCalcResultEquityDetailParams tpCalcResultEquityDetailParams = new TpCalcResultEquityDetailParams();
            TpCalcResultEquityDetailDao tpCalcResultEquityDetailDao = 
                (TpCalcResultEquityDetailDao) TpCalcResultEquityDetailDao.FACTORY.newInstance(tpCalcResultEquityDetailParams);
            TpCalcResultEquityDetailRow tpCalcResultEquityDetailRow = tpCalcResultEquityDetailDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityDetailRow.getAccountId());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0009() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow2());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[3]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            
            TpCalcResultEquityParams calcParams = new TpCalcResultEquityParams();
            TpCalcResultEquityDao tpCalcResultEquityDao = 
                (TpCalcResultEquityDao) TpCalcResultEquityDao.FACTORY.newInstance(calcParams);
            TpCalcResultEquityRow tpCalcResultEquityRow = tpCalcResultEquityDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityRow.getAccountId());
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(), 0);
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(),0);
            assertEquals(5678921.154,tpCalcResultEquityRow.getTodayExecutedAmount0(), 0);
            assertEquals(36451.258,tpCalcResultEquityRow.getTodayUnexecutedAmount0(), 0);
            assertEquals(456.12345,tpCalcResultEquityRow.getDayTradeRestraint0(),0);
            assertEquals(7841235.876,tpCalcResultEquityRow.getOtherRestraint0(),0);
            assertEquals(79854.5465,tpCalcResultEquityRow.getTrustSecurityAsset0(),0);
            
            TpCalcResultEquityDetailParams tpCalcResultEquityDetailParams = new TpCalcResultEquityDetailParams();
            TpCalcResultEquityDetailDao tpCalcResultEquityDetailDao = 
                (TpCalcResultEquityDetailDao) TpCalcResultEquityDetailDao.FACTORY.newInstance(tpCalcResultEquityDetailParams);
            TpCalcResultEquityDetailRow tpCalcResultEquityDetailRow = tpCalcResultEquityDetailDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityDetailRow.getAccountId());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0010() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow2());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[4]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            
            TpCalcResultEquityParams calcParams = new TpCalcResultEquityParams();
            TpCalcResultEquityDao tpCalcResultEquityDao = 
                (TpCalcResultEquityDao) TpCalcResultEquityDao.FACTORY.newInstance(calcParams);
            TpCalcResultEquityRow tpCalcResultEquityRow = tpCalcResultEquityDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityRow.getAccountId());
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(), 0);
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(),0);
            assertEquals(5678921.154,tpCalcResultEquityRow.getTodayExecutedAmount0(), 0);
            assertEquals(36451.258,tpCalcResultEquityRow.getTodayUnexecutedAmount0(), 0);
            assertEquals(456.12345,tpCalcResultEquityRow.getDayTradeRestraint0(),0);
            assertEquals(7841235.876,tpCalcResultEquityRow.getOtherRestraint0(),0);
            assertEquals(79854.5465,tpCalcResultEquityRow.getTrustSecurityAsset0(),0);
            
            TpCalcResultEquityDetailParams tpCalcResultEquityDetailParams = new TpCalcResultEquityDetailParams();
            TpCalcResultEquityDetailDao tpCalcResultEquityDetailDao = 
                (TpCalcResultEquityDetailDao) TpCalcResultEquityDetailDao.FACTORY.newInstance(tpCalcResultEquityDetailParams);
            TpCalcResultEquityDetailRow tpCalcResultEquityDetailRow = tpCalcResultEquityDetailDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityDetailRow.getAccountId());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0011() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow2());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(getBranchRow());
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(333812512203L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[5]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(true,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getTpErrorInfo());
            assertEquals(null,l_result.getAttentionObjectionType());
            
            TpCalcResultEquityParams calcParams = new TpCalcResultEquityParams();
            TpCalcResultEquityDao tpCalcResultEquityDao = 
                (TpCalcResultEquityDao) TpCalcResultEquityDao.FACTORY.newInstance(calcParams);
            TpCalcResultEquityRow tpCalcResultEquityRow = tpCalcResultEquityDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityRow.getAccountId());
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(), 0);
            assertEquals(3546.123,tpCalcResultEquityRow.getAccountBalance0(),0);
            assertEquals(5678921.154,tpCalcResultEquityRow.getTodayExecutedAmount0(), 0);
            assertEquals(36451.258,tpCalcResultEquityRow.getTodayUnexecutedAmount0(), 0);
            assertEquals(456.12345,tpCalcResultEquityRow.getDayTradeRestraint0(),0);
            assertEquals(7841235.876,tpCalcResultEquityRow.getOtherRestraint0(),0);
            assertEquals(79854.5465,tpCalcResultEquityRow.getTrustSecurityAsset0(),0);
            
            TpCalcResultEquityDetailParams tpCalcResultEquityDetailParams = new TpCalcResultEquityDetailParams();
            TpCalcResultEquityDetailDao tpCalcResultEquityDetailDao = 
                (TpCalcResultEquityDetailDao) TpCalcResultEquityDetailDao.FACTORY.newInstance(tpCalcResultEquityDetailParams);
            TpCalcResultEquityDetailRow tpCalcResultEquityDetailRow = tpCalcResultEquityDetailDao.findRowByPk(7894563L);
            assertEquals(333812512203L,tpCalcResultEquityDetailRow.getAccountId());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidateTradingPowerEquitySell_C0012() 
    {
        String STR_METHOD_NAME = "testvalidateTradingPowerEquitySell_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());
            
            TestDBUtility.insertWithDel(getMarketRow());
            TestDBUtility.insertWithDel(getEqtypeTradedProductRow());
            TestDBUtility.insertWithDel(getProductRow());
            TestDBUtility.insertWithDel(getEqtypeProductRow());
            TestDBUtility.insertWithDel(getTradedProductRow());
            TestDBUtility.insertWithDel(getTradingpowerCalcConditionRow());

            TestDBUtility.insertWithDel(getSubAccountRow2());

            TestDBUtility.insertWithDel(getMainAccountRow2());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            BranchParams l_branchParams = getBranchRow();
            l_branchParams.setBranchType(BranchTypeEnum.OTHER_BRANCH);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(getTpCashBalanceRow());
        } 
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            //市場コード、受付時間区分、銘柄コードコードを退避する。
            l_clendarContext.setMarketCode("1234567");
            l_clendarContext.setTradingTimeType("1234567");
            l_clendarContext.setProductCode("1234567");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",l_clendarContext);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20060606L));
            
            WEB3GentradeSubAccount gentradeSubAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPNewOrderSpec tPNewOrderSpec = new WEB3TPNewOrderSpec();
            WEB3TPNewOrderSpec[] tPNewOrderSpecArray = new WEB3TPNewOrderSpec[1];
            tPNewOrderSpec.setProductId(3304148080000L);
            tPNewOrderSpec.setMarketId(3303L);
            
            tPNewOrderSpec.setDeliveryDate(bizDate[1]); //業務日付を取得する
            
            tPNewOrderSpecArray[0] = tPNewOrderSpec;

            WEB3TPTradingPowerServiceImpl tPTradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result = 
                tPTradingPowerServiceImpl.validateTradingPowerEquitySell(gentradeSubAccount,tPNewOrderSpecArray,true);
            
            assertEquals(false,l_result.isResultFlg());
            assertEquals(0D,l_result.getTradingPower(), 0);
            assertEquals(null,l_result.getAttentionObjectionType());
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = l_result.getTpErrorInfo();
            assertEquals("1",l_tpErrorInfo.tradinPowerErrorDiv);
            assertEquals(12D,l_tpErrorInfo.lackAccountBalance,0);
            assertEquals(0D,l_tpErrorInfo.buyOrderPossibleAmount,0);
            assertEquals(11D,l_tpErrorInfo.sellOrderPossibleQuantity,0);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
