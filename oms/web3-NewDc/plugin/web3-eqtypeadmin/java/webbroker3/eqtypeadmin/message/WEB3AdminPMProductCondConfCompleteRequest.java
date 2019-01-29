head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondConfCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件設定完了リクエスト (WEB3AdminPMProductCondConfCompleteRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式銘柄条件設定完了リクエスト)<BR>
 * <BR>
 * 管理者・株式銘柄条件設定完了リクエストクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondConfCompleteRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMProductCondConfCompleteRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_conf_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondConfCompleteRequest.class);

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
     * （暗証番号）<BR>
     * <BR>
     * 暗証番号<BR>
     * <BR>
     * password<BR>
     * <BR>
     */
    public String password;

    /**
     * （取引規制一覧）<BR>
     * <BR>
     * 株式銘柄取引規制の一覧<BR>
     * <BR>
     * tradingRegulation list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] tradingRegulationList;

    /**
     * （基本情報一覧）<BR>
     * <BR>
     * 株式銘柄基本情報の一覧<BR>
     * <BR>
     * basicInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] basicInfoList;

    /**
     * （信用銘柄情報一覧）<BR>
     * <BR>
     * 株式銘柄信用銘柄情報の一覧<BR>
     * <BR>
     * stockMarginInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] stockMarginInfoList;

    /**
     * （委託保証金率一覧）<BR>
     * <BR>
     * 株式銘柄委託保証金率の一覧<BR>
     * <BR>
     * depositRate list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] depositRateList;

    /**
     * （代用有価証券情報一覧）<BR>
     * <BR>
     * 株式銘柄代用有価証券情報の一覧<BR>
     * <BR>
     * substituteSecurityInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] substituteSecurityInfoList;

    /**
     * （株式銘柄値段情報の一覧）<BR>
     * <BR>
     * 値段情報一覧<BR>
     * <BR>
     * priceInfo list<BR>
     * <BR>
     */
    public WEB3AdminPMProductCondConfigUnit[] priceInfoList;

    /**
     * @@roseuid 41FA29F8039A
     */
    public WEB3AdminPMProductCondConfCompleteRequest()
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
     * ２）確認内容チェック<BR>
     * 　@２－１）以下のクラス変数が全てnullの場合、<BR>
     * 　@　@　@　@　@「確認内容がnull」の例外をスローする。<BR>
     * 　@　@　@　@　@・取引停止一覧<BR>
     * 　@　@　@　@　@・基本情報一覧<BR>
     * 　@　@　@　@　@・信用銘柄情報一覧<BR>
     * 　@　@　@　@　@・委託保証金一覧<BR>
     * 　@　@　@　@　@・代用有価証券一覧<BR>
     * 　@　@　@　@　@・値段情報一覧<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01440<BR>
     * <BR>
     * 　@２－２）上記クラス変数について、nullでないデータは<BR>
     * 　@　@　@　@　@データの要素(=銘柄条件設定情報)数分、<BR>
     * 　@　@　@　@　@以下の処理を繰り返す。<BR>
     * 　@　@　@２－２－１）銘柄条件設定情報.validate()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * Changed as per 【株管理者（インド発注）】仕様変更管理台帳_English.xls
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     * 　@1-1) If this.productCode == null<BR>
     * 　@　@　@　@　@Throw the exception "this.productCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@1-2) If this.productCode meets with the following conditions<BR>
     * 　@　@　@　@　@Throw the exception " input productCode error"<BR>
     * 　@　@　@　@　@・this.productCode.length != 5<BR>
     * 　@　@　@　@　@・this.productCode != numerical value<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 2)confirm content check<BR>
     * 　@2-1) If the following class variables are all null<BR>
     * 　@　@　@　@　@Throw the exception "the confirm content is null"<BR>
     *            ・tradingRegulationList<BR>
     *            ・basicInfoList<BR>
     *            ・stockMarginInfoList<BR>
     *            ・depositRateList<BR>
     *            ・substituteSecurityInfoList<BR>
     *            ・priceInfoList<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01440<BR>
     * <BR>
     * 　@2-2) Loop the process for as many times as <BR>
     *         data elements(=WEB3AdminPMProductCondConfigUnit)<BR>
     *         if the data is not null about the class variables mentioned above.<BR>
     * 　@　@　@2-2-1) Call WEB3AdminPMProductCondConfigUnit.validate()<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4181B2980190
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intFive = 5;
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(productCode);
        int l_intTradingRegulationListlength = 0;
        int l_intBasicInfoListlength = 0;
        int l_intStockMarginInfoListlength = 0;
        int l_intDepositRateListlength = 0;
        int l_intSubstituteSecurityInfoListlength = 0;
        int l_intPriceInfoListlength = 0;

        WEB3AdminPMProductCondConfigUnit l_productCondConfigUnit =
            new WEB3AdminPMProductCondConfigUnit();

        // 1-1 if productCode is null, throw Exception
        if (productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 1-2 if productCode is Not Numeric & length not equal to 5 digits, throw Exception
            if ((!WEB3StringTypeUtility.isNumber(productCode))
                || (l_productCodeNumLength != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if folowing instances are null then throw Exception.
        if ((this.tradingRegulationList == null)
            && (this.basicInfoList == null)
            && (this.stockMarginInfoList == null)
            && (this.depositRateList == null)
            && (this.substituteSecurityInfoList == null)
            && (this.priceInfoList == null))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01440,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2  Loop the process for as many times as data elements
            if (this.tradingRegulationList != null)
            {
                l_intTradingRegulationListlength = this.tradingRegulationList.length;
                for (int i = 0; i < l_intTradingRegulationListlength; i++)
                {
                    l_productCondConfigUnit = tradingRegulationList[i];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.basicInfoList != null)
            {
                l_intBasicInfoListlength = this.basicInfoList.length;
                for (int j = 0; j < l_intBasicInfoListlength; j++)
                {
                    l_productCondConfigUnit = basicInfoList[j];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.stockMarginInfoList != null)
            {
                l_intStockMarginInfoListlength = this.stockMarginInfoList.length;
                for (int k = 0; k < l_intStockMarginInfoListlength; k++)
                {
                    l_productCondConfigUnit = stockMarginInfoList[k];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.depositRateList != null)
            {
                l_intDepositRateListlength = this.depositRateList.length;
                for (int l = 0; l < l_intDepositRateListlength; l++)
                {
                    l_productCondConfigUnit = depositRateList[l];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.substituteSecurityInfoList != null)
            {
                l_intSubstituteSecurityInfoListlength = this.substituteSecurityInfoList.length;
                for (int m = 0; m < l_intSubstituteSecurityInfoListlength; m++)
                {
                    l_productCondConfigUnit = substituteSecurityInfoList[m];
                    l_productCondConfigUnit.validate();
                }
            }

            if (this.priceInfoList != null)
            {
                l_intPriceInfoListlength = this.priceInfoList.length;
                for (int n = 0; n < l_intPriceInfoListlength; n++)
                {
                    l_productCondConfigUnit = priceInfoList[n];
                    l_productCondConfigUnit.validate();
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
        return new WEB3AdminPMProductCondConfCompleteResponse(this);
    }
}
@
