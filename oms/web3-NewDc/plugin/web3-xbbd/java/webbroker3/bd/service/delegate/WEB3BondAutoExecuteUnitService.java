head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecuteUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ยฉฎ๑่UnitT[rX(WEB3BondAutoExecuteUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 ๅ๛ (u) VK์ฌ
*/

package webbroker3.bd.service.delegate;


import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (ยฉฎ๑่UnitT[rX)<BR>
 * ยฉฎ๑่UnitT[rXC^[tF[X
 * 
 * @@author ๅ๛
 * @@version 1.0
 */

public interface WEB3BondAutoExecuteUnitService extends Service
{
    
    /**
     * (notifyฉฎ๑่)<BR>
     * ฉฎ๑่๐ท้ <BR>
     * @@param l_bondOrderUnit - (ยถPส)<BR>
     * ยถPส<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D7227703CC
     */
    public void notifyAutoExecute(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException;
}
@
