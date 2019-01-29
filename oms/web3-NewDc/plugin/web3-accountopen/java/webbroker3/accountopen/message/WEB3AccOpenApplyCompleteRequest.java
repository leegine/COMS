head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込完了リクエスト(WEB3AccOpenApplyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (口座開設申込完了リクエスト)<BR>
 * 口座開設申込完了リクエスト<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenApplyCompleteRequest extends WEB3GenRequest
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenApplyCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_applyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081612L;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (口座開設申込情報)<BR>
     * 口座開設申込情報<BR>
     */
    public WEB3AccOpenApplyInfo accoutOpenApplyInfo;

    /**
     * @@roseuid 41B45E7C0261
     */
    public WEB3AccOpenApplyCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenApplyCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@口座開設申込情報のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01336<BR>
     * <BR>
     * ２）　@口座開設申込情報.証券会社コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01338<BR>
     * <BR>
     * ３）　@口座開設申込情報.部店コードのチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01339<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C947F014B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@口座開設申込情報のチェック<BR>
        //　@１－１）　@未入力の場合、例外をスローする。
        if (this.accoutOpenApplyInfo == null)
        {
            log.debug("口座開設申込情報が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01336,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設申込情報が未指定です。");
        }
        
        //２）　@口座開設申込情報.証券会社コードのチェック
        //　@２－１）　@未入力の場合、例外をスローする。
        if (this.accoutOpenApplyInfo.institutionCode == null || "".equals(this.accoutOpenApplyInfo.institutionCode.trim()))
        {
            log.debug("口座開設申込情報の証券会社コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01338,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設申込情報の証券会社コードが未指定です。");
        }

        //３）　@口座開設申込情報.部店コードのチェック
        //　@３－１）　@未入力の場合、例外をスローする。
        if (this.accoutOpenApplyInfo.branchCode == null || "".equals(this.accoutOpenApplyInfo.branchCode.trim()))
        {
            log.debug("口座開設申込情報の部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01339,
                this.getClass().getName() + STR_METHOD_NAME,
                "口座開設申込情報の部店コードが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
}
@
