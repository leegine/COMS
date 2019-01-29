head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҈ב֓o�^�n���h��(WEB3AdminTMExchangeRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 ���G�� (���u) �V�K�쐬
*/
package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMExchangeRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҈ב֓o�^�n���h��)<BR>
 * �Ǘ��҈ב֓o�^�n���h���N���X<BR>
 * <BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminTMExchangeRegistHandler.class);

     /**
      * �R���X�g���N�^
      */
     public WEB3AdminTMExchangeRegistHandler()
     {

     }

    /**
     * (get���͉��)<BR>
     * �Ǘ��҈ב֓o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AdminTMExchangeRegistInputResponse
     */
     public WEB3AdminTMExchangeRegistInputResponse getInputScreen(
         WEB3AdminTMExchangeRegistInputRequest l_request)
     {
         final String STR_METHOD_NAME =
             "getInputScreen(WEB3AdminTMExchangeRegistInputRequest)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.debug("�p�����[�^�l�s���B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
         }
         WEB3AdminTMExchangeRegistService l_service = null;
         WEB3AdminTMExchangeRegistInputResponse l_response = null;

         try
         {
             //get�Ǘ��҈ב֓o�^�T�[�r�X
             l_service = (WEB3AdminTMExchangeRegistService)Services.getService(
                 WEB3AdminTMExchangeRegistService.class);
             if (l_service == null)
             {
                 log.debug("�Ǘ��҈ב֓o�^�T�[�r�X���擾�Ɏ��s���܂����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             //execute
             //WEB3BaseException
             l_response = (WEB3AdminTMExchangeRegistInputResponse)l_service.execute(l_request);
         }
         catch (WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistInputResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(
                 l_request,
                 "get���͉�ʂ����{�Ɏ��s���܂����B",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (Exception l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistInputResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }

             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "get���͉�ʂ����{�Ɏ��s���܂����B",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     }

     /**
      * (validate�ב֓o�^)<BR>
      * �Ǘ��҈ב֓o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
      * <BR>
      * @@param l_request - ���N�G�X�g�f�[�^
      * @@return WEB3AdminTMExchangeRegistConfirmResponse
      */
     public WEB3AdminTMExchangeRegistConfirmResponse validateExchangeRegist(
         WEB3AdminTMExchangeRegistConfirmRequest l_request)
     {
         final String STR_METHOD_NAME =
             "validateExchangeRegist(WEB3AdminTMExchangeRegistConfirmRequest)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.debug("�p�����[�^�l�s���B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
         }

         WEB3AdminTMExchangeRegistService l_service = null;
         WEB3AdminTMExchangeRegistConfirmResponse l_response = null;

         try
         {
             //get�Ǘ��҈ב֓o�^�T�[�r�X
             l_service = (WEB3AdminTMExchangeRegistService)Services.getService(
                 WEB3AdminTMExchangeRegistService.class);
             if (l_service == null)
             {
                 log.debug("�Ǘ��҈ב֓o�^�T�[�r�X���擾�Ɏ��s���܂����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             //execute
             //WEB3BaseException
             l_response = (WEB3AdminTMExchangeRegistConfirmResponse)l_service.execute(l_request);
         }
         catch (WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistConfirmResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(
                 l_request,
                 "validate�ב֓o�^�����{�Ɏ��s���܂����B",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (Exception l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistConfirmResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }

             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "validate�ב֓o�^�����{�Ɏ��s���܂����B",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     }

     /**
      * (submit�ב֓o�^)<BR>
      * �Ǘ��҈ב֓o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
      * <BR>
      * @@param l_request - ���N�G�X�g�f�[�^
      * @@return WEB3AdminTMExchangeRegistCompleteResponse
      */
     public WEB3AdminTMExchangeRegistCompleteResponse submitExchangeRegist(
         WEB3AdminTMExchangeRegistCompleteRequest l_request)
     {
         final String STR_METHOD_NAME =
             "submitExchangeRegist(WEB3AdminTMExchangeRegistCompleteRequest)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.debug("�p�����[�^�l�s���B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
         }

         WEB3AdminTMExchangeRegistCompleteResponse l_response = null;
         WEB3AdminTMExchangeRegistService l_service = null;

         try
         {
             //get�Ǘ��҈ב֓o�^�T�[�r�X
             l_service = (WEB3AdminTMExchangeRegistService)Services.getService(
                 WEB3AdminTMExchangeRegistService.class);
             if (l_service == null)
             {
                 log.debug("�Ǘ��҈ב֓o�^�T�[�r�X���擾�Ɏ��s���܂����B");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             //execute
             //WEB3BaseException
             l_response = (WEB3AdminTMExchangeRegistCompleteResponse)l_service.execute(l_request);
         }
         catch (WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistCompleteResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(
                 l_request,
                 "submit�ב֓o�^�����{�Ɏ��s���܂����B",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (Exception l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistCompleteResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }

             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "submit�ב֓o�^�����{�Ɏ��s���܂����B",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     }
}
@
