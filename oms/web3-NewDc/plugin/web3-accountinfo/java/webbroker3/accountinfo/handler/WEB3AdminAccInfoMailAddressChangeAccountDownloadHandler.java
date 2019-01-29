head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�������(WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�������)<BR>
 * �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�������<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class);
    /**
     * @@roseuid 418F3A0E005D
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h�⍇����ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5778000E
     */
    public WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeAccountDownloadService l_service = null;
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�_�E�����[�h��ʕ\��)<BR>
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F3F300A7
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " downloadScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeAccountDownloadService l_service = null;
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (���[���A�h���X�ύX�ڋq�_�E�����[�h)<BR>
     * ���[���A�h���X�ύX�ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃁁[���A�h���X�ύX�ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F3F300A9
     */
    public WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse mailAddressChangeAccountDownload(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailAddressChangeAccountDownload(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeAccountDownloadService l_service = null;
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���Ұٱ��ڽ�ύX�ڋq�޳�۰�ރT�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
