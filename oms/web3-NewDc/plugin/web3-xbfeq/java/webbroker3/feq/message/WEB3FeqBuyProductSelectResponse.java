head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄選択レスポンス(WEB3FeqBuyProductSelectResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 中尾寿彦 (SRA) 新規作成
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式銘柄選択レスポンス)<BR>
 * 外国株式銘柄選択レスポンスクラス<BR>
 * 
 * @@author 中尾寿彦(SRA)
 * @@version 1.0
 */
public class WEB3FeqBuyProductSelectResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_productSelect";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511071500L;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了文言を表示する市場コードの一覧。<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (デフォルトコンストラクタ)<BR>
     */
    public WEB3FeqBuyProductSelectResponse()
    {
    }
    
    /**
     * (コンストラクタ)<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqBuyProductSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
