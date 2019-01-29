head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付銘柄条件登録サービスImpl(WEB3MutualFixedBuyConditionServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/16 于瀟(中訊) 新規作成 モデルNo.608,615,616,617,618,619
Revision History : 2008/07/31 武波(中訊) 仕様変更 モデルNo.620,622,623,624
Revision History : 2008/08/05 武波(中訊) 仕様変更 モデルNo.625
Revision History : 2008/08/06 武波(中訊) 仕様変更 モデルNo.626
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeDivDef;
import webbroker3.common.define.WEB3FinInstitutionDivDef;
import webbroker3.common.define.WEB3MFStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.gentrade.message.WEB3GentradeMultiCheckResults;
import webbroker3.gentrade.message.WEB3GentradeMultiDocCheckResultUnit;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFixedBuyCloseDateDrawDateCalc;
import webbroker3.mf.WEB3MutualFixedBuyCommonService;
import webbroker3.mf.WEB3MutualFixedBuyDrawAccount;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualProductCategory;
import webbroker3.mf.data.MfFixedBuyingChangeHistParams;
import webbroker3.mf.data.MfFixedBuyingChangeHistRow;
import webbroker3.mf.data.MfFixedBuyingChangeParams;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3MFFirstDisplayDivDef;
import webbroker3.mf.define.WEB3MFSonarSendCheckDivDef;
import webbroker3.mf.message.WEB3MutualFixedBuyAccountInfo;
import webbroker3.mf.message.WEB3MutualFixedBuyCommonUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyTotalUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (投信定時定額買付銘柄条件登録サービスImpl)<BR>
 * 投信定時定額買付銘柄条件登録サービス実装クラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3MutualFixedBuyConditionServiceImpl extends WEB3MutualClientRequestService
    implements WEB3MutualFixedBuyConditionService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionServiceImpl.class);

    /**
     * @@roseuid 487597AA0167
     */
    public WEB3MutualFixedBuyConditionServiceImpl()
    {

    }

    /**
     * 投信定時定額買付銘柄条件登録サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * input定時定額買付銘柄条件登録()、<BR>
     * validate定時定額買付銘柄条件登録()、<BR>
     * submit定時定額買付銘柄条件登録()いずれかのメソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C24201FE
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
        else if (l_request instanceof WEB3MutualFixedBuyConditionInputRequest)
        {

            l_response = this.inputMutualFixedBuyCondition((WEB3MutualFixedBuyConditionInputRequest)l_request);
        }
        else if (l_request instanceof WEB3MutualFixedBuyConditionConfirmRequest)
        {
            l_response = this.validateMutualFixedBuyCondition((WEB3MutualFixedBuyConditionConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3MutualFixedBuyConditionCompleteRequest)
        {
            l_response = this.submitMutualFixedBuyCondition((WEB3MutualFixedBuyConditionCompleteRequest)l_request);
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
     * (input定時定額買付銘柄条件登録)<BR>
     * 投資信託定時定額買付銘柄条件登録入力を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * <BR>
     * =========================================================<BR>
     * 具体位置：is電子鳩障害中の戻り値 == trueの場合、<BR>
     * 　@　@　@　@　@BUSINESS_ERROR_01984「障害中注文不可」エラーをスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_01984<BR>
     * =========================================================<BR>
     * <BR>
     * 「（投信）定時定額買付銘柄条件登録入力」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3MutualFixedBuyConditionInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C2F00242
     */
    protected WEB3MutualFixedBuyConditionInputResponse inputMutualFixedBuyCondition(
        WEB3MutualFixedBuyConditionInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "inputMutualFixedBuyCondition(WEB3MutualFixedBuyConditionInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        l_request.validate();

        //validate注文受付可能
        //緊急停止されていないか、あるいはバッチ処理中でないかチェックする
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get補助口座
        //ログインセキュリティサービスより補助顧客を取得する
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //getCommonOrderValidator
        //注文チェックオブジェクトを取得する
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //get発注日
        //発注日を取得する
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //validate取引可能顧客
        //当該顧客が取引可能な顧客かどうかを判定する
        //[validate取引可能顧客に渡す引数]
        //　@顧客：取得した補助口座.getMainAccount()
        //　@発注日：取引時間管理.get発注日()の戻り値
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validateAccountForTrading = l_orderValidator.validateAccountForTrading(
            l_genMainAccount,
            new Timestamp(l_datOrderBizDate.getTime()));

        if (!l_validateAccountForTrading.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateAccountForTrading.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //validate引落口座登録
        //引落口座が登録済かどうかチェックを行う
        //［validate引落口座登録の引数］
        //証券会社コード：取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード：取得した補助口座.getMainAccount().getAccountCode()の戻り値
        String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strMainAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        l_mutualFixedBuyCommonService.validateDrawAccountRegist(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode,
            l_strMainAccountCode);

        //get投信銘柄カテゴリーリスト
        //投信銘柄カテゴリーリストを取得する
        //[get投信銘柄カテゴリーリストに渡すパラメタ]
        //証券会社コード：取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        List l_lisMFProductCategorysLists =
            l_mfProductManager.getMutualFundProductCategoryList(l_strSubAccountInstitutionCode);

        WEB3MutualProductCategoryUnit[] l_mutualProductCategoryUnits = null;
        //<分岐処理> get投信銘柄カテゴリーリスト（）の戻り値 > 0件の場合
        if (l_lisMFProductCategorysLists != null && l_lisMFProductCategorysLists.size() > 0)
        {
            //create投信銘柄カテゴリー一覧(List)
            l_mutualProductCategoryUnits =
                l_mfProductManager.createMutualFundProductCategoryList(l_lisMFProductCategorysLists);
        }

        //calc適用開始年月（業務日付）
        //[calc適用開始年月日（業務日付）の引数]
        //証券会社コード：取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        Date l_datOrderBiz = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //calc適用開始年月（現在日時）
        //[calc適用開始年月日（現在日時）の引数]
        //証券会社コード：取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        Date l_datCurrent = l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //merge定時定額買付条件変更
        //画面表示する、積立登録データと新規追加データを取得する
        //［merge定時定額買付条件変更の引数］
        //補助口座：取得した補助口座
        //適用開始年月：calc適用開始年月（業務日付）の戻り値
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChange(l_subAccount, l_datOrderBiz);

        boolean l_blnFutureCustomer = false;
        WEB3MutualFixedBuyConditionUnit[] l_mutualfixedBuyConditionUnitSorts = null;
        WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnits = null;
        Institution l_institution = l_subAccount.getInstitution();
        int l_intMutualFixedBuyConditionUnitsLength = 0;
        List l_lisUnitLists = new ArrayList();
        Iterator l_iteUnitLists  = null;
        if (l_mutualFixedBuyConditionUnits != null)
        {
            l_intMutualFixedBuyConditionUnitsLength = l_mutualFixedBuyConditionUnits.length;
            for (int i = 0; i < l_intMutualFixedBuyConditionUnitsLength; i++)
            {
                l_lisUnitLists.add(l_mutualFixedBuyConditionUnits[i]);
            }
            l_iteUnitLists = l_lisUnitLists.iterator();
        }
        
        //<分岐処理> merge定時定額買付条件変更の件数の戻り値が0件以外の場合
        if (l_mutualFixedBuyConditionUnits != null && l_intMutualFixedBuyConditionUnitsLength != 0)
        {

            //<LOOP処理> merge定時定額買付条件変更の件数の戻り値の要素数分LOOP
            while(l_iteUnitLists.hasNext())
            {
                //get投信銘柄
                //拡張投信銘柄の取得
                //[引数]
                //　@証券会社：取得した補助口座オブジェクト.getInstitution()の戻り値
                //　@銘柄コード：投信定時定額買付条件行.銘柄コード
                //　@回号コード：0
                WEB3MutualFundProduct l_mutualFundProduct = null;
                try
                {
                    WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                        (WEB3MutualFixedBuyConditionUnit) l_iteUnitLists.next();
                    l_mutualFundProduct = 
                        (WEB3MutualFundProduct)l_mfProductManager.getMutualFundProduct(
                        l_institution, l_mutualFixedBuyConditionUnit.mutualProductCode, "0");
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //is買付可能
                //買付可能期間のチェック
                //[引数]
                //　@発注日：get発注日の戻り値
                boolean l_blnAcquirePossible = l_mutualFundProduct.isAcquiredPossible(l_datOrderBizDate);

                //<分岐処理> is買付可能の戻り値が、falseの場合
                if(l_blnAcquirePossible == false)
                {
                    //merge定時定額買付条件変更の件数の戻り値より削除する
                       l_iteUnitLists.remove();
                }
            }

            l_mutualFixedBuyConditionUnits =
                new WEB3MutualFixedBuyConditionUnit[l_lisUnitLists.size()];
            l_lisUnitLists.toArray(l_mutualFixedBuyConditionUnits);

            //未来月あり顧客の判断
            //当該顧客のデータが未来月かどうか判断する。
            //以下の場合、未来月あり顧客フラグにtrueをセットする
            // merge定時定額買付条件変更の戻り値[0]．適用開始年月 >  calc適用開始年月(現在日時）の戻り値の場合、
            //trueをセットする
            if (l_lisUnitLists != null && l_lisUnitLists.size() != 0)
            {

                if (WEB3DateUtility.compareToMonth(l_mutualFixedBuyConditionUnits[0].validStartDate, l_datCurrent) > 0)
                {
                    l_blnFutureCustomer = true;
                }

                //sort定時定額買付条件一覧
                //[sort投信定時定額買付条件()に渡す引数]
                //投信定時定額買付条件行[ ]：merge定時定額買付条件変更の戻り値
                l_mutualfixedBuyConditionUnitSorts =
                    l_mutualFixedBuyCommonService.sortFixedBuyConditionList(l_mutualFixedBuyConditionUnits);

                //get定時定額買付金額合計
                //定時定額買付買付金額の合計を取得する。
                //[get定時定額買付買付金額合計の引数]
                //投信定時定額買付条件行[ ]：sort定時定額買付条件一覧の戻り値
                l_mutualFixedBuyTotalUnits =
                    this.getFixedBuyTotalUnit(l_mutualfixedBuyConditionUnitSorts);
            }
        }

        List l_lisLowMutualFundProductCategoryLists = null;
        //<分岐処理> リクエスト．カテゴリコードがnull以外の場合
        if (l_request.categoryCode != null)
        {
            //get投信銘柄カテゴリー(String, String)
            //証券会社コード =  取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
            //投信銘柄カテゴリーコード = リクエスト．カテゴリーコード
            WEB3MutualProductCategory l_mutualProductCategory =
                l_mfProductManager.getMutualFundProductCategory(
                    l_strSubAccountInstitutionCode,
                    l_request.categoryCode);

            //get下位投信銘柄カテゴリーリスト
            //指定されたカテゴリーコードに紐付く下位カテゴリーの投信銘柄カテゴリーParamsのリストを返す。
            //get下位投信銘柄カテゴリーリストの引数］
            //証券会社コード =  取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
            //カテゴリーコード = リクエスト．カテゴリーコード
            l_lisLowMutualFundProductCategoryLists = l_mfProductManager.getLowMutualFundProductCategoryList(
                l_strSubAccountInstitutionCode,
                l_request.categoryCode);

            //get下位投信銘柄カテゴリーリストの戻り値にget投信銘柄カテゴリーの戻り値を追加する。
            if (l_mutualProductCategory != null)
            {
                l_lisLowMutualFundProductCategoryLists.add(
                    (MutualFundProductCategoryRow)l_mutualProductCategory.getDataSourceObject());
            }
        }

        List l_lisFixedBuyPossibleProductLists = null;
        WEB3MutualFixedBuyConditionUnit[] l_mfBuyConditionUnitNews = null;

        //<分岐処理> （ リクエスト．初回表示フラグ == 「0：表示する」)　@または
        //　@（リクエスト．初回表示フラグ == 「2：閲覧チェック時」　@かつ　@リクエスト.銘柄コード != null ）） の場合
        if ((WEB3MFFirstDisplayDivDef.DISPLAY.equals(l_request.firstDisplayDiv)
            || (WEB3MFFirstDisplayDivDef.READING_CHECK.equals(l_request.firstDisplayDiv)
                && l_request.productCode != null)))
        {

            //<分岐処理> 未来月あり顧客フラグ == trueの場合、エラーをスローする。
            if(l_blnFutureCustomer)
            {
                log.debug("未来月申込銘柄があるため、新規銘柄追加不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03110,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "未来月申込銘柄があるため、新規銘柄追加不可。");
            }

            //定時定額で買付可能な銘柄を取得する
            //［get定時定額買付可能リストの引数］
            //証券会社コード =  取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
            //銘柄コード一覧 = リクエスト．銘柄コード
            //カテゴリーリスト = get下位投信銘柄カテゴリーリストの戻り値にget投信銘柄カテゴリーの戻り値を追加したリスト
            l_lisFixedBuyPossibleProductLists = l_mfProductManager.getFixedBuyPossibleProductList(
                l_strSubAccountInstitutionCode,
                l_request.productCode,
                l_lisLowMutualFundProductCategoryLists);

            int l_intFixedBuyPossibleProductListCnt = l_lisFixedBuyPossibleProductLists.size();
            //<LOOP処理> get定時定額買付可能リストの戻り値の要素数文LOOP
            l_mfBuyConditionUnitNews = new WEB3MutualFixedBuyConditionUnit[l_intFixedBuyPossibleProductListCnt];
            for (int i = 0; i < l_intFixedBuyPossibleProductListCnt; i++)
            {
                //投信定時定額買付条件行( )
                l_mfBuyConditionUnitNews[i] =
                    new WEB3MutualFixedBuyConditionUnit();

                //プロパティセット
                //以下の通り、プロパティをセットする。
                //銘柄コード：拡張投信銘柄.getProductCode
                WEB3MutualFundProduct l_fundProduct =
                    (WEB3MutualFundProduct)l_lisFixedBuyPossibleProductLists.get(i);
                MutualFundProductRow l_row =
                    (MutualFundProductRow)l_fundProduct.getDataSourceObject();
                l_mfBuyConditionUnitNews[i].mutualProductCode = l_row.getProductCode();
                //銘柄名：取得した拡張投信銘柄.get銘柄名
                l_mfBuyConditionUnitNews[i].mutualProductName = l_row.getStandardName();
                //投信銘柄カテゴリーコード：取得した拡張投信銘柄.getカテゴリコード
                l_mfBuyConditionUnitNews[i].categoryCode = l_row.getCategoryCode();
                //買付金額（月々）：null
                l_mfBuyConditionUnitNews[i].monthlyBuyAmount = null;
                //買付金額（積み増し）：null
                l_mfBuyConditionUnitNews[i].increaseBuyAmount = null;
                //銘柄表示順序：取得した拡張投信銘柄.get表示順位
                if (l_row.getIndicationRankingIsNull())
                {
                    l_mfBuyConditionUnitNews[i].displayOrder = null;
                }
                else
                {
                    l_mfBuyConditionUnitNews[i].displayOrder =
                        l_row.getIndicationRanking() + "";
                }
                //適用開始年月：calc適用開始年月（現在日時）の戻り値
                l_mfBuyConditionUnitNews[i].validStartDate = l_datCurrent;
                //更新日時：null
                l_mfBuyConditionUnitNews[i].updateDate = null;
                //口座引落年月：null
                l_mfBuyConditionUnitNews[i].debitAccountYM = null;
                //確定引落金額（積み増し）：null
                l_mfBuyConditionUnitNews[i].definiteIncreaseBuyAmount = null;
                //目論見書閲覧チェック：null
                l_mfBuyConditionUnitNews[i].checkResult = null;
                //一時停止中フラグ：false
                l_mfBuyConditionUnitNews[i].suspensionFlag = false;
            }
        }

        int l_intMutualFixedBuyConditionUnitsCnt = 0;
        if (l_mutualFixedBuyConditionUnits != null)
        {
            l_intMutualFixedBuyConditionUnitsCnt = l_mutualFixedBuyConditionUnits.length;
        }
        WEB3GentradeMultiDocCheckResultUnit[] l_gentradeMultiDocCheckResultUnits =
            new WEB3GentradeMultiDocCheckResultUnit[l_intMutualFixedBuyConditionUnitsCnt];
        String l_strUpMutualFundProductCategoryCode = null;
        WEB3GentradeBatoClientService l_gentradeBatoClientService = null;
        //<分岐処理> リクエスト．電子鳩チェックフラグ == trueの場合
        if (l_request.batoCheckFlag)
        {
            //is電子鳩停止中
            //is電子鳩障害中の戻り値 == trueの場合、
            //BUSINESS_ERROR_01984「障害中注文不可」エラーをスローする。
            l_gentradeBatoClientService =
                (WEB3GentradeBatoClientService)Services.getService(
                    WEB3GentradeBatoClientService.class);
            if (l_gentradeBatoClientService.isBatoStopping())
            {
                log.debug("[電子鳩システム障害中]障害中注文不可。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01984,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "[電子鳩システム障害中]障害中注文不可。");
            }

            //<LOOP処理> merge定時定額買付条件変更の戻り値の件数分LOOPし、
            //複数銘柄目論見書閲覧チェックUnitクラスの配列を作成する

            for (int i = 0; i < l_intMutualFixedBuyConditionUnitsCnt; i++)
            {
                //<分岐処理> リクエスト．種別コード == nullの場合
                if (l_request.categoryCode == null)
                {
                    //get上位投信銘柄カテゴリーコード
                    //指定された銘柄の親カテゴリーコードを取得する。
                    //（validate目論見書閲覧で使用）
                    //［get上位投信銘柄カテゴリーリストの引数］
                    //カテゴリーコード = merge定時定額買付条件変更の戻り値.カテゴリコード
                    //会社コード =  取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
                    l_strUpMutualFundProductCategoryCode = l_mfProductManager.getUpMutualFundProductCategoryCode(
                        l_mutualFixedBuyConditionUnits[i].categoryCode,
                        l_strSubAccountInstitutionCode);
                }

                //複数銘柄目論見書閲覧チェック結果Unitクラス
                l_gentradeMultiDocCheckResultUnits[i] = new WEB3GentradeMultiDocCheckResultUnit();
                //下記の通り、プロパティをセットする。
                //銘柄コード = merge定時定額買付条件変更の戻り値．銘柄コード
                //種別コード = リクエスト．種別コード == nullの場合、get上位投信銘柄カテゴリーコードの戻り値(0左詰め3桁)
                //             リクエスト．種別コード == null以外の場合、リクエスト．種別コード
                //チェック結果 = null
                l_gentradeMultiDocCheckResultUnits[i].productCode =
                    l_mutualFixedBuyConditionUnits[i].mutualProductCode;
                if (l_request.typeCode == null)
                {
                    if (l_strUpMutualFundProductCategoryCode == null
                        || l_strUpMutualFundProductCategoryCode.length() == 0)
                    {
                        l_strUpMutualFundProductCategoryCode = "000";
                    }
                    else if (l_strUpMutualFundProductCategoryCode.length() == 1)
                    {
                        l_strUpMutualFundProductCategoryCode = "00" + l_strUpMutualFundProductCategoryCode;
                    }
                    else if (l_strUpMutualFundProductCategoryCode.length() == 2)
                    {
                        l_strUpMutualFundProductCategoryCode = "0" + l_strUpMutualFundProductCategoryCode;
                    }
                    l_gentradeMultiDocCheckResultUnits[i].typeCode = l_strUpMutualFundProductCategoryCode;
                }
                else
                {
                    l_gentradeMultiDocCheckResultUnits[i].typeCode = l_request.typeCode;
                }
                l_gentradeMultiDocCheckResultUnits[i].checkResult = null;
            }

            WEB3GentradeMultiCheckResults l_gentradeMultiCheckResults = null;
            //<分岐処理> 複数銘柄目論見書閲覧チェックUnitクラスの配列がnull以外かつサイズが0以外の場合
            if (l_gentradeMultiDocCheckResultUnits != null
                && l_gentradeMultiDocCheckResultUnits.length != 0)
            {
                //validate複数銘柄目論見書閲覧
                //目論見書が閲覧済か未済かチェックする。
                //［validate複数銘柄目論見書閲覧の引数］
                //複数銘柄目論見書閲覧チェックリスト = 複数銘柄目論見書閲覧チェックUnitクラス［］
                //代理入力不可時チェックフラグ = false：チェックしない
                l_gentradeMultiCheckResults = l_gentradeBatoClientService.validateMultiProspectus(
                    l_gentradeMultiDocCheckResultUnits,
                    false);
            }

            // <LOOP処理> merge定時定額買付条件変更の戻り値の件数分LOOPし、
            //投信定時定額買付条件行に目論見書閲覧チェック結果をセットする
            for (int i = 0; i < l_intMutualFixedBuyConditionUnitsCnt; i++)
            {
                //プロパティセット
                //定時定額買付条件行オブジェクト．目論見書閲覧チェックに、プロパティをセットする。
                //validate複数銘柄目論見書閲覧の戻り値[index]．チェック結果が0： 閲覧済の場合
                //　@　@目論見書閲覧チェック =  0： 閲覧済
                //validate複数銘柄目論見書閲覧の戻り値[index]．チェック結果が1： 閲覧未済の場合
                //　@　@目論見書閲覧チェック =  1： 閲覧未済
                if (WEB3GentradeBatoCheckResultDef.INSPECTION.equals(
                    l_gentradeMultiCheckResults.checkResult[i].checkResult))
                {
                    l_mutualFixedBuyConditionUnits[i].checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
                }
                else if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(
                    l_gentradeMultiCheckResults.checkResult[i].checkResult))
                {
                    l_mutualFixedBuyConditionUnits[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
                }
            }

            //<LOOP処理> 新規追加投信定時定額買付条件行の要素数文LOOPし、
            //複数銘柄目論見書閲覧チェックUnitクラスの配列を作成する
            l_gentradeMultiDocCheckResultUnits = null;
            if (l_mfBuyConditionUnitNews != null && l_mfBuyConditionUnitNews.length > 0)
            {
                int l_intMfBuyConditionUnitNewsCnt = l_mfBuyConditionUnitNews.length;
                l_gentradeMultiDocCheckResultUnits =
                    new WEB3GentradeMultiDocCheckResultUnit[l_intMfBuyConditionUnitNewsCnt];
                for (int i = 0; i < l_intMfBuyConditionUnitNewsCnt; i++)
                {
                    //<分岐処理> リクエスト．種別コード == nullの場合
                    //指定された銘柄の親カテゴリーコードを取得する。
                    //（validate目論見書閲覧で使用）
                    //［get上位投信銘柄カテゴリーリストの引数］
                    //カテゴリーコード = 新規追加投信定時定額買付条件行．カテゴリーコード
                    //会社コード =  取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
                    if (l_request.typeCode == null)
                    {
                        l_strUpMutualFundProductCategoryCode = l_mfProductManager.getUpMutualFundProductCategoryCode(
                            l_mfBuyConditionUnitNews[i].categoryCode,
                            l_strSubAccountInstitutionCode);
                    }

                    //複数銘柄目論見書閲覧チェック結果Unitクラス
                    l_gentradeMultiDocCheckResultUnits[i] =
                        new WEB3GentradeMultiDocCheckResultUnit();

                    //プロパティセット
                    //下記の通り、プロパティをセットする。
                    //銘柄コード = 新規追加投信定時定額買付条件行．銘柄コード
                    //種別コード = リクエスト．種別コード == nullの場合、get上位投信銘柄カテゴリーコードの戻り値(0左詰め3桁)
                    //リクエスト．種別コード == null以外の場合、リクエスト．種別コード
                    //チェック結果 = null
                    l_gentradeMultiDocCheckResultUnits[i].productCode =
                        l_mfBuyConditionUnitNews[i].mutualProductCode;
                    if (l_request.typeCode == null)
                    {
                        if (l_strUpMutualFundProductCategoryCode == null
                            || l_strUpMutualFundProductCategoryCode.length() == 0)
                        {
                            l_strUpMutualFundProductCategoryCode = "000";
                        }
                        else if (l_strUpMutualFundProductCategoryCode.length() == 1)
                        {
                            l_strUpMutualFundProductCategoryCode = "00" + l_strUpMutualFundProductCategoryCode;
                        }
                        else if (l_strUpMutualFundProductCategoryCode.length() == 2)
                        {
                            l_strUpMutualFundProductCategoryCode = "0" + l_strUpMutualFundProductCategoryCode;
                        }
                        l_gentradeMultiDocCheckResultUnits[i].typeCode = l_strUpMutualFundProductCategoryCode;
                    }
                    else if (l_request.typeCode != null)
                    {
                        l_gentradeMultiDocCheckResultUnits[i].typeCode = l_request.typeCode;
                    }
                    l_gentradeMultiDocCheckResultUnits[i].checkResult = null;
                }
            }

            //<分岐処理> 複数銘柄目論見書閲覧チェックUnitクラスの配列がnull以外かつサイズが0以外の場合
            if (l_gentradeMultiDocCheckResultUnits != null
                && l_gentradeMultiDocCheckResultUnits.length != 0)
            {
                //validate複数銘柄目論見書閲覧(複数銘柄目論見書閲覧チェックリスト
                //目論見書が閲覧済か未済かチェックする。
                //［validate複数銘柄目論見書閲覧の引数］
                //複数銘柄目論見書閲覧チェックリスト = 複数銘柄目論見書閲覧チェックUnitクラスの配列
                //代理入力不可時チェックフラグ = false：チェックしない
                l_gentradeMultiCheckResults = l_gentradeBatoClientService.validateMultiProspectus(
                    l_gentradeMultiDocCheckResultUnits,
                    false);
            }

            //<LOOP処理> 新規追加投信定時定額買付条件行の要素数文LOOPし、
            //投信定時定額買付条件行に目論見書閲覧チェック結果をセットする
            if (l_mfBuyConditionUnitNews != null && l_mfBuyConditionUnitNews.length > 0)
            {
                int l_intMfBuyConditionUnitNewsCnt = l_mfBuyConditionUnitNews.length;
                for (int i = 0; i< l_intMfBuyConditionUnitNewsCnt; i++)
                {
                    //プロパティセット
                    //定時定額買付条件行オブジェクト．目論見書閲覧チェックに、プロパティをセットする。
                    //validate複数銘柄目論見書閲覧の戻り値[index]．チェック結果が0： 閲覧済の場合
                    //目論見書閲覧チェック =  0： 閲覧済
                    //validate複数銘柄目論見書閲覧の戻り値[index]．チェック結果が1： 閲覧未済の場合
                    //目論見書閲覧チェック =  1： 閲覧未済
                  if (WEB3GentradeBatoCheckResultDef.INSPECTION.equals(
                      l_gentradeMultiCheckResults.checkResult[i].checkResult))
                  {
                      l_mfBuyConditionUnitNews[i].checkResult = WEB3GentradeBatoCheckResultDef.INSPECTION;
                  }
                  else if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(
                      l_gentradeMultiCheckResults.checkResult[i].checkResult))
                  {
                      l_mfBuyConditionUnitNews[i].checkResult = WEB3GentradeBatoCheckResultDef.UNINSPECTION;
                  }
                }
            }
        }

        //定時定額買付引落口座
        //定時定額買付引落口座クラスを生成する。
        //[定時定額買付引落口座に渡す引数]
        //　@証券会社コード：取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード：取得した補助口座.getMainAccount().getAccountCode()の戻り値
        WEB3MutualFixedBuyDrawAccount l_mfBuyDrawAccount = null;
        try
        {
            l_mfBuyDrawAccount = new WEB3MutualFixedBuyDrawAccount(
                l_strSubAccountInstitutionCode,
                l_strMainAccountBranchCode,
                l_strMainAccountCode);
        }
        catch (WEB3BaseException l_be)
        {
            log.debug("定時定額買付引落口座に該当するデータがありません!");
        }
        //<分岐処理> 定時定額買付引落口座．get金融機@関区分=銀行の場合
        String[] l_strFinBranchNames = null;
        if (l_mfBuyDrawAccount != null)
        {
            if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //get銀行支店名
                //[get銀行支店名に渡す引数]
                //銀行コード：定時定額買付引落口座．銀行コード
                //支店コード：定時定額買付引落口座．支店コード
                l_strFinBranchNames = l_mutualFixedBuyCommonService.getFinBranchName(
                    l_mfBuyDrawAccount.getFinInstitutionCode(),
                    l_mfBuyDrawAccount.getFinBranchCode());
            }
        }

        //投信定時定額引落口座情報
        WEB3MutualFixedBuyAccountInfo l_mfBuyAccountInfo = new WEB3MutualFixedBuyAccountInfo();
        //プロパティセット
        //以下の通り、プロパティをセットする。
        //・銀行コード=定時定額買付引落口座．銀行コード
        l_mfBuyAccountInfo.financialInstitutionCode = l_mfBuyDrawAccount.getFinInstitutionCode();
        //・支店コード=定時定額買付引落口座．支店コード
        l_mfBuyAccountInfo.financialBranchCode = l_mfBuyDrawAccount.getFinBranchCode();
        //・金融機@関区分=定時定額買付引落口座．金融機@関区分
        l_mfBuyAccountInfo.financialInstitutionDiv = l_mfBuyDrawAccount.getFinInstitutionDiv();
        //・引落口座番号=定時定額買付引落口座．引落口座番号
        l_mfBuyAccountInfo.financialAccountCode = l_mfBuyDrawAccount.getDrawAccountNo();
        //・引落口座名義人（カナ）=定時定額買付引落口座．引落口座名義人（カナ）
        l_mfBuyAccountInfo.financialAccountName = l_mfBuyDrawAccount.getDrawAccountNameKana();
        //金融機@関区分が銀行の場合
        if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
        {
            //・銀行名=get銀行支店名の戻り値String[0]
            l_mfBuyAccountInfo.financialInstitutionName = l_strFinBranchNames[0];
            //・支店名=get銀行支店名の戻り値String[1]
            l_mfBuyAccountInfo.financialBranchName = l_strFinBranchNames[1];
            //・預金区分=定時定額買付引落口座．預金区分
            l_mfBuyAccountInfo.financialAccountDiv = l_mfBuyDrawAccount.getDepositDiv();
        }
        //金融機@関区分が郵便局の場合
        else if (WEB3FinInstitutionDivDef.POST_OFFICE.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
        {
            //・銀行名=null
            l_mfBuyAccountInfo.financialInstitutionName = null;
            //・支店名=null
            l_mfBuyAccountInfo.financialBranchName = null;
            //・預金区分=null
            l_mfBuyAccountInfo.financialAccountDiv = null;
        }

        //createレスポンス
        //（createResponseの実装）
        //定時定額買付銘柄条件登録入力レスポンスオブジェクトを生成して返す。
        WEB3MutualFixedBuyConditionInputResponse l_response =
            (WEB3MutualFixedBuyConditionInputResponse)l_request.createResponse();

        //レスポンスセット
        //投信定時定額買付積立登録内容  = sort定時定額買付条件一覧の戻り値
        //投信定時定額買付新規追加内容　@=　@新規追加投信定時定額買付条件行
        //投信定時定額引落口座　@=　@取得した投信定時定額引落口座情報
        //投信定時定額買付金額合計　@= get投信定時定額買付金額合計の戻り値
        //投信銘柄カテゴリー一覧 = create投信銘柄カテゴリー一覧の戻り値
        if (l_mutualfixedBuyConditionUnitSorts != null && l_mutualfixedBuyConditionUnitSorts.length != 0)
        {
            l_response.conditionList = l_mutualfixedBuyConditionUnitSorts;
        }

        l_response.addConditionList = l_mfBuyConditionUnitNews;
        l_response.acountInfo = l_mfBuyAccountInfo;

        if (l_mutualFixedBuyTotalUnits != null && l_mutualFixedBuyTotalUnits.length != 0)
        {
            l_response.totalList = l_mutualFixedBuyTotalUnits;
        }
        l_response.categoryList = l_mutualProductCategoryUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate定時定額買付銘柄条件登録)<BR>
     * 投資信託定時定額買付銘柄条件登録確認を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投信）定時定額買付銘柄条件登録確認」参照。<BR>
     * <BR>
     * =========================================================<BR>
     * 具体位置：validate外国証券口座開設<BR>
     * 　@　@　@　@　@　@(補助口座 : SubAccount, 拡張投信銘柄 : 拡張投信銘柄)<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_01341<BR>
     * =========================================================<BR>
     *  <BR>
     * =========================================================<BR>
     * 具体位置：＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02480<BR>
     * =========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3MutualFixedBuyConditionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C53F0149
     */
    protected WEB3MutualFixedBuyConditionConfirmResponse validateMutualFixedBuyCondition(
        WEB3MutualFixedBuyConditionConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMutualFixedBuyCondition(WEB3MutualFixedBuyConditionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        //リクエストチェック
        l_request.validate();

        //validate注文受付可能
        //緊急停止されていないか、あるいはバッチ処理中でないかチェックする
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get補助口座
        //ログインセキュリティサービスより補助顧客を取得する
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //getCommonOrderValidator
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //get発注日
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //validate取引可能顧客
        //当該顧客が取引可能な顧客かどうかを判定する。
        //[validate取引可能顧客に渡す引数]
        //　@顧客：取得した補助口座.getMainAccount()
        //　@発注日：取引時間管理.get発注日()の戻り値
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validateAccountForTrading = l_orderValidator.validateAccountForTrading(
            l_genMainAccount,
            new Timestamp(l_datOrderBizDate.getTime()));

        if (!l_validateAccountForTrading.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateAccountForTrading.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //validate引落口座登録
        //引落口座が登録済かどうかチェックを行う。
        //［validate引落口座登録の引数］
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード：取得した補助口座.getMainAccount().getAccountCode()の戻り値
        String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strMainAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        l_mutualFixedBuyCommonService.validateDrawAccountRegist(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode,
            l_strMainAccountCode);

        //calc適用開始年月（業務日付）
        //適用開始年月（業務日付）を取得する。
        //[calc適用開始年月（業務日付）の引数]
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        Date l_datOrderBiz = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //calc適用開始年月（現在日時）
        //適用開始年月（現在日時）を取得する。
        //[calc適用開始年月（現在日時）の引数]
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        Date l_datCurrent = l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //定時定額買付締切日引落日計算
        //［定時定額買付締切日引落日計算の引数］
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strSubAccountInstitutionCode, l_strMainAccountBranchCode);

        //calc賞与締切日時
        //賞与締切日を取得する。
        //[calc賞与締切日の引数]
        //指定年月：calc適用開始年月（現在日時）の戻り値
        Date l_datCalcPrizeAndCloseDateHour =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcPrizeAndCloseDateHour(l_datCurrent);

        //calc通常締切日時（WEB）
        //通常締切日時(WEB)を取得する
        //[calc通常締切日時（WEB）の引数]
        //指定年月：calc適用開始年月（現在日時）の戻り値
        Date l_datCalcUsuallyCloseDateTime =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDateTime(l_datCurrent);

        //買付金額積み増し変更フラグ == false
        //買付金額積み増し変更フラグにfalseをセットする。
        //以下のLOOP処理で、リクエスト．買付金額（積み増し）!=nullの場合、trueをセットする
        int l_intConditionLisCnt = 0;
        if (l_request.conditionList != null)
        {
            l_intConditionLisCnt = l_request.conditionList.length;
        }
        boolean l_blnIncreaseBuyAmountFlag = false;


        //<LOOP処理> リクエストデータ．投信定時定額買付積立登録内容の件数分LOOP
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        MutualFundProduct l_mutualFundProduct = null;
        Institution l_institution = l_subAccount.getInstitution();
        for (int i = 0; i < l_intConditionLisCnt; i++)
        {
            //get投信銘柄
            //拡張投信銘柄を取得する。
            //[get投信銘柄に渡すパラメタ]
            //証券会社：　@取得した補助口座.getInstitution()の戻り値
            //銘柄コード： リクエストデータ.投信定時定額買付積立登録内容.銘柄コード
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.conditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWFか判定を行う
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is外国投信
            //外国投信か判定を行う
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()の戻り値 == true or is外国投信()の戻り値 == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //外国証券口座開設済か判定を行う
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //戻り値がfalseの場合、「外国証券口座未開設エラー」として例外をスローする
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("当該顧客は外国証券口座開設なし。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "当該顧客は外国証券口座開設なし。");
                }
            }

            //validate定時定額買付金額
            //定時定額買付金額のチェックを行なう。
            //[validate定時定額買付金額に渡すパラメタ]
            //拡張投信銘柄：取得した補助口座
            //買付金額（月々）：リクエストデータ.投信定時定額買付積立登録内容.買付金額（月々）
            //買付金額（積み増し）：リクエストデータ.投信定時定額買付積立登録内容.買付金額（積み増し）
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.conditionList[i].monthlyBuyAmount,
                l_request.conditionList[i].increaseBuyAmount);

            //is定時定額買付可能
            //定時定額買付可能銘柄かどうかのチェック
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする
            if (!l_blnFixedBuyPos)
            {
                //定時定額買付不可銘柄エラーとして例外をスローする。
                //※例外文字列の追加
                //　@（WEB3Exception.errorMessage)
                //に以下の内容をセットする。
                //リクエスト.定時定額買付共通情報.銘柄コード
                log.debug("定時定額買付不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.conditionList[i].mutualProductCode);
            }

            //リクエスト．投信定時定額買付積立登録内容．買付金額（積み増し） != nullの場合、
            //買付金額積み増し変更フラグにtrueをセットする。
            if (l_request.conditionList[i].increaseBuyAmount != null)
            {
                l_blnIncreaseBuyAmountFlag = true;
            }
        }

        //<LOOP処理> リクエストデータ．投信定時定額買付新規追加内容の件数分LOOP
        int l_intAddConditionListCnt = 0;
        if (l_request.addConditionList != null)
        {
            l_intAddConditionListCnt = l_request.addConditionList.length;
        }
        //投信定時定額買付条件行
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_intAddConditionListCnt];
        for (int i = 0; i < l_intAddConditionListCnt; i++)
        {
            //get投信銘柄
            //拡張投信銘柄を取得する。
            //[get投信銘柄に渡すパラメタ]
            //証券会社：取得した補助口座.getInstitution()の戻り値
            //銘柄コード：リクエストデータ.投信定時定額買付新規追加内容.銘柄コード
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.addConditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWFか判定を行う
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is外国投信
            //外国投信か判定を行う
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()の戻り値 == true or is外国投信()の戻り値 == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //外国証券口座開設済か判定を行う
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //戻り値がfalseの場合、「外国証券口座未開設エラー」として例外をスローする
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("当該顧客は外国証券口座開設なし。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "当該顧客は外国証券口座開設なし。");
                }
            }

            //validate定時定額買付金額
            //定時定額買付金額のチェックを行なう。
            //[validate定時定額買付金額に渡すパラメタ]
            //拡張投信銘柄：取得した補助口座
            //買付金額（月々）：リクエストデータ.投信定時定額買付新規追加内容.買付金額（月々）
            //買付金額（積み増し）：リクエストデータ.投信定時定額買付新規追加内容.買付金額（積み増し）
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.addConditionList[i].monthlyBuyAmount,
                l_request.addConditionList[i].increaseBuyAmount);

            //is定時定額買付可能
            //定時定額買付可能銘柄かどうかのチェック
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする
            if (!l_blnFixedBuyPos)
            {
                //定時定額買付不可銘柄エラーとして例外をスローする。
                //※例外文字列の追加
                //　@（WEB3Exception.errorMessage)
                //に以下の内容をセットする。
                //リクエスト.定時定額買付共通情報.銘柄コード
                log.debug("定時定額買付不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.addConditionList[i].mutualProductCode);
            }

            //投信定時定額買付条件行
            l_mutualFixedBuyConditionUnits[i] =
                new WEB3MutualFixedBuyConditionUnit();
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //下記の通り、プロパティをセットする。
            //銘柄コード：リクエスト．定時定額買付新規追加内容．銘柄コード
            l_mutualFixedBuyConditionUnits[i].mutualProductCode = l_request.addConditionList[i].mutualProductCode;
            //買付金額（月々）：リクエスト．定時定額買付新規追加内容．買付金額（月々）
            l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = l_request.addConditionList[i].monthlyBuyAmount;
            //買付金額（積み増し）：リクエスト．定時定額買付新規追加内容．買付金額（積み増し）
            l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = l_request.addConditionList[i].increaseBuyAmount;
            //銘柄表示順序：取得した拡張投信銘柄.get表示順位
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder =
                    l_mutualFundProductRow.getIndicationRanking() + "";
            }
            //適用開始年月：calc適用開始年月（現在日時）の戻り値
            l_mutualFixedBuyConditionUnits[i].validStartDate = l_datCurrent;
            //更新日時：null
            l_mutualFixedBuyConditionUnits[i].updateDate = null;
            //口座引落年月：null
            l_mutualFixedBuyConditionUnits[i].debitAccountYM = null;
            //確定引落金額（積み増し）：null
            l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = null;
            //目論見書閲覧チェック：null
            l_mutualFixedBuyConditionUnits[i].checkResult = null;
            //一時停止中フラグ：false
            l_mutualFixedBuyConditionUnits[i].suspensionFlag = false;
        }

        //締切日アラート要求フラグの判断
        //以下の場合、締切日アラート要求フラグにtrueをセットする。
        //is賞与月 = true かつ
        //calc賞与締切日時 < 現在日時 <= calc通常締切日時(WEB)　@かつ
        //買付金額積み増し変更フラグ == true
        //[is賞与月の引数]
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //指定年月：calc適用開始年月（現在日時）の戻り値
        boolean l_blnClosingAlertRequestFlags = false;
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_mutualFixedBuyCommonService.isPrizeAndMonth(l_strSubAccountInstitutionCode, l_datCurrent)
            && WEB3DateUtility.compareToSecond(l_datCalcPrizeAndCloseDateHour, l_tsSystemTimestamp) < 0
            && WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_datCalcUsuallyCloseDateTime) <= 0
            && l_blnIncreaseBuyAmountFlag)
        {
            l_blnClosingAlertRequestFlags = true;
        }

        //merge定時定額買付条件変更
        //画面表示する、積立登録データと新規追加データを取得する。
        //［merge定時定額買付条件変更の引数
        //補助口座：取得した補助口座オブジェクト
        //適用開始年月：calc適用開始年月（業務日付）の戻り値
        WEB3MutualFixedBuyConditionUnit[] l_mfBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChange(l_subAccount, l_datOrderBiz);

        WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionList = null;
        //<分岐処理> merge定時定額買付条件変更の件数が0件以外の場合
        if (l_mfBuyConditionUnits != null && l_mfBuyConditionUnits.length != 0)
        {
            //sort定時定額買付条件一覧
            //ソート処理
            //[sort投信定時定額買付条件()に渡す引数]
            // merge定時定額買付条件変更の戻り値
            l_sortFixedBuyConditionList =
                l_mutualFixedBuyCommonService.sortFixedBuyConditionList(l_mfBuyConditionUnits);
        }

        //createレスポンス
        //定時定額買付銘柄条件登録確認レスポンスオブジェクトを生成して返す
        WEB3MutualFixedBuyConditionConfirmResponse l_response =
            (WEB3MutualFixedBuyConditionConfirmResponse)l_request.createResponse();

        //レスポンスセット
        //投信定時定額買付積立登録内容  = sort定時定額買付条件一覧の戻り値
        //投信定時定額買付新規追加内容　@=　@新規追加投信定時定額買付条件行
        //投信定時定額引落口座　@=　@null
        //投信定時定額買付金額合計　@= null
        //投信銘柄カテゴリー一覧  =　@null
        //締切日アラート要求フラグ = 締切日アラート要求フラグの判断でセットした値
        l_response.conditionList = l_sortFixedBuyConditionList;
        l_response.addConditionList = l_mutualFixedBuyConditionUnits;
        l_response.acountInfo = null;
        l_response.totalList = null;
        l_response.categoryList = null;
        l_response.closingDateAlertFlag = l_blnClosingAlertRequestFlags;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit定時定額買付銘柄条件登録)<BR>
     * 投資信託定時定額買付銘柄条件登録完了を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（投信）定時定額買付銘柄条件登録完了」参照。<BR>
     * <BR>
     * =========================================================<BR>
     * 具体位置：validate外国証券口座開設<BR>
     * 　@　@　@　@　@　@(補助口座 : SubAccount, 拡張投信銘柄 : 拡張投信銘柄)<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_01341<BR>
     * =========================================================<BR>
     * <BR>
     * =========================================================<BR>
     * 具体位置：＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02480<BR>
     * =========================================================<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3MutualFixedBuyConditionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4851C5E802A5
     */
    protected WEB3MutualFixedBuyConditionCompleteResponse submitMutualFixedBuyCondition(
        WEB3MutualFixedBuyConditionCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitMutualFixedBuyCondition(WEB3MutualFixedBuyConditionCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate
        //リクエストチェック
        l_request.validate();

        //validate注文受付可能
        //緊急停止されていないか、あるいはバッチ処理中でないかチェックする
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //get補助口座
        //ログインセキュリティサービスより補助顧客を取得する
        SubAccount l_subAccount = this.getSubAccount();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //getCommonOrderValidator
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //get発注日
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //validate取引可能顧客
        //当該顧客が取引可能な顧客かどうかを判定する。
        //[validate取引可能顧客に渡す引数]
        //　@顧客：取得した補助口座.getMainAccount()
        //　@発注日：取引時間管理.get発注日()の戻り値
        WEB3GentradeMainAccount l_genMainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        OrderValidationResult l_validateAccountForTrading = l_orderValidator.validateAccountForTrading(
            l_genMainAccount,
            new Timestamp(l_datOrderBizDate.getTime()));

        if (!l_validateAccountForTrading.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateAccountForTrading.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //get代理入力者
        //代理入力者オブジェクトを取得する。
        //（成りすましではない場合、null が返却される）
        Trader l_trader = this.getTrader();

        //validate取引パスワード
        //validate取引パスワード( )をコールする
        //[validate取引パスワードに渡すパラメタ]
        //代理入力者：取得した代理入力者
        //補助口座：取得した補助口座オブジェクト
        //パスワード：リクエストデータ.暗証番号
        OrderValidationResult l_validateTradingPassword = l_orderValidator.validateTradingPassword(
            l_trader,
            l_subAccount,
            l_request.password);

        if (!l_validateTradingPassword.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validateTradingPassword.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //validate引落口座登録
        //引落口座が登録済かどうかチェックを行う。
        //［validate引落口座登録の引数］
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード：取得した補助口座.getMainAccount().getAccountCode()の戻り値
        String l_strSubAccountInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        String l_strMainAccountBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strMainAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        l_mutualFixedBuyCommonService.validateDrawAccountRegist(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode,
            l_strMainAccountCode);

        //calc適用開始年月（業務日付）
        //適用開始年月（業務日付）を取得する。
        //[calc適用開始年月（業務日付）の引数]
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        Date l_datOrderBiz = l_mutualFixedBuyCommonService.calcValidStartDateOrderBizdate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //calc適用開始年月（現在日時）
        //適用開始年月（現在日時）を取得する。
        //[calc適用開始年月（現在日時）の引数]
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        Date l_datCurrent = l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
            l_strSubAccountInstitutionCode,
            l_strMainAccountBranchCode);

        //定時定額買付締切日引落日計算
        //［定時定額買付締切日引落日計算の引数］
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strSubAccountInstitutionCode, l_strMainAccountBranchCode);

        //calc賞与締切日時
        //賞与締切日を取得する。
        //[calc賞与締切日の引数]
        //指定年月：calc適用開始年月（現在日時）の戻り値
        Date l_datCalcPrizeAndCloseDateHour =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcPrizeAndCloseDateHour(l_datCurrent);

        //calc通常締切日時（WEB）
        //通常締切日時(WEB)を取得する
        //[calc通常締切日時（WEB）の引数]
        //指定年月：calc適用開始年月（現在日時）の戻り値
        Date l_datCalcUsuallyCloseDateTime =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDateTime(l_datCurrent);

        //買付金額積み増し変更フラグ == false
        //買付金額積み増し変更フラグにfalseをセットする。
        //以下のLOOP処理で、リクエスト．買付金額（積み増し）!=nullの場合、trueをセットする
        int l_intConditionLisCnt = 0;
        if (l_request.conditionList != null)
        {
            l_intConditionLisCnt = l_request.conditionList.length;
        }
        boolean l_blnIncreaseBuyAmountFlag = false;

        //<LOOP処理> リクエストデータ．投信定時定額買付積立登録内容の件数分LOOP
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        MutualFundProduct l_mutualFundProduct = null;
        Institution l_institution = l_subAccount.getInstitution();
        for (int i = 0; i < l_intConditionLisCnt; i++)
        {
            //get投信銘柄
            //拡張投信銘柄を取得する。
            //[get投信銘柄に渡すパラメタ]
            //証券会社：　@取得した補助口座.getInstitution()の戻り値
            //銘柄コード： リクエストデータ.投信定時定額買付積立登録内容.銘柄コード
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.conditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWFか判定を行う
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is外国投信
            //外国投信か判定を行う
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()の戻り値 == true or is外国投信()の戻り値 == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //外国証券口座開設済か判定を行う
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //戻り値がfalseの場合、「外国証券口座未開設エラー」として例外をスローする
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("当該顧客は外国証券口座開設なし。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "当該顧客は外国証券口座開設なし。");
                }
            }

            //validate定時定額買付金額
            //定時定額買付金額のチェックを行なう。
            //[validate定時定額買付金額に渡すパラメタ]
            //拡張投信銘柄：取得した補助口座
            //買付金額（月々）：リクエストデータ.定時定額買付積立登録内容.買付金額（月々）
            //買付金額（積み増し）：リクエストデータ.定時定額買付積立登録内容.買付金額（積み増し）
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.conditionList[i].monthlyBuyAmount,
                l_request.conditionList[i].increaseBuyAmount);

            //is定時定額買付可能
            //定時定額買付可能銘柄かどうかのチェック
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする
            if (!l_blnFixedBuyPos)
            {
                //定時定額買付不可銘柄エラーとして例外をスローする。
                //※例外文字列の追加
                //　@（WEB3Exception.errorMessage)
                //に以下の内容をセットする。
                //リクエスト.定時定額買付共通情報.銘柄コード
                log.debug("定時定額買付不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.conditionList[i].mutualProductCode);
            }

            //リクエスト．定時定額買付積立登録内容．買付金額（積み増し） != nullの場合、
            //買付金額積み増し変更フラグにtrueをセットする
            if (l_request.conditionList[i].increaseBuyAmount != null)
            {
                l_blnIncreaseBuyAmountFlag = true;
            }
        }

        //<LOOP処理> リクエストデータ．投信定時定額買付新規追加内容の件数分LOOP
        int l_intAddConditionListCnt = 0;
        if (l_request.addConditionList != null)
        {
            l_intAddConditionListCnt = l_request.addConditionList.length;
        }
        //投信定時定額買付条件行
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_intAddConditionListCnt];
        for (int i = 0; i < l_intAddConditionListCnt; i++)
        {
            //get投信銘柄
            //拡張投信銘柄を取得する。
            //[get投信銘柄に渡すパラメタ]
            //証券会社：取得した補助口座.getInstitution()の戻り値
            //銘柄コード：リクエストデータ.投信定時定額買付新規追加内容.銘柄コード
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_request.addConditionList[i].mutualProductCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)l_mutualFundProduct;
            //isFWF
            //FWFか判定を行う
            boolean l_blnIsFWF = l_mfProduct.isFWF();

            //is外国投信
            //外国投信か判定を行う
            boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

            //isFWF()の戻り値 == true or is外国投信()の戻り値 == true
            if (l_blnIsFWF || l_blnIsForeignFund)
            {
                //外国証券口座開設済か判定を行う
                boolean l_blnIsForeignAccountOpen = l_genMainAccount.isForeignAccountOpen();

                //戻り値がfalseの場合、「外国証券口座未開設エラー」として例外をスローする
                if (!l_blnIsForeignAccountOpen)
                {
                    log.debug("当該顧客は外国証券口座開設なし。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "当該顧客は外国証券口座開設なし。");
                }
            }

            //validate定時定額買付金額
            //定時定額買付金額のチェックを行なう。
            //[validate定時定額買付金額に渡すパラメタ]
            //拡張投信銘柄：取得した補助口座
            //買付金額（月々）：リクエストデータ.投信定時定額買付新規追加内容.買付金額（月々））
            //買付金額（積み増し）：リクエストデータ.投信定時定額買付新規追加内容.買付金額（積み増し）
            l_mutualFixedBuyCommonService.validateFixedBuyAmount(
                l_subAccount,
                l_request.addConditionList[i].monthlyBuyAmount,
                l_request.addConditionList[i].increaseBuyAmount);

            //is定時定額買付可能
            //定時定額買付可能銘柄かどうかのチェック
            boolean l_blnFixedBuyPos = l_mfProduct.isFixedBuyPossible();

            //＜分岐処理＞is定時定額買付可能()の戻り値 == false の場合、例外をスローする
            if (!l_blnFixedBuyPos)
            {
                //定時定額買付不可銘柄エラーとして例外をスローする。
                //※例外文字列の追加
                //　@（WEB3Exception.errorMessage)
                //に以下の内容をセットする。
                //リクエスト.定時定額買付共通情報.銘柄コード
                log.debug("定時定額買付不可銘柄エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02480,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_request.addConditionList[i].mutualProductCode);
            }

            //投信定時定額買付条件行
            l_mutualFixedBuyConditionUnits[i] =
                new WEB3MutualFixedBuyConditionUnit();
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //下記の通り、プロパティをセットする。
            //銘柄コード：リクエスト．定時定額買付新規追加内容．銘柄コード
            l_mutualFixedBuyConditionUnits[i].mutualProductCode = l_request.addConditionList[i].mutualProductCode;
            //買付金額（月々）：リクエスト．定時定額買付新規追加内容．買付金額（月々）
            l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = l_request.addConditionList[i].monthlyBuyAmount;
            //買付金額（積み増し）：リクエスト．定時定額買付新規追加内容．買付金額（積み増し）
            l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = l_request.addConditionList[i].increaseBuyAmount;
            //銘柄表示順序：取得した拡張投信銘柄.get表示順位
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnits[i].displayOrder =
                    l_mutualFundProductRow.getIndicationRanking() + "";
            }
            //適用開始年月：calc適用開始年月（現在日時）の戻り値
            l_mutualFixedBuyConditionUnits[i].validStartDate = l_datCurrent;
            //更新日時：null
            l_mutualFixedBuyConditionUnits[i].updateDate = null;
            //口座引落年月：null
            l_mutualFixedBuyConditionUnits[i].debitAccountYM = null;
            //確定引落金額（積み増し）：null
            l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = null;
            //目論見書閲覧チェック：null
            l_mutualFixedBuyConditionUnits[i].checkResult = null;
            //一時停止中フラグ：false
            l_mutualFixedBuyConditionUnits[i].suspensionFlag = false;
        }

        //締切日アラート要求フラグの判断
        //以下の場合、締切日アラート要求フラグにtrueをセットする。
        //is賞与月 = true かつ
        //calc賞与締切日時 < 現在日時 <= calc通常締切日時(WEB)　@かつ
        //買付金額積み増し変更フラグ == true
        //[is賞与月の引数]
        //証券会社コード：取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値
        //指定年月：calc適用開始年月（現在日時）の戻り値
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
        boolean l_blnClosingAlertRequestFlags = false;
        if (l_mutualFixedBuyCommonService.isPrizeAndMonth(
            l_strSubAccountInstitutionCode, l_datCurrent)
            && WEB3DateUtility.compareToSecond(l_datCalcPrizeAndCloseDateHour, l_tsSystemTimestamp) < 0
            && WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_datCalcUsuallyCloseDateTime) <= 0
            && l_blnIncreaseBuyAmountFlag)
        {
            l_blnClosingAlertRequestFlags = true;
        }

        //merge定時定額買付条件変更
        //画面表示する、積立登録データと新規追加データを取得する。
        //［merge定時定額買付条件変更の引数］
        //補助口座：取得した補助口座オブジェクト
        //適用開始年月：calc適用開始年月（業務日付）の戻り値
        WEB3MutualFixedBuyConditionUnit[] l_mfBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChange(l_subAccount, l_datOrderBiz);

        WEB3MutualFixedBuyConditionUnit[] l_sortFixedBuyConditionList = null;
        //<分岐処理> merge定時定額買付条件変更の件数が0件以外の場合
        if (l_mfBuyConditionUnits != null && l_mfBuyConditionUnits.length != 0)
        {
            //sort定時定額買付条件一覧
            //ソート処理
            //[sort投信定時定額買付条件()に渡す引数]
            // merge定時定額買付条件変更の戻り値
            l_sortFixedBuyConditionList =
                l_mutualFixedBuyCommonService.sortFixedBuyConditionList(l_mfBuyConditionUnits);
        }

        //<LOOP処理> 投信定時定額買付積立登録内容の件数分LOOP
        for (int i = 0; i < l_intConditionLisCnt; i++)
        {
            //更新前の定時定額買付条件行の取得
            //merge定時定額買付条件変更の戻り値から該当銘柄の定時定額買付条件行を取得する。
            //1) merge定時定額買付条件変更の戻り値の件数分、ループ
            //  1)-1) merge定時定額買付条件変更の戻り値.銘柄コード == 投信定時定額買付積立登録内容.銘柄コードが一致した場合
            //    1)-1)-2) merge定時定額買付条件変更の戻り値[index]を取得
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit = null;
            int l_intMfBuyConditionUnits = 0;
            if (l_mfBuyConditionUnits != null)
            {
                l_intMfBuyConditionUnits = l_mfBuyConditionUnits.length;
            }
            for (int j = 0; j < l_intMfBuyConditionUnits; j++)
            {

                if (WEB3Toolkit.isEquals(
                    l_request.conditionList[i].mutualProductCode,
                    l_mfBuyConditionUnits[j].mutualProductCode))
                {
                    l_mutualFixedBuyConditionUnit = l_mfBuyConditionUnits[j];
                }
            }    
            //update定時定額買付条件変更
            //[update定時定額買付条件変更の引数]
            //補助口座：get補助口座の戻り値
            //扱者：get代理入力者の戻り値
            //定時定額買付共通情報：投信定時定額買付積立登録内容[index]
            //投信定時定額買付条件行：前処理で取得した定時定額買付条件行[index]
            //発注日：取得した発注日
            //適用開始年月：calc適用開始年月（現在日時）の戻り値
            //賞与締切日時：calc賞与締切日時の戻り値
            //通常締切日時(WEB3)：calc通常締切日時(WEB3)の戻り
            this.updateMutualFixedBuyConditionChange(
                l_subAccount,
                l_trader,
                l_request.conditionList[i],
                l_mutualFixedBuyConditionUnit,
                l_datOrderBizDate,
                l_datCurrent,
                new Timestamp(l_datCalcPrizeAndCloseDateHour.getTime()),
                new Timestamp(l_datCalcUsuallyCloseDateTime.getTime()));
        }

        // <LOOP処理> 投信定時定額買付新規追加内容の件数分LOOP
        for (int i = 0; i < l_intAddConditionListCnt; i++)
        {
            //update定時定額買付条件変更
            //[update定時定額買付条件変更の引数]
            //補助口座：get補助口座の戻り値
            //扱者：get代理入力者の戻り値
            //定時定額買付共通情報：投信定時定額買付新規追加内容[index]
            //投信定時定額買付条件行：null
            //発注日：取得した発注日
            //適用開始年月：calc適用開始年月（現在日時）の戻り値
            //賞与締切日時：calc賞与締切日時の戻り値
            //通常締切日時(WEB3)：calc通常締切日時(WEB3)の戻り値
            this.updateMutualFixedBuyConditionChange(
                l_subAccount,
                l_trader,
                l_request.addConditionList[i],
                null,
                l_datOrderBizDate,
                l_datCurrent,
                new Timestamp(l_datCalcPrizeAndCloseDateHour.getTime()),
                new Timestamp(l_datCalcUsuallyCloseDateTime.getTime()));
        }

        //createレスポンス
        //（createResponseの実装）
        //定時定額買付銘柄条件登録完了レスポンスオブジェクトを生成して返す
        WEB3MutualFixedBuyConditionCompleteResponse l_response =
            (WEB3MutualFixedBuyConditionCompleteResponse)l_request.createResponse();

        //レスポンスセット
        //投信定時定額買付積立登録内容  = sort定時定額買付条件一覧の戻り値
        //投信定時定額買付新規追加内容　@=　@新規追加投信定時定額買付条件行
        //投信定時定額引落口座　@=　@null
        //投信定時定額買付金額合計　@= null
        //投信銘柄カテゴリー一覧  =　@null
        //締切日アラート要求フラグ = 締切日アラート要求フラグの判断でセットした値
        l_response.conditionList = l_sortFixedBuyConditionList;
        l_response.addConditionList = l_mutualFixedBuyConditionUnits;
        l_response.acountInfo = null;
        l_response.totalList = null;
        l_response.categoryList = null;
        l_response.closingDateAlertFlag = l_blnClosingAlertRequestFlags;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (merge定時定額買付条件変更（当月反映）)<BR>
     * 定時定額買付条件リストの内容と定時定額買付条件変更リストの内容をマージし、<BR>
     * 投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * 1) 定時定額買付共通サービスを取得する。<BR>
     * <BR>
     * 2) 取得した定時定額買付共通サービス.calc適用開始年月（現在日時）をコールする<BR>
     * 　@　@[calc適用開始年月（現在日時）の引数]<BR>
     * 　@　@　@証券会社コード:引数.証券会社.getInstitutionCode()の戻り値<BR>
     * 　@　@　@部店コード    :引数.部店コード<BR>
     * <BR>
     * 3) 引数.定時定額買付条件リストの件数分ループし、<BR>
     * 　@　@投信定時定額買付条件行のリストを作成する。<BR>
     * <BR>
     * 　@3)-1) 投信定時定額買付条件行オブジェクトを生成する。<BR>
     * <BR>
     * 　@3)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。<BR>
     * 　@　@［get投信銘柄の引数］<BR>
     * 　@　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@　@銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード<BR>
     * <BR>
     * 　@3)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@******************************************************************<BR>
     * 　@　@　@　@**** 定時定額買付条件テーブルの内容を投信定時定額買付条件行オブジェクトの<BR>
     * 　@　@　@　@**** 　@プロパティへセットする。<BR>
     * 　@　@　@　@******************************************************************<BR>
     * 　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード<BR>
     * 　@　@　@　@銘柄名：取得した拡張投信銘柄.get銘柄名<BR>
     * 　@　@　@　@投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード<BR>
     * 　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（月々）<BR>
     * 　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（積み増し）<BR>
     * 　@　@　@　@銘柄表示順序：取得した投信銘柄マスタRow.get表示順位<BR>
     * 　@　@　@　@適用開始年月：<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月>=<BR>
     * 　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月をセット<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月＜<BR>
     * 　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値をセット<BR>
     * 　@　@　@　@更新日時：null<BR>
     * 　@　@　@　@口座引落年月：<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月>=<BR>
     * 　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月をセット<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月＜<BR>
     * 　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値をセット<BR>
     * 　@　@　@　@確定引落金額（積み増し）：null<BR>
     * 　@　@　@　@目論見書閲覧チェック：null<BR>
     * 　@　@　@　@一時停止中フラグ：false<BR>
     * 　@　@　@　@sonar送信チェック： "1：sonar送信可能性あり"<BR> 
     * <BR>
     * 　@3)-4) 投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。<BR>
     * <BR>
     * 4) 引数.定時定額買付条件変更リストでループする<BR>
     * <BR>
     * 　@4)-1) 投信定時定額買付条件行のリストでループする。<BR>
     * <BR>
     * 　@　@　@4)-1)-1) 以下の条件で比較し、一致する場合<BR>
     * 　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード  ==<BR>
     * 　@　@　@　@　@　@　@　@　@投信定時定額買付条件行．銘柄コード<BR>
     * <BR>
     * 　@　@　@　@　@4)-1)-1)-1) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@「1：追加」または「2：変更」または「4：一時停止」の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@4)-1)-1)-1)-1) 投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブルの内容で投信定時定額<BR>
     * 　@　@　@　@　@　@　@　@　@**** 　@買付条件行の内容を上書きする。<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@　@　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@適用開始年月：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月>=<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月＜<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値をセット。<BR>
     * 　@　@　@　@　@　@　@　@　@口座引落年月：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月>=<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット。<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月＜<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値をセット。<BR>
     * 　@　@　@　@　@　@　@　@　@一時停止中フラグ：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@「4：一時停止」の場合、true<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@「4：一時停止」以外の場合、false<BR>
     * <BR>
     * 　@　@　@　@　@4)-1)-1)-2) 上記以外の場合<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブルの内容を元に投信定時定額<BR>
     * 　@　@　@　@　@　@　@　@　@**** 　@買付条件行のリストから削除する。<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブル.変更区分が「3：解除」の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@**** 　@投信定時定額買付条件行のリストから削除する。<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * <BR>
     * 　@　@　@　@　@　@　@4)-1)-1)-2)-1) 投信定時定額買付条件行リストから該当する行オブジェクトを削除する。<BR>
     * <BR>
     * 　@　@　@　@　@4)-1)-1)-3) 引数.定時定額買付条件変更リストのループへ戻る。<BR>
     * <BR>
     * 　@***********************************************************************<BR>
     * 　@**** 定時定額買付条件変更リストに存在し、<BR>
     * 　@**** 　@投信定時定額買付条件行のリストに存在しない場合は、この箇所に処理が来る。<BR>
     * 　@***********************************************************************<BR>
     * 　@4)-2)引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@「1：追加」または「2：変更」または「4：一時停止」の場合<BR>
     * <BR>
     * 　@　@　@4)-2)-1) 投信定時定額買付条件行オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@4)-2)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。<BR>
     * 　@　@　@　@［get投信銘柄の引数］<BR>
     * 　@　@　@　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@　@　@　@銘柄コード：<BR>
     * 　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード<BR>
     * <BR>
     * 　@　@　@4)-2)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@******************************************************************<BR>
     * 　@　@　@　@**** 定時定額買付条件変更テーブルのみに存在する場合、<BR>
     * 　@　@　@　@**** 定時定額買付条件変更テーブルの内容を投信定時定額買付条件行の<BR>
     * 　@　@　@　@**** 　@プロパティへセットする。<BR>
     * 　@　@　@　@******************************************************************<BR>
     * 　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@銘柄コード：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード<BR>
     * 　@　@　@　@銘柄名：取得した拡張投信銘柄.get銘柄名<BR>
     * 　@　@　@　@投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード<BR>
     * 　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@銘柄表示順序：取得した投信銘柄マスタRow.get表示順位<BR>
     * 　@　@　@　@適用開始年月：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月>=<BR>
     * 　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月＜<BR>
     * 　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値をセット<BR>
     * 　@　@　@　@更新日時：null<BR>
     * 　@　@　@　@口座引落年月：<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月>=<BR>
     * 　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月＜<BR>
     * 　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@calc適用開始年月（現在日時）の戻り値をセット<BR>
     * 　@　@　@　@確定引落金額（積み増し）：null<BR>
     * 　@　@　@　@目論見書閲覧チェック：null<BR>
     * 　@　@　@　@一時停止中フラグ：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@「4：一時停止」の場合、true<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@「4：一時停止」以外の場合、false<BR>
     * 　@　@　@　@sonar送信チェック： "0：sonar送信可能性なし"<BR> 
     * <BR>
     * 　@　@　@4)-2)-4)  投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。<BR>
     * <BR>
     * 5)　@投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * 6) 投信定時定額買付条件行の配列をリターンする。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_lisFixedBuyConditionLists - (定時定額買付条件リスト)<BR>
     * 定時定額買付条件リスト<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (定時定額買付条件変更リスト)<BR>
     * 定時定額買付条件変更リスト<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 48525B4F0079
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChangeMonth(
        Institution l_institution,
        String l_strBranchCode,
        List l_lisFixedBuyConditionLists,
        List l_lisFixedBuyConditionChangeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChangeMonth("
            + "Institution, String, List, List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionLists == null
            || l_lisFixedBuyConditionChangeLists == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //1) 定時定額買付共通サービスを取得する。
        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        //2) 取得した定時定額買付共通サービス.calc適用開始年月（現在日時）をコールする
        //[calc適用開始年月（現在日時）の引数]
        //証券会社コード:引数.証券会社.getInstitutionCode()の戻り値
        //部店コード    :引数.部店コード
        Date l_datCurrentDate =
            l_mutualFixedBuyCommonService.calcValidStartDateCurrentDate(
                l_institution.getInstitutionCode(),
                l_strBranchCode);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //拡張投信銘柄マネージャを取得する。
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //3) 引数.定時定額買付条件リストの件数分ループし、投信定時定額買付条件行のリストを作成する。
        Iterator l_iteratorFixedBuyConditionLists =
            l_lisFixedBuyConditionLists.iterator();
        List l_lisUnitLists = new ArrayList();
        MutualFundProduct l_mutualFundProduct;
        while (l_iteratorFixedBuyConditionLists.hasNext())
        {
            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorFixedBuyConditionLists.next();

            //3)-1) 投信定時定額買付条件行オブジェクトを生成する。
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();

            //3)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。
            //［get投信銘柄の引数］
            //証券会社：引数.証券会社
            //銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_mfFixedBuyingCondRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //3)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。
            //定時定額買付条件テーブルの内容を投信定時定額買付条件行オブジェクトのプロパティへセットする。
            //[セットする内容]
            //銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード
            l_mutualFixedBuyConditionUnit.mutualProductCode =
                l_mfFixedBuyingCondRow.getProductCode();
            //銘柄名：取得した拡張投信銘柄.get銘柄名
            l_mutualFixedBuyConditionUnit.mutualProductName =
                l_mutualFundProductRow.getStandardName();
            //投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード
            l_mutualFixedBuyConditionUnit.categoryCode =
                l_mutualFundProductRow.getCategoryCode();
            //買付金額（月々）：引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（月々）
            if (l_mfFixedBuyingCondRow.getMonthlyBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getMonthlyBuyAmount());
            }
            //買付金額（積み増し）：引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（積み増し）
            if (l_mfFixedBuyingCondRow.getIncreaseBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getIncreaseBuyAmount());
            }
            //銘柄表示順序：取得した投信銘柄マスタRow.get表示順位
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnit.displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.displayOrder =
                    WEB3StringTypeUtility.formatNumber(
                        l_mutualFundProductRow.getIndicationRanking());
            }

            //引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月>=
            //calc適用開始年月（現在日時）の戻り値の場合、
            int l_intCompareToMonth = WEB3DateUtility.compareToMonth(
                l_datCurrentDate,
                l_mfFixedBuyingCondRow.getDrawDate());
            if (l_intCompareToMonth <= 0)
            {
                //適用開始年月：
                //引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月をセット
                l_mutualFixedBuyConditionUnit.validStartDate =
                    l_mfFixedBuyingCondRow.getDrawDate();

                //口座引落年月：
                //引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月をセット
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_mfFixedBuyingCondRow.getDrawDate().getTime());
            }
            //引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月＜
            //calc適用開始年月（現在日時）の戻り値の場合、
            else
            {
                //適用開始年月：、
                //calc適用開始年月（現在日時）の戻り値をセット
                l_mutualFixedBuyConditionUnit.validStartDate = l_datCurrentDate;

                //口座引落年月：
                //calc適用開始年月（現在日時）の戻り値をセット
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_datCurrentDate.getTime());
            }
            //更新日時：null
            l_mutualFixedBuyConditionUnit.updateDate = null;
            //確定引落金額（積み増し）：null
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
            //目論見書閲覧チェック：null
            l_mutualFixedBuyConditionUnit.checkResult = null;
            //一時停止中フラグ：false
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            //sonar送信チェック： "1：sonar送信可能性あり"
            l_mutualFixedBuyConditionUnit.sonarSendCheck = 
                    WEB3MFSonarSendCheckDivDef.SEND_POSSIBILITY;

            //3)-4) 投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。
            l_lisUnitLists.add(l_mutualFixedBuyConditionUnit);
        }

        //4) 引数.定時定額買付条件変更リストでループする
        Iterator l_iteratorChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        while (l_iteratorChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorChangeLists.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //4)-1) 投信定時定額買付条件行のリストでループする。
            Iterator l_iteratorUnitLists = l_lisUnitLists.iterator();
            boolean l_blnFlag = true;
            while (l_iteratorUnitLists.hasNext())
            {
                WEB3MutualFixedBuyConditionUnit l_unit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorUnitLists.next();

                //4)-1)-1) 以下の条件で比較し、一致する場合
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード  ==
                //投信定時定額買付条件行．銘柄コード
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_unit.mutualProductCode))
                {
                    //4)-1)-1)-1) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                    //「1：追加」または
                    //「2：変更」または
                    //「4：一時停止」の場合
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        //4)-1)-1)-1)-1) 投信定時定額買付条件行オブジェクトのプロパティセット。
                        //定時定額買付条件変更テーブルの内容で投信定時定額買付条件行の内容を上書きする。
                        //買付金額（月々）：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_unit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_unit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }
                        //買付金額（積み増し）：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_unit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_unit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }

                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月>=
                        //calc適用開始年月（現在日時）の戻り値の場合、
                        int l_intCompareToMonth = WEB3DateUtility.compareToMonth(
                            l_datCurrentDate,
                            l_mfFixedBuyingChangeRow.getValidStartDate());
                        if (l_intCompareToMonth <= 0)
                        {
                            //適用開始年月：
                            //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット。
                            l_unit.validStartDate =
                                l_mfFixedBuyingChangeRow.getValidStartDate();

                            //口座引落年月：
                            //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット。
                            l_unit.debitAccountYM =
                                new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        }
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月＜
                        //calc適用開始年月（現在日時）の戻り値の場合
                        else
                        {
                            //適用開始年月：
                            //calc適用開始年月（現在日時）の戻り値をセット。
                            l_unit.validStartDate = l_datCurrentDate;

                            //口座引落年月：
                            //calc適用開始年月（現在日時）の戻り値をセット。
                            l_unit.debitAccountYM =
                                new Timestamp(l_datCurrentDate.getTime());
                        }
                        //一時停止中フラグ：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                        //「4：一時停止」の場合、true
                        if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                        {
                            l_unit.suspensionFlag = true;
                        }
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                        //「4：一時停止」以外の場合、false
                        else
                        {
                            l_unit.suspensionFlag = false;
                        }
                    }
                    //4)-1)-1)-2) 上記以外の場合
                    //定時定額買付条件変更テーブルの内容を元に投信定時定額買付条件行のリストから削除する。
                    //定時定額買付条件変更テーブル.変更区分が「3：解除」の場合、
                    //投信定時定額買付条件行のリストから削除する。
                    else
                    {
                        //4)-1)-1)-2)-1) 投信定時定額買付条件行リストから該当する行オブジェクトを削除する。
                        l_iteratorUnitLists.remove();
                    }

                    l_blnFlag = false;
                    //4)-1)-1)-3) 引数.定時定額買付条件変更リストのループへ戻る。
                    break;
                }
            }

            if (!l_blnFlag)
            {
                continue;
            }

            //定時定額買付条件変更リストに存在し、投信定時定額買付条件行のリストに存在しない場合は、
            //この箇所に処理が来る。
            //4)-2) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
            //「1：追加」または「2：変更」または「4：一時停止」の場合
            if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
            {
                //4)-2)-1) 投信定時定額買付条件行オブジェクトを生成する。
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //4)-2)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。
                //［get投信銘柄の引数］
                //証券会社：引数.証券会社
                //銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード
                try
                {
                    l_mutualFundProduct =
                        l_mutualFundProductManager.getMutualFundProduct(
                            l_institution,
                            l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //4)-2)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。
                //定時定額買付条件変更テーブルのみに存在する場合、
                //定時定額買付条件変更テーブルの内容を投信定時定額買付条件行のプロパティへセットする。
                if (l_mfFixedBuyingChangeRow != null)
                {
                    //[セットする内容]
                    //銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード
                    l_mutualFixedBuyConditionUnit.mutualProductCode =
                        l_mfFixedBuyingChangeRow.getProductCode();
                    //銘柄名：取得した拡張投信銘柄.get銘柄名
                    l_mutualFixedBuyConditionUnit.mutualProductName =
                        l_mutualFundProductRow.getStandardName();
                    //投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード
                    l_mutualFixedBuyConditionUnit.categoryCode =
                        l_mutualFundProductRow.getCategoryCode();
                    //買付金額（月々）：
                    //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）
                    if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                            WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                    }
                    //買付金額（積み増し）：
                    //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）
                    if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                            WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                    }
                    //銘柄表示順序：取得した投信銘柄マスタRow.get表示順位
                    if (l_mutualFundProductRow.getIndicationRankingIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundProductRow.getIndicationRanking());
                    }

                    //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月>=
                    //calc適用開始年月（現在日時）の戻り値の場合、
                    int l_intCompareToMonth = WEB3DateUtility.compareToMonth(
                        l_datCurrentDate,
                        l_mfFixedBuyingChangeRow.getValidStartDate());
                    if (l_intCompareToMonth <= 0)
                    {
                        //適用開始年月：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット
                        l_mutualFixedBuyConditionUnit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();

                        //口座引落年月：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月をセット
                        l_mutualFixedBuyConditionUnit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                    }
                    //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月＜
                    //calc適用開始年月（現在日時）の戻り値の場合、
                    else
                    {
                        //適用開始年月：
                        //calc適用開始年月（現在日時）の戻り値をセット
                        l_mutualFixedBuyConditionUnit.validStartDate =
                            l_datCurrentDate;

                        //口座引落年月：
                        //calc適用開始年月（現在日時）の戻り値をセット
                        l_mutualFixedBuyConditionUnit.debitAccountYM =
                            new Timestamp(l_datCurrentDate.getTime());
                    }
                    //更新日時：null
                    l_mutualFixedBuyConditionUnit.updateDate = null;
                    //確定引落金額（積み増し）：null
                    l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                    //目論見書閲覧チェック：null
                    l_mutualFixedBuyConditionUnit.checkResult = null;
                    //一時停止中フラグ：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                    //「4：一時停止」の場合、true
                    if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                    }
                    //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                    //「4：一時停止」以外の場合、false
                    else
                    {
                        l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                    }
                    //sonar送信チェック： "0：sonar送信可能性なし" 
                    l_mutualFixedBuyConditionUnit.sonarSendCheck = 
                            WEB3MFSonarSendCheckDivDef.SEND_NO_POSSIBILITY;
                }

                //4)-2)-4)  投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。
                l_lisUnitLists.add(l_mutualFixedBuyConditionUnit);
            }
        }

        //5)　@投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisUnitLists.size()];
        l_lisUnitLists.toArray(l_mutualFixedBuyConditionUnits);

        //6) 投信定時定額買付条件行の配列をリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (merge定時定額買付条件変更（未來月反映）)<BR>
     * merge定時定額買付条件行リストの内容と定時定額買付条件変更リストの内容をマージし、<BR>
     * 投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * 1) 引数.定時定額買付条件変更リストの件数分ループする。<BR>
     * <BR>
     * 　@1)-1) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。<BR>
     * 　@　@［get投信銘柄の引数］<BR>
     * 　@　@　@証券会社：引数．証券会社<BR>
     * 　@　@　@銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード<BR>
     * <BR>
     * 　@1)-2) 引数.merge定時定額買付条件行リストでループする<BR>
     * <BR>
     * 　@　@　@1)-2)-1) 以下の条件で比較する。<BR>
     * 　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード ==<BR>
     * 　@　@　@　@　@　@　@　@　@引数．merge定時定額買付条件行リスト．銘柄コード<BR>
     * <BR>
     * 　@　@　@　@　@1)-2)-1)-1) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@「1：追加」または「2：変更」または「4：一時停止」の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@1)-2)-1)-1)-1) 投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブルの内容で<BR>
     * 　@　@　@　@　@　@　@　@　@**** 　@投信定時定額買付条件行の内容を上書きする。<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@　@　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@適用開始年月：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@　@　@　@口座引落年月：<BR>
     *  　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@　@　@　@一時停止中フラグ：<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@「4：一時停止」の場合、true<BR>
     * 　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@「4：一時停止」以外の場合、false<BR>
     * <BR>
     * 　@　@　@　@　@1)-2)-1)-2) 上記以外の場合<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブルの内容を元に投信定時定額<BR>
     * 　@　@　@　@　@　@　@　@　@**** 　@買付条件行のリストから削除する。<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブル.変更区分が「3：解除」の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@**** 　@投信定時定額買付条件行のリストから削除する。<BR>
     * 　@　@　@　@　@　@　@　@　@********************************************************<BR>
     * <BR>
     * 　@　@　@　@　@　@　@1)-2)-1)-2)-1) 投信定時定額買付条件行のリストから該当する行オブジェクトを削除する。<BR>
     * <BR>
     *  　@　@　@　@　@1)-2)-1)-3) 定時定額買付条件変更リストのループへ戻る。<BR>
     * <BR>
     * 　@***********************************************************************<BR>
     * 　@**** 定時定額買付条件変更リストに存在し、<BR>
     * 　@**** 　@merge定時定額買付条件行リストに存在しない場合は、この箇所に処理が来る。<BR>
     * 　@***********************************************************************<BR>
     * 　@1)-3) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@「1：追加」または「2：変更」または「4：一時停止」の場合 <BR>
     * <BR>
     * 　@　@　@1)-3)-1) 投信定時定額買付条件行オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@1)-3)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。<BR>
     * 　@　@　@　@［get投信銘柄の引数］<BR>
     * 　@　@　@　@　@　@証券会社：引数．証券会社<BR>
     * 　@　@　@　@　@　@銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード<BR>
     * <BR>
     * 　@　@　@1)-3)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@******************************************************************<BR>
     * 　@　@　@　@**** 定時定額買付条件変更テーブルのみに存在する場合、<BR>
     * 　@　@　@　@**** 定時定額買付条件変更テーブルの内容を投信定時定額買付条件行の<BR>
     * 　@　@　@　@**** 　@プロパティへセットする。<BR>
     * 　@　@　@　@******************************************************************<BR>
     * 　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@銘柄コード：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード<BR>
     * 　@　@　@　@銘柄名：取得した拡張投信銘柄.get銘柄名<BR>
     * 　@　@　@　@投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード<BR>
     * 　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@銘柄表示順序：取得した投信銘柄マスタRow.get表示順位<BR>
     * 　@　@　@　@適用開始年月：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@更新日時：null<BR>
     * 　@　@　@　@口座引落年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@確定引落金額（積み増し）：null<BR>
     * 　@　@　@　@目論見書閲覧チェック：null<BR>
     * 　@　@　@　@一時停止中フラグ：<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@「4：一時停止」の場合、true<BR>
     * 　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@「4：一時停止」以外の場合、false<BR>
     * 　@　@　@　@sonar送信チェック： "0：sonar送信可能性なし"<BR>
     * <BR>
     * 　@　@　@1)-3)-4) 投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。<BR>
     * <BR>
     * 2)　@投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * 3) 投信定時定額買付条件行の配列をリターンする。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_mergeMutualFixedBuyConditionUnitLists - (merge定時定額買付条件行リスト)<BR>
     * merge定時定額買付条件行リスト<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (定時定額買付条件変更リスト)<BR>
     * 定時定額買付条件変更リスト<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4855C52A02F7
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChangeFutureMonth(
        Institution l_institution,
        String l_strBranchCode,
        WEB3MutualFixedBuyConditionUnit[] l_mergeMutualFixedBuyConditionUnitLists,
        List l_lisFixedBuyConditionChangeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChangeFutureMonth("
            + "Institution, String, WEB3MutualFixedBuyConditionUnit[], List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionChangeLists == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //拡張投信銘柄マネージャを取得する。
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        int l_intLength = 0;
        if (l_mergeMutualFixedBuyConditionUnitLists != null)
        {
            l_intLength = l_mergeMutualFixedBuyConditionUnitLists.length;
        }

        List l_lisUnitLists = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            l_lisUnitLists.add(l_mergeMutualFixedBuyConditionUnitLists[i]);
        }
        //1) 引数.定時定額買付条件変更リストの件数分ループする。
        Iterator l_iteratorChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        MutualFundProduct l_mutualFundProduct;
        while (l_iteratorChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorChangeLists.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //1)-1) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。
            //［get投信銘柄の引数］
            //証券会社：引数．証券会社
            //銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード
            try
            {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_mfFixedBuyingChangeRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            boolean l_blnFlag = true;
            //1)-2) 引数.merge定時定額買付条件行リストでループする
            Iterator l_iteratorMutualFixedBuyConditionUnits =
                l_lisUnitLists.iterator();
            while (l_iteratorMutualFixedBuyConditionUnits.hasNext())
            {
                //1)-2)-1) 以下の条件で比較する。
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード   ==
                //引数．merge定時定額買付条件行リスト．銘柄コード
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorMutualFixedBuyConditionUnits.next();
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_mutualFixedBuyConditionUnit.mutualProductCode))
                {
                    //1)-2)-1)-1) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                    //「1：追加」または「2：変更」または「4：一時停止」の場合
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        //1)-2)-1)-1)-1) 投信定時定額買付条件行オブジェクトのプロパティセット。
                        //定時定額買付条件変更テーブルの内容で投信定時定額買付条件行の内容を上書きする。
                        //買付金額（月々）：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }
                        //買付金額（積み増し）：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }
                        //適用開始年月：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                        l_mutualFixedBuyConditionUnit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();
                        //口座引落年月：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                        l_mutualFixedBuyConditionUnit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        //一時停止中フラグ：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                        //「4：一時停止」の場合、true
                        if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                        {
                            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                        }
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                        //「4：一時停止」以外の場合、false
                        else
                        {
                            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                        }
                    }

                    //1)-2)-1)-2) 上記以外の場合
                    //定時定額買付条件変更テーブルの内容を元に投信定時定額買付条件行のリストから削除する。
                    //定時定額買付条件変更テーブル.変更区分が「3：解除」の場合、
                    //投信定時定額買付条件行のリストから削除する。
                    //1)-2)-1)-2)-1) 投信定時定額買付条件行のリストから該当する行オブジェクトを削除する。
                    else
                    {
                        l_iteratorMutualFixedBuyConditionUnits.remove();
                    }
                    l_blnFlag = false;
                    //1)-2)-1)-3) 定時定額買付条件変更リストのループへ戻る。
                    break;
                }
            }
            //定時定額買付条件変更リストに存在し、merge定時定額買付条件行リストに存在しない場合は、
            //この箇所に処理が来る。

            if (!l_blnFlag)
            {
                continue;
            }

            //1)-3) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
            //「1：追加」または「2：変更」または「4：一時停止」の場合
            if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
            {
                //1)-3)-1) 投信定時定額買付条件行オブジェクトを生成する。
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //1)-3)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。
                //［get投信銘柄の引数］
                //証券会社：引数．証券会社
                //銘柄コード：
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード
                try
                {
                    l_mutualFundProduct =
                        l_mutualFundProductManager.getMutualFundProduct(
                            l_institution,
                            l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //1)-3)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。
                //定時定額買付条件変更テーブルのみに存在する場合、
                //定時定額買付条件変更テーブルの内容を投信定時定額買付条件行のプロパティへセットする。
                //[セットする内容]
                //銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード
                l_mutualFixedBuyConditionUnit.mutualProductCode =
                    l_mfFixedBuyingChangeRow.getProductCode();
                //銘柄名：取得した拡張投信銘柄.get銘柄名
                l_mutualFixedBuyConditionUnit.mutualProductName =
                    l_mutualFundProductRow.getStandardName();
                //投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード
                l_mutualFixedBuyConditionUnit.categoryCode =
                    l_mutualFundProductRow.getCategoryCode();
                //買付金額（月々）：
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）
                if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                }
                //買付金額（積み増し）：
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）
                if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                }
                //銘柄表示順序：取得した投信銘柄マスタRow.get表示順位
                if (l_mutualFundProductRow.getIndicationRankingIsNull())
                {
                    l_mutualFixedBuyConditionUnit.displayOrder = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.displayOrder =
                        WEB3StringTypeUtility.formatNumber(
                            l_mutualFundProductRow.getIndicationRanking());
                }
                //適用開始年月：
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                l_mutualFixedBuyConditionUnit.validStartDate =
                    l_mfFixedBuyingChangeRow.getValidStartDate();
                //更新日時：null
                l_mutualFixedBuyConditionUnit.updateDate = null;
                //口座引落年月：
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                //確定引落金額（積み増し）：null
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                //目論見書閲覧チェック：null
                l_mutualFixedBuyConditionUnit.checkResult = null;
                //一時停止中フラグ：
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                //「4：一時停止」の場合、true
                if (WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                {
                    l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                }
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                //「4：一時停止」以外の場合、false
                else
                {
                    l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                }
                //sonar送信チェック： "0：sonar送信可能性なし"
                l_mutualFixedBuyConditionUnit.sonarSendCheck = 
                        WEB3MFSonarSendCheckDivDef.SEND_NO_POSSIBILITY;

                //1)-3)-4)  投信定時定額買付条件行オブジェクトを
                //投信定時定額買付条件行のリストへ追加する。
                l_lisUnitLists.add(l_mutualFixedBuyConditionUnit);
            }
        }

        //2)　@投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisUnitLists.size()];
        l_lisUnitLists.toArray(l_mutualFixedBuyConditionUnits);

        //3) 投信定時定額買付条件行の配列をリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (get最新履歴番号)<BR>
     * 定時定額買付条件変更履歴テーブルの履歴番号を採番する。<BR>
     * <BR>
     * １）定時定額買付条件変更履歴テーブルを検索し、<BR>
     * 　@　@　@定時定額買付条件変更履歴Rowのリストを取得。<BR>
     * 　@[検索条件]<BR>
     * 　@　@証券会社コード：引数．証券会社コード<BR>
     * 　@　@部店コード：引数．部店コード<BR>
     * 　@　@口座コード：引数．口座コード<BR>
     * 　@　@銘柄コード：引数．銘柄コード<BR>
     * 　@　@適用開始年月：引数．適用開始年月<BR>
     * 　@[ソート条件]<BR>
     * 　@order by 履歴番号 desc<BR>
     * <BR>
     * ２）1レコード目の定時定額買付条件変更履歴Row．履歴番号 + 1をリターンする。<BR>
     * <BR>
     * ※レコードが存在しない場合、1をリターンする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_datValidStartDate - (適用開始年月)<BR>
     * 適用開始年月<BR>
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 485A4685007C
     */
    protected long getLastNumber(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strProductCode,
        Date l_datValidStartDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getLastNumber(String, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);

        //１）定時定額買付条件変更履歴テーブルを検索し、
        //  定時定額買付条件変更履歴Rowのリストを取得。
        List l_lisRows = null;

        //[検索条件]
        //　@証券会社コード：引数．証券会社コード
        //　@部店コード：引数．部店コード
        //　@口座コード：引数．口座コード
        //　@銘柄コード：引数．銘柄コード
        //　@適用開始年月：引数．適用開始年月
        String l_strWhere =
            " institution_code = ? and "
            + " branch_code = ? and "
            + " account_code = ? and "
            + " product_code = ? and "
            + " to_char(valid_start_date, 'yyyyMM') = ? ";

        //[ソート条件]
        //order by 履歴番号 desc
        String l_strOrderBy = " serial_no desc ";

        Object[] l_bindVars =
            {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strProductCode,
                WEB3DateUtility.formatDate(
                    l_datValidStartDate,
                    WEB3GentradeTimeDef.DATE_FORMAT_YM)
            };
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRows =
                l_queryProcessor.doFindAllQuery(
                    MfFixedBuyingChangeHistRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //レコードが存在しない場合、1をリターンする。
        if (l_lisRows == null || l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return 1L;
        }
        else
        {
            //1レコード目の定時定額買付条件変更履歴Row．履歴番号 + 1をリターンする。
            MfFixedBuyingChangeHistRow l_mfFixedBuyingChangeHistRow =
                (MfFixedBuyingChangeHistRow)l_lisRows.get(0);
            long l_lngSerialNo = l_mfFixedBuyingChangeHistRow.getSerialNo() + 1;

            log.exiting(STR_METHOD_NAME);
            return l_lngSerialNo;
        }
    }

    /**
     * (merge定時定額買付条件変更)<BR>
     * 定時定額買付条件テーブルの内容と定時定額買付条件変更テーブルの内容をマージして<BR>
     * 投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * ================================================<BR>
     * ○定時定額買付条件テーブルのレコードを取得する。<BR>
     * 　@　@（条件変更可能なレコードを取得する。）<BR>
     * ================================================　@<BR>
     * 　@1) 検索条件文字列として、以下の文字列を作成する。<BR>
     * 　@　@　@"口座引落年月 >= ? " <BR>
     * <BR>
     * 　@2) 検索条件値として、以下のObject配列を作成する。<BR>
     * 　@　@　@・引数.適用開始年月<BR>
     * <BR>
     * 　@3) 定時定額買付共通サービス.get定時定額買付条件リストをコールする。<BR>
     * 　@　@　@[get定時定額買付条件リストの引数]<BR>
     * 　@　@　@　@証券会社コード：<BR>
     * 　@　@　@　@　@引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@　@　@部店コード：<BR>
     * 　@　@　@　@　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値<BR>
     * 　@　@　@　@口座コード：引数.補助口座.getMainAccount().getAccountCode()の戻り値<BR>
     * 　@　@　@　@検索条件文字列：作成した検索条件文字列<BR>
     * 　@　@　@　@検索条件値：作成した検索条件値<BR>
     * <BR>
     * ================================================<BR>
     * ○定時定額買付条件変更テーブルのレコードを取得する。<BR>
     * 　@　@（SONAR反映前のレコードと反映済みの一時停止レコードを取得する。）<BR>
     * ================================================<BR>
     * 　@4) 検索条件文字列として、以下の文字列を作成する。 <BR>
     * 　@　@　@　@"( 発注区分 in (?,?) or<BR>
     * 　@　@　@　@変更区分 =　@? ) and<BR>
     * 　@　@　@　@適用開始年月 = ? " <BR>
     * <BR>
     * 　@5) 検索条件値として、以下のObject配列を作成する。<BR>
     * 　@　@　@・1：sonar未送信<BR>
     * 　@　@　@・２：sonar送信済<BR>
     * 　@　@　@・４：一時停止<BR>
     * 　@　@　@・引数.適用開始年月<BR>
     * <BR>
     * 　@6) 定時定額買付共通サービス.get定時定額買付条件変更リストをコールする。<BR>
     * 　@　@　@[get定時定額買付条件変更リストの引数]<BR>
     * 　@　@　@　@証券会社コード：<BR>
     * 　@　@　@　@　@引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@　@　@部店コード：<BR>　@　@
     * 　@　@　@　@　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値<BR>　@　@
     * 　@　@　@　@口座コード：引数.補助口座.getMainAccount().getAccountCode()の戻り値<BR>
     * 　@　@　@　@検索条件文字列：作成した検索条件文字列<BR>
     * 　@　@　@　@検索条件値：作成した検索条件値<BR>
     * <BR>
     * ================================================<BR>
     * ○定時定額買付条件テーブルのレコードの内容と<BR>
     * 　@　@定時定額買付条件変更テーブルのレコードの内容をマージし、<BR>
     * 　@　@投信定時定額買付条件行の配列を作成する。<BR>
     * ================================================<BR>
     * 　@7) this.merge定時定額買付条件変更(当月反映)をコールする。<BR>
     * 　@　@　@[merge定時定額買付条件変更(当月反映)の引数]<BR>
     * 　@　@　@　@証券会社：引数.補助口座.getInstitution()の戻り値<BR>
     * 　@　@　@　@部店コード：<BR>
     * 　@　@　@　@　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値<BR>
     * 　@　@　@　@定時定額買付条件リスト：get定時定額買付条件リストの戻り値<BR>
     * 　@　@　@　@定時定額買付条件変更リスト：get定時定額買付条件変更リストの戻り値<BR>
     * <BR>
     * ================================================<BR>
     * ○定時定額買付条件変更テーブルのレコードを取得する。<BR>
     * 　@　@（3ヶ月表示用のレコードと未来月顧客の変更レコード）<BR>
     * ================================================<BR>
     * 　@8) 検索条件文字列として、以下の文字列を作成する。  <BR>
     * 　@　@　@"発注区分 in (?,?) and<BR>
     * 　@　@　@ 適用開始年月 > ?"<BR>
     * <BR>
     * 　@9) 検索条件値として、以下のObject配列を作成する。<BR>
     * 　@　@　@・1：sonar未送信<BR>
     * 　@　@　@・２：sonar送信済<BR>
     * 　@　@　@・引数.適用開始年月<BR>
     * <BR>
     * 　@10) 定時定額買付共通サービス.get定時定額買付条件変更リストをコールする。<BR>
     * 　@　@　@[get定時定額買付条件変更リストの引数]<BR>
     * 　@　@　@　@証券会社コード：<BR>
     * 　@　@　@　@　@引数.補助口座.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@　@　@部店コード：<BR>
     * 　@　@　@　@　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値<BR>　@
     * 　@　@　@　@口座コード：引数.補助口座.getMainAccount().getAccountCode()の戻り値<BR>
     * 　@　@　@　@検索条件文字列：作成した検索条件文字列<BR>
     * 　@　@　@　@検索条件値：作成した検索条件値<BR>
     * <BR>
     * 　@11）get定時定額買付条件変更リストの戻り値が０件の場合は、<BR>
     * 　@　@merge定時定額買付条件変更(当月反映)の戻り値をリターンする。<BR>
     * <BR>
     * ================================================<BR>
     * ○作成した投信定時定額買付条件行の配列に<BR>
     * 　@　@3ヶ月表示用のレコードと未来月顧客の変更レコードの内容をマージし、<BR>
     * 　@　@投信定時定額買付条件行の配列を作成して返却する。<BR>
     * ================================================<BR>
     * 　@12）get定時定額買付条件変更リストの戻り値が０件でない場合、以下の処理を行う。　@<BR>
     * <BR>
     * 　@12-1) this.merge定時定額買付条件変更(未来月反映)をコールする。<BR>
     * 　@　@　@[merge定時定額買付条件変更(未来月反映)の引数]<BR>
     * 　@　@　@　@証券会社：引数.補助口座.getInstitution()の戻り値<BR>
     * 　@　@　@　@部店コード：<BR>
     * 　@　@　@　@　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値<BR>
     * 　@　@　@　@merge定時定額買付条件行リスト：<BR>
     * 　@　@　@　@　@merge定時定額買付条件変更（当月反映）の戻り値<BR>
     * 　@　@　@　@定時定額買付条件変更リスト：get定時定額買付条件変更リストの戻り値<BR>
     * <BR>
     * 　@12-2) merge定時定額買付条件変更(未来月反映)の戻り値をリターンする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_datValidStartDate - (適用開始年月)<BR>
     * 適用開始年月<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 485F020E01E7
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionChange(
        SubAccount l_subAccount, Date l_datValidStartDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionChange(SubAccount, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null
            || l_datValidStartDate == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //○定時定額買付条件テーブルのレコードを取得する。
        //（条件変更可能なレコードを取得する。）
        //1) 検索条件文字列として、以下の文字列を作成する。
        //"口座引落年月 >= ? "
        String l_strWhere = " to_char(draw_date, 'yyyyMM') >= ? ";

        //2) 検索条件値として、以下のObject配列を作成する。
        Object[] l_objValues = new Object[]{
            WEB3DateUtility.formatDate(
                l_datValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        //get定時定額買付条件リストの引数]
        //証券会社コード　@　@：　@引数.補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード　@　@　@   ：　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード　@　@　@   ：　@引数.補助口座.getMainAccount().getAccountCode()の戻り値
        //検索条件文字列     ：作成した検索条件文字列
        //検索条件値　@  　@   ：作成した検索条件値
        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        List l_fixedBuyConditionLists = l_mutualFixedBuyCommonService.getFixedBuyConditionList(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_strWhere,
            l_objValues);

        //○定時定額買付条件変更テーブルのレコードを取得する。
        //SONAR反映前のレコードと反映済みの一時停止レコードを取得する。
        //4) 検索条件文字列として、以下の文字列を作成する。
        //"( 発注区分 in (?,?) or 変更区分 =　@? ) and 適用開始年月 = ? "
        l_strWhere = " (status in (?, ?) or change_div = ?) and to_char(valid_start_date, 'yyyyMM') = ? ";

        //5) 検索条件値として、以下のObject配列を作成する。
        //・1：sonar未送信
        //・２：sonar送信済
        //・４：一時停止
        //・引数.適用開始年月
        l_objValues = new Object[]{
            WEB3MFStatusDef.SONAR_NOT_SEND,
            WEB3MFStatusDef.SONAR_SENDED,
            WEB3ChangeDivDef.TEMP_STOP,
            WEB3DateUtility.formatDate(
                l_datValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        //6) 定時定額買付共通サービス.get定時定額買付条件変更リストをコールする。
        //[get定時定額買付条件変更リストの引数]
        //証券会社コード　@　@：　@引数.補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード　@　@　@   ：　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード　@　@　@   ：　@引数.補助口座.getMainAccount().getAccountCode()の戻り値
        //検索条件文字列     ：作成した検索条件文字列
        //検索条件値　@  　@   ：作成した検索条件値
        List l_fixedBuyConditionChangeLists =
            l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_strWhere,
                l_objValues);

        //定時定額買付条件テーブルのレコードの内容と
        //定時定額買付条件変更テーブルのレコードの内容をマージし
        //投信定時定額買付条件行の配列を作成する。
        //7) this.merge定時定額買付条件変更(当月反映)をコールする。
        //[merge定時定額買付条件変更(当月反映)の引数]
        //証券会社　@　@　@　@：引数.補助口座.getInstitution()の戻り値
        //部店コード　@　@　@　@：引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //定時定額買付条件リスト   　@　@：get定時定額買付条件リストの戻り値
        //定時定額買付条件変更リスト   ：get定時定額買付条件変更リストの戻り値
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChangeMonth(
                l_subAccount.getInstitution(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_fixedBuyConditionLists,
                l_fixedBuyConditionChangeLists);

        //○定時定額買付条件変更テーブルのレコードを取得する。
        //（3ヶ月表示用のレコードと未来月顧客の変更レコード）
        //8) 検索条件文字列として、以下の文字列を作成する。
        //"発注区分 in (?,?) and 適用開始年月 > ?"
        l_strWhere = " status in (?, ?) and to_char(valid_start_date, 'yyyyMM') > ? ";

        //9) 検索条件値として、以下のObject配列を作成する。
        //・1：sonar未送信
        //・２：sonar送信済
        //・引数.適用開始年月
        l_objValues = new Object[]{
            WEB3MFStatusDef.SONAR_NOT_SEND,
            WEB3MFStatusDef.SONAR_SENDED,
            WEB3DateUtility.formatDate(
                l_datValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        //10) 定時定額買付共通サービス.get定時定額買付条件変更リストをコールする。
        //[get定時定額買付条件変更リストの引数]
        //証券会社コード　@　@：　@引数.補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード　@　@　@   ：　@引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード　@　@　@   ：　@引数.補助口座.getMainAccount().getAccountCode()の戻り値
        //検索条件文字列     ：作成した検索条件文字列
        //検索条件値　@  　@   ：作成した検索条件値
        l_fixedBuyConditionChangeLists =
            l_mutualFixedBuyCommonService.getFixedBuyConditionChangeList(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_strWhere,
                l_objValues);

        //11）get定時定額買付条件変更リストの戻り値が０件の場合は、
        //merge定時定額買付条件変更(当月反映)の戻り値をリターンする。
        if (l_fixedBuyConditionChangeLists.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return l_mutualFixedBuyConditionUnits;
        }

        //○作成した投信定時定額買付条件行の配列に
        //3ヶ月表示用のレコードと未来月顧客の変更レコードの内容をマージし、
        //投信定時定額買付条件行の配列を作成して返却する。
        //12）get定時定額買付条件変更リストの戻り値が０件でない場合、以下の処理を行う。
        //12-1) this.merge定時定額買付条件変更(未来月反映)をコールする。
        // [merge定時定額買付条件変更(未来月反映)の引数]
        //証券会社　@　@　@　@：引数.補助口座.getInstitution()の戻り値
        //部店コード　@　@　@　@：引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //merge定時定額買付条件行リスト：merge定時定額買付条件変更（当月反映）の戻り値
        //定時定額買付条件変更リスト   ：get定時定額買付条件変更リストの戻り値
        l_mutualFixedBuyConditionUnits =
            this.mergeMutualFixedBuyConditionChangeFutureMonth(
                l_subAccount.getInstitution(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_mutualFixedBuyConditionUnits,
                l_fixedBuyConditionChangeLists);

        //12-2) merge定時定額買付条件変更(未来月反映)の戻り値をリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (update定時定額買付条件変更)<BR>
     * 定時定額買付条件変更テーブルと定時定額買付条件変更履歴テーブルをupdateまたはinsertする。<BR>
     * <BR>
     * ============================================<BR>
     * ○定時定額買付条件変更テーブルを検索する。<BR>
     * ============================================<BR>
     * 1) 定時定額買付条件変更テーブルを検索し、定時定額買付条件変更Paramsを取得する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード：引数.補助口座 .getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@部店コード：引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値<BR>
     * 　@　@口座コード：引数.補助口座.getMainAccount().getAccountCode()の戻り値<BR>
     * 　@　@銘柄コード：引数．投信定時定額買付共通情報．銘柄コード<BR>
     * 　@　@適用開始年月：<BR>
     * 　@　@　@引数.投信定時定額買付共通情報．適用開始年月 < calc適用開始年月（現在日時）（*1）<BR>
     * 　@　@　@　@の場合、calc適用開始年月（現在日時）（*1）<BR>
     * 　@　@　@それ以外の場合、<BR>
     * 　@　@　@　@引数.投信定時定額買付共通情報．適用開始年月<BR>
     * <BR>
     * =============================================<BR>
     * ○定時定額買付条件変更テーブルにレコードが存在する場合、<BR>
     * 　@　@定時定額買付条件変更テーブルへupdateする。<BR>
     * =============================================<BR>
     * 2）検索結果==1件の場合、<BR>
     * 　@　@　@取得した定時定額買付条件変更Paramsオブジェクトからクローンを作成する。 <BR>
     * <BR>
     * 　@　@2)-1) クローンした定時定額買付条件変更Paramsへのセット。<BR>
     * 　@　@　@　@[クローンした定時定額買付条件変更Paramsへのセット]<BR>
     * 　@　@　@　@セット内容は、DB更新仕様参照。<BR>
     * 　@　@　@　@（投信定時定額買付銘柄条件登録_定時定額買付条件変更テーブル.xls<BR>
     * 　@　@　@　@　@定時定額買付条件変更テーブル(更新の場合)シート参照)<BR>
     * <BR>
     * 　@　@2)-2) 定時定額買付条件変更テーブルをupdateする。<BR>
     * <BR>
     * =============================================<BR>
     * ○定時定額買付条件変更テーブルにレコードが存在しない場合、<BR>
     * 　@定時定額買付条件変更テーブルへinsertする。<BR>
     * =============================================<BR>
     * 3）検索結果==0件の場合、定時定額買付条件変更Paramsインスタンスを生成する。<BR>
     * <BR>
     * 　@　@3)-1) 生成した定時定額買付条件変更Paramsへのセット。<BR>
     * 　@　@　@　@[定時定額買付条件変更Paramsへのセット]<BR>
     * 　@　@　@　@セット内容は、DB更新仕様参照。<BR>
     * 　@　@　@　@（投信定時定額買付銘柄条件登録_定時定額買付条件変更テーブル.xls<BR>
     * 　@　@　@　@　@定時定額買付条件変更テーブル(挿入の場合)シート参照)<BR>
     * <BR>
     * 　@　@3)-2) 定時定額買付条件変更テーブルをinsertする。<BR>
     * <BR>
     * ==============================================<BR>
     * ○定時定額買付条件変更履歴テーブルへinsertする。<BR>
     * ==============================================<BR>
     * 4）定時定額買付条件変更履歴Paramsインスタンスを生成する。<BR>
     * 　@　@[定時定額買付条件変更履歴Paramsへのセット]<BR>
     * 　@　@　@　@セット内容は、DB更新仕様参照。<BR>
     * 　@　@　@　@（投信定時定額買付銘柄条件登録_定時定額買付条件変更履歴テーブル.xls）<BR>
     * <BR>
     * 5) 定時定額買付条件変更履歴テーブルをinsertする。<BR>
     * <BR>
     * （*1）引数．適用開始年月<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_trader - (扱者)<BR>
     * 扱者<BR>
     * @@param l_mutualFixedBuyCommonUnit - (投信定時定額買付共通情報)<BR>
     * 投信定時定額買付共通情報<BR>
     * @@param l_mutualFixedBuyConditionUnit - (投信定時定額買付条件行)<BR>
     * 投信定時定額買付条件行<BR>
     * @@param l_datBizDate - (発注日)<BR>
     * 発注日<BR>
     * @@param l_datValidStartDate - (適用開始年月)<BR>
     * 適用開始年月<BR>
     * @@param l_tsPrizeClosingDate - (賞与締切日時)<BR>
     * 賞与締切日時<BR>
     * @@param l_tsCommonClosingDate - (通常締切日時(WEB3))<BR>
     * 通常締切日時(WEB3)<BR>
     * @@roseuid 486DF9E7010E
     * @@throws WEB3BaseException
     */
    protected void updateMutualFixedBuyConditionChange(
        SubAccount l_subAccount,
        Trader l_trader,
        WEB3MutualFixedBuyCommonUnit l_mutualFixedBuyCommonUnit,
        WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit,
        Date l_datBizDate,
        Date l_datValidStartDate,
        Timestamp l_tsPrizeClosingDate,
        Timestamp l_tsCommonClosingDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateMutualFixedBuyConditionChange("
            + "SubAccount, Trader,"
            + " WEB3MutualFixedBuyCommonUnit, WEB3MutualFixedBuyConditionUnit,"
            + " Date, Date, Timestamp, Timestamp)";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFixedBuyCommonUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //○定時定額買付条件変更テーブルを検索する。
        //1) 定時定額買付条件変更テーブルを検索し、定時定額買付条件変更Paramsを取得する。
        //証券会社コード：引数.補助口座 .getInstitution().getInstitutionCode()の戻り値
        //部店コード：引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード：引数.補助口座.getMainAccount().getAccountCode()の戻り値
        //銘柄コード：引数．投信定時定額買付共通情報．銘柄コード
        //適用開始年月：引数.投信定時定額買付共通情報．適用開始年月 < calc適用開始年月（現在日時）の場合、
        //calc適用開始年月（現在日時）
        //それ以外の場合、引数.投信定時定額買付共通情報．適用開始年月
        Timestamp l_tsValidStartDate = null;
        if (WEB3DateUtility.compareToMonth(
            l_mutualFixedBuyCommonUnit.validStartDate,
            l_datValidStartDate) < 0)
        {
            l_tsValidStartDate = new Timestamp(
                l_datValidStartDate.getTime());
        }
        else
        {
            l_tsValidStartDate = new Timestamp(
                l_mutualFixedBuyCommonUnit.validStartDate.getTime());
        }

        List l_lisMfFixedBuyingChangeRows = null;
        String l_strMfFixedBuyingChangeQuery =
            " institution_code = ? and branch_code = ? and account_code = ? "
            + " and product_code = ? and to_char(valid_start_date, 'yyyyMM') = ? ";
        Object[] l_objMfFixedBuyingChangeQuerys = new Object[]{
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_mutualFixedBuyCommonUnit.mutualProductCode,
            WEB3DateUtility.formatDate(
                l_tsValidStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YM)};

        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);
        MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisMfFixedBuyingChangeRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingChangeRow.TYPE,
                l_strMfFixedBuyingChangeQuery,
                l_objMfFixedBuyingChangeQuerys);

            //2）検索結果==1件の場合、取得した定時定額買付条件変更Paramsオブジェクトからクローンを作成する。
            if (l_lisMfFixedBuyingChangeRows.size() == 1)
            {
                //2)-1) クローンした定時定額買付条件変更Paramsへのセット。
                //[クローンした定時定額買付条件変更Paramsへのセット]
                //セット内容は、DB更新仕様参照。
                //投信定時定額買付銘柄条件登録_定時定額買付条件変更テーブル.xls
                //　@定時定額買付条件変更テーブル(更新の場合)シート参照)
                MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                    (MfFixedBuyingChangeRow)l_lisMfFixedBuyingChangeRows.get(0);
                l_mfFixedBuyingChangeParams =
                    new MfFixedBuyingChangeParams(
                        l_mfFixedBuyingChangeRow);

                //買付金額（月々）
                //引数.投信定時定額買付共通情報．変更区分 == 「2:変更」の場合
                //かつ 引数.投信定時定額買付共通情報．買付金額（月々）== nullの場合、
                //引数.投信定時定額買付条件行．買付金額(月々)をセット
                //上記以外
                //引数.投信定時定額買付共通情報．買付金額（月々）をセット
                if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.monthlyBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.monthlyBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(null);
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.monthlyBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(null);
                    }
                }

                //買付金額（積み増し）
                //引数.投信定時定額買付共通情報．変更区分 == 「1:追加」の場合
                //かつ 引数.投信定時定額買付共通情報．買付金額（積み増し） == nullの場合、0をセット
                //引数.投信定時定額買付共通情報．変更区分 == 「2:変更」の場合 かつ
                //引数.投信定時定額買付共通情報．買付金額（積み増し） == nullの場合、
                //引数.投信定時定額買付条件行．買付金額(積み増し)をセット
                //上記以外
                //引数.投信定時定額買付共通情報．買付金額（積み増し）をセット
                if (WEB3ChangeDivDef.ADD.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(0);
                }
                else if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(null);
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.increaseBuyAmount));
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(null);
                    }
                }

                //確定引落買付金額（積み増し）
                //取得した定時定額買付条件変更Params.確定引落買付金額（賞与）がnullの場合
                if (l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmountIsNull())
                {
                    //定時定額買付共通サービス.is賞与月(*1) = true
                    //かつ 引数.賞与締切日時 < 現在日時(*2) <= 引数．通常締切日時(WEB)
                    //(*1)[is賞与月の引数]
                    //証券会社コード：取得した定時定額買付条件変更Params．get証券会社コード
                    //適用開始年月：引数.適用開始年月
                    //(*2)GtlUtils.getTradingSystem().getSystemTimestamp()の値
                    //障害管理票3107
                    if (l_mutualFixedBuyCommonService.isPrizeAndMonth(
                        l_mfFixedBuyingChangeParams.getInstitutionCode(),
                        l_datValidStartDate)
                        && (WEB3DateUtility.compareToSecond(l_tsPrizeClosingDate,
                            GtlUtils.getTradingSystem().getSystemTimestamp()) < 0)
                        && (WEB3DateUtility.compareToSecond(l_tsCommonClosingDate,
                            GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0))
                    {
                        //引数.投信定時定額買付共通情報．変更区分が追加の場合、0
                        if (WEB3ChangeDivDef.ADD.equals(
                            l_mutualFixedBuyCommonUnit.changeDiv))
                        {
                            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(0);
                        }
                        else
                        {
                            if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                            {
                                //引数.投信定時定額買付共通情報．変更区分が変更・解除・一時停止の場合、
                                //引数.投信定時定額買付条件行.買付金額(積み増し)
                                if(WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                    || WEB3ChangeDivDef.RELEASE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                    || WEB3ChangeDivDef.TEMP_STOP.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                                {
                                    l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(
                                        Double.parseDouble(
                                            l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                                }
                            }
                            else
                            {
                                l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
                            }
                        }
                    }
                }

                //変更区分:引数.投信定時定額買付共通情報．変更区分
                l_mfFixedBuyingChangeParams.setChangeDiv(
                    l_mutualFixedBuyCommonUnit.changeDiv);

                //発注区分
                //「1：sonar未送信」
                l_mfFixedBuyingChangeParams.setStatus(
                    WEB3MFStatusDef.SONAR_NOT_SEND);

                //発注日:引数．発注日
                l_mfFixedBuyingChangeParams.setBizDate(l_datBizDate);

                //申込日時:GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_mfFixedBuyingChangeParams.setOrderTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //チャネル
                //セッションより取得した注文チャネル
                OpLoginSecurityService l_opLoginSec =
                    (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                String l_strOrderChanel =
                    l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                l_mfFixedBuyingChangeParams.setOrderChanel(l_strOrderChanel);

                //削除フラグ
                //引数.投信定時定額買付共通情報．変更区分が「1：追加」または「2：変更」または「4：一時停止」の場合、
                //「0：FALSE」
                //引数.投信定時定額買付共通情報．変更区分が「3：解除」かつ
                //クローンされた定時定額買付条件変更Params.get発注区分が「4：sonar送信対象外」かつ
                //クローンされた定時定額買付条件変更Params.get確定引落金額（賞与）がnullの場合
                //「1:TRUE」
                //上記以外
                //「0：FALSE」
                if (WEB3ChangeDivDef.ADD.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    || WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    || WEB3ChangeDivDef.TEMP_STOP.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                {
                    l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
                }
                else if (WEB3ChangeDivDef.RELEASE.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                {
                    //障害3106
                    if (WEB3MFStatusDef.SONAR_SEND_EXCEPT_OBJECT.equals(
                        l_mfFixedBuyingChangeParams.getStatus())
                        && l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmountIsNull())
                    {
                        l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.TRUE);
                    }
                    else
                    {
                        l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
                    }
                }

                //更新者コード
                //顧客入力の場合.引数.補助口座オブジェクト .口座コードをセットする。
                //代理入力の場合.引数.扱者.扱者コードをセットする。
                if (l_trader != null)
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_trader.getTraderCode());
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_subAccount.getMainAccount().getAccountCode());
                }

                //作成日付GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_mfFixedBuyingChangeParams.setCreatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //更新日付GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_mfFixedBuyingChangeParams.setLastUpdatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //2)-2) 定時定額買付条件変更テーブルをupdateする。
                l_queryProcessor.doUpdateQuery(l_mfFixedBuyingChangeParams);
            }
            else if (l_lisMfFixedBuyingChangeRows.size() == 0)
            {
                //3）検索結果==0件の場合、定時定額買付条件変更Paramsインスタンスを生成する。
                l_mfFixedBuyingChangeParams =
                    new MfFixedBuyingChangeParams();

                //証券会社コード
                //引数.補助口座 .getInstitution().getInstitutionCode()の戻り値
                l_mfFixedBuyingChangeParams.setInstitutionCode(
                    l_subAccount.getInstitution().getInstitutionCode());

                //部店コード
                //引数.補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
                l_mfFixedBuyingChangeParams.setBranchCode(
                    l_subAccount.getMainAccount().getBranch().getBranchCode());

                //口座コード
                //引数.補助口座.getMainAccount().getAccountCode()の戻り値
                l_mfFixedBuyingChangeParams.setAccountCode(
                    l_subAccount.getMainAccount().getAccountCode());

                //銘柄コード
                //引数.投信定時定額買付共通情報．銘柄コード
                l_mfFixedBuyingChangeParams.setProductCode(
                    l_mutualFixedBuyCommonUnit.mutualProductCode);

                //適用開始年月
                //引数.投信定時定額買付共通情報．適用年月日 < calc適用開始年月（現在日時）(*3)の場合、
                //calc適用開始年月（現在日時）をセット
                //それ以外の場合、
                //引数.投信定時定額買付共通情報．適用年月日
                //(*3)引数．適用開始年月
                if (WEB3DateUtility.compareToMonth(
                    l_mutualFixedBuyCommonUnit.validStartDate,
                    l_datValidStartDate) < 0)
                {
                    l_mfFixedBuyingChangeParams.setValidStartDate(
                        l_datValidStartDate);
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setValidStartDate(
                        l_mutualFixedBuyCommonUnit.validStartDate);
                }

                //買付金額（月々）
                //引数.投信定時定額買付共通情報．変更区分 == 「2:変更」の場合
                //かつ 引数.投信定時定額買付共通情報．買付金額（月々）== nullの場合、
                //引数.投信定時定額買付条件行．買付金額(月々)をセット
                //上記以外
                //引数.投信定時定額買付共通情報．買付金額（月々）をセット
                if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.monthlyBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.monthlyBuyAmount));
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.monthlyBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.monthlyBuyAmount));
                    }
                }

                //買付金額（積み増し）
                //引数.投信定時定額買付共通情報．変更区分 == 「1:追加」の場合
                //かつ 引数.投信定時定額買付共通情報．買付金額（積み増し） == nullの場合、0をセット
                //引数.投信定時定額買付共通情報．変更区分 == 「2:変更」の場合 かつ
                //引数.投信定時定額買付共通情報．買付金額（積み増し） == nullの場合、
                //引数.投信定時定額買付条件行．買付金額(積み増し)をセット
                //上記以外
                //引数.投信定時定額買付共通情報．買付金額（積み増し）をセット
                if (WEB3ChangeDivDef.ADD.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(0);
                }
                else if (WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                    && l_mutualFixedBuyCommonUnit.increaseBuyAmount == null)
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                    }
                }
                else
                {
                    if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyCommonUnit.increaseBuyAmount))
                    {
                        l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(
                            Double.parseDouble(l_mutualFixedBuyCommonUnit.increaseBuyAmount));
                    }
                }

                //確定引落買付金額（積み増し）
                //定時定額買付共通サービス.is賞与月(*1) = true
                //かつ 引数.賞与締切日時 < 現在日時(*2) <= 引数.通常締切日時(WEB)
                //(*1)[is賞与月の引数]
                //証券会社コード：取得した定時定額買付条件変更Params．get証券会社コード
                //適用開始年月：引数.適用開始年月
                //(*2)GtlUtils.getTradingSystem().getSystemTimestamp()の値
                if (l_mutualFixedBuyCommonService.isPrizeAndMonth(
                    l_mfFixedBuyingChangeParams.getInstitutionCode(),
                    l_datValidStartDate)
                    && (WEB3DateUtility.compareToSecond(l_tsPrizeClosingDate,
                        GtlUtils.getTradingSystem().getSystemTimestamp()) < 0)
                    && (WEB3DateUtility.compareToSecond(l_tsCommonClosingDate,
                        GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0))
                {
                    if (WEB3ChangeDivDef.ADD.equals(
                        l_mutualFixedBuyCommonUnit.changeDiv))
                    {
                        //引数.投信定時定額買付共通情報．変更区分が追加の場合、0
                        l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(0);
                    }
                    else
                    {
                        if (WEB3StringTypeUtility.isNotEmpty(l_mutualFixedBuyConditionUnit.increaseBuyAmount))
                        {
                            //引数.投信定時定額買付共通情報．変更区分が変更・解除・一時停止の場合、
                            //引数.投信定時定額買付条件行.買付金額(積み増し)
                            if(WEB3ChangeDivDef.CHANGE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                ||WEB3ChangeDivDef.RELEASE.equals(l_mutualFixedBuyCommonUnit.changeDiv)
                                || WEB3ChangeDivDef.TEMP_STOP.equals(l_mutualFixedBuyCommonUnit.changeDiv))
                            {
                                l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(
                                    Double.parseDouble(
                                        l_mutualFixedBuyConditionUnit.increaseBuyAmount));
                            }
                        }
                        else
                        {
                            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
                        }
                    }
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
                }

                //変更区分:引数.投信定時定額買付共通情報．変更区分
                l_mfFixedBuyingChangeParams.setChangeDiv(
                    l_mutualFixedBuyCommonUnit.changeDiv);

                //発注区分
                //「1：sonar未送信」
                l_mfFixedBuyingChangeParams.setStatus(WEB3MFStatusDef.SONAR_NOT_SEND);

                //発注日
                //引数．発注日
                l_mfFixedBuyingChangeParams.setBizDate(l_datBizDate);

                //申込日時:GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_mfFixedBuyingChangeParams.setOrderTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //チャネル
                //セッションより取得した注文チャネル
                OpLoginSecurityService l_opLoginSec =
                    (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                String l_strOrderChanel =
                    l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);
                l_mfFixedBuyingChangeParams.setOrderChanel(l_strOrderChanel);

                //削除フラグ
                //「0：FALSE」
                l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);

                //更新者コード
                //顧客入力の場合.引数.補助口座オブジェクト .口座コードをセットする。
                //代理入力の場合.引数.扱者.扱者コードをセットする。
                if (l_trader != null)
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_trader.getTraderCode());
                }
                else
                {
                    l_mfFixedBuyingChangeParams.setLastUpdater(
                        l_subAccount.getMainAccount().getAccountCode());
                }

                //作成日付GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_mfFixedBuyingChangeParams.setCreatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //更新日付GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
                l_mfFixedBuyingChangeParams.setLastUpdatedTimestamp(
                    GtlUtils.getTradingSystem().getSystemTimestamp());

                //3)-2) 定時定額買付条件変更テーブルをinsertする。
                WEB3DataAccessUtility.insertRow(l_mfFixedBuyingChangeParams);
            }

            //○定時定額買付条件変更履歴テーブルへinsertする。
            //）定時定額買付条件変更履歴Paramsインスタンスを生成する。
            //[定時定額買付条件変更履歴Paramsへのセット]
            //セット内容は、DB更新仕様参照。
            //（投信定時定額買付銘柄条件登録_定時定額買付条件変更履歴テーブル.xls）
            MfFixedBuyingChangeHistParams l_mfFixedBuyingChangeHistParams =
                new MfFixedBuyingChangeHistParams();

            //証券会社コード:取得した定時定額買付条件変更Params．get証券会社コード
            l_mfFixedBuyingChangeHistParams.setInstitutionCode(
                l_mfFixedBuyingChangeParams.getInstitutionCode());

            //部店コード:取得した定時定額買付条件変更Params．get部店コード
            l_mfFixedBuyingChangeHistParams.setBranchCode(
                l_mfFixedBuyingChangeParams.getBranchCode());

            //口座コード:取得した定時定額買付条件変更Params．get口座コード
            l_mfFixedBuyingChangeHistParams.setAccountCode(
                l_mfFixedBuyingChangeParams.getAccountCode());

            //銘柄コード:取得した定時定額買付条件変更Params．get銘柄コード
            l_mfFixedBuyingChangeHistParams.setProductCode(
                l_mfFixedBuyingChangeParams.getProductCode());

            //適用開始年月:取得した定時定額買付条件変更Params．get適用開始年月
            l_mfFixedBuyingChangeHistParams.setValidStartDate(
                l_mfFixedBuyingChangeParams.getValidStartDate());

            //履歴番号:this．get最新履歴番号（*1）の戻り値
            //(*1)[get最新履歴番号の引数]
            //証券会社コード：取得した定時定額買付条件変更Params．get証券会社コード
            //部店コード：取得した定時定額買付条件変更Params．get部店コード
            //口座コード：取得した定時定額買付条件変更Params．get口座コード
            //銘柄コード：取得した定時定額買付条件変更Params．get銘柄コード
            //適用開始年月：取得した定時定額買付条件変更Params．get適用開始年月
            l_mfFixedBuyingChangeHistParams.setSerialNo(
                (int)this.getLastNumber(
                    l_mfFixedBuyingChangeParams.getInstitutionCode(),
                    l_mfFixedBuyingChangeParams.getBranchCode(),
                    l_mfFixedBuyingChangeParams.getAccountCode(),
                    l_mfFixedBuyingChangeParams.getProductCode(),
                    l_mfFixedBuyingChangeParams.getValidStartDate()));

            //買付金額（月々）:取得した定時定額買付条件変更Params．get買付金額（月々）
            if (!l_mfFixedBuyingChangeParams.getMonthlyBuyAmountIsNull())
            {
                l_mfFixedBuyingChangeHistParams.setMonthlyBuyAmount(
                    l_mfFixedBuyingChangeParams.getMonthlyBuyAmount());
            }
            else
            {
                l_mfFixedBuyingChangeHistParams.setMonthlyBuyAmount(null);
            }

            //買付金額（積み増し）:取得した定時定額買付条件変更Params．get買付金額（賞与）
            if (!l_mfFixedBuyingChangeParams.getIncreaseBuyAmountIsNull())
            {
                l_mfFixedBuyingChangeHistParams.setIncreaseBuyAmount(
                    l_mfFixedBuyingChangeParams.getIncreaseBuyAmount());
            }
            else
            {
                l_mfFixedBuyingChangeHistParams.setIncreaseBuyAmount(null);
            }

            //確定引落買付金額（積み増し）:取得した定時定額買付条件変更Params．get確定引落買付金額（賞与）
            if (!l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmountIsNull())
            {
                l_mfFixedBuyingChangeHistParams.setFinDrawIncreaseBuyAmount(
                    l_mfFixedBuyingChangeParams.getFinDrawIncreaseBuyAmount());
            }
            else
            {
                l_mfFixedBuyingChangeHistParams.setFinDrawIncreaseBuyAmount(null);
            }

            //変更区分:取得した定時定額買付条件変更Params．get変更区分
            l_mfFixedBuyingChangeHistParams.setChangeDiv(
                l_mfFixedBuyingChangeParams.getChangeDiv());

            //発注区分:取得した定時定額買付条件変更Params．get発注区分
            l_mfFixedBuyingChangeHistParams.setStatus(
                l_mfFixedBuyingChangeParams.getStatus());

            //発注日  :取得した定時定額買付条件変更Params．get発注日
            l_mfFixedBuyingChangeHistParams.setBizDate(
                l_mfFixedBuyingChangeParams.getBizDate());

            //申込日時:取得した定時定額買付条件変更Params．get申込日時
            l_mfFixedBuyingChangeHistParams.setOrderTimestamp(
                l_mfFixedBuyingChangeParams.getOrderTimestamp());

            //チャネル:取得した定時定額買付条件変更Params．getチャネル
            l_mfFixedBuyingChangeHistParams.setOrderChanel(
                l_mfFixedBuyingChangeParams.getOrderChanel());

            //削除フラグ  :取得した定時定額買付条件変更Params．get削除フラグ
            l_mfFixedBuyingChangeHistParams.setDeleteFlag(
                l_mfFixedBuyingChangeParams.getDeleteFlag());

            //更新者コード:取得した定時定額買付条件変更Params．get更新者コード
            l_mfFixedBuyingChangeHistParams.setLastUpdater(
                l_mfFixedBuyingChangeParams.getLastUpdater());

            //作成日付    :取得した定時定額買付条件変更Params．get作成日付
            l_mfFixedBuyingChangeHistParams.setCreatedTimestamp(
                l_mfFixedBuyingChangeParams.getCreatedTimestamp());

            //更新日付    :取得した定時定額買付条件変更Params．get更新日付
            l_mfFixedBuyingChangeHistParams.setLastUpdatedTimestamp(
                l_mfFixedBuyingChangeParams.getLastUpdatedTimestamp());

            //5) 定時定額買付条件変更履歴テーブルをinsertする。
            WEB3DataAccessUtility.insertRow(l_mfFixedBuyingChangeHistParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get定時定額買付金額合計)<BR>
     * get定時定額買付金額合計<BR>
     * 定時定額買付金額の合計を計算する。<BR>
     * <BR>
     * １．投信定時定額買付金額合計オブジェクトを生成する。<BR>
     * <BR>
     * <BR>
     * ２．投信定時定額買付条件行[]の件数分、以下の処理を繰返す。<BR>
     * <BR>
     * 　@２－１．投信定時定額買付条件行.一時停止中フラグ　@==　@false の場合、<BR>
     * 　@　@２-１-１．投信定時定額買付金額合計.月々合計 =<BR>
     * 　@　@　@　@　@　@投信定時定額買付金額合計.月々合計 + 投信定時定額買付条件行.買付金額（月々）<BR>
     * <BR>
     * 　@　@２-１-２．投信定時定額買付金額合計.積み増し合計 =<BR>
     * 　@　@　@　@　@　@投信定時定額買付金額合計.積み増し合計 + 投信定時定額買付条件行.買付金額（積み増し）<BR>
     * <BR>
     * 　@　@２-１-３．投信定時定額買付金額合計.口座引落年月 = ｎｕｌｌの場合<BR>
     * <BR>
     * 　@　@　@　@２-１-３-１．投信定時定額買付金額合計.口座引落年月 =　@投信定時定額買付条件行.口座引落年月<BR>
     * <BR>
     * ３．戻り値 定時定額買付金額合計[0]に投信定時定額買付金額合計をセットする。<BR>
     * 　@　@　@（*)nullでない場合、セットする。<BR>
     * @@param l_mutualFixedBuyConditionUnits - (投信定時定額買付条件行[])<BR>
     * 投信定時定額買付条件行[]<BR>
     * @@return WEB3MutualFixedBuyTotalUnit[]<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyTotalUnit[] getFixedBuyTotalUnit(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFixedBuyTotalUnit(WEB3MutualFixedBuyConditionUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFixedBuyConditionUnits == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１．投信定時定額買付金額合計オブジェクトを生成する。
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnit = new WEB3MutualFixedBuyTotalUnit();

        //２．投信定時定額買付条件行[]の件数分、以下の処理を繰返す。
        int l_intLength = l_mutualFixedBuyConditionUnits.length;
        for (int i = 0; i < l_intLength; i ++)
        {
            //２－１．投信定時定額買付条件行.一時停止中フラグ　@==　@false の場合、

            if (!l_mutualFixedBuyConditionUnits[i].suspensionFlag)
            {
                //２-１-１．投信定時定額買付金額合計.月々合計 =
                //投信定時定額買付金額合計.月々合計 + 投信定時定額買付条件行.買付金額（月々）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnit.monthlyBATotal))
                {
                    l_mfBuyTotalUnit.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnit.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnit.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //２-１-２．投信定時定額買付金額合計.積み増し合計 =
                //投信定時定額買付金額合計.積み増し合計 + 投信定時定額買付条件行.買付金額（積み増し）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnit.increaseBATotal))
                {
                    l_mfBuyTotalUnit.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }
                l_mfBuyTotalUnit.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnit.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //２-１-３．投信定時定額買付金額合計.口座引落年月 = ｎｕｌｌの場合
                //２-１-３-１．投信定時定額買付金額合計.口座引落年月 =　@投信定時定額買付条件行.口座引落年月
                if (l_mfBuyTotalUnit.debitAccountYM == null)
                {
                    //２-１-３-１．投信定時定額買付金額合計.口座引落年月 =
                    //　@投信定時定額買付条件行.口座引落年月
                    l_mfBuyTotalUnit.debitAccountYM = l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }
        }

        //３．戻り値 定時定額買付金額合計[0]に投信定時定額買付金額合計をセットする。
        //（*)nullでない場合、セットする。
        List l_lisMutualFixedBuyTotalUnits = new ArrayList();
        if (l_mfBuyTotalUnit.debitAccountYM != null
            && l_mfBuyTotalUnit.increaseBATotal != null
            && l_mfBuyTotalUnit.monthlyBATotal != null)
        {
            l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnit);
        }
        WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnits =
            new WEB3MutualFixedBuyTotalUnit[l_lisMutualFixedBuyTotalUnits.size()];
        l_lisMutualFixedBuyTotalUnits.toArray(l_mutualFixedBuyTotalUnits);
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyTotalUnits;
    }
}
@
