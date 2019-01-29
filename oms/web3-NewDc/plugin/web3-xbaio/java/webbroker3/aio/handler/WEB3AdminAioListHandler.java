head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ғ��o���ꗗ�n���h���N���X(WEB3AdminAioListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/05 �����q (���u) �V�K�쐬�@@�d�l�ύX���f�� NO.694
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ғ��o���ꗗ�n���h��)<BR>
 * �Ǘ��ғ��o���ꗗ�n���h���N���X<BR>
 * <BR>
 * @@author �����q(���u)
 * @@version 1.0 
 */
public class WEB3AdminAioListHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminAioListHandler.class);

    /**
     * @@roseuid 45C4096500C0
     */
    public WEB3AdminAioListHandler() 
    {
     
    }

    /**
     * ���o���ꗗ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ғ��o���ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param ���N�G�X�g�f�[�^ - ���N�G�X�g�f�[�^
     * @@return webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse
     * @@roseuid 45B73592003C
     */
    public WEB3AdminAioCashTransferListInputResponse getInputScreen(
        WEB3AdminAioCashTransferListInputRequest l_request) 
    {
        String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminAioCashTransferListInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioListService l_service;
        WEB3AdminAioCashTransferListInputResponse l_response;

        try
        {
            //���o���ꗗ���͉�ʕ\���������s��
            l_service = 
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioCashTransferListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�Ǘ��ғ��o���ꗗ�T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminAioCashTransferListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashTransferListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �Ǘ��ғ��o���ꗗ�T�[�r�X.execute__",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * ���o���ꗗ���ʉ�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ғ��o���ꗗ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param ���N�G�X�g�f�[�^ - ���N�G�X�g�f�[�^
     * @@return webbroker3.aio.message.WEB3AdminAioCashTransferListResponse
     * @@roseuid 45B737F200B9
     */
    public WEB3AdminAioCashTransferListResponse getListScreen(WEB3AdminAioCashTransferListRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "getListScreen(WEB3AdminAioCashTransferListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioListService l_service;
        WEB3AdminAioCashTransferListResponse l_response;

        try
        {
            //���̑������Ɖ�T�[�r�X���擾��
            l_service = 
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioCashTransferListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�Ǘ��ғ��o���ꗗ�T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminAioCashTransferListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashTransferListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �Ǘ��ғ��o���ꗗ�T�[�r�X.execute__",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * ���o���ꗗget�_�E�����[�h�t�@@�C���������s���B<BR>
     * <BR>
     * �Ǘ��ғ��o���ꗗ�T�[�r�XImpl���擾���A<BR>
     * <BR>
     * execute()���\�b�h���R�[������B
     * @@param ���N�G�X�g�f�[�^ - ���N�G�X�g�f�[�^
     * @@return webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse
     * @@roseuid 45B737F3003C
     */
    public WEB3AdminAioCashTransferListDownloadResponse getDownLoadFile(WEB3AdminAioCashTransferListDownloadRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "getDownLoadFile(WEB3AdminAioCashTransferListDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioListService l_service;
        WEB3AdminAioCashTransferListDownloadResponse l_response;

        try
        {
            //���̑������Ɖ�T�[�r�X���擾��
            l_service = 
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioCashTransferListDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__�Ǘ��ғ��o���ꗗ�T�[�r�X�̎擾�Ɏ��s���܂���__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = 
                (WEB3AdminAioCashTransferListDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashTransferListDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call �Ǘ��ғ��o���ꗗ�T�[�r�X.execute__",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
