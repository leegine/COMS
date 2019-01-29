head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SendmailDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 送信メール区分  定数定義クラス(WEB3SendmailDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 李頴淵 (SinoCom) 新規作成
Revesion History : 2005/07/05 孟東 (中訊) 株式約定メールの番号と定義名を修正
                                          証拠金、IPOと外株などの定義を追加
Revesion History : 2009/01/06 趙林鵬 (中訊) 共通仕様変更・ＤＢレイアウト668
Revesion History : 2009/02/12 趙林鵬 (中訊) 共通仕様変更・ＤＢレイアウト672
*/
package webbroker3.common.define;

/**
 * 送信メール区分  定数を定義する。
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SendmailDivDef
{

    /**
     * 01：サービス利用　@
     */
    public final static String SRVREGI = "01";

    /**
     * 0101：サービス利用申込確認メール　@
     */
    public final static String SRVREGI_CONFIRM_MAIL = "0101";

    /**
     * 0102：サービス利用期限メール
     */
    public final static String SRVREGI_TERM_MAIL = "0102";

    /**
     * 0103：サービス利用期限メール送信者一覧メール
     */
    public final static String SRVREGI_TERM_MAILSENDER_LIST_MAIL = "0103";

    /**
     * 02：口座開設
     */
    public final static String ACCOPEN = "02";

    /**
     * 0201：口座開設申込メール
     */
    public final static String ACCOPEN_APPLICATION_MAIL = "0201";

    /**
     * 0202：口座開設完了メール
     */
    public final static String ACCOPEN_COMPLETE_MAIL = "0202";

    /**
     * 03：問合せ管理
     */
    public final static String FAQ = "03";

    /**
     * 0301：問合せメール
     */
    public final static String FAQ_INPUT = "0301";
    
	/**
	 * 0302：問合せ受付メール
	 */
	public final static String FAQ_COMPLETE = "0302";

    /**
     * 04：お客様情報
     */
    public final static String ACCINFO = "04";

    /**
     * 0401：案内メール
     */
    public final static String ACCINFO_GUIDE_MAIL = "0401";

    /**
     * 05：連絡管理
     */
    public final static String INFORM = "05";

    /**
     * 0501：連絡種別：０１用メール
     */
    public final static String INFORM_01_MAIL = "0501";

    /**
     * 0502：連絡種別：０２用メール
     */
    public final static String INFORM_02_MAIL = "0501";

    /**
     * 0503：連絡種別：０３用メール
     */
    public final static String INFORM_03_MAIL = "0503";

    /**
     * 0504：連絡種別：０４用メール
     */
    public final static String INFORM_04_MAIL = "0504";

    /**
     * 06：証拠金管理
     */
    public final static String DEPOSIT = "06";

    /**
     * 0601：証拠金メール
     */
    public final static String DEPOSIT_NOTIFY = "0601";

    /**
     * 06011：証拠金不足のお知らせメール
     */
    public final static String DEPOSIT_SHORTAGE_NOTIFY = "06011";

    /**
     * 06012：証拠金不足のお知らせ及び強制反対売買実行メール
     */
    public final static String DEPOSIT_SHORTAGE_COERCION_OPPOSITION = "06012";

    /**
     * 06013：強制反対売買実行メール
     */
    public final static String DEPOSIT_COERCION_OPPOSITION = "06013";

    /**
     * 0602：証拠金不足顧客一覧メール
     */
    public final static String DEPOSIT_SHORTAGE_LIST = "0602";

    /**
     * 07：入出金
     */
    public final static String AIO = "07";

    /**
     * 0701：入金連絡メール
     */
    public final static String AIO_DEPOSIT_INFORM = "0701";

    /**
     * 08：IPO
     */
    public final static String IPO = "08";
    
    /**
     * 0801：抽選結果メール
     */
    public final static String IPO_LOT_RESULT = "0801";

    /**
     * 08010001：抽選結果メール（当選）
     */
    public final static String IPO_ELECTION = "08010001";

    /**
     * 08010002：抽選結果メール（補欠）
     */
    public final static String IPO_SUPPLEMENT = "08010002";

    /**
     * 08010003：抽選結果メール（落選）
     */
    public final static String IPO_DEFEAT = "08010003";

    /**
     * 09：株式ミニ投資
     */
    public final static String MINI_STOCK = "09";

    /**
     * 0901：株式ミニ投資
     */
    public final static String MINI_EQUITY = "0901";

    /**
     * 10：外国株式
     */
    public final static String FEQ = "10";
    
    /**
     * 1001：新規注文受付メール(外国株式)
     */
    public final static String FEQ_ORDER_ACCEPT = "1001";

    /**
     * 1002：訂正注文受付メール(外国株式)
     */
    public final static String FEQ_ORDER_CHANGE = "1002";

    /**
     * 1003：取消注文受付メール(外国株式)
     */
    public final static String FEQ_ORDER_CANCEL = "1003";

    /**
     * 20：株式
     */
    public final static String EQUITY = "20";

    /**
     * 2001：株式約定状態
     */
    public final static String EQUITY_ORDER_EXEC_STATUS = "2001";

    /**
     * 20010：株式未約定メール
     */
    public final static String EQUITY_ORDER_UNEXECUTED = "20010";

    /**
     * 20011：株式一部約定メール
     */
    public final static String EQUITY_ORDER_PARTIALLY_EXECUTED = "20011";

    /**
     * 20012：株式約定メール
     */
    public final static String EQUITY_ORDER_EXECUTED = "20012";

    /**
     * 20013：株式失効メール
     */
    public final static String EQUITY_ORDER_CLOSE = "20013";

    /**
     * 21：先物
     */
    public final static String FUTURES = "21";

    /**
     * 2101：先物約定状態
     */
    public final static String FUTURES_ORDER_EXEC_STATUS = "2101";

    /**
     * 21010：先物未約定メール
     */
    public final static String FUTURES_ORDER_UNEXECUTED = "21010";

    /**
     * 21011：先物一部約定メール
     */
    public final static String FUTURES_ORDER_PARTIALLY_EXECUTED = "21011";

    /**
     * 21012：先物約定メール
     */
    public final static String FUTURES_ORDER_EXECUTED = "21012";

    /**
     * 21013：先物失効メール
     */
    public final static String FUTURES_ORDER_CLOSE = "21013";

    /**
     * 22：OP
     */
    public final static String OPTION = "22";

    /**
     * 2201：OP約定状態
     */
    public final static String OPTION_ORDER_EXEC_STATUS = "2201";

    /**
     * 22010：OP未約定メール
     */
    public final static String OPTION_ORDER_UNEXECUTED = "22010";

    /**
     * 22011：OP一部約定メール
     */
    public final static String OPTION_ORDER_PARTIALLY_EXECUTED = "22011";

    /**
     * 22012：OP約定メール
     */
    public final static String OPTION_ORDER_EXECUTED = "22012";

    /**
     * 22013：OP失効メール
     */
    public final static String OPTION_ORDER_CLOSE = "22013";

    /**
     * 31：注意情報通知メール
     */
    public final static String ATTENTION_INFO_NOTIFY = "31";

    /**
     * 3101：注意情報（売停情報）
     */
    public final static String ATTENTION_INFO_SELL_STOP_INFO = "3101";

    /**
     * 3102：注意情報（制限値幅情報）
     */
    public final static String TTENTION_INFO_LIMIT_RANGE_INFO = "3102";

    /**
     * 3103：注意情報（フリーフォーマット）
     */
    public final static String TTENTION_INFO_FREE_FORMAT = "3103";

    /**
     * 3104：注意情報（エラー）
     */
    public final static String TTENTION_INFO_ERROR = "3104";
}
@
