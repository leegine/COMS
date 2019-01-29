head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約入力リクエストクラス(WEB3MutualSellInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 黄建 (中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー  
                   2004/12/07 于美麗 (中訊) 残対応 
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClaimDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託解約入力リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3MutualSellInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408120915L;
    
    /**
     * 投信銘柄ID
     */
    public String id;
    
    /**
     * 請求方法@<BR>
     * <BR>
     * null:指定無し　@0:解約請求　@1:買取請求<BR>
     */
    public String sellBuyDiv;
    
    /**
        * ログユーティリティ<BR>
        */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellInputRequest.class);
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A8900F022A
     */
    public WEB3MutualSellInputRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信解約入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8901B01EC
     */
    public WEB3GenResponse createResponse() 
    {
        return  new WEB3MutualSellInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@IDチェック<BR>
     * 　@IDがnullの場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00080 <BR>
     * <BR>
     * ２)　@請求方法@チェック<BR>
     * ２－１）this.請求方法@==nullの場合、例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00401 <BR>
     * ２－２）請求方法@が以下のいずれにも当てはまらない場合、例外をスローする。<BR>
     *     ・”指定無し”<BR>
     * 　@　@・”解約請求”<BR>
     * 　@　@・”買取請求”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00402 <BR>
     * @@roseuid 40A8904B01CC
     */
    public void validate() throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１)　@IDチェック
        
        //IDがnullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("ＩＤが未指定です。");
            throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00080,
               getClass().getName() + "." + STR_METHOD_NAME,
               "ＩＤが未指定です。"); 
        }

        //２－２）請求方法@が以下のいずれにも当てはまらない場合、例外をスローする。
         //・”指定無し.解約請求・買取請求
        if (!WEB3StringTypeUtility.isEmpty(this.sellBuyDiv) &&
            !WEB3ClaimDivDef.SELL.equals(this.sellBuyDiv) &&
            !WEB3ClaimDivDef.BUY.equals(this.sellBuyDiv))
        {
            log.debug("請求方法@が”指定無し”、”解約請求”、”買取請求”以外の場合。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00402,
                getClass().getName() + "." + STR_METHOD_NAME,
                "請求方法@が”指定無し”、”解約請求”、”買取請求”以外の場合。"); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
