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
filename	WEB3MarginProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引銘柄コード名称クラス(WEB3MarginProductCodeNameUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （信用取引銘柄コード名称）。<br>
 * <br>
 * 信用取引銘柄コード名称クラス
 * @@version 1.0
 */
public class WEB3MarginProductCodeNameUnit extends Message 
{
    
    /**
     * (銘柄コード)<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     */
    public String productName;
    
    /**
     * (信用取引銘柄コード名称)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 40C930B3015E
     */
    public WEB3MarginProductCodeNameUnit() 
    {
     
    }
}
@
