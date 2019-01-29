head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPChangeAssetEvalDivInputRequestクラス(WEB3AdminTPChangeAssetEvalDivInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力計算方法@変更入力画面リクエストクラス
 */
public class WEB3AdminTPChangeAssetEvalDivInputRequest extends WEB3GenRequest
{

    public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_input";

   /**
    * 部店コード
    */
   public String branchCode;

   /**
    * 顧客コード
    */
   public String accountCode;

   /**
    * @@roseuid 41DBB1DE0087
    */
   public WEB3AdminTPChangeAssetEvalDivInputRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
    * （ただし、当クラス内で完結する簡易チェックのみとする。）
    *
    * １）部店コード、顧客コードチェック
    * 　@以下のチェックを行う。
    * 　@１－１）this.部店コードが以下の条件に該当する場合、
    * 　@　@　@　@　@「部店コードエラー」の例外をスローする。
    * 　@　@　@　@　@・this.部店コード == null
    * 　@　@　@　@　@・this.部店コード.length != 3
    * 　@　@　@　@　@・this.部店コード != 数値
    *
    * 　@１－２）this.顧客コードが以下の条件に該当する場合、
    * 　@　@　@　@　@「顧客コードエラー」の例外をスローする。
    * 　@　@　@　@　@・this.顧客コード == null
    * 　@　@　@　@　@・this.顧客コード.length != 6
    * 　@　@　@　@　@・this.顧客コード != 数値
    * @@roseuid 41BD464400C2
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
    * @@roseuid 41DBB1DE01CF
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPChangeAssetEvalDivInputResponse();
   }

   /**
    * このクラスの文字列表現を返す。
    * @@return String
    */
   public String toString()
   {

       StringBuffer l_sb = new StringBuffer("WEB3AdminTPChangeAssetEvalDivInputRequest={");
//       l_sb.append(super.toString());
       l_sb.append("branchCode=").append(branchCode);
       l_sb.append(",accountCode=").append(accountCode);
       l_sb.append("}");

       return l_sb.toString();

   }
}
@
