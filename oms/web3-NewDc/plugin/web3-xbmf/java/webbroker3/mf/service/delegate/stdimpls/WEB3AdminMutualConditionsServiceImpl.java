head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信銘柄条件登録サービス　@実装(WEB3AdminMutualConditionsServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 王蘭芬(中訊) 新規作成
Revesion History : 2004/08/25 韋念瓊 (中訊) レビュー   
Revesion History : 2004/12/06 于美麗 (中訊) 残対応 
Revesion History : 2005/10/23 黄建 (中訊) フィデリティ対応 
Revesion History : 2006/05/18 肖志偉 (中訊)　@仕様変更 モデル414      
Revesion History : 2006/10/19 周捷 (中訊) 仕様変更・モデル499、505      
Revesion History : 2006/10/31 周捷 (中訊) 障害番号:U02921       
Revesion History : 2006/11/06 SRA大杉 障害番号:U02932       
Revesion History : 2007/04/10 趙林鵬 (中訊) モデル No.545
Revesion History : 2007/10/15 孫洪江 (中訊) モデル No.580 ＤＢ更新仕様093
*/

package webbroker3.mf.service.delegate.stdimpls;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MfTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3BuyPossibleDivDef;
import webbroker3.common.define.WEB3BuySellSwtSpecityDivDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SellPossibleDivDef;
import webbroker3.common.define.WEB3SpecDivDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3SwtPossibleDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.WEB3AdminMutualFrgncal;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductCondSpec;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradedProduct;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MutualFund2ndProductSonarDao;
import webbroker3.mf.data.MutualFund2ndProductSonarRow;
import webbroker3.mf.define.WEB3MFRecruitPossibleDivDef;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse;
import webbroker3.mf.message.WEB3MutualProductConditionsCommonInfo;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信銘柄条件登録サービス　@実装クラス<BR>
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminMutualConditionsServiceImpl extends WEB3MutualClientRequestService 
    implements WEB3AdminMutualConditionsService 
{
    
    /**
     * ログ出力ユーティリティ。
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualConditionsServiceImpl.class);
    
    /**
     * 管理者投資信託 銘柄条件登録を実施する。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドのいずれかをコールする。<BR>
     * 　@　@input銘柄条件登録()<BR>
     * 　@　@validate銘柄条件登録()<BR>
     * 　@　@submit銘柄条件登録()<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E530C2013A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        // リクエストデータの型により、以下のいずれかのメソッドをコールする。
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualConditionsInputRequest)
        {
            // －input銘柄条件登録()
            l_response = this.inputProductConditionsRegist(
                (WEB3AdminMutualConditionsInputRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualConditionsConfirmRequest)
        {
            // －validate銘柄条件登録()
            l_response = this.validateProductConditionsRegist(
                (WEB3AdminMutualConditionsConfirmRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminMutualConditionsCompleteRequest)
        {
            // －submit銘柄条件登録()
            l_response = this.submitProductConditionsRegist(
                (WEB3AdminMutualConditionsCompleteRequest)l_request);
            log.exiting(l_strMethodName);
            return l_response;
        }
        else
        {
            // パラメータ値が不正
            log.debug(l_strMethodName + " パラメータ値が不正！");
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        } 
    }
    
    /**
     * (input銘柄条件登録)<BR>
     * 投信銘柄条件登録入力処理を行う。<BR>
     * <BR>
     * シーケンス図「投信（管理者）input銘柄条件登録」参照<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3F1030164
     */
    protected WEB3AdminMutualConditionsInputResponse inputProductConditionsRegist(
        WEB3AdminMutualConditionsInputRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "inputProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsInputRequest l_request)";
        log.entering(l_strMethodName);

        //２）入力チェック 
        //　@引数.リクエストデータ.validate()をコールする。 
        l_request.validate();

        //３）管理者権限チェック 
        //　@３－１）管理者オブジェクト.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //　@３－２）管理者オブジェクト.validate権限( )をコールする。 
        // [validate権限()に指定する引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.投信（カレンダー管理） 
        // is更新：　@true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                true);
        
        // ４）投信銘柄条件登録入力レスポンスの設定。 
        //  取得した投信銘柄オブジェクトを元に、レスポンスの設定を行う。 

        // --------- Start -------------- 拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- 拡張投信銘柄マネージャの取得を行う
        
        WEB3AdminMutualConditionsInputResponse l_response = 
            (WEB3AdminMutualConditionsInputResponse)l_request.createResponse();
        try
        {
            // ４－１）拡張投信銘柄マネージャ.getProduct()の戻り値を投信銘柄クラスでキャストする。 
            WEB3MutualFundProduct l_mfProduct = 
                (WEB3MutualFundProduct)l_mfProductManager.getProduct(Long.parseLong(l_request.id));
            
            log.debug("拡張投信銘柄マネージャ.getProduct()の戻り値 Product ID = " + l_mfProduct.getProductId());
            
            //４－２）投信第2銘柄マスタDao.findRowByProductCode()をコールし、 
            // 投信第2銘柄マスタRowオブジェクトを取得する。
            MutualFund2ndProductSonarRow l_mfProductSonar2Row = 
                MutualFund2ndProductSonarDao.findRowByProductCodeInstitutionCode(
                    l_mfProduct.getProductCode(),
                    l_admin.getInstitutionCode());
            log.debug("投信第2銘柄マスタRowオブジェクトを取得するの戻り値 ProductCode = " + 
                    l_mfProductSonar2Row.getProductCode());
            
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow)l_mfProduct.getDataSourceObject();
            
            //４－３）投信銘柄条件登録共通情報オブジェクトを生成し、以下のプロパティをセットする。
            WEB3MutualProductConditionsCommonInfo l_mfProductInfo = 
                new WEB3MutualProductConditionsCommonInfo();
            
            // -------------------------------------------------------------
            //　@　@銘柄コード＝投信銘柄.getProductCode()
            l_mfProductInfo.mutualProductCode = l_mfProduct.getProductCode();
            
            //WEBBROKER3取扱状況＝投信銘柄.getDataSourceObject().getシステム取扱区分() 
            l_mfProductInfo.web3TreatmentFlag = l_mfProductRow.getSystemHandlingDiv();
            
            //銘柄名（和名）＝投信銘柄.get銘柄名() 
            l_mfProductInfo.jpnProductName = l_mfProductRow.getStandardName();
            
            //銘柄名（英名）＝投信銘柄.getDataSourceObject().get銘柄名（英名）() 
            l_mfProductInfo.engProductName = l_mfProductRow.getEngProductName();
            
            //投信銘柄カテゴリーコード＝投信銘柄.getDataSourceObject().getカテゴリーコード() 
            l_mfProductInfo.categoryCode = l_mfProductRow.getCategoryCode();
            
            //買付開始日＝投信銘柄.getDataSourceObject().get買付開始日() 
            l_mfProductInfo.buyStartDate = l_mfProductRow.getBuyStartDate();
            
            //買付終了日＝投信銘柄.getDataSourceObject().get買付終了日() 
            l_mfProductInfo.buyEndDate = l_mfProductRow.getBuyEndDate();
            
            //解約乗換開始日＝投信銘柄.getDataSourceObject().get解約乗換開始日() 
            l_mfProductInfo.sellSwitchingStartDate = l_mfProductRow.getSellSwtStartDate();
            
            //解約乗換終了日＝投信銘柄.getDataSourceObject().get解約乗換終了日() 
            l_mfProductInfo.sellSwitchingEndDate = l_mfProductRow.getSellSwtEndDate();
            
            //買取請求開始日＝投信銘柄.getDataSourceObject().get買取請求開始日() 
            l_mfProductInfo.buyClaimStartDate = l_mfProductRow.getBuyClaimStartDate();
            
            //買取請求終了日＝投信銘柄.getDataSourceObject().get買取請求終了日() 
            l_mfProductInfo.buyClaimEndDate = l_mfProductRow.getBuyClaimEndDate();
            
            //募集開始日＝拡張投信銘柄.get募集開始日() 
            l_mfProductInfo.applyAbleStartDate = l_mfProductRow.getRecruitStartDate();

            //募集終了日＝拡張投信銘柄.get募集終了日()
			l_mfProductInfo.applyAbleEndDate = l_mfProductRow.getRecruitEndDate();

            //指定方法@（買付）＝投信銘柄.get指定方法@（買付）() 
            l_mfProductInfo.buySelectable = l_mfProductRow.getBuySpecityDiv();
            
            //最低口数（新規買付）＝投信銘柄.get最低口数（新規買付）()
            if(l_mfProductRow.getNewBuyMinQtyIsNull())
            {
                l_mfProductInfo.newBuyMinQty = null;    
            }
            else
            {
                l_mfProductInfo.newBuyMinQty = 
                    Long.toString(l_mfProductRow.getNewBuyMinQty());
            }
            
            //単位口数（新規買付）＝投信銘柄.get単位口数（新規買付）()
            if(l_mfProductRow.getNewBuyUnitQtyIsNull()) 
            {
                l_mfProductInfo.newBuyUnitQty = null;
            }
            else
            {
                l_mfProductInfo.newBuyUnitQty = 
                    Long.toString(l_mfProductRow.getNewBuyUnitQty());
            }
            
            //最低金額（新規買付）＝投信銘柄.get最低金額（新規買付）()
            if(l_mfProductRow.getNewBuyMinAmtIsNull()) 
            {
                l_mfProductInfo.newBuyMinAmt = null;
            }
            else
            {
                l_mfProductInfo.newBuyMinAmt = 
                    Long.toString(l_mfProductRow.getNewBuyMinAmt());
            }
            
            //単位金額（新規買付）＝投信銘柄.get単位金額（新規買付）()
            if(l_mfProductRow.getNewBuyUnitAmtIsNull())
            { 
                l_mfProductInfo.newBuyUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.newBuyUnitAmt = 
                    Long.toString(l_mfProductRow.getNewBuyUnitAmt());
            }

            //最低口数（追加買付）＝投信銘柄.get最低口数（追加買付）() 
            if(l_mfProductRow.getAddBuyMinQtyIsNull())
            {
                l_mfProductInfo.addBuyMinQty = null;
            }
            else
            {
                l_mfProductInfo.addBuyMinQty = 
                    Long.toString(l_mfProductRow.getAddBuyMinQty());
            }
           
            //単位口数（追加買付）＝投信銘柄.get単位口数（追加買付）() 
            if(l_mfProductRow.getAddBuyUnitQtyIsNull())
            {
                l_mfProductInfo.addBuyUnitQty = null;
            }
            else
            {
                l_mfProductInfo.addBuyUnitQty = 
                    Long.toString(l_mfProductRow.getAddBuyUnitQty());
            }
            
            //最低金額（追加買付）＝投信銘柄.get最低金額（追加買付）() 
            if(l_mfProductRow.getAddBuyMinAmtIsNull())
            {
                l_mfProductInfo.addBuyMinAmt = null;
            }
            else
            {
                l_mfProductInfo.addBuyMinAmt = 
                    Long.toString(l_mfProductRow.getAddBuyMinAmt());
            }

            //単位金額（追加買付）＝投信銘柄.get単位金額（追加買付）() 
            if(l_mfProductRow.getAddBuyUnitAmtIsNull())
            {
                l_mfProductInfo.addBuyUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.addBuyUnitAmt = 
                    Long.toString(l_mfProductRow.getAddBuyUnitAmt());
            }
            
            //指定方法@（解約）＝投信銘柄.get指定方法@（解約）() 
            l_mfProductInfo.sellSelectable = l_mfProductRow.getSellSpecifyDiv();
            
            //最低口数（解約）＝投信銘柄.get最低口数（解約）()
            if(l_mfProductRow.getSellMinQtyIsNull())
            {
                l_mfProductInfo.sellMinQty = null;
            }
            else
            {
                l_mfProductInfo.sellMinQty = 
                    Long.toString(l_mfProductRow.getSellMinQty());
            }
            
            //単位口数（解約）＝投信銘柄.get単位口数（解約）() 
            if(l_mfProductRow.getSellUnitQtyIsNull())
            {
                l_mfProductInfo.sellUnitQty = null;
            }
            else
            {
                l_mfProductInfo.sellUnitQty = 
                    Long.toString(l_mfProductRow.getSellUnitQty());
            }
           
            //最低金額（解約）＝投信銘柄.get最低金額（解約）() 
            if(l_mfProductRow.getSellMinAmtIsNull())
            {
                l_mfProductInfo.sellMinAmt = null;
            }
            else
            {
                l_mfProductInfo.sellMinAmt = 
                    Long.toString(l_mfProductRow.getSellMinAmt());
            }
            
            //単位金額（解約）＝投信銘柄.get単位金額（解約）()
            if(l_mfProductRow.getSellUnitAmtIsNull())
            {
                l_mfProductInfo.sellUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.sellUnitAmt = 
                    Long.toString(l_mfProductRow.getSellUnitAmt());
            }
      
            //指定方法@（乗換）＝投信銘柄.get指定方法@（乗換）() 
            l_mfProductInfo.switchingSelectable = l_mfProductRow.getSwtSpecifyDiv();
            
            //最低口数（乗換）＝投信銘柄.get最低口数（乗換）() 
            if(l_mfProductRow.getSwtMinQtyIsNull())
            {
                l_mfProductInfo.switchingMinQty = null;
            }
            else
            {
                l_mfProductInfo.switchingMinQty = 
                    Long.toString(l_mfProductRow.getSwtMinQty());
            }
           
            //単位口数（乗換）＝投信銘柄.get単位口数（乗換）()
            if(l_mfProductRow.getSwtUnitQtyIsNull())
            {
                l_mfProductInfo.switchingUnitQty = null;
            }
            else
            {
                l_mfProductInfo.switchingUnitQty = 
                    Long.toString(l_mfProductRow.getSwtUnitQty());
            }
            
            //最低金額（乗換）＝投信銘柄.get最低金額（乗換）()
            if(l_mfProductRow.getSwtMinAmtIsNull())
            {
                l_mfProductInfo.switchingMinAmt = null;
            }
            else
            {
                l_mfProductInfo.switchingMinAmt = 
                    Long.toString(l_mfProductRow.getSwtMinAmt());
            }

            //単位金額（乗換）＝投信銘柄.get単位金額（乗換）()
            if(l_mfProductRow.getSwtUnitAmtIsNull())
            {
                l_mfProductInfo.switchingUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.switchingUnitAmt = 
                    Long.toString(l_mfProductRow.getSwtUnitAmt());
            }
            
            //指定方法@（募集）＝拡張投信銘柄.get指定方法@（募集）() 
            l_mfProductInfo.applySelectable =  l_mfProductRow.getRecruitSpecityDiv();
            
            //最低口数（募集）＝拡張投信銘柄.get最低口数（募集）() 
            if (l_mfProductRow.getRecruitMinQtyIsNull())
            {
                l_mfProductInfo.applyMinQty = null;
            }
            else
            {
                l_mfProductInfo.applyMinQty =  l_mfProductRow.getRecruitMinQty() + "";
            }

            //単位口数（募集）＝拡張投信銘柄.get単位口数（募集）() 
            if (l_mfProductRow.getRecruitUnitQtyIsNull())
            {
                l_mfProductInfo.applyUnitQty = null;
            }
            else
            {
                l_mfProductInfo.applyUnitQty =  l_mfProductRow.getRecruitUnitQty() + "";
            }
            
            //最低金額（募集）＝拡張投信銘柄.get最低金額（募集）() 
            if (l_mfProductRow.getRecruitMinAmtIsNull())
            {
                l_mfProductInfo.applyMinAmt = null;
            }
            else
            {
                l_mfProductInfo.applyMinAmt =  l_mfProductRow.getRecruitMinAmt() + "";
            }
            
            //単位金額（募集）＝拡張投信銘柄.get単位金額（募集）() 
            if (l_mfProductRow.getRecruitUnitAmtIsNull())
            {
                l_mfProductInfo.applyUnitAmt = null;
            }
            else
            {
                l_mfProductInfo.applyUnitAmt = l_mfProductRow.getRecruitUnitAmt() + "";
            }

            //買付制限区分＝拡張投信銘柄.getDataSourceObject().get買付制限区分() 
            l_mfProductInfo.buyRestrictionDiv = l_mfProductRow.getBuyLimitDiv();
            
            //受渡方法@＝拡張投信銘柄.get受渡方法@() 
            l_mfProductInfo.deliveryVariation = l_mfProductRow.getDeliveryMethod();
            
            //特定日取引銘柄区分＝拡張投信銘柄.getDataSourceObject().get特定日取引銘柄区分()
            l_mfProductInfo.unitTypeProductDiv = l_mfProductRow.getUnitTypeProductDiv();

            //外貨最低金額（新規買付）＝拡張投信銘柄.get外貨最低金額（新規買付）
            if (l_mfProductRow.getFrgnNewBuyMinAmtIsNull())
            {
                l_mfProductInfo.frgnMinAmtBuy = null;
            }
            else
            {
                l_mfProductInfo.frgnMinAmtBuy = l_mfProductRow.getFrgnNewBuyMinAmt() + "";
            }

            //外貨単位金額（新規買付）＝拡張投信銘柄.get外貨単位金額（新規買付）
            if (l_mfProductRow.getFrgnNewBuyUnitAmtIsNull())
            {
                l_mfProductInfo.frgnUnitAmtBuy = null;
            }
            else
            {
                l_mfProductInfo.frgnUnitAmtBuy = l_mfProductRow.getFrgnNewBuyUnitAmt() + "";
            }

            //外貨最低金額（追加買付）＝拡張投信銘柄.get外貨最低金額（追加買付）
            if (l_mfProductRow.getFrgnAddBuyMinAmtIsNull())
            {
                l_mfProductInfo.frgnMinAmtAdd = null;
            }
            else
            {
                l_mfProductInfo.frgnMinAmtAdd = l_mfProductRow.getFrgnAddBuyMinAmt() + "";
            }

            //外貨単位金額（追加買付）＝拡張投信銘柄.get外貨単位金額（追加買付）
            if (l_mfProductRow.getFrgnAddBuyUnitAmtIsNull())
            {
                l_mfProductInfo.frgnUnitAmtAdd = null;
            }
            else
            {
                l_mfProductInfo.frgnUnitAmtAdd = l_mfProductRow.getFrgnAddBuyUnitAmt() + "";
            }

            //外貨最低金額（解約）    ＝拡張投信銘柄.get外貨最低金額（解約）
            if (l_mfProductRow.getFrgnSellMinAmtIsNull())
            {
                l_mfProductInfo.frgnMinAmtSell = null;
            }
            else
            {
                l_mfProductInfo.frgnMinAmtSell = l_mfProductRow.getFrgnSellMinAmt() + "";
            }

            //外貨単位金額（解約）    ＝拡張投信銘柄.get外貨単位金額（解約
            if (l_mfProductRow.getFrgnSellUnitAmtIsNull())
            {
                l_mfProductInfo.frgnUnitAmtSell = null;
            }
            else
            {
                l_mfProductInfo.frgnUnitAmtSell = l_mfProductRow.getFrgnSellUnitAmt() + "";
            }

            //募集手数料区分           ＝拡張投信銘柄.get募集手数料区分
            l_mfProductInfo.applyCommissionDiv = l_mfProductRow.getRecruitCommissionDiv();

            //--------------- 取引可能区分（当日発注分／翌営業日発注分） ------------
            //○現在日付の取得 
            //－GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値を現在日時とする。
            Timestamp l_datSystemDate = 
                GtlUtils.getTradingSystem().getSystemTimestamp();
            
            //－取得した現在日付を引数に、this.is国内営業日()をコールする。    
            boolean l_blnIsCurBizDate = this.isCalendarBizDate(l_datSystemDate);
            
            //○海外市場の休日を考慮した発注日の取得
            //get投信発注日()をコールする。         
            Date l_datMfBizDate = 
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                                
            //○当営業日分の取引銘柄オブジェクトの取得 
            //－拡張投信銘柄マネージャ.get投信取引銘柄()をコールし、取引銘柄オブジェクトを取得する。 
            //[get投信取引銘柄に渡す引数] 
            //・証券会社＝管理者オブジェクト.get証券会社()の戻り値 
            //・銘柄コード＝投信銘柄.get銘柄コード()の戻り値 
            WEB3MutualFundTradedProduct l_tradedProductTodayOrder =
                (WEB3MutualFundTradedProduct)l_mfProductManager.getMutualFundTradedProduct(
                    l_admin.getInstitution(),
                    l_mfProduct.getProductCode());
           
            MutualFundTradedProductRow l_mfTradedProductRow = 
                (MutualFundTradedProductRow)l_tradedProductTodayOrder
                        .getDataSourceObject();
                
            //○取得した取引銘柄オブジェクトの「有効ビジネス日付」を取得
            String l_strValidForBizDate = l_mfTradedProductRow.getValidForBizDate();
                        
            //－取得した有効ビジネス日付を引数に、this.is営業日()をコールする。
            Date l_dateValidForBizDate = 
                    WEB3DateUtility.getDate(l_strValidForBizDate,"yyyyMMdd");
            boolean l_blnIsBizDate = 
                this.isBizDate(
                        l_admin.getInstitutionCode(),
                        l_mfProduct.getProductCode(),
                        new Timestamp (l_dateValidForBizDate.getTime()));
            
            // 翌営業日分の投信取引銘柄オブジェクト
            WEB3MutualFundTradedProduct l_tradedProductNextDayOrder = null;

            //○is国内営業日＝true かつ、is営業日()＝trueの場合 
            if(l_blnIsCurBizDate && l_blnIsBizDate)
            {
                log.debug("現在日付＝国内営業日かつ、国内発注日＝投信営業日");
                //○国内発注日からの翌営業日を取得   
                //－国内発注日を取得する 
                Date l_datBizDate = 
                    WEB3GentradeTradingTimeManagement.getOrderBizDate();
                    
                //－営業日計算オブジェクト.roll()をコールする。 
                //　@[rollに渡す引数] 
                //　@　@加算／減算日数＝１ 
                Timestamp l_datNextBizDate = new WEB3GentradeBizDate(
                        new Timestamp(l_datBizDate.getTime())).roll(1);
                   
                //○翌営業日分の取引銘柄UPDQオブジェクトの取得 
                //－投信取引銘柄一時テーブルを検索する。 
                //　@[検索条件] 
                //　@　@取引銘柄ID＝取引銘柄Aオブジェクト.get取引銘柄ID() and 
                //　@　@有効ビジネス日付＝roll()の戻り値 
                //－検索結果!=1件の場合、[データ不整合]として例外をスローする。
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                String l_strWhere = "traded_product_id = ? and valid_for_biz_date = ?";
                Object[] l_objWhereValues = {
                    new Long(l_tradedProductTodayOrder.getTradedProductId()),
                    WEB3DateUtility.formatDate(l_datNextBizDate, "yyyyMMdd") };
                    
                List l_lisTradedProductNextDay = l_processor.doFindAllQuery(
                        MfTradedProductUpdqRow.TYPE,
                        l_strWhere,
                        null,
                        l_objWhereValues); 
                    
                //－検索結果!=1件の場合、[データ不整合]として例外をスローする。
                if(l_lisTradedProductNextDay == null || l_lisTradedProductNextDay.size() != 1)
                {
                    log.debug("データ不整合エラー!"); 
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + l_strMethodName);
                }
                MutualFundTradedProductParams l_mfTradedProductBParams = 
                    new MutualFundTradedProductParams();
                GtlUtils.copyRow2Params((Row)l_lisTradedProductNextDay.get(0), l_mfTradedProductBParams);
                Row l_mfTradedProductBRow = l_mfTradedProductBParams;
                l_tradedProductNextDayOrder = 
                     (WEB3MutualFundTradedProduct)l_mfProductManager.toTradedProduct(
                          l_mfTradedProductBRow);
                
                // set values-----------------
                
                //・買付可能区分（当日発注分）＝当日発注分の投信取引銘柄オブジェクト.is買付可能()
                //  戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.todayBuyPossDiv = WEB3BuyPossibleDivDef.NOT_ACQUIRED;
                if (l_tradedProductTodayOrder.isAcquiredPossible())
                {
                    l_mfProductInfo.todayBuyPossDiv = WEB3BuyPossibleDivDef.ACQUIRED;
                }
                
                //・解約可能区分（当日発注分）＝当日発注分の投信取引銘柄オブジェクト.is解約可能()
                //  戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.todaySellPossDiv = WEB3SellPossibleDivDef.NOT_SELL;
                if (l_tradedProductTodayOrder.isSellPossible())
                {
                    l_mfProductInfo.todaySellPossDiv = WEB3SellPossibleDivDef.SELL;
                }
                
                //・乗換可能区分（当日発注分）＝当日発注分の投信取引銘柄オブジェクト.is乗換可能()
                //　@戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.todaySwitchingPossDiv = WEB3SwtPossibleDivDef.NOT_SWITCHING;
                if (l_tradedProductTodayOrder.isSwitchingPossible())
                {
                    l_mfProductInfo.todaySwitchingPossDiv = WEB3SwtPossibleDivDef.SWITCHING;
                }
                
                //募集可能区分（当日発注分）＝取引銘柄Aオブジェクト.is募集可能() (*) 
                //戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.todayApplyPossDiv = WEB3MFRecruitPossibleDivDef.NOT_RECRUIT;
                if (l_tradedProductTodayOrder.isRecruitPossible())
                {
                    l_mfProductInfo.todayApplyPossDiv = WEB3MFRecruitPossibleDivDef.RECRUIT;
                }

                //・買付可能区分（翌日発注分）＝翌営業日発注分の投信取引銘柄オブジェクト.is買付可能()
                //　@戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.NOT_ACQUIRED;
                if (l_tradedProductNextDayOrder.isAcquiredPossible())
                {
                    l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.ACQUIRED;
                }
                
                //・解約可能区分（翌日発注分）＝翌営業日発注分の投信取引銘柄オブジェクト.is解約可能()
                //　@戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.NOT_SELL;
                if (l_tradedProductNextDayOrder.isSellPossible())
                {
                    l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.SELL;
                }
                
                //・乗換可能区分（翌日発注分）＝翌営業日発注分の投信取引銘柄オブジェクト.is乗換可能() 
                //　@戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.NOT_SWITCHING;
                if (l_tradedProductNextDayOrder.isSwitchingPossible())
                {
                    l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.SWITCHING;
                }
                
                //募集可能区分（翌日発注分）＝取引銘柄Bオブジェクト.is募集可能() (*)
                //戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.NOT_RECRUIT;
                if (l_tradedProductNextDayOrder.isRecruitPossible())
                {
                    l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.RECRUIT;
                }
                
                //　@現在日からの発注日をセットする。
                l_response.curBizDate = l_datMfBizDate;
                
                //　@現在日からの翌営業日＝投信取引時間管理.get投信翌営業日()の戻り値をセットする。
                l_response.nextBizDate = 
                    WEB3MutualFundTradingTimeManagement.getMutualNextOrderBizDate(
                        l_admin.getInstitutionCode(),
                        l_mfProduct.getProductCode());
                
            }
            
            //○is国内営業日＝false　@または　@is営業日()＝falseの場合
            else if(!l_blnIsCurBizDate || !l_blnIsBizDate)
            {
                log.debug("現在日付≠国内営業日または、国内発注日≠投信営業日");
                // ・取得した取引銘柄オブジェクトを”翌営業日発注分”と判定する。
                l_tradedProductNextDayOrder = l_tradedProductTodayOrder;

                //・買付可能区分（当日発注分）＝当日発注分の投信取引銘柄オブジェクト.is買付可能()
                //　@is営業日()＝false の場合、全てに null をセットする。
                l_mfProductInfo.todayBuyPossDiv = null;
                //・解約可能区分（当日発注分）＝当日発注分の投信取引銘柄オブジェクト.is解約可能()
                //　@is営業日()＝false の場合、全てに null をセットする。
                l_mfProductInfo.todaySellPossDiv = null;
                //・乗換可能区分（当日発注分）＝当日発注分の投信取引銘柄オブジェクト.is乗換可能()
                //　@is営業日()＝false の場合、全てに null をセットする。
                l_mfProductInfo.todaySwitchingPossDiv = null;
                //募集可能区分（当日発注分）＝null 
                l_mfProductInfo.todayApplyPossDiv = null;

                //・買付可能区分（翌日発注分）＝翌営業日発注分の投信取引銘柄オブジェクト.is買付可能()
                //　@戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.NOT_ACQUIRED;
                if (l_tradedProductNextDayOrder.isAcquiredPossible())
                {
                    l_mfProductInfo.nextDayBuyPossDiv = WEB3BuyPossibleDivDef.ACQUIRED;
                }
                //・解約可能区分（翌日発注分）＝翌営業日発注分の投信取引銘柄オブジェクト.is解約可能()
                //　@戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.NOT_SELL;
                if (l_tradedProductNextDayOrder.isSellPossible())
                {
                    l_mfProductInfo.nextDaySellPossDiv = WEB3SellPossibleDivDef.SELL;
                }
                //・乗換可能区分（翌日発注分）＝翌営業日発注分の投信取引銘柄オブジェクト.is乗換可能() 
                //　@戻り値が true の場合”可”をセット、false の場合”不可”をセットする。
                l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.NOT_SWITCHING;
                if (l_tradedProductNextDayOrder.isSwitchingPossible())
                {
                    l_mfProductInfo.nextDaySwitchingPossDiv = WEB3SwtPossibleDivDef.SWITCHING;
                }
                //募集可能区分（翌日発注分）＝取引銘柄Aオブジェクト.is募集可能() (*) 
                //(*) 戻り値が true の場合“可”をセット、false の場合“不可”をセットする。
                l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.NOT_RECRUIT;
                if (l_tradedProductNextDayOrder.isRecruitPossible())
                {
                    l_mfProductInfo.nextDayApplyPossDiv = WEB3MFRecruitPossibleDivDef.RECRUIT;
                }

                //・is営業日＝falseの場合、現在日からの発注日にnull をセットする。
                l_response.curBizDate = null;
                
                //・is営業日＝falseの場合、現在日からの翌営業日＝get.投信発注日()の戻り値をセットする。
                l_response.nextBizDate = l_datMfBizDate; 
            }
            
            // ------------ 取引可能区分（当日発注分／翌営業日発注分） ------------
            
            //　@・オペレーション日付＝取得した現在日付 
            l_mfProductInfo.operationDate = l_datSystemDate;
            
            // -------------- 注文受付締切時間（平日） ------------------ 
            
            //○取引時間テーブル」を以下の条件で検索する。 
            //　@[検索条件] 
            //　@　@証券会社コード＝投信銘柄.get証券会社コード() and 
            //　@　@受付時間区分＝”投資信託” 
            //　@　@商品コード＝投信銘柄.get銘柄コード and 
            //　@　@営業日区分＝”終日営業日” and 
            //　@　@市場トリガ発行＝”SONARへMQトリガを実施しない” and 
            //　@　@受付可能＝”受付不可” and 
            //　@　@発注日計算＝”翌営業日” 
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            String l_whereClause = 
                " institution_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " submit_market_trigger = ? and " +
                " enable_order = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVars[] = {
                l_mfProduct.getInstitution().getInstitutionCode(),
                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                l_mfProduct.getProductCode(),
                WEB3BizDateTypeDef.BIZ_DATE,
                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                WEB3EnableOrderDef.DISABLED,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };
            
            // test log
            log.debug("l_whereClause = " + l_whereClause);
            for (int f = 0; f < l_bindVars.length; f++)
            {
                log.debug("l_bindVars[" + f + "] = " + l_bindVars[f]);
            }
            
            List l_lisTimeFullTradingTimeRows =
                l_processor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_whereClause,
                    null,
                    l_bindVars);            
            
            if(l_lisTimeFullTradingTimeRows == null || l_lisTimeFullTradingTimeRows.size() == 0)
            {
                //－検索結果の件数=0件の場合、（データ不整合）の例外をスローする。
                log.debug("データ不整合エラー!"); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName
                );
            }
            //－検索結果の１件目を取得し、取引時間テーブルRowでキャストする。 
            TradingTimeRow l_tradingTimeRowTimeFull = (TradingTimeRow) l_lisTimeFullTradingTimeRows.get(0);

            //－作成した取引時間テーブルRowをもとに、以下のプロパティをセットする。 
            //・注文締切開始時間（平日）＝取引時間テーブルRow.get開始時間()の先頭から４桁 
            if(l_tradingTimeRowTimeFull.getStartTime().compareTo(l_tradingTimeRowTimeFull.getEndTime()) == 0)
            {
                Date l_datStartTimeFull = 
                    WEB3DateUtility.getDate(l_tradingTimeRowTimeFull.getStartTime(), "HHmmss");
                l_datStartTimeFull = WEB3DateUtility.addSecond(l_datStartTimeFull, 1L);                    
                l_mfProductInfo.orderCloseStartTimeFull = 
                    WEB3DateUtility.formatDate(l_datStartTimeFull, "HHmmss").substring(0, 4);
            }
            else
            {
                l_mfProductInfo.orderCloseStartTimeFull = 
                    l_tradingTimeRowTimeFull.getStartTime().substring(0, 4);
            }

            //・注文締切終了時間（平日）＝取引時間テーブルRow.get終了時間()の１秒後の時間の先頭から４桁(*2)           
            Date l_datEndTimeFull = 
                WEB3DateUtility.getDate(l_tradingTimeRowTimeFull.getEndTime(), "HHmmss");
            l_datEndTimeFull = WEB3DateUtility.addSecond(l_datEndTimeFull, 1L);                    
            l_mfProductInfo.orderCloseEndTimeFull = 
                WEB3DateUtility.formatDate(l_datEndTimeFull, "HHmmss").substring(0, 4);
            
            //------------- 注文受付締切時間（平日） ------------------
            
            // ------------- 注文受付締切時間（半日） ------------------ 
            //○取引時間テーブル」を以下の条件で検索する。 
            //　@[検索条件] 
            //　@　@証券会社コード＝投信銘柄.get証券会社コード() and 
            //　@　@受付時間区分＝”投資信託” 
            //　@　@商品コード＝投信銘柄.get銘柄コード and 
            //　@　@営業日区分＝”半日（午前のみ）” and 
            //　@　@市場トリガ発行＝”SONARへMQトリガを実施しない” and 
            //　@　@受付可能＝”受付不可” and 
            //　@　@発注日計算＝”翌営業日” 
           
            String l_whereClauseHalf = 
                " institution_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " submit_market_trigger = ? and " +
                " enable_order = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVarsHalf[] = {
                    l_mfProduct.getInstitution().getInstitutionCode(),
                    WEB3TradingTimeTypeDef.MUTUAL_FUND,
                    l_mfProduct.getProductCode(),
                    WEB3BizDateTypeDef.BIZ_DATE_AM,
                    WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                    WEB3EnableOrderDef.DISABLED,
                    WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE };

            // test log
            log.debug("l_whereClauseHalf = " + l_whereClause);
            for (int f = 0; f < l_bindVars.length; f++)
            {
                log.debug("l_bindVarsHalf[" + f + "] = " + l_bindVarsHalf[f]);
            }
            
            List l_lisTimeHalfTradingTimeRows =
                l_processor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_whereClauseHalf,
                    null,
                    l_bindVarsHalf);            
            
            if(l_lisTimeHalfTradingTimeRows == null || l_lisTimeHalfTradingTimeRows.size() == 0)
            {
                //－検索結果の件数=0件の場合、（データ不整合）の例外をスローする。
                log.debug("データ不整合エラー!"); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName
                );
            }
            else
            {
                //－検索結果の１件目を取得し、取引時間テーブルRowでキャストする。 
                //－それをもとに、以下のプロパティをセットする。 
                //・注文締切開始時間（半日）＝取引時間テーブルRow.get開始時間()の先頭から４桁 
                //・注文締切終了時間（半日）＝取引時間テーブルRow.get終了時間()の１秒後の時間の先頭から４桁(*2) 
                TradingTimeRow l_tradingTimeRowTimeHalf = (TradingTimeRow) l_lisTimeHalfTradingTimeRows.get(0);
                
                if(l_tradingTimeRowTimeHalf.getStartTime().compareTo(l_tradingTimeRowTimeHalf.getEndTime()) == 0)
                {
                    Date l_datStartTimeHalf = 
                        WEB3DateUtility.getDate(l_tradingTimeRowTimeHalf.getStartTime(), "HHmmss");
                    l_datStartTimeHalf = WEB3DateUtility.addSecond(l_datStartTimeHalf, 1L);                    
                    l_mfProductInfo.orderCloseStartTimeHalf = 
                        WEB3DateUtility.formatDate(l_datStartTimeHalf, "HHmmss").substring(0, 4);
                }
                else
                {
                    l_mfProductInfo.orderCloseStartTimeHalf = 
                        l_tradingTimeRowTimeHalf.getStartTime().substring(0, 4);
                }

                Date l_datEndTimeHalf = 
                    WEB3DateUtility.getDate(l_tradingTimeRowTimeHalf.getEndTime(), "HHmmss");
                l_datEndTimeHalf = WEB3DateUtility.addSecond(l_datEndTimeHalf, 1L);                    
                
                l_mfProductInfo.orderCloseEndTimeHalf = 
                    WEB3DateUtility.formatDate(l_datEndTimeHalf, "HHmmss").substring(0, 4);
            }

            // ----------------- 注文受付締切時間（半日） ------------------
            
            //○取得したレスポンスデータに以下のプロパティをセットする。 
            
            //　@投信協会銘柄コード＝投信銘柄.getDataSourceObject().get投信協会銘柄コード() 
            l_response.mutualAssocProductCode = 
                l_mfProductRow.getMutualassocProductCode();
            
            //　@最終更新者コード＝投信銘柄.getDataSourceObject().get最終更新者() 
            l_response.lastUpdaterCode = 
                l_mfProductRow.getLastUpdater();
            
            //  最終更新日時＝拡張投信銘柄.getDataSourceObject().get更新日付（オンライン）() 
            l_response.lastUpdateTime = 
                l_mfProductRow.getOnlineUpdatedTimestamp();
            
            //　@設定日＝投信銘柄.get設定日() 
            l_response.settingDate = 
                WEB3DateUtility.toDay(l_mfProductRow.getSettingDate());
            
            //　@償還日＝投信銘柄.get償還日() 
            l_response.refundDate = 
                WEB3DateUtility.toDay(l_mfProductRow.getRedemptionDate());
            
            //　@解約解禁日＝投信銘柄.get解約解禁日() 
            l_response.removalOfNotSell = 
                WEB3DateUtility.toDay(l_mfProductRow.getSellBanDate());

            //-- 指定方法@一覧（買付） -------------------------- 
            //　@投信第2銘柄マスタRowオブジェクト.get買付金額指定区分()=="1:可能"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get買付口数指定区分()=="0:不可"の場合 
            //　@"3:金額"で一覧を作成する。 
            
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyAmtSpecDiv()) 
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getBuyQtySpecDiv()))
            {
                String[] l_strBuySelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.buySelectableList = l_strBuySelectableList;
            }
            //　@投信第2銘柄マスタRowオブジェクト.get買付金額指定区分()=="0:不可"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get買付口数指定区分()=="1:可能"の場合 
            //　@"4:口数"で一覧を作成する。 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getBuyAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyQtySpecDiv()))
            {
                String[] l_strBuySelectableList = 
                    {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.buySelectableList = l_strBuySelectableList;
            }
            //　@投信第2銘柄マスタRowオブジェクト.get買付口数指定区分()=="1:可能"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get買付金額指定区分()=="1:可能"の場合 
            //　@"0:選択指定"、"3:金額"、"4:口数"で一覧を作成する。 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getBuyQtySpecDiv()))
            {
                String[] l_strBuySelectableList = 
                    {
                        WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE
                    };
                l_response.buySelectableList = l_strBuySelectableList;
            }

            //-- 指定方法@一覧（解約） ---------------------- 
            //　@投信第2銘柄マスタRowオブジェクト.get解約金額指定区分()=="1:可能"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get解約口数指定区分()=="0:不可"の場合 
            //　@"3:金額"で一覧を作成する。 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellAmtSpecDiv()) 
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSellQtySpecDiv()))
            {
                String[] l_strSellSelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.sellSelectableList = l_strSellSelectableList;
            }
            //　@投信第2銘柄マスタRowオブジェクト.get解約金額指定区分()=="0:不可"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get解約口数指定区分()=="1:可能"の場合 
            //　@"4:口数"で一覧を作成する。 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSellAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellQtySpecDiv()))
            {
                String[] l_strSellSelectableList = {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.sellSelectableList = l_strSellSelectableList;
            }
            //　@投信第2銘柄マスタRowオブジェクト.get解約口数指定区分()=="1:可能"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get解約金額指定区分()=="1:可能"の場合 
            //　@"0:選択指定"、"3:金額"、"4:口数"で一覧を作成する。 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSellQtySpecDiv()))
            {
                String[] l_strSellSelectableList = 
                    {
                        WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE
                    };
                l_response.sellSelectableList = l_strSellSelectableList;
            }

            //-- 指定方法@一覧（乗換） ---------------------- 
            //　@投信第2銘柄マスタRowオブジェクト.get乗換金額指定区分()=="1:可能"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get乗換口数指定区分()=="0:不可"の場合 
            //　@"3:金額"で一覧を作成する。 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtAmtSpecDiv()) 
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSwtQtySpecDiv()))
            {
                String[] l_strSwtSelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.switchingSelectableList = l_strSwtSelectableList;
            }
            //　@投信第2銘柄マスタRowオブジェクト.get乗換金額指定区分()=="0:不可"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get乗換口数指定区分()=="1:可能"の場合 
            //　@"4:口数"で一覧を作成する。 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getSwtAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtQtySpecDiv()))
            {
                String[] l_strSwtSelectableList = {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.switchingSelectableList = l_strSwtSelectableList;
            }
            //　@投信第2銘柄マスタRowオブジェクト.get乗換口数指定区分()=="1:可能"であり、 
            //　@かつ投信第2銘柄マスタRowオブジェクト.get乗換金額指定区分()=="1:可能"の場合 
            //　@"0:選択指定"、"3:金額"、"4:口数"で一覧を作成する。 
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtAmtSpecDiv()) 
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getSwtQtySpecDiv()))
            {
                String[] l_strSwtSelectableList = 
                    {
                        WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                        WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE
                    };
                l_response.switchingSelectableList = l_strSwtSelectableList;
            }

            //-- 指定方法@一覧（募集） ---------------------- 
            //(*) 投信第2銘柄マスタRowオブジェクト.get募集金額指定区分()=="1:可能"であり、 
            //かつ投信第2銘柄マスタRowオブジェクト.get募集口数指定区分()=="0:不可"の場合 
            //"3:金額"で一覧を作成する。
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitAmtSpecDiv())
                && WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getRecruitQtySpecDiv()))
            {
                String[] l_strApplySelectableList = {WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE};
                l_response.applySelectableList = l_strApplySelectableList;
            }
            
            //(*) 投信第2銘柄マスタRowオブジェクト.get募集金額指定区分()=="0:不可"であり、 
            //かつ投信第2銘柄マスタRowオブジェクト.get募集口数指定区分()=="1:可能"の場合 
            //"4:口数"で一覧を作成する。 
            if (WEB3SpecDivDef.NOT_POSSIBLE.equals(l_mfProductSonar2Row.getRecruitAmtSpecDiv())
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitQtySpecDiv()))
            {
                String[] l_strApplySelectableList = {WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.applySelectableList = l_strApplySelectableList;
            }
            
            //(*) 投信第2銘柄マスタRowオブジェクト.get募集口数指定区分()=="1:可能"であり、 
            //かつ投信第2銘柄マスタRowオブジェクト.get募集金額指定区分()=="1:可能"の場合 
            //"0:選択指定"、"3:金額"、"4:口数"で一覧を作成する。 
            
            if (WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitAmtSpecDiv())
                && WEB3SpecDivDef.POSSIBLE.equals(l_mfProductSonar2Row.getRecruitQtySpecDiv()))
            {
                String[] l_strApplySelectableList = {
                    WEB3BuySellSwtSpecityDivDef.SELECT_DESIGNATE,
                    WEB3BuySellSwtSpecityDivDef.PRICE_DESIGNATE,
                    WEB3BuySellSwtSpecityDivDef.QUANTITY_DESIGNATE};
                l_response.applySelectableList = l_strApplySelectableList;
            }
            //-- 指定方法@一覧（募集） ---------------------- 
            
            //○銘柄情報＝生成した投信銘柄条件登録共通情報オブジェクト
            l_response.mutualProductInfo = l_mfProductInfo;

            //外貨MMFフラグ＝拡張投信銘柄.is外貨MMF()の戻り値
            l_response.frgnMmfFlag = l_mfProduct.isFrgnMmf();

            //通貨コード＝拡張投信銘柄.get通貨コード()の戻り値
            l_response.currencyCode = l_mfProduct.getCurrencyCode();

        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する証券会社オブジェクトまたは投信銘柄オブジェクトがありません!"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        return l_response;        
    }
    
    /**
     * (submit銘柄条件登録)<BR>
     * 投信銘柄条件登録処理を行う。<BR>
     * <BR>
     * シーケンス図「投信（管理者）銘柄条件登録」参照<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3F14000E6
     */
    protected WEB3AdminMutualConditionsCompleteResponse submitProductConditionsRegist(
        WEB3AdminMutualConditionsCompleteRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "submitProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsCompleteResponse l_request)";
        log.entering(l_strMethodName);
        
        //○入力チェック 
        //　@引数.リクエストデータ.validate()をコールする。 
        l_request.validate();
        
        //○管理者権限チェック 
        //　@管理者オブジェクト.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //　@管理者オブジェクト.validate権限( )をコールする。 
        //　@　@[validate権限()に指定する引数] 
        //　@　@機@能カテゴリコード：　@機@能カテゴリコード.投信（カレンダー管理） 
        //　@　@is更新：　@true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                true);
        
        //○管理者のパスワードチェックを行う。 
        //　@管理者オブジェクト.validateパスワードをコールする。         
        l_admin.validateTradingPassword(l_request.password);
        
        //○オペレーション日付チェック
        //　@現在日付取得
        //　@GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値 
        Timestamp l_datSystemDate = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
            
        log.debug("現在日付" + l_datSystemDate);
        
        //　@リクエストデータ.銘柄情報.オペレーション日付!=現在日付の場合、 
        //　@例外をスローする。
        Date l_datOperationDate = l_request.mutualProductInfo.operationDate;
        if(WEB3DateUtility.compareToDay(l_datSystemDate,l_datOperationDate) != 0)
        {
            log.debug("銘柄情報のオペレーション日付は現在日付ではありません。 ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01353,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        
        //○証券会社オブジェクトの取得 
        //　@管理者オブジェクト.get証券会社コードをコールする。 
        Institution l_institution = l_admin.getInstitution();
        
        //○投信銘柄条件内容オブジェクトの生成と値の設定 
        WEB3MutualProductConditionsCommonInfo l_mfInfo = 
            l_request.mutualProductInfo;
        
        WEB3MutualFundProductCondSpec l_mfProductCondSpec = new WEB3MutualFundProductCondSpec();
        l_mfProductCondSpec.setMutualProductCode(l_mfInfo.mutualProductCode);
        l_mfProductCondSpec.setBuyStartDate(l_mfInfo.buyStartDate);
        l_mfProductCondSpec.setBuyEndDate(l_mfInfo.buyEndDate);
        l_mfProductCondSpec.setSellSwitchingStartDate(l_mfInfo.sellSwitchingStartDate);
        l_mfProductCondSpec.setSellSwitchingEndDate(l_mfInfo.sellSwitchingEndDate);
        l_mfProductCondSpec.setBuyClaimStartDate(l_mfInfo.buyClaimStartDate);
        l_mfProductCondSpec.setBuyClaimEndDate(l_mfInfo.buyClaimEndDate);
        l_mfProductCondSpec.setBuySelectable(l_mfInfo.buySelectable);
        
        //・募集開始日＝リクエストデータ.銘柄情報.募集開始日
        l_mfProductCondSpec.setApplyAbleStartDate(l_mfInfo.applyAbleStartDate);
        //・募集終了日＝リクエストデータ.銘柄情報.募集終了日 
        l_mfProductCondSpec.setApplyAbleEndDate(l_mfInfo.applyAbleEndDate);
        
        if (l_mfInfo.newBuyMinQty == null)
        {
            l_mfProductCondSpec.setNewBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinQty(l_mfInfo.newBuyMinQty);
        }
        
        if (l_mfInfo.newBuyUnitQty == null)
        {
            l_mfProductCondSpec.setNewBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitQty(l_mfInfo.newBuyUnitQty);
        }
        
        if (l_mfInfo.newBuyMinAmt == null)
        {
            l_mfProductCondSpec.setNewBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinAmt(l_mfInfo.newBuyMinAmt);
        }
        
        if (l_mfInfo.newBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(l_mfInfo.newBuyUnitAmt);
        }
        
        if (l_mfInfo.addBuyMinQty == null)
        {
            l_mfProductCondSpec.setAddBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinQty(l_mfInfo.addBuyMinQty);
        }
        
        if (l_mfInfo.addBuyUnitQty == null)
        {
            l_mfProductCondSpec.setAddBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitQty(l_mfInfo.addBuyUnitQty);
        }
        
        if (l_mfInfo.addBuyMinAmt == null)
        {
            l_mfProductCondSpec.setAddBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinAmt(l_mfInfo.addBuyMinAmt);
        }
        
        if (l_mfInfo.addBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(l_mfInfo.addBuyUnitAmt);
        }

        l_mfProductCondSpec.setSellSelectable(l_mfInfo.sellSelectable);
        
        if (l_mfInfo.sellMinQty == null)
        {
            l_mfProductCondSpec.setSellMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinQty(l_mfInfo.sellMinQty);
        }  
        
        if (l_mfInfo.sellUnitQty == null)
        {
            l_mfProductCondSpec.setSellUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitQty(l_mfInfo.sellUnitQty);
        }
        
        if (l_mfInfo.sellMinAmt == null)
        {
            l_mfProductCondSpec.setSellMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinAmt(l_mfInfo.sellMinAmt);
        } 
        
        if (l_mfInfo.sellUnitAmt == null)
        {
            l_mfProductCondSpec.setSellUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitAmt(l_mfInfo.sellUnitAmt);
        } 

        l_mfProductCondSpec.setSwitchingSelectable(l_mfInfo.switchingSelectable);
        
        if (l_mfInfo.switchingMinQty == null)
        {
            l_mfProductCondSpec.setSwitchingMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinQty(l_mfInfo.switchingMinQty);
        }  
        
        if (l_mfInfo.switchingUnitQty == null)
        {
            l_mfProductCondSpec.setSwitchingUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitQty(l_mfInfo.switchingUnitQty);
        }
        
        if (l_mfInfo.switchingMinAmt == null)
        {
            l_mfProductCondSpec.setSwitchingMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinAmt(l_mfInfo.switchingMinAmt);
        } 
        
        if (l_mfInfo.switchingUnitAmt == null)
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(l_mfInfo.switchingUnitAmt);
        } 
        
        //・指定方法@（募集）＝リクエストデータ.銘柄情報.指定方法@（募集）
        if (l_mfInfo.applySelectable == null)
        {
            l_mfProductCondSpec.setApplySelectable(null);
        }
        else
        {
            l_mfProductCondSpec.setApplySelectable(l_mfInfo.applySelectable);
        }
        //・最低口数（募集）＝リクエストデータ.銘柄情報.最低口数（募集） 
        if (l_mfInfo.applyMinQty == null)
        {
            l_mfProductCondSpec.setApplyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinQty(l_mfInfo.applyMinQty);
        }
        //・単位口数（募集）＝リクエストデータ.銘柄情報.単位口数（募集） 
        if (l_mfInfo.applyUnitQty == null)
        {
            l_mfProductCondSpec.setApplyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitQty(l_mfInfo.applyUnitQty);
        }
        //・最低金額（募集）＝リクエストデータ.銘柄情報.最低金額（募集） 
        if (l_mfInfo.applyMinAmt == null)
        {
            l_mfProductCondSpec.setApplyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinAmt(l_mfInfo.applyMinAmt);
        }
        //・単位金額（募集）＝リクエストデータ.銘柄情報.単位金額（募集）
        if (l_mfInfo.applyUnitAmt == null)
        {
            l_mfProductCondSpec.setApplyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitAmt(l_mfInfo.applyUnitAmt);
        }
        
        l_mfProductCondSpec.setBuyPossibleDivTheDay(
                l_mfInfo.todayBuyPossDiv);
        l_mfProductCondSpec.setBuyPossibleDivTheNextDay(
                l_mfInfo.nextDayBuyPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheDay(
                l_mfInfo.todaySellPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheNextDay(
                l_mfInfo.nextDaySellPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheDay(
                l_mfInfo.todaySwitchingPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheNextDay(
                l_mfInfo.nextDaySwitchingPossDiv);
        
        //・募集可能区分（当日発注分）＝リクエストデータ.銘柄情報.募集可能区分（当日発注分） 
        l_mfProductCondSpec.setApplyPossDivTheDay(
            l_mfInfo.todayApplyPossDiv);
        
        l_mfProductCondSpec.setOrderCloseStartTimeFull(
                l_mfInfo.orderCloseStartTimeFull);
        l_mfProductCondSpec.setOrderCloseEndTimeFull(
                l_mfInfo.orderCloseEndTimeFull);
        l_mfProductCondSpec.setOrderCloseStartTimeHalf(
                l_mfInfo.orderCloseStartTimeHalf);
        l_mfProductCondSpec.setOrderCloseEndTimeHalf(
                l_mfInfo.orderCloseEndTimeHalf);
        
        //・募集可能区分（翌日発注分）＝リクエストデータ.銘柄情報.募集可能区分（翌日発注分） 
        l_mfProductCondSpec.setApplyPossDivTheNextDay(
            l_mfInfo.nextDayApplyPossDiv);

        //外貨最低金額（新規買付）＝リクエストデータ.銘柄情報.外貨最低金額（新規買付）
        l_mfProductCondSpec.setBuyFrgnMinAmtBuy(l_mfInfo.frgnMinAmtBuy);

        //・外貨単位金額（新規買付）＝リクエストデータ.銘柄情報.外貨単位金額（新規買付）
        l_mfProductCondSpec.setBuyFrgnUnitAmtBuy(l_mfInfo.frgnUnitAmtBuy);

        //・外貨最低金額（追加買付）＝リクエストデータ.銘柄情報.外貨最低金額（追加買付）
        l_mfProductCondSpec.setBuyFrgnMinAmtAdd(l_mfInfo.frgnMinAmtAdd);

        //・外貨単位金額（追加買付）＝リクエストデータ.銘柄情報.外貨単位金額（追加買付）
        l_mfProductCondSpec.setBuyFrgnUnitAmtAdd(l_mfInfo.frgnUnitAmtAdd);

        //・外貨最低金額（解約）＝リクエストデータ.銘柄情報.外貨最低金額（解約）
        l_mfProductCondSpec.setSellFrgnMinAmtSell(l_mfInfo.frgnMinAmtSell);

        //・外貨単位金額（解約）＝リクエストデータ.銘柄情報.外貨単位金額（解約）
        l_mfProductCondSpec.setSellFrgnUnitAmtSell(l_mfInfo.frgnUnitAmtSell);

        //○銘柄条件の登録審査実施 
        //　@拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        //　@拡張投信銘柄マネージャ.validate銘柄条件()をコールする。 
        l_mfProductManager.validateProductCond(l_institution, l_mfProductCondSpec);
         
        //--------------- 投信銘柄マスタテーブルの更新 --------------------         
        try
        {
            //○投信銘柄オブジェクトの取得 
            //　@拡張投信銘柄マネージャ.get更新用投信銘柄()の戻り値を投信銘柄クラスでキャストする。
            WEB3MutualFundProduct l_mfProduct = 
                l_mfProductManager.getUpdateMutualFundTradedProduct(
                    l_institution.getInstitutionCode(), 
                    l_mfInfo.mutualProductCode);
            
            //○投信銘柄オブジェクトより、投信銘柄Paramsオブジェクトの生成 
            //　@投信銘柄オブジェクト.getDataSourceObject()をコールする。
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow)l_mfProduct.getDataSourceObject();
            MutualFundProductParams l_mfProductParams = 
                new MutualFundProductParams(l_mfProductRow);
            
            //○投信銘柄Paramsオブジェクトに更新内容をセットする。
            if (l_mfInfo.web3TreatmentFlag != null)
            { 
                l_mfProductParams.setSystemHandlingDiv(l_mfInfo.web3TreatmentFlag);   
            }
            if (l_mfInfo.jpnProductName != null)
            {
                l_mfProductParams.setStandardName(l_mfInfo.jpnProductName);   
            }
            if (l_mfInfo.engProductName != null)
            {
                l_mfProductParams.setEngProductName(l_mfInfo.engProductName);   
            }
            if (l_mfInfo.categoryCode != null)
            {
                l_mfProductParams.setCategoryCode(l_mfInfo.categoryCode);
            }
            if (l_mfInfo.buySelectable != null)
            {
                l_mfProductParams.setBuySpecityDiv(l_mfInfo.buySelectable); 
            }
            if (l_mfInfo.sellSelectable != null)
            {
                l_mfProductParams.setSellSpecifyDiv(l_mfInfo.sellSelectable);
            }
            if (l_mfInfo.switchingSelectable != null)
            {
                l_mfProductParams.setSwtSpecifyDiv(l_mfInfo.switchingSelectable);
            }
            if (l_mfInfo.newBuyMinQty != null)
            {
                l_mfProductParams.setNewBuyMinQty(Long.parseLong(l_mfInfo.newBuyMinQty));
            }
            if (l_mfInfo.addBuyMinQty != null)
            {
                l_mfProductParams.setAddBuyMinQty(Long.parseLong(l_mfInfo.addBuyMinQty));
            }
            if (l_mfInfo.sellMinQty != null)
            {
                l_mfProductParams.setSellMinQty(Long.parseLong(l_mfInfo.sellMinQty));
            }
            if (l_mfInfo.switchingMinQty != null)
            {
                l_mfProductParams.setSwtMinQty(Long.parseLong(l_mfInfo.switchingMinQty));
            }
            if (l_mfInfo.newBuyUnitQty != null)
            {
                l_mfProductParams.setNewBuyUnitQty(Long.parseLong(l_mfInfo.newBuyUnitQty));
            }
            if (l_mfInfo.addBuyUnitQty != null)
            {
                l_mfProductParams.setAddBuyUnitQty(Long.parseLong(l_mfInfo.addBuyUnitQty));
            }
            if (l_mfInfo.sellUnitQty != null)
            {
                l_mfProductParams.setSellUnitQty(Long.parseLong(l_mfInfo.sellUnitQty));
            }
            if (l_mfInfo.switchingUnitQty != null)
            {
                l_mfProductParams.setSwtUnitQty(Long.parseLong(l_mfInfo.switchingUnitQty));
            }
            if (l_mfInfo.newBuyMinAmt != null)
            {
                l_mfProductParams.setNewBuyMinAmt(Long.parseLong(l_mfInfo.newBuyMinAmt));
            }
            if (l_mfInfo.addBuyMinAmt != null)
            {
                l_mfProductParams.setAddBuyMinAmt(Long.parseLong(l_mfInfo.addBuyMinAmt));
            }
            if (l_mfInfo.sellMinAmt != null)
            {
                l_mfProductParams.setSellMinAmt(Long.parseLong(l_mfInfo.sellMinAmt));
            }
            if (l_mfInfo.switchingMinAmt != null)
            {
                l_mfProductParams.setSwtMinAmt(Long.parseLong(l_mfInfo.switchingMinAmt));
            }
            if (l_mfInfo.newBuyUnitAmt != null)
            {
                l_mfProductParams.setNewBuyUnitAmt(Long.parseLong(l_mfInfo.newBuyUnitAmt));
            }
            if (l_mfInfo.addBuyUnitAmt != null)
            {
                l_mfProductParams.setAddBuyUnitAmt(Long.parseLong(l_mfInfo.addBuyUnitAmt));
            }
            if (l_mfInfo.sellUnitAmt != null)
            {
                l_mfProductParams.setSellUnitAmt(Long.parseLong(l_mfInfo.sellUnitAmt));
            }
            if (l_mfInfo.switchingUnitAmt != null)
            {
                l_mfProductParams.setSwtUnitAmt(Long.parseLong(l_mfInfo.switchingUnitAmt));
            }
            if (l_mfInfo.buyStartDate != null)
            {
                l_mfProductParams.setBuyStartDate(l_mfInfo.buyStartDate);
            }
            if (l_mfInfo.buyEndDate != null)
            {
                l_mfProductParams.setBuyEndDate(l_mfInfo.buyEndDate);
            }
            if (l_mfInfo.sellSwitchingStartDate != null)
            {
                l_mfProductParams.setSellSwtStartDate(l_mfInfo.sellSwitchingStartDate);
            }
            if (l_mfInfo.sellSwitchingEndDate != null)
            {
                l_mfProductParams.setSellSwtEndDate(l_mfInfo.sellSwitchingEndDate);
            }
            if (l_mfInfo.buyClaimStartDate != null)
            {
                l_mfProductParams.setBuyClaimStartDate(l_mfInfo.buyClaimStartDate);
            }
            if (l_mfInfo.buyClaimEndDate != null)
            {
                l_mfProductParams.setBuyClaimEndDate(l_mfInfo.buyClaimEndDate);
            }
            
            //指定方法@（募集）＝リクエストデータ.銘柄情報.指定方法@（募集）
            if (l_mfInfo.applySelectable != null)
            {
                l_mfProductParams.setRecruitSpecityDiv(l_mfInfo.applySelectable);
            }
            
            //最低口数（募集）＝リクエストデータ.銘柄情報.最低口数（募集） 
            if (l_mfInfo.applyMinQty != null)
            {
                l_mfProductParams.setRecruitMinQty(Long.parseLong(l_mfInfo.applyMinQty));
            }
            
            //単位口数（募集）＝リクエストデータ.銘柄情報.単位口数（募集） 
            if (l_mfInfo.applyUnitQty != null)
            {
                l_mfProductParams.setRecruitUnitQty(Long.parseLong(l_mfInfo.applyUnitQty));
            }
            
            //最低金額（募集）＝リクエストデータ.銘柄情報.最低金額（募集） 
            if (l_mfInfo.applyMinAmt != null)
            {
                l_mfProductParams.setRecruitMinAmt(Long.parseLong(l_mfInfo.applyMinAmt));
            }
            
            //単位金額（募集）＝リクエストデータ.銘柄情報.単位金額（募集） 
            if (l_mfInfo.applyUnitAmt != null)
            {
                l_mfProductParams.setRecruitUnitAmt(Long.parseLong(l_mfInfo.applyUnitAmt));
            }
            
            //買付制限区分＝リクエストデータ.銘柄情報.買付制限区分
            if (l_mfInfo.buyRestrictionDiv != null)
            {
                l_mfProductParams.setBuyLimitDiv(l_mfInfo.buyRestrictionDiv);
            }
            
            //募集開始日＝リクエストデータ.銘柄情報.募集開始日 
            if (l_mfInfo.applyAbleStartDate != null)
            {
                l_mfProductParams.setRecruitStartDate(l_mfInfo.applyAbleStartDate);
            }           
            
            //募集終了日＝リクエストデータ.銘柄情報.募集終了日
            if (l_mfInfo.applyAbleEndDate != null)
            {
				l_mfProductParams.setRecruitEndDate(l_mfInfo.applyAbleEndDate);
            }
            
            //受渡方法@＝リクエストデータ.銘柄情報.受渡方法@ 
            if (l_mfInfo.deliveryVariation != null)
            {
                l_mfProductParams.setDeliveryMethod(l_mfInfo.deliveryVariation);
            }
            
            //特定日取引銘柄区分＝リクエストデータ.特定日取引銘柄区分
            if (l_mfInfo.unitTypeProductDiv != null)
            {
                l_mfProductParams.setUnitTypeProductDiv(l_mfInfo.unitTypeProductDiv);
            }

            //外貨最低金額（新規買付）＝リクエストデータ.銘柄情報.外貨最低金額（新規買付）
            if (l_mfInfo.frgnMinAmtBuy != null)
            {
                l_mfProductParams.setFrgnNewBuyMinAmt(
                    Long.parseLong(l_mfInfo.frgnMinAmtBuy));
            }

            //外貨単位金額（新規買付）＝リクエストデータ.銘柄情報.外貨単位金額（新規買付
            if (l_mfInfo.frgnUnitAmtBuy != null)
            {
                l_mfProductParams.setFrgnNewBuyUnitAmt(
                    Long.parseLong(l_mfInfo.frgnUnitAmtBuy));
            }

            //外貨最低金額（追加買付）＝リクエストデータ.銘柄情報.外貨最低金額（追加買付）
            if (l_mfInfo.frgnMinAmtAdd != null)
            {
                l_mfProductParams.setFrgnAddBuyMinAmt(
                    Long.parseLong(l_mfInfo.frgnMinAmtAdd));
            }

            //外貨単位金額（追加買付）＝リクエストデータ.銘柄情報.外貨単位金額（追加買付）
            if (l_mfInfo.frgnUnitAmtAdd != null)
            {
                l_mfProductParams.setFrgnAddBuyUnitAmt(
                    Long.parseLong(l_mfInfo.frgnUnitAmtAdd));
            }

            //外貨最低金額（解約）＝リクエストデータ.銘柄情報.外貨最低金額（解約）
            if (l_mfInfo.frgnMinAmtSell != null)
            {
                l_mfProductParams.setFrgnSellMinAmt(
                    Long.parseLong(l_mfInfo.frgnMinAmtSell));
            }

            //外貨単位金額（解約）＝リクエストデータ.銘柄情報.外貨単位金額（解約）
            if (l_mfInfo.frgnUnitAmtSell != null)
            {
                l_mfProductParams.setFrgnSellUnitAmt(
                    Long.parseLong(l_mfInfo.frgnUnitAmtSell));
            }

            //募集手数料区分＝リクエストデータ.銘柄情報.募集手数料区分
            if (l_mfInfo.applyCommissionDiv != null)
            {
                l_mfProductParams.setRecruitCommissionDiv(l_mfInfo.applyCommissionDiv);
            }

            //○投信銘柄マスタテーブルの更新処理 
            //　@すべての項目がnullの場合銘柄マスタテーブルの更新を行わない。
            if ((l_mfInfo.web3TreatmentFlag != null)
                || (l_mfInfo.jpnProductName != null)
                || (l_mfInfo.engProductName != null)
                || (l_mfInfo.categoryCode != null)
                || (l_mfInfo.buySelectable != null)
                || (l_mfInfo.sellSelectable != null)
                || (l_mfInfo.switchingSelectable != null)
                || (l_mfInfo.newBuyMinQty != null)
                || (l_mfInfo.addBuyMinQty != null)
                || (l_mfInfo.sellMinQty != null)
                || (l_mfInfo.switchingMinQty != null)
                || (l_mfInfo.newBuyUnitQty != null)
                || (l_mfInfo.addBuyUnitQty != null)
                || (l_mfInfo.sellUnitQty != null)
                || (l_mfInfo.switchingUnitQty != null)
                || (l_mfInfo.newBuyMinAmt != null)
                || (l_mfInfo.addBuyMinAmt != null)
                || (l_mfInfo.sellMinAmt != null)
                || (l_mfInfo.switchingMinAmt != null)
                || (l_mfInfo.newBuyUnitAmt != null)
                || (l_mfInfo.addBuyUnitAmt != null)
                || (l_mfInfo.sellUnitAmt != null)
                || (l_mfInfo.switchingUnitAmt != null)
                || (l_mfInfo.buyStartDate != null)
                || (l_mfInfo.buyEndDate != null)
                || (l_mfInfo.sellSwitchingStartDate != null)
                || (l_mfInfo.sellSwitchingEndDate != null)
                || (l_mfInfo.buyClaimStartDate != null)
                || (l_mfInfo.buyClaimEndDate != null)
                || (l_mfInfo.applyAbleEndDate != null) 
                || (l_mfInfo.applyAbleStartDate != null)
                || (l_mfInfo.applyMinAmt != null)
                || (l_mfInfo.applyMinQty != null)
                || (l_mfInfo.applySelectable != null)
                || (l_mfInfo.applyUnitAmt != null)
                || (l_mfInfo.applyUnitQty != null)
                || (l_mfInfo.applyUnitAmt != null)
                || (l_mfInfo.buyRestrictionDiv != null)
                || (l_mfInfo.deliveryVariation != null)
                || (l_mfInfo.unitTypeProductDiv != null)
                || (l_mfInfo.frgnMinAmtBuy != null)
                || (l_mfInfo.frgnUnitAmtBuy != null)
                || (l_mfInfo.frgnMinAmtAdd != null)
                || (l_mfInfo.frgnUnitAmtAdd != null)
                || (l_mfInfo.frgnMinAmtSell != null)
                || (l_mfInfo.frgnUnitAmtSell != null)
                || (l_mfInfo.applyCommissionDiv != null))
            {
                l_mfProductParams.setLastUpdater(l_admin.getAdministratorCode());
            
                l_mfProductParams.setOnlineUpdatedTimestamp (GtlUtils.getSystemTimestamp());
            
                //　@QueryProcessor.doUpdateQuery()をコールする。
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_processor.doUpdateQuery(l_mfProductParams);
            }
            
            //--------------- 取引可能区分（当日発注分／翌営業日発注分）の更新 ------------           
            //○投信取引銘柄の取得 
            //　@－取引カレンダコンテキストの設定値更新処理を実施 
            //　@－投信取引銘柄オブジェクト取得処理を実施 
            MutualFundTradedProduct l_mfTradedProduct = 
                l_mfProductManager.getMutualFundTradedProduct(
                    l_admin.getInstitution(),
                    l_request.mutualProductInfo.mutualProductCode);
            
            //○現在日付が、国内営業日かのチェック
            //　@取得した現在日付を引数に、this.is国内営業日()をコールする。    
            boolean l_blnIsCalendarBizDate = this.isCalendarBizDate(l_datSystemDate);
                    
            //○国内発注日の取得 
            Date l_datBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            String l_strBizDate = 
                WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
            
            //○国内発注日が、投信営業日かのチェック
            //　@取得した国内発注日を引数にthis.is営業日()をコールする。
            boolean l_blnIsBizDate = 
                this.isBizDate(
                    l_admin.getInstitution().getInstitutionCode(),
                    l_request.mutualProductInfo.mutualProductCode,
                    new Timestamp (l_datBizDate.getTime()));
                
            //○投信発注日の取得
            // 　@get投信発注日()をコールする。
            Date l_datMfBizDate =
                WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
            
            //○海外市場の休日を考慮した翌営業日の取得
            //　@ get投信翌営業日()をコールする。
            Timestamp l_datNextMfBizDate = 
                WEB3MutualFundTradingTimeManagement.getMutualNextOrderBizDate(
                    l_admin.getInstitution().getInstitutionCode(),
                    l_request.mutualProductInfo.mutualProductCode);
            
            //○リクエストの買付・解約・乗換可能区分(当営業日分・翌営業日分)のnullチェック
            WEB3MutualProductConditionsCommonInfo l_mfProductInfo = 
                l_request.mutualProductInfo;
            boolean l_blnBuy = l_mfProductInfo.todayBuyPossDiv != null;
            boolean l_blnSell = l_mfProductInfo.todaySellPossDiv != null;
            boolean l_blnSwt = l_mfProductInfo.todaySwitchingPossDiv != null;
            boolean l_blnRecruit = l_mfProductInfo.todayApplyPossDiv != null;
            
            boolean l_blnBuyNext = l_mfProductInfo.nextDayBuyPossDiv != null;
            boolean l_blnSellNext = l_mfProductInfo.nextDaySellPossDiv != null;
            boolean l_blnSwtNext = l_mfProductInfo.nextDaySwitchingPossDiv != null;
            boolean l_blnRecruitNext = l_mfProductInfo.nextDayApplyPossDiv != null;
            
            //○取引銘柄（xTradeで取得した）の更新 
            //　@－投信取引銘柄テーブルの更新処理、または投信取引銘柄一時テーブルの更新処理を行う。
            QueryProcessor l_processor = Processors.getDefaultProcessor();
                    
            String l_strNextDayBuyPossDiv = null;
            String l_strNextDaySellPossDiv = null;
            String l_strNextDaySwtPossDiv = null;
            String l_strNextDayRecruitDiv = null;
            Timestamp l_tmsScramBizDate = null;     

            //[検索条件] 
            //　@取引銘柄ID＝取引銘柄オブジェクト.get取引銘柄ID() and 
            //　@有効ビジネス日付＝取得した国内発注日 
            String l_strWhere = "traded_product_id = ? and valid_for_biz_date = ?";
            log.debug("取引銘柄テーブルの検索");
            log.debug("取引銘柄ID＝"+ l_mfTradedProduct.getTradedProductId());
            log.debug("有効ビジネス日付＝"+ l_strBizDate);
            
            //取引銘柄オブジェクトの取得 
            //　@投信取引銘柄テーブルを以下の条件で検索する。 
            //[検索条件] 
            //　@取引銘柄ID＝取引銘柄オブジェクト.get取引銘柄ID() and 
            //　@有効ビジネス日付＝取得した国内発注日 
            //・検索結果 = 0件の場合、取引銘柄一時テーブルを検索する。
            //・検索結果 > 1件の場合、[データ不整合]として例外をスローする。
            Object[] l_objWhereValues = {
                new Long(l_mfTradedProduct.getTradedProductId()),
                    l_strBizDate};
                   
            List l_lisTradedProduct = l_processor.doFindAllQuery(
                    MutualFundTradedProductRow.TYPE,
                    l_strWhere,
                    null,
                    l_objWhereValues);
                    
            log.debug("size ="  + l_lisTradedProduct.size());               
            //取引銘柄テーブル検索結果==0件の場合、取引銘柄UPDQテーブルを検索する。
            if(l_lisTradedProduct.size() == 0)
            {
                log.debug("取引銘柄UPDQテーブルの検索");
                log.debug("取引銘柄ID＝"+ l_mfTradedProduct.getTradedProductId());
                log.debug("有効ビジネス日付＝"+ l_strBizDate);
                // 取引銘柄UPDQオブジェクトの取得 
                //　@投信取引銘柄テーブルを以下の条件で検索する。 
                //[検索条件] 
                //　@取引銘柄ID＝取引銘柄オブジェクト.get取引銘柄ID() and 
                //　@有効ビジネス日付＝取得した国内発注日 
                //・検索結果!=1件の場合、[データ不整合]として例外をスローする。
                List l_lisTradedProductUpdq = l_processor.doFindAllQuery(
                        MfTradedProductUpdqRow.TYPE,
                        l_strWhere,
                        null,
                        new Object[] {
                                new Long(l_mfTradedProduct.getTradedProductId()),
                                    l_strBizDate });
                        
                log.debug("size ="  + l_lisTradedProductUpdq.size());
                //取引銘柄UPDQテーブル検索結果!=1件の場合、[データ不整合]として例外をスローする。
                if(l_lisTradedProductUpdq == null || l_lisTradedProductUpdq.size() != 1)
                {
                    log.debug("データ不整合エラー!"); 
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + "." + l_strMethodName
                    );
                }
                //取引銘柄Updqオブジェクトの更新
                //累投取引銘柄一時テーブルParamsに以下のプロパティをセットし、
                //その内容で更新処理を行う。        
                MfTradedProductUpdqRow l_mfTradedProductUpdqTodayRow =
                    (MfTradedProductUpdqRow)l_lisTradedProductUpdq.get(0);          
            
                MfTradedProductUpdqParams l_mfTradedProductUpdqParams = 
                    new MfTradedProductUpdqParams(l_mfTradedProductUpdqTodayRow);
                
                // ・現在日付＝国内営業日かつ国内発注日＝投信営業日の場合
                // 　@かつ、買付・解約・乗換区分(当営業日分)のどれかに値がセットされていた場合
                if(l_blnIsCalendarBizDate && l_blnIsBizDate && (l_blnBuy || l_blnSell || l_blnSwt || l_blnRecruit))
                {
                    log.debug("is営業日()＝trueの場合");
                    log.debug("当営業日分の取引銘柄UPDQを更新する。");
                    //「買付・解約・乗換可能区分」のセット     
                    if(l_blnBuy)
                    {
                        l_mfTradedProductUpdqParams.setBuyPossibleDiv(
                            l_mfProductInfo.todayBuyPossDiv);
                    }
                    if(l_blnSell)
                    {
                        l_mfTradedProductUpdqParams.setSellPossibleDiv(
                            l_mfProductInfo.todaySellPossDiv);
                    }
                    if(l_blnSwt)
                    {
                        l_mfTradedProductUpdqParams.setSwtPossibleDiv(
                            l_mfProductInfo.todaySwitchingPossDiv);
                    }
                    if (l_blnRecruit)
                    {
                        l_mfTradedProductUpdqParams.setRecruitPossibleDiv(
                            l_mfProductInfo.todayApplyPossDiv);
                    }

                    //「緊急停止有効日」のセット
                    // 「買付・解約・乗換可能区分」がすべて可能の場合、nullをセット
                    //  それ以外は、get投信発注日()の戻り値をセット             
                    String l_dbBuyPossDiv = l_mfTradedProductUpdqParams.getBuyPossibleDiv();
                    String l_dbSellPossDiv = l_mfTradedProductUpdqParams.getSellPossibleDiv();
                    String l_dbSwtPossDiv = l_mfTradedProductUpdqParams.getSwtPossibleDiv();
                    String l_dbRecruitPossDiv = l_mfTradedProductUpdqParams.getRecruitPossibleDiv();
                    if(WEB3BuyPossibleDivDef.ACQUIRED.equals(l_dbBuyPossDiv) 
                            && WEB3SellPossibleDivDef.SELL.equals(l_dbSellPossDiv) 
                            && WEB3SwtPossibleDivDef.SWITCHING.equals(l_dbSwtPossDiv) 
                            && WEB3MFRecruitPossibleDivDef.RECRUIT.equals(l_dbRecruitPossDiv))
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(null);  
                    }
                    else
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(l_datMfBizDate);     
                    }
                
                    l_mfTradedProductUpdqParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductUpdqParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
                
                    l_processor.doUpdateQuery(l_mfTradedProductUpdqParams);   
                }
                // ・現在日付≠国内営業日または国内発注日≠投信営業日の場合
                // 　@かつ、買付・解約・乗換区分(翌営業日分)のどれかに値がセットされていた場合
                else if((!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                                && (l_blnBuyNext || l_blnSellNext || l_blnSwtNext || l_blnRecruitNext))
                {
                    log.debug("is営業日()＝falseの場合");
                    log.debug("翌営業日分の取引銘柄UPDQを更新する。");
                    //「買付・解約・乗換可能区分」のセット                      
                    if(l_blnBuyNext)
                    {
                        l_mfTradedProductUpdqParams.setBuyPossibleDiv(
                            l_mfProductInfo.nextDayBuyPossDiv);
                    }
                    if(l_blnSellNext)
                    {
                        l_mfTradedProductUpdqParams.setSellPossibleDiv(
                            l_mfProductInfo.nextDaySellPossDiv);
                    }
                    if(l_blnSwtNext)
                    {
                        l_mfTradedProductUpdqParams.setSwtPossibleDiv(
                            l_mfProductInfo.nextDaySwitchingPossDiv);
                    }
                    if(l_blnRecruitNext)
                    {
                        l_mfTradedProductUpdqParams.setRecruitPossibleDiv(
                            l_mfProductInfo.nextDayApplyPossDiv);
                    }
                    
                    // 翌営業日分更新内容の作成             
                    l_strNextDayBuyPossDiv =
                        l_mfTradedProductUpdqParams.getBuyPossibleDiv();
                    l_strNextDaySellPossDiv =
                        l_mfTradedProductUpdqParams.getSellPossibleDiv();
                    l_strNextDaySwtPossDiv =
                        l_mfTradedProductUpdqParams.getSwtPossibleDiv();
                    l_strNextDayRecruitDiv = 
                        l_mfTradedProductUpdqParams.getRecruitPossibleDiv();
                    
                    //「緊急停止有効日」のセット
                    // 「買付・解約・乗換可能区分」がすべて可能の場合、nullをセット
                    //  それ以外は、get投信発注日()の戻り値をセット                 
                    if(WEB3BuyPossibleDivDef.ACQUIRED.equals(l_strNextDayBuyPossDiv) 
                        && WEB3SellPossibleDivDef.SELL.equals(l_strNextDaySellPossDiv) 
                        && WEB3SwtPossibleDivDef.SWITCHING.equals(l_strNextDaySwtPossDiv)
                        && WEB3MFRecruitPossibleDivDef.RECRUIT.equals(l_strNextDayRecruitDiv))
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(null);  
                    }
                    else
                    {
                        l_mfTradedProductUpdqParams.setScramBizDate(l_datMfBizDate);     
                    }
                
                    l_mfTradedProductUpdqParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductUpdqParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
                
                    l_processor.doUpdateQuery(l_mfTradedProductUpdqParams);   
                }                                       
            }        
            //取引銘柄テーブル検索結果>1件の場合、[データ不整合]として例外をスローする。
            else if(l_lisTradedProduct == null || l_lisTradedProduct.size() > 1)
            {
                log.debug("データ不整合エラー!"); 
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + l_strMethodName
                );
            }
            
            //取引銘柄テーブル検索結果＝1件の場合
            else
            {
                MutualFundTradedProduct l_mfTradedProductToday = 
                    (WEB3MutualFundTradedProduct)l_mfProductManager.toTradedProduct(
                        (Row)l_lisTradedProduct.get(0));
                
                MutualFundTradedProductRow l_mfTradedProductTodayRow =
                    (MutualFundTradedProductRow)l_mfTradedProductToday.
                        getDataSourceObject();                  
            
                MutualFundTradedProductParams l_mfTradedProductParams = 
                    new MutualFundTradedProductParams(l_mfTradedProductTodayRow);
                //取引銘柄オブジェクトの更新
                //　@投信取引銘柄テーブルParamsに以下のプロパティをセットし、
                //　@その内容で更新処理を行う。
                // ・現在日付＝国内営業日かつ国内発注日＝投信営業日の場合
                // 　@かつ、買付・解約・乗換区分(当営業日分)のどれかに値がセットされていた場合
                if(l_blnIsCalendarBizDate && l_blnIsBizDate && (l_blnBuy || l_blnSell || l_blnSwt || l_blnRecruit))
                {
                    log.debug("is営業日()＝trueの場合");
                    log.debug("当営業日分の取引銘柄を更新する。");
                    //「買付・解約・乗換可能区分」のセット        
                    if(l_blnBuy)
                    {
                    l_mfTradedProductParams.setBuyPossibleDiv(
                            l_mfProductInfo.todayBuyPossDiv);
                    }
                    if(l_blnSell)
                    {
                        l_mfTradedProductParams.setSellPossibleDiv(
                            l_mfProductInfo.todaySellPossDiv);
                    }
                    if(l_blnSwt)
                    {
                        l_mfTradedProductParams.setSwtPossibleDiv(
                            l_mfProductInfo.todaySwitchingPossDiv);
                    }
                    if(l_blnRecruit)
                    {
                        l_mfTradedProductParams.setRecruitPossibleDiv(
                            l_mfProductInfo.todayApplyPossDiv);
                    }
                
                    l_mfTradedProductParams.setScramBizDate(null);
                    l_mfTradedProductParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
               
                    l_processor.doUpdateQuery(l_mfTradedProductParams);
                }
                // ・現在日付≠国内営業日または国内発注日≠投信営業日の場合
                // 　@かつ、買付・解約・乗換区分(翌営業日分)のどれかに値がセットされていた場合
                else if((!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                                && (l_blnBuyNext || l_blnSellNext || l_blnSwtNext || l_blnRecruitNext))
                {
                    log.debug("is営業日()＝falseの場合");
                    log.debug("翌営業日分の取引銘柄を更新する。");
                    //「買付・解約・乗換可能区分」のセット     
                    if(l_blnBuyNext)
                    {
                        l_mfTradedProductParams.setBuyPossibleDiv(
                            l_mfProductInfo.nextDayBuyPossDiv);
                    }
                    if(l_blnSellNext)
                    {
                        l_mfTradedProductParams.setSellPossibleDiv(
                            l_mfProductInfo.nextDaySellPossDiv);
                    }
                    if(l_blnSwtNext)
                    {
                        l_mfTradedProductParams.setSwtPossibleDiv(
                            l_mfProductInfo.nextDaySwitchingPossDiv);
                    }
                    if(l_blnRecruitNext)
                    {
                        l_mfTradedProductParams.setRecruitPossibleDiv(
                            l_mfProductInfo.nextDayApplyPossDiv);
                    }
                    
                    // 翌営業日分更新内容の作成
                    l_strNextDayBuyPossDiv =
                        l_mfTradedProductParams.getBuyPossibleDiv();                
                    l_strNextDaySellPossDiv =
                        l_mfTradedProductParams.getSellPossibleDiv();
                    l_strNextDaySwtPossDiv =
                        l_mfTradedProductParams.getSwtPossibleDiv();
                    l_strNextDayRecruitDiv =
                        l_mfTradedProductParams.getRecruitPossibleDiv();
                
                    l_mfTradedProductParams.setScramBizDate(null);
                    l_mfTradedProductParams.setLastUpdater(
                            l_admin.getAdministratorCode());
                    l_mfTradedProductParams.setOnlineUpdatedTimestamp(
                            GtlUtils.getSystemTimestamp());
               
                    l_processor.doUpdateQuery(l_mfTradedProductParams);
                }   
            }
            
            //○取引銘柄一時テーブル（国内翌営業日から投信翌営業日分まで）の更新
            // リクエスト.買付・解約・乗換可能区分（翌営業日分）
            // のいづれかに値がセットされていた場合
            if(l_blnBuyNext || l_blnSellNext || l_blnSwtNext || l_blnRecruitNext)
            {
                log.debug("翌営業日分までの取引銘柄UPDQの検索");
                log.debug("取引銘柄ID＝"+ l_mfTradedProduct.getTradedProductId());
                //現在からの「国内発注日」を引数に、
                //　@営業日計算オブジェクトを生成する。 
                Timestamp l_tmValidForBizDate =
                    new Timestamp (l_datBizDate.getTime());
                WEB3GentradeBizDate l_GentradeBizDate = 
                    new WEB3GentradeBizDate(l_tmValidForBizDate);
                    
                //営業日計算オブジェクト.roll()をコールする。 
                //　@[rollに渡す引数] 
                //　@加算／減算日数＝１ 
                Timestamp l_datNextBizDate = l_GentradeBizDate.roll(1);
                
                //投信取引銘柄一時テーブルの検索。
                //　@[検索条件] 
                //　@　@取引銘柄ID＝取引銘柄オブジェクト.get取引銘柄ID() and 
                //    市場ID＝市場オブジェクト.getMarketId() and
                //　@　@有効ビジネス日付>＝取得した国内翌営業日 and
                //    有効ビジネス日付<＝投信翌営業日
                String l_whereClause = " product_id = ? and " +
                        " market_id = ? and " +
                        " valid_for_biz_date >= ? and " +
                        " valid_for_biz_date <= ? ";
                String l_strCondition = " for update ";

                WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                MutualFundTradedProductRow l_mfTraderProductRow = 
                    (MutualFundTradedProductRow) l_mfTradedProduct.getDataSourceObject();
                
                long l_lngMarketId = 
                    l_gentradeFinObjectManager.getMarket(
                        l_admin.getInstitutionCode(),
                        WEB3MarketCodeDef.DEFAULT
                        ).getMarketId();
                
                //国内翌営業日
                String l_strNextBizDate = 
                    WEB3DateUtility.formatDate(l_datNextBizDate,"yyyyMMdd");
                
                //投信翌営業日  
                String l_strNextMfBizDate = null;
                //・現在日付＝国内営業日かつ国内発注日＝投信営業日の場合
                //  get投信翌営業日()の戻り値    
                if(l_blnIsCalendarBizDate && l_blnIsBizDate)
                {  
                    l_strNextMfBizDate = 
                        WEB3DateUtility.formatDate(l_datNextMfBizDate,"yyyyMMdd");  
                }
                //・現在日付≠国内営業日または国内発注日≠投信営業日の場合
                //  get投信発注日()の戻り値    
                else if(!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                {
                    l_strNextMfBizDate = 
                        WEB3DateUtility.formatDate(l_datMfBizDate,"yyyyMMdd");
                }

                log.debug("有効ビジネス日付(国内翌営業日)>＝"+ l_strNextBizDate); 
                log.debug("有効ビジネス日付(投信翌営業日)<＝"+ l_strNextMfBizDate);               
                Object l_bindVars[] = {
                        new Long(l_mfTraderProductRow.getProductId()),
                        new Long(l_lngMarketId),
                        l_strNextBizDate,
                        l_strNextMfBizDate};
                
                List l_rtnList =
                    l_processor.doFindAllQuery(
                        MfTradedProductUpdqRow.TYPE,
                        l_whereClause,
                        null,
                        l_strCondition,
                        l_bindVars);    
                
                log.debug("size ="+ l_rtnList.size());                
                if(l_rtnList != null && l_rtnList.size() != 0)
                {
                    int l_intSize = l_rtnList.size();

                    for (int i = 0; i < l_intSize; i++)
                    {
                        MfTradedProductUpdqRow l_mfTradedProductUpdqRow = 
                            (MfTradedProductUpdqRow) l_rtnList.get(i);
                       
                        MfTradedProductUpdqParams l_mfTradedProductUpdqParams = 
                            new MfTradedProductUpdqParams(l_mfTradedProductUpdqRow);

                        //・翌営業日分更新内容が作成されていない場合、
                        //  国内翌営業日分の投信取引銘柄一時テーブルから翌営業日分更新内容を作成する。
                        if (l_strNextDayBuyPossDiv == null && l_strNextDaySellPossDiv == null 
                                && l_strNextDaySwtPossDiv == null && l_strNextDayRecruitDiv == null
                                    && l_mfTradedProductUpdqParams.getValidForBizDate().
                                            compareTo(l_strNextBizDate) == 0)
                        {
                            log.debug("翌営業日分更新内容を作成");
                            //「買付・解約・乗換可能区分」のセット     
                            if(l_blnBuyNext)
                            {
                                l_strNextDayBuyPossDiv =
                                    l_mfProductInfo.nextDayBuyPossDiv;
                            }
                            else
                            {
                                l_strNextDayBuyPossDiv =
                                    l_mfTradedProductUpdqParams.getBuyPossibleDiv();
                            }
                            if(l_blnSellNext)
                            {
                                l_strNextDaySellPossDiv =
                                    l_mfProductInfo.nextDaySellPossDiv;
                            }
                            else
                            {
                                l_strNextDaySellPossDiv =
                                    l_mfTradedProductUpdqParams.getSellPossibleDiv();
                            }
                            if(l_blnSwtNext)
                            {
                                l_strNextDaySwtPossDiv =
                                    l_mfProductInfo.nextDaySwitchingPossDiv;   
                            }
                            else
                            {
                                l_strNextDaySwtPossDiv =
                                    l_mfTradedProductUpdqParams.getSwtPossibleDiv();
                            }
                            if (l_blnRecruitNext)
                            {
                                l_strNextDayRecruitDiv =
                                    l_mfProductInfo.nextDayApplyPossDiv;
                            }
                            else
                            {                           
                                l_strNextDayRecruitDiv = 
                                    l_mfTradedProductUpdqParams.getRecruitPossibleDiv();
                            }
                        }
                    }                   
                    //「緊急停止有効日」のセット
                    // 「買付・解約・乗換可能区分」がすべて可能の場合、nullをセット
                    //  それ以外は、以下をセットする。
                    //　@・現在日付＝国内営業日かつ国内発注日＝投信営業日の場合
                    //  　@get投信翌営業日()の戻り値
                    //　@・現在日付≠国内営業日または国内発注日≠投信営業日の場合
                    //  　@get投信発注日()の戻り値               
                    if(WEB3BuyPossibleDivDef.ACQUIRED.equals(l_strNextDayBuyPossDiv) 
                            && WEB3SellPossibleDivDef.SELL.equals(l_strNextDaySellPossDiv) 
                            && WEB3SwtPossibleDivDef.SWITCHING.equals(l_strNextDaySwtPossDiv)
                            && WEB3MFRecruitPossibleDivDef.RECRUIT.equals(l_strNextDayRecruitDiv))
                    {
                        l_tmsScramBizDate = null;  
                    }
                    else
                    {
                        if(l_blnIsCalendarBizDate && l_blnIsBizDate)
                        {  
                            l_tmsScramBizDate = l_datNextMfBizDate;
                        }
                        else if(!l_blnIsCalendarBizDate || !l_blnIsBizDate)
                        {
                            l_tmsScramBizDate = new Timestamp(l_datMfBizDate.getTime());
                        }
                    }
                    
                    //・投信翌営業日分までの投信取引銘柄一時テーブルを更新
                    for (int i = 0; i < l_intSize; i++)
                    {
                        log.debug("投信翌営業日分までの投信取引銘柄UPDQを更新");
                        MfTradedProductUpdqRow l_mfTradedProductUpdqRow = 
                            (MfTradedProductUpdqRow) l_rtnList.get(i);
                        
                        MfTradedProductUpdqParams l_mfTradedProductUpdqParams = 
                            new MfTradedProductUpdqParams(l_mfTradedProductUpdqRow);

                        l_mfTradedProductUpdqParams.setBuyPossibleDiv(
                            l_strNextDayBuyPossDiv);

                        l_mfTradedProductUpdqParams.setSellPossibleDiv(
                            l_strNextDaySellPossDiv);

                        l_mfTradedProductUpdqParams.setSwtPossibleDiv(
                            l_strNextDaySwtPossDiv); 
                        
                        l_mfTradedProductUpdqParams.setRecruitPossibleDiv(
                            l_strNextDayRecruitDiv);
                        
                        l_mfTradedProductUpdqParams.setScramBizDate(l_tmsScramBizDate);  

                        l_mfTradedProductUpdqParams.setLastUpdater(
                                l_admin.getAdministratorCode());

                        l_mfTradedProductUpdqParams.setOnlineUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        
                        l_processor.doUpdateQuery(l_mfTradedProductUpdqParams);
                    }
                }
            }
            
            //--------------- 取引時間テーブルの更新 --------------------         
            //○部店コード一覧の取得 
            Branch l_Branchs[] = l_institution.getBranches();
            String l_arrBranchCode[] = new String[l_Branchs.length];
            for (int i = 0; i < l_Branchs.length; i++)
            {
                l_arrBranchCode[i] = l_Branchs[i].getBranchCode();
            }
            
            //○注文締切時間（平日）の更新 
            // 　@－投信取引時間管理.注文締切時間更新()をコールする。 
            if((l_mfProductInfo.orderCloseStartTimeFull != null) 
                    || (l_mfProductInfo.orderCloseEndTimeFull != null))
            {
                WEB3MutualFundTradingTimeManagement.updateOrderCloseTime(
                        l_institution.getInstitutionCode(),
                        l_arrBranchCode,
                        l_mfProductInfo.mutualProductCode,
                        WEB3BizDateTypeDef.BIZ_DATE,
                        l_mfProductInfo.orderCloseStartTimeFull,
                        l_mfProductInfo.orderCloseEndTimeFull);
            }
            
            //○注文締切時間（半日）の更新 
            // 　@－投信取引時間管理.注文締切時間更新()をコールする。
            if((l_mfProductInfo.orderCloseStartTimeHalf != null) 
                    || (l_mfProductInfo.orderCloseEndTimeHalf != null))
            {
                WEB3MutualFundTradingTimeManagement.updateOrderCloseTime(
                        l_institution.getInstitutionCode(),
                        l_arrBranchCode,
                        l_mfProductInfo.mutualProductCode,
                        WEB3BizDateTypeDef.BIZ_DATE_AM,
                        l_mfProductInfo.orderCloseStartTimeHalf,
                        l_mfProductInfo.orderCloseEndTimeHalf);
            }
            
            WEB3AdminMutualConditionsCompleteResponse l_response = 
                (WEB3AdminMutualConditionsCompleteResponse)l_request.createResponse();

            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました! ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex
            );
        }
        
    }
    
    /**
     * (validate銘柄条件登録)<BR>
     * 投信銘柄条件登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図「投信（管理者）validate銘柄条件登録」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図「投信（管理者）validate銘柄条件登録」: <BR>
     *        5((現在日付取得 <BR>
     *        リクエストデータ.銘柄情報.オペレーション日付!=現在日付の場合 <BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01353<BR>
     *  ========================================================<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40E3F11B01A2
     */
    protected WEB3AdminMutualConditionsConfirmResponse validateProductConditionsRegist(
        WEB3AdminMutualConditionsConfirmRequest l_request) throws WEB3BaseException 
    {
        final String l_strMethodName = "validateProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsConfirmResponse l_request)";
        log.entering(l_strMethodName);

        
        // １）入力チェックを行う。
        l_request.validate();
        
        //３）管理者権限チェック 
        //　@３－１）管理者オブジェクト.getInstanceFromログイン情報( )をコールする。
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //　@３－２）管理者オブジェクト.validate権限( )をコールする。 
        // [validate権限()に指定する引数] 
        // 機@能カテゴリコード：　@機@能カテゴリコード.投信（カレンダー管理） 
        // is更新：　@true
        l_admin.validateAuthority(
                WEB3TransactionCategoryDef.ADMIN_MUTUAL_CONDITIONS,
                true);
        
        // ４）オペレーション日付チェック
        // ①@現在日付取得
        //　@GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値 
        Timestamp l_datSystemDate = 
            GtlUtils.getTradingSystem().getSystemTimestamp();
            
        log.debug("現在日付＝" + l_datSystemDate);
        
        // ②リクエストデータ.銘柄情報.オペレーション日付!=現在日付の場合、 
        //　@ 例外をスローする。
        Date l_datOperationDate = l_request.mutualProductInfo.operationDate;
        if(WEB3DateUtility.compareToDay(l_datSystemDate,l_datOperationDate) != 0)
        {
            log.debug("銘柄情報のオペレーション日付は現在日付ではありません。 ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01353,
                this.getClass().getName() + "." + l_strMethodName
            );
        }
        
        //５）証券会社オブジェクトの取得 
        //　@５－１）管理者オブジェクト.get証券会社コードをコールする。 
        Institution l_institution = l_admin.getInstitution();
        
        //６）投信銘柄条件内容オブジェクトの生成と値の設定 
        WEB3MutualProductConditionsCommonInfo l_mfInfo = 
            l_request.mutualProductInfo;
        
        WEB3MutualFundProductCondSpec l_mfProductCondSpec = new WEB3MutualFundProductCondSpec();
        l_mfProductCondSpec.setMutualProductCode(l_mfInfo.mutualProductCode);
        l_mfProductCondSpec.setBuyStartDate(l_mfInfo.buyStartDate);
        l_mfProductCondSpec.setBuyEndDate(l_mfInfo.buyEndDate);
        l_mfProductCondSpec.setSellSwitchingStartDate(l_mfInfo.sellSwitchingStartDate);
        l_mfProductCondSpec.setSellSwitchingEndDate(l_mfInfo.sellSwitchingEndDate);
        l_mfProductCondSpec.setBuyClaimStartDate(l_mfInfo.buyClaimStartDate);
        l_mfProductCondSpec.setBuyClaimEndDate(l_mfInfo.buyClaimEndDate);
        l_mfProductCondSpec.setBuySelectable(l_mfInfo.buySelectable);
        
        //・募集開始日＝リクエストデータ.銘柄情報.募集開始日
        l_mfProductCondSpec.setApplyAbleStartDate(l_mfInfo.applyAbleStartDate);
        //・募集終了日＝リクエストデータ.銘柄情報.募集終了日 
        l_mfProductCondSpec.setApplyAbleEndDate(l_mfInfo.applyAbleEndDate);
        
        if (l_mfInfo.newBuyMinQty == null)
        {
            l_mfProductCondSpec.setNewBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinQty(l_mfInfo.newBuyMinQty);
        }
        
        if (l_mfInfo.newBuyUnitQty == null)
        {
            l_mfProductCondSpec.setNewBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitQty(l_mfInfo.newBuyUnitQty);
        }
        
        if (l_mfInfo.newBuyMinAmt == null)
        {
            l_mfProductCondSpec.setNewBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyMinAmt(l_mfInfo.newBuyMinAmt);
        }
        
        if (l_mfInfo.newBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setNewBuyUnitAmt(l_mfInfo.newBuyUnitAmt);
        }
        
        if (l_mfInfo.addBuyMinQty == null)
        {
            l_mfProductCondSpec.setAddBuyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinQty(l_mfInfo.addBuyMinQty);
        }
        
        if (l_mfInfo.addBuyUnitQty == null)
        {
            l_mfProductCondSpec.setAddBuyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitQty(l_mfInfo.addBuyUnitQty);
        }
        
        if (l_mfInfo.addBuyMinAmt == null)
        {
            l_mfProductCondSpec.setAddBuyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyMinAmt(l_mfInfo.addBuyMinAmt);
        }
        
        if (l_mfInfo.addBuyUnitAmt == null)
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setAddBuyUnitAmt(l_mfInfo.addBuyUnitAmt);
        }

        l_mfProductCondSpec.setSellSelectable(l_mfInfo.sellSelectable);
        
        if (l_mfInfo.sellMinQty == null)
        {
            l_mfProductCondSpec.setSellMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinQty(l_mfInfo.sellMinQty);
        }  
        
        if (l_mfInfo.sellUnitQty == null)
        {
            l_mfProductCondSpec.setSellUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitQty(l_mfInfo.sellUnitQty);
        }
        
        if (l_mfInfo.sellMinAmt == null)
        {
            l_mfProductCondSpec.setSellMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellMinAmt(l_mfInfo.sellMinAmt);
        } 
        
        if (l_mfInfo.sellUnitAmt == null)
        {
            l_mfProductCondSpec.setSellUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSellUnitAmt(l_mfInfo.sellUnitAmt);
        } 

        l_mfProductCondSpec.setSwitchingSelectable(l_mfInfo.switchingSelectable);
        
        if (l_mfInfo.switchingMinQty == null)
        {
            l_mfProductCondSpec.setSwitchingMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinQty(l_mfInfo.switchingMinQty);
        }  
        
        if (l_mfInfo.switchingUnitQty == null)
        {
            l_mfProductCondSpec.setSwitchingUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitQty(l_mfInfo.switchingUnitQty);
        }
        
        if (l_mfInfo.switchingMinAmt == null)
        {
            l_mfProductCondSpec.setSwitchingMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingMinAmt(l_mfInfo.switchingMinAmt);
        } 
        
        if (l_mfInfo.switchingUnitAmt == null)
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setSwitchingUnitAmt(l_mfInfo.switchingUnitAmt);
        } 
        
        //・指定方法@（募集）＝リクエストデータ.銘柄情報.指定方法@（募集）
        if (l_mfInfo.applySelectable == null)
        {
            l_mfProductCondSpec.setApplySelectable(null);
        }
        else
        {
            l_mfProductCondSpec.setApplySelectable(l_mfInfo.applySelectable);
        }
        //・最低口数（募集）＝リクエストデータ.銘柄情報.最低口数（募集） 
        if (l_mfInfo.applyMinQty == null)
        {
            l_mfProductCondSpec.setApplyMinQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinQty(l_mfInfo.applyMinQty);
        }
        //・単位口数（募集）＝リクエストデータ.銘柄情報.単位口数（募集） 
        if (l_mfInfo.applyUnitQty == null)
        {
            l_mfProductCondSpec.setApplyUnitQty(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitQty(l_mfInfo.applyUnitQty);
        }
        //・最低金額（募集）＝リクエストデータ.銘柄情報.最低金額（募集） 
        if (l_mfInfo.applyMinAmt == null)
        {
            l_mfProductCondSpec.setApplyMinAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyMinAmt(l_mfInfo.applyMinAmt);
        }
        //・単位金額（募集）＝リクエストデータ.銘柄情報.単位金額（募集）
        if (l_mfInfo.applyUnitAmt == null)
        {
            l_mfProductCondSpec.setApplyUnitAmt(null);
        }
        else
        {
            l_mfProductCondSpec.setApplyUnitAmt(l_mfInfo.applyUnitAmt);
        }
        
        l_mfProductCondSpec.setBuyPossibleDivTheDay(
                l_mfInfo.todayBuyPossDiv);
        l_mfProductCondSpec.setBuyPossibleDivTheNextDay(
                l_mfInfo.nextDayBuyPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheDay(
                l_mfInfo.todaySellPossDiv);
        l_mfProductCondSpec.setSellPossibleDivTheNextDay(
                l_mfInfo.nextDaySellPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheDay(
                l_mfInfo.todaySwitchingPossDiv);
        l_mfProductCondSpec.setSwtPossibleDivTheNextDay(
                l_mfInfo.nextDaySwitchingPossDiv);
        //・募集可能区分（当日発注分）＝リクエストデータ.銘柄情報.募集可能区分（当日発注分） 
        l_mfProductCondSpec.setApplyPossDivTheDay(
            l_mfInfo.todayApplyPossDiv);
        
        l_mfProductCondSpec.setOrderCloseStartTimeFull(
                l_mfInfo.orderCloseStartTimeFull);
        l_mfProductCondSpec.setOrderCloseEndTimeFull(
                l_mfInfo.orderCloseEndTimeFull);
        l_mfProductCondSpec.setOrderCloseStartTimeHalf(
                l_mfInfo.orderCloseStartTimeHalf);
        l_mfProductCondSpec.setOrderCloseEndTimeHalf(
                l_mfInfo.orderCloseEndTimeHalf);
        //・募集可能区分（翌日発注分）＝リクエストデータ.銘柄情報.募集可能区分（翌日発注分） 
        l_mfProductCondSpec.setApplyPossDivTheNextDay(
            l_mfInfo.nextDayApplyPossDiv);

        //外貨最低金額（新規買付）＝リクエストデータ.銘柄情報.外貨最低金額（新規買付）
        l_mfProductCondSpec.setBuyFrgnMinAmtBuy(l_mfInfo.frgnMinAmtBuy);

        //・外貨単位金額（新規買付）＝リクエストデータ.銘柄情報.外貨単位金額（新規買付）
        l_mfProductCondSpec.setBuyFrgnUnitAmtBuy(l_mfInfo.frgnUnitAmtBuy);

        //・外貨最低金額（追加買付）＝リクエストデータ.銘柄情報.外貨最低金額（追加買付）
        l_mfProductCondSpec.setBuyFrgnMinAmtAdd(l_mfInfo.frgnMinAmtAdd);

        //・外貨単位金額（追加買付）＝リクエストデータ.銘柄情報.外貨単位金額（追加買付）
        l_mfProductCondSpec.setBuyFrgnUnitAmtAdd(l_mfInfo.frgnUnitAmtAdd);

        //・外貨最低金額（解約）＝リクエストデータ.銘柄情報.外貨最低金額（解約）
        l_mfProductCondSpec.setSellFrgnMinAmtSell(l_mfInfo.frgnMinAmtSell);

        //・外貨単位金額（解約）＝リクエストデータ.銘柄情報.外貨単位金額（解約）
        l_mfProductCondSpec.setSellFrgnUnitAmtSell(l_mfInfo.frgnUnitAmtSell);

        // ７）銘柄条件の登録審査を実施
        //    拡張投信銘柄マネージャ.validate銘柄条件()をコールする。
        // --------- Start -------------- 拡張投信銘柄マネージャの取得を行う
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
        WEB3MutualFundProductManager l_mfProductManager = 
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
        // --------- End -------------- 拡張投信銘柄マネージャの取得を行う
        l_mfProductManager.validateProductCond(
                l_institution, 
                l_mfProductCondSpec);

        WEB3AdminMutualConditionsConfirmResponse l_response = 
            (WEB3AdminMutualConditionsConfirmResponse)l_request.createResponse();

        return l_response;
    }
    
    /**
     * (is営業日)<BR>
     * 引数.対象日付が休日かどうかを判定する。 <BR>
     * <BR>
     * １）引数.対象日付が”土曜日”または”日曜日”の場合、falseを返却する。 <BR>
     * <BR>
     * ２）以下の条件で「カレンダーテーブル」を検索する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 　@日付＝引数.対象日付 and <BR>
     * 　@営業日区分＝”非営業日” <BR>
     * <BR>
     * －検索結果の件数＞0件の場合、falseを返却する。 <BR>
     * <BR>
     * ３）海外市場カレンダー.is休日()をコールし、trueが返却された場合、falseを返却する。 <BR>
     * [is休日に渡す引数] <BR>
     * 　@証券会社コード＝引数.証券会社コード <BR>
     * 　@銘柄コード＝引数.銘柄コード <BR>
     * 　@日付＝引数.対象日付 <BR>
     * <BR>
     * ４）上記以外の場合、trueを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strProductCode -  銘柄コード<BR>
     * @@param l_dat - 対象日付<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40E3F14000E6
     */
    public boolean isBizDate(
            String l_strInstitutionCode,
            String l_strProductCode,
            Timestamp l_dat) throws WEB3BaseException
    {
        final String l_strMethodName = "validateProductConditionsRegist(" 
            + "WEB3AdminMutualConditionsConfirmResponse l_request)";
        log.entering(l_strMethodName);
        
        boolean l_blnReturnValue = true;
        try
        {
            String l_strBizDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(l_dat);   
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            {
                l_blnReturnValue = false;
            }
            else
            {
                WEB3AdminMutualFrgncal l_adminMutualFrgncal = 
                    new WEB3AdminMutualFrgncal();
                boolean l_blnIsHoliday = l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode,
                        l_strProductCode,
                        l_dat);
                l_blnReturnValue = !l_blnIsHoliday;
            }
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("該当する証券会社オブジェクトまたは投信銘柄オブジェクトがありません!"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(l_strMethodName);
        return l_blnReturnValue;
    }
    
    /**
     * (is国内営業日)<BR>
     *引数.対象日付が休日かどうかを判定する。 <BR>
     *<BR>
     *１）引数.対象日付が”土曜日”または”日曜日”の場合、falseを返却する。<BR> 
     *<BR>
     *２）以下の条件で「カレンダーテーブル」を検索する。<BR>
     *[検索条件] <BR>
　@   *日付＝引数.対象日付 and <BR>
　@   *営業日区分＝”非営業日” <BR>
     *－検索結果の件数＞0件の場合、falseを返却する。<BR>
     *<BR>
     *３）上記以外の場合、trueを返却する。<BR>
     * @@param l_timestap - 対象日付
     * @@return l_bln
     * @@throws WEB3BaseException
     * @@roseuid 406932820270
     */
    public boolean isCalendarBizDate(Timestamp l_tsObjectDate)throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isCalendarBizDate(WEB3AdminMutualConditionsServiceImpl)";
        log.entering(STR_METHOD_NAME);       
        
        Date l_dateObjectDate = WEB3DateUtility.getDate(
            WEB3DateUtility.formatDate(l_tsObjectDate,"yyyyMMdd"),"yyyyMMdd");
        l_tsObjectDate = new Timestamp(l_dateObjectDate.getTime());
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_tsObjectDate);
        
        int l_intWeekDiv = l_calendar.get(Calendar.DAY_OF_WEEK) - 1;
        
        //１）引数.対象日付が”土曜日”または”日曜日”の場合、falseを返却する。 
        if (l_intWeekDiv == 6 || l_intWeekDiv == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）以下の条件で「カレンダーテーブル」を検索する。
        try
        {
            String l_strWhereClause = 
                "holiday = ? and biz_date_type = ?";
            //DataQueryException,DataNetworkException
            List l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                    l_strWhereClause,
                    new Object[] { 
                        l_tsObjectDate, 
                        WEB3BizDateTypeDef.NO_BIZ_DATE });

            if (l_lisRows.size() > 0)
            {
                log.exiting(STR_METHOD_NAME);
                return false;                      
            }           
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
