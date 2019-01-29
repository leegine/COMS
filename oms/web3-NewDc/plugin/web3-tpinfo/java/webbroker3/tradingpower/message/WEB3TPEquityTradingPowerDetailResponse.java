head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEquityTradingPowerDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 現物株買付余力詳細画面表示レスポンス(WEB3TPEquityTradingPowerDetailResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (現物株買付余力詳細画面表示レスポンス)<BR>
 * 現物株買付余力詳細画面表示レスポンスクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPEquityTradingPowerDetailResponse extends WEB3GenResponse 
{

   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_equity_tradingpower_detail";

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
    * 日計り拘束金
    */
   public String dayTradeRestraint;
   
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
    * 必要保証金
    */
   public String marginDeposit;
   
   /**
    * 現金必要保証金
    */
   public String cashMarginDeposit;

   /**
    * 保証金余力
    */
   public String depositTradingPower;
   
   /**
    * 使用可能現金
    */
   public String actualAccountBalance;

   /**
    * 現物株買付充当余力
    */
   public String equityApplyTradingPower;
   
   /**
    * 現物株買付余力
    */
   public String equityTradingPower;
   
   
   /**
    * (コンストラクタ)
    * @@param l_request
    * @@roseuid 41B6542000CA
    */
   protected WEB3TPEquityTradingPowerDetailResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B5671500E2
    */
   public WEB3TPEquityTradingPowerDetailResponse() 
   {   
   }
   
}
@
