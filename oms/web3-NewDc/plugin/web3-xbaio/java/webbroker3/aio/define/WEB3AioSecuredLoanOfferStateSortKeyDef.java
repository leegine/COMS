head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecuredLoanOfferStateSortKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioSecuredLoanOfferStateSortKeyDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/20 柴双紅（中訊）新規作成 仕様変更・モデルNo.756
*/

package webbroker3.aio.define;

/**
 * (担保ローン申込状況ソートキー区分)<BR>
 * 担保ローン申込状況ソートキー区分<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public interface WEB3AioSecuredLoanOfferStateSortKeyDef
{
    /**
     * accountCode： 顧客コード<BR>
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * stockLoanAccount： ストックローン口座番号<BR>
     */
    public static final String STOCK_LOAN_ACCOUNT = "stockLoanAccount";

    /**
     * applyDate： 申込日<BR>
     */
    public static final String APPLY_DATE = "applyDateFrom";
}
@
