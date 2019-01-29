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
filename	WEB3FuturesOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����w���敨�V�K�����̓n���h���N���X(WEB3FuturesOpenMarginInputHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/21 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesProductSelectRequest;
import webbroker3.ifo.message.WEB3FuturesProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�V�K�����̓n���h��)<BR>
 * �����w���敨�V�K�����̓n���h���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3FuturesOpenContractInputHandler implements MessageHandler
{
    /**
      * Logger
      */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractInputHandler.class);

    /**
     * @@roseuid 40F7B07101E4
     */
    public WEB3FuturesOpenContractInputHandler()
    {

    }

    /**
     * (�V�K�����̓��N�G�X�g)<BR>
     * �����w���敨�V�K�����͕\���������s���B<BR>
     * <BR>
     * �����w���敨�V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FuturesOpenMarginInputResponse
     * @@roseuid 40A8520B015A
     */
    public WEB3FuturesOpenMarginInputResponse openMarginInputRequest(WEB3FuturesOpenMarginInputRequest l_request)
    {
        final String STR_METHOD_NAME = "openMarginInputRequest(WEB3FuturesOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOpenMarginInputResponse l_response = null;
        WEB3FuturesOpenContractInputService l_service = null;

        //�����w���敨�V�K������
        try
        {
            l_service = (WEB3FuturesOpenContractInputService) Services.getService(WEB3FuturesOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesOpenMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨�V�K�����̓T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }
        try
        {
            //�����w���敨�V�K�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
            l_response = (WEB3FuturesOpenMarginInputResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOpenMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�V�K�����͂̕\�������Ɏ��s���܂����B", l_ex);

            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (�V�K�������I�����N�G�X�g)<BR>
     * �����w���敨�V�K�������I��\���������s���B<BR>
     * <BR>
     * �����w���敨�V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FuturesProductSelectResponse
     * @@roseuid 40A853560244
     */
    public WEB3FuturesProductSelectResponse openMarginProductSelectRequest(WEB3FuturesProductSelectRequest l_request)
    {
        final String STR_METHOD_NAME = ".openMarginProductSelectRequest(WEB3FuturesProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesProductSelectResponse l_response = null;
        WEB3FuturesOpenContractInputService l_service = null;

        //�����w���敨�V�K�������I��\���������s��
        try
        {
            l_service = (WEB3FuturesOpenContractInputService) Services.getService(WEB3FuturesOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���敨�V�K�����̓T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            return l_response;
        }
        //�����w���敨�V�K�����̓T�[�r�X���擾���A execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3FuturesProductSelectResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�V�K�����̖͂����I��\�������Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
