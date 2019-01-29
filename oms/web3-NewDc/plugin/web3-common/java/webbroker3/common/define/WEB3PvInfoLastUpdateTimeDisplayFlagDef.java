head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoLastUpdateTimeDisplayFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 最終更新日時表示フラグ(WEB3PvInfoLastUpdateTimeDisplayFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李丁銀(sinocom) 新規作成
*/
package webbroker3.common.define;

/** 
 * 最終更新日時表示フラグ
 * @@author 李丁銀
 * @@version 1.0
 */
public interface WEB3PvInfoLastUpdateTimeDisplayFlagDef
{

    /**
     * 1: 非表示　@　@　@　@　@　@  　@　@
     */
    public final static String DISPLAY_NO = "1";

    /**
     * 0: 表示　@　@
     */
    public final static String DISPLAY_YES = "0";

}
@
