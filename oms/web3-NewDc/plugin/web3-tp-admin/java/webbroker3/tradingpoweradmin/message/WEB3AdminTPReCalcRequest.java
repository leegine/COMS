head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.39.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力再計算実行Requestクラス(WEB3AdminTPReCalcRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 *  余力再計算リクエストクラス
 */
public class WEB3AdminTPReCalcRequest extends WEB3GenRequest 
{
   
    /**
     * PTYPE
     */
     public static final String PTYPE = "tradingpoweradmin_recalc";

   /**
    * 部店コード
    */
   public String branchCodes;

   /**
    * 対象顧客区分
    */
   public String accountProperty;

   /**
    * 顧客コード(自)
    */
   public String accountCodeSt;

   /**
    * 顧客コード(至)
    */
   public String accountCodeEd;
   
   /**
    */
   public WEB3AdminTPReCalcRequest() 
   {
    
   }
   
   /**
    * 当リクエストデータの整合性チェックを行う。 
    * （ただし、当クラス内で完結する簡易チェックのみとする。） 
    * 
    * [部店コード[] != nullの場合]
    * 部店コード nullチェック、半角数字チェック、数字3桁
    *
    * 対象顧客区分が4:顧客指定の時のみ以下のチェックを行う。
    * [顧客コード(自) != null or 顧客コード(至) != nullの場合]
    * 顧客コード(自) nullチェック、半角数字チェック、数字7桁
    * 顧客コード(至) nullチェック、半角数字チェック、数字7桁
    * 
    */
   public void validate() throws WEB3BusinessLayerException
   {
        final String METHOD_NAME = "validate()";

        //部店コードチェック
        if(branchCodes == null)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, METHOD_NAME);                
        }
  		else if(branchCodes.length() != 3)
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00834, METHOD_NAME);
  	  	try
		{
    		Integer.parseInt(branchCodes);   			
		}
  		catch(NumberFormatException ne)
		{
  			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01729, METHOD_NAME, ne.getMessage());  	  			
		}
  		
        //対象顧客区分が顧客指定のときのみチェック
        if (accountProperty.equals("4"))
        {
            //顧客コード(自)
            if(accountCodeSt == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, METHOD_NAME);
            }
	        if(accountCodeSt.length() != 7)
	            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, METHOD_NAME);
            try
            {
                Integer.parseInt(accountCodeSt);   			
            }
            catch(NumberFormatException ne)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01330, METHOD_NAME, ne.getMessage());	  			
            }

            //顧客コード(至)
            if(accountCodeEd == null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, METHOD_NAME);
            }            
            if(accountCodeEd.length() != 7)
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, METHOD_NAME);               
            try
            {
                Integer.parseInt(accountCodeEd);           
            }
            catch(NumberFormatException ne)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01331, METHOD_NAME, ne.getMessage());              
            }

            //顧客コード(自)＜(至)チェック
            if(accountCodeSt.compareTo(accountCodeEd) > 0)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00795, METHOD_NAME);
            }
            
	    }
        else
        {
            //顧客コード(自)
            if(accountCodeSt != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01936, METHOD_NAME);
            }

            //顧客コード(至)
            if( accountCodeEd != null)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01936, METHOD_NAME);
            }

        }
   }
   
   /**
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse() 
   {
    return new WEB3AdminTPReCalcResponse();
   }
   
   
}
@
