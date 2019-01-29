head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʌ�t����o�^�n���h��(WEB3DocumentDeliverHistoryRegistHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 ���V��@@(SRA) �V�K�쐬
*/
package webbroker3.gentrade.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistResponse;
import webbroker3.gentrade.service.delegate.WEB3DocumentDeliverHistoryRegistService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (���ʌ�t����o�^�n���h��)<BR>
 * <BR>
 * ���ʌ�t����o�^�n���h���N���X<BR>
 * @@author ���V��@@
 * @@version 1.0
 */
public class WEB3DocumentDeliverHistoryRegistHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DocumentDeliverHistoryRegistHandler.class);

	/**
	 * �R���X�g���N�^<BR> 
	 */
	public WEB3DocumentDeliverHistoryRegistHandler() 
    {
	}
    
    /**
     * (���ʌ�t����o�^)<BR>
     * <BR>
     * ���ʌ�t����o�^���������{����B<BR>
     * <BR>
     * ���ʌ�t����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�f�[�^
     * @@return WEB3DocumentDeliverHistoryRegistResponse
     */
    public WEB3DocumentDeliverHistoryRegistResponse documentDeliverHistoryRegist(WEB3DocumentDeliverHistoryRegistRequest l_request)
    {
        final String STR_METHOD_NAME = "documentDeliverHistoryRegist(WEB3DocumentDeliverHistoryRegistRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3DocumentDeliverHistoryRegistService l_service = null;
        WEB3DocumentDeliverHistoryRegistResponse l_response = null;
        
        // ���ʌ�t����o�^�T�[�r�X���擾
        try
        {
            l_service = 
                (WEB3DocumentDeliverHistoryRegistService)
                    Services.getService(WEB3DocumentDeliverHistoryRegistService.class);
        }
        catch (Exception e)
        {
            l_response = 
                (WEB3DocumentDeliverHistoryRegistResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "���ʌ�t����o�^�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                e);
            
            return l_response;
        }
        
        // ���ʌ�t����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = 
                (WEB3DocumentDeliverHistoryRegistResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = 
                (WEB3DocumentDeliverHistoryRegistResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "���ʌ�t����o�^�Ɏ��s���܂����B", 
                e);
            
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
        
    }
}
@
