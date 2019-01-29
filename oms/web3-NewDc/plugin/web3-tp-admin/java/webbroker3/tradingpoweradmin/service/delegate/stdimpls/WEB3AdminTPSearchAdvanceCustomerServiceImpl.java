head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPSearchAdvanceCustomerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金維持率割れ/立替金発生顧客検索サービスImplクラス(WEB3AdminTPSearchAdvanceCustomerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) 新規作成
Revision History : 2008/10/20 張明鹿(中訊) モデルNo.037
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.ArrayListPage;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.data.TpCalcResultEquityDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.define.WEB3TPDepositDivDef;
import webbroker3.tradingpoweradmin.data.RequisitionAccountEquityRow;
import webbroker3.tradingpoweradmin.data.RequisitionAccountMarginRow;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPCustomerAttributeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPMarkToMarketEndDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPProcessManagementIdDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceDetailUnit;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPSearchAdvanceCustomerService;
import webbroker3.util.WEB3LogUtility;

/**
 * 保証金維持率割れ/立替金発生顧客検索サービスImplクラス
 */
public class WEB3AdminTPSearchAdvanceCustomerServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPSearchAdvanceCustomerService
{
    /**
     * (ログ)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPSearchAdvanceCustomerServiceImpl.class);

    /**
     * (デバッグison)
     */
    private static boolean DBG = log.ison();
    
    /**
     * (コンストラクタ)
     */
    public WEB3AdminTPSearchAdvanceCustomerServiceImpl() 
    {    
    }
   
    /**
     * (exetute)<BR>
     * <BR>
     * シーケンス図<BR>
     * 「execute」参照。<BR>
     * <BR>
     * @@param l_request
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
   		throws WEB3BaseException
   	{
        final String METHOD_NAME = "execute";

        log.entering(METHOD_NAME);
      
        //レスポンス
        WEB3GenResponse l_response = null;

        //保証金維持率割れ/立替金発生顧客検索入力画面表示
        if(l_request instanceof WEB3AdminTPAdvanceCustomerSearchInputRequest)
        {
            l_response =  getAdvancCustomerInput( (WEB3AdminTPAdvanceCustomerSearchInputRequest)l_request);           
        }
        else
        //保証金維持率割れ/立替金発生顧客検索一覧画面表示
		if(l_request instanceof WEB3AdminTPAdvanceCustomerSearchListRequest)
		{
		    l_response =  getAdvanceCustomerList((WEB3AdminTPAdvanceCustomerSearchListRequest)l_request);           
		}
		else
        //保証金維持率割れ/立替金発生顧客画面表示
        if(l_request instanceof WEB3AdminTPAdvanceCustomerDetailRequest)
        {
          l_response =  this.getAdvanceCustomerDetail((WEB3AdminTPAdvanceCustomerDetailRequest)l_request);           
        }
        else
		{
		    //予期せぬエラー
		    throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());           
		}
        
		log.exiting(METHOD_NAME);
		return l_response;
      
   	}

    /**
     * (get保証金維持率割れ/立替金発生顧客入力)<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get保証金維持率割れ/立替金発生顧客入力」参照。<BR>
     * <BR>
     * @@param l_request 
     * @@return WEB3AdminTPAdvanceCustomerSearchInputResponse
     */
    protected WEB3AdminTPAdvanceCustomerSearchInputResponse getAdvancCustomerInput(WEB3AdminTPAdvanceCustomerSearchInputRequest l_request)
     throws WEB3BaseException 
    {
        final String METHOD_NAME = "getAdvancCustomerInput";
        log.entering(METHOD_NAME);

         //管理者
         WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
         //管理者権限チェック
         l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
         
         
         //値洗い終了区分取取得
         String[] l_markToMarketEndDiv = getMarkToMarketEndDiv( l_admin );
         
         //レスポンス
         WEB3AdminTPAdvanceCustomerSearchInputResponse l_response = (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
         
         //プロパティセット
         l_response.markToMarketEndDiv = l_markToMarketEndDiv;//値洗い終了区分
         
         //レスポンス返却
         return l_response;
    }

    /**
     * (get保証金維持率割れ/立替金発生顧客一覧)<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get保証金維持率割れ/立替金発生顧客一覧」参照。<BR>
     * <BR>
     * @@param l_request 
     * @@return WEB3AdminTPAdvanceCustomerSearchListResponse
     */
    protected WEB3AdminTPAdvanceCustomerSearchListResponse getAdvanceCustomerList(WEB3AdminTPAdvanceCustomerSearchListRequest l_request)
     throws WEB3BaseException 
    {
        final String METHOD_NAME = "getAdvanceCustomerList";
        log.entering(METHOD_NAME);

        //リクエストの属性妥当性チェック
        l_request.validate();
        
         //管理者
         WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
         
         //管理者権限チェック
         l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
                  
        //値洗い終了区分取取得
        String[] l_markToMarketEndDiv = getMarkToMarketEndDiv( l_admin );

         //保証金維持率割れ/立替金発生顧客リスト
         List l_list = new ArrayList();
         
         //部門コードリスト
         String[] branchCode = l_request.branchCode;
         
         //部店コードを昇順にソート
         Arrays.sort( branchCode );
         
         int l_missCount = 0;
         for(int i=0; i<branchCode.length; i++ )
         {
             //部店権限チェック
             l_admin.validateBranchPermission( branchCode[ i ] );   
             
             //値洗い区分チェック
             try
             {
                 this.validateToMarketEndDiv(l_admin.getInstitutionCode(), branchCode[i], l_request.markToMarketDiv);                 
             }
             catch(WEB3BaseException se)
             {
                 l_missCount++;
                 if(l_missCount == branchCode.length)
                 {
                     throw se;
                 }                 
             }
             
         }
         
         ListPage l_lisRows = null; 
         WEB3AdminTPAdvanceCustomerUnit l_unit = null;
         
         //現物顧客
         if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST ) )
         {
             //get請求発生顧客Params一覧<現物顧客>
             l_lisRows = this.getRequisitionAccountEquityParamsList(l_admin, l_request);
             
             for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
             {
                 RequisitionAccountEquityRow l_row = (RequisitionAccountEquityRow)l_iter.next();
                 //保証金維持率割れ/立替金発生顧客ユニット
                 //get保証金維持率割れ/立替金発生顧客<現物顧客>
                 l_unit = getAdvanceCustomerEquity(l_row);
                 if( l_unit != null )
                 {
                     l_list.add( l_unit );
                 }
             }                        
             
         }
         //信用顧客
         else
         {
             //get請求発生顧客Params一覧<信用顧客>
             l_lisRows = this.getRequisitionAccountMarginParamsList(l_admin, l_request);

             for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
             {
                 RequisitionAccountMarginRow l_row = (RequisitionAccountMarginRow)l_iter.next();

                 //保証金維持率割れ/立替金発生顧客ユニット
                 //get保証金維持率割れ/立替金発生顧客<現物顧客>
                 l_unit = this.getAdvanceCustomerMargin(l_row);
                 if( l_unit != null )
                 {
                     l_list.add( l_unit );
                 }

             }
         }                        
        
