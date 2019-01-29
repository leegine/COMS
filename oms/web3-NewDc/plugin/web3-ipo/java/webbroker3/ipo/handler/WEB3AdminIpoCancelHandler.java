head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���~�n���h���N���X(WEB3AdminIpoCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOCancelCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOCancelCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOCancelConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOCancelConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ���IPO���~�n���h���N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoCancelHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoCancelHandler.class);
    
    /**
     * @@roseuid 4112EE560053
     */
    public WEB3AdminIpoCancelHandler() 
    {
     
    }
    
    /**
     * (IPO���~�m�F)<BR>
     * �Ǘ���IPO���~�m�F����<BR>
     * <BR>
     * �Ǘ���IPO���~�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO���~�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIpoCancelConfirmResponse
     * @@roseuid 40D01AAE02AD
     */
    public WEB3AdminIPOCancelConfirmResponse ipoCancelConfirm(WEB3AdminIPOCancelConfirmRequest l_request) 
    {
 
        final String STR_METHOD_NAME = " ipoCancelConfirm(WEB3AdminIpoCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOCancelConfirmResponse l_response = null;
        WEB3AdminIpoCancelService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoCancelService)Services.getService(WEB3AdminIpoCancelService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO���~�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        

        try
        {
            l_response = (WEB3AdminIPOCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO���~�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;      

    }
    
    /**
     * (IPO���~����)<BR>
     * �Ǘ���IPO���~��������<BR>
     * <BR>
     * �Ǘ���IPO���~�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO���~�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoCancelCompleteResponse
     * @@roseuid 40D01AAE02BC
     */
    public WEB3AdminIPOCancelCompleteResponse ipoCancelComplete(WEB3AdminIPOCancelCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " ipoCancelComplete(WEB3AdminIpoCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOCancelCompleteResponse l_response = null;
        WEB3AdminIpoCancelService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoCancelService)Services.getService(WEB3AdminIpoCancelService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO���~�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        

        try
        {
            l_response = (WEB3AdminIPOCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO���~�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
 
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response; 
         
    }
}
@
