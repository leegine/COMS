head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : OฎoสmPT[rX(WEB3AdminFeqExecutionOneNotifyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/12 ๊ฯ (u) VK์ฌ 
                   2006/10/17 ๅ๛(u) fNo.288ฮ
                   2006/12/15 ๊ฯ (u) fNo.311ฮ
                   2006/12/19 ๊ฯ (u) fNo.319ฮ   
                   2006/12/20 ๊ฯ (u) fNo.323ฮ
Revesion History : 2008/10/02 ๅเV(SRA) yOฎzdlฯXวไ ifjNo.468               
Revesion History : 2010/03/05 g (u)yOฎzdlฯXวไ ifjNo.541
*/

package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;
import webbroker3.slebase.data.SleRcvdQParams;

/**
 * (OฎoสmPT[rX)<BR>
 * OฎoสmPT[rXC^tFCX <BR>
 * <BR>
 * igUNVฎซFTransactionalInterceptor.TX_JOIN_EXISTINGj<BR>
 *   
 * @@author ๊ฯ
 * @@version 1.0
 */
public interface WEB3FeqExecutionNotifyUnitService extends Service
{

    /**
     * notify๑่<BR>
     * ๑่๐sคB<BR>
     * @@param l_feqOrderUnit - (ถPส)<BR>
     * ถPส<BR>
     * @@param l_hostFeqOrderExecNotifyParams - (OoสmL[)<BR>
     * OoสmL[<BR>
     * @@param l_sleRvcdQParams - (Oๆ๘RCVD_Q)<BR>
     * Oๆ๘RCVD_Q<BR>
     * @@param l_todayLoginFlag - (๚ืึo^tO)<BR>
     * ๚ืึo^tO<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FECAF006D
     */
    public void notifyOrder(
    	WEB3FeqOrderUnit l_feqOrderUnit, 
    	HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams,
    	SleRcvdQParams l_sleRvcdQParams,
        Boolean l_todayLoginFlag)
        throws WEB3BaseException;
}
@
