head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FrontOrderSwitchManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (発注経路切替処理) (WEB3FrontOrderSwitchManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
Revesion History : 2007/02/17  李木子 (中訊) 実装の問題No.002-004
Revesion History : 2007/02/28  吉麗ナ (中訊) ＤＢ更新仕様No.005-007
Revesion History : 2007/02/28  孟亜南 (中訊) 仕様変更モデルNo.061-089 No.096 No.097
Revesion History : 2007/02/28  孟亜南 (中訊) 実装の問題No.010
Revesion History : 2007/06/26  周墨洋 (中訊) 実装の問題No.012
Revesion History : 2008/12/05  劉剣 (中訊) モデルNo.142 No.144 No.146
Revesion History : 2009/02/25  李玉玲 (中訊) ＤＢ更新仕様No.013
Revesion History : 2009/03/02  李玉玲 (中訊) ＤＢ更新仕様No.014
Revesion History : 2009/04/03  張騰宇 (中訊) モデルNo.152
*/
package webbroker3.dirsec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3ChangeReqResDivDef;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3NoticeTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.dirsec.define.WEB3AdminFrontDataCodeDef;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.data.MarketNoticeManagementRow;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeDao;
import webbroker3.gentrade.data.VirtualServerChangeParams;
import webbroker3.gentrade.data.VirtualServerInformationParams;
import webbroker3.gentrade.data.VirtualServerInformationRow;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3LogUtility;

/**
 * 発注経路切替処理クラス<BR>
 * <BR>
 * WEB3FrontOrderSwitchManagement<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3FrontOrderSwitchManagement {
    
    // フロント発注管理共通サービス
    private WEB3AdminDirSecFrontOrderCommonService commonService;
    
    // 証券会社コード
    private String institutionCode;
    
    // 市場コード
    private String marketCode;
    
    // フロント発注取引所区分コード
    private String frotOrderExCode;
    
    // フロント発注システム区分
    private String frotOrderSystemDiv;
    
    // 銘柄タイプ
    private String productType;
    
    // 切替処理方式区分
    private String changeProcessDiv;
    
    // データコード
    private String dataCode;
    
    //ユーザデータ
    private String userData;
    
    // 発注経路区分
    private String submitOrderDiv;
    
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FrontOrderSwitchManagement.class);

    
    /**
     * WEB3FrontOrderSwitchManagementクラスのオブジェクトを生成する。  <BR>
     * <BR>
     * １）各属性の設定をおこなう。  <BR>
     * <BR>
     *  　@１－１）this.フロント発注管理共通サービス = パラメータ.フロント発注管理共通サービス <BR>
     * <BR>
     *  　@１－２）this.証券会社コード = パラメータ.管理者・発注経路切替完了リクエスト.証券会社コード <BR>
     * <BR>
     *  　@１－３）this.市場コード = パラメータ.管理者・発注経路切替完了リクエスト.市場コード <BR>
     * <BR>
     *  　@１－４）this.フロント発注取引所区分コード = パラメータ.フロント発注管理共通サービス.getフロント発注取引所区分コード（） <BR>
     * <BR>
     *  　@　@　@[引数] <BR>
     *  　@　@　@フロント発注市場コード： パラメータ.管理者・発注経路切替完了リクエスト.変換市場コード <BR>
     * <BR>
     *  　@１－５）this.フロント発注システム区分 = パラメータ.フロント発注管理共通サービス.getフロント発注システム区分（） <BR>
     * <BR>
     *  　@　@　@[引数] <BR>
     *  　@　@　@フロント発注市場コード： パラメータ.管理者・発注経路切替完了リクエスト.変換市場コード <BR>
     * <BR>
     *  　@１－６）this.銘柄タイプ = パラメータ.管理者・発注経路切替完了リクエスト.銘柄タイプ <BR>
     * <BR>
     *  　@１－７）this.切替処理方式区分 = パラメータ.管理者・発注経路切替完了リクエスト.切替処理方式区分   <BR>
     * <BR>
     *  　@１－８）this.データコード = パラメータ.フロント発注管理共通サービス.getデータコード（） <BR>
     * <BR>
     *  　@　@　@[引数] <BR>
     *  　@　@　@切替起動区分： パラメータ.管理者・発注経路切替完了リクエスト.切替起動区分   <BR>
     *  　@　@　@切替処理方式区分： this.切替処理方式区分  <BR>
     * <BR>
     *  　@１－９）this.ユーザデータ = パラメータ.フロント発注管理共通サービス.getユーザデータ（） <BR>
     * <BR>
     *  　@　@　@[引数] <BR>
     *  　@　@　@変換市場コード： パラメータ.管理者・発注経路切替完了リクエスト.変換市場コード <BR>
     *  　@　@　@銘柄タイプ： this.銘柄タイプ    <BR>
     *  　@　@　@切替起動区分： パラメータ.管理者・発注経路切替完了リクエスト.切替起動区分   <BR>
     * <BR>
     *  　@１－１０）this.発注経路区分 = パラメータ.管理者・発注経路切替完了リクエスト.発注経路区分  <BR>
     * <BR>
     * @@param フロント発注管理共通クラスオブジェクト - フロント発注管理共通クラスオブジェクト。<BR>
     * @@param 発注経路切替完了オブジェクト - 発注経路切替完了オブジェクト。<BR>
     * @@roseuid 42F2B62A0259
     */
    public WEB3FrontOrderSwitchManagement(WEB3AdminDirSecFrontOrderCommonService l_commonService, 
                                                        WEB3AdminFrontRouteChangeCompleteRequest l_request)
    {
        // フロント発注管理共通クラスオブジェクト
        this.commonService = l_commonService;
        // 証券会社コード
        this.institutionCode = l_request.institutionCode;
        // 市場コード
        this.marketCode = l_request.marketCode;
        // フロント発注取引所区分コード
        this.frotOrderExCode = l_commonService.getFrontOrderExchangeCode(l_request.convertMarketCode);
        // フロント発注システム区分
        this.frotOrderSystemDiv = l_commonService.getFrontSystemDiv(l_request.convertMarketCode);
        // 銘柄タイプ
        this.productType = l_request.productType;
        // 切替処理方式区分
        this.changeProcessDiv = l_request.changeProcessDiv;
        // データコード
        this.dataCode = l_commonService.getDataCode(l_request.changeStartDiv,changeProcessDiv);
        // ユーザデータ
        this.userData = l_commonService.getUserData(l_request.convertMarketCode, l_request.productType, changeProcessDiv);
        // 発注経路区分
        this.submitOrderDiv = l_request.submitOrderRouteDiv;
    }

    /**
     *発注経路切替の新規実行処理を行う。<BR>
     *<BR>
     *１）　@update発注経路無効（）で発注先切替テーブルを更新する。<BR>
     *<BR>
     *２）　@get仮想サーバ情報（）を実行し、仮想サーバ情報を取得する。<BR>
     *<BR>
     *３）　@２）で取得したレコードが0件の場合<BR>
     *<BR>
     *　@３－１）　@returnする。<BR>
     *<BR>
     *４）　@２）で取得したレコード分、ループ処理を行う。<BR>
     *<BR>
     *　@４－１）　@execute障害仮想サーバレコード（）を実行し、障害仮想サーバ切替管理テーブルにレコードを登録する。<BR>
     *<BR>
     *　@　@　@[引数]<BR>
     *　@　@　@仮想サーバ情報Row： 仮想サーバ情報Row<BR>
     *<BR>
     *５）　@if　@切替処理方式区分 == "０：通番照会処理方式"の場合<BR>
     *<BR>
     *　@５－１）　@MAXASトリガーを発行（通番照会指示処理）。<BR>
     *<BR>
     *　@　@　@　@　@　@　@new WEB3MQMessageSpec(this.証券会社コード , "AX0X1", this.ユーザデータ)<BR>
     *<BR>
     *　@　@　@　@　@　@　@WEB3MQGatewayService.send(メッセージ内容：WEB3MQMessageSpec)<BR>
     *<BR>
     *　@５－２）　@returnする。<BR>
     *<BR>
     *６）　@else 切替処理方式区分 != "０：通番照会処理方式"の場合、<BR>
     *<BR>
     *　@６－１）　@this.銘柄タイプ = 1：株式の場合<BR>
     *<BR>
     *　@　@６－１－１）　@get市場受付不明注文（）を実行し、市場受付不明注文を取得する。<BR>
     *<BR>
     *　@　@　@　@[引数]<BR>
     *　@　@　@　@なし<BR>
     *<BR>
     *　@　@６－１－２）　@６－１－１）で取得したレコードが0件でない場合、<BR>
     *　@　@　@　@　@　@６－１－１）で取得したレコード分、ループ処理を行う。<BR>
     *<BR>
     *　@　@　@６－１－２－１）　@validateキューテーブル重複エラー（）を実行し、エラー（戻り値がtrue）の場合、<BR>
     *　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     *<BR>
     *　@　@　@　@[引数]<BR>
     *　@　@　@　@社内処理項目： 株式注文取引キューRoｗ（市場受付不明注文）.社内処理項目<BR>
     *<BR>
     *　@　@　@６－１－２－２）　@エラーでない場合<BR>
     *<BR>
     *　@　@　@　@６－１－２－２－１）　@create市場受付不明訂正注文（）を実行し、市場受付不明注文の訂正注文を作成する。<BR>
     *<BR>
     *　@　@　@　@　@　@[引数]<BR>
     *　@　@　@　@　@　@株式注文取引キューRoｗ（市場受付不明注文）： 株式注文取引キューRoｗ（市場受付不明注文）<BR>
     *<BR>
     *　@　@　@　@６－１－２－２－２）　@update不明注文処理区分（）を実行し、<BR>
     *　@　@　@　@　@　@　@　@市場受付不明注文の処理区分を9：市場受付確認中に更新する。<BR>
     *<BR>
     *　@　@　@　@　@　@[引数]<BR>
     *　@　@　@　@　@　@株式注文取引キューRoｗ（市場受付不明注文）： 株式注文取引キューRoｗ（市場受付不明注文）<BR>
     *<BR>
     *　@６－２）　@this.銘柄タイプ = 6：先物オプションの場合<BR>
     *<BR>
     *　@　@６－２－１）　@get先物OP市場受付不明注文（）を実行し、市場受付不明注文を取得する。<BR>
     *<BR>
     *　@　@　@　@[引数]<BR>
     *　@　@　@　@なし<BR>
     *<BR>
     *　@　@６－２－２）　@６－２－１）で取得したレコードが0件でない場合、<BR>
     *　@　@　@　@　@　@６－２－１）で取得したレコード分、ループ処理を行う。<BR>
     *<BR>
     *　@　@　@６－２－２－１）　@validate先物OPキューテーブル重複エラー（）を実行し、エラー（戻り値がtrue）の場合、<BR>
     *　@　@　@　@　@　@　@　@　@例外をスローする。<BR>
     *<BR>
     *　@　@　@　@　@[引数]<BR>
     *　@　@　@　@　@社内処理項目： 先物OP注文取引キューRoｗ（市場受付不明注文）.社内処理項目<BR>
     *<BR>
     *　@　@　@６－２－２－２）　@エラーでない場合<BR>
     *<BR>
     *　@　@　@　@６－２－２－２－１）　@create先物OP市場受付不明訂正注文（）を実行し、<BR>
     *　@　@　@　@　@　@　@　@　@市場受付不明注文の訂正注文を作成する。<BR>
     *<BR>
     *　@　@　@　@　@　@[引数]<BR>
     *　@　@　@　@　@　@先物OP注文取引キューRoｗ（市場受付不明注文）： 先物OP注文取引キューRoｗ（市場受付不明注文）<BR>
     *<BR>
     *　@　@　@　@６－２－２－２－２）　@update先物OP不明注文処理区分（）を実行し、<BR>
     *　@　@　@　@　@　@　@　@　@市場受付不明注文の処理区分を9：市場受付確認中に更新する。<BR>
     *<BR>
     *　@　@　@　@　@　@[引数]<BR>
     *　@　@　@　@　@　@先物OP注文取引キューRoｗ（市場受付不明注文）： 先物OP注文取引キューRoｗ（市場受付不明注文）<BR>
     *<BR>
     *　@６－３）　@MAXASトリガーを発行（通知代行指示処理）。<BR>
     *<BR>
     *　@　@　@　@　@　@　@new WEB3MQMessageSpec(this.証券会社コード , "AX9X1", this.ユーザデータ)<BR>
     *<BR>
     *　@　@　@　@　@　@　@WEB3MQGatewayService.send(メッセージ内容：WEB3MQMessageSpec)<BR>
     * @@roseuid 42F2C0B40084
     */
    public void executeOrderRouteSwitching() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "executeOrderRouteSwitching()";
        log.entering(STR_METHOD_NAME); 
        // 仮想サーバ情報List
        List l_virtualServerLists = new ArrayList();
        // トリガー発行サービスインスタンス
        WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
        
        // 発注経路を無効
        this.updateOrderRouteInvalidity();
    
        // 仮想サーバ情報を取得
        l_virtualServerLists = this.getVirtualServerInfo();
        
        if(l_virtualServerLists.size() == 0)
        {
            return;
        }
        
        Iterator l_virtualServerObj = l_virtualServerLists.iterator();
        
        while(l_virtualServerObj.hasNext())
        {
            // VirtualServerInformationRowオブジェクトの抽出
            VirtualServerInformationRow l_virServerInfoRow = (VirtualServerInformationParams)l_virtualServerObj.next();

            // 障害仮想サーバ切替管理テーブルにレコードを登録する
            this.executeVirtualServChange(l_virServerInfoRow);
        }
        
        // 切替処理方式区分が通番照会処理方式の場合、MAXASトリガーを発行
        if(this.changeProcessDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE))
        {
            // トリガー発行処理を行う。
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(this.institutionCode, WEB3AdminFrontDataCodeDef.NUBER_REF_REQ_CODE,
                                                                 this.userData);

            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);
        
            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> 通番照会処理送信 成功！！！");
            }
            else
            {
                log.debug("==> 通番照会処理送信 失敗 ！！！");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            return;
        }
        // 切替処理方式が全訂正処理方式の場合
        else
        {
            if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(this.productType))
            {
                //市場受付不明注文の取得
                List l_grayOrders = this.getMarketAcceptGrayOrder();
                
                if(l_grayOrders.size() != 0)
                {
                    Iterator l_grayObj = l_grayOrders.iterator();
                    
                    while(l_grayObj.hasNext())
                    {
                        // HostEqtypeOrderAllRowオブジェクトの抽出
                        HostEqtypeOrderAllRow l_hostRow = (HostEqtypeOrderAllRow)l_grayObj.next();
                        
                        // 重複エラーチェック
                        boolean l_isHostRepeat = this.validateHostEqtypeRepeat(l_hostRow.getCorpCode());
                        // レコードが存在する場合、重複エラーをスローする
                        if(l_isHostRepeat)
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }
                        else
                        {
                            // 訂正注文作成
                            this.createMarketAcceptModifyOrder(l_hostRow);
                            
                            // グレー注文の処理区分を"市場受付確認中"に更新する
                            this.updateGrayOrderStatus(l_hostRow);
                        }
                    }
                  }
            }
            else if(Integer.toString(ProductTypeEnum.IFO.intValue()).equals(this.productType))
            {

                //市場受付不明注文の取得
                List l_grayOrders = this.getIfoMarketAcceptGrayOrder();
                
                if(l_grayOrders.size() != 0)
                {
                    Iterator l_grayObj = l_grayOrders.iterator();
                    
                    while(l_grayObj.hasNext())
                    {
                        // HostFotypeOrderAllRowオブジェクトの抽出
                        HostFotypeOrderAllRow l_hostRow = (HostFotypeOrderAllRow)l_grayObj.next();
                        
                        // 重複エラーチェック
                        boolean l_isHostRepeat = this.validateIfoHostFotypeRepeat(l_hostRow.getCorpCode());
                        // レコードが存在する場合、重複エラーをスローする
                        if(l_isHostRepeat)
                        {
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
                        }
                        else
                        {
                            // 訂正注文作成
                            this.createIfoMarketAcceptModifyOrder(l_hostRow);
                            
                            // グレー注文の処理区分を"市場受付確認中"に更新する
                            this.updateIfoGrayOrderStatus(l_hostRow);
                        }
                    }
                  }
            }
            
            //MAXASトリガー(通知代行解除要求)を発行
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(this.institutionCode, WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REQ_CODE, this.userData);
        
            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);

            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> 通知代行解除要求処理送信 成功！！！");
            }
            else
            {
                log.debug("==> 通知代行解除要求処理送信 失敗 ！！！");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            } 
        }
    
        log.exiting(STR_METHOD_NAME);        
    }
   
    /**
     * 発注先切替テーブルの有効フラグを無効に切り替える。<BR>
     * <BR>
     * １） 検索条件オブジェクト生成<BR>
     * <BR>
     * 　@　@[条件] <BR>
     * 　@　@証券会社コード = ?  and <BR>
     * 　@　@市場コード = ? and <BR>
     * 　@　@フロント発注システム区分 = ? and <BR>
     * 　@　@銘柄タイプ = ?<BR>
     * 　@　@発注経路区分 = ? <BR>
     * <BR>
     * ２）　@検索条件データコンテナ生成<BR>
     * <BR>
     * 　@２－１）　@this.証券会社コードを設定。<BR>
     * <BR>
     * 　@２－２）　@this.市場コードを設定。<BR>
     * <BR>
     * 　@２－３）　@this.フロント発注システム区分を設定。<BR>
     * <BR>
     * 　@２－４）　@this.銘柄タイプを設定。<BR>
     * <BR>
     * 　@２－５）　@this.発注経路区分を設定。<BR>
     * <BR>
     * ３）　@doUpdateAllQuery（）で、有効フラグ"０：OFF"に更新する。<BR>
     * <BR>
     * 　@　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@　@「発注経路切替_発注先切替テーブル.xls」を参照。<BR>
     * @@roseuid 42F2CEFC006D
     */
    protected void updateOrderRouteInvalidity()throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "updateOrderRouteInvalidity()";
        log.entering(STR_METHOD_NAME);
        
        // 発注先切替テーブルオブジェクト生成
