head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointProductManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント商品マネージャ(WEB3PointProductManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;

/**
 * (ポイント商品マネージャ)<BR>
 * ポイント商品マネージャインターフェイス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public interface WEB3PointProductManager extends Service 
{
    
    /**
     * (getカテゴリー)<BR>
     * 証券会社コード、カテゴリー番号から、ポイントカテゴリーオブジェクトを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory
     * @@roseuid 418F2DE30136
     */
    public WEB3PointCategory getCategory(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (getカテゴリー)<BR>
     * 証券会社コードから、その証券会社が扱っているカテゴリーの配列を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@return webbroker3.point.WEB3PointCategory[]
     * @@roseuid 418F32DF0202
     */
    public WEB3PointCategory[] getCategories(String l_strInstitutionCode) throws WEB3BaseException;
    
    /**
     * (get景品)<BR>
     * 証券会社コード、景品番号から、ポイント景品オブジェクトを取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium
     * @@roseuid 418F32DF0230
     */
    public WEB3PointPremium getPremium(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException;
    
    /**
     * (get景品)<BR>
     * 証券会社コード、カテゴリー番号から、ポイント景品の配列を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 418F2DEA000E
     */
    public WEB3PointPremium[] getPremiums(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (get取扱可能景品)<BR>
     * 証券会社コード、カテゴリー番号から、<BR>現在取扱可能なポイント景品の配列を取得する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * 
     * @@return webbroker3.point.WEB3PointPremium[]
     * @@roseuid 41A43AEB0085
     */
    public WEB3PointPremium[] getHandingPossiblePremium(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (validateカテゴリー内容)<BR>
     * カテゴリー内容のチェックを行う。<BR>
     * @@param l_strCategoryName - (カテゴリー名)<BR>
     * カテゴリー名<BR>
     * 
     * @@param l_strOutline - (概要)<BR>
     * 概要<BR>
     * @@roseuid 4191857503D0
     */
    public void validateCategorySpec(String l_strCategoryName, String l_strOutline) throws WEB3BaseException;
    
    /**
     * (saveNewカテゴリー)<BR>
     * 新規のカテゴリーを追加(insert)する。<BR>
     * @@param l_category - (カテゴリー)<BR>
     * ポイントカテゴリーオブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 41919F6B0272
     */
    public void saveNewCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate訂正カテゴリー内容)<BR>
     * 訂正したカテゴリー内容のチェックを行う。<BR>
     * @@param l_strCategoryName - (カテゴリー名)<BR>
     * カテゴリー名<BR>
     * 
     * @@param l_strOutline - (概要)<BR>
     * 概要<BR>
     * 
     * @@param l_category - (カテゴリー)<BR>
     * ポイントカテゴリーオブジェクト<BR>
     * @@roseuid 4191B21100DC
     */
    public void validateChangeCategorySpec(String l_strCategoryName, String l_strOutline, WEB3PointCategory l_category) throws WEB3BaseException;
    
    /**
     * (saveカテゴリー)<BR>
     * カテゴリーを更新(update)する。<BR>
     * @@param l_category - (カテゴリー)<BR>
     * ポイントカテゴリーオブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 4191B666020C
     */
    public void saveCategory(WEB3PointCategory l_category, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate取扱景品)<BR>
     * 引数の証券会社コード、カテゴリー番号に関連する景品が取扱われているかどうか<BR>
     * をチェックする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strCategoryNo - (カテゴリー番号)<BR>
     * カテゴリー番号<BR>
     * @@roseuid 4191C0D803B2
     */
    public void validateHandingPremium(String l_strInstitutionCode, String l_strCategoryNo) throws WEB3BaseException;
    
    /**
     * (validate重複景品番号)<BR>
     * 景品番号が重複してないかをチェックする。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * @@roseuid 4193258A0250
     */
    public void validateDupliPremiumNo(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException;
    
    /**
     * (validate景品内容)<BR>
     * 景品の内容をチェックする。<BR>
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
     * @@roseuid 4193259E03C7
     */
    public void validatePremiumSpec(String l_strPremiumNo, String l_strPremiumName, String l_strRequiredPoint, Date l_datStartDateTime, Date l_datEndDateTime) throws WEB3BaseException;
    
    /**
     * (saveNew景品)<BR>
     * 新規の景品を追加(insert)する。<BR>
     * @@param l_premium - (景品)<BR>
     * ポイント景品オブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 41933402031B
     */
    public void saveNewPremium(WEB3PointPremium l_premium, WEB3Administrator l_admin) throws WEB3BaseException;
    
    /**
     * (validate訂正景品内容)<BR>
     * 訂正した景品の内容をチェックする。<BR>
     * @@param l_strPremiumNo - (景品番号)<BR>
     * 景品番号<BR>
     * 
     * @@param l_strPremiumName - (景品名)<BR>
     * 景品名<BR>
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
     * @@roseuid 419326D80108
     */
    public void validateChangePremiumSpec(String l_strPremiumNo, String l_strPremiumName, String l_strRequiredPoint, Date l_datStartDateTime, Date l_datEndDateTime, WEB3PointPremium l_pointPremium) throws WEB3BaseException;
    
    /**
     * (save景品)<BR>
     * 景品を更新(update)する。<BR>
     * @@param l_premium - (景品)<BR>
     * ポイント景品オブジェクト<BR>
     * 
     * 
     * @@param l_admin - (管理者)<BR>
     * 管理者オブジェクト<BR>
     * @@roseuid 419334320108
     */
    public void savePremium(WEB3PointPremium l_premium, WEB3Administrator l_admin) throws WEB3BaseException;
}
@
