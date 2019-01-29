head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者データマネージャ(WEB3AdminToDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 譚漢江(中訊) 新規作成
                 : 2006/08/23　@肖志偉(中訊) 仕様変更モデルNo.068,070,071,081
                 : 2006/10/18  唐性峰(中訊) 仕様変更モデルNo.091,094
                 : 2006/11/28　@周捷(中訊) 仕様変更モデルNo.117
                 : 2006/12/19　@徐大方(中訊) 仕様変更モデルNo.122
                 : 2006/12/20　@徐大方(中訊) 仕様変更モデルNo.125
*/

package webbroker3.admintriggerorder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.admintriggerorder.define.WEB3AdminToDifferentTimeDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTradeStopKeyItemDef;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefRefCommonRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderStopStateUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DetectTypeDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TargetTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.define.WEB3TriggerOrderStatusDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyRow;
import webbroker3.rlsgateway.data.RlsOrderMissParams;
import webbroker3.rlsgateway.data.RlsOrderMissRow;
import webbroker3.rlsgateway.define.WEB3RlsRequestTypeDef;
import webbroker3.triggerorder.base.data.TriggerOrderStopParams;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者データマネージャ)<BR>
 * トリガー注文管理者のDB I/O、データ変換などを管理するクラス。<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3AdminToDataManager 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToDataManager.class);
    
    /**
     * @@roseuid 43F1BDF601F4
     */
    public WEB3AdminToDataManager() 
    {
     
    }
    
    /**
     * (is商品実施)<BR>
     * 部店コード一覧に該当する部店について<BR>
     * 指定した商品を実施しているかを判定する。<BR>
     * 実施している場合trueを、実施していない場合、falseを返却する。<BR>
     * <BR>
     * ※引数の部店コード一覧のうち、<BR>
     * 　@一部店でも実施していれば、実施となる。<BR>
     * <BR>
     * １）パラメータ.部店コード一覧の要素数分のLoop処理。<BR>
     * <BR>
     * 　@１－１）　@拡張アカウントマネージャ.get部店()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[get部店()にセットするパラメータ]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@部店コード：　@処理対象の部店コード<BR>
     * <BR>
     * 　@１－２）　@パラメータ.商品区分＝"オプション"の場合、<BR>
     * 　@　@処理対象の部店.オプション実施区分＝"実施"の場合、trueを返却する。<BR>
     * <BR>
     * 　@１－３）　@パラメータ.商品区分＝"先物"の場合、<BR>
     * 　@　@処理対象の部店.先物実施区分＝"実施"の場合、trueを返却する。<BR>
     * <BR>
     * ２）全部店が商品未実施の場合（１のLoop処理でbreakしなかった場合）、<BR>
     * 　@　@falseを返却する。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列<BR>
     * @@param l_strProductType - (商品区分)<BR>
     * 商品区分<BR> 
     * <BR>
     * 1：　@現物株式<BR> 
     * 2：　@信用取引<BR> 
     * 3：　@先物<BR> 
     * 4：　@オプション<BR> 
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43E7228C03DF
     */
    public static boolean isProductExec(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strProductType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isProductExec(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）パラメータ.部店コード一覧の要素数分のLoop処理。
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            //１－１）　@拡張アカウントマネージャ.get部店()メソッドをコールする。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();  
            WEB3GentradeBranch l_branch = null;
            
            try
            {
                l_branch = 
                    (WEB3GentradeBranch) l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCodes[i]);
            }
            catch (NotFoundException l_nfe)
            {
                continue;
            }    

            BranchRow l_branchRow = (BranchRow) l_branch.getDataSourceObject();
      
            //１－２）　@パラメータ.商品区分＝"オプション"の場合、
            //処理対象の部店.オプション実施区分＝"実施"の場合、trueを返却する。
            if (WEB3CommodityDivDef.OPTION.equals(l_strProductType))
            {
                if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOptionDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            
            //１－３）　@パラメータ.商品区分＝"先物"の場合、
            //処理対象の部店.先物実施区分＝"実施"の場合、trueを返却する。
            if (WEB3CommodityDivDef.FUTURE.equals(l_strProductType))
            {
                if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getFutureDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }

        //２）全部店が商品未実施の場合（１のLoop処理でbreakしなかった場合）、
        //falseを返却する。 <BR>
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (get顧客一覧)<BR>
     * 引数の条件に該当する顧客の一覧を返却する。<BR>
     * <BR>
     * １） ArrayListを生成する。<BR>
     * <BR>
     * ２） DB検索<BR>
     * 　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。<BR>
     * 　@２－１） 拡張アカウントマネージャ.get顧客()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[get顧客()にセットするパラメータ]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@部店コード：　@処理対象の部店コード<BR>
     * 　@　@　@口座コード：　@パラメータ.顧客コード<BR>
     * <BR>
     * 　@２－２） 　@２－１）の結果が取得できた場合は、<BR>
     * 　@　@　@生成したArrayListに追加する。<BR>
     * <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。<BR>
     * 　@※toArray()の戻り値.length＝0の場合、<BR>
     * 　@　@「該当データなし」の例外をスローする。<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01037<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     * @@roseuid 43E83318024E
     */
    public static WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode,
        String[] l_strBranchCodes,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１） ArrayListを生成する。
        List l_lisAccounts = new ArrayList();
        
        //２） DB検索
        //パラメータ.部店コード[]の要素数分以下の処理を繰り返す。
        for (int i = 0; i < l_strBranchCodes.length; i++)
        {
            //２－１） 拡張アカウントマネージャ.get顧客()メソッドをコールする。
            //      [get顧客()にセットするパラメータ]
            //      証券会社コード：　@パラメータ.証券会社コード
            //      部店コード：　@処理対象の部店コード
            //      口座コード：　@パラメータ.顧客コード
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountMananger = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountMananger.getMainAccount(l_strInstitutionCode, l_strBranchCodes[i], l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                continue;
            }
            
            //２－２） 　@２－１）の結果が取得できた場合は、生成したArrayListに追加する。
            if (null != l_mainAccount)
            {
                l_lisAccounts.add(l_mainAccount);
            }
        }
        
        //３）　@ArrayList.toArray()の戻り値を返却する。
        WEB3GentradeMainAccount[] l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
        l_lisAccounts.toArray(l_mainAccounts);
        
        //※toArray()の戻り値.length＝0の場合、「該当データなし」の例外をスローする。
        if (0 == l_mainAccounts.length)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }
    
    /**
     * (create共通検索条件文字列)<BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）　@検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）　@部店条件を検索条件文字列に追加する。<BR>
     * 　@パラメータ.リクエストデータ.部店コードの要素数分"?"を追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "branch_id in (?, ?,,,) "<BR>
     * <BR>
     * ３）　@パラメータ.リクエストデータ.注文ID≠nullの場合、<BR>
     * 　@注文IDを検索条件文字列に追加する。 <BR>
     * <BR>
     * 　@検索条件文字列 += "and order_id = ? "<BR>
     * <BR>
     * ４）　@発注日条件 <BR>
     * 　@４－１）　@パラメータ.リクエストデータ.発注日≠nullの場合、<BR>
     * 　@　@以下の発注日条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and biz_date = ? "<BR>
     * <BR>
     * 　@４－２）　@パラメータ.リクエストデータ.発注日＝nullの場合、<BR>
     * 　@　@以下の発注日条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and biz_date >= ? "<BR>
     * <BR>
     * ５）　@パラメータ.リクエストデータ.顧客コード≠nullの場合、<BR>
     * 　@this.get顧客一覧()の戻り値の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and account_id in (?, ?,,,) "<BR>
     * <BR>
     * 　@[get顧客一覧()にセットするパラメータ]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@部店コード：　@パラメータ.リクエストデータ.部店コード<BR>
     * 　@　@顧客コード：　@パラメータ.リクエストデータ.顧客コード<BR>
     * <BR>
     * ６）パラメータ.市場コード != nullの場合、<BR>
     * 　@市場IDを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and market_id = ? "<BR>
     * <BR>
     * ７）　@パラメータ.リクエストデータ.発注状況区分≠nullの場合、<BR>
     * 　@this.create共通検索条件文字列（発注状況区分）()の戻り値を<BR>
     * 　@検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@リクエストデータ：　@パラメータ.リクエストデータ<BR>
     * <BR>
     * ８）　@条件注文種別を検索条件文字列に追加する。<BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from oms_con_order_request "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where request_type = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and order_type = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and product_type = ? )"<BR>
     * <BR>
     * ９）　@パラメータ.リクエストデータ.時価情報受信時間From≠nullの場合、<BR>
     * 　@時価情報受信時間From条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where hit_tick_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@>= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * １０）　@パラメータ.リクエストデータ.時価情報受信時間To≠nullの場合、<BR>
     * 　@時価情報受信時間To条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where hit_tick_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@<= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * １１）　@パラメータ.リクエストデータ.トリガー起動時間From≠nullの場合、<BR>
     * 　@トリガー起動時間From条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where rls_hit_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@>= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * １２）　@パラメータ.リクエストデータ.トリガー起動時間To≠nullの場合、<BR>
     * 　@トリガー起動時間To条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where rls_hit_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@<= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * １３）　@パラメータ.リクエストデータ.発注完了時間From≠nullの場合、<BR>
     * 　@発注完了時間From条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where order_submit_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@>= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * １４）　@パラメータ.リクエストデータ.発注完了時間To≠nullの場合、<BR>
     * 　@発注完了時間To条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where order_submit_timestamp  <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@<= to_date(?,'yyyyMMddHH24:MI:SS')) "<BR>
     * <BR>
     * １５）　@パラメータ.リクエストデータ.乖離時間≠nullの場合、<BR>
     * パラメータ.リクエストデータ乖離時間の要素数分下記処理を行う。<BR>
     * <BR>
     * 　@[乖離時間From＝"時価受信時間"、かつ、<BR>
     * 　@乖離時間To＝"トリガー起動時間"の場合]<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where round((rls_hit_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@- hit_tick_timestamp) * 86400) >= ? ) "<BR>
     * <BR>
     * 　@[乖離時間From＝"時価受信時間"、かつ、<BR>
     * 　@乖離時間To＝"発注完了時間"の場合]<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where round((order_submit_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@- hit_tick_timestamp) * 86400) >= ? ) "<BR>
     * <BR>
     * 　@[乖離時間From＝"トリガー起動時間"、かつ、<BR>
     * 　@乖離時間To＝"発注完了時間"の場合]<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_con_order_hit_notify "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "where round((order_submit_timestamp <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@- rls_hit_timestamp) * 86400) >= ? ) "<BR>
     * <BR>
     * １６）　@パラメータ.リクエストデータ.歩み値照合時間From≠null or<BR>
     * 　@　@パラメータ.リクエストデータ.歩み値照合時間To≠null or<BR>
     * 　@　@パラメータ.リクエストデータ.歩み値照合区分≠nullの場合、<BR>
     * 　@歩み値照合条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and order_id in "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(select order_id from rls_order_miss "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@+ "where detect_type = ? "<BR>
     * <BR>
     * 　@　@[パラメータ.リクエストデータ.歩み値照合時間From≠nullの場合]<BR>
     * 　@　@　@歩み値照合時間From条件を検索条件文字列に追加する。<BR>
     * 　@　@　@検索条件文字列 += "and created_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS') "<BR>
     * <BR>
     * 　@　@[パラメータ.リクエストデータ.歩み値照合時間To≠nullの場合]<BR>
     * 　@　@　@歩み値照合時間To条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and created_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS') "<BR>
     * <BR>
     * 　@　@[パラメータ.リクエストデータ.歩み値照合区分≠null かつ、<BR>
     * 　@　@　@歩み値照合区分≠"全てのエラー"の場合]<BR>
     * 　@　@　@歩み値照合区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@検索条件文字列 += "and miss_type = ? "<BR>
     * <BR>
     * 　@　@※上記検索条件追加後、検索条件文字列の末尾に")"を追加すること。<BR>
     * <BR>
     * １７）　@作成した検索条件文字列を返却する。  <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・注文照会共通リクエストオブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43E826BD0329
     */
    public static String createCommonQueryString(
        String l_strInstitutionCode,
        WEB3AdminToOrderRefRefCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCommonQueryString(String, WEB3AdminToOrderRefRefCommonRequest)";
        log.entering(STR_METHOD_NAME);

        //１）　@検索条件文字列インスタンス(：String)を生成する。
        String l_strWhere = new String();

        //２）　@部店条件を検索条件文字列に追加する。
        //パラメータ.リクエストデータ.部店コードの要素数分"?"を追加する。
        int l_intLength = l_request.branchCode.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if (i == 0)
            {
                l_strWhere += "branch_id in (? ";
            }
            else
            {
                l_strWhere += ", ? ";
            }
        }
        l_strWhere += ") ";

        //３）　@パラメータ.リクエストデータ.注文ID≠nullの場合、注文IDを検索条件文字列に追加する。
        if (null != l_request.orderId)
        {
            l_strWhere += "and order_id = ? ";
        }

        //４）　@発注日条件
        //４－１）　@パラメータ.リクエストデータ.発注日≠nullの場合、以下の発注日条件を検索条件文字列に追加する。
        if (null != l_request.orderBizDate)
        {
            l_strWhere += "and biz_date = ? ";
        }
        //４－２）　@パラメータ.リクエストデータ.発注日＝nullの場合、以下の発注日条件を検索条件文字列に追加する。
        else
        {
            l_strWhere += "and biz_date >= ? ";
        }

        //５）　@パラメータ.リクエストデータ.顧客コード≠nullの場合、
        //this.get顧客一覧()の戻り値の要素数分"?"を追加する。
        if (null != l_request.accountCode)
        {
            int l_intCodeLength =
                getAccountList(l_strInstitutionCode, l_request.branchCode, l_request.accountCode).length;
            for (int i = 0; i < l_intCodeLength; i++)
            {
                if (i == 0)
                {
                    l_strWhere += "and account_id in (? ";
                }
                else
                {
                    l_strWhere += ", ?";
                }
            }
            l_strWhere += ") ";
        }

        //６）　@パラメータ.リクエストデータ.市場コード≠nullの場合、市場IDを検索条件文字列に追加する。
        if (null != l_request.marketCode)
        {
            l_strWhere += "and market_id = ? ";
        }

        //７）　@パラメータ.リクエストデータ.発注状況区分≠nullの場合、
        //this.create共通検索条件文字列（発注状況区分）()の戻り値を
        //検索条件文字列に追加する。
        //[引数]
        //　@リクエストデータ：　@パラメータ.リクエストデータ
        if (null != l_request.triggerOrderState)
        {
            l_strWhere += createCommonQueryStringForTriggerOrderState(l_request);
        }

        //８）　@条件注文種別を検索条件文字列に追加する。
        l_strWhere += "and order_id in "
                    + "(select order_id from oms_con_order_request "
                    + "where request_type = ? "
                    + "and order_type = ? "
                    + "and product_type = ? ) ";

        //９）　@パラメータ.リクエストデータ.時価情報受信時間From≠nullの場合、
        //時価情報受信時間From条件を検索条件文字列に追加する。
        if (null != l_request.currentPriceInfoAcceptTimeFrom)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where hit_tick_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //１０）　@パラメータ.リクエストデータ.時価情報受信時間To≠nullの場合、
        //時価情報受信時間To条件を検索条件文字列に追加する。
        if (null != l_request.currentPriceInfoAcceptTimeTo)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where hit_tick_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //１１）　@パラメータ.リクエストデータ.トリガー起動時間From≠nullの場合、
        //トリガー起動時間From条件を検索条件文字列に追加する。
        if (null != l_request.triggerStartTimeFrom)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where rls_hit_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //１２）　@パラメータ.リクエストデータ.トリガー起動時間To≠nullの場合、
        //トリガー起動時間To条件を検索条件文字列に追加する。
        if (null != l_request.triggerStartTimeTo)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where rls_hit_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //１３）　@パラメータ.リクエストデータ.発注完了時間From≠nullの場合、
        //発注完了時間From条件を検索条件文字列に追加する。
        if (null != l_request.orderCompleteTimeFrom)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where order_submit_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //１４）　@パラメータ.リクエストデータ.発注完了時間To≠nullの場合、
        //発注完了時間To条件を検索条件文字列に追加する。
        if (null != l_request.orderCompleteTimeTo)
        {
            l_strWhere += "and order_id in "
                        + "(select order_id from rls_con_order_hit_notify "
                        + "where order_submit_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS')) ";
        }

        //１５）　@パラメータ.リクエストデータ.乖離時間≠nullの場合、
        //パラメータ.リクエストデータ乖離時間の要素数分下記処理を行う。
        if (null != l_request.differentTimeList)
        {
            for (int i = 0; i < l_request.differentTimeList.length; i++)
            {
                //[乖離時間From＝"時価受信時間"、かつ、
                //乖離時間To＝"トリガー起動時間"の場合]
                if (WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(
                    l_request.differentTimeList[i].differentTimeFrom)
                    && WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(
                    l_request.differentTimeList[i].differentTimeTo))
                {
                    l_strWhere += "and order_id in "
                                + "(select order_id from rls_con_order_hit_notify "
                                + "where round((rls_hit_timestamp - hit_tick_timestamp) * 86400) >= ? ) ";
                }

                //[乖離時間From＝"時価受信時間"、かつ、乖離時間To＝"発注完了時間"の場合]
                if (WEB3AdminToDifferentTimeDef.CURRENTPRICE_ACCEPT_TIME.equals(
                    l_request.differentTimeList[i].differentTimeFrom)
                    && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(
                    l_request.differentTimeList[i].differentTimeTo))
                {
                    l_strWhere += "and order_id in "
                                + "(select order_id from rls_con_order_hit_notify "
                                + "where round((order_submit_timestamp - hit_tick_timestamp) * 86400) >= ? ) ";
                }

                //[乖離時間From＝"トリガー起動時間"、かつ、乖離時間To＝"発注完了時間"の場合]
                if (WEB3AdminToDifferentTimeDef.TRIGGER_START_TIME.equals(
                    l_request.differentTimeList[i].differentTimeFrom)
                    && WEB3AdminToDifferentTimeDef.ORDER_COMPLETE_TIME.equals(
                    l_request.differentTimeList[i].differentTimeTo))
                {
                    l_strWhere += "and order_id in "
                                + "(select order_id from rls_con_order_hit_notify "
                                + "where round((order_submit_timestamp - rls_hit_timestamp) * 86400) >= ? ) ";
                }
            }
        }

        //１６）　@パラメータ.リクエストデータ.歩み値照合時間From≠nullの場合、
        //　@歩み値照合時間From条件を検索条件文字列に追加する。
        if (l_request.tickMatchTimeFrom != null
            || l_request.tickMatchTimeTo != null
            || l_request.tickMatchDiv != null)
        {
            l_strWhere += "and order_id in "
                + "(select order_id from rls_order_miss "
                + "where detect_type = ? ";
            
	        //リクエストデータ.歩み値照合時間From≠nullの場合、
            if (l_request.tickMatchTimeFrom != null)
            {
                l_strWhere += "and created_timestamp >= to_date(?,'yyyyMMddHH24:MI:SS') ";
            }
            
	        //リクエストデータ.歩み値照合時間To≠nullの場合、
	        if (l_request.tickMatchTimeTo != null)
	        {
	            l_strWhere += "and created_timestamp <= to_date(?,'yyyyMMddHH24:MI:SS') ";
	        }
	        
            //リクエストデータ.歩み値照合時間To≠null かつ
            // 歩み値照合区分≠"全てのエラー"の場合、
            String l_strTickMatchDiv = l_request.tickMatchDiv;
	        if (l_strTickMatchDiv != null
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(l_strTickMatchDiv))
            {
                l_strWhere += "and miss_type = ? ";
            }
            
            l_strWhere += ")";
        }

        //１９）　@作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strWhere;
    }

    /**
     * (create共通検索条件データコンテナ)<BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@部店条件を生成したArrayListに追加する。<BR>
     * 　@パラメータ.リクエストデータ.部店コードの各要素に<BR>
     * 　@　@該当する部店IDを全てArrayListに追加する。<BR>
     * <BR>
     * 　@※部店コードに該当する部店IDが取得できない場合は <BR>
     * 　@　@「該当データなし」の例外をスローする。<BR>
     *   class: WEB3BusinessLayerException <BR>
     *   tag: BUSINESS_ERROR_01037 <BR>
     * ３）　@パラメータ.リクエストデータ.注文ID≠nullの場合、<BR>
     * 　@注文IDを生成したArrayListに追加する。<BR>
     * <BR>
     * ４）　@発注日条件<BR>
     * 　@４－１）　@パラメータ.リクエストデータ.発注日≠nullの場合、<BR>
     * 　@　@パラメータ.リクエストデータ.発注日を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@４－２）　@パラメータ.リクエストデータ.発注日＝nullの場合、<BR>
     * 　@　@業務日付(*1)を生成したArrayListに追加する。<BR>
     * <BR>
     * ５）　@パラメータ.リクエストデータ.顧客コード≠nullの場合、<BR>
     * 　@口座IDを生成したArrayListに追加する。<BR>
     * <BR>
     * 　@※口座IDは、this.get顧客一覧()にて取得した全ての顧客.口座IDをセット<BR>
     * <BR>
     * 　@[get顧客一覧()にセットするパラメータ]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@部店コード：　@パラメータ.リクエストデータ.部店コード<BR>
     * 　@　@顧客コード：　@パラメータ.リクエストデータ.顧客コード<BR>
     * <BR>
     * ６）パラメータ.リクエストデータ.市場コード != nullの場合、<BR>
     * 　@市場IDを生成したArrayListに追加する。<BR>
     * <BR>
     * 　@※市場IDは、拡張金融オブジェクトマネージャ.get市場()にて<BR>
     * 　@　@取得した市場.市場IDをセット。  <BR>
     * <BR>
     * 　@　@[get市場()にセットするパラメータ]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@市場コード：　@パラメータ.リクエストデータ.市場コード<BR>
     * <BR>
     * ７）　@パラメータ.リクエストデータ.発注状況区分≠nullの場合、<BR>
     * 　@this.create共通検索条件データコンテナ（発注状況区分）()の戻り値の<BR>
     * 　@String配列の各要素を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@リクエストデータ：　@パラメータ.リクエストデータ<BR>
     * <BR>
     * ８）　@条件注文種別を検索条件文字列に追加する。<BR>
     * 　@取引を判別する条件を生成したArrayListに追加する。<BR>
     * 　@　@・2（＝注文登録）<BR>
     * 　@　@・パラメータ.リクエストデータ.条件注文種別<BR>
     * 　@　@・パラメータ.銘柄タイプ<BR>
     * <BR>
     * ９）　@パラメータ.リクエストデータ.時価情報受信時間From≠nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.時価情報受信時間Fromを生成したArrayListに追加する。<BR>
     * <BR>
     * １０）　@パラメータ.リクエストデータ.時価情報受信時間To≠nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.時価情報受信時間Toを生成したArrayListに追加する。<BR>
     * <BR>
     * １１）　@パラメータ.リクエストデータ.トリガー起動時間From≠nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.トリガー起動時間Fromを生成したArrayListに追加する。<BR>
     * <BR>
     * １２）　@パラメータ.リクエストデータ.トリガー起動時間To≠nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.トリガー起動時間Toを生成したArrayListに追加する。<BR>
     * <BR>
     * １３）　@パラメータ.リクエストデータ.発注完了時間From≠nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.発注完了時間Fromを生成したArrayListに追加する。<BR>
     * <BR>
     * １４）　@パラメータ.リクエストデータ.発注完了時間To≠nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.発注完了時間Toを生成したArrayListに追加する。<BR>
     * <BR>
     * １５）　@パラメータ.リクエストデータ.乖離時間≠nullの場合、<BR>
     * パラメータ.リクエストデータ乖離時間の各要素の乖離時間を<BR>
     * 生成したArrayListに追加する。 <BR>
     * <BR>
     * １６）　@パラメータ.リクエストデータ.歩み値照合時間From≠null or<BR>
     * 　@　@パラメータ.リクエストデータ.歩み値照合時間To≠null or<BR>
     * 　@　@パラメータ.リクエストデータ.歩み値照合区分≠nullの場合、<BR>
     * 　@　@以下を生成したArrayListに追加する。<BR>
     * 　@　@・"オンライン"（検知区分）<BR>
     * <BR>
     * 　@　@[パラメータ.リクエストデータ.歩み値照合時間From≠nullの場合]<BR>
     * 　@　@　@・パラメータ.リクエストデータ.歩み値照合時間From<BR>
     * <BR>
     * 　@　@[パラメータ.リクエストデータ.歩み値照合時間To≠nullの場合]<BR>
     * 　@　@　@・パラメータ.リクエストデータ.歩み値照合時間To<BR>
     * <BR>
     * 　@　@[パラメータ.リクエストデータ.歩み値照合区分≠null かつ、<BR>
     * 　@　@　@歩み値照合区分≠"全てのエラー"の場合]<BR>
     * 　@　@　@・パラメータ.リクエストデータ.歩み値照合区分<BR>
     * <BR>
     * １７）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate()<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・注文照会共通リクエストオブジェクト<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 43E884A5018B
     */
    public static String[] createCommonQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminToOrderRefRefCommonRequest l_request,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createCommonQueryDataContainer("
            + "String, WEB3AdminToOrderRefRefCommonRequest, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）　@ArrayListを生成する。
        List l_lisQueryContainers = new ArrayList();

        //２）　@部店条件を生成したArrayListに追加する。パラメータ.リクエストデータ.部店コードの各要素に
        //該当する部店IDを全てArrayListに追加する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        int l_intCodeLength = l_request.branchCode.length;
        for (int i = 0; i < l_intCodeLength; i++)
        {
            try
            {
                l_branch =
                    (WEB3GentradeBranch)l_accountManager.getBranch(
                        l_accountManager.getInstitution(l_strInstitutionCode), l_request.branchCode[i]);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            l_lisQueryContainers.add(String.valueOf(l_branch.getBranchId()));
        }

        //３）　@パラメータ.リクエストデータ.注文ID≠nullの場合、
        //注文IDを生成したArrayListに追加する。
        if (null != l_request.orderId)
        {
            l_lisQueryContainers.add(l_request.orderId);
        }

        //４）　@発注日条件
        // 　@４－１）　@パラメータ.リクエストデータ.発注日≠nullの場合、
        //   パラメータ.リクエストデータ.発注日を生成したArrayListに追加する。
        if (null != l_request.orderBizDate)
        {
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(l_request.orderBizDate, "yyyyMMdd"));
        }

        //   ４－２）　@パラメータ.リクエストデータ.発注日＝nullの場合、
        // 　@　@業務日付(*1)を生成したArrayListに追加する。
        //     (*1)GtlUtils.getTradingSystem().getBizDate()
        if (null == l_request.orderBizDate)
        {
            l_lisQueryContainers.add(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd"));
        }

        //５）　@パラメータ.リクエストデータ.顧客コード≠nullの場合、
        // 　@口座IDを生成したArrayListに追加する。
        if (null != l_request.accountCode)
        {
            WEB3GentradeMainAccount[] l_mainAccount =
                getAccountList(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            int l_intLength = l_mainAccount.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_lisQueryContainers.add(Long.toString(l_mainAccount[i].getAccountId()));
            }
        }

        //６）　@パラメータ.リクエストデータ.市場コード≠nullの場合、
        // 　@市場IDを生成したArrayListに追加する。
        if (null != l_request.marketCode)
        {
            WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            Market l_market = null;

            try
            {
                //throw NotFoundException
                l_market = l_gentradeFinObjectManager.getMarket(l_strInstitutionCode, l_request.marketCode);
            }
            catch (NotFoundException l_nfex)
            {
                log.error("テーブルに該当するデータがありません。", l_nfex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_nfex.getMessage(),
                l_nfex);
            }

            l_lisQueryContainers.add(Long.toString(l_market.getMarketId()));
        }

        //７）　@パラメータ.リクエストデータ.発注状況区分≠nullの場合、
        //this.create共通検索条件データコンテナ（発注状況区分）()の戻り値の
        //String配列の各要素を生成したArrayListに追加する。
        //[引数]
        //　@リクエストデータ：　@パラメータ.リクエストデータ
        if (null != l_request.triggerOrderState)
        {
            String[] l_strOrderStates =
                createCommonQueryDataContainerForTriggerOrderState(l_request);
            int l_intLength = l_strOrderStates.length;
            for (int i = 0; i < l_intLength; i++)
            {
                l_lisQueryContainers.add(l_strOrderStates[i]);
            }
        }

        // ８）　@条件注文種別を検索条件文字列に追加する。
        // 　@取引を判別する条件を生成したArrayListに追加する。
        // 　@　@・2（＝注文登録）
        // 　@　@・パラメータ.リクエストデータ.条件注文種別
        // 　@　@・パラメータ.銘柄タイプ
        l_lisQueryContainers.add(String.valueOf(WEB3RlsRequestTypeDef.NEW));
        l_lisQueryContainers.add(l_request.triggerOrderType);
        l_lisQueryContainers.add(String.valueOf(l_productType.intValue()));

        // ９）　@パラメータ.リクエストデータ.時価情報受信時間From≠nullの場合、
        // 　@パラメータ.リクエストデータ.時価情報受信時間Fromを生成したArrayListに追加する。
        if (null != l_request.currentPriceInfoAcceptTimeFrom)
        {
            l_lisQueryContainers.add(l_request.currentPriceInfoAcceptTimeFrom);
        }

        // １０）　@パラメータ.リクエストデータ.時価情報受信時間To≠nullの場合、
        // 　@パラメータ.リクエストデータ.時価情報受信時間Toを生成したArrayListに追加する。
        if (null != l_request.currentPriceInfoAcceptTimeTo)
        {
            l_lisQueryContainers.add(l_request.currentPriceInfoAcceptTimeTo);
        }

        // １１）　@パラメータ.リクエストデータ.トリガー起動時間From≠nullの場合、
        // 　@パラメータ.リクエストデータ.トリガー起動時間Fromを生成したArrayListに追加する。
        if (null != l_request.triggerStartTimeFrom)
        {
            l_lisQueryContainers.add(l_request.triggerStartTimeFrom);
        }

        // １２）　@パラメータ.リクエストデータ.トリガー起動時間To≠nullの場合、
        // 　@パラメータ.リクエストデータ.トリガー起動時間Toを生成したArrayListに追加する。
        if (null != l_request.triggerStartTimeTo)
        {
            l_lisQueryContainers.add(l_request.triggerStartTimeTo);
        }

        // １３）　@パラメータ.リクエストデータ.発注完了時間From≠nullの場合、
        // 　@パラメータ.リクエストデータ.発注完了時間Fromを生成したArrayListに追加する。
        if (null != l_request.orderCompleteTimeFrom)
        {
            l_lisQueryContainers.add(l_request.orderCompleteTimeFrom);
        }

        // １４）　@パラメータ.リクエストデータ.発注完了時間To≠nullの場合、
        // 　@パラメータ.リクエストデータ.発注完了時間Toを生成したArrayListに追加する。
        if (null != l_request.orderCompleteTimeTo)
        {
            l_lisQueryContainers.add(l_request.orderCompleteTimeTo);
        }

        // １５）　@パラメータ.リクエストデータ.乖離時間≠nullの場合、
        // パラメータ.リクエストデータ乖離時間の各要素の乖離時間を
        // 生成したArrayListに追加する。
        if (null != l_request.differentTimeList)
        {
            for (int i = 0; i < l_request.differentTimeList.length; i++)
            {
                l_lisQueryContainers.add(l_request.differentTimeList[i].differentTime);
            }
        }

        //１６）　@パラメータ.リクエストデータ.歩み値照合時間From≠null or
        //    パラメータ.リクエストデータ.歩み値照合時間To≠null or
        //    パラメータ.リクエストデータ.歩み値照合区分≠nullの場合、
        //    以下を生成したArrayListに追加する。
        String l_strTickMatchDiv = l_request.tickMatchDiv;
        if (l_request.tickMatchTimeFrom != null
            || l_request.tickMatchTimeTo != null
            || l_strTickMatchDiv != null)
        {
            //　@　@・"オンライン"（検知区分）
            l_lisQueryContainers.add(WEB3DetectTypeDef.ON_LINE);
            
            //リクエストデータ.歩み値照合時間From≠nullの場合、
            if (l_request.tickMatchTimeFrom != null)
            {
	            l_lisQueryContainers.add(l_request.tickMatchTimeFrom);
            }
            
            //リクエストデータ.歩み値照合時間To≠null かつ
            // 歩み値照合区分≠"全てのエラー"の場合、
            if (l_request.tickMatchTimeTo != null)
            {
	            l_lisQueryContainers.add(l_request.tickMatchTimeTo);
            }
            
            if (l_strTickMatchDiv != null
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(l_strTickMatchDiv))
            {
                l_lisQueryContainers.add(l_strTickMatchDiv);
            }
        }

        //１９）　@生成したArrayList.toArray()の戻り値を返却する。
        String[] l_strQueryContainers = new String[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_strQueryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
    }

    /**
     * (getルールエンジンからの通知)<BR>
     * 引数の条件に該当するルールエンジンからの通知Paramsを返却する。<BR> 
     * <BR>
     * １）　@ルールエンジンからの通知テーブル(rls_con_order_hit_notify)を<BR>
     * 　@　@　@引数の条件で検索した結果を返却する。<BR>
     * <BR>
     * 　@[検索条件]<BR> 
     * 　@　@口座ID：　@パラメータ.注文単位.口座ID<BR>
     * 　@　@補助口座ID：　@パラメータ.注文単位.補助口座ID<BR>
     * 　@　@条件付き注文タイプ：　@パラメータ.条件注文種別<BR>
     * 　@　@注文ID：　@パラメータ.注文単位.注文ID<BR>
     * 　@　@注文銘柄タイプ：　@パラメータ.銘柄タイプ<BR>
     * <BR>
     * ２）　@検索結果を返却する。<BR>
     * 　@※検索結果が取得できなかった場合、nullを返却する。<BR> 
     * 　@※検索結果のレコードが複数件の場合は、データ不整合の例外をスローする。<BR>
     * class: WEB3SystemLayerException<BR>
     * tag  : SYSTEM_ERROR_80006<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strTriggerOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@return RlsConOrderHitNotifyParams
     * @@throws WEB3BaseException
     * @@roseuid 43E9B694028B
     */
    public static RlsConOrderHitNotifyParams getRlsConOrderHitNotify(
        OrderUnit l_orderUnit,
        String l_strTriggerOrderType,
        ProductTypeEnum l_productType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRlsConOrderHitNotify(OrderUnit, String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）　@ルールエンジンからの通知テーブル(rls_con_order_hit_notify)を引数の条件で検索した結果を返却する。
        // 　@[検索条件]
        // 　@　@口座ID：　@パラメータ.注文単位.口座ID
        // 　@　@補助口座ID：　@パラメータ.注文単位.補助口座ID
        // 　@　@条件付き注文タイプ：　@パラメータ.条件注文種別
        //   　@注文ID：　@パラメータ.注文単位.注文ID
        // 　@　@注文銘柄タイプ：　@パラメータ.銘柄タイプ
        String l_strWhere = " account_id = ? ";
        l_strWhere += " and sub_account_id = ? ";
        l_strWhere += " and order_type = ? ";
        l_strWhere += " and order_id = ? ";
        l_strWhere += " and product_type = ? ";
        
        Object[] l_objValue = 
            new Object[]{
                String.valueOf(l_orderUnit.getAccountId()),
                String.valueOf(l_orderUnit.getSubAccountId()),
                l_strTriggerOrderType,
                String.valueOf(l_orderUnit.getOrderId()),
                String.valueOf(l_productType.intValue())}; 
        
        List l_lisRecords = new ArrayList();
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                RlsConOrderHitNotifyRow.TYPE,
                l_strWhere,
                null,
                l_objValue);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //２）　@検索結果を返却する。
        //※検索結果が取得できなかった場合、nullを返却する。
        if (null == l_lisRecords || 0 == l_lisRecords.size())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //※検索結果のレコードが複数件の場合は、データ不整合の例外をスローする。
        if (l_lisRecords.size() > 1)
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                "WEB3AdminToOrderReferenceDataManager" + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }
        
        RlsConOrderHitNotifyParams[] l_params = new RlsConOrderHitNotifyParams[l_lisRecords.size()];
        l_lisRecords.toArray(l_params);
        
        log.exiting(STR_METHOD_NAME);
        return l_params[0];
    }
    
    /**
     * (reset取引カレンダコンテキスト)<BR>
     * 取引カレンダコンテキストの値を引数の値で再セットする。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストを取得する。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールする。<BR>
     * <BR>
     * 　@[getAttribute()にセットするパラメータ]<BR>
     * 　@　@arg0：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * 　@※取得できなかった場合は、取引カレンダコンテキストを生成し、<BR>
     * 　@　@　@以降の処理で使用すること。<BR>
     * <BR>
     * ２）　@取得した取引カレンダコンテキストに以下のプロパティをセットする。<BR>
     * 　@※対応するパラメータがnullの場合は、再セットを行わない。<BR>
     * 　@　@IDに該当するオブジェクトが取得できなかった場合、<BR>
     * 　@　@「該当データなし」のシステムエラーをスローする。<BR>
     * 　@　@　@class: WEB3SystemLayerException<BR>
     * 　@　@　@tag  : SYSTEM_ERROR_80005<BR>
     * <BR>
     * [パラメータ.銘柄タイプ == "株式"の場合]<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@部店コード = パラメータ.部店IDに該当する部店コード<BR>
     * 　@　@市場コード = パラメータ.市場IDに該当する市場コード<BR>
     * 　@　@銘柄コード = パラメータ.銘柄IDに該当する株式銘柄.銘柄コード<BR>
     * 　@　@受付時間区分 = パラメータ.受付時間区分<BR>
     * <BR>
     * 　@[パラメータ.銘柄タイプ == "先物オプション"の場合<BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@部店コード = パラメータ.部店IDに該当する部店コード<BR>
     * 　@　@市場コード = "DEFAULT"<BR>
     * 　@　@銘柄コード = パラメータ.銘柄IDに該当する先物OP銘柄.原資産銘柄コード<BR>
     * 　@　@受付時間区分 = パラメータ.受付時間区分<BR>
     * <BR>
     * ３）　@取引カレンダコンテキストを再セットする。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.setAttribute()をコールする。<BR>
     * <BR>
     * 　@[setAttribute()にセットするパラメータ]<BR>
     * 　@　@arg0：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 　@　@arg1：　@プロパティセットした取引カレンダコンテキスト<BR>
     * <BR>
     * ４）　@受付日時、日付ロールのリセット<BR>
     * 　@取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@param l_branchID - (部店ID)<BR>
     * 部店ID<BR>
     * @@param l_marketID - (市場ID)<BR>
     * 市場ID<BR>
     * @@param l_productID - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_strTradetimeType - (受付時間区分)<BR>
     * 受付時間区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E9B694028B
     */
    public static void resetTradingCalContext(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        Long l_branchID,
        Long l_marketID,
        Long l_productID,
        String l_strTradetimeType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " resetTradingCalContext(String, ProductTypeEnum, Long, Long, Long, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@取引カレンダコンテキストを取得する。
        //ThreadLocalSystemAttributesRegistry.getAttribute()をコールする。
        //※取得できなかった場合は、取引カレンダコンテキストを生成し、
        //　@以降の処理で使用すること。
        Object l_object = ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        WEB3GentradeTradingClendarContext l_context = null;
        if (l_object != null)
        {
            l_context = (WEB3GentradeTradingClendarContext) l_object;
        }
        else
        {
            l_context = new WEB3GentradeTradingClendarContext();
        }

        //※対応するパラメータがnullの場合は、再セットを行わない。
        String l_strInstitutionCodeForSet = l_context.getInstitutionCode();
        String l_strBranchCodeForSet = l_context.getBranchCode();
        String l_strMarketCodeForSet = l_context.getMarketCode();
        String l_strProductCodeForSet = l_context.getProductCode();
        String l_strTradetimeDivForSet = l_context.getTradingTimeType();

        if (l_strInstitutionCode != null)
        {
            l_strInstitutionCodeForSet = l_strInstitutionCode;
        }

        if (l_strTradetimeType != null)
        {
            l_strTradetimeDivForSet = l_strTradetimeType;
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        if (l_branchID != null)
        {
            try
            {
                Branch l_branch = l_accountManager.getBranch(l_branchID.longValue());
                l_strBranchCodeForSet = l_branch.getBranchCode();
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }

        if (l_marketID != null)
        {
            try
            {
                WEB3GentradeFinObjectManager l_finObjManager =
                    (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket) l_finObjManager.getMarket(l_marketID.longValue());
                l_strMarketCodeForSet = l_market.getMarketCode();
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }

        if (l_productID != null)
        {
            if (ProductTypeEnum.IFO.equals(l_productType))
            {
                IfoProductRow l_ifoProductRow = null;
                try
                {
                    l_ifoProductRow = IfoProductDao.findRowByPk(l_productID.longValue());
                }
                catch (DataFindException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                l_strProductCodeForSet = l_ifoProductRow.getUnderlyingProductCode();
            }
        }

        //２）　@取得した取引カレンダコンテキストに以下のプロパティをセットする。
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            //証券会社コード = パラメータ.証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCodeForSet);
            //部店コード = パラメータ.部店IDに該当する部店コード
            l_context.setBranchCode(l_strBranchCodeForSet);
            //市場コード = パラメータ.市場IDに該当する市場コード
            l_context.setMarketCode(l_strMarketCodeForSet);
            //銘柄コード = "DEFAULT"
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //受付時間区分 = パラメータ.受付時間区分
            l_context.setTradingTimeType(l_strTradetimeDivForSet);
        }
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //証券会社コード = パラメータ.証券会社コード
            l_context.setInstitutionCode(l_strInstitutionCodeForSet);
            //部店コード = パラメータ.部店IDに該当する部店コード
            l_context.setBranchCode(l_strBranchCodeForSet);
            //市場コード = "DEFAULT"
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //銘柄コード = パラメータ.銘柄IDに該当する先物OP銘柄.原資産銘柄コード
            l_context.setProductCode(l_strProductCodeForSet);
            //受付時間区分 = パラメータ.受付時間区分
            l_context.setTradingTimeType(l_strTradetimeDivForSet);
        }

        //３）　@取引カレンダコンテキストを再セットする。
        //ThreadLocalSystemAttributesRegistry.setAttribute()をコールする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //４）　@受付日時、日付ロールのリセット
        //取引時間管理.setTimestamp()をコールする。
        WEB3GentradeTradingTimeManagement.setTimestamp();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get手動失効対象注文単位一覧)<BR>
     * 手動失効対象の注文単位の一覧を取得する。<BR>
     * <BR>
     * １）　@検索条件文字列（：String）、<BR>
     * 　@検索条件データコンテナ（：ArrayList）を生成する。<BR>
     * <BR>
     * ２）共通条件を作成する。<BR>
     * 　@２－１）　@ID直接指定時（パラメータ.注文ID != null）の場合、<BR>
     * 　@　@注文ID条件を検索条件に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "order_id = ?"<BR>
     * 　@　@データコンテナにパラメータ.注文IDを追加する。<BR>
     * <BR>
     * 　@２－２）　@上記以外の場合、<BR>
     * 　@　@注文を特定する条件を検索条件に追加する。<BR>
     * 　@　@２－２－１）　@部店条件を検索条件に追加する。<BR>
     * 　@　@　@パラメータ.部店コードの要素数分"?"を追加する。 <BR>
     * <BR>
     * 　@　@　@検索条件文字列 = "branch_id in (?, ?,,,) "<BR>
     * 　@　@　@データコンテナにパラメータ.部店コードに該当する部店.部店IDを<BR>
     * 　@　@　@要素数分、addする。<BR>
     * <BR>
     * 　@　@　@※部店を取得する際に、パラメータ.証券会社を使用する。<BR>
     * 　@　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」<BR>
     * 　@　@　@　@の業務エラーをスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * 　@　@２－２－２）　@注文条件を検索条件に追加する。<BR>
     * 　@　@　@検索条件文字列 += " and order_open_status = ?"<BR>
     * 　@　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・"オープン"<BR>
     * <BR>
     * 　@　@２－２－３）　@条件注文種別条件を検索条件に追加する。<BR>
     * 　@　@　@２－２－３－１）　@以下、パラメータ.条件注文種別一覧の要素数分、<BR>
     * 　@　@　@　@Loopし、条件注文種別条件を作成する。 <BR>
     * 　@　@　@　@　@　@２－２－３－１－１）　@処理対象の要素が"逆指値"の場合 <BR>
     * 　@　@　@　@　@　@　@[条件注文種別条件 == ""の場合] <BR>
     * 　@　@　@　@　@　@　@条件注文種別条件 = " (order_condition_type = ?)"<BR>
     * <BR>
     *  　@　@　@　@　@　@[上記以外の場合]<BR>
     *  　@　@　@　@　@　@条件注文種別条件 += " or (order_condition_type = ?)"<BR>
     * <BR>
     *   　@　@　@　@　@　@データコンテナ（以下の順で追加する。）<BR>
     *   　@　@　@　@　@　@・"逆指値" <BR>
     * <BR>
     * 　@　@　@　@　@　@２－２－３－１－２）　@処理対象の要素が"W指値"の場合 <BR>
     * 　@　@　@　@　@　@　@[条件注文種別条件 == ""の場合]<BR>
     *  　@　@　@　@　@　@ 条件注文種別条件 = " (order_condition_type = ?" <BR>
     *  　@　@　@　@　@　@　@+ " and request_type <> ?)"<BR>
     * <BR>
     *  　@　@　@　@　@　@[上記以外の場合]<BR>
     *   　@　@　@　@　@　@条件注文種別条件 += " or (order_condition_type = ?"<BR>
     *    　@　@　@　@　@　@+ " and request_type <> ?)"<BR>
     * <BR>
     * 　@　@　@　@　@　@　@データコンテナ（以下の順で追加する。）<BR>
     *  　@　@　@　@　@　@　@・"W指値"<BR>
     *   　@　@　@　@　@　@ ・"時価サーバ" <BR>
     * <BR>
     * 　@　@　@２－２－３－２）　@作成した条件注文種別条件を<BR>
     * 　@　@　@　@検索条件文字列に追加する。 <BR>
     * <BR>
     * 　@　@　@　@検索条件文字列 += " and (" + 条件注文種別条件 + ")" <BR>
     * <BR>
     * 　@　@２－２－４）　@パラメータ.顧客コード != nullの場合、<BR>
     * 　@　@　@顧客条件を検索条件に追加する。<BR>
     * 　@　@　@２－２－４－１）　@トリガー注文管理者データマネージャ.get顧客一覧()を<BR>
     * 　@　@　@　@コールする。<BR>
     * <BR>
     * 　@　@　@　@[get顧客一覧()に指定する引数]<BR>
     * 　@　@　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード<BR>
     * 　@　@　@　@　@部店コード：　@パラメータ.部店コード<BR>
     * 　@　@　@　@　@顧客コード：　@パラメータ.顧客コード<BR>
     * <BR>
     * 　@　@　@２－２－４－２）　@２－２－４－１）の戻り値の要素数分、検索条件に"?"を、<BR>
     * 　@　@　@　@データコンテナに、各要素の口座IDを追加する。<BR>
     * <BR>
     * 　@　@　@　@検索条件文字列 += " and account_id in (?, ?,,,) "<BR>
     * 　@　@　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@　@・２－２－４－１）の戻り値の各要素.口座ID<BR>
     * <BR>
     * 　@２－３）　@パラメータ.口座IDFrom != null　@かつ<BR>
     * 　@　@パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を<BR>
     * 　@　@検索条件に追加する。<BR>
     * 　@　@※非同期処理を行う場合に設定される。<BR>
     * <BR>
     * 　@　@検索条件文字列 += " and account_id >= ?"<BR>
     * 　@　@　@　@　@　@　@　@+ " and account_id <= ?"<BR>
     * 　@　@データコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@・パラメータ.口座IDFrom<BR>
     * 　@　@　@・パラメータ.口座IDTo<BR>
     * <BR>
     * ３）　@戻り値を格納するArrayListを生成する。<BR>
     * <BR>
     * ４）　@パラメータ.商品区分一覧に"現物株式" or "信用取引"が含まれる場合、<BR>
     * 　@　@以下の処理を行う。<BR>
     * 　@４－１）　@ID直接指定時でない（パラメータ.注文ID == null）の場合、<BR>
     * 　@　@Eqtype特有の検索条件を作成する。<BR>
     * <BR>
     * 　@　@４－１－１）　@パラメータ.注文ID == nullの場合、<BR>
     * 　@　@[パラメータ.商品区分一覧に"現物株式"が含まれる場合]<BR>
     * 　@　@　@Eqtype検索条件文字列 = " (order_type in (?, ?)"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@+ " and sonar_traded_code <> ?)"<BR>
     * 　@　@　@Eqtypeデータコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・"現物買注文"<BR>
     * 　@　@　@　@・"現物売注文"<BR>
     * 　@　@　@　@・"立会外分売"<BR>
     * <BR>
     * 　@　@[パラメータ.商品区分一覧に"信用取引"が含まれる場合]<BR>
     * 　@　@　@[Eqtype検索条件文字列が空文字の場合]<BR>
     * 　@　@　@　@Eqtype検索条件文字列 = " (order_categ in (?, ?))"<BR>
     * 　@　@　@[上記以外]<BR>
     * 　@　@　@　@Eqtype検索条件文字列 += " or order_categ in (?, ?)"<BR>
     * <BR>
     * 　@　@　@Eqtypeデータコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・"信用新規建注文"<BR>
     * 　@　@　@　@・"信用返済注文"<BR>
     * <BR>
     * 　@　@４－１－２）　@Eqtype検索条件文字列に演算子を追加する。<BR>
     * 　@　@　@Eqtype検索条件文字列 = " and (" + Eqtype検索条件文字列 + ")"<BR>
     * <BR>
     * 　@　@４－１－３）　@市場条件をEqtype検索条件文字列に追加する。<BR>
     * 　@　@　@パラメータ.市場コード一覧の要素数分"?"を追加する。<BR>
     * <BR>
     * 　@　@　@Eqtype検索条件文字列 = " and market_id in (?, ?,,,) "<BR>
     * 　@　@　@Eqtypeデータコンテナにパラメータ.市場コード一覧に該当する市場.市場IDを<BR>
     * 　@　@　@要素数分、addする。<BR>
     * <BR>
     * 　@　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」<BR>
     * 　@　@　@の業務エラーをスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * 　@　@４－１－４）　@パラメータ.銘柄コード != nullの場合、<BR>
     * 　@　@　@銘柄条件をEqtype検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@Eqtype検索条件文字列 = " and product_id = ?"<BR>
     * 　@　@　@Eqtypeデータコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・パラメータ.銘柄コードに該当する株式銘柄.銘柄ID<BR>
     * <BR>
     * 　@　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」<BR>
     * 　@　@　@の業務エラーをスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * 　@４－２）　@DBを検索する。<BR>
     * 　@　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@　@arg0：　@株式注文単位Row.TYPE <BR>
     * 　@　@　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@　@　@　@+ 作成したEqtype検索条件文字列<BR>
     * 　@　@　@　@arg2：　@作成したデータコンテナ.toArray()と<BR>
     * 　@　@　@　@　@　@　@　@作成したEqtypeデータコンテナ.toArray()の<BR>
     * 　@　@　@　@　@　@　@　@戻り値を要素とする配列<BR>
     * 　@　@　@　@※Eqtype検索条件／データコンテナを作成していない場合は、<BR>
     * 　@　@　@　@　@検索条件に追加しない。<BR>
     * <BR>
     * 　@４－３）　@４－２）の戻り値の各要素から株式注文単位を作成し、<BR>
     * 　@　@戻り値を格納するArrayListに追加する。<BR>
     * <BR>
     * 　@　@※株式注文単位は、拡張株式注文マネージャ.toOrderUnit()にて<BR>
     * 　@　@　@作成する。<BR>
     * <BR>
     * ５）　@パラメータ.商品区分一覧に"先物" or "オプション"が含まれる場合、<BR>
     * 　@以下の処理を行う。<BR>
     * 　@５－１）　@ID直接指定時でない（パラメータ.注文ID == null）の場合、<BR>
     * 　@　@Ifo特有の検索条件を作成する。<BR>
     * <BR>
     * 　@　@[パラメータ.商品区分一覧に"先物"が含まれる場合]<BR>
     * 　@　@　@Ifo検索条件文字列 = " future_option_div = ?"<BR>
     * 　@　@　@Ifoデータコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・"先物"<BR>
     * <BR>
     * 　@　@[パラメータ.商品区分一覧に"オプション"が含まれる場合]<BR>
     * 　@　@　@[Ifo検索条件文字列が空文字の場合]<BR>
     * 　@　@　@　@Ifo検索条件文字列 = " future_option_div = ?"<BR>
     * 　@　@　@[上記以外]<BR>
     * 　@　@　@　@Ifo検索条件文字列 += " or future_option_div = ?"<BR>
     * <BR>
     * 　@　@　@Ifoデータコンテナ（以下の順で追加する。）<BR>
     * 　@　@　@　@・"オプション"<BR>
     * <BR>
     * 　@　@５－１－１）　@Ifo検索条件文字列に演算子を追加する。<BR>
     * 　@　@　@Ifo検索条件文字列 = " and (" + Ifo検索条件文字列 + ")"<BR>
     * <BR>
     * 　@５－２）　@DBを検索する。<BR>
     * 　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@　@　@arg0：　@先物OP注文単位Row.TYPE <BR>
     * 　@　@　@arg1：　@作成した検索条件文字列<BR>
     * 　@　@　@　@　@　@　@+ 作成したIfo検索条件文字列<BR>
     * 　@　@　@arg2：　@作成したデータコンテナ.toArray()と<BR>
     * 　@　@　@　@　@　@　@作成したIfoデータコンテナ.toArray()の<BR>
     * 　@　@　@　@　@　@　@戻り値を要素とする配列<BR>
     * 　@　@　@※Ifo検索条件／データコンテナを作成していない場合は、<BR>
     * 　@　@　@　@検索条件に追加しない。<BR>
     * <BR>
     * 　@５－３）　@５－２）の戻り値の各要素から先物OP注文単位を作成し、<BR>
     * 　@　@戻り値を格納するArrayListに追加する。<BR>
     * <BR>
     * 　@　@※先物OP注文単位は、OP注文マネージャ.toOrderUnit()にて<BR>
     * 　@　@　@作成する。<BR>
     * <BR>
     * ６）　@戻り値を格納するArrayList.toArray()の戻り値を返却する。<BR>
     * 　@　@※戻り値を格納するArrayList.size() == 0の場合、nullを返却する。<BR>
     * <BR>
     * @@param l_orderID - (注文ID)<BR>
     * ※ID直接指定時のみセット<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列<BR>
     * @@param l_strTriggerOrderTypes - (条件注文種別一覧)<BR>
     * 条件注文種別一覧<BR>
     * @@param l_strProductTypes - (商品区分一覧)<BR>
     * 商品区分一覧<BR>
     * @@param l_strMarketCodes - (市場コード一覧)<BR>
     * 市場コードの配列<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_accountIDFrom - (口座IDFrom)<BR>
     * 口座IDFrom<BR>
     * @@param l_accountIDTo - (口座IDTo)<BR>
     * 口座IDTo<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43E9B694028B
     * @@return OrderUnit[]
     */
    public static OrderUnit[] getManualExpireOrderUnits(
        Long l_orderID,
        Institution l_institution,
        String[] l_strBranchCodes,
        String[] l_strTriggerOrderTypes,
        String[] l_strCommodityDivs,
        String[] l_strMarketCodes,
        String l_strProductCode,
        String l_strAccountCode,
        Long l_accountIDFrom,
        Long l_accountIDTo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getManualExpireOrderUnits("
            + "Long, Institution, String[], "
            + "String[], String[], String[], "
            + "String, String, Long, Long)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@検索条件文字列（：String）、
        //検索条件データコンテナ（：ArrayList）を生成する。
        ArrayList l_lisQueryStrings = new ArrayList();
        ArrayList l_lisQueryContainers = new ArrayList();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //２）共通条件を作成する。
        //２－１）　@ID直接指定時（パラメータ.注文ID != null）の場合、
        //注文ID条件を検索条件に追加する。
        if (l_orderID != null)
        {
            //　@検索条件文字列 = "order_id = ?"
            l_lisQueryStrings.add(" order_id = ? ");
            //　@データコンテナにパラメータ.注文IDを追加する。
            l_lisQueryContainers.add(l_orderID);
        }
        //２－２）　@上記以外の場合、注文を特定する条件を検索条件に追加する。
        else
        {
            //２－２－１）　@部店条件を検索条件に追加する。
            //パラメータ.部店コードの要素数分"?"を追加する。
            //検索条件文字列 = "branch_id in (?, ?,,,) "
            if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
            {
                StringBuffer l_sbBranchIdForQuery = new StringBuffer(" branch_id in (");
                int l_intLength = l_strBranchCodes.length;
                try
                {
                    for (int i = 0; i < l_intLength; i++)
                    {
                        l_sbBranchIdForQuery.append("?,");
                        //データコンテナにパラメータ.部店コードに
                        //該当する部店.部店IDを要素数分、addする。
                        Branch l_branch = l_accountManager.getBranch(l_institution, l_strBranchCodes[i]);
                        l_lisQueryContainers.add(String.valueOf(l_branch.getBranchId()));
                    }
                }
                catch (NotFoundException l_nfe)
                {
                    log.error("条件に該当するデータが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        "条件に該当するデータが存在しない。");
                }
                l_lisQueryStrings.add(l_sbBranchIdForQuery.substring(0, l_sbBranchIdForQuery.length() - 1) + ") ");
                //２－２－２）　@注文条件を検索条件に追加する。
                //検索条件文字列 += " and order_open_status = ?"
                l_lisQueryStrings.add(" and order_open_status = ? ");
            }
            else
            {
                //２－２－２）　@注文条件を検索条件に追加する。
                //検索条件文字列 += " order_open_status = ?"
                l_lisQueryStrings.add(" order_open_status = ? ");
            }

            //データコンテナ（以下の順で追加する。）
            //・"オープン"
            l_lisQueryContainers.add(String.valueOf(OrderOpenStatusEnum.OPEN.intValue()));

            //２－２－３）　@条件注文種別条件を検索条件に追加する。
            //２－２－３－１）　@以下、パラメータ.条件注文種別一覧の要素数分、Loopし、条件注文種別条件を作成する。
            String l_strTriggerOrderTypeCondtion = "";
            if (l_strTriggerOrderTypes != null && l_strTriggerOrderTypes.length != 0)
            {
                int l_intLength = l_strTriggerOrderTypes.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    //２－２－３－１－１）　@処理対象の要素が"逆指値"の場合
                    if (WEB3TriggerOrderTypeDef.STOP.equals(l_strTriggerOrderTypes[i]))
                    {
                        //[条件注文種別条件 == ""の場合]
                        //条件注文種別条件 = " (order_condition_type = ?)"
                        if ("".equals(l_strTriggerOrderTypeCondtion))
                        {
                            l_strTriggerOrderTypeCondtion = " (order_condition_type = ?)";
                        }
                        //[上記以外の場合]
                        //条件注文種別条件 += " or (order_condition_type = ?)"
                        else
                        {
                            l_strTriggerOrderTypeCondtion += " or (order_condition_type = ?)";
                        }

                        //データコンテナ（以下の順で追加する。）
                        //・"逆指値"
                        l_lisQueryContainers.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
                    }

                    //２－２－３－１－２）　@処理対象の要素が"W指値"の場合
                    else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_strTriggerOrderTypes[i]))
                    {
                        //[条件注文種別条件 == ""の場合]
                        //条件注文種別条件 = " (order_condition_type = ?"
                        //           + " and request_type <> ?)"
                        if ("".equals(l_strTriggerOrderTypeCondtion))
                        {
                            l_strTriggerOrderTypeCondtion = " (order_condition_type = ?"
                                + " and request_type <> ?)";
                        }

                        //[上記以外の場合]
                        //条件注文種別条件 += " or (order_condition_type = ?"
                        //　@　@　@　@+ " and request_type <> ?)"
                        else
                        {
                            l_strTriggerOrderTypeCondtion +=
                                " or (order_condition_type = ?" + " and request_type <> ?)";
                        }

                        // データコンテナ（以下の順で追加する。）
                        //　@　@　@・"W指値"
                        //　@　@　@・"時価サーバ"
                        l_lisQueryContainers.add(WEB3OrderingConditionDef.W_LIMIT_PRICE);
                        l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
                    }
                }

                //２－２－３－２）　@作成した条件注文種別条件を検索条件文字列に追加する。
                l_lisQueryStrings.add("and (" + l_strTriggerOrderTypeCondtion + ")");
            }

            //２－２－４）　@パラメータ.顧客コード != nullの場合、
            //顧客条件を検索条件に追加する。
            //２－２－４－１）　@トリガー注文管理者データマネージャ.get顧客一覧()を
            //コールする。
            //[get顧客一覧()に指定する引数]
            //証券会社コード：　@パラメータ.証券会社.証券会社コード
            //部店コード：　@パラメータ.部店コード
            //顧客コード：　@パラメータ.顧客コード
            if (WEB3StringTypeUtility.isNotEmpty(l_strAccountCode))
            {
                WEB3GentradeMainAccount[] l_mainAccounts =
                    getAccountList(l_institution.getInstitutionCode(), l_strBranchCodes, l_strAccountCode);
                //２－２－４－２）　@２－２－４－１）の戻り値の要素数分、検索条件に"?"を、
                //データコンテナに、各要素の口座IDを追加する。
                //検索条件文字列 += " and account_id in (?, ?,,,) "
                StringBuffer l_sbMainAccountIdForQuery = new StringBuffer(" and account_id in (");
                int l_intLength = l_strBranchCodes.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    l_sbMainAccountIdForQuery.append("?,");
                    //データコンテナ（以下の順で追加する。）
                    //・２－２－４－１）の戻り値の各要素.口座ID
                    l_lisQueryContainers.add(String.valueOf(l_mainAccounts[i].getAccountId()));
                }
                l_lisQueryStrings.add(
                    l_sbMainAccountIdForQuery.substring(0, l_sbMainAccountIdForQuery.length() - 1) + ") ");
            }
        }
        //２－３）　@パラメータ.口座IDFrom != null　@かつ
        //パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を
        //検索条件に追加する。
        //※非同期処理を行う場合に設定される。
        if (l_accountIDFrom != null && l_accountIDTo != null)
        {
            //検索条件文字列 += " and account_id >= ?"
            //　@　@　@　@　@　@+ " and account_id <= ?"
            l_lisQueryStrings.add(" and account_id >= ? ");
            l_lisQueryStrings.add(" and account_id <= ? ");
            //データコンテナ（以下の順で追加する。）
            //・パラメータ.口座IDFrom
            //・パラメータ.口座IDTo
            l_lisQueryContainers.add(l_accountIDFrom);
            l_lisQueryContainers.add(l_accountIDTo);
        }

        //３）　@戻り値を格納するArrayListを生成する。
        ArrayList l_lisOrderUnits = new ArrayList();

        boolean l_blnIsEquity = false;
        boolean l_blnIsMargin = false;
        boolean l_blnIsFuture = false;
        boolean l_blnIsOption = false;
        int l_intLength = l_strCommodityDivs.length;
        for (int i = 0; i < l_intLength; i++)
        {
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDivs[i]))
            {
                l_blnIsEquity = true;
            }
            else if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDivs[i]))
            {
                l_blnIsMargin = true;
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDivs[i]))
            {
                l_blnIsFuture = true;
            }
            else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDivs[i]))
            {
                l_blnIsOption = true;
            }
        }

        int l_intCommonQueryString = l_lisQueryStrings.size();
        int l_intCommonContainer = l_lisQueryContainers.size();

        //４）　@パラメータ.商品区分一覧に"現物株式" or "信用取引"が含まれる場合、
        //以下の処理を行う。
        if (l_blnIsEquity || l_blnIsMargin)
        {
            //Eqtype検索条件文字列
            ArrayList l_lisEqtypeQueryStrings = new ArrayList();
            //４－１）　@ID直接指定時でない（パラメータ.注文ID == null）の場合、
            //Eqtype特有の検索条件を作成する。
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            if (l_orderID == null)
            {
                //４－１－１）　@パラメータ.注文ID == nullの場合、
                //[パラメータ.商品区分一覧に"現物株式"が含まれる場合]
                if (l_blnIsEquity)
                {
                    //Eqtype検索条件文字列 = " (order_type in (?, ?)"
                    //　@　@　@　@　@　@　@　@　@　@+ " and sonar_traded_code <> ?)"
                    l_lisEqtypeQueryStrings.add(" (order_type in (?, ?) ");
                    l_lisEqtypeQueryStrings.add(" and sonar_traded_code <> ?) ");
                    //Eqtypeデータコンテナ（以下の順で追加する。）
                    //・"現物買注文"
                    //・"現物売注文"
                    //・"立会外分売"
                    l_lisQueryContainers.add(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue()));
                    l_lisQueryContainers.add(String.valueOf(OrderTypeEnum.EQUITY_SELL.intValue()));
                    l_lisQueryContainers.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
                }
                //[パラメータ.商品区分一覧に"信用取引"が含まれる場合]
                if (l_blnIsMargin)
                {
                    //[Eqtype検索条件文字列が空文字の場合]
                    if (!l_blnIsEquity)
                    {
                        //  Eqtype検索条件文字列 = " (order_categ in (?, ?))"
                        l_lisEqtypeQueryStrings.add(" (order_categ in (?, ?)) ");
                    }
                    //[上記以外]
                    else
                    {
                        //　@Eqtype検索条件文字列 += " or order_categ in (?, ?)"
                        l_lisEqtypeQueryStrings.add(" or order_categ in (?, ?) ");
                    }
                    //Eqtypeデータコンテナ（以下の順で追加する。）
                    //・"信用新規建注文"
                    //・"信用返済注文"
                    l_lisQueryContainers.add(String.valueOf(OrderCategEnum.OPEN_MARGIN.intValue()));
                    l_lisQueryContainers.add(String.valueOf(OrderCategEnum.CLOSE_MARGIN.intValue()));
                }
                //４－１－２）　@Eqtype検索条件文字列に演算子を追加する。
                //Eqtype検索条件文字列 = " and (" + Eqtype検索条件文字列 + ")"
                StringBuffer l_sbEqtypeQueryString = new StringBuffer("");
                int l_intEqtypeQueryString = l_lisEqtypeQueryStrings.size();
                for (int j = 0; j < l_intEqtypeQueryString; j++)
                {
                    l_sbEqtypeQueryString.append(l_lisEqtypeQueryStrings.get(j));
                }
                l_lisQueryStrings.add(" and (" + l_sbEqtypeQueryString.toString() + ") ");

                //４－１－３）　@市場条件をEqtype検索条件文字列に追加する。
                //パラメータ.市場コード一覧の要素数分"?"を追加する。
                if (l_strMarketCodes != null && l_strMarketCodes.length != 0)
                {
                    //Eqtype検索条件文字列 = " and market_id in (?, ?,,,) "
                    StringBuffer l_sbMarketIdForQuery = new StringBuffer(" and market_id in (");
                    l_intLength = l_strMarketCodes.length;
                    try
                    {
                        WEB3GentradeFinObjectManager l_finObjManager =
                            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                        for (int i = 0; i < l_intLength; i++)
                        {
                            l_sbMarketIdForQuery.append("?,");
                            WEB3GentradeMarket l_market =
                                (WEB3GentradeMarket)l_finObjManager.getMarket(l_institution, l_strMarketCodes[i]);

                            //Eqtypeデータコンテナにパラメータ.市場コード一覧に
                            //該当する市場.市場IDを要素数分、addする。
                            l_lisQueryContainers.add(String.valueOf(l_market.getMarketId()));
                        }
                    }
                    //※取得時に例外となった場合、
                    //「条件に該当するデータが存在しない。」
                    //の業務エラーをスローする。
                    catch (NotFoundException l_nfe)
                    {
                        log.error("条件に該当するデータが存在しない。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                            "WEB3AdminToDataManager." + STR_METHOD_NAME,
                            "条件に該当するデータが存在しない。");
                    }
                    l_lisQueryStrings.add(l_sbMarketIdForQuery.substring(0, l_sbMarketIdForQuery.length() - 1) + ") ");
                }

                //４－１－４）　@パラメータ.銘柄コード != nullの場合、
                //銘柄条件をEqtype検索条件文字列に追加する。
                if (WEB3StringTypeUtility.isNotEmpty(l_strProductCode))
                {
                    WEB3EquityProductManager l_productManager =
                        (WEB3EquityProductManager) l_tradingModule.getProductManager();
                    try
                    {
                        //Eqtype検索条件文字列 = " and product_id = ?"
                        l_lisQueryStrings.add(" and product_id = ? ");
                        EqTypeProduct l_product = l_productManager.getProduct(l_institution, l_strProductCode);
                        //Eqtypeデータコンテナ（以下の順で追加する。）
                        //・パラメータ.銘柄コードに該当する株式銘柄.銘柄ID
                        l_lisQueryContainers.add(String.valueOf(l_product.getProductId()));
                    }
                    //※取得時に例外となった場合、
                    //「条件に該当するデータが存在しない。」
                    //の業務エラーをスローする。
                    catch (NotFoundException l_nfe)
                    {
                        log.error("条件に該当するデータが存在しない。");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                            "WEB3AdminToDataManager." + STR_METHOD_NAME,
                            "条件に該当するデータが存在しない。");
                    }
                }
            }

            StringBuffer l_sbQueryString = new StringBuffer("");
            int l_intQueryString = l_lisQueryStrings.size();
            for (int i = 0; i < l_intQueryString; i++)
            {
                l_sbQueryString.append(l_lisQueryStrings.get(i));
            }

            int l_intQueryContainer = l_lisQueryContainers.size();
            Object[] l_objContainers = new Object[l_intQueryContainer];
            l_lisQueryContainers.toArray(l_objContainers);

            //４－２）　@DBを検索する。
            //QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                //[doFindAllQuery()にセットするパラメータ]
                //arg0：　@株式注文単位Row.TYPE
                //arg1：　@作成した検索条件文字列
                //　@　@　@+ 作成したEqtype検索条件文字列
                //arg2：　@作成したデータコンテナ.toArray()と
                //　@　@　@　@作成したEqtypeデータコンテナ.toArray()の
                //　@　@　@　@戻り値を要素とする配列
                //　@　@　@  ※Eqtype検索条件／データコンテナを作成していない場合は、
                //　@　@　@　@検索条件に追加しない。
                List l_lisRows = l_queryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_objContainers);

                if (!l_lisRows.isEmpty())
                {
                    WEB3EquityOrderManager l_orderMgr =
                        (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

                    int l_intResultLength = l_lisRows.size();
                    for (int i = 0; i < l_intResultLength; i++)
                    {
                        l_lisOrderUnits.add(l_orderMgr.toOrderUnit((EqtypeOrderUnitRow)l_lisRows.get(i)));
                    }
                }
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DBアクセスエラー", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DBアクセスエラー", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
        }

        int l_intEqtypeQueryString = l_lisQueryStrings.size();
        int l_intEqtypeContainer = l_lisQueryContainers.size();

        for (int i = 0; i < l_intEqtypeContainer - l_intCommonContainer; i++)
        {
            l_lisQueryContainers.remove(l_intCommonContainer);
        }

        for (int i = 0; i < l_intEqtypeQueryString - l_intCommonQueryString; i++)
        {
            l_lisQueryStrings.remove(l_intCommonQueryString);
        }

        //５）　@パラメータ.商品区分一覧に"先物" or "オプション"が含まれる場合、
        //以下の処理を行う。
        if (l_blnIsFuture || l_blnIsOption)
        {
            //Ifo特有の検索条件
            ArrayList l_lisIfoQueryStrings = new ArrayList();
            //５－１）　@ID直接指定時でない（パラメータ.注文ID == null）の場合
            if (l_orderID == null)
            {
                //[パラメータ.商品区分一覧に"先物"が含まれる場合]
                if (l_blnIsFuture)
                {
                    //Ifo検索条件文字列 = " future_option_div = ?"
                    l_lisIfoQueryStrings.add(" future_option_div = ? ");
                    //Ifoデータコンテナ（以下の順で追加する。）
                    //・"先物"
                    l_lisQueryContainers.add(WEB3FuturesOptionDivDef.FUTURES);
                }
                //[パラメータ.商品区分一覧に"オプション"が含まれる場合]
                if (l_blnIsOption)
                {
                    //[Ifo検索条件文字列が空文字の場合]
                    if (!l_blnIsFuture)
                    {
                        //Ifo検索条件文字列 = " future_option_div = ?"
                        l_lisIfoQueryStrings.add(" future_option_div = ? ");
                    }
                    //[上記以外]
                    else
                    {
                        //Ifo検索条件文字列 += " or future_option_div = ?"
                        l_lisIfoQueryStrings.add(" or future_option_div = ? ");
                    }
                    //Ifoデータコンテナ（以下の順で追加する。）
                    //・"オプション"
                    l_lisQueryContainers.add(WEB3FuturesOptionDivDef.OPTION);
                }

                //５－１－１）　@Ifo検索条件文字列に演算子を追加する。
                StringBuffer l_sbIfoQueryString = new StringBuffer("");
                int l_intIfoQueryString = l_lisIfoQueryStrings.size();
                for (int j = 0; j < l_intIfoQueryString; j++)
                {
                    l_sbIfoQueryString.append(l_lisIfoQueryStrings.get(j));
                }
                //Ifo検索条件文字列 = " and (" + Ifo検索条件文字列 + ")"
                l_lisQueryStrings.add(" and (" + l_sbIfoQueryString.toString() + ") ");
            }

            StringBuffer l_sbQueryString = new StringBuffer("");
            int l_intQueryString = l_lisQueryStrings.size();
            for (int i = 0; i < l_intQueryString; i++)
            {
                l_sbQueryString.append(l_lisQueryStrings.get(i));
            }
            int l_intQueryContainer = l_lisQueryContainers.size();
            Object[] l_objContainer = new Object[l_intQueryContainer];
            l_lisQueryContainers.toArray(l_objContainer);

            //　@５－２）　@DBを検索する。
            //QueryProcessor.doFindAllQuery()メソッドをコールする。
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
                //[doFindAllQuery()にセットするパラメータ]
                //　@arg0：　@先物OP注文単位Row.TYPE
                //　@arg1：　@作成した検索条件文字列
                //　@　@　@　@　@+ 作成したIfo検索条件文字列
                //　@arg2：　@作成したデータコンテナ.toArray()と
                //　@　@　@　@　@作成したIfoデータコンテナ.toArray()の
                //　@　@　@　@　@戻り値を要素とする配列
                //　@※Ifo検索条件／データコンテナを作成していない場合は、
                //　@　@検索条件に追加しない。
                List l_lisRows = l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_objContainer);

                if (!l_lisRows.isEmpty())
                {
                    WEB3OptionOrderManagerImpl l_orderMgr =
                        (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
                    int l_intResultLength = l_lisRows.size();
                    for (int i = 0; i < l_intResultLength; i++)
                    {
                        l_lisOrderUnits.add(l_orderMgr.toOrderUnit((IfoOrderUnitRow)l_lisRows.get(i)));
                    }
                }
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DBアクセスエラー", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DBアクセスエラー", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminToDataManager." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
        }

        //２）　@検索結果を返却する。
        if (l_lisOrderUnits.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        OrderUnit[] l_orderUnits = new OrderUnit[l_lisOrderUnits.size()];
        l_lisOrderUnits.toArray(l_orderUnits);
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }

    /**
     * (get商品区分一覧)<BR>
     * 引数の部店コード一覧に該当する部店の<BR>
     * 商品実施状況をチェックし、実施している商品の一覧を返却する。<BR>
     * ※引数の部店コード一覧のうち、<BR>
     * 　@一部店でも実施していれば、実施となる。<BR>
     * <BR>
     * １）部店一覧の取得<BR>
     * 　@１－１）ArrayListを生成する。<BR>
     * 　@１－２）パラメータ.部店コード一覧の要素数分、<BR>
     * 　@　@拡張アカウントマネージャ.get部店()メソッドをコールし、<BR>
     * 　@　@結果をArrayListに追加する。<BR>
     * <BR>
     * 　@　@[get部店()にセットするパラメータ]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@部店コード：　@処理対象の部店コード<BR>
     * 　@１－３）ArrayList.toArray()メソッドをコールし、<BR>
     * 　@　@部店一覧(部店オブジェクトの配列)を取得する。<BR>
     * <BR>
     * ２）　@戻り値を格納するTreeMapを生成する。<BR>
     * <BR>
     * ３）　@現物株式を追加する。<BR>
     * 　@TreeMap.put("現物株式", "現物株式")メソッドをコールする。<BR>
     * <BR>
     * ４）１）にて取得した部店一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@４－１）処理対象の部店の各実施区分の値により、<BR>
     * 　@　@商品区分をTreeMapに追加する。<BR>
     * <BR>
     * 　@　@[処理対象の部店.制度信用実施区分 == "実施"の場合 or<BR>
     * 　@　@　@処理対象の部店.一般信用実施区分 == "実施"の場合]<BR>
     * 　@　@　@TreeMap.put("信用取引", "信用取引")メソッドをコールする。<BR>
     * 　@　@[処理対象の部店.先物実施区分 == "実施"の場合]<BR>
     * 　@　@　@TreeMap.put("先物", "先物")メソッドをコールする。<BR>
     * 　@　@[処理対象の部店.オプション実施区分 == "実施"の場合]<BR>
     * 　@　@　@TreeMap.put("オプション", "オプション")メソッドをコールする。<BR>
     * <BR>
     * ５）TreeMap.values().toArray()の戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCodeList - (部店コード一覧)<BR>
     * 部店コードの配列<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 44064F80019F
     */
    public static String[] getCommodityDivList(String l_strInstitutionCode, String[] l_strBranchCodeList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommodityDivList(String, String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodeList == null || l_strBranchCodeList.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }
        
        // １）部店一覧の取得
        // １－１）ArrayListを生成する。
        ArrayList l_lisBranchs = new ArrayList();
        int l_intLength = l_strBranchCodeList.length;
        
        // １－２）パラメータ.部店コード一覧の要素数分、
        //　@　@拡張アカウントマネージャ.get部店()メソッドをコールし、
        //　@　@結果をArrayListに追加する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();  
            
        try
        {
            for(int i = 0; i < l_intLength; i++)
            {
                Branch l_branch = l_accountManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCodeList[i]);
                l_lisBranchs.add(l_branch);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                "WEB3AdminToDataManager." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        // 　@１－３）ArrayList.toArray()メソッドをコールし、
        // 　@　@部店一覧(部店オブジェクトの配列)を取得する。
        Branch[] l_branchs = new Branch[l_lisBranchs.size()];
        l_lisBranchs.toArray(l_branchs);
        
        // ２）　@戻り値を格納するTreeMapを生成する。
        TreeMap l_map= new TreeMap();
        
        // ３）　@現物株式を追加する。
        // TreeMap.put("現物株式", "現物株式")メソッドをコールする。
        l_map.put(WEB3CommodityDivDef.EQUITY, WEB3CommodityDivDef.EQUITY);
        
        // ４）１）にて取得した部店一覧の要素数分、
        // 　@以下の処理を繰り返す。
        // 　@４－１）処理対象の部店の各実施区分の値により、
        // 　@　@商品区分をTreeMapに追加する。
        l_intLength = l_branchs.length;
        for(int i = 0; i < l_intLength; i++)
        {
            BranchRow l_branchRow =(BranchRow) l_branchs[i].getDataSourceObject();
            //　@[処理対象の部店.制度信用実施区分 == "実施"の場合 or
            //　@  処理対象の部店.一般信用実施区分 == "実施"の場合]
            // 　@ TreeMap.put("信用取引", "信用取引")メソッドをコールする。
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginSysDiv())
                || WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getMarginGenDiv()))
            {
                l_map.put(WEB3CommodityDivDef.MARGIN, WEB3CommodityDivDef.MARGIN);
            }
            
            //　@[処理対象の部店.先物実施区分 == "実施"の場合]
            //  　@TreeMap.put("先物", "先物")メソッドをコールする。
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getFutureDiv()))
            {
                l_map.put(WEB3CommodityDivDef.FUTURE, WEB3CommodityDivDef.FUTURE);
            }
            
            // 　@ [処理対象の部店.オプション実施区分 == "実施"の場合]
            //  TreeMap.put("オプション", "オプション")メソッドをコールする。
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getOptionDiv()))
            {
                l_map.put(WEB3CommodityDivDef.OPTION, WEB3CommodityDivDef.OPTION);
            }
        }
        
        // ５）TreeMap.values().toArray()の戻り値を返却する。
        String[] l_strProductList = new String[l_map.size()];
        l_map.values().toArray(l_strProductList);
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductList;
    }
    
    /**
    * (get特殊執行条件取扱停止一覧)<BR>
    * 条件に該当する特殊執行条件取扱停止テーブル<BR>
    * のレコードを返却する。<BR>
    * <BR>
    * １）　@検索条件の決定<BR>
    * 　@パラメータ.処理区分により、検索方法@を分岐する。<BR>
    * 　@１－１）　@パラメータ.処理区分 == "商品"の場合<BR>
    * 　@　@検索条件は以下の通りとする。<BR>
    * 　@　@【検索条件】<BR>
    * 　@　@　@証券会社コード = パラメータ.証券会社コード<BR>
    * 　@　@　@And 部店コード IN (パラメータ.部店コードの全要素)<BR>
    * 　@　@　@And 設定対象種別 = "商品"<BR>
    * 　@　@　@And キー情報 IN (証券会社が実施している商品の商品区分一覧(*1))<BR>
    * 　@　@　@And 削除フラグ = "DEFAULT"<BR>
    * <BR>
    * 　@　@【ソート条件】<BR>
    * 　@　@　@キー情報 昇順, 部店コード 昇順<BR>
    * <BR>
    * 　@１－２）　@パラメータ.処理区分 == "市場"の場合<BR>
    * 　@　@検索条件は以下の通りとする。<BR>
    * 　@　@【検索条件】<BR>
    * 　@　@　@証券会社コード = パラメータ.証券会社コード<BR>
    * 　@　@　@And 設定対象種別 = "市場"<BR>
    * 　@　@　@And キー情報 IN (証券会社が取扱可能な市場コード一覧(*2))<BR>
    * 　@　@　@And 削除フラグ = "DEFAULT"<BR>
    * <BR>
    * 　@　@【ソート条件】<BR>
    * 　@　@　@to_number(キー情報) 昇順<BR>
    * <BR>
    * 　@１－３）　@パラメータ.処理区分 == "銘柄"の場合<BR>
    * 　@　@検索条件は以下の通りとする。<BR>
    * 　@　@【検索条件】<BR>
    * 　@　@　@証券会社コード = パラメータ.証券会社コード<BR>
    * 　@　@　@And 設定対象種別 = "銘柄"<BR>
    * 　@　@　@And キー情報 = パラメータ.銘柄コード　@※nullの場合は条件に含めない。<BR>
    * 　@　@　@And 有効期限To >= 現在時刻のYYYYMMDD<BR>
    * 　@　@　@And 削除フラグ = "DEFAULT"<BR>
    * <BR>
    * 　@　@【ソート条件】<BR>
    * 　@　@　@[パラメータ.ソートキー == nullの場合]<BR>
    * 　@　@　@　@ソート条件なし。<BR>
    * 　@　@　@[上記以外]<BR>
    * 　@　@　@　@以下の分岐を、パラメータ.ソートキーの要素数分Loopし、<BR>
    * 　@　@　@　@ソート条件を作成する。<BR>
    * 　@　@　@　@<ソート項目><BR>
    * 　@　@　@　@　@処理対象の要素.キー項目が、<BR>
    * 　@　@　@　@　@["銘柄コード"の場合]<BR>
    * 　@　@　@　@　@　@特殊執行条件取扱停止テーブル.キー情報<BR>
    * 　@　@　@　@　@["有効期限From"の場合]<BR>
    * 　@　@　@　@　@　@特殊執行条件取扱停止テーブル.有効期限From<BR>
    * <BR>
    * 　@　@　@　@<昇順／降順><BR>
    * 　@　@　@　@　@処理対象の要素.昇順／降順に対応するソート文字列（ASC or DESC）<BR>
    * <BR>
    * ２）　@DB検索<BR>
    * 　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
    * <BR>
    * 　@　@[doFindAllQuery()にセットするパラメータ]<BR>
    * 　@　@　@arg0：　@特殊執行条件取扱停止Row.TYPE<BR>
    * 　@　@　@arg1：　@決定した検索条件をSQL化した値<BR>
    * 　@　@　@arg2：　@決定した検索条件の検索値の配列（：String[]）<BR>
    * 　@　@　@arg3：　@null <BR>
    * 　@　@　@arg4：　@決定したソート条件をSQL化した値<BR>
    * <BR>
    * ３）　@検索結果を返却する。<BR>
    * <BR>
    * (*1)トリガー注文管理者データマネージャ.get商品区分一覧()にて<BR>
    * 　@取得する。<BR>
    * <BR>
    * 　@[get商品区分一覧()にセットするパラメータ]<BR>
    * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
    * 　@　@部店コード一覧：　@パラメータ.部店コード<BR>
    * <BR>
    * (*2)（部店市場別）取扱情報.get取扱可能市場()にて取得する。<BR>
    * <BR>
    * 　@[get取扱可能市場()にセットするパラメータ]<BR>
    * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
    * 　@　@銘柄タイプ：　@"株式"<BR>
    * @@param l_strInstitutionCode - (証券会社コード)<BR>
    * 証券会社コード<BR>
    * @@param l_strBranchCodes - (部店コード)<BR>
    * 部店コードの配列<BR>
    * @@param l_strTradeStopDiv - (処理区分)<BR>
    * 処理区分<BR>
    * @@param l_strProductCode - (銘柄コード)<BR>
    * 銘柄コード<BR>
    * @@param l_tradeStopSortKeys - (ソートキー)<BR>
    * ソートキー<BR>
    * @@return List
    * @@throws WEB3BaseException
    */
    public static List getTriggerOrderStopList(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strTradeStopDiv, 
        String l_strProductCode,
        WEB3AdminToTradeStopSortKey[] l_tradeStopSortKeys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getTriggerOrderStopList(String, String[], String, String, " +
            "WEB3AdminToTradeStopSortKey[])";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lisQueryStrings = new ArrayList();
        ArrayList l_lisQueryContainers = new ArrayList();
        String l_strSortCondition = null;
        
        //１）　@検索条件の決定
        //パラメータ.処理区分により、検索方法@を分岐する。
        //１－１）　@パラメータ.処理区分 == "商品"の場合
        //検索条件は以下の通りとする。
        if (WEB3TargetTypeDef.COMMODITY.equals(l_strTradeStopDiv))
        {
            //【検索条件】
            //証券会社コード = パラメータ.証券会社コード
            l_lisQueryStrings.add(" institution_code = ? ");
            l_lisQueryContainers.add(l_strInstitutionCode);
            
            //And 部店コード IN (パラメータ.部店コードの全要素)
            l_lisQueryStrings.add(" and branch_code in ( ");
            int l_intLen = l_strBranchCodes.length;
            int i = 0;
            for (; i < l_intLen - 1; i++)
            {
                l_lisQueryStrings.add(" ?, ");
                l_lisQueryContainers.add(l_strBranchCodes[i]);
            }
            l_lisQueryStrings.add(" ?) ");
            l_lisQueryContainers.add(l_strBranchCodes[i]);
            
            //And 設定対象種別 = "商品"
            l_lisQueryStrings.add(" and target_type = ? ");
            l_lisQueryContainers.add(WEB3TargetTypeDef.COMMODITY);
            
            //And キー情報 IN (証券会社が実施している商品の商品区分一覧(*1))
            l_lisQueryStrings.add(" and key in ( ");
            String[] l_strKeys = getCommodityDivList(l_strInstitutionCode, l_strBranchCodes);
            l_intLen = l_strKeys.length;
            for (i = 0; i < l_intLen - 1; i++)
            {
                l_lisQueryStrings.add(" ?, ");
                l_lisQueryContainers.add(l_strKeys[i]);
            }
            l_lisQueryStrings.add(" ?) ");
            l_lisQueryContainers.add(l_strKeys[i]);
            
            //And 削除フラグ = "DEFAULT"
            l_lisQueryStrings.add(" and delete_flag = ? ");
            l_lisQueryContainers.add(BooleanEnum.FALSE);
            
            //【ソート条件】
            //キー情報 昇順, 部店コード 昇順
            l_strSortCondition = " key asc, branch_code asc ";
        }
        //１－２）　@パラメータ.処理区分 == "市場"の場合
        //検索条件は以下の通りとする。
        else if (WEB3TargetTypeDef.MARKET.equals(l_strTradeStopDiv))
        {
            //【検索条件】
            //証券会社コード = パラメータ.証券会社コード
            l_lisQueryStrings.add(" institution_code = ? ");
            l_lisQueryContainers.add(l_strInstitutionCode);
            
            //And 設定対象種別 = "市場"
            l_lisQueryStrings.add(" and target_type = ? ");
            l_lisQueryContainers.add(WEB3TargetTypeDef.MARKET);
            
            //And キー情報 IN (証券会社が取扱可能な市場コード一覧(*2))
            l_lisQueryStrings.add(" and key in ( ");
            String[] l_strKeys = WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode, 
                ProductTypeEnum.EQUITY);
            int l_intLen = l_strKeys.length;
            int i = 0;
            for (; i < l_intLen - 1; i++)
            {
                l_lisQueryStrings.add(" ?, ");
                l_lisQueryContainers.add(l_strKeys[i]);
            }
            l_lisQueryStrings.add(" ?) ");
            l_lisQueryContainers.add(l_strKeys[i]);
            
            //And 削除フラグ = "DEFAULT"
            l_lisQueryStrings.add(" and delete_flag = ? ");
            l_lisQueryContainers.add(BooleanEnum.FALSE);
            
            //【ソート条件】
            //to_number(キー情報) 昇順
            l_strSortCondition = " to_number(key) asc ";
        }
        //１－３）　@パラメータ.処理区分 == "銘柄"の場合
        //検索条件は以下の通りとする。
        else if (WEB3TargetTypeDef.PRODUCT.equals(l_strTradeStopDiv))
        {
            //【検索条件】
            //証券会社コード = パラメータ.証券会社コード
            l_lisQueryStrings.add(" institution_code = ? ");
            l_lisQueryContainers.add(l_strInstitutionCode);
            
            //And 設定対象種別 = "銘柄"
            l_lisQueryStrings.add(" and target_type = ? ");
            l_lisQueryContainers.add(WEB3TargetTypeDef.PRODUCT);
            
            //And キー情報 = パラメータ.銘柄コード　@※nullの場合は条件に含めない。
            if (WEB3StringTypeUtility.isNotEmpty(l_strProductCode))
            {
                l_lisQueryStrings.add(" and key = ? ");
                l_lisQueryContainers.add(l_strProductCode);
            }
            
            //And 有効期限To >= 現在時刻のYYYYMMDD
            l_lisQueryStrings.add(" and valid_term_to >= ? ");
            l_lisQueryContainers.add(
                WEB3DateUtility.formatDate(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()), "yyyyMMdd"));
            
            //And 削除フラグ = "DEFAULT"
            l_lisQueryStrings.add(" and delete_flag = ? ");
            l_lisQueryContainers.add(BooleanEnum.FALSE);
            
            //【ソート条件】
            //[パラメータ.ソートキー == nullの場合]
            //ソート条件なし。
            if (l_tradeStopSortKeys != null && l_tradeStopSortKeys.length != 0)
            {
                //以下の分岐を、パラメータ.ソートキーの要素数分Loopし、
                //ソート条件を作成する。
                //<ソート項目>
                //処理対象の要素.キー項目が、
                //["銘柄コード"の場合]
                //特殊執行条件取扱停止テーブル.キー情報
                //["有効期限From"の場合]
                //特殊執行条件取扱停止テーブル.有効期限From
                int l_intLen = l_tradeStopSortKeys.length;
                for (int i = 0; i < l_intLen; i++)
                {
                    if (WEB3AdminToTradeStopKeyItemDef.PRODUCT_CODE.equals(l_tradeStopSortKeys[i].keyItem))
                    {
                        if (WEB3StringTypeUtility.isNotEmpty(l_strSortCondition))
                        {
                            l_strSortCondition += " , key ";
                        }
                        else
                        {
                            l_strSortCondition = " key ";
                        }
                        
                        if (WEB3AscDescDef.ASC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " asc ";
                        }
                        else if (WEB3AscDescDef.DESC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " desc ";
                        }
                    }
                    else if (WEB3AdminToTradeStopKeyItemDef.EXPIRATION_START_DATE.equals(
                        l_tradeStopSortKeys[i].keyItem))
                    {
                        if (WEB3StringTypeUtility.isNotEmpty(l_strSortCondition))
                        {
                            l_strSortCondition += " , valid_term_from ";
                        }
                        else
                        {
                            l_strSortCondition = " valid_term_from ";
                        }
                        
                        if (WEB3AscDescDef.ASC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " asc ";
                        }
                        else if (WEB3AscDescDef.DESC.equals(l_tradeStopSortKeys[i].ascDesc))
                        {
                            l_strSortCondition += " desc ";
                        }
                    }
                }
            }
        }
        
        //２）　@DB検索
        StringBuffer l_sbQueryString = new StringBuffer("");
        int l_intQueryString = l_lisQueryStrings.size();
        for (int i = 0; i < l_intQueryString; i++)
        {
            l_sbQueryString.append(l_lisQueryStrings.get(i));
        }
        
        Object[] l_objContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_objContainers);
        List l_lisRows = null;
        try
        {
            //QueryProcessor.doFindAllQuery()メソッドをコールする。
            //[doFindAllQuery()にセットするパラメータ]
            //　@arg0：　@特殊執行条件取扱停止Row.TYPE
            //　@arg1：　@決定した検索条件をSQL化した値
            //　@arg2：　@決定したソート条件をSQL化した値
            //　@arg3：　@null
            //　@arg4：　@決定した検索条件の検索値の配列（：String[]）
            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                TriggerOrderStopRow.TYPE, 
                l_sbQueryString.toString(), 
                l_strSortCondition,
                null,
                l_objContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRows;
    }
    
    /**
     * (create取扱停止情報一覧)<BR>
     * 引数より取扱停止情報の一覧を作成する。<BR>
     * <BR>
     * １）　@戻り値を格納するArrayListを生成する。<BR>
     * <BR>
     * ２）　@パラメータ.特殊執行条件取扱停止一覧の<BR>
     * 　@要素数分、以下の処理をLoopする。<BR>
     * 　@２－１）　@取扱停止情報を生成する。<BR>
     * <BR>
     * 　@２－２）　@生成した取扱停止情報に<BR>
     * 　@　@以下のプロパティをセットする。<BR>
     * <BR>
     * 　@　@ID = 処理対象の要素.特殊執行条件取扱停止ID<BR>
     * 　@　@部店コード = 処理対象の要素.部店コード<BR>
     * 　@　@商品区分 =<BR>
     * 　@　@　@処理対象の要素.設定対象種別 == "商品"の場合、<BR>
     * 　@　@　@処理対象の要素.キー情報<BR>
     * 　@　@市場コード =<BR>
     * 　@　@　@処理対象の要素.設定対象種別 == "市場"の場合、<BR>
     * 　@　@　@処理対象の要素.キー情報<BR>
     * 　@　@店頭公開区分 =<BR>
     * 　@　@　@処理対象の要素.設定対象種別 == "市場"の場合 かつ <BR>
     * 　@　@　@処理対象の要素.キー情報 == "JASDAQ"の場合、<BR>
     * 　@　@　@処理対象の要素.追加情報<BR>
     * 　@　@銘柄コード =<BR>
     * 　@　@　@処理対象の要素.設定対象種別 == "銘柄"の場合、<BR>
     * 　@　@　@処理対象の要素.キー情報<BR>
     * 　@　@銘柄名 =<BR>
     * 　@　@　@処理対象の要素.設定対象種別 == "銘柄"の場合、<BR>
     * 　@　@　@株式銘柄(*1).getDataSourceObject().銘柄名<BR>
     * 　@　@停止理由 = 処理対象の要素.停止理由<BR>
     * 　@　@有効期限From = 処理対象の要素.有効期限From<BR>
     * 　@　@有効期限To = 処理対象の要素.有効期限To<BR>
     * <BR>
     * 　@２－３）　@注文停止状況を格納するArrayListを生成する。<BR>
     * <BR>
     * 　@２－４）　@処理対象の条件注文種別(*2)の要素数分、<BR>
     * 　@　@以下の処理をLoopする。<BR>
     * 　@　@２－４－１）　@注文停止状況を生成する。<BR>
     * <BR>
     * 　@　@２－４－２）　@注文停止状況に以下のプロパティをセットする。<BR>
     * 　@　@　@処理対象の要素が、<BR>
     * 　@　@　@["連続注文"の場合]<BR>
     * 　@　@　@　@条件注文種別 = 処理対象の要素<BR>
     * 　@　@　@　@停止フラグ =<BR>
     * 　@　@　@　@　@処理対象の特殊執行条件取扱停止Row.連続注文停止フラグ<BR>
     * 　@　@　@["OCO注文"の場合]<BR>
     * 　@　@　@　@条件注文種別 = 処理対象の要素<BR>
     * 　@　@　@　@停止フラグ =<BR>
     * 　@　@　@　@　@処理対象の特殊執行条件取扱停止Row.OCO注文停止フラグ<BR>
     * 　@　@　@["IFD注文"の場合]<BR>
     * 　@　@　@　@条件注文種別 = 処理対象の要素<BR>
     * 　@　@　@　@停止フラグ =<BR>
     * 　@　@　@　@　@処理対象の特殊執行条件取扱停止Row.IFD注文停止フラグ<BR>
     * 　@　@　@["逆指値注文"の場合]<BR>
     * 　@　@　@　@条件注文種別 = 処理対象の要素<BR>
     * 　@　@　@　@停止フラグ =<BR>
     * 　@　@　@　@　@処理対象の特殊執行条件取扱停止Row.逆指値注文停止フラグ<BR>
     * 　@　@　@["W指値注文"の場合]<BR>
     * 　@　@　@　@条件注文種別 = 処理対象の要素<BR>
     * 　@　@　@　@停止フラグ =<BR>
     * 　@　@　@　@　@処理対象の特殊執行条件取扱停止Row.W指値注文停止フラグ<BR>
     * <BR>
     * 　@　@２－４－３）　@プロパティセットした注文停止状況を<BR>
     * 　@　@　@ArrayListに追加する。<BR>
     * <BR>
     * 　@２－５）　@取扱停止情報に注文停止状況をセットする。<BR>
     * 　@　@取扱停止情報.注文停止状況一覧 =<BR>
     * 　@　@　@注文停止状況を格納するArrayList.toArray()<BR>
     * <BR>
     * 　@２－６）　@戻り値を格納するArrayListにプロパティセットした<BR>
     * 　@　@取扱停止情報を追加する。<BR>
     * <BR>
     * ３）　@戻り値を格納するArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)株式銘柄<BR>
     * 　@拡張プロダクトマネージャ.getProduct(証券会社,処理対象の要素.キー情報)<BR>
     * <BR>
     * (*2)処理対象の条件注文種別<BR>
     * 　@以下の条件注文種別を処理対象とする。<BR>
     * 　@　@①@"連続注文"<BR>
     * 　@　@②"OCO注文"<BR>
     * 　@　@③"IFD注文"<BR>
     * 　@　@④"逆指値注文"<BR>
     * 　@　@⑤"W指値"<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_triggerOrderStopList - (特殊執行条件取扱停止一覧)<BR>
     * 特殊執行条件取扱停止Rowの配列<BR>
     * @@return WEB3AdminToTradeStopInfoUnit[]
     * @@throws WEB3BaseException
     */
    public static WEB3AdminToTradeStopInfoUnit[] createTradeStopInfoList(
        Institution l_institution, 
        TriggerOrderStopRow[] l_triggerOrderStopList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createTradeStopInfoList(Institution, TriggerOrderStopRow[])";
        log.entering(STR_METHOD_NAME);
        
        //１）　@戻り値を格納するArrayListを生成する。
        ArrayList l_lisReturns = new ArrayList();
        
        //２）　@パラメータ.特殊執行条件取扱停止一覧の
        //要素数分、以下の処理をLoopする。
        int l_intLen = l_triggerOrderStopList.length;
        for (int i = 0; i < l_intLen; i++)
        {
            //２－１）　@取扱停止情報を生成する。
            WEB3AdminToTradeStopInfoUnit l_tradeStopInfoUnit = new WEB3AdminToTradeStopInfoUnit();
            
            //２－２）　@生成した取扱停止情報に
            //以下のプロパティをセットする。
            //ID = 処理対象の要素.特殊執行条件取扱停止ID
            l_tradeStopInfoUnit.id = String.valueOf(l_triggerOrderStopList[i].getTriggerOrderStopId());
            
            //部店コード = 処理対象の要素.部店コード
            l_tradeStopInfoUnit.branchCode = l_triggerOrderStopList[i].getBranchCode();
            
            //商品区分 =
            //　@処理対象の要素.設定対象種別 == "商品"の場合、
            //　@処理対象の要素.キー情報
            String l_strTargetType = l_triggerOrderStopList[i].getTargetType();
            String l_strKey = l_triggerOrderStopList[i].getKey();
            if (WEB3TargetTypeDef.COMMODITY.equals(l_strTargetType))
            {
                l_tradeStopInfoUnit.productDiv = l_strKey;
            }
            //市場コード =
            //　@処理対象の要素.設定対象種別 == "市場"の場合、
            //　@処理対象の要素.キー情報
            else if (WEB3TargetTypeDef.MARKET.equals(l_strTargetType))
            {
                l_tradeStopInfoUnit.marketCode = l_strKey;
                
                //店頭公開区分 =
                //　@処理対象の要素.キー情報 == "JASDAQ"の場合、
                //　@処理対象の要素.追加情報
                if (WEB3MarketCodeDef.JASDAQ.equals(l_strKey))
                {
                    l_tradeStopInfoUnit.otcOpenDiv = l_triggerOrderStopList[i].getAddition();
                }
            }
            //銘柄コード =
            //　@処理対象の要素.設定対象種別 == "銘柄"の場合、
            //　@処理対象の要素.キー情報
            //銘柄名 =
            //　@処理対象の要素.設定対象種別 == "銘柄"の場合、
            //　@株式銘柄(*1).getDataSourceObject().銘柄名
            else if (WEB3TargetTypeDef.PRODUCT.equals(l_strTargetType))
            {
                l_tradeStopInfoUnit.productCode = l_strKey;
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                WEB3EquityProductManager l_productManager = (WEB3EquityProductManager) 
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                try
                {
                    Product l_product = 
                        l_productManager.getProduct(l_institution, l_strKey);
                    EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow) l_product.getDataSourceObject();
                    l_tradeStopInfoUnit.productName = l_eqtypeProductRow.getStandardName();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("条件に該当するデータが存在しない。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                        "WEB3AdminToDataManager." + STR_METHOD_NAME,
                        "条件に該当するデータが存在しない。");
                }
            }
            
            //停止理由 = 処理対象の要素.停止理由
            l_tradeStopInfoUnit.stopReason = l_triggerOrderStopList[i].getStopReason();
            
            //有効期限From = 処理対象の要素.有効期限From
            l_tradeStopInfoUnit.expirationStartDate = 
                WEB3DateUtility.formatDate(l_triggerOrderStopList[i].getValidTermFrom(), "yyyyMMdd");
            
            //有効期限To = 処理対象の要素.有効期限To
            l_tradeStopInfoUnit.expirationEndDate = 
                WEB3DateUtility.formatDate(l_triggerOrderStopList[i].getValidTermTo(), "yyyyMMdd");
            
            //２－３）　@注文停止状況を格納するArrayListを生成する。
            ArrayList l_lisUnits = new ArrayList();
            
            //２－４）　@処理対象の条件注文種別(*2)の要素数分、
            //以下の処理をLoopする。
            //２－４－１）　@注文停止状況を生成する。
            WEB3AdminToOrderStopStateUnit l_succUnit = new WEB3AdminToOrderStopStateUnit();
            
            //２－４－２）　@注文停止状況に以下のプロパティをセットする。
            //処理対象の要素が、
            //["連続注文"の場合]
            //　@条件注文種別 = 処理対象の要素
            //　@停止フラグ =
            //　@　@処理対象の特殊執行条件取扱停止Row.連続注文停止フラグ
            TriggerOrderStopParams l_params = new TriggerOrderStopParams(l_triggerOrderStopList[i]);
            l_succUnit.triggerOrderType = WEB3TriggerOrderTypeDef.SUCC;
            l_succUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getSuccOrderStopFlag();

            //ArrayListに追加する。
            l_lisUnits.add(l_succUnit);
            
            //注文停止状況を生成する。
            WEB3AdminToOrderStopStateUnit l_ocoUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["OCO注文"の場合]
            //　@条件注文種別 = 処理対象の要素
            //　@停止フラグ =
            //　@　@処理対象の特殊執行条件取扱停止Row.OCO注文停止フラグ
            l_ocoUnit.triggerOrderType = WEB3TriggerOrderTypeDef.OCO;
            l_ocoUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getOcoOrderStopFlag();
            
            //ArrayListに追加する。
            l_lisUnits.add(l_ocoUnit);
            
            //注文停止状況を生成する。
            WEB3AdminToOrderStopStateUnit l_ifdUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["IFD注文"の場合]
            //　@条件注文種別 = 処理対象の要素
            //　@停止フラグ =
            //　@　@処理対象の特殊執行条件取扱停止Row.IFD注文停止フラグ
            l_ifdUnit.triggerOrderType = WEB3TriggerOrderTypeDef.IFD;
            l_ifdUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getIfdOrderStopFlag();
            
            //ArrayListに追加する。
            l_lisUnits.add(l_ifdUnit);
            
            //注文停止状況を生成する。
            WEB3AdminToOrderStopStateUnit l_stopUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["逆指値注文"の場合]
            //　@条件注文種別 = 処理対象の要素
            //　@停止フラグ =
            //　@　@処理対象の特殊執行条件取扱停止Row.逆指値注文停止フラグ
            l_stopUnit.triggerOrderType = WEB3TriggerOrderTypeDef.STOP;
            l_stopUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getStopOrderStopFlag();
            
            //ArrayListに追加する。
            l_lisUnits.add(l_stopUnit);
            
            //注文停止状況を生成する。
            WEB3AdminToOrderStopStateUnit l_wlimitUnit = new WEB3AdminToOrderStopStateUnit();
            
            //["W指値注文"の場合]
            //　@条件注文種別 = 処理対象の要素
            //　@停止フラグ =
            //　@　@処理対象の特殊執行条件取扱停止Row.W指値注文停止フラグ
            l_wlimitUnit.triggerOrderType = WEB3TriggerOrderTypeDef.W_LlIMIT;
            l_wlimitUnit.stopFlag = BooleanEnum.FALSE.intValue() != l_params.getWlimitOrderStopFlag();

            //ArrayListに追加する。
            l_lisUnits.add(l_wlimitUnit);
            
            //２－５）　@取扱停止情報に注文停止状況をセットする。
            //　@取扱停止情報.注文停止状況一覧 =
            //　@　@注文停止状況を格納するArrayList.toArray()
            l_tradeStopInfoUnit.orderStopStateList = new WEB3AdminToOrderStopStateUnit[l_lisUnits.size()];
            l_lisUnits.toArray(l_tradeStopInfoUnit.orderStopStateList);
            
            //２－６）　@戻り値を格納するArrayListにプロパティセットした
            //　@取扱停止情報を追加する。
            l_lisReturns.add(l_tradeStopInfoUnit);
        }
        
        //３）　@戻り値を格納するArrayList.toArray()の戻り値を返却する。
        WEB3AdminToTradeStopInfoUnit[] l_trderStopStateUnits = new WEB3AdminToTradeStopInfoUnit[l_lisReturns.size()];
        l_lisReturns.toArray(l_trderStopStateUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_trderStopStateUnits;
    }

    /**
     * (create共通検索条件文字列（発注状況区分）)<BR>
     * 発注状況区分の検索条件文字列を作成する。<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータ.発注状況区分＝nullの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@検索条件文字列インスタンス(：String)を生成する。 <BR>
     * <BR>
     * ３）　@パラメータ.リクエストデータ.条件注文種別＝"逆指値"の場合<BR>
     * 　@ <BR>
     * 　@３－１）　@以下の条件を検索条件文字列に追加する。<BR>
     *  　@　@ [パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合]<BR>
     * 　@　@　@検索条件文字列 += "and submit_order_delay_flag = ? " <BR>
     * <BR>
     * 　@　@　@[パラメータ.リクエストデータ.発注状況区分＝("発注中", "発注完了")の場合]<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and order_condition_type = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and request_type = ? "<BR>
     *   　@　@[上記以外]<BR>
     * 　@　@　@検索条件文字列 += "and request_type = ? "  <BR>
     * <BR>
     * ４）　@パラメータ.リクエストデータ.条件注文種別＝"W指値"の場合<BR>
     * <BR>
     * 　@４－１）　@以下の条件を検索条件文字列に追加する。<BR>
     * 　@　@　@[パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合] <BR>
     * 　@　@　@検索条件文字列 += "and submit_order_delay_flag = ? " <BR>
     * 　@　@　@[上記以外]<BR>
     * 　@　@　@検索条件文字列 += "and request_type = ? "<BR>
     * <BR>
     * ５）　@作成した検索条件文字列を返却する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・注文照会共通リクエストオブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String createCommonQueryStringForTriggerOrderState(
        WEB3AdminToOrderRefRefCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createCommonQueryStringForTriggerOrderState(WEB3AdminToOrderRefRefCommonRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager" + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@パラメータ.リクエストデータ.発注状況区分＝nullの場合、nullを返却する。
        String l_strTriggerOrderState = l_request.triggerOrderState;
        if (l_strTriggerOrderState == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@検索条件文字列インスタンス(：String)を生成する。
        String l_strWhere = new String();

        //３）　@パラメータ.リクエストデータ.条件注文種別＝"逆指値"の場合
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_request.triggerOrderType))
        {
            //　@３－１）　@以下の条件を検索条件文字列に追加する。
            //　@[パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合]
            //　@　@検索条件文字列 += "and submit_order_delay_flag = ? "
            if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_strWhere += "and submit_order_delay_flag = ? ";
            }
            //パラメータ.リクエストデータ.発注状況区分＝("発注中", "発注完了")の場合のみ、
            //検索条件文字列 += "and order_condition_type = ? " + "and request_type = ? "
            else if (WEB3TriggerOrderStatusDef.ORDERING.equals(l_strTriggerOrderState)
                || WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(l_strTriggerOrderState))
            {
                l_strWhere += "and order_condition_type = ? "
                    + "and request_type = ? ";
            }
            //　@[上記以外]
            //　@　@検索条件文字列 += "and request_type = ? "
            else
            {
                l_strWhere += "and request_type = ? ";
            }
        }

        //４）　@パラメータ.リクエストデータ.条件注文種別＝"W指値"の場合
        else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_request.triggerOrderType))
        {
            //　@４－１）　@以下の条件を検索条件文字列に追加する。
            //[パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合]
            //検索条件文字列 += "and submit_order_delay_flag = ? "
            //[上記以外]
            //検索条件文字列 += "and request_type = ? "
            if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_strWhere += "and submit_order_delay_flag = ? ";
            }
            else
            {
                l_strWhere += "and request_type = ? ";
            }
        }

        //５）　@作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strWhere;
    }

    /**
     * (create共通検索条件データコンテナ（発注状況区分）)<BR>
     * 発注状況区分の検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータ.発注状況区分＝nullの場合、nullを返却する。<BR>
     * <BR>
     * ２）　@ArrayListを生成する。  <BR>
     * <BR>
     * ３）　@パラメータ.リクエストデータ.条件注文種別＝"逆指値"の場合、<BR>
     * 　@以下の条件を生成したArrayListに追加する。  <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"待機@中"の場合] <BR>
     * 　@　@・"DEFAULT"（リクエストタイプ） <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝（"発注中"）の場合]<BR>
     * 　@　@・"逆指値"（発注条件） <BR>
     * 　@　@・"時価サーバ"（リクエストタイプ）<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝（"発注完了"）の場合]<BR>
     * 　@　@・"DEFAULT"（発注条件）<BR>
     * 　@　@・"時価サーバ"（リクエストタイプ）<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"発注審査エラー"の場合] <BR>
     * 　@　@・"発注失敗"（リクエストタイプ） <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合]<BR>
     *   　@・"遅延あり"（発注遅延フラグ）
     * <BR>
     * ４）　@パラメータ.リクエストデータ.条件注文種別＝"W指値"の場合、<BR>
     * 　@以下の条件を生成したArrayListに追加する。  <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"待機@中"の場合] <BR>
     * 　@　@・"DEFAULT"（リクエストタイプ） <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"発注中"の場合]<BR>
     * 　@　@・"時価サーバ"（リクエストタイプ） <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"発注完了"の場合] <BR>
     * 　@　@・"切替完了"（リクエストタイプ） <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合]<BR>
     * 　@　@・"遅延あり"（発注遅延フラグ）
     * <BR>
     * 　@[パラメータ.リクエストデータ.発注状況区分＝"ストップ注文失効"の場合]<BR>
     * 　@　@・"失効"（リクエストタイプ） <BR>
     * <BR>
     * ５）　@生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・注文照会共通リクエストオブジェクト<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] createCommonQueryDataContainerForTriggerOrderState(
        WEB3AdminToOrderRefRefCommonRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createCommonQueryDataContainerForTriggerOrderState(WEB3AdminToOrderRefRefCommonRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminToDataManager" + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@パラメータ.リクエストデータ.発注状況区分＝nullの場合、nullを返却する。
        String l_strTriggerOrderState = l_request.triggerOrderState;
        if (l_strTriggerOrderState == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@ArrayListを生成する。
        List l_lisQueryContainers = new ArrayList();

        //３）　@パラメータ.リクエストデータ.条件注文種別＝"逆指値"の場合、
        if (WEB3TriggerOrderTypeDef.STOP.equals(l_request.triggerOrderType))
        {
            //[パラメータ.リクエストデータ.発注状況区分＝"待機@中"の場合]
            //　@・"DEFAULT"（リクエストタイプ）
            if (WEB3TriggerOrderStatusDef.ORDER_WAITING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.DEFAULT);
            }

            //　@[パラメータ.リクエストデータ.発注状況区分＝（"発注中"）の場合]
            //　@　@・"逆指値"（発注条件）
            //　@　@・"時価サーバ"（リクエストタイプ）
            else if (WEB3TriggerOrderStatusDef.ORDERING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
                l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
            }

            //[パラメータ.リクエストデータ.発注状況区分＝（"発注完了"）の場合]
            //　@　@・"DEFAULT"（発注条件）
            //　@　@・"時価サーバ"（リクエストタイプ）
            else if (WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3OrderingConditionDef.DEFAULT);
                l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
            }

            //　@[パラメータ.リクエストデータ.発注状況区分＝"発注審査エラー"の場合]
            //　@　@・"発注失敗"（リクエストタイプ）
            else if (WEB3TriggerOrderStatusDef.ORDER_VALIDATE_ERROR.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.ORDER_FAILURE);
            }

            //　@　@[パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合]
            // 　@・"遅延あり"（発注遅延フラグ）
            else if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(BooleanEnum.TRUE.intValue() + "");
            }
        }

        //４）　@パラメータ.リクエストデータ.条件注文種別＝"W指値"の場合、
        //　@以下の条件を生成したArrayListに追加する。
        else if (WEB3TriggerOrderTypeDef.W_LlIMIT.equals(l_request.triggerOrderType))
        {
            //　@[パラメータ.リクエストデータ.発注状況区分＝"待機@中"の場合]
            //　@　@・"DEFAULT"（リクエストタイプ）
            if (WEB3TriggerOrderStatusDef.ORDER_WAITING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.DEFAULT);
            }

            //　@[パラメータ.リクエストデータ.発注状況区分＝"発注中"の場合]
            //　@　@・"時価サーバ"（リクエストタイプ）
            else if (WEB3TriggerOrderStatusDef.ORDERING.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.QUOTE_SERVER);
            }

            //　@[パラメータ.リクエストデータ.発注状況区分＝"発注完了"の場合]
            //　@　@・"切替完了"（リクエストタイプ）
            else if (WEB3TriggerOrderStatusDef.ORDER_COMPLETE.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.TRANSFERED);
            }

            //　@　@[パラメータ.リクエストデータ.発注状況区分＝"発注遅延エラー"の場合]
            //    ・"遅延あり"（発注遅延フラグ）
            else if (WEB3TriggerOrderStatusDef.ORDER_DELAY_ERROR.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(BooleanEnum.TRUE.intValue() + "");
            }

            //　@[パラメータ.リクエストデータ.発注状況区分＝"ストップ注文失効"の場合]
            //　@　@・"失効"（リクエストタイプ）
            else if (WEB3TriggerOrderStatusDef.STOP_ORDER_INVALIDATION.equals(l_strTriggerOrderState))
            {
                l_lisQueryContainers.add(WEB3RequestTypeDef.INVALIDATE);
            }
        }

        //５）　@生成したArrayList.toArray()の戻り値を返却する。
        String[] l_strQueryContainers = new String[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_strQueryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;

    }

    /**
     * (get発注失敗注文)<BR>
     * 引数の条件に該当する発注失敗注文Paramsを返却する。<BR>
     * <BR>
     * １）　@発注失敗注文テーブル(rls_order_miss)を<BR>
     * 　@　@　@引数の条件で検索した結果を返却する。<BR>
     * <BR>
     * 　@[検索条件]<BR>
     * 　@　@失敗区分：　@パラメータ.失敗区分(*1)<BR>
     * 　@　@口座ID：　@パラメータ.注文単位.口座ID <BR>
     * 　@　@補助口座ID：　@パラメータ.注文単位.補助口座ID <BR>
     * 　@　@条件付き注文タイプ：　@パラメータ.条件注文種別 <BR>
     * 　@　@注文ID：　@パラメータ.注文単位.注文ID <BR>
     * 　@　@銘柄タイプ：　@パラメータ.銘柄タイプ <BR>
     * 　@　@検知区分：　@"オンライン" <BR>
     * <BR>
     * 　@(*1)対応するパラメータがnullの場合は、検索条件に加えない。<BR>
     * <BR>
     * ２）　@検索結果を返却する。  <BR>
     * 　@※検索結果が取得できなかった場合、nullを返却する。<BR>
     * 　@※検索結果のレコードが複数件の場合は、作成日付が最新のレコードを返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strOrderType - (条件注文種別)<BR>
     * 条件注文種別<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@param l_strMissType - (失敗区分)<BR>
     * 失敗区分<BR>
     * @@return RlsOrderMissParams
     * @@throws WEB3BaseException
     */
    public static RlsOrderMissParams getRlsOrderMiss(
        OrderUnit l_orderUnit,
        String l_strOrderType,
        ProductTypeEnum l_productType,
        String l_strMissType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getRlsOrderMiss(OrderUnit, String, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisQueryContainers = new ArrayList();

        //失敗区分
        if (l_strMissType != null)
        {
            l_sbWhere.append("miss_type = ? and");
            l_lisQueryContainers.add(l_strMissType);
        }

        //口座ID
        l_sbWhere.append(" account_id = ?");
        l_lisQueryContainers.add(l_orderUnit.getAccountId() + "");

        //補助口座ID
        l_sbWhere.append(" and sub_account_id = ?");
        l_lisQueryContainers.add(l_orderUnit.getSubAccountId() + "");

        //条件付き注文タイプ
        l_sbWhere.append(" and oms_cond_order_type = ?");
        l_lisQueryContainers.add(l_strOrderType);

        //注文ID
        l_sbWhere.append(" and order_id = ?");
        l_lisQueryContainers.add(l_orderUnit.getOrderId() + "");

        //銘柄タイプ
        l_sbWhere.append(" and product_type = ?");
        l_lisQueryContainers.add(l_productType);

        //検知区分
        l_sbWhere.append(" and detect_type = ?");
        l_lisQueryContainers.add(WEB3DetectTypeDef.ON_LINE);

        Object[] l_objQueryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_objQueryContainers);

        List l_lisRows = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisRows = l_processor.doFindAllQuery(
                RlsOrderMissRow.TYPE,
                l_sbWhere.toString(),
                l_objQueryContainers);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminToDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@検索結果を返却する。
        //    ※検索結果が取得できなかった場合、nullを返却する。
        RlsOrderMissRow l_rlsOrderMissRow = null;
        if (l_lisRows == null || l_lisRows.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //※検索結果のレコードが複数件の場合
        else if (l_lisRows.size() > 1)
        {
            Timestamp l_tsCreated = null;
            RlsOrderMissRow l_row = null;
            for (int i = 0; i < l_lisRows.size(); i++)
            {
                l_row = (RlsOrderMissRow)l_lisRows.get(i);
                if (l_tsCreated == null)
                {
                    l_tsCreated = l_row.getCreatedTimestamp();
                    l_rlsOrderMissRow = l_row;
                }
                else if (l_row.getCreatedTimestamp().compareTo(l_tsCreated) > 0)
                {
                    l_tsCreated = l_row.getCreatedTimestamp();
                    l_rlsOrderMissRow = l_row;
                }
            }
        }
        else
        {
            l_rlsOrderMissRow = (RlsOrderMissRow)l_lisRows.get(0);
        }

        RlsOrderMissParams l_rlsOrderMissParams =
            new RlsOrderMissParams(l_rlsOrderMissRow);
        log.exiting(STR_METHOD_NAME);
        return l_rlsOrderMissParams;
    }

}
@
