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
filename	WEB3AdminToTradeStopInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止入力レスポンス(WEB3AdminToTradeStopInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/05 張　@芳(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・取扱停止入力レスポンス)<BR>
 * トリガー注文管理者・取扱停止入力レスポンスクラス<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_input";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * @@roseuid 4430D3BA01A5
     */
    public WEB3AdminToTradeStopInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminToTradeStopInputResponse(WEB3AdminToTradeStopInputRequest l_request)
    {
        super(l_request);
    } 
}
@
