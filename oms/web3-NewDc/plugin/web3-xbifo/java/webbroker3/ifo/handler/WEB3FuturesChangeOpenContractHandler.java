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
filename	WEB3FuturesChangeOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����V�K���n���h���N���X(WEB3FuturesOpenMarginChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 ���� (���u) �V�K�쐬
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�����V�K���n���h��)<BR>
 * �����w���敨�����V�K���n���h���N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesChangeOpenContractHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractHandler.class);
        
    /**
     * @@roseuid 40F7B073002E
     */
    public WEB3FuturesChangeOpenContractHandler() 
    {
     
    }
    
    /**
     * (confirm�����V�K��)<BR>
     * �����w���敨�̒����V�K�������R�����s���B<BR>
     * <BR>
     * �����w���敨�����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���敨�����V�K���m�F���N�G�X�g
     * @@return WEB3FuturesOpenMarginChangeConfirmResponse
     * @@roseuid 40A8A44C001D
     */
    public WEB3FuturesOpenMarginChangeConfirmResponse confirmOpenMarginChange(WEB3FuturesOpenMarginChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "confirmOpenMarginChange(WEB3FuturesOpenMarginChangeConfirmRequest)";

        log.entering(STR_METHOD_NAME);
                
        WEB3FuturesOpenMarginChangeConfirmResponse l_response = null;
        WEB3FuturesChangeOpenContractService l_service = null;
        
        //�����w���敨�����V�K���T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3FuturesChangeOpenContractService)Services.getService(
            WEB3FuturesChangeOpenContractService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���敨�����V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�����V�K���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���敨�����V�K���Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
        
    }
    
    /**
     * (complete�����V�K��)<BR>
     * �����w���敨�̒����V�K��������o�^����B<BR>
     * <BR>
     * �����w���敨�����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���敨�����V�K���������N�G�X�g
     * @@return WEB3FuturesOpenMarginChangeCompleteResponse
     * @@roseuid 40A8A44C003C
     */
    public WEB3FuturesOpenMarginChangeCompleteResponse completeOpenMarginChange(WEB3FuturesOpenMarginChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "completeOpenMarginChange(WEB3FuturesOpenMarginChangeCompleteRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesOpenMarginChangeCompleteResponse l_response = null;
        WEB3FuturesChangeOpenContractService l_service = null;
        
        //�����w���敨�����V�K���T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3FuturesChangeOpenContractService)Services.getService(
            WEB3FuturesChangeOpenContractService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���敨�����V�K���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�����V�K���Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���敨�����V�K���Ɏ��s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
