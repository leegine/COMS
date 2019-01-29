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
filename	WEB3BondRecruitBuyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�n���h��(WEB3BondRecruitBuyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/7 �s�p (���u) �V�K�쐬 
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteRequest;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmRequest;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyService;

/**
 * (������/���t�n���h��)<BR>
 * ������/���t�n���h��<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3BondRecruitBuyHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyHandler.class); 
    
    /**
     * @@roseuid 44FBFD3A038A
     */
    public WEB3BondRecruitBuyHandler() 
    {
     
    }
    
    /**
     * (������/���t�m�F)<BR>
     * ������/���t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse
     * @@roseuid 44C82BE6021A
     */
    public WEB3BondApplyBuyConfirmResponse confirmBondRecruitBuy(WEB3BondApplyBuyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmBondRecruitBuy(WEB3BondApplyBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondApplyBuyConfirmResponse l_response = null;
        WEB3BondRecruitBuyService l_service = null;
        
        try
        {
            //������/���t�T�[�r�X�̎擾
            l_service = (WEB3BondRecruitBuyService)
                Services.getService(WEB3BondRecruitBuyService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3BondApplyBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������/���t�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3BondApplyBuyConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3BondApplyBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "������/���t�m�F�����̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (������/���t����)<BR>
     * ������/���t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^ 
     * @@return webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse
     * @@roseuid 44C82BFD0362
     */
    public WEB3BondApplyBuyCompleteResponse completeBondRecruitBuy(WEB3BondApplyBuyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeBondRecruitBuy(WEB3BondApplyBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondApplyBuyCompleteResponse l_response = null;
        WEB3BondRecruitBuyService l_service = null;
        
        try
        {
            //������/���t�T�[�r�X�̎擾
            l_service = (WEB3BondRecruitBuyService)
                Services.getService(WEB3BondRecruitBuyService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3BondApplyBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������/���t�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3BondApplyBuyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3BondApplyBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "������/���t���������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
