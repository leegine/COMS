head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 簿価単価明細照会サービスImpl(WEB3BVSBookValueSpecsServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/08  賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradehistory.WEB3HistoryTradeHistoryDataManager;
import webbroker3.tradehistory.data.BookValueSpecParams;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsRequest;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsResponse;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsUnit;
import webbroker3.tradehistory.service.delegate.WEB3BVSBookValueSpecsService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.common.define.WEB3BookValueRecordDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (簿価単価明細照会サービスImpl)<BR>
 * 簿価単価明細照会サービス実装クラス<BR>
 * 
 * @@author 賈元春
 * @@version 1.0 
 */
public class WEB3BVSBookValueSpecsServiceImpl extends WEB3ClientRequestService implements WEB3BVSBookValueSpecsService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3BVSBookValueSpecsServiceImpl.class);      
    
    /**
     * @@roseuid 418877BB0196
     */
    public WEB3BVSBookValueSpecsServiceImpl() 
    {
     
    }
    
    /**
     * 簿価単価明細照会処理を行う。<BR>
     * <BR>
     * this.get簿価単価明細照会()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 416E4F390312
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null; 
        if(l_request instanceof WEB3BVSBookValueSpecsRequest)
        {
            
            l_response = this.getBookValueSpecs((WEB3BVSBookValueSpecsRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, this.getClass().getName() + STR_METHOD_NAME);
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get簿価単価明細照会)<BR>
     * 簿価単価明細照会処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(簿価単価明細照会サービス)get簿価単価明細照会」<BR>
     * ======================================================= <BR>
     *         シーケンス図 :  「(簿価単価明細照会サービス)get簿価単価明細照会」<BR>
     *         具体位置 : 1.5 get簿価単価明細件数(顧客 : 顧客, 商品コード : String, 銘柄コード : String) <BR>
     *         get簿価単価明細件数()メソッドの戻り値 == 0の場合、<BR>
     *         特定口座取引未存在エラー。<BR>
     *         class         :  WEB3BusinessLayerException<BR>
     *         tag            :  BUSINESS_ERROR_01081         <BR>
     * ======================================================= <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 簿価単価明細照会リクエストオブジェクト<BR>
     * @@return webbroker3.plsbvs.message.WEB3BVSBookValueSpecsResponse
     * @@roseuid 416E4F4E0063
     */
    protected WEB3BVSBookValueSpecsResponse getBookValueSpecs(WEB3BVSBookValueSpecsRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBookValueSpecs(WEB3BVSBookValueSpecsRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3 get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                
        //1.4 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //
        WEB3BVSBookValueSpecsResponse l_response = 
            (WEB3BVSBookValueSpecsResponse)l_request.createResponse();   
        //1.5 get簿価単価明細件数(顧客, String, String)
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        int l_intBookValueSpecCount = l_historyTradeHistoryDataManager.getBookValueSpecCount(l_mainAccount, l_request.commodityCode, l_request.productCode);
        if (l_intBookValueSpecCount == 0)
        {
            l_response.productCode = l_request.productCode;
            l_response.productName = l_request.productName;
            l_response.currentProlossAmount = "0";
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";
            l_response.bookValueUnits = null;
            return l_response;
        }
        
        //1.6 取得データ格納用ArrayListを生成する。
        List l_lisBookValueSpecs = new ArrayList();
        
        //1.7 create残高データ(顧客, String, String, String)
        WEB3BVSBookValueSpecsUnit l_web3BVSBookValueSpecsUnit = this.createBalanceData(l_mainAccount, l_request.commodityCode, l_request.productCode, l_request.displayTerm);
        
        //1.8 create残高データ()の戻り値 != nullの場合  
        double l_dblSumBalanceProLoss = 0.0D;   
        if (l_web3BVSBookValueSpecsUnit != null)    
        {   
            // add(残高データ : Object)
            l_lisBookValueSpecs.add(l_web3BVSBookValueSpecsUnit);
            l_dblSumBalanceProLoss = Double.parseDouble(l_web3BVSBookValueSpecsUnit.prolossAmount);  
        }   
    
        //1.10 get簿価単価明細一覧(顧客, String, String, String, boolean)
        List l_lisBookValueSpecUnits = 
            l_historyTradeHistoryDataManager.getBookValueSpecList(
                l_mainAccount, 
                l_request.commodityCode, 
                l_request.productCode, 
                l_request.displayTerm, 
                false);       
        double l_dblsumproloss = 0;
        int l_intSize = 0;
        if (l_lisBookValueSpecUnits != null)
        {
            l_intSize = l_lisBookValueSpecUnits.size();
        }
        
        //1.11 (*)get簿価単価明細一覧()の戻り値の要素(=損益明細Params)数分Loop処理
        for (int i = 0; i < l_intSize; i++)
        {
            //1.11.1 簿価単価明細情報()
            WEB3BVSBookValueSpecsUnit l_bookValueSpecsUnit = new WEB3BVSBookValueSpecsUnit();
            BookValueSpecParams l_bookValueSpecParams = (BookValueSpecParams)l_lisBookValueSpecUnits.get(i);
            if (l_bookValueSpecParams.getBookValueSpecIdIsSet())
            {
                l_bookValueSpecsUnit.bookValueSpecId = "" + l_bookValueSpecParams.getBookValueSpecId();
            }
            else
            {
                l_bookValueSpecsUnit.bookValueSpecId = null;
            }
            l_bookValueSpecsUnit.bookvalRecDiv = l_bookValueSpecParams.getRecDiv();
            l_bookValueSpecsUnit.calcDate = l_bookValueSpecParams.getCalcDate();
            l_bookValueSpecsUnit.deliveryDate = l_bookValueSpecParams.getDeliveryDate();
            l_bookValueSpecsUnit.buySellDiv = l_bookValueSpecParams.getBuySellDiv();
            l_bookValueSpecsUnit.tradeType = l_bookValueSpecParams.getTradeType();
            if (l_bookValueSpecParams.getQuantityIsNull())
            {
                l_bookValueSpecsUnit.quantity = null;
            }
            else
            {
                l_bookValueSpecsUnit.quantity = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getQuantity());
            }
            
            if (l_bookValueSpecParams.getPriceIsNull())
            {
                l_bookValueSpecsUnit.execPrice = null;
            }
            else
            {
                l_bookValueSpecsUnit.execPrice = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getPrice());
            }            
            l_bookValueSpecsUnit.ccyCode = l_bookValueSpecParams.getCcyCode();
            if (l_bookValueSpecParams.getNetAmountIsNull())
            {
                l_bookValueSpecsUnit.deliveryAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.deliveryAmount = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getNetAmount());
            }
            
            double l_dblproloss = l_bookValueSpecParams.getProloss1();
            String l_strtrade_type = l_bookValueSpecParams.getTradeType();
            String l_strbuy_sell = l_bookValueSpecParams.getBuySellDiv();
            if (!((l_dblproloss <= 0) && (
                (l_strbuy_sell.equals("2") && (l_strtrade_type.equals("11") || l_strtrade_type.equals("83") || l_strtrade_type.equals("92"))) ||
                (l_strbuy_sell.equals("2") && l_strtrade_type.equals("25")) ||
                (l_strbuy_sell.equals("2") && l_strtrade_type.equals("35")) ||
                (l_strbuy_sell.equals("2") && l_strtrade_type.equals("53")))))
            {
                if (l_bookValueSpecParams.getProloss1IsNull())
                {
                    l_bookValueSpecsUnit.prolossAmount = null;
                }
                else
                {
                    l_bookValueSpecsUnit.prolossAmount = WEB3StringTypeUtility.formatNumber(l_dblproloss);
                }
            }
            
            if (l_bookValueSpecParams.getCalcAmountIsNull())
            {
                l_bookValueSpecsUnit.calcAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.calcAmount = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getCalcAmount());
            }
            
            if (l_bookValueSpecParams.getBookAmountIsNull())
            {
                l_bookValueSpecsUnit.bookAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookAmount = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getBookAmount());
            }
            
            if (l_bookValueSpecParams.getBalQuantityIsNull())
            {
                
            }
            else
            {
                l_bookValueSpecsUnit.balQuantity = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getBalQuantity());
            }
            
            if (l_bookValueSpecParams.getBookValueIsNull())
            {
                l_bookValueSpecsUnit.bookPrice = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookPrice = WEB3StringTypeUtility.formatNumber(l_bookValueSpecParams.getBookValue());
            }
            
            l_bookValueSpecsUnit.debitBalDiv = l_bookValueSpecParams.getDebitBalDiv();
            //1.11.3 add(明細データ : Object)
            l_lisBookValueSpecs.add(l_bookValueSpecsUnit);
            //1.11.4 (*)明細データの損益の集計
            if (l_lisBookValueSpecs == null)
            {
                l_dblsumproloss = 0;
            }
            else
            {
                l_dblsumproloss += l_bookValueSpecParams.getProloss1();
            }            
        }

        //1.12 toArray()
        WEB3BVSBookValueSpecsUnit[] l_web3BVSBookValueSpecsUnits = new WEB3BVSBookValueSpecsUnit[l_lisBookValueSpecs.size()];
        l_lisBookValueSpecs.toArray(l_web3BVSBookValueSpecsUnits); 

        //1.13 createResponse       
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_web3BVSBookValueSpecsUnits, l_intPageIndex, l_intPageSize);
        WEB3BVSBookValueSpecsUnit[] l_returnSpecUnit = (WEB3BVSBookValueSpecsUnit[])l_pageIndexInfo.getArrayReturned(WEB3BVSBookValueSpecsUnit.class);

        //1.14 (*)プロパティセット   
        l_response.productCode = l_request.productCode;
        l_response.productName = l_request.productName;
        l_response.currentProlossAmount = 
            WEB3StringTypeUtility.formatNumber(l_dblSumBalanceProLoss + l_dblsumproloss);       
        l_response.totalPages  = l_pageIndexInfo.getTotalPages() + "";
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";        
        l_response.bookValueUnits = l_returnSpecUnit;        

        log.exiting(STR_METHOD_NAME);  
        return l_response;
    }
    
    /**
     * (create残高データ)<BR>
     * 残高データを格納した損益明細情報インスタンスを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(簿価単価明細照会サービス)create残高データ」参照<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10:　@株式<BR>
     * 11:　@信用<BR>
     * 15:　@ミニ株<BR>
     * 20:　@投信<BR>
     * 21:　@外投<BR>
     * 22:　@累投<BR>
     * 23:　@MRF<BR>
     * 30:　@債券<BR>
     * 40:　@外株<BR>
     * 50:　@株先<BR>
     * 51： 株指数OP<BR>
     * 52:　@債先<BR>
     * 53:　@債先OP<BR>
     * 54:　@店OP<BR>
     * 55:　@外先<BR>
     * 56:　@外先OP<BR>
     * 57:　@株OP<BR>
     * 60:　@外債<BR>
     * 70:　@金<BR>
     * 71:　@金GP<BR>
     * 80:　@特殊<BR>
     * 91:　@CD<BR>
     * 92:　@CP<BR>
     * 93:　@BA<BR>
     * 99:　@金銭<BR>
     * <BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strDisplayTerm - (表示期間)<BR>
     * 表示期間<BR>
     * <BR>
     * 0：　@前月月初以降(DEFAULT)<BR>
     * 1：　@1ヶ月分<BR>
     * 2：　@1週間分<BR>
     * 3：　@前日1日分<BR>
     * @@return webbroker3.plsbvs.message.WEB3BVSBookValueSpecsUnit
     * @@roseuid 416E5EE803CE
     */
    protected WEB3BVSBookValueSpecsUnit createBalanceData(WEB3GentradeMainAccount l_mainAccount, String l_strCommodityCode, String l_strProductCode, String l_strDisplayTerm) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createBalanceData(WEB3GentradeMainAccount, String, String, String) ";
        log.entering(STR_METHOD_NAME);         
        
        //1.1 簿価単価明細情報()
        WEB3BVSBookValueSpecsUnit l_bookValueSpecsUnit = new WEB3BVSBookValueSpecsUnit();

        //1.2 get最終計算年月日from簿価単価明細(顧客, String, String, String)
        WEB3HistoryTradeHistoryDataManager l_historyTradeHistoryDataManager = new WEB3HistoryTradeHistoryDataManager();
        Date l_datFinalCalcDatefromBookValueSpec = 
            l_historyTradeHistoryDataManager.getFinalCalcDateFromBookValueSpec(
                l_mainAccount, 
                l_strCommodityCode, 
                l_strProductCode, 
                l_strDisplayTerm);

        //1.3 get残高レコードfrom簿価単価明細(顧客, String, String)
        List l_lisBalanceRecordfromBookValueSpec = 
            l_historyTradeHistoryDataManager.getBalanceRecordFromBookValueSpec(
                l_mainAccount, 
                l_strCommodityCode, 
                l_strProductCode);

        //1.4 get最終計算年月日from簿価単価明細()の戻り値 == nullの場合
        if (l_datFinalCalcDatefromBookValueSpec == null)
        {
            if (l_lisBalanceRecordfromBookValueSpec != null)
            {
                //1.4.1 (*)プロパティセット
                if (((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBalQuantityIsNull())
                {
                    l_bookValueSpecsUnit.balQuantity = null;
                }
                else
                {
                    l_bookValueSpecsUnit.balQuantity = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBalQuantity());
                }
                
                if (((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookValueIsNull())
                {
                    l_bookValueSpecsUnit.bookPrice = null;
                }
                else
                {
                    l_bookValueSpecsUnit.bookPrice = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookValue());
                }
                
                if (((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookAmountIsNull())
                {
                    l_bookValueSpecsUnit.bookAmount = null;
                }
                else
                {
                    l_bookValueSpecsUnit.bookAmount = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookAmount());
                }                
            }
            else    
            {   
                l_bookValueSpecsUnit.balQuantity = "0"; 
                l_bookValueSpecsUnit.bookPrice = "0";   
                l_bookValueSpecsUnit.bookAmount = "0";
            }
        }
        else
        {
            //1.5.1 (*)検索条件文字列＆パラメータセット＆ソート条件の作成
            StringBuffer l_sbQueryString = new StringBuffer(); 
            l_sbQueryString.append("and rec_div = ? ");          
            l_sbQueryString.append("and calc_date = ?");               

            List l_lisArrayList = new ArrayList();
            l_lisArrayList.add(WEB3BookValueRecordDivDef.DETAIL_REC);
            l_lisArrayList.add(l_datFinalCalcDatefromBookValueSpec);

            Object[] l_objArray = new Object[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_objArray);
            
            String l_strSortCond = " sort_no desc";

            //1.5.2 get簿価単価明細一覧(顧客, String, String, String, Object[], String)
            List l_lisgetBookValueSpecUnits = 
                l_historyTradeHistoryDataManager.getBookValueSpecList(
                    l_mainAccount, 
                    l_strCommodityCode, 
                    l_strProductCode, 
                    l_sbQueryString.toString(), 
                    l_objArray, 
                    l_strSortCond);

            if(l_lisgetBookValueSpecUnits == null 
                || l_lisgetBookValueSpecUnits.size() == 0)
            {
                log.debug("テーブルに該当するデータがありません。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.5.3 (*)プロパティセット
            if (((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBalQuantityIsNull())
            {
                l_bookValueSpecsUnit.balQuantity = null;
            }
            else
            {
                l_bookValueSpecsUnit.balQuantity = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBalQuantity());
            }
            
            if (((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookValueIsNull())
            {
                l_bookValueSpecsUnit.bookPrice = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookPrice = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookValue());
            }
            
            if (((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookAmountIsNull())
            {
                l_bookValueSpecsUnit.bookAmount = null;
            }
            else
            {
                l_bookValueSpecsUnit.bookAmount = WEB3StringTypeUtility.formatNumber(((BookValueSpecParams)l_lisgetBookValueSpecUnits.get(0)).getBookAmount());
            }
        }
        double l_dblsumproloss = 0;
        if (l_lisBalanceRecordfromBookValueSpec != null)
        {
            //1.6 (*)get残高レコードfrom簿価単価明細()の戻り値の要素(=簿価単価明細Params)数分Loop処理
            int l_intSize = l_lisBalanceRecordfromBookValueSpec.size();
            for (int i = 0; i < l_intSize; i++)
            {
                //1.6.1 (*)損益の集計
                l_dblsumproloss += ((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(i)).getProloss1() +
                    ((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(i)).getProloss2();
                
            }            
        }

        //1.7 get簿価単価明細一覧(顧客, String, String, String, boolean)
        List l_lisBoolgetBookValueSpecUnits = l_historyTradeHistoryDataManager.getBookValueSpecList(l_mainAccount, l_strCommodityCode, l_strProductCode, l_strDisplayTerm, true);
        double l_dblsumproloss1 = 0;
        if (l_lisBoolgetBookValueSpecUnits != null)
        {
            //1.8 (*)get簿価単価明細一覧()の戻り値の要素(=簿価単価明細Params)数分Loop処理
            int l_intBookValueSize = l_lisBoolgetBookValueSpecUnits.size();
            for (int j = 0; j < l_intBookValueSize; j++)
            {
                //1.8.1 (*)損益の集計
                l_dblsumproloss1 += ((BookValueSpecParams)l_lisBoolgetBookValueSpecUnits.get(j)).getProloss1();
            }            
        }       

        //1.9 (*)プロパティセット
        if (l_lisBalanceRecordfromBookValueSpec != null)
        {
            l_bookValueSpecsUnit.bookValueSpecId = String.valueOf(((BookValueSpecParams)l_lisBalanceRecordfromBookValueSpec.get(0)).getBookValueSpecId());    
        }
        else
        {
            l_bookValueSpecsUnit.bookValueSpecId = null;
        }
                
        l_bookValueSpecsUnit.bookvalRecDiv = WEB3BookValueRecordDivDef.BALANCE_REC;
        l_bookValueSpecsUnit.prolossAmount = WEB3StringTypeUtility.formatNumber(l_dblsumproloss + l_dblsumproloss1);
                
        log.exiting(STR_METHOD_NAME); 
        return l_bookValueSpecsUnit;
    }
}
@
