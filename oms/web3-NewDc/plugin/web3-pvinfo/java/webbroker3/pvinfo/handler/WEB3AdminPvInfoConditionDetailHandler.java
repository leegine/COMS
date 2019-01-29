head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定詳細ハンドラ(WEB3AdminPvInfoConditionDetailHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/26 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionDetailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者表示設定詳細ハンドラ)<BR>
 * 管理者表示設定詳細ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionDetailHandler implements MessageHandler
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionDetailHandler.class);


    /**
     * (get表示設定詳細画面)<BR>
     * 表示設定詳細画面表示処理を行う。<BR>
     * <BR>
     * 管理者表示設定詳細サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定詳細リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionDetailResponse
     * @@roseuid 415CB8DD00B4
     */
    public WEB3AdminPvInfoConditionDetailResponse getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest l_request)
    {
        final String STR_METHOD_NAME = " getConditionDetailScreen(WEB3AdminPvInfoConditionDetailRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPvInfoConditionDetailResponse l_response = null;
        WEB3AdminPvInfoConditionDetailService l_service = null;
        
        //管理者表示設定詳細サービスを取得し
        try
        {
            l_service = (WEB3AdminPvInfoConditionDetailService)Services.getService(
                WEB3AdminPvInfoConditionDetailService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者表示設定詳細の取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;    
        }
        
        //管理者表示設定詳細サービス.execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminPvInfoConditionDetailResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminPvInfoConditionDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "管理者表示設定詳細サービス.execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }
}
@
