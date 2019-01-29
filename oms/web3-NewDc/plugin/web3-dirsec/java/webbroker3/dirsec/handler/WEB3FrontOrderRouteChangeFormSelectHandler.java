head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderRouteChangeFormSelectHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注経路切替方式選択ハンドラクラス(WEB3FrontOrderRouteChangeFormSelectHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3FrontOrderRouteChangeFormSelectService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者発注経路切替方式選択ハンドラ)<BR>
 * <BR>
 * 管理者発注経路切替方式選択ハンドラクラス<BR>
 * <BR>
 * WEB3FrontOrderRouteChangeFormSelectHandler<BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3FrontOrderRouteChangeFormSelectHandler implements MessageHandler {

    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderRouteChangeHandler.class);

    /**
      * @@roseuid 43001A2C038D
      */
     public WEB3FrontOrderRouteChangeFormSelectHandler() 
     {
    
     }
   
     /**
      * 管理者発注経路切替方式選択画面表示処理を行う。<BR>
      * <BR>
      * 管理者発注経路切替方式選択サービスImplを取得し、<BR>
      * execute()メソッドをコールする。
      * @@param リクエストデータ - 管理者・発注経路切替選択リクエストオブジェクト
      * @@return 管理者・発注経路切替処理方式選択レスポンス
      * @@roseuid 42D21CBC03BD
      */
     public WEB3AdminFrontChangeProcessSelectResponse getSelectScreen(WEB3AdminFrontChangeProcessSelectRequest l_request) 
     {
         final String STR_METHOD_NAME = "getSelectScreen(WEB3AdminFrontChangeProcessSelectRequest)";
         log.entering(STR_METHOD_NAME);
         WEB3AdminFrontChangeProcessSelectResponse l_response = null;
         WEB3FrontOrderRouteChangeFormSelectService l_service = null;
         try
         {
             l_service =
                 (WEB3FrontOrderRouteChangeFormSelectService) Services.getService(
                                     WEB3FrontOrderRouteChangeFormSelectService.class);
         } catch (Exception l_exp)
         {
             l_response = (WEB3AdminFrontChangeProcessSelectResponse) l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

             log.error(
                 l_request,
                 "Failed while acquiring WEB3AdminFrontOrderRouteChangeServiceImpl ",
                 l_response.errorInfo,
                 l_exp);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         try
         {
             l_response = (WEB3AdminFrontChangeProcessSelectResponse) l_service.execute(l_request);
         } catch (WEB3BaseException l_exp)
         {
             log.error(l_request, "Failed to access getSelectScreen()", l_exp);
             l_response = (WEB3AdminFrontChangeProcessSelectResponse) l_request.createResponse();
             l_response.errorInfo = l_exp.getErrorInfo();
         }
         log.exiting(STR_METHOD_NAME);
         return l_response;
     }
}
@
