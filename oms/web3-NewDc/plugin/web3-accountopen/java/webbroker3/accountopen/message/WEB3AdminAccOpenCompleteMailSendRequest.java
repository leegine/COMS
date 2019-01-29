head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenCompleteMailSendRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設完了メール送信リクエスト(WEB3AdminAccOpenCompleteMailSendRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 張学剛 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設完了メール送信リクエスト)<BR>
 * 管理者口座開設完了メール送信リクエスト<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminAccOpenCompleteMailSendRequest extends WEB3GenRequest
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenCompleteMailSendRequest.class);
    
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_completeMailSend";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081608L;

    /**
     * (部店コード)<BR>
     * ＭＬ送信対象の部店コード配列<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * ＭＬ送信対象の顧客コード配列<BR>
     */
    public String[] accountCode;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 41B45E750213
     */
    public WEB3AdminAccOpenCompleteMailSendRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenCompleteMailSendResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コード[]，顧客コード[]のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00835<BR>
     * 　@１－２）　@部店コード配列と顧客コード配列で、<BR>
     * 要素数が違っていれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01322<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A5AAD50323
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード[]，顧客コード[]のチェック
        //１－１）部店コード[]　@未入力の場合、例外をスローする。
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                "部店コード[]未入力の場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //１－１）顧客コード[]　@未入力の場合、例外をスローする。
        if (this.accountCode == null || this.accountCode.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME,
                "顧客コード[]未入力の場合、例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        //１－２)部店コード配列と顧客コード配列で、要素数が違っていれば例外をスローする。
        if (this.branchCode.length != this.accountCode.length)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01322,
                getClass().getName() + STR_METHOD_NAME,
                "部店コード配列と顧客コード配列で、要素数が違っていれば例外をスローする");
                
            log.debug("口座開設.", l_e);               
            log.exiting(STR_METHOD_NAME);            
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}@
