head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.52.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderPermissionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文認可 定数定義インタフェイス(WEB3OrderPermissionDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
Revesion History : 2004/09/14 鄒 政(sinocom)　@javadocを修正
*/
package webbroker3.common.define;

/**
 * 顧客マスター．注文認可　@定数定義インタフェイス
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3OrderPermissionDef
{
    /**
     * 0 : 認可
     */
    public static final String PERMISSION = "0";

    /**
     * 1 : 認可以外
     */
    public static final String NO_PERMISSION = "1";

}
@
