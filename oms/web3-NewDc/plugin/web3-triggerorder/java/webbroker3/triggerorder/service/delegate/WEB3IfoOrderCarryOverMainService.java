head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ζ¨OPΆJzCT[rX(WEB3IfoOrderCarryOverMainService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/21 ζβΡQ(u) VKμ¬ fNo.669
Revision History : 2007/07/11 ζβΡQ(u) fNo.774
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (ζ¨OPΆJzCT[rX)<BR>
 * ζ¨OPΆJzCT[rXC^tFCX<BR>
 * <BR>
 * @@author ζβΡQ
 * @@version 1.0
 */

public interface WEB3IfoOrderCarryOverMainService extends WEB3BackBusinessService
{
    /**
     * ζ¨OPΆJzπΐ{·ιB<BR>
     * @@param l_request - NGXg<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;

    /**
     * (createϊΆ)<BR>
     * ϊΆπμ¬·ιB<BR>
     * @@param l_mainAccount  - (Ϊq)<BR>
     * ΪqIuWFNg<BR>
     * @@param l_strFutureOptionDiv - (ζ¨^IvVζͺ)<BR>
     * ζ¨^IvVζͺ<BR>
     * @@param l_strCarryoverProcessType - (ΆJzζͺ)<BR>
     * ΆJzζͺ<BR>
     * @@throws WEB3BaseException
     */
    public void createNextOrder(
        MainAccount l_mainAccount,
        String l_strFutureOptionDiv,
        String l_strCarryoverProcessType) throws WEB3BaseException;
}
@
