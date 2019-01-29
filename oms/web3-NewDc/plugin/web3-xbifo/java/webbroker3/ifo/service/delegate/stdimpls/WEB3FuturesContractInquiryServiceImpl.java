head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesContractInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物建玉照会サービスImpl(WEB3FuturesContractInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強 (中訊) 新規作成
            001: 2004/09/1 李強(中訊) UT-000341を対応しました。
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesContractDivisionComparator;
import webbroker3.ifo.message.WEB3FuturesContractReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3FuturesProductCodeComparator;
import webbroker3.ifo.message.WEB3FuturesProfitAndLossComparator;
import webbroker3.ifo.service.delegate.WEB3FuturesContractInquiryService;

/**
 * (先物建玉照会サービスImpl)<BR>
 * 株価指数先物建玉照会サービス実装クラス
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesContractInquiryServiceImpl extends WEB3FuturesClientRequestService 
    implements WEB3FuturesContractInquiryService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesContractInquiryServiceImpl.class);
    /**
     * @@roseuid 40F7A2C30290
     */
    public WEB3FuturesContractInquiryServiceImpl() 
    {
     
    }
    
    /**
     * 株価指数先物建玉照会サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物建玉照会サービス）建玉照会」参照。<BR>
     * ==========================================================<BR>
     * シーケンス図(「（先物建玉照会サービス）建玉照会」): 8 get銘柄(Institution, String)<BR>
     * (銘柄コードチェック)<BR>
     * get銘柄で指定の銘柄コードが取得できなかった場合は、<BR>
     *「銘柄コードチェックエラー」の例外をthrowする。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00301<BR>
     * ==========================================================<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FuturesContractReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A996300103
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
    throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3FuturesContractReferenceRequest)";

        log.entering(STR_METHOD_NAME);
        
        if (!(l_request instanceof WEB3FuturesContractReferenceRequest))
        {             
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                   this.getClass().getName() + "." + STR_METHOD_NAME);                 
        }
        //2.validate()      
        WEB3FuturesContractReferenceRequest l_request0 = (WEB3FuturesContractReferenceRequest) l_request;
        l_request0.validate();

        //3.get補助口座()           
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        log.debug("3.get補助口座() : " + l_subAccount);

        //4.システム売買停止(バッチ中、緊急停止中)チェックを実施する。            
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //5.getInstance()            
        WEB3IfoOrderManagerReusableValidations l_managerReusableValidations =
            (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations.getInstance();
        log.debug("5.getInstance() : " + l_managerReusableValidations);

        //6.validate先物OP口座開設(補助口座 : 補助口座, 先物／オプション区分 : String)           
        l_managerReusableValidations.validateFuturesOptionAccountOpen(
            l_subAccount,
            WEB3FuturesOptionDivDef.FUTURES);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
       
        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        log.debug("get : l_orderMgr = " + l_orderMgr);       
       
        WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        log.debug("get : l_productMgr = " + l_productMgr);
        
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        log.debug("get : l_positionManager = " + l_positionManager);
        
        //7.(*1)分岐フロー
        //リクエストデータに銘柄コードがセットされている場のみ、
        //以下の処理を実施する。
        //8.get銘柄(Institution, String)            
        //NotFoundException
        if (l_request0.futProductCode != null && !"".equals(l_request0.futProductCode))
        {   
            try
            {          
                log.debug("l_subAccount.getInstitution() = " + l_subAccount.getInstitution().getInstitutionCode());                 
                WEB3IfoProductImpl l_product = l_productMgr.getIfoProduct(l_subAccount.getInstitution(), l_request0.futProductCode);
    
                log.debug("get : l_product = " + l_product);
            }        
            catch (NotFoundException l_ex)
            {
                String msg = "該当銘柄なし。";
                log.error(msg);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        //9.is取引可能顧客(補助口座 : 補助口座)
        boolean l_blnIsTradedPossibleCustomer =
            l_orderMgr.isTradedPossibleCustomer(l_subAccount);
        log.debug("9.is取引可能顧客 : =" + l_blnIsTradedPossibleCustomer);

        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits = 
            l_positionManager.createProductCodeNameFromContract(
                l_subAccount,
                false,
                WEB3FuturesOptionDivDef.FUTURES);                
        log.debug("10.create銘柄コード名称from建玉 : = " + l_productCodeNameUnits);               

        //16.createResponse( )
        WEB3FuturesContractReferenceResponse l_response =
            (WEB3FuturesContractReferenceResponse)l_request0.createResponse();

        //(該当データチェック)
        //create銘柄コード名称from建玉の返り値がNULLの場合、
        //レスポンス作成、プロパティセット処理を行う。
        //（建玉照会明細作成、ソート処理、ページング処理は行わない）
        if (l_productCodeNameUnits == null)
        {
            //(1)該当データなしの場合(create銘柄コード名称from建玉の返り値がNULL)
            //レスポンス.建玉照会明細 = NULL
            //レスポンス.総ページ数 = 0
            //レスポンス.総レコード数 = 0
            //レスポンス.表示ページ番号 = 0
            //レスポンス.株価指数先物オプション銘柄コード名称 = NULL
            //レスポンス.顧客ロック区分 = is取引可能顧客の返り値がfalseならtrue
            l_response.contractReferenceUnits = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.futOpProductCodeNames = null;
            if (l_blnIsTradedPossibleCustomer)
            {
                l_response.accountLock = false;
            }
            else
            {
                l_response.accountLock = true;
            }
         
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }



        //11.create検索条件文字列(String)
        String l_searchCondString = createSearchCondCharacter(l_request0.futProductCode);
        log.debug("11.create検索条件文字列 : = " + l_searchCondString);

        //12.create検索条件データコンテナ(String)
        //NotFoundException, WEB3BaseException           
        String[] l_searchCondDataContainer =
            createSearchCondDataContainer(l_request0.futProductCode);
        log.debug("12.create検索条件データコンテナ : = " + l_searchCondDataContainer);
        
        //13.create先物建玉照会明細(補助口座, String, String, String, String[])        
        //建玉照会画面に表示する建玉照会明細の一覧を作成する。
        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnits = 
            l_positionManager.createFuturesContractInquiryDetails(
                l_subAccount,
                WEB3FuturesOptionDivDef.FUTURES,
                l_request0.settlementState,
                l_searchCondString,
                l_searchCondDataContainer);           

        log.debug("13.create先物建玉照会明細 : = " + l_contractReferenceUnits);

        //(検索条件該当データチェック)
        //create先物建玉照会明細の返り値がNULLの場合、
        //レスポンス作成、プロパティセット処理を行う。
        //(ソート処理、ページング処理は行わない)
        if (l_contractReferenceUnits == null)
        {
            //(2)検索条件該当データなしの場合(create建玉照会明細の返り値がNULLの場合)

            //レスポンス.建玉照会明細 = NULL
            //レスポンス.総ページ数 = 0
            //レスポンス.総レコード数 = 0
            //レスポンス.表示ページ番号 = 0
            //レスポンス.株価指数先物オプション銘柄コード名称 = create銘柄コード名称from建玉の返り値
            //レスポンス.顧客ロック区分 = is取引可能顧客の返り値がfalseならtrue
            l_response.contractReferenceUnits = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
            l_response.futOpProductCodeNames = l_productCodeNameUnits;
            if (l_blnIsTradedPossibleCustomer)
            {
                l_response.accountLock = false;
            }
            else
            {
                l_response.accountLock = true;
            }
          
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //14.sort建玉照会明細(株価指数先物建玉照会明細[], 株価指数先物オプションソートキー[])            
        WEB3FuturesContractReferenceUnit[] l_sortedContractReferenceUnit =
            sortContractReferenceUnit(l_contractReferenceUnits, l_request0.futOpSortKeys);
        log.debug("14.sort建玉照会明細 : = " + l_sortedContractReferenceUnit.length);

        //15.createページ(株価指数先物建玉照会リクエスト, 株価指数先物建玉照会明細[])            
        WEB3FuturesContractReferenceUnit[] l_createPage =
            createPage(l_request0, l_sortedContractReferenceUnit);
        log.debug("15.createページ : = " + l_createPage);

        //(*2)レスポンスデータにプロパティをセットする。
        //該当データチェック、検索条件該当データチェック結果にもとづき、セットされるプロパティが異なる。

        //(3)(1)(2)以外の場合

        //レスポンス.建玉照会明細 = createページの返り値
        //レスポンス.総レコード数 = create建玉照会明細の返り値のサイズ　@
        //レスポンス.総ページ数 = 総レコード数 ÷ リクエストデータ.ページ内表示行数 ※計算結果が1未満、または余りが存在する場合には更に1を加算する
        //レスポンス.表示ページ番号 = リクエストデータ.要求ページ番号
        //※レスポンス.総レコード数 <= (リクエストデータ.ページ内表示行数 * (リクエストデータ.要求ページ番号 - 1))の場合は、レスポンス.総ページ数をセット(最終ページ番号をセット)
        //レスポンス.株価指数先物オプション銘柄コード名称 = ｃreate銘柄コード名称from建玉の返り値
        //レスポンス.顧客ロック区分 = is取引可能顧客の返り値がfalseならtrue  ※ロック顧客の場合：true、ロック顧客でない場合：falseとなる
        int l_intTotalRecords = l_contractReferenceUnits.length;
        int l_intPageSize = Integer.parseInt(l_request0.pageSize);
        int l_intTotalPages = l_intTotalRecords / l_intPageSize;
        int l_intRemains = l_intTotalRecords % l_intPageSize;
        
        l_response.contractReferenceUnits = l_createPage;
        l_response.totalRecords = Integer.toString(l_intTotalRecords);            

        if (l_intRemains != 0)
        {
            l_intTotalPages += 1;
        }
        
        l_response.totalPages = Integer.toString(l_intTotalPages);
        l_response.pageIndex = l_request0.pageIndex;
        if (l_intTotalRecords
            <= (l_intPageSize * (Integer.parseInt(l_request0.pageIndex) - 1)))
        {
            l_response.pageIndex = l_response.totalPages;
        }
        l_response.futOpProductCodeNames = l_productCodeNameUnits;
        
        if (l_blnIsTradedPossibleCustomer)
        {
            l_response.accountLock = false;
        }
        else
        {
            l_response.accountLock = true;
        }
       
        log.exiting(STR_METHOD_NAME);
        return l_response;    

    }
    
    /**
     * (create検索条件文字列)<BR>
     * リクエストデータをもとに、検索条件（where以下指定の文字列）<BR>
     * を作成する。<BR>
     * <BR>
     * (1)戻り値となる文字列のインスタンスを生成<BR>
     * <BR>
     * (2)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、<BR>
     * 銘柄ID指定を文字列インスタンスに追加（銘柄コードに対応<BR>
     * する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * (3)文字列インスタンスを返却<BR>
     * （パラメータ.銘柄コード＝NULLの場合、NULLを返却する）<BR>
     * @@param l_strProductCode - 銘柄コード
     * @@return String
     * @@roseuid 40A995010122
     */
    protected String createSearchCondCharacter(String l_strProductCode) 
    {
        final String STR_METHOD_NAME =
            "createSearchCondCharacter(String)";

        log.entering(STR_METHOD_NAME);
        //(1)戻り値となる文字列のインスタンスを生成<BR>
        String l_strReturnValue = null;

        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {          
            l_strReturnValue = " and product_id = ?";  
            log.exiting(STR_METHOD_NAME);
            return l_strReturnValue;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return l_strReturnValue;
        }
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * リクエストデータから、検索条件（where以下指定の文字列）の<BR>
     * パラメータリストを作成する。<BR>
     * <BR>
     * (1)文字列配列を作成<BR>
     * <BR>
     * (2)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、銘柄IDを<BR>
     * 　@　@文字列配列にセット（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@銘柄ID ＝ 先物OPプロダクトマネージャ.get銘柄<BR>
     * (証券会社オブジェクト(*1), パラメータ.銘柄コード).銘柄ID<BR>
     * <BR>
     * (*1)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定<BR>
     * <BR>
     * (3)文字列配列を返却<BR>
     * （パラメータ.銘柄コード＝NULLの場合、NULLを返却する）<BR>
     * @@param l_strProductCode - 銘柄コード
     * @@return String[]
     * @@roseuid 40A995010131
     */
    protected String[] createSearchCondDataContainer(String l_strProductCode) 
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSearchCondDataContainer(String)";

        log.entering(STR_METHOD_NAME);
        //(1)文字列配列を作成
        String[] l_strReturnValue = new String[1];

        //(2)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、銘柄IDを<BR>
        //　@　@文字列配列にセット（銘柄コードに対応する銘柄IDで検索を行う)<BR>
        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {

            String l_strProductId = null;           
            
            //(*1)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();       
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
     
            WEB3IfoProductManagerImpl l_productMgr = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            log.debug("get : l_productMgr = " + l_productMgr);         
            try
            {
                l_strProductId =
                    Long.toString(
                        l_productMgr.getIfoProduct(l_subAccount.getInstitution(), 
                                                   l_strProductCode).getProductId());

                l_strReturnValue[0] = l_strProductId;    
                log.debug("reuturn value = " + l_strProductId);
            }
            catch (NotFoundException l_ex)
            {
                String msg = "該当銘柄なし。";
                log.error(msg);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);

            }
            log.exiting(STR_METHOD_NAME);
            return l_strReturnValue;       
          
        }
        else
        {   // (3)文字列配列を返却<BR>
            // （パラメータ.銘柄コード＝NULLの場合、NULLを返却する）<BR>
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (createページ)<BR>
     * 要求ページ番号に該当する株価指数先物建玉照会明細の配列を<BR>
     * 作成する。<BR>
     * <BR>
     * (1)ページ内表示行数、要求ページ番号の取得<BR>
     * ページ内表示行数 = パラメータ.リクエストデータ.ページ内表示行数<BR>
     * 要求ページ番号 = パラメータ.リクエストデータ.要求ページ番号<BR>
     * <BR>
     * (1)要求ページ開始位置を決定<BR>
     * fromIndex = ページ内表示行数 * (要求ページ番号 - 1)<BR>
     * <BR>
     * (2)要求ページ終了位置を決定<BR>
     * toIndex = (ページ内表示行数 * 要求ページ番号) - 1<BR>
     * <BR>
     * ※パラメータ.建玉照会明細の要素数 <= fromIndexの場合、<BR>
     * (総レコード数が要求ページ番号に満たない場合)<BR>
     * fromIndex =　@パラメータ.建玉照会明細の要素数 - ページ内表示行数<BR>
     * toIndex = パラメータ.建玉照会明細の要素数 - 1<BR>
     * <BR>
     * (3)ArrayListを作成<BR>
     * <BR>
     * (4)パラメータ.建玉照会明細数分Loop処理<BR>
     * <BR>
     * 建玉照会明細のインデックスが<BR>
     * fromIndexとtoIndexの範囲内(fromIndex以上、toIndex以下)<BR>
     * である場合は、<BR>
     * 作成したArrayListに建玉照会明細オブジェクトを追加<BR>
     * <BR>
     * (5)ArrayList.toArrayで該当ページ番号の建玉照会明細の配列を返却する
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物建玉照会リクエストオブジェクト
     * @@param l_contractReferenceUnit - (建玉照会明細)<BR>
     * 株価指数先物建玉照会明細の配列<BR>
     * (総レコード数分の配列)
     * @@return WEB3FuturesContractReferenceUnit[]
     * @@roseuid 40A995010141
     */
    protected WEB3FuturesContractReferenceUnit[] createPage(
        WEB3FuturesContractReferenceRequest l_request, 
        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit) 
    {
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
        l_contractReferenceUnit, 
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));
                       
         return (WEB3FuturesContractReferenceUnit[])l_pageIndexInfo.getArrayReturned(WEB3FuturesContractReferenceUnit.class);
    }
    
    /**
     * (sort建玉照会明細)<BR>
     * 指定されたソートキー、昇降順にもどついて建玉照会明細のソートを行う。<BR>
     * <BR>
     * (1)ArrayListを作成<BR>
     * <BR>
     * (2)パラメータ.ソートキーの配列数分Loop処理<BR>
     * 　@(2-1)パラメータ.ソートキー.キー項目を取得<BR>
     * <BR>
     * 　@(2-2)パラメータ.ソートキー.昇順/降順を取得<BR>
     * <BR>
     * 　@(2-3)キー項目による分岐処理<BR>
     * 　@　@キー項目が銘柄コードであった場合、先物銘柄コードComparator<BR>
     * を生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が損益であった場合、先物損益Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建日であった場合、先物建日Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建区分であった場合、先物建区分Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@(2-4)(2-3)にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * (3)ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * (4)Comparatorの配列順のソート処理<BR>
     * (web3-common)WEB3ArraysUtility.sort(パラメータ.建玉照会明細、<BR>Comparator[])<BR>
     * <BR>
     * (5)ソートされた建玉照会明細の配列を返却<BR>
     * @@param l_contractReferenceUnit - (建玉照会明細)<BR>
     * 株価指数先物建玉照会明細の配列
     * @@param l_sortKey - (ソートキー)<BR>
     * 株価指数先物オプションソートキーの配列
     * @@return WEB3FuturesContractReferenceUnit[]
     * @@roseuid 40A995010180
     */
    protected WEB3FuturesContractReferenceUnit[] sortContractReferenceUnit(
        WEB3FuturesContractReferenceUnit[] l_contractReferenceUnit, 
        WEB3FuturesOptionsSortKey[] l_sortKey) 
    {
        final String STR_METHOD_NAME =
            "sortContractReferenceUnit(WEB3FuturesContractReferenceUnit[], WEB3FuturesOptionsSortKey[])";

        log.entering(STR_METHOD_NAME);
        //(1)ArrayListを作成
        List l_lisComparator = new ArrayList();

        //(2)パラメータ.ソートキーの配列数分Loop処理
        int l_intSortKeyLength = l_sortKey.length;
        log.debug("パラメータ.ソートキーのの要素数 = " + l_intSortKeyLength);

        String l_strKeyItem; //パラメータ.ソートキー.キー項目
        String l_strAscDesc; //パラメータ.ソートキー.昇順/降順
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //　@(2-1)パラメータ.ソートキー.キー項目を取得
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("パラメータ.ソートキー.キー項目 " + i + 1 + " = " + l_strKeyItem);

            //　@(2-2)パラメータ.ソートキー.昇順/降順を取得
            l_strAscDesc = l_sortKey[i].ascDesc;
            log.debug("パラメータ.ソートキー.昇順/降順 " + i + 1 + " = " + l_strAscDesc);

            //　@(2-3)キー項目による分岐処理
            //　@　@キー項目が銘柄コードであった場合、銘柄コードComparatorを生成
            if (WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(l_strKeyItem))
            {               
                WEB3FuturesProductCodeComparator l_productCodeComparator =
                    new WEB3FuturesProductCodeComparator(l_strAscDesc);
                //作成したComparatorをArrayListに追加
                l_lisComparator.add(l_productCodeComparator); 
            }
            //キー項目が損益であった場合、損益Comparatorを生成
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {                
                WEB3FuturesProfitAndLossComparator l_profitAndLossComparator =
                    new WEB3FuturesProfitAndLossComparator(l_strAscDesc);
                l_lisComparator.add(l_profitAndLossComparator);
            }
            //キー項目が建日であった場合、建日Comparatorを生成
            else if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {              
                WEB3FuturesOpenDateComparator l_openDateComparator =
                    new WEB3FuturesOpenDateComparator(l_strAscDesc);
                l_lisComparator.add(l_openDateComparator);
            }
            //キー項目が建区分であった場合、建区分Comparatorを生成
            else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {                
                WEB3FuturesContractDivisionComparator l_contractDivisionComparator =
                    new WEB3FuturesContractDivisionComparator(l_strAscDesc);
                l_lisComparator.add(l_contractDivisionComparator);
            }
        }

        //(3)ArrayListからComparatorの配列を作成
      
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);

        //(4)Comparatorの配列順のソート処理        
        WEB3ArraysUtility.sort(l_contractReferenceUnit, l_comparators);
        
        log.exiting(STR_METHOD_NAME);
        return l_contractReferenceUnit;
    }    

}
@
