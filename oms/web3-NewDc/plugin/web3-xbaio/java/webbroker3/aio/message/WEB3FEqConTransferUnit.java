head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替明細(WEB3FEqConTransferUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (外株振替明細)<BR>
 * 外株振替明細クラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferUnit extends Message 
{
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String orderId;
    
    /**
     * (受付日時)<BR>
     * 受付日時
     */
    public Date receptionDate;
    
    /**
     * (振替金額)<BR>
     * 振替金額
     */
    public String changeAmt;
    
    /**
     * (受渡予定日)<BR>
     * 受渡予定日
     */
    public Date deliveryScheduledDate;
    
    /**
     * (処理状況)<BR>
     * 処理状況<BR>
     * <BR>
     * 0： 振替中<BR>
     * 1： UWG決済中<BR>
     * 2： UWG決済完了<BR>
     * 3： UWG決済エラー<BR>
     * 4： 取消済
     */
    public String transactionStateType;
    
    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ<BR>
     * <BR>
     * 取消可能： true<BR>
     * 取消不可： false
     */
    public boolean cancelFlag;
    
    /**
     * (外株振替明細)<BR>
     * コンストラクタ
     * @@roseuid 41CBF4D60343
     */
    public WEB3FEqConTransferUnit() 
    {
     
    }
}
@
