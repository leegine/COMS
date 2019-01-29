head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryDailyBalanceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受渡日別残高情報(WEB3HistoryDailyBalanceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27  温 顕 法@(中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (受渡日別残高情報)<BR>
 * 受渡日別残高情報クラス<BR>
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public class WEB3HistoryDailyBalanceUnit extends Message 
{
    
    /**
     * (顧客勘定残高履歴ID)<BR>
     * 顧客勘定残高履歴ID<BR>
     */
    public String transactionHistoryId;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * (口座残高)<BR>
     * 口座残高<BR>
     */
    public String accountBalance;
    
    /**
     * (損益明細リンクフラグ)<BR>
     * 損益明細リンクフラグ<BR>
     * <BR>
     * false：　@リンクなし<BR>
     * true：　@リンクあり<BR>
     */
    public boolean profitLossLink;
    
    /**
     * (取引履歴情報一覧)<BR>
     * 取引履歴情報一覧<BR>
     */
    public WEB3HistoryTradeHistoryUnit[] tradeHistoryUnits;
    
    /**
     * @@roseuid 41789C48030D
     */
    public WEB3HistoryDailyBalanceUnit() 
    {
     
    }
}
@
