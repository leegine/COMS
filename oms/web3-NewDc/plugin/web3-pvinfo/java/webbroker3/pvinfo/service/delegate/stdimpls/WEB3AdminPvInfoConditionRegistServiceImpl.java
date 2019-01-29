head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3AdminPvInfoConditionRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者表示設定登録サービスImpl(WEB3AdminPvInfoConditionRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/28 李弘毅(中訊) 作成
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputRequest;
import webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayConditionUnit;
import webbroker3.pvinfo.service.delegate.WEB3AdminPvInfoConditionRegistService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者表示設定登録サービスImpl)<BR>
 * 管理者表示設定登録サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3AdminPvInfoConditionRegistServiceImpl extends WEB3ClientRequestService implements WEB3AdminPvInfoConditionRegistService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPvInfoConditionRegistServiceImpl.class);

    /**
     * 管理者表示設定登録処理を行う。<BR>
     * <BR>
     * 引数の型により、以下のメソッドを呼び分ける<BR>
     * <BR>
     * ○管理者・表示設定登録入力リクエストの場合<BR>
     * 　@this.get表示設定登録入力画面()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・表示設定登録確認リクエストの場合<BR>
     * 　@this.validate表示設定登録()メソッドをコールする。<BR>
     * <BR>
     * ○管理者・表示設定登録完了リクエストの場合<BR>
     * 　@this.submit表示設定登録()メソッドをコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 415BFD8A0395
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminPvInfoConditionRegistInputRequest)
        {
            //管理者・表示設定登録入力リクエストの場合,this.get表示設定登録入力画面()メソッドをコールする。
            l_response = this.getConditionRegistInputScreen((WEB3AdminPvInfoConditionRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoConditionRegistConfirmRequest)
        {
            //管理者・表示設定登録確認リクエストの場合,this.validate表示設定登録()メソッドをコールする。
            l_response = this.validateConditionRegist((WEB3AdminPvInfoConditionRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminPvInfoConditionRegistCompleteRequest)
        {
            //管理者・表示設定登録完了リクエストの場合,this.submit表示設定登録()メソッドをコールする。
            l_response = this.submitConditionRegist((WEB3AdminPvInfoConditionRegistCompleteRequest)l_request);
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正、該当するWEB3AdminPvInfoConditionRegistInputRequest,"
                + "WEB3AdminPvInfoConditionRegistConfirmRequest, WEB3AdminPvInfoConditionRegistCompleteRequest類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get表示設定登録入力画面)<BR>
     * 表示設定登録入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定登録サービス)get表示設定登録入力画面」参照<BR>
     * ========================================================<BR>
     * create表示条件情報一覧(管理者)<BR>
     * <BR>
     * nullが返却された場合は、<BR>
     * 「表示条件設定なし」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01036<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定登録入力リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistInputResponse
     * @@roseuid 415BFDF3023C
     */
    protected WEB3AdminPvInfoConditionRegistInputResponse getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getConditionRegistInputScreen(WEB3AdminPvInfoConditionRegistInputRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 getInstanceFromログイン情報()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報を執行します");

        //2 validate権限(機@能コード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");

        //3 create表示条件情報一覧(管理者)
        WEB3PvInfoDisplayConditionUnit[] l_dispConditionUnits = l_dataManager.createDisplayConditionList(l_administrator);
        log.debug("create表示条件情報一覧を執行します");
        if (l_dispConditionUnits == null)
        {
            log.info("表示条件設定なし");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01036,
                getClass().getName() + STR_METHOD_NAME);
        }

        //4 createResponse()
        WEB3AdminPvInfoConditionRegistInputResponse l_response = (WEB3AdminPvInfoConditionRegistInputResponse)l_request.createResponse();

        //5 プロパティセット
        l_response.displayConditionUnits = l_dispConditionUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate表示設定登録)<BR>
     * 表示設定登録確認処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定登録サービス)validate表示設定登録」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定登録確認リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistConfirmResponse
     * @@roseuid 415BFDF3024C
     */
    protected WEB3AdminPvInfoConditionRegistConfirmResponse validateConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConditionRegist(WEB3AdminPvInfoConditionRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();
        log.debug("validateを執行します");

        //2 getInstanceFromログイン情報()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報を執行します");
        
        //3 validate権限(機@能コード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");

        //4 get新規表示内容ID()
        long l_lngNewDisplayContentsId = l_dataManager.getNewDisplayContentsId();
        log.debug("get新規表示内容ID = " + l_lngNewDisplayContentsId);
        
        //5 createResponse()
        WEB3AdminPvInfoConditionRegistConfirmResponse l_response = (WEB3AdminPvInfoConditionRegistConfirmResponse)l_request.createResponse();

        //6 プロパティセット
        l_response.displayContentsId = WEB3StringTypeUtility.formatNumber(l_lngNewDisplayContentsId);
        log.debug("表示内容ID = " + l_response.displayContentsId);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit表示設定登録)<BR>
     * 表示設定登録完了処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(管理者表示設定登録サービス)submit表示設定登録」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・表示設定登録完了リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3AdminPvInfoConditionRegistCompleteResponse
     * @@roseuid 415BFDF3026B
     */
    protected WEB3AdminPvInfoConditionRegistCompleteResponse submitConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitConditionRegist(WEB3AdminPvInfoConditionRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3Administrator l_administrator = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1 validate()
        l_request.validate();
        log.debug("validateを執行します");
        
        //2 getInstanceFromログイン情報()
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        log.debug("getInstanceFromログイン情報を執行します");

        //3 validate権限(機@能コード : String, is更新 : boolean)
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.PRIVATE_INFO, true);
        log.debug("validate権限を執行します");

        //4 validate取引パスワード(String)
        l_administrator.validateTradingPassword(l_request.password);
        log.debug("validate取引パスワードを執行します");

        //5  insert表示内容(管理者, 表示内容情報)
        l_dataManager.insertDisplayContents(l_administrator, l_request.displayContentsUnit);
        log.debug("insert表示内容を執行します");

        //6 createResponse()
        WEB3AdminPvInfoConditionRegistCompleteResponse l_response = (WEB3AdminPvInfoConditionRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
