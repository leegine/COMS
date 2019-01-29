head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoBlinkDisplayFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 点滅表示フラグ(WEB3PvInfoBlinkDisplayFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李丁銀(sinocom) 新規作成
*/
package webbroker3.common.define;

/** 
 * 点滅表示フラグ
 * @@author 李丁銀
 * @@version 1.0
 */
public interface WEB3PvInfoBlinkDisplayFlagDef
{

    /**
     * 1:あり　@　@　@　@　@  　@　@
     */
    public final static String BLINK_DISP_YES = "1";

    /**
     * 0: なし　@　@
     */
    public final static String BLINK_DISP_NO = "0";

}
@
