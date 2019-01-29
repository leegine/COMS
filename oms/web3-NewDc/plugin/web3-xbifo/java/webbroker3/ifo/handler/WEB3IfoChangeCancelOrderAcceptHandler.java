head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoChangeCancelOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright       : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name       :�敨OP���������t�n���h���N���X(WEB3IfoChangeCancelOrderAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22   ���Ō� (Sinocom) �V�K�쐬 
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptService;
import webbroker3.ifo.message.WEB3IfoChangeCancelAcceptResponse;
import webbroker3.ifo.message.WEB3IfoChangeCancelAcceptRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP���������t�n���h��)<BR>
 * �敨OP���������t�n���h���N���X<BR>
 * @@author  : ���Ō�
 * @@version : 1.0
 */
public class WEB3IfoChangeCancelOrderAcceptHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoChangeCancelOrderAcceptHandler.class);
            
    /**
     * @@roseuid 40C07552037A
     */
    public WEB3IfoChangeCancelOrderAcceptHandler() 
    {
     
    }
    
    /**
     * (�敨OP���������t���N�G�X�g)<BR>
     * �敨OP������t���������{����B<BR>
     * <BR>
     * �敨OP������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �敨OP������t���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3IfoChangeCancelAcceptResponse
     * @@roseuid 40837F4B00A8
     */
    public WEB3IfoChangeCancelAcceptResponse ifoChangeCancelOrderAcceptRequest(
            WEB3IfoChangeCancelAcceptRequest l_request) 
    {
        log.debug(" (�敨OP���������t���N�G�X�g)");
        final String STR_METHOD_NAME =
            getClass().getName()
                + "optionOrderAcceptRequest(WEB3IfoOrderAcceptRequest l_request)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoChangeCancelAcceptResponse l_response = null;
        WEB3IfoChangeCancelOrderAcceptService l_service = null;
        
        //�����w���I�v�V���������V�K���T�[�r�X���擾����
        try
        {
            l_service =
                    (WEB3IfoChangeCancelOrderAcceptService)Services.getService(
                    WEB3IfoChangeCancelOrderAcceptService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IfoChangeCancelAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V�������������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3IfoChangeCancelAcceptResponse)l_service.execute(
                    l_request);
                   
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IfoChangeCancelAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�������������t�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
