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
filename	WEB3MarginExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引分割約定クラス(WEB3MarginExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引分割約定）。<br>
 * <br>
 * 信用取引分割約定クラス
 * @@version 1.0
 */
public class WEB3MarginExecuteUnit extends Message 
{
    
    /**
     * (約定日時)
     */
    public Date executionTimestamp;
    
    /**
     * (約定株数)
     */
    public String execQuantity;
    
    /**
     * (約定単価)
     */
    public String execPrice;
    
    /**
     * @@roseuid 414045390173
     */
    public WEB3MarginExecuteUnit() 
    {
     
    }
}
@
