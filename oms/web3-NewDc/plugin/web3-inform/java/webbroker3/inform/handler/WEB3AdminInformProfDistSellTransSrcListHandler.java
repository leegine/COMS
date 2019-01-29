head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �����E���z���E���p����U����ꗗ�T�[�r�X�n���h���N���X(WEB3AdminInformProfDistSellTransSrcListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 �Ӑ�(���u) �V�K�쐬 ���f��No.046
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistSellTransSrcListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����E���z���E���p����U����ꗗ�T�[�r�X�n���h���N���X)<BR>
 * �����E���z���E���p����U����ꗗ�T�[�r�X�n���h���N���X<BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcListHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListHandler.class);

    /**
     * @@roseuid 465593750165
     */
    public WEB3AdminInformProfDistSellTransSrcListHandler()
    {

    }

    /**
     * (���͉�ʕ\��)<BR>
     * �����E���z���E���p����U���挟�����͉�ʂ�\������B<BR>
     * <BR>
     * �����E���z���E���p����U����ꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistSellTransSrcInpResponse
     * @@roseuid 461F4FF8036D
     */
    public WEB3AdminInformProfDistSellTransSrcInpResponse displayInputScreen(
        WEB3AdminInformProfDistSellTransSrcInpRequest l_request)
    {
        String STR_METHOD_NAME = " displayInputScreen(WEB3AdminInformProfDistSellTransSrcInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistSellTransSrcInpResponse l_response = null;
        WEB3AdminInformProfDistSellTransSrcListService l_service = null;

        //�����E���z���E���p����U����ꗗ�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformProfDistSellTransSrcListService)Services.getService(
                WEB3AdminInformProfDistSellTransSrcListService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminInformProfDistSellTransSrcInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����E���z���E���p����U����ꗗ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�A����񌟍��T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcInpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����E���z���E���p����U���挟�����͂Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (�ꗗ��ʕ\��)<BR>
     * �����E���z���E���p����U����ꗗ��ʂ�\������B<BR>
     * <BR>
     * �����E���z���E���p����U����ꗗ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminInformProfDistSellTransSrcListResponse
     * @@roseuid 461F500B0330
     */
    public WEB3AdminInformProfDistSellTransSrcListResponse displayListScreen(
        WEB3AdminInformProfDistSellTransSrcListRequest l_request)
    {
        String STR_METHOD_NAME = " displayListScreen(WEB3AdminInformProfDistSellTransSrcListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformProfDistSellTransSrcListResponse l_response = null;
        WEB3AdminInformProfDistSellTransSrcListService l_service = null;

        //�����E���z���E���p����U����ꗗ�T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformProfDistSellTransSrcListService)Services.getService(
                WEB3AdminInformProfDistSellTransSrcListService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AdminInformProfDistSellTransSrcListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����E���z���E���p����U����ꗗ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�A����񌟍��T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformProfDistSellTransSrcListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����E���z���E���p����U����ꗗ�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
