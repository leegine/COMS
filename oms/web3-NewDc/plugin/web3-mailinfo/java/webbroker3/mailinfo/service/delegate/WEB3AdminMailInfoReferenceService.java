head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報一覧サービス(WEB3AdminMailInfoReferenceService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (メール情報一覧サービス)<BR>
 * メール情報一覧サービスインターフェイス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public interface WEB3AdminMailInfoReferenceService extends WEB3BusinessService 
{    
    /**
     * メール情報一覧照会処理を行う。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C46A3001D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
