head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p���A�g�n���h��(WEB3SrvRegiStreamHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 �g�C�� �V�K�쐬 ���f��371�A���f��377
*/
package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStreamService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p���A�g�n���h��)<BR>
 * �T�[�r�X���p���A�g�n���h���N���X<BR>
 *
 * @@author �g�C��
 * @@version 1.0
 */
public class WEB3SrvRegiStreamHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamHandler.class);

   /**
    * @@roseuid 4831260B03D8
    */
   public WEB3SrvRegiStreamHandler()
   {

   }

   /**
    * (���A�g���N�G�X�g)<BR>
    * �T�[�r�X���p���A�g�������s���B<BR>
    * <BR>
    * �T�[�r�X���p���A�g�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
    * @@roseuid 4815933F03C3
    */
   public WEB3SrvRegiStreamResponse srvRegiStreamRequest(WEB3SrvRegiStreamRequest l_request)
   {
       final String STR_METHOD_NAME = "srvRegiStreamRequest(WEB3SrvRegiStreamRequest)";
       log.entering(STR_METHOD_NAME);

       WEB3SrvRegiStreamResponse l_response = null;
       WEB3SrvRegiStreamService l_service = null;

       try
       {
           l_service =
               (WEB3SrvRegiStreamService)Services.getService(WEB3SrvRegiStreamService.class);
       }
       catch (Exception l_ex)
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "�T�[�r�X���p���A�g�T�[�r�X���擾�Ɏ��s���܂����B",
               l_response.errorInfo,
               l_ex);

           log.exiting(STR_METHOD_NAME);
           return l_response;
       }

       try
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_service.execute(l_request);
       }
       catch (WEB3BaseException l_ex)
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();

           log.error(
               l_request,
               "�T�[�r�X���p���A�g���������s���܂����B",
               l_response.errorInfo,
               l_ex);

           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch (WEB3BaseRuntimeException l_ex)
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();
           log.error(
               l_request,
               "�T�[�r�X���p���A�g���������s���܂����B",
               l_response.errorInfo,
               l_ex);

           log.exiting(STR_METHOD_NAME);
           return l_response;
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
}@
