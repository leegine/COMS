head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消完了レスポンス(WEB3AioCashoutCancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金取消完了レスポンス)<BR>
 * 出金取消完了レスポンスクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutCancelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410100905L;    
    
    /**
     * (取消対象となっている注文の出金額)
     */
    public String cashoutAmt;
    
    /**
     * (振込予定日)<BR>
     * 取消対象となっている注文の振込予定日
     */
    public Date transScheduledDate;
    
    /**
     * (DB更新時間)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (取り消した注文の注文ID)
     */
    public String orderId;
    
    /**
     * @@roseuid 4158EB5F00A2
     */
    public WEB3AioCashoutCancelCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
