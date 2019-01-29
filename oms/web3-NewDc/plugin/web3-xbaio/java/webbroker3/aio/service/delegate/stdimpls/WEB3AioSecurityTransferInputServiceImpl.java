head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替入力サービスImpl(WEB3AioSecurityTransferInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 屈陽 (中訊) 新規作成 
                   2005/02/16 黄建 (中訊) 残対応
                   2006/10/26 何文敏 (中訊) 仕様変更・モデル664
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioBizLogicProvider;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.define.WEB3AioTransferSortkeyDef;
import webbroker3.aio.message.WEB3AioChangePossQuantityComparator;
import webbroker3.aio.message.WEB3AioDepositDivComparator;
import webbroker3.aio.message.WEB3AioInstrumentsTypeComparator;
import webbroker3.aio.message.WEB3AioMarketValueComparator;
import webbroker3.aio.message.WEB3AioProductCodeComparator;
import webbroker3.aio.message.WEB3AioProductNameComparator;
import webbroker3.aio.message.WEB3AioSecurityTransferInputRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferInputResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferListRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferListResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferProductCodeNameUnit;
import webbroker3.aio.message.WEB3AioSecurityTransferSortKeyUnit;
import webbroker3.aio.message.WEB3AioSecurityTransferUnit;
import webbroker3.aio.message.WEB3AioTaxTypeComparator;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DailyAssetParams;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (証券振替入力サービスImpl)<BR>
 * 証券振替入力サービス実装クラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferInputServiceImpl extends WEB3ClientRequestService implements WEB3AioSecurityTransferInputService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferInputServiceImpl.class);  
    
    /**
     * 証券振替入力サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型によりcreate一覧画面()、またはcreate入力画面()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41576F0E034B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //リクエストデータの型によりcreate一覧画面()、またはcreate入力画面()メソッドをコールする。
        if (l_request instanceof WEB3AioSecurityTransferListRequest)
        {
            l_response = 
                createListScreen((WEB3AioSecurityTransferListRequest)l_request);   
        }
        else if (l_request instanceof WEB3AioSecurityTransferInputRequest)
        {
            l_response =
                createInputScreen((WEB3AioSecurityTransferInputRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create一覧画面)<BR>
     * 一覧画面の表示用データを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(証券振替入力)create一覧画面」 参照
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(証券振替サービスモデル) / 証券振替入力 」<BR>
     *          : 証券振替入力 / create一覧画面
     *  1.5 is信用口座開設(String)
     *  アイテムの定義
     *  信用口座を開設しているかのチェックを行う。 
     *  [引数] 
     *  弁済区分： 0（指定なし)
     *  戻り値=false の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00747<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41576F640231
     */
    protected WEB3AioSecurityTransferListResponse createListScreen(WEB3AioSecurityTransferListRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createListScreen(WEB3AioSecurityTransferListRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.5 信用口座を開設しているかのチェックを行う。 
        //[引数] 
        //弁済区分： 0（指定なし） 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is信用口座開設(String)の戻り値=false の場合、例外をスローする。
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is信用口座開設(String)の戻り値=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is信用口座開設(String)の戻り値=false");            
        }
        
        //1.6 取得条件の文字列を生成する。 
        //[引数] 
        //銘柄タイプ： リクエストデータ.商品タイプ 
        String l_strInstrumentType = l_request.instrumentsType;
        //銘柄コード： リクエストデータ.銘柄コード 
        String l_strProductCode = l_request.productCode;
        //預り区分： リクエストデータ.預り区分 
        String l_strDepositDiv = l_request.depositDiv;
        
        String l_strCond = this.createGetCondCharacterString(
            l_strInstrumentType,
            l_strProductCode,
            l_strDepositDiv);
            
        //1.7 取得条件にセットするデータの配列を生成する。 
        //[引数] 
        //口座： 顧客オブジェクト 
        //銘柄タイプ： リクエストデータ.商品タイプ 
        //銘柄コード： リクエストデータ.銘柄コード 
        //預り区分： リクエストデータ.預り区分 
        Object[] l_objArrayCondData = this.createGetCondDataContainer(
            l_mainAccount,
            l_strInstrumentType,
            l_strProductCode,
            l_strDepositDiv);        

        List l_lisDailyAssetRows; 
        try
        {
            //1.8 クエリプロセッサを取得する。
            //throw datafindException and networkException         
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            //1.9 受渡日別保有資産テーブルから、条件に合致するレコードを取得する。 
            //[引数] 
            //Rowタイプ： 受渡日別保有資産Row.TYPE 
            //Where： create取得条件文字列()の戻り値 
            //リスト： create取得条件データコンテナ()の戻り値 
            
            //throw dataqueryException and networkException    
            l_lisDailyAssetRows =
                l_processor.doFindAllQuery(
                    DailyAssetRow.TYPE,
                    l_strCond,
                    l_objArrayCondData);

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }  
        
        
        //1.10 空のArrayListを生成する
        List l_lisSecurityTransferUnit = new ArrayList();
        
        //1.11 取得したレコードごとにLOOP    
        //get the iterator of the RowList
        int l_intSize = 0;
        if (l_lisDailyAssetRows != null )
        {
            l_intSize = l_lisDailyAssetRows.size();
        }
        
        for (int i = 0; i < l_intSize; i++)
        {
            DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAssetRows.get(i);            
            
            // test log
            log.debug("取得したレコード......NO. " + i);
            log.debug("l_dailyAssetRow = " + l_dailyAssetRow);
                        
            //(*1)select the record of Product under the condition (DailyAssetRow.productId)
            ProductRow l_productRow;
            try
            {
                l_productRow = ProductDao.findRowByPk(l_dailyAssetRow.getProductId());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DataQueryException", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, 
                    l_ex.getMessage(), 
                    l_ex);            
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DataNetworkException", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, 
                    l_ex.getMessage(), 
                    l_ex);
            }  
            
            //1.11.1 (*2) (*1)で取得した銘柄.代用掛目 != 0 の場合
            if (l_productRow.getMarginRatio() != 0)
            {
                //1.11.1.1 預り証券明細オブジェクトを生成する。 
                //[引数] 
                //保有資産： 受渡日別保有資産Params
                DailyAssetParams l_dailyAssetParams = 
                    new DailyAssetParams(l_dailyAssetRow);
             
                WEB3AioSecurityTransferUnit l_newAioSecurityTransferUnit =
                    createInstitutionBondDetails(l_dailyAssetParams);
                
                //1.11.1.2 ArrayListに預り証券明細オブジェクトを追加する。 
                //[引数] 
                //arg0： 預り証券明細オブジェクト 
                int l_intTempSize = l_lisSecurityTransferUnit.size();
                    
                //預り証券明細.数量 == 0 の場合も、スキップする。      
                if (l_newAioSecurityTransferUnit.changePossQuantity.equals("0"))
                {
                    continue;
                }
               
                //商品タイプ、銘柄コード、口座区分、預り区分が一致する
                //明細が既に存在する場合は、スキップする。
                //get the iterator of the new List --空のArrayList  
                if (l_intTempSize == 0)
                {
                    //追加
                    l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit); 
                    continue;
                }
                
                boolean l_blnMark = false;
                for (int j = 0; j < l_intTempSize; j++)
                {
                    WEB3AioSecurityTransferUnit l_unit = 
                        (WEB3AioSecurityTransferUnit)l_lisSecurityTransferUnit.get(j);
                    
                    if (l_newAioSecurityTransferUnit.taxType == null || l_unit.taxType == null)
                    {
                        if (l_newAioSecurityTransferUnit.taxType == null && l_unit.taxType == null)
                        {
                            if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                                l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                                l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                            {
                                l_blnMark = true;
                                break;        
                            }
                        }
                    }
                    else
                    {
                        //compare 
                        if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                            l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                            l_newAioSecurityTransferUnit.taxType.equals(l_unit.taxType) &&
                            l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                        {
                            l_blnMark = true;
                            break;        
                        }                         
                    } 
                }         
                if (l_blnMark == false)
                {
                    l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit);                                                          
                }                               
            }
        }

        //1.12 doFindAllQuery(Rowタイプ : RowType, Where : String, リスト : Object[])
        //注文単位テーブルから、条件に合致するレコードを取得する。 
        //[引数] 
        //Rowタイプ： 注文単位Row.TYPE 
        //Where： create取得条件文字列()の戻り値 
        //リスト： create取得条件データコンテナ()の戻り値 
        List l_lisAioOrderUnitRows; 
        try
        {            
            QueryProcessor l_processor = Processors.getDefaultProcessor();            
            //throw dataqueryException and networkException    
            l_lisAioOrderUnitRows =
                l_processor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strCond,
                    l_objArrayCondData);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }  
        
        int l_intOrderUnitSize = 0;
        if (l_lisAioOrderUnitRows != null )
        {
            l_intOrderUnitSize = l_lisAioOrderUnitRows.size();
        }
        
        //1.13 取得したレコードごとにLOOP
        for (int i = 0; i < l_intOrderUnitSize; i++)
        {
            boolean l_blnSameProduct = false;
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);            
            for (int j = 0; j < l_intSize; j++)
            {
				DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAssetRows.get(j);
				if (l_aioOrderUnitRow.getProductId() == l_dailyAssetRow.getProductId() &&
					l_aioOrderUnitRow.getSubAccountId() == l_dailyAssetRow.getSubAccountId() &&
					l_aioOrderUnitRow.getTaxType() == l_dailyAssetRow.getTaxType())
                {
                    l_blnSameProduct = true;
                    break;
                }               
            }
            //1.13.1 取得した受渡日別保有資産のレコードに同一の銘柄IDと補助口座IDと税区分が存在しない場合
            if (!l_blnSameProduct)
            {                        
                ProductRow l_productRow;
                try
                {
                    l_productRow = ProductDao.findRowByPk(l_aioOrderUnitRow.getProductId());
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DataQueryException", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName, 
                        l_ex.getMessage(), 
                        l_ex);            
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DataNetworkException", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName, 
                        l_ex.getMessage(), 
                        l_ex);
                }  
                
                //1.13.1.1 (*4) (*3)で取得した銘柄.代用掛目 != 0 の場合
                if (l_productRow.getMarginRatio() != 0)
                {
                    //1.13.1.1.1 create預り証券明細オブジェクトを生成する。 
                    //[引数] 
                    //保有資産： 注文単位Params 
                    AioOrderUnitParams l_aioOrderUnitParams = 
                        new AioOrderUnitParams(l_aioOrderUnitRow);
                 
                    WEB3AioSecurityTransferUnit l_newAioSecurityTransferUnit =
                        createInstitutionBondDetails(l_aioOrderUnitParams);
                    
                    //1.13.1.1.2 ArrayListに預り証券明細オブジェクトを追加する。 
                    //[引数] 
                    //arg0： 預り証券明細オブジェクト                     
                    int l_intTempSize = l_lisSecurityTransferUnit.size();
                        
                    //預り証券明細.数量 == 0 の場合も、スキップする。      
                    if (l_newAioSecurityTransferUnit.changePossQuantity.equals("0"))
                    {
                        continue;
                    }
                   
                    //商品タイプ、銘柄コード、口座区分、預り区分が一致する
                    //明細が既に存在する場合は、スキップする。
                    //get the iterator of the new List --空のArrayList  
                    if (l_intTempSize == 0)
                    {
                        //追加
                        l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit); 
                        continue;
                    }
                    
                    boolean l_blnMark = false;
                    for (int j = 0; j < l_intTempSize; j++)
                    {
                        WEB3AioSecurityTransferUnit l_unit = 
                            (WEB3AioSecurityTransferUnit)l_lisSecurityTransferUnit.get(j);
                        
                        if (l_newAioSecurityTransferUnit.taxType == null || l_unit.taxType == null)
                        {
                            if (l_newAioSecurityTransferUnit.taxType == null && l_unit.taxType == null)
                            {
                                if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                                    l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                                    l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                                {
                                    l_blnMark = true;
                                    break;        
                                }
                            }
                        }
                        else
                        {
                            //compare 
                            if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                                l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                                l_newAioSecurityTransferUnit.taxType.equals(l_unit.taxType) &&
                                l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                            {
                                l_blnMark = true;
                                break;        
                            }                         
                        } 
                    }         
                    if (l_blnMark == false)
                    {
                        l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit);                                                          
                    }           
                }
            }
        }

        //1.14 配列を取得する。 
        WEB3AioSecurityTransferUnit[] l_arrayAioSecurityTransferUnit =
            new WEB3AioSecurityTransferUnit[l_lisSecurityTransferUnit.size()];
        //toArray
        l_lisSecurityTransferUnit.toArray(l_arrayAioSecurityTransferUnit);

        //1.15 預り証券明細の配列をソートする。 
        //[引数] 
        //明細： 預り証券明細の配列 
        //ソートキー： リクエストデータ.ソートキー 
        WEB3AioSecurityTransferUnit[] l_web3AioSecurityTransferUnit = 
            this.sortInstitutionBondDetails(
                l_arrayAioSecurityTransferUnit,
                l_request.sortKeys);

        //1.16  get表示明細、表示対象行（fromIndex～toIndex）のオブジェクト配列を指定する。
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
 
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(l_web3AioSecurityTransferUnit, l_intPageIndex, l_intPageSize);
        l_web3AioSecurityTransferUnit = 
            (WEB3AioSecurityTransferUnit[])l_pageIndexInfo.getArrayReturned(WEB3AioSecurityTransferUnit.class);
   
            
        //1.17 保有銘柄明細の配列を取得する。 
        WEB3AioSecurityTransferProductCodeNameUnit[] l_arraySecTraProductCodeNameUnit =
            this.getProductCodeNameDetails();
            
        //log for test
        log.debug("保有銘柄明細の配列 : " + l_arraySecTraProductCodeNameUnit);    
            
        //1.18 レスポンスデータを生成する。 
        WEB3AioSecurityTransferListResponse l_response = 
            (WEB3AioSecurityTransferListResponse)l_request.createResponse();
            
        //1.19 プロパティセット
        //レスポンス.預り証券一覧 = 証券振替入力サービスImpl.get表示明細()の戻り値
        l_response.securityTransfer = l_web3AioSecurityTransferUnit;
        
        //レスポンス.表示ページ番号 = toIndex / リクエストデータ.ページ内容表示行数  ※小数点以下切り上げ
        l_response.pageIndex = Integer.toString(l_pageIndexInfo.getPageIndex());
        
        //レスポンス.総ページ数 = （預り証券明細［］.length()の戻り値） / リクエストデータ.ページ内容表示行数  ※小数点以下切り上げ
        l_response.totalPages = Integer.toString(l_pageIndexInfo.getTotalPages());
         
        //レスポンス.総レコード数 = 預り証券明細［］.length()の戻り値
        l_response.totalRecords = Integer.toString(l_pageIndexInfo.getTotalRecords());
        
        //レスポンス.銘柄一覧 = 証券振替入力サービスImpl.get保有銘柄明細()の戻り値
        l_response.productCodeNames = l_arraySecTraProductCodeNameUnit;
               
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create入力画面)<BR>
     * 入力画面の表示用データを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(証券振替入力)create入力画面」 参照
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「(証券振替サービスモデル) / 証券振替入力 」<BR>
     *          : 証券振替入力 / create入力画面
     *  1.5 is信用口座開設(String)
     *  アイテムの定義
     *  信用口座を開設しているかのチェックを行う。 
     *  [引数] 
     *  弁済区分： 0（指定なし)
     *  戻り値=false の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00747<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41576F6D02CE
     */
    protected WEB3AioSecurityTransferInputResponse createInputScreen(WEB3AioSecurityTransferInputRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "createInputScreen(WEB3AioSecurityTransferInputRequest l_request)";
        log.entering(l_strMethodName);

        //1.1 リクエストデータの整合性をチェックする。 
        l_request.validate();
        
        //1.2 補助口座オブジェクトを取得する。 
        //[引数] 
        //補助口座タイプ： 1（預り金口座） 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate注文(SubAccount)
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.5 信用口座を開設しているかのチェックを行う。 
        //[引数] 
        //弁済区分： 0（指定なし） 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is信用口座開設(String)の戻り値=false の場合、例外をスローする。
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is信用口座開設(String)の戻り値=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is信用口座開設(String)の戻り値=false");            
        }
        
        //1.6 発注日を取得する。 
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 証券振替注文の1日の上限回数を超えてないかをチェックする。 
        //[引数] 
        //補助口座： get補助口座()の戻り値 
        //発注日： get発注日()の戻り値 
        l_orderManager.validateInstitutionTransferPossibleCount(
            l_subAccount,
            l_datOrderBizDate);
            
        //1.8 銘柄オブジェクトを取得する。 
        //[引数] 
        //a> 銘柄タイプ： リクエストデータ.商品タイプ 
        String l_strInstrumentType = l_request.instrumentsType;
        
        ProductTypeEnum l_enumInstrumentType;      
        if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.EQUITY;
        }
        else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.BOND;
        }
        else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.MUTUAL_FUND;
        }
        else 
        {
            l_enumInstrumentType = ProductTypeEnum.FOREIGN_EQUITY;
        }
        
        //b> 銘柄コード： リクエストデータ.銘柄コード 
        String l_strProductCode = l_request.productCode;
        //c> 証券会社： 補助口座.getInsutitution()の戻り値 
        Institution l_institution = l_subAccount.getInstitution(); 
        
        //d> AIOプロダクトマネージャを取得 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
        //e> getProduct
        Product l_product = l_productManager.getProduct(
            l_enumInstrumentType, 
            l_strProductCode, 
            l_institution);
        
        //1.9 振替可能数量を算出する。 
        //[引数] 
        //a> 口座ID： （get補助口座()の戻り値）.getAccountId()の戻り値 
        long l_lngAccountId = l_subAccount.getAccountId();
        
        //b> 銘柄タイプ： リクエストデータ.商品タイプ ---l_enumInstrumentType in 1.8
        
        //c> 銘柄ID： （get銘柄()の戻り値）.銘柄ID 
        long l_lngProductId = l_product.getProductId();
        
        //d> 税区分： 
        TaxTypeEnum l_taxTypeEnum;
        //リクエストデータ.口座区分=nullの場合： 0（その他）
        if (l_request.taxType == null)
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;            
        }
        //リクエストデータ.口座区分=”一般”の場合： 1（一般） 
        else if(WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;    
        }
        //リクエストデータ.口座区分=”特定”の場合： 2（特定）
        else
        {            
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        //e> 預り区分： リクエストデータ.振替元預り区分 
        String l_strDepositDiv = l_request.depositDiv;
        
        //get WEB3AioBizLogicProvider
        WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
            (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        // test log
        log.debug("リクエストデータ.振替元預り区分 = " + l_strDepositDiv);
        
        double l_dblTransPossibleAmount = 
            l_web3AioBizLogicProvider.calcTransPossibleAmount(
                l_lngAccountId,
                l_enumInstrumentType,
                l_lngProductId,
                l_taxTypeEnum,
                l_strDepositDiv);

        // test log
        log.debug("振替可能数量を算出 = " + l_dblTransPossibleAmount);
        
        //1.10 証券評価額を算出する。 
        //[引数] 
        //銘柄ID：（get銘柄()の戻り値）.銘柄ID 
        //預り区分：リクエストデータ.振替元預り区分 
        //数量：calc振替可能数量()の戻り値 
        double l_dblStockEvalueAmount = 
            l_web3AioBizLogicProvider.calcStockEvalueAmount(
                l_lngProductId,
                l_strDepositDiv,
                l_dblTransPossibleAmount);
            
        // test log
        log.debug(" 証券評価額を算出 = " + l_dblStockEvalueAmount);

        //1.11 レスポンスデータを生成する。 
        WEB3AioSecurityTransferInputResponse l_response =
            (WEB3AioSecurityTransferInputResponse)l_request.createResponse();
            
        //1.12 プロパティをセットする。
        //レスポンス.銘柄名 = (get銘柄()の戻り値).銘柄名
        l_response.productName = l_product.getStandardName();
        //レスポンス.振替可能数量 = 入出金計算サービス.calc振替可能数量()の戻り値
        l_response.changePossQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblTransPossibleAmount);
        //レスポンス.評価額 = 入出金計算サービス.calc証券評
        l_response.marketValue = 
            WEB3StringTypeUtility.formatNumber(l_dblStockEvalueAmount);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get保有銘柄明細)<BR>
     * 保有銘柄明細の配列を生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（証券振替入力）get保有銘柄明細」 参照
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferProductCodeNameUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4163BB8403CB
     */
     protected WEB3AioSecurityTransferProductCodeNameUnit[] getProductCodeNameDetails() 
        throws WEB3BaseException
     {
         String l_strMethodName = "getProductCodeNameDetails()";
         log.entering(l_strMethodName);

         //1.1 預り金口座オブジェクトを取得する。 
         //[引数] 
         //補助口座タイプ： 1（預り金口座） 
         SubAccount l_subAccount = 
             this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

         //1.2 保証金口座オブジェクトを取得する。 
         //[引数] 
         //補助口座タイプ： 2（保証金口座） 
         SubAccount l_marginSubAccount = 
             this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);

         List l_lisDailyAssetRows;    
         try
         {
             //1.3 getDefaultProcessor()
             //throw datafindException and networkException
             QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

             //1.4 受渡日別保有資産テーブルから、以下の条件のレコードを取得する。  
             //[引数] 
             //Rowタイプ： 受渡日別保有資産Row.TYPE 
             //Where： "account_id=? and (sub_accout_id=? or sub_account_id=?) and 
             //        (product_type>=? and product_type<=?) and mini_stock_div=?" 
             //リスト： 以下の項目のリスト 
             //預り金口座.getAccountId()の戻り値
             long l_lngAccountId = l_subAccount.getAccountId(); 
             log.debug("AccountId= " + l_lngAccountId);

             //預り金口座.getSubAccountId()の戻り値 
             long l_lngSubAccountId = l_subAccount.getSubAccountId();
             log.debug("l_lngSubAccountId= " + l_lngSubAccountId);

             //保証金口座.getSubAccountId()の戻り値 
             long l_marginSubAccountId = l_marginSubAccount.getSubAccountId();
             log.debug("l_marginSubAccountId= " + l_marginSubAccountId);

             //銘柄タイプ.株式 
             ProductTypeEnum l_productTypeEquity = ProductTypeEnum.EQUITY;

             //銘柄タイプ.投信 
             ProductTypeEnum l_productTypeMutualFund = ProductTypeEnum.MUTUAL_FUND;

             //0（DEFAULT） 
             String l_miniStockType = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;

             String l_strWhere = 
                 " account_id = ? and (sub_account_id = ? or sub_account_id = ?) and " +
                 "(product_type >= ? and product_type <= ?) and mini_stock_div = ? ";
             Object[] l_arrayWhere = {
                 new Long(l_lngAccountId),
                 new Long(l_lngSubAccountId),
                 new Long(l_marginSubAccountId),
                 Integer.toString(l_productTypeEquity.intValue()),
                 Integer.toString(l_productTypeMutualFund.intValue()),
				 l_miniStockType};

             //throw dataqueryException and networkException    
             l_lisDailyAssetRows = 
                 l_queryProcessor.doFindAllQuery(
                         DailyAssetRow.TYPE,
                         l_strWhere,
                         l_arrayWhere);
         }
         catch (DataFindException l_ex)
         {
             log.error("__DataFindException in select__", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);
         }
         catch (DataQueryException l_ex)
         {
             log.error("__DataQueryException in select__", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);            
         }
         catch (DataNetworkException l_ex)
         {
             log.error("DataNetworkException in select", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);
         } 

         //1.5 空のArrayListを生成する。 
         List l_lisSecurityTranProCodeNameUnits = new ArrayList();

         //FinApp, TradingModule
         FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
         TradingModule l_tradingModule = 
             l_finApp.getTradingModule(ProductTypeEnum.AIO);

         //AIOプロダクトマネージャを取得 
         WEB3AioProductManager l_productManager = 
             (WEB3AioProductManager)l_tradingModule.getProductManager();

         int l_intSize = 0;
         if (l_lisDailyAssetRows != null)
         {
             l_intSize = l_lisDailyAssetRows.size();     
         }

         //1.6 取得した受渡日別保有資産レコード毎にLOOP 
         for (int i = 0; i < l_intSize; i++)      
         {
             //1.6.1 get銘柄(ProductTypeEnum, long)
             //[引数] 
             //a> 銘柄タイプ(int)： 受渡日別保有資産Params.銘柄タイプ
             ProductTypeEnum l_productTypeEnum = 
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductType();

             //b> 銘柄ID： 受渡日別保有資産Params.銘柄ID 
             long l_lngProductId = 
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductId();

             Product l_product = l_productManager.getProduct(
                     l_productTypeEnum,
                     l_lngProductId);

             //2005-2-16==========huang-jian=========障害票U00869==========Start

             //1.6.2取得した銘柄.代用掛目 != 0 の場合
             if (l_product.getMarginRatio() != 0)
             {
                 //1.6.2.1get補助口座(, )
                 //補助口座を取得する。 
                 //[引数] 
                 //口座ID： 受渡日別保有資産Params.口座ID 
                 //補助口座ID： 受渡日別保有資産Params.補助口座ID
                 long l_lngAccountId =
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getAccountId();    
                 long l_lngSubAccountId = 
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getSubAccountId();
                 WEB3GentradeAccountManager l_accountManager =
                     (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                 try
                 {
                     SubAccount l_subGentAccount =
                         l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);

                     //1.6.2.2 getSubAccountType( )補助口座タイプを取得する。
                     SubAccountTypeEnum l_subAccountType = l_subGentAccount.getSubAccountType(); 

                     //1.6.2.3 calc振替可能数量(long, ProductTypeEnum, long, TaxTypeEnum, String)
                     //振替可能数量を算出する。 
                     //[引数] 
                     //口座ID： 受渡日別保有資産Params.口座ID 
                     //銘柄タイプ： 受渡日別保有資産Params.銘柄タイプ 
                     //銘柄ID： 受渡日別保有資産Params.銘柄ID 
                     //税区分： 受渡日別保有資産Params.税区分 
                     //預り区分： 
                     //補助口座タイプ = 1（預り金） の場合、1（保護） 
                     //補助口座タイプ = 2（保証金） の場合、2（代用） 
                     WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                         (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
                     String l_strDepositDiv = null;
                     if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountType))
                     {
                         l_strDepositDiv = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;
                     }
                     else
                     {
                         if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountType))
                         {
                             l_strDepositDiv = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;
                         }
                     }   
                     double l_dblTransPossibleAmount = 
                         l_web3AioBizLogicProvider.calcTransPossibleAmount(
                             l_lngAccountId,
                             ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductType(),
                             l_lngProductId,
                             ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getTaxType(),
                             l_strDepositDiv);

                     //1.6.2.4 振替可能数量 > 0 の場合
                     if (l_dblTransPossibleAmount > 0)
                     {
                         //1.6.2.4.1 get銘柄(ProductTypeEnum, long)
                         //銘柄オブジェクトを取得する。 
                         //[引数] 
                         //銘柄タイプ： 受渡日別保有資産Params.銘柄タイプ 
                         //銘柄ID： 受渡日別保有資産Params.銘柄ID 
                         Product l_products = l_productManager.getProduct(
                             ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductType(),
                             l_lngProductId);

                         //1.6.2.4.2 証券振替保有銘柄明細( )    
                         //保有銘柄明細のインスタンスを生成する。 
                         WEB3AioSecurityTransferProductCodeNameUnit l_securityTranProCodeNameUnit = 
                             new WEB3AioSecurityTransferProductCodeNameUnit();

                         //1.6.2.4.3 プロパティセット    
                         //以下のとおりに、プロパティをセットする。

                         //a> 保有銘柄明細.商品タイプ = 銘柄オブジェクト.銘柄タイプ
                         l_securityTranProCodeNameUnit.instrumentsType = 
                             String.valueOf(l_products.getProductType().intValue());

                         //b> 保有銘柄明細.銘柄コード = （以下のとおり）
                         //商品タイプ == ”債券” の場合、銘柄オブジェクト.回号コード(SONAR) + 銘柄オブジェクト.銘柄コード(SONAR)の上4桁
                         //商品タイプ != ”債券” の場合、銘柄オブジェクト.銘柄コード
                         if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                         {
                             l_securityTranProCodeNameUnit.productCode =
                                 ((EqtypeProductRow)l_products.getDataSourceObject()).getProductCode();    
                         }
                         else if(ProductTypeEnum.BOND.equals(l_productTypeEnum))
                         {
                             // 銘柄オブジェクト.銘柄コード(SONAR)の上4桁
                             String l_strHostProductCode = ((BondProductRow)
                                 l_products.getDataSourceObject()).getHostProductCode().substring(0, 4);
                             //銘柄オブジェクト.回号コード(SONAR)
                             String l_strHostIssueCode = ((BondProductRow)
                                 l_products.getDataSourceObject()).getHostProductIssueCode();
                             
                             l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                             
                             l_securityTranProCodeNameUnit.productCode = l_strHostProductCode;
                         }
                         else if(ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
                         {
                             l_securityTranProCodeNameUnit.productCode =
                                 ((MutualFundProductRow)l_products.getDataSourceObject()).getProductCode();
                         }
                         else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
                         {
                             l_securityTranProCodeNameUnit.productCode =
                                 ((FeqProductRow)l_products.getDataSourceObject()).getProductCode();
                         }

                         //c> 保有銘柄明細.銘柄名 = 銘柄オブジェクト.銘柄名
                         l_securityTranProCodeNameUnit.productName = 
                             l_products.getStandardName();

                         //1.6.2.4.4 add(arg0 : Object)
                         //ArrayListに保有銘柄明細オブジェクトを追加する。 
                         //[引数] 
                         //arg0： 保有銘柄明細オブジェクト
                         int l_intTranProCodeSize = 0;
                         if (l_lisSecurityTranProCodeNameUnits != null)
                         {
                             l_intTranProCodeSize = l_lisSecurityTranProCodeNameUnits.size();     
                         }
                         //すでにArrayListに存在する銘柄については、スキップする。
                         boolean l_booMark = true;     

                         for (int j = 0; j < l_intTranProCodeSize; j++)
                         {
                             if (((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).productCode
                             .equals(l_securityTranProCodeNameUnit.productCode) && 
                             ((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).instrumentsType
                             .equals(l_securityTranProCodeNameUnit.instrumentsType))
                             {
                                 l_booMark = false;
                                 break;    
                             }                        
                         }                  
                         if (l_booMark)
                         {
                             l_lisSecurityTranProCodeNameUnits.add(l_securityTranProCodeNameUnit);        
                         }                                      
                     }     
                 }
                 catch (NotFoundException l_ex)
                 {
                     log.debug("__NotFoundException__", l_ex);
                     //例外をスローする
                     log.exiting(l_strMethodName);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         getClass().getName() + "." + l_strMethodName,
                         l_ex.getMessage(),
                         l_ex);
                 }   
             }
        }

         //1.7 doFindAllQuery(Rowタイプ : RowType, Where : String, リスト : Object[])
         //注文単位テーブルから、以下の条件のレコードを取得する。 
         //[条件] 
         //口座ID = 預り金口座.getAccountId()の戻り値 and 
         //（補助口座ID = 預り金口座.getSubAccountId()の戻り値 or 保証金口座.getSubAccountId()の戻り値） and 
         //（銘柄タイプ >= 1：株式 and 銘柄タイプ <= 3：投信） 
         //[引数] 
         //Rowタイプ： 注文単位Row.TYPE 
         //Where： "account_id=? and (sub_account_id=? or sub_account_id=?) and (product_type>=? and product_type<=?) and mini_stock_div=?" 
         //リスト： 以下の項目のリスト 
         //預り金口座.getAccountId()の戻り値 
         //預り金口座.getSubAccountId()の戻り値 
         //保証金口座.getSubAccountId()の戻り値 
         //銘柄タイプ.株式 
         //銘柄タイプ.投信 
         //0（DEFAULT） 
         List l_lisAioOrderUnitRows; 
         try
         {
             String l_strWhere = 
                 " account_id = ? and (sub_account_id = ? or sub_account_id = ? ) and " +
                 "(product_type >= ? and product_type <= ?) and mini_stock_div = ? ";
             
             long l_lngAccountId = l_subAccount.getAccountId();
             long l_lngSubAccountId = l_subAccount.getSubAccountId();
             long l_lngMarginSubAccountId = l_marginSubAccount.getSubAccountId();
                          
             Object[] l_arrayWhere = {
                 new Long(l_lngAccountId),
                 new Long(l_lngSubAccountId),
                 new Long(l_lngMarginSubAccountId),
                 ProductTypeEnum.EQUITY,
                 ProductTypeEnum.MUTUAL_FUND,
                 BooleanEnum.FALSE };
    
             QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
             //throw dataqueryException and networkException   

             l_lisAioOrderUnitRows = 
                 l_queryProcessor.doFindAllQuery(
                     AioOrderUnitRow.TYPE,
                     l_strWhere,
                     l_arrayWhere);
         }         
         catch (DataQueryException l_ex)
         {
             log.error("__DataQueryException in select__", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);            
         }
         catch (DataNetworkException l_ex)
         {
             log.error("DataNetworkException in select", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);
         } 
         int l_intOrderUnitSize = 0;
         if (l_lisAioOrderUnitRows != null )
         {
             l_intOrderUnitSize = l_lisAioOrderUnitRows.size();
         }
         
         long[] l_lngProductIds = new long[l_intSize];
         
         for (int k = 0; k < l_intSize; k++)
         {
             DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAssetRows.get(k);
             l_lngProductIds[k] = l_dailyAssetRow.getProductId();
         }
         
         //1.8 取得したレコードごとにLOOP
         for (int i = 0; i < l_intOrderUnitSize; i++)
         {
             boolean l_blnSameProductId = false;
             AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);            
             for (int j = 0; j < l_intSize; j++)
             {
                 if (l_aioOrderUnitRow.getProductId() == l_lngProductIds[j])
                 {
                     l_blnSameProductId = true;
                     break;
                 }               
             }
             //1.8.1 取得した受渡日別保有資産のレコードに同一の銘柄IDが存在しない場合
             if (!l_blnSameProductId)
             {             
                 //1.8.1.1 銘柄オブジェクトを取得する。 
                 //[引数] 
                 //arg0： 注文単位Params.銘柄ID 
                 ProductRow l_productRow;
                 try
                 {
                     l_productRow = ProductDao.findRowByPk(l_aioOrderUnitRow.getProductId());
                 }
                 catch (DataQueryException l_ex)
                 {
                     log.error("DataQueryException", l_ex);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + l_strMethodName, 
                         l_ex.getMessage(), 
                         l_ex);            
                 }
                 catch (DataNetworkException l_ex)
                 {
                     log.error("DataNetworkException", l_ex);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + l_strMethodName, 
                         l_ex.getMessage(), 
                         l_ex);
                 }  
                 
                 //1.8.1.2 取得した銘柄.代用掛目 != 0 の場合
                 if (l_productRow.getMarginRatio() != 0)
                 {
                     //1.8.1.2.1 get補助口座(口座ID : , 補助口座ID : )
                     //補助口座を取得する。
                     //[引数] 
                     //口座ID： 注文単位Params.口座ID 
                     //補助口座ID： 注文単位Params.補助口座ID  
                     long l_lngAccountId = l_aioOrderUnitRow.getAccountId();    
                     long l_lngSubAccountId = l_aioOrderUnitRow.getSubAccountId();
                     WEB3GentradeAccountManager l_accountManager =
                         (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                     
                     SubAccount l_subGentAccount = null;
                     try
                     {
                         l_subGentAccount =
                             l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);

                     }
                     catch (NotFoundException l_ex)
                     {
                         log.debug("__NotFoundException__", l_ex);
                         //例外をスローする
                         log.exiting(l_strMethodName);
                         throw new WEB3SystemLayerException(
                             WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                             getClass().getName() + "." + l_strMethodName,
                             l_ex.getMessage(),
                             l_ex);
                     }        
                     //1.8.1.2.2 補助口座タイプを取得する。
                     SubAccountTypeEnum l_subAccountType = l_subGentAccount.getSubAccountType(); 
                     
                     //1.8.1.2.3 振替可能数量を算出する。 
                     //[引数] 
                     //口座ID： 注文単位Params.口座ID 
                     //銘柄タイプ： 注文単位Params.銘柄タイプ 
                     //銘柄ID： 注文単位Params.銘柄ID 
                     //税区分： 注文単位Params.税区分 
                     //預り区分： 
                     //補助口座タイプ = 1（預り金） の場合、1（保護） 
                     //補助口座タイプ = 2（保証金） の場合、2（代用）
                     String l_strDepositDiv = null;
                     if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountType))
                     {
                         l_strDepositDiv = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;
                     }
                     else
                     {
                         if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountType))
                         {
                             l_strDepositDiv = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;
                         }
                     }   
                     WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                         (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
                     
                     double l_dblTransPossibleAmount = 
                         l_web3AioBizLogicProvider.calcTransPossibleAmount(
                             l_lngAccountId,
                             l_aioOrderUnitRow.getProductType(),
                             l_aioOrderUnitRow.getProductId(),
                             l_aioOrderUnitRow.getTaxType(),
                             l_strDepositDiv);
                     
                     //1.8.1.2.4 取得した数量 > 0 の場合
                     if (l_dblTransPossibleAmount > 0)
                     {
                         //1.8.1.2.4.1 銘柄オブジェクトを取得する。 
                         //[引数] 
                         //銘柄タイプ： 注文単位Params.銘柄タイプ 
                         //銘柄ID： 注文単位Params.銘柄ID 
                         Product l_product = 
                             l_productManager.getProduct(
                                 l_aioOrderUnitRow.getProductType(),  
                                 l_aioOrderUnitRow.getProductId()); 
                         
                         //1.8.1.2.4.2 証券振替保有銘柄明細( )    
                         //保有銘柄明細のインスタンスを生成する。 
                         WEB3AioSecurityTransferProductCodeNameUnit l_securityTranProCodeNameUnit = 
                             new WEB3AioSecurityTransferProductCodeNameUnit();
                         
                         //1.8.1.2.4.3 プロパティセット
                         //保有銘柄明細.商品タイプ = 銘柄オブジェクト.銘柄タイプ
                         //保有銘柄明細.銘柄コード = （以下のとおり）
                         //  商品タイプ = ”債券” の場合、銘柄.回号コード(SONAR) + 銘柄.銘柄コード(SONAR)の上4桁
                         //  商品タイプ != ”債券” の場合、銘柄.getProductCode()の戻り値
                         //保有銘柄明細.銘柄名 = 銘柄オブジェクト.銘柄名
                         
                         //a> 保有銘柄明細.商品タイプ = 銘柄オブジェクト.銘柄タイプ
                         l_securityTranProCodeNameUnit.instrumentsType = 
                             String.valueOf(l_product.getProductType().intValue());
                         
                         ProductTypeEnum l_productTypeEnum = l_aioOrderUnitRow.getProductType();   
                         if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                         {
                             //商品タイプ 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 String.valueOf(ProductTypeEnum.EQUITY.intValue());
                             //銘柄コード
                             l_securityTranProCodeNameUnit.productCode =
                                 ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
                         }
                         //商品タイプ = ”債券” の場合、銘柄.回号コード(SONAR) + 銘柄.銘柄コード(SONAR)の上4桁
                         else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
                         {
                             //商品タイプ 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 String.valueOf(ProductTypeEnum.BOND.intValue());
                             //銘柄.銘柄コード(SONAR)の上4桁      
                             String l_strHostProductCode = ((BondProductRow)
                                 l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                             //銘柄.回号コード(SONAR)
                             String l_strHostIssueCode = ((BondProductRow)
                                 l_product.getDataSourceObject()).getHostProductIssueCode();
                             l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                             
                             l_securityTranProCodeNameUnit.productCode = l_strHostProductCode;
                         }
                         else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
                         {
                             //商品タイプ 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 String.valueOf(ProductTypeEnum.MUTUAL_FUND.intValue());
                             //銘柄コード
                             l_securityTranProCodeNameUnit.productCode =
                                 ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();
                         }
                         else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
                         {
                             //商品タイプ 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 
                                 String.valueOf(ProductTypeEnum.FOREIGN_EQUITY.intValue());
                             //銘柄コード
                             l_securityTranProCodeNameUnit.productCode =
                                 ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();
                         }
                         //保有銘柄明細.銘柄名 = 銘柄オブジェクト.銘柄名
                         l_securityTranProCodeNameUnit.productName = 
                             l_product.getStandardName();

                         //1.8.1.2.4.4 add(arg0 : Object)
                         //ArrayListに保有銘柄明細オブジェクトを追加する。 
                         //[引数] 
                         //arg0： 保有銘柄明細オブジェクト
                         int l_intTranProCodeSize = 0;
                         if (l_lisSecurityTranProCodeNameUnits != null)
                         {
                             l_intTranProCodeSize = l_lisSecurityTranProCodeNameUnits.size();     
                         }
                         //すでにArrayListに存在する銘柄については、スキップする。
                         boolean l_booMark = true;     

                         for (int j = 0; j < l_intTranProCodeSize; j++)
                         {
                             if (((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).productCode
                                 .equals(l_securityTranProCodeNameUnit.productCode) && 
                                 ((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).instrumentsType
                                 .equals(l_securityTranProCodeNameUnit.instrumentsType))
                             {
                                 l_booMark = false;
                                 break;    
                             }                        
                         }                  
                         if (l_booMark)
                         {
                             l_lisSecurityTranProCodeNameUnits.add(l_securityTranProCodeNameUnit);        
                         }                                      
                     }                    
                 }
             }
         }
         //1.9 toArray
         WEB3AioSecurityTransferProductCodeNameUnit[] l_arraySecurityTranProCodeNameUnits = 
             new WEB3AioSecurityTransferProductCodeNameUnit[l_lisSecurityTranProCodeNameUnits.size()];

         l_lisSecurityTranProCodeNameUnits.toArray(l_arraySecurityTranProCodeNameUnits);

         //1.10 銘柄名Comparatorインスタンスを生成する。 
         //[引数] 
         //orderBy： "A"（昇順） 
         WEB3AioProductNameComparator l_web3ProductNameComparator = 
             new WEB3AioProductNameComparator(WEB3AscDescDef.ASC);

         Comparator[] l_productNameComparators = {l_web3ProductNameComparator};
         //1.11 保有銘柄明細を銘柄名にて昇順にソートする。 
         //[引数] 
         //保有銘柄明細： 保有銘柄明細[] 
         //com： 銘柄名Comparator 
         WEB3ArraysUtility.sort(
             l_arraySecurityTranProCodeNameUnits,
             l_productNameComparators);

         log.exiting(l_strMethodName);

         return l_arraySecurityTranProCodeNameUnits;
    }
    
    /**
     * (create取得条件文字列)<BR>
     * リクエストデータから、データ取得条件文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）口座ID条件生成<BR>
     * <BR>
     *   条件文字列： "account_id=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３）補助口座ID条件生成<BR>
     * <BR>
     *   引数.預り区分 != 0（指定なし）の場合<BR>
     * <BR>
     *   条件文字列： " and sub_account_id=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４）銘柄タイプ条件生成<BR>
     * <BR>
     *   引数.銘柄タイプ == null の場合<BR> 
     * <BR>
     *   条件文字列： " and product_type>=? and product_type<=?" 
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR> 
     * <BR>
     *   引数.銘柄タイプ != null の場合<BR>
     * <BR>
     *   条件文字列： " and product_type=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５）銘柄ID条件生成<BR>
     * <BR>
     *   引数.銘柄コード != null の場合<BR>
     * <BR>
     *   条件文字列： " and product_id=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ６）ミニ株区分条件生成<BR>
     * <BR>
     *   条件文字列： " and mini_stock_div=?"<BR> 
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>  
     * ７）生成した文字列を返却する。  
     * @@param l_strProductType - 銘柄タイプ
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strBondDiv - 預り区分
     * @@return String
     * @@roseuid 41649A4902D9
     */
    protected String createGetCondCharacterString(
        String l_strProductType, 
        String l_strProductCode, 
        String l_strBondDiv) 
    {
        String l_strMethodName = 
            "createGetCondCharacterString(String l_strProductType, String l_strProductCode, String l_strBondDiv)";
        log.entering(l_strMethodName);
        
        //１）空の文字列を生成する。
        StringBuffer l_strBufWhere = new StringBuffer();
        
        //２）口座ID条件生成        
        //  条件文字列： "account_id=?"        
        //  上記文字列を１）の文字列の末尾に追加する。
        l_strBufWhere.append(" account_id = ? ");

        //３）補助口座ID条件生成
        //  引数.預り区分 != 0（指定なし） の場合
        //  条件文字列： " and sub_account_id=?"
        //  上記文字列を１）の文字列の末尾に追加する。
        if (!WEB3AioDepositTypeDivDef.DEFAULT.equals(l_strBondDiv))
        {
            l_strBufWhere.append(" and sub_account_id = ? ");    
        }

        //４）銘柄タイプ条件生成
        //  引数.銘柄タイプ == null の場合 
        //  条件文字列： " and product_type>=? and product_type<=?" 
        //  上記文字列を１）の文字列の末尾に追加する。 

        //  引数.銘柄タイプ != null の場合
        //  条件文字列： " and product_type=?"
        //  上記文字列を１）の文字列の末尾に追加する。
        if (l_strProductType == null)
        {
            l_strBufWhere.append(" and product_type >= ? and product_type <= ?");
        }
        else
        {
            l_strBufWhere.append(" and product_type = ? ");    
        }        

        //５）銘柄ID条件生成
        //  引数.銘柄コード != null の場合
        //  条件文字列： " and product_id=?"
        //  上記文字列を１）の文字列の末尾に追加する。
        if (l_strProductCode != null)
        {
            l_strBufWhere.append(" and product_id = ? ");    
        }   
        
        //６）ミニ株区分条件生成
        //  条件文字列： " and mini_stock_div=?" 
        //  上記文字列を１）の文字列の末尾に追加する。
        l_strBufWhere.append(" and mini_stock_div=?");
        
        log.exiting(l_strMethodName);
        
        //７）生成した文字列を返却する。
        return l_strBufWhere.toString();
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータリストを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）口座ID条件生成<BR>
     * <BR>
     *   引数.口座.getAccountId()の戻り値を１）のリストに追加する。<BR>
     * <BR>
     * ３）補助口座ID条件生成<BR>
     * <BR>
     * ３－１）引数.預り区分 = 1（保護） の場合<BR>
     * <BR>
     *   引数.口座.getSubAccount(補助口座タイプ).getSubAccountId()<BR>
     *       の戻り値を１）のリストに追加する。<BR>
     * <BR>
     *   [getSubAccountに渡す引数]<BR>
     *   補助口座タイプ = 1（預り金）<BR>
     * <BR>
     * ３－２）引数.預り区分 = 2（代用） の場合<BR>
     * <BR>
     *   引数.口座.getSubAccount(補助口座タイプ).getSubAccountId()<BR>
     *       の戻り値を１）のリストに追加する。<BR>
     * <BR>
     *   [getSubAccountに渡す引数]<BR>
     *   補助口座タイプ = 2（保証金）<BR>
     * <BR>
     * ４）銘柄タイプ条件生成<BR>
     * <BR>
     *   引数.銘柄タイプ == null の場合<BR> 
     * <BR>
     *   1（株式）、3（投信）を１）のリストに追加する。<BR>
     * <BR>
     *   引数.銘柄タイプ != null の場合<BR>
     * <BR>
     *   引数.銘柄タイプを１）のリストに追加する。<BR>
     * <BR>
     * ５）銘柄ID条件生成<BR>
     * <BR>
     *   引数.銘柄コード != null の場合<BR>
     * <BR>
     *   AIOプロダクトマネージャ.get銘柄(銘柄タイプ, 銘柄コード, 証券会社).getProductId()<BR>
     *   の戻り値を１）のリストに追加する。<BR>
     * <BR>
     *   [get銘柄に渡す引数]<BR>
     *   銘柄タイプ = 引数.銘柄タイプ<BR>
     *   銘柄コード = 引数.銘柄コード<BR>
     *   証券会社 = 引数.口座.getInstitution()の戻り値<BR>
     * <BR>
     * ６）ミニ株区分条件生成
     * <BR>
     *   0（DEFAULT）を１）のリストに追加する。<BR> 
     * <BR> 
     * ７）リストから配列を取得し、返却する。
     * @@param l_mainAccount - 口座オブジェクト
     * @@param l_strProductType - 銘柄タイプ
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strBondDiv - 預り区分
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 41649A6A01CF
     */
    protected Object[] createGetCondDataContainer(
        MainAccount l_mainAccount, 
        String l_strProductType, 
        String l_strProductCode, 
        String l_strBondDiv) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "createGetCondDataContainer(MainAccount l_mainAccount, String l_strProductType, String l_strProductCode, String l_strBondDiv)";
        log.entering(l_strMethodName);
        
        //１）空のArrayListを生成する。
        List l_lisBindVars = new ArrayList();
        
        //２）口座ID条件生成
        //  引数.口座.getAccountId()の戻り値を１）のリストに追加する。
        long l_lngAccountId = l_mainAccount.getAccountId();
        l_lisBindVars.add(new Long(l_lngAccountId));

        //３）補助口座ID条件生成
        //get subAccountId
        long l_subAccountId;
        
        //３－１）引数.預り区分 = 1（保護） の場合
        //  引数.口座.getSubAccount(補助口座タイプ).getSubAccountId()
        //      の戻り値を１）のリストに追加する。
        //  [getSubAccountに渡す引数]
        //  補助口座タイプ = 1（預り金）
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_strBondDiv))
        {
            try
            {
                l_subAccountId = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId();
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in getSubAccount(預り金)__", l_ex);
                log.exiting(l_strMethodName);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);            
            }
        }

        //３－２）引数.預り区分 = 2（代用） の場合
        //  引数.口座.getSubAccount(補助口座タイプ).getSubAccountId()
        //      の戻り値を１）のリストに追加する。
        //  [getSubAccountに渡す引数]
        //  補助口座タイプ = 2（保証金）
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_strBondDiv))
        {
            try
            {
                l_subAccountId = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getSubAccountId();
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in getSubAccount(保証金)__", l_ex);
                log.exiting(l_strMethodName);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);            
            }
        }        

        //４）銘柄タイプ条件生成
        //引数.銘柄タイプ == null の場合
        //1（株式）、3（投信）を１）のリストに追加する。
        
        //  引数.銘柄タイプ != null の場合
        //  引数.銘柄タイプを１）のリストに追加する。
        if (l_strProductType == null)
        {
            l_lisBindVars.add(ProductTypeEnum.EQUITY);
            l_lisBindVars.add(ProductTypeEnum.MUTUAL_FUND);
        }
        else
        {
            l_lisBindVars.add(l_strProductType);    
        } 

        //５）銘柄ID条件生成
        //  引数.銘柄コード != null の場合
        //  AIOプロダクトマネージャ.get銘柄(銘柄タイプ, 銘柄コード, 証券会社).getProductId()
        //  の戻り値を１）のリストに追加する。
        //  [get銘柄に渡す引数]
        //  銘柄タイプ = 引数.銘柄タイプ
        //  銘柄コード = 引数.銘柄コード
        //  証券会社 = 引数.口座.getInstitution()の戻り値      
        
        if (l_strProductCode != null)
        {
            //a> FinApp, TradingModule
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
            //b> AIOプロダクトマネージャを取得 
            WEB3AioProductManager l_productManager = 
                (WEB3AioProductManager)l_tradingModule.getProductManager();
                
            //productType
            ProductTypeEnum l_productTypeEnum;
        
            if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strProductType)) 
            {
                l_productTypeEnum = ProductTypeEnum.EQUITY;
            }
            else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strProductType)) 
            {
                l_productTypeEnum = ProductTypeEnum.BOND;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strProductType)) 
            {
                l_productTypeEnum = ProductTypeEnum.MUTUAL_FUND;
            }
            else 
            {
                l_productTypeEnum = ProductTypeEnum.FOREIGN_EQUITY;
            }
            
            //getInstitution
            Institution l_institution = l_mainAccount.getInstitution();
                
            //c> getProductId
            Product l_product = l_productManager.getProduct(
                l_productTypeEnum,
                l_strProductCode,
                l_institution);  
            long l_lngProductId = l_product.getProductId();    
            
                
            l_lisBindVars.add(new Long(l_lngProductId));      
        }

        //６）ミニ株区分条件生成
        //0（DEFAULT）を１）のリストに追加する。
        l_lisBindVars.add(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

        //７）リストから配列を取得し、返却する。
        Object[] l_bindVars = new Object[l_lisBindVars.size()];
        l_lisBindVars.toArray(l_bindVars);
        
        log.exiting(l_strMethodName);
        
        return l_bindVars;
    }
    
    /**
     * (create預り証券明細)<BR>
     * 預り証券明細オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図「（証券振替入力）create預り証券明細」 参照
     * @@param l_params - 保有資産データ
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit
     * @@throws WEB3BaseException
     * @@roseuid 4164C4B80385
     */
    protected WEB3AioSecurityTransferUnit createInstitutionBondDetails(DailyAssetParams l_params) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createInstitutionBondDetails(DailyAssetParams l_params)";
        log.entering(l_strMethodName);
        
        //1.1 空の預り証券明細インスタンスを生成する。 
        WEB3AioSecurityTransferUnit l_web3AioSecurityTransferUnit =
            new WEB3AioSecurityTransferUnit();
            
        //1.2 銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄タイプ： 引数.保有資産.銘柄タイプ 
        //銘柄ID： 引数.保有資産.銘柄ID 
        
        //a> FinApp, TradingModule
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //b> AIOプロダクトマネージャを取得 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //c> productType
        ProductTypeEnum l_productTypeEnum = l_params.getProductType();   
        //d> productId
        long l_lngProductId = l_params.getProductId();     
                
        //e> getProduct
        Product l_product = 
            l_productManager.getProduct(l_productTypeEnum, l_lngProductId); 
        
        //1.3 補助口座を取得する。 
        //[引数] 
        //口座ID： 引数.保有資産.口座ID 
        //補助口座ID： 引数.保有資産.補助口座ID 
        
        //a> WEB3GentradeAccountManager
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //c> accountId 
        long l_lngAccountId = l_params.getAccountId();
        //d> subAccountId (SubAccountTypeEnum)
        long l_lngSubAccountId = l_params.getSubAccountId();
        
        //getsubaccount
        try
        {
            //throw NotFoundException
            SubAccount l_subAccount = 
                l_gentradeAccountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
                
            //1.4 補助口座タイプを取得する。
            SubAccountTypeEnum l_subAccountTypeEnum = l_subAccount.getSubAccountType();
            
            //1.5 calc振替可能数量  
            //[引数] 
            //口座ID： 引数.保有資産.口座ID --l_lngAccountId
            //銘柄タイプ： 引数.保有資産.銘柄タイプ --l_productTypeEnum
            //銘柄ID： 引数.保有資産.銘柄ID --l_lngProductId
            //税区分： 引数.保有資産.税区分 
            //預り区分： 
            //補助口座タイプ = 1（預り金） の場合、1（保護） 
            //補助口座タイプ = 2（保証金） の場合、2（代用） 

            //a> get WEB3AioBizLogicProvider
            WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            //b> 税区分
            TaxTypeEnum l_taxTypeEnum = l_params.getTaxType();
            
            //c> 預り区
            String l_strDepositTypeDivDef = null;
            
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;    
            }
            else if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;    
            }
            
            //d> calc振替可能数量 
            double l_dblTransPossibleAmount = 
                l_web3AioBizLogicProvider.calcTransPossibleAmount(
                    l_lngAccountId,
                    l_productTypeEnum,
                    l_lngProductId,
                    l_taxTypeEnum,
                    l_strDepositTypeDivDef);
                
            //1.6 証券評価額を算出する。 
            //[引数] 
            //銘柄ID： 引数.保有資産.銘柄ID --l_lngProductId
            //預り区分： --l_strDepositTypeDivDef
            //補助口座タイプ = 1（預り金） の場合、1（保護） 
            //補助口座タイプ = 2（保証金） の場合、2（代用） 
            //数量： calc振替可能数量()の戻り値 --l_dblTransPossibleAmount

            double l_strStockEvalueAmount = 
                l_web3AioBizLogicProvider.calcStockEvalueAmount(
                    l_lngProductId,
                    l_strDepositTypeDivDef,
                    l_dblTransPossibleAmount);
                    
            //1.7 以下のようにプロパティをセットする。
            //預り証券明細.商品タイプ = 引数.保有資産.銘柄タイプ --l_productTypeEnum
            //預り証券明細.銘柄コード = （以下のとおり）
            //  商品タイプ = ”債券” の場合、銘柄.回号コード(SONAR) + 銘柄.銘柄コード(SONAR)の上4桁
            //  商品タイプ != ”債券” の場合、銘柄.getProductCode()の戻り値
            //預り証券明細.銘柄名 = 銘柄.getProductName()の戻り値
            //預り証券明細.口座区分 = （以下のとおり）
            //     引数.保有資産.税区分 = 0（その他） の場合、null
            //     引数.保有資産.税区分 = 1（一般） の場合、0（一般）
            //     引数.保有資産.税区分 = 2（特定） or 3（特定かつ源泉徴収） の場合、1（特定）
            //預り証券明細.数量 = 入出金計算サービス.calc振替可能数量()の戻り値 --l_dblTransPossibleAmount
            //預り証券明細.評価額 = 入出金計算サービス.calc証券評価額()の戻り値 --l_strStockEvalueAmount
            //預り証券明細.預り区分 = （以下のとおり）--l_strDepositTypeDivDef
            //     補助口座タイプ = 1（預り金） の場合、1（保護）
            //     補助口座タイプ = 2（保証金） の場合、2（代用）
            
            //a> 預り証券明細.商品タイプ                       
            //b> 預り証券明細.銘柄コード
            if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.EQUITY.intValue());
                //銘柄コード
                l_web3AioSecurityTransferUnit.productCode =
                    ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            //商品タイプ = ”債券” の場合、銘柄.回号コード(SONAR) + 銘柄.銘柄コード(SONAR)の上4桁
            else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.BOND.intValue());
                //銘柄.銘柄コード(SONAR)の上4桁      
                String l_strHostProductCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                //銘柄.回号コード(SONAR)
                String l_strHostIssueCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductIssueCode();
                l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                
                l_web3AioSecurityTransferUnit.productCode = l_strHostProductCode;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.MUTUAL_FUND.intValue());
                //銘柄コード
                l_web3AioSecurityTransferUnit.productCode =
                    ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    
                    String.valueOf(ProductTypeEnum.FOREIGN_EQUITY.intValue());
                //銘柄コード
                l_web3AioSecurityTransferUnit.productCode =
                    ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            
            //c> 預り証券明細.銘柄名
            l_web3AioSecurityTransferUnit.productName = l_product.getStandardName();
            
            //d> 預り証券明細.口座区分
            if (TaxTypeEnum.UNDEFINED.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = null;            
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.NORMAL;            
            }
            else 
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.SPECIAL;            
            }

            //e> 預り証券明細.数量
            l_web3AioSecurityTransferUnit.changePossQuantity = WEB3StringTypeUtility.formatNumber(l_dblTransPossibleAmount);
            
            //f> 預り証券明細.評価額
            l_web3AioSecurityTransferUnit.marketValue = WEB3StringTypeUtility.formatNumber(l_strStockEvalueAmount) ;
            
            //g> 預り証券明細.預り区分
            l_web3AioSecurityTransferUnit.depositDiv = l_strDepositTypeDivDef;            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in getSubAccount__", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);            
        }
        
        log.exiting(l_strMethodName);
        
        return l_web3AioSecurityTransferUnit;
    }
    
    /**
     * (create預り証券明細)<BR>
     * 預り証券明細オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図「（証券振替入力）create預り証券明細２」 参照
     * @@param l_params - 注文単位データ
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit
     * @@throws WEB3BaseException
     * @@roseuid 4164C4B80385
     */
    protected WEB3AioSecurityTransferUnit createInstitutionBondDetails(AioOrderUnitParams l_params) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createInstitutionBondDetails(AioOrderUnitParams l_params)";
        log.entering(l_strMethodName);
        
        //1.1 空の預り証券明細インスタンスを生成する。 
        WEB3AioSecurityTransferUnit l_web3AioSecurityTransferUnit =
            new WEB3AioSecurityTransferUnit();
            
        //1.2 銘柄オブジェクトを取得する。 
        //[引数] 
        //銘柄タイプ： 引数.注文単位.銘柄タイプ 
        //銘柄ID： 引数.注文単位.銘柄ID 

        //a> FinApp, TradingModule
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //b> AIOプロダクトマネージャを取得 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //c> productType
        ProductTypeEnum l_productTypeEnum = l_params.getProductType();   
        //d> productId
        long l_lngProductId = l_params.getProductId();     
                
        //e> getProduct
        Product l_product = 
            l_productManager.getProduct(l_productTypeEnum, l_lngProductId); 
        
        //1.3 補助口座を取得する。 
        //[引数] 
        //口座ID： 引数.注文単位.口座ID 
        //補助口座ID： 引数.注文単位.補助口座ID 
        
        //a> WEB3GentradeAccountManager
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //c> accountId 
        long l_lngAccountId = l_params.getAccountId();
        //d> subAccountId (SubAccountTypeEnum)
        long l_lngSubAccountId = l_params.getSubAccountId();
        
        //getsubaccount
        try
        {
            //throw NotFoundException
            SubAccount l_subAccount = 
                l_gentradeAccountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
                
            //1.4 補助口座タイプを取得する。
            SubAccountTypeEnum l_subAccountTypeEnum = l_subAccount.getSubAccountType();
            
            //1.5 calc振替可能数量  
            //[引数] 
            //口座ID： 引数.注文単位.口座ID 
            //銘柄タイプ： 引数.注文単位.銘柄タイプ 
            //銘柄ID： 引数.注文単位.銘柄ID 
            //税区分： 引数.注文単位.税区分 
            //預り区分： 
            //補助口座タイプ = 1（預り金） の場合、1（保護） 
            //補助口座タイプ = 2（保証金） の場合、2（代用）  

            //a> get WEB3AioBizLogicProvider
            WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            //b> 税区分
            TaxTypeEnum l_taxTypeEnum = l_params.getTaxType();
            
            //c> 預り区
            String l_strDepositTypeDivDef = null;
            
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;    
            }
            else if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;    
            }
            
            //d> calc振替可能数量 
            double l_dblTransPossibleAmount = 
                l_web3AioBizLogicProvider.calcTransPossibleAmount(
                    l_lngAccountId,
                    l_productTypeEnum,
                    l_lngProductId,
                    l_taxTypeEnum,
                    l_strDepositTypeDivDef);
                
            //1.6 証券評価額を算出する。 
            //[引数] 
            //銘柄ID： 引数.注文単位.銘柄ID 
            //税区分： 引数.注文単位.税区分 
            //預り区分： 
            //補助口座タイプ = 1（預り金） の場合、1（保護） 
            //補助口座タイプ = 2（保証金） の場合、2（代用） 
            //数量： calc振替可能数量()の戻り値 

            double l_strStockEvalueAmount = 
                l_web3AioBizLogicProvider.calcStockEvalueAmount(
                    l_lngProductId,
                    l_strDepositTypeDivDef,
                    l_dblTransPossibleAmount);
                    
            //1.7 以下のようにプロパティをセットする。
            //預り証券明細.商品タイプ = 引数.注文単位.銘柄タイプ --l_productTypeEnum
            //預り証券明細.銘柄コード = （以下のとおり）
            //  商品タイプ = ”債券” の場合、銘柄.回号コード(SONAR) + 銘柄.銘柄コード(SONAR)の上4桁
            //  商品タイプ != ”債券” の場合、銘柄.getProductCode()の戻り値
            //預り証券明細.銘柄名 = 銘柄.getProductName()の戻り値
            //預り証券明細.口座区分 = （以下のとおり）
            //     引数.注文単位.税区分 = 0（その他） の場合、null
            //     引数.注文単位.税区分 = 1（一般） の場合、0（一般）
            //     引数.注文単位.税区分 = 2（特定） or 3（特定かつ源泉徴収） の場合、1（特定）
            //預り証券明細.数量 = 入出金計算サービス.calc振替可能数量()の戻り値 --l_dblTransPossibleAmount
            //預り証券明細.評価額 = 入出金計算サービス.calc証券評価額()の戻り値 --l_strStockEvalueAmount
            //預り証券明細.預り区分 = （以下のとおり）--l_strDepositTypeDivDef
            //     補助口座タイプ = 1（預り金） の場合、1（保護）
            //     補助口座タイプ = 2（保証金） の場合、2（代用）
            
            //a> 預り証券明細.商品タイプ                       
            //b> 預り証券明細.銘柄コード
            if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.EQUITY.intValue());
                //銘柄コード
                l_web3AioSecurityTransferUnit.productCode =
                    ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            //商品タイプ = ”債券” の場合、銘柄.回号コード + 銘柄.銘柄コードの上4桁
            else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.BOND.intValue());
                //銘柄.銘柄コード(SONAR)の上4桁      
                String l_strHostProductCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                //銘柄.回号コード(SONAR)
                String l_strHostIssueCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductIssueCode();
                l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                
                l_web3AioSecurityTransferUnit.productCode = l_strHostProductCode;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.MUTUAL_FUND.intValue());
                //銘柄コード
                l_web3AioSecurityTransferUnit.productCode =
                    ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
            {
                //商品タイプ 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    
                    String.valueOf(ProductTypeEnum.FOREIGN_EQUITY.intValue());
                //銘柄コード
                l_web3AioSecurityTransferUnit.productCode =
                    ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            
            //c> 預り証券明細.銘柄名
            l_web3AioSecurityTransferUnit.productName = l_product.getStandardName();
            
            //d> 預り証券明細.口座区分
            if (TaxTypeEnum.UNDEFINED.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = null;            
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.NORMAL;            
            }
            else 
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.SPECIAL;            
            }

            //e> 預り証券明細.数量
            l_web3AioSecurityTransferUnit.changePossQuantity = WEB3StringTypeUtility.formatNumber(l_dblTransPossibleAmount);
            
            //f> 預り証券明細.評価額
            l_web3AioSecurityTransferUnit.marketValue = WEB3StringTypeUtility.formatNumber(l_strStockEvalueAmount) ;
            
            //g> 預り証券明細.預り区分
            l_web3AioSecurityTransferUnit.depositDiv = l_strDepositTypeDivDef;            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in getSubAccount__", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);            
        }
        
        log.exiting(l_strMethodName);
        
        return l_web3AioSecurityTransferUnit;
    }
    
    /**
     * (sort預り証券明細)<BR>
     * 預り証券明細の配列をソートキーに従ってソートする。<BR>
     * <BR>
     * １）空のArrayListインスタンスを生成する。 <BR>
     * <BR>
     * ２）ソートキーの配列の各要素について以下の処理を行う。<BR>
     * <BR>
     * ２－１）ソートキー.キー項目 = "商品タイプ”の場合<BR>
     * <BR>
     *   ・商品タイプComparatorを生成する。<BR>
     *     引数.orderByには、ソートキー.昇順/降順を設定する。<BR>
     * <BR>
     *   ・生成したComparatorをListに追加する。<BR>
     * <BR>
     * ２－２）ソートキー.キー項目 = "銘柄コード”の場合<BR>
     * <BR>
     *   ・銘柄コードComparatorを生成する。<BR>
     *     引数.orderByには、ソートキー.昇順/降順を設定する。<BR>
     * <BR>
     *   ・生成したComparatorをListに追加する。<BR>
     * <BR>
     * ２－３）ソートキー.キー項目 = "口座区分”の場合<BR>
     * <BR>
     *   ・口座区分Comparatorを生成する。<BR>
     *     引数.orderByには、ソートキー.昇順/降順を設定する。<BR>
     * <BR>
     *   ・生成したComparatorをListに追加する。<BR>
     * <BR>
     * ２－４）ソートキー.キー項目 = "数量”の場合<BR>
     * <BR>
     *   ・数量Comparatorを生成する。<BR>
     *     引数.orderByには、ソートキー.昇順/降順を設定する。<BR>
     * <BR>
     *   ・生成したComparatorをListに追加する。<BR>
     * <BR>
     * ２－５）ソートキー.キー項目 = "評価額”の場合<BR>
     * <BR>
     *   ・評価額Comparatorを生成する。<BR>
     *     引数.orderByには、ソートキー.昇順/降順を設定する。<BR>
     * <BR>
     *   ・生成したComparatorをListに追加する。<BR>
     * <BR>
     * ２－６）ソートキー.キー項目 = "預り区分”の場合<BR>
     * <BR>
     *   ・預り区分Comparatorを生成する。<BR>
     *     引数.orderByには、ソートキー.昇順/降順を設定する。<BR>
     * <BR>
     *   ・生成したComparatorをListに追加する。<BR>
     * <BR>
     * ３）ArrayListからComparatorの配列を取得する。<BR>
     * <BR>
     * ４）Comparatorの配列順のソート処理を行う。<BR>
     *    WEB3ArraysUtility.sort(引数.明細, Comparator[]) <BR>
     * <BR>
     * ５）ソートされた明細の配列を返却する。
     * @@param l_securityTransferUnit - 預り証券明細の配列
     * @@param l_securityTransferSortKeyUnit - 証券振替ソートキーの配列
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit[]
     * @@roseuid 4164C67303C3
     */
    protected WEB3AioSecurityTransferUnit[] sortInstitutionBondDetails(
        WEB3AioSecurityTransferUnit[] l_securityTransferUnit, 
        WEB3AioSecurityTransferSortKeyUnit[] l_securityTransferSortKeyUnit) 
    {
        String l_strMethodName = 
            "sortInstitutionBondDetails(WEB3AioSecurityTransferUnit[] l_securityTransferUnit, WEB3AioSecurityTransferSortKeyUnit[] l_securityTransferSortKeyUnit)";
        log.entering(l_strMethodName);
        
        //１）空のArrayListインスタンスを生成する。 
        List l_lisSecurityTransferUnit = new ArrayList();

        //２）ソートキーの配列の各要素について以下の処理を行う。
        if (l_securityTransferSortKeyUnit == null)
        {
            log.debug("パラメータ値がNULLする！");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        for (int i = 0; i < l_securityTransferSortKeyUnit.length; i++)
        {
            //２－１）ソートキー.キー項目 = "商品タイプ”の場合
            //  ・商品タイプComparatorを生成する。
            //    引数.orderByには、ソートキー.昇順/降順を設定する。
            //  ・生成したComparatorをListに追加する。
            if (WEB3AioTransferSortkeyDef.INSTRUMENTS_TYPE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioInstrumentsTypeComparator l_instrumentsTypeComparator =
                    new WEB3AioInstrumentsTypeComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_instrumentsTypeComparator);
            }
 
            //２－２）ソートキー.キー項目 = "銘柄コード”の場合
            //  ・銘柄コードComparatorを生成する。
            //    引数.orderByには、ソートキー.昇順/降順を設定する。
            //  ・生成したComparatorをListに追加する。
            if (WEB3AioTransferSortkeyDef.PRODUCT_CODE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioProductCodeComparator l_productCodeComparator =
                    new WEB3AioProductCodeComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_productCodeComparator);    
            }            

            //２－３）ソートキー.キー項目 = "口座区分”の場合
            //  ・口座区分Comparatorを生成する。
            //    引数.orderByには、ソートキー.昇順/降順を設定する。
            //  ・生成したComparatorをListに追加する。
            if (WEB3AioTransferSortkeyDef.TAX_TYPE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioTaxTypeComparator l_taxTypeComparator =
                    new WEB3AioTaxTypeComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_taxTypeComparator);    
            }

            //２－４）ソートキー.キー項目 = "数量”の場合
            //  ・数量Comparatorを生成する。
            //    引数.orderByには、ソートキー.昇順/降順を設定する。
            //  ・生成したComparatorをListに追加する。
            if (WEB3AioTransferSortkeyDef.CHANGE_POSS_QUANTITY.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioChangePossQuantityComparator l_changePossQuantityComparator =
                    new WEB3AioChangePossQuantityComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_changePossQuantityComparator);    
            }

            //２－５）ソートキー.キー項目 = "評価額”の場合
            //  ・評価額Comparatorを生成する。
            //    引数.orderByには、ソートキー.昇順/降順を設定する。
            //  ・生成したComparatorをListに追加する。
            if (WEB3AioTransferSortkeyDef.MARKET_VALUE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioMarketValueComparator l_marketValueComparator =
                    new WEB3AioMarketValueComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_marketValueComparator);    
            }

            //２－６）ソートキー.キー項目 = "預り区分”の場合
            //  ・預り区分Comparatorを生成する。
            //    引数.orderByには、ソートキー.昇順/降順を設定する。
            //  ・生成したComparatorをListに追加する。
            if (WEB3AioTransferSortkeyDef.DEPOSIT_DIV.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioDepositDivComparator l_depositDivComparator =
                    new WEB3AioDepositDivComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_depositDivComparator);    
            }            
        }

        //３）ArrayListからComparatorの配列を取得する。
        Comparator[] l_comparator = 
            new Comparator[l_lisSecurityTransferUnit.size()];
        //toArray
        l_lisSecurityTransferUnit.toArray(l_comparator); 

        //４）Comparatorの配列順のソート処理を行う。
        //   WEB3ArraysUtility.sort(引数.明細, Comparator[]) 
        WEB3ArraysUtility.sort(l_securityTransferUnit, l_comparator);

        //５）ソートされた明細の配列を返却する。        
        
        log.exiting(l_strMethodName);
        
        return l_securityTransferUnit;
    }
    
    /**
     * (get表示明細)<BR>
     * 預り証券明細の配列から要求ページ内に表示される明細の配列を取得して返却する。<BR>
     * <BR>
     * １）空のArrayListインスタンスを生成する。<BR>
     * <BR>
     * ２）預り証券明細[引数.fromIndex]から預り証券明細［引数.toIndex]の要素を、<BR>
     *     １）のArrayListに追加する。<BR>
     * <BR>
     * ３）ArrayListから配列を取得する。<BR>
     * <BR>
     * ４）生成した配列を返却する。
     * @@param l_securityTransferUnit - 預り証券明細の配列
     * @@param l_intFromIndex - 表示対象の開始位置のインデックス番号
     * @@param l_intToIndex - 表示対象の終了位置のインデックス番号
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit[]
     * @@roseuid 4164C9CC026C
     */
    protected WEB3AioSecurityTransferUnit[] getIndicationDetails(WEB3AioSecurityTransferUnit[] l_securityTransferUnit, int l_intFromIndex, int l_intToIndex) 
    {        
        return null;
    }
}
@
