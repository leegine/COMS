head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ʒm�n���h��(WEB3EquityOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �R�w�� (���u) �V�K�쐬
                   2004/12/15 �����a��(SAR) �c�Č��Ή� �m��.�O�V�T
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3EquityOrderNotifyRequest;
import webbroker3.equity.message.WEB3EquityOrderNotifyResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �i���������ʒm�n���h���j�B<br>
 * <br>
 * �����ʒm�n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyHandler.class);

    /**
     * @@roseuid 40B40A7C03C8
     */
    public WEB3EquityOrderNotifyHandler()
    {

    }

    /**
     * �i�������������ʒm���N�G�X�g�j�B<br>
     * <br>
     * MAXAS��胊�N�G�X�g���󂯁A�������������ʒm���������s����B <BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�@@���������ʒm�T�[�r�X�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@���������ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A <BR>
     * �������ʂł��錻�����������ʒm���X�|���X�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A <BR>
     * �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B <BR>
     * <BR>
     * �S�j�@@�������������ʒm���X�|���X�I�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * @@param l_request - (���̓f�[�^) <BR>
     * MAXAS����̃��N�G�X�g���b�Z�[�W���w�肷��B <BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 4021F72601DF
     */
    public WEB3BackResponse WEB3EquityOrderNotifyRequest(WEB3EquityOrderNotifyRequest l_request)
    {
        // �������������T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "WEB3EquityOrderNotifyRequest(WEB3EquityOrderNotifyRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderNotifyResponse l_response = null;
        WEB3EquityOrderNotifyService l_service = null;

        // ���������ʒm�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3EquityOrderNotifyService) Services.getService(
                    WEB3EquityOrderNotifyService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_e)
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            // ��Е��X�戵�\�s��Params���擾�ł��܂���ł���
            log.error(
                l_request,
                "���������ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "���������ʒm�Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "���������ʒm�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
