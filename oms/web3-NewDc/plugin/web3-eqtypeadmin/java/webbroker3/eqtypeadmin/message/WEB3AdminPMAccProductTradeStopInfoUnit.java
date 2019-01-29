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
filename	WEB3AdminPMAccProductTradeStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客銘柄別取引停止情報クラス (WEB3AdminPMAccProductTradeStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （顧客銘柄別取引停止情報クラス）<BR>
 * <BR>
 * 顧客銘柄別取引停止情報クラス<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopInfoUnit extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopInfoUnit.class);

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
     * （顧客名）<BR>
     * <BR>
     * 顧客名<BR>
     * <BR>
     * ※一覧機@能にて使用。<BR>
     * <BR>
     * accountName<BR>
     * <BR>
     * ※It is used for list functions<BR>
     * <BR>
     */
    public String accountName = null;

    /**
     * （銘柄コード）<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     * ※指定なしの場合はnullをセット。<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     * ※Set null if no specification<BR>
     * <BR>
     */
    public String productCode;

    /**
     * （銘柄名）<BR>
     * <BR>
     * 銘柄名<BR>
     * <BR>
     * ※一覧機@能にて使用。
     * <BR>
     * productName<BR>
     * <BR>
     * It is used for list functions<BR>
     * <BR>
     */
    public String productName = null;

    /**
     * （有効期限From）<BR>
     * <BR>
     * 有効期限From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * expirationStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String expirationStartDate = null;

    /**
     * （有効期限To）<BR>
     * <BR>
     * 有効期限To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * expirationEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String expirationEndDate = null;

    /**
     * （変更後の有効期限To）<BR>
     * <BR>
     * 変更後の有効期限To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※変更機@能にて使用。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftChangeExpirationEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ※It is used for change functions<BR>
     * <BR>
     */
    public String aftChangeExpirationEndDate = null;

    /**
     * （理由）<BR>
     * <BR>
     * 理由<BR>
     * <BR>
     * reason<BR>
     * <BR>
     */
    public String reason = null;

    /**
     * （変更後理由）<BR>
     * <BR>
     * 変更後の理由<BR>
     * <BR>
     * ※変更機@能にて使用。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftChangeReason<BR>
     * <BR>
     * ※It is used for change functions<BR>
     * <BR>
     */
    public String aftChangeReason = null;

    /**
     * （顧客取引停止情報一覧）
     */
    public WEB3AdminPMAccTradeStopInfoUnit[] accTradeStopInfoList;

    /**
     * @@roseuid 41FD931001D4
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）部店コードチェック<BR>
     * 　@１－１）this.部店コード == nullの場合、<BR>
     * 　@　@　@　@　@「部店コードがnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * 　@１－２）ths.部店コードが以下のいづれかに該当する場合、<BR>
     * 　@　@　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.部店コード != 数値<BR>
     * 　@　@　@　@　@・this.部店コード.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）顧客コードチェック<BR>
     * 　@２－１）this.顧客コード == nullの場合、<BR>
     * 　@　@　@　@　@「顧客コードがnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * 　@２－２）ths.顧客コードが以下のいづれかに該当する場合、<BR>
     * 　@　@　@　@　@「顧客コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.顧客コード != 数値<BR>
     * 　@　@　@　@　@・this.顧客コード.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * ３）銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合、以下チェックを行う。<BR>
     * 　@３－１）this.銘柄コードが以下のいづれかに該当する場合、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.銘柄コード != 数値<BR>
     * 　@　@　@　@　@・this.銘柄コード.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ４）有効期限Fromチェック<BR>
     * 　@this.有効期限From != nullの場合、以下のチェックを行う。<BR>
     * 　@４－１）this.有効期限Fromが日付型に変換できない場合は、<BR>
     * 　@　@　@　@「有効期限Fromエラー(存在しない日付)」の例外を<BR>
     * 　@　@　@　@スローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * ５）有効期限Toチェック <BR>
     * 　@this.有効期限To != nullの場合、以下のチェックを行う。<BR>
     * 　@５－１）this.有効期限Toが日付型に変換できない場合は、<BR>
     * 　@　@　@　@「有効期限Toエラー(存在しない日付)」の例外を<BR>
     * 　@　@　@　@スローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * ６）変更後有効期限Toチェック <BR>
     * 　@this.変更後有効期限To != nullの場合、以下のチェックを行う。<BR>
     * 　@６－１）this.有効期限Toが日付型に変換できない場合は、<BR>
     * 　@　@　@　@「有効期限Toエラー(存在しない日付)」の例外を<BR>
     * 　@　@　@　@スローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * ７）有効期限From/To整合性チェック<BR>
     * 　@this.有効期限From != null　@かつ　@this.有効期限To != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@７－１）this.有効期限From > this.有効期限Toの場合、<BR>
     * 　@　@　@　@　@「有効期限整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@※Date型に変換して比較すること。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * ８）有効期限From/変更後有効期限To整合性チェック<BR>
     * 　@this.有効期限From != null　@かつ　@this.変更後有効期限To != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@８－１）this.有効期限From > this.変更後有効期限Toの場合、<BR>
     * 　@　@　@　@　@「有効期限整合性エラー」の例外をスローする。<BR>
     * 　@　@　@　@　@※Date型に変換して比較すること。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * ９）理由チェック<BR>
     * 　@this.理由 != nullの場合、以下のチェックを行う。<BR>
     * 　@９－１）this.理由.length > 50である場合、<BR>
     * 　@　@　@　@　@「入力理由エラー(桁数超過)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * １０）変更後理由チェック<BR>
     * 　@this.変更後理由 != nullの場合、以下のチェックを行う。<BR>
     * 　@１０－１）this.変更後理由.length > 50である場合、<BR>
     * 　@　@　@　@　@「入力理由エラー(桁数超過)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>      
     * １１）顧客取引停止情報一覧チェック<BR>
     * 　@１１－１）this.顧客取引停止情報一覧 == nullの場合、<BR>
     * 　@　@　@　@　@「顧客取引停止情報一覧がnull」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01436<BR>
     * <BR>
     * 　@１１－２）this.顧客取引停止情報一覧.length == 0の場合、<BR>
     * 　@　@　@　@　@「顧客取引停止情報一覧の要素数が0」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01437<BR>
     * <BR>
     * 　@１１－３）this.顧客取引停止情報一覧.length分以下の処理を繰り返す。<BR>
     * 　@　@　@　@１１－３－１）顧客取引停止情報.validate()をコールする。<BR>
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
     * 　@2-1) If this.accountCode == null<BR>
     * 　@　@　@　@　@Throw the exception "accountCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * 　@2-2)If this.accountCode meets with one of the followings<BR>
     * 　@　@　@　@　@Throw the exception "accountCode error"<BR>
     * 　@　@　@　@　@・this.accountCode != numerical value<BR>
     * 　@　@　@　@　@・this.accountCode.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
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
     * 　@Check the followings if this.expirationStartDate != null<BR>
     *  4-1) If it is unable to change this.expirationStartDate to Date type,<BR>
     *         Throw the exception "expirationStartDate error (date doesn't exist)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * 5)expirationEndDate <BR>
     * 　@Check the followings if this.expirationEndDate != null<BR>
     * 　@5-1)If it is unable to change this.expirationEndDate to Date type,<BR>
     * 　@　@　@　@Throw the exception "expirationEndDate error (date doesn't exist)<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * 6)aftChangeExpirationEndDate <BR>
     * 　@Check the followings if this.aftChangeExpirationEndDate != null<BR>
     * 　@6-1)If it is unable to change this.aftChangeExpirationEndDate to Date type,<BR>
     * 　@　@　@　@Throw the exception "expirationEndDate error (date doesn't exist)<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * 7)expirationStartDate/expirationEndDate<BR>
     * 　@If this.expirationStartDate != null and this.expirationEndDate != null,<BR>
     * 　@Check the followings<BR>
     * 　@7-1) If this.expirationStartDate > this.expirationEndDate<BR>
     * 　@　@　@　@　@Throw the exception "expiration correspondence error"<BR>
     * 　@　@　@　@　@※Compare them after changing them to Date type<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * 8)expirationStartDate/aftChangeExpirationEndDate<BR>
     * 　@If this.expirationStartDate != null and this.aftChangeExpirationEndDate != null,<BR>
     * 　@Check the followings<BR>
     * 　@8-1) If this.expirationStartDate > this.aftChangeExpirationEndDate<BR>
     * 　@　@　@　@　@Throw the exception "expiration correspondence error"<BR>
     * 　@　@　@　@　@※Compare them after changing them to Date type<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * 9)reason check<BR>
     * 　@Check the followings if this.reason != null<BR>
     * 　@9-1) If this.reason.length > 50,<BR>
     * 　@　@　@　@　@Throw the exception "input reason error(digits excess)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * 10)aftChangeReason check<BR>
     * 　@Check the followings if this.aftChangeReason != null<BR>
     * 　@10-1) If this.aftChangeReason.length > 50,<BR>
     * 　@　@　@　@　@Throw the exception "input aftChangeReason error(digits excess)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * 11)accTradeStopInfoList check<BR>
     * 　@11-1) If this.accTradeStopInfoList == null<BR>
     * 　@　@　@　@　@Throw the exception "accTradeStopInfoList is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01436<BR>
     * <BR>
     * 　@11-2) If this.accTradeStopInfoList.length == 0,<BR>
     * 　@　@　@　@　@Throw the excetion "Elements of accTradeStopInfoList is 0"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01437<BR>
     * <BR>
     * 　@11-3)Loop the process for as many times as this.accTradeStopInfoList.length<BR>
     * 　@　@　@　@11-3-1)Call WEB3AdminPMAccTradeStopInfoUnit.validate()<BR>
     * <BR>
     * @@roseuid 4185F33E00BE
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        final String DATE_FORMAT = "yyyyMMdd";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMAccTradeStopInfoUnit l_pMAccTradeStopInfoUnit =
            new WEB3AdminPMAccTradeStopInfoUnit();
        int l_branchCodeNumLength = WEB3StringTypeUtility.getByteLength(this.branchCode);
        int l_accountCodeNumLength = WEB3StringTypeUtility.getByteLength(this.accountCode);
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        int l_reasonNumLength = WEB3StringTypeUtility.getByteLength(this.reason);
        int l_aftChangeReasonNumLength = WEB3StringTypeUtility.getByteLength(this.aftChangeReason);
        int l_intaccTradeStopInfoListlength = 0;
        final int l_intThree = 3;
        final int l_intSix = 6;
        final int l_intFive = 5;
        final int l_intFifty = 50;

        // 1-1 branchCode = null, throw Exception.
        if (branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 1-2 if branchCode is not numeric OR its length not equal to 3,
            // throw exception
            if ((!WEB3StringTypeUtility.isNumber(branchCode))
                || (l_branchCodeNumLength != l_intThree))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if accountCode is null throw Exception
        if (accountCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 if accountCode is not numeric OR its length not equal to 6, throw exception
            if ((!WEB3StringTypeUtility.isNumber(accountCode))
                || (l_accountCodeNumLength != l_intSix))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if productCode is not numeric OR its length not equal to 5,
        // throw exception
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

        // 4-1 if expirationStartDate can' be formated to Date Type throw Exception.
        if (expirationStartDate != null
            && !WEB3StringTypeUtility.isDateStr(expirationStartDate, DATE_FORMAT))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01431,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 5-1 if expirationEndDate cant be formated to date type then throw Exception
        if (expirationEndDate != null
            && !WEB3StringTypeUtility.isDateStr(expirationEndDate, DATE_FORMAT))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 6-1 if aftChangeExpirationEndDate cant be formated to date type then throw Exception
        if (aftChangeExpirationEndDate != null
            && !WEB3StringTypeUtility.isDateStr(aftChangeExpirationEndDate, DATE_FORMAT))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 7-1 if expirationStartDate & expirationEndDate are not null
        if ((expirationStartDate != null) && (expirationEndDate != null))
        {
            Date l_expirationStartDate = WEB3DateUtility.getDate(expirationStartDate, DATE_FORMAT);
            Date l_expirationEndDate = WEB3DateUtility.getDate(expirationEndDate, DATE_FORMAT);

            int l_resultcompare =
                (WEB3DateUtility.compare(l_expirationStartDate, l_expirationEndDate));

            // if l_resultcompare > 0 1 throw Exception
            if (l_resultcompare > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // 8-1 if expirationStartDate & aftChangeExpirationEndDate are not null
        if ((expirationStartDate != null) && (aftChangeExpirationEndDate != null))
        {
            Date l_expirationStartDate = WEB3DateUtility.getDate(expirationStartDate, DATE_FORMAT);
            Date l_aftChgexpirationEndDate = WEB3DateUtility.getDate(aftChangeExpirationEndDate, DATE_FORMAT);

            int l_resultcompare =
                (WEB3DateUtility.compare(l_expirationStartDate, l_aftChgexpirationEndDate));

            // if l_resultcompare > 0 throw Exception
            if (l_resultcompare > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 9-1 if length of reason not equal to 50 , throw Exception.
        if (reason != null)
        {
            if (l_reasonNumLength > l_intFifty)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // 10-1 if length of aftChangeReason not equal to 50 , throw Exception.
        if (aftChangeReason != null)
        {
            if (l_aftChangeReasonNumLength > l_intFifty)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 11-1 if accTradeStopInfoList is null throw Exception
        if (accTradeStopInfoList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01436,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_intaccTradeStopInfoListlength = accTradeStopInfoList.length;
            // 11-2 if accTradeStopInfoList.length = 0 then throw Exception.
            if (l_intaccTradeStopInfoListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01437,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 11-3 Loop the process for as many times as this.accTradeStopInfoList.length<BR>
                for (int i = 0; i < l_intaccTradeStopInfoListlength; i++)
                {
                    l_pMAccTradeStopInfoUnit = this.accTradeStopInfoList[i];
                    // 11-3-1 Call branchTradingStatusList.validate()
                    l_pMAccTradeStopInfoUnit.validate();
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
