head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l�������ҏ��ꗗ�n���h��(WEB3AdminAccInfoInsiderInfoListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 ���C�g (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l�������ҏ��ꗗ�n���h��)<BR>
 * �Ǘ��҂��q�l�������ҏ��ꗗ�n���h��<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListHandler implements MessageHandler
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoListHandler.class);
        
    /**
     * @@roseuid 418F3A0D02CE
     */
    public WEB3AdminAccInfoInsiderInfoListHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �����ҏ��ꗗ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���������ꗗ���̓��N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoInsiderInfoInputResponse inputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoInsiderInfoInputResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoInsiderInfoListService)Services.getService(WEB3AdminAccInfoInsiderInfoListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l�������ҏ��ꗗ�̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;                
    }
        
    /**
     * (�ꗗ��ʕ\��)<BR>
     * �����ҏ��ꗗ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���������ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoListResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoInsiderInfoListResponse listScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " listScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoInsiderInfoListResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoInsiderInfoListService)Services.getService(WEB3AdminAccInfoInsiderInfoListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l�������ҏ��ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l�������ҏ��ꗗ�̈ꗗ��ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;            
    }
}
@
