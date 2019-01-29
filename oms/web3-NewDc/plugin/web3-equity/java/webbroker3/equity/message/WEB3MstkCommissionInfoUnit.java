head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCommissionInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資手数料情報(WEB3MstkCommissionInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  彭巍(中訊) 新規作成
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * （株式ミニ投資手数料情報）。<BR>
 * <BR>
 * 株式ミニ投資手数料情報クラス
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3MstkCommissionInfoUnit extends Message 
{
    
    /**
     * （手数料コース）。
     */
    public String commissionCourse;
    
    /**
     * （手数料）。
     */
    public String commission;
    
    /**
     * （手数料消費税）。
     */
    public String commissionConsumptionTax;
    
    /**
     * （株式ミニ投資手数料情報）。<BR>
     * <BR>
     * コンストラクタ。
     */
    public WEB3MstkCommissionInfoUnit() 
    {
     
    }
}
@
