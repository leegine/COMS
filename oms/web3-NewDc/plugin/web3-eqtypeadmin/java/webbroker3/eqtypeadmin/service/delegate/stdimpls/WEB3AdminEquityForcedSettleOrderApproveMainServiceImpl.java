head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認メインサービスImpl(WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveMainService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認メインサービスImpl)<BR>
 * 管理者・強制決済仮注文承認／非承認メインサービス実装クラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl
    implements WEB3AdminEquityForcedSettleOrderApproveMainService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl.class);

    /**
     * @@roseuid 462CA423039E
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl()
    {

    }

    /**
     * （非同期）強制決済仮注文承認／非承認処理を起動する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者強制決済仮注文承認／非承認メインサービス）execute」参照。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 46032847029F
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveMainRequest l_approveMainRequest =
            (WEB3AdminEquityForcedSettleOrderApproveMainRequest)l_request;

        WEB3BackResponse l_response = null;

        //validate( )
        l_approveMainRequest.validate();

        //startThread(l_lngThreadNo : long)
        WEB3GentradeDaemonTriggerManager l_manager = new WEB3GentradeDaemonTriggerManager();
        l_manager.startThread(l_approveMainRequest.threadNo.longValue());

        //（非同期）管理者・強制決済仮注文承認／非承認サービスImpl
        WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_approveServiceImpl =
            new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl(l_approveMainRequest);

        //execute(arg0 : Runnable)
        //（非同期）強制決済仮注文承認／非承認処理を行う。
        //[引数]
        //arg0：　@生成した（非同期）管理者・強制決済仮注文承認／非承認サービスImpl
        WEB3AsynExecuteService l_executeService =
            (WEB3AsynExecuteService)Services.getService(WEB3AsynExecuteService.class);
        l_executeService.execute(l_approveServiceImpl);

        //createResponse( )
        l_response = l_approveMainRequest.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
