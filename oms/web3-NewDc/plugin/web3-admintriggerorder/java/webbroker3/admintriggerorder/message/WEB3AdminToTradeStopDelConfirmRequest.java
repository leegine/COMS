head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopDelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止削除確認リクエスト(WEB3AdminToTradeStopDelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 呉衛安(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・取扱停止削除確認リクエスト)<BR>
 * トリガー注文管理者・取扱停止削除確認リクエストクラス<BR>
 * 
 * @@author 呉衛安
 * @@version 1.0  
 */
public class WEB3AdminToTradeStopDelConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopDelConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_del_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (特殊執行条件取扱停止ID)<BR>
     * 特殊執行条件取扱停止ID<BR>
     */
    public String triggerTradeStopId;
    
    /**
     * @@roseuid 4430D2D10242
     */
    public WEB3AdminToTradeStopDelConfirmRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@特殊執行条件取扱停止IDチェック<BR>
     * 　@１－１）　@this.特殊執行条件取扱停止ID == nullの場合、<BR>
     * 　@　@「特殊執行条件取扱停止IDが未入力」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02430<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406B796006E
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@特殊執行条件取扱停止IDチェック
        //　@１－１）　@this.特殊執行条件取扱停止ID == nullの場合、
        //　@　@「特殊執行条件取扱停止IDが未入力」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.triggerTradeStopId))
        {
            log.debug("特殊執行条件取扱停止IDが未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02430,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "特殊執行条件取扱停止IDが未入力です。");
        }            
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * トリガー注文管理者・取扱停止削除確認レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopDelConfirmResponse(this);
    }
}@
