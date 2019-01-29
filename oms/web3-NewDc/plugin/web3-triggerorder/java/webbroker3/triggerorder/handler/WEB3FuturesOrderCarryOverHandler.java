head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����J�z�n���h���N���X(WEB3FuturesOrderCarryOverHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 䈋� (���u) �V�K�쐬
Revesion History : 2007/7/18 ��іQ (���u) WEB3-ES-IFO-A-FT-0059
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3FuturesOrderCarryOverRequest;
import webbroker3.triggerorder.message.WEB3FuturesOrderCarryOverResponse;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����w���敨�����J�z�n���h��)<BR>
 * �����w���敨�����J�z�n���h���N���X<BR>
 */
public class WEB3FuturesOrderCarryOverHandler implements MessageHandler
{
    /**
       * ���O�o�̓��[�e�B���e�B�B<BR>
       */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderCarryOverHandler.class);
    /**
     * @@roseuid 40F7B07102CE
     */
    public WEB3FuturesOrderCarryOverHandler()
    {
    }

    /**
     * (�敨�����J�z���N�G�X�g)<BR>
     * �����w���敨�����J�z�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FuturesOrderCarryOverResponse
     */
    public WEB3FuturesOrderCarryOverResponse orderCarryOverRequest(WEB3FuturesOrderCarryOverRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName()
                + ".orderCarryOverRequest(WEB3FuturesOrderCarryOverRequest l_request)";

        log.debug(STR_METHOD_NAME);

        WEB3FuturesOrderCarryOverResponse l_response = null;
        WEB3FuturesOrderCarryOverService l_service = null;

        try
        {
            l_service =
                (WEB3FuturesOrderCarryOverService)Services.getService(
                    WEB3FuturesOrderCarryOverService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����w���敨�����J�z�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����w���敨�����J�z�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3FuturesOrderCarryOverResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����w���敨�����J�z�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.debug(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
