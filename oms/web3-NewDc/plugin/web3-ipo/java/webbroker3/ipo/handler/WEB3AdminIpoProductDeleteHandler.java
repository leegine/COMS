head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO�����폜�n���h��(WEB3AdminIpoProductDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/20 ���o�� �V�K�쐬
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���IPO�����폜�n���h��)<BR>
 * �Ǘ���IPO�����폜�n���h���N���X
 * 
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3AdminIpoProductDeleteHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoProductDeleteHandler.class);
    
    /**
     * @@roseuid 4112EE570181
     */
    public WEB3AdminIpoProductDeleteHandler() 
    {
     
    }
    
    /**
     * (�����폜�m�F)<BR>
     * �Ǘ���IPO�����폜�m�F����<BR>
     * <BR>
     * �Ǘ���IPO�����폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����폜�m�F���N�G�X�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse
     * @@roseuid 40C7DEB10166
     */
    public WEB3AdminIPOProductDeleteConfirmResponse productDeleteConfirm(WEB3AdminIPOProductDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " productDeleteConfirm(WEB3AdminIPOProductDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductDeleteConfirmResponse l_response = null;
        WEB3AdminIpoProductDeleteService l_service = null;
        
        //�Ǘ���IPO�����폜�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminIpoProductDeleteService)Services.getService(
                WEB3AdminIpoProductDeleteService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO�����폜�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ���IPO�����폜�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIPOProductDeleteConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteConfirmResponse)l_request.createResponse();
			// 2004/11/12 U00407 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA START
			// l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorInfo = l_ex.getErrorInfo();
			// 2004/11/12 U00407 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA END
            log.error(
                l_request,
                "�Ǘ���IPO�����폜�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (�����폜����)<BR>
     * �Ǘ���IPO�����폜��������<BR>
     * <BR>
     * �Ǘ���IPO�����폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ���IPO�����폜�������N�G�X�g
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse
     * @@roseuid 40C7DEB1031C
     */
    public WEB3AdminIPOProductDeleteCompleteResponse productDeleteComplete(WEB3AdminIPOProductDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " productDeleteComplete(WEB3AdminIPOProductDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductDeleteCompleteResponse l_response = null;
        WEB3AdminIpoProductDeleteService l_service = null;
        
        //�Ǘ���IPO�����폜�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminIpoProductDeleteService)Services.getService(
                    WEB3AdminIpoProductDeleteService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ���IPO�����폜�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //�Ǘ���IPO�����폜�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminIPOProductDeleteCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDeleteCompleteResponse)l_request.createResponse();                
			// 2004/11/12 U00407 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA START
    		// l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			l_response.errorInfo = l_ex.getErrorInfo();
            // 2004/11/12 U00407 �r�W�l�X�G���[�����X�|���X�ɃZ�b�g����悤�ɏC��  ���@@SRA END
            log.error(
                l_request,
                "�Ǘ���IPO�����폜�Ɏ��s���܂����B",
                l_ex);
            return l_response; 

        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
}
@
