head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 取引余力ユニット(WEB3TPTradingPowerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/03 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (取引余力ユニット)<BR>
 * 取引余力ユニットクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPTradingPowerUnit extends Message 
{
    
   /**
    * 日付
    */
   public Date bizDate;
       
   /**
    * 現物株買付余力
    */
   public String equityTradingPower;
   
   /**
    * 信用新規建余力
    */
   public String marginTradingPower;
   
   /**
    * 信用現引余力
    */
   public String swapLongTradingPower;
   
   /**
    * 投信買付余力
    */
   public String mutualTradingPower;
   
   /**
    * その他商品買付余力
    */
   public String otherTradingPower;
   
   /**
    * 出金余力
    */
   public String paymentTradingPower;
   
   /**
    * 保証金預託率
    */
   public String marginCollateralRate;
   
   /**
    * 余力適用日<現物株買付余力>
    */
   public Date equityBuyApplyDate;
   
   /**
    * 余力適用日<信用新規建余力>
    */
   public Date marginApplyDate;

      
}
@
