head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccRegAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客区分(WEB3AccRegAccountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 顧客区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AccRegAccountDivDef
{

    /**
     * 0：一般
     */
    public final static String GENERAL  = "0";

    /**
     * 1：大口
     */
    public final static String LARGE_LOT  = "1";

    /**
     * 2：一任
     */
    public final static String LEAVING_A_MATTER_ENTIRELY  = "2";

    /**
     * 3：月次報告
     */
    public final static String MONTHLY_REPORT  = "3";

    /**
     * 4：取引明細
     */
    public final static String TRADING_DETAIL  = "4";

    /**
     * 5：新取明細
     */
    public final static String NEW_TRADING_DETAIL  = "5";

}@
