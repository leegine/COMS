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
filename	WEB3MutualTradeOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����������ʒm�n���h���N���X(WEB3MutualTradeOrderNotifyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/24 ����� (���u) ���r���[
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyRequest;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyResponse;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M�����������ʒm�n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualTradeOrderNotifyHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualTradeOrderNotifyHandler.class);    

    /**
     * (���������ʒm���N�G�X�g)<BR>
     * �����M�����������ʒm���������{����B<BR>
     * <BR>
     * �����M�����������ʒm�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.mf.message.WEB3MutualTradeOrderNotifyResponse
     * @@roseuid 40567E0801DE
     */
    public WEB3MutualTradeOrderNotifyResponse tradeOrderNotifyRequest(
        WEB3MutualTradeOrderNotifyRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "tradeOrderNotifyRequest(" +
                "WEB3MutualTradeOrderNotifyRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�����M�����������ʒm�T�[�r�X�C���^�[�t�F�C�X
        WEB3MutualTradeOrderNotifyService l_service = null;          
        
        //�����M�����������ʒm���X�|���X�N���X
        WEB3MutualTradeOrderNotifyResponse l_response = null;     
        try
        {
            l_service = 
                (WEB3MutualTradeOrderNotifyService)Services.getService(
                    WEB3MutualTradeOrderNotifyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
             //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3MutualTradeOrderNotifyResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M�����������ʒm�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualTradeOrderNotifyResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualTradeOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�����M�����������ʒm�T�[�r�X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
