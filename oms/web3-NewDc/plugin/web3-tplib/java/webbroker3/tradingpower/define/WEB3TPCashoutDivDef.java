head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashoutDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「出金区分」の定数定義インターフェース(WEB3TPCashoutDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/12/15 張騰宇 新規作成　@モデル413
 */
package webbroker3.tradingpower.define;

/**
 * (「出金区分」の定数定義インターフェース)
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3TPCashoutDivDef
{
    /**
     *  ０：顧客申込
     */
    public final static String ACCOUNT = "0";

    /**
     * １：その他
     */
    public final static String OTHER = "1";
}
@
