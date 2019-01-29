head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理検索ハンドラクラス(WEB3AdminTPPaymentRequisitionManageHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionManageService;


/**
 * 入金請求管理検索ハンドラ
 */
public class WEB3AdminTPPaymentRequisitionManageHandler implements MessageHandler
{
    WEB3AdminTPPaymentRequisitionManageService service;
    
    /**
     * @@roseuid 4412A9800296
     */
    public WEB3AdminTPPaymentRequisitionManageHandler() 
    {
    	service = (WEB3AdminTPPaymentRequisitionManageService)Services.getService(WEB3AdminTPPaymentRequisitionManageService.class);
    }
    
    /**
     * (get入金請求管理検索結果)
     *
     * 入金請求管理検索処理を行う。
     *
     * this.get入金請求管理検索結果()メソッドをコールする。
     * @@param l_request - 入金請求管理リクエスト
     * @@return WEB3AdminTPPaymentRequisitionManageResponse
     * @@roseuid 41BD555800A3
     */
    public WEB3AdminTPPaymentRequisitionManageSearchResponse WEB3AdminTPPaymentRequisitionManageSearchRequest(WEB3AdminTPPaymentRequisitionManageSearchRequest l_request)
    {
    	WEB3AdminTPPaymentRequisitionManageSearchResponse l_response;
        try
        {
            l_response = (WEB3AdminTPPaymentRequisitionManageSearchResponse)service.execute(l_request);
        }
        catch(WEB3BaseException e)
        {
            l_response =  new WEB3AdminTPPaymentRequisitionManageSearchResponse();
            l_response.errorInfo = e.getErrorInfo();
        }
        return l_response;
    }

    public WEB3AdminTPPaymentRequisitionManageHistoryResponse WEB3AdminTPPaymentRequisitionManageHistoryRequest(WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request)
    {
    	WEB3AdminTPPaymentRequisitionManageHistoryResponse l_response;
        try
        {
            l_response = (WEB3AdminTPPaymentRequisitionManageHistoryResponse)service.execute(l_request);
        }
        catch(WEB3BaseException e)
        {
            l_response =  new WEB3AdminTPPaymentRequisitionManageHistoryResponse();
            l_response.errorInfo = e.getErrorInfo();
        }
        return l_response;
    }

}
@
