head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSignDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 単価調整値の符号定数定義インタフェイス(WEB3ToSuccSignDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 鄭海良(中訊)　@新規作成
*/
package webbroker3.triggerorder.define;

/**
 * 連続注文単価調整値情報の単価調整値の符号定数定義インタフェイス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccSignDivDef
{
    /**
     * +：　@加算
     */
    public static final String ADD = "+";

    /**
     * -：　@減算
     */
    public static final String SUBTRACT = "-";
}
@
