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
filename	WEB3AdminOffFloorChangeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄更新共通リクエスト (WEB3AdminOffFloorChangeCommonRequest.java)
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
 * (管理者立会外分売銘柄更新共通リクエスト)<BR>
 * <BR>
 * 管理者立会外分売銘柄更新サービス（確認／完了）リクエストデータのスーパークラス。
 * <BR>
 * <BR>
 * ------<English>---------------<BR>
 * <BR>
 * WEB3AdminOffFloorChangeCommonRequest<BR>
 * <BR>
 * super class of WEB3AdminOffFloorChangeService(validate/submit) request data<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public abstract class WEB3AdminOffFloorChangeCommonRequest extends WEB3GenRequest
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorChangeCommonRequest.class);

    /**
     * (立会外分売銘柄キー)<BR>
     * <BR>
     * 立会外分売銘柄キー。<BR>
     * <BR>
     * productKey<BR>
     * <BR>
     */
    public WEB3AdminOffFloorProductKey productKey;

    /**
     * (分売価格)<BR>
     * <BR>
     * 分売価格。<BR>
     * <BR>
     * offFloorOrderPrice<BR>
     * <BR>
     */
    public String offFloorOrderPrice;

    /**
     * (申込株数上限)<BR>
     * <BR>
     * 申込株数上限。<BR>
     * （一人あたりの注文可能株数の上限値）<BR>
     * <BR>
     * ----<English>---------<BR>
     * <BR>
     * maxApplyQuantity<BR>
     * （maximum value of applyQuantity per person）<BR>
     * <BR>
     */
    public String maxApplyQuantity;

    /**
     * @@roseuid 421AE35D015A
     */
    public WEB3AdminOffFloorChangeCommonRequest()
    {

    }

    /**
     * 当クラスのプロパティの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@立会外分売銘柄キーチェック <BR>
     * <BR>
     * 　@１－１）　@立会外分売銘柄キー.validate( )をコールする。<BR>
     * <BR>
     * ２）　@分売価格チェック<BR>
     * <BR>
     * 　@２－１）　@this.分売価格≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。 <BR>
     * 　@　@　@・this.分売価格≠数字 <BR>
     * 　@　@　@　@　@　@「分売価格が数字以外」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01453<BR>
     * <BR>
     * 　@　@　@・this.分売価格≦0 <BR>
     * 　@　@　@　@　@　@「分売価格が0以下」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01454<BR>
     * <BR>
     * 　@　@　@・this.分売価格＝9桁を超える数字 <BR>
     * 　@　@　@　@　@　@「分売価格の桁数が9桁を超過」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01455<BR>
     * <BR>
     * ３）　@申込株数上限チェック<BR>
     * <BR>
     * 　@３－１）　@this.申込株数上限≠null、かつ
     * 以下のいずれかに該当する場合は、以下の例外をthrowする。 <BR>
     * 　@　@　@・this.申込株数上限≠数字 <BR>
     * 　@　@　@　@　@　@「申込株数上限が数字以外」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01456<BR>
     * <BR>
     * 　@　@　@・this.申込株数上限≦0 <BR>
     * 　@　@　@　@　@　@「申込株数上限が0以下」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01457<BR>
     * <BR>
     * 　@　@　@・this.申込株数上限＝8桁を超える数字 <BR>
     * 　@　@　@　@　@　@「申込株数上限の桁数が8桁を超過」の例外をthrow。 <BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01458<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence of the properties in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)off floor productKey check<BR>
     * <BR>
     * 　@1-1)Call off floor productKey.validate( )<BR>
     * <BR>
     * 2)offFloorOrderPrice check<BR>
     * <BR>
     * 　@2-1)If this.offFloorOrderPrice≠null and it meets either of the following
     * cases, throw the following exception<BR>
     * 　@　@　@・this.offFloorOrderPrice≠numerical value<BR>
     * 　@　@　@　@　@　@Throw the exception, "offFloorOrderPrice is not a numerical
     * value"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01453<BR>
     * <BR>
     * 　@　@　@・this.offFloorOrderPrice≦0 <BR>
     * 　@　@　@　@　@　@Throw the exception, "offFloorOrderPrice is less than 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01454<BR>
     * <BR>
     * 　@　@　@・this.offFloorOrderPrice＝a numerical value with more than 9 digits<BR>
     * 　@　@　@　@　@　@Throw the exception, "offFloorOrderPrice has more than 9 digits"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01455<BR>
     * <BR>
     * 3)maxApplyQuantity check<BR>
     * <BR>
     * 　@3-1)If this.maxApplyQuantity≠null and it meets either of the following cases,
     * throw the following exception<BR>
     * 　@　@　@・this.maxApplyQuantity≠numerical value<BR>
     * 　@　@　@　@　@　@Throw the exception, "maxApplyQuantity is not a numerical value"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01456<BR>
     * <BR>
     * 　@　@　@・this.maxApplyQuantity≦0 <BR>
     * 　@　@　@　@　@　@Throw the exception, "maxApplyQuantity is less than 0"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01457<BR>
     * <BR>
     * 　@　@　@・this.maxApplyQuantity＝a numerical value with more than 8 digits<BR>
     * 　@　@　@　@　@　@Throw the exception, "maxApplyQuantity has more than 8 digits"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_01458<BR>
     * <BR>
     * @@roseuid 41B7D3A40221
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        final int l_intNine = 9;
        final int l_intMin = 0;
        final int l_intEight = 8;
        int l_intoffFloorOrderPriceNumLength =
            WEB3StringTypeUtility.getByteLength(this.offFloorOrderPrice);
        int l_intMaxApplyQuantityNumLength =
            WEB3StringTypeUtility.getByteLength(this.maxApplyQuantity);

        // 1-1 Call off floor productKey.validate( )
        this.productKey.validate();

        // 2-1 if offFloorOrderPrice is Not Numeric, throw Exception
        if (this.offFloorOrderPrice != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.offFloorOrderPrice)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01453,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // if offFloorOrderPrice is less than 0, throw Exception.
                if (Integer.parseInt(this.offFloorOrderPrice) <= l_intMin)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01454,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

            // if lenght of offFloorOrderPrice is greater than 9, throw Exception.
            if (l_intoffFloorOrderPriceNumLength > l_intNine)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01455,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 maxApplyQuantity is Not a numeric, throw Exception.
        if (this.maxApplyQuantity != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.maxApplyQuantity)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01456,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // if maxApplyQuantity is less than 0, throw Exception
                if (Integer.parseInt(this.maxApplyQuantity) <= l_intMin)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01457,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }

            // if length of maxApplyQuantity is greater than 8, throw Exception.
            if (l_intMaxApplyQuantityNumLength > l_intEight)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01458,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (非 Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public abstract WEB3GenResponse createResponse();
}
@
