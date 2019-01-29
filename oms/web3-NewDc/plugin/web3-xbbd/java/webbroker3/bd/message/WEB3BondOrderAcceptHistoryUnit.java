head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderAcceptHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文受付履歴(WEB3BondOrderAcceptHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.216
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (注文受付履歴)<BR>
 * 注文受付履歴<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondOrderAcceptHistoryUnit extends Message
{

    /**
     * (注文受付日)<BR>
     * 注文受付日<BR>
     */
    public Date orderDate;

    /**
     * (注文金額)<BR>
     * 注文金額<BR>
     */
    public String orderAmount;

    /**
     * (注文件数)<BR>
     * 注文件数<BR>
     */
    public String orderNumber;

    /**
     * (累計)<BR>
     * 累計<BR>
     */
    public String accumulatedTotal;

    /**
     * (注文受付履歴)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46637A000148
     */
    public WEB3BondOrderAcceptHistoryUnit()
    {

    }
}
@
