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
filename	WEB3IfoBalanceReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP残高照会サービスインタフェイス(WEB3IfoBalanceReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * (先物OP残高照会サービス)<BR>
 * 先物OP残高照会サービスインタフェイス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public interface WEB3IfoBalanceReferenceService extends WEB3BusinessService 
{
   
    /**
     * 株価指数先物/オプション残高照会処理を行う。
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41AAC9CD011E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
