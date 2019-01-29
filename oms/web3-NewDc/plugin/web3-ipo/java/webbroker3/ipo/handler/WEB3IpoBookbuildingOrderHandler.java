head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O�\���n���h���N���X(WEB3IpoBookbuildingOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�u�b�N�r���f�B���O�\���n���h���N���X
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderHandler implements MessageHandler 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingOrderHandler.class);
    
    /**
     * @@roseuid 4112EE5800F6
     */
    public WEB3IpoBookbuildingOrderHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * IPO�u�b�N�r���f�B���O�\�����͉�ʕ\������<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O�\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderInputResponse
     * @@roseuid 40D28A9F019D
     */
    public WEB3IPOBookBuildingDemandInputResponse inputScreenIndication(
        WEB3IPOBookBuildingDemandInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " inputScreenIndication(WEB3IpoBookbuildingOrderInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingDemandInputResponse l_response = null;
        WEB3IpoBookbuildingOrderService l_service = null;
        
        try
        {
            //IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾��
            l_service =
                (WEB3IpoBookbuildingOrderService)Services.getService(
            WEB3IpoBookbuildingOrderService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingDemandInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_e);
        }

        try
        {
            //IPO�u�b�N�r���f�B���O�\���T�[�r�X.execute()���\�b�h���R�[������
            l_response =
                (WEB3IPOBookBuildingDemandInputResponse)l_service.execute(
                    l_request);
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingDemandInputResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O�\�����͉�ʕ\�������Ɏ��s���܂����B", l_we);
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * IPO�u�b�N�r���f�B���O�\���m�F����<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O�\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderConfirmResponse
     * @@roseuid 40D28A9F0101
     */
    public WEB3IPOBookBuildingDemandConfirmResponse bookbuildingOrderConfirm(
        WEB3IPOBookBuildingDemandConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " bookbuildingOrderConfirm(WEB3IpoBookbuildingOrderConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingDemandConfirmResponse l_response = null;
        WEB3IpoBookbuildingOrderService l_service = null;

        try
        {        
            //IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾��
            l_service =
                (WEB3IpoBookbuildingOrderService)Services.getService(
            WEB3IpoBookbuildingOrderService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingDemandConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_e);
        }

        try
        {        
            //IPO�u�b�N�r���f�B���O�\���T�[�r�X.execute()���\�b�h���R�[������
            l_response =
                (WEB3IPOBookBuildingDemandConfirmResponse)l_service.execute(
                    l_request);
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingDemandConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O�\���m�F�����Ɏ��s���܂����B", l_we);
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * IPO�u�b�N�r���f�B���O�\����������<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderCompleteResponse
     * @@roseuid 40D28A9F013F
     */
    public WEB3IPOBookBuildingDemandCompleteResponse bookbuildingOrderComplete(
        WEB3IPOBookBuildingDemandCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " bookbuildingOrderComplete(WEB3IpoBookbuildingOrderCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingDemandCompleteResponse l_response = null;
        WEB3IpoBookbuildingOrderService l_service = null;
        
        try
        {
            //IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾��
            l_service =
                (WEB3IpoBookbuildingOrderService)Services.getService(
            WEB3IpoBookbuildingOrderService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingDemandCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_e);
        }

        try
        {
            //IPO�u�b�N�r���f�B���O�\���T�[�r�X.execute()���\�b�h���R�[������
            l_response =
                (WEB3IPOBookBuildingDemandCompleteResponse)l_service.execute(
                    l_request);
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingDemandCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O�\�����������Ɏ��s���܂����B", l_we);
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
