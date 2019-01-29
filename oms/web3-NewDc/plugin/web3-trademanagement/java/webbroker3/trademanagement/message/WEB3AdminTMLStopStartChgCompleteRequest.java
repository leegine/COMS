head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLStopStartChgCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン停止再開変更完了リクエスト(WEB3AdminTMLStopStartChgCompleteRequest.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・ログイン停止再開変更完了リクエスト）<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgCompleteRequest<BR>
 * <BR>
 * WEB3AdminTMLStopStartChgCompleteRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMLStopStartChgCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tml_stop_start_chg_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLStopStartChgCompleteRequest.class);
    /**
     * （暗証番号）<BR>
     * <BR>
     * 暗証番号<BR>
     * <BR>
     * password<BR>
     */
    public String password;

    /**
     * (ログイン許可状況一覧)
     * <BR>
     * ログイン許可状況一覧<BR>
     */
    public WEB3AdminTMLoginPermissionStatusUnit[] loginPermissionStatusList;

    /**
     * @@roseuid 41DD356B03DA
     */
    public WEB3AdminTMLStopStartChgCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）暗証番号チェック<BR>
     * 　@１－１）this.暗証番号 == nullの場合、<BR>
     * 　@　@　@　@　@「暗証番号がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * ２）ログイン許可状況一覧チェック<BR>
     * 　@２－１）this.ログイン許可状況一覧 == nullの場合は、<BR>
     * 　@　@　@　@　@「ログイン許可状況一覧がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01416<BR>
     * <BR>
     * 　@２－２）this.ログイン許可状況一覧.length == 0の場合は、<BR>
     * 　@　@　@　@　@「ログイン許可状況一覧の要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01417<BR>
     * <BR>
     * 　@２－３）this.ログイン許可状況一覧の要素数分以下の処理を<BR>
     * 　@　@　@　@　@繰り返す。<BR>
     * 　@　@　@　@　@２－３－１）this.ログイン許可状況.validate()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)password check<BR>
     *   1-1)If this.password == null<BR>
     *           Throw the following error [password is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * 2)loginPermissionStatusList check<BR>
     *   2-1)If this.loginPermissionStatusList == null<BR>
     *           Throw the following error [loginPermissionStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01416<BR>
     * <BR>
     *   2-2)If this.loginPermissionStatusList.length == 0<BR>
     *           Throw the following error [loginPermissionStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01417417417417
     * <BR>
     *   2-3)Loop for as many times as the elements of
     * this.loginPermissionStatusList<BR>
     *      2-3-1)Call this.WEB3AdminTMLoginPermissionStatusUnit.validate()<BR>
     * @@roseuid 417757A9010E
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_loginPermissionStatusListlength = 0;
        WEB3AdminTMLoginPermissionStatusUnit l_loginPermissionStatus = null;

        //Step 1-1 password = null, throw Exception
        if (this.password == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // Step 2-1 loginPermissionStatusList = nul, throw Exception.l
        if (this.loginPermissionStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01416,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else

            // Step 2-2 l_loginPermissionStatusListlength = 0, throw Exception.
            {
            l_loginPermissionStatusListlength = this.loginPermissionStatusList.length;
            if (l_loginPermissionStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01417,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // Step 2-3Loop for as many times as the elements of loginPermissionStatusList
            for (int i = 0; i < l_loginPermissionStatusListlength; i++)
            {
                l_loginPermissionStatus = this.loginPermissionStatusList[i];

                // Step 2-3-1 call validate.
                l_loginPermissionStatus.validate();
            }
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMLStopStartChgCompleteResponse(this);
    }
}
@
