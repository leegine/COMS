head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductCancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄取消サービス実装クラス(WEB3AdminAioSLProductCancelServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孫洪江 (中訊) 新規作成 仕様変更モデル760 モデル766 モデル769 モデル771
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.WEB3AdminAioSLProductRegistControlService;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmResponse;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (担保銘柄取消サービスImpl) <BR>
 * 担保銘柄取消サービス実装クラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminAioSLProductCancelServiceImpl implements WEB3AdminAioSLProductCancelService
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductCancelServiceImpl.class);

    /**
     * 担保銘柄取消処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * １－１） 引数のリクエストデータが、担保銘柄登録取消確認リクエストの場合<BR>
     * 　@－validate担保銘柄取消()をコールする。<BR>
     * <BR>
     * １－２） 引数のリクエストデータが、担保銘柄登録取消完了リクエストの場合<BR>
     * 　@－submit担保銘柄取消()をコールする。<BR>
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

        if (l_request instanceof WEB3AdminSLProductCancelConfirmRequest)
        {
            //validate担保銘柄取消()をコールする
            l_response = validateSLProductCancel((WEB3AdminSLProductCancelConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminSLProductCancelCompleteRequest)
        {
            //submit担保銘柄取消()をコールする
            l_response = submitSLProductCancel((WEB3AdminSLProductCancelCompleteRequest)l_request);
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
     * （validate担保銘柄取消）<BR>
     * 担保銘柄の取消確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate担保銘柄取消」 参照<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置　@:　@レコードが取得できない場合、<BR>
     * 　@　@　@　@対象レコード存在なしエラーをthrowする。<BR>
     * 　@　@　@　@class　@:  WEB3BusinessLayerException  <BR>
     * 　@　@　@　@tag　@　@:　@BUSINESS_ERROR_02837<BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 担保銘柄登録取消確認リクエスト
     * @@return WEB3AdminSLProductCancelConfirmResponse
     * @@throws WEB3BaseException
     */
    public WEB3AdminSLProductCancelConfirmResponse validateSLProductCancel(
        WEB3AdminSLProductCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSLProductCancel(WEB3AdminSLProductCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )(担保銘柄登録取消確認リクエスト::validate)
        //リクエストデータの整合性チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報( )
        //ログイン情報より管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //該当の管理者がこの機@能が使えるか権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： ”B0602”
        //is更新： true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE,
            true);

        WEB3AdminAioSLProductRegistControlService l_aioSLProductRegistControlService =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        //リクエスト.担保銘柄検索キー.銘柄ID
        long l_lngProductId = l_request.searchConditions.productId;

        //リクエスト.担保銘柄検索キー.適用期間from
        Date l_datTargetPeriodFrom = l_request.searchConditions.targetPeriodFrom;

        //get担保銘柄行(long, Date)(担保銘柄登録制御サービスImpl::get担保銘柄行)
        //主キーから担保銘柄行を取得する。
        //[引数]
        //銘柄ID： リクエスト.担保銘柄検索キー.銘柄ID
        //適用期間from： リクエスト.担保銘柄検索キー.適用期間from
        SecurityProductRow l_securityProductRow = null;
        try
        {
            l_securityProductRow =
                l_aioSLProductRegistControlService.getSecurityProductRow(l_lngProductId, l_datTargetPeriodFrom);
        }
        catch (WEB3BaseException l_ex)
        {
            //レコードが取得できない場合、対象レコード存在なしエラーをthrowする。
            log.error("レコードが存在しません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "レコードが存在しません。");
        }

        //拡張プロダクトマネージャを取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        ProductManager l_equityProductManager = l_tradingModule.getProductManager();

        //getProduct(arg0 : long)
        //銘柄オブジェクトを取得する。
        //[引数]
        // arg0： リクエスト.担保銘柄検索キー.銘柄ID
        Product l_product = null;
        try
        {
            l_product = l_equityProductManager.getProduct(l_lngProductId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        //createResponse( )
        //レスポンスオブジェクトの生成
        WEB3AdminSLProductCancelConfirmResponse l_response =
            (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();

        // プロパティセット
        WEB3SLProductInfoUnit l_stockLoanProductInfo = new WEB3SLProductInfoUnit();

        //レスポンスに以下の内容をセットする。
        //レスポンス.銘柄登録情報.銘柄ID = 担保銘柄Row.銘柄ID
        l_stockLoanProductInfo.productId = l_securityProductRow.getProductId();

        //レスポンス.銘柄登録情報.銘柄コード = 担保銘柄Row.銘柄コード
        l_stockLoanProductInfo.productCode = l_securityProductRow.getProductCode();

        //レスポンス.銘柄登録情報.銘柄タイプ = 担保銘柄Row.銘柄タイプ
        l_stockLoanProductInfo.productType = l_securityProductRow.getProductType().intValue() + "";

        //レスポンス.銘柄登録情報.銘柄名 = getProduct().銘柄名
        l_stockLoanProductInfo.productName = l_product.getStandardName();

        //レスポンス.銘柄登録情報.適格区分 = 担保銘柄Row.適格区分
        l_stockLoanProductInfo.qualifiedDiv = l_securityProductRow.getFitFlg();

        //レスポンス.銘柄登録情報.掛目 = 担保銘柄Row.掛目の小数点以下をカットしString型に変換した値
        l_stockLoanProductInfo.weight = (long)l_securityProductRow.getEstimationRatio() + "";

        //レスポンス.銘柄登録情報.適用期間from = 担保銘柄Row.適用期間from
        l_stockLoanProductInfo.targetPeriodFrom = l_securityProductRow.getApplyTermFrom();

        //レスポンス.銘柄登録情報.適用期間to = 担保銘柄Row.適用期間to
        l_stockLoanProductInfo.targetPeriodTo = l_securityProductRow.getApplyTermTo();

        //レスポンス.銘柄登録情報.理由 = 担保銘柄Row.理由
        l_stockLoanProductInfo.reason = l_securityProductRow.getReason();

        l_response.stockLoanProductInfo = l_stockLoanProductInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （submit担保銘柄取消）<BR>
     * 担保銘柄の取消完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit担保銘柄取消」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 担保銘柄登録取消完了リクエスト
     * @@return WEB3AdminSLProductCancelCompleteResponse
     * @@throws WEB3BaseException
     */
    public WEB3AdminSLProductCancelCompleteResponse submitSLProductCancel(
        WEB3AdminSLProductCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSLProductCancel(WEB3AdminSLProductCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )(担保銘柄登録取消完了リクエスト::validate)
        //リクエストデータの整合性チェック
        l_request.validate();

        //getInstanceFromログイン情報( )
        //ログイン情報より管理者オブジェクトを取得する。
        WEB3Administrator l_web3Administrator =
            WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //該当の管理者がこの機@能が使えるか権限チェックを行う。
        //[引数]
        //機@能カテゴリコード： ”B0602”
        //is更新： true
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_PRODUCT_MANAGE,
            true);

        //validate取引パスワード(パスワード : String)
        //取引パスワードの認証を行う。
        //[validate取引パスワード()に指定する引数]
        //取引パスワード：　@リクエストデータ.暗証番号
        l_web3Administrator.validateTradingPassword(
            l_request.password);

        WEB3AdminAioSLProductRegistControlService l_aioSLProductRegistControlService =
            (WEB3AdminAioSLProductRegistControlService)Services.getService(
                WEB3AdminAioSLProductRegistControlService.class);

        //delete担保銘柄情報(担保銘柄検索情報)
        //主キーを対象に担保銘柄テーブルのレコードを削除する。
        //[引数]
        //削除対象キー： リクエスト.担保銘柄検索キー
        l_aioSLProductRegistControlService.deleteSecurityProductInfo(l_request.searchConditions);

        //createResponse( )
        //レスポンスオブジェクトの生成
        WEB3AdminSLProductCancelCompleteResponse l_response =
            (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
