head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ύX�n���h��(WEB3AdminSrvRegiAccountChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�n���h���N���X<BR> 
 *                                                               
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountChangeHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountChangeHandler.class);
    
    /**
     * @@roseuid 416F415C03C8
     */
    public WEB3AdminSrvRegiAccountChangeHandler() 
    {
     
    }
    
    /**
     * (confirm�ڋq�ύX)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�R���������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�m�F���N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse
     * @@roseuid 40F5E29000DC
     */
    public WEB3AdminSrvRegiCustomerChangeConfirmResponse confirmAccountChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmAccountChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiCustomerChangeConfirmResponse l_response = null;
        WEB3AdminSrvRegiAccountChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiAccountChangeService)
                Services.getService(WEB3AdminSrvRegiAccountChangeService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��Ҍڋq�ύX�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (complete�ڋq�ύX)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X���擾���A<BR>
     * execute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������N�G�X�g�@@�I�u�W�F�N�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse
     * @@roseuid 40F5E29C011A
     */
    public WEB3AdminSrvRegiCustomerChangeCompleteResponse completeAccountChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeAccountChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiCustomerChangeCompleteResponse l_response = null;
        WEB3AdminSrvRegiAccountChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiAccountChangeService)Services.getService(WEB3AdminSrvRegiAccountChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��Ҍڋq�ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��Ҍڋq�ύX�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
             
        return l_response;
    }
}
@
