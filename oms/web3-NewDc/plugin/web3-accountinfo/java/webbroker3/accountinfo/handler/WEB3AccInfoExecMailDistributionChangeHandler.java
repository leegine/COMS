head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoExecMailDistributionChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報約定／未約定メール配信設定変更ハンドラ(WEB3AccInfoExecMailDistributionChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoExecMailDistributionChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報約定／未約定メール配信設定変更ハンドラ)<BR>
 * お客様情報約定／未約定メール配信設定変更ハンドラ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoExecMailDistributionChangeHandler implements MessageHandler 
{                  
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoExecMailDistributionChangeHandler.class);
    /**
     * @@roseuid 418F3A1102CE
     */
    public WEB3AccInfoExecMailDistributionChangeHandler() 
    {
     
    }
    
    /**
     * (約定／未約定メール配信設定変更完了)<BR>
     * 約定／未約定メール配信設定を変更する。<BR>
     * <BR>
     * お客様情報約定／未約定メール配信設定変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報約定／未約定メール配信設定変更完了リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteResponse
     * @@roseuid 413C010A02AD
     */
    public WEB3AccInfoExecMailDistributionChangeCompleteResponse execMailDistributionChangeComplete(WEB3AccInfoExecMailDistributionChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " execMailDistributionChangeComplete(WEB3AccInfoExecMailDistributionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoExecMailDistributionChangeCompleteResponse l_response = null;
        WEB3AccInfoExecMailDistributionChangeService l_service = null;
        
        //お客様情報約定／未約定メール配信設定変更サービスを取得
        try
        {
            l_service = (WEB3AccInfoExecMailDistributionChangeService)Services.getService(WEB3AccInfoExecMailDistributionChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoExecMailDistributionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報約定／未約定メール配信設定変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //お客様情報約定／未約定メール配信設定変更サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AccInfoExecMailDistributionChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoExecMailDistributionChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報約定／未約定メール配信設定変更サービスに失敗しました。",
                l_ex);
            return l_response; 

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
}
@
