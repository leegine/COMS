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
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : MpVK]ÍÚ×æÊ\¦X|X(WEB3TPMarginTradingPowerDetailResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) VKì¬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (MpVK]ÍÚ×æÊ\¦X|X)<BR>
 * MpVK]ÍÚ×æÊ\¦X|XNXB<BR>
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
     * ]ÍvZÊID
     */
    public String calcResultId;
   
   /**
    * út
    */
   public Date bizDate;
   
   /**
    * aèà
    */
   public String accountBalance;
   
   /**
    * Oúä
    */
   public String comparedPreviousDay;
   
   /**
    * ­[à
    */
   public String unexecutedAmount;
   
   /**
    * »Ì¼S©à
    */
   public String otherRestraint;
   
   /**
    * »àÛØà
    */
   public String cashDeposit;
   
   /**
    * ãpØ]¿z
    */
   public String substituteSecurityAsset;
   
   /**
    * ­ªãpØ]¿z
    */
   public String orderSubstituteSecurityAsset;
   
   /**
    * ·üÛØà
    */
   public String guarantyDeposit;
   
   /**
    * Ê]¿¹
    */
   public String contractAssetLoss;
   
   /**
    * Ê]¿v
    */
   public String contractAssetProfit;
   
   /**
    * Ê]¿¹v
    */
   public String contractAssetProfitLoss;
   
   /**
    * è¿
    */
   public String contractCommission;
   
   /**
    * úàEtúàEÝ¿
    */
   public String interestFeeLoss;
   
   /**
    * úàEtúàv
    */
   public String interestFeeProfit;
   
   /**
    * »Ì¼¢ûïp
    */
   public String otherAccruedCost;
   
   /**
    * Êoï
    */
   public String contractTotalCost;
   
   /**
    * ¢ónÊÏ¹
    */
   public String undeliContractLoss;
   
   /**
    * ¢ónÊÏv
    */
   public String undeliContractProfit;
   
   /**
    * óüÛØà
    */
   public String acceptDeposit;
   
   /**
    * Êãà
    */
   public String contractAmount;
   
   /**
    * KvÛØà
    */
   public String marginDeposit;
   
   /**
    * »àKvÛØà
    */
   public String cashMarginDeposit;

   /**
    * ­ªÊãà
    */
   public String orderContractAmount;
   
   /**
    * ­ªKvÛØà
    */
   public String orderMarginDeposit;
   
   /**
    * ­ª»àKvÛØà
    */
   public String orderCashMarginDeposit;

   /**
    * úvèÔÏE»ø»nÊãà
    */
   public String dayRepayContractAmount;
   
   /**
    * úvèÔÏE»ø»nKvÛØà
    */
   public String dayRepayMarginDeposit;
   
   /**
    * úvèÔÏE»ø»n»àKvÛØà
    */
   public String dayRepayCashMarginDeposit;

   /**
    * KvÛØàv
    */
   public String marginDepositTotal;
   
   /**
    * »àKvÛØàv
    */
   public String cashMarginDepositTotal;

   /**
    * ÛØà]Í
    */
   public String depositTradingPower;
   
   /**
    * MpVK]Í
    */
   public String marginTradingPower;
   
   /**
    * ÛØàÛ]Í
    */
   public WEB3TPMarginMaintenanceTradingPowerUnit[] marginMaintenanceTradingPowerUnits;
   
   /**
    * ÛØàaõ¦
    */
   public String marginCollateralRate;
   
   /**
    * ÛØà¦
    */
   public String marginDepositRate;
   
   /**
    * ÅáKvÛØà
    */
   public String minMarginDeposit;
   
   /**
    * ÊÏ¹<ú>
    */
   public String settleContractLossToday;
   
   /**
    * ÊÏ¹<wèú>
    */
   public String settleContractLossDesignatedDate;
   
   /**
    * ÊÏv<ú>
    */
   public String settleContractProfitToday;
      
   /**
    * ÊÏv<wèú>
    */
   public String settleContractProfitDesignatedDate;

   /**
    * Ï¹vv<ú>
    */
   public String settleContractProfitLossToday;
   
   /**
    * ÏÊ<Oú¿i]¿>
    */
   public String settleContractPrevDay;
   
   /**
    * Ï¹vÝv<TZ>
    */
   public String settleContractProfitLossTotal;
   
   /**
    * (RXgN^)
    * @@param l_request
    * @@roseuid 41B6B47A028F
    */
   protected WEB3TPMarginTradingPowerDetailResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (RXgN^)
    * @@roseuid 41B5838A0103
    */
   public WEB3TPMarginTradingPowerDetailResponse() 
   {
    
   }
}
@
