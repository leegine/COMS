head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoSortKeyItemNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金不足状況ソートキー項目 定義インタフェイス（WEB3AdminIfoSortKeyItemNameDef.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/03 李玉玲（中訊）新規作成
*/
package webbroker3.ifoadmin.define;

/**
 * 証拠金不足状況ソートキー項目 定義インタフェイス
 *
 * @@author 李玉玲
 * @@version 1.0
 */
public interface WEB3AdminIfoSortKeyItemNameDef
{

    /**
     * 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * 請求額
     */
    public static final String CLAIM_AMOUNT = "claimAmount";

    /**
     * 現在未入金額
     */
    public static final String CUR_NON_PAY_AMT = "curNonPayAmt";

    /**
     * 現在証拠金所要額
     */
    public static final String CUR_IFO_DEPOSIT_NECESSARY_AMT = "curIfoDepositNecessaryAmt";

    /**
     * 建玉有無フラグ
     */
    public static final String CONTRACT_EXIST_FLAG = "contractExistFlag";

    /**
     * 注文有無フラグ
     */
    public static final String ORDER_EXIST_FLAG = "orderExistFlag";

}
@
