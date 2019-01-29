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
filename	WEB3DocumentDeliverHistoryRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付履歴登録ハンドラ(WEB3DocumentDeliverHistoryRegistHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 大澤喜宗@(SRA) 新規作成
*/
package webbroker3.gentrade.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistResponse;
import webbroker3.gentrade.service.delegate.WEB3DocumentDeliverHistoryRegistService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (書面交付履歴登録ハンドラ)<BR>
 * <BR>
 * 書面交付履歴登録ハンドラクラス<BR>
 * @@author 大澤喜宗@
 * @@version 1.0
 */
public class WEB3DocumentDeliverHistoryRegistHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DocumentDeliverHistoryRegistHandler.class);

	/**
	 * コンストラクタ<BR> 
	 */
	public WEB3DocumentDeliverHistoryRegistHandler() 
    {
	}
    
    /**
     * (書面交付履歴登録)<BR>
     * <BR>
     * 書面交付履歴登録処理を実施する。<BR>
     * <BR>
     * 書面交付履歴登録サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request リクエストデータ
     * @@return WEB3DocumentDeliverHistoryRegistResponse
     */
    public WEB3DocumentDeliverHistoryRegistResponse documentDeliverHistoryRegist(WEB3DocumentDeliverHistoryRegistRequest l_request)
    {
        final String STR_METHOD_NAME = "documentDeliverHistoryRegist(WEB3DocumentDeliverHistoryRegistRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3DocumentDeliverHistoryRegistService l_service = null;
        WEB3DocumentDeliverHistoryRegistResponse l_response = null;
        
        // 書面交付履歴登録サービスを取得
        try
        {
            l_service = 
                (WEB3DocumentDeliverHistoryRegistService)
                    Services.getService(WEB3DocumentDeliverHistoryRegistService.class);
        }
        catch (Exception e)
        {
            l_response = 
                (WEB3DocumentDeliverHistoryRegistResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "書面交付履歴登録サービスの取得に失敗しました。", 
                l_response.errorInfo, 
                e);
            
            return l_response;
        }
        
        // 書面交付履歴登録サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = 
                (WEB3DocumentDeliverHistoryRegistResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = 
                (WEB3DocumentDeliverHistoryRegistResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "書面交付履歴登録に失敗しました。", 
                e);
            
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
        
    }
}
@
