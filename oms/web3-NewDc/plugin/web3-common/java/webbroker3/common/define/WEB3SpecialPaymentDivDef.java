head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SpecialPaymentDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 特殊利払区分定数定義インタフェイス(WEB3SpecialPaymentDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 特殊利払区分　@定数定義インタフェイス
 *                                                                     
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3SpecialPaymentDivDef
{
    /**
     * 0：特殊利払日無し
     */
    public static final String NO_SPECIAL_PAYMENT_DAY = "0";

    /**
     * 1：初期・終期スキップ
     */
    public static final String START_END_SKIP = "1";

    /**
     * 2：初期スキップ
     */
    public static final String START_SKIP = "2";

    /**
     * 3：終期スキップ
     */
    public static final String END_SKIP = "3";

    /**
     * 4：初期不払
     */
    public static final String START_NOT_PAYMENT = "4";

    /**
     * 5：初期満額払い
     */
    public static final String START_FULL_PAYMENT = "5";

    /**
     * 6：ショートインタレスト
     */
    public static final String SHORT_INTEREST = "6";
}
@
