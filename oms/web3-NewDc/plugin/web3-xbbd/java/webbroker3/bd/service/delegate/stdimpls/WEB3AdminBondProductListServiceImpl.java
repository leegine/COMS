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
filename	WEB3AdminBondProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者債券銘柄一覧サービスImpl(WEB3AdminBondProductListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 何文敏 (中訊) 新規作成
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TradeHandleDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.WEB3AdminBondQueryContainer;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.define.WEB3BondProductSearchKeyItemDivDef;
import webbroker3.bd.message.WEB3AdminBondProductConditionUnit;
import webbroker3.bd.message.WEB3AdminBondProductListConditionInfo;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchListRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchListResponse;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.bd.service.delegate.WEB3AdminBondProductListService;

/**
 * (管理者債券銘柄一覧サービスImpl)<BR>
 * 管理者債券　@銘柄一覧サービス実装クラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0 
 */
public class WEB3AdminBondProductListServiceImpl implements WEB3AdminBondProductListService 
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductListServiceImpl.class);
    
    /**
     * @@roseuid 44E3362E006D
     */
    public WEB3AdminBondProductListServiceImpl() 
    {
     
    }
    
    /**
     * 管理者債券銘柄一覧照会を実施する。 <BR>
     * <BR>
     * ｼｰｹﾝｽ図「（債券）銘柄一覧execute」参照<BR>
     * @@param l_request - 上り処理用リクエストの基底クラス。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44BD81120218
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);  
        
        //1.execute(WEB3GenRequest)
        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        WEB3GenResponse l_response = null;
        
        //1.1 l_requestが債券管理者銘柄一覧画面表示リクエストの場合
        // get銘柄一覧検索条件(管理者債券銘柄一覧画面表示リクエスト)
        if (l_request instanceof WEB3AdminBondProductSearchInputRequest)
        {
            l_response = getProductSearchListCondition(
                (WEB3AdminBondProductSearchInputRequest) l_request);
        }
        
        //1.2 l_requestが債券管理者銘柄一覧検索リクエスト
        //search銘柄一覧(管理者債券銘柄一覧検索リクエスト)
        else if (l_request instanceof WEB3AdminBondProductSearchListRequest)
        {
            l_response = searchProductList(
                (WEB3AdminBondProductSearchListRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get銘柄一覧検索条件)<BR>
     * 債券管理者銘柄一覧入力処理を行う。 <BR>
     * <BR>
     * シーケンス図「（債券）get銘柄一覧」参照<BR>
     * --------------------------------------------------<BR>
     * @@param l_request - 債券管理者銘柄一覧画面表示リクエスト<BR>
     * @@return WEB3AdminBondProductSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B600D103B1
     */
    protected WEB3AdminBondProductSearchInputResponse getProductSearchListCondition(
        WEB3AdminBondProductSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getProductSearchListCondition(WEB3AdminBondProductSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();     
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
        
        //1.2 validate権限(String, boolean)
        //[validate権限()に指定する引数]  
        // 機@能カテゴリコード：　@機@能カテゴリコード.債券銘柄管理 
        // is更新：　@false 
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);

        //1.3 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.4 get取扱可能種別コード一覧(String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
            (WEB3BondProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getProductManager();

        String[] l_strHandlingPossibleTypeCodeList =
           l_bondProductManager.getTradeHandlingPossibleBondCategCodeList(l_strInstitutionCode);
        
        //1.5 get通貨コード一覧(証券会社コード : String)
        String[] l_strCurrencyCodes = 
            WEB3GentradeCurrency.getCurrencyCodeList(l_strInstitutionCode);
        
        //1.6 createレスポンス( )
        WEB3AdminBondProductSearchInputResponse l_response = 
            (WEB3AdminBondProductSearchInputResponse) l_request.createResponse();
        
        //1.7 プロパティセット
        //種別コード一覧
        l_response.bondKindCodeList = l_strHandlingPossibleTypeCodeList;
            
        //通貨コード一覧
        l_response.currencyCodeList = l_strCurrencyCodes;
        
        //取扱区分一覧
        String[] l_strTradeHandleDivList = {
            WEB3TradeHandleDivDef.DISABLED,
            WEB3TradeHandleDivDef.MANAGER,
            WEB3TradeHandleDivDef.MANAGER_CUSTOMER};
        l_response.tradeHandleDivList = l_strTradeHandleDivList;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (search銘柄一覧)<BR>
     * 債券管理者銘柄一覧検索処理を行う。 <BR>
     * <BR>
     * シーケンス図「（債券）search銘柄一覧」参照<BR>
     * --------------------------------------------------<BR>
     * @@param ｌ_request - 債券管理者銘柄一覧検索リクエスト<BR>
     * @@return WEB3AdminBondProductSearchListResponse
     * @@throws WEB3BaseException
     * @@roseuid 44B600D50305
     */
    protected WEB3AdminBondProductSearchListResponse searchProductList(
        WEB3AdminBondProductSearchListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "searchProductList(WEB3AdminBondProductSearchListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
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
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限()に指定する引数]  
        // 機@能カテゴリコード：　@機@能カテゴリコード.債券銘柄管理 
        // is更新：　@false
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.BOND_PRODUCT_MANAGE, false);
        
        //1.4 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 create検索条件(管理者債券銘柄一覧検索条件)
        WEB3AdminBondQueryContainer l_creatQuetyCond = 
            (WEB3AdminBondQueryContainer)createQueryContainer(l_request.conditionInfo);
               
        //1.6 createソート条件(債券ソートキー[])
        String l_strCreatSortCond = this.createSortCond(l_request.sortKeys);
        
        //1.7 get債券銘柄リスト(String, String, Object[], String)
        //[引数] 
        // 証券会社コード：get証券会社コード()の戻り値 
        //検索条件文字列：検索条件コンテナ.get検索条件文字列()の戻り値
        //検索条件データコンテナ：検索条件コンテナ.get検索データ配列()の戻り値
        // ソート条件文字列：createソート条件()の戻り値

        String l_strGetQueryString = l_creatQuetyCond.getQueryString();
        Object[] l_objGetQueryStringData = l_creatQuetyCond.getQueryData();
         
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_productManager = 
             (WEB3BondProductManager) l_finApp.getTradingModule(
                 ProductTypeEnum.BOND).getProductManager();
         
        List l_list =
            l_productManager.getBondProductList(
                l_strInstitutionCode, 
                l_strGetQueryString, 
                l_objGetQueryStringData,
                l_strCreatSortCond);
        
        //1.8 create債券銘柄照会情報一覧(論理ビュー::List)
        WEB3AdminBondProductConditionUnit[] l_bondProductReferenceInfoList =
            this.createBondProductReferenceInfoList(l_list);
        
        int l_intRequestPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intRequestPageSize = Integer.parseInt(l_request.pageSize);
        
        //1.9 createレスポンス( )
        WEB3AdminBondProductSearchListResponse l_response =
            (WEB3AdminBondProductSearchListResponse) l_request.createResponse();
 
        //1.10 WEB3PageIndexInfo(l_objs : 論理ビュー::java::lang::Object[], 
        //     l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_bondProductReferenceInfoList, 
                l_intRequestPageIndex, 
                l_intRequestPageSize);
       
        //1.11 getTotalPages( )
        int l_intGetTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.12 getTotalRecords( )
        int l_intGetTotalRecords = l_pageIndexInfo.getTotalRecords();
        
        //1.13 getPageIndex( )
        int l_intGetPageIndex = l_pageIndexInfo.getPageIndex();
        
        //1.14 getArrayReturned( )
        WEB3AdminBondProductConditionUnit[] l_productReferenceInfoList = 
            (WEB3AdminBondProductConditionUnit[])l_pageIndexInfo.getArrayReturned(
                WEB3AdminBondProductConditionUnit.class);

        //1.15 プロパティセット
        //総ページ数
        l_response.totalPages = l_intGetTotalPages + "";        
       
        //表示ページ番号
        l_response.pageIndex = l_intGetPageIndex + "";     
        
        //総レコード数
        l_response.totalRecords = l_intGetTotalRecords + "";
        
        //債券銘柄照会情報一覧
        if (l_intGetTotalPages == 0)
        {
            l_response.conditionList = null;
        }
        else
        {
            l_response.conditionList = l_productReferenceInfoList;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createソート条件)<BR>
     * ソート条件を文字列変換する。 <BR>
     * <BR>
     * <BR>
     * (1)引数.ソートキー.キー項目の数分、対応するテーブルの列物理名を<BR>
     * 　@　@　@昇順／降順指定を付加しソート条件文字列を作成する。 <BR>
     * <BR>
     * 　@　@・キー項目とテーブルの列名称との対応は以下の通り <BR>
     * 　@　@　@　@　@　@・銘柄コード（WEB3）：①@債券銘柄テーブル．通貨コード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@②債券銘柄テーブル．銘柄コード（SONAR)<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@③債券銘柄テーブル．回号コード（SONAR)<BR>
     * 　@　@　@　@　@　@・発行日　@　@　@　@　@　@　@：債券銘柄テーブル．発行日 <BR>
     * 　@　@　@　@　@　@・償還日　@　@　@　@　@　@　@：債券銘柄テーブル．償還日 <BR>
     * <BR>
     * 　@　@・昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定 <BR>
     * <BR>
     * <BR>
     * (2)作成したソート条件文字列を返却する。<BR>
     * <BR>
     * ※キー項目文字列（シンボル名）は、メッセージ定義書を参照 <BR>
     * ※テーブル名：債券銘柄テーブル <BR>
     * ※テーブルの列物理名は、テーブルレイアウトを参照 <BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * 債券ソートキーの配列<BR>
     * @@throws throws WEB3BaseException
     * @@return String
     * @@roseuid 44C444F301C4
     */
    protected String createSortCond(WEB3BondSortKey[] l_sortKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createSortCond(WEB3BondSortKey[] l_sortKey)";
        log.entering(STR_METHOD_NAME);

        //(1)引数.ソートキー.キー項目の数分、対応するテーブルの列物理名を
        // 　@　@　@昇順／降順指定を付加しソート条件文字列を作成する。 
        if (l_sortKey == null || l_sortKey.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータが未指定(null)です。");
        }

        StringBuffer l_sbStringBuffer = new StringBuffer();        
        int l_intCnt = l_sortKey.length;
        
        //キー項目とテーブルの列名称との対応は以下の通り 
        //銘柄コード（WEB3）：①@債券銘柄テーブル．通貨コード
        //                   ②債券銘柄テーブル．銘柄コード（SONAR)
        //　@　@　@　@　@　@　@　@　@  ③債券銘柄テーブル．回号コード（SONAR)
        //発行日　@　@　@　@　@　@：債券銘柄テーブル．発行日 
        //償還日　@　@　@　@　@　@：債券銘柄テーブル．償還日 
        //昇順／降順指定は、ソートキー.昇順／降順 指定に従い設定 
        for (int i = 0; i < l_intCnt; i++)
        {
            String l_strKeyItem = l_sortKey[i].keyItem;
            String l_strAscDesc = l_sortKey[i].ascDesc;
            
            if (WEB3BondProductSearchKeyItemDivDef.PRODUCT_CODE .equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_sbStringBuffer.append("currency_code asc, ");
                    l_sbStringBuffer.append("host_product_code asc, ");
                    l_sbStringBuffer.append("host_product_issue_code asc, ");
                }
                else
                {
                    l_sbStringBuffer.append("currency_code desc, ");
                    l_sbStringBuffer.append("host_product_code desc, ");
                    l_sbStringBuffer.append("host_product_issue_code desc, ");
                }
            }
            else if (WEB3BondProductSearchKeyItemDivDef.ISSUE_DATE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_sbStringBuffer.append("issue_date asc, ");
                }
                else
                {
                    l_sbStringBuffer.append("issue_date desc, ");
                }
            }
            else if (WEB3BondProductSearchKeyItemDivDef.MATURITY_DATE.equals(l_strKeyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
                {
                    l_sbStringBuffer.append("maturity_date asc, ");
                }
                else
                {
                    l_sbStringBuffer.append("maturity_date desc, ");
                }                    
            }
        }
        
        String l_strOrderBy = "";
        if (l_sbStringBuffer.length() > 0)
        {
            l_strOrderBy = l_sbStringBuffer.toString();
            l_strOrderBy = " " + l_strOrderBy.substring(0, l_strOrderBy.length() - 2) + " ";
        }
           
        log.exiting(STR_METHOD_NAME);
        return l_strOrderBy;     
    }
              
    /**
     * (create検索条件)<BR>
     * 債券銘柄一覧の検索を実施する為の検索条件コンテナを作成して返却する。 <BR>
     * <BR>
     * １）引数の債券タイプ!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and 債券タイプ = ? "　@を追加する<BR>
     * 　@検索条件データに　@引数の債券タイプ　@を追加する<BR>
     * <BR>
     * ２）引数の種別コード!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and 種別コード = ? " を追加する<BR>
     * 　@検索条件データに　@引数の種別コード　@を追加する<BR>
     * <BR>
     * ３）引数の銘柄コード(WEB3)!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and　@銘柄コード(WEB3) = ? " を追加する<BR>
     * 　@検索条件データに　@引数の銘柄コード(WEB3) を追加する<BR>
     * <BR>
     * ４）引数の発行日!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and 発行日 = ?"　@追加する<BR>
     * 　@検索条件データに　@引数の発行日　@を追加する<BR>
     * <BR>
     * ５）引数の償還日!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and 償還日 = ? "　@追加する<BR>
     * 　@検索条件データに　@引数の償還日　@を追加する<BR>
     * <BR>
     * ６）引数の利払日!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and ( 利払日1 = ?　@or 利払日2 = ? ) "　@追加する<BR>
     * 　@検索条件データに　@引数の利払日の値　@を　@二回　@追加する。<BR>
     * 　@(利払日１と利払日2の両方を検索するため)<BR>
     * <BR>
     * ７）引数の通貨コード!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and 通貨コード =　@? " を追加する<BR>
     * 　@検索条件データに　@引数の通貨コード 　@を追加する<BR>
     * <BR>
     * ８）引数の取扱区分!=nullの場合：<BR>
     * 　@検索条件文字列に　@" and 取扱区分 =　@? "　@追加する<BR>
     * 　@検索条件データに　@引数の取扱区分　@を追加する<BR>
     * <BR>
     * 上記作成した検索条件文字列と検索条件データを検索条件コンテナに設定し、返却する。<BR>
     * @@param l_bondProductListConditionInfo - (債券管理者銘柄一覧検索条件)<BR>
     * リクエストの債券管理者銘柄一覧検索条件<BR>
     * @@return WEB3AdminBondQueryContainer
     * @@roseuid 44C6F0B100F7
     */
    protected WEB3AdminBondQueryContainer createQueryContainer(
        WEB3AdminBondProductListConditionInfo l_bondProductListConditionInfo) 
    {
        final String STR_METHOD_NAME = 
            "createQueryContainer(" +
            "WEB3AdminBondExecRefConditionInfo l_bondProductListConditionInfo)";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_sbQueryString = new StringBuffer();
        List l_lisQueryStringDate = new ArrayList();
        
        if (l_bondProductListConditionInfo != null)
        {
            //１）引数の債券タイプ!=nullの場合：
            if (l_bondProductListConditionInfo.bondType != null)
            {
                //検索条件文字列に　@" and 債券タイプ = ? "　@を追加する 
                l_sbQueryString.append(" and bond_type = ? ");
                //検索条件データに　@引数の債券タイプ　@を追加する 
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.bondType);
            }
            
            //２）引数の種別コード!=nullの場合： 
            //検索条件文字列に　@" and 種別コード = ? " を追加する 
            //検索条件データに　@引数の種別コード　@を追加する 
            if (l_bondProductListConditionInfo.bondCategCode != null)
            {
                l_sbQueryString.append(" and bond_categ_code = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.bondCategCode);
            }
            
            //３）引数の銘柄コード(WEB3)!=nullの場合： 
            //検索条件文字列に　@" and　@銘柄コード(WEB3) = ? " を追加する 
            //検索条件データに　@引数の銘柄コード(WEB3) を追加する 
            if (l_bondProductListConditionInfo.productCode != null)
            {
                l_sbQueryString.append(" and product_code = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.productCode);
            }
            
            //４）引数の発行日!=nullの場合： 
            //検索条件文字列に　@" and 発行日 = ?"　@追加する 
            //検索条件データに　@引数の発行日　@を追加する 
            if (l_bondProductListConditionInfo.issueDate != null)
            {
                l_sbQueryString.append(" and issue_date = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.issueDate);
            }
            
            //５）引数の償還日!=nullの場合： 
            //検索条件文字列に　@" and 償還日 = ? "　@追加する 
            //検索条件データに　@引数の償還日　@を追加する
            if (l_bondProductListConditionInfo.maturityDate != null)
            {
                l_sbQueryString.append(" and  maturity_date = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.maturityDate);
            }  
            
            //６）引数の利払日!=nullの場合： 
            //検索条件文字列に　@" and ( 利払日1 = ?　@or 利払日2 = ? ) "　@追加する 
            //検索条件データに　@引数の利払日の値　@を　@二回　@追加する。 
            //(利払日１と利払日2の両方を検索するため) 
            if (l_bondProductListConditionInfo.interestPaymentDay != null)
            {
                l_sbQueryString.append(" and (interest_payment_day_1st = ? or interest_payment_day_2nd = ?) ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.interestPaymentDay);
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.interestPaymentDay);
            }
            
            //７）引数の通貨コード!=nullの場合： 
            //検索条件文字列に　@" and 通貨コード =　@? " を追加する 
            //検索条件データに　@引数の通貨コード 　@を追加する 
            if (l_bondProductListConditionInfo.currencyCode != null)
            {
                l_sbQueryString.append(" and currency_code = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.currencyCode);
            }
            
            //８）引数の取扱区分!=nullの場合： 
            //検索条件文字列に　@" and 取扱区分 =　@? "　@追加する 
            //検索条件データに　@引数の取扱区分　@を追加する 
            if (l_bondProductListConditionInfo.tradeHandleDiv != null)
            {
                l_sbQueryString.append(" and trade_handle_div = ? ");
                l_lisQueryStringDate.add( l_bondProductListConditionInfo.tradeHandleDiv);
            }
        }
        
        //上記作成した検索条件文字列と検索条件データを検索条件コンテナに設定し、返却する。 
        WEB3AdminBondQueryContainer l_adminBondQueryContainer = 
            new WEB3AdminBondQueryContainer();
        
        //検索条件文字列
        String l_strQueryString = l_sbQueryString.toString();
        //検索条件コンテナ
        
        Object[] l_objQueryStringData = new Object[l_lisQueryStringDate.size()];
        l_lisQueryStringDate.toArray(l_objQueryStringData);
        l_adminBondQueryContainer.setQueryString(l_strQueryString);
        l_adminBondQueryContainer.setQueryData(l_objQueryStringData);
       
        log.exiting(STR_METHOD_NAME);
        return l_adminBondQueryContainer;
    }
    
    /**
     * (create債券銘柄照会情報一覧)<BR>
     * 引数.債券銘柄リストから債券銘柄照会情報の配列を作成。<BR>
     * <BR>
     * １）引数.債券銘柄リストをLoopする。<BR>
     * 　@１－１）債券銘柄照会情報を生成する。<BR>
     * <BR>
     * 　@１－２）債券銘柄行の属性をセットする。<BR>
     * 　@　@・債券銘柄行.銘柄コード（WEB3） = 債券銘柄.銘柄コード（WEB3）<BR>
     * 　@　@・債券銘柄行.取扱銘柄名 = 債券銘柄.銘柄名<BR>
     * 　@　@・債券銘柄行.HOST銘柄名1　@= 債券銘柄.HOST銘柄名1<BR>
     * 　@　@・債券銘柄行.通貨コード = 債券銘柄.通貨コード<BR>
     * 　@　@・債券銘柄行.取扱区分 = 債券銘柄.取扱区分<BR>
     * 　@　@・債券銘柄行.発行価格 = 債券銘柄.発行価格<BR>
     * 　@　@・債券銘柄行.利率 = 債券銘柄.利率<BR>
     * 　@　@・債券銘柄行.発行日 = 債券銘柄.発行日<BR>
     * 　@　@・債券銘柄行.償還日 = 債券銘柄.償還日<BR>
     * 　@　@・債券銘柄行.年間利払回数 = 債券銘柄.年間利払回数<BR>
     * 　@　@・債券銘柄行.利払日1 = 債券銘柄.利払日1<BR>
     * 　@　@・債券銘柄行.利払日2 = 債券銘柄.利払日2<BR>
     * 　@　@・債券銘柄行.種別コード = 債券銘柄.種別コード<BR>
     * <BR>
     * 　@１－３）配列に追加する。<BR>
     * <BR>
     * ２）Loop終了後、債券銘柄照会情報の配列を返す。<BR>
     * @@param l_lisBondProductList - (債券銘柄リスト)<BR>
     * 債券銘柄リスト<BR>
     * @@return WEB3AdminBondProductConditionUnit[]
     * @@roseuid 44D67A68029F
     */
    protected WEB3AdminBondProductConditionUnit[] createBondProductReferenceInfoList(
        List l_lisBondProductList) 
    {
        final String STR_METHOD_NAME = 
            "createBondProductReferenceInfoList(List l_lisBondProductList)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSize = 0;
        if (l_lisBondProductList != null && !l_lisBondProductList.isEmpty())
        {
            l_intSize = l_lisBondProductList.size();
        }
        
        ArrayList l_listProductConditionUnit = new ArrayList();
        
        //１）引数.債券銘柄リストをLoopする。
        //１－１）債券銘柄照会情報を生成する。

        for (int i = 0; i < l_intSize; i++)
        {
            WEB3AdminBondProductConditionUnit l_bondProductConditionUnit = 
                new WEB3AdminBondProductConditionUnit();
            BondProductRow l_bondProductRow = 
                (BondProductRow)l_lisBondProductList.get(i);
            if(l_bondProductRow != null)
            {
                //１－２）債券銘柄行の属性をセットする。
                //債券銘柄行.銘柄コード（WEB3） = 債券銘柄.銘柄コード（WEB3）
                l_bondProductConditionUnit.productCode = 
                    l_bondProductRow.getProductCode();
                
                //債券銘柄行.取扱銘柄名 = 債券銘柄.銘柄名
                l_bondProductConditionUnit.handlingProductName = 
                    l_bondProductRow.getProductName();
                
                //債券銘柄行.HOST銘柄名1　@= 債券銘柄.HOST銘柄名1
                l_bondProductConditionUnit.hostProductName1 = 
                    l_bondProductRow.getHostProductName1();
                
                //債券銘柄行.通貨コード = 債券銘柄.通貨コード
                l_bondProductConditionUnit.currencyCode = 
                    l_bondProductRow.getCurrencyCode();
                
                //債券銘柄行.取扱区分 = 債券銘柄.取扱区分
                l_bondProductConditionUnit.tradeHandleDiv = 
                    l_bondProductRow.getTradeHandleDiv();
                
                //債券銘柄行.発行価格 = 債券銘柄.発行価格
                l_bondProductConditionUnit.issuePrice = 
                    WEB3StringTypeUtility.formatNumber(
                        l_bondProductRow.getIssuePrice());
                
                //債券銘柄行.利率 = 債券銘柄.利率
                l_bondProductConditionUnit.coupon = 
                    WEB3StringTypeUtility.formatNumber(
                        l_bondProductRow.getCoupon());
                
                //債券銘柄行.発行日 = 債券銘柄.発行日
                l_bondProductConditionUnit.issueDate = 
                    l_bondProductRow.getIssueDate();
                
                //債券銘柄行.償還日 = 債券銘柄.償還日
                l_bondProductConditionUnit.maturityDate = 
                    l_bondProductRow.getMaturityDate();
                
                //債券銘柄行.年間利払回数 = 債券銘柄.年間利払回数
                l_bondProductConditionUnit.yearlyInterestPayments = 
                    l_bondProductRow.getYearlyInterestPayments() + "";
                
                //債券銘柄行.利払日1 = 債券銘柄.利払日1
                l_bondProductConditionUnit.interestPaymentDay1 = 
                    l_bondProductRow.getInterestPaymentDay1st();
                
                //債券銘柄行.利払日2 = 債券銘柄.利払日2
                l_bondProductConditionUnit.interestPaymentDay2 = 
                    l_bondProductRow.getInterestPaymentDay2nd();
                
                //債券銘柄行.種別コード = 債券銘柄.種別コード
                l_bondProductConditionUnit.bondCategCode = 
                    l_bondProductRow.getBondCategCode();
                
                //１－３）配列に追加する
                l_listProductConditionUnit.add(l_bondProductConditionUnit);
            }
        }
        
        WEB3AdminBondProductConditionUnit[] l_adminBondProductConditionUnits = 
            new WEB3AdminBondProductConditionUnit[l_listProductConditionUnit.size()];
        l_listProductConditionUnit.toArray(l_adminBondProductConditionUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminBondProductConditionUnits;  
    }
}
@
