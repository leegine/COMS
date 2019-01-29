head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoManagementDetailsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����Ǘ��E�ڍ׃n���h��(WEB3AdminIpoManagementDetailsHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/19 ���o�� �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOManagementRequest;
import webbroker3.ipo.message.WEB3AdminIPOManagementResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoManagementDetailsService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO�����Ǘ��E�ڍ׃n���h��)<BR>
 * �Ǘ���IPO�����Ǘ��E�ڍ׃n���h���N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIpoManagementDetailsHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoManagementDetailsHandler.class);
    
    /**
     * @@roseuid 4112EE5700FE
     */
    public WEB3AdminIpoManagementDetailsHandler() 
    {
     
    }
    
    /**
     * (�����Ǘ�)<BR>
     * �Ǘ���IPO�����Ǘ�����<BR>
     * <BR>
     * �Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@roseuid 40C66718028D
     */
    public WEB3AdminIPOManagementResponse productManagement(WEB3AdminIPOManagementRequest l_request) 
    {
        final String STR_METHOD_NAME = " productManagement(WEB3AdminIPOManagementRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOManagementResponse l_response = null;
        WEB3AdminIpoManagementDetailsService l_service = null;
        
        //�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminIpoManagementDetailsService)Services.getService(
                    WEB3AdminIpoManagementDetailsService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOManagementResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIPOManagementResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOManagementResponse)l_request.createResponse();
//            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�����ڍו\��)<BR>
     * �Ǘ���IPO�����ڍ׏���<BR>
     * <BR>
     * �Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@roseuid 40C6672A00C8
     */
    public WEB3AdminIPOProductDetailsResponse productDetailsIndication(WEB3AdminIPOProductDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME = " productDetailsIndication(WEB3AdminIPOProductDetailsRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductDetailsResponse l_response = null;
        WEB3AdminIpoManagementDetailsService l_service = null;
        
        //�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminIpoManagementDetailsService)Services.getService(
                    WEB3AdminIpoManagementDetailsService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIPOProductDetailsResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDetailsResponse)l_request.createResponse();
//            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ���IPO�����Ǘ��E�ڍ׃T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
     
    }
}
@
