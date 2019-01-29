head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BizDateCalcParameterDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 営業日区分FLAG定数定義クラス(WEB3BizDateCalcParameterDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 今井　@高史(SRA) 新規作成
Revesion History : 2004/02/12 趨 正(中訊) 新規作成
Revesion History : 2007/12/18 孟暁シン(中訊) 【共通】仕様変更管理台帳No572(ＤＢレイアウト)を対応
*/
package webbroker3.common.define;

/**
 * 営業日区分FLAG定数定義クラス
 *
 * @@author 今井　@高史(SRA)
 * @@version 1.0
 */
public interface WEB3BizDateCalcParameterDef
{
    /**
     * 0：当日
     */
    public final static String DAY_BIZ_DATE = "0";

    /**
     * 1：翌営業日
     */
    public final static String NEXT_BIZ_DATE = "1";

    /**
     * ２：２営業日後
     */
    public final static String NEXT_TWO_BIZ_DATE = "2";
    /**
     * 3：前営業日
     */
    public final static String BEFORE_BIZ_DATE = "3";
}
@
