head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資MRF注文受付サービスインターフェイス(WEB3RuitoMRFOrderAcceptService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate;

import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;

/**
 * 累積投資MRF注文受付サービスインターフェイス<BR>
 */
public interface WEB3RuitoMRFOrderAcceptService extends WEB3BackBusinessService
{

    /**
     * 累積投資MRF注文受付サービス処理を実施する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 405814510021
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
