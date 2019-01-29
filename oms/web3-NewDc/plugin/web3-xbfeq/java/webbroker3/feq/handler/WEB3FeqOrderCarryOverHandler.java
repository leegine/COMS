head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文繰越ハンドラ(WEB3FeqOrderCarryOverHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成    
				 : 2005/08/01 郭英(中訊) レビュー          
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqOrderTransferRequest;
import webbroker3.feq.message.WEB3FeqOrderTransferResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文繰越ハンドラ)<BR>
 * 外国株式注文繰越ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqOrderCarryOverHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverHandler.class);
    
    /**
     * @@roseuid 42D0DA1901A5
     */
    public WEB3FeqOrderCarryOverHandler() 
    {
     
    }
    
    /**
     * (submit注文繰越)<BR>
     * 外国株式注文繰越処理を行う。<BR>
     * <BR>
     * 外国株式注文繰越サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文繰越リクエストオブジェクト
     * @@return WEB3FeqOrderTransferResponse
     * @@roseuid 42B8A25003B4
     */
    public WEB3FeqOrderTransferResponse submitOrderCarryOver(
        WEB3FeqOrderTransferRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitOrderCarryOver(WEB3FeqOrderTransferRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式注文繰越サービスインターフェイス
        WEB3FeqOrderCarryOverService l_service = null;
         
        //外国株式注文繰越レスポンス
        WEB3FeqOrderTransferResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqOrderCarryOverService) Services.getService(
                    WEB3FeqOrderCarryOverService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqOrderTransferResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式注文繰越サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqOrderTransferResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqOrderTransferResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式注文繰越処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
