head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェックサービスImpl(WEB3AioCashoutTradingPowerServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 黄建 (中訊) 新規作成
Revesion History : 2004/10/25 周勇(中訊) レビュー
Revesion History : 2004/12/17 周勇 (中訊) 残対応
Revesion History : 2006/08/28 車進 (中訊) 仕様変更・モデルNo.630、645
Revesion History : 2006/11/15 徐宏偉 (中訊)仕様変更・モデルNo.684
Revesion History : 2007/03/28 何文敏 (中訊)仕様変更・モデルNo.718
Revesion History : 2007/04/09 何文敏 (中訊)仕様変更・モデルNo.720
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.aio.define.WEB3AioProcessFlagDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckRequest;
import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckResponse;
import webbroker3.aio.service.delegate.WEB3AioCashOutOrderTriggerIssueUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerService;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostStatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3PaymentApplyTriggerDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金余力チェックサービスImpl)<BR>
 * 出金余力チェックサービス実装クラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AioCashoutTradingPowerServiceImpl 
    implements WEB3AioCashoutTradingPowerService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutTradingPowerServiceImpl.class);
    
    /**
     * 出金余力チェック処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金余力チェック）execute」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3BackResponse
     * @@roseuid 41294DB20312
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashoutTradingPowerCheckRequest l_checkRequest = null;
        if (!(l_request instanceof WEB3AioCashoutTradingPowerCheckRequest))
        {
            log.debug(
                "リクエストデータが" +
                "WEB3AioCashoutTradingPowerCheckRequest以外である, but is " + l_request.getClass().getName());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            l_checkRequest = (WEB3AioCashoutTradingPowerCheckRequest) l_request;
        }
        
        // 1.1)取得条件の文字列を生成する。 
        //[引数] 
        //処理フラグ： リクエストデータ.処理フラグ
        String l_strCharacter =
            this.createGetCondCharacterString(l_checkRequest.processFlag);
        log.debug("取得条件の文字列を生成する = " + l_strCharacter);    
        
        // 1.2)取得条件に設定する値の配列を生成する。
        //[引数] 
        //リクエストデータ： リクエストデータ 
        Object[] l_objGetCondDataContainer = 
            this.createGetCondDataContainer(l_checkRequest);
        
        try
        {
            // 1.3)クエリプロセッサを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        
            // 1.4)出金請求注文キューテーブルから、レコードを取得する。
            //［引数］ 
            //Rowタイプ： 出金請求注文キューRow.TYPE 
            //Where： create取得条件文字列()の戻り値 
            //orderBy： "branch_code desc, account_code desc" 
            //condition： null 
            //リスト： create取得条件データコンテナ()の戻り値
            List l_lisHostPaymentOrderRows = 
                l_queryProcessor.doFindAllQuery(
                    HostPaymentOrderRow.TYPE,
                    l_strCharacter,
                    "branch_code desc, account_code desc",
                    null,
                    l_objGetCondDataContainer);
            int l_intSize = 0;
            if (l_lisHostPaymentOrderRows != null && !l_lisHostPaymentOrderRows.isEmpty())
            {
                l_intSize = l_lisHostPaymentOrderRows.size();
            }

            //入出金・入出庫リクエスト送信サービス
            WEB3AioMarketRequestSenderServiceImpl 
                l_marketRequestSenderServiceImpl = 
                    (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();
            
            // 1.5)is余力チェック後トリガ発行(String)
            boolean l_blnIsTpCheckedTrigger = 
                this.isTradingPowerCheckedTriggerIssue(
                l_checkRequest.institutionCode);

            //1.6.getDB時価余力チェック区分(String)
            //「証券会社テーブル.DB時価余力チェック区分」を取得する。
            //[引数]
            //リクエストデータ.証券会社コード
            String l_strDbCurrentPriceCheckDiv =
                this.getDbCurrentPriceCheckDiv(l_checkRequest.institutionCode);
            
            // getトリガ発行件数( )
            // システムプリファ@レンスより、出金余力チェック後のトリガ発行件数 を取得する。 
            int l_intTriggerIssueNumber = this.getTriggerIssueNumber();

            int l_intCount = 0;
            // 1.7)取得したレコード毎にLoop処理
            for(int i=0; i < l_intSize; i++)
            {
                HostPaymentOrderRow l_hostPaymentOrderRow = 
                    (HostPaymentOrderRow) l_lisHostPaymentOrderRows.get(i);
                
                HostPaymentOrderParams l_hostPaymentOrderParams = 
                    new HostPaymentOrderParams(l_hostPaymentOrderRow);
                
                l_intCount++;
                // 1.7.1)execute(出金請求注文キューParams, String, boolean, String)
                //注文の余力チェック処理を行う。
                //[引数]
                //出金請求注文キュー： 取得した出金請求注文キューParams
                //処理フラグ： リクエストデータ.処理フラグ
                //トリガ発行フラグ： is余力チェック後トリガ発行メソッドで取得した値
                //DB時価余力チェック区分： getDB時価余力チェック区分メソッドで取得した値
                try
                {
                    WEB3AioCashoutTradingPowerUnitService l_aioCashoutTradingPowerUnitService =
                        (WEB3AioCashoutTradingPowerUnitService) Services.getService(
                            WEB3AioCashoutTradingPowerUnitService.class);
                    
                    l_aioCashoutTradingPowerUnitService.execute(
                        l_hostPaymentOrderParams,
                        l_checkRequest.processFlag, 
                        l_blnIsTpCheckedTrigger,
                        l_strDbCurrentPriceCheckDiv);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error("注文の余力チェックにてエラーが発生しました。");
                    continue;
                } 
                // リクエストデータ.処理フラグ != '0'（全件データ処理） の場合、実施
                if (!WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_checkRequest.processFlag))
                {
                    // Loopカウント数 >= getトリガ発行件数()の場合、以下の処理を実行
                    if (l_intCount >= l_intTriggerIssueNumber)
                    {
                        // execute
                        WEB3AioCashOutOrderTriggerIssueUnitService l_cashOutOrderTriggerIssueUnitService =
                            (WEB3AioCashOutOrderTriggerIssueUnitService)Services.getService(
                                WEB3AioCashOutOrderTriggerIssueUnitService.class);
                        l_cashOutOrderTriggerIssueUnitService.execute(l_checkRequest.institutionCode);

                        l_intCount = 0;
                    }
                }
            }

            // リクエストデータ.処理フラグ != '0'（全件データ処理） の場合、実施
            // トリガ発行(String, String)
            // 証券会社コード： リクエストデータ.証券会社コード
            // データコード： "GI801T"
            if (!WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_checkRequest.processFlag))
            {
                // execute
                WEB3AioCashOutOrderTriggerIssueUnitService l_cashOutOrderTriggerIssueUnitService =
                    (WEB3AioCashOutOrderTriggerIssueUnitService)Services.getService(
                        WEB3AioCashOutOrderTriggerIssueUnitService.class);
                l_cashOutOrderTriggerIssueUnitService.execute(l_checkRequest.institutionCode);
//                l_marketRequestSenderServiceImpl.submitTrigger(
//                        l_checkRequest.institutionCode, 
//                        WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T");
            }
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
        //出金余力チェックレスポンス
        WEB3AioCashoutTradingPowerCheckResponse 
            l_aioCashoutTradingPowerCheckResponse = 
                (WEB3AioCashoutTradingPowerCheckResponse) l_checkRequest.createResponse(); 

        //生成した出金余力チェックレスポンスを返す。
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutTradingPowerCheckResponse;
    }
    
    /**
     * (create取得条件文字列)<BR>
     * リクエストデータから、データ取得条件文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）データコード条件生成<BR>
     * <BR>
     *   条件文字列： "request_code='GI801'"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３）証券会社コード条件生成<BR>
     * <BR>
     *   条件文字列： " and institution_code=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４）振込予定日条件生成<BR>
     * <BR>
     *   引数.処理フラグ != '0'（全件データ処理） の場合<BR>
     * <BR>
     *   条件文字列： " and est_transfer_date=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５）処理区分条件生成<BR>
     * <BR>
     *   条件文字列： " and status='3'"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ６）生成した文字列を返却する。<BR>
     * @@param l_strProcessFlag - (処理フラグ)
     * @@return String
     * @@roseuid 4129523A0227
     */
    protected String createGetCondCharacterString(String l_strProcessFlag) 
    {
        final String STR_METHOD_NAME = 
            "createGetCondCharacterString(String l_strProcessFlag)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strProcessFlag == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）空の文字列を生成する。         
        String l_strCreateCharacter = "";
        
        //２）データコード条件生成 
        //条件文字列： "request_code='GI801'" 
        //上記文字列を１）の文字列の末尾に追加する。     
        l_strCreateCharacter = 
            l_strCreateCharacter + " request_code = ? "; 
        
        //３）証券会社コード条件生成 
        //条件文字列： " and institution_code=?" 
        //上記文字列を１）の文字列の末尾に追加する。
        l_strCreateCharacter = 
            l_strCreateCharacter + " and institution_code = ? ";      
        
        //４）振込予定日条件生成 
        //引数.処理フラグ != '0'（全件データ処理） の場合 
        //条件文字列： " and est_transfer_date=?"
        //上記文字列を１）の文字列の末尾に追加する。
        if (!WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_strProcessFlag))
        {
            l_strCreateCharacter = 
                l_strCreateCharacter + " and to_char(est_transfer_date, 'yyyyMMdd') = ? ";              
        }
        else
        {
            l_strCreateCharacter = l_strCreateCharacter + "";
        }
              
        //５）処理区分条件生成 
        //条件文字列： " and status='3'" 
        //上記文字列を１）の文字列の末尾に追加する。
        l_strCreateCharacter = l_strCreateCharacter +  " and status = ? ";
        
        
        //６）生成した文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strCreateCharacter;
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータリストを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     *   引数.リクエストデータ.証券会社コードを１）のリストに追加する。<BR>
     * <BR>
     * ３）振込予定日<BR>
     * <BR>
     * ３－１）引数.リクエストデータ.処理フラグ = '1'（当日振込分データ処理） の場合 <BR> 
     * <BR>
     * ３－１－１）現在時刻（システムタイムスタンプ）を取得する。<BR>
     * <BR>
     * ３－１－２）取得した現在時刻の日付部分を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３－２）引数.リクエストデータ.処理フラグ = '2'（翌日振込分データ処理） の場合<BR>
     * <BR>
     * ３－２－１）現在時刻（システムタイムスタンプ）を取得する。<BR>
     * <BR>
     * ３－２－２）取得した現在時刻の翌営業日を算出する。<BR>
     * <BR>
     * ３－２－３）取得した翌営業日日付を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４）リストから配列を取得し、返却する。<BR>
     * @@param l_request - (リクエストデータ)
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 4129542D0053
     */
    protected Object[] createGetCondDataContainer(WEB3AioCashoutTradingPowerCheckRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createGetCondDataContainer(WEB3AioCashoutTradingPowerCheckRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
                
        //１）空のArrayListを生成する。 
        List l_lisCreateGetCondDataContainer = new ArrayList();
        
        //データコード
        l_lisCreateGetCondDataContainer.add(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
        
        //２）証券会社コード 
        //引数.リクエストデータ.証券会社コードを１）のリストに追加する。 
        l_lisCreateGetCondDataContainer.add(l_request.institutionCode);
       
        
        //３）振込予定日
        //３－１）引数.リクエストデータ.処理フラグ = '1'（当日振込分データ処理） の場合
        //３－１－１）現在時刻（システムタイムスタンプ）を取得する。
        //３－１－２）取得した現在時刻の日付部分を１）の文字列の末尾に追加する。
        if(WEB3AioProcessFlagDef.DAY_BIZ_TRANSFER_PROCESS.equals(l_request.processFlag))
        {
            Date l_returnDate = GtlUtils.getSystemTimestamp();
            l_lisCreateGetCondDataContainer.add(
                WEB3DateUtility.formatDate(l_returnDate, "yyyyMMdd"));   
        }
        
        //３－２）引数.リクエストデータ.処理フラグ = '2'（翌日振込分データ処理） の場合 
        //３－２－１）現在時刻（システムタイムスタンプ）を取得する。
        if (WEB3AioProcessFlagDef.NEXT_DATE_TRANSFER_PROCESS.equals(l_request.processFlag))
        {
            //３－２－２）取得した現在時刻の翌営業日を算出する。
            //３－２－３）取得した翌営業日日付を１）の文字列の末尾に追加する。
            Date l_returnDate = new WEB3GentradeBizDate(
                new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);
            l_lisCreateGetCondDataContainer.add(
                WEB3DateUtility.formatDate(l_returnDate, "yyyyMMdd"));
        }
        
        //処理区分
        l_lisCreateGetCondDataContainer.add(WEB3AioHostStatusDef.NOT_DEAL);
        
        //４）リストから配列を取得し、返却する
        Object[] l_objCreateGetCondDataContainer = 
            new Object[l_lisCreateGetCondDataContainer.size()];
        l_lisCreateGetCondDataContainer.toArray(l_objCreateGetCondDataContainer);
        
        log.exiting(STR_METHOD_NAME);
        return l_objCreateGetCondDataContainer;
    }
    
    /**
     * (is余力チェック後トリガ発行)<BR>
     * 該当する証券会社が余力チェック後にトリガ発行をするという<BR>
     * 設定になっているか<BR>どうかとチェックする。<BR>
     * <BR>
     * １）証券会社テーブルのレコードを取得する。<BR>
     * <BR>
     *   部店（ 引数.部店ID ）.getInstitution().getDataSourceObject()<BR>
     * <BR>
     * ２）証券会社Params.出金申込トリガ発行 = <BR>
     * 　@　@"1"（タイマー実施（余力チェックあり））の場合は、trueを返す。<BR>
     * 　@　@それ以外の場合は、falseを返す。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isTradingPowerCheckedTriggerIssue(String l_strInstitutionCode) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "isTradingPowerCheckedTriggerIssue(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）証券会社テーブルのレコードを取得する。       
        InstitutionRow l_institutionRow = null;
        try
        {
            l_institutionRow = InstitutionDao.findRowByInstitutionCode(l_strInstitutionCode);
            
            //２）証券会社Params.出金申込トリガ発行 = "1"
            //（タイマー実施（余力チェックあり））の場合は、trueを返す。 
            //それ以外の場合は、falseを返す。
            if( l_institutionRow != null && WEB3PaymentApplyTriggerDef.ENFORCEMENT_CHECK.equals(
                l_institutionRow.getPaymentApplyTrigger()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (getDB時価余力チェック区分)<BR>
     * "DB時価余力チェック区分"を取得し返却する。<BR>
     * <BR>
     * １） 『証券会社テーブル』を以下条件で検索。<BR>
     * 　@　@（※レコードが取得出来なかった場合は例外をスローする）<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社テーブル.証券会社コード = 引数.証券会社コード<BR>
     * <BR>
     * ２） 1）の検索結果より、"DB時価余力チェック区分"を取得し返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@return String
     * @@throws WEB3BaseException
     */
    private String getDbCurrentPriceCheckDiv(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDbCurrentPriceCheckDiv(String)";
        log.entering(STR_METHOD_NAME);

        //"DB時価余力チェック区分"を取得し返却する
        //１） 『証券会社テーブル』を以下条件で検索。
        //　@　@（※レコードが取得出来なかった場合は例外をスローする）
        //　@　@　@[条件]
        //　@　@　@証券会社テーブル.証券会社コード = 引数.証券会社コード
        String l_strDbCurrentPriceCheckDiv = null;
        try
        {
            InstitutionRow l_institutionRow =
                (InstitutionRow)InstitutionDao.findRowByInstitutionCode(l_strInstitutionCode);
            if (l_institutionRow == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_strDbCurrentPriceCheckDiv = l_institutionRow.getDbCurrentPriceCheckDiv();
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２） 1）の検索結果より、"DB時価余力チェック区分"を取得し返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strDbCurrentPriceCheckDiv;
    }

    /**
     * (getトリガ発行件数)<BR>
     * システムプリファ@レンスより、出金余力チェック後のトリガ発行件数<BR>
     * を取得する。<BR>
     * <BR>
     * １）　@以下の条件で、システムプリファ@レンステーブルを検索する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 名称（環境変数名） = PAY_TRIGGER_ORDER_MAX_COUNT<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@（出金請求レコードトリガー発行件数Max値）<BR>
     * <BR>
     * ２）　@取得したレコードから、value を返却する。<BR>
     *      レコードを取得できなかった場合、99999を返却する。<BR>
     */
    private int getTriggerIssueNumber() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTriggerIssueNumber()"; 
        log.entering(STR_METHOD_NAME);
        
        String l_preference = WEB3SystemPreferencesNameDef.PAY_TRIGGER_ORDER_MAX_COUNT;
        // 以下の条件で、システムプリファ@レンステーブルを検索する。
        // [検索条件]
        // 名称（環境変数名） = PAY_TRIGGER_ORDER_MAX.COUNT
        SystemPreferencesRow l_systemPreferencerow;
        try
        {
            l_systemPreferencerow = (SystemPreferencesRow)SystemPreferencesDao.findRowByName(l_preference);
            if (l_systemPreferencerow != null)
            {
                int l_intValue = Integer.parseInt(l_systemPreferencerow.getValue());
                return l_intValue;
            }
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return 99999;
    }
}
@
