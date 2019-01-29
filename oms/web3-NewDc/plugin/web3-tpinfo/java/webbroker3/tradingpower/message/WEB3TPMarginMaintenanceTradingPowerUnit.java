head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginMaintenanceTradingPowerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 保証金維持余力ユニットクラス(WEB3TPMarginMaintenanceTradingPowerUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (保証金維持余力ユニット)<BR>
 * 保証金維持余力ユニットクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPMarginMaintenanceTradingPowerUnit extends Message 
{
   
   /**
    * 保証金維持率 
    */
   public String marginMaintenanceRate;
   
   /**
    * 保証金維持余力
    */
   public String marginMaintenanceTradingPower;
   
   /**
    * (コンストラクタ）
    * @@roseuid 41B58389027A
    */
   public WEB3TPMarginMaintenanceTradingPowerUnit() 
   {
    
   }
}
@
