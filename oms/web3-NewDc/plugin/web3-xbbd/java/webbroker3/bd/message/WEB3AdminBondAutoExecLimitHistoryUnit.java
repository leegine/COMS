head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondAutoExecLimitHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : ©“®–ñ’è˜g—š—ğ(WEB3AdminBondAutoExecLimitHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 æâ—Ñ–Q (’†u) V‹Kì¬
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (©“®–ñ’è˜g—š—ğ)<BR>
 * ©“®–ñ’è˜g—š—ğƒNƒ‰ƒX
 * <BR>
 * @@author æâ—Ñ–Q
 * @@version 1.0
 */
public class WEB3AdminBondAutoExecLimitHistoryUnit  extends Message
{
    
    /**
     * (–ñ’è“ú)<BR>
     * –ñ’è“ú
     */
    public Date executionUpdateDate;
    
    /**
     * (–ñ’èÏc‚)<BR>
     * –ñ’èÏc‚
     */
    public String executedBalance;
    
    /**
     * (©“®–ñ’è˜g)<BR>
     * ©“®–ñ’è˜g
     */
    public String autoExecLimit;
    
    /**
     * @@roseuid 44E3363201F4
     */
    public WEB3AdminBondAutoExecLimitHistoryUnit() 
    {
     
    }
}
@
