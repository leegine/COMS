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
filename	WEB3EquityOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������t�n���h���N���X(WEB3EquityOrderAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/24 �����@@���F(SRA) �V�K�쐬
                   2004/10/22�@@���C��   
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityOrderAcceptRequest;
import webbroker3.equity.message.WEB3EquityOrderAcceptResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i����������t�n���h���j�B<BR>
 * <BR>
 * ����������t�n���h���N���X
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptHandler.class);
    /**
     * @@roseuid 40B1E6D400FA
     */
    public WEB3EquityOrderAcceptHandler()
    {
    }

    /**
     * (����������t���N�G�X�g )
     * ���N�G�X�g���󂯁A����������t���������s����B <BR>
     * �i�V�X�e���������j�K�C�h 4.5.�n���h���@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�@@����������t�T�[�r�X�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@����������t�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A <BR>
     * �������ʂł��銔��������t���X�|���X�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�@@�T�[�r�X�ŗ�O�����������ꍇ�́A <BR>
     * �G���[�������X�|���X���b�Z�[�W�ɐݒ肷��B <BR>
     * <BR>
     * �S�j�@@����������t���X�|���X�I�u�W�F�N�g��ԋp����B <BR>
     * <BR>
     * @@param WEB3EquityOrderAcceptRequest - (���̓f�[�^) <BR>
     * �N���C�A���g����̃��N�G�X�g���b�Z�[�W���w�肷��B <BR>
     * @@return WEB3EquityOrderAcceptResponse
     * @@roseuid 4035DA8303B9
     */
    public WEB3EquityOrderAcceptResponse equityOrderAcceptRequest(WEB3EquityOrderAcceptRequest request)
    {
        final String STR_METHOD_NAME =
            "equityOrderAcceptRequest(WEB3EquityOrderAcceptRequest)";
        log.entering(STR_METHOD_NAME);
        if (request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityOrderAcceptResponse l_response = null;
        WEB3EquityOrderAcceptService l_service = null;
        try
        {
            l_service =
                (WEB3EquityOrderAcceptService) Services.getService(
                    WEB3EquityOrderAcceptService.class);
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                request,
                "����������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) l_service.execute(request);
        }
        catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(request, "����������t�Ɏ��s���܂����B", l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityOrderAcceptResponse) request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(request, "����������t�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
