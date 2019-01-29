head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定変更サービスImpl(WEB3AdminPvInfoConditionChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/28 李丁銀(中訊) 作成
Revesion History : 2004/11/02 奚靖(中訊) 修正
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionChangeService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者表示設定変更サービスImpl)<BR>
 * 管理者表示設定変更サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionChangeServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionChangeService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionChangeServiceImpl.class);

    /**
     * 表示設定変更処理を行う。<BR>
     * <BR>
     * 引数の型により、以下のメソッドを呼び分ける<BR>
     * <BR>
     * ○管理者・表示設定変更入力リクエストの場合<BR>
     * 　@this.get表示設定変更入力画面()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・表示設定変更確認リクエストの場合<BR>
     * 　@this.validate表示設定変更()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・表示設定変更完了リクエストの場合<BR>
     * 　@this.submit表示設定変更()メソッドをコールする。<BR>
     *
     * @@param l_request
     *  - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@throws WEB3BaseException
     * @@roseuid 415D1378036B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if(l_request instanceof WEB3AdminPvInfoConditionChangeInputRequest)
        {
            l_response = this.getConditionChangeInputScreen((WEB3AdminPvInfoConditionChangeInputRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionChangeConfirmRequest)
        {
            l_response = this.validateConditionChange((WEB3AdminPvInfoConditionChangeConfirmRequest)l_request);
        }
        else if(l_request instanceof WEB3AdminPvInfoConditionChangeCompleteRequest)
        {
            l_response = this.submitConditionChange((WEB3AdminPvInfoConditionChangeCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorInfo =
                "パラメータの類型が不正、WEB3AdminPvInfoConditionChangeInputRequest," +
                "WEB3AdminPvInfoConditionChangeConfirmRequest," +
                "WEB3AdminPvInfoConditionChangeCompleteRequest類型。";
            log.error(l_strErrorInfo);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get表示設定変更入力画面)<BR>
     * 表示設定変更入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定変更サービス)get表示設定変更入力画面」参照<BR>
     * ========================================================<BR>
     * get表示内容Params(表示内容ID：　@long)<BR>
     * <BR>
     *nullが返却された場合は、<BR>
     *「表示条件設定なし」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01036<BR>
     * nullが返却された場合は、<BR>
     * 「該当データ未存在」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01037<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定変更入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeInputResponse
     * @@roseuid 415D13C001B6
     */
    protected WEB3AdminPvInfoConditionChangeInputResponse getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConditionChangeInputScreen(WEB3AdminPvInfoConditionChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionChangeInputResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validateを執行します");
        
        //1.2.getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報を執行します");

        //1.3.validate権限(機@能コード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");



        //1.4表示条件情報の一覧を作成する。 
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        
        WEB3PvInfoDisplayConditionUnit[] l_conditonUnits = l_dataMgr.createDisplayConditionList(l_admin);
        if (l_conditonUnits == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01036.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01036,
                getClass().getName() + "." + STR_METHOD_NAME);
            
        }
        //1.5.get表示内容Params(long)
        long l_lngDisplayContentsID = Long.parseLong(l_request.displayContentsId);
        log.debug("表示内容ID =" + l_lngDisplayContentsID);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.getDisplayContentsParams(l_lngDisplayContentsID);
        log.debug("get表示内容Paramsを執行します");
        if(l_displayContentsParams == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01037.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.表示内容情報()
        WEB3PvInfoDisplayContentsUnit l_displayContentsUnit = new WEB3PvInfoDisplayContentsUnit();

        //1.7.(*)プロパティセット
        l_displayContentsUnit.displayContentsId =
            WEB3StringTypeUtility.formatNumber(l_displayContentsParams.display_contents_id);
        log.debug("表示内容ID =" + l_displayContentsUnit.displayContentsId);
        
        l_displayContentsUnit.conditionNumber = l_displayContentsParams.condition_no;
        log.debug("表示条件番号 =" + l_displayContentsUnit.conditionNumber);
        
        l_displayContentsUnit.priorityDiv = l_displayContentsParams.priority_div;
        log.debug("優先区分 =" + l_displayContentsUnit.priorityDiv);
        
        l_displayContentsUnit.listStartDate =WEB3DateUtility.formatDate(l_displayContentsParams.term_from, "yyyyMMddHHmm");
        log.debug("表示期間From =" + l_displayContentsUnit.listStartDate);
        
        l_displayContentsUnit.listEndDate =WEB3DateUtility.formatDate(l_displayContentsParams.term_to, "yyyyMMddHHmm");
        log.debug("表示期間To =" + l_displayContentsUnit.listEndDate);
        
        l_displayContentsUnit.displayTitle = l_displayContentsParams.display_title;
        log.debug("表示タイトル =" + l_displayContentsUnit.displayTitle);
        
        l_displayContentsUnit.displayMessage = l_displayContentsParams.display_message;
        log.debug("表示文章 =" + l_displayContentsUnit.displayMessage);
        
        l_displayContentsUnit.displayColor = l_displayContentsParams.display_color;
        log.debug("表示色 =" + l_displayContentsUnit.displayColor);
        
        log.debug("点滅表示フラグ =" + l_displayContentsParams.blink_display_flag);
        if(WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_displayContentsParams.blink_display_flag))
        {
            l_displayContentsUnit.blinkDisplayFlag = true;
        }
        else
        {
            l_displayContentsUnit.blinkDisplayFlag = false;
        }

        l_displayContentsUnit.displayUrl = l_displayContentsParams.display_url;
        log.debug("URL指定 =" + l_displayContentsUnit.displayUrl);
        
        log.debug("最終更新日時表示フラグ =" + l_displayContentsParams.last_update_time_display_flag);
        if(WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_displayContentsParams.last_update_time_display_flag))
        {
            l_displayContentsUnit.lastUpdateTimeDisplayFlag = false;
        }
        else
        {
            l_displayContentsUnit.lastUpdateTimeDisplayFlag = true;
        }

        log.debug("有効/無効フラグ =" + l_displayContentsParams.effective_flag);
        if(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_displayContentsParams.effective_flag))
        {
            l_displayContentsUnit.effectiveFlag = false;
        }
        else
        {
            l_displayContentsUnit.effectiveFlag = true;
        }
        
        l_displayContentsUnit.displayDevice = l_displayContentsParams.display_device;
        log.debug("表示媒体 =" + l_displayContentsUnit.displayDevice);
        
        l_displayContentsUnit.lastUpdateMember = l_displayContentsParams.last_update_member;
        log.debug("最終更新者 =" + l_displayContentsUnit.lastUpdateMember);
        
        l_displayContentsUnit.lastUpdatedTimestamp = l_displayContentsParams.last_updated_timestamp;
        log.debug("最終更新日時 =" + l_displayContentsUnit.lastUpdatedTimestamp);

        //1.8.createResponse
        l_response = (WEB3AdminPvInfoConditionChangeInputResponse)l_request.createResponse();

        //1.9.(*)プロパティセット
        l_response.displayContentsUnit = l_displayContentsUnit;
        l_response.displayConditionUnits = l_conditonUnits;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate表示設定変更)<BR>
     * 表示設定変更確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定変更サービス)validate表示設定変更」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定変更確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeConfirmResponse
     * @@roseuid 415D13C001D5
     */
    protected WEB3AdminPvInfoConditionChangeConfirmResponse validateConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConditionChange(WEB3AdminPvInfoConditionChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionChangeConfirmResponse l_response = null;

        //1.1validate()
        l_request.validate();
        log.debug("validateを執行します");

        //1.2.getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報を執行します");

        //1.3.validate権限(機@能コード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");

        //1.4.createResponse()
        l_response = (WEB3AdminPvInfoConditionChangeConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit表示設定変更)<BR>
     * 表示設定変更完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定変更サービス)submit表示設定変更」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定変更完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionChangeCompleteResponse
     * @@roseuid 415D13C001E5
     */
    protected WEB3AdminPvInfoConditionChangeCompleteResponse submitConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitConditionChange(WEB3AdminPvInfoConditionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPvInfoConditionChangeCompleteResponse l_response = null;

        //1.1.validate()
        l_request.validate();
        log.debug("validateを執行します");
        
        //1.2.getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報を執行します");

        //1.3.validate権限(機@能コード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");

        //1.4.validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);
        log.debug("validate取引パスワードを執行します");

        //1.5.create表示内容Params(表示内容情報)
        WEB3PvInfoDataManager l_dataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        DisplayContentsParams l_displayContentsParams = l_dataMgr.createDisplayContentsParams(l_request.displayContentsUnit);
        log.debug("create表示内容Paramsを執行します");
        if(l_displayContentsParams == null)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.SYSTEM_ERROR_80006.error_message);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.6.update表示内容(表示内容Params)
        l_dataMgr.updateDisplayContents(l_displayContentsParams);
        log.debug("update表示内容 を執行します");

        //1.7.createResponse();
        l_response = (WEB3AdminPvInfoConditionChangeCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
