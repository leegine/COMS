head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービスImpl(WEB3PvInfoPrivateInformationDetailServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/22 李丁銀(中訊) 作成
Revesion History : 2005/10/07 譚漢江(中訊) 仕様変更No.058
Revesion History : 2006/5/22 凌建平(中訊) 仕様変更No.063修正
Revesion History : 2006/09/12 張騰宇(中訊) 仕様変更モデル070,071
Revesion History : 2006/09/27 張騰宇(中訊) 仕様変更モデル072
Revesion History : 2007/02/26 関博(中訊) 仕様変更モデル073
Revesion History : 2007/03/07 関博(中訊) 仕様変更モデル074
Revesion History : 2007/03/16 関博(中訊) 仕様変更モデル076
Revision History : 2008/10/07 柴双紅(中訊) 仕様変更モデル108、モデル112
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.define.WEB3PvInfoTPBizDateCheckNumDef;
import webbroker3.pvinfo.message.WEB3PvInfoAdvanceUnit;
import webbroker3.pvinfo.message.WEB3PvInfoCashoutStopUnit;
import webbroker3.pvinfo.message.WEB3PvInfoCommissionCampaignUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteRequest;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse;
import webbroker3.pvinfo.message.WEB3PvInfoFirstAdditionalInfo;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailRequest;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse;
import webbroker3.pvinfo.message.WEB3PvInfoSecondAdditionalInfo;
import webbroker3.pvinfo.message.WEB3PvInfoSettleContractUnit;
import webbroker3.pvinfo.message.WEB3PvInfoShortfallGenerationInfo;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationDetailService;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPAdddepositInfo;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービスImpl)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービス実装クラス<BR>
 * @@author 王亞洲(中訊)
 */
