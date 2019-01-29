head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.22.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMPStopStartReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・商品別取扱停止再開照会リクエスト(WEB3AdminTMPStopStartReferenceRequest.java)
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
 * （管理者・商品別取扱停止再開照会リクエスト）<BR>
 * <BR>
 * 管理者・市場別取引停止再開変更完了レスポンスクラス<BR>
 * <BR>
 * WEB3AdminTMPStopStartReferenceRequest<BR>
 * <BR>
 * WEB3AdminTMPStopStartReferenceRequest class<BR>
 * <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3AdminTMPStopStartReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tmp_stop_start_reference";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501071345L;

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMPStopStartReferenceRequest.class);

    /**
     * （部店コード一覧）<BR>
     * <BR>
     * ※部店権限チェックに使用。<BR>
     * 　@部店権限チェックを行わない場合はnullをセット。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * branchTradingStatusList<BR>
     * <BR>
     * ※It uses it to check the branch authority. <BR>
     *   When the branch authority is not checked, set null.<BR>
     * <BR>
     */
    public String[] branchCodeList = null;

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
     * <BR>
     */
    public String branchCode;

    /**
     * @@roseuid 41DD3CD20040
     */
    public WEB3AdminTMPStopStartReferenceRequest()
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
     * ----<English>--------------------<BR>
     * <BR>
     * The correspondence of this class is checked<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     *  1-1)If this.productCode != null check the following<BR>
     *     1-1-1)If this.productCode.length != 3    or<BR>
     *             productCode.length != number<BR>
     *             throw the following error [productCode error]<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00779<BR>
     * @@roseuid 4173884301D1
     * @@throws WEB3BaseException WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intThree = 3;

        // 1-1 if productCode != null, enter next loop.
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTMPStopStartReferenceResponse(this);
    }
}
@
