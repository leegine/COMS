head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込共通リクエスト(WEB3AioCashoutCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (出金申込共通リクエスト)<BR>
 * 出金申込共通リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101115L;    
    
    /**
     * (出金金額)<BR>
     * 画面にて入力された出金額
     */
    public String cashoutAmt;
    
    /**
     * (振込予定日)<BR>
     * 画面にて選択された振込予定日
     */
    public Date transScheduledDate;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutCommonRequest.class);  
            
    /**
     * @@roseuid 4158EB610005
     */
    public WEB3AioCashoutCommonRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）出金金額チェック<BR>
     *   リクエストデータ.出金金額に数字以外の文字が含まれる or<BR>
     *   リクエストデータ.出金金額 = null or<BR>
     *   リクエストデータ.出金金額 <= 0 or<BR>
     *   リクエストデータ.出金金額.length() > 9
     *     の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00771<BR>
     * <BR>
     * <BR>
     * ２）振込予定日チェック<BR>
     *   リクエストデータ.振込予定日 = nullの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00772<BR>
     * <BR>
     * @@roseuid 40E249B8022B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）出金金額チェック
          //リクエストデータ.出金金額に数字以外の文字が含まれる or
          //リクエストデータ.出金金額 = null or
          //リクエストデータ.出金金額 <= 0 or
          //リクエストデータ.出金金額.length() > 9
          //の場合、例外をスローする。
            //class: WEB3BusinessLayerException
            //tag:   BUSINESS_ERROR_00771
          if (WEB3StringTypeUtility.isEmpty(this.cashoutAmt) || 
             (WEB3StringTypeUtility.isNumber(this.cashoutAmt) == false) || 
             (Double.parseDouble(this.cashoutAmt) <= 0) ||
             (this.cashoutAmt.length() > 9))
          {
              throw new WEB3BusinessLayerException(
                  WEB3ErrorCatalog.BUSINESS_ERROR_00771,
                  this.getClass().getName() + "." + STR_METHOD_NAME,
                  "リクエストデータ.出金金額に数字以外の文字が含まれる or " +
                  "リクエストデータ.出金金額 = null or " +
                  "リクエストデータ.出金金額 <= 0 or " +
                  "リクエストデータ.出金金額.length() > 9");   
          }  
        
        //２）振込予定日チェック
          //リクエストデータ.振込予定日 = nullの場合、例外をスローする。
            //class: WEB3BusinessLayerException
            // tag:   BUSINESS_ERROR_00772
        if (this.transScheduledDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00772,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.振込予定日 = null");  
        }        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金申込共通レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB61002D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutCommonResponse(this);
    }
}
@
