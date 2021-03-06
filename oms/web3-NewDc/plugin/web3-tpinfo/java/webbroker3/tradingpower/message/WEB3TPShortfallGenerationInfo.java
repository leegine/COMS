head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 不足金発生情報(WEB3TPShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 張騰宇（中訊）新規作成 モデルNo.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (不足金発生情報) <BR>
 * (不足金発生情報ユニット)<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3TPShortfallGenerationInfo extends Message
{

    /**
     * (期日(T+0))<BR>
     * (期日(T+0))<BR>
     */
    public Date closeDate0;
    
    /**
     * (期日(T+1))<BR>
     * (期日(T+1))<BR>
     */
    public Date closeDate1;
    
    /**
     * (期日(T+2))<BR>
     * (期日(T+2))<BR>
     */
    public Date closeDate2;
    
    /**
     * (期日(T+3))<BR>
     * (期日(T+3))<BR>
     */
    public Date closeDate3;
    
    /**
     * (期日(T+4))<BR>
     * (期日(T+4))<BR>
     */
    public Date closeDate4;
    
    /**
     * (期日(T+5))<BR>
     * (期日(T+5))<BR>
     */
    public Date closeDate5;
    
    /**
     * (必要入金額(T+0))<BR>
     * (必要入金額(T+0))<BR>
     */
    public String requiredPayAmt0 = "0";
    
    /**
     * (必要入金額(T+1))<BR>
     * (必要入金額(T+1))<BR>
     */
    public String requiredPayAmt1 = "0";
    
    /**
     * (必要入金額(T+2))<BR>
     * (必要入金額(T+2))<BR>
     */
    public String requiredPayAmt2 = "0";
    
    /**
     * (必要入金額(T+3))<BR>
     * (必要入金額(T+3))<BR>
     */
    public String requiredPayAmt3 = "0";
    
    /**
     * (必要入金額(T+4))<BR>
     * (必要入金額(T+4))<BR>
     */
    public String requiredPayAmt4 = "0";
    
    /**
     * (必要入金額(T+5))<BR>
     * (必要入金額(T+5))<BR>
     */
    public String requiredPayAmt5 = "0";
    
    /**
     * (精算額(T+0))<BR>
     * (精算額(T+0))<BR>
     */
    public String adjustedAmt0 = "0";
    
    /**
     * (精算額(T+1))<BR>
     * (精算額(T+1))<BR>
     */
    public String adjustedAmt1 = "0";
    
    /**
     * (日計り拘束金(T+0))<BR>
     * (日計り拘束金(T+0))<BR>
     */
    public String dayTradeRestraint0 = "0";
    
    /**
     * (日計り拘束金(T+1))<BR>
     * (日計り拘束金(T+1))<BR>
     */
    public String dayTradeRestraint1 = "0";
    
    /**
     * (保証金からの振替額(T+0))<BR>
     * (保証金からの振替額(T+0))<BR>
     */
    public String transferFromMarginDeposit0 = "0";
    
    /**
     * (保証金からの振替額(T+1))<BR>
     * (保証金からの振替額(T+1))<BR>
     */
    public String transferFromMarginDeposit1 = "0";
}
@
