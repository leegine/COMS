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
filename	WEB3FaqConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ管理お問合せ確認リクエスト(WEB3FaqConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (問合せ管理お問合せ確認リクエスト)<BR>
 * 問合せ管理お問合せ確認リクエスト<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3FaqConfirmRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqConfirmRequest.class);   
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "faq_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171305L;

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (問合せ情報)<BR>
     * 問合せ情報<BR>
     */
    public WEB3FaqInfo faqInfo;

    /**
     * @@roseuid 41C25C06029F
     */
    public WEB3FaqConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FaqConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@問合せ情報のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01689<BR>
     * 　@１－２）　@問合せ情報.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6F08902EA
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@問合せ情報のチェック
        //１－１）　@未入力の場合、例外をスローする。
        
        if (this.faqInfo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01689, 
                this.getClass().getName() + STR_METHOD_NAME,
                "問合せ情報が未指定です。");  
        }
        
        //１－２）　@問合せ情報.validate()をコールする。
        this.faqInfo.validate();
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
