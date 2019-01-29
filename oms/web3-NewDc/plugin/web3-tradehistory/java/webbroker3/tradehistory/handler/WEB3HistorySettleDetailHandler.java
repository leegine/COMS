head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϖ��׃n���h��(WEB3HistorySettleDetailHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailRequest;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistorySettleDetailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ϖ��׃n���h��)<BR>
 * ���ϖ��׃n���h���N���X<BR>
 * 
 * @@author �Ɍ��t(���u)
 * @@version 1.00
 */
public class WEB3HistorySettleDetailHandler implements MessageHandler 
{
    
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistorySettleDetailHandler.class);
    
    /**
     * @@roseuid 41789C4C02AF
     */
    public WEB3HistorySettleDetailHandler() 
    {
     
    }
    
    /**
     * (get���ϖ��׉��)<BR>
     * ���ϖ��ו\���������s���B<BR>
     * <BR>
     * ���ϖ��׃T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���ϖ��׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse
     * @@roseuid 413EBA0B00A5
     */
    public WEB3HistorySettleDetailResponse getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3HistorySettleDetailResponse l_response = null;
        WEB3HistorySettleDetailService l_service = null;     
        try
        {
            l_service= (WEB3HistorySettleDetailService)Services.getService(WEB3HistorySettleDetailService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3HistorySettleDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "���ϖ��׃T�[�r�XImpl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3HistorySettleDetailResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3HistorySettleDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���ϖ��ו\�������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
