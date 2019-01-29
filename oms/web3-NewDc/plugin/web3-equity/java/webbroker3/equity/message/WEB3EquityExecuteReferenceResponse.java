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
filename	WEB3EquityExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定照会レスポンス(WEB3EquityExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 張坤芳 (中訊) 新規作成
Revesion History : 2004/12/14 桑原 (SRA) 残案件対応
*/

package webbroker3.equity.message;


import webbroker3.common.message.WEB3GenResponse;
import java.util.Date;

/**
 * （現物株式注文約定照会レスポンス）。<BR>
 * <BR>
 * 現物株式注文約定照会応答　@レスポンスデータクラス<BR>
 * <BR>
 * 現物株式：「注文約定照会」<BR>
 * 現物株式：「訂正取消一覧」の両画面で使用する。
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceResponse extends WEB3GenResponse
{

    /**
     * (銘柄一覧)<BR>
     * （コード・名称を含む）銘柄一覧<BR>
     */
    public webbroker3
        .equity
        .message
        .WEB3EquityProductCodeNameUnit[] productCodeNames;

    /**
     * (市場コード一覧)<BR>
     * 部店の取扱可能な市場コード一覧<BR>
     */
    public String[] marketList;
    
    /**
     * (発注日一覧)<BR>
     * 発注日一覧<BR>
     */
    public Date[] orderBizDateList;
    

    /**
     * (注文情報一覧)<BR>
     * 検索条件に紐付いた注文情報の一覧<BR>
     * （現物株式注文約定照会注文単位の配列）<BR>
     */
    public webbroker3.equity.message.WEB3EquityExecuteGroup[] executeGroups;

    /**
     * (総ページ数)<BR>
     * 該当する全ページ数<BR>
     */
    public String totalPages;

    /**
     * (総レコード数)<BR>
     * 該当する全データ数<BR>
     */
    public String totalRecords;

    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     * （通常は、要求ページ番号がそのままセットされる）<BR>
     */
    public String pageIndex;

    /**
     * (ID一覧)<BR>
     * 指定検索条件に合致する全ての注文IDの一覧。<BR>
     */
    public String[] idList;

    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了警告文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_exec_reference";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405121057L;

    /**
     * @@roseuid 40A298240109
     */
    public WEB3EquityExecuteReferenceResponse(WEB3EquityExecuteReferenceRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * @@roseuid 40A298240109
     */
    public WEB3EquityExecuteReferenceResponse()
    {
    }
}
@
