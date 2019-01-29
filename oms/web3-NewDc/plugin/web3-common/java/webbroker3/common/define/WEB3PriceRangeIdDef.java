head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PriceRangeIdDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 値幅区分定数定義クラス(WEB3PriceRangeIdDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 和田　@友一(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 値幅区分の定数を定義する。
 *
 * @@author 和田　@友一(SRA)
 * @@version 1.0
 */
public interface WEB3PriceRangeIdDef
{
    /**
     * 指定なし
     */
    public final static String DEFAULT = "0";

    /**
     * 円
     */
    public final static String YEN = "1";

    /**
     * ％
     */
    public final static String PERCENT = "2";

    /**
     * 1/XX
     */
    public final static String FRACTION = "3";
}
@
