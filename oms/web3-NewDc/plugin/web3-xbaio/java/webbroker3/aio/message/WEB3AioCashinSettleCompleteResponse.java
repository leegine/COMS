head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済完了レスポンス(WEB3AioCashinSettleCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成    
                   2004/10/22 黄建 (中訊) レビュー                
   
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (決済完了レスポンス)<BR>
 * 決済完了レスポンスクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCompleteResponse extends WEB3AioCashinCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_settle_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (.comデビット決済取引番号)<BR>
     * オンライン決済で使用された決済取引番号<BR>
     */
    public String comDebitNumber;
    
    /**
     * (入金金額)<BR>
     * オンライン決済の入金金額<BR>
     */
    public String cashinAmt;
    
    /**
     * (振込予定日)<BR>
     * オンライン決済の振込予定日<BR>
     */
    public Date transScheduledDate;
    
    /**
     * (証券口座振替日)<BR>
     * 顧客の証券口座への振替日
     */
    public Date accountTransDate;
    
    /**
     * (更新時間)<BR>
     * DBの更新時間<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (注文ID)<BR>
     * DB登録された注文ID<BR>
     */
    public String orderId;
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 4158EB3303D4
     */
    public WEB3AioCashinSettleCompleteResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinSettleCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
