head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFOrderQuantityType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文数量区分 定数定義インタフェイス(WEB3MFOrderQuantityType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/15 王蘭芬(中訊)　@新規作成
*/
package webbroker3.mf.define;

/**
 * 注文数量区分(投資信託　@投信注文照会注文単位)　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3MFOrderQuantityType
{
    /**
     * 0:口数　@　@　@　@
     */
    public static final String QTY = "0";

    /**
     * ブランク:円
     */
    public static final String BLANK_EN = " ";

    /**
     * ブランク×２:円
     */
    public static final String TWO_BLANK_EN = "  ";

    /**
     * A0:US$
     */
    public static final String US_DOLLAR = "A0";

    /**
     * A3:HK$
     */
    public static final String HK_DOLLAR = "A3";

    /**
     * T0:円
     */
    public static final String EN = "T0";

}
@
