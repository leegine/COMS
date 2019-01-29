head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBankDepositNotifyTradeDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioBankDepositNotifyTradeDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/07 堀野和美 (FLJ) 新規作成
*/

package webbroker3.aio.define;

/**
 * (入金通知取引区分)<BR>
 * 入金通知取引区分<BR>
 *
 * @@author 堀野和美(FLJ)
 * @@version 1.0
 */

public interface WEB3AioBankDepositNotifyTradeDivDef
{
    /**
     *  10:現金<BR>
     */
    public static final String CASH = "10";

    /**
     *  11:振込<BR>
     */
    public static final String PAY_BY_TRANSFER = "11";


    /**
     *  12:他店券入金<BR>
     */
    public static final String CHECK = "12";

    /**
     *  13: 交換<BR>
     */
    public static final String EXCHANGE = "13";

    /**
     *  14:振替<BR>
     */
    public static final String TRANSFER = "14";

    /**
     *  18:その他<BR>
     */
    public static final String OTHER = "18";

    /**
     *  19:訂正<BR>
     */
    public static final String CORRECT = "19";

}
@
