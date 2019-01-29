head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正取消通知サービスインタフェイス(WEB3FuturesChangeCancelNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 盧法@旭 (中訊) 新規作成
*/


package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;


/**
 * (先物訂正取消通知サービス)<BR>
 * 株価指数先物訂正取消通知サービスインタフェイス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public interface WEB3FuturesChangeCancelNotifyService extends WEB3BackBusinessService 
{
    
    /**
     * 株式指数先物訂正取消通知サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A89DDE02B8
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
