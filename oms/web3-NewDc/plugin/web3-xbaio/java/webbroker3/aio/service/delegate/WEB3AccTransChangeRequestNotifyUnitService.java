head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeRequestNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : UΦΏΚmUnitService(WEB3AccTransChangeRequestNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 °όν (u) VKμ¬
                   2004/10/26 όE(u) r[
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.data.HostTransferReceiptParams;
import webbroker3.common.WEB3BaseException;

/**
 * (UΦΏΚmUnitService)<BR>
 * UΦΏΚmUnitServiceC^[tFCX<BR>
 * 
 * @@author °όν(u)
 * @@version 1.0
 */
public interface WEB3AccTransChangeRequestNotifyUnitService extends Service
{

    /**
     * (submitΆ)<BR>
     * SONAR©ηΜUΦΆΜo^πs’AVKΆΜΆIDπΤp·ιB<BR>
     * @@param l_hostTransferReceiptParams - (UΦόΝΚmL[ParamsIuWFNg)<BR>
     * @@param l_orderType - (ΆνΚ)
     * @@param l_changeType - (UΦ^Cv)
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 413C2CC401E5
     */
    public long submitOrder(
        HostTransferReceiptParams l_hostTransferReceiptParams,
        OrderTypeEnum l_orderType,
        AssetTransferTypeEnum l_changeType)
            throws WEB3BaseException;
}
@
