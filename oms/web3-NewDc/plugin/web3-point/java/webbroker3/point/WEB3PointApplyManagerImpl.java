head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント申込マネージャImpl(WEB3PointApplyManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 張学剛 (中訊) 新規作成
*/
package webbroker3.point;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApplyAcceptDivDef;
import webbroker3.common.define.WEB3ApplyCancelDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.data.PointAdjustParams;
import webbroker3.point.data.PointAdjustRow;
import webbroker3.point.data.PointApplyDao;
import webbroker3.point.data.PointApplyParams;
import webbroker3.point.data.PointApplyRow;
import webbroker3.point.data.PointTermDao;
import webbroker3.point.data.PointTermRow;
import webbroker3.point.data.PointTotalDao;
import webbroker3.point.data.PointTotalRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ポイント申込マネージャImpl)<BR>
 * ポイント申込マネージャ実装クラス<BR>
 * 
 * @@author 張学剛
 * @@version 1.0 
 */
public class WEB3PointApplyManagerImpl implements WEB3PointApplyManager 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointApplyManagerImpl.class);
    
    /**
     * (get申込)<BR>
     * 申込オブジェクトを取得する。<BR>
     * <BR>
     * 引数.申込IDに該当するポイント申込オブジェクトを取得する。<BR>
     * @@param l_lngApplyId - (申込ID)<BR>
     * 申込ID<BR>
     * 
     * @@return webbroker3.point.WEB3PointApply
     * @@roseuid 419C8D8501EB
     */
    public WEB3PointApply getApply(long l_lngApplyId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getApply(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            PointApplyRow l_row = PointApplyDao.findRowByApplyId(l_lngApplyId);//DataNetworkException,DataQueryException
            
            if (l_row == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            PointApplyParams l_pointApplyParams = new PointApplyParams(l_row);
            
            WEB3PointApply l_pointApply = new WEB3PointApply(l_pointApplyParams);
            
            log.exiting(STR_METHOD_NAME);
            
            return l_pointApply;
        
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get申込)<BR>
     * 該当顧客の過去7日の申込データを取得する。<BR>
     * <BR>
     * １）以下の条件でポイント申込テーブルからレコードを申込日時の昇順で取得する。<BR>
     * <BR>
     * [取得条件]<BR>
     *    証券会社コード = 引数.証券会社コード and<BR>
     *    部店コード = 引数.部店コード and<BR>
     *    口座コード = 引数.顧客コード and<BR>
     *    ( (申込受付日時 = null and 申込取消日時 = null) or<BR>
     *       ( 申込受付日時 = null and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
     *       ( 申込取消日時 = null and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
     *       ( 申込受付日時 > 申込取消日時 and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
     *       ( 申込受付日時 < 申込取消日時 and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) )<BR>
     * <BR>
     * ２）取得したレコードを配列にして、返却する。<BR>
     *    ※検索の結果0件だった場合は、要素0の配列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return webbroker3.point.data.PointApplyParams
     * @@roseuid 419DC5E10142
     */
    public PointApplyParams[] getApply(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getApply(String, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {      
            List l_lisRecords = null;
            
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
            
            Date l_date = WEB3DateUtility.addDay(l_tsSystemTime, -7);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;
            
            //[取得条件]<BR>
            //証券会社コード = 引数.証券会社コード and<BR>
            //部店コード = 引数.部店コード and<BR>
            //口座コード = 引数.顧客コード and<BR>
            //( (申込受付日時 = null and 申込取消日時 = null) or<BR>
            //( 申込受付日時 = null and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
            //( 申込取消日時 = null and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
            //( 申込受付日時 > 申込取消日時 and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
            //( 申込受付日時 < 申込取消日時 and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) )<BR>

            l_sbWhere = "institution_code = ? and branch_code = ? and account_code = ? and " + 
                " ((apply_accept_timestamp IS NULL  and apply_cancel_timestamp IS NULL) or " +
                " (apply_accept_timestamp IS NULL and apply_cancel_timestamp >= ?) or " +
                " (apply_cancel_timestamp IS NULL and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp > apply_cancel_timestamp and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp < apply_cancel_timestamp and apply_cancel_timestamp >= ?))";               
                
            l_objWhere = new Object[]{l_strInstitutionCode, l_strBranchCode, 
                l_strAccountCode, l_date, l_date, l_date, l_date};          
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_sbWhere,
                " apply_timestamp asc ", 
                "",
                l_objWhere);//DataQueryException, DataNetworkException
                
            //取得したレコードを配列にして、返却する。
            //検索の結果0件だった場合は、要素0の配列を返却する。            
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            } 
            
            PointApplyParams[] l_params = new PointApplyParams[l_intRecords];
            
            for (int i = 0; i < l_intRecords; i++)
            {
                l_params[i] = new PointApplyParams((PointApplyRow)l_lisRecords.get(i));
            }
            
            return l_params;      
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get申込件数)<BR>
     * 引数の証券会社コード、景品番号に該当する景品のポイント申込をしている件数を取得する。<BR>
     * <BR>
     * １）以下の条件で、ポイント申込テーブルを検索する。<BR>
     * <BR>
     * 検索条件：<BR>
     *    証券会社コード = 引数.証券会社コード and<BR>
     *    景品番号 = 引数.景品番号 and<BR>
     *    ( (申込受付日時 = null and 申込取消日時 = null) or<BR>
     *       ( 申込受付日時 = null and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
     *       ( 申込取消日時 = null and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
     *       ( 申込受付日時 > 申込取消日時 and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
     *       ( 申込受付日時 < 申込取消日時 and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) )<BR>
     * <BR>
     * ２）検索結果のレコード数を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@return long
     * @@roseuid 41934895035A
     */
    public long getApplyNumber(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getApplyNumber(String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {      
            List l_lisRecords = null;
            
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
            
            Date l_date = WEB3DateUtility.addDay(l_tsSystemTime, -7);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;
            
            //検索条件：<BR>
            // 証券会社コード = 引数.証券会社コード and<BR>
            //  景品番号 = 引数.景品番号 and<BR>
            // ( (申込受付日時 = null and 申込取消日時 = null) or<BR>
            // ( 申込受付日時 = null and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
            // ( 申込取消日時 = null and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
            // ( 申込受付日時 > 申込取消日時 and 申込受付日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) or<BR>
            // ( 申込受付日時 < 申込取消日時 and 申込取消日時 >= <BR>(TradingSystem.getSystemTimestamp() - 7日) ) )<BR>

            l_sbWhere = "institution_code = ? and premium_no = ? and " + 
                " ((apply_accept_timestamp IS NULL and apply_cancel_timestamp IS NULL) or " +
                " (apply_accept_timestamp IS NULL and apply_cancel_timestamp >= ?) or " +
                " (apply_cancel_timestamp IS NULL and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp > apply_cancel_timestamp and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp < apply_cancel_timestamp and apply_cancel_timestamp >= ?))";               
                
            l_objWhere = new Object[]{l_strInstitutionCode, l_strPremiumNo, 
                l_date, l_date, l_date, l_date};        
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_sbWhere,
                l_objWhere);//DataQueryException, DataNetworkException
                
            //２）検索結果のレコード数を返却する。
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_intRecords;      
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get利用可能ポイント)<BR>
     * 利用可能ポイントを取得する。<BR>
     * <BR>
     * this.get利用可能ポイント()をコールする。<BR>
     * <BR>
     * [get利用可能ポイントにセットする引数]<BR>
     * 証券会社コード： 引数.証券会社コード<BR>
     * 部店コード： 引数.部店コード<BR>
     * 顧客コード： 引数.顧客コード<BR>
     * 取消解除申込： null<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return long
     * @@roseuid 41AF149B0196
     */
    public long getUsePossiblePoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getUsePossiblePoint(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngPossiblePoint = this.getUsePossiblePoint(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode, null);
        
        log.exiting(STR_METHOD_NAME);
            
        return l_lngPossiblePoint;
    }
    
    /**
     * (get利用可能ポイント)<BR>
     * 利用可能ポイントを取得する。<BR>
     * （取消解除申込対応）<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_cancelReleaseApply - (取消解除申込)<BR>
     * 取消解除予定の申込データ<BR>
     * 
     * @@return long
     * @@roseuid 419465ED01C5
     */
    protected long getUsePossiblePoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointApply l_cancelReleaseApply) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUsePossiblePoint(String, String, String, WEB3PointApply)";
        log.entering(STR_METHOD_NAME);
        
        //①@ 有効期限月を取得する。
        //get有効期限月()をコールする。
        //[get有効期限月()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //対象月： 現在時刻から取得した年月（YYYYMM）        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();       
        String l_strSystemDate = WEB3DateUtility.formatDate(l_tsSystemTime, "yyyyMM");        
        String l_strValidYearMonth = this.getValidTermMon(l_strInstitutionCode, l_strSystemDate);
        
        if (l_strValidYearMonth == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("now Year Mon:" + l_strSystemDate);
        log.debug("有効期限月:" + l_strValidYearMonth);
        
        Date l_date = WEB3DateUtility.getDate(l_strValidYearMonth, "yyyyMM");
        Date l_systemDate = WEB3DateUtility.getDate(l_strSystemDate, "yyyyMM");
        
        String l_strValidYear = WEB3DateUtility.formatDate(l_date, "yyyy"); 
        String l_strValidMonth = WEB3DateUtility.formatDate(l_date, "MM"); 
        
        String l_strSystemYear = WEB3DateUtility.formatDate(l_systemDate, "yyyy"); 
        String l_strSystemMonth = WEB3DateUtility.formatDate(l_systemDate, "MM"); 
            
        //②   有効期限月から今月までの残ポイントの合計値を算出する。
        //有効期限月から今月までのループ処理を行う。
        //1)  該当年月の残ポイントを取得する。
        //get残ポイント()をコールする。
        //[get残ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： ループの要素の年月（YYYYMM）
        int l_intYear = Integer.parseInt(l_strSystemYear) - Integer.parseInt(l_strValidYear);
        int l_intMonth = Integer.parseInt(l_strSystemMonth) - Integer.parseInt(l_strValidMonth);
        
        int l_intSum = l_intYear * 12 + l_intMonth + 1;
        
        long l_lngRemainedPointSum = 0;
        
        String l_strDateByDate = l_strValidYearMonth;
        for (int i = 0; i < l_intSum; i++)
        {
            log.debug("sum:" + l_intSum);
            log.debug("月" + l_strDateByDate);
            
            long l_lngRemainedPoint = this.getRemainedPoint(l_strInstitutionCode, 
                l_strBranchCode, l_strAccountCode, l_strDateByDate);
            
            //2)  取得したポイントを集計する。
            //集計結果を(A)とする。
            l_lngRemainedPointSum = l_lngRemainedPointSum + l_lngRemainedPoint;
            log.debug("集計結果:" + l_lngRemainedPointSum);
            
            Date l_dateCal =  WEB3DateUtility.getDate(l_strDateByDate, "yyyyMM");
            if (l_dateCal == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dateCal);
            
            l_cal.add(Calendar.MONTH, 1);
            
            Date l_nextDate = new Date(l_cal.getTimeInMillis());
            
            l_strDateByDate = WEB3DateUtility.formatDate(l_nextDate, "yyyyMM");
        }       
        
        //③   有効月からの引落未済ポイントを取得する。
        //get引落未済ポイント()をコールする。
        //[get引落未済ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //取消解除申込： 引数.取消解除申込
        //※戻り値を(B)とする。
        long l_lngNotWithdrawnPoint = this.getNotWithdrawnPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_cancelReleaseApply); 
        log.debug("有効月からの引落未済ポイント" + l_lngNotWithdrawnPoint);       

        //④   利用可能ポイントを算出し、返却する。
        //利用可能ポイント = (A) - (B)        
        long l_lngPossiblePoint = l_lngRemainedPointSum - l_lngNotWithdrawnPoint;
        log.debug("l_lngRemainedPointSum - l_lngNotWithdrawnPoint");
        log.debug("利用可能ポイント:" + l_lngRemainedPointSum + " - " + l_lngNotWithdrawnPoint + " = " + l_lngPossiblePoint);

        log.exiting(STR_METHOD_NAME);
        return l_lngPossiblePoint;
    }
    
    /**
     * (get失効注意ポイント)<BR>
     * 失効注意ポイントを取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return long
     * @@roseuid 419D76BC0326
     */
    public long getExpirationAttentionPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExpirationAttentionPoint(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コードを受け取り、算出した失効注意ポイントを返却する。

        //①@   有効期限月を取得する。
        //get有効期限月()をコールする。
        //[get有効期限月()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //対象月： 現在時刻から取得した年月（YYYYMM）
        //※戻り値を(A)とする。
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();       
        String l_strSystemDate = WEB3DateUtility.formatDate(l_tsSystemTime, "yyyyMM"); 
        
        String l_strMonth = this.getValidTermMon(l_strInstitutionCode, l_strSystemDate);
        
        if (l_strMonth == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("有効期限月:" + l_strMonth);
        
        //②   有効期限月の残ポイントを算出する。
        //get残ポイント()をコールする。
        //[get残ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： (A)
        //※戻り値を(B)とする。
        long l_lngRemainedPoint = this.getRemainedPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strMonth);
        log.debug("有効期限月の残ポイント:" + l_lngRemainedPoint);
        
        //③   残ポイントの判定
        //・   (B) ≦ 0 の場合、失効注意ポイント = 0 として返却する。
        //・   (B) ＞ 0 の場合、以降を処理する。
        
        if (l_lngRemainedPoint <= 0)
        {
            log.debug("(B) ≦ 0 ");
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        log.debug("(B) ＞ 0");

        //④   有効月からの引落未済ポイントを取得する。
        //get引落未済ポイント()をコールする。
        //[get引落未済ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //取消解除申込： null
        //※戻り値を(C)とする。
        long l_lngNotWithdrawnPoint = this.getNotWithdrawnPoint(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode, null);
            
        log.debug("有効月からの引落未済ポイント:" + l_lngNotWithdrawnPoint);
        
        //⑤   失効注意ポイントを確定し、返却する。
        //失効注意ポイント = (B) - (C)
        //※失効注意ポイント < 0 の場合、0を返却する。
        long l_lngvoidPoint = l_lngRemainedPoint - l_lngNotWithdrawnPoint;
        
        if (l_lngvoidPoint < 0)
        {
            log.debug("失効注意ポイント < 0 ");
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        log.debug("失効注意ポイント = (B) - (C):" + l_lngRemainedPoint + " - " + l_lngNotWithdrawnPoint);

        log.exiting(STR_METHOD_NAME);
        return l_lngvoidPoint;
    }
    
    /**
     * (get申込ポイント)<BR>
     * 引数.対象月に申込を行ったポイントの合計を取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 処理対象となる年月（YYYYMM）<BR>
     * 
     * @@return long
     * @@roseuid 41AEE81E0226
     */
    public long getApplyPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = " getApplyPoint(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コード、
        //対象月（YYYYMM）を受け取り、算出した対象月に申込を行ったポイントの合計を返却する。

        //①@   ポイント申込テーブルから、以下の条件でレコードを取得する。
        //[取得条件]
        //証券会社コード ＝ 引数.証券会社コード and
        //部店コード ＝ 引数.部店コード and
        //口座コード ＝ 引数.顧客コード and
        //申込取消区分 ＝ 0（取消未済） and
        //申込日時 ≧ (A) and
        //申込日時 ＜ (B)
        //※(A)： 引数.対象月の1日の0:00
        //  (B)： 引数.対象月の翌月の1日の0:00
        try
        {      
            log.debug("対象月:" + l_strObjectMonth); 
            
            List l_lisRecords = null;
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;
            
            Date l_date = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            if (l_date == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // 引数.対象月の1日の0:00
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_date);
            
            l_cal.add(Calendar.MONTH, 1);
            
            //引数.対象月の翌月の1日の0:00
            String l_strNextDate = WEB3DateUtility.formatDate(new Date(l_cal.getTimeInMillis()), "yyyyMM");
            Date l_nextDate = WEB3DateUtility.getDate(l_strNextDate, "yyyyMM");            
            
            l_sbWhere = "institution_code = ? and branch_code = ? and " + 
                " account_code = ? and apply_cancel_div = ? and " +
                " apply_timestamp >= ? and apply_timestamp < ? " ;
               
            l_objWhere = new Object[]{l_strInstitutionCode, l_strBranchCode, 
                l_strAccountCode, WEB3ApplyCancelDivDef.NOT_CANCELED, l_date, l_nextDate};        
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_sbWhere,
                l_objWhere);//DataQueryException, DataNetworkException
                
            //②   取得したレコードの使用ポイントを集計し、返却する。
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            }      
            
            int l_intPoint = 0;
            for (int i = 0; i <l_intRecords; i++)
            {
                PointApplyRow l_row = (PointApplyRow)l_lisRecords.get(i);
                log.debug("No" + i + ":" + l_row.getUsedPoint());
                l_intPoint = l_intPoint + l_row.getUsedPoint();
            }
            
            log.debug("totle:" + l_intPoint);     
       
            log.exiting(STR_METHOD_NAME);
            return l_intPoint;      
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get残ポイント)<BR>
     * 対象月の残ポイントを取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 処理対象となる年月（YYYYMM）<BR>
     * 
     * @@return long
     * @@roseuid 41AEFCBA034B
     */
    protected long getRemainedPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRemainedPoint(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コード、対象月（YYYYMM）を受け取り、算出した対象月の残ポイントを返却する。
        
        try
        {   
            log.debug("対象月:" + l_strObjectMonth);
            
            //①@   以下の条件で、対象月のポイント合計テーブルのレコードを取得する。
            //[取得条件]
            //証券会社コード ＝ 引数.証券会社コード
            //部店コード ＝ 引数.部店コード
            //口座コード ＝ 引数.顧客コード
            //年月 ＝ 引数.対象月 
            PointTotalRow l_row = PointTotalDao.findRowByInstitutionCodeBranchCodeAccountCodePeriod(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_strObjectMonth);//DataQueryException, DataNetworkException
                
            //②   対象月の未集計調整ポイントを取得する。
            //get未集計調整ポイント()をコールする。
            //[get未集計調整ポイント()にセットする引数]
            //証券会社コード： 引数.証券会社コード
            //部店コード： 引数.部店コード
            //顧客コード： 引数.顧客コード
            //対象月： 引数.対象月
            //※戻り値を(A)とする。
            long l_lngNotTotalAdjustPoint = this.getNotTotalAdjustPoint(l_strInstitutionCode, 
                l_strBranchCode, l_strAccountCode, l_strObjectMonth);
            log.debug("対象月の未集計調整ポイント:" + l_lngNotTotalAdjustPoint);
        
            //③   残ポイントを算出する。
            //1)  合計レコードが取得できなかった場合
            //   (A) ＞ 0 の場合、残ポイント ＝ (A)
            //   (A) ≦ 0 の場合、残ポイント ＝ 0
            //2)  合計レコードが取得できた場合
            //調整ポイント合計 ＝ レコード.合計調整ポイント ＋ (A) とすると、
            //   調整ポイント合計 ＞ 0 の場合
            //残ポイント ＝ レコード.合計獲得ポイント ＋ 調整ポイント合計
            //－ レコード.引落確定申込ポイント － レコード.引落確定調整ポイント
            //   調整ポイント合計 ≦ 0 の場合
            //残ポイント ＝ レコード.合計獲得ポイント － レコード.引落確定申込ポイント
            //－ レコード.引落確定調整ポイント
        
            //調整ポイント合計
            long l_lngTotalPoint = 0; 
        
            //残ポイント
            long l_lngRemainedPoint = 0;
        
            //1)  合計レコードが取得できなかった場合
            if (l_row == null)
            {
                if (l_lngNotTotalAdjustPoint > 0)
                {
                    l_lngRemainedPoint = l_lngNotTotalAdjustPoint;                
                }
                else
                {
                    l_lngRemainedPoint = 0;
                }
            }
            //2)  合計レコードが取得できた場合
            else
            {                   
                l_lngTotalPoint = l_row.getTotalAdjustPoint() + l_lngNotTotalAdjustPoint;
                log.debug("l_row.getTotalAdjustPoint() + l_lngNotTotalAdjustPoint:");
                log.debug(l_row.getTotalAdjustPoint() + " + " + l_lngNotTotalAdjustPoint);
                log.debug("l_lngTotalPoint:" + l_lngTotalPoint);
                if (l_lngTotalPoint > 0)
                {
                    log.debug("l_lngTotalPoint > 0");
                    l_lngRemainedPoint = l_row.getTotalGetPoint() + l_lngTotalPoint - 
                        l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint();
                    log.debug("l_row.getTotalGetPoint() + l_lngTotalPoint - " +
                        "l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint()");
                    log.debug(l_row.getTotalGetPoint() + " + " + l_lngTotalPoint + "- " +
                        l_row.getWithdrawnApplyPoint() + " - " + l_row.getWithdrawnAdjustPoint());
                    log.debug("合計レコード:" + l_lngRemainedPoint);
                }
                else
                {
                    log.debug("l_lngTotalPoint < 0");
                    l_lngRemainedPoint = l_row.getTotalGetPoint() - 
                        l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint();
                    log.debug("l_row.getTotalGetPoint() - " +
                        "l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint()");
                    log.debug(l_row.getTotalGetPoint() + "- " +
                        l_row.getWithdrawnApplyPoint() + " - " + l_row.getWithdrawnAdjustPoint());
                    log.debug("合計レコード:" + l_lngRemainedPoint);
                }
            }

            //④ 算出した残ポイントを返却する。
            log.exiting(STR_METHOD_NAME);
            
            return l_lngRemainedPoint;    
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (get引落繰越ポイント)<BR>
     * 対象月から見た失効月の引落未済のポイントの合計を取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 処理対象となる年月（YYYYMM）<BR>
     * 
     * @@param l_cancelReleaseApply - (取消解除申込)<BR>
     * 取消解除予定の申込データ<BR>
     * 
     * @@param l_lngPreMonCarryoverPoint - (前月繰越ポイント)<BR>
     * 前月繰越ポイント<BR>
     * 
     * @@return long
     * @@roseuid 41AF0551003E
     */
    protected long getWithdrawnCarryOverPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth, WEB3PointApply l_cancelReleaseApply, long l_lngPreMonCarryoverPoint) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getWithdrawnCarryOverPoint(String, String, String, String, WEB3PointApply, long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("ObjectMonth:" + l_strObjectMonth);
        
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コード、対象月（YYYYMM）、
        //申込の取消解除を考慮する場合はその申込データ、
        //対象月の前月の繰越ポイントを考慮する必要がある場合はそのポイントを受け取り、
        //算出した対象月から見た失効月の引落繰越ポイントの合計を返却する。

        //①@   失効月を取得する。
        //get失効月()をコールする。
        //[get失効月()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //対象月： 引数.対象月
        //※戻り値を(A)とする。        
        String l_strInvalidMon = this.getInvalidMon(l_strInstitutionCode, l_strObjectMonth);
        
        if (l_strInvalidMon == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("失効月:" + l_strInvalidMon);

        //②   失効月の残ポイントを取得する。
        //get残ポイント()をコールする。
        //[get残ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： (A)
        //※戻り値を(B)とする。        
        long l_lngRemainedPoint = this.getRemainedPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strInvalidMon);
        log.debug("失効月の残ポイント:" + l_lngRemainedPoint);

        //③   対象月の前月の申込ポイントを取得する。
        //get申込ポイント()をコールする。
        //[get申込ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： 引数.対象月の1ヶ月前の年月（YYYYMM）
        //※戻り値を(C)とする。
        Date l_date = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
        if (l_date == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        Calendar l_cal = new GregorianCalendar();
            
        l_cal.setTime(l_date);
            
        l_cal.add(Calendar.MONTH, -1);
            
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        
        //対象月： 引数.対象月の1ヶ月前の年月（YYYYMM)
        String l_strMonth = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");      
        
        long l_lngApplyPoint = getApplyPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strMonth);                
        log.debug("対象月の前月(" + l_strMonth + ")の申込ポイント:" + l_lngApplyPoint);

        //④   引数.取消解除申込 != null and 引数.取消解除申込.申込日時の年月部分（YYYYMM） = 
        //引数.対象月の1ヶ月前の年月（YYYYMM） の場合
        //(C) = (C) + 引数.取消解除申込.使用ポイント とする。      
        if (l_cancelReleaseApply != null)
        {
            log.debug("取消解除申込 != null");
            String l_strApplyMonth = WEB3DateUtility.formatDate(l_cancelReleaseApply.getApplyTimestamp(), "yyyyMM"); 
            if (l_strMonth == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            if (l_strMonth.equals(l_strApplyMonth))
            {
                log.debug("取消解除申込.申込日時の年月部分（YYYYMM）= 引数.対象月の1ヶ月前の年月（YYYYMM） の場合");
                PointApplyParams l_params = new PointApplyParams((PointApplyParams)l_cancelReleaseApply.getDataSourceObject());
                
                if (l_params == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                l_lngApplyPoint = l_lngApplyPoint + l_params.getUsedPoint();
                
                log.debug("対象月の前月の申込ポイントchanged:" + l_lngApplyPoint);

            }
        }


        //⑤   対象月の前月の未集計調整ポイントを取得する。
        //get未集計調整ポイント()をコールする。
        //[get未集計調整ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： 引数.対象月の1ヶ月前の年月（YYYYMM）
        //※戻り値を(D)とする。
        long l_lngAdjustPoint = getNotTotalAdjustPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strMonth);
        log.debug("対象月の前月(" + l_strMonth + ")の未集計調整ポイント:" + l_lngAdjustPoint);            


        //⑥   暫定繰越ポイントを算出する。
        //・   (D) ＜ 0 の場合、暫定繰越ポイント = 引数.前月繰越ポイント + (C) - (D)
        //・   (D) ≧ 0 の場合、暫定繰越ポイント = 引数.前月繰越ポイント + (C)
        //※暫定繰越ポイント = (E) とする。
        long l_lngOverPoint = 0;
        if (l_lngAdjustPoint < 0)
        {
            log.debug("(D) ＜ 0 の場合");
            //(D) ＜ 0 の場合、暫定繰越ポイント = 引数.前月繰越ポイント + (C) - (D)
            l_lngOverPoint = l_lngPreMonCarryoverPoint + l_lngApplyPoint - l_lngAdjustPoint;
            log.debug("l_lngPreMonCarryoverPoint + l_lngApplyPoint - l_lngAdjustPoint:" + 
                l_lngPreMonCarryoverPoint + " + "+ l_lngApplyPoint + " - " + l_lngAdjustPoint);
            log.debug("暫定繰越ポイント:" + l_lngOverPoint);
        }
        else
        {
            log.debug("(D) ≧ 0 の場合");
            //(D) ≧ 0 の場合、暫定繰越ポイント = 引数.前月繰越ポイント + (C)
            l_lngOverPoint = l_lngPreMonCarryoverPoint + l_lngApplyPoint;
            log.debug("暫定繰越ポイント:" + l_lngOverPoint);
        }

        //⑦   引落繰越ポイントを算出し、返却する。
        //(B) > 0 and (E) > 0 の場合
        //・   (B) ＜ (E) の場合、引落繰越ポイント = (E) - (B)
        //・   (B) ≧ (E) の場合、引落繰越ポイント = 0
        //それ以外の場合
        //引落繰越ポイント = (E)
        long l_lngPoint = 0;
        if (l_lngRemainedPoint > 0 && l_lngOverPoint > 0)
        {
            log.debug("(B) > 0 and (E) > 0 の場合");
            //(B) ＜ (E) の場合、引落繰越ポイント = (E) - (B)
            if (l_lngRemainedPoint < l_lngOverPoint)
            {
                log.debug("(B) ＜ (E) の場合");
                l_lngPoint = l_lngOverPoint -  l_lngRemainedPoint;
                
                log.debug("l_lngOverPoint:" + l_lngOverPoint + " - l_lngRemainedPoint:" + l_lngRemainedPoint);
                
                log.debug("引落繰越ポイント:" + l_lngPoint);
            }
        }
        else
        {
            log.debug("!((B) > 0 and (E) > 0) の場合");
            l_lngPoint = l_lngOverPoint;
            log.debug("l_lngOverPoint:" + l_lngOverPoint);
                
            log.debug("引落繰越ポイント:" + l_lngPoint);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lngPoint;
    }
    
    /**
     * (get引落未済ポイント)<BR>
     * 有効月から引落未済のポイントの合計を取得する。<BR>
     * <BR>
     * this.get引落未済ポイント()をコールする。<BR>
     * <BR>
     * [get引落未済ポイント()にセットする引数]<BR>
     * 証券会社コード： 引数.証券会社コード<BR>
     * 部店コード： 引数.部店コード<BR>
     * 顧客コード： 引数.顧客コード<BR>
     * 取消解除申込： null<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)_<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@return long
     * @@roseuid 41AFE9590131
     */
    public long getNotWithdrawnPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNotWithdrawnPoint(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngPoint = this.getNotWithdrawnPoint(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_lngPoint;
    }
    
    /**
     * (get引落未済ポイント)<BR>
     * 有効月から引落未済のポイントの合計を取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_cancelReleaseApply - (取消解除申込)<BR>
     * 取消解除予定の申込データ<BR>
     * 
     * @@return long
     * @@roseuid 41AEF7D30207
     */
    protected long getNotWithdrawnPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointApply l_cancelReleaseApply) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNotWithdrawnPoint(String, String, String, WEB3PointApply)";
        log.entering(STR_METHOD_NAME);
        
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コード、申込の取消解除を考慮する場合はその申込データを受け取り、算出した有効月から引落未済のポイントの合計を返却する。

        //①@   先月を対象月として失効月の引落繰越ポイントを取得する。
        //get引落繰越ポイント()をコールする。
        //[get引落繰越ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： 現在時刻の1ヶ月前の年月（YYYYMM）
        //取消解除申込： 引数.取消解除申込
        //前月繰越ポイント： 0
        //※戻り値を(A)とする。
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        Calendar l_cal = new GregorianCalendar();            
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -1);           
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());               
        String l_strDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM"); 
        
        long l_lngPoint = this.getWithdrawnCarryOverPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strDate, l_cancelReleaseApply, 0);
            
        log.debug("先月を対象月として失効月の引落繰越ポイントを取得する:" + l_lngPoint);
        
        //②   今月を対象月として失効月の引落繰越ポイントを取得する。
        //get引落繰越ポイント()をコールする。
        //[get引落繰越ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： 現在時刻から取得した年月（YYYYMM）
        //取消解除申込： 引数.取消解除申込
        //前月繰越ポイント： (A)
        //※戻り値を(B)とする。             
        String l_strSystemDate = WEB3DateUtility.formatDate(l_tsSystemTime, "yyyyMM"); 
        
        long l_lngThisPoint = this.getWithdrawnCarryOverPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strSystemDate, l_cancelReleaseApply, l_lngPoint);
            
        log.debug("今月を対象月として失効月の引落繰越ポイントを取得する:" + l_lngThisPoint);

        //③   今月の申込ポイントを取得する。
        //get申込ポイント()をコールする。
        //[get申込ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： 現在時刻から取得した年月（YYYYMM）
        //※戻り値を(C)とする。
        long l_lngApplyPoint = this.getApplyPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strSystemDate);
            
        log.debug("今月(" + l_strSystemDate + ")の申込ポイント:" + l_lngApplyPoint);

        //④   引数.取消解除申込 != null and 引数.取消解除申込.申込日時の年月部分（YYYYMM） = 今月の年月（YYYYMM） の場合
        //(C) = (C) + 引数.取消解除申込.使用ポイント とする。
        if (l_cancelReleaseApply != null)
        {
            log.debug("引数.取消解除申込 != null");
            String l_strApplyMonth = WEB3DateUtility.formatDate(l_cancelReleaseApply.getApplyTimestamp(), "yyyyMM");
            
            if (l_strSystemDate == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            if (l_strSystemDate.equals(l_strApplyMonth))
            {
                log.debug("引数.取消解除申込.申込日時の年月部分（YYYYMM） = 今月の年月（YYYYMM） の場合");
                PointApplyParams l_params = new PointApplyParams((PointApplyParams)l_cancelReleaseApply.getDataSourceObject());
                if (l_params == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                l_lngApplyPoint = l_lngApplyPoint + l_params.getUsedPoint();
                
                log.debug("今月(" + l_strSystemDate + ")の申込ポイントchanged:" + l_lngApplyPoint);
            }   
        }
   
        
        //⑤   今月の未集計調整ポイントを取得する。
        //get未集計調整ポイント()をコールする。
        //[get未集計調整ポイント()にセットする引数]
        //証券会社コード： 引数.証券会社コード
        //部店コード： 引数.部店コード
        //顧客コード： 引数.顧客コード
        //対象月： 現在時刻から取得した年月（YYYYMM）
        //※戻り値を(D)とする。
        long l_lngAdjustPoint = this.getNotTotalAdjustPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strSystemDate);    
            
        log.debug("今月(" + l_strSystemDate +")の未集計調整ポイントを取得する:" + l_lngAdjustPoint);
            
        //⑥   引落未済ポイントを算出し、返却する。
        //・   (D) ＜ 0 の場合、引落未済ポイント = (B) + (C) - (D)
        //・   (D) ≧ 0 の場合、引落未済ポイント = (B) + (C)
        long l_lngNotWithdrawnPoint = 0;
        if (l_lngAdjustPoint < 0)
        {
            log.debug("(D) ＜ 0 の場合");
            l_lngNotWithdrawnPoint = l_lngThisPoint + l_lngApplyPoint - l_lngAdjustPoint;
            log.debug("l_lngThisPoint + l_lngApplyPoint - l_lngAdjustPoint");
            log.debug(l_lngThisPoint + " + " + l_lngApplyPoint + " - " + l_lngAdjustPoint);
            log.debug("引落未済ポイント:" + l_lngNotWithdrawnPoint);
        }
        else
        {
            log.debug("(D) ≧ 0 の場合");
            l_lngNotWithdrawnPoint = l_lngThisPoint + l_lngApplyPoint;
            log.debug("l_lngThisPoint + l_lngApplyPoin");
            log.debug(l_lngThisPoint + " + " + l_lngApplyPoint);
            log.debug("引落未済ポイント:" + l_lngNotWithdrawnPoint);
        }
        
        log.exiting(STR_METHOD_NAME);

        return l_lngNotWithdrawnPoint;
    }
    
    /**
     * (get未集計調整ポイント)<BR>
     * 対象月の未集計の調整ポイントの合計を取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 処理対象となる年月（YYYYMM）<BR>
     * 
     * @@return long
     * @@roseuid 4199CD72002E
     */
    public long getNotTotalAdjustPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNotTotalAdjustPoint(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コード、対象月（YYYYMM）を受け取り、
        //算出した対象月の未集計調整ポイントの合計を返却する。

        //①@   ポイント調整テーブルから、以下の条件でレコードを取得する。
        //[取得条件]
        //証券会社コード ＝ 引数.証券会社コード and
        //部店コード ＝ 引数.部店コード and
        //口座コード ＝ 引数.顧客コード and
        //作成日時 ≧ (A) and
        //作成日時 ＜ (B)
        //※(A)： 引数.対象月の1日の0:00
        //  (B)： 引数.対象月の翌月の1日の0:00
        try
        {      
            log.debug("対象月:" + l_strObjectMonth);
            
            List l_lisRecords = null;

            Calendar l_cal = new GregorianCalendar();
            
            // 引数.対象月の1日の0:00
            Date l_date = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            
            if (l_date == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_cal.setTime(l_date);
            
            l_cal.add(Calendar.MONTH, 1);
            
            //引数.対象月の翌月の1日の0:00
            Date l_nextDate = new Date(l_cal.getTimeInMillis());
                                    
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;

            l_sbWhere = "institution_code = ? and branch_code = ? and " + 
                " account_code = ? and created_timestamp >= ? and created_timestamp < ? ";
                                
            l_objWhere = new Object[]{l_strInstitutionCode, l_strBranchCode, l_strAccountCode, 
                l_date, l_nextDate};        
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointAdjustRow.TYPE,
                l_sbWhere,
                l_objWhere);//DataQueryException, DataNetworkException
            
            //算出した対象月の未集計調整ポイントの合計を返却する。
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            }      
            
            int l_intPoint = 0;
            for (int i = 0; i <l_intRecords; i++)
            {
                PointAdjustRow l_row = (PointAdjustRow)l_lisRecords.get(i);
                l_intPoint = l_intPoint + l_row.getAdjustPoint();
                log.debug("No" + i + ":" + l_intPoint);
            }
            
            log.debug("totle:" + l_intPoint);
            log.exiting(STR_METHOD_NAME);
            
            return l_intPoint;      
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get有効期限月)<BR>
     * 有効期限月を取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 算出対象となる年月（YYYYMM）<BR>
     * 
     * @@return String
     * @@roseuid 41AEF123012C
     */
    public String getValidTermMon(String l_strInstitutionCode, String l_strObjectMonth) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getValidTermMon(String, String)";
        log.entering(STR_METHOD_NAME);
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コード、対象月（YYYYMM）を受け取り、
        //対象月の有効期限月を返却する。

        try
        {   
            //①@   ポイント有効期限テーブルから、以下の条件でレコードを取得する。
            //[取得条件]
            //証券会社コード ＝ 引数.証券会社コード
            
            PointTermRow l_row = PointTermDao.findRowByInstitutionCode(l_strInstitutionCode);//DataQueryException, DataNetworkException
            
            if (l_row == null)
            {
                return null;
            }
            
            String l_strValidity = l_row.getTermOfValidity();
            
            //②   引数.対象月の（ポイント有効期限テーブル.有効期限－１）
            //ヶ月前の年月（YYYYMM）を算出し、返却する。
            
            int l_intValidity = Integer.parseInt(l_strValidity) - 1;//NumberFormatException     
            
            Date l_objectMonth = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            if (l_objectMonth == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_objectMonth);
            
            l_cal.add(Calendar.MONTH, -l_intValidity);
            
            Date l_date = new Date(l_cal.getTimeInMillis());
            
            String l_strValidityNew = WEB3DateUtility.formatDate(l_date, "yyyyMM");
            
            log.exiting(STR_METHOD_NAME);
            
            return l_strValidityNew;      
        }
        catch (NumberFormatException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get失効月)<BR>
     * 失効月を取得する。<BR>
     * <BR>
     * 処理の詳細は、計算式書参照。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strObjectMonth - (対象月)<BR>
     * 算出対象となる年月（YYYYMM）<BR>
     * 
     * @@return String
     * @@roseuid 41AEF2BE0226
     */
    protected String getInvalidMon(String l_strInstitutionCode, String l_strObjectMonth) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInvalidMon(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[メソッド概要]
        //引数にて証券会社コード、部店コード、顧客コード、
        //対象月（YYYYMM）を受け取り、対象月の失効月を返却する。

        //①@   ポイント有効期限テーブルから、以下の条件でレコードを取得する。
        //[取得条件]
        //証券会社コード ＝ 引数.証券会社コード    
        
        //② 引数.対象月の（ポイント有効期限テーブル.有効期限）ヶ月前の年月（YYYYMM）を算出し、返却する。
        try
        {      
            PointTermRow l_row = PointTermDao.findRowByInstitutionCode(l_strInstitutionCode);//DataQueryException, DataNetworkException
            
            if (l_row == null)
            {
                return null;
            }
            
            String l_strTermOfValidity = l_row.getTermOfValidity();
            
            int l_intValidityMon = Integer.parseInt(l_strTermOfValidity);//NumberFormatException            
            
            Date l_objectMonth = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            if (l_objectMonth == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_objectMonth);  
            
            l_cal.add(Calendar.MONTH, -l_intValidityMon);
            
            Date l_nextDate = new Date(l_cal.getTimeInMillis());
            
            String l_strInvalidMon = WEB3DateUtility.formatDate(l_nextDate, "yyyyMM");
            
            log.exiting(STR_METHOD_NAME);
            
            return l_strInvalidMon;
        }
        catch (NumberFormatException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate調整ポイント)<BR>
     * 調整ポイントのチェックを行う。<BR>
     * <BR>
     * １）整合性チェック<BR>
     * <BR>
     * １－１）<BR>
     * <BR>
     *    引数.調整ポイント = null or<BR>
     *    引数.調整ポイント = 0 or<BR>
     *    引数.調整ポイント != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01719<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01720<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01721<BR>
     * <BR>
     * １－２）引数.調整ポイント > 0 のとき<BR>
     * <BR>
     *    引数.調整ポイント.length() > 8<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01722<BR>
     * <BR>
     * １－３）引数.調整ポイント < 0 のとき<BR>
     * <BR>
     *    引数.調整ポイント.length() > 9 （⇒マイナスを含めたlength）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01722<BR>
     * <BR>
     * ２）調整後ポイントのチェック<BR>
     * <BR>
     *    引数.利用可能ポイント + 引数.調整ポイント < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01724<BR>
     * @@param l_strAdjustPoint - (調整ポイント)<BR>
     * 調整ポイント<BR>
     * 
     * @@param l_lngUsePossiblePoint - (利用可能ポイント)<BR>
     * 利用可能ポイント<BR>
     * @@roseuid 419468D500FA
     */
    public void validateAdjustPoint(String l_strAdjustPoint, long l_lngUsePossiblePoint) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAdjustPoint(String, long)";
        log.entering(STR_METHOD_NAME);
        
        //１）整合性チェック<BR>
        //１－１）<BR>
        //   引数.調整ポイント = null or<BR>
        //   引数.調整ポイント = 0 or<BR>
        //   引数.調整ポイント != 数字<BR>
        //   の場合、例外をスローする。<BR>
        //        class: WEB3BusinessLayerException<BR>
        //        tag:   BUSINESS_ERROR_01719<BR>
        //        class: WEB3BusinessLayerException<BR>
        //        tag:   BUSINESS_ERROR_01720<BR>
        //        class: WEB3BusinessLayerException<BR>
        //        tag:   BUSINESS_ERROR_01721<BR>
        if (l_strAdjustPoint == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01719,
                getClass().getName() + STR_METHOD_NAME,
                " 引数.調整ポイント = null の場合、例外をスローする。");
        }
        
        if (!WEB3StringTypeUtility.isNumber(l_strAdjustPoint))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01721,
                getClass().getName() + STR_METHOD_NAME,
                " 引数.調整ポイント != 数字 の場合、例外をスローする。");  
        }
        
        if (new Long(l_strAdjustPoint).intValue() == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01720,
                getClass().getName() + STR_METHOD_NAME,
                " 引数.調整ポイント = 0 の場合、例外をスローする。"); 
        }
        
        //１－２）引数.調整ポイント > 0 のとき<BR>
        //    引数.調整ポイント.length() > 8<BR>
        //    の場合、例外をスローする。<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01722<BR>
        if (new Long(l_strAdjustPoint).intValue() > 0 && WEB3StringTypeUtility.getByteLength(l_strAdjustPoint) > 8)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01722,
                getClass().getName() + STR_METHOD_NAME,
                " 調整ポイントの桁数が制限を越えています。");
        }
        
        // １－３）引数.調整ポイント < 0 のとき<BR>
        // <BR>
        //    引数.調整ポイント.length() > 9 （⇒マイナスを含めたlength）<BR>
        // <BR>
        //    の場合、例外をスローする。<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01722<BR>
        if (new Long(l_strAdjustPoint).intValue() < 0 && WEB3StringTypeUtility.getByteLength(l_strAdjustPoint) > 9)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01722,
                getClass().getName() + STR_METHOD_NAME,
                " 調整ポイントの桁数が制限を越えています。");
        }
        
        // ２）調整後ポイントのチェック<BR>
        // <BR>
        //    引数.利用可能ポイント + 引数.調整ポイント < 0<BR>
        // <BR>
        //    の場合、例外をスローする。<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01724<BR>
        long l_lngPoint = l_lngUsePossiblePoint + new Long(l_strAdjustPoint).intValue();
        if (l_lngPoint < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01724,
                getClass().getName() + STR_METHOD_NAME,
                " 引数.利用可能ポイント + 引数.調整ポイント < 0 " +
                " の場合、例外をスローする。");
        }
               
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateポイント余力)<BR>
     * ポイント余力のチェックを行う。<BR>
     * <BR>
     * １）利用可能ポイントの取得<BR>
     * <BR>
     *    this.get利用可能ポイント()をコールする。<BR>
     * <BR>
     *    [get利用可能ポイント()にセットする引数]<BR>
     *    証券会社コード： 引数.証券会社コード<BR>
     *    部店コード： 引数.部店コード<BR>
     *    顧客コード： 引数.顧客コード<BR>
     * <BR>
     * ２）ポイント余力のチェック<BR>
     * <BR>
     *    １）の結果 < 引数.ポイント景品.get必要ポイント()の戻り値<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01725<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_premium - (景品)<BR>
     * 景品オブジェクト<BR>
     * @@roseuid 419D767D0374
     */
    public void validatePointPower(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointPremium l_premium) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePointPower(String, String, String, WEB3PointPremium)";
        log.entering(STR_METHOD_NAME);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // １）利用可能ポイントの取得<BR>
        //    this.get利用可能ポイント()をコールする。<BR>
        //    [get利用可能ポイント()にセットする引数]<BR>
        //    証券会社コード： 引数.証券会社コード<BR>
        //    部店コード： 引数.部店コード<BR>
        //    顧客コード： 引数.顧客コード<BR>
        long l_lngPossiblePoint = this.getUsePossiblePoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode);
        
        // ２）ポイント余力のチェック<BR>
        //    １）の結果 < 引数.ポイント景品.get必要ポイント()の戻り値<BR>
        //    の場合、例外をスローする。<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01725<BR>
        if (l_lngPossiblePoint < l_premium.getRequiredPoint())
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01970,
                getClass().getName() + STR_METHOD_NAME,
                " 利用可能ポイント< 引数.ポイント景品.get必要ポイント()の戻り値 " +
                " の場合、例外をスローする。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateポイント余力)<BR>
     * 申込の取消解除でポイント申込が有効になった時のポイント余力のチェックを行う。<BR>
     * <BR>
     * １）利用可能ポイントの算出<BR>
     * <BR>
     *    this.get利用可能ポイント()をコールする。<BR>
     * <BR>
     *    [get利用可能ポイントにセットする引数]<BR>
     *    証券会社コード： 引数.申込データ.証券会社コード<BR>
     *    部店コード： 引数.申込データ.部店コード<BR>
     *    顧客コード： 引数.申込データ.口座コード<BR>
     *    取消解除申込： 引数.申込データ<BR>
     * <BR>
     *    ※戻り値を(A)とする。<BR>
     * <BR>
     * ３）ポイント余力チェック<BR>
     * <BR>
     *    (A) < 0<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01725<BR>
     * <BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * @@roseuid 419D8739028A
     */
    public void validatePointPower(WEB3PointApply l_applyData) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePointPower(WEB3PointApply)";
        log.entering(STR_METHOD_NAME);
        if (l_applyData == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // １）利用可能ポイントの算出<BR>
        //    this.get利用可能ポイント()をコールする。<BR>
        //    [get利用可能ポイントにセットする引数]<BR>
        //    証券会社コード： 引数.申込データ.証券会社コード<BR>
        //    部店コード： 引数.申込データ.部店コード<BR>
        //    顧客コード： 引数.申込データ.口座コード<BR>
        //    取消解除申込： 引数.申込データ<BR>
        //    ※戻り値を(A)とする。
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        long l_lngPossiblePoint = getUsePossiblePoint(l_params.getInstitutionCode(), 
            l_applyData.getBranchCode(), l_applyData.getAccountCode(), l_applyData);
        
        //３）ポイント余力チェック
        if (l_lngPossiblePoint < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01725,
                getClass().getName() + STR_METHOD_NAME,
                " 利用可能ポイント < 0 " +
                " の場合、例外をスローする。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (saveNew調整)<BR>
     * 調整データをDBに登録する。<BR>
     * <BR>
     * １）行オブジェクト取得 <BR>
     *    引数.調整データ.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ２）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
     *    作成日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ３）DB更新 <BR>
     *    行オブジェクトの内容でポイント調整テーブルに行を挿入（insert）する。 <BR>
     * <BR>
     * @@param l_adjustData - (調整データ)<BR>
     * ポイント調整オブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419479C7007D
     */
    public void saveNewAdjust(WEB3PointAdjust l_adjustData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewAdjust(WEB3PointAdjust, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_adjustData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）行オブジェクト取得
        //引数.調整データ.getDataSourceObject()にて行オブジェクトを取得する。
        PointAdjustParams l_params = (PointAdjustParams)l_adjustData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //２）取得した行オブジェクトに以下の通り、項目の初期値をセットする。             
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //作成日時 = TradingSystem.getSystemTimestamp()
        l_params.setCreatedTimestamp(l_tsSystemTime);
        
        //更新日時 = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //更新者コード = 引数.管理者の管理者コード
        String l_strAdministratorCode = l_admin.getAdministratorCode();        
        l_params.setLastUpdater(l_strAdministratorCode);
        
        //３）DB更新
        //行オブジェクトの内容でポイント調整テーブルに行を挿入（insert）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doInsertQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (saveNew申込)<BR>
     * 申込データをDBに登録する。<BR>
     * <BR>
     * １）行オブジェクト取得 <BR>
     *    引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。<BR>
     * <BR>
     * ２）取得した行オブジェクトの値がセットされてない項目に値をセットする。<BR>
     *    DB更新仕様「ポイント交換申込_ポイント申込テーブル」参照 <BR>
     * <BR>
     * ３）DB更新 <BR>
     *    行オブジェクトの内容でポイント申込テーブルに行を挿入（insert）する。<BR>
     * <BR>
     * @@param l_appyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * 
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者オブジェクト<BR>
     * @@roseuid 41A451E10111
     */
    public void saveNewApply(WEB3PointApply l_appyData, Trader l_trader) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewApply(WEB3PointApply, Trader)";
        log.entering(STR_METHOD_NAME);
        
        if (l_appyData == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）行オブジェクト取得
        //引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。
        PointApplyParams l_params = (PointApplyParams)l_appyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //２）取得した行オブジェクトに以下の通り、項目の初期値をセットする。          
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //申込経路区分 = セッションから取得した注文経路区分
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
                
        if (l_opLoginSec == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME); 
        }
                
        //注文経路区分
        String l_orderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                
        l_params.setApplyRootDiv(l_orderRootDiv);
        
        //申込日時 = TradingSystem.getSystemTimestamp()
        l_params.setApplyTimestamp(l_tsSystemTime);
        
        //更新日時 = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //申込受付区分 0：申込み時
        l_params.setApplyAcceptDiv(WEB3ApplyAcceptDivDef.APPLYING);
        
        //申込取消区分 0：取消未済
        l_params.setApplyCancelDiv(WEB3ApplyCancelDivDef.NOT_CANCELED);
        
        //扱者コード        
        //更新者コード
        if (l_trader == null)
        {
            //扱者コード=null の場合、口座コード
            l_params.setLastUpdater(l_appyData.getAccountCode());
        }
        
        if (l_trader != null)
        {
            //代理入力の場合、代理入力者.getTraderCode()
            l_params.setTraderCode(l_trader.getTraderCode());
            
            //扱者コード!=null の場合、扱者コード
            l_params.setLastUpdater(l_trader.getTraderCode());
        }
        
        //３）DB更新
        //行オブジェクトの内容でポイント調整テーブルに行を挿入（insert）する。
        try
        {
            //２）取得した行オブジェクトに以下の通り、項目の初期値をセットする。
            //申込ID（自動採番）
            l_params.setApplyId(PointApplyDao.newPkValue());//DataNetworkException,DataQueryException
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doInsertQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save申込受付)<BR>
     * 申込受付状態にDBを更新する。<BR>
     * <BR>
     * １）更新用の行オブジェクト生成<BR>
     *    引数.申込データ.createForUpdateParams()をコールする。<BR>
     * <BR>
     * ２）行オブジェクト取得 <BR>
     *    引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
     *    申込受付区分 = 1（証券会社受付済み）<BR>
     *    申込受付者 = 引数.管理者のログインID<BR>
     *    申込受付日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ４）DB更新 <BR>
     *    行オブジェクトの内容でポイント申込テーブルを更新（update）する。 <BR>
     * <BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419C90410093
     */
    public void saveApplyAccept(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveApplyAccept(WEB3PointApply, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_applyData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // １）更新用の行オブジェクト生成<BR>
        //    引数.申込データ.createForUpdateParams()をコールする。<BR>
        l_applyData.createForUpdateParams();
        
        // ２）行オブジェクト取得 <BR>
        //    引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
        //    申込受付区分 = 1（証券会社受付済み）<BR>
        l_params.setApplyAcceptDiv(WEB3ApplyAcceptDivDef.INSTITUTION_ACCEPTED);
        
        //申込受付者 = 引数.管理者のログインID<BR>
        l_params.setApplyAcceptUser(new Long(l_admin.getLoginId()).toString());
        
        //申込受付日時 = TradingSystem.getSystemTimestamp() <BR>
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_params.setApplyAcceptTimestamp(l_tsSystemTime);
        
        //更新日時 = TradingSystem.getSystemTimestamp() <BR>
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //更新者コード = 引数.管理者の管理者コード<BR>
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // ４）DB更新 <BR>
        //    行オブジェクトの内容でポイント申込テーブルを更新（update）する。 <BR>
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save申込取消)<BR>
     * 申込取消状態にDBを更新する。<BR>
     * <BR>
     * １）更新用の行オブジェクト生成<BR>
     *    引数.申込データ.createForUpdateParams()をコールする。<BR>
     * <BR>
     * ２）行オブジェクト取得 <BR>
     *    引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
     *    申込取消区分 = 1（取消済み）<BR>
     *    申込取消者 = 引数.管理者のログインID<BR>
     *    申込取消日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ４）DB更新 <BR>
     *    行オブジェクトの内容でポイント申込テーブルを更新（update）する。 <BR>
     * <BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419D631501E6
     */
    public void saveApplyCancel(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveApplyCancel(WEB3PointApply, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_applyData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // １）更新用の行オブジェクト生成<BR>
        //    引数.申込データ.createForUpdateParams()をコールする。<BR>
        l_applyData.createForUpdateParams();
        
        // ２）行オブジェクト取得 <BR>
        //    引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
        //    申込受付区分 = 1（証券会社受付済み）<BR>
        l_params.setApplyCancelDiv(WEB3ApplyCancelDivDef.CANCELED);
        
        //申込取消者 = 引数.管理者のログインID
        l_params.setApplyCancelUser(new Long(l_admin.getLoginId()).toString());
        
        //申込取消日時 = TradingSystem.getSystemTimestamp() <BR>
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_params.setApplyCancelTimestamp(l_tsSystemTime);
        
        //更新日時 = TradingSystem.getSystemTimestamp() <BR>
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //更新者コード = 引数.管理者の管理者コード<BR>
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // ４）DB更新 <BR>
        //行オブジェクトの内容でポイント申込テーブルを更新（update）する。>
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_params == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save申込取消解除)<BR>
     * 申込取消解除状態にDBを更新する。<BR>
     * <BR>
     * １）更新用の行オブジェクト生成<BR>
     *    引数.申込データ.createForUpdateParams()をコールする。<BR>
     * <BR>
     * ２）行オブジェクト取得 <BR>
     *    引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
     *    申込取消区分 = 0（取消未済）<BR>
     *    申込取消者 = 引数.管理者のログインID<BR>
     *    申込取消日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ４）DB更新 <BR>
     *    行オブジェクトの内容でポイント申込テーブルを更新（update）する。 <BR>
     * <BR>
     * @@param l_applyData - (申込データ)<BR>
     * ポイント申込オブジェクト<BR>
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419D74860142
     */
    public void saveApplyCancelRelease(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveApplyCancelRelease(WEB3PointApply, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_applyData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // １）更新用の行オブジェクト生成<BR>
        //    引数.申込データ.createForUpdateParams()をコールする。<BR>
        l_applyData.createForUpdateParams();
        
        // ２）行オブジェクト取得 <BR>
        //    引数.申込データ.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
        //申込取消区分 = 0（取消未済）<BR>
        l_params.setApplyCancelDiv(WEB3ApplyCancelDivDef.NOT_CANCELED);
        
        //申込取消者 = 引数.管理者のログインID
        l_params.setApplyCancelUser(new Long(l_admin.getLoginId()).toString());
        
        //申込取消日時 = TradingSystem.getSystemTimestamp() <BR>
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_params.setApplyCancelTimestamp(l_tsSystemTime);
        
        //更新日時 = TradingSystem.getSystemTimestamp() <BR>
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //更新者コード = 引数.管理者の管理者コード<BR>
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // ４）DB更新 <BR>
        //行オブジェクトの内容でポイント申込テーブルを更新（update）する。>
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DBアクセスが失敗の場合
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //例外をスローする
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate申込可能)<BR>
     * ポイント申込可能かどうかのチェックを行う。<BR>
     * <BR>
     * １）景品オブジェクトの取得<BR>
     * <BR>
     *    ポイント商品マネージャImpl.get景品()をコールする。<BR>
     * <BR>
     *    [get景品()にセットする引数]<BR>
     *    証券会社コード： 引数.証券会社コード<BR>
     *    景品番号： 引数.景品番号<BR>
     * <BR>
     * ２）景品の提供期間チェック<BR>
     * <BR>
     *    下記の条件を満たさない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01726<BR>
     * <BR>
     *    景品.提供開始日時 ≦ 現在日時 ＜ 景品.提供終了日時<BR>
     * <BR>
     * ３）ポイント余力チェック<BR>
     * <BR>
     *    this.validateポイント余力()をコールする。<BR>
     * <BR>
     *    [get景品()にセットする引数]<BR>
     *    証券会社コード： 引数.証券会社コード<BR>
     *    部店コード： 引数.部店コード<BR>
     *    顧客コード： 引数.顧客コード<BR>
     *    景品： 景品オブジェクト<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * 
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * 
     * @@param l_strPrimiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * @@roseuid 41A6E44C001A
     */
    public void validateApplyPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strPrimiumNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateApplyPossible(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）景品オブジェクトの取得
        //ポイント商品マネージャImpl.get景品()をコールする。
        //[get景品()にセットする引数]<BR>
        //証券会社コード： 引数.証券会社コード<BR>
        //景品番号： 引数.景品番号<BR>
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME); 
        }
            
        WEB3PointPremium l_pointPremium = l_manager.getPremium(
            l_strInstitutionCode, l_strPrimiumNo);
            
        if (l_pointPremium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //２）景品の提供期間チェック
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //景品.提供開始日時
        Timestamp l_tsStartDateTime = new Timestamp(l_pointPremium.getStartDateTime().getTime());
        
        //景品.提供終了日時
        Timestamp l_tsEndDateTime = new Timestamp(l_pointPremium.getEndDateTime().getTime());
        
        //下記の条件を満たさない場合、例外をスローする。
        //景品.提供開始日時 ≦ 現在日時 ＜ 景品.提供終了日時
        int l_intComparaeOne = WEB3DateUtility.compareToSecond(l_tsSystemTime, l_tsEndDateTime);
        int l_intComparaeTwo = WEB3DateUtility.compareToSecond(l_tsSystemTime, l_tsStartDateTime);
        if (!(l_intComparaeOne < 0 && l_intComparaeTwo >= 0))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01726,
                getClass().getName() + STR_METHOD_NAME,
                " 景品.提供終了日時≦現在日時< 景品.提供開始日時" +
                " の場合、例外をスローする。");
        }
        
        //３）ポイント余力チェック
        //this.validateポイント余力()をコールする。
        //[get景品()にセットする引数]<BR>
        //証券会社コード： 引数.証券会社コード<BR>
        //部店コード： 引数.部店コード<BR>
        //顧客コード： 引数.顧客コード<BR>
        //景品： 景品オブジェクト<BR>
        this.validatePointPower(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_pointPremium);
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
