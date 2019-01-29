head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 決済明細レスポンス(WEB3HistorySettleDetailResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 王敏 (中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (決済明細レスポンス)<BR>
 * 決済明細レスポンスクラス<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3HistorySettleDetailResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_settleDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221704L;
        
    /**
     * (翻訳摘要名)<BR>
     * 翻訳摘要名<BR>
     */
    public String remarkName;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (市場区分)<BR>
     * 1：　@東証<BR>
     * 2：　@大証<BR>
     * 3：　@名証<BR>
     * 4：　@札証<BR>
     * 5：　@NNM<BR>
     * 6：　@JASDAQ<BR>
     * 9：　@福証<BR>
     */
    public String marketDiv;
    
    /**
     * (数量)<BR>
     * 数量<BR>
     */
    public String quantity;
    
    /**
     * (口座区分)<BR>
     * 0：　@一般<BR>
     * 1：　@特定<BR>
     */
    public String taxType;
    
    /**
     * (弁済区分)<BR>
     * 1：　@制度信用<BR>
     * 3：　@一般信用<BR>
     */
    public String repaymentDiv;
    
    /**
     * (建約定日)<BR>
     * 建約定日<BR>
     */
    public Date openExecDate;
    
    /**
     * (埋約定日)<BR>
     * 埋約定日<BR>
     */
    public Date closeExecDate;
    
    /**
     * (建単価)<BR>
     * 建単価<BR>
     */
    public String contractPrice;
    
    /**
     * (埋単価)<BR>
     * 埋単価<BR>
     */
    public String closeContractPrice;
    
    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;
    
    /**
     * (建金額)<BR>
     * 建金額<BR>
     */
    public String contractAmount;
    
    /**
     * (埋金額)<BR>
     * 埋金額<BR>
     */
    public String closeContractAmount;
    
    /**
     * (建手数料)<BR>
     * 建手数料<BR>
     */
    public String openCommission;
    
    /**
     * (埋手数料)<BR>
     * 埋手数料<BR>
     */
    public String closeCommission;
    
    /**
     * (建手数料消費税)<BR>
     * 建手数料消費税<BR>
     */
    public String openCommissionTax;
    
    /**
     * (埋手数料消費税)<BR>
     * 埋手数料消費税<BR>
     */
    public String closeCommissionTax;
    
    /**
     * (事務管理費)<BR>
     * 事務管理費<BR>
     */
    public String managementFee;
    
    /**
     * (事務管理費消費税)<BR>
     * 事務管理費消費税<BR>
     */
    public String managementFeeTax;
    
    /**
     * (名義書換料)<BR>
     * 名義書換料<BR>
     */
    public String nameTransferFee;
    
    /**
     * (名義書換料消費税)<BR>
     * 名義書換料消費税<BR>
     */
    public String nameTransferFeeTax;
    
    /**
     * (貸株料)<BR>
     * 貸株料<BR>
     */
    public String loanEquityFee;
    
    /**
     * (支払日歩)<BR>
     * 支払日歩<BR>
     */
    public String debitDailyInterest;
    
    /**
     * (受入日歩)<BR>
     * 受入日歩<BR>
     */
    public String creditDailyInterest;
    
    /**
     * (配当金)<BR>
     * 配当金<BR>
     */
    public String dividendAmount;
    
    /**
     * (調整額)<BR>
     * 調整額<BR>
     */
    public String adjustAmount;
    
    /**
     * (受渡金額)<BR>
     * 受渡金額<BR>
     */
    public String deliveryAmount;
    
    /**
     * @@roseuid 41789C4903C8
     */
    public WEB3HistorySettleDetailResponse() 
    {
     
    }

    /**
     * デフォルトコンストラクタ<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3HistorySettleDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
