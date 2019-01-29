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
filename	WEB3EquityExecuteUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定照会約定(WEB3EquityExecuteUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 張坤芳 (中訊) 新規作成
*/
package webbroker3.equity.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （現物株式注文約定照会約定）。<BR>
 * <BR>
 * 現物株式注文約定照会 約定　@データクラス<BR>
 * <BR>
 * 現物株式：「注文約定照会」<BR>
 * 現物株式：「訂正取消一覧」の両画面で使用する。
 * @@version 1.0
 */
public class WEB3EquityExecuteUnit extends Message
{

    /**
     * (約定日時)<BR>
     * 約定日時<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (約定株数)<BR>
     * 注文情報に紐付いた約定の数量<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     * 注文情報に紐付いた約定の単価<BR>
     */
    public String execPrice;

    /**
     * (現物株式注文約定照会約定)<BR>
     * コンストラクタ
     * @@roseuid 407CC7ED0046
     */
    public WEB3EquityExecuteUnit()
    {

    }
}
@
