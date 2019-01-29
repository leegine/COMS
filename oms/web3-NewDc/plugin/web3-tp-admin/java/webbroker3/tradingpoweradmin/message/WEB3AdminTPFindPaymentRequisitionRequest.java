head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindPaymentRequisitionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求検索リクエストクラス(WEB3AdminTPFindPaymentRequisitionRequest.java)
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
 * 入金請求検索リクエストクラス
 */
public class WEB3AdminTPFindPaymentRequisitionRequest extends WEB3GenRequest
{
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_find_paymentrequisition";


     /**
      * 部店コード
      */
     public String[] branchCodes;

   /**
    * 顧客コード
    */
   public String accountCode;

   /**
    * 検索開始日
    */
   public Date startDate;

   /**
    * 検索終了日
    */
   public Date endDate;

   /**
    * 入金請求区分
    */
   public String[] paymentRequisitionDivisions;

   /**
    * 実績区分
    */
   public String[] requisitionStatuses;

   /**
    * 計算元区分
    */
   public String[] calclationSources;

   /**
    * @@roseuid 41DE22F90100
    */
   public WEB3AdminTPFindPaymentRequisitionRequest()
   {

   }

   /**
    * 当リクエストデータの整合性チェックを行う。
　@　@* （ただし、当クラス内で完結する簡易チェックのみとする。）
    *１）部店コードチェック
    * this.部店コードが以下の条件に該当する場合、
    * 「部店コードエラー」の例外をスローする。
    * ・this.部店コード == null
    * ・this.部店コード.length != 3
    * ・this.部店コード != 数値
    *
    * ２）顧客指定の場合(顧客コード  != nullのとき)
    * this.顧客コードが以下の条件に該当する場合、
    * 「顧客コードエラー」の例外をスローする。
    * ・this.顧客コード.length != 6
    * ・this.顧客コード != 数値
    *
    * ３）検索期間指定の場合
    * (検索開始日 != null && 検索終了日 != nullのとき)
    * 以下に該当した場合「検索期間の指定が不正」の例外をスローする。
    * ・検索開始日 > 検索終了日
    *
    * @@roseuid 41BD68E101CC
    */
   public void validate() throws WEB3BusinessLayerException
   {
       final String METHOD_NAME = "validate()";

 		//１）部店コードチェック
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

        //２）顧客指定の場合
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

        //３）検索期間指定の場合
        if(this.startDate != null && this.endDate != null)
        {
            if(startDate.after(endDate))
            {
      			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01899, METHOD_NAME);
            }
        }


   }

   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41DE22F9019C
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminTPFindPaymentRequisitionResponse();
   }


}
@
