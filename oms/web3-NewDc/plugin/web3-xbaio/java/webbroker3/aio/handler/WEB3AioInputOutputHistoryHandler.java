head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputHistoryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴ハンドラ(WEB3AioInputOutputHistoryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 艾興 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioInputOutputHistoryListRequest;
import webbroker3.aio.message.WEB3AioInputOutputHistoryListResponse;
import webbroker3.aio.service.delegate.WEB3AioInputOutputHistoryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (入出庫履歴ハンドラ)<BR>
 * 入出庫履歴ハンドラクラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3AioInputOutputHistoryHandler.class);
    /**
     * @@roseuid 41EC855C0128
     */
    public WEB3AioInputOutputHistoryHandler() 
    {
     
    }
    
    /**
     * (履歴画面表示)<BR>
     * 履歴画面の表示を行う。<BR>
     * <BR>
     * 入出庫履歴サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AioInputOutputHistoryListResponse
     * @@roseuid 41B7B64702BA
     */
    public WEB3AioInputOutputHistoryListResponse handleHistoryRequest(WEB3AioInputOutputHistoryListRequest l_request) 
    {
        final String STR_METHOD_NAME = "handleHistoryRequest()";
        log.entering(STR_METHOD_NAME);
        WEB3AioInputOutputHistoryListResponse l_response = null;
        WEB3AioInputOutputHistoryService l_service = null;

         try
         {
             l_service = (WEB3AioInputOutputHistoryService)Services.getService(WEB3AioInputOutputHistoryService.class);
         }
         catch (Exception l_ex)
         {

             l_response = (WEB3AioInputOutputHistoryListResponse)l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(l_request, "予期しないシステムエラーが発生しました。", l_response.errorInfo, l_ex);

             return l_response;
         }
        
         try
         {
             l_response = (WEB3AioInputOutputHistoryListResponse)l_service.execute(l_request);
         }

         catch (WEB3BaseException l_ex)
         {
             l_response = (WEB3AioInputOutputHistoryListResponse)l_request.createResponse();        
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(l_request, this.getClass().getName() + STR_METHOD_NAME, l_ex);
             return l_response;
         }
        
         log.exiting(STR_METHOD_NAME);

         //レスポンスオブジェクトを返却する。
         return l_response;

    }
}
@
