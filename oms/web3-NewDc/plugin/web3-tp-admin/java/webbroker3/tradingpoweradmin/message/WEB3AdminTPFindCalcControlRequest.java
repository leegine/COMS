head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindCalcControlRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力制御機@能検索リクエストクラス(WEB3AdminTPFindCalcControlRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
Revision History : 2007/07/26 趙林鵬 (中訊) モデル：No.004
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力制御機@能検索リクエストクラス
 */
public class WEB3AdminTPFindCalcControlRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_find_calccontrol";

   /**
    * 部店コード
    */
   public String[] branchCodes;

   /**
    * 顧客コード
    */
   public String accountCode;

   /**
    * 取引停止区分
    */
   public String tradingStop;

   /**
    * 信用新規建余力区分
    */
   public String marginOpenPositionStop;

   /**
    * 先物OP新規建余力区分
    */
   public String ifoOpenPositionStop;

   /**
    * 出金余力区分
    */
   public String paymentStop;

   /**
    * その他商品買付区分
    */
   public String otherTradingStop;

   /**
    * (追証未入金区分)<BR>
    * 0:追証未入金なし 1:追証未入金あり<BR>
    */
   public String additionalDepositStop;

   /**
    * @@roseuid 41DBC92701F5
    */
   public WEB3AdminTPFindCalcControlRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * [部店コード[] != nullの場合]
    * 部店コード nullチェック、数字3桁

    * [顧客コード != nullの場合]
    * 部店コード nullチェック、数字3桁
    * 顧客コード 数字6桁
    *
    * [[取引停止区分 != nullの場合]
    * 取引停止区分 = ”余力不可”
    *
    *[信用新規建余力区分 != nullの場合]
    *信用新規建余力区分 = ”余力不可”
    *
    *[先物OP新規建余力区分 != nullの場合]
    *先物OP新規建余力区分 = ”余力不可”
    *
    *[出金余力区分 != nullの場合]
    *出金余力区分 = ”余力不可”
    *
    *[その他商品買付余力区分 != nullの場合]
    *その他商品買付余力区分 = ”余力不可”
    *
    * @@roseuid 41B9348803B3
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
    * @@roseuid 41DBC9270253
    */
   public WEB3GenResponse createResponse()
   {
    return new WEB3AdminTPFindCalcControlResponse();
   }


}
@
