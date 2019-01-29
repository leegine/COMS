head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定時定額買付共通サービスImpl(WEB3MutualFixedBuyCommonServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 徐宏偉 (中訊) 新規作成
                 : 2006/07/24 栄イ (中訊) 仕様変更 モデル No.459
Revesion History : 2008/07/09 武波 (中訊) 仕様変更 モデル No.607,612
Revesion History : 2008/07/31 武波 (中訊) 仕様変更 モデル No.621
*/
package webbroker3.mf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.data.MfFixedBuyingDrawAccountRow;
import webbroker3.mf.message.WEB3MutualAccountDrawYMComparator;
import webbroker3.mf.message.WEB3MutualDisplayOrderComparator;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryCodeComparator;
import webbroker3.mf.message.WEB3MutualProductCodeComparator;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (定時定額買付共通サービスImpl)<BR>
 * 定時定額買付共通サービスImpl<BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyCommonServiceImpl
    implements WEB3MutualFixedBuyCommonService 
{
   /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCommonServiceImpl.class);
    /**
     * (validate定時定額買付金額)<BR>
     * 定時定額買付最低金額チェック、単位金額チェックを行なう。<BR>
     * <BR>
     * １）定時定額買付最低金額を取得する。<BR>
     * 　@　@(*) 引数.補助口座.getBranch().get投信定時定額買付最低金額()の <BR>
     * 　@　@　@戻り値を取得する。<BR> 
     * 　@　@(*) get投信定時定額買付最低金額()の戻り値がDouble.NaNの場合、<BR>
     * 　@　@　@データ不整合エラーをスローする。<BR>
     * 　@　@　@　@　@　@　@　@class: WEB3SystemLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   SYSTEM_ERROR_80006 <BR>
     * <BR>
     * 　@１－１）引数.買付金額（月々）≠null and 引数.買付金額（月々）≠0 <BR>
     * 　@　@　@　@　@　@の場合は以下をチェックする。 <BR>
     * 　@　@　@　@　@引数.買付金額（月々） ＜ 定時定額買付最低金額の <BR>
     * 　@　@　@　@　@　@場合は例外をスローする。<BR> 
     *　@　@　@　@　@（定時定額買付最低金額エラー） <BR>
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02481 <BR>
     * <BR>
     * 　@１－２）引数.買付金額（積み増し）≠null and 引数.買付金額（積み増し）≠0 <BR>
     * 　@　@　@　@　@　@の場合は以下をチェックする。<BR>
     * 　@　@　@　@　@引数.買付金額（積み増し） ＜ 定時定額買付最低金額の <BR>
     * 　@　@　@　@　@　@場合は例外をスローする。<BR> 
     * 　@　@　@　@　@（定時定額買付最低金額エラー）<BR> 
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02481 <BR>
     * <BR>
     * ２）定時定額買付単位金額を取得する<BR> 
     * 　@　@(*) 引数.補助口座.getBranch().get投信定時定額買付単位金額()の <BR>
     * 　@　@　@戻り値を取得する。<BR> 
     * 　@　@(*) get投信定時定額買付単位金額()の戻り値がDouble.NaNの場合、<BR>
     * 　@　@　@データ不整合エラーをスローする。 <BR>
     * <BR>
     * 　@２－１）引数.買付金額（月々）≠null and 引数.買付金額（月々）≠0 <BR>
     * 　@　@　@　@　@　@の場合は以下をチェックする。 <BR>
     * 　@　@　@　@　@引数.買付金額（月々）が定時定額買付単位金額で割り切れない<BR>
     * 　@　@　@　@　@　@場合は例外をスローする。 <BR>
     * 　@　@　@　@　@（定時定額買付単位金額エラー）<BR>
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02491 <BR>
     * <BR>
     * 　@２－２）引数.買付金額（積み増し）≠null and 引数.買付金額（積み増し）≠0 <BR>
     * 　@　@　@　@　@　@の場合は以下をチェックする。 <BR>
     * 　@　@　@　@　@引数.買付金額（積み増し）が定時定額買付単位金額で割り切れない<BR>
     * 　@　@　@　@　@　@場合は例外をスローする。 <BR>
     *　@　@　@　@　@（定時定額買付単位金額エラー）<BR>
     * 　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_02491 <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_strMonthlyBuyAmount - (買付金額（月々）)<BR>
     * 買付金額（月々）<BR>
     * @@param l_strIncreaseBuyAmount - (買付金額（積み増し）)<BR>
     * 買付金額（積み増し）<BR>
     * @@throws WEB3BaseException
     */
    public void validateFixedBuyAmount(
        SubAccount l_subAccount, 
        String l_strMonthlyBuyAmount,
        String l_strIncreaseBuyAmount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFixedBuyAmount(SubAccount l_subAccount, " + 
            " String l_strMonthlyBuyAmount, String l_strIncreaseBuyAmount) ";
        log.entering(STR_METHOD_NAME);
        
        //１）定時定額買付最低金額を取得する。
        // (*) 引数.補助口座.getBranch().get投信定時定額買付最低金額()の 
        // 　@　@戻り値を取得する。
        
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
 
        WEB3GentradeBranch l_gentradeBranch = 
            (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();    
        double l_dblFixedBuyMinAmount = l_gentradeBranch.getMfFixedBuyingMinAmount();
        
        //(*) get投信定時定額買付最低金額()の戻り値がDouble.NaNの場合、
        //データ不整合エラーをスローする。
        if (Double.isNaN(l_dblFixedBuyMinAmount))
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
             
        //１－１）引数.買付金額（月々）≠null and 引数.買付金額（月々）≠0 
        //　@　@　@　@　@の場合は以下をチェックする。
        if (l_strMonthlyBuyAmount != null && !"0".equals(l_strMonthlyBuyAmount))
        {
            //引数.買付金額（月々） ＜ 定時定額買付最低金額の 
            //　@　@　@　@　@場合は例外をスローする。
            double l_dblMonthlyBuyAmount = Double.parseDouble(l_strMonthlyBuyAmount);
            if (l_dblMonthlyBuyAmount < l_dblFixedBuyMinAmount)
            {
                log.debug("引数.買付金額（月々） ＜ 定時定額買付最低金額の" + 
                    "場合は例外をスローする。");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
        }
        
        //１－２）引数.買付金額（積み増し）≠null and 引数.買付金額（積み増し）≠0
        // 　@　@　@　@　@の場合は以下をチェックする。
        if (l_strIncreaseBuyAmount != null && !"0".equals(l_strIncreaseBuyAmount))
        {
            //引数.買付金額（積み増し） ＜ 定時定額買付最低金額の 
            // 　@　@　@　@　@場合は例外をスローする。
            double l_dblIncreaseBuyAmount = Double.parseDouble(l_strIncreaseBuyAmount);
            if (l_dblIncreaseBuyAmount < l_dblFixedBuyMinAmount)
            {
                log.debug("引数.買付金額（積み増し） ＜ 定時定額買付最低金額の" + 
                    "場合は例外をスローする。");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02481,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
        }
        
        //２）定時定額買付単位金額を取得する
        //　@　@　@　@　@(*) 引数.補助口座.getBranch().get投信定時定額買付単位金額()の 
        //　@　@　@　@　@戻り値を取得する。
        double l_dblFixedBuyUnitAmount = l_gentradeBranch.getMfFixedBuyingUnitAmount();
               
        //(*) get投信定時定額買付単位金額()の戻り値がDouble.NaNの場合、<BR>
        // 　@　@　@データ不整合エラーをスローする。
        if (Double.isNaN(l_dblFixedBuyUnitAmount))
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //２－１）引数.買付金額（月々）≠null and 引数.買付金額（月々）≠0 
        // 　@　@　@　@　@の場合は以下をチェックする。
        if (l_strMonthlyBuyAmount != null && !"0".equals(l_strMonthlyBuyAmount))
        {
            //引数.買付金額（月々）が定時定額買付単位金額で割り切れない
            // 　@　@　@　@　@場合は例外をスローする。
            double l_dblMonthlyBuyAmount = Double.parseDouble(l_strMonthlyBuyAmount);
            if (l_dblMonthlyBuyAmount % l_dblFixedBuyUnitAmount != 0)
            {
                log.debug("引数.買付金額（月々）が定時定額買付単位金額" + 
                    "で割り切れない場合は例外をスローする。");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02491,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
        }
        
        //２－２）引数.買付金額（積み増し）≠null and 引数.買付金額（積み増し）≠0 
        // 　@　@　@　@　@の場合は以下をチェックする。       
        if (l_strIncreaseBuyAmount != null && !"0".equals(l_strIncreaseBuyAmount))
        {
            //引数.買付金額（積み増し）が定時定額買付単位金額で割り切れない
            // 　@　@　@　@　@場合は例外をスローする。
            double l_dblIncreaseBuyAmout = Double.parseDouble(l_strIncreaseBuyAmount);
            if (l_dblIncreaseBuyAmout % l_dblFixedBuyUnitAmount != 0)
            {
                log.debug("引数.買付金額（積み増し）が定時定額買付単位金額" + 
                    "で割り切れない場合は例外をスローする。");
                log.exiting(STR_METHOD_NAME);  
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02491,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }           
        }
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (validate外国証券口座開設)<BR>
     * 外国証券口座の開設の必要があるかどうかをチェックする。<BR>
     * <BR>
     * １）引数.拡張投信銘柄.isFWF（）をコールする。<BR>
     * ２）引数.拡張投信銘柄.is外国投信（）をコールする。<BR> 
     * ３）isFWF()の戻り値 == true または、is外国投信()の戻り値 == true の場合、<BR>  
     * 　@　@以下を実施する。 <BR> 
     * <BR> 
     * 　@　@３－１）引数.補助口座.getMainAccount()をコールし、顧客を取得する。<BR>  
     * <BR> 
     * 　@　@３－２）取得した顧客.is外国証券口座開設()をコールし、 <BR> 
     * 　@　@　@　@　@　@is外国証券口座開設()の戻り値がtrueの場合、falseを返す。<BR>  
     * 　@　@　@　@　@　@is外国証券口座開設()の戻り値がfalseの場合、trueを返す。 <BR> 
     * <BR> 
     * ４）それ以外の場合、falseを返す。<BR> 
     * <BR> 
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_mfProduct - (拡張投信銘柄)<BR>
     * 拡張投信銘柄<BR>
     * @@return boolean
     */
    public boolean validateForeignSecAccOpen(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct) 
    {
        final String STR_METHOD_NAME =
            "validateForeignSecAccOpen(SubAccount l_subAccount, " + 
            "  SubAccount l_subAccount, WEB3MutualFundProduct l_mfProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
     
        boolean l_blnRetrunFlag = false;
        
        //１）引数.拡張投信銘柄.isFWF（）をコールする。
        boolean l_blnIsFWF = l_mfProduct.isFWF();
        
        //２）引数.拡張投信銘柄.is外国投信（）をコールする。
        boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();
        
        //３）isFWF()の戻り値 == true または、is外国投信()の戻り値 == true の場合、  
        //　@　@以下を実施する。
        if (l_blnIsFWF || l_blnIsForeignFund)
        {
            //３－１）引数.補助口座.getMainAccount()をコールし、顧客を取得する。
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            
            //３－２）取得した顧客.is外国証券口座開設()をコールし、 
            // 　@　@　@　@　@　@is外国証券口座開設()の戻り値がtrueの場合、falseを返す。  
            //　@　@　@　@　@　@is外国証券口座開設()の戻り値がfalseの場合、trueを返す。
            boolean l_blnIsForeignAccountOpen = l_mainAccount.isForeignAccountOpen();
            if (l_blnIsForeignAccountOpen)
            {
                l_blnRetrunFlag = false;
            }
            else
            {
                l_blnRetrunFlag = true;
            }
        }
        
        // ４）それ以外の場合、falseを返す。
        log.exiting(STR_METHOD_NAME);  
        return l_blnRetrunFlag;
    }
    
    /**
     * (get定時定額買付条件リスト)<BR>
     * 定時定額買付条件のリストを返す。<BR>
     * <BR>
     * １）定時定額買付条件テーブルを検索し、 <BR>
     * 　@　@オブジェクトのListを取得する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード <BR>
     * 　@　@　@部店コード　@= 引数.部店コード <BR>
     * 　@　@　@口座コード　@= 引数.口座コード <BR>
     * 　@　@　@※引数:検索条件文字列がnull以外の場合、検索条件に <BR>
     *         and 引数:検索条件文字列を追加し、引数:検索条件値をバインドする。 <BR>
     * <BR>
     * ２）　@定時定額買付条件のListをリターンする。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_objQueryValues - (検索条件値)<BR>
     * 検索条件値<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionList(
        String l_strInstitutionCode,
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strQueryString, 
        Object[] l_objQueryValues) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getFixedBuyConditionList(String, String, String, String, Object[])";
        log.entering(STR_METHOD_NAME);
        
        List l_lisReturn = null;
        
        //［検索条件］
        //    証券会社コード = 引数.証券会社コード
        //  　@部店コード　@= 引数.部店コード 
        //　@　@口座コード　@= 引数.口座コード 
        String l_strWhere = " institution_code = ? and branch_code = ? and account_code = ? ";
        List l_lisValue = new ArrayList();
        l_lisValue.add(l_strInstitutionCode);
        l_lisValue.add(l_strBranchCode);
        l_lisValue.add(l_strAccountCode);
        
        //引数:検索条件文字列がnull以外の場合、検索条件に 
        //and 引数:検索条件文字列を追加し、引数:検索条件値をバインドする。
        if(l_strQueryString != null && l_objQueryValues != null)
        {
            l_strWhere += "and " + l_strQueryString;
            for(int i = 0; i < l_objQueryValues.length; i++)
            {
                l_lisValue.add(l_objQueryValues[i]);
            }
        }
        Object[] l_objValues = l_lisValue.toArray();
        
        //定時定額買付条件テーブルを検索し、 
        //オブジェクトのListを取得する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturn = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingCondRow.TYPE, 
                l_strWhere, l_objValues);
        }
        catch(DataNetworkException l_dnex)
           {
            log.error("DBへのアクセスに失敗しました。", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }
        catch(DataQueryException l_dqex)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //定時定額買付条件のListをリターンする。
        return l_lisReturn;
    }
    
    /**
     * (get銀行支店名)<BR>
     * 銀行名・支店名を取得する。 <BR>
     * <BR>
     * 金融機@関（銀行）マスタを検索して、銀行名、支店名を取得する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 銀行コード = 引数．銀行コード <BR>
     * 支店コード = 引数．支店コード <BR>
     * <BR>
     * 戻り値： <BR>
     * 　@String[]の1番目のインデックスには銀行名を <BR>
     * 　@2番目のインデックスには支店名をセットする。 <BR>
     * <BR>
     * @@param l_strFinInstitutionCode - (銀行コード)<BR>
     * 銀行コード<BR>
     * @@param l_strFinBranchCode - (支店コード)<BR>
     * 支店コード<BR>
     * @@return String[] <BR>
     * @@throws WEB3BaseException
     */
    public String[] getFinBranchName(
        String l_strFinInstitutionCode,
        String l_strFinBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "getFinBranchName(String, String)";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strReturns = new String[2];
        List l_lisRow = new ArrayList();
        
        //[検索条件]
        //銀行コード = 引数．銀行コード 
        //支店コード = 引数．支店コード
        String l_strCondition =  
            " fin_institution_code = ? and fin_branch_code = ? ";
        Object[] l_objConditionValues = new Object[] {
            l_strFinInstitutionCode,
            l_strFinBranchCode};
        
        try
        {
            //金融機@関（銀行）マスタを検索して、銀行名、支店名を取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRow = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE, 
                l_strCondition, 
                l_objConditionValues);
        }
        catch(DataNetworkException l_dnex)
           {
            log.error("DBへのアクセスに失敗しました。", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
           }        
        catch(DataQueryException l_dqex)
        {
            log.error("DBへのアクセスに失敗しました。", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        
        if(l_lisRow == null || l_lisRow.size() == 0)
        {
            log.debug("テーブルに該当するデータがありません!"); 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        
        //戻り値： 
        //String[]の1番目のインデックスには銀行名を
        //2番目のインデックスには支店名をセットする。
        FinInstitutionBankRow l_row = (FinInstitutionBankRow)l_lisRow.get(0);
        l_strReturns[0] = l_row.getFinInstitutionName();
        l_strReturns[1] = l_row.getFinBranchName();
        
        log.exiting(STR_METHOD_NAME);
        return l_strReturns;
    }


    /**
     * (get定時定額買付条件変更リスト)<BR>
     * 引数の条件に該当する定時定額買付条件変更のリストを取得する。<BR>
     * <BR>
     * 定時定額買付条件変更のリストを返す。<BR>
     * <BR>
     * １）定時定額買付条件変更テーブルを検索し、<BR>
     * 　@　@オブジェクトのListを取得する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@部店コード　@= 引数.部店コード<BR>
     * 　@　@　@口座コード　@= 引数.口座コード<BR>
     * 　@　@　@削除フラグ　@= 「0:FALSE」<BR>
     * <BR>
     * 　@　@　@※引数:検索条件文字列がnull以外の場合、検索条件に<BR>
     * 　@　@　@　@and 引数:検索条件文字列を追加し、引数:検索条件値をバインドする。<BR>
     * <BR>
     * ２）　@定時定額買付条件変更のListをリターンする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@param l_strQueryString - (検索条件文字列)<BR>
     * 検索条件文字列<BR>
     * @@param l_objQueryValues - (検索条件値)<BR>
     * 検索条件値<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     */
    public List getFixedBuyConditionChangeList(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strQueryString,
        Object[] l_objQueryValues) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getFixedBuyConditionChangeList(String, String, String, String, Object[])";
        log.entering(STR_METHOD_NAME);

        //１）定時定額買付条件変更テーブルを検索し、オブジェクトのListを取得する。
        //［検索条件］
        //証券会社コード = 引数.証券会社コード
        //部店コード　@= 引数.部店コード
        //口座コード　@= 引数.口座コード
        //削除フラグ　@= 「0:FALSE」
        //※引数:検索条件文字列がnull以外の場合、
        //検索条件にand 引数:検索条件文字列を追加し、引数:検索条件値をバインドする。
        List l_lisMfFixedBuyingChangeRows = null;
        try
        {
            String l_strMfFixedBuyingChangeQuery =
                " institution_code = ? and branch_code = ? "
                + " and account_code = ? and delete_flag = ? ";

            List l_lisMfFixedBuyingChangeQuerys = new ArrayList();
            l_lisMfFixedBuyingChangeQuerys.add(l_strInstitutionCode);
            l_lisMfFixedBuyingChangeQuerys.add(l_strBranchCode);
            l_lisMfFixedBuyingChangeQuerys.add(l_strAccountCode);
            l_lisMfFixedBuyingChangeQuerys.add(BooleanEnum.FALSE);
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strQueryString))
            {
                l_strMfFixedBuyingChangeQuery +=
                     " and " + l_strQueryString;

                for (int i = 0; i < l_objQueryValues.length; i++)
                {
                    l_lisMfFixedBuyingChangeQuerys.add(l_objQueryValues[i]);
                }
            }

            Object[] l_objMfFixedBuyingChangeQuerys =
                new Object[l_lisMfFixedBuyingChangeQuerys.size()];
            l_lisMfFixedBuyingChangeQuerys.toArray(l_objMfFixedBuyingChangeQuerys);
            l_lisMfFixedBuyingChangeRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingChangeRow.TYPE,
                l_strMfFixedBuyingChangeQuery,
                l_objMfFixedBuyingChangeQuerys);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisMfFixedBuyingChangeRows;
    }

    /**
     * (calc適用開始年月（業務日付）)<BR>
     * 業務日付ベースの適用開始年月を取得する。<BR>
     * <BR>
     * 1) GtlUtils.getTradingSystem().getBizDate()をコールし、業務日付を取得する。<BR>
     * <BR>
     * 2) 定時定額買付締切日引落日計算インスタンスを生成<BR>
     * 　@　@[コンストラクタの引数]<BR>
     * 　@　@証券会社コード：引数.証券会社コード<BR>
     * 　@　@部店コード：引数.部店コード<BR>
     * <BR>
     * 3) 取得した定時定額買付締切日引落日計算オブジェクト.calc通常締切日（WEB）をコール<BR>
     * 　@　@[calc通常締切日（WEB）の引数]<BR>
     * 　@　@指定年月：取得した業務日付<BR>
     * <BR>
     * 4) 取得した業務日付 <= 取得したcalc通常締切日（WEB）の戻り値の場合<BR>
     * <BR>
     * 　@　@4-1) 取得した業務日付の年月をリターンする。<BR>
     * 　@　@　@　@　@※日付は1日とする。<BR>
     * <BR>
     * 5) 上記以外<BR>
     * <BR>
     * 　@　@5-1) 取得した業務日付の年月+1ヶ月をリターンする。<BR>
     * 　@　@　@　@　@※日付は1日とする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateOrderBizdate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcValidStartDateOrderBizdate(String, String)";
        log.entering(STR_METHOD_NAME);

        //1) GtlUtils.getTradingSystem().getBizDate()をコールし、業務日付を取得する。
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();

        //2) 定時定額買付締切日引落日計算インスタンスを生成
        //証券会社コード：引数.証券会社コード
        //部店コード：引数.部店コード
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strInstitutionCode, l_strBranchCode);

        //3) 取得した定時定額買付締切日引落日計算オブジェクト.calc通常締切日（WEB）をコール
        //指定年月：取得した業務日付
        Date l_datUsuallyCloseDate = l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDate(l_datBizDate);

        Date l_datReturn = null;
        //4) 取得した業務日付 <= 取得したcalc通常締切日（WEB）の戻り値の場合
        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datUsuallyCloseDate) <= 0)
        {
            //4-1) 取得した業務日付の年月をリターンする。
            //※日付は1日とする。
            String l_strBizDateYM =
                WEB3DateUtility.formatDate(l_datBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn = WEB3DateUtility.getDate(l_strBizDateYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }
        else
        {
            //5) 上記以外
            //5-1) 取得した業務日付の年月+1ヶ月をリターンする
            //※日付は1日とする。
            l_datBizDate = WEB3DateUtility.addMonth(l_datBizDate, 1);
            String l_strBizDateYM =
                WEB3DateUtility.formatDate(l_datBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn = WEB3DateUtility.getDate(l_strBizDateYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturn;
    }

    /**
     * (calc適用開始年月（現在日時）)<BR>
     * 現在日時ベースの適用開始年月を取得する。<BR>
     * <BR>
     * 1) GtlUtils.getTradingSystem().getSystemTimestamp()をコールし、現在日時を取得する。<BR>
     * <BR>
     * 2) 定時定額買付締切日引落日計算インスタンスを生成<BR>
     * 　@　@[コンストラクタの引数]<BR>
     * 　@　@証券会社コード：引数.証券会社コード<BR>
     * 　@　@部店コード：引数.部店コード<BR>
     * <BR>
     * 3) 取得した定時定額買付締切日引落日計算オブジェクト.calc通常締切日時（WEB）をコール<BR>
     * 　@　@[calc通常締切日時（WEB）の引数]<BR>
     * 　@　@指定年月：取得した現在日時の年月日<BR>
     * <BR>
     * 4) 取得した現在日時 <= 取得したcalc通常締切日時（WEB）の戻り値の場合<BR>
     * <BR>
     * 　@　@4-1) 取得した現在日時の年月をリターンする。<BR>
     * 　@　@　@　@　@※日付は1日とする。<BR>
     * <BR>
     * 5) 上記以外<BR>
     * <BR>
     * 　@　@5-1) 取得した現在日時の年月+1ヶ月をリターンする。<BR>
     * 　@　@　@　@　@※日付は1日とする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     */
    public Date calcValidStartDateCurrentDate(
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcValidStartDateCurrentDate(String, String)";
        log.entering(STR_METHOD_NAME);

        //1) GtlUtils.getTradingSystem().getSystemTimestamp()をコールし、現在日時を取得する。
        Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //2) 定時定額買付締切日引落日計算インスタンスを生成
        //証券会社コード：引数.証券会社コード
        //部店コード：引数.部店コード
        WEB3MutualFixedBuyCloseDateDrawDateCalc l_mutualFixedBuyCloseDateDrawDateCalc =
            new WEB3MutualFixedBuyCloseDateDrawDateCalc(l_strInstitutionCode, l_strBranchCode);

        //3) 取得した定時定額買付締切日引落日計算オブジェクト.calc通常締切日時（WEB）をコール
        //指定年月：取得した現在日時の年月日
        Date l_datUsuallyCloseDateHour =
            l_mutualFixedBuyCloseDateDrawDateCalc.calcUsuallyCloseDateTime(
                WEB3DateUtility.toDay(l_datSystemTimestamp));

        Date l_datReturn = null;
        //4) 取得した現在日時 <= 取得したcalc通常締切日時（WEB）の戻り値の場合 
        if (WEB3DateUtility.compareToSecond(l_datSystemTimestamp, l_datUsuallyCloseDateHour) <= 0)
        {
            //4-1) 取得した現在日時の年月をリターンする。
            //※日付は1日とする。
            String l_strSystemTimestampYM =
                WEB3DateUtility.formatDate(l_datSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn =
                WEB3DateUtility.getDate(l_strSystemTimestampYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }
        else
        {
            //5) 上記以外
            // 5-1) 取得した現在日時の年月+1ヶ月をリターンする。
            //※日付は1日とする。
            l_datSystemTimestamp = WEB3DateUtility.addMonth(l_datSystemTimestamp, 1);
            String l_strSystemTimestampYM =
                WEB3DateUtility.formatDate(l_datSystemTimestamp, WEB3GentradeTimeDef.DATE_FORMAT_YM);
            l_datReturn =
                WEB3DateUtility.getDate(l_strSystemTimestampYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturn;
    }

    /**
     * (validate引落口座登録)<BR>
     * 定時定額買付引落口座が登録されているかチェックする。<BR>
     * <BR>
     * １）以下の条件で、定時定額買付引落口座テーブルを検索する。<BR>
     * <BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@部店コード　@= 引数.部店コード<BR>
     * 　@　@　@口座コード　@= 引数.口座コード<BR>
     * <BR>
     * ※ レコードが存在しない場合に例外をスローする。<BR>
     * 　@　@「定時定額買付引落口座未登録エラー」<BR>
     * 　@　@　@class　@　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@　@:　@BUSINESS_ERROR_03099<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     */
    public void validateDrawAccountRegist(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateDrawAccountRegist(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）以下の条件で、定時定額買付引落口座テーブルを検索する。
        try
        {
            String l_strQuery =
                " institution_code = ? and branch_code = ?  and account_code = ? ";
            Object[] l_objQuerys = new Object[]{
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisMfFixedBuyingDrawAccountRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingDrawAccountRow.TYPE,
                l_strQuery,
                l_objQuerys);

            if (l_lisMfFixedBuyingDrawAccountRows.isEmpty())
            {
                //※ レコードが存在しない場合に例外をスローする。
                //「定時定額買付引落口座未登録エラー」
                log.debug("定時定額買付引落口座未登録エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03099,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "定時定額買付引落口座未登録エラー。");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc賞与確定引落金額)<BR>
     * 賞与確定引落金額を取得する。<BR>
     * <BR>
     * 1) this.is賞与月をコールする。<BR>
     * 　@　@[is賞与月の引数]<BR>
     * 　@　@　@証券会社コード：引数.定時定額買付条件Row.証券会社コード<BR>
     * 　@　@　@指定年月：引数.定時定額買付条件Row.口座引落年月<BR>
     * <BR>
     * 2) this.is賞与月がfalseの場合、nullをリターン<BR>
     * <BR>
     * 3) this.is賞与月がtrueの場合<BR>
     * <BR>
     * 　@　@3)-1) 定時定額買付条件変更テーブルを検索する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@証券会社コード = 引数.定時定額買付条件Row.証券会社コード and <BR>
     * 　@　@　@部店コード　@= 引数.定時定額買付条件Row.部店コード and<BR>
     * 　@　@　@口座コード　@= 引数.定時定額買付条件Row.口座コード and<BR>
     * 　@　@　@銘柄コード　@= 引数.定時定額買付条件Row.銘柄コード and<BR>
     * 　@　@　@適用開始年月　@= 引数.定時定額買付条件Row.口座引落年月 and<BR>
     * 　@　@　@削除フラグ = 0:FALSE<BR>
     * <BR>
     * 　@　@3)-2)レコードが取得できた場合、<BR>
     * 　@　@　@取得した定時定額買付条件変更Row.確定引落買付金額（積み増し）をリターンする。<BR>
     * <BR>
     * 　@　@3)-3)レコードが存在しない場合、nullをリターンする。<BR>
     * <BR>
     * @@param l_mfFixedBuyingCondRow - (定時定額買付条件Row)<BR>
     * 定時定額買付条件Row<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String calcPrizeAndDecisioDrawAmount(
        MfFixedBuyingCondRow l_mfFixedBuyingCondRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcPrizeAndDecisioDrawAmount(MfFixedBuyingCondRow)";
        log.entering(STR_METHOD_NAME);

        if (l_mfFixedBuyingCondRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //1) this.is賞与月をコールする。
        //証券会社コード：引数.定時定額買付条件Row.証券会社コード
        //指定年月：引数.定時定額買付条件Row.口座引落年月
        boolean l_blnIsPrizeAndMoon = this.isPrizeAndMonth(
            l_mfFixedBuyingCondRow.getInstitutionCode(),
            l_mfFixedBuyingCondRow.getDrawDate());

        //2) this.is賞与月がfalseの場合、nullをリターン
        if (!l_blnIsPrizeAndMoon)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //3) this.is賞与月がtrueの場合
        //3)-1) 定時定額買付条件変更テーブルを検索する
        //証券会社コード = 引数.定時定額買付条件Row.証券会社コード and
        //部店コード　@= 引数.定時定額買付条件Row.部店コード and
        //口座コード　@= 引数.定時定額買付条件Row.口座コード and
        //銘柄コード　@= 引数.定時定額買付条件Row.銘柄コード and
        //適用開始年月　@= 引数.定時定額買付条件Row.口座引落年月 and
        //削除フラグ = 0:FALSE
        List l_lisMfFixedBuyingChangeRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strMfFixedBuyingChangeQuery =
                " institution_code = ? "
                + " and branch_code = ? "
                + " and account_code = ? "
                + " and product_code = ? "
                + " and to_char(valid_start_date, 'yyyyMM') = ? "
                + " and delete_flag = ? ";

            List l_lisMfFixedBuyingChangeQuerys = new ArrayList();
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getInstitutionCode());
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getBranchCode());
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getAccountCode());
            l_lisMfFixedBuyingChangeQuerys.add(l_mfFixedBuyingCondRow.getProductCode());
            l_lisMfFixedBuyingChangeQuerys.add(
                WEB3DateUtility.formatDate(
                    l_mfFixedBuyingCondRow.getDrawDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YM));
            l_lisMfFixedBuyingChangeQuerys.add(BooleanEnum.FALSE.intValue() + "");

            Object[] l_objMfFixedBuyingChangeQuerys = new Object[l_lisMfFixedBuyingChangeQuerys.size()];
            l_lisMfFixedBuyingChangeQuerys.toArray(l_objMfFixedBuyingChangeQuerys);
            l_lisMfFixedBuyingChangeRows = l_queryProcessor.doFindAllQuery(
                MfFixedBuyingChangeRow.TYPE,
                l_strMfFixedBuyingChangeQuery,
                l_objMfFixedBuyingChangeQuerys);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisMfFixedBuyingChangeRows.isEmpty())
        {
            //3)-3)レコードが存在しない場合、nullをリターンする。
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //3)-2)レコードが取得できた場合、取得した定時定額買付条件変更Row.確定引落買付金額（積み増し）をリターンする。
        MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
            (MfFixedBuyingChangeRow)l_lisMfFixedBuyingChangeRows.get(0);
        if (!l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3StringTypeUtility.formatNumber(
                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (is賞与月)<BR>
     * 指定した年月が賞与月かどうか判定する。<BR>
     * <BR>
     * 1)　@システムプリファ@レンステーブルを以下の条件で検索する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@　@名称（環境変数名）：XX_SBS_BONUS_MONTH<BR>
     * 　@　@　@※XXは引数.証券会社コード<BR>
     * <BR>
     * 2)　@取得したシステムプリファ@レンスRow.値をカンマ区切り毎に分割してループする。<BR>
     * <BR>
     * 　@　@2)-1)　@引数.指定年月の月(MM)とカンマで区切られた値が一致したら、<BR>
     * 　@　@　@　@trueをリターンする。<BR>
     * <BR>
     * 3)それ以外の場合、falseをリターンする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_datSelectYM - (指定年月)<BR>
     * 指定年月<BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isPrizeAndMonth(
        String l_strInstitutionCode,
        Date l_datSelectYM) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isPrizeAndMonth(String, Date)";
        log.entering(STR_METHOD_NAME);
        //1) システムプリファ@レンステーブルを以下の条件で検索する。
        //名称（環境変数名）：XX_SBS_BONUS_MONTH
        //※XXは引数.証券会社コード
        SystemPreferencesPK l_systemPreferencesPK =
            new SystemPreferencesPK(
                l_strInstitutionCode + WEB3SystemPreferencesNameDef.SBS_BONUS_MONTH);
        try
        {
            SystemPreferencesRow l_systemPreferencesRow =
                SystemPreferencesDao.findRowByPk(l_systemPreferencesPK);

            //2) 取得したシステムプリファ@レンスRow.値をカンマ区切り毎に分割してループする。
            String l_strValue = l_systemPreferencesRow.getValue();

            //2)-1)  引数.指定年月の月(MM)とカンマで区切られた値が一致したら、trueをリターンする。
            String l_strSelectYM =
                WEB3DateUtility.formatDate(l_datSelectYM, WEB3GentradeTimeDef.DATE_FORMAT_YM);

            String[] l_strSelectYMs = l_strValue.split(",");
            String l_strSelectMonth = l_strSelectYM.substring(4, 6);

            for (int i = 0; i < l_strSelectYMs.length; i++)
            {
                if (l_strSelectYMs[i] != null
                    && l_strSelectYMs[i].equals(l_strSelectMonth))
                {
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //3)それ以外の場合、falseをリターンする。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (sort定時定額買付条件一覧)<BR>
     * 指定されたソートキー、昇降順に基づいて投信定時定額買付条件行のソートを行う。<BR>
     * <BR>
     * １)　@ArrayListを作成<BR>
     * <BR>
     * ２)　@Comparatorを生成<BR>
     * <BR>
     * 　@・口座引落年月Comparatorを生成 (昇順)<BR>
     * 　@・銘柄表示順序Comparatorを生成 (昇順)<BR>
     * 　@・投信銘柄カテゴリーコードComparatorを生成 (昇順)<BR>
     * 　@・銘柄コードComparatorを生成 (昇順)<BR>
     * <BR>
     * ３)ComparatorをArrayListに追加<BR>
     * <BR>
     * ４)　@ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * ５)　@Comparatorの配列順のソート処理<BR>
     * (web3-common)WEB3ArraysUtility.sort(引数:投信定時定額買付条件行、Comparator[])<BR>
     * <BR>
     * ６)　@ソートされた投信定時定額買付条件行の配列を返却<BR>
     * 昇降順に基づいて投信定時定額買付条件行のソートを行う。<BR>
     * @@param l_mutualFixedBuyConditionUnits - (投信定時定額買付条件行[] )<BR>
     * 投信定時定額買付条件行 []<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]<BR>
     * @@throws WEB3BaseException
     */
    public WEB3MutualFixedBuyConditionUnit[] sortFixedBuyConditionList(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "sortFixedBuyConditionList(WEB3MutualFixedBuyConditionUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_mutualFixedBuyConditionUnits == null
            || l_mutualFixedBuyConditionUnits.length == 0)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１)ArrayListを作成
        ArrayList l_lisComparator = new ArrayList();

        // ２)　@Comparatorを生成
        //   ・口座引落年月Comparatorを生成 (昇順)
        WEB3MutualAccountDrawYMComparator l_accountDrawYMComparator =
            new WEB3MutualAccountDrawYMComparator(WEB3AscDescDef.ASC);

        // 　@・銘柄表示順序Comparatorを生成 (昇順)
        WEB3MutualDisplayOrderComparator l_displayOrderComparator =
            new WEB3MutualDisplayOrderComparator(WEB3AscDescDef.ASC);

        // 　@・投信銘柄カテゴリーコードComparatorを生成 (昇順)
        WEB3MutualProductCategoryCodeComparator l_productCategoryCodeComparator =
            new WEB3MutualProductCategoryCodeComparator(WEB3AscDescDef.ASC);

        // 　@・銘柄コードComparatorを生成 (昇順)
        WEB3MutualProductCodeComparator l_productCodeComparator =
            new WEB3MutualProductCodeComparator(WEB3AscDescDef.ASC);

        // ３)ComparatorをArrayListに追加
        l_lisComparator.add(l_accountDrawYMComparator);
        l_lisComparator.add(l_displayOrderComparator);
        l_lisComparator.add(l_productCategoryCodeComparator);
        l_lisComparator.add(l_productCodeComparator);

        // ４)　@ArrayListからComparatorの配列を作成
        Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
        l_lisComparator.toArray(l_comparators);

        // ５)　@Comparatorの配列順のソート処理
        // (web3-common)WEB3ArraysUtility.sort(引数:投信定時定額買付条件行、Comparator[])
        WEB3ArraysUtility.sort(l_mutualFixedBuyConditionUnits, l_comparators);

        // ６)　@ソートされた投信定時定額買付条件行の配列を返却
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }
}
@
