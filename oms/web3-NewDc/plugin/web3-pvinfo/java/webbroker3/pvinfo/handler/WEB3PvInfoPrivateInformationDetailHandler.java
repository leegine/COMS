head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ��ݏڍ׃n���h��(WEB3PvInfoPrivateInformationDetailHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/23 ������(���u) �쐬
*/
package webbroker3.pvinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteRequest;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailRequest;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationDetailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (��ײ�ްĲ�̫Ұ��ݏڍ׃n���h��)<BR>
 * ��ײ�ްĲ�̫Ұ��ݏڍ׃n���h���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationDetailHandler implements MessageHandler
{
    /**
     *���O�o�̓��[�e�B���e�B�B<BR>
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationDetailHandler.class);
    
    /**
     * (get�ڍ׉��)<BR>
     * �ڍ׉�ʕ\���������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��ײ�ްĲ�̫Ұ��ݏڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse
     * @@roseuid 41479B2F0173
     */
    public WEB3PvInfoPrivateInformationDetailResponse getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest l_request)
    {
        final String STR_METHOD_NAME = " getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest)";
        log.entering(STR_METHOD_NAME);
      
        WEB3PvInfoPrivateInformationDetailResponse l_response = null;
        WEB3PvInfoPrivateInformationDetailService l_service = null;
        
        //�ڍ׉�ʕ\�������T�[�r�X���擾
        try
        {
            l_service = (WEB3PvInfoPrivateInformationDetailService)Services.getService(WEB3PvInfoPrivateInformationDetailService.class);
        }        
        //�T�[�r�X�ŗ�O�����������ꍇ��
        catch(Exception l_ex)
        {
             l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(l_request, 
                "�ڍ׉�ʕ\�������T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo, l_ex);    
             
             log.exiting(STR_METHOD_NAME);
             return l_response;                      
        }
        
        //�ڍ׉�ʕ\�������T�[�r�X.execute()
        try
        {
            l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�ڍ׉�ʕ\�������T�[�r�X.execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
                l_response.errorInfo, l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (delete�_�C���N�g�w�胁�b�Z�[�W)<BR>
     * �_�C���N�g�w�胁�b�Z�[�W�폜�������s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse
     * @@roseuid 41479BA1026D
     */
    public WEB3PvInfoDirectMessageDeleteResponse deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest l_request)
    {
        final  String STR_METHOD_NAME = "deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3PvInfoDirectMessageDeleteResponse l_response = null;
        WEB3PvInfoPrivateInformationDetailService l_service = null;
        
        //�_�C���N�g�w�胁�b�Z�[�W�폜�������擾
        try
        {
            l_service = (WEB3PvInfoPrivateInformationDetailService)Services.getService(WEB3PvInfoPrivateInformationDetailService.class);            
        }     
        //�T�[�r�X�ŗ�O�����������ꍇ��
        catch(Exception l_ex)
        {
            l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "�_�C���N�g�w�胁�b�Z�[�W�폜�����̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�_�C���N�g�w�胁�b�Z�[�W�폜����.execute()
        try
        {
            l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�_�C���N�g�w�胁�b�Z�[�W�폜����.execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
