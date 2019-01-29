head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄登録サービスImpl(WEB3AdminBondDomesticProductRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 何文敏(中訊) 新規作成 仕様変更・モデルNo.193
Revision History : 2007/07/11 柴双紅(中訊) 仕様変更・モデルNo.201、モデルNo.212
Revision History : 2007/10/08 武波 (中訊) 仕様変更・モデル259
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputResponse;
import webbroker3.bd.message.WEB3BondDomesticProductBasicInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者国内債券銘柄登録サービスImpl)<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistServiceImpl implements WEB3AdminBondDomesticProductRegistService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistServiceImpl.class);

    /**
     * @@roseuid 4691D3AE021B
     */
    public WEB3AdminBondDomesticProductRegistServiceImpl()
    {

    }

    /**
     * 管理者国内債券銘柄登録サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * input銘柄登録()、validate銘柄登録()、submit銘柄登録()<BR>
     * のいずれかのメソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 466505DD0109
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminBondDomesticProductRegistInputRequest)
        {
            l_response =
                this.inputProductRegist(
                    (WEB3AdminBondDomesticProductRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticProductRegistConfirmRequest)
        {
            l_response =
                this.validateProductRegist(
                    (WEB3AdminBondDomesticProductRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminBondDomesticProductRegistCompleteRequest)
        {
            l_response =
                this.submitProductRegist(
                    (WEB3AdminBondDomesticProductRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (input銘柄登録)<BR>
     * 管理者国内債券銘柄登録入力処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券銘柄登録）input銘柄登録」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticProductRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4664F51F0128
     */
    protected WEB3AdminBondDomesticProductRegistInputResponse inputProductRegist(
        WEB3AdminBondDomesticProductRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " inputProductRegist(WEB3AdminBondDomesticProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //  機@能カテゴリコード　@：機@能カテゴリコード.債券銘柄管理
        //　@is更新　@：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            false);

        //get債券銘柄(long)
        //[引数]
        //　@　@銘柄ID　@：リクエストデータ.銘柄ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_productManager.getBondProduct(Long.parseLong(l_request.productId));

        //get発行券種(String)
        //[引数]
        //　@　@銘柄ID　@：リクエストデータ.銘柄ID
        String[] l_strIssueCouponTypes =
            l_productManager.getIssueCouponType(l_request.productId);

        //国内債券銘柄基本情報( )
        WEB3BondDomesticProductBasicInfo l_productBasicInfo =
            new WEB3BondDomesticProductBasicInfo();

        //プロパティ・セット
        //銘柄コード           ：債券銘柄.銘柄コード（SONAR)
        l_productBasicInfo.productCode = l_bondProduct.getHostProductCode();

        //回号コード           ：債券銘柄.回号コード（SONAR）
        l_productBasicInfo.productIssueCode = l_bondProduct.getHostProductIssueCode();

        //銘柄名（HOST)       ：債券銘柄.HOST銘柄名１
        l_productBasicInfo.productNameHost = l_bondProduct.getHostProductName1();

        //種別コード           ：債券銘柄.種別コード
        l_productBasicInfo.bondCategCode = l_bondProduct.getBondCategCode();

        //発行券種            ：get発行券種()の戻り値
        l_productBasicInfo.issueCouponType = l_strIssueCouponTypes;

        //発行日         ：債券銘柄.発行日
        l_productBasicInfo.issueDate = l_bondProduct.getIssueDate();

        //応募単価            ：債券銘柄.買付単価
        BondProductRow l_bondProductRow =  (BondProductRow)l_bondProduct.getDataSourceObject();
        if (!l_bondProductRow.getBuyPriceIsNull())
        {
            l_productBasicInfo.applyPrice =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        }

        //利率          ：債券銘柄.利率
        l_productBasicInfo.coupon =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());

        //年間利払回数      ：債券銘柄.年間利払回数
        l_productBasicInfo.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";

        //利払日１            ：債券銘柄.利払日１
        l_productBasicInfo.couponPaymentDate1 = l_bondProduct.getInterestPaymentDay1();

        //利払日２            ：債券銘柄.利払日２
        l_productBasicInfo.couponPaymentDate2 = l_bondProduct.getInterestPaymentDay2();

        //償還日         ：債券銘柄.償還日
        l_productBasicInfo.maturityDate = l_bondProduct.getMaturityDate();

        //応募開始日（SONAR）    ：債券銘柄.応募開始日（SONAR）
        l_productBasicInfo.recruitStartDateSONAR = l_bondProduct.getHostRecruitStartDate();

        //応募終了日（SONAR）    ：債券銘柄.応募終了日（SONAR）
        l_productBasicInfo.recruitEndDateSONAR = l_bondProduct.getHostRecruitEndDate();

        // 国内債券銘柄更新情報( )
        WEB3BondDomesticProductUpdateInfo l_productUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();

        //プロパティ・セット
        //取扱区分            ：債券銘柄.取扱区分
        l_productUpdateInfo.tradeHandleDiv = l_bondProduct.getTradeHandleDiv();

        //売買区分            ：債券銘柄.売買区分
        l_productUpdateInfo.dealingType = l_bondProduct.getTradeType();

        //応募開始日（WEB3）     ：債券銘柄.取扱開始日時
        l_productUpdateInfo.recruitStartDateWEB3 = l_bondProduct.getTradeStartDate();

        //応募終了日（WEB3)     ：債券銘柄.取扱終了日時
        l_productUpdateInfo.recruitEndDateWEB3 = l_bondProduct.getTradeEndDate();

        //応募開始日（インターネット）  ：債券銘柄.応募開始日
        l_productUpdateInfo.recruitStartDateInterNet = l_bondProduct.getRecruitStartDate();

        //応募終了日（インターネット)  ：債券銘柄.応募終了日
        l_productUpdateInfo.recruitEndDateInterNet = l_bondProduct.getRecruitEndDate();

        //受渡日         ：債券銘柄.受渡日
        l_productUpdateInfo.deliveryDate = l_bondProduct.getDeliveryDate();

        //銘柄名（WEB3)       ：債券銘柄.銘柄名
        l_productUpdateInfo.productNameWEB3 = l_bondProduct.getProductName();

        //申込単位            ：債券銘柄.申込単位
        l_productUpdateInfo.applyUnit =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getTradeUnit());

        //最低額面            ：債券銘柄.最低額面
        l_productUpdateInfo.minFaceAmount =
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getMinFaceAmount());

        //最高額面            ：債券銘柄.最高額面
        if (!l_bondProductRow.getMaxFaceAmountIsNull())
        {
            l_productUpdateInfo.maxFaceAmount =
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getMaxFaceAmount());
        }

        //目論見書閲覧チェック区分    ：債券銘柄.目論見書閲覧チェック区分
        l_productUpdateInfo.prospectusCheckDiv = l_bondProduct.getProspectusCheckDiv();

        //createResponse( )
        WEB3AdminBondDomesticProductRegistInputResponse l_response =
            (WEB3AdminBondDomesticProductRegistInputResponse)l_request.createResponse();

        //銘柄基本情報        ：作成した国内債券銘柄基本情報
        l_response.productBasicInfo = l_productBasicInfo;

        //銘柄更新情報        ：作成した国内債券銘柄更新情報
        l_response.productUpdateInfo = l_productUpdateInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate銘柄登録)<BR>
     * 管理者国内債券銘柄登録確認処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券銘柄登録）validate銘柄登録」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticProductRegistConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4664F53500EA
     */
    protected WEB3AdminBondDomesticProductRegistConfirmResponse validateProductRegist(
        WEB3AdminBondDomesticProductRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateProductRegist(WEB3AdminBondDomesticProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //  機@能カテゴリコード　@：機@能カテゴリコード.債券銘柄管理
        //　@is更新　@：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //応募終了日（WEB3)と応募終了日（インターネット）に時刻を設定する。
        //[引数]
        //国内債券銘柄更新情報：　@リクエストデータ.銘柄更新情報
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            this.setRecruitEndDate(l_request.bondDomesticProductUpdateInfo);

        //validate銘柄内容(String, 国内債券銘柄更新情報)
        //[引数]
        //　@　@銘柄ID　@：リクエストデータ.銘柄ID
        //　@　@国内債券銘柄更新情報　@：国内債券銘柄更新情報
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        l_productManager.validateProductSpec(
            l_request.productId,
            l_bondDomesticProductUpdateInfo);

        //createResponse( )
        WEB3AdminBondDomesticProductRegistConfirmResponse l_response =
            (WEB3AdminBondDomesticProductRegistConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit銘柄登録)<BR>
     * 管理者国内債券銘柄登録完了処理を行なう。<BR>
     * <BR>
     * シーケンス図：「（管理者国内債券銘柄登録）submit銘柄登録」参照。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticProductRegistCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4664F57601F3
     */
    protected WEB3AdminBondDomesticProductRegistCompleteResponse submitProductRegist(
        WEB3AdminBondDomesticProductRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitProductRegist(WEB3AdminBondDomesticProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        // 機@能カテゴリコード　@：機@能カテゴリコード.債券銘柄管理
        //　@is更新　@：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE,
            true);

        //validate取引パスワード(パスワード : String)
        //[引数]
        //　@　@パスワード　@：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //応募終了日（WEB3)と応募終了日（インターネット）に時刻を設定する。
        //[引数]
        //国内債券銘柄更新情報：　@リクエストデータ.銘柄更新情報
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            this.setRecruitEndDate(l_request.bondDomesticProductUpdateInfo);

        //validate銘柄内容(String, 国内債券銘柄更新情報)
        //[引数]
        //　@　@銘柄ID　@：リクエストデータ.銘柄ID
        //　@　@国内債券銘柄更新情報　@：国内債券銘柄更新情報
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        l_productManager.validateProductSpec(
            l_request.productId,
            l_bondDomesticProductUpdateInfo);

        //get管理者コード( )
        String l_strAdministratorCode = l_administrator.getAdministratorCode();

        //update債券銘柄内容(String, 国内債券銘柄更新情報, String)
        //[引数]
        //　@　@銘柄ID　@：リクエストデータ.銘柄ID
        //　@　@国内債券銘柄更新情報　@：国内債券銘柄更新情報
        //　@　@管理者コード　@：get管理者コード()の戻り値
        l_productManager.updateBondProductContent(
            l_request.productId,
            l_bondDomesticProductUpdateInfo,
            l_strAdministratorCode);

        //createResponse( )
        WEB3AdminBondDomesticProductRegistCompleteResponse l_response =
            (WEB3AdminBondDomesticProductRegistCompleteResponse)l_request.createResponse();

        //更新者コード    ：get管理者コード()の戻り値
        l_response.updaterCode = l_strAdministratorCode;

        //更新日時        ：現在日時
        l_response.updateTimeStamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (set応募終了時刻)<BR>
     * 国内債券銘柄更新情報の応募終了日（WEB3)と<BR>
     * 　@　@　@応募終了日（インターネット）に時刻を設定する。<BR>
     * <BR>
     * １）応募終了日(インターネット）の時刻を設定する。<BR>
     * <BR>
     * 　@　@１－１）債券取引時間管理.get国内債券締切時刻()をコールする。<BR>
     * 　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@日付　@：　@国内債券銘柄更新情報.応募終了日(インターネット）<BR>
     * <BR>
     * 　@　@１－２）国内債券銘柄更新情報.応募終了日(インターネット）<BR>
     * 　@　@　@　@　@　@　@　@の時間部分に取得した締切時刻を設定する。<BR>
     * <BR>
     * ２）応募終了日(WEB3）の時刻を設定する。<BR>
     * <BR>
     * 　@　@２－１）債券取引時間管理.get国内債券締切時刻()をコールする。<BR>
     * 　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@日付　@：　@国内債券銘柄更新情報.応募終了日(WEB3）<BR>
     * <BR>
     * 　@　@２－２）国内債券銘柄更新情報.応募終了日(WEB3）<BR>
     * 　@　@　@　@　@　@　@　@の時間部分に取得した締切時刻を設定する。<BR>
     * <BR>
     * ３）国内債券銘柄更新情報を返却する。<BR>
     * @@param l_bondDomesticProductUpdateInfo - (国内債券銘柄更新情報)<BR>
     * 国内債券銘柄更新情報<BR>
     * @@return WEB3BondDomesticProductUpdateInfo
     * @@throws WEB3BaseException
     */
    protected WEB3BondDomesticProductUpdateInfo setRecruitEndDate(
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setRecruitEndDate(WEB3BondDomesticProductUpdateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_bondDomesticProductUpdateInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）応募終了日(インターネット）の時刻を設定する。
        //１－１）債券取引時間管理.get国内債券締切時刻()をコールする。
        //[引数]
        //日付　@：　@国内債券銘柄更新情報.応募終了日(インターネット）
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement =
            new WEB3BondTradingTimeManagement();
        String l_strRecruitEndDateInterNet =
            l_bondTradingTimeManagement.getBondDomesticLimitTime(
                l_bondDomesticProductUpdateInfo.recruitEndDateInterNet);

        // １－２）国内債券銘柄更新情報.応募終了日(インターネット）の時間部分に取得した締切時刻を設定する。
        String l_strFormatInterNetDate =
            WEB3DateUtility.formatDate(
                l_bondDomesticProductUpdateInfo.recruitEndDateInterNet,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strRecruitEndDateInterNet;

        // ２）応募終了日(WEB3）の時刻を設定する。
        // 　@　@２－１）債券取引時間管理.get国内債券締切時刻()をコールする。
        //   [引数]
        //   日付　@：　@国内債券銘柄更新情報.応募終了日(WEB3）
        String l_strRecruitEndDateWEB3 = l_bondTradingTimeManagement.getBondDomesticLimitTime(
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3);

        //    ２－２）国内債券銘柄更新情報.応募終了日(WEB3）の時間部分に取得した締切時刻を設定する。
        String l_strFormatWEB3Date =
            WEB3DateUtility.formatDate(
                l_bondDomesticProductUpdateInfo.recruitEndDateWEB3,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD) + l_strRecruitEndDateWEB3;

        //３）国内債券銘柄更新情報を返却する。
        String l_strDateFormat =
            WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS;
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate(l_strFormatWEB3Date, l_strDateFormat);
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate(l_strFormatInterNetDate, l_strDateFormat);

        log.exiting(STR_METHOD_NAME);
        return l_bondDomesticProductUpdateInfo;
    }
}
@
