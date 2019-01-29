head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginTradingPowerDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 信用新規建余力詳細画面表示レスポンス(WEB3TPMarginTradingPowerDetailResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (信用新規建余力詳細画面表示レスポンス)<BR>
 * 信用新規建余力詳細画面表示レスポンスクラス。<BR>
 * 
 * @@author asano(SCS)
 */

public class WEB3TPMarginTradingPowerDetailResponse extends WEB3GenResponse 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "tradingpower_margin_tradingpower_detail";

    /**
     * 余力計算結果ID
     */
    public String calcResultId;
   
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
    * その他拘束金
    */
   public String otherRestraint;
   
   /**
    * 現金保証金
    */
   public String cashDeposit;
   
   /**
    * 代用証券評価額
    */
   public String substituteSecurityAsset;
   
   /**
    * 発注分代用証券評価額
    */
   public String orderSubstituteSecurityAsset;
   
   /**
    * 差入保証金
    */
   public String guarantyDeposit;
   
   /**
    * 建玉評価損
    */
   public String contractAssetLoss;
   
   /**
    * 建玉評価益
    */
   public String contractAssetProfit;
   
   /**
    * 建玉評価損益
    */
   public String contractAssetProfitLoss;
   
   /**
    * 建手数料
    */
   public String contractCommission;
   
   /**
    * 日歩・逆日歩・貸株料
    */
   public String interestFeeLoss;
   
   /**
    * 日歩・逆日歩益
    */
   public String interestFeeProfit;
   
   /**
    * その他未収費用
    */
   public String otherAccruedCost;
   
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
    * 必要保証金
    */
   public String marginDeposit;
   
   /**
    * 現金必要保証金
    */
   public String cashMarginDeposit;

   /**
    * 発注分建玉代金
    */
   public String orderContractAmount;
   
   /**
    * 発注分必要保証金
    */
   public String orderMarginDeposit;
   
   /**
    * 発注分現金必要保証金
    */
   public String orderCashMarginDeposit;

   /**
    * 日計り返済・現引現渡建玉代金
    */
   public String dayRepayContractAmount;
   
   /**
    * 日計り返済・現引現渡必要保証金
    */
   public String dayRepayMarginDeposit;
   
   /**
    * 日計り返済・現引現渡現金必要保証金
    */
   public String dayRepayCashMarginDeposit;

   /**
    * 必要保証金合計
    */
   public String marginDepositTotal;
   
   /**
    * 現金必要保証金合計
    */
   public String cashMarginDepositTotal;

   /**
    * 保証金余力
    */
   public String depositTradingPower;
   
   /**
    * 信用新規建余力
    */
   public String marginTradingPower;
   
   /**
    * 保証金維持余力
    */
   public WEB3TPMarginMaintenanceTradingPowerUnit[] marginMaintenanceTradingPowerUnits;
   
   /**
    * 保証金預託率
    */
   public String marginCollateralRate;
   
   /**
    * 保証金率
    */
   public String marginDepositRate;
   
   /**
    * 最低必要保証金
    */
   public String minMarginDeposit;
   
   /**
    * 建玉決済損<当日>
    */
   public String settleContractLossToday;
   
   /**
    * 建玉決済損<指定日>
    */
   public String settleContractLossDesignatedDate;
   
   /**
    * 建玉決済益<当日>
    */
   public String settleContractProfitToday;
      
   /**
    * 建玉決済益<指定日>
    */
   public String settleContractProfitDesignatedDate;

   /**
    * 決済損益合計<当日>
    */
   public String settleContractProfitLossToday;
   
   /**
    * 決済建玉<前日価格評価>
    */
   public String settleContractPrevDay;
   
   /**
    * 決済損益累計<概算>
    */
   public String settleContractProfitLossTotal;
   
   /**
    * (コンストラクタ)
    * @@param l_request
    * @@roseuid 41B6B47A028F
    */
   protected WEB3TPMarginTradingPowerDetailResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B5838A0103
    */
   public WEB3TPMarginTradingPowerDetailResponse() 
   {
    
   }
}
@
