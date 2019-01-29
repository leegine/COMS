head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付共通リクエスト(WEB3MutualFixedBuyCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信定時定額買付共通リクエスト)<BR>
 * 投信定時定額買付共通リクエスト<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyCommonRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_common";
    
    /**
     * SerialVersionUID<BR>
     */   
    public final static long serialVersionUID = 200606261701L;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCommonRequest.class);
   
    /**
     * (投信定時定額買付共通情報一覧)<BR>
     * 投信定時定額買付共通情報一覧<BR>
     */
    public WEB3MutualFixedBuyCommonUnit[] commonList;
   
    /**
     * (投信定時定額買付共通リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     */
    public WEB3MutualFixedBuyCommonRequest()
    {
    }
  
    /** 
     * 当リクエストデータの整合性チェックを行う。<BR>  
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>  
     * <BR>
     * １）投信定時定額買付共通情報一覧チェック <BR>
     * 　@　@１－１)　@投信定時定額買付共通情報一覧==nullの場合、例外をスローする。<BR>  
     * 　@　@　@　@　@　@　@（買付条件設定なしエラー） <BR>
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02474 <BR>
     * <BR>
     * 　@　@１－２)　@投信定時定額買付共通情報一覧の要素数==0件の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@（買付条件設定なしエラー）<BR> 
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02474 <BR>
     * <BR>
     * 　@　@１－３)　@投信定時定額買付共通情報一覧の要素数分繰り返してチェックを行う。<BR> 
     * 　@　@　@　@１－３－１）銘柄コード == null の場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@　@　@　@(銘柄コード未指定エラー）<BR> 
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * 　@　@　@　@１－３－２）買付金額（月々） == null の場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@　@　@　@（買付金額入力エラー）<BR> 
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02475 <BR>
     * <BR>
     * 　@　@　@　@１－３－３）買付金額（月々）が数字以外の場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@　@　@　@（数字チェックエラー）<BR> 
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02476 <BR>
     * <BR>
     * 　@　@　@　@１－３－４）買付金額（月々） ≧ 8桁の場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@　@　@　@（買付金額（月々）桁数エラー）<BR>
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02477 <BR> 
     * <BR>
     * 　@　@　@　@１－３－５）買付金額（積み増し） ≠ null の場合、以下のチェックを行なう。<BR> 
     * 　@　@　@　@　@　@１－３－５－１）買付金額（積み増し）が数字以外の場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@（数字チェックエラー）<BR> 
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02478 <BR>
     * <BR>
     * 　@　@　@　@　@　@１－３－５－２）買付金額（積み増し） ≧ 9桁の場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@（買付金額（積み増し）桁数エラー）<BR>
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02479 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);     
        
        //１）投信定時定額買付共通情報一覧チェック 
        //　@１－１)　@投信定時定額買付共通情報一覧==nullの場合、例外をスローする。
        if (this.commonList == null)
        {
            log.debug("投信定時定額買付共通情報一覧==nullの場合、" + 
                "例外をスローする。");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02474,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "");
        }
        
        //１－２)　@投信定時定額買付共通情報一覧の要素数==0件の場合、例外をスローする。
        if (this.commonList.length == 0)
        {
        	 log.debug("投信定時定額買付共通情報一覧の要素数==0件の場合、" + 
                 "例外をスローする。");
        	 log.exiting(STR_METHOD_NAME); 
        	 throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02474,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "");
        }
        
        //１－３)　@投信定時定額買付共通情報一覧の要素数分繰り返してチェックを行う。
        //　@　@　@１－３－１）銘柄コード == null の場合、例外をスローする。
        for (int i = 0; i < this.commonList.length; i++)
        {
            if (this.commonList[i].mutualProductCode == null)
            {
            	log.debug("銘柄コード == null の場合、例外をスローする。");
            	log.exiting(STR_METHOD_NAME); 
            	throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");       
            }
            
            //１－３－２）買付金額（月々） == null の場合、例外をスローする。
            if (this.commonList[i].monthlyBuyAmount == null)
            {
            	log.debug("買付金額（月々） == null の場合、例外をスローする。");
            	log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02475,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");  
            }
            
            //１－３－３）買付金額（月々）が数字以外の場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.commonList[i].monthlyBuyAmount))
            {
            	log.debug("買付金額（月々）が数字以外の場合、例外をスローする。");
            	log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02476,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");  
            }
            
            //１－３－４）買付金額（月々） ≧ 8桁の場合、例外をスローする。
            int l_intMonthlyBuyAmoutLen = this.commonList[i].monthlyBuyAmount.length();
            if (l_intMonthlyBuyAmoutLen >= 8)
            {
            	log.debug("買付金額（月々） ≧ 8桁の場合、例外をスローする。");
            	log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02477,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
            
            //１－３－５）買付金額（積み増し） ≠ null の場合、以下のチェックを行なう。
            //１－３－５－１）買付金額（積み増し）が数字以外の場合、例外をスローする。
            if (this.commonList[i].increaseBuyAmount != null)
            {
               	if (!WEB3StringTypeUtility.isDigit(this.commonList[i].increaseBuyAmount))
               	{
            		log.debug("買付金額（積み増し）が数字以外の場合、例外をスローする。");
            		log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02478,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "");
               	}
               	
               	//１－３－５－２）買付金額（積み増し） ≧ 9桁の場合、例外をスローする。
               	int l_intIncreaseBuyAmountLen = this.commonList[i].increaseBuyAmount.length();
               	if (l_intIncreaseBuyAmountLen >= 9)
               	{
               		log.debug("買付金額（積み増し） ≧ 9桁の場合、例外をスローする。");
               		log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02479,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "");
               	}
            }
        }
        log.exiting(STR_METHOD_NAME);             
    }
  
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }        
}
@
