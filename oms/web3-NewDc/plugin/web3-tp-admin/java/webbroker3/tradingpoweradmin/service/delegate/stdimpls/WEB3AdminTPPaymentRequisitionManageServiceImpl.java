head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求管理検索サービスImplクラス(WEB3AdminTPPaymentRequisitionManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 宮本 篤東(SCS) 新規作成
				   2006/03/27 宮本 篤東(SCS) 検索条件及びソート条件の追加
Revision History : 2008/10/20 劉剣（中訊）モデルNo.032
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.ArrayListPage;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageDetailUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryUnit;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionManageService;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityRow;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityParams;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginRow;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPCustomerAttributeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPPaymentRequisitionManageDef;
import webbroker3.util.WEB3LogUtility;

/**
 * 入金請求管理検索サービスImpl
 */
public class WEB3AdminTPPaymentRequisitionManageServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPPaymentRequisitionManageService 
{
    
    /**
     * (ログ)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionManageServiceImpl.class);

    /**
     * @@roseuid 4412A98E0083
     */
    public WEB3AdminTPPaymentRequisitionManageServiceImpl() 
    {
     
    }
    
    /**
     * (get入金請求管理顧客Params一覧<現物顧客>)<BR>
     * <BR>
     * 入金請求管理一覧を検索する為のパラメータで<BR>
     * 入金請求管理テーブル(現物)の検索を行う。<BR>
     * <BR>
     * 以下の条件で入金請求管理テーブル（現物）から検索を行う。<BR>
     * 顧客コード == null の場合は入金請求が発生している顧客を取得する。<BR>
     * <BR>
     * ・計算日 = 業務日付（TradingSystemImpl.getBizDate()）<BR>
     * <BR>
     * ・証券会社コード = 入金請求管理一覧リクエスト.会社コード<BR>
     * <BR>
     * ・部店コード = 入金請求管理一覧リクエスト.部店コード<BR>
     * <BR>
     * ・口座コード like 入金請求管理一覧リクエスト.顧客コード + "%"<BR>
     * （※顧客コード != null の場合）<BR>
     * <BR>
     * ・(入金請求額(T+0) > 0 or 入金請求額(T+1) > 0 or 入金請求額(T+2) > 0 or 残高（当日+0日） < 0)<BR>
     * （※顧客コード == null の場合）<BR>
     * <BR>
     * ・扱者コード = 入金請求管理一覧リクエスト.扱者コード<BR>
     * （※扱者コード != null の場合）<BR>
	 * <BR>
     * @@param WEB3Administrator - 管理者
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - 入金請求管理一覧リクエスト
     * @@return ListPage - 検索結果データ
     * @@roseuid 44057950005F
     */
    public ListPage getPaymentRequisitionManageParamsListEquity(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageSearchRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsListEquity";

        //画面から来るページのインデックスは１から始まる値
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

        //業務日付を取得する。
        Date l_bizDate0 = tradingSystem.getBizDate();

        //管理者所属の証券会社
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //条件作成
        //計算日
        l_sbWhere.append(" calc_date = ? ");
        l_lisBindVars.add(l_bizDate0);

        //会社コード
        l_sbWhere.append(" and institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //部店コード
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //顧客コード
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%' ");
            l_lisBindVars.add(l_request.accountCode);
        }

        //扱者コード
        if(l_request.traderCode != null)
        {
            l_sbWhere.append(" and   sonar_trader_code = ? ");
            l_lisBindVars.add(l_request.traderCode);
        }

        //顧客コードが検索条件に含まれていない場合は入金請求が発生している顧客のみ表示する。
        if(l_request.accountCode == null)
        {
	        l_sbWhere.append(" and (payment_requisition_amount_0 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_1 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_2 > 0 ");
	        l_sbWhere.append(" or cash_balance0 < 0 )");
        }

    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("substr(account_id,9,6) asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(PaymentRequisitionEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionEquityParams.TYPE});
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(PaymentRequisitionEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex,new RowType[] {PaymentRequisitionEquityParams.TYPE});
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
     * (get入金請求管理顧客Params一覧<信用顧客>)<BR>
     * <BR>
     * 入金請求管理テーブル(信用)の検索を行う。<BR>
     * <BR>
     * 以下の条件で入金請求管理テーブルから検索。<BR>
     * 顧客コード == null の場合は入金請求が発生している顧客を取得する。<BR>
     * 摘要の指定[20%割れ日数]、[30%割れ日数]が指定されていた場合、<BR>
     * 摘要に一致する顧客を表示する。<BR>
     * （※顧客指定の場合は入金請求が発生していなくても表示するが、<BR>
     * 摘要が指定されている場合は摘要に一致しないと表示されない。）<BR>
     * <BR>
     * ・計算日 = 業務日付（TradingSystemImpl.getBizDate()）<BR>
     * <BR>
     * ・証券会社コード = 入金請求管理一覧リクエスト.会社コード<BR>
     * <BR>
     * ・部店コード = 入金請求管理一覧リクエスト.部店コード<BR>
     * <BR>
     * ・口座コード like 入金請求管理一覧リクエスト.顧客コード + "%"<BR>
     * （※顧客コード != null の場合）<BR>
     * <BR>
     * ・(入金請求額(T+0) > 0 or 入金請求額(T+1) > 0 or 入金請求額(T+2) > 0<BR>
     * 　@or 残高（当日+0日） < 0)<BR>
     * （※顧客コード == null の場合）<BR>
     * <BR>
     * ・扱者コード = 入金請求管理一覧リクエスト.扱者コード<BR>
     * （※扱者コード != null の場合）<BR>
     * <BR>
     * ・20%割れ発生日 >= 入金請求管理一覧リクエスト.摘要日数<BR>
     * （※入金請求管理一覧リクエスト.摘要 == 1（20%割れ） &&<BR>
     * 　@入金請求管理一覧リクエスト.摘要日数 == 3（20%割れ指定最大日数）の場合）<BR>
     * <BR>
     * ・20%割れ発生日 > 入金請求管理一覧リクエスト.摘要日数<BR>
     * （※入金請求管理一覧リクエスト.摘要 == 1（20%割れ） &&<BR>
     * 　@入金請求管理一覧リクエスト.摘要日数 == 0（摘要日数すべて）の場合）<BR>
     * <BR>
     * ・20%割れ発生日 = 入金請求管理一覧リクエスト.摘要日数<BR>
     * （※入金請求管理一覧リクエスト.摘要 == 1（20%割れ） &&<BR>
     * 　@入金請求管理一覧リクエスト.摘要日数 != (0 or 3)の場合）<BR>
     * <BR>
     * ・30%割れ発生日 >= 入金請求管理一覧リクエスト.摘要日数<BR>
     * （※入金請求管理一覧リクエスト.摘要 == 2（30%割れ） &&<BR>
     * 　@入金請求管理一覧リクエスト.摘要日数 == 8（30%割れ指定最大日数）の場合）<BR>
     * <BR>
     * ・30%割れ発生日 > 入金請求管理一覧リクエスト.摘要日数<BR>
     * （※入金請求管理一覧リクエスト.摘要 == 2（30%割れ） &&<BR>
     * 　@入金請求管理一覧リクエスト.摘要日数 == 0（摘要日数すべて）の場合）<BR>
     * <BR>
     * ・30%割れ発生日 = 入金請求管理一覧リクエスト.摘要日数<BR>
     * （※入金請求管理一覧リクエスト.摘要 == 2（30%割れ） &&<BR>
     * 　@入金請求管理一覧リクエスト.摘要日数 != (0 or 8)の場合）<BR>
     * <BR>
     * @@param WEB3Administrator - 管理者
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - 入金請求管理一覧リクエスト
     * @@return ListPage - 検索結果データ
     * @@roseuid 4405B4FA031E
     */
    public ListPage getPaymentRequisitionManageParamsListMargin(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageSearchRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsListMargin";

        //画面から来るページのインデックスは１から始まる値
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

        //業務日付を取得する。
        Date l_bizDate0 = tradingSystem.getBizDate();

        //管理者所属の証券会社
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //条件作成
        //計算日
        l_sbWhere.append(" calc_date = ? ");
        l_lisBindVars.add(l_bizDate0);

        //会社コード
        l_sbWhere.append(" and institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //部店コード
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //顧客コード
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%' ");
            l_lisBindVars.add(l_request.accountCode);
        }

        //扱者コード
        if(l_request.traderCode != null)
        {
            l_sbWhere.append(" and   sonar_trader_code = ? ");
            l_lisBindVars.add(l_request.traderCode);
        }

        //摘要が指定なしではない場合条件を追加する。
        if(l_request.summary != WEB3AdminTPPaymentRequisitionManageDef.notSpecify)
        {
        	//検索条件の適用に20%割れが指定された場合
        	if(l_request.summary.equals(WEB3AdminTPPaymentRequisitionManageDef.break20))
        	{
        		//摘要日数の指定が20%割れの指定最大日だった場合
        		if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.max20SpecifyDay))
        		{
                	l_sbWhere.append(" and   BREAK20ELAPSED_DAYS >= ? ");
        		}
        		else if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.allSpecifyDay))
        		{
        			l_sbWhere.append(" and   BREAK20ELAPSED_DAYS > ? ");
        		}
        		else
        		{
                	l_sbWhere.append(" and   BREAK20ELAPSED_DAYS = ? ");
        		}
                l_lisBindVars.add(l_request.summaryDays);
        	}
        	//検索条件の適用に30%割れが指定された場合
        	if(l_request.summary.equals(WEB3AdminTPPaymentRequisitionManageDef.break30))
        	{
        		//摘要日数の指定が30%割れの指定最大日だった場合
        		if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.max30SpecifyDay))
        		{
                	l_sbWhere.append(" and   BREAK30ELAPSED_DAYS >= ? ");
        		}
        		else if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.allSpecifyDay))
        		{
                	l_sbWhere.append(" and   BREAK30ELAPSED_DAYS > ? ");
        		}
        		else
        		{
                	l_sbWhere.append(" and   BREAK30ELAPSED_DAYS = ? ");
        		}
                l_lisBindVars.add(l_request.summaryDays);
        	}
        }

        //顧客コードが検索条件に含まれていない場合は入金請求が発生して
        if(l_request.accountCode == null)
        {
	        l_sbWhere.append(" and (payment_requisition_amount_0 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_1 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_2 > 0 ");
	        l_sbWhere.append(" or cash_balance0 < 0 )");
        }
        
    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("substr(account_id,9,6) asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(PaymentRequisitionMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionMarginParams.TYPE});
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(PaymentRequisitionMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex,new RowType[] {PaymentRequisitionMarginParams.TYPE});
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
     * (get入金請求管理顧客Params履歴<現物顧客>)
     * <BR>
     * 以下の条件で入金請求管理テーブルから検索顧客の全データを検索。
     * <BR>
     * [引数]
     * 管理者
     * 入金請求管理履歴リクエスト
     * <BR>
     * 部店コード、顧客コードより顧客取得
     * 顧客ID = (顧客.顧客ID)
     * @@param WEB3Administrator - 管理者
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - 入金請求管理履歴リクエスト
     * @@return ListPage - 検索結果データ
     * @@roseuid 44072C6F017A
     */
    public ListPage getPaymentRequisitionManageParamsHistoryEquity(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsHistoryEquity";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        //管理者所属の証券会社
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //条件作成
        //会社コード、部店コード
        l_sbWhere.append(" account_id in ( ");
        l_sbWhere.append(" select account_id ");
        l_sbWhere.append(" from main_account ");
        l_sbWhere.append(" where institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //部店コード
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //顧客コード
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%'");
            l_lisBindVars.add(l_request.accountCode);
        }

        l_sbWhere.append(") ");

    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("calc_date asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List results = l_qp.doFindAllQuery(PaymentRequisitionEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionEquityParams.TYPE});
            int size = results.size();
            return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
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
    /**
     * (get入金請求管理顧客Params履歴<信用顧客>)
     * <BR>
     * 以下の条件で入金請求管理テーブルから検索顧客の全データを検索。
     * <BR>
     * [引数]
     * 管理者
     * 入金請求管理履歴リクエスト
     * <BR>
     * 部店コード、顧客コードより顧客取得
     * 顧客ID = (顧客.顧客ID)
     * @@param WEB3Administrator - 管理者
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - 入金請求管理履歴リクエスト
     * @@return ListPage - 検索結果データ
     * @@roseuid 44072C780216
     */
    public ListPage getPaymentRequisitionManageParamsHistoryMargin(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsHistoryMargin";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        //管理者所属の証券会社
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //条件作成
        //会社コード、部店コード
        l_sbWhere.append(" account_id in ( ");
        l_sbWhere.append(" select account_id ");
        l_sbWhere.append(" from main_account ");
        l_sbWhere.append(" where institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //部店コード
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //顧客コード
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%' ");
            l_lisBindVars.add(l_request.accountCode);
        }

        l_sbWhere.append(") ");

    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("calc_date asc");        
        try
        {
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List results = l_qp.doFindAllQuery(PaymentRequisitionMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionMarginParams.TYPE});
            int size = results.size();
            return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
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
     * (get入金請求管理顧客一覧<現物顧客>)<BR>
     * <BR>
     * 入金請求管理現物一覧Paramsより取得したデータを基にレスポンスの作成を行う。
     * <BR>
     * [引数]
     * 入金請求管理現物一覧Params
     * <BR>
     * @@param l_request - 入金請求管理現物一覧Params
     * @@return WEB3AdminTPPaymentRequisitionManageSearchResponse - 検索結果
     * @@roseuid 440579950205
     */
    protected WEB3AdminTPPaymentRequisitionManageSearchResponse getPaymentRequisitionManageList(WEB3AdminTPPaymentRequisitionManageSearchRequest l_request) 
    throws WEB3BaseException 
    {
        final String METHOD_NAME = "getPaymentRequisitionManageList";
        log.entering(METHOD_NAME);

        //リクエストの属性妥当性チェック
        l_request.validate();

        //管理者
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェック
        l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

        //入金請求顧客リスト
        List l_list = new ArrayList();

        ListPage l_lisRows = null; 
        WEB3AdminTPPaymentRequisitionManageUnit l_unit = null;

        //現物顧客
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST ) )
        {
            //get請求発生顧客Params一覧<現物顧客>
            l_lisRows = this.getPaymentRequisitionManageParamsListEquity(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionEquityRow l_row = (PaymentRequisitionEquityRow)l_iter.next();
                //入金請求管理一覧ユニット
                //get入金請求管理一覧<現物顧客>
                l_unit = getPaymentRequisitionManageAccountListEquity(l_row);
                if( l_unit != null )
                {
                    l_list.add( l_unit );
                }
            }                        
        }
        
        //信用顧客
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.MARGIN_CUST ) )
        {
            //get請求発生顧客Params一覧<信用顧客>
            l_lisRows = this.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionMarginRow l_row = (PaymentRequisitionMarginRow)l_iter.next();
                //入金請求管理一覧ユニット
                //get入金請求管理一覧<信用顧客>
                l_unit = getPaymentRequisitionManageAccountListMargin(l_row);
                if( l_unit != null )
                {
                    l_list.add( l_unit );
                }
            }                        
        }
        
        //レスポンス
        WEB3AdminTPPaymentRequisitionManageSearchResponse l_response = (WEB3AdminTPPaymentRequisitionManageSearchResponse)l_request.createResponse();
        l_response.manageUnits = (WEB3AdminTPPaymentRequisitionManageUnit[])l_list.toArray(new WEB3AdminTPPaymentRequisitionManageUnit[l_list.size()]);//入金請求管理一覧

        //プロパティセット
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

        //レスポンス返却
        return l_response;
}
    
    /**
     * (get入金請求管理顧客一覧<信用顧客>)<BR>
     * <BR>
     * 入金請求管理信用一覧Paramsより取得したデータを基にレスポンスの作成を行う。
     * <BR>
     * [引数]
     * 入金請求管理信用一覧Params
     * <BR>
     * @@param l_request - 入金請求管理信用一覧Params
     * @@return WEB3AdminTPPaymentRequisitionManageHistoryResponse - 検索結果
     * @@roseuid 440579950205
     */
    protected WEB3AdminTPPaymentRequisitionManageHistoryResponse getPaymentRequisitionManageHistory(WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request) 
    throws WEB3BaseException 
    {
        final String METHOD_NAME = "getPaymentRequisitionManageHistory";
        log.entering(METHOD_NAME);

        //部店コード
        String branchCode = null;
        //顧客コード
        String accountCode = null;
        //顧客名
        String accountName = null;
        
        //リクエストの属性妥当性チェック
        l_request.validate();

        //管理者
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //管理者権限チェック
        l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

        //入金請求顧客リスト
        List l_list = new ArrayList();

        ListPage l_lisRows = null; 
        WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit = null;

        //現物顧客
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST ) )
        {
            //get請求発生顧客Params履歴<現物顧客>
            l_lisRows = this.getPaymentRequisitionManageParamsHistoryEquity(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionEquityRow l_row = (PaymentRequisitionEquityRow)l_iter.next();
                //入金請求管理一覧ユニット
                //get入金請求管理履歴<現物顧客>
                l_unit = getPaymentRequisitionManageAccountHistoryEquity(l_row);
                if( l_unit != null )
                {
                    branchCode   = format(l_row.getAccountId()).substring(5,8);//部店コード
                    accountCode   = format(l_row.getAccountId()).substring(8,14);//顧客コード
                    accountName   = l_row.getFamilyName();//顧客名
                    l_list.add( l_unit );
                }
            }                        
        }
        
        //信用顧客
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.MARGIN_CUST ) )
        {
            //get請求発生顧客Params履歴<信用顧客>
            l_lisRows = this.getPaymentRequisitionManageParamsHistoryMargin(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionMarginRow l_row = (PaymentRequisitionMarginRow)l_iter.next();
                //入金請求管理一覧ユニット
                //get入金請求管理履歴<信用顧客>
                l_unit = getPaymentRequisitionManageAccountHistoryMargin(l_row);
                if( l_unit != null )
                {
                    branchCode   = format(l_row.getAccountId()).substring(5,8);//部店コード
                    accountCode   = format(l_row.getAccountId()).substring(8,14);//顧客コード
                    accountName   = l_row.getFamilyName();//顧客名
                    l_list.add( l_unit );
                }
            }                        
        }        
        //レスポンス
        WEB3AdminTPPaymentRequisitionManageHistoryResponse l_response = (WEB3AdminTPPaymentRequisitionManageHistoryResponse)l_request.createResponse();
        l_response.branchCode = branchCode;
        l_response.accountCode = accountCode;
        l_response.accountName = accountName;
        l_response.historyUnits = (WEB3AdminTPPaymentRequisitionManageHistoryUnit[])l_list.toArray(new WEB3AdminTPPaymentRequisitionManageHistoryUnit[l_list.size()]);//入金請求管理履歴

        //レスポンス返却
        return l_response;
    }
    
    /**
     * get入金請求管理顧客一覧<現物顧客>
     * @@param 顧客
     * @@param PaymentRequisitionEquityRow
     * @@return WEB3AdminTPPaymentRequisitionManageUnit
     * @@roseuid 4405BECE00DC
     */
    public WEB3AdminTPPaymentRequisitionManageUnit getPaymentRequisitionManageAccountListEquity(PaymentRequisitionEquityRow l_requisitionRow)
    throws WEB3BaseException
    {
    	//余力停止区分
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //入金請求管理詳細
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //プロパティセット
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//請求区分
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//預り金
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR客勘残
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//日計り拘束金
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//その他拘束金

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//請求区分
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//預り金
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR客勘残
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//日計り拘束金
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//その他拘束金

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//請求区分
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//預り金
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR客勘残
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//日計り拘束金
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//その他拘束金

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//請求区分
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//預り金
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR客勘残
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//日計り拘束金
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//その他拘束金

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//請求区分
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//預り金
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR客勘残
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//日計り拘束金
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//その他拘束金

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//請求区分
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//預り金
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR客勘残
        l_detailUnits[5].dayTradeRestraint = null;//日計り拘束金
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//その他拘束金

        //立替金顧客ユニット
        WEB3AdminTPPaymentRequisitionManageUnit l_unit = new WEB3AdminTPPaymentRequisitionManageUnit();

        //プロパティセット
        l_unit.branchCode   = format(l_requisitionRow.getAccountId()).substring(5,8);//部店コード
        l_unit.accountCode   = format(l_requisitionRow.getAccountId()).substring(8,14);//顧客コード
        l_unit.accountName   = l_requisitionRow.getFamilyName();//顧客名
        l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();//扱者コード
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//顧客属性
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//取引停止区分
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getIfoOpenPositionStop();//先物OP新規建余力区分
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getPaymentStop();//出金余力区分
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getOtherTradingStop();//その他商品買付余力区分
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.manageDetails = l_detailUnits;//入金請求管理詳細
        log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
        log.debug("変換した立替金顧客ユニット=" + l_unit.toString());

        return l_unit;
    }
    
    /**
     * get入金請求管理顧客一覧<信用顧客>
     * @@param 顧客
     * @@param PaymentRequisitionMarginRow
     * @@return WEB3AdminTPPaymentRequisitionManageUnit
     * @@roseuid 4406F324017A
     */
    public WEB3AdminTPPaymentRequisitionManageUnit getPaymentRequisitionManageAccountListMargin(PaymentRequisitionMarginRow l_requisitionRow) 
    {
    	//余力停止区分
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //入金請求管理詳細
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //プロパティセット
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//請求区分
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//預り金
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR客勘残
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//日計り拘束金
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//その他拘束金
        l_detailUnits[0].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit0());//委託保証金
        l_detailUnits[0].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit0());//受入保証金残
        l_detailUnits[0].marginDeposit = format(l_requisitionRow.getMarginDeposit0());//必要保証金
        l_detailUnits[0].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit0());//内現金必要保証金
        l_detailUnits[0].contractAmount = format(l_requisitionRow.getContractAmount0());//建玉代金
        if(l_requisitionRow.getMarginDepositRate0IsNull() == false)
        {
        	l_detailUnits[0].marginDepositRate = format(l_requisitionRow.getMarginDepositRate0());//保証金預託率
        }

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//請求区分
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//預り金
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR客勘残
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//日計り拘束金
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//その他拘束金
        l_detailUnits[1].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit1());//委託保証金
        l_detailUnits[1].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit1());//受入保証金残
        l_detailUnits[1].marginDeposit = format(l_requisitionRow.getMarginDeposit1());//必要保証金
        l_detailUnits[1].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit1());//内現金必要保証金
        l_detailUnits[1].contractAmount = format(l_requisitionRow.getContractAmount1());//建玉代金
        if(l_requisitionRow.getMarginDepositRate1IsNull() == false)
        {
        	l_detailUnits[1].marginDepositRate = format(l_requisitionRow.getMarginDepositRate1());//保証金預託率
        }

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//請求区分
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//預り金
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR客勘残
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//日計り拘束金
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//その他拘束金
        l_detailUnits[2].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit2());//委託保証金
        l_detailUnits[2].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit2());//受入保証金残
        l_detailUnits[2].marginDeposit = format(l_requisitionRow.getMarginDeposit2());//必要保証金
        l_detailUnits[2].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit2());//内現金必要保証金
        l_detailUnits[2].contractAmount = format(l_requisitionRow.getContractAmount2());//建玉代金
        if(l_requisitionRow.getMarginDepositRate2IsNull() == false)
        {
        	l_detailUnits[2].marginDepositRate = format(l_requisitionRow.getMarginDepositRate2());//保証金預託率
        }

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//請求区分
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//預り金
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR客勘残
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//日計り拘束金
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//その他拘束金
        l_detailUnits[3].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit3());//委託保証金
        l_detailUnits[3].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit3());//受入保証金残
        l_detailUnits[3].marginDeposit = format(l_requisitionRow.getMarginDeposit3());//必要保証金
        l_detailUnits[3].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit3());//内現金必要保証金
        l_detailUnits[3].contractAmount = format(l_requisitionRow.getContractAmount3());//建玉代金
        if(l_requisitionRow.getMarginDepositRate3IsNull() == false)
        {
        	l_detailUnits[3].marginDepositRate = format(l_requisitionRow.getMarginDepositRate3());//保証金預託率
        }

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//請求区分
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//預り金
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR客勘残
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//日計り拘束金
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//その他拘束金
        l_detailUnits[4].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit4());//委託保証金
        l_detailUnits[4].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit4());//受入保証金残
        l_detailUnits[4].marginDeposit = format(l_requisitionRow.getMarginDeposit4());//必要保証金
        l_detailUnits[4].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit4());//内現金必要保証金
        l_detailUnits[4].contractAmount = format(l_requisitionRow.getContractAmount4());//建玉代金
        if(l_requisitionRow.getMarginDepositRate4IsNull() == false)
        {
        	l_detailUnits[4].marginDepositRate = format(l_requisitionRow.getMarginDepositRate4());//保証金預託率
        }

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//請求区分
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//預り金
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR客勘残
        l_detailUnits[5].dayTradeRestraint = null;//日計り拘束金
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//その他拘束金
        l_detailUnits[5].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit5());//委託保証金
        l_detailUnits[5].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit5());//受入保証金残
        l_detailUnits[5].marginDeposit = format(l_requisitionRow.getMarginDeposit5());//必要保証金
        l_detailUnits[5].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit5());//内現金必要保証金
        l_detailUnits[5].contractAmount = format(l_requisitionRow.getContractAmount5());//建玉代金
        if(l_requisitionRow.getMarginDepositRate5IsNull() == false)
        {
        	l_detailUnits[5].marginDepositRate = format(l_requisitionRow.getMarginDepositRate5());//保証金預託率
        }

        //立替金顧客ユニット
        WEB3AdminTPPaymentRequisitionManageUnit l_unit = new WEB3AdminTPPaymentRequisitionManageUnit();

        //プロパティセット
        l_unit.branchCode   = format(l_requisitionRow.getAccountId()).substring(5,8);//部店コード
        l_unit.accountCode   = format(l_requisitionRow.getAccountId()).substring(8,14);//顧客コード
        l_unit.accountName   = l_requisitionRow.getFamilyName();//顧客名
        l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();//扱者コード
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//顧客属性
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//取引停止区分
        }
        if(!l_requisitionRow.getMarginOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getMarginOpenPositionStop();//信用新規建余力区分
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getIfoOpenPositionStop();//先物OP新規建余力区分
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getPaymentStop();//出金余力区分
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[4] = l_requisitionRow.getOtherTradingStop();//その他商品買付余力区分
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.break20Day   = l_requisitionRow.getBreak20day();//20%割れ発生日
        l_unit.break20ElapsedDays   = l_requisitionRow.getBreak20elapsedDays();//20%割れ経過日数
        l_unit.break30Day   = l_requisitionRow.getBreak30day();//30%割れ発生日
        l_unit.break30ElapsedDays   = l_requisitionRow.getBreak30elapsedDays();//30%割れ経過日数
        l_unit.manageDetails = l_detailUnits;//入金請求管理詳細
        log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
        log.debug("変換した立替金顧客ユニット=" + l_unit.toString());

        return l_unit;
    }
    
    /**
     * get入金請求管理顧客履歴<現物顧客>
     * @@param PaymentRequisitionEquityRow
     * @@return WEB3AdminTPPaymentRequisitionManageHistoryUnit
     * @@roseuid 44072D160293
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit getPaymentRequisitionManageAccountHistoryEquity(PaymentRequisitionEquityRow l_requisitionRow) 
    {
    	//余力停止区分
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //入金請求管理詳細
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //プロパティセット
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//請求区分
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//預り金
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR客勘残
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//日計り拘束金
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//その他拘束金

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//請求区分
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//預り金
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR客勘残
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//日計り拘束金
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//その他拘束金

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//請求区分
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//預り金
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR客勘残
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//日計り拘束金
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//その他拘束金

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//請求区分
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//預り金
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR客勘残
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//日計り拘束金
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//その他拘束金

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//請求区分
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//預り金
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR客勘残
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//日計り拘束金
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//その他拘束金

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//請求区分
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//預り金
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR客勘残
        l_detailUnits[5].dayTradeRestraint = null;//日計り拘束金
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//その他拘束金

        //立替金顧客ユニット
        WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit = new WEB3AdminTPPaymentRequisitionManageHistoryUnit();

        //プロパティセット
        l_unit.bizDate = l_requisitionRow.getCalcDate();//日付
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//顧客属性
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//取引停止区分
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getIfoOpenPositionStop();//先物OP新規建余力区分
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getPaymentStop();//出金余力区分
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getOtherTradingStop();//その他商品買付余力区分
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.manageDetails = l_detailUnits;//入金請求管理詳細
        log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
        log.debug("変換した立替金顧客ユニット=" + l_unit.toString());

        return l_unit;
    }
    
    /**
     * get入金請求管理顧客履歴<信用顧客>
     * @@param PaymentRequisitionMarginRow
     * @@return WEB3AdminTPPaymentRequisitionManageHistoryUnit
     * @@roseuid 44072D2301D7
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit getPaymentRequisitionManageAccountHistoryMargin(PaymentRequisitionMarginRow l_requisitionRow) 
    {
    	//余力停止区分
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //入金請求管理詳細
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //プロパティセット
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//請求区分
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//預り金
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR客勘残
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//日計り拘束金
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//その他拘束金
        l_detailUnits[0].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit0());//委託保証金
        l_detailUnits[0].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit0());//受入保証金残
        l_detailUnits[0].marginDeposit = format(l_requisitionRow.getMarginDeposit0());//必要保証金
        l_detailUnits[0].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit0());//内現金必要保証金
        l_detailUnits[0].contractAmount = format(l_requisitionRow.getContractAmount0());//建玉代金
        if(l_requisitionRow.getMarginDepositRate0IsNull() == false)
        {
        	l_detailUnits[0].marginDepositRate = format(l_requisitionRow.getMarginDepositRate0());//保証金預託率
        }

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//請求区分
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//預り金
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR客勘残
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//日計り拘束金
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//その他拘束金
        l_detailUnits[1].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit1());//委託保証金
        l_detailUnits[1].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit1());//受入保証金残
        l_detailUnits[1].marginDeposit = format(l_requisitionRow.getMarginDeposit1());//必要保証金
        l_detailUnits[1].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit1());//内現金必要保証金
        l_detailUnits[1].contractAmount = format(l_requisitionRow.getContractAmount1());//建玉代金
        if(l_requisitionRow.getMarginDepositRate1IsNull() == false)
        {
        	l_detailUnits[1].marginDepositRate = format(l_requisitionRow.getMarginDepositRate1());//保証金預託率
        }

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//請求区分
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//預り金
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR客勘残
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//日計り拘束金
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//その他拘束金
        l_detailUnits[2].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit2());//委託保証金
        l_detailUnits[2].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit2());//受入保証金残
        l_detailUnits[2].marginDeposit = format(l_requisitionRow.getMarginDeposit2());//必要保証金
        l_detailUnits[2].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit2());//内現金必要保証金
        l_detailUnits[2].contractAmount = format(l_requisitionRow.getContractAmount2());//建玉代金
        if(l_requisitionRow.getMarginDepositRate2IsNull() == false)
        {
        	l_detailUnits[2].marginDepositRate = format(l_requisitionRow.getMarginDepositRate2());//保証金預託率
        }

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//請求区分
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//預り金
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR客勘残
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//日計り拘束金
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//その他拘束金
        l_detailUnits[3].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit3());//委託保証金
        l_detailUnits[3].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit3());//受入保証金残
        l_detailUnits[3].marginDeposit = format(l_requisitionRow.getMarginDeposit3());//必要保証金
        l_detailUnits[3].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit3());//内現金必要保証金
        l_detailUnits[3].contractAmount = format(l_requisitionRow.getContractAmount3());//建玉代金
        if(l_requisitionRow.getMarginDepositRate3IsNull() == false)
        {
        	l_detailUnits[3].marginDepositRate = format(l_requisitionRow.getMarginDepositRate3());//保証金預託率
        }

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//請求区分
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//預り金
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR客勘残
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//日計り拘束金
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//その他拘束金
        l_detailUnits[4].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit4());//委託保証金
        l_detailUnits[4].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit4());//受入保証金残
        l_detailUnits[4].marginDeposit = format(l_requisitionRow.getMarginDeposit4());//必要保証金
        l_detailUnits[4].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit4());//内現金必要保証金
        l_detailUnits[4].contractAmount = format(l_requisitionRow.getContractAmount4());//建玉代金
        if(l_requisitionRow.getMarginDepositRate4IsNull() == false)
        {
        	l_detailUnits[4].marginDepositRate = format(l_requisitionRow.getMarginDepositRate4());//保証金預託率
        }

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//計算日
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//入金請求額
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//請求区分
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//預り金
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR客勘残
        l_detailUnits[5].dayTradeRestraint = null;//日計り拘束金
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//その他拘束金
        l_detailUnits[5].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit5());//委託保証金
        l_detailUnits[5].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit5());//受入保証金残
        l_detailUnits[5].marginDeposit = format(l_requisitionRow.getMarginDeposit5());//必要保証金
        l_detailUnits[5].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit5());//内現金必要保証金
        l_detailUnits[5].contractAmount = format(l_requisitionRow.getContractAmount5());//建玉代金
        if(l_requisitionRow.getMarginDepositRate5IsNull() == false)
        {
        	l_detailUnits[5].marginDepositRate = format(l_requisitionRow.getMarginDepositRate5());//保証金預託率
        }

        //立替金顧客ユニット
        WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit = new WEB3AdminTPPaymentRequisitionManageHistoryUnit();

        //プロパティセット
        l_unit.bizDate = l_requisitionRow.getCalcDate();//日付
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//顧客属性
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//取引停止区分
        }
        if(!l_requisitionRow.getMarginOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getMarginOpenPositionStop();//信用新規建余力区分
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getIfoOpenPositionStop();//先物OP新規建余力区分
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getPaymentStop();//出金余力区分
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[4] = l_requisitionRow.getOtherTradingStop();//その他商品買付余力区分
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.break20Day = l_requisitionRow.getBreak20day();//20%割れ発生日
        l_unit.break20ElapsedDays   = l_requisitionRow.getBreak20elapsedDays();//20%割れ経過日数
        l_unit.break30Day   = l_requisitionRow.getBreak30day();//30%割れ発生日
        l_unit.break30ElapsedDays   = l_requisitionRow.getBreak30elapsedDays();//30%割れ経過日数
        l_unit.manageDetails = l_detailUnits;//入金請求管理詳細
        log.debug("RequisitionAccountMarginRow=" + l_requisitionRow.toString());       
        log.debug("変換した立替金顧客ユニット=" + l_unit.toString());

        return l_unit;
    }

    /**
     * (exetute)<BR>
     * <BR>
     * 引数で与えられたリクエストを基に業務処理を行い、処理結果をレスポンスに設定してを返す。<BR>
     * <BR>
     * シーケンス図「execute」参照。<BR>
     *  <BR>
     * @@param l_request - リクエスト
     * @@return 処理結果が設定されたレスポンス
     */
	public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException {

		
		final String METHOD_NAME = "execute";

        log.entering(METHOD_NAME);
      
        //レスポンス
        WEB3GenResponse l_response = null;

        //入金請求管理一覧画面表示
        if(l_request instanceof WEB3AdminTPPaymentRequisitionManageSearchRequest)
        {
            l_response =  getPaymentRequisitionManageList( (WEB3AdminTPPaymentRequisitionManageSearchRequest)l_request);           
        }
		else if(l_request instanceof WEB3AdminTPPaymentRequisitionManageHistoryRequest)
        //入金請求管理履歴画面表示
        {
            l_response =  getPaymentRequisitionManageHistory((WEB3AdminTPPaymentRequisitionManageHistoryRequest)l_request);           
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
