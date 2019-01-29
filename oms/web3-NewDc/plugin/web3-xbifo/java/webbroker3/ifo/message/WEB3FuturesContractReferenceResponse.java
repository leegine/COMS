head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物建玉照会レスポンス(WEB3FuturesContractReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 李媛 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数先物建玉照会レスポンス)<BR>
 * 株価指数先物建玉照会画面表示レスポンスクラス<BR>
 *                                                                     
 * @@author 李媛
 * @@version 1.0
 */
public class WEB3FuturesContractReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_contractReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407201058L;
    
    /**
     * (建玉照会明細)
     */
    public WEB3FuturesContractReferenceUnit[] contractReferenceUnits;
    
    /**
     * (表示ページ番号)<BR>
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (総ページ数)
     */
    public String totalPages;
    
    /**
     * (総レコード数)
     */
    public String totalRecords;
    
    /**
     * 株価指数先物銘柄コード名称<BR>
     * (検索条件表示に使用)<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    
    /**
     * (顧客ロック区分)<BR>
     * true：ロック顧客<BR>
     * false：ロック顧客ではない<BR>
     */
    public boolean accountLock;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3FuturesContractReferenceResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesContractReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
