head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqFinTransactionManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式トランザクションマネージャ(WEB3FeqFinTransactionManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 呉艶飛(中訊) 新規作成
                 : 2005/07/26 王煜(中訊) レビュー
Revesion History : 2007/11/20 何文敏(中訊) 仕様変更 モデルNo.365
*/
package webbroker3.feq;

import java.math.BigDecimal;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqFinTransactionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式トランザクションマネージャ) <BR>
 * 外国株式トランザクションマネージャ<BR>
 * <BR>
 * @@ author 呉艶飛 <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqFinTransactionManager extends FeqFinTransactionManagerImpl 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqFinTransactionManager.class);
    
    /**
     * @@roseuid 42CE39E703B9
     */
    public WEB3FeqFinTransactionManager() 
    {
     
    }
    
    /**
     * (get受渡代金合計) <BR>
     * （getNetAmount）  <BR>
     * 指定注文に関連するトランザクションデータより、 <BR>
     * 注文に対応する合計受渡金額を算出する。  <BR>
     *  <BR>
     * １）　@トランザクション（取引勘定明細）テーブル検索  <BR>
     * 　@引数で指定した注文単位に該当する外株トランザクションを <BR>
     *   getトランザクション()にて取得する。  <BR>
     *  <BR>
     * [引数]  <BR>
     * 注文単位：　@引数の注文単位オブジェクト  <BR>
     *  <BR>
     * ２）　@合計金額計算  <BR>
     * 　@取得したすべての株式トランザクションの受渡代金を集計し、 <BR>
     * 計算結果を返却する。  <BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 注文単位
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 4292AFE90302
     */
    public double getNetAmount(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getNetAmount(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@トランザクション（取引勘定明細）テーブル検索
        //引数で指定した注文単位に該当する外株トランザクションを 
        //getトランザクション()にて取得する。
        List l_lstTransaction = this.getTransactions(l_feqOrderUnit);
        double  l_dblNetAmount = 0.0D;
        if (l_lstTransaction != null && !l_lstTransaction.isEmpty())
        {
            for (int i = 0; i < l_lstTransaction.size(); i++)
            {
                FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow)l_lstTransaction.get(i);
                //２）　@合計金額計算 
                l_dblNetAmount += l_transactionRow.getNetAmount();
            }
            
        }
        log.exiting(STR_METHOD_NAME);
        //計算結果を返却する。
        return l_dblNetAmount;
    }
    
    /**
     * (get受渡代金合計（外貨）) <BR>
     * （getNetAmountFc）  <BR>
     * 指定注文に関連するトランザクションデータより、 <BR>
     * 注文に対応する合計受渡金額（外貨）を算出する。  <BR>
     *  <BR>
     * １）　@トランザクション（取引勘定明細）テーブル検索  <BR>
     * 　@引数で指定した注文単位に該当する外株トランザクションを <BR>
     * 　@getトランザクション()にて取得する。  <BR>
     *  <BR>
     * [引数]  <BR>
     * 注文単位：　@引数の注文単位オブジェクト  <BR>
     *  <BR>
     * ２）　@合計金額計算  <BR>
     * 　@取得したすべての株式トランザクションの受渡代金（外貨）を集計し、 <BR>
     * 　@計算結果を返却する。  <BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 注文単位
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 4292BA3702A4
     */
    public double getNetAmountFc(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getNetAmountFc(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        //１）トランザクション（取引勘定明細）テーブル検索  
        //引数で指定した注文単位に該当する外株トランザクションを 
        //getトランザクション()にて取得する。
        List l_lstTransaction = this.getTransactions(l_feqOrderUnit);
        BigDecimal l_bdNetAmountFc = new BigDecimal("0");
        if (l_lstTransaction != null && !l_lstTransaction.isEmpty())
        {
            for (int i = 0; i < l_lstTransaction.size(); i++)
            {
                FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow)l_lstTransaction.get(i);
                //２）　@合計金額計算 
                l_bdNetAmountFc =
                    l_bdNetAmountFc.add(new BigDecimal(String.valueOf(l_transactionRow.getNetAmountFc())));
            }
        }
        log.exiting(STR_METHOD_NAME);
        //計算結果を返却する。
        return l_bdNetAmountFc.doubleValue();
    }
    
    /**
     * (getトランザクション) <BR>
     * （getTransactionsのオーバーロード）  <BR>
     *  <BR>
     * 引数で指定した注文単位に該当するトランザクションを取得する。  <BR>
     *  <BR>
     * １）　@トランザクションテーブル検索  <BR>
     * 　@以下の条件で外国株式トランザクション（取引勘定明細）を検索し、 <BR>
     * 　@取得結果を集合でする。<BR>
     *  <BR>
     * 　@[検索条件]  <BR>
     * 　@注文ID = 注文単位.注文ID  <BR>
     * 　@注文単位ID = 注文単位.注文単位ID  <BR>
     * 　@削除フラグ = FALSE  <BR>
     * @@param l_feqOrderUnit - (注文単位) <BR>
     * 注文単位
     * @@return List
     * @@throws WEB3BaseException 
     * @@roseuid 4292B03A01C9
     */
    public List getTransactions(FeqOrderUnit l_feqOrderUnit)throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getTransactions(FeqOrderUnit)";    
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        List l_lstreturnTransaction = null;
        
        //データ査詢
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            //１）　@トランザクションテーブル検索
            String l_strWhere = " order_id = ? and order_unit_id = ? and delete_flag = ? ";
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_feqOrderUnit.getOrderId());
            l_strBindValue[1] = new Long(l_feqOrderUnit.getOrderUnitId());
            l_strBindValue[2] = BooleanEnum.FALSE;
                        
            l_lstreturnTransaction = processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        log.exiting(STR_METHOD_NAME);        
        return l_lstreturnTransaction;
    }
    
    /**
     * (getトランザクション) <BR>
     * （getTransactionのオーバーロード）  <BR>
     *  <BR>
     * 引数で指定した約定に該当するトランザクションを取得する。  <BR>
     *  <BR>
     * １）　@トランザクションテーブル検索  <BR>
     * 　@以下の条件で外国株式トランザクション（取引勘定明細）を検索し、 <BR>
     * 　@取得行を返却する。  <BR>
     *  <BR>
     * 　@[検索条件]  <BR>
     * 　@注文ID = 約定.注文ID  <BR>
     * 　@注文単位ID = 約定.注文単位ID  <BR>
     * 　@約定ID = 約定.約定ID  <BR>
     *  <BR>
     * 　@該当行がない場合、<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01037<BR>
     * 　@該当行が２件以上ある場合例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01461<BR>
     * @@param l_feqOrderExecution - (約定)<BR>
     * 約定オブジェクト
     * @@return FeqFinTransactionParams
     * @@throws WEB3BaseException 
     * @@roseuid 4294367703A2
     */
    public FeqFinTransactionParams getTransaction(FeqOrderExecution l_feqOrderExecution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getTransactions(FeqOrderExecution)";    
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderExecution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        List l_lstTransaction = null;
        
        //データ査詢
        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            //１）　@トランザクションテーブル検索
            String l_strWhere = " order_id = ? and order_unit_id = ? and order_execution_id = ? ";
            FeqOrderExecutionRow l_executionRow = 
                (FeqOrderExecutionRow)l_feqOrderExecution.getDataSourceObject();
            Object[] l_strBindValue = new Object[3];
            l_strBindValue[0] = new Long(l_executionRow.getOrderId());
            l_strBindValue[1] = new Long(l_executionRow.getOrderUnitId());
            l_strBindValue[2] = new Long(l_executionRow.getOrderExecutionId());

                        
            l_lstTransaction = processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                l_strWhere, l_strBindValue);

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
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }        
        
        if (l_lstTransaction == null || l_lstTransaction.isEmpty())
        {
            String l_strMessage = "条件に該当するデータが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_lstTransaction.size() > 1)
        {
            String l_strMessage = "テーブルに重複する該当データが存在します。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01461,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMessage);
        }
        log.exiting(STR_METHOD_NAME);        
        return new FeqFinTransactionParams((FeqFinTransactionRow)l_lstTransaction.get(0));
    }

    /**
     * (get適用為替レート)<BR>
     * 引数で指定した注文単位に該当するトランザクション.適用為替レートを取得する。<BR>
     * 　@※複数件取得できる場合は、トランザクションList(0)の為替レートを取得する。<BR>
     * <BR>
     * １）　@トランザクションテーブル検索<BR>
     * 　@以下の条件で外国株式トランザクション（取引勘定明細）を検索し、取得結果を<BR>
     * 　@集合で取得する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@注文ID = 注文単位.注文ID<BR>
     * 　@注文単位ID = 注文単位.注文単位ID<BR>
     * <BR>
     * 　@※　@トランザクションが取得できない場合は、システムエラー<BR>
     * 　@”該当データなし”を返却する。<BR>
     * <BR>
     * ２）　@適用為替レート取得<BR>
     * 　@トランザクションList(0)．適用為替レートを返却する。<BR>
     * <BR>
     * @@param l_feqOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return Double
     * @@throws WEB3BaseException
     */
    public Double getFxRate(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "getFxRate(FeqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_feqOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １）　@トランザクションテーブル検索
        List l_lisTransactions = null;
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            // [検索条件]
            // 注文ID = 注文単位.注文ID
            // 注文単位ID = 注文単位.注文単位ID
            String l_strWhere = " order_id = ? and order_unit_id = ? ";
            Object[] l_bindValues = new Object[2];
            l_bindValues[0] = new Long(l_feqOrderUnit.getOrderId());
            l_bindValues[1] = new Long(l_feqOrderUnit.getOrderUnitId());

            l_lisTransactions = processor.doFindAllQuery(
                FeqFinTransactionRow.TYPE,
                l_strWhere,
                l_bindValues);
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
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // トランザクションが取得できない場合は、システムエラー”該当データなし”を返却する。
        if (l_lisTransactions.isEmpty())
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }

        // ２）　@適用為替レート取得
        // 　@トランザクションList(0)．適用為替レートを返却する。
        FeqFinTransactionRow l_feqFinTransactionRow =
            (FeqFinTransactionRow)l_lisTransactions.get(0);
        double l_dblFxRate = l_feqFinTransactionRow.getFxRate();

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblFxRate);
    }
}
@
