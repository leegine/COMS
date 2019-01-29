head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwtProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信乗換先銘柄一覧照会サービスImpl(WEB3MutualSwtProductListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 韋念瓊 (中訊) 新規作成                   
Revesion History : 2007/04/06 唐性峰 (中訊) 実装005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.message.WEB3MutualBuyProductGroup;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.message.WEB3MutualSwTargetListRequest;
import webbroker3.mf.message.WEB3MutualSwTargetListResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwtProductListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3Toolkit;

/**
 * 投信乗換先銘柄一覧照会サービスImpl
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwtProductListServiceImpl extends 
    WEB3MutualClientRequestService implements WEB3MutualSwtProductListService 
{    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwtProductListServiceImpl.class);
        
    /**
     * 投信乗換先銘柄一覧照会サービス処理を行う。 <BR>
     * <BR>
     * シーケンス図「（投信）乗換先銘柄一覧」参照。<BR>
     * <BR>
     *  ========================================================<BR> 
     *  1.11 isシステム取扱( )<BR>
     *      戻り値 == false の場合,例外(取扱不可銘柄エラー)をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00362<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR> 
     *  1.12  is解約乗換可能(Date)<BR>
     *      戻り値 == false の場合,例外(取引不可銘柄エラー)をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.13  is乗換可能( )<BR>
     *      戻り値 == false の場合,例外(乗換不可銘柄エラー)をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00375<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.15 calc解約可能残高口数(SubAccount, 拡張投信銘柄, String)<BR>
     *      戻り値 == 0 の場合,例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02270<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.16 get乗換可能銘柄リスト(Timestamp)<BR>
     *      戻り値 == null  or 戻り値.size == 0 の場合 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01364<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.17.2 get表示対象銘柄リスト(List, String[])<BR>
     *      戻り値 == null  or 戻り値.size == 0 の場合 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01364<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2ED11023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3MutualSwTargetListRequest l_swtListRequest = null;
        
        if (l_request instanceof WEB3MutualSwTargetListRequest)
        {
            l_swtListRequest = (WEB3MutualSwTargetListRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        //1.1 入力内容チェック 
        l_swtListRequest.validate();

        // --顧客別取引停止属性チェック 
        //1.2 this.get補助口座( )をコールし、補助口座オブジェクトを取得する。
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3 getAsset(資産ID : long)
        //保有資産オブジェクトを取得する。 
        //[引数] 
        //資産ID： リクエスト.資産ID 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_mfPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        AssetRow l_assetRow = null;
        try
        {
            Asset l_asset = 
                l_mfPositionManager.getAsset(
                    Long.parseLong(l_swtListRequest.id));
            l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
        }
        catch (NotFoundException l_ex)
        {
            log.debug("保有資産該当データなし。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産該当データなし。");
        }
        
        //1.4 getProduct(銘柄ID : long)
        //銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄ID： 保有資産.銘柄ID        
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        
        Product l_product = null;
        try
        {
            l_product = l_mfProductManager.getProduct(l_assetRow.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("テーブルに該当するデータがありません。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        
        //1.5 to銘柄(Row)(拡張投信銘柄マネージャ::to銘柄)
        //投信銘柄オブジェクトを取得する。 
        //[引数] 
        //Rowオブジェクト： getProduct()の戻り値から取得したRowオブジェクト         
        Row l_row = (Row)l_product.getDataSourceObject();
        
        WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)
            l_mfProductManager.toProduct(l_row);
        
        //1.6 reset銘柄コード(銘柄コード : String)
        //取引カレンダコンテキストの銘柄コードを更新する。 
        //[引数] 
        //銘柄コード： 投信銘柄.getProductCode()の戻り値
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mfProduct.getProductCode());
        
        //1.7 受付日時、日付ロールをセットする。
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        //1.8 validate注文受付可能( )
        //  以下のチェックを行う。   
        //  受付時間チェック 
        //  システム停止チェック 
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.9 get投信発注日( )(投信取引時間管理::get投信発注日)
        //発注日を取得する。
        Timestamp l_datBizDate = new Timestamp(
            WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
        
        //1.10 validate取引可能顧客(顧客 : 顧客, 発注日 : Timestamp)
        //取引可能な顧客かどうかをチェックする。 

        //[引数] 
        //顧客： 補助口座.getMainAccount()の戻り値 
        //発注日： get投信発注日()の戻り値
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        
        OrderValidationResult l_validationResult =  
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.11 isシステム取扱( )
        boolean l_blnIsSystemHandling = l_mfProduct.isSystemHandling();
        
        //戻り値 == false の場合,例外(取扱不可銘柄エラー)をスローする。
        if (!l_blnIsSystemHandling)
        {
            log.debug("取扱不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取扱不可銘柄エラー。");
        }
        
        //1.12  is解約乗換可能(Date)
        boolean l_blnIsSellSwitchingPoss = 
            l_mfProduct.isSellSwitchingPossible(l_datBizDate);
        
        //戻り値 == false の場合,例外(取引不可銘柄エラー)をスローする。
        if (!l_blnIsSellSwitchingPoss)
        {
            log.debug("取引不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引不可銘柄エラー。");
        }
        
        //1.13  is乗換可能( )
        boolean l_blnIsSwitchingAble = 
            l_mfProduct.isSwitchingAble();
        //戻り値 == false の場合,例外(乗換不可銘柄エラー)をスローする。
        if (!l_blnIsSwitchingAble)
        {
            log.debug("乗換不可銘柄エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00375,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乗換不可銘柄エラー。");
        }
        
        //1.14 validate緊急停止(拡張投信銘柄, String)
        //乗換元銘柄が緊急停止されているかどうかをチェックする。 
        //[引数] 
        //拡張投信銘柄： 投信銘柄オブジェクト 
        //処理区分： “乗換” 
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();

        l_validationsCheck.validateEmergencyStop(
            l_mfProduct, WEB3ProcessDivDef.SWITCHING);       
        
        //1.15 calc解約可能残高口数(SubAccount, 拡張投信銘柄, String)
        //乗換可能残高口数を取得する。 

        //［引数］ 
        //補助口座： 補助口座オブジェクト 
        //拡張投信銘柄： 投信銘柄オブジェクト 
        //資産ID： リクエスト.資産ID 
        double l_dblSellPossQty = 
            l_mfPositionManager.calcSellPossiblePositionQty(
                l_subAccount, l_mfProduct, l_swtListRequest.id);
        
        //戻り値 == 0 の場合,例外をスローする。
        if (l_dblSellPossQty == 0)
        {
            log.debug("乗換可能残高口数不足エラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02270,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乗換可能残高口数不足エラー。");
        }
        
        //1.16 get乗換可能銘柄リスト()
        //乗換可能な銘柄のリストを取得する。 
        List l_lisSwtPossProduct = 
            l_mfProduct.getSwitchingAbleProductList();
        
        // 戻り値 == null  or 戻り値.size == 0 の場合 例外をスローする。
        if (l_lisSwtPossProduct == null || l_lisSwtPossProduct.size() == 0)
        {
            log.debug("乗換可能銘柄なしエラー。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01364,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "乗換可能銘柄なしエラー。");
        }
        
        List l_lisObjProduct = null;
        
        //1.17  (*1)リクエスト.投信銘柄カテゴリーコード != null の場合
        if (l_swtListRequest.categoryCode != null)
        {
            //1.17.1 get表示対象カテゴリーコード(String, String)
            //表示対象となる投信銘柄カテゴリーコードの配列を取得する。 
            //[引数] 
            //証券会社コード： 顧客.getInstitution().getInstitutionCode()の戻り値 
            //カテゴリーコード： リクエスト.投信銘柄カテゴリーコード 
            String[] l_strCategoryCodes = 
                this.getObjCategoryCode(
                    l_genMainAccount.getInstitution().getInstitutionCode(), 
                    l_swtListRequest.categoryCode);
            
            //1.17.2 get表示対象銘柄リスト(List, String[])
            //一覧の表示対象となる銘柄のリストを取得する。 
            //[引数] 
            //乗換可能銘柄： get乗換可能銘柄リスト()の戻り値 
            //カテゴリーコード： get表示対象カテゴリーコード()の戻り値 
            l_lisObjProduct = 
                this.getObjProductList(l_lisSwtPossProduct, l_strCategoryCodes);
            
            //戻り値 == null  or 戻り値.size == 0 の場合 例外をスローする。
            if (l_lisObjProduct == null || l_lisObjProduct.size() == 0)
            {
                log.debug("乗換可能銘柄なしエラー。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01364,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "乗換可能銘柄なしエラー。");
            }
        }          
        
        //1.18 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //ページング処理オブジェクトを生成する。 

        //[引数] 
        //l_list： （以下のとおり） 
        //   リクエスト.投信銘柄カテゴリーコード != null の場合、get表示対象銘柄リスト()の戻り値 
        //   リクエスト.投信銘柄カテゴリーコード == null の場合、get乗換可能銘柄リスト()の戻り値 
        //l_intRequestPageIndex： リクエスト.要求ページ番号 
        //l_intRequestPageSize： リクエスト.ページ内表示行数
        int l_intPageSize = Integer.parseInt(l_swtListRequest.pageSize);
        int l_intPageIndex = Integer.parseInt(l_swtListRequest.pageIndex);  
        
        WEB3PageIndexInfo l_pageIndexInfo = null;
        
        if (l_swtListRequest.categoryCode != null)
        {
            log.debug("リクエスト.投信銘柄カテゴリーコード != null の場合");
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisObjProduct, 
                l_intPageIndex, 
                l_intPageSize);            
        }
        else
        {
            log.debug("リクエスト.投信銘柄カテゴリーコード == null の場合");
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisSwtPossProduct, 
                l_intPageIndex, 
                l_intPageSize);            
        }
        
        List l_lisBuyProdcutGroup = new Vector();
        
        //1.19 getArrayReturned( )
        //表示対象ページに該当するオブジェクト配列を取得する。 
        WEB3MutualFundProduct[] l_mfArrProducts = 
            (WEB3MutualFundProduct[]) l_pageIndexInfo.getArrayReturned(
                WEB3MutualFundProduct.class);
        
        log.debug("getArrayReturned()の戻り値のsize = " + l_mfArrProducts.length);
        
        //1.20 reset注文受付トランザクション(String)
        //取引カレンダコンテキストの注文受付トランザクションを更新する。
		//[引数]
		//注文受付トランザクション： 注文受付トランザクション.”買付”
		WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
				WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);

        //1.21 (*2)getArrayReturned()の戻り値の各要素についてLoop処理
        for (int i = 0; i < l_mfArrProducts.length; i++)
        {
			//1.21.1 reset銘柄コード(銘柄コード : String)
			//取引カレンダコンテキストの銘柄コードを更新する。 

			//[引数] 
			//銘柄コード： 投信銘柄.getProductCode()の戻り値 
			WEB3MutualFundTradingTimeManagement.resetProductCode(l_mfArrProducts[i].getProductCode());

			//1.21.2 setTimestamp( )
			//受付日時、日付ロールをセットする。 
			WEB3MutualFundTradingTimeManagement.setTimestamp();
            
            //1.21.3 投信買付銘柄一覧行のインスタンスを生成する。 
            WEB3MutualBuyProductGroup l_mfBuyProdcutGroup = new WEB3MutualBuyProductGroup();
            MutualFundProductRow l_mfProductRow = (MutualFundProductRow)
                l_mfArrProducts[i].getDataSourceObject();
            
            //1.21.4 (*3)プロパティセット
            //(*3)以下のとおりに、プロパティをセットする。

            //ID          = 投信銘柄.getProductId()の戻り値
            l_mfBuyProdcutGroup.id = l_mfArrProducts[i].getProductId() + "";
            
            //銘柄コード           = 投信銘柄.getProductCode()の戻り値
            l_mfBuyProdcutGroup.mutualProductCode = l_mfArrProducts[i].getProductCode();
            
            //銘柄名         = 投信銘柄.get銘柄名()の戻り値
			l_mfBuyProdcutGroup.mutualProductName = l_mfArrProducts[i].getMutualProductName();
            
            //投信銘柄カテゴリーコード    = 投信銘柄.getカテゴリーコード()の戻り値
            l_mfBuyProdcutGroup.categoryCode = l_mfProductRow.getCategoryCode();
            
            //買付基準価額通貨コード = 投信銘柄.get通貨コード()の戻り値
            l_mfBuyProdcutGroup.constantValueCurrencyCode = l_mfArrProducts[i].getCurrencyCode();
            
            //買付基準価額      = 投信銘柄.get買付基準価額()の戻り値
            l_mfBuyProdcutGroup.constantValue = l_mfArrProducts[i].getConstantValue() + "";
            
            //買付基準価額適用日   = 投信銘柄.get基準価額適用日()の戻り値
            l_mfBuyProdcutGroup.constantValueAppDate = l_mfArrProducts[i].getConstantValueAppDate();
            
            //新規買付時単位口数   = 投信銘柄.get単位口数(新規買付)()の戻り値
            l_mfBuyProdcutGroup.newBuyUnitQty = l_mfArrProducts[i].getNewBuyUnitQty() + "";
            
            //新規買付時最低口数   = 投信銘柄.get最低口数(新規買付)()の戻り値
            l_mfBuyProdcutGroup.newBuyMinQty = l_mfArrProducts[i].getNewBuyMinQty() + "";
            
            //新規買付時単位金額   = 投信銘柄.get単位金額(新規買付)()の戻り値
            l_mfBuyProdcutGroup.newBuyUnitAmt = l_mfArrProducts[i].getNewBuyUnitAmt() + "";
            
            //新規買付時最低金額   = 投信銘柄.get最低金額(新規買付)()の戻り値
            l_mfBuyProdcutGroup.newBuyMinAmt = l_mfArrProducts[i].getNewBuyMinAmt() + "";
            
            //追加買付時単位口数   = 投信銘柄.get単位口数(追加買付)()の戻り値
            l_mfBuyProdcutGroup.addBuyUnitQty = l_mfArrProducts[i].getAddBuyUnitQty() + "";
            
            //追加買付時最低口数   = 投信銘柄.get最低口数(追加買付)()の戻り値
            l_mfBuyProdcutGroup.addBuyMinQty = l_mfArrProducts[i].getAddBuyMinQty() + "";
            
            //追加買付時単位金額   = 投信銘柄.get単位金額(追加買付)()の戻り値
            l_mfBuyProdcutGroup.addBuyUnitAmt = l_mfArrProducts[i].getAddBuyUnitAmt() + "";
            
            //追加買付時最低金額   = 投信銘柄.get最低金額(追加買付)()の戻り値
            l_mfBuyProdcutGroup.addBuyMinAmt = l_mfArrProducts[i].getAddBuyMinAmt() + "";
            
            //注文受付締切時間        = 投信取引時間管理.get注文受付締切()をコールし、
            //               戻された値の１秒後の時間"HHMMSS"を"HH:MM"に編集してセットする。
            String l_strOrderCloseTime = 
                WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
            Date l_datOrderCloseTime = 
                WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
            l_strOrderCloseTime = 
                WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");  
            l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                + WEB3GentradeTimeDef.STR_COLON
                + l_strOrderCloseTime.substring(2, 4);            
            
            l_mfBuyProdcutGroup.orderCloseTime = l_strOrderCloseTime;
            
            //備考区分            = null
            l_mfBuyProdcutGroup.noteType = null;
            
            //1.21.5 is買付可能(Date)(拡張投信銘柄::is買付可能)
            //当該銘柄が買付可能かどうかを判定する。 

            //[引数] 
            //発注日： get投信発注日()の戻り値 
            boolean l_blnIsBuyPoss = 
                l_mfArrProducts[i].isAcquiredPossible(l_datBizDate);
            
            //戻り値 == false の場合、以下のとおりにプロパティをセットする。
            //投信買付銘柄一覧行.備考区分 = ”買付停止中”
            if (!l_blnIsBuyPoss)
            {
                l_mfBuyProdcutGroup.noteType = WEB3RemarkDivDef.HANDLING_DISABLE;
            }
            
            //1.21.6 validate注文受付可能( )
            //当該銘柄が受付可能かどうかをチェックする。 
            try
            {
                WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            }
            catch(WEB3BaseException l_ex)
            {
                //例外がスローされた場合、以下のとおりにプロパティをセットする。
                //投信買付銘柄一覧行.備考区分 = ”受付時間外”
                l_mfBuyProdcutGroup.noteType = WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
            }
            
            //1.21.7 validate緊急停止(拡張投信銘柄, String)
            //当該銘柄が緊急停止されているかどうかをチェックする。 

            //[引数] 
            //拡張投信銘柄： 投信銘柄オブジェクト 
            //処理区分： “買付” 
            try
            {
                l_validationsCheck.validateEmergencyStop(l_mfArrProducts[i], WEB3ProcessDivDef.BUY);
            }
            catch(WEB3BaseException l_ex)
            {
                //例外がスローされた場合、以下のとおりにプロパティをセットする。
                //投信買付銘柄一覧行.備考区分 = ”緊急停止中”
                l_mfBuyProdcutGroup.noteType = WEB3RemarkDivDef.EMERGENCY_STOP;
            }
            l_lisBuyProdcutGroup.add(l_mfBuyProdcutGroup);
        }
        
        int l_intListSize = l_lisBuyProdcutGroup.size();
        
        WEB3MutualBuyProductGroup[] l_mfBuyProductGroups = 
            new WEB3MutualBuyProductGroup[l_intListSize];
        
        l_lisBuyProdcutGroup.toArray(l_mfBuyProductGroups);
        
        //1.22 get投信銘柄カテゴリーリスト(String)
        //投信銘柄カテゴリーコードを検索する。 

        //[引数] 
        //証券会社コード： 顧客.getInstitution().getInstitutionCode()の戻り値 
        List l_lisProductCategory = 
            l_mfProductManager.getMutualFundProductCategoryList(
                l_genMainAccount.getInstitution().getInstitutionCode());        
        
        //1.23 create投信銘柄カテゴリー一覧(List)
        //投信銘柄カテゴリー一覧を作成する。 

        //[引数] 
        //銘柄カテゴリー一覧： get投信銘柄カテゴリーリスト()の戻り値 
        WEB3MutualProductCategoryUnit[] l_productCategoryUnits = 
            l_mfProductManager.createMutualFundProductCategoryList(
                l_lisProductCategory);
        
        //1.24 getPageIndex( ) 表示ページ番号を取得する。 
        String l_strPageIndex = l_pageIndexInfo.getPageIndex() + "";

        //1.25 getTotalPages( ) 総ページ数を取得する。
        String l_strTotalPages = l_pageIndexInfo.getTotalPages() + ""; 
        
        //1.26 getTotalRecords( ) 総レコード数を取得する。
        String l_strTotalRecords = l_pageIndexInfo.getTotalRecords() + ""; 
        
        //1.27 createResponse( )
        //レスポンスデータを生成する。
        WEB3MutualSwTargetListResponse l_response = 
            (WEB3MutualSwTargetListResponse) l_request.createResponse();
        
        //1.28 (*4)以下のとおりに、プロパティをセットする。

        //投信銘柄カテゴリー一覧 = create投信銘柄カテゴリー一覧()の戻り値
        l_response.categoryList = l_productCategoryUnits;
        
        //表示ページ番号     = getPageIndex()の戻り値
        l_response.pageIndex = l_strPageIndex; 
        
        //総ページ数       = getTotalPages()の戻り値
        l_response.totalPages = l_strTotalPages; 
        
        //総レコード数      = getTotalRecords()の戻り値
        l_response.totalRecords = l_strTotalRecords;
        
        //乗換先銘柄一覧     = 投信買付銘柄一覧行の配列
        l_response.switchingProductGroups = l_mfBuyProductGroups;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get表示対象カテゴリーコード)<BR>
     * 一覧に表示対象となるカテゴリーコードの配列を取得する。 <BR>
     * <BR>
     * １）指定された投信銘柄カテゴリーコードのカテゴリーとリンクする下位の <BR>
     * カテゴリーを取得する。 <BR>
     * <BR>
     *    拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト()をコールする。<BR>
     * <BR>
     *    [引数] <BR>
     *    証券会社コード： 引数.証券会社コード <BR>
     *    カテゴリーコード： 引数.カテゴリーコード <BR>
     * <BR>
     * ２）取得したカテゴリーのリストからカテゴリーコードの配列を生成する。 <BR>
     * <BR>
     * ３）生成したカテゴリーコードの配列に引数.カテゴリーコードを追加し、<BR>
     * その配列を返却する。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strCategoryCode - カテゴリーコード
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 40BDAA350378
     */
    protected String[] getObjCategoryCode(
        String l_strInstitutionCode, 
        String l_strCategoryCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getObjCategoryCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）指定された投信銘柄カテゴリーコードのカテゴリーとリンクする下位のカテゴリーを取得する。 
        //拡張投信銘柄マネージャ.get下位投信銘柄カテゴリーリスト()をコールする。 

        //[引数] 
        //証券会社コード： 引数.証券会社コード 
        //カテゴリーコード： 引数.カテゴリーコード 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        
        List l_lisLowMfProductCategory = 
            l_mfProductManager.getLowMutualFundProductCategoryList(
                l_strInstitutionCode, l_strCategoryCode);
        
        //２）取得したカテゴリーのリストからカテゴリーコードの配列を生成する。 
        List l_lisCategoryCodes = new Vector();
        if (l_lisLowMfProductCategory != null)
        {            
            for (int i = 0; i < l_lisLowMfProductCategory.size(); i++)
            {
                MutualFundProductCategoryRow l_mfProductCategoryRow = 
                    (MutualFundProductCategoryRow)l_lisLowMfProductCategory.get(i);
                
                l_lisCategoryCodes.add(l_mfProductCategoryRow.getCategoryCode());
            }
        }
        
        //３）生成したカテゴリーコードの配列に引数.カテゴリーコードを追加し、その配列を返却する。
        l_lisCategoryCodes.add(l_strCategoryCode);
        
        String[] l_strCategoryCodes = new String[l_lisCategoryCodes.size()];
        l_lisCategoryCodes.toArray(l_strCategoryCodes);
       
        log.exiting(STR_METHOD_NAME);
        return l_strCategoryCodes;
    }
    
    /**
     * (get表示対象銘柄リスト)<BR>
     * 一覧の表示対象となる銘柄のリストを取得する。 <BR>
     * <BR>
     * １）空のリストを生成する。 <BR>
     * <BR>
     * ２）乗換可能銘柄のリストの各要素について、以下の処理を行う。 <BR>
     * <BR>
     * ２－１）銘柄.カテゴリーコードと同じコードが引数.カテゴリーコードの<BR>
     *      配列に存在するかをチェックする。 <BR>
     * <BR>
     * ２－２）存在する場合は、１）で生成したリストに追加する。 <BR>
     * <BR>
     * ３）生成されたリストを返却する。 <BR>
     * <BR>
     * @@param l_lisSwtPossProduct - 乗換可能銘柄
     * @@param l_strCategoryCode - カテゴリーコード
     * @@return List
     * @@roseuid 40BDAA350378
     */
    protected List getObjProductList(
        List l_lisSwtPossProduct, String[] l_strCategoryCode)
    {
        String STR_METHOD_NAME = "getObjProductList(List, String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisSwtPossProduct == null || l_strCategoryCode == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）空のリストを生成する。 
        List l_lisObjProduct = new Vector();

        //２）乗換可能銘柄のリストの各要素について、以下の処理を行う。     
        for (int i = 0; i < l_lisSwtPossProduct.size(); i++)
        {
            WEB3MutualFundProduct l_mfSwtProduct = 
                (WEB3MutualFundProduct)l_lisSwtPossProduct.get(i);
            
            MutualFundProductRow l_mfProductRow = (MutualFundProductRow)l_mfSwtProduct.getDataSourceObject();
            
            String l_strSwtCategoryCode = l_mfProductRow.getCategoryCode();
            
            //２－１）銘柄.カテゴリーコードと同じコードが引数.カテゴリーコードの配列に存在するかをチェックする。             
            if (WEB3Toolkit.contain(l_strCategoryCode, l_strSwtCategoryCode))
            {
                //２－２）存在する場合は、１）で生成したリストに追加する。 
                l_lisObjProduct.add(l_mfSwtProduct);
            }
        }
        //３）生成されたリストを返却する。         
        
        log.exiting(STR_METHOD_NAME);
        return l_lisObjProduct;
    }
}
@
