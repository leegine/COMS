head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransitionReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力推移明細ユニットクラス(WEB3TPAssetUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import com.fitechlabs.xtrade.kernel.message.Message;
import java.util.Date;

/**
 * (余力推移明細ユニット)<BR>
 * 余力推移明細ユニットクラス。<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPTransitionReferenceUnit extends Message
{

    /**
     * 日付
     */
    public Date bizDate;

    /**
     * 預り金
     */
    public String accountBalance;

    /**
     * 前日比
     */
    public String comparedPreviousDay;

    /**
     * 発注充当金
     */
    public String unexecutedAmount;

    /**
     * 発注分返済決済損益
     */
    public String orderRepaySettleProfitLoss;

    /**
     * 今回発注分返済決済損益
     */
    public String currOrderRepaySettleProfitLoss;

    /**
     * 日計り拘束金
     */
    public String dayTradeRestraint;

    /**
     * その他拘束金
     */
    public String otherRestraint;

    /**
     * (預り金担保出金拘束金)
     */
    public String cashDepositRestraint;

    /**
     * 現金保証金
     */
    public String cashDeposit;

    /**
     * 証券評価額
     */
    public String securityAsset;

    /**
     * 差入保証金
     */
    public String guarantyDeposit;

    /**
     * 建玉評価益
     */
    public String contractAssetProfit;

    /**
     * 建玉評価損
     */
    public String contractAssetLoss;

    /**
     * 建玉評価損益
     */
    public String contractAssetProfitLoss;

    /**
     * 建玉諸経費
     */
    public String contractTotalCost;

    /**
     * 未受渡建玉決済損
     */
    public String undeliContractLoss;

    /**
     * 未受渡建玉決済益
     */
    public String undeliContractProfit;

    /**
     * 受入保証金
     */
    public String acceptDeposit;

    /**
     * 建玉代金
     */
    public String contractAmount;

    /**
     * 日計り返済・現引現渡建玉代金
     */
    public String dayRepayContractAmount;

    /**
     * 未受渡建玉代金
     */
    public String undeliContractAmount;

    /**
     * 必要保証金
     */
    public String marginDeposit;

    /**
     * 日計り返済・現引現渡必要保証金
     */
    public String dayRepayMarginDeposit;

    /**
     * 現金必要保証金
     */
    public String cashMarginDeposit;

    /**
     * 日計り返済・現引現渡現金必要保証金
     */
    public String dayRepayCashMarginDeposit;

    /**
     * 保証金余力
     */
    public String depositTradingPower;

    /**
     * 保証金引出拘束金
     */
    public String depositWithdrawRestraint;

    /**
     * 未受渡建玉必要保証金
     */
    public String undeliMarginDeposit;

    /**
     * 未受渡建玉現金必要保証金
     */
    public String undeliCashMarginDeposit;

    /**
     * 保証金引出余力
     */
    public String depositWithdrawTradingPower;

    /**
     * 出金余力
     */
    public String paymentTradingPower;

    /**
     * 保証金預託率
     */
    public String marginCollateralRate;

    /**
     * 追証必要保証金
     */
    public String additionalMarginDeposit;

    /**
     * 追証余力
     */
    public String additionalDepositTradingPower;

    /**
     * 保証金維持余力＜保証金率＞
     */
    public String marginDepositPower;

    /**
     * 保証金維持余力＜保証金維持率＞
     */
    public String marginMaintenancePower;

    /**
     * 保証金維持余力＜法@定保証金維持率＞
     */
    public String legalMarginDepositPower;

    /**
     * 預り金不足額            
     */
    public String accountBalanceShortfall;

    /**
     * 入金請求額
     */
    public String payClaimAmount;

    /**
     * 現物株買付余力
     */
    public String equityTradingPower;

    /**
     * 現物株買付余力<日計り拘束金考慮>
     */
    public String equityTradingPowerDayTrade;

    /**
     * 信用新規建余力
     */
    public String marginTradingPower;

    /**
     * 信用現引余力
     */
    public String swapLongTradingPower;

    /**
     * 信用現引余力<日計り拘束金考慮>
     */
    public String swapLongTradingPowerDayTrade;

    /**
     * 投信買付余力
     */
    public String mutualTradingPower;

    /**
     * その他商品買付余力 
     */
    public String otherTradingPower;

    /**
     * (コンストラクタ)
     * @@roseuid 41B5632E020B
     */
    public WEB3TPTransitionReferenceUnit()
    {

    }
}@
