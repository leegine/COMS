head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashTransferListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧結果レスポンス(WEB3AdminAioCashTransferListResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 何文敏 (中訊) 新規作成　@仕様変更モデル NO.693、NO.695
Revision History : 2007/02/16 長瀬亜紀 (SCS) 仕様変更・実装の問題 NO.006
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (入出金一覧結果レスポンス)<BR>
 * 入出金一覧結果レスポンスクラス<BR>
 *
 * @@author 何文敏 (中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cash_transfer_list";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * (SONAR入金合計金額)<BR>
     * SONAR入金合計金額<BR>
     */
    public String sonarCashinTotal = "0";

    /**
     * (バーチャル入金合計金額)<BR>
     * バーチャル入金合計金額<BR>
     */
    public String virtualCashinTotal = "0";

    /**
     * (ネット入金合計金額)<BR>
     * ネット入金合計金額<BR>
     */
    public String netCashinTotal = "0";

    /**
     * (出金合計金額)<BR>
     * 出金合計金額<BR>
     */
    public String cashoutTotal = "0";

    /**
     * (振替（預り金から株先証拠金）合計金額)<BR>
     * 振替（預り金から株先証拠金）合計金額<BR>
     */
    public String transferTotalDepositToMargin = "0";

    /**
     * (振替（株先証拠金から預り金）合計金額)<BR>
     * 振替（株先証拠金から預り金）合計金額<BR>
     */
    public String transferTotalMarginToDeposit = "0";

    /**
     * (為替保証金振替（預り金から為替保証金）合計金額)<BR>
     * 為替保証金振替（預り金から為替保証金）合計金額<BR>
     */
    public String fxTotalDepositToGuaranty = "0";

    /**
     * (為替保証金振替（為替保証金から預り金）合計金額)<BR>
     * 為替保証金振替（為替保証金から預り金）合計金額<BR>
     */
    public String fxTotalGuarantyToDeposit = "0";

    /**
     * (その他振替（預り金からX）合計金額)<BR>
     * その他振替（預り金からX）合計金額<BR>
     */
    public String otherTotalAccountBalanceToX = "0";

    /**
     * (その他振替（Xから預り金）合計金額)<BR>
     * その他振替（Xから預り金）合計金額<BR>
     */
    public String otherTotalXToAccountBalance = "0";

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords;

    /**
     * (入出金一覧明細一覧)<BR>
     * 入出金一覧明細一覧<BR>
     */
    public WEB3AioAdminCashTransferListUnit[] cashTransferListUnits;

    /**
     * @@roseuid 45C3F157035B
     */
    public WEB3AdminAioCashTransferListResponse() 
    {

    }
    
    /**
     * @@roseuid 4158EB66008E
     */
    public WEB3AdminAioCashTransferListResponse(WEB3AdminAioCashTransferListRequest l_request) 
    {
        super(l_request);
    }
}
@
