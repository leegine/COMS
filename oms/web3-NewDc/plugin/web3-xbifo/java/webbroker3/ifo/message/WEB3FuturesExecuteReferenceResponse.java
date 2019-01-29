head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文約定照会レスポンスクラス(WEB3FuturesExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強(中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数先物注文約定照会レスポンス)<BR>
 * 株価指数先物注文約定照会レスポンスクラス<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesExecuteReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211147L;
    /**
     * (注文約定照会注文単位)<BR>
     */
    public WEB3FuturesExecuteGroup[] futExecuteGroups;
    
    /**
     * (表示ページ番号)<BR>
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (総ページ数)<BR>
     */
    public String totalPages;
    /**
     * (発注日一覧)<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (総レコード数)<BR>
     */
    public String totalRecords;
    
    /**
     * (ID一覧)<BR>
     * 検索条件に該当する全注文ＩＤ<BR>
     * （ソートされた状態）<BR>
     * <BR>
     * ＰＣ版の場合設定<BR>
     */
    public String[] idList;
    
    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (株価指数先物オプション銘柄コード名称)<BR>
     * 検索条件表示用<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    
    /**
     * (顧客ロック区分)<BR>
     * true：ロック顧客である　@false：ロック顧客でない<BR>
     */
    public boolean accountLock;
    
    /**
     * @@roseuid 40F7AE100203
     */
    public WEB3FuturesExecuteReferenceResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesExecuteReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
