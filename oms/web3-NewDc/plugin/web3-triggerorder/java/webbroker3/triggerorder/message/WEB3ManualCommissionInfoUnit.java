head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ManualCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手動発注手数料情報(WEB3ManualCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (手動発注手数料情報)<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3ManualCommissionInfoUnit extends Message 
{
    
    /**
     * (手数料コース)<BR>
     * 手数料コース<BR>
     */
    public String commissionCourse = null;
    
    /**
     * (手数料)<BR>
     * 手数料<BR>
     */
    public String commission = null;
    
    /**
     * (手数料消費税)<BR>
     * 手数料消費税<BR>
     */
    public String commissionConsumptionTax = null;
    
    /**
     * (手動発注手数料情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 43F48892038A
     */
    public WEB3ManualCommissionInfoUnit() 
    {
     
    }
}
@
