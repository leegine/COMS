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
filename	WEB3MarginSwapMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�n���h��(WEB3MarginSwapMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.service.delegate.WEB3MarginSwapMarginService;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * �i�M�p����������n�n���h���j�B<BR>
 * <BR>
 * �M�p����������n�n���h���N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginHandler implements MessageHandler 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginHandler.class);
        
    /**
     * @@roseuid 414184C40041
     */
    public WEB3MarginSwapMarginHandler() 
    {
     
    }
    
    /**
     * (confirm�������n)<BR>
     * �M�p����������n�����R�����s���B<BR>
     * <BR>
     * �M�p����������n�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginSwapMarginConfirmResponse
     * @@roseuid 405693970057
     */
    public WEB3MarginSwapMarginConfirmResponse confirmSwapMargin(WEB3MarginSwapMarginConfirmRequest l_request) 
    {
        final String METHOD_NAME = "confirmSwapMargin(WEB3MarginSwapMarginConfirmRequest)";
    
        log.entering(METHOD_NAME);

        WEB3MarginSwapMarginConfirmResponse l_response = null;
        WEB3MarginSwapMarginService l_service      = null;
        
        //�M�p����������n�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3MarginSwapMarginService)Services.getService(
                    WEB3MarginSwapMarginService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p����������n�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        
        //�M�p����������n�����R���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�M�p����������n�����R���Ɏ��s���܂����B", l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginSwapMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����������n�����R���Ɏ��s���܂����B", l_bre);
            log.exiting(METHOD_NAME);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (complete�������n)<BR>
     * �M�p����������n�����o�^���s���B<BR>
     * <BR>
     * �M�p����������n�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3MarginSwapMarginCompleteResponse
     * @@roseuid 4056939E0161
     */
    public WEB3MarginSwapMarginCompleteResponse completeSwapMargin(WEB3MarginSwapMarginCompleteRequest l_request) 
    {
        final String METHOD_NAME = "completeSwapMargin(WEB3MarginSwapMarginCompleteRequest)";
        
        log.entering(METHOD_NAME);

        WEB3MarginSwapMarginCompleteResponse l_response = null;
        WEB3MarginSwapMarginService l_service      = null;
        
        //�M�p����������n�T�[�r�X���擾
        try
        {
            l_service =
                (WEB3MarginSwapMarginService)Services.getService(
                    WEB3MarginSwapMarginService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�M�p����������n�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        
        //�M�p����������n�T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "�M�p����������n�����Ɏ��s���܂����B", l_ex);
            log.exiting(METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginSwapMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "�M�p����������n�����Ɏ��s���܂����B", l_bre);
            log.exiting(METHOD_NAME);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
