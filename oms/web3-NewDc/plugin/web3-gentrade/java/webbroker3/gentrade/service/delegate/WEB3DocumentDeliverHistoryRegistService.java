head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åaĪ Ø\[VVXeæņ
File Name        : Ęðtðo^T[rX(WEB3DocumentDeliverHistoryRegistService)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 åāVė@@(SRA) VKėŽ
*/
package webbroker3.gentrade.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (Ęðtðo^T[rX)<BR>
 * <BR>
 * Ęðtðo^T[rXC^[tFCX<BR>
 * @@author åāVė@@
 * @@version 1.0
 */
public interface WEB3DocumentDeliverHistoryRegistService extends WEB3BusinessService 
{

    /**
     * (execute)<BR>
     * <BR>
	 * Ęðtðo^T[rXðsĪB<BR>
     * <BR>
     * @@param l_request NGXg
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
	 */
	public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;

}
@
