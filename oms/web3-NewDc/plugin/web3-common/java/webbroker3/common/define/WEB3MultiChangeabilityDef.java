head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MultiChangeabilityDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 複数回訂正可能区分　@定数定義インタフェイス(WEB3MultiChangeabilityDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/14 森川昌平 新規作成
*/
package webbroker3.common.define;

/**
 * 複数回訂正可能区分　@定数定義インタフェイス
 *                                                                     
 * @@author 森川昌平
 * @@version 1.0
 */
public interface WEB3MultiChangeabilityDef
{
    /**
     * 0 : 訂正可能
     */
    public static final String CHANGEABLE = "0";

    /**
     * 1 : 訂正不可
     */
    public static final String NOT_CHANGEABLE = "1";
}
@
