head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderOfferListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :IPO�\���E�w���\���ꗗ�n���h���N���X(WEB3IpoOrderOfferListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���]��(���u) �V�K�쐬
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3IPODemandOfferRequest;
import webbroker3.ipo.message.WEB3IPODemandOfferResponse;
import webbroker3.ipo.service.delegate.WEB3IpoOrderOfferListService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�\���E�w���\���ꗗ�n���h���N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IpoOrderOfferListHandler implements MessageHandler 
{
    
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoOrderOfferListHandler.class);
    
    /**
     * @@roseuid 4112EEA800C9
     */
    public WEB3IpoOrderOfferListHandler() 
    {
     
    }
    
    /**
     * (�\���w���\���ꗗ�\��)<BR>
     * IPO�\���w���\���ꗗ�\������<BR>
     * <BR>
     * IPO�\���w���\���ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO�\���w���\�����N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3IpoOrderOfferResponse
     * @@roseuid 40DB93470395
     */
    public WEB3IPODemandOfferResponse orderOfferList(WEB3IPODemandOfferRequest l_request) 
    {
        final String STR_METHOD_NAME =
            " orderOfferList(WEB3IpoOrderOfferRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPODemandOfferResponse l_response = null;
        WEB3IpoOrderOfferListService l_service = null;
        
        //IPO�\���E�w���\���ꗗ�T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3IpoOrderOfferListService)Services.getService(
            WEB3IpoOrderOfferListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPODemandOfferResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�\���E�w���\���ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO�\���E�w���\���ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3IPODemandOfferResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPODemandOfferResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO�\���E�w���\���ꗗ�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�u�b�N�r���f�B���O�\������\��)<BR>
     * IPO�u�b�N�r���f�B���O�\������\������<BR>
     * <BR>
     * IPO�\���w���\���ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * IPO�u�b�N�r���f�B���O�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return WEB3IpoBookbuildingOrderActionResponse
     * @@roseuid 40DB934703A5
     */
    public WEB3IPOBookBuildingHistoryResponse bookbuildingAction(WEB3IPOBookBuildingHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 "bookbuildingAction(WEB3IpoBookbuildingOrderActionRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingHistoryResponse l_response = null;
        WEB3IpoOrderOfferListService l_service = null;
        
        //IPO�\���E�w���\���ꗗ�T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3IpoOrderOfferListService)Services.getService(
            WEB3IpoOrderOfferListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�\���E�w���\���ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO�\���E�w���\���ꗗ�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3IPOBookBuildingHistoryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO�\���E�w���\���ꗗ�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;

    }
}
@
