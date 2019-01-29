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
filename	WEB3MutualSwitchingInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�抷���̓n���h���N���X(WEB3MutualSwitchingInputHandler)
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
import webbroker3.mf.message.WEB3MutualSwitchingInputRequest;
import webbroker3.mf.message.WEB3MutualSwitchingInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * ���M�抷���̓n���h���N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualSwitchingInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwitchingInputHandler.class);

    /**
     * (�抷���̓��N�G�X�g )<BR>
     * �����M���̏抷���͉�ʕ\���������s���B<BR>
     * <BR>
     * ���M�抷���̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���M�抷���̓��N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.mf.message.WEB3MutualSwitchingInputResponse
     * @@roseuid 40B1713402BF
     */
    public WEB3MutualSwitchingInputResponse switchingInputRequest(WEB3MutualSwitchingInputRequest l_request)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "switchingInputRequest(WEB3MutualSwitchingInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3MutualSwitchingInputResponse l_response = null;

        WEB3MutualSwitchingInputService l_service = null;

        try
        {
            l_service =
                (WEB3MutualSwitchingInputService) Services.getService(
            WEB3MutualSwitchingInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MutualSwitchingInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����M���̏抷���͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //���M�抷���̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response =
                (WEB3MutualSwitchingInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualSwitchingInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�����M���̏抷���͉�ʕ\�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
