head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : MΆΚmUnitService(WEB3MutualTradeOrderNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ©  (u) VKμ¬
                   2004/08/24 €δ (u) r[
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.mf.data.HostXbmfOrderNotifyParams;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (MΆΚmUnitService)
 * MΆΚmPT[rXC^tF[XMΆΚmP²ΖΜπΐ{·ιB<BR>
 * 
 * @@author ©(u)
 * @@version 1.0
 */

public interface WEB3MutualTradeOrderNotifyUnitService extends Service 
{
    
    /**
     * (notifyΆΚm)<BR>
     * MΆΚmπs€B<BR>
     * @@param l_orderNotifyQueueParams - MΆΚmL[Params<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40BEF93003E7
     */
    public void notifyTradeOrderNotify(HostXbmfOrderNotifyParams l_orderNotifyQueueParams)
    	throws WEB3BaseException;
}
@
