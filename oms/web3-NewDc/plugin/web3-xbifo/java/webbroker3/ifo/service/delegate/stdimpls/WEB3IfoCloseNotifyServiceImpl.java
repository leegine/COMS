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
filename	WEB3IfoCloseNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知サービスImpl(WEB3IfoCloseNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/18 盧法@旭 (中訊) 新規作成
              001: 2004/07/23 王暁傑 (中訊) WEB3HostRequestCodeDefでWEB3IfoRequestCodeTypeDefを差し替える
              002: 2004/07/29 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000063 execute()を修正
              003: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyService;

/**
 * (先物OP失効通知サービスImpl)<BR>
 * 先物OP失効通知サービス実装クラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyServiceImpl implements WEB3IfoCloseNotifyService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoCloseNotifyServiceImpl.class);    
    /**
     * @@roseuid 40C0752E02CE
     */
    public WEB3IfoCloseNotifyServiceImpl() 
    {
     
    }
    
    /**
     * 先物OP失効通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OPサービス）失効通知」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4088F4C60086
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3IfoCloseOrderResponse execute(WEB3IfoCloseOrderRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3IfoCloseOrderRequest l_ifoCloseOrderRequest = (WEB3IfoCloseOrderRequest)l_request;

        // スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_ifoCloseOrderRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynIfoCloseNotifyServiceImpl(
            l_ifoCloseOrderRequest));

        log.exiting(STR_METHOD_NAME);
        return l_ifoCloseOrderRequest.createResponse();
    }   
 }

@
