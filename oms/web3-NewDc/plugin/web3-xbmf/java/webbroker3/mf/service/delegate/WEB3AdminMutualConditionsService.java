head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ų\[VVXeęń
File Name        : ĒŅMĮæšo^T[rX@@C^[tFCX(WEB3AdminMutualConditionsService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ¤ä(u) VKģ¬
                   2004/08/25 ččOął (u) r[    
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * ĒŅMĮæšo^T[rX@@C^[tFCXNX<BR>
 * 
 * @@author ¤ä(u)
 * @@version 1.0
 */
public interface WEB3AdminMutualConditionsService extends WEB3BusinessService 
{
    
    /**
     * ĒŅMõ Įæšo^šĄ{·éB<BR>
     * @@param l_request - NGXgf[^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3C53601ED
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
