head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (フロント発注共通サービスImpl) (WEB3AdminFrontOrderCommonService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.118
Revision History : 2007/02/27  王明明 (中訊) 仕様変更モデルNo.124
Revision History : 2007/03/19  孟亜南 (中訊) 仕様変更モデルNo.126 No.127
Revision History : 2007/03/19  孟亜南 (中訊) 実装の問題No.004
Revision History : 2008/12/17  張騰宇 (中訊) 株式のＤＢレイアウト164
Revision History : 2009/02/20  張騰宇 (中訊) 株式のＤＢレイアウト168
Revision History : 2009/05/21  張騰宇 (中訊) 仕様変更モデルNo.244
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontMarketNoticeHistoryUnit;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.equity.data.MarketNoticeManagementParams;
import webbroker3.equity.data.MarketNoticeManagementRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者フロント発注共通サービスImpl)<BR>
 * <BR>
 * 管理者フロント発注共通サービスImplクラス<BR>
 * <BR>
 * WEB3AdminFrontOrderCommonServiceImpl<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontOrderCommonServiceImpl implements WEB3AdminFrontOrderCommonService {
    
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderCommonServiceImpl.class);

    /**
     * @@roseuid 43016EAF01F7
     */
    public WEB3AdminFrontOrderCommonServiceImpl() 
    {
    
    }
   
    /**
     * 市場通知管理テーブルの検索に適した市場コードを取得し、<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@DB検索<BR>
     * 　@以下の条件で発注先切替テーブルの市場コード、フロント発注システム区分を検索する<BR>
     * 。<BR>
     * 　@※市場コードで、ソートする。<BR>
     * <BR>
     * 　@証券会社コード = パラメータ.証券会社コード And<BR>
     * 　@銘柄タイプ in (1：株式, 6：先物オプション) And<BR>
     * 　@発注経路区分 = ２：フロント発注正系 <BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、例外をスローする。<BR>
     * <BR>
     * ３）　@取得したListのサイズ分、Loop処理を行う。<BR>
     * <BR>
     * ３－１）　@Listオブジェクトから、市場コードとフロント発注システム区分コードを取得<BR>
     * する。<BR>
     * <BR>
     * ３－２）　@フロント発注市場コード変換（市場コード:String , <BR>
     * フロント発注システム区分コード:String）を呼び出し、<BR>
     * 　@　@　@　@　@フロント発注変換市場コードを取得する。<BR>
     * <BR>
     * ３－３）　@取得したフロント発注変換市場コードが、<BR>
     * 既にArrayListオブジェクトにadd()されている（contains() == true）場合、continueする。 <BR>
     * <BR>
     * ３－４）　@取得したフロント発注変換市場コードをArrayListオブジェクトにadd()する。<BR>
     * <BR>
     * ４）　@ArrayListオブジェクトを配列（:String）に変換する。<BR>
     * <BR>
     * ５）　@変換した配列を返却する。<BR>
     * @@param 証券会社コード - 証券会社コード<BR>
     * @@return 市場コードString[]<BR>
     * @@roseuid 42D5E65E01C2
     */
    public String[] getFindPossibleMarketCode(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getFindPossibleMarketCode(String)";
        log.entering(STR_METHOD_NAME);

        // ArrayListオブジェクトの生成
        List l_lstConvertCodes = new ArrayList();
        // 発注先切替テーブル検索結果List
        List l_switchingResult = new ArrayList();
        // return用String型配列
        String[] l_dispMarketLists = null;
        
        // 検索条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and product_type in ( ?,?)");
        l_sbWhere.append(" and submit_order_route_div = ? ");
        
        // 検索条件コンテナの生成
        Object[] l_objWhere =
            {
                l_strInstitutionCode,
                Integer.toString(ProductTypeEnum.EQUITY.intValue()),
                Integer.toString(ProductTypeEnum.IFO.intValue()),
                WEB3SubmitOrderRouteDivDef.FRONT_ORDER_MAIN_FACTION
            };        
        
        try{
            // DB検索
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_switchingResult = l_queryProcessor.doFindAllQuery(
                OrderSwitchingRow.TYPE,
                l_sbWhere.toString(),
                "market_code asc",
                null,
                l_objWhere);
            
            // 検索結果が0件の場合、エラーメッセージをスローする。
            if(l_switchingResult.size() == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02046, // 市場コード未設定エラー 
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

        Iterator l_ObjSwitchResult = l_switchingResult.iterator();
        
        while(l_ObjSwitchResult.hasNext())
        {   
            // 発注先切替テーブルRowオブジェクトの取得      
            OrderSwitchingRow switchRow = (OrderSwitchingParams)l_ObjSwitchResult.next();
            
            // 市場コードを取得
            String l_marketCode = switchRow.getMarketCode();
            // フロント発注システム区分を取得
            String l_frontSystemDiv = switchRow.getFrontOrderSystemCode();
            
            // 画面表示用の変換市場コードを取得
            String l_convertMarketCode = this.getFrontOrderMarketCode(l_marketCode, l_frontSystemDiv);
            
            // 既にArrayListオブジェクトにadd()されている（contains() == true）場合、continueする。
            if (l_lstConvertCodes.contains(l_convertMarketCode))
            {
                continue;
            }
            
            // Listオブジェクトに追加
            l_lstConvertCodes.add(l_convertMarketCode);
        }
        
        l_dispMarketLists = new String[l_lstConvertCodes.size()];
        // Listから配列に変換
        l_lstConvertCodes.toArray(l_dispMarketLists);

        log.exiting(STR_METHOD_NAME);
        return l_dispMarketLists;
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
     * 通知履歴参照一覧を作成する。<BR>
     * <BR>
     * <BR>
     * １）　@市場通知履歴明細メッセージオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@ArrayListオブジェクトを生成する。<BR>
     * <BR>
     * ３）　@パラメータ,Listオブジェクトのサイズ分、Loop処理を行う。<BR>
     * <BR>
     * ３－１）　@注文受付番号を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.acceptNumber = Listオブジェクト.getAcceptNumber();<BR>
     * <BR>
     * ３－２）　@仮想サーバNoを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.virtualServerNumber = <BR>
     * Listオブジェクト.getVirtualServerNumberMarket();<BR>
     * <BR>
     * ３－３）　@データ種別コードを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.dataClassCode = Listオブジェクト.getDataClassCode();<BR>
     * <BR>
     * ３－４）　@社内処理項目を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.corpCode = Listオブジェクト.getCorpCode();<BR>
     * <BR>
     * ３－５）　@通知番号を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.noticeNumber = Listオブジェクト.getNoticeNumber();<BR>
     * <BR>
     * ３－６）　@処理時刻（作成日付）を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.createdTimestamp = <BR>
     * Listオブジェクト.getCreatedTimestamp();<BR>
     * <BR>
     * ３－７）　@部店を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.branchCode = Listオブジェクト.getbranchCode();<BR>
     * <BR>
     * ３－８）　@口座コードを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.accountCode = Listオブジェクト.getAccountCode();<BR>
     * <BR>
     * ３－９）　@銘柄コードを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.productCode = Listオブジェクト.getProductCode();<BR>
     * <BR>
     * ３－１０）　@売買区分を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.dealingType = Listオブジェクト.getBuySellDiv();<BR>
     * <BR>
     * ３－１１）　@エラーコードを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.errorCode = Listオブジェクト.getErrorCode();<BR>
     * <BR>
     * ３－１２）　@データ種別詳細コードを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.dataClassDetailCode = <BR>
     * Listオブジェクト.getDataClassDetailCode();<BR>
     * <BR>
     * ３－１３）　@再送ダブリフラグを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.resendFlg = Listオブジェクト.getResendFlg();<BR>
     * <BR>
     * ３－１４）　@執行条件を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.execCondType = <BR>
     * Listオブジェクト.getExecutionCondition();<BR>
     * <BR>
     * ３－１５）　@値段条件を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.priceCondType = <BR>
     * Listオブジェクト.getPriceConditionType();<BR>
     * <BR>
     * ３－１６）　@注文数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.orderQuantity = Listオブジェクト.getOrderQuantity();<BR>
     * <BR>
     * ３－１７）　@注文値段を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.limitPrice = Listオブジェクト.getLimitPrice();<BR>
     * <BR>
     * ３－１８）　@信用取引区分を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.marginCode = Listオブジェクト.getMarginCode();<BR>
     * <BR>
     * ３－１９）　@自己委託区分を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.tradeauditCode = <BR>
     * Listオブジェクト.getTradeauditCode();<BR>
     * <BR>
     * ３－２０）　@空売りフラグを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.shortSellOrderFlag = <BR>
     * Listオブジェクト.getShortSellOrderFlag();<BR>
     * <BR>
     * ３－２１）　@（被訂正）注文値段を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.orgLimitPrice = Listオブジェクト.getOrgLimitPrice();<BR>
     * <BR>
     * ３－２２）　@（被訂正）社内処理項目を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.orgCorpCode = Listオブジェクト.getOrgCorpCode();<BR>
     * <BR>
     * ３－２３）　@削減数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.cutQuantity = Listオブジェクト.getCutQuantity();<BR>
     * <BR>
     * ３－２４）　@約定値段を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.execPrice = Listオブジェクト.getExecPrice();<BR>
     * <BR>
     * ３－２５）　@約定数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.execQuantity = Listオブジェクト.getExecQuantity();<BR>
     * <BR>
     * ３－２６）　@注文残数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.leftQuantity = Listオブジェクト.getLeftQuantity();<BR>
     * <BR>
     * ３－２７）　@値段符号を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.priceMark = Listオブジェクト.getPriceMark();<BR>
     * <BR>
     * ３－２８）　@出来符号を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.execMark = Listオブジェクト.getExecMark();<BR>
     * <BR>
     * ３－２９）　@約定通知番号を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.execNumber = Listオブジェクト.getExecNumber();<BR>
     * <BR>
     * ３－３０）　@訂正結果コードを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.modifiedResult = <BR>
     * Listオブジェクト.getModifiedResult();<BR>
     * <BR>
     * ３－３１）　@失効理由コードを格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.reasonCode = Listオブジェクト.getReasonCode();<BR>
     * <BR>
     * ３－３２）　@ストップ符号を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.stopMark = Listオブジェクト.getStopMark();<BR>
     * <BR>
     * ３－３３）　@（被訂正）執行条件を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.orgExecCondType = <BR>
     * Listオブジェクト.getOrgExecutionCondition();<BR>
     * <BR>
     * ３－３４）　@（被訂正）値段条件を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.orgPriceCondType = <BR>
     * Listオブジェクト.getOrgPriceConditionType();<BR>
     * <BR>
     * ３－３５）　@（被訂正）注文数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.orgOrderQuantity = <BR>
     * Listオブジェクト.getOrgOrderQuantity();<BR>
     * <BR>
     * ３－３６）　@取消削減済数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.canceledQuantity = <BR>
     * Listオブジェクト.getCanceledQuantity();<BR>
     * <BR>
     * ３－３７）　@内出来数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.executedQuantity = <BR>
     * Listオブジェクト.getExecutedQuantity();<BR>
     * <BR>
     * ３－３８）　@失効注文数量を格納<BR>
     * <BR>
     * 　@　@メッセージオブジェクト.expirationQuantity = <BR>
     * Listオブジェクト.getExpirationQuantity();<BR>
     * <BR>
     * ３－３９）　@メッセージオブジェクトをArrayListオブジェクトにadd（）する。<BR>
     * <BR>
     * ４）　@市場通知履歴明細型の配列オブジェクトをArrayListオブジェクトのサイズで生成する。<BR>
     * <BR>
     * ５）　@toArray()で、リスト内の要素を格納する配列オブジェクトに変換する。<BR>
     * <BR>
     * 　@　@ArrayListオブジェクト.toArray(市場通知履歴明細型の配列オブジェクト);<BR>
     * <BR>
     * ６）　@変換した配列オブジェクトを返却する。<BR>
     * @@param 市場通知管理一覧 - get通知履歴一覧（）で取得したListオブジェクト。<BR>
     * @@return <BR>
     * 市場通知履歴明細)[]<BR>
     * @@roseuid 42DCA871028B
     */
    public WEB3AdminFrontMarketNoticeHistoryUnit[] createNoticeHistryRefList(List l_histList) 
    {
        final String STR_METHOD_NAME = "createNoticeHistryRefList(List)";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        List l_noticeManagements = new ArrayList();
        // 返却するWEB3AdminFrontMarketNoticeHistoryUnit[]型配列
        WEB3AdminFrontMarketNoticeHistoryUnit[] l_retHistryLists = null;
        
        Iterator l_objHistryLists = l_histList.iterator();
        
        while(l_objHistryLists.hasNext())
        {
            // MarketNoticeManagementRowオブジェクトの取得
            MarketNoticeManagementRow l_noticeRow = (MarketNoticeManagementParams)l_objHistryLists.next();
            
            // 市場通知履歴明細メッセージオブジェクトを生成
            WEB3AdminFrontMarketNoticeHistoryUnit l_objNoticeHistry = new WEB3AdminFrontMarketNoticeHistoryUnit();
            
            // 注文受付番号を格納
            l_objNoticeHistry.acceptNumber = l_noticeRow.getAcceptNumber();

            // 仮想サーバNoを格納
            l_objNoticeHistry.virtualServerNumber = l_noticeRow.getVirtualServerNumberMarket();

            // データ種別コードを格納
            l_objNoticeHistry.dataClassCode = l_noticeRow.getDataClassCode();

            // 社内処理項目を格納
            l_objNoticeHistry.corpCode = l_noticeRow.getCorpCode();

            // 通知番号を格納
            l_objNoticeHistry.noticeNumber = Long.toString(l_noticeRow.getNoticeNumber());

            // 処理時刻（作成日付）を格納
            l_objNoticeHistry.createdTimestamp = l_noticeRow.getCreatedTimestamp();

            // 部店を格納
            l_objNoticeHistry.branchCode = l_noticeRow.getBranchCode();

            // 口座コードを格納
            l_objNoticeHistry.accountCode = l_noticeRow.getAccountCode();

            // 銘柄コードを格納
            l_objNoticeHistry.productCode = l_noticeRow.getProductCode();

            // 売買区分を格納
            l_objNoticeHistry.dealingType = l_noticeRow.getBuySellDiv();

            // エラーコードを格納
            l_objNoticeHistry.errorCode = l_noticeRow.getErrorCode();

            // データ種別詳細コードを格納
            l_objNoticeHistry.dataClassDetailCode = l_noticeRow.getDataClassDetailCode();

            // 再送ダブリフラグを格納
            l_objNoticeHistry.resendFlg = l_noticeRow.getResendFlg();

            // 執行条件を格納
            l_objNoticeHistry.execCondType = l_noticeRow.getExecutionCondition();

            //値段条件を格納
            l_objNoticeHistry.priceCondType = l_noticeRow.getPriceConditionType();

            // 注文数量を格納
            l_objNoticeHistry.orderQuantity = Double.toString(l_noticeRow.getOrderQuantity());

            // 注文値段を格納
            l_objNoticeHistry.limitPrice = Double.toString(l_noticeRow.getLimitPrice());

            // 信用取引区分を格納
            l_objNoticeHistry.marginCode = l_noticeRow.getMarginCode();

            // 自己委託区分を格納
            l_objNoticeHistry.tradeauditCode = l_noticeRow.getTradeauditCode();

            // 空売りフラグを格納
            l_objNoticeHistry.shortSellOrderFlag = l_noticeRow.getShortSellOrderFlag();

            //（被訂正）注文値段を格納
            l_objNoticeHistry.orgLimitPrice = Double.toString(l_noticeRow.getOrgLimitPrice());

            //（被訂正）社内処理項目を格納
            l_objNoticeHistry.orgCorpCode = l_noticeRow.getOrgCorpCode();

            // 削減数量を格納
            l_objNoticeHistry.cutQuantity = Long.toString(l_noticeRow.getCutQuantity());

            // 約定値段を格納
            l_objNoticeHistry.execPrice = Double.toString(l_noticeRow.getExecPrice());

            // 約定数量を格納
            l_objNoticeHistry.execQuantity = Double.toString(l_noticeRow.getExecQuantity());

            // 注文残数量を格納
            l_objNoticeHistry.leftQuantity = Double.toString(l_noticeRow.getLeftQuantity());

            // 値段符号を格納
            l_objNoticeHistry.priceMark = l_noticeRow.getPriceMark();

            // 出来符号を格納
            l_objNoticeHistry.execMark = l_noticeRow.getExecMark();

            // 約定通知番号を格納
            l_objNoticeHistry.execNumber = Integer.toString(l_noticeRow.getExecNumber());

            // 訂正結果コードを格納
            l_objNoticeHistry.modifiedResult = l_noticeRow.getModifiedResult();

            // 失効理由コードを格納
            l_objNoticeHistry.reasonCode = l_noticeRow.getReasonCode();

            // ストップ符号を格納
            l_objNoticeHistry.stopMark = l_noticeRow.getStopMark();

            //（被訂正）執行条件を格納
            l_objNoticeHistry.orgExecCondType = l_noticeRow.getOrgExecutionCondition();

            //（被訂正）値段条件を格納
            l_objNoticeHistry.orgPriceCondType = l_noticeRow.getOrgPriceConditionType();

            // (被訂正）注文数量を格納
            l_objNoticeHistry.orgOrderQuantity = Double.toString(l_noticeRow.getOrgOrderQuantity());

            // 取消削減済数量を格納
            l_objNoticeHistry.canceledQuantity = Long.toString(l_noticeRow.getCanceledQuantity());

            // 内出来数量を格納
            l_objNoticeHistry.executedQuantity = Long.toString(l_noticeRow.getExecutedQuantity());

            // 失効注文数量を格納
            l_objNoticeHistry.expirationQuantity = Double.toString(l_noticeRow.getExpirationQuantity());
            
            // メッセージオブジェクトをArrayListオブジェクトに追加
            l_noticeManagements.add(l_objNoticeHistry);
        }
        
        l_retHistryLists = new WEB3AdminFrontMarketNoticeHistoryUnit[l_noticeManagements.size()];
        
        // Listから配列に変換
        l_noticeManagements.toArray(l_retHistryLists);
      
        log.exiting(STR_METHOD_NAME);
        return l_retHistryLists;
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
     * 当日を含めて４営業日前までの営業日一覧を取得し、返却する。<BR>
     * <BR>
     * １）　@ArrayListオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@各営業日を取得する。<BR>
     * <BR>
     * ２－１）　@WEB3GentradeBizDateインスタンスを生成する。<BR>
     * 　@　@　@　@　@引数：基準日には「翌日」をセットする。<BR>
     * <BR>
     * ２－２）　@WEB3GentradeBizDate.roll(-1)で当日を含めて１営業日前の日付を取得し、<BR>
     * ArrayListオブジェクトにadd()する。<BR>
     * <BR>
     * ２－３）　@WEB3GentradeBizDate.roll(-2)で当日を含めて２営業日前の日付を取得し、<BR>
     * ArrayListオブジェクトにadd()する。<BR>
     * <BR>
     * ２－４）　@WEB3GentradeBizDate.roll(-3)で当日を含めて３営業日前の日付を取得し、<BR>
     * ArrayListオブジェクトにadd()する。<BR>
     * <BR>
     * ２－５）　@WEB3GentradeBizDate.roll(-4)で当日を含めて４営業日前の日付を取得し、<BR>
     * ArrayListオブジェクトにadd()する。<BR>
     * <BR>
     * ３）　@ArrayListオブジェクトを配列（:Date）に変換する。<BR>
     * @@return Date[]<BR>
     * @@roseuid 4303EFAC0105
     */
    public Date[] getNoticeReceivedDateRef() throws WEB3SystemLayerException 
    {
        final String STR_METHOD_NAME = "getNoticeReceivedDateRef()";
        log.entering(STR_METHOD_NAME);
        
        // ArrayListオブジェクトの生成
        ArrayList l_businessDays = new ArrayList();
        // WEB3GentradeBizDateオブジェクト
        WEB3GentradeBizDate l_gentradeBizDate = null;
        // 返却するDate型配列オブジェクト
        Date[] l_bizLists = null;

        // 翌日日付の取得
        Date tommorow = WEB3DateUtility.addDay(Calendar.getInstance().getTime(), 1);
        
        // WEB3GentradeBizDateインスタンスを生成
        l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(tommorow.getTime()));
        
        // １営業日前の日付をListに追加
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-1)));
        // ２営業日前の日付をListに追加
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-2)));
        // ３営業日前の日付をListに追加
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-3)));
        // ４営業日前の日付をListに追加
        l_businessDays.add(WEB3DateUtility.toDay(l_gentradeBizDate.roll(-4)));

        l_bizLists = new Date[l_businessDays.size()];

        // Listから配列に変換
        l_businessDays.toArray(l_bizLists);

        log.exiting(STR_METHOD_NAME);    
        return l_bizLists;
    }
}
@
