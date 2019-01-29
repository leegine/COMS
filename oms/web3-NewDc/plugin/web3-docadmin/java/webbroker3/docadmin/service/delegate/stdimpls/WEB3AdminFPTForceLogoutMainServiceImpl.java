head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者 書面未承諾 強制ログアウトメインサービスImpl(WEB3AdminFPTForceLogoutMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutMainService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;
import webbroker3.util.WEB3LogUtility;


/**
 * 管理者 書面未承諾 強制ログアウトメインサービスImpl
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutMainServiceImpl implements WEB3AdminFPTForceLogoutMainService 
{
	
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutMainServiceImpl.class);
    
    /**
     * @@roseuid 47DF46760353
     */
    public WEB3AdminFPTForceLogoutMainServiceImpl() 
    {
     
    }
    
    /**
     * （非同期）書面未承諾 強制ログアウト処理を起動する。 
     * 
     * シーケンス図 
     * 「（管理者 書面未承諾 強制ログアウト）execute」参照。
     * @@param l_request - リクエスト
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 47D64BA90008
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BackResponse l_response = null;
        WEB3AdminFPTForceLogoutMainRequest l_mainRequest = null;
        if (l_request instanceof WEB3AdminFPTForceLogoutMainRequest)
        {
            l_mainRequest = (WEB3AdminFPTForceLogoutMainRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 管理者 書面未承諾 強制ログアウトメインリクエスト");
        }
        
        //1.1リクエストデータの整合性をチェックする。
        l_mainRequest.validate();
        
        //1.2デーモントリガーの該当レコードを"処理中"でupdateする。 
        //[引数] 
        //l_lngThreadNo：　@リクエストデータ.スレッドNo
        WEB3GentradeDaemonTriggerManager l_manager = new WEB3GentradeDaemonTriggerManager();
        l_manager.startThread(l_mainRequest.threadNo.longValue());
        
        //1.3(非同期)管理者 書面未承諾 強制rお具アウトImplを生成する。 
        //※Services.getService()ではない。 
		//[引数] 
		//リクエストデータ：　@リクエストデータ
        WEB3AsynAdminFPTForceLogoutServiceImpl l_expireServiceImpl = 
            new WEB3AsynAdminFPTForceLogoutServiceImpl(l_mainRequest);
        
        WEB3AsynExecuteService l_executeService = 
            (WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);

        //1.4 （非同期）書面未承諾 強制ログアウト処理を行う。 
        //[引数] 
        //arg0：　@生成した（非同期）管理者 書面未承諾 強制ログアウトサービスImpl
        l_executeService.execute(l_expireServiceImpl);
        
        //1.5レスポンスデータを生成する。
        l_response = l_mainRequest.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
