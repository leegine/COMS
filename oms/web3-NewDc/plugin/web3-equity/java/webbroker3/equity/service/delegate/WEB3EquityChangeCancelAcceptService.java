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
filename	WEB3EquityChangeCancelAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ®ù³æÁótT[rX(WEB3EquityChangeCancelAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 üææ (u) VKì¬
                   2004/09/14 Cg (u) C³(javaDoc)
                   2004/09/22 á¸@@®(u) C³
                   2005/01/06 ªºa¾(SRA) JavaDocC³
*/
package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * i®ù³æÁótT[rXjB
 * @@author @@®
 * @@version 1.0
 */
public interface WEB3EquityChangeCancelAcceptService
    extends WEB3BackBusinessService
{
    
    /**
     * ¶ù³æÁótðÀ{·é<BR>
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
