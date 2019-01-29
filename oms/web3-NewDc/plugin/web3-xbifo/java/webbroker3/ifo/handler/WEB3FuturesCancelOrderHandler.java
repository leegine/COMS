head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨��������n���h���N���X(WEB3FuturesCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3FuturesCancelCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesCancelOrderService;

/**
 * (�����w���敨��������n���h��)<BR>
 * �����w���敨��������n���h���N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesCancelOrderHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCancelOrderHandler.class);    
    /**
     * @@roseuid 40F7B070032C
     */
    public WEB3FuturesCancelOrderHandler() 
    {
     
    }
    
    /**
     * (confirm���)<BR>
     * �����w���敨�̎�������R�����s���B<BR>
     * <BR>
     * �����w���敨��������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V������������m�F���N�G�X�g<BR>
     * @@return WEB3FuturesCancelConfirmResponse
     * @@roseuid 40A819C50254
     */
    public WEB3FuturesCancelConfirmResponse confirmCancel(WEB3FuturesCancelConfirmRequest l_request) 
    {
        final String METHOD_NAME =
                "confirmCancel(WEB3FuturesCancelConfirmRequest)";
    
        log.entering(METHOD_NAME);

        WEB3FuturesCancelConfirmResponse l_response = null;
        WEB3FuturesCancelOrderService l_service = null;
        
        //�����w���I�v�V������������T�[�r�X���擾
        try
        {
            l_service =
                (WEB3FuturesCancelOrderService)Services.getService(
                    WEB3FuturesCancelOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���敨��������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //�����w���敨��������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        
        try
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�����w���敨��������̔����R���Ɏ��s���܂����B", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���敨��������̔����R���Ɏ��s���܂����B", l_rex);
            return l_response;
        }
        
        return l_response;
    }
    
    /**
     * (complete���)<BR>
     * �����w���敨�̎��������o�^����B<BR>
     * <BR>
     * �����w���敨��������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V������������������N�G�X�g<BR>
     * @@return WEB3FuturesCancelCompleteResponse
     * @@roseuid 40A819C50263
     */
    public WEB3FuturesCancelCompleteResponse completeCancel(WEB3FuturesCancelCompleteRequest l_request) 
    {
        final String METHOD_NAME =
                "completeCancel(WEB3FuturesCancelCompleteRequest)";    
        log.entering(METHOD_NAME);

        WEB3FuturesCancelCompleteResponse l_response = null;
        WEB3FuturesCancelOrderService l_service      = null;
        
        //�����w���敨��������T�[�r�X���擾
        try
        {
            l_service =
                (WEB3FuturesCancelOrderService)Services.getService(
                    WEB3FuturesCancelOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���敨��������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //�����w���敨��������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�����w���敨��������̓o�^�Ɏ��s���܂����B", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���敨��������̓o�^�Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
