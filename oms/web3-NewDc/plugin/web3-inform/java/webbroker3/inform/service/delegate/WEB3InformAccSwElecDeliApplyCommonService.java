head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込共通サービスインターフェイス(WEB3InformAccSwElecDeliApplyCommonService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 孟亜南(中訊) モデル・No.100
Revision History : 2007/08/30 金傑(中訊) モデル・No.107
Revision History : 2007/09/19 張騰宇(中訊) モデル・No.109
*/
package webbroker3.inform.service.delegate;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.WEB3Inform;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;

/**
 * (口座切替・電子交付申込共通サービスインターフェイス)<BR>
 * 口座切替・電子交付申込共通サービスインターフェイス<BR>
 *
 * @@author 孟亜南
 * @@version 1.0
 */
public interface WEB3InformAccSwElecDeliApplyCommonService extends Service
{
    /**
     * (validate口座切替・電子交付申込情報)<BR>
     * 口座切替・電子交付申込情報の変更内容をチェックする。<BR>
     * <BR>
     * @@param l_beforeChangedInfo - (変更前情報)<BR>
     * 口座切替・電子交付申込情報オブジェクト
     * @@param l_changedInfo - (変更後情報)<BR>
     * 口座切替・電子交付申込情報オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateAccSwitchElecDeliApplyInfo(
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_beforeChangedInfo,
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo) throws WEB3BaseException;

    /**
     * (create各種連絡)<BR>
     * 各種連絡新規行を生成する。<BR>
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
        String l_strInformType) throws WEB3BaseException;

    /**
     * (create口座切替・電子交付申込日付情報)<BR>
     * 口座切替・電子交付申込日付情報メッセージデータを作成する。<BR>
     *
     * @@param l_strTaxType - (税区分)<BR>
     * 税区分
     * @@param l_strMarginTaxType - (信用取引税区分)<BR>
     * 信用取引税区分
     * @@return WEB3AdminInformAccSwitchElecDeliAppDtInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo createAccSwitchElecDeliAppDtInfo(
        String l_strTaxType,
        String l_strMarginTaxType) throws WEB3BaseException;

    /**
     * (create口座切替・電子交付申込情報)<BR>
     * 口座切替・電子交付申込情報メッセージデータを作成する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        MainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (create口座切替・電子交付申込情報)<BR>
     * 口座切替・電子交付申込情報メッセージデータを作成する。<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行<BR>
     * @@return WEB3AdminInformAccSwitchElecDeliApplyInfo
     * @@throws WEB3BaseException
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo createAccSwitchElecDeliApplyInfo(
        VariousInformParams l_variousInformParams) throws WEB3BaseException;

    /**
     * (create取報・取残電子交付・特定口座登録行)<BR>
     * 取報・取残電子交付・特定口座登録新規行を生成する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_variousInformParams - (各種連絡行)<BR>
     * 各種連絡行<BR>
     * @@throws WEB3BaseException
     */
    public HostConditionRegVoucherParams createHostConditionRegVoucherParams(
        MainAccount l_mainAccount,
        VariousInformParams l_variousInformParams) throws WEB3BaseException;

    /**
     * (トリガ発行)<BR>
     * トリガを発行する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strDataCode - (データコード)<BR>
     * データコード<BR>
     */
    public void submitMarketTrigger(String l_strInstitutionCode, String l_strDataCode);

    /**
     * (validate伝票作成)<BR>
     * 伝票作成可能かチェックを行う。<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException;

    /**
     * (validate伝票作成)<BR>
     * 伝票作成可能かチェックを行う。<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@param l_datStartScheduleDate - (開始予定日)<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherMake(
        String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv, Date l_datStartScheduleDate)
            throws WEB3BaseException;

    /**
     * (validate伝票変更)<BR>
     * 伝票変更が可能かチェックを行う。<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherChange(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException;

    /**
     * (validate伝票取消)<BR>
     * 伝票取消可能かチェックを行う。<BR>
     * @@param l_strMakeStatus - (作成状況)<BR>
     * 作成状況<BR>
     * @@param l_blnSubmitMarketTriggerDiv - (トリガー発行区分)<BR>
     * トリガー発行区分<BR>
     * @@throws WEB3BaseException
     */
    public void validateVoucherCancel(String l_strMakeStatus, boolean l_blnSubmitMarketTriggerDiv)
        throws WEB3BaseException;

    /**
     * (get各種連絡一覧)<BR>
     * 以下の検索条件から取得した各種連絡レコードをList型で返却する。<BR>
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
        String l_strAccountCode) throws WEB3BaseException;
}
@