public class WEB3PvInfoPrivateInformationDetailServiceImpl extends WEB3ClientRequestService implements WEB3PvInfoPrivateInformationDetailService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationDetailServiceImpl.class);

    /**
     * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下の処理を呼び分ける。<BR>
     * ○ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細リクエストの場合<BR>
     * 　@・get詳細画面()メソッドをコールする。<BR>
     * <BR>
     * ○ダイレクト指定メッセージ削除リクエストの場合<BR>
     * 　@・deleteダイレクト指定メッセージ()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41479D0D00A8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if(l_request instanceof WEB3PvInfoPrivateInformationDetailRequest)
        {
            l_response = this.getDetailedScreen((WEB3PvInfoPrivateInformationDetailRequest)l_request);
        }
        else if(l_request instanceof WEB3PvInfoDirectMessageDeleteRequest)
        {
            l_response = this.deleteDirectMessage((WEB3PvInfoDirectMessageDeleteRequest)l_request);
        }
        else
        {
            String l_strErrorInfo =
                "パラメータの類型が不正、該当するWEB3PvInfoPrivateInformationDetailRequest," +
                "WEB3PvInfoDirectMessageDeleteRequest類型。";
            log.error(l_strErrorInfo);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get詳細画面)<BR>
     * 詳細画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービス)get詳細画面」参照<BR>
     * ========================================================<BR>
     * get表示内容Params(表示内容ID:long)<BR>
     * <BR>
     * 戻り値がnullの場合は、<BR>
     * 「表示メッセージが存在しません」<BR>
     * の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01039<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse
     * @@roseuid 41479EE201A2
     */
    protected WEB3PvInfoPrivateInformationDetailResponse getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate()
        l_request.validate();
        log.debug("validate()を執行します");

        //1.2.get補助口座(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        log.debug("get補助口座(SubAccountTypeEnum)を執行します");

        //1.3.getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        log.debug("getMainAccount()を執行します");

        //1.4.validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        log.debug("validate注文受付可能()を執行します");

        //1.5.get表示内容Params(long)
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsId);
        log.debug("表示内容ID: " + l_lngDisplayContentsId);
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        DisplayContentsParams l_displayContentsParams =
            l_dataManager.getDisplayContentsParams(l_lngDisplayContentsId);
        log.debug("get表示内容Params(long)を執行します");
        log.debug("表示内容Params: " + l_displayContentsParams);

        if (l_displayContentsParams == null)
        {
            log.debug("表示内容Params == nullの場合");
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01039.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01039,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.メッセージ (*)分岐フロー
        WEB3PvInfoAdvanceUnit[] l_AdvanceUnits = null;
        if (WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_displayContentsParams.getConditionNo())
                ||WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_displayContentsParams.getConditionNo())
                ||WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_displayContentsParams.getConditionNo()))
        {
            l_AdvanceUnits = this.createAdvanceUnitList((WEB3GentradeSubAccount)l_subAccount);
        }

        //1.7.2 決済期限間近の建玉情報 ArrayList()
        List l_lisSettleContractUnits = new ArrayList();

        //1.7メッセージ (*)分岐フロー
        boolean l_blnIsBeforeAWeek = WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(l_displayContentsParams.condition_no);
        log.debug("l_blnIsBeforeAWeek =" + l_blnIsBeforeAWeek);
        if (l_blnIsBeforeAWeek)
        {
            //1.7.1 get決済期限間近建玉一覧(顧客, boolean)
            List l_lisSettleContracts = l_dataManager.getSettleContractList(l_mainAccount, true);
            log.debug("get決済期限間近建玉一覧(顧客, boolean)を執行します");

            if (l_lisSettleContracts == null)
            {
                log.debug("決済期限間近建玉一覧 == nullの場合");
                String l_strErrorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80006.getErrorMessage();
                log.error(STR_METHOD_NAME + l_strErrorInfo);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorInfo);
            }

            //1.7.3 get決済期限間近建玉一覧()の戻り値(=EqTypeContractParams)の数分Loop処理
            int l_intSettleContractCnt = l_lisSettleContracts.size();
            for (int i = 0; i < l_intSettleContractCnt; i++)
            {
                EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisSettleContracts.get(i);

                //1.7.3.1 get株式銘柄Params(long)
                long l_lngProductId = l_eqtypeContractRow.getProductId();
                log.debug("銘柄ID: " + l_lngProductId);
                EqtypeProductParams l_eqtypeParams = l_dataManager.getEqtypeProductParams(l_lngProductId);
                log.debug("get株式銘柄Params(long)を執行します");

                //1.7.3.2 決済期限間近の建玉情報()
                WEB3PvInfoSettleContractUnit l_pvInfoSettleContractUnit = new WEB3PvInfoSettleContractUnit();

                //1.7.3.3メッセージ プロパティセット
                Timestamp l_tsCloseDate = l_eqtypeContractRow.getCloseDate();
                WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsCloseDate);
                Timestamp l_tsBeforeCloseDate = l_gentradeBizDate.roll(-1);

                l_pvInfoSettleContractUnit.beforeBizDate = l_tsBeforeCloseDate;
                l_pvInfoSettleContractUnit.productCode = l_eqtypeParams.getProductCode();
                l_pvInfoSettleContractUnit.productName = l_eqtypeParams.getStandardName();

                log.debug("期日の1営業日前: " + l_pvInfoSettleContractUnit.beforeBizDate);
                log.debug("銘柄コード: " + l_pvInfoSettleContractUnit.productCode);
                log.debug("銘柄名: " + l_pvInfoSettleContractUnit.productName);

                //1.7.3.4 add(決済期限間近の建玉情報 : Object)
                l_lisSettleContractUnits.add(l_pvInfoSettleContractUnit);
                log.debug("add(決済期限間近の建玉情報 : Object)を執行します");
            }
        }

        //1.8 toArray()
        WEB3PvInfoSettleContractUnit[] l_arrlisSettleContractUnits = new WEB3PvInfoSettleContractUnit[l_lisSettleContractUnits.size()];
        l_lisSettleContractUnits.toArray(l_arrlisSettleContractUnits);
        log.debug("toArray()を執行します");
        
        //1.9(*)分岐フロー
        //表示条件が"一部出金停止"の場合
        //(表示内容Params.表示条件番号 == "1049：　@一部出金停止")
        //以下の処理を実施
        boolean l_blnPart = WEB3PvInfoConditionDef.PART_PAYMENT_STOP.equals(l_displayContentsParams.condition_no);
       
        
        WEB3PvInfoCashoutStopUnit l_pvInfoCashoutStopUnit = null;
        
        if (l_blnPart)
        {
            //1.9.1get担保不足顧客データParams(顧客, String)
            SecurityShortageAccountParams l_securityShortageAccountParams = 
                l_dataManager.getSecurityShortageAccountParams(l_mainAccount,WEB3PaymentStopDivDef.PART);
           
            //1.9.2出金停止情報( )
            l_pvInfoCashoutStopUnit = new WEB3PvInfoCashoutStopUnit();
            
            //1.9.3 プロパティセット
            if (l_securityShortageAccountParams == null)
            {
                l_pvInfoCashoutStopUnit = null;
            }
            else 
            {
                l_pvInfoCashoutStopUnit.cashoutStopDate = l_securityShortageAccountParams.getProcDate();
                l_pvInfoCashoutStopUnit.cashoutStopAmount = l_securityShortageAccountParams.getPaymentStopAmount();
            }
        }

        //1.10 (*)分岐フロー
        // (*)分岐フロー
        // 表示条件が"手数料割引キャンペーン"の場合
        // (表示内容Params.表示条件番号 == "1051：　@手数料割引キャンペーン")
        // 以下の処理を実施する。
        boolean l_blnIsCommission = WEB3PvInfoConditionDef.COMMISSION_DISCOUNT_CAMPAIGN.equals(l_displayContentsParams.condition_no);

        List l_lisCommissionCampaignUnit = new ArrayList();
        if (l_blnIsCommission)
        {
            // get割引キャンペーン顧客Params(顧客)
            CommCampaignAccHistoryParams l_commCampaignAccHistoryParams[] = 
                l_dataManager.getCommCampaignAccHistoryParams(l_mainAccount);

            // プロパティセット
            if (l_commCampaignAccHistoryParams != null)
            {
                for (int i = 0; i < l_commCampaignAccHistoryParams.length; i++)
                {
                    // 手数料割引キャンペーン情報( )
                    WEB3PvInfoCommissionCampaignUnit l_pvInfoCommissionCampaignUnit = new WEB3PvInfoCommissionCampaignUnit();
                    // get商品コード(long)
                    String[] l_strCommProductCodes =
                        l_dataManager.getCommProductCodes(l_commCampaignAccHistoryParams[i].getCampaignId());

                    BigDecimal l_bdAccountChargeRatio = new BigDecimal("" + l_commCampaignAccHistoryParams[i].getAccountChargeRatio());
                    BigDecimal l_bdRate = new BigDecimal("" + 100D);
                    l_pvInfoCommissionCampaignUnit.campaignName = l_commCampaignAccHistoryParams[i].getCommCampaignName();
                    l_pvInfoCommissionCampaignUnit.commodityCodeList = l_strCommProductCodes;
                    l_pvInfoCommissionCampaignUnit.discountRate = WEB3StringTypeUtility.formatNumber(l_bdRate.subtract(l_bdAccountChargeRatio).doubleValue());
                    l_pvInfoCommissionCampaignUnit.applyStartDate = l_commCampaignAccHistoryParams[i].getAppliStartDate();
                    l_pvInfoCommissionCampaignUnit.applyEndDate = l_commCampaignAccHistoryParams[i].getAppliEndDate();
                    l_lisCommissionCampaignUnit.add(l_pvInfoCommissionCampaignUnit);
                }
            }
        }

        String l_strConditionNo = l_displayContentsParams.getConditionNo();
        //(*)分岐フロー
        //表示条件が"不足金発生&信用口座未開設"もしくは"不足金発生信用口座開設の場合"
        //(表示内容Params.表示条件番号 == "1054：　@不足金発生＆信用口座未開設" or
        // 表示内容Params.表示条件番号 == "1055：　@不足金発生＆信用口座開設")
        //以下の処理を実施する。
        WEB3PvInfoShortfallGenerationInfo l_shortfallGenerationInfo = null;
        if (WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE.equals(l_strConditionNo)
            || WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN.equals(l_strConditionNo))
        {
            //不足金発生情報
            l_shortfallGenerationInfo = new WEB3PvInfoShortfallGenerationInfo();

            //get余力取引停止区分(顧客 : 顧客)
            String l_strTPTradingStop = l_dataManager.getTPTradingStop(l_mainAccount);

            //get不足金発生情報(顧客 : 顧客)
            WEB3TPShortfallOccurInfo l_tPShortfallGenerationInfo =
                l_dataManager.getShortfallGenerationInfo(l_mainAccount);

            //プロパティセット
            //不足金発生表示情報オブジェクトにプロパティをセットする。
            //取引停止区分 = get余力取引停止区分()の戻り値
            l_shortfallGenerationInfo.tradeStopDiv = l_strTPTradingStop;

            //保証金自動振替後判定フラグ = get不足金発生情報()の戻り値.保証金自動振替後判定フラグ
            l_shortfallGenerationInfo.autoTransferAfterJudgeFlag =
                l_tPShortfallGenerationInfo.depositAutoTransferDivFlag;

            //期日(T+0) = get不足金発生情報()の戻り値.期日(T+0)
            l_shortfallGenerationInfo.closeDate0 =
                l_tPShortfallGenerationInfo.closeDate0;

            //期日(T+1) = get不足金発生情報()の戻り値.期日(T+1)
            l_shortfallGenerationInfo.closeDate1 =
                l_tPShortfallGenerationInfo.closeDate1;

            //期日(T+2) = get不足金発生情報()の戻り値.期日(T+2)
            l_shortfallGenerationInfo.closeDate2 =
                l_tPShortfallGenerationInfo.closeDate2;

            //期日(T+3) = get不足金発生情報()の戻り値.期日(T+3)
            l_shortfallGenerationInfo.closeDate3 =
                l_tPShortfallGenerationInfo.closeDate3;

            //期日(T+4) = get不足金発生情報()の戻り値.期日(T+4)
            l_shortfallGenerationInfo.closeDate4 =
                l_tPShortfallGenerationInfo.closeDate4;

            //期日(T+5) = get不足金発生情報()の戻り値.期日(T+5)
            l_shortfallGenerationInfo.closeDate5 =
                l_tPShortfallGenerationInfo.closeDate5;

            //必要入金額(T+0) = get不足金発生情報()の戻り値.必要入金額(T+0)
            l_shortfallGenerationInfo.requiredPayAmt0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt0);

            //必要入金額(T+1) = get不足金発生情報()の戻り値.必要入金額(T+1)
            l_shortfallGenerationInfo.requiredPayAmt1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt1);

            //必要入金額(T+2) = get不足金発生情報()の戻り値.必要入金額(T+2)
            l_shortfallGenerationInfo.requiredPayAmt2 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt2);

            //必要入金額(T+3) = get不足金発生情報()の戻り値.必要入金額(T+3)
            l_shortfallGenerationInfo.requiredPayAmt3 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt3);

            //必要入金額(T+4) = get不足金発生情報()の戻り値.必要入金額(T+4)
            l_shortfallGenerationInfo.requiredPayAmt4 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt4);

            //必要入金額(T+5) = get不足金発生情報()の戻り値.必要入金額(T+5)
            l_shortfallGenerationInfo.requiredPayAmt5 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt5);

            //精算額(T+0) = get不足金発生情報()の戻り値.精算額(T+0)
            l_shortfallGenerationInfo.adjustedAmt0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.adjustedAmt0);

            //精算額(T+1) = get不足金発生情報()の戻り値.精算額(T+1)
            l_shortfallGenerationInfo.adjustedAmt1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.adjustedAmt1);

            //日計り拘束金(T+0) = get不足金発生情報()の戻り値.日計り拘束金(T+0)
            l_shortfallGenerationInfo.dayTradeRestraint0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.dayTradeRestraint0);

            //日計り拘束金(T+1) = get不足金発生情報()の戻り値.日計り拘束金(T+1)
            l_shortfallGenerationInfo.dayTradeRestraint1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.dayTradeRestraint1);

            //保証金からの振替額(T+0) = get不足金発生情報()の戻り値.保証金からの振替額(T+0)
            l_shortfallGenerationInfo.transferFromMarginDeposit0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.transferFromMarginDeposit0);

            //保証金からの振替額(T+1) = get不足金発生情報()の戻り値.保証金からの振替額(T+1)
            l_shortfallGenerationInfo.transferFromMarginDeposit1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.transferFromMarginDeposit1);
        }

        //(*)分岐フロー
        //表示条件が"第一水準追証発生"の場合
        //(表示内容Params.表示条件番号 == "1056：　@第一水準追証発生")
        //以下の処理を実施する。
        WEB3PvInfoFirstAdditionalInfo l_firstAdditionalInfo = null;
        if (WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo))
        {
            //get追証未入金区分(顧客 : 顧客)
            String l_strAdditionalDepositStop =
                l_dataManager.getAdditionalDepositStop(l_mainAccount);

            //get追証発生情報(顧客 : 顧客)
            WEB3TPAdddepositGenerationInfo l_tPAdddepositGenerationInfo =
                l_dataManager.getAdddepositGenerationInfo(l_mainAccount);

            //(*)分岐フロー
            //get追証発生情報()の戻り値.get追証情報　@!= null かつ
            //get追証発生情報()の戻り値.get追証情報  itanceof  入金請求管理<第一水準追証情報>型
            //である場合
            WEB3TPAdddepositInfo l_adddepositInfo =
                l_tPAdddepositGenerationInfo.getAdddepositInfo();
            if (l_adddepositInfo != null
                && l_adddepositInfo instanceof WEB3TPFirstAdddepositInfo)
            {
                WEB3TPFirstAdddepositInfo l_firstAdddepositInfo =
                    (WEB3TPFirstAdddepositInfo)l_adddepositInfo;
                //第一水準追証発生表示情報( )
                l_firstAdditionalInfo = new WEB3PvInfoFirstAdditionalInfo();

                //プロパティセット
                //第一水準追証表示情報オブジェクトにプロパティをセットする。
                //※以下の追証情報とは、get追証発生情報()の戻り値.get追証情報の戻り値を
                //　@入金請求管理<第一水準追証情報>型にキャストしたオブジェクト
                //取引停止区分 = get追証未入金区分()の戻り値
                l_firstAdditionalInfo.firstTradeStopDiv = l_strAdditionalDepositStop;

                //保証金自動振替後判定フラグ = get追証発生情報()の戻り値.get保証金自動振替後判定フラグ
                l_firstAdditionalInfo.firstAutoTransferAfterJudgeFlag =
                    l_tPAdddepositGenerationInfo.getDepositAutoTransferDivFlag();

                //経過日数 = 追証情報.経過日数
                l_firstAdditionalInfo.firstDepositPassDay =
                    l_firstAdddepositInfo.firstDepositPassDay + "";

                //有効経過日数 = 追証情報.有効経過日数
                l_firstAdditionalInfo.firstDepositPassDayValid =
                    l_firstAdddepositInfo.firstDepositPassDayValid + "";

                //発生日 = 追証情報.発生日
                l_firstAdditionalInfo.firstDepositOccurredDate =
                    l_firstAdddepositInfo.firstDepositOccurredDate;

                //保証金率 = 追証情報.保証金率
                l_firstAdditionalInfo.firstMarginDepositRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstMarginDepositRate);

                //保証金維持率 = 追証情報.保証金維持率
                l_firstAdditionalInfo.firstDepositRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstDepositRate);

                //追証金額 = 追証情報.追証金額
                l_firstAdditionalInfo.firstDepositAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstDepositAmount);

                //追証決済必要額 = 追証情報.追証決済必要額
                l_firstAdditionalInfo.firstSettlement =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstSettlement);

                //保証金増減 = 追証情報.保証金増減
                l_firstAdditionalInfo.firstMarginDepositInDe =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstMarginDepositInDe);

                //保証金増減(見込金額) = 追証情報.保証金増減(見込金額)
                l_firstAdditionalInfo.firstMarginDepositInDeExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstMarginDepositInDeExpect);

                //決済済建玉 = 追証情報.決済済建玉
                l_firstAdditionalInfo.firstSettledContract =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstSettledContract);

                //未解消金額 = 追証情報.未解消金額
                l_firstAdditionalInfo.firstUncancelAmt =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstUncancelAmt);

                //未解消決済必要額 = 追証情報.未解消決済必要額
                l_firstAdditionalInfo.firstUncancelSettleRequiredAmt =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstUncancelSettleRequiredAmt);
            }
        }

        //(*)分岐フロー
        //表示条件が"第二水準追証発生"の場合
        //(表示内容Params.表示条件番号 == "1057：　@第二水準追証発生")
        //以下の処理を実施する。
        WEB3PvInfoSecondAdditionalInfo l_secondAdditionalInfo = null;
        if (WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo))
        {
            //get追証未入金区分(顧客 : 顧客)
            String l_strAdditionalDepositStop =
                l_dataManager.getAdditionalDepositStop(l_mainAccount);

            //get追証発生情報(顧客 : 顧客)
            WEB3TPAdddepositGenerationInfo l_tPAdddepositGenerationInfo =
                l_dataManager.getAdddepositGenerationInfo(l_mainAccount);

            //(*)分岐フロー
            //get追証発生情報()の戻り値.get追証情報　@!= null かつ
            //get追証発生情報()の戻り値.get追証情報  itanceof  入金請求管理<第二水準追証情報>型
            //である場合
            WEB3TPAdddepositInfo l_adddepositInfo = l_tPAdddepositGenerationInfo.getAdddepositInfo();
            if (l_adddepositInfo != null
                && l_adddepositInfo instanceof WEB3TPSecondAdddepositInfo)
            {
                WEB3TPSecondAdddepositInfo l_secondAdddepositInfo =
                    (WEB3TPSecondAdddepositInfo)l_adddepositInfo;
                //第二水準追証発生表示情報( )
                l_secondAdditionalInfo = new WEB3PvInfoSecondAdditionalInfo();

                //プロパティセット
                //第二水準追証表示情報オブジェクトにプロパティをセットする。
                //※以下の追証情報とは、get追証発生情報()の戻り値.get追証情報の戻り値を
                //　@入金請求管理<第二水準追証情報>型にキャストしたオブジェクト
                //取引停止区分 = get追証未入金区分()の戻り値
                l_secondAdditionalInfo.secondTradeStopDiv = l_strAdditionalDepositStop;

                //保証金自動振替後判定フラグ = get追証発生情報()の戻り値.get保証金自動振替後判定フラグ
                l_secondAdditionalInfo.secondAutoTransferAfterJudgeFlag =
                    l_tPAdddepositGenerationInfo.getDepositAutoTransferDivFlag();

                //期日(請求2) = 追証情報.期日(請求2)
                l_secondAdditionalInfo.secondCloseDate2 =
                    l_secondAdddepositInfo.secondCloseDate2;

                //期日(請求1) = 追証情報.期日(請求1)
                l_secondAdditionalInfo.secondCloseDate1 =
                    l_secondAdddepositInfo.secondCloseDate1;

                //期日(請求見込) = 追証情報.期日(請求見込)
                l_secondAdditionalInfo.secondCloseDateExpect =
                    l_secondAdddepositInfo.secondCloseDateExpect;

                //発生日(請求2) = 追証情報.発生日(請求2)
                l_secondAdditionalInfo.secondDepositOccurredDate2 =
                    l_secondAdddepositInfo.secondDepositOccurredDate2;

                //発生日(請求1) = 追証情報.発生日(請求1)
                l_secondAdditionalInfo.secondDepositOccurredDate1 =
                    l_secondAdddepositInfo.secondDepositOccurredDate1;

                //発生日(請求見込) = 追証情報.発生日(請求見込)
                l_secondAdditionalInfo.secondDepositOccurredDateExpect =
                    l_secondAdddepositInfo.secondDepositOccurredDateExpect;

                //保証金維持率 = 追証情報.保証金維持率
                l_secondAdditionalInfo.secondDepositRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDepositRate);

                //保証金戻し維持率 = 追証情報.保証金戻し維持率
                l_secondAdditionalInfo.secondDepositBackRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDepositBackRate);

                //保証金率（請求2） = 追証情報.保証金率（請求2）
                l_secondAdditionalInfo.secondMarginDepositRate2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositRate2);

                //保証金率（請求1） = 追証情報.保証金率（請求1）
                l_secondAdditionalInfo.secondMarginDepositRate1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositRate1);

                //保証金率（請求見込） = 追証情報.保証金率（請求見込）
                l_secondAdditionalInfo.secondMarginDepositRateExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositRateExpect);

                //追証金額（未入金） = 追証情報.追証金額（未入金）
                l_secondAdditionalInfo.secondDepositNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDepositNonPay);

                //追証金額（請求2） = 追証情報.追証金額（請求2）
                l_secondAdditionalInfo.secondDeposit2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDeposit2);

                //追証金額（請求1） = 追証情報.追証金額（請求1）
                l_secondAdditionalInfo.secondDeposit1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDeposit1);

                //追証決済必要額（未入金） = 追証情報.追証決済必要額（未入金）
                l_secondAdditionalInfo.secondSettlementNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettlementNonPay);

                //追証決済必要額（請求2） = 追証情報.追証決済必要額（請求2）
                l_secondAdditionalInfo.secondSettlement2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettlement2);

                //追証決済必要額（請求1） = 追証情報.追証決済必要額（請求1）
                l_secondAdditionalInfo.secondSettlement1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettlement1);

                //保証金増減 = 追証情報.保証金増減
                l_secondAdditionalInfo.secondMarginDepositInDe =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositInDe);

                //保証金増減（見込金額） = 追証情報.保証金増減（見込金額）
                l_secondAdditionalInfo.secondMarginDepositInDeExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositInDeExpect);

                //決済済建玉 = 追証情報.決済済建玉
                l_secondAdditionalInfo.secondSettledContract =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettledContract);

                //未解消金額（未入金） = 追証情報.未解消金額（未入金）
                l_secondAdditionalInfo.secondUncancelAmtNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmtNonPay);

                //未解消金額（請求2） = 追証情報.未解消金額（請求2）
                l_secondAdditionalInfo.secondUncancelAmt2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmt2);

                //未解消金額（請求1） = 追証情報.未解消金額（請求1）
                l_secondAdditionalInfo.secondUncancelAmt1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmt1);

                //未解消金額（請求見込） = 追証情報.未解消金額（請求見込）
                l_secondAdditionalInfo.secondUncancelAmtExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmtExpect);

                //未解消決済必要額（未入金） = 追証情報.未解消決済必要額（未入金）
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay);

                //未解消決済必要額（請求2） = 追証情報.未解消決済必要額（請求2）
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmt2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2);

                //未解消決済必要額（請求1） = 追証情報.未解消決済必要額（請求1）
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmt1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1);

                //未解消決済必要額（請求見込） = 追証情報.未解消決済必要額（請求見込）
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect);
            }
        }

        //1.10 メッセージ (*)分岐フロー
		//  (*)分岐フロー
		//  顧客入力の場合(this.get代理入力者()==null)
		//  かつ、
		//  未読既読管理対象メッセージの場合
		//  (表示内容Params.表示条件番号 == ("0000：ダイレクト指定" or
		//  "1001：入金請求発生＆信用口座開設" or "1002：入金請求発生＆信用口座未開設" or
		//  "1007：決済期限間近(一週間前)の建玉保有" or "1003：立替金発生" or "1005：証拠金不足" or
		//  "1041：20％割れ1日＆30％割れ5日以下" or　@"1042：20％割れ1日＆30％割れ6日" or
		//  "1043：20％割れ2日＆30％割れ6日以下" or　@"1044：20％割れ3日以上" or
		//  "1045：30％割れ2～4日" or "1046：30％割れ5日" or "1047：30％割れ6日" or "1048：30％割れ7日以上" or
        //  "1054：不足金発生＆信用口座未開設" or "1055：不足金発生＆信用口座開設" or
        //  "1056：第一水準追証発生" or "1057：第二水準追証発生")
		//  以下の処理を実施する。
        if ((this.getTrader() == null) 
            && (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_5DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_6DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE.equals(
                l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN.equals(
                l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR.equals(
                l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR.equals(
                l_displayContentsParams.condition_no)))
        {
            log.debug("顧客入力の場合かつ未読既読管理対象メッセージの場合");
            //1.10.1 get閲覧履歴Params(顧客, long)
            BrowseHistoryParams l_browseHistoryParams =
                l_dataManager.getBrowseHistoryParams(l_mainAccount, l_lngDisplayContentsId);
            log.debug("get閲覧履歴Params(顧客, long)を執行します");

            //1.10.2 メッセージ (*)分岐フロー
            if (l_browseHistoryParams == null)
            {
                log.debug("閲覧履歴データを存在しない場合");
                //1.10.2.1 insert閲覧履歴(String, String, String, long, boolean)
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                String l_strAccountCode = l_mainAccount.getAccountCode().substring(0, 6);
                log.debug("証券会社コード: " + l_strInstitutionCode);
                log.debug("部店コード: " + l_strBranchCode);
                log.debug("顧客コード: " + l_strAccountCode);
                log.debug("表示内容ID: " + l_lngDisplayContentsId);
                log.debug("is既読: " + true);


                //is既読：　@true(既読データ)
                l_dataManager.insertBrowseHistory(l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_lngDisplayContentsId, true);
                log.debug("insert閲覧履歴(String, String, String, long, boolean)を執行します");
            }
            //1.10.3 (l_browseHistoryParams != null)
            else
            {
                log.debug("閲覧履歴データを存在する場合");
                //1.10.3.1  clone閲覧履歴Params(閲覧履歴Params)
                BrowseHistoryParams l_cloneBrowseHistoryParams = l_dataManager.cloneBrowseHistoryParams(l_browseHistoryParams);
                log.debug("clone閲覧履歴Params(閲覧履歴Params)を執行します");

                //1.10.3.2 メッセージ プロパティセット
                l_cloneBrowseHistoryParams.read_flag = WEB3PvInfoReadFlagDef.READ_YES;
                //ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻
                Timestamp l_tsCurrent = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                l_cloneBrowseHistoryParams.setLastReadTimestamp(l_tsCurrent);
                l_cloneBrowseHistoryParams.last_updated_timestamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

                //1.10.3.3 update閲覧履歴(閲覧履歴Params)
                l_dataManager.updateBrowseHistory(l_cloneBrowseHistoryParams);
                log.debug("update閲覧履歴(閲覧履歴Params)を執行します");
            }
        }

        //1.11 createResponse()
        WEB3PvInfoPrivateInformationDetailResponse l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_request.createResponse();
        log.debug("createResponse()を執行します");

        //1.12 プロパティセット
        l_response.displayContentsId = new Long(l_displayContentsParams.getDisplayContentsId()).toString();
        l_response.displayTitle = l_displayContentsParams.display_title;
        l_response.lastUpdatedTimestamp = l_displayContentsParams.last_updated_timestamp;
        l_response.urlLink = l_displayContentsParams.display_url;
        l_response.displayMessage = l_displayContentsParams.display_message;
        log.debug("表示内容ＩＤ: " + l_response.displayContentsId);
        log.debug("表示タイトル: " + l_response.displayTitle);
        log.debug("最終更新日時: " + l_response.settleContractUnits);
        log.debug("URLリンク: " + l_response.settleContractUnits);
        log.debug("表示文章" + l_response.displayMessage);
        l_response.advanceUnits = l_AdvanceUnits;

        if (l_arrlisSettleContractUnits.length == 0)
        {
            log.debug("toArray()の戻り値.length == 0の場合");
            l_response.settleContractUnits = null;
            log.debug("建玉情報一覧: " + l_response.settleContractUnits);
        }
        else
        {
            log.debug("toArray()の戻り値.length != 0の場合");
            l_response.settleContractUnits = l_arrlisSettleContractUnits;
            log.debug("建玉情報一覧: " + l_response.settleContractUnits);
        }
        
            l_response.cashoutStopUnit = l_pvInfoCashoutStopUnit;

        if(l_lisCommissionCampaignUnit == null || l_lisCommissionCampaignUnit.size() == 0)
        {
            l_response.commissionCampaignUnits = null; 
        }
        else
        {
            WEB3PvInfoCommissionCampaignUnit[] l_pvInfoCCUs = new WEB3PvInfoCommissionCampaignUnit[l_lisCommissionCampaignUnit.size()];
            l_lisCommissionCampaignUnit.toArray(l_pvInfoCCUs);
            l_response.commissionCampaignUnits = l_pvInfoCCUs;
        }

        //不足金発生表示情報 ＝　@プロパティセットした不足金発生表示情報
        //      　@　@※プロパティセットしてない場合はnullをセット。
        l_response.shortfallGenerationInfo = l_shortfallGenerationInfo;

        //第一水準追証表示情報    ＝　@プロパティセットした第一水準追証表示情報
        //      　@　@※プロパティセットしてない場合はnullをセット。
        l_response.firstAdditionalInfo = l_firstAdditionalInfo;

        //第二水準追証表示情報    ＝　@プロパティセットした第二水準追証表示情報
        //      　@　@※プロパティセットしてない場合はnullをセット。
        l_response.secondAdditionalInfo = l_secondAdditionalInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (deleteダイレクト指定メッセージ)<BR>
     * ダイレクト指定メッセージ削除処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービス)deleteダイレクト指定メッセージ」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * ダイレクト指定メッセージ削除リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse
     * @@roseuid 41479F3E004A
     */
    protected WEB3PvInfoDirectMessageDeleteResponse deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        //1.1 validate( )(ダイレクト指定メッセージ削除リクエスト)
        l_request.validate();
        log.debug("validate( )を執行します");

        //1.2 get補助口座(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        log.debug("get補助口座(SubAccountTypeEnum)を執行します");

        //1.3 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        log.debug("getMainAccount()を執行します");

        //1.4  validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        log.debug("validate注文受付可能()を執行します");

        //1.5 get閲覧履歴Params(顧客, long)
        WEB3PvInfoDataManager l_pvInfoDataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        long l_lngDIsplayContentsId = Long.parseLong(l_request.displayContentsId);
        BrowseHistoryParams l_browseHistoryParams
            = l_pvInfoDataMgr.getBrowseHistoryParams(l_mainAccount, l_lngDIsplayContentsId);
        log.debug("get閲覧履歴Params(顧客, long)を執行します");

        //1.6 clone閲覧履歴Params(閲覧履歴Params)
        BrowseHistoryParams l_cloneBrowseHistoryParams = l_pvInfoDataMgr.cloneBrowseHistoryParams(l_browseHistoryParams);
        log.debug("clone閲覧履歴Params(閲覧履歴Params)を執行します");

        //1.7 メッセージ プロパティセット
        l_cloneBrowseHistoryParams.delete_flag = WEB3PvInfoDeleteFlagDef.DELETE_YES;
        l_cloneBrowseHistoryParams.last_updated_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //1.8 update閲覧履歴(閲覧履歴Params)
        l_pvInfoDataMgr.updateBrowseHistory(l_cloneBrowseHistoryParams);
        log.debug("update閲覧履歴(閲覧履歴Params)を執行します");

        //1.9 createResponse()
        WEB3PvInfoDirectMessageDeleteResponse l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_request.createResponse();
        log.debug("createResponse()を執行します");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create立替金情報一覧)<BR>
     * 立替金情報の一覧を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ詳細サービス)create立替金情報一覧」参照<BR>
     * @@param l_subAccount<BR>
     * @@return WEB3PvInfoAdvanceUnit[]<BR>
     */
    protected WEB3PvInfoAdvanceUnit[] createAdvanceUnitList(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAdvanceUnitList(WEB3GentradeMainAccount L_subAccount)";
        log.entering(STR_METHOD_NAME);
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        //1.1資産余力情報を取得する。
        Object l_objPowerInfo = l_dataManager.getTradingPowerInfo(l_subAccount);
        //1.2立替金情報を格納するArrayListを生成する。
        List l_lstAdvanceUnit = new ArrayList();
        //1.3(*)インデックスの範囲内(0～5)についてLoop処理
        //パラメータ.補助口座.getMainAccount()メソッドにて顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //顧客オブジェクト.is信用口座開設()メソッドをコールする。       
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        for (int i = 0; i <= WEB3PvInfoTPBizDateCheckNumDef.MAX_DAYS ; i++)
        {
            //入金請求額
            double l_dblDemanDamount = 0D;
            //営業日
            Date l_datBizDate = null;
            //引出可能現金
            double l_dblCallPower = 0D;
            //引出可能現金
            double l_dblBalance = 0D;
            //(現物顧客の場合)パラメータ.補助口座.getMainAccount().is信用口座開設() == falseの場合、以下の処理を実施する
            if (!l_blnIsMarginAccount)
            {
                WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
                //1.3.1.1余力計算条件を取得する。
                WEB3TPCalcCondition l_calcCondition = l_powerCalcEquity.getCalcCondition();
                //1.3.1.2営業日を取得する。
                l_datBizDate = l_calcCondition.getBizDate(i);
                //1.3.1.3入金請求額を算出する。
                l_dblDemanDamount = l_powerCalcEquity.calcDemandamount(i);
            }
            else if (l_blnIsMarginAccount)
            {
                WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
                //1.3.2.1余力計算条件を取得する。
                WEB3TPCalcCondition l_calcCondition = l_powerCalcMargin.getCalcCondition();
                //1.3.2.2営業日を取得する。
                l_datBizDate = l_calcCondition.getBizDate(i);
                //1.3.2.3追証余力を算出する。
                l_dblCallPower = l_powerCalcMargin.calcMarginCallPower(i);
                	//1.3.2.4引出可能現金を算出する。
                	//l_dblBalance = Math.abs(Math.min(l_powerCalcMargin.calcActualPaymentBalance(i), 0D));
				//2006/03/13 修正
				//1.3.2.4特別立替金を算出する。
				l_dblBalance = l_powerCalcMargin.calcSpecialDebitAmount(i);
                //1.3.2.5入金請求額を算出する。
                l_dblDemanDamount = l_powerCalcMargin.calcDemandamount(i);
            }
            
            //1.3.3(*)入金請求額チェック
            //処理対象の余力推移明細ユニット.入金請求額 == 0の場合、次の要素へ処理を移行する。(continue;)
            if (l_dblDemanDamount == 0D)
            {
                continue;
            }
            
            //1.3.4立替金情報インスタンスを生成する。
            WEB3PvInfoAdvanceUnit l_advanceUnit = new WEB3PvInfoAdvanceUnit();
            //1.3.5(*)プロパティセット(*)立替金情報に以下のプロパティをセットする。
            //立替金発生日    ＝　@calc営業日()の戻り値
            l_advanceUnit.advanceGenDate = WEB3DateUtility.toDay(l_datBizDate);
            if (l_blnIsMarginAccount)
            {
                //追証余力(*1)  ＝　@calc追証余力()の戻り値
                l_advanceUnit.additionalTradingPower = WEB3StringTypeUtility.formatNumber(l_dblCallPower);
				//預り金不足額(*1)    ＝　@calc特別立替金()の戻り値
				l_advanceUnit.accountBalanceShortfall = WEB3StringTypeUtility.formatNumber(l_dblBalance);
            }
            //入金請求額    ＝　@calc入金請求額()の戻り値
            l_advanceUnit.payClaimAmount = WEB3StringTypeUtility.formatNumber(l_dblDemanDamount);
            
            //1.3.6ArrayListに立替金情報を追加する。
            l_lstAdvanceUnit.add(l_advanceUnit);
        }
        
        //1.3.7ArrayList立替金情報の配列を取得する。
        WEB3PvInfoAdvanceUnit[] l_AdvanceUnits = new WEB3PvInfoAdvanceUnit[l_lstAdvanceUnit.size()];
        l_lstAdvanceUnit.toArray(l_AdvanceUnits);
        log.exiting(STR_METHOD_NAME);
        return l_AdvanceUnits;
    }
}
@
