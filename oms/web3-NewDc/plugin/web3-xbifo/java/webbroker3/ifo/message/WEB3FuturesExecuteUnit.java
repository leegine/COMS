head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文約定照会約定(WEB3FutureExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 李媛 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (株価指数先物注文約定照会約定)<BR>
 * 株価指数先物注文約定照会約定クラス
 *                                                                     
 * @@author 李媛
 * @@version 1.0
 */
public class WEB3FuturesExecuteUnit extends Message
{

    /**
     * (約定日時)
     */
    public Date executionTimestamp;

    /**
     * (約定数量)
     */
    public String execQuantity;

    /**
     * (約定単価)
     */
    public String execPrice;

    /**
     * @@roseuid 40C0754B037A
     */
    public WEB3FuturesExecuteUnit()
    {

    }
}
@
