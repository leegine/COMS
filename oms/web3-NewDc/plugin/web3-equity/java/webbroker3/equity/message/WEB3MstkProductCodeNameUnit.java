head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文約定照会注文単位(WEB3MstkExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  彭巍(中訊) 新規作成
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * （株式ミニ投資銘柄コード名称）。<BR>
 * <br>
 * 株式ミニ投資銘柄コード名称
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3MstkProductCodeNameUnit extends Message 
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
     * (株式ミニ投資銘柄コード名称)<BR>
     * デフォルトコンストラクタ。
     */
    public WEB3MstkProductCodeNameUnit() 
    {
     
    }
}
@
