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
filename	WEB3FuturesChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ԍσn���h���N���X(WEB3FuturesChangeClosingContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteRequest;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractService;

import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�����ԍσn���h��)<BR>
 * �����w���敨�����ԍσn���h���N���X<BR>
 * @@author ���Q
 * @@version 1.0
 */
public class WEB3FuturesChangeClosingContractHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeClosingContractHandler.class);
            
    /**
     * @@roseuid 40F7B07301D4
     */
    public WEB3FuturesChangeClosingContractHandler() 
    {
     
    }
    
    /**
     * (confirm�����ԍ�)<BR>
     * �����w���敨�̒����ԍϔ����R�����s���B<BR>
     * <BR>
     * �����w���敨�����ԍσT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�����ԍϊm�F���N�G�X�g
     * @@return WEB3FuturesCloseMarginChangeConfirmResponse
     * @@roseuid 40A8B03003A4
     */
    public WEB3FuturesCloseMarginChangeConfirmResponse confirmChangeClosingContract(WEB3FuturesCloseMarginChangeConfirmRequest l_request) 
    {

        final String STR_METHOD_NAME =
            "confirmChangeClosingContract(WEB3FuturesCloseMarginChangeConfirmRequest l_request)";
                        
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesCloseMarginChangeConfirmResponse l_response = null;
        WEB3FuturesChangeClosingContractService l_service = null;

        //�����w���敨�����V�K���T�[�r�X���擾����
        try
        {
            
            l_service =
                (WEB3FuturesChangeClosingContractService)Services.getService(
                WEB3FuturesChangeClosingContractService.class);
                
        }
        
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�����w���敨�̒����ԍσT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
                
            return l_response;
            
        }
        
        //�����w���敨�����V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            
            l_response =
                (WEB3FuturesCloseMarginChangeConfirmResponse)l_service.execute(l_request);
                
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�̒����ԍσT�[�r�X.execute()�����s���܂����B", l_ex);
            return l_response;
            
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���敨�̒����ԍσT�[�r�X.execute()�����s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
        
    }
    
    /**
     * (complete�����ԍ�)<BR>
     * �����w���敨�̒����ԍϒ�����o�^����B<BR>
     * <BR>
     * �����w���敨�����ԍσT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨�����ԍϊ������N�G�X�g
     * @@return WEB3FuturesCloseMarginChangeCompleteResponse
     * @@roseuid 40A8B03003B3
     */
    public WEB3FuturesCloseMarginChangeCompleteResponse completeChangeClosingContract(WEB3FuturesCloseMarginChangeCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME =
            "completeChangeClosingContract(WEB3FuturesCloseMarginChangeCompleteRequest l_request)";
             
                
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesCloseMarginChangeCompleteResponse l_response = null;
        WEB3FuturesChangeClosingContractService l_service = null;
        
        //�����w���敨�����V�K���T�[�r�X���擾����
        try
        {
            
            l_service =
                (WEB3FuturesChangeClosingContractService)Services.getService(
                WEB3FuturesChangeClosingContractService.class);
                
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�����w���敨�̒����ԍσT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
                
            return l_response;
            
        }
        
        //�����w���I�v�V���������V�K���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            
            l_response =
                (WEB3FuturesCloseMarginChangeCompleteResponse)l_service.execute(l_request);
                
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�̒����ԍσT�[�r�X.execute()�����s���܂����B", l_ex);
            return l_response;
            
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "�����w���敨�̒����ԍσT�[�r�X.execute()�����s���܂����B", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
