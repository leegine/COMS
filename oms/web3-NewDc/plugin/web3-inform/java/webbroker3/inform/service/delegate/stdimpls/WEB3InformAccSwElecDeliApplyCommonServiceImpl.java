head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.45.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込共通サービスImpl(WEB3InformAccSwElecDeliApplyCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/18 孟亜南(中訊) モデル・No.100
Revesion History : 2007/07/30 孟亜南(中訊) モデル・No.103
Revesion History : 2007/08/29 金傑(中訊) モデル・No.106
Revesion History : 2007/08/30 金傑(中訊) モデル・No.107
Revesion History : 2007/09/19 張騰宇(中訊) モデル・No.109 115
Revesion History : 2007/09/27 趙林鵬(中訊) モデル・No.117
Revesion History : 2007/09/28 張騰宇(中訊) モデル・No.118
Revesion History : 2007/10/04 長瀬(SCS) モデル・No.119
Revesion History : 2009/02/16 柴双紅(中訊) モデル・No.138
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OnlyMobileOpenDivDef;
import webbroker3.common.define.WEB3PosReportDivDef;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TradingReportDivDef;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformCapitalGainTaxAccOpenDivDef;
import webbroker3.inform.define.WEB3InformTaxTypeDef;
import webbroker3.inform.define.WEB3InformUserDataDef;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (口座切替・電子交付申込共通サービスImpl)<BR>
 * 口座切替・電子交付申込共通サービス実装クラス<BR>
 *
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyCommonServiceImpl implements WEB3InformAccSwElecDeliApplyCommonService
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
           WEB3InformAccSwElecDeliApplyCommonServiceImpl.class);

    /**
     * (validate口座切替・電子交付申込情報)<BR>
     * 口座切替・電子交付申込情報の変更内容をチェックする。 <BR>
     * <BR>
     * １） 「変更項目有無」チェック <BR>
     *      　@＜チェック対象項目＞ <BR>
     * 　@　@　@ － モバイル専用口座開設区分 <BR>
     * 　@　@　@ － 取引報告書交付区分 <BR>
     * 　@　@　@ － 取引残高報告書交付区分 <BR>
     * 　@　@　@ － 取引残高報告書作成周期区分 <BR>
     * 　@　@　@ － 取引残高報告書預り証作成フラグ <BR>
     * 　@　@　@ － 取引残高報告書計算書作成フラグ <BR>
     * 　@　@　@ － 税区分 <BR>
     * 　@　@　@ － 税区分（次年） <BR>
     * 　@　@　@ － 信用取引税区分 <BR>
     * 　@　@　@ － 信用取引税区分（次年） <BR>
     * 　@　@　@ － 特定管理口座開設区分 <BR>
     * <BR>
     * １－１） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、 <BR>
     * 　@　@　@　@ 一項目も差異がない場合、例外をスローする。 <BR>
     * 　@　@　@　@ エラーメッセージ【BUSINESS_ERROR_02680（変更項目がありません。）】<BR>
     * を表示。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02680<BR>
     * <BR>
     * １－２） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、 <BR>
     * 　@　@　@　@ 一項目でも差異がある場合、以下のチェックを行う。 <BR>
     * <BR>
     * １－２－１） 該当する(引数)変更後情報の項目が、[全て未入力]の場合、<BR>
     * 例外をスローする。 <BR>
     *      　@      エラーメッセージ【BUSINESS_ERROR_02688(変更項目無しエラー)】<BR>
     *      を表示。 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02688<BR>
     * <BR>
     * ２） 「取報・取残変更項目有無」チェック <BR>
     *      　@＜チェック対象項目＞ <BR>
     * 　@　@　@ － 取引報告書交付区分 <BR>
     * 　@　@　@ － 取引残高報告書交付区分 <BR>
     * 　@　@　@ － 取引残高報告書作成周期区分 <BR>
     * 　@　@　@ － 取引残高報告書預り証作成フラグ <BR>
     * 　@　@　@ － 取引残高報告書計算書作成フラグ <BR>
     * <BR>
     * ２－１） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、 <BR>
     *          一項目でも差異がある場合、以下のチェックを行う。 <BR>
     * <BR>
     * ２－１－１） 該当する(引数)変更後情報の項目が <BR>
     *      　@      [全て未入力、又は、全て入力]以外の場合、例外をスローする。 <BR>
     *      　@      エラーメッセージ【ビジネスエラー『取報・取残変更項目無しエラー』】<BR>
     *      を表示。 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02872<BR>
     * <BR>
     * ３） 税区分変更項目有無チェック <BR>
     *      　@＜チェック対象項目＞ <BR>
     * 　@　@　@ － 税区分 <BR>
     * 　@　@　@ － 税区分（次年） <BR>
     * <BR>
     * ３－１） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、 <BR>
     *          一項目でも差異がある場合、以下のチェックを行う。 <BR>
     * <BR>
     * ３－１－１） 該当する(引数)変更後情報の項目が <BR>
     *      　@      [全て未入力、又は、全て入力]以外の場合、例外をスローする。 <BR>
     *      　@      エラーメッセージ【ビジネスエラー『税区分変更項目無しエラー』】<BR>
     *      を表示。 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02873<BR>
     * <BR>
     * ４） 「信用取引税区分変更項目有無」チェック <BR>
     *      　@＜チェック対象項目＞ <BR>
     * 　@　@　@ － 信用取引税区分 <BR>
     * 　@　@　@ － 信用取引税区分（次年） <BR>
     * <BR>
     * ４－１） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、 <BR>
     *          一項目でも差異がある場合、以下のチェックを行う。 <BR>
     * <BR>
     * ４－１－１） 該当する(引数)変更後情報の項目が <BR>
     *      　@      [全て未入力、又は、全て入力]以外の場合、例外をスローする。 <BR>
     *      　@      エラーメッセージ【ビジネスエラー『信用取引税区分変更項目無しエラー』】<BR>
     *      を表示。<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02874<BR>
     * <BR>
     * ５） 「特定口座属性」チェック <BR>
     *      　@＜チェック対象項目①@＞ <BR>
     * 　@　@　@ － 税区分 <BR>
     * 　@　@　@ － 信用取引税区分 <BR>
     *      　@＜チェック対象項目②＞ <BR>
     * 　@　@　@ － 税区分（次年） <BR>
     * 　@　@　@ － 信用取引税区分（次年） <BR>
     * <BR>
     * ５－１） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目①@＞を比較し、 <BR>
     *          一項目でも差異がある場合、且つ、 <BR>
     *          (引数)変更後情報が下記のいずれかに該当する場合、例外をスローする。 <BR>
     *      　@  エラーメッセージ【ビジネスエラー『特定口座エラー』】を表示。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02875<BR>
     * <BR>
     * ５－１－１） 税区分 == 2：特定 の場合、 <BR>
     *              信用取引税区分 != (1：一般、又は、2：特定、又は、null)  <BR>
     * <BR>
     * ５－１－２） 税区分 == 3：特定口座かつ源泉徴収 の場合、 <BR>
     *              信用取引税区分 != (1：一般、又は、3：特定口座かつ源泉徴収、<BR>
     *              又は、null)  <BR>
     * <BR>
     * ５－２） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目②＞を比較し、 <BR>
     *          一項目でも差異がある場合、且つ、 <BR>
     *          (引数)変更後情報が下記のいずれかに該当する場合、例外をスローする。 <BR>
     *          エラーメッセージ【ビジネスエラー『特定口座エラー』】を表示。 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02875<BR>
     * <BR>
     * ５－２－１） 税区分（次年） == 2：特定 の場合、 <BR>
     *              信用取引税区分（次年） != (1：一般、又は、2：特定、又は、null)  <BR>
     * <BR>
     * ５－２－２） 税区分（次年） == 3：特定口座かつ源泉徴収 の場合、 <BR>
     *              信用取引税区分（次年） != (1：一般、又は、<BR>
     *              3：特定口座かつ源泉徴収、又は、null)  <BR>
     * <BR>
     * ６） 「特定管理口座属性」チェック <BR>
     *      　@＜チェック対象項目＞ <BR>
     * 　@　@　@ － 税区分 <BR>
     * 　@　@　@ － 信用取引税区分 <BR>
     * 　@　@　@ － 特定管理口座開設区分 <BR>
     * <BR>
     * ６－１） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、<BR>
     *          一項目でも差異がある場合、且つ、 <BR>
     *          (引数)変更後情報が下記に該当する場合、例外をスローする。 <BR>
     *      　@  エラーメッセージ【ビジネスエラー『特定管理口座エラー』】を表示。<BR>
     * <BR>
     * ６－１－１） 税区分 == 1：一般、且つ、信用取引税区分 == 1：一般 の場合、<BR>
     *              特定管理口座開設区分 == 1：開設 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02876<BR>
     * <BR>
     * ７） 「口座属性」チェック   <BR>
     *      　@＜チェック対象項目＞ <BR>
     * 　@　@　@ － モバイル専用口座開設区分 <BR>
     * 　@　@　@ － 取引報告書交付区分 <BR>
     * 　@　@　@ － 取引残高報告書交付区分 <BR>
     * <BR>
     * ７－１） (引数)変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、<BR>
     *          一項目でも差異がある場合、且つ、 <BR>
     *          (引数)変更後情報が下記に該当する場合、例外をスローする。 <BR>
     *      　@  エラーメッセージ【ビジネスエラー『電子交付同意エラー』】を表示。 <BR>
     * <BR>
     * ７－１－１） モバイル専用口座開設区分 == 1：開設 の場合、 <BR>
     *              取引報告書交付区分 == 1：電子配布、又は、<BR>
     *              取引残高報告書交付区分 == 9：電子配布<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@　@BUSINESS_ERROR_02877<BR>
     * @@param l_beforeChangedInfo - (変更前情報)<BR>
     * 口座切替・電子交付申込情報オブジェクト
     * @@param l_changedInfo - (変更後情報)<BR>
     * 口座切替・電子交付申込情報オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateAccSwitchElecDeliApplyInfo(
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo,
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccSwitchElecDeliApplyInfo("
            + "WEB3AdminInformAccSwitchElecDeliApplyInfo, "
            + "MainAccount)";

        log.entering(STR_METHOD_NAME);

        if (l_beforeChangedInfo == null)
        {
            log.debug("変更項目無しエラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更項目無しエラー。");
        }

        if (l_changedInfo == null)
        {
            log.debug("変更項目無しエラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更項目無しエラー。");
        }

        // １） 「変更項目有無」チェック
        // ＜チェック対象項目＞
        // － モバイル専用口座開設区分
        // － 取引報告書交付区分
        // － 取引残高報告書交付区分
        // － 取引残高報告書作成周期区分
        // － 取引残高報告書預り証作成フラグ
        // － 取引残高報告書計算書作成フラグ
        // － 税区分
        // － 税区分（次年）
        // － 信用取引税区分
        // － 信用取引税区分（次年）
        // － 特定管理口座開設区分
        // １－１） 変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、
        // 　@　@　@一項目も差異がない場合、例外をスローする。
        // 　@　@　@エラーメッセージ【BUSINESS_ERROR_02680（変更項目がありません。）】を表示。
        if (WEB3Toolkit.isEquals(l_beforeChangedInfo.mobileAccoutDiv, l_changedInfo.mobileAccoutDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.tradingReportDiv, l_changedInfo.tradingReportDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportDiv, l_changedInfo.positionReportDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportCycleDiv, l_changedInfo.positionReportCycleDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.certificateDepositDiv, l_changedInfo.certificateDepositDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.accountStatementDiv, l_changedInfo.accountStatementDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.taxTypeNext, l_changedInfo.taxTypeNext)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxTypeNext, l_changedInfo.marginTaxTypeNext)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.capitalGainTaxAccOpenDiv, l_changedInfo.capitalGainTaxAccOpenDiv))
        {
            log.debug("変更項目がありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02680,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更項目がありません。");
        }

        // １－２） 変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、
        // 　@　@　@一項目でも差異がある場合、以下のチェックを行う。
        // １－２－１） 該当する(引数)変更後情報の項目が、[全て未入力]の場合、例外をスローする。
        // 　@　@　@　@　@　@エラーメッセージ【BUSINESS_ERROR_02688(変更項目無しエラー)】を表示。
        if (l_changedInfo.mobileAccoutDiv == null
            && l_changedInfo.tradingReportDiv == null
            && l_changedInfo.positionReportDiv == null
            && l_changedInfo.positionReportCycleDiv == null
            && l_changedInfo.certificateDepositDiv == null
            && l_changedInfo.accountStatementDiv == null
            && l_changedInfo.taxType == null
            && l_changedInfo.taxTypeNext == null
            && l_changedInfo.marginTaxType == null
            && l_changedInfo.marginTaxTypeNext == null
            && l_changedInfo.capitalGainTaxAccOpenDiv == null)
        {
            log.debug("変更項目無しエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "変更項目無しエラー。");
        }

        // ２） 「取報・取残変更項目有無」チェック、
        //        － 取引報告書交付区分
        //        － 取引残高報告書交付区分
        //        － 取引残高報告書作成周期区分
        //        － 取引残高報告書預り証作成フラグ
        //        － 取引残高報告書計算書作成フラ
        // ２－１） 変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、
        // 　@　@　@一項目でも差異がある場合、以下のチェックを行う。
        // ２－１－１） 該当する(引数)変更後情報の項目が
        // [全て未入力、又は、全て入力]以外の場合、例外をスローする。
        // エラーメッセージ【ビジネスエラー『取報・取残変更項目無しエラー』】を表示。
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.tradingReportDiv, l_changedInfo.tradingReportDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportDiv, l_changedInfo.positionReportDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportCycleDiv, l_changedInfo.positionReportCycleDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.certificateDepositDiv, l_changedInfo.certificateDepositDiv)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.accountStatementDiv, l_changedInfo.accountStatementDiv)))
        {
            if (!((l_changedInfo.tradingReportDiv == null
                && l_changedInfo.positionReportDiv == null
                && l_changedInfo.positionReportCycleDiv == null
                && l_changedInfo.certificateDepositDiv == null
                && l_changedInfo.accountStatementDiv == null)
                || (l_changedInfo.tradingReportDiv != null
                && l_changedInfo.positionReportDiv != null
                && l_changedInfo.positionReportCycleDiv != null
                && l_changedInfo.certificateDepositDiv != null
                && l_changedInfo.accountStatementDiv != null)))
            {
                log.debug("ビジネスエラー『取報・取残変更項目無しエラー』。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02872,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ビジネスエラー『取報・取残変更項目無しエラー』。");
            }
        }

        // ３） 税区分変更項目有無チェック。
        // 　@　@＜チェック対象項目＞
        //     － 税区分
        //     － 税区分（次年）
        // ３－１） 変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、
        // 　@　@　@一項目でも差異がある場合、以下のチェックを行う。
        // ３－１－１） 該当する(引数)変更後情報の項目が
        // [全て未入力、又は、全て入力]以外の場合、例外をスローする。
        // エラーメッセージ【ビジネスエラー『税区分変更項目無しエラー』】を表示。
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
           && WEB3Toolkit.isEquals(l_beforeChangedInfo.taxTypeNext, l_changedInfo.taxTypeNext)))
        {
            if (!((l_changedInfo.taxType == null && l_changedInfo.taxTypeNext == null)
                || (l_changedInfo.taxType != null && l_changedInfo.taxTypeNext != null)))
            {
                log.debug("ビジネスエラー『税区分変更項目無しエラー』。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02873,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ビジネスエラー『税区分変更項目無しエラー』。");
            }
        }

        // ４） 「信用取引税区分変更項目有無」チェック
        //        － 信用取引税区分
        //        － 信用取引税区分（次年）
        // ４－１） 変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、
        // 　@　@　@一項目でも差異がある場合、以下のチェックを行う。
        // ４－１－１） 該当する(引数)変更後情報の項目が
        // [全て未入力、又は、全て入力]以外の場合、例外をスローする。
        // エラーメッセージ【ビジネスエラー『信用取引税区分変更項目無しエラー』】を表示。
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxTypeNext, l_changedInfo.marginTaxTypeNext)))
        {
            if (!((l_changedInfo.marginTaxType == null && l_changedInfo.marginTaxTypeNext == null)
                || (l_changedInfo.marginTaxType != null && l_changedInfo.marginTaxTypeNext != null)))
            {
                log.debug("ビジネスエラー『信用取引税区分変更項目無しエラー』。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02874,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ビジネスエラー『信用取引税区分変更項目無しエラー』。");
            }
        }

        // ５） 「特定口座属性」チェック
        // 　@　@　@＜チェック対象項目①@＞
        // 　@　@　@－ 税区分
        // 　@　@　@－ 信用取引税区分
        // 　@　@　@＜チェック対象項目②＞
        // 　@　@　@－ 税区分（次年）
        // 　@　@　@－ 信用取引税区分（次年）
        // ５－１） 変更前情報と(引数)変更後情報の＜チェック対象項目①@＞を比較し、
        // 　@　@　@一項目でも差異がある場合、且つ、
        // 　@　@　@(引数)変更後情報が下記のいずれかに該当する場合、例外をスローする。
        // 　@　@　@エラーメッセージ【ビジネスエラー『特定口座エラー』】を表示。
        // ５－１－１） 税区分 == 2：特定 の場合、
        // 　@　@　@　@　@信用取引税区分 != (1：一般、又は、2：特定、又は、null)
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)))
        {
            if (WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.taxType))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxType))
                    && !(WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.marginTaxType))
                    && l_changedInfo.marginTaxType != null)
                {
                    log.debug("ビジネスエラー『特定口座エラー』。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "ビジネスエラー『特定口座エラー』。");
                }
            }

            // ５－１－２） 税区分 == 3：特定口座かつ源泉徴収 の場合、
            // 　@　@　@　@　@信用取引税区分 != (1：一般、又は、3：特定口座かつ源泉徴収、又は、null)
            if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.taxType))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxType))
                    && !(WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.marginTaxType))
                    && l_changedInfo.marginTaxType != null)
                {
                    log.debug("ビジネスエラー『特定口座エラー』。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "ビジネスエラー『特定口座エラー』。");
                }
            }
        }

        // ５－２） 変更前情報と(引数)変更後情報の＜チェック対象項目①@＞を比較し、
        // 　@　@　@一項目でも差異がある場合、且つ、
        // 　@　@　@(引数)変更後情報が下記のいずれかに該当する場合、例外をスローする。
        // 　@　@　@エラーメッセージ【ビジネスエラー『特定口座エラー』】を表示。
        // ５－２－１） 税区分（次年） == 2：特定 の場合、
        // 　@　@　@　@　@信用取引税区分（次年） != (1：一般、又は、2：特定、又は、null)
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxTypeNext, l_changedInfo.taxTypeNext)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxTypeNext, l_changedInfo.marginTaxTypeNext)))
        {
            if (WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.taxTypeNext))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxTypeNext))
                    && !(WEB3InformTaxTypeDef.SPECIAL.equals(l_changedInfo.marginTaxTypeNext))
                    && l_changedInfo.marginTaxTypeNext != null)
                {
                    log.debug("ビジネスエラー『特定口座エラー』。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "ビジネスエラー『特定口座エラー』。");
                }
            }

            // ５－２－２） 税区分（次年） == 3：特定口座かつ源泉徴収 の場合、
            // 　@　@　@　@　@信用取引税区分（次年） != (1：一般、又は、3：特定口座かつ源泉徴収、又は、null)
            if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.taxTypeNext))
            {
                if (!(WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxTypeNext))
                    && !(WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_changedInfo.marginTaxTypeNext))
                    && l_changedInfo.marginTaxTypeNext != null)
                {
                    log.debug("ビジネスエラー『特定口座エラー』。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02875,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "ビジネスエラー『特定口座エラー』。");
                }
            }
        }

        // ６） 「特定管理口座属性」チェック
        // 　@　@　@　@＜チェック対象項目＞
        // 　@　@　@　@－ 税区分
        // 　@　@　@　@－ 信用取引税区分
        // 　@　@　@　@－ 特定管理口座開設区分
        // ６－１） 変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、
        // 　@　@　@一項目でも差異がある場合、且つ、
        // 　@　@　@(引数)変更後情報が下記のいずれかに該当する場合、例外をスローする。
        // 　@　@　@エラーメッセージ【ビジネスエラー『特定管理口座エラー』】を表示。
        // ６－１－１） 税区分 == 1：一般、且つ、信用取引税区分 == 1：一般 の場合、
        // 　@　@　@　@　@特定管理口座開設区分 == 1：開設
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.taxType, l_changedInfo.taxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.marginTaxType, l_changedInfo.marginTaxType)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.capitalGainTaxAccOpenDiv, l_changedInfo.capitalGainTaxAccOpenDiv)))
        {
            if (WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.taxType)
                && WEB3InformTaxTypeDef.NORMAL.equals(l_changedInfo.marginTaxType))
            {
                if (WEB3InformCapitalGainTaxAccOpenDivDef.OPEN.equals(l_changedInfo.capitalGainTaxAccOpenDiv))
                {
                    log.debug("ビジネスエラー『特定管理口座エラー』。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02876,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "ビジネスエラー『特定管理口座エラー』。");
                }
            }
        }

        // ７） 「口座属性」チェック
        // 　@　@　@　@＜チェック対象項目＞
        // 　@　@　@　@－ モバイル専用口座開設区分
        // 　@　@　@　@－ 取引報告書交付区分
        // 　@　@　@　@－ 取引残高報告書交付区分
        // ７－１） 変更前情報と(引数)変更後情報の＜チェック対象項目＞を比較し、
        // 　@　@　@一項目でも差異がある場合、且つ、
        // 　@　@　@(引数)変更後情報が下記のいずれかに該当する場合、例外をスローする。
        // 　@　@　@エラーメッセージ【ビジネスエラー『電子交付同意エラー』】を表示。
        // ７－１－１） モバイル専用口座開設区分 == 1：開設 の場合、
        // 　@　@　@　@　@取引報告書交付区分 == 1：電子配布、又は、取引残高報告書交付区分 == 9：電子配布
        if (!(WEB3Toolkit.isEquals(l_beforeChangedInfo.mobileAccoutDiv, l_changedInfo.mobileAccoutDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.tradingReportDiv, l_changedInfo.tradingReportDiv)
            && WEB3Toolkit.isEquals(l_beforeChangedInfo.positionReportDiv, l_changedInfo.positionReportDiv)))
        {
            if (WEB3OnlyMobileOpenDivDef.OPEN.equals(l_changedInfo.mobileAccoutDiv))
            {
                if (WEB3TradingReportDivDef.ELECTRON_DISTRIBUTION.equals(l_changedInfo.tradingReportDiv)
                    || WEB3PosReportDivDef.ELECTRON_DISTRIBUTION.equals(l_changedInfo.positionReportDiv))
                {
                    log.debug("ビジネスエラー『電子交付同意エラー』。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02877,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "ビジネスエラー『電子交付同意エラー』。");
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create各種連絡)<BR>
     * 各種連絡新規行を生成する。<BR>
     * <BR>
     * １）　@行オブジェクト生成 <BR>
     * 　@各種連絡Paramsオブジェクトを生成する。<BR>
     * <BR>
     * 　@※各種連絡ParamsはDDLより自動生成する。<BR>
     * <BR>
     * ２）　@顧客.getDataSourceObject()にて顧客行を取得する。<BR>
     * <BR>
     * ３）　@行オブジェクトにプロパティをセットする。<BR>
     * 　@１）で生成した各種連絡Paramsオブジェクトのプロパティに、<BR>
     * 　@　@　@以下の通りセットを行う。<BR>
     * <BR>
     * 　@各種連絡Params.証券会社コード ＝ 顧客行.証券会社コード <BR>
     * 　@各種連絡Params.連絡種別 ＝ (引数)連絡種別 <BR>
     * 　@各種連絡Params.部店コード ＝ 顧客行.部店コード<BR>
     * 　@各種連絡Params.顧客コード ＝ 顧客行.口座コード <BR>
     * 　@各種連絡Params.顧客名 ＝ 顧客行.名前（苗字） <BR>
     * 　@各種連絡Params.顧客メールアドレス ＝ 顧客行.emailアドレス<BR>
     * 　@各種連絡Params.区分１ ＝ (引数)変更後情報.モバイル専用口座開設区分<BR>
     * 　@各種連絡Params.区分２ ＝ (引数)変更後情報.取引報告書交付区分 <BR>
     * 　@各種連絡Params.区分３ ＝ (引数)変更後情報.取引残高報告書交付区分 <BR>
     * 　@各種連絡Params.区分４ ＝ (引数)変更後情報.取引残高報告書作成周期区分 <BR>
     * 　@各種連絡Params.区分５ ＝ (引数)変更後情報.取引残高報告書預り証作成フラグ <BR>
     * 　@各種連絡Params.区分６ ＝ (引数)変更後情報.取引残高報告書計算書作成フラグ <BR>
     * 　@各種連絡Params.区分７ ＝ (引数)変更後情報.税区分 <BR>
     * 　@各種連絡Params.区分８ ＝ (引数)変更後情報.税区分（次年）<BR>
     * 　@各種連絡Params.区分９ ＝ (引数)変更後情報.信用取引税区分 <BR>
     * 　@各種連絡Params.区分１０ ＝ (引数)変更後情報.信用取引税区分（次年）<BR>
     * 　@各種連絡Params.区分１１ ＝ (引数)変更後情報.特定管理口座開設区分 <BR>
     * 　@各種連絡Params.区分１２ ＝ 顧客行.モバイル専用口座開設区分<BR>
     * 　@各種連絡Params.テキスト１ ＝ (引数)日付情報.申込日時 <BR>
     * 　@各種連絡Params.テキスト２ ＝ (引数)日付情報.開始予定日<BR>
     * 　@各種連絡Params.テキスト３ ＝ (引数)日付情報.税区分開設日<BR>
     * 　@各種連絡Params.テキスト４ ＝ (引数)日付情報.信用取引税区分開設日<BR>
     * 　@各種連絡Params.テキスト５ ＝ 顧客行.モバイル専用口座開設区分更新者コード <BR>
     * 　@各種連絡Params.テキスト６ ＝ 顧客行.モバイル専用口座開設区分更新日時 <BR>
     * 　@※null以外の場合、’yyyymmddhhmmss’型に変換。<BR>
     * <BR>
     * ４）　@各種連絡オブジェクト返却<BR>
     * 　@行オブジェクトを指定し、各種連絡オブジェクトを生成し返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@param l_changedInfo - (変更後情報)<BR>
     * 変更後情報
     * @@param l_dateInfo - (日付情報)<BR>
     * 日付情報
     * @@param l_strInformType - (連絡種別)<BR>
     * 連絡種別
     * @@return WEB3Inform
     * @@throws WEB3BaseException
     */
    public WEB3Inform createVariousInform(
        MainAccount l_mainAccount,
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo,
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_dateInfo,
        String l_strInformType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createVariousInform(MainAccount, "
            + "WEB3AdminInformAccSwitchElecDeliApplyInfo, "
            + "WEB3AdminInformAccSwitchElecDeliAppDtInfo, "
            + "String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null
            || l_changedInfo == null
            || l_dateInfo == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }

        //各種連絡Paramsオブジェクトを生成する
        VariousInformParams l_variousInformParams = new VariousInformParams();

        //顧客.getDataSourceObject()にて変更前データを取得する。
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //各種連絡Params.証券会社コード ＝ 顧客行.証券会社コード
        l_variousInformParams.setInstitutionCode(l_mainAcountRow.getInstitutionCode());

        //各種連絡Params.連絡種別 ＝ (引数)連絡種別
        l_variousInformParams.setInformDiv(l_strInformType);

        //各種連絡Params.部店コード ＝ 顧客行.部店コード
        l_variousInformParams.setBranchCode(l_mainAcountRow.getBranchCode());

        //各種連絡Params.顧客コード ＝ 顧客行.口座コード
        l_variousInformParams.setAccountCode(l_mainAcountRow.getAccountCode());

        //各種連絡Params.顧客名 ＝ 顧客行.名前（苗字）
        l_variousInformParams.setAccountName(l_mainAcountRow.getFamilyName());

        //各種連絡Params.顧客メールアドレス ＝ 顧客行.emailアドレス
        l_variousInformParams.setEmailAddress(l_mainAcountRow.getEmailAddress());

        //各種連絡Params.区分１ ＝ (引数)変更後情報.モバイル専用口座開設区分
        l_variousInformParams.setExtDiv1(l_changedInfo.mobileAccoutDiv);

        //各種連絡Params.区分２ ＝ (引数)変更後情報.取引報告書交付区分
        l_variousInformParams.setExtDiv2(l_changedInfo.tradingReportDiv);

        //各種連絡Params.区分３ ＝ (引数)変更後情報.取引残高報告書交付区分
        l_variousInformParams.setExtDiv3(l_changedInfo.positionReportDiv);

        //各種連絡Params.区分４ ＝ (引数)変更後情報.取引残高報告書作成周期区分
        l_variousInformParams.setExtDiv4(l_changedInfo.positionReportCycleDiv);

        //各種連絡Params.区分５ ＝ (引数)変更後情報.取引残高報告書預り証作成フラグ
        l_variousInformParams.setExtDiv5(l_changedInfo.certificateDepositDiv);

        //各種連絡Params.区分６ ＝ (引数)変更後情報.取引残高報告書計算書作成フラグ
        l_variousInformParams.setExtDiv6(l_changedInfo.accountStatementDiv);

        //各種連絡Params.区分７ ＝ (引数)変更後情報.税区分
        l_variousInformParams.setExtDiv7(l_changedInfo.taxType);

        //各種連絡Params.区分８ ＝ (引数)変更後情報.税区分（次年）
        l_variousInformParams.setExtDiv8(l_changedInfo.taxTypeNext);

        //各種連絡Params.区分９ ＝ (引数)変更後情報.信用取引税区分
        l_variousInformParams.setExtDiv9(l_changedInfo.marginTaxType);

        //各種連絡Params.区分１０ ＝ (引数)変更後情報.信用取引税区分（次年）
        l_variousInformParams.setExtDiv10(l_changedInfo.marginTaxTypeNext);

        //各種連絡Params.区分１１ ＝ (引数)変更後情報.特定管理口座開設区分
        l_variousInformParams.setExtDiv11(l_changedInfo.capitalGainTaxAccOpenDiv);

        //各種連絡Params.区分１２ ＝ 顧客行.モバイル専用口座開設区分
        l_variousInformParams.setExtDiv12(l_mainAcountRow.getOnlyMobileOpenDiv());

        //各種連絡Params.テキスト１ ＝ (引数)日付情報.申込日時
        l_variousInformParams.setExtText1(WEB3DateUtility.formatDate(
            l_dateInfo.applyDate,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));

        //各種連絡Params.テキスト２ ＝ (引数)日付情報.開始予定日
        l_variousInformParams.setExtText2(WEB3DateUtility.formatDate(
            l_dateInfo.startScheduleDate,
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));

        //各種連絡Params.テキスト３ ＝ (引数)日付情報.税区分開設日
        l_variousInformParams.setExtText3(l_dateInfo.taxTypeOpenDate);

        //各種連絡Params.テキスト４ ＝ (引数)日付情報.信用取引税区分開設日
        l_variousInformParams.setExtText4(l_dateInfo.marginTaxTypeOpenDate);

        //各種連絡Params.テキスト５ ＝ 顧客行.モバイル専用口座開設区分更新者コード
        l_variousInformParams.setExtText5(l_mainAcountRow.getOnlyMblOpnDivLastUpdater());

        //各種連絡Params.テキスト６ ＝ 顧客行.モバイル専用口座開設区分更新日時
        //null以外の場合、’yyyymmddhhmmss’型に変換。
        if (l_mainAcountRow.getOnlyMblOpnDivTimestamp() != null)
        {
            l_variousInformParams.setExtText6(
                WEB3DateUtility.formatDate(l_mainAcountRow.getOnlyMblOpnDivTimestamp(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
        }

        //各種連絡オブジェクト返却
        WEB3Inform l_inform = new WEB3Inform(l_variousInformParams);

        log.exiting(STR_METHOD_NAME);
        return l_inform;
    }

    /**
     * (create口座切替・電子交付申込日付情報)<BR>
     * 口座切替・電子交付申込日付情報メッセージデータを作成する。<BR>
     * <BR>
     * １） 口座切替・電子交付申込日付情報オブジェクトを生成する <BR>
     * <BR>
     * ２） 取引時間管理.get発注日にて発注日を取得する。<BR>
     * <BR>
     * ３） ２）を基に和暦変換した日付を取得する。<BR>
     * 　@　@年号.toJapDate()にて変換した和暦（YYMMDD形式）文字列（=index[1])。<BR>
     * <BR>
     * ４） 以下の通り、プロパティをセットする。<BR>
     * <BR>
     * ・口座切替・電子交付申込日付情報.申込日時 ＝ 現在日時<BR>
     * ※GtlUtils.getSystemTimestamp()にて取得 <BR>
     * ・口座切替・電子交付申込日付情報.開始予定日 ＝ ２）で取得した日時 <BR>
     * ・口座切替・電子交付申込日付情報.税区分開設日 ＝ 以下の通りセット <BR>
     * 　@　@－(引数)税区分 == 2：特定、又は、3：特定口座かつ源泉徴収 の場合、<BR>
     * 　@　@３）で取得した和暦文字列 <BR>
     * ・口座切替・電子交付申込日付情報.信用取引税区分開設日 ＝以下の通りセット <BR>
     * 　@　@－(引数)信用取引税区分 == 2：特定、又は、3：特定口座かつ源泉徴収 の場合、<BR>
     * 　@　@３）で取得した和暦文字列 <BR>
     * 　@　@－上記以外の場合、nullをセット <BR>
     * <BR>
     * ５）　@生成した口座切替・電子交付申込日付情報オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_strTaxType - (税区分)<BR>
     * 税区分
     * @@param l_strMarginTaxType - (信用取引税区分)<BR>
     * 信用取引税区分
     * @@return WEB3AdminInformAccSwitchElecDeliAppDtInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo createAccSwitchElecDeliAppDtInfo(
        String l_strTaxType,
        String l_strMarginTaxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createAccSwitchElecDeliAppDtInfo(String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 口座切替・電子交付申込日付情報オブジェクトを生成する
        WEB3AdminInformAccSwitchElecDeliAppDtInfo l_adminInformAccSwitchElecDeliAppDtInfo =
            new WEB3AdminInformAccSwitchElecDeliAppDtInfo();

        //２） 取引時間管理.get発注日にて発注日を取得する。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //３） ２）を基に和暦変換した日付を取得する。
        //年号.toJapDate()にて変換した和暦（YYMMDD形式）文字列（=index[1])
        String[] l_strJapDates = WEB3GentradeEra.toJapDate(l_datBizDate);

        //４） 以下の通り、プロパティをセットする。
        //口座切替・電子交付申込日付情報.申込日時 ＝ 現在日時
        l_adminInformAccSwitchElecDeliAppDtInfo.applyDate =
            GtlUtils.getSystemTimestamp();

        //口座切替・電子交付申込日付情報.開始予定日 ＝ ２）で取得した日時
        l_adminInformAccSwitchElecDeliAppDtInfo.startScheduleDate = l_datBizDate;

        //口座切替・電子交付申込日付情報.税区分開設日 ＝ 以下の通りセット
        //－(引数)税区分 == 2：特定、又は、3：特定口座かつ源泉徴収 の場合、３）で取得した和暦文字列
        //－上記以外の場合、nullをセット
        String l_strTaxTypeOpenDate = null;
        int l_intSpecial = (TaxTypeEnum.SPECIAL).intValue();
        int l_intSpecialWithHold = (TaxTypeEnum.SPECIAL_WITHHOLD).intValue();

        if (String.valueOf(l_intSpecial).equals(l_strTaxType)
            || String.valueOf(l_intSpecialWithHold).equals(l_strTaxType))
        {
            l_strTaxTypeOpenDate = l_strJapDates[1];
        }

        l_adminInformAccSwitchElecDeliAppDtInfo.taxTypeOpenDate = l_strTaxTypeOpenDate;

        //口座切替・電子交付申込日付情報.信用取引税区分開設日 ＝以下の通りセット
        //－(引数)信用取引税区分 == 2：特定、又は、3：特定口座かつ源泉徴収 の場合、３）で取得した和暦文字列
        //－上記以外の場合、nullをセット
        String l_strMarginTaxTypeOpenDate = null;
        if (String.valueOf(l_intSpecial).equals(l_strMarginTaxType)
            || String.valueOf(l_intSpecialWithHold).equals(l_strMarginTaxType))
        {
            l_strMarginTaxTypeOpenDate = l_strJapDates[1];
        }

        l_adminInformAccSwitchElecDeliAppDtInfo.marginTaxTypeOpenDate = l_strMarginTaxTypeOpenDate;

        //５）　@生成した口座切替・電子交付申込日付情報オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_adminInformAccSwitchElecDeliAppDtInfo;
    }

    /**
     * (create口座切替・電子交付申込情報)<BR>
     * 口座切替・電子交付申込情報メッセージデータを作成する。<BR>
     * <BR>
     * １） 口座切替・電子交付申込情報オブジェクトを生成する。<BR>
     * <BR>
     * ２） 顧客.getDataSourceObject()にて顧客行を取得する。<BR>
     * <BR>
     * ３） 以下の通り、プロパティをセットする。<BR>
     * <BR>
     * 　@口座切替・電子交付申込情報.モバイル専用口座開設区分 ＝<BR>
     * 　@　@　@　@顧客行.モバイル専用口座開設区分 <BR>
     * 　@口座切替・電子交付申込情報.取引報告書交付区分 ＝ 顧客行.取引報告書交付区分 <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書交付区分 ＝<BR>
     * 　@　@　@　@顧客行.取引残高報告書交付区分 <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書作成周期区分 ＝<BR>
     * 　@　@　@　@顧客行.取引残高報告書作成周期区分 <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書預り証作成フラグ ＝<BR>
     * 　@　@　@　@顧客行.取引残高報告書預り証作成フラグ <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書計算書作成フラグ ＝<BR>
     * 　@　@　@　@顧客行.取引残高報告書計算書作成フラグ <BR>
     * 　@口座切替・電子交付申込情報.税区分 ＝ 顧客行.税区分 <BR>
     * 　@口座切替・電子交付申込情報.税区分（次年） ＝ 顧客行.税区分（次年） <BR>
     * 　@口座切替・電子交付申込情報.信用取引税区分 ＝ 顧客行.信用取引税区分 <BR>
     * 　@　@※顧客行.信用取引税区分 == null の場合、nullをセット <BR>
     * 　@口座切替・電子交付申込情報.信用取引税区分（次年） ＝<BR>
     * 　@　@　@　@顧客行.信用取引税区分（次年）<BR>
     * 　@　@※顧客行.信用取引税区分（次年） == null の場合、nullをセット <BR>
     * 　@口座切替・電子交付申込情報.特定管理口座開設区分 ＝<BR>
     * 　@　@　@　@顧客行.特定管理口座開設区分<BR>
     * <BR>
     * ４） 生成した口座切替・電子交付申込情報オブジェクトを返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createAccSwitchElecDeliApplyInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }

        //１） 口座切替・電子交付申込情報オブジェクトを生成する。
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo =
            new WEB3AdminInformAccSwitchElecDeliApplyInfo();

        //２） 顧客.getDataSourceObject()にて顧客行を取得する。
        MainAccountRow l_mainAcountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //口座切替・電子交付申込情報.モバイル専用口座開設区分 ＝ 顧客行.モバイル専用口座開設区分
        l_changedInfo.mobileAccoutDiv = l_mainAcountRow.getOnlyMobileOpenDiv();

        //口座切替・電子交付申込情報.取引報告書交付区分 ＝ 顧客行.取引報告書交付区分
        l_changedInfo.tradingReportDiv = l_mainAcountRow.getTradingReportDiv();

        //口座切替・電子交付申込情報.取引残高報告書交付区分 ＝ 顧客行.取引残高報告書交付区分
        l_changedInfo.positionReportDiv = l_mainAcountRow.getPositionReportDiv();

        //口座切替・電子交付申込情報.取引残高報告書作成周期区分 ＝ 顧客行.取引残高報告書作成周期区分
        l_changedInfo.positionReportCycleDiv = l_mainAcountRow.getPositionReportCycleDiv();

        //口座切替・電子交付申込情報.取引残高報告書預り証作成フラグ ＝ 顧客行.取引残高報告書預り証作成フラグ
        l_changedInfo.certificateDepositDiv = "" + l_mainAcountRow.getCertificateDepositFlag().intValue();

        //口座切替・電子交付申込情報.取引残高報告書計算書作成フラグ ＝ 顧客行.取引残高報告書計算書作成フラグ
        l_changedInfo.accountStatementDiv = "" + l_mainAcountRow.getAccountStatementFlag().intValue();

        //口座切替・電子交付申込情報.税区分 ＝ 顧客行.税区分
        l_changedInfo.taxType = "" + l_mainAcountRow.getTaxType().intValue();

        //口座切替・電子交付申込情報.税区分（次年） ＝ 顧客行.税区分（次年）
        l_changedInfo.taxTypeNext = "" + l_mainAcountRow.getTaxTypeNext().intValue();

        if (l_mainAcountRow.getMarginTaxType() != null)
        {
            //口座切替・電子交付申込情報.信用取引税区分 ＝ 顧客行.信用取引税区分
            l_changedInfo.marginTaxType = "" + l_mainAcountRow.getMarginTaxType().intValue();
        }

        if (l_mainAcountRow.getMarginTaxTypeNext() != null)
        {
            //口座切替・電子交付申込情報.信用取引税区分（次年） ＝ 顧客行.信用取引税区分（次年）
            l_changedInfo.marginTaxTypeNext = "" + l_mainAcountRow.getMarginTaxTypeNext().intValue();
        }

        //口座切替・電子交付申込情報.特定管理口座開設区分 ＝ 顧客行.特定管理口座開設区分
        l_changedInfo.capitalGainTaxAccOpenDiv = l_mainAcountRow.getSpMngAccOpenDiv();

        //４） 生成した口座切替・電子交付申込情報オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_changedInfo;
    }

    /**
     * (create口座切替・電子交付申込情報)<BR>
     * 口座切替・電子交付申込情報メッセージデータを作成する。 <BR>
     * <BR>
     * １） 口座切替・電子交付申込情報オブジェクトを生成する。 <BR>
     * <BR>
     * ２） 以下の通り、プロパティをセットする。 <BR>
     * <BR>
     * 　@口座切替・電子交付申込情報.モバイル専用口座開設区分 ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分１ <BR>
     * 　@口座切替・電子交付申込情報.取引報告書交付区分 ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分２ <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書交付区分 ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分３ <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書作成周期区分 ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分４ <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書預り証作成フラグ ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分５ <BR>
     * 　@口座切替・電子交付申込情報.取引残高報告書計算書作成フラグ ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分６ <BR>
     * 　@口座切替・電子交付申込情報.税区分    ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分７ <BR>
     * 　@口座切替・電子交付申込情報.税区分（次年）  ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分８ <BR>
     * 　@口座切替・電子交付申込情報.信用取引税区分  ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分９ <BR>
     * 　@口座切替・電子交付申込情報.信用取引税区分（次年）  ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分１０ <BR>
     * 　@口座切替・電子交付申込情報.特定管理口座開設区分 ＝ <BR>
     * 　@　@　@　@（引数）各種連絡行.区分１１ <BR>
     * <BR>
     * 　@※nullの場合、nullをセット <BR>
     * <BR>
     * ３） 生成した口座切替・電子交付申込情報オブジェクトを返却する。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行<BR>
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException 
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAccSwitchElecDeliApplyInfo(VariousInformParams)";
        log.entering(STR_METHOD_NAME);

        if (l_variousInformParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１） 口座切替・電子交付申込情報オブジェクトを生成する。
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_AccSwitchElecDeliApplyInfo =
            new WEB3AdminInformAccSwitchElecDeliApplyInfo();

        //２） 以下の通り、プロパティをセットする。
        // 　@口座切替・電子交付申込情報.モバイル専用口座開設区分 ＝ （引数）各種連絡行.区分１
        l_AccSwitchElecDeliApplyInfo.mobileAccoutDiv = l_variousInformParams.getExtDiv1();
        // 　@口座切替・電子交付申込情報.取引報告書交付区分 ＝ （引数）各種連絡行.区分２
        l_AccSwitchElecDeliApplyInfo.tradingReportDiv = l_variousInformParams.getExtDiv2();
        // 　@口座切替・電子交付申込情報.取引残高報告書交付区分 ＝ （引数）各種連絡行.区分３
        l_AccSwitchElecDeliApplyInfo.positionReportDiv = l_variousInformParams.getExtDiv3();
        // 　@口座切替・電子交付申込情報.取引残高報告書作成周期区分 ＝ （引数）各種連絡行.区分４
        l_AccSwitchElecDeliApplyInfo.positionReportCycleDiv = l_variousInformParams.getExtDiv4();
        // 　@口座切替・電子交付申込情報.取引残高報告書預り証作成フラグ ＝ （引数）各種連絡行.区分５
        l_AccSwitchElecDeliApplyInfo.certificateDepositDiv = l_variousInformParams.getExtDiv5();
        // 　@口座切替・電子交付申込情報.取引残高報告書計算書作成フラグ ＝ （引数）各種連絡行.区分６
        l_AccSwitchElecDeliApplyInfo.accountStatementDiv = l_variousInformParams.getExtDiv6();
        // 　@口座切替・電子交付申込情報.税区分    ＝ （引数）各種連絡行.区分７
        l_AccSwitchElecDeliApplyInfo.taxType = l_variousInformParams.getExtDiv7();
        // 　@口座切替・電子交付申込情報.税区分（次年）  ＝ （引数）各種連絡行.区分８
        l_AccSwitchElecDeliApplyInfo.taxTypeNext = l_variousInformParams.getExtDiv8();
        // 　@口座切替・電子交付申込情報.信用取引税区分  ＝ （引数）各種連絡行.区分９
        l_AccSwitchElecDeliApplyInfo.marginTaxType = l_variousInformParams.getExtDiv9();
        // 　@口座切替・電子交付申込情報.信用取引税区分（次年）  ＝ （引数）各種連絡行.区分１０
        l_AccSwitchElecDeliApplyInfo.marginTaxTypeNext = l_variousInformParams.getExtDiv10();
        // 　@口座切替・電子交付申込情報.特定管理口座開設区分 ＝ （引数）各種連絡行.区分１１
        l_AccSwitchElecDeliApplyInfo.capitalGainTaxAccOpenDiv = l_variousInformParams.getExtDiv11();

        //３） 生成した口座切替・電子交付申込情報オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_AccSwitchElecDeliApplyInfo;
    }

    /**
     * (create取報・取残電子交付・特定口座登録行)<BR>
     * 取報・取残電子交付・特定口座登録新規行を生成する。 <BR>
     * <BR>
     * １）　@行オブジェクト生成 <BR>
     * 　@取報・取残電子交付・特定口座登録Paramsオブジェクトを生成する。 <BR>
     * <BR>
     * 　@※取報・取残電子交付・特定口座登録ParamsはDDLより自動生成する。 <BR>
     * <BR>
     * ２）　@顧客.getDataSourceObject()にて変更前情報を取得する。  <BR>
     * <BR>
     * ３）　@行オブジェクトにプロパティをセットする。  <BR>
     * 　@１）で生成した取報・取残電子交付・特定口座登録Paramsオブジェクトの<BR>
     * 　@　@　@プロパティに、以下の通りセットを行う。 <BR>
     * <BR>
     * 　@取報・取残電子交付・特定口座登録Params.証券会社コード ＝<BR>
     * 　@　@　@　@ (引数)各種連絡行.証券会社コード <BR>
     * 　@取報・取残電子交付・特定口座登録Params.部店コード ＝<BR>
     * 　@　@　@　@ (引数)各種連絡行.部店コード <BR>
     * 　@取報・取残電子交付・特定口座登録Params.顧客コード ＝<BR>
     * 　@　@　@　@ (引数)各種連絡行.顧客コード <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@定期 ＝  <BR>
     * 　@　@顧客行.取引残高報告書作成周期区分 != (引数)各種連絡行.区分４ の場合、<BR>
     * 　@　@　@　@(引数)各種連絡行.区分４ <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@電子交付（都度） ＝ <BR>
     * 　@　@顧客行.取引残高報告書交付区分 != (引数)各種連絡行.区分３ の場合、<BR>
     * 　@　@　@　@(引数)各種連絡行.区分３ <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@預り証 ＝ <BR>
     * 　@　@顧客行.取引残高報告書預り証作成フラグ != (引数)各種連絡行.区分５ の場合、<BR>
     * 　@　@　@　@(引数)各種連絡行.区分５ <BR>
     * 　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@計算書 ＝  <BR>
     * 　@　@顧客行.取引残高報告書計算書作成フラグ != (引数)各種連絡行.区分６ の場合、<BR>
     * 　@　@　@　@(引数)各種連絡行.区分６ <BR>
     * 　@取報・取残電子交付・特定口座登録Params.電子交付　@取引報告書 ＝  <BR>
     * 　@　@顧客行.取引報告書交付区分 != (引数)各種連絡行.区分２ の場合、<BR>
     * 　@　@　@　@(引数)各種連絡行.区分２ <BR>
     * 　@顧客行.税区分 != (引数)各種連絡行.区分７ の場合、以下をセットする。 <BR>
     * 　@　@①@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@今回 ＝ <BR>
     * 　@　@　@－(引数)各種連絡行.区分７ ＝ 0 の場合、null <BR>
     * 　@　@　@－(引数)各種連絡行.区分７ ＝ 1 の場合、0：一般 <BR>
     * 　@　@　@－(引数)各種連絡行.区分７ ＝ 2 の場合、1：特定・源泉なし <BR>
     * 　@　@　@－(引数)各種連絡行.区分７ ＝ 3 の場合、2：特定・源泉あり <BR>
     * 　@　@②取報・取残電子交付・特定口座登録Params.（現物）特定口座　@開設日 ＝ <BR>
     * 　@　@　@　@(引数)各種連絡行.テキスト３ <BR>
     * 　@顧客行.税区分（次年） != (引数)各種連絡行.区分８ の場合、<BR>
     * 　@以下をセットする。 <BR>
     * 　@　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@次回 ＝ <BR>
     * 　@　@　@－(引数)各種連絡行.区分８ ＝ 0 の場合、null <BR>
     * 　@　@　@－(引数)各種連絡行.区分８ ＝ 1 の場合、0：一般 <BR>
     * 　@　@　@－(引数)各種連絡行.区分８ ＝ 2 の場合、1：特定・源泉なし <BR>
     * 　@　@　@－(引数)各種連絡行.区分８ ＝ 3 の場合、2：特定・源泉あり <BR>
     * 　@顧客行.信用取引税区分 != (引数)各種連絡行.区分９ の場合、<BR>
     * 　@以下をセットする。 <BR>
     * 　@　@①@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@今回 ＝  <BR>
     * 　@　@　@－(引数)各種連絡行.区分９ ＝ 0 の場合、null <BR>
     * 　@　@　@－(引数)各種連絡行.区分９ ＝ 1 の場合、0：一般 <BR>
     * 　@　@　@－(引数)各種連絡行.区分９ ＝ 2 の場合、1：特定・源泉なし <BR>
     * 　@　@　@－(引数)各種連絡行.区分９ ＝ 3 の場合、2：特定・源泉あり <BR>
     * 　@　@②取報・取残電子交付・特定口座登録Params.（信用）特定口座　@開設日 ＝ <BR>
     * 　@　@　@　@(引数)各種連絡行.テキスト４ <BR>
     * 　@顧客行.信用取引税区分（次年） != (引数)各種連絡行.区分１０ の場合、<BR>
     * 　@以下をセットする。 <BR>
     * 　@　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@次回 ＝  <BR>
     * 　@　@　@－(引数)各種連絡行.区分１０ ＝ 0 の場合、null <BR>
     * 　@　@　@－(引数)各種連絡行.区分１０ ＝ 1 の場合、0：一般 <BR>
     * 　@　@　@－(引数)各種連絡行.区分１０ ＝ 2 の場合、<BR>
     * 　@　@　@　@1：特定・源泉なし <BR>
     * 　@　@　@－(引数)各種連絡行.区分１０ ＝ 3 の場合、<BR>
     * 　@　@　@　@2：特定・源泉あり <BR>
     * 　@取報・取残電子交付・特定口座登録Params.特定管理口座 ＝  <BR>
     * 　@　@顧客行.特定管理口座開設区分 != (引数)各種連絡行.区分１１ の場合、<BR>
     * 　@　@　@　@(引数)各種連絡行.区分１１ <BR>
     * <BR>
     * 　@※条件に該当しない場合はnullをセットする。 <BR>
     * <BR>
     * ４）　@生成した行オブジェクトを返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行<BR>
     * @@return HostConditionRegVoucherParams
     * @@throws WEB3BaseException
     */
    public HostConditionRegVoucherParams createHostConditionRegVoucherParams(
        MainAccount l_mainAccount,
        VariousInformParams l_variousInformParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createHostConditionRegVoucherParams(MainAccount, VariousInformParams)";
        log.entering(STR_METHOD_NAME);

        if (l_variousInformParams == null || l_mainAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@行オブジェクト生成
        //　@取報・取残電子交付・特定口座登録Paramsオブジェクトを生成する。
        //　@※取報・取残電子交付・特定口座登録ParamsはDDLより自動生成する。
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            new HostConditionRegVoucherParams();
        
        //２）　@顧客.getDataSourceObject()にて変更前情報を取得する。
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        //３）　@行オブジェクトにプロパティをセットする。
        //　@１）で生成した取報・取残電子交付・特定口座登録Paramsオブジェクトの
        //プロパティに、以下の通りセットを行う。
        //　@取報・取残電子交付・特定口座登録Params.証券会社コード ＝
        //(引数)各種連絡行.証券会社コード
        l_hostConditionRegVoucherParams.setInstitutionCode(l_variousInformParams.getInstitutionCode());
 
        //　@取報・取残電子交付・特定口座登録Params.部店コード ＝ (引数)各種連絡行.部店コード
        l_hostConditionRegVoucherParams.setBranchCode(l_variousInformParams.getBranchCode());

        //　@取報・取残電子交付・特定口座登録Params.顧客コード ＝ (引数)各種連絡行.顧客コード
        l_hostConditionRegVoucherParams.setAccountCode(l_variousInformParams.getAccountCode());

        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@定期 ＝
        //　@　@顧客行.取引残高報告書作成周期区分 != (引数)各種連絡行.区分４ の場合、
        //    (引数)各種連絡行.区分４
        if (!l_mainAccountRow.getPositionReportCycleDiv().equals(l_variousInformParams.getExtDiv4()))
        {
            l_hostConditionRegVoucherParams.setPosReportTermDiv(l_variousInformParams.getExtDiv4());
        }

        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@電子交付（都度） ＝
        //　@　@顧客行.取引残高報告書交付区分 != (引数)各種連絡行.区分３ の場合、
        //    (引数)各種連絡行.区分３
        if (!l_mainAccountRow.getPositionReportDiv().equals(l_variousInformParams.getExtDiv3()))
        {
            l_hostConditionRegVoucherParams.setPosReportCycleDiv(l_variousInformParams.getExtDiv3());
        }

        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@預り証 ＝
        //　@　@顧客行.取引残高報告書預り証作成フラグ != (引数)各種連絡行.区分５ の場合、
        //    (引数)各種連絡行.区分５
        String l_strCertificateDepositFlag = String.valueOf(l_mainAccountRow.getCertificateDepositFlag().intValue());
        if (!l_strCertificateDepositFlag.equals(l_variousInformParams.getExtDiv5()))
        {
            l_hostConditionRegVoucherParams.setPosReportCertifDepoDiv(l_variousInformParams.getExtDiv5());
        }

        //　@取報・取残電子交付・特定口座登録Params.取引残高報告書　@計算書 ＝
        //　@　@顧客行.取引残高報告書計算書作成フラグ != (引数)各種連絡行.区分６ の場合、
        //    (引数)各種連絡行.区分６
        String l_strAccountStatementFlag = String.valueOf(l_mainAccountRow.getAccountStatementFlag().intValue());
        if (!l_strAccountStatementFlag.equals(l_variousInformParams.getExtDiv6()))
        {
            l_hostConditionRegVoucherParams.setPosReportAccStateDiv(l_variousInformParams.getExtDiv6());
        }

        //　@取報・取残電子交付・特定口座登録Params.電子交付　@取引報告書 ＝
        //　@　@顧客行.取引報告書交付区分 != (引数)各種連絡行.区分２ の場合、(引数)各種連絡行.区分２
        if (!l_mainAccountRow.getTradingReportDiv().equals(l_variousInformParams.getExtDiv2()))
        {
            l_hostConditionRegVoucherParams.setTradingEReportDiv(l_variousInformParams.getExtDiv2());
        }

        //　@顧客行.税区分 != (引数)各種連絡行.区分７ の場合、以下をセットする。
        String l_strTaxType = String.valueOf(l_mainAccountRow.getTaxType().intValue());
        if (!l_strTaxType.equals(l_variousInformParams.getExtDiv7()))
        {
            //　@　@①@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@今回 ＝
            //　@　@　@－(引数)各種連絡行.区分７ ＝ 0 の場合、null
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(null);
            }
            //　@　@　@－(引数)各種連絡行.区分７ ＝ 1 の場合、0：一般
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(WEB3TaxTypeDivDef.NORMAL);
            }
            //　@　@　@－(引数)各種連絡行.区分７ ＝ 2 の場合、1：特定・源泉なし
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //　@　@　@－(引数)各種連絡行.区分７ ＝ 3 の場合、2：特定・源泉あり
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv7()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDiv(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }

            //　@　@②取報・取残電子交付・特定口座登録Params.（現物）特定口座　@開設日 ＝ (引数)各種連絡行.テキスト３
            l_hostConditionRegVoucherParams.setEquitySpAccOpenDat(l_variousInformParams.getExtText3());
        }

        //　@顧客行.税区分（次年） != (引数)各種連絡行.区分８ の場合、以下をセットする。
        String l_strTaxTypeNext = String.valueOf(l_mainAccountRow.getTaxTypeNext().intValue());
        if (!l_strTaxTypeNext.equals(l_variousInformParams.getExtDiv8()))
        {
            //　@　@取報・取残電子交付・特定口座登録Params.（現物）特定口座　@次回 ＝
            //　@　@　@－(引数)各種連絡行.区分８ ＝ 0 の場合、null
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(null);
            }
            //　@　@　@－(引数)各種連絡行.区分８ ＝ 1 の場合、0：一般
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(WEB3TaxTypeDivDef.NORMAL);
            }
            //　@　@　@－(引数)各種連絡行.区分８ ＝ 2 の場合、1：特定・源泉なし
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //　@　@　@－(引数)各種連絡行.区分８ ＝ 3 の場合、2：特定・源泉あり
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv8()))
            {
                l_hostConditionRegVoucherParams.setEquityTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }
        }

        //　@顧客行.信用取引税区分 != (引数)各種連絡行.区分９ の場合、以下をセットする。
        String l_strMarginTaxType = null;
        if (l_mainAccountRow.getMarginTaxType() != null)
        {
            l_strMarginTaxType = String.valueOf(l_mainAccountRow.getMarginTaxType().intValue());
        }
        if (!WEB3Toolkit.isEquals(l_strMarginTaxType, l_variousInformParams.getExtDiv9()))
        {
            //　@　@①@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@今回 ＝
            //　@    －(引数)各種連絡行.区分９ ＝ 0 の場合、null
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(null);
            }
            //　@　@　@－(引数)各種連絡行.区分９ ＝ 1 の場合、0：一般
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(WEB3TaxTypeDivDef.NORMAL);
            }
            //　@　@　@－(引数)各種連絡行.区分９ ＝ 2 の場合、1：特定・源泉なし
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //　@　@　@－(引数)各種連絡行.区分９ ＝ 3 の場合、2：特定・源泉あり
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv9()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDiv(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }
            
            //　@　@②取報・取残電子交付・特定口座登録Params.（信用）特定口座　@開設日 ＝ (引数)各種連絡行.テキスト４
            l_hostConditionRegVoucherParams.setMarginSpAccOpenDat(l_variousInformParams.getExtText4());
        }

        //　@顧客行.信用取引税区分（次年） != (引数)各種連絡行.区分１０ の場合、以下をセットする。
        String l_strMarginTaxTypeNext = null;
        if (l_mainAccountRow.getMarginTaxTypeNext() != null)
        {
            l_strMarginTaxTypeNext = String.valueOf(l_mainAccountRow.getMarginTaxTypeNext().intValue());
        }
        if (!WEB3Toolkit.isEquals(l_strMarginTaxTypeNext, l_variousInformParams.getExtDiv10()))
        {
            //　@　@取報・取残電子交付・特定口座登録Params.（信用）特定口座　@次回 ＝
            //　@　@　@－(引数)各種連絡行.区分１０ ＝ 0 の場合、null
            if (WEB3InformTaxTypeDef.OTHER.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(null);
            }
            //　@　@　@－(引数)各種連絡行.区分１０ ＝ 1 の場合、0：一般
            else if (WEB3InformTaxTypeDef.NORMAL.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(WEB3TaxTypeDivDef.NORMAL);
            }
            //　@　@　@－(引数)各種連絡行.区分１０ ＝ 2 の場合、1：特定・源泉なし
            else if (WEB3InformTaxTypeDef.SPECIAL.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE);
            }
            //　@　@　@－(引数)各種連絡行.区分１０ ＝ 3 の場合、2：特定・源泉あり
            else if (WEB3InformTaxTypeDef.SPECIAL_WITHHOLD.equals(l_variousInformParams.getExtDiv10()))
            {
                l_hostConditionRegVoucherParams.setMarginTaxDivNext(WEB3TaxTypeDivDef.SPECIAL_SOURCE);
            }
        }

        //　@取報・取残電子交付・特定口座登録Params.特定管理口座 ＝
        //　@　@顧客行.特定管理口座開設区分 != (引数)各種連絡行.区分１１ の場合、(引数)各種連絡行.区分１１
        if (!WEB3Toolkit.isEquals(l_mainAccountRow.getSpMngAccOpenDiv(),
            l_variousInformParams.getExtDiv11()))
        {
            l_hostConditionRegVoucherParams.setSpMngAccOpenDiv(l_variousInformParams.getExtDiv11());
        }

        log.exiting(STR_METHOD_NAME);
        return l_hostConditionRegVoucherParams;
    }

    /**
     * (トリガ発行)<BR>
     * トリガを発行する。  <BR>
     * <BR>
     * １）WEB3MQMessageSpecを生成する。   <BR>
     * <BR>
     *    WEB3MQMessageSpec(証券会社コード, データコード,ユーザデータ)  <BR>
     * <BR>
     *    [引数]   <BR>
     *    証券会社コード：　@引数.証券会社コード  <BR>
     *    データコード：　@引数.データコード + "T" <BR>
     *    ユーザデータ：　@"２：客区分（既存客）" <BR>
     * <BR>
     * ２）MQトリガを発行する。  <BR>
     * <BR>
     *    WEB3MQGatewayServiceImpl.send(MQメッセージ内容)  <BR>
     * <BR>
     *    [引数]  <BR>
     *    MQメッセージ内容： 生成したWEB3MQMessageSpec  <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strDataCode - (データコード)<BR>
     * データコード<BR>
     */
    public void submitMarketTrigger(String l_strInstitutionCode, String l_strDataCode)
    {
        final String STR_METHOD_NAME = "submitMarketTrigger(String, String)";
        log.entering(STR_METHOD_NAME);

        // １）WEB3MQMessageSpecを生成する。
        // WEB3MQMessageSpec(証券会社コード, データコード,ユーザデータ)
        //    証券会社コード：　@引数.証券会社コード
        //    データコード：　@引数.データコード + "T"
        //    ユーザデータ：　@"２：客区分（既存客）"
        WEB3MQMessageSpec l_mqMessageSpec =
            new WEB3MQMessageSpec(l_strInstitutionCode,
                l_strDataCode + "T",
                WEB3InformUserDataDef.EXISTING_ACCOUNT);

        // ２）MQトリガを発行する。
        WEB3MQGatewayService l_mqGatewayService =
            (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
        log.debug("トリガを発行する........");
        l_mqGatewayService.send(l_mqMessageSpec);
        log.debug("トリガを発行する........OK!");
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate伝票作成)<BR>
     * 伝票作成可能かチェックを行う。 <BR>
     * <BR>
     * １）　@引数.トリガー発行区分 == true の場合 <BR>
     * 　@１－１）　@引数.作成状況が、0：未作成、3：受付完了、4：受付エラー <BR>
     * 　@　@　@　@のいずれでもない場合、 <BR>
     * 　@　@　@　@　@　@例外をスロー <BR>
     * 　@　@　@　@　@　@「既に伝票が作成済みのため、オンライン中は伝票作成が行えません。」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02786<BR>
     * <BR>
     * ２）　@引数.トリガー発行区分 == false の場合　@ <BR>
     * 　@２－１）　@引数.作成状況が、0：未作成、2：受付中、3：受付完了、<BR>
     * 　@　@　@　@4：受付エラー のいずれでもない場合、 <BR>
     * 　@　@　@　@　@　@例外をスロー <BR>
     * 　@　@　@　@　@　@「伝票が処理済みのため伝票作成が行えません。」 <BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherMake(String, boolean)";
        log.entering(STR_METHOD_NAME);

        //１）　@引数.トリガー発行区分 == true の場合
        if (l_blnSubmitMarketTriggerDiv)
        {
            //１－１）　@引数.作成状況が、0：未作成、3：受付完了、4：受付エラー
            //のいずれでもない場合
            //例外をスロー 
            //「既に伝票が作成済みのため、オンライン中は伝票作成が行えません。」
            if (!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus)
                && (!WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
            }
        }
        //２）　@引数.トリガー発行区分 == false の場合
        else
        {
            //２－１）　@引数.作成状況が、0：未作成、2：受付中、3：受付完了、
            //4：受付エラー のいずれでもない場合、
            //例外をスロー 「伝票が処理済みのため伝票作成が行えません。」
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.INT_ACCEPT.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("伝票が処理済みのため伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "伝票が処理済みのため伝票作成が行えません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate伝票作成)<BR>
     * 伝票作成可能かチェックを行う。 <BR>
     * <BR>
     * １）　@引数.トリガー発行区分 == true の場合 <BR>
     * 　@１－１）　@引数.作成状況が、1：作成済 or <BR>
     * 　@　@(引数.作成状況が、2：受付中 and  引数.開始予定日 >= 現在日付) の場合、 <BR>
     * 　@　@　@　@　@　@例外をスロー <BR>
     * 　@　@　@　@　@　@「既に伝票が作成済みのため、オンライン中は伝票作成が行えません。」　@ <BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02786<BR>
     * <BR>
     * ２）　@引数.トリガー発行区分 == false の場合　@ <BR>
     * 　@２－１）　@引数.作成状況が、0：未作成、2：受付中、3：受付完了、<BR>
     * 　@　@　@　@　@　@4：受付エラー のいずれでもない場合、 <BR>
     * 　@　@　@　@　@　@例外をスロー <BR>
     * 　@　@　@　@　@　@「伝票が処理済みのため伝票作成が行えません。」<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@param l_datStartScheduleDate - (開始予定日)<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(
            String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv, Date l_datStartScheduleDate)
                throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherMake(String, boolean, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_datStartScheduleDate == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@引数.トリガー発行区分 == true の場合
        if (l_blnSubmitMarketTriggerDiv)
        {
            //引数.作成状況が、1：作成済 or (引数.作成状況が、2：受付中 and  引数.開始予定日 >= 現在日付) の場合、
            //例外をスロー 
            //「既に伝票が作成済みのため、オンライン中は伝票作成が行えません。」
            if ((WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)) 
                || (WEB3VoucherCreateStatusDef.INT_ACCEPT.equals(l_strMakeStatus) 
                && (WEB3DateUtility.compareToDay(l_datStartScheduleDate, GtlUtils.getSystemTimestamp()) >= 0)))
            {
                log.debug("既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
            }
        }
        //２）　@引数.トリガー発行区分 == false の場合
        else
        {
            //２－１）　@引数.作成状況が、0：未作成、2：受付中、3：受付完了、
            //4：受付エラー のいずれでもない場合、
            //例外をスロー 「伝票が処理済みのため伝票作成が行えません。」
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.INT_ACCEPT.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("伝票が処理済みのため伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "伝票が処理済みのため伝票作成が行えません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate伝票変更)<BR>
     * 伝票変更が可能かチェックを行う。 <BR>
     * <BR>
     * １）　@引数.トリガー発行区分 == true の場合 <BR>
     * 　@１－１）　@引数.作成状況が、0：未作成、4：受付エラー のいずれでもない場合、<BR>
     * 　@　@　@　@　@　@例外をスロー <BR>
     * 　@　@　@　@　@　@「既に伝票が作成済みのため、<BR>
     * 　@　@　@　@　@　@　@オンライン中は伝票作成が行えません。」　@ <BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02786<BR>
     * <BR>
     * ２）　@引数.トリガー発行区分 == false の場合　@ <BR>
     * 　@２－１）　@引数.作成状況が、0：未作成、1：作成済み、<BR>
     * 　@　@　@　@4：受付エラー のいずれでもない場合、 <BR>
     * 　@　@　@　@　@　@例外をスロー <BR>
     * 　@　@　@　@　@　@「伝票が処理済みのため伝票作成が行えません。」　@<BR>
     * 　@　@　@　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02787<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherChange(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherChange(String, boolean)";
        log.entering(STR_METHOD_NAME);

        // １）　@引数:トリガー発行区分 == true の場合
        if (l_blnSubmitMarketTriggerDiv)
        {
            // １－１）　@作成状況が、0：未作成、4：受付エラー のいずれでもない場合、
            // 例外をスロー「既に伝票が作成済みのため、オンライン中は伝票作成が行えません。」
            if (!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus)
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02786,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");
            }
        }
        // ２）　@引数:トリガー発行区分 == false の場合
        else
        {
            // ２－１）　@引数.作成状況が、0：未作成、1：作成済み、4：受付エラー のいずれでもない場合、
            // 例外をスロー「伝票が処理済みのため伝票作成が行えません。」
            if ((!WEB3VoucherCreateStatusDef.NOT_CREATE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus))
                && (!WEB3VoucherCreateStatusDef.ACCEPT_ERROR.equals(l_strMakeStatus)))
            {
                log.debug("伝票が処理済みのため伝票作成が行えません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02787,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "伝票が処理済みのため伝票作成が行えません。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate伝票取消)<BR>
     * 伝票取消可能かチェックを行う。 <BR>
     * <BR>
     * １）　@引数.トリガー発行区分 == true or 引数.作成状況 != 1：作成済 の場合、<BR>
     * 　@　@　@例外をスロー<BR>
     * 　@　@　@classes:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02798<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherCancel(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateVoucherCancel(String, boolean)";
        log.entering(STR_METHOD_NAME);
        //１）　@引数.トリガー発行区分 == true or 引数.作成状況 != 1：作成済 の場合、
        // 例外をスロー
        if (l_blnSubmitMarketTriggerDiv
            || (!WEB3VoucherCreateStatusDef.CREATE_COMPLETE.equals(l_strMakeStatus)))
        {
            log.debug("オンライン中または伝票未作成または伝票送信済みの場合、取消不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02798,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "オンライン中または伝票未作成または伝票送信済みの場合、取消不可。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get各種連絡一覧)<BR>
     * 以下の検索条件から取得した各種連絡レコードをList型で返却する。 <BR>
     * <BR>
     * １）　@各種連絡テーブルの検索 <BR>
     * [検索条件] <BR>
     * 証券会社コード： 引数.証券会社コード <BR>
     * 連絡種別： 引数.連絡種別 <BR>
     * 部店コード： 引数.部店コード <BR>
     * 顧客コード： 引数.顧客コード <BR>
     * ※（顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。 <BR>
     * <BR>
     * [ソート条件] <BR>
     * 更新日時（降順） <BR>
     * <BR>
     * * レコードが見つからない場合、nullを返却 <BR>
     * <BR>
     * ２）検索結果を返却<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strInformType - (連絡種別)<BR>
     * 連絡種別<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getVariousInformList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strInformType,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getVariousInformList(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRows = null;

        //１）　@各種連絡テーブルの検索
        //[検索条件]
        //証券会社コード： 引数.証券会社コード
        //連絡種別： 引数.連絡種別
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //※（顧客コード.length() == 6）の場合は、最初の6byteのみ比較する。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and inform_div = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and account_code like ? || '%' ");

        Object[] l_objQueryContainers =
            {l_strInstitutionCode, l_strInformType, l_strBranchCode, l_strAccountCode};

        //[ソート条件]
        //更新日時（降順）
        String l_sortKey = " last_updated_timestamp desc ";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_sbWhere.toString(),
                l_sortKey,
                null,
                l_objQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //* レコードが見つからない場合、nullを返却
        if (l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）検索結果を返却
        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }
}
@
