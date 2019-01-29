head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会サービス実装クラス(WEB3MutualBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
Revesion History : 2005/10/23 黄建 (中訊) フィデリティ対応  
Revesion History : 2006/05/15 肖志偉(中訊) 仕様変更（モデル) :411,415
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル522
Revesion History : 2007/02/16 柴双紅 (中訊) 仕様変更・モデル541
Revesion History : 2007/03/26 徐大方 (中訊) 仕様変更・モデル552
Revesion History : 2007/04/09 唐性峰 (中訊) 仕様変更・モデル558
Revesion History : 2008/04/21 王志葵 (中訊) 仕様変更・モデル595
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.message.WEB3MutualBalanceReferenceComparator;
import webbroker3.mf.message.WEB3MutualBalanceReferenceDetailUnit;
import webbroker3.mf.message.WEB3MutualBalanceReferenceRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.service.delegate.WEB3MutualBalanceReferenceService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信残高照会サービスImpl)<BR>
 * 投信残高照会サービス実装クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualBalanceReferenceService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceServiceImpl.class);
   
   /**
    * 投信残高照会処理を行う。<BR>
    * <BR>
    * リクエストデータの型により、<BR>
    * 以下のメソッドを呼び分ける。<BR>
    * <BR>
    * ○投信残高照会残高合計リクエストの場合<BR>
    * 　@this.get残高合計()メソッドをコールする。<BR>
    * <BR>
    * ○投信残高照会リクエストの場合<BR>
    * 　@this.get残高照会()メソッドをコールする。<BR>
    * @@param l_request - リクエスト
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 41AD8E4E0243
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
       log.entering(STR_METHOD_NAME);

       if (l_request == null)
       {
           log.debug(" __parameter_error__");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       WEB3GenResponse l_response = null;
       if (l_request instanceof WEB3MutualBalanceReferenceRequest)
       {
           //this.get残高照会()
           l_response =
               this.getBalanceReference((WEB3MutualBalanceReferenceRequest) l_request);
       }
       else if (l_request instanceof WEB3MutualBalanceReferenceTotalRequest)
       {
           //this.get残高合計()
           l_response =
               this.getBalanceReferenceTotal((WEB3MutualBalanceReferenceTotalRequest) l_request);
       }
       else
       {
           log.debug(
               STR_METHOD_NAME
                   + " __Request "
                   + " WEB3MutualBalanceReferenceRequest "
                   + " と WEB3MutualBalanceReferenceTotalRequest以外である");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80018,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (get残高照会)<BR>
    * 投信残高照会処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「(投信)get残高照会」参照
    * @@param l_request - 投信残高照会リクエストオブジェクト
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceResponse
    * @@throws WEB3BaseException
    * @@roseuid 41AD8E4C031E
    */
   protected WEB3MutualBalanceReferenceResponse getBalanceReference(
       WEB3MutualBalanceReferenceRequest l_request) 
           throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "getBalanceReference()";
       log.entering(STR_METHOD_NAME);
       
       if (l_request == null)
       {
           log.debug(" __parameter_error__");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       // 1.1 リクエストデータの整合性をチェックする。
       l_request.validate();
       
       //投信・外貨MMF表示区分
       //※nullの場合、「0:投信のみ」とする
       if (l_request.mutualFrgnMmfDisplayDiv == null)
       {
           l_request.mutualFrgnMmfDisplayDiv =
               WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
       }

       // 1.2 validate注文受付可能
       WEB3MutualFundTradingTimeManagement.validateOrderAccept();
       
       // 1.3 補助口座オブジェクトを取得する
       SubAccount l_subAccount = this.getSubAccount();
       
       //拡張投信ポジションマネージャを取得する
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3MutualFundPositionManager l_web3MfPositionMgr =
           (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
               ProductTypeEnum.MUTUAL_FUND).getPositionManager();
       
       // 1.4 保有資産銘柄一覧を取得する 
       //  [引数] 
       //    補助口座：　@get補助口座()の戻り値
       //    投信･外貨MMF表示区分：　@リクエスト.投信･外貨MMF表示区分
       List l_lisAsserts = l_web3MfPositionMgr.getAssets(
           l_subAccount, l_request.mutualFrgnMmfDisplayDiv);
       
       // 1.5 ArrayListを生成する。
       List l_lisBalanceReferenceDetailUnits = new Vector();       

       int l_intAssertsLength = 0;
       if(l_lisAsserts != null)
       {
           l_intAssertsLength = l_lisAsserts.size();
       }
       
       // 　@－拡張投信銘柄マネージャを取得する
       WEB3MutualFundProductManager l_productManager =
           (WEB3MutualFundProductManager) l_finApp.getTradingModule(
               ProductTypeEnum.MUTUAL_FUND).getProductManager();
       
       WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
           (WEB3MutualFundOrderManagerReusableValidationsCheck) 
               MutualFundProductTypeOrderManagerReusableValidations.getInstance();
       
       // 1.6 getAssets()の戻り値の要素(=保有資産Params)数分Loop処理 
       for(int i = 0; i < l_intAssertsLength; i++)
       {
           MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAsserts.get(i);
           AssetRow l_assetRow = (AssetRow) l_mfAsset.getDataSourceObject();
           
           // 1.6.1 銘柄IDを取得する
           long l_lngProductId = l_assetRow.getProductId();

           WEB3MutualFundProduct l_mfProduct = null;
           try
           {
               // 1.6.2 銘柄オブジェクトを取得する  
               // [引数] 
               // 銘柄ID：　@処理対象の保有資産Params.getProductId()
               Product l_product = 
                   l_productManager.getProduct(l_lngProductId);
               
               // 1.6.3 拡張投信銘柄を取得する。
               // [引数] 
               // 銘柄Row：　@getProduct()の戻り値
               l_mfProduct = 
                   (WEB3MutualFundProduct) l_productManager.toProduct(
                       (MutualFundProductRow) l_product.getDataSourceObject());
           }
           catch (NotFoundException l_ex)
           {
               log.error("__NotFoundException__ 銘柄オブジェクトを取得できない!");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
           }  
           
           // 1.6.4 取引時間管理の銘柄コードを変更する。 
           // ［引数］ 
           //  銘柄コード：　@to銘柄()の戻り値.銘柄コード
           WEB3MutualFundTradingTimeManagement.resetProductCode(
               l_mfProduct.getProductCode());
           
           // 1.6.5 受付日時、日付ロールを再セットする。
           WEB3MutualFundTradingTimeManagement.setTimestamp();
           
           // 1.6.6 解約可能残高口数を取得する。 
           // [引数］ 
           // 補助口座：　@get補助口座()の戻り値 
           // 拡張投信銘柄：　@to銘柄()の戻り値
           //資産ID：　@getAssets()で取得した処理対象の保有資産テーブルParams.get資産ID
           double l_dblSellPossiblePositionQty = 
		       l_web3MfPositionMgr.calcSellPossiblePositionQty(
                   l_subAccount, 
                   l_mfProduct, 
                   l_mfAsset.getAssetId() + "");           
           
           // 1.6.7 解約中概算口数を取得する。 
           // [引数］ 
			//補助口座：　@get補助口座()の戻り値 
			//拡張投信銘柄：　@to銘柄()の戻り値 
			//税区分：　@getAssets()で取得した処理対象の保有資産テーブルParams.get税区分 
			//保有資産:：　@getAssets()で取得した処理対象の保有資産
           double l_dblSellingEstimatedQty = 
		       l_web3MfPositionMgr.calcSellingEstimatedQty(
		           l_subAccount, l_mfProduct, l_mfAsset.getTaxType(), l_mfAsset);
           
           // 1.6.8 投信タイプを取得する。
           MutualFundTypeEnum l_mfTypeEnum = l_mfProduct.getMutualFundType();
           
           // 1.6.9 注文受付締切時間を取得する。
           String l_strOrderCloseTime = 
               WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
           
           // 1.6.10 投信残高照会明細インスタンスを生成する。
           WEB3MutualBalanceReferenceDetailUnit l_mfBalanceReferenceDetailUnit = 
               new WEB3MutualBalanceReferenceDetailUnit();
           
           // 1.6.11 投信残高照会明細に以下のプロパティをセットする。
           //参考基準価額
           String l_strConstantValue = null;
           //乗換時単位口数
           String l_strSwitchingUnitQty = null;
           //乗換時最低口数
           String l_strSwitchingMinQty = null;
           //乗換時単位金額
           String l_strSwitchingUnitAmt = null;
           //乗換時最低金額
           String l_strSwitchingMinAmt = null;
           //投信銘柄.is外貨MMF()==falseの場合
           if (!l_mfProduct.isFrgnMmf())
           {
               //拡張投信銘柄.get解約価額
               l_strConstantValue = WEB3StringTypeUtility.formatNumber(l_mfProduct.getSellValue());
               //拡張投信銘柄.get単位口数（乗換）
               l_strSwitchingUnitQty = l_mfProduct.getSwitchingUnitQty() + "";
               //拡張投信銘柄.get最低口数（乗換）
               l_strSwitchingMinQty = l_mfProduct.getSwitchingMinQty() + "";
               //拡張投信銘柄.get単位金額（乗換）
               l_strSwitchingUnitAmt = l_mfProduct.getSwitchingUnitAmt() + "";
               //拡張投信銘柄.get最低金額（乗換）
               l_strSwitchingMinAmt = l_mfProduct.getSwitchingMinAmt() + "";
           }

           // ID　@＝　@処理対象の保有資産Params.保有資産ID
           l_mfBalanceReferenceDetailUnit.id = l_assetRow.getAssetId() + "";
           
		   // 銘柄コード　@＝　@投信銘柄.getProductId()
		   l_mfBalanceReferenceDetailUnit.mutualProductId = l_mfProduct.getProductId() + "";
           
           // 銘柄コード　@＝　@投信銘柄.getProductCode()
           l_mfBalanceReferenceDetailUnit.mutualProductCode = l_mfProduct.getProductCode();
           
           // 銘柄名　@＝　@投信銘柄.get銘柄名()
           l_mfBalanceReferenceDetailUnit.mutualProductName = l_mfProduct.getMutualProductName();
           
           //口座区分 = 処理対象の保有資産Params.税区分 == "一般"の場合、"0：一般"をセット。
           //　@　@　@処理対象の保有資産Params.税区分 == "特定"または"特定口座かつ源泉徴収"の場合、"1：特定"をセット。
           //　@　@　@以外、"2：その他"をセット。
           if(TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
           {
               l_mfBalanceReferenceDetailUnit.taxType = WEB3MFAccountDivDef.NORMAL;
           }
           else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType())
               || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetRow.getTaxType()))
           {
               l_mfBalanceReferenceDetailUnit.taxType = WEB3MFAccountDivDef.SPECIAL;
           }
           else
           {
               l_mfBalanceReferenceDetailUnit.taxType = WEB3MFAccountDivDef.OTHER;
           }
           
           // 解約可能口数　@＝　@calc解約可能残高口数()の戻り値
           l_mfBalanceReferenceDetailUnit.sellableQty = 
               WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
           
           // 解約中概算口数　@＝　@calc解約中概算口数()の戻り値
           l_mfBalanceReferenceDetailUnit.sellingEstimatedQty = 
               WEB3StringTypeUtility.formatNumber(l_dblSellingEstimatedQty);
           
           // 参考基準価額通貨コード	＝　@投信銘柄.get通貨コード()
           l_mfBalanceReferenceDetailUnit.constantValueCurrencyCode = 
               l_mfProduct.getCurrencyCode();

           // 参考基準価額 投信銘柄.is外貨MMF()==trueの場合、null。
           l_mfBalanceReferenceDetailUnit.constantValue = l_strConstantValue;

           // 基準価額適用日	＝　@投信銘柄.get基準価額適用日()
           l_mfBalanceReferenceDetailUnit.constantValueAppDate = 
               l_mfProduct.getConstantValueAppDate();
           
           if(((MutualFundTypeEnum.OTHER.equals(l_mfTypeEnum) ||
               MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum)) && 
              TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
              || l_mfProduct.isFrgnMmf())
           {
           	   // [（getMatualFundType()の戻り値 == "その他" or "国外"） 
               // and 処理対象の保有資産Params.税区分 == "一般"の場合] または
               //　@[（投信銘柄.is外貨MMF() == true）]
               // 個別元本　@＝　@null
               l_mfBalanceReferenceDetailUnit.indivPrincipal = null;
           }
           else
           {
               // 個別元本　@＝　@保有資産テーブルParams.getBookValue()
               // （小数点以下四捨五入）
			   l_mfBalanceReferenceDetailUnit.indivPrincipal = 
				   WEB3StringTypeUtility.formatNumber(
				       new BigDecimal(Double.toString(
				           l_assetRow.getBookValue())).setScale(
				               0,BigDecimal.ROUND_HALF_UP).doubleValue());
           }
           
		   // 評価額　@＝　@拡張投信ポジションマネージャ.calc評価額()
		   double l_dblMarketValue = 
			   l_web3MfPositionMgr.calcMarketValue(
                   l_subAccount, l_mfProduct, l_mfAsset.getAssetId() + "");
           
		   l_mfBalanceReferenceDetailUnit.marketValue = 
			   WEB3StringTypeUtility.formatNumber(l_dblMarketValue);
		      
           if(((MutualFundTypeEnum.OTHER.equals(l_mfTypeEnum) ||
               MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum)) && 
              TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
              || l_mfProduct.isFrgnMmf())
           {
               // [（getMatualFundType()の戻り値 == "その他" or "国外"） 
               // and 処理対象の保有資産Params.税区分 == "一般"の場合]または
               //　@[（投信銘柄.is外貨MMF() == true）]
               // 評価損益　@＝　@null
               l_mfBalanceReferenceDetailUnit.appraisalProfitLoss = null;
           }
           else
           {
               // 上記以外
               // 評価損益　@＝　@拡張投信ポジションマネージャ.calc評価損益()
               // [calc評価額()、calc評価損益()にセットするパラメータ]
               //   補助口座：　@get補助口座()の戻り値
               //   拡張投信銘柄：　@to銘柄()の戻り値

               double l_dblAppraisalProfitLoss = 
                   l_web3MfPositionMgr.calcAppraisalProfitLoss(
                       l_subAccount, l_mfProduct, l_mfAsset.getAssetId() + "");
               
               l_mfBalanceReferenceDetailUnit.appraisalProfitLoss = 
                   WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitLoss);
           }

           // 解約時単位口数　@＝　@投信銘柄.get単位口数(解約)()
           l_mfBalanceReferenceDetailUnit.sellUnitQty = l_mfProduct.getSellUnitQty() + "";
           
           // 解約時最低口数　@＝　@投信銘柄.get最低口数(解約)()
           l_mfBalanceReferenceDetailUnit.sellMinQty = l_mfProduct.getSellMinQty() + "";
           
           // 解約時単位金額　@＝　@投信銘柄.get単位金額(解約)()
           l_mfBalanceReferenceDetailUnit.sellUnitAmt = l_mfProduct.getSellUnitAmt() + "";
           
           // 解約時最低金額　@＝　@投信銘柄.get最低金額(解約)()
           l_mfBalanceReferenceDetailUnit.sellMinAmt = l_mfProduct.getSellMinAmt() + "";

           // 乗換時単位口数 投信銘柄.is外貨MMF()==trueの場合、null。
           l_mfBalanceReferenceDetailUnit.switchingUnitQty = l_strSwitchingUnitQty;

           // 乗換時最低口数 投信銘柄.is外貨MMF()==trueの場合、null。
           l_mfBalanceReferenceDetailUnit.switchingMinQty = l_strSwitchingMinQty;

           // 乗換時単位金額 投信銘柄.is外貨MMF()==trueの場合、null。
           l_mfBalanceReferenceDetailUnit.switchingUnitAmt = l_strSwitchingUnitAmt;

           // 乗換時最低金額 投信銘柄.is外貨MMF()==trueの場合、null。
           l_mfBalanceReferenceDetailUnit.switchingMinAmt = l_strSwitchingMinAmt;

           // 注文受付締切時間　@＝　@
           // get注文受付締切時間()の戻り値の１秒後の時間"HHMMSS"を"HH:MM"に編集してセット。
           log.debug("l_strOrderCloseTime" + l_strOrderCloseTime);
           
           Date l_datOrderCloseTime = 
               WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
           l_strOrderCloseTime = 
               WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss"); 
           l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
               + WEB3GentradeTimeDef.STR_COLON
               + l_strOrderCloseTime.substring(2, 4);

           l_mfBalanceReferenceDetailUnit.orderCloseTime = l_strOrderCloseTime;
           
           // 解約可能区分　@＝　@null
           l_mfBalanceReferenceDetailUnit.sellPossType = null;
           
           // 買取可能区分　@＝　@null
           l_mfBalanceReferenceDetailUnit.buyPossType = null;
           
           // 乗換可能区分　@＝　@null
           l_mfBalanceReferenceDetailUnit.switchingPossType = null;
           
           // 買付可能区分　@＝　@null
           l_mfBalanceReferenceDetailUnit.buyPosDiv = null;
           
           //投信銘柄カテゴリーコード   ＝　@投信銘柄.getカテゴリーコード()
           MutualFundProductRow l_mfProductRow = 
               (MutualFundProductRow)l_mfProduct.getDataSourceObject();
           l_mfBalanceReferenceDetailUnit.categoryCode = l_mfProductRow.getCategoryCode();

           //・円転基準価額
           //　@　@1）投信銘柄.get通貨コード( )がT0の場合 または
           //　@      投信銘柄.is外貨MMF()がtrueの場合
           //　@　@　@　@nullをセットする。
           //　@　@2）投信銘柄.get通貨コード( )がT0でない場合
           //　@　@　@　@拡張投信銘柄.get円転基準価額(WEB3MFProcessDivDef.解約)をセットする。
           if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mfProduct.getCurrencyCode())
               || l_mfProduct.isFrgnMmf())
           {
               l_mfBalanceReferenceDetailUnit.yenConstantValue = null;
           }
           else
           {
               l_mfBalanceReferenceDetailUnit.yenConstantValue = 
                   l_mfProduct.getYenConstantValue(WEB3MFProcessDivDef.SELL);
           }   

           //参考レート
           //  1）投信銘柄.get通貨コード( )がT0の場合
           //　@　@　@ nullをセットする。
           //　@2）投信銘柄.get通貨コード( )がT0でない場合
           //      2-1) 投信銘柄.is外貨MMF()がtrueの場合
           //　@　@　@   投信銘柄.get外貨MMF為替レート()の戻り値外貨MMF為替レートParamsのTTB
           //　@　@　@   をセットする。（小数第３位で四捨五入）
           //      2-2) 投信銘柄.is外貨MMF()がfalseの場合
           //　@　@　@   投信銘柄.get為替レート()の戻り値為替レートParamsのTTB / 同為替レート計算単位
           //　@　@   をセットする。（小数第３位で四捨五入）
           if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mfProduct.getCurrencyCode()))
           {
               l_mfBalanceReferenceDetailUnit.referenceRate = null;
           }
           else
           {
               if (l_mfProduct.isFrgnMmf())
               {
                   BigDecimal l_bdTtBuyingRate =
                       new BigDecimal(l_mfProduct.getFrgnMmfExchangeRate().getTtBuyingRate() + "");
                   l_mfBalanceReferenceDetailUnit.referenceRate =
                       WEB3StringTypeUtility.formatNumber(
                           l_bdTtBuyingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
               }
               else
               {
                   BigDecimal l_bdTtBuyingRate = 
                       new BigDecimal(l_mfProduct.getExchangeRate().getTtBuyingRate() + "");
                   BigDecimal l_bdExchangeCalcUnit = 
                       new BigDecimal(l_mfProduct.getExchangeRate().getExchangeCalcUnit() + "");
                   l_mfBalanceReferenceDetailUnit.referenceRate =
                       WEB3StringTypeUtility.formatNumber(
                           l_bdTtBuyingRate.divide(
                               l_bdExchangeCalcUnit,
                               2,
                               BigDecimal.ROUND_HALF_UP).doubleValue());
               }
           }

           //参考レート確定日
           // 　@　@1）投信銘柄.get通貨コード( )がT0の場合
           //　@　@　@ nullをセットする。
           //　@　@2）投信銘柄.get通貨コード( )がT0でない場合
           //       2-1) 投信銘柄.is外貨MMF ＝ trueの場合            
           //            投信銘柄.get外貨MMF為替レート()の戻り値
           //            外貨MMF為替レートParamsの為替レート確定日をセットする。 
           //       2-2) 投信銘柄.is外貨MMF ＝ falseの場合
           //            投信銘柄.get為替レート()の戻り値
           //            為替レートParamsの為替レート確定日をセットする。
           if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mfProduct.getCurrencyCode())) 
           {
               l_mfBalanceReferenceDetailUnit.referenceRateFixedDay = null;
           }
           else
           {
               if (l_mfProduct.isFrgnMmf())
               {
                   l_mfBalanceReferenceDetailUnit.referenceRateFixedDay =
                       l_mfProduct.getFrgnMmfExchangeRate().getExecTimestamp();
               }
               else
               {
                   l_mfBalanceReferenceDetailUnit.referenceRateFixedDay =
                       l_mfProduct.getExchangeRate().getExecTimestamp();
               }
           }

           //未収分配金
           //  1) 投信銘柄.is外貨MMF() == trueの場合、拡張投信ポジションマネージャ.get未収分配金()の戻り値をセット。
           //      [get未収分配金の引数]
           //      資産ID:処理対象の保有資産Params.保有資産ID
           //      拡張投信銘柄:取得した投信銘柄オブジェクト
           //  2) 投信銘柄.is外貨MMF() == falseの場合、nullをセット。
           if (l_mfProduct.isFrgnMmf())
           {
               l_mfBalanceReferenceDetailUnit.unreceivedDistribution =
                   WEB3StringTypeUtility.formatNumber(
                       l_web3MfPositionMgr.getUnreceiveDist(
                           l_mfAsset.getAssetId() + "",
                           l_mfProduct));
           }
           else
           {
               l_mfBalanceReferenceDetailUnit.unreceivedDistribution = null;
           }

           //　@外貨MMFフラグ  ＝  投信銘柄.is外貨MMF()
           l_mfBalanceReferenceDetailUnit.frgnMmfFlag = l_mfProduct.isFrgnMmf();

           //　@解約時外貨単位金額 ＝　@投信銘柄.get外貨単位金額(解約)()
           if (!l_mfProductRow.getFrgnSellUnitAmtIsNull())
           {
               l_mfBalanceReferenceDetailUnit.sellFrgnUnitAmt =
                   l_mfProduct.getFrgnSellUnitAmt() + "";
           }
           //　@解約時外貨最低金額 ＝　@投信銘柄.get外貨最低金額(解約)()
           if (!l_mfProductRow.getFrgnSellMinAmtIsNull())
           {
               l_mfBalanceReferenceDetailUnit.sellFrgnMinAmt =
                   l_mfProduct.getFrgnSellMinAmt() + "";
           }

           //指定方法@（解約） = 投信銘柄.get指定方法@(解約)
           l_mfBalanceReferenceDetailUnit.sellSelectable =
               l_mfProduct.getSellSelectable();

           //指定方法@（乗換） = 投信銘柄.get指定方法@(乗換)
           l_mfBalanceReferenceDetailUnit.switchingSelectable =
               l_mfProduct.getSwitchingSelectable();

           //1.6.12 ( * 各可能区分セット)====================start==================
           // 売付可能チェック
           // 1.1 calc解約可能残高口数()の戻り値 == 0の場合
           if(l_dblSellPossiblePositionQty == 0)
           {
               // 1.1.1 以下の区分に"全部解約中"をセットする。
               // ・投信残高照会明細.解約可能区分
               l_mfBalanceReferenceDetailUnit.sellPossType = WEB3RemarkDivDef.All_SELLING;
               
               // ・投信残高照会明細.買取可能区分
               l_mfBalanceReferenceDetailUnit.buyPossType = WEB3RemarkDivDef.All_SELLING;
               
               // ・投信残高照会明細.乗換可能区分
               l_mfBalanceReferenceDetailUnit.switchingPossType = WEB3RemarkDivDef.All_SELLING;
           }
           
           try
           {
               // 1.2 validate緊急停止(拡張投信銘柄, String) 
               // [引数] 
               // 拡張投信銘柄：　@to銘柄()の戻り値 
               // 処理区分：　@"解約"
               l_validationsCheck.validateEmergencyStop(
                   l_mfProduct, 
                   WEB3ProcessDivDef.SELL);
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("解約緊急停止エラー " + l_ex);
               
               // 1.3 解約のvalidate緊急停止()が例外をスローした場合
               // 1.3.1 以下の区分に"緊急停止中"をセットする。
               // ・投信残高照会明細.解約可能区分
               l_mfBalanceReferenceDetailUnit.sellPossType = WEB3RemarkDivDef.EMERGENCY_STOP;
               
               // ・投信残高照会明細.買取可能区分
               l_mfBalanceReferenceDetailUnit.buyPossType = WEB3RemarkDivDef.EMERGENCY_STOP;
           }
           
           try
           {
               // 1.4 銘柄の乗換緊急停止チェックを実施する。 
               // [引数] 
               // 拡張投信銘柄：　@to銘柄()の戻り値 
               // 処理区分：　@"乗換" 
               l_validationsCheck.validateEmergencyStop(
                   l_mfProduct, 
                   WEB3ProcessDivDef.SWITCHING);
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("乗換緊急停止エラー " + l_ex);
               
               // 1.5 乗換のvalidate緊急停止()が例外をスローした場合
               // 1.5.1 以下の区分に"緊急停止中"をセットする。
               // 　@・投信残高照会明細.乗換可能区分
               l_mfBalanceReferenceDetailUnit.switchingPossType = WEB3RemarkDivDef.EMERGENCY_STOP;
           }

           // 1.6 注文受付トランザクションを再セットする。 
           // [引数] 
           // 注文受付トランザクション：　@"売付(新規建売)"
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
           
           try
           {
               // 1.7 validate注文受付可能
               WEB3MutualFundTradingTimeManagement.validateOrderAccept();
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("注文受付可能エラー" + l_ex);
               // 1.8 validate注文受付可能()が例外をスローした場合
               // 1.8.1 以下の区分に"受付時間外"をセットする。
               // ・投信残高照会明細.解約可能区分
               l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
               
               // ・投信残高照会明細.買取可能区分
               l_mfBalanceReferenceDetailUnit.buyPossType = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
               
               // ・投信残高照会明細.乗換可能区分
               l_mfBalanceReferenceDetailUnit.switchingPossType = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
           }
           
           // 1.9 投信発注日を取得する
           Date l_datMfOrderBizDate = 
               WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                      
           //1.10 is解約乗換可能(Date)
           // [引数] 
           // 発注日：　@　@
           //     ※特定日取引本格対応されるまでの暫定対応
           //     取得した拡張投信銘柄.getProductIdが3303910181800または3303911181800の場合
           //　@       取引時間管理.get発注日()の戻り値
           //     それ以外の場合
           //　@       get投信発注日()の戻り値
           Date l_datArgIsSellSwitchingPossible = null;
           if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
           {
        	   l_datArgIsSellSwitchingPossible = WEB3GentradeTradingTimeManagement.getOrderBizDate();
           }
           else
           {
        	   l_datArgIsSellSwitchingPossible = l_datMfOrderBizDate;
           }
           boolean l_blnIsSellSwitchingPossible = 
               l_mfProduct.isSellSwitchingPossible(l_datArgIsSellSwitchingPossible);
           
           //投信銘柄.is国内投信()    
           boolean l_blnIsDomesticFund = l_mfProduct.isDomesticFund();

           //拡張投信銘柄.is外国投信()
           boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

           //拡張投信銘柄.isFWF()
           boolean l_blnIsFWF = l_mfProduct.isFWF();

           // 1.11  (*5)is解約乗換可能()の戻り値  == falseの場合 
           if(!l_blnIsSellSwitchingPossible)
           {
               // 1.11.1 is解約乗換可能()の戻り値  == falseの場合
               // 以下の区分に"取引停止中"をセットする
               // ・投信残高照会明細.解約可能区分
               l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.HANDLING_DISABLE;
               
               // ・投信残高照会明細.乗換可能区分
               l_mfBalanceReferenceDetailUnit.switchingPossType = 
                   WEB3RemarkDivDef.HANDLING_DISABLE;
               
               // ・投信残高照会明細.買取可能区分
			   l_mfBalanceReferenceDetailUnit.buyPossType = 
				   WEB3RemarkDivDef.HANDLING_DISABLE;
           }
           // 1.12 (*6)(*5)以外 AND 拡張投信銘柄.is国内投信()の戻り値 == true の場合
           else if(l_blnIsSellSwitchingPossible && l_blnIsDomesticFund)
           {
               // 1.12 (*5)以外の場合
               // 1.12.1 買取請求が可能であるか判別する。 
               // [引数] 
               // 発注日：　@get投信発注日()の戻り値 
               // 補助口座：　@get補助口座()の戻り値 
               // 拡張投信銘柄：　@to銘柄()の戻り値
               boolean l_blnIsBuyPossible = l_validationsCheck.isBuyingRequestPossible(
                   l_datMfOrderBizDate, 
                   l_subAccount, 
                   l_mfProduct);
               
               if(!l_blnIsBuyPossible)
               {
                   // 1.12.2 is買取請求可能()の戻り値 == falseの場合
                   // 以下の区分に"取引停止中"をセットする                   
                   // ・投信残高照会明細.買取可能区分
                   l_mfBalanceReferenceDetailUnit.buyPossType = 
                       WEB3RemarkDivDef.HANDLING_DISABLE;
               }
           }
           //(*8)(*5)以外 AND (拡張投信銘柄.is外国投信()の戻り値 == true OR 
           // 拡張投信銘柄.isFWF()の戻り値 == true )の場合
           else if (l_blnIsForeignFund || l_blnIsFWF)
           {
               // 1.13.1以下の区分に"取引不可(買付停止中)"をセットする
               // ・投信残高照会明細.解約可能区分
			   l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.HANDLING_DISABLE;
           }

           //(*9)(*5)以外 AND (拡張投信銘柄.is外貨MMF()の戻り値 == true)の場合
           else if (l_mfProduct.isFrgnMmf())
           {
               l_mfBalanceReferenceDetailUnit.buyPossType =
                   WEB3RemarkDivDef.HANDLING_DISABLE;
           }

		   // 1.13 1.14 乗換可能チェック
		   // is乗換可能(　@)をコールし、戻り値がfalseの場合
		   // 乗換可能区分に"取扱不可"をセットする。
		   log.debug("投信銘柄.is乗換可能(　@) = " + l_mfProduct.isSwitchingAble());
		   if(!l_mfProduct.isSwitchingAble())
		   {
			   l_mfBalanceReferenceDetailUnit.switchingPossType = 
			   	   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
		   }
           
           // 1.15 isシステム取扱( ) WebⅢで取扱可能な投信銘柄か判別する。
           boolean l_blnIsSystemHandle = l_mfProduct.isSystemHandling();
           
           //1.16  (*8)isシステム取扱() == falseの場合
           if(!l_blnIsSystemHandle)
           {
			   log.debug("システム取扱不可");
               // 1.16.1 isシステム取扱() == falseの場合
               // 以下の区分に"取扱不可"をセットする。
               // ・投信残高照会明細.解約可能区分
               l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
               
               // ・投信残高照会明細.買取可能区分
               l_mfBalanceReferenceDetailUnit.buyPossType = 
                   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
               
               // ・投信残高照会明細.乗換可能区分
               l_mfBalanceReferenceDetailUnit.switchingPossType = 
                   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
           }
           
           //1.17 投信銘柄が買付可能か判別する。 
           // [引数] 
           // 発注日：　@get投信発注日()の戻り値
           boolean l_blnAcquiredPossible = 
               l_mfProduct.isAcquiredPossible(l_datMfOrderBizDate);
           
           //1.18 投信銘柄に買付制限があるか判別する。
           boolean l_blnAcquiredDeregExistence = 
               l_mfProduct.isAcquiredDeregExistence();
           
           // 1.19 is買付可能() == false または is買付制限有り() == trueの場合
           if(!l_blnAcquiredPossible || l_blnAcquiredDeregExistence)
           {
               // 1.19.1 以下の区分に"買付停止中"をセットする。
               // ・投信残高照会明細.買付可能区分
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.HANDLING_DISABLE;
           }
           
           // 1.20 注文受付トランザクションを再セットする。 
           // [引数] 
           // 注文受付トランザクション：　@"買付(新規建買)"
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
           
           try
           {
               // 1.21 validate注文受付可能
               WEB3MutualFundTradingTimeManagement.validateOrderAccept();
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("注文受付可能エラー" + l_ex);
               // 1.22  (*10)validate注文受付可能()が例外をスローした場合
               // 1.22.1 以下の区分に"受付時間外"をセットする。
               // ・投信残高照会明細.買付可能区分
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
           }
 
           try
           {
               // 1.23  銘柄の買付緊急停止チェックを実施する。 
               // [引数] 
               // 拡張投信銘柄：　@to銘柄()の戻り値 
               // 処理区分：　@"買付"  
               l_validationsCheck.validateEmergencyStop(
                       l_mfProduct, 
                       WEB3ProcessDivDef.BUY);
           }
           catch(WEB3BaseException l_ex)
           {
               //1.24  (*11)validate緊急停止()が例外をスローした場合
               log.debug("買付緊急停止エラー " + l_ex);
               
               // 1.24.1 validate緊急停止()が例外をスローした場合
               // 以下の区分に"緊急停止中"をセットする。
               // 　@・投信残高照会明細.買付可能区分
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.EMERGENCY_STOP;
           }
           
           //1.25 is募集可能(Date)(拡張投信銘柄::is募集可能)
           //投信銘柄が募集可能か判別する。 

           //[引数] 
           //発注日：　@get投信発注日()の戻り値
           boolean l_blnIsRecruitPoss = 
               l_mfProduct.isRecruitPossible(l_datMfOrderBizDate);
           
           //1.26 (*12)is募集可能() == true の場合
           if (l_blnIsRecruitPoss)
           {
               //1.26.1 (*)プロパティセット
               //(*)以下の区分に"募集期間中"をセットする。
               //・投信残高照会明細.買付可能区分
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.RECRUIT_BETWEEN;
           }
           //1.27 reset注文受付トランザクション(注文受付トランザクション : String)
           //注文受付トランザクションを再セットする。 
           //[引数] 
           //注文受付トランザクション：　@"募集" 
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.RECRUIT);
           
           //1.28 validate注文受付可能( )
           //1.29 (*13)validate注文受付可能()が例外をスローした場合
           try
           {
               WEB3MutualFundTradingTimeManagement.validateOrderAccept();
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("注文受付可能エラー" + l_ex);
               //1.29.1 (*)プロパティセット
               // (*)以下の区分に"受付時間外"をセットする。
               //・投信残高照会明細.買付可能区分
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
           }
           //1.30 validate緊急停止(拡張投信銘柄, String)
           //銘柄の募集緊急停止チェックを実施する。 
           //[引数] 
           //拡張投信銘柄：　@to銘柄()の戻り値 
           //処理区分：　@"募集" 
           try
           {
               l_validationsCheck.validateEmergencyStop(
                   l_mfProduct, 
                   WEB3ProcessDivDef.RECRUIT);          
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("買付緊急停止エラー " + l_ex);  
               //1.31 (*14)validate緊急停止()が例外をスローした場合
               //1.31.1  (*)プロパティセット
               //(*)以下の区分に"緊急停止中"をセットする。
               //　@・投信残高照会明細.買付可能区分 
               
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.EMERGENCY_STOP;
           }
           
           //1.32 reset注文受付トランザクション(注文受付トランザクション)
           //注文受付トランザクションを再セットする。 
           //[引数] 
           //注文受付トランザクション：　@"照会"
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.REFERENCE);
           
           //1.33 isシステム取扱( )
           l_blnIsSystemHandle = l_mfProduct.isSystemHandling();           
           
		   //1.34 (*15)isシステム取扱() == falseの場合
		   if(!l_blnIsSystemHandle)
		   {
			   log.debug("システム取扱不可");
               //1.34.1 (*)以下の区分に"取扱不可"をセットする。
			   //・投信残高照会明細.買付可能区分
			   l_mfBalanceReferenceDetailUnit.buyPosDiv =
			      WEB3RemarkDivDef.HANDLING_WEB_DISABLE; 
		   }          
           //各可能区分のセットを行う。=====================end===================
           
           //1.6.13
           l_lisBalanceReferenceDetailUnits.add(l_mfBalanceReferenceDetailUnit);
       }
       
       // 1.7 投信残高照会明細の配列を生成する。
       int l_intListSize = l_lisBalanceReferenceDetailUnits.size();
       WEB3MutualBalanceReferenceDetailUnit[] l_arrBalanceReferenceDetailUnits = 
           new WEB3MutualBalanceReferenceDetailUnit[l_intListSize];
       l_lisBalanceReferenceDetailUnits.toArray(l_arrBalanceReferenceDetailUnits);
       
       // 1.8 作成した残高照会明細をソートする。 
       // [引数] 
       //     投信残高照会明細：　@toArray()の戻り値 
       //     ソートキー：　@リクエストデータ.ソートキー
       this.sortBalanceReferenceDetailUnit(
           l_arrBalanceReferenceDetailUnits,
           l_request.sortKeys);
       
       //1.9 get投信銘柄カテゴリーリスト(String)
       //投信銘柄カテゴリーコードを検索する。 
       //[引数] 
       //証券会社コード： 補助口座.getInstitution().getInstitutionCode()の戻り値 
       List l_lisProductCategory = 
           l_productManager.getMutualFundProductCategoryList(
               l_subAccount.getInstitution().getInstitutionCode()
               );
       
       //1.10 create投信銘柄カテゴリー一覧(List)
       //投信銘柄カテゴリー一覧を作成する。
       //[引数] 
       //銘柄カテゴリー一覧： get投信銘柄カテゴリーリスト()の戻り値 
       WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits =
           l_productManager.createMutualFundProductCategoryList(
               l_lisProductCategory);       
       
       // 1.11 レスポンスデータを生成する。
       WEB3MutualBalanceReferenceResponse l_response = 
           (WEB3MutualBalanceReferenceResponse) l_request.createResponse();
       
       // 1.12 レスポンスデータにプロパティをセットする。
       
       int l_intPageSize = Integer.parseInt(l_request.pageSize);
       int l_intPageIndex = Integer.parseInt(l_request.pageIndex);  
       
       WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
           l_arrBalanceReferenceDetailUnits, 
           l_intPageIndex, 
           l_intPageSize);
       
       // レスポンス.残高照会明細 ＝ 表示対象行の投信残高照会明細の配列
       l_response.balanceReference = 
           (WEB3MutualBalanceReferenceDetailUnit[]) l_pageIndexInfo.getArrayReturned(
               WEB3MutualBalanceReferenceDetailUnit.class);
       
       // レスポンス.総ページ数 ＝ 総レコード数 / リクエストデータ.ページ内表示行数
       // 			　@　@		　@※計算結果は小数点以下一位を切り上げた整数値とする。
       l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
           
       // レスポンス.総レコード数 ＝ toArray()の戻り値.length
       l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + ""; 
       
       // レスポンス.表示ページ番号 ＝ toIndex /　@リクエストデータ.ページ内表示行数
       // 		　@　@		　@※計算結果は小数点以下一位を切り上げた整数値とする。
       l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
       
       //レスポンス.投信銘柄カテゴリー一覧 ＝create投信銘柄カテゴリー一覧()の戻り値
       l_response.categoryList = l_mfProductCategoryUnits;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (get残高合計)<BR>
    * 投信残高合計処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「(投信)get残高合計」参照
    * @@param l_request - 投信残高照会残高合計リクエストオブジェクト
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse
    * @@throws WEB3BaseException
    * @@roseuid 41AD8E4C033D
    */
   protected WEB3MutualBalanceReferenceTotalResponse getBalanceReferenceTotal(
           WEB3MutualBalanceReferenceTotalRequest l_request) 
               throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "getBalanceReferenceTotal()";
       log.entering(STR_METHOD_NAME);
       
       if (l_request == null)
       {
           log.debug(" __parameter_error__");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       //投信・外貨MMF表示区分
       //※nullの場合、「0:投信のみ」とする
       if (l_request.mutualFrgnMmfDisplayDiv == null)
       {
           l_request.mutualFrgnMmfDisplayDiv =
               WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
       }

       //１）　@validate注文受付可能
       WEB3MutualFundTradingTimeManagement.validateOrderAccept();
       
       // 2) 補助口座オブジェクトを取得する
       SubAccount l_subAccount = this.getSubAccount();
       
       //拡張投信ポジションマネージャを取得する
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3MutualFundPositionManager l_web3MfPositionMgr =
           (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                   ProductTypeEnum.MUTUAL_FUND).getPositionManager();
       
       // 3)保有資産銘柄一覧を取得する 
       //  [引数] 
       //    補助口座：　@get補助口座()の戻り値 
       //    投信･外貨MMF表示区分：　@リクエスト.投信･外貨MMF表示区分
       List l_lisAsserts = l_web3MfPositionMgr.getAssets(
               l_subAccount,
               l_request.mutualFrgnMmfDisplayDiv);
       
       // 4) レスポンスデータを生成する。
       WEB3MutualBalanceReferenceTotalResponse l_response = 
           (WEB3MutualBalanceReferenceTotalResponse) l_request.createResponse();
       
       // 5) getAssets()の戻り値の要素(=保有資産Params)数分Loop処理
       int l_intAssertsLength = 0;
       if(l_lisAsserts != null)
       {
           l_intAssertsLength = l_lisAsserts.size();
       }
       
       // 　@－拡張投信銘柄マネージャを取得する
       WEB3MutualFundProductManager l_productManager =
           (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                   ProductTypeEnum.MUTUAL_FUND).getProductManager();
       
       // レスポンスデータ.一般口座評価額合計
       double l_dblNormalAccountTotalAsset = 
           Double.parseDouble(l_response.normalAccountTotalAsset);
       // レスポンスデータ.一般口座評価損益合計
       double l_dblNormalAccountTotalAppraisalProfitLoss = 
           Double.parseDouble(l_response.normalAccountTotalAssetProfitLoss);
       // レスポンスデータ.特定口座評価額合計
       double l_dblCapitalGainTotalAsset = 
           Double.parseDouble(l_response.capitalGainTotalAsset);
       // レスポンスデータ.特定口座評価損益合計
       double l_dblCapitalGainTotalAppraisalProfitLoss = 
           Double.parseDouble(l_response.capitalGainTotalAssetProfitLoss);
       // レスポンスデータ.外貨MMF評価額合計
       double l_dblFrgnMmfTotalAsset =
           Double.parseDouble(l_response.frgnMmfTotalAsset);
       
       for(int i = 0; i < l_intAssertsLength; i++)
       {
           MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAsserts.get(i);
           AssetRow l_assetRow = (AssetRow) l_mfAsset.getDataSourceObject();

           WEB3MutualFundProduct l_mfProduct = null;
           try
           {
               // 5 -1 )銘柄オブジェクトを取得する 
               // [引数] 
               // 銘柄ID：　@処理対象の保有資産Params.getProductId()
               Product l_product = 
                   l_productManager.getProduct(l_assetRow.getProductId());
               
               // 5 - 2) 拡張投信銘柄を取得する。 
               // [引数] 
               // 銘柄Row：　@getProduct()の戻り値 
               l_mfProduct = 
                   (WEB3MutualFundProduct) l_productManager.toProduct(
                       (MutualFundProductRow) l_product.getDataSourceObject());
           }
           catch (NotFoundException l_ex)
           {
               log.error("__NotFoundException__ 銘柄オブジェクトを取得できない!");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
           } 
           
           // 5 - 3) 投信タイプを取得する
           MutualFundTypeEnum l_mfTypeEnum = l_mfProduct.getMutualFundType();
           
           // 5 - 4) 評価額を取得する。 
           // [引数] 
           // 補助口座：　@get補助口座()の戻り値 
           // 拡張投信銘柄：　@to銘柄()の戻り値
           double l_dblMarketValue = 
               l_web3MfPositionMgr.calcMarketValue(l_subAccount, l_mfProduct,l_mfAsset.getAssetId() + "");

           //(*)評価損益算出処理対象外チェック
		   //以下のいずれかに該当する場合、
		   //  評価損益は計算しないので、calc評価損益()をコールせず、
		   //  評価損益の計算結果に 0 を設定する。
		   //    ①@拡張投信銘柄.is外貨MMF()==falseかつ
		   //     getMatualFundType()の戻り値 == "国外" かつ
		   //     処理対象の保有資産Params.税区分 == "一般"の場合。
		   //    ②拡張投信銘柄.is外貨MMF()==trueの場合。
           double l_dblAppraisalProfitLoss = 0;
           if (l_mfProduct.isFrgnMmf()
               || (MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum)
               && TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType())))
           {
               l_dblAppraisalProfitLoss = 0;
           }
           else
           {
               //[引数]
               //補助口座：　@get補助口座()の戻り値
               //拡張投信銘柄：　@to銘柄()の戻り値 
               //資産ID：　@getAssets()で取得した処理対象の保有資産テーブルParams.get資産ID
               l_dblAppraisalProfitLoss =
                   l_web3MfPositionMgr.calcAppraisalProfitLoss(
                       l_subAccount, l_mfProduct, l_assetRow.getAssetId() + "");
           }

           //拡張投信銘柄.is外貨MMF()==falseの場合
           if (!l_mfProduct.isFrgnMmf())
           {
               // 5- 7 ) )処理対象の保有資産Params.税区分 == "一般"の場合
               if(TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
               {
                   // 5 - 7 - 1 ) 一般口座評価額合計、一般口座評価損益合計に加算
                   // レスポンスデータ.一般口座評価額合計 += calc評価額()の戻り値
                   // レスポンスデータ.一般口座評価損益合計 += calc評価損益()の戻り値
                   l_dblNormalAccountTotalAsset += l_dblMarketValue;
                   l_dblNormalAccountTotalAppraisalProfitLoss += l_dblAppraisalProfitLoss;
               }
               else
               {
                   // 5 - 7 - 2) 処理対象の保有資産Params.税区分 == ("特定" or "特定口座かつ源泉徴収")の場合
                   if(TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()) || 
                           TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetRow.getTaxType()))
                   {
                       // 5 - 7 - 1 ) 一般口座評価額合計、一般口座評価損益合計に加算
                       // レスポンスデータ.特定口座評価額合計 += calc評価額()の戻り値
                       // レスポンスデータ.特定口座評価損益合計 += calc評価損益()の戻り値
                       l_dblCapitalGainTotalAsset += l_dblMarketValue;
                       l_dblCapitalGainTotalAppraisalProfitLoss += l_dblAppraisalProfitLoss;
                   }
               }
           }
           else
           {
               //外貨MMF評価額合計に加算
               //レスポンスデータ.外貨MMF評価額合計 += calc評価額()の戻り値
               l_dblFrgnMmfTotalAsset += l_dblMarketValue;
           }
       }
       
       // レスポンスデータ.一般口座評価額合計
       l_response.normalAccountTotalAsset =
           WEB3StringTypeUtility.formatNumber(l_dblNormalAccountTotalAsset) ;
       //  レスポンスデータ.一般口座評価損益合計
       l_response.normalAccountTotalAssetProfitLoss = 
           WEB3StringTypeUtility.formatNumber(l_dblNormalAccountTotalAppraisalProfitLoss);
       // レスポンスデータ.特定口座評価額合計
       l_response.capitalGainTotalAsset = 
           WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTotalAsset);
       // レスポンスデータ.特定口座評価損益合計
       l_response.capitalGainTotalAssetProfitLoss = 
           WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTotalAppraisalProfitLoss);
       // レスポンスデータ.外貨MMF評価額合計
       l_response.frgnMmfTotalAsset =
           WEB3StringTypeUtility.formatNumber(l_dblFrgnMmfTotalAsset);
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (sort残高照会明細)<BR>
    * 指定されたソートキー、昇降順に基づいて<BR>
    * 投信残高照会明細のソートを行う。 <BR>
    * <BR>
    * １)　@ArrayListを作成 <BR>
    * <BR>
    * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
    * 　@２－１）ソートキー.キー項目の値に対応する<BR>
    *       比較項目のComparatorを生成し、<BR>
    * 　@　@　@ArrayListに追加する。 <BR>
    * <BR>
    * 　@　@　@①@投信残高照会Comparatorを生成する。<BR>
    * <BR>
    * 　@　@　@　@[コンストラクタにセットするパラメータ] <BR>
    * 　@　@　@　@　@orderBy： ソートキー.昇順／降順<BR>
    * 　@　@　@　@　@比較項目：　@ソートキー.キー項目<BR>
    * <BR>
    * 　@　@　@②ArrayListに生成したComparatorを追加する。<BR>
    * <BR>
    * ３)ソート<BR>
    * 　@WEB3ArraysUtility.sort()メソッドをコールする。<BR>
    * <BR>
    * 　@[sort()にセットするパラメータ]<BR>
    * 　@　@obj：　@パラメータ.投信残高照会明細<BR>
    * 　@　@com：　@生成したArrayList.toArray()の戻り値<BR>
    * @@param l_balanceReferenceDetailUnit - 投信残高照会明細の配列
    * 
    * @@param l_sortKey - ソートキー
    * @@roseuid 41AD8EE0036C
    */
   protected void sortBalanceReferenceDetailUnit(
           WEB3MutualBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnit, 
           WEB3MutualSortKey[] l_sortKey) 
   {
       final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit()";
       log.entering(STR_METHOD_NAME);
       
       if (l_sortKey == null)
       {
           log.debug(" パラメータ値がNULL ");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       // １)　@ArrayListを作成 
       List l_lisComparator = new Vector();

       // ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。
       for(int i = 0; i < l_sortKey.length; i++)
       {
           // 　@２－１）ソートキー.キー項目の値に対応する
           //       比較項目のComparatorを生成し、
           // 　@　@　@ArrayListに追加する。 
           // 　@　@　@①@投信残高照会Comparatorを生成する。
           // 　@　@　@　@[コンストラクタにセットするパラメータ] 
           // 　@　@　@　@　@orderBy： ソートキー.昇順／降順
           // 　@　@　@　@　@比較項目：　@ソートキー.キー項目
           String l_strKeyItem = l_sortKey[i].keyItem;
           String l_strAscDesc = l_sortKey[i].ascDesc;
           WEB3MutualBalanceReferenceComparator l_comparator = 
               new WEB3MutualBalanceReferenceComparator(l_strAscDesc, l_strKeyItem);
           
           // 　@　@　@②ArrayListに生成したComparatorを追加する。
           l_lisComparator.add(l_comparator);
       }
       
       // ３)ソート
       // 　@WEB3ArraysUtility.sort()メソッドをコールする。
       // 　@[sort()にセットするパラメータ]
       // 　@　@obj：　@パラメータ.投信残高照会明細
       // 　@　@com：　@生成したArrayList.toArray()の戻り値
       Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
       l_lisComparator.toArray(l_comparators);
       WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit, l_comparators);
   }
}
@
