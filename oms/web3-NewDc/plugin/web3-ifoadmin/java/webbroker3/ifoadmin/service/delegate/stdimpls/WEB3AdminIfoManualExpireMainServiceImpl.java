head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効メインサービスImpl(WEB3AdminIfoManualExpireMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireMainService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効メインサービスImpl)<BR>
 * 管理者・先物OP手動失効メインサービス実装クラス<BR>
 * （非同期処理を行う為のエントリークラス）<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */

public class WEB3AdminIfoManualExpireMainServiceImpl implements WEB3AdminIfoManualExpireMainService 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireMainServiceImpl.class);
     
    /**
     * @@roseuid 447AC0CE03B9
     */
    public WEB3AdminIfoManualExpireMainServiceImpl() 
    {
     
    }
    
    /**
     *（非同期）手動失効処理を起動する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（管理者先物OP手動失効メインサービス）execute」参照。<BR>
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
        WEB3AdminIfoManualLapseMainRequest l_mainRequest = null;
        if (l_request instanceof WEB3AdminIfoManualLapseMainRequest)
        {
            l_mainRequest = (WEB3AdminIfoManualLapseMainRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 管理者・株式手動失効メインリクエスト");
        }
        
        // リクエストデータの整合性をチェックする。
        l_mainRequest.validate();
        
        // デーモントリガーの該当レコードを"処理中"でupdateする。
        WEB3GentradeDaemonTriggerManager l_manager = new WEB3GentradeDaemonTriggerManager();
        l_manager.startThread(l_mainRequest.threadNo.longValue());
        
        // （非同期）管理者・株式手動失効サービスImplを生成する。
        WEB3AsynAdminIfoManualExpireServiceImpl l_expireServiceImpl = 
            new WEB3AsynAdminIfoManualExpireServiceImpl(l_mainRequest);
        
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);

        // （非同期）株式手動失効処理を行う。
        //[引数]
        // arg0：　@生成した（非同期）管理者・株式手動失効サービスImpl
        l_executeService.execute(l_expireServiceImpl);
        
        // レスポンスデータを生成する。
        l_response = l_mainRequest.createResponse();
        
        //  return
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
