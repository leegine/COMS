head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOpenContractInputService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : ()åa¤ Ø\[VVXeæñ
File Name           : OPVKüÍT[rX(WEB3OptionOpenContractInputService.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/15 àò (u) VKì¬
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (OPVKüÍT[rX)<BR>
 * <BR>
 * ¿wIvVVKüÍT[rXC^tF[X<BR>
 * @@author àò
 * @@version 1.0
 */
public interface WEB3OptionOpenContractInputService extends WEB3BusinessService 
{
    
    /**
     * ¿wIvVVKüÍT[rXðÀ{·éB
     * @@param l_request - NGXg
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 407A51FD00CB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
