head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueRecoveryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������SEND�L���[���J�o���n���h��(WEB3AdminFeqSendQueueRecoveryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ����� (���u) �V�K�쐬
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqSendQueueRecoveryCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueRecoveryCompleteResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueRecoveryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO������SEND�L���[���J�o���n���h��)<BR>
 * �Ǘ��ҊO������SEND�L���[���J�o���n���h���N���X<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3AdminFeqSendQueueRecoveryHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueRecoveryHandler.class);
    
    /**
     * @@roseuid 42D0DA180119
     */
    public WEB3AdminFeqSendQueueRecoveryHandler() 
    {
     
    }
    
    /**
     * (submit�X�V)<BR>
     * �X�V�����B<BR>
     * <BR>
     * �Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFeqSendQueueRecoveryCompleteResponse
     * @@roseuid 4214980A032E
     */
    public WEB3AdminFeqSendQueueRecoveryCompleteResponse submitUpdate(
        WEB3AdminFeqSendQueueRecoveryCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = 
            " submitUpdate(WEB3AdminFeqSendQueueRecoveryCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqSendQueueRecoveryCompleteResponse l_response = null;
        WEB3AdminFeqSendQueueRecoveryService l_service = null;

        try
        {            
            //�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X
            l_service = (WEB3AdminFeqSendQueueRecoveryService)
                Services.getService(WEB3AdminFeqSendQueueRecoveryService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqSendQueueRecoveryCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueRecoveryCompleteResponse)
                l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "submit�X�V�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueRecoveryCompleteResponse) 
                l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "submit�X�V�Ɏ��s���܂����B",
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
