head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOffFloorProductListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 立会外分売銘柄一覧レスポンス(WEB3EquityOffFloorProductListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 岡村和明(SRA) 新規作成
                   2004/12/29 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （立会外分売銘柄一覧レスポンス）。<BR>
 * <BR>
 * 立会外分売銘柄一覧応答　@レスポンスデータクラス
 * @@author 岡村和明(SRA) 
 * @@version 1.0
 */
public class WEB3EquityOffFloorProductListResponse extends WEB3GenResponse
{

    /**
     * （serialVersionUID）。
     */
    public static final long serialVersionUID = 200412171000L;

    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "equity_off_floor_product_list";
    
    /**
     * （銘柄一覧）。<BR>
     * <BR>
     * 立会外分売銘柄一覧
     */
    public WEB3EquityOffFloorProductGroup[] productList;
    
    /**
     * （取引終了警告市場コード一覧）。<BR>
     * <BR>
     * 取引終了警告文言を表示する市場コードの一覧
     */
    public String[] messageSuspension;
    
    /**
     * （コンストラクタ）。<BR>
     * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。
     * @@paramWEB3EquityAssetInquiryRequest l_request
     */
    public WEB3EquityOffFloorProductListResponse(WEB3EquityOffFloorProductListRequest l_request)
    {
        super(l_request);
    }
}
@
