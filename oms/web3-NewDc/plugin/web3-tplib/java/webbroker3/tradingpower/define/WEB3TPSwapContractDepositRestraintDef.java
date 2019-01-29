head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSwapContractDepositRestraintDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「現引現渡建玉必要保証金拘束区分」の定数定義インターフェース(WEB3TPSwapContractDepositRestraintDef.java)
 Author Name   : Daiwa Institute of Research
 Revision History : 2009/12/03 張騰宇 新規作成　@モデルNo400
 */
package webbroker3.tradingpower.define;

/**
 * (「現引現渡建玉必要保証金拘束区分」の定数定義インターフェース)
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3TPSwapContractDepositRestraintDef
{
    /**
     * 現引現渡の約定日のみ拘束する。
     */
    public final static String ONE_DAY = "0";
    
    /**
     * 現引現渡の約定日から、受渡日前日まで拘束する。
     */
    public final static String THREE_DAYS = "1";
}
@
