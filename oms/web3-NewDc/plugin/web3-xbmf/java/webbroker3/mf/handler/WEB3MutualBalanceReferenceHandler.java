head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�n���h���N���X(WEB3MutualBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualBalanceReferenceRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse;
import webbroker3.mf.service.delegate.WEB3MutualBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�c���Ɖ�n���h��)<BR>
 * ���M�c���Ɖ�n���h���N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceHandler.class);
   
   /**
    * (get�c���Ɖ�)<BR>
    * ���M�c���Ɖ�����s���B<BR>
    * <BR>
    * ���M�c���Ɖ�T�[�r�XImpl���擾���A<BR>
    * execute()���\�b�h���R�[������B
    * @@param l_request - ���M�c���Ɖ�N�G�X�g�I�u�W�F�N�g
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceResponse
    * @@roseuid 41AD8D930224
    */
   public WEB3MutualBalanceReferenceResponse getBalanceReference(WEB3MutualBalanceReferenceRequest l_request) 
   {
       final String STR_METHOD_NAME = "getBalanceReference()";
       log.entering(STR_METHOD_NAME);

       WEB3MutualBalanceReferenceService l_service = null;
       WEB3MutualBalanceReferenceResponse l_response = null;

       try
       {
           l_service =
               (WEB3MutualBalanceReferenceService) Services.getService(
                   WEB3MutualBalanceReferenceService.class);
       }
       // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
       catch (Exception l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "���M�c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
               l_response.errorInfo,
               l_ex);
           return l_response;
       }

       try
       {
           l_response =
               (WEB3MutualBalanceReferenceResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceResponse) l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();
           log.error(
               l_request,
               "���M�c���Ɖ�������s���܂����B",
               l_ex.getErrorInfo(),
               l_ex);
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (get�c�����v)<BR>
    * ���M�c�����v�������s���B<BR>
    * <BR>
    * ���M�c���Ɖ�T�[�r�XImpl���擾���A<BR>
    * execute()���\�b�h���R�[������B
    * @@param l_request - ���M�c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse
    * @@roseuid 41AD8DD80020
    */
   public WEB3MutualBalanceReferenceTotalResponse getBalanceReferenceTotal(WEB3MutualBalanceReferenceTotalRequest l_request) 
   {
       final String STR_METHOD_NAME = "getBalanceReferenceTotal()";
       log.entering(STR_METHOD_NAME);

       WEB3MutualBalanceReferenceService l_service = null;
       WEB3MutualBalanceReferenceTotalResponse l_response = null;

       try
       {
           l_service =
               (WEB3MutualBalanceReferenceService) Services.getService(
                   WEB3MutualBalanceReferenceService.class);
       }
       // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
       catch (Exception l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceTotalResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "���M�c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
               l_response.errorInfo,
               l_ex);
           return l_response;
       }

       try
       {
           l_response =
               (WEB3MutualBalanceReferenceTotalResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceTotalResponse) l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();
           log.error(
               l_request,
               "���M�c�����v���������s���܂����B",
               l_ex.getErrorInfo(),
               l_ex);
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
}
@
