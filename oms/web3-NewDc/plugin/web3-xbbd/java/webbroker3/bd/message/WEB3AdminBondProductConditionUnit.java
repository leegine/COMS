head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.37.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券銘柄照会情報(WEB3AdminBondProductConditionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (債券銘柄照会情報)<BR>
 * 債券銘柄行
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductConditionUnit extends Message
{
    
    /**
     * (銘柄コード(WEB3))<BR>
     * 銘柄コード(WEB3)
     */
    public String productCode;
    
    /**
     * (HOST銘柄名1)<BR>
     * HOST銘柄名1
     */
    public String hostProductName1;
    
    /**
     * (取扱銘柄名)<BR>
     * 取扱銘柄名
     */
    public String handlingProductName;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * (取扱区分)<BR>
     * 取扱区分
     */
    public String tradeHandleDiv;
    
    /**
     * (発行価格)<BR>
     * 発行価格
     */
    public String issuePrice;
    
    /**
     * (利率)<BR>
     * 利率
     */
    public String coupon;
    
    /**
     * (発行日)<BR>
     * 発行日
     */
    public Date issueDate;
    
    /**
     * (償還日)<BR>
     * 償還日
     */
    public Date maturityDate;
    
    /**
     * (年間利払回数)<BR>
     * 年間利払回数<BR>
     * <BR>
     * 不定時は99999999
     */
    public String yearlyInterestPayments;
    
    /**
     * (利払日1)<BR>
     * 利払日1<BR>
     * <BR>
     * "0000"の時は表示しない
     */
    public String interestPaymentDay1;
    
    /**
     * (利払日2)<BR>
     * 利払日2<BR>
     * <BR>
     * "0000"の時は表示しない
     */
    public String interestPaymentDay2;
    
    /**
     * (種別コード)<BR>
     * 種別コード
     */
    public String bondCategCode;
    
    /**
     * @@roseuid 44E3363B0280
     */
    public WEB3AdminBondProductConditionUnit() 
    {
     
    }
}
@
