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
filename	WEB3EquityMarginExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式・信用注文約定照会レスポンス(WEB3EquityMarginExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 関博(中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (株式・信用注文約定照会レスポンス)<BR>
 * 株式・信用注文約定照会レスポンスクラス<BR>
 * @@author  関博
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceResponse extends WEB3GenResponse
{

    /**
     * ログ出力。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceResponse.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_margin_execute_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701101134L;

    /**
     * (市場コード一覧)<BR>
     * 市場コード一覧<BR>
     */
    public String[] marketList;

    /**
     * (発注日一覧)<BR>
     * 発注日一覧<BR>
     */
    public Date[] orderBizDateList;

    /**
     * (注文情報一覧)<BR>
     * 注文情報一覧<BR>
     */
    public WEB3EquityMarginExecuteGroup[] executeGroups;

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
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ID一覧)<BR>
     * 検索条件に該当する全注文ＩＤ<BR>
     * （ソートされた状態）<BR>
     * <BR>
     * PC版の場合設定<BR>
     */
    public WEB3EquityMarginOrderIdUnit[] idList;

    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了警告文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;

    /**
     * コンストラクタ。<BR>
     * 引数で与えられたリクエストオブジェクトを基に
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     * @@roseuid 45A33C7A03C8
     */
    public WEB3EquityMarginExecuteReferenceResponse(WEB3EquityMarginExecuteReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
