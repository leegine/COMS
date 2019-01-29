head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformCatDelimitterDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連結項目デリミッタ(WEB3InformCatDelimitterDef.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/03/09 唐性峰　@(中訊) 新規作成(モデル・No.032)
*/

package webbroker3.inform.define;

/**
 * (連結項目デリミッタ)
 * @@author 唐性峰
 * @@version 1.0
 */
public interface WEB3InformCatDelimitterDef
{
    /**
     * 半角SPACE
     */
    public static final String  HALF_SPACE = " "; 

    /**
     * 全角SPACE
     */
    public static final String FULL_SPACE = "　@";

    /**
     * ハイフン（"-"）
     */
    public static final String HYPHEN = "-";

    /**
     * 空文字（""）
     */
    public static final String EMPTY_WORD = "";
}
@
