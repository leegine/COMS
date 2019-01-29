head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEquityManualSwitchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �抔��W�w�l�����蓮�ؑփn���h��(WEB3ToWLimitEquityManualSwitchHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/20�@@ꎉ�(���u) �V�K�쐬 (���f���jNo.184 191
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquityWLimitManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquityWLimitManualConfirmRequest;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (����W�w�l�����蓮�ؑփn���h��)<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3ToWLimitEquityManualSwitchHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitEquityManualSwitchHandler.class);

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToWLimitEquityManualSwitchHandler()
    {

    }

    /**
     * (confirm�蓮�ؑ�)<BR>
     * ����W�w�l�����蓮�ؑ֊m�F�������s���B<BR>
     * <BR>
     * �P�j�@@����W�w�l�����蓮�ؑփ��C���T�[�r�XImpl���擾���A<BR>
     * �@@�@@�@@execute()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[execute()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@request�F�@@�p�����[�^.����W�w�l�����蓮�ؑ֊m�F���N�G�X�g <BR>
     * <BR>
     * �Q�j�@@execute()�̖߂�l��Ԃ��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ����W�w�l�����蓮�ؑ֊m�F���N�G�X�g
     * @@return WEB3EquityManualConfirmResponse
     */
    public WEB3EquityManualConfirmResponse confirmManualOrder(
        WEB3EquityWLimitManualConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmManualOrder(" +
        "WEB3EquityWlimitManualConfirmRequest) ";

        WEB3EquityManualConfirmResponse l_response = null;
        WEB3ToWLimitEquityManualSwitchMainService l_service = null;

        try
        {
            //get����W�w�l�����蓮�ؑփ��C���T�[�r�X
            l_service =
                (WEB3ToWLimitEquityManualSwitchMainService)Services.getService(
                    WEB3ToWLimitEquityManualSwitchMainService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "����W�w�l�����蓮�ؑփ��C���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response = (WEB3EquityManualConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "����W�w�l�����蓮�ؑ֊m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "����W�w�l�����蓮�ؑ֊m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()�̖߂�l��Ԃ��B
        log.entering(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�蓮�ؑ�)<BR>
     * ����W�w�l�����蓮�ؑ֊����������s���B<BR>
     * <BR>
     * �P�j�@@����W�w�l�����蓮�ؑփ��C���T�[�r�XImpl���擾���A<BR>
     * �@@�@@�@@execute()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[execute()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@request�F�@@�p�����[�^.����W�w�l�����蓮�ؑ֊������N�G�X�g <BR>
     * <BR>
     * �Q�j�@@execute()�̖߂�l��Ԃ��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ����W�w�l�����蓮�ؑ֊������N�G�X�g
     * @@return WEB3EquityManualCompleteResponse
     */
    public WEB3EquityManualCompleteResponse completeManualOrder(
        WEB3EquityWLimitManualCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeManualOrder(WEB3EquityWlimitManualCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        WEB3EquityManualCompleteResponse l_response = null;
        WEB3ToWLimitEquityManualSwitchMainService l_service = null;

        try
        {
            //get����W�w�l�����蓮�ؑփ��C���T�[�r�X
            l_service =
                (WEB3ToWLimitEquityManualSwitchMainService)Services.getService(
                    WEB3ToWLimitEquityManualSwitchMainService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "����W�w�l�����蓮�ؑփ��C���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response = (WEB3EquityManualCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "����W�w�l�����蓮�ؑ֊��������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "����W�w�l�����蓮�ؑ֊��������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()�̖߂�l��Ԃ��B
        log.entering(STR_METHOD_NAME);
        return l_response;
    }
}
@
