head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���ݒ�ꗗ�n���h��(WEB3ToSuccSettingListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccSettingListRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingListResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A���ݒ�ꗗ�n���h��)<BR>
 * �A���ݒ�ꗗ�n���h���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToSuccSettingListHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingListHandler.class);
    
    /**
     * @@roseuid 4348ECB6037A
     */
    public WEB3ToSuccSettingListHandler() 
    {
     
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �A���ݒ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �A���ݒ�ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �A���ݒ�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccSettingListResponse
     * @@roseuid 431CFF6302F1
     */
    public WEB3SuccSettingListResponse getListScreen(WEB3SuccSettingListRequest l_request) 
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3SuccSettingListRequest l_request)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccSettingListResponse l_response = null;
        WEB3ToSuccSettingListService l_service = null;       
        // �A���ݒ�ꗗ��ʕ\���������s���B
        // �A���ݒ�ꗗ�T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_service =
                (WEB3ToSuccSettingListService) Services.getService(WEB3ToSuccSettingListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =(WEB3SuccSettingListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�A���ݒ�ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccSettingListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3SuccSettingListResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "�A���ݒ�ꗗ�Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3SuccSettingListResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�A���ݒ�ꗗ�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
