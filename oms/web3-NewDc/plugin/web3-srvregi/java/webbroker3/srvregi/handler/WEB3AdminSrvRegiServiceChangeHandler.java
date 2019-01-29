head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�n���h��(WEB3AdminSrvRegiServiceChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�n���h���N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeHandler.class);
    
    /**
     * @@roseuid 416F415B02FD
     */
    public WEB3AdminSrvRegiServiceChangeHandler() 
    {
     
    }
    
    /**
     * (confirm�T�[�r�X�ύX)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�R���������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@roseuid 40F5140600E3
     */
    public WEB3AdminSrvRegiServiceChangeConfirmResponse confirmSrvChange(WEB3AdminSrvRegiServiceChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmSrvChange(WEB3AdminSrvRegiServiceChangeConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceChangeConfirmResponse l_response = null;
        WEB3AdminSrvRegiServiceChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceChangeService)Services.getService(WEB3AdminSrvRegiServiceChangeService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�R���Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�R���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (complete�T�[�r�X�ύX)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�������s���B<BR>
     * <BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X���擾���Aexecute( )<BR>
     * ���\�b�h���R�[������B<BR>
     * @@roseuid 40F5140F00E3
     */
    public WEB3AdminSrvRegiServiceChangeCompleteResponse completeSrvChange(WEB3AdminSrvRegiServiceChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeSrvChange(WEB3AdminSrvRegiServiceChangeCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceChangeCompleteResponse l_response = null;
        WEB3AdminSrvRegiServiceChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceChangeService)Services.getService(WEB3AdminSrvRegiServiceChangeService.class);//Exception
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminSrvRegiServiceChangeCompleteResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �T�[�r�X���p�Ǘ��҃T�[�r�X�ύX�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
                
        return l_response; 
    }
}
@
