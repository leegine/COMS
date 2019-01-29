head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryMutualFundRuitoDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信・累投(WEB3TradeHistoryMutualFundRuitoDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02  範慧琴(中訊) 新規作成
Revesion History : 2007/06/14  周墨洋(中訊) モデル 071
*/
package webbroker3.tradehistory.define;

/**
 * 投信・累投  定数定義インタフェイス
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public interface WEB3TradeHistoryMutualFundRuitoDef
{

    /**
     *20：　@国内投信
     */
    public final static String MUTUAL_FUND_DOMESTIC = "20";
    
    /**
     *21：　@外国投信
     */
    public final static String MUTUAL_FUND_FOREIGN = "21";

    /**
     *22：　@GP
     */
    public final static String GP = "22";

    /**
     *23：　@MRF
     */
    public final static String MRF = "23";

    /**
     *00：　@MMF
     */
    public final static String MMF = "00";

    /**
     *D102：　@売付
     */
    public final static String SELL = "D102";

    /**
     *D108：　@買付
     */
    public final static String BUY = "D108";

    /**
     *A3:振替
     */
    public final static String TRANSFER = "A3";

}
@
