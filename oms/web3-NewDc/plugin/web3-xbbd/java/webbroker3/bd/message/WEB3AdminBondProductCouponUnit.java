head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.55.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductCouponUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券銘柄利率(WEB3AdminBondProductCouponUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (債券銘柄利率)<BR>
 * 債券銘柄利率クラス
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3AdminBondProductCouponUnit extends Message
{
    
    /**
     * (利払日)<BR>
     * 利払日
     */
    public Date interestPaymentDay;
    
    /**
     * (利率)<BR>
     * 利率
     */
    public String coupon;
    
    /**
     * @@roseuid 44E3363A029F
     */
    public WEB3AdminBondProductCouponUnit() 
    {
     
    }
}
@
