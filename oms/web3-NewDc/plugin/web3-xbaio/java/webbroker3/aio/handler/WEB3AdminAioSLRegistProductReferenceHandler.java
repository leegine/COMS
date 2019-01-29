head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLRegistProductReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۓo�^�����Ɖ�n���h���N���X(WEB3AdminAioSLRegistProductReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����F (���u) �V�K�쐬 ���f��760
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductRegiListRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLRegistProductReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�S�ۓo�^�����Ɖ�n���h��)<BR>
 * �S�ۓo�^�����Ɖ�n���h���N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminAioSLRegistProductReferenceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLRegistProductReferenceHandler.class);

    /**
     * (get�S�ۓo�^�����ꗗ)<BR>
     * �o�^����Ă���S�ۖ�����\�����鏈�����R�[������B <BR>
     * <BR>
     * �P�j�@@�S�ۓo�^�����Ɖ�T�[�r�X���擾���Aexecute���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLProductRegiListResponse
     */
    public WEB3AdminSLProductRegiListResponse getSLRegiProductList(
        WEB3AdminSLProductRegiListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSLRegiProductList(WEB3AdminSLProductRegiListRequest)";
        log.entering(STR_METHOD_NAME);

        //�S�ۓo�^�����Ɖ�T�[�r�X���擾
        WEB3AdminAioSLRegistProductReferenceService l_service = null;
        WEB3AdminSLProductRegiListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLRegistProductReferenceService)
                Services.getService(WEB3AdminAioSLRegistProductReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegiListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۓo�^�����Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSLProductRegiListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegiListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�o�^����Ă���S�ۖ�����\�����鏈�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductRegiListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�o�^����Ă���S�ۖ�����\�����鏈�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
