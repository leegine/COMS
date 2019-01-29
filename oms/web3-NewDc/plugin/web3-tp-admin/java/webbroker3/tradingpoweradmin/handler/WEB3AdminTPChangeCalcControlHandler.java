head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeCalcControlHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͐���@@�\�ύX�n���h���N���X(WEB3AdminTPChangeCalcControlHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : ${date} �x��a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputRequest;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeCalcControlService;

/**
 * �]�͐���@@�\�ύX�n���h���N���X
 */
public class WEB3AdminTPChangeCalcControlHandler implements MessageHandler
{

    WEB3AdminTPChangeCalcControlService service;

   /**
    * @@roseuid 41DBCA970030
    */
   public WEB3AdminTPChangeCalcControlHandler()
   {
       service = (WEB3AdminTPChangeCalcControlService)Services.getService(WEB3AdminTPChangeCalcControlService.class);
   }

   /**
    * (get�]�͐���@@�\��������)
    * �]�͐���@@�\�����������s���B
    *
    * �]�͐���@@�\�ύX�T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͐���@@�\�������N�G�X�g
    * @@return webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlResponse
    * @@roseuid 41B961DB02D8
    */
   public WEB3AdminTPFindCalcControlResponse findCalcControlRequest(WEB3AdminTPFindCalcControlRequest l_request)
   {
       WEB3AdminTPFindCalcControlResponse l_response;
       try
       {
           l_response = (WEB3AdminTPFindCalcControlResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPFindCalcControlResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (get�]�͐���@@�\�ύX���͉��)
    * �]�͐���@@�\�ύX���͉�ʎ擾�������s���B
    *
    * �]�͐���@@�\�ύX�T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͐���@@�\�ύX���͉�ʃ��N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputResponse
    * @@roseuid 41CBA12C01C9
    */
   public WEB3AdminTPChangeCalcControlInputResponse changeCalcControlInputRequest(WEB3AdminTPChangeCalcControlInputRequest l_request)
   {
       WEB3AdminTPChangeCalcControlInputResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeCalcControlInputResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeCalcControlInputResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (validate�]�͐���@@�\�ύX)
    * �]�͐���@@�\�ύX�m�F�������s���B
    *
    * �]�͐���@@�\�ύX�T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͐���@@�\�ύX�m�F���N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmResponse
    * @@roseuid 41B961E803D2
    */
   public WEB3AdminTPChangeCalcControlConfirmResponse changeCalcControlConfirmRequest(WEB3AdminTPChangeCalcControlConfirmRequest l_request)
   {
       WEB3AdminTPChangeCalcControlConfirmResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeCalcControlConfirmResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeCalcControlConfirmResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (submit�]�͐���@@�\�ύX)
    * �]�͐���@@�\�ύX�����������s���B
    *
    * �]�͐���@@�\�ύX�T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͐���@@�\�ύX�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRespons
    * e
    * @@roseuid 41B961F20029
    */
   public WEB3AdminTPChangeCalcControlCompleteResponse changeCalcControlCompleteRequest(WEB3AdminTPChangeCalcControlCompleteRequest l_request)
   {
       WEB3AdminTPChangeCalcControlCompleteResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeCalcControlCompleteResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeCalcControlCompleteResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
