head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PriceRangeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 値幅チェック区分定数定義クラス(WEB3PriceRangeTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/20 和田　@友一(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 値幅チェック区分の定数を定義する。
 *
 * @@author 和田　@友一(SRA)
 * @@version 1.0
 */
public interface WEB3PriceRangeTypeDef
{
    /**
     * 0:値幅チェックなし 
     */
    public final static String NO_CHECK = "0";

    /**
     * 1:値幅チェックあり指定なし
     */
    public final static String CHECK = "1";
}
@
