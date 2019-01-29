head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP残高照会サービス実装クラス(WEB3IfoBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成 
                 : 2006/7/24 周捷(中訊)　@仕様変更モデル526、537    
                 : 2006/8/11 郭英(中訊)　@仕様変更モデル544     
Revesion History : 2007/06/29 孫洪江 (中訊) 仕様変更モデルNo.752
Revesion History : 2008/08/20 劉剣 (中訊) IFO小数点対応
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsBalRefTotalParIndexUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparator;
import webbroker3.ifo.message.WEB3OptionsSessionTypeComparator;
import webbroker3.ifo.service.delegate.WEB3IfoBalanceReferenceService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

/**
 * (先物OP残高照会サービスImpl)<BR>
 * 先物OP残高照会サービス実装クラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoBalanceReferenceServiceImpl extends WEB3ClientRequestService implements WEB3IfoBalanceReferenceService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoBalanceReferenceServiceImpl.class);
        
   
    /**
     * 株価指数先物/オプション残高照会処理を行う。<BR>
     * <BR>
     * this.get残高照会()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41AAC9ED0015
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FuturesOptionsBalanceReferenceRequest) 
        {
           l_response = getBalanceReference((WEB3FuturesOptionsBalanceReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3FuturesOptionsBalanceReferenceTotalRequest)
        {
            l_response = getBalanceTotal((WEB3FuturesOptionsBalanceReferenceTotalRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
   }
   
    /**
     * (get残高照会)<BR>
     * 株価指数先物/オプション残高照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(先物OP残高照会)get残高照会」<BR>
     * 参照<BR>
     * @@param l_request - 先物OP残高照会リクエストオブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse
     * @@roseuid 41AACA0802F3
     */
    protected WEB3FuturesOptionsBalanceReferenceResponse getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //リクエストデータの整合性をチェックする。
        l_request.validate();
        //補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount(l_request.fuOpDiv);
        //システム売買停止(バッチ中、緊急停止中)チェックを実施する。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
       
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        
        WEB3IfoProductImpl l_ifoProductImpl = null;
        Institution l_institution = l_subAccount.getInstitution();

        //リクエストデータに銘柄コードが設定されている場合
        if (l_request.productCode != null)
        {
             //銘柄オブジェクトを取得する。該当する銘柄が存在しない場合は例外をスローする。
             try
             {
                 l_ifoProductImpl = l_productManager.getIfoProduct(l_institution, l_request.productCode);
             }
             catch (NotFoundException e)
             {
                 throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                     getClass().getName() + "." + STR_METHOD_NAME);
             }
        }
        
        //リクエストデータに銘柄特定項目(取引市場、指数種別、限月)が設定されている場合
        if ((l_request.marketCode != null)
            && (l_request.targetProductCode != null)
            && (l_request.delivaryMonth != null))
        {
            if(WEB3FuturesOptionDivDef.FUTURES.equals(l_request.fuOpDiv))
            {
                //銘柄オブジェクトを取得する。該当する銘柄が存在しない場合は例外をスローする。
                try
                {
                    l_ifoProductImpl = l_productManager.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.FUTURES,
                        0,
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);
                }
                catch (NotFoundException e)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            else if (WEB3FuturesOptionDivDef.OPTION.equals(l_request.fuOpDiv))
            {
                IfoDerivativeTypeEnum l_ifoDerivativeType = null;
                if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))
                {
                    l_ifoDerivativeType = IfoDerivativeTypeEnum.CALL_OPTIONS;
                }
                else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))
                {
                    l_ifoDerivativeType = IfoDerivativeTypeEnum.PUT_OPTIONS;
                }
                else
                {
                    //オプション商品区分が存在しない値
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }

                //銘柄オブジェクトを取得する。該当する銘柄が存在しない場合は例外をスローする。
                try
                {
                l_ifoProductImpl = l_productManager.getIfoProduct(
                    l_institution,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    l_ifoDerivativeType,
                    Double.parseDouble(l_request.strikePrice),
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
                }
                catch (NotFoundException e)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        
        //指定口座の保持する全建玉の銘柄コードと銘柄名の一覧を取得する。
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits = l_positionManager.createProductCodeNameFromContract(l_subAccount,true,l_request.fuOpDiv);
        //レスポンスデータを生成する。
        WEB3FuturesOptionsBalanceReferenceResponse l_response = (WEB3FuturesOptionsBalanceReferenceResponse)l_request.createResponse();
        //(*)create銘柄コード名称from建玉()の戻り値 != nullの場合
        if (l_productCodeNameUnits != null)
        {
            //検索条件文字列を作成する。
            String l_strQuery = this.createQueryString(l_ifoProductImpl);
            //検索条件データコンテナを作成する。
            String[] l_strContainer = this.createQueryContainer(l_ifoProductImpl);  
            //残高照会画面に表示する残高照会明細の一覧を作成する。
            WEB3FuturesOptionsDetailUnit[] l_detailUnit = l_positionManager.createIfoBalanceReferenceDetailUnit(l_subAccount,l_request.fuOpDiv,l_request.settlementState,l_strQuery,l_strContainer);
            //残高照会明細をソートする。
            this.sortBalanceReferenceDetailUnit(l_detailUnit,l_request.sortKeys);
            
            //(1)該当データなしの場合(create銘柄コード名称from建玉()の戻り値がnull　@または　@create残高照会明細()の戻り値がnullの場合)
            if (l_productCodeNameUnits == null || l_detailUnit == null )
            {
                //レスポンス.残高照会明細          
                l_response.balanceReference = null;
                //create銘柄コード名称from建玉()の戻り値
                l_response.futOpProductCodeNames = l_productCodeNameUnits;
                return l_response;          
            }
            //(2)(1)以外の場合
            else
            {
                
                //create銘柄コード名称from建玉()の戻り値
                l_response.futOpProductCodeNames = l_productCodeNameUnits;
                //レスポンス.残高照会明細  
                WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_detailUnit, Integer.parseInt(l_request.pageIndex),Integer.parseInt(l_request.pageSize));
                l_response.balanceReference = (WEB3FuturesOptionsDetailUnit[])l_pageIndexInfo.getArrayReturned(WEB3FuturesOptionsDetailUnit.class);
                //レスポンス.総ページ数
                l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
                //レスポンス.総レコード数
                l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
                //レスポンス.表示ページ番号 
                l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();                                                       
            }
                       
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get残高合計)<BR>
     * 株価指数先物/オプション残高合計処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(先物OP残高照会)get残高合計」<BR>
     * 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 先物OP残高照会残高合計リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse
     * @@roseuid 41AD2DE80020
     */
    protected WEB3FuturesOptionsBalanceReferenceTotalResponse getBalanceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1.リクエストデータの整合性をチェックする。
        l_request.validate();
        //1.2.補助口座を取得する。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount(l_request.fuOpDiv);
        //1.3.システム売買停止(バッチ中、緊急停止中)チェックを実施する。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        //1.4.レスポンスデータを生成する。
        WEB3FuturesOptionsBalanceReferenceTotalResponse l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse)l_request.createResponse();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        //1.5.残高照会画面に表示する残高照会明細の一覧を作成する。
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = l_positionManager.createIfoBalanceReferenceDetailUnit(l_subAccount,l_request.fuOpDiv,null,null,null);
        //(*)create先物OP残高照会明細()の戻り値の要素数分Loop処理
        
        int l_intDetailUnitLength = 0;
        if (l_detailUnit != null)
        {
            l_intDetailUnitLength = l_detailUnit.length;
        }
        //買建玉総数量
        double l_dblBuyQuantity = 0;
        //買建玉評価損益合計
        double l_dblBuyIncome = 0;
        //買建玉評価損益合計(諸経費込）
        double l_dblBuyIncomeCost = 0;
        //買建玉時価総額
        double l_dblBuyCurrentPrice = 0;
        
        //プット買建玉総数量
        double l_dblPutBuyQuantity = 0;
        //プット買建玉評価損益合計
        double l_dblPutBuyIncome = 0; 
        //プット買建玉評価損益合計(諸経費込)
        double l_dblPutBuyIncomeCost = 0; 
        //プット買建玉時価総額
        double l_dblPutBuyCurrentPrice = 0;
        
        //コール買建玉総数量
        double l_dblCallBuyQuantity = 0;
        //コール買建玉評価損益合計
        double l_dblCallBuyIncome = 0; 
        //コール買建玉評価損益合計(諸経費込)
        double l_dblCallBuyIncomeCost = 0; 
        //コール買建玉時価総額
        double l_dblCallBuyCurrentPrice = 0;
        
        //売建玉総数量
        double l_dblSellQuantity = 0;
        //売建玉評価損益合計
        double l_dblSellIncome = 0;
        //売建玉評価損益合計(諸経費込)
        double l_dblSellIncomeCost = 0;
        //売建玉時価総額
        double l_dblSellCurrentPrice = 0;
        
        //プット売建玉総数量
        double l_dblPutSellQuantity = 0;
        //プット売建玉評価損益合計
        double l_dblPutSellIncome = 0; 
        //プット売建玉評価損益合計(諸経費込)
        double l_dblPutSellIncomeCost = 0; 
        //プット売建玉時価総額
        double l_dblPutSellCurrentPrice = 0;
        
        //コール売建玉総数量
        double l_dblCallSellQuantity = 0;
        //コール売建玉評価損益合計
        double l_dblCallSellIncome = 0; 
        //コール売建玉評価損益合計(諸経費込)
        double l_dblCallSellIncomeCost = 0; 
        //コール売建玉時価総額
        double l_dblCallSellCurrentPrice = 0;

        BigDecimal l_bdBuyIncome = new BigDecimal(l_dblBuyIncome + "");
        BigDecimal l_bdBuyIncomeCost = new BigDecimal(l_dblBuyIncomeCost + "");
        BigDecimal l_bdBuyCurrentPrice = new BigDecimal(l_dblBuyCurrentPrice + "");
        BigDecimal l_bdPutBuyIncome = new BigDecimal(l_dblPutBuyIncome + "");
        BigDecimal l_bdPutBuyIncomeCost = new BigDecimal(l_dblPutBuyIncomeCost + "");
        BigDecimal l_bdPutBuyCurrentPrice = new BigDecimal(l_dblPutBuyCurrentPrice + "");
        BigDecimal l_bdCallBuyIncome = new BigDecimal(l_dblCallBuyIncome + "");
        BigDecimal l_bdCallBuyIncomeCost = new BigDecimal(l_dblCallBuyIncomeCost + "");
        BigDecimal l_bdCallBuyCurrentPrice = new BigDecimal(l_dblCallBuyCurrentPrice + "");
        BigDecimal l_bdSellIncome = new BigDecimal(l_dblSellIncome + "");
        BigDecimal l_bdSellIncomeCost = new BigDecimal(l_dblSellIncomeCost + "");
        BigDecimal l_bdSellCurrentPrice = new BigDecimal(l_dblSellCurrentPrice + "");
        BigDecimal l_bdPutSellIncome = new BigDecimal(l_dblPutSellIncome + "");
        BigDecimal l_bdPutSellIncomeCost = new BigDecimal(l_dblPutSellIncomeCost + "");
        BigDecimal l_bdPutSellCurrentPrice = new BigDecimal(l_dblPutSellCurrentPrice + "");
        BigDecimal l_bdCallSellIncome = new BigDecimal(l_dblCallSellIncome + "");
        BigDecimal l_bdCallSellIncomeCost = new BigDecimal(l_dblCallSellIncomeCost + "");
        BigDecimal l_bdCallSellCurrentPrice = new BigDecimal(l_dblCallSellCurrentPrice + "");

        //1.6.HashMap( )
        HashMap l_hsmUnitSize = new HashMap();
        HashMap l_hsmUnit = new HashMap();
        
        //1.7.get（部店指数別）取扱条件一覧(証券会社コード : String, 部店コード : String, 先物／オプション区分 : String)
        //(部店指数別)取扱条件オブジェクトを取得する  
        //[引数]  
        //証券会社コード：  
        //証券会社 = 補助口座.get証券会社()  
        //証券会社コード = 証券会社.get証券会社コード()  
        //部店コード：　@補助口座．get取扱店()．getBranchCode  
        //先物／オプション区分：　@リクエスト.先物／オプション区分
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexeFutures =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndexList(
        		l_subAccount.getInstitution().getInstitutionCode(),
        		l_branch.getBranchCode(),
        		l_request.fuOpDiv);
        
        if (l_branchIndexeFutures == null || l_branchIndexeFutures.length == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                "(部店指数別)取扱条件オブジェクトを取得できません");
        }

        int l_intBranchIndexeFutures = l_branchIndexeFutures.length;
        
        //1.8.取得した全ての(部店指数別)取扱条件オブジェクトに対しループする
        for (int i = 0; i < l_intBranchIndexeFutures; i++)
        {
        	boolean l_blnRep = false;
        	//1.8.1.(部店指数別)取扱条件オブジェクト.原資産銘柄コードで重複しない場合
        	for (int j = 0; j < i; j++)
        	{
        		String l_strTargetProductCode = l_branchIndexeFutures[i].getUnderlyingProductCode();
        		if (l_strTargetProductCode.equals(l_branchIndexeFutures[j].getUnderlyingProductCode()))
        		{
        			l_blnRep = true;
        		}
        	}
        	
        	if (!l_blnRep)
        	{
        		//1.8.1.1指数別残高合計()
        		WEB3FuturesOptionsBalRefTotalParIndexUnit l_balRefTotalParIndexUnit = 
        			new WEB3FuturesOptionsBalRefTotalParIndexUnit();
        		l_balRefTotalParIndexUnit.targetProductCode = l_branchIndexeFutures[i].getUnderlyingProductCode();
        		//1.8.1.2プロパティセット
        		l_hsmUnit.put(
                    l_branchIndexeFutures[i].getUnderlyingProductCode(), 
    				l_balRefTotalParIndexUnit);
        	}
        }
        
        //1.9.(*)create先物OP残高照会明細()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_intDetailUnitLength; i++)
        {
            //指数乗数リストから指数乗数を取得する。
            Double l_dblUnitSize = null;
            Object l_strUnitSize = l_hsmUnitSize.get(l_detailUnit[i].productCode + l_detailUnit[i].marketCode);
            if (l_strUnitSize == null)
            {
                //取引カレンダコンテキストの銘柄コードを再セットする。
                WEB3GentradeTradingTimeManagement.resetProductCode(l_detailUnit[i].targetProductCode);
                WEB3IfoTradedProductImpl l_tradedProductImpl = null;
                try
                {
                    //取引銘柄を取得する。
                    l_tradedProductImpl = 
                        l_productManagerImpl.getIfoTradedProduct(
                            l_subAccount.getInstitution(),
                            l_detailUnit[i].productCode,
                            l_detailUnit[i].marketCode);
                }
                catch (NotFoundException l_nfex)
                {
                    log.error(STR_METHOD_NAME, l_nfex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        STR_METHOD_NAME);
                }
                IfoTradedProductRow l_tradedProduceRow = 
                    (IfoTradedProductRow)l_tradedProductImpl.getDataSourceObject();
                //指数乗数 = get取引銘柄()の戻り値.1単位当り乗数 とする
                l_dblUnitSize =  new Double(l_tradedProduceRow.getUnitSize());
                
                //指数乗数格納リストに指数乗数を追加する。
                l_hsmUnitSize.put((l_detailUnit[i].productCode + l_detailUnit[i].marketCode),
                    l_dblUnitSize);
            }
            else
            {
                l_dblUnitSize = new Double(l_strUnitSize.toString());
            }
            BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize.doubleValue() + "");
            //処理対象の残高照会明細.時価 == null　@
            //かつ処理対象の残高照会明細.損益 == null　@
            //かつ処理対sy層の残高照会明細.損益(諸経費込) == nullの場合、
            if (l_detailUnit[i].currentPrice == null 
            	&& l_detailUnit[i].income == null
            	&& l_detailUnit[i].incomeCost == null)
            {
                l_detailUnit[i].currentPrice = "0";
                l_detailUnit[i].income = "0";
                l_detailUnit[i].incomeCost = "0";
            }
            
            WEB3FuturesOptionsBalRefTotalParIndexUnit l_totalParIndexUnit = 
            	(WEB3FuturesOptionsBalRefTotalParIndexUnit) l_hsmUnit.get(l_detailUnit[i].targetProductCode); 
            
            //(*)処理対象の残高照会明細.建区分 == "買建"の場合
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_detailUnit[i].contractType))
            {
                //レスポンスデータ.買建玉総数量 += 処理対象の残高照会明細.建数量
                l_dblBuyQuantity += 
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                //レスポンスデータ.買建玉評価損益合計 += 処理対象の残高照会明細.損益
                l_bdBuyIncome = l_bdBuyIncome.add(new BigDecimal(l_detailUnit[i].income));
                //レスポンスデータ.買建玉評価損益合計(諸経費込) 
                //+= 処理対象の残高照会明細.損益(諸経費込)
                l_bdBuyIncomeCost = l_bdBuyIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                //レスポンスデータ.買建玉時価総額 += 
                //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                l_bdBuyCurrentPrice = l_bdBuyCurrentPrice.add(
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                        new BigDecimal(l_detailUnit[i].currentPrice)));
                
                if (l_totalParIndexUnit != null)
                {
	                //指数別残高合計.買建玉総数量 += 処理対象の残高照会明細.建数量
	                l_totalParIndexUnit.buyTotalQuantity = 
	                	WEB3StringTypeUtility.formatNumber(
	            			Double.parseDouble(l_totalParIndexUnit.buyTotalQuantity) 
	                		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                
	                //指数別残高合計.買建玉評価損益合計 += 処理対象の残高照会明細.損益
	                l_totalParIndexUnit.buyAssetProfitLoss =
	                	WEB3StringTypeUtility.formatNumber(
	            			new BigDecimal(l_totalParIndexUnit.buyAssetProfitLoss).add(
	                		    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                
	                //指数別残高合計.買建玉評価損益合計(諸経費込) 
	                //+= 処理対象の残高照会明細.損益(諸経費込)
	                l_totalParIndexUnit.buyAssetProfitLossCost =
	                	WEB3StringTypeUtility.formatNumber(
	            			new BigDecimal(l_totalParIndexUnit.buyAssetProfitLossCost).add(
	                		    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                //指数別残高合計.買建玉時価総額 += 
	                //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                    l_totalParIndexUnit.buyCurrentPrice =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.buyCurrentPrice).add(
                                new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                    new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                }

            
                //(*)処理対象の残高照会明細.オプション商品区分 = "プット"の場合
                if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //レスポンスデータ.プット買建玉総数量 += 処理対象の残高照会明細.建数量
                    l_dblPutBuyQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //レスポンスデータ.プット買建玉評価損益合計 += 処理対象の残高照会明細.損益
                    l_bdPutBuyIncome = l_bdPutBuyIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //レスポンスデータ.プット買建玉評価損益合計(諸経費込) 
                    //+= 処理対象の残高照会明細.損益(諸経費込)
                    l_bdPutBuyIncomeCost = l_bdPutBuyIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //レスポンスデータ.プット買建玉時価総額 += 
                    //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                    l_bdPutBuyCurrentPrice = l_bdPutBuyCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //指数別残高合計.プット買建玉総数量 += 処理対象の残高照会明細.建数量
	                    l_totalParIndexUnit.putBuyTotalQuantity =
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.putBuyTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                        
	                    //指数別残高合計.プット買建玉評価損益合計 += 処理対象の残高照会明細.損益
                        l_totalParIndexUnit.putBuyAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putBuyAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //指数別残高合計.プット買建玉評価損益合計(諸経費込) 
	                    //+= 処理対象の残高照会明細.損益(諸経費込)
                        l_totalParIndexUnit.putBuyAssetProfitLossCost =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putBuyAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //指数別残高合計.プット買建玉時価総額 
	                    //+= 処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                        l_totalParIndexUnit.putBuyCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putBuyCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                    
                }
                //(*)処理対象の残高照会明細.オプション商品区分 = "コール"の場合 
                else if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //レスポンスデータ.コール買建玉総数量 += 処理対象の残高照会明細.建数量
                    l_dblCallBuyQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //レスポンスデータ.コール買建玉評価損益合計 += 処理対象の残高照会明細.損益
                    l_bdCallBuyIncome = l_bdCallBuyIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //レスポンスデータ.コール買建玉評価損益合計(諸経費込) 
                    //+= 処理対象の残高照会明細.損益(諸経費込)
                    l_bdCallBuyIncomeCost = l_bdCallBuyIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //レスポンスデータ.コール買建玉時価総額
                    //+= 処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                    l_bdCallBuyCurrentPrice = l_bdCallBuyCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //指数別残高合計.コール買建玉総数量 += 処理対象の残高照会明細.建数量
	                    l_totalParIndexUnit.callBuyTotalQuantity = 
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.callBuyTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                        
	                    //指数別残高合計.コール買建玉評価損益合計 += 処理対象の残高照会明細.損益
                        l_totalParIndexUnit.callBuyAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callBuyAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //指数別残高合計.コール買建玉評価損益合計(諸経費込) 
	                    //+= 処理対象の残高照会明細.損益(諸経費込)
                        l_totalParIndexUnit.callBuyAssetProfitLossCost =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callBuyAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //指数別残高合計.コール買建玉時価総額 
	                    //+= 処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価   
                        l_totalParIndexUnit.callBuyCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callBuyCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                } 
            }
            //(*)処理対象の残高照会明細.建区分 == "売建"の場合
            if (WEB3IfoContractTypeDef.OPEN_SELL.equals(l_detailUnit[i].contractType))
            {
                //レスポンスデータ.売建玉総数量 += 処理対象の残高照会明細.建数量
                l_dblSellQuantity += 
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                //レスポンスデータ.売建玉評価損益合計 += 処理対象の残高照会明細.損益
                l_bdSellIncome = l_bdSellIncome.add(new BigDecimal(l_detailUnit[i].income));
                //レスポンスデータ.売建玉評価損益合計(諸経費込) += 
                //処理対象の残高照会明細.損益(諸経費込)
                l_bdSellIncomeCost = l_bdSellIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                //レスポンスデータ.売建玉時価総額 += 
                //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                l_bdSellCurrentPrice = l_bdSellCurrentPrice.add(
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                        new BigDecimal(l_detailUnit[i].currentPrice)));

                if (l_totalParIndexUnit != null)
                {
	                //指数別残高合計.売建玉総数量 += 処理対象の残高照会明細.建数量
	                l_totalParIndexUnit.sellTotalQuantity = 
	                	WEB3StringTypeUtility.formatNumber(
	            			Double.parseDouble(l_totalParIndexUnit.sellTotalQuantity) 
	                		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                    
	                //指数別残高合計.売建玉評価損益合計 += 処理対象の残高照会明細.損益
                    l_totalParIndexUnit.sellAssetProfitLoss =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.sellAssetProfitLoss).add(
                                new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                    
	                //指数別残高合計.売建玉評価損益合計(諸経費込) += 
	                //処理対象の残高照会明細.損益(諸経費込)
                    l_totalParIndexUnit.sellAssetProfitLossCost =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.sellAssetProfitLossCost).add(
                                new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                //指数別残高合計.売建玉時価総額 += 
	                //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価            
                    l_totalParIndexUnit.sellCurrentPrice =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.sellCurrentPrice).add(
                                new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                    new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                }
            
                //(*)処理対象の残高照会明細.オプション商品区分 = "プット"の場合
                if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //レスポンスデータ.プット売建玉総数量 += 
                    //処理対象の残高照会明細.建数量
                    l_dblPutSellQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //レスポンスデータ.プット売建玉評価損益合計 += 
                    //処理対象の残高照会明細.損益
                    l_bdPutSellIncome = l_bdPutSellIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //レスポンスデータ.プット売建玉評価損益合計(諸経費込) += 
                    //処理対象の残高照会明細.損益(諸経費込)
                    l_bdPutSellIncomeCost = l_bdPutSellIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //レスポンスデータ.プット売建玉時価総額 += 
                    //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                    l_bdPutSellCurrentPrice = l_bdPutSellCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //指数別残高合計.プット売建玉総数量 += 
	                    //処理対象の残高照会明細.建数量
	                    l_totalParIndexUnit.putSellTotalQuantity = 
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.putSellTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                        
	                    //指数別残高合計.プット売建玉評価損益合計 += 
	                    //処理対象の残高照会明細.損益
                        l_totalParIndexUnit.putSellAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putSellAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //指数別残高合計.プット売建玉評価損益合計(諸経費込) += 
	                    //処理対象の残高照会明細.損益(諸経費込)
                        l_totalParIndexUnit.putSellAssetProfitLossCost =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putSellAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //指数別残高合計.プット売建玉時価総額 += 
	                    //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価                       
                        l_totalParIndexUnit.putSellCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putSellCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                }
                //(*)処理対象の残高照会明細.オプション商品区分 = "コール"の場合 
                else if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //レスポンスデータ.コール売建玉総数量 += 処理対象の残高照会明細.建数量
                    l_dblCallSellQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //レスポンスデータ.コール売建玉評価損益合計 += 処理対象の残高照会明細.損益
                    l_bdCallSellIncome = l_bdCallSellIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //レスポンスデータ.コール売建玉評価損益合計(諸経費込) += 
                    //処理対象の残高照会明細.損益(諸経費込)
                    l_bdCallSellIncomeCost = l_bdCallSellIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //レスポンスデータ.コール売建玉時価総額 += 
                    //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                    l_bdCallSellCurrentPrice = l_bdCallSellCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //指数別残高合計.コール売建玉総数量 += 処理対象の残高照会明細.建数量
	                    l_totalParIndexUnit.callSellTotalQuantity = 
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.callSellTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue()); 
	                       
	                    //指数別残高合計.コール売建玉評価損益合計 += 処理対象の残高照会明細.損益
                        l_totalParIndexUnit.callSellAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callSellAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //指数別残高合計.コール売建玉評価損益合計(諸経費込) += 
	                    //処理対象の残高照会明細.損益(諸経費込)
                        l_totalParIndexUnit.callSellAssetProfitLossCost = 
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callSellAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //指数別残高合計.コール売建玉時価総額 += 
	                    //処理対象の残高照会明細.建数量 *取得した指数乗数 *処理対象の残高照会明細.時価
                        l_totalParIndexUnit.callSellCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callSellCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                }               
            }       
            if (l_totalParIndexUnit != null)
            {
	            l_hsmUnit.put(l_totalParIndexUnit.targetProductCode, l_totalParIndexUnit);    
            }
        }

        l_response.buyCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyCurrentPrice.doubleValue());
        l_response.buyAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncome.doubleValue());
        l_response.buyAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncomeCost.doubleValue());
        l_response.buyTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyQuantity);
        
        l_response.putBuyCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdPutBuyCurrentPrice.doubleValue());
        l_response.putBuyAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdPutBuyIncome.doubleValue());
        l_response.putBuyAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdPutBuyIncomeCost.doubleValue());
        l_response.putBuyTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblPutBuyQuantity);
        
        l_response.callBuyCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCallBuyCurrentPrice.doubleValue());
        l_response.callBuyAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdCallBuyIncome.doubleValue());
        l_response.callBuyAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdCallBuyIncomeCost.doubleValue());
        l_response.callBuyTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblCallBuyQuantity);
        
        l_response.sellCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdSellCurrentPrice.doubleValue());
        l_response.sellAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdSellIncome.doubleValue());
        l_response.sellAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdSellIncomeCost.doubleValue());
        l_response.sellTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblSellQuantity);
        
        l_response.putSellCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdPutSellCurrentPrice.doubleValue());
        l_response.putSellAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdPutSellIncome.doubleValue());
        l_response.putSellAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdPutSellIncomeCost.doubleValue());
        l_response.putSellTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblPutSellQuantity);
        
        l_response.callSellCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCallSellCurrentPrice.doubleValue());
        l_response.callSellAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdCallSellIncome.doubleValue());
        l_response.callSellAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdCallSellIncomeCost.doubleValue());
        l_response.callSellTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblCallSellQuantity);
        
        //レスポンスデータ.建玉総数量 = レスポンスデータ.買建玉総数量
        //+ レスポンスデータ.売建玉総数量
        l_response.totalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyQuantity + l_dblSellQuantity);
        //レスポンスデータ.評価損益合計 = レスポンスデータ.買建玉評価損益合計
        //+ レスポンスデータ.売建玉評価損益合計
        l_response.appraisalProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncome.add(l_bdSellIncome).doubleValue());
        //レスポンスデータ.評価損益合計(諸経費込) = レスポンスデータ.買建玉評価損益合計(諸経費込)
        //+ レスポンスデータ.売建玉評価損益合計(諸経費込)
        l_response.appraisalProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncomeCost.add(l_bdSellIncomeCost).doubleValue());
        
        Collection l_clnUnit = l_hsmUnit.values();
        WEB3FuturesOptionsBalRefTotalParIndexUnit[] l_totalParIndexUnits = null;
        int l_intUnitSize = 0;
        if (l_clnUnit != null && !l_clnUnit.isEmpty())
        {
        	l_intUnitSize = l_clnUnit.size();
        	l_totalParIndexUnits =
        		new WEB3FuturesOptionsBalRefTotalParIndexUnit[l_intUnitSize];
        	l_clnUnit.toArray(l_totalParIndexUnits);
        }
        //指数別残高合計.買建玉総数量
        double l_dblUnitBuyTotalQuantity = 0;
        //指数別残高合計.売建玉総数量     
        double l_dblUnitSellTotalQuantity = 0;
        //指数別残高合計.建玉総数量
        double l_dblUnitTotalQuantity = 0;
        //指数別残高合計.評価損益合計
        double l_dblUnitAppraisalProfitLoss = 0;     
        //指数別残高合計.評価損益合計(諸経費込)
        double l_dblUnitAppraisalProfitLossCost = 0;
        for (int k = 0; k < l_intUnitSize; k++)
        {
            //指数別残高合計.建玉総数量 = 指数別残高合計.買建玉総数量
            //+ 指数別残高合計.売建玉総数量
        	l_dblUnitBuyTotalQuantity = new BigDecimal(l_totalParIndexUnits[k].buyTotalQuantity).doubleValue();
        	l_dblUnitSellTotalQuantity = new BigDecimal(l_totalParIndexUnits[k].sellTotalQuantity).doubleValue();
        	l_dblUnitTotalQuantity = l_dblUnitBuyTotalQuantity + l_dblUnitSellTotalQuantity;
        	l_totalParIndexUnits[k].totalQuantity = WEB3StringTypeUtility.formatNumber(l_dblUnitTotalQuantity);
        	
            //指数別残高合計.評価損益合計 = 指数別残高合計.買建玉評価損益合計
            //+ 指数別残高合計.売建玉評価損益合計
            BigDecimal l_bdUnitBuyAssetProfitLoss = new BigDecimal(l_totalParIndexUnits[k].buyAssetProfitLoss);
            BigDecimal l_bdUnitSellAssetProfitLoss = new BigDecimal(l_totalParIndexUnits[k].sellAssetProfitLoss);
            l_dblUnitAppraisalProfitLoss = l_bdUnitBuyAssetProfitLoss.add(l_bdUnitSellAssetProfitLoss).doubleValue();
            l_totalParIndexUnits[k].appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblUnitAppraisalProfitLoss);
            
            //指数別残高合計.評価損益合計(諸経費込) = 指数別残高合計.買建玉評価損益合計(諸経費込)
            //+ 指数別残高合計.売建玉評価損益合計(諸経費込)
            BigDecimal l_bdUnitBuyAssetProfitLossCost = new BigDecimal(l_totalParIndexUnits[k].buyAssetProfitLossCost);
            BigDecimal l_bdUnitSellAssetProfitLossCost = new BigDecimal(l_totalParIndexUnits[k].sellAssetProfitLossCost);
            l_dblUnitAppraisalProfitLossCost =
                l_bdUnitBuyAssetProfitLossCost.add(l_bdUnitSellAssetProfitLossCost).doubleValue();
            l_totalParIndexUnits[k].appraisalProfitLossCost = 
            	WEB3StringTypeUtility.formatNumber(l_dblUnitAppraisalProfitLossCost);
        }
        l_response.futuresOptionsBalRefTotalParIndexUnits = l_totalParIndexUnits;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get補助口座)<BR>
     * 引数の先物／オプション区分に該当する補助口座を取得する。<BR>
     * <BR>
     * １）　@パラメータ.先物／オプション区分により、補助口座を取得する。<BR>
     * 　@[パラメータ.先物／オプション区分 == "先物"の場合]<BR>
     * 　@　@this.get補助口座()メソッドをコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@[get補助口座()にセットするパラメータ]<BR>
     * 　@　@　@補助口座タイプ：　@SubAccountTypeEnum.証拠金口座<BR>
     * <BR>
     * 　@[パラメータ.先物／オプション区分 == "オプション"の場合]<BR>
     * 　@　@①@　@ログインセキュリティサービスより口座ＩＤを取得する。<BR>
     * 　@　@②　@口座IDに該当する顧客オブジェクトを取得する。<BR>
     * 　@　@③　@顧客オブジェクト.getOP取引口座タイプ()メソッドをコールする。<BR>
     * 　@　@④　@this.get補助口座()メソッドをコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@　@[get補助口座()にセットするパラメータ]<BR>
     * 　@　@　@　@補助口座タイプ：　@③の戻り値<BR>
     * @@param l_strFuOpDiv - 先物／オプション区分<BR>
     * <BR>
     * 1：　@先物<BR>
     * 2：　@オプション<BR>
     * @@return WEB3GentradeSubAccount
     * @@roseuid 41AAD19402C4
     */
    protected WEB3GentradeSubAccount getSubAccount(String l_strFuOpDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //１）ログインセキュリティサービスより口座ＩＤを取得する。
        if (l_strFuOpDiv == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //１）ログインセキュリティサービスより口座ＩＤを取得する。        
        long l_lngAccountId = l_opLoginSec.getAccountId();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        SubAccount  l_subAccount = null;
        try
        {
            //②　@口座IDに該当する顧客オブジェクトを取得する。
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);

            //４）アカウントマネージャ.getSubAccount(補助口座タイプ)にて、該当顧客の指数オプション取引用補助口座オブジェクトを取得する。
            //[パラメータ.先物／オプション区分 == "オプション"の場合]
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFuOpDiv))
            {
                //３）顧客.getOP取引口座タイプ()により該当顧客の補助口座タイプを取得する。
                SubAccountTypeEnum l_subAccountType = l_mainAccount.getOpSubAccountType();
                l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
            }
            //[パラメータ.先物／オプション区分 == "先物"の場合]
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuOpDiv))
            {
                if (!l_mainAccount.isIfoAccountOpen(l_strFuOpDiv))
                {
                    log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00284);
                    //例外をスローする
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00284, this.getClass().getName() + STR_METHOD_NAME);
                }
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
           
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return (WEB3GentradeSubAccount)l_subAccount;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * リクエストデータをもとに、検索条件（<BR>
     * where以下指定の文字列）を作成する。<BR>
     * <BR>
     * １）パラメータ.先物OP銘柄 == nullの場合、<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * ２）１）以外の場合、以下の処理を行う。<BR>
     * 　@１－１）検索条件文字列インスタンス(：String)を生成<BR>
     * 　@１－２）銘柄IDを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and product_id = ? "<BR>
     * <BR>
     * 　@１－３）作成した検索条件文字列を返却する。<BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト
     * @@return String
     * @@roseuid 41AAE01002D4
     */
    protected String createQueryString(WEB3IfoProductImpl l_ifoProduct) 
    {
        final String STR_METHOD_NAME = "createQueryString(String l_strProductCode) ";
        log.entering(STR_METHOD_NAME);
 
        //パラメータ.銘柄コード＝NULLの場合、NULLを返却する
        if (l_ifoProduct == null)
        {
            return null;
        }
 
        String l_strQueryString = " and product_id = ? ";
 
        log.exiting(STR_METHOD_NAME);
 
        return l_strQueryString;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * リクエストデータから、検索条件（<BR>
     * where以下指定の文字列）の"?"にセットする、<BR>
     * パラメータリストを作成する。<BR>
     * <BR>
     * １）パラメータ.先物OP銘柄 == nullの場合、nullを返却する。<BR>
     * <BR>
     * ２）１）以外の場合、以下の処理を実施する。<BR>
     * 　@２－１）銘柄ID(*1)を0番目の要素とする文字列配列を生成する。<BR>
     * 　@２－２）生成した文字列配列を返却する。<BR>
     * <BR>
     * (*1)パラメータ.先物OP銘柄.get銘柄ID()の戻り値<BR>
     * <BR>
     * @@param l_ifoProduct - 先物OP銘柄オブジェクト
     * @@return String[]
     * @@roseuid 41AAE01002F3
     */
     protected String[] createQueryContainer(WEB3IfoProductImpl l_ifoProduct)
     {
         final String STR_METHOD_NAME = "createQueryContainer(WEB3IfoProductImpl)";
         log.entering(STR_METHOD_NAME);
  
         //パラメータ.先物OP銘柄＝NULLの場合、NULLを返却する
         if (l_ifoProduct == null)
         {
             return null;
         }
         //２－１）銘柄IDを0番目の要素とする文字列配列を生成する。
         String[] l_strQueryContainer = {Long.toString(l_ifoProduct.getProductId())};

         
         log.exiting(STR_METHOD_NAME);
         //２－２）生成した文字列配列を返却する。
         return l_strQueryContainer;
     }
    
    /**
     * (sort残高照会明細)<BR>
     * 指定されたソートキー、昇降順に基づいて明細のソートを行う。<BR>
     * <BR>
     * １）パラメータ.残高照会明細 == nullの場合、<BR>
     * 　@処理を終了する。<BR>
     * <BR>
     * ２）ArrayListを生成する。 <BR>
     * <BR>
     * ３）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@３－１）ソートキー.キー項目の値に対応する比較項目の<BR>
     *       Comparatorを生成し、ArrayListに追加する。 <BR>
     * <BR>
     * 　@　@　@①@先物OP残高照会Comparatorを生成する。<BR>
     * <BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ] <BR>
     * 　@　@　@　@　@orderBy： ソートキー.昇順／降順<BR>
     * 　@　@　@　@　@比較項目：　@ソートキー.キー項目(*1)<BR>
     * <BR>
     * 　@　@　@②ArrayListに生成したComparatorを追加する。<BR>
     * <BR>
     * ４）WEB3ArraysUtility.sort()メソッドをコールする。 <BR>
     * <BR>
     * 　@[sort()にセットするパラメータ] <BR>
     * 　@　@obj：　@パラメータ.残高照会明細<BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値<BR>
     * <BR>
     * <BR>
     * (*1)キー項目が建日の場合、<BR>
     * 　@　@建日Comparator、立会区分Comparatorを生成する。<BR>
     * 　@　@立会区分Comparator生成時のorderByには建日Comparatorで<BR>
     * 　@　@設定された値を設定すること。<BR>
     * @@param l_balanceReferenceDetailUnit - 先物OP残高照会明細の配列
     * @@param l_sortKeys - 株価指数オプションソートキーの配列
     * @@roseuid 41AAE0100303
     */
     protected void sortBalanceReferenceDetailUnit(WEB3FuturesOptionsDetailUnit[] l_balanceReferenceDetailUnit, WEB3FuturesOptionsSortKey[] l_sortKeys) 
     {
         final String STR_METHOD_NAME = " sortBalanceReferenceDetailUnit(WEB3FuturesOptionsDetailUnit[] l_balanceReferenceDetailUnit, WEB3FuturesOptionsSortKey[] l_sortKeys)";
         log.entering(STR_METHOD_NAME);
  
         List l_lstComparators = new ArrayList();
         int l_intSortKeys = 0;
         if (l_sortKeys != null)
         {
             l_intSortKeys = l_sortKeys.length;
         }
  
         for (int i = 0; i < l_intSortKeys; i++)
         {
             String l_strKeyItem = l_sortKeys[i].keyItem;
             String l_strAscDesc = l_sortKeys[i].ascDesc;
  
             if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
             {
                 Comparator l_opOpenDateCom = new WEB3OptionsOpenDateComparator(l_strAscDesc);
                 Comparator l_opSessionTypeCom = new WEB3OptionsSessionTypeComparator(l_strAscDesc);
                 l_lstComparators.add(l_opOpenDateCom);
                 l_lstComparators.add(l_opSessionTypeCom);
             }

             Comparator l_com = new WEB3FuturesOptionsBalanceReferenceComparator(l_strAscDesc,l_strKeyItem);
             l_lstComparators.add(l_com);
         }
  
         Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
         l_lstComparators.toArray(l_comparators);
  
         WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit, l_comparators);
         log.exiting(STR_METHOD_NAME);
     }
}
@
