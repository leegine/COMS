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
filename	WEB3BondProductSearchKeyItemDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3BondProductSearchKeyItemDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 黄建(中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * キー項目 定数定義インタフェイス
 *                                                                     
 * @@author 黄建
 * @@version 1.0 
 */
public interface WEB3BondProductSearchKeyItemDivDef
{
    /**
     * 発行日
     */
    public static final String ISSUE_DATE = "issueDate";
    
    /**
     * 銘柄コード(WEB3)
     */
    public static final String PRODUCT_CODE = "productCode";
    
    /**
     * 償還日
     */
    public static final String MATURITY_DATE = "maturityDate";
}@
