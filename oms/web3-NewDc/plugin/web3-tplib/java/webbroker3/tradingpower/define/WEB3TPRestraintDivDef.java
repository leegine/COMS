head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPRestraintDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「拘束区分」の定数定義インターフェース(WEB3TPRestraintDivDef.java)
 Author Name   : Daiwa Institute of Research
 Revision History : 2009/12/03 張騰宇 新規作成　@モデルNo400
 */
package webbroker3.tradingpower.define;

/**
 * (「拘束区分」の定数定義インターフェース)
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3TPRestraintDivDef
{
    /**
     * 0：DEFAULT
     */
    public final static String DEFAULT = "0";

    /**
     * 1：必要保証金
     */
    public final static String MARGIN_DEPOSIT = "1";

    /**
     * 2：評価損益
     */
    public final static String PROFITLOSS = "2";
}
@
