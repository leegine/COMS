head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�V�K�\���n���h��(WEB3MutualFixedBuyApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 �юu�� (���u) �V�K�쐬                      
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�莞��z���t�V�K�\���n���h���N���X
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3MutualFixedBuyApplyHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyHandler.class);

    /**
     * (�莞��z���t�V�K�\������)<BR>
     * ���M�莞��z���t�V�K�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3MutualFixedBuyApplyInputResponse
     */
    public WEB3MutualFixedBuyApplyInputResponse fixedBuyApplyInput(
        WEB3MutualFixedBuyApplyInputRequest l_request)
    {
        final String l_strMethodName = 
            "fixedBuyApplyInput(WEB3MutualFixedBuyApplyInputRequest)";
        log.entering(l_strMethodName);
        
        WEB3MutualFixedBuyApplyService l_service;
        WEB3MutualFixedBuyApplyInputResponse l_response;
        try
        {
            //���M�莞��z���t�V�K�\���T�[�r�X���擾
            l_service = 
                (WEB3MutualFixedBuyApplyService)Services.getService(
                    WEB3MutualFixedBuyApplyService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(
                l_request,
                "���M�莞��z���t�V�K�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MutualFixedBuyApplyInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�莞��z���t�V�K�\�����͂Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);
        return l_response;
    }
    
    /**
     * (�莞��z���t�V�K�\���R��)<BR>
     * ���M�莞��z���t�V�K�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3MutualFixedBuyApplyConfirmResponse
     */
    public WEB3MutualFixedBuyApplyConfirmResponse fixedBuyApplyValidate(
        WEB3MutualFixedBuyApplyConfirmRequest l_request)
    {
        final String l_strMethodName = 
            "fixedBuyApplyValidate(WEB3MutualFixedBuyApplyConfirmRequest)";
        log.entering(l_strMethodName);
        
        WEB3MutualFixedBuyApplyService l_service;
        WEB3MutualFixedBuyApplyConfirmResponse l_response;
        try
        {
            //���M�莞��z���t�V�K�\���T�[�r�X���擾
            l_service = 
                (WEB3MutualFixedBuyApplyService)Services.getService(
                    WEB3MutualFixedBuyApplyService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(
                l_request,
                "���M�莞��z���t�V�K�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MutualFixedBuyApplyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualFixedBuyApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            
            log.error(l_request, "�莞��z���t�V�K�\���R���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);
        return l_response;
    }
}
@
