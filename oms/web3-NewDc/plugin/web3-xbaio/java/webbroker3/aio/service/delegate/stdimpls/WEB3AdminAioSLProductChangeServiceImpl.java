head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄変更サービスImpl(WEB3AdminAioSLProductChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 トウ鋒鋼(中訊) 新規作成 仕様変更モデルNo.760 No.769
Revision History : 2007/10/26 トウ鋒鋼(中訊) 仕様変更モデルNo.816 ＤＢ更新仕様158
Revision History : 2007/10/26 トウ鋒鋼(中訊) 仕様変更モデルNo.814
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminAioSLProductRegistControlService;
import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.message.WEB3SLProductSearchConditions;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductChangeService;
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
 * (担保銘柄変更サービスImpl)<BR>
 * 担保銘柄変更サービス実装クラス<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3AdminAioSLProductChangeServiceImpl implements WEB3AdminAioSLProductChangeService
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductChangeServiceImpl.class);

    /**
     * @@roseuid 46E8EE2A0308
     */
    public WEB3AdminAioSLProductChangeServiceImpl()
    {

    }

    /**
     * 担保銘柄変更処理を実施する。<BR>
     * <BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * １－１） 引数のリクエストデータが、担保銘柄変更入力リクエストの場合 <BR>
     * 　@－get担保銘柄変更入力画面()をコールする。 <BR>
     * <BR>
     * １－２） 引数のリクエストデータが、担保銘柄変更確認リクエストの場合 <BR>
     * 　@－validate担保銘柄変更()をコールする。 <BR>
     * <BR>
     * １－３） 引数のリクエストデータが、担保銘柄変更完了リクエストの場合 <BR>
     * 　@－submit担保銘柄変更()をコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBC11001F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        //１－１） 引数のリクエストデータが、担保銘柄変更入力リクエストの場合
        //　@－get担保銘柄変更入力画面()をコールする。
        if (l_request instanceof WEB3AdminSLProductChangeInputRequest)
        {
            l_response = this.getSLProductChangeInputScreen(
                (WEB3AdminSLProductChangeInputRequest)l_request);
        }
        //１－２） 引数のリクエストデータが、担保銘柄変更確認リクエストの場合
        //　@－validate担保銘柄変更()をコールする。
        else if (l_request instanceof WEB3AdminSLProductChangeConfirmRequest)
        {
            l_response = validateSLProductChange(
                (WEB3AdminSLProductChangeConfirmRequest)l_request);
        }
        //１－３） 引数のリクエストデータが、担保銘柄変更完了リクエストの場合
        //　@－submit担保銘柄変更()をコールする。
        else if (l_request instanceof WEB3AdminSLProductChangeCompleteRequest)
        {
            l_response = submitSLProductChange(
                (WEB3AdminSLProductChangeCompleteRequest)l_request);
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
     * (get担保銘柄変更入力画面)<BR>
     * 担保銘柄変更入力画面表示処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「get担保銘柄変更入力画面」参照。 <BR>
     * ========================================================<BR>
     * シーケンス図 (「証券担保ローンサービスモデル(管理者)」)<BR>
     * 　@　@具体位置：レコードが取得できない場合、<BR>
     * 　@　@　@　@　@　@　@対象レコード存在なしエラーをthrowする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_02837<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLProductChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBDDE008D
     */
    protected WEB3AdminSLProductChangeInputResponse getSLProductChangeInputScreen(
        WEB3AdminSLProductChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSLProductChangeInputScreen(WEB3AdminSLProductChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： ”B0602”
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //get担保銘柄行(long, Date)
        long l_lngProductId = l_request.searchConditions.productId;

        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //担保銘柄変更サービスを取得し
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        SecurityProductRow l_securityProductRow = null;

        try
        {
            //担保銘柄行を取得する
            l_securityProductRow = l_service.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //レコードが取得できない場合、対象レコード存在なしエラーthrowする
            log.error("レコードが存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //getProduct(arg0 : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        ProductManager l_productManager = l_tradingModule.getProductManager();

        Product l_product = null;
        try
        {
            //銘柄オブジェクトを取得する
            l_product = l_productManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("銘柄オブジェクトを取得する: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //createResponse( )
        WEB3AdminSLProductChangeInputResponse l_response =
            (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();

        //メッセージ プロパティセット
        l_response.stockLoanProductInfo = new WEB3SLProductInfoUnit();
        //レスポンス.銘柄登録情報.銘柄ID = 担保銘柄Row.銘柄ID
        l_response.stockLoanProductInfo.productId = l_securityProductRow.getProductId();
        //レスポンス.銘柄登録情報.銘柄コード = 担保銘柄Row.銘柄コード
        l_response.stockLoanProductInfo.productCode = l_securityProductRow.getProductCode();
        //レスポンス.銘柄登録情報.銘柄タイプ = 担保銘柄Row.銘柄タイプ
        l_response.stockLoanProductInfo.productType = l_securityProductRow.getProductType().intValue() + "";
        //レスポンス.銘柄登録情報.銘柄名 = getProduct().銘柄名
        l_response.stockLoanProductInfo.productName = l_product.getStandardName();
        //レスポンス.銘柄登録情報.適格区分 = 担保銘柄Row.適格区分
        l_response.stockLoanProductInfo.qualifiedDiv = l_securityProductRow.getFitFlg();
        //レスポンス.銘柄登録情報.掛目 = 担保銘柄Row.掛目の小数点以下をカットしString型に変換した値
        l_response.stockLoanProductInfo.weight = (long)l_securityProductRow.getEstimationRatio() + "";
        //レスポンス.銘柄登録情報.適用期間from = 担保銘柄Row.適用期間from
        l_response.stockLoanProductInfo.targetPeriodFrom = l_securityProductRow.getApplyTermFrom();
        //レスポンス.銘柄登録情報.適用期間to = 担保銘柄Row.適用期間to
        l_response.stockLoanProductInfo.targetPeriodTo = l_securityProductRow.getApplyTermTo();
        //レスポンス.銘柄登録情報.理由 = 担保銘柄Row.理由
        l_response.stockLoanProductInfo.reason = l_securityProductRow.getReason();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate担保銘柄変更)<BR>
     * 担保銘柄変更確認処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「validate担保銘柄変更」参照。 <BR>
     * ========================================================<BR>
     * シーケンス図 (「証券担保ローンサービスモデル(管理者)」)<BR>
     * 　@　@具体位置：レコードが取得できない場合、<BR>
     * 　@　@　@　@　@　@　@対象レコード存在なしエラーをthrowする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_02837<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLProductChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBEF602CF
     */
    protected WEB3AdminSLProductChangeConfirmResponse validateSLProductChange(
        WEB3AdminSLProductChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSLProductChange(WEB3AdminSLProductChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： ”B0602”
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //get担保銘柄行(long, Date)
        long l_lngProductId = l_request.searchConditions.productId;

        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //担保銘柄変更サービスを取得し
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        SecurityProductRow l_securityProductRow = null;

        try
        {
            //担保銘柄行を取得する
            l_securityProductRow = l_service.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //レコードが取得できない場合、対象レコード存在なしエラーthrowする
            log.error("レコードが存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate変更項目(担保銘柄Row, 担保銘柄登録情報)
        //[引数]
        //変更前担保銘柄情報： get担保銘柄行（）の戻り値
        //変更後担保銘柄情報： リクエスト.変更後銘柄登録情報
        validateChangeItem(l_securityProductRow, l_request.changedStockLoanProductInfo);

        //createResponse( )
        WEB3AdminSLProductChangeConfirmResponse l_response =
            (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit担保銘柄変更)<BR>
     * 担保銘柄変更完了処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「submit担保銘柄変更」参照。 <BR>
     * ========================================================<BR>
     * シーケンス図 (「証券担保ローンサービスモデル(管理者)」)<BR>
     * 　@　@具体位置：レコードが取得できない場合、<BR>
     * 　@　@　@　@　@　@　@対象レコード存在なしエラーをthrowする。<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_02837<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLProductChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DCBFCE039A
     */
    protected WEB3AdminSLProductChangeCompleteResponse submitSLProductChange(
        WEB3AdminSLProductChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSLProductChange(WEB3AdminSLProductChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[引数]
        //機@能カテゴリコード： ”B0602”
        //is更新： true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE, true);

        //validate取引パスワード(パスワード : String)
        l_administrator.validateTradingPassword(l_request.password);

        //get担保銘柄行(long, Date)
        long l_lngProductId = l_request.searchConditions.productId;

        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //担保銘柄変更サービスを取得し
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        SecurityProductRow l_securityProductRow = null;

        try
        {
            //担保銘柄行を取得する
            l_securityProductRow = l_service.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //レコードが取得できない場合、対象レコード存在なしエラーthrowする
            log.error("レコードが存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //validate変更項目(担保銘柄Row, 担保銘柄登録情報)
        //変更前担保銘柄情報： get担保銘柄行（）の戻り値
        //変更後担保銘柄情報： リクエスト.変更後銘柄登録情報

        WEB3SLProductInfoUnit l_changedStockLoanProductInfo = l_request.changedStockLoanProductInfo;
        validateChangeItem(l_securityProductRow, l_changedStockLoanProductInfo);

        //get管理者コード( )
        String l_strAdminCode = l_administrator.getAdministratorCode();

        //更新日付：TradingSystem.getSystemTimestamp()の戻り値
        Date l_datTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //create担保銘柄更新情報(担保銘柄登録情報, String, Date, 担保銘柄Row)
        SecurityProductRow l_changedSecurityProductRow = createSLProductChangeUpdateInfo(
            l_changedStockLoanProductInfo, l_strAdminCode, l_datTimestamp, l_securityProductRow);

        //担保銘柄検索情報
        WEB3SLProductSearchConditions l_searchKeyConditions = l_request.searchConditions;

        //update担保銘柄情報(担保銘柄検索情報, 担保銘柄Row)
        l_service.updateSecurityProductInfo(l_searchKeyConditions, l_changedSecurityProductRow);

        //createResponse( )
        WEB3AdminSLProductChangeCompleteResponse l_response =
            (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create担保銘柄更新情報)<BR>
     * 担保銘柄テーブルに更新する情報を生成し、返却する。<BR>
     * <BR> 
     * １）　@担保銘柄Rowの各項目を更新する。 <BR>
     * <BR>
     * ・引数.担保銘柄Row.評価掛目 = 引数.変更後銘柄情報.評価掛目 <BR>
     * 　@* 値がnullの場合、0 <BR>
     * ・引数.担保銘柄Row.適格区分 = 引数.変更後銘柄情報.適格区分 <BR>
     * ・引数.担保銘柄Row.適用期間from = 引数.変更後銘柄情報.適用期間from <BR>
     * ・引数.担保銘柄Row.適用期間to = 引数.変更後銘柄情報.適用期間to <BR>
     * 　@* 値がnullの場合、9999/12/31 <BR>
     * ・引数.担保銘柄Row.理由 = 引数.変更後銘柄情報.理由 <BR>
     * ・引数.担保銘柄Row.更新者コード = 引数:管理者コード <BR>
     * ・引数.担保銘柄Row.更新日付 = 引数更新日付 <BR>
     * <BR>
     * ２）　@担保銘柄Rowを返却する。<BR>
     * <BR>
     * @@param l_changedProductInfo - (変更後銘柄情報)<BR>
     * 変更後銘柄情報<BR>
     * @@param l_strAdminCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * @@param l_datUpdateDate - (更新日付)<BR>
     * 更新日付<BR>
     * @@param l_securityProductRow - (担保銘柄Row)<BR>
     * 担保銘柄Row<BR>
     * @@return 担保銘柄Row
     * @@throws WEB3BaseException
     * @@roseuid 46DD47F00331
     */
    private SecurityProductRow createSLProductChangeUpdateInfo(
        WEB3SLProductInfoUnit l_changedProductInfo,
        String l_strAdminCode,
        Date l_datUpdateDate,
        SecurityProductRow l_securityProductRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSLProductChangeUpdateInfo(" +
            "WEB3SLProductInfoUnit, String, Date, SecurityProductRow)";

        log.entering(STR_METHOD_NAME);

        if (l_securityProductRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        SecurityProductParams l_securityProductParams = new SecurityProductParams(l_securityProductRow);

        //引数.変更後銘柄情報.評価掛目値がnullの場合
        if (l_changedProductInfo.weight == null)
        {
            //引数.担保銘柄Row.評価掛目 = 0
            l_securityProductParams.setEstimationRatio(0);
        }
        else
        {
            //引数.担保銘柄Row.評価掛目 = 引数.変更後銘柄情報.評価掛目
            l_securityProductParams.setEstimationRatio(Double.parseDouble(l_changedProductInfo.weight));

        }
        //引数.担保銘柄Row.適格区分 = 引数.変更後銘柄情報.適格区分
        l_securityProductParams.setFitFlg(l_changedProductInfo.qualifiedDiv);
        //引数.担保銘柄Row.適用期間from = 引数.変更後銘柄情報.適用期間from
        l_securityProductParams.setApplyTermFrom(l_changedProductInfo.targetPeriodFrom);

        //引数.変更後銘柄情報.適用期間to値がnullの場合
        if (l_changedProductInfo.targetPeriodTo == null)
        {
            //引数.担保銘柄Row.適用期間to = 9999/12/31
            l_securityProductParams.setApplyTermTo(
                WEB3DateUtility.getDate(WEB3GentradeTimeDef.MAX_YMD, WEB3GentradeTimeDef.DATE_SPLIT_YMD));
        }
        else
        {
            //引数.担保銘柄Row.適用期間to = 引数.変更後銘柄情報.適用期間to
            l_securityProductParams.setApplyTermTo(l_changedProductInfo.targetPeriodTo);
        }

        //引数.担保銘柄Row.理由 = 引数.変更後銘柄情報.理由
        l_securityProductParams.setReason(l_changedProductInfo.reason);
        //引数.担保銘柄Row.更新者コード = 引数:管理者コード
        l_securityProductParams.setLastUpdater(l_strAdminCode);
        //引数.担保銘柄Row.更新日付 = 引数更新日付
        l_securityProductParams.setLastUpdatedTimestamp(l_datUpdateDate);

        log.exiting(STR_METHOD_NAME);
        return l_securityProductParams;
    }

    /**
     * (edit担保銘柄情報)<BR>
     * 指定された引数のレコードが存在した場合、行をremoveして<BR>
     * Listを返却する。<BR>
     * <BR>
     * １） 引数:担保銘柄情報の要素分、Loop処理を行う。<BR>
     * <BR>
     * 　@１－１）　@担保銘柄行の取得<BR>
     * <BR>
     * 　@１－２）　@担保銘柄行.適用期間from == 引数:適用期間fromの場合、<BR>
     * 　@　@　@　@　@　@要素をremove()する。<BR>
     * <BR>
     * ２）　@担保銘柄情報を返却する。<BR>
     * @@param l_datTargetPeriodFrom - (適用期間from)<BR>
     * 適用期間from<BR>
     * @@param l_lisSecurityProductInfos - (担保銘柄情報)<BR>
     * 担保銘柄情報<BR>
     * @@return List
     * @@roseuid 46DE6DA301B6
     */
    private List editSLProductInfo(Date l_datTargetPeriodFrom, List l_lisSecurityProductInfos)
    {
        final String STR_METHOD_NAME = "editSLProductInfo(Date, List)";
        log.entering(STR_METHOD_NAME);

        List l_lisCopies = new ArrayList();

        int l_intLength = l_lisSecurityProductInfos.size();
        for (int i = 0; i < l_intLength; i++)
        {
            l_lisCopies.add(l_lisSecurityProductInfos.get(i));
        }

        Iterator l_itRecords = l_lisCopies.iterator();
        //１） 引数:担保銘柄情報の要素分、Loop処理を行う
        while (l_itRecords.hasNext())
        {
            //１－１）　@担保銘柄行の取得
            SecurityProductRow l_securityProductRow =
                (SecurityProductRow)l_itRecords.next();

            //１－２）　@担保銘柄行.適用期間from == 引数:適用期間fromの場合、要素をremove()する
            if (WEB3DateUtility.compareToDay(
                l_securityProductRow.getApplyTermFrom(), l_datTargetPeriodFrom) == 0)
            {
                l_itRecords.remove();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisCopies;
    }

    /**
     * (validate変更項目)<BR>
     * 変更項目についてのチェックを行う。<BR>
     * <BR>
     * シーケンス図「validate変更項目」参照<BR>
     * ========================================================<BR>
     * シーケンス図 (「証券担保ローンサービスモデル(管理者)」)<BR>
     * 　@　@具体位置：以下の処理の戻り値が 1 の場合、例外をスロー<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_02688<BR>
     * ========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図 (「証券担保ローンサービスモデル(管理者)」)<BR>
     * 　@　@具体位置：引数:変更後担保情報銘柄.適用期間from < roll()<BR>
     *      　@　@　@　@で取得した値 の場合、例外をスロー<BR>
     * 　@　@class 　@: WEB3BusinessLayerException<BR>
     * 　@　@tag  　@ : BUSINESS_ERROR_02929<BR>
     * ========================================================<BR>
     * @@param l_preSecurityProductInfo - (変更前担保銘柄情報)<BR>
     * 変更前担保銘柄情報<BR>
     * @@param l_changedSecurityProductInfo - (変更後担保銘柄情報)<BR>
     * 変更後担保銘柄情報<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DF4B960201
     */
    private void validateChangeItem(SecurityProductRow l_preSecurityProductInfo,
        WEB3SLProductInfoUnit l_changedSecurityProductInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeItem(SecurityProductRow, WEB3SLProductInfoUnit)";
        log.entering(STR_METHOD_NAME);

        //以下の処理の戻り値が 1 の場合、例外をスロー
        //compare変更情報(担保銘柄Row, 担保銘柄登録情報)
        WEB3AdminAioSLProductRegistControlService l_service =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        int l_intCompareChangeInfo = l_service.compareChangeInfo(
            l_preSecurityProductInfo, l_changedSecurityProductInfo);

        if (l_intCompareChangeInfo == 1)
        {
            log.debug("変更項目無しエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02688,
                this.getClass().getName() + "."  + STR_METHOD_NAME,
                "変更項目無しエラー");
        }

        //is項目変更(String, String)
        Date l_datPreFrom = l_preSecurityProductInfo.getApplyTermFrom();
        Date l_datChangedFrom = l_changedSecurityProductInfo.targetPeriodFrom;

        boolean l_blnIsItemChangeFrom = l_service.isItemChange(
            WEB3DateUtility.formatDate(l_datPreFrom, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_datChangedFrom, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //is項目変更(String, String)
        Date l_datPreTo = l_preSecurityProductInfo.getApplyTermTo();
        Date l_datChangedTo = l_changedSecurityProductInfo.targetPeriodTo;

        boolean l_blnIsItemChangeTo = l_service.isItemChange(
            WEB3DateUtility.formatDate(l_datPreTo, WEB3GentradeTimeDef.DATE_FORMAT_YMD),
            WEB3DateUtility.formatDate(l_datChangedTo, WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //適用期間from or 適用期間to が変更されている場合、以下の処理を実行
        if (l_blnIsItemChangeFrom || l_blnIsItemChangeTo)
        {
            //get担保銘柄情報(long)
            List l_lisSecurityProductInfos = l_service.getSecurityProductInfo(
                l_preSecurityProductInfo.getProductId());

            //edit担保銘柄情報(Date, List)
            List l_editedSLProductInfos = editSLProductInfo(
                l_preSecurityProductInfo.getApplyTermFrom(), l_lisSecurityProductInfos);

            //適用期間fromが変更されている場合
            if (l_blnIsItemChangeFrom)
            {
                //営業日計算(基準日 : Timestamp)
                Timestamp l_tsStandardDate = GtlUtils.getTradingSystem().getSystemTimestamp();
                WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tsStandardDate);

                //roll(加算／減算日数 : int)
                Date l_datBizDate = l_bizDate.roll(1);

                //引数:変更後担保情報銘柄.適用期間from < roll()で取得した値 の場合、例外をスロー
                if (WEB3DateUtility.compareToDay(l_datChangedFrom, l_datBizDate) < 0)
                {
                    log.debug("適用期間fromは翌営業日より小さいです。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02929,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "適用期間fromは翌営業日より小さいです。");
                }
            }

            //edit担保銘柄情報（）の戻り値要素 != 0 の場合、以下の処理を実行
            if (l_editedSLProductInfos.size() != 0)
            {
                //validate担保銘柄同一期間(List, Date, Date)
                //[引数]
                // 担保銘柄情報： edit担保銘柄情報（）の戻り値
                // 適用期間from： 引数.変更後担保銘柄情報.適用期間from
                // 適用期間to： 引数.変更後担保銘柄情報.適用期間to
                l_service.validateSecurityProductSameTerm(
                    l_editedSLProductInfos, l_datChangedFrom, l_datChangedTo);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
