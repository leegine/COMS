head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3YearlyInterestPaymentsDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 年間利払回数定数定義インタフェイス(WEB3SpecialPaymentDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 栄イ(中訊) 新規作成
Revesion History : 2006/09/19 栄イ(中訊)  ＤＢレイアウト(債券銘柄マスタテーブル)による
*/
package webbroker3.common.define;

/**
 * 年間利払回数　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3YearlyInterestPaymentsDef
{
    /**
     * 0:DEFAULT
     */
    public static final String DEFAULT = "0";

    /**
     * 99999999:不定時
     */
    public static final String NO_FIXED_TIME = "99999999";
}
@
