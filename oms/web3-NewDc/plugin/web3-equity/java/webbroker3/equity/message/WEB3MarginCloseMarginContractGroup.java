head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引決済建株一覧明細行(WEB3MarginCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引決済建株一覧明細行）。<br>
 * <br>
 * 信用取引決済建株一覧明細行クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCloseMarginContractGroup extends Message 
{
         
    /**
     * (建日)<BR>
     * <BR>
     * YYYY/MM/DD形式で表示
     */
    public Date openDate;
    
    /**
     * (建単価)<BR>
     */
    public String contractPrice;
    
    /**
     * (注文株数)<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     */
    public String limitPrice;
    
    /**
     * (約定株数)
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     */
    public String execPrice;
    
    /**
     * (建手数料)<BR>
     */
    public String contractCommission;
    
    /**
     * (順日歩)<BR>
     * ※税込
     */
    public String interestFee;
    
    /**
     * (逆日歩)<BR>
     * ※税込
     */
    public String payInterestFee;
    
    /**
     * (貸株料)<BR>
     * ※税込
     */
    public String loanEquityFee;
    
    /**
     * (書換料)<BR>
     * ※税込
     */
    public String setupFee;
    
    /**
     * (管理費)<BR>
     * ※税込
     */
    public String managementFee;
    
    /**
     * (その他)<BR>
     * ※税込
     */
    public String otherwise;
    
    /**
     * (決済損益)<BR>
     */
    public String settleProfitLoss;
    
    /**
     * (決済順位)<BR>
     */
    public String settlePriority;
    
    /**
     * @@roseuid 414032D000F4
     */
    public WEB3MarginCloseMarginContractGroup() 
    {
     
    }
}
@
