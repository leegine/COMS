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
filename	WEB3AdminToTradeStopDelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止削除完了レスポンス(WEB3AdminToTradeStopDelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04 呉衛安(中訊) 新規作成
*/

package webbroker3.admintriggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (トリガー注文管理者・取扱停止削除完了レスポンス)<BR>
 * トリガー注文管理者・取扱停止削除完了レスポンスクラス<BR>
 * 
 * @@author 呉衛安
 * @@version 1.0  
 */
public class WEB3AdminToTradeStopDelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_trade_stop_del_complete";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200604031700L;
    
    /**
     * (現在時刻)<BR>
     * 現在時刻<BR>
     */
    public Date currentTime;
    
    /**
     * @@roseuid 4430D2D10399
     */
    public WEB3AdminToTradeStopDelCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト<BR>
     */
    public WEB3AdminToTradeStopDelCompleteResponse(WEB3AdminToTradeStopDelCompleteRequest l_request)
    {
        super(l_request);
    } 
}@
