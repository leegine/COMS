head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeOnlineRunStatus.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン実行結果(WEB3GentradeOnlineRunStatus)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/5/6 王暁傑 (中訊) 新規作成
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.gentrade.data.OnlineRunStatusDao;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (オンライン実行結果)<BR>
 * 負荷分散を行うオンライン処理の実行結果を管理する。<BR>
 * <BR>
 * （DBレイアウト 「オンライン実行結果テーブル仕様.xls」参照）<BR>
 * 
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3GentradeOnlineRunStatus implements BusinessObject 
{
    /**
     * ログ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3GentradeOnlineRunStatus.class);
    
    /**
     * オンライン実行結果Rowオブジェクト。
     */
    private OnlineRunStatusRow onlineRunStatusRow;
    
    /**
     * (オンライン実行結果)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、<BR>
     * 引数のRowオブジェクトをプロパティにセットする。<BR>
     * @@param l_onlineRunStatusRow - オンライン実行結果Rowオブジェクト
     * @@return WEB3GentradeOnlineRunStatus
     * @@roseuid 42798D330347
     */
    public WEB3GentradeOnlineRunStatus(OnlineRunStatusRow l_onlineRunStatusRow) 
    {
        this.onlineRunStatusRow = l_onlineRunStatusRow;
    }
    
    /**
     * this.オンライン実行結果Rowを返却する。
     * @@return Object
     * @@roseuid 42798D330328
     */
    public Object getDataSourceObject() 
    {
        return this.onlineRunStatusRow;
    }
    
    /**
     * (set処理中)<BR>
     * （staticメソッド）<BR>
     * 指定内容でオンライン実行結果テーブルを検索し、<BR>
     * 「処理中」ステータスで１レコードの登録（データなし時）／<BR>
     * 更新（データあり時）を行い、<BR>
     * 登録／更新レコードに該当するオンライン実行結果オブジェクトを返す。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@指定内容で、オンライン実行結果テーブルを検索する。<BR>
     * <BR>
     * 　@-----------------------------------------------------<BR>
     * 　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@証券会社コード = 引数の証券会社コード<BR>
     * 　@かつ　@銘柄タイプ = 引数の銘柄タイプ<BR>
     * 　@かつ　@先物／オプション区分 = 引数の先物／オプション区分<BR>
     * 　@かつ　@オンラインサービス区分 = 引数のオンラインサービス区分<BR>
     * 　@かつ　@From口座ID = 引数のFrom口座ID<BR>
     * 　@-----------------------------------------------------<BR>
     * <BR>
     * ２）　@該当データの有無により、以下の通り分岐する。<BR>
     * <BR>
     * ２－１）　@該当データなしの場合<BR>
     * <BR>
     * 　@・指定内容で、オンライン実行結果テーブルに１レコード登録する。<BR>
     * <BR>
     * 　@　@-----------------------------------------------------<BR>
     * 　@　@＜オンライン実行結果テーブル：登録内容＞<BR>
     * <BR>
     * 　@　@証券会社コード：　@引数の証券会社コード<BR>
     * 　@　@銘柄タイプ：　@引数の銘柄タイプ<BR>
     * 　@　@先物／オプション区分：　@引数の先物／オプション区分<BR>
     * 　@　@オンラインサービス区分：　@引数のオンラインサービス区分<BR>
     * 　@　@From口座ID：　@引数のFrom口座ID<BR>
     * 　@　@To口座ID：　@引数のTo口座ID<BR>
     * 　@　@処理区分：　@"処理中"<BR>
     *     作成日付：　@GtlUtils.getSystemTimestamp()<BR>
     *     更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * 　@　@-----------------------------------------------------<BR>
     * <BR>
     * 　@・登録レコードのオンライン実行結果Rowオブジェクトを指定し、<BR>
     * 　@　@オンライン実行結果オブジェクトを生成する。<BR>
     * 　@　@生成したオンライン実行結果オブジェクトを返却する。<BR>
     * <BR>
     * ２－２）　@該当データありの場合<BR>
     * <BR>
     * 　@・取得したレコード.実行ステータス区分 = "処理中"の場合は、<BR>
     * 　@　@「指定AP起動中（二重起動エラー）」の例外をthrowする。<BR>
     *　@　@　@tag: BUSINESS_ERROR_01992
     *　@　@　@class: WEB3BusinessLayerException
     * <BR>
     * 　@・取得したレコード.実行ステータス区分 !=  "処理中"の場合は、<BR>
     *　@　@以下の処理を行う。<BR>
     * <BR>
     * 　@　@（１）検索結果のオンライン実行結果Rowオブジェクトを指定し、<BR>
     * 　@　@　@　@　@オンライン実行結果オブジェクトを生成する。<BR>
     * 　@　@（２）生成したオンライン実行結果オブジェクト.<BR>
     * 　@　@　@　@　@　@　@　@update実行ステータス区分("処理中")を実行する。<BR>
     * 　@　@（３）生成したオンライン実行結果オブジェクトを返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード。
     * @@param l_productType - 銘柄タイプ。
     * @@param l_strFutureOptionDiv - 先物／オプション区分。<BR>
     * （0：DEFAULT　@1：先物　@2：オプション）<BR>
     * @@param l_strOnlineServiceDiv - オンラインサービス区分。<BR>
     * （1：出来終了通知　@2：注文繰越）<BR>
     * @@param l_lngAccountIdFrom - From口座ID。
     * @@param l_lngAccountIdTo - To口座ID。
     * @@return webbroker3.gentrade.WEB3GentradeOnlineRunStatus
     * @@roseuid 42799B2E00FD
     */
    public static WEB3GentradeOnlineRunStatus setDealing(
            String l_strInstitutionCode, 
            ProductTypeEnum l_productType, 
            String l_strFutureOptionDiv, 
            String l_strOnlineServiceDiv, 
            long l_lngAccountIdFrom, 
            long l_lngAccountIdTo)
                throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setDealing";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeOnlineRunStatus l_result = null;
//        １）　@DB検索
//       　@指定内容で、オンライン実行結果テーブルを検索する。
//
//       　@-----------------------------------------------------
//       　@＜検索条件＞
//
//       　@　@　@証券会社コード = 引数の証券会社コード
//       　@かつ　@銘柄タイプ = 引数の銘柄タイプ
//       　@かつ　@先物／オプション区分 = 引数の先物／オプション区分
//       　@かつ　@オンラインサービス区分 = 引数のオンラインサービス区分
//       　@かつ　@From口座ID = 引数のFrom口座ID
//       　@-----------------------------------------------------
        
        boolean l_blnIsDataExist = false;
        OnlineRunStatusRow l_onlineRunStatusRow = null;
        try
        {
            try
            {
                l_onlineRunStatusRow =
                    (OnlineRunStatusRow)OnlineRunStatusDao.findRowByInstitutionCodeProductTypeFutureOptionDivOnlineServiceDivAccountIdFrom(
                        l_strInstitutionCode,
                        l_productType,
                        l_strFutureOptionDiv,
                        l_strOnlineServiceDiv,
                        l_lngAccountIdFrom);
            }
            catch (DataFindException l_dfex)
            {
                l_blnIsDataExist = false;
                
                log.debug("Data not found  from online_run_status with " + 
                        " 証券会社コード =  " + l_strInstitutionCode + 
                        " 銘柄タイプ = " + l_productType + 
                        " 先物／オプション区分 = " + l_strFutureOptionDiv + 
                        " オンラインサービス区分 = " + l_strOnlineServiceDiv + 
                        " From口座ID = " + l_lngAccountIdFrom);
            }
                        
            if (l_onlineRunStatusRow == null)
            {
                l_blnIsDataExist = false;
            }
            else
            {
                l_blnIsDataExist = true;
            }
                        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            if (!l_blnIsDataExist)
            {
                //２－１）　@該当データなしの場合
                
                //・指定内容で、オンライン実行結果テーブルに１レコード登録する。
                OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
                
//              　@　@-----------------------------------------------------
//                
//              　@　@＜オンライン実行結果テーブル：登録内容＞
//              　@　@証券会社コード：　@引数の証券会社コード
//              　@　@銘柄タイプ：　@引数の銘柄タイプ
//              　@　@先物／オプション区分：　@引数の先物／オプション区分
//              　@　@オンラインサービス区分：　@引数のオンラインサービス区分
//              　@　@From口座ID：　@引数のFrom口座ID
//              　@　@To口座ID：　@引数のTo口座ID
//              　@　@処理区分：　@"処理中"
//                  作成日付：　@GtlUtils.getSystemTimestamp()
//                  更新日付：　@GtlUtils.getSystemTimestamp()
//              　@　@-----------------------------------------------------
                l_onlineRunStatusParams.setInstitutionCode(l_strInstitutionCode);
                l_onlineRunStatusParams.setProductType(l_productType);
                l_onlineRunStatusParams.setFutureOptionDiv(l_strFutureOptionDiv);
                l_onlineRunStatusParams.setOnlineServiceDiv(l_strOnlineServiceDiv);
                l_onlineRunStatusParams.setAccountIdFrom(l_lngAccountIdFrom);
                l_onlineRunStatusParams.setAccountIdTo(l_lngAccountIdTo);
                l_onlineRunStatusParams.setRunStatusDiv(WEB3RunStatusDivDef.DEALING);    
                l_onlineRunStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                l_onlineRunStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                l_queryProcessor.doInsertQuery(l_onlineRunStatusParams);
                
//               　@・登録レコードのオンライン実行結果Rowオブジェクトを指定し、
//              　@　@オンライン実行結果オブジェクトを生成する。
//              　@　@生成したオンライン実行結果オブジェクトを返却する。
                l_result = new WEB3GentradeOnlineRunStatus(l_onlineRunStatusParams);
                
            }
            else
            {
                //該当データありの場合
                
                if (WEB3RunStatusDivDef.DEALING.equals(l_onlineRunStatusRow.getRunStatusDiv()))
                {
                    //・取得したレコード.実行ステータス区分 = "処理中"の場合は、
                    //「指定AP起動中（二重起動エラー）」の例外をthrowする。
                    throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01992,
                            STR_METHOD_NAME);                    
                }
                else
                {
                    //取得したレコード.実行ステータス区分 != "処理中"の場合は、以下の処理を行う。
                    //（１）検索結果のオンライン実行結果Rowオブジェクトを指定し、
                    //    オンライン実行結果オブジェクトを生成する。
                    l_result = new WEB3GentradeOnlineRunStatus(l_onlineRunStatusRow);
                    
                    //（２）生成したオンライン実行結果オブジェクト.update実行ステータス区分("処理中")を実行する。
                    l_result.updateRunStatusDiv(WEB3RunStatusDivDef.DEALING);
                                     
                }                                
            }
        }
        catch (DataQueryException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }        
        catch (DataNetworkException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (update実行ステータス区分)<BR>
     * オブジェクトプロパティのRowに該当するオンライン実行結果テーブルのレコードの<BR>
     * 実行ステータス区分を、指定値で更新する。<BR>
     * <BR>
     * １）プロパティのRowオブジェクトに該当するオンライン実行結果テーブルの１レコードを<BR>
     * 　@　@　@更新する。<BR>
     * <BR>
     * 　@-----------------------------------------------------<BR>
     * 　@＜オンライン実行結果テーブル：更新値設定内容＞<BR>
     * <BR>
     * 　@実行ステータス区分：　@引数の実行ステータス区分<BR>
     * 　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * 　@-----------------------------------------------------<BR>
     * <BR>
     * ２）　@returnする。<BR>
     * @@param l_strRunStatusDiv - 実行ステータス区分。<BR>
     * （0：未処理　@1：処理済　@5：処理中　@9：エラー）<BR>
     * @@roseuid 4279A1BB0354
     */
    public void updateRunStatusDiv(String l_strRunStatusDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateRunStatusDiv(String l_strRunStatusDiv) ";
        log.entering(STR_METHOD_NAME);
        
        //プロパティのRowオブジェクトに該当するオンライン実行結果Paramsオブジェクトを生成
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams(this.onlineRunStatusRow);
        
        //更新値設定内容
        //実行ステータス区分：　@引数の実行ステータス区分
        l_onlineRunStatusParams.setRunStatusDiv(l_strRunStatusDiv);
        //更新日付：　@GtlUtils.getSystemTimestamp()
        l_onlineRunStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        this.onlineRunStatusRow = l_onlineRunStatusParams;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            l_queryProcessor.doUpdateQuery(l_onlineRunStatusParams);
        }
        catch (DataQueryException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }        
        catch (DataNetworkException l_dfex)
        {
            log.error(STR_METHOD_NAME, l_dfex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfex.getMessage(),
                l_dfex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
