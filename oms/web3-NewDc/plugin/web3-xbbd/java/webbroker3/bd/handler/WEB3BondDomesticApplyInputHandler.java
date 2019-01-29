head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������̓n���h��(WEB3BondDomesticApplyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.225
*/
package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (������������̓n���h��)<BR>
 * ������������̓n���h��<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputHandler implements MessageHandler
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyInputHandler.class);

    /**
     * @@roseuid 46A473FD0196
     */
    public WEB3BondDomesticApplyInputHandler()
    {

    }

    /**
     * (�������������)<BR>
     * ������������͏������s���B<BR>
     * <BR>
     * ������������̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BondDomesticApplyInputResponse
     * @@roseuid 466CCD150226
     */
    public WEB3BondDomesticApplyInputResponse bondDomesticApplyInput(
        WEB3BondDomesticApplyInputRequest l_request)
    {
        final String STR_METHOD_NAME = " bondDomesticApplyInput(WEB3BondDomesticApplyInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BondDomesticApplyInputResponse l_response = null;
        WEB3BondDomesticApplyInputService l_service = null;
        try
        {
            l_service =
                (WEB3BondDomesticApplyInputService)Services.getService(
                    WEB3BondDomesticApplyInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������������̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3BondDomesticApplyInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondDomesticApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
