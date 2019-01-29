head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����n���h���N���X(WEB3MutualSellHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ��O�� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.mf.message.WEB3MutualSellCompleteRequest;
import webbroker3.mf.message.WEB3MutualSellCompleteResponse;
import webbroker3.mf.message.WEB3MutualSellConfirmRequest;
import webbroker3.mf.message.WEB3MutualSellConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualSellService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�����n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSellHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellHandler.class);
    
    /**
     * (confirm���)<BR>
     * �����M�����R�����s���B<BR>
     * <BR>
     * �����M�����T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSellConfirmResponse
     * @@roseuid 4055698B0270
     */
    public WEB3MutualSellConfirmResponse confirmSell(
            WEB3MutualSellConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "confirmSell(" +
            "WEB3MutualSellConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);       

        //�����M�����T�[�r�X���擾��
        WEB3MutualSellService l_service = null;
        WEB3MutualSellConfirmResponse l_response = null;
        try
        {
            l_service = (WEB3MutualSellService) 
                Services.getService(WEB3MutualSellService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3MutualSellConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����M����񏈗������s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete���)<BR>
     * �����M�����o�^���s���B<BR>
     * <BR>
     * �����M�����T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSellCompleteResponse
     * @@roseuid 405569D8001E
     */
    public WEB3MutualSellCompleteResponse completeSell(
            WEB3MutualSellCompleteRequest l_request) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = "completeSell(" +
            "WEB3MutualSellCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //�����M�����T�[�r�X���擾
        WEB3MutualSellService l_service = null;
        WEB3MutualSellCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3MutualSellService) 
                Services.getService(WEB3MutualSellService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3MutualSellCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�����M�����o�^�����s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
