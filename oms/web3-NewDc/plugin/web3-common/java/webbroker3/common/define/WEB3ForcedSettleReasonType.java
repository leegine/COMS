head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForcedSettleReasonType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済理由区分定数定義インタフェイス(WEB3ForcedSettleReasonType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/25 栄イ(中訊) 新規作成
Revision History : 2008/01/23 栄イ (中訊) 株式仕様変更管理台帳(ＤＢレイアウト)No.155を対応
*/
package webbroker3.common.define;

/**
 * 強制決済理由区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ForcedSettleReasonType
{
    /**
     * 0：期日到来
     */
    public final static String FIXED_DATE_COMING = "0";

    /**
     * 1：保証金維持率割れ（オンライン開始前・軽度）
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS = "1";

    /**
     * 2：保証金維持率割れ（オンライン開始前・重度）
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS = "2";

    /**
     * 3：保証金維持率割れ（場間）
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_MARKET = "3";

    /**
     * 4：保証金維持率割れ（オンライン開始前・法@定）
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL = "4";

    /**
     * 9：手動強制決済
     */
    public final static String MANUAL_FORCED_SETTLE = "9";
}
@
