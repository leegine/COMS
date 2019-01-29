head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAccProductTradeStopListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者顧客銘柄別取引停止一覧サービス実装クラス)
                        (WEB3AdminEquityAccProductTradeStopListServiceImpl.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AccountProductOrderStopParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.WEB3AdminPMEquityDataManager;
import webbroker3.eqtypeadmin.define.WEB3AdminKeyItemDef;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopInfoUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccTradeStopSortKey;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopListService;

import webbroker3.equity.WEB3EquityProductManager;

/**
 * （管理者顧客銘柄別取引停止一覧サービス実装クラス）<BR>
 * <BR>
 * 管理者顧客銘柄別取引停止一覧サービス実装クラス<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopListServiceImpl class<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopListServiceImpl extends
         WEB3ClientRequestService implements WEB3AdminEquityAccProductTradeStopListService
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopListServiceImpl.class);
   /**
    * @@roseuid 41FD944D0186
    */
   public WEB3AdminEquityAccProductTradeStopListServiceImpl()
   {

   }

   /**
    * 顧客銘柄別取引停止一覧表示処理を行う。<BR>
    * <BR>
    * this.get一覧画面()をコールする。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Execute WEB3AdminEquityAccProductTradeStopListService screen process<BR>
    * <BR>
    * call this.getListScreen()<BR>
    * <BR>
    * @@param l_request - （リクエスト）<BR>
    * <BR>
    * リクエスト<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * @@return WEB3GenResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@roseuid 41987EF002D6
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
                            "WEB3AdminEquityAccProductTradeStopListServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminPMAccProductTradeStopListRequest)
        {
            try
            {
                l_response = this.getListScreen((WEB3AdminPMAccProductTradeStopListRequest)
                                                                                         l_request);
            } catch (DataQueryException l_exce)
            {
                log.debug(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            } catch (DataNetworkException l_exce)
            {
                log.debug(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            } catch (NotFoundException l_exce)
            {
                log.debug(l_exce.getMessage());
                String l_strMsg = "Error while aquiring the Data ";
                log.error(l_strMsg, l_exce);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exce.toString(),
                    l_exce);
            }
        } else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 顧客銘柄別取引停止一覧リクエスト");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

   /**
    * (get一覧画面())<BR>
    * <BR>
    * 顧客銘柄別取引停止一覧表示処理を行う。<BR>
    * <BR>
    * シーケンス図<BR>
    * 「(管理者顧客銘柄別取引停止一覧サービス)get一覧画面」
    * 参照<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (getListScreen)<BR>
    * <BR>
    * Execute WEB3AdminEquityAccProductTradeStopListService screen process<BR>
    * <BR>
    * Refer to the sequence diagram "(administrator; account product trade stop list
    * service) getListScreen"<BR>
    * <BR>
    * @@param l_request - リクエストデータ<BR>
    * <BR>
    * 管理者・顧客銘柄別取引停止一覧リクエストオブジェクト<BR>
    * <BR>
    * ---------<English>----------<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * WEB3AdminPMAccProductTradeStopListRequest object<BR>
    * <BR>
    * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse
    * @@throws WEB3BaseException WEB3BaseException
    * @@throws DataNetworkException DataNetworkException
    * @@throws DataQueryException DataQueryException
    * @@throws DataFindException DataFindException
    * @@throws NotFoundException NotFoundException
    * @@roseuid 41987F230268
    */
    protected WEB3AdminPMAccProductTradeStopListResponse
        getListScreen(WEB3AdminPMAccProductTradeStopListRequest l_request)
            throws WEB3BaseException, DataFindException, DataNetworkException,
                DataQueryException, NotFoundException
    {
        WEB3AdminPMAccProductTradeStopListResponse l_response = null;
        WEB3Administrator l_web3Administrator = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_genTradeAccountManager = null;
        AccountProductOrderStopParams[]  l_accountProductOrderStopParams = null;
        WEB3AdminPMAccProductTradeStopInfoUnit l_accProductTradeStopInfoUnit =
            new WEB3AdminPMAccProductTradeStopInfoUnit();
        WEB3AdminPMEquityDataManager l_equityDataManager = new WEB3AdminPMEquityDataManager();
        WEB3AdminPMAccTradeStopSortKey[] l_sortKeys = null;
        WEB3PageIndexInfo l_pageIndexInfo = null;
        String[] l_strBranchCodes = null;
        String l_strAccountCode = null;
        String l_strProductCode = null;
        String l_strQueryCond = null;
        String l_strInstituionCode = null;
        String l_strSortCondList = null;
        Object[] l_dataContainers = null;
        List l_lisGetAccTradeStopInfo = null;
        ArrayList l_lisProductTradeStopInfoUnit = null;
        WEB3AdminPMAccProductTradeStopInfoUnit[] l_productTradeStopInfoUnits = null;
        int i = 0;
        int l_intPageIndex = 0;
        int l_intPageSize = 0;
        int l_intProductStopSize = 0;

        // Step 1.1 Input request values are validated
        l_request.validate();

        // Step 1.2 get the instance of Administrator
        l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // Step  1.3 validate authority
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.EQTYPE_ACCOUNT_PRODUCT_STOP,
            false);

        //Step 1.4 call validate Branch Permission of  Administrator
        l_strBranchCodes = l_request.branchCodeList;
        l_web3Administrator.validateBranchPermission(l_strBranchCodes);

        /*Creating the variable to store the values of l_request,
            which are called in the following steps.*/
        l_strAccountCode = l_request.accountCode;
        l_strProductCode = l_request.productCode;
        l_sortKeys = l_request.sortKeys;

        l_strInstituionCode = l_web3Administrator.getInstitutionCode();

        //Step 1.5 Create the query String
        l_strQueryCond = this.createQueryString
                                (l_strInstituionCode, l_strBranchCodes, l_strAccountCode, l_strProductCode);
		
        //Step 1.6 Create the Object to hold the dataContainer
        l_dataContainers = this.createQueryDataContainer
                                (l_strInstituionCode,l_strBranchCodes,l_strAccountCode, l_strProductCode);

        //Step 1.7 Create the Sord condition List
        l_strSortCondList = this.createSortCond(l_sortKeys);

        //Step 1.8  Acquire the list of account product trade stop
        l_genTradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        l_lisGetAccTradeStopInfo  = l_genTradeAccountManager.getAccountProductOrderStopList
                      (l_strQueryCond, l_dataContainers, l_strSortCondList);

        //Step 1.9 Check for List is null
        if (l_lisGetAccTradeStopInfo == null)
        {
            // 1.9.1 Create a response object
           l_response = (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();

           //Step 1.9.2 Set the property to the response object
           l_response.totalPages = "1";
           l_response.totalRecords = "0";
           l_response.pageIndex = "1";
           l_response.accProductTradeStopInfo = null;

           //Step 1.9.3 Return the response object
           return l_response;
        }

        //Step 1.10 Create ArrayList to store WEB3AdminPMAccProductTradeStopInfoUnit
        l_lisProductTradeStopInfoUnit = new ArrayList();

        /*
         * Step 1.11 Calculation of From Index and LastIndex for the loop
         * Code is been modified as we are using the standard function for
         * From Index and To Index
         */
        l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        l_intPageSize = Integer.parseInt(l_request.pageSize);
        l_pageIndexInfo =
            new WEB3PageIndexInfo(l_lisGetAccTradeStopInfo, l_intPageIndex, l_intPageSize);

        l_accountProductOrderStopParams =
            (AccountProductOrderStopParams[]) l_pageIndexInfo
            .getArrayReturned(AccountProductOrderStopParams.class);

        l_intProductStopSize = l_accountProductOrderStopParams.length;
        //Step 1.11 Loop process from fromIndex to toIndex
        for (i = 0; i < l_intProductStopSize; i++)
        {
            //Step 1.11.1 Create WEB3AdminPMAccProductTradeStopInfoUnit
            l_accProductTradeStopInfoUnit =
                 l_equityDataManager.createAccProductTradeStopInfoUnit
                        (l_accountProductOrderStopParams[i]);

            //Step 1.11. 2 Set the created WEB3AdminPMAccProductTradeStopInfoUnit to ArrayList
            l_lisProductTradeStopInfoUnit.add(l_accProductTradeStopInfoUnit);
        }

        //Step 1.12 Create a array to store the Product Trade Stop Info Unit list
        l_productTradeStopInfoUnits =
            new WEB3AdminPMAccProductTradeStopInfoUnit[l_lisProductTradeStopInfoUnit.size()];
        l_lisProductTradeStopInfoUnit.toArray(l_productTradeStopInfoUnits);

        // 1.13  Create a response object
        l_response = (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();

        //Step 1.14 Set the property to the response object
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(
            l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(
            l_pageIndexInfo.getTotalRecords());
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
        l_response.accProductTradeStopInfo = l_productTradeStopInfoUnits;

        //Step 1.15 Return the response object
        return l_response;
    }

   /**
    * （create検索条件文字列）<BR>
    * <BR>
    * 検索条件文字列を作成する。<BR>
    * <BR>
    * １）以下の条件を表す検索条件文字列を作成する。<BR>
    * <BR>
    * 　@[条件]<BR>
    * 　@　@証券会社コード = パラメータ.証券会社コード　@かつ<BR>
    * 　@　@適用終了年月日 >= 現在日付<BR>
    * <BR>
    * 　@検索条件文字列 = " institution_code = ? "<BR>
    * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and apply_end_date >= ? "<BR>
    * <BR>
    * ２）パラメータ.部店コード != nullの場合、<BR>
    * 　@以下の条件を検索条件文字列に追加する。<BR>
    * 　@※部店コードに該当する部店IDを検索条件とする。<BR>
    * <BR>
    * 　@検索条件文字列 += "and branch_id = ? "<BR>
    * <BR>
    * ３）パラメータ.口座コード != nullの場合、<BR>
    * 　@以下の条件を検索条件文字列に追加する。<BR>
    * 　@※口座コードに該当する口座IDを検索条件とする。<BR>
    * <BR>
    * 　@検索条件文字列 += "and account_id = ? "<BR>
    * <BR>
    * ４）パラメータ.銘柄コード != nullの場合、<BR>
    * 　@以下の条件を検索条件文字列に追加する。<BR>
    * 　@※銘柄コードに該当する銘柄IDを検索条件とする。<BR>
    * <BR>
    * 　@検索条件文字列 += "and product_id = ? "<BR>
    * <BR>
    * ５）作成した検索条件文字列を返却する。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (createQueryString)<BR>
    * <BR>
    * 1)Create query string<BR>
    * <BR>
    * 　@[Condition]<BR>
    * 　@　@institution_code = parameter.institutionCode　@and<BR>
    * 　@　@apply_start_date >= currentDate<BR>
    * <BR>
    * 　@l_strQueryCond = " institution_code = ? "<BR>
    * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "and apply_end_date >= ? "<BR>
    * <BR>
    * 2)If parameter.branchCode != null<BR>
    * 　@Add the following condition tol_strQueryCond<BR>
    * 　@※Assumed that branch_id conrresponding to branchCode is l_strQueryCond .<BR>
    * <BR>
    * 　@l_strQueryCond  += "and branch_id = ? "<BR>
    * <BR>
    * 3)If parameter.accountCode != null<BR>
    * 　@Add the following condition to l_strQueryCond .<BR>
    * 　@※Assumed that account_id corresponding to accountCode is l_strQueryCond <BR>
    * <BR>
    * 　@l_strQueryCond  += "and account_id = ? "<BR>
    * <BR>
    * 4)If parameter.productCode != null<BR>
    * 　@Add the following condition to l_strQueryCond<BR>
    * 　@※Assumed that product_id corresponding to productCode is l_strQueryCond <BR>
    * <BR>
    * 　@l_strQueryCond  += "and product_id = ? "<BR>
    * <BR>
    * 5)Return created l_strQueryCond <BR>
    * <BR>
    * @@param l_strBranchCode - （部店コード）<BR>
    * <BR>
    * 部店コード<BR>
    * <BR>
    * l_strBranchCode<BR>
    * <BR>
    * @@param l_strAccountCode - （口座コード）<BR>
    * <BR>
    * 口座コード<BR>
    * <BR>
    * l_strAccountCode<BR>
    * <BR>
    * @@param l_strProductCode - （銘柄コード）<BR>
    * <BR>
    * 銘柄コード
    * <BR>
    * l_strProductCode<BR>
    * <BR>
    * @@return java.lang.String
    * @@roseuid 4198823C01BD
    */
   protected String
	 createQueryString(String l_strInstituionCode, String[] l_strBranchCodes, String l_strAccountCode, String l_strProductCode)
	 throws WEB3BaseException,DataFindException, DataNetworkException, DataQueryException
   {
	   final String STR_METHOD_NAME = "createQueryString()";
	   log.entering(STR_METHOD_NAME);
	    WEB3AdminPMEquityDataManager l_equityDataManager = new WEB3AdminPMEquityDataManager();
	
	   //Step 1: Create query string
	   String l_strQueryCond = new String("institution_code = ? and apply_end_date >= ? ");

	   //Step  2: If parameter.branchCode is not null
	   String[] l_strBranchIdList = l_equityDataManager.getBranchId(l_strInstituionCode,l_strBranchCodes);
	   if (l_strBranchIdList == null)
	   {
		   String l_strMsg = "No data for the Branch";
		   log.error("Error while aquiring the Branch Id ");
               throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                   this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg);
	   }
       l_strQueryCond += "and branch_id in (";
       StringBuffer l_strEdit = new StringBuffer();
	   for(int i = 0; i < l_strBranchIdList.length ; i++)
	   {
	       l_strEdit.append(" ?,");
	   } 
       l_strQueryCond += (l_strEdit.substring(0,l_strEdit.length() - 1) + ") ").toString();

	   //Step  3: If parameter.accountCode is not null
	   if (l_strAccountCode != null)
	   {
		   l_strQueryCond += "and account_id = ? ";
	   }

	   //Step  4: If parameter.productCode is not null
	   if (l_strProductCode != null)
	   {
		   l_strQueryCond += "and product_id = ? ";
	   }

	   log.exiting(STR_METHOD_NAME);

	   //StepL 5 return the string object
	   return l_strQueryCond;
   }

   /**
    * （create検索条件データコンテナ）<BR>
    * <BR>
    * 検索条件データコンテナを作成する。<BR>
    * <BR>
    * １）ArrayListを生成する。<BR>
    * <BR>
    * ２）以下の値を上から順に生成したArrayListに<BR>
    * 　@　@セットする。<BR>
    * 　@　@・パラメータ.証券会社コード<BR>
    * 　@　@・現在時刻(*1)の日付部分<BR>
    * <BR>
    * ３）パラメータ.部店コード != nullの場合、<BR>
    * 　@パラメータ.部店コードに該当する部店IDをArrayListに追加する。<BR>
    * 　@※部店コードに該当する部店IDが取得できなかった場合、<BR>
    * 　@　@「該当部店データなし」の例外をスローする。<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_01386<BR>
    * <BR>
    * ４）パラメータ.口座コード != nullの場合、<BR>
    * 　@パラメータ.口座コードに該当する口座IDをArrayListに追加する。<BR>
    * 　@※口座コードに該当する口座IDが取得できなかった場合、<BR>
    * 　@　@「該当顧客データなし」の例外をスローする。<BR>
    * <BR>
    * ５）パラメータ.銘柄コード != nullの場合、<BR>
    * 　@以下の条件により、パラメータセットを追加する。<BR>
    * <BR>
    * 　@[パラメータ.銘柄コード == "00000"(全銘柄)の場合]<BR>
    * 　@　@0をArrayListに追加する。<BR>
    * 　@[上記以外の場合]<BR>
    * 　@　@パラメータ.銘柄コードに該当する銘柄IDをArrayListに追加する。<BR>
    * 　@　@※銘柄コードに該当する銘柄IDが取得できなかった場合、<BR>
    * 　@　@「該当銘柄データなし」の例外をスローする。<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_00391<BR>
    * <BR>
    * ６）作成したArrayList.toArray()の戻り値を返却する。<BR>
    * <BR>
    * (*1)現在時刻<BR>
    * ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)<BR>
    * にて取得した現在時刻<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (createQueryDataContainer)<BR>
    * <BR>
    * Carete queryDataContainer<BR>
    * <BR>
    * 1)Created ArrayList<BR>
    * <BR>
    * 2)Set the following values to the created ArrayList.<BR>
    * 　@　@・parameter.institutionCode<BR>
    * 　@　@・Date in timeStamp(*1)<BR>
    * <BR>
    * 3)If parameter.branchCode != null<BR>
    * 　@Add branch_id corresponding to parameter.branchCode to ArrayList<BR>
    * 　@※If it is disable to get  branch_id corresponding to branchCode,<BR>
    * 　@　@Throw the exception [No corresponding branch data]<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_01386<BR>
    * <BR>
    * 4)If parameter.accountCode != null<BR>
    * 　@Add account_id corresponding to accountCode to ArrayList<BR>
    * 　@※If it is disable to get  account_id corresponding to accountCode,<BR>
    * 　@　@Throw the exception [No corresponding account data]<BR>
    * <BR>
    * 5)If parameter.productCode != null<BR>
    * 　@Add parameter set based on the following conditions.<BR>
    * <BR>
    * 　@[If parameter.productCode == "00000"(all products)]<BR>
    * 　@　@Add 0 to ArrayList<BR>
    * 　@[For other cases]<BR>
    * 　@　@Add product_id corresponding to parameter.productCode to ArrayList<BR>
    * 　@　@※If it is disable to get product_id corresponding to productCode,<BR>
    * 　@　@Throw the exception [No corresponding product data]<BR>
    * <BR>
    * class : WEB3BusinessLayerException<BR>
    * tag : BUSINESS_ERROR_00391<BR>
    * <BR>
    *
    * ６）Return the return value of created ArrayList.toArray()<BR>
    * <BR>
    * (*1)timeStamp<BR>
    * timeStamp acquired at <BR>
    * ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManageme
    * nt.TIMESTAMP_TAG)<BR>
    * <BR>
    * @@param l_strInstitutionCode - （証券会社コード）<BR>
    * <BR>
    * 証券会社コード<BR>
    * <BR>
    * l_strInstitutionCode<BR>
    * <BR>
    * @@param l_strBranchCode - （部店コード）<BR>
    * <BR>
    * 部店コード<BR>
    * <BR>
    * l_strBranchCode<BR>
    * <BR>
    * @@param l_strAccountCode - （口座コード）<BR>
    * <BR>
    * 口座コード<BR>
    * <BR>
    * l_strAccountCode<BR>
    * <BR>
    * @@param l_strProductCode - （銘柄コード）<BR>
    * <BR>
    * @@throws WEB3BaseException WEB3BaseException
    * 銘柄コード
    * <BR>
    * l_strProductCode<BR>
    * <BR>
    * @@return Object[]
    * @@throws WEB3BaseException WEB3BaseException
    * @@throws DataFindException DataFindException
    * @@throws DataNetworkException DataNetworkException
    * @@throws DataQueryException DataQueryException
    * @@roseuid 4198823C01DC
    */
    protected Object[] createQueryDataContainer(String l_strInstitutionCode,
        String[] l_strBranchCodes, String l_strAccountCode, String l_strProductCode)
            throws WEB3BaseException, DataFindException, DataNetworkException,
                DataQueryException
    {
        final String STR_METHOD_NAME = "createQueryDataContainer()";
        log.entering(STR_METHOD_NAME);
        
		//getBranchId
		WEB3AdminPMEquityDataManager l_equityDataManager = new WEB3AdminPMEquityDataManager();
		String[] l_strBranchIdList = l_equityDataManager.getBranchId(l_strInstitutionCode,l_strBranchCodes);
		if (l_strBranchIdList == null)
		{
			String l_strMsg = "No data for the Branch";
			log.error("Error while aquiring the Branch Id ");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01386,
				this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg);

		}

        String l_strAccountId = null;
        String l_strProductId = null;
        Date l_dateTimeStamp = new Date();
        Object[] l_queryData = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager = null;
        WEB3GentradeBranch l_gentradeBranch = null;
        long l_lngBranchId;
        long l_lngAccountId;
        long l_lngProductId;

        //Step 1 : Created ArrayList
        ArrayList l_arrQueryDataList = new ArrayList();

        //Step 2 : Add the Instititution Code and  Time Stamp to the ArrayList
        l_arrQueryDataList.add(l_strInstitutionCode);
        l_dateTimeStamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
                                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        l_arrQueryDataList.add(WEB3DateUtility.toDay(l_dateTimeStamp));
		
        l_gentradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        /*Step 3 : If Branch Code is not null then add the l_strBranchCode to array list,
                        else throw  exception*/
        if (l_strBranchCodes != null)
        {
            for (int i = 0; i < l_strBranchIdList.length; i++)
            {
                l_arrQueryDataList.add(l_strBranchIdList[i]);
            }	
        }

        /*Step 4 : If Account Code is not null then add the l_strAccountCode to array list,
                        else throw  exception*/
        if (l_strAccountCode != null)
        {
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                try
                {
					WEB3GentradeMainAccount  l_gentradeMainAccount = (WEB3GentradeMainAccount)
					    l_gentradeAccountManager.getMainAccount(l_strInstitutionCode,
					    l_strBranchCodes[i], l_strAccountCode);
						l_lngAccountId = l_gentradeMainAccount.getAccountId();
						l_strAccountId = new Long(l_lngAccountId).toString();
						l_arrQueryDataList.add(l_strAccountId);
						break;
                } catch (WEB3SystemLayerException l_exp){
                	if (i == l_strBranchCodes.length -1)
                	{
						String l_strMsg = "No data for the Account";
						log.error("Error while aquiring the Account Id ");
						throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_01387,
							this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg, l_exp);
                	}
                }
            }
        }

        /*Step 5 : If Product Code is not null then add corresponding l_strProductCode to
                        array list, else throw  exception*/
        if (l_strProductCode != null)
        {
            /*Step 5.1 : if ProductCode == "00000"(all products) then add "0" to array list
                             else add l_strProductCode to the arraylist */
            if (l_strProductCode.equals("00000"))
            {
                l_arrQueryDataList.add("0");
            } else
            {
                try
                {

                    TradingModule l_tradingModule =
                        l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                    WEB3EquityProductManager l_equityProductManager =
                        (WEB3EquityProductManager) l_tradingModule.getProductManager();
                    WEB3GentradeInstitution l_instituion =
                        new WEB3GentradeInstitution(l_strInstitutionCode);
                    EqTypeProduct l_eqTypeProduct  =
                        l_equityProductManager.getProduct(l_instituion, l_strProductCode);
                    l_lngProductId = l_eqTypeProduct.getProductId();
                    l_strProductId = new Long(l_lngProductId).toString();
                    l_arrQueryDataList.add(l_strProductId);
                } catch (NotFoundException l_exp)
                {
                    String l_strMsg = "No data for the Product";
                    log.error("Error while aquiring the Product Id ");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00391,
                       this.getClass().getName() + "." + STR_METHOD_NAME, l_strMsg, l_exp);
                }
            }
        }
        //Step 6:  return value of created ArrayList.toArray()
        l_queryData = l_arrQueryDataList.toArray();
        log.exiting(STR_METHOD_NAME);
        return l_queryData;
    }

   /**
    * （createソート条件）<BR>
    * <BR>
    * ソート条件を作成する。<BR>
    * <BR>
    * １）空のソート条件文字列("")を作成する。<BR>
    * ２）パラメータ.ソートキーの要素数分以下の処理を<BR>
    * 　@繰り返す。<BR>
    * 　@２－１）処理対象のソートキー.キー項目の値により、<BR>
    * 　@　@　@　@　@作成したソート条件文字列に条件を追加する。<BR>
    * 　@　@　@　@　@※ソート条件文字列が空("")でない場合は、以下の処理を行う前に<BR>
    * 　@　@　@　@　@　@","(カンマ)をソート条件文字列に追加すること。<BR>
    * <BR>
    * 　@　@　@　@　@キー項目が、<BR>
    * 　@　@　@　@　@["部店コード"の場合]<BR>
    * 　@　@　@　@　@　@ソート条件文字列 += "branch_id "<BR>
    * 　@　@　@　@　@["顧客コード"の場合]<BR>
    * 　@　@　@　@　@　@ソート条件文字列 += "account_id "<BR>
    * 　@　@　@　@　@["銘柄コード"の場合]<BR>
    * 　@　@　@　@　@　@ソート条件文字列 += "product_id "<BR>
    * 　@　@　@　@　@["有効期限From"の場合]<BR>
    * 　@　@　@　@　@　@ソート条件文字列 += "apply_start_date "<BR>
    * <BR>
    * 　@２－２）処理対象のソートキー.昇順／降順の値により、<BR>
    * 　@　@　@　@　@昇順／降順をソート条件文字列に追加する。<BR>
    * <BR>
    * 　@　@　@　@　@昇順／降順が、<BR>
    * 　@　@　@　@　@["A：昇順"の場合]<BR>
    * 　@　@　@　@　@　@ソート条件文字列 += "asc "<BR>
    * 　@　@　@　@　@["D：降順"の場合]<BR>
    * 　@　@　@　@　@　@ソート条件文字列 += "desc "<BR>
    * <BR>
    * ２）作成したソート条件文字列を返却する。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (createSortCond)<BR>
    * <BR>
    * Create sortCond<BR>
    * <BR>
    * 1)Create an empty sortCondList("")<BR>
    * 2)Loop process for as many times as elements of parameter.sortKeys <BR>
    * 　@2-1)Add the conditions to created sortCondList based on the values of
    * sortKeys.keyItem for the process<BR>
    * 　@　@　@　@　@※If sortCondList is not empty(""), add ","(comma) to sortCondList
    * before processing.<BR>
    * <BR>
    * 　@　@　@　@　@If a keyItem is ["branchCode"]<BR>
    * 　@　@　@　@　@　@sortCondList += "branch_id "<BR>
    * 　@　@　@　@　@If a keyItem is ["accountCode"]<BR>
    * 　@　@　@　@　@　@sortCondList += "account_id "<BR>
    * 　@　@　@　@　@If a keyItem is ["productCode"]<BR>
    * 　@　@　@　@　@　@sortCondList += "product_id "<BR>
    * 　@　@　@　@　@If a keyItem is [" expirationStartDate"]<BR>
    * 　@　@　@　@　@　@sortCondList += "apply_start_date "<BR>
    * <BR>
    * 　@2-2）Add ascDesc to sortCondList according to the value of sortKeys.ascDesc
    * for the process<BR>
    * <BR>
    * 　@　@　@　@　@If ascDesc is ["A: ASC"]<BR>
    * 　@　@　@　@　@　@sortCondList += "asc "<BR>
    * 　@　@　@　@　@If ascDesc is ["D: DESC"]<BR>
    * 　@　@　@　@　@　@sortCondList += "desc "<BR>
    * <BR>
    * 2)Return the created sortCondList<BR>
    * <BR>
    * @@param l_sortKeys - （ソートキー）
    * <BR>
    * ソートキー<BR>
    * <BR>
    * l_sortKeys<BR>
    * <BR>
    * @@return java.lang.String
    * @@roseuid 4198823C01FB
    */
    protected String createSortCond(WEB3AdminPMAccTradeStopSortKey[] l_sortKeys)
    {
        int i = 0;
        int l_intLen = l_sortKeys.length;

        //Step 1: Create an empty sortCondList("")
        String l_strSortCondList = new String("");

        //Step 2: Loop process for as many times as elements of parameter.sortKeys
        for (i = 0; i < l_intLen; i++)
        {
            /*If sortCondList is not empty(""), add ","(comma) to sortCondList
                before processing.*/
            if (!l_strSortCondList.equals(""))
            {
                l_strSortCondList += ", ";
            }

            /*Setp 2-1: Add the conditions to created sortCondList based on the values of
                        sortKeys.keyItem for the process*/
            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.BRANCH_CODE))
            {
                l_strSortCondList += "branch_id";
            }

            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.ACCOUNT_CODE))
            {
                l_strSortCondList += "substr(account_id,9,6)";
            }

            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.PRODUCT_CODE))
            {
                l_strSortCondList += "product_id";
            }

            if (l_sortKeys[i].keyItem.equals(WEB3AdminKeyItemDef.TERM_FROM))
            {
                l_strSortCondList += "apply_start_date";
            }

            //Step 2-2: Add ascDesc to sortCondList according to the value of sortKeys.ascDesc
            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.ASC))
            {
                l_strSortCondList += " asc";
            }

            if (l_sortKeys[i].ascDesc.equals(WEB3AscDescDef.DESC))
            {
                l_strSortCondList += " desc";
            }
        }
        return l_strSortCondList;
    }
}
@
