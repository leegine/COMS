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
filename	WEB3OptionOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : OP�V�K�����̓n���h��(WEB3OptionOpenContractInputHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/19 ������ (���u) �V�K�쐬
                 001: 2004/07/31 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000097
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;

/**
 * (OP�V�K�����̓n���h��)<BR>
 * �����w���I�v�V�����V�K�����̓n���h���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionOpenContractInputHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractInputHandler.class);

    /**
     * @@roseuid 40C0755701A5
     */
    public WEB3OptionOpenContractInputHandler()
    {

    }

    /**
     * (�V�K�����̓��N�G�X�g)<BR>
     * <BR>
     * �����w���I�v�V�����V�K�����͕\���������s���B<BR>
     * <BR>
     * �����w���I�v�V�����V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3OptionsOpenMarginInputResponse
     * @@roseuid 407A50B00261
     */
    public WEB3OptionsOpenMarginInputResponse openContractInputRequest(WEB3OptionsOpenMarginInputRequest l_request)
    {
        final String STR_METHOD_NAME = "openContractInputRequest(WEB3OptionsOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsOpenMarginInputResponse l_response = null;
        WEB3OptionOpenContractInputService l_service = null;

        //�����w���I�v�V�����V�K�����͕\���������s��
        try
        {
            l_service = (WEB3OptionOpenContractInputService)Services.getService(WEB3OptionOpenContractInputService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3OptionsOpenMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���I�v�V�����V�K�����̓T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);

            return l_response;
        }
        try
        {
            //�����w���I�v�V�����V�K�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
            l_response = (WEB3OptionsOpenMarginInputResponse)l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOpenMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V�����V�K�����͂̕\�������Ɏ��s���܂����B", l_ex);
            
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (�V�K�������I�����N�G�X�g)<BR>
     * <BR>
     * �����w���I�v�V�����V�K�������I��\���������s���B<BR>
     * <BR>
     * �����w���I�v�V�����V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3OptionsProductSelectResponse
     * @@roseuid 40A888A300FC
     */
    public WEB3OptionsProductSelectResponse openContractProductSelectRequest(WEB3OptionsProductSelectRequest l_request)
    {
        final String STR_METHOD_NAME = ".openContractProductSelectRequest(WEB3OptionsProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsProductSelectResponse l_response = null;
        WEB3OptionOpenContractInputService l_service = null;

        //�����w���I�v�V�����V�K�������I��\���������s��
        try
        {
            l_service = (WEB3OptionOpenContractInputService)Services.getService(WEB3OptionOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsProductSelectResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���I�v�V�����V�K�����̓T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);

            return l_response;
        }
        //�����w���I�v�V�����V�K�����̓T�[�r�X���擾���A execute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3OptionsProductSelectResponse)l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsProductSelectResponse)l_request.createResponse();
            
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "�����w���I�v�V�����V�K�����̖͂����I��\�������Ɏ��s���܂����B", l_ex);
            
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
