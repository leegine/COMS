head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCloseMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�M�p��������ԍϓ��̓n���h���N���X)
                 : (WEB3MarginChangeCloseMarginInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/28 li-songfeng (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputResponse;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p��������ԍϓ��̓n���h���j�B<BR>
 * <BR>
 * �M�p��������ԍϓ��̓n���h���N���X
 * @@version�@@1.0
 */
public class WEB3MarginChangeCloseMarginInputHandler implements MessageHandler 
{
   /**
    * (���O���[�e�B���e�B)<BR>
    */
   private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginInputHandler.class);
 
    /**
     * @@roseuid 414184C6017A
     */
    public WEB3MarginChangeCloseMarginInputHandler() 
    {
     
    }
    
    /**
     * (get�����ԍϓ��͉��)<BR>
     * �M�p��������ԍς̓��͉�ʕ\���������s���B<BR>
     * <BR>
     * �M�p��������ԍϓ��̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginCloseMarginChangeInputResponse
     * @@roseuid 407CB2050163
     */
    public WEB3MarginCloseMarginChangeInputResponse getCloseMarginChangeInputScreen(WEB3MarginCloseMarginChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getCloseMarginChangeInputScreen(WEB3MarginCloseMarginChangeInputRequest)";
        
        log.entering(STR_METHOD_NAME);
        WEB3MarginChangeCloseMarginInputService l_service=null;
        WEB3MarginCloseMarginChangeInputResponse l_response=null;
        try
        {
            l_service = (WEB3MarginChangeCloseMarginInputService)Services.getService(WEB3MarginChangeCloseMarginInputService.class);
        }
        catch (Exception l_ex)
        {
 
            l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "1�M�p��������ԍϓ��̓T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3MarginCloseMarginChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "2�M�p��������ԍϓ��͂Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginCloseMarginChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "2�M�p��������ԍϓ��͂Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
     log.exiting(STR_METHOD_NAME);
     return l_response;
    }
}
@
