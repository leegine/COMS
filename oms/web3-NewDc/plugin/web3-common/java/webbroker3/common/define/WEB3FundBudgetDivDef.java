head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FundBudgetDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 資金の性格(WEB3FundBudgetDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 資金の性格 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3FundBudgetDivDef
{

    /**
     * 0：余剰資金
     */
    public final static String SURPLUS_FUNDS  = "0";

    /**
     * 1：退職金
     */
    public final static String RETIREMENT_ALLOWANCE  = "1";

    /**
     * 2：借入金
     */
    public final static String BORROWED_FUNDS  = "2";

    /**
     * 3：その他
     */
    public final static String OTHER  = "3";

}@
