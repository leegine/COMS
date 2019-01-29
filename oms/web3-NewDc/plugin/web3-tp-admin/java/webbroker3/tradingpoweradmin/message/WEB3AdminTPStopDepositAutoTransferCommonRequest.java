head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopDepositAutoTransferCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録共通リクエストクラス(WEB3AdminTPStopDepositAutoTransferCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金自動振替停止登録共通リクエストクラス
 */
public class WEB3AdminTPStopDepositAutoTransferCommonRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_stop_depositautotransfer_common";

   /**
    * 部店コード
    */
   public String branchCode;

   /**
    * 顧客コード
    */
   public String accountCode;

   /**
    * 停止開始日
    */
   public Date autotransferStopStart;

   /**
    * 停止終了日
    */
   public Date autotransferStopEnd;

   /**
    * @@roseuid 41DBC979008E
    */
   public WEB3AdminTPStopDepositAutoTransferCommonRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * １）部店コード、顧客コードチェック
    * 以下のチェックを行う。
    * １－１）部店コードが以下の条件に該当する場合、
    * 「部店コードエラー」の例外をスローする。
    * ・部店コード == null
    * ・部店コード.length != 3
    * ・部店コード != 数値
    *
    * １－２）顧客コードが以下の条件に該当する場合、
    * 「顧客コードエラー」の例外をスローする。
    * ・顧客コード == null
    * ・顧客コード.length != 6
    * ・顧客コード != 数値
    *
    * ２）停止期間のチェック
    * 以下のチェックを行う。
    * ２－１）停止開始日が以下に該当した場合「停止開始日エラー」の例外をスローする。
    * ・停止開始日 == null
    * ・停止開始日 > 停止終了日
    *
    * ２－２）停止終了日が以下に該当した場合「停止終了日エラー」の例外をスローする。
    * ・停止終了日 == null
    * @@roseuid 41D124410072
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

        //１）検索期間指定の場合
        if(this.autotransferStopStart == null)
        {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01900, METHOD_NAME);
        }
        if(this.autotransferStopEnd == null)
        {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01901, METHOD_NAME);
        }

        if(this.autotransferStopStart.after(this.autotransferStopEnd))
        {
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01902, METHOD_NAME);
        }

   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97900EC
    */
   public WEB3GenResponse createResponse()
   {
       return null;
   }
}
@
