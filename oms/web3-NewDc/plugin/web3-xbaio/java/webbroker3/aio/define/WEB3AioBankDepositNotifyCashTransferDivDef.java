head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBankDepositNotifyCashTransferDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioBankDepositNotifyCashTransferDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/07 堀野和美 (FLJ) 新規作成
*/

package webbroker3.aio.define;

/**
 * (入金通知入払区分)<BR>
 * 入金通知入払区分<BR>
 *
 * @@author 堀野和美(FLJ)
 * @@version 1.0
 */

public interface WEB3AioBankDepositNotifyCashTransferDivDef
{

    /**
     *  1:入金<BR>
     */
    public static final String CASH_IN = "1";

    /**
     * 2:出金<BR>
     */
    public static final String CASH_OUT = "2";


}
@
