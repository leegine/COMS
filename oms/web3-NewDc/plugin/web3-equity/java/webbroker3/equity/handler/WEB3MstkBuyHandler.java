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
filename	WEB3MstkBuyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����n���h��(WEB3MstkBuyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���]�� (���u) �V�K�쐬
                   2004/12/29 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkBuyCompleteRequest;
import webbroker3.equity.message.WEB3MstkBuyCompleteResponse;
import webbroker3.equity.message.WEB3MstkBuyConfirmRequest;
import webbroker3.equity.message.WEB3MstkBuyConfirmResponse;
import webbroker3.equity.message.WEB3MstkBuyInputRequest;
import webbroker3.equity.message.WEB3MstkBuyInputResponse;
import webbroker3.equity.service.delegate.WEB3MstkBuyService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j�������t�����n���h���j�B<BR>
 * <BR>
 * �����~�j�������t�����n���h���N���X
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3MstkBuyHandler implements MessageHandler 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBuyHandler.class);

    /**
     * 
     */
    public WEB3MstkBuyHandler() 
    {
     
    }
    
    /**
     * �iget���t���͉�ʁj�B<BR>
     * <BR>
     * �~�j�����t�������͉�ʕ\�����s���B <BR>
     * <BR>
     * �����~�j�������t�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j�������t�������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkBuyInputResponse
     */
    public WEB3MstkBuyInputResponse handleGetBuyInputScreen(WEB3MstkBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " handleGetBuyInputScreen(WEB3MstkBuyInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBuyInputResponse l_response = null;
        WEB3MstkBuyService l_service = null;
        
        //�����~�j�������t�����T�[�r�X���擾
        try
        {
            l_service = (WEB3MstkBuyService)Services.getService(WEB3MstkBuyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����~�j�������t�����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3MstkBuyInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkBuyInputResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request,
				"�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B",
				l_ex);
			return l_response; 

		}
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
    
    /**
     * �iconfirm�����j�B<BR>
     * <BR>
     * �~�j�����t���������R�����s���B <BR>
     * <BR>
     * �����~�j�������t�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j�������t�����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkBuyConfirmResponse
     */
    public WEB3MstkBuyConfirmResponse handleConfirmOrder(WEB3MstkBuyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " handleConfirmOrder(WEB3MstkBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBuyConfirmResponse l_response = null;
        WEB3MstkBuyService l_service = null;
        
        //�����~�j�������t�����T�[�r�X���擾
        try
        {
            l_service = (WEB3MstkBuyService)Services.getService(WEB3MstkBuyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����~�j�������t�����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3MstkBuyConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkBuyConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
        catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkBuyConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B",
				l_ex);
			return l_response; 

		}
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �icomplete�����j�B<BR>
     * <BR>
     * �~�j�����t�����o�^���s���B <BR>
     * <BR>
     * �����~�j�������t�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j�������t�����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkBuyCompleteResponse
     */
    public WEB3MstkBuyCompleteResponse handleCompleteOrder(WEB3MstkBuyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " handleCompleteOrder(WEB3MstkBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBuyCompleteResponse l_response = null;
        WEB3MstkBuyService l_service = null;
        
        //�����~�j�������t�����T�[�r�X���擾
        try
        {
            l_service = (WEB3MstkBuyService)Services.getService(WEB3MstkBuyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MstkBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����~�j�������t�����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3MstkBuyCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkBuyCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkBuyCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B",
				l_ex);
			return l_response; 

		}
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
