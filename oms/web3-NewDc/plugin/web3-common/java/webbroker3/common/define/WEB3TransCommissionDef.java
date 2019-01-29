head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransCommissionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替手数料区分(WEB3TransCommissionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 振替手数料区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3TransCommissionDef
{

    /**
     * 1：同行同店無料
     */
    public final static String SAME_TRADE_STORE_FREE  = "1";

    /**
     * 2：同行
     */
    public final static String SAME_TRADE  = "2";

    /**
     * 4：他行
     */
    public final static String OTHER_TRADE  = "4";

    /**
     * 5：同行同店有料
     */
    public final static String SAME_TRADE_STORE_CHARGE  = "5";

    /**
     * 9：その他
     */
    public final static String OTHER  = "9";

}@
