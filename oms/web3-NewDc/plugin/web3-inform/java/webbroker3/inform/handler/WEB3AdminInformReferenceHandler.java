head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��n���h���N���X(WEB3AdminInformReferenceHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDetailResponse;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadResponse;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformInputResponse;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.inform.message.WEB3AdminInformListResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A����񌟍��n���h��)<BR>
 * �A����񌟍��n���h���N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformReferenceHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceHandler.class);
    
    /**
     * @@roseuid 41EE631D038A
     */
    public WEB3AdminInformReferenceHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �A����񌟍����͉�ʂ�\������B<BR>
     * <BR>
     * �A����񌟍��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.inform.message.WEB3AdminInformInputResponse
     * @@roseuid 41BD7F970278
     */
    public WEB3AdminInformInputResponse informInputDisplay(WEB3AdminInformInputRequest l_request) 
    {
        final String METHOD_NAME = "informInputDisplay(WEB3AdminInformInputRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformInputResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //�A����񌟍��T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�A����񌟍��T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //�A����񌟍��T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�A����񌟍��Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�ꗗ��ʕ\��)<BR>
     * �A����񌟍��ꗗ��ʂ�\������B<BR>
     * <BR>
     * �A����񌟍��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.inform.message.WEB3AdminInformListResponse
     * @@roseuid 41BD817A01FB
     */
    public WEB3AdminInformListResponse informListDisplay(WEB3AdminInformListRequest l_request) 
    {
        final String METHOD_NAME = "informListDisplay(WEB3AdminInformListRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformListResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //�A����񌟍��T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�A����񌟍��T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //�A����񌟍��T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�A����񌟍��Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�ڍ׉�ʕ\��)<BR>
     * �A����񌟍��ڍ׉�ʂ�\������B<BR>
     * <BR>
     * �A����񌟍��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.inform.message.WEB3AdminInformDetailResponse
     * @@roseuid 41BD817B03C0
     */
    public WEB3AdminInformDetailResponse informDetailDisplay(WEB3AdminInformDetailRequest l_request) 
    {
        final String METHOD_NAME = "informDetailDisplay(WEB3AdminInformDetailRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformDetailResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //�A����񌟍��T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�A����񌟍��T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //�A����񌟍��T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformDetailResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�A����񌟍��Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (�e��A���_�E�����[�h)<BR>
     * �e��A���_�E�����[�h�t�@@�C���擾�������s���B<BR>
     * <BR>
     * �A����񌟍��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.inform.message.WEB3AdminInformDownLoadResponse
     * @@roseuid 41BD822C0259
     */
    public WEB3AdminInformDownLoadResponse allInformDownload(WEB3AdminInformDownLoadRequest l_request) 
    {
        final String METHOD_NAME = "allInformDownload(WEB3AdminInformDownLoadRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformDownLoadResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //�A����񌟍��T�[�r�X���擾
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�A����񌟍��T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //�A����񌟍��T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformDownLoadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�A����񌟍��Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
