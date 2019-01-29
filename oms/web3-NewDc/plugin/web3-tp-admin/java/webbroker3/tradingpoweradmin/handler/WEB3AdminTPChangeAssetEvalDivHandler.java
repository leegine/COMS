head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͌v�Z���@@�ύX�n���h���N���X(WEB3AdminTPChangeAssetEvalDivHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : ${date} �x��a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeAssetEvalDivService;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * (�]�͌v�Z���@@�ύX�n���h���N���X)
 *
 * MessegeHandler�N���X���g���B
 */
public class WEB3AdminTPChangeAssetEvalDivHandler implements MessageHandler
{
    WEB3AdminTPChangeAssetEvalDivService service;

   /**
    * @@roseuid 41DBC9CD007E
    */
   public WEB3AdminTPChangeAssetEvalDivHandler()
   {
       service = (WEB3AdminTPChangeAssetEvalDivService)Services.getService(WEB3AdminTPChangeAssetEvalDivService.class);
   }

   /**
    * (get�]�͌v�Z���@@�ύX���͉��)
    * �]�͌v�Z���@@�ύX���͉�ʕ\���������s���B
    *
    * �]�͌v�Z���@@�ύX�T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͌v�Z���@@�ύX���̓��N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse
    * @@roseuid 41B8FB5201FE
    */
   public WEB3AdminTPChangeAssetEvalDivInputResponse changeAssetEvalDivInputRequest(WEB3AdminTPChangeAssetEvalDivInputRequest l_request)
   {
       WEB3AdminTPChangeAssetEvalDivInputResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeAssetEvalDivInputResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeAssetEvalDivInputResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (validate�]�͌v�Z���@@�ύX)
    * �]�͌v�Z���@@�ύX�m�F�������s���B
    *
    * �]�͌v�Z���@@�ύX�T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͌v�Z���@@�ύX�m�F���N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRespons
    * e
    * @@roseuid 41C7DDA30124
    */
   public WEB3AdminTPChangeAssetEvalDivConfirmResponse changeAssetEvalDivConfirmRequest(WEB3AdminTPChangeAssetEvalDivConfirmRequest l_request)
   {
       WEB3AdminTPChangeAssetEvalDivConfirmResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeAssetEvalDivConfirmResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeAssetEvalDivConfirmResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (submit�]�͌v�Z���@@�ύX)
    * �]�͌v�Z���@@�ύX�����������s���B
    *
    * �]�͌v�Z���@@�ύX�T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �]�͌v�Z���@@�ύX�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRespon
    * se
    * @@roseuid 41B8FB720038
    */
   public WEB3AdminTPChangeAssetEvalDivCompleteResponse changeAssetEvalDivCompleteRequest(WEB3AdminTPChangeAssetEvalDivCompleteRequest l_request)
   {
       WEB3AdminTPChangeAssetEvalDivCompleteResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeAssetEvalDivCompleteResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeAssetEvalDivCompleteResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
