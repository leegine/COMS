head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ꗗ�n���h��(WEB3HistoryTradeHistoryListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28 �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeHistoryListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (��������ꗗ�n���h��)<BR>
 * ��������ꗗ�n���h���N���X<BR>
 * 
 * @@author �Ɍ��t(���u)
 * @@version 1.00
 */
public class WEB3HistoryTradeHistoryListHandler implements MessageHandler 
{
    /**
     *���O�o�̓��[�e�B���e�B�B
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistoryTradeHistoryListHandler.class);
    
    /**
     * @@roseuid 41789C4D00AB
     */
    public WEB3HistoryTradeHistoryListHandler() 
    {
     
    }
    
    /**
     * (get��������ꗗ���)<BR>
     * ��������ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * ��������ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��������ꗗ���N�G�X�g<BR>
     * @@return WEB3HistoryTradeHistoryListResponse
     * @@roseuid 413C2D7A03BF
     */
    public WEB3HistoryTradeHistoryListResponse getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3HistoryTradeHistoryListResponse l_response = null;
        WEB3HistoryTradeHistoryListService l_service = null;
             
        try
        {
            l_service= (WEB3HistoryTradeHistoryListService)Services.getService(WEB3HistoryTradeHistoryListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3HistoryTradeHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "��������ꗗ�T�[�r�XImpl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3HistoryTradeHistoryListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3HistoryTradeHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "��������ꗗ��ʕ\�������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }

    /**
     * (get��������ꗗ�t�@@�C���_�E�����[�h)<BR>
     * ��������ꗗ�t�@@�C���_�E�����[�h�������s���B<BR>
     * <BR>
     * ��������ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��������ꗗ�t�@@�C���_�E�����[�h���N�G�X�g<BR>
     * @@return WEB3HistoryTradeHistoryDownloadResponse
     * @@roseuid 413C2D7A03BF
     */
    public WEB3HistoryTradeHistoryDownloadResponse getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3HistoryTradeHistoryDownloadResponse l_response = null;
        WEB3HistoryTradeHistoryListService l_service = null;
             
        try
        {
            l_service= (WEB3HistoryTradeHistoryListService)Services.getService(WEB3HistoryTradeHistoryListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3HistoryTradeHistoryDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "��������ꗗ�T�[�r�XImpl�̎擾�Ɏ��s���܂���", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3HistoryTradeHistoryDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3HistoryTradeHistoryDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "��������ꗗ��ʕ\�������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        return l_response; 
    }
}
@
