head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MFDealDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引区分 定数定義インタフェイス(WEB3MFDealDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 黄建(中訊)　@新規作成
*/

package webbroker3.mf.define;

/**
 * 取引区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public class WEB3MFDealDivDef
{
    /**
     * 1:買付 
     */
    public static final String BUY = "1";
    
    /**
     * 2:解約
     */
    public static final String SELL = "2";
    
    /**
     * 3:乗換
     */
    public static final String SWITCHING = "3";
    
    /**
     * 4:募集
     */
    public static final String RECRUIT = "4";
}
@
