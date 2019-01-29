head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�u�b�N�r���f�B���O����n���h��(WEB3IpoBookbuildingCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�u�b�N�r���f�B���O����n���h��
 *                                                                
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IpoBookbuildingCancelHandler implements MessageHandler 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingCancelHandler.class);
        
    /**
     * @@roseuid 4112EE5802CD
     */
    public WEB3IpoBookbuildingCancelHandler() 
    {
     
    }
    
    /**
     * IPO�u�b�N�r���f�B���O����m�F����<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelConfirmResponse
     * @@roseuid 40D935570362
     */
    public WEB3IPOBookBuildingCancelConfirmResponse bookbuildingCancelConfirm(
        WEB3IPOBookBuildingCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " bookbuildingCancelConfirm(WEB3IPOBookBuildingCancelConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingCancelConfirmResponse l_response = null;
        WEB3IpoBookbuildingCancelService l_service = null;

        try
        {        
            //IPO�u�b�N�r���f�B���O����T�[�r�X���擾��
            l_service =
                (WEB3IpoBookbuildingCancelService)Services.getService(
            WEB3IpoBookbuildingCancelService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_e);
        }

            
        try
        {        
            //IPO�u�b�N�r���f�B���O����T�[�r�X.execute()���\�b�h���R�[������
            l_response =
                (WEB3IPOBookBuildingCancelConfirmResponse)l_service.execute(
                    l_request);//WEB3BaseException
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O����m�F�����Ɏ��s���܂����B", l_we);
        }
    
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * IPO�u�b�N�r���f�B���O�����������<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelCompleteResponse
     * @@roseuid 40D935570372
     */
    public WEB3IPOBookBuildingCancelCompleteResponse bookbuildingCancelComplete(
        WEB3IPOBookBuildingCancelCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " bookbuildingCancelComplete(WEB3IPOBookBuildingCancelCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingCancelCompleteResponse l_response = null;
        WEB3IpoBookbuildingCancelService l_service = null;
        try
        {   
            //IPO�u�b�N�r���f�B���O����T�[�r�X���擾��
            l_service =
                (WEB3IpoBookbuildingCancelService)Services.getService(
            WEB3IpoBookbuildingCancelService.class);

        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_e);
        }
            
        try
        {   
            //IPO�u�b�N�r���f�B���O����T�[�r�X.execute()���\�b�h���R�[������
            l_response =
                (WEB3IPOBookBuildingCancelCompleteResponse)l_service.execute(
                    l_request);//WEB3BaseException
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O������������Ɏ��s���܂����B", l_we);
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
