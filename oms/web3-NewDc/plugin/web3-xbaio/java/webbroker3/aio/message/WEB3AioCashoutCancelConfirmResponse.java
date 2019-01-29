head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消確認レスポンス(WEB3AioCashoutCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成 
                   2004/10/22 黄建 (中訊) レビュー                      
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (出金取消確認レスポンス)<BR>
 * 出金取消確認レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashoutCancelConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (取消対象となっている注文のID)
     */
    public String orderId;
    
    /**
     * (出金余力)<BR>
     * 顧客の現在の出金余力
     */
    public String paymentPower;
    
    /**
     * (出金金額)<BR>
     * 取消対象となっている注文の出金額
     */
    public String cashoutAmt;
    
    /**
     * (振込予定日)<BR>
     * 取消対象となっている注文の振込予定日
     */
    public Date transScheduledDate;
    
    /**
     * (確認時発注日)<BR>
     * 確認処理時の発注日<BR>
     * （画面表示なし）
     */
    public Date checkDate;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158EB5F0391
     */
    public WEB3AioCashoutCancelConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
