head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񃁁[���A�h���X�ύX�n���h��(WEB3AccInfoMailAddressChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                 : 2006/05/19 ���� (���u) �d�l�ύX�E���f��104
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailAddressChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l��񃁁[���A�h���X�ύX�n���h��)<BR>
 * ���q�l��񃁁[���A�h���X�ύX�n���h��<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeHandler implements MessageHandler 
{                   
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressChangeHandler.class);
    /**
     * @@roseuid 418F3A0B003E
     */
    public WEB3AccInfoMailAddressChangeHandler() 
    {
     
    }
    
    /**
     * (���[���A�h���X�ύX�m�F)<BR>
     * ���[���A�h���X��ύX����B<BR>
     * <BR>
     * ���q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l��񃁁[���A�h���X�ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse
     * @@roseuid 413D3D510326
     */
    public WEB3AccInfoMailAddressChangeConfirmResponse mailAddressChangeConfirm(WEB3AccInfoMailAddressChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeConfirm(WEB3AccInfoMailAddressChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMailAddressChangeConfirmResponse l_response = null;
        WEB3AccInfoMailAddressChangeService l_service = null;
        
        //���q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AccInfoMailAddressChangeService)Services.getService(WEB3AccInfoMailAddressChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "���q�l��񃁁[���A�h���X�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            
            return l_response;      
        }
        
        //���q�l��񃁁[���A�h���X�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AccInfoMailAddressChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeConfirmResponse)l_request.createResponse();
//			<--�C���R��̈וύX***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
			l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--�C���R��̂̈וύX***2006.06.14 SCS Inomata
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񃁁[���A�h���X�ύX�T�[�r�X�Ɏ��s���܂����B",
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
     * ���q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l��񃁁[���A�h���X�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse
     * @@roseuid 413D3D510335
     */
    public WEB3AccInfoMailAddressChangeCompleteResponse mailAddressChangeComplete(WEB3AccInfoMailAddressChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailAddressChangeComplete(WEB3AccInfoMailAddressChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMailAddressChangeCompleteResponse l_response = null;
        WEB3AccInfoMailAddressChangeService l_service = null;
        
        //���q�l��񃁁[���A�h���X�ύX�T�[�r�X���擾
        try
        {
            l_service = (WEB3AccInfoMailAddressChangeService)Services.getService(WEB3AccInfoMailAddressChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorMessage = l_ex.getMessage();
            log.error(
                l_request,
                "���q�l��񃁁[���A�h���X�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //���q�l��񃁁[���A�h���X�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AccInfoMailAddressChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoMailAddressChangeCompleteResponse)l_request.createResponse();
//			<--�C���R��̈וύX***2006.06.14 SCS Inomata
//			l_response.errorMessage = l_ex.getMessage() ;
			l_response.errorMessage = l_ex.getErrorMessage() ;
//			<--�C���R��̂̈וύX***2006.06.14 SCS Inomata
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񃁁[���A�h���X�ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
