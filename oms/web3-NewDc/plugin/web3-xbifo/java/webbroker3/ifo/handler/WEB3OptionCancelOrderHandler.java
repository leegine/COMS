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
filename	WEB3OptionCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP��������n���h��(WEB3OptionCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/19 ���� �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.ifo.service.delegate.WEB3OptionCancelOrderService;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * (OP��������n���h��)<BR>
 * �����w���I�v�V������������n���h���N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionCancelOrderHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionCancelOrderHandler.class);    
        
    /**
     * @@roseuid 40C0754F004E
     */
    public WEB3OptionCancelOrderHandler() 
    {
     
    }
    
    /**
     * (confirm���)<BR>
     * �����w���I�v�V�����̎�������R�����s���B<BR>
     * <BR>
     * �����w���I�v�V������������T�[�r�X���擾���A<BR>execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V������������m�F���N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse
     * @@roseuid 4051431F004D
     */
    public WEB3OptionsCancelConfirmResponse confirmCancel(WEB3OptionsCancelConfirmRequest l_request) 
    {
        final String METHOD_NAME =
                "confirmCancel(WEB3OptionsCancelConfirmRequest)";
    
        log.entering(METHOD_NAME);

        WEB3OptionsCancelConfirmResponse l_response = null;
        WEB3OptionCancelOrderService l_service      = null;
        
        //�����w���I�v�V������������T�[�r�X���擾
        try
        {
            l_service =
                (WEB3OptionCancelOrderService)Services.getService(
                    WEB3OptionCancelOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V������������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //�����w���I�v�V������������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����̎�������R���Ɏ��s���܂����B", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����̎�������R���Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (complete���)<BR>
     * �����w���I�v�V�����̎��������o�^����B<BR>
     * <BR>
     * �����w���I�v�V������������T�[�r�X���擾���A<BR>execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���I�v�V������������������N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse
     * @@roseuid 40514347027F
     */
    public WEB3OptionsCancelCompleteResponse completeCancel(WEB3OptionsCancelCompleteRequest l_request) 
    {
        final String METHOD_NAME =
                "confirmCancel(WEB3OptionsCancelCompleteRequest)";
    
        log.entering(METHOD_NAME);

        WEB3OptionsCancelCompleteResponse l_response = null;
        WEB3OptionCancelOrderService l_service      = null;
        
        //�����w���I�v�V������������T�[�r�X���擾
        try
        {
            l_service =
                (WEB3OptionCancelOrderService)Services.getService(
                    WEB3OptionCancelOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V������������T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //�����w���I�v�V������������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����̎��������o�^�Ɏ��s���܂����B", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����̎��������o�^�Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
