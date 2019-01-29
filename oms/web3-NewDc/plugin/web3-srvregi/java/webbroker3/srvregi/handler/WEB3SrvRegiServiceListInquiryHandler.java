head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceListInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�n���h��(WEB3SrvRegiServiceListInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 ���o�� �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceListInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�n���h��)<BR>
 * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�n���h���N���X<BR>
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3SrvRegiServiceListInquiryHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceListInquiryHandler.class);
    
    /**
     * @@roseuid 416F415D031C
     */
    public WEB3SrvRegiServiceListInquiryHandler() 
    {
     
    }
    
    /**
     * (search�T�[�r�X)<BR>
     * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�����s���B<BR>
     * <BR>
     * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse
     * @@roseuid 40F781E40178
     */
    public WEB3SrvRegiReferenceResponse searchService(WEB3SrvRegiReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " searchService(WEB3SrvRegiReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiReferenceResponse l_response = null;
        WEB3SrvRegiServiceListInquiryService l_service = null;
        
        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3SrvRegiServiceListInquiryService)Services.getService(
                WEB3SrvRegiServiceListInquiryService.class);    //Exception
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SrvRegiReferenceResponse)l_service.execute(
                l_request);                      //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�T�[�r�X���p�T�[�r�X�ꗗ�Ɖ�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
}
@
