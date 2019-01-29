head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.35.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OnlyMobileOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : モバイル専用口座開設区分定数定義インタフェイス(WEB3OnlyMobileOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * モバイル専用口座開設区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3OnlyMobileOpenDivDef
{
    /**
     * 0：未開設（DEFAULT）
     */
    public final static String DEFAULT = "0";

    /**
     * 1：開設
     */
    public final static String OPEN = "1";
}
@
