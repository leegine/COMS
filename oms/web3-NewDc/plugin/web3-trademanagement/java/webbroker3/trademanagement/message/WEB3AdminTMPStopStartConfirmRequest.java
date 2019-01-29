head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMPStopStartConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・商品別取扱停止再開変更確認リクエスト(WEB3AdminTMPStopStartConfirmRequest.java)
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
 * （管理者・商品別取扱停止再開変更確認リクエスト）<BR>
 * <BR>
 * 管理者・商品別取扱停止再開変更確認リクエストクラス<BR>
 * <BR>
 * WEB3AdminTMPStopStartConfirmRequest<BR>
 * <BR>
 * WEB3AdminTMPStopStartConfirmRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMPStopStartConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmp_stop_start_chg_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMPStopStartConfirmRequest.class);

    /**
     * （部店コード）<BR>
     * <BR>
     * ※全部店の場合は、nullをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     * ※Null is set for every branch. <BR>
     */
    public String branchCode;

    /**
     * (部店別取扱状況一覧)
     * <BR>
     * 部店別取扱状況一覧<BR>
     */
    public WEB3AdminTMBranchTradingStatusUnit[] branchTradingStatusList;

    /**
     * @@roseuid 41DD3C590291
     */
    public WEB3AdminTMPStopStartConfirmRequest()
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
     * ２）部店別取扱状況一覧のチェック<BR>
     * 　@２－１）this.部店別取扱状況一覧 == nullの場合、<BR>
     * 　@　@　@　@　@「部店別取扱状況一覧がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01427<BR>
     * <BR>
     * 　@２－２）this.部店別取扱状況一覧.length == 0の場合、<BR>
     * 　@　@　@　@　@「部店別取扱状況一覧の要素数が0」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01426<BR>
     * <BR>
     * 　@２－３）this.部店別取扱状況一覧.lengthの数分、<BR>
     * 　@　@　@　@　@以下の処理を繰り返す。<BR>
     * 　@　@　@　@　@２－３－１）部店別取扱状況.validate()をコールする。<BR>
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
     * 2)branchTradingStatusList check<BR>
     *   2-1)If this.branchTradingStatusList == null<BR>
     *            Throw the following error [branchTradingStatusList is null]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01427<BR>
     * <BR>
     *   2-2)If this.branchTradingStatusList.length == 0<BR>
     *            Throw the following error [branchTradingStatusList has 0
     * elements]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_01426<BR>
     * <BR>
     *   2-3)Loop for as many branchTradingStatusList elements<BR>
     *      2-3-1)Call branchTradingStatusList.validate()<BR>
     * @@roseuid 417382350397
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_branchTradingStatusListlength = 0;
        WEB3AdminTMBranchTradingStatusUnit l_branchTradingStatusUnit =
            new WEB3AdminTMBranchTradingStatusUnit();
        final int l_intThree = 3;

        // 1-1if branchCode != null, entr next loop.
        if (this.branchCode != null)
        {
            // 1-1-1 if branchCode.length() != 3 OR branchCode is Not Numeric.
            if ((this.branchCode.length() != l_intThree)
                || (!WEB3StringTypeUtility.isNumber(this.branchCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 branchTradingStatusList is null throw Exception.
        if (this.branchTradingStatusList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01427,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_branchTradingStatusListlength = this.branchTradingStatusList.length;

            // 2-2 if l_branchTradingStatusListlength = 0, throw Exception.
            if (l_branchTradingStatusListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01426,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 2-3 Loop for as many branchTradingStatusList elements
            for (int i = 0; i < l_branchTradingStatusListlength; i++)
            {
                l_branchTradingStatusUnit = this.branchTradingStatusList[i];
                // 2-3-1 Call branchTradingStatusList.validate()
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
        return new WEB3AdminTMPStopStartConfirmResponse(this);
    }
}
@
