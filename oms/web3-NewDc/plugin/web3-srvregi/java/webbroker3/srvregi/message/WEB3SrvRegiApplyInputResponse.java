head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込入力レスポンス(WEB3SrvRegiApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2007/06/05 趙林鵬 (中訊) モデル.248
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用申込入力レスポンス)<BR>
 * サービス利用申込入力レスポンスクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiApplyInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151437L;
    
    /**
     * (抽選区分)<BR>
     * 0:無　@1:有<BR>
     */
    public String lotteryDiv;
    
    /**
     * (運用区分)<BR>
     * 0:無条件当選　@1:通常運用（抽選有）　@2:通常運用（抽選有オークション）<BR>
     */
    public String useDiv;
    
    /**
     * (サービス名称)
     */
    public String serviceName;
    
    /**
     * (サービス内容)
     */
    public String serviceContent;
    
    /**
     * (利用期間料金情報)
     */
    public WEB3SrvRegiChargeInfo[] chargeInfo;
    
    /**
     * (試用期間単位区分)<BR>
     * 1:年　@2:月　@3:日<BR>
     */
    public String trialDiv;
    
    /**
     * (試用期間)
     */
    public String trialPeriod;
    
    /**
     * (募集枠)
     */
    public String applyMax;
    
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
     * (前回の最高落札額)
     */
    public String maxSuccBidding;
    
    /**
     * (前回の最低落札額)
     */
    public String minSuccBidding;
    
    /**
     * (前回の加重平均額)
     */
    public String weightedAverage;

    /**
     * (申込属性区分)<BR>
     * 1：無料対象　@2：申込不可<BR>
     */
    public String applyAttributeDiv;

    /**
     * (無料対象期間)<BR>
     * 無料対象期間<BR>
     */
    public String freeTargetPeriod;

    /**
     * (無料属性申込区分)<BR>
     * null or '0' ：通常申込　@'1'：無料属性申込<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (サービス利用申込入力レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1F374017E
     */
    public WEB3SrvRegiApplyInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3SrvRegiApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
