head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΨUΦΚmUnitServiceC^[tFCX(WEB3AioSecurityTransferNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 €δ(u) VKμ¬
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (ΨUΦΚmUnitService)<BR>
 * ΨUΦΚmUnitServiceC^[tFCX
 * 
 * @@author €δ(u)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferNotifyUnitService extends Service 
{
    
    /**
     * ΨUΦΚmπs€B
     * @@param l_aioOrderUnit - ΆPΚIuWFNgΜzρ
     * @@param l_errorCode - G[R[h
     * @@param l_acceptNotifyDiv - σtΚmζͺ
     * @@roseuid 415792E003BA
     */
    public void execute(AioOrderUnit[] l_aioOrderUnit, String l_errorCode, String l_acceptNotifyDiv)
        throws WEB3BaseException;
}
@
