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
filename	WEB3AdminPMProductCondConfInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件設定入力リクエスト(WEB3AdminPMProductCondConfInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;

/**
 * (管理者・株式銘柄条件設定入力リクエスト)<BR>
 * <BR>
 * 管理者・株式銘柄条件設定入力リクエストクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondConfInputRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_conf_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    public final static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPMProductCondConfInputRequest.class);

    /**
     * （銘柄コード）<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * （大項目区分一覧） <BR>
     * <BR>
     * 大項目区分一覧 <BR>
     * <BR>
     * ※定義値についてはDBレイアウト <BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * <BR>
     * -----------<English>------------<BR>
     * <BR>
     * largeItemList<BR>
     * <BR>
     * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     */
    public String[] largeItemList;
    /**
     * @@roseuid 41FA29E001E5
     */
    public WEB3AdminPMProductCondConfInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）銘柄コードチェック<BR>
     * 　@１－１）this.銘柄コード == nullの場合、<BR>
     * 　@　@　@　@　@「this.銘柄コードがnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@１－２）this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「入力銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.銘柄コード.length != 5<BR>
     * 　@　@　@　@　@・this.銘柄コード != 数値<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ２）大項目区分チェック<BR>
     * 　@２－１）this.大項目区分 == nullの場合、<BR>
     * 　@　@　@　@　@「大項目区分がnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01447<BR>
     * <BR>
     * 　@２－２）this.大項目区分.length == 0の場合<BR>
     * 　@　@　@　@　@「大項目区分の要素数が0」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01448<BR>
     * <BR>
     * 　@２－３）this.大項目区分の値が、以下の値のいづれにも<BR>
     * 　@　@　@　@　@該当しない場合、「大項目区分が未定義の値」の<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@・"取引規制"<BR>
     * 　@　@　@　@　@・"基本情報"<BR>
     * 　@　@　@　@　@・"信用銘柄情報"<BR>
     * 　@　@　@　@　@・"委託保証金率"<BR>
     * 　@　@　@　@　@・"代用有価証券情報"<BR>
     * 　@　@　@　@　@・"値段情報"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01449<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1) productCode check<BR>
     * 　@1-1)If this.productCode == null<BR>
     * 　@　@　@　@　@Throw the exception "this.productCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@1-2)If this. productCode meets with the following conditions<BR>
     * 　@　@　@　@　@Throw the exception "input productCode  error"<BR>
     * 　@　@　@　@　@・this. productCode.length != 5<BR>
     * 　@　@　@　@　@・this. productCode != numerical value<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 2)largeItemList check<BR>
     * 　@2-1) If this.largeItemList == null<BR>
     * 　@　@　@　@　@Throw the exception "largeItemList is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01447<BR>
     * <BR>
     * 　@2-2) If this.largeItemList.length == 0<BR>
     * 　@　@　@　@　@Throw the exception "elements of largeItemList is 0"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01448<BR>
     * <BR>
     * 　@2-3)The value of this.largeItemList meets neither of the following values,<BR>
     * 　@　@　@　@　@Throw the exception "largeItemList is an undefined value"<BR>
     * 　@　@　@　@　@・"Def.TRADING_REGULATION"<BR>
     * 　@　@　@　@　@・"Def.BASIC_INFO"<BR>
     * 　@　@　@　@　@・"Def.MARGIN_PRODUCT_INFO"<BR>
     * 　@　@　@　@　@・"Def.DEPOSIT_RATE"<BR>
     * 　@　@　@　@　@・"Def.SUBSTITUTE_SECURITY_INFO"<BR>
     * 　@　@　@　@　@・"Def.PRICE_INFO"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01449<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4181A87702E0
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intFive = 5;
        int l_productCodeProductByteLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        int l_intlength = 0;

        // 1-1productCode is null throw exception
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 1-2 if productCode length!=5 and productCode is not numeric throw exception
        }
        if ((l_productCodeProductByteLength != l_intFive)
            || (!WEB3StringTypeUtility.isNumber(this.productCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1if largeItemList is null throw exception
        if (this.largeItemList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01447,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 2-2 largeItemList length is zero throw exception
        }
        if (this.largeItemList.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01448,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            // 2-3 if largeItemList is not equals to following values throw exception
        }
        l_intlength = largeItemList.length;
        for (int i = 0; i < l_intlength; i++)
        {
            if ((!WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.BASIC_INFO.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE.equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO
                .equals(largeItemList[i]))
                && (!WEB3AdminEquityLargeItemDivDef.PRICE_INFO.equals(largeItemList[i])))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01449,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMProductCondConfInputResponse(this);
    }
}
@
