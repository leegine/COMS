head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInvalidItemInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : s๕ฺ๎๑(WEB3AccOpenInvalidItemInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 ฃw VK์ฌ
*/
package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (s๕ฺ๎๑)<BR>
 * s๕ฺ๎๑<BR>
 * @@author ฃw
 * @@version 1.0
 */
public class WEB3AccOpenInvalidItemInfo extends Message 
{
    
    /**
     * (s๕ฺผ)<BR>
     * s๕ฺผ<BR>
     */
    public String invalidItemName;
    
    /**
     * (Rg)<BR>
     * Rg<BR>
     */
    public String comment;
    
    /**
     * (ฎนtO)<BR>
     * ฎนtO<BR>
     * <BR>
     * trueF@@ฎน`FbN ่<BR>
     * falseF@@ฎน`FbNศต<BR>
     */
    public boolean compFlag;
    
    /**
     * @@roseuid 41B45E7A033C
     */
    public WEB3AccOpenInvalidItemInfo() 
    {
     
    }
}
@
