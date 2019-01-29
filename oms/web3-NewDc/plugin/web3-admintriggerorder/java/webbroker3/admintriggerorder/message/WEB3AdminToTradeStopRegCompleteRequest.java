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
filename	WEB3AdminToTradeStopRegCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止登録完了リクエスト(WEB3AdminToTradeStopRegCompleteRequest.java)
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
 * (トリガー注文管理者・取扱停止登録完了リクエスト)<BR>
 * トリガー注文管理者・取扱停止登録完了リクエストクラス<BR>
 * 
 * @@author 呉衛安
 * @@version 1.0  
 */
public class WEB3AdminToTradeStopRegCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopRegCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_reg_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password = null;
    
    /**
     * (取扱停止情報)<BR>
     * 取扱停止情報<BR>
     */
    public WEB3AdminToTradeStopInfoUnit tradeStopInfoUnit;
    
    /**
     * @@roseuid 4430D362037A
     */
    public WEB3AdminToTradeStopRegCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@取扱停止情報チェック<BR>
     * 　@１－１）　@this.取扱停止情報 == nullの場合、<BR>
     * 　@　@「取扱停止情報が未入力」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_02431<BR>
     * <BR>
     * 　@１－２）　@this.取扱停止情報.銘柄コード == nullの場合、<BR>
     * 　@　@「銘柄コードが未指定です。」の例外をスローする。<BR>
     *     class : WEB3BusinessLayerException<BR>
     *     tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@１－３）　@this.取扱停止情報.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4406BA3C01B6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@取扱停止情報チェック
        //　@１－１）　@this.取扱停止情報 == nullの場合、
        //　@　@「取扱停止情報が未入力」の例外をスローする。
        if (this.tradeStopInfoUnit == null)
        {
            log.debug("取扱停止情報が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02431,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱停止情報が未入力です。");
        }        
        
        // １－２）　@this.取扱停止情報.銘柄コード == nullの場合、
        //　@　@「銘柄コードが未指定です。」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.tradeStopInfoUnit.productCode))
        {
            log.debug("銘柄コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードが未指定です。");
        }
        
        // １－３）　@this.取扱停止情報.validate()をコールする。
        this.tradeStopInfoUnit.validate();
        
        log.exiting(STR_METHOD_NAME);         
    }
    
    /**
     * (createResponseの実装)<BR>
     * <BR>
     * トリガー注文管理者・取扱停止登録完了レスポンスオブジェクトを返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41E78F6401A5
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminToTradeStopRegCompleteResponse(this);
    }
}@
