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
filename	WEB3BondProductDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品区分定数定義インタフェイス(WEB3BondProductDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/10 周墨洋 (中訊) 新規作成
*/

package webbroker3.bd.define;

/**
 * 商品区分定数定義インタフェイス
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public interface WEB3BondProductDivDef
{

    /**
     * 1：すべての銘柄
     */
    public static final String ALL_PRODUCT = "1";

    /**
     * 2：外国債券銘柄のみ
     */
    public static final String FOREIGN_BOND_ONLY = "2";

    /**
     * 3：国内債券(個人向け国債を含む）
     */
    public static final String DOMESTIC_BOND = "3";

    /**
     * 4：国内債券(個人向け国債を含まない）
     */
    public static final String DOMESTIC_BOND_EXCEPT_INDIVIDUAL = "4";

    /**
     * 5：個人向け国債のみ
     */
    public static final String DOMESTIC_BOND_INDIVIDUAL = "5";

}
@
