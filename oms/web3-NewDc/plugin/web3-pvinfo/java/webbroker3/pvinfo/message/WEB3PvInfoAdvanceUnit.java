head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAdvanceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立替金情報(WEB3PvInfoAdvanceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (立替金情報)<BR>
 * 立替金情報クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoAdvanceUnit extends Message 
{
    
    /**
     * (立替金発生日)<BR>
     * 立替金発生日<BR>
     */
    public Date advanceGenDate;
        
    /**
     * (追証余力)<BR>
     * 追証余力<BR>
     */
    public String additionalTradingPower = null;
    
    /**
     * (預り金不足額)<BR>
     * 預り金不足額<BR>
     */
    public String accountBalanceShortfall = null;
    
    /**
     * (入金請求額)<BR>
     * 入金請求額<BR>
     */
    public String payClaimAmount;
    
    /**
     * (立替金情報)<BR>
     * コンストラクタ。<BR>
     */
    public WEB3PvInfoAdvanceUnit()
    {
        
    }
    
}
@
