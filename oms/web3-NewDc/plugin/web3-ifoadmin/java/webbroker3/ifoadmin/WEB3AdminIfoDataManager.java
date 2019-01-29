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
filename	WEB3AdminIfoDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OPデータマネージャ(WEB3AdminIfoDataManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
Revision History : 2007/07/17　@張騰宇(中訊) モデル003
Revision History : 2009/03/03　@孟亞南(中訊) モデル007,008
Revision History : 2009/03/09　@劉剣(中訊) モデル013
*/
package webbroker3.ifoadmin;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ProcessIdDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifodeposit.WEB3IfoDepositPersistentDataManager;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者・先物OPデータマネージャ）<BR>
 * <BR>
 * 管理者・先物OPデータマネージャ<BR>
 * 商品管理(株式)のデータ作成などを管理するクラス<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminIfoDataManager<BR>
 * WEB3AdminIfoDataManager class.<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminIfoDataManager
{
    /** Log Variable.<BR> */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDataManager.class);

    /**
     * @@roseuid 41FDBE3C0109
     */
    public WEB3AdminIfoDataManager()
    {
    }

    /**
     * (get銘柄)<BR>
     * 引数の条件に該当する先物OP銘柄を取得する。<BR> 
     * ※パラメータ.先物／オプション区分 == nullの場合、 <BR>
　@   * nullを返却する。 <BR>
　@   * <BR>
     * １）　@パラメータ.先物／オプション区分 == "先物"の場合<BR> 
　@   *   先物OPプロダクトマネージャ.get銘柄()に処理を委譲する。 <BR>
     * <BR>
     *   [get銘柄()に指定する引数]<BR> 
　@   *    　@証券会社：　@パラメータ.証券会社<BR> 
　@   *    　@原資産銘柄コード：　@パラメータ.指数種別<BR> 
　@   *    　@限月：　@パラメータ.限月<BR> 
　@   *    　@先物オプション商品：　@"先物"（固定）<BR> 
　@   *    　@行使価格：　@0（固定） <BR>
　@   *    　@分割：　@"DEFAULT"（固定）<BR> 
　@   *    　@対象市場コード：　@null（固定） <BR>
     * <BR>
     * ２）　@パラメータ.先物／オプション区分 == "オプション"の場合<BR> 
     *   先物OPプロダクトマネージャ.get銘柄()に処理を委譲する。<BR> 
     * <BR>
     *   [get銘柄()に指定する引数]<BR> 
     *       証券会社：　@パラメータ.証券会社<BR> 
     *       原資産銘柄コード：　@パラメータ.指数種別<BR> 
     *       限月：　@パラメータ.限月 <BR>
     *       先物オプション商品： <BR>
     *       [パラメータ.オプション商品区分 == "プット"の場合]<BR> 
     *       "プットオプション" <BR>
     *       [上記以外の場合] <BR>
     *       "コールオプション" <BR>
     *       行使価格：　@パラメータ.行使価格<BR> 
     *       分割：　@"DEFAULT"（固定） <BR>
     *       対象市場コード：　@null（固定） <BR>
     * <BR>
     * ３）　@get銘柄()の戻り値を返却する。<BR> 
     *   ※上記処理にて例外がスローされた場合、nullを返却する。<BR>
     * @@param l_institution 証券会社<BR>
     * @@param l_strFuturesOptionDivision 先物／オプション区分<BR>
     * @@param l_strTargetProductCode 指数種別<BR>
     * @@param l_strDelivaryMonth 限月<BR>
     * @@param l_strStrikePrice 行使価格<BR>
     * @@param l_strOpProductType オプション商品区分<BR>
     * @@return WEB3IfoProductImpl 先物OP銘柄<BR>
     */
    public static WEB3IfoProductImpl getProduct(WEB3GentradeInstitution l_institution ,
        String l_strFuturesOptionDivision ,
        String l_strTargetProductCode ,
        String l_strDelivaryMonth ,
        String l_strStrikePrice ,
        String l_strOpProductType) 
    {
        final String STR_METHOD_NAME = "getProduct(WEB3GentradeInstitution, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoProductImpl l_ifoProductImpl = null;
        
        //※パラメータ.先物／オプション区分 == nullの場合、 
        //　@nullを返却する。 
        if (l_strFuturesOptionDivision == null) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        try 
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            WEB3IfoProductManagerImpl  l_ifoProductManager = 
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            
            //１）　@パラメータ.先物／オプション区分 == "先物"の場合 
            //　@先物OPプロダクトマネージャ.get銘柄()に処理を委譲する。
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuturesOptionDivision)) 
            {
                l_ifoProductImpl = l_ifoProductManager.getIfoProduct(l_institution,
                    l_strTargetProductCode,
                    l_strDelivaryMonth,
                    IfoDerivativeTypeEnum.FUTURES,
                    0D,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    null);
            }
            
            //２）　@パラメータ.先物／オプション区分 == "オプション"の場合 
            //　@先物OPプロダクトマネージャ.get銘柄()に処理を委譲する。
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFuturesOptionDivision)) 
            {
                // 先物オプション商品： 
                //  [パラメータ.オプション商品区分 == "プット"の場合] 
                //　@"プットオプション" 
                //　@[上記以外の場合] 
                //　@"コールオプション" 
                IfoDerivativeTypeEnum l_ifoDerivativeTypeEnum = null;
                if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_strOpProductType)) 
                {
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.PUT_OPTIONS;
                } 
                else 
                {
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.CALL_OPTIONS;
                }
                
                l_ifoProductImpl = l_ifoProductManager.getIfoProduct(l_institution,
                    l_strTargetProductCode,
                    l_strDelivaryMonth,
                    l_ifoDerivativeTypeEnum,
                    Double.parseDouble(l_strStrikePrice),
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    null);
            }
        } 
        catch (NotFoundException l_ex) 
        {
            //３）　@get銘柄()の戻り値を返却する。 
            //　@※上記処理にて例外がスローされた場合、nullを返却する。
            log.debug(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (WEB3BaseException l_ex) 
        {
            //３）　@get銘柄()の戻り値を返却する。 
            //　@※上記処理にて例外がスローされた場合、nullを返却する。
            log.debug(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoProductImpl;
    }

    /**
     * (get顧客一覧)<BR>
     * 引数の条件に該当する顧客の一覧を返却する。<BR> 
     * <BR>
     * １） ArrayListを生成する。 <BR>
     * <BR>
     * ２） DB検索 <BR>
     * 　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。 <BR>
     * 　@２－１） 拡張アカウントマネージャ.get顧客()メソッドをコールする。 <BR>
     * <BR>
     * 　@　@[get顧客()にセットするパラメータ] <BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード <BR>
     * 　@　@　@部店コード：　@処理対象の部店コード <BR>
     * 　@　@　@口座コード：　@パラメータ.顧客コード <BR>
     * <BR>
     * 　@２－２） 　@２－１）の結果が取得できた場合は、 <BR>
     * 　@　@　@生成したArrayListに追加する。 <BR>
     * 　@ <BR>
     * ３）　@ArrayList.toArray()の戻り値を返却する。 <BR>
     * 　@※toArray()の戻り値.length＝0の場合、 <BR>
     * 　@　@「該当データなし」の例外をスローする。<BR>
     * 　@class : WEB3BusinessLayerException<BR>
     * 　@tag : BUSINESS_ERROR_01037<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@return webbroker3.gentrade.WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901E5
     */
    public static WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getAccountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１） ArrayListを生成する。  
        List l_lisAccounts = new ArrayList();
        
        //２） DB検索  
        //　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。  
        //　@２－１） 拡張アカウントマネージャ.get顧客()メソッドをコールする。  
        //　@　@[get顧客()にセットするパラメータ]  
        //　@　@　@証券会社コード：　@パラメータ.証券会社コード  
        //　@　@　@部店コード：　@処理対象の部店コード  
        //　@　@　@口座コード：　@パラメータ.顧客コード  
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMananger = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        for (int i = 0; i < l_strBranchCodes.length; i++) 
        {
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
            
            //　@２－２） 　@２－１）の結果が取得できた場合は、生成したArrayListに追加する。  
            if (l_mainAccount != null) 
            {
                l_lisAccounts.add(l_mainAccount);
            }
        }
        //３）　@ArrayList.toArray()の戻り値を返却する。 
        WEB3GentradeMainAccount[] l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
        l_lisAccounts.toArray(l_mainAccounts);
        
        //　@※toArray()の戻り値.length＝0の場合、「該当データなし」の例外をスローする。
        if (l_mainAccounts.length == 0)
        {
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminIfoDataManager."+ STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
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
     * 　@class　@:　@WEB3SystemLayerException<BR>
     * 　@tag　@:　@SYSTEM_ERROR_80005<BR>
     * <BR>
     * 　@　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@　@部店コード = 部店コード = パラメータ.部店IDに該当する部店コード <BR>
     * 　@　@市場コード = "DEFAULT"<BR>
     * 　@　@銘柄コード = パラメータ.銘柄IDに該当する先物OP銘柄.原資産銘柄コード <BR>
     * 　@　@　@　@　@　@　@　@ただし、パラメータ.銘柄ID==nullの場合は、"DEFAULT"をセット<BR>
     * 　@　@受付時間区分 = "株価指数先物OP"<BR>
     * <BR>
     * ３）　@取引カレンダコンテキストを再セットする。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.setAttribute()をコールする。<BR>
     * <BR>
     * 　@[setAttribute()にセットするパラメータ]<BR>
     * 　@　@arg0：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * 　@　@arg1：　@プロパティセットした取引カレンダコンテキスト<BR>
     * <BR>
     * ４）　@受付日時、日付ロールのリセットを行う。<BR>
     * 　@取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_branchId - (部店ID)<BR>
     * 部店ID
     * @@param l_productId - (銘柄ID)<BR>
     * 銘柄ID
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901F6
     */
    public static void resetTradingCalContext(
        String l_strInstitutionCode, 
        Long l_branchId, 
        Long l_productId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "resetTradingCalContext(String, Long, Long)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取引カレンダコンテキストを取得する。 
        //　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールする。 
        Object l_object = 
            ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //　@※取得できなかった場合は、取引カレンダコンテキストを生成し、以降の処理で使用すること。 
        WEB3GentradeTradingClendarContext l_context = null;
        if (l_object != null)
        {
            l_context = (WEB3GentradeTradingClendarContext)l_object;
        }
        else
        {
            l_context = new WEB3GentradeTradingClendarContext();
        }
        
        //２）　@取得した取引カレンダコンテキストに以下のプロパティをセットする。 
        //　@※対応するパラメータがnullの場合は、再セットを行わない。
        //　@　@証券会社コード = パラメータ.証券会社コード 
        if (l_strInstitutionCode != null)
        {
            l_context.setInstitutionCode(l_strInstitutionCode);
        }
        
        //　@　@部店コード = パラメータ.部店IDに該当する部店コード 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        if (l_branchId != null)
        {
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                Branch l_branch = l_accountManager.getBranch(l_branchId.longValue());
                l_context.setBranchCode(l_branch.getBranchCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }
        
        //　@　@市場コード = "DEFAULT"  
        l_context.setMarketCode(WEB3ProductCodeDef.DEFAULT);
        
        //　@　@銘柄コード = パラメータ.銘柄IDに該当する先物OP銘柄.原資産銘柄コード 
        if (l_productId != null)
        {
            IfoProductManager l_ifoProductManager = 
                (IfoProductManager)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            try
            {
                WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_productId.longValue());
                l_context.setProductCode(l_ifoProduct.getUnderlyingProductCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("テーブルに該当するデータがありません。", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    "テーブルに該当するデータがありません。");
            }
        }
        //ただし、パラメータ.銘柄ID==nullの場合は、"DEFAULT"をセット
        else
        {
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        }
            
        //受付時間区分 = "株価指数先物OP" 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        //３）　@取引カレンダコンテキストを再セットする。 
        //　@ThreadLocalSystemAttributesRegistry.setAttribute()をコールする。 
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //４）　@受付日時、日付ロールのリセットを行う。 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get手動失効対象注文単位一覧)<BR>
     * 手動失効対象の注文単位の一覧を取得する。<BR>
     * <BR>
     *手動失効対象の注文単位の一覧を取得する。 <BR>
     *<BR>
     *１）　@検索条件文字列（：String）、 <BR>
     *　@検索条件データコンテナ（：ArrayList）を生成する。 <BR>
     *<BR>
     *２）検索条件を作成する。 <BR>
     *　@２－１）　@以下の注文条件を検索条件に追加する。 <BR>
     *　@　@[注文条件] <BR>
     *　@　@　@発注条件 = "DEFAULT" <BR>
     *　@　@　@And 注文有効状態 = "オープン" <BR>
     *　@　@　@And 市場から確認済みの数量 = null <BR>
     *<BR>
     *　@　@検索条件文字列 = " and order_condition_type = ?" <BR>
     *　@　@　@　@　@　@　@+ " and order_open_status = ?" <BR>
     *　@　@　@　@　@　@　@+ " and confirmed_quantity is null" <BR>
     *<BR>
     *　@　@データコンテナ（以下の順で追加する。） <BR>
     *　@　@　@・"DEFAULT（条件指定なし）" <BR>
     *　@　@　@・"オープン" <BR>
     *<BR>
     *　@２－２）　@部店条件を検索条件に追加する。 <BR>
     *　@　@パラメータ.部店コードの要素数分"?"を追加する。 <BR>
     *<BR>
     *　@　@検索条件文字列 += " and branch_id in (?, ?,,,) " <BR>
     *　@　@データコンテナにパラメータ.部店コードに該当する部店.部店IDを <BR>
     *　@　@要素数分、追加する。 <BR>
     *<BR>
     *　@　@※部店を取得する際に、パラメータ.証券会社を使用する。 <BR>
     *　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」 <BR>
     *　@　@　@の業務エラーをスローする。 <BR>
     *<BR>
     *　@２－３）　@パラメータ.銘柄ID != nullの場合、 <BR>
     *　@　@銘柄条件を検索条件に追加する。 <BR>
     *<BR>
     *　@　@検索条件文字列 += " and product_id = ? " <BR>
     *　@　@データコンテナにパラメータ.銘柄IDを追加する。 <BR>
     *<BR>
     *　@２－４）　@取引条件を検索条件文字列に追加する。 <BR>
     *　@　@２－４－１）　@パラメータ.取引区分一覧の要素数分Loopし、"?"を追加する。 <BR>
     *<BR>
     *　@　@　@検索条件文字列 += " and order_type in (?,?,,,) " <BR>
     *　@　@　@データコンテナにパラメータ.取引区分一覧の全要素を追加する。 <BR>
     *<BR>
     *　@２－５）　@パラメータ.顧客コード != nullの場合、 <BR>
     *　@　@顧客条件を検索条件に追加する。 <BR>
     *　@　@２－５－１）　@管理者・先物OPデータマネージャ.get顧客一覧()を <BR>
     *　@　@　@コールする。 <BR>
     *<BR>
     *　@　@　@[get顧客一覧()に指定する引数] <BR>
     *　@　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード <BR>
     *　@　@　@　@部店コード：　@パラメータ.部店コード <BR>
     *　@　@　@　@顧客コード：　@パラメータ.顧客コード <BR>
     *<BR>
     *　@　@２－５－２）　@２－５－１）の戻り値の要素数分、検索条件に"?"を、 <BR>
     *　@　@　@データコンテナに、各要素の口座IDを追加する。 <BR>
     *<BR>
     *　@　@　@検索条件文字列 += " and account_id in (?, ?,,,) " <BR>
     *　@　@　@データコンテナ（以下の順で追加する。） <BR>
     *　@　@　@　@・２－５－１）の戻り値の各要素.口座ID <BR>
     *<BR>
     *　@２－６）　@パラメータ.口座IDFrom != null　@かつ <BR>
     *　@　@パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を <BR>
     *　@　@検索条件に追加する。 <BR>
     *　@　@※非同期処理を行う場合に設定される。 <BR>
     *<BR>
     *　@　@検索条件文字列 += " and account_id >= ?" <BR>
     *　@　@　@　@　@　@　@　@+ " and account_id <= ?" <BR>
     *　@　@データコンテナ（以下の順で追加する。） <BR>
     *　@　@　@・パラメータ.口座IDFrom <BR>
     *　@　@　@・パラメータ.口座IDTo <BR>
     *<BR>
     *３）　@DBを検索する。 <BR>
     *　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     *<BR>
     *　@　@[doFindAllQuery()にセットするパラメータ] <BR>
     *　@　@　@arg0：　@先物OP注文単位Row.TYPE  <BR>
     *　@　@　@arg1：　@作成した検索条件文字列 <BR>
     *　@　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値 <BR>
     *<BR>
     *　@※検索結果が取得できなかった場合、nullを返却する。 <BR>
     *<BR>
     *４）　@キューテーブルを検索する条件を作成する。 <BR>
     *　@４－１）　@基本の検索条件文字列、データコンテナを作成する。 <BR>
     *　@　@※データコンテナは、以前のものを使用する場合、必ず要素を全てclearすること。 <BR>
     *　@　@[基本条件] <BR>
     *　@　@　@証券会社コード = パラメータ.証券会社コード <BR>
     *　@　@　@処理区分 = "未処理" <BR>
     *<BR>
     *　@　@検索条件文字列 = "institution_code = ?" <BR>
     *　@　@　@　@　@　@　@　@+ " and status = ?" <BR>
     *　@　@データコンテナ（以下の順で追加する。） <BR>
     *　@　@　@・パラメータ.証券会社 <BR>
     *　@　@　@・"未処理" <BR>
     *<BR>
     *　@４－２）　@部店条件を検索条件に追加する。 <BR>
     *　@　@パラメータ.部店コードの要素数分"?"を追加する。 <BR>
     *<BR>
     *　@　@検索条件文字列 += " and branch_code in (?, ?,,,)" <BR>
     *　@　@データコンテナにパラメータ.部店コードの全要素を追加する。 <BR>
     *<BR>
     *　@４－３）　@パラメータ.顧客コード != nullの場合、 <BR>
     *　@　@顧客条件を検索条件文字列に追加する。 <BR>
     *<BR>
     *　@　@４－３－１）　@管理者・先物OPデータマネージャ.get顧客一覧()を <BR>
     *　@　@　@コールする。 <BR>
     *<BR>
     *　@　@　@[get顧客一覧()に指定する引数] <BR>
     *　@　@　@　@証券会社コード：　@パラメータ.証券会社.証券会社コード <BR>
     *　@　@　@　@部店コード：　@パラメータ.部店コード <BR>
     *　@　@　@　@顧客コード：　@パラメータ.顧客コード <BR>
     *<BR>
     *　@　@４－３－２）　@４－３－１）の戻り値の要素数分、検索条件に"?"を、 <BR>
     *　@　@　@データコンテナに、各要素の口座コードを追加する。 <BR>
     *<BR>
     *　@　@　@検索条件文字列 += " and account_code in (?, ?,,,) " <BR>
     *　@　@　@データコンテナ（以下の順で追加する。） <BR>
     *　@　@　@　@・４－３－１）の戻り値の各要素.口座コード <BR>
     *<BR>
     *５）　@識別コードリスト（：ArrayList）を生成する。 <BR>
     *<BR>
     *６）　@先物OP注文取引キューテーブルを検索する。 <BR>
     *　@QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     *<BR>
     *　@　@[doFindAllQuery()にセットするパラメータ] <BR>
     *　@　@　@arg0：　@先物OP注文取引キューRow.TYPE  <BR>
     *　@　@　@arg1：　@作成した検索条件文字列 <BR>
     *　@　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値 <BR>
     *<BR>
     *　@検索結果が取得できた場合、検索結果の各要素.識別コードを <BR>
     *　@識別コードリストに追加する。 <BR>
     *<BR>
     *　@検索結果が取得できなかった場合、nullを返却する。 <BR>
     *<BR>
     *７）　@失効対象注文リスト（：ArrayList）を生成する。 <BR>
     *<BR>
     *８）　@失効対象注文を確定する。 <BR>
     *　@３）の戻り値(=失効対象の先物OP注文単位List)の要素数分、Loop処理を行い、 <BR>
     *　@処理対象の要素.識別コードが識別コードリストに含まれる場合、 <BR>
     *　@失効対象注文リストに処理対象の要素を追加する。 <BR>
     *<BR>
     *９）　@失効対象注文リスト.toArray()の戻り値を返却する。 <BR>
     *　@※失効対象注文リスト.size() == 0の場合、nullを返却する。 <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列
     * @@param l_strProductID - (銘柄ID)<BR>
     * 銘柄ID
     * @@param l_strTradingTypeList - (取引区分一覧)<BR>
     * 取引区分一覧
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@param l_accountIdFrom - (口座IDFrom)<BR>
     * 口座IDFrom
     * @@param l_accountIdTo - (口座IDTo)<BR>
     * 口座IDTo
     * @@return IfoOrderUnitRow[]
     * @@throws WEB3BaseException 
     * @@roseuid 44695939020C
     */
    public static IfoOrderUnitRow[] getManualExpireOrderUnits(
        WEB3GentradeInstitution l_institution, 
        String[] l_strBranchCodes, 
        String l_strProductID, 
        String[] l_strTradingTypeList, 
        String l_strAccountCode, 
        Long l_accountIdFrom, 
        Long l_accountIdTo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getManualExpireOrderUnits(" 
            + "WEB3GentradeInstitution, String[], String, String[], String, Long, Long)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        //１）　@検索条件文字列（：String）、  
        //　@検索条件データコンテナ（：ArrayList）を生成する。 
        List l_lisQueryContainers = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //２）検索条件を作成する。 
        //　@２－１）　@以下の注文条件を検索条件に追加する。 
        //      [注文条件] 
        //     　@発注条件 = "DEFAULT" 
        //     　@And 注文有効状態 = "オープン" 
        //     　@And 市場から確認済みの数量 = null 
        //     検索条件文字列 = " and order_condition_type = ?" 
        //     　@+ " and order_open_status = ?" 
        //     　@+ " and confirmed_quantity is null" 
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("order_condition_type = ?");
        l_sbQueryString.append(" and order_open_status = ?");
        l_sbQueryString.append(" and confirmed_quantity is null");
        
        //　@　@データコンテナ（以下の順で追加する。)
        //　@　@　@・"DEFAULT（条件指定なし）" 
        l_lisQueryContainers.add(WEB3OrderingConditionDef.DEFAULT);
        
        //　@　@　@・"オープン" 
        l_lisQueryContainers.add(OrderOpenStatusEnum.OPEN);
        
        //　@２－２）　@部店条件を検索条件に追加する。  
        //　@　@パラメータ.部店コードの要素数分"?"を追加する。
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
            // 検索条件文字列 += " and branch_id in (?, ?,,,) " 
            StringBuffer l_sbBranchIdForQuery = new StringBuffer();
            l_sbBranchIdForQuery.append(" and branch_id in (");
            try 
            {
                for (int i = 0; i < l_strBranchCodes.length; i++) 
                {
                    l_sbBranchIdForQuery.append("?,");
                    
                    //　@　@データコンテナにパラメータ.部店コードに該当する部店.部店IDを要素数分、addする。
                    Branch l_branch = l_accountManager.getBranch(l_institution, l_strBranchCodes[i]);
                    l_lisQueryContainers.add(new Long(l_branch.getBranchId()));
                }
            }
            catch (NotFoundException l_ex)
            {
                //　@　@※部店を取得する際に、パラメータ.証券会社を使用する。 
                //　@　@※取得時に例外となった場合、「条件に該当するデータが存在しない。」の業務エラーをスローする。 
                log.error("条件に該当するデータが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }
            
            l_sbQueryString.append(l_sbBranchIdForQuery.substring(0, l_sbBranchIdForQuery.length() - 1));
            l_sbQueryString.append(")");
        }

        //　@２－３）　@パラメータ.銘柄ID != nullの場合、 
        //　@　@銘柄条件を検索条件に追加する。 
        if (l_strProductID != null) 
        {
            // 検索条件文字列 += " and product_id = ? " 
            l_sbQueryString.append(" and product_id = ?");
            
            //データコンテナにパラメータ.銘柄IDを追加する。  
            l_lisQueryContainers.add(String.valueOf(l_strProductID));
        }
        
        //　@２－4）　@取引条件を検索条件文字列に追加する。 
        //　@　@２－4－１）　@パラメータ.取引区分一覧の要素数分Loopし、"?"を追加する。  
        //　@　@　@検索条件文字列 += " and order_type in (?,?,,,) "  
        //　@　@　@データコンテナにパラメータ.取引区分一覧の全要素を追加する。  　@　@
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0) 
        {
            StringBuffer l_sbIfoForQuery = new StringBuffer();
            l_sbIfoForQuery.append(" and order_type in (");
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                l_sbIfoForQuery.append("?,");
                l_lisQueryContainers.add(l_strTradingTypeList[i]);
            }
            l_sbQueryString.append(l_sbIfoForQuery.substring(0, l_sbIfoForQuery.length() -1));
            l_sbQueryString.append(")");
        }
        
        //　@２－5）　@パラメータ.顧客コード != nullの場合、
        //　@　@顧客条件を検索条件に追加する。 
        if (l_strAccountCode != null)
        {
            //２－５－１）　@管理者・先物OPデータマネージャ.get顧客一覧()を 
            //　@コールする。 
            //　@[get顧客一覧()に指定する引数] 
            // 証券会社コード：　@パラメータ.証券会社.証券会社コード 
            // 部店コード：　@パラメータ.部店コード 
            // 顧客コード：　@パラメータ.顧客コード 
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminIfoDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //　@　@２－５－２）　@２－５－１）の戻り値の要素数分、検索条件に"?"を、 
            //　@　@　@データコンテナに、各要素の口座IDを追加する。　@　@　@
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //　@検索条件文字列 += " and account_id in (?, ?,,,) " 
                StringBuffer l_sbAccountIdForQuery = new StringBuffer();
                l_sbAccountIdForQuery.append(" and account_id in (");
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountIdForQuery.append("?,");
                    
                    // データコンテナ（以下の順で追加する。） 
                    //　@・２－５－１）の戻り値の各要素.口座ID 
                    l_lisQueryContainers.add(String.valueOf(l_mainAccounts[i].getAccountId()));
                }
                
                l_sbQueryString.append(l_sbAccountIdForQuery.substring(0, l_sbAccountIdForQuery.length() - 1));
                l_sbQueryString.append(")");
            }
        }
        
        //　@２－6）　@パラメータ.口座IDFrom != null　@かつ 
        //　@　@パラメータ.口座IDTo != nullの場合、口座IDレンジ条件を 
        //　@　@検索条件に追加する。 
        //　@　@※非同期処理を行う場合に設定される。 　@　@
        if (l_accountIdFrom != null && l_accountIdTo != null) 
        {
            //　@　@検索条件文字列 += " and account_id >= ?" + " and account_id <= ?" 
            l_sbQueryString.append(" and account_id >= ?");
            l_sbQueryString.append(" and account_id <= ?");
            
            //・パラメータ.口座IDFrom 
            l_lisQueryContainers.add(l_accountIdFrom);
            
            //・パラメータ.口座IDTo 
            l_lisQueryContainers.add(l_accountIdTo);
        }
        
        //３）　@DBを検索する。 
        //　@　@QueryProcessor.doFindAllQuery()メソッドをコールする。 
        QueryProcessor l_queryProcessor = null;
        List l_lisRows = null;
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            
            //　@　@[doFindAllQuery()にセットするパラメータ] 
            //　@　@　@arg0：　@先物OP注文単位Row.TYPE  
            //　@　@　@arg1：　@作成した検索条件文字列 
            //　@　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値 
            l_lisRows = l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_queryContainers);
            
            // ※検索結果が取得できなかった場合、nullを返却する。
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        catch (DataQueryException l_dqex)
        {
            log.error("DBアクセスエラー", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DBアクセスエラー", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
         
        //４）　@キューテーブルを検索する条件を作成する。 
        //　@４－１）　@基本の検索条件文字列、データコンテナを作成する。 
        //　@　@※データコンテナは、以前のものを使用する場合、必ず要素を全てclearすること。 
        //　@　@[基本条件] 
        //　@　@　@証券会社コード = パラメータ.証券会社コード 
        //　@　@　@処理区分 = "未処理" 
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisIfoQueryContainers = new ArrayList();
        
        //　@　@検索条件文字列 = "institution_code = ?" + " and status = ?" 
        l_sbQuery.append("institution_code = ?");
        l_sbQuery.append(" and status = ?");
        
        //・パラメータ.証券会社 
        l_lisIfoQueryContainers.add(l_institution.getInstitutionCode());

        //・"未処理" 
        l_lisIfoQueryContainers.add(WEB3FrontOrderStatusDef.NOT_DEAL);
        
        //　@４－２）　@部店条件を検索条件に追加する。 
        //　@　@パラメータ.部店コードの要素数分"?"を追加する。 
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0) 
        {
            //　@　@検索条件文字列 += " and branch_code in (?, ?,,,)" 
            //　@　@データコンテナにパラメータ.部店コードの全要素を追加する。
            StringBuffer l_sbBranchCodeForQuery = new StringBuffer();
            l_sbBranchCodeForQuery.append(" and branch_code in (");
            for (int i = 0; i < l_strBranchCodes.length; i++) 
            {
                l_sbBranchCodeForQuery.append("?,");
                l_lisIfoQueryContainers.add(l_strBranchCodes[i]);
            }
            
            l_sbQuery.append(l_sbBranchCodeForQuery.substring(0, l_sbBranchCodeForQuery.length() - 1));
            l_sbQuery.append(")");
        }
        
        //　@４－３）　@パラメータ.顧客コード != nullの場合、 
        //　@　@顧客条件を検索条件文字列に追加する。 
        //　@　@４－3－１）　@管理者・先物OPデータマネージャ.get顧客一覧()を 
        //　@　@　@コールする。 
        if (l_strAccountCode != null) 
        {
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminIfoDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //　@　@４－3－２）　@４－3－１）の戻り値の要素数分、検索条件に"?"を、 
            //　@　@　@データコンテナに、各要素の口座コードを追加する。 
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //検索条件文字列 += " and account_code in (?, ?,,,) "
                //データコンテナ（以下の順で追加する。） 
                //　@　@　@　@・４－3－１）の戻り値の各要素.口座コード 
                StringBuffer l_sbAccountsForQuery = new StringBuffer();
                l_sbAccountsForQuery.append(" and account_code in (");
                
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountsForQuery.append("?,");
                    l_lisIfoQueryContainers.add(l_mainAccounts[i].getAccountCode());
                }
                
                l_sbQuery.append(l_sbAccountsForQuery.substring(0, l_sbAccountsForQuery.length() - 1));
                l_sbQuery.append(")");
            }
        }
        
        //５）　@識別コードリスト（：ArrayList）を生成する。 
        List l_lisOrderRequestNumbers = new ArrayList();
        
        //６）　@先物OP注文取引キューテーブルを検索する。  
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0)
        {
            //　@QueryProcessor.doFindAllQuery()メソッドをコールする。 
            QueryProcessor l_ifoEqueryProcessor =null;
            List l_lisIfoRows = null;
            Object[] l_ifoQueryContainers = new Object[l_lisIfoQueryContainers.size()];
            l_lisIfoQueryContainers.toArray(l_ifoQueryContainers);
            try
            {
                l_ifoEqueryProcessor = Processors.getDefaultProcessor();
                l_lisIfoRows = l_ifoEqueryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_sbQuery.toString(),
                    l_ifoQueryContainers);
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DBアクセスエラー", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DBアクセスエラー", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
            if (!l_lisIfoRows.isEmpty())
            {
                for (int i = 0; i < l_lisIfoRows.size(); i++) 
                {
                    //　@検索結果が取得できた場合、検索結果の各要素.識別コードを 
                    //　@識別コードリストに追加する。 
                    HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisIfoRows.get(i);
                    l_lisOrderRequestNumbers.add(l_row.getOrderRequestNumber());
                }
            } 
            else 
            {
                // 検索結果が取得できなかった場合、nullを返却する。
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        
        //７）　@失効対象注文リスト（：ArrayList）を生成する。 
        List l_lisLapseTargetOrder = new ArrayList();
        
        //８）　@失効対象注文を確定する。 
        //　@３）の戻り値(=失効対象の先物OP注文単位List)の要素数分、Loop処理を行い、 
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_lisRows.get(i);
            for (int j = 0; j < l_lisOrderRequestNumbers.size(); j++) 
            {
                //　@処理対象の要素.識別コードが識別コードリストに含まれる場合、 
                //　@失効対象注文リストに処理対象の要素を追加する。
                if (l_ifoOrderUnitRow.getOrderRequestNumber().equals(l_lisOrderRequestNumbers.get(j)))
                {
                    l_lisLapseTargetOrder.add(l_ifoOrderUnitRow);
                }
            }
        }
        
        //※失効対象注文リスト.size() == 0の場合、nullを返却する。
        if (l_lisLapseTargetOrder.size() == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //９）　@失効対象注文リスト.toArray()の戻り値を返却する。
        IfoOrderUnitRow[] l_rows = new IfoOrderUnitRow[l_lisLapseTargetOrder.size()];
        l_lisLapseTargetOrder.toArray(l_rows);
        log.exiting(STR_METHOD_NAME);
        return l_rows;

    }

    /**
     * (get証拠金情報一覧)<BR>
     * 引数のリクエストデータにて指定された条件に該当する <BR>
     * 証拠金情報の一覧を返却する。 <BR>
     * <BR>
     * １）　@検索条件文字列（：String）、検索条件データコンテナ（：ArrayList）を生成する。 <BR>
     * <BR>
     * ２） 証拠金情報を取得する検索条件を作成する。 <BR>
     * <BR>
     * ２－１）　@証券会社コード条件を検索条件に追加する。 <BR>
     * 　@　@検索条件文字列 += " institution_code　@= ? " <BR>
     * 　@　@データコンテナにパラメータ.証券会社.証券会社コードを追加する。 <BR>
     * <BR>
     * ２－２）　@部店条件を検索条件に追加する。 <BR>
     * 　@　@パラメータ.リクエストデータ.部店コード一覧の要素数分"?"を追加する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 += " and branch_code in (?, ?,,,) " <BR>
     * <BR>
     * 　@　@部店条件を生成した検索条件データコンテナに追加する。 <BR>
     * 　@　@パラメータ.リクエストデータ.部店コードの各要素を全てデータコンテナに追加する。 <BR>
     * <BR>
     * ２－３） パラメータ.リクエストデータ．顧客コード!= nullの場合、<BR>
     * 　@　@　@顧客コードを検索条件文字列に追加する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 += "　@and account_code　@like ? " <BR>
     * <BR>
     * データコンテナに「パラメータ.リクエストデータ.顧客コード＋%」を追加する。 <BR>
     * <BR>
     * ２－４）　@検索日付を検索条件に追加する。 <BR>
     * <BR>
     * 　@　@検索条件文字列 += " and calc_date = ?" <BR>
     * <BR>
     * 　@　@データコンテナにパラメータ.リクエストデータ.検索日付追加する。 <BR>
     * <BR>
     * ３）　@DBを検索する。 <BR>
     * 　@QueryProcessor.doFindAllQuery()メソッドをコールする。 <BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ] <BR>
     * 　@　@arg0：　@証拠金Row.TYPE <BR>
     * 　@　@arg1：　@作成した検索条件文字列 <BR>
     * 　@　@arg2：　@作成したデータコンテナ.toArray()の戻り値 <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社
     * @@param l_request - (管理者・証拠金不足状況照会リクエスト)<BR>
     * 管理者・証拠金不足状況照会リクエスト
     * @@return IfoDepositRow[]
     * @@throws WEB3BaseException
     */
    public static IfoDepositRow[] getDepositInfoList(
        Institution l_institution,
        WEB3AdminIfoDepShortageReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDepositInfoList(Institution, WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        //　@検索条件文字列（：String）、検索条件データコンテナ（：ArrayList）を生成する。
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisContainers = new ArrayList();
        //証券会社コード条件を検索条件に追加する。
        //検索条件文字列 += " institution_code　@= ? "
        //データコンテナにパラメータ.証券会社.証券会社コードを追加する。
        l_sbWhere.append(" institution_code = ? ");
        l_lisContainers.add(l_institution.getInstitutionCode());

        //部店条件を検索条件に追加する。
        //パラメータ.リクエストデータ.部店コード一覧の要素数分"?"を追加する。
        //検索条件文字列 += " and branch_code in (?, ?,,,) "
        //部店条件を生成した検索条件データコンテナに追加する。
        //パラメータ.リクエストデータ.部店コードの各要素を全てデータコンテナに追加する。
        int l_intBranchCodeLength = l_request.branchCode.length;
        l_sbWhere.append(" and branch_code in ( ");
        for (int i = 0; i < l_intBranchCodeLength; i++)
        {
            if (i + 1 == l_intBranchCodeLength)
            {
                l_sbWhere.append("?");
            }
            else
            {
                l_sbWhere.append("?, ");
            }
            l_lisContainers.add(l_request.branchCode[i]);
        }
        l_sbWhere.append(" ) ");

        //パラメータ.リクエストデータ．顧客コード!= nullの場合、
        //顧客コードを検索条件文字列に追加する。
        //検索条件文字列 += "　@and account_code　@like ? "
        //データコンテナに「パラメータ.リクエストデータ.顧客コード＋%」を追加する。
        if (l_request.accountCode != null)
        {
            l_sbWhere.append(" and account_code like ? || '%' ");
            l_lisContainers.add(l_request.accountCode);
        }

        //検索日付を検索条件に追加する。
        //検索条件文字列 += " and calc_date = ?"
        //データコンテナにパラメータ.リクエストデータ.検索日付追加する。
        l_sbWhere.append(" and calc_date = ? ");
        l_lisContainers.add(l_request.searchDate);

        //QueryProcessor.doFindAllQuery()メソッドをコールする。
        //[doFindAllQuery()にセットするパラメータ]
        //arg0：　@証拠金Row.TYPE
        //arg1：　@作成した検索条件文字列
        //arg2：　@作成したデータコンテナ.toArray()の戻り値
        QueryProcessor l_ifoProcessor = null;
        List l_lisIfoDepositRows = null;
        Object[] l_ifoQueryContainers = new Object[l_lisContainers.size()];
        l_lisContainers.toArray(l_ifoQueryContainers);
        try
        {
            l_ifoProcessor = Processors.getDefaultProcessor();
            l_lisIfoDepositRows =
                l_ifoProcessor.doFindAllQuery(
                    IfoDepositRow.TYPE,
                    l_sbWhere.toString(),
                    l_ifoQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスエラー", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoDepositRow[] l_ifoDepositRows = new IfoDepositRow[l_lisIfoDepositRows.size()];
        l_lisIfoDepositRows.toArray(l_ifoDepositRows);
        log.exiting(STR_METHOD_NAME);
        return l_ifoDepositRows;
    }

    /**
     * (is証拠金不足メール送信済)<BR>
     * 該当会社部店の当営業日の証拠金不足メール送信済であるかを判定する。<BR>
     * 証拠金不足メール送信済である場合はtrueを、未送信である場合はfalseを返却する。<BR>
     * <BR>
     * １）　@プロセス管理Paramsの取得<BR>
     * 　@証拠金計算データソースアクセス管理.getプロセス管理Params( )<BR>
     * <BR>
     * 　@[引数の設定]<BR>
     * 　@プロセスID：　@”0001”(証拠金不足確定)<BR>
     * 　@証券会社コード：　@引数.証券会社コード<BR>
     * 　@部店コード：　@引数.部店コード<BR>
     * <BR>
     * ２）　@証拠金不足メール送信済の場合(getプロセス管理Params( )で該当データあり)、<BR>
     * trueを返却する。 <BR>
     * <BR>
     * ３）　@証拠金不足メール未送信の場合(getプロセス管理Params( )で該当データなし(null))、<BR>
     * falseを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@return boolean
     */
    public static boolean isIfoDepositMailFlag(String l_strInstitutionCode, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "isIfoDepositMailFlag(String, String)";
        log.entering(STR_METHOD_NAME);

        //証拠金計算データソースアクセス管理.getプロセス管理Params( )
        //[引数の設定]
        //プロセスID：　@”0001”(証拠金不足確定)
        //証券会社コード：　@引数.証券会社コード
        //部店コード：　@引数.部店コード
        ProcessManagementParams l_processManagementParams =
            WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                WEB3ProcessIdDef.DEPOSIT_SHORTAGE_CONFIRM, l_strInstitutionCode, l_strBranchCode);

        //証拠金不足メール送信済の場合(getプロセス管理Params( )で該当データあり)、trueを返却する。
        if (l_processManagementParams != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //証拠金不足メール未送信の場合(getプロセス管理Params( )で該当データなし(null))、falseを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is清算値速報受信済)<BR>
     * 清算値速報受信済がどうかを判定する。<BR>
     * <BR>
     * 受信済である場合はtrueを、受信済でない場合はfalseを返却する。<BR>
     * <BR>
     * １）　@プロセス管理Paramsの取得<BR>
     * 　@証拠金計算データソースアクセス管理.getプロセス管理Params( )<BR>
     * <BR>
     * 　@[引数の設定]<BR>
     * 　@プロセスID：　@”0008”(清算値速報受信)<BR>
     * 　@証券会社コード：　@引数.証券会社コード<BR>
     * 　@部店コード：　@引数.部店コード<BR>
     * <BR>
     * ２）　@清算値速報受信済の場合(getプロセス管理Params( )で該当データあり)、trueを返却する。<BR>
     * <BR>
     * ３）　@清算値速報未送信の場合(getプロセス管理Params( )で該当データなし(null))、falseを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@return boolean
     */
    public static boolean isQuickReportReceived(String l_strInstitutionCode, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "isQuickReportReceived(String, String)";
        log.entering(STR_METHOD_NAME);

        //証拠金計算データソースアクセス管理.getプロセス管理Params( )
        //[引数の設定]
        //プロセスID：　@”0008”(清算値速報受信)
        //証券会社コード：　@引数.証券会社コード
        //部店コード：　@引数.部店コード
        ProcessManagementParams l_processManagementParams =
            WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                WEB3ProcessIdDef.QUICK_REPORT_RECEIVED, l_strInstitutionCode, l_strBranchCode);

        //清算値速報受信済の場合(getプロセス管理Params( )で該当データあり)、trueを返却する。
        if (l_processManagementParams != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //清算値速報未送信の場合(getプロセス管理Params( )で該当データなし(null))、falseを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
