head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           :先物OP注文受付サービス実装クラス(WEB3IfoOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/21   王暁傑 (Sinocom) 新規作成
              001: 2004/07/28  王暁傑 (Sinocom) 対応バッグ WEB3_IFO_UT-000046
              002: 2004/07/29  盧法@旭 (Sinocom) 対応バッグ WEB3_IFO_UT-000061
**/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.message.WEB3IfoOrderAcceptRequest;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文受付サービスImpl)<BR>
 * 先物OP注文受付サービス実装クラス<BR>
 */
public class WEB3IfoOrderAcceptServiceImpl implements WEB3IfoOrderAcceptService
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3IfoOrderAcceptServiceImpl.class);
    /**
     * @@roseuid 40C0752F0119
     */
    public WEB3IfoOrderAcceptServiceImpl()
    {

    }

    /**
     * 先物OP注文受付サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OPサービス）先物オプション注文受付」参照。<BR>
     * @@param l_request - リクエストデータ
     *
     * @@return webbroker3.ifo.message.WEB3IfoOrderAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057CF4501C7
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(l_request)";
        log.entering( STR_METHOD_NAME);

        WEB3IfoOrderAcceptRequest l_orderAcceptRequest = (WEB3IfoOrderAcceptRequest)l_request;
        // スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_orderAcceptRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynIfoOrderAcceptServiceImpl(l_orderAcceptRequest));
        
        log.exiting(STR_METHOD_NAME);
        return l_orderAcceptRequest.createResponse();
    }
}
@
