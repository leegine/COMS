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
filename	WEB3FuturesContractInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨���ʏƉ�n���h���N���X(WEB3FuturesContractInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 ���� (���u) �V�K�쐬
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesContractReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesContractInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨���ʏƉ�n���h��)<BR>
 * �����w���敨���ʏƉ�n���h���N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesContractInquiryHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractInquiryHandler.class);
        
    /**
     * @@roseuid 40F7B0700261
     */
    public WEB3FuturesContractInquiryHandler() 
    {
     
    }
    
    /**
     * (get����)<BR>
     * �����w���敨���ʏƉ�����s���B<BR>
     * <BR>
     * �����w���敨���ʏƉ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����w���敨���ʏƉ�N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FuturesContractReferenceResponse
     * @@roseuid 40A4C0E5032B
     */
    public WEB3FuturesContractReferenceResponse getContract(WEB3FuturesContractReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "getContract(WEB3FuturesContractReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesContractReferenceResponse l_response = null;
        WEB3FuturesContractInquiryService l_service = null;

        try
        {
            //�����w���敨���ʏƉ�T�[�r�X�I�u�W�F�N�g���擾����

            l_service =
                (WEB3FuturesContractInquiryService)Services.getService(
            WEB3FuturesContractInquiryService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨���ʏƉ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex); 
            log.exiting(STR_METHOD_NAME);           
            return l_response;
        }

        try
        {
            //�����w���敨���ʏƉ�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
            //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B           
            l_response = (WEB3FuturesContractReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨���ʏƉ�Ɏ��s���܂����B", l_ex);      
            log.exiting(STR_METHOD_NAME);               
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
