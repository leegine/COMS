head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携サービスImpl(WEB3AioCashinCooperationServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/12 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.message.WEB3AioCashinCooperationNotifyRequest;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連携サービスImpl) <BR>
 * 入金連携サービス実装クラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationServiceImpl implements WEB3AioCashinCooperationService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3AioCashinCooperationServiceImpl()
    {
    }

    /**
     * (execute)
     * 入金連携サービス処理を行う。 <BR>
     * <BR>
     * 引数で与えられたリクエストを基に業務処理を行い、<BR>
     * 処理結果をレスポンスに設定してを返す。 <BR>
     * <BR>
     * １）スレッドを開始する。 <BR>
     * 　@・デーモントリガーマネージャークラスのインスタンスを生成する。 <BR>
     * 　@・デーモントリガーマネージャークラス.スレッド開始()をコール <BR>
     * 　@　@[引数] <BR>
     * 　@　@　@スレッドＮｏ：入金連携通知リクエスト.スレッドNo <BR>
     * <BR>
     * ２）非同期実行 <BR>
     * 　@・入金連携通知非同期サービスImplのインスタンスを生成する。 <BR>
     * 　@　@［引数］ <BR>
     * 　@　@　@入金連携通知リクエスト：引数.入金連携通知リクエスト <BR>
     * 　@・非同期実行実装サービスを取得する。 <BR>
     * 　@・非同期実行実装サービス.execute()をコール <BR>
     * 　@　@［引数］ <BR>
     * 　@　@　@Runnable：生成した入金連携通知非同期サービスImplオブジェクト <BR>
     * <BR>
     * ４）レスポンスオブジェクトを生成し返却する。 <BR>
     * 
     * @@param l_request リクエスト 
     * @@return 処理結果が設定されたレスポンス 
     * @@roseuid 41C7B2080071
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashinCooperationNotifyRequest l_aioCashinInputRequest =
            (WEB3AioCashinCooperationNotifyRequest)l_request;
        
        //１）スレッドを開始する。 
        //　@・デーモントリガーマネージャークラスのインスタンスを生成する。 
        //　@・デーモントリガーマネージャークラス.スレッド開始()をコール 
        //　@　@[引数] 
        //　@　@スレッドＮｏ：入金連携通知リクエスト.スレッドNo         
        
        WEB3GentradeDaemonTriggerManager l_daemonTriggerManager = 
            new WEB3GentradeDaemonTriggerManager();
        
        l_daemonTriggerManager.startThread(
            l_aioCashinInputRequest.threadNo.longValue());
        
        //２）非同期実行 
        //・入金連携通知非同期サービスImplのインスタンスを生成する。 
        //　@［引数］ 
        //　@　@入金連携通知リクエスト：引数.入金連携通知リクエスト 
        //・非同期実行実装サービスを取得する。 
        //・非同期実行実装サービス.execute()をコール 
        //　@［引数］ 
        //　@　@Runnable：生成した入金連携通知非同期サービスImplオブジェクト 
        
        WEB3AioCashinCooperationNotifyAsyncServiceImpl l_asyncServiceImpl =
            new WEB3AioCashinCooperationNotifyAsyncServiceImpl(l_aioCashinInputRequest);            

        WEB3AsynExecuteService l_asynExecuteService =
            (WEB3AsynExecuteService)Services.getService(
                WEB3AsynExecuteService.class);
        
        l_asynExecuteService.execute(l_asyncServiceImpl);
     
        //４）レスポンスオブジェクトを生成し返却する。
        WEB3BackResponse l_response = l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
}@
