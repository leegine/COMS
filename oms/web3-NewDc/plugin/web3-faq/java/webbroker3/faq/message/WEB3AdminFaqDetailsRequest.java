head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者問合せ管理お問合せ詳細リクエスト(WEB3AdminFaqDetailsRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者問合せ管理お問合せ詳細リクエスト)<BR>
 * 管理者問合せ管理お問合せ詳細リクエスト<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminFaqDetailsRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqDetailsRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_faq_details";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171302L;

    /**
     * (問合せコード)<BR>
     * 問合せコード<BR>
     */
    public String faqCode;

    /**
     * (機@能カテゴリコード)<BR>
     * 機@能カテゴリコード<BR>
     */
    public String transactionCategoryCode;

    /**
     * @@roseuid 41C25C090109
     */
    public WEB3AdminFaqDetailsRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFaqDetailsResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@問合せコードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01688<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41AC35E40007
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@問合せコードのチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.faqCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01688, 
                this.getClass().getName() + STR_METHOD_NAME,
                "問合せコードが未指定です。");         
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
