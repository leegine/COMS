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
filename	WEB3MarginSwapMarginAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�M�p����������n��t�n���h���N���X(WEB3MarginSwapMarginAcceptHandler.java)
Author Name      : 2004/10/8 Ḗ@@��(���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginSwapMarginAcceptRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginAcceptResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptService;
import webbroker3.util.WEB3LogUtility;
/**
 * �i�M�p����������n��t�n���h���j�B<BR>
 * <BR>
 * �M�p����������n��t�n���h���N���X
 *  @@author �@@��
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptHandler implements MessageHandler 
{
    

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginContractReferenceHandler.class);

    /**
     * @@roseuid 414184C400F5
     */
    public WEB3MarginSwapMarginAcceptHandler() 
    {
     
    }
    
    /**
     * (�������n��t���N�G�X�g)<BR>
     * �M�p����������n��t���������{����B<BR>
     * <BR>
     * �M�p����������n��t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �M�p����������n��t���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.margin.message.WEB3MarginSwapMarginAcceptResponse
     * @@roseuid 4057AECB00DD
     */
    public WEB3MarginSwapMarginAcceptResponse swapMarginAcceptRequest(WEB3MarginSwapMarginAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = "swapMarginAcceptRequest(WEB3MarginSwapMarginAcceptRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);            
        }               
        WEB3MarginSwapMarginAcceptService l_service = null;
        WEB3MarginSwapMarginAcceptResponse  l_response = null;
        try
        {
            l_service = (WEB3MarginSwapMarginAcceptService) Services.getService(WEB3MarginSwapMarginAcceptService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p����������n��t�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p����������n��t�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginSwapMarginAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����������n��t�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
