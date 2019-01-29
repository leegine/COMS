head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ロック登録解除受付ハンドラ(WEB3AccInfoLockRegistRelaxationAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoLockRegistReleaseAcceptRequest;
import webbroker3.accountinfo.message.WEB3AccInfoLockRegistReleaseAcceptResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (ロック登録解除受付ハンドラ)<BR>
 * ロック登録解除受付ハンドラ<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockRegistReleaseAcceptHandler.class);

    public WEB3AccInfoLockRegistReleaseAcceptHandler() 
    {
     
    }
    
    /**
     * (ロック登録解除受付)<BR>
     * ロック登録解除受付処理を実施する。 <BR>
     * <BR>
     * ロック登録解除受付サービスを取得し、execute()メソッドをコールする。<BR> 
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 416B745B027C<BR>
     * @@param l_request<BR>
     * @@return WEB3AccInfoLockRegistReleaseAcceptResponse<BR>
     */
    public WEB3AccInfoLockRegistReleaseAcceptResponse lockRegistReleaseAccept(WEB3AccInfoLockRegistReleaseAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = " lockRegistReleaseAccept(WEB3AccInfoLockRegistReleaseAcceptRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoLockRegistReleaseAcceptResponse l_response = null;
        WEB3AccInfoLockRegistReleaseAcceptService l_service = null;

        try
        {
            l_service = (WEB3AccInfoLockRegistReleaseAcceptService)Services.getService(WEB3AccInfoLockRegistReleaseAcceptService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoLockRegistReleaseAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "ロック登録解除受付サービの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        //ロック登録解除受付サービ.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AccInfoLockRegistReleaseAcceptResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoLockRegistReleaseAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "ロック登録解除受付サービに失敗しました。",
                l_ex);
            return l_response; 

        }
        log.exiting(STR_METHOD_NAME);

        return l_response;                
    }
}
@
