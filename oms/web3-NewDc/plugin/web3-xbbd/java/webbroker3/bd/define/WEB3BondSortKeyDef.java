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
filename	WEB3BondSortKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券ソートキーのソートキー項目定数定義インタフェイス(WEB3BondSortKeyDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/25 周墨洋 (中訊) 新規作成 モデル192
*/

package webbroker3.bd.define;

/**
 * (債券ソートキーのソートキー項目定数定義インタフェイス)<BR>
 * 債券ソートキーのソートキー項目定数定義インタフェイス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public interface WEB3BondSortKeyDef
{
    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * 回号コード
     */
    public static final String PRODUCT_ISSUE_CODE = "productIssueCode";

    /**
     * 発行日
     */
    public static final String ISSUE_DATE = "issueDate";

    /**
     * 償還日
     */
    public static final String MATURITY_DATE = "maturityDate";

    /**
     * 利率
     */
    public static final String COUPON = "coupon";

}
@
