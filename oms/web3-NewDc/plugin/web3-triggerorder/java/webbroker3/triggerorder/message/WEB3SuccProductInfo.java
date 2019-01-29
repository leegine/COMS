head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.45.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文銘柄情報(WEB3SuccProductInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (連続注文銘柄情報)<BR>
 * 連続注文銘柄情報<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccProductInfo extends Message 
{
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (連続注文銘柄情報)<BR>
     * コンストラクタ<BR>
     * @@roseuid 431F848A0081
     */
    public WEB3SuccProductInfo() 
    {
     
    }
}
@