         //レスポンス
         WEB3AdminTPAdvanceCustomerSearchListResponse l_response = (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
                  
         //プロパティセット
         l_response.markToMarketEndDiv = l_markToMarketEndDiv;//値洗い終了区分
         l_response.adminAdvanceCustomerUnits = (WEB3AdminTPAdvanceCustomerUnit[])l_list.toArray(new WEB3AdminTPAdvanceCustomerUnit[l_list.size()]);//立替金顧客一覧
         if(l_lisRows != null)
         {
             l_response.totalPages   = format(l_lisRows.totalPages());//総ページ数
             l_response.totalRecords = format(l_lisRows.totalSize());//総レコード数
             l_response.pageIndex    = format(l_lisRows.pageNumber() + 1);//表示ページ番号(index+1)
             
         }
         else
         {
             l_response.pageIndex    = l_request.pageIndex;
             l_response.totalPages   = "0";//総ページ数
             l_response.totalRecords = "0";//総レコード数
         }
         
         log.exiting(METHOD_NAME);
         return l_response;             
         
    }
    
    /**
     * (get請求発生顧客一覧<現物顧客>)<BR>
     * <BR>
     * 請求発生顧客<現物顧客>テーブルより検索。<BR>
     * <BR>
     * @@param 証券会社ID
     * @@param 部店コード
     * @@param 顧客コード
     * @@return List 顧客Paramsリスト
     */
    protected ListPage getRequisitionAccountEquityParamsList(WEB3Administrator l_admin, WEB3AdminTPAdvanceCustomerSearchListRequest l_request)
    	throws WEB3BaseException
    {
        final String METHOD_NAME = "getRequisitionAccountEquityParamsList";

        //画面から来るページのインデックスは１から始まる値
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);
        
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //管理者所属の証券会社
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        log.debug("l_request.branchCode.length" + l_request.branchCode.length);
        
        //条件作成
        //証券会社コード
        l_sbWhere.append("institution_code = ? " );
        l_lisBindVars.add(l_institution.getInstitutionCode());
        
        //部店コード
        for(int i = 0; i < l_request.branchCode.length; i++)
        {
            if(i > 0)
            {
                l_sbWhere.append(", ");
            }
            else
            {
                l_sbWhere.append("and branch_code in (");
            }
            l_sbWhere.append("?");                    
            l_lisBindVars.add(l_request.branchCode[i]);
        }
        if(l_request.branchCode.length > 0)
            l_sbWhere.append(")");  

        //顧客コード
        if(l_request.customerCode != null)
        {
            int l_count = 0;
            for(int i = 0; i < l_request.branchCode.length; i++)
            {
                try
                {
                    MainAccount l_mainAccount = 
                        ((WEB3GentradeAccountManager)GtlUtils.getAccountManager()).getMainAccount(l_institution.getInstitutionCode(), l_request.branchCode[i], l_request.customerCode);
                    
                    log.debug("account=" + l_mainAccount.toString());
                    
                    if(l_count > 0)
                    {
                        l_sbWhere.append(",?");                                                
                    }
                    else
                    {
                        l_sbWhere.append(" and account_code in (?");                        
                    }
                    l_lisBindVars.add(l_mainAccount.getAccountCode());                    
                    l_count++;
                }
                catch(WEB3BaseException e)
                {
                    //１件も顧客が見つからなかった場合
                    //空のリストページを返す
                    if(l_count == 0)
                    {
                        log.debug("account not found. institutionCode=" + l_institution.getInstitutionCode() + "branchCode=" + l_request.branchCode[i] + "customerCode=" + l_request.customerCode);
                        return new ArrayListPage(Collections.EMPTY_LIST, l_pageSize, l_pageIndex);
                    }
                }
            }
            if(l_count > 0)
            {
                l_sbWhere.append(")");
            }
        }
        
