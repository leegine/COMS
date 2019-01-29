head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����c���Ɖ�n���h��(WEB3MarginBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalResponse;
import webbroker3.equity.service.delegate.WEB3MarginBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * �i�M�p����c���Ɖ�n���h���j�B<BR>
 * <BR>
 * �M�p����c���Ɖ�n���h���N���X<BR>
 */
public class WEB3MarginBalanceReferenceHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginBalanceReferenceHandler.class);

     /**
      * @@roseuid 4206CDD40386<BR>
      */
     public WEB3MarginBalanceReferenceHandler() 
     {
      
     }
     
     /**
      * (get�c���Ɖ�)<BR>
      * <BR>
      * �M�p����c���Ɖ�����s���B<BR>
      * <BR>
      * �M�p����c���Ɖ�T�[�r�XImpl���擾���A<BR>
      * execute()���\�b�h���R�[������B<BR>
      * @@param l_request - (���N�G�X�g�f�[�^) �M�p����c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
      * @@return WEB3MarginBalanceReferenceResponse<BR>
      * @@roseuid 41BFCEC8037C<BR>
      */
     public WEB3MarginBalanceReferenceResponse getBalanceReference(WEB3MarginBalanceReferenceRequest l_request) 
     {
         final String STR_METHOD_NAME = "getBalanceReference(WEB3MarginBalanceReferenceRequest)";
         log.entering(STR_METHOD_NAME);

         WEB3MarginBalanceReferenceService l_service = null;
         WEB3MarginBalanceReferenceResponse l_response = null;

         try
         {
             // �M�p����c���Ɖ�T�[�r�X���擾
             l_service =
                 (WEB3MarginBalanceReferenceService) Services.getService(
                     WEB3MarginBalanceReferenceService.class);
         }
         catch (Exception e)
         {
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "�M�p����c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                 l_response.errorInfo,e);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
                
         try
         {        
             //execute()���\�b�h���R�[������B
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_service.execute(l_request);
         }
         catch (WEB3BaseException l_be)
         {
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_request.createResponse();
             l_response.errorInfo = l_be.getErrorInfo();
             log.error(l_request, "�M�p����c���Ɖ�Ɏ��s���܂����B", l_be);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (WEB3BaseRuntimeException l_bre)
         {
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_request.createResponse();
             l_response.errorInfo = l_bre.getErrorInfo();
             log.error(l_request, "�M�p����c���Ɖ�Ɏ��s���܂����B", l_bre);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
            
         log.exiting(STR_METHOD_NAME);

         // ���X�|���X�I�u�W�F�N�g��ԋp����B
         return l_response;
     }
     
     /**
      * (get�c�����v)<BR>
      * <BR>
      * �M�p����c�����v�������s���B<BR>
      * <BR>
      * �M�p����c���Ɖ�T�[�r�XImpl���擾���A<BR>
      * execute()���\�b�h���R�[������B<BR>
      * @@param l_request - (���N�G�X�g�f�[�^) �M�p����c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g<BR>
      * @@return WEB3MarginBalanceReferenceTotalResponse<BR>
      * @@roseuid 41BFCF360040<BR>
      */
     public WEB3MarginBalanceReferenceTotalResponse getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest l_request) 
     {
         final String STR_METHOD_NAME = "getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest)";
         log.entering(STR_METHOD_NAME);

         WEB3MarginBalanceReferenceService l_service = null;
         WEB3MarginBalanceReferenceTotalResponse l_response = null;

         try
         {
             //�M�p����c���Ɖ�T�[�r�X���擾
             l_service =
                 (WEB3MarginBalanceReferenceService) Services.getService(
                     WEB3MarginBalanceReferenceService.class);
         }
         catch (Exception e)
         {
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "�M�p����c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                 l_response.errorInfo,
                 e);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
                
         try
         {        
             //execute()���\�b�h���R�[������B
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_service.execute(l_request);
         }
         catch (WEB3BaseException l_be)
         {
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_request.createResponse();
             l_response.errorInfo = l_be.getErrorInfo();
             log.error(l_request, "�M�p����c�����v�Ɏ��s���܂����B", l_be);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (WEB3BaseRuntimeException l_bre)
         {
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_request.createResponse();
             l_response.errorInfo = l_bre.getErrorInfo();
             log.error(l_request, "�M�p����c�����v�Ɏ��s���܂����B", l_bre);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
            
         log.exiting(STR_METHOD_NAME);

         // ���X�|���X�I�u�W�F�N�g��ԋp����B
         return l_response;
     }
}
@
