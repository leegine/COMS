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
filename	WEB3AdminDirSecFrontOrderCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (フロント発注管理共通サービスImpl) (WEB3AdminDirSecFrontOrderCommonServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.118
Revesion History : 2007/02/17  李木子 (中訊) 実装の問題No.004
Revesion History : 2007/03/01  謝旋 (中訊) 仕様変更モデルNo.023,025,No.041-049
Revesion History : 2007/02/28  孟亜南 (中訊) 仕様変更モデルNo.059-No.060,No.090-093
Revesion History : 2009/05/21  張騰宇 (中訊) 仕様変更モデルNo.153
*/
package webbroker3.dirsec.service.delegate.stdimpls;

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

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeReqResDivDef;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3NoticeTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.dirsec.WEB3FrontOrderSwitchManagement;
import webbroker3.dirsec.define.WEB3AdminFrontDataCodeDef;
import webbroker3.dirsec.define.WEB3AdminFrontHostStatusDivDef;
import webbroker3.dirsec.define.WEB3AdminFrontSwitchStartDivDef;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.VirtualServerChangeDao;
import webbroker3.gentrade.data.VirtualServerChangeRow;
import webbroker3.gentrade.data.VirtualServerInformationRow;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者フロント発注管理共通サービスImpl)<BR>
 * <BR>
 * 管理者フロント発注管理共通サービスImplクラス<BR>
 * <BR>
 * WEB3AdminDirSecFrontOrderCommonServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminDirSecFrontOrderCommonServiceImpl implements WEB3AdminDirSecFrontOrderCommonService {
    
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecFrontOrderCommonServiceImpl.class);
    
    /**
     * 発注先切替テーブルより、条件に該当するレコードを取得し、<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件で発注先切替テーブルを検索する。<BR>
     * 　@※銘柄タイプ、市場コード、フロント発注システム区分、発注経路区分の昇順でソートする。<BR>
     * <BR>
     * 　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@銘柄タイプ in (1：株式, 6：先物オプション) and 
     * 　@市場コード in (1：東京, 2：大阪, 3：名古屋, 9：NNM, 10：JASDAQ) 
     * <BR>
     * 　@検索結果が取得できなかった場合、例外をスローする。<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@param 銘柄タイプ - 銘柄タイプ<BR>
     * @@return 発注先切替Row[]<BR>
     * @@roseuid 42D252DF0062
     */
    public OrderSwitchingRow[] getOrderRouteSwitchingRows(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getOrderRouteSwitchingRows(String)";
        log.entering(STR_METHOD_NAME);

        OrderSwitchingRow[] l_row = null;
        
        // ArrayListオブジェクトの生成
        List l_lstSwitchRows = new ArrayList(); 
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type in (?,?) ");
        l_sbWhere.append(" and market_code in (?,?,?,?,?) ");

        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                Integer.toString(ProductTypeEnum.EQUITY.intValue()),
                Integer.toString(ProductTypeEnum.IFO.intValue()),
                WEB3MarketCodeDef.TOKYO,
                WEB3MarketCodeDef.OSAKA,
                WEB3MarketCodeDef.NAGOYA,
                WEB3MarketCodeDef.NNM,
                WEB3MarketCodeDef.JASDAQ
            };        

        try{
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRows = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                "product_type asc, market_code asc, front_order_system_code asc, submit_order_route_div asc",
                null,
                l_objWhere);
            
            // 検索結果が存在しない場合、エラーメッセージをスローする。
            if(l_lstSwitchRows.size() == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                 
            }
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
        
        l_row = new OrderSwitchingRow[l_lstSwitchRows.size()];
        
        l_lstSwitchRows.toArray(l_row);
        
        return l_row;
    }
    
    /**
     * 障害仮想サーバ切替管理テーブルに"SONAR全障害"の<BR>
     * レコードが存在するかどうかチェックする。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件にて、障害仮想サーバ切替管理テーブルを<BR>
     * 　@検索する。<BR>
     * <BR>
     * 　@切替指示応答区分 = 09：SONAR全障害 and<BR>
     * 　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@フロント発注取引所区分コード = パラメータ.フロント発注取引所区分コード And<BR>
     * 　@フロント発注システム区分 = パラメータ.フロント発注システム区分 And<BR>
     * 　@フロント発注取引区分コード = 1：株券売買 and<BR>
     * 　@銘柄タイプ = パラメータ.銘柄タイプ<BR>
     * <BR>
     * 　@検索結果が取得できた場合、例外をスローする。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@param フロント発注取引所区分コード - フロント発注取引所区分コード<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分<BR>
     * @@param 銘柄タイプ - 銘柄タイプ<BR>
     * @@roseuid 42D24F1E0275
     */
    public void validateSonarCheck(String l_institutionCode, String l_frontExCode, 
                                    String l_frontSystemCode, String l_strProductType) throws WEB3BusinessLayerException, WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "validateSonarCheck(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" change_req_res_div = ? ");
        l_sbWhere.append(" and institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");

        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                WEB3ChangeReqResDivDef.SONAR_ALL_TROUBLES,
                l_institutionCode,
                l_frontExCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType
            };        
        
        try{
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
                
            // 検索結果が存在する場合、エラーメッセージをスローする。
            if(l_lstSwitchRoutes.size() != 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02213,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                 
            }
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
    }

    /**
     * 切替起動区分の値に基づき、切替処理を実行する。<BR>
     * <BR>
     * １）　@getデータコード（）で、データコードを取得する。<BR>
     * <BR>
     * ２）　@getユーザデータ（）で、ユーザデータを取得する。<BR>
     * <BR>
     * 　@ 　@[引数] <BR>
     * 　@ 　@変換市場コード： パラメータ.リクエスト.変換市場コード <BR>
     * 　@ 　@銘柄タイプ： パラメータ.リクエスト.銘柄タイプ <BR>
     * 　@ 　@切替起動区分： パラメータ.リクエスト.切替起動区分 <BR>
     * <BR>
     * ３）　@if　@切替起動区分 == "0:発注経路切替"の場合<BR>
     * <BR>
     * 　@３－１）　@発注経路切替処理クラスのインスタンスを生成する。<BR>
     * <BR>
     * 　@３－２）　@オブジェクト.execute発注経路切替（）で、発注経路新規実行処理を行う。<BR>
     * <BR>
     * <BR>
     * ４）　@else　@if　@切替起動区分 == "6:全訂正処理再起動"の場合<BR>
     * <BR>
     * 　@４－１） MAXASトリガーを発行。　@<BR>
     * <BR>
     * 　@　@　@　@　@　@new WEB3MQMessageSpec(パラメータ.リクエスト.証券会社コード , １）のデータコード, ２）のユーザデータ)<BR>
     * 　@　@　@　@　@　@WEB3MQGatewayService.send(メッセージ内容：WEB3MQMessageSpec)<BR>
     * <BR>
     * 　@４－２）　@returnする。<BR>
     * <BR>
     * ５）　@else<BR>
     * <BR>
     * 　@　@５－１）　@get障害仮想サーバ切替管理テーブルレコード（）で、要求系レコードが処理済のレコードを検索する。<BR>
     * <BR>
     * <BR>
     * 　@　@５－２）　@５－１）で、取得したレコードのサイズ分Loop処理を行う。<BR>
     * <BR>
     * 　@　@　@５－２－１）　@get切替指示応答系区分（）で、応答系区分を取得。<BR>
     * <BR>
     * 　@　@　@５－２－２）　@VirtualServerChangeDao.findRowByPk()で、応答系のレコードを取得。<BR>
     * 得。<BR>
     * <BR>
     * 　@　@　@５－２－３）　@５－２－２）でレコードが存在しない場合<BR>
     * 　@　@　@ 　@　@　@５－２－３－１）　@set切替管理テーブル更新項目（）で、<BR>
     * 　@　@　@ 　@　@　@障害仮想サーバ切替管理テーブルを更新するパラメータを取得する。<BR>
     * <BR>
     * 　@　@　@ 　@　@　@ 　@　@　@[引数]<BR>
     * 　@　@　@ 　@　@　@ 　@　@　@なし<BR>
     * <BR>
     * 　@　@　@ 　@　@　@５－２－３－２）　@doUpdateQuery（）で、要求系処理済レコードを未処理に更新する。<BR>
     * <BR>
     * 　@　@５－３）　@MAXASトリガーを発行。<BR>
     * 　@　@ 　@　@new WEB3MQMessageSpec(パラメータ.リクエスト.証券会社コード , １）のデータコード, ２）のユーザデータ)<BR>
     * 　@　@ 　@　@WEB3MQGatewayService.send(メッセージ内容：WEB3MQMessageSpec)<BR>
     * <BR>
     * 　@　@　@　@　@　@　@WEB3MQMessageSpec = new WEB3MQMessageSpec(証券会社コード , <BR>
     * データコード ,ユーザデータ)<BR>
     * <BR>
     * @@param リクエストオブジェクト - 発注経路切替完了リクエスト。<BR>
     * @@roseuid 42F19DD600EA
     */
    public void executeSwitchTransactionIssue(WEB3AdminFrontRouteChangeCompleteRequest l_request) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "executeSwitchTransactionIssue(WEB3AdminFrontRouteChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        // 証券会社コード
        String l_institutionCode = l_request.institutionCode;
        // 切替処理方式区分
        String l_changeProcessDiv = l_request.changeProcessDiv;
        // 切替起動区分
        String l_changeStartDiv = l_request.changeStartDiv;
        // トリガー発行を行うためのデータコードを取得
        String l_dataCode = this.getDataCode(l_changeStartDiv,l_changeProcessDiv);
        // トリガー発行を行うためのユーザデータを取得
        String l_userData = this.getUserData(l_request.convertMarketCode,l_request.productType, l_changeStartDiv);
        // トリガー発行サービスインスタンス
        WEB3MQGatewayService l_gatewayService = (WEB3MQGatewayService)Services.getService(WEB3MQGatewayService.class);
        // 障害仮想サーバテーブル要求系・処理済List
        List l_lstReqVirtuals = new ArrayList();

        // 切替起動区分が発注経路切替の場合、発注経路切替処理クラスにて新規実行処理を行う。
        if(l_changeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH))
        {
            // 発注経路切替処理クラスオブジェクト
            WEB3FrontOrderSwitchManagement l_switchManagement = new WEB3FrontOrderSwitchManagement(this, l_request);   
            
            // 発注経路切替新規実行
            l_switchManagement.executeOrderRouteSwitching();           
        }
        // 切替起動区分が全訂正処理再起動の場合、トリガー発行処理を行う。
        else if(l_changeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.ALLCORR_REQ_RESTART))
        {
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(l_institutionCode, l_dataCode, l_userData);
            
            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);
            
            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> 全訂正処理再起動送信 成功！！！");
            }
            else
            {
                log.debug("==> 全訂正処理再起動送信 失敗 ！！！");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            return;
        }
        else
        {
            // 要求系で処理済のレコードを取得
            l_lstReqVirtuals = this.getVirtualServerChangeRecord(l_request);

            //Loop処理
            Iterator  virtualsObj = l_lstReqVirtuals.iterator(); 
            
            while(virtualsObj.hasNext())
            {
                // VirtualServerChangeRowオブジェクトの抽出
                VirtualServerChangeRow l_changeRow = (VirtualServerChangeRow)virtualsObj.next();
                
                // 応答系区分を取得
                String  l_resDiv = this.getSwitchPointResponseDiv(l_changeStartDiv);
                
                // 取得した要求系・処理済レコードを基に、応答系レコードを取得
                try
                {
                    VirtualServerChangeDao.findRowByPk(l_changeRow.getVirtualServerNumberMarket(),l_resDiv,
                                            l_changeRow.getNoticeType(), l_changeRow.getFrontOrderExchangeCode());                    
                }
                // レコードが存在しない場合、要求系処理済レコードを未処理に更新する。
                catch(DataFindException notData)
                {
                    try
                    {
                        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                        l_queryProcessor.doUpdateQuery(l_changeRow.getPrimaryKey(), this.setVirtualServerChangeUpdateCalums());
                    }
                    catch (DataException l_de)
                    {
                        log.error(STR_METHOD_NAME, l_de);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_de.getMessage(),
                        l_de);
                    }
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
            }
            // トリガー発行処理を行う。
            WEB3MQMessageSpec spec = new WEB3MQMessageSpec(l_institutionCode, l_dataCode, l_userData);
            
            WEB3MQSendResult l_web3MQSendResult = l_gatewayService.send(spec);
            
            if (l_web3MQSendResult.isSuccessResult())
            {
                log.debug("==> 再起動送信 成功！！！");
            }
            else
            {
                log.debug("==> 再起動送信 失敗 ！！！");
                throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }

    /**
     * 引数の発注先切替Rowsより発注先情報の一覧を<BR>
     * 作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.発注先切替Rowsの要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@２－１）　@発注先情報インスタンスを生成する。<BR>
     * 　@２－２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@変換市場コード = getフロント発注市場コード（発注先切替Row.市場コード, <BR>
     * 発注先切替Row.フロント発注システム区分）<BR>
     * 　@　@市場コード = 発注先切替Row.市場コード<BR>
     * 　@　@フロント発注システム区分 = 発注先切替Row.フロント発注システム区分<BR>
     * 　@　@銘柄タイプ = 発注先切替Row.銘柄タイプ<BR>
     * 　@　@発注経路 = 発注先切替Row.発注経路区分<BR>
     * 　@　@有効フラグ = 発注先切替Row.有効フラグ<BR>
     * <BR>
     * 　@２－３）　@プロパティセットしたインスタンスをArrayListに追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param 発注先切替Rows - 発注先切替Rowオブジェクトの配列<BR>
     * @@return webbroker3.dirsec.message.WEB3AdminOrderRouteInfo[]<BR>
     * @@roseuid 42D25498035F
     */
    public WEB3AdminOrderRouteSwitchingInfo[] createSwitchRouteInfoList(OrderSwitchingRow[] l_switchRows)    
    {
        final String STR_METHOD_NAME = "createSwitchRouteInfoList(OrderSwitchingRow[])";
        log.entering(STR_METHOD_NAME);
        
        // WEB3AdminOrderRouteSwitchingInfo[]型の配列を生成
        WEB3AdminOrderRouteSwitchingInfo[] routeInfo = null;
        
        // ArrayListの生成
        List l_lstSwitchInfos = new ArrayList();
        
        for(int i = 0; l_switchRows.length > i ; i++)
        {
            // 発注先情報インスタンス生成
            WEB3AdminOrderRouteSwitchingInfo l_switchInfo = new WEB3AdminOrderRouteSwitchingInfo();

            // 変換市場コードを格納 
            l_switchInfo.convertMarketCode = this.getFrontOrderMarketCode(l_switchRows[i].getMarketCode(), 
                                                                            l_switchRows[i].getFrontOrderSystemCode());

            // 市場コードを格納
            l_switchInfo.marketCode = l_switchRows[i].getMarketCode();
            
            // フロント発注システム区分を格納
            l_switchInfo.frontOrderSystemCode = l_switchRows[i].getFrontOrderSystemCode();
            
            // 銘柄タイプを格納
            l_switchInfo.productType = Integer.toString(l_switchRows[i].getProductType().intValue());
            
            // 発注経路区分を格納
            l_switchInfo.submitOrderRouteDiv = l_switchRows[i].getSubmitOrderRouteDiv();
            
            // 有効フラグを格納
            l_switchInfo.validFlag = l_switchRows[i].getValidFlag();
            
            // 発注先情報インスタンスをListに格納
            l_lstSwitchInfos.add(l_switchInfo);
        }
        // Listのサイズ分、配列オブジェクトを生成
        routeInfo = new WEB3AdminOrderRouteSwitchingInfo[l_lstSwitchInfos.size()];
        
        // Listの内容を配列型に変換
        l_lstSwitchInfos.toArray(routeInfo);

        log.exiting(STR_METHOD_NAME);
        return routeInfo;
    }

    /**
     * 発注先切替テーブルからデータを検索し、結果を返却する。<BR>
     * <BR>
     * １）　@Listオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@DB検索<BR>
     * 　@以下の条件で発注先切替テーブルを検索する。<BR>
     * ※銘柄タイプ、市場コード、フロント発注システム区分の昇順でソートする。<BR>
     * <BR>
     * 　@証券会社コード = パラメータ.証券会社コード and <BR>
     * 　@銘柄タイプ in (1：株式, 6：先物オプション)  and <BR>
     * 　@発注経路区分 = "３：フロント発注副系"<BR>
     * <BR>
     * ４）　@検索結果が0件の場合、エラーコードをスローする。<BR>
     * <BR>
     * ５）　@取得した結果をListオブジェクトに格納する。<BR>
     * <BR>
     * ６）　@Listオブジェクトを返却する。<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@return List<BR>
     * @@roseuid 42E46BB2019E
     */
    public List getFrontSwitchRouteTarget(String l_strInstitutionCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFrontSwitchRouteTarget(String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type in ( ?,?) ");
        l_sbWhere.append(" and submit_order_route_div = ? ");

        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                Integer.toString(ProductTypeEnum.EQUITY.intValue()),
                Integer.toString(ProductTypeEnum.IFO.intValue()),
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION
            };        
        
        try{
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                "product_type asc, market_code asc, front_order_system_code asc",
                null,
                l_objWhere);
            
            // 検索結果が0件の場合、エラーメッセージをスローする。
            if(l_lstSwitchRoutes.size() == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                    this.getClass().getName() + "." + STR_METHOD_NAME);                 
            }
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
        return l_lstSwitchRoutes;
    }

    /**
     * 仮想サーバ情報テーブルから、仮想サーバ件数を取得し、処理件数情報オブジェクトに格<BR>
     * 納する。<BR>
     * <BR>
     * １） フロント発注取引所区分コードを取得する。 <BR>
     *   １－１）　@パラメータ.銘柄タイプが1：株式の場合、<BR>
     *      株式発注サービス.getフロント発注取引所区分コード（）<BR>
     *    [引数] <BR>
     *    市場コード： パラメータ.市場コード <BR>
     * <BR>
     *   １－２）　@パラメータ.銘柄タイプが6：先物オプションの場合、<BR>
     *      先物OP発注サービス.getフロント発注取引所区分コード（） <BR>
     *      [引数] <BR>
     *      市場コード： パラメータ.市場コード <BR>
     * <BR>     
     * ２）　@DB検索<BR>
     * <BR>
     *  条件：証券会社コード = パラメータ.証券会社コード and <BR>
     *         フロント発注取引所区分コード = <BR>
     * １）で取得したフロント発注取引所区分コード and <BR>
     *         フロント発注システム区分 = パラメータ.フロント発注システム区分 and <BR>
     *         フロント発注取引区分コード = "１：株券売買" and <BR>
     *         銘柄タイプ = パラメータ.銘柄タイプ <BR> 
     * <BR>
     * ３）　@取得件数情報オブジェクト.仮想サーバ件数 = 検索結果件数<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 処理件数情報オブジェクト - 処理件数情報オブジェクト。<BR>
     * @@roseuid 42E467C801CC
     */
    public void getVitualServerCount(String l_institutionCode, String l_marketCode, String l_frontSystemCode,String l_strProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getVitualServerCount(String, String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // フロント発注取引所区分コード
        String l_frontExchangeCode = null;

        // 市場コードを基に、フロント発注取引所区分コードを取得
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        // 先物OP発注サービス
        WEB3IfoFrontOrderService l_orderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        
        // パラメータ.銘柄タイプが1：株式の場合、株式発注サービス.getフロント発注取引所区分コード（）
        if (Integer.toString(ProductTypeEnum.EQUITY.intValue()).equals(l_strProductType))
        {
            l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        }
        
        if (Integer.toString(ProductTypeEnum.IFO.intValue()).equals(l_strProductType))
        {
            l_frontExchangeCode = l_orderService.getFrontOrderExchangeCode(l_marketCode);
        }
        
        // ArrayListオブジェクトの生成
        List l_lstVirtualCounts = new ArrayList(); 
        
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
                l_institutionCode,
                l_frontExchangeCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType
            };        
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstVirtualCounts = l_queryProcessor.doFindAllQuery(
                VirtualServerInformationRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
            
            // 検索結果件数を、処理件数情報クラス.仮想サーバ件数に格納
            l_processInfoUnit.virtualServerQuantity = Integer.toString(l_lstVirtualCounts.size());
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
    }

    /**
     * 障害仮想サーバ管理テーブルから切替指示応答区分が応答系のレコードを取得し、結果を返却する。<BR>
     * <BR>
     * １）　@株式発注サービス.getフロント発注取引所区分コード（）から、フロント発注取引<BR>
     * 所区分コードを取得する。<BR>
     * <BR>
     * ２）　@Listオブジェクトを生成する。<BR>
     * <BR>
     * ３）　@DB検索<BR>
     * 　@以下の条件で障害仮想サーバ管理テーブルを検索する。<BR>
     * <BR>
     * 証券会社コードコード =１）で取得した証券会社コード and <BR>
     * フロント発注取引所区分コード =１）で取得したフロント発注取引所区分コード and <BR>
     * フロント発注システム区分 = パラメータ.フロント発注システム区分 and <BR>
     * フロント発注取引区分コード = "１：株券売買" and <BR>
     * 銘柄タイプ = パラメータ.銘柄タイプ and<BR>
     * 切替指示応答区分 in ('02:通番照会応答' , <BR>
     *                              '04:通知代行解除応答' , <BR>
     *                              '06:通知代行応答' , <BR>
     *                              '08:通知再送応答')<BR>
     * <BR>
     * ４）　@取得した結果をListオブジェクトに格納する。<BR>
     * <BR>
     * ５）　@Listオブジェクトを返却する。<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ。<BR>
     * @@return List<BR>
     * @@roseuid 42E4A1EA0315
     */
    public List getSwitchRouteResRcord(String l_institutionCode, 
        String l_marketCode, 
        String l_frontSystemCode, 
        String l_strProductType) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getSwitchRouteResRcord(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // フロント発注取引所区分コード
        String l_frontExchangeCode = null;
        // 応答系レコードList
        List l_lstResRcords = null;

        // 市場コードを基に、フロント発注取引所区分コードを取得
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and change_req_res_div in (?,?,?,?) ");   
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_institutionCode,
                l_frontExchangeCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType, 
                WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_RESPONSE,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_RESPONSE,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_RESPONSE,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE                
            };        
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstResRcords = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
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

        return l_lstResRcords;
    }

    /**
     * 障害仮想サーバ管理テーブルから切替指示応答区分が要求系のレコードを取得し、結果を<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@株式発注サービス.getフロント発注取引所区分コード（）から、フロント発注取引<BR>
     * 所区分コードを取得する。<BR>
     * <BR>
     * ２）　@Listオブジェクトを生成する。<BR>
     * <BR>
     * ３）　@DB検索<BR>
     * 　@以下の条件で障害仮想サーバ管理テーブルを検索する。<BR>
     * <BR>
     * 証券会社コード = パラメータ.証券会社コード and 
     * フロント発注取引所区分コード =１）で取得したフロント発注取引所区分コード and <BR>
     * フロント発注システム区分 = パラメータ.フロント発注システム区分 and <BR>
     * フロント発注取引区分コード = "１：株券売買" and <BR>
     * 銘柄タイプ = パラメータ.銘柄タイプ and<BR>
     * 切替指示応答区分 in ('01:通番照会要求' , <BR>
     *                              '03:通知代行解除要求' , <BR>
     *                              '05:通知代行要求' , <BR>
     *                              '07:通知再送要求')<BR>
     * <BR>
     * ４）　@取得した結果をListオブジェクトに格納する。<BR>
     * <BR>
     * ５）　@Listオブジェクトを返却する。<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ。<BR>
     * @@return List<BR>
     * @@roseuid 42FC54A400A2
     */
    public List getSwitchRouteReqRcord(String l_institutionCode, 
        String l_marketCode, 
        String l_frontSystemCode, 
        String l_strProductType) throws WEB3SystemLayerException  
    {
        final String STR_METHOD_NAME = "getSwitchRouteReqRcord(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // フロント発注取引所区分コード
        String l_frontExchangeCode = null;
        // 要求系レコードList
        List l_lstReqRcords = null;

        // 市場コードを基に、フロント発注取引所区分コードを取得
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and change_req_res_div in (?,?,?,?) ");   
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_institutionCode,
                l_frontExchangeCode,
                l_frontSystemCode,
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                l_strProductType,
                WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST,
                WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST,
                WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST                
            };        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstReqRcords = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
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

        return l_lstReqRcords;
    }

    /**
     * 発注先切替テーブルから現発注経路を検索し、結果を取得する。<BR>
     * <BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件で発注先切替テーブルを検索する。<BR>
     * <BR>
     * 証券会社コード = パラメータ.証券会社コード and <BR>
     * 銘柄タイプ = パラメータ.銘柄タイプ and <BR>
     * 市場コード = パラメータ.市場コード and <BR>
     * フロント発注システム区分 = パラメータ.フロント発注システム区分 and <BR>
     * 有効フラグ = "１：ON"<BR>
     * <BR>
     * ２）　@if　@検索結果が0件の場合、"９：発注停止"を処理件数情報クラスに格納する。<BR>
     * <BR>
     * ３）　@else　@取得した結果を処理件数情報クラスに格納する。<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ<BR>
     * @@param 処理件数情報クラス - 処理件数情報クラス。<BR>
     * @@return String<BR>
     * @@roseuid 42EE00700203
     */
    public String getNowOrderRoute(String l_strInstitutionCode, String l_strMarketCode, String l_strFrontSystemCode, String l_strProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getNowOrderRoute(String, String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_code = ? ");        
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and valid_flag = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strProductType,
                l_strMarketCode,
                l_strFrontSystemCode,
                WEB3ValidFlag.ON
            };        
        
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
            
            // 検索結果が0件の場合、"９：発注停止"をセット
            if(l_lstSwitchRoutes.size() == 0)
            {
                l_processInfoUnit.submitOrderRouteDiv = WEB3SubmitOrderRouteDivDef.ORDER_STOP;
                
                return WEB3SubmitOrderRouteDivDef.ORDER_STOP;                  
            }
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
        
        // 発注経路区分をセット
        OrderSwitchingRow l_swtichRow = (OrderSwitchingParams)l_lstSwitchRoutes.get(0);
        
        l_processInfoUnit.submitOrderRouteDiv = l_swtichRow.getSubmitOrderRouteDiv();
        
        return l_swtichRow.getSubmitOrderRouteDiv();
    }

    /**
     * 株式注文取引キューテーブルから、"市場受付確認前"、"市場受付確認中"、<BR>
     * "市場受付確認済"の注文件数を取得し、処理件数オブジェクトに格納する。<BR>
     * <BR>
     *１）　@株式発注サービス.getフロント発注取引所区分コード（）から、<BR>
     *　@　@　@フロント発注取引所区分コードを <BR>
     *　@　@　@取得する。 <BR>
     *<BR>
     *　@[引数] <BR>
     *　@市場コード： パラメータ.市場コード <BR>
     *<BR>
     * ２）　@"市場受付確認前"、"市場受付確認中"、"市場受付確認済"のデータをLoop処理を行い、取得する。<BR>
     * <BR>
     * for(要求データサイズ)<BR>
     * <BR>
     * 　@２－１）　@条件文字列を生成する。<BR>
     * <BR>
     * 　@　@証券会社コード = ？ and <BR>
     * 　@　@フロント発注取引所区分コード = ？ and <BR>
     * 　@　@フロント発注システム区分 = ？ and <BR>
     * 　@　@フロント発注取引区分コード = ？ and <BR>
     * 　@　@発注経路区分 = ？ and <BR>
     * 　@　@if( "市場受付確認前" )<BR>
     * <BR>
     * 　@　@　@ 処理区分 in (? , ?)<BR>
     * <BR>
     * 　@　@else("市場受付確認中" or "市場受付確認済")<BR>
     * <BR>
     * 　@　@　@ 処理区分 = ?<BR>
     * <BR>
     * 　@２－２）　@検索条件データコンテナを作成する。<BR>
     * <BR>
     * 　@　@２－２－１）　@ArrayListを生成する。<BR>
     * <BR>
     * 　@　@２－２－２）　@パラメータ.証券会社コードをArrayListに追加する。<BR>
     * <BR>
     * 　@　@２－２－３）　@１）で取得したフロント発注取引所区分コードをArrayListに追加する。 <BR>
     * <BR>
     * 　@　@２－２－４）　@パラメータ.フロント発注システム区分をArrayListに追加する。<BR>
     * <BR>
     * 　@　@２－２－５）　@"１：株券売買"をArrayList追加する。<BR>
     * <BR>
     * 　@　@２－２－６）　@if( "市場受付確認前" , "市場受付確認中" )<BR>
     * 　@　@　@　@　@　@　@　@　@　@"２：フロント発注正系"をArrayListに追加する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@else（ "市場受付確認済" ）　@<BR>
     * 　@　@　@　@　@　@　@　@　@　@"３：フロント発注副系"をArrayListに追加する。<BR>
     * <BR>
     * 　@　@２－２－７）　@if( "市場受付確認前" )<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@"１：送信中"をArrayListに追加する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@"２：AMG入力完了"をArrayListに追加する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@else if（ "市場受付確認中" , "市場受付確認済" ）　@<BR>
     *   　@　@　@　@　@　@　@　@　@　@"９：市場受付確認中"をArrayListに追加する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@else（"市場受付確認済" ）　@<BR>
     *   　@　@　@　@　@　@　@　@　@　@"８：市場受付確認済"をArrayListに追加する。<BR>
     * <BR>
     * 　@２－３）　@DB検索<BR>
     * <BR>
     * 　@２－４）　@処理件数情報オブジェクトに件数を格納する。<BR>
     * <BR>
     * 　@　@２－４－１）　@if（"市場受付確認前"）<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@処理件数情報オブジェクト.確認前件数 = データ取得サイズ<BR>
     * <BR>
     * 　@　@２－４－２）　@if（"市場受付確認中"）<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@処理件数情報オブジェクト.確認中件数 = データ取得サイズ<BR>
     * <BR>
     * 　@　@２－４－３）　@if（"市場受付確認済"）<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@処理件数情報オブジェクト.確認済件数 = データ取得サイズ　@<BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード - 市場コード。<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@param 処理件数オブジェクト - 処理件数オブジェクト。<BR>
     * @@roseuid 42EE05720280
     */
    public void getGrayOrder(String l_institutionCode, String l_marketCode, 
            String l_frontSystemCode, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getGrayOrder(String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // フロント発注取引所区分コード
        String l_frontExchangeCode = null;

        // 市場コードを基に、フロント発注取引所区分コードを取得
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        
        l_frontExchangeCode = l_frontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // キューテーブル受付処理区分をListに格納
        List l_lstStatusDivs = new ArrayList();
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE);
        
        // Listサイズ分、loop処理を行う。
        for(int lstSize = 0; l_lstStatusDivs.size() > lstSize ; lstSize++)
        {
            // ArrayListオブジェクトの生成
            List l_lstHostRcords = new ArrayList(); 
        
            // 検索条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and front_order_exchange_code = ? ");
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and front_order_trade_code = ? ");
            l_sbWhere.append(" and submit_order_route_div = ? ");            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_sbWhere.append(" and status in (?,?) ");                
            }
            else
            {
                l_sbWhere.append(" and status = ? ");            
            }
            
            // 検索条件コンテナの生成
            List l_lstWheres = new ArrayList();
            
            l_lstWheres.add(l_institutionCode);
            l_lstWheres.add(l_frontExchangeCode);
            l_lstWheres.add(l_frontSystemCode);
            l_lstWheres.add(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)) || 
                    WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION);
            }
            else{
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);            
            }
            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize))){
                // 送信済
                l_lstWheres.add(WEB3FrontOrderStatusDef.SENDED);
                // AMG入力完了
                l_lstWheres.add(WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize))){
                // 市場受付確認中
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize))){
                // 市場受付確認済
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMED);
            }

            // 配列に変換            
            Object[] l_objWhere = l_lstWheres.toArray();
 
            try
            {
                // DB検索
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lstHostRcords = l_queryProcessor.doFindAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
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
            
            // 市場確認前レコードの場合、処理件数情報オブジェクト.確認前件数にレコード件数を格納
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.beforeNumber = Integer.toString(l_lstHostRcords.size());
            }
            // 市場確認中レコードの場合、処理件数情報オブジェクト.確認中件数にレコード件数を格納
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.inNumber = Integer.toString(l_lstHostRcords.size());
            }
            // 市場確認済レコードの場合、処理件数情報オブジェクト.確認済件数にレコード件数を格納
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.afterNumber = Integer.toString(l_lstHostRcords.size());
            }                                        
        }
    }

    /**
     * データコードを返却する。<BR>
     * <BR>
     * ［パラメータ］<BR>
     * 切替起動区分<BR>
     * <BR>
     * １）　@データコードを生成する。<BR>
     * 　@１－１）　@if　@切替起動区分 == "０：発注経路切替"の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@String　@"データコード" = "ＡＸ0ＡＸ1"<BR>
     * <BR>
     * 　@１－２）　@if　@切替起動区分 == "１：通番照会要求再起動"の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@String　@"データコード" = "ＡＸ0ＡＸ1"<BR>
     * <BR>
     * 　@１－３）　@if　@切替起動区分 == "２：通知代行解除要求再起動"の場合<BR>
     * <BR>
     *                  if　@切替処理方式区分 == ”０：通番照会処理方式”の場合<BR>
     * 　@　@　@　@　@　@　@          String　@"データコード" = "ＡＸＺＹ1"<BR>
     *                  if　@切替処理方式区分 == ”１：全訂正処理方式”の場合<BR>
     * 　@　@　@　@　@　@　@          String　@"データコード" = "ＡＸ9Ｘ1"<BR>
     * <BR>
     * 　@１－４）　@if　@切替起動区分 == "３：通知代行要求再起動"の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@String　@"データコード" = "ＡＸ9Ｘ1"<BR>
     * <BR>
     * 　@１－５）　@if　@切替起動区分 == <BR>
     * "４：通知再送要求（受付系）再起動"or"５：通知再送要求（約定系）再起動"の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@String　@"データコード" = "ＡＸ8Ｘ1"<BR>
     * <BR>
     * 　@１－６）　@if　@切替起動区分 == "６：全訂正処理再起動"の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@String　@"データコード" = "ＡＸ2Ｘ1"<BR>
     * <BR>
     * ２）　@データコードを返却する。<BR>
     * @@param 切替起動区分 - 切替起動区分。<BR>
     * @@param 切替処理方式区分 - 切替処理方式区分。<BR>
     * @@return String<BR>
     * @@roseuid 42F1E259002E
     */
    public String getDataCode(String l_strSwitchBootDiv,String l_changeProcessDiv) 
    {
        // return値
        String retDataCode = null;
        
        // 切替起動区分 =発注経路切替の場合、通番照会要求コードをセット
        if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NUBER_REF_REQ_CODE;
        }
        // 切替起動区分 =通番照会要求再起動の場合、通番照会要求コードをセット
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NUBER_REF_REQ_CODE;
        }
        // 切替起動区分 =通知代行解除要求再起動の場合、通知代行解除要求コードをセット
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART))
        {
            // 切替処理方式区分が”0:通番照会処理方式”の場合
            if(l_changeProcessDiv.equals(WEB3AdminFrontSwitchStartDivDef.FRONT_ORDER_ROUTE_SWITCH))
            {
                retDataCode = WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REL_REQ_CODE;
            }
            // 切替処理方式区分が”1:全訂正処理方式”の場合
            else
            {
                retDataCode = WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REQ_CODE;
            }
        }
        // 切替起動区分 =通知代行要求再起動の場合、通知代行要求コードをセット
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NOTICEAGENCY_REQ_CODE;
        }
        // 切替起動区分 =通知再送要求（受付系）再起動 or 通知再送要求（約定系）再起動の場合、通知再送要求コードをセット
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                                                                                l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.NOTICERESEND_REQ_CODE;
        }
        // 切替起動区分 =全訂正処理再起動の場合、全訂正処理要求コードをセット
        else if(l_strSwitchBootDiv.equals(WEB3AdminFrontSwitchStartDivDef.ALLCORR_REQ_RESTART))
        {
            retDataCode = WEB3AdminFrontDataCodeDef.ALLCORR_REQ_CODE;
        }

        return retDataCode;
    }

    /**
     * ユーザデータを返却する。    <BR>
     * <BR>
     * １）切替起動区分 =通知再送要求（受付系）再起動 || 通知再送要求（約定系）再起動の場合、  <BR>
     * <BR>
     *  　@１－１）get通知種別（）で、通知種別を取得する。  <BR>
     * <BR>
     *  　@　@[引数]  <BR>
     *  　@　@切替起動区分 = パラメータ.切替起動区分 <BR>
     * <BR>
     *  　@１－２）String "ユーザデータ" = "パラメータ.変換市場コード" + "通知種別" <BR>
     * <BR>
     * ２）else ユーザデータ = パラメータ.変換市場コード   <BR>
     * <BR>
     * ３）ユーザデータ += パラメータ.銘柄タイプ <BR>
     * <BR>
     * ４）値を返却する。   <BR>
     * <BR>
     * @@param 変換市場コード - 画面表示用変換市場コード。<BR>
     * @@param 銘柄タイプ - 銘柄タイプ。<BR>
     * @@param 切替起動区分 - 切替起動区分。<BR>
     * @@return String<BR>
     * @@roseuid 42F1E26B0138
     */
    public String getUserData(String l_strConvertMarketCode, String l_strProductType, String l_strChangeStartDiv) 
    {
        // return値
        String retUserData = null;
        
        StringBuffer editUserCode = new StringBuffer();
        
        // 通知種別
        String noticeType = null;
        
        // 切替起動区分 =通知再送要求（受付系）再起動 || 通知再送要求（約定系）再起動の場合、通知種別＝"01" || "02"をセット
        if(l_strChangeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                l_strChangeStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART)) 
        {
            noticeType = this.getNoticeType(l_strChangeStartDiv);
            
            // 変換市場コードと、通知種別を結合。
            retUserData = editUserCode.append(l_strConvertMarketCode).append(noticeType).toString();
        }
        // 変換市場コードを格納
        else
        {
            retUserData = l_strConvertMarketCode;
        }
        
        retUserData += l_strProductType;

        return retUserData;
    }

    /**
     * 条件に応じて、切替指示応答区分の要求系を返却する。<BR>
     * <BR>
     * [パラメータ]<BR>
     * 切替起動区分<BR>
     * <BR>
     * <BR>
     * １）　@if 切替起動区分 == "1:通番照会要求再起動"<BR>
     * <BR>
     * 　@　@　@　@　@return　@"01:通番照会要求"<BR>
     * <BR>
     * ２）　@else if 切替起動区分 == "2:通知代行解除要求再起動"<BR>
     * <BR>
     * 　@　@　@　@　@return　@"03:通知代行解除要求"<BR>
     * <BR>
     * ３）　@else if 切替起動区分 == "3:通知代行要求再起動"<BR>
     * <BR>
     * 　@　@　@　@　@return　@"05:通知代行要求"<BR>
     * <BR>
     * ４）　@else if 切替起動区分 == <BR>
     * "4:通知再送要求（受付系）再起動"　@or　@5:通知再送要求（約定系）再起動<BR>
     * <BR>
     * 　@　@　@　@　@return　@"07:通知再送要求"<BR>
     * @@param 切替起動区分 - 切替起動区分。<BR>
     * @@return String<BR>
     * @@roseuid 430037A700A5
     */
    private String getSwitchPointReqDiv(String l_switchStartDiv)
    {
        // return値
        String l_reqDiv = null;

        // 切替起動区分が通番照会要求再起動の場合、通番照会要求を返す
        if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_REQUEST;
        }
        // 切替起動区分が通知代行解除要求再起動の場合、通知代行解除要求を返す
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_REQUEST;
        }
        // 切替起動区分が通知代行要求再起動の場合、通知代行要求を返す
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_REQUEST;
        }
        // 切替起動区分が通知再送要求（受付系）or通知再送要求（約定系）再起動の場合、通知再送要求を返す
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                    l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            l_reqDiv = WEB3ChangeReqResDivDef.NOTICE_RESEND_REQUEST;
        }
        
        return l_reqDiv;
    }

    /**
     * 条件に応じて、切替指示応答区分の応答系を返却する。<BR>
     * <BR>
     * [パラメータ]<BR>
     * 切替起動区分<BR>
     * <BR>
     * <BR>
     * １）　@if 切替起動区分 == "1:通番照会要求再起動"<BR>
     * <BR>
     * 　@　@　@　@　@return　@"02:通番照会応答"<BR>
     * <BR>
     * ２）　@else if 切替起動区分 == "2:通知代行解除要求再起動"<BR>
     * <BR>
     * 　@　@　@　@　@return　@"04:通知代行解除応答"<BR>
     * <BR>
     * ３）　@else if 切替起動区分 == "3:通知代行要求再起動"<BR>
     * <BR>
     * 　@　@　@　@　@return　@"06:通知代行応答"<BR>
     * <BR>
     * ４）　@else if 切替起動区分 == <BR>
     * "4:通知再送要求（受付系）再起動"　@or　@5:通知再送要求（約定系）再起動<BR>
     * <BR>
     * 　@　@　@　@　@return　@"08:通知再送応答"<BR>
     * @@param 切替起動区分 - 切替起動区分。<BR>
     * @@return String<BR>
     * @@roseuid 430068E70358
     */
    private String getSwitchPointResponseDiv(String l_switchStartDiv)
    {
        // return値
        String l_resDiv = null;

        // 切替起動区分が通番照会要求再起動の場合、通番照会応答を返す
        if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NUBER_REF_REQ_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.SERIAL_NUMBER_REFERENCE_RESPONSE;
        }
        // 切替起動区分が通知代行解除要求再起動の場合、通知代行解除応答を返す
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REL_REQ_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_RELEASE_RESPONSE;
        }
        // 切替起動区分が通知代行要求再起動の場合、通知代行応答を返す
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICEAGENCY_REQ_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.NOTICE_AGENCY_RESPONSE;
        }
        // 切替起動区分が通知再送要求（受付系）or通知再送要求（約定系）再起動の場合、通知再送応答を返す
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART) || 
                                l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            l_resDiv = WEB3ChangeReqResDivDef.NOTICE_RESEND_RESPONSE;
        }
        
        return l_resDiv;
    }

    /**
     * 切替起動区分に応じた通知種別を返却する。<BR>
     * <BR>
     * [パラメータ]<BR>
     * 切替起動区分<BR>
     * <BR>
     * <BR>
     * １）　@if　@切替起動区分 == "4:通知再送要求（受付系）再起動"の場合<BR>
     * <BR>
     * 　@　@return "01:受付系"<BR>
     * <BR>
     * ２）　@else if　@切替起動区分 == "5:通知再送要求（約定系）再起動"の場合<BR>
     * <BR>
     * 　@　@return "02:約定系"<BR>
     * <BR>
     * ３）　@else<BR>
     * <BR>
     * 　@　@return "00:デフォルト"<BR>
     * @@param 切替起動区分 - 切替起動区分。<BR>
     * @@return String<BR>
     * @@roseuid 43003DE801FC
     */
    private String getNoticeType(String l_switchStartDiv) 
    {
        // return値
        String l_notice = null;

        // 切替起動区分が通知再送要求（受付系）の場合、受付系を返す
        if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_ACC_RESTART))
        {
            l_notice = WEB3NoticeTypeDef.ACCEPT_TYPE;
        }
        // 切替起動区分が通知再送要求（約定系）の場合、約定系を返す
        else if(l_switchStartDiv.equals(WEB3AdminFrontSwitchStartDivDef.NOTICERESEND_REQ_CONT_RESTART))
        {
            l_notice = WEB3NoticeTypeDef.EXECUTED_TYPE;
        }
        else
        {
            l_notice = WEB3NoticeTypeDef.DEFAULT;
        }

        return l_notice;
    }

    /**
     * 発注先切替テーブルのDEOSレコードの有無をチェックする。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件で発注先切替テーブルを検索する。<BR>
     * <BR>
     * 　@[条件] 
     * 　@証券会社コード = パラメータ.証券会社コード and  <BR>
     * 　@銘柄タイプ = パラメータ.銘柄タイプ and <BR>
     * 　@市場コード = パラメータ.市場コード and <BR>
     * 　@発注経路区分 = "２：フロント発注正系" and  <BR>
     * 　@フロント発注システム区分 = パラメータ.フロント発注システム区分 <BR>
     * <BR>
     * ２）　@レコードの有無を返却する。<BR>
     * <BR>
     * @@param 証券会社コード - 証券会社コード。<BR>
     * @@param 市場コード。<BR>
     * @@param フロント発注システム区分。<BR>
     * @@param 銘柄タイプ。<BR>
     * @@return java.lang.boolean<BR>
     * @@roseuid 42E46BB2019E
     */
    public boolean isFrontRoute(String l_strInstitutionCode, String l_strMarketCode, String l_frontSysDiv, 
        String l_strProductType) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getFrontSwitchRoute(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_lstSwitchRoutes = new ArrayList(); 
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and submit_order_route_div = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                l_strProductType,
                l_strMarketCode,
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION,
                l_frontSysDiv
            };        
        
        try{
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstSwitchRoutes = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
            
            // 検索結果が0件の場合、falseをスローする。
            if(l_lstSwitchRoutes.size() == 0)
            {
                return false;
            }
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
     * フロント発注市場コードから、フロント発注取引所区分コードを取得する。<BR>
     * <BR>
     * １）　@フロント発注市場コード2桁の頭1桁を取得する。<BR>
     * <BR>
     * ２）　@取得した値を返却する。<BR>
     * @@param フロント発注市場コード - 画面表示で使用される市場コード。<BR>
     * @@return String<BR>
     * @@roseuid 42D6473100A9
     */
    public String getFrontOrderExchangeCode(String l_strConvertMarketCode) 
    {
        
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)";
        log.entering(STR_METHOD_NAME);

        // 返却するオブジェクト生成
        String l_frontExCode = null;
        
        // 引数の1桁目を取得
        l_frontExCode = l_strConvertMarketCode.substring(0,1);

        log.exiting(STR_METHOD_NAME);
        return l_frontExCode;
    }

    /**
     * フロント発注市場コードから、フロント発注システム区分を取得する。<BR>
     * <BR>
     * １）　@パラメータ:フロント発注市場コード2桁目を取得する。<BR>
     * <BR>
     * ２）　@取得した値を返却する。<BR>
     * @@param フロント発注市場コード - 画面表示で使用される市場コード。<BR>
     * @@return String<BR>
     * @@roseuid 42D6480F0377
     */
    public String getFrontSystemDiv(String l_strConvertMarketCode) 
    {
        final String STR_METHOD_NAME = "getFrontSystemDiv(String)";
        log.entering(STR_METHOD_NAME);
        
        // 返却するオブジェクト生成
        String l_SysDiv = null;
        
        // 引数の2桁目を取得
        l_SysDiv = l_strConvertMarketCode.substring(1);

        log.exiting(STR_METHOD_NAME);
        return l_SysDiv;
    }

    /**
     * 引数の市場コード、フロント発注システム区分コードから、画面表示用の<BR>
     * 市場コードに変換し、返却する。<BR>
     * <BR>
     * １）　@市場コードがJASDAQ　@or　@NNMの場合、市場コードを"２"（大証）に変換する。<BR>
     * <BR>
     * ２）　@市場コードとフロント発注システム区分コードを結合する。<BR>
     * <BR>
     * ３）　@結合した値を返却する。<BR>
     * @@param 市場コード - 市場コード.<BR>
     * @@param フロント発注システム区分 - フロント発注システム区分。<BR>
     * @@return String<BR>
     * @@roseuid 42E0A66C02AD
     */
    public String getFrontOrderMarketCode(String l_marketCode, String l_frontSystemCode) 
    {
        final String STR_METHOD_NAME = "getFrontOrderMarketCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        // 変換市場コード
        String l_editmarketCode = null;

        if (l_marketCode.equals(WEB3MarketCodeDef.JASDAQ) || l_marketCode.equals(WEB3MarketCodeDef.NNM))
        {
            l_editmarketCode = WEB3FrontOrderExchangeCodeDef.OSAKA_SECURITIES_EXCHANGE;
        }
        else
        {
            l_editmarketCode = l_marketCode;
        }
        
        StringBuffer l_unitMarketCode = new StringBuffer();
        
        // 市場コードとシステム区分を結合
        l_unitMarketCode.append(l_editmarketCode).append(l_frontSystemCode);
        
        return l_unitMarketCode.toString();
    }
   
    /**
     * 障害仮想サーバ切替管理テーブルで、要求系レコードが処理済のレコードを検索する。<BR>
     * <BR>
     * １）　@検索条件生成<BR>
     * <BR>
     * 　@条件：証券会社コード = ? and <BR>
     * 　@　@　@　@　@フロント発注取引所区分コード = ? and <BR>
     * 　@　@　@　@　@フロント発注システム区分 = ? and <BR>
     * 　@　@　@　@　@フロント発注取引区分コード = ? and <BR>
     * 　@　@　@　@　@切替指示応答区分 = ? and <BR>
     * 　@　@　@　@　@通知種別 = ? and <BR>
     * 　@　@　@　@　@処理区分 = ?<BR>
     * <BR>
     * ２）　@検索条件コンテナ生成 <BR>
     * <BR>
     * 　@２－１）　@パラメータ.リクエスト.証券会社コードを設定。 <BR>
     * <BR>
     * 　@２－２）　@getフロント発注取引所区分コード（）を実行し、戻り値を設定。<BR>
     * <BR>
     * 　@ 　@ 　@[引数]  <BR>
     * 　@ 　@ 　@パラメータ.リクエスト.変換市場コード  <BR>
     * <BR>
     * 　@２－３）　@getフロント発注システム区分（）を実行し、戻り値を設定。  <BR>
     * <BR>
     * 　@ 　@ 　@[引数]  <BR>
     * 　@ 　@ 　@パラメータ.リクエスト.変換市場コード  <BR>
     * <BR>
     * 　@２－４）　@1：株券売買を設定。 <BR>
     * <BR>
     * 　@２－５）　@get切替指示要求系区分（）を実行し、戻り値を設定。 <BR>
     * <BR>
     * 　@ 　@ 　@[引数] <BR>
     * 　@ 　@ 　@パラメータ.リクエスト.切替起動区分 <BR>
     * <BR>
     * 　@２－６）　@get通知種別（）を実行し、戻り値を設定。 <BR>
     * <BR>
     * 　@ 　@ 　@[引数] <BR>
     * 　@ 　@ 　@パラメータ.リクエスト.切替起動区分 <BR>
     * <BR>
     * 　@２－７）　@1：処理済を設定。 <BR>
     * <BR>
     * ３）　@doFindAllQuery()でデータを取得。<BR>
     * <BR>
     * ４）　@検索結果を返却する。<BR>
     * @@param リクエストオブジェクト - 管理者・発注経路切替完了リクエスト。<BR>
     * @@return List<BR>
     * @@roseuid 430020A20170
     */
    private List getVirtualServerChangeRecord(WEB3AdminFrontRouteChangeCompleteRequest l_priRequest) throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getVirtualServerChangeRecord(WEB3AdminFrontRouteChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        // 処理済レコードList
        List l_lstReqRcords = null;

        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and front_order_exchange_code = ? ");
        l_sbWhere.append(" and front_order_system_code = ? ");
        l_sbWhere.append(" and front_order_trade_code = ? ");
        l_sbWhere.append(" and change_req_res_div = ? ");
        l_sbWhere.append(" and notice_type = ? ");
        l_sbWhere.append(" and status = ? ");

        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_priRequest.institutionCode,
                this.getFrontOrderExchangeCode(l_priRequest.convertMarketCode),
                this.getFrontSystemDiv(l_priRequest.convertMarketCode),
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE,
                this.getSwitchPointReqDiv(l_priRequest.changeStartDiv),
                this.getNoticeType(l_priRequest.changeStartDiv),
                WEB3StatusDef.DEALT
            };        
       
        try
        {
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lstReqRcords = l_queryProcessor.doFindAllQuery(
                VirtualServerChangeRow.TYPE,
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
        return l_lstReqRcords;
    }
   
    /**
     * 障害仮想サーバ切替管理テーブルのデータを更新するパラメータを設定する。<BR>
     * <BR>
     * １）　@Mapオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@更新する項目をMapに設定する。<BR>
     * <BR>
     * 　@　@【ｘTrade】補足資料.DB更新 <BR>
     * 　@　@「発注経路切替_障害仮想サーバ切替管理テーブル.xls」の <BR>
     * 　@　@シート「障害仮想サーバ切替管理テーブル（update）」を参照。<BR>
     * <BR>
     * ３）　@Mapオブジェクトを返却する。<BR>
     * @@return Map<BR>
     * @@roseuid 42F1C03A02FD
     */
    private Map setVirtualServerChangeUpdateCalums() 
    {
        // Update項目Map
        Map status = new HashMap();
        
        status.put( "status" , WEB3StatusDef.NOT_DEAL);
        status.put( "last_updated_timestamp" , GtlUtils.getSystemTimestamp());
        
        
        return status;
    }

    /**
     *先物OP注文取引キューテーブルから、"市場受付確認前"、"市場受付確認中"、<BR>
     *"市場受付確認済"の注文件数を取得し、処理件数オブジェクトに格納する。 <BR>
     *<BR>
     *１）　@先物OP発注サービス.getフロント発注取引所区分コード（）から、
     *　@　@　@フロント発注取引所区分コードを取得する。 <BR>
     *<BR>
     *　@[引数] <BR>
     *　@市場コード： パラメータ.市場コード <BR>
     *<BR>
     *２）　@"市場受付確認前"、"市場受付確認中"、
     *　@　@　@"市場受付確認済"のデータをLoop処理を行い、取得する。 <BR>
     *<BR>
     *for(要求データサイズ) <BR>
     *<BR>
     *　@２－１）　@条件文字列を生成する。 <BR>
     *<BR>
     *　@　@証券会社コード = ？ and  <BR>
     *　@　@フロント発注取引所区分コード = ？ and  <BR>
     *　@　@フロント発注システム区分 = ？ and  <BR>
     *　@　@フロント発注取引区分コード = ？ and  <BR>
     *　@　@発注経路区分 = ？ and  <BR>
     *　@　@if( "市場受付確認前" ) <BR>
     *<BR>
     *　@　@　@ 処理区分 in (? , ?) <BR>
     *<BR>
     *　@　@else("市場受付確認中" or "市場受付確認済") <BR>
     *<BR>
     *　@　@　@ 処理区分 = ? <BR>
     *<BR>
     *　@２－２）　@検索条件データコンテナを作成する。 <BR>
     *<BR>
     *　@　@２－２－１）　@ArrayListを生成する。 <BR>
     *<BR>
     *　@　@２－２－２）　@パラメータ.証券会社コードをArrayListに追加する。 <BR>
     *<BR>
     *　@　@２－２－３）　@１）で取得したフロント発注取引所区分コードをArrayListに追加する。 <BR>
     *<BR>
     *　@　@２－２－４）　@パラメータ.フロント発注システム区分をArrayListに追加する。 <BR>
     *<BR>
     *　@　@２－２－５）　@"１：株券売買"をArrayList追加する。 <BR>
     *<BR>
     *　@　@２－２－６）　@if( "市場受付確認前" , "市場受付確認中" ) <BR>
     *　@　@　@　@　@　@　@　@　@　@"２：フロント発注正系"をArrayListに追加する。 <BR>
     *　@　@　@　@　@　@　@　@　@　@else（ "市場受付確認済" ）　@ <BR>
     *　@　@　@　@　@　@　@　@　@　@"３：フロント発注副系"をArrayListに追加する。 <BR>
     *<BR>
     *　@　@２－２－７）　@if( "市場受付確認前" ) <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@"１：送信済"をArrayListに追加する。 <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@"２：AMG入力完了"をArrayListに追加する。 <BR>
     *　@　@　@　@　@　@　@　@　@　@　@else if（ "市場受付確認中" ） <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@"９：市場受付確認中"をArrayListに追加する。 <BR>
     *　@　@　@　@　@　@　@　@　@　@　@else if（"市場受付確認済" ）　@ <BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@"８：市場受付確認済"をArrayListに追加する。 <BR>
     *<BR>
     *　@２－３）　@DB検索 <BR>
     *<BR>
     *　@２－４）　@処理件数情報オブジェクトに件数を格納する。 <BR>
     *<BR>
     *　@　@２－４－１）　@if（"市場受付確認前"） <BR>
     *<BR>
     *　@　@　@　@　@　@　@　@　@　@処理件数情報オブジェクト.確認前件数 = データ取得サイズ <BR>
     *<BR>
     *　@　@２－４－２）　@if（"市場受付確認中"） <BR>
     *<BR>
     *　@　@　@　@　@　@　@　@　@　@処理件数情報オブジェクト.確認中件数 = データ取得サイズ <BR>
     *<BR>
     *　@　@２－４－３）　@if（"市場受付確認済"） <BR>
     *<BR>
     *　@　@　@　@　@　@　@　@　@　@処理件数情報オブジェクト.確認済件数 = データ取得サイズ　@<BR>
     *<BR>
     * @@param l_institutionCode - 証券会社コード<BR>
     * @@param l_marketCode - 市場コード<BR>
     * @@param l_frontSystemCode - フロント発注システム区分<BR>
     * @@param l_processInfoUnit - 処理件数情報<BR>
    */
    public void getIfoGrayOrder(String l_institutionCode, 
        String l_marketCode, 
        String l_frontSystemCode, 
        WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getIfoGrayOrder(String, String, String, WEB3AdminFrontProcessNumberInfoUnit)";
        log.entering(STR_METHOD_NAME);
        
        // フロント発注取引所区分コード
        String l_frontExchangeCode = null;

        // 市場コードを基に、フロント発注取引所区分コードを取得
        WEB3IfoFrontOrderService l_ifoFrontOrderService =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        
        l_frontExchangeCode = l_ifoFrontOrderService.getFrontOrderExchangeCode(l_marketCode);
        
        // キューテーブル受付処理区分をListに格納
        List l_lstStatusDivs = new ArrayList();
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN);
        l_lstStatusDivs.add(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE);
        
        // Listサイズ分、loop処理を行う。
        for(int lstSize = 0; l_lstStatusDivs.size() > lstSize ; lstSize++)
        {
            // ArrayListオブジェクトの生成
            List l_lstHostRcords = new ArrayList(); 
        
            // 検索条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and front_order_exchange_code = ? ");
            l_sbWhere.append(" and front_order_system_code = ? ");
            l_sbWhere.append(" and front_order_trade_code = ? ");
            l_sbWhere.append(" and submit_order_route_div = ? ");            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_sbWhere.append(" and status in (?,?) ");                
            }
            else
            {
                l_sbWhere.append(" and status = ? ");            
            }
            
            // 検索条件コンテナの生成
            List l_lstWheres = new ArrayList();
            
            l_lstWheres.add(l_institutionCode);
            l_lstWheres.add(l_frontExchangeCode);
            l_lstWheres.add(l_frontSystemCode);
            l_lstWheres.add(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)) || 
                    WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION);
            }
            else{
                l_lstWheres.add(WEB3SubmitOrderRouteDivDef.FRONT_ORDER_SUB_FACTION);            
            }
            
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize))){
                // 送信済
                l_lstWheres.add(WEB3FrontOrderStatusDef.SENDED);
                // AMG入力完了
                l_lstWheres.add(WEB3FrontOrderStatusDef.AMG_INPUT_COMPLETE);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize))){
                // 市場受付確認中
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMING);
            }
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize))){
                // 市場受付確認済
                l_lstWheres.add(WEB3FrontOrderStatusDef.MARKET_ACCEPT_CONFIRMED);
            }

            // 配列に変換            
            Object[] l_objWhere = l_lstWheres.toArray();
 
            try
            {
                // DB検索
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                l_lstHostRcords = l_queryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
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
            
            // 市場確認前レコードの場合、処理件数情報オブジェクト.確認前件数にレコード件数を格納
            if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_PREV.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.beforeNumber = Integer.toString(l_lstHostRcords.size());
            }
            // 市場確認中レコードの場合、処理件数情報オブジェクト.確認中件数にレコード件数を格納
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_IN.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.inNumber = Integer.toString(l_lstHostRcords.size());
            }
            // 市場確認済レコードの場合、処理件数情報オブジェクト.確認済件数にレコード件数を格納
            else if(WEB3AdminFrontHostStatusDivDef.MARKET_CONFIRM_FUTURE.equals(l_lstStatusDivs.get(lstSize)))
            {
                l_processInfoUnit.afterNumber = Integer.toString(l_lstHostRcords.size());
            }                                        
        }
    }
}
@
