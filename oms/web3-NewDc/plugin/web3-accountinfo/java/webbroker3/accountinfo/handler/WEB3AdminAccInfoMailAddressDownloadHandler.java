head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�������(WEB3AdminAccInfoMailAddressDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�������)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�������<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressDownloadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressDownloadHandler.class);
    /**
     * @@roseuid 418F3A0E038A
     */
    public WEB3AdminAccInfoMailAddressDownloadHandler() 
    {
     
    }
    /**
     * (���͉�ʕ\��)<BR>
     * ���[���A�h���X�S���_�E�����[�h���͉�ʕ\���������s���B  <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ұٱ��ڽ�S���⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E614023D
     */
    public WEB3AdminAccInfoMailAddressInquiryResponse inputScreenDisplay(WEB3AdminAccInfoMailAddressInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoMailAddressInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressInquiryResponse l_response = null;
        WEB3AdminAccInfoMailAddressDownloadService l_service = null;
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressDownloadService)Services.getService(WEB3AdminAccInfoMailAddressDownloadService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressInquiryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�_�E�����[�h��ʕ\��)<BR>
     * ���[���A�h���X�S���_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E614023D
     */
    public WEB3AdminAccInfoMailAddressDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoMailAddressDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " downloadScreenDisplay(WEB3AdminAccInfoMailAddressDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressDownloadService l_service = null;
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressDownloadService)Services.getService(WEB3AdminAccInfoMailAddressDownloadService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���[���A�h���X�S���_�E�����[�h)<BR>
     * ���[���A�h���X�S���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ұٱ��ڽ�S��̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147E614025D
     */
    public WEB3AdminAccInfoMailAddressFileDownloadResponse mailAddressDownload(WEB3AdminAccInfoMailAddressFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailAddressDownload(WEB3AdminAccInfoMailAddressFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressFileDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressDownloadService l_service = null;
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressDownloadService)Services.getService(WEB3AdminAccInfoMailAddressDownloadService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�S���޳�۰�ރT�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
