head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EnableMarketOrderDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 成行可能定数定義インタフェイス(WEB3EnableMarketOrderDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 成行可能 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3EnableMarketOrderDef
{

    /**
     * 0：成行可能（指値／成行）　@　@　@　@  　@　@
     */
    public final static String ENABLE_MARKET_ORDER = "0";

    /**
     * 1：成行不可（指値のみ可）　@　@
     */
    public final static String DISABLE_MARKET_ORDER = "1";
    
}@
