head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenRegistSearchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�ݐ\�������n���h��(WEB3AdminAccOpenRegistSearchHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenRegistSearchService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�ݐ\�������n���h��)<BR>
 * �Ǘ��Ҍ����J�ݐ\�������n���h��<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminAccOpenRegistSearchHandler implements MessageHandler 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenRegistSearchHandler.class);
    
    /**
     * @@roseuid 41B45E75000F
     */
    public WEB3AdminAccOpenRegistSearchHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �����J�ݐ\��������ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐ\�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A155C70058
     */
    public WEB3AdminAccOpenApplySearchInputResponse inputScreenDisplay(WEB3AdminAccOpenApplySearchInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccOpenApplySearchInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplySearchInputResponse l_response = null;
        WEB3AdminAccOpenRegistSearchService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminAccOpenRegistSearchService)Services.getService(
                    WEB3AdminAccOpenRegistSearchService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminAccOpenApplySearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAccOpenApplySearchInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplySearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�����J�ݐ\��������ʕ\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�����J�ݐ\���_�E�����[�h)<BR>
     * �����J�ݐ\���_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �Ǘ��Ҍ����J�ݐ\�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��Ҍ����J�ݐ\���_�E�����[�h���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 41A155C70078
     */
    public WEB3AdminAccOpenApplyDownloadResponse accOpenRegistDownload(WEB3AdminAccOpenApplyDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " accOpenRegistDownload(WEB3AdminAccOpenApplyDownloadRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyDownloadResponse l_response = null;
        WEB3AdminAccOpenRegistSearchService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminAccOpenRegistSearchService)Services.getService(
                    WEB3AdminAccOpenRegistSearchService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminAccOpenApplyDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����J�ݐ\�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAccOpenApplyDownloadResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�����J�ݐ\���_�E�����[�h�t�@@�C���f�[�^�擾�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
