head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondProductSortKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券銘柄のソートキー項目定数定義インタフェイス(WEB3BondProductSortKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 郭英(中訊) 新規作成
*/
package webbroker3.bd.define;

/**
 * 債券銘柄のソートキー項目定数定義インタフェイス
 * 
 * @@author 郭英
 * @@version 1.0 
 */
public interface WEB3BondProductSortKeyItemDef
{
    /**
     * 種別コード
     */
    public static final String BOND_CATEG_CODE = "bond_categ_code";
    
    /**
     * 銘柄コード（SONAR）
     */
    public static final String HOST_PRODUCT_CODE = "host_product_code";
    
    /**
     * 回号コード(SONAR)
     */
    public static final String HOST_PRODUCT_ISSUE_CODE = "host_product_issue_code";
}
@
