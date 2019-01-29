head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : ŠO‘Š”®’•¶ŒJ‰zUnitService(WEB3FeqOrderCarryOverUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 èè”Oàù (’†u) V‹Kì¬
                 : 2005/08/01 Šs‰p(’†u) ƒŒƒrƒ…[       
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;


/**
 * (ŠO‘Š”®’•¶ŒJ‰zUnitService)<BR>
 * ŠO‘Š”®’•¶ŒJ‰zUnitServiceƒCƒ“ƒ^ƒtƒFƒCƒX
 * 
 * @@author èè”Oàù(’†u)
 * @@version 1.0
 */
public interface WEB3FeqOrderCarryOverUnitService extends Service 
{
    
    /**
     * (exec’•¶ŒJ‰z)<BR>
     * ŒÚ‹q’PˆÊ‚Å’•¶ŒJ‰zˆ—‚ğÀ{‚·‚éB
     * @@param l_mainAccount - (ŒÚ‹q)<BR>
     * ŒÚ‹qƒIƒuƒWƒFƒNƒg
     * @@throws WEB3BaseException
     * @@roseuid 42B8A35F0327
     */
    public void execOrderCarryOver(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;
}
@
