head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��M���������o�^�n���h��(WEB3AdminMutualConditionsHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 �����(���u) �V�K�쐬
                   2004/08/25 ��O�� (���u) ���r���[    
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ��ғ��M���������o�^�n���h�� �N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualConditionsHandler implements MessageHandler 
{
    
    /**
     * ���O���[�e�B���e�B
     */

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsHandler.class);

    /**
     * (���������o�^���̓��N�G�X�g)<BR>
     * �Ǘ��ғ����M�� ���������o�^���͏������s���B<BR>
     * <BR>
     * ���M���������o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse
     * @@roseuid 40E3D326018F
     */
    public WEB3AdminMutualConditionsInputResponse productConditionsRegistInputRequest(
        WEB3AdminMutualConditionsInputRequest l_request) 
    {
        final String l_strMethodName = "productConditionsRegistInputRequest("
            + "WEB3AdminMutualConditionsInputRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualConditionsService l_service = null;
        WEB3AdminMutualConditionsInputResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminMutualConditionsService)Services.getService(
                    WEB3AdminMutualConditionsService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ғ��M���������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualConditionsInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ғ����M�� ���������o�^���͂Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
    
    /**
     * (search���������o�^)<BR>
     * �Ǘ��ғ����M�� ���������o�^�m�F�������s���B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse
     * @@roseuid 40E3D3260191
     */
    public WEB3AdminMutualConditionsConfirmResponse searchProductConditionsRegist(
        WEB3AdminMutualConditionsConfirmRequest l_request) 
    {
        final String l_strMethodName = "searchProductConditionsRegist("
            + "WEB3AdminMutualConditionsConfirmRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualConditionsService l_service = null;
        WEB3AdminMutualConditionsConfirmResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminMutualConditionsService)Services.getService(
                    WEB3AdminMutualConditionsService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ғ��M���������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualConditionsConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ғ����M�� ���������o�^�m�F�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
    
    /**
     * (complete���������o�^)<BR>
     * �Ǘ��ғ����M�� ���������o�^�����������s���B<BR>
     * <BR>
     * ���M�C�O���������o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse
     * @@roseuid 40E3D3260193
     */
    public WEB3AdminMutualConditionsCompleteResponse completeProductConditionsRegist(
        WEB3AdminMutualConditionsCompleteRequest l_request) 
    {
        final String l_strMethodName = "completeProductConditionsRegist("
            + "WEB3AdminMutualConditionsCompleteRequest l_request ";
        log.entering(l_strMethodName);
        WEB3AdminMutualConditionsService l_service = null;
        WEB3AdminMutualConditionsCompleteResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminMutualConditionsService)Services.getService(
                    WEB3AdminMutualConditionsService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ғ��M���������o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualConditionsCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualConditionsCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ғ����M�� ���������o�^�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);

        return l_response;
    }
}
@
