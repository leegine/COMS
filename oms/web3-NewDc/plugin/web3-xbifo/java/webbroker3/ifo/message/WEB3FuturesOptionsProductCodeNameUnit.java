head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物オプション銘柄コード名称(WEB3FuturesOptionsProductCodeNameUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李頴淵 (中訊) 新規作成
              001: 2004/08/05 王暁傑 (中訊) 対応バグ U00021
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (先物オプション銘柄コード名称)<BR>
 * 先物オプション銘柄コード名称クラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3FuturesOptionsProductCodeNameUnit extends Message
{
    
    /**
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * 銘柄名
     */
    public String productName;
    
    /**
     * @@roseuid 40C075440000
     */
    public WEB3FuturesOptionsProductCodeNameUnit() 
    {
     
    }
}
@
