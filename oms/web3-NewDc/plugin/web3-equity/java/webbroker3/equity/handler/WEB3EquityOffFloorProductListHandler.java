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
filename	WEB3EquityOffFloorProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会外分売銘柄一覧ハンドラ(WEB3EquityOffFloorProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 森川 (SRA) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityOffFloorProductListRequest;
import webbroker3.equity.message.WEB3EquityOffFloorProductListResponse;
import webbroker3.equity.service.delegate.WEB3EquityOffFloorProductListService;
import webbroker3.util.WEB3LogUtility;


/**
 * （立会外分売銘柄一覧ハンドラ）。<BR>
 * <BR>
 * 立会外分売銘柄一覧ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOffFloorProductListHandler.class);


    /**
     * (デフォルトのコンストラクタ)。<BR>
     * <BR>
     * 立会外分売銘柄一覧ハンドラを生成する。<BR>
     * 
     */
    public WEB3EquityOffFloorProductListHandler()
    {
    }
    

    /**
     * (get銘柄一覧)。<BR>
     * <BR>
     * 立会外分売銘柄一覧表示処理を行う。 <BR>
     * <BR>
     * 立会外分売銘柄一覧サービスを取得し、execute()メソッドをコールする。 <BR>
     * <BR>
     * @@param  l_request - リクエストデータ<BR>
     * @@return webbroker3.equity.message.WEB3EquityOffFloorProductListResponse
     */
    public WEB3EquityOffFloorProductListResponse equityOffFloorProductListRequest(
        WEB3EquityOffFloorProductListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "equityOffFloorProductListRequest(WEB3EquityOffFloorProductListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOffFloorProductListResponse         l_response  = null;
        WEB3EquityOffFloorProductListService    l_service   = null;


        //--------------------
        //立会外分売銘柄一覧サービスの取得
        //--------------------
        try
        {
            l_service = (WEB3EquityOffFloorProductListService) Services
                .getService(WEB3EquityOffFloorProductListService.class);
        }
        catch (Exception l_exception)
        {
            l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, "立会外分売銘柄一覧サービスの取得に失敗しました。",
                l_response.errorInfo, l_exception);
                
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //--------------------
        //立会外分売銘柄一覧サービスの実行
        //--------------------
        try
        {
            l_response = (WEB3EquityOffFloorProductListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_request, "立会外分売銘柄一覧に失敗しました。", l_wbe);
            l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_wbe.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_request, "立会外分売銘柄一覧に失敗しました。", l_bre);
            l_response = (WEB3EquityOffFloorProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
