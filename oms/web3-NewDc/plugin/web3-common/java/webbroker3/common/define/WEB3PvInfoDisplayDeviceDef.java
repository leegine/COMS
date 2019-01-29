head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoDisplayDeviceDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示媒体(WEB3PvInfoDisplayDeviceDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李丁銀(sinocom) 新規作成
*/
package webbroker3.common.define;

/** 
 * 表示媒体
 * @@author 李丁銀
 * @@version 1.0
 */
public interface WEB3PvInfoDisplayDeviceDef
{

    /**
     * 0: DEFAULT(PC&モバイル)　@　@　@　@　@  　@　@
     */
    public final static String DEFAULT_DEVICE = "0";

    /**
     * 1: PC　@　@
     */
    public final static String PC_DEVICE = "1";

    /**
     * 2: モバイル
     */
    public final static String MOBILE_DEVICE = "2";
}
@
