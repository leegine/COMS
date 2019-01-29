head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.30.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiHistoryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス履歴情報一覧行(WEB3AdminSrvRegiHistoryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (サービス利用管理者サービス履歴情報一覧行)<BR>
 * サービス利用管理者サービス履歴情報一覧行データクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiHistoryGroup extends Message 
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
     * (最高落札額)
     */
    public String maxSuccBidding;
    
    /**
     * (最適落札額)
     */
    public String minSuccBidding;
    
    /**
     * (加重平均額)
     */
    public String weightedAverage;
    
    /**
     * (サービス利用管理者サービス履歴情報一覧行)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE4F9A0100
     */
    public WEB3AdminSrvRegiHistoryGroup() 
    {
     
    }
}
@
