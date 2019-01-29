head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧レスポンス(WEB3HistoryTradeHistoryListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25  温 顕 法@(中訊) 新規作成
*/

package webbroker3.tradehistory.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (取引履歴一覧レスポンス)<BR>
 * 取引履歴一覧レスポンスクラス<BR>
 * 
 * @@author 温 顕 法@
 * @@version 1.0 
 */
public class WEB3HistoryTradeHistoryListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeHistoryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410221710L;
         
    /**
     * (取引報告書実施フラグ)<BR>
     * 取引報告書実施フラグ<BR>
     * <BR>
     * false: 未実施<BR>
     * true:　@実施<BR>
     */
    public boolean tradingReportFlag;
    
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
     * (受渡日別残高情報一覧)<BR>
     * 受渡日別残高情報一覧<BR> 
     */
    public WEB3HistoryDailyBalanceUnit[] dailyBalanceUnits;
    
    /**
     * @@roseuid 41789C4B03A9
     */
    public WEB3HistoryTradeHistoryListResponse() 
    {
     
    }
    
    /**
     * デフォルトコンストラクタ<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3HistoryTradeHistoryListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
