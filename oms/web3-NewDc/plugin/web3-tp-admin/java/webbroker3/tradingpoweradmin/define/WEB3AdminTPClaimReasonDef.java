head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPClaimReasonDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 請求事由定義インターフェース(WEB3AdminTPClaimReasonDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/09　@姜丹(中訊) 新規作成 モデルNo.027
*/
package webbroker3.tradingpoweradmin.define;

/**
 * 請求事由　@定数定義インタフェイス
 *                                                                      
 * @@author 姜丹
 * @@version 1.0
 */
public interface WEB3AdminTPClaimReasonDef
{
    /**
     * 1：立替金/特別立替金
     */
    public static final String DEBIT_AMOUNT_SPECIAL = "1";

    /**
     * 2：不足金(当日)
     */
    public static final String SHORT_FALL_GENERATION_TODAY = "2";

    /**
     * 3：30%割れ追証
     */
    public static final String BREAK30_ADDITIONAL = "3";

    /**
     * 4：20%割れ追証
     */
    public static final String BREAK20_ADDITIONAL = "4";

    /**
     * 5：指定なし
     */
    public static final String DEFAULT= "5";

}
@
