head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.07.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDisplayContentsUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示内容情報(WEB3PvInfoDisplayContentsUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2006/05/12 凌建平(中訊) 仕様変更管理台帳・モデルNo.062
Revesion History : 2006/05/22 凌建平(中訊) 仕様変更管理台帳・モデルNo.063
Revesion History : 2008/08/14 武　@波(中訊) 仕様変更管理台帳・モデルNo.105
*/
package webbroker3.pvinfo.message;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (表示内容情報)<BR>
 * 表示内容情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoDisplayContentsUnit extends Message 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoDisplayContentsUnit.class);

    /**
     * (表示内容ID)<BR>
     * 表示内容ID<BR>
     */
    public String displayContentsId;
    
    /**
     * (表示条件番号)<BR>
     * 表示条件番号<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     * 1028：　@ログインパスワード変更要<BR>
     * 1029：　@外国証券口座開設<BR>
     * 1030： 外株保有<BR>
     * 1031： 外株注文発生（当日）<BR>
     * 1032： 外株注文発生（翌日）<BR>
     * 1033： 外株約定発生<BR>
     * 1041：　@20％割れ1日＆30％割れ5日以下<BR>
     * 1042：　@20％割れ1日＆30％割れ6日<BR>
     * 1043：　@20％割れ2日＆30％割れ6日以下<BR>
     * 1044：　@20％割れ3日以上<BR>
     * 1045：　@30％割れ2～4日<BR>
     * 1046：　@30％割れ5日<BR>
     * 1047：　@30％割れ6日<BR>
     * 1048：　@30％割れ7日以上<BR>
     */
    public String conditionNumber;
    
    /**
     * (優先区分)<BR>
     * 優先区分<BR>
     * <BR>
     * 0：　@ダイレクト指定<BR>
     * 1：　@最優先<BR>
     * 2：　@優先<BR>
     * 3：　@通常<BR>
     */
    public String priorityDiv;
    
    /**
     * (表示期間From)<BR>
     * 表示期間From<BR>
     */
    public String listStartDate;
    
    /**
     * (表示期間To)<BR>
     * 表示期間To<BR>
     */
    public String listEndDate;
    
    /**
     * (表示タイトル)<BR>
     * 表示タイトル<BR>
     */
    public String displayTitle;
    
    /**
     * (表示文章)<BR>
     * 表示文章<BR>
     */
    public String displayMessage;
    
    /**
     * (表示色)<BR>
     * 表示色<BR>
     * <BR>
     * 0：　@黒<BR>
     * 1：　@赤<BR>
     * 2：　@青<BR>
     * 3：　@緑<BR>
     * 4：　@茶<BR>
     */
    public String displayColor;
    
    /**
     * (点滅表示フラグ)<BR>
     * 点滅表示フラグ<BR>
     * <BR>
     * false：　@なし<BR>
     * true：　@あり<BR>
     */
    public boolean blinkDisplayFlag;
    
    /**
     * (URL指定)<BR>
     * URL指定<BR>
     */
    public String displayUrl;
    
    /**
     * (最終更新日時表示フラグ)<BR>
     * 最終更新日時表示フラグ<BR>
     * <BR>
     * false：　@表示<BR>
     * true：　@非表示<BR>
     */
    public boolean lastUpdateTimeDisplayFlag;
    
    /**
     * (有効/無効フラグ)<BR>
     * 有効/無効フラグ<BR>
     * <BR>
     * false：　@有効<BR>
     * true：　@無効<BR>
     */
    public boolean effectiveFlag;
    
    /**
     * (表示媒体)<BR>
     * 表示媒体<BR>
     * <BR>
     * 0：　@DEFAULT(PC&モバイル)<BR>
     * 1：　@PC<BR>
     * 2：　@モバイル<BR>
     */
    public String displayDevice;
    
    /**
     * (最終更新者)<BR>
     * 最終更新者<BR>
     */
    public String lastUpdateMember;
    
    /**
     * (最終更新日時)<BR>
     * 最終更新日時<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (表示内容情報)<BR>
     * コンストラクタ。<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit
     * @@roseuid 414670CA01E6
     */
    public WEB3PvInfoDisplayContentsUnit() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）表示条件番号チェック<BR>
     * 　@this.表示条件番号 == nullの場合、<BR>
     * 　@「表示条件エラー」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01044<BR>
     * <BR>
     * ２）表示タイトルチェック<BR>
     * 　@this.表示タイトルが以下の条件のいづれかに該当する場合、<BR>
     * 　@「表示タイトルエラー」の例外をスローする。<BR>
     * 　@　@・this.表示タイトル == null<BR>
     * 　@　@・WEB3StringTypeUtility.getFixedByteLength(this.表示タイトル) > 100<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01045<BR>
     * <BR>
     * ３）表示文章チェック<BR>
     * 　@this.表示文章が以下の条件のいづれかに該当する場合、<BR>
     * 　@「表示文章エラー」の例外をスローする。<BR>
     * 　@　@・this.表示文章 == null<BR>
     * 　@　@・this.表示文章.byte数 > 2000byte<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01046<BR>
     * <BR>
     * ４）表示媒体チェック<BR>
     * 　@this.表示媒体 == nullの場合、<BR>
     * 　@「表示媒体エラー」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01047<BR>
     * <BR>
     * ５）表示優先度チェック<BR>
     * 　@this.優先区分が以下の条件のいづれかに該当する場合、<BR>
     * 　@「表示優先度エラー」の例外をスローする。<BR>
     * 　@　@・this.表示条件番号 == ("1001：入金請求発生&信用口座開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1002：入金請求発生&信用口座未開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1003：立替金発生" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1005：証拠金不足" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1007：　@決済期限間近（一週間前）の建玉保有") かつ<BR>
     * 　@　@　@this.優先区分 != null<BR>
     * 　@　@・this.表示条件番号 != ("1001：入金請求発生&信用口座開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1002：入金請求発生&信用口座未開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1003：立替金発生" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1005：証拠金不足" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1007：　@決済期限間近（一週間前）の建玉保有") かつ<BR>
     * 　@　@　@this.優先区分 == null<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01048<BR>
     * <BR>
     * ６）表示期間(自)チェック<BR>
     * 　@this.表示期間From != nullの場合、以下のチェックを行い、<BR>
     * 　@いづれかの条件に該当する場合は、<BR>
     * 　@「表示期間(自)エラー」の例外をスローする。<BR>
     * 　@・Date型に変換してエラーが発生<BR>
     * 　@・表示期間Fromの年月日時分 <= システム時間(*1)の年月日時分<BR>
     * 分<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01049<BR>
     * <BR>
     * ７）表示期間(至)チェック<BR>
     * 　@this.表示期間To != nullの場合、以下のチェックを行い、<BR>
     * 　@いづれかの条件に該当する場合は、<BR>
     * 　@「表示期間(至)エラー」の例外をスローする。<BR>
     * 　@・Date型に変換してエラーが発生<BR>
     * 　@・表示期間Toの年月日時分 <= システム時間(*1)の年月日時分<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01050<BR>
     * <BR>
     * ８）表示期間(自～至)整合性チェック<BR>
     * 　@this.表示期間From != null かつ this.表示期間To != nullの場合、<BR>
     * 　@以下の条件に該当する場合は、「表示期間不整合エラー」の例外を<BR>
     * 　@スローする。<BR>
     * 　@　@・this.表示期間From > this.表示期間To<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01051<BR>
     * <BR>
     * ９）表示色チェック<BR>
     * 　@this.表示色が以下の条件のいづれかに該当する場合、<BR>
     * 　@「表示色エラー」の例外をスローする。<BR>
     * 　@　@・this.表示条件番号 == ("0000：ダイレクト指定" or<BR>
                                   "1001：入金請求発生&信用口座開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1002：入金請求発生&信用口座未開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1003：立替金発生" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1005：証拠金不足" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1007：　@決済期限間近（一週間前）の建玉保有") かつ<BR>
     * 　@　@　@this.表示色 != null<BR>
     * 　@　@・this.表示条件番号 != ("0000：ダイレクト指定" or<BR>
                                   "1001：入金請求発生&信用口座開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1002：入金請求発生&信用口座未開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1003：立替金発生" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1005：証拠金不足" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1007：　@決済期限間近（一週間前）の建玉保有") かつ<BR>
     * 　@　@　@this.表示色 == null<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01052<BR>
     * <BR>
     * １０）URLチェック<BR>
     * 　@this.URL指定が以下の条件のいづれかに該当する場合、<BR>
     * 　@「URLエラー」の例外をスローする。<BR>
     * 　@　@・this.表示条件番号 == ("1001:入金請求発生&信用口座開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1002:入金請求発生&信用口座未開設" or<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@"1003:立替金発生") かつ<BR>
     * 　@　@　@this.URL指定 != null<BR>
     * 　@　@・this.URL指定.length > 100<BR>
     * 　@　@・this.URL指定の先頭の文字が"http"でない<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01053<BR>
     * <BR>
     * (*1)<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)にて<BR>
     * 取得したシステム時間<BR>
     * @@roseuid 415C02E900A5
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）表示条件番号チェック 
        if(conditionNumber == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01044.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01044,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //２）表示タイトルチェック
        if (displayTitle == null || WEB3StringTypeUtility.getFixedByteLength(displayTitle) > 100)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01045.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01045,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //３）表示文章チェック
        if (displayMessage == null || 
            displayMessage != null && WEB3StringTypeUtility.getByteLength(displayMessage) > 2000)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01045.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01046,
                getClass().getName() + "." + STR_METHOD_NAME);
        }        
        
        //４）表示媒体チェック
        if(displayDevice == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01047.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01047,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //５）表示優先度チェック
        if(priorityDiv == null &&
            (!WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)) 
            || priorityDiv != null &&
            (WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)))
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01048.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01048,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //６）表示期間(自)チェック
        Timestamp l_tsCurrent = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        //・Date型に変換してエラーが発生
        Date l_dStartDate = WEB3DateUtility.getDate(listStartDate, "yyyyMMddHHmm"); 
        if(listStartDate != null && l_dStartDate == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01049.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01049,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        if(listStartDate != null && WEB3DateUtility.compareToMinute(l_dStartDate, l_tsCurrent) <= 0)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01049.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01049,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //７）表示期間(至)チェック
        //・Date型に変換してエラーが発生
        Date l_dEndDate = WEB3DateUtility.getDate(listEndDate, "yyyyMMddHHmm");
        if(listEndDate != null && l_dEndDate == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01050.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01050,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        if(listEndDate != null && WEB3DateUtility.compareToMinute(l_dEndDate, l_tsCurrent) <= 0)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01050.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01050,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //８）表示期間(自～至)整合性チェック
        if(listStartDate != null && listEndDate != null)
        {
            if(WEB3DateUtility.compare(l_dStartDate, l_dEndDate) > 0)
            {
                log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01051.error_message);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //９）表示色チェック
        if(displayColor == null &&
            (!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber) &&
            !WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)) 
            || displayColor != null &&
            (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(conditionNumber) ||
            WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber)||
            WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber)||
            WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber)||
            WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(conditionNumber)||
            WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(conditionNumber)))
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01052.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01052,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１０）URLチェック
        if(displayUrl != null && (WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(conditionNumber) 
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(conditionNumber) 
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(conditionNumber))
            || displayUrl != null && (WEB3StringTypeUtility.getByteLength(displayUrl) > 100)
            || displayUrl != null && (!displayUrl.toLowerCase().startsWith("http")))
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01053.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01053,
                getClass().getName() + "." + STR_METHOD_NAME);
        }     

        log.exiting(STR_METHOD_NAME);
    }
}
@
