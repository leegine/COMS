head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.44.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�o�^�n���h��(WEB3AdminSrvRegiAccountRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountRegistService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�o�^�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�n���h���N���X<BR> 
 *                                                               
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountRegistHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountRegistHandler.class);
    
    /**
     * @@roseuid 416F415C031C
     */
    public WEB3AdminSrvRegiAccountRegistHandler() 
    {
     
    }
    
    /**
     * (confirm�ڋq�o�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�R���������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse
     * @@roseuid 40F5DF6D0253
     */
    public WEB3AdminSrvRegiCustomerRegistConfirmResponse confirmAccountRegist(WEB3AdminSrvRegiCustomerRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmAccountRegist(WEB3AdminSrvRegiCustomerRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);        
        
        WEB3AdminSrvRegiCustomerRegistConfirmResponse l_response = null;
        WEB3AdminSrvRegiAccountRegistService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiAccountRegistService)
                Services.getService(WEB3AdminSrvRegiAccountRegistService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiCustomerRegistConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��Ҍڋq�o�^�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�ڋq�o�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�o�^�������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse
     * @@roseuid 40F5DF750020
     */
    public WEB3AdminSrvRegiCustomerRegistCompleteResponse completeAccountRegist(WEB3AdminSrvRegiCustomerRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeAccountRegist(WEB3AdminSrvRegiCustomerRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiCustomerRegistCompleteResponse l_response = null;
        WEB3AdminSrvRegiAccountRegistService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiAccountRegistService)
                Services.getService(WEB3AdminSrvRegiAccountRegistService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��Ҍڋq�o�^�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiCustomerRegistCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��Ҍڋq�o�^�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        return l_response;
    }
}
@
