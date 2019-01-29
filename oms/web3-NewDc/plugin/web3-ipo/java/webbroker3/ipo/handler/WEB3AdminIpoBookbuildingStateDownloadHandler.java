head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoBookbuildingStateDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�������(WEB3AdminIpoBookbuildingStateDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �ė� (���u) �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoBookbuildingStateDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�������)
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIpoBookbuildingStateDownloadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoBookbuildingStateDownloadHandler.class);
    
    /**
     * @@roseuid 4112EE560189
     */
    public WEB3AdminIpoBookbuildingStateDownloadHandler() 
    {
     
    }
    
    /**
     * (�u�b�N�r���f�B���O�󋵃_�E�����[�h��ʕ\��)<BR>
     * IPO�u�b�N�r���f�B���O�󋵎擾�������s���B<BR>
     * <BR>
     * �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return WEB3AdminIPOBookBuildingStateDownloadResponse
     * @@roseuid 40E139C50131
     */
    public WEB3AdminIPOBookBuildingStateDownloadResponse bookbuildingStateDownloadScreenIndication(WEB3AdminIPOBookBuildingStateDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " bookbuildingStateDownloadScreenIndication(WEB3AdminIPOBookBuildingStateDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOBookBuildingStateDownloadResponse l_response = null;
        WEB3AdminIpoBookbuildingStateDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoBookbuildingStateDownloadService)Services.getService(WEB3AdminIpoBookbuildingStateDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOBookBuildingStateDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOBookBuildingStateDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOBookBuildingStateDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�\�����z�}��ʕ\��)<BR>
     * IPO�u�b�N�r���f�B���O�\�����z�}��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸސ\�����z�}ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse
     * @@roseuid 40E139C50150
     */
    public WEB3AdminIPOBookBuildingDemandMapResponse orderDistributionChartScreenIndication(WEB3AdminIPOBookBuildingDemandMapRequest l_request) 
    {
        return null;
    }
    
    /**
     * (�u�b�N�r���f�B���O�󋵃_�E�����[�h)<BR>
     * IPO�u�b�N�r���f�B���O�󋵃_�E�����[�h�t�@@�C���f�[�^�擾�������s���B<BR>
     * <BR>
     * �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸޏ�̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return WEB3AdminIPOBookBuildingStateFileDownloadResponse
     * @@roseuid 40E139C50160
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadResponse bookbuildingStateDownload(WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " bookbuildingStateDownload(WEB3AdminIPOBookBuildingStateFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOBookBuildingStateFileDownloadResponse l_response = null;
        WEB3AdminIpoBookbuildingStateDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoBookbuildingStateDownloadService)Services.getService(WEB3AdminIpoBookbuildingStateDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�u�b�N�r���f�B���O�\������\��)<BR>
     * IPO�u�b�N�r���f�B���O�\�������ꗗ�擾�������s���B<BR>
     * <BR>
     * �Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ���IPO�ޯ�����ިݸސ\������ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse
     * @@roseuid 40E139C50170
     */
    public WEB3AdminIPOBookBuildingHistoryResponse bookbuildingOrderActionIndication(WEB3AdminIPOBookBuildingHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME = " bookbuildingOrderActionIndication(WEB3AdminIPOBookBuildingHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOBookBuildingHistoryResponse l_response = null;
        WEB3AdminIpoBookbuildingStateDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoBookbuildingStateDownloadService)Services.getService(WEB3AdminIpoBookbuildingStateDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOBookBuildingHistoryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "�Ǘ���IPO�ޯ�����ިݸޏ��޳�۰�ރT�[�r�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
