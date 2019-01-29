head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�n���h��(WEB3AdminAccInfoLockAccountListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLockAccountListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�n���h��)<BR>
 * �Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�n���h��<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountListHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLockAccountListHandler.class);

    public WEB3AdminAccInfoLockAccountListHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���b�N�ڋq�o�^�⍇���ꗗ���͉�ʕ\���������s���B<BR> 
     * <BR>
     * ���b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>  
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoLockAccountSearchInputResponse inputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLockAccountSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLockAccountSearchInputResponse l_response = null;
        WEB3AdminAccInfoLockAccountListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLockAccountListService)Services.getService(WEB3AdminAccInfoLockAccountListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X �̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;    
        }
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoLockAccountSearchInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
        log.exiting(STR_METHOD_NAME);

        return l_response;                
    }
        
    /**
     * (���b�N�ڋq�o�^�⍇���ꗗ�\��)<BR>
     * ���b�N�ڋq�o�^�⍇���ꗗ�\���������s���B 
     * ���b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B  
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���������ꗗ���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * <BR>
     * @@return WEB3AdminAccInfoLockAccountSearchListResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoLockAccountSearchListResponse getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest l_request) 
    {
        final String STR_METHOD_NAME = " getLockAccountRegistList(WEB3AdminAccInfoLockAccountSearchListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLockAccountSearchListResponse l_response = null;
        WEB3AdminAccInfoLockAccountListService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLockAccountListService)Services.getService(WEB3AdminAccInfoLockAccountListService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X �̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;   
        }
        //�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminAccInfoLockAccountSearchListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLockAccountSearchListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l��񃍃b�N�ڋq�o�^�⍇���ꗗ�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
        log.exiting(STR_METHOD_NAME);

        return l_response;              
    }
}
@
