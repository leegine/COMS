head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ErrorReasonCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ErrorReasonCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 li-yunfeng(sinocom)　@新規作成
Revesion History : 2004/10/09 孟東(sinocom)　@定義追加
Revesion History : 2006/5/25 凌建平 (中訊) インターフェイス申請・No079
Revesion History : 2006/07/12 栄イ (中訊)【先物オプション】仕様変更・ＤＢレイアウトNo.046
Revesion History : 2007/01/26 栄イ (中訊)【先物オプション】仕様変更・ＤＢレイアウトNo.067
Revesion History : 2007/04/23 栄イ (中訊)【株式】仕様変更・ＤＢレイアウトNo.138
*/
package webbroker3.common.define;

/**
 * 注文エラー理由コード　@定数定義インタフェイス
 *
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3ErrorReasonCodeDef
{
    /**
     * 0000 : 正常
     */
    public static final String NORMAL = "0000";

    /**
     * 1001 : 受付エラー
     */
    public static final String ACCEPT_ERROR = "1001";

    /**
     * 1002 : 訂正エラー
     */
    public static final String CHANGE_ERROR = "1002";

    /**
     * 1003 : 取消エラー
     */
    public static final String CANCEL_ERROR = "1003";

    /**
     * 1004 : 切替エラー
     */
    public static final String TRANSFER_ERROR = "1004";

    /**
     * 0001 : 値幅エラー
     */
    public static final String PRICE_RANGE_ERROR = "0001";

    /**
     * 0002 : 預り金不足エラー
     */
    public static final String DEPOSIT_MONEY_SHORT_ERROR = "0002";

    /**
     * 0003 : 残高不足エラー（株式、指数オプション）
     */
    public static final String POSITION_SHORT_ERROR = "0003";

    /**
     * 0004 : 保証金不足エラー
     */
    public static final String GUARANTY_MONEY_SHORT_ERROR = "0004";

    /**
     * 0005 : 建株残高不足エラー
     */
    public static final String OPEN_INTERSET_SHORT_ERROR = "0005";

    /**
     * 0006 : 売買停止銘柄エラー
     */
    public static final String TRADE_STOP_PRODUCT_ERROR = "0006";

    /**
     * 0007 : 市場変更銘柄エラー
     */
    public static final String MARKET_CHANGE_PRODUCT_ERROR = "0007";

    /**
     * 0008 : 買付余力エラー
     */
    public static final String TRADE_POWER_ERROR = "0008";

    /**
     * 0009 : 売付可能数量エラー
     */
    public static final String SELL_QUANTITY_ERROR = "0009";

    /**
     * 0010 : 特定口座エラー
     */
    public static final String SPEC_ACCOUNT_ERROR = "0010";

    /**
     * 0011 : 出合停止銘柄エラー
     */
    public static final String CARRY_OVER_SKIP_PRODUCT_ERROR = "0011";

    /**
     * 0012 : 二階建チェックエラー
     */
    public static final String MARGIN_SEC_ERROR = "0012";

    /**
     * 0013：発注日エラー
     */
    public static final String BIZ_DATE_ERROR = "0013";

    /**
     * 0014：呼値チェックエラー
     */
    public static final String TICK_VALUE_ERROR = "0014";

    /**
     * 0015：空売りチェックエラー
     */
    public static final String SHORT_SELLING_ERROR = "0015";

    /**
     * 0016：決済期日到来済エラー
     */
    public static final String SETTLEDAY_CAME_ERROR = "0016";

    /**
     * 0017：現引・現渡注文登録済エラー
     */
    public static final String SWAP_MARGIN_REGISTED_ERROR = "0017";

    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * 1400 : ＧＩ８０１'以外のＤＡＴＡ ＣＯＤＥ ＥＲＲ
     */
    public static final String NOT_GI801_DATA_CODE_ERROR = "1400";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * 2200 : 部店コード該当なしＥＲＲ
     */
    public static final String NO_BRANCH_CODE_ERROR = "2200";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * 1200 : 顧客登録ファ@イル ＤＩＳＫ ＥＲＲ
     *        残高・当日伝票ファ@イル ＤＩＳＫ ＥＲＲ
     *        顧客精算ファ@イル ＤＩＳＫ ＥＲＲ
     *        自動振替登録ファ@イル ＤＩＳＫ ＥＲＲ
     *        アンサー当日伝票ファ@イル ＤＩＳＫ ＥＲＲ
     */
    public static final String FILE_DISK_ERROR = "1200";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * 9500 : 残高なし ＥＲＲ
     */
    public static final String NO_BALANCE_ERROR = "9500";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * C100 : 残高オーバー ＥＲＲ
     */
    public static final String BALANCE_OVER_ERROR = "C100";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * FFFF : 預り書未回収チェックによる拒否電文 ＥＲＲ
     */
    public static final String TELEGRAM_REJECT_ERROR = "FFFF";
    
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * FF69 : ロック客 ＥＲＲ
     */
    public static final String LOCK_CUSTOMER_ERROR = "FF69";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * D600 : 振込先 ＥＲＲ
     */
    public static final String DEPOSIT_FROM_ERROR = "D600";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * 9100 : ＯＡ８８０Ｙ９０ のＫＥＹ ＣＯＤＥ ＥＲＲ
     */
    public static final String QA880Y90_KEY_CODE_ERROR = "9100";
 
    /**
     * (入出金注文単位テーブルの注文エラー理由コード)
     * FF4E : Ｒ２２１ ＲＥＰＯＲＴ の 端末 ＥＲＲ
     */
    public static final String R221_REPORT_END_ERROR = "FF4E";

    /**
      * 9001 : その他エラー
      */
    public static final String OTHRE_ERROR = "9001";

    /**
     * W001：株管理者手動失効済
     */
    public static final String EQUITY_ADMIN_MANUAL_EXPIRED = "W001";

    /**
     * W002：トリガー注文管理者手動失効済
     */
    public static final String TRIGGER_ADMIN_MANUAL_EXPIRED = "W002";

    /**
     * W004：先物OP管理者手動失効済
     */
    public static final String FUTURE_OP_ADMIN_MANUAL_EXPIRED = "W004";
}
@
