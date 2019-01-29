head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3StopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 停止  定数定義クラス(WEB3StopDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 張威 (sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 停止  定数を定義する。
 *
 * @@author 張威 (sinocom)
 * @@version 1.0
 */
public interface WEB3StopDef
{

    /**
     * 停止でない　@
     */
    public final static String NO_STOP = "0";

    /**
     * 停止中
     */
    public final static String STOP_INSIDE = "1";

}

@
