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
filename	WEB3FuturesChangeClosingContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ԍϓ��̓n���h���N���X(WEB3FuturesChangeClosingContractInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 ���Q (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractInputService;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputResponse;

import webbroker3.util.WEB3LogUtility;


/**
 * (�����w���敨�����ԍϓ��̓n���h��)<BR>
 * �����w���敨�����ԍϓ��̓n���h���N���X<BR>
 * @@author ���Q
 * @@version 1.0
 */
public class WEB3FuturesChangeClosingContractInputHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeClosingContractInputHandler.class);      
    
    /**
     * @@roseuid 40F7B0720290
     */
    public WEB3FuturesChangeClosingContractInputHandler() 
    {
     
    }
    
    /**
     * (�����ԍϓ��̓��N�G�X�g)<BR>
     * �����w���敨�����ԍϓ��͕\���������s���B<BR>
     * <BR>
     * �����w���敨�����ԍϓ��̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FuturesCloseMarginChangeInputResponse
     * @@roseuid 40A8BBBE02C6
     */
    public WEB3FuturesCloseMarginChangeInputResponse WEB3FuturesCloseMarginChangeInputRequest(WEB3FuturesCloseMarginChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
           "WEB3FuturesCloseMarginChangeInputRequest(WEB3FuturesCloseMarginChangeInputRequest l_request)";

        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesCloseMarginChangeInputResponse l_response = null;
        WEB3FuturesChangeClosingContractInputService l_service = null;
        
        //�����w���敨�����V�K�����̓T�[�r�X���擾����
        try
        {
            
            l_service =
                (WEB3FuturesChangeClosingContractInputService)Services.getService(
                WEB3FuturesChangeClosingContractInputService.class);
                
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "�����w���敨�����ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
                
            return l_response;
            
        }
        
        //�����w���敨�����V�K�����̓T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            
            l_response =
                (WEB3FuturesCloseMarginChangeInputResponse)l_service.execute(l_request);
                
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�����ԍϓ��̓T�[�r�X.execute()���\�b�h�Ɏ��s���܂����B", l_ex);
            return l_response;
            
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
