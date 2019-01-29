head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BuySellSettlementDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済定数定義インタフェイス(WEB3BuySellSettlementDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 張威(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 決済定数定義インタフェイス
 *
 * @@author 張威
 * @@version 1.0
 */
public interface WEB3BuySellSettlementDivDef
{

    /**
     * 0：選択指定　@　@　@　@  　@　@
     */
    public final static String SELECT_DESIGNATE = "0";

    /**
     * 1：円貨
     */
    public final static String JAPANESE_CURRENCY = "1";
    
    /**
     * 2：外貨
     */
    public final static String FOREIGN_CURRENCY = "2";

} 
@
