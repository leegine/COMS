head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetHistoryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���Y�]���z�����n���h�� (WEB3TPAssetHistoryHandler)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/08/25  䈋�(���u) �V�K�쐬
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryRequest;
import webbroker3.tradingpower.message.WEB3TPAssetHistoryResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���Y�]���z�����n���h��) <BR>
 * ���Y�]���z�����n���h���N���X �B<BR>
 * 
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3TPAssetHistoryHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3TPAssetHistoryHandler.class);

    /**
     * (�R���X�g���N�^)
     * 
     * @@roseuid 41B64BDC0241
     */
    public WEB3TPAssetHistoryHandler()
    {

    }

    /**
     * (create���Y�]���z�������) <BR>
     * ���Y�]�͏���ʕ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * <BR>
     * 
     * @@param l_request
     * @@return webbroker3.tradingpower.message.WEB3TPAssetHistoryResponse
     * @@roseuid 41B64A6E03A8
     */
    public WEB3TPAssetHistoryResponse createAssertHistory(
                    WEB3TPAssetHistoryRequest l_request)
    {
        final String STR_METHOD_NAME = "createAssertHistory";
        log.entering(STR_METHOD_NAME);

        WEB3TPAssetTradingPowerService l_service = null;
        WEB3TPAssetHistoryResponse l_response = null;

        try
        {
            //���Y�]�͏���ʕ\���T�[�r�X���擾
            l_service = (WEB3TPAssetTradingPowerService) 
                Services.getService(WEB3TPAssetTradingPowerService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3TPAssetHistoryResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "���Y�]�͏���ʕ\���T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response = (WEB3TPAssetHistoryResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3TPAssetHistoryResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���Y�]�͏���ʕ\���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3TPAssetHistoryResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���Y�]�͏���ʕ\���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

}@
