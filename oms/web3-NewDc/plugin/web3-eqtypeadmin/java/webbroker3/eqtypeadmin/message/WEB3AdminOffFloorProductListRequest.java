head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄一覧リクエスト(WEB3AdminOffFloorProductListRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者立会外分売銘柄一覧リクエスト)<BR>
 * <BR>
 * 管理者立会外分売銘柄一覧サービスのリクエストデータ。<BR>
 * <BR>
 * -----<English>-----------<BR>
 * <BR>
 * WEB3AdminOffFloorProductListRequest<BR>
 * <BR>
 * request data of WEB3AdminOffFloorProductListService<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorProductListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_off_floor_product_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorProductListRequest.class);

    /**
     * (ソートキー)<BR>
     * <BR>
     * 管理者立会外分売ソートキーの一覧<BR>
     * <BR>
     * 対象項目：銘柄コード、市場コード、受付開始日時、受付終了日時<BR>
     * <BR>
     * ----<English>------------<BR>
     * <BR>
     * sortKeys<BR>
     * <BR>
     * a list of sortKeys for administrator: off floor<BR>
     * <BR>
     * items: productCode, marketCode, orderStartDatetime, orderEndDatetime<BR>
     * <BR>
     */
    public webbroker3.eqtypeadmin.message.WEB3AdminOffFloorSortKey[] sortKeys;

    /**
     * @@roseuid 421AE24C01F7
     */
    public WEB3AdminOffFloorProductListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ソートキーのチェック<BR>
     * 　@１－１）this.ソートキー＝nullであった場合<BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@１－２）this.ソートキー.要素数＝０だった場合<BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１－３）this.ソートキーの全要素に対して<BR>
     * 　@　@　@　@下記のチェックを行う。<BR>
     * 　@　@１－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@１－３－２）ソートキーの配列の個数分、繰り返してチェックを行う。<BR>
     * 　@　@　@　@　@　@　@　@　@以下の項目名以外が存在した場合、例外とする。<BR>
     * 　@　@　@　@・銘柄コード<BR>
     * 　@　@　@　@・市場コード<BR>
     * 　@　@　@　@・受付開始日時<BR>
     * 　@　@　@　@・受付終了日時<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)sortKeys check<BR>
     * 　@1-1)If this.sorKeys＝null<BR>
     * 　@　@　@　@Throw the exception "sortKeys is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@1-2)If the number of elements of this.sortKeys = 0,<BR>
     * 　@　@　@　@Throw the exception "the number of elements of this.sortKeys is 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@1-3)Check the followings about all elements of this.sortKeys<BR>
     * <BR>
     * 　@　@1-3-1)Call sortKeys.validate()<BR>
     * <BR>
     * 　@　@1-3-2)Repeat the check for as many times as the number of arrays of
     * sortKeys<BR>
     * 　@　@　@　@　@　@　@　@　@If there is an item name other than the following item
     * names<BR>
     *                     Throw an exception<BR>
     * 　@　@　@　@・productCode<BR>
     * 　@　@　@　@・marketCode<BR>
     * 　@　@　@　@・orderStartDatetime<BR>
     * 　@　@　@　@・orderEndDatetime<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
     * @@roseuid 41BD053003A5
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_intSortKeyLength = 0;

        //1-1) If this.sorKeys＝null, throw Exception.
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        /*
         * 1-2) If the number of elements of this.sortKeys = 0,<BR>
         * throw the exception
         */
        l_intSortKeyLength = sortKeys.length;
        if (l_intSortKeyLength == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 1-3-1) Call sortKeys.validate()
        } else
        {
            for (int i = 0; i < l_intSortKeyLength; i++)
            {
                sortKeys[i].validate();
            }

            /* 1-3-2)Repeat the check for as many times as the number of arrays of
            * sortKeys
            *  If there is an item name other than the following item
            * names  Throw an exception
            * productCode
            * marketCode
            * orderStartDatetime
            * orderEndDatetime
            **/
            for (int i = 0; i < l_intSortKeyLength; i++)
            {
                if ((!"productCode".equals(sortKeys[i].keyItem))
                    && (!"marketCode".equals(sortKeys[i].keyItem))
                    && (!"orderStartDatetime".equals(sortKeys[i].keyItem))
                    && (!"orderEndDatetime".equals(sortKeys[i].keyItem)))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminOffFloorProductListResponse(this);
    }
}
@
