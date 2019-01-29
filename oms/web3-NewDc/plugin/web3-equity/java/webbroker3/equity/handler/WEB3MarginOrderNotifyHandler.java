head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  (�M�p��������ʒm�n���h��)<BR>
                 :  �M�p��������ʒm�n���h���N���X
                 :  (WEB3MarginOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ������ (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginOrderNotifyRequest;
import webbroker3.equity.message.WEB3MarginOrderNotifyResponse;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������ʒm�n���h���j�B<BR>
 * <BR>
 * �M�p��������ʒm�n���h���N���X
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyHandler implements MessageHandler 
{ 
   /**
    * (���O���[�e�B���e�B)<BR>
    */
    private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginOrderNotifyHandler.class);

    
    /**
     * @@roseuid 414184C5010A
     */
    public WEB3MarginOrderNotifyHandler() 
    {
     
    }
    
    /**
     * (�����ʒm���N�G�X�g)<BR>
     * �M�p��������ʒm�������s���B<BR>
     * <BR>
     * �M�p��������ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p��������ʒm���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3MarginOrderNotifyResponse
     * @@roseuid 4057B23103AC
     */
    public WEB3MarginOrderNotifyResponse orderNotifyRequest(WEB3MarginOrderNotifyRequest l_request) 
    {
        final String STR_METHOD_NAME = "orderNotifyRequest(WEB3MarginOrderNotifyRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3MarginOrderNotifyResponse l_response = null;
        WEB3MarginOrderNotifyService l_service = null;
        try
        {
            l_service= (WEB3MarginOrderNotifyService)Services.getService(WEB3MarginOrderNotifyService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginOrderNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p��������ʒm�T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3MarginOrderNotifyResponse)l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginOrderNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p��������ʒm�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginOrderNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p��������ʒm�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
