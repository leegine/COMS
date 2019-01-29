head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BankAccountRegiDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3BankAccountRegiDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2007/03/09 栄イ (中訊) 仕様変更・ＤＢレイアウトNo.471を対応
*/
package webbroker3.common.define;

/**
 * 振込先（銀行口座）登録区分　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3BankAccountRegiDef
{
    /**
     * 0 : 未登録
     */
    public static final String NOT_REGISTER = "0";

    /**
     * 1:円貨のみ登録済
     */
    public static final String ALREADY_REGISTER = "1";

    /**
     * 2:外貨のみ登録済
     */
    public static final String ONLY_FOREIGN_CURRENCY_REGISTERED = "2";

    /**
     * 3:両方登録済
     */
    public static final String BOTH_REGISTERED = "3";
}
@
