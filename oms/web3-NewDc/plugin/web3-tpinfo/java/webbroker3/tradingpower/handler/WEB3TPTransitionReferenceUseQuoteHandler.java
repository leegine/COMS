head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceUseQuoteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͐���(�����v�Z)��ʕ\���n���h���N���X(WEB3TPTransitionReferenceUseQuoteHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/01/31 �V�K�쐬
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceUseQuoteResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͐���(�����v�Z)��ʕ\���n���h��)<BR>
 * �]�͐���(�����v�Z)��ʕ\���n���h���B<BR>
 * 
 */
public class WEB3TPTransitionReferenceUseQuoteHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTransitionReferenceUseQuoteHandler.class);
   
   /**
    * (�R���X�g���N�^)
    */
   public WEB3TPTransitionReferenceUseQuoteHandler() 
   {
   }
   
   /**
    * (create�]�͐���(�����v�Z)���)<BR>
    * �]�͐���(�����v�Z)��ʕ\���������s���B<BR>
    * <BR>
    * ���Y�]�͏���ʕ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * @@param l_request
    * @@return WEB3TPTransitionReferenceUseQuoteResponse)
    */
   public WEB3TPTransitionReferenceUseQuoteResponse transitionReferenceUseQuoteRequest(WEB3TPTransitionReferenceUseQuoteRequest l_request) 
   {
       final String STR_METHOD_NAME = "transitionReferenceUseQuoteRequest";
       log.entering(STR_METHOD_NAME);

       //���Y�]�͏���ʕ\���T�[�r�X
       WEB3TPAssetTradingPowerService l_service = null;
       //�]�͐���(�����v�Z)��ʕ\�����N�G�X�g
       WEB3TPTransitionReferenceUseQuoteResponse l_response = null;

       try
       {
           //���Y�]�͏���ʕ\���T�[�r�X�擾
           l_service = (WEB3TPAssetTradingPowerService)Services.getService(WEB3TPAssetTradingPowerService.class);
           //���Y�]�͏���ʕ\���T�[�r�X���s
           l_response =(WEB3TPTransitionReferenceUseQuoteResponse)l_service.execute(l_request);
       } 
       catch (WEB3BaseException ex)
       {
           l_response = (WEB3TPTransitionReferenceUseQuoteResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(WEB3BaseRuntimeException ex)
       {
           l_response = (WEB3TPTransitionReferenceUseQuoteResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(Exception  ex)
       {
           l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.exiting(STR_METHOD_NAME);
           return l_response;       
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
}
@
