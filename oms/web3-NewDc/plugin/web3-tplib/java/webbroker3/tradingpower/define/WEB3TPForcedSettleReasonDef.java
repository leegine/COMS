head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPForcedSettleReasonDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済理由定数定義インタフェイス(WEB3ForcedSettleReasonDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/17 孟亞南(中訊) 新規作成 モデルNo.210
*/
package webbroker3.tradingpower.define;

/**
 * (強制決済理由)<BR>
 * 強制決済理由 定数定義インタフェイス<BR>
 *
 * @@author 孟亞南(中訊)
 * @@version 1.0
 */
public interface WEB3TPForcedSettleReasonDef
{
    /**
     * "1"：オンライン開始前（重度）
     */
    public final static String BEFORE_ONLINE_SERIOUSNESS = "1";

    /**
     * "2"：オンライン開始前（軽度）
     */
    public final static String BEFORE_ONLINE_SLIGHTNESS = "2";

    /**
     * "3"：場間
     */
    public final static String TRADING_TIME_ZONE = "3";

    /**
     * "4"：オンライン開始前（法@定）
     */
    public final static String BEFORE_ONLINE_LEGAL = "4";
}
@
