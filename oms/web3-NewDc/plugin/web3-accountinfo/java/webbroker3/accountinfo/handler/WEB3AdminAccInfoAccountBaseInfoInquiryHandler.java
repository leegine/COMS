head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccountBaseInfoInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ڋq��{���⍇���n���h��(WEB3AdminAccInfoAccountBaseInfoInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccountBaseInfoInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���ڋq��{���⍇���n���h��)<BR>
 * �Ǘ��҂��q�l���ڋq��{���⍇���n���h��<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminAccInfoAccountBaseInfoInquiryHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccountBaseInfoInquiryHandler.class);
    /**
     * @@roseuid 418F3A0F0251
     */
    public WEB3AdminAccInfoAccountBaseInfoInquiryHandler() 
    {
     
    }
    
    /**
     * (�ڋq��{���⍇��)<BR>
     * �ڋq��{���⍇���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���ڋq��{���⍇�����N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse
     * @@roseuid 4163B2620264
     */
    public WEB3AdminAccInfoAccountBaseInfoResultResponse accountBaseInfoInquiry(
        WEB3AdminAccInfoAccountBaseInfoResultRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoAccountBaseInfoResultResponse l_response = null;
        WEB3AdminAccInfoAccountBaseInfoInquiryService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoAccountBaseInfoInquiryService)Services.getService(
                    WEB3AdminAccInfoAccountBaseInfoInquiryService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoResultResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoResultResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoResultResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �ڋq��{���⍇�����͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���ڋq��{���⍇�����N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse
     * @@roseuid 416CBD5502F9
     */
    public WEB3AdminAccInfoAccountBaseInfoInquiryResponse inputScreenDisplay(
        WEB3AdminAccInfoAccountBaseInfoInquiryRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoAccountBaseInfoInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoAccountBaseInfoInquiryResponse l_response = null;
        WEB3AdminAccInfoAccountBaseInfoInquiryService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoAccountBaseInfoInquiryService)Services.getService(
                    WEB3AdminAccInfoAccountBaseInfoInquiryService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoInquiryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l���ڋq��{���⍇���T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
