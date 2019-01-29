head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindDepositAutoTransferStopRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止顧客検索リクエストクラス(WEB3AdminTPFindDepositAutoTransferStopRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 保証金自動振替停止顧客検索リクエストクラス
 */
public class WEB3AdminTPFindDepositAutoTransferStopRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_find_depositautotransfer_stop";

     /**
    * 部店コード
    */
   public String branchCodes[];

   /**
    * 顧客コード
    */
   public String accountCode;

   /**
    * @@roseuid 41DBC97901A7
    */
   public WEB3AdminTPFindDepositAutoTransferStopRequest()
   {

   }

   /**
    * チェック内容：
    *
    * １）部店コードチェック
    * 以下のチェックを行う。
    * １－１）部店コードが以下の条件に該当する場合、
    * 「部店コードエラー」の例外をスローする。
    * 部店コード != nullの場合
    * ・部店コード.length != 3
    * ・部店コード != 数値
    *
    * ２－１）
    * [顧客コード != nullの場合]
    * 部店コード nullチェック、数字3桁
    * 顧客コード 数字6桁
    * @@roseuid 41D252D703BA
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

       //部店は必ず指定されてくる前提なので最初にチェック
  		//部店コードチェック
  		if(branchCodes == null || branchCodes.length == 0)
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);

  		for(int i = 0; i < branchCodes.length; i++)
  		{
  		    if(branchCodes[i].length() != 3)
  		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME);
  	  		try
  			{
  	  			Integer.parseInt(branchCodes[i]);
  			}
  	  		catch(NumberFormatException ne)
  			{
  	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00779, METHOD_NAME, ne.getMessage());
  			}
  		}

  		//顧客コードチェック
  		if(accountCode != null)
  		{
  		    if(accountCode.length() != 6)
  		        throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME);
	  		try
			{
	  			Integer.parseInt(accountCode);
			}
	  		catch(NumberFormatException ne)
			{
	  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00780, METHOD_NAME, ne.getMessage());
			}
  		}


   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC97901F5
    */
   public WEB3GenResponse createResponse()
   {
    return new WEB3AdminTPFindDepositAutoTransferStopResponse();
   }
}
@
