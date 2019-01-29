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
filename	WEB3BondRecruitBuyProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�����ꗗ�n���h��(WEB3BondRecruitBuyProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 �s�p (���u) �V�K�쐬 
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.bd.message.WEB3BondApplyBuyProductListRequest;
import webbroker3.bd.message.WEB3BondApplyBuyProductListResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyProductListService;

/**
 * (������/���t�����ꗗ�n���h��)<BR>
 * ������/���t�����ꗗ�n���h��<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3BondRecruitBuyProductListHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyProductListHandler.class);  
    
    /**
     * @@roseuid 44FBFD3A0290
     */
    public WEB3BondRecruitBuyProductListHandler() 
    {
     
    }
    
    /**
     * (������/���t�����ꗗ)<BR>
     * ������/���t�����ꗗ�������s���B <BR>
     * <BR>
     * ������/���t�����ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3BondApplyBuyProductListResponse
     * @@roseuid 44B757CE0249
     */
    public WEB3BondApplyBuyProductListResponse bondRecruitBuyProductList(WEB3BondApplyBuyProductListRequest l_request) 
    {
        final String STR_METHOD_NAME = " bondRecruitBuyProductList(WEB3BondApplyBuyProductListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondApplyBuyProductListResponse l_response = null;
        WEB3BondRecruitBuyProductListService l_service = null;
        
        try
        {
            //������/���t�����ꗗ�T�[�r�X�̎擾
            l_service = (WEB3BondRecruitBuyProductListService)
                Services.getService(WEB3BondRecruitBuyProductListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3BondApplyBuyProductListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������/���t�����ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3BondApplyBuyProductListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3BondApplyBuyProductListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "������/���t�����ꗗ�����̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
