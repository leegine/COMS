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
filename	WEB3AdminPMProductCondRefReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件照会リクエスト(WEB3AdminPMProductCondRefReferenceRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・株式銘柄条件照会リクエスト)<BR>
 * <BR>
 * 管理者・株式銘柄条件照会リクエストクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondRefReferenceRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondRefReferenceRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_pm_product_cond_ref_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

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
     * （営業日）<BR>
     * <BR>
     * 営業日<BR>
     * <BR>
     * bizDate<BR>
     * <BR>
     */
    public Date bizDate;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondRefReferenceRequest.class);

    /**
     * @@roseuid 41FD8E7E003E
     */

    public WEB3AdminPMProductCondRefReferenceRequest()
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
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)productCode check
     * 　@1-1)If this.productCode == null<BR>
     * 　@　@　@　@　@Throw the exception "this.productCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00079<BR>
     * <BR>
     * 　@1-2) If this.productCode meets with the following conditions,<BR>
     * 　@　@　@　@　@Throw the exception "input productCode error"<BR>
     * 　@　@　@　@・this.productCode.length != 5<BR>
     * 　@　@　@　@　@・this.productCode != numrical value<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4181A67D033E
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final int l_intFive = 5;
        int l_productCodeProductByteLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //1-1 if productCode=null throw exception
        if (this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + STR_METHOD_NAME);

            //1-2 if productCode length<5 and productCode is not numeric throw exception
        } else if (
            (l_productCodeProductByteLength != l_intFive)
                || (!WEB3StringTypeUtility.isNumber(this.productCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMProductCondRefReferenceResponse(this);
    }
}
@