//      OrderSwitchingParams l_switchParamas = new OrderSwitchingParams();
        // 更新パラメータMap
        Map updateMap = new HashMap(); 
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and market_code = ? ");        
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                this.institutionCode,
                this.marketCode,
                this.frotOrderSystemDiv,
                this.productType,
                this.submitOrderDiv
            };        

        // 更新項目を生成
        updateMap.put("valid_flag",WEB3ValidFlag.OFF);
        updateMap.put("last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateAllQuery(OrderSwitchingRow.TYPE,l_sbWhere.toString(), l_objWhere, updateMap);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            

        log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * 仮想サーバ情報テーブルから、仮想サーバ情報を取得する。<BR>
     * <BR>
     * １） 検索条件オブジェクト生成<BR>
     * <BR>
     *  　@　@[条件] <BR>
     *  　@　@証券会社コード = ?  and <BR>
     *  　@　@フロント発注取引所区分コード = ? and <BR>
     *  　@　@フロント発注システム区分 = ? and <BR>
     *  　@　@フロント発注取引区分コード = ?<BR>
     *  　@　@銘柄タイプ = ? <BR>
     * <BR>
     * ２）　@検索条件データコンテナ生成<BR>
     * <BR>
     * 　@２－１）　@this.証券会社コードを設定。 <BR>
     * <BR>
     * 　@２－２）　@this.フロント発注取引所区分コードを設定。 <BR>
     * <BR>
     * 　@２－３）　@this.フロント発注システム区分を設定。<BR>
     * <BR>
     * 　@２－４）　@"１：株券売買"を設定。 <BR>
     * <BR>
     * 　@２－５）　@this.銘柄タイプを設定。<BR>
     * <BR>
     * ３）　@doFindAllQuery()で、レコードを取得<BR>
     * <BR>
     * ４）　@検索結果を返却する。<BR>
     * @@return List<BR>
     * @@roseuid 42F2F1D3008C
     */
    protected List getVirtualServerInfo() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getVirtualServerInfo()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_lstVirtualServers = new ArrayList();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");        
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                this.institutionCode,
                this.frotOrderExCode,
                this.frotOrderSystemDiv,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                this.productType,
            };        
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstVirtualServers = l_queryProcessor.doFindAllQuery(VirtualServerInformationRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }                            

        log.exiting(STR_METHOD_NAME);
        
     return l_lstVirtualServers;
    }
   
    /**
     * 障害仮想サーバレコードが、テーブルに存在するかチェックを行う。<BR>
     * <BR>
     * １）　@VirtualServerChangeDao.findDaoByPk()で、検索処理を行う。<BR>
     * <BR>
     * ２）　@if レコードが存在する場合、<BR>
     * <BR>
     *        trueを返却する。<BR>
     *      else<BR>
     * <BR>
     *        falseを返却する。<BR>
     * @@param 仮想サーバNo.（市場） - 仮想サーバNo.（市場）。<BR>
     * @@param 切替指示応答区分 - 切替指示応答区分。<BR>
     * @@param 通知種別 - 通知種別。<BR>
     * @@param フロント発注取引所区分コード - フロント発注取引所区分コード。<BR>
     * @@return boolean<BR>
     * @@roseuid 42F302C001E4
     */
    private boolean validateVirtualServerRecord(String l_virServerNo, String l_changeReqResDiv, 
                                                        String l_noticeType, String l_frontOrderExCode)throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateVirtualServerRecord()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            VirtualServerChangeDao l_dao = VirtualServerChangeDao.findDaoByPk(l_virServerNo, l_changeReqResDiv, l_noticeType, l_frontOrderExCode);
        }
        catch(DataFindException l_finEx)
        {
            return false;
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.exiting(STR_METHOD_NAME);

        return true;
    }
   
    /**
     * 障害仮想サーバ切替管理テーブルにデータをInsertする。<BR>
     * <BR>
     * [パラメータ]<BR>
     * 切替指示応答区分<BR>
     * 通知種別<BR>
     * 仮想サーバ情報テーブルRow<BR>
     * <BR>
     * １）　@VirtualServerChangeParamsクラスのインスタンスを生成する。<BR>
     * <BR>
     * <BR>
     * ２）　@if　@切替指示応答区分 = "通知代行要求" かつ<BR>
     * 　@　@　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード = "東証" or "名証"の場合、<BR>
     * <BR>
     * 　@　@２－１）　@get最終通知No（）処理を実行し、受付系通知通番を取得する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.仮想サーバNo.（市場）<BR>
     * 　@　@　@　@通知種別：　@"受付系"<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     * 　@　@２－２）　@get最終通知No（）処理を実行し、約定系通知通番を取得する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.仮想サーバNo.（市場）<BR>
     * 　@　@　@　@通知種別：　@"約定系"<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     * ３）　@if　@パラメータ.切替指示応答区分 = "通知再送要求"の場合、<BR>
     * <BR>
     * 　@　@３－１）　@get最終通知No（）処理を実行し、最終通知Noを取得する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.仮想サーバNo.（市場）<BR>
     * 　@　@　@　@通知種別：　@通知種別<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     * ４）　@set切替管理テーブルレコード（）処理を実行し、障害仮想サーバ切替管理テーブルParamsを取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@仮想サーバ情報テーブルRow：　@仮想サーバ情報テーブルRow<BR>
     * 　@　@切替指示応答区分：　@切替指示応答区分<BR>
     * 　@　@通知種別：　@通知種別<BR>
     * 　@　@最終通知No：　@最終通知No<BR>
     * 　@　@代行要求時受付系最終通知No：　@受付系通知通番<BR>
     * 　@　@代行要求時約定系最終通知No：　@約定系通知通番<BR>
     * <BR>
     * ５）　@doInsertQuery（）処理を実行する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@VirtualServerChangeParams：　@障害仮想サーバ切替管理テーブルParams<BR>
     * <BR>
     * 　@＊エラーの場合、例外をスローする。<BR>
     * @@param 切替指示応答区分 - 切替指示応答区分。<BR>
     * @@param 通知種別<BR>
     * @@param 仮想サーバ情報テーブルRow - 仮想サーバ情報テーブルRow。<BR>
     * @@roseuid 42F3204A02DE
     */
    protected void insertVirtualServerChange(String l_changeReqResDiv, String l_noticeType, 
                                                    VirtualServerInformationRow l_virtualInfoRow) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "insertVirtualServerChange(String, String, VirtualServerInformationRow)";
        log.entering(STR_METHOD_NAME);

        // VirtualServerChangeParamsクラスのインスタンスを生成
        VirtualServerChangeParams l_changeParams = new VirtualServerChangeParams();
        // 最終通知No
        String l_finalNoticeNo = null;
        //受付系通知通番
        String l_strAcceptNoticeNo = null;
        //約定系通知通番
        String l_strExecutedNoticeNo = null;
        //仮想サーバ情報テーブルRow.フロント発注取引所区分コード
        String l_strFrontOrderExchangeCode = l_virtualInfoRow.getFrontOrderExchangeCode();

        try
        {
            //if　@切替指示応答区分 = "通知代行要求" かつ
            //仮想サーバ情報テーブルRow.フロント発注取引所区分コード = "東証" or "名証"の場合、
            if (WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST.equals(l_changeReqResDiv)
                && (WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
                    || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)))
            {
                //get最終通知No（）処理を実行し、受付系通知通番を取得する。
                l_strAcceptNoticeNo = this.getFinalNoticeNo(
                    l_virtualInfoRow.getVirtualServerNumberMarket(),
                    WEB3NoticeTypeDef.ACCEPT_TYPE,
                    l_strFrontOrderExchangeCode);

                //get最終通知No（）処理を実行し、約定系通知通番を取得する。
                l_strExecutedNoticeNo = this.getFinalNoticeNo(
                     l_virtualInfoRow.getVirtualServerNumberMarket(),
                     WEB3NoticeTypeDef.EXECUTED_TYPE,
                     l_strFrontOrderExchangeCode);
            }

            // 切替指示応答区分が通知再送要求の場合、最終通知Noを取得
            if(l_changeReqResDiv.equals(WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST))
            {
                l_finalNoticeNo = this.getFinalNoticeNo(l_virtualInfoRow.getVirtualServerNumberMarket(), l_noticeType, 
                                                                                l_virtualInfoRow.getFrontOrderExchangeCode());
            }
            // インサート項目をセットする
            l_changeParams = this.setVirtualServerChangeRecord(
                l_virtualInfoRow,
                l_changeReqResDiv,
                l_noticeType,
                l_finalNoticeNo,
                l_strAcceptNoticeNo,
                l_strExecutedNoticeNo);

            // Insert処理
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_changeParams);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }           
        log.exiting(STR_METHOD_NAME);                 
    }
   
    /**
     * 障害仮想サーバ切替管理テーブルにInsertする項目をセットする。<BR>
     * <BR>
     * [パラメータ]<BR>
     * 仮想サーバ情報テーブルRow<BR>
     * 切替指示応答区分<BR>
     * 通知種別<BR>
     * 最終通知No<BR>
     * 代行要求時受付系最終通知No<BR>
     * 代行要求時約定系最終通知No<BR>
     * <BR>
     * 　@１）　@VirtualServerChangeParamsクラスのインスタンスを生成する。<BR>
     * <BR>
     * 　@２）　@Insertする項目をセットする。<BR>
     * 　@　@ 　@　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@　@ 　@　@「発注経路切替_障害仮想サーバ切替管理テーブル.xls」の <BR>
     * 　@　@ 　@　@ シート「（通番照会処理）障害仮想サーバ切替管理テーブル」と <BR>
     *  　@　@ 　@　@シート「（全訂正処理）障害仮想サーバ切替管理テーブル」を参照。 <BR>
     * <BR>
     *  　@　@ 　@　@※切替指示応答区分が通知再送要求の場合のみ、最終通知No != nullとなる。<BR>
     * <BR>
     * ３）　@VirtualServerChangeParamsオブジェクトを返却する。<BR>
     * @@param 仮想サーバ情報テーブルRow - 仮想サーバ情報テーブルRowオブジェクト。<BR>
     * @@param 切替指示応答区分 - 切替指示応答区分。<BR>
     * @@param 通知種別 - 通知種別。<BR>
     * @@param 最終通知No - 最終通知No。<BR>
     * @@param 代行要求時受付系最終通知No - 代行要求時受付系最終通知No。<BR>
     * @@param 代行要求時約定系最終通知No - 代行要求時約定系最終通知No。<BR>
     * @@return VirtualServerChangeParams<BR>
     * @@roseuid 42F3300A03C8
     */
    private VirtualServerChangeParams setVirtualServerChangeRecord(
        VirtualServerInformationRow l_priVirtualInfoRow,
        String l_priReqResDiv,
        String l_priNoticeDiv,
        String l_priFinalNoticeNo,
        String l_strAcceptNoticeNo,
        String l_strExecutedNoticeNo)
    {
        // VirtualServerChangeParamsインスタンスの生成
        VirtualServerChangeParams l_priVirtualServPrams = new VirtualServerChangeParams();
        
        // 仮想サーバNo.（市場）をセット
        l_priVirtualServPrams.virtual_server_number_market = l_priVirtualInfoRow.getVirtualServerNumberMarket();
        // 切替指示応答区分
        l_priVirtualServPrams.change_req_res_div = l_priReqResDiv;
        // 通知種別
        l_priVirtualServPrams.notice_type = l_priNoticeDiv;
        // 証券会社コード
        l_priVirtualServPrams.institution_code = l_priVirtualInfoRow.getInstitutionCode();
        // フロント発注取引所区分コード
        l_priVirtualServPrams.front_order_exchange_code = l_priVirtualInfoRow.getFrontOrderExchangeCode();
        // フロント発注システム区分
        l_priVirtualServPrams.front_order_system_code = l_priVirtualInfoRow.getFrontOrderSystemCode();
        // フロント発注取引区分コード
        l_priVirtualServPrams.front_order_trade_code = WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE;
        // クライアントNo
        l_priVirtualServPrams.client_number = l_priVirtualInfoRow.getClientNumber();
        // 通知ファ@イルNo
        l_priVirtualServPrams.notice_file_number = l_priVirtualInfoRow.getNoticeFileNumber();
        // 再送要求通知番号from
        if(l_priFinalNoticeNo != null)
        {
            long l_longNoticeNo = 0;
            
            l_longNoticeNo = Long.parseLong(l_priFinalNoticeNo);
 
            l_priVirtualServPrams.resend_notice_number_from = new Long(l_longNoticeNo);    
        }
        //代行元受付系通知通番
        if (l_strAcceptNoticeNo != null)
        {
            long l_lngAcceptNoticeNo = 0;

            l_lngAcceptNoticeNo = Long.parseLong(l_strAcceptNoticeNo);

            l_priVirtualServPrams.agency_accept_notice_number = new Long(l_lngAcceptNoticeNo);
        }
        //代行元約定系通知通番
        if (l_strExecutedNoticeNo != null)
        {
            long l_lngExecutedNoticeNo = 0;

            l_lngExecutedNoticeNo = Long.parseLong(l_strExecutedNoticeNo);

            l_priVirtualServPrams.agency_exec_notice_number = new Long(l_lngExecutedNoticeNo);
        }

        // 処理区分
        l_priVirtualServPrams.status = WEB3StatusDef.NOT_DEAL;
        // 銘柄タイプ
        l_priVirtualServPrams.product_type = l_priVirtualInfoRow.getProductType();
        
        return l_priVirtualServPrams;
    }
   
    /**
     * 市場通知管理テーブルで、通知種別が"01：受付系" or <BR>
     * "02：約定系"のレコードの最終通知番号を取得する。<BR>
     * <BR>
     * １）　@検索条件文字列を生成する。<BR>
     * <BR>
     *  　@　@[条件] <BR>
     *  　@　@仮想サーバNo.（市場） = ? and <BR>
     *  　@　@通知種別 = ? and <BR>
     *  　@　@フロント発注取引所区分コード = ? and<BR>
     *  　@　@経過営業日数 = ? <BR>
     * <BR>
     * ２）　@ソート条件文字列を生成する。<BR>
     * <BR>
     * 　@　@　@ソート条件：　@通知番号.desc<BR>
     * <BR>
     * ３）　@検索条件文字列コンテナを生成する。<BR>
     * <BR>
     * 　@　@　@３－１）　@パラメータ.仮想サーバNo.（市場）を設定。 <BR>
     * <BR>
     * 　@　@　@３－２）　@パラメータ.通知種別を設定。 <BR>
     * <BR>
     * 　@　@　@３－３）　@パラメータ.フロント発注取引所区分コードを設定。 <BR>
     * <BR>
     * 　@　@　@３－４）　@0を設定。  <BR>
     * <BR>
     * ４）　@doFindAllQuery()で、データを取得する。<BR>
     * <BR>
     * ５）　@最終通知番号を返却する。<BR>
     * <BR>
     * 　@５－１）　@検索結果が0件の場合 <BR>
     * <BR>
     * 　@　@５－１－１）　@パラメータ.フロント発注取引所区分コード = "東証" or "名証"の場合、 <BR>
     * 　@　@　@  　@　@　@　@　@　@最終通知番号 = 0を返却する。 <BR>
     * 　@　@５－１－２）　@上記以外の場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@最終通知番号 = 1を返却する。<BR>
     * <BR>
     * 　@５－２）　@データが存在する場合、最終通知番号をRowオブジェクトから取得し返却する。<BR>
     * @@param 仮想サーバNo.（市場） - 仮想サーバNo.（市場）。<BR>
     * @@param 通知種別 - 通知種別。<BR>
     * @@param フロント発注取引所区分コード - フロント発注取引所区分コード。<BR>
     * @@return String<BR>
     * @@roseuid 42F340C20398
     */
    private String getFinalNoticeNo(String l_priServNoMarket, String l_priNoticeType, String l_priFrontExCode) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getFinalNoticeNo(String, String, String)";
        log.entering(STR_METHOD_NAME);
        // 最終通知No
        String l_finalNoticeNo = null;
        
        // ArrayListオブジェクトの生成
        List l_lstMarketNotices = new ArrayList();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" virtual_server_number_market = ? ");
        l_sbWhere.append(" and notice_type = ? ");        
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and biz_date_count = ? ");

        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_priServNoMarket,
                l_priNoticeType,
                l_priFrontExCode,
                Integer.toString(0)
            };
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            // 通知番号取得の為に、レコードを1件取得する。
            l_lstMarketNotices = l_queryProcessor.doFindAllQuery(MarketNoticeManagementRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    "notice_number desc",
                                                                    null,
                                                                    l_objWhere,
                                                                    1,
                                                                    0);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        // 検索結果が0件の場合
        if(l_lstMarketNotices.size() == 0)
        {
            //パラメータ.フロント発注取引所区分コード = "東証" or "名証"の場合、
            //最終通知番号 = 0を返却する
            if (WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_priFrontExCode)
                || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_priFrontExCode))
            {
                l_finalNoticeNo = "0";
            }
            else
            {
                //上記以外の場合、最終通知番号 = 1を返却する。
                l_finalNoticeNo = "1";
            }
        }
        // 最終通知Noを取得
        else
        {
            MarketNoticeManagementRow row = (MarketNoticeManagementRow)l_lstMarketNotices.get(0); 
            l_finalNoticeNo = Long.toString(row.getNoticeNumber());
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_finalNoticeNo;
    }
   
    /**
     * 株式注文取引キューテーブルから、フロント発注正系で市場受付不明注文を取得する。 <BR>
     * <BR>
     * １）　@検索条件文字列を生成する。    <BR>
     * <BR>
     * 　@[条件]   <BR>
     * 　@証券会社コード = ? and    <BR>
     * 　@フロント発注取引所区分コード = ? and <BR>
     * 　@フロント発注システム区分 = ? and   <BR>
     * 　@フロント発注取引区分コード = ? and  <BR>
     * 　@発注経路区分 = ? and <BR>
     * 　@処理区分 in (?,?) <BR>
     * <BR>
     * ２）　@検索条件文字列コンテナを生成する。    <BR>
     * <BR>
     * 　@２－１）　@this.証券会社コードを設定。  <BR>
     * <BR>
     * 　@２－２）　@this.フロント発注取引所区分コードを設定。   <BR>
     * <BR>
     * 　@２－３）　@this.フロント発注システム区分を設定。 <BR>
     * <BR>
     * 　@２－４）　@1：株券売買を設定。    <BR>
     * <BR>
     * 　@２－５）　@2：フロント発注正系を設定。    <BR>
     * <BR>
     * 　@２－６）　@1：送信済を設定。 <BR>
     * <BR>
     * 　@２－７）　@2：AMG入力完了を設定。 <BR>
     * <BR>
     * ３）　@doFindAllQuery（）で、市場受付不明注文を取得。   <BR>
     * <BR>
     * ４）　@取得結果を返却する。   <BR>
     * <BR>
     * @@return List<BR>
     * @@roseuid 42F6A97E029F
     */
    private List getMarketAcceptGrayOrder() throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getMarketAcceptGrayOrder()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_grayOrders = new ArrayList();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");        
        l_sbWhere.append(" and status in (?,?) ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                this.institutionCode,
                this.frotOrderExCode,
                this.frotOrderSystemDiv,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION,
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_grayOrders = l_queryProcessor.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.exiting(STR_METHOD_NAME);

        return l_grayOrders;
    }
    
    /**
     * 先物OP注文取引キューテーブルから、フロント発注正系で市場受付不明注文を取得する。   <BR>
     * <BR>
     * １）　@検索条件文字列を生成する。    <BR>
     * <BR>
     *  　@[条件]   <BR>
     *  　@証券会社コード = ? and    <BR>
     *  　@フロント発注取引所区分コード = ? and <BR>
     *  　@フロント発注システム区分 = ? and   <BR>
     *  　@フロント発注取引区分コード = ? and  <BR>
     *  　@発注経路区分 = ? and <BR>
     *  　@処理区分 in (?,?) <BR>
     * <BR>
     * ２）　@検索条件文字列コンテナを生成する。    <BR>
     * <BR>
     *  　@２－１）　@this.証券会社コードを設定。  <BR>
     * <BR>
     *  　@２－２）　@this.フロント発注取引所区分コードを設定。   <BR>
     * <BR>
     *  　@２－３）　@this.フロント発注システム区分を設定。 <BR>
     * <BR>
     *  　@２－４）　@1：株券売買を設定。    <BR>
     * <BR>
     *  　@２－５）　@2：フロント発注正系を設定。    <BR>
     * <BR>
     *  　@２－６）　@1：送信済を設定。 <BR>
     * <BR>
     *  　@２－７）　@2：AMG入力完了を設定。 <BR>
     * <BR>
     * ３）　@doFindAllQuery（）で、市場受付不明注文を取得。   <BR>
     * <BR>
     * ４）　@取得結果を返却する。   <BR>
     * @@return List<BR>
     * @@roseuid 
     */
    private List getIfoMarketAcceptGrayOrder() throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = ".getIfoMarketAcceptGrayOrder()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_grayOrders = new ArrayList();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");        
        l_sbWhere.append(" and status in (?,?) ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                this.institutionCode,
                this.frotOrderExCode,
                this.frotOrderSystemDiv,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION,
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_grayOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.exiting(STR_METHOD_NAME);

        return l_grayOrders;
    }

    /**
     * 取消区分 = 0：取消以外、全訂正処理区分 = 1：全訂正処理を検索条件に、<BR>
     * 株式注文取引キューテーブルを検索し、<BR>
     * レコードが存在すればtrueを返却する。    <BR>
     * <BR>
     * １）　@検索条件オブジェクト生成 <BR>
     * <BR>
     * 　@[条件]   <BR>
     * 　@取消区分 = ? and   <BR>
     * 　@社内処理項目 = ? and <BR>
     * 　@全訂正処理区分 = ?    <BR>
     * <BR>
     * ２）　@検索条件データコンテナ生成    <BR>
     * <BR>
     * 　@２－１）　@0：取消以外を設定。    <BR>
     * <BR>
     * 　@２－２）　@パラメータ.社内処理項目を設定。  <BR>
     * <BR>
     * 　@２－３）　@1：全訂正処理を設定。   <BR>
     * <BR>
     * ３）　@doFindAllQuery()で、レコードを検索。   <BR>
     * <BR>
     * ４）　@検索結果が存在すればtrueを返却、存在しなければfalseを返却する。 <BR>
     * <BR>
     * @@param 社内処理項目 - 社内処理項目。<BR>
     * @@return boolean<BR>
     * @@roseuid 42F6E0AB0321
     */
    protected boolean validateHostEqtypeRepeat(String l_corpCode) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateHostEqtypeRepeat(String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_hostOrders = new ArrayList();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                WEB3CancelDivDef.EXCEPT_CANCEL,
                l_corpCode,
                WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE,
            };
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);

            // 主キーを基に検索
