head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������t�n���h��(WEB3EquityChangeCancelAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 �����(���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequest;
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������t�n���h���j�B<BR>
 * <BR>
 * �������������t�n���h���N���X�B
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeCancelAcceptHandler.class);

    /**
     * @@roseuid 40A0A7FD00C5
     */
    public WEB3EquityChangeCancelAcceptHandler()
    {
    }

    /**
     * �i�������������t���N�G�X�g�j<BR>
     * <BR>
     * ���N�G�X�g���󂯁A�������������t���������s����B<BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@�������������t�T�[�r�X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�������������t�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A�������ʂł���
     * �������������t���X�|���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B<BR>
     * <BR>
     * �S�j�@@�������������t���X�|���X�I�u�W�F�N�g��ԋp����B<BR>
     *
     * @@param l_request - �������������t���N�G�X�g
     * @@return webbroker3.equity.message.WEB3EquityChangeCancelAcceptResponse
     */
    public WEB3EquityChangeCancelAcceptResponse equityChangeCancelAcceptRequest(WEB3EquityChangeCancelAcceptRequest l_request)
    {
        // �������������t�T�[�r�X�I�u�W�F�N�g���擾����
        final String STR_METHOD_NAME =
            "equityChangeCancelAcceptRequest(WEB3EquityChangeCancelAcceptRequest)";       
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityChangeCancelAcceptResponse l_response = null;
        WEB3EquityChangeCancelAcceptService l_service = null;

        // �������������t�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3EquityChangeCancelAcceptService) Services.getService(
                    WEB3EquityChangeCancelAcceptService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�������������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�������������t�Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�������������t�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
