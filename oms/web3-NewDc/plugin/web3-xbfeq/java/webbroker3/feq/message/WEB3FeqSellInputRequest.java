head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付入力リクエスト(WEB3FeqSellInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式売付入力リクエスト)<BR>
 * 外国株式売付入力リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellInputRequest extends WEB3GenRequest 
{   
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (保有資産ID)<BR>
     * 保有資産ID<BR>
     */
    public String assetId;
    
    /**
     * @@roseuid 42CE3A0302EE
     */
    public WEB3FeqSellInputRequest() 
    {
     
    }
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellInputRequest.class);
        
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）保有資産ID<BR>
     * <BR>
     *    this.保有資産ID == null<BR>
     * <BR>
     *  の場合、「保有資産IDがnull」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01919<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 42944D3B00F9
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）保有資産IDチェック
        //１－１）保有資産IDがnullの場合、「保有資産IDがnull」の例外をスローする。
        if (this.assetId == null)
        {
            log.debug("保有資産IDが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + STR_METHOD_NAME,
                "保有資産IDが未指定(null)です。" + this.assetId);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqSellInputResponse(this);
    }
}
@
