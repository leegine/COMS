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
filename	WEB3FuturesOrderExecNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物出来通知サービスインタフェイス(WEB3FuturesOrderExecNotifyService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 艾興 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (先物出来通知サービス)<BR>
 * 株価指数先物出来通知サービスインタフェイス<BR>
 */
public interface WEB3FuturesOrderExecNotifyService
    extends WEB3BackBusinessService
{

    /**
     * 株式指数先物出来通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物サービス）先物出来通知」参照。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesOrderExecNotifyResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A83F5200DD
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;
}
@
