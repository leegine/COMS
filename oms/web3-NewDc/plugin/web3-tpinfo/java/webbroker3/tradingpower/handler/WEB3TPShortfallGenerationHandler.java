head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s����������ʕ\���n���h��(WEB3TPShortfallGenerationHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/17 �����i���u�j�V�K�쐬 ���f��No.312
*/
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationRequest;
import webbroker3.tradingpower.message.WEB3TPShortfallGenerationResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�s����������ʕ\���n���h��)<BR>
 * �s����������ʕ\���n���h���N���X�B<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3TPShortfallGenerationHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3TPShortfallGenerationHandler.class);

    /**
     * (�R���X�g���N�^)<BR>
     */
    public WEB3TPShortfallGenerationHandler()
    {

    }

    /**
     * (create�s�����������)<BR>
     * ���Y�]�͏���ʕ\���������s���B<BR>
     * <BR>
     * ���Y�]�͏���ʕ\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �s���������󋵉�ʕ\�����N�G�X�g�N���X�B<BR>
     * @@return WEB3TPShortfallGenerationResponse
     */
    public WEB3TPShortfallGenerationResponse createShortfallGenerationScreen(
        WEB3TPShortfallGenerationRequest l_request)
    {
        final String STR_METHOD_NAME = "createShortfallGenerationScreen(WEB3TPShortfallGenerationRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TPShortfallGenerationResponse l_response = null;
        WEB3TPAssetTradingPowerService l_service = null;

        //���Y�]�͏���ʕ\���T�[�r�X���擾
        try
        {
            l_service = (WEB3TPAssetTradingPowerService)Services.getService(
                WEB3TPAssetTradingPowerService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���Y�]�͏���ʕ\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3TPShortfallGenerationResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���Y�]�͏���ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3TPShortfallGenerationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���Y�]�͏���ʕ\�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
