head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecutionNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付出来通知サービスImpl(WEB3FeqOrderAcceptExecutionNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyRequest;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptExecutionNotifyService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文受付出来通知サービスImpl)<BR>
 * 外国株式注文受付出来通知サービス実装クラス<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptExecutionNotifyServiceImpl implements WEB3FeqOrderAcceptExecutionNotifyService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecutionNotifyServiceImpl.class);
    
    /**
     * 外国株式注文受付出来通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * <BR>
     * 「（外国株式注文受付出来通知サービス）注文受付出来通知」参照。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストオブジェクト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        if (!(l_request instanceof WEB3FeqOrderAcceptExecNotifyRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3FeqOrderAcceptExecNotifyRequest l_orderAcceptExecNotifyRequest = 
            (WEB3FeqOrderAcceptExecNotifyRequest)l_request;

        //1.1 リクエストデータの整合性チェックを行う
        l_orderAcceptExecNotifyRequest.validate();
        
        WEB3FeqOrderAcceptExecNotifyResponse l_response = 
        	(WEB3FeqOrderAcceptExecNotifyResponse) l_orderAcceptExecNotifyRequest.createResponse();
        //1.2 スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(
        	l_orderAcceptExecNotifyRequest.threadNo.longValue());
        
        //1.3 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService)Services.getService(WEB3AsynExecuteService.class);
        
        //1.4 非同期処理を実行する。
        l_service.execute(new WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl(
        	l_orderAcceptExecNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
