head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文約定詳細レスポンス(WEB3FeqExecuteDetailsResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式注文約定詳細レスポンス)<BR>
 * 外国株式注文約定詳細レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqExecuteDetailsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_executeDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (注文詳細)<BR>
     * 注文詳細オブジェクト<BR>
     */
    public WEB3FeqOrderDetailInfoUnit orderDetailInfoUnit;
    
    /**
     * (約定詳細)<BR>
     * 外国株式約定詳細情報オブジェクト<BR>
     */
    public WEB3FeqExecuteDetailInfoUnit executeDetailInfoUnit;
    
    /**
     * @@roseuid 42CE3A0801C5
     */
    public WEB3FeqExecuteDetailsResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqExecuteDetailsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
