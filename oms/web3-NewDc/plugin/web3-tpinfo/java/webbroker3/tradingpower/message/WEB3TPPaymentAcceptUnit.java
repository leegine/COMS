head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentAcceptUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 出金受付状況明細ユニットクラス(WEB3TPPaymentAcceptUnit.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

/**
 * (出金受付状況明細ユニット)<BR>
 * 出金受付状況明細ユニットクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * 出金受付状況明細
 */
public class WEB3TPPaymentAcceptUnit extends Message 
{
   
   /**
    * 日付
    */
   public Date bizDate;
   
   /**
    * 出金額
    */
   public String paymentAmount;
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B932E60130
    */
   public WEB3TPPaymentAcceptUnit() 
   {
    
   }
}
@
