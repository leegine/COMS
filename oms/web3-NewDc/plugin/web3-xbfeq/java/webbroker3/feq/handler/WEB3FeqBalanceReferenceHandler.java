head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������c���Ɖ�n���h��(WEB3FeqBalanceReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[          
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBalanceReferenceRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceResponse;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalRequest;
import webbroker3.feq.message.WEB3FeqBalanceReferenceTotalResponse;
import webbroker3.feq.service.delegate.WEB3FeqBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������c���Ɖ�n���h��)<BR>
 * �O�������c���Ɖ�n���h���N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceHandler.class);
    
    /**
     * @@roseuid 42D0DA19006D
     */
    public WEB3FeqBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get�c���Ɖ�)<BR>
     * �O�������c���Ɖ�����s���B<BR>
     * <BR>
     * �O�������c���Ɖ�T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������c���Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqBalanceReferenceResponse
     * @@roseuid 42A7F63602FA
     */
    public WEB3FeqBalanceReferenceResponse getBalanceReference(
        WEB3FeqBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getBalanceReference(WEB3FeqBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������c���Ɖ�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqBalanceReferenceService l_service = null;          
         
        //�O�������c���Ɖ�X�|���X
        WEB3FeqBalanceReferenceResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FeqBalanceReferenceService) Services.getService(
                    WEB3FeqBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBalanceReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������c���Ɖ���Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�c�����v)<BR>
     * �O�������c�����v�������s���B<BR>
     * <BR>
     * �O�������c���Ɖ�T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�������c�����v���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.feq.message.WEB3FeqBalanceReferenceTotalResponse
     * @@roseuid 42A7F65B0154
     */
    public WEB3FeqBalanceReferenceTotalResponse getBalanceTotal(
        WEB3FeqBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getBalanceTotal(WEB3FeqBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //�O�������c���Ɖ�T�[�r�X�C���^�[�t�F�C�X
        WEB3FeqBalanceReferenceService l_service = null;          
         
        //�O�������c�����v���X�|���X
        WEB3FeqBalanceReferenceTotalResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FeqBalanceReferenceService) Services.getService(
                    WEB3FeqBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FeqBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�������c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�O�������c�����v�����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
