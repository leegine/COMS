head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoReadFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 未読既読フラグ(WEB3PvInfoReadFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李丁銀(sinocom) 新規作成
*/
package webbroker3.common.define;

/** 
 * 未読既読フラグ
 * @@author 李丁銀
 * @@version 1.0
 */
public interface WEB3PvInfoReadFlagDef
{

    /**
     * 1：既読　@　@　@　@　@　@  　@　@
     */
    public final static String READ_YES = "1";

    /**
     * 0：未読　@　@
     */
    public final static String READ_NO = "0";

}
@
