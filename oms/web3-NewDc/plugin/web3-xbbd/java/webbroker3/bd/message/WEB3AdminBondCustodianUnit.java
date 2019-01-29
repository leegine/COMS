head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.48.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondCustodianUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カストディアン(WEB3AdminBondCustodianUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (カストディアン)<BR>
 * カストディアン
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondCustodianUnit  extends Message
{
    
    /**
     * (カストディアンコード)<BR>
     * カストディアンコード
     */
    public String custodianCode;
    
    /**
     * (カストディアン名称)<BR>
     * カストディアン名称
     */
    public String custodianName;
    
    /**
     * @@roseuid 44E3363202CE
     */
    public WEB3AdminBondCustodianUnit() 
    {
     
    }
}
@
