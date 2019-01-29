head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPSearchAdvanceCustomerHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金維持率割れ/立替金発生顧客検索ハンドラクラス(WEB3AdminTPSearchAdvanceCustomerHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPSearchAdvanceCustomerService;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPSearchAdvanceCustomerServiceImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * (保証金維持率割れ/立替金発生顧客検索ハンドラクラス)<BR>
 * <BR>
 * MessegeHandlerクラスを拡張。<BR>
 */
public class WEB3AdminTPSearchAdvanceCustomerHandler implements MessageHandler 
{
    /**
     * (ログ)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPSearchAdvanceCustomerHandler.class);
    
    
   /**
    * コンストラクタ
    */
   public WEB3AdminTPSearchAdvanceCustomerHandler() 
   {
   }

   /**
    * (get保証金維持率割れ/立替金発生顧客入力)<BR>
    * <BR>
    * @@param l_request 保証金維持率割れ/立替金発生顧客検索入力画面表示リクエスト
    * @@return WEB3AdminTPAdvanceCustomerSearchInputResponse 保証金維持率割れ/立替金発生顧客検索入力画面表示レスポンス
    */
   public WEB3AdminTPAdvanceCustomerSearchInputResponse getAdvanceCustomerInput(WEB3AdminTPAdvanceCustomerSearchInputRequest l_request) 
   {
       //保証金維持率割れ/立替金発生顧客検索サービス
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //保証金維持率割れ/立替金発生顧客検索入力画面レスポンス
       WEB3AdminTPAdvanceCustomerSearchInputResponse l_response = null;
       
       try
       {
           //保証金維持率割れ/立替金発生顧客検索サービス取得
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //保証金維持率割れ/立替金発生顧客検索サービス実行
           l_response = (WEB3AdminTPAdvanceCustomerSearchInputResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(WEB3BaseRuntimeException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(Exception e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
       }
       
       //保証金維持率割れ/立替金発生顧客検索入力画面表示レスポンス返却
       return l_response;
   }

   
   /**
    * (get保証金維持率割れ/立替金発生顧客一覧)<BR>
    * <BR>
    * @@param l_request 保証金維持率割れ/立替金発生顧客検索一覧画面表示リクエスト
    * @@return WEB3AdminTPAdvanceCustomerSearchListResponse 保証金維持率割れ/立替金発生顧客検索一覧画面表示レスポンス
    */
   public WEB3AdminTPAdvanceCustomerSearchListResponse getAdvanceCustomerList(WEB3AdminTPAdvanceCustomerSearchListRequest l_request) 
   {
       //保証金維持率割れ/立替金発生顧客検索サービス
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //保証金維持率割れ/立替金発生顧客検索一覧画面表示レスポンス
       WEB3AdminTPAdvanceCustomerSearchListResponse l_response = null;
       try
       {
           //保証金維持率割れ/立替金発生顧客検索サービス取得
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //保証金維持率割れ/立替金発生顧客検索サービス実行
           l_response = (WEB3AdminTPAdvanceCustomerSearchListResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(WEB3BaseRuntimeException e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(Exception e)
       {
           l_response =  (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
       }
              
       //保証金維持率割れ/立替金発生顧客検索一覧画面表示レスポンス返却
       return l_response;
   }
   
   
   /**
    * (get保証金維持率割れ/立替金発生顧客詳細)<BR>
    * <BR>
    * @@param l_request  保証金維持率割れ/立替金発生顧客詳細画面表示リクエスト
    * @@return WEB3AdminTPAdvanceCustomerDetailResponse  保証金維持率割れ/立替金発生顧客詳細画面表示レスポンス
    */
   public WEB3AdminTPAdvanceCustomerDetailResponse getMarginMaintenanceRateDebitAmountCustomerDetail(WEB3AdminTPAdvanceCustomerDetailRequest l_request) 
   {
       //保証金維持率割れ/立替金発生顧客検索サービス
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //保証金維持率割れ/立替金発生顧客詳細画面表示レスポンス
       WEB3AdminTPAdvanceCustomerDetailResponse l_response = null;
       try
       {
           //保証金維持率割れ/立替金発生顧客検索サービス取得
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //保証金維持率割れ/立替金発生顧客検索サービス実行
           l_response = (WEB3AdminTPAdvanceCustomerDetailResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           log.error(e.getMessage(), e);
           l_response = (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(WEB3BaseRuntimeException e)
       {
           log.error(e.getMessage(), e);
           l_response =  (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       catch(Exception e)
       {   
           log.error(e.getMessage(), e);
           l_response =  (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
       }
       
       //保証金維持率割れ/立替金発生顧客詳細画面表示レスポンス返却
       return l_response;
   }
   
}
@
