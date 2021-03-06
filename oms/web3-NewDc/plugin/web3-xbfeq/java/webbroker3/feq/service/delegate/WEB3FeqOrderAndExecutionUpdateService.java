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
filename	WEB3FeqOrderAndExecutionUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : Oฎo^๑่XVT[rX(WEB3FeqOrderAndExecutionUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 เ๒(u) VK์ฌ
                 : 2005/07/26 ค๛U(u) r[
*/
package webbroker3.feq.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.feq.data.HostFeqOrderExecNotifyParams;


/**
 * (Oฎo^๑่XVT[rX) <BR>
 * Oฎo^๑่XVT[rXC^tFCX <BR>
 * TransactionalInterceptor.TX_JOIN_EXISTING <BR>
 * 
 * @@ author เ๒ 
 * @@ version 1.0
 */
public interface WEB3FeqOrderAndExecutionUpdateService extends Service
{
    
    /**
     * (update๑่๎๑) <BR>
     * o^๑่XV๐sค <BR>
     *  <BR>
     * V[PX} <BR>
     * ui๑่๎๑XVjupdate๑่๎๑vQฦB <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (OoสmL[s) <BR>
     * OoสmL[sIuWFNg <BR>
     *  <BR>
     * ฆOoสmL[ParamsอDDLๆ่ฉฎถฌท้B <BR>
     * @@throws WEB3BaseException
     * @@roseuid 428C039102C5
     */
    public void updateExecuteUnit(HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams) 
        throws WEB3BaseException;
    
    /**
     * (update๑่ๆม) <BR>
     * o^๑่ๆมXV๐sค <BR>
     *  <BR>
     * V[PX} <BR>
     * ui๑่๎๑XVjupdate๑่ๆมvQฦB <BR>
     * @@param l_hostFeqOrderExecNotifyParams - (OoสmL[s) <BR>
     * OoสmL[sIuWFNg <BR>
     *  <BR>
     * ฆOoสmL[ParamsอDDLๆ่ฉฎถฌท้B <BR>
     * 
     * @@param l_lngExecuteId - (๑่hc)
     * @@throws WEB3BaseException
     * @@roseuid 428C039102D5
     */
    public void updateExecuteCancel(
        HostFeqOrderExecNotifyParams l_hostFeqOrderExecNotifyParams, 
        long l_lngExecuteId)throws WEB3BaseException;
}
@
