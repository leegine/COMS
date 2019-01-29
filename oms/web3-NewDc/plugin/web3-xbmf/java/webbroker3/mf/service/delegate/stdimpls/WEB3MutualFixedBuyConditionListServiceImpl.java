head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付条件一覧サービスImpl (WEB3MutualFixedBuyConditionListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 徐宏偉 (中訊) 新規作成
                   2006/08/10 山下　@（SRA）仕様変更 No486
Revesion History : 2007/08/30 趙林鵬 (中訊) 仕様変更 No.569,572
Revesion History : 2008/07/08 王志葵 (中訊) 仕様変更 モデルNo.606,613
Revesion History : 2008/07/31 王志葵 (中訊) 仕様変更 モデルNo.621,624
*/
package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeDivDef;
import webbroker3.common.define.WEB3FinInstitutionDivDef;
import webbroker3.common.define.WEB3MFStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFixedBuyCommonService;
import webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl;
import webbroker3.mf.WEB3MutualFixedBuyDrawAccount;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.message.WEB3MutualFixedBuyAccountInfo;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mf.message.WEB3MutualFixedBuyTotalUnit;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信定時定額買付条件一覧サービスImpl )<BR>
 * 投信定時定額買付条件一覧サービス実装クラス <BR>
 * 
 * @@author 徐宏偉(中訊)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionListServiceImpl 
    extends WEB3MutualClientRequestService
    implements WEB3MutualFixedBuyConditionListService
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionListServiceImpl.class);
    
    /**
     * 投信定時定額買付条件一覧サービス処理を行う。<BR> 
     * <BR>
     * シーケンス図参照<BR>
     *  ========================================================<BR>
     * 相互作用図: 投信定時定額買付条件一覧 / 投信定時定額買付条件一覧: <BR>
     * 例外(定時定額買付未実施エラー)をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_02520<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3MutualFixedBuyConditionListRequest l_ｍutualFixedBuyConditionListRequest = null;
        if (l_request instanceof WEB3MutualFixedBuyConditionListRequest)
        {
            //リクエストデータの具象データ型が「 投信定時定額買付条件一覧入力リクエスト」の場合
            l_ｍutualFixedBuyConditionListRequest = (WEB3MutualFixedBuyConditionListRequest) l_request;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //get補助口座( )
        //補助口座オブジェクトを取得する。
        SubAccount l_subAccount = this.getSubAccount();
        
        //validate注文受付可能( )
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();       

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        //get投信銘柄カテゴリーリスト(String)
        //[get投信銘柄カテゴリーリストに渡す引数]  
        // 証券会社コード：取得した補助口座オブジェクト.getInstitution().getInstitutionCode()
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        List l_lisProductCategory = 
            l_mfProductManager.getMutualFundProductCategoryList(l_strInstitutionCode);    
        
        //get投信銘柄カテゴリーリスト()の戻り値>0件の場合
        WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits = null;
        if (l_lisProductCategory.size() > 0)
        {
            //create投信銘柄カテゴリー一覧(List)
            //投信銘柄カテゴリー一覧を作成する。  
            //[create投信銘柄カテゴリー一覧に渡す引数]  
            //　@銘柄カテゴリー一覧：get投信銘柄カテゴリーリスト()の戻り値
            l_mfProductCategoryUnits =
                l_mfProductManager.createMutualFundProductCategoryList(
                    l_lisProductCategory);            
        }
             
        //get定時定額買付条件リスト(String, String, String, String, Object[])
        //定時定額買付条件テーブルからレコードを取得する。 
        //[get定時定額買付条件リストの引数] 
        //(証券会社コード : String, 部店コード : String, 口座コード : String, 検索条件文字列 : String, 検索条件値 : Object[]) 
        //証券会社コード   ：　@取得した補助口座オブジェクト .getInstitution().getInstitutionCode()の戻り値 
        //部店コード　@　@　@   ：　@取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値 
        //口座コード　@　@　@   ：　@取得した補助口座.getMainAccount().getAccountCode()の戻り値 
        //検索条件文字列 ：　@null 
        //検索条件値　@  　@ ：　@null   
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        List l_lisFixedBuyConditionList = l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
            l_strInstitutionCode, 
            l_strBranchCode, 
            l_strAccountCode, 
            null, null);

        //<検索条件文字列作成>
        //検索条件文字列として、以下の文字列を設定する。
        //"発注区分 in (?,?)　@or ( 変更区分 in (?,?) and 削除フラグ = ? )"
        String l_strQueryString = " ( status in (?, ?) "
            + " or ( change_div in (?, ?) and delete_flag = ? )) ";

        //<検索条件値設定>
        //Object配列に以下の値を追加
        //1：sonar未送信
        //２：sonar送信済
        //3：解除
        //4：一時停止
        //0:FALSE
        Object[] l_objQueryValues = new Object[]{
            WEB3MFStatusDef.SONAR_NOT_SEND,
            WEB3MFStatusDef.SONAR_SENDED,
            WEB3ChangeDivDef.RELEASE,
            WEB3ChangeDivDef.TEMP_STOP,
            BooleanEnum.FALSE
            };

        //get定時定額買付条件変更リスト(
        //証券会社コード : String,
        //部店コード : String,
        //口座コード : String,
        //検索条件文字列 : String,
        //検索条件値 : Object[])
        //証券会社コード   ：　@取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード　@　@　@   ：　@取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        //口座コード　@　@　@   ：　@取得した補助口座.getMainAccount().getAccountCode()の戻り値
        //検索条件文字列 ：　@作成した検索条件文字列
        //検索条件値　@  　@ ：　@作成した検索条件値設定
        List l_lisFixedBuyConditionChangeList =
            l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strQueryString,
                l_objQueryValues);

        //calc適用開始年月（業務日付）
        //適用開始年月（業務日付）を取得する。
        //[引数]
        //証券会社コード：取得した補助口座.getInstitution().getInstitutionCode()の戻り値
        //部店コード　@　@　@：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値
        Date l_datOrderBizdate =
            l_mutualFixedBuyCommonServiceImpl.calcValidStartDateOrderBizdate(
                l_strInstitutionCode,
                l_strBranchCode);

        //＜１ヶ月目表示リスト取得＞
        //get指定年月定時定額買付条件リスト(List, Date)
        //１ヶ月目の定時定額買付条件リストを取得する。
        //[引数]
        //定時定額買付条件リスト：get定時定額買付条件リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）で取得した年月－１ヶ月
        List l_lisFirstSelectMYLists = this.getSelectMYFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            WEB3DateUtility.addMonth(l_datOrderBizdate, -1));

        //get指定年月定時定額買付条件変更リスト(List, Date)
        //１ヶ月目の定時定額買付条件変更リストを取得する。
        //[引数]
        //定時定額買付条件変更リスト：get定時定額買付条件変更リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）で取得した年月－１ヶ月
        List l_lisFirstSelectMYChangeLists =
            this.getSelectMYFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                WEB3DateUtility.addMonth(l_datOrderBizdate, -1));

        //＜2ヶ月目表示リスト取得＞
        //get指定年月定時定額買付条件リスト(List, Date)
        //２ヶ月目の定時定額買付条件リストを取得する。
        //[引数]
        //定時定額買付条件リスト：get定時定額買付条件リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）で取得した年月
        List l_lisSecondSelectMYLists = this.getSelectMYFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            l_datOrderBizdate);

        //get指定年月定時定額買付条件変更リスト(List, Date)
        //２ヶ月目の定時定額買付条件変更リストを取得する。
        //[引数]
        //定時定額買付条件変更リスト：get定時定額買付条件変更リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）で取得した年月
        List l_lisSecondSelectMYChangeLists =
            this.getSelectMYFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                l_datOrderBizdate);

        //＜3ヶ月目表示リスト取得＞
        //get指定年月定時定額買付条件リスト(List, Date)
        //３ヶ月目の定時定額買付条件リストを取得する。
        //[引数]
        //定時定額買付条件リスト：get定時定額買付条件リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）で取得した年月+１ヶ月
        List l_lisThirdSelectMYLists = this.getSelectMYFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            WEB3DateUtility.addMonth(l_datOrderBizdate, 1));

        //get指定年月定時定額買付条件変更リスト(List, Date)
        //３ヶ月目の定時定額買付条件変更リストを取得する。
        //[引数]
        //定時定額買付条件変更リスト：get定時定額買付条件変更リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）で取得した年月+１ヶ月
        List l_lisThirdSelectMYChangeLists =
            this.getSelectMYFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                WEB3DateUtility.addMonth(l_datOrderBizdate, 1));

        //＜未来月表示リスト取得＞
        //get未来月定時定額買付条件リスト(List, Date)
        //未来月の定時定額買付条件リストを取得する。
        //[引数]
        //定時定額買付条件リスト：get定時定額買付条件リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）の戻り値
        List l_lisFutureLists = this.getFutureFixedBuyConditionList(
            l_lisFixedBuyConditionList,
            l_datOrderBizdate);

        //get未来月定時定額買付条件変更リスト(List, Date)
        //未来月の定時定額買付条件変更リストを取得する。
        //[引数]
        //定時定額買付条件変更リスト：get定時定額買付条件変更リスト()の戻り値
        //指定年月：calc適用開始年月（業務日付）の戻り値
        List l_lisFutureChangeLists =
            this.getFutureFixedBuyConditionChangeList(
                l_lisFixedBuyConditionChangeList,
                l_datOrderBizdate);

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsFirst = null;
        Institution l_institution = l_subAccount.getInstitution();
        //＜1ヶ月目表示リスト取得＞のget指定年月定時定額買付条件リスト !=0 または
        //＜1ヶ月目表示リスト取得＞のget指定年月定時定額買付条件変更リスト !=0  の場合
        if (l_lisFirstSelectMYLists.size() != 0
            || l_lisFirstSelectMYChangeLists.size() != 0)
        {
            //merge定時定額買付条件（当月表示）(Institution, List, List)
            //１ヶ月目の定時定額買付条件リストと
            //１ヶ月目の定時定額買付条件変更リストをマージして
            //投信定時定額買付条件行の配列を作成する。
            //[引数]
            //証券会社   ：　@取得した補助口座.getInstitution()の戻り値
            //定時定額買付条件リスト　@：
            //＜１ヶ月目表示リスト取得＞get指定年月定時定額買付条件リスト()の戻り値
            //定時定額買付条件変更リスト　@：
            //＜１ヶ月目表示リスト取得＞get指定年月定時定額買付条件変更リスト()の戻り値
            l_mutualFixedBuyConditionUnitsFirst =
                this.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFirstSelectMYLists,
                    l_lisFirstSelectMYChangeLists);
        }

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsSecond = null;
        //＜２ヶ月目表示リスト取得＞のget指定年月定時定額買付条件リスト !=0 または
        //＜２ヶ月目表示リスト取得＞のget指定年月定時定額買付条件変更リスト !=0  の場合
        if (l_lisSecondSelectMYLists.size() != 0
            || l_lisSecondSelectMYChangeLists.size() != 0)
        {
            //merge定時定額買付条件（当月表示）(Institution, List, List)
            //２ヶ月目の定時定額買付条件リストと
            //２ヶ月目の定時定額買付条件変更リストをマージして
            //投信定時定額買付条件行の配列を作成する。
            //[引数]
            //証券会社   ：　@取得した補助口座.getInstitution()の戻り値
            //定時定額買付条件リスト　@：
            //＜２ヶ月目表示リスト取得＞get指定年月定時定額買付条件リスト()の戻り値
            //定時定額買付条件変更リスト　@：
            //＜２ヶ月目表示リスト取得＞get指定年月定時定額買付条件変更リスト()の戻り値
            l_mutualFixedBuyConditionUnitsSecond =
                this.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisSecondSelectMYLists,
                    l_lisSecondSelectMYChangeLists);
        }

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsThird = null;
        //＜3ヶ月目表示リスト取得＞のget指定年月定時定額買付条件リスト == 0 かつ
        //＜3ヶ月目表示リスト取得＞のget指定年月定時定額買付条件変更リスト !=0  の場合
        if (l_lisThirdSelectMYLists.size() == 0
            && l_lisThirdSelectMYChangeLists.size() != 0)
        {
            //merge定時定額買付条件（３ヶ月表示）
            //２ヶ月目の定時定額買付条件リストと
            //３ヶ月目の定時定額買付条件変更リストをマージして
            //投信定時定額買付条件行の配列を作成する。
            //[引数]
            //証券会社   ：　@取得した補助口座.getInstitution()の戻り値
            //定時定額買付条件行　@：　@２ヶ月目表示データ
            //定時定額買付条件変更リスト（３ヶ月目）　@：
            //＜３ヶ月目表示リスト取得＞get指定年月定時定額買付条件変更リスト()の戻り値
            l_mutualFixedBuyConditionUnitsThird =
                this.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_mutualFixedBuyConditionUnitsSecond,
                    l_lisThirdSelectMYChangeLists);
        }

        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsFuture = null;
        //＜未来月表示リスト取得＞のget未来月定時定額買付条件リスト !=0  の場合
        if (l_lisFutureLists.size() != 0)
        {
            //merge定時定額買付条件（当月表示）(Institution, List, List)
            //未来月の定時定額買付条件リストと
            //未来月の定時定額買付条件変更リストをマージして
            //投信定時定額買付条件行の配列を作成する。
            //[引数]
            //証券会社   ：　@取得した補助口座.getInstitution()の戻り値
            //定時定額買付条件リスト　@：
            //＜未来月表示リスト取得＞のget未来月定時定額買付条件リスト()の戻り値
            //定時定額買付条件変更リスト　@：
            //＜未来月表示リスト取得＞のget未来月定時定額買付条件変更リスト()の戻り値
            l_mutualFixedBuyConditionUnitsFuture =
                this.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFutureLists,
                    l_lisFutureChangeLists);
        }

        int l_intFirstLength =0;
        int l_intSecondLength = 0;
        int l_intThirdLength = 0;
        int l_intFutureLength = 0;
        
        //取得した表示データから投信定時定額買付条件行の配列を作成
        if (l_mutualFixedBuyConditionUnitsFirst != null)
        {
            l_intFirstLength = l_mutualFixedBuyConditionUnitsFirst.length;
        }
        if (l_mutualFixedBuyConditionUnitsSecond != null)
        {
            l_intSecondLength = l_mutualFixedBuyConditionUnitsSecond.length;
        }
        if (l_mutualFixedBuyConditionUnitsThird != null)
        {
            l_intThirdLength = l_mutualFixedBuyConditionUnitsThird.length;
        }
        if (l_mutualFixedBuyConditionUnitsFuture != null)
        {
            l_intFutureLength = l_mutualFixedBuyConditionUnitsFuture.length;
        }

        int l_intTotalLength = l_intFirstLength
            + l_intSecondLength
            + l_intThirdLength
            + l_intFutureLength;
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_intTotalLength];

        //１ヶ月目表示データ != 0件の場合、投信定時定額買付条件行の配列に追加する。
        if (l_intFirstLength != 0)
        {
            for (int i = 0; i < l_intFirstLength; i++)
            {
                l_mutualFixedBuyConditionUnits[i] =
                    l_mutualFixedBuyConditionUnitsFirst[i];
            }
        }

        //２ヶ月目表示データ != 0件の場合、投信定時定額買付条件行の配列に追加する。
        if (l_intSecondLength != 0)
        {
            for (int i = 0; i < l_intSecondLength; i++)
            {
                l_mutualFixedBuyConditionUnits[l_intFirstLength + i] =
                    l_mutualFixedBuyConditionUnitsSecond[i];
            }
        }

        //３ヶ月目表示データ != 0件の場合、投信定時定額買付条件行の配列に追加する。
        if (l_intThirdLength != 0)
        {
            for (int i = 0; i < l_intThirdLength; i++)
            {
                l_mutualFixedBuyConditionUnits[l_intFirstLength + l_intSecondLength + i] =
                    l_mutualFixedBuyConditionUnitsThird[i];
            }
        }

        //未来月表示データ != 0件の場合、投信定時定額買付条件行の配列に追加する。
        if (l_intFutureLength != 0)
        {
            for (int i = 0; i < l_intFutureLength; i++)
            {
                l_mutualFixedBuyConditionUnits[l_intFirstLength + l_intSecondLength + l_intThirdLength + i] =
                    l_mutualFixedBuyConditionUnitsFuture[i];
            }
        }

        WEB3MutualFixedBuyTotalUnit[] l_mfBuyTotalUnits = null;
        WEB3MutualFixedBuyConditionUnit[] l_sortConditionUnits = null;
        //＜分岐処理＞作成した投信定時定額買付条件行の配列 != 0件 の場合
        if (l_intTotalLength != 0)
        {
            //sort定時定額買付条件一覧(投信定時定額買付条件行[] )
            //ソート処理
            //[sort投信定時定額買付条件()に渡す引数]
            //投信定時定額買付条件一覧：作成した投信定時定額買付条件行の配列
            l_sortConditionUnits =
                l_mutualFixedBuyCommonServiceImpl.sortFixedBuyConditionList(
                    l_mutualFixedBuyConditionUnits);

            //get定時定額買付金額合計(投信定時定額買付条件行[] )
            //定時定額買付買付金額の合計を取得する。
            //[get定時定額買付買付金額合計の引数]
            //投信定時定額買付条件行[ ] ： sort定時定額買付条件一覧()の戻り値
            l_mfBuyTotalUnits = this.getFixedBuyTotalUnit(l_sortConditionUnits);
        }

        //createレスポンス( )(投信定時定額買付条件一覧リクエスト::createレスポンス)
        WEB3MutualFixedBuyConditionListResponse l_response = 
            (WEB3MutualFixedBuyConditionListResponse)l_ｍutualFixedBuyConditionListRequest.createResponse();
        
        //定時定額買付引落口座(String, String, String)
        //定時定額買付引落口座クラスを生成する。 
        //[定時定額買付引落口座に渡す引数]  
        //　@証券会社コード：取得した補助口座.getInstitution().getInstitutionCode()の戻り値 
        //  部店コード：取得した補助口座.getMainAccount().getBranch().getBranchCode()の戻り値 
        //  口座コード：取得した補助口座.getMainAccount().getAccountCode()の戻り値
        WEB3MutualFixedBuyDrawAccount l_mfBuyDrawAccount = null;
        try
        {
            l_mfBuyDrawAccount = 
                new WEB3MutualFixedBuyDrawAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
        }
        catch (WEB3BaseException l_be)
        {
            log.debug("定時定額買付引落口座に該当するデータがありません!");           
        }
        //＜分岐処理＞定時定額買付引落口座．get金融機@関区分=銀行の場合
        String[] l_strFinBranchNames = null;
        WEB3MutualFixedBuyAccountInfo l_mfBuyAccountInfo = null;
        if (l_mfBuyDrawAccount != null)
        {
            if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //get銀行支店名(String, String)
                //[get銀行支店名に渡す引数] 
                // 銀行コード：定時定額買付引落口座．銀行コード 
                // 支店コード：定時定額買付引落口座．支店コード    
                l_strFinBranchNames = l_mutualFixedBuyCommonServiceImpl.getFinBranchName(
                    l_mfBuyDrawAccount.getFinInstitutionCode(), 
                    l_mfBuyDrawAccount.getFinBranchCode());
            }
            
            //投信定時定額引落口座情報( )(投信定時定額引落口座情報::投信定時定額引落口座情報)
            //定時定額引落口座情報オブジェクトを生成する。
            l_mfBuyAccountInfo = new WEB3MutualFixedBuyAccountInfo();
              
            //＜(*)プロパティセット＞ ・・・（Ａ）
            //以下の通り、プロパティをセットする。
            //・銀行コード                     = 定時定額買付引落口座．銀行コード
            l_mfBuyAccountInfo.financialInstitutionCode = l_mfBuyDrawAccount.getFinInstitutionCode();
                
            //・支店コード                     = 定時定額買付引落口座．支店コード
            l_mfBuyAccountInfo.financialBranchCode = l_mfBuyDrawAccount.getFinBranchCode();
                
            //・金融機@関区分               = 定時定額買付引落口座．金融機@関区分
            l_mfBuyAccountInfo.financialInstitutionDiv = l_mfBuyDrawAccount.getFinInstitutionDiv();    
            
            //・引落口座番号　@　@　@　@　@　@ = 定時定額買付引落口座．引落口座番号
            l_mfBuyAccountInfo.financialAccountCode = l_mfBuyDrawAccount.getDrawAccountNo();
                
            //・引落口座名義人（カナ）　@　@= 定時定額買付引落口座．引落口座名義人（カナ）
            l_mfBuyAccountInfo.financialAccountName = l_mfBuyDrawAccount.getDrawAccountNameKana();
                
            //金融機@関区分が銀行の場合
            if (WEB3FinInstitutionDivDef.BANK.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //　@・銀行名　@　@                    = get銀行支店名の戻り値String[0]
                l_mfBuyAccountInfo.financialInstitutionName = l_strFinBranchNames[0];
                
                //　@・支店名                         = get銀行支店名の戻り値String[1]
                l_mfBuyAccountInfo.financialBranchName = l_strFinBranchNames[1];
                
                //　@・預金区分                      = 定時定額買付引落口座．預金区分
                l_mfBuyAccountInfo.financialAccountDiv = l_mfBuyDrawAccount.getDepositDiv();
                
            }
            //金融機@関区分が郵便局の場合
            else if(WEB3FinInstitutionDivDef.POST_OFFICE.equals(l_mfBuyDrawAccount.getFinInstitutionDiv()))
            {
                //　@・銀行名　@　@                    = null
                l_mfBuyAccountInfo.financialInstitutionName = null;
                
                //　@・支店名                         = null
                l_mfBuyAccountInfo.financialBranchName = null;
                
                //　@・預金区分                      = null
                l_mfBuyAccountInfo.financialAccountDiv = null;        
            }
        }
        //＜レスポンスデータセット＞
        //以下の通り、レスポンスデータをセットする。
        
        //・投信銘柄カテゴリーコード一覧   = create投信銘柄カテゴリー一覧()の戻り値
        l_response.categoryList = l_mfProductCategoryUnits;

        //・投信定時定額買付条件一覧   = （以下のとおり）
        //merge定時定額買付条件()の戻り値 != 0件 の場合、sort定時定額買付条件一覧()の戻り値
        //merge定時定額買付条件()の戻り値 == 0件 の場合、null
        l_response.conditionList = l_sortConditionUnits;

        //・投信定時定額買付金額合計   = get定時定額買付買付金額合計の戻り値                
        l_response.totalList = l_mfBuyTotalUnits;

        //・投信定時定額引落口座情報   =　@（以下のとおり）
        //定時定額買付引落口座が取得できた場合、（Ａ）でプロパティをセットした投信定時定額引落口座情報
        //定時定額買付引落口座が取得できなかった場合、null
        l_response.acountInfo = l_mfBuyAccountInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }    

    /**
     * (merge定時定額買付条件（当月表示）)<BR>
     * 定時定額買付条件リストの内容と定時定額買付条件変更リストの内容をマージして<BR>
     * 投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * <BR>
     * 1)　@定時定額買付共通サービスを取得する。<BR>
     * <BR>
     * 2)　@引数.定時定額買付条件リストの件数分ループし、投信定時定額買付条件行オブジェクトのリストを作成する。<BR>
     * <BR>
     * 　@2)-1)　@投信定時定額買付条件行オブジェクトを生成する。<BR>
     * <BR>
     * 　@2)-2)　@拡張投信銘柄マネージャー.get投信銘柄()をコールする。<BR>
     * 　@　@［get投信銘柄の引数］<BR>
     * 　@　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@　@銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード<BR>
     * <BR>
     * 　@2)-3)　@投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@**** 定時定額買付条件テーブルの内容を投信定時定額買付条件行のプロパティへセットする。<BR>
     * 　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@　@　@銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード<BR>
     * 　@　@　@　@　@　@銘柄名：取得した投信銘柄マスタRow.get銘柄名<BR>
     * 　@　@　@　@　@　@投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード<BR>
     * 　@　@　@　@　@　@買付金額（月々）：引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（月々）<BR>
     * 　@　@　@　@　@　@買付金額（積み増し）：引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@銘柄表示順序：取得した投信銘柄マスタRow.get表示順位<BR>
     * 　@　@　@　@　@　@適用開始年月：引数．定時定額買付条件リスト.定時定額買付条件Row．適用開始年月<BR>
     * 　@　@　@　@　@　@更新日時：null<BR>
     * 　@　@　@　@　@　@口座引落年月：引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月<BR>
     * 　@　@　@　@　@　@確定引落金額（積み増し）：定時定額買付共通サービス．calc賞与確定引落金額(*1)の戻り値をセット<BR>
     * 　@　@　@　@　@　@目論見書閲覧チェック：null<BR>
     * 　@　@　@　@　@　@一時停止中フラグ：false<BR>
     * <BR>
     * 　@2)-4)　@投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。<BR>
     * <BR>
     * 3)　@引数.定時定額買付条件変更リストでループし、引数.定時定額買付条件変更リストの内容を<BR>
     * 　@　@投信定時定額買付条件行オブジェクトのリストに反映する。<BR>
     * <BR>
     * 　@3)-1)　@投信定時定額買付条件行のリストでループする。<BR>
     * <BR>
     * 　@　@　@3)-1)-1)　@以下の条件で比較して、一致する場合<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード   ==<BR>
     * 　@　@　@　@　@　@　@投信定時定額買付条件行．銘柄コード<BR>
     * <BR>
     * 　@　@　@　@　@3)-1)-1)-1)　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@「1：追加」または「2：変更」の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@もしくは、変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合<BR>
     * 　@　@　@　@　@　@　@3)-1)-1)-1)-1)　@投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブルの内容で投信定時定額買付条件行を上書きする。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が「1：追加」または「2：変更」の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合、　@0<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が「1：追加」または「2：変更」の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合、　@0<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@適用開始年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@口座引落年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@確定引落金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@一時停止中フラグ：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が「1：追加」または「2：変更」の場合、　@false<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合、　@true<BR>
     * <BR>
     * 　@　@　@　@　@3)-1)-1)-2)　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）== null』 の場合<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブルの内容を元に投信定時定額買付条件行のリストから削除する。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * <BR>
     * 　@　@　@　@　@　@　@3)-1)-1)-2)-1)　@投信定時定額買付条件行のリストから該当する行オブジェクトを削除する。<BR>
     * <BR>
     * 　@　@　@　@　@3)-1)-1)-3)　@引数.定時定額買付条件変更リストのループへ戻る。<BR>
     * <BR>
     * 　@3)-2)引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が「1：追加」または「2：変更」の場合<BR>
     * 　@　@　@　@もしくは、変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合<BR>
     * <BR>
     * 　@　@　@3)-2)-1)　@投信定時定額買付条件行オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@3)-2)-2)　@拡張投信銘柄マネージャー.get投信銘柄()をコールする。<BR>
     * 　@　@　@　@　@［get投信銘柄の引数］<BR>
     * 　@　@　@　@　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@　@　@　@　@銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード<BR>
     * <BR>
     * 　@　@　@3)-2)-3)　@投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@**** 投信定時定額買付条件変更テーブルのみに存在する場合、<BR>
     * 　@　@　@　@**** 投信定時定額買付条件変更テーブルの内容を投信定時定額買付条件行のプロパティへセットする。<BR>
     * 　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@　@　@銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード<BR>
     * 　@　@　@　@　@　@銘柄名：取得した投信銘柄マスタRow.get銘柄名<BR>
     * 　@　@　@　@　@　@投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード<BR>
     * 　@　@　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が「1：追加」または「2：変更」の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合、　@0<BR>
     * 　@　@　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が「1：追加」または「2：変更」の場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合、　@0<BR>
     * 　@　@　@　@　@　@銘柄表示順序：取得した投信銘柄マスタRow.get表示順位<BR>
     * 　@　@　@　@　@　@適用開始年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@更新日時：null<BR>
     * 　@　@　@　@　@　@口座引落年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@確定引落金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@目論見書閲覧チェック：null<BR>
     * 　@　@　@　@　@　@一時停止中フラグ：<BR>
     * 　@　@　@　@　@　@　@　@　@変更区分が「1：追加」または「2：変更」の場合　@falseを設定<BR>
     * 　@　@　@　@　@　@　@　@　@変更区分が『「3：解除」または「4：一時停止」』かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合　@trueを設定<BR>
     * <BR>
     * 　@　@　@3)-2)-4)　@　@投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。<BR>
     * <BR>
     * <BR>
     * 4)　@投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * 5)　@投信定時定額買付条件行の配列をリターンする。<BR>
     * <BR>
     * (*1)　@[calc賞与確定引落金額()の引数]<BR>
     * 　@　@　@　@　@　@　@　@定時定額買付条件Row：引数．定時定額買付条件リスト.定時定額買付条件Row<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_lisMutualFixedBuyConditionLists - (定時定額買付条件リスト)<BR>
     * 定時定額買付条件リスト<BR>
     * @@param l_lisMutualFixedBuyConditionChangeLists - (定時定額買付条件変更リスト)<BR>
     * 定時定額買付条件変更リスト<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionMonth(
        Institution l_institution,
        List l_lisMutualFixedBuyConditionLists,
        List l_lisMutualFixedBuyConditionChangeLists) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionMonth("
            + "Institution, List, List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisMutualFixedBuyConditionLists == null
            || l_lisMutualFixedBuyConditionChangeLists == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp =
            (FinApp)Services.getService(FinApp.class);

        //拡張投信銘柄マネージャを取得する。
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //1) 定時定額買付共通サービスを取得する。
        WEB3MutualFixedBuyCommonService l_mutualFixedBuyCommonService =
            (WEB3MutualFixedBuyCommonService)Services.getService(
                WEB3MutualFixedBuyCommonService.class);

        //2) 引数.定時定額買付条件リストの件数分ループし、投信定時定額買付条件行オブジェクトのリストを作成する。
        List l_lisMutualFixedBuyConditionUnitLists = new ArrayList();
        Iterator l_iteratorMutualFixedBuyConditionLists =
            l_lisMutualFixedBuyConditionLists.iterator();
        while (l_iteratorMutualFixedBuyConditionLists.hasNext())
        {
            //2)-1) 投信定時定額買付条件行オブジェクトを生成する。
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();

            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorMutualFixedBuyConditionLists.next();

            //2)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。
            //［get投信銘柄の引数］
            //証券会社：引数.証券会社
            //銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード
            MutualFundProduct l_mutualFundProduct;
            try {
                l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                    l_institution,
                    l_mfFixedBuyingCondRow.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("テーブルに該当するデータがありません ");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            MutualFundProductRow l_mutualFundProductRow =
                (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
            //2)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。
            //定時定額買付条件テーブルの内容を投信定時定額買付条件行のプロパティへセットする。
            //[セットする内容]
            //銘柄コード：引数．定時定額買付条件リスト.定時定額買付条件Row．銘柄コード
            l_mutualFixedBuyConditionUnit.mutualProductCode =
                l_mfFixedBuyingCondRow.getProductCode();
            //銘柄名：取得した投信銘柄マスタRow.get銘柄名
            l_mutualFixedBuyConditionUnit.mutualProductName =
                l_mutualFundProductRow.getStandardName();
            //投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード
            l_mutualFixedBuyConditionUnit.categoryCode =
                l_mutualFundProductRow.getCategoryCode();
            //買付金額（月々）：引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（月々）
            if (l_mfFixedBuyingCondRow.getMonthlyBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getMonthlyBuyAmount());
            }
            //買付金額（積み増し）：引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（積み増し）
            if (l_mfFixedBuyingCondRow.getIncreaseBuyAmountIsNull())
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_mfFixedBuyingCondRow.getIncreaseBuyAmount());
            }
            //銘柄表示順序：取得した投信銘柄マスタRow.get表示順位
            if (l_mutualFundProductRow.getIndicationRankingIsNull())
            {
                l_mutualFixedBuyConditionUnit.displayOrder = null;
            }
            else
            {
                l_mutualFixedBuyConditionUnit.displayOrder =
                    l_mutualFundProductRow.getIndicationRanking() + "";
            }
            //適用開始年月：引数．定時定額買付条件リスト.定時定額買付条件Row．適用開始年月
            l_mutualFixedBuyConditionUnit.validStartDate =
                l_mfFixedBuyingCondRow.getValidStartDate();
            //更新日時：null
            l_mutualFixedBuyConditionUnit.updateDate = null;
            //口座引落年月：引数．定時定額買付条件リスト.定時定額買付条件Row．口座引落年月
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                new Timestamp(l_mfFixedBuyingCondRow.getDrawDate().getTime());
            //確定引落金額（積み増し）：
            //定時定額買付共通サービス．calc賞与確定引落金額(*1)の戻り値 != 
            //引数．定時定額買付条件リスト.定時定額買付条件Row．買付金額（積み増し）の場合、
            //calc賞与確定引落金額の戻り値をセット
            //(*1) [calc賞与確定引落金額()の引数]
            //定時定額買付条件Row：引数．定時定額買付条件リスト.定時定額買付条件Row
            String l_strPrizeAndDecisioDrawAmount = 
                l_mutualFixedBuyCommonService.calcPrizeAndDecisioDrawAmount(l_mfFixedBuyingCondRow);
            if(l_strPrizeAndDecisioDrawAmount == null)
            {
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
            }
            else if(!l_strPrizeAndDecisioDrawAmount.equals(WEB3StringTypeUtility.formatNumber(
                l_mfFixedBuyingCondRow.getIncreaseBuyAmount())))
            {
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = l_strPrizeAndDecisioDrawAmount;
            }
            //それ以外の場合、null
            else
            {
                l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
            }  
            //目論見書閲覧チェック：null
            l_mutualFixedBuyConditionUnit.checkResult = null;
            //一時停止中フラグ：false
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;

            //2)-4) 投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。
            l_lisMutualFixedBuyConditionUnitLists.add(
                l_mutualFixedBuyConditionUnit);
        }

        //3) 引数.定時定額買付条件変更リストでループし、引数.定時定額買付条件変更リストの内容を
        //投信定時定額買付条件行オブジェクトのリストに反映する。
        Iterator l_iteratorMutualFixedBuyConditionChangeLists =
            l_lisMutualFixedBuyConditionChangeLists.iterator();
        while (l_iteratorMutualFixedBuyConditionChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorMutualFixedBuyConditionChangeLists.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //3)-1) 投信定時定額買付条件行のリストでループする。
            Iterator l_iteratorUnitLists =
                l_lisMutualFixedBuyConditionUnitLists.iterator();
            boolean l_blnFlag = true;
            while (l_iteratorUnitLists.hasNext())
            {
                WEB3MutualFixedBuyConditionUnit l_unit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorUnitLists.next();

                //3)-1)-1) 以下の条件で比較して、一致する場合
                //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード   ==
                //投信定時定額買付条件行．銘柄コード
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_unit.mutualProductCode))
                {
                    //3)-1)-1)-1) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                    //「1：追加」または「2：変更」の場合、もしくは、変更区分が
                    //『「3：解除」または「4：一時停止」』かつ
                    //『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合
                    if ((WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                        || ((WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv))
                            || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                            && !l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                    {
                        //3)-1)-1)-1)-1) 投信定時定額買付条件行オブジェクトのプロパティセット。
                        //定時定額買付条件変更テーブルの内容で投信定時定額買付条件行を上書きする。
                        //[セットする内容]
                        //変更区分が「1：追加」または「2：変更」の場合、
                        if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                            || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                        {
                            //買付金額（月々）：
                            //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）
                            if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                            {
                                l_unit.monthlyBuyAmount = null;
                            }
                            else
                            {
                                l_unit.monthlyBuyAmount =
                                    WEB3StringTypeUtility.formatNumber(
                                        l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                            }

                            //買付金額（積み増し）：
                            //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）
                            if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                            {
                                l_unit.increaseBuyAmount = null;
                            }
                            else
                            {
                                l_unit.increaseBuyAmount =
                                    WEB3StringTypeUtility.formatNumber(
                                        l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                            }

                            //一時停止中フラグ：false
                            l_unit.suspensionFlag = false;
                        }
                        //変更区分が『「3：解除」または「4：一時停止」』かつ
                        //『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合、
                        else
                        {
                            //買付金額（月々）：　@0
                            l_unit.monthlyBuyAmount = "0";
                            //買付金額（積み増し）：　@0
                            l_unit.increaseBuyAmount = "0";
                            //一時停止中フラグ：true 
                            l_unit.suspensionFlag = true;
                        }

                        //適用開始年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                        l_unit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();
                        //口座引落年月：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                        l_unit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        //確定引落金額（積み増し）：
                        //変更区分が「1：追加」または「2：変更」の場合、　@
                        //  引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!=
                        //  引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）の場合、
                        //  引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）
                        //  それ以外の場合、null
                        if (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                        {
                            l_unit.definiteIncreaseBuyAmount = null;
                        }
                        else if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                                    || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                        {
                            if (!WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals(
                                        WEB3StringTypeUtility.formatNumber(l_mfFixedBuyingChangeRow.getIncreaseBuyAmount())))
                            {
                                l_unit.definiteIncreaseBuyAmount =
                                    WEB3StringTypeUtility.formatNumber(
                                        l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                            }
                            else
                            {
                                l_unit.definiteIncreaseBuyAmount = null;
                            }
                        }
                        //変更区分が「3：解除」または「4：一時停止」の場合、
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）
                        else
                        {
                            l_unit.definiteIncreaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                        }
                    }

                    //3)-1)-1)-2) 引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
                    //『「3：解除」または「4：一時停止」』かつ
                    //『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）== null
                    //または　@0　@』 の場合、
                    //定時定額買付条件変更テーブルの内容を元に投信定時定額買付条件行のリストから削除する。
                    if ((WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                        && (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull()
                            ||WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals("0")))
                    {
                        //3)-1)-1)-2)-1) 投信定時定額買付条件行のリストから該当する行オブジェクトを削除する。
                        l_iteratorUnitLists.remove();
                    }

                    l_blnFlag = false;
                    //3)-1)-1)-3) 引数.定時定額買付条件変更リストのループへ戻る。
                    break;
                }
            }

            if (!l_blnFlag)
            {
                continue;
            }

            //3)-2)引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．変更区分が
            //「1：追加」または「2：変更」の場合もしくは、
            //変更区分が『「3：解除」または「4：一時停止」』かつ
            //『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null 
            // かつ != 0 』 の場合
            if ((WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                || ((WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv)
                    || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    && (!l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull()
                        && !WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals("0"))))
            {
                //3)-2)-1) 投信定時定額買付条件行オブジェクトを生成する。
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //3)-2)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。
                //［get投信銘柄の引数］
                //証券会社：引数.証券会社
                //銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード
                MutualFundProduct l_mutualFundProduct;
                try
                {
                    l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                        l_institution,
                        l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません ");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //3)-2)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。
                //投信定時定額買付条件変更テーブルのみに存在する場合、
                //投信定時定額買付条件変更テーブルの内容を投信定時定額買付条件行のプロパティへセットする。
                if (l_mfFixedBuyingChangeRow != null)
                {
                    //[セットする内容]
                    //銘柄コード：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．銘柄コード
                    l_mutualFixedBuyConditionUnit.mutualProductCode =
                        l_mfFixedBuyingChangeRow.getProductCode();
                    //銘柄名：取得した投信銘柄マスタRow.get銘柄名
                    l_mutualFixedBuyConditionUnit.mutualProductName =
                        l_mutualFundProductRow.getStandardName();
                    //投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード
                    l_mutualFixedBuyConditionUnit.categoryCode =
                        l_mutualFundProductRow.getCategoryCode();

                    //変更区分が「1：追加」または「2：変更」の場合、
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                    {
                        //買付金額（月々）：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（月々）
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }

                        //買付金額（積み増し）：
                        //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }

                        //一時停止中フラグ：falseを設定
                        l_mutualFixedBuyConditionUnit.suspensionFlag = false;
                    }
                    //変更区分が『「3：解除」または「4：一時停止」』かつ
                    //『引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!= null』 の場合、
                    else
                    {
                        //買付金額（月々）：　@0
                        l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "0";
                        //買付金額（積み増し）：　@0
                        l_mutualFixedBuyConditionUnit.increaseBuyAmount = "0";
                        //一時停止中フラグ：trueを設定
                        l_mutualFixedBuyConditionUnit.suspensionFlag = true;
                    }

                    //銘柄表示順序：取得した投信銘柄マスタRow.get表示順位
                    if (l_mutualFundProductRow.getIndicationRankingIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder = null;
                    }
                    else
                    {
                        l_mutualFixedBuyConditionUnit.displayOrder =
                            WEB3StringTypeUtility.formatNumber(
                                l_mutualFundProductRow.getIndicationRanking());
                    }
                    //適用開始年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                    l_mutualFixedBuyConditionUnit.validStartDate =
                        l_mfFixedBuyingChangeRow.getValidStartDate();
                    //更新日時：null
                    l_mutualFixedBuyConditionUnit.updateDate = null;
                    //口座引落年月：引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．適用開始年月
                    l_mutualFixedBuyConditionUnit.debitAccountYM =
                        new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                    //確定引落金額（積み増し）：
                    //変更区分が「1：追加」または「2：変更」の場合、
                    //  引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）!=
                    //  引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．買付金額（積み増し）の場合、
                    //  引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）
                    //  それ以外の場合、null
                    if (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                    {
                        l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                    }
                    else if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                    {
                        if (!WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount()).equals(
                                    WEB3StringTypeUtility.formatNumber(l_mfFixedBuyingChangeRow.getIncreaseBuyAmount())))
                        {
                            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                        }
                        else
                        {
                            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                        }
                    }
                    //変更区分が「3：解除」または「4：一時停止」の場合、
                    //引数．定時定額買付条件変更リスト.定時定額買付条件変更Row．確定引落買付金額（積み増し）
                    else
                    {
                        l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount =
                            WEB3StringTypeUtility.formatNumber(
                                l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                    }

                    //目論見書閲覧チェック：null
                    l_mutualFixedBuyConditionUnit.checkResult = null;
                }

                //3)-2)-4)  投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。
                l_lisMutualFixedBuyConditionUnitLists.add(l_mutualFixedBuyConditionUnit);
            }
        }

        //4) 投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisMutualFixedBuyConditionUnitLists.size()];
        l_lisMutualFixedBuyConditionUnitLists.toArray(l_mutualFixedBuyConditionUnits);

        //5) 投信定時定額買付条件行の配列をリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyConditionUnits;
    }

    /**
     * (merge定時定額買付条件（３ヶ月表示）)<BR>
     * ３ヶ月目の定時定額買付条件変更リストの内容と投信定時定額買付条件行（２ヶ月目）の内容をマージして<BR>
     * 投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * <BR>
     * 1)　@定時定額買付共通サービスを取得する。<BR>
     * <BR>
     * 2)　@引数.投信定時定額買付条件行の件数分ループし、投信定時定額買付条件行のリストを作成する。<BR>
     * <BR>
     * 　@2)-1)　@投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@**** 投信定時定額買付条件行(２ヶ月目)の内容を３ヶ月目表示用データに更新する。<BR>
     * 　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@　@　@口座引落年月：引数．投信定時定額買付条件行．口座引落年月＋１<BR>
     * <BR>
     * 　@2-2)投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。<BR>
     * <BR>
     * 3)　@引数.定時定額買付条件変更リスト（３ヶ月目）でループし、引数.定時定額買付条件変更リスト（３ヶ月目）の<BR>
     * 　@内容を投信定時定額買付条件行オブジェクトのリストに反映する。<BR>
     * <BR>
     * 　@3)-1)　@投信定時定額買付条件行のリストでループする。<BR>
     * <BR>
     * 　@　@　@3)-1)-1)　@以下の条件で比較して、一致する場合<BR>
     * 　@　@　@　@　@　@引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．銘柄コード   ==<BR>
     * 　@　@　@　@　@　@　@投信定時定額買付条件行．銘柄コード<BR>
     * <BR>
     * 　@　@　@　@　@3)-1)-1)-1)　@引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．変更区分が<BR>
     * 　@　@　@　@　@　@「1：追加」または「2：変更」の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@3)-1)-1)-1)-1)　@投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブル(３ヶ月目)の内容で投信定時定額買付条件行を上書きする。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@買付金額（月々）：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@買付金額（積み増し）：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@適用開始年月：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@口座引落年月：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月<BR>
     * <BR>
     * 　@　@　@　@　@3)-1)-1)-2)　@引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．変更区分が「3：解除」または「4：一時停止」の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブルの内容を元に投信定時定額買付条件行のリストから削除する。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * <BR>
     * 　@　@　@　@　@3)-1)-1)-3)　@引数.定時定額買付条件変更リスト（３ヶ月目）のループへ戻る。<BR>
     * <BR>
     * 　@3)-2)　@一致するリストが投信定時定額買付行に存在しない場合　@かつ<BR>
     * 　@　@　@　@　@　@　@　@定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．変更区分が「1：追加」または「2：変更」の場合<BR>
     * <BR>
     * 　@　@　@　@　@3)-2)-1)投信定時定額買付条件行オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@　@　@3)-2)-2)　@拡張投信銘柄マネージャー.get投信銘柄()をコールする。<BR>
     * 　@　@　@　@　@　@　@　@　@［get投信銘柄の引数］<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@証券会社：引数.証券会社<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@銘柄コード：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．銘柄コード<BR>
     * <BR>
     * 　@　@　@　@　@3)-2)-3)　@投信定時定額買付条件行オブジェクトのプロパティセット。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@**** 定時定額買付条件変更テーブル（３ヶ月目）の内容を投信定時定額買付条件行のリストへ追加する。<BR>
     * 　@　@　@　@　@　@　@　@　@******************************************************************************************<BR>
     * 　@　@　@　@　@　@　@　@　@[セットする内容]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@銘柄コード：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．銘柄コード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@銘柄名：取得した投信銘柄マスタRow.get銘柄名<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@買付金額（月々）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（月々）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@買付金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@銘柄表示順序：取得した投信銘柄マスタRow.get表示順位<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@適用開始年月：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@更新日時：null<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@口座引落年月：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@確定引落金額（積み増し）：<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．確定引落買付金額（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@目論見書閲覧チェック：null<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@一時停止中フラグ：false<BR>
     * <BR>
     * 　@　@　@　@　@3)-2)-4)投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。<BR>
     * <BR>
     * 4)　@投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。<BR>
     * <BR>
     * 5)　@投信定時定額買付条件行の配列をリターンする。<BR>
     * <BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_mutualFixedBuyConditionUnits - (投信定時定額買付条件行)<BR>
     * 投信定時定額買付条件行<BR>
     * @@param l_lisMutualFixedBuyConditionChangeListThreeMonths - (定時定額買付条件変更リスト（３ヶ月目）)<BR>
     * 定時定額買付条件変更リスト（３ヶ月目）<BR>
     * @@return WEB3MutualFixedBuyConditionUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyConditionUnit[] mergeMutualFixedBuyConditionThreeMonth(
        Institution l_institution,
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits,
        List l_lisMutualFixedBuyConditionChangeListThreeMonths) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mergeMutualFixedBuyConditionThreeMonth("
            + "Institution, WEB3MutualFixedBuyConditionUnit[], List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisMutualFixedBuyConditionChangeListThreeMonths == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        FinApp l_finApp =
            (FinApp)Services.getService(FinApp.class);

        //拡張投信銘柄マネージャを取得する。
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager)l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();

        //1) 定時定額買付共通サービスを取得する。

        //2) 引数.投信定時定額買付条件行の件数分ループし、投信定時定額買付条件行オブジェクトのリストを作成する。
        List l_lisMutualFixedBuyConditionUnitLists = new ArrayList();
        int l_intUnitsLength = 0;
        if (l_mutualFixedBuyConditionUnits != null)
        {
            l_intUnitsLength = l_mutualFixedBuyConditionUnits.length;
        }
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnitsThird =
            new WEB3MutualFixedBuyConditionUnit[l_intUnitsLength];
        for (int i = 0; i < l_intUnitsLength; i++)
        {
            l_mutualFixedBuyConditionUnitsThird[i] =
                new WEB3MutualFixedBuyConditionUnit();
            //2)-1) 投信定時定額買付条件行オブジェクトのプロパティセット。
            //投信定時定額買付条件行(２ヶ月目)の内容を３ヶ月目表示用データに更新する。
            //[セットする内容]
            //口座引落年月：引数．投信定時定額買付条件行．口座引落年月＋１
            l_mutualFixedBuyConditionUnitsThird[i].debitAccountYM =
                new Timestamp(WEB3DateUtility.addMonth(
                    l_mutualFixedBuyConditionUnits[i].debitAccountYM,
                    1).getTime());

            //確定引落金額（積み増し）：null
            l_mutualFixedBuyConditionUnitsThird[i].definiteIncreaseBuyAmount = null;

            l_mutualFixedBuyConditionUnitsThird[i].categoryCode =
                l_mutualFixedBuyConditionUnits[i].categoryCode;
            l_mutualFixedBuyConditionUnitsThird[i].checkResult =
                l_mutualFixedBuyConditionUnits[i].checkResult;
            l_mutualFixedBuyConditionUnitsThird[i].displayOrder =
                l_mutualFixedBuyConditionUnits[i].displayOrder;
            l_mutualFixedBuyConditionUnitsThird[i].increaseBuyAmount =
                l_mutualFixedBuyConditionUnits[i].increaseBuyAmount;
            l_mutualFixedBuyConditionUnitsThird[i].monthlyBuyAmount =
                l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount;
            l_mutualFixedBuyConditionUnitsThird[i].mutualProductCode =
                l_mutualFixedBuyConditionUnits[i].mutualProductCode;
            l_mutualFixedBuyConditionUnitsThird[i].mutualProductName =
                l_mutualFixedBuyConditionUnits[i].mutualProductName;
            l_mutualFixedBuyConditionUnitsThird[i].suspensionFlag =
                l_mutualFixedBuyConditionUnits[i].suspensionFlag;
            l_mutualFixedBuyConditionUnitsThird[i].updateDate =
                l_mutualFixedBuyConditionUnits[i].updateDate;
            l_mutualFixedBuyConditionUnitsThird[i].validStartDate =
                l_mutualFixedBuyConditionUnits[i].validStartDate;
            //2-2)投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。
            l_lisMutualFixedBuyConditionUnitLists.add(l_mutualFixedBuyConditionUnitsThird[i]);
        }

        //3) 引数.定時定額買付条件変更リスト（３ヶ月目）でループし、引数.定時定額買付条件変更リスト（３ヶ月目）の
        //内容を投信定時定額買付条件行オブジェクトのリストに反映する。
        Iterator l_iteratorListThreeMonths =
            l_lisMutualFixedBuyConditionChangeListThreeMonths.iterator();
        while (l_iteratorListThreeMonths.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorListThreeMonths.next();

            String l_strChangeDiv = l_mfFixedBuyingChangeRow.getChangeDiv();

            //3)-1) 投信定時定額買付条件行のリストでループする。
            Iterator l_iteratorUnitLists =
                l_lisMutualFixedBuyConditionUnitLists.iterator();
            boolean l_blnFlag = true;
            while (l_iteratorUnitLists.hasNext())
            {
                WEB3MutualFixedBuyConditionUnit l_unit =
                    (WEB3MutualFixedBuyConditionUnit)l_iteratorUnitLists.next();

                //3)-1)-1) 以下の条件で比較して、一致する場合
                //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．銘柄コード   ==
                //投信定時定額買付条件行．銘柄コード
                if (l_mfFixedBuyingChangeRow.getProductCode().equals(
                    l_unit.mutualProductCode))
                {
                    //3)-1)-1)-1) 引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．変更区分が
                    //「1：追加」または「2：変更」の場合
                    if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
                    {
                        //3)-1)-1)-1)-1) 投信定時定額買付条件行オブジェクトのプロパティセット。
                        //定時定額買付条件変更テーブル(３ヶ月目)の内容で投信定時定額買付条件行を上書きする。
                        //[セットする内容]
                        //買付金額（月々）：
                        //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（月々）
                        if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                        {
                            l_unit.monthlyBuyAmount = null;
                        }
                        else
                        {
                            l_unit.monthlyBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                        }
                        //買付金額（積み増し）：
                        //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（積み増し）
                        if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                        {
                            l_unit.increaseBuyAmount = null;
                        }
                        else
                        {
                            l_unit.increaseBuyAmount =
                                WEB3StringTypeUtility.formatNumber(
                                    l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                        }
                        //適用開始年月：
                        //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月
                        l_unit.validStartDate =
                            l_mfFixedBuyingChangeRow.getValidStartDate();
                        //口座引落年月：
                        //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月
                        l_unit.debitAccountYM =
                            new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                        //一時停止中フラグ:
                        //false
                        l_unit.suspensionFlag = false;
                    }

                    //3)-1)-1)-2) 引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．変更区分が
                    //「3：解除」または「4：一時停止」の場合
                    if (WEB3ChangeDivDef.RELEASE.equals(l_strChangeDiv)
                        || WEB3ChangeDivDef.TEMP_STOP.equals(l_strChangeDiv))
                    {
                        //定時定額買付条件変更テーブルの内容を元に投信定時定額買付条件行のリストから削除する。
                        l_iteratorUnitLists.remove();
                    }

                    l_blnFlag = false;
                    //3)-1)-1)-3) 引数.定時定額買付条件変更リスト（３ヶ月目）のループへ戻る。
                    break;
                }
            }

            if (!l_blnFlag)
            {
                continue;
            }

            //3)-2) 一致するリストが投信定時定額買付行に存在しない場合　@かつ
            //定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．変更区分が
            //「1：追加」または「2：変更」の場合
            if (WEB3ChangeDivDef.ADD.equals(l_strChangeDiv)
                || WEB3ChangeDivDef.CHANGE.equals(l_strChangeDiv))
            {
                //3)-2)-1)投信定時定額買付条件行オブジェクトを生成する。
                WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                    new WEB3MutualFixedBuyConditionUnit();

                //3)-2)-2) 拡張投信銘柄マネージャー.get投信銘柄()をコールする。
                //［get投信銘柄の引数］
                //証券会社：引数.証券会社
                //銘柄コード：
                //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．銘柄コード
                MutualFundProduct l_mutualFundProduct = null;
                try
                {
                    l_mutualFundProduct = l_mutualFundProductManager.getMutualFundProduct(
                        l_institution,
                        l_mfFixedBuyingChangeRow.getProductCode());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません ");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                MutualFundProductRow l_mutualFundProductRow =
                    (MutualFundProductRow)l_mutualFundProduct.getDataSourceObject();
                //3)-2)-3) 投信定時定額買付条件行オブジェクトのプロパティセット。
                //定時定額買付条件変更テーブル（３ヶ月目）の内容を投信定時定額買付条件行のリストへ追加する。
                //[セットする内容]
                //銘柄コード：引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．銘柄コード
                l_mutualFixedBuyConditionUnit.mutualProductCode =
                    l_mutualFundProductRow.getProductCode();
                //銘柄名：取得した投信銘柄マスタRow.get銘柄名
                l_mutualFixedBuyConditionUnit.mutualProductName =
                    l_mutualFundProductRow.getStandardName();
                //投信銘柄カテゴリーコード：取得した投信銘柄マスタRow.getカテゴリコード
                l_mutualFixedBuyConditionUnit.categoryCode =
                    l_mutualFundProductRow.getCategoryCode();
                //買付金額（月々）：
                //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（月々）
                if (l_mfFixedBuyingChangeRow.getMonthlyBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.monthlyBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getMonthlyBuyAmount());
                }
                //買付金額（積み増し）：
                //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．買付金額（積み増し）
                if (l_mfFixedBuyingChangeRow.getIncreaseBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.increaseBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getIncreaseBuyAmount());
                }
                //銘柄表示順序：取得した投信銘柄マスタRow.get表示順位
                if (l_mutualFundProductRow.getIndicationRankingIsNull())
                {
                    l_mutualFixedBuyConditionUnit.displayOrder = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.displayOrder =
                        WEB3StringTypeUtility.formatNumber(
                            l_mutualFundProductRow.getIndicationRanking());
                }
                //適用開始年月：
                //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月
                l_mutualFixedBuyConditionUnit.validStartDate =
                    l_mfFixedBuyingChangeRow.getValidStartDate();
                //更新日時：null
                l_mutualFixedBuyConditionUnit.updateDate = null;
                //口座引落年月：
                //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．適用開始年月
                l_mutualFixedBuyConditionUnit.debitAccountYM =
                    new Timestamp(l_mfFixedBuyingChangeRow.getValidStartDate().getTime());
                //確定引落金額（積み増し）：
                //引数．定時定額買付条件変更リスト（３ヶ月目）.定時定額買付条件変更Row．確定引落買付金額（積み増し）
                if (l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmountIsNull())
                {
                    l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = null;
                }
                else
                {
                    l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount =
                        WEB3StringTypeUtility.formatNumber(
                            l_mfFixedBuyingChangeRow.getFinDrawIncreaseBuyAmount());
                }
                //目論見書閲覧チェック：null
                l_mutualFixedBuyConditionUnit.checkResult = null;
                //一時停止中フラグ：false
                l_mutualFixedBuyConditionUnit.suspensionFlag = false;

                //3)-2)-4)投信定時定額買付条件行オブジェクトを投信定時定額買付条件行のリストへ追加する。
                l_lisMutualFixedBuyConditionUnitLists.add(
                    l_mutualFixedBuyConditionUnit);
            }
        }

        //4) 投信定時定額買付条件行のリストから投信定時定額買付条件行の配列を作成する。
        WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
            new WEB3MutualFixedBuyConditionUnit[l_lisMutualFixedBuyConditionUnitLists.size()];
        l_lisMutualFixedBuyConditionUnitLists.toArray(l_conditionUnits);

        //5) 投信定時定額買付条件行の配列をリターンする。
        log.exiting(STR_METHOD_NAME);
        return l_conditionUnits;
    }

    /**
     * (get指定年月定時定額買付条件リスト)<BR>
     * 引数．定時定額買付条件リストから、口座引落年月が引数．指定年月と等しいレコードを抽出し、<BR>
     * リストにして返却する。<BR>
     * @@param l_lisFixedBuyConditionLists - (定時定額買付条件リスト)<BR>
     * 定時定額買付条件リスト<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getSelectMYFixedBuyConditionList(
        List l_lisFixedBuyConditionLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSelectMYFixedBuyConditionList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionLists == null
            || l_datSelectMY == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        List l_lisSelectYMLists = new ArrayList();
        Iterator l_iteratorConditionLists = l_lisFixedBuyConditionLists.iterator();
        while (l_iteratorConditionLists.hasNext())
        {
            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorConditionLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingCondRow.getDrawDate(),
                    l_datSelectMY);
            if (l_intCompareResult == 0)
            {
                l_lisSelectYMLists.add(l_mfFixedBuyingCondRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSelectYMLists;
    }

    /**
     * (get指定年月定時定額買付条件変更リスト)<BR>
     * 引数．定時定額買付条件変更リストから、適用開始年月が引数．指定年月と等しいレコードを抽出し、<BR>
     * リストにしてを返却する。<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (定時定額買付条件変更リスト)<BR>
     * 定時定額買付条件変更リスト<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getSelectMYFixedBuyConditionChangeList(
        List l_lisFixedBuyConditionChangeLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSelectMYFixedBuyConditionChangeList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionChangeLists == null
            || l_datSelectMY == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        List l_lisSelectYMChangeLists = new ArrayList();
        Iterator l_iteratorConditionChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        while (l_iteratorConditionChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorConditionChangeLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingChangeRow.getValidStartDate(),
                    l_datSelectMY);
            if (l_intCompareResult == 0)
            {
                l_lisSelectYMChangeLists.add(l_mfFixedBuyingChangeRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisSelectYMChangeLists;
    }


    /**
     * (get定時定額買付金額合計)<BR>
     * get定時定額買付金額合計<BR>
     * 定時定額買付金額の合計を計算する。<BR>
     * ３番目オブジェクトは、3ヶ月表示時に画面表示する。<BR>
     * <BR>
     * １．投信定時定額買付金額合計オブジェクトを生成する。（１番目）<BR>
     * ２．投信定時定額買付金額合計オブジェクトを生成する。（２番目）<BR>
     * ３．投信定時定額買付金額合計オブジェクトを生成する。（３番目）<BR>
     * <BR>
     * ４．変数work確定引落金額合計（積み増し）算出フラグ（１番目）を作成し、<BR>
     * 　@　@falseをセットする。<BR>
     * ５．変数work確定引落金額合計（積み増し）算出フラグ（２番目）を作成し、<BR>
     * 　@　@falseをセットする。<BR>
     * ６．変数work確定引落金額合計（積み増し）算出フラグ（３番目）を作成し、<BR>
     * 　@　@falseをセットする。<BR>
     * <BR>
     * ７．投信定時定額買付条件行[]の1レコード目の投信定時定額買付条件行.口座引落年月を<BR>
     * 　@　@変数work口座引落年月にセットする。<BR>
     * <BR>
     * ８．投信定時定額買付条件行[]の件数分、以下の処理を繰返す。<BR>
     * <BR>
     * 　@８-１．投信定時定額買付条件行.口座引落年月　@=　@work口座引落年月の場合<BR>
     * <BR>
     * 　@　@８-１-１．投信定時定額買付金額合計（１番目）.月々合計 =<BR>
     * 　@　@　@　@　@　@投信定時定額買付金額合計（１番目）.月々合計 +<BR>
     * 　@　@　@　@　@　@　@　@投信定時定額買付条件行.買付金額（月々）<BR>
     * <BR>
     * 　@　@８-１-２．投信定時定額買付金額合計（１番目）.積み増し合計 =<BR>
     * 　@　@　@　@　@　@投信定時定額買付金額合計（１番目）.積み増し合計 +<BR>
     * 　@　@　@　@　@　@　@　@投信定時定額買付条件行.買付金額（積み増し）<BR>
     * <BR>
     * 　@　@８-１-３．投信定時定額買付条件行.確定引落金額（積み増し） ≠　@nullの場合<BR>
     * <BR>
     * 　@　@　@　@８-１-３-１．work確定引落金額合計（積み増し）算出フラグ（１番目）にtrueをセットする。<BR>
     * <BR>
     * 　@　@　@　@８-１-３-２．投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し） =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し） +<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@投信定時定額買付条件行.確定引落金額（積み増し）<BR>
     * <BR>
     * 　@　@８-１-４．投信定時定額買付条件行.確定引落金額（積み増し） ==　@nullの場合<BR>
     * <BR>
     * 　@　@　@　@８-１-４-１．投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し） =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 投信定時定額買付条件行.買付金額（積み増し）<BR>
     * <BR>
     * 　@　@８-１-５．投信定時定額買付金額合計（１番目）.口座引落年月 = ｎｕｌｌの場合<BR>
     * <BR>
     * 　@　@　@　@８-１-５-１．投信定時定額買付金額合計（１番目）.口座引落年月 =<BR>
     * 　@　@　@　@　@　@　@投信定時定額買付条件行.口座引落年月<BR>
     * <BR>
     * 　@８-２　@投信定時定額買付条件行.口座引落年月　@==　@work口座引落年月+1の場合<BR>
     * <BR>
     * 　@　@８-２-１．投信定時定額買付金額合計（２番目）.月々合計 =<BR>
     * 　@　@　@　@投信定時定額買付金額合計（２番目）.月々合計 + 投信定時定額買付条件行.買付金額（月々）<BR>
     * <BR>
     * 　@　@８-２-２．投信定時定額買付金額合計（２番目）.積み増し合計 =<BR>
     * 　@　@　@　@投信定時定額買付金額合計（２番目）.積み増し合計<BR>
     * 　@　@　@　@　@　@+ 投信定時定額買付条件行.買付金額（積み増し）<BR>
     * <BR>
     * 　@　@８-２-３．投信定時定額買付条件行.確定引落金額（積み増し） ≠　@nullの場合<BR>
     * <BR>
     * 　@　@　@　@８-２-３-１．work確定引落金額合計（積み増し）算出フラグ（２番目）にtrueをセットする。<BR>
     * <BR>
     * 　@　@　@　@８-２-３-２．投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し） =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 投信定時定額買付条件行.確定引落金額（積み増し）<BR>
     * <BR>
     * 　@　@８-２-４．投信定時定額買付条件行.確定引落金額（積み増し） ==　@nullの場合<BR>
     * <BR>
     * 　@　@　@　@８-２-４-１．投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し） =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 投信定時定額買付条件行.買付金額（積み増し）<BR>
     * <BR>
     * 　@　@８-２-５．投信定時定額買付金額合計（２番目）.口座引落年月 = ｎｕｌｌの場合<BR>
     * <BR>
     * 　@　@　@　@８-２-５-１．投信定時定額買付金額合計（２番目）.口座引落年月 =<BR>
     * 　@　@　@　@　@　@投信定時定額買付条件行.口座引落年月<BR>
     * <BR>
     * 　@８-３　@投信定時定額買付条件行.口座引落年月　@==　@work口座引落年月+2の場合<BR>
     * <BR>
     * 　@　@８-３-１．投信定時定額買付金額合計（３番目）.月々合計 =<BR>
     * 　@　@　@ 投信定時定額買付金額合計（３番目）.月々合計 + 投信定時定額買付条件行.買付金額（月々）<BR>
     * <BR>
     * 　@　@８-３-２．投信定時定額買付金額合計（３番目）.積み増し合計 =<BR>
     * 　@　@　@ 投信定時定額買付金額合計（３番目）.積み増し合計 + 投信定時定額買付条件行.買付金額（積み増し合計）<BR>
     * <BR>
     * 　@　@８-３-３．投信定時定額買付条件行.確定引落金額（積み増し） ≠　@nullの場合<BR>
     * <BR>
     * 　@　@　@　@８-３-３-１．work確定引落金額合計（積み増し）算出フラグ（３番目）にtrueをセットする。<BR>
     * <BR>
     * 　@　@　@　@８-３-３-２．投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し） =<BR>
     * 　@　@　@ 　@　@　@　@　@　@　@　@投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 投信定時定額買付条件行.確定引落金額（積み増し）<BR>
     * <BR>
     * 　@　@８-３-４．投信定時定額買付条件行.確定引落金額（積み増し） ==　@nullの場合<BR>
     * <BR>
     * 　@　@　@　@８-３-４-１．投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し） =<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し）<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ 投信定時定額買付条件行.買付金額（積み増し）<BR>
     * <BR>
     * 　@　@８-３-５．投信定時定額買付金額合計（３番目）.口座引落年月 = ｎｕｌｌの場合<BR>
     * <BR>
     * 　@　@　@　@８-３-５-１．投信定時定額買付金額合計（３番目）.口座引落年月 =<BR>
     * 　@　@　@　@　@　@投信定時定額買付条件行.口座引落年月<BR>
     * <BR>
     * ９．work確定引落金額合計（積み増し）算出フラグ（１番目） == false の場合<BR>
     * <BR>
     * 　@９-１．投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し） = null をセットする。<BR>
     * <BR>
     * １０．work確定引落金額合計（積み増し）算出フラグ（２番目） == false の場合<BR>
     * <BR>
     * 　@１０-１．投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し） = null をセットする。<BR>
     * <BR>
     * １１．work確定引落金額合計（積み増し）算出フラグ（３番目） == false の場合<BR>
     * <BR>
     * 　@１１-１．投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し） = null をセットする。<BR>
     * <BR>
     * １２．戻り値 定時定額買付金額合計[0]に投信定時定額買付金額合計（１番目）をセットし、<BR>
     * 　@　@　@戻り値 定時定額買付金額合計[1]に投信定時定額買付金額合計（２番目）をセットし、<BR>
     * 　@　@　@戻り値 定時定額買付金額合計[2]に投信定時定額買付金額合計（３番目）をセットする。<BR>
     * 　@　@　@（*)nullでない場合、セットする。<BR>
     * @@param l_mutualFixedBuyConditionUnits - (投信定時定額買付条件行[])<BR>
     * 投信定時定額買付条件行[]<BR>
     * @@return WEB3MutualFixedBuyTotalUnit[]<BR>
     * @@throws WEB3BaseException
     */
    protected WEB3MutualFixedBuyTotalUnit[] getFixedBuyTotalUnit(
        WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFixedBuyTotalUnit(WEB3MutualFixedBuyConditionUnit[])";
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

        // １．投信定時定額買付金額合計オブジェクトを生成する。（１番目）
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnitOne = new WEB3MutualFixedBuyTotalUnit();

        // ２．投信定時定額買付金額合計オブジェクトを生成する。（２番目）
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnitTwo = new WEB3MutualFixedBuyTotalUnit();

        //３．投信定時定額買付金額合計オブジェクトを生成する。（３番目）
        WEB3MutualFixedBuyTotalUnit l_mfBuyTotalUnitThree = new WEB3MutualFixedBuyTotalUnit();

        //４．変数work確定引落金額合計（積み増し）算出フラグ（１番目）を作成し、falseをセットする。
        boolean l_blnIsDefiniteIncreaseBATotalFlagOne = false;
        //５．変数work確定引落金額合計（積み増し）算出フラグ（２番目）を作成し、falseをセットする。
        boolean l_blnIsDefiniteIncreaseBATotalFlagTwo = false;
        //６．変数work確定引落金額合計（積み増し）算出フラグ（３番目）を作成し、falseをセットする。
        boolean l_blnIsDefiniteIncreaseBATotalFlagThree = false;
        //７．投信定時定額買付条件行[]の1レコード目の
        //投信定時定額買付条件行.口座引落年月を変数work口座引落年月にセットする。
        Date l_datDebitAccountYM = l_mutualFixedBuyConditionUnits[0].debitAccountYM;

        //８．投信定時定額買付条件行[]の件数分、以下の処理を繰返す。
        int l_intLength = l_mutualFixedBuyConditionUnits.length;
        for (int i = 0; i < l_intLength; i ++)
        {
            //８-１．投信定時定額買付条件行.口座引落年月　@=　@work口座引落年月の場合
            int l_intCompare = WEB3DateUtility.compareToMonth(
                l_datDebitAccountYM, l_mutualFixedBuyConditionUnits[i].debitAccountYM);

            if (l_intCompare == 0)
            {
                //８-１-１．投信定時定額買付金額合計（１番目）.月々合計 =
                //投信定時定額買付金額合計（１番目）.月々合計
                //+ 投信定時定額買付条件行.買付金額（月々）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.monthlyBATotal))
                {
                    l_mfBuyTotalUnitOne.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnitOne.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitOne.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //８-１-２．投信定時定額買付金額合計（１番目）.積み増し合計 =
                //投信定時定額買付金額合計（１番目）.積み増し合計
                //+ 投信定時定額買付条件行.買付金額（積み増し）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.increaseBATotal))
                {
                    l_mfBuyTotalUnitOne.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }

                l_mfBuyTotalUnitOne.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitOne.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //８-１-３．投信定時定額買付条件行.確定引落金額（積み増し） ≠　@nullの場合
                if (l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount != null)
                {
                    //８-１-３-１．work確定引落金額合計（積み増し）算出フラグ（１番目）にtrueをセットする。
                    l_blnIsDefiniteIncreaseBATotalFlagOne = true;
                    //８-１-３-２．投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し） =
                    //投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し）
                    //+ 投信定時定額買付条件行.確定引落金額（積み増し）
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitOne.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitOne.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitOne.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount));
                }
                //８-１-４．投信定時定額買付条件行.確定引落金額（積み増し） ==　@nullの場合
                else
                {
                    //８-１-４-１．投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し） =
                    //投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し）
                    //+ 投信定時定額買付条件行.買付金額（積み増し）
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitOne.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitOne.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                    {
                        l_mfBuyTotalUnitOne.definiteIncreaseBATotal = "0";
                    }

                    l_mfBuyTotalUnitOne.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitOne.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));
                }

                //８-１-５．投信定時定額買付金額合計（１番目）.口座引落年月 = ｎｕｌｌの場合
                if (l_mfBuyTotalUnitOne.debitAccountYM == null)
                {
                    //８-１-５-１．投信定時定額買付金額合計（１番目）.口座引落年月 =
                    //　@投信定時定額買付条件行.口座引落年月
                    l_mfBuyTotalUnitOne.debitAccountYM = l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }

            //８-２　@投信定時定額買付条件行.口座引落年月　@==　@work口座引落年月+1の場合
            l_intCompare = WEB3DateUtility.compareToMonth(
                WEB3DateUtility.addMonth(l_datDebitAccountYM, 1),
                l_mutualFixedBuyConditionUnits[i].debitAccountYM);
            if (l_intCompare == 0)
            {
                //８-２-１．投信定時定額買付金額合計（２番目）.月々合計 =
                //投信定時定額買付金額合計（２番目）.月々合計 + 投信定時定額買付条件行.買付金額（月々）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.monthlyBATotal))
                {
                    l_mfBuyTotalUnitTwo.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnitTwo.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitTwo.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //８-２-２．投信定時定額買付金額合計（２番目）.積み増し合計 =
                //投信定時定額買付金額合計（２番目）.積み増し合計
                //+ 投信定時定額買付条件行.買付金額（積み増し）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.increaseBATotal))
                {
                    l_mfBuyTotalUnitTwo.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }

                l_mfBuyTotalUnitTwo.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitTwo.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //８-２-３．投信定時定額買付条件行.確定引落金額（積み増し） ≠　@nullの場合
                if (l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount != null)
                {
                    //８-２-３-１．work確定引落金額合計（積み増し）算出フラグ（２番目）にtrueをセットする。
                    l_blnIsDefiniteIncreaseBATotalFlagTwo = true;
                    //８-２-３-２．投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し） =
                    //投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し）
                    //+ 投信定時定額買付条件行.確定引落金額（積み増し）
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount));
                }

                //８-２-４．投信定時定額買付条件行.確定引落金額（積み増し） ==　@nullの場合
                else
                {
                    //８-２-４-１．投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し） =
                    //投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し）
                    //+ 投信定時定額買付条件行.買付金額（積み増し）
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                    {
                        l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = "0";
                    }

                    l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitTwo.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));
                }

                //８-２-５．投信定時定額買付金額合計（２番目）.口座引落年月 = ｎｕｌｌの場合
                if (l_mfBuyTotalUnitTwo.debitAccountYM == null)
                {
                    //８-２-５-１．投信定時定額買付金額合計（２番目）.口座引落年月 =
                    //投信定時定額買付条件行.口座引落年月
                    l_mfBuyTotalUnitTwo.debitAccountYM =
                        l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }

            //８-３　@投信定時定額買付条件行.口座引落年月　@==　@work口座引落年月+2の場合
            l_intCompare = WEB3DateUtility.compareToMonth(
                WEB3DateUtility.addMonth(l_datDebitAccountYM, 2),
                l_mutualFixedBuyConditionUnits[i].debitAccountYM);
            if (l_intCompare == 0)
            {
                //８-３-１．投信定時定額買付金額合計（３番目）.月々合計 =
                //投信定時定額買付金額合計（３番目）.月々合計
                //+ 投信定時定額買付条件行.買付金額（月々）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.monthlyBATotal))
                {
                    l_mfBuyTotalUnitThree.monthlyBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount = "0";
                }

                l_mfBuyTotalUnitThree.monthlyBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitThree.monthlyBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].monthlyBuyAmount));

                //８-３-２．投信定時定額買付金額合計（３番目）.積み増し合計 =
                //投信定時定額買付金額合計（３番目）.積み増し合計
                //+ 投信定時定額買付条件行.買付金額（積み増し合計）
                if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.increaseBATotal))
                {
                    l_mfBuyTotalUnitThree.increaseBATotal = "0";
                }

                if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                {
                    l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                }

                l_mfBuyTotalUnitThree.increaseBATotal = WEB3StringTypeUtility.formatNumber(
                    Double.parseDouble(l_mfBuyTotalUnitThree.increaseBATotal)
                    + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));

                //８-３-３．投信定時定額買付条件行.確定引落金額（積み増し） ≠　@nullの場合
                if (l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount != null)
                {
                    //８-３-３-１．work確定引落金額合計（積み増し）算出フラグ（３番目）にtrueをセットする。
                    l_blnIsDefiniteIncreaseBATotalFlagThree = true;
                    //８-３-３-２．投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し） =
                    //投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し）
                    //+ 投信定時定額買付条件行.確定引落金額（積み増し）
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitThree.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitThree.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitThree.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].definiteIncreaseBuyAmount));
                }

                //８-３-４．投信定時定額買付条件行.確定引落金額（積み増し） ==　@nullの場合
                else
                {
                    //８-３-４-１．投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し） =
                    //投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し）
                    //+ 投信定時定額買付条件行.買付金額（積み増し）
                    if (WEB3StringTypeUtility.isEmpty(l_mfBuyTotalUnitThree.definiteIncreaseBATotal))
                    {
                        l_mfBuyTotalUnitThree.definiteIncreaseBATotal = "0";
                    }

                    if (WEB3StringTypeUtility.isEmpty(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount))
                    {
                        l_mutualFixedBuyConditionUnits[i].increaseBuyAmount = "0";
                    }

                    l_mfBuyTotalUnitThree.definiteIncreaseBATotal = WEB3StringTypeUtility.formatNumber(
                        Double.parseDouble(l_mfBuyTotalUnitThree.definiteIncreaseBATotal)
                        + Double.parseDouble(l_mutualFixedBuyConditionUnits[i].increaseBuyAmount));
                }

                //８-３-５．投信定時定額買付金額合計（３番目）.口座引落年月 = ｎｕｌｌの場合
                if (l_mfBuyTotalUnitThree.debitAccountYM == null)
                {
                    //８-３-５-１．投信定時定額買付金額合計（３番目）.口座引落年月 =
                    //投信定時定額買付条件行.口座引落年月
                    l_mfBuyTotalUnitThree.debitAccountYM =
                        l_mutualFixedBuyConditionUnits[i].debitAccountYM;
                }
            }
        }

        //９．work確定引落金額合計（積み増し）算出フラグ（１番目） == false の場合
        //９-１．投信定時定額買付金額合計（１番目）.確定引落金額合計（積み増し） = null をセットする。
        if (!l_blnIsDefiniteIncreaseBATotalFlagOne)
        {
            l_mfBuyTotalUnitOne.definiteIncreaseBATotal = null;
        }

        //１０．work確定引落金額合計（積み増し）算出フラグ（２番目） == false の場合
        //１０-１．投信定時定額買付金額合計（２番目）.確定引落金額合計（積み増し） = null をセットする。
        if (!l_blnIsDefiniteIncreaseBATotalFlagTwo)
        {
            l_mfBuyTotalUnitTwo.definiteIncreaseBATotal = null;
        }

        //１１．work確定引落金額合計（積み増し）算出フラグ（３番目） == false の場合
        //１１-１．投信定時定額買付金額合計（３番目）.確定引落金額合計（積み増し） = null をセットする。
        if (!l_blnIsDefiniteIncreaseBATotalFlagThree)
        {
            l_mfBuyTotalUnitThree.definiteIncreaseBATotal = null;
        }

        //１２．戻り値 定時定額買付金額合計[0]に投信定時定額買付金額合計（１番目）をセットし、
        //戻り値 定時定額買付金額合計[1]に投信定時定額買付金額合計（２番目）をセットし、
        //戻り値 定時定額買付金額合計[2]に投信定時定額買付金額合計（３番目）をセットする。
        //（*)nullでない場合、セットする。
        List l_lisMutualFixedBuyTotalUnits = new ArrayList();
        l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnitOne);
        if (l_mfBuyTotalUnitTwo.debitAccountYM != null
            && l_mfBuyTotalUnitTwo.increaseBATotal != null)
        {
            l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnitTwo);
        }

        if (l_mfBuyTotalUnitThree.debitAccountYM != null
            && l_mfBuyTotalUnitThree.increaseBATotal != null)
        {
            l_lisMutualFixedBuyTotalUnits.add(l_mfBuyTotalUnitThree);
        }

        WEB3MutualFixedBuyTotalUnit[] l_mutualFixedBuyTotalUnits =
            new WEB3MutualFixedBuyTotalUnit[l_lisMutualFixedBuyTotalUnits.size()];

        l_lisMutualFixedBuyTotalUnits.toArray(l_mutualFixedBuyTotalUnits);
        log.exiting(STR_METHOD_NAME);
        return l_mutualFixedBuyTotalUnits;
    }

    /**
     * (get未来月定時定額買付条件リスト)<BR>
     * 引数．定時定額買付条件リストから、口座引落年月＞引数．指定年月の条件に一致するレコードを抽出し、<BR>
     * リストにして返却する。<BR>
     * @@param l_lisFixedBuyConditionLists - (定時定額買付条件リスト)<BR>
     * 定時定額買付条件リスト<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getFutureFixedBuyConditionList(
        List l_lisFixedBuyConditionLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFutureFixedBuyConditionList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionLists == null
            || l_datSelectMY == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        List l_lisFutureLists = new ArrayList();
        Iterator l_iteratorConditionLists =
            l_lisFixedBuyConditionLists.iterator();
        while (l_iteratorConditionLists.hasNext())
        {
            MfFixedBuyingCondRow l_mfFixedBuyingCondRow =
                (MfFixedBuyingCondRow)l_iteratorConditionLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingCondRow.getDrawDate(),
                    l_datSelectMY);
            if (l_intCompareResult > 0)
            {
                l_lisFutureLists.add(l_mfFixedBuyingCondRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisFutureLists;
    }

    /**
     * (get未来月定時定額買付条件変更リスト)<BR>
     * 引数．定時定額買付条件変更リストから、適用開始年月＞引数．指定年月の条件に一致するレコードを抽出し、<BR>
     * リストにして返却する。<BR>
     * @@param l_lisFixedBuyConditionChangeLists - (定時定額買付条件変更リスト)<BR>
     * 定時定額買付条件変更リスト<BR>
     * @@param l_datSelectMY - (指定年月)<BR>
     * 指定年月<BR>
     * @@return List
     * @@throws WEB3BaseException
     */
    protected List getFutureFixedBuyConditionChangeList(
        List l_lisFixedBuyConditionChangeLists,
        Date l_datSelectMY) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFutureFixedBuyConditionChangeList(List, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_lisFixedBuyConditionChangeLists == null
            || l_datSelectMY == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        List l_lisFutureChangeLists = new ArrayList();
        Iterator l_iteratorConditionChangeLists =
            l_lisFixedBuyConditionChangeLists.iterator();
        while (l_iteratorConditionChangeLists.hasNext())
        {
            MfFixedBuyingChangeRow l_mfFixedBuyingChangeRow =
                (MfFixedBuyingChangeRow)l_iteratorConditionChangeLists.next();

            int l_intCompareResult =
                WEB3DateUtility.compareToMonth(
                    l_mfFixedBuyingChangeRow.getValidStartDate(),
                    l_datSelectMY);
            if (l_intCompareResult > 0)
            {
                l_lisFutureChangeLists.add(l_mfFixedBuyingChangeRow);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisFutureChangeLists;
    }
}
@
