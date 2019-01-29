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
filename	WEB3FuturesChangeOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����V�K�����̓T�[�r�X�����N���X(WEB3FuturesChangeOpenContractInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 ���� (���u) �V�K�쐬
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�����V�K�����̓n���h��)<BR>
 * �����w���敨�����V�K�����̓n���h���N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3FuturesChangeOpenContractInputHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractInputHandler.class);
        
    /**
     * @@roseuid 40F7B0730119
     */
    public WEB3FuturesChangeOpenContractInputHandler() 
    {
     
    }
    
    /**
     * (�����V�K�����̓��N�G�X�g)<BR>
     * �����w���敨�����V�K�����͕\���������s���B<BR>
     * <BR>
     * �����w���敨�����V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FuturesOpenMarginChangeInputResponse
     * @@roseuid 40A8AB3603AC
     */
    public WEB3FuturesOpenMarginChangeInputResponse openMarginChangeInputRequest(WEB3FuturesOpenMarginChangeInputRequest l_request) 
    {        
        final String STR_METHOD_NAME = "openMarginChangeInputRequest(WEB3FuturesOpenMarginChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesOpenMarginChangeInputResponse l_response = null;
        WEB3FuturesChangeOpenContractInputService l_service = null;
        
        //�����w���敨�����V�K�����̓T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3FuturesChangeOpenContractInputService)Services.getService(
            WEB3FuturesChangeOpenContractInputService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesOpenMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���敨�����V�K�����̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        
        //�����w���敨�����V�K�����̓T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3FuturesOpenMarginChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOpenMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�����V�K�����͂Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;        
   
    }
}
@
