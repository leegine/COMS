head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認入力サービス実装クラス(WEB3AdminAioCashinConfirmInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 韋念瓊 (中訊) 新規作成   
                   2004/10/25 屈陽 (中訊) レビュー                 
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmInputResponse;
import webbroker3.aio.message.WEB3AioFinancialInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinConfirmInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連絡確認入力サービスImpl)<BR>
 * 入金連絡確認入力サービス実装クラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashinConfirmInputServiceImpl extends WEB3ClientRequestService implements WEB3AdminAioCashinConfirmInputService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinConfirmInputServiceImpl.class);
        
    /**
     * 入金連絡確認入力サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入金連絡確認入力）入力画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4107499D0136
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3AdminAioCashinConfirmInputResponse l_adminAioCashinConfirmInputResponse = null;        

        //1.1 前日営業日を取得する。 
        //［引数］ 
        //基準日： システムタイムスタンプ 
        //加算／減算日数： -1        
        
        Date l_datBizDate = new WEB3GentradeBizDate(
            GtlUtils.getTradingSystem().getSystemTimestamp()).roll(-1);
            
        log.debug("前日営業日 = " + l_datBizDate);
        
        //1.2 入金の受付締切時間を取得する。 

        //［引数］ 
        //市場コード： 0（DEFAULT) 
        //商品コード： 0（DEFAULT) 
        String l_strTradeCloseTime =
            WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                WEB3MarketCodeDef.DEFAULT, WEB3ProductCodeDef.DEFAULT);
        log.debug("入金の受付締切時間 = " + l_strTradeCloseTime);
        
        //1.3 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
               
        //1.4 証券会社コードを取得する。
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        log.debug("証券会社コード = " + l_strInstitutionCode);     
        
        //1.5 部店コードを取得する。
        String l_strBranchCode = l_web3Administrator.getBranchCode();
        log.debug("部店コード = " + l_strBranchCode);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_web3AioOrderMgr =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();
                   
        //1.6 該当の証券会社で利用可能な振込先金融機@関明細リストを取得する。 
        //［引数］ 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //部店コード： null
        
        WEB3AioFinancialInstitutionUnit[] l_web3AioFinancialInstitutionUnits = 
            l_web3AioOrderMgr.createFinancialInstitutionDetails(
                l_strInstitutionCode, null);
        
        //1.7 レスポンスデータを生成する。
        l_adminAioCashinConfirmInputResponse = (WEB3AdminAioCashinConfirmInputResponse) 
            l_request.createResponse();
        
        //1.8  プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。
        
        //レスポンス.部店コード = 管理者.get部店コード()の戻り値
        l_adminAioCashinConfirmInputResponse.branchCode = l_strBranchCode;
        log.debug("レスポンス.部店コード = " + 
                l_adminAioCashinConfirmInputResponse.branchCode);
        
        //レスポンス.連絡日時（自） = （以下のとおり）
        //    営業日計算.calc営業日()で取得した前日営業日の日付と
        //    取引時間管理.get市場閉局時間()で取得した入金受付締切時刻を編集したもの
        
        //=======remain zhou-yong NO.1 2004/12/14 begin ========
        
        l_adminAioCashinConfirmInputResponse.minNoticeDate = 
            WEB3DateUtility.getDate(WEB3DateUtility.formatDate(
                l_datBizDate, "yyyyMMdd") + l_strTradeCloseTime, 
                    "yyyyMMddHHmmss");

        log.debug("レスポンス.連絡日時（自） = " + 
                l_adminAioCashinConfirmInputResponse.minNoticeDate);
        
        //レスポンス.連絡日時（至） = （以下のとおり）
        //    システムタイムスタンプから取得した当日営業日の日付と
        //    取引時間管理.get市場閉局時間()で取得した入金受付締切時刻を編集したもの
        
        l_adminAioCashinConfirmInputResponse.maxNoticeDate =
            WEB3DateUtility.getDate(WEB3DateUtility.formatDate(
                GtlUtils.getTradingSystem().getSystemTimestamp(), "yyyyMMdd") + 
                    l_strTradeCloseTime, "yyyyMMddHHmmss");
        
        //=======remain zhou-yong NO.1 2004/12/14 end ========
        
        log.debug("レスポンス.連絡日時（至） = " + 
                l_adminAioCashinConfirmInputResponse.maxNoticeDate);
        
        //レスポンス.振込先金融機@関一覧 = 入出金注文マネージャ.create振込先金融機@関明細()の戻り値
        l_adminAioCashinConfirmInputResponse.financialInstitutionUnits = 
            l_web3AioFinancialInstitutionUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashinConfirmInputResponse;
    }
}
@
