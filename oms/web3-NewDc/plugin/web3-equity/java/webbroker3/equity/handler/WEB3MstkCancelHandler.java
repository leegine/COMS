head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j������������n���h��(WEB3MstkCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 �J�N���V (���u) �V�K�쐬
                   2004/12/29 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkCancelCompleteRequest;
import webbroker3.equity.message.WEB3MstkCancelCompleteResponse;
import webbroker3.equity.message.WEB3MstkCancelConfirmRequest;
import webbroker3.equity.message.WEB3MstkCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MstkCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j������������n���h���j�B<BR>
 * <BR>
 * �����~�j������������n���h���N���X
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3MstkCancelHandler implements MessageHandler 
{
    
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelHandler.class);
        
    /**
     * 
     */
    public WEB3MstkCancelHandler() 
    {
     
    }
    
    /**
     * �iconfirm�����j�B<BR>
     * <BR>
     * �~�j��������������R�����s���B <BR>
     * <BR>
     * �����~�j������������T�[�r�X���擾���Aexecute()<BR>
     * ���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j������������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkCancelConfirmResponse
     */
    public WEB3MstkCancelConfirmResponse handleConfirmOrder(WEB3MstkCancelConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = "handleConfirmOrder(WEB3MstkCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkCancelConfirmResponse l_response = null;
        WEB3MstkCancelService l_service = null;
        
        // �����~�j������������T�[�r�X���擾
        try
        {  
                      
            l_service=(WEB3MstkCancelService)Services.getService(WEB3MstkCancelService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j������������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �����~�j������������T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3MstkCancelConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����~�j������������T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
            
			l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"�����~�j������������T�[�r�X�Ɏ��s���܂����B",
				l_ex);
			return l_response;                    
            
		}
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * �icomplete�����j�B<BR>
     * <BR>
     * �~�j����������o�^���s���B<BR>
     * <BR>
     * �����~�j������������T�[�r�X���擾���Aexecute()<BR>
     * ���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j������������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkCancelCompleteResponse
     */
    public WEB3MstkCancelCompleteResponse handleCompleteOrder(WEB3MstkCancelCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = "handleCompleteOrder(WEB3MstkCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
             
        WEB3MstkCancelCompleteResponse l_response = null;
        WEB3MstkCancelService l_service = null;
        
        // �����~�j������������T�[�r�X���擾   
        try
        {
            
            l_service=(WEB3MstkCancelService)Services.getService(WEB3MstkCancelService.class);
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j������������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //�����~�j������������T�[�r�X���擾���Aexecute()���\�b�h���R�[������       
        try
        {
            
            l_response = (WEB3MstkCancelCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����~�j������������T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
                    
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
            
			l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"�����~�j������������T�[�r�X�Ɏ��s���܂����B",
				l_ex);
			return l_response;
                    
		}
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
