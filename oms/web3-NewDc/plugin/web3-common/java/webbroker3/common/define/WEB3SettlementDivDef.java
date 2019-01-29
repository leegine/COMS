head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SettlementDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済定数定義インタフェイス(WEB3SettlementDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 決済 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3SettlementDivDef
{
    /**
     * 0：通常決済
     */
    public static final String NORMAL = "0";

    /**
     * 1：円貨　@　@  　@　@
     */
    public final static String JAPANESE_CURRENCY = "1";

    /**
     * 2：外貨
     */
    public final static String FOREIGN_CURRENCY = "2";

} @
