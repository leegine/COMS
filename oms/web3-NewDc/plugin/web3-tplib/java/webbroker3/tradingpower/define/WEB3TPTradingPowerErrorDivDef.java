head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerErrorDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力エラー区分Def(WEB3TPTradingPowerErrorDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 nakazato(ACT) 新規作成
                   2006/09/11 徐宏偉 (中訊) モデルNo.008
Revesion History : 2007/09/26 孟亞南 (中訊) モデルNo.193
Revesion History : 2008/12/11 劉剣 (中訊) モデルNo.379
*/
package webbroker3.tradingpower.define;

/**
 * (取引余力エラー区分Def)
 */
public interface WEB3TPTradingPowerErrorDivDef
{

    /**
     * (預り金不足エラー)
     */
    public static final String LACK_ACCOUNT_BALANCE = "1";

    /**
     * (二階建エラー)
     */
    public static final String MARGIN_SEC_ERROR = "2";

    /**
     * (取引停止エラー)
     */
    public static final String TRADING_STOP_ERROR = "3";

    /**
     * (その他商品買付余力エラー)
     */
    public static final String OTHER_TRADING_STOP_ERROR = "4";

    /**
     * (信用新規建余力停止エラー)
     */
    public static final String MARGIN_OPEN_POSITION_STOP_ERROR = "5";

    /**
     * (出金余力停止エラー)
     */
    public static final String PAYMENT_STOP_ERROR = "6";

    /**
     * (保証金不足エラー)
     */
    public static final String LACK_MARGIN_POWER = "7";

    /**
     * (（増担保）預り金不足エラー)
     */
    public static final String INC_DEPOSIT_LACK_ACCOUNT_BALANCE = "8";

    /**
     * (（増担保）保証金不足エラー)
     */
    public static final String INC_DEPOSIT_LACK_MARGIN_POWER = "9";
    
    /**
     * (預り金担保出金余力停止エラー)
     */
    public static final String CASH_DEPOSIT_PAYMENT_STOP_ERROR = "10";
    
    /**
     * (証券担保ローン金額ロックエラー)
     */
    public static final String SECURITY_DEPOSIT_LOAN_LOCK_ERROR = "11";

    /**
     * (受入保証金占有率超過エラー)
     */
    public static final String RECEIPT_DEPOSIT_RATE_OVER_ERROR = "12";
}
@
