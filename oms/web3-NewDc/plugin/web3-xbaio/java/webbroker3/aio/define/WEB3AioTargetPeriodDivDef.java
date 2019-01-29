head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTargetPeriodDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 適用期間区定数定義インタフェイス(WEB3AioTargetPeriodDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/18 張騰宇（中訊）新規作成 モデルNo.760
*/

package webbroker3.aio.define;

/**
 * (適用期間区)<BR>
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AioTargetPeriodDivDef
{
    /**
     * 0：適用期間中<BR>
     */
    public static final String TARGET_PERIODING = "0";

    /**
     * 1：適用期間外<BR>
     */
    public static final String TARGET_PERIOD_OUT = "1";
}
@
