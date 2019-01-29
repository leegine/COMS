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
filename	WEB3EquityOrderBuyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t�������̓n���h�� (WEB3EquityOrderBuyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/10 �R�w�� (���u) �V�K�쐬
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3EquityBuyInputResponse;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.equity.message.WEB3EquityProductSelectResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.service.delegate.WEB3EquityOrderBuyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * �i�����������t�������̓n���h���N���X�j�B 
 * @@version 1.0
 */
public class WEB3EquityOrderBuyInputHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputHandler.class);
           
    /**
     * @@roseuid 409B36EA0157
     */
    public WEB3EquityOrderBuyInputHandler() 
    {
    
    }
   
    /**
     * (���t�������̓��N�G�X�g) <BR>
     * <BR>
     * �����������t�������͕\���������s���B<BR>
     * <BR>
     * �����������t�������̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) <BR>
     * <BR>
     * �����������t�������̓��N�G�X�g�I�u�W�F�N�g <BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     * @@roseuid 406285F70087
     */
    public WEB3EquityBuyInputResponse equityOrderBuyInputRequest(WEB3EquityBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "equityOrderBuyInputRequest(WEB3EquityBuyInputRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3EquityBuyInputResponse l_response = null;
        WEB3EquityOrderBuyInputService l_service = null;
       
        // �����������t�������̓T�[�r�X�I�u�W�F�N�g���擾����
        // �����������t�������̓T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3EquityOrderBuyInputService) Services.getService(WEB3EquityOrderBuyInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =(WEB3EquityBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�����������t�������̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "���t�������͂Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "���t�������͂Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (���t���������I�����N�G�X�g) <BR>
     * <BR>
     * �����������t���������I����ʕ\���������s���B<BR>
     * <BR>
     * �����������t�������̓T�[�r�X���擾���A
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) <BR>
     * <BR>
     * ���t���������I�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     */
    public WEB3EquityProductSelectResponse equityProductSelectRequest(WEB3EquityProductSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = "equityProductSelectRequest(WEB3EquityProductSelectRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3EquityProductSelectResponse l_response = null;
        WEB3EquityOrderBuyInputService l_service = null;
       
        // �����������t�������̓T�[�r�X�I�u�W�F�N�g���擾����
        // �����������t�������̓T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        // �������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_service =
                (WEB3EquityOrderBuyInputService) Services.getService(WEB3EquityOrderBuyInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception e)
        {
            l_response =(WEB3EquityProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�����������t�������̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityProductSelectResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "���t���������I���Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "���t���������I���Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
