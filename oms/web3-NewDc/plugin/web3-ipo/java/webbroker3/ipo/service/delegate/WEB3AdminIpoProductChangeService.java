head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÁ¿ù³T[rX(WEB3AdminIpoProductChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 dÙ (u) VKì¬
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (ÇÒIPOÁ¿ù³T[rX)<BR>
 * ÇÒIPOÁ¿ù³T[rXC^tFCX
 * 
 * @@author dÙ
 * @@version 1.0
 */
public interface WEB3AdminIpoProductChangeService extends WEB3BusinessService 
{
    
    /**
     * ÇÒIPOÁ¿ù³ðÀ{·éB
     * @@param l_request - NGXgf[^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 40C94B400069
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
