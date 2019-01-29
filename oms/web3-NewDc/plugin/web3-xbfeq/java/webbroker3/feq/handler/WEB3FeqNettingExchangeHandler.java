head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������בփl�b�e�B���O�n���h��(WEB3FeqNettingExchangeHandler.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/08 �����F (���u) �V�K�쐬 ���f��549
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqNettingExchangeRequest;
import webbroker3.feq.message.WEB3FeqNettingExchangeResponse;
import webbroker3.feq.service.delegate.WEB3FeqNettingExchangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������בփl�b�e�B���O�n���h��)<BR>
 * �O�������בփl�b�e�B���O�n���h���N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FeqNettingExchangeHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqNettingExchangeHandler.class);

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3FeqNettingExchangeHandler()
    {

    }

    /**
     * (�בփl�b�e�B���O)<BR>
     * �בփl�b�e�B���O���s���B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3FeqNettingExchangeResponse
     */
    public WEB3FeqNettingExchangeResponse nettingExchange(WEB3FeqNettingExchangeRequest l_request)
    {
        final String STR_METHOD_NAME = "nettingExchange(WEB3FeqNettingExchangeRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqNettingExchangeResponse l_response = null;
        WEB3FeqNettingExchangeService l_service = null;

        try
        {
            l_service = (WEB3FeqNettingExchangeService)Services.getService(
                WEB3FeqNettingExchangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3FeqNettingExchangeResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������בփl�b�e�B���O�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3FeqNettingExchangeResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FeqNettingExchangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�בփl�b�e�B���O���s�������Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FeqNettingExchangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�בփl�b�e�B���O���s�������Ɏ��s���܂����B",
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
