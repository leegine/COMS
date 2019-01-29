head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : その他件数照会リクエスト(WEB3AdminAioOtherCountReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 韋念瓊(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioOtherOrderProductDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (その他件数照会リクエスト)<BR>
 * その他件数照会リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceRequest extends WEB3GenRequest 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioOtherCountReferenceRequest.class);  
    
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_other_count_reference";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;
    
    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード
     */
    public String[] branchCodeList;
    
    /**
     * (商品区分)<BR>
     * 画面にて選択された商品区分 <BR>
     * <BR>
     * 1:オンライン入金　@ <BR>
     * 2:為替保証金　@ <BR>
     * 3:外国株式（外部連携）<BR>
     */
    public String commodityDiv;
    
    /**
     * (決済機@関ID)<BR>
     * 画面にて選択された決済機@関のID <BR>
     * 商品区分に金融機@関連携が選択された場合、必須。 <BR>
     */
    public String paySchemeId;
    
    /** 
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １）部店コードチェック <BR>
     * <BR>
     * リクエストデータ.部店コード = null or <BR>
     * リクエストデータ.部店コード.length() != 3 <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * ２）商品区分チェック <BR>
     * <BR>
     * リクエストデータ.商品区分 = null <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02182<BR>
     * <BR>
     * ３）決済機@関IDチェック <BR>
     * <BR>
     * リクエストデータ.商品区分 = オンライン入金の場合で <BR>
     * リクエストデータ.決済機@関ID = null or <BR>
     * リクエストデータ.決済機@関ID.length() != 11 or <BR>
     * リクエストデータ.決済機@関ID.startsWith("ComOndebi") = false <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00767<BR>
     * <BR>
     */
   public void validate() throws WEB3BaseException
   {
       final String STR_METHOD_NAME = "validate()";
       log.entering(STR_METHOD_NAME);
       
       //１）部店コードチェック 
       //リクエストデータ.部店コード = null or 
       //リクエストデータ.部店コード.length() != 3 
       //の場合、例外をスローする。 
       if(this.branchCodeList == null || 
          this.branchCodeList.length ==0)
       {
    		log.exiting(STR_METHOD_NAME);
	    	throw new WEB3BusinessLayerException(
		    	WEB3ErrorCatalog.BUSINESS_ERROR_00833,
			    this.getClass().getName() + "." + STR_METHOD_NAME,
			    "部店コードが未指定です。");
       }

       for (int i = 0; i < this.branchCodeList.length; i++)
       {
           if (this.branchCodeList[i].length() != 3)
           {                
               log.exiting(STR_METHOD_NAME);                
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00834 ,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "部店コードのサイズが不正です。");
           }
       }

       //２）商品区分チェック 
       //リクエストデータ.商品区分 = null 
       //の場合、例外をスローする。
       if (WEB3StringTypeUtility.isEmpty(this.commodityDiv))
       {
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02182,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               "リクエストデータ.商品区分 = null");
       }
       
       //３）決済機@関IDチェック 
       //リクエストデータ.商品区分 = オンライン入金の場合で 
       //リクエストデータ.決済機@関ID = null or 
       //リクエストデータ.決済機@関ID.length() != 11 or 
       //リクエストデータ.決済機@関ID.startsWith("ComOndebi") = false 
       //の場合、例外をスローする。
       if (WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(
               this.commodityDiv))
       {
           if (WEB3StringTypeUtility.isEmpty(this.paySchemeId) || 
               (this.paySchemeId.length() != 11 && !this.paySchemeId.equals("0")) ||
               (!this.paySchemeId.startsWith("ComOndebi") && !this.paySchemeId.equals("0")))
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   "リクエストデータ.決済機@関ID = null or " +
                   "リクエストデータ.決済機@関ID.length() != 11 or " +
                   "リクエストデータ.決済機@関ID.startsWith(ComOndebi) = false");
           }
       }
   }
    
    /**
     * @@roseuid 423552AB0000
     */
    public WEB3AdminAioOtherCountReferenceRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * その他件数照会レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioOtherCountReferenceResponse(this);
    }
}
@
