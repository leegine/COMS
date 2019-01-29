head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoEffectiveFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 有効/無効フラグ(WEB3PvInfoEffectiveFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李丁銀(sinocom) 新規作成
*/
package webbroker3.common.define;

/** 
 * 有効/無効フラグ
 * @@author 李丁銀
 * @@version 1.0
 */
public interface WEB3PvInfoEffectiveFlagDef
{

    /**
     * 1: 無効　@　@　@　@　@  　@　@
     */
    public final static String EFFECTIVE_NO = "1";

    /**
     * 0: 有効　@　@
     */
    public final static String EFFECTIVE_YES = "0";

}
@
