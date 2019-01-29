head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M��������t�n���h���N���X(WEB3MutualOrderAcceptServiceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ����  (���u) �V�K�쐬
                   2004/08/24 ����� (���u) ���r���[
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualOrderAcceptRequest;
import webbroker3.mf.message.WEB3MutualOrderAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M��������t�n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3MutualOrderAcceptServiceHandler implements MessageHandler 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualOrderAcceptServiceHandler.class);
    
    /**
     * �����M��������t�������s���B<BR>
     * <BR>
     * �����M��������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualOrderAcceptResponse
     * @@roseuid 4056658B03BB
     */
    public WEB3MutualOrderAcceptResponse mutualOrderAcceptRequest(
        WEB3MutualOrderAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = "mutualOrderAcceptRequest(" +
            "WEB3MutualOrderAcceptRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�����M��������t�T�[�r�X�C���^�[�t�F�C�X
        WEB3MutualOrderAcceptService l_service = null;          
        
        //�����M��������t���X�|���X�N���X
        WEB3MutualOrderAcceptResponse l_response = null;     
        try
        {
            l_service = 
                (WEB3MutualOrderAcceptService) Services.getService(
                    WEB3MutualOrderAcceptService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
             //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3MutualOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M��������t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualOrderAcceptResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�����M��������t�T�[�r�X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
