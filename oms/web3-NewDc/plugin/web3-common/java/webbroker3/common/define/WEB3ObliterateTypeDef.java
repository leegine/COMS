head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.03.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ObliterateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ObliterateTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 抹消区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3ObliterateTypeDef
{
    /**
     * 0:Def 
     */
    public static final String DEF = "0";

    /**
     * 9:未登録 or 抹消
     */
    public static final String NOTREG_OR_OBLITERATE = "9";

}
@
