head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せ詳細リクエスト(WEB3AdminAccOpenStateInquiryDetailRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設状況問合せ詳細リクエスト)<BR>
 * 管理者口座開設状況問合せ詳細リクエスト<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenStateInquiryDetailRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenStateInquiryDetailRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081607L;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * @@roseuid 41B45E760280
     */
    public WEB3AdminAccOpenStateInquiryDetailRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenStateInquiryDetailResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@検索条件のチェック<BR>
     * 　@１－１）　@識別コード，顧客コードの両方が未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01332<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C33A6008F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //１）　@検索条件のチェック
        //１－１）　@識別コード，顧客コードの両方が未入力の場合、例外をスローする。
        if ((this.requestNumber == null || "".equals(this.requestNumber.trim())) && 
        (this.accountCode == null || "".equals(this.accountCode.trim())))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01332,
                getClass().getName() + STR_METHOD_NAME,
                " 識別コード、顧客コードの両方が未指定です。" + 
                this.requestNumber + ", " + this.accountCode);
        }
        
        log.exiting(STR_METHOD_NAME);

    }

}
@
