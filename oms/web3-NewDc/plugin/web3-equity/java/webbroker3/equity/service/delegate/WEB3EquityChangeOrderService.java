head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeOrderService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ®¶ù³T[rX(WEB3EquityChangeOrderService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 üææ (u) VKì¬
                   2004/12/15 öõF(SRA) cÄÎÉæéC³
                   2005/01/06 ªºa¾(SRA) JavaDocC³
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * i®¶ù³T[rXjB<BR>
 * <BR>
 * ®¶ù³T[rXC^[tF[X
 * @@version 1.0
 */
public interface WEB3EquityChangeOrderService extends WEB3BusinessService
{
    /**
     * (execute)<BR>
     * <BR>
     * »¨®¶ù³T[rXðÀ{·éB<BR>
     * <BR>
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4042C943028C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException;
}
@
