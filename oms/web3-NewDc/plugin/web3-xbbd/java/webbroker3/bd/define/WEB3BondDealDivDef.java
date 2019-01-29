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
filename	WEB3BondDealDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引区分  定数定義インタフェイス(WEB3BondDealDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25　@周捷 (中訊) 新規作成
*/
package webbroker3.bd.define;

/**
 * 取引区分 定数定義インタフェイス
 *
 * @@author 周捷 (中訊)
 * @@version 1.0
 */
public class WEB3BondDealDivDef
{
    /**
     *  1: 応募
     */
    public static final String  RECRUIT = "1";
    
    /**
     *  2: 買付
     */
    public static final String  BUY = "2";
    
    /**
     *  3: 売却
     */
    public static final String  SELL = "3";
}
@
