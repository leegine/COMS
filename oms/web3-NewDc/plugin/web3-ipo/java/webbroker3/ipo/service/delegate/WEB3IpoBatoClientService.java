head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBatoClientService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : IPOdqµÚ±T[rX(WEB3IpoBatoClientService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/26 Âûg(u) VKì¬
*/
package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (IPOdqµÚ±T[rX)<BR>
 * IPOdqµÚ±T[rX
 *
 * @@author Âûg
 * @@version 1.0
 */
public interface WEB3IpoBatoClientService extends WEB3BusinessService 
{
    
    /**
     * IPOÉ¨¯édqµÚ±ðÀ{·éB
     * @@param l_request - NGXg
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43D07FFF0050
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
