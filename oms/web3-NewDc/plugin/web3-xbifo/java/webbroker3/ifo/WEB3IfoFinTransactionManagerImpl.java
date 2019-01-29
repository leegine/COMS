head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoFinTransactionManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPトランザクションマネージャ(WEB3IfoTransactionManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 張朋波(中訊) 新規作成
              001: 2004/07/23  王暁傑 (中訊)  getTransactionsを修正
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoFinTransactionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OPトランザクションマネージャ)<BR>
 * 先物OPトランザクションマネージャクラス<BR>
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3IfoFinTransactionManagerImpl extends IfoFinTransactionManagerImpl 
{
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoFinTransactionManagerImpl.class);

    /**
     * コンストラクタ。<BR>
     * <BR>
     * @@roseuid 40C0750B01E4
     */
    public WEB3IfoFinTransactionManagerImpl() 
    {
        super();
        
        // --- Test Log
        log.debug("------- >>>>>>>> WEB3IfoPositionManagerImpl Has Intializtion Successed !!!");
    }
    
    /**
     * (getトランザクション)<BR>
     * <BR>
     * （getTransactionsのオーバーロード）<BR>
     * <BR>
     * 引数で指定した注文単位に該当する先物OPトランザクションを取得する。<BR>
     * <BR>
     * １）　@株式トランザクションテーブル検索<BR>
     * 　@以下の条件で先物OPトランザクション（取引勘定）テーブルを検索し、<BR>
     * 取得結果を集合で返却する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 注文単位.注文ID<BR>
     * 注文単位.注文単位ID<BR>
     * 削除フラグ = FALSE
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * 
     * @@return List
     * @@roseuid 40A2DDCE0226
     */
    public List getTransactions(OrderUnit l_orderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";    
        log.entering(STR_METHOD_NAME);
        
        
        // --- Test Log
        log.debug("Input Parm: 注文単位オブジェクト l_orderUnit = " + l_orderUnit);

        List l_returnList = null;
        
        //データ査詢
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            
            String l_strWhere = " order_id = ? and order_unit_id = ? and delete_flag = ? ";
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_orderUnit.getOrderId()).toString();
            l_strBindValue[1] = new Long(l_orderUnit.getOrderUnitId()).toString();
            l_strBindValue[2] = BooleanEnum.FALSE;
                        
            l_returnList = processor.doFindAllQuery(IfoFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

        } 
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "先物OPトランザクション（取引勘定）テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            String l_strMessage = " 先物OPトランザクション（取引勘定）テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        log.exiting(STR_METHOD_NAME);
        
        return l_returnList;
    }
    
    /**
     * (getトランザクション)<BR>
     * <BR>
     * （getTransactionsのオーバーロード）<BR>
     * <BR>
     * 指定した建玉ID、トランザクションカテゴリ、発生日付に該当するトランザクション(取・
     * 勘定明細)の一覧を取得する。<BR>
     * <BR>
     * (1)トランザクションテーブル検索<BR>
     * 以下の条件でトランザクションテーブルを検索する<BR>
     * <BR>
     * [検索条件]<BR>
     * 建玉ID = パラメータ.建玉ID<BR>
     * トランザクションカテゴリ = パラメータ.トランザクションカテゴリ<BR>
     * トランザクション発生日時 = パラメータ.発生日付と同じ日付<BR>
     * 削除フラグ = FALSE 
     * <BR>
     * (2)(1)の取得結果の一覧を返却<BR>
     * ※データが存在しない場合にはNULLを返却す<BR>
     * @@param l_lngContractID - 建玉ID<BR>
     * @@param l_intTransactionCategory - トランザクションカテゴリ<BR>
     * <BR>
     * 0：その他<BR>
     * 10：入出金<BR>
     * 20：現物取引<BR>
     * 30：新規建取引<BR>
     * 40：返済取引<BR>
     * 60：現引・現渡取引<BR>
     * 70：諸費用関連<BR>
     * 80：入出庫<BR>
     * @@param l_datOccurDate - (発生日付)<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40AAB60B0292
     */
    public List getTransactions(long l_lngContractID, 
        FinTransactionCateg l_transactionCategory, Date l_datOccurDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";
        log.entering(STR_METHOD_NAME);
        
        // --- Test Log
        log.debug("Input Parm: 建玉ID l_lngContractID = " + l_lngContractID);
        log.debug("Input Parm: トランザクションカテゴリ l_intTransactionCategory = " + l_transactionCategory);
        log.debug("Input Parm: 発生日付 l_datOccurDate = " + l_datOccurDate);

        List l_returnList = null;
        
        //データ査詢
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
                        
            String l_strWhere = " contract_id = ? and fin_transaction_categ = ?"
                + " and to_char(fin_transaction_timestamp,'yyyyMMdd') = ? and delete_flag = ?";
                            
            SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strOccurDate = l_format.format(l_datOccurDate);
            
            Object[] l_objBindValue = new Object[4];
            l_objBindValue[0] = "" + l_lngContractID;
            l_objBindValue[1] =  l_transactionCategory;
            l_objBindValue[2] = l_strOccurDate;
            l_objBindValue[3] = BooleanEnum.FALSE;
                        
            l_returnList = processor.doFindAllQuery(
                IfoFinTransactionRow.TYPE,
                l_strWhere, 
                l_objBindValue);
               
        }
        catch(DataQueryException l_ex)
        {
            String l_strMessage = "トランザクションテーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch(DataNetworkException l_ex)        
        {
            String l_strMessage = "トランザクションテーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        

        log.exiting(STR_METHOD_NAME);
        return l_returnList;
    }
    
    /**
     * (getNetAmount)<BR>
     * (get受渡金額合計)<BR>
     * 指定注文に関連するトランザクションデータより、注文に対応する合計受渡金額を算出する。<BR>
     * <BR>
     * １）　@株式トランザクションテーブル検索<BR>
     * 　@引数で指定した注文単位に該当する株式トランザクションをgetトランザクション()にて取得する。<BR>
     * <BR>
     * [引数]<BR>
     * 注文単位：　@引数の注文単位オブジェクト<BR>
     * <BR>
     * ２）　@合計金額計算<BR>
     * 　@取得したすべての株式トランザクションの受渡代金を集計し、計算結果を返却する。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * 
     * @@return double
     * @@roseuid 40A2DDCE0254
     */
    public double getNetAmount(OrderUnit l_orderUnit) 
    {
        final String STR_METHOD_NAME = "getNetAmount";
        log.entering(STR_METHOD_NAME);
        
        double l_dblAmout = 0;
        BigDecimal l_bdAmout = new BigDecimal("0");
        //getトランザクション

        List l_lisTransactions = null;
        try
        {
            
            l_lisTransactions = this.getTransactions(l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error In getトランザクション: ", l_ex);
        }
        //合計金額計算               
        
        if (l_lisTransactions != null) 
        {
            IfoFinTransactionRow l_transactionRow = null;
            int size = l_lisTransactions.size();

            for (int i = 0; i < size; i++)
            {
                //受渡金額を集計し、計算結果
                l_transactionRow = (IfoFinTransactionRow)l_lisTransactions.get(i);

                BigDecimal l_bdNetAmout = new BigDecimal(l_transactionRow.getNetAmount() + "");
                l_bdAmout = l_bdAmout.add(l_bdNetAmout);
                l_dblAmout = l_bdAmout.doubleValue();
            }
        } 
        log.debug("------ >>>>> 合計金額計算 = " + l_dblAmout);

        log.exiting(STR_METHOD_NAME);
        
        return l_dblAmout;
    }
    
    /**
     *（getTransactionsのオーバーロード）<BR>
     *   指定した注文単位ID、建玉IDに該当するトランザクション(取引勘定明細)の一覧を取得する。<BR>
     *(1)トランザクションテーブル検索<BR>
     *以下の条件でトランザクションテーブルを検索する<BR>
     *  [検索条件]<BR>
     *  注文単位ID = パラメータ.注文単位ID<BR>
     *  建玉ID = パラメータ.建玉ID<BR>
     *  削除フラグ = FALSE<BR>
     *(2)(1)の取得結果の一覧を返却する。<BR>
     * @@param l_orderUnitId
     * @@param l_contractId
     * @@return
     * @@throws WEB3BaseException
     */
    public List getTransactions(long l_orderUnitId ,long l_contractId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactions";    
        log.entering(STR_METHOD_NAME);
               
        List l_returnList = null;
        
        //データ査詢
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();

            String l_strWhere = " order_unit_id = ? and contract_id = ? and delete_flag = ? ";
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_orderUnitId);
            l_strBindValue[1] = new Long(l_contractId);
            l_strBindValue[2] = BooleanEnum.FALSE;            
            l_returnList = processor.doFindAllQuery(IfoFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);
        } 
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "先物OPトランザクション（取引勘定）テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);            
        } 
        catch (DataNetworkException l_ex)        
        {
            String l_strMessage = " 先物OPトランザクション（取引勘定）テーブルを検索 error";
            log.error(l_strMessage, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);           
        }
        
        log.debug(" 先物OPトランザクション（取引勘定）テーブルを検索 l_returnList = " + l_returnList);
        log.exiting(STR_METHOD_NAME);
        
        return l_returnList;
    }
}
@
