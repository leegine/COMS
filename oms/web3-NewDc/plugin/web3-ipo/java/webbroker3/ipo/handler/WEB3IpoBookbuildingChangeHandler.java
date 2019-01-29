head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  IPO�u�b�N�r���f�B���O�����n���h���N���X(WEB3IpoBookbuildingChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ���]��(���u) �V�K�쐬
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�u�b�N�r���f�B���O�����n���h���N���X
 * @@author ���]��(���u)
 * @@version 1.0
 */
public class WEB3IpoBookbuildingChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingChangeHandler.class);
    
    /**
     * @@roseuid 4112EE5803DB
     */
    public WEB3IpoBookbuildingChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * IPO�u�b�N�r���f�B���O�������͉�ʕ\������<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O�������̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeInputResponse
     * @@roseuid 40D9695002ED
     */
    public WEB3IPOBookBuildingChangeInputResponse inputScreenIndication(WEB3IPOBookBuildingChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
             " inputScreenIndication(WEB3IpoBookbuildingChangeInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingChangeInputResponse l_response = null;
        WEB3IpoBookbuildingChangeService l_service = null;
        
        //IPO�u�b�N�r���f�B���O�����T�[�r�X�C���^�t�F�C�X
        try
        {
            l_service =
                (WEB3IpoBookbuildingChangeService)Services.getService(
            WEB3IpoBookbuildingChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO�u�b�N�r���f�B���O�����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3IPOBookBuildingChangeInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;        

    }
    
    /**
     * IPO�u�b�N�r���f�B���O�����m�F����<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O�����m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeConfirmResponse
     * @@roseuid 40D9692F0195
     */
    public WEB3IPOBookBuildingChangeConfirmResponse bookbuildingChangeConfirm(WEB3IPOBookBuildingChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 " bookbuildingChangeConfirm(WEB3IpoBookbuildingChangeConfirmRequest )";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingChangeConfirmResponse l_response = null;
        WEB3IpoBookbuildingChangeService l_service = null;
        
        //IPO�u�b�N�r���f�B���O�����T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3IpoBookbuildingChangeService)Services.getService(
            WEB3IpoBookbuildingChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO�u�b�N�r���f�B���O�����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3IPOBookBuildingChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O�����Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * IPO�u�b�N�r���f�B���O������������<BR>
     * <BR>
     * IPO�u�b�N�r���f�B���O�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - IPO�u�b�N�r���f�B���O�����������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingChangeCompleteResponse
     * @@roseuid 40D9692F01A5
     */
    public WEB3IPOBookBuildingChangeCompleteResponse bookbuildingChangeComplete(WEB3IPOBookBuildingChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 " bookbuildingChangeComplete(WEB3IpoBookbuildingChangeCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingChangeCompleteResponse l_response = null;
        WEB3IpoBookbuildingChangeService l_service = null;
        
        //IPO�u�b�N�r���f�B���O�����T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3IpoBookbuildingChangeService)Services.getService(
            WEB3IpoBookbuildingChangeService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO�u�b�N�r���f�B���O�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO�u�b�N�r���f�B���O�����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3IPOBookBuildingChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO�u�b�N�r���f�B���O�����T�[�r�X�Ɏ��s���܂����B", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
