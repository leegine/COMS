head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : ������׃n���h��(WEB3HistoryTradeDetailHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 ���q (���u) �V�K�쐬
*/

package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeDetailService;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse;

/**
 * (������׃n���h��)<BR>
 * ������׃n���h���N���X<BR>
 * @@author ���q
 * @@version 1.0
 */
public class WEB3HistoryTradeDetailHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistoryTradeDetailHandler.class);
    
    /**
     * @@roseuid 41789C4C03A9
     */
    public WEB3HistoryTradeDetailHandler() 
    {
     
    }
    
    /**
     * (get������׉��)<BR>
     * ������׉�ʕ\���������s���B<BR>
     * <BR>
     * ������׃T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ������׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse
     * @@roseuid 413D90560113
     */
    public WEB3HistoryTradeDetailResponse getTradeDetailScreen(WEB3HistoryTradeDetailRequest l_request) 
    {
        final String STR_METHOD_NAME = " getTradeDetailScreen(WEB3HistoryTradeDetailRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3HistoryTradeDetailResponse  l_response = null;
        WEB3HistoryTradeDetailService  l_service = null;

        try
        {
            l_service = (WEB3HistoryTradeDetailService) Services.getService(
                                 WEB3HistoryTradeDetailService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3HistoryTradeDetailResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������׃T�[�r�X�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3HistoryTradeDetailResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.error(l_request, "������׉�ʕ\�����s���܂����B", e);
            l_response = (WEB3HistoryTradeDetailResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
