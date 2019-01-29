head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SystemPreferencesNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : システムプリファ@レンス名定数定義インタフェイス(WEB3SystemPreferencesNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/02 孟東(sinocom) 新規作成
Revesion History : 2006/06/01 凌建平(中訊) インターフェンス申請書・No080
Revesion History : 2006/08/30 栄イ(中訊) インターフェンス申請書・No086
Revesion History : 2006/09/18 栄イ(中訊)  ＤＢレイアウト(システムプリファ@レンス)による
Revesion History : 2006/10/18 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo432
Revesion History : 2007/03/28 キョウ再平(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo481
Revesion History : 2007/04/09 キョウ再平(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo484
Revesion History : 2007/04/19 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo487
Revesion History : 2007/12/10 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo583
Revesion History : 2008/07/14 趙林鵬(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo635
Revesion History : 2009/02/05 趙林鵬(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo674
Revesion History : 2009/08/19 趙林鵬(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo692
*/
package webbroker3.common.define;

/**
 * システムプリファ@レンス名定数定義インタフェイス
 *
 * @@author meng-d
 * @@version 1.0
 */
public interface WEB3SystemPreferencesNameDef
{
    /**
     * DL_REC_COUNT_TRADEHISTORYLIST：取引履歴一覧ファ@イルダウンロードにおける超過件数値
     */
    public final static String DL_REC_COUNT_TRADEHISTORYLIST = "DL_REC_COUNT_TRADEHISTORYLIST";

    /**
     * _MIN_ACCOUNT:専用振込先口座警告件数
     */
    public final static String MIN_ACCOUNT = "_MIN_ACCOUNT";

    /**
     * ONLINE_SERVICE_START_TIME:オンラインサービス開始時間
     */
    public final static String ONLINE_SERVICE_START_TIME = "system.onlineservice.starttime";

    /**
     * トリガー注文発注遅延無視実施会社フラグ
     */
    public final static String IS_SUBMIT_DELAY_ORDER = ".rls.is.submit.delay.order";

    /**
     * _FIN_ACCOUNT_NAME:口座名義人
     */
    public final static String FIN_ACCOUNT_NAME = "_FIN_ACCOUNT_NAME";

    /**
     * DL_REC_COUNT_PROFITLOSSLIST：損益明細ファ@イルダウンロードにおける超過件数値
     */
    public final static String DL_REC_COUNT_PROFITLOSSLIST = "DL_REC_COUNT_PROFITLOSSLIST";

    /**
     * PAY_TRIGGER_ORDER_MAX_COUNT：出金請求レコードトリガー発行件数Max値
     */
    public final static String PAY_TRIGGER_ORDER_MAX_COUNT = "PAY_TRIGGER_ORDER_MAX_COUNT";

    /**
     * DL_REC_COUNT_SERVICE_ACCOUNT_DATA：サービス利用管理者顧客データダウンロードにおける超過件数値
     */
    public final static String DL_REC_COUNT_SERVICE_ACCOUNT_DATA = "DL_REC_COUNT_SERVICE_ACCOUNT_DATA";

    /**
     * exec.notify.wait.seconds：SONAR入力の約定が注文通知より先にWEBⅢに到着した場合、注文通知を何秒待つか設定する
     */
    public final static String EXEC_NOTIFY_WAIT_SECONDS = "exec.notify.wait.seconds";

    /**
     * UL_DOCADMIN_DEVMANAGE_COUNT:（金商法@書面交付管理アップロード件数）<BR>
     * 　@※ドキュメント管理金商法@交付閲覧ULで使用
     */
    public final static String UL_DOCADMIN_DEVMANAGE_COUNT = "UL_DOCADMIN_DEVMANAGE_COUNT";

    /**
     * _SBS_DRAW_DD:（定時定額買付　@引落日）<BR>
     * 　@※定時定額買付の締切日算出に使用（XX=証券会社コード）<BR>
     */
    public final static String SBS_DRAW_DD = "_SBS_DRAW_DD";

    /**
     * _SBS_DAY_COUNT:（定時定額買付　@起算営業日数）<BR>
     * 　@※定時定額買付の締切日算出に（XX=証券会社コード）<BR>
     */
    public final static String SBS_DAY_COUNT = "_SBS_DAY_COUNT";

    /**
     * _SBS_DRAW_DD_BONUS:（定時定額買付賞与引落日）<BR>
     */
    public final static String SBS_DRAW_DD_BONUS = "_SBS_DRAW_DD_BONUS";

    /**
     * _SBS_DAY_COUNT_BONUS:（定時定額買付賞与締切日起算日数）<BR>
     */
    public final static String SBS_DAY_COUNT_BONUS = "_SBS_DAY_COUNT_BONUS";

    /**
     * _SBS_BONUS_MONTH:（定時定額買付ボーナス月）<BR>
     */
    public final static String SBS_BONUS_MONTH = "_SBS_BONUS_MONTH";

    /**
     * era_condition_year:（年号判定条件）<BR>
     */
    public final static String ERA_CONDITION_YEAR = "era_condition_year";

    /**
     * web3.adminAccountOpenAccTransfer.MaxAmount:（口座開設移管の毎回最大処理件数）<BR>
     */
    public final static String ADMIN_ACCOUNT_OPEN_ACC_TRANSFER_MAX_AMOUNT = "web3.adminAccountOpenAccTransfer.MaxAmount";
}
@
