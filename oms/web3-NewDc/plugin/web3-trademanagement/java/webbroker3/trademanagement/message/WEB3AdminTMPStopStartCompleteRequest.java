head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.21.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMPStopStartCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・商品別取扱停止再開変更完了リクエスト(WEB3AdminTMPStopStartCompleteRequest.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （管理者・商品別取扱停止再開変更完了リクエスト）<BR>
 * <BR>
 * 管理者・市場別取引停止再開変更完了レスポンスクラス<BR>
 * <BR>
 * WEB3AdminTMPStopStartCompleteRequest<BR>
 * <BR>
 * WEB3AdminTMPStopStartCompleteRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMPStopStartCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmp_stop_start_chg_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMPStopStartCompleteRequest.class);

    /**
     * （部店コード）<BR>
     * <BR>
     * ※全部店の場合は、nullをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * （branchCode）<BR>
     * <BR>
     * ※Null is set for every branch.<BR>
     */
    public String branchCode;

    /**
     * （暗証番号）<BR>
     * <BR>
     * 暗証番号<BR>
     * <BR>
     * password<BR>
     */
    public String password;

    /**
     * (部店別取扱状況一覧)
     * <BR>
     * 部店別取扱状況一覧<BR>
     */
    public WEB3AdminTMBranchTradingStatusUnit[] branchTradingStatusList;

    /**
     * @@roseuid 41DD3C650001
     */
    public WEB3AdminTMPStopStartCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * 　@this.部店コード != nullの場合は、以下のチェックを行う。<BR>
     * 　@１－１）this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・部店コード.length != 3<BR>
     * 　@　@　@　@　@・部店コード != 数値<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）暗証番号チェック<BR>
     * 　@２－１）this.暗証番号 == nullの場合は、<BR>
     * 　@　@　@　@　@「暗証番号がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * ３）部店別取扱状況一覧のチェック<BR>
     * 　@３－１）this.部店別取扱状況一覧 == nullの場合、<BR>
     * 　@　@　@　@　@「部店別取扱状況一覧がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01427<BR>
     * <BR>
     * 　@３－２）this.部店別取扱状況一覧.length == 0の場合、<BR>
     * 　@　@　@　@　@「部店別取扱状況一覧の要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01426<BR>
     * <BR>
     * 　@３－３）this.部店別取扱状況一覧.lengthの数分、<BR>
     * 　@　@　@　@　@以下の処理を繰り返す。<BR>
     * 　@　@　@　@　@３－３－１）部店別取扱状況.validate()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     *   1-1)If this.branchCode != null<BR>
     *     1-1-1)If "branchCode.length != 3" or<BR>
     *              "branchCode != number"<BR>
     *              Throw the following error [branchCode error]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 2)password check<BR>
     *   2-1)If this.password == null<BR>
     *            Throw the following error [password is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01090<BR>
     * <BR>
     * 3)branchTradingStatusList check<BR>
     *   3-1)If this.branchTradingStatusList ==null<BR>
     *            Throw the following error [branchTradingStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01427<BR>
     * <BR>
     *   3-2)If this.branchTradingStatusList.length == 0<BR>
     *            Throw the following error [branchTradingStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01426<BR>
     * <BR>
     *   3-3)Loop for as many branchTradingStatusList elements<BR>
     *     3-3-1)Call WEB3AdminTMBranchTradingStatusUnit.validate()<BR>
     * @@roseuid 4173850D02EB
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_branchTradingStatusListlength = 0;
        final int l_intThree = 3;
        WEB3AdminTMBranchTradingStatusUnit l_branchTradingStatusUnit = null;

        // 1-1 if branchCode != null check given condition.
        if (this.branchCode != null)
        {
            // 1-1-1 branchCode.length() != 3 OR branchCode is Not Numeric.
            if ((this.branchCode.length() != l_intThree)
                || (!WEB3StringTypeUtility.isNumber(this.branchCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if password = null, throw Exception.
        if (this.password == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 3-1 if branchTradingStatusList = null, throw Exception.
        if (this.branchTradingStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01427,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 3-2 l_branchTradingStatusListlength = 0, throw Exception.
            l_branchTradingStatusListlength = branchTradingStatusList.length;
            if (l_branchTradingStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01426,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 3-3 Loop for as many branchTradingStatusList elements
            for (int i = 0; i < l_branchTradingStatusListlength; i++)
            {
                l_branchTradingStatusUnit = branchTradingStatusList[i];
                //3-3-1 Call WEB3AdminTMBranchTradingStatusUnit.validate().
                l_branchTradingStatusUnit.validate();
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
        return new WEB3AdminTMPStopStartCompleteResponse(this);
    }
}
@
