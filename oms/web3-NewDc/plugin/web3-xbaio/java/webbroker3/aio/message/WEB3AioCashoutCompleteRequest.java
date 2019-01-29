head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.16.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込完了リクエスト(WEB3AioCashoutCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金申込完了リクエスト)<BR>
 * 出金申込完了リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutCompleteRequest extends WEB3AioCashoutCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101305L;    
    
    /**
     * (画面にて入力された暗証番号)
     */
    public String password;
    
    /**
     * (確認時発注日)<BR>
     * 確認処理時の発注日<BR>
     * （画面表示なし）
     */
    public Date checkDate;
    
    /**
     * (確認時注文ID)<BR>
     * 確認処理時の注文ID<BR>
     * （画面表示なし）
     */
    public long checkOrderID;
   
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCompleteRequest.class);  
            
    /**
     * @@roseuid 4158EB6102B8
     */
    public WEB3AioCashoutCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidateメソッドをコールする。<BR>
     * <BR>
     * ２）確認時発注日チェック<BR>
     *   リクエストデータ.確認時発注日 = null の場合、例外をスローする。<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * <BR>
     * ３）確認時注文IDチェック<BR>
     *   リクエストデータ.確認時注文ID = 0 の場合、例外をスローする。<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00770<BR>
     * <BR>
     * @@roseuid 40E281BC022B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
          
        //１）スーパークラスのvalidateメソッドをコールする。
        super.validate();
      
        //２）確認時発注日チェック
        //リクエストデータ.確認時発注日 = null の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00078
        if (this.checkDate == null) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.確認時発注日 = null");   
        }   
           
        //３）確認時注文IDチェック<BR>
        //リクエストデータ.確認時注文ID = 0 の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00770
        if (this.checkOrderID == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00770,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.確認時注文ID = 0");   
        }   
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金申込完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutCompleteResponse(this);
    }
}
@
