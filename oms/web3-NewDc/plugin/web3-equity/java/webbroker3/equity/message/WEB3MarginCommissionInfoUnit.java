head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引手数料情報(WEB3MarginCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引手数料情報）。<br>
 * <br>
 * 信用取引手数料情報クラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginCommissionInfoUnit extends Message 
{
    
    /**
     * (手数料コース)
     */
    public String commissionCourse;
    
    /**
     * (手数料)
     */
    public String commission;
    
    /**
     * (手数料消費税)
     */
    public String commissionConsumptionTax;
    
    /**
     * (信用取引手数料情報)<BR>
     * コンストラクタ。<BR>
     * @@return WEB3MarginCommissionInfoUnit
     * @@roseuid 40C930670323
     */
    public WEB3MarginCommissionInfoUnit() 
    {
     
    }
}
@
