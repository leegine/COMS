head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentRequisitionManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理(WEB3TPPaymentRequisitionManagement.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 孟亞南 (中訊) 仕様変更モデル308,316,
Revision History : 2008/10/20 張少傑 (中訊) 仕様変更モデル317,329,338
Revision History : 2008/10/22 劉剣 (中訊) 仕様変更モデル340,342,344
Revision History : 2008/10/27 劉剣 (中訊) 仕様変更モデル346
Revision History : 2008/10/28 孟亞南 (中訊) 仕様変更モデル347
Revision History : 2008/10/29 張少傑 (中訊) 仕様変更モデル348
Revision History : 2008/10/31 孟亞南 (中訊) 仕様変更モデル349,350,354
Revision History : 2008/11/04 張少傑 (中訊) 仕様変更モデル357
Revision History : 2008/11/06 孟亞南 (中訊) 仕様変更モデル358,359,360,361
Revision History : 2008/11/07 孟亞南 (中訊) 仕様変更モデル362
Revision History : 2008/11/12 三島淳一郎 (SCS) 仕様変更モデル366
Revision History : 2008/11/18 三島淳一郎 (SCS) 仕様変更モデル368,369
Revision History : 2008/11/21 三島淳一郎 (SCS) 仕様変更モデル373
Revision History : 2008/11/21 三島淳一郎 (SCS) 実装の問題007
Revision History : 2009/05/27 張騰宇 (中訊) 仕様変更モデル390
*/
package webbroker3.tradingpower;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountAttributeDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3TodayClearanceDeterminationDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.data.PaymentRequisitMngDao;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.tradingpower.define.WEB3TPProcessManagementIdDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金請求管理)<BR>
 * (入金請求管理)<BR>
 * <BR>
 * 入金請求管理Paramsと資産余力情報より、各種計算を行うクラス<BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPPaymentRequisitionManagement
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionManagement.class);

    /**
     * (入金請求管理Params)<BR>
     * (入金請求管理Params)<BR>
     * <BR>
     * 入金請求管理テーブルの検索結果のデータを保持するクラス<BR>
     */
    protected PaymentRequisitMngParams paymentRequisitMngParams;

    /**
     * (顧客勘定残高(マスタ情報)<保証金>)<BR>
     * (顧客勘定残高(マスタ情報)<保証金>)<BR>
     * <BR>
     * 顧客勘定残高(マスタ情報)テーブルの検索結果のデータを保持するクラス<BR>
     */
    protected TpCashBalanceParams tpCashBalanceParams;

    /**
     * (資産余力情報<現物顧客>)<BR>
     * (資産余力情報<現物顧客>)<BR>
     * <BR>
     * 余力更新結果<現物顧客>より、各種取引可能額を計算するクラス<BR>
     * （信用顧客の場合、nullを設定）<BR>
     */
    protected WEB3TPTradingPowerCalcEquity tpCalcEquity;

    /**
     * (資産余力情報<信用顧客>)<BR>
     * (資産余力情報<信用顧客>)<BR>
     * <BR>
     * 余力更新結果<信用顧客>より、各種取引可能額を計算するクラス<BR>
     * （現物顧客の場合、nullを設定）<BR>
     */
    protected WEB3TPTradingPowerCalcMargin tpCalcMargin;

    /**
     * (入出金注文単位一覧(預り金⇒保証金))<BR>
     * (入出金注文単位一覧(預り金⇒保証金))<BR>
     * <BR>
     * 受渡日がT+0以降で、（預り金⇒保証金）振替のデータを保持するList<BR>
     */
    protected List aioOrderUnitListFromDepositToMargins;

    /**
     * (入出金注文単位一覧(保証金⇒預り金))<BR>
     * (入出金注文単位一覧(保証金⇒預り金))<BR>
     * <BR>
     * 受渡日がT+0以降で、（保証金⇒預り金）振替のデータを保持するList<BR>
     */
    protected List aioOrderUnitListFromMarginToDeposits;

    /**
     * (信用現物判定フラグ)<BR>
     * (信用現物判定フラグ)<BR>
     * <BR>
     * 信用顧客と現物顧客の判定を行うフラグ<BR>
     * 0：現物顧客<BR>
     * 1：信用顧客<BR>
     */
    protected String marginEquityJudgeFlag;

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * (保証金自動振替後判定フラグ)<BR>
     * <BR>
     * 保証金自動振替前後の判定を行うフラグ<BR>
     * false：保証金自動振替前<BR>
     * true ：保証金自動振替後<BR>
     */
    protected boolean depositAutoTransferDivFlag;

    /**
     * (当初追証発生考慮フラグ)<BR>
     * (当初追証発生考慮フラグ)<BR>
     * <BR>
     * 追証発生の判断の際、追証金額を考慮するかどうかを判定するフラグ。<BR>
     * <BR>
     * true：　@保証金自動振替前は、追証金額の発生をもって追証発生とする。<BR>
     * false：　@保証金自動振替前後にかかわらず、追証未発生金額の発生をもって追証発生とする。<BR>
     */
    protected boolean firstOpenAdddepositFlag = false;

    /**
     * (入金請求管理)<BR>
     * (コンストラクタ)<BR>
     * @@roseuid 486C243503D4
     */
    public WEB3TPPaymentRequisitionManagement()
    {

    }

    /**
     * (create入金請求管理)<BR>
     * (staticメソッド)(create入金請求管理)<BR>
     * <BR>
     * 入金請求管理インスタンスを作成する <BR>
     * <BR>
     * １）　@create入金請求管理()をコールし、戻り値を返却する。<BR>
     * [引数]<BR>
     * ・顧客：　@引数.顧客<BR>
     * ・当初追証発生考慮フラグ：　@false<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@return WEB3TPPaymentRequisitionManagement
     * @@throws WEB3BaseException
     */
    public static WEB3TPPaymentRequisitionManagement createPaymentRequisitionManagement(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        return createPaymentRequisitionManagement(l_mainAccount, false);
    }

    /**
     * (create入金請求管理)<BR>
     * (staticメソッド)(create入金請求管理)<BR>
     * <BR>
     * 入金請求管理インスタンスを作成する <BR>
     * <BR>
     * ※シーケンス図「(入金請求管理)create入金請求管理」参照<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (当初追証発生考慮フラグ)<BR>
     * (当初追証発生考慮フラグ)<BR>
     * @@return WEB3TPPaymentRequisitionManagement
     * @@roseuid 486C243503D5
     * @@throws WEB3BaseException
     */
    public static WEB3TPPaymentRequisitionManagement createPaymentRequisitionManagement(
        MainAccount l_mainAccount,
        boolean l_blnFirstOpenAdddepositFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createPaymentRequisitionManagement(MainAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            new WEB3TPPaymentRequisitionManagement();

        //getAccountId()
        long l_lngAccountId = l_mainAccount.getAccountId();

        //入金請求管理テーブルを検索
        //［検索条件］
        //　@　@・口座ID　@=　@getAccountId()の戻り値
        PaymentRequisitMngRow l_paymentRequisitMngRow = null;
        try
        {
            l_paymentRequisitMngRow =
                PaymentRequisitMngDao.findRowByAccountId(l_lngAccountId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //証券会社オブジェクトを取得する。
        Institution l_institution = l_mainAccount.getInstitution();
        //部店オブジェクトを取得する。
        Branch l_branch = l_mainAccount.getBranch();

        //保証金自動振替後かどうか判定する。
        //[引数]
        //証券会社コード：　@getInstitution().getInstitutionCode()の戻り値
        //部店コード：　@getBranch().getBranchCode()の戻り値
        boolean l_blnDepositAutoTransfer =
            isDepositAutoTransfer(l_institution.getInstitutionCode(), l_branch.getBranchCode());

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;
        //is信用口座開設(弁済区分 : String)
        boolean l_blnIsMarginAccountEstablished =
            l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        WEB3GentradeSubAccount l_subAccount = null;

        //is信用口座開設()の戻り値 == true(信用顧客)の場合、
        //以下の処理を実施する。
        if (l_blnIsMarginAccountEstablished)
        {
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = null;
            try
            {
                //getSubAccount
                //[引数]
                //2：　@株式信用取引口座（保証金）
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //create余力計算条件<標準>(補助口座)
            WEB3TPCalcCondition l_tpCalcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //find余力計算結果<信用顧客>～口座ＩＤ指定～(long)
            List l_lisCalcResultMarginParams =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報<信用顧客>(List, 余力計算条件)
            l_tpCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResultMarginParams, l_tpCalcCondition);

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                //顧客勘定残高(マスタ情報)テーブルを検索
                //［検索条件］
                //　@　@・口座ID　@　@　@　@=　@getAccountId()の戻り値
                //　@　@・補助口座ID　@=　@
                //getSubAccount(“2:株式信用取引口座（保証金）”).getSubAccountId()の戻り値
                TpCashBalanceRow l_tpCashBalanceRow =
                    TpCashBalanceDao.findRowByAccountIdSubAccountId(
                        l_lngAccountId, l_subAccount.getSubAccountId());

                //入出金注文単位テーブルを検索（預り金⇒保証金）
                //１）　@入出金注文単位テーブルを検索する。
                //　@［検索条件］
                //　@　@・口座ID　@　@　@　@=　@getAccountId()の戻り値
                //　@　@・補助口座ID　@=　@getSubAccount(“2:株式信用取引口座（保証金）”).getSubAccountId()の戻り値
                //　@　@・注文種別　@　@ =　@”1005”（振替注文(預り金から信用保証金)）
                //　@　@・受渡日　@>=　@資産余力情報<信用顧客>.get余力計算条件.get営業日(T+0)
                //　@　@・注文状態 NOT IN(“6:発注失敗(新規注文)”, “14:発注済(取消注文)”)
                //２）　@検索結果の件数分、Listに設定する。
                StringBuffer l_sbWhereDeposit = new StringBuffer();
                l_sbWhereDeposit.append(" account_id = ? ");
                l_sbWhereDeposit.append(" and sub_account_id = ? ");
                l_sbWhereDeposit.append(" and order_type = ? ");
                l_sbWhereDeposit.append(" and delivery_date >= ? ");
                l_sbWhereDeposit.append(" and order_status not in(?, ?) ");
                Object[] l_aioWhereDeposits =
                    {new Long(l_lngAccountId),
                    new Long(l_subAccount.getSubAccountId()),
                    OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                    l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0),
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED};

                List l_lisAioOrderUnitDeposits = l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhereDeposit.toString(),
                    l_aioWhereDeposits);

                //入出金注文単位テーブルを検索（保証金⇒預り金）
                //１）　@入出金注文単位テーブルを検索する。
                //　@［検索条件］
                //　@　@・口座ID　@　@　@　@=　@getAccountId()の戻り値
                //　@　@・補助口座ID　@=　@getSubAccount(“2:株式信用取引口座（保証金）”).getSubAccountId()の戻り値
                //　@　@・注文種別　@　@ =　@”1006”（振替注文(信用保証金から預り金)）
                //　@　@・受渡日　@>=　@資産余力情報<信用顧客>.get余力計算条件.get営業日(T+0)
                //　@　@・注文状態 NOT IN(“6:発注失敗(新規注文)”, “14:発注済(取消注文)”)
                //２）　@検索結果の件数分、Listに設定する。
                StringBuffer l_sbWhereMargin = new StringBuffer();
                l_sbWhereMargin.append(" account_id = ? ");
                l_sbWhereMargin.append(" and sub_account_id = ? ");
                l_sbWhereMargin.append(" and order_type = ? ");
                l_sbWhereMargin.append(" and delivery_date >= ? ");
                l_sbWhereMargin.append(" and order_status not in(?, ?) ");
                Object[] l_aioWhereMargins =
                    {new Long(l_lngAccountId),
                    new Long(l_subAccount.getSubAccountId()),
                    OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
                    l_tpCalcMargin.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_0),
                    OrderStatusEnum.NOT_ORDERED,
                    OrderStatusEnum.CANCELLED};

                List l_lisAioOrderUnitMargins = l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_sbWhereMargin.toString(),
                    l_aioWhereMargins);

                //入金請求管理の属性にセットする。
                //　@［信用顧客の場合］
                //　@　@　@・this.入金請求管理Params　@=　@入金請求管理Params  入金請求管理Paramsが0件の場合はnullをセット
                if (l_paymentRequisitMngRow == null)
                {
                    l_paymentRequisitionManagement.paymentRequisitMngParams = null;
                }
                else
                {
                    l_paymentRequisitionManagement.paymentRequisitMngParams =
                        new PaymentRequisitMngParams(l_paymentRequisitMngRow);
                }
                //　@　@　@・this.顧客勘定残高(マスタ情報)<保証金>　@=　@顧客勘定残高(マスタ情報)Params
                //        顧客勘定残高(マスタ情報)Paramsが0件の場合はnullをセット
                if (l_tpCashBalanceRow == null)
                {
                    l_paymentRequisitionManagement.tpCashBalanceParams = null;
                }
                else
                {
                    l_paymentRequisitionManagement.tpCashBalanceParams =
                        new TpCashBalanceParams(l_tpCashBalanceRow);
                }
                //　@　@　@・this.資産余力情報<信用顧客>　@=　@資産余力情報<信用顧客>
                l_paymentRequisitionManagement.tpCalcMargin = l_tpCalcMargin;
                //　@　@　@・this.資産余力情報<現物顧客>　@=　@NULL
                l_paymentRequisitionManagement.tpCalcEquity = null;
                //　@　@　@・this.入出金注文単位一覧(預り金)　@=
                //　@　@　@　@入出金注文単位テーブルの検索結果（預り金⇒保証金）
                l_paymentRequisitionManagement.aioOrderUnitListFromDepositToMargins = l_lisAioOrderUnitDeposits;
                //　@　@　@・this.入出金注文単位一覧(保証金)　@=
                //　@　@　@　@入出金注文単位テーブルの検索結果（保証金⇒預り金）
                l_paymentRequisitionManagement.aioOrderUnitListFromMarginToDeposits = l_lisAioOrderUnitMargins;
                //　@　@　@・this.信用現物判定フラグ　@=　@”1”（信用顧客）
                l_paymentRequisitionManagement.marginEquityJudgeFlag =
                    WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;
                //   ・this.保証金自動振替後判定フラグ　@=　@is保証金自動振替後()の戻り値
                l_paymentRequisitionManagement.depositAutoTransferDivFlag = l_blnDepositAutoTransfer;
                //   ・this.当初追証発生考慮フラグ　@=　@引数.当初追証発生考慮フラグ
                l_paymentRequisitionManagement.firstOpenAdddepositFlag = l_blnFirstOpenAdddepositFlag;
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //is信用口座開設()の戻り値 == false(現物顧客)の場合、
        //以下の処理を実施する。
        else
        {
            WEB3TPTradingPowerCalcEquity l_tpCalcEquity = null;
            try
            {
                //getSubAccount
                //[引数]
                //1：　@株式取引口座（預り金）
                l_subAccount =
                    (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //create余力計算条件<標準>(補助口座)
            WEB3TPCalcCondition l_tpCalcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //find余力計算結果<現物顧客>～口座ＩＤ指定～(long)
            List l_lisCalcResultEquityParams =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //資産余力情報<現物顧客>(List, 余力計算条件)
            l_tpCalcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResultEquityParams, l_tpCalcCondition);

            //入金請求管理の属性にセットする。
            //　@［現物顧客の場合］
            //　@　@　@・this.入金請求管理Params　@=　@入金請求管理Params  入金請求管理Paramsが0件の場合はnullをセット
            if (l_paymentRequisitMngRow == null)
            {
                l_paymentRequisitionManagement.paymentRequisitMngParams = null;
            }
            else
            {
                l_paymentRequisitionManagement.paymentRequisitMngParams =
                    new PaymentRequisitMngParams(l_paymentRequisitMngRow);
            }
            //　@　@　@・this.顧客勘定残高(マスタ情報)<保証金>　@=　@NULL
            l_paymentRequisitionManagement.tpCashBalanceParams = null;
            //　@　@　@・this.資産余力情報<信用顧客>　@=　@NULL
            l_paymentRequisitionManagement.tpCalcMargin = null;
            //　@　@　@・this.資産余力情報<現物顧客>　@=　@資産余力情報<現物顧客>
            l_paymentRequisitionManagement.tpCalcEquity = l_tpCalcEquity;
            //　@　@　@・this.入出金注文単位一覧(預り金)　@=　@NULL
            l_paymentRequisitionManagement.aioOrderUnitListFromDepositToMargins = null;
            //　@　@　@・this.入出金注文単位一覧(保証金)　@=　@NULL
            l_paymentRequisitionManagement.aioOrderUnitListFromMarginToDeposits = null;
            //　@　@　@・this.信用現物判定フラグ　@=　@”0”（現物顧客）
            l_paymentRequisitionManagement.marginEquityJudgeFlag =
                WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;
            //   ・this.保証金自動振替後判定フラグ　@=　@is保証金自動振替後()の戻り値
            l_paymentRequisitionManagement.depositAutoTransferDivFlag = l_blnDepositAutoTransfer;
            //   ・this.当初追証発生考慮フラグ　@=　@引数.当初追証発生考慮フラグ
            l_paymentRequisitionManagement.firstOpenAdddepositFlag = l_blnFirstOpenAdddepositFlag;
        }
        log.exiting(STR_METHOD_NAME);
        return l_paymentRequisitionManagement;
    }

    /**
     * (get入金請求管理Params)<BR>
     * (get入金請求管理Params)<BR>
     * <BR>
     * this.入金請求管理Paramsを返却する。<BR>
     * @@return PaymentRequisitMngParams
     * @@roseuid 486C243503D7
     */
    public PaymentRequisitMngParams getPaymentRequisitMngParams()
    {
        return this.paymentRequisitMngParams;
    }

    /**
     * (set入金請求管理Params)<BR>
     * (set入金請求管理Params)<BR>
     * <BR>
     * 引数.入金請求管理Paramsをthis.入金請求管理Paramsにセットする。<BR>
     * @@param l_paymentRequisitMngParams - (入金請求管理Params)<BR>
     * (入金請求管理Params)<BR>
     * @@roseuid 486C243503D8
     */
    public void setPaymentRequisitMngParams(PaymentRequisitMngParams l_paymentRequisitMngParams)
    {
        this.paymentRequisitMngParams = l_paymentRequisitMngParams;
    }

    /**
     * (get顧客勘定残高(マスタ情報)<保証金>)<BR>
     * (get顧客勘定残高(マスタ情報)<保証金>)<BR>
     * <BR>
     * this.顧客勘定残高(マスタ情報)<保証金>を返却する。<BR>
     * @@return TpCashBalanceParams
     * @@roseuid 486C243503DA
     */
    public TpCashBalanceParams getTpCashBalanceParams()
    {
        return this.tpCashBalanceParams;
    }

    /**
     * (set顧客勘定残高(マスタ情報)<保証金>)<BR>
     * (set顧客勘定残高(マスタ情報)<保証金>)<BR>
     * <BR>
     * 引数.顧客勘定残高(マスタ情報)Paramsをthis.顧客勘定残高(マスタ情報)<保証金>にセットする。<BR>
     * @@param l_tpCashBalanceParams - (顧客勘定残高(マスタ情報)Params)<BR>
     * (顧客勘定残高(マスタ情報)Params)<BR>
     * @@roseuid 486C243503E4
     */
    public void setTpCashBalanceParams(TpCashBalanceParams l_tpCashBalanceParams)
    {
        this.tpCashBalanceParams = l_tpCashBalanceParams;
    }

    /**
     * (get資産余力情報<現物顧客>)<BR>
     * (get資産余力情報<現物顧客>)<BR>
     * <BR>
     * this.資産余力情報<現物顧客>を返却する。<BR>
     * @@return WEB3TPTradingPowerCalcEquity
     * @@roseuid 486C243503E6
     */
    public WEB3TPTradingPowerCalcEquity getTpCalcEquity()
    {
        return this.tpCalcEquity;
    }

    /**
     * (set資産余力情報<現物顧客>)<BR>
     * (set資産余力情報<現物顧客>)<BR>
     * <BR>
     * 引数.資産余力情報<現物顧客>をthis.資産余力情報<現物顧客>にセットする。<BR>
     * @@param l_tpCalcEquity - (資産余力情報<現物顧客>)<BR>
     * (資産余力情報<現物顧客>)<BR>
     * @@roseuid 486C243503E7
     */
    public void setTpCalcEquity(WEB3TPTradingPowerCalcEquity l_tpCalcEquity)
    {
        this.tpCalcEquity = l_tpCalcEquity;
    }

    /**
     * (get資産余力情報<信用顧客>)<BR>
     * (get資産余力情報<信用顧客>)<BR>
     * <BR>
     * this.資産余力情報<信用顧客>を返却する。<BR>
     * @@return WEB3TPTradingPowerCalcMargin
     * @@roseuid 486C243503E9
     */
    public WEB3TPTradingPowerCalcMargin getTpCalcMargin()
    {
        return this.tpCalcMargin;
    }

    /**
     * (set資産余力情報<信用顧客>)<BR>
     * (set資産余力情報<信用顧客>)<BR>
     * <BR>
     * 引数.資産余力情報<信用顧客>をthis.資産余力情報<信用顧客>にセットする。<BR>
     * @@param l_tpCalcMargin - (資産余力情報<信用顧客>)<BR>
     * (資産余力情報<信用顧客>)<BR>
     * @@roseuid 486C243503EA
     */
    public void setTpCalcMargin(WEB3TPTradingPowerCalcMargin l_tpCalcMargin)
    {
        this.tpCalcMargin = l_tpCalcMargin;
    }

    /**
     * (get入出金注文単位一覧(預り金⇒保証金))<BR>
     * (get入出金注文単位一覧(預り金⇒保証金))<BR>
     * <BR>
     * this.入出金注文単位一覧(預り金⇒保証金)を返却する。<BR>
     * @@return List
     * @@roseuid 486C243503EC
     */
    public List getAioOrderUnitListFromDepositToMargins()
    {
        return this.aioOrderUnitListFromDepositToMargins;
    }

    /**
     * (set入出金注文単位一覧(預り金⇒保証金))<BR>
     * (set入出金注文単位一覧(預り金⇒保証金))<BR>
     * <BR>
     * 引数.入出金注文単位一覧(預り金⇒保証金)をthis.入出金注文単位一覧(預り金⇒保証金)にセットする。<BR>
     * @@param l_lisAioOrderUnitListFromDepositToMargins - (入出金注文単位一覧(預り金⇒保証金))<BR>
     * (入出金注文単位一覧(預り金⇒保証金))<BR>
     * @@roseuid 486C243503ED
     */
    public void setAioOrderUnitListFromDepositToMargins(List l_lisAioOrderUnitListFromDepositToMargins)
    {
        this.aioOrderUnitListFromDepositToMargins = l_lisAioOrderUnitListFromDepositToMargins;
    }

    /**
     * (get入出金注文単位一覧(保証金⇒預り金))<BR>
     * (get入出金注文単位一覧(保証金⇒預り金))<BR>
     * <BR>
     * this.入出金注文単位一覧(保証金⇒預り金)を返却する。<BR>
     * @@return List
     * @@roseuid 486C243503EF
     */
    public List getAioOrderUnitListFromMarginToDeposits()
    {
        return this.aioOrderUnitListFromMarginToDeposits;
    }

    /**
     * (set入出金注文単位一覧(保証金⇒預り金))<BR>
     * (set入出金注文単位一覧(保証金⇒預り金))<BR>
     * <BR>
     * 引数.入出金注文単位一覧(保証金⇒預り金)をthis.入出金注文単位一覧(保証金⇒預り金)にセットする。<BR>
     * @@param l_lisAioOrderUnitListFromMarginToDeposits - (入出金注文単位一覧(保証金⇒預り金))<BR>
     * (入出金注文単位一覧(保証金⇒預り金))<BR>
     * @@roseuid 486C243503F0
     */
    public void setAioOrderUnitListFromMarginToDeposits(List l_lisAioOrderUnitListFromMarginToDeposits)
    {
        this.aioOrderUnitListFromMarginToDeposits = l_lisAioOrderUnitListFromMarginToDeposits;
    }

    /**
     * (get信用現物判定フラグ)<BR>
     * (get信用現物判定フラグ)<BR>
     * <BR>
     * this.信用現物判定フラグを返却する。<BR>
     * @@return String
     * @@roseuid 486C243503F2
     */
    public String getMarginEquityJudgeFlag()
    {
        return this.marginEquityJudgeFlag;
    }

    /**
     * (set信用現物判定フラグ)<BR>
     * (set信用現物判定フラグ)<BR>
     * <BR>
     * 引数.信用現物判定フラグをthis.信用現物判定フラグにセットする。<BR>
     * @@param l_strMarginEquityJudgeFlag - (信用現物判定フラグ)<BR>
     * (信用現物判定フラグ)<BR>
     * @@roseuid 486C2436000C
     */
    public void setMarginEquityJudgeFlag(String l_strMarginEquityJudgeFlag)
    {
        this.marginEquityJudgeFlag = l_strMarginEquityJudgeFlag;
    }

    /**
     * (is保証金自動振替後)<BR>
     * (staticメソッド)(is保証金自動振替後)<BR>
     * <BR>
     * プロセス管理テーブルの検索結果を元に、保証金自動振替の判定結果を返却する。<BR>
     * <BR>
     * １．プロセス管理テーブルを検索する。<BR>
     * <BR>
     * 　@［検索条件］<BR>
     * 　@　@　@・プロセスID　@=　@”0005”（保証金自動振替終了）<BR>
     * 　@　@　@・証券会社コード　@=　@引数.証券会社コード<BR>
     * 　@　@　@・部店コード　@=　@引数.部店コード<BR>
     * <BR>
     * ２．検索結果を元に、判定結果を返却する。<BR>
     * <BR>
     * 　@（１） プロセス管理テーブルの検索結果　@==　@NULL　@or　@プロセス管理テーブルの検索結果が0件の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@　@FALSE（保証金自動振替前）<BR>
     * 　@（２） (1)以外の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@　@TRUE（保証金自動振替後）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * (証券会社コード)<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * (部店コード)<BR>
     * @@return boolean
     * @@roseuid 48DB5FAC02A7
     */
    public static boolean isDepositAutoTransfer(String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDepositAutoTransfer(String, String)";
        log.entering(STR_METHOD_NAME);

        ProcessManagementRow l_processManagementRow = null;
        try
        {
            //１．プロセス管理テーブルを検索する。
            //［検索条件］
            //・プロセスID　@=　@”0005”（保証金自動振替終了）
            //・証券会社コード　@=　@引数.証券会社コード
            //・部店コード　@=　@引数.部店コード
            l_processManagementRow =
                ProcessManagementDao.findRowByProcessIdInstitutionCodeBranchCode(
                    WEB3TPProcessManagementIdDef.DEPOSIT_AUTO_TRANSFER_STOP,
                    l_strInstitutionCode,
                    l_strBranchCode);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3TPPaymentRequisitionManagement." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //　@（１） プロセス管理テーブルの検索結果　@==　@NULL　@or　@プロセス管理テーブルの検索結果が0件の場合
        //［返却値］
        //FALSE（保証金自動振替前）
        //　@（２） (1)以外の場合
        //［返却値］
        //TRUE（保証金自動振替後）
        if (l_processManagementRow != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is保証金自動振替後判定フラグ)<BR>
     * (is保証金自動振替後判定フラグ)<BR>
     * <BR>
     * this.保証金自動振替後判定フラグを返却する。<BR>
     * @@return boolean
     * @@roseuid 486C24F703C8
     */
    public boolean isDepositAutoTransferDivFlag()
    {
        return this.depositAutoTransferDivFlag;
    }

    /**
     * (set保証金自動振替後判定フラグ)<BR>
     * (set保証金自動振替後判定フラグ)<BR>
     * <BR>
     * 引数.保証金自動振替後判定フラグをthis.保証金自動振替後判定フラグにセットする。<BR>
     * @@param l_blnDepositAutoTransferDivFlag - (保証金自動振替後判定フラグ)<BR>
     * (保証金自動振替後判定フラグ)<BR>
     * @@roseuid 486C24F703C9
     */
    public void setDepositAutoTransferDivFlag(boolean l_blnDepositAutoTransferDivFlag)
    {
        this.depositAutoTransferDivFlag = l_blnDepositAutoTransferDivFlag;
    }

    /**
     * (get当初追証発生考慮フラグ)<BR>
     * (get当初追証発生考慮フラグ)<BR>
     * <BR>
     * this.当初追証発生考慮フラグを返却する。<BR>
     * @@return boolean
     */
    public boolean getFirstOpenAdddepositFlag()
    {
        return this.firstOpenAdddepositFlag;
    }

    /**
     * (set当初追証発生考慮フラグ)<BR>
     * (set当初追証発生考慮フラグ)<BR>
     * <BR>
     * 引数.当初追証発生考慮フラグをthis.当初追証発生考慮フラグにセットする。<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (当初追証発生考慮フラグ)<BR>
     * (当初追証発生考慮フラグ)<BR>
     */
    public void setFirstOpenAdddepositFlag(boolean l_blnFirstOpenAdddepositFlag)
    {
        this.firstOpenAdddepositFlag = l_blnFirstOpenAdddepositFlag;
    }

    /**
     * (is不足金発生)<BR>
     * (is不足金発生)<BR>
     * <BR>
     * 不足金が発生しているか判定を行い、判定結果を返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理)is不足金発生」参照<BR>
     * @@return boolean
     * @@roseuid 48EC3FB90012
     */
    public boolean isShortfallGeneration()
    {
        final String STR_METHOD_NAME = "isShortfallGeneration()";
        log.entering(STR_METHOD_NAME);

        for (int i = 0; i <= 5; i++)
        {
            //get預り金不足額(T+n)の戻り値 > 0
            //TRUE(不足金発生)を返却
            double l_dbLackAccountBalance = this.getLackAccountBalance(i);
            if (l_dbLackAccountBalance > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is第一水準追証発生)<BR>
     * (is第一水準追証発生)<BR>
     * <BR>
     * 第一水準追証が発生しているか判定を行い、判定結果を返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理)is第一水準追証発生」参照<BR>
     * @@return boolean
     * @@roseuid 48EC4088024A
     */
    public boolean isFirstAdddeposit()
    {
        final String STR_METHOD_NAME = "isFirstAdddeposit()";
        log.entering(STR_METHOD_NAME);

        //第一水準追証未解消金額を取得する。
        double l_dblFirstAdddepositUncancelAmt = this.getFirstAdddepositUncancelAmt();

        //this.当初追証発生考慮フラグ == false の場合
        if (!this.firstOpenAdddepositFlag)
        {
            //get第一水準追証未解消金額() > 0 の場合
            if (l_dblFirstAdddepositUncancelAmt > 0)
            {
                //TRUE(第一水準追証発生)を返却
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //this.当初追証発生考慮フラグ == true の場合
        else
        {
            //保証金自動振替後判定フラグを取得する。
            boolean l_blnDepositAutoTransferDivFlag = this.isDepositAutoTransferDivFlag();

            //is保証金自動振替後判定フラグ() = true かつ get第一水準追証未解消金額() > 0 の場合
            if (l_blnDepositAutoTransferDivFlag && l_dblFirstAdddepositUncancelAmt > 0)
            {
                //TRUE(第一水準追証発生)を返却
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            //第一水準追証金額を取得する。
            double l_dblFirstAdddepositAmount = this.getFirstAdddepositAmount();

            //is保証金自動振替後判定フラグ() = false かつ get第一水準追証金額() > 0 の場合
            if (!l_blnDepositAutoTransferDivFlag && l_dblFirstAdddepositAmount > 0)
            {
                //TRUE(第一水準追証発生)を返却
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        //FALSE(第一水準追証未発生)を返却
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is第二水準追証発生)<BR>
     * (is第二水準追証発生)<BR>
     * <BR>
     * 第二水準追証が発生しているか判定を行い、判定結果を返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理)is第二水準追証発生」参照<BR>
     * @@return boolean
     * @@roseuid 48EC408F00B3
     */
    public boolean isSecondAdddeposit()
    {
        final String STR_METHOD_NAME = "isSecondAdddeposit()";
        log.entering(STR_METHOD_NAME);

        //第二水準追証未解消情報を取得する。
        WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
            this.createSecondAdddepositNotClearInfo();

        //this.当初追証発生考慮フラグ == false の場合
        if (!this.firstOpenAdddepositFlag)
        {
            //第二水準追証未解消情報.追証金額(未入金) > 0 又は
            //第二水準追証未解消情報.追証金額(請求2) > 0　@又は
            //第二水準追証未解消情報.追証金額(請求1) > 0 又は
            //第二水準追証未解消情報.追証金額(見込金額) > 0 の場合
            if (l_secondAdddepositNotClearInfo.secondDepositNonPay > 0
                || l_secondAdddepositNotClearInfo.secondDeposit2 > 0
                || l_secondAdddepositNotClearInfo.secondDeposit1 > 0
                || l_secondAdddepositNotClearInfo.secondDepositExpect > 0)
            {
                //TRUE(第二水準追証発生)を返却
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //this.当初追証発生考慮フラグ == true の場合
        else
        {
            //保証金自動振替後判定フラグを取得する。
            boolean l_blnDepositAutoTransferDivFlag = this.isDepositAutoTransferDivFlag();

            //is保証金自動振替後判定フラグ() = true かつ
            //(第二水準追証未解消情報.追証金額(未入金) > 0 又は
            //第二水準追証未解消情報.追証金額(請求2) > 0　@又は
            //第二水準追証未解消情報.追証金額(請求1) > 0 又は
            //第二水準追証未解消情報.追証金額(見込金額) > 0) の場合
            if (l_blnDepositAutoTransferDivFlag
                && (l_secondAdddepositNotClearInfo.secondDepositNonPay > 0
                    || l_secondAdddepositNotClearInfo.secondDeposit2 > 0
                    || l_secondAdddepositNotClearInfo.secondDeposit1 > 0
                    || l_secondAdddepositNotClearInfo.secondDepositExpect > 0))
            {
                //TRUE(第二水準追証発生)を返却
                log.exiting(STR_METHOD_NAME);
                return true;
            }

            //第二水準追証金額（未入金）を取得する。
            double l_dblSecondAdddepositDepositNonPay = this.getSecondAdddepositDepositNonPay();
            //第二水準追証金額（請求2）を取得する。
            double l_dblSecondAdddepositDeposit2 = this.getSecondAdddepositDeposit2();
            //第二水準追証金額（請求1）を取得する。
            double l_dblSecondAdddepositDeposit1 = this.getSecondAdddepositDeposit1();

            //is保証金自動振替後判定フラグ() = false かつ
            //(get第二水準追証金額（未入金） > 0 又は
            //get第二水準追証金額（請求2） > 0 又は
            //get第二水準追証金額（請求1） > 0 )の場合
            if (!l_blnDepositAutoTransferDivFlag
                && (l_dblSecondAdddepositDepositNonPay > 0
                    || l_dblSecondAdddepositDeposit2 > 0
                    || l_dblSecondAdddepositDeposit1 > 0))
            {
                //TRUE(第二水準追証発生)を返却
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //FALSE(第二水準追証未発生)を返却
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get顧客属性)<BR>
     * (get顧客属性)<BR>
     * <BR>
     * 「顧客属性」を返却する。<BR>
     * <BR>
     *  (a)this.入金請求管理Params == null の場合<BR>
     *  　@(1)this.信用現物判定フラグ == "0"（現物顧客） かつ <BR>
     * 　@　@　@　@　@　@this.資産余力情報<現物顧客>.余力計算条件.get預り証券評価制() == false の場合<BR>
     * 　@ 　@［返却値］<BR>
     * 　@　@　@"0"（現物・前金制）<BR>
     *  　@(2)this.信用現物判定フラグ == "0"（現物顧客） かつ <BR>
     * 　@　@　@　@　@　@this.資産余力情報<現物顧客>.余力計算条件.get預り証券評価制() == true の場合<BR>
     * 　@ 　@［返却値］<BR>
     * 　@　@　@"1"（現物・預り証券評価制）<BR>
     *  　@(3)this.信用現物判定フラグ == "1"（信用顧客）の場合<BR>
     * 　@ 　@［返却値］<BR>
     * 　@　@　@"2"（信用）<BR>
     *  (b)this.入金請求管理Params != null の場合<BR>
     *  　@［返却値］  <BR>
     *  　@this.入金請求管理Params.get顧客属性（）<BR>
     * @@return String
     * @@roseuid 48C8BCC8000A
     */
    public String getAccountAttribute()
    {
        final String STR_METHOD_NAME = "getAccountAttribute()";
        log.entering(STR_METHOD_NAME);

        //(a)this.入金請求管理Params == null の場合
        if (this.paymentRequisitMngParams == null)
        {
            //(1)this.信用現物判定フラグ == "0"（現物顧客） かつ
            //this.資産余力情報<現物顧客>.余力計算条件.get預り証券評価制() == false の場合
            //　@ 　@［返却値］
            //   "0"（現物・前金制）
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag)
                && !this.tpCalcEquity.calcCondition.isEquityEvalDiv())
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountAttributeDef.EQUITY_NOT_ASSET_EVAL;
            }

            //(2)this.信用現物判定フラグ == "0"（現物顧客） かつ
            //this.資産余力情報<現物顧客>.余力計算条件.get預り証券評価制() == true の場合
            //　@ 　@［返却値］
            //   "1"（現物・預り証券評価制）
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag)
                && this.tpCalcEquity.calcCondition.isEquityEvalDiv())
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountAttributeDef.EQUITY_ASSET_EVAL;
            }

            //(3)this.信用現物判定フラグ == "1"（信用顧客）の場合
            //［返却値］
            //"2"（信用）
            if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountAttributeDef.MARGIN;
            }
        }
        //(b)this.入金請求管理Params != null の場合
        //［返却値］
        //this.入金請求管理Params.get顧客属性（）
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getAccountAttribute();
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get計算日)<BR>
     * (get計算日)<BR>
     * <BR>
     * 「計算日」を返却する。<BR>
     * <BR>
     *  (a)this.入金請求管理Params == null の場合<BR>
     *  　@(1)this.信用現物判定フラグ == "0"（現物顧客）<BR>
     * 　@ 　@［返却値］<BR>
     * 　@　@　@ this.資産余力情報<現物顧客>.余力計算条件.get営業日(-1)<BR>
     *  　@(2)this.信用現物判定フラグ == "1"（信用顧客）の場合<BR>
     * 　@ 　@［返却値］<BR>
     * 　@　@　@ this.資産余力情報<信用顧客>.余力計算条件.get営業日(-1)<BR>
     * <BR>
     *  (b)this.入金請求管理Params != null の場合<BR>
     *  　@［返却値］  <BR>
     *  　@this.入金請求管理Params.get計算日（）<BR>
     * <BR>
     * @@return Date
     * @@roseuid 48C8BD340091
     */
    public Date getCalcDate()
    {
        final String STR_METHOD_NAME = "getCalcDate()";
        log.entering(STR_METHOD_NAME);

        //(a)this.入金請求管理Params == null の場合
        if (this.paymentRequisitMngParams == null)
        {
            //(1)this.信用現物判定フラグ == "0"（現物顧客）
            //［返却値］
            //this.資産余力情報<現物顧客>.余力計算条件.get営業日(-1)
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
            {
                log.exiting(STR_METHOD_NAME);
                return this.tpCalcEquity.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
            }
            //(2)this.信用現物判定フラグ == "1"（信用顧客）の場合
            //［返却値］
            //this.資産余力情報<信用顧客>.余力計算条件.get営業日(-1)
            else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
            {
                log.exiting(STR_METHOD_NAME);
                return this.tpCalcMargin.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_MINUS1);
            }
        }
        else
        {
            //(b)this.入金請求管理Params != null の場合
            //［返却値］
            //this.入金請求管理Params.get計算日（）
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getCalcDate();
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get立替金)<BR>
     * (get立替金)<BR>
     * <BR>
     * 「立替金」を返却する。<BR>
     * <BR>
     *  (a)this.入金請求管理Params == null の場合<BR>
     *  　@［返却値］<BR>
     *  　@0<BR>
     * <BR>
     *  (b)this.入金請求管理Params != null の場合<BR>
     *  　@［返却値］<BR>
     *  　@this.入金請求管理Params.get立替金（）<BR>
     * @@return double
     * @@roseuid 48C8BE2D01AF
     */
    public double getDebitAmount()
    {
        final String STR_METHOD_NAME = "getDebitAmount()";
        log.entering(STR_METHOD_NAME);

        //(a)this.入金請求管理Params == null の場合
        //［返却値］
        //0
        if (this.paymentRequisitMngParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //(b)this.入金請求管理Params != null の場合
        else
        {
            //this.入金請求管理Params.get立替金（）
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getDebitAmount();
        }
    }

    /**
     * (get特別立替金)<BR>
     * (get特別立替金)<BR>
     * <BR>
     * 「特別立替金」を返却する。<BR>
     * <BR>
     *  (a)this.入金請求管理Params == null の場合<BR>
     *  　@［返却値］<BR>
     *  　@0<BR>
     * <BR>
     *  (b)this.入金請求管理Params != null の場合<BR>
     *  　@［返却値］<BR>
     *  　@this.入金請求管理Params.get特別立替金（）<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C8BE6602C6
     */
    public double getSpecialDebitAmount()
    {
        final String STR_METHOD_NAME = "getSpecialDebitAmount()";
        log.entering(STR_METHOD_NAME);

        //(a)this.入金請求管理Params == null の場合
        //［返却値］
        //0
        if (this.paymentRequisitMngParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //(b)this.入金請求管理Params != null の場合
        else
        {
            //this.入金請求管理Params.get特別立替金（）
            log.exiting(STR_METHOD_NAME);
            return this.paymentRequisitMngParams.getSpecialDebitAmount();
        }
    }

    /**
     * (get預り金不足額)<BR>
     * (get預り金不足額)<BR>
     * <BR>
     * 顧客属性の判定後、「預り金不足額」を返却する。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc預り金不足額(現物顧客)(T+n)<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc預り金不足額(信用顧客)(T+n)<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48C9BD670052
     */
    public double getLackAccountBalance(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getLackAccountBalance(int)";
        log.entering(STR_METHOD_NAME);

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        //this.calc預り金不足額(現物顧客)(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcAccountBalanceShortfallEquity(l_intSpecifiedPoint);
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //［返却値］
        //this.calc預り金不足額(信用顧客)(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get期日)<BR>
     * (get期日)<BR>
     * <BR>
     * 顧客属性の判定後、引数で指定された指定日(=n)の「期日」を返却する。 <BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが-2以上5以下でない時、nullを返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行い、引数で指定された指定日(=n)の「期日」を返却する。<BR>
     * 　@（１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合<BR>
     * 　@　@[返却値] <BR>
     * 　@　@this.資産余力情報<現物顧客>.get余力計算条件.get営業日（T+n）<BR>
     * <BR>
     * 　@（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合<BR>
     * 　@　@[返却値] <BR>
     * 　@　@this.資産余力情報<信用顧客>.get余力計算条件.get営業日（T+n）<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return Date
     * @@roseuid 48D30BFC0001
     */
    public Date getDate(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getLackAccountBalance(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数チェックを行う。
        //nが-2以上5以下でない時、nullを返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_MINUS2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_MINUS1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //（１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //this.資産余力情報<現物顧客>.get余力計算条件.get営業日（T+n）
            log.exiting(STR_METHOD_NAME);
            return this.tpCalcEquity.getCalcCondition().getBizDate(l_intSpecifiedPoint);
        }

        //（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //this.資産余力情報<信用顧客>.get余力計算条件.get営業日（T+n）
            log.exiting(STR_METHOD_NAME);
            return this.tpCalcMargin.getCalcCondition().getBizDate(l_intSpecifiedPoint);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (calc精算額)<BR>
     * (calc精算額)<BR>
     * <BR>
     * 顧客属性の判定後、引数の指定日(=n)に対応した「精算額」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上1以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@精算額(T+n)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@預り金不足額(T+n)の判定を行う。<BR>
     * 　@（a）預り金不足額(T+n)　@==　@0の場合<BR>
     * 　@　@精算額(T+n)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）預り金不足額(T+n)　@!=　@0の場合<BR>
     * 　@　@４）以降の処理を行う。<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・預り金不足額(T+n)　@　@　@・・・this.calc預り金不足額(信用顧客)(T+n)<BR>
     * <BR>
     * ４）　@「精算額」を計算する。<BR>
     * 　@（a）指定日(=n)　@==　@0　@の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@精算額(T+0)　@=　@顧客勘定(T+0)　@－　@振替額(保⇒預)(T+0)<BR>
     * <BR>
     * 　@（b）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@（１）顧客勘定(T+0) > 0 の場合<BR>
     * 　@　@　@精算額(T+1)　@=　@顧客勘定(T+1)　@－　@振替額(保⇒預)(T+1)<BR>
     * 　@　@（２）上記（１）以外の場合<BR>
     * 　@　@　@精算額(T+1)　@=　@顧客勘定(T+1)　@－　@顧客勘定(T+0)　@－　@振替額(保⇒預)(T+1)<BR>
     * <BR>
     * 　@（c）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@精算額(T+1)　@=　@0<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・顧客勘定(T+n)　@　@　@・・・this.calc顧客勘定(T+n)<BR>
     * 　@　@・振替額(保⇒預)(T+n)　@　@　@・・・this.calc保証金からの振替額(日付指定)(T+n)<BR>
     * <BR>
     * ５）　@計算した精算額(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48C9BD2F025F
     */
    public double calcAdjustedAmt(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAdjustedAmt(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数チェックを行う。
        //nが0以上1以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //精算額(T+n)　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //・預り金不足額(T+n)　@　@　@・・・this.calc預り金不足額(信用顧客)(T+n)
            double l_dblAccountBalanceShortfallMargin =
                this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);

            //３）　@預り金不足額(T+n)の判定を行う。
            //（a）預り金不足額(T+n)　@==　@0の場合
            //精算額(T+n)　@=　@0を設定し、返却する。
            if (GtlUtils.Double.isZero(l_dblAccountBalanceShortfallMargin))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //４）　@「精算額」を計算する。
            //（a）指定日(=n)　@==　@0　@の場合
            //［計算式］
            //精算額(T+0)　@=　@顧客勘定(T+0)　@－　@振替額(保⇒預)(T+0)
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
            {
                //・顧客勘定(T+n)　@　@　@・・・this.calc顧客勘定(T+n)
                double l_dblAccountCalculate = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_0);
                //・振替額(保⇒預)(T+n)　@　@　@・・・this.calc保証金からの振替額(日付指定)(T+n)
                double l_dblTransferFromMarginDeposit = this.calcTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);

                BigDecimal l_bdAccountCalculate = new BigDecimal(l_dblAccountCalculate + "");
                BigDecimal l_bdTransferFromMarginDeposit = new BigDecimal(l_dblTransferFromMarginDeposit + "");

                log.exiting(STR_METHOD_NAME);
                return l_bdAccountCalculate.subtract(l_bdTransferFromMarginDeposit).doubleValue();
            }

            //（b）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && this.isDepositAutoTransferDivFlag())
            {
                //・顧客勘定(T+n)　@　@　@・・・this.calc顧客勘定(T+n)
                double l_dblAccountCalculate0 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_0);
                double l_dblAccountCalculate1 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_1);
                //・振替額(保⇒預)(T+n)　@　@　@・・・this.calc保証金からの振替額(日付指定)(T+n)
                double l_dblTransferFromMarginDeposit = this.calcTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);

                BigDecimal l_bdAccountCalculate0 = new BigDecimal(l_dblAccountCalculate0 + "");
                BigDecimal l_bdAccountCalculate1 = new BigDecimal(l_dblAccountCalculate1 + "");
                BigDecimal l_bdTransferFromMarginDeposit = new BigDecimal(l_dblTransferFromMarginDeposit + "");

                log.exiting(STR_METHOD_NAME);
                //［計算式］
                //（１）顧客勘定(T+0) > 0 の場合<BR>
                //　@精算額(T+1)　@=　@顧客勘定(T+1)　@－　@振替額(保⇒預)(T+1)<BR>
                if (l_dblAccountCalculate0 > 0)
                {
                    return l_bdAccountCalculate1.subtract(
                            l_bdTransferFromMarginDeposit).doubleValue();   
                }
                //（２）上記（１）以外の場合<BR>
                //　@精算額(T+1)　@=　@顧客勘定(T+1)　@－　@顧客勘定(T+0)　@－　@振替額(保⇒預)(T+1)<BR>
                else
                {
                    return l_bdAccountCalculate1.subtract(
                            l_bdAccountCalculate0).subtract(
                            l_bdTransferFromMarginDeposit).doubleValue();
                }
            }
            
            //（c）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
            //［計算式］
            //精算額(T+1)　@=　@0
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc日計り拘束金)<BR>
     * (calc日計り拘束金)<BR>
     * <BR>
     * 顧客属性の判定後、引数の指定日(=n)に対応した「日計り拘束金」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上1以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@日計り拘束金(T+n)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@預り金不足額(T+n)の判定を行う。<BR>
     * 　@（a）預り金不足額(T+n)　@==　@0の場合<BR>
     * 　@　@日計り拘束金(T+n)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）預り金不足額(T+n)　@!=　@0の場合<BR>
     * 　@　@４）以降の処理を行う。<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・預り金不足額(T+n)　@　@　@・・・this.calc預り金不足額(信用顧客)(T+n)<BR>
     * <BR>
     * ４）　@「日計り拘束金」を計算する。<BR>
     * 　@（a）指定日(=n)　@==　@0　@の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@日計り拘束金(T+0)　@=　@MAX（日計り拘束金(T+0)　@－　@振替額(預⇒保)(T+0),　@特別立替金実績,　@0）<BR>
     * <BR>
     * 　@（b）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@日計り拘束金(T+1)　@=　@MAX（日計り拘束金(T+1)　@－　@振替額(預⇒保)(T+1),　@0）<BR>
     * <BR>
     * 　@（c）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@日計り拘束金(T+1)　@=　@0<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・日計り拘束金(T+n)　@　@　@・・・this.get日計り拘束金(T+n)<BR>
     * 　@　@・振替額(預⇒保)(T+n)　@　@　@・・・this.calc預り金からの振替額(日付指定)(T+n)<BR>
     * 　@　@・特別立替金実績　@　@　@・・・this.資産余力情報<信用顧客>.get余力計算条件.get別立替金実績（）<BR>
     * <BR>
     * ５）　@集計した日計り拘束金(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48C8DCD30244
     */
    public double calcDayTradeRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcDayTradeRestraint(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数チェックを行う。
        //nが0以上1以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //日計り拘束金(T+n)　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //・預り金不足額(T+n)　@　@　@・・・this.calc預り金不足額(信用顧客)(T+n)
            double l_dblAccountBalanceShortfallMargin =
                this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);

            //（a）預り金不足額(T+n)　@==　@0の場合
            //日計り拘束金(T+n)　@=　@0を設定し、返却する。
            if (GtlUtils.Double.isZero(l_dblAccountBalanceShortfallMargin))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //（a）指定日(=n)　@==　@0　@の場合
            //［計算式］
            //日計り拘束金(T+0)　@=　@MAX（日計り拘束金(T+0)　@－　@振替額(預⇒保)(T+0),　@特別立替金実績,　@0）
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
            {
                //　@　@・日計り拘束金(T+n)　@　@　@・・・this.get日計り拘束金(T+n)
                double l_dblDayTradeRestraint =
                    this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0);
                //　@　@・振替額(預⇒保)(T+n)　@　@　@・・・this.calc預り金からの振替額(日付指定)(T+n)
                double l_dblAccountBalanceFromMarginDeposit =
                    this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
                //　@　@・特別立替金実績　@　@　@・・・this.資産余力情報<信用顧客>.get余力計算条件.get特別立替金実績（）
                double l_dblSpecialDebitAmount =
                    this.tpCalcMargin.getCalcCondition().getSpecialDebitAmount();

                BigDecimal l_bdDayTradeRestraint =
                    new BigDecimal(l_dblDayTradeRestraint + "");
                BigDecimal l_bdAccountBalanceFromMarginDeposit =
                    new BigDecimal(l_dblAccountBalanceFromMarginDeposit + "");

                double l_dblMaxAmount = Math.max(l_bdDayTradeRestraint.subtract(
                    l_bdAccountBalanceFromMarginDeposit).doubleValue(), l_dblSpecialDebitAmount);
                log.exiting(STR_METHOD_NAME);
                return Math.max(l_dblMaxAmount, 0);
            }

            //（b）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合
            //［計算式］
            //日計り拘束金(T+1)　@=　@MAX（日計り拘束金(T+1)　@－　@振替額(預⇒保)(T+1),　@0）
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && this.isDepositAutoTransferDivFlag())
            {
                //　@　@・日計り拘束金(T+n)　@　@　@・・・this.get日計り拘束金(T+n)
                double l_dblDayTradeRestraint =
                    this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1);
                //　@　@・振替額(預⇒保)(T+n)　@　@　@・・・this.calc預り金からの振替額(日付指定)(T+n)
                double l_dblAccountBalanceFromMarginDeposit =
                    this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);

                BigDecimal l_bdDayTradeRestraint =
                    new BigDecimal(l_dblDayTradeRestraint + "");
                BigDecimal l_bdAccountBalanceFromMarginDeposit =
                    new BigDecimal(l_dblAccountBalanceFromMarginDeposit + "");

                log.exiting(STR_METHOD_NAME);
                return Math.max(l_bdDayTradeRestraint.subtract(
                        l_bdAccountBalanceFromMarginDeposit).doubleValue(), 0);
            }

            //（c）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
            //［計算式］
            //日計り拘束金(T+1)　@=　@0
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get保証金からの振替額)<BR>
     * (get保証金からの振替額)<BR>
     * <BR>
     * 顧客属性の判定後、引数の指定日(=n)に対応した「保証金からの振替額」を返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上1以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@保証金からの振替額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@預り金不足額(T+n)の判定を行う。<BR>
     * 　@（a）預り金不足額(T+n)　@==　@0の場合<BR>
     * 　@　@保証金からの振替額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）預り金不足額(T+n)　@!=　@0の場合<BR>
     * 　@　@４）以降の処理を行う。<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・預り金不足額(T+n)　@　@　@・・・this.calc預り金不足額(信用顧客)(T+n)<BR>
     * <BR>
     * ４）　@「保証金からの振替額」を設定する。<BR>
     * <BR>
     * 　@(a)指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@［設定値］<BR>
     * 　@　@保証金からの振替額　@=　@0<BR>
     * <BR>
     * 　@(b)上記(a)以外 の場合<BR>
     * 　@　@［設定値］<BR>
     * 　@　@保証金からの振替額　@=　@集計後振替額<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・集計後振替額　@　@　@・・・this.calc保証金からの振替額(日付指定)(T+n)<BR>
     * <BR>
     * ５）　@集計した保証金からの振替額(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48C9BCE102E4
     */
    public double getTransferFromMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getTransferFromMarginDeposit(int)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数チェックを行う。
        //nが0以上1以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //保証金からの振替額　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //・預り金不足額(T+n)　@　@　@・・・this.calc預り金不足額(信用顧客)(T+n)
            double l_accountBalanceShortfallMargin =
                this.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);

            //（a）預り金不足額(T+n)　@==　@0の場合
            //保証金からの振替額　@=　@0を設定し、返却する。
            if (GtlUtils.Double.isZero(l_accountBalanceShortfallMargin))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //（a）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
            if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //(b)上記(a)以外 の場合
            //保証金からの振替額　@=　@集計後振替額
            //集計後振替額　@　@　@・・・this.calc保証金からの振替額(日付指定)(T+n)
            else
            {
                log.exiting(STR_METHOD_NAME);
                return this.calcTransferFromMarginDeposit(l_intSpecifiedPoint);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (get第一水準追証経過日数)<BR>
     * (get第一水準追証経過日数)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証経過日数」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@第一水準追証経過日数　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@表示条件の判定を行い、値を返却する。<BR>
     * 　@（a）this.保証金自動振替後判定フラグ　@==　@true の場合<BR>
     * 　@　@（１）第一水準追証金額　@>　@0の場合<BR>
     * 　@　@　@[1]第一水準追証未解消金額　@<=　@0の場合<BR>
     * 　@　@　@　@［設定値］<BR>
     * 　@　@　@　@第一水準追証経過日数　@=　@1<BR>
     * <BR>
     * 　@　@　@[2]  [1]以外の場合<BR>
     * 　@　@　@　@［設定値］<BR>
     * 　@　@　@　@第一水準追証経過日数　@=　@経過日数　@＋　@1<BR>
     * <BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@［設定値］<BR>
     * 　@　@　@第一水準追証経過日数　@=　@0<BR>
     * <BR>
     * 　@（b）(a)以外の場合<BR>
     * 　@　@（１）this.入金請求管理Params == nullの場合<BR>
     * 　@　@［設定値］<BR>
     * 　@　@第一水準追証経過日数　@=　@0<BR>
     * <BR>
     * 　@　@（２）this.入金請求管理Params.get第一水準追証経過日数（） == 0 の場合<BR>
     * 　@　@［設定値］<BR>
     * 　@　@第一水準追証経過日数　@=　@0<BR>
     * <BR>
     * 　@　@（３）上記（１）（２）以外の場合 <BR>
     * 　@　@［設定値］<BR>
     * 　@　@第一水準追証経過日数　@=　@経過日数　@＋　@1<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・第一水準追証金額　@　@　@・・・this.calc第一水準追証金額（）<BR>
     * 　@　@・第一水準追証未解消金額　@　@　@・・・this.calc第一水準追証未解消金額（）<BR>
     * 　@　@・経過日数　@　@　@・・・this.入金請求管理Params.get第一水準追証経過日数（）<BR>
     * 　@　@　@ただし、this.入金請求管理Params == nullの場合は、経過日数 = 0 とする。<BR>
     * <BR>
     * ３）　@設定した第一水準追証経過日数を返却する。<BR>
     * @@return int
     * @@roseuid 48C9BE4F0301
     */
    public int getFirstAdddepositPassDay()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositPassDay()";
        log.entering(STR_METHOD_NAME);

        int l_intFirstAdddepositPassDay = 0;

        //第一水準追証金額を取得する。
        double l_dblFirstAdddepositAmount = this.calcFirstAdddepositAmount();

        //第一水準追証未解消金額を取得する。
        double l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositUncancelAmt();

        //経過日数を取得する。
        double l_dblPassDay = 0;
        if (this.paymentRequisitMngParams == null)
        {
            l_dblPassDay = 0;
        }
        else
        {
            l_dblPassDay = this.paymentRequisitMngParams.getFirstDepositPassDay();
        }

        //１）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //第一水準追証経過日数　@=　@0を設定し、返却する。
            return 0;
        }

        //b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@表示条件の判定を行い、値を返却する。
            //（a）this.保証金自動振替後判定フラグ　@==　@true の場合
            if (this.depositAutoTransferDivFlag)
            {
                //（１）第一水準追証金額　@>　@0の場合
                if (l_dblFirstAdddepositAmount > 0)
                {
                    //[1]第一水準追証未解消金額　@<=　@0の場合
                    if (l_dblFirstAdddepositUncancelAmt < 0 || GtlUtils.Double.isZero(l_dblFirstAdddepositUncancelAmt))
                    {
                        //［設定値］
                        //第一水準追証経過日数　@=　@1
                        l_intFirstAdddepositPassDay = 1;
                    }
                    //[2] [1]以外の場合
                    else
                    {
                        //［設定値］
                        //第一水準追証経過日数　@=　@経過日数　@＋　@1
                        l_intFirstAdddepositPassDay =
                            new BigDecimal(l_dblPassDay + "").add(new BigDecimal("1")).intValue();
                    }
                }
                //（２）(1)以外の場合
                else
                {
                    //［設定値］
                    //第一水準追証経過日数　@=　@0
                    l_intFirstAdddepositPassDay = 0;
                }
            }
            //（b）(a)以外の場合
            else
            {
                //（１）this.入金請求管理Params == nullの場合
                //［設定値］ 
                //第一水準追証経過日数　@=　@0
                if (this.paymentRequisitMngParams == null)
                {
                    l_intFirstAdddepositPassDay = 0;
                }
                //（２）this.入金請求管理Params.get第一水準追証経過日数（） == 0 の場合
                //［設定値］
                //第一水準追証経過日数　@=　@0
                else if (GtlUtils.Double.isZero(this.paymentRequisitMngParams.getFirstDepositPassDay()))
                {
                    l_intFirstAdddepositPassDay = 0;
                }
                //（３）上記（１）（２）以外の場合
                //［設定値］
                //第一水準追証経過日数　@=　@経過日数　@＋　@1
                else
                {
                    l_intFirstAdddepositPassDay =
                        new BigDecimal(l_dblPassDay + "").add(new BigDecimal("1")).intValue();
                }

            }
        }

        log.exiting(STR_METHOD_NAME);

        //３）　@設定した第一水準追証経過日数を返却する。
        return l_intFirstAdddepositPassDay;
    }

    /**
     * (get第一水準追証有効経過日数)<BR>
     * (get第一水準追証有効経過日数)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証有効経過日数」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@ 0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * <BR>
     * 　@　@(1)this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件(<BR>
     * 　@　@　@　@"first.margin.pass.day1") == null　@の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@　@999<BR>
     * <BR>
     * 　@　@(2)this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件(<BR>
     * 　@　@　@　@"first.margin.pass.day1") != null　@の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@　@this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件<BR>
     * 　@　@　@　@("first.margin.pass.day1")<BR>
     * @@return int
     * @@roseuid 48C9BF0201EA
     */
    public int getFirstAdddepositPassDayValid()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositPassDayValid()";
        log.entering(STR_METHOD_NAME);

        int l_intFirstAdddepositPassDayValid = 0;

        //１）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //0
            l_intFirstAdddepositPassDayValid = 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //(1)this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件(
            //"first.margin.pass.day1") == null　@の場合
            if (this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1) == null)
            {
                //［返却値］
                //999
                l_intFirstAdddepositPassDayValid = 999;
            }
            //(2)this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.margin.pass.day1") != null　@の場合
            else
            {
                //［返却値］
                //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.margin.pass.day1")
                String l_strInstBranCalcCondition =
                    this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                        WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1);

                l_intFirstAdddepositPassDayValid = Integer.parseInt(l_strInstBranCalcCondition);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_intFirstAdddepositPassDayValid;
    }

    /**
     * (get第一水準追証発生日)<BR>
     * (get第一水準追証発生日)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証発生日」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@第一水準追証発生日　@=　@NULLを設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@「第一水準追証発生日」を設定する。<BR>
     * 　@（a）this.保証金自動振替後判定フラグ　@==　@true の場合<BR>
     * 　@　@（１）「第一水準追証経過日数　@==　@0」の場合<BR>
     * 　@　@　@［設定値］<BR>
     * 　@　@　@第一水準追証発生日　@=　@NULL<BR>
     * <BR>
     * 　@　@（２）「第一水準追証経過日数　@==　@1」の場合<BR>
     * 　@　@　@［設定値］<BR>
     * 　@　@　@第一水準追証発生日　@=　@期日(T+0)<BR>
     * <BR>
     * 　@　@（３）(1)、(2)以外の場合<BR>
     * 　@　@　@［設定値］<BR>
     * 　@　@　@第一水準追証発生日　@=　@発生日<BR>
     * <BR>
     * 　@（b）(a)以外の場合<BR>
     * 　@　@［設定値］<BR>
     * 　@　@第一水準追証発生日　@=　@発生日<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・第一水準追証経過日数　@　@　@・・・this.get第一水準追証経過日数（）<BR>
     * 　@　@・発生日　@　@　@・・・this.入金請求管理Params.get第一水準追証発生日（）<BR>
     * 　@　@　@ただし、this.入金請求管理Params == null の場合は、発生日 = null とする。<BR>
     * 　@　@・期日(T+0)　@　@　@・・・this.get期日(T+0)<BR>
     * <BR>
     * ３）　@設定した第一水準追証発生日を返却する。<BR>
     * @@return Date
     * @@roseuid 48C9BF590317
     */
    public Date getFirstAdddepositOccurredDate()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositOccurredDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datFirstAdddepositOccurredDate = null;

        //第一水準追証経過日数を取得する。
        int l_intFirstAdddepositPassDay = this.getFirstAdddepositPassDay();

        //発生日を取得する。
        Timestamp l_tsOccurredDate = null;
        if (this.paymentRequisitMngParams == null)
        {
            l_tsOccurredDate = null;
        }
        else
        {
            l_tsOccurredDate = this.paymentRequisitMngParams.getFirstDepositOccurredDate();
        }

        //期日(T+0)を取得する。
        Date l_dat = this.getDate(WEB3TPSpecifiedPointDef.T_0);

        //１）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //第一水準追証発生日　@=　@NULLを設定し、返却する。
            return null;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@「第一水準追証発生日」を設定する。
            //（a）this.保証金自動振替後判定フラグ　@==　@true の場合
            if (this.depositAutoTransferDivFlag)
            {
                //（１）「第一水準追証経過日数　@==　@0」の場合
                if (l_intFirstAdddepositPassDay == 0)
                {
                    //［設定値］
                    //第一水準追証発生日　@=　@NULL
                    l_datFirstAdddepositOccurredDate = null;
                }
                //（２）「第一水準追証経過日数　@==　@1」の場合
                else if (l_intFirstAdddepositPassDay == 1)
                {
                    //［設定値］
                    //第一水準追証発生日　@=　@期日(T+0)
                    l_datFirstAdddepositOccurredDate = l_dat;
                }
                //（３）(1)、(2)以外の場合
                else
                {
                    //［設定値］
                    //第一水準追証発生日　@=　@発生日
                    l_datFirstAdddepositOccurredDate = l_tsOccurredDate;
                }
            }
            //（b）(a)以外の場合
            else
            {
                //［設定値］
                //第一水準追証発生日　@=　@発生日
                l_datFirstAdddepositOccurredDate = l_tsOccurredDate;
            }
        }

        log.exiting(STR_METHOD_NAME);

        //３）　@設定した第一水準追証発生日を返却する。
        return l_datFirstAdddepositOccurredDate;
    }

    /**
     * (get第一水準追証保証金率)<BR>
     * (get第一水準追証保証金率)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証保証金率」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@第一水準追証保証金率　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@第一水準追証保証金率を返却する。<BR>
     * 　@（a）this.保証金自動振替後判定フラグ　@==　@true の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc保証金率（）<BR>
     * <BR>
     * 　@（b）this.保証金自動振替後判定フラグ　@==　@false かつ this.入金請求管理Params == NULL の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（c）this.保証金自動振替後判定フラグ　@==　@false かつ this.入金請求管理Params != NULL の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.入金請求管理Params.get保証金預託率()<BR>
     * @@return double
     * @@roseuid 48C9C0810268
     */
    public double getFirstAdddepositMarginDepositRate()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositMarginDepositRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositMarginDepositRate = 0;

        //１）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //第一水準追証保証金率　@=　@0を設定し、返却する。
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@第一水準追証保証金率を返却する。
            //（a）this.保証金自動振替後判定フラグ　@==　@true の場合
            if (this.depositAutoTransferDivFlag)
            {
                //［返却値］
                //this.calc保証金率（）
                l_dblFirstAdddepositMarginDepositRate = this.calcMarginDepositRate();
            }

            //（b）this.保証金自動振替後判定フラグ　@==　@false かつ this.入金請求管理Params == NULL の場合
            if (!this.depositAutoTransferDivFlag && this.paymentRequisitMngParams == null)
            {
                //［返却値］
                //0
                l_dblFirstAdddepositMarginDepositRate = 0;
            }

            //（c）this.保証金自動振替後判定フラグ　@==　@false かつ this.入金請求管理Params != NULL の場合
            if (!this.depositAutoTransferDivFlag && this.paymentRequisitMngParams != null)
            {
                //［返却値］
                //this.入金請求管理Params.get保証金預託率()
                l_dblFirstAdddepositMarginDepositRate = this.paymentRequisitMngParams.getMarginDepositRate();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositMarginDepositRate;
    }

    /**
     * (get第一水準追証保証金維持率)<BR>
     * (get第一水準追証保証金維持率)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証保証金維持率」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null かつ<BR>
     * 　@　@　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1") == NULLの場合<BR>
     * 　@ 　@［返却値］<BR>
     * 　@ 　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params == null かつ<BR>
     * 　@　@　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1") != NULLの場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1")<BR>
     * <BR>
     * 　@　@（3）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第一水準保証金維持率（）<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C1790105
     */
    public double getFirstAdddepositDepositRate()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositDepositRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositDepositRate = 0;

        //１）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //0
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositDepositRate;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null かつ
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1") == NULLの場合
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1) == null)
            {
                //［返却値］
                //0
                l_dblFirstAdddepositDepositRate = 0;
            }
            //（2）this.入金請求管理Params == null かつ
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1") != NULLの場合
            else if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1) != null)
            {
                //［返却値］
                //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1")
                l_dblFirstAdddepositDepositRate = Double.parseDouble(
                    this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                        WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1));
            }
            //（3）this.入金請求管理Params != null の場合
            else if (this.paymentRequisitMngParams != null)
            {
                //［返却値］
                //this.入金請求管理Params.get第一水準保証金維持率（）
                l_dblFirstAdddepositDepositRate = this.paymentRequisitMngParams.getFirstDepositRate();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositDepositRate;
    }

    /**
     * (get第一水準追証金額)<BR>
     * (get第一水準追証金額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証金額」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@ 0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第一水準追証金額（）<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C51E02F7
     */
    public double getFirstAdddepositAmount()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositAmount()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositAmount = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositAmount;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            //0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return l_dblFirstAdddepositAmount;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            //this.入金請求管理Params.get第一水準追証金額（）
            if (this.paymentRequisitMngParams != null)
            {
                log.exiting(STR_METHOD_NAME);
                return this.paymentRequisitMngParams.getFirstDepositAmount();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositAmount;
    }

    /**
     * (get第一水準追証決済必要額)<BR>
     * (get第一水準追証決済必要額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証決済必要額」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@ 0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第一水準追証決済必要額（）<BR>
     * <BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C51E0307
     */
    public double getFirstAdddepositSettlement()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositSettlement()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositSettlement = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositSettlement;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場
            //［返却値］
            //0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return l_dblFirstAdddepositSettlement;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            //this.入金請求管理Params.get第一水準追証決済必要額（）
            if (this.paymentRequisitMngParams != null)
            {
                log.exiting(STR_METHOD_NAME);
                return this.paymentRequisitMngParams.getFirstSettlement();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositSettlement;
    }

    /**
     * (get第一水準追証保証金増減)<BR>
     * (get第一水準追証保証金増減)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証保証金増減」を返却する。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc保証金増減（）<BR>
     * @@return double
     * @@roseuid 48C9C5870332
     */
    public double getFirstAdddepositMarginDepositInDe()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositMarginDepositInDe()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositMarginDepositInDe = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositMarginDepositInDe;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //［返却値］
        //this.calc保証金増減（）
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcMarginDepositInDe();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositMarginDepositInDe;
    }

    /**
     * (get第一水準追証保証金増減（見込金額）)<BR>
     * (get第一水準追証保証金増減(見込金額))<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証保証金増減(見込金額)」を返却する。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc保証金増減(見込金額)（）<BR>
     * @@return double
     * @@roseuid 48C9C67D033A
     */
    public double getFirstAdddepositMarginDepositInDeExpect()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositMarginDepositInDeExpect()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositMarginDepositInDeExpect = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositMarginDepositInDeExpect;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //［返却値］
        //this.calc保証金増減(見込金額)（）
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return this.calcMarginDepositInDeExpect();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositMarginDepositInDeExpect;
    }

    /**
     * (get第一水準追証決済済建玉)<BR>
     * (get第一水準追証決済済建玉)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証決済済建玉」を返却する。<BR>
     * <BR>
     * 　@（１）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 <BR>
     * 　@　@［返却値］  <BR>
     * 　@　@0 <BR>
     * <BR>
     * 　@（２）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 <BR>
     * 　@　@［返却値］  <BR>
     * 　@　@this.get当日返済建玉代金（）<BR>
     * @@return double
     * @@roseuid 48C9C72101DD
     */
    public double getFirstAdddepositSettledContract()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositSettledContract()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositSettledContract = 0;

        //（１）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        //0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（２）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //［返却値］
        //this.get当日返済建玉代金（）
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblFirstAdddepositSettledContract = this.getTodayRepayContractAmount();
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositSettledContract;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositSettledContract;
    }

    /**
     * (get第一水準追証未解消金額)<BR>
     * (get第一水準追証未解消金額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証未解消金額」を算出し、返却する。 <BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（１）this.保証金自動振替後判定フラグ　@==　@true の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.calc第一水準追証金額（）<BR>
     * <BR>
     * 　@　@（２）this.保証金自動振替後判定フラグ　@==　@false の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.calc第一水準追証未解消金額（）<BR>
     * @@return double
     * @@roseuid 48C9C57502D5
     */
    public double getFirstAdddepositUncancelAmt()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositUncancelAmt()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositUncancelAmt = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //0
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositUncancelAmt;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（１）this.保証金自動振替後判定フラグ　@==　@true の場合
            if (this.depositAutoTransferDivFlag)
            {
                //［返却値］
                //this.calc第一水準追証金額（）
                l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositAmount();
            }
            //（２）this.保証金自動振替後判定フラグ　@==　@false の場合
            else
            {
                //［返却値］
                //this.calc第一水準追証未解消金額（）
                l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositUncancelAmt();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositUncancelAmt;
    }

    /**
     * (get第一水準追証未解消決済必要額)<BR>
     * (get第一水準追証未解消決済必要額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証未解消決済必要額」を算出し、返却する。 <BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（１）this.保証金自動振替後判定フラグ　@==　@true の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.calc第一水準追証決済必要額（）<BR>
     * <BR>
     * 　@　@（２）this.保証金自動振替後判定フラグ　@==　@false の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.calc第一水準追証未解消決済必要額（）<BR>
     * @@return double
     * @@roseuid 48C9C7D202D1
     */
    public double getFirstAdddepositUncancelSettleRequiredAmt()
    {
        final String STR_METHOD_NAME = "getFirstAdddepositUncancelSettleRequiredAmt()";
        log.entering(STR_METHOD_NAME);

        double l_dblFirstAdddepositUncancelSettleRequiredAmt = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //0
            log.exiting(STR_METHOD_NAME);
            return l_dblFirstAdddepositUncancelSettleRequiredAmt;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（１）this.保証金自動振替後判定フラグ　@==　@true の場合
            if (this.depositAutoTransferDivFlag)
            {
                //［返却値］
                //this.calc第一水準追証決済必要額（）
                l_dblFirstAdddepositUncancelSettleRequiredAmt = this.calcFirstAdddepositSettlement();
            }
            //（２）this.保証金自動振替後判定フラグ　@==　@false の場合
            else
            {
                //［返却値］
                //this.calc第一水準追証未解消決済必要額（）
                l_dblFirstAdddepositUncancelSettleRequiredAmt = this.calcFirstAdddepositUncancelSettleRequiredAmt();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblFirstAdddepositUncancelSettleRequiredAmt;
    }

    /**
     * (get第二水準追証期日（請求2）)<BR>
     * (get第二水準追証期日(請求2))<BR>
     * <BR>
     * 顧客属性の判定後、第二水準追証期日(請求2)を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@強制開始時刻を取得する。<BR>
     * 　@（a）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場合<BR>
     * 　@　@［取得先］ <BR>
     * 　@　@強制開始時刻　@=　@0000<BR>
     * <BR>
     * 　@（b）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場合<BR>
     * 　@　@［取得先］ <BR>
     * 　@　@強制開始時刻　@=　@this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）<BR>
     * <BR>
     * ３）　@第二水準追証期日(請求2)を返却する。<BR>
     * 　@［返却値］<BR>
     * 　@第二水準追証期日(請求2)　@=　@期日(T+0)　@＋　@強制開始時刻<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・期日(T+0)　@　@　@・・・this.get期日(T+0)<BR>
     * 　@　@・強制開始時刻　@　@　@・・・２）で取得した強制開始時刻<BR>
     * @@return Date
     * @@roseuid 48C9C8770302
     */
    public Date getSecondAdddepositCloseDate2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositCloseDate2()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositCloseDate2 = null;

        //期日(T+0)を取得する。
        Date l_dat0 = this.getDate(WEB3TPSpecifiedPointDef.T_0);

        //強制開始時刻
        String l_strStartTime = null;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositCloseDate2;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@強制開始時刻を取得する。
            //（a）this.資産余力情報<信用顧客>.get余力計算条件.
            //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場合
            if (this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT) == null)
            {
                //［取得先］
                //強制開始時刻　@=　@0000
                l_strStartTime = "0000";
            }
            //（b）this.資産余力情報<信用顧客>.get余力計算条件.
            //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場合
            else
            {
                //［取得先］
                //強制開始時刻　@=　@this.資産余力情報<信用顧客>.get余力計算条件.
                //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”)
                l_strStartTime = this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT);
            }

            //３）　@第二水準追証期日(請求2)を返却する。
            //［返却値］
            //第二水準追証期日(請求2)　@=　@期日(T+0)　@＋　@強制開始時刻
            String l_strSecondAdddepositCloseDate2 =
                WEB3DateUtility.formatDate(l_dat0, WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strStartTime;

            l_datSecondAdddepositCloseDate2 = WEB3DateUtility.getDate(
                l_strSecondAdddepositCloseDate2,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositCloseDate2;
    }

    /**
     * (get第二水準追証期日（請求1）)<BR>
     * (get第二水準追証期日(請求1))<BR>
     * <BR>
     * 顧客属性の判定後、第二水準追証期日(請求1)を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@強制開始時刻を取得する。<BR>
     * 　@（a）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場合<BR>
     * 　@　@［取得先］ <BR>
     * 　@　@強制開始時刻　@=　@0000<BR>
     * <BR>
     * 　@（b）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場合<BR>
     * 　@　@［取得先］ <BR>
     * 　@　@強制開始時刻　@=　@this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）<BR>
     * <BR>
     * ３）　@第二水準追証期日(請求1)を返却する。<BR>
     * 　@［返却値］<BR>
     * 　@第二水準追証期日(請求1)　@=　@期日(T+1)　@＋　@強制開始時刻<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・期日(T+1)　@　@　@・・・this.get期日(T+1)<BR>
     * 　@　@・強制開始時刻　@　@　@・・・２）で取得した強制開始時刻<BR>
     * @@return Date
     * @@roseuid 48C9C8AF0268
     */
    public Date getSecondAdddepositCloseDate1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositCloseDate1()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositCloseDate1 = null;

        //期日(T+1)を取得する。
        Date l_dat1 = this.getDate(WEB3TPSpecifiedPointDef.T_1);

        //強制開始時刻
        String l_strStartTime = null;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //NULL
            l_datSecondAdddepositCloseDate1 = null;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@強制開始時刻を取得する。
            //（a）this.資産余力情報<信用顧客>.get余力計算条件.
            //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場合
            if (this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT) == null)
            {
                //［取得先］
                //強制開始時刻　@=　@0000
                l_strStartTime = "0000";
            }
            //（b）this.資産余力情報<信用顧客>.get余力計算条件.
            //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場合
            else
            {
                //［取得先］
                //強制開始時刻　@=　@this.資産余力情報<信用顧客>.get余力計算条件.
                //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”)
                l_strStartTime = this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT);
            }

            //３）　@第二水準追証期日(請求1)を返却する。
            //［返却値］
            //第二水準追証期日(請求1)　@=　@期日(T+1)　@＋　@強制開始時刻
            String l_strSecondAdddepositCloseDate1 =
                WEB3DateUtility.formatDate(l_dat1, WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strStartTime;

            l_datSecondAdddepositCloseDate1 = WEB3DateUtility.getDate(
                l_strSecondAdddepositCloseDate1,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositCloseDate1;
    }

    /**
     * (get第二水準追証期日（見込金額）)<BR>
     * (get第二水準追証期日(見込金額))<BR>
     * <BR>
     * 顧客属性と保証金自動振替後判定フラグの判定後、第二水準追証期日(見込金額)を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@保証金自動振替後判定フラグの判定を行う。<BR>
     * <BR>
     * 　@（a）is保証金自動振替後判定フラグ() == TRUE の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * 　@（b）is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * <BR>
     * ３）　@強制開始時刻を取得する。<BR>
     * 　@（a）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場合<BR>
     * 　@　@［取得先］ <BR>
     * 　@　@強制開始時刻　@=　@0000<BR>
     * <BR>
     * 　@（b）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場合<BR>
     * 　@　@［取得先］ <BR>
     * 　@　@強制開始時刻　@=　@this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）<BR>
     * <BR>
     * ４）　@第二水準追証期日(見込金額)を返却する。<BR>
     * 　@［返却値］<BR>
     * 　@第二水準追証期日(見込金額)　@=　@期日(T+2)　@＋　@強制開始時刻<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・期日(T+2)　@　@　@・・・this.get期日(T+2)<BR>
     * 　@　@・強制開始時刻　@　@　@・・・３）で取得した強制開始時刻<BR>
     * @@return Date
     * @@roseuid 48C9C8AF03C0
     */
    public Date getSecondAdddepositCloseDateExpect()
    {
        final String STR_METHOD_NAME = "SecondAdddepositCloseDateExpect()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositCloseDateExpect = null;

        //期日(T+2)を取得する。
        Date l_dat2 = this.getDate(WEB3TPSpecifiedPointDef.T_2);

        //強制開始時刻
        String l_strStartTime = null;

        //１）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //NULL
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@保証金自動振替後判定フラグの判定を行う。
            //（a）is保証金自動振替後判定フラグ() == TRUE の場合
            //３）以降の処理を行う。
            if (this.isDepositAutoTransferDivFlag())
            {
                //３）　@強制開始時刻を取得する。
                //（a）this.資産余力情報<信用顧客>.get余力計算条件.
                //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場合
                if (this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT) == null)
                {
                    //［取得先］
                    //強制開始時刻　@=　@0000
                    l_strStartTime = "0000";
                }
                //（b）this.資産余力情報<信用顧客>.get余力計算条件.
                //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場合
                else
                {
                    //［取得先］
                    //強制開始時刻　@=　@this.資産余力情報<信用顧客>.get余力計算条件.
                    //get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）
                    l_strStartTime = this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                        WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT);
                }
            }
            //（b）is保証金自動振替後判定フラグ() == FALSE の場合
            else
            {
                //［返却値］
                //NULL
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //４）　@第二水準追証期日(見込金額)を返却する。
            //［返却値］
            //第二水準追証期日(見込金額)　@=　@期日(T+2)　@＋　@強制開始時刻
            String l_strSecondAdddepositCloseDateExpect =
                WEB3DateUtility.formatDate(l_dat2, WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strStartTime;

            l_datSecondAdddepositCloseDateExpect = WEB3DateUtility.getDate(
                l_strSecondAdddepositCloseDateExpect,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositCloseDateExpect;
    }

    /**
     * (get第二水準追証発生日（請求2）)<BR>
     * (get第二水準追証発生日(請求2))<BR>
     * <BR>
     * 顧客属性の判定後、第二水準追証発生日(請求2)を返却する。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.get期日(T-2)<BR>
     * @@return Date
     * @@roseuid 48C9C9150135
     */
    public Date getSecondAdddepositDepositOccurredDate2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositOccurredDate2()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositDepositOccurredDate2 = null;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositDepositOccurredDate2;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //this.get期日(T-2)
            log.exiting(STR_METHOD_NAME);
            return this.getDate(WEB3TPSpecifiedPointDef.T_MINUS2);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositDepositOccurredDate2;
    }

    /**
     * (get第二水準追証発生日（請求1）)<BR>
     * (get第二水準追証発生日(請求1))<BR>
     * <BR>
     * 顧客属性の判定後、第二水準追証発生日(請求1)を返却する。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.get期日(T-1)<BR>
     * @@roseuid 48C9C93A0146
     * @@return Date
     */
    public Date getSecondAdddepositDepositOccurredDate1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositOccurredDate1()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositDepositOccurredDate1 = null;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositDepositOccurredDate1;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //this.get期日(T-1)
            log.exiting(STR_METHOD_NAME);
            return this.getDate(WEB3TPSpecifiedPointDef.T_MINUS1);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositDepositOccurredDate1;
    }

    /**
     * (get第二水準追証発生日（見込金額）)<BR>
     * (get第二水準追証発生日(見込金額))<BR>
     * <BR>
     * 顧客属性と保証金自動振替後判定フラグの判定後、第二水準追証発生日(見込金額)を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@保証金自動振替後判定フラグの判定を行う。<BR>
     * <BR>
     * 　@（a）is保証金自動振替後判定フラグ() == TRUE の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.get期日(T+0)<BR>
     * <BR>
     * 　@（b）is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@NULL<BR>
     * @@return Date
     * @@roseuid 48C9C93A0250
     */
    public Date getSecondAdddepositDepositOccurredDateExpect()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositOccurredDateExpect()";
        log.entering(STR_METHOD_NAME);

        Date l_datSecondAdddepositDepositOccurredDateExpect = null;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //［返却値］
            //NULL
            log.exiting(STR_METHOD_NAME);
            return l_datSecondAdddepositDepositOccurredDateExpect;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@保証金自動振替後判定フラグの判定を行う。
            //（a）is保証金自動振替後判定フラグ() == TRUE の場合
            if (this.isDepositAutoTransferDivFlag())
            {
                //［返却値］
                //this.get期日(T+0)
                log.exiting(STR_METHOD_NAME);
                return this.getDate(WEB3TPSpecifiedPointDef.T_0);
            }
            //（b）is保証金自動振替後判定フラグ() == FALSE の場合
            else
            {
                //［返却値］
                //NULL
                log.exiting(STR_METHOD_NAME);
                return l_datSecondAdddepositDepositOccurredDateExpect;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_datSecondAdddepositDepositOccurredDateExpect;
    }

    /**
     * (get第二水準追証保証金維持率)<BR>
     * (get第二水準追証保証金維持率)<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証保証金維持率」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null かつ<BR>
     * 　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate") == NULL の場合<BR>
     * 　@ 　@［返却値］<BR>
     * 　@　@ 0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params == null かつ<BR>
     * 　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate") != NULL の場合<BR>
     * 　@ 　@［返却値］<BR>
     * 　@　@  this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate")<BR>
     * <BR>
     * 　@　@（3）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第二水準保証金維持率（）<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C977034C
     */
    public double getSecondAdddepositDepositRate()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDepositRate = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        //　@0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null かつ
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate") == NULL の場合
            //［返却値］
            //0
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE) == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params == null かつ
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate") != NULL の場合
            //［返却値］
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate")
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE) != null)
            {
                l_dblSecondAdddepositDepositRate =
                    Double.parseDouble(
                        this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE));
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositRate;
            }
            else if (this.paymentRequisitMngParams != null)
            {
                //（3）this.入金請求管理Params != null の場合
                //［返却値］
                // this.入金請求管理Params.get第二水準保証金維持率（）
                l_dblSecondAdddepositDepositRate = this.paymentRequisitMngParams.getSecondDepositRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositRate;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDepositRate;
    }

    /**
     * (get第二水準追証保証金戻し維持率)<BR>
     * (get第二水準追証保証金戻し維持率)<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証保証金戻し維持率」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null かつ<BR>
     * 　@　@　@this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate") == NULL の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@ 0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params == null かつ<BR>
     * 　@　@　@this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate") != NULL の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate")<BR>
     * <BR>
     * 　@　@（3）this.入金請求管理Params != null の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@ this.入金請求管理Params.get第二水準保証金戻し維持率（）<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C993008D
     */
    public double getSecondAdddepositDepositBackRate()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositBackRate()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDepositBackRate = 0;
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null かつ
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate") == NULL の場合
            //［返却値］
            //0
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE) == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params == null かつ
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate") != NULL の場合
            //［返却値］
            //this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate")
            if (this.paymentRequisitMngParams == null
                && this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE) != null)
            {
                l_dblSecondAdddepositDepositBackRate =
                    Double.parseDouble(
                        this.tpCalcMargin.calcCondition.getInstBranCalcCondition(
                            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE));
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositBackRate;
            }
            else
            {
                //（3）this.入金請求管理Params != null の場合
                //［返却値］
                //this.入金請求管理Params.get第二水準保証金戻し維持率（）
                l_dblSecondAdddepositDepositBackRate =
                    this.paymentRequisitMngParams.getSecondDepositBackRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositBackRate;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDepositBackRate;
    }

    /**
     * (get第二水準追証保証金率（請求2）)<BR>
     * (get第二水準追証保証金率(請求2))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証保証金率(請求2)」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get保証金預託率(前日)（）<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9C9FA0179
     */
    public double getSecondAdddepositMarginDepositRate2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositRate2()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositRate2 = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else
            {
                //（2）this.入金請求管理Params != null の場合
                //［返却値］
                // this.入金請求管理Params.get保証金預託率(前日)（）
                l_dblSecondAdddepositMarginDepositRate2 =
                    this.paymentRequisitMngParams.getMarginDepositRatePrebizdate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositRate2;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositRate2;
    }

    /**
     * (get第二水準追証保証金率（請求1）)<BR>
     * (get第二水準追証保証金率(請求1))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証保証金率(請求1)」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get保証金預託率（）<BR>
     * @@return double
     * @@roseuid 48C9CA1C0189
     */
    public double getSecondAdddepositMarginDepositRate1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositRate1()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositRate1 = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else
            {
                //（2）this.入金請求管理Params != null の場合
                //［返却値］
                // this.入金請求管理Params.get保証金預託率（）
                l_dblSecondAdddepositMarginDepositRate1 =
                    this.paymentRequisitMngParams.getMarginDepositRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositRate1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositRate1;
    }

    /**
     * (get第二水準追証保証金率（見込金額）)<BR>
     * (get第二水準追証保証金率(見込金額))<BR>
     * <BR>
     * 顧客属性と保証金自動振替後判定フラグの判定後、「第二水準追証保証金率(見込金額)」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@保証金自動振替後判定フラグの判定を行う。<BR>
     * <BR>
     * 　@（a）is保証金自動振替後判定フラグ() == TRUE の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc保証金率（）<BR>
     * <BR>
     * 　@（b）is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * @@return double
     * @@roseuid 48C9CA1C0254
     */
    public double getSecondAdddepositMarginDepositRateExpect()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositRateExpect()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositRateExpec = 0;

        // １）　@顧客属性の判定を行う
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            // ２）　@保証金自動振替後判定フラグの判定を行う。
            //（a）is保証金自動振替後判定フラグ() == TRUE の場合
            //［返却値］
            // this.calc保証金率（）
            if (this.isDepositAutoTransferDivFlag())
            {
                l_dblSecondAdddepositMarginDepositRateExpec =
                    this.calcMarginDepositRate();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositRateExpec;
            }
            else
            {
                //（b）is保証金自動振替後判定フラグ() == FALSE の場合
                //［返却値］
                // 0
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositRateExpec;
    }

    /**
     * (get第二水準追証金額（未入金）)<BR>
     * (get第二水準追証金額(未入金))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証金額(未入金)」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第二水準追証未入金（）<BR>
     * @@return double
     * @@roseuid 48C9CA5B0070
     */
    public double getSecondAdddepositDepositNonPay()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDepositNonPay()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDepositNonPay = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            // this.入金請求管理Params.get第二水準追証未入金（）
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositDepositNonPay =
                    this.paymentRequisitMngParams.getSecondDepositNonPay();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDepositNonPay;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDepositNonPay;
    }

    /**
     * (get第二水準追証金額（請求2）)<BR>
     * (get第二水準追証金額(請求2))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証金額(請求(2))」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第二水準追証請求(2)（）<BR>
     * @@roseuid 48C9CA5B0080
     * @@return double
     */
    public double getSecondAdddepositDeposit2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDeposit2()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDeposit2 = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            // this.入金請求管理Params.get第二水準追証請求(2)（）
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositDeposit2 =
                    this.paymentRequisitMngParams.getSecondDeposit2();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDeposit2;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDeposit2;
    }

    /**
     * (get第二水準追証金額（請求1）)<BR>
     * (get第二水準追証金額(請求1))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証金額(請求(1))」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第二水準追証請求(1)（）<BR>
     * @@return double
     * @@roseuid 48C9CA5B0081
     */
    public double getSecondAdddepositDeposit1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositDeposit1()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositDeposit1 = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            // this.入金請求管理Params.get第二水準追証請求(1)（）
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositDeposit1 =
                    this.paymentRequisitMngParams.getSecondDeposit1();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositDeposit1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositDeposit1;
    }

    /**
     * (get第二水準追証決済必要額（未入金）)<BR>
     * (get第二水準追証決済必要額(未入金))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証決済必要額(未入金)」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第二水準追証未入金決済必要額（）<BR>
     * <BR>
     * @@return double
     * @@roseuid 48C9CAC50013
     */
    public double getSecondAdddepositSettlementNonPay()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettlementNonPay()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettlementNonPay = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            // this.入金請求管理Params.get第二水準追証未入金決済必要額（）
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositSettlementNonPay =
                    this.paymentRequisitMngParams.getSecondSettlementNonPay();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositSettlementNonPay;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettlementNonPay;
    }

    /**
     * (get第二水準追証決済必要額（請求2）)<BR>
     * (get第二水準追証決済必要額(請求2))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証決済必要額(請求(2))」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第二水準追証決済必要額(2)（）<BR>
     * @@return double
     * @@roseuid 48C9CAC50022
     */
    public double getSecondAdddepositSettlement2()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettlement2()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettlement2 = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            // this.入金請求管理Params.get第二水準追証決済必要額(2)
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositSettlement2 =
                    this.paymentRequisitMngParams.getSecondSettlement2();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositSettlement2;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettlement2;
    }

    /**
     * (get第二水準追証決済必要額（請求1）)<BR>
     * (get第二水準追証決済必要額(請求1))<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証決済必要額(請求(1))」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@（1）this.入金請求管理Params == null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@0<BR>
     * <BR>
     * 　@　@（2）this.入金請求管理Params != null の場合<BR>
     * 　@　@　@［返却値］<BR>
     * 　@　@　@this.入金請求管理Params.get第二水準追証決済必要額(1)（）<BR>
     * @@return double
     * @@roseuid 48C9CAC50023
     */
    public double getSecondAdddepositSettlement1()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettlement1()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettlement1 = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //（1）this.入金請求管理Params == null の場合
            //［返却値］
            // 0
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（2）this.入金請求管理Params != null の場合
            //［返却値］
            // this.入金請求管理Params.get第二水準追証決済必要額(1)（）
            if (this.paymentRequisitMngParams != null)
            {
                l_dblSecondAdddepositSettlement1 =
                    this.paymentRequisitMngParams.getSecondSettlement1();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositSettlement1;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettlement1;
    }

    /**
     * (get第二水準追証保証金増減)<BR>
     * (get第二水準追証保証金増減)<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証保証金増減」を返却する。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc保証金増減（）<BR>
     * @@return double
     * @@roseuid 48C9CB1C02D1
     */
    public double getSecondAdddepositMarginDepositInDe()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositInDe()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositInDe = 0;

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //［返却値］
        // this.calc保証金増減（）
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblSecondAdddepositMarginDepositInDe = this.calcMarginDepositInDe();
            log.exiting(STR_METHOD_NAME);
            return l_dblSecondAdddepositMarginDepositInDe;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositInDe;
    }

    /**
     * (get第二水準追証保証金増減（見込金額）)<BR>
     * (get第二水準追証保証金増減(見込金額))<BR>
     * <BR>
     * 顧客属性と保証金自動振替後判定フラグの判定後、「第二水準追証保証金増減(見込金額)」を返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@保証金自動振替後判定フラグの判定を行う。<BR>
     * <BR>
     * 　@（a）is保証金自動振替後判定フラグ() == TRUE の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@this.calc保証金増減(見込金額)（）<BR>
     * <BR>
     * 　@（b）is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@［返却値］<BR>
     * 　@　@0<BR>
     * @@return double
     * @@roseuid 48C9CB1C02E1
     */
    public double getSecondAdddepositMarginDepositInDeExpect()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositMarginDepositInDeExpect()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositMarginDepositInDeExpect = 0;

        //１）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        // ２）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@保証金自動振替後判定フラグの判定を行う。
            //（a）is保証金自動振替後判定フラグ() == TRUE の場合
            //［返却値］
            // this.calc保証金増減(見込金額)（）
            if (this.isDepositAutoTransferDivFlag())
            {
                l_dblSecondAdddepositMarginDepositInDeExpect = this.calcMarginDepositInDeExpect();
                log.exiting(STR_METHOD_NAME);
                return l_dblSecondAdddepositMarginDepositInDeExpect;
            }
            //（b）is保証金自動振替後判定フラグ() == FALSE の場合
            //［返却値］
            // 0
            if (!this.isDepositAutoTransferDivFlag())
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositMarginDepositInDeExpect;
    }

    /**
     * (get第二水準追証決済済建玉)<BR>
     * (get第二水準追証決済済建玉)<BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証決済済建玉」を返却する。<BR>
     * <BR>
     * 　@（１）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 <BR>
     * 　@　@［返却値］  <BR>
     * 　@　@0 <BR>
     * <BR>
     * 　@（２）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 <BR>
     * 　@　@［返却値］  <BR>
     * 　@　@this.get当日返済建玉代金（）<BR>
     * @@return double
     * @@roseuid 48C9CB1C02E2
     */
    public double getSecondAdddepositSettledContract()
    {
        final String STR_METHOD_NAME = "getSecondAdddepositSettledContract()";
        log.entering(STR_METHOD_NAME);

        double l_dblSecondAdddepositSettledContract = 0;

        //（１）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（２）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //［返却値］
        // this.get当日返済建玉代金（）
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblSecondAdddepositSettledContract = this.getTodayRepayContractAmount();
            log.exiting(STR_METHOD_NAME);
            return l_dblSecondAdddepositSettledContract;
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblSecondAdddepositSettledContract;
    }

    /**
     * (create第二水準追証未解消情報)<BR>
     * (create第二水準追証未解消情報)<BR>
     * <BR>
     * 顧客属性の判定後、計算した結果を「第二水準追証未解消情報」オブジェクトに設定し、返却する。<BR>
     * <BR>
     * １）　@第二水準追証未解消情報オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@　@第二水準追証未解消情報オブジェクトを、生成した際の初期値のまま返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@当日保証金入金額の計算を行う。<BR>
     * 　@[計算式]<BR>
     * 　@（a）当日保証金入金額(未入金)の計算を行う。<BR>
     * 　@　@　@当日保証金入金額(未入金)　@=　@MIN（追証未入金,　@当日保証金入金額）<BR>
     * <BR>
     * 　@（b）当日保証金入金額(請求2)の計算を行う。<BR>
     * 　@　@　@当日保証金入金額(請求2)　@=　@MIN（追証請求(2),　@当日保証金入金額　@－　@(a)の当日保証金入金額(未入金)）<BR>
     * <BR>
     * 　@（c）当日保証金入金額(請求1)の計算を行う。<BR>
     * 　@　@　@当日保証金入金額(請求1)　@=　@当日保証金入金額　@－　@(b)の当日保証金入金額(請求2)　@－　@(a)の当日保証金入金額(未入金)<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・追証未入金　@　@　@・・・this.入金請求管理Params.get第二水準追証未入金（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証未入金 = 0 とする。<BR>
     * 　@　@・追証請求(2)　@　@　@・・・this.入金請求管理Params.get第二水準追証請求(2)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(2) = 0 とする。<BR>
     * 　@　@・当日保証金入金額　@　@　@・・・this.calc当日保証金入金額（）<BR>
     * <BR>
     * ４）　@(保証金入金後)追証金額の計算を行う。<BR>
     * 　@[計算式]<BR>
     * 　@（a）(保証金入金後)追証未入金の計算を行う。<BR>
     * 　@　@　@(保証金入金後)追証未入金　@=　@MAX（追証未入金　@－　@当日保証金入金額(未入金),　@0）<BR>
     * <BR>
     * 　@（b）(保証金入金後)追証未入金決済必要額の計算を行う。<BR>
     * 　@　@（１）「追証未入金　@=　@0」の場合<BR>
     * 　@　@　@　@(保証金入金後)追証未入金決済必要額　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@(保証金入金後)追証未入金決済必要額　@=　@追証未入金決済必要額　@×　@(a)の(保証金入金後)追証未入金　@／　@追証未入金<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@（c）(保証金入金後)追証請求(2)の計算を行う。<BR>
     * 　@　@　@(保証金入金後)追証請求(2)　@=　@MAX（追証請求(2)　@-　@当日保証金入金額(請求2),　@0）<BR>
     * <BR>
     * 　@（d）(保証金入金後)追証請求(2)決済必要額の計算を行う。<BR>
     * 　@　@（１）「追証請求(2)　@=　@0」の場合<BR>
     * 　@　@　@　@(保証金入金後)追証請求(2)決済必要額　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@(保証金入金後)追証請求(2)決済必要額　@=　@追証請求(2)決済必要額　@×　@(c)の(保証金入金後)追証請求(2)　@／　@追証請求(2)<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@（e）(保証金入金後)追証請求(1)の計算を行う。<BR>
     * 　@　@　@(保証金入金後)追証請求(1)　@=　@MAX（追証請求(1)　@-　@当日保証金入金額(請求1),　@0）<BR>
     * <BR>
     *  　@※　@各値の取得方法@<BR>
     * 　@　@・追証未入金　@　@　@　@　@　@・・・this.入金請求管理Params.get第二水準追証未入金（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証未入金 = 0 とする。<BR>
     * 　@　@・追証請求(2)　@　@　@　@　@　@・・・this.入金請求管理Params.get第二水準追証請求(2)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(2) = 0 とする。<BR>
     * 　@　@・追証請求(1)　@　@　@　@　@　@・・・this.入金請求管理Params.get第二水準追証請求(1)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(1) = 0 とする。<BR>
     * 　@　@・当日保証金入金額(未入金)　@　@　@・・・３-(a)で計算した当日保証金入金額(未入金)<BR>
     * 　@　@・当日保証金入金額(請求2)　@　@　@・・・３-(b)で計算した当日保証金入金額(請求2)<BR>
     * 　@　@・当日保証金入金額(請求1)　@　@　@・・・３-(c)で計算した当日保証金入金額(請求1)<BR>
     * 　@　@・追証未入金決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証未入金決済必要額（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証未入金決済必要額 = 0 とする。<BR>
     * 　@　@・追証請求(2)決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証決済必要額(2)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(2)決済必要額 = 0 とする。<BR>
     * 　@　@・追証請求(1)決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証決済必要額(1)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(1)決済必要額 = 0 とする。<BR>
     * <BR>
     * ５）　@当日返済建玉代金の計算を行う。<BR>
     * 　@[計算式]<BR>
     * 　@（a）当日返済建玉代金(未入金)の計算を行う。<BR>
     * 　@　@　@当日返済建玉代金(未入金)　@=　@MIN（(保証金入金後)追証未入金決済必要額,　@当日返済建玉代金）<BR>
     * <BR>
     * 　@（b）当日返済建玉代金(請求2)の計算を行う。<BR>
     * 　@　@　@当日返済建玉代金(請求2)　@=　@MIN（(保証金入金後)追証請求(2)決済必要額,　@当日返済建玉代金　@－　@(a)の当日返済建玉代金(未入金)）<BR>
     * <BR>
     * 　@（c）当日返済建玉代金(請求1)の計算を行う。<BR>
     * 　@　@　@当日返済建玉代金(請求1)　@=　@当日返済建玉代金　@－　@(b)の当日返済建玉代金(請求2)　@－　@(a)の当日返済建玉代金(未入金)<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・(保証金入金後)追証未入金決済必要額　@　@　@・・・４-(b)で計算した(保証金入金後)追証未入金決済必要額<BR>
     * 　@　@・(保証金入金後)追証請求(2)決済必要額　@　@　@・・・４-(d)で計算した(保証金入金後)追証請求(2)決済必要額<BR>
     * 　@　@・当日返済建玉代金　@　@　@・・・this.get当日返済建玉代金（）<BR>
     * <BR>
     * ６）　@計算条件の判定を行う。<BR>
     * 　@（a）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”today.clearance.determination.div”）　@==　@”1”の場合<BR>
     * 　@　@　@７）以降の処理を行う。 <BR>
     * <BR>
     * 　@（b）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”today.clearance.determination.div”）　@==　@NULL<BR>
     * 　@　@　@又は、this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”today.clearance.determination.div”）　@!=　@”1”の場合<BR>
     * 　@　@　@以下の値を設定し、８）以降の処理を行う。<BR>
     * 　@　@　@　@・当日消し込み換算額(未入金)　@=　@0<BR>
     * 　@　@　@　@・当日消し込み換算額(請求2)　@=　@0<BR>
     * 　@　@　@　@・当日消し込み換算額(請求1)　@=　@0<BR>
     * <BR>
     * ７）　@当日消し込み換算額の計算を行う。<BR>
     * 　@[計算式]<BR>
     * 　@（a）当日消し込み換算額(未入金)の計算を行う。<BR>
     * 　@　@（１）「追証未入金決済必要額　@=　@0」の場合<BR>
     * 　@　@　@　@当日消し込み換算額(未入金)　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@当日消し込み換算額(未入金)　@=　@追証未入金　@×　@当日返済建玉代金(未入金)　@／　@追証未入金決済必要額<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り捨てとする。）<BR>
     * <BR>
     * 　@（b）当日消し込み換算額(請求2)の計算を行う。<BR>
     * 　@　@（１）「追証請求(2)決済必要額　@=　@0」の場合<BR>
     * 　@　@　@　@当日消し込み換算額(請求2)　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@当日消し込み換算額(請求2)　@=　@追証請求(2)　@×　@当日返済建玉代金(請求2)　@／　@追証請求(2)決済必要額<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り捨てとする。）<BR>
     * <BR>
     * 　@（c）当日消し込み換算額(請求1)の計算を行う。<BR>
     * 　@　@（１）「追証請求(1)決済必要額　@=　@0」の場合<BR>
     * 　@　@　@　@当日消し込み換算額(請求1)　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@当日消し込み換算額(請求1)　@=　@追証請求(1)　@×　@当日返済建玉代金(請求1)　@／　@追証請求(1)決済必要額<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り捨てとする。）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・追証未入金決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証未入金決済必要額（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証未入金決済必要額 = 0 とする。<BR>
     * 　@　@・追証請求(2)決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証決済必要額(2)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(2)決済必要額 = 0 とする。<BR>
     * 　@　@・追証請求(1)決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証決済必要額(1)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(1)決済必要額 = 0 とする。<BR>
     * 　@　@・追証未入金　@　@　@・・・this.入金請求管理Params.get第二水準追証未入金（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証未入金 = 0 とする。<BR>
     * 　@　@・追証請求(2)　@　@　@・・・this.入金請求管理Params.get第二水準追証請求(2)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(2) = 0 とする。<BR>
     * 　@　@・追証請求(1)　@　@　@・・・this.入金請求管理Params.get第二水準追証請求(1)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(1) = 0 とする。<BR>
     * 　@　@・当日返済建玉代金(未入金)　@　@　@・・・５-(a)で計算した当日返済建玉代金(未入金)<BR>
     * 　@　@・当日返済建玉代金(請求2)　@　@　@・・・５-(b)で計算した当日返済建玉代金(請求2)<BR>
     * 　@　@・当日返済建玉代金(請求1)　@　@　@・・・５-(c)で計算した当日返済建玉代金(請求1)<BR>
     * <BR>
     * ８）　@(未解消)追証金額と(未解消)決済必要額の計算を行う。<BR>
     * 　@[計算式]<BR>
     * 　@（a）追証金額(未入金)の計算を行う。<BR>
     * 　@　@　@追証金額(未入金)　@=　@MAX（(保証金入金後)追証未入金　@－　@当日消し込み換算額(未入金),　@0）<BR>
     * <BR>
     * 　@（b）決済必要額(未入金)の計算を行う。<BR>
     * 　@　@（１）「追証未入金　@=　@0」の場合<BR>
     * 　@　@　@　@決済必要額(未入金)　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@決済必要額(未入金)　@=　@追証未入金決済必要額　@×　@(a)の追証金額(未入金)　@／　@追証未入金<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@（c）追証金額(請求2)の計算を行う。<BR>
     * 　@　@　@追証金額(請求2)　@=　@MAX（(保証金入金後)追証請求(2)　@－　@当日消し込み換算額(請求2),　@0）<BR>
     * <BR>
     * 　@（d）決済必要額(請求2)の計算を行う。<BR>
     * 　@　@（１）「追証請求(2)　@=　@0」の場合<BR>
     * 　@　@　@　@決済必要額(請求2)　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@決済必要額(請求2)　@=　@追証請求(2)決済必要額　@×　@(c)の追証金額(請求2)　@／　@追証請求(2)<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@（e）追証金額(請求1)の計算を行う。<BR>
     * 　@　@　@追証金額(請求1)　@=　@MAX（(保証金入金後)追証請求(1)　@－　@当日消し込み換算額(請求1),　@0）<BR>
     * <BR>
     * 　@（f）決済必要額(請求1)の計算を行う。<BR>
     * 　@　@（１）「追証請求(1)　@=　@0」の場合<BR>
     * 　@　@　@　@決済必要額(請求1)　@=　@0<BR>
     * 　@　@（２）(1)以外の場合<BR>
     * 　@　@　@　@決済必要額(請求1)　@=　@追証請求(1)決済必要額　@×　@(e)の追証金額(請求1)　@／　@追証請求(1)<BR>
     * 　@　@　@　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・(保証金入金後)追証未入金　@　@　@・・・４-(a)で計算した(保証金入金後)追証未入金<BR>
     * 　@　@・(保証金入金後)追証請求(2)　@　@　@・・・４-(c)で計算した(保証金入金後)追証請求(2)<BR>
     * 　@　@・(保証金入金後)追証請求(1)　@　@　@・・・４-(e)で計算した(保証金入金後)追証請求(1)<BR>
     * 　@　@・当日消し込み換算額(未入金)　@　@　@・・・６-(b)又は７-(a)で計算した当日消し込み換算額(未入金)<BR>
     * 　@　@・当日消し込み換算額(請求2)　@　@　@・・・６-(b)又は７-(b)で計算した当日消し込み換算額(請求2)<BR>
     * 　@　@・当日消し込み換算額(請求1)　@　@　@・・・６-(b)又は７-(c)で計算した当日消し込み換算額(請求1)<BR>
     * 　@　@・追証未入金決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証未入金決済必要額（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証未入金決済必要額 = 0 とする。<BR>
     * 　@　@・追証請求(2)決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証決済必要額(2)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(2)決済必要額 = 0 とする。<BR>
     * 　@　@・追証請求(1)決済必要額　@　@　@・・・this.入金請求管理Params.get第二水準追証決済必要額(1)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(1)決済必要額 = 0 とする。<BR>
     * 　@　@・追証未入金　@　@　@・・・this.入金請求管理Params.get第二水準追証未入金（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証未入金 = 0 とする。<BR>
     * 　@　@・追証請求(2)　@　@　@・・・this.入金請求管理Params.get第二水準追証請求(2)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(2) = 0 とする。<BR>
     * 　@　@・追証請求(1)　@　@　@・・・this.入金請求管理Params.get第二水準追証請求(1)（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、追証請求(1) = 0 とする。<BR>
     * <BR>
     * ９）　@保証金自動振替の判定を行う。<BR>
     * 　@（a）this.保証金自動振替後判定フラグ　@==　@FALSE(保証金自動振替前)の場合<BR>
     *  　@　@　@８で計算した項目を第二水準追証未解消情報オブジェクトに設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.保証金自動振替後判定フラグ　@==　@TRUE(保証金自動振替後)の場合<BR>
     * 　@　@　@１０）以降の処理を行う。<BR>
     * <BR>
     * １０）　@追証金額(見込金額)と決済必要額(見込金額)の計算を行う。<BR>
     * 　@[計算式]<BR>
     * 　@（a）追証金額(見込金額)の計算を行う。<BR>
     * 　@　@　@追証金額(見込金額)　@=　@MAX（追証金額　@－　@追証金額(請求1)　@－　@追証金額(請求2)　@－　@追証金額(未入金),　@0）<BR>
     * <BR>
     * 　@（b）決済必要額(見込金額)の計算を行う。<BR>
     * 　@　@　@決済必要額(見込金額)　@=　@MAX（決済必要額　@－　@決済必要額(請求1)　@－　@決済必要額(請求2)　@－　@決済必要額(未入金),　@0）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・追証金額　@　@　@・・・this.calc第二水準追証金額（）<BR>
     * 　@　@・追証金額(未入金)　@　@　@・・・８-(a)で計算した追証金額(未入金)<BR>
     * 　@　@・追証金額(請求2)　@　@　@・・・８-(c)で計算した追証金額(請求2)<BR>
     * 　@　@・決済必要額　@　@　@・・・this.calc第二水準追証決済必要額（）<BR>
     * 　@　@・決済必要額(未入金)　@　@　@・・・８-(b)で計算した決済必要額(未入金)<BR>
     * 　@　@・決済必要額(請求2)　@　@　@・・・８-(d)で計算した決済必要額(請求2)<BR>
     * <BR>
     * １１）　@８と１０で計算した項目を第二水準追証未解消情報オブジェクトに設定し、返却する。<BR>
     * @@return WEB3TPSecondAdddepositNotClearInfo
     * @@roseuid 48CA25000001
     */
    public WEB3TPSecondAdddepositNotClearInfo createSecondAdddepositNotClearInfo()
    {
        final String STR_METHOD_NAME = "createSecondAdddepositNotClearInfo()";
        log.entering(STR_METHOD_NAME);

        //当日保証金入金額(未入金)
        double l_dblTodayDepositPaymentAmountNonPay = 0;
        //当日保証金入金額(請求2)
        double l_dblTodayDepositPaymentAmount2 = 0;
        //当日保証金入金額(請求1)
        double l_dblTodayDepositPaymentAmount1 = 0;
        //(保証金入金後)追証未入金
        double l_dblSecondDepositNonPayAfter = 0;
        //(保証金入金後)追証未入金決済必要額
        double l_dblSecondSettlementNonPayAfter = 0;
        //(保証金入金後)追証請求(2)
        double l_dblSecondDeposit2After = 0;
        //(保証金入金後)追証請求(2)決済必要額
        double l_dblSecondSettlement2After = 0;
        //(保証金入金後)追証請求(1)
        double l_dblSecondDeposit1After = 0;
        //当日返済建玉代金(未入金)
        double l_dblTodayRepayContractAmountNonPay = 0;
        //当日返済建玉代金(請求2)
        double l_dblTodayRepayContractAmount2 = 0;
        //当日返済建玉代金(請求1)
        double l_dblTodayRepayContractAmount1 = 0;
        //当日消し込み換算額(未入金)
        double l_dblTodayEliminateAmountNonPay = 0;
        //当日消し込み換算額(請求2)
        double l_dblTodayEliminateAmount2 = 0;
        //当日消し込み換算額(請求1)
        double l_dblTodayEliminateAmount1 = 0;
        //追証金額(未入金)
        double l_dblDepositNonPay = 0;
        //決済必要額(未入金)
        double l_dblSettlementNonPay = 0;
        //追証金額(請求2)
        double l_dblDeposit2 = 0;
        //決済必要額(請求2)
        double l_dblSettlement2 = 0;
        //追証金額(請求1)
        double l_dblDeposit1 = 0;
        //決済必要額(請求1)
        double l_dblSettlement1 = 0;
        //追証金額(見込金額)
        double l_dblDepositExpect = 0;
        //決済必要額(見込金額)
        double l_dblSettlementExpect = 0;

        //追証未入金を取得する。
        double l_dblSecondDepositNonPay = 0;
        //追証請求(2)を取得する。
        double l_dblSecondDeposit2 = 0;
        //追証請求(1)を取得する。
        double l_dblSecondDeposit1 = 0;
        //追証未入金決済必要額を取得する。
        double l_dblSecondSettlementNonPay = 0;
        //追証請求(2)決済必要額を取得する。
        double l_dblSecondSettlement2 = 0;
        //追証請求(1)決済必要額を取得する。
        double l_dblSecondSettlement1 = 0;
        if (this.paymentRequisitMngParams == null)
        {
            l_dblSecondDepositNonPay = 0;
            l_dblSecondDeposit2 = 0;
            l_dblSecondDeposit1 = 0;
            l_dblSecondSettlementNonPay = 0;
            l_dblSecondSettlement2 = 0;
            l_dblSecondSettlement1 = 0;
        }
        else
        {
            l_dblSecondDepositNonPay = this.paymentRequisitMngParams.getSecondDepositNonPay();
            l_dblSecondDeposit2 = this.paymentRequisitMngParams.getSecondDeposit2();
            l_dblSecondDeposit1 = this.paymentRequisitMngParams.getSecondDeposit1();
            l_dblSecondSettlementNonPay = this.paymentRequisitMngParams.getSecondSettlementNonPay();
            l_dblSecondSettlement2 = this.paymentRequisitMngParams.getSecondSettlement2();
            l_dblSecondSettlement1 = this.paymentRequisitMngParams.getSecondSettlement1();
        }

        //当日保証金入金額を取得する。
        double l_dblTodayDepositPaymentAmount = this.calcTodayDepositPaymentAmount();
        //当日返済建玉代金を取得する。
        double l_dblTodayRepayContractAmount = this.getTodayRepayContractAmount();
        //追証金額を取得する。
        double l_dblSecondAdddepositAmount = this.calcSecondAdddepositAmount();
        //決済必要額を取得する。
        double l_dblSecondAdddepositSettlement = this.calcSecondAdddepositSettlement();

        //１）　@第二水準追証未解消情報オブジェクトを生成する。
        WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo = new WEB3TPSecondAdddepositNotClearInfo();

        //２）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            //第二水準追証未解消情報オブジェクトを、生成した際の初期値のまま返却する。
            return l_secondAdddepositNotClearInfo;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //３）以降の処理を行う。
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //３）　@当日保証金入金額の計算を行う。
            //[計算式]
            //（a）当日保証金入金額(未入金)の計算を行う。
            //当日保証金入金額(未入金)　@=　@MIN（追証未入金,　@当日保証金入金額）
            l_dblTodayDepositPaymentAmountNonPay = Math.min(l_dblSecondDepositNonPay, l_dblTodayDepositPaymentAmount);

            //（b）当日保証金入金額(請求2)の計算を行う。
            //当日保証金入金額(請求2)　@=　@MIN（追証請求(2),　@当日保証金入金額　@－　@(a)の当日保証金入金額(未入金)）
            double l_dblAmount0 =
                new BigDecimal(l_dblTodayDepositPaymentAmount + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmountNonPay + "")).doubleValue();
            l_dblTodayDepositPaymentAmount2 = Math.min(l_dblSecondDeposit2, l_dblAmount0);

            //（c）当日保証金入金額(請求1)の計算を行う。
            //当日保証金入金額(請求1)　@=　@当日保証金入金額　@－　@(b)の当日保証金入金額(請求2)　@－　@(a)の当日保証金入金額(未入金)
            l_dblTodayDepositPaymentAmount1 =
                new BigDecimal(l_dblTodayDepositPaymentAmount + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmount2 + "")).subtract(
                        new BigDecimal(l_dblTodayDepositPaymentAmountNonPay + "")).doubleValue();

            //４）　@(保証金入金後)追証金額の計算を行う。
            //[計算式]
            //（a）(保証金入金後)追証未入金の計算を行う。
            //(保証金入金後)追証未入金　@=　@MAX（追証未入金　@－　@当日保証金入金額(未入金),　@0）
            double l_dblAmount1 =
                new BigDecimal(l_dblSecondDepositNonPay + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmountNonPay + "")).doubleValue();
            l_dblSecondDepositNonPayAfter = Math.max(l_dblAmount1, 0);

            //（b）(保証金入金後)追証未入金決済必要額の計算を行う。
            //（１）「追証未入金　@=　@0」の場合
            if (GtlUtils.Double.isZero(l_dblSecondDepositNonPay))
            {
                //(保証金入金後)追証未入金決済必要額　@=　@0
                l_dblSecondSettlementNonPayAfter = 0;
            }
            //（２）(1)以外の場合
            else
            {
                //(保証金入金後)追証未入金決済必要額　@=　@追証未入金決済必要額　@×　@(a)の(保証金入金後)追証未入金　@／　@追証未入金
                //（計算結果の小数点以下を切り上げとする。）
                l_dblSecondSettlementNonPayAfter =
                    new BigDecimal(l_dblSecondSettlementNonPay + "").multiply(
                        new BigDecimal(l_dblSecondDepositNonPayAfter + "")).divide(
                            new BigDecimal(l_dblSecondDepositNonPay + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //（c）(保証金入金後)追証請求(2)の計算を行う。
            //(保証金入金後)追証請求(2)　@=　@MAX（追証請求(2)　@-　@当日保証金入金額(請求2),　@0）
            double l_dblAmount2 =
                new BigDecimal(l_dblSecondDeposit2 + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmount2 + "")).doubleValue();
            l_dblSecondDeposit2After = Math.max(l_dblAmount2, 0);

            //（d）(保証金入金後)追証請求(2)決済必要額の計算を行う。
            //（１）「追証請求(2)　@=　@0」の場合
            if (GtlUtils.Double.isZero(l_dblSecondDeposit2))
            {
                //(保証金入金後)追証請求(2)決済必要額　@=　@0
                l_dblSecondSettlement2After = 0;
            }
            //（２）(1)以外の場合
            else
            {
                //(保証金入金後)追証請求(2)決済必要額　@=　@追証請求(2)決済必要額　@×　@(c)の(保証金入金後)追証請求(2)　@／　@追証請求(2)
                //（計算結果の小数点以下を切り上げとする。）
                l_dblSecondSettlement2After =
                    new BigDecimal(l_dblSecondSettlement2 + "").multiply(
                        new BigDecimal(l_dblSecondDeposit2After + "")).divide(
                            new BigDecimal(l_dblSecondDeposit2 + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //（e）(保証金入金後)追証請求(1)の計算を行う。
            //(保証金入金後)追証請求(1)　@=　@MAX（追証請求(1)　@-　@当日保証金入金額(請求1),　@0）
            double l_dblAmount3 =
                new BigDecimal(l_dblSecondDeposit1 + "").subtract(
                    new BigDecimal(l_dblTodayDepositPaymentAmount1 + "")).doubleValue();
            l_dblSecondDeposit1After = Math.max(l_dblAmount3, 0);

            //５）　@当日返済建玉代金の計算を行う。
            //[計算式]
            //（a）当日返済建玉代金(未入金)の計算を行う。
            //当日返済建玉代金(未入金)　@=　@MIN（(保証金入金後)追証未入金決済必要額,　@当日返済建玉代金）
            l_dblTodayRepayContractAmountNonPay =
                Math.min(l_dblSecondSettlementNonPayAfter,
                    l_dblTodayRepayContractAmount);

            //（b）当日返済建玉代金(請求2)の計算を行う。
            //当日返済建玉代金(請求2)　@=　@MIN（(保証金入金後)追証請求(2)決済必要額,　@当日返済建玉代金　@－　@(a)の当日返済建玉代金(未入金)）
            double l_dblAmount4 =
                new BigDecimal(l_dblTodayRepayContractAmount + "").subtract(
                    new BigDecimal(l_dblTodayRepayContractAmountNonPay + "")).doubleValue();
            l_dblTodayRepayContractAmount2 = Math.min(l_dblSecondSettlement2After, l_dblAmount4);

            //（c）当日返済建玉代金(請求1)の計算を行う。
            //当日返済建玉代金(請求1)　@=　@当日返済建玉代金　@－　@(b)の当日返済建玉代金(請求2)　@－　@(a)の当日返済建玉代金(未入金)
            l_dblTodayRepayContractAmount1 =
                new BigDecimal(l_dblTodayRepayContractAmount + "").subtract(
                    new BigDecimal(l_dblTodayRepayContractAmount2 + "")).subtract(
                        new BigDecimal(l_dblTodayRepayContractAmountNonPay + "")).doubleValue();

            //６）　@計算条件の判定を行う。
            //a）this.資産余力情報<信用顧客>.get余力計算条件.
            //get会社部店別余力計算条件（”today.clearance.determination.div”）　@==　@”1”の場合
            //７）以降の処理を行う。
            String l_strInstBranCalcCondition =
                this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.TODAY_CLEARANCE_DETERMINATION_DIV);

            if (WEB3TodayClearanceDeterminationDivDef.EXECUTE.equals(l_strInstBranCalcCondition))
            {
                //７）　@当日消し込み換算額の計算を行う。
                //[計算式]
                //（a）当日消し込み換算額(未入金)の計算を行う。
                //（１）「追証未入金決済必要額　@=　@0」の場合
                if (GtlUtils.Double.isZero(l_dblSecondSettlementNonPay))
                {
                    //当日消し込み換算額(未入金)　@=　@0
                    l_dblTodayEliminateAmountNonPay = 0;
                }
                //（２）(1)以外の場合
                else
                {
                    //当日消し込み換算額(未入金)　@=　@追証未入金　@×　@当日返済建玉代金(未入金)　@／　@追証未入金決済必要額
                    //（計算結果の小数点以下を切り捨てとする。）
                    l_dblTodayEliminateAmountNonPay =
                        new BigDecimal(l_dblSecondDepositNonPay + "").multiply(
                            new BigDecimal(l_dblTodayRepayContractAmountNonPay + "")).divide(
                                new BigDecimal(l_dblSecondSettlementNonPay + ""),
                                0, BigDecimal.ROUND_DOWN).doubleValue();
                }

                //（b）当日消し込み換算額(請求2)の計算を行う。
                //（１）「追証請求(2)決済必要額　@=　@0」の場合
                if (GtlUtils.Double.isZero(l_dblSecondSettlement2))
                {
                    //当日消し込み換算額(請求2)　@=　@0
                    l_dblTodayEliminateAmount2 = 0;
                }
                //（２）(1)以外の場合
                else
                {
                    //当日消し込み換算額(請求2)　@=　@追証請求(2)　@×　@当日返済建玉代金(請求2)　@／　@追証請求(2)決済必要額
                    //（計算結果の小数点以下を切り捨てとする。）
                    l_dblTodayEliminateAmount2 =
                        new BigDecimal(l_dblSecondDeposit2 + "").multiply(
                            new BigDecimal(l_dblTodayRepayContractAmount2 + "")).divide(
                                new BigDecimal(l_dblSecondSettlement2 + ""), 0, BigDecimal.ROUND_DOWN).doubleValue();
                }

                //（c）当日消し込み換算額(請求1)の計算を行う。
                //（１）「追証請求(1)決済必要額　@=　@0」の場合
                if (GtlUtils.Double.isZero(l_dblSecondSettlement1))
                {
                    //当日消し込み換算額(請求1)　@=　@0
                    l_dblTodayEliminateAmount1 = 0;
                }
                //（２）(1)以外の場合
                else
                {
                    //当日消し込み換算額(請求1)　@=　@追証請求(1)　@×　@当日返済建玉代金(請求1)　@／　@追証請求(1)決済必要額
                    //（計算結果の小数点以下を切り捨てとする。）
                    l_dblTodayEliminateAmount1 =
                        new BigDecimal(l_dblSecondDeposit1 + "").multiply(
                            new BigDecimal(l_dblTodayRepayContractAmount1 + "")).divide(
                                new BigDecimal(l_dblSecondSettlement1 + ""), 0, BigDecimal.ROUND_DOWN).doubleValue();
                }
            }
            //（b）this.資産余力情報<信用顧客>.get余力計算条件.
            //get会社部店別余力計算条件（”today.clearance.determination.div”）　@==　@NULL
            //又は、this.資産余力情報<信用顧客>.get余力計算条件.
            //get会社部店別余力計算条件（”today.clearance.determination.div”）　@!=　@”1”の場合
            //以下の値を設定し、８）以降の処理を行う。
            else
            {
                //・当日消し込み換算額(未入金)　@=　@0
                l_dblTodayEliminateAmountNonPay = 0;
                //・当日消し込み換算額(請求2)　@=　@0
                l_dblTodayEliminateAmount2 = 0;
                //・当日消し込み換算額(請求1)　@=　@0
                l_dblTodayEliminateAmount1 = 0;
            }

            //８）　@(未解消)追証金額と(未解消)決済必要額の計算を行う。
            //[計算式]
            //（a）追証金額(未入金)の計算を行う。
            //追証金額(未入金)　@=　@MAX（(保証金入金後)追証未入金　@－　@当日消し込み換算額(未入金),　@0）
            double l_dblAmount5 =
                new BigDecimal(l_dblSecondDepositNonPayAfter + "").subtract(
                    new  BigDecimal(l_dblTodayEliminateAmountNonPay + "")).doubleValue();
            l_dblDepositNonPay = Math.max(l_dblAmount5, 0);

            //（b）決済必要額(未入金)の計算を行う。
            //（１）「追証未入金　@=　@0」の場合
            if (GtlUtils.Double.isZero(l_dblSecondDepositNonPay))
            {
                //決済必要額(未入金)　@=　@0
                l_dblSettlementNonPay = 0;
            }
            //（２）(1)以外の場合
            else
            {
                //決済必要額(未入金)　@=　@追証未入金決済必要額　@×　@(a)の追証金額(未入金)　@／　@追証未入金
                //（計算結果の小数点以下を切り上げとする。）
                l_dblSettlementNonPay =
                    new BigDecimal(l_dblSecondSettlementNonPay + "").multiply(
                        new BigDecimal(l_dblDepositNonPay + "")).divide(
                            new BigDecimal(l_dblSecondDepositNonPay + ""),
                            0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //（c）追証金額(請求2)の計算を行う。
            //追証金額(請求2)　@=　@MAX（(保証金入金後)追証請求(2)　@－　@当日消し込み換算額(請求2),　@0）
            double l_dblAmount6 =
                new BigDecimal(l_dblSecondDeposit2After + "").subtract(
                    new  BigDecimal(l_dblTodayEliminateAmount2 + "")).doubleValue();
            l_dblDeposit2 = Math.max(l_dblAmount6, 0);

            //（d）決済必要額(請求2)の計算を行う。
            //（１）「追証請求(2)　@=　@0」の場合
            if (GtlUtils.Double.isZero(l_dblSecondDeposit2))
            {
                //決済必要額(請求2)　@=　@0
                l_dblSettlement2 = 0;
            }
            //（２）(1)以外の場合
            else
            {
                //決済必要額(請求2)　@=　@追証請求(2)決済必要額　@×　@(c)の追証金額(請求2)　@／　@追証請求(2)
                //（計算結果の小数点以下を切り上げとする。）
                l_dblSettlement2 =
                    new BigDecimal(l_dblSecondSettlement2 + "").multiply(
                        new BigDecimal(l_dblDeposit2 + "")).divide(
                            new BigDecimal(l_dblSecondDeposit2 + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //（e）追証金額(請求1)の計算を行う。
            //追証金額(請求1)　@=　@MAX（(保証金入金後)追証請求(1)　@－　@当日消し込み換算額(請求1),　@0）
            double l_dblAmount7 =
                new BigDecimal(l_dblSecondDeposit1After + "").subtract(
                    new  BigDecimal(l_dblTodayEliminateAmount1 + "")).doubleValue();
            l_dblDeposit1 = Math.max(l_dblAmount7, 0);

            //（f）決済必要額(請求1)の計算を行う。
            //（１）「追証請求(1)　@=　@0」の場合
            if (GtlUtils.Double.isZero(l_dblSecondDeposit1))
            {
                //決済必要額(請求1)　@=　@0
                l_dblSettlement1 = 0;
            }
            //（２）(1)以外の場合
            else
            {
                //決済必要額(請求1)　@=　@追証請求(1)決済必要額　@×　@(e)の追証金額(請求1)　@／　@追証請求(1)
                //（計算結果の小数点以下を切り上げとする。）
                l_dblSettlement1 =
                    new BigDecimal(l_dblSecondSettlement1 + "").multiply(
                        new BigDecimal(l_dblDeposit1 + "")).divide(
                            new BigDecimal(l_dblSecondDeposit1 + ""), 0, BigDecimal.ROUND_CEILING).doubleValue();
            }

            //９）　@保証金自動振替の判定を行う。
            //（a）this.保証金自動振替後判定フラグ　@==　@FALSE(保証金自動振替前)の場合
            if (!this.depositAutoTransferDivFlag)
            {
                //８で計算した項目を第二水準追証未解消情報オブジェクトに設定し、返却する。
                //追証金額(未入金)設定
                l_secondAdddepositNotClearInfo.secondDepositNonPay = l_dblDepositNonPay;
                //決済必要額(未入金)設定
                l_secondAdddepositNotClearInfo.secondSettlementNonPay = l_dblSettlementNonPay;
                //追証金額(請求2)設定
                l_secondAdddepositNotClearInfo.secondDeposit2 = l_dblDeposit2;
                //決済必要額(請求2)設定
                l_secondAdddepositNotClearInfo.secondSettlement2 = l_dblSettlement2;
                //追証金額(請求1)設定
                l_secondAdddepositNotClearInfo.secondDeposit1 = l_dblDeposit1;
                //決済必要額(請求1)設定
                l_secondAdddepositNotClearInfo.secondSettlement1 = l_dblSettlement1;
            }
            //（b）this.保証金自動振替後判定フラグ　@==　@TRUE(保証金自動振替後)の場合
            //１０）以降の処理を行う。
            else
            {
                //１０）　@追証金額(見込金額)と決済必要額(見込金額)の計算を行う。
                //[計算式]
                //（a）追証金額(見込金額)の計算を行う。
                //追証金額(見込金額)　@=　@MAX（追証金額　@－　@追証金額(請求1)　@－　@追証金額(請求2)　@－　@追証金額(未入金),　@0）
                double l_dblAmount8 =
                    new BigDecimal(l_dblSecondAdddepositAmount + "").subtract(
                        new BigDecimal(l_dblDeposit1 + "")).subtract(
                            new BigDecimal(l_dblDeposit2 + "")).subtract(
                                new BigDecimal(l_dblDepositNonPay + "")).doubleValue();
                l_dblDepositExpect = Math.max(l_dblAmount8, 0);

                //（b）決済必要額(見込金額)の計算を行う。
                //決済必要額(見込金額)　@=　@MAX（決済必要額　@－　@決済必要額(請求1)　@－　@決済必要額(請求2)　@－　@決済必要額(未入金),　@0）
                double l_dblAmount9 =
                    new BigDecimal(l_dblSecondAdddepositSettlement + "").subtract(
                        new BigDecimal(l_dblSettlement1 + "")).subtract(
                            new BigDecimal(l_dblSettlement2 + "")).subtract(
                                new BigDecimal(l_dblSettlementNonPay + "")).doubleValue();
                l_dblSettlementExpect = Math.max(l_dblAmount9, 0);

                //１１）　@８と１０で計算した項目を第二水準追証未解消情報オブジェクトに設定し、返却する。
                //追証金額(未入金)設定
                l_secondAdddepositNotClearInfo.secondDepositNonPay = l_dblDepositNonPay;
                //決済必要額(未入金)設定
                l_secondAdddepositNotClearInfo.secondSettlementNonPay = l_dblSettlementNonPay;
                //追証金額(請求2)設定
                l_secondAdddepositNotClearInfo.secondDeposit2 = l_dblDeposit2;
                //決済必要額(請求2)設定
                l_secondAdddepositNotClearInfo.secondSettlement2 = l_dblSettlement2;
                //追証金額(請求1)設定
                l_secondAdddepositNotClearInfo.secondDeposit1 = l_dblDeposit1;
                //決済必要額(請求1)設定
                l_secondAdddepositNotClearInfo.secondSettlement1 = l_dblSettlement1;
                //追証金額(見込金額)設定
                l_secondAdddepositNotClearInfo.secondDepositExpect = l_dblDepositExpect;
                //決済必要額(見込金額)設定
                l_secondAdddepositNotClearInfo.secondSettlementExpect = l_dblSettlementExpect;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_secondAdddepositNotClearInfo;
    }

    /**
     * (calc預り金不足額（現物顧客）)<BR>
     * (calc預り金不足額(現物顧客))<BR>
     * <BR>
     * 引数の指定日(=n)に対応した「預り金不足額」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の判定を行い、預り金不足額(T+n)を計算する。<BR>
     * 　@(a)指定日(=n)　@==　@0　@の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@預り金不足額(T+0)　@=　@ABS（MIN（引出可能現金(T+0),　@0））<BR>
     * <BR>
     * 　@(b)(a)以外の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@預り金不足額(T+n)　@=　@MAX（ABS（MIN（引出可能現金(T+n),　@0））　@－　@ABS（MIN（引出可能現金(T+n-1),　@0））,　@0）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@　@・引出可能現金(T+n)　@　@　@・・・this.資産余力情報<現物顧客>.calc引出可能現金(T+n)<BR>
     * <BR>
     * ３）　@計算した預り金不足額(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48DB7E89039E
     */
    protected double calcAccountBalanceShortfallEquity(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceShortfallEquity(int)";
        log.entering(STR_METHOD_NAME);

        double l_dblAccountBalanceShortfallEquity = 0;
        //１）　@引数チェックを行う。
        //nが0以上5以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //２）　@指定日の判定を行い、預り金不足額(T+n)を計算する。
        //(a)指定日(=n)　@==　@0　@の場合
        //預り金不足額(T+0)　@=　@ABS（MIN（引出可能現金(T+0),　@0））
        if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblAccountBalanceShortfallEquity =
                Math.abs(Math.min(this.tpCalcEquity.calcActualPaymentBalance(l_intSpecifiedPoint), 0));
        }
        //(b)(a)以外の場合
        //預り金不足額(T+n)　@=　@MAX（ABS（MIN（引出可能現金(T+n),　@0））
        //　@－　@ABS（MIN（引出可能現金(T+n-1),　@0））,　@0）
        else
        {
            double l_dblActualPaymentBalance =
                this.tpCalcEquity.calcActualPaymentBalance(l_intSpecifiedPoint);
            double l_dblActualPaymentBalance1 =
                this.tpCalcEquity.calcActualPaymentBalance(l_intSpecifiedPoint - 1);
            l_dblAccountBalanceShortfallEquity =
                new BigDecimal(Math.abs(Math.min(l_dblActualPaymentBalance, 0))).subtract(
                    new BigDecimal(Math.abs(Math.min(l_dblActualPaymentBalance1, 0)))).doubleValue();
            l_dblAccountBalanceShortfallEquity = Math.max(l_dblAccountBalanceShortfallEquity, 0);
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblAccountBalanceShortfallEquity;
    }

    /**
     * (calc預り金不足額（信用顧客）)<BR>
     * (calc預り金不足額(信用顧客))<BR>
     * <BR>
     * 引数の指定日(=n)に対応した「預り金不足額」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上1以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@指定日の判定を行い、預り金不足額(T+n)を計算する。<BR>
     * 　@(a)指定日(=n)　@==　@0　@の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@預り金不足額(T+0)　@=　@ABS（MIN（顧客勘定(T+0)　@－　@MAX（日計り拘束金(T+0)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@－　@振替額(預⇒保)(T+0),　@特別立替金実績,　@0）,　@0））<BR>
     * <BR>
     * 　@(b)指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合<BR>
     * 　@　@［計算式］<BR>
     *　@　@（１）wk預り金不足額(T+1)を計算する。<BR>
     *　@　@　@wk預り金不足額(T+1)　@=　@ABS（MIN（顧客勘定(T+1)<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@－　@MAX（日計り拘束金(T+1)　@－　@振替額(預⇒保)(T+1),　@0）,　@0））<BR>
     *　@　@（２）上記（１）で計算したwk預り金不足額(T+1)をもとに預り金不足額(T+1)を計算する。<BR>
     *　@　@　@預り金不足額(T+1)　@=　@MAX(wk預り金不足額(T+1)　@－　@預り金不足額(T+0),　@0)<BR>
     * <BR>
     * 　@(c)指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合<BR>
     * 　@　@預り金不足額(T+1) = 0<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@　@・顧客勘定(T+n)　@　@　@・・・this.calc顧客勘定(T+n)<BR>
     * 　@　@　@・日計り拘束金(T+n)　@　@　@・・・this.get日計り拘束金(T+n)<BR>
     * 　@　@　@・振替額(預⇒保)(T+n)　@　@　@・・・this.calc預り金からの振替額(日付指定)(T+n)<BR>
     * 　@　@　@・特別立替金実績　@　@　@・・・this.資産余力情報<信用顧客>.get余力計算条件.get特別立替金実績（）<BR>
     * 　@　@　@・wk預り金不足額(T+1)　@　@　@・・・２-(b)（１）で計算したwk預り金不足額(T+1)
     *　@　@ 　@・預り金不足額(T+0)　@　@　@・・・this.calc預り金不足額（信用顧客）(T+0)
     * <BR>
     * ３）　@計算した預り金不足額(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48DB7E8B02E3
     */
    protected double calcAccountBalanceShortfallMargin(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceShortfallMargin(int)";
        log.entering(STR_METHOD_NAME);

        //預り金不足額
        double l_dblRequiredPayAmt = 0;
        //顧客勘定(T+0)
        double l_dblAccountCalculate0 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdAccountCalculate0 = new BigDecimal(l_dblAccountCalculate0 + "");
        //日計り拘束金(T+0)
        double l_dblDayTradeRestraint0 = this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdDayTradeRestraint0 = new BigDecimal(l_dblDayTradeRestraint0 + "");
        //振替額(預⇒保)(T+0)
        double l_dblAccountBalanceFromMarginDeposit0 =
            this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdAccountBalanceFromMarginDeposit0 =
            new BigDecimal(l_dblAccountBalanceFromMarginDeposit0 + "");

        //特別立替金実績
        double l_dblSpecialDebitAmount = this.tpCalcMargin.getCalcCondition().getSpecialDebitAmount();
        //顧客勘定(T+1)
        double l_dblAccountCalculate1 = this.calcAccountCalculate(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdAccountCalculate1 = new BigDecimal(l_dblAccountCalculate1 + "");
        //日計り拘束金(T+1)
        double l_dblDayTradeRestraint1 = this.getDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdDayTradeRestraint1 = new BigDecimal(l_dblDayTradeRestraint1 + "");
        //振替額(預⇒保)(T+1)
        double l_dblAccountBalanceFromMarginDeposit1 =
            this.calcAccountBalanceFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdAccountBalanceFromMarginDeposit1 =
            new BigDecimal(l_dblAccountBalanceFromMarginDeposit1 + "");
        //wk預り金不足額(T+1)
        double l_dblRequiredPayAmtWk1 = 0;

        //MAX（日計り拘束金(T+0)－　@振替額(預⇒保)(T+0),　@特別立替金実績,　@0）
        double l_dblMaxAmount0 = Math.max(l_bdDayTradeRestraint0.subtract(
            l_bdAccountBalanceFromMarginDeposit0).doubleValue(), l_dblSpecialDebitAmount);
        double l_dblMaxAmount = Math.max(l_dblMaxAmount0, 0);
        
        double l_dblMaxAmount1 = Math.max(l_bdDayTradeRestraint1.subtract(
            l_bdAccountBalanceFromMarginDeposit1).doubleValue(), 0);

        //預り金不足額(T+0)　@=　@ABS（MIN（顧客勘定(T+0)　@－　@MAX（日計り拘束金(T+0)
        //　@　@　@－　@振替額(預⇒保)(T+0),　@特別立替金実績,　@0）,　@0））
        //QA:WEB3-TPLIB-A-FT-0082
        double l_dblRequiredPayAmt0 = 
            Math.abs(Math.min(l_bdAccountCalculate0.subtract(
                new BigDecimal(l_dblMaxAmount + "")).doubleValue(), 0));

        //１）　@引数チェックを行う。
        //nが0以上1以下でない時、0を返却する
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //２）　@指定日の判定を行い、預り金不足額(T+n)を計算する。
        //a)指定日(=n)　@==　@0　@の場合
        //　@　@預り金不足額(T+0)を返す。
        if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_0)
        {
            l_dblRequiredPayAmt = l_dblRequiredPayAmt0;
        }

        //(b)指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合
        else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && this.isDepositAutoTransferDivFlag())
        {
            //（１）wk預り金不足額(T+1)を計算する。
            //　@wk預り金不足額(T+1)　@=　@ABS（MIN（顧客勘定(T+1)
            //　@　@  －　@MAX（日計り拘束金(T+1)　@－　@振替額(預⇒保)(T+1),　@0）,　@0））
            l_dblRequiredPayAmtWk1 =
                Math.abs(Math.min(l_bdAccountCalculate1.subtract(
                    new BigDecimal(l_dblMaxAmount1)).doubleValue(), 0));
            
            //（２）上記（１）で計算したwk預り金不足額(T+1)をもとに預り金不足額(T+1)を計算する。
            //　@預り金不足額(T+1)　@=　@MAX(wk預り金不足額(T+1)　@－　@預り金不足額(T+0),　@0)
            l_dblRequiredPayAmt = Math.max(new BigDecimal(l_dblRequiredPayAmtWk1).subtract(
                    new BigDecimal(l_dblRequiredPayAmt0)).doubleValue(), 0);
        }

        //c)指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
        //預り金不足額(T+1) = 0
        else if (l_intSpecifiedPoint == WEB3TPSpecifiedPointDef.T_1 && !this.isDepositAutoTransferDivFlag())
        {
            l_dblRequiredPayAmt = 0;
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblRequiredPayAmt;
    }

    /**
     * (calc預り金からの振替額（日付指定）)<BR>
     * (calc預り金からの振替額(日付指定))<BR>
     * <BR>
     * 顧客属性の判定後、引数の指定日(=n)に対応した「預り金からの振替額(日付指定)」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上1以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@預り金からの振替額(日付指定)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@(預り金⇒保証金)振替額(T+n)の注文数量を集計する。<BR>
     * 　@this.入出金注文単位一覧(預り金⇒保証金)(i).get受渡日（）　@==　@this.get期日(n)場合、以下の処理を行う。<BR>
     * 　@（i=this.入出金注文単位一覧(預り金⇒保証金)の件数）<BR>
     * 　@　@　@［集計］<BR>
     * 　@　@　@集計結果　@=　@集計結果　@＋　@注文数量<BR>
     * <BR>
     * 　@　@　@※　@各値の取得方法@<BR>
     * 　@　@　@　@・注文数量　@　@　@　@・・・this.入出金注文単位一覧(預り金⇒保証金)(i).get注文数量（）<BR>
     * <BR>
     * ４）　@集計した預り金からの振替額(日付指定)(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48DB83A10346
     */
    protected double calcAccountBalanceFromMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceFromMarginDeposit(int)";

        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdAccountBalanceFromMarginDeposit =
            new BigDecimal("0");
        //１）　@引数チェックを行う。
        //nが0以上1以下でない時、0を返却する
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //２）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        // 預り金からの振替額(日付指定)　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //３）　@(預り金⇒保証金)振替額(T+n)の注文数量を集計する。
        //this.入出金注文単位一覧(預り金⇒保証金)(i).get受渡日（）　@==　@this.get期日(n)の場合、以下の処理を行う。
        //集計した保証金からの振替額(日付指定)(T+n)を返却する。
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            int l_intLength  = this.aioOrderUnitListFromDepositToMargins.size();
            for (int i = 0; i < l_intLength; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromDepositToMargins.get(i);
                if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) == 0)
                {
                    l_bdAccountBalanceFromMarginDeposit =
                        l_bdAccountBalanceFromMarginDeposit.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }
        }
        log.exiting(STR_METHOD_NAME);

        //４）　@集計した預り金からの振替額(日付指定)(T+n)を返却する。
        return l_bdAccountBalanceFromMarginDeposit.doubleValue();
    }

    /**
     * (calc保証金からの振替額（日付指定）)<BR>
     * (calc保証金からの振替額(日付指定))<BR>
     * <BR>
     * 顧客属性の判定後、引数の指定日(=n)に対応した「保証金からの振替額(日付指定)」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上1以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@保証金からの振替額(日付指定)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@(保証金⇒預り金)振替額(T+n)の注文数量を集計する。<BR>
     * 　@this.入出金注文単位一覧(保証金⇒預り金)(i).get受渡日（）　@==　@this.get期日(n)場合、以下の処理を行う。<BR>
     * 　@（i=this.入出金注文単位一覧(保証金⇒預り金)の件数）<BR>
     * 　@　@　@［集計］<BR>
     * 　@　@　@集計結果　@=　@集計結果　@＋　@注文数量<BR>
     * <BR>
     * 　@　@　@※　@各値の取得方法@<BR>
     * 　@　@　@　@・注文数量　@　@　@　@・・・this.入出金注文単位一覧(保証金⇒預り金)(i).get注文数量（）<BR>
     * <BR>
     * ４）　@集計した保証金からの振替額(日付指定)(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48DB8675013B
     */
    protected double calcTransferFromMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalanceFromMarginDeposit(int)";

        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdTransferFromMarginDeposit =
            new BigDecimal("0");

        //１）　@引数チェックを行う。
        //nが0以上1以下でない時、0を返却する
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //２）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        // 保証金からの振替額(日付指定)　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //３）　@(保証金⇒預り金)振替額(T+n)の注文数量を集計する。
        //this.入出金注文単位一覧(保証金⇒預り金)(i).get受渡日（）　@==　@this.get期日(n)の場合、以下の処理を行う。
        //集計した保証金からの振替額(日付指定)(T+n)を返却する。
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            int l_intLength  = this.aioOrderUnitListFromMarginToDeposits.size();
            for (int i = 0; i < l_intLength; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromMarginToDeposits.get(i);
                if (WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) == 0)
                {
                    l_bdTransferFromMarginDeposit =
                        l_bdTransferFromMarginDeposit.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }
        }
        log.exiting(STR_METHOD_NAME);

        //４）　@集計した保証金からの振替額(日付指定)(T+n)を返却する。
        return l_bdTransferFromMarginDeposit.doubleValue();
    }

    /**
     * (get日計り拘束金)<BR>
     * (get日計り拘束金)<BR>
     * <BR>
     * 顧客属性の判定後、引数で指定された指定日(=n)の「日計り拘束金」を返却する。 <BR>
     * <BR>
     * １）　@引数チェックを行う。 <BR>
     * 　@nが0以上5以下でない時、0を返却する。 <BR>
     * <BR>
     * ２）　@顧客属性の判定を行い、引数で指定された指定日(=n)の「日計り拘束金」を返却する。 <BR>
     * 　@（１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合<BR>
     * 　@　@[返却値] <BR>
     * 　@　@this.資産余力情報<現物顧客>.get日計り拘束金（T+n） <BR>
     * <BR>
     * 　@（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合<BR>
     * 　@　@[返却値] <BR>
     * 　@　@this.資産余力情報<信用顧客>.get日計り拘束金（T+n） <BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48DB8E690017
     */
    protected double getDayTradeRestraint(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getDayTradeRestraint(int)";
        log.entering(STR_METHOD_NAME);

        double l_dblDayTradeRestraint = 0;

        //１）　@引数チェックを行う。
        //nが0以上5以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
        // this.資産余力情報<現物顧客>.get日計り拘束金（T+n）
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblDayTradeRestraint =  this.tpCalcEquity.getDayTradeRestraint(l_intSpecifiedPoint);
        }

        //（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
        // this.資産余力情報<信用顧客>.get日計り拘束金（T+n）
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblDayTradeRestraint =  this.tpCalcMargin.getDayTradeRestraint(l_intSpecifiedPoint);
        }
        log.exiting(STR_METHOD_NAME);

        return l_dblDayTradeRestraint;
    }

    /**
     * (calc顧客勘定)<BR>
     * (calc顧客勘定)<BR>
     * <BR>
     * 顧客属性の判定後、引数の指定日(=n)に対応した「顧客勘定」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行い、顧客勘定(T+n)を計算する。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客）の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@顧客勘定(T+n)　@=　@預り金(T+n)<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客）の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@顧客勘定(T+n)　@=　@預り金(T+n)　@－　@現金保証金(T+n)<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・預り金(T+n)　@　@　@　@　@　@　@　@　@ ・・・this.calc預り金(T+n)<BR>
     * 　@　@・現金保証金(T+n)　@　@　@　@　@　@・・・this.calc現金保証金(T+n)<BR>
     * <BR>
     * ３）　@計算した顧客勘定(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48CF028102C8
     */
    protected double calcAccountCalculate(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountCalculate(int)";
        log.entering(STR_METHOD_NAME);

        double l_dblAccountCalculate = 0;
        //１）　@引数チェックを行う。
        //nが0以上5以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //a）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
        //   顧客勘定(T+n)　@=　@預り金(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountCalculate = this.calcAccountBalance(l_intSpecifiedPoint);
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
        // 顧客勘定(T+n)　@=　@預り金(T+n)　@－　@現金保証金(T+n)
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountCalculate =
                new BigDecimal(this.calcAccountBalance(l_intSpecifiedPoint) + "").subtract(
                    new BigDecimal(this.calcCashMarginDeposit(l_intSpecifiedPoint) + "")).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);

        //３）　@計算した顧客勘定(T+n)を返却する。
        return l_dblAccountCalculate;
    }

    /**
     * (calc預り金)<BR>
     * (calc預り金) <BR>
     * <BR>
     * 引数の指定日(=n)に対応した「預り金」を算出し、返却する。 <BR>
     * <BR>
     * １）　@引数チェックを行う。 <BR>
     * 　@nが0以上5以下でない時、0を返却する。 <BR>
     * <BR>
     * ２）　@預り金(T+n)を計算する。 <BR>
     * 　@［計算式］ <BR>
     * 　@預り金(T+n)　@=　@預り金残高(T+n)　@－　@当日約定済代金(T+n)　@－　@当日未約定代金(T+n) <BR>
     * <BR>
     * 　@※　@計算式に使用する各値の取得方法@<BR>
     * 　@　@（１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合 <BR>
     * 　@　@　@・預り金残高(T+n)
     * 　@　@・・・this.資産余力情報<現物顧客>.get預り金残高(T+n) <BR>
     * 　@　@　@・当日約定済代金(T+n)　@　@　@　@・・・this.資産余力情報<現物顧客>.get当日約定済代金(T+n) <BR>
     * 　@　@　@・当日未約定代金(T+n)　@　@　@　@・・・this.資産余力情報<現物顧客>.get当日未約定代金(T+n) <BR>
     * <BR>
     * 　@　@（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合 <BR>
     * 　@　@　@・預り金残高(T+n)
     * 　@　@・・・this.資産余力情報<信用顧客>.get預り金残高(T+n) <BR>
     * 　@　@　@・当日約定済代金(T+n)　@　@　@　@・・・this.資産余力情報<信用顧客>.get当日約定済代金(T+n) <BR>
     * 　@　@　@・当日未約定代金(T+n)　@　@　@　@・・・this.資産余力情報<信用顧客>.get当日未約定代金(T+n) <BR>
     * <BR>
     * ３）　@計算した預り金(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48CF05B802C4
     */
    protected double calcAccountBalance(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcAccountBalance(int)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdAccountBalance = new BigDecimal("0");
        //預り金残高(T+n)
        double l_dblAccountBalanceAmount = 0;
        //当日約定済代金(T+n)
        double l_dblTodayExecutedAmount = 0;
        //当日未約定代金(T+n)
        double l_dblTodayUnexecutedAmount = 0;

        //１）　@引数チェックを行う。
        //nが0以上5以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
        //・預り金残高(T+n)　@　@　@　@　@ 　@　@・・・this.資産余力情報<現物顧客>.get預り金残高(T+n)
        //・当日約定済代金(T+n)　@　@　@　@・・・this.資産余力情報<現物顧客>.get当日約定済代金(T+n)
        //・当日未約定代金(T+n)　@　@　@　@・・・this.資産余力情報<現物顧客>.get当日未約定代金(T+n)
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountBalanceAmount = this.tpCalcEquity.getAccountBalance(l_intSpecifiedPoint);
            l_dblTodayExecutedAmount = this.tpCalcEquity.getTodayExecutedAmount(l_intSpecifiedPoint);
            l_dblTodayUnexecutedAmount = this.tpCalcEquity.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        }
        //（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
        //・預り金残高(T+n)　@　@　@　@　@ 　@　@・・・this.資産余力情報<信用顧客>.get預り金残高(T+n)
        //・当日約定済代金(T+n)　@　@　@　@・・・this.資産余力情報<信用顧客>.get当日約定済代金(T+n)
        //・当日未約定代金(T+n)　@　@　@　@・・・this.資産余力情報<信用顧客>.get当日未約定代金(T+n)
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblAccountBalanceAmount = this.tpCalcMargin.getAccountBalance(l_intSpecifiedPoint);
            l_dblTodayExecutedAmount = this.tpCalcMargin.getTodayExecutedAmount(l_intSpecifiedPoint);
            l_dblTodayUnexecutedAmount = this.tpCalcMargin.getTodayUnexecutedAmount(l_intSpecifiedPoint);
        }

        BigDecimal l_bdAccountBalanceAmount = new BigDecimal(l_dblAccountBalanceAmount + "");
        BigDecimal l_bdTodayExecutedAmount = new BigDecimal(l_dblTodayExecutedAmount + "");
        BigDecimal l_bdTodayUnexecutedAmount = new BigDecimal(l_dblTodayUnexecutedAmount + "");

        //２）　@預り金(T+n)を計算する。
        l_bdAccountBalance = l_bdAccountBalanceAmount.subtract(
            l_bdTodayExecutedAmount).subtract(l_bdTodayUnexecutedAmount);

        //３）　@計算した預り金(T+n)を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_bdAccountBalance.doubleValue();
    }

    /**
     * (calc現金保証金)<BR>
     * (calc現金保証金)<BR>
     * <BR>
     * 顧客属性の判定後、引数の指定日(=n)に対応した「現金保証金」を算出し、返却する。<BR>
     * <BR>
     * １）　@引数チェックを行う。<BR>
     * 　@nが0以上5以下でない時、0を返却する。<BR>
     * <BR>
     * ２）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@現金保証金　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@現金保証金残高(T+n)を取得する。<BR>
     * 　@［取得先］<BR>
     * 　@this.顧客勘定残高(マスタ情報)<保証金>.get残高(当日+n)<BR>
     * <BR>
     *　@※this.顧客勘定残高(マスタ情報)<保証金> == nullの場合、現金保証金残高(T+n) = 0とする<BR>
     * <BR>
     * ４）　@(預り金⇒保証金)振替額(T+n)の注文数量を、T+0～T+nまで集計する。<BR>
     * 　@this.入出金注文単位一覧(預り金⇒保証金)(i).get受渡日（）　@<=　@this.get期日(n) かつ、<BR>
     * 　@this.入出金注文単位一覧(預り金⇒保証金)(i).get発注日（）　@>=　@this.get期日(0) の場合、以下の処理を行う。<BR>
     * 　@（i=this.入出金注文単位一覧(預り金⇒保証金)の件数）<BR>
     * 　@　@　@［集計］<BR>
     * 　@　@　@注文数量(集計)　@=　@注文数量(集計)　@＋　@注文数量<BR>
     * <BR>
     * 　@　@　@※　@各値の取得方法@<BR>
     * 　@　@　@　@・注文数量　@　@　@　@・・・this.入出金注文単位一覧(預り金⇒保証金)(i).get注文数量（）<BR>
     * <BR>
     * ５）　@(保証金⇒預り金)振替額(T+n)の注文数量を、T+0～T+nまで集計する。<BR>
     * 　@this.入出金注文単位一覧(保証金⇒預り金)(i).get受渡日（）　@<=　@this.get期日(n) かつ、<BR>
     * 　@this.入出金注文単位一覧(預り金⇒保証金)(i).get発注日（）　@>=　@this.get期日(0) の場合、以下の処理を行う。<BR>
     * 　@（i=this.入出金注文単位一覧(保証金⇒預り金)の件数）<BR>
     * 　@　@　@［集計］<BR>
     * 　@　@　@注文数量(集計)　@=　@注文数量(集計)　@＋　@注文数量<BR>
     * <BR>
     * 　@　@　@※　@各値の取得方法@<BR>
     * 　@　@　@　@・注文数量　@　@　@　@・・・this.入出金注文単位一覧(保証金⇒預り金)(i).get注文数量（）<BR>
     * <BR>
     * ６）　@現金保証金(T+n)を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@現金保証金(T+n)　@=　@現金保証金残高(T+n)　@＋<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@sum（(預り金⇒保証金)振替額(T+0..n)）　@－　@sum（(保証金⇒預り金)振替額(T+0..n)）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・現金保証金残高(T+n)　@　@　@　@　@　@　@　@　@　@　@　@　@・・・３で取得した現金保証金高(T+n)<BR>
     * 　@　@・sum（(預り金⇒保証金)振替額(T+0..n)）　@　@　@・・・４で集計した(預り金⇒保証金)振替額(T+n)の注文数量(集計)<BR>
     * 　@　@・sum（(保証金⇒預り金)振替額(T+0..n)）　@　@　@・・・５で集計した(保証金⇒預り金)振替額(T+n)の注文数量(集計)<BR>
     * <BR>
     * ７）　@計算した現金保証金(T+n)を返却する。<BR>
     * @@param l_intSpecifiedPoint - (指定日)<BR>
     * (指定日)<BR>
     * @@return double
     * @@roseuid 48CF06800331
     */
    protected double calcCashMarginDeposit(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "calcCashMarginDeposit(int)";
        log.entering(STR_METHOD_NAME);

        BigDecimal l_bdCashMarginDeposit = new BigDecimal("0");
        //現金保証金残高(T+n)
        double l_dblCashBalance = 0;
        //(預り金⇒保証金)振替額(T+n)
        BigDecimal l_bdSumQuantityFromDepositToMargins = new BigDecimal("0");
        //(保証金⇒預り金)振替額(T+n)
        BigDecimal l_bdSumQuantityFromMarginsToDeposit = new BigDecimal("0");
        //１）　@引数チェックを行う。
        //nが0以上5以下でない時、0を返却する。
        if (l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_0
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_1    
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_2
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_3
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_4
            && l_intSpecifiedPoint != WEB3TPSpecifiedPointDef.T_5)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //２）　@顧客属性の判定を行う。
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //     現金保証金　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //  ３）以降の処理を行う。
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {

            if (this.tpCashBalanceParams != null)
            {
            
                //現金保証金残高(T+n)を取得する。
                switch(l_intSpecifiedPoint)
                {
                    //残高（当日+　@０日）
                    //this.顧客勘定残高(マスタ情報)<保証金>.get残高(当日+n)
                    case WEB3TPSpecifiedPointDef.T_0:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance0();
                        break;
                    //残高（当日+　@１日）
                    case WEB3TPSpecifiedPointDef.T_1:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance1();
                        break;
                    //残高（当日+　@２日））
                    case WEB3TPSpecifiedPointDef.T_2:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance2();
                        break;
                    //残高（当日+　@３日）
                    case WEB3TPSpecifiedPointDef.T_3:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance3();
                        break;
                    //残高（当日+　@４日）
                    case WEB3TPSpecifiedPointDef.T_4:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance4();
                        break;
                    //残高（当日+　@５日以降）
                    default:
                        l_dblCashBalance = this.tpCashBalanceParams.getCashBalance5();
                }

            }

            //４）　@(預り金⇒保証金)振替額(T+n)の注文数量を、T+0～T+nまで集計する。
            //this.入出金注文単位一覧(預り金⇒保証金)(i).get受渡日（）　@<=　@this.get期日(n) かつ、
            //this.入出金注文単位一覧(預り金⇒保証金)(i).get発注日（）　@>=　@this.get期日(0) の場合、以下の処理を行う。
            //　@（i=this.入出金注文単位一覧(預り金⇒保証金)の件数）
            //［集計］
            //   注文数量(集計)　@=　@注文数量(集計)　@＋　@注文数量
            int l_intLength  = this.aioOrderUnitListFromDepositToMargins.size();
            for (int i = 0; i < l_intLength; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromDepositToMargins.get(i);
                if ((WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) <= 0) && 
                    (WEB3DateUtility.compareToDay(WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd"),
                    this.getDate(0)) >= 0))
                {
                    l_bdSumQuantityFromDepositToMargins =
                        l_bdSumQuantityFromDepositToMargins.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }

            //５）　@(保証金⇒預り金)振替額(T+n)の注文数量を、T+0～T+nまで集計する。
            //this.入出金注文単位一覧(保証金⇒預り金)(i).get受渡日（）　@<=　@this.get期日(n) かつ、
            //this.入出金注文単位一覧(預り金⇒保証金)(i).get発注日（）　@>=　@this.get期日(0) の場合、以下の処理を行う。
            //（i=this.入出金注文単位一覧(保証金⇒預り金)の件数）
            //　@　@［集計］
            //注文数量(集計)　@=　@注文数量(集計)　@＋　@注文数量
            int l_intSize = this.aioOrderUnitListFromMarginToDeposits.size();
            for (int i = 0; i < l_intSize; i++)
            {
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)aioOrderUnitListFromMarginToDeposits.get(i);
                if ((WEB3DateUtility.compareToDay(l_aioOrderUnitRow.getDeliveryDate(),
                    this.getDate(l_intSpecifiedPoint)) <= 0) && 
                    (WEB3DateUtility.compareToDay(WEB3DateUtility.getDate(l_aioOrderUnitRow.getBizDate(), "yyyyMMdd"),
                    this.getDate(0)) >= 0))
                {
                    l_bdSumQuantityFromMarginsToDeposit =
                        l_bdSumQuantityFromMarginsToDeposit.add(
                            new BigDecimal(l_aioOrderUnitRow.getQuantity() + ""));
                }
            }

            BigDecimal l_bdCashBalance = new BigDecimal(l_dblCashBalance + "");

            //６）　@現金保証金(T+n)を計算する。
            //現金保証金(T+n)　@=　@現金保証金残高(T+n)　@＋
            //sum（(預り金⇒保証金)振替額(T+0..n)）　@－　@sum（(保証金⇒預り金)振替額(T+0..n)）
            l_bdCashMarginDeposit =
                l_bdCashBalance.add(l_bdSumQuantityFromDepositToMargins).subtract(
                    l_bdSumQuantityFromMarginsToDeposit);
        }

        log.exiting(STR_METHOD_NAME);
        //７）　@計算した現金保証金(T+n)を返却する。
        return l_bdCashMarginDeposit.doubleValue();
    }

    /**
     * (calc保証金率)<BR>
     * (calc保証金率) <BR>
     * <BR>
     * 顧客属性の判定後、「保証金率」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@保証金率　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@建玉代金の判定を行う。<BR>
     * 　@（a）「建玉代金　@=　@0」の場合<BR>
     * 　@　@保証金率　@=　@999.99を設定し、返却する。<BR>
     * <BR>
     * 　@（b）(a)以外の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@保証金率を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@保証金率　@=　@（実質保証金　@／　@建玉代金）　@×　@100<BR>
     * 　@　@（計算結果の小数点第三位以下を切り捨てとする。）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・実質保証金　@　@　@　@　@　@・・・this.calc実質保証金（）<BR>
     * 　@　@・建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉金( T + 0 )（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ + this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）<BR>
     * <BR>
     * ４）　@計算した保証金率を返却する。<BR>
     * @@return double
     * @@roseuid 48D33BFE0166
     */
    protected double calcMarginDepositRate()
    {
        final String STR_MEHTOD_NAME = "calcMarginDepositRate()";
        log.entering(STR_MEHTOD_NAME);

        double l_dblMarginDepositRate = 0;

        //建玉代金
        //建玉代金・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）
        //+ this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）
        BigDecimal l_bdContractAmount =
            new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "").add(
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + ""));

        //実質保証金
        //・実質保証金　@　@　@　@　@　@・・・this.calc実質保証金（）
        BigDecimal l_bdRealMargin = new BigDecimal(this.calcRealMargin() + "");
        //１）　@顧客属性の判定を行う。
        //　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //　@　@保証金率　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_MEHTOD_NAME);
            return 0;
        }
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@建玉代金の判定を行う。
            //（a）「建玉代金　@=　@0」の場合
            // 保証金率　@=　@999.99を設定し、返却する。
            if (GtlUtils.Double.isZero(l_bdContractAmount.doubleValue()))
            {
                log.exiting(STR_MEHTOD_NAME);
                return 999.99;
            }
            else
            {
                //３）　@保証金率を計算する
                //保証金率　@=　@（実質保証金　@／　@建玉代金）　@×　@100
                //（計算結果の小数点第三位以下を切り捨てとする。）
                l_dblMarginDepositRate = l_bdRealMargin.divide(
                        l_bdContractAmount, 10, BigDecimal.ROUND_DOWN).multiply(
                            new BigDecimal(100 + "")).setScale(
                            2, BigDecimal.ROUND_DOWN).doubleValue();
            }
        }
        log.exiting(STR_MEHTOD_NAME);

        //４）　@計算した保証金率を返却する。
        return l_dblMarginDepositRate;
    }

    /**
     * (get当日返済建玉代金)<BR>
     * (get当日返済建玉代金) <BR>
     * <BR>
     * 顧客属性の判定後、「当日返済建玉代金」を返却する。  <BR>
     * <BR>
     * 　@（１）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 <BR>
     * 　@　@［返却値］  <BR>
     * 　@　@0 <BR>
     * <BR>
     * 　@（２）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 <BR>
     * 　@　@［返却値］  <BR>
     * 　@　@this.資産余力情報<信用顧客>.get余力計算結果詳細Params<信用顧客>.get当日返済建玉代金（）<BR>
     * @@return double
     * @@roseuid 48D99BE40061
     */
    protected double getTodayRepayContractAmount()
    {
        final String STR_METHOD_NAME = "getTodayRepayContractAmount()";
        log.entering(STR_METHOD_NAME);

        double l_dblTodayRepayContractAmount = 0;

        //（１）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //［返却値］
        // 0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（２）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //this.資産余力情報<信用顧客>.get余力計算結果詳細Params<信用顧客>.get当日返済建玉代金（）
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            l_dblTodayRepayContractAmount =
                this.tpCalcMargin.getCalcResultDetailMargin().getTodayRepayContractAmount();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblTodayRepayContractAmount;
    }

    /**
     * (calc保証金増減)<BR>
     * (calc保証金増減)<BR>
     * <BR>
     * 顧客属性の判定後、「保証金増減」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@保証金増減　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@入金請求管理テーブルのレコードをチェックする。<BR>
     * 　@（a）this.入金請求管理Params == nullの場合<BR>
     * 　@［計算式］<BR>
     * 　@保証金増減　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.入金請求管理Params != nullの場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@現金保証金純増減を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@現金保証金純増減　@=　@現金保証金　@－　@前日繰越現金保証金 <BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・現金保証金　@　@　@・・・this.calc現金保証金(T+0)（）<BR>
     * 　@　@・前日繰越現金保証金　@　@　@・・・this.入金請求管理Params.get現金保証金（）<BR>
     * <BR>
     * ４）　@代用評価額純増減を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@代用評価額純増減　@=　@代用証券評価額　@－　@前日繰越代用証券評価額<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・代用証券評価額　@　@　@・・・this.資産余力情報<信用顧客>.get余力計算結果詳細Params<信用顧客>.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@get代用証券評価額(前日単価評価)（）<BR>
     * 　@　@・前日繰越代用証券評価額　@　@　@・・・this.入金請求管理Params.get代用証券評価額（）<BR>
     * <BR>
     * ５）　@保証金増減を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@保証金増減　@=　@現金保証金純増減　@＋　@代用評価額純増減<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・現金保証金純増減　@　@・・・３で計算した現金保証金純増減<BR>
     * 　@　@・代用評価額純増減　@　@・・・４で計算した代用評価額純増減<BR>
     * <BR>
     * ６）　@計算した保証金増減を返却する。<BR>
     * @@return double
     * @@roseuid 48D9E9750019
     */
    protected double calcMarginDepositInDe()
    {
        final String STR_METHOD_NAME = "calcMarginDepositInDe()";
        log.entering(STR_METHOD_NAME);

        //保証金増減
        BigDecimal l_bdMarginDepositInDe = new BigDecimal("0");
        //現金保証金純増減
        BigDecimal l_bdCashMarginDepositInDe = new BigDecimal("0");
        //代用評価額純増減
        BigDecimal l_bdSubstituteAssetOldDayValueInDe = new BigDecimal("0");
        //・現金保証金　@　@　@・・・this.calc現金保証金(T+0)（）
        double l_dblCashMarginDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdCashMarginDeposit = new BigDecimal(l_dblCashMarginDeposit + "");

        //１）　@顧客属性の判定を行う。
        //　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //　@保証金増減　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        //２）以降の処理を行う。
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //前日繰越現金保証金　@　@　@・・・this.入金請求管理Params.get現金保証金（）
            double l_dblBeforeTodayCashDeposit;
            //前日繰越代用証券評価額　@　@　@・・・this.入金請求管理Params.get代用証券評価額（）
            double l_dblSubstituteSecurityAsset;
            if (this.paymentRequisitMngParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            else
            {
                l_dblBeforeTodayCashDeposit = this.paymentRequisitMngParams.getCashMarginDeposit();
                l_dblSubstituteSecurityAsset = this.paymentRequisitMngParams.getSubstituteSecurityAsset();

                //代用証券評価額
                double l_dblSubstituteAssetOldDayValue =
                    this.tpCalcMargin.getCalcResultDetailMargin().getSubstituteAssetOldDayValue();
                BigDecimal l_bdSubstituteAssetOldDayValue = new BigDecimal(l_dblSubstituteAssetOldDayValue + "");
                BigDecimal l_bdBeforeTodayCashDeposit = new BigDecimal(l_dblBeforeTodayCashDeposit + "");
                BigDecimal l_bdSubstituteSecurityAsset = new BigDecimal(l_dblSubstituteSecurityAsset + "");

                //２）　@現金保証金純増減を計算する
                //現金保証金純増減　@=　@現金保証金　@－　@前日繰越現金保証金
                l_bdCashMarginDepositInDe = l_bdCashMarginDeposit.subtract(l_bdBeforeTodayCashDeposit);

                //３）　@代用評価額純増減を計算する。
                //代用評価額純増減　@=　@代用証券評価額　@－　@前日繰越代用証券評価額
                l_bdSubstituteAssetOldDayValueInDe =
                    l_bdSubstituteAssetOldDayValue.subtract(l_bdSubstituteSecurityAsset);

                //４）　@保証金増減を計算する。
                //保証金増減　@=　@現金保証金純増減　@＋　@代用評価額純増減
                l_bdMarginDepositInDe =
                    l_bdCashMarginDepositInDe.add(l_bdSubstituteAssetOldDayValueInDe);
            }
        }
        log.exiting(STR_METHOD_NAME);

        //５）　@計算した保証金増減を返却する。
        return  l_bdMarginDepositInDe.doubleValue();
    }

    /**
     * (calc保証金増減（見込金額）)<BR>
     * (calc保証金増減(見込金額))<BR>
     * <BR>
     * 顧客属性の判定後、「保証金増減(見込金額)」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@保証金増減(見込金額)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@現金保証金純増減を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@現金保証金純増減　@=　@現金保証金　@－　@前日繰越現金保証金<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・現金保証金　@　@　@　@　@　@　@　@　@・・・this.calc現金保証金(T+1)<BR>
     * 　@　@・前日繰越現金保証金　@　@　@・・・this.calc現金保証金（T+0）<BR>
     * <BR>
     * ３）　@代用評価額純増減を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@代用評価額純増減　@=　@代用証券評価額　@－　@前日繰越代用証券評価額<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・代用証券評価額　@　@　@　@　@　@　@　@・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get代用証券評価額(T+1)（）<BR>
     * 　@　@・前日繰越代用証券評価額　@　@・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get代用証券評価額(T+0)（）<BR>
     * <BR>
     * ４）　@保証金増減(見込金額)を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@保証金増減(見込金額)　@=　@現金保証金純増減　@＋　@代用評価額純増減<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・現金保証金純増減　@　@・・・２で計算した現金保証金純増減<BR>
     * 　@　@・代用評価額純増減　@　@・・・３で計算した代用評価額純増減<BR>
     * <BR>
     * ５）　@計算した保証金増減(見込金額)を返却する。<BR>
     * @@return double
     * @@roseuid 48D9F5FA0225
     */
    protected double calcMarginDepositInDeExpect()
    {
        final String STR_METHOD_NAME = "calcMarginDepositInDeExpect";
        log.entering(STR_METHOD_NAME);

        //保証金増減(見込金額)
        BigDecimal l_bdMarginDepositInDeExpect = new BigDecimal("0");
        //現金保証金純増減
        BigDecimal l_bdCashMarginDepositInDe = new BigDecimal("0");
        //現金保証金
        double l_dblCashMarginDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdCashMarginDeposit = new BigDecimal(l_dblCashMarginDeposit + "");
        //前日繰越現金保証金
        double l_dblBeforeTodayCashDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        BigDecimal l_bdBeforeTodayCashDeposit = new BigDecimal(l_dblBeforeTodayCashDeposit + "");
        //代用評価額純増減
        BigDecimal l_bdSubstituteAssetOldDayValueInDe = new BigDecimal("0");
        //代用証券評価額
        double l_dblSubstituteAssetOldDayValue =
            this.tpCalcMargin.getCalcResultMargin().getSubstituteSecurityAsset1();
        BigDecimal l_bdSubstituteAssetOldDayValue = new BigDecimal(l_dblSubstituteAssetOldDayValue + "");
        //前日繰越代用証券評価額
        double l_dblSubstituteSecurityAsset =
            this.tpCalcMargin.getCalcResultMargin().getSubstituteSecurityAsset0();
        BigDecimal l_bdSubstituteSecurityAsset = new BigDecimal(l_dblSubstituteSecurityAsset + "");

        //１）　@顧客属性の判定を行う。
        //保証金増減(見込金額)　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        // ２）以降の処理を行う。
        else if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //２）　@現金保証金純増減を計算する。
            //現金保証金純増減　@=　@現金保証金　@－　@前日繰越現金保証金
            l_bdCashMarginDepositInDe =
                l_bdCashMarginDeposit.subtract(l_bdBeforeTodayCashDeposit);

            //３）　@代用評価額純増減を計算する。
            //代用評価額純増減　@=　@代用証券評価額　@－　@前日繰越代用証券評価額
            l_bdSubstituteAssetOldDayValueInDe =
                l_bdSubstituteAssetOldDayValue.subtract(l_bdSubstituteSecurityAsset);

            //４）　@保証金増減(見込金額)を計算する。
            //保証金増減(見込金額)　@=　@現金保証金純増減　@＋　@代用評価額純増減
            l_bdMarginDepositInDeExpect =
                l_bdCashMarginDepositInDe.add(l_bdSubstituteAssetOldDayValueInDe);
        }

        log.exiting(STR_METHOD_NAME);

        //５）　@計算した保証金増減(見込金額)を返却する。
        return l_bdMarginDepositInDeExpect.doubleValue();
    }

    /**
     * (calc第一水準追証当日消し込み換算額)<BR>
     * (calc第一水準追証当日消し込み換算額)<BR>
     * <BR>
     * 顧客属性と計算条件の判定後、「第一水準追証当日消し込み換算額」を算出し、返却する。 <BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@第一水準追証当日消し込み換算額　@=　@0 を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。 <BR>
     * <BR>
     * ２）　@計算条件の判定を行う。<BR>
     * 　@（a）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”today.clearance.determination.div”）　@==　@”1”の場合<BR>
     * 　@　@３）以降の処理を行う。 <BR>
     * <BR>
     * 　@（b）this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”today.clearance.determination.div”）　@==　@NULL<BR>
     * 　@　@　@又は、this.資産余力情報<信用顧客>.get余力計算条件.<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@get会社部店別余力計算条件（”today.clearance.determination.div”）　@!=　@”1”の場合<BR>
     * 　@　@第一水準追証当日消し込み換算額　@=　@0 を設定し、返却する。<BR>
     * <BR>
     * ３）　@第一水準追証決済必要額を取得する。<BR>
     * 　@［取得先］ <BR>
     * 　@第一水準追証決済必要額　@=　@this.入金請求管理Params.get第一水準追証決済必要額（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、第一水準追証決済必要額 = 0 とする。<BR>
     * <BR>
     * ４）　@第一水準追証決済必要額の判定を行う。<BR>
     * 　@（a）第一水準追証決済必要額　@==　@0の場合<BR>
     * 　@　@第一水準追証当日消し込み換算額　@=　@0 を設定し、返却する。<BR>
     * <BR>
     * 　@（b）第一水準追証決済必要額　@!=　@0の場合<BR>
     * 　@　@５）以降の処理を行う。 <BR>
     * <BR>
     * ５）　@第一水準追証当日消し込み換算額を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@第一水準追証当日消し込み換算額　@=　@第一水準追証金額　@×　@当日返済建玉代金<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@／　@第一水準追証決済必要額<BR>
     * 　@（計算結果の小数点以下を切り捨てとする。）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・第一水準追証金額　@　@　@　@　@　@　@　@　@・・・this.入金請求管理Params.get第一水準追証金額（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、第一水準追証金額 = 0 とする。<BR>
     * 　@　@・当日返済建玉代金　@　@　@　@　@　@　@　@　@・・・this.get当日返済建玉代金（）<BR>
     * 　@　@・第一水準追証決済必要額　@　@　@　@　@・・・３で取得した第一水準追証決済必要額<BR>
     * <BR>
     * ６）　@計算した第一水準追証当日消し込み換算額を返却する。<BR>
     * @@return double
     * @@roseuid 48DA082100EA
     */
    protected double calcFirstAdddepositEliminateAmount()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositEliminateAmount()";
        log.entering(STR_METHOD_NAME);

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //当日保証金入金額　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            String l_strInstBranCalcCondition =
                this.tpCalcMargin.getCalcCondition().getInstBranCalcCondition(
                    WEB3BranchPreferencesNameDef.TODAY_CLEARANCE_DETERMINATION_DIV);

            //this.資産余力情報<信用顧客>.get余力計算条件.
            //　@get会社部店別余力計算条件（”today.clearance.determination.div”）　@==　@NULL
            //又は、this.資産余力情報<信用顧客>.get余力計算条件.
            // get会社部店別余力計算条件（”today.clearance.determination.div”）　@!=　@”1”の場合
            if (l_strInstBranCalcCondition == null
                || !WEB3TodayClearanceDeterminationDivDef.EXECUTE.equals(l_strInstBranCalcCondition))
            {
                //第一水準追証当日消し込み換算額　@=　@0 を設定し、返却する。
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //第一水準追証決済必要額　@=　@this.入金請求管理Params.get第一水準追証決済必要額（）
            //ただし、this.入金請求管理Params == null の場合は、第一水準追証決済必要額 = 0 とする。
            double l_dblFirstSettlement = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstSettlement = this.paymentRequisitMngParams.getFirstSettlement();
            }
            BigDecimal l_bdFirstSettlement = new BigDecimal("" + l_dblFirstSettlement);
            //（a）第一水準追証決済必要額　@==　@0の場合
            if (GtlUtils.Double.isZero(l_dblFirstSettlement))
            {
                //第一水準追証当日消し込み換算額　@=　@0 を設定し、返却する。
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //第一水準追証金額　@　@　@　@　@　@　@　@　@・・・this.入金請求管理Params.get第一水準追証金額（）
            double l_dblFirstDepositAmount = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstDepositAmount = this.paymentRequisitMngParams.getFirstDepositAmount();
            }
            BigDecimal l_bdFirstDepositAmount = new BigDecimal("" + l_dblFirstDepositAmount);
            //当日返済建玉代金　@　@　@　@　@　@　@　@　@・・・this.get当日返済建玉代金（）
            double l_dblTodayRepayContractAmount = this.getTodayRepayContractAmount();
            BigDecimal l_bdTodayRepayContractAmount = new BigDecimal("" + l_dblTodayRepayContractAmount);
            //第一水準追証決済必要額　@　@ 　@　@・・・３で取得した第一水準追証決済必要額
            //　@第一水準追証当日消し込み換算額　@=　@第一水準追証金額　@×　@当日返済建玉代金 ／　@第一水準追証決済必要額
            //　@（計算結果の小数点以下を切り捨てとする。）
            log.exiting(STR_METHOD_NAME);
            return l_bdFirstDepositAmount.multiply(
                    l_bdTodayRepayContractAmount).divide(
                        l_bdFirstSettlement, 0, BigDecimal.ROUND_DOWN).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc当日保証金入金額)<BR>
     * (calc当日保証金入金額)<BR>
     * <BR>
     * 顧客属性の判定後、「当日保証金入金額」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@当日保証金入金額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@当日保証金入金額を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@当日保証金入金額　@=　@MAX（保証金増減，　@0）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・保証金増減　@　@　@　@　@　@　@・・・this.calc保証金増減（）<BR>
     * 　@<BR>
     * ３）　@計算した当日保証金入金額を返却する。<BR>
     * @@return double
     * @@roseuid 48DA37EE0266
     */
    protected double calcTodayDepositPaymentAmount()
    {
        final String STR_METHOD_NAME = "calcTodayDepositPaymentAmount()";
        log.entering(STR_METHOD_NAME);
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //当日保証金入金額　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //保証金増減　@　@　@　@　@　@　@・・・this.calc保証金増減（）
            double l_dblMarginDepositInDe = this.calcMarginDepositInDe();
            //当日保証金入金額　@=　@MAX（保証金増減，　@0）
            log.exiting(STR_METHOD_NAME);
            return Math.max(l_dblMarginDepositInDe, 0);
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc当日保証金入金額（見込金額）)<BR>
     * (calc当日保証金入金額(見込金額))<BR>
     * <BR>
     * 顧客属性の判定後、「当日保証金入金額(見込金額)」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@当日保証金入金額(見込金額)　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@当日保証金入金額(見込金額)を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@当日保証金入金額(見込金額)　@=　@MAX（保証金増減，　@0）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・保証金増減　@　@　@　@　@　@　@・・・this.calc保証金増減(見込金額)（）<BR>
     * 　@<BR>
     * ３）　@計算した当日保証金入金額(見込金額)を返却する。<BR>
     * @@return double
     * @@roseuid 48DA38D00365
     */
    protected double calcTodayDepositPaymentAmountExpect()
    {
        final String STR_METHOD_NAME = "calcTodayDepositPaymentAmountExpect()";
        log.entering(STR_METHOD_NAME);
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //当日保証金入金額(見込金額)　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //保証金増減　@　@　@　@　@　@　@・・・this.calc保証金増減(見込金額)（）
            double l_dblMarginDepositInDeExpect = this.calcMarginDepositInDeExpect();
            //当日保証金入金額(見込金額)　@=　@MAX（保証金増減，　@0）
            log.exiting(STR_METHOD_NAME);
            return Math.max(l_dblMarginDepositInDeExpect, 0);
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc実質保証金)<BR>
     * (calc実質保証金) <BR>
     * <BR>
     * 顧客属性の判定後、「実質保証金」を算出し、返却する。 <BR>
     * <BR>
     * １）　@顧客属性の判定を行う。 <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 <BR>
     * 　@　@実質保証金　@=　@0 を設定し、返却する。 <BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 <BR>
     * 　@　@２）以降の処理を行う。  <BR>
     * <BR>
     * ２）　@実質保証金を計算する。 <BR>
     * 　@［計算式］ <BR>
     * 　@実質保証金　@=　@（現金保証金　@＋　@代用証券評価額） <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@－　@（ABS（MIN（建玉評価損益，　@0））　@＋　@建玉諸経費　@＋　@未受渡決済損） <BR>
     * <BR>
     * 　@※　@計算式に使用する各値の取得方法@ <BR>
     * 　@　@・現金保証金　@　@　@　@　@・・・this.calc現金保証金(T+0)（）<BR>
     * 　@　@・代用証券評価額　@　@・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get代用証券評価額(T+0)（）<BR>
     * 　@　@・建玉評価損益　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉価損益( T + 0 )()<BR>
     * 　@　@・建玉諸経費　@　@　@　@　@・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get建玉諸経費(T+0）（）<BR>
     * 　@　@・未受渡決済損　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未受渡建玉済損(T+0)（）<BR>
     * <BR>
     * ３）　@計算した実質保証金を返却する。<BR>
     * @@return double
     * @@roseuid 48DA24F5022C
     */
    protected double calcRealMargin()
    {
        final String STR_METHOD_NAME = "calcRealMargin()";
        log.entering(STR_METHOD_NAME);
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //実質保証金　@=　@0 を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //現金保証金　@　@　@　@　@・・・this.calc現金保証金(T+0)（）
            double l_dblCashMarginDeposit = this.calcCashMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
            BigDecimal l_bdCashMarginDeposit = new BigDecimal("" + l_dblCashMarginDeposit);
            //代用証券評価額　@　@・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get代用証券評価額(T+0)（）
            double l_dblSubstituteSecurityAsset0 =
                this.tpCalcMargin.getCalcResultMargin().getSubstituteSecurityAsset0();
            BigDecimal l_bdSubstituteSecurityAsset0 = new BigDecimal("" + l_dblSubstituteSecurityAsset0);
            //建玉評価損益　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉評価損益( T + 0 )()
            double l_dblContractAssetProfitLoss =
                this.tpCalcMargin.getCalcResultMargin().getContractAssetProfitLoss();
            //建玉諸経費　@　@　@　@　@・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get建玉諸経費(T+0）（）
            double l_dblContractTotalCost =
                this.tpCalcMargin.getCalcResultMargin().getContractTotalCost();
            BigDecimal l_bdContractTotalCost = new BigDecimal("" + l_dblContractTotalCost);
            //未受渡決済損　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未受渡建玉決済損(T+0)（）
            double l_dblUndeliContractLoss0 =
                this.tpCalcMargin.getCalcResultMargin().getUndeliContractLoss0();
            BigDecimal l_bdUndeliContractLoss0 = new BigDecimal("" + l_dblUndeliContractLoss0);

            //実質保証金　@=　@（現金保証金　@＋　@代用証券評価額）
            //      －　@（ABS（MIN（建玉評価損益，　@0））　@＋　@建玉諸経費　@＋　@未受渡決済損）
            BigDecimal l_bdValue = new BigDecimal(Math.abs(Math.min(l_dblContractAssetProfitLoss, 0)));

            BigDecimal l_bdCount =
                l_bdValue.add(l_bdContractTotalCost).add(l_bdUndeliContractLoss0);

            log.exiting(STR_METHOD_NAME);
            return l_bdCashMarginDeposit.add(l_bdSubstituteSecurityAsset0).subtract(l_bdCount).doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc第一水準追証未解消金額)<BR>
     * (calc第一水準追証未解消金額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証未解消金額」を算出し、返却する。 <BR>
     * <BR>
     * １）　@顧客属性の判定を行い、第一水準追証未解消金額を計算する。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@［計算式］ <BR>
     * 　@　@第一水準追証未解消金額　@=　@0<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@［計算式］<BR>
     * 　@　@第一水準追証未解消金額　@=　@MAX（第一水準追証金額　@－　@（当日保証金入金額　@＋　@当日消し込み換算額），　@0）<BR>
     * <BR>
     * 　@　@※　@各値の取得方法@<BR>
     * 　@　@　@・第一水準追証金額　@　@　@　@　@　@　@　@　@・・・this.入金請求管理Params.get第一水準追証金額（）<BR>
     * 　@　@　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、第一水準追証金額 = 0 とする。<BR>
     * 　@　@　@・当日保証金入金額　@　@　@　@　@　@　@　@　@・・・this.calc当日保証金入金額（）<BR>
     * 　@　@　@・当日消し込み換算額　@　@　@　@　@　@　@　@・・・this.calc第一水準追証当日消し込み換算額（）<BR>
     * <BR>
     * ２）　@計算した第一水準追証未解消金額を返却する。<BR>
     * @@return double
     * @@roseuid 48DA349A0295
     */
    protected double calcFirstAdddepositUncancelAmt()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositUncancelAmt()";
        log.entering(STR_METHOD_NAME);
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //第一水準追証未解消金額　@=　@0
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //第一水準追証金額　@　@　@　@　@　@　@　@　@・・・this.入金請求管理Params.get第一水準追証金額（）
            //ただし、this.入金請求管理Params == null の場合は、第一水準追証金額 = 0 とする。
            double l_dblFirstDepositAmount = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstDepositAmount = this.paymentRequisitMngParams.getFirstDepositAmount();
            }

            BigDecimal l_bdFirstDepositAmount = new BigDecimal("" + l_dblFirstDepositAmount);
            //当日保証金入金額　@　@　@　@　@　@　@　@　@・・・this.calc当日保証金入金額（）
            double l_dblTodayDepositPaymentAmount = this.calcTodayDepositPaymentAmount();
            BigDecimal l_bdTodayDepositPaymentAmount = new BigDecimal("" + l_dblTodayDepositPaymentAmount);
            //当日消し込み換算額　@　@　@　@　@　@　@　@・・・this.calc第一水準追証当日消し込み換算額（）
            double l_dblFirstAdddepositEliminateAmount = this.calcFirstAdddepositEliminateAmount();
            BigDecimal l_bdFirstAdddepositEliminateAmount = new BigDecimal("" + l_dblFirstAdddepositEliminateAmount);

            //第一水準追証未解消金額　@=　@MAX（第一水準追証金額　@－　@（当日保証金入金額　@＋　@当日消し込み換算額），　@0）
            BigDecimal l_bdAmount = l_bdTodayDepositPaymentAmount.add(l_bdFirstAdddepositEliminateAmount);

            log.exiting(STR_METHOD_NAME);
            return Math.max(l_bdFirstDepositAmount.subtract(l_bdAmount).doubleValue(), 0);
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc第一水準追証未解消決済必要額)<BR>
     * (calc第一水準追証未解消決済必要額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証未解消決済必要額」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@第一水準追証未解消決済必要額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@ゼロ判定を行う。<BR>
     * 　@（a）第一水準追証金額　@==　@0の場合<BR>
     * 　@　@第一水準追証未解消決済必要額　@=　@0 を設定し、返却する。<BR>
     * <BR>
     * 　@（b）第一水準追証金額　@!=　@0の場合<BR>
     * 　@　@３）以降の処理を行う。 <BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・第一水準追証金額　@　@・・・this.入金請求管理Params.get第一水準追証金額（）<BR>
     * 　@　@ただし、this.入金請求管理Params == null の場合は、第一水準追証金額 = 0 とする。<BR>
     * <BR>
     * ３）　@第一水準追証未解消決済必要額を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@第一水準追証未解消決済必要額　@=　@第一水準追証決済必要額　@×　@第一水準追証未解消金額　@／　@第一水準追証金額<BR>
     * 　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・第一水準追証決済必要額　@　@　@・・・this.入金請求管理Params.get第一水準追証決済必要額（）<BR>
     * 　@　@　@　@　@　@　@ただし、this.入金請求管理Params == null の場合は、第一水準追証決済必要額 = 0 とする。<BR>
     * 　@　@・第一水準追証未解消金額　@　@　@・・・this.calc第一水準追証未解消金額（）<BR>
     * <BR>
     * ４）　@計算した第一水準追証未解消決済必要額を返却する。<BR>
     * @@return double
     * @@roseuid 48DA3ABB02AC
     */
    protected double calcFirstAdddepositUncancelSettleRequiredAmt()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositUncancelSettleRequiredAmt()";
        log.entering(STR_METHOD_NAME);
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //第一水準追証未解消決済必要額　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //第一水準追証金額　@　@・・・this.入金請求管理Params.get第一水準追証金額（）
            //ただし、this.入金請求管理Params == null の場合は、第一水準追証金額 = 0 とする。
            double l_dblFirstDepositAmount = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstDepositAmount = this.paymentRequisitMngParams.getFirstDepositAmount();
            }

            BigDecimal l_bdFirstDepositAmount = new BigDecimal("" + l_dblFirstDepositAmount);
            //（a）第一水準追証金額　@==　@0の場合
            //第一水準追証未解消決済必要額　@=　@0 を設定し、返却する。
            if (GtlUtils.Double.isZero(l_dblFirstDepositAmount))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //第一水準追証決済必要額　@　@　@・・・this.入金請求管理Params.get第一水準追証決済必要額（）
            //ただし、this.入金請求管理Params == null の場合は、第一水準追証決済必要額 = 0 とする。
            double l_dblFirstSettlement = 0;
            if (this.paymentRequisitMngParams != null)
            {
                l_dblFirstSettlement = this.paymentRequisitMngParams.getFirstSettlement();
            }

            BigDecimal l_bdFirstSettlement = new BigDecimal("" + l_dblFirstSettlement);

            //第一水準追証未解消金額　@　@　@・・・this.calc第一水準追証未解消金額（）
            double l_dblFirstAdddepositUncancelAmt = this.calcFirstAdddepositUncancelAmt();

            BigDecimal l_bdFirstAdddepositUncancelAmt = new BigDecimal("" + l_dblFirstAdddepositUncancelAmt);
            //第一水準追証未解消決済必要額を計算する。
            //［計算式］
            //第一水準追証未解消決済必要額　@=　@第一水準追証決済必要額　@×　@第一水準追証未解消金額　@／　@第一水準追証金額
            //（計算結果の小数点以下を切り上げとする。）
            log.exiting(STR_METHOD_NAME);
            return l_bdFirstSettlement.multiply(
                    l_bdFirstAdddepositUncancelAmt).divide(
                        l_bdFirstDepositAmount, 0, BigDecimal.ROUND_CEILING).doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc第一水準追証金額)<BR>
     * (calc第一水準追証金額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証金額」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@第一水準追証金額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@第二水準追証の判定を行う。<BR>
     * 　@（a）第二水準追証未入金　@>　@0　@又は　@第二水準追証請求(2)　@>　@0　@又は<BR>
     * 　@　@　@第二水準追証請求(1)　@>　@0　@又は　@第二水準追証請求見込　@>　@0 の場合<BR>
     * 　@　@第一水準追証金額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）(a)以外の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・第二水準追証未入金　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(未入金)<BR>
     * 　@　@・第二水準追証請求(2)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求2)<BR>
     * 　@　@・第二水準追証請求(1)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求1)<BR>
     * 　@　@・第二水準追証請求見込　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(見込金額)<BR>
     * <BR>
     * ３）　@第一水準追証金額を計算する。<BR>
     * <BR>
     * 　@（a）建玉代金　@==　@0の場合 <BR>
     * 　@第一水準追証金額　@=　@0 を設定し、返却する。 <BR>
     * <BR>
     * 　@（b）建玉代金　@!=　@0の場合 <BR>
     * 　@［計算式］<BR>
     * 　@第一水準追証金額　@=　@ABS（MIN（実質保証金　@－　@（建玉代金　@×　@第一水準保証金維持率　@／　@100），　@0））<BR>
     * 　@※　@「建玉代金　@×　@第一水準保証金維持率　@／　@100」の計算結果は小数点以下を切り上げとする。<BR> 
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・実質保証金　@　@　@・・・this.calc実質保証金（）<BR>
     * 　@　@・建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ +  this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）<BR>
     * 　@　@・第一水準保証金維持率　@　@　@・・・this.get第一水準追証保証金維持率（）<BR>
     * <BR>
     * ４）　@計算した第一水準追証金額を返却する。<BR>
     * @@return double
     * @@roseuid 48DA2CBD0166
     */
    protected double calcFirstAdddepositAmount()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositAmount()";
        log.entering(STR_METHOD_NAME);

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //第一水準追証金額　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //　@・第二水準追証未入金　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(未入金)
            double l_dblSecondDepositNonPay = this.createSecondAdddepositNotClearInfo().secondDepositNonPay;
            //　@・第二水準追証請求(2)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求2)
            double l_dblSecondDeposit2 = this.createSecondAdddepositNotClearInfo().secondDeposit2;
            //　@・第二水準追証請求(1)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求1)
            double l_dblSecondDeposit1 = this.createSecondAdddepositNotClearInfo().secondDeposit1;
            //　@・第二水準追証請求見込　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(見込金額)
            double l_dblSecondDepositExpect = this.createSecondAdddepositNotClearInfo().secondDepositExpect;

            //（a）第二水準追証未入金　@>　@0　@又は　@第二水準追証請求(2)　@>　@0　@又は
            //第二水準追証請求(1)　@>　@0　@又は　@第二水準追証請求見込　@>　@0 の場合
            //第一水準追証金額　@=　@0を設定し、返却する。
            if (l_dblSecondDepositNonPay > 0
                || l_dblSecondDeposit2 > 0
                || l_dblSecondDeposit1 > 0
                || l_dblSecondDepositExpect > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //実質保証金　@　@　@　@　@・・・this.calc実質保証金（）
            double l_dblRealMargin = this.calcRealMargin();
            BigDecimal l_bdRealMargin = new BigDecimal("" + l_dblRealMargin);
            //建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）
            //      + this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //建玉代金
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);

            //建玉代金　@!=　@0の場合
            if (!GtlUtils.Double.isZero(l_bdContractAmount.doubleValue()))
            {
                //第一水準保証金維持率　@　@　@・・・this.get第一水準追証保証金維持率（）
                double l_dblFirstAdddepositDepositRate = this.getFirstAdddepositDepositRate();
                BigDecimal l_bdFirstAdddepositDepositRate = new BigDecimal("" + l_dblFirstAdddepositDepositRate);
                //第一水準追証金額を計算する。
                //［計算式］
                //第一水準追証金額　@=　@ABS（MIN（実質保証金　@－　@（建玉代金　@×　@第一水準保証金維持率　@／　@100），　@0））
                //「建玉代金　@×　@第一水準保証金維持率　@／　@100」の計算結果は小数点以下を切り上げとする。
                BigDecimal l_bdAmount = l_bdContractAmount.multiply(
                    l_bdFirstAdddepositDepositRate).divide(
                        new BigDecimal(100 + ""), 0, BigDecimal.ROUND_CEILING);
                log.exiting(STR_METHOD_NAME);
                return Math.abs(Math.min(l_bdRealMargin.subtract(l_bdAmount).doubleValue(), 0));
            }
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc第一水準追証決済必要額)<BR>
     * (calc第一水準追証決済必要額)<BR>
     * <BR>
     * 顧客属性の判定後、「第一水準追証決済必要額」を算出し、返却する。<BR>
     * <BR>
     * １）　@顧客属性の判定を行う。<BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合<BR>
     * 　@　@第一水準追証決済必要額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合<BR>
     * 　@　@２）以降の処理を行う。<BR>
     * <BR>
     * ２）　@第二水準追証の判定を行う。<BR>
     * 　@（a）第二水準追証未入金　@>　@0　@又は　@第二水準追証請求(2)　@>　@0　@又は<BR>
     * 　@　@　@第二水準追証請求(1)　@>　@0　@又は　@第二水準追証請求見込　@>　@0 の場合<BR>
     * 　@　@第一水準追証決済必要額　@=　@0を設定し、返却する。<BR>
     * <BR>
     * 　@（b）(a)以外の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・第二水準追証未入金　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(未入金)<BR>
     * 　@　@・第二水準追証請求(2)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求2)<BR>
     * 　@　@・第二水準追証請求(1)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求1)<BR>
     * 　@　@・第二水準追証請求見込　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(見込金額)<BR>
     * <BR>
     * ３）　@ゼロ判定を行う。<BR>
     * 　@（a）実質保証金　@＋　@第一水準追証金額　@==　@0の場合<BR>
     * 　@　@第一水準追証決済必要額　@=　@0 を設定し、返却する。<BR>
     * <BR>
     * 　@（b）実質保証金　@＋　@第一水準追証金額　@!=　@0の場合<BR>
     * 　@　@４）以降の処理を行う。 <BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・実質保証金　@　@　@　@　@　@ ・・・this.calc実質保証金（）<BR>
     * 　@　@・第一水準追証金額　@　@・・・this.calc第一水準追証金額（）<BR>
     * <BR>
     * ４）　@第一水準追証決済必要額を計算する。<BR>
     * 　@［計算式］<BR>
     * 　@第一水準追証決済必要額　@=　@建玉代金　@×　@第一水準追証金額　@／　@（実質保証金　@＋　@第一水準追証金額）<BR>
     * 　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ +  this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）<BR>
     * <BR>
     * ４）　@計算した第一水準追証決済必要額を返却する。<BR>
     * @@return double
     * @@roseuid 48DA2FB90063
     */
    protected double calcFirstAdddepositSettlement()
    {
        final String STR_METHOD_NAME = "calcFirstAdddepositSettlement()";
        log.entering(STR_METHOD_NAME);
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //第一水準追証決済必要額　@=　@0を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //　@・第二水準追証未入金　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(未入金)
            double l_dblSecondDepositNonPay = this.createSecondAdddepositNotClearInfo().secondDepositNonPay;
            //　@・第二水準追証請求(2)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求2)
            double l_dblSecondDeposit2 = this.createSecondAdddepositNotClearInfo().secondDeposit2;
            //　@・第二水準追証請求(1)　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(請求1)
            double l_dblSecondDeposit1 = this.createSecondAdddepositNotClearInfo().secondDeposit1;
            //　@・第二水準追証請求見込　@　@　@・・・this.create第二水準追証未解消情報（）.追証金額(見込金額)
            double l_dblSecondDepositExpect = this.createSecondAdddepositNotClearInfo().secondDepositExpect;

            //（a）第二水準追証未入金　@>　@0　@又は　@第二水準追証請求(2)　@>　@0　@又は
            //第二水準追証請求(1)　@>　@0　@又は　@第二水準追証請求見込　@>　@0 の場合
            //第一水準追証決済必要額　@=　@0を設定し、返却する。
            if (l_dblSecondDepositNonPay > 0
                || l_dblSecondDeposit2 > 0
                || l_dblSecondDeposit1 > 0
                || l_dblSecondDepositExpect > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //実質保証金　@　@　@　@　@・・・this.calc実質保証金（）
            double l_dblRealMargin = this.calcRealMargin();
            BigDecimal l_bdRealMargin = new BigDecimal("" + l_dblRealMargin);

            //第一水準追証金額　@　@・・・this.calc第一水準追証金額（）
            double l_dblFirstAdddepositAmount = this.calcFirstAdddepositAmount();
            BigDecimal l_bdFirstAdddepositAmount = new BigDecimal("" + l_dblFirstAdddepositAmount);
            //（a）実質保証金　@＋　@第一水準追証金額　@==　@0の場合
            //第一水準追証決済必要額　@=　@0 を設定し、返却する。
            BigDecimal l_bdAmount = l_bdFirstAdddepositAmount.add(l_bdRealMargin);
            if (GtlUtils.Double.isZero(l_bdAmount.doubleValue()))
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）
            //      + this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //建玉代金
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);

            //第一水準追証決済必要額を計算する。
            //［計算式］
            //第一水準追証決済必要額　@=　@建玉代金　@×　@第一水準追証金額　@／　@（実質保証金　@＋　@第一水準追証金額）
            //（計算結果の小数点以下を切り上げとする。）
            log.exiting(STR_METHOD_NAME);
            return l_bdContractAmount.multiply(
                    l_bdFirstAdddepositAmount).divide(
                        l_bdAmount, 0, BigDecimal.ROUND_CEILING).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc第二水準追証金額)<BR>
     * (calc第二水準追証金額) <BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証金額」を算出し、返却する。 <BR>
     * <BR>
     * １）　@顧客属性の判定を行う。 <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 <BR>
     * 　@　@第二水準追証金額　@=　@0 を設定し、返却する。 <BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 <BR>
     * 　@　@２）以降の処理を行う。  <BR>
     * <BR>
     * ２）　@保証金預託率の判定を行う。 <BR>
     * 　@（a）保証金預託率　@>　@第二水準保証金維持率 の場合 <BR>
     * 　@　@第二水準追証金額　@=　@0 を設定し、返却する。 <BR>
     * <BR>
     * 　@（b）(a)以外の場合<BR>
     * 　@　@３）以降の処理を行う。<BR>
     * <BR>
     * 　@※　@判定に使用する各値の取得方法@ <BR>
     * 　@　@・保証金預託率　@　@　@　@　@　@　@　@・・・this.calc保証金率（）<BR>
     * 　@　@・第二水準保証金維持率　@　@・・・this.get第二水準追証保証金維持率（）<BR>
     * <BR>
     * ３）　@第二水準追証金額を計算する。<BR>
     * 　@［計算式］ <BR>
     * 　@第二水準追証金額　@=　@ABS（MIN（実質保証金　@－　@建玉代金　@×　@第二水準保証金戻し維持率　@／　@100，　@0））<BR>
     * 　@※　@「建玉代金　@×　@第二水準保証金戻し維持率　@／　@100」の計算結果は小数点以下を切り上げとする。<BR> 
     * <BR>
     * 　@※　@計算式に使用する各値の取得方法@ <BR>
     * 　@　@・実質保証金　@　@　@　@　@・・・this.calc実質保証金（）<BR>
     * 　@　@・建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ +  this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）<BR>
     * 　@　@・第二水準保証金戻し維持率　@　@　@・・・this.get第二水準追証保証金戻し維持率（）<BR>
     * <BR>
     * ４）　@計算した第二水準追証金額を返却する。<BR>
     * @@return double
     * @@roseuid 48DAD72501DD
     */
    protected double calcSecondAdddepositAmount()
    {
        final String STR_METHOD_NAME = "calcSecondAdddepositAmount()";
        log.entering(STR_METHOD_NAME);
        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //第二水準追証決済必要額　@=　@0 を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            //　@　@・保証金預託率　@　@　@　@　@　@　@　@・・・this.calc保証金率（）
            double l_dblMarginDepositRate = this.calcMarginDepositRate();
            // 　@・第二水準保証金維持率　@　@・・・this.get第二水準追証保証金維持率（）
            double l_dblSecondAdddepositDepositRate = this.getSecondAdddepositDepositRate();

            //（a）保証金預託率　@>　@第二水準保証金維持率 の場合
            //第二水準追証金額　@=　@0 を設定し、返却する。
            if (l_dblMarginDepositRate > l_dblSecondAdddepositDepositRate)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //実質保証金　@　@　@　@　@・・・this.calc実質保証金（）
            double l_dblRealMargin = this.calcRealMargin();
            BigDecimal l_bdRealMargin = new BigDecimal("" + l_dblRealMargin);
            //建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）
            //      + this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //建玉代金
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);
            //第二水準保証金戻し維持率　@　@　@ ・・・this.get第二水準追証保証金戻し維持率（）
            double l_dblSecondAdddepositDepositBackRate = this.getSecondAdddepositDepositBackRate();
            BigDecimal l_bdSecondAdddepositDepositBackRate =
                new BigDecimal(l_dblSecondAdddepositDepositBackRate + "");

            //第二水準追証金額を計算する。
            //［計算式］
            //第二水準追証金額　@=　@ABS（MIN（実質保証金　@－　@建玉代金　@×　@第二水準保証金戻し維持率　@／　@100，　@0））
            //「建玉代金　@×　@第二水準保証金戻し維持率　@／　@100」の計算結果は小数点以下を切り上げとする。 
            BigDecimal l_bdAmount = l_bdContractAmount.multiply(
                l_bdSecondAdddepositDepositBackRate).divide(
                    new BigDecimal(100 + ""), 0, BigDecimal.ROUND_CEILING);

            log.exiting(STR_METHOD_NAME);
            return Math.abs(Math.min(l_bdRealMargin.subtract(l_bdAmount).doubleValue(), 0));
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }

    /**
     * (calc第二水準追証決済必要額)<BR>
     * (calc第二水準追証決済必要額) <BR>
     * <BR>
     * 顧客属性の判定後、「第二水準追証決済必要額」を算出し、返却する。 <BR>
     * <BR>
     * １）　@顧客属性の判定を行う。 <BR>
     * 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 <BR>
     * 　@　@第二水準追証決済必要額　@=　@0 を設定し、返却する。 <BR>
     * <BR>
     * 　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 <BR>
     * 　@　@２）以降の処理を行う。  <BR>
     * <BR>
     * ２）　@ゼロ判定を行う。<BR>
     * 　@（a）実質保証金　@＋　@第二水準追証金額　@==　@0の場合<BR>
     * 　@　@第二水準追証決済必要額　@=　@0 を設定し、返却する。<BR>
     * <BR>
     * 　@（b）実質保証金　@＋　@第二水準追証金額　@!=　@0の場合<BR>
     * 　@　@３）以降の処理を行う。 <BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・実質保証金　@　@　@・・・this.calc実質保証金（）<BR>
     * 　@　@・第二水準追証金額　@　@　@・・・this.calc第二水準追証金額（）<BR>
     * <BR>
     * ３）　@第二水準追証決済必要額を計算する。 <BR>
     * 　@［計算式］ <BR>
     * 　@第二水準追証決済必要額　@=　@建玉代金　@×　@第二水準追証金額　@／　@（実質保証金　@＋　@第二水準追証金額）<BR>
     * 　@（計算結果の小数点以下を切り上げとする。）<BR>
     * <BR>
     * 　@※　@各値の取得方法@<BR>
     * 　@　@・建玉代金　@　@　@　@　@　@　@ ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@ +  this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）<BR>
     * <BR>
     * ４）　@計算した第二水準追証決済必要額を返却する。<BR>
     * @@return double
     * @@roseuid 48DADE880267
     */
    protected double calcSecondAdddepositSettlement()
    {
        final String STR_METHOD_NAME = "calcSecondAdddepositSettlement()";
        log.entering(STR_METHOD_NAME);

        //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
        //第二水準追証決済必要額　@=　@0 を設定し、返却する。
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }

        //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
        if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(this.marginEquityJudgeFlag))
        {

            //実質保証金　@　@　@・・・this.calc実質保証金（）
            double l_dblRealMargin = this.calcRealMargin();
            //第二水準追証金額　@　@　@・・・this.calc第二水準追証金額（）
            double l_dblSecondAdddepositAmount = this.calcSecondAdddepositAmount();

            BigDecimal l_bdRealMargin = new BigDecimal(l_dblRealMargin + "");
            BigDecimal l_bdSecondAdddepositAmount = new BigDecimal(l_dblSecondAdddepositAmount + "");

            BigDecimal l_bdValue = l_bdRealMargin.add(l_bdSecondAdddepositAmount);

            //（a）実質保証金　@＋　@第二水準追証金額　@==　@0の場合
            if (GtlUtils.Double.isZero(l_bdValue.doubleValue()))
            {
                //第二水準追証決済必要額　@=　@0 を設定し、返却する。
                log.exiting(STR_METHOD_NAME);
                return 0;
            }

            //建玉代金   ・・・this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get未決済建玉代金( T + 0 )（）
            //        + this.資産余力情報<信用顧客>.get余力計算結果Params<信用顧客>.get日計り返済・現引現渡建玉代金( T + 0 )（）
            BigDecimal l_bdContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getContractAmount0() + "");

            BigDecimal l_bdDayRepayContractAmount0 =
                new BigDecimal(this.tpCalcMargin.getCalcResultMargin().getDayRepayContractAmount0() + "");

            //建玉代金
            BigDecimal l_bdContractAmount = l_bdContractAmount0.add(l_bdDayRepayContractAmount0);
            //第二水準追証決済必要額を計算する。
            //［計算式］
            //第二水準追証決済必要額　@=　@建玉代金　@×　@第二水準追証金額　@／　@（実質保証金　@＋　@第二水準追証金額）
            //（計算結果の小数点以下を切り上げとする。）
            log.exiting(STR_METHOD_NAME);
            return l_bdContractAmount.multiply(
                        l_bdSecondAdddepositAmount).divide(
                            l_bdValue, 0, BigDecimal.ROUND_CEILING).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
}
@
