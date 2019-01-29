head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointProductManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント商品マネージャImpl(WEB3PointProductManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.data.PointCategoryMasterDao;
import webbroker3.point.data.PointCategoryMasterParams;
import webbroker3.point.data.PointCategoryMasterRow;
import webbroker3.point.data.PointPremiumMasterDao;
import webbroker3.point.data.PointPremiumMasterParams;
import webbroker3.point.data.PointPremiumMasterRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (ポイント商品マネージャImpl)<BR>
 * ポイント商品マネージャ実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointProductManagerImpl implements WEB3PointProductManager 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointProductManagerImpl.class);
    
    /**
     * (getカテゴリー)<BR>
     * 証券会社コード、カテゴリー番号から、ポイントカテゴリーオブジェクトを取得する。<BR>
     * <BR>
     * ポイントカテゴリーマスターDAOにて、引数を条件とするレコードを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory
     * @@roseuid 418F3D1E0136
     */
    public WEB3PointCategory getCategory(String l_strInstitutionCode, String l_strCategoryNo) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCategory(String , String )";
        log.entering(STR_METHOD_NAME);
        
        PointCategoryMasterParams l_params = null;
        int l_intCategoryNo = 0;
        
        if (!WEB3StringTypeUtility.isInteger(l_strCategoryNo))
        {
            String l_strMessage = "parameter error! categoryNo = " + l_strCategoryNo;
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        l_intCategoryNo = Integer.parseInt(l_strCategoryNo);
        try
        {
            l_params = new PointCategoryMasterParams(PointCategoryMasterDao.findRowByPk(l_strInstitutionCode, l_intCategoryNo));//DataFindException,DataQueryException,DataNetworkException
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
        return new WEB3PointCategory(l_params);
    }
    
    /**
     * (getカテゴリー)<BR>
     * 証券会社コードから、その証券会社が扱っているカテゴリーの配列を取得する。<BR>
     * <BR>
     * １）以下の条件でポイントカテゴリーマスターテーブルを検索する。<BR>
     * <BR>
     * 検索条件： 証券会社コード = 引数.証券会社コード<BR>
     * order by： カテゴリー番号<BR>
     * <BR>
     * ２）取得したレコードを配列にして、返却する。<BR>
     *    ※検索の結果0件だった場合は、要素0の配列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory[]
     * @@roseuid 418F3D1E0165
     */
    public WEB3PointCategory[] getCategories(String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCategories(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "institution_code = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointCategoryMasterRow.TYPE,
                l_strWhere,
                "category_no",
                null,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        WEB3PointCategory[] l_categories = null;
        if (l_lisRows != null)
        {
            int l_intCategoryCount = l_lisRows.size();
            l_categories = new WEB3PointCategory[l_intCategoryCount];
            for (int i = 0; i < l_intCategoryCount; i++)
            {
                l_categories[i] = new WEB3PointCategory((PointCategoryMasterParams)l_lisRows.get(i)); 
            }
        }
        else
        {
            l_categories = new WEB3PointCategory[0];
        }
        
        log.exiting(STR_METHOD_NAME);     
        return l_categories;
    }
    
    /**
     * (get景品)<BR>
     * 証券会社コード、景品番号から、ポイント景品オブジェクトを取得する。<BR>
     * <BR>
     * ポイント景品マスターDAOにて、引数を条件とするレコードを取得し返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium
     * @@roseuid 418F3D1E0194
     */
    public WEB3PointPremium getPremium(String l_strInstitutionCode, String l_strPremiumNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPremium(String , String )";
        log.entering(STR_METHOD_NAME);
        
        PointPremiumMasterParams l_params = null;
        try
        {
            l_params = new PointPremiumMasterParams(PointPremiumMasterDao.findRowByPk(l_strInstitutionCode, l_strPremiumNo));//DataFindException,DataQueryException,DataNetworkException
        }
        catch (DataFindException l_e)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
        return new WEB3PointPremium(l_params);
    }
    
    /**
     * (get景品)<BR>
     * 証券会社コード、カテゴリー番号から、ポイント景品の配列を取得する。<BR>
     * <BR>
     * １）以下の条件でポイント景品マスターテーブルを検索する。<BR>
     * <BR>
     * 検索条件： 証券会社コード = 引数.証券会社コード and<BR>
     *               カテゴリー番号 = 引数.カテゴリー番号<BR>
     * order by： 景品番号<BR>
     * <BR>
     * ２）取得したレコードを配列にして、返却する。<BR>
     *    ※検索の結果0件だった場合は、要素0の配列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 418F3D1E01C3
     */
    public WEB3PointPremium[] getPremiums(String l_strInstitutionCode, String l_strCategoryNo)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getPremiums(String , String )";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "institution_code = ? AND category_no = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strCategoryNo}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                "premium_no",
                null,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        WEB3PointPremium[] l_premiums = null;
        if (l_lisRows != null)
        {
            int l_intPremiumCount = l_lisRows.size();
            l_premiums = new WEB3PointPremium[l_intPremiumCount];
            for (int i = 0; i < l_intPremiumCount; i++)
            {
                l_premiums[i] = new WEB3PointPremium((PointPremiumMasterParams)l_lisRows.get(i)); 
            }
        }
        else
        {
            l_premiums = new WEB3PointPremium[0];
        }
        
        log.exiting(STR_METHOD_NAME);     
        return l_premiums;
    }
    
    /**
     * (get取扱可能景品)<BR>
     * 証券会社コード、カテゴリー番号から、現在取扱可能なポイント景品の配列を取得する。<BR>
     * <BR>
     * １）以下の条件でポイント景品マスターテーブルを検索する。<BR>
     * <BR>
     * 検索条件： 証券会社コード = 引数.証券会社コード and<BR>
     *               カテゴリー番号 = 引数.カテゴリー番号 and<BR>
     *               現在日時 >= 提供開始日時 and<BR>
     *               現在日時 < 提供終了日時<BR>
     * order by： 景品番号<BR>
     * <BR>
     * ２）取得したレコードを配列にして、返却する。<BR>
     *    ※検索の結果0件だった場合は、要素0の配列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 41A43B15015F
     */
    public WEB3PointPremium[] getHandingPossiblePremium(String l_strInstitutionCode, String l_strCategoryNo) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getHandingPossiblePremium(String , String )";
        log.entering(STR_METHOD_NAME);
        
        String l_strWhere = "institution_code = ? AND category_no = ? AND start_date_time <= ? AND end_date_time > ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strCategoryNo, GtlUtils.getSystemTimestamp(), GtlUtils.getSystemTimestamp()}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                "premium_no",
                null,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        WEB3PointPremium[] l_premiums = null;
        if (l_lisRows != null)
        {
            int l_intPremiumCount = l_lisRows.size();
            l_premiums = new WEB3PointPremium[l_intPremiumCount];
            for (int i = 0; i < l_intPremiumCount; i++)
            {
                l_premiums[i] = new WEB3PointPremium((PointPremiumMasterParams)l_lisRows.get(i)); 
            }
        }
        else
        {
            l_premiums = new WEB3PointPremium[0];
        }
        
        log.exiting(STR_METHOD_NAME);     
        return l_premiums;
    }
    
    /**
     * (validateカテゴリー内容)<BR>
     * カテゴリー内容のチェックを行う。<BR>
     * <BR>
     * １）カテゴリー名チェック<BR>
     * <BR>
     *    引数.カテゴリー名.length() > 80 or 引数.カテゴリー名 = null <BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01886<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01705<BR>
     * <BR>
     * ２）概要チェック<BR>
     * <BR>
     *    引数.概要.length() > 400<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01706<BR>
     * <BR>
     * @@param l_strCategoryName - (カテゴリー名)<BR>
     * カテゴリー名<BR>
     * 
     * @@param l_strOutline - (概要)<BR>
     * 概要<BR>
     * @@roseuid 4191859002B7
     */
    public void validateCategorySpec(String l_strCategoryName, String l_strOutline) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateCategorySpec(String , String )";
        log.entering(STR_METHOD_NAME);
        
        // １）カテゴリー名チェック
        if (l_strCategoryName == null)
        {
            String l_strMessage = "categoryName length error! categoryName = " + l_strCategoryName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01886, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (WEB3StringTypeUtility.getByteLength(l_strCategoryName) > 80)
        {
            String l_strMessage = "categoryName length error! categoryName = " + l_strCategoryName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01705, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // ２）概要チェック
        if (l_strOutline != null && WEB3StringTypeUtility.getByteLength(l_strOutline) > 400)
        {
            String l_strMessage = "outline length error! outline = " + l_strOutline;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01706, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (saveNewカテゴリー)<BR>
     * 新規のカテゴリーを追加(insert)する。<BR>
     * <BR>
     * １）カテゴリー番号を新規に採番する。 <BR>
     *    引数.カテゴリー.setNewカテゴリー番号()をコールする。 <BR>
     * <BR>
     * ２）行オブジェクト取得 <BR>
     *    引数.カテゴリー.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
     *    作成日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ４）DB更新 <BR>
     *    行オブジェクトの内容でポイントカテゴリーマスターテーブルに行を挿入（insert）する。 <BR>
     * <BR>
     * @@param l_category - (カテゴリー)<BR>
     * ポイントカテゴリーオブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 4191A00C02B0
     */
    public void saveNewCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewCategory(WEB3PointCategory , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_category == null)
        {
            String l_strMessage = "parameter error! category = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        
        // １）カテゴリー番号を新規に採番する。
        l_category.setNewCategoryNo(l_admin.getInstitutionCode());
 
        // ２）行オブジェクト取得 
        PointCategoryMasterParams l_params = (PointCategoryMasterParams)l_category.getDataSourceObject();

        // ３）取得した行オブジェクトに以下の通り、項目の初期値をセットする。
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setCreatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());
        
        // ４）DB更新 
        try
        {
            Processors.getDefaultProcessor().doInsertQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate訂正カテゴリー内容)<BR>
     * 訂正したカテゴリー内容のチェックを行う。<BR>
     * <BR>
     * １）カテゴリー内容のチェックを行う。<BR>
     *    this.validateカテゴリー内容()メソッドをコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    カテゴリー名： 引数.カテゴリー名<BR>
     *    概要： 引数.概要<BR>
     * <BR>
     * ２）訂正箇所のチェックを行う。<BR>
     *    以下の条件に合致する場合、つまり訂正がされてない場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01707<BR>
     * <BR>
     *    カテゴリー.カテゴリー名 = 引数.カテゴリー名 and<BR>
     *    カテゴリー.カテゴリー概要 = 引数.概要<BR>
     * <BR>
     * @@param l_strCategoryName - (カテゴリー名)<BR>
     * カテゴリー名<BR>
     * 
     * @@param l_strOutline - (概要)<BR>
     * 概要<BR>
     * 
     * @@param l_category - (カテゴリー)<BR>
     * ポイントカテゴリーオブジェクト<BR>
     * @@roseuid 4191B23302A1
     */
    public void validateChangeCategorySpec(String l_strCategoryName, String l_strOutline, WEB3PointCategory l_category)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateChangeCategorySpec(String , String , WEB3PointCategory )";
        log.entering(STR_METHOD_NAME);
        
        if (l_category == null)
        {
            String l_strMessage = "parameter error! category = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        // １）カテゴリー内容のチェックを行う。
        this.validateCategorySpec(l_strCategoryName, l_strOutline);
        
        // ２）訂正箇所のチェックを行う。
        boolean l_bolCategoryNameEquals = false;
        if (l_strCategoryName == null && l_category.getCategoryName() == null)
        {
            l_bolCategoryNameEquals = true;
        }
        else if (l_strCategoryName == null || l_category.getCategoryName() == null)
        {
            l_bolCategoryNameEquals = false;
        }
        else
        {
            l_bolCategoryNameEquals = l_strCategoryName.equals(l_category.getCategoryName());
        }        
        
        boolean l_bolCategoryOutlineEquals = false;
        if (l_strOutline == null && l_category.getCategoryOutline() == null)
        {
            l_bolCategoryOutlineEquals = true;
        }
        else if (l_strOutline == null || l_category.getCategoryOutline() == null)
        {
            l_bolCategoryOutlineEquals = false;
        }
        else
        {
            l_bolCategoryOutlineEquals = l_strOutline.equals(l_category.getCategoryOutline());
        }        

        if (l_bolCategoryNameEquals && l_bolCategoryOutlineEquals)
        {
            String l_strMessage = "ChangeCategory error! " 
                + l_strCategoryName 
                + ", " + l_strOutline 
                + "; " + l_category.getCategoryName()
                + ", " + l_category.getCategoryOutline();
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01707, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (saveカテゴリー)<BR>
     * カテゴリーを更新(update)する。<BR>
     * <BR>
     * １）行オブジェクト取得 <BR>
     *    引数.カテゴリー.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ２）取得した行オブジェクトに以下の通り、項目の値をセットする。 <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ３）DB更新 <BR>
     *    行オブジェクトの内容でポイントカテゴリーマスターテーブルの行を更新（update）する。 <BR>
     * <BR>
     * @@param l_category - (カテゴリー)<BR>
     * ポイントカテゴリーオブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 4191B6BE0316
     */
    public void saveCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveCategory(WEB3PointCategory , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_category == null)
        {
            String l_strMessage = "parameter error! category = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }

        // １）行オブジェクト取得
        PointCategoryMasterParams l_params = (PointCategoryMasterParams)l_category.getDataSourceObject();

        // ２）取得した行オブジェクトに以下の通り、項目の値をセットする。 
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // ３）DB更新 
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
                
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate取扱景品)<BR>
     * 引数の証券会社コード、カテゴリー番号に関連する景品が取扱われているかどうかをチェックする。<BR>
     * <BR>
     * １）以下の条件で、ポイント景品マスターテーブルを検索する。<BR>
     * <BR>
     * 検索条件： 証券会社コード = 引数.証券会社コード and<BR>
     *               カテゴリー番号 = 引数.カテゴリー番号<BR>
     * <BR>
     * ２）取得できた件数 > 0 の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01708<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * @@roseuid 4191C1390306
     */
    public void validateHandingPremium(String l_strInstitutionCode, String l_strCategoryNo)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateHandingPremium(String , String )";
        log.entering(STR_METHOD_NAME);
        
        // １）以下の条件で、ポイント景品マスターテーブルを検索する。
        String l_strWhere = "institution_code = ? AND category_no = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strCategoryNo}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        
        // ２）取得できた件数 > 0 の場合、例外をスローする。
        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            String l_strMessage = "景品件数 > 0 ! " 
                + "InstitutionCode = " + l_strInstitutionCode 
                + ", CategoryNo = " + l_strCategoryNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01708, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate重複景品番号)<BR>
     * 景品番号が重複してないかをチェックする。<BR>
     * <BR>
     * 以下の条件でポイント景品マスターテーブルを検索し、レコードが取得できた場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01709<BR>
     * <BR>
     * 検索条件：<BR>
     *    証券会社コード = 引数.証券会社コード and<BR>
     *    景品番号 = 引数.景品番号<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * @@roseuid 41932C1B0156
     */
    public void validateDupliPremiumNo(String l_strInstitutionCode, String l_strPremiumNo)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDupliPremiumNo(String , String )";
        log.entering(STR_METHOD_NAME);
        
        // １）以下の条件で、ポイント景品マスターテーブルを検索する。
        String l_strWhere = "institution_code = ? AND premium_no = ?";
        Object[] l_bindObjs = new Object[]{l_strInstitutionCode, l_strPremiumNo}; 
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                PointPremiumMasterRow.TYPE,
                l_strWhere,
                l_bindObjs);//DataFindException,DataQueryException,DataNetworkException 
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        
        // ２）取得できた件数 > 0 の場合、例外をスローする。
        if (l_lisRows != null && l_lisRows.size() > 0)
        {
            String l_strMessage = "景品件数 > 0 ! " 
                + "InstitutionCode = " + l_strInstitutionCode 
                + ", PremiumNo = " + l_strPremiumNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01709, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate景品内容)<BR>
     * 景品の内容をチェックする。<BR>
     * <BR>
     * １）景品番号チェック<BR>
     * <BR>
     * １－１）<BR>
     * <BR>
     *    引数.景品番号.length() > 5 or 引数.景品番号= null <BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01727<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01710<BR>
     * <BR>
     * １－２）<BR>
     * <BR>
     *    引数.景品番号に「半角英数字」、「-（ハイフン）」以外の値が含まれている場合、<BR>
     *    例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01711<BR>
     * <BR>
     * ２）景品名チェック<BR>
     * <BR>
     *    引数.景品名.length() > 80 or 引数.景品名= null <BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01887<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01712<BR>
     * <BR>
     * <BR>
     * ３）必要ポイントチェック<BR>
     * <BR>
     *    引数.必要ポイント.length() > 8 or<BR>
     *    引数.必要ポイント <= 0 or<BR>
     *    引数.必要ポイント != 数字<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01713<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01714<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01715<BR>
     * <BR>
     * ４）提供期間チェック<BR>
     * <BR>
     * ４－１）<BR>
     * <BR>
     *    引数.提供開始日時 = null or<BR>
     *    引数.提供終了日時 = null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01716<BR>
     * <BR>
     * ４－２）<BR>
     * <BR>
     *    引数.提供開始日時 >= 引数.提供終了日時<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01717<BR>
     * <BR>
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@param l_strPremiumName - (景品名)<BR>
     * 景品名<BR>
     * 
     * @@param l_strRequiredPoint - (必要ポイント)<BR>
     * 必要ポイント<BR>
     * 
     * @@param l_datStartDateTime - (提供開始日時)<BR>
     * 提供開始日時<BR>
     * 
     * @@param l_datEndDateTime - (提供終了日時)<BR>
     * 提供終了日時<BR>
     * @@roseuid 41932C1B0185
     */
    public void validatePremiumSpec(
        String l_strPremiumNo, 
        String l_strPremiumName, 
        String l_strRequiredPoint, 
        Date l_datStartDateTime, 
        Date l_datEndDateTime) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePremiumSpec(String , String , String , Date , Date )";
        log.entering(STR_METHOD_NAME);
        
        // １）景品番号チェック
        // １－１） 引数.景品番号.length() > 5 or        引数.景品番号= null 
        if (l_strPremiumNo == null)
        {
            String l_strMessage = "景品番号error ! 景品番号 = " + l_strPremiumNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01727, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (WEB3StringTypeUtility.getByteLength(l_strPremiumNo) > 5)
        {
            String l_strMessage = "景品番号error ! 景品番号 = " + l_strPremiumNo;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01710, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // １－２）引数.景品番号に「半角英数字」、「-（ハイフン）」以外の値が含まれている場合
        if (l_strPremiumNo != null)
        {
            if (!WEB3StringTypeUtility.isSingle(l_strPremiumNo))
            {
                String l_strMessage = "景品番号error ! 景品番号 = " + l_strPremiumNo;
                log.debug(l_strMessage);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01711, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            else
            {
                int l_intLen = WEB3StringTypeUtility.getByteLength(l_strPremiumNo);
                for (int i = 0; i < l_intLen; i++) 
                {
                    char l_ch = l_strPremiumNo.charAt(i);
                    if (!(WEB3StringTypeUtility.isSingleEng(l_ch)
                        || WEB3StringTypeUtility.isSingleNum(l_ch)
                        || (l_ch == '-')))
                    {
                        String l_strMessage = "景品番号error ! 景品番号 = " + l_strPremiumNo;
                        log.debug(l_strMessage);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01711, 
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_strMessage);
                    }
                }
            }
        }

        // ２）景品名チェック
        if (l_strPremiumName == null)
        {
            String l_strMessage = "景品名error ! 景品名 = " + l_strPremiumName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01887, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        if (WEB3StringTypeUtility.getByteLength(l_strPremiumName) > 80)
        {
            String l_strMessage = "景品名error ! 景品名 = " + l_strPremiumName;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01712, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // ３）必要ポイントチェック
        if (l_strRequiredPoint != null && WEB3StringTypeUtility.getByteLength(l_strRequiredPoint) > 8)
        {
            String l_strMessage = "必要ポイントerror ! 必要ポイント = " + l_strRequiredPoint;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01713, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        if (!WEB3StringTypeUtility.isNumber(l_strRequiredPoint))
        {
            String l_strMessage = "必要ポイントerror ! 必要ポイント = " + l_strRequiredPoint;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01715, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        long l_lngRequiredPoint = Long.parseLong(l_strRequiredPoint);
        if (l_lngRequiredPoint <= 0)
        {
            String l_strMessage = "必要ポイントerror ! 必要ポイント = " + l_strRequiredPoint;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01714, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        // ４）提供期間チェック
        // ４－１）引数.提供開始日時 = null or 引数.提供終了日時 = null
        if (l_datStartDateTime == null || l_datEndDateTime == null)
        {
            String l_strMessage = "提供日時error ! 提供開始日時=" + l_datStartDateTime + ",提供終了日時 ="  + l_datEndDateTime;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01716, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // ４－２）引数.提供開始日時 >= 引数.提供終了日時
        if (WEB3DateUtility.compareToSecond(l_datStartDateTime, l_datEndDateTime) >= 0)
        {
            String l_strMessage = "提供日時error ! 提供開始日時=" + l_datStartDateTime + ",提供終了日時 ="  + l_datEndDateTime;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01717, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (saveNew景品)<BR>
     * 新規の景品を追加(insert)する。<BR>
     * <BR>
     * １）行オブジェクト取得 <BR>
     *    引数.景品.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ２）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 <BR>
     *    作成日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ３）DB更新 <BR>
     *    行オブジェクトの内容でポイント景品マスターテーブルに行を挿入（insert）する。 <BR>
     * <BR>
     * @@param l_premium - (景品)<BR>
     * ポイント景品オブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 4193345D003D
     */
    public void saveNewPremium(WEB3PointPremium l_premium, WEB3Administrator l_admin)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " saveNewPremium(WEB3PointPremium , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_premium == null)
        {
            String l_strMessage = "parameter error! premium = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }

        // １）行オブジェクト取得
        PointPremiumMasterParams l_params = (PointPremiumMasterParams)l_premium.getDataSourceObject();
                 
        // ２）取得した行オブジェクトに以下の通り、項目の初期値をセットする。 
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setCreatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // ３）DB更新 
        try
        {
            Processors.getDefaultProcessor().doInsertQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
    
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate訂正景品内容)<BR>
     * 訂正した景品の内容をチェックする。<BR>
     * <BR>
     * １）景品内容のチェックを行う。<BR>
     *    this.validate景品内容()メソッドをコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    景品番号： 引数.景品番号<BR>
     *    景品名： 引数.景品名<BR>
     *    必要ポイント： 引数.必要ポイント<BR>
     *    提供開始日時： 引数.提供開始日時<BR>
     *    提供終了日時： 引数.提供終了日時<BR>
     * <BR>
     * ２）訂正箇所のチェックを行う。<BR>
     *    以下の条件に合致する場合、つまり訂正がされてない場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01718<BR>
     * <BR>
     *    景品.景品名 = 引数.景品名 and<BR>
     *    景品.必要ポイント = 引数.必要ポイント and<BR>
     *    景品.提供開始日時 = 引数.提供開始日時 and<BR>
     *    景品.提供終了日時 = 引数.提供終了日時<BR>
     * <BR>
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@param l_strPremiumName - (景品名)<BR>
     * 景品名<BR>
     * 
     * @@param l_strRequiredPoint - (必要ポイント)<BR>
     * 必要ポイント<BR>
     * 
     * @@param l_datStartDateTime - (提供開始日時)<BR>
     * 提供開始日時<BR>
     * 
     * @@param l_datEndDateTime - (提供終了日時)<BR>
     * 提供終了日時<BR>
     * 
     * @@param l_pointPremium - (景品)<BR>
     * ポイント景品オブジェクト<BR>
     * @@roseuid 41932C1B01A4
     */
    public void validateChangePremiumSpec(
        String l_strPremiumNo, 
        String l_strPremiumName, 
        String l_strRequiredPoint, 
        Date l_datStartDateTime, 
        Date l_datEndDateTime, 
        WEB3PointPremium l_pointPremium) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangePremiumSpec(String , String , String , Date , Date , WEB3PointPremium )";
        log.entering(STR_METHOD_NAME);
        
        // １）景品内容のチェックを行う。
        this.validatePremiumSpec(
            l_strPremiumNo, 
             l_strPremiumName, 
             l_strRequiredPoint, 
             l_datStartDateTime, 
             l_datEndDateTime);
        
        // ２）訂正箇所のチェックを行う。
        if (l_pointPremium == null)
        {
            String l_strMessage = "parameter error! premium = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        long l_lngRequiredPoint = Long.parseLong(l_strRequiredPoint);
        
        boolean l_bolPremiumNoEquals = false;
        if (l_strPremiumNo == null && l_pointPremium.getPremiumNo() == null)
        {
            l_bolPremiumNoEquals = true;
        }
        else if (l_strPremiumNo == null || l_pointPremium.getPremiumNo() == null)
        {
            l_bolPremiumNoEquals = false;
        }
        else
        {
            l_bolPremiumNoEquals = l_strPremiumNo.equals(l_pointPremium.getPremiumNo());
        }        
        
        boolean l_bolPremiumNameEquals = false;
        if (l_strPremiumName == null && l_pointPremium.getPremiumName() == null)
        {
            l_bolPremiumNameEquals = true;
        }
        else if (l_strPremiumName == null || l_pointPremium.getPremiumName() == null)
        {
            l_bolPremiumNameEquals = false;
        }
        else
        {
            l_bolPremiumNameEquals = l_strPremiumName.equals(l_pointPremium.getPremiumName());
        }        
        if (l_bolPremiumNoEquals
            && l_bolPremiumNameEquals 
            && l_lngRequiredPoint == l_pointPremium.getRequiredPoint()
            && WEB3DateUtility.compareToSecond(l_datStartDateTime, l_pointPremium.getStartDateTime()) == 0
            && WEB3DateUtility.compareToSecond(l_datEndDateTime, l_pointPremium.getEndDateTime()) == 0)
        {
            String l_strMessage = "ChangePremium error! " 
                + l_strPremiumNo 
                + ", " + l_strPremiumName
                + ", " + l_strRequiredPoint 
                + ", " + l_datStartDateTime 
                + ", " + l_datEndDateTime
                + "; " + l_pointPremium.getPremiumNo()
                + ", " + l_pointPremium.getPremiumName()
                + ", " + l_pointPremium.getRequiredPoint()
                + ", " + l_pointPremium.getStartDateTime()
                + ", " + l_pointPremium.getEndDateTime();
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01718, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (save景品)<BR>
     * 景品を更新(update)する。<BR>
     * <BR>
     * １）行オブジェクト取得 <BR>
     *    引数.景品.getDataSourceObject()にて行オブジェクトを取得する。 <BR>
     * <BR>
     * ２）取得した行オブジェクトに以下の通り、項目の値をセットする。 <BR>
     *    更新日時 = TradingSystem.getSystemTimestamp() <BR>
     *    更新者コード = 引数.管理者の管理者コード<BR>
     * <BR>
     * ３）DB更新 <BR>
     *    行オブジェクトの内容でポイント景品マスターテーブルの行を更新（update）する。 <BR>
     * <BR>
     * @@param l_premium - (景品)<BR>
     * ポイント景品オブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 4193345F002D
     */
    public void savePremium(WEB3PointPremium l_premium, WEB3Administrator l_admin) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " savePremium(WEB3PointPremium , WEB3Administrator )";
        log.entering(STR_METHOD_NAME);
        
        if (l_premium == null)
        {
            String l_strMessage = "parameter error! premium = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }
        if (l_admin == null)
        {
            String l_strMessage = "parameter error! admin = null.";
            log.debug(l_strMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);

        }

        // １）行オブジェクト取得
        PointPremiumMasterParams l_params = (PointPremiumMasterParams)l_premium.getDataSourceObject();

        // ２）取得した行オブジェクトに以下の通り、項目の値をセットする。 
        Timestamp l_tsCurrentTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        if (l_params == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_params.setLastUpdatedTimestamp(l_tsCurrentTime);
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // ３）DB更新 
        try
        {
            Processors.getDefaultProcessor().doUpdateQuery(l_params);//DataQueryException,DataNetworkException
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
}
@
