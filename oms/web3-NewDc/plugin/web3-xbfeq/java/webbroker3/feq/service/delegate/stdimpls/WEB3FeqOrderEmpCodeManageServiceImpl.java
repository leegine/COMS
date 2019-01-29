head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderEmpCodeManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式運用コード採番サービスImpl(WEB3FeqOrderEmpCodeManageServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
                 : 2007/01/09 SRA沢田 障害K00025（モデルNo.330）
Revesion History : 2009/08/03  武波(中訊) 仕様変更モデルNo.502
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.data.FeqOrderEmpNumberParams;
import webbroker3.feq.data.FeqOrderEmpNumberRow;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.feq.util.WEB3FeqStringUtility;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式運用コード採番サービスImpl) <BR>
 * 外国株式運用コード採番サービス実装クラス
 * 
 * @@author 艾興
 * @@version 1.0 
 */
public class WEB3FeqOrderEmpCodeManageServiceImpl implements WEB3FeqOrderEmpCodeManageService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderEmpCodeManageServiceImpl.class);
    /**
     * @@roseuid 42CE39F501F4
     */
    public WEB3FeqOrderEmpCodeManageServiceImpl() 
    {
     
    }
    
    /**
     * (get新規運用コード) <BR>
     * 運用コードを自動採番する。  <BR>
     *  <BR>
     * 運用コードのコード体系は以下の通り。  <BR>
     *  <BR>
     * 　@・１，２桁目（index=0～1）：<BR>
     * 　@外国株式運用コード取得サービス.getPREFIX（引数.証券会社コード）の戻り値<BR>
     * 　@・３桁目（index=2）：　@運用コード市場識別区分（市場マスタテーブル）  <BR>
     * 　@・４～７桁目（index=3～6）：　@ ４桁の通番 <BR>
     *  <BR>
     * １）外国株式運用コード採番TransactionCallbackクラスを生成し、<BR>
     * 　@　@生成したTransactionCallbackクラスでdoTransaction()を実行<BR>
     * 　@　@運用コードの新規採番、取得する<BR>
     *  <BR>
     * 　@　@コンストラクタの引数<BR>
     * 　@　@　@証券会社コード：　@　@　@　@引数.証券会社コード<BR>
     * 　@　@　@運用コード市場識別区分：引数.運用コード市場識別区分<BR>
     * 　@　@　@発注日：　@　@　@　@　@　@　@　@引数.発注日<BR>
     * 　@　@doTransaction()のトランザクション属性：TX_JOIN_EXISTING<BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strFeqOrderEmpDiv - (運用コード市場識別区分)
     * @@param l_datBizDate - (発注日)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42845BE000AC
     */
    public String getNewEmpCode(String l_strInstitutionCode, 
        String l_strFeqOrderEmpDiv, Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewEmpCode(String l_strInstitutionCode, ]" +
                "String l_strFeqOrderEmpDiv, Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);

        String l_strNewEmpCode;
        try
        {
            // １）外国株式運用コード採番TransactionCallbackクラスを生成し、
            // 　@　@生成したTransactionCallbackクラスでdoTransaction()を実行
            // 　@　@運用コードを取得する
            WEB3FeqOrderEmpCodeManageTransactionCallback l_transactionCallback =
                new WEB3FeqOrderEmpCodeManageTransactionCallback(
                    l_strInstitutionCode, l_strFeqOrderEmpDiv, l_datBizDate);
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_strNewEmpCode = (String) l_processor.doTransaction(
                QueryProcessor.TX_CREATE_NEW, l_transactionCallback);
           
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strNewEmpCode;
    }
    
    /**
     * (get新規運用コード) <BR>
     * 運用コードを自動採番する。  <BR>
     *  <BR>
     * this.get新規運用コード(String,String,Date)に委譲（delegate）する。 <BR>
     *  <BR>
     * [get新規運用コード()に指定する引数] <BR>
     * 証券会社コード：　@市場.getInstitutionCode() <BR>
     * 運用コード市場識別区分：　@市場.運用コード市場識別区分 <BR>
     * 発注日：　@発注日 <BR>
     * @@param l_market - (市場) <BR>
     * 市場オブジェクト
     * @@param l_datBizDate - (発注日)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 428467810296
     */
    public String getNewEmpCode(WEB3GentradeMarket l_market, Date l_datBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewEmpCode(WEB3GentradeMarket l_market, " +
                "Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);        
        
        MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
        String l_strInstitutionCode = l_marketRow.getInstitutionCode();
        String l_strNewEmpCode = 
            this.getNewEmpCode(l_strInstitutionCode, l_marketRow.getFeqOrderEmpDiv(), l_datBizDate);
        log.exiting(STR_METHOD_NAME);
        return l_strNewEmpCode;
    }

    /**
     * (外国株式運用コード採番TransactionCallback)<BR>
     * <BR>
     * トランザクション処理を実施する内部クラス<BR>
     * （トランザクション属性：TX_JOIN_EXISTING）<BR>
     */
    public class WEB3FeqOrderEmpCodeManageTransactionCallback implements TransactionCallback
    {
        /**
         * (証券会社コード)<BR>
         * 証券会社コード
         */
        private String institutionCode; 
        /**
         * (運用コード市場識別区分)<BR>
         * 運用コード市場識別区分
         */
        private String orderEmpDiv;
        /**
         * (発注日)<BR>
         * 発注日
         */
        private Date bizDate;

        /**
         * コンストラクタ。<BR>
         * <BR>
         * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
         * @@param l_strInstitutionCode
         * @@param l_strFeqOrderEmpDiv
         * @@param l_datBizDate
         */
        public WEB3FeqOrderEmpCodeManageTransactionCallback(
            String l_strInstitutionCode,
            String l_strFeqOrderEmpDiv,
            Date l_datBizDate
        ) 
        {
            this.institutionCode = l_strInstitutionCode; 
            this.orderEmpDiv = l_strFeqOrderEmpDiv;
            this.bizDate = l_datBizDate;
        }

        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * １）外国株式運用コードテーブルを以下の条件で検索する。<BR>
         * 　@　@この時、select for updateにて共有ロックをする<BR>
         * <BR>
         * 　@　@[条件]  <BR>
         * 　@　@外国株式運用コード.証券会社コード = 証券会社コード And <BR>
         * 　@　@外国株式運用コード.運用コード市場識別区分 =  運用コード市場識別区分 And <BR>
         * 　@　@外国株式運用コード.発注日 = 発注日 <BR>
         * <BR>
         * ２）該当データがない場合は、 <BR>
         * 　@　@以下の通りレコードを新規登録（insert）し、 <BR>
         * 　@　@運用コード最大値を返却する。  <BR>
         * <BR>
         * 　@　@外国株式運用コード.証券会社コード = 証券会社コード  <BR>
         * 　@　@外国株式運用コード.運用コード市場識別区分 = 運用コード市場識別区分  <BR>
         * 　@　@外国株式運用コード.運用コード（SEQ） = "0001"  <BR>
         * 　@　@外国株式運用コード.発注日 = 発注日 <BR>
         * 　@　@外国株式運用コード.作成日時 = TradingSystem.getSystemTimestamp()  <BR>
         * 　@　@外国株式運用コード.更新日時 = TradingSystem.getSystemTimestamp()  <BR>
         * <BR>
         * ３）該当データがある場合は、以下の通りレコードを更新（update）し、 <BR>
         * 　@　@運用コード最大値を返却する。  <BR>
         * <BR>
         * 　@　@外国株式運用コード.運用コード（SEQ） = （※１）  <BR>
         * 　@　@外国株式運用コード.更新日時 = TradingSystem.getSystemTimestamp()  <BR>
         * <BR>
         * 　@　@（※１）　@運用コード（SEQ）の計算  <BR>
         * 　@　@○既存値と同一発注日の場合  <BR>
         * 　@　@　@引数の発注日と、該当データ.発注日が同じ場合、  <BR>
         * 　@　@　@既存値の通番＋１の数字を4桁の文字列に編集する。  <BR>
         * <BR>
         * ４）採番した値をreturnする<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         */
        public Object process() throws DataNetworkException, DataQueryException
        {
            final String STR_METHOD_NAME = " process() ";
            log.entering(STR_METHOD_NAME);

            WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
                (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                        WEB3FeqOrderEmpCodeGettingService.class);
            String l_strNewEmpCode = "";
            try
            {
                l_strNewEmpCode =
                    l_feqOrderEmpCodeGettingService.getPREFIX(this.institutionCode) + this.orderEmpDiv;
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ? and feq_order_emp_div = ? and biz_date = ?";
            String l_strCondition = " for update ";
            Object[] l_objWhereValue = new Object[3];
            l_objWhereValue[0] = this.institutionCode;
            l_objWhereValue[1] = this.orderEmpDiv;
            l_objWhereValue[2] = this.bizDate;
            List l_lstFeqOrderEmpNumber = l_processor.doFindAllQuery(
                FeqOrderEmpNumberRow.TYPE,
                l_strWhere,
                l_strCondition,
                l_objWhereValue
            );

            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            // ２）該当データがない場合は、 
            // 　@　@以下の通りレコードを新規登録（insert）し、 
            // 　@　@運用コード最大値を返却する。  
            if ((l_lstFeqOrderEmpNumber == null) || (l_lstFeqOrderEmpNumber.size() == 0))
            { 
                FeqOrderEmpNumberParams l_feqOrderEmpNumberParams = new FeqOrderEmpNumberParams();
                l_feqOrderEmpNumberParams.setInstitutionCode(this.institutionCode);
                l_feqOrderEmpNumberParams.setFeqOrderEmpDiv(this.orderEmpDiv);
                l_feqOrderEmpNumberParams.setLatestOrderEmpNumber("0001");
                l_feqOrderEmpNumberParams.setBizDate(this.bizDate);
                l_feqOrderEmpNumberParams.setCreatedTimestamp(l_timeStamp);
                l_feqOrderEmpNumberParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doInsertQuery(l_feqOrderEmpNumberParams);
                l_strNewEmpCode = l_strNewEmpCode + "0001";
            }
            // ３）該当データがある場合は、以下の通りレコードを更新（update）し、 
            // 　@　@運用コード最大値を返却する。  
            else
            {
                FeqOrderEmpNumberParams l_feqOrderEmpNumberParams = 
                    new FeqOrderEmpNumberParams((FeqOrderEmpNumberRow)l_lstFeqOrderEmpNumber.get(0));
                    
                String str_LatestOrderEmpNumber = WEB3FeqStringUtility.addForString(l_feqOrderEmpNumberParams.getLatestOrderEmpNumber(),1);      
                l_feqOrderEmpNumberParams.setLatestOrderEmpNumber(str_LatestOrderEmpNumber);
                
                l_feqOrderEmpNumberParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doUpdateQuery(l_feqOrderEmpNumberParams);
                l_strNewEmpCode = l_strNewEmpCode + l_feqOrderEmpNumberParams.getLatestOrderEmpNumber();
            }                    
            log.exiting(STR_METHOD_NAME);
            return l_strNewEmpCode;
        }
    }
}
@
