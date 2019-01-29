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
filename	WEB3BondWarningDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 警告区分インタフェイス(WEB3BondWarningDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 黄建(中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * 警告区分 定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0 
 */

public interface WEB3BondWarningDivDef
{
    /**
     * 1：経過利子が正確でない可能性
     */
    public static final String ACCRUED_INTEREST_WRONG_POSSIBLE = "1";
    
    /**
     * 2：受渡代金が一致しない 　@　@
     */
    public static final String ESTIMATED_PRICE_DIFFERENCE = "2";
    
    /**
     * 3：余力チェックNG
     */
    public static final String TRADE_POWER_CHECK_NG = "3";
    
    /**
     * 4：経過利子計算不可能
     */
    public static final String ACCRUED_INTEREST_CANNOT_CALCULATION = "4";
    
    /**
     * 5：海外市場が発注日に対して非営業日
     */
    public static final String FRGN_ORDERBIZDATE_NOBIZDATE = "5";
}
@
