head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAssetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �a�莑�Y��ʕ\���n���h���N���X(WEB3TPAssetHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPAssetRequest;
import webbroker3.tradingpower.message.WEB3TPAssetResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�a�莑�Y��ʕ\���n���h��)<BR>
 * �a�莑�Y��ʕ\���n���h���B<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPAssetHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPAssetHandler.class);
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B64BDC0241
    */
   public WEB3TPAssetHandler() 
   {
    
   }
   
   /**
    * (create�a�莑�Y���)<BR>
    * �a�莑�Y��ʕ\���������s���B<BR>
    * <BR>
    * ���Y�]�͏���ʕ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * @@param l_request
    * @@return webbroker3.tradingpower.message.WEB3TPAssetResponse
    * @@roseuid 41B64A6E03A8
    */
   public WEB3TPAssetResponse assetRequest(WEB3TPAssetRequest l_request) 
   {
       final String STR_METHOD_NAME = "assetRequest";
       log.entering(STR_METHOD_NAME);

       //���Y�]�͏���ʕ\���T�[�r�X
       WEB3TPAssetTradingPowerService l_service = null;
       //�a�莑�Y��ʕ\�����X�|���X
       WEB3TPAssetResponse l_response = null;

       try
       {
           //���Y�]�͏���ʕ\���T�[�r�X�擾
           l_service =
               (WEB3TPAssetTradingPowerService)Services.getService(WEB3TPAssetTradingPowerService.class);
            //���Y�]�͏���ʕ\���T�[�r�X���s
           l_response =(WEB3TPAssetResponse)l_service.execute(l_request);
       } 
       catch (WEB3BaseException ex)
       {
           l_response = (WEB3TPAssetResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(WEB3BaseRuntimeException ex)
       {
           l_response = (WEB3TPAssetResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(Exception  ex)
       {
           l_response = (WEB3TPAssetResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.exiting(STR_METHOD_NAME);
           return l_response;       
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }

}
@
