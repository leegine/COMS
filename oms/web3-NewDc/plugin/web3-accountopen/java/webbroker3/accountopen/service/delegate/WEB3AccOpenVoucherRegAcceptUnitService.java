head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.45.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ûÀJÝ`[o^ótUnitService(WEB3AccOpenVoucherRegAcceptUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/16 ACÇ(u) VKì¬
*/

package webbroker3.accountopen.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (ûÀJÝ`[o^ótUnitService)<BR>
 * ûÀJÝ`[o^ótPT[rXC^tFCX<BR>
 *   
 * @@author ACÇ
 * @@version 1.0
 */
public interface WEB3AccOpenVoucherRegAcceptUnitService extends Service 
{
    
    /**
     * (notify`[o^ót)<BR>
     * ûÀJÝ`[o^ótPðÀ{µAÊiÏ^G[jð<BR>
     * Ôp·éB<BR>
     * @@param l_accOpenAcceptParams - ûÀJÝ`[o^ótL[<BR>
     * <BR>
     * ¦@@ûÀJÝ`[o^ótL[ParamsNXÍADDLæè©®¶¬·éB<BR>
     * 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41B169B402DE
     */
    public String notifyVoucherRegAccept(HostAccOpenAcceptParams l_accOpenAcceptParams) throws WEB3BaseException;
}
@
