head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�������������������̓n���h��(WEB3ToSuccEquityChangeOrderInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�������������������̓n���h��)<BR>
 * �i�A���j�������������������̓n���h���N���X�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderInputHandler implements MessageHandler 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderInputHandler.class);
    
    /**
     * @@roseuid 4369ED2E03A9
     */
    public WEB3ToSuccEquityChangeOrderInputHandler() 
    {
     
    }
    
    /**
     * (get�������͉��)<BR>
     * �i�A���j�������������������͉�ʕ\���������s���B<BR>
     * <BR>
     * �i�A���j�������������������̓T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param  l_request - ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccEquityChangeInputResponse
     * @@roseuid 433915D503C3
     */
    public WEB3SuccEquityChangeInputResponse getChangeInputScreen(WEB3SuccEquityChangeInputRequest  l_request) 
    {
        final String STR_METHOD_NAME = " getChangeInputScreen(WEB3SuccEquityChangeInputRequest )";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityChangeInputResponse l_response = null;
        WEB3ToSuccEquityChangeOrderInputService l_service = null;       

        // �i�A���j�������������������̓T�[�r�X�C���^�t�F�[�X���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_service =
                (WEB3ToSuccEquityChangeOrderInputService) Services.getService(WEB3ToSuccEquityChangeOrderInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =(WEB3SuccEquityChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�������������������̓T�[�r�X�C���^�t�F�[�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccEquityChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "get�������͉�ʂɎ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "get�������͉�ʂɎ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
