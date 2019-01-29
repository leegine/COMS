head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3CarryoverProcessTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文繰越処理区分　@定数定義インタフェイス(WEB3CarryoverProcessType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/25 趙林鵬(中訊) 新規作成 モデルNo.669
*/

package webbroker3.ifo.define;

/**
 * 注文繰越処理区分　@定数定義インタフェイス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public interface WEB3CarryoverProcessTypeDef
{
    /**
     * "注文繰越"：注文繰越<BR>
     */
    public static final String ORDER_CARRY_OVER = "注文繰越";

    /**
     * "夕場前注文繰越"：夕場前注文繰越<BR>
     */
    public static final String BEFORE_EVENING_SESSION_ORDER_CARRY_OVER = "夕場前注文繰越";
}
@
