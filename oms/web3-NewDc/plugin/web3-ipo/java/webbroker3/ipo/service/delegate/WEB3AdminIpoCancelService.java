head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.33.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPO~T[rXC^tFCX(WEB3AdminIpoCancelService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 Cg (u) VKì¬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * ÇÒIPO~T[rXC^tFCX
 * 
 * @@author Cg
 * @@version 1.0
 */
public interface WEB3AdminIpoCancelService extends WEB3BusinessService 
{
    
    /**
     * ÇÒIPO~ðÀ{·éB
     * @@param l_request - NGXg
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40D27FB302E5
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
