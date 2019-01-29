head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�������n���h��(WEB3MarginCancelServiceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/08 ������ (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCancelCompleteRequest;
import webbroker3.equity.message.WEB3MarginCancelCompleteResponse;
import webbroker3.equity.message.WEB3MarginCancelConfirmRequest;
import webbroker3.equity.message.WEB3MarginCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�������n���h���j�B<BR>
 * <BR>
 * �M�p�������n���h���N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCancelHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCancelHandler.class);
        
    /**
     * @@roseuid 414184C40249
     */
    public WEB3MarginCancelHandler() 
    {
     
    }
    
    /**
     * (confirm���)<BR>
     * �M�p�������R�����s���B<BR>
     * <BR>
     * �M�p�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p���������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MarginCancelConfirmResponse<BR>
     * @@roseuid 405808D001E6
     */
    public WEB3MarginCancelConfirmResponse confirmCancel(WEB3MarginCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                "confirmSwapMargin(WEB3MarginCancelConfirmRequest)";
    
        log.entering(STR_METHOD_NAME);

        WEB3MarginCancelConfirmResponse l_response = null;
        WEB3MarginCancelService l_service      = null;
        
        //�M�p�������T�[�r�X���擾
        try
        {
            l_service =
                (WEB3MarginCancelService)Services.getService(
                    WEB3MarginCancelService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�M�p�������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p�������R���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p�������R���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (complete���)<BR>
     * �M�p�������������s���B<BR>
     * <BR>
     * �M�p�������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �M�p���������N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MarginCancelCompleteResponse<BR>
     * @@roseuid 405808D60198
     */
    public WEB3MarginCancelCompleteResponse completeCancel(WEB3MarginCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                "confirmSwapMargin(WEB3MarginCancelCompleteRequest)";
    
        log.entering(STR_METHOD_NAME);

        WEB3MarginCancelCompleteResponse l_response = null;
        WEB3MarginCancelService l_service      = null;
        
        //�M�p�������T�[�r�X���擾
        try
        {
            l_service =
                (WEB3MarginCancelService)Services.getService(
                    WEB3MarginCancelService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p�������T�[�r�X�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�M�p�������T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�M�p�����������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p�����������Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
