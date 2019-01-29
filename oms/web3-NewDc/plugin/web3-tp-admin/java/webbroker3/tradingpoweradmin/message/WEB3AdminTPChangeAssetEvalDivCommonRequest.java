head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTPChangeAssetEvalDivCommonRequestクラス(WEB3AdminTPChangeAssetEvalDivCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 余力計算方法@変更共通リクエストクラス
 */
public class WEB3AdminTPChangeAssetEvalDivCommonRequest extends WEB3GenRequest
{
    public static final String PTYPE = "tradingpoweradmin_change_assetevaldiv_common";

   /**
    * (部店コード)
    */
   public String branchCode;

   /**
    * (顧客コード)
    */
   public String accountCode;

   /**
    * (預り証券評価制区分)
    */
   public String assetEvalDiv;

   /**
    * @@roseuid 41DBC14F0083
    */
   public WEB3AdminTPChangeAssetEvalDivCommonRequest()
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
    *
    * ２）預り証券評価制区分のチェック
    * 　@以下に該当する場合「預り証券評価制区分がnull」の例外をスローする。
    * 　@　@　@　@　@・this.預り証券評価制区分 == null
    * @@roseuid 41D23C74010A
    */
   public void validate() throws WEB3BusinessLayerException

   {
  		final String METHOD_NAME = "validate";

  		//１）部店コード、顧客コードチェック

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

   		//２）預り証券評価制区分のチェック
   		if(assetEvalDiv == null)
   		{
   			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01891, METHOD_NAME);
   		}
   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DBC20300D2
    */
   public WEB3GenResponse createResponse()
   {
       return null;
   }


}
@
