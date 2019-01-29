head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ColumnTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 項目型(WEB3ColumnTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/09 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 項目型 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3ColumnTypeDef
{

    /**
     * 0：文字列
     */
    public final static String CHARACTER_STRING  = "0";

    /**
     * 10：数値（int）
     */
    public final static String INT_NUMBER  = "10";

    /**
     * 11：数値（long）
     */
    public final static String LONG_NUMBER  = "11";

    /**
     * 12：数値（double）
     */
    public final static String DOUBLE_NUMBER  = "12";

    /**
     * 20：日付
     */
    public final static String DATE  = "20";

    /**
     * 21：日付時間（CSV列モデルに定義）
     */
    public final static String DATE_TIME  = "21";

}@
