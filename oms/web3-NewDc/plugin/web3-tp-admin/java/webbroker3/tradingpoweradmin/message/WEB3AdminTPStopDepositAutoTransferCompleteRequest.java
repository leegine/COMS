head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録完了リクエストクラス(WEB3AdminTPStopDepositAutoTransferCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 保証金自動振替停止登録完了リクエストクラス
 */
public class WEB3AdminTPStopDepositAutoTransferCompleteRequest extends WEB3AdminTPStopDepositAutoTransferCommonRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_complete";

   /**
    * 管理者パスワード
    */
   public String adminPassword;

   /**
    * @@roseuid 41DBC97A0040
    */
   public WEB3AdminTPStopDepositAutoTransferCompleteRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * １）登録内容チェック
    *  親クラス.validate()を呼ぶ。
    *
    * ２）管理者パスワードのチェック
    * 以下に該当する場合「暗証番号が未指定です。」の例外をスローする。
    * ・管理者パスワード == null
    *
    *
    * @@roseuid 41C6B73D0038
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";
       super.validate();
  		if(adminPassword == null)
  		{
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01090, METHOD_NAME);
  		}
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97803AB
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPStopDepositAutoTransferCompleteResponse();
   }
}
@
