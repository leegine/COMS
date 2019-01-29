head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInformationUpdateDetailsInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式銘柄情報更新明細入力リクエスト(WEB3AdminFeqProductInformationUpdateDetailsInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式銘柄情報更新明細入力リクエスト)<BR>
 * 管理者外国株式銘柄情報更新明細入力リクエストクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqProductInformationUpdateDetailsInputRequest extends WEB3GenRequest 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductInformationUpdateDetailsInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productInformationUpdateDetailsInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (銘柄コード)<BR>
     * 画面にて入力された銘柄コード
     */
    public String productCode;
    
    /**
     * @@roseuid 42CE39FA0222
     */
    public WEB3AdminFeqProductInformationUpdateDetailsInputRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）銘柄コード<BR>
     * <BR>
     *    this.銘柄コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00079<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B2B4300202
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@銘柄コード
        // 　@this.銘柄コード == nullの場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.productCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                getClass().getName() + STR_METHOD_NAME,
                "銘柄コードが未入力です。");
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
        return new WEB3AdminFeqProductInformationUpdateDetailsInputResponse(this);
    }
}
@
