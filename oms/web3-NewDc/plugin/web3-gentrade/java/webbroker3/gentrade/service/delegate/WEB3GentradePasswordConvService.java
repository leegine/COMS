head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÃØÔÏ·T[rXC^tFCX(WEB3GentradePasswordConvService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ì(rq`) VKì¬
*/
package webbroker3.gentrade.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountResponse;

/**
 * ÃØÔÏ·T[rXÀNX<br /> 
 * <br />
 * @@author rq`ì
 */
public interface WEB3GentradePasswordConvService 
	extends WEB3BackBusinessService
{
	/**
	 * ÃØÔÏ·T[rXðs¤B<br />
	 * <br />
	 * @@param l_request - NGXgf[^
	 * @@exception  SYSTEM_ERROR_80003:@@DBG[
	 * @@return WEB3BackResponse<br />
	 * @@roseuid 421036A8039E
	 */
	public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;

}
@
