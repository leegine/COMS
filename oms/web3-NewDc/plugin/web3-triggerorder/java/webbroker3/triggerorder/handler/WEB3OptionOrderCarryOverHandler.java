head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3OptionOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�����J�z�n���h��(WEB3OptionOrderCarryOverHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 ���� (���u) �V�K�쐬
              001: 2004/07/26 ���Ō� (���u)�Ή��o�b�O WEB3_IFO_UT-000026  log�AorderCarryOverRequest()���C��
Revesion History : 2007/07/18 ��іQ (���u) WEB3-ES-IFO-A-FT-0059
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3OptionsOrderCarryOverRequest;
import webbroker3.triggerorder.message.WEB3OptionsOrderCarryOverResponse;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP�����J�z�n���h��)<BR>
 * �����w���I�v�V���������J�z�n���h���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionOrderCarryOverHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOrderCarryOverHandler.class);

    /**
     * @@roseuid 40C0754F0177
     */
    public WEB3OptionOrderCarryOverHandler()
    {

    }

    /**
     * �����w���I�v�V���������J�z�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     */
    public WEB3OptionsOrderCarryOverResponse orderCarryOverRequest(WEB3OptionsOrderCarryOverRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName() + ".getContract(WEB3OptionsOrderCarryOverRequest)";

        log.debug(STR_METHOD_NAME + " : ENTER");

        WEB3OptionsOrderCarryOverResponse l_response = null;
        WEB3OptionOrderCarryOverService l_service = null;

        try
        {
            log.debug("�����w���I�v�V���������J�z�T�[�r�X���擾: ENTER");

            l_service =
                (WEB3OptionOrderCarryOverService)Services.getService(
                    WEB3OptionOrderCarryOverService.class);
            log.debug("�����w���I�v�V���������J�z�T�[�r�X���擾: END");
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3OptionsOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�����w���I�v�V���������J�z�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            log.debug("�����w���I�v�V���������J�z�T�[�r�X�I�u�W�F�N�g.execute�i): ENTER");
            l_response = (WEB3OptionsOrderCarryOverResponse)l_service.execute(l_request);
            log.debug("�����w���I�v�V���������J�z�T�[�r�X�I�u�W�F�N�g.execute�i): END");
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���I�v�V���������J�z�Ɏ��s���܂����B", l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3OptionsOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����w���I�v�V���������J�z�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.debug(STR_METHOD_NAME + " : END");

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
