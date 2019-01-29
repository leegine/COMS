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
filename	WEB3AdminBondOrderLockStatusUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������b�N�敪�X�V�n���h��(WEB3AdminBondOrderLockStatusUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ����(���u) �V�K�쐬         
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockRequest;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderLockStatusUpdateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���Ǘ��Ғ������b�N�敪�X�V�n���h��)<BR>
 * ���Ǘ��Ғ������b�N�敪�X�V�n���h���N���X
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockStatusUpdateHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondOrderLockStatusUpdateHandler.class);
    
    /**
     * @@roseuid 44E336300109
     */
    public WEB3AdminBondOrderLockStatusUpdateHandler() 
    {
     
    }
    
    /**
     * (���Ǘ��Ғ������b�N�敪�X�V���N�G�X�g)<BR>
     * ���Ǘ��Ғ������b�N�敪�X�V�@@�\���s���B<BR>
     * <BR>
     * ���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X���擾���Aexecute()���\�b�h<BR>
     * ���R�[������B
     * @@param l_request - l_request - (���N�G�X�g)<BR>
     * ���Ǘ��Ғ�����胍�b�N���N�G�X�g
     * @@return webbroker3.bd.message.WEB3AdminBondOrderLockUnlockResponse
     * @@roseuid 44C42C4F03D1
     */
    public WEB3AdminBondOrderLockUnlockResponse updateBondOrderLockStatus(
        WEB3AdminBondOrderLockUnlockRequest l_request) 
    {

        final String STR_METHOD_NAME = 
            " updateBondOrderLockStatus(WEB3AdminBondOrderLockUnlockRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondOrderLockUnlockResponse l_response = null;
        WEB3AdminBondOrderLockStatusUpdateService l_service = null;
        try
        {
            l_service =
                (WEB3AdminBondOrderLockStatusUpdateService)Services.getService(
                    WEB3AdminBondOrderLockStatusUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondOrderLockUnlockResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���Ǘ��Ғ������b�N�敪�X�V�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3AdminBondOrderLockUnlockResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondOrderLockUnlockResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminBondOrderLockUnlockResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
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
