head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݐϓ������������ʒm�n���h���N���X(WEB3RuitoTradeOrderNotifyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 �m�X (���u) �V�K�쐬
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyService;

/**
 * �ݐϓ������������ʒm�n���h���N���X
 */
public class WEB3RuitoTradeOrderNotifyHandler implements MessageHandler 
{
       /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyHandler.class);
   
   /**
    * @@roseuid 40B40A7C03C8
    */
   public WEB3RuitoTradeOrderNotifyHandler() 
   {
    
   }
   
   /**
    * �ݐϓ������������ʒm���s���B<BR>
    * <BR>
    * �ݐϓ������������ʒm�T�[�r�X���擾���A<BR>
    * <BR>
    * execute()���\�b�h���R�[������B<BR>
    * <BR>
    * MAXAS��胊�N�G�X�g���󂯁A�ݓ����������ʒm���������s����B<BR>
    * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj<BR>
    * <BR>
    * �P�j�@@�ݓ����������ʒm�T�[�r�X�I�u�W�F�N�g���擾����B<BR>
    * <BR>
    * �Q�j�@@�ݓ����������ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A<BR>
    *       �������ʂł���ݓ����������ʒm���X�|���X�I�u�W�F�N�g���擾����B<BR>
    * <BR>
    * �R�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A<BR>
    *       �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B<BR>
    * <BR>
    * �S�j�@@�ݓ����������ʒm���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
    * @@param l_request - �ݐϓ������������ʒm���N�G�X�g�I�u�W�F�N�g <BR>
    * @@return webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse
    * @@roseuid 405A4E4F008B
    */
   public WEB3RuitoDealingOrderNotifyResponse tradeOrderNotifyRequest(
       WEB3RuitoDealingOrderNotifyRequest l_request) 
   {
     // �ݐϓ������������ʒm�T�[�r�X�I�u�W�F�N�g���擾����
         final String STR_METHOD_NAME = 
             "WEB3RuitoDealingOrderNotifyRequest(WEB3BackRequest)";

       log.entering(STR_METHOD_NAME);
       if (l_request == null)
       {
           log.debug(" �p�����[�^�l��NULL ");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "�p�����[�^�l��NULL");            
       }
        WEB3RuitoDealingOrderNotifyResponse l_response = null;
        WEB3RuitoTradeOrderNotifyService    l_service  = null;
         
         // �ݐϓ������������ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
         // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
         try
         {
             l_service =(WEB3RuitoTradeOrderNotifyService) 
                 Services.getService(WEB3RuitoTradeOrderNotifyService.class);
             l_response =
                 (WEB3RuitoDealingOrderNotifyResponse) 
                 l_service.execute(l_request);
         }
         catch (WEB3BaseException e)
         {
             l_response =
                 (WEB3RuitoDealingOrderNotifyResponse) 
                 l_request.createResponse();
             l_response.errorInfo = e.getErrorInfo();
             log.error(l_request, "�ݐϓ������������ʒm�Ɏ��s���܂����B", e);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);

         // ���X�|���X�I�u�W�F�N�g��ԋp����B
         return l_response;
   }
}
@
