head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付一覧レスポンス(WEB3EquitySellListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 任林林 (中訊) 新規作成
Revesion History : 2004/12/07 岡村和明(SRA) 残案件対応 Ｎｏ.０４６
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式売付一覧レスポンス）。<BR>
 * <BR>
 * 現物株式保有資産一覧照会応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquitySellListResponse extends WEB3GenResponse
{

    /**
     * 銘柄一覧<BR>
     * （コード・名称を含む）銘柄一覧<BR>
     */
    public WEB3EquityProductCodeNameUnit[] productCodeNames;

    /**
     * (市場コード一覧)<BR>
     * 市場コードの配列
     */
    public String[] marketList;

    /**
     * 明細<BR>
     * 現物株式保有資産一覧照会保有資産オブジェクトの配列<BR>
     */
    public WEB3EquityAssetUnit[] equityAssetUnits;

    /**
     * 総ページ数<BR>
     * 該当する全ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 該当する全データ数<BR>
     */
    public String totalRecords;

    /**
     * 表示ページ番号
     * （通常は、要求ページ番号がそのままセットされる）
     */
    public String pageIndex;

    /**
     * 取引終了警告市場コード一覧<BR>
     * 取引終了警告文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405101030L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_sell_list";

    /**
     * @@roseuid 409F5EA30110
     */
    public WEB3EquitySellListResponse()
    {

    }
    /**
     * コンストラクタ。<BR>
     * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@paramWEB3EquityAssetInquiryRequest l_request
     */
    public WEB3EquitySellListResponse(WEB3EquitySellListRequest l_request)
    {
        super(l_request);
    }
}
@
