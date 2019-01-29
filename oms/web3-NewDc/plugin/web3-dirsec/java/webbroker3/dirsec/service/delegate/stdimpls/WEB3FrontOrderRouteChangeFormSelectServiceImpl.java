head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderRouteChangeFormSelectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注経路切替処理方式選択サービス実装クラス(WEB3FrontOrderRouteChangeFormSelectServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.116
*/
package webbroker3.dirsec.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3FrontOrderRouteChangeFormSelectService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者発注経路切替処理方式選択サービス)<BR>
 * <BR>
 * 管理者発注経路切替処理方式選択サービス実装クラス<BR>
 * <BR>
 * WEB3FrontOrderRouteChangeFormSelectServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3FrontOrderRouteChangeFormSelectServiceImpl implements WEB3FrontOrderRouteChangeFormSelectService{

    /**
    * Log Variable
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FrontOrderRouteChangeFormSelectServiceImpl.class);

    /**
     * @@roseuid 43001A2C02A3
     */
    public WEB3FrontOrderRouteChangeFormSelectServiceImpl() 
    {
    
    }
   
    /**
     * 管理者発注経路切替方式選択サービスを行う。<BR>
     * <BR>
     * リクエストデータを引数とし、<BR>
     * this.get選択画面()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 42D21DA00227
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3GenResponse l_response = null;
 
        // get選択画面をコールする。
        if (l_request instanceof WEB3AdminFrontChangeProcessSelectRequest)
        {
            l_response = getSelectScreen((WEB3AdminFrontChangeProcessSelectRequest) l_request);
        } 
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "INPUT リクエスト NOT 管理者発注経路切替サービスリクエスト");
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;            
    }
   
    /**
     * 管理者発注経路切替方式選択画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（発注経路切替方式選択）get選択画面」参照<BR>
     * @@param リクエストデータ - 管理者・発注経路切替選択リクエストオブジェクト<BR>
     * @@return 管理者・発注経路切替処理方式選択レスポンス<BR>
     * @@roseuid 42D21DC2017B
     */
    protected WEB3AdminFrontChangeProcessSelectResponse getSelectScreen(WEB3AdminFrontChangeProcessSelectRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getSelectScreen(WEB3AdminFrontChangeProcessSelectRequest  l_request)";
        log.entering(STR_METHOD_NAME);

        // ログイン情報インスタンス
        WEB3Administrator l_administrator = null;
        // 発注経路切替選択レスポンス
        WEB3AdminFrontChangeProcessSelectResponse l_response = null;
        
        // 1.1.ログイン情報インスタンス取得
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        // 1.2.validate権限チェック()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH,
            false);

        //1.3. isDIR管理者( )チェック DIR管理者でない場合、例外をスローする。
        boolean l_blnDir = l_administrator.isDirAdministrator();
        if (!l_blnDir)
        {
            log.error(STR_METHOD_NAME +
                WEB3ErrorCatalog.BUSINESS_ERROR_00857.error_message);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00857,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        
        // 1.4.レスポンスオブジェクト生成
        l_response = (WEB3AdminFrontChangeProcessSelectResponse) l_request.createResponse();
        
        // 1.5.レスポンスを返却
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
