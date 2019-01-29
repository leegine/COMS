head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOpenAtOrderDLServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式寄付注文DLサービスImpl(WEB3AdminFeqOpenAtOrderDLServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
Revesion History : 2005/08/03 黄建(中訊) レビュー  
Revesion History : 2007/01/16 徐大方(中訊) モデル　@No.333対応
Revesion History : 2007/02/25 齊珂(中訊) モデル　@No.344対応
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadRequest;
import webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOpenAtOrderDLService;
import webbroker3.feq.WEB3FeqOpenAtOrderDLCSV;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式寄付注文DLサービスImpl)<BR>
 * 外国株式寄付注文DLサービス実装クラス<BR>
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqOpenAtOrderDLServiceImpl 
    implements WEB3AdminFeqOpenAtOrderDLService 
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOpenAtOrderDLServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F000AB
     */
    public WEB3AdminFeqOpenAtOrderDLServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式寄付注文DLサービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    get入力画面()<BR>
     *    getダウンロードファ@イル()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421494340178
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターが未指定(null)です。");
        }

        WEB3GenResponse l_response;

        //リクエストデータの型により、以下のメソッドをコールする。
        //－get入力画面()
        if (l_request instanceof WEB3AdminFeqOpenAtOrderDownloadInputRequest)
        {
            l_response = getInputScreen((WEB3AdminFeqOpenAtOrderDownloadInputRequest) l_request);
        } 
        //－getダウンロードファ@イル()
        else if (l_request instanceof WEB3AdminFeqOpenAtOrderDownloadRequest)
        {
            l_response = getDownloadFile((WEB3AdminFeqOpenAtOrderDownloadRequest) l_request);
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
     * 入力画面取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）寄付注文DL）get入力画面」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214947202E0
     */
    protected WEB3AdminFeqOpenAtOrderDownloadInputResponse getInputScreen(
        WEB3AdminFeqOpenAtOrderDownloadInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminFeqOpenAtOrderDownloadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ログイン情報から管理者インスタンスを取得する
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 証券会社コードを取得する
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.3 権限のチェックを行う。 
        //[引数] 
        //機@能カテゴリーコード： 機@能カテゴリーコード.”外株（注文約定管理）” 
        //is更新： false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            false);

        //get取扱可能市場(証券会社コード : String, 銘柄タイプ : ProductTypeEnum)
        //取扱可能な市場コードの一覧を取得する。
        //[引数]
        //証券会社：　@get証券会社コード()の戻り値
        //銘柄タイプ：　@ProductTypeEnum.外国株式
        String[] l_strMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY);

        int l_intCnt = 0;

        if (l_strMarkets != null && l_strMarkets.length > 0)
        {
            l_intCnt = l_strMarkets.length;
        }
        else if (l_strMarkets.length == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        log.debug("HandlingPossibleMarket length ==== " + l_strMarkets.length);

        //空のリストを生成する
        //（非市場直結用）
        List l_lisMarketCode1 = new ArrayList();

        //空のリストを生成する
        //（市場直結用）
        List l_lisMarketCode2 = new ArrayList();

        //get取扱可能市場()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_intCnt; i++)
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

        //レスポンスデータを生成する。
        WEB3AdminFeqOpenAtOrderDownloadInputResponse l_response
            = (WEB3AdminFeqOpenAtOrderDownloadInputResponse) l_request.createResponse();

        //プロパティセット
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

        l_response.marketCodeList = l_strMarketCodes;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * ダウンロードファ@イル取得処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）寄付注文DL）getダウンロードファ@イル」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOpenAtOrderDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214949C0253
     */
    protected WEB3AdminFeqOpenAtOrderDownloadResponse getDownloadFile(
        WEB3AdminFeqOpenAtOrderDownloadRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getDownloadFile(WEB3AdminFeqOpenAtOrderDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 リクエストデータの整合性をチェックする
        l_request.validate();
        
        //1.2 ログイン情報から管理者インスタンスを取得する
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 権限のチェックを行う。 
        //[引数] 
        //機@能カテゴリーコード： 機@能カテゴリーコード.”外株（注文約定管理）” 
        //is更新： false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, 
            false);
        
        //1.4 証券会社コードを取得する
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //1.5 部店コードを取得する
        String l_strBranchCode = l_web3Administrator.getBranchCode();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager
            = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //1.6 部店インスタンスを取得する。 
        //[引数] 
        //証券会社コード： get証券会社コード()の戻り値 
        //部店コード： get部店コード()の戻り値 
        Branch l_branch = null;
        try 
        {
            l_branch = l_accountManager.getWeb3GenBranch(
                l_strInstitutionCode, 
                l_strBranchCode);
        } 
        catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "部店インスタンスを取得しない。", 
                l_ex);
        }
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                this.getClass().getName() + STR_METHOD_NAME, 
                "部店インスタンスを取得しない。");
        }
        
        //1.7 市場インスタンスを取得する。 
        //[引数] 
        //証券会社コード： get証券会社コード()の戻り値 
        //市場コード： リクエスト.市場コード 
        WEB3GentradeFinObjectManager l_finObjectManager 
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        Market l_market = null;
        try 
        {
            l_market = l_finObjectManager.getMarket(l_strInstitutionCode, l_request.marketCode);
        } 
        catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "市場インスタンスを取得しない。", 
                l_ex);
        }
        if (l_market == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "市場インスタンスを取得しない。");
        }
        
        //1.8 クエリプロセッサを取得する
        List l_lisResult = null;
        try 
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //1.9 注文単位から該当する注文を取得する。 
            //[取得条件] 
            //証券会社コード = get証券会社コード()の戻り値 
            //部店ID = 部店.getBranchId()の戻り値 
            //市場ID = 市場.getMarketId()の戻り値 
            //注文有効状態 = ”オープン”
            //注文状態 = ”発注中(新規注文)"又は”発注済(訂正注文)”
            //市場から確認済の数量 = null
            //発注日 = 当日 
            //
            //[引数] 
            //Rowタイプ： 外株注文単位Row.TYPE 
            //Where： "institution_code = ? and branch_id = ? and market_id = ? and order_open_status = ? and 
            //         order_status in (?,?) and confirmed_quantity is null and biz_date = ?" 
            //orderBy： "order_emp_code" 
            //Condition： null 
            //リスト： （以下の要素の配列） 
            //   get証券会社コード()の戻り値 
            //   部店.getBranchId()の戻り値 
            //   市場.getMarketId()の戻り値 
            //   OrderOpenStatusEnum.”オープン” 
            //   OrderStatusEnum.”発注中(新規注文)"又は”発注済(訂正注文)”
            //   null
            //   TradingSystem.getBizdate()の戻り値 
            String l_strWhere = " institution_code = ? and branch_id = ? " +
                "and market_id = ? and order_open_status = ? and order_status in (?, ?) and confirmed_quantity is null and biz_date = ? ";
                
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate,"yyyyMMdd");

            Object[] l_objs = new Object[] {
                l_strInstitutionCode, 
                new Long(l_branch.getBranchId()), 
                new Long(l_market.getMarketId()), 
                OrderOpenStatusEnum.OPEN,
                OrderStatusEnum.ORDERING,
                OrderStatusEnum.MODIFIED,
                l_strBizDate};

            l_lisResult = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_strWhere,
                "order_emp_code",
                null,
                l_objs);
        } 
        catch (DataFindException l_ex) 
        {
            String l_strMessage = "注文単位から該当する注文を取得";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        catch (DataQueryException l_ex) 
        {
            String l_strMessage = "注文単位から該当する注文を取得";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        catch (DataNetworkException l_ex) 
        {
            String l_strMessage = "注文単位から該当する注文を取得";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
        }
        if (l_lisResult == null || l_lisResult.isEmpty())
        {
            String l_strMessage = "注文単位から該当する注文を取得";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
            "該当するデータが存在しません。");
        }

        //1.10 寄付注文ダウンロードCSVインスタンスを生成する
        WEB3FeqOpenAtOrderDLCSV l_web3FeqOpenAtOrderDLCSV
            = new WEB3FeqOpenAtOrderDLCSV();

        int l_intCnt = l_lisResult.size();

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //1.11 取得した注文単位毎にLoop処理
        for (int i = 0; i < l_intCnt; i++)
        {
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow)l_lisResult.get(i);
            WEB3FeqOrderUnit l_feqOrderUnit = 
                (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_feqOrderUnitRow);
            //.(WEB3FeqOrderUnit) l_lisResult.get(i);
            
            //1.11.1 空の明細行を生成する add明細行()
            int l_intLineIndex = l_web3FeqOpenAtOrderDLCSV.addRow();
            
            //1.11.2 運用コードをセットする。 
            //[引数] 
            //行番号： add明細行()の戻り値 
            //運用コード： 注文単位.運用コード
            l_web3FeqOpenAtOrderDLCSV.setOrderEmpCode(
                l_intLineIndex, 
                l_feqOrderUnit.getOrderEmpCode());
            
            //1.11.3 現地銘柄コードをセットする。 
            //[引数] 
            //行番号： add明細行()の戻り値 
            //銘柄ID： 注文単位.銘柄ID 
            l_web3FeqOpenAtOrderDLCSV.setOffshoreProductCode(
                l_intLineIndex, 
                l_feqOrderUnit.getProduct().getProductId());
            
            //1.11.4 銘柄名をセットする。 
            //[引数] 
            //行番号： add明細行()の戻り値 
            l_web3FeqOpenAtOrderDLCSV.setProductName(l_intLineIndex);
            
            //1.11.5 売買区分をセットする。 
            //
            //[引数] 
            //行番号： add明細行()の戻り値 
            //is買付： 注文単位.is買付()の戻り値 
            l_web3FeqOpenAtOrderDLCSV.setOrderType(
                l_intLineIndex, 
                l_feqOrderUnit.isBuy());
            
            //1.11.6 注文株数をセットする。 
            //
            //[引数] 
            //行番号： add明細行()の戻り値 
            //注文株数： 注文単位.注文数量 
            l_web3FeqOpenAtOrderDLCSV.setOrderQuantity(
                l_intLineIndex, 
                (long)l_feqOrderUnit.getQuantity());
            
            //1.11.7 約定方法@をセットする。 

            //[引数] 
            //行番号： add明細行()の戻り値 
            l_web3FeqOpenAtOrderDLCSV.setExecMethod(l_intLineIndex);

            //1.11.8 注文単価をセットする。 
            //
            //[引数] 
            //行番号： add明細行()の戻り値 
            //指値： 注文単位.指値 
            l_web3FeqOpenAtOrderDLCSV.setOrderPrice(
                l_intLineIndex, 
                l_feqOrderUnit.getLimitPrice());

            //1.11.9 有効期限をセットする。 
            //
            //[引数] 
            //行番号： add明細行()の戻り値 
            l_web3FeqOpenAtOrderDLCSV.setExpirationDate(l_intLineIndex);
        }

        //1.12 CSVファ@イル行を取得する
        String[] l_strCsvFileLines = l_web3FeqOpenAtOrderDLCSV.getCsvFileLines();

        //1.13 レスポンスデータを生成する
        WEB3AdminFeqOpenAtOrderDownloadResponse l_response = 
            (WEB3AdminFeqOpenAtOrderDownloadResponse) l_request.createResponse();

        //1.14 プロパティセット
        l_response.downloadFileList = l_strCsvFileLines;
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
