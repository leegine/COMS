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
filename	WEB3AdminToTradeStopDelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止削除確認レスポンス(WEB3AdminToTradeStopDelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 呉衛安(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・取扱停止削除確認レスポンス)<BR>
 * トリガー注文管理者・取扱停止削除確認レスポンスクラス<BR>
 * 
 * @@author 呉衛安
 * @@version 1.0  
 */
public class WEB3AdminToTradeStopDelConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_del_confirm";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (取扱停止情報)<BR>
     * 取扱停止情報<BR>
     */
    public WEB3AdminToTradeStopInfoUnit tradeStopInfoUnit;
    
    /**
     * @@roseuid 4430D2D2003E
     */
    public WEB3AdminToTradeStopDelConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminToTradeStopDelConfirmResponse(WEB3AdminToTradeStopDelConfirmRequest l_request)
    {
        super(l_request);
    } 
}@
