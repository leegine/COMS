head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p�n���h��(WEB3BondSellHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondSellCompleteRequest;
import webbroker3.bd.message.WEB3BondSellCompleteResponse;
import webbroker3.bd.message.WEB3BondSellConfirmRequest;
import webbroker3.bd.message.WEB3BondSellConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondSellService;

/**
 * (�����p�n���h��)<BR>
 * �����p�n���h���N���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellHandler.class); 
    
    /**
     * @@roseuid 44FBFD3A02DE
     */
    public WEB3BondSellHandler() 
    {
     
    }
    
    /**
     * (�����p�m�F)<BR>
     * �����p�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3BondSellConfirmResponse
     * @@roseuid 44E93FD302D6
     */
    public WEB3BondSellConfirmResponse confirmBondSell(WEB3BondSellConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmBondSell(WEB3BondSellConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondSellService l_service = null;
        WEB3BondSellConfirmResponse l_response = null;
        
        try
        {
            //�����p�T�[�r�X���擾��
            l_service = 
                (WEB3BondSellService)Services.getService(
                    WEB3BondSellService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����p�T�[�r�X���擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response =
                (WEB3BondSellConfirmResponse)l_service.execute(l_request);           
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3BondSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����p�m�F���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�����p����)<BR>
     * �����p�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3BondSellCompleteResponse
     * @@roseuid 44E93FE1022A
     */
    public WEB3BondSellCompleteResponse completeBondSell(WEB3BondSellCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeBondSell(WEB3BondSellCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondSellService l_service = null;
        WEB3BondSellCompleteResponse l_response = null;
        
        try
        {
            //�����p�T�[�r�X���擾��
            l_service =
                (WEB3BondSellService)Services.getService(
                        WEB3BondSellService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����p�T�[�r�X���擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[��
            l_response = 
                (WEB3BondSellCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����p�������������s���܂����B",
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
