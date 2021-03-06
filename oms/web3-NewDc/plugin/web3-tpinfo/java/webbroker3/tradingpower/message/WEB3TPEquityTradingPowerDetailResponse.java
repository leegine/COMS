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
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : »¨t]ÍÚ×æÊ\¦X|X(WEB3TPEquityTradingPowerDetailResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) VKì¬
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (»¨t]ÍÚ×æÊ\¦X|X)<BR>
 * »¨t]ÍÚ×æÊ\¦X|XNXB<BR>
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
    * úvèS©à
    */
   public String dayTradeRestraint;
   
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
    * Ê]¿¹v
    */
   public String contractAssetProfitLoss;
   
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
    * KvÛØà
    */
   public String marginDeposit;
   
   /**
    * »àKvÛØà
    */
   public String cashMarginDeposit;

   /**
    * ÛØà]Í
    */
   public String depositTradingPower;
   
   /**
    * gpÂ\»à
    */
   public String actualAccountBalance;

   /**
    * »¨t[]Í
    */
   public String equityApplyTradingPower;
   
   /**
    * »¨t]Í
    */
   public String equityTradingPower;
   
   
   /**
    * (RXgN^)
    * @@param l_request
    * @@roseuid 41B6542000CA
    */
   protected WEB3TPEquityTradingPowerDetailResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (RXgN^)
    * @@roseuid 41B5671500E2
    */
   public WEB3TPEquityTradingPowerDetailResponse() 
   {   
   }
   
}
@
