head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・顧客銘柄別取引停止一覧リクエスト (WEB3AdminPMAccProductTradeStopListRequest.java)
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
 * （管理者・顧客銘柄別取引停止一覧リクエスト）<BR>
 * <BR>
 * 管理者・顧客銘柄別取引停止一覧リクエストクラス<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopListRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
	public final static String PTYPE = "admin_p_m_acc_product_trade_stop_list";
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopListRequest.class);

    /**
     * （部店コード一覧）<BR>
     * <BR>
     * 部店コード一覧<BR>
     * <BR>
     * 管理者の部店コード一覧<BR>
     * <BR>
     * ※部店権限のチェックに使用。<BR>
     * <BR>
     * ------<English>----------<BR>
     * <BR>
     * branchCodeList<BR>
     * <BR>
     * branchCode list<BR>
     * <BR>
     * ※it is used for validateBranchPermission<BR>
     * <BR>
     */
    public String[] branchCodeList;

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
     * 銘柄コード<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * （要求ページ番号）<BR>
     * <BR>
     * 要求ページ番号<BR>
     * <BR>
     * pageIndex<BR>
     * <BR>
     */
    public String pageIndex;

    /**
     * （ページ内表示行数）<BR>
     * <BR>
     * ページ内表示行数<BR>
     * <BR>
     * pageSize<BR>
     * <BR>
     */
    public String pageSize;

    /**
     * （ソートキー）<BR>
     * <BR>
     * ソートキー<BR>
     * <BR>
     * sortKeys<BR>
     * <BR>
     */
    public WEB3AdminPMAccTradeStopSortKey[] sortKeys;

    /**
     * intMinValue int
     */
    private int intMinValue = 0;

    /**
     * @@roseuid 41FD92FB029F
     */
    public WEB3AdminPMAccProductTradeStopListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コード一覧チェック<BR>
     * 　@１－１）this.部店コード一覧 == nullの場合<BR>、
     * 　@　@　@　@　@「部店コード一覧がnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01429<BR>
     * <BR>
     * ２）部店コードチェック<BR>
     * 　@this.部店コード != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.部店コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.部店コード != 数値<BR>
     * 　@　@　@　@　@　@・this.部店コード.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ３）顧客コードチェック<BR>
     * 　@this.顧客コード != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.顧客コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「顧客コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.顧客コード != 数値<BR>
     * 　@　@　@　@　@　@・this.顧客コード.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * ４）銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合、以下のチェックを行う。<BR>
     * 　@４－１）this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@　@・this.銘柄コード != 数値<BR>
     * 　@　@　@　@　@　@・this.銘柄コード.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ５）ソートキーチェック<BR>
     * 　@５－１）this.ソートキー == nullであった場合 <BR>
     * 　@　@　@　@「ソートキーがnull」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@５－２）this.ソートキー.要素数 == 0だった場合 <BR>
     * 　@　@　@　@「ソートキー.要素数が0」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@５－３）this.ソートキーの全要素に対して <BR>
     * 　@　@　@　@下記のチェックを行う。 <BR>
     * 　@　@５－３－１）ソートキー.validate()をコールする。<BR>
     * <BR>
     * ６）要求ページ番号チェック <BR>
     * 　@６－１）this.要求ページ番号 == nullであった場合、 <BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@６－２）this.要求ページ番号が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@６－３）this.要求ページ番号 <= 0であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * ７）ページ内表示行数チェック <BR>
     * 　@７－１）this.ページ内表示行数 == nullであった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@７－２）this.ページ内表示行数が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@７－３）this.ページ内表示行数 <= 0であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCodeList check<BR>
     * 　@1-1) If this.branchCodeList == null<BR>
     * 　@　@　@　@　@Throw the exception "branchCodeList is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01429<BR>
     * <BR>
     * 2)branchCode check<BR>
     * 　@Check the followings if this.branchCode != null<BR>
     * 　@2-1) If this.branchCode meets with the following conditions,<BR>
     * 　@　@　@　@　@Throw the exception "branchCode error"<BR>
     * 　@　@　@　@　@　@・this.branchCode != numerical value<BR>
     * 　@　@　@　@　@　@・this.branchCode.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 3)accountCode check<BR>
     * 　@Check the followings if this.accountCode != null<BR>
     * 　@3-1) If this.accountCode meets with the following conditions,<BR>
     * 　@　@　@　@　@Throw the exception "accountCode error"<BR>
     * 　@　@　@　@　@　@・this.accountCode != numerical value<BR>
     * 　@　@　@　@　@　@・this.accountCode.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * 4)productCode check<BR>
     * 　@Check the followings if this.productCode != null<BR>
     * 　@4-1) If this.productCode meets with the following conditions,<BR>
     * 　@　@　@　@　@Throw the exception "productCode error"<BR>
     * 　@　@　@　@　@　@・this.productCode != numerical value<BR>
     * 　@　@　@　@　@　@・this.productCode.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 5)sortKeys check<BR>
     * 　@5-1) If this.sortKeys == null <BR>
     * 　@　@　@　@Throw the exception "sortKeys is null" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@5-2) If this.sortKeys.element number == 0 <BR>
     * 　@　@　@　@Throw the exception "sortKeys.element number is 0" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@5-3)Check the following on all elements of this.sortKeys<BR>
     * 　@　@5-3-1) Call sortKeys.validate() <BR>
     * <BR>
     * 6)pageIndex check<BR>
     * 　@6-1) If this.pageIndex == null <BR>
     * 　@　@　@　@Throw the exception "pageIndex is null" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@6-2) If this.pageIndex is not a numerical value, <BR>
     * 　@　@　@　@Throw the exception "pageIndex is not a numerical value" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@6-3)If this.pageIndex <= 0 <BR>
     * 　@　@　@　@Throw the exception "pageIndex is less than 0" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00616<BR>
     * <BR>
     * 7)pageSize check<BR>
     * 　@7-1) If this.pageSize == null <BR>
     * 　@　@　@　@Throw the exception "pageSize is null" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@7-2) If this.pageSize is not a numerical value <BR>
     * 　@　@　@　@Throw the exception "pageSize is not a numerical value" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@7-3) If this.pageSize <= 0 <BR>
     * 　@　@　@　@Throw the exception "pageSize is less than 0" <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4185F0B60169
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        int l_accountCodeNumLength = WEB3StringTypeUtility.getByteLength(this.accountCode);
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        final int l_intThree = 3;
        final int l_intSix = 6;
        final int l_intFive = 5;

        // 1-1 if branchCodeList is null throw Exception.
        if (branchCodeList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 if branchCode is not Numeric & its length Not equal to 3, throw Exception.
		for (int i = 0; i < branchCodeList.length; i++)
		{
			if (branchCodeList[i] != null)
			{
				if ((!WEB3StringTypeUtility.isNumber(branchCodeList[i]))
					|| (WEB3StringTypeUtility.getByteLength(branchCodeList[i]) != l_intThree))
				{
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00779,
						this.getClass().getName() + "." + STR_METHOD_NAME);
				}
			}
		}

        // 3-1 if accountCode is not Numeric & its length Not equal to 6, throw Exception.
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

        // 4-1 if productCode is not Numeric & its length Not equal to 5, throw Exception.
        if (productCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(productCode))
                || (l_productCodeNumLength != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 if sortKeys is null throw Exception.
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 5-2 if sortKeys length is 0, throw Exception.
            if (sortKeys.length == intMinValue)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //  5-3-1 Call sortKeys.validate()
        for (int i = intMinValue; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
        }

        // 6-1 if pageIndex is null throw Exception.
        if (pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
            // 6-2 if pageIndex is Not Numeic, throw Exception.
            if (!WEB3StringTypeUtility.isNumber(pageIndex))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
                // 6-3 if pageIndex is <= 0, throw Exception.
                if (Integer.parseInt(pageIndex) <= intMinValue)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }

        // 7-1 if pageSize is null, throw Exception.
        if (pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 7-2 if pageSize is Not Number, throw Exception
            if (!WEB3StringTypeUtility.isNumber(pageSize))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 7-3 if pageSize is < = 0, throw Exception.
        if (Integer.parseInt(pageSize) <= intMinValue)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMAccProductTradeStopListResponse(this);
    }
}
@
