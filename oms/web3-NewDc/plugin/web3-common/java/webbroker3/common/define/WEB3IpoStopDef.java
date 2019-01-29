head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IpoStopDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO停止定数定義インタフェイス(WEB3IpoStopDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * IPO停止 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3IpoStopDef
{

    /**
     * 0：DEFAULT（開催中） 　@　@
     */
    public final static String DEFAULT = "0";

    /**
     * 　@1：停止中　@　@ 　@　@
     */
    public final static String STOPPING = "1";
    
     /**
     * 　@2：中止　@　@　@　@　@　@
     */
    public final static String DISCONTINUATION  = "2";
}

@
