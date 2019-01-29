head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�܂����ē����[���z�M�w���n���h��(WEB3AdminAccInfoMailDistributionHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailDistributionService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�܂����ē����[���z�M�w���n���h��)<BR>
 * �Ǘ��҂��q�܂����ē����[���z�M�w���n���h��<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */

public class WEB3AdminAccInfoMailDistributionHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionHandler.class);
        
    public WEB3AdminAccInfoMailDistributionHandler()
    {
        
    }
    /**
     * (�z�M�w����ʕ\��)<BR>
     * �ē����[���z�M�w����ʕ\���������s���B<BR>
     *<BR>
     *�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���A<BR>
     *execute()���\�b�h���R�[��<BR>
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w�����N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA301D8
     */
    public WEB3AdminAccInfoMailDistributionResponse getMailDistributionScreen(WEB3AdminAccInfoMailDistributionRequest l_request)
    {
        final String STR_METHOD_NAME = "getMailDistributionScreen(WEB3AccInfoMailDistributionRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate�z�M�w��)<BR>
     * �ē����[���z�M�w���m�F�������s���B<BR> 
     *<BR>
     *�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���A<BR>
     *execute()���\�b�h���R�[������B<BR>
     * @@param l_request - :�Ǘ��҂��q�l���ē����[���z�M�w���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA302D8 
     */
    public WEB3AdminAccInfoMailDistributionConfirmResponse validateMailDistribution(WEB3AdminAccInfoMailDistributionConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateMailDistribution(WEB3AccInfoMailDistributionConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionConfirmResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (submit�z�M�w��)<BR>
     *�ē����[���z�M�w�������������s���B<BR> 
     *<BR>
     *�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���A<BR>
     *execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA303D8 
     */
    public WEB3AdminAccInfoMailDistributionCompleteResponse submintMailDistribution(WEB3AdminAccInfoMailDistributionCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submintMailDistribution(WEB3AccInfoMailDistributionCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionCompleteResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate�z�M���)<BR>
     * �ē����[���z�M�w������m�F�������s���B<BR> 
     *<BR>
     *�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA304D8
     */
    public WEB3AdminAccInfoMailDistributionCancelConfirmResponse validateMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateMailDistributionCancle(WEB3AccInfoMailDistributionCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionCancelConfirmResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (submit�z�M���)<BR>
     * �ē����[���z�M�w����������������s���B<BR> 
     *<BR>
     *�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���ē����[���z�M�w������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA305D8
     */
    public WEB3AdminAccInfoMailDistributionCancelCompleteResponse submitMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitMailDistributionCancle(WEB3AccInfoMailDistributionCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionCancelCompleteResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ē����[���z�M�w���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
