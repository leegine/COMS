head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPAccountAttributeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追証発生状況定数定義インタフェイス(WEB3AdminTPAccountAttributeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/14 安陽(中訊) 新規作成
*/

package webbroker3.tradingpoweradmin.define;

/**
 * 追証発生状況　@定数定義インタフェイス
 *
 * @@author 安陽
 * @@version 1.0
 */
public interface WEB3AdminTPAccountAttributeDef
{
    /**
     * 現物（前金制）
     */
    public static final String EQUITY_NOT_ASSET_EVAL = "現物（前金制）";

    /**
     * 現物（評価制）
     */
    public static final String EQUITY_ASSET_EVAL = "現物（評価制）";

    /**
     * 信用
     */
    public static final String MARGIN = "信用";

}
@
