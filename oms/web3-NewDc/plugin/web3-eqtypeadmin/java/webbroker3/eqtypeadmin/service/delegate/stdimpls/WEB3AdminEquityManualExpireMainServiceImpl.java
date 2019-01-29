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
filename	WEB3AdminEquityManualExpireMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式手動失効メインサービスImpl(WEB3AdminEquityManualExpireMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/31 呉艶飛 (中訊) 新規作成
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireMainService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・株式手動失効メインサービスImpl)<BR>
 * 管理者・株式手動失効メインサービス実装クラス<BR>
 * （非同期処理を行う為のエントリークラス）<BR>
 * <BR>
 * @@author 呉艶飛
 * @@version 1.0
 */

public class WEB3AdminEquityManualExpireMainServiceImpl implements WEB3AdminEquityManualExpireMainService 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireMainServiceImpl.class);
     
    /**
     * @@roseuid 447AC0CE03B9
     */
    public WEB3AdminEquityManualExpireMainServiceImpl() 
    {
     
    }
    
    /**
     *（非同期）手動失効処理を起動する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者株式手動失効メインサービス）execute」参照。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 44698678027F
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BackResponse l_response = null;
        WEB3AdminEquityManualLapseMainRequest l_mainRequest = null;
        if (l_request instanceof WEB3AdminEquityManualLapseMainRequest)
        {
            l_mainRequest = (WEB3AdminEquityManualLapseMainRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 管理者・株式手動失効メインリクエスト");
        }
        
        //1.1リクエストデータの整合性をチェックする。
        l_mainRequest.validate();
        
        //1.2デーモントリガーの該当レコードを"処理中"でupdateする。
        WEB3GentradeDaemonTriggerManager l_manager = new WEB3GentradeDaemonTriggerManager();
        l_manager.startThread(l_mainRequest.threadNo.longValue());
        
        //1.3（非同期）管理者・株式手動失効サービスImplを生成する。
        WEB3AsynAdminEquityManualExpireServiceImpl l_expireServiceImpl = 
            new WEB3AsynAdminEquityManualExpireServiceImpl(l_mainRequest);
        
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);

        //1.4（非同期）株式手動失効処理を行う。
        //[引数]
        // arg0：　@生成した（非同期）管理者・株式手動失効サービスImpl
        l_executeService.execute(l_expireServiceImpl);
        
        //1.5レスポンスデータを生成する。
        l_response = l_mainRequest.createResponse();
        
        //1.6 return
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
