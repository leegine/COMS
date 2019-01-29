head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankSettleControlService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : マルチバンク決済制御サービス(WEB3AioMultiBankSettleControlService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/22 韋念瓊 (中訊) レビュー
                   2006/04/13 肖志偉 (中訊) 仕様変更・モデル538
                   2006/04/13 肖志偉 (中訊) 仕様変更・モデル540
*/
package webbroker3.aio;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import webbroker3.aio.message.WEB3AioPrSessionUnit;
import webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (マルチバンク決済制御サービス)<BR>
 * マルチバンク決済制御サービスインターフェイス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public interface WEB3AioMultiBankSettleControlService extends Service
{

    /**
     * (get選択決済機@関明細)<BR>
     * 該当する証券会社、部店で取扱っている決済機@関の一覧と受付状況を取得する。
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strCareerDiv - (キャリア区分)
     * @@return WEB3AioSelectSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A6027A
     */
    public WEB3AioSelectSettleInstitutionUnit[] getSelectPaySchemeDetails(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strCareerDiv)
        throws WEB3BaseException;

    /**
     * (get提携決済機@関明細)<BR>
     * 該当する証券会社、部店で取扱っている決済機@関の一覧を取得する。
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@return WEB3AioSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A60355
     */
    public WEB3AioSettleInstitutionUnit[] getAffiliatedPaySchemeDetails(
                String l_strInstitutionCode)
        throws WEB3BaseException;

    /**
     * (get入出金状況)<BR>
     * オンライン入出金の状況を取得する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * @@param l_OrderStatus - (注文状態)<BR>
     * @@param l_strOrderCancleStatus - (注文取消区分)<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A602C8
     */
    public String getCashTransSituation(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber,
        OrderStatusEnum l_OrderStatus,
        String l_strOrderCancleStatus)
        throws WEB3BaseException;

    /**
     * (get金融機@関名)<BR>
     * オンライン入金の決済機@関の名称を取得する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A60307
     */
    public String getFinInstitutionName(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber)
        throws WEB3BaseException;

    // =========remian zhou-yong NO.1 begin ========
    
    /**
     * (create決済依頼URL)<BR>
     * 決済依頼の際に返却するリダイレクトURLの文字列を生成する。
     * @@param l_prSessionUnit - (PR層保持情報)
     * @@param l_subAccount - (補助口座)<BR><BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)<BR>
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A603A3
     */
    public String createSettlementRequestURL(
        WEB3AioPrSessionUnit l_prSessionUnit,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strOrderRequestNumber)
        throws WEB3BaseException;

    // =========remian zhou-yong NO.1 end ========
    
    /**
     * (insert入出金状況)<BR>
     * 決済依頼のステータスでの金融機@関連携入出金状況テーブルのレコードを生成する。
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_strPaySchemeId - (決済機@関ID)<BR>
     * @@param l_strNetAmount - (金額)<BR>
     * 入金金額<BR>
     * @@param l_datBizDate - (発注日)
     * @@param l_strOrderRequestNumber - (識別コード)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411721A6023B
     */
    public void insertCashTransSituation(
        Trader l_trader,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strNetAmount,
        Date l_datBizDate,
        String l_strOrderRequestNumber)
        throws WEB3BaseException;

    /**
     * (validate受信電文)<BR>
     * 受信電文のチェックを行う。<BR>
     * @@param l_receiptTelegramData - (受信電文データ)<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD810244
     */
    public String validateReceiptTelegram(
        HashMap l_receiptTelegramData,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus);

    /**
     * (create送信電文)<BR>
     * 送信電文を生成する。
     * @@param l_response - (レスポンスデータ)
     * @@param l_strSendTelegramDatas - (送信電文データ)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD8103AB
     */
    public void createSendTelegram(
        HttpServletResponse l_response,
        String[] l_strSendTelegramDatas)
        throws IOException;

    /**
     * (insert注文情報要求)<BR>
     * 注文情報要求テーブルにレコードを追加する。<BR>
     * @@param l_receiptTelegramData - (受信電文データ)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD82010B
     */
    public void insertOrderInfoRequire(HashMap l_receiptTelegramData)
        throws WEB3BaseException;

    /**
     * (insert決済開始要求)<BR>
     * 決済開始要求テーブルにレコードを追加する。
     * @@param l_receiptTelegramData - (受信電文データ)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119CF14038C
     */
    public void insertSettleStartRequire(HashMap l_receiptTelegramData)
        throws WEB3BaseException;

    /**
     * (insert決済結果通知)<BR>
     * 決済結果通知テーブルにレコードを追加する。
     * @@param l_ReceiptTelegramData - (受信電文データ)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119F79E033E
     */
    public void insertSettleResultNotify(HashMap l_ReceiptTelegramData)
        throws WEB3BaseException;

    /**
     * (update注文要求受付)<BR>
     * 金融機@関連携入出金状況テーブルを注文受付時の状態に更新する。<BR>
     * @@param l_strReturnCode - (受信電文のチェック結果)<BR>
     * @@param l_strComDebitNumber - (.comデビット決済取引番号)<BR>
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 4119AD8201F5
     */
    public void updateOrderRequireAccept(
        String l_strReturnCode,
        String l_strComDebitNumber,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
        throws WEB3BaseException;

    /**
     * (update注文要求応答)<BR>
     * 金融機@関連携入出金状況テーブルを注文受付応答時の状態に更新する。
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 4119AD8203CA
     */
    public void updateOrderRequireResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update注文要求中止)<BR>
     * 金融機@関連携入出金状況テーブルを注文受付中止時の状態に更新する。
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 4119ADF901E6
     */
    public void updateOrderRequireDiscontinuation(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update決済開始受付)<BR>
     * 金融機@関連携入出金状況テーブルを決済開始受付時の状態に更新する。
     * @@param l_strReturnCode - (受信電文のチェック結果)
     * @@param l_cashTransSituation - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況オブジェクト
     * @@roseuid 4119CDB700CD
     */
    public void updateSettleStartAccept(
        String l_strReturnCode,
        WEB3AioFinInstitutionCashTransStatus l_cashTransSituation)
        throws WEB3BaseException;

    /**
     * (update決済開始応答)<BR>
     * 金融機@関連携入出金状況テーブルを決済開始応答時の状態に更新する。
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 4119D031030F
     */
    public void updateSettleStartResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update決済結果通知)<BR>
     * 金融機@関連携入出金状況テーブルを決済結果通知時の状態に更新する。
     * @@param l_strReturnCode - (受信電文のチェック結果)
     * @@param l_datDeliveryScheduledDate - (受渡予定日)
     * @@param l_datComondebiCaptureDate - (振込入金予定日)
     * @@param l_cashTransSituation - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況オブジェクト
     * @@roseuid 4119F7460263
     */
    public void updateSettleResultNotify(
        String l_strReturnCode,
        Date l_datDeliveryScheduledDate,
        Date l_datComondebiCaptureDate,
        WEB3AioFinInstitutionCashTransStatus l_cashTransSituation)
        throws WEB3BaseException;

    /**
     * (update決済結果応答)<BR>
     * 金融機@関連携入出金状況テーブルを決済結果応答時の状態に更新する。
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 4119F746032E
     */
    public void updateSettleResultResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update決済完了)<BR>
     * 金融機@関連携入出金状況テーブルを決済完了時（エラー）の状態に更新する。
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 411B189B033E
     */
    public void updateSettleComplete(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (update決済エラー)<BR>
     * 金融機@関連携入出金状況テーブルを決済エラー時の状態に更新する。
     * @@param l_cashTransStatus - (入出金状況)<BR>
     * @@throws WEB3BaseException<BR>
     * 金融機@関連携入出金状況Paramsオブジェクト
     * @@roseuid 411B32420235
     */
    public void updateSettleError(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)
            throws WEB3BaseException;

    /**
     * (getメッセージコード)<BR>
     * メッセージコードを取得する。
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strAccountCode - (顧客コード)
     * @@param l_strOrderRequestNumber - (識別コード)
     * @@param l_strComDebitNumber - (.comデビット決済取引番号)
     * @@param l_strStatus - (処理区分)
     * @@param l_strOrderStatusFlag - (処理FLAG（注文）)
     * @@param l_strStartStatusFlag - (処理FLAG（決済開始）)
     * @@param l_strResultStatusFlag - (処理FLAG（決済結果）)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD83011B
     */
    public String getMessageCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strOrderRequestNumber,
        String l_strComDebitNumber,
        String l_strStatus,
        String l_strOrderStatusFlag,
        String l_strStartStatusFlag,
        String l_strResultStatusFlag)
        throws WEB3BaseException;

    /**
     * (createHashMapFrom受信電文)<BR>
     * 受信電文から、HashMapを生成する。
     * @@param l_request - (リクエストデータ)
     * @@return HashMap
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD8302FF
     */
    public HashMap createHashMapFromReceiptTelegram(
                HttpServletRequest l_request)
        throws IOException, WEB3BaseException;
    
    /**
     * (getプリファ@レンス)<BR>
     * パラメータに指定された設定名称の設定値をシステムプリファ@レンステーブルから取得する。<BR> 
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strName - (設定名称)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD83011B
     */
    public String getPreference(
        String l_strInstitutionCode,
        String l_strName)
        throws WEB3BaseException;
    
    /**
     * (getキャリア区分)<BR>
     * 注文経路区分からキャリア区分を取得し、値を変換する。<BR> 
     * <BR>
     * @@param l_strOrderRootDiv - (注文経路区分) 
     * @@return String
     */
    public String getCareerDiv(String l_strOrderRootDiv);
}
@
