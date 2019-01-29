head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminFeqExecutionReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文約定照会サービスImpl(WEB3AdminFeqExecutionReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 鄭海良(中訊) 新規作成
Revesion History : 2005/08/03 韋念瓊(中訊) レビュー
Revesion History : 2007/02/26 徐大方(中訊)仕様変更モデルNo.092
Revesion History : 2008/01/23 柴双紅(中訊) 外国株式モデルNo.372
Revesion History : 2008/10/02 大澤(SRA) 【管理者注文約定照会】仕様変更管理台帳（モデル）No.131
Revesion History : 2009/08/03 武波(中訊) 【管理者注文約定照会】仕様変更管理台帳（モデル）No.134,No.135
Revesion History : 2009/08/03 武波(中訊) 【管理者注文約定照会】実装No.002
*/

package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.WEB3AdminProductExecutionInfo;
import webbroker3.adminorderexecinquiry.define.WEB3AdminFeqSortKeyDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORDetailDispInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecutionUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORTradingProductUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminFeqExecutionReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者外国株式注文約定照会サービスImpl)<BR>
 * 管理者外国株式注文約定照会サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionReferenceServiceImpl implements WEB3AdminFeqExecutionReferenceService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionReferenceServiceImpl.class);
    
    /**
     * @@roseuid 42D1C8EA02EE
     */
    public WEB3AdminFeqExecutionReferenceServiceImpl() 
    {
     
    }
       
    /**
     * 管理者外国株式注文約定照会サービスを行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * リクエストデータが、<BR>
     * [管理者・外国株式注文約定照会入力リクエストの場合]<BR>
     * 　@this.get入力画面()をコールする。<BR>
     * <BR>
     * [管理者・外国株式注文約定照会リクエストの場合]<BR>
     * 　@this.get照会画面()をコールする。<BR>
     * <BR>
     * [管理者・外国株式出来入力一覧リクエストの場合]<BR>
     * 　@this.get出来入力一覧()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A67E4D02D0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminORFeqOrderExecutionRefInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminORFeqOrderExecutionRefInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminORFeqOrderExecutionRefReferenceRequest)
        {
            //get照会画面()
            l_response = 
                this.getReferenceScreen((WEB3AdminORFeqOrderExecutionRefReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminORFeqExecuteListRequest)
        {
            //get出来入力一覧
            l_response = 
                this.getExecInputList((WEB3AdminORFeqExecuteListRequest)l_request);
        }
        else
        {
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
     * (get入力画面)<BR>
     * 管理者外国株式注文約定照会入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式注文約定照会サービス)get入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・外国株式注文約定照会入力リクエストオブジェクト<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A67E9F0020
     */
    protected WEB3AdminORFeqOrderExecutionRefInputResponse getInputScreen(
        WEB3AdminORFeqOrderExecutionRefInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminORFeqOrderExecutionRefInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者ロのグイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException

        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 get商品実施情報(証券会社コード : String, 部店コード一覧 : String[])
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();
        
        WEB3AdminProductExecutionInfo l_productExecutionInfo = null;
        try
        {
            //WEB3SystemLayerException,NotFoundException,DataFindException,DataQueryException,DataNetworkException
            l_productExecutionInfo = l_orderExecuteDataManager.getProductExecutionInfo(
                l_strInstitutionCode, 
                l_request.branchCode);
        }
        catch (DataFindException l_ex)
        {
            String l_strMessage = "商品実施情報が存在しない。";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "商品実施情報が存在しない。";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        //1.6 create取扱商品一覧(商品実施情報)
        WEB3AdminORTradingProductUnit[] l_tradingProductUnits = 
            this.createTradeProductList(l_productExecutionInfo);//WEB3BaseException
        
        //1.7 get取扱可能市場(証券会社コード : String, 銘柄タイプ : ProductTypeEnum)
        String[] l_strMarkets = WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
            l_strInstitutionCode, 
            ProductTypeEnum.FOREIGN_EQUITY);//WEB3SystemLayerException

        //ArrayList( )
        ArrayList l_lisMarketCode1 = new ArrayList();

        //ArrayList( )
        ArrayList l_lisMarketCode2 = new ArrayList();

        for (int i = 0; i < l_strMarkets.length; i++)
        {
            String l_strMarketCode = l_strMarkets[i];
            MarketRow l_marketRow = null;
            try
            {
                l_marketRow =
                    MarketDao.findRowByInstitutionCodeMarketCode(l_strInstitutionCode, l_strMarketCode);

                if (l_marketRow == null)
                {
                    log.debug("market not found with InstitutionCode, MarketCode:"
                        + l_strInstitutionCode + ", " + l_strMarketCode);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "条件に該当する市場がみつかりませんでした。");
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }

            //市場用プリファ@レンステーブルより該当するレコードを取得する。
            //市場ID：　@get取扱可能市場()の戻り値の該当要素（=市場コード）に該当する市場.市場ID
            //プリファ@レンス項目名：　@"feq.sle.broker"
            //項目名連番：　@1
            try
            {
                MarketPreferencesDao.findRowByPk(
                    l_marketRow.getMarketId(),
                    WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER,
                    1);

                //分岐フロー：市場用プレファ@レンステーブルに該当レコードが存在する場合
                //リスト（市場直結用）に市場コードを追加する。
                if (!l_lisMarketCode2.contains(l_strMarketCode))
                {
                    l_lisMarketCode2.add(l_strMarketCode);
                }
                log.debug("市場用プレファ@レンステーブルに該当レコードが存在する場合、"
                + "リスト（市場直結用）に市場コードを追加する");
            }
            catch(DataFindException l_ex)
            {
                //分岐フロー：市場用プリファ@レンステーブルに該当レコードが存在しない場合
                //リスト（非市場直結用）に市場コードを追加する。
                if (!l_lisMarketCode1.contains(l_strMarketCode))
                {
                    l_lisMarketCode1.add(l_strMarketCode);
                }

                log.debug("市場用プリファ@レンステーブルに該当レコードが存在しない場合、"
                    + "リスト（非市場直結用）に市場コードを追加する。");
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBへのアクセスに失敗しました。",
                    l_ex);
            }
        }

        //1.8 ArrayList( )
        ArrayList l_bizDateList = new ArrayList();
        
        //1.9 (*)業務日付の前営業日、業務日付を追加する。
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizDate = new Timestamp(l_datBizDate.getTime());
        
        //前営業日        
        for (int i = 0; i < l_strMarkets.length; i++)
        {
            WEB3GentradeBizDate l_gentradeBizDate = 
                new WEB3GentradeBizDate(
                    new Timestamp(l_tsBizDate.getTime()));
                    
            Date l_datPreBizDate = l_gentradeBizDate.calcFeqBizDate(
                l_strInstitutionCode, 
                l_strMarkets[i], 
                l_tsBizDate, 
                -1);
                
            boolean l_blnIsContain = false;
            for (int j = 0; j < l_bizDateList.size(); j++)
            {
                if (WEB3DateUtility.compareToDay(l_datPreBizDate, (Date)l_bizDateList.get(j)) == 0)
                {
                    l_blnIsContain = true;
                    break;
                }
            }
                
            //重複しない値のみArrayListに追加して下さい。
            if (!l_blnIsContain)
            {
                l_bizDateList.add(l_datPreBizDate);
            }                
        }

        //業務日付
        l_bizDateList.add(l_datBizDate);

        //1.10 (*)get取扱可能市場()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_strMarkets.length; i++)
        {
            //1.10.1 reset市場コード(市場コード : String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarkets[i]);
            
            //1.10.2 get発注日( )
            Date l_datBizDateTemp = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.10.3 (*)get発注日()の戻り値がArrayListに存在しない場合は、追加する。
            boolean l_blnExist = false;
            for (int j = 0; j < l_bizDateList.size(); j++)
            {
                if (WEB3DateUtility.compareToDay(l_datBizDateTemp, (Date)l_bizDateList.get(j)) == 0)
                {
                    l_blnExist = true;
                    break;
                }
            }
            if (!l_blnExist)
            {
                l_bizDateList.add(l_datBizDateTemp);                                
            }            
        }
        
        //1.11 取扱可能注文条件(
        //    証券会社コード : String, 
        //    銘柄タイプ : ProductTypeEnum, 
        //    先物／オプション区分 : String, 
        //    信用取引区分 : String)
        WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode, 
            ProductTypeEnum.FOREIGN_EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT);//WEB3SystemLayerException
            
        //1.12 取扱可能執行条件取得( )
        String[] l_strHandlingPossibleExecConds = l_orderCond.getHandlingPossibleExecCond();

        //1.13 取扱可能注文期限区分取得( )
        String[] l_strHandlingPossibleExpirationDateTypes = 
            l_orderCond.getHandlingPossibleExpirationDateType();

        //1.14 取扱可能発注条件取得( )
        String[] l_strHandlingPossibleOrderConds = l_orderCond.getHandlingPossibleOrderCond();
        
        //1.15 createResponse( )
        WEB3AdminORFeqOrderExecutionRefInputResponse l_response = 
            (WEB3AdminORFeqOrderExecutionRefInputResponse)l_request.createResponse();

        //1.16 プロパティセット
        //発注日一覧
        Date[] l_datBizDateList = new Date[l_bizDateList.size()];
        l_bizDateList.toArray(l_datBizDateList);
        for (int i = 0; i < l_datBizDateList.length; i++)
        {
            l_datBizDateList[i] = WEB3DateUtility.toDay(l_datBizDateList[i]);
        }
        l_response.orderBizDateList = l_datBizDateList;

        //市場コード一覧
        List l_lisMarketCodes = new ArrayList();
        for (int i = 0; i < l_lisMarketCode1.size(); i++)
        {
            l_lisMarketCodes.add(l_lisMarketCode1.get(i));
        }

        for (int j = 0; j < l_lisMarketCode2.size(); j++)
        {
            l_lisMarketCodes.add(l_lisMarketCode2.get(j));
        }

        String[] l_strMarketCodes = new String[l_lisMarketCodes.size()];
        l_lisMarketCodes.toArray(l_strMarketCodes);

        l_response.marketList = l_strMarketCodes;
        
        //執行条件一覧
        l_response.execCondTypeList = l_strHandlingPossibleExecConds;
        
        //注文期限区分一覧
        l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateTypes;
        
        //発注条件一覧
        l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;
        
        //取扱商品一覧  
        l_response.tradingProductList = l_tradingProductUnits;
        
        //注文経路区分一覧
        l_response.orderRootDivList = new String[]{
            WEB3OrderRootDivDef.CALLCENTER, 
            WEB3OrderRootDivDef.PC,
            WEB3OrderRootDivDef.SLINGSHOT,
            WEB3OrderRootDivDef.I_MODE,
            WEB3OrderRootDivDef.VODAFONE,
            WEB3OrderRootDivDef.AU,
            WEB3OrderRootDivDef.HOST};
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get照会画面)<BR>
     * 管理者外国株式注文約定照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式注文約定照会サービス)get照会画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・外国株式注文約定照会リクエストオブジェクト<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A67E9F003F
     */
    protected WEB3AdminORFeqOrderExecutionRefReferenceResponse getReferenceScreen(
        WEB3AdminORFeqOrderExecutionRefReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getReferenceScreen(WEB3AdminORFeqOrderExecutionRefReferenceRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者ロのグイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get運用コード(証券会社コード : String, 運用コード : String)
        //証券会社コード： get証券会社コード（）の戻り値
        //運用コード：リクエストデータ.運用コード
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.5 create共通検索条件文字列(証券会社コード : String, リクエストデータ : 管理者・注文約定照会共通リクエスト)
        WEB3AdminOrderExecuteDataManager l_dataManager = 
            new WEB3AdminOrderExecuteDataManager();
        String l_strCommonQueryString = 
            l_dataManager.createCommonQueryString(l_strInstitutionCode, l_request);
        
        //1.6 create検索条件文字列(String, String, String, String, String, String, boolean)
        String l_strQueryString = this.createQueryString(
            l_request.productCode,
            l_request.marketCode,
            l_request.taxType,
            l_request.execCondType,
            l_request.managementCode,
            l_request.updaterCode,
            false);
            
        //1.7 create共通検索条件データコンテナ(証券会社コード : String, リクエストデータ : 管理者・注文約定照会共通リクエスト)
        String[] l_strCommonQueryDataContainer = null;
        try
        {
            l_strCommonQueryDataContainer = 
                l_dataManager.createCommonQueryDataContainer(l_strInstitutionCode, l_request);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            throw l_ex;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        
        //1.8 create検索条件データコンテナ(String, String, String, String, String, String, String, boolean)
        String[] l_strQueryContainer = this.createQueryContainer(
            l_strInstitutionCode, 
            l_request.productCode,
            l_request.marketCode,
            l_request.taxType,
            l_request.execCondType,
            l_strEmpCode,
            l_request.updaterCode,
            false);
            
        //1.9 createソート条件(注文約定照会ソートキー[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.10 get注文単位一覧(String, String[], String)
        int l_intCommonLen = 0;
        if (l_strCommonQueryDataContainer != null)
        {
            l_intCommonLen = l_strCommonQueryDataContainer.length; 
        }
        int l_intSpecialLen = 0;
        if (l_strQueryContainer != null)
        {
            l_intSpecialLen = l_strQueryContainer.length;
        }
        String[] l_strQueryContainerAll =
            new String[l_intCommonLen + l_intSpecialLen];
        for (int i = 0; i < l_intCommonLen; i++)
        {
            l_strQueryContainerAll[i] = l_strCommonQueryDataContainer[i];
        }
        for (int i = 0; i < l_intSpecialLen; i++)
        {
            l_strQueryContainerAll[i + l_intCommonLen] = l_strQueryContainer[i];
        }
        WEB3FeqOrderUnit[] l_orderUnits = this.getOrderUnitList(
            l_strCommonQueryString + l_strQueryString,
            l_strQueryContainerAll,
            l_strSortCond);
        
        //1.11 remove繰越元注文単位(注文単位一覧 : 外国株式注文単位[])
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderUnit[] l_removedOrderUnits = 
            l_orderManager.removeCarryOverOriginOrder(l_orderUnits);
        
        //1.12 (*)remove繰越元注文単位()の戻り値 != nullの場合、以下の処理を実施する。
        WEB3AdminORFeqOrderExecutionRefUnit[] l_feqOrderExecInquiryUnits = null;
        WEB3AdminORDetailDispInfoUnit[] l_dispInfoUnits = null;
        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;
        int l_intPageIndexResponse = 0;
        if (l_removedOrderUnits != null && l_removedOrderUnits.length != 0)
        {
            // If this.pageIndex is not a numerical value, throw Exception.
            if (!WEB3StringTypeUtility.isNumber(l_request.pageIndex))
            {
                String l_strMessage = "要求ページ番号が数字以外の値です「" + l_request.pageIndex + "」。";
                log.debug(l_strMessage);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }

            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);

            // If l_request.Size is not a numerical value, throw Exception.
            if (!WEB3StringTypeUtility.isNumber(l_request.pageSize))
            {
                String l_strMessage = "ページ内表示行数が数字以外の値です「" + l_request.pageSize + "」。";
                log.debug(l_strMessage);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() +  STR_METHOD_NAME,
                    l_strMessage);
            }
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            //1.12.1 WEB3PageIndexInfo(arg0 : 論理ビュー::java::lang::Object[], arg1 : int, arg2 : int)
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
                l_removedOrderUnits,
                l_intPageIndex,
                l_intPageSize);
                
            //1.12.2 getArrayReturned(arg0 : Class)
            WEB3FeqOrderUnit[] l_arrayReturneds = 
                (WEB3FeqOrderUnit[])l_pageIndexInfo.getArrayReturned(WEB3FeqOrderUnit.class);
            
            //1.12.3 create外国株式注文約定照会Unit一覧(外国株式注文単位[])
            l_feqOrderExecInquiryUnits = 
                this.createFeqOrderExecInquiryUnitList(l_arrayReturneds);
                
            //1.12.4 create詳細画面情報一覧(管理者注文約定照会共通Unit[])
            l_dispInfoUnits = l_dataManager.createExecuteDetailsInfoList(
                l_feqOrderExecInquiryUnits);//WEB3BaseException
            
            //1.12.5 getTotalPages( )
            l_intTotalPages = l_pageIndexInfo.getTotalPages();
            
            //1.12.6 getTotalRecords( )
            l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
            
            //1.12.7 getPageIndex( )
            l_intPageIndexResponse = l_pageIndexInfo.getPageIndex();
        }
        
        //1.13 get証券会社( )
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution)l_admin.getInstitution();
        
        //1.14  get注文繰越処理区分(銘柄タイプ : ProductTypeEnum, 先物／オプション区分 : String)
        String l_strgetCarryoverEndType = l_institution.getCarryoverEndType(
            ProductTypeEnum.FOREIGN_EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT);//WEB3SystemLayerException
        
        //1.15 createResponse( )
        WEB3AdminORFeqOrderExecutionRefReferenceResponse l_response = 
            (WEB3AdminORFeqOrderExecutionRefReferenceResponse)l_request.createResponse();
            
        //1.16 プロパティセット
        //出来終了／注文繰越処理区分
        l_response.carryoverEndType = l_strgetCarryoverEndType;
        
        //総ページ数
        l_response.totalPages = l_intTotalPages + "";
        
        //総レコード数
        l_response.totalRecords = l_intTotalRecords + "";
        
        //表示ページ番号
        l_response.pageIndex = l_intPageIndexResponse + "";
        
        //詳細画面情報一覧
        l_response.detailDispInfoList = l_dispInfoUnits;
        
        //管理者外国株式注文約定照会Unit一覧
        l_response.feqOrderExecuteList = l_feqOrderExecInquiryUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get出来入力一覧)<BR>
     * 管理者外国株式出来入力一覧処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式注文約定照会サービス)get出来入力一覧」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・外国株式出来入力一覧リクエストオブジェクト<BR>
     * @@return WEB3AdminORFeqExecuteListResponse
     * @@throws WEB3BaseException
     * @@roseuid 42AD07A5002D
     */
    protected WEB3AdminORFeqExecuteListResponse getExecInputList(
        WEB3AdminORFeqExecuteListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getExecInputList(WEB3AdminORFeqExecuteListRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者ロのグイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException
        
        //1.4 validate部店権限(部店コード : String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3SystemLayerException    
        
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get運用コード(証券会社コード : String, 運用コード : String)
        //証券会社コード： get証券会社コード（）の戻り値
        //運用コード：リクエストデータ.運用コード
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.6 create共通検索条件文字列(String, 管理者・注文約定照会共通リクエスト)
        WEB3AdminOrderExecuteDataManager l_dataManager = new WEB3AdminOrderExecuteDataManager();
        String l_strCommonQueryString = 
            l_dataManager.createCommonQueryString(l_strInstitutionCode, l_request);

        //1.7 create検索条件文字列(String, String, String, String, String, String, boolean)
        String l_strQueryString = this.createQueryString(
            l_request.productCode,
            l_request.marketCode,
            null,
            null,
            l_request.managementCode,
            l_request.updaterCode,
            true);
            
        //1.8 create共通検索条件データコンテナ(証券会社コード : String, リクエストデータ : 管理者・注文約定照会共通リクエスト)
        String[] l_strCommonQueryDataContainers = null;
        try
        {
            l_strCommonQueryDataContainers = 
                l_dataManager.createCommonQueryDataContainer(l_strInstitutionCode, l_request);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            throw l_ex;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        
        //1.9 create検索条件データコンテナ(String, String, String, String, String, String, String, boolean)
        String[] l_strQueryContainer = this.createQueryContainer(
            l_strInstitutionCode, 
            l_request.productCode,
            l_request.marketCode,
            null,
            null,
            l_strEmpCode,
            l_request.updaterCode,
            true);

        //1.10 createソート条件（出来入力一覧）(注文約定照会ソートキー[])
        String l_strSortCond = this.createSortCondExecInputList(l_request.sortKeys);
        
        //1.11 get約定一覧(String, String[], String)
        int l_intCommonLen = 0;
        if (l_strCommonQueryDataContainers != null)
        {
            l_intCommonLen = l_strCommonQueryDataContainers.length; 
        }
        int l_intSpecialLen = 0;
        if (l_strQueryContainer != null)
        {
            l_intSpecialLen = l_strQueryContainer.length;
        }
        String[] l_strQueryContainerAll =
            new String[l_intCommonLen + l_intSpecialLen];
        for (int i = 0; i < l_intCommonLen; i++)
        {
            l_strQueryContainerAll[i] = l_strCommonQueryDataContainers[i];
        }
        for (int i = 0; i < l_intSpecialLen; i++)
        {
            l_strQueryContainerAll[i + l_intCommonLen] = l_strQueryContainer[i];
        }
        WEB3FeqOrderExecution[] l_orderExecutions= this.getExecList(
            l_strCommonQueryString + l_strQueryString,
            l_strQueryContainerAll,
            l_strSortCond);
            
        //1.12 get約定一覧の戻り値 != nullの場合、以下の処理を実施する。
        WEB3AdminORFeqExecutionUnit[] l_executionRefUnits = null;
        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;
        int l_intPageIndexResponse = 0;
        if (l_orderExecutions != null && l_orderExecutions.length != 0)
        {
            //1.12.1 WEB3PageIndexInfo(arg0 : 論理ビュー::java::lang::Object[], arg1 : int, arg2 : int)
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
                l_orderExecutions,
                l_intPageIndex,
                l_intPageSize);

            //1.12.2 getArrayReturned(arg0 : Class)
            WEB3FeqOrderExecution[] l_arrayReturneds = 
                (WEB3FeqOrderExecution[])l_pageIndexInfo.getArrayReturned(WEB3FeqOrderExecution.class);
                
            //1.12.3 create出来入力明細一覧(外国株式約定[])
            l_executionRefUnits = 
                this.createExecInputDetailList(l_arrayReturneds);    
            
            //1.12.5 getTotalPages( )
            l_intTotalPages = l_pageIndexInfo.getTotalPages();
            
            //1.12.6 getTotalRecords( )
            l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
            
            //1.12.7 getPageIndex( )
            l_intPageIndexResponse = l_pageIndexInfo.getPageIndex();

        }
        else
        {
            log.debug("該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");

        }
        
        //1.13 createResponse( )
        WEB3AdminORFeqExecuteListResponse l_response = 
            (WEB3AdminORFeqExecuteListResponse)l_request.createResponse();
        
        //1.14 プロパティセット
        //総ページ数
        l_response.totalPages = l_intTotalPages + "";
        
        //総レコード数
        l_response.totalRecords = l_intTotalRecords + "";
        
        //表示ページ番号
        l_response.pageIndex = l_intPageIndexResponse + "";

        //明細一覧
        l_response.executionList = l_executionRefUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create取扱商品一覧)<BR>
     * 引数の商品実施情報により取扱可能な商品と取引の組み合わせを作成し、<BR>
     * 返却する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）外国株式の商品区分と取引区分の組み合わせを作成する。<BR>
     * 　@処理対象の取引区分(*1)について、以下の処理を繰り返す。<BR>
     * 　@２－１）取扱商品インスタンスを生成する。<BR>
     * 　@２－２）生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@商品区分 = "外国株式"<BR>
     * 　@　@取引区分 = 処理対象の取引区分<BR>
     * 　@２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * 　@(*1)外国株式の処理対象取引区分<BR>
     * 　@　@・"外国株式買付"<BR>
     * 　@　@・"外国株式売付"<BR>
     * <BR>
     * ３）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param productExecInfo - (商品実施情報)<BR>
     * 商品実施情報オブジェクト<BR>
     * @@return 取扱商品[]
     * @@throws WEB3BaseException
     * @@roseuid 42A689DA0214
     */
    protected WEB3AdminORTradingProductUnit[] createTradeProductList(
        WEB3AdminProductExecutionInfo l_productExecInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createTradeProductList(WEB3AdminProductExecutionInfo )";
        log.entering(STR_METHOD_NAME);

        //１）ArrayListを生成する。
        ArrayList l_arrayList = new ArrayList();

        //２）外国株式の商品区分と取引区分の組み合わせを作成する。
        //　@処理対象の取引区分(*1)について、以下の処理を繰り返す。
        //　@２－１）取扱商品インスタンスを生成する。
        WEB3AdminORTradingProductUnit l_productUnit = new WEB3AdminORTradingProductUnit();
    
        //　@２－２）生成したインスタンスに以下のプロパティをセットする。
        //　@　@商品区分 = "外国株式"
        //　@　@取引区分 = 処理対象の取引区分
        l_productUnit.productDiv = WEB3AdminProductDivDef.FEQ;
        l_productUnit.tradingType = OrderTypeEnum.FEQ_BUY.intValue() + "";
        
        //　@２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。
        l_arrayList.add(l_productUnit);

        //　@２－１）取扱商品インスタンスを生成する。
        l_productUnit = new WEB3AdminORTradingProductUnit();
    
        //　@２－２）生成したインスタンスに以下のプロパティをセットする。
        //　@　@商品区分 = "外国株式"
        //　@　@取引区分 = 処理対象の取引区分
        l_productUnit = new WEB3AdminORTradingProductUnit();
        l_productUnit.productDiv = WEB3AdminProductDivDef.FEQ;
        l_productUnit.tradingType = OrderTypeEnum.FEQ_SELL.intValue() + "";

        //　@２－３）生成したArrayListにプロパティセットしたインスタンスを追加する。
        l_arrayList.add(l_productUnit);
        
        //３）生成したArrayList.toArray()の戻り値を返却する。
        WEB3AdminORTradingProductUnit[] l_productUnits = 
            new WEB3AdminORTradingProductUnit[l_arrayList.size()];
        l_arrayList.toArray(l_productUnits);
        
        log.exiting(STR_METHOD_NAME);        
        return l_productUnits;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * (createQueryString)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）パラメータ.銘柄コード != nullの場合、<BR>
     * 　@銘柄IDを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and product_id = ? "<BR>
     * <BR>
     * ３）パラメータ.市場コード != nullの場合、<BR>
     * 　@市場IDを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and market_id = ? "<BR>
     * <BR>
     * ４）パラメータ.口座区分 != nullの場合、<BR>
     * 　@税区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[パラメータ.口座区分 == "一般"の場合]<BR>
     * 　@　@検索条件文字列 += "and tax_type = ? "<BR>
     * 　@[パラメータ.口座区分 == "特定"の場合]<BR>
     * 　@　@検索条件文字列 += "and tax_type in (?, ?) "<BR>
     * <BR>
     * ５）パラメータ.執行条件 != nullの場合、<BR>
     * 　@執行条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and execution_condition_type = ? "<BR>
     * <BR>
     * ６）パラメータ.運用コード != nullの場合、<BR>
     * 　@運用コード条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and order_emp_code "<BR>
     * <BR>
     * ７）パラメータ.更新者コード != nullの場合、<BR>
     * 　@更新者コード条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and last_updater = ? "<BR>
     * <BR>
     * ８）パラメータ.is未削除のみ == trueの場合、<BR>
     * 　@削除フラグ条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and (delete_flag is null or delete_flag = ?) "<BR>
     * <BR>
     * ９）作成した検索条件文字列を返却する。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_strTaxType - (口座区分)<BR>
     * 口座区分<BR>
     * @@param l_strExecCond - (執行条件)<BR>
     * 執行条件<BR>
     * @@param l_strEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_blnIsOnlyUndeleted - (is未削除のみ)<BR>
     * 未削除のデータのみ抽出するかどうかのフラグ<BR>
     * <BR>
     * false：　@削除、未削除を抽出<BR>
     * true：　@未削除のみ抽出<BR>
     * @@return String
     * @@roseuid 42A68D460168
     */
    protected String createQueryString(
        String l_strProductCode, 
        String l_strMarketCode, 
        String l_strTaxType, 
        String l_strExecCond, 
        String l_strEmpCode, 
        String l_strUpdaterCode, 
        boolean l_blnIsOnlyUndeleted) 
    {
        final String STR_METHOD_NAME = 
            " createQueryString(String, String, String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //１）検索条件文字列インスタンス(：String)を生成する。
        String l_strQueryString = "";
        
        //２）パラメータ.銘柄コード != nullの場合、
        //　@銘柄IDを検索条件文字列に追加する。
        //　@検索条件文字列 += "and product_id = ? "
        if (l_strProductCode != null)
        {
            l_strQueryString += " and product_id = ? ";
        }

        //３）パラメータ.市場コード != nullの場合、
        //　@市場IDを検索条件文字列に追加する。
        //　@検索条件文字列 += "and market_id = ? "
        if (l_strMarketCode != null)
        {
            l_strQueryString += " and market_id = ? ";
        }

        //４）パラメータ.口座区分 != nullの場合、
        //　@税区分を検索条件文字列に追加する。
        //　@[パラメータ.口座区分 == "一般"の場合]
        //　@　@検索条件文字列 += "and tax_type = ? "
        //　@[パラメータ.口座区分 == "特定"の場合]
        //　@　@検索条件文字列 += "and tax_type in (?, ?) "
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
        {
            l_strQueryString += " and tax_type = ? ";
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType))
        {
            l_strQueryString += " and tax_type in (?, ?) ";
        }

        //５）パラメータ.執行条件 != nullの場合、
        //　@執行条件を検索条件文字列に追加する。
        //　@検索条件文字列 += "and execution_condition_type = ? "
        if (l_strExecCond != null)
        {
            l_strQueryString += " and execution_condition_type = ? ";
        }

        //６）パラメータ.運用コード != nullの場合、
        //　@運用コード条件を検索条件文字列に追加する。
        //　@検索条件文字列 += "and order_emp_code "
        if (l_strEmpCode != null)
        {
            l_strQueryString += " and order_emp_code = ? ";
        }

        //７）パラメータ.更新者コード != nullの場合、
        //　@更新者コード条件を検索条件文字列に追加する。
        //　@検索条件文字列 += "and last_updater = ? "
        if (l_strUpdaterCode != null)
        {
            l_strQueryString += " and last_updater = ? ";
        }

        //８）パラメータ.is未削除のみ == trueの場合、
        //　@削除フラグ条件を検索条件文字列に追加する。
        //　@検索条件文字列 += "and (delete_flag = null or delete_flag = ?) "
        if (l_blnIsOnlyUndeleted)
        {
            l_strQueryString += " and (delete_flag is null or delete_flag = ?) ";
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * (createQueryDataContainer)<BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメータ.銘柄コード != nullの場合、<BR>
     * 　@銘柄IDを生成したArrayListに追加する。<BR>
     * <BR>
     * 　@※銘柄IDは、外国株式プロダクトマネージャ.<BR>
     * 　@get外国株式銘柄()にて取得した外国株式銘柄.銘柄IDをセット。<BR>
     * <BR>
     * 　@[get外国株式銘柄()にセットするパラメータ]<BR>
     * 　@　@証券会社：　@拡張アカウントマネージャ.<BR>
     * 　@　@　@getInstitution(パラメータ.証券会社コード)にて取得した証券会社<BR>
     * 　@　@銘柄コード：　@パラメータ.銘柄コード<BR>
     * <BR>
     * ３）パラメータ.市場コード != nullの場合、<BR>
     * 　@市場IDを生成したArrayListに追加する。<BR>
     * <BR>
     * 　@※市場IDは、拡張金融オブジェクトマネージャ.get市場()にて<BR>
     * 　@　@取得した市場.市場IDをセット。<BR>
     * <BR>
     * 　@　@[get市場()にセットするパラメータ]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@市場コード：　@パラメータ.市場コード<BR>
     * <BR>
     * ４）パラメータ.口座区分 != nullの場合、<BR>
     * 　@以下の条件により、税区分を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.口座区分 == "一般"の場合]<BR>
     * 　@　@以下の値を追加<BR>
     * 　@　@・TaxTypeEnum.一般<BR>
     * <BR>
     * 　@[パラメータ.口座区分 == "特定"の場合]<BR>
     * 　@　@以下の値を追加<BR>
     * 　@　@・TaxTypeEnum.特定<BR>
     * 　@　@・TaxTypeEnum.特定かつ源泉徴収<BR>
     * <BR>
     * ５）パラメータ.執行条件 != nullの場合、<BR>
     * 　@執行条件を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@※執行条件は、外国株式注文マネージャ.get執行条件()にて取得する。<BR>
     * <BR>
     * 　@[get執行条件()にセットするパラメータ]<BR>
     * 　@　@執行条件：　@パラメータ.執行条件<BR>
     * <BR>
     * ６）パラメータ.運用コード != nullの場合、<BR>
     * 　@パラメータ.運用コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ７）パラメータ.更新者コード != nullの場合、<BR>
     * 　@パラメータ.更新者コードを生成したArrayListに追加する。<BR>
     * <BR>
     * ８）パラメータ.is未削除のみ == trueの場合、<BR>
     * 　@"0"を生成したArrayListに追加する。<BR>
     * <BR>
     * ９）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@param l_strTaxType - (口座区分)<BR>
     * 口座区分<BR>
     * @@param l_strExecCond - (執行条件)<BR>
     * 執行条件<BR>
     * @@param l_strEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@param l_strUpdaterCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@param l_blnIsOnlyUndeleted - (is未削除のみ)<BR>
     * 未削除のデータのみ抽出するかどうかのフラグ<BR>
     * <BR>
     * false：　@削除、未削除を抽出<BR>
     * true：　@未削除のみ抽出<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 42A68D46016F
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode, 
        String l_strProductCode, 
        String l_strMarketCode, 
        String l_strTaxType, 
        String l_strExecCond, 
        String l_strEmpCode, 
        String l_strUpdaterCode, 
        boolean l_blnIsOnlyUndeleted) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createQueryContainer(String, String, String, String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //１）ArrayListを生成する。
        ArrayList l_arrayList = new ArrayList();
        
        //２）パラメータ.銘柄コード != nullの場合、
        //　@銘柄IDを生成したArrayListに追加する。
        //　@※銘柄IDは、外国株式プロダクトマネージャ.
        //　@get外国株式銘柄()にて取得した外国株式銘柄.銘柄IDをセット。
        //　@[get外国株式銘柄()にセットするパラメータ]
        //　@　@証券会社：　@拡張アカウントマネージャ.
        //　@　@　@getInstitution(パラメータ.証券会社コード)にて取得した証券会社
        //　@　@銘柄コード：　@パラメータ.銘柄コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "拡張アカウントマネージャが存在しない。");
        }
        Institution l_institution;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "証券会社が存在しない。[証券会社code = " + l_strInstitutionCode + " ]";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
    
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "外国株式プロダクトマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_strProductCode != null)
        {
            
            FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = l_productManager.getFeqProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                    + l_strProductCode + "」";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage,
                    l_ex);
            }
            if (l_feqProduct == null)
            {
                String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                    + l_strProductCode + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            l_arrayList.add(l_feqProduct.getProductId() + "");
        }

        //３）パラメータ.市場コード != nullの場合、
        //　@市場IDを生成したArrayListに追加する。
        //　@※市場IDは、拡張金融オブジェクトマネージャ.get市場()にて
        //　@　@取得した市場.市場IDをセット。
        //　@　@[get市場()にセットするパラメータ]
        //　@　@　@証券会社コード：　@パラメータ.証券会社コード
        //　@　@　@市場コード：　@パラメータ.市場コード
        WEB3GentradeFinObjectManager l_finObjManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        if (l_finObjManager == null)
        {
            String l_strMessage = "拡張金融オブジェクトマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_strMarketCode != null)
        {
            Market l_market = null;
            try
            {
                l_market = l_finObjManager.getMarket(l_strInstitutionCode, l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "市場が存在しない。" 
                    + "証券会社コード = 「 " + l_strInstitutionCode 
                    + "」、市場コード =「" + l_strMarketCode 
                    + "」";
                log.error(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            l_arrayList.add(l_market.getMarketId() + "");
        }
        
        //４）パラメータ.口座区分 != nullの場合、
        //　@以下の条件により、税区分を生成したArrayListに追加する。
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
        {
            //　@[パラメータ.口座区分 == "一般"の場合]
            //　@　@以下の値を追加
            //　@　@・TaxTypeEnum.一般
            l_arrayList.add(TaxTypeEnum.NORMAL.intValue() + "");
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType))
        {
            //　@[パラメータ.口座区分 == "特定"の場合]
            //　@　@以下の値を追加
            //　@　@・TaxTypeEnum.特定
            //　@　@・TaxTypeEnum.特定かつ源泉徴収
            l_arrayList.add(TaxTypeEnum.SPECIAL.intValue() + "");
            l_arrayList.add(TaxTypeEnum.SPECIAL_WITHHOLD.intValue() + "");
        }
        
        //５）パラメータ.執行条件 != nullの場合、
        //　@執行条件を生成したArrayListに追加する。
        //　@※執行条件は、外国株式注文マネージャ.get執行条件()にて取得する。
        //　@[get執行条件()にセットするパラメータ]
        //　@　@執行条件：　@パラメータ.執行条件
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_strExecCond != null)
        {
            l_arrayList.add(l_orderManager.getExecutionCondition(l_strExecCond).intValue() + "");
        }
        
        //６）パラメータ.運用コード != nullの場合、
        //　@パラメータ.運用コードを生成したArrayListに追加する。
        if (l_strEmpCode != null)
        {
            l_arrayList.add(l_strEmpCode);
        }
        
        //７）パラメータ.更新者コード != nullの場合、
        //　@パラメータ.更新者コードを生成したArrayListに追加する。
        if (l_strUpdaterCode != null)
        {
            l_arrayList.add(l_strUpdaterCode);
        }
        
        //８）パラメータ.is未削除のみ == trueの場合、
        //　@"0"を生成したArrayListに追加する。
        if (l_blnIsOnlyUndeleted)
        {
            l_arrayList.add("0");
        }
        
        //９）生成したArrayList.toArray()の戻り値を返却する。
        String[] l_objects = 
            new String[l_arrayList.size()];
        l_arrayList.toArray(l_objects);
        
        log.exiting(STR_METHOD_NAME);
        return l_objects;
    }
    
    /**
     * (createソート条件)<BR>
     * (createSortCond)<BR>
     * ソート条件を作成する。<BR>
     * <BR>
     * １）ソート条件文字列(：String)を作成する。<BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@２－１）ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「注文ID」　@　@　@→　@外国株式注文単位.注文ID<BR>
     * 　@　@・「部店コード」　@→　@外国株式注文単位.部店ID<BR>
     * 　@　@・「顧客コード」　@→　@substr(外国株式注文単位.口座ID, 9, 6)<BR>
     * 　@　@・「銘柄コード」　@→　@外国株式注文単位.銘柄ID<BR>
     * 　@　@・「市場コード」　@→　@外国株式注文単位.市場ID<BR>
     * 　@　@・「口座区分」　@→　@外国株式注文単位.税区分<BR>
     * 　@　@・「取引区分」　@→　@外国株式注文単位.注文種別<BR>
     * 　@　@・「決済区分」　@→　@外国株式注文単位.決済区分<BR>
     * 　@　@・「注文時間」　@→　@外国株式注文単位.受注日時<BR>
     * 　@　@・「発注日」　@→　@外国株式注文単位.発注日<BR>
     * 　@　@・「執行条件」　@→　@外国株式注文単位.執行条件<BR>
     * 　@　@・「注文期限」　@→　@外国株式注文単位.注文失効日付<BR>
     * 　@　@・「発注条件」　@→　@外国株式注文単位.発注条件<BR>
     * 　@　@・「受渡日」　@→　@外国株式注文単位.受渡日<BR>
     * 　@　@・「運用コード」　@→　@外国株式注文単位.運用コード<BR>
     * <BR>
     * 　@２－２）ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ３）ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加<BR>
     * <BR>
     * ４）作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@roseuid 42A68D460178
     */
    protected String createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = 
            " createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] )";
        log.entering(STR_METHOD_NAME);
        
        //１）ソート条件文字列(：String)を作成する。
        String l_strSortCond = "";
        
        //２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        int l_intCount = 0; 
        if (l_sortKeys != null)
        {
            l_intCount = l_sortKeys.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //　@２－１）ソートキー.キー項目を対応する列物理名に変換し、
            //　@　@作成したソート条件文字列に追加する。
            //　@　@・「注文ID」　@　@　@→　@外国株式注文単位.注文ID
            //　@　@・「部店コード」　@→　@外国株式注文単位.部店ID
            //　@　@・「顧客コード」　@→　@substr(外国株式注文単位.口座ID, 9, 6)
            //　@　@・「銘柄コード」　@→　@外国株式注文単位.銘柄ID
            //　@　@・「市場コード」　@→　@外国株式注文単位.市場ID
            //　@　@・「口座区分」　@→　@外国株式注文単位.税区分
            //　@　@・「取引区分」　@→　@外国株式注文単位.注文種別
            //　@　@・「決済区分」　@→　@外国株式注文単位.決済区分
            //　@　@・「注文時間」　@→　@外国株式注文単位.受注日時
            //　@　@・「発注日」　@→　@外国株式注文単位.発注日
            //　@　@・「執行条件」　@→　@外国株式注文単位.執行条件
            //　@　@・「注文期限」　@→　@外国株式注文単位.注文失効日付
            //　@　@・「発注条件」　@→　@外国株式注文単位.発注条件
            //　@　@・「受渡日」　@→　@外国株式注文単位.受渡日
            //　@　@・「運用コード」　@→　@外国株式注文単位.運用コード
            if (i != 0)
            {
                l_strSortCond += " , ";
            }
            
            String l_strKeyItem = l_sortKeys[i].keyItem;
            if (WEB3AdminFeqSortKeyDef.ORDER_ID.equals(l_strKeyItem))
            {
                l_strSortCond += " order_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " branch_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " substr(account_id, 9, 6) ";
            }
            else if (WEB3AdminFeqSortKeyDef.PRODUCT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " product_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.MARKET_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " market_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.TAX_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " tax_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.TRADING_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.SETTLE_DIV.equals(l_strKeyItem))
            {
                l_strSortCond += " settle_div ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_START_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " received_date_time ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_BIZ_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " biz_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.EXECCOND_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " execution_condition_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.EXPIRATION_DATE_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " expiration_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_COND_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_condition_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.DELIVERY_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " delivery_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.MANAGEMENT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_emp_code ";
            }
        
            //　@２－２）ソートキー.昇順／降順に対応する取得順序
            //　@　@(asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " asc ";
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " desc ";
            }
        }
        
        //３）ソート条件末尾に、注文単位テーブル.更新日付を昇順指定で付加
        if (l_intCount != 0)
        {
            l_strSortCond += " , ";
        }
        l_strSortCond += " last_updated_timestamp asc ";
        
        //４）作成したソート条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
    
    /**
     * (get注文単位一覧)<BR>
     * 引数の条件に該当する注文単位の一覧を返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@外国株式注文単位Row.TYPE<BR>
     * 　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ３）検索結果より、外国株式注文単位の配列を生成し、返却する。<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A68D46017A
     */
    protected WEB3FeqOrderUnit[] getOrderUnitList(
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getOrderUnitList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        //　@[doFindAllQuery()にセットするパラメータ]
        //　@　@arg0：　@外国株式注文単位Row.TYPE
        //　@　@arg1：　@パラメータ.検索条件文字列
        //　@　@arg2：　@パラメータ.ソート条件
        //　@　@arg3：　@null
        //　@　@arg4：　@パラメータ.検索条件データコンテナ
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (IllegalStateException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //　@検索結果が取得できなかった場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            return null;
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //３）検索結果より、外国株式注文単位の配列を生成し、返却する。
        int l_intCount = l_lisRecords.size();
        WEB3FeqOrderUnit[] l_orderUnits = new WEB3FeqOrderUnit[l_intCount];
        for (int i = 0; i < l_intCount; i++)
        {
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            l_orderUnits[i] = (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_orderUnitRow);
        }
        
        
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }
    
    /**
     * (create外国株式注文約定照会Unit一覧)<BR>
     * 引数の注文単位一覧より、外国株式注文約定照会Unitの一覧を<BR>
     * 作成し、返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者外国株式注文約定照会サービス)create外国株式注文約定照会Unit一覧」<BR>
     * 参照<BR>
     * @@param l_orderUnits - (注文単位一覧)<BR>
     * 外国株式注文単位オブジェクトの配列<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefUnit
     * @@throws WEB3BaseException
     * @@roseuid 42A6924C0272
     */
    protected WEB3AdminORFeqOrderExecutionRefUnit[] createFeqOrderExecInquiryUnitList(
        WEB3FeqOrderUnit[] l_orderUnits) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createFeqOrderExecInquiryUnitList(WEB3FeqOrderUnit )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();
        
        //1.2 パラメータ.注文単位一覧の要素数分Loop処理
        int l_intCount = 0;
        if (l_orderUnits != null)
        {
            l_intCount = l_orderUnits.length;
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "FinAppが存在しない。");
        }

        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();        
        if (l_gentradeFinObjectManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "拡張金融オブジェクトマネージャが存在しない。");
        }
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3AdminOrderExecuteDataManager l_dataManager = new WEB3AdminOrderExecuteDataManager();
        for (int i = 0; i < l_intCount; i++)
        {
            //1.2.1 getProduct( )
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnits[i].getProduct();
            
            //1.2.2 isMarketOrder( )
            boolean l_blnIsMarketOrder = l_orderUnits[i].isMarketOrder();
            
            //1.2.3 get部店コード( )
            String l_strBranchCode = l_orderUnits[i].getBranchCode();
            
            //1.2.4 get顧客( )
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_orderUnits[i].getMainAccount();
            
            //1.2.5 get市場( )
            Market l_market = l_orderUnits[i].getMarket();
            
            //1.2.6 get注文状態区分( )
            String l_strOrderStatusDiv = l_orderUnits[i].getOrderStatusDiv();
            
            //1.2.7 get約定状態区分( )
            String l_strExecStatusDiv = l_orderUnits[i].getExecStatusDiv();
            
            //1.2.8 get運用コード( )
            String l_strOrderEmpCode = l_orderUnits[i].getOrderEmpCode();
            
            //1.2.9 getTrader(arg0 : long)
            Trader l_trader = null;
            FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnits[i].getDataSourceObject();
            if (!l_row.getTraderIdIsNull())
            {
                try
                {
                    l_trader = l_gentradeFinObjectManager.getTrader(l_orderUnits[i].getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    String l_strMessage = "Traderャが存在しない。TraderId = 「" 
                        + l_orderUnits[i].getTraderId() + "」";
                    log.error(l_strMessage, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage,
                        l_ex);
                }
            }
            
            //1.2.10 get取引区分(OrderTypeEnum)
            String l_strTradingType = l_dataManager.getTradingType(l_orderUnits[i].getOrderType());
            
            //1.2.11 get執行条件（SONAR）(執行条件 : String)
            String l_strExecutionConditionTypeSonar = l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnits[i].getExecutionConditionType().intValue() + "");//WEB3BusinessLayerException

            //1.2.12 is出来るまで注文単位(注文単位 : FeqOrderUnit)
            boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnits[i]);
            
            //1.2.13 管理者外国株式注文約定照会Unit( )
            WEB3AdminORFeqOrderExecutionRefUnit l_feqOrderExecutionRefUnit = 
                new WEB3AdminORFeqOrderExecutionRefUnit();
                
            //1.2.14 プロパティセット
            //********** 共通部分 **********
            //ID     ＝　@注文単位.注文ID
            l_feqOrderExecutionRefUnit.id = l_orderUnits[i].getOrderId() + "";
            
            //部店コード  ＝　@get部店コード()の戻り値
            l_feqOrderExecutionRefUnit.branchCode = l_strBranchCode;
            
            //顧客コード  ＝　@get顧客()の戻り値.get表示顧客コード()
            l_feqOrderExecutionRefUnit.accountCode = l_mainAccount.getDisplayAccountCode();
            
            //商品区分   ＝　@"外国株式"
            l_feqOrderExecutionRefUnit.productDiv = WEB3AdminProductDivDef.FEQ;
            
            //取引区分   ＝　@get取引区分()の戻り値をセット。
            l_feqOrderExecutionRefUnit.tradingType = l_strTradingType;
            
            //注文数量   ＝　@注文単位.注文数量
            l_feqOrderExecutionRefUnit.orderQuantity = 
                WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getQuantity());
            
            //注文単価区分 ＝　@isMarketOrder()の戻り値 == trueの場合、"成行"をセット。
            //　@　@　@                    falseの場合、"指値"をセット。
            if (l_blnIsMarketOrder)
            {
                l_feqOrderExecutionRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;

                //注文単価   ＝　@注文単価区分 ==　@"指値"の場合、注文単位.指値をセット。
            }
            else
            {
                l_feqOrderExecutionRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_feqOrderExecutionRefUnit.orderPrice = 
                    WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getLimitPrice());
            }
            
            //概算受渡代金 ＝　@(*3)
            // 注文単位.get約定状態区分() == "一部成立" or "全部成立"の場合、以下の値をセットする。
            // 　@概算受渡代金    ＝　@外国株式トランザクションマネージャ.getトランザクション(注文単位)にて取得した
            // 　@　@　@　@　@　@　@　@　@　@　@トランザクション.受渡代金の合計値
            WEB3FeqFinTransactionManager l_finTransaction = 
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_strExecStatusDiv) ||
                WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_strExecStatusDiv))
            {
                double l_dblNetAmount = l_finTransaction.getNetAmount(l_orderUnits[i]);
                //買い注文の場合、’×(-1)’してからセット
                if(l_orderUnits[i].isBuy()){
                    l_feqOrderExecutionRefUnit.estimatedPrice = 
                                        WEB3StringTypeUtility.formatNumber(l_dblNetAmount * (-1));
                }else{
                    l_feqOrderExecutionRefUnit.estimatedPrice = 
                                        WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
                }
            }
                
            //注文チャネル ＝　@注文単位.初回注文の注文チャネル
            l_feqOrderExecutionRefUnit.orderChannel = l_row.getOrderChanel();
            
            //注文状態区分 ＝　@get注文状態区分()の戻り値
            l_feqOrderExecutionRefUnit.orderState = l_strOrderStatusDiv;
            
            //注文時間   ＝　@注文単位.受注日時
            l_feqOrderExecutionRefUnit.orderDate = l_row.getReceivedDateTime();
             
            //発注日        ＝　@注文単位.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            l_feqOrderExecutionRefUnit.orderBizDate = 
                WEB3DateUtility.toDay(l_datBizDate);
            
            //執行条件   ＝　@get執行条件（SONAR）()の戻り値
            l_feqOrderExecutionRefUnit.execCondType = l_strExecutionConditionTypeSonar;
            
            //注文有効期限 ＝　@is出来るまで注文単位()の戻り値 == trueの場合、注文単位.注文失効日付をセット。
            if (l_blnIsCarriedOrderUnit)
            {
                l_feqOrderExecutionRefUnit.expirationDate =
                    WEB3DateUtility.toDay(l_row.getExpirationDate()); 
            }
            
            //発注条件区分 ＝　@注文単位.発注条件
            l_feqOrderExecutionRefUnit.orderCondType = 
                l_row.getOrderConditionType();
                
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_row.getOrderConditionType())
                || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_row.getOrderConditionType()))
            {
                //発注条件単価 ＝　@(*1)
                if (!l_row.getStopOrderPriceIsNull())
                {
                    l_feqOrderExecutionRefUnit.orderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_row.getStopOrderPrice());                
                }
                //発注条件演算子    ＝　@(*1)
                l_feqOrderExecutionRefUnit.condOperator = l_row.getOrderCondOperator();
            }
            
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_row.getOrderConditionType()))
            {
                l_feqOrderExecutionRefUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                if (!l_row.getWLimitPriceIsNull() && l_row.getWLimitPrice() != 0)
                {
                    //W指値用注文単価   ＝　@(*2)
                    l_feqOrderExecutionRefUnit.wlimitOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_row.getWLimitPrice());
                }
                else
                {
                    //W指値用注文単価区分 ＝　@(*2)
                    l_feqOrderExecutionRefUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
            }
            
            //約定数量   ＝　@(*3)
            if (!l_orderUnits[i].isUnexecuted())
            {
                l_feqOrderExecutionRefUnit.execQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getExecutedQuantity());
            } 
            
            //約定単価   ＝　@(*3)
            if (!l_orderUnits[i].isUnexecuted())
            {

               //注文単位.合計約定金額(外貨) ／ 注文単位.約定数量
                BigDecimal l_bdExecutedAmount = new BigDecimal(
                    String.valueOf(l_orderUnits[i].getExecutedAmount()));
                BigDecimal l_bdExecutedQuantity = new BigDecimal(
                    String.valueOf(l_orderUnits[i].getExecutedQuantity()));
                BigDecimal l_bdExecPriceTemp =
                    l_bdExecutedAmount.divide(l_bdExecutedQuantity, 6, BigDecimal.ROUND_HALF_EVEN);

                l_feqOrderExecutionRefUnit.execPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdExecPriceTemp.doubleValue());

            }
            
            //扱者コード  ＝　@注文単位.取引者ID != nullの場合、取引者IDに該当する扱者.扱者コードをセット。
            if (l_trader != null)
            {
                l_feqOrderExecutionRefUnit.traderCode = l_trader.getTraderCode();
            }
            
            //約定状態区分 ＝　@get約定状態区分()の戻り値
            l_feqOrderExecutionRefUnit.execType = l_strExecStatusDiv;
            
            //訂正取消区分 ＝　@注文単位.注文訂正・取消区分
            l_feqOrderExecutionRefUnit.changeCancelDiv = l_row.getModifyCancelType();
            
            //注文経路区分 ＝　@注文単位.注文経路区分
            l_feqOrderExecutionRefUnit.orderRootDiv = l_row.getOrderRootDiv();
            
            //受渡日        ＝　@注文単位.受渡日
            l_feqOrderExecutionRefUnit.deliveryDate = 
                WEB3DateUtility.toDay(l_orderUnits[i].getDeliveryDate());
            
            //********** Feq特化部分 **********
            //伝票No       ＝　@注文単位.伝票No
            l_feqOrderExecutionRefUnit.orderNumber = l_row.getVoucherNo();
            
            //銘柄コード  ＝　@getProduct()の戻り値.銘柄コード
            l_feqOrderExecutionRefUnit.productCode = l_product.getProductCode();
            
            //現地銘柄コード    ＝　@getProduct()の戻り値.現地銘柄コード
            l_feqOrderExecutionRefUnit.localProductCode = l_product.getOffshoreProductCode();
            
            //市場コード  ＝　@get市場()の戻り値.市場コード
            l_feqOrderExecutionRefUnit.marketCode = l_market.getMarketCode();
            
            //口座区分   ＝　@注文単位.税区分 == "一般"の場合、"一般"をセット。"特定"の場合、"特定"をセット。
            TaxTypeEnum l_taxType = l_orderUnits[i].getTaxType();
            if (TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_feqOrderExecutionRefUnit.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_taxType))
            {
                l_feqOrderExecutionRefUnit.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }

            //運用コード  ＝　@get運用コード()の戻り値
            l_feqOrderExecutionRefUnit.managementCode = l_strOrderEmpCode;
            
            //決済区分   ＝　@注文単位.決済区分
            l_feqOrderExecutionRefUnit.settleDiv = l_row.getSettleDiv();
            
            //通貨コード  ＝　@注文単位.通貨コード
            l_feqOrderExecutionRefUnit.currencyCode = l_row.getCurrencyCode();
            
            //受渡代金（外貨）   ＝　@(*3)
            // 　@注文単位.get約定状態区分() == "一部成立" or "全部成立"の場合、以下の値をセットする。
            // 受渡代金（外貨）    ＝　@外国株式トランザクションマネージャ.getトランザクション(注文単位)にて取得した
            // 　@　@　@　@　@　@　@　@　@　@　@　@トランザクション.受渡代金（外貨）の合計値
            if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_strExecStatusDiv) ||
                WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_strExecStatusDiv))
            {
                double l_dblNetAmountFc = l_finTransaction.getNetAmountFc(l_orderUnits[i]);
                //買い注文の場合、’×(-1)’してからセット
                if(l_orderUnits[i].isBuy()){
                    l_feqOrderExecutionRefUnit.foreignDeliveryPrice = 
                                WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc * (-1));
                }else{
                    l_feqOrderExecutionRefUnit.foreignDeliveryPrice = 
                                WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc);
                }
            }            
            
            //更新者コード ＝　@注文単位.更新者コード
            l_feqOrderExecutionRefUnit.updaterCode = l_row.getLastUpdater();
            
            //(*1)
            //注文単位.発注条件 == ("逆指値" or "W指値")の場合、以下の値をセット。
            //　@　@            発注条件単価    ＝　@注文単位.逆指値基準値
            //　@　@            発注条件演算子   ＝　@注文単位.発注条件演算子
            //
            //(*2)
            //注文単位.発注条件 == "W指値"の場合、以下の値をセット。
            //W指値用注文単価区分    ＝　@W指値用注文単価 == 0の場合、"成行"をセット。
            //　@　@　@                        以外、"指値"をセット。
            //W指値用注文単価  ＝　@注文単位.(W指値)訂正指値
            //　@　@　@                        ※注文単位.(W指値)訂正指値 == 0の場合、nullをセット。
            //
            //(*3)
            //約定がついている場合(注文単位.isUnExecuted() == false)、以下の値をセット。
            //概算受渡代金    ＝　@外国株式トランザクションマネージャ.getトランザクション(注文単位)にて取得した
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@            トランザクション.受渡代金の合計値
            //受渡代金（外貨）  ＝　@外国株式トランザクションマネージャ.getトランザクション(注文単位)にて取得した
            //　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@            トランザクション.受渡代金（外貨）の合計値
            //約定数量  ＝　@注文単位.約定数量
            //約定単価  ＝　@注文単位.合計約定金額 / 注文単位.約定数量(円未満は四捨五入)
            
            //1.2.15 add(arg0 : Object)
            l_arrayList.add(l_feqOrderExecutionRefUnit);
        }
        
        //1.3 toArray( )
        WEB3AdminORFeqOrderExecutionRefUnit[] l_feqOrderExecutionRefUnits = 
            new WEB3AdminORFeqOrderExecutionRefUnit[l_arrayList.size()];
        l_arrayList.toArray(l_feqOrderExecutionRefUnits);

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderExecutionRefUnits;
    }
    
    /**
     * (createソート条件（出来入力一覧）)<BR>
     * 出来入力一覧用のソート条件を作成する。<BR>
     * <BR>
     * １）ソート条件文字列(：String)を作成する。<BR>
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@２－１）ソートキー.キー項目を対応する列物理名に変換し、<BR>
     * 　@　@作成したソート条件文字列に追加する。<BR>
     * <BR>
     * 　@　@・「注文ID」　@→　@外国株式約定.注文ID<BR>
     * 　@　@・「運用コード」　@→　@外国株式約定.運用コード<BR>
     * 　@　@・「部店コード」　@→　@外国株式約定.部店ID<BR>
     * 　@　@・「顧客コード」　@→　@substr(外国株式約定.口座ID, 9, 6)<BR>
     * 　@　@・「銘柄コード」　@→　@外国株式約定.銘柄ID<BR>
     * 　@　@・「市場コード」　@→　@外国株式約定.市場ID<BR>
     * 　@　@・「識別コード」　@→　@外国株式約定.識別コード<BR>
     * 　@　@・「約定番号」　@→　@外国株式約定.約定通番<BR>
     * 　@　@・「取引区分」　@→　@外国株式約定.注文種別<BR>
     * 　@　@・「発注日」　@→　@外国株式約定.発注日<BR>
     * 　@　@・「受渡日」　@→　@外国株式約定.現地受渡日<BR>
     * <BR>
     * 　@２－２）ソートキー.昇順／降順に対応する取得順序<BR>
     * 　@　@(asc or desc)をソート条件文字列に追加する。<BR>
     * <BR>
     * ３）ソート条件末尾に、約定テーブル.更新日付を昇順指定で付加<BR>
     * <BR>
     * ４）作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@roseuid 42AD09470201
     */
    protected String createSortCondExecInputList(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = 
            " createSortCondExecInputList(WEB3AdminOROrderExecutionSortKeyUnit )";
        log.entering(STR_METHOD_NAME);
        
        //１）ソート条件文字列(：String)を作成する。
        String l_strSortCond = "";
        
        //２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        int l_intCount = 0; 
        if (l_sortKeys != null)
        {
            l_intCount = l_sortKeys.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //　@２－１）ソートキー.キー項目を対応する列物理名に変換し、
            //　@　@作成したソート条件文字列に追加する。
            //　@　@・「注文ID」　@→　@外国株式約定.注文ID
            //　@　@・「運用コード」　@→　@外国株式約定.運用コード
            //　@　@・「部店コード」　@→　@外国株式約定.部店ID
            //　@　@・「顧客コード」　@→　@substr(外国株式約定.口座ID, 9, 6)
            //　@　@・「銘柄コード」　@→　@外国株式約定.銘柄ID
            //　@　@・「市場コード」　@→　@外国株式約定.市場ID
            //　@　@・「識別コード」　@→　@外国株式約定.識別コード
            //　@　@・「約定番号」　@→　@外国株式約定.約定通番
            //　@　@・「取引区分」　@→　@外国株式約定.注文種別
            //　@　@・「発注日」　@→　@外国株式約定.発注日
            //　@　@・「受渡日」　@→　@外国株式約定.現地受渡日
            if (i != 0)
            {
                l_strSortCond += " , ";
            }
            
            String l_strKeyItem = l_sortKeys[i].keyItem;
            if (WEB3AdminFeqSortKeyDef.ORDER_ID.equals(l_strKeyItem))
            {
                l_strSortCond += " order_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.MANAGEMENT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_emp_code ";
            }
            else if (WEB3AdminFeqSortKeyDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " branch_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " substr(account_id, 9, 6) ";
            }
            else if (WEB3AdminFeqSortKeyDef.PRODUCT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " product_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.MARKET_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " market_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.REQUEST_NUMBER.equals(l_strKeyItem))
            {
                l_strSortCond += " order_request_number ";
            }
            else if (WEB3AdminFeqSortKeyDef.EXEC_NO.equals(l_strKeyItem))
            {
                l_strSortCond += " exec_serial_no ";
            }
            else if (WEB3AdminFeqSortKeyDef.TRADING_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_BIZ_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " biz_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.DELIVERY_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " f_delivery_date ";
            }

            //　@２－２）ソートキー.昇順／降順に対応する取得順序
            //　@　@(asc or desc)をソート条件文字列に追加する。
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " asc ";
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " desc ";
            }
        }

        
        //３）ソート条件末尾に、約定テーブル.更新日付を昇順指定で付加
        if (l_intCount != 0)
        {
            l_strSortCond += " , ";
        }
        l_strSortCond += " last_updated_timestamp asc ";
        
        //４）作成したソート条件文字列を返却する。
        
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
    
    /**
     * (get約定一覧)<BR>
     * 引数の条件に該当する約定の一覧を返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@arg0：　@外国株式約定Row.TYPE<BR>
     * 　@　@arg1：　@パラメータ.検索条件文字列<BR>
     * 　@　@arg2：　@パラメータ.ソート条件<BR>
     * 　@　@arg3：　@null<BR>
     * 　@　@arg4：　@パラメータ.検索条件データコンテナ<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * ３）検索結果より、外国株式約定の配列を生成し、返却する。<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_strQueryContainers - (検索条件データコンテナ)<BR>
     * 検索条件データコンテナ<BR>
     * @@param l_strSortCond - (ソート条件)<BR>
     * ソート条件<BR>
     * @@return 外国株式約定
     * @@throws WEB3BaseException
     * @@roseuid 42AD0925005B
     */
    protected WEB3FeqOrderExecution[] getExecList(
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getExecList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //１）QueryProcessor.doFindAllQuery()メソッドをコールする。
        //　@[doFindAllQuery()にセットするパラメータ]
        //　@　@arg0：　@外国株式約定Row.TYPE
        //　@　@arg1：　@パラメータ.検索条件文字列
        //　@　@arg2：　@パラメータ.ソート条件
        //　@　@arg3：　@null
        //　@　@arg4：　@パラメータ.検索条件データコンテナ
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderExecutionRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (IllegalStateException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //　@検索結果が取得できなかった場合、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            return null;
        }
        
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //３）検索結果より、外国株式約定の配列を生成し、返却する。
        int l_intCount = l_lisRecords.size();
        WEB3FeqOrderExecution[] l_orderExcutions = new WEB3FeqOrderExecution[l_intCount];
        for (int i = 0; i < l_intCount; i++)
        {
            WEB3FeqOrderExecution l_orderExcution = (WEB3FeqOrderExecution)
                l_orderManager.toOrderExecution((FeqOrderExecutionRow)l_lisRecords.get(i));
            l_orderExcutions[i] = l_orderExcution;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExcutions;
    }
    
    /**
     * (create出来入力明細一覧)<BR>
     * 引数の約定一覧より、外国株式出来入力明細の一覧を<BR>
     * 作成し、返却する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.約定一覧の要素数分、以下の処理を繰り返す。<BR>
     * 　@２－１）　@外国株式出来入力明細インスタンスを生成する。<BR>
     * 　@２－２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@ID　@＝　@約定.注文ID<BR>
     * 　@　@運用コード　@＝　@約定.運用コード<BR>
     * 　@　@識別コード　@＝　@約定.識別コード<BR>
     * 　@　@部店コード　@＝　@約定.部店IDに該当する部店コード<BR>
     * 　@　@顧客コード　@＝　@約定.口座IDに該当する顧客.get表示顧客コード()<BR>
     * 　@　@銘柄コード　@＝　@約定.銘柄IDに該当する銘柄コード<BR>
     * 　@　@現地銘柄コード　@＝　@約定.銘柄IDに該当する現地銘柄コード<BR>
     * 　@　@取引区分　@＝　@管理者注文約定照会データマネージャ.get取引区分<BR>
     * (約定.注文種別)<BR>
     * 　@　@市場コード　@＝　@約定.市場IDに該当する市場コード<BR>
     * 　@　@発注日　@＝　@約定.発注日<BR>
     * 　@　@約定番号　@＝　@約定.約定通番を、pattern="000"でフォーマットした文字列。<BR>
     * 　@　@約定単価　@＝　@約定.約定単価<BR>
     * 　@　@約定数量　@＝　@約定.約定数量<BR>
     * 　@　@約定為替　@＝　@約定.為替レート<BR>
     * 　@　@約定日　@＝　@約定.約定日<BR>
     * 　@　@受渡日　@＝　@約定.受渡日<BR>
     * 　@　@現地受渡日　@＝　@約定.現地受渡日<BR>
     * 　@　@更新者コード　@＝　@約定.更新者コード<BR>
     * <BR>
     * 　@２－３）　@ArrayListにプロパティセットしたインスタンスを追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_execs - (約定一覧)<BR>
     * 外国株式約定オブジェクトの配列<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefUnit
     * @@throws WEB3BaseException
     */
    protected WEB3AdminORFeqExecutionUnit[] createExecInputDetailList(
        WEB3FeqOrderExecution[] l_orderExecution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createExecInputDetailList(WEB3FeqOrderExecution[])";
        log.entering(STR_METHOD_NAME);
        
        //１）　@ArrayListを生成する。
        ArrayList l_arrayList = new ArrayList();
        
        //２）　@パラメータ.約定一覧の要素数分、以下の処理を繰り返す。
        int l_intCount = 0;
        if (l_orderExecution != null)
        {
            l_intCount = l_orderExecution.length;
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "拡張アカウントマネージャが存在しない。");
        }
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "外国株式プロダクトマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //　@２－１）　@外国株式出来入力明細インスタンスを生成する。
            WEB3AdminORFeqExecutionUnit l_feqExecutionUnit = new WEB3AdminORFeqExecutionUnit();            
            
            FeqOrderExecutionRow l_row = 
                (FeqOrderExecutionRow)l_orderExecution[i].getDataSourceObject(); 
            //　@２－２）　@生成したインスタンスに以下のプロパティをセットする。        
            //　@　@ID　@＝　@約定.注文ID
            l_feqExecutionUnit.id = l_orderExecution[i].getOrderId() + "";
            
            //　@　@運用コード　@＝　@約定.運用コード
            l_feqExecutionUnit.managementCode = l_row.getOrderEmpCode();
                        
            //　@　@識別コード　@＝　@約定.識別コード
            l_feqExecutionUnit.requestNumber = l_row.getOrderRequestNumber();
            
            //　@　@部店コード　@＝　@約定.部店IDに該当する部店コード
            WEB3GentradeBranch l_branch = null;
            try
            {
                l_branch = 
                    (WEB3GentradeBranch) l_accountManager.getBranch(l_orderExecution[i].getBranchId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "部店が存在しない。";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.branchCode = l_branch.getBranchCode();
            
            //　@　@顧客コード　@＝　@約定.口座IDに該当する顧客.get表示顧客コード()
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = (WEB3GentradeMainAccount) 
                    l_accountManager.getMainAccount(l_orderExecution[i].getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "顧客が存在しない。";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.accountCode = l_mainAccount.getDisplayAccountCode();
            
            //　@　@銘柄コード　@＝　@約定.銘柄IDに該当する銘柄コード
            WEB3FeqProduct l_product = null;
            try
            {
                l_product = 
                    (WEB3FeqProduct) l_productManager.getProduct(l_orderExecution[i].getProductId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "外国株式銘柄が存在しない。";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.productCode = l_product.getProductCode();
            
            //　@　@現地銘柄コード　@＝　@約定.銘柄IDに該当する現地銘柄コード
            l_feqExecutionUnit.localProductCode = l_product.getOffshoreProductCode();
            
            //　@　@取引区分　@＝　@管理者注文約定照会データマネージャ.get取引区分
            //(約定.注文種別)
            WEB3AdminOrderExecuteDataManager l_dataManager = new WEB3AdminOrderExecuteDataManager();
            l_feqExecutionUnit.tradingType = 
                l_dataManager.getTradingType(l_orderExecution[i].getOrderType());
            
            //　@　@市場コード　@＝　@約定.市場IDに該当する市場コード
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            if (l_finObjManager == null)
            {
                String l_strMessage = "拡張金融オブジェクトマネージャが存在しない。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            Market l_market = null; 
            try
            {
                l_market = l_finObjManager.getMarket(l_orderExecution[i].getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "市場が存在しない。";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.marketCode = l_market.getMarketCode();
            
            //　@　@発注日　@＝　@約定.発注日
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            l_feqExecutionUnit.orderBizDate = WEB3DateUtility.toDay(l_datBizDate);
            
            //　@　@約定番号　@＝　@約定.約定通番を、pattern="000"でフォーマットした文字列。
            l_feqExecutionUnit.execNo = 
                WEB3StringTypeUtility.formatNumber(l_row.getExecSerialNo(), 3);
            
            //　@　@約定単価　@＝　@約定.約定単価
            if (!l_row.getExecPriceIsNull())
            {
                l_feqExecutionUnit.execPrice = 
                    WEB3StringTypeUtility.formatNumber(l_row.getExecPrice());            
            }
            
            //　@　@約定数量　@＝　@約定.約定数量
            l_feqExecutionUnit.execQuantity = 
                WEB3StringTypeUtility.formatNumber(l_row.getExecQuantity());
            
            //　@　@約定為替　@＝　@約定.為替レート
            l_feqExecutionUnit.execExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_row.getFxRate());
            
            //　@　@約定日　@＝　@約定.約定日
            l_feqExecutionUnit.executionDate = l_row.getExecTimestamp();
            
            //　@　@受渡日　@＝　@約定.受渡日
            l_feqExecutionUnit.deliveryDate = WEB3DateUtility.toDay(l_row.getDeliveryDate());
            
            //　@　@現地受渡日　@＝　@約定.現地受渡日
            l_feqExecutionUnit.localDeliveryDate = WEB3DateUtility.toDay(l_row.getFDeliveryDate());
            
            //　@　@更新者コード　@＝　@約定.更新者コード
            l_feqExecutionUnit.updaterCode = l_row.getLastUpdater();
        
            //　@２－３）　@ArrayListにプロパティセットしたインスタンスを追加する。
            l_arrayList.add(l_feqExecutionUnit);
        }
        
        //３）　@ArrayList.toArray()の戻り値を返却する。
        WEB3AdminORFeqExecutionUnit[] l_feqExecutionUnit = 
            new WEB3AdminORFeqExecutionUnit[l_arrayList.size()];
        l_arrayList.toArray(l_feqExecutionUnit);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqExecutionUnit;
    }
}
@
