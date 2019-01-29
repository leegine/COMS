head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellPossDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 売却可能区分　@定数定義インタフェイス(WEB3BondSellPossDibDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/23 周捷(中訊) 新規作成
*/
package webbroker3.bd.define;

/**
 * 売却可能区分　@定数定義インタフェイス
 * 
 * @@author 周捷
 * @@version 1.0 
 */
public class WEB3BondSellPossDivDef
{
    /**
     * 0：不可
     */
    public static final String SELL_POSS_NO = "0";
    
    /**
     * 1：可
     */
    public static final String SELL_POSS = "1";
}
@
