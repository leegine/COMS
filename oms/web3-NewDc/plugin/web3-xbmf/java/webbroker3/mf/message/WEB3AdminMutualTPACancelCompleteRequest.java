head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託管理者余力調整取消完了リクエスト(WEB3AdminMutualTPACancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 黄建 (中訊) 新規作成
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 *(投資信託管理者余力調整取消完了リクエスト)<BR>
 *投資信託管理者余力調整取消完了リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3AdminMutualTPACancelCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tpa_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191632L;
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPACancelCompleteRequest.class);
    
    /**
     * (顧客コード)<BR>
     *  顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (注文ＩＤ)<BR>
     *  注文ＩＤ<BR>
     */
    public String orderId;
    
    /**
     * (暗証番号)<BR>
     *  暗証番号<BR>
     */
    public String password;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>  
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>  
     * <BR>
     *１）顧客コードチェック  <BR>
     *    this.顧客コード==nullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00835 <BR>
     * <BR>
     *２）注文IDチェック  
     *    this.注文ID==nullの場合、例外をスローする。<BR>
     *<BR>
     *      class: WEB3BusinessLayerException <BR>
     *      tag: BUSINESS_ERROR_00600 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@顧客コードチェック
        //this.顧客コード==nullの場合、例外をスローする。
        if (this.accountCode == null)
        {
            log.debug("顧客コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードが未指定です。");            
        }
        
        //２）注文IDチェック 
        //this.注文ID==nullの場合、例外をスローする。
        if (this.orderId == null)
        {
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");   
        }
        log.exiting(STR_METHOD_NAME);	
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 投信募集注文完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B5029E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualTPACancelCompleteResponse(this);
    } 

}
@
