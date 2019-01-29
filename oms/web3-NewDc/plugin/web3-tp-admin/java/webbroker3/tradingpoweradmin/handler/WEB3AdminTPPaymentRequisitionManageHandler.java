head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ������n���h���N���X(WEB3AdminTPPaymentRequisitionManageHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionManageService;


/**
 * ���������Ǘ������n���h��
 */
public class WEB3AdminTPPaymentRequisitionManageHandler implements MessageHandler
{
    WEB3AdminTPPaymentRequisitionManageService service;
    
    /**
     * @@roseuid 4412A9800296
     */
    public WEB3AdminTPPaymentRequisitionManageHandler() 
    {
    	service = (WEB3AdminTPPaymentRequisitionManageService)Services.getService(WEB3AdminTPPaymentRequisitionManageService.class);
    }
    
    /**
     * (get���������Ǘ���������)
     *
     * ���������Ǘ������������s���B
     *
     * this.get���������Ǘ���������()���\�b�h���R�[������B
     * @@param l_request - ���������Ǘ����N�G�X�g
     * @@return WEB3AdminTPPaymentRequisitionManageResponse
     * @@roseuid 41BD555800A3
     */
    public WEB3AdminTPPaymentRequisitionManageSearchResponse WEB3AdminTPPaymentRequisitionManageSearchRequest(WEB3AdminTPPaymentRequisitionManageSearchRequest l_request)
    {
    	WEB3AdminTPPaymentRequisitionManageSearchResponse l_response;
        try
        {
            l_response = (WEB3AdminTPPaymentRequisitionManageSearchResponse)service.execute(l_request);
        }
        catch(WEB3BaseException e)
        {
            l_response =  new WEB3AdminTPPaymentRequisitionManageSearchResponse();
            l_response.errorInfo = e.getErrorInfo();
        }
        return l_response;
    }

    public WEB3AdminTPPaymentRequisitionManageHistoryResponse WEB3AdminTPPaymentRequisitionManageHistoryRequest(WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request)
    {
    	WEB3AdminTPPaymentRequisitionManageHistoryResponse l_response;
        try
        {
            l_response = (WEB3AdminTPPaymentRequisitionManageHistoryResponse)service.execute(l_request);
        }
        catch(WEB3BaseException e)
        {
            l_response =  new WEB3AdminTPPaymentRequisitionManageHistoryResponse();
            l_response.errorInfo = e.getErrorInfo();
        }
        return l_response;
    }

}
@
