head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdditionalGenerationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �Ǐؔ�����ʕ\���n���h�� (WEB3AdditionalGenerationScreenHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/10/17 ������ �V�K�쐬���f��No.312
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPAdditionalGenerationResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǐؔ�����ʕ\���n���h�� )<BR>
 * �Ǐؔ�����ʕ\���n���h�� �B<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3TPAdditionalGenerationHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPAdditionalGenerationHandler.class);

    /**
     * (�R���X�g���N�^)<BR>
     */
    public WEB3TPAdditionalGenerationHandler()
    {

    }

    /**
     * (create�Ǐؔ������)<BR>
     * ���Y�]�͏���ʕ\���������s���B<BR>
     * <BR>
     * ���Y�]�͏���ʕ\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǐؔ�����ʕ\�����N�G�X�g�N���X�B<BR>
     * @@return WEB3TPAdditionalGenerationResponse
     */
    public WEB3TPAdditionalGenerationResponse createAdditionalGenerationScreen(
        WEB3TPAdditionalGenerationRequest l_request)
    {
        final String STR_METHOD_NAME = "createAdditionalGenerationScreen(WEB3TPAdditionalGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TPAssetTradingPowerService l_service = null;
        WEB3TPAdditionalGenerationResponse l_response = null;

        try
        {
            //���Y�]�͏���ʕ\���T�[�r�X���擾��
            l_service =
                (WEB3TPAssetTradingPowerService)Services.getService(
                    WEB3TPAssetTradingPowerService.class);
        }
        catch (Exception  l_ex)
        {
            l_response = (WEB3TPAdditionalGenerationResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���Y�]�͏���ʕ\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //�Ǐؔ�����ʕ\�����X�|���X
            l_response =
                (WEB3TPAdditionalGenerationResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3TPAdditionalGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǐؔ�����ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3TPAdditionalGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǐؔ�����ʕ\�������Ɏ��s���܂����B",
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
