head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�n���h�� (WEB3BondBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/21 ��іQ (���u) �V�K�쐬
Revesion History : 2007/07/17 �Ӑ� (���u) �d�l�ύX�E���f��208
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondBalanceReferenceRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.bd.service.delegate.WEB3BondBalanceReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���c���Ɖ�n���h��)<BR>
 * ���c���Ɖ�n���h���N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceHandler.class);
    
    /**
     * @@roseuid 44E3362F0399
     */
    public WEB3BondBalanceReferenceHandler() 
    {
     
    }
    
    /**
     * (get�c���Ɖ�)<BR>
     * ���c���Ɖ�������{����B <BR>
     * <BR>
     * ���c���Ɖ�T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3BondBalanceReferenceResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondBalanceReferenceResponse getBalanceReference(WEB3BondBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3BondBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondBalanceReferenceResponse l_response = null;
        WEB3BondBalanceReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3BondBalanceReferenceService)Services.getService(
                    WEB3BondBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���c���Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3BondBalanceReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }

    /**
     * (get�c�����v)<BR>
     * ���c���Ɖ�c�����v���������{����B<BR>
     * <BR>
     * ���c���Ɖ�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���c���Ɖ�c�����v���N�G�X�g<BR>
     * @@return WEB3BondBalanceReferenceTotalResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondBalanceReferenceTotalResponse getBalanceTotal(WEB3BondBalanceReferenceTotalRequest l_request)
    {
        final String STR_METHOD_NAME = " getBalanceTotal(WEB3BondBalanceReferenceTotalRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3BondBalanceReferenceTotalResponse l_response = null;
        WEB3BondBalanceReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3BondBalanceReferenceService)Services.getService(
                    WEB3BondBalanceReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceTotalResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���c���Ɖ�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()���\�b�h���R�[������B
        try
        {
            l_response =
                (WEB3BondBalanceReferenceTotalResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondBalanceReferenceTotalResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
