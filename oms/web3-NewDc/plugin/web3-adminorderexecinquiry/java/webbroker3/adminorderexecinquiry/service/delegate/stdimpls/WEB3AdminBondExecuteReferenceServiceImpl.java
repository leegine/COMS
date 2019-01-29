head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminBondExecuteReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会サービスImpl(WEB3AdminBondExecuteReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏(中訊) 新規作成
                   2007/02/08 崔遠鵬 (中訊) 仕様変更・モデル090 
Revesion History : 2007/07/10 劉立峰(中訊) 仕様変更モデルNo.100
Revesion History : 2007/09/26 武波(中訊) モデルNo.108
*/

package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;

import webbroker3.adminorderexecinquiry.WEB3AdminBondQueryContainer;
import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.define.WEB3BondExecRefUnitKeyItemDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondAccountCodeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondBizDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondBranchCodeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondDeliveryDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondExecDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondForeignDeliveryDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondForeignExecDateComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondOrderTypeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondProductCodeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondReceivedDateTimeComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondSettlementDivComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminBondTraderComparator;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefConditionInfo;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminBondExecuteReferenceService;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondTradingTypeListDef;
import webbroker3.bd.define.WEB3BondTypeListDef;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderUnitIntroduceDivRow;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券管理者注文約定照会サービスImpl)<BR>
 * 債券管理者注文約定照会サービス実装クラス<BR>
 *   
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3AdminBondExecuteReferenceServiceImpl 
	implements WEB3AdminBondExecuteReferenceService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteReferenceServiceImpl.class);
    
    /**
     * @@roseuid 44E335A40167
     */
    public WEB3AdminBondExecuteReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 債券管理者注文約定照会を実施する。<BR>
     * <BR>
     * シーケンス図「（債券）管理者注文約定照会execute」参照
     * @@param l_request - (リクエスト)<BR>
     * 上り処理用リクエストの基底クラス。
     * @@throws  
     * @@roseuid 44B7518602DF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        WEB3GenResponse l_response = null;
    
        if (l_request instanceof WEB3AdminORBondExecRefReferenceRequest)
        {
            l_response = 
            	this.getReferenceScreen((WEB3AdminORBondExecRefReferenceRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminORBondExecRefInputRequest)
        {
            l_response = 
            	this.getInputScreen((WEB3AdminORBondExecRefInputRequest) l_request);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get注文約定照会条件)<BR>
     * 債券管理者注文約定照会条件表示処理を行う。<BR> 
     * <BR>
     * シーケンス図「(債券)get注文約定照会条件」参照<BR>
     * --------------------------------------------------
     * @@param request - (リクエスト)
     * 債券管理者注文約定照会条件表示リクエスト
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminBondExecRefInputResponse
     * @@roseuid 44B753CC0294
     */
    protected WEB3AdminORBondExecRefInputResponse getInputScreen
        (WEB3AdminORBondExecRefInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminORBondExecRefInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(String, boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_ORDER_EXECUTE_REFERENCE, false);

        //1.3 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 get発注日( )
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.5 get通貨コード一覧(証券会社コード : String)
        String[] l_strCurrencyCodes = 
            WEB3GentradeCurrency.getCurrencyCodeList(l_strInstitutionCode);
        
        //1.6 createレスポンス( )
        WEB3AdminORBondExecRefInputResponse l_response = 
            (WEB3AdminORBondExecRefInputResponse) l_request.createResponse();
        
        //1.7 プロパティセット       
        //発注日
        l_response.orderBizDate = l_datBizDate;
        
        //注文種別一覧
        String[] l_strTradingTypeList = 
            {
                WEB3BondTradingTypeListDef.BUY,
                WEB3BondTradingTypeListDef.SELL,
                WEB3BondTradingTypeListDef.RECRUIT
            };
        l_response.tradingTypeList = l_strTradingTypeList;
        
        //注文約定区分一覧
        String[] l_strExecutionStateList = 
            {
                WEB3BondOrderExecStatusDef.UNEXECUTED,
                WEB3BondOrderExecStatusDef.EXECUTED,
                WEB3BondOrderExecStatusDef.CANCELED
            };
        l_response.executionStateList = l_strExecutionStateList;
        
        //決済区分一覧
        String[] l_strSettleDivList = 
            {
                WEB3SettlementDivDef.JAPANESE_CURRENCY, 
                WEB3SettlementDivDef.FOREIGN_CURRENCY
            };
        l_response.settleDivList = l_strSettleDivList;
             
        //通貨コード一覧
        l_response.currencyCodeList = l_strCurrencyCodes;

        //債券タイプ一覧
        String[] l_strBondTypeList =
            {
                WEB3BondTypeListDef.FOREIGN_BOND,
                WEB3BondTypeListDef.INDIVIDUAL_GOVERNMENT_BOND,
                WEB3BondTypeListDef.CORPORATE_BOND
            };
        l_response.bondTypeList = l_strBondTypeList;

        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
    
    /**
     * (search注文約定照会一覧)<BR>
     * 債券注文約定照会表示と検索処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(債券)search注文約定照会一覧」参照<BR>
     * ---------------------------------------------------------------
     * @@param l_request - (リクエスト)<BR>
     * 債券管理者注文約定照会検索表示リクエスト
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefReferenceResponse
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     * @@throws  
     * @@throws NotFoundException 
     * @@roseuid 44B752B80105
     */
    protected WEB3AdminORBondExecRefReferenceResponse getReferenceScreen(
        WEB3AdminORBondExecRefReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getReferenceScreen(WEB3AdminORBondExecRefReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.validate( )
        l_request.validate();
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.BOND_ORDER_EXECUTE_REFERENCE, false);
        
        //1.4 validate部店権限(部店コード : String[])
        //部店権限のチェックを行う。 
        //[引数] 
        //部店コード：　@リクエストから取得した部店コード
        if (l_request.conditionInfo == null)
        {
            log.debug(STR_METHOD_NAME + "パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        l_administrator.validateBranchPermission(l_request.conditionInfo.branchCode);
        
        //1.5 get証券会社コード( )     
        l_admin.getInstitutionCode();
        
        //1.6 create検索条件(債券管理者注文約定照会検索条件情報, 管理者)
        WEB3AdminBondQueryContainer l_bondQueryContainer = 
            this.createQueryContainer(l_request.conditionInfo, l_administrator);
        
        //1.7 get債券注文単位リスト(条件文字列 : String, 条件データコンテナ : Object[])
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getOrderManager();
        
        String l_strGetQueryString = l_bondQueryContainer.getQueryString();
        Object[] l_objgGetQueryStringDate = l_bondQueryContainer.getQueryData();
        
        List l_lisbondOrderUnitList = 
            l_bondOrderManager.getBondOrderUnitList(
                l_strGetQueryString, l_objgGetQueryStringDate); 
         
        //1.8 create注文約定照会行(論理ビュー::List)
        WEB3AdminORBondExecRefUnit[] l_createExecReferenceUnit = 
            this.createExecReferenceUnit(l_lisbondOrderUnitList);

        //1.9 sort注文約定照会行(債券管理者注文約定照会行[], 注文約定照会ソートキー[])
        WEB3AdminORBondExecRefUnit[] l_sortOrderExecRefUnit =
        	this.sortOrderExecRefUnit(
                l_createExecReferenceUnit, 
                l_request.sortKeys);
        
        //1.10 createレスポンス( )
        WEB3AdminORBondExecRefReferenceResponse l_response = (
            WEB3AdminORBondExecRefReferenceResponse) l_request.createResponse();
                    
        //1.11 WEB3PageIndexInfo(l_objs : 論理ビュー::java::lang::Object[], l_intRequestPageIndex : int, l_intRequestPageSize : int)
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_sortOrderExecRefUnit,               
                l_intRequestPageIndex,
                l_intRequestPageSize);
       
        //1.12 getTotalPages( )
        int l_intGetTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.13 getTotalRecords( )
        int l_intGetTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.14 getPageIndex( )
        int l_intGetPageIndex = l_pageIndexInfo.getPageIndex();
        
        List l_lisUnits = new ArrayList();
        Object[] l_objUnits = l_pageIndexInfo.getArrayReturned();
        for (int i = 0; i < l_objUnits.length; i++ )
        {
        	l_lisUnits.add(l_objUnits[i]);
        }
        //1.15 getArrayReturned( )
        WEB3AdminORBondExecRefUnit[] l_adminORBondExecRefUnit = 
        	new WEB3AdminORBondExecRefUnit[l_lisUnits.size()];
        l_lisUnits.toArray(l_adminORBondExecRefUnit);
               
        //1.16 プロパティセット
        //総ページ数
        l_response.totalPages = l_intGetTotalPages + "";     
        
        //表示ページ番号
        l_response.pageIndex = l_intGetPageIndex + "";        
        
        //総レコード数
        l_response.totalRecords = l_intGetTotalRecords + "";
        
        if (l_intGetTotalPages == 0)
        {
            l_response.orderList = null;
        }
        else
        {
            l_response.orderList = l_adminORBondExecRefUnit;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (sort注文約定照会行)<BR>
     * 指定されたソートキー、昇降順にもどついて債券管理者注文約定照会行データのソートを<BR>
     * 行う。<BR> 
     * <BR>
     * １）ArrayListを生成する。<BR>  
     * <BR>
     * ２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。<BR> 
     * 　@　@　@　@①@ソートキー.キー項目の値に対応する比較項目Comparatorを生成する。<BR>  
     * 　@　@　@　@　@　@[コンストラクタにセットするパラメータ]<BR>  
     * 　@　@　@　@　@　@orderBy： ソートキー.昇順／降順 <BR>
     * <BR>
     * 　@　@　@　@②ArrayListに生成したComparatorを追加する。<BR> 
     * <BR>
     * ３）ArrayListからComparatorの配列を作成し、WEB3ArraysUtility.sort()メソッドをコー<BR>
     * ルする。<BR>  
     * <BR>
     * ４)ソートした債券管理者注文約定照会行の配列を返却する。<BR>
     * @@param ｌ_orBondExecRefUnit - (債券管理者注文約定照会行配列)<BR>
     * 債券管理者注文約定照会行の配列
     * @@param l_orderExecRefUnit - (ソートキー配列)<BR>
     * 注文約定照会ソートキーの配列
     * 
     * @@return WEB3AdminORBondExecRefUnit[]
     * @@roseuid 44B7560E016E
     */
    protected WEB3AdminORBondExecRefUnit[] sortOrderExecRefUnit(
        WEB3AdminORBondExecRefUnit[] ｌ_bondExecRefUnits, 
        WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys) 
    {    
        final String STR_METHOD_NAME = 
            "sortOrderExecRefUnit(WEB3AdminORBondExecRefUnit[]," +
            "WEB3AdminOROrderExecutionSortKeyUnit[])";
        log.entering(STR_METHOD_NAME);
        
        //１）ArrayListを生成する。
        ArrayList l_lisComparator = new ArrayList();
        
        //２）パラメータ.ソートキーの要素数分以下の処理を繰り返す。
        //①@ソートキー.キー項目の値に対応する比較項目Comparatorを生成する。
        //[コンストラクタにセットするパラメータ]
        //orderBy： ソートキー.昇順／降順
        int l_intLength = l_sortKeys.length;
        for (int i = 0; i < l_intLength; i ++)
        { 
            if (WEB3BondExecRefUnitKeyItemDivDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(1)(債券)顧客コードComparatorを生成する。
                WEB3AdminBondAccountCodeComparator l_accountCodeComparator = 
                    new WEB3AdminBondAccountCodeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_accountCodeComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)銘柄コード（WEB3）Comparator
                WEB3AdminBondProductCodeComparator l_productCodeComparator  =
                    new WEB3AdminBondProductCodeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_productCodeComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)注文種別Comparator
                WEB3AdminBondOrderTypeComparator l_orderTypeComparator =
                    new WEB3AdminBondOrderTypeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_orderTypeComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.SETTLE_DIV.equals(l_sortKeys[i].keyItem))
            {
                //(債券)決済区分Comparator
                WEB3AdminBondSettlementDivComparator l_settlementDivComparator =
                    new WEB3AdminBondSettlementDivComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_settlementDivComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.ACCEPT_ORDER_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                //(債券)受注日時Comparator
                WEB3AdminBondReceivedDateTimeComparator l_receivedDateTimeComparator =
                    new WEB3AdminBondReceivedDateTimeComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_receivedDateTimeComparator);
            }
       
            if (WEB3BondExecRefUnitKeyItemDivDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)注文発注日Comparator
                WEB3AdminBondBizDateComparator l_bizDateComparator = 
                    new WEB3AdminBondBizDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_bizDateComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_EXECUTION_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)国内約定日Comparator
                WEB3AdminBondExecDateComparator l_execDateComparator  = 
                    new WEB3AdminBondExecDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_execDateComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_EXECUTION_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)現地約定日Comparator
                WEB3AdminBondForeignExecDateComparator l_foreignExecDateComparator =
                    new WEB3AdminBondForeignExecDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_foreignExecDateComparator);
            }
            
            if (WEB3BondExecRefUnitKeyItemDivDef.DOMESTIC_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))   
            {
                //(債券)受渡日Comparator
                WEB3AdminBondDeliveryDateComparator l_deliveryDateComparator =
                    new WEB3AdminBondDeliveryDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_deliveryDateComparator);
            }
                       
            if (WEB3BondExecRefUnitKeyItemDivDef.FOREIGN_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)現地受渡日Comparator
                WEB3AdminBondForeignDeliveryDateComparator l_foreignDeliveryDateComparator  =
                    new WEB3AdminBondForeignDeliveryDateComparator(l_sortKeys[i].ascDesc);
                
                l_lisComparator.add(l_foreignDeliveryDateComparator);
            }

            if (WEB3BondExecRefUnitKeyItemDivDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)部店コードComparator
                WEB3AdminBondBranchCodeComparator l_branchCodeComparator  =
                    new WEB3AdminBondBranchCodeComparator(l_sortKeys[i].ascDesc);

                l_lisComparator.add(l_branchCodeComparator);
            }

            if (WEB3BondExecRefUnitKeyItemDivDef.SONAR_TRADER_CODE.equals(l_sortKeys[i].keyItem))
            {
                //(債券)扱者Comparator
                WEB3AdminBondTraderComparator l_traderComparator  =
                    new WEB3AdminBondTraderComparator(l_sortKeys[i].ascDesc);

                l_lisComparator.add(l_traderComparator);
            }
        }
        //3）ArrayListからComparatorの配列を取得する。
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);
        WEB3ArraysUtility.sort(ｌ_bondExecRefUnits, l_comparators);

        //４)ソートした債券管理者注文約定照会行の配列を返却する。 
        log.exiting(STR_METHOD_NAME);
        return ｌ_bondExecRefUnits;   
    }
    
    /**
     * (create注文約定照会行)<BR>
     * 引数.債券注文単位リストから債券管理者注文約定照会行の配列を作成。<BR>
     * <BR>
     * １）引数.債券注文単位リストの要素数分Loopする。<BR>
     * ※プロパティセット時、各項目がNULLでない場合のみセットする。<BR>
     * 　@１－１）Loopの債券注文単位毎の部店ID、顧客ID、銘柄ID、オペレータＩＤに対応する<BR>
     * 　@　@　@　@　@部店コード、表示顧客コード、銘柄コード（WEB3）、銘柄名、オペレータコードを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@・部店コードと表示顧客コードは、拡張アカウントマネージャ.get顧客（顧客ID）<BR>
     *　@　@　@　@　@　@の取得オブジェクトから取得<BR>
     * 　@　@　@　@　@・銘柄コード（WEB3）と銘柄名は、債券プロダクトマネージャ.get債券銘柄（<BR>
     *　@　@　@　@　@　@銘柄ID）の取得オブジェクトから取得<BR>
     * 　@　@　@　@　@・オペレータコードは、拡張金融オブジェクトマネージャ.get取引者(オペレー<BR>
     *　@　@　@　@　@　@タID)の取得オブジェクトから取得<BR>
     * <BR>
     * 　@１－２）債券管理者注文約定照会行を生成する。<BR>
     * <BR>
     * 　@１－３）債券管理者注文約定照会行の属性をセットする。<BR>
     * <BR>
     * 　@　@・債券管理者注文約定照会行.注文ID　@　@　@　@　@　@　@＝債券注文単位.注文ID<BR>
     * 　@　@・債券管理者注文約定照会行.部店コード　@　@　@　@　@＝１－１）で取得した部店コード<BR>
     * 　@　@・債券管理者注文約定照会行.顧客コード　@　@　@　@　@＝１－１）で取得した表示顧客コード <BR>
     * 　@　@・債券管理者注文約定照会行.銘柄コード（WEB3）＝１－１）で取得した銘柄コード(WEB3）<BR>
     * 　@　@・債券管理者注文約定照会行.銘柄名　@　@　@　@　@　@　@＝１－１）で取得した銘柄名<BR>
     * 　@　@・債券管理者注文約定照会行.決済区分　@　@　@　@　@＝債券注文単位.決済区分<BR>
     * 　@　@・債券管理者注文約定照会行.注文数量　@　@　@　@　@＝債券注文単位.注文数量<BR>
     * 　@　@・債券管理者注文約定照会行.指値　@　@　@　@　@　@　@　@＝債券注文単位.指値<BR>
     * 　@　@・債券管理者注文約定照会行.売買代金（円貨）　@＝債券注文単位.売買代金（円貨）<BR>
     * 　@　@・債券管理者注文約定照会行.経過利子（円貨）　@＝債券注文単位.経過利子（円貨）<BR>
     * 　@　@・債券管理者注文約定照会行.受渡代金（円貨）　@＝債券注文単位.受渡代金（円貨）<BR>
     * 　@　@・債券管理者注文約定照会行.通貨コード　@　@　@　@　@＝債券注文単位.通貨コード<BR>
     * 　@　@・債券管理者注文約定照会行.売買代金（外貨）　@＝債券注文単位.売買代金（外貨）<BR>
     * 　@　@・債券管理者注文約定照会行.経過利子（外貨）　@＝債券注文単位.経過利子（外貨）<BR>
     * 　@　@・債券管理者注文約定照会行.受渡代金（外貨）　@＝債券注文単位.受渡代金（外貨）<BR>
     * 　@　@・債券管理者注文約定照会行.受注日時　@　@　@　@　@　@＝債券注文単位.受注日時<BR>
     * 　@　@・債券管理者注文約定照会行.発注日　@　@　@　@　@　@　@＝債券注文単位.発注日<BR>
     * 　@　@・債券管理者注文約定照会行.約定日　@　@　@　@　@　@　@＝債券注文単位.約定日<BR>
     * 　@　@・債券管理者注文約定照会行.現地約定日　@　@　@　@＝債券注文単位.現地約定日<BR>
     * 　@　@・債券管理者注文約定照会行.注文約定区分　@　@　@＝債券注文単位.注文約定区分<BR>
     * 　@　@・債券管理者注文約定照会行.注文経路区分　@　@　@＝債券注文単位.注文経路区分<BR>
     * 　@　@・債券管理者注文約定照会行.初回注文の注文チャネル＝債券注文単位.初回注文の注文チャネル<BR>
     * 　@　@・債券管理者注文約定照会行.オペレータコード　@　@　@＝１－１）で取得したオペレータコード<BR>
     * 　@　@・債券管理者注文約定照会行.扱者コード（SONAR）＝債券注文単位.扱者コード（SONAR）<BR>
     * 　@　@・債券管理者注文約定照会行.管理者コード　@　@　@　@＝債券注文単位.管理者コード<BR>
     * <BR>
     * 　@１－４）注文種別をセットする。<BR>
     * 　@　@　@　@　@債券注文単位.get注文種別判定()で債券注文種別判定オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@・債券注文種別判定.is応募＝＝trueの場合、<BR>
     * 　@　@　@　@　@　@債券管理者注文約定照会行.注文種別　@　@　@　@　@＝’応募’<BR>
     * <BR>
     * 　@　@　@　@　@・債券注文種別判定.is買付＝＝trueの場合、<BR>
     * 　@　@　@　@　@　@債券管理者注文約定照会行.注文種別　@　@　@　@　@＝’買付’<BR>
     * <BR>
     * 　@　@　@　@　@・債券注文種別判定.is売却＝＝trueの場合、<BR>
     * 　@　@　@　@　@　@債券管理者注文約定照会行.注文種別　@　@　@　@　@＝’売却’<BR>
     * <BR>
     * 　@１－５）受渡日、現地受渡日をセットする。<BR>
     * 　@　@　@　@・債券注文種別判定.is応募注文 == trueの場合、<BR>
     * 　@　@　@　@　@　@債券管理者注文約定照会行.受渡日　@　@　@　@　@　@　@＝債券注文単位.入金日<BR>
     * 　@　@　@　@　@　@債券管理者注文約定照会行.現地受渡日(*1)　@　@＝債券注文単位.入金日<BR>
     * <BR>
     * 　@　@　@　@・債券注文種別判定.is応募注文 != trueの場合、<BR>
     * 　@　@　@　@　@　@債券管理者注文約定照会行.受渡日　@　@　@　@　@　@　@＝債券注文単位.受渡日<BR>
     * 　@　@　@　@　@　@債券管理者注文約定照会行.現地受渡日(*1)　@　@＝債券注文単位.現地受渡日<BR>
     * <BR>
     * 　@　@　@　@(*1)債券注文単位.get債券タイプ != ”外国債券” の場合、nullをセットする。<BR>
     * <BR>
     * <BR>
     * 　@１－６）債券注文単位.約定数量＞０の場合<BR>
     * 　@　@　@　@・債券管理者注文約定照会行.約定数量　@　@　@　@　@＝債券注文単位.約定数量<BR>
     * <BR>
     * 　@１－７）債券注文単位.約定単価＞０の場合<BR>
     * 　@　@・債券管理者注文約定照会行.約定単価　@　@　@　@　@＝債券注文単位.約定単価<BR>
     * <BR>
     * 　@１－８）為替レートは以下のように設定する。<BR>
     * 　@　@・債券注文単位.get注文約定区分＝＝’未約定’の場合<BR>
     * 　@　@　@　@債券管理者注文約定照会行.為替レート＝債券注文単位.get基準為替レート()<BR>
     * 　@　@・債券注文単位.get注文約定区分＝＝’約定済’の場合<BR>
     * 　@　@　@　@債券管理者注文約定照会行.為替レート＝債券注文単位.get約定為替レート()<BR>
     * 　@　@・上記以外の場合<BR>
     * 　@　@　@　@債券注文単位.約定為替レート != nullの場合<BR>
     * 　@　@　@　@　@債券管理者注文約定照会行.為替レート＝債券注文単位.get約定為替レート()<BR>
     * 　@　@　@　@債券注文単位.基準為替レート != nullの場合<BR>
     * 　@　@　@　@　@債券管理者注文約定照会行.為替レート＝債券注文単位.get基準為替レート()<BR>
     * <BR>
     * 　@１－９）紹介店区分と紹介店コードは以下のように設定する。<BR>
     * <BR>
     * 　@　@１－９－１）債券注文単位.get注文単位紹介区分を呼ぶ<BR>
     * <BR>
     * 　@　@１－９－２）取得した注文単位紹介区分Rowオブジェクトがnullでない場合、以下の値<BR>
     *　@　@　@　@　@をセットする。<BR>
     * 　@　@　@・債券管理者注文約定照会行.紹介店区分<BR>　@　@ 
     *　@　@　@　@　@＝注文単位紹介区分Rowオブジェクト.get紹介区分<BR>
     * 　@　@　@・債券管理者注文約定照会行.紹介店コード　@　@＝注文単位紹介区分Rowオブジェク<BR>
     *　@　@　@　@　@ト.紹介店コード<BR>
     * <BR>
     * 　@１－１０）ロック解除ボタン区分は以下のように設定する。<BR>
     * 　@　@・債券管理者ヘルパーサービス.getロック解除ボタン区分で取得した値を設定する。<BR>
     * <BR>
     * <BR>
     * 　@１－１１）約定変更ボタン区分は以下のように設定する。<BR>
     * 　@　@・債券管理者ヘルパーサービス.get約定変更ボタン区分()で取得した値を設定する。<BR>
     * <BR>
     * <BR>
     * 　@１－１２）取消ボタン区分は以下のように設定する。<BR>
     * 　@　@・債券管理者ヘルパーサービス.get取消ボタン区分()で取得した値を設定する。<BR>
     * <BR>
     * 　@１－１３）債券管理者注文約定照会行の配列に債券管理者注文約定照会行を追加する。
     * <BR>
     * <BR>
     * ２）Loop終了後、債券管理者注文約定照会行の配列を返す。
     * @@param l_lisBondOrderUnits - (債券注文単位リスト)<BR>
     * 拡張債券注文単位リスト
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminORBondExecRefUnit[]
     * @@throws WEB3BaseException 
     * @@throws  
     * @@throws NotFoundException 
     * @@roseuid 44B7569A01F0
     */
    protected WEB3AdminORBondExecRefUnit[] createExecReferenceUnit(
        List l_lisBondOrderUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createExecReferenceUnit(List l_lisBondOrderUnits)";
        log.entering(STR_METHOD_NAME);
        //１）引数.債券注文単位リストの要素数分Loopする。 
        //１－１）Loopの債券注文単位毎の部店ID、顧客ID、銘柄ID、オペレータＩＤに対応する 
        //　@　@　@　@部店コード、顧客コード、銘柄コード（WEB3）、銘柄名、オペレータコードを取得する。 
        
        ArrayList l_lisTemp = new ArrayList();
        for (int i = 0; i < l_lisBondOrderUnits.size(); i++)
        {              
        	BondOrderUnitRow l_orderUnitRow = (BondOrderUnitRow) l_lisBondOrderUnits.get(i);
            WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit(l_orderUnitRow);
              
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
            	(WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            //・部店コードと顧客コードは、拡張アカウントマネージャ.get顧客（顧客ID）の取得オブジェクトから取得 
            WEB3GentradeMainAccount l_mainAccount = null;
            String l_strBranchCode = null;
            String l_strAccountCode = null;
            String l_strProductCode = null;
            String l_strProductName = null;
            String l_strTraderCode = null;
            Trader l_trader = null;
            BondOrderUnitRow l_bondOrderUnitRow = 
            	(BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
            try
            {
                l_mainAccount = 
                	(WEB3GentradeMainAccount) l_accountManager.getMainAccount(
            			l_bondOrderUnit.getAccountId());

                l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                l_strAccountCode = l_mainAccount.getDisplayAccountCode();
               
                //・銘柄コード（WEB3）と銘柄名は、債券プロダクトマネージャ.get債券銘柄（銘柄ID）の取得オブジェクトから取得 
                WEB3BondProductManager l_productManager = 
                    (WEB3BondProductManager) l_finApp.getTradingModule(
                		ProductTypeEnum.BOND).getProductManager();
                WEB3BondProduct l_bondProduct = null;
                l_bondProduct = 
                    (WEB3BondProduct) l_productManager.getBondProduct(
                		l_bondOrderUnit.getProduct().getProductId());
                l_strProductCode = l_bondProduct.getProductCode();
                l_strProductName = l_bondProduct.getProductName();
                
                //・オペレータコードは、拡張金融オブジェクトマネージャ.get取引者(オペレータID)の取得オブジェクトから取得
                WEB3GentradeFinObjectManager l_finObjManager = 
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                if (!((BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject
                        ()).getTraderIdIsNull())
                {
                    l_trader = l_finObjManager.getTrader(l_bondOrderUnit.getTraderId());
                    l_strTraderCode = l_trader.getTraderCode();
                }
            } 
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
                  
            //１－２）債券管理者注文約定照会行を生成する。
            WEB3AdminORBondExecRefUnit l_bondExecRefUnit = new WEB3AdminORBondExecRefUnit();
   
            //１－３）債券管理者注文約定照会行の属性をセットする。 
            //  ・債券管理者注文約定照会行.注文ID　@　@　@　@　@　@　@＝債券注文単位.注文ID 
            if (l_bondOrderUnitRow.getOrderIdIsSet())
            {
            	l_bondExecRefUnit.id = l_bondOrderUnit.getOrderId() + "";
            }
          
            //　@・債券管理者注文約定照会行.部店コード　@　@　@　@　@＝１－１）で取得した部店コード
            if (l_strBranchCode != null)
            {
            	l_bondExecRefUnit.branchCode = l_strBranchCode;
            }
            
            //　@・債券管理者注文約定照会行.顧客コード　@　@　@　@　@＝１－１）で取得した表示顧客コード 
            if (l_strAccountCode != null)
            {
            	l_bondExecRefUnit.accountCode = l_strAccountCode;
            }
            
            //　@・債券管理者注文約定照会行.銘柄コード（WEB3）＝１－１）で取得した銘柄コード(WEB3）
            if (l_strProductCode != null)
            {
            	l_bondExecRefUnit.productCode = l_strProductCode;
            }
            
            //　@・債券管理者注文約定照会行.銘柄名　@　@　@　@　@　@　@＝１－１）で取得した銘柄名
            if (l_strProductName != null)
            {
            	l_bondExecRefUnit.productName = l_strProductName;
            }
            
            //　@・債券管理者注文約定照会行.決済区分　@　@　@　@　@＝債券注文単位.決済区分
            if (l_bondOrderUnit.getSettlementDiv() != null)
            {
            	l_bondExecRefUnit.settleDiv = l_bondOrderUnit.getSettlementDiv();
            }
            
            //　@・債券管理者注文約定照会行.注文数量　@　@　@　@　@＝債券注文単位.注文数量 
            if (l_bondOrderUnitRow.getQuantityIsSet())
            {
	            l_bondExecRefUnit.orderQuantity = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getQuantity());
            }
           
            //　@・債券管理者注文約定照会行.指値　@　@　@　@　@　@　@　@＝債券注文単位.指値 
            if (!l_bondOrderUnitRow.getLimitPriceIsNull())
            {
	            l_bondExecRefUnit.limitOrderPrice = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getLimitPrice()) ;
            }
            
            
            //　@・債券管理者注文約定照会行.売買代金（円貨）　@＝債券注文単位.売買代金（円貨）
            if (!l_bondOrderUnitRow.getTradingPriceIsNull())
            {
	            l_bondExecRefUnit.yenTradePrice =
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getTradingPrice());
            }
            
            //　@・債券管理者注文約定照会行.経過利子（円貨）　@＝債券注文単位.経過利子（円貨）
            if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
            {
	            l_bondExecRefUnit.yenAccruedInterest = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getAccruedInterest());
            }
            
            //　@・債券管理者注文約定照会行.受渡代金（円貨）　@＝債券注文単位.受渡代金（円貨）
            if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
            {
	            l_bondExecRefUnit.yenDeliveryPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getEstimatedPrice());
            }
            
            //　@・債券管理者注文約定照会行.通貨コード　@　@　@　@　@＝債券注文単位.通貨コード
            if (l_bondOrderUnit.getCurrencyCode() != null)
            {
            	l_bondExecRefUnit.currencyCode = l_bondOrderUnit.getCurrencyCode();
            }
            
            //　@・債券管理者注文約定照会行.売買代金（外貨）　@＝債券注文単位.売買代金（外貨）
            if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
            {
	            l_bondExecRefUnit.foreignTradePrice = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignTradingPrice());
            }
            
            //　@・債券管理者注文約定照会行.経過利子（外貨）　@＝債券注文単位.経過利子（外貨）
            if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
            {
	            l_bondExecRefUnit.foreignAccruedInterest = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignAccruedInterest());
            }
            
            //　@・債券管理者注文約定照会行.受渡代金（外貨）　@＝債券注文単位.受渡代金（外貨）
            if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
            {
	            l_bondExecRefUnit.foreignDeliveryPrice = 
	                WEB3StringTypeUtility.formatNumber(l_bondOrderUnit.getForeignEstimatedPrice());
            }
            
            //　@・債券管理者注文約定照会行.受注日時　@　@　@　@　@　@＝債券注文単位.受注日時
            if (l_bondOrderUnit.getReceivedDateTime() != null)
            {
	            l_bondExecRefUnit.acceptOrderTimeStamp = l_bondOrderUnit.getReceivedDateTime();
            }
            
            //　@・債券管理者注文約定照会行.発注日　@　@　@　@　@　@　@＝債券注文単位.発注日 
            if (l_bondOrderUnitRow.getBizDateIsSet())
            {
	            l_bondExecRefUnit.orderBizDate = 
	                WEB3DateUtility.getDate(l_bondOrderUnit.getBizDate(), "yyyyMMdd");      
            }
            //　@・債券管理者注文約定照会行.約定日　@　@　@　@　@　@　@＝債券注文単位.約定日 
            if (l_bondOrderUnit.getExecDate() != null)
            {
	            l_bondExecRefUnit.domesticExecutionDate = l_bondOrderUnit.getExecDate();
            }
            
            //　@・債券管理者注文約定照会行.現地約定日　@　@　@　@＝債券注文単位.現地約定日
            if (l_bondOrderUnit.getForeignExecDate() != null)
            {
	            l_bondExecRefUnit.foreignExecutionDate = l_bondOrderUnit.getForeignExecDate();
            }
            
            //　@・債券管理者注文約定照会行.注文約定区分　@　@　@＝債券注文単位.注文約定区分 
            if (l_bondOrderUnitRow.getOrderExecStatusIsSet())
            {
            	l_bondExecRefUnit.executionState = l_bondOrderUnit.getOrderExecStatus();
            }
            
            //　@・債券管理者注文約定照会行.注文経路区分　@　@　@＝債券注文単位.注文経路区分
            if (l_bondOrderUnit.getOrderRootDiv() != null)
            {
            	l_bondExecRefUnit.orderRootDiv = l_bondOrderUnit.getOrderRootDiv();
            }
            
            //　@・債券管理者注文約定照会行.初回注文の注文チャネル＝債券注文単位.初回注文の注文チャネル
            if (l_bondOrderUnit.getOrderChannel() != null)
            {
            	l_bondExecRefUnit.orderChannel = l_bondOrderUnit.getOrderChannel();
            }
            
            //　@・債券管理者注文約定照会行.オペレータコード　@　@　@＝１－１）で取得したオペレータコード
            if (l_strTraderCode != null)
            {
            	l_bondExecRefUnit.operatorCode = l_strTraderCode;
            }
            
            //　@・債券管理者注文約定照会行.扱者コード（SONAR）＝債券注文単位.扱者コード（SONAR）
            if (l_bondOrderUnit.getSonarTraderCode() != null)
            {
            	l_bondExecRefUnit.sonarTraderCode = l_bondOrderUnit.getSonarTraderCode();
            }
            
            //　@・債券管理者注文約定照会行.管理者コード　@　@　@　@＝債券注文単位.管理者コード
            if (l_bondOrderUnit.getAdminstratorCode() != null)
            {
            	l_bondExecRefUnit.administratorCode = l_bondOrderUnit.getAdminstratorCode();
            }
   
            //１－４）注文種別をセットする。 
            //債券注文単位.get注文種別判定()で債券注文種別判定オブジェクトを取得する。 
            //・債券注文種別判定.is応募＝＝trueの場合、 
            //債券管理者注文約定照会行.注文種別　@　@　@　@　@＝’応募’ 
            //・債券注文種別判定.is買付＝＝trueの場合、 
            //債券管理者注文約定照会行.注文種別　@　@　@　@　@＝’買付’
            //・債券注文種別判定.is売却＝＝trueの場合、
            //債券管理者注文約定照会行.注文種別　@　@　@　@　@＝’売却’
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
            if (l_bondOrderTypeJudge.isRecruitOrder())
            {               
                l_bondExecRefUnit.tradingType = WEB3BondTradingTypeListDef.RECRUIT;
            }
            else if (l_bondOrderTypeJudge.isBuyOrder())
            {
                l_bondExecRefUnit.tradingType = WEB3BondTradingTypeListDef.BUY;
            }
            else if (l_bondOrderTypeJudge.isSellOrder())
            {
                l_bondExecRefUnit.tradingType = WEB3BondTradingTypeListDef.SELL;
            }
            
            //１－５）受渡日、現地受渡日をセットする。
            if (l_bondOrderTypeJudge.isRecruitOrder())
            {
                //・債券注文種別判定.is応募注文 == trueの場合、
                //債券管理者注文約定照会行.受渡日＝債券注文単位.入金日
                l_bondExecRefUnit.domesticDeliveryDate = l_bondOrderUnit.getPaymentDate();

                //債券管理者注文約定照会行.現地受渡日(*1)＝債券注文単位.入金日
                l_bondExecRefUnit.foreignDeliveryDate = l_bondOrderUnit.getPaymentDate();
            }
            else
            {
                //・債券注文種別判定.is応募注文 != trueの場合、
                //債券管理者注文約定照会行.受渡日＝債券注文単位.受渡日
                l_bondExecRefUnit.domesticDeliveryDate = l_bondOrderUnit.getDeliveryDate();

                //債券管理者注文約定照会行.現地受渡日(*1)＝債券注文単位.現地受渡日
                l_bondExecRefUnit.foreignDeliveryDate = l_bondOrderUnit.getForeignDeliveryDate();
            }
            //(*1)債券注文単位.get債券タイプ != ”外国債券” の場合、nullをセットする。
            if (!BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
            {
                l_bondExecRefUnit.foreignDeliveryDate = null;
            }

            //１－６）債券管理者注文約定照会行.約定数量　@　@　@　@　@＝債券注文単位.約定数量
            if (l_bondOrderUnit.getExecutedQuantity() > 0)
            {
                l_bondExecRefUnit.execQuantity = WEB3StringTypeUtility.formatNumber(
                    l_bondOrderUnit.getExecutedQuantity());
            }
            
            //１－７）債券注文単位.約定単価＞０の場合 
            //・債券管理者注文約定照会行.約定単価　@　@　@　@　@＝債券注文単位.約定単価 
            if (l_bondOrderUnit.getExecutedPrice() > 0)
            {
                l_bondExecRefUnit.execPrice = WEB3StringTypeUtility.formatNumber(
                    l_bondOrderUnit.getExecutedPrice());
            }
            
            //1－８）為替レートは以下のように設定する。
            //・債券注文単位.get注文約定区分＝＝’未約定’の場合
            //   債券管理者注文約定照会行.為替レート＝債券注文単位.get基準為替レート()
            //・債券注文単位.get注文約定区分＝＝’約定済’の場合
            //   債券管理者注文約定照会行.為替レート＝債券注文単位.get約定為替レート()        
            //・上記以外の場合
            //   債券注文単位.約定為替レート != nullの場合
            //     債券管理者注文約定照会行.為替レート＝債券注文単位.get約定為替レート()
            //   債券注文単位.基準為替レート != nullの場合
            //     債券管理者注文約定照会行.為替レート＝債券注文単位.get基準為替レート()
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()))
            {
            	if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
            	{
            		l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
        				l_bondOrderUnit.getBaseFxRate());
            	}
            }
            else if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()))
            {
            	if (!l_bondOrderUnitRow.getExecFxRateIsNull())
            	{
	                l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
	                    l_bondOrderUnit.getExecFxRate());
            	}
            }
            else
            {
                if (!l_bondOrderUnitRow.getExecFxRateIsNull())
                {
                    l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
                        l_bondOrderUnit.getExecFxRate());
                }
                else if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
                {
                    l_bondExecRefUnit.fxRate = WEB3StringTypeUtility.formatNumber(
                        l_bondOrderUnit.getBaseFxRate());
                }
            }
            
            //　@１－９）紹介店区分と紹介店コードは以下のように設定する。
            //１－９－１）債券注文単位.get注文単位紹介区分を呼ぶ 
            OrderUnitIntroduceDivRow l_orderUnitIntroduceDiv = l_bondOrderUnit.getOrderUnitIntroduceDiv();
            
            //１－９－２）取得した注文単位紹介区分Rowオブジェクトがnullでない場合、以下の値をセットする。 
            //　@　@・債券管理者注文約定照会行.紹介店区分　@　@ ＝注文単位紹介区分Rowオブジェクト.get紹介区分
            if (l_orderUnitIntroduceDiv != null)
            {
	            l_bondExecRefUnit.introduceStoreDiv = l_orderUnitIntroduceDiv.getIntroduceBranchDiv();
	            
	            //　@　@・債券管理者注文約定照会行.紹介店コード　@　@＝注文単位紹介区分Rowオブジェクト.紹介店コード 
	            l_bondExecRefUnit.introduceStoreCode =  l_orderUnitIntroduceDiv.getIntroduceBranchCode();
            }
            
            //１－１0）ロック解除ボタン区分は以下のように設定する。 
            WEB3AdminBondHelperService l_service = 
                (WEB3AdminBondHelperService) Services.getService(WEB3AdminBondHelperService.class);
            
            //　@・債券管理者ヘルパーサービス.getロック解除ボタン区分で取得した値を設定する。             
            l_bondExecRefUnit.lockDiv = l_service.getOrderLockButtonDiv(l_bondOrderUnit);
            
            //１－１１）約定変更ボタン区分は以下のように設定する。 
            //　@・債券管理者ヘルパーサービス.get約定変更ボタン区分()で取得した値を設定する。 
            l_bondExecRefUnit.execChgDiv = l_service.getExecuteChangButtonDiv(l_bondOrderUnit);
            
            //１－１２）取消ボタン区分は以下のように設定する。 
            //　@・債券管理者ヘルパーサービス.get取消ボタン区分()で取得した値を設定する。 
            l_bondExecRefUnit.cancelDiv = l_service.getCancelButtonDiv(l_bondOrderUnit);
            
            //１－１３）債券管理者注文約定照会行の配列に債券管理者注文約定照会行を追加する。 
            
            l_lisTemp.add(l_bondExecRefUnit);
        }
        
        WEB3AdminORBondExecRefUnit[] l_adminORBondExecRefUnit =
            new WEB3AdminORBondExecRefUnit[l_lisTemp.size()];
        l_lisTemp.toArray(l_adminORBondExecRefUnit);

        log.exiting(STR_METHOD_NAME);
        return l_adminORBondExecRefUnit;
    }   

    /**
     * (create検索条件)<BR>
     *注文約定検索を実施する為の検索条件コンテナを生成して返却する。<BR>
     *<BR>
     *1) 部店条件を検索条件文字列に追加する。 <BR>
     *　@　@債券管理者注文約定照会検索条件情報.部店コードの要素数分、拡張アカウントマネージャから部店を取得。 <BR>
     *　@　@(該当する部店情報が見つからない場合、エラー「該当部店データなし」エラーをスローする) <BR>
     * 　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@tag      : BUSINESS_ERROR_01386<BR>
     *    検索条件文字列に " 部店ID in (?, ?,,,) "を追加する <BR>
     *    検索条件データに　@上記に対応する部店.部店ID　@を追加する <BR>
     *<BR>
     *2) 債券管理者注文約定照会検索条件情報.注文ID!=nullの場合： <BR>
     *    検索条件文字列に " and 注文ID = ? "を追加する <BR><BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.注文ID　@を追加する <BR>
     *<BR>
     *3) 債券管理者注文約定照会検索条件情報.銘柄コード（WEB3）!=nullの場合： <BR>
     *    銘柄コード（WEB3）で債券銘柄マネージャから債券銘柄を取得。(該当する債券銘<BR>
     *    柄が見つからない場合、エラー「指定した銘柄コードに合致している銘柄が存在しません。」<BR>
     *    をスローする) <BR>
     * 　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@tag      : BUSINESS_ERROR_00301<BR>
     *    検索条件文字列に " and 銘柄ID = ? "を追加する <BR>
     *    検索条件データに　@債券銘柄.銘柄ID　@を追加する <BR>
     *<BR>
     *4) 債券管理者注文約定照会検索条件情報.顧客コード!=nullの場合： <BR>
     *    管理者注文約定照会データマネージャ.get顧客一覧(証券会社コード, 部店コード, 顧客コード)で顧客一覧を取得。 <BR>
     *    (該当する顧客が見つからない場合、エラー「該当する顧客が存在しません。」をスローする) <BR>
     *    [引数] <BR>
     * 　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@tag      : BUSINESS_ERROR_01035<BR>
     *　@　@　@証券会社コード：管理者.証券会社コード <BR>
     *　@　@　@部店コード：債券管理者注文約定照会検索条件情報.部店コード <BR>
     *　@　@　@顧客コード：債券管理者注文約定照会検索条件情報.顧客コード <BR>
     *    検索条件文字列に " and 口座ID in (?, ?,,,) "を追加する <BR>
     *    検索条件データに　@上記に対応する顧客.口座ID　@を追加する <BR>
     *<BR>
     *5) 債券管理者注文約定照会検索条件情報.発注日!=nullの場合： <BR>
     *    検索条件文字列に " and 発注日 = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.発注日　@を追加する <BR>
     *<BR>
     *6) 債券管理者注文約定照会検索条件情報.約定日!=nullの場合： <BR>
     *    検索条件文字列に " and 約定日 = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.約定日　@を追加する <BR>
     *<BR>
     *7) 債券管理者注文約定照会検索条件情報.注文種別!=nullの場合： <BR>
     *    [注文種別＝＝買付　@又は　@注文種別＝＝売却　@の場合] <BR>
     *    　@　@検索条件文字列に " and 注文種別 = ? "を追加する <BR>
     *    　@　@検索条件データに　@債券管理者注文約定照会検索条件情報.注文種別　@を追加する <BR>
     *    　@　@検索条件文字列に " and 取引 = ? "を追加する <BR>
     *    　@　@検索条件データに　@国内仕切取引　@を追加する <BR>
     *　@　@[注文種別＝＝応募　@の場合] <BR>
     *    　@　@検索条件文字列に " and (（注文種別 = ? "を追加する <BR>
     *    　@　@検索条件データに　@債券買付　@を追加する <BR>
     *    　@　@検索条件文字列に " and 取引 = ?） "を追加する <BR>
     *    　@　@検索条件データに　@募集取引　@を追加する <BR>
     *        検索条件文字列に " or 注文種別 = ? )"を追加する <BR>
     *　@　@    検索条件データに　@国内債券応募注文　@を追加する <BR>
     *<BR>
     *8) 債券管理者注文約定照会検索条件情報.注文約定区分!=nullの場合： <BR>
     *    検索条件文字列に " and 注文約定区分 = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.注文約定区分 を追加する <BR>
     *<BR>
     *9) 債券管理者注文約定照会検索条件情報.決済区分!=nullの場合： <BR>
     *    検索条件文字列に " and 決済区分 = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.決済区分　@を追加する <BR>
     *<BR>
     *10) 債券管理者注文約定照会検索条件情報.通貨コード!=nullの場合： <BR>
     *    検索条件文字列に " and 通貨コード = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.通貨コード　@を追加する <BR>
     *<BR>
     *11) 債券管理者注文約定照会検索条件情報.オペレータコード!=nullの場合：<BR>
     *    管理者注文約定照会データマネージャ.get扱者一覧(証券会社, 部店コード, <BR>
     *扱者コード)で扱者一覧を取得。<BR>
     *    (該当する扱者が見つからない場合、エラー「扱者が存在しない。」をスローする)<BR>
     * 　@　@class    : WEB3BusinessLayerException<BR>
     * 　@　@tag      : BUSINESS_ERROR_01191<BR>
     *    [引数]<BR>
     *　@　@　@証券会社：管理者.証券会社<BR>
     *　@　@　@部店コード：債券管理者注文約定照会検索条件情報.部店コード<BR>
     *　@　@　@扱者コード：債券管理者注文約定照会検索条件情報.オペレータコード<BR>
     *    検索条件文字列に " and 取引者ID in (?, ?,,,) "を追加する<BR>
     *    検索条件データに　@上記に対応する扱者.扱者ID　@を追加する<BR>
     *<BR>
     *12) 債券管理者注文約定照会検索条件情報.扱者コード(SONAR)!=nullの場合： <BR>
     *    検索条件文字列に " and 扱者コード(SONAR) = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.扱者コード(SONAR)　@を以下の条件で編集し、追加する <BR>
     *    [扱者コード(SONAR)が5桁未満の場合 <BR>
     *    （扱者コード(SONAR).length < 5）] <BR>
     *     ・前”0”詰に編集した扱者コード(SONAR) <BR>
     *    [上記以外の場合] <BR>
     *     ・扱者コード(SONAR) <BR>
     *<BR>
     *13) 債券管理者注文約定照会検索条件情報.管理者コード!=nullの場合： <BR>
     *    検索条件文字列に " and 管理者コード = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.管理者コード　@を追加する <BR>
     *<BR>
     *14） 債券管理者注文約定照会検索条件情報.債券タイプ!=nullの場合： <BR>
     *    検索条件文字列に " and 債券タイプ = ? "を追加する <BR>
     *    検索条件データに　@債券管理者注文約定照会検索条件情報.債券タイプ　@を追加する <BR>
     *<BR>
     *15) 検索条件文字列と検索条件データを検索条件コンテナに設定して、返す <BR> 
     * @@param l_conditionInfo - (債券管理者注文約定照会検索条件情報)<BR>
     * 債券管理者注文約定照会検索条件情報<BR>
     * <BR>
     * @@param l_admin - (管理者)<BR>
     * 管理者<BR>
     * @@return webbroker3.adminorderexecinquiry.message.WEB3AdminBondQueryContainer
     * @@throws WEB3BaseException    
     * @@throws NotFoundException 
     * @@roseuid 44BB23BB03BC
     */
    protected WEB3AdminBondQueryContainer createQueryContainer(
        WEB3AdminORBondExecRefConditionInfo l_conditionInfo,
        WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createQueryContainer(WEB3AdminBondExecRefConditionInfo l_conditionInfo, WEB3Asministrator l_admin)";
        log.entering(STR_METHOD_NAME);
        
        if (l_conditionInfo == null)
        {
        	log.debug(STR_METHOD_NAME + "パラメータ値不正。");
        	log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3Administrator.class +"." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryStringDate = new ArrayList();
        
        Institution l_institution = l_admin.getInstitution();
        
        //1) 部店条件を検索条件文字列に追加する。 
        //　@　@債券管理者注文約定照会検索条件情報.部店コードの要素数分、拡張アカウントマネージャから部店を取得。 
        //　@　@(該当する部店情報が見つからない場合、エラー「該当部店データなし」エラーをスローする) 
        //    検索条件文字列に " 部店ID in (?, ?,,,) "を追加する 
        //    検索条件データに　@上記に対応する部店.部店ID　@を追加する 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        int l_intLength = 0;
        if (l_conditionInfo.branchCode != null && l_conditionInfo.branchCode.length > 0)
        {
            l_intLength = l_conditionInfo.branchCode.length;
            
            String l_strTempBranchId = " branch_id in (";
            Branch l_branch = null;
            for (int i = 0; i < l_intLength; i++ )
            {
                try
                {
                    l_branch = l_accountManager.getBranch(l_institution, l_conditionInfo.branchCode[i]);
                } 
                catch (NotFoundException l_ex)
                {
                    log.error("該当部店データなし", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                l_strTempBranchId += "?, ";
                l_lisQueryStringDate.add(new Long(l_branch.getBranchId()));
            }
            
            l_strTempBranchId = l_strTempBranchId.substring(0, l_strTempBranchId.length() - 2) + ") ";
            
            l_sbQueryString.append(l_strTempBranchId);
        }

        //2)債券管理者注文約定照会検索条件情報.注文ID!=nullの場合：
        //索条件文字列に " and 注文ID = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.注文ID　@を追加する 
        if (l_conditionInfo.id != null)
        {
            l_sbQueryString.append(" and order_id = ?");
            l_lisQueryStringDate.add(l_conditionInfo.id);
        }
     
        //3) 債券管理者注文約定照会検索条件情報.銘柄コード（WEB3）!=nullの場合：
        //銘柄コード（WEB3）で債券銘柄マネージャから債券銘柄を取得。(該当する債券銘柄
        //が見つからない場合、エラー「指定した銘柄コードに合致している銘柄が存在しません。」をスローする) 
        //検索条件文字列に " and 銘柄ID = ? "を追加する 
        //検索条件データに　@債券銘柄.銘柄ID　@を追加する 
        if (l_conditionInfo.productCode != null)
        {
            WEB3BondProductManager l_productManager = 
                (WEB3BondProductManager) l_finApp.getTradingModule(ProductTypeEnum.BOND).getProductManager();
            WEB3BondProduct l_bondProduct = null;
            try
            {
                l_bondProduct = (WEB3BondProduct) l_productManager.getBondProduct(
                    l_institution, l_conditionInfo.productCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("指定した銘柄コードに合致している銘柄が存在しません。 ",l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            l_sbQueryString.append(" and product_id = ?");
            l_lisQueryStringDate.add(new Long(l_bondProduct.getProductId())); 
        }
        
        //4) 債券管理者注文約定照会検索条件情報.顧客コード!=nullの場合： 
        //    管理者注文約定照会データマネージャ.get顧客一覧(証券会社コード, 部店コード, 顧客コード)で顧客一覧を取得。 
        //    (該当する顧客が見つからない場合、エラー「該当する顧客が存在しません。」をスローする) 
        //    [引数] 
        //　@　@　@証券会社コード：管理者.証券会社コード 
        //　@　@　@部店コード：債券管理者注文約定照会検索条件情報.部店コード 
        //　@　@　@顧客コード：債券管理者注文約定照会検索条件情報.顧客コード 
        //    検索条件文字列に " and 口座ID in (?, ?,,,) "を追加する 
        //    検索条件データに　@上記に対応する顧客.口座ID　@を追加する 
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager =
            new WEB3AdminOrderExecuteDataManager();
        if (l_conditionInfo.accountCode != null)
        {
            WEB3GentradeMainAccount[] l_gentradeMainAccount = null;
            try
            {
                l_gentradeMainAccount =l_orderExecuteDataManager.getAccountList(
                    l_institution.getInstitutionCode(),
                    l_conditionInfo.branchCode,
                    l_conditionInfo.accountCode);                
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("該当する顧客が存在しません", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_gentradeMainAccount != null && l_gentradeMainAccount.length > 0)
            {
                int l_intCnt = l_gentradeMainAccount.length;
                
                String l_strTempAccountId = " and account_id in (";
                for (int i = 0; i < l_intCnt; i++)
                {
                    long l_lngAccountId = l_gentradeMainAccount[i].getAccountId();

                    l_strTempAccountId += "?, ";
                    l_lisQueryStringDate.add(new Long(l_lngAccountId));
                }
                
                l_strTempAccountId = l_strTempAccountId.substring(0, l_strTempAccountId.length() - 2) + ") ";
                l_sbQueryString.append(l_strTempAccountId);
            }
        }    
        
        //5) 債券管理者注文約定照会検索条件情報.発注日!=nullの場合：
        //検索条件文字列に " and 発注日 = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.発注日　@を追加する
        if (l_conditionInfo.orderBizDate != null)
        {
            l_sbQueryString.append(" and biz_date = ?");
            String l_strBizDate = WEB3DateUtility.formatDate(l_conditionInfo.orderBizDate, "yyyyMMdd"); 
            l_lisQueryStringDate.add(l_strBizDate);
        }
        
        //6) 債券管理者注文約定照会検索条件情報.約定日!=nullの場合： 
        //検索条件文字列に " and 約定日 = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.約定日　@を追加する
        if (l_conditionInfo.executionUpdateDate != null)
        {
            l_sbQueryString.append(" and exec_date = ?");
            l_lisQueryStringDate.add(l_conditionInfo.executionUpdateDate);
        }
        
       // 7) 債券管理者注文約定照会検索条件情報.注文種別!=nullの場合： 
       //[注文種別＝＝買付　@又は　@注文種別＝＝売却　@の場合] 
       // 　@　@検索条件文字列に " and 注文種別 = ? "を追加する 
       // 　@　@検索条件データに　@債券管理者注文約定照会検索条件情報.注文種別　@を追加する 
       //     検索条件文字列に " and 取引 = ? "を追加する 
       //   　@検索条件データに　@国内仕切取引　@を追加する
       //[注文種別＝＝応募　@の場合] 
       // 　@　@検索条件文字列に " and (（注文種別 = ? "を追加する 
       // 　@　@検索条件データに　@債券買付　@を追加する 
       // 　@　@検索条件文字列に " and 取引 = ?） "を追加する 
       // 　@　@検索条件データに　@募集取引　@を追加する
       //     検索条件文字列に " or 注文種別 = ? )"を追加する 
       //　@   検索条件データに　@国内債券応募注文　@を追加する 
        if (l_conditionInfo.tradingType != null)
        {
            if (WEB3BondTradingTypeListDef.BUY.equals(l_conditionInfo.tradingType) ||
        		WEB3BondTradingTypeListDef.SELL.equals(l_conditionInfo.tradingType))
            {
                l_sbQueryString.append(" and order_type = ?");
                l_lisQueryStringDate.add(l_conditionInfo.tradingType);
                l_sbQueryString.append(" and deal_type = ?");
                l_lisQueryStringDate.add(WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);
            }
            if (WEB3BondTradingTypeListDef.RECRUIT.equals(l_conditionInfo.tradingType))
            {
                l_sbQueryString.append(" and ((order_type = ? ");
                l_lisQueryStringDate.add(WEB3BondTradingTypeListDef.BUY);
                l_sbQueryString.append(" and deal_type = ?) ");
                l_lisQueryStringDate.add(WEB3DealTypeDef.RECRUIT_TRADING);
                l_sbQueryString.append(" or order_type = ? )");
                l_lisQueryStringDate.add(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);
            }
        }
        
        
        //8) 債券管理者注文約定照会検索条件情報.注文約定区分!=nullの場合： 
        //検索条件文字列に " and 注文約定区分 = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.注文約定区分 を追加する 
        if (l_conditionInfo.executionState != null)
        {
            l_sbQueryString.append(" and order_exec_status = ?");
            l_lisQueryStringDate.add(l_conditionInfo.executionState);
        }
        
        //9) 債券管理者注文約定照会検索条件情報.決済区分!=nullの場合： 
        //検索条件文字列に " and 決済区分 = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.決済区分　@を追加する
        if (l_conditionInfo.settleDiv != null)
        {
            l_sbQueryString.append(" and settlement_div = ?");
            l_lisQueryStringDate.add(l_conditionInfo.settleDiv);
        }
        
        //10) 債券管理者注文約定照会検索条件情報.通貨コード!=nullの場合： 
        //検索条件文字列に " and 通貨コード = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.通貨コード　@を追加する
        if (l_conditionInfo.currencyCode != null)
        {
            l_sbQueryString.append(" and currency_code = ?");
            l_lisQueryStringDate.add(l_conditionInfo.currencyCode);
        }
        
        //11) 債券管理者注文約定照会検索条件情報.オペレータコード!=nullの場合：
        //    管理者注文約定照会データマネージャ.get扱者一覧(証券会社, 部店コード, 
        //扱者コード)で扱者一覧を取得。
        //    (該当する扱者が見つからない場合、エラー「扱者が存在しない。」をスローする)
        //    [引数]
        //　@　@　@証券会社：管理者.証券会社
        //　@　@　@部店コード：債券管理者注文約定照会検索条件情報.部店コード
        //　@　@　@扱者コード：債券管理者注文約定照会検索条件情報.オペレータコード
        //    検索条件文字列に " and 取引者ID in (?, ?,,,) "を追加する
        //    検索条件データに　@上記に対応する扱者.扱者ID　@を追加する
        if (l_conditionInfo.operatorCode != null)
        {
            WEB3GentradeTrader[] l_gentradeTrader = null; 
            try
            {
                l_gentradeTrader = l_orderExecuteDataManager.getTraderList(
                    l_institution, 
                    l_conditionInfo.branchCode,
                    l_conditionInfo.operatorCode);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("扱者が存在しない。 ",l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01191,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if (l_gentradeTrader != null && l_gentradeTrader.length > 0)
            {
                String l_strTempTraderId = " and trader_id in(";
                l_intLength = l_gentradeTrader.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    l_strTempTraderId += "?, "; 
                    l_lisQueryStringDate.add(new Long(l_gentradeTrader[i].getTraderId()));
                }
                l_strTempTraderId = l_strTempTraderId.substring(0, l_strTempTraderId.length() - 2) + ") ";
                l_sbQueryString.append(l_strTempTraderId);
            }
        }
        
        //12) 債券管理者注文約定照会検索条件情報.扱者コード(SONAR)!=nullの場合： 
        //検索条件文字列に " and 扱者コード(SONAR) = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.扱者コード(SONAR)　@を以下の条件で編集し、追加する
        //[扱者コード(SONAR)が5桁未満の場合
        //（扱者コード(SONAR).length < 5）]
        // ・前”0”詰に編集した扱者コード(SONAR)
        //[上記以外の場合]
        // ・扱者コード(SONAR)
        if (l_conditionInfo.sonarTraderCode != null)
        {
            l_sbQueryString.append(" and sonar_trader_code = ?");
            
            int l_intSonarTraderCodeLen = 5;
            if (l_conditionInfo.sonarTraderCode.length() < l_intSonarTraderCodeLen)
            {
                StringBuffer l_strSonarTraderCodeFillZero = new StringBuffer();
                for (int i = l_conditionInfo.sonarTraderCode.length(); i < l_intSonarTraderCodeLen; i++)
                {
                    l_strSonarTraderCodeFillZero.append("0");
                }
                l_lisQueryStringDate.add(l_strSonarTraderCodeFillZero + l_conditionInfo.sonarTraderCode);
            }
            else
            {
                l_lisQueryStringDate.add(l_conditionInfo.sonarTraderCode);
            }
        }

        //13) 債券管理者注文約定照会検索条件情報.管理者コード!=nullの場合： 
        //検索条件文字列に " and 管理者コード = ? "を追加する 
        //検索条件データに　@債券管理者注文約定照会検索条件情報.管理者コード　@を追加する
        if (l_conditionInfo.administratorCode != null)
        {
            l_sbQueryString.append(" and administrator_code = ?");
            l_lisQueryStringDate.add(l_conditionInfo.administratorCode);
        }
        
        //14） 債券管理者注文約定照会検索条件情報.債券タイプ!=nullの場合：
        //    検索条件文字列に " and 債券タイプ = ? "を追加する
        //    検索条件データに　@債券管理者注文約定照会検索条件情報.債券タイプ　@を追加する
        if(l_conditionInfo.bondType != null)
        {
            l_sbQueryString.append(" and bond_type = ?");
            l_lisQueryStringDate.add(l_conditionInfo.bondType);
        }
        //15) 検索条件文字列と検索条件データを検索条件コンテナに設定して、返す
        WEB3AdminBondQueryContainer l_adminBondQueryContainer = new WEB3AdminBondQueryContainer();
        String l_strQueryString = l_sbQueryString.toString();
        Object[] l_objQueryStringData = new Object[l_lisQueryStringDate.size()];
        l_lisQueryStringDate.toArray(l_objQueryStringData);
        l_adminBondQueryContainer.setQueryString(l_strQueryString);
        l_adminBondQueryContainer.setQueryData(l_objQueryStringData);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminBondQueryContainer;
    }
}
@
