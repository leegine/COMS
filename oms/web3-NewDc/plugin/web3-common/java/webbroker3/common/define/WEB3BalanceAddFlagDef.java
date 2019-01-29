head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BalanceAddFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@                                                                    /**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 残高計上フラグ  定数定義クラス(WEB3BalanceAddFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 周勇 (sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 残高計上フラグ  定数を定義する。
 *
 * @@author 周勇 (sinocom)
 * @@version 1.0
 */
public interface WEB3BalanceAddFlagDef
{

    /**
     * 残高計上未済　@
     */
    public final static String BALANCE_OUTSTANDING = "0";

    /**
     * 残高計上済
     */
    public final static String BALANCE_STANDING = "1";
}
              @