        final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("branch_code, account_code asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(RequisitionAccountEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars);
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(RequisitionAccountEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex);
            }
            
        }
        catch(NumberFormatException ne)
        {
            log.error(ne.getMessage(), ne);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME, ne.getMessage());            
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
        }
    }

    /**
     * (get請求発生顧客一覧<信用顧客>)<BR>
     * <BR>
     * 請求発生顧客<信用顧客>テーブルより検索。<BR>
     * <BR>
     * @@param 証券会社ID
     * @@param 部店コード
     * @@param 顧客コード
     * @@return List 顧客Paramsリスト
     */
    protected ListPage getRequisitionAccountMarginParamsList(WEB3Administrator l_admin, WEB3AdminTPAdvanceCustomerSearchListRequest l_request)
    	throws WEB3BaseException
    {
        final String METHOD_NAME = "getRequisitionAccountMarginParamsList";
        
        //画面から来るページのインデックスは１から始まる値
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //管理者所属の証券会社
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        log.debug("l_request.branchCode.length" + l_request.branchCode.length);
        
        //条件作成
        //証券会社コード
        l_sbWhere.append("institution_code = ? " );
        l_lisBindVars.add(l_institution.getInstitutionCode());
        
        int l_count = 0;
        //部店コード
        for(int i = 0; i < l_request.branchCode.length; i++)
        {
            if(i > 0)
            {
                l_sbWhere.append(", ");
            }
            else
            {
                l_sbWhere.append("and branch_code in (");
            }
            l_sbWhere.append("?");                    
            l_lisBindVars.add(l_request.branchCode[i]);
        }
        if(l_request.branchCode.length > 0)
            l_sbWhere.append(")");  

        //顧客コード
        if(l_request.customerCode != null)
        {
            for(int i = 0; i < l_request.branchCode.length; i++)
            {
                try
                {
                    MainAccount l_mainAccount = 
                        ((WEB3GentradeAccountManager)GtlUtils.getAccountManager()).getMainAccount(l_institution.getInstitutionCode(), l_request.branchCode[i], l_request.customerCode);
                    
                    log.debug("account=" + l_mainAccount.toString());
                    
                    if(l_count > 0)
                    {
                        l_sbWhere.append(",?");                                                
                    }
                    else
                    {
                        l_sbWhere.append(" and account_code in (?");                        
                    }
                    l_lisBindVars.add(l_mainAccount.getAccountCode());                    
                    l_count++;
                }
                catch(WEB3BaseException e)
                {
                    //１件も顧客が見つからなかった場合
                    //空のリストページを返す
                    if(l_count == 0)
                    {
                        log.debug("account not found. institutionCode=" + l_institution.getInstitutionCode() + "branchCode=" + l_request.branchCode[i] + "customerCode=" + l_request.customerCode);
                        return new ArrayListPage(Collections.EMPTY_LIST, l_pageSize, l_pageIndex);
                    }
                }
            }
            if(l_count > 0)
            {
                l_sbWhere.append(")");
            }
        }
        
        //値洗い区分
        if(l_request.markToMarketDiv != null)
        {
            l_sbWhere.append(" and mark_to_market_div = ?");
            l_lisBindVars.add(l_request.markToMarketDiv);
            
        }
        
        final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("branch_code, account_code asc");        
        try
        {                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(RequisitionAccountMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars);
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(RequisitionAccountMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex);
            }
            
        }
        catch(NumberFormatException ne)
        {
            log.error(ne.getMessage(), ne);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME, ne.getMessage());            
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
        }

    }
    
   /**
    * (get保証金維持率割れ/立替金発生顧客詳細)<BR>
    * <BR>
    * シーケンス図<BR>
    * 「get保証金維持率割れ/立替金発生顧客詳細」参照。<BR>
    * <BR>
    * @@param l_request 
    * @@return WEB3AdminTPAdvanceCustomerDetailResponse
    */
   protected WEB3AdminTPAdvanceCustomerDetailResponse getAdvanceCustomerDetail(WEB3AdminTPAdvanceCustomerDetailRequest l_request) 
        throws WEB3BaseException
   {
       
       final String METHOD_NAME = "getAdvanceCustomerDetail";
       log.entering(METHOD_NAME);

        //リクエストの属性妥当性チェック
        l_request.validate();

       //管理者
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //管理者権限チェック
        l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

        //立替金明細ユニット一覧(T+0～T+5)
          WEB3AdminTPAdvanceDetailUnit[] l_detailUnits = new WEB3AdminTPAdvanceDetailUnit[ 6 ];
        
        //現物顧客
        if(l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST))
        {
            //余力計算結果ID取得
            long l_lngCalcResultId = Long.parseLong( l_request.calcResultId );            
            TpCalcResultEquityRow l_tpCalcResultEquityRow = this.getTpCalcResultEquityRow(l_lngCalcResultId);
            TpCalcResultEquityDetailRow l_tpCalcResultEquityDetailRow = this.getTpCalcResultEquityDetailRow(l_lngCalcResultId);
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)GtlUtils.getAccountManager().getSubAccount(l_tpCalcResultEquityRow.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);                
            }
            catch(NotFoundException ne)
            {
                log.error(ne.getMessage(), ne);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, ne.getMessage());
            }

            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            //資産余力情報<現物顧客>取得
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount, l_tpCalcResultEquityRow, l_tpCalcResultEquityDetailRow);

            //T+0～T+5
            for(int i=0; i<=5; i++  )
            {
                //預り金残高
                double l_dblAccountBalance = l_calcEquity.getAccountBalance( i );
                //当日約定済代金
                double l_dblTodayExecutedAmount = l_calcEquity.getTodayExecutedAmount( i );                
                //当日未約定代金
                double l_dblTodayUnexecutedAmount = l_calcEquity.getTodayUnexecutedAmount( i );
                //その他拘束金
                double l_dblOtherRestraint = l_calcEquity.getOtherRestraint( i );
                //日計り拘束金
                double l_dblDayTradeRestraint = l_calcEquity.getDayTradeRestraint( i );
                //預り資産評価額
                double l_dblTrustSecurityAsset = l_calcEquity.getTrustSecurityAsset( i );
                
                //実質客勘残
                double l_dblRealAccountBalance = l_dblAccountBalance - l_dblTodayExecutedAmount
                                                - l_dblTodayUnexecutedAmount - l_dblOtherRestraint;
                                                
                //立替金
                double l_dblDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance, 0d ) );
                
                //特別立替金
                double l_dblSpecialDebitAmount = 0d;
                if( l_dblDayTradeRestraint == 0d )
                {
                    l_dblSpecialDebitAmount = 0d;
                }
                else
                {
                    l_dblSpecialDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance - l_dblDayTradeRestraint, 0d ) );
                }
                
                //立替金明細ユニット
                WEB3AdminTPAdvanceDetailUnit l_detailUnit = new WEB3AdminTPAdvanceDetailUnit();
            
                //プロパテイセット
                l_detailUnit.accountBalance        = format( l_dblAccountBalance - l_dblTodayExecutedAmount );//預り金
                l_detailUnit.todayUnexecutedAmount = format( l_dblTodayUnexecutedAmount );//発注充当金
                l_detailUnit.dayTradeRestraint     = format( l_dblDayTradeRestraint );//日計り拘束金
                l_detailUnit.otherRestraint        = format( l_dblOtherRestraint );//その他拘束金
                l_detailUnit.securityAsset         = format( l_dblTrustSecurityAsset );//預り証券評価額
                l_detailUnit.realAccountBalance    = format( l_dblRealAccountBalance );//実質客勘定残
                l_detailUnit.debitAmount           = format( l_dblDebitAmount );//立替金
                l_detailUnit.specialDebitAmount    = format( l_dblSpecialDebitAmount );//特別立替金
            
                //一覧にセット
                l_detailUnits[ i ] = l_detailUnit;

                log.debug("立替金発生顧客詳細<現物>[" + i + "]=" + l_detailUnits[ i ]);
                
            }
   
        }
        //信用顧客
        else
        {
            //余力計算結果ID取得
            long l_lngCalcResultId = Long.parseLong( l_request.calcResultId );            
            TpCalcResultMarginRow l_tpCalcResultMarginRow = this.getTpCalcResultMarginRow(l_lngCalcResultId);
            TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow = this.getTpCalcResultMarginDetailRow(l_lngCalcResultId);
            
            log.debug("取得した余力計算結果Params=" + l_tpCalcResultMarginRow);
            log.debug("取得した余力計算結果詳細Params=" + l_tpCalcResultMarginDetailRow);
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)GtlUtils.getAccountManager().getSubAccount(l_tpCalcResultMarginRow.getAccountId(), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);                
            }
            catch(NotFoundException ne)
            {
                log.error(ne.getMessage(), ne);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, ne.getMessage());
            }
            
            
            //取引余力サービス
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            //資産余力情報<信用顧客>取得
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount, l_tpCalcResultMarginRow, l_tpCalcResultMarginDetailRow);

            log.debug("取得した資産余力情報<信用顧客>=" + l_calcMargin);
            
            //余力計算条件取得
            WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

            //代用低下率取得
            String l_strSubstituteValuationDropRate = l_request.substituteValuationDropRate;
            BigDecimal l_dbSubstituteValuationDropRate = null;
            //代用低下率が指定されていない場合、ゼロとする
            if( l_strSubstituteValuationDropRate == null || l_strSubstituteValuationDropRate.equals("") )
            {
                l_dbSubstituteValuationDropRate = BigDecimal.valueOf(0);
            }
            else
            {
                l_dbSubstituteValuationDropRate = new BigDecimal( l_strSubstituteValuationDropRate );
        
            }            
            log.debug("代用低下率=" + l_dbSubstituteValuationDropRate);

            //保証金維持率
            int l_intMarginMentenanceRate = l_condition.getMarginMentenanceRate();
            log.debug("保証金維持率=" + l_intMarginMentenanceRate);

            //最低必要保証金
            double l_dblMinMarginDeposit = l_condition.getMinMarginDeposit();
            log.debug("最低必要保証金=" + l_dblMinMarginDeposit);

            //法@定最低必要保証金
            double l_dblLegalMinMarginDeposit = l_condition.getLegalMinMarginDeposit();
            log.debug("法@定最低必要保証金=" + l_dblLegalMinMarginDeposit);
                    
            //T+0～T+5
            for(int i=0; i<=5; i++ )
            {
                //預り金残高
                double l_dblAccountBalance = l_calcMargin.getAccountBalance( i );
                log.debug("預り金残高=" + l_dblAccountBalance);

                //当日約定済代金
                double l_dblTodayExecutedAmount = l_calcMargin.getTodayExecutedAmount( i );                
                log.debug("当日約定済代金=" + l_dblTodayExecutedAmount);

                //当日未約定代金
                double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount( i );
                log.debug("当日未約定代金=" + l_dblTodayUnexecutedAmount);

                //その他拘束金
                double l_dblOtherRestraint = l_calcMargin.getOtherRestraint( i );
                log.debug("その他拘束金=" + l_dblOtherRestraint);

                //日計り拘束金
                double l_dblDayTradeRestraint = l_calcMargin.getDayTradeRestraint( i );
                log.debug("日計り拘束金=" + l_dblDayTradeRestraint);

                //現金保証金
                double l_dbMarginAccountBalance = l_calcMargin.calcMarginAccountBalance( i );
                log.debug("現金保証金=" + l_dbMarginAccountBalance);

                //代用証券評価額
                double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset( i );
                log.debug("代用証券評価額=" + l_dblSubstituteSecurityAsset);

                //未決済建玉評価損益
                double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss( i );
                log.debug("未決済建玉評価損益=" + l_dblContractAssetProfitLoss);

                //建玉諸経費
                double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(i);
                log.debug("建玉諸経費=" + l_dblContractTotalCost);
                
                //未受渡建玉決済損
                double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss( i );
                log.debug("未受渡建玉決済損=" + l_dblUndeliContractLoss);

                //受入保証金
                double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit( i );
                log.debug("受入保証金=" + l_dblReceiptMarginDeposit);

                //現金必要保証金
                double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit( i );
                log.debug("現金必要保証金=" + l_dblCashMarginDeposit);

                //未決済建玉代金
                double l_dblContractAmount = l_calcMargin.getContractAmount( i );
                log.debug("未決済建玉代金=" + l_dblContractAmount);

                //追証余力
                double l_dblMarginCallPower = l_calcMargin.calcMarginCallPower( i );
                log.debug("追証余力=" + l_dblMarginCallPower);
                
                //保証金預託率=[受入保証金(n) / 未決済建玉代金]
                BigDecimal l_bdContractAmount = new BigDecimal( l_dblContractAmount );
                                
                double l_dblMarginDepositRate = 0.0d;
                if(l_dblReceiptMarginDeposit != 0.0d && l_dblContractAmount != 0.0d)
                {
                    BigDecimal l_bdMarginDepositRate = new BigDecimal(l_dblReceiptMarginDeposit / l_dblContractAmount * 100);
                    l_dblMarginDepositRate = l_bdMarginDepositRate.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                }
                log.debug("保証金預託率=" + l_dblMarginDepositRate);
                
                             
                //実質客勘残
                double l_dblRealAccountBalance = l_dblAccountBalance - l_dblTodayExecutedAmount
                                                - l_dblTodayUnexecutedAmount - l_dblOtherRestraint;                                
                log.debug("実質客勘残=" + l_dblRealAccountBalance);

                //立替金
                double l_dblDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance, 0d ) );
                log.debug("立替金=" + l_dblDebitAmount);
               
                //特別立替金
                double l_dblSpecialDebitAmount = 0d;
                if( l_dblDayTradeRestraint == 0d )
                {
                    l_dblSpecialDebitAmount = 0d;
                }
                else
                {
                    l_dblSpecialDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance - l_dblDayTradeRestraint, 0d ) );
                }
                log.debug("特別立替金=" + l_dblSpecialDebitAmount);
                
                //保証金維持必要額
                BigDecimal l_bdMarginMentenanceRate = new BigDecimal( l_intMarginMentenanceRate );
                BigDecimal l_bdMarginMaintenanceAmount = l_bdContractAmount.multiply( l_bdMarginMentenanceRate ).divide( BigDecimal.valueOf(100), 0, BigDecimal.ROUND_FLOOR );
                double l_dblMarginMaintenanceAmount = l_bdMarginMaintenanceAmount.doubleValue();
                log.debug("保証金維持必要額=" + l_dblMarginMaintenanceAmount);
                
                //保証金請求額
                double l_dblMarginClaimedAmount = Math.min( l_dblMarginCallPower, 0);
                log.debug("保証金請求額=" + l_dblMarginClaimedAmount);

                //顧客勘定残高不足分
                double l_dblAccountBalanceShortfall = Math.min( l_dblMarginClaimedAmount, Math.abs( Math.min( l_dblRealAccountBalance - l_dbMarginAccountBalance, 0) ) );
                log.debug("顧客勘定残高不足分=" + l_dblAccountBalanceShortfall);
                
                //現金保証金不足分
                double l_dblCashMarginShortfall = l_dblMarginClaimedAmount - l_dblAccountBalanceShortfall;
                log.debug("現金保証金不足分=" + l_dblCashMarginShortfall);
                
                //代用証券評価額<代用評価低下率考慮>
                BigDecimal l_bdSubstituteSecurityAsset = new BigDecimal( l_dblSubstituteSecurityAsset );
                BigDecimal SubstituteSecurityAssetIncDropRate
                     = l_bdSubstituteSecurityAsset.multiply( BigDecimal.valueOf(100).subtract( l_dbSubstituteValuationDropRate ) ).divide( BigDecimal.valueOf(100), 1, BigDecimal.ROUND_FLOOR);
                double l_dblSubstituteSecurityAssetIncDropRate = SubstituteSecurityAssetIncDropRate.doubleValue();
                log.debug("代用証券評価額<代用評価低下率考慮>=" + l_dblSubstituteSecurityAssetIncDropRate);
                
                //差入保証金<代用低下率考慮>
                double l_dblPaidMarginDepositIncDropRate = l_dbMarginAccountBalance + l_dblSubstituteSecurityAssetIncDropRate;
                log.debug("差入保証金<代用低下率考慮>=" + l_dblPaidMarginDepositIncDropRate);
                
                //受入保証金<代用評価低下率考慮>
                double l_dblReceiptMarginDepositIncDropRate = l_dblPaidMarginDepositIncDropRate + Math.min( l_dblContractAssetProfitLoss, 0d ) - l_dblContractTotalCost - l_dblUndeliContractLoss;
                log.debug("受入保証金<代用評価低下率考慮>=" + l_dblReceiptMarginDepositIncDropRate);
                
                //追証余力<代用評価低下率考慮>
                double l_dblMarginCallPowerIncDropRate = Math.min( l_dblPaidMarginDepositIncDropRate - l_dblMinMarginDeposit,
                                                                    l_dblReceiptMarginDepositIncDropRate - Math.max( l_dblMarginMaintenanceAmount, l_dblLegalMinMarginDeposit) );
                log.debug("追証余力<代用評価低下率考慮>=" + l_dblMarginCallPowerIncDropRate);
                
                //保証金預託率<代用評価低下率考慮>                
                double l_dblMarginDepositRateIncDropRate = 0.0d;
                if(l_dblContractAmount != 0.0d && l_dblReceiptMarginDepositIncDropRate != 0.0d)
                {
                    BigDecimal l_bdMarginDepositRateIncDropRate = new BigDecimal(l_dblReceiptMarginDepositIncDropRate / l_dblContractAmount * 100);
                    l_dblMarginDepositRateIncDropRate = l_bdMarginDepositRateIncDropRate.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                }
                log.debug("保証金預託率<代用評価低下率考慮>=" + l_dblMarginDepositRateIncDropRate);

                //保証金請求額<代用評価低下率考慮>
                double l_dblMarginClaimedAmountIncDropRate = Math.min( l_dblMarginCallPowerIncDropRate, 0 );
                log.debug("保証金請求額<代用評価低下率考慮>=" + l_dblMarginClaimedAmountIncDropRate);

                //顧客勘定残高不足分 = Min( 保証金請求額<代用評価低下率考慮>, Abs( Min(実質客勘残 - 現金保証金, 0) ) )
                double l_dblAccountBalanceShortfallIncDropRate = Math.min( l_dblMarginClaimedAmountIncDropRate, Math.abs( Math.min( l_dblRealAccountBalance - l_dbMarginAccountBalance, 0) ) );
                log.debug("顧客勘定残高不足分=" + l_dblAccountBalanceShortfallIncDropRate);
                
                //現金保証金不足分 = 保証金請求額<代用評価低下率考慮> ? 顧客勘定残高不足分<代用評価低下率考慮>
                double l_dblCashMarginShortfallIncDropRate = l_dblMarginClaimedAmountIncDropRate - l_dblAccountBalanceShortfallIncDropRate;
                log.debug("現金保証金不足分=" + l_dblCashMarginShortfallIncDropRate);
                
                //立替金明細ユニット
                WEB3AdminTPAdvanceDetailUnit l_detailUnit = new WEB3AdminTPAdvanceDetailUnit();
            
                //プロパテイセット
                l_detailUnit.realAccountBalance = format( l_dblRealAccountBalance );//実質客勘残
                l_detailUnit.cashDeposit = format( l_dblAccountBalance );//現金保証金
                l_detailUnit.todayUnexecutedAmount = format( l_dblTodayUnexecutedAmount );//発注充当金
                l_detailUnit.dayTradeRestraint = format( l_dblDayTradeRestraint );//日計り拘束金
                l_detailUnit.otherRestraint = format( l_dblOtherRestraint );//その他拘束金
                l_detailUnit.marginAccountBalance = format( l_dblAccountBalance - l_dblTodayExecutedAmount - l_dblTodayUnexecutedAmount - l_dblOtherRestraint );//使用可能現金保証金
                l_detailUnit.securityAsset = format( l_dblSubstituteSecurityAsset );//代用証券評価額
                l_detailUnit.contractAssetProfitLoss = format( l_dblContractAssetProfitLoss );//未決済建玉評価損益
                l_detailUnit.contractTotalCost = format( l_dblContractTotalCost );//建玉諸経費
                l_detailUnit.undeliContractLoss = format( l_dblUndeliContractLoss );//未受渡建玉決済損
                l_detailUnit.receiptMarginDeposit = format( l_dblReceiptMarginDeposit );//受入保証金
                l_detailUnit.marginMaintenanceAmount = format( l_dblMarginMaintenanceAmount );//保証金維持必要額
                l_detailUnit.cashMarginDeposit = format( l_dblCashMarginDeposit );//現金必要保証金
                l_detailUnit.marginCallPower = format( l_dblMarginCallPower );//追証余力
                l_detailUnit.contractAmount = format( l_dblContractAmount );//建玉代金
                l_detailUnit.marginDepositRate = format( l_dblMarginDepositRate );//保証金預託率
                l_detailUnit.debitAmount = format( l_dblDebitAmount );//立替金
                l_detailUnit.specialDebitAmount = format( l_dblSpecialDebitAmount );//特別立替金
                l_detailUnit.marginClaimedAmount = format( l_dblMarginClaimedAmount );//保証金請求額
                l_detailUnit.accountBalanceShortfall = format( l_dblAccountBalanceShortfall );//顧客勘定残高不足分
                l_detailUnit.cashMarginShortfall = format( l_dblCashMarginShortfall );//現金保証金不足分
                l_detailUnit.substituteSecurityAssetIncDropRate = format( l_dblSubstituteSecurityAssetIncDropRate );//代用証券評価額<代用評価低下率考慮>
                l_detailUnit.receiptMarginDepositIncDropRate = format( l_dblReceiptMarginDepositIncDropRate );//受入保証金<代用評価低下率考慮>
                l_detailUnit.marginCallPowerIncDropRate = format( l_dblMarginCallPowerIncDropRate );//追証余力<代用評価低下率考慮>
                l_detailUnit.marginDepositRateIncDropRate = format( l_dblMarginDepositRateIncDropRate );//保証金預託率<代用評価低下率考慮>
                l_detailUnit.accountBalanceShortfallIncDropRate = format( l_dblAccountBalanceShortfallIncDropRate );//顧客勘定残高不足分<代用評価低下率考慮>
                l_detailUnit.cashMarginBalanceShortfallIncDropRate = format( l_dblCashMarginShortfallIncDropRate );//現金保証金不足分<代用評価低下率考慮>
                l_detailUnit.marginClaimedAmountIncDropRate = format( l_dblMarginClaimedAmountIncDropRate );//保証金請求額<代用評価低下率考慮>
                           
                //一覧にセット
                l_detailUnits[ i ] = l_detailUnit;
                
                log.debug("立替金発生顧客詳細<信用>[" + i + "]=" + l_detailUnits[ i ]);
            
            }
               
        }
        
        //レスポンス
        WEB3AdminTPAdvanceCustomerDetailResponse l_response = (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
       
        //プロパティセット
       l_response.advanceCustomerDetailUnits = l_detailUnits;
       
        //レスポンス返却
        log.exiting(METHOD_NAME);
        return l_response;                      
        
   }
   
   /**
    * (get立替金顧客<現物顧客>)<BR>
    * <BR>
    * シーケンス図<BR>
    * 「get立替金顧客<現物顧客>」参照。<BR>
    * <BR>
    * @@param 口座ID
    * @@return 保証金維持率割れ/立替金発生顧客ユニット
    */
   protected WEB3AdminTPAdvanceCustomerUnit getAdvanceCustomerEquity(RequisitionAccountEquityRow l_requisitionRow)
   throws WEB3BaseException
   {
       //顧客明細一覧
       WEB3AdminTPAdvanceCustomerDetailUnit[] l_detailUnits
            = new WEB3AdminTPAdvanceCustomerDetailUnit[ 6 ];

       //プロパティセット
       //T+0
       l_detailUnits[0] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[0].realAccountBalance = format(l_requisitionRow.getRealAccountBalance0());//実質客定残
       l_detailUnits[0].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset0());//預り証券評価額
       l_detailUnits[0].debitAmount        = format(l_requisitionRow.getDebitAmount0());//立替金
       l_detailUnits[0].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount0());//特別立替金
       
       //T+1
       l_detailUnits[1] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[1].realAccountBalance = format(l_requisitionRow.getRealAccountBalance1());//実質客定残
       l_detailUnits[1].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset1());//預り証券評価額
       l_detailUnits[1].debitAmount        = format(l_requisitionRow.getDebitAmount1());//立替金
       l_detailUnits[1].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount1());//特別立替金
              
       //T+2
       l_detailUnits[2] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[2].realAccountBalance = format(l_requisitionRow.getRealAccountBalance2());//実質客定残
       l_detailUnits[2].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset2());//預り証券評価額
       l_detailUnits[2].debitAmount        = format(l_requisitionRow.getDebitAmount2());//立替金
       l_detailUnits[2].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount2());//特別立替金

       //T+3
       l_detailUnits[3] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[3].realAccountBalance = format(l_requisitionRow.getRealAccountBalance3());//実質客定残
       l_detailUnits[3].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset3());//預り証券評価額
       l_detailUnits[3].debitAmount        = format(l_requisitionRow.getDebitAmount3());//立替金
       l_detailUnits[3].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount3());//特別立替金

       //T+4
       l_detailUnits[4] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[4].realAccountBalance = format(l_requisitionRow.getRealAccountBalance4());//実質客定残
       l_detailUnits[4].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset4());//預り証券評価額
       l_detailUnits[4].debitAmount        = format(l_requisitionRow.getDebitAmount4());//立替金
       l_detailUnits[4].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount4());//特別立替金

       //T+5
       l_detailUnits[5] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[5].realAccountBalance = format(l_requisitionRow.getRealAccountBalance5());//実質客定残
       l_detailUnits[5].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset5());//預り証券評価額
       l_detailUnits[5].debitAmount        = format(l_requisitionRow.getDebitAmount5());//立替金
       l_detailUnits[5].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount5());//特別立替金
       
       //立替金顧客ユニット
       WEB3AdminTPAdvanceCustomerUnit l_unit = new WEB3AdminTPAdvanceCustomerUnit();
       
       //プロパティセット
       l_unit.calcResultId = String.valueOf(l_requisitionRow.getCalcResultEquityId());//余力計算結果ID
       l_unit.branchCode   = l_requisitionRow.getBranchCode();//部店コード
       l_unit.accountCode  = l_requisitionRow.getAccountCode();//顧客コード
       l_unit.accountName  = l_requisitionRow.getFamilyName();//顧客名
       l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();       
       l_unit.depositDiv = l_requisitionRow.getAssetEvaluationDiv();//預り証券顧客区分
       l_unit.advanceCustomerDetailUnits = l_detailUnits;//立替金顧客明細一覧

       log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
       log.debug("変換した立替金顧客ユニット=" + l_unit.toString());
       
       return l_unit;     
       
   }
   
   /**
    * (get保証金維持率割れ/立替金発生顧客<信用顧客>)<BR>
    * <BR>
    * @@param 口座ID
    * @@param 値洗い区分
    * @@param 代用評価低下率
    * @@return 保証金維持率割れ/立替金発生顧客ユニット
    */
   protected WEB3AdminTPAdvanceCustomerUnit getAdvanceCustomerMargin(RequisitionAccountMarginRow l_requisitionRow)
   throws WEB3BaseException
   {
       //顧客明細一覧
       WEB3AdminTPAdvanceCustomerDetailUnit[] l_detailUnits
            = new WEB3AdminTPAdvanceCustomerDetailUnit[ 6 ];
       
       //プロパティセット
       //立替金顧客明細ユニット
       //T+0
       l_detailUnits[0] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[0].debitAmount             = format(l_requisitionRow.getDebitAmount0());//立替金
       l_detailUnits[0].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount0());//特別立替金
       l_detailUnits[0].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit0());//受入保証金
       l_detailUnits[0].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount0());//保証金維持必要額
       l_detailUnits[0].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate0());//保証金預託率
       l_detailUnits[0].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount0());//保証金請求額

       //T+1
       l_detailUnits[1] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[1].debitAmount             = format(l_requisitionRow.getDebitAmount1());//立替金
       l_detailUnits[1].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount1());//特別立替金
       l_detailUnits[1].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit1());//受入保証金
       l_detailUnits[1].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount1());//保証金維持必要額
       l_detailUnits[1].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate1());//保証金預託率
       l_detailUnits[1].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount1());//保証金請求額

       //T+2
       l_detailUnits[2] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[2].debitAmount             = format(l_requisitionRow.getDebitAmount2());//立替金
       l_detailUnits[2].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount2());//特別立替金
       l_detailUnits[2].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit2());//受入保証金
       l_detailUnits[2].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount2());//保証金維持必要額
       l_detailUnits[2].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate2());//保証金預託率
       l_detailUnits[2].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount2());//保証金請求額

       //T+3
       l_detailUnits[3] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[3].debitAmount             = format(l_requisitionRow.getDebitAmount3());//立替金
       l_detailUnits[3].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount3());//特別立替金
       l_detailUnits[3].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit3());//受入保証金
       l_detailUnits[3].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount3());//保証金維持必要額
       l_detailUnits[3].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate3());//保証金預託率
       l_detailUnits[3].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount3());//保証金請求額

       //T+4
       l_detailUnits[4] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[4].debitAmount             = format(l_requisitionRow.getDebitAmount4());//立替金
       l_detailUnits[4].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount4());//特別立替金
       l_detailUnits[4].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit4());//受入保証金
       l_detailUnits[4].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount4());//保証金維持必要額
       l_detailUnits[4].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate4());//保証金預託率
       l_detailUnits[4].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount4());//保証金請求額

       //T+5
       l_detailUnits[5] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[5].debitAmount             = format(l_requisitionRow.getDebitAmount5());//立替金
       l_detailUnits[5].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount5());//特別立替金
       l_detailUnits[5].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit5());//受入保証金
       l_detailUnits[5].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount5());//保証金維持必要額
       l_detailUnits[5].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate5());//保証金預託率
       l_detailUnits[5].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount5());//保証金請求額
       
       //顧客ユニット
       WEB3AdminTPAdvanceCustomerUnit l_unit = new WEB3AdminTPAdvanceCustomerUnit();
       
       //プロパティセット
       l_unit.calcResultId = String.valueOf(l_requisitionRow.getCalcResultMarginId());//余力計算結果ID
       l_unit.branchCode   = l_requisitionRow.getBranchCode();//部店コード
       l_unit.accountCode  = l_requisitionRow.getAccountCode();//顧客コード
       l_unit.accountName  = l_requisitionRow.getFamilyName();//顧客名
       l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();//扱者コード       
       l_unit.depositDiv   = WEB3TPDepositDivDef.NOT_DEPOSIT_CUSTOMER;//預り証券顧客区分
       l_unit.advanceCustomerDetailUnits = l_detailUnits;//立替金顧客明細一覧
       
       log.debug("RequisitionAccountMarginRow=" + l_requisitionRow.toString());       
       log.debug("変換した顧客ユニット=" + l_unit.toString());
       
       return l_unit;     
       
   }
   

   /**
    * (get値洗い終了区分)<BR>
    * <BR>
    * シーケンス図<BR>
    * 「get値洗い終了区分」参照。<BR>
    * <BR>
    * @@param l_admin 管理者
    * @@return String[] 値洗い終了区分
    * @@throws WEB3BaseException
    */   
   protected String[] getMarkToMarketEndDiv(WEB3Administrator l_admin) throws WEB3BaseException
   {
       //証券会社コード
       String l_strInstitutionCode = l_admin.getInstitutionCode();
       
       //大引け値洗い終了count
       int l_intCount1;
       String l_strWhere1 = " process_id in (?, ?) and institution_code = ? and status = ? ";
       Object[] l_bindVars1 = {
                                WEB3AdminTPProcessManagementIdDivDef.NORMAL_END,//0002：大引け値洗い終了
                                WEB3AdminTPProcessManagementIdDivDef.PROMPT_REPORT_NORMAL_END,//0004:大引け速報値洗い終了
                                l_strInstitutionCode,
                                WEB3StatusDef.DEALT//1：処理済み
                              };              
       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_intCount1 = l_qp.doGetCountQuery( ProcessManagementRow.TYPE, l_strWhere1, l_bindVars1);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                        getClass().getName() + "getMarkToMarketEndDiv" );
       }

       //前引け値洗い終了count
       int l_intCount2;
       String l_strWhere2 = " process_id = ? and institution_code = ? and status = ? ";
       Object[] l_bindVars2 = {
                                WEB3AdminTPProcessManagementIdDivDef.MORNING_CLOSED_END,//0003：前場引け値洗い終了
                                l_strInstitutionCode,
                                WEB3StatusDef.DEALT//1：処理済み
                              };              
       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_intCount2 = l_qp.doGetCountQuery( ProcessManagementRow.TYPE, l_strWhere2, l_bindVars2);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                        getClass().getName() + "getMarkToMarketEndDiv" );
       }
       
       //値洗い終了区分
       String[] l_strMarkToMarketEndDiv = null;
       
       if( l_intCount1 > 0 && l_intCount2 > 0 )
       {
           l_strMarkToMarketEndDiv = new String[2];
           l_strMarkToMarketEndDiv[0] = WEB3AdminTPMarkToMarketEndDivDef.NORMAL;//大引け
           l_strMarkToMarketEndDiv[1] = WEB3AdminTPMarkToMarketEndDivDef.MORNING_CLOSED;//前引け
           log.debug("大引け前場引け状態");

       }
       else
       if( l_intCount1 > 0 && l_intCount2 == 0 )
       {
           l_strMarkToMarketEndDiv = new String[1];
           l_strMarkToMarketEndDiv[0] = WEB3AdminTPMarkToMarketEndDivDef.NORMAL;//大引け
           log.debug("大引け状態");
       }
       else
       if( l_intCount1 == 0 && l_intCount2 > 0 )
       {
           l_strMarkToMarketEndDiv = new String[1];
           l_strMarkToMarketEndDiv[0] = WEB3AdminTPMarkToMarketEndDivDef.MORNING_CLOSED;//前引け
           log.debug("前場引け状態");
       }
       else
       {
           l_strMarkToMarketEndDiv = null;
           log.debug("場中");
       }
       
       
       return l_strMarkToMarketEndDiv;
   }
   
   /**
    * get余力計算結果<現物顧客>
    * @@param l_epCalcResultEquityId　@余力計算結果ID
    * @@return TpCalcResultEquityRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultEquityRow getTpCalcResultEquityRow(long l_epCalcResultEquityId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultEquityRow(long l_epCalcResultEquityId)";
       
       try
       {
           return TpCalcResultEquityDao.findRowByPk(l_epCalcResultEquityId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

   /**
    * get余力計算結果詳細<現物顧客>
    * @@param l_epCalcResultEquityId　@余力計算結果ID
    * @@return TpCalcResultEquityRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultEquityDetailRow getTpCalcResultEquityDetailRow(long l_epCalcResultEquityId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultEquityDetailRow(long l_epCalcResultEquityId)";
       
       try
       {
           return TpCalcResultEquityDetailDao.findRowByPk(l_epCalcResultEquityId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }
   
   /**
    * get余力計算結果<信用顧客>
    * @@param l_epCalcResultEquityId　@余力計算結果ID
    * @@return TpCalcResultMarginRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultMarginRow getTpCalcResultMarginRow(long l_tpCalcResultMarginId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultMarginRow(long l_tpCalcResultMarginId)";
       
       try
       {
           return TpCalcResultMarginDao.findRowByPk(l_tpCalcResultMarginId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

   /**
    * get余力計算結果詳細<信用顧客>
    * @@param l_tpCalcResultMarginId　@余力計算結果ID
    * @@return TpCalcResultMarginRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultMarginDetailRow getTpCalcResultMarginDetailRow(long l_tpCalcResultMarginId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultMarginDetailRow(long l_tpCalcResultMarginId)";
       
       try
       {
           return TpCalcResultMarginDetailDao.findRowByPk(l_tpCalcResultMarginId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }
   
   public void validateToMarketEndDiv(String l_institutionCode, String l_branchCode, String l_markToMarketEndDiv)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "validateToMarketEndDiv";
       
	   int l_count = 0;
       try
       {
           String l_strWhere = null;
           Object[] l_bindVars;
           if(WEB3AdminTPMarkToMarketEndDivDef.MORNING_CLOSED.equals(l_markToMarketEndDiv))
           {       
       	       l_strWhere = "process_id = ? and institution_code = ? and branch_code = ?";
       	       l_bindVars = new Object[3];
       	       l_bindVars[0] = WEB3AdminTPProcessManagementIdDivDef.MORNING_CLOSED_END;
       	       l_bindVars[1] = l_institutionCode;
    	       l_bindVars[2] = l_branchCode;
           }
           
           else if(WEB3AdminTPMarkToMarketEndDivDef.NORMAL.equals(l_markToMarketEndDiv))
           {   
               int missCount = 0;
       	       l_strWhere = "process_id in (?, ?) and institution_code = ? and branch_code = ?";
       	       l_bindVars = new Object[4];
    	       l_bindVars[0] = WEB3AdminTPProcessManagementIdDivDef.NORMAL_END;
    	       l_bindVars[1] = WEB3AdminTPProcessManagementIdDivDef.PROMPT_REPORT_NORMAL_END;
    	       l_bindVars[2] = l_institutionCode;
    	       l_bindVars[3] = l_branchCode;
               
           }
           else
           {
               throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME);                           
           }
           
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_count = l_qp.doGetCountQuery(ProcessManagementRow.TYPE, l_strWhere, l_bindVars);
                      
       }
   	   catch(DataException e)
   	   {
           throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME);            
   	   }
       
   	   if(l_count < 1)
   	   {
   	       throw new WEB3SystemLayerException(
   	               WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME);               	       
   	   }


   }
   
   
   
   
   /**
    * double型をString形式にフォーマットする。<BR>
    * <BR>
    * @@param param
    * @@return String
    */
   private String format(double l_param)
   {       
       NumberFormat l_nf = NumberFormat.getInstance();
       l_nf.setGroupingUsed( false );
       return l_nf.format( l_param ); 
   }
   
}
@
