head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BuyLimitDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 買付制限区分定数定義インタフェイス(WEB3BuyLimitDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 劉江涛(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 買付制限区分 定数定義インタフェイス
 *
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3BuyLimitDivDef
{

    /**
     * 0：買付可能　@　@  　@　@
     */
    public final static String BUY_POSSIBLE = "0";

    /**
     * 1：乗換買付のみ可能
     */
    public final static String ONLY_SWITCHING_BUY_POSSIBLE = "1";
    
    
}@
