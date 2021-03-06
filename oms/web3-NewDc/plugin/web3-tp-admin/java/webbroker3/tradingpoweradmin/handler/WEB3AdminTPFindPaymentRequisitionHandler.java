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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : üà¿õnhNXNX(WEB3AdminTPFindPaymentRequisitionHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : ${date} xìaü(FLJ) VKì¬
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPFindPaymentRequisitionService;

/**
 * üà¿õnhNX
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
    * (getüà¿ÚqõÊ)
    *
    * üà¿õðs¤B
    *
    * this.getüà¿ÚqõÊ()\bhðR[·éB
    * @@param l_request - üà¿õNGXg
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
