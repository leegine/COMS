head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済一覧画面表示レスポンスクラス(WEB3OptionsCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 新規作成
              001: 2004/08/04 王暁傑 対応バッグ U00016
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション返済一覧画面表示レスポンス)<BR>
 * 株価指数オプション返済一覧画面表示レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginList";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406101442L;
        
    /**
     * 株価指数オプション返済一覧行
     */
    public WEB3OptionsCloseMarginGroup[] closeMarginGroups;
    
    /**
     * 実際に表示するページ位置を指定　@※先頭ページを"1"とする<BR>
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
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;
    
    /**
     * 株価指数先物オプション銘柄コード名称<BR>
     * (検索条件表示に使用)<BR>
     */
    //Start 2004/08/04 王暁傑 対応バッグ U00016
    //public WEB3FuturesOptionsProductCodeNameUnit[] opProductCodeNames;
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    //End 2004/08/04 王暁傑 対応バッグ U00016
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsCloseMarginListResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsCloseMarginListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
