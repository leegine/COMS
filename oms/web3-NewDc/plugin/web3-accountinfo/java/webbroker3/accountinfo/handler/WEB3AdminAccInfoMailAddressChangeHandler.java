head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�n���h��(WEB3AdminAccInfoMailAddressChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                 : 2006/05/19 ���� (���u) �d�l�ύX�E���f��104
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�n���h��)<BR>
 * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�n���h��<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeHandler.class);
    /**
     * @@roseuid 418F3A0E0203
     */
    public WEB3AdminAccInfoMailAddressChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���[���A�h���X�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeInputResponse
     * @@roseuid 4166629402AB
     */
    public WEB3AdminAccInfoMailAddressChangeInputResponse inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeInputResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeService l_service = null;
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeService)Services.getService(WEB3AdminAccInfoMailAddressChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���[���A�h���X�ύX�m�F)<BR>
     * ���[���A�h���X��ύX����B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmResponse
     * @@roseuid 4147FAD8020E
     */
    public WEB3AdminAccInfoMailAddressChangeConfirmResponse mailAddressChangeConfirm(WEB3AdminAccInfoMailAddressChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeConfirm(WEB3AdminAccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeConfirmResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeService l_service = null;
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeService)Services.getService(WEB3AdminAccInfoMailAddressChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
//			<--�C���R��̈וύX***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
            l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--�C���R��̂̈וύX***2006.06.14 SCS Inomata
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���[���A�h���X�ύX����)<BR>
     * ���[���A�h���X��ύX����B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteResponse
     * @@roseuid 4147FAD8021E
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteResponse mailAddressChangeComplete(WEB3AdminAccInfoMailAddressChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeComplete(WEB3AdminAccInfoMailAddressChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeCompleteResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeService l_service = null;
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeService)Services.getService(WEB3AdminAccInfoMailAddressChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
//			<--�C���R��̈וύX***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
			l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--�C���R��̂̈וύX***2006.06.14 SCS Inomata
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃁁[���A�h���X�ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
