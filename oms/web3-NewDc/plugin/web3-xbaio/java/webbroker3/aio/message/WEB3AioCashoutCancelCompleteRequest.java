head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消完了リクエスト(WEB3AioCashoutCancelCompleteRequest)
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
 * (出金取消完了リクエスト)<BR>
 * 出金取消完了リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutCancelCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410100928L;    
    
    /**
     * (取消対象となる出金注文のID)
     */
    public String orderId;
    
    /**
     * (暗証番号)<BR>
     * 画面にて入力された暗証番号
     */
    public String password;
    
    /**
     * (確認処理時の発注日)
     * （画面表示なし）
     */
    public Date checkDate;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelCompleteRequest.class);  
    /**
     * @@roseuid 4158EB5E01E1
     */
    public WEB3AioCashoutCancelCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）注文IDチェック<BR>
     *   リクエストデータ.注文ID = null の場合、例外をスローする。<BR><BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * <BR>
     * ２）確認時発注日チェック<BR>
     *   リクエストデータ.確認時発注日 = null の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * @@roseuid 40E5197000DD
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）注文IDチェック
        //リクエストデータ.注文ID = null の場合、例外をスローする。
           //class: WEB3BusinessLayerException
           //tag:   BUSINESS_ERROR_00600
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.注文ID = null");   
        } 
        
        //２）確認時発注日チェック<BR>
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
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金取消完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutCancelCompleteResponse(this);
    }
}
@
