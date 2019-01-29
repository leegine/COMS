head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	ToStringUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Object#toString()メソッドを実装するためのユーティリティを提供するクラス(ToStringUtils.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/03 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.tradingpower.util;

/**
 * Object#toString()メソッドを実装するためのユーティリティを提供するクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class ToStringUtils
{
    
    /**
     * 余力計算モジュールで使用するToStringStyleクラス
     */
    private static final ToStringStyle WEB3TP_STYLE = new ToStringStyle();

    /**
     * 新しいToStringBuilderのインスタンスを生成する。
     * 
     * @@param object toStringメソッドを実装する対象となるオブジェクト
     * @@return ToStringBuilderのインスタンス
     */
    public static ToStringBuilder newToStringBuilder(Object object)
    {
        return new ToStringBuilder(object, WEB3TP_STYLE, new StringBuffer());
    }
    
}
@
