head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧ハンドラ(WEB3PvInfoPrivateInformationListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/25 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateRequest;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧ハンドラ)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧ハンドラクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationListHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationListHandler.class);
    
    /**
     * (get顧客連絡画面)<BR>
     * 顧客連絡画面表示処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 顧客連絡リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse
     * @@roseuid 414526840260
     */
    public WEB3PvInfoAccountConnectionResponse getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest l_request)
    {
        final String STR_METHOD_NAME = " getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoAccountConnectionResponse l_response = null;
        WEB3PvInfoPrivateInformationListService l_service = null;
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスを取得する
        try
        {
            l_service = (WEB3PvInfoPrivateInformationListService)Services.getService(WEB3PvInfoPrivateInformationListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =(WEB3PvInfoAccountConnectionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスの取得に失敗しました。", l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3PvInfoAccountConnectionResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoAccountConnectionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;       
    }

    /**
     * (get証券会社連絡画面)<BR>
     * 証券会社連絡画面表示処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 証券会社連絡リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse
     * @@roseuid 414526C300AA
     */
    public WEB3PvInfoInstitutionConnectionResponse getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest l_request)
    {
        
        final String STR_METHOD_NAME = " getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoInstitutionConnectionResponse l_response = null;
        WEB3PvInfoPrivateInformationListService l_service = null;
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスを取得する
        try
        {
            l_service = (WEB3PvInfoPrivateInformationListService)Services.getService(WEB3PvInfoPrivateInformationListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =(WEB3PvInfoInstitutionConnectionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスの取得に失敗しました。", l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3PvInfoInstitutionConnectionResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoInstitutionConnectionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;  
        
    }

    /**
     * (get注文約定状況画面)<BR>
     * 注文約定状況画面表示処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 注文約定状況リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse
     * @@roseuid 414526ED01A4
     */
    public WEB3PvInfoOrderExecStateResponse getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest l_request)
    {
        final String STR_METHOD_NAME = " getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoOrderExecStateResponse l_response = null;
        WEB3PvInfoPrivateInformationListService l_service = null;
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスを取得する
        try
        {
            l_service = (WEB3PvInfoPrivateInformationListService)Services.getService(WEB3PvInfoPrivateInformationListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =(WEB3PvInfoOrderExecStateResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスの取得に失敗しました。", l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3PvInfoOrderExecStateResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoOrderExecStateResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;  
        
    }
}
@
