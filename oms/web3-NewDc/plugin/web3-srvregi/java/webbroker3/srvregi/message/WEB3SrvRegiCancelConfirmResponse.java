head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用取消確認レスポンス(WEB3SrvRegiCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (サービス利用取消確認レスポンス)<BR>
 * サービス利用取消確認レスポンスクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_cancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151417L;
    
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
     * (申込料金)
     */
    public String applyAmt;
    
    /**
     * (前回の最高落札額)<BR>
     * （オークション無の場合はnull）<BR>
     */
    public String maxSuccBidding;
    
    /**
     * (前回の最低落札額)<BR>
     * （オークション無の場合はnull）<BR>
     */
    public String minSuccBidding;
    
    /**
     * (前回の加重平均額)<BR>
     * （オークション無の場合はnull）<BR>
     */
    public String weightedAverage;
    
    /**
     * (サービス利用取消確認レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F20C300270
     */
    public WEB3SrvRegiCancelConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3SrvRegiCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
