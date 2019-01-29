head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEquityTradingPowerDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���������t�]�͏ڍ׉�ʕ\���n���h���N���X(WEB3TPEquityTradingPowerDetailHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���������t�]�͏ڍ׉�ʕ\���n���h��)<BR>
 * ���������t�]�͏ڍ׉�ʕ\���n���h���B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPEquityTradingPowerDetailHandler implements MessageHandler 
{
    
    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPEquityTradingPowerDetailHandler.class);
   
   /**
    * (�R���X�g���N�^)<BR>
    * @@roseuid 41B650A6005D
    */
   public WEB3TPEquityTradingPowerDetailHandler() 
   {
    
   }
   
   /**
    * (create���������t�]�͏ڍ׉��)<BR>
    * ���������t�]�͏ڍ׉�ʕ\���������s���B<BR>
    * <BR>
    * ���Y�]�͏���ʕ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * @@param l_request
    * @@return WEB3TPEquityTradingPowerDetailResponse
    * @@roseuid 41B64F9A0260
    */ 
   public WEB3TPEquityTradingPowerDetailResponse equityTradingPowerDetailRequest(WEB3TPEquityTradingPowerDetailRequest l_request) 
   {
       final String STR_METHOD_NAME = "equityTradingPowerDetailRequest";
       log.entering(STR_METHOD_NAME);

       //���Y�]�͏���ʕ\���T�[�r�X
       WEB3TPAssetTradingPowerService l_service = null;
       //���������t�]�͏ڍ׉�ʕ\�����X�|���X
       WEB3TPEquityTradingPowerDetailResponse l_response = null;

       try
       {
           //���Y�]�͏���ʕ\���T�[�r�X�擾
           l_service =
               (WEB3TPAssetTradingPowerService)Services.getService(WEB3TPAssetTradingPowerService.class);
           //���Y�]�͏���ʕ\���T�[�r�X���s
           l_response =(WEB3TPEquityTradingPowerDetailResponse)l_service.execute(l_request);
       } 
       catch (WEB3BaseException ex)
       {
           l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(WEB3BaseRuntimeException ex)
       {
           l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(Exception  ex)
       {
           l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.exiting(STR_METHOD_NAME);
           return l_response;       
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }

}
@
