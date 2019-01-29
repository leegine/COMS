head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文約定照会レスポンス(WEB3OptionsExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;


/**
 * (株価指数オプション注文約定照会レスポンス)<BR>
 * 株価指数オプション注文約定照会レスポンスクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsExecuteReferenceResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111108L;

    /**
     * 注文約定照会注文単位
     */
    public WEB3OptionsExecuteGroup[] opExecuteGroups;

    /**
     * （表示ページ番号)
     * <BR>
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * 総ページ数
     */
    public String totalPages;

    /**
     * 総レコード数
     */
    public String totalRecords;

    /**
     * (ID一覧)<BR>
     * <BR>
     * 検索条件に該当する全注文ＩＤ<BR>
     * （ソートされた状態）<BR>
     * <BR>
     * ＰＣ版の場合設定<BR>
     */
    public String[] idList;
    /**
     * 発注日一覧<BR>
     * 検索条件表示用<BR>
     */
    public Date[] orderBizDateList;

    /**
     * (取引終了警告文言)<BR>
     * <BR>
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;

    /**
     * (株価指数先物オプション銘柄コード名称)<BR>
     * <BR>
     * 検索条件表示用<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;

    /**
     * (顧客ロック区分)<BR>
     * <BR>
     * true：ロック顧客である　@false：ロック顧客でない<BR>
     */
    public boolean accountLock;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsExecuteReferenceResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsExecuteReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
