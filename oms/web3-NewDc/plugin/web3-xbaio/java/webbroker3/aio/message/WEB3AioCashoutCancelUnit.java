head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消明細(WEB3AioCashoutCancelUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (出金取消明細)<BR>
 * 出金取消明細クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutCancelUnit extends Message
{    
    /**
     * (出金注文の注文ID)
     */
    public String orderId;
    
    /**
     * (受付日時)<BR>
     * 注文の受付日時
     */
    public Date receptionDate;
    
    /**
     * (振込予定日)
     */
    public Date transScheduledDate;
    
    /**
     * (出金金額)
     */
    public String cashoutAmt;
    
    /**
     * (コンストラクタ)
     * @@return WEB3AioCashoutCancelUnit
     * @@roseuid 40F66F490336
     */
    public WEB3AioCashoutCancelUnit() 
    {
     
    }
}
@
