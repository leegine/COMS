head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwitchingHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���抷�n���h���N���X(WEB3MutualSwitchingHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 ������ (���u) �V�K�쐬
                   2004/08/24 ��O�� (���u) ���r���[    
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequest;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmRequest;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingService;
import webbroker3.util.WEB3LogUtility;

/**
 * �����M���抷�n���h���N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingHandler.class);

    /**
     * �����M���抷�R�����s���B<BR>
     * <BR>
     * �����M���抷�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse
     * @@roseuid 40557E700389
     */
    public WEB3MutualSwitchingConfirmResponse confirmSwitching(WEB3MutualSwitchingConfirmRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "confirmSwitching(WEB3MutualSwitchingConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualSwitchingService l_service = null;
        WEB3MutualSwitchingConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualSwitchingService) Services.getService(
                    WEB3MutualSwitchingService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSwitchingConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M���抷�T�[�r�X�̎擾�����s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MutualSwitchingConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSwitchingConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����M���抷�R�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * �����M���抷�o�^���s���B<BR>
     * <BR>
     * �����M���抷�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse
     * @@roseuid 40557E750398
     */
    public WEB3MutualSwitchingCompleteResponse completeSwitching(WEB3MutualSwitchingCompleteRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "completeSwitching(WEB3MutualSwitchingCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualSwitchingService l_service = null;
        WEB3MutualSwitchingCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3MutualSwitchingService) Services.getService(
                    WEB3MutualSwitchingService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSwitchingCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M���抷�T�[�r�X�̎擾�����s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3MutualSwitchingCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSwitchingCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����M���抷�o�^���������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
