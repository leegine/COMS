head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondFxRateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 為替レート(WEB3AdminBondFxRateInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (為替レート)<BR>
 * 為替レートクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondFxRateInfo  extends Message
{
    
    /**
     * (買付基準為替)<BR>
     * 買付基準為替
     */
    public String buyBaseFx;
    
    /**
     * (売却基準為替)<BR>
     * 売却基準為替
     */
    public String sellBaseFx;
    
    /**
     * (買付約定為替)<BR>
     * 買付約定為替
     */
    public String buyExecFx;
    
    /**
     * (売却約定為替)<BR>
     * 売却約定為替
     */
    public String sellExecFx;
    
    /**
     * @@roseuid 44E336380290
     */
    public WEB3AdminBondFxRateInfo() 
    {
     
    }
}
@
