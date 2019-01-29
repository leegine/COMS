head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualExpireMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効メインサービスImpl(WEB3AdminToManualExpireMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireMainService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・手動失効メインサービスImpl)<BR>
 * （WEB3AdminToManualExpireMainServiceImpl）<BR>
 * トリガー注文管理者・手動失効メインサービス実装クラス<BR>
 * （非同期処理を行う為のエントリークラス）<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToManualExpireMainServiceImpl implements WEB3AdminToManualExpireMainService 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToManualExpireMainServiceImpl.class);
    
    /**
     * @@roseuid 4419312B03A9
     */
    public WEB3AdminToManualExpireMainServiceImpl() 
    {
     
    }
    
    /**
     * （非同期）手動失効処理を起動する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・手動失効メインサービス）execute」参照。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 440E806C03A2
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        if (!(l_request instanceof WEB3AdminToManualLapseMainRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3AdminToManualLapseMainRequest l_mainRequest = (WEB3AdminToManualLapseMainRequest) l_request;
        log.debug("***** メインリクエストデータ内容 *****");
        log.debug("証券会社コード：[" + l_mainRequest.institutionCode + "]");
        log.debug("スレッドNo：[" + l_mainRequest.threadNo + "]");
        log.debug("From口座ID：[" + l_mainRequest.rangeFrom + "]");
        log.debug("To口座ID：[" + l_mainRequest.rangeTo + "]");
        log.debug("失効対象注文条件：[" + l_mainRequest.lapseTargetOrderCondition + "]");
        
        //1.1 validate( )
        l_mainRequest.validate();
        
        //1.2 startThread(l_lngThreadNo : long)
        //デーモントリガーの該当レコードを"処理中"でupdateする。
        //※二重起動の防止。
        WEB3GentradeDaemonTriggerManager l_triggerManager = new WEB3GentradeDaemonTriggerManager();
        l_triggerManager.startThread(l_mainRequest.threadNo.longValue());
        
        //1.3 （非同期）トリガー注文管理者・手動失効サービスImpl(トリガー注文管理者・手動失効メインリクエスト)
        WEB3AsynAdminToManualExpireServiceImpl l_serviceImpl = 
            new WEB3AsynAdminToManualExpireServiceImpl(l_mainRequest);
        
        //1.4 execute(arg0 : Runnable)
        //非同期処理なので、メソッドの終了を待たない。
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
        l_executeService.execute(l_serviceImpl);
        
        //1.5 createResponse( )
        WEB3BackResponse l_mainResponse = l_mainRequest.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_mainResponse;
    }
}
@
