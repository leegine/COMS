head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiLotteryInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用抽選情報(WEB3SrvRegiLotteryInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用抽選情報)<BR>
 * サービス利用抽選情報データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3SrvRegiLotteryInfo extends Message 
{
    
     /**
     * (抽選情報ID)<BR>
     * 通番<BR>
     */
    public String lotteryId;
    
    /**
     * (運用区分)<BR>
     * 0:無条件当選　@1:通常運用（抽選有）　@2:通常運用（抽選有オークション）<BR>
     */
    public String useDiv;
    
    /**
     * (申込期間（自）)
     */
    public Date applyStartDate;
    
    /**
     * (申込期間（至）)
     */
    public Date applyEndDate;
    
    /**
     * (抽選日)
     */
    public Date lotteryDate;
    
    /**
     * (適用開始日)
     */
    public Date trialStartDate;
    
    /**
     * (適用終了日)
     */
    public Date trialEndDate;
    
    /**
     * (利用料金)<BR>
     * this.運用区分が"無条件当選"または"通常運用（抽選有）"の場合、<BR>
     * "利用料金"として参照<BR>
     * this.運用区分が"通常運用（抽選有オークション）"の場合、<BR>
     * "最低入札価格"として参照する。<BR>
     */
    public String chargeAmt;
    
    /**
     * (入札単位)
     */
    public String biddingPriceUnit;
    
    /**
     * (出金日)
     */
    public Date paymentDate;
    
    /**
     * (募集枠)
     */
    public String applyMax;
    
    /**
     * (無効区分)<BR>
     * true:無効　@false:有効<BR>
     */
    public boolean invalidDiv;
    
    /**
     * (サービス利用抽選情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE3A9D0083
     */
    public WEB3SrvRegiLotteryInfo() 
    {
     
    }
}
@
