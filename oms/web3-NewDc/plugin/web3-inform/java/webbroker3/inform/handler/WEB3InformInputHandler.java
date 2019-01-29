head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡入力ハンドラ(WEB3InformInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.inform.service.delegate.WEB3InformInputService;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformInputServiceImpl;
import webbroker3.inform.message.WEB3InformInputResponse;
import webbroker3.inform.message.WEB3InformInputRequest;
import webbroker3.inform.message.WEB3InformConfirmResponse;
import webbroker3.inform.message.WEB3InformConfirmRequest;
import webbroker3.inform.message.WEB3InformCompleteResponse;
import webbroker3.inform.message.WEB3InformCompleteRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡入力ハンドラ)
 * 連絡入力ハンドラクラス
 */
public class WEB3InformInputHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformInputHandler.class);

    /**
     * @@roseuid 41EE631D029F
     */
    public WEB3InformInputHandler()
    {

    }

    /**
     * (入力画面表示)<BR>
     * 連絡入力画面を表示する。<BR>
     * <BR>
     * 連絡入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return webbroker3.inform.message.WEB3InformInputResponse
     * @@roseuid 419DA01B01AF
     */
    public WEB3InformInputResponse informInputDisplay(WEB3InformInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "informInputDisplay(WEB3InformInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3InformInputResponse l_response = null;
        WEB3InformInputService l_informInputService = null;
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            log.error("該当パラメータにNull値は設定できません。.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        try
        {
            l_informInputService = 
                (WEB3InformInputService)Services.getService(WEB3InformInputService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3InformInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(l_request,
                "予期しないシステムエラーが発生しました",
                l_response.errorInfo,
                l_ex); 
            return l_response;  
        }
        try
        {
            l_response = 
                (WEB3InformInputResponse)l_informInputService.execute(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3InformInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "連絡入力 Response Object 取得し失敗",
                l_ex); 
            return l_response;          
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (連絡確認)<BR>
     * 連絡の審査を行う。<BR>
     * <BR>
     * 連絡入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3InformConfirmResponse
     * @@roseuid 419DA0D70171
     */
    public WEB3InformConfirmResponse informConfirm(WEB3InformConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "informConfirm(WEB3InformConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3InformConfirmResponse l_response = null;
        WEB3InformInputService l_informInputService = null;
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            log.error("該当パラメータにNull値は設定できません。.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        try
        {
            l_informInputService = 
                (WEB3InformInputService)Services.getService(WEB3InformInputService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3InformConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(l_request,
                "予期しないシステムエラーが発生しました",
                l_response.errorInfo,
                l_ex); 
            return l_response;  
        }
        try
        {
            l_response = 
                (WEB3InformConfirmResponse)l_informInputService.execute(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3InformConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "連絡入力 Response Object 取得し失敗",
                l_ex); 
            return l_response;          
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (連絡完了)<BR>
     * 連絡の登録を行う。<BR>
     * <BR>
     * 連絡入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return webbroker3.inform.message.WEB3InformCompleteResponse
     * @@roseuid 419DA01B01BF
     */
    public WEB3InformCompleteResponse informComplete(WEB3InformCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "informComplete(WEB3InformCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3InformCompleteResponse l_response = null;
        WEB3InformInputService l_informInputService = null;
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            log.error("該当パラメータにNull値は設定できません。.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        try
        {
            l_informInputService = 
                (WEB3InformInputService)Services.getService(WEB3InformInputService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3InformCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(l_request,
                "予期しないシステムエラーが発生しました",
                l_response.errorInfo,
                l_ex); 
            return l_response;  
        }
        try
        {
            l_response = 
                (WEB3InformCompleteResponse)l_informInputService.execute(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3InformCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "連絡入力 Response Object 取得し失敗",
                l_ex); 
            return l_response;          
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
}
@
