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
filename	WEB3EquityMarginOrderIdUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式・信用注文ID(WEB3EquityMarginOrderIdUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 関博(中訊) 新規作成
*/

package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (株式・信用注文ID)<BR>
 * 株式・信用注文IDクラス<BR>
 * @@author  関博
 * @@version 1.0
 */
public class WEB3EquityMarginOrderIdUnit extends Message
{

    /**
     * (ID)<BR>
     * 注文ID<BR>
     */
    public String id;

    /**
     * (商品区分)<BR>
     * 1： 現物株式<BR>
     * 2： 信用取引<BR>
     */
    public String productDiv;

    /**
     * (株式・信用注文ID)<BR>
     * コンストラクタ<BR>
     * @@roseuid 455D1E7A00CD
     */
    public WEB3EquityMarginOrderIdUnit()
    {

    }
}
@
