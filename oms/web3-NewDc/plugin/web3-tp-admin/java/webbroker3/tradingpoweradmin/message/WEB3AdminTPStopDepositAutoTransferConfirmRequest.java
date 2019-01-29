head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録確認リクエストクラス(WEB3AdminTPStopDepositAutoTransferConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * 保証金自動振替停止登録確認リクエストクラス
 */
public class WEB3AdminTPStopDepositAutoTransferConfirmRequest extends WEB3AdminTPStopDepositAutoTransferCommonRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_confirm";

   /**
    * @@roseuid 41DBC979039B
    */
   public WEB3AdminTPStopDepositAutoTransferConfirmRequest()
   {

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97803AB
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPStopDepositAutoTransferConfirmResponse();
   }
}
@
