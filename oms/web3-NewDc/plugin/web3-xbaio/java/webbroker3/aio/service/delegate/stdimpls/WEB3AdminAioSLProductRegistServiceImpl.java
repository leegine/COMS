head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録サービスImpl(WEB3AdminAioSLProductRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孟亞南 (中訊) 新規作成 モデルNo.760
Revision History : 2007/09/18 孟亞南 (中訊) モデルNo.769
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminAioSLProductRegistControlService;
import webbroker3.aio.define.WEB3AdminAioSLStockLoanWeightDef;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (担保銘柄登録サービスImpl)<BR>
 * 担保銘柄登録サービス実装クラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminAioSLProductRegistServiceImpl implements WEB3AdminAioSLProductRegistService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistServiceImpl.class);

    /**
     * 担保銘柄登録処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * １－１） 引数のリクエストデータが、担保銘柄登録入力リクエストの場合<BR>
     * 　@－get担保銘柄入力画面()をコールする。 <BR>
     * <BR>
     * １－２） 引数のリクエストデータが、担保銘柄登録確認リクエストの場合<BR>
     * 　@－validate担保銘柄登録()をコールする。<BR>
     * <BR>
     * １－３） 引数のリクエストデータが、担保銘柄登録完了リクエストの場合<BR>
     * 　@－submit担保銘柄登録()をコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //引数のリクエストデータが、担保銘柄登録入力リクエストの場合
        //－get担保銘柄入力画面()をコールする。
        if (l_request instanceof WEB3AdminSLProductRegistInputRequest)
        {
            l_response =
                this.getSLProductInputScreen((WEB3AdminSLProductRegistInputRequest)l_request);
        }
        //引数のリクエストデータが、担保銘柄登録確認リクエストの場合
        //－validate担保銘柄登録()をコールする
        else if (l_request instanceof WEB3AdminSLProductRegistConfirmRequest)
        {
            l_response =
                this.validateSLProductRegist((WEB3AdminSLProductRegistConfirmRequest)l_request);
        }
        //引数のリクエストデータが、担保銘柄登録完了リクエストの場合
        //－submit担保銘柄登録()をコールする。
        else if (l_request instanceof WEB3AdminSLProductRegistCompleteRequest)
        {
            l_response =
                this.submitSLProductRegist((WEB3AdminSLProductRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get担保銘柄入力画面)<BR>
     * 担保銘柄登録入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get担保銘柄入力画面」参照。<BR>
     * <BR>
     * @@param l_request - (担保銘柄登録入力リクエストオブジェクト)<BR>
     * 担保銘柄登録入力リクエストオブジェクト
     * @@return WEB3AdminSLProductRegistInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegistInputResponse getSLProductInputScreen(
        WEB3AdminSLProductRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLProductInputScreen(WEB3AdminSLProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //レスポンスデータを生成する
        WEB3AdminSLProductRegistInputResponse l_response =
            (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();

        //翌営業日日付
        l_response.nextBizDate = new WEB3GentradeBizDate(
            new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate担保銘柄登録)<BR>
     * <BR>
     * 担保銘柄登録確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate担保銘柄登録」参照。<BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(validate担保銘柄登録) <BR>
     * 具体位置：(リクエスト.担保銘柄登録情報.適用期間from < roll()で取得した値<BR>
     * 　@　@　@　@の場合、例外をスロー)<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02929 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(validate担保銘柄登録) <BR>
     * 具体位置：(株式銘柄オブジェクトを取得できない場合<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02928 <BR>
     * ========================================================== <BR>
     * @@param l_request - (担保銘柄登録確認リクエストオブジェクト)<BR>
     * 担保銘柄登録確認リクエストオブジェクト
     * @@return WEB3AdminSLProductRegistConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegistConfirmResponse validateSLProductRegist(
        WEB3AdminSLProductRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSLProductRegist(WEB3AdminSLProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //営業日計算
        //roll(加算／減算日数 : int)
        Date l_datBizDate = new WEB3GentradeBizDate(
            new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);

        //担保名銘柄登録情報オブジェクト.適用期間From < roll()
        //throwエラー
        if (WEB3DateUtility.compareToDay(
            l_request.stockLoanProductInfo.targetPeriodFrom, l_datBizDate) < 0)
        {
            log.debug("適用期間fromは翌営業日より小さいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02929,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用期間fromは翌営業日より小さいです。");
        }

        //get証券会社
        Institution l_institution = l_administrator.getInstitution();

        Product l_product = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeProductManager l_eqTypeProductManager =
            (EqTypeProductManager)l_tradingModule.getProductManager();

        try
        {
            l_product =
                l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_request.stockLoanProductInfo.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("株式銘柄オブジェクトが取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02928,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //担保銘柄登録制御サービスImpl
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
            WEB3AdminAioSLProductRegistControlService.class);

        //get担保銘柄情報(long)
        List l_lisProductInfos = l_service.getSecurityProductInfo(l_product.getProductId());

        if (l_lisProductInfos != null && l_lisProductInfos.size() != 0)
        {
            //validate担保銘柄同一期間
            l_service.validateSecurityProductSameTerm(
            	l_lisProductInfos,
                l_request.stockLoanProductInfo.targetPeriodFrom,
                l_request.stockLoanProductInfo.targetPeriodTo);
        }

        //レスポンスデータを生成する
        WEB3AdminSLProductRegistConfirmResponse l_response =
            (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();

        //レスポンス.銘柄登録情報
        l_response.stockLoanProductInfo = new WEB3SLProductInfoUnit();
        //レスポンス.銘柄登録情報.銘柄ID
        l_response.stockLoanProductInfo.productId = l_product.getProductId();

        //レスポンス.銘柄登録情報.銘柄コード
        l_response.stockLoanProductInfo.productCode = l_request.stockLoanProductInfo.productCode;

        //レスポンス.銘柄登録情報.銘柄タイプ
        l_response.stockLoanProductInfo.productType = l_request.stockLoanProductInfo.productType;

        //レスポンス.銘柄登録情報.銘柄名
        l_response.stockLoanProductInfo.productName = l_product.getStandardName();

        //レスポンス.銘柄登録情報.適格区分
        l_response.stockLoanProductInfo.qualifiedDiv = l_request.stockLoanProductInfo.qualifiedDiv;

        //レスポンス.銘柄登録情報.掛目
        if (l_request.stockLoanProductInfo.weight != null)
        {
            l_response.stockLoanProductInfo.weight = l_request.stockLoanProductInfo.weight;
        }
        else
        {
            l_response.stockLoanProductInfo.weight = WEB3AdminAioSLStockLoanWeightDef.WEIGHT;
        }

        //レスポンス.銘柄登録情報.適用期間From
        l_response.stockLoanProductInfo.targetPeriodFrom = l_request.stockLoanProductInfo.targetPeriodFrom;

        //レスポンス.銘柄登録情報.適用期間To
        if (l_request.stockLoanProductInfo.targetPeriodTo != null)
        {
            l_response.stockLoanProductInfo.targetPeriodTo = l_request.stockLoanProductInfo.targetPeriodTo;
        }
        else
        {
            l_response.stockLoanProductInfo.targetPeriodTo =
                WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD);
        }

        //レスポンス.銘柄登録情報.理由
        l_response.stockLoanProductInfo.reason = l_request.stockLoanProductInfo.reason;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit担保銘柄登録)<BR>
     * <BR>
     * 担保銘柄登録完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit担保銘柄登録」参照。<BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(submit担保銘柄登録) <BR>
     * 具体位置：(リクエスト.担保銘柄登録情報.適用期間from < roll()で取得した値<BR>
     * 　@　@　@　@の場合、例外をスロー)<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02929 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * シーケンス図 ：(submit担保銘柄登録) <BR>
     * 具体位置：(株式銘柄オブジェクトを取得できない場合<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02928 <BR>
     * ========================================================== <BR>
     * @@param l_request - (担保銘柄登録完了リクエストオブジェクト)<BR>
     * 担保銘柄登録完了リクエストオブジェクト
     * @@return WEB3AdminSLProductRegistCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminSLProductRegistCompleteResponse submitSLProductRegist(
        WEB3AdminSLProductRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSLProductRegist(WEB3AdminSLProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //validate取引パスワード
        l_administrator.validateTradingPassword(l_request.password);

        //営業日計算
        //roll(加算／減算日数 : int)
        Date l_datBizDate = new WEB3GentradeBizDate(
            new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);

        //担保名銘柄登録情報オブジェクト.適用期間From < roll()
        //throwエラー
        if (WEB3DateUtility.compareToDay(
            l_request.stockLoanProductInfo.targetPeriodFrom, l_datBizDate) < 0)
        {
            log.debug("適用期間fromは翌営業日より小さいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02929,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用期間fromは翌営業日より小さいです。");
        }

        //get証券会社
        Institution l_institution = l_administrator.getInstitution();

        Product l_product = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeProductManager l_eqTypeProductManager =
            (EqTypeProductManager)l_tradingModule.getProductManager();

        try
        {
            //getProduct
            l_product =
                l_eqTypeProductManager.getProduct(
                    l_institution,
                    l_request.stockLoanProductInfo.productCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("株式銘柄オブジェクトが取得できません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02928,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //担保銘柄登録制御サービスImpl
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
            WEB3AdminAioSLProductRegistControlService.class);

        //get担保銘柄情報(long)
        List l_lisProductInfos = l_service.getSecurityProductInfo(l_product.getProductId());

        if (l_lisProductInfos != null && l_lisProductInfos.size() != 0)
        {
            //validate担保銘柄同一期間
            l_service.validateSecurityProductSameTerm(
            	l_lisProductInfos,
                l_request.stockLoanProductInfo.targetPeriodFrom,
                l_request.stockLoanProductInfo.targetPeriodTo);
        }

        //get管理者コード
        String l_strAdminCode = l_administrator.getAdministratorCode();

        //insert担保銘柄情報(String, 担保銘柄登録情報, String)
        l_service.insertSecurityProductInfo(
            l_institution.getInstitutionCode(),
            l_request.stockLoanProductInfo,
            l_strAdminCode);

        //レスポンスデータを生成する
        WEB3AdminSLProductRegistCompleteResponse l_response =
            (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
