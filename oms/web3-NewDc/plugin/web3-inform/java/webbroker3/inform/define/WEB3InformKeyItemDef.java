head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : キーアイテムインタフェイスクラス(WEB3InformKeyItemDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
Revesion History    : 2005/05/28 李木子(中訊) モデルNo.045,No.046
Revesion History    : 2008/03/04 謝旋(中訊) モデルNo.130
*/

package webbroker3.inform.define;

/**
 * (キーアイテムインタフェイス)<BR>
 * キーアイテムインタフェイスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3InformKeyItemDef
{
    /**
     * 0 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * 1 識別コード
     */
    public static final String REQUEST_NUMBER = "requestNumber";

    /**
     * 2 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * 3 受付日時
     */
    public static final String ACCEPT_TIME = "receptionDate";

    /**
     * 4 扱者コード
     */
    public static final String TRADER_CODE = "traderCode";

    /**
     * 5 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";
    
    /**
     * 6 登録年月日
     */
    public static final String REGIST_DATE = "registDate";

    /**
     * 7 申込日時
     */
    public static final String APPLY_DATE = "applyDate";
}@
