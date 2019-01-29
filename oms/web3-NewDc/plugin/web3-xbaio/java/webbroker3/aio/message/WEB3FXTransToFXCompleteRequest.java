head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替完了リクエスト(WEB3FXTransToFXCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 屈陽 (中訊) 新規作成   
                 : 2006/07/13 韋念瓊 (中訊) 仕様変更 NO.601,602
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
 * (FXへの振替完了リクエスト) <BR>
 * FXへの振替完了リクエストクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteRequest extends
    WEB3FXResultNoticeCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (注文ID) <BR>
     * 注文ID <BR>
     */
    public String orderId;
    
    /**
     * (暗証番号) <BR>
     * 画面から入力された暗証番号  <BR>
     */
    public String password;

    /**
     * (確認時発注日) <BR>
     * 確認処理時の発注日 <BR>
     * （画面表示なし） <BR>
     */
    public Date checkDate;
    
    /**
     * (FXシステムコード) <BR>
     * FXシステムコード <BR>
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E76933030D
     */
    public WEB3FXTransToFXCompleteRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXCompleteRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １） スーパークラスのvalidateメソッドを呼び出す。 <BR>
     * <BR>
     * ２） 注文IDチェック <BR>
     * this.注文ID＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00600 <BR>
     * <BR>
     * ３） 確認時発注日チェック <BR>
     * this.確認時発注日＝nullの場合、例外をthrowする。 <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00078 <BR>
     * <BR>
     * 
     * @@roseuid 41BE50120083
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // １） スーパークラスのvalidateメソッドを呼び出す。
        super.validate(); 
 
        // ２） 注文IDチェック 
        // this.注文ID＝nullの場合、例外をthrowする。  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00600 
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.注文ID = null"); 
        }
 
        // ３） 確認時発注日チェック 
        // this.確認時発注日＝nullの場合、例外をthrowする。 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00078 
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.確認時発注日 = null"); 
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransToFXCompleteResponse(this);
    }
}@
