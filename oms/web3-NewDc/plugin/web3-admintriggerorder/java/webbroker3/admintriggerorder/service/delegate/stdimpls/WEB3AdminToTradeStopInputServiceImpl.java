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
filename	WEB3AdminToTradeStopInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止入力サービスImpl(WEB3AdminToTradeStopInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止入力サービスImpl)<BR>
 * トリガー注文管理者・取扱停止入力サービス実装クラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInputServiceImpl implements WEB3AdminToTradeStopInputService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopInputServiceImpl.class);
    
    /**
     * @@roseuid 4430DD35034B
     */
    public WEB3AdminToTradeStopInputServiceImpl() 
    {
     
    }
    
    /**
     * 取扱停止入力画面表示処理を行う。<BR>
     * <BR>
     * this.get入力画面()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44192D0102EE
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
        if (l_request instanceof WEB3AdminToTradeStopInputRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToTradeStopInputRequest) l_request);
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
     * (get入力画面)<BR>
     * 取扱停止入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止入力サービス）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止入力リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 44192D7F0186
     */
    protected WEB3AdminToTradeStopInputResponse getInputScreen(WEB3AdminToTradeStopInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToTradeStopInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2.validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, false);
        
        //1.3 createResponse( )
        WEB3AdminToTradeStopInputResponse l_response = 
            (WEB3AdminToTradeStopInputResponse) l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
