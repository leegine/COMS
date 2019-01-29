head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金商法@共通(WEB3AdminFPTCommon.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 何文敏 (中訊) 新規作成
Revision History : 2007/10/09 何文敏 (中訊) モデルNo.002
Revision History : 2007/12/07 武波 (中訊) モデルNo.013
Revision History : 2008/01/28 武波 (中訊) モデルNo.023,No.028
*/

package webbroker3.docadmin;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.docadmin.define.WEB3AdminFPTDocumentCheckDivDef;
import webbroker3.docadmin.message.WEB3FPTBatoProductCodeAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentCategoryDetailsInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (金商法@共通)<BR>
 * 金商法@共通クラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminFPTCommon
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTCommon.class);

    /**
     * (金商法@共通)<BR>
     * ディフォルトコンストラクタ<BR>
     * @@roseuid 46F8DA35024D
     */
    public WEB3AdminFPTCommon()
    {

    }

    /**
     * (get銘柄名)<BR>
     * 銘柄名を取得する。<BR>
     * <BR>
     * <BR>
     * １）　@引数.書面チェック区分 == IPOの場合<BR>
     * <BR>
     *   １-１）　@this.getIPO銘柄名()をコールする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@証券会社コード：引数.証券会社.get証券会社コード()の戻り値<BR>
     * 　@　@　@　@銘柄コード：引数.銘柄コード<BR>
     * <BR>
     *   １-２）　@this.getIPO銘柄名()の戻り値 != (null or "")の場合、<BR>
     * 　@　@戻り値を返却する。<BR>
     * <BR>
     *   １-３）　@this.getIPO銘柄名()の戻り値 == (null or "") の場合、<BR>
     * 　@　@""を返却する。<BR>
     * <BR>
     * <BR>
     * ２）　@引数.書面チェック区分 == 投信の場合<BR>
     * <BR>
     *   ２-１）　@this.get投信銘柄名()をコールする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@　@銘柄コード：引数.銘柄コード<BR>
     * <BR>
     *   ２-２）　@this.get投信銘柄名()の戻り値 != (null or "") の場合、<BR>
     *   　@　@戻り値を返却する。<BR>
     * <BR>
     *   ２-３）　@this.get投信銘柄名()の戻り値 == (null or "") の場合、"" を返却する。<BR>
     * <BR>
     * <BR>
     * ３）　@引数.書面チェック区分 == FXの場合、"" を返却する。<BR>
     * <BR>
     * <BR>
     * ４）　@引数.書面チェック区分 == 金商法@の場合、"" を返却する。<BR>
     * <BR>
     * ５）　@引数.書面チェック区分が上記以外の場合、"" を返却する。<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strDocumentCheckDiv - (書面チェック区分)<BR>
     * 書面チェック区分<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46F3A07A0085
     */
    public String getProductName(
        Institution l_institution,
        String l_strProductCode,
        String l_strDocumentCheckDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getProductName(WEB3GentradeInstitution ,String , String)";
        log.entering(STR_METHOD_NAME);

        if (l_institution == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １） 引数.書面チェック区分 == IPOの場合
        if (WEB3AdminFPTDocumentCheckDivDef.IPO.equals(l_strDocumentCheckDiv))
        {
            //  １-１） this.getIPO銘柄名()をコールする。
            // 証券会社コード：引数.証券会社.get証券会社コード()の戻り値
            String l_strInstitutionCode = l_institution.getInstitutionCode();
            String l_strIPOProductName = this.getIPOStandardName(l_strInstitutionCode, l_strProductCode);

            if (WEB3StringTypeUtility.isNotEmpty(l_strIPOProductName))
            {
                // １-２） this.getIPO銘柄名()の戻り値 != (null or "") の場合、戻り値を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_strIPOProductName;
            }
            else
            {
                // １-３） this.getIPO銘柄名()の戻り値 == (null or "") の場合、"" を返却する。
                log.exiting(STR_METHOD_NAME);
                return "";
            }
        }
        else if (WEB3AdminFPTDocumentCheckDivDef.MUTUAL_FUND.equals(l_strDocumentCheckDiv))
        {
            // ２） 引数.書面チェック区分 == 投信の場合
            // ２-１） this.get投信銘柄名()をコールする
            String l_strStandardProductName =
                this.getMFProductName(l_institution, l_strProductCode);

            if (WEB3StringTypeUtility.isNotEmpty(l_strStandardProductName))
            {
                // ２-２） this.get投信銘柄名()の戻り値 != (null or "") の場合、戻り値を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_strStandardProductName;
            }
            else
            {
                // ２-３） this.get投信銘柄名()の戻り値 == (null or "") の場合、"" を返却する。
                log.exiting(STR_METHOD_NAME);
                return "";
            }
        }
        else if (WEB3AdminFPTDocumentCheckDivDef.FX.equals(l_strDocumentCheckDiv))
        {
            // ３） 引数.書面チェック区分 == FXの場合、"" を返却する。
            log.exiting(STR_METHOD_NAME);
            return "";
        }

        else if (WEB3AdminFPTDocumentCheckDivDef.FPT.equals(l_strDocumentCheckDiv))
        {
            // ４） 引数.書面チェック区分 == 金商法@の場合、"" を返却する。
            log.exiting(STR_METHOD_NAME);
            return "";
        }
        else
        {
            // ５） 引数.書面チェック区分が上記以外の場合、"" を返却する。
            log.exiting(STR_METHOD_NAME);
            return "";
        }
    }

    /**
     * (getIPO銘柄名)<BR>
     * IPO銘柄テーブルより銘柄名を取得する。<BR>
     * <BR>
     * <BR>
     * １）　@IPO銘柄テーブルより以下条件で検索を行う。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@銘柄コード = 引数.銘柄コード<BR>
     * 　@　@削除フラグ = 0<BR>
     * 　@　@作成日時 の降順でソート<BR>
     * <BR>
     * 　@　@作成日時 = 上記条件を全て満たすレコード中で一番新しい日付<BR>
     * <BR>
     * ２）　@１）　@の検索結果0番目のレコードの銘柄名を取得し、返却する。<BR>
     * <BR>
     * ３）　@１）　@で検索結果が0の場合、nullを返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46F8A913001E
     */
    private String getIPOStandardName(String l_strInstitutionCode, String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getIPOStandardName(String, String)";
        log.entering(STR_METHOD_NAME);

        // １） IPO銘柄テーブルより以下条件で検索を行う。
        List l_lisRows = null;
        try
        {
            // IPO銘柄テーブルより以下条件で検索を行う。
            String l_strWhereClause =
                " institution_code = ? and product_code = ? and delete_flag = ? ";

     	    //[条件]
            //証券会社コード = 引数.証券会社コード
            //銘柄コード = 引数.銘柄コード
            //削除フラグ = 0
            //作成日時 の降順でソート
            Object[] l_objBindVars = {l_strInstitutionCode, l_strProductCode, BooleanEnum.FALSE};
            String l_strSortCond = " created_timestamp desc ";

            // 以下条件で書面区分管理テーブルより検索を行う。
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IpoProductRow.TYPE,
                    l_strWhereClause,
                    l_strSortCond,
                    null,
                    l_objBindVars);
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

        //  ３）１） で検索結果が0の場合、nullを返却する。
        int l_intSize = l_lisRows.size();
        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        // ２） １） の検索結果0番目のレコードの銘柄名を取得し、返却する。
        else
        {
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_lisRows.get(0);
            String l_strIPOProductName = l_ipoProductRow.getStandardName();

            log.exiting(STR_METHOD_NAME);
            return l_strIPOProductName;
        }
    }

    /**
     * (get投信銘柄名)<BR>
     * プロダクトマネージャを利用し、投信銘柄名を取得する。<BR>
     * <BR>
     * <BR>
     * １）　@投信のトレーディングモジュールを取得する。<BR>
     * <BR>
     * ２）　@プロダクトマネージャを取得する。<BR>
     * <BR>
     * ３） プロダクトマネージャ.get投信銘柄()をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@証券会社： 引数.証券会社<BR>
     * 　@　@　@銘柄コード： 引数.銘柄コード<BR>
     * <BR>
     * ４）　@銘柄オブジェクトが取得できた場合、<BR>
     * 　@　@　@銘柄オブジェクト.getDataSourceObject().get銘柄名()を返却する。<BR>
     * <BR>
     * ５）　@銘柄オブジェクトが取得できなかった場合、nullを返却する。<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社オブジェクト<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46F8AD2701BF
     */
    private String getMFProductName(
        Institution l_institution, String l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMFProductName(Institution, String)";
        log.entering(STR_METHOD_NAME);

        // プロダクトマネージャを利用し、投信銘柄名を取得する。
        // １） 投信のトレーディングモジュールを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        // ２） プロダクトマネージャを取得する。
        WEB3MutualFundProductManager l_productManager =
            (WEB3MutualFundProductManager)l_tradingModule.getProductManager();

        // ３） プロダクトマネージャ.get投信銘柄()をコールする。
        // 証券会社： 引数.証券会社
        // 銘柄コード： 引数.銘柄コード
        MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                l_productManager.getMutualFundProduct(l_institution, l_strProductCode);

            // ４） 銘柄オブジェクトが取得できた場合、
            // 銘柄オブジェクト.getDataSourceObject().get銘柄名()を返却する。
            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            String l_strStandardName = l_mutualFundProductRow.getStandardName();

            log.exiting(STR_METHOD_NAME);
            return l_strStandardName;
        }
        catch (NotFoundException l_ex)
        {
            // ５） 銘柄オブジェクトが取得できなかった場合、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (getシステムプリファ@レンス)<BR>
     * システムプリファ@レンスよりレコードを取得する。<BR>
     * <BR>
     * １） 引数.環境変数名を条件に検索を行う。<BR>
     * <BR>
     * ２） レコードが取得できた場合、値を返却する。<BR>
     * <BR>
     * ３） レコードが取得できない場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_strPreference - (環境変数名)<BR>
     * 環境変数名<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public static String getSystemPreferences(String l_strPreference) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSystemPreferences(String)";
        log.entering(STR_METHOD_NAME);

        SystemPreferencesRow l_systemPreferencerow;
        try
        {
            //１） 引数.環境変数名を条件に検索を行う。
            l_systemPreferencerow = (SystemPreferencesRow)SystemPreferencesDao.findRowByName(l_strPreference);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_systemPreferencerow != null)
        {
            //２） レコードが取得できた場合、値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_systemPreferencerow.getValue();
        }
        else
        {
            //３） レコードが取得できない場合は、nullを返却する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get書面種類詳細情報)<BR>
     * 書面種類管理情報、及び対応する電子鳩銘柄コード管理情報を取得し、<BR>
     * 書面種類詳細情報を作成する。<BR>
     * <BR>
     * <BR>
     * １）　@書面区分管理オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@[指定する引数]<BR>
     * 　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@部店コード = 引数.部店コード<BR>
     * 　@　@　@書面区分 = 引数.書面区分<BR>
     * 　@　@　@書面種類コード = 引数.書面種類コード<BR>
     * <BR>
     * <BR>
     * ２）　@書面区分管理情報を取得する。<BR>
     * 　@　@　@書面区分管理#get書面区分管理一覧()をコールする。<BR>
     * <BR>
     * <BR>
     * ３）　@ArrayListオブジェクトの生成<BR>
     * <BR>
     * <BR>
     * ４）　@２）　@で取得した書面区分管理情報配列の長さ分、Loop処理<BR>
     * 　@４-１）　@電子鳩銘柄コード管理オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@[指定する引数]<BR>
     * 　@　@　@　@証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@　@部店コード = 引数.部店コード<BR>
     * 　@　@　@　@書面区分 = 書面区分管理情報[index].書面区分<BR>
     * 　@　@　@　@電子鳩銘柄コード = 書面区分管理情報[index].書面種類コード<BR>
     * <BR>
     * 　@４-２）　@電子鳩銘柄コード管理情報を取得する。<BR>
     * 　@　@　@電子鳩銘柄コード管理#電子鳩銘柄コード管理一覧()をコールする。<BR>
     * 　@　@　@戻り値配列の長さ == 0 の場合は、「電子鳩銘柄コード管理テーブルにレコードが存在しません」例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02999<BR>
     * <BR>
     * <BR>
     * 　@４-３）　@書面種類詳細情報オブジェクトを生成し、以下をセットする。<BR>
     * <BR>
     * 　@　@　@　@書面区分管理情報 = 書面区分管理情報[index]<BR>
     * 　@　@　@　@電子鳩銘柄コード管理情報 = 電子鳩銘柄コード管理#電子鳩銘柄コード管理一覧()の戻り値<BR>
     * <BR>
     * 　@４-４）　@書面種類詳細情報オブジェクトをListにadd()<BR>
     * <BR>
     * <BR>
     * ５）　@ArrayListオブジェクトを書面種類詳細情報[]に変換<BR>
     * <BR>
     * <BR>
     * ６）　@５）で変換した値をreturn<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strDocumentDiv - (書面区分)<BR>
     * 書面区分<BR>
     * @@param l_strDocumentCategory - (書面種類コード)<BR>
     * 書面種類コード<BR>
     * @@return WEB3FPTDocumentCategoryDetailsInfoUnit[]
     * @@throws WEB3BaseException
     */
    public static WEB3FPTDocumentCategoryDetailsInfoUnit[] getDocumentCategoryDetailsInfoUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strDocumentDiv,
        String l_strDocumentCategory) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentCategoryDetailsInfoUnit(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 書面区分管理オブジェクトを生成する。
        //証券会社コード = 引数.証券会社コード
        //部店コード = 引数.部店コード
        //書面区分 = 引数.書面区分
        //書面種類コード = 引数.書面種類コード
        WEB3AdminFPTDocDivManagement l_docDivManagement =
            new WEB3AdminFPTDocDivManagement(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strDocumentDiv,
                l_strDocumentCategory);

        //２） 書面区分管理情報を取得する。
        //書面区分管理#get書面区分管理一覧()をコールする。
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivAdminInfoUnits =
            l_docDivManagement.getDocDivManagementLists();

        //３）　@ArrayListオブジェクトの生成
        List l_lisDocumentCategoryDetailsInfoUnits = new ArrayList();

        //４） ２） で取得した書面区分管理情報配列の長さ分、Loop処理
        for (int i = 0; i < l_documentDivAdminInfoUnits.length; i++)
        {
            //４-１） 電子鳩銘柄コード管理オブジェクトを生成する。
            //証券会社コード = 引数.証券会社コード
            //部店コード = 引数.部店コード
            //書面区分 = 書面区分管理情報[index].書面区分
            //電子鳩銘柄コード = 書面区分管理情報[index].書面種類コード
            WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
                new WEB3AdminFPTBatoProductCodeManagement(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_documentDivAdminInfoUnits[i].documentDiv,
                    l_documentDivAdminInfoUnits[i].documentCategory);

            //４-２） 電子鳩銘柄コード管理情報を取得する。
            //電子鳩銘柄コード管理#電子鳩銘柄コード管理一覧()をコールする。
            //戻り値配列の長さ == 0 の場合は、「電子鳩銘柄コード管理テーブルにレコードが存在しません」例外をスローする。
            WEB3FPTBatoProductCodeAdminInfoUnit[] l_batoProductCodeAdminInfoUnits =
                l_batoProductCodeManagement.getBatoProductCodeAdminInfoUnit();
            int l_intBatoProductCodeAdminInfoUnit = l_batoProductCodeAdminInfoUnits.length;
            if (l_intBatoProductCodeAdminInfoUnit == 0)
            {
                log.debug("電子鳩銘柄コード管理テーブルにレコードが存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02999,
                    WEB3AdminFPTCommon.class.getName() + "." + STR_METHOD_NAME,
                    "電子鳩銘柄コード管理テーブルにレコードが存在しません。");
            }

            //４-３） 書面種類詳細情報オブジェクトを生成し、以下をセットする。
            WEB3FPTDocumentCategoryDetailsInfoUnit l_documentCategoryDetailsInfoUnit =
                new WEB3FPTDocumentCategoryDetailsInfoUnit();

            //書面区分管理情報 = 書面区分管理情報[index]
            l_documentCategoryDetailsInfoUnit.documentDivList = l_documentDivAdminInfoUnits[i];

            //電子鳩銘柄コード管理情報 = 電子鳩銘柄コード管理#電子鳩銘柄コード管理一覧()の戻り値
            l_documentCategoryDetailsInfoUnit.batoProductCodeAdminInfo = l_batoProductCodeAdminInfoUnits;

            //４-４）　@書面種類詳細情報オブジェクトをListにadd()
            l_lisDocumentCategoryDetailsInfoUnits.add(l_documentCategoryDetailsInfoUnit);
        }

        //５）　@ArrayListオブジェクトを書面種類詳細情報[]に変換
        WEB3FPTDocumentCategoryDetailsInfoUnit[] l_documentCategoryDetailsInfoUnits =
            new WEB3FPTDocumentCategoryDetailsInfoUnit[l_lisDocumentCategoryDetailsInfoUnits.size()];

        l_lisDocumentCategoryDetailsInfoUnits.toArray(l_documentCategoryDetailsInfoUnits);

        //６）　@５）で変換した値をreturn
        log.exiting(STR_METHOD_NAME);
        return l_documentCategoryDetailsInfoUnits;
    }
}
@
