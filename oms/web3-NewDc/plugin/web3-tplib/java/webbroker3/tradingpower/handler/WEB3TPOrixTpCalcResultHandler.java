head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixTpCalcResultHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͌v�Z���ʃn���h���N���X(WEB3TPOrixTpCalcResultHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) �V�K�쐬
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultRequest;
import webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPOrixVantiveService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�͌v�Z���ʃn���h��)<BR>
 * �]�͌v�Z���ʃn���h���B<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public class WEB3TPOrixTpCalcResultHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPOrixTpCalcResultHandler.class);
   
   /**
    * (�R���X�g���N�^)
    */
   public WEB3TPOrixTpCalcResultHandler() 
   {
    
   }
   
   /**
    * (create�]�͌v�Z����)<BR>
    * �]�͌v�Z���ʏ������s���B<BR>
    * <BR>
    * Vantive�A�g�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
    * @@param l_request
    * @@return webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultResponse
    */
   public WEB3TPOrixTpCalcResultResponse orixTpCalcResultRequest(WEB3TPOrixTpCalcResultRequest l_request) 
   {
       final String STR_METHOD_NAME = "orixTpCalcResultRequest";
       log.entering(STR_METHOD_NAME);

       //Vantive�A�g�T�[�r�X
       WEB3TPOrixVantiveService l_service = null;
       //�]�͌v�Z���ʃ��X�|���X
       WEB3TPOrixTpCalcResultResponse l_response = null;

       try
       {
           //Vantive�A�g�T�[�r�X�擾
           l_service =
               (WEB3TPOrixVantiveService)Services.getService(WEB3TPOrixVantiveService.class);
            //Vantive�A�g�T�[�r�X���s
           l_response = (WEB3TPOrixTpCalcResultResponse)l_service.execute(l_request);
       } 
       catch (WEB3BaseException ex)
       {
           l_response = (WEB3TPOrixTpCalcResultResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           ex.printStackTrace();
           return l_response;
       }
       catch(WEB3BaseRuntimeException ex)
       {
           l_response = (WEB3TPOrixTpCalcResultResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           ex.printStackTrace();
           return l_response;
       }
       catch(Exception  ex)
       {
           l_response = (WEB3TPOrixTpCalcResultResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.exiting(STR_METHOD_NAME);
           ex.printStackTrace();
           return l_response;       
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }

}
@
