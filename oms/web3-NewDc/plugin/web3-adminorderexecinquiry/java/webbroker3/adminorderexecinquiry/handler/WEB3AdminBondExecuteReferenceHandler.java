head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ�n���h��(WEB3AdminBondExecuteReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/10 �����q(���u) �V�K�쐬  
*/

package webbroker3.adminorderexecinquiry.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminBondExecuteReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���Ǘ��Ғ������Ɖ�n���h��)<BR>
 * ���Ǘ��Ғ������Ɖ�n���h���N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminBondExecuteReferenceHandler implements MessageHandler
{
    
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteReferenceHandler.class);
    
    /**
     * (���Ǘ��Ғ������Ɖ�����\�����N�G�X�g)<BR>
     * ���Ǘ��Ғ������Ɖ�����\�����s���B<BR>
     * <BR>
     * ���Ǘ��Ғ������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h
     * ���R�[������B
     * @@param l_request - (���N�G�X�g)<BR>
     * ���Ǘ��Ғ������Ɖ�����\�����N�G�X�g
     * @@return 
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse 
     * @@roseuid 44B74C6001D0
     */
    public WEB3AdminORBondExecRefReferenceResponse getReferenceScreen
        (WEB3AdminORBondExecRefReferenceRequest l_request)
    {
        String STR_METHOD_NAME = 
            "getReferenceScreen(WEB3AdminORBondExecReferrenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecuteReferenceService l_service = null;
        WEB3AdminORBondExecRefReferenceResponse l_response = null;
        
        try
        {
            //���Ǘ��Ғ������Ɖ�T�[�r�X���擾��
            l_service = 
                (WEB3AdminBondExecuteReferenceService)Services.getService(
                        WEB3AdminBondExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminORBondExecRefReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__���Ǘ��Ғ������Ɖ�T�[�r�X���擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminORBondExecRefReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminORBondExecRefReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (���Ǘ��Ғ������Ɖ�T�[�r�X.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
        
    }
    
    /**
     * (���Ǘ��Ғ������Ɖ���\�����N�G�X�g)<BR>
     * ���Ǘ��Ғ������Ɖ���\�����s���B<BR>
     * <BR>
     * ���Ǘ��Ғ������Ɖ�T�[�r�X���擾���Aexecute()���\�b�h
     * ���R�[������B
     * @@param l_request - (���N�G�X�g)<BR>
     * ���Ǘ��Ғ������Ɖ���\�����N�G�X�g
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputResponse
     * @@roseuid 44B74C74000B
     */
    public WEB3AdminORBondExecRefInputResponse getInputScreen
        (WEB3AdminORBondExecRefInputRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminORBondExecRefInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminBondExecuteReferenceService l_service = null;
        WEB3AdminORBondExecRefInputResponse l_response = null;
        
        try
        {
            //���Ǘ��Ғ������Ɖ�T�[�r�X���擾��
            l_service = 
                (WEB3AdminBondExecuteReferenceService)Services.getService(
                        WEB3AdminBondExecuteReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminORBondExecRefInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__���Ǘ��Ғ������Ɖ�T�[�r�X���擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminORBondExecRefInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminORBondExecRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (���Ǘ��Ғ������Ɖ�T�[�r�X.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;     
    }
}
@
