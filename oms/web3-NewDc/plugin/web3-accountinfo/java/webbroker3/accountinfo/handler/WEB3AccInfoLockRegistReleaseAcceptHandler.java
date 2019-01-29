head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockRegistReleaseAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���b�N�o�^������t�n���h��(WEB3AccInfoLockRegistRelaxationAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;


import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoLockRegistReleaseAcceptRequest;
import webbroker3.accountinfo.message.WEB3AccInfoLockRegistReleaseAcceptResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���b�N�o�^������t�n���h��)<BR>
 * ���b�N�o�^������t�n���h��<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockRegistReleaseAcceptHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockRegistReleaseAcceptHandler.class);

    public WEB3AccInfoLockRegistReleaseAcceptHandler() 
    {
     
    }
    
    /**
     * (���b�N�o�^������t)<BR>
     * ���b�N�o�^������t���������{����B <BR>
     * <BR>
     * ���b�N�o�^������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR> 
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 416B745B027C<BR>
     * @@param l_request<BR>
     * @@return WEB3AccInfoLockRegistReleaseAcceptResponse<BR>
     */
    public WEB3AccInfoLockRegistReleaseAcceptResponse lockRegistReleaseAccept(WEB3AccInfoLockRegistReleaseAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = " lockRegistReleaseAccept(WEB3AccInfoLockRegistReleaseAcceptRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoLockRegistReleaseAcceptResponse l_response = null;
        WEB3AccInfoLockRegistReleaseAcceptService l_service = null;

        try
        {
            l_service = (WEB3AccInfoLockRegistReleaseAcceptService)Services.getService(WEB3AccInfoLockRegistReleaseAcceptService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoLockRegistReleaseAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���b�N�o�^������t�T�[�r�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        //���b�N�o�^������t�T�[�r.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AccInfoLockRegistReleaseAcceptResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoLockRegistReleaseAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���b�N�o�^������t�T�[�r�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
        log.exiting(STR_METHOD_NAME);

        return l_response;                
    }
}
@
