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
filename	WEB3OptionChangeOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����V�K���n���h��(WEB3OptionChangeOpenContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractService;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;


/**
 * (OP�����V�K���n���h��)<BR>
 * �����w���I�v�V���������V�K���n���h���N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeOpenContractHandler.class);
    
    /**
     * @@roseuid 40C0755E0177
     */
    public WEB3OptionChangeOpenContractHandler() 
    {
     
    }
    
    /**
     * (confirm�����V�K��)<BR>
     * <BR>
     * �����w���I�v�V�����̒����V�K�������R�����s���B<BR>
     * <BR>
     * �����w���I�v�V���������V�K���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �����w���I�v�V���������V�K���m�F���N�G�X�g<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse
     * @@roseuid 4056BFB70145
     */
    public WEB3OptionsOpenMarginChangeConfirmResponse confirmChangeOpenContract(WEB3OptionsOpenMarginChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".confirmChangeOpenContract(WEB3OptionsOpenMarginChangeConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmResponse l_response = null;
        WEB3OptionChangeOpenContractService l_service = null;
        
        //�����w���I�v�V���������V�K���T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3OptionChangeOpenContractService)Services.getService(
            WEB3OptionChangeOpenContractService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V���������V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������V�K���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������V�K���Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
                
    }
    
    /**
     * �����w���I�v�V�����̒����V�K��������o�^����B<BR>
     * <BR>
     * �����w���I�v�V���������V�K���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V���������V�K���������N�G�X�g
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse
     * @@roseuid 4056BFCE02BC
     */
    public WEB3OptionsOpenMarginChangeCompleteResponse completeChangeOpenContract(WEB3OptionsOpenMarginChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".completeChangeOpenContract(WEB3OptionsOpenMarginChangeCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeCompleteResponse l_response = null;
        WEB3OptionChangeOpenContractService l_service = null;
        
        //�����w���I�v�V���������V�K���T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3OptionChangeOpenContractService)Services.getService(
            WEB3OptionChangeOpenContractService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���I�v�V���������V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������V�K���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������V�K���Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
        
    }
}
@
