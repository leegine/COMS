head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           :先物OP訂正取消受付サービス実装クラス(WEB3IfoChangeCancelOrderAcceptServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22   王暁傑 (Sinocom) 新規作成
              001: 2004/08/14 王暁傑 対応 【株価指数オプション】ソースコードチェック指摘事項(JP)200408
*/
package webbroker3.ifo.service.delegate.stdimpls;


import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifo.message.WEB3IfoChangeCancelAcceptRequest;
import webbroker3.ifo.message.WEB3IfoChangeCancelAcceptResponse;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * (先物OP訂正取消受付サービスImpl)<BR>
 * 先物OP訂正取消受付サービス実装クラス<BR>
 */
public class WEB3IfoChangeCancelOrderAcceptServiceImpl
    implements WEB3IfoChangeCancelOrderAcceptService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelOrderAcceptServiceImpl.class);

    /**
     * @@roseuid 40C0752F00BB
     */
    public WEB3IfoChangeCancelOrderAcceptServiceImpl()
    {

    }

    /**
     * 先物OP訂正取消受付サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物OPサービス）先物オプション訂正取消受付」参照。<BR>
     * @@param l_request リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4083833A026D
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3IfoChangeCancelAcceptRequest l_inRequest = (WEB3IfoChangeCancelAcceptRequest)l_request;
        WEB3IfoChangeCancelAcceptResponse l_response = (WEB3IfoChangeCancelAcceptResponse) l_inRequest.createResponse();

        // スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_inRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AysnIfoChangeCancelOrderAcceptServiceImpl(l_inRequest));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
