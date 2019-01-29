head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopUpdInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止変更入力リクエスト(WEB3AdminToTradeStopUpdInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 張　@芳(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・取扱停止変更入力リクエスト)<BR>
 * トリガー注文管理者・取扱停止変更入力リクエストクラス<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToTradeStopUpdInputRequest extends WEB3AdminToCommonRequest 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopUpdInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_upd_input";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (処理区分)<BR>
     * 処理区分<BR>
     * <BR>
     * 1：　@商品<BR>
     * 2：　@市場<BR>
     * 3：　@銘柄<BR>
     */
    public String tradeStopDiv;
    
    /**
     * (特殊執行条件取扱停止ID)<BR>
     * 特殊執行条件取扱停止ID<BR>
     * <BR>
     * ※処理区分 == "銘柄"の場合セット。<BR>
     */
    public String triggerTradeStopId = null;
    
    /**
     * @@roseuid 4430D3B9038A
     */
    public WEB3AdminToTradeStopUpdInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@処理区分チェック<BR>
     * 　@２－１）　@this.処理区分 == nullの場合、<BR>
     * 　@　@「処理区分が未入力」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01249<BR>
     * <BR>
     * 　@２－２）　@this.処理区分に以下の値以外が設定されていた<BR>
     * 　@　@場合、「処理区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"商品"<BR>
     * 　@　@　@・"市場"<BR>
     * 　@　@　@・"銘柄"<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_01250<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406BCCE02A0
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）super.validate()をコールする。
        super.validate();
        
        // ２）処理区分チェック
        // 　@２－１）this.処理区分 == nullの場合、
        // 　@　@     「処理区分が未入力」の例外をスローする。
        //           class : WEB3BusinessLayerException
        //           tag : BUSINESS_ERROR_01249
        if (WEB3StringTypeUtility.isEmpty(this.tradeStopDiv))
        {
            log.debug("処理区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01249,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分が未指定です。");            
        }
        
        // 　@２－２）this.処理区分に以下の値以外が設定されていた
        // 　@　@       場合、「処理区分が未定義の値」の例外をスローする。
        // 　@　@　@     ・"商品"
        // 　@　@　@     ・"市場"
        // 　@　@　@     ・"銘柄"
        //            class : WEB3BusinessLayerException
        //            tag : BUSINESS_ERROR_01250
        if (!(WEB3TargetTypeDef.COMMODITY.equals(this.tradeStopDiv)
            || WEB3TargetTypeDef.MARKET.equals(this.tradeStopDiv)
            || WEB3TargetTypeDef.PRODUCT.equals(this.tradeStopDiv)))
        {
            log.debug("処理区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01250,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分の値が存在しないコード値です。");               
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * トリガー注文管理者・取扱停止変更入力レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopUpdInputResponse(this);
    }
}
@
