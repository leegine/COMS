head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.44.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInformAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : eνAσtUnitService(WEB3AccOpenInformAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/05/28 Δog (u) VKμ¬ f No.123, No.127, No.135
Revision History : 2007/06/12 Δog (u) f No.141
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.inform.data.VariousInformParams;

/**
 * (eνAσtUnitService)<BR>
 * eνAσtPT[rXC^tFCX<BR>
 * <BR>
 * @@author Δog
 * @@version 1.0
 */
public interface WEB3AccOpenInformAcceptUnitService extends Service
{
    /**
     * (notifyeνAσt)<BR>
     * eνAσt1πΐ{·ιB<BR>
     * <BR>
     * @@param l_hostAccOpenAcceptParams - (σtL[Params)<BR>
     * ϋΐJέ`[o^σtL[Params<BR>
     * @@param l_variousInformParams - (eνAParams)<BR>
     * eνAParams<BR>
     * @@param l_strProcessDiv - (ζͺ)<BR>
     * ζͺ<BR>
     * <BR>
     * 1F Ο<BR>
     * 9F G[<BR>
     * @@throws WEB3BaseException
     */
    public void notifyInformAccept(
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
        VariousInformParams l_variousInformParams,
        String l_strProcessDiv) throws WEB3BaseException;
}
@
