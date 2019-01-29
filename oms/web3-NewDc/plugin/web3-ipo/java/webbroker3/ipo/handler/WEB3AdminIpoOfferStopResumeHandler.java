head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoOfferStopResumeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO��W��~�ĊJ�n���h���N���X(WEB3AdminIpoOfferStopResumeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/24 ���C�g (���u) �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoOfferStopResumeService;
import webbroker3.util.WEB3LogUtility;

/**
 * �Ǘ���IPO��W��~�ĊJ�n���h���N���X
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoOfferStopResumeHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoOfferStopResumeHandler.class);
    
    /**
     * @@roseuid 4112EE57004A
     */
    public WEB3AdminIpoOfferStopResumeHandler() 
    {
     
    }
    
    /**
     * (��W��~�ĊJ�m�F)<BR>
     * �Ǘ���IPO��W��~�ĊJ�m�F����<BR>
     * <BR>
     * �Ǘ���IPO��W��~�ĊJ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO��W��~�ĊJ�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeConfirmResponse
     * @@roseuid 40D015D0003C
     */
    public WEB3AdminIPOOfferStopResumeConfirmResponse offerStopResumeConfirm(WEB3AdminIPOOfferStopResumeConfirmRequest l_request) 
    {

        final String STR_METHOD_NAME = " offerStopResumeConfirm(WEB3AdminIpoOfferStopResumeConfirmRequest)";   
        log.entering(STR_METHOD_NAME);
                
        WEB3AdminIPOOfferStopResumeConfirmResponse l_response = null;
        WEB3AdminIpoOfferStopResumeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoOfferStopResumeService)Services.getService(WEB3AdminIpoOfferStopResumeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO��W��~�ĊJ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
            
        //�Ǘ���IPO��W��~�ĊJ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
                
            l_response = (WEB3AdminIPOOfferStopResumeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ���IPO��W��~�ĊJ�Ɏ��s���܂����B", l_ex);
            return l_response;
                
        }
        log.exiting(STR_METHOD_NAME);
            
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
    
    /**
     * (��W��~�ĊJ����)<BR>
     * �Ǘ���IPO��W��~�ĊJ��������<BR>
     * <BR>
     * �Ǘ���IPO��W��~�ĊJ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO��W��~�ĊJ�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIpoOfferStopResumeCompleteResponse
     * @@roseuid 40D01614023F
     */
    public WEB3AdminIPOOfferStopResumeCompleteResponse offerStopResumeComplete(WEB3AdminIPOOfferStopResumeCompleteRequest l_request) 
    {

        final String STR_METHOD_NAME = " offerStopResumeComplete(WEB3AdminIpoOfferStopResumeCompleteRequest)";   
        log.entering(STR_METHOD_NAME);
                
        WEB3AdminIPOOfferStopResumeCompleteResponse l_response = null;
        WEB3AdminIpoOfferStopResumeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoOfferStopResumeService)Services.getService(WEB3AdminIpoOfferStopResumeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO��W��~�ĊJ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
            
        //�Ǘ���IPO��W��~�ĊJ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
                
            l_response = (WEB3AdminIPOOfferStopResumeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ���IPO��W��~�ĊJ�Ɏ��s���܂����B", l_ex);
            return l_response;
                
        }
        log.exiting(STR_METHOD_NAME);
            
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
}
@
