head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IpoUnitDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 表示用単位区分定数定義インタフェイス(WEB3IpoUnitDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 表示用単位区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3IpoUnitDivDef
{

    /**
     * 1：株数　@　@　@　@　@　@  　@　@
     */
    public final static String STOCK_QUANTITY = "1";

    /**
     * 2：口数　@　@
     */
    public final static String QUANTITY = "2";
    
}@
