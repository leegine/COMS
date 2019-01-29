head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinanceAmountRepayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 融資額返済UnitServiceImpl(WEB3AioFinanceAmountRepayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 唐性峰 (中訊) 新規作成                                     
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.data.PayRequiredAmountParams;
import webbroker3.aio.data.PayRequiredAmountRow;
import webbroker3.aio.message.WEB3AioFinanceAmountRepayRequest;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayService;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (融資額返済サービスImpl)<BR>
 * 融資額返済サービス実装クラス<BR>
 * 
 * @@author 唐性峰(中訊)
 * @@version 1.0
 */
public class WEB3AioFinanceAmountRepayServiceImpl implements WEB3AioFinanceAmountRepayService
{
    /**
     * ログユーティリティ<BR>
     */
    WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioFinanceAmountRepayServiceImpl.class);
    
    /**
     * @@roseuid 4510F52E0148
     */
    public WEB3AioFinanceAmountRepayServiceImpl() 
    {
     
    }
    
    /**
     * 証券担保ローンの返済額を設定する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（融資額返済）execute」参照。<BR>
     * <BR> 
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioFinanceAmountRepayRequest l_repayRequest = null;
        if (l_request instanceof WEB3AioFinanceAmountRepayRequest)
        {
            l_repayRequest = (WEB3AioFinanceAmountRepayRequest)l_request;
        }
        else 
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメーターのタイプが不正です。");
        }
        List l_lisPayRequiredAmount = null;
        try
        {
            //1.1 getDefaultProcessor( )
            //クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //検索用
            Timestamp l_tsBaseDate = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsBaseDate);
            Timestamp l_strDate = l_genBizDate.roll(0);
            String l_strFomateDate = WEB3DateUtility.formatDate(l_strDate, "yyyyMMdd");
            
            String l_strWhere = "proc_date = ? and status = ?";
            Object[] l_objWhere = {l_strFomateDate, WEB3StatusDef.NOT_DEAL};

            //1.2 doFindAllQuery(arg0 : RowType, arg1 : String, arg2 : Object[])
            //処理日 = 当日営業日 
            //処理区分 = "0"(未処理の区分) 
            //[引数] 
            //Rowタイプ： 返済必要額データRow.TYPE 
            //Where： "proc_date=? and status=?" 
            //リスト： 以下の項目のリスト 
            //当日営業日(YYYYMMDD) 
            //"0"
            l_lisPayRequiredAmount =
                l_queryProcessor.doFindAllQuery(
                    PayRequiredAmountRow.TYPE,
                    l_strWhere,
                    l_objWhere);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //1.3 取得した返済必要額データParams毎のLoop処理
        int l_intLength = 0;
        if (l_lisPayRequiredAmount != null && l_lisPayRequiredAmount.size() != 0)
        {
            l_intLength = l_lisPayRequiredAmount.size();
        }
        for (int i = 0; i < l_intLength; i++)
        {
            //1.3.1 execute(返済必要額データParams : 返済必要額データParams)
            //返済必要額データ更新処理を行う。 
            //[引数] 
            //返済必要額データParams：取得した返済必要額データParams
            WEB3AioFinanceAmountRepayUnitService l_secFinanceLoanRepayUnitService =
                (WEB3AioFinanceAmountRepayUnitService)Services.getService(
                    WEB3AioFinanceAmountRepayUnitService.class);
            PayRequiredAmountRow l_payRequiredAmountRow = 
                (PayRequiredAmountRow)l_lisPayRequiredAmount.get(i);
            PayRequiredAmountParams l_payRequiredAmountParams = 
                new PayRequiredAmountParams(l_payRequiredAmountRow);
            l_secFinanceLoanRepayUnitService.execute(l_payRequiredAmountParams);
        }
        
        //1.4 レスポンスオブジェクトを生成し返却する。
        WEB3BackResponse l_response = l_repayRequest.createResponse();
         
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
