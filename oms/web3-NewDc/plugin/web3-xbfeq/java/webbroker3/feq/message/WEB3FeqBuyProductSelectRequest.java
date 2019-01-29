head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄選択リクエスト(WEB3FeqBuyProductSelectRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 中尾寿彦 (SRA) 新規作成 
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式銘柄選択リクエスト)<BR>
 * 外国株式銘柄選択リクエストクラス<BR>
 * 
 * @@author 中尾寿彦(SRA)
 * @@version 1.0
 */
public class WEB3FeqBuyProductSelectRequest extends WEB3GenRequest
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
     * (デフォルトコンストラクタ)<BR>
     */
    public WEB3FeqBuyProductSelectRequest()
    {
    }
    
    /**
     * (createResponse)<BR>
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqBuyProductSelectResponse(this);
    }
}
@
