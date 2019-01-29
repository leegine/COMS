head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部者情報一覧サービスImpl(WEB3AdminAccInfoInsiderInfoListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoInsiderInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoInsiderInfoListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (管理者お客様情報内部者情報一覧サービスImpl)<BR>
 * 管理者お客様情報内部者情報一覧サービス実装クラス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoListServiceImpl implements WEB3AdminAccInfoInsiderInfoListService
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoListServiceImpl.class);
    
    public WEB3AdminAccInfoInsiderInfoListServiceImpl()
    {
        
    }
    
    /**
     * 内部者情報一覧処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報内部者情報一覧入力ﾘｸｴｽﾄの場合 <BR>
     * －get入力画面()をコールする。 <BR>
     * ○ 引数のリクエストデータが、管理者お客様情報内部者情報一覧ﾘｸｴｽﾄの場合 <BR>
     * －get一覧画面()をコールする。 <BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminAccInfoInsiderInfoInputRequest)
        {
            l_response = getInputScreenDisplay((WEB3AdminAccInfoInsiderInfoInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminAccInfoInsiderInfoListRequest)
        {
            l_response = getListScreenDisplay((WEB3AdminAccInfoInsiderInfoListRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 内部者情報一覧入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者お客様情報（内部者情報一覧）get入力画面」<BR>
     * 参照<BR>
     * 
     * @@param l_request - 管理者お客様情報内部情報一覧入力リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    protected WEB3AdminAccInfoInsiderInfoInputResponse getInputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreenDisplay(WEB3AdminAccInfoInsiderInfoInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, false);
        
        //1.4) validate部店権限(部店コード : String[])
        l_administrator.validateBranchPermission(l_request.branchCodeList);
        
        //1.5) createResponse( )
        WEB3AdminAccInfoInsiderInfoInputResponse l_response = 
            (WEB3AdminAccInfoInsiderInfoInputResponse)l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get一覧画面)<BR>
     * 内部者情報一覧入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「管理者お客様情報（内部者情報一覧）get一覧画面」<BR>
     * 参照<BR>
     * @@param l_request - 管理者お客様情報内部情報一覧リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3AdminAccInfoInsiderInfoListResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    protected WEB3AdminAccInfoInsiderInfoListResponse getListScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreenDisplay(WEB3AdminAccInfoInsiderInfoListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1) validate( )
        l_request.validate();
        
        //1.2) getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3) validate権限(機@能カテゴリコード : String, is更新 : boolean)(機@能カテゴリコード.顧客取引管理, false)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.ACCINFO_TRADING, false);
        
        //1.4) get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.5) validate部店権限(部店コード : String[])
        l_administrator.validateBranchPermission(l_request.branchCode);
        
        //1.6) create検索条件文字列(String, String[], String, String)
        String l_strQueryString = 
            this.createQueryString(l_strInstitutionCode, l_request.branchCode, l_request.accountCode, l_request.productCode);
        
        //1.7) create検索条件データコンテナ(String, String[], String, String)
        String[] l_strQueryContainer = 
            this.createQueryContainer(l_strInstitutionCode, l_request.branchCode, l_request.accountCode, l_request.productCode);
        
        //1.8) createソート条件(お客様情報ソートキー[])
        String l_strSortCond =
            this.createSortCond(l_request.sortKeys);
        
        //1.9) get内部者(検索条件文字列 : String, 検索条件データコンテナ : String[], ソート条件 : String)
        
        log.debug(l_strQueryString + l_strSortCond);
        for (int i = 0; i < l_strQueryContainer.length; i++)
        {
            log.debug(l_strQueryContainer[i]);
        }
        
        List l_insider = WEB3GentradeInsider.getInsider(l_strQueryString, l_strQueryContainer, l_strSortCond);
        
        //1.10) ArrayList( )
        List l_list = new ArrayList();
        
        //1.11) (*1)get内部者()の戻り値の内、表示対象行(fromIndex ～ toIndex)の間Loop処理
        int l_intPageSize = Integer.parseInt(l_request.pageSize);//ページ内表示行数
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);//要求ページ番号
        
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_insider, l_intPageIndex, l_intPageSize);
            
        WEB3GentradeInsider[] l_insiderLast = (WEB3GentradeInsider[])l_pageIndexInfo.getArrayReturned(WEB3GentradeInsider.class);
        
        for(int i = 0; i < l_insiderLast.length; i++ )
        {
            
            //1.11.1) create内部者情報一覧Unit(内部者)
            WEB3AccInfoInsiderInfoUnit l_insiderInfoUnit = this.createInsiderInfoListUnit(l_insiderLast[i]);
            //1.11.2) add(arg0(=内部者情報一覧Unit) : Object)
            l_list.add(l_insiderInfoUnit);
            
        }
        
        //1.12) toArray( )
        WEB3AccInfoInsiderInfoUnit[] l_insiderInfoUnits = new WEB3AccInfoInsiderInfoUnit[l_list.size()];
        l_list.toArray(l_insiderInfoUnits);

        //1.13) createResponse( )
        WEB3AdminAccInfoInsiderInfoListResponse l_response = 
            (WEB3AdminAccInfoInsiderInfoListResponse)l_request.createResponse();
        
        //1.14) (*2)プロパティセット
        l_response.insideInfoList = l_insiderInfoUnits;
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。<BR> 
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値の検索条件文字列インスタンス（：String）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード条件を追加する。<BR>
     * <BR>
     * 　@" institution_code =  ? "<BR>
     * <BR>
     * ３）　@部店条件追加 <BR>
     * 　@部店条件を追加する。部店コード[]の要素数分"?"を追加する。<BR> 
     * <BR>
     * 　@" and branch_id in (?, ?,,,) " <BR>
     * <BR>
     * ４）　@口座条件追加<BR>
     * 　@パラメータ.口座コード != nullの場合、口座条件を追加する。<BR>
     * <BR>
     * 　@" and account_id = ? "<BR>
     * <BR>
     * ５）　@銘柄条件追加<BR>
     * 　@パラメータ.銘柄コード != nullの場合、銘柄条件を追加する。<BR>
     * <BR>
     * 　@" and product_id = ? "<BR>
     * <BR>
     * ６）　@文字列インスタンスを返却 <BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * @@param l_strAccountCode - 口座コード
     * @@param l_strProductCode - 銘柄コード
     * @@return String
     */
    protected String createQueryString(String l_strInstitutionCode, String[] l_strBranchCodes,
        String l_strAccountCode, String l_strProductCode)
    {
        
        final String STR_METHOD_NAME = " createQueryString(String, String[], String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strSearchCond;
        
       /*
        * ２）　@証券会社条件追加<BR>
        * 　@証券会社コード条件を追加する。<BR>
        * <BR>
        * 　@" institution_code =  ? "<BR>
        */
        l_strSearchCond = " institution_code = ? ";
        
        //部店条件を追加する。部店コード[]の要素数分"?"を編集する。
        
        int l_intBranchCodesCnt = l_strBranchCodes.length;
        
        if (l_intBranchCodesCnt > 0)
        {
            StringBuffer l_sbQueryBranchCodes = new StringBuffer();
            
            
            
            for (int i = 0; i < l_intBranchCodesCnt; i++)
            {

                if (l_sbQueryBranchCodes.length() != 0)
                {
                    l_sbQueryBranchCodes.append(", ");
                }
                l_sbQueryBranchCodes.append("?");
                
            }                
            l_strSearchCond += " and branch_id in (" + l_sbQueryBranchCodes.toString() + ")";          
        }
        
        /*
         *４）　@口座条件追加<BR>
         * パラメータ.口座コード != nullの場合、口座条件を追加する。<BR>
         */
        if(l_strAccountCode != null)
        {
            l_strSearchCond += " and account_id = ? ";
        }
                
        //５）　@銘柄条件追加
        if(l_strProductCode != null)
        {
            l_strSearchCond += " and product_id = ? ";
        }
        
        return l_strSearchCond;
    }
    
    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）を生成 <BR>
     * <BR>
     * ２）　@証券会社条件追加<BR>
     * 　@証券会社コード文字列を追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]<BR>
     * 　@証券会社コード<BR>
     * <BR>
     * ３）　@部店条件追加 <BR>
     * 　@部店コード[]に該当する部店ＩＤをすべて追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数]　@<BR>
     * 　@部店ＩＤ※<BR>
     * <BR>
     * 　@※アカウントマネージャ.getBranch(証券会社，部店コード)にて取得する。<BR>
     * 　@※証券会社オブジェクトは、アカウントマネージャ.getInstitution(証券会社コード)にて取得する。<BR>
     * <BR>
     * ４）　@口座条件追加<BR>
     * 　@パラメータ.口座コード != nullの場合、口座コードに該当する口座ＩＤを追加する。<BR>
     * <BR>
     * [add()に指定する引数]　@<BR>
     * 　@口座ＩＤ※<BR>
     * <BR>
     * 　@※アカウントマネージャ.get顧客(証券会社コード，部店コード，口座コード)にて取得する。<BR>
     * <BR>
     * ５）　@銘柄条件追加<BR>
     * 　@パラメータ.銘柄コード != nullの場合、銘柄コードに該当する銘柄ＩＤを追加する。<BR>
     * <BR>
     * 　@[add()に指定する引数]　@<BR>
     * 　@銘柄ＩＤ※<BR>
     * <BR>
     * 　@※拡張プロダクトマネージャ.getProduct(証券会社，銘柄コード)にて取得する。<BR>
     * <BR>
     * ６）　@配列を返却 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCodes - 部店コード配列
     * @@param l_strAccountCode - 口座コード
     * @@param l_strProductCode - 銘柄コード
     * @@return String[]
     * @@roseuid 415103500340
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes,
        String l_strAccountCode, 
        String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createQueryContainer(String, String[], String, String)";
        log.entering(STR_METHOD_NAME);
        
        //戻り値編集用インスタンス（：ArrayList）を生成
        List l_lisQueryContainer = new ArrayList();
        
        //２）　@証券会社条件追加
        l_lisQueryContainer.add(l_strInstitutionCode);
        
        //３）　@部店条件追加
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //拡張アカウントマネージャ取得する
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        //拡張プロダクトマネージャ
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager) (l_finApp.getTradingModule(ProductTypeEnum.EQUITY)).getProductManager();
            
        long l_lngBranchId = 0;
        Institution l_institution;
        try
        {
            l_institution = l_accMgr.getInstitution(l_strInstitutionCode);
            
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                Branch l_branch = l_accMgr.getBranch(l_institution, l_strBranchCodes[i]);
                l_lngBranchId = l_branch.getBranchId();
                l_lisQueryContainer.add("" + l_lngBranchId);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error in 部店条件追加....... ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // ４）　@口座条件追加
        if (l_strAccountCode != null)
        {
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                try
                {
                    WEB3GentradeMainAccount  l_mainAccount =
                        (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstitutionCode,
                            l_strBranchCodes[i], l_strAccountCode);
                    l_lisQueryContainer.add(String.valueOf(l_mainAccount.getAccountId()));
                    break;
                } catch (WEB3SystemLayerException l_exp){
                    if (i == l_strBranchCodes.length -1)
                    {
                        String l_strMsg = "入力部店コード:[" + l_strBranchCodes.toString() + "] "
                                        + "入力顧客コード:[" + l_strAccountCode + "]に該当する顧客が取得できませんでした。";
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01387,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_strMsg);
                    }
                }
            }
        }
        
        //５）　@銘柄条件追加
        if (l_strProductCode != null)
        {
            try
            {
                EqTypeProduct l_product = l_productManager.getProduct(l_institution, l_strProductCode);
                long l_lngProductId = l_product.getProductId();
                l_lisQueryContainer.add("" + l_lngProductId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません。", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

        }

        String[] l_strQueryDataContainers = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strQueryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryDataContainers;     
    }

    /**
     * (createソート条件)<BR>
     *ソート条件文字列を編集する。 <BR>
     *<BR>
     *１）ソート条件文字列作成<BR>
     *　@テーブル列物理名を使用し、対応するソート条件文字列（order by句）を編集する。<BR>
     *　@１－１）　@パラメータ.ソートキーの要素数分以下の処理を繰り返し、<BR>
     *　@　@　@　@　@　@ソート条件文字列を作成する。<BR>
     *　@　@１－１－１）　@ソートキー.キー項目に対応するテーブル列物理名を<BR>
     *　@　@　@　@　@　@　@　@　@　@ソート条件に追加する。<BR>
     *<BR>
     *　@　@　@[内部者情報一覧Unit.部店コードの場合]<BR>
     *　@　@　@　@内部者マスター.部店ID<BR>
     *　@　@　@[内部者情報一覧Unit.顧客コードの場合]<BR>
     *　@　@　@　@substr(内部者マスター.口座ID, 9, 6)<BR>
     *　@　@　@[内部者情報一覧Unit.銘柄コードの場合]<BR>
     *　@　@　@　@内部者マスター.銘柄ID<BR>
     *　@　@　@[内部者情報一覧Unit.関係コードの場合]<BR>
     *　@　@　@　@内部者マスター.関係区分<BR>
     *　@　@　@[内部者情報一覧Unit.役員名の場合]<BR>
     *　@　@　@　@内部者マスター.役員名<BR>
     *　@　@　@[内部者情報一覧Unit.役職名の場合]<BR>
     *　@　@　@　@内部者マスター.役職名<BR>
     *　@　@　@[内部者情報一覧Unit.登録状況区分の場合]<BR>
     *　@　@　@　@内部者マスター.登録状況区分<BR>
     *<BR>
     *　@　@１－１－２）　@ソートキー.昇順／降順に対応するソート順序(asc or desc)を<BR>
     *　@　@　@　@　@　@　@　@　@ソート条件に追加する。<BR>
     *<BR>
     *２） 作成したソート条件文字列を返却する。<BR>
     * @@param  l_sortKeys - ソートキー
     * @@return String
     * @@roseuid 4164C934006A
     */
    protected String createSortCond(WEB3AccInfoSortKey[] l_sortKeys)
    {
        StringBuffer l_sbQuery = new StringBuffer();
        
        int l_intLength = l_sortKeys.length;

        for (int i = 0; i < l_intLength; i++ )
        {
            
            if(WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" branch_id ");
            }
            else if(WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
            	//substr(内部者マスター.口座ID, 9, 6)
                l_sbQuery.append(" substr(account_id , 9 , 6) ");
            }
            else if(WEB3AccInfoKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" product_id ");
            }
            else if(WEB3AccInfoKeyItemDef.RELATION_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" relation_div ");
            }
            else if(WEB3AccInfoKeyItemDef.OFFICER_NAME.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" officer_name ");
            }
            else if(WEB3AccInfoKeyItemDef.POST_NAME.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" post_name ");
            }
            else if(WEB3AccInfoKeyItemDef.REGIST_DIV.equals(l_sortKeys[i].keyItem))
            {
                l_sbQuery.append(" regist_div ");
            }
            else
            {
                continue;
            }
            String l_sort = null;
            if("A".equals(l_sortKeys[i].ascDesc))
            {
                l_sort = "Asc";
            }
            else
            {
                l_sort = "Desc";
            }
            l_sbQuery.append(l_sort);
            
            if(i < l_intLength - 1)
            {
                l_sbQuery.append(", ");
            }
                        
        }
        
        return l_sbQuery.toString();

    }
    
    /**
     * (create内部者情報一覧Unit)<BR>
     *引数の内部者オブジェクトより、内部者情報一覧Unitメッセージデータを作成する。<BR>                                                  
     *             <BR>                                                       
     *１）　@内部者情報一覧Unit生成   <BR>                                     
     *　@内部者情報一覧Unitを生成する。  <BR>                                  
     *                      <BR>                                              
     *２）　@プロパティセット     <BR>                                         
     *　@１）にて生成した内部者情報一覧Unitに以下のプロパティをセットする。<BR>
     *                                                 <BR>                  
     *　@内部者情報一覧Unit.部店コード = 部店(*1).部店コード     <BR>          
     *                                                    <BR>                
     *　@内部者情報一覧Unit.顧客コード = 顧客(*2).get表示顧客コード  <BR>             
     *                                                                    
     *　@内部者情報一覧Unit.顧客名（漢字） = 顧客(*2).顧客名（漢字）  <BR>     
     *　@内部者情報一覧Unit.銘柄コード = 株式銘柄(*3).銘柄コード <BR>          
     *                                                   <BR>                 
     *　@内部者情報一覧Unit.銘柄名 = 株式銘柄(*3).銘柄名   <BR>                
     *                                                        <BR>            
     *　@内部者情報一覧Unit.関係コード = パラメータ.内部者.関係区分   <BR>     
     *　@内部者情報一覧Unit.役員名 = パラメータ.内部者.役員名    <BR>         
     *　@内部者情報一覧Unit.役職名 = パラメータ.内部者.役職名     <BR>         
     *　@内部者情報一覧Unit.登録状況区分 = パラメータ.内部者.登録状況区分  <BR>
     *                                                        <BR>            
     *３）プロパティセットした内部者情報一覧Unitを返却する。 <BR>             
     *                                        <BR>                            
     *(*1)アカウントマネージャ.getBranch(パラメータ.内部者.部店ID)にて取得<BR>
     *(*2)アカウントマネージャ.get顧客(パラメータ.内部者.口座ID)にて取得  <BR>
     *(*3)拡張株式プロダクトマネージャ.getProduct(パラメータ.内部者.銘柄ID)にて取得<BR>
     *
     *@@param l_insiders - 内部者
     *@@return WEB3AccInfoInsiderInfoUnit
     */
    protected WEB3AccInfoInsiderInfoUnit createInsiderInfoListUnit(WEB3GentradeInsider l_insiders) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createInsiderInfoListUnit(WEB3GentradeInsider[])";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoInsiderInfoUnit l_insiderInfoUnit = new WEB3AccInfoInsiderInfoUnit();

        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) GtlUtils.getFinApp().getAccountManager();
        WEB3EquityProductManager l_productManager = 
            (WEB3EquityProductManager) (GtlUtils.getFinApp().getTradingModule(ProductTypeEnum.EQUITY)).getProductManager();
        
        long l_lngBranchId = ((InsiderRow)(l_insiders.getDataSourceObject())).getBranchId();
        long l_lngAccountId = ((InsiderRow)(l_insiders.getDataSourceObject())).getAccountId();
        long l_lngProductId = ((InsiderRow)(l_insiders.getDataSourceObject())).getProductId();
        
        try
        {
            //部店コード
            String l_strBranchCode = l_accMgr.getBranch(l_lngBranchId).getBranchCode();
            //口座コード
            String l_strAccountCode = ((WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_lngAccountId)).getDisplayAccountCode();
            //顧客名（漢字）
            String l_strAccountName = 
                ((MainAccountRow)l_accMgr.getMainAccount(l_lngAccountId).getDataSourceObject()).getFamilyName();
            //銘柄コード
            String l_strProductCode = ((WEB3EquityProduct)l_productManager.getProduct(l_lngProductId)).getProductCode();
            //銘柄名
            String l_strProductName = 
                ((WEB3EquityProduct)l_productManager.getProduct(l_lngProductId)).getStandardName();
        
            l_insiderInfoUnit.branchCode = l_strBranchCode;
            l_insiderInfoUnit.accountCode = l_strAccountCode;
            l_insiderInfoUnit.accountName = l_strAccountName;
            l_insiderInfoUnit.productCode = l_strProductCode;
            l_insiderInfoUnit.productName = l_strProductName;
            l_insiderInfoUnit.relationCode = l_insiders.getRelationDiv();
            l_insiderInfoUnit.executive = l_insiders.getOfficerName();
            l_insiderInfoUnit.position = l_insiders.getPostName();
            if(((InsiderRow)l_insiders.getDataSourceObject()).getRegistDivIsSet())
            {
                l_insiderInfoUnit.registStateDiv = ((InsiderRow)l_insiders.getDataSourceObject()).getRegistDiv();
            }
            
        }
        catch(NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        return l_insiderInfoUnit;
    }

}
@
