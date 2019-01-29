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
filename	WEB3EquityMarginExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式・信用分割約定(WEB3EquityMarginExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 関博(中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (株式・信用分割約定)<BR>
 * 株式・信用分割約定クラス<BR>
 * @@author  関博
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteUnit extends Message
{

    /**
     * (約定日時)<BR>
     * 約定日時<BR>
     */
    public Date executionTimestamp;

    /**
     * (約定株数)<BR>
     * 約定株数<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice;

    /**
     * (株式・信用分割約定)<BR>
     * コンストラクタ<BR>
     * @@roseuid 455C428300FC
     */
    public WEB3EquityMarginExecuteUnit()
    {

    }
}
@
