head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付確認リクエスト(WEB3FeqSellConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (外国株式売付確認リクエスト)<BR>
 * 外国株式売付確認リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellConfirmRequest extends WEB3FeqSellCommonRequest 
{
  
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
        
    /**
     * @@roseuid 42CE3A0303B9
     */
    public WEB3FeqSellConfirmRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqSellConfirmResponse(this);
    }
}
@
