head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.38.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccTaxTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座区分定数定義インタフェイス(WEB3ToSuccTaxTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 孟東(中訊)　@新規作成
*/
package webbroker3.triggerorder.define;

/**
 * 口座区分定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3ToSuccTaxTypeDef
{
    /**
     * 0：　@一般
     */
    public static final String NORMAL = "0";

    /**
     * 1：　@特定
     */
    public static final String SPECIAL = "1";
}
@
