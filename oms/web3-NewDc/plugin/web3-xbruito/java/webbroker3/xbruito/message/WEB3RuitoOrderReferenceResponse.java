head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資注文照会レスポンスクラス(WEB3RuitoOrderReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累積投資注文照会レスポンスクラス<BR>
 */
public class WEB3RuitoOrderReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_order_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3RuitoOrderReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * 注文情報一覧<BR>
     */
    public webbroker3.xbruito.message.WEB3RuitoOrderGroup[] ruitoOrderGroups;

    /**
     * 表示ページ番号<BR>
     * 実際に表示するページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
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
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C9403C8
     */
    public WEB3RuitoOrderReferenceResponse()
    {

    }
}
@
