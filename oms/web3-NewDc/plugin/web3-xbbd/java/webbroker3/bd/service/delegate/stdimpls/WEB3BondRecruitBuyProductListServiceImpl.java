head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondRecruitBuyProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券応募/買付銘柄一覧サービスImpl(WEB3BondRecruitBuyProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/5 郭英 (中訊) 新規作成 
Revesion History : 2007/07/10 周墨洋 (中訊) モデルNo.194
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.define.WEB3BondProductSortKeyItemDef;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;
import webbroker3.bd.message.WEB3BondApplyBuyProductInfo;
import webbroker3.bd.message.WEB3BondApplyBuyProductListRequest;
import webbroker3.bd.message.WEB3BondApplyBuyProductListResponse;
import webbroker3.bd.message.WEB3BondCurrencyInfo;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券応募/買付銘柄一覧サービスImpl)<BR>
 * 債券応募/買付銘柄一覧サービスImpl
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3BondRecruitBuyProductListServiceImpl 
    extends WEB3BondClientRequestService implements WEB3BondRecruitBuyProductListService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyProductListServiceImpl.class);   
    
    /**
     * @@roseuid 44FBFD3A00BB
     */
    public WEB3BondRecruitBuyProductListServiceImpl() 
    {
     
    }
    
    /**
     * 債券応募/買付銘柄一覧サービス処理を実施する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「(債券)応募/買付銘柄一覧」参照。
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B7410C020B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (!(l_request instanceof WEB3BondApplyBuyProductListRequest))
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3BondApplyBuyProductListRequest l_listRequest = (WEB3BondApplyBuyProductListRequest) l_request;
        
        //1.1: validate( )
        l_listRequest.validate();
        
        //1.2: validate注文受付可能( )  
        WEB3BondTradingTimeManagement.validateOrderAccept();
        
        //1.3: get補助口座( )
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.4:create検索条件文字列(String, String)
        String l_strQueryString = this.createQueryString(
            l_listRequest.referenceType, 
            l_listRequest.currencyCode);

        //1.5: create検索条件データコンテナ(String, String)
        Object[] l_objsQueryContainer = this.createQueryDataContainer(
            l_listRequest.referenceType,
            l_listRequest.currencyCode);
        
        //1.6: createソート条件文字列( )
        String l_strSortCond = this.createSortCond();
        
        //1.7: get債券銘柄リスト(String, String, Object[], String)
        FinApp l_finapp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productMgr = 
            (WEB3BondProductManager) l_tradingModule.getProductManager();

        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

        List l_lisProducts = l_productMgr.getBondProductList(
            l_strInstitutionCode,
            l_strQueryString,
            l_objsQueryContainer,
            l_strSortCond);
        
        //1.8: get債券銘柄リスト()の戻り値の要素数分Loop処理
        int l_intLoopCnt = 0;
        if (l_lisProducts != null && !l_lisProducts.isEmpty())
        {
            l_intLoopCnt = l_lisProducts.size();
        }
        
        List l_lisCurrencyInfos = new ArrayList();
        List l_lisCurrencyCodes = new ArrayList();
        for (int i = 0; i < l_intLoopCnt; i++)
        {
            WEB3BondProduct l_product = (WEB3BondProduct) l_productMgr.toProduct(
                (BondProductRow) l_lisProducts.get(i));
            
            //1.8.1: is外貨建( )
            boolean l_blnIsForeignCurrency = l_product.isForeignCurrency();
            
            //1.8.2: ＜分岐処理＞is外貨建()の戻り値 == true の場合
            if (l_blnIsForeignCurrency)
            {
                //1.8.2.1: get通貨コード( )
                String l_strCurrencyCode = l_product.getCurrencyCode();
                
                boolean l_blnIsDeal = false;
                if (i != 0)
                {
                    if (!l_lisCurrencyCodes.contains(l_strCurrencyCode))
                    {
                        l_lisCurrencyCodes.add(l_strCurrencyCode);
                        l_blnIsDeal = true;
                    }
                }
                else
                {
                    l_lisCurrencyCodes.add(l_strCurrencyCode);
                    l_blnIsDeal = true;
                }
                
                //1.8.2.2: get通貨コード()の戻り値が、作成した通貨情報.通貨コードと重複しない場合
                if (l_blnIsDeal)
                {
                    //1.8.2.2.1: （共通）通貨(証券会社コード : String, 通貨コード : String)
                    WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                        l_strInstitutionCode, 
                        l_strCurrencyCode);
                    
                    
                    //1.8.2.2.2: get為替レート(is買付 : boolean, is約定計算 : boolean, 入力為替レート : double)
                    double l_dblExchangeRate = l_currency.getExchangeRate(true, false, 0);
                    
                    //1.8.2.2.3:通貨情報( )
                    WEB3BondCurrencyInfo l_currencyInfo = new WEB3BondCurrencyInfo();
                    
                    //1.8.2.2.4: プロパティ・セット
                    l_currencyInfo.currencyCode = l_currency.getCurrencyCode();
                    l_currencyInfo.fxRate = WEB3StringTypeUtility.formatNumber(l_dblExchangeRate);
                    
                    l_lisCurrencyInfos.add(l_currencyInfo);                    
                }                
            }            
        }
        
        //1.9: WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisProducts,
            Integer.parseInt(l_listRequest.pageIndex),
            Integer.parseInt(l_listRequest.pageSize));
        
        //1.10: getArrayReturned(l_classType : Class)
        BondProductRow[] l_productRows = 
            (BondProductRow[]) l_pageIndexInfo.getArrayReturned(BondProductRow.class);
        
        //1.11: getArrayReturned()の戻り値の要素数分Loop処理
        int l_intReturnCnt = 0;
        WEB3BondApplyBuyProductInfo[] l_applyBuyProductInfos = null;
        if (l_productRows != null && l_productRows.length > 0)
        {
            l_intReturnCnt = l_productRows.length;
            l_applyBuyProductInfos = new WEB3BondApplyBuyProductInfo[l_intReturnCnt];
        }
        
        for(int i = 0; i < l_intReturnCnt; i++)
        {
            BondProductRow l_productRow = l_productRows[i];
            
            //1.11.1: get売買区分( )
            String l_strTradeType = l_productRow.getTradeType();
            
            //1.11.2: 債券応募/買付銘柄情報( )
            l_applyBuyProductInfos[i] = new WEB3BondApplyBuyProductInfo();
            
            //1.11.3: プロパティ・セット
            //銘柄ID     = 債券銘柄.銘柄ID
            l_applyBuyProductInfos[i].productId = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getProductId());
            
            //銘柄名       = 債券銘柄.銘柄名
            l_applyBuyProductInfos[i].productName = l_productRow.getProductName();
            
            //種別コード     = 債券銘柄.種別コード
            l_applyBuyProductInfos[i].bondCategCode = l_productRow.getBondCategCode();
            
            //S&P       = 債券銘柄.S&P
            l_applyBuyProductInfos[i].sAndP = l_productRow.getSAndP();
            
            //Moody's       = 債券銘柄.MOODY'S
            l_applyBuyProductInfos[i].moodys = l_productRow.getMoodys();
            
            //利率        = 債券銘柄.利率
            l_applyBuyProductInfos[i].coupon = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getCoupon());
            
            //年間利払回数    = 債券銘柄.年間利払回数
            l_applyBuyProductInfos[i].yearlyInterestPayments = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getYearlyInterestPayments());
            
            //利払日1      = 債券銘柄.利払日1
            l_applyBuyProductInfos[i].interestPaymentDay1 = l_productRow.getInterestPaymentDay1st();
            
            //利払日2      = 債券銘柄.利払日2
            l_applyBuyProductInfos[i].interestPaymentDay2 = l_productRow.getInterestPaymentDay2nd();
            
            //通貨コード     = 債券銘柄.通貨コード
            l_applyBuyProductInfos[i].currencyCode = l_productRow.getCurrencyCode();
            
            //申込単位      = 債券銘柄.申込単位
            l_applyBuyProductInfos[i].tradeUnit = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getTradeUnit());
            
            //最低申込数量    = 債券銘柄.最低額面
            l_applyBuyProductInfos[i].minOrderQuantity = 
                WEB3StringTypeUtility.formatNumber(l_productRow.getMinFaceAmount());
            
            //最高申込数量    = 債券銘柄.最高額面
            if (!l_productRow.getMaxFaceAmountIsNull())
            {
                l_applyBuyProductInfos[i].maxOrderQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_productRow.getMaxFaceAmount());
            }
            
            //応募開始日 = 債券銘柄.応募開始日(*1)
            //応募終了日 = 債券銘柄.応募終了日(*1)
            //(*1)get売買区分()の戻り値 == ”応募”の場合のみセットする。それ以外の場合、null。
            //取引可能区分    = get売買区分()の戻り値 == ”応募”and 債券銘柄.is応募可能() == true の場合、”応募”（取引可能区分）
            //get売買区分()の戻り値 == ”買付” or ”買付/売却”の場合、”買付”（取引可能区分）
            if (WEB3BondTradeTypeDef.RECRUIT.equals(l_strTradeType))
            {
                l_applyBuyProductInfos[i].recruitStartDate = l_productRow.getRecruitStartDate();
                l_applyBuyProductInfos[i].recruitEndDate = l_productRow.getRecruitEndDate();

                WEB3BondProduct l_product = (WEB3BondProduct) l_productMgr.toProduct(
                        (BondProductRow) l_productRow);

                if (l_product.isRecruitPossible())
                {
                    l_applyBuyProductInfos[i].posibleDiv = WEB3BondDealDivDef.RECRUIT;
                }
            }
            else if (WEB3BondTradeTypeDef.BUY.equals(l_strTradeType) ||
                WEB3BondTradeTypeDef.BUY_SELL.equals(l_strTradeType))
            {
                l_applyBuyProductInfos[i].recruitStartDate = null;
                l_applyBuyProductInfos[i].recruitEndDate = null;
                l_applyBuyProductInfos[i].posibleDiv = WEB3BondDealDivDef.BUY;
            }  
            else
            {
                l_applyBuyProductInfos[i].recruitStartDate = null;
                l_applyBuyProductInfos[i].recruitEndDate = null;
            }
            
            //買付単価      = 債券銘柄.買付単価
            if (!l_productRow.getBuyPriceIsNull())
            {
                l_applyBuyProductInfos[i].buyPrice = 
                    WEB3StringTypeUtility.formatNumber(l_productRow.getBuyPrice());
            }
            
            //発行日       = 債券銘柄.発行日
            l_applyBuyProductInfos[i].issueDate = l_productRow.getIssueDate();
            
            //償還日       = 債券銘柄.償還日
            l_applyBuyProductInfos[i].maturityDate = l_productRow.getMaturityDate();     
        }
        
        //1.12: getPageIndex( )
        String l_strPageIndex = 
            WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
        
        //1.13: getTotalPages( )
        String l_strTotalPages = 
            WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalPages());
        
        //1.14: getTotalRecords( )
        String l_strTotalRecords = 
            WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalRecords());
        
        //1.15:createResponse( )
        WEB3BondApplyBuyProductListResponse l_response = 
            (WEB3BondApplyBuyProductListResponse) l_listRequest.createResponse();
        
        //1.16:プロパティ・セット
        //通貨情報一覧        = 通貨情報の配列
        WEB3BondCurrencyInfo[] l_currencyInfos = null;
        if (!l_lisCurrencyInfos.isEmpty())
        {
            l_currencyInfos = new WEB3BondCurrencyInfo[l_lisCurrencyInfos.size()];
            l_lisCurrencyInfos.toArray(l_currencyInfos);
        }
        l_response.currencyList = l_currencyInfos;
        
        //応募/買付情報一覧     = 債券応募/買付銘柄情報の配列
        l_response.productList = l_applyBuyProductInfos;
        
        //表示ページ番号       = getPageIndex()の戻り値
        l_response.pageIndex = l_strPageIndex;
        
        //総ページ数     = getTotalPages()の戻り値
        l_response.totalPages = l_strTotalPages;
        
        //総レコード数        = getTotalRecords()の戻
        l_response.totalRecords = l_strTotalRecords;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を作成する。 <BR>
     * <BR>
     * (1)以下の文字列を作成する。<BR>
     * <BR>
     * 　@" 取扱区分 = ? and 取扱開始日時 <= ? and 取扱終了日時 > ?  and 債券タイプ = ? "<BR>
     * <BR>
     * (2)引数.照会区分 == ”応募一覧”の場合、以下の文字列を最後尾に追加する。 <BR>
     * <BR>
     * 　@" and 売買区分 = ? " <BR>
     * <BR>
     * (3)引数.照会区分 == ”買付一覧” の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 売買区分 in (?, ?) " <BR>
     * <BR>
     * (4)引数.照会区分 == ”応募/買付一覧” の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@" and 売買区分 in (?, ?, ?) " <BR>
     * <BR>
     * (5)引数.通貨コード ≠ null の場合、以下の文字列を最後尾に追加する。<BR>
     * <BR>
     * 　@(5-1)引数.通貨コード == "T0" の場合<BR>
     * <BR>
     * 　@　@　@　@" and ( 通貨コード is null or 通貨コード = ? ) "<BR>
     * <BR>
     * 　@(5-2)それ以外の場合<BR>
     * <BR>
     * 　@　@　@　@" and 通貨コード = ? "<BR>
     * <BR>
     * (6)作成した文字列を返却する。<BR>
     * @@param l_strReferenceType - (照会区分)<BR>
     * 照会区分
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード
     * @@return String
     * @@roseuid 44B7669A00F2
     */
    protected String createQueryString(String l_strReferenceType, String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = " createQueryString(String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strReturn = "";
        
        //(1)以下の文字列を作成する。 
        //　@" 取扱区分 = ? and 取扱開始日時 <= ? and 取扱終了日時 > ?  and 債券タイプ = ? " 
        l_strReturn = " trade_handle_div = ? and trade_start_date <= ? and trade_end_date > ? and bond_type = ? ";
        
        //(2)引数.照会区分 == ”応募一覧”の場合、以下の文字列を最後尾に追加する。  
        //　@" and 売買区分 = ? "  
        if (WEB3BondReferenceTypeDef.RECRUIT_LIST.equals(l_strReferenceType))
        {
            l_strReturn += " and trade_type = ?"; 
        }           
        //(3)引数.照会区分 == ”買付一覧” の場合、以下の文字列を最後尾に追加する。  
        //　@" and 売買区分 in (?, ?) "  
        else if (WEB3BondReferenceTypeDef.BUY_LIST.equals(l_strReferenceType))
        {
            l_strReturn += " and trade_type in (?, ?)"; 
        }         
        //(4)引数.照会区分 == ”応募/買付一覧” の場合、以下の文字列を最後尾に追加する。 
        //　@" and 売買区分 in (?, ?, ?) "  
        else if (WEB3BondReferenceTypeDef.RECRUIT_BUY_LIST.equals(l_strReferenceType))
        {
            l_strReturn += " and trade_type in (?, ?, ?)"; 
        }
        
        //(5)引数.通貨コード ≠ null の場合、以下の文字列を最後尾に追加する。 
        if (l_strCurrencyCode != null)
        {
            //　@(5-1)引数.通貨コード == "T0" の場合 
            //　@　@　@　@" and ( 通貨コード is null or 通貨コード = ? ) " 
            if (WEB3GentradeCurrencyCodeDef.JPY.equals(l_strCurrencyCode))
            {
                l_strReturn += " and (currency_code is null or currency_code = ?)";
            }
            //　@(5-2)それ以外の場合 
            //　@　@　@　@" and 通貨コード = ? " 
            else                
            {
                l_strReturn += " and currency_code = ?";
            }
        }

        //(6)作成した文字列を返却する。 
        log.exiting(STR_METHOD_NAME);
        return l_strReturn + " ";
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データをObjectの配列に設定しレスポンスする。<BR>
     * <BR>
     * (1)Objectの配列を作成する。<BR>
     * <BR>
     * (2)現在日時を取得する。<BR>
     * <BR>
     * (3)(1)で作成した配列に以下をセットする。<BR>
     * <BR>
     * 　@　@①@"管理者/顧客"（取扱区分）<BR>
     * 　@　@②(2)で取得した現在日時<BR>
     * 　@　@③(2)で取得した現在日時<BR>
     * 　@　@④"外国債券"（債券タイプ）<BR>
     * <BR>
     * (3)引数.照会区分 == ”応募一覧” の場合 、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@⑤"応募"（売買区分）<BR>
     * <BR>
     * (4)引数.照会区分 == ”買付一覧” の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@⑤"買付"(売買区分)<BR>
     * 　@　@⑥"買付/売却"（売買区分）<BR>
     * <BR>
     * (5)引数.照会区分 == ”応募/買付一覧” の場合 、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@⑤"応募"（売買区分）<BR>
     * 　@　@⑥"買付"(売買区分)<BR>
     * 　@　@⑦"買付/売却"（売買区分）<BR>
     * <BR>
     * (5)引数.通貨コード ≠ null の場合、配列に以下を追加する。<BR>
     * <BR>
     * 　@　@引数.通貨コード<BR>
     * <BR>
     * (6)作成した配列を返却する。<BR>
     * @@param l_strReferenceType - (照会区分)<BR>
     * 照会区分
     * @@param l_strCurrencyCode - (通貨コード)<BR>
     * 通貨コード
     * @@return Object[]
     * @@roseuid 44BC4B3000AB
     */
    protected Object[] createQueryDataContainer(String l_strReferenceType, String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //(1)Objectの配列を作成する。 
        List l_lisContainer = new ArrayList();
        
        //(2)現在日時を取得する。 
        Timestamp l_tsNewTime = GtlUtils.getSystemTimestamp();
        
        //(3)(1)で作成した配列に以下をセットする。 
        //　@　@①@"管理者/顧客"（取扱区分） 
        //　@　@②(2)で取得した現在日時 
        //　@　@③(2)で取得した現在日時 
        //　@　@④"外国債券"（債券タイプ）
        l_lisContainer.add(WEB3TradeHandleDivDef.MANAGER_CUSTOMER);
        l_lisContainer.add(l_tsNewTime);
        l_lisContainer.add(l_tsNewTime);
        l_lisContainer.add(BondTypeEnum.FOREIGN_BOND);
        
        //(3)引数.照会区分 == ”応募一覧” の場合 、配列に以下を追加する。 
        //　@　@⑤"応募"（売買区分） 
        if (WEB3BondReferenceTypeDef.RECRUIT_LIST.equals(l_strReferenceType))
        {
            l_lisContainer.add(WEB3BondTradeTypeDef.RECRUIT);
        }        
        //(4)引数.照会区分 == ”買付一覧” の場合、配列に以下を追加する。 
        //
        //　@　@⑤"買付"(売買区分) 
        //　@　@⑥"買付/売却"（売買区分） 
        else if (WEB3BondReferenceTypeDef.BUY_LIST.equals(l_strReferenceType))
        {
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY);
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY_SELL);
        }        
        //(5)引数.照会区分 == ”応募/買付一覧” の場合 、配列に以下を追加する。 
        //　@　@⑤"応募"（売買区分） 
        //　@　@⑥"買付"(売買区分) 
        //　@　@⑦"買付/売却"（売買区分） 
        else if (WEB3BondReferenceTypeDef.RECRUIT_BUY_LIST.equals(l_strReferenceType))
        {
            l_lisContainer.add(WEB3BondTradeTypeDef.RECRUIT);
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY);
            l_lisContainer.add(WEB3BondTradeTypeDef.BUY_SELL);
        } 
        //(5)引数.通貨コード ≠ null の場合、配列に以下を追加する。 
        //
        //　@　@引数.通貨コード 
        if (l_strCurrencyCode != null)
        {
            l_lisContainer.add(l_strCurrencyCode);
        }
            
        //(6)作成した配列を返却する。
        Object[] l_objReturns = new Object[l_lisContainer.size()];
        l_lisContainer.toArray(l_objReturns);
        
        log.exiting(STR_METHOD_NAME);
        return l_objReturns;
    }
    
    /**
     * (createソート条件文字列)<BR>
     * ソート条件文字列を作成する。<BR>
     * <BR>
     * 以下の条件でソート条件文字列を作成し、作成した文字列を返却する。<BR>
     * <BR>
     * [ソート条件]<BR>
     *  "種別コード"の昇順、"銘柄コード（SONAR）"の昇順、"回号コード(SONAR)"の昇順<BR>
     * <BR>
     * ※債券銘柄の同項目名。
     * @@return String
     * @@roseuid 44BDB73F034B
     */
    protected String createSortCond() 
    {
        final String STR_METHOD_NAME = " createSortCond()";
        log.entering(STR_METHOD_NAME);
        
        //以下の条件でソート条件文字列を作成し、作成した文字列を返却する。 
        //[ソート条件] 
        // "種別コード"の昇順、"銘柄コード（SONAR）"の昇順、"回号コード(SONAR)"の昇順 
        String l_strSortCond = " " + WEB3BondProductSortKeyItemDef.BOND_CATEG_CODE + " " + "asc" +
            ", " + WEB3BondProductSortKeyItemDef.HOST_PRODUCT_CODE + " " + "asc" + 
            ", " + WEB3BondProductSortKeyItemDef.HOST_PRODUCT_ISSUE_CODE + " " + "asc ";
        
        //※債券銘柄の同項目名。    
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
}
@
