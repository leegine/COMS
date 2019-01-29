head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioHostSpsecCommodityDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioHostSpsecCommodityDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/18 黄建 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 特定口座強制振替キューテーブルの商品区分　@定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0
 */

public class WEB3AioHostSpsecCommodityDef
{
    /**
     *  1：株
     */
    public static final String EQUITY = "1";
    
    /**
     *  2：投信
     */
    public static final String MUTUAL_FUND = "2";
    
    /**
     *  3：債券
     */
    public static final String BOND = "3";
    
    /**
     *  4：外株
     */
    public static final String FOREIGN_EQUITY = "4";
    
    /**
     *  9：CD
     */
    public static final String CD = "9";
}
@
