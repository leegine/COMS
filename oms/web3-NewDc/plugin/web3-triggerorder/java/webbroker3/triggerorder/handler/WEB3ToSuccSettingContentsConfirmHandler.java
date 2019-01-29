head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingContentsConfirmHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݒ���e�m�F�n���h��(WEB3ToSuccSettingContentsConfirmHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/1�V ���r(���u) �V�K�쐬
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingContentsConfirmService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ݒ���e�m�F�n���h��)<BR>
 * �ݒ���e�m�F�n���h���N���X<BR>
 * @@author ���r <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccSettingContentsConfirmHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingContentsConfirmHandler.class);
    
    
    /**
     * @@roseuid 434B592E0399
     */
    public WEB3ToSuccSettingContentsConfirmHandler() 
    {
     
    }
    
    /**
     * (get�m�F���)<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ݒ���e�m�F���N�G�X�g�I�u�W�F�N�gBR>
     * @@return WEB3SuccSettingContentConfirmResponse
     * @@roseuid 431D18E2001A
     */
    public WEB3SuccSettingContentConfirmResponse getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccSettingContentConfirmResponse l_response = null;
        WEB3ToSuccSettingContentsConfirmService l_service = null;       
        // �ݒ���e�m�F�T�[�r�XImpl���擾���A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =(WEB3ToSuccSettingContentsConfirmService) 
				Services.getService(WEB3ToSuccSettingContentsConfirmService.class);        
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =(WEB3SuccSettingContentConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�ݒ���e�m�F�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccSettingContentConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccSettingContentConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ݒ���e�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3SuccSettingContentConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�ݒ���e�m�F�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
