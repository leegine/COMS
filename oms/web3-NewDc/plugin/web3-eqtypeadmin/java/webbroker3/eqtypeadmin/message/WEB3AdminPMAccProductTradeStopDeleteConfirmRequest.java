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
filename	WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・顧客銘柄別取引停止削除確認リクエスト (WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （管理者・顧客銘柄別取引停止削除確認リクエスト）<BR>
 * <BR>
 * 管理者・顧客銘柄別取引停止削除確認リクエストクラス<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopDeleteConfirmRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopDeleteConfirmRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_p_m_acc_product_trade_stop_delete_confirm";

    /**
     * serialVersionUID<BR>
     */
    public final static  long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.class);

    /**
     * （部店コード）<BR>
     * <BR>
     * 部店コード<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     */
    public String branchCode;

    /**
     * （顧客コード）<BR>
     * <BR>
     * 顧客コード<BR>
     * <BR>
     * accountCode<BR>
     * <BR>
     */
    public String accountCode;

    /**
     * （銘柄コード）<BR>
     * <BR>
     * 銘柄コード
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * （有効期限From）<BR>
     * 有効期限From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * expirationStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String expirationStartDate;

    /**
     * @@roseuid 41FD9324007D
     */
    public WEB3AdminPMAccProductTradeStopDeleteConfirmRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * 　@１－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     *<BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.部店コード != 数値<BR>
     * 　@　@　@　@　@　@・this.部店コード.length != 3<BR>
     *<BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）顧客コードチェック<BR>
     * 　@this.顧客コード != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.顧客コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「顧客コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.顧客コード != 数値<BR>
     * 　@　@　@　@　@　@・this.顧客コード.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * ３）銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.銘柄コード != 数値<BR>
     * 　@　@　@　@　@　@・this.銘柄コード.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ４）有効期限Fromチェック <BR>
     * 　@４－１）this.有効期限From == nullであった場合、 <BR>
     * 　@　@　@　@「有効期限Fromがnull」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01430<BR>
     * <BR>
     * 　@４－２）this.有効期限Fromが日付型に変換できない場合は、<BR>
     * 　@　@　@　@「有効期限Fromエラー(存在しない日付)」の例外を<BR>
     * 　@　@　@　@スローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     *  1-1) If this.branchCode == null.<BR>
     *         Throw the exception of "branchCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     *  1-2) If this.branchCode meets with the following conditions,<BR>
     *         Throw the exception "branchCode error"<BR>
     *         ・this.branchCode  != numerical value<BR>
     *         ・this.branchCode.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 2)accountCode check<BR>
     * Check the followings if this.accountCode  != null<BR>
     *  2-1)  If this.accountCode meets with the following conditions,<BR>
     *         Throw the exception "accountCode error"<BR>
     *         ・this.accountCode  != numerical value<BR>
     *         ・this.accountCode.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * 3)productCode check<BR>
     * Check the followings if this.productCode  != null<BR>
     *  3-1)  If this.productCode meets with the following conditions,<BR>
     *         Throw the exception "productCode error"<BR>
     *         ・this.productCode != numerical value<BR>
     *         ・this.productCode.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 4)expirationStartDate check<BR>
     * 4-1) If this.expirationStartDateFrom == null,<BR>
     * 　@　@　@　@Throw the exception "expirationStartDate is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01430<BR>
     * <BR>
     *  4-2) If it is unable to change this.expirationStartDate to Date type,<BR>
     *         Throw the exception "expirationStartDate error (date doesn't exist)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * @@roseuid 4185F22C0169
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        final int l_intThree = 3;
        final int l_intSix = 6;
        final int l_intFive = 5;
        int l_branchCodeNumLength = WEB3StringTypeUtility.getByteLength(branchCode);
        int l_accountCodeNumLength = WEB3StringTypeUtility.getByteLength(accountCode);
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(productCode);

        // 1-1
        if (branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 1-2 if branch Code Not a Numeric & length Not equal to 3 throw Exception
            if ((!WEB3StringTypeUtility.isNumber(branchCode))
                || (l_branchCodeNumLength != l_intThree))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if accountCode Not a Numeric & length Not equal to 6 throw Exception
        if (accountCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(accountCode))
                || (l_accountCodeNumLength != l_intSix))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if accountCode Not a Numeric & length Not equal to 6 throw Exception
        if (this.productCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(this.productCode))
                || (l_productCodeNumLength != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if expirationStartDate is null throw Exception.
        if (expirationStartDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01430,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 4-2 if expirationStartDate cant be formated to Datetype, throw Exception
            if (!WEB3StringTypeUtility.isDateStr(expirationStartDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01431,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopDeleteConfirmResponse(this);
    }

}
@
