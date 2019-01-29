head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済明細(WEB3SLRepayUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (証券担保ローン返済明細)<BR>
 * 証券担保ローン返済明細クラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayUnit extends Message
{
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;

    /**
     * (返済予定日)<BR>
     * 返済予定日<BR>
     */
    public Date repayScheduledDate;

    /**
     * (返済額)<BR>
     * 返済額<BR>
     */
    public String repayAmt;

    /**
     * (受付日時)<BR>
     * 注文の受付日時<BR>
     */
    public Date receptionDate;

    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ<BR>
     */
    public String cancelFlag;

    /**
     * (証券担保ローン返済明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46CA4FA70263
     */
    public WEB3SLRepayUnit()
    {

    }
}
@
