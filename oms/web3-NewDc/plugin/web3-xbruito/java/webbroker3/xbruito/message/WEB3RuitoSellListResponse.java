head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約一覧レスポンスクラス(WEB3RuitoSellListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累投解約一覧レスポンス<BR>
 */
public class WEB3RuitoSellListResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_sell_list";
    
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
    protected WEB3RuitoSellListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * 保有資産明細<BR>
     * （累積投資解約一覧行クラスの配列）<BR>
     */
    public webbroker3.xbruito.message.WEB3RuitoAssetGroup[] ruitoAssetGroups;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922BE8035B
     */
    public WEB3RuitoSellListResponse()
    {

    }
}
@
