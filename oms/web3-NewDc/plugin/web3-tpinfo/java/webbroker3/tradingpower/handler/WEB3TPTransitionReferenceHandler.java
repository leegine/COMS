head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͐��ډ�ʕ\���n���h���N���X(WEB3TPTransitionReferenceHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceRequest;
import webbroker3.tradingpower.message.WEB3TPTransitionReferenceResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͐��ډ�ʕ\���n���h��)<BR>
 * �]�͐��ډ�ʕ\���n���h���B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPTransitionReferenceHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTransitionReferenceHandler.class);
   
   /**
    * (�R���X�g���N�^)<BR>
    * @@roseuid 41B650A50389
    */
   public WEB3TPTransitionReferenceHandler() 
   {
   }
   
   /**
    * (create�]�͐��ډ��)<BR>
    * �]�͐��ډ�ʕ\���������s���B<BR>
    * <BR>
    * ���Y�]�͏���ʕ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * @@param l_request
    * @@return webbroker3.tradingpower.message.WEB3TPTransitionReferenceResponse)
    * @@roseuid 41B64EB2002E
    */
   public WEB3TPTransitionReferenceResponse transitionReferenceRequest(WEB3TPTransitionReferenceRequest l_request) 
   {
       final String STR_METHOD_NAME = "transitionReferenceRequest";
       log.entering(STR_METHOD_NAME);

       //���Y�]�͏���ʕ\���T�[�r�X
       WEB3TPAssetTradingPowerService l_service = null;
       //�]�͐��ډ�ʕ\�����X�|���X
       WEB3TPTransitionReferenceResponse l_response = null;

       try
       {
           //���Y�]�͏���ʕ\���T�[�r�X�擾
           l_service = (WEB3TPAssetTradingPowerService)Services.getService(WEB3TPAssetTradingPowerService.class);
           //���Y�]�͏���ʕ\���T�[�r�X���s
           l_response =(WEB3TPTransitionReferenceResponse)l_service.execute(l_request);
       } 
       catch (WEB3BaseException ex)
       {
           l_response = (WEB3TPTransitionReferenceResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(WEB3BaseRuntimeException ex)
       {
           l_response = (WEB3TPTransitionReferenceResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(Exception  ex)
       {
           l_response = (WEB3TPTransitionReferenceResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.exiting(STR_METHOD_NAME);
           return l_response;       
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
}
@
