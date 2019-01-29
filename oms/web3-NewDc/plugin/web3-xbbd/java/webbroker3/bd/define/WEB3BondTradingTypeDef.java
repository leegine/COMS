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
filename	WEB3BondTradingTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文種別区分(WEB3BondTradingTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 黄建(中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * 債券注文種別区分 定数定義インタフェイス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public interface WEB3BondTradingTypeDef
{
    /**
     * 401：債券買注文
     */
    public final static String BOND_BUY = "401";

    /**
     * 402：債券売り注文
     */
    public final static String BOND_SELL = "402";
}
@
