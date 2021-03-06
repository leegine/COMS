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
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΫΨΰΫ¦κ/§Φΰ­ΆΪqυnhNX(WEB3AdminTPSearchAdvanceCustomerHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) VKμ¬
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
 * (ΫΨΰΫ¦κ/§Φΰ­ΆΪqυnhNX)<BR>
 * <BR>
 * MessegeHandlerNXπg£B<BR>
 */
public class WEB3AdminTPSearchAdvanceCustomerHandler implements MessageHandler 
{
    /**
     * (O)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPSearchAdvanceCustomerHandler.class);
    
    
   /**
    * RXgN^
    */
   public WEB3AdminTPSearchAdvanceCustomerHandler() 
   {
   }

   /**
    * (getΫΨΰΫ¦κ/§Φΰ­ΆΪqόΝ)<BR>
    * <BR>
    * @@param l_request ΫΨΰΫ¦κ/§Φΰ­ΆΪqυόΝζΚ\¦NGXg
    * @@return WEB3AdminTPAdvanceCustomerSearchInputResponse ΫΨΰΫ¦κ/§Φΰ­ΆΪqυόΝζΚ\¦X|X
    */
   public WEB3AdminTPAdvanceCustomerSearchInputResponse getAdvanceCustomerInput(WEB3AdminTPAdvanceCustomerSearchInputRequest l_request) 
   {
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rX
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυόΝζΚX|X
       WEB3AdminTPAdvanceCustomerSearchInputResponse l_response = null;
       
       try
       {
           //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rXζΎ
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rXΐs
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
       
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυόΝζΚ\¦X|XΤp
       return l_response;
   }

   
   /**
    * (getΫΨΰΫ¦κ/§Φΰ­ΆΪqκ)<BR>
    * <BR>
    * @@param l_request ΫΨΰΫ¦κ/§Φΰ­ΆΪqυκζΚ\¦NGXg
    * @@return WEB3AdminTPAdvanceCustomerSearchListResponse ΫΨΰΫ¦κ/§Φΰ­ΆΪqυκζΚ\¦X|X
    */
   public WEB3AdminTPAdvanceCustomerSearchListResponse getAdvanceCustomerList(WEB3AdminTPAdvanceCustomerSearchListRequest l_request) 
   {
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rX
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυκζΚ\¦X|X
       WEB3AdminTPAdvanceCustomerSearchListResponse l_response = null;
       try
       {
           //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rXζΎ
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rXΐs
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
              
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυκζΚ\¦X|XΤp
       return l_response;
   }
   
   
   /**
    * (getΫΨΰΫ¦κ/§Φΰ­ΆΪqΪΧ)<BR>
    * <BR>
    * @@param l_request  ΫΨΰΫ¦κ/§Φΰ­ΆΪqΪΧζΚ\¦NGXg
    * @@return WEB3AdminTPAdvanceCustomerDetailResponse  ΫΨΰΫ¦κ/§Φΰ­ΆΪqΪΧζΚ\¦X|X
    */
   public WEB3AdminTPAdvanceCustomerDetailResponse getMarginMaintenanceRateDebitAmountCustomerDetail(WEB3AdminTPAdvanceCustomerDetailRequest l_request) 
   {
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rX
       WEB3AdminTPSearchAdvanceCustomerService service = null;
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqΪΧζΚ\¦X|X
       WEB3AdminTPAdvanceCustomerDetailResponse l_response = null;
       try
       {
           //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rXζΎ
           service = (WEB3AdminTPSearchAdvanceCustomerService)Services.getService(WEB3AdminTPSearchAdvanceCustomerService.class);
           //ΫΨΰΫ¦κ/§Φΰ­ΆΪqυT[rXΐs
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
       
       //ΫΨΰΫ¦κ/§Φΰ­ΆΪqΪΧζΚ\¦X|XΤp
       return l_response;
   }
   
}
@
