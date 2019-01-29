head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍Čv�ZHandler�N���X(WEB3AdminTpAssetReCalcHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPReCalcService;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * (�]�͍Čv�Z�n���h���N���X)
 * 
 * MessegeHandler�N���X���g���B
 */
public class WEB3AdminTPReCalcHandler implements MessageHandler 
{
    WEB3AdminTPReCalcService service;
   
   /**
    * @@roseuid 41DBC9CD007E
    */
   public WEB3AdminTPReCalcHandler() 
   {
       service = (WEB3AdminTPReCalcService)Services.getService(WEB3AdminTPReCalcService.class);    
   }
      
   /**
    * (submit�]�͍Čv�Z)
    * �]�͍Čv�Z���s�������s���B 
    * 
    * �]�͍Čv�Z�T�[�r�XImpl���擾���A 
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͍Čv�Z���s���N�G�X�g
    * @@return 
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcResponse
    * @@roseuid 41B8FB720038
    */
   public WEB3AdminTPReCalcResponse reCalcRequest(WEB3AdminTPReCalcRequest l_request) 
   {
       WEB3AdminTPReCalcResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReCalcResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReCalcResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
   
   /**
    * (get�]�͍Čv�Z���)
    * �]�͍Čv�Z���͏������s���B 
    * 
    * �]�͍Čv�Z�T�[�r�XImpl���擾���A 
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͍Čv�Z���̓��N�G�X�g
    * @@return 
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputResponse
    * @@roseuid 41B8FB720038
    */
   public WEB3AdminTPReCalcInputResponse reCalcInputRequest(WEB3AdminTPReCalcInputRequest l_request) 
   {
       WEB3AdminTPReCalcInputResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReCalcInputResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReCalcInputResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
