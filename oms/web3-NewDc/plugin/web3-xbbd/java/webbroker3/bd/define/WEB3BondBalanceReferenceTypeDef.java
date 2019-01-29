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
filename	WEB3BondBalanceReferenceTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会共通照会区分定数定義インタフェイス(WEB3BondBalanceReferenceTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/3/9 齊珂(中訊) 新規作成
Revesion History : 2007/07/17 武波   (中訊) 仕様変更・モデル207
*/

package webbroker3.bd.define;

/**
 * 債券残高照会共通照会区分定数定義インタフェイス
 *
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3BondBalanceReferenceTypeDef
{
    /**
     * １：すべての銘柄
     */
    public static final String ALL_PRODUCT = "1";

    /**
     * ２：外国債券銘柄のみ
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
}@
