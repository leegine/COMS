head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferForceUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΨUΦ­§UnitService(WEB3AioSecurityTransferForceUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 όz (u) VKμ¬   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostMrgsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (ΨUΦ­§UnitService)<BR>
 * ΨUΦ­§UnitServiceC^[tFCX
 * 
 * @@author όz(u)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferForceUnitService extends Service 
{
    
    /**
     * (submitΆ)<BR>
     * ΨUΦΜΆπo^΅AzρπζΎ·ιB
     * @@param l_params - γpUΦ­§L[e[uΜsIuWFNg
     * @@return AioOrderUnit[]
     * @@roseuid 4157961901F5
     */
    public AioOrderUnit[] submitOrder(HostMrgsecTransNotifyParams l_params) 
        throws WEB3BaseException;
}
@
