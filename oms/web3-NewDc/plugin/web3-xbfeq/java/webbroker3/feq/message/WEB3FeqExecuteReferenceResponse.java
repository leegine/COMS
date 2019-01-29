head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文約定照会レスポンス(WEB3FeqExecuteReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式注文約定照会レスポンス)<BR>
 * 外国株式注文約定照会レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqExecuteReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (銘柄情報一覧)<BR>
     * 外国株式銘柄情報の配列<BR>
     */
    public WEB3FeqProductCodeNameUnit[] productCodeNames;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コードの配列<BR>
     */
    public String[] marketList;
    
    /**
     * (発注日一覧)<BR>
     * 発注日の配列<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (注文一覧)<BR>
     * 外国株式注文明細の配列<BR>
     */
    public WEB3FeqExecuteGroup[] executeGroups;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;
    
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
     * @@roseuid 42CE3A09003E
     */
    public WEB3FeqExecuteReferenceResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqExecuteReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
