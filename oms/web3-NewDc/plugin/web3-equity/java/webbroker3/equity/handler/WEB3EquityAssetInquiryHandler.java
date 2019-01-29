head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAssetInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売付一覧ハンドラ(WEB3EquityAssetInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 任林林 (中訊) 新規作成
                   2004/12/16 坂上(SRA) 残案件対応(「保有資産一覧」⇒「売付一覧」)
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquitySellListRequest;
import webbroker3.equity.message.WEB3EquitySellListResponse;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * （売付一覧ハンドラ）。<BR>
 * <BR>
 * 売付一覧ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityAssetInquiryHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAssetInquiryHandler.class);
    /**
     * @@roseuid 409F4650027E
     */
    public WEB3EquityAssetInquiryHandler()
    {

    }

    /**
     * (売付一覧リクエスト)<BR>
     * 売付一覧処理を行う。<BR>
     * <BR>
     * 売付一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_requestData - リクエストデータ<BR>
     * 売付一覧リクエストオブジェクト
     * @@return webbroker3.equity.message.WEB3EquityAssetInquiryResponse
     * @@roseuid 406031240056
     */
    public WEB3EquitySellListResponse assetInquiryRequest(WEB3EquitySellListRequest l_requestData)
    {
        final String STR_METHOD_NAME = "assetInquiryRequest(WEB3EquitySellListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityAssetInquiryService l_service = null;
        WEB3EquitySellListResponse l_response = null;

        try
        {
            //売付一覧サービスを取得
            l_service =
                (WEB3EquityAssetInquiryService) Services.getService(
                    WEB3EquityAssetInquiryService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquitySellListResponse) l_requestData.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_requestData, "売付一覧サービス取得に失敗しました。", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()メソッドをコールする。
            l_response =
                (WEB3EquitySellListResponse) l_service.execute(l_requestData);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquitySellListResponse) l_requestData.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_requestData, "売付一覧に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquitySellListResponse) l_requestData.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_requestData, "売付一覧に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
