head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.16.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeCompleteUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : UÖ®¹UnitService(WEB3AccTransChangeCompleteUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 °üí (u) VKì¬
                   2004/10/22 ¤ä(u) r[                                       
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (UÖ®¹UnitService)<BR>
 * UÖ®¹UnitServiceC^[tFCX<BR>
 * 
 * @@author °üí(u)
 * @@version 1.0
 */
public interface WEB3AccTransChangeCompleteUnitService extends Service
{

    /**
     * (completeUÖ)
     * @@param l_orderUnit - (¶PÊIuWFNg)
     * @@throws WEB3BaseException
     * @@roseuid 413C22410253
     */
    public void completeChange(AioOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@
