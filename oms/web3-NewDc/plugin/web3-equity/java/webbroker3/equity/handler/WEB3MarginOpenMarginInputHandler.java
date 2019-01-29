head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K�����̓n���h��(WEB3MarginOpenMarginInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���Ō� (Sinocom) �V�K�쐬 
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginInputResponse;
import webbroker3.equity.message.WEB3MarginProductSelectRequest;
import webbroker3.equity.message.WEB3MarginProductSelectResponse;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginInputService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * �i�M�p����V�K�����̓n���h���j�B<BR>
 * <BR>
 * �M�p����V�K�����̓n���h���N���X
 * @@author ���Ō�
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputHandler implements MessageHandler 
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputHandler.class);
           
    /**
     * @@roseuid 414184C50060
     */
    public WEB3MarginOpenMarginInputHandler() 
    {
     
    }
    
    /**
     * (get�����I�����)<BR>
     * �M�p����̐V�K�������I����ʕ\���������s���B<BR>
     * <BR>
     * �M�p����V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginProductSelectResponse
     * @@roseuid 40F64C01036C
     */
    public WEB3MarginProductSelectResponse getProductSelectScreen(WEB3MarginProductSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = "getProductSelectScreen(WEB3MarginProductSelectRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginProductSelectResponse l_response = null;
        WEB3MarginOpenMarginInputService l_service = null;

        //�M�p����̐V�K�������I����ʕ\���������s���B
        try
        {
            l_service = (WEB3MarginOpenMarginInputService)Services.getService(WEB3MarginOpenMarginInputService.class);

            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3MarginProductSelectResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p����V�K�����̓T�[�r�X�擾�Ɏ��s���܂����B�B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�M�p����V�K�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3MarginProductSelectResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginProductSelectResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p��������I���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginProductSelectResponse)l_request.createResponse();

            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p��������I���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (get�V�K�����͉��)<BR>
     * �M�p����̐V�K�����͕\���������s���B<BR>
     * <BR>
     * �M�p����V�K�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginOpenMarginInputResponse
     * @@roseuid 407BBC6903B8
     */
    public WEB3MarginOpenMarginInputResponse getOpenMarginInputScreen(WEB3MarginOpenMarginInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getOpenMarginInputScreen(WEB3OptionsOpenMarginInputResponse)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginInputResponse l_response = null;
        WEB3MarginOpenMarginInputService l_service = null;

        //�M�p����̐V�K�����͕\���������s��
        try
        {
            l_service = (WEB3MarginOpenMarginInputService)Services.getService(WEB3MarginOpenMarginInputService.class);

            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3MarginOpenMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "�M�p����V�K�����̓T�[�r�X�擾�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�M�p����V�K�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
        try
        {
            l_response = (WEB3MarginOpenMarginInputResponse) l_service.execute(l_request);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginOpenMarginInputResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p����V�K�����͂Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginOpenMarginInputResponse)l_request.createResponse();

            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����V�K�����͂Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
