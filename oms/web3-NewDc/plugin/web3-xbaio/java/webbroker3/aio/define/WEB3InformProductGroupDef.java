head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3InformProductGroupDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品グループ区分インタフェイス(WEB3InformProductGroupDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/27 凌建平(中訊) 作成
*/

package webbroker3.aio.define;

/**
 * 商品グループ区分インタフェイス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3InformProductGroupDef
{
    
    /**
     * Z:全商品
     */
    public static final String ALL_PRODUCT = "Z";
    
    /**
     * A:株式
     */
    public static final String EQUITY = "A";
    
    /**
     * B:外国株式
     */
    public static final String FOREIGN_EQUITY = "B";
    
    /**
     * C:投信
     */
    public static final String MUTUAL_FUND = "C";

    /**
     * D:債券
     */
    public static final String BOND = "D";
}
@
