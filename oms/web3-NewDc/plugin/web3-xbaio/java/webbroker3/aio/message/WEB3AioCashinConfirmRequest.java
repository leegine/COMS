head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金額確認リクエスト(WEB3AioCashinConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成      
                   2004/10/22 黄建 (中訊) レビュー                 
*/
package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入金額確認リクエスト)<BR>
 * 入金額確認リクエストクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinConfirmRequest extends WEB3AioCashinCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;       
    
    /**
     * (入金金額)<BR>
     * 画面にて入力された入金額<BR>
     */
    public String cashinAmt;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinConfirmRequest.class);
    
    /**
     *  デフォルトコンストラク
     * @@roseuid 4158E9B603AE
     */
    public WEB3AioCashinConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B40347
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinConfirmResponse(this);
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
     * @@throws WEB3BaseException
     * @@roseuid 40E258140140
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidateメソッドをコールする
        super.validate();
        
        //２）入金金額チェック        
        //リクエストデータ.入金金額 = null
        if(WEB3StringTypeUtility.isEmpty(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.入金金額 = null");              
        }
        
        //リクエストデータ.入金金額に数字以外の文字が含まれる        
        if(!WEB3StringTypeUtility.isNumber(this.cashinAmt))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.入金金額に数字以外の文字が含まれる, " +
                "リクエストデータ.入金金額 = " + this.cashinAmt);              
        }

        //リクエストデータ.入金金額.length()
        if(this.cashinAmt.length() > 12)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00766,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.入金金額.length() > 12 , " +
                "リクエストデータ.入金金額.length() = " + this.cashinAmt.length());              
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
}
@
