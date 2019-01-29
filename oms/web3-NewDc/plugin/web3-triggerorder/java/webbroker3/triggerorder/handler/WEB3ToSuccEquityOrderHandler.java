head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j���������n���h��(WEB3ToSuccEquityOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/14 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j���������n���h��)<BR>
 * �i�A���j���������T�[�r�X�n���h���N���X�B
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderHandler implements MessageHandler 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderHandler.class);
    
    /**
     * @@roseuid 4348ECB602DE
     */
    public WEB3ToSuccEquityOrderHandler() 
    {
     
    }
    
    /**
     * (confirm���t����)<BR>
     * �i�A���j�����������t�����m�F�������s���B<BR>
     * <BR>
     * �u�i�A���j���������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3SuccEquityBuyConfirmResponse
     * @@roseuid 431D3CC4029F
     */
    public WEB3SuccEquityBuyConfirmResponse confirmBuyOrder(WEB3SuccEquityBuyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =" confirmBuyOrder(WEB3SuccEquityBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquityBuyConfirmResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get�i�A���j���������T�[�r�X
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =(WEB3SuccEquityBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j���������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquityBuyConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�����������t�����m�F���������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(
                l_request, 
                "�i�A���j�����������t�����m�F���������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete���t����)<BR>
     * �i�A���j�����������t���������������s���B<BR>
     * <BR>
     * �u�i�A���j���������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3SuccEquityBuyCompleteResponse
     * @@roseuid 431D3DB3007C
     */
    public WEB3SuccEquityBuyCompleteResponse completeBuyOrder(WEB3SuccEquityBuyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =" completeBuyOrder(WEB3SuccEquityBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquityBuyCompleteResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get�i�A���j���������T�[�r�X
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =(WEB3SuccEquityBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j���������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquityBuyCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�����������t�����������������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(
                l_request, 
                "�i�A���j�����������t�����������������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm���t����)<BR>
     * �i�A���j�����������t�����m�F�������s���B<BR>
     * <BR>
     * �u�i�A���j���������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3SuccEquitySellConfirmResponse
     * @@roseuid 431D3DDA006C
     */
    public WEB3SuccEquitySellConfirmResponse confirmSellOrder(WEB3SuccEquitySellConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =" confirmSellOrder(WEB3SuccEquitySellConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquitySellConfirmResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get�i�A���j���������T�[�r�X
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =(WEB3SuccEquitySellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j���������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquitySellConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�����������t�����m�F���������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquitySellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�����������t�����m�F���������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete���t����)<BR>
     * �i�A���j�����������t���������������s���B<BR>
     * <BR>
     * �u�i�A���j���������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B
     * @@return WEB3SuccEquitySellCompleteResponse
     * @@roseuid 431D3DDA007C
     */
    public WEB3SuccEquitySellCompleteResponse completeSellOrder(WEB3SuccEquitySellCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =" completeSellOrder(WEB3SuccEquitySellCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquitySellCompleteResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get�i�A���j���������T�[�r�X
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =(WEB3SuccEquitySellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j���������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquitySellCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "�i�A���j�����������t�����������������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquitySellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�i�A���j�����������t�����������������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
