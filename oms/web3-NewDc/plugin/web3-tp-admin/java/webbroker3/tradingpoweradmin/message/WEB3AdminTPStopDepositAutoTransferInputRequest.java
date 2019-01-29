head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録入力画面リクエストクラス(WEB3AdminTPStopDepositAutoTransferInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金自動振替停止登録入力画面リクエストクラス
 */
public class WEB3AdminTPStopDepositAutoTransferInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_input";

   /**
    * 部店コード
    */
   public String branchCode;

   /**
    * 顧客コード
    */
   public String accountCode;

   /**
    * @@roseuid 41DBC978035D
    */
   public WEB3AdminTPStopDepositAutoTransferInputRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * １）部店コード、顧客コードチェック
    * 以下のチェックを行う。
    * １－１）this.部店コードが以下の条件に該当する場合、
    * 「部店コードエラー」の例外をスローする。
    * ・this.部店コード == null
    * ・this.部店コード.length != 3
    * ・this.部店コード != 数値
    *
    * １－２）this.顧客コードが以下の条件に該当する場合、
    * 「顧客コードエラー」の例外をスローする。
    * ・this.顧客コード == null
    * ・this.顧客コード.length != 6
    * ・this.顧客コード != 数値
    * @@roseuid 41BE420D030B
    */
   public void validate() throws WEB3BusinessLayerException
   {
   		final String METHOD_NAME = "validate";

   		//部店コードチェック
   		if(branchCode == null ||
   				branchCode.length() != 3)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);
   		}
   		try
		{
   			Integer.parseInt(branchCode);
		}
   		catch(NumberFormatException ne)
		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME, ne.getMessage());

		}

   		//顧客コードチェック
   		if(accountCode == null ||
   				accountCode.length() != 6)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME);
   		}
   		try
		{
   			Integer.parseInt(accountCode);
		}
   		catch(NumberFormatException ne)
		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME, ne.getMessage());

		}

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97803AB
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPStopDepositAutoTransferInputResponse();
   }

}
@
