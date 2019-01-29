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
filename	WEB3AdminToTradeStopUpdCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止変更完了レスポンス(WEB3AdminToTradeStopUpdCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 張　@芳(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・取扱停止変更完了レスポンス)<BR>
 * トリガー注文管理者・取扱停止変更完了レスポンスクラス<BR>
 * 
 * @@author 張　@芳
 * @@version 1.0
 */
public class WEB3AdminToTradeStopUpdCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_upd_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 20060403164900L;
    
    /**
     * (現在時刻)<BR>
     * 現在時刻<BR>
     */
    public Date currentTime;
    
    /**
     * @@roseuid 4430D3B9033C
     */
    public WEB3AdminToTradeStopUpdCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminToTradeStopUpdCompleteResponse(WEB3AdminToTradeStopUpdCompleteRequest l_request)
    {
        super(l_request);
    } 
}
@
