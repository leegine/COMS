head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPTransactionCategoryCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者機@能カテゴリコード定義インターフェース(WEB3AdministratorFunctionCategoryCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/12 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPTransactionCategoryCodeDefインターフェース。
 * 管理者機@能カテゴリコード定義。
 * @@author 堀野 和美(FLJ)
 * @@version 1.0
 *
 */
public interface WEB3AdminTPTransactionCategoryCodeDef {

    /*
     * 余力管理者機@能カテゴリコード
     */
    public static final String TRADINGPOWER_ADMIN = "A0201";

    /*
     * 管理者管理者（テスト用。）
     */
    public static final String ADMINISTRATOR_ADMIN = "D0101";
}
@
