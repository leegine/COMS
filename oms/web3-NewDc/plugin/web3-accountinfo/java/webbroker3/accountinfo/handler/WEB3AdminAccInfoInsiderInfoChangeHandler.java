head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l�������ҏ��ύX�n���h��(WEB3AdminAccInfoInsiderInfoChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l�������ҏ��ύX�n���h��)<BR>
 * �Ǘ��҂��q�l�������ҏ��ύX�n���h��<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoChangeHandler.class);
        
    /**
     * @@roseuid 418F3A140000
     */
    public WEB3AdminAccInfoInsiderInfoChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �����ҏ��ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l�������ҏ��ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B64A200B7
     */
    public WEB3AdminAccInfoInsiderInfoChangeInputResponse 
        inputScreenDisplay(WEB3AdminAccInfoInsiderInfoChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoInsiderInfoChangeInputResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoInsiderInfoChangeService)Services.getService(
            WEB3AdminAccInfoInsiderInfoChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�ύX�m�F)<BR>
     * �����ҏ��ύX�m�F�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l�������ҏ��ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeConfirmResponse
     * @@roseuid 415D017B03D5
     */
    public WEB3AdminAccInfoInsiderInfoChangeConfirmResponse 
        changeConfirm(WEB3AdminAccInfoInsiderInfoChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoInsiderInfoChangeConfirmResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoInsiderInfoChangeService)Services.getService(
            WEB3AdminAccInfoInsiderInfoChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�ύX����)<BR>
     * �����ҏ��ύX�����������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l�������ҏ��ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoChangeCompleteResponse
     * @@roseuid 415D017B03D7
     */
    public WEB3AdminAccInfoInsiderInfoChangeCompleteResponse 
        changeComplete(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoInsiderInfoChangeCompleteResponse l_response = null;
        WEB3AdminAccInfoInsiderInfoChangeService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoInsiderInfoChangeService)Services.getService(
            WEB3AdminAccInfoInsiderInfoChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoInsiderInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l�������ҏ��ύX�T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
