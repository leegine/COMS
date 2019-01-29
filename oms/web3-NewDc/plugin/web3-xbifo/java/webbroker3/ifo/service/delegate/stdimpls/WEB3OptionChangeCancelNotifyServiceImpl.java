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
filename	WEB3OptionChangeCancelNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正取消通知サービスImpl(WEB3OptionChangeCancelNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/15 盧法@旭 (中訊) 新規作成
              001: 2004/07/23 王暁傑 (中訊) IfoOrderExecutionConditionTypeでWEB3IfoModifiedExecutionTypeDefを差し替える
              002: 2004/07/29 王暁傑 (中訊) excute()の引数を修正 対応バッグ WEB3_IFO_UT-000053
              003: 2004/07/29 王暁傑 (中訊) excute()の引数を修正 対応バッグ WEB3_IFO_UT-000062
                                                                            WEB3_IFO_UT-000053の再修正 
              004: 2004/07/29 王暁傑 (中訊) process()を修正 対応バッグ WEB3_IFO_UT-000091
              005: 2004/8/5 盧法@旭   process()を修正 対応バッグ WEB3_IFO_UT-000115
              006: 2004/08/06 王暁傑 (中訊) process()を修正 対応バッグ WEB3_IFO_UT-000120
              007: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              
**/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyRequest;
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyService;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP訂正取消通知サービスImpl)<BR>
 * 株価指数オプション訂正取消通知サービス実装クラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyServiceImpl implements WEB3OptionChangeCancelNotifyService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3OptionChangeCancelNotifyServiceImpl.class);

    /**
    /**
     * @@roseuid 40C0752D033C
     */
    public WEB3OptionChangeCancelNotifyServiceImpl()
    {

    }

    /**
     * 株式指数オプション訂正取消通知サービス処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OPサービス）オプション訂正取消通知」参照。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057D2570198
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3OptionsChangeCancelNotifyResponse execute(WEB3OptionsChangeCancelNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("The request parameter is null");
            throw new WEB3BaseException
                (WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
        }

        WEB3OptionsChangeCancelNotifyRequest l_optionsChangeCancelNotifyRequest =
            (WEB3OptionsChangeCancelNotifyRequest) l_request;

        WEB3OptionsChangeCancelNotifyResponse l_response =
            (WEB3OptionsChangeCancelNotifyResponse) l_optionsChangeCancelNotifyRequest.createResponse();

        // スレッド開始
        new WEB3GentradeDaemonTriggerManager().startThread(l_optionsChangeCancelNotifyRequest.threadNo.longValue());
        // 1.3. 非同期実行
        WEB3AsynExecuteService l_service =
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_service.execute(new WEB3AsynOptionChangeCancelNotifyServiceImpl(
            l_optionsChangeCancelNotifyRequest));

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
