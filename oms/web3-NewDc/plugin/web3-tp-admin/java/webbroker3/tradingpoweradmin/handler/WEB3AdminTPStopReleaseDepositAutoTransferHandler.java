head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopReleaseDepositAutoTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋������U�֒�~�o�^/�����n���h���N���X(WEB3AdminTPStopReleaseDepositAutoTransferHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : ${date} �x��a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPStopReleaseDepositAutoTransferService;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRequest;

/**
 * �ۏ؋������U�֒�~�o�^/�����n���h���N���X
 */
public class WEB3AdminTPStopReleaseDepositAutoTransferHandler implements MessageHandler
{
    WEB3AdminTPStopReleaseDepositAutoTransferService service;

   /**
    * @@roseuid 41DBCAAD01A7
    */
   public WEB3AdminTPStopReleaseDepositAutoTransferHandler()
   {
       service = (WEB3AdminTPStopReleaseDepositAutoTransferService)Services.getService(WEB3AdminTPStopReleaseDepositAutoTransferService.class);
   }

   /**
    * (get�ۏ؋������U�֒�~�o�^���͉��)
    * �ۏ؋������U�֒�~�o�^���͉�ʎ擾�������s���B
    *
    * �ۏ؋������U�֒�~/�����T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �ۏ؋������U�֒�~�o�^���͉�ʃ��N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResp
    * onse
    * @@roseuid 41BE508003B7
    */
   public WEB3AdminTPStopDepositAutoTransferInputResponse stopDepositAutoTransferInputRequest(WEB3AdminTPStopDepositAutoTransferInputRequest l_request)
   {
       WEB3AdminTPStopDepositAutoTransferInputResponse l_response;
       try
       {
           l_response = (WEB3AdminTPStopDepositAutoTransferInputResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPStopDepositAutoTransferInputResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (validate�ۏ؋������U�֒�~�o�^)
    * �ۏ؋������U�֒�~�o�^�m�F�������s���B
    *
    * �ۏ؋������U�֒�~/�����T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �ۏ؋������U�֒�~�o�^�m�F���N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRe
    * sponse
    * @@roseuid 41C91E280038
    */
   public WEB3AdminTPStopDepositAutoTransferConfirmResponse stopDepositAutoTransferConfirmRequest(WEB3AdminTPStopDepositAutoTransferConfirmRequest l_request)
   {
       WEB3AdminTPStopDepositAutoTransferConfirmResponse l_response;
       try
       {
           l_response = (WEB3AdminTPStopDepositAutoTransferConfirmResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPStopDepositAutoTransferConfirmResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (submit�ۏ؋������U�֒�~�o�^)
    * �ۏ؋������U�֒�~�o�^�����������s���B
    *
    * �ۏ؋������U�֒�~/�����T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �ۏ؋������U�֒�~�o�^�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteR
    * esponse
    * @@roseuid 41BE508E006B
    */
   public WEB3AdminTPStopDepositAutoTransferCompleteResponse stopDepositAutoTransferCompleteRequest(WEB3AdminTPStopDepositAutoTransferCompleteRequest l_request)
   {
       WEB3AdminTPStopDepositAutoTransferCompleteResponse l_response;
       try
       {
           l_response = (WEB3AdminTPStopDepositAutoTransferCompleteResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPStopDepositAutoTransferCompleteResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (get�ۏ؋������U�֒�~�ڋq��������)
    * �ۏ؋������U�֒�~�ڋq�����������s���B
    *
    * �ۏ؋������U�֒�~/�����T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �ۏ؋������U�֒�~�ڋq�������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRespo
    * nse
    * @@roseuid 41BE505802FB
    */
   public WEB3AdminTPFindDepositAutoTransferStopResponse findDepositAutoTransferStopRequest(WEB3AdminTPFindDepositAutoTransferStopRequest l_request)
   {
       WEB3AdminTPFindDepositAutoTransferStopResponse l_response;
       try
       {
           l_response = (WEB3AdminTPFindDepositAutoTransferStopResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPFindDepositAutoTransferStopResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (validate�ۏ؋������U�֒�~����)
    * �ۏ؋������U�֒�~�����m�F�������s���B
    *
    * �ۏ؋������U�֒�~/�����T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �ۏ؋������U�֒�~�����m�F���N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfir
    * mResponse
    * @@roseuid 41BE5096026F
    */
   public WEB3AdminTPReleaseDepositAutoTransferConfirmResponse releaseDepositAutoTransferConfirmRequest(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest l_request)
   {
       WEB3AdminTPReleaseDepositAutoTransferConfirmResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReleaseDepositAutoTransferConfirmResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReleaseDepositAutoTransferConfirmResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (submit�ۏ؋������U�֒�~����)
    * �ۏ؋������U�֒�~���������������s���B
    *
    * �ۏ؋������U�֒�~/�����T�[�r�XImpl���擾���A
    * execute()���\�b�h���R�[������B
    * @@param l_request - �ۏ؋������U�֒�~�����������N�G�X�g
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferComple
    * teResponse
    * @@roseuid 41BE50D502FB
    */
   public WEB3AdminTPReleaseDepositAutoTransferCompleteResponse releaseDepositAutoTransferCompleteRequest(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest l_request)
   {
       WEB3AdminTPReleaseDepositAutoTransferCompleteResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReleaseDepositAutoTransferCompleteResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReleaseDepositAutoTransferCompleteResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
