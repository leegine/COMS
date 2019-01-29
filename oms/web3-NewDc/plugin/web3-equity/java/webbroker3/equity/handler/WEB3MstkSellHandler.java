head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�n���h���N���X(WEB3MstkSellHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���C�g (���u) �V�K�쐬
                   2004/12/29 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkSellCompleteRequest;
import webbroker3.equity.message.WEB3MstkSellCompleteResponse;
import webbroker3.equity.message.WEB3MstkSellConfirmRequest;
import webbroker3.equity.message.WEB3MstkSellConfirmResponse;
import webbroker3.equity.message.WEB3MstkSellInputRequest;
import webbroker3.equity.message.WEB3MstkSellInputResponse;
import webbroker3.equity.message.WEB3MstkSellListRequest;
import webbroker3.equity.message.WEB3MstkSellListResponse;
import webbroker3.equity.service.delegate.WEB3MstkSellService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j�������t�����n���h���j�B
 * <BR>
 * �����~�j�������t�n���h���N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellHandler implements MessageHandler 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellHandler.class);
    
    /**
     * 
     */
    public WEB3MstkSellHandler() 
    {
     
    }
    
    /**
     * �iget���t�ꗗ�j�B<BR>
     * <BR>
     * �����~�j�������t�ꗗ��ʕ\�����������{����B <BR>
     * <BR>
     * �����~�j�������t�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j�������t�ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkSellListResponse
     */
    public WEB3MstkSellListResponse handleGetSellList(WEB3MstkSellListRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " handleGetSellList(WEB3MstkSellListRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellListResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����~�j�������t�����T�[�r�X�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3MstkSellListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellListResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
    
    /**
     * �iget���t���͉�ʁj�B<BR>
     * <BR>
     * �~�j�����t�������͉�ʕ\�����s���B<BR>
     * <BR>
     * �����~�j�������t�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request (���N�G�X�g�f�[�^)<BR>
     * �@@�@@�@@�����~�j�������t�������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3MstkSellInputResponse
     */
    public WEB3MstkSellInputResponse handleGetSellInputScreen(WEB3MstkSellInputRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " handleGetSellInputScreen(WEB3MstkSellInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellInputResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����~�j�������t�����T�[�r�X�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3MstkSellInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
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
     * @@return WEB3MstkSellConfirmResponse
     */
    public WEB3MstkSellConfirmResponse handleConfirmOrder(WEB3MstkSellConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " handleConfirmOrder(WEB3MstkSellConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellConfirmResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����~�j�������t�����T�[�r�X�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3MstkSellConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
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
     * @@return WEB3MstkSellCompleteResponse
     */
    public WEB3MstkSellCompleteResponse handleCompleteOrder(WEB3MstkSellCompleteRequest l_request) 
    {

        final String STR_METHOD_NAME = " handleCompleteOrder(WEB3MstkSellCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellCompleteResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�������t�����T�[�r�X�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�����~�j�������t�����T�[�r�X�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3MstkSellCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "�����~�j�������t�����T�[�r�X�Ɏ��s���܂����B", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
}
@
