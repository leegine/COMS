head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携サービスImpl(WEB3AioOnPaymentCooperationServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AioOnPaymentCooperationRequest;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationService;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金連携サービスImpl) <BR>
 * 出金連携サービス実装クラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationServiceImpl implements WEB3AioOnPaymentCooperationService
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationServiceImpl.class);  
    
    /**
     * @@roseuid 41E77F4A01D4
     */
    public WEB3AioOnPaymentCooperationServiceImpl()
    {
    }

    /**
     * 出金連携処理を行う。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「（出金連携）execute」 参照 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3BackResponse
     * @@roseuid 41C7B2080071
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioOnPaymentCooperationRequest l_onPaymentCooperationRequest = 
            (WEB3AioOnPaymentCooperationRequest)l_request;        
        
        //1.1 validate( )(出金連携リクエスト::validate)
        //リクエストのチェックを行う。
        l_onPaymentCooperationRequest.validate();
        
        //1.2 InstitutionImpl(証券会社コード : long)
        //証券会社オブジェクトを取得する。 
        //[引数] 
        //証券会社コード ： リクエスト.証券会社コード
        AccountManager l_accountManager = GtlUtils.getAccountManager();
       
        Institution l_institution = null;
        try
        {                
            l_institution = 
                l_accountManager.getInstitution(
                    l_onPaymentCooperationRequest.institutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" テーブルに該当するデータがありません: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.3 getBranches( )
        //証券会社に所属するすべての部店をBranchオブジェクトの配列として取得する。
        Branch[] l_branchs = l_institution.getBranches();
        int l_intLength = 0;
        
        if (l_branchs != null || l_branchs.length != 0)
        {
            l_intLength = l_branchs.length;
            log.debug("Branchオブジェクトの配列.length := " + l_intLength);
        }
        
        //1.4 ArrayList( )
        //注文単位オブジェクト用のArrayList( )を作成する。
        List l_lisAioOrderUnit = new ArrayList();
        
        //1.5 getBranches( )の要素毎にLoop処理を行う。
        for (int i = 0; i < l_intLength; i++)
        {
            //1.5.1 is投信解約時出金注文生成( )
            //部店が出金連携を行うかどうかの判別を行う。 

            //戻り値が”true”の場合は、出金連携の処理実施。 
            //戻り値が”false”の場合は、出金連携の処理対象外とする。
            WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_branchs[i];
            
            boolean l_blnIsPaymentOrderCreate = l_branch.isPaymentOrderCreate();
            
            //戻り値が”false”の場合は、以降の処理をスキップする。
            if (!l_blnIsPaymentOrderCreate)
            {
                log.debug("is投信解約時出金注文生成( )の戻り値が”false”の場合");
                continue;
            }
            
            //1.5.2 get出金連携発注日(String)(出金連携サービスImpl::get出金連携発注日)
            //発注日を取得する。 

            log.debug("l_branch.getBranchCode() = " + l_branch.getBranchCode());
            //[引数] 
            //部店コード ： 部店[index].getBranchCode
            String l_strOnPaymentCoopBizDate = 
                this.getOnPaymentCooperationBizDate(l_branch.getBranchCode());            
            
            //1.5.3 （*1）注文単位テーブルの読込み
            //（*1）
            //以下の条件で注文単位テーブルよりデータを取得する。

            //[検索条件]
            // 部店ID ： 部店[index].getBranchId( )
            // 注文種別 ： 1001（出金）
            // 注文カテゴリ ： 9（入出金）
            // 注文状態 ：1（受付済）
            // 銘柄タイプ ： 5（現金）
            // 注文有効状態 ： 1（オープン）
            // 失効区分 ： 1（オープン）
            // 発注日 ： get出金連携発注日( )の戻り値
            // 出金申込区分 ：null or ”mf（投資信託）”

            //[ソート条件]
            // 口座ID　@昇順
            try
            {                
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();         
                StringBuffer l_strWhere = new StringBuffer();
                l_strWhere.append(" branch_id = ?");     
                l_strWhere.append(" and order_type = ?");
                l_strWhere.append(" and order_categ = ?");
                l_strWhere.append(" and order_status = ?");    
                l_strWhere.append(" and product_type = ?");    
                l_strWhere.append(" and order_open_status = ?");   
                l_strWhere.append(" and expiration_status = ?");   
                l_strWhere.append(" and biz_date = ?");   
                l_strWhere.append(" and (payment_application_div is null");   
                l_strWhere.append(" or payment_application_div = ? )");
                
                String l_strOrderBy = " account_id "; 
                Object[] l_objMutualFrgncalWhere =
                {
                    new Long(l_branch.getBranchId()), 
                    OrderTypeEnum.CASH_OUT,
                    OrderCategEnum.CASH_IN_OUT,
                    OrderStatusEnum.ACCEPTED,
                    ProductTypeEnum.CASH,
                    OrderOpenStatusEnum.OPEN,
                    OrderExpirationStatusEnum.OPEN,
                    l_strOnPaymentCoopBizDate, 
                    WEB3AioPaymentApplicationDivDef.MF
                };
                List l_lisAioOrderUnitRows = 
                    l_queryProcessor.doFindAllQuery(
                        AioOrderUnitRow.TYPE,
                        l_strWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objMutualFrgncalWhere);
                
                int l_intSize = 0;
                if (l_lisAioOrderUnitRows != null && !l_lisAioOrderUnitRows.isEmpty())
                {
                    l_intSize = l_lisAioOrderUnitRows.size();
                    log.debug("l_lisAioOrderUnitRows.size() = " + l_intSize);
                }
                else
                {
                    log.debug("該当する注文データはありません。【部店コード】：" + l_branch.getBranchCode());
                    continue;
                }
                
                //1.5.4 変数.口座ID = null をセット
                String l_strAccountId = null;
                String l_strPaymentApplicationDiv = null;
                
                //1.5.5 取得した注文単位ごとのループ処理
                //ループ処理は、注文単位要素数に”1”を加えた回数
                for (int j = 0; j < l_intSize + 1; j++)
                {
                    String l_strAccountIdLoop = null;
                    String l_strPaymentApplicationDivLoop = null;
                    AioOrderUnit l_aioOrderUnit = null;
                    
                    if (l_intSize != j)
                    {
                        AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(j);
                        l_strAccountIdLoop = l_aioOrderUnitRow.getAccountId() + "";
                        l_strPaymentApplicationDivLoop = l_aioOrderUnitRow.getPaymentApplicationDiv();
                        
                        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                        WEB3AioOrderManager l_aioOrderManager = 
                            (WEB3AioOrderManager) l_finApp.getTradingModule(
                                ProductTypeEnum.AIO).getOrderManager();                    
                    
                        OrderUnit[] l_orderUnits = 
                            l_aioOrderManager.getOrderUnits(l_aioOrderUnitRow.getOrderId());
                        l_aioOrderUnit = (AioOrderUnit) l_orderUnits[0];  
                    }
                    
                    //1.5.5.1 （*2-1）分岐フロー
                    //（*2-1） 変数.口座ID = nullの場合
                    if (l_strAccountId == null)
                    {
                        log.debug("変数.口座ID = nullの場合");
                        //1.5.5.1.1 注文単位[index].口座IDを変数.口座IDにセット
                        l_strAccountId = l_strAccountIdLoop;
                        l_strPaymentApplicationDiv = l_strPaymentApplicationDivLoop;
                        
                        //1.5.5.1.2 add(注文単位オブジェクト[index] : Object)
                        //注文単位リストに同じ顧客IDの注文単位オブジェクトを追加する。 
      
                        //[引数] 
                        //注文単位オブジェクト[index]                        
                        l_lisAioOrderUnit.add(l_aioOrderUnit);
                        
                        //1.5.5.1.3 以降の処理をスキップする。
                        continue;
                    }
                    
                    //1.5.5.2 （*2-2）分岐フロー
                    //（*2-2）変数.口座ID ≠ 注文単位[index].口座IDの場合
                    else if (!l_strAccountId.equals(l_strAccountIdLoop))
                    {
                        log.debug("変数.口座ID ≠ 注文単位[index].口座IDの場合");
                        //1.5.5.2.1 ArrayList( )の要素数 > ”1”の場合
                        //  or
                        //ArrayList( )の要素数 == ”1”の場合かつ
                        //注文単位.出金申込区分 == ”mf（投信）”の場合
                        if (l_lisAioOrderUnit.size() > 1 || 
                            l_lisAioOrderUnit.size() == 1 && 
                            WEB3AioPaymentApplicationDivDef.MF.equals(l_strPaymentApplicationDiv))
                        {
                            log.debug("ArrayList( )の要素数 > ”1”の場合 " +
                                "or ArrayList( )の要素数 == ”1”の場合かつ " +
                                "注文単位.出金申込区分 == ”mf（投信）”の場合");
                            
                            //1.5.5.2.1.1 toArray( )
                            //注文単位オブジェクトの配列を返却する。
                            AioOrderUnit[] l_aioOrderUnits = new AioOrderUnit[l_lisAioOrderUnit.size()];
                            l_lisAioOrderUnit.toArray(l_aioOrderUnits);
                            
                            //1.5.5.2.1.2 execute(AioOrderUnit[])
                            //出金連携処理を行う。 
                            //[引数] 
                            //注文単位オブジェクト[ ] ： 注文単位オブジェクトの配列 

                            //シーケンス図 
                            //「(出金連携)出金連携」参照
                            WEB3AioOnPaymentCooperationUnitService l_onPaymentCoopUnitService =
                                (WEB3AioOnPaymentCooperationUnitService) Services.getService(
                                    WEB3AioOnPaymentCooperationUnitService.class);
                            
                            l_onPaymentCoopUnitService.execute(l_aioOrderUnits);                            
                        }
                        
                        //1.5.5.2.2  注文単位[index].口座IDを変数.口座IDにセット
                        l_strAccountId = l_strAccountIdLoop;
                        l_strPaymentApplicationDiv = l_strPaymentApplicationDivLoop;
                        
                        //1.5.5.2.3 clear( )
                        //次の注文単位オブジェクト用（異なる顧客IDの注文用）にクリアする。
                        l_lisAioOrderUnit.clear();
                        
                        //1.5.5.2.4 add(注文単位オブジェクト[index] : Object)
                        //注文単位リストに新しい顧客IDの注文単位を追加する。 

                        //[引数] 
                        //注文単位オブジェクト[index]
                        l_lisAioOrderUnit.add(l_aioOrderUnit);
                    }
                    
                    //1.5.5.3 （*2-3）分岐フロー
                    //（*2-3）変数.口座ID == 注文単位[index].口座IDの場合
                    else if (l_strAccountId.equals(l_strAccountIdLoop))
                    {
                        log.debug("変数.口座ID == 注文単位[index].口座IDの場合");
                        
                        //1.5.5.3.1 add(注文単位オブジェクト[index] : Object)
                        //注文単位リストに新しい顧客IDの注文単位を追加する。 

                        //[引数] 
                        //注文単位オブジェクト[index]
                        l_lisAioOrderUnit.add(l_aioOrderUnit);
                    }
                }
                
                //1.5.6 clear( )
                //次の注文単位オブジェクト用（異なる部店IDの注文用）にクリアする。
                l_lisAioOrderUnit.clear();                
            }        
            catch (DataException l_ex)
            {
                log.error("DBへのアクセスに失敗しました: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //1.6 createResponse( )
        WEB3BackResponse l_response = l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }

    /**
     * (get出金連携発注日) <BR>
     * 発注日を取得する。 <BR>
     * <BR>
     * １） 取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－引数の内容より取引時間コンテキストのプロパティをセットする。 <BR> 
     * <BR>
     *  　@取引カレンダコンテキスト.部店コード = 引数の部店コード  <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     *      取引時間コンテキストをセットする。<BR>  
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。  <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ３） 取引時間管理.get発注日()をコール <BR>
     * <BR>
     * ４） 取引時間管理.get発注日()の戻り値の日付部分を”yyyyMMdd”形式の <BR>
     *          文字列で返却する。 <BR>
     * <BR>
     * @@param l_strBranchCode - 部店コード
     * @@return WEB3FXTransToFXConfirmResponse
     * @@roseuid 41C7B2080090
     */
    protected String getOnPaymentCooperationBizDate(String l_strBranchCode)       
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "getOnPaymentCooperationBizDate(String l_strBranchCode)";
        log.entering(l_strMethodName);
        
        //１） 取引カレンダコンテキストに内容をセットする。 
        //　@－引数の内容より取引時間コンテキストのプロパティをセットする。  

        //　@取引カレンダコンテキスト.部店コード = 引数の部店コード         
       
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        log.debug("l_context = " + l_context);

        l_context.setBranchCode(l_strBranchCode);        
        
        //　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。  
        //  設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
        
        //２）　@受付日時、日付ロールをセットする。  
        //　@－取引時間管理.setTimestamp()をコールする。        
        WEB3GentradeTradingTimeManagement.setTimestamp();
       
        //３） 取引時間管理.get発注日()をコール 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //４） 取引時間管理.get発注日()の戻り値の日付部分を”yyyyMMdd”形式の文字列で返却する。
        String l_strOnPaymentBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        
        log.debug("出金連携発注日 = " + l_strOnPaymentBizDate);
        log.exiting(l_strMethodName);
        return l_strOnPaymentBizDate;
    }
}@
