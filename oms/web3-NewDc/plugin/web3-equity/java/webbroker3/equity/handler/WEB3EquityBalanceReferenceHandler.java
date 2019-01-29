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
filename	WEB3EquityBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������c���Ɖ�n���h��(WEB3EquityBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityBalanceReferenceRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceResponse;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceTotalResponse;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * �i���������c���Ɖ�n���h���j�B<BR>
 * <BR>
 * ���������c���Ɖ�n���h���N���X<BR>
 */
public class WEB3EquityBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBalanceReferenceHandler.class);
        
    /**
     * @@roseuid 4206CC4A001B<BR>
     */
    public WEB3EquityBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get�c���Ɖ�)<BR>
     * <BR>
     * ���������c���Ɖ�����s���B<BR>
     * <BR>
     * ���������c���Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) ���������c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3EquityBalanceReferenceResponse<BR>
     * �X<BR>
     * @@roseuid 41B58FEF002C<BR>
     */
    public WEB3EquityBalanceReferenceResponse getBalanceReference(WEB3EquityBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3EquityBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBalanceReferenceService l_service = null;
        WEB3EquityBalanceReferenceResponse l_response = null;

        try
        {
            //���������c���Ɖ�T�[�r�X���擾
            l_service =
                (WEB3EquityBalanceReferenceService) Services.getService(
                    WEB3EquityBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���������c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "���������c���Ɖ�Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityBalanceReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "���������c���Ɖ�Ɏ��s���܂����B", l_bre);
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
     * ���������c�����v�������s���B<BR>
     * <BR>
     * ���������c���Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) ���������c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3EquityBalanceReferenceTotalResponse<BR>
     * @@roseuid 41B59034027D<BR>
     */
    public WEB3EquityBalanceReferenceTotalResponse getBalanceTotal(WEB3EquityBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3EquityBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityBalanceReferenceService l_service = null;
        WEB3EquityBalanceReferenceTotalResponse l_response = null;

        try
        {
            //���������c���Ɖ�T�[�r�X���擾
            l_service =
                (WEB3EquityBalanceReferenceService) Services.getService(
                    WEB3EquityBalanceReferenceService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, "���������c���Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B", l_response.errorInfo, e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
                
        try
        {        
            //execute()���\�b�h���R�[������B
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "���������c�����v�Ɏ��s���܂����B", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityBalanceReferenceTotalResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "���������c�����v�Ɏ��s���܂����B", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
            
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
