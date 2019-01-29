head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentRequisitionManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理サービスImpl(WEB3TPPaymentRequisitionManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 陸文静 (中訊) 仕様変更モデル309,310,318,337,339,341
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionManagement;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.define.WEB3AdditionalGenerationStateDivDef;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.tradingpower.define.WEB3TPShortfallGenerationStateDivDef;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

/**
 * (入金請求管理サービスImpl)<BR>
 * (入金請求管理サービスImpl)<BR>
 * <BR>
 * 入金請求管理インターフェイスの実装クラス<BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPPaymentRequisitionManageServiceImpl implements WEB3TPPaymentRequisitionManageService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionManageServiceImpl.class);

    /**
     * @@roseuid 48F5856500AB
     */
    public WEB3TPPaymentRequisitionManageServiceImpl()
    {

    }

    /**
     * (get不足金発生状況)<BR>
     * (get不足金発生状況)<BR>
     * <BR>
     * 不足金が発生しているか判定を行い、判定結果を返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理サービス)get不足金発生状況」参照<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@return String
     * @@roseuid 487E92A7012
     * @@throws WEB3BaseException
     */
    public String getLackCashOccurSituation(MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLackCashOccurSituation(MainAccount)";
        log.entering(STR_METHOD_NAME);

        //入金請求管理オブジェクトを生成する。
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //不足金の発生結果を取得する。
        boolean l_blnIsShortfallGeneration =
            l_paymentRequisitionManagement.isShortfallGeneration();

        if(l_blnIsShortfallGeneration)
        {
            //get信用現物判定フラグ
            String l_strMarginEquityFlag =
                l_paymentRequisitionManagement.getMarginEquityJudgeFlag();

            //(*)分岐フロ－
            //get信用現物判定フラグ()の戻り値　@==　@"1"(信用顧客) の場合
            if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(l_strMarginEquityFlag))
            {
                //”2”(不足金発生<信用顧客>)を返却
                log.exiting(STR_METHOD_NAME);
                return WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_ACCOUNT;
            }

            //(*)分岐フロ－
            //get信用現物判定フラグ()の戻り値　@==　@"0"(現物顧客) の場合
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(l_strMarginEquityFlag))
            {
                //”1”(不足金発生<現物顧客>)を返却
                log.exiting(STR_METHOD_NAME);
                return WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_ACCOUNT;
            }
        }

        //”0”(不足金未発生)を返却
        log.exiting(STR_METHOD_NAME);
        return WEB3TPShortfallGenerationStateDivDef.SHORTFALL_NOT_GENERATION;
    }

    /**
     * (get追証発生状況)<BR>
     * (get追証発生状況)<BR>
     * <BR>
     * 追証が発生しているか判定を行い、判定結果を返却する。<BR>
     * <BR>
     * １）　@get追証発生状況（）をコールし、戻り値を返却する。<BR>
     * <BR>
     * [引数]<BR>
     * ・顧客：　@引数.顧客<BR>
     * ・当初追証発生フラグ：　@false<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@return String
     * @@roseuid 487EA526027C
     */
    public String getAdditionalMarginSituation(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        //１）　@get追証発生状況（）をコールし、戻り値を返却する。
        return this.getAdditionalMarginSituation(l_mainAccount, false);
    }

    /**
     * (get追証発生状況)<BR>
     * (get追証発生状況)<BR>
     * <BR>
     * 追証が発生しているか判定を行い、判定結果を返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理サービス)get追証発生状況」参照<BR>
     * ======================================================== <BR>
     * シーケンス図 ：((入金請求管理サービス)get追証発生状況) <BR>
     * 具体位置：((*)分岐フローis信用口座開設()の戻り値 = FALSE(現物顧客)の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (当初追証発生考慮フラグ)<BR>
     * (当初追証発生考慮フラグ)<BR>
     * @@return String
     * @@roseuid 487EA526027C
     * @@throws WEB3BaseException
     */
    public String getAdditionalMarginSituation(
        MainAccount l_mainAccount, boolean l_blnFirstOpenAdddepositFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionalMarginSituation(MainAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

        //信用口座を開設しているかどうかの判別を行う。
        //true：　@開設済
        //false：　@未開設
        //[引数]
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountEstablished =
            l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)分岐フロー
        //is信用口座開設()の戻り値 = false(現物顧客)
        if (!l_blnIsMarginAccountEstablished)
        {
            log.debug("信用口座開設なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "信用口座開設なし。");
        }

        //入金請求管理オブジェクトを生成する。
        //[引数]
        //顧客：　@引数.顧客
        //当初追証発生考慮フラグ：　@引数.当初追証発生考慮フラグ
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(
                l_gentradeMainAccount, l_blnFirstOpenAdddepositFlag);

        //第二水準追証の発生結果を取得する。
        boolean l_blnIsSecondAdddeposit = l_paymentRequisitionManagement.isSecondAdddeposit();

        //(*)分岐フロ－
        //is第二水準追証発生( )の戻り値　@==　@TRUE の場合
        if(l_blnIsSecondAdddeposit)
        {
            //”2”(第二水準追証発生)を返却
            log.exiting(STR_METHOD_NAME);
            return WEB3AdditionalGenerationStateDivDef.SECOND_ADDITIONAL_GENERATION;
        }

        //第一水準追証の発生結果を取得する。
        boolean l_blnIsFirstAdddeposit = l_paymentRequisitionManagement.isFirstAdddeposit();

        //(*)分岐フロ－
        //is第一水準追証発生( )の戻り値　@==　@TRUE の場合
        if (l_blnIsFirstAdddeposit)
        {
            //”1”(第一水準追証発生)を返却
            log.exiting(STR_METHOD_NAME);
            return WEB3AdditionalGenerationStateDivDef.FIRST_ADDITIONAL_GENERATION;
        }

        //”0”(追証未発生)を返却
        log.exiting(STR_METHOD_NAME);
        return WEB3AdditionalGenerationStateDivDef.ADDITIONAL_NOT_GENERATION;
    }

    /**
     * (get入金請求顧客詳細情報)<BR>
     * (get入金請求顧客詳細情報)<BR>
     * <BR>
     * 入金請求顧客詳細情報クラスを生成し、返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理サービス)get入金請求顧客詳細情報」参照<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@return WEB3TPPaymentRequisitionAccountDetailInfo
     * @@roseuid 4872C6020347
     * @@throws WEB3BaseException
     */
    public WEB3TPPaymentRequisitionAccountDetailInfo getPaymentRequisitionAccountDetailInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentRequisitionAccountDetailInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);

        //入金請求管理オブジェクトを生成する。
        //[引数]
        //顧客：　@引数.顧客
        //当初追証発生考慮フラグ：　@true
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(
                l_mainAccount, true);

        //入金請求顧客詳細情報を生成する。
        WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
            WEB3TPPaymentRequisitionAccountDetailInfo.createPaymentRequisitionAccountDetailInfo(
                l_paymentRequisitionManagement);

        log.exiting(STR_METHOD_NAME);
        return l_paymentRequisitionAccountDetailInfo;
    }

    /**
     * (get不足金発生情報)<BR>
     * (get不足金発生情報)<BR>
     * <BR>
     * 不足金発生情報クラスを生成し、返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理サービス)get不足金発生情報」参照<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@return WEB3TPShortfallGenerationInfo
     * @@roseuid 4872C6020343
     * @@throws WEB3BaseException
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getShortfallGenerationInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);

        //入金請求管理オブジェクトを生成する。
        //[引数]
        //顧客：　@引数.顧客
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //不足金発生情報を生成する。
        //[引数]
        //・入金請求管理：　@create入金請求管理()の戻り値
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            WEB3TPShortfallOccurInfo.createShortfallGenerationInfo(l_paymentRequisitionManagement);

        log.exiting(STR_METHOD_NAME);
        return l_shortfallGenerationInfo;
    }

    /**
     * (get追証発生情報)<BR>
     * (get追証発生情報)<BR>
     * <BR>
     * 追証発生情報クラスを生成し、返却する。<BR>
     * <BR>
     * １）　@get追証発生情報（）をコールし、戻り値を返却する。<BR>
     * <BR>
     * [引数] <BR>
     * ・顧客：　@引数.顧客  <BR>
     * ・当初追証発生考慮フラグ：　@false <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 4872C602034B
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        //１）　@get追証発生情報（）をコールし、戻り値を返却する。
        return this.getAdddepositGenerationInfo(l_mainAccount, false);
    }

    /**
     * (get追証発生情報)<BR>
     * (get追証発生情報)<BR>
     * <BR>
     * 追証発生情報クラスを生成し、返却する。<BR>
     * <BR>
     * ※シーケンス図「(入金請求管理サービス)get追証発生情報」参照<BR>
     * ======================================================== <BR>
     * シーケンス図 ：((入金請求管理サービス)get追証発生情報) <BR>
     * 具体位置：((*)分岐フローis信用口座開設()の戻り値 = FALSE(現物顧客)の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * (顧客)<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (当初追証発生考慮フラグ)<BR>
     * (当初追証発生考慮フラグ)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 4872C602034B
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(
        MainAccount l_mainAccount, boolean l_blnFirstOpenAdddepositFlag)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdddepositGenerationInfo(MainAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

        //信用口座を開設しているかどうかの判別を行う。
        //true：　@開設済
        //false：　@未開設
        //[引数]
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountEstablished =
            l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)分岐フロー
        //is信用口座開設()の戻り値 == false(現物顧客)
        if (!l_blnIsMarginAccountEstablished)
        {
            log.debug("信用口座開設なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "信用口座開設なし。");
        }

        //入金請求管理オブジェクトを生成する。
        //[引数]
        //顧客：　@引数.顧客
        //当初追証発生考慮フラグ：　@引数.当初追証発生考慮フラグ
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(
                l_gentradeMainAccount, l_blnFirstOpenAdddepositFlag);

        //追証発生情報を生成する。
        //[引数]
        //入金請求管理：　@create入金請求管理()の戻り値
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
            WEB3TPAdddepositGenerationInfo.createAdddepositGenerationInfo(l_paymentRequisitionManagement);

        //追証発生情報を返却
        log.exiting(STR_METHOD_NAME);
        return l_adddepositGenerationInfo;
    }
}
@
