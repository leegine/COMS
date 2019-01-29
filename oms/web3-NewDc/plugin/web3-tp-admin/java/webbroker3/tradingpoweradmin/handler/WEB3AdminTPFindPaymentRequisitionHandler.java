head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindPaymentRequisitionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求検索ハンドラクラスクラス(WEB3AdminTPFindPaymentRequisitionHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : ${date} 堀野和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPFindPaymentRequisitionService;

/**
 * 入金請求検索ハンドラクラス
 */
public class WEB3AdminTPFindPaymentRequisitionHandler implements MessageHandler
{

    WEB3AdminTPFindPaymentRequisitionService service;

   /**
    * @@roseuid 41DE22CE0054
    */
   public WEB3AdminTPFindPaymentRequisitionHandler()
   {
       service = (WEB3AdminTPFindPaymentRequisitionService)Services.getService(WEB3AdminTPFindPaymentRequisitionService.class);
   }

   /**
    * (get入金請求顧客検索結果)
    *
    * 入金請求検索処理を行う。
    *
    * this.get入金請求顧客検索結果()メソッドをコールする。
    * @@param l_request - 入金請求検索リクエスト
    * @@return WEB3AdminTPFindPaymentRequisitionResponse
    * @@roseuid 41BD555800A3
    */
   public WEB3AdminTPFindPaymentRequisitionResponse findPaymentRequisitionRequest(WEB3AdminTPFindPaymentRequisitionRequest l_request)
   {
       WEB3AdminTPFindPaymentRequisitionResponse l_response;
       try
       {
           l_response = (WEB3AdminTPFindPaymentRequisitionResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPFindPaymentRequisitionResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
