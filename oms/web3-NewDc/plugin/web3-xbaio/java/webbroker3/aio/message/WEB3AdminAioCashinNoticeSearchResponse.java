head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知検索レスポンス(WEB3AdminAioCashinNoticeSearchResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 韋念瓊  (中訊) 新規作成
                 : 2006/8/23 車進(中訊) 仕様変更 モデル 614
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 入金通知検索レスポンス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeSearchResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_aio_cashin_notice_search";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211117L;    
    
    /**
     * 表示ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * 総ページ数<BR>
     */
    public String totalPages;
    
    /**
     * 総レコード数<BR>
     */
    public String totalRecords;
    
    /**
     * 正常明細件数<BR>
     */
    public String normalNumber = "0";
    
    /**
     * エラー明細件数<BR>
     */
    public String errorNumber = "0";
    
    /**
     * 未処理明細件数<BR>
     * <BR>
     */
    public String nonTransactionNumber = "0";
    
    /**
     * 総明細件数<BR>
     */
    public String totalNumber = "0";
    
    /**
     * 入金件数<BR>
     */			
    public String cashinNumber = "0";
    
    /**
     * 出金件数<BR>
     */			
    public String cashoutNumber = "0";

    /**
     * 正常明細合計金額<BR>
     */
    public String normalTotalPrice = "0";

    /**
     * エラー明細合計金額<BR>
     */
    public String errorTotalPrice = "0";
    
    /**
     * 未処理明細合計金額<BR>
     */
    public String nonTransactionTotalPrice = "0";
    
    /**
     * 総明細合計金額<BR>
     */
    public String totalPrice = "0";
    
    /**
     * 入金合計金額<BR>
     */		
    public String cashinTotalPrice = "0";
    
    /**
     * 出金合計金額<BR>
     */
    public String cashoutTotalPrice = "0";
    
    /**
     * 選択通貨コード<BR>
     * 表示用通貨コード<BR>
     */
    public String[] selectCurrencyCode;
    
    /**
     * 入金通知明細一覧<BR>
     */
    public WEB3AioCashinNoticeUnit2[] cashinNoticeList;
    
    /**
     * 外貨サマリ情報一覧<BR>
     */
    public WEB3ForeignSummaryInfo[] foreignSummaryList;
    
    /**
     * (入金通知検索レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40DF7B7B01AC
     */
    public WEB3AdminAioCashinNoticeSearchResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminAioCashinNoticeSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
