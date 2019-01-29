head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopDeleteServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止削除サービスImpl(WEB3AdminToTradeStopDeleteServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@鄭徳懇(中訊) 新規作成
*/
package webbroker3.admintriggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopDeleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopDao;
import webbroker3.triggerorder.base.data.TriggerOrderStopParams;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止削除サービスImpl)<BR>
 * トリガー注文管理者・取扱停止削除サービス実装クラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminToTradeStopDeleteServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopDeleteService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopDeleteServiceImpl.class);

    /**
     * @@roseuid 4430DB3E032C
     */
    public WEB3AdminToTradeStopDeleteServiceImpl() 
    {
     
    }
    
    /**
     * 取扱停止削除処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○トリガー注文管理者・取扱停止削除確認リクエストの場合<BR>
     * 　@this.validate削除()をコールする。<BR>
     * <BR>
     * ○トリガー注文管理者・取扱停止削除完了リクエストの場合<BR>
     * 　@this.submit削除()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D2DC01E2
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        // ○トリガー注文管理者・取扱停止削除確認リクエストの場合
        // 　@this.validate削除()をコールする。
        if (l_request instanceof WEB3AdminToTradeStopDelConfirmRequest)
        {
            l_response = this.validateDelete((WEB3AdminToTradeStopDelConfirmRequest) l_request);
        }
        // ○トリガー注文管理者・取扱停止削除完了リクエストの場合
        //　@this.submit削除()をコールする。
        else if (l_request instanceof WEB3AdminToTradeStopDelCompleteRequest)
        {
            l_response = this.submitDelete((WEB3AdminToTradeStopDelCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate削除)<BR>
     * 取扱停止削除確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止削除サービス）validate削除」参照。<BR>
     * ============================================================== <BR>
     *    シーケンス図：「（トリガー注文管理者・取扱停止削除サービス）validate削除」<BR>
     *    具体位置：<BR>
     *    1.4 (*)特殊執行条件取扱停止Rowの取得<BR>
     *    リクエストデータ.特殊執行条件取扱停止IDに<BR>
     *    該当する特殊執行条件取扱停止Rowを取得する。<BR>
     *    ※Daoクラスのメソッドを使用。<BR>
     *    取得できなかった場合、該当データなしのシステムエラーをスローする。<BR>
     *    class : WEB3SystemLayerException<BR>
     *    tag : SYSTEM_ERROR_80005<BR>
     * ============================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止削除確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopDelConfirmResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4410D2BF009A
     */
    protected WEB3AdminToTradeStopDelConfirmResponse validateDelete(WEB3AdminToTradeStopDelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDelete(WEB3AdminToTradeStopDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);

        //1.4 (*)特殊執行条件取扱停止Rowの取得
        //リクエストデータ.特殊執行条件取扱停止IDに
        //該当する特殊執行条件取扱停止Rowを取得する。
        //※Daoクラスのメソッドを使用。
        //取得できなかった場合、該当データなしのシステムエラーをスローする。
        TriggerOrderStopRow l_row = null;
        try
        {
            l_row = TriggerOrderStopDao.findRowByTriggerOrderStopId(Long.parseLong(l_request.triggerTradeStopId));
            if (l_row == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "テーブルに該当するデータがありません。");
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.5 get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.6 create取扱停止情報一覧(証券会社, 特殊執行条件取扱停止Row[])
        TriggerOrderStopRow[] l_rows = new TriggerOrderStopRow[1];
        l_rows[0] = l_row;
        WEB3AdminToTradeStopInfoUnit[] l_units = WEB3AdminToDataManager.createTradeStopInfoList(l_institution, l_rows);
        
        //1.7 createResponse( )
        WEB3AdminToTradeStopDelConfirmResponse l_response = 
            (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
        
        //1.8 (*)プロパティセット
        //取扱停止情報    ＝　@create取扱停止情報一覧()の戻り値の0番目の要素
        l_response.tradeStopInfoUnit = l_units[0];
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit削除)<BR>
     * 取扱停止削除完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止削除サービス）submit削除」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止削除完了リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopDelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410D2BF00F8
     */
    protected WEB3AdminToTradeStopDelCompleteResponse submitDelete(WEB3AdminToTradeStopDelCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitDelete(WEB3AdminToTradeStopDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, true);

        //1.4 validate取引パスワード(パスワード : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 delete取扱停止(管理者, long)
        this.deleteTradeStop(l_admin, Long.parseLong(l_request.triggerTradeStopId));
        
        //1.6 createResponse( )
        WEB3AdminToTradeStopDelCompleteResponse l_response = 
            (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
        
        //レスポンス生成後、現在時刻をセットする。
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (delete取扱停止)<BR>
     * 引数のIDに該当する特殊執行条件取扱停止<BR>
     * テーブルのレコードを削除する。<BR>
     * ※論理削除<BR>
     * <BR>
     * １）　@以下の条件に該当するレコードをupdateする。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@特殊執行条件取扱停止ID =<BR>
     * 　@　@　@パラメータ.特殊執行条件取扱停止ID<BR>
     * <BR>
     * 　@[更新内容]<BR>
     * 　@　@DB更新仕様<BR>
     * 　@　@「取扱停止削除_特殊執行条件取扱停止テーブル仕様.xls」参照。<BR>
     * @@param l_administrator - (管理者)<BR>
     * 管理者<BR>
     * @@param l_lngTriggerOrderStopId - (特殊執行条件取扱停止ID)<BR>
     * 特殊執行条件取扱停止ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4417D2FE023A
     */
    protected void deleteTradeStop(WEB3Administrator l_administrator, long l_lngTriggerOrderStopId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteTradeStop(WEB3Administrator, long)";
        log.entering(STR_METHOD_NAME);
        
        // １）　@以下の条件に該当するレコードをupdateする。
        try
        {
            TriggerOrderStopRow l_row = 
                (TriggerOrderStopRow) TriggerOrderStopDao.findRowByTriggerOrderStopId(l_lngTriggerOrderStopId);
            
            if (l_row == null)
            {
                log.debug("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "テーブルに該当するデータがありません。");
            }
            
            TriggerOrderStopParams l_params = new TriggerOrderStopParams(l_row);
            //削除フラグ = 1：削除済
            l_params.setDeleteFlag(BooleanEnum.TRUE.intValue());
            //更新者コード = 管理者.管理者コード
            l_params.setLastUpdater(l_administrator.getAdministratorCode());
            //更新日付 = 現在時刻
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            Processors.getDefaultProcessor().doUpdateQuery(l_params);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
