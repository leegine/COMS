head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.57.28;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentGetRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : 金商法@書面情報取得リクエストクラス(WEB3FPTDocumentGetRequest.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/16 劉レイ(北京中訊) 新規作成 仕様変更モデルNo.354
 */
package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (金商法@書面情報取得リクエスト)<BR>
 * 金商法@書面情報取得リクエストクラス<BR>
 * <BR>
 * @@author 劉レイ(北京中訊)
 * @@version 1.0
 */
public class WEB3FPTDocumentGetRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "fpt_document_get";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011161749L;

    /**
     * (交付対象)<BR>
     * 交付対象<BR>
     * <BR>
     * 0：全顧客<BR>
     * 1：信用開設済顧客<BR>
     * 2：先物・オプション開設済顧客<BR>
     */
    public String deliveryTarget;

    /**
     * コンストラクタ<BR>
     */
    public WEB3FPTDocumentGetRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * 以下の項目のいずれかがnullの場合、例外をスローする。<BR>
     * <BR>
     * 交付対象<BR>
     * <BR>
     * 交付対象かがnullの場合<BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@BUSINESS_ERROR_03222<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);

        if (this.deliveryTarget == null)
        {
            log.debug("交付対象が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03223,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "交付対象が未入力です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FPTDocumentGetResponse(this);
    }
}
@
