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
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : OฎถJzUnitService(WEB3FeqOrderCarryOverUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ่่Oเ๙ (u) VK์ฌ
                 : 2005/08/01 sp(u) r[       
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;


/**
 * (OฎถJzUnitService)<BR>
 * OฎถJzUnitServiceC^tFCX
 * 
 * @@author ่่Oเ๙(u)
 * @@version 1.0
 */
public interface WEB3FeqOrderCarryOverUnitService extends Service 
{
    
    /**
     * (execถJz)<BR>
     * ฺqPสลถJz๐ภ{ท้B
     * @@param l_mainAccount - (ฺq)<BR>
     * ฺqIuWFNg
     * @@throws WEB3BaseException
     * @@roseuid 42B8A35F0327
     */
    public void execOrderCarryOver(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException;
}
@
