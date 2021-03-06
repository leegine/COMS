head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.15.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOutputNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : oΙΚmUnitService(WEB3AioOutputNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 θθOΰω (u) VKμ¬   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.aio.data.HostSpsecTransNotifyParams;
import webbroker3.common.WEB3BaseException;


/**
 * (oΙΚmUnitService)<BR>
 * oΙΚmUnitServiceC^[tFCX
 * 
 * @@author θθOΰω(u)
 * @@version 1.0
 */
public interface WEB3AioOutputNotifyUnitService extends Service 
{
    
    /**
     * (notifyoΙΚm)<BR>
     * oΙΚmπs€B
     * @@param l_strInstitutionCode - ΨοΠR[h
     * @@param l_strBranchCode - XR[h
     * @@param l_strAccountCode - ΪqR[h
     * @@param l_strProductCode - ΑΏR[h
     * @@param l_strCustodyDiv - ΫΗζͺ
     * @@param l_strSpecificDiv - Αθϋΐζͺ
     * @@param l_lngQuantity - Κ
     * @@param l_strCommodityDiv - €iζͺ
     * @@param l_strCancelDiv - ζΑζͺ
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4157961901F5
     */
    public String notifyOutputNotify(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strProductCode, 
        String l_strCustodyDiv, 
        String l_strSpecificDiv, 
        Long l_lngQuantity,
	    String l_strCommodityDiv,
	    String l_strCancelDiv) 
        throws WEB3BaseException;
}
@