//          HostEqtypeOrderAllDao l_dao = HostEqtypeOrderAllDao.findDaoByPk(WEB3CancelDivDef.EXCEPT_CANCEL, l_corpCode, WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
        }
//        // データが存在しない場合はfalseを返却
//        catch(DataFindException l_finEx)
//        {
//            return false;
//        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        // データが存在しない場合はfalseを返却
        if(l_hostOrders.size() == 0)
        {
            return false;            
        }
        log.exiting(STR_METHOD_NAME);

        return true;
    }
    
    /**
     * 取消区分 = 0：取消以外、全訂正処理区分 = 1：全訂正処理を検索条件に、 <BR>
     * 先物OP注文取引キューテーブルを検索し、<BR>
     * レコードが存在すればtrueを返却する。 <BR>
     * <BR>
     * １）　@検索条件オブジェクト生成 <BR>
     * <BR>
     *  　@ 　@[条件] <BR>
     * 　@ 　@ 取消区分 = ? and <BR>
     * 　@ 　@ 社内処理項目 = ? and <BR>
     * 　@ 　@ 全訂正処理区分 = ? <BR>
     * <BR>
     * ２）　@検索条件データコンテナ生成 <BR>
     * <BR>
     *   　@ 　@２－１）　@0：取消以外を設定。 <BR>
     * <BR>
     *   　@ 　@２－２）　@パラメータ.社内処理項目を設定。 <BR>
     * <BR>
     *   　@ 　@２－３）　@1：全訂正処理を設定。 <BR>
     * <BR>
     * ３）　@doFindAllQuery()で、レコードを検索。 <BR>
     * <BR>
     * ４）　@検索結果が存在すればtrueを返却、存在しなければfalseを返却する。<BR>
     * <BR>
     * @@param 社内処理項目 - 社内処理項目。<BR>
     * @@return boolean<BR>
     * @@roseuid 42F6E0AB0321
     */
    private boolean validateIfoHostFotypeRepeat(String l_corpCode) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = ".validateIfoHostFotypeRepeat(String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_hostOrders = new ArrayList();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                WEB3CancelDivDef.EXCEPT_CANCEL,
                l_corpCode,
                WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE,
            };
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_hostOrders = l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                                    l_sbWhere.toString(),
                                                                    l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        // データが存在しない場合はfalseを返却
        if(l_hostOrders.size() == 0)
        {
            return false;            
        }
        log.exiting(STR_METHOD_NAME);

        return true;
    }
   
    /**
     * 株式注文取引キューテーブルに市場受付不明注文の訂正注文を作成する。   <BR>
     * <BR>
     * １）　@HostEqtypeOrderAllParamsオブジェクトを生成する。 <BR>
     * <BR>
     * ２）　@各項目を設定する。    <BR>
     * <BR>
     * 　@【ｘTrade】補足資料.DB更新  <BR>
     * 　@「発注経路切替_株式注文取引キューテーブル.xls」の    <BR>
     * 　@　@シート「発注経路切替_株式注文取引キューテーブル（insert）」を参照。 <BR>
     * <BR>
     * ３）　@doInsertQuery（）処理を実行し、データをInsertする。  <BR>
     * <BR>
     * @@param 株式注文取引キューRow（市場受付不明注文） - 株式注文取引キューRow（市場受付不明注文）。<BR>
     * @@roseuid 42F6E0B0019A
     */
    protected void createMarketAcceptModifyOrder(HostEqtypeOrderAllRow l_hostEqtypeRow) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "createMarketAcceptNodifyOrder(HostEqtypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        // HostEqtypeOrderAllParamsインスタンスを生成
        HostEqtypeOrderAllParams orderAllParams = new HostEqtypeOrderAllParams();
        
        // データコード = "AI802"(株式注文訂正)をセット
        orderAllParams.request_code = WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE;
		// 口座IDをセット
		orderAllParams.account_id = new Long(l_hostEqtypeRow.getAccountId());
        // 証券会社コードをセット
        orderAllParams.institution_code = l_hostEqtypeRow.getInstitutionCode();
        // 部店コードをセット
        orderAllParams.branch_code = l_hostEqtypeRow.getBranchCode();
        // 識別コードをセット
        orderAllParams.order_request_number = l_hostEqtypeRow.getOrderRequestNumber();
        // 銘柄コードをセット
        orderAllParams.product_code = l_hostEqtypeRow.getProductCode();
        // 受注日時をセット
        orderAllParams.received_date_time = l_hostEqtypeRow.getReceivedDateTime();
        // 発注経路区分 = フロント発注副系をセット
        orderAllParams.submit_order_route_div = WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION;
        // 売付数量をセット
        if(l_hostEqtypeRow.getSellOrderQuantity() != 0.0D && ! l_hostEqtypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.sell_order_quantity = new Double(l_hostEqtypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.sell_order_quantity = new Double(l_hostEqtypeRow.getSellOrderQuantity());
        }
        // 買付数量をセット
        if(l_hostEqtypeRow.getBuyOrderQuantity() != 0.0D && ! l_hostEqtypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.buy_order_quantity = new Double(l_hostEqtypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.buy_order_quantity = new Double(l_hostEqtypeRow.getBuyOrderQuantity());
        }
        // 指値をセット
		if(l_hostEqtypeRow.getPriceConditionType() != null && l_hostEqtypeRow.getPriceConditionType().equals(" "))
		{
            if(l_hostEqtypeRow.getChangeLimitPriceIsNull())
            {
                orderAllParams.limit_price = new Double(l_hostEqtypeRow.getLimitPrice());
            }
            else
            {
                orderAllParams.limit_price = new Double(l_hostEqtypeRow.getChangeLimitPrice());
            }
		}
		// 訂正数量をセット
        if(! l_hostEqtypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.change_quantity = new Double(l_hostEqtypeRow.getChangeQuantity());
        }
		else if(l_hostEqtypeRow.getSellOrderQuantity() != 0.0D)
		{
			orderAllParams.change_quantity = orderAllParams.sell_order_quantity;
		}
		else
		{
			orderAllParams.change_quantity = orderAllParams.buy_order_quantity;
		}
		// 訂正指値をセット
		if(l_hostEqtypeRow.getPriceConditionType() != null && l_hostEqtypeRow.getPriceConditionType().equals(" "))
		{
            if(l_hostEqtypeRow.getChangeLimitPriceIsNull())
            {
                orderAllParams.change_limit_price = new Double(l_hostEqtypeRow.getLimitPrice());
            }
            else
            {
                orderAllParams.change_limit_price = new Double(l_hostEqtypeRow.getChangeLimitPrice());
            }
		}
        // 取消区分をセット
        orderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
        // フロント発注取引所区分コードをセット
        orderAllParams.front_order_exchange_code = l_hostEqtypeRow.getFrontOrderExchangeCode();
        // フロント発注システム区分をセット
        orderAllParams.front_order_system_code = l_hostEqtypeRow.getFrontOrderSystemCode();
        // フロント発注取引区分コードをセット
        orderAllParams.front_order_trade_code = l_hostEqtypeRow.getFrontOrderTradeCode();
        // 社内処理項目をセット
        orderAllParams.setCorpCode(l_hostEqtypeRow.getCorpCode());

        //（被訂正）社内処理項目をセット
        //市場受付不明注文.フロント発注取引所区分コード == "東証" or "名証"　@かつ、
        //訂正（市場受付不明注文.データコード == "AI802"かつ取消区分 == "0"）の場合
        //　@・市場受付不明注文.（被訂正）社内処理項目
        //上記外の場合
        //　@・市場受付不明注文.社内処理項目
        String l_strFrontOrderExchangeCode = l_hostEqtypeRow.getFrontOrderExchangeCode();
        String l_strRequestCode = l_hostEqtypeRow.getRequestCode();
        String l_strCancelDiv = l_hostEqtypeRow.getCancelDiv();

        if ((WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode)
            || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_strFrontOrderExchangeCode))
            && WEB3HostRequestCodeDef.EQUITY_ORDER_CHANGE.equals(l_strRequestCode)
            && WEB3CancelDivDef.EXCEPT_CANCEL.equals(l_strCancelDiv))
        {
            orderAllParams.setOrgCorpCode(l_hostEqtypeRow.getOrgCorpCode());
        }
        else
        {
            orderAllParams.setOrgCorpCode(l_hostEqtypeRow.getCorpCode());
        }

        // 全訂正処理区分 = "全訂正処理"をセット
        orderAllParams.setAllOrderChangeDiv(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
        // 処理区分 = "未処理"をセット
        orderAllParams.setStatus(WEB3StatusDef.NOT_DEAL);
        
        try{
            // Insert処理
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(orderAllParams);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }           
        log.exiting(STR_METHOD_NAME);                 
    }
    
    /**
     * 先物OP注文取引キューテーブルに市場受付不明注文の訂正注文を作成する。 <BR>
     * <BR>
     * １）　@HostFotypeOrderAllParamsオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@各項目を設定する。<BR>
　@   *   　@ 　@【ｘTrade】補足資料.DB更新<BR>
　@   *   　@ 　@「発注経路切替_先物OP注文取引キューテーブル.xls」の <BR>
　@　@ *   　@ 　@シート「発注経路切替_先物OP注文取引キューテーブル（insert）」を参照。 <BR>
　@　@ * <BR>
     * ３）　@doInsertQuery（）処理を実行し、データをInsertする。<BR>
     * @@param 先物OP注文取引キューRow（市場受付不明注文）- 先物OP注文取引キューRow（市場受付不明注文）。<BR>
     * @@roseuid 
     */
    private void createIfoMarketAcceptModifyOrder(HostFotypeOrderAllRow l_hostFotypeRow) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = ".createIfoMarketAcceptModifyOrder(HostFotypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        // HostFotypeOrderAllParamsインスタンスを生成
        HostFotypeOrderAllParams orderAllParams = new HostFotypeOrderAllParams();
       
        //データコード
        if (WEB3IfoProductTypeDef.FUTURES.equals(l_hostFotypeRow.getFutureOptionProductType()))
        {
            orderAllParams.request_code = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
        }
        else
        {
            orderAllParams.request_code = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;
        }
        
        //口座ＩＤ
        orderAllParams.account_id = new Long(l_hostFotypeRow.getAccountId());
        //証券会社コード
        orderAllParams.institution_code = l_hostFotypeRow.getInstitutionCode();
        //部店コード
        orderAllParams.branch_code = l_hostFotypeRow.getBranchCode(); 
        //識別コード
        orderAllParams.order_request_number = l_hostFotypeRow.getOrderRequestNumber();
        //銘柄コード
        orderAllParams.product_code = l_hostFotypeRow.getProductCode();
        //受注日時
        orderAllParams.received_date_time = l_hostFotypeRow.getReceivedDateTime();
        //発注経路区分
        orderAllParams.submit_order_route_div = WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION;
        //売付数量
        if(l_hostFotypeRow.getSellOrderQuantity() != 0.0D && ! l_hostFotypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.sell_order_quantity = new Double(l_hostFotypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.sell_order_quantity = new Double(l_hostFotypeRow.getSellOrderQuantity());
        }
        // 買付数量をセット
        if(l_hostFotypeRow.getBuyOrderQuantity() != 0.0D && ! l_hostFotypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.buy_order_quantity = new Double(l_hostFotypeRow.getChangeQuantity());
        }
        else
        {
            orderAllParams.buy_order_quantity = new Double(l_hostFotypeRow.getBuyOrderQuantity());
        }
        // 指値をセット
        if(l_hostFotypeRow.getChangeLimitPriceIsNull())
        {
            orderAllParams.limit_price = new Double(l_hostFotypeRow.getLimitPrice());
        }
        else
        {
            orderAllParams.limit_price = new Double(l_hostFotypeRow.getChangeLimitPrice());
        }
        // 訂正数量をセット
        if(l_hostFotypeRow.getChangeQuantityIsNull() && l_hostFotypeRow.getSellOrderQuantity() != 0.0D)
        {
            orderAllParams.change_quantity = orderAllParams.sell_order_quantity;
        }
        else if(l_hostFotypeRow.getChangeQuantityIsNull() && l_hostFotypeRow.getSellOrderQuantity() == 0.0D)
        {
            orderAllParams.change_quantity = orderAllParams.buy_order_quantity;
        }
        else if(!l_hostFotypeRow.getChangeQuantityIsNull())
        {
            orderAllParams.change_quantity = new Double(l_hostFotypeRow.getChangeQuantity());
        }
        // 訂正指値をセット
        if(l_hostFotypeRow.getChangeLimitPriceIsNull())
        {
                orderAllParams.change_limit_price = new Double(l_hostFotypeRow.getLimitPrice());
                
        }
        else 
        {
            orderAllParams.change_limit_price = new Double(l_hostFotypeRow.getChangeLimitPrice());
        }
        // 取消区分をセット
        orderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
        // フロント発注取引所区分コードをセット
        orderAllParams.front_order_exchange_code = l_hostFotypeRow.getFrontOrderExchangeCode();
        // フロント発注システム区分をセット
        orderAllParams.front_order_system_code = l_hostFotypeRow.getFrontOrderSystemCode();
        // フロント発注取引区分コードをセット
        orderAllParams.front_order_trade_code = l_hostFotypeRow.getFrontOrderTradeCode();
        // 社内処理項目をセット
        orderAllParams.setCorpCode(l_hostFotypeRow.getCorpCode());
        // （被訂正）社内処理項目をセット
        orderAllParams.org_corp_code = l_hostFotypeRow.getCorpCode();
        // 全訂正処理区分 = "全訂正処理"をセット
        orderAllParams.setAllOrderChangeDiv(WEB3AllOrderChangeDivDef.ALL_ORDER_CHANGE);
        // 処理区分 = "未処理"をセット
        orderAllParams.setStatus(WEB3StatusDef.NOT_DEAL);
        
        try
        {
            // Insert処理
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(orderAllParams);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }           
        log.exiting(STR_METHOD_NAME);                 
    }
   
    /**
     * 市場受付不明注文の処理区分を"９：市場受付確認中"に更新する。<BR>
     * <BR>
     * １）　@検索条件オブジェクト生成<BR>
     * <BR>
     *    　@ 　@[条件]<BR>
     *    　@ 　@取消区分 = ? and<BR>
     *    　@ 　@社内処理項目 = ? and<BR>
     *    　@ 　@全訂正処理区分 = ? and<BR>
     *    　@ 　@処理区分 in (?, ?)<BR>
     * <BR>
     * ２）　@検索条件データコンテナ生成<BR>
     * <BR>
     *    　@ 　@２－１）　@パラメータ.株式注文取引キューRoｗ.取消区分を設定。<BR>
     * <BR>
     *    　@ 　@２－２）　@パラメータ.株式注文取引キューRoｗ.社内処理項目を設定。<BR>
     * <BR>
     *    　@ 　@２－３）　@パラメータ.株式注文取引キューRoｗ.全訂正処理区分を設定。<BR>
     * <BR>
     *    　@ 　@２－４）　@1：送信済を設定。<BR>
     * <BR>
     *    　@ 　@２－５）　@2：AMG入力完了を設定。<BR>
     * <BR>
     * ３）　@doUpdateQuery（）で、レコードを更新する。<BR>
     * <BR>
     *    　@ 　@【ｘTrade】補足資料.DB更新<BR>
     *    　@ 　@「発注経路切替_株式注文取引キューテーブル.xls」の<BR>
     *    　@ 　@シート「発注経路切替_株式注文取引キューテーブル (update)」を参照。<BR>
     * <BR>
     * @@param 株式注文取引キューRow（市場受付不明注文） - 株式注文取引キューRow（市場受付不明注文）。<BR>
     * @@roseuid 42F6F4660211
     */
    protected void updateGrayOrderStatus(HostEqtypeOrderAllRow l_hostEqtypeRoｗ) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "updateGrayOrderStatus(HostEqtypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        
        // Mapオブジェクトの生成
        Map l_mapStatus = new HashMap();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        l_sbWhere.append(" and status in (?,?) ");
        
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_hostEqtypeRoｗ.getCancelDiv(),
                l_hostEqtypeRoｗ.getCorpCode(),
                l_hostEqtypeRoｗ.getAllOrderChangeDiv(),
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };

        l_mapStatus.put("status",WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
        l_mapStatus.put("last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        try{
            // Update処理
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateAllQuery(HostEqtypeOrderAllRow.TYPE, l_sbWhere.toString(), l_objWhere, l_mapStatus);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }           
        log.exiting(STR_METHOD_NAME);                 
    }

    /**
     * 市場受付不明注文の処理区分を"９：市場受付確認中"に更新する。<BR>
     * <BR>
     * １）　@検索条件オブジェクト生成<BR>
     * <BR>
     *    　@ 　@[条件]<BR>
     *    　@ 　@取消区分 = ? and<BR>
     *    　@ 　@社内処理項目 = ? and<BR>
     *    　@ 　@全訂正処理区分 = ? and<BR>
     *    　@ 　@処理区分 in (?, ?)<BR>
     * <BR>
     * ２）　@検索条件データコンテナ生成<BR>
     * <BR>
     *    　@ 　@２－１）　@パラメータ.先物OP注文取引キューRoｗ.取消区分を設定。 <BR>
     * <BR>
     *    　@ 　@２－２）　@パラメータ.先物OP注文取引キューRoｗ.社内処理項目を設定。 <BR>
     * <BR>
     *    　@ 　@２－３）　@パラメータ.先物OP注文取引キューRoｗ.全訂正処理区分を設定。 <BR>
     * <BR>
     *    　@ 　@２－４）　@1：送信済を設定。<BR>
     * <BR>
     *    　@ 　@２－５）　@2：AMG入力完了を設定。<BR>
     * <BR>
     * ３）　@doUpdateQuery（）で、レコードを更新する。<BR>
     * <BR>
     *    　@ 　@【ｘTrade】補足資料.DB更新<BR>
     *    　@ 　@「発注経路切替_先物OP注文取引キューテーブル(update)」の <BR>
     *    　@ 　@シート「発注経路切替_先物OP注文取引キューテーブル(update)」を参照。
     * <BR>
     * @@param 先物OP注文取引キューRoｗ（市場受付不明注文）- 先物OP注文取引キューRoｗ（市場受付不明注文）。<BR>
     * @@roseuid 42F6F4660211
     */
    private void updateIfoGrayOrderStatus(HostFotypeOrderAllRow l_hostFotypeRoｗ) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = ".updateIfoGrayOrderStatus(HostFotypeOrderAllRow)";
        log.entering(STR_METHOD_NAME);
        
        // Mapオブジェクトの生成
        Map l_mapStatus = new HashMap();
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" cancel_div = ? ");
        l_sbWhere.append(" and corp_code = ? ");
        l_sbWhere.append(" and all_order_change_div = ? ");
        l_sbWhere.append(" and status in (?,?) ");
        
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_hostFotypeRoｗ.getCancelDiv(),
                l_hostFotypeRoｗ.getCorpCode(),
                l_hostFotypeRoｗ.getAllOrderChangeDiv(),
                WEB3FrontOrderStatusDef.SENDED,
                WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE
            };

        l_mapStatus.put("status",WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
        l_mapStatus.put("last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        try{
            // Update処理
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateAllQuery(HostFotypeOrderAllRow.TYPE, l_sbWhere.toString(), l_objWhere, l_mapStatus);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }           
        log.exiting(STR_METHOD_NAME);                 
    }
    
    /**
     * 障害仮想サーバ切替管理テーブルに切替指示要求系レコードを挿入する。   <BR>
     * <BR>
     * [パラメータ]<BR>
     * 仮想サーバ情報テーブルRow<BR>
     * <BR>
     *  １）　@this.切替処理方式が通番照会処理の場合、通番照会要求レコードを登録<BR>
     * <BR>
     *  　@１－１）　@validate障害仮想サーバレコード（）を実行し、    <BR>
     *  　@　@　@　@　@　@障害仮想サーバ切替管理テーブルにキー重複が存在しないかチェックをおこなう。 <BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.仮想サーバNo.（市場）<BR>
     * 　@　@　@　@切替指示応答区分：　@"通番照会要求"<BR>
     * 　@　@　@　@通知種別：　@"デフォルト"<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     *  　@１－２）　@エラーがない場合、insert障害仮想サーバ切替管理テーブル（）を実行し、 <BR>
     *  　@　@　@　@　@　@通番照会要求レコードを登録する。  <BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@切替指示応答区分：　@"通番照会要求"<BR>
     * 　@　@　@　@通知種別：　@"デフォルト"<BR>
     * 　@　@　@　@仮想サーバ情報テーブルRow：　@仮想サーバ情報テーブルRow <BR>
     * <BR>
     *  ２）　@通知代行解除要求レコードを登録  <BR>
     * <BR>
     *  　@２－１）　@validate障害仮想サーバレコード（）を実行し、    <BR>
     *  　@　@　@　@　@　@障害仮想サーバ切替管理テーブルにキー重複が存在しないかチェックをおこなう。 <BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.仮想サーバNo.（市場）<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知代行解除要求"<BR>
     * 　@　@　@　@通知種別：　@"デフォルト"<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     *  　@２－２）　@エラーがない場合、insert障害仮想サーバ切替管理テーブル（）を実行し、 <BR>
     *  　@　@　@　@　@　@通知代行解除要求レコードを登録する。    <BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知代行解除要求"<BR>
     * 　@　@　@　@通知種別：　@"デフォルト"<BR>
     * 　@　@　@　@仮想サーバ情報テーブルRow：　@仮想サーバ情報テーブルRow<BR>
     * <BR>
     *  ３）　@通知代行要求レコードを登録    <BR>
     * <BR>
     *  　@３－１）　@validate障害仮想サーバレコード（）を実行し、    <BR>
     *  　@　@　@　@　@　@障害仮想サーバ切替管理テーブルにキー重複が存在しないかチェックをおこなう。 <BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.仮想サーバNo.（市場）<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知代行要求"<BR>
     * 　@　@　@　@通知種別：　@"デフォルト"<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     *  　@３－２）　@エラーがない場合、insert障害仮想サーバ切替管理テーブル（）を実行し、 <BR>
     *  　@　@　@　@　@　@通知代行要求レコードを登録する。  <BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知代行要求"<BR>
     * 　@　@　@　@通知種別：　@"デフォルト"<BR>
     * 　@　@　@　@仮想サーバ情報テーブルRow：　@仮想サーバ情報テーブルRow<BR>
     * <BR>
     *  ４）　@通知再送要求（受付系）レコードを登録   <BR>
     * <BR>
     *  　@４－１）　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、<BR>
     * <BR>
     * 　@　@　@４－１－１）　@validate障害仮想サーバレコード（）を実行し、<BR>
     *  　@　@　@　@　@　@　@　@　@障害仮想サーバ切替管理テーブルにキー重複が存在しないかチェックをおこなう。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.仮想サーバNo.（市場）<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知再送要求"<BR>
     * 　@　@　@　@通知種別：　@"受付系"<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     * 　@　@　@４－１－２）　@エラーがない場合、insert障害仮想サーバ切替管理テーブル（）を実行し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@通知再送要求（受付系）レコードを登録する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知再送要求"<BR>
     * 　@　@　@　@通知種別：　@"受付系"<BR>
     * 　@　@　@　@仮想サーバ情報テーブルRow：　@仮想サーバ情報テーブルRow<BR>
     * <BR>
     *  ５）　@通知再送要求（約定系）レコードを登録   <BR>
     * <BR>
     *  　@５－１）　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、<BR>
     * <BR>
     * 　@　@　@５－１－１）　@validate障害仮想サーバレコード（）を実行し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@障害仮想サーバ切替管理テーブルにキー重複が存在しないかチェックをおこなう。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@仮想サーバNo.（市場）：　@仮想サーバ情報テーブルRow.get仮想サーバNo.（市場）<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知再送要求"<BR>
     * 　@　@　@　@通知種別：　@"約定系"<BR>
     * 　@　@　@　@フロント発注取引所区分コード：　@仮想サーバ情報テーブルRow.フロント発注取引所区分コード<BR>
     * <BR>
     * 　@　@　@５－１－２）　@エラーがない場合、insert障害仮想サーバ切替管理テーブル（）を実行し、<BR>
     * 　@　@　@　@　@　@　@　@　@　@通知再送要求（約定系）レコードを登録する。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@切替指示応答区分：　@"通知再送要求"<BR>
     * 　@　@　@　@通知種別：　@"約定系"<BR>
     * 　@　@　@　@仮想サーバ情報テーブルRow：　@仮想サーバ情報テーブルRow<BR>
     * <BR>
     * @@param 仮想サーバ情報テーブルRow - 仮想サーバ情報テーブルRow<BR>
     * @@roseuid 
     */
    private void executeVirtualServChange(VirtualServerInformationRow l_priVirServInfoRow) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "executeVirtualServChange(VirtualServerInformationRow)";
        log.entering(STR_METHOD_NAME);
        
        // 仮想サーバNo(市場)取得
        String l_virServerNumMarket = l_priVirServInfoRow.getVirtualServerNumberMarket();
        // フロント発注取引所区分コード取得
        String l_frotnExCode = l_priVirServInfoRow.getFrontOrderExchangeCode();
        // 障害仮想サーバ重複キー区分
        boolean l_isrepeat = false;

        // 切替処理方式が通番照会処理の場合、通番照会要求レコードを登録
        if(this.changeProcessDiv.equals(WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE)) 
        {
            // 障害仮想サーバ切替管理テーブル、レコード重複チェック
            l_isrepeat = this.validateVirtualServerRecord(l_virServerNumMarket, WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST , 
                                                            WEB3NoticeTypeDef.DEFAULT, l_frotnExCode);
                    
            // 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
            if(l_isrepeat)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                this.insertVirtualServerChange(WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST, 
                                                        WEB3NoticeTypeDef.DEFAULT, l_priVirServInfoRow);
            }
        }
        // 通知代行解除要求レコードを登録
        // 障害仮想サーバ切替管理テーブル、レコード重複チェック
        l_isrepeat = this.validateVirtualServerRecord(l_virServerNumMarket, WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST,
                                                             WEB3NoticeTypeDef.DEFAULT, l_frotnExCode);
                    
        // 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
        if(l_isrepeat)
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            this.insertVirtualServerChange(WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST, 
                                                WEB3NoticeTypeDef.DEFAULT, l_priVirServInfoRow);
        }
        // 通知代行要求レコードを登録
        // 障害仮想サーバ切替管理テーブル、レコード重複チェック
        l_isrepeat = this.validateVirtualServerRecord(l_virServerNumMarket, WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST, 
                                                            WEB3NoticeTypeDef.DEFAULT, l_frotnExCode);
                    
        // 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
        if(l_isrepeat)
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            this.insertVirtualServerChange(WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST, 
                                                    WEB3NoticeTypeDef.DEFAULT, l_priVirServInfoRow);
        }
        // 通知再送要求（受付系）レコードを登録
        //仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、
        if (!(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_frotnExCode)
            || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_frotnExCode)))
        {
            // 障害仮想サーバ切替管理テーブル、レコード重複チェック
            l_isrepeat = this.validateVirtualServerRecord(
                l_virServerNumMarket,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                WEB3NoticeTypeDef.ACCEPT_TYPE,
                l_frotnExCode);

            // 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
            if (l_isrepeat)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                this.insertVirtualServerChange(
                    WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                    WEB3NoticeTypeDef.ACCEPT_TYPE,
                    l_priVirServInfoRow);
            }
        }
        // 通知再送要求（約定系）レコードを登録
        //仮想サーバ情報テーブルRow.フロント発注取引所区分コード != "東証" or "名証"の場合、
        if (!(WEB3FrontOrderExchangeCodeDef.TOKYO_STOCK_EXCHANGE.equals(l_frotnExCode)
            || WEB3FrontOrderExchangeCodeDef.NAGOYA_STOCK_EXCHANGE.equals(l_frotnExCode)))
        {
            // 障害仮想サーバ切替管理テーブル、レコード重複チェック
            l_isrepeat = this.validateVirtualServerRecord(
                l_virServerNumMarket,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                WEB3NoticeTypeDef.EXECUTED_TYPE,
                l_frotnExCode);

            // 障害仮想サーバ切替管理テーブルにレコードが存在する場合、エラーを返却する。
            if (l_isrepeat)
            {
                log.error(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02208,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else
            {
                 this.insertVirtualServerChange(
                     WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST,
                     WEB3NoticeTypeDef.EXECUTED_TYPE,
                     l_priVirServInfoRow);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
