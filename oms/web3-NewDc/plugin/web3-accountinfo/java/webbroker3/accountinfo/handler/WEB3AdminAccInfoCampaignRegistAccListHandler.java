head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔�������L�����y�[���o�^�ڋq�Ɖ�n���h��
                       (WEB3AdminAccInfoCampaignRegistAccListHandler.java)
Author Name      : Daiwa Institute of Research
Revision  History : 2007/02/01 �Ј��� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (�o�^�ڋq�Ɖ�n���h��)<BR>
 * �萔�������L�����y�[���o�^�ڋq�Ɖ�n���h��<BR>
 * @@author �Ј��� 
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignRegistAccListHandler.class);
    
    /**
     * @@roseuid 45C08B51035B
     */
    public WEB3AdminAccInfoCampaignRegistAccListHandler() 
    {
     
    }
    
    /**
     * (�Ɖ��ʕ\��)<BR>
     * �萔�������L�����y�[���o�^�ڋq�Ɖ��ʕ\���������s���B <BR>
     * �o�^�ڋq�Ɖ�T�[�r�XImpl���擾���A execute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��҂��q�l���<BR>
     * �萔�������L�����y�[���o�^�ڋq�Ɖ�N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * 
     * @@return WEB3AdminAccInfoCampaignRegistAccListResponse
     * @@roseuid 45ADEF2F0114
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse listScreenShow(
            WEB3AdminAccInfoCampaignRegistAccListRequest l_request) 
    {
        final String STR_METHOD_NAME = ".getListScreen()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignRegistAccListService l_service = null;
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = null;
        
        try
        {
            l_service =
                (WEB3AdminAccInfoCampaignRegistAccListService)Services.getService(
                    WEB3AdminAccInfoCampaignRegistAccListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignRegistAccListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o�^�ڋq�Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminAccInfoCampaignRegistAccListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignRegistAccListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�o�^�ڋq�Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
