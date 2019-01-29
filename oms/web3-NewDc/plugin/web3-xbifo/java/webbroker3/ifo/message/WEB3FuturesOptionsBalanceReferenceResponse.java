head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物オプション残高照会レスポンスクラス(WEB3FuturesOptionsBalanceReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株価指数先物オプション残高照会レスポンス)<BR>
 * 株価指数先物オプション残高照会レスポンスクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceResponse extends WEB3GenResponse 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReference";
    
    /**
     * (残高照会明細)<BR>
     * 株価指数先物オプション残高照会明細の配列<BR>
     */
    public WEB3FuturesOptionsDetailUnit[] balanceReference;
    
    /**
     * 株価指数先物オプション銘柄コード名称<BR>
     * (検索条件表示に使用)<BR>
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] futOpProductCodeNames;
    
    /**
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex = "0";
    
    /**
     * 総ページ数<BR>
     */
    public String totalPages = "0";
    
    /**
     * 総レコード数<BR> 
     */
    public String totalRecords = "0";
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesOptionsBalanceReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
