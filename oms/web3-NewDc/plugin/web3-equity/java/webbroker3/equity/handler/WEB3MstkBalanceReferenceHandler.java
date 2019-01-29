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
filename	WEB3MstkBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����c���Ɖ�n���h��(WEB3EquityBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MstkBalanceReferenceTotalResponse;
import webbroker3.equity.service.delegate.WEB3MstkBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * �i�����~�j�����c���Ɖ�n���h���j�B<BR>
 * <BR>
 * �����~�j�����c���Ɖ�n���h���N���X<BR>
 */
public class WEB3MstkBalanceReferenceHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBalanceReferenceHandler.class);
        
    /**
     * @@roseuid 4206CCB302EA<BR>
     */
    public WEB3MstkBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get�c���Ɖ�)<BR>
     * <BR>
     * �����~�j�����c���Ɖ�����s���B<BR>
     * <BR>
     * �����~�j�����c���Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �����~�j�����c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MstkBalanceReferenceResponse<BR>
     * @@roseuid 41C2D1730083<BR>
     */
    public WEB3MstkBalanceReferenceResponse getBalanceReference(WEB3MstkBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBalanceReferenceService l_service = null;
        WEB3MstkBalanceReferenceResponse l_response = null;

        try
        {
            //�����~�j�����c���Ɖ�T�[�r�X���擾
            l_service =
                (WEB3MstkBalanceReferenceService) Services.getService(
                    WEB3MstkBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�����c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MstkBalanceReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "�����~�j�����c���Ɖ�T�[�r�X.get�c���Ɖ�()���\�b�h���s���ɃG���[���������܂����B", 
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (get�c�����v)<BR>
     * <BR>
     * �����~�j�����c�����v�������s���B<BR>
     * <BR>
     * �����~�j�����c���Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �����~�j�����c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MstkBalanceReferenceTotalResponse<BR>
     * @@roseuid 41C2D1CE0381<BR>
     */
    public WEB3MstkBalanceReferenceTotalResponse getBalanceTotal(WEB3MstkBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MstkBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MstkBalanceReferenceService l_service = null;
        WEB3MstkBalanceReferenceTotalResponse l_response = null;

        try
        {
            //�����~�j�����c���Ɖ�T�[�r�X���擾
            l_service =
                (WEB3MstkBalanceReferenceService) Services.getService(
                    WEB3MstkBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3MstkBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����~�j�����c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3MstkBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3MstkBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request, 
                "�����~�j�����c���Ɖ�T�[�r�X.get�c�����v()���\�b�h���s���ɃG���[���������܂����B", 
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
