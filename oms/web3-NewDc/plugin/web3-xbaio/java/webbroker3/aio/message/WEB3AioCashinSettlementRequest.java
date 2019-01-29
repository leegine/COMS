head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettlementRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済依頼リクエスト(WEB3AioCashinSettlementRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成    
                   2004/10/22 黄建 (中訊) レビュー                   
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (決済依頼リクエスト)<BR>
 * 決済依頼リクエストクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettlementRequest extends WEB3AioCashinCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_settlement";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (入金金額)<BR>
     * 画面にて入力された入金額<BR>
     */
    public String cashinAmt;
    
    /**
     * (暗証番号)<BR>
     * 画面にて入力された暗証番号<BR>
     */
    public String password;
    
    /**
     * (確認時発注日)<BR>
     * 確認処理時の発注日<BR>
     * （画面表示なし）<BR>
     */
    public Date checkDate;
    
    /**
     * (PR層保持情報)<BR>
     * （画面表示なし）<BR>
     */
    public WEB3AioPrSessionUnit prSessionUnit ;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettlementRequest.class);
    
    /**
     * @@roseuid 4158EB3401B9
     */
    public WEB3AioCashinSettlementRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinSettlementResponse(this);
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidateメソッドをコールする。<BR>
     * <BR>
     * ２）入金金額チェック<BR>
     *   リクエストデータ.入金金額に数字以外の文字が含まれる or<BR>
     *   リクエストデータ.入金金額 = null or<BR>
     *   リクエストデータ.入金金額.length() > 12<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00766<BR>
     * <BR>
     * ３）確認時発注日チェック<BR>
     *   リクエストデータ.確認時発注日 = null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * <BR>
     * ４）PR層保持情報チェック<BR>
     *    ４－１）
     *   リクエストデータ.PR層保持情報 = nullの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01739<BR>
     * <BR>
     * <BR>
     *    ４－２） 
     *   PR層保持情報の各属性 = nullの場合、例外をスローする。 
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01740<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E2633702E6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidateメソッドをコールする
        super.validate();
        
        //リクエストデータ.入金金額 = null
        if(WEB3StringTypeUtility.isEmpty(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.入金金額 = null ");
        }
        
        //２）入金金額チェック 
        //リクエストデータ.入金金額に数字以外の文字が含まれる
        if(!WEB3StringTypeUtility.isNumber(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.入金金額に数字以外の文字が含まれる, " +
                "リクエストデータ.入金金額 = " + this.cashinAmt);
        }
            
        //リクエストデータ.入金金額.length() > 12 
        if(this.cashinAmt.length() > 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.入金金額.length() > 12, " +
                "リクエストデータ.入金金額.length() = " + this.cashinAmt.length());
        }        

        //３）確認時発注日チェック 
        //リクエストデータ.確認時発注日 = null
        if(this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.確認時発注日 = null");
        }
        
        //=====remain zhou-yong NO.1 begin =========
        
        //４）PR層保持情報チェック
        //４－１） 
        //リクエストデータ.PR層保持情報 = null 
        //の場合、例外をスローする。
        if(this.prSessionUnit == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01739,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.PR層保持情報 = null");
            
        }
        //４－２） 
        //PR層保持情報の各属性 = null 
        //の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.prSessionUnit.regetServiceId)
            && WEB3StringTypeUtility.isEmpty(this.prSessionUnit.wolfAid)
            && WEB3StringTypeUtility.isEmpty(this.prSessionUnit.wolfSession)
            && WEB3StringTypeUtility.isEmpty(this.prSessionUnit.wolfSsid))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01740,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "PR層保持情報の各属性 = null");
            
        }

        //=====remain zhou-yong NO.1 end =========
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
