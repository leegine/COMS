head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信募集注文入力リクエストクラス(WEB3MutualApplyInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 黄建 (中訊) 新規作成
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信募集注文入力リクエストクラス <BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3MutualApplyInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_apply_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualApplyInputRequest.class);
    
    /**
     * (投信銘柄ID)<BR>  
     *  投信銘柄ID<BR>  
     */
    public String id;
    
    /**
     * (電子鳩チェックフラグ)<BR>  
     *  電子鳩チェックフラグ<BR>  
     *  true：チェック要  false：チェック不要 <BR>     
     */
    public boolean batoCheckFlag;
    
    /**
     * (種別コード)<BR>  
     *  種別コード<BR>  
     */
    public String typeCode;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>  
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>  
     * <BR>
     * １)　@銘柄IDチェック <BR>
     * this.ID==nullの場合、例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02229 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１)　@銘柄IDチェック
        //this.ID==nullの場合、例外をスローする。
        if (this.id == null)
        {
            log.debug("銘柄IDが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄IDが未指定です。");       
        }   
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 投信募集注文入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B5029E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualApplyInputResponse(this);
    }   
}
@
