head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン共通(WEB3AdminAccInfoCampaignCommon.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 栄イ (中訊) 新規作成
Revision History : 2007/02/07 吉麗ナ (中訊) 仕様変更モデル189、192
Revision History : 2007/02/16 吉麗ナ (中訊) 仕様変更モデル194～197
Revision History : 2007/02/28 Inomata(SCS)モデルNo.202
Revision History : 2007/03/07 Inomata(SCS)モデルNo.209
*/
package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.accountinfo.data.CommCampaignCondMstDao;
import webbroker3.accountinfo.data.CommCampaignCondMstPK;
import webbroker3.accountinfo.data.CommCampaignCondMstParams;
import webbroker3.accountinfo.data.CommCampaignCondMstRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegistTypeDef;
import webbroker3.accountinfo.define.WEB3AccInfoTargetPeriodCheckDef;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviListRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
//import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeAccountManager;
//import webbroker3.gentrade.WEB3GentradeBizDate;
//import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 手数料割引キャンペーン共通<BR>
 * 手数料割引キャンペーン共通サービス<BR>
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignCommon
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminAccInfoCampaignCommon.class);

    /**
     * 翌営業日
     */
    private static final int BIZDATE_T1 = 1;

    /**
     * 翌々営業日
     */
    private static final int BIZDATE_T2 = 2;

    /**
     * 日付フォーマット文字列
     */
    private static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";

    private static final String BLANK = "";

    private static WEB3AdminAccInfoCampaignCommon accInfoCampaignCommon = new WEB3AdminAccInfoCampaignCommon();


    /**
     * @@roseuid 45C08B5301F4
     */
    protected WEB3AdminAccInfoCampaignCommon()
    {

    }

    /**
     * 手数料割引キャンペーン共通のインスタンスを取得
     */
    public static WEB3AdminAccInfoCampaignCommon getInstance()
    {
        return accInfoCampaignCommon;
    }

    /**
     * 手数料割引キャンペーン共通のインスタンスを設定
     */
    public static void setInstance(WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon)
    {
        accInfoCampaignCommon = l_accInfoCampaignCommon;
    }

    /**
     * (createソート条件文字列)<BR>
     * ソート条件文字列を生成する。<BR>
     * <BR>
     * テーブル列物理名を使用し、対応するソート文字列（order by句）を編集する。<BR>
     * <BR>
     * １） 戻り値生成<BR>
     *     戻り値のソート条件文字列インスタンス（：StringBuffer）を生成する。<BR>
     * <BR>
     * ２） （引数）ソートキーの要素数分、以下処理を行い、ソート文字列を作成する。<BR>
     *   ２-１） （引数）ソートキーが示すキー項目を、<BR>
     *          手数料割引キャンペーン条件マスタ列物理名に変換し、ソート文字列に追加する。<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.部店コードの場合、<BR>
     *            手数料割引キャンペーン条件マスタ.部店コード<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.顧客コードの場合、<BR>
     *            手数料割引キャンペーン条件マスタ.顧客コード<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.徴収率の場合、<BR>
     *            手数料割引キャンペーン条件マスタ.徴収率<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.扱者コードの場合、<BR>
     *            手数料割引キャンペーン条件マスタ.扱者コード<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.口座開設日Fromの場合、<BR>
     *            手数料割引キャンペーン条件マスタ.口座開設日From<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.口座開設日Toの場合、<BR>
     *            手数料割引キャンペーン条件マスタ.口座開設日To<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.口座開設日登録日の場合、<BR>
     *            手数料割引キャンペーン条件マスタ.登録日<BR>
     * <BR>
     * 　@       -ソートキー.キー項目.口座開設日更新日の場合、<BR>
     *            手数料割引キャンペーン条件マスタ.更新日<BR>
     * <BR>
     *   ２-２） ソートキー.昇順／降順に対応するソート順序（asc or desc）をソート条件文字列に追加する。<BR>
     * <BR>
     * ３） 作成したソート条件文字列インスタンス.toString()を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソート条件の配列<BR>
     * @@return String
     * @@roseuid 45AC9AC303DA
     */
    protected String createSortCondition(WEB3AccInfoSortKey[] l_sortKeys)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCondition(WEB3AccInfoSortKey)";
        log.entering(STR_METHOD_NAME);

        if (l_sortKeys == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１） 戻り値生成
        //    戻り値のソート条件文字列インスタンス（：StringBuffer）を生成する。
        StringBuffer l_sbSortCondition = new StringBuffer();

        //２） （引数）ソートキーの要素数分、以下処理を行い、ソート文字列を作成する。
        //２-１） （引数）ソートキーが示すキー項目を、
        //       手数料割引キャンペーン条件マスタ列物理名に変換し、ソート文字列に追加する。
        //-ソートキー.キー項目.部店コードの場合、
        //手数料割引キャンペーン条件マスタ.部店コード
        //-ソートキー.キー項目.顧客コードの場合、
        //手数料割引キャンペーン条件マスタ.顧客コード
        //-ソートキー.キー項目.徴収率の場合、
        //手数料割引キャンペーン条件マスタ.徴収率
        //-ソートキー.キー項目.扱者コードの場合、
        //手数料割引キャンペーン条件マスタ.扱者コード
        //-ソートキー.キー項目.口座開設日Fromの場合、
        //手数料割引キャンペーン条件マスタ.口座開設日From
        //-ソートキー.キー項目.口座開設日Toの場合、
        //手数料割引キャンペーン条件マスタ.口座開設日To
        //-ソートキー.キー項目.口座開設日登録日の場合、
        //手数料割引キャンペーン条件マスタ.登録日
        //-ソートキー.キー項目.口座開設日更新日の場合、
        //手数料割引キャンペーン条件マスタ.更新日
        //２-２） ソートキー.昇順／降順に対応するソート順序（asc or desc）をソート条件文字列に追加する。
        Map l_mapTransform = new HashMap();
        l_mapTransform.put(WEB3AccInfoKeyItemDef.BRANCH_CODE, " branch_code ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.ACCOUNT_CODE, " account_code ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.COLLECT_RATE, " account_charge_ratio ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.TRADER_CODE, " sonar_trader_code ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_FROM, " acc_open_date_from ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_TO, " acc_open_date_to ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.REGIST_DATE, " created_timestamp ");
        l_mapTransform.put(WEB3AccInfoKeyItemDef.UPDATED_DATE, " last_updated_timestamp ");
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            l_sbSortCondition.append(l_mapTransform.get(l_sortKeys[i].keyItem));
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCondition.append(" asc ");
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCondition.append(" desc ");
            }
            if ((i + 1) != l_sortKeys.length)
            {
                l_sbSortCondition.append(", ");
            }
            else
            {
                l_sbSortCondition.append(" ");
            }
        }

        //３） 作成したソート条件文字列インスタンス.toString()を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCondition.toString();
    }

    /**
     * (create検索データコンテナ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）を生成 <BR>
     * <BR>
     * ２）　@手数料割引キャンペーン条件ID条件追加<BR>
     * 　@手数料割引キャンペーン条件ID  != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに手数料割引キャンペーン条件ID条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.手数料割引キャンペーン条件ID <BR>
     * <BR>
     * ３）　@証券会社コード条件追加 <BR>
     * 　@証券会社コード != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに、証券会社コードを追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.証券会社コード <BR>
     * <BR>
     * ４）　@部店コード条件追加 <BR>
     * 　@部店コード != null && 部店コード != ""の場合、<BR>
     * 検索条件データコンテナインスタンスに部店コード条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.部店コード<BR>
     * <BR>
     * ５）　@顧客コード条件追加 <BR>
     * 　@顧客コード != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに顧客コード条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.顧客コード + "%"<BR>
     * <BR>
     * ６）　@商品コード条件追加 <BR>
     * 　@商品コード != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに商品コード条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.商品コード<BR>
     * <BR>
     * ７）　@徴収率条件追加 <BR>
     * 　@徴収率 != null の場合、<BR>
     * 検索条件データコンテナインスタンスに徴収率条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.徴収率<BR>
     * <BR>
     * ８）　@扱者コード条件追加 <BR>
     * 　@扱者コード != null && 扱者コード != ""の場合、<BR>
     * 検索条件データコンテナインスタンスに扱者コード条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.扱者コード <BR>
     * <BR>
     * ９）　@口座開設区分条件追加 <BR>
     * 　@口座開設区分 != null && 口座開設区分 != ""の場合、<BR>
     * 検索条件データコンテナインスタンスに口座開設区分条件を追加する。 <BR>
     * <BR>
     * 　@[add()に指定する引数] <BR>
     * 　@（引数）検索項目.口座開設区分<BR>
     * <BR>
     * １０）　@対象日条件追加  <BR>
     * 　@対象日 != nullの場合、検索条件データコンテナインスタンスに対象日条件を追加する。  <BR>
     * <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.対象日 <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.対象日 <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.対象日 <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.対象日 <BR>
     * <BR>
     * １１）　@対象期間From条件追加  <BR>
     * 　@対象期間From != null && 対象期間From != ""の場合、検索条件データコンテナインスタンスに対象期間From条件を追加する。  <BR>
     * <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.対象期間From <BR>
     * <BR>
     * １２）　@口座開設日From条件追加  <BR>
     * 　@口座開設日From != nullの場合、検索条件データコンテナインスタンスに口座開設日From条件を追加する。  <BR>
     * <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.口座開設日From <BR>
     * <BR>
     * １３）　@削除フラグ条件追加  <BR>
     * 　@削除フラグ != nullの場合、検索条件データコンテナインスタンスに削除フラグ条件を追加する。  <BR>
     * <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.削除フラグ <BR>
     * <BR>
     * １４）　@キャンペーン名称条件追加  <BR>
     * 　@キャンペーン名称 != nullの場合、検索条件データコンテナインスタンスにキャンペーン名称条件を追加する。  <BR>
     * <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@"%" + （引数）検索項目.キャンペーン名称 + "%" <BR>
     * <BR>
     * １５）　@登録タイプ条件追加  <BR>
     * 　@登録タイプ != nullの場合、検索条件データコンテナインスタンスに登録タイプ条件を追加する。  <BR>
     *       <BR>
     * 　@[add()に指定する引数]  <BR>
     * 　@（引数）検索項目.登録タイプ <BR>
     * <BR>
     * １６）　@配列を返却  <BR>
     * 　@戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。 <BR>
     * @@param l_searchCondition - (検索項目)<BR>
     * キャンペーン検索条件<BR>
     * @@return String[]
     * @@roseuid 45AC585D02CE
     */
    protected Object[] createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition l_searchCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSearchContainers(WEB3AdminAccInfoCampaignSearchCondition)";
        log.entering(STR_METHOD_NAME);

        if (l_searchCondition == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１）　@戻り値生成
        //戻り値編集用インスタンス（：ArrayList）を生成
        List l_lisSearchContainers = new ArrayList();

        //２）　@手数料割引キャンペーン条件ID条件追加
        //手数料割引キャンペーン条件ID  != nullの場合、
        //検索条件データコンテナインスタンスに手数料割引キャンペーン条件ID条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.手数料割引キャンペーン条件ID
        if (l_searchCondition.commissionCampaignConditionId != null)
        {
            l_lisSearchContainers.add(l_searchCondition.commissionCampaignConditionId);
        }

        //３）　@証券会社コード条件追加
        //証券会社コード != nullの場合、検索条件データコンテナインスタンスに、証券会社コードを追加する。
        //[add()に指定する引数]
        //（引数）検索項目.証券会社コード
        if (l_searchCondition.institutionCode != null)
        {
            l_lisSearchContainers.add(l_searchCondition.institutionCode);
        }

        //４）　@部店コード条件追加
        //部店コード != null && l_searchCondition.branchCode != ""の場合、検索条件データコンテナインスタンスに部店コード条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.部店コード
        if (l_searchCondition.branchCode != null && !BLANK.equals(l_searchCondition.branchCode))
        {
            l_lisSearchContainers.add(l_searchCondition.branchCode);
        }

        //５）　@顧客コード条件追加
        //顧客コード != nullの場合、検索条件データコンテナインスタンスに顧客コード条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.顧客コード + "%"
        if (l_searchCondition.accountCode != null)
        {
            l_lisSearchContainers.add(l_searchCondition.accountCode + "%");
        }

        //６）　@商品コード条件追加
        //商品コード != nullの場合、検索条件データコンテナインスタンスに商品コード条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.商品コード
        if (l_searchCondition.itemCodes != null)
        {
            for (int i = 0; i < l_searchCondition.itemCodes.length; i++)
            {
                l_lisSearchContainers.add(l_searchCondition.itemCodes[i]);
            }
        }

        //７）　@徴収率条件追加
        //徴収率 != null の場合、検索条件データコンテナインスタンスに徴収率条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.徴収率
        if (l_searchCondition.collectRate != null)
        {
            l_lisSearchContainers.add(l_searchCondition.collectRate);
        }

        //８）　@扱者コード条件追加
        //扱者コード != null && 扱者コード != ""の場合、検索条件データコンテナインスタンスに扱者コード条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.扱者コード
        if (l_searchCondition.traderCode != null && !BLANK.equals(l_searchCondition.traderCode))
        {
            l_lisSearchContainers.add(l_searchCondition.traderCode);
        }

        //９）　@口座開設区分条件追加
        //口座開設区分 != null && 口座開設区分 != ""の場合、検索条件データコンテナインスタンスに口座開設区分条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.口座開設区分
        if (l_searchCondition.accountOpenDiv != null && !BLANK.equals(l_searchCondition.accountOpenDiv))
        {
            l_lisSearchContainers.add(l_searchCondition.accountOpenDiv);
        }

        //１０）　@対象日条件追加
        //対象日 != nullの場合、検索条件データコンテナインスタンスに対象日条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.対象日
        //[add()に指定する引数]
        //（引数）検索項目.対象日
        //[add()に指定する引数]
        //（引数）検索項目.対象日
        //[add()に指定する引数]
        //（引数）検索項目.対象日
        if (l_searchCondition.targetDate != null)
        {
            l_lisSearchContainers.add(l_searchCondition.targetDate);
            l_lisSearchContainers.add(l_searchCondition.targetDate);
            l_lisSearchContainers.add(l_searchCondition.targetDate);
            l_lisSearchContainers.add(l_searchCondition.targetDate);
        }

        //１１）　@対象期間From条件追加
        //対象期間From != null && 対象期間From != ""の場合、検索条件データコンテナインスタンスに対象期間From条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.対象期間From
        if (l_searchCondition.targetPeriodFrom != null && !BLANK.equals(l_searchCondition.targetPeriodFrom))
        {
            l_lisSearchContainers.add(
                WEB3DateUtility.getDate(l_searchCondition.targetPeriodFrom,YYYYMMDD_DATE_FORMAT));
        }

        //１２）　@口座開設日From条件追加
        //口座開設日From != nullの場合、検索条件データコンテナインスタンスに口座開設日From条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.口座開設日From
        if (l_searchCondition.accountOpenDateFrom != null)
        {
            l_lisSearchContainers.add(l_searchCondition.accountOpenDateFrom);
        }

        //１３）　@削除フラグ条件追加
        //削除フラグ != nullの場合、検索条件データコンテナインスタンスに削除フラグ条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.削除フラグ
        if (l_searchCondition.deleteFlag != null)
        {
            l_lisSearchContainers.add(l_searchCondition.deleteFlag);
        }

        //１４）　@キャンペーン名称条件追加
        //キャンペーン名称 != nullの場合、検索条件データコンテナインスタンスにキャンペーン名称条件を追加する。
        //[add()に指定する引数]
        //"%" + （引数）検索項目.キャンペーン名称 + "%"
        if (l_searchCondition.campaignName != null)
        {
            l_lisSearchContainers.add("%" + l_searchCondition.campaignName + "%");
        }

        //１５）　@登録タイプ条件追加
        //登録タイプ != nullの場合、検索条件データコンテナインスタンスに登録タイプ条件を追加する。
        //[add()に指定する引数]
        //（引数）検索項目.登録タイプ
        if (l_searchCondition.registerTypes != null)
        {
            for (int i = 0; i < l_searchCondition.registerTypes.length; i++)
            {
                l_lisSearchContainers.add(l_searchCondition.registerTypes[i]);
            }
        }

        //１６）　@配列を返却
        //戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。
        Object[] l_queryContainers = new Object[l_lisSearchContainers.size()];
        l_queryContainers = l_lisSearchContainers.toArray();
        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。 <BR>
     * <BR>
     * １）　@戻り値生成 <BR>
     * 　@戻り値の検索条件文字列インスタンス（：StringBuffer）を生成 <BR>
     * <BR>
     * ２）　@手数料割引キャンペーン条件ID条件追加<BR>
     * 　@手数料割引キャンペーン条件ID  != nullの場合、<BR>
     * 手数料割引キャンペーン条件ID条件を追加する。 <BR>
     * <BR>
     *   " campaign_id = ?"<BR>
     * <BR>
     * ３）　@証券会社コード条件追加 <BR>
     * 　@証券会社コード条件を追加する。 <BR>
     * <BR>
     *   ３-１） StringBuffer長 > 0の場合<BR>
     * 　@           " and institution_code = ? "<BR>
     * <BR>
     *   ３-１） StringBuffer長 == 0 の場合<BR>
     * 　@           " institution_code = ? "<BR>
     * <BR>
     * ４）　@部店コード条件追加 <BR>
     * 　@４-１） 部店コード != null && 部店コード != ""の場合 <BR>
     * <BR>
     * 　@" and branch_code = ? " <BR>
     * <BR>
     * 　@４-２） 部店コード == ""の場合 <BR>
     * <BR>
     * 　@" and branch_code is null " <BR>
     * <BR>
     * ５）　@顧客コード条件追加 <BR>
     * 　@顧客コード != nullの場合、顧客コード条件を追加する。 <BR>
     * <BR>
     * 　@" and account_code like ? " <BR>
     * <BR>
     * ６）　@商品コード条件追加 <BR>
     * 　@商品コード != null or 商品コード長 > 0 の場合、商品コード条件を追加する。 <BR>
     *   ６-１） 商品コードが長さ１の場合<BR>
     * 　@  "and campaign_id in <BR>
     * ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )" <BR>
     *   ６-２） 商品コード長さ > 1 の場合<BR>
     * 　@  "and campaign_id in <BR>
     * ( select campaign_id from comm_campaign_product_mst where comm_product_code in (?,?,?,…))" <BR>
     *      ※商品コード長さ回数"?"を付加する。<BR>
     * <BR>
     * ７）　@徴収率条件追加 <BR>
     * 　@徴収率 != null の場合、徴収率条件を追加する。 <BR>
     * <BR>
     * 　@"and account_charge_ratio = ? " <BR>
     * <BR>
     * ８）　@扱者コード条件追加 <BR>
     * 　@８-１） 扱者コード != null && 扱者コード != ""の場合 <BR>
     * <BR>
     * 　@" and sonar_trader_code = ? " <BR>
     * <BR>
     * 　@８-２） 扱者コード == ""の場合 <BR>
     * <BR>
     * 　@" and sonar_trader_code is null " <BR>
     * <BR>
     * ９）　@口座開設区分条件追加 <BR>
     * 　@９-１） 口座開設区分 != null && 口座開設区分 != ""の場合 <BR>
     * <BR>
     * 　@" and acc_open_kind_div = ? " <BR>
     * <BR>
     * 　@９-２） 口座開設区分 == ""の場合 <BR>
     * <BR>
     * 　@" and acc_open_kind_div is null " <BR>
     * <BR>
     * １０）　@対象日条件追加  <BR>
     * 　@対象日 != nullの場合、対象日条件を追加する。  <BR>
     * <BR>
     *   " and (((appli_start_date <= ? OR appli_start_date IS NULL) AND <BR>
     *  (appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL)<BR>
     *  OR (acc_open_date_from <= ?<BR>
     *  AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ?<BR>
     *  AND acc_open_pass_month IS NOT NULL)<BR>
     *  OR (appli_start_date IS NULL AND appli_end_date IS NULL<BR>
     *  AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL))" <BR>
     * <BR>
     * １１）　@対象期間From条件追加  <BR>
     *   １１-１） 対象期間From != null && 対象期間From != ""の場合<BR>
     * <BR>
     * 　@" and appli_start_date = ? "<BR>
     * <BR>
     *   １１-２） 対象期間From == ""の場合<BR>
     * <BR>
     * 　@" and appli_start_date is null "  <BR>
     * <BR>
     * １２）　@口座開設日From条件追加   <BR>
     * 　@口座開設日From != nullの場合、口座開設日From条件を追加する。 <BR>
     * <BR>
     * 　@" and acc_open_date_from = ? "   <BR>
     * <BR>
     * １３）　@削除フラグ条件追加  <BR>
     * 　@削除フラグ != nullの場合、削除フラグ条件を追加する。  <BR>
     * <BR>
     * 　@" and delete_flag = ? "  <BR>
     * <BR>
     * １４）　@キャンペーン名称条件追加  <BR>
     * 　@キャンペーン名称 != nullの場合、キャンペーン名称条件を追加する。  <BR>
     * <BR>
     * 　@" and comm_campaign_name like ? "  <BR>
     * <BR>
     * １５）　@登録タイプ条件追加  <BR>
     * 　@登録タイプ != null or 登録タイプ長 > 0 の場合、登録タイプ条件を追加する。  <BR>
     *   １５-１） 登録タイプが長さ１の場合 <BR>
     * 　@  "and regist_type  = ? "  <BR>
     *   １５-２） 登録タイプ長さ > 1 の場合 <BR>
     * 　@  "and regist_type in ( ?,?,?,…)"  <BR>
     *      ※登録タイプ長さ回数"?"を付加する。 <BR>
     * <BR>
     * １６）検索条件文字列インスタンス.toString() を返却する <BR>
     * @@param l_searchCondition - (検索項目)<BR>
     * キャンペーン検索条件オブジェクト<BR>
     * @@return String
     * @@roseuid 45AC56D00296
     */
    protected String createSearchCondition(WEB3AdminAccInfoCampaignSearchCondition l_searchCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSearchCondition(WEB3AdminAccInfoCampaignSearchCondition)";
        log.entering(STR_METHOD_NAME);

        if (l_searchCondition == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１）　@戻り値生成
        //戻り値の検索条件文字列インスタンス（：StringBuffer）を生成
        StringBuffer l_sbSearchCondition = new StringBuffer();

        //２）　@手数料割引キャンペーン条件ID条件追加
        //手数料割引キャンペーン条件ID  != nullの場合、手数料割引キャンペーン条件ID条件を追加する。
        //" campaign_id = ?"
        if (l_searchCondition.commissionCampaignConditionId != null)
        {
            l_sbSearchCondition.append(" campaign_id = ?");
        }

        //３）　@証券会社コード条件追加
        //証券会社コード != nullの場合、条件を追加する。
        if (l_searchCondition.institutionCode != null)
        {
            //３-１） StringBuffer長 > 0の場合
            //" and institution_code = ? "
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and institution_code = ? ");
            }
            //３-１） StringBuffer長 == 0 の場合
            //" institution_code = ? "
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" institution_code = ? ");
            }
        }

        //４）　@部店コード条件追加
        //部店コード != null && 部店コード != ""の場合
        //" and branch_code = ? "
        //部店コード == ""の場合
        //" and branch_code is null "
        if (l_searchCondition.branchCode != null && !BLANK.equals(l_searchCondition.branchCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and branch_code = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" branch_code = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.branchCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and branch_code is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" branch_code is null ");
            }

        }

        //５）　@顧客コード条件追加
        //顧客コード != nullの場合、顧客コード条件を追加する。
        //" and account_code like ? "
        if (l_searchCondition.accountCode != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and account_code like ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" account_code like ? ");
            }
         }

        //６）　@商品コード条件追加
        //商品コード != null or 商品コード長 > 0 の場合、商品コード条件を追加する。
        //６-１） 商品コードが長さ１の場合
        //"and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )"
        if ((l_searchCondition.itemCodes != null) && (l_searchCondition.itemCodes.length == 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(
                    "and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? ) ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(
                    "campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? ) ");
            }
        }
        //６-２） 商品コード長さ > 1 の場合
        //"and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code in (?,?,?,…))"
        //※商品コード長さ回数"?"を付加する。
        else if ((l_searchCondition.itemCodes != null) && (l_searchCondition.itemCodes.length > 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(
                    " and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code in (");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(
                    " campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code in (");
            }
            for (int i = 0; i < l_searchCondition.itemCodes.length; i++)
            {
                l_sbSearchCondition.append("?");
                if (i != l_searchCondition.itemCodes.length - 1)
                {
                    l_sbSearchCondition.append(", ");
                }
            }
            l_sbSearchCondition.append("))");
        }

        //７）　@徴収率条件追加
        //徴収率 != null の場合、徴収率条件を追加する。
        //"and account_charge_ratio = ? "
        if (l_searchCondition.collectRate != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and account_charge_ratio = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" account_charge_ratio = ? ");
            }
        }

        //８）　@扱者コード条件追加
        //扱者コード != null && 扱者コード != ""の場合
        //" and sonar_trader_code = ? "
        //扱者コード == ""の場合
        //" and sonar_trader_code is null "
        if (l_searchCondition.traderCode != null && !BLANK.equals(l_searchCondition.traderCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and sonar_trader_code = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" sonar_trader_code = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.traderCode))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and sonar_trader_code is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" sonar_trader_code is null ");
            }
        }
        //９）　@口座開設区分条件追加
        //口座開設区分 != null && 口座開設区分 != ""の場合
        //" and acc_open_kind_div = ? "
        //口座開設区分 == ""の場合
        //" and acc_open_kind_div is null "
        if (l_searchCondition.accountOpenDiv != null && !BLANK.equals(l_searchCondition.accountOpenDiv))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and acc_open_kind_div = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" acc_open_kind_div = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.accountOpenDiv))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and acc_open_kind_div is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" acc_open_kind_div is null ");
            }
        }

        //１０）　@対象日条件追加
        //対象日 != nullの場合、対象日条件を追加する。
        // " AND (((appli_start_date <= ? OR appli_start_date IS NULL) AND
        //(appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL)
        //OR (acc_open_date_from <= ?
        //AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ?
        //AND acc_open_pass_month IS NOT NULL)
        //OR (appli_start_date IS NULL AND appli_end_date IS NULL
        //AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL))"
        if (l_searchCondition.targetDate != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" AND (((appli_start_date <= ? OR appli_start_date IS NULL) AND ");
                l_sbSearchCondition.append("(appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL) ");
                l_sbSearchCondition.append("OR (acc_open_date_from <= ? ");
                l_sbSearchCondition.append("AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ? ");
                l_sbSearchCondition.append("AND acc_open_pass_month IS NOT NULL) ");
                l_sbSearchCondition.append("OR (appli_start_date IS NULL AND appli_end_date IS NULL ");
                l_sbSearchCondition.append("AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL)) ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" (((appli_start_date <= ? OR appli_start_date IS NULL) AND ");
                l_sbSearchCondition.append("(appli_end_date >= ? OR appli_end_date IS NULL) AND acc_open_pass_month IS NULL) ");
                l_sbSearchCondition.append("OR (acc_open_date_from <= ? ");
                l_sbSearchCondition.append("AND (ADD_MONTHS(acc_open_date_to, acc_open_pass_month) + acc_open_pass_date) >= ? ");
                l_sbSearchCondition.append("AND acc_open_pass_month IS NOT NULL) ");
                l_sbSearchCondition.append("OR (appli_start_date IS NULL AND appli_end_date IS NULL ");
                l_sbSearchCondition.append("AND acc_open_date_from IS NULL AND acc_open_date_to IS NULL)) ");
            }
        }

        //１１）　@対象期間From条件追加
        //１１-１） 対象期間From != null && 対象期間From != ""の場合
        //" and appli_start_date = ? "
        //１１-２） 対象期間From == ""の場合
        //" and appli_start_date is null "
        if (l_searchCondition.targetPeriodFrom != null && !BLANK.equals(l_searchCondition.targetPeriodFrom))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and appli_start_date = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" appli_start_date = ? ");
            }
        }
        else if (BLANK.equals(l_searchCondition.targetPeriodFrom))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and appli_start_date is null ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" appli_start_date is nul ");
            }
        }

        //１２）　@口座開設日From条件追加
        //口座開設日From != nullの場合、口座開設日From条件を追加する。
        //" and acc_open_date_from = ? "

        if (l_searchCondition.accountOpenDateFrom != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and acc_open_date_from = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" acc_open_date_from = ? ");
            }
        }

        //１３）　@削除フラグ条件追加
        //削除フラグ != nullの場合、削除フラグ条件を追加する。
        //" and delete_flag = ? "
        if (l_searchCondition.deleteFlag != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and delete_flag = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" delete_flag = ? ");
            }
        }

        //１４）　@キャンペーン名称条件追加
        //キャンペーン名称 != nullの場合、キャンペーン名称条件を追加する。
        //" and comm_campaign_name like ? "
        if (l_searchCondition.campaignName != null)
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and comm_campaign_name like ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" comm_campaign_name like ? ");
            }
        }

        //１５）　@登録タイプ条件追加
        //登録タイプ != null or 登録タイプ長 > 0 の場合、登録タイプ条件を追加する。
        //１５-１） 登録タイプが長さ１の場合
        //"and regist_type  = ? "
        if ((l_searchCondition.registerTypes != null) && (l_searchCondition.registerTypes.length == 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and regist_type  = ? ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" regist_type  = ? ");
            }
        }
        //１５-２） 登録タイプ長さ > 1 の場合
        //"and regist_type in ( ?,?,?,…)"
        //※登録タイプ長さ回数"?"を付加する。
        else if ((l_searchCondition.registerTypes != null) && (l_searchCondition.registerTypes.length > 1))
        {
            if (l_sbSearchCondition.length() > 0)
            {
                l_sbSearchCondition.append(" and regist_type in ( ");
            }
            else if (l_sbSearchCondition.length() == 0)
            {
                l_sbSearchCondition.append(" regist_type in ( ");
            }
            for (int i = 0; i < l_searchCondition.registerTypes.length; i++)
            {
                l_sbSearchCondition.append("?");
                if (i != l_searchCondition.registerTypes.length - 1)
                {
                    l_sbSearchCondition.append(", ");
                }
            }
            l_sbSearchCondition.append(") ");
        }

        //１６）検索条件文字列インスタンス.toString() を返却する
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCondition.toString();
    }

    /**
     * (create手数料割引キャンペーン条件情報)<BR>
     * 手数料割引キャンペーン条件マスタの手数料割引キャンペーン条件IDと<BR>
     * 手数料割引キャンペーン商品マスタのキャンペーン手数料条件IDを元に、<BR>
     * （引数）手数料割引キャンペーン条件マスタListと（引数）手数料割引キャンペーン商品マスタListの<BR>
     * マージを行う。<BR>
     * <BR>
     * １） 返却値用の手数料割引キャンペーン条件情報配列を生成する。（長さ：キャンペーン条件マスタ行List.size()）<BR>
     * <BR>
     * ２） キャンペーン条件マスタ行List.size() 回数以下を繰り返す。<BR>
     * <BR>
     *   ２-１） 手数料割引キャンペーン条件IDを取得する。<BR>
     *             キャンペーン条件マスタ行List.get(index).getColumn(手数料割引キャンペーン条件ID)<BR>
     * <BR>
     *   ２-２） キャンペーン商品マスタ行Listより ２-１） で取得したIDを持つ商品コードを取得する。<BR>
     * <BR>
     *   ２-３） ２-２） に於いて取得した商品コード数の長さを持つString配列を生成し、取得した商品コードを要素に設定する。<BR>
     * <BR>
     *   ２-４） 手数料割引キャンペーン条件情報プロパティへ以下設定を行う。<BR>
     * <BR>
     *    手数料割引キャンペーン条件情報[index].手数料割引キャンペーン条件ID<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(手数料割引キャンペーン条件ID)<BR>
     *    手数料割引キャンペーン条件情報[index].キャンペーン名称<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(キャンペーン名称)<BR>
     *    手数料割引キャンペーン条件情報[index].証券会社コード<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(証券会社コード)<BR>
     *    手数料割引キャンペーン条件情報[index].部店コード<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(部店コード)<BR>
     *    手数料割引キャンペーン条件情報[index].顧客コード<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(顧客コード)<BR>
     *    手数料割引キャンペーン条件情報[index].顧客名<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(顧客名称)<BR>
     *    手数料割引キャンペーン条件情報[index].商品コード[]<BR>
     *  = ２-３） で生成したString配列<BR>
     *    手数料割引キャンペーン条件情報[index].対象期間From<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(対象期間From)<BR>
     *    手数料割引キャンペーン条件情報[index].対象期間To<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(対象期間To)<BR>
     *    手数料割引キャンペーン条件情報[index].徴収率<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(顧客徴収率)<BR>
     *    手数料割引キャンペーン条件情報[index].口座開設経過期間（月）<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(口座開設経過期間（月）)<BR>
     *    手数料割引キャンペーン条件情報[index].口座開設経過期間（日）    <BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(口座開設経過期間（日）)<BR>
     *    手数料割引キャンペーン条件情報[index].扱者コード<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(扱者コード)<BR>
     *    手数料割引キャンペーン条件情報[index].口座開設区分<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(口座開設区分)<BR>
     *    手数料割引キャンペーン条件情報[index].口座開設日From<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(口座開設日From)<BR>
     *    手数料割引キャンペーン条件情報[index].口座開設日To<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(口座開設日To)<BR>
     *    手数料割引キャンペーン条件情報[index].登録タイプ<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(登録タイプ)<BR>
     *    手数料割引キャンペーン条件情報[index].削除フラグ<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(削除フラグ)<BR>
     *    手数料割引キャンペーン条件情報[index].処理区分<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(処理区分)<BR>
     *    手数料割引キャンペーン条件情報[index].登録者<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(更新者コード)<BR>
     *    手数料割引キャンペーン条件情報[index].登録日<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(作成日時)<BR>
     *    手数料割引キャンペーン条件情報[index].更新日<BR>
     *  = キャンペーン条件マスタ行List.get(index).getColumn(更新日時)<BR>
     * <BR>
     * ３） ２）で作成した手数料割引キャンペーン条件情報配列を返却する。<BR>
     * <BR>
     * @@param l_lisCampaignConditionMasterList - (キャンペーン条件マスタ行List)<BR>
     * 手数料割引キャンペーン条件マスタ行リスト<BR>
     * @@param l_lisCampaignItemMasterList - (キャンペーン商品マスタ行List)<BR>
     * 手数料割引キャンペーン商品マスタ行リスト<BR>
     * @@return WEB3AccInfoCampaignInfo[]
     * @@roseuid 45B6C0DC02BC
     */
    protected WEB3AccInfoCampaignInfo[] createAccInfoCampaignInfo(
        List l_lisCampaignConditionMasterList,
        List l_lisCampaignItemMasterList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAccInfoCampaignInfo(List, List)";
        log.entering(STR_METHOD_NAME);

        if ((l_lisCampaignConditionMasterList == null) || (l_lisCampaignItemMasterList == null))
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１） 返却値用の手数料割引キャンペーン条件情報配列を生成する。
        //（長さ：キャンペーン条件マスタ行List.size()）
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos
            = new WEB3AccInfoCampaignInfo[l_lisCampaignConditionMasterList.size()];

        Map l_map = new HashMap();

        //キャンペーン商品マスタ行Listより ２-１） で取得したIDを持つ商品コードを取得
        for (int i = 0; i < l_lisCampaignItemMasterList.size(); i++)
        {
            Long l_longCampaignId =
                new Long(((CommCampaignProductMstRow)l_lisCampaignItemMasterList.get(i)).getCampaignId());
            List l_lisCommProductCodes = new ArrayList();

            if (!l_map.containsKey(l_longCampaignId))
            {
                l_lisCommProductCodes.add(((CommCampaignProductMstRow)l_lisCampaignItemMasterList.get(i)).getCommProductCode());
                l_map.put(l_longCampaignId, l_lisCommProductCodes);
            }
            else
            {
                ((List)l_map.get(l_longCampaignId)).add(
                        ((CommCampaignProductMstRow)l_lisCampaignItemMasterList.get(i)).getCommProductCode());
            }
        }

        //２） キャンペーン条件マスタ行List.size() 回数以下を繰り返す。
        for (int i = 0; i < l_lisCampaignConditionMasterList.size(); i++)
        {
            l_accInfoCampaignInfos[i] = new WEB3AccInfoCampaignInfo();

            String[] l_strCommProductCodes = null;

            //２-１） 手数料割引キャンペーン条件IDを取得する。
            //キャンペーン条件マスタ行List.get(index).getColumn(手数料割引キャンペーン条件ID)
            Long l_longCampaignId = new Long(((CommCampaignCondMstRow)
                l_lisCampaignConditionMasterList.get(i)).getCampaignId());

            //２-２） キャンペーン商品マスタ行Listより ２-１） で取得したIDを持つ商品コードを取得する。
            //２-３） ２-２） に於いて取得した商品コード数の長さを持つString配列を生成し、
            //取得した商品コードを要素に設定する。
            if ((!l_map.isEmpty()) && (l_map.containsKey(l_longCampaignId)))
            {
                List l_lisForGetCommProductCodes = (List)l_map.get(l_longCampaignId);
                l_strCommProductCodes =
                    (String[])(l_lisForGetCommProductCodes).toArray(new String[l_lisForGetCommProductCodes.size()]);
            }

            //２-４） 手数料割引キャンペーン条件情報プロパティへ以下設定を行う。
            //手数料割引キャンペーン条件情報[index].手数料割引キャンペーン条件ID
            //= キャンペーン条件マスタ行List.get(index).getColumn(手数料割引キャンペーン条件ID)
            l_accInfoCampaignInfos[i].campaignId =
                Long.toString(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getCampaignId());
            //手数料割引キャンペーン条件情報[index].キャンペーン名称
            //= キャンペーン条件マスタ行List.get(index).getColumn(キャンペーン名称)
            l_accInfoCampaignInfos[i].campaignName =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getCommCampaignName();
            //手数料割引キャンペーン条件情報[index].証券会社コード
            //= キャンペーン条件マスタ行List.get(index).getColumn(証券会社コード)
            l_accInfoCampaignInfos[i].institutionCode =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getInstitutionCode();
            //手数料割引キャンペーン条件情報[index].部店コード
            //= キャンペーン条件マスタ行List.get(index).getColumn(部店コード)
            l_accInfoCampaignInfos[i].branchCode =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getBranchCode();
            //手数料割引キャンペーン条件情報[index].顧客コード
            //= キャンペーン条件マスタ行List.get(index).getColumn(顧客コード)
            String l_strAccountCode = ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccountCode();
            if (l_strAccountCode != null)
            {
                l_accInfoCampaignInfos[i].accountCode =
                    ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccountCode().substring(0, 6);
            }
            //手数料割引キャンペーン条件情報[index].顧客名
            //= キャンペーン条件マスタ行List.get(index).getColumn(顧客名称)
            l_accInfoCampaignInfos[i].accountName =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getFamilyName();
            //手数料割引キャンペーン条件情報[index].商品コード[]
            //= ２-３） で生成したString配列
            l_accInfoCampaignInfos[i].itemCode = l_strCommProductCodes;
            //手数料割引キャンペーン条件情報[index].対象期間From
            //= キャンペーン条件マスタ行List.get(index).getColumn(対象期間From)
            l_accInfoCampaignInfos[i].targetPeriodFrom =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAppliStartDate();
            //手数料割引キャンペーン条件情報[index].対象期間To
            //= キャンペーン条件マスタ行List.get(index).getColumn(対象期間To)
            l_accInfoCampaignInfos[i].targetPeriodTo =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAppliEndDate();
            //手数料割引キャンペーン条件情報[index].徴収率
            //= キャンペーン条件マスタ行List.get(index).getColumn(顧客徴収率)
            l_accInfoCampaignInfos[i].collectRate =
                WEB3StringTypeUtility.formatNumber(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccountChargeRatio());
            //手数料割引キャンペーン条件情報[index].口座開設経過期間（月）
            //= キャンペーン条件マスタ行List.get(index).getColumn(口座開設経過期間（月）)
            l_accInfoCampaignInfos[i].accopenPassPeriodMonth =
                ((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenPassMonth();
            //手数料割引キャンペーン条件情報[index].口座開設経過期間（日）
            //= キャンペーン条件マスタ行List.get(index).getColumn(口座開設経過期間（日）)
            l_accInfoCampaignInfos[i].accopenPassPeriodDay =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenPassDate());
            //手数料割引キャンペーン条件情報[index].扱者コード
            //= キャンペーン条件マスタ行List.get(index).getColumn(扱者コード)
            l_accInfoCampaignInfos[i].traderCode =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getSonarTraderCode());
            //手数料割引キャンペーン条件情報[index].口座開設区分
            //= キャンペーン条件マスタ行List.get(index).getColumn(口座開設区分)
            l_accInfoCampaignInfos[i].accountOpenDiv =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenKindDiv());
            //手数料割引キャンペーン条件情報[index].口座開設日From
            //= キャンペーン条件マスタ行List.get(index).getColumn(口座開設日From)
            l_accInfoCampaignInfos[i].accountOpenDateFrom =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenDateFrom());
            //手数料割引キャンペーン条件情報[index].口座開設日To
            //= キャンペーン条件マスタ行List.get(index).getColumn(口座開設日To)
            l_accInfoCampaignInfos[i].accountOpenDateTo =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getAccOpenDateTo());
            //手数料割引キャンペーン条件情報[index].登録タイプ
            //= キャンペーン条件マスタ行List.get(index).getColumn(登録タイプ)
            l_accInfoCampaignInfos[i].registType =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getRegistType());
            //手数料割引キャンペーン条件情報[index].削除フラグ
            //= キャンペーン条件マスタ行List.get(index).getColumn(削除フラグ)
            l_accInfoCampaignInfos[i].deleteFlag =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getDeleteFlag());
            //手数料割引キャンペーン条件情報[index].処理区分
            //= キャンペーン条件マスタ行List.get(index).getColumn(処理区分)
            l_accInfoCampaignInfos[i].transactionDiv =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getStatus());
            //手数料割引キャンペーン条件情報[index].登録者
            //= キャンペーン条件マスタ行List.get(index).getColumn(更新者コード)
            l_accInfoCampaignInfos[i].registrant =
                (((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getLastUpdater());
            //手数料割引キャンペーン条件情報[index].登録日
            //= キャンペーン条件マスタ行List.get(index).getColumn(作成日時)
            l_accInfoCampaignInfos[i].registDate =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getCreatedTimestamp());
            //手数料割引キャンペーン条件情報[index].更新日
            //= キャンペーン条件マスタ行List.get(index).getColumn(更新日時)
            l_accInfoCampaignInfos[i].updateDate =
                (Date)(((CommCampaignCondMstRow)l_lisCampaignConditionMasterList.get(i)).getLastUpdatedTimestamp());
        }

        //３） ２）で作成した手数料割引キャンペーン条件情報配列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCampaignInfos;
    }

    /**
     * (deleteキャンペーン条件)<BR>
     * 手数料割引キャンペーン条件マスタレコードの削除を行う。<BR>
     * <BR>
     * １） 削除対処レコードの削除フラグチェックを行う。<BR>
     * <BR>
     *      [this.validate削除フラグ()に指定する引数]<BR>
     *        手数料割引キャンペーン条件ID： （引数）手数料割引キャンペーン条件ID<BR>
     *  <BR>
     * ２） Mapオブジェクトを作成し、以下を設定する。<BR>
     * <BR>
     *     コラム名： "delete_flag"      / 値： "1"<BR>
     *     コラム名： "status"           / 値： "0"<BR>
     *     コラム名： "last_updater"     / 値： （引数）更新者コード<BR>
     *     コラム名： "last_updated_timestamp"   / 値： 現在日時<BR>
     * <BR>
     * ３） QueryProcessor#doUpdateQuery()メソッドをコールする。<BR>
     * <BR>
     *       [doUpdateQuery()にセットするパラメータ]  <BR>
     *    　@　@ arg0：　@（引数）手数料割引キャンペーン条件IDを<BR>
     * 基にした手数料割引キャンペーン条件マスタのPrimaryKeyオブジェクト<BR>
     *         arg1： ２）で作成したMapオブジェクト<BR>
     * @@param l_strCampaignConditionId - 手数料割引キャンペーン条件ID<BR>
     * <BR>
     * @@param l_strUpdaterCode - 更新者コード<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 45AD7DEF0382
     */
    public void deleteCampaignCondition(String l_strCampaignConditionId, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteCampaignCondition(String, String)";
        log.entering(STR_METHOD_NAME);

        if (l_strCampaignConditionId == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１） 削除対処レコードの削除フラグチェックを行う。
        //[this.validate削除フラグ()に指定する引数]
        //手数料割引キャンペーン条件ID： （引数）手数料割引キャンペーン条件ID
        this.validateDeleteFlag(l_strCampaignConditionId);

        //２） Mapオブジェクトを作成し、以下を設定する。
        //コラム名： "delete_flag" / 値： "1"
        //コラム名： "status" / 値： "0"
        //コラム名： "last_updater" / 値： （引数）更新者コード
        //コラム名： "last_updated_timestamp" / 値： 現在日時
        Map l_mapSearchCondition = new HashMap();
        l_mapSearchCondition.put("delete_flag", WEB3PvInfoDeleteFlagDef.DELETE_YES);
        l_mapSearchCondition.put("status", WEB3StatusDef.NOT_DEAL);
        l_mapSearchCondition.put("last_updater", l_strUpdaterCode);
        l_mapSearchCondition.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //３） QueryProcessor#doUpdateQuery()メソッドをコールする。
        //[doUpdateQuery()にセットするパラメータ]
        //arg0：　@（引数）手数料割引キャンペーン条件IDを基にした手数料割引キャンペーン条件マスタのPrimaryKeyオブジェクト
        //arg1： ２）で作成したMapオブジェクト
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                new CommCampaignCondMstPK(Long.parseLong(l_strCampaignConditionId)),
                l_mapSearchCondition);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getキャンペーン一覧)<BR>
     * 手数料割引キャンペーンレコードの取得を行う。<BR>
     * <BR>
     * 手数料割引キャンペーン条件マスタ及び手数料割引キャンペーン商品マスタより<BR>
     * 手数料割引キャンペーンレコードを取得する。<BR>
     * <BR>
     * １） キャンペーン検索条件オブジェクトを生成する。<BR>
     * <BR>
     *     [setキャンペーン検索条件_all()に指定する引数]<BR>
     *       手数料割引キャンペーン検索条件： （引数）l_request.手数料割引キャンペーン検索条件<BR>
     *       手数料割引キャンペーン条件ID： null<BR>
     *       証券会社コード： （引数）証券会社コード<BR>
     *       登録タイプ： （引数）登録タイプ<BR>
     * <BR>
     * <BR>
     * ２） 手数料割引キャンペーン条件マスタよりデータを取得する。<BR>
     * <BR>
     *   ２-１） 検索条件文字列を作成する。<BR>
     * <BR>
     *      [this.create検索条件文字列()に指定する引数]<BR>
     *        キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     *   ２-２） 検索データコンテナを作成する。<BR>
     * <BR>
     *      [this.create検索データコンテナ()に指定する引数]<BR>
     *        キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     *   ２-３） ソート条件を作成する。<BR>
     * <BR>
     *      [this.createソート条件()に指定する引数]<BR>
     *        ソートキー[]：（引数）l_request.お客様情報ソートキー[]<BR>
     * <BR>
     *   ２-４） QueryProcessor.doFindAllQuery()メソッドをコールする。  <BR>
     *      [doFindAllQuery()に指定する引数]  <BR>
     *    　@  arg0：　@手数料割引キャンペーン条件マスタRowType <BR>
     *   　@　@ arg1：　@this.create検索条件文字列() の戻り値<BR>
     * 　@  　@ arg2：　@this.createソート条件()の戻り値<BR>
     * 　@　@   arg3：　@null <BR>
     * 　@　@   arg4：　@this.create検索データコンテナ()の戻り値 <BR>
     * 　@ 　@  arg5：　@リクエストデータ.ページ内表示行数<BR>
     * 　@　@   arg6：  リクエストデータ.要求ページ番号<BR>
     *        arg7：  手数料割引キャンペーン商品マスタRowType※ <BR>
     * <BR>
     *           （※手数料割引キャンペーン商品マスタへの副問合せが無い場合はarg7は不要） <BR>
     * <BR>
     * ２-５） ２-４）の戻り値が 0件 の場合、nullを返却する。<BR>
     * <BR>
     * ３） ２）で取得したデータを元に、手数料割引キャンペーン商品マスタのデータ検索を行う。<BR>
     *     取得出来ない場合は、例外をスローする。<BR>
     * <BR>
     *      [this.getキャンペーン商品マスタ行リスト()に指定する引数]<BR>
     *        キャンペーン条件マスタ行List： ２）で取得した手数料割引キャンペーン条件マスタ行List<BR>
     * <BR>
     * <BR>
     * ４） ２） で取得したListに ３）で取得した商品コードをマージする。<BR>
     * <BR>
     *      [this.create手数料割引キャンーペーン条件情報()に指定する引数]<BR>
     *        キャンペーン条件マスタ行List： ２）で取得した手数料割引キャンペーン条件マスタ行List<BR>
     *        キャンペーン商品マスタ行List： ３）で取得した手数料割引キャンペーン商品マスタ行List<BR>
     * <BR>
     * ５） ４）の戻り値を返却する。<BR>
     * @@param l_request - リクエストデータオブジェクト<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strRegistTypes - (登録タイプ)<BR>
     * 登録タイプの配列<BR>
     * <BR>
     * 0： 口座開設条件指定<BR>
     * 1：個別顧客指定<BR>
     * 2：強制個別顧客指定<BR>
     * @@return WEB3AccInfoCampaignInfo[]
     * @@roseuid 45AB508903A1
     */
    public WEB3AccInfoCampaignInfo[] getCampaignList(WEB3GenRequest l_request, String l_strInstitutionCode,
            String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignList(WEB3GenRequest, String, String[])";
        log.entering(STR_METHOD_NAME);

        //１） キャンペーン検索条件オブジェクトを生成する。
        //[setキャンペーン検索条件_all()に指定する引数]
        //手数料割引キャンペーン検索条件： （引数）l_request.手数料割引キャンペーン検索条件
        //手数料割引キャンペーン条件ID： null
        //証券会社コード： （引数）証券会社コード
        //登録タイプ： （引数）登録タイプ
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();
        if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenListRequest)
        {
            l_campaignSearchCondition.setCampaignCondition(
                ((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).campaignSearchItem,
                null,
                l_strInstitutionCode,
                l_strRegistTypes);
        }
        else if (l_request instanceof WEB3AdminAccInfoCampaignIndiviListRequest)
        {
            l_campaignSearchCondition.setCampaignCondition(
                ((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).campaignSearchItem,
                null,
                l_strInstitutionCode,
                l_strRegistTypes);
        }
        else
        {
            log.error("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "l_request = " + l_request
                );
        }

        //２） 手数料割引キャンペーン条件マスタよりデータを取得する。
        //２-１） 検索条件文字列を作成する。
        //[this.create検索条件文字列()に指定する引数]
        //キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト
        String l_strSearchCondition = this.createSearchCondition(l_campaignSearchCondition);

        //２-２） 検索データコンテナを作成する。
        //[this.create検索データコンテナ()に指定する引数]
        //キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト
        Object[] l_strSearchContainers = this.createSearchContainers(l_campaignSearchCondition);

        //２-３） ソート条件を作成する。
        //[this.createソート条件()に指定する引数]
        //ソートキー[]：（引数）l_request.お客様情報ソートキー[]
        String l_strSortCondition = null;
        if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenListRequest)
        {
            l_strSortCondition =
                this.createSortCondition(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).sortKeys);
        }
        else
        {
            l_strSortCondition =
                this.createSortCondition(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).sortKeys);
        }

        //２-４） QueryProcessor.doFindAllQuery()メソッドをコールする。
        //[doFindAllQuery()に指定する引数]
        //arg0：　@手数料割引キャンペーン条件マスタRowType
        //arg1：　@this.create検索条件文字列() の戻り値
        //arg2：　@this.createソート条件()の戻り値
        //arg3：　@null
        //arg4：　@this.create検索データコンテナ()の戻り値
        //arg5：　@リクエストデータ.ページ内表示行数
        //arg6：  リクエストデータ.要求ページ番号
        //arg7：  手数料割引商品マスタRowType
        //（※手数料割引キャンペーン商品マスタへの副問合せが無い場合はarg7は不要）
        List l_lisCampaignConditionMasterList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            RowType[] l_commCampaignProductMstRowTypes = new RowType[1];
            l_commCampaignProductMstRowTypes[0] = CommCampaignProductMstRow.TYPE;
            if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenListRequest)
            {
                int l_intRequestPageIndex =
                    Integer.parseInt(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).pageIndex);

                if (l_intRequestPageIndex < 1)
                {
                    l_intRequestPageIndex = 1;
                }
                l_intRequestPageIndex = l_intRequestPageIndex - 1;

                if (l_campaignSearchCondition.itemCodes != null)
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).pageSize),
                        l_intRequestPageIndex,
                        l_commCampaignProductMstRowTypes);
                }
                else
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignAccOpenListRequest)l_request).pageSize),
                        l_intRequestPageIndex);
                }
            }
            else
            {
                int l_intRequestPageIndex =
                    Integer.parseInt(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).pageIndex);

                if (l_intRequestPageIndex < 1)
                {
                    l_intRequestPageIndex = 1;
                }
                l_intRequestPageIndex = l_intRequestPageIndex - 1;

                if (l_campaignSearchCondition.itemCodes != null)
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).pageSize),
                        l_intRequestPageIndex,
                        l_commCampaignProductMstRowTypes);
                }
                else
                {
                    l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                        CommCampaignCondMstRow.TYPE,
                        l_strSearchCondition,
                        l_strSortCondition,
                        null,
                        l_strSearchContainers,
                        Integer.parseInt(((WEB3AdminAccInfoCampaignIndiviListRequest)l_request).pageSize),
                        l_intRequestPageIndex);
                }
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //２-５） ２-４）の戻り値が 0件 の場合、nullを返却する。
        if ((l_lisCampaignConditionMasterList != null) && (l_lisCampaignConditionMasterList.size() == 0))
        {
            log.debug("手数料割引キャンペーン条件マスタのデータ取得件が 0件、nullを返却する。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //３） ２）で取得したデータを元に、手数料割引キャンペーン商品マスタのデータ検索を行う。
        //取得出来ない場合は、例外をスローする。
        //[this.getキャンペーン商品マスタ行リスト()に指定する引数]
        //キャンペーン条件マスタ行List： ２）で取得した手数料割引キャンペーン条件マスタ行List
        List l_lisCampaignItemMasterList
            = this.getCampaignItemMasterList(l_lisCampaignConditionMasterList);
        if ((l_lisCampaignItemMasterList == null) || (l_lisCampaignItemMasterList.isEmpty()))
        {
            log.debug("手数料割引キャンペーン商品マスタのデータ取得出来ない、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //４） ２） で取得したListに ３）で取得した商品コードをマージする。
        //[this.create手数料割引キャンーペーン条件情報()に指定する引数]
        //キャンペーン条件マスタ行List： ２）で取得した手数料割引キャンペーン条件マスタ行List
        //キャンペーン商品マスタ行List： ３）で取得した手数料割引キャンペーン商品マスタ行List
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos = this.createAccInfoCampaignInfo(
            l_lisCampaignConditionMasterList,
            l_lisCampaignItemMasterList);

        //５） ４）の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCampaignInfos;
    }

    /**
     * (getキャンペーン商品マスタ行リスト)<BR>
     * （引数）手数料割引キャンペーン条件Listに対応する<BR>
     * 手数料割引キャンペーン商品マスタ行を検索し、<BR>
     * 手数料割引キャンペーン商品マスタ行リストを返却する。<BR>
     * <BR>
     * <BR>
     * １） （引数）手数料割引キャンペーン条件List長を元に、<BR>
     *      手数料割引キャンペーン条件マスタ検索用文字列を作成する。<BR>
     * <BR>
     *      "campaign_id in (?, ?, ?, …)"     ※Listの長さ分"?"を付加。<BR>
     * <BR>
     * <BR>
     * ２） （引数）手数料割引キャンペーン条件Listより手数料割引キャンペーン条件IDを抽出し、<BR>
     *      手数料割引キャンペーン条件マスタ検索用データコンテナを作成する。<BR>
     * <BR>
     *   ２-１） ArrayListを生成<BR>
     * <BR>
     *   ２-２） List長の回数以下を繰り返す。<BR>
     * <BR>
     *           [add()に指定する引数]<BR>
     *           （引数）手数料割引キャンペーン条件List.get(0…).<BR>
     * getColumn(手数料割引キャンペーン条件ID) <BR>
     * <BR>
     * <BR>
     * ３） 手数料割引キャンペーン商品マスタよりレコードを取得する。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数] <BR>
     * 　@　@  arg0： 手数料割引キャンペーン商品マスタRow.TYPE <BR>
     * 　@　@  arg1： １）で作成した検索用文字列<BR>
     * 　@　@  arg2： "campaign_id, comm_product_code"<BR>
     *       arg3： null<BR>
     *       arg4： ２）で作成したArrayList#toArray()<BR>
     * <BR>
     * ４） ３）で取得した手数料割引キャンペーン商品マスタ行Listを返却する。<BR>
     * <BR>
     * @@param l_lisCampaignConditionMasterList - (キャンペーン条件マスタ行List)<BR>
     * 手数料割引キャンペーン条件のリスト<BR>
     * @@return List
     * @@roseuid 45B5E7260076
     */
    protected List getCampaignItemMasterList(List l_lisCampaignConditionMasterList)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignItemMasterList(List)";
        log.entering(STR_METHOD_NAME);

        if (l_lisCampaignConditionMasterList == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１） （引数）手数料割引キャンペーン条件List長を元に、
        //手数料割引キャンペーン条件マスタ検索用文字列を作成する。
        //"campaign_id in (?, ?, ?, …)"     ※Listの長さ分"?"を付加。
        StringBuffer l_sbSearchCondition = new StringBuffer();
        if (l_lisCampaignConditionMasterList.size() != 0)
        {
            l_sbSearchCondition.append("campaign_id in (");
            for (int i = 0; i < l_lisCampaignConditionMasterList.size(); i++)
            {
                l_sbSearchCondition.append("?");
                if (i != l_lisCampaignConditionMasterList.size() - 1)
                {
                    l_sbSearchCondition.append(", ");
                }
            }
            l_sbSearchCondition.append(")");
        }

        //２） （引数）手数料割引キャンペーン条件Listより手数料割引キャンペーン条件IDを抽出し、
        //手数料割引キャンペーン条件マスタ検索用データコンテナを作成する。
        //２-１） ArrayListを生成
        List l_lisCampaignId = new ArrayList();
        //２-２） List長の回数以下を繰り返す。
        for (int i = 0; i < l_lisCampaignConditionMasterList.size(); i++)
        {
            //[add()に指定する引数]
            //（引数）手数料割引キャンペーン条件List.get(0…).getColumn(手数料割引キャンペーン条件ID)
            l_lisCampaignId.add(((CommCampaignCondMstRow)
                l_lisCampaignConditionMasterList.get(i)).getColumn("campaign_id"));
        }

        //３） 手数料割引キャンペーン商品マスタよりレコードを取得する。
        //[doFindAllQuery()に指定する引数]
        //arg0： 手数料割引キャンペーン商品マスタRow.TYPE
        //arg1： １）で作成した検索用文字列
        //arg2： "campaign_id, comm_product_code"
        //arg3： null
        //arg4： ２）で作成したArrayList#toArray()
        List l_lisCampaignItemMasterList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCampaignItemMasterList = l_queryProcessor.doFindAllQuery(
                CommCampaignProductMstRow.TYPE,
                l_sbSearchCondition.toString(),
                "campaign_id, comm_product_code",
                null,
                l_lisCampaignId.toArray());
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //４） ３）で取得した手数料割引キャンペーン商品マスタ行Listを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisCampaignItemMasterList;
    }

    /**
     * (getキャンペーン条件)<BR>
     * 手数料割引キャンペーン条件IDをキーとして<BR>
     * 手数料割引キャンペーン条件マスタ及び手数料割引キャンペーン商品名マスタよりデータを取得する。<BR>
     * データが取得出来ない場合は例外をスローする。<BR>
     * <BR>
     * １） 検索対処レコードの削除フラグチェックを行う。<BR>
     * <BR>
     *      [this.validate削除フラグ()に指定する引数]<BR>
     *        手数料割引キャンペーン条件ID： （引数）変更後情報.手数料割引キャンペーン条件ID<BR>
     * <BR>
     * ２） キャンペーン検索条件オブジェクトを生成する。<BR>
     * <BR>
     *     [setキャンペーン検索条件_all()に指定する引数]<BR>
     *       手数料割引キャンペーン検索条件： null<BR>
     *       手数料割引キャンペーン条件ID： （引数）手数料割引キャンペーン条件ID<BR>
     *       証券会社コード： null<BR>
     *       登録タイプ： null <BR>
     * <BR>
     * ３） 手数料割引キャンペーン条件マスタよりデータを取得する。<BR>
     *      取得出来ない場合は、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01037<BR>
     * <BR>
     *   ３-１） 検索条件文字列を作成する。<BR>
     * <BR>
     *      [this.create検索条件文字列()に指定する引数]<BR>
     *        キャンペーン検索項目：２） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     *   ３-２） 検索データコンテナを作成する。<BR>
     * <BR>
     *      [this.create検索データコンテナ()に指定する引数]<BR>
     *        キャンペーン検索項目：２） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     *   ３-３） QueryProcessor#doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     *      [doFindAllQuery()に指定する引数]  <BR>
     *    　@  arg0： 手数料割引キャンペーン条件マスタRowType<BR>
     *   　@　@ arg1：　@this.create検索条件文字列() の戻り値<BR>
     * 　@  　@ arg2：　@this.create検索データコンテナ()の戻り値 <BR>
     * <BR>
     * ４） 手数料割引キャンペーン商品マスタより商品コードを取得する。<BR>
     *      取得出来ない場合は例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01037<BR>
     * <BR>
     *      [this.getキャンペーン商品マスタ行リスト()に指定する引数]<BR>
     *        キャンペーン条件マスタ行List： ３）で取得した手数料割引キャンペーン条件マスタ行List<BR>
     * <BR>
     * ５）   ３） で取得したListに ４）で取得した商品コードをマージする。<BR>
     * <BR>
     *      [this.create手数料割引キャンーペーン条件情報()に指定する引数]<BR>
     *        キャンペーン条件マスタ行List： ３）で取得した手数料割引キャンペーン条件マスタ行List<BR>
     *        キャンペーン商品マスタ行List： ４）で取得した手数料割引キャンペーン商品マスタ行List<BR>
     * <BR>
     * ６） ５） の戻り値を返却する。 <BR>
     * @@param l_strCampaignId - 手数料割引キャンペーン条件ID<BR>
     * <BR>
     * @@return WEB3AccInfoCampaignInfo
     * @@roseuid 45AC33210298
     */
    public WEB3AccInfoCampaignInfo getCampaignCondition(String l_strCampaignId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCampaignItemMasterList(List)";
        log.entering(STR_METHOD_NAME);

        //１） 検索対処レコードの削除フラグチェックを行う。
        //[this.validate削除フラグ()に指定する引数]
        //手数料割引キャンペーン条件ID： （引数）変更後情報.手数料割引キャンペーン条件ID
        this.validateDeleteFlag(l_strCampaignId);

        //２） キャンペーン検索条件オブジェクトを生成する。
        //[setキャンペーン検索条件_all()に指定する引数]
        //手数料割引キャンペーン検索条件： null
        //手数料割引キャンペーン条件ID： （引数）手数料割引キャンペーン条件ID
        //証券会社コード： null
        //登録タイプ： null
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();
        l_campaignSearchCondition.setCampaignCondition(
            null,
            l_strCampaignId,
            null,
            null);

        //３） 手数料割引キャンペーン条件マスタよりデータを取得する。
        //取得出来ない場合は、例外をスローする。
        //３-１） 検索条件文字列を作成する。
        //[this.create検索条件文字列()に指定する引数]
        //キャンペーン検索項目：２） で作成したキャンペーン検索条件オブジェクト
        String l_strSearchCondition = this.createSearchCondition(l_campaignSearchCondition);

        //３-２） 検索データコンテナを作成する。
        //[this.create検索データコンテナ()に指定する引数]
        //キャンペーン検索項目：２） で作成したキャンペーン検索条件オブジェクト
        Object[] l_strSearchContainers = this.createSearchContainers(l_campaignSearchCondition);

        //３-３） QueryProcessor#doFindAllQuery()メソッドをコールする。
        //[doFindAllQuery()に指定する引数]
        //arg0： 手数料割引キャンペーン条件マスタRowType
        //arg1：　@this.create検索条件文字列() の戻り値
        //arg2：　@this.create検索データコンテナ()の戻り値
        List l_lisCampaignConditionMasterList = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisCampaignConditionMasterList = l_queryProcessor.doFindAllQuery(
                CommCampaignCondMstRow.TYPE,
                l_strSearchCondition,
                l_strSearchContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        if ((l_lisCampaignConditionMasterList == null) || (l_lisCampaignConditionMasterList.size() == 0))
        {
            log.debug("手数料割引キャンペーン条件マスタのデータ取得出来ない、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //４） 手数料割引キャンペーン商品マスタより商品コードを取得する。
        //取得出来ない場合は例外をスローする。
        //[this.getキャンペーン商品マスタ行リスト()に指定する引数]
        //キャンペーン条件マスタ行List： ３）で取得した手数料割引キャンペーン条件マスタ行List
        List l_lisCampaignItemMasterList = this.getCampaignItemMasterList(l_lisCampaignConditionMasterList);
        if ((l_lisCampaignItemMasterList == null) || (l_lisCampaignItemMasterList.isEmpty()))
        {
            log.debug("手数料割引キャンペーン商品マスタのデータ取得出来ない、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }

        //５）   ３） で取得したListに ４）で取得した商品コードをマージする。
        //[this.create手数料割引キャンーペーン条件情報()に指定する引数]
        //キャンペーン条件マスタ行List： ３）で取得した手数料割引キャンペーン条件マスタ行List
        //キャンペーン商品マスタ行List： ４）で取得した手数料割引キャンペーン商品マスタ行List
        WEB3AccInfoCampaignInfo[] l_accInfoCampaignInfos
            = this.createAccInfoCampaignInfo(
            l_lisCampaignConditionMasterList,
            l_lisCampaignItemMasterList);

        //６） ５） の戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_accInfoCampaignInfos[0];
    }

    /**
     * (get重複キャンペーン条件)<BR>
     * 手数料割引キャンペーンレコードの取得を行う。<BR>
     * 重複キャンペーン検索用メソッド<BR>
     * <BR>
     * 手数料割引キャンペーン条件マスタ及び手数料割引キャンペーン商品マスタより<BR>
     * 手数料割引キャンペーンレコードを取得する。<BR>
     * レコードが取得出来ない場合はnull値を返却する。<BR>
     * <BR>
     * <BR>
     * １） 手数料割引キャンペーン条件マスタよりデータを取得する。<BR>
     * <BR>
     *   １-１） 検索条件文字列を作成する。<BR>
     * <BR>
     *      [this.create検索条件文字列()に指定する引数]<BR>
     *        キャンペーン検索項目：（引数）重複検索条件<BR>
     * <BR>
     *   １-２） 検索データコンテナを作成する。<BR>
     * <BR>
     *      [this.create検索データコンテナ()に指定する引数]<BR>
     *        キャンペーン検索項目：重複検索条件<BR>
     * <BR>
     *   １-３） QueryProcessor.doFindAllQuery()メソッドをコールする。  <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数] <BR>
     *    　@ arg0：　@手数料割引キャンペーン条件マスタRowType <BR>
     *       arg1：　@１-１）で作成した検索文字列 + " and delete_flag != '1' or delete_flag is null"<BR>
     * 　@　@  arg2：　@"campaign_id" <BR>
     * 　@    arg3：　@null<BR>
     * 　@　@  arg4：　@１-２）で作成した検索データコンテナ<BR>
     *       arg5：  手数料割引商品マスタRowType<BR>
     * <BR>
     * ２） １） の戻り値Listの長さ > 0 の場合、以下処理を行う。<BR>
     * <BR>
     *   ２-１） 手数料割引キャンペーン商品マスタより商品マスタ行を取得する。<BR>
     *           取得出来ない場合は例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01037<BR>
     * <BR>
     *           [this.getキャンペーン商品マスタ行リスト()に指定する引数]<BR>
     *             キャンペーン条件マスタ行List： １）で取得した手数料割引キャンペーン条件マスタ行List<BR>
     * <BR>
     *   ２-２） １） で取得したListに ２-１）で取得した商品コードをマージする。<BR>
     * <BR>
     *      [this.create手数料割引キャンーペーン条件情報()に指定する引数]<BR>
     *        キャンペーン条件マスタ行List： １）で取得した手数料割引キャンペーン条件マスタ行List<BR>
     *        キャンペーン商品マスタ行List： ２-１）で取得した手数料割引キャンペーン商品マスタ行List<BR>
     * <BR>
     *   ２-３） ２-２）の戻り値を返却する。 <BR>
     * <BR>
     * ３） １）の戻り値Listの長さ = 0 の場合、nullを返却する。<BR>
     * @@param l_sameSearchCondition - 重複検索条件<BR>
     * @@return WEB3AccInfoCampaignInfo[]
     * @@roseuid 45B07FF101D8
     */
    public WEB3AccInfoCampaignInfo[] getSameCampaignCondition(
            WEB3AdminAccInfoCampaignSearchCondition l_sameSearchCondition) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getSameCampaignCondition(WEB3AdminAccInfoCampaignSearchCondition)";
        log.entering(STR_METHOD_NAME);

        //１） 手数料割引キャンペーン条件マスタよりデータを取得する。
        //１-１） 検索条件文字列を作成する。
        //[this.create検索条件文字列()に指定する引数]
        //キャンペーン検索項目：（引数）重複検索条件
        String l_strSearchCondition
            = this.createSearchCondition(l_sameSearchCondition);

        //１-２） 検索データコンテナを作成する。
        //[this.create検索データコンテナ()に指定する引数]
        //キャンペーン検索項目：重複検索条件
        Object[] l_strSearchContainers
            = this.createSearchContainers(l_sameSearchCondition);

        //１-３） QueryProcessor.doFindAllQuery()メソッドをコールする。
        //[doFindAllQuery()に指定する引数]
        //arg0：　@手数料割引キャンペーン条件マスタRowType
        //arg1：　@１-１）で作成した検索文字列 + " and delete_flag != '1' or delete_flag is null"
        //arg2：　@"campaign_id"
        //arg3：　@null
        //arg4：　@１-２）で作成した検索データコンテナ
        //arg5：  手数料割引商品マスタRowType
        List l_lisCampaignConditionMasters = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            RowType[] l_commCampaignProductMstRowTypes = new RowType[1];
            l_commCampaignProductMstRowTypes[0] = CommCampaignProductMstRow.TYPE;
            l_lisCampaignConditionMasters = l_queryProcessor.doFindAllQuery(
                CommCampaignCondMstRow.TYPE,
                l_strSearchCondition + " and (delete_flag != '1' or delete_flag is null)",
                "campaign_id",
                null,
                l_strSearchContainers,
                l_commCampaignProductMstRowTypes);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //２） １） の戻り値Listの長さ > 0 の場合、以下処理を行う。
        //２-１） 手数料割引キャンペーン商品マスタより商品マスタ行を取得する。
        //取得出来ない場合は例外をスローする。
        //[this.getキャンペーン商品マスタ行リスト()に指定する引数]
        //キャンペーン条件マスタ行List： １）で取得した手数料割引キャンペーン条件マスタ行List
        List l_lisCampaignItemMasters = new ArrayList();
        if ((l_lisCampaignConditionMasters != null) && (l_lisCampaignConditionMasters.size() > 0))
        {
            l_lisCampaignItemMasters = this.getCampaignItemMasterList(l_lisCampaignConditionMasters);
            if ((l_lisCampaignItemMasters == null) || (l_lisCampaignItemMasters.size() == 0))
            {
                log.debug("手数料割引キャンペーン商品マスタのデータ取得出来ない、例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "条件に該当するデータが存在しない。");
            }

            //２-２） １） で取得したListに ２-１）で取得した商品コードをマージする。
            //[this.create手数料割引キャンーペーン条件情報()に指定する引数]
            //キャンペーン条件マスタ行List： １）で取得した手数料割引キャンペーン条件マスタ行List
            //キャンペーン商品マスタ行List： ２-１）で取得した手数料割引キャンペーン商品マスタ行List
            WEB3AccInfoCampaignInfo[] l_campaignInfos =
                this.createAccInfoCampaignInfo(l_lisCampaignConditionMasters, l_lisCampaignItemMasters);

            //２-３） ２-２）の戻り値を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_campaignInfos;
        }

        //３） １）の戻り値Listの長さ = 0 の場合、nullを返却する。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (get総レコード件数)<BR>
     * 総レコード件数値を返す。<BR>
     * <BR>
     * <BR>
     * １） キャンペーン検索条件オブジェクトを生成する。<BR>
     * <BR>
     *     [setキャンペーン検索条件_all()に指定する引数]<BR>
     *       手数料割引キャンペーン検索条件： （引数）キャンペーン検索項目<BR>
     *       手数料割引キャンペーン条件ID： null<BR>
     *       証券会社コード： （引数）証券会社コード<BR>
     *       登録タイプ： （引数）登録タイプ <BR>
     * <BR>
     * ２） 検索条件文字列を作成する。<BR>
     *     [create検索条件文字列()に指定する引数]<BR>
     *     キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     * ３） 検索データコンテナを作成する。<BR>
     *     [create検索データコンテナ()に指定する引数]<BR>
     *     キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     * ３） 総レコード件数を取得する。<BR>
     * <BR>
     *   ３-１） 検索条件に「商品コード」が含まれていない場合<BR>
     * <BR>
     *     ３-１-１） QueryProcessor#doGetCountQuery()メソッドをコールする。<BR>
     * <BR>
     *                  [doGetCountQuery()にセットするパラメータ]  <BR>
     *    　@  　@           arg0：　@手数料割引キャンペーン条件マスタRowType <BR>
     *   　@　@              arg1：　@this.create検索条件文字列() の戻り値<BR>
     * 　@  　@              arg2：　@this.create検索データコンテナ()の戻り値 <BR>
     * <BR>
     *     ３-１-２） QueryProcessor#doGetCountQuery()の戻り値を返却する。<BR>
     * <BR>
     * <BR>
     *   ３-２） 検索条件に「商品コード」が含まれている場合<BR>
     * <BR>
     *     ３-２-１） QueryProcessor#oFindAllQuery()メソッドをコールする。  <BR>
     * <BR>
     *                  [doFindAllQuery()にセットするパラメータ]  <BR>
     *    　@　@            arg0：　@手数料割引キャンペーン条件マスタRowType <BR>
     *   　@　@             arg1：　@this.create検索条件文字列() の戻り値<BR>
     * 　@  　@             arg2：　@null<BR>
     * 　@　@               arg3：　@null <BR>
     * 　@　@               arg4：　@this.create検索データコンテナ()の戻り値 <BR>
     *                    arg5：  手数料割引商品マスタRowType<BR>
     * <BR>
     *     ３-２-２） QueryProcessor#doFindAllQuery()メソッドの戻り値の長さを返却する。<BR>
     * @@param l_campaignSearchCondition - (キャンペーン検索項目)<BR>
     * 手数料割引キャンペーン検索条件オブジェクト<BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strRegistTypes - 登録タイプ<BR>
     * @@return int
     * @@roseuid 45AC95920184
     */
    public int getAllRecordCount(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strInstitutionCode, String[] l_strRegistTypes) throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "getAllRecordCount(WEB3AccInfoCampaignSearchCondition, String, String[])";
        log.entering(STR_METHOD_NAME);

        //１） キャンペーン検索条件オブジェクトを生成する。
        //[setキャンペーン検索条件_all()に指定する引数]
        //手数料割引キャンペーン検索条件： （引数）キャンペーン検索項目
        //手数料割引キャンペーン条件ID： null
        //証券会社コード： （引数）証券会社コード
        //登録タイプ： （引数）登録タイプ
        WEB3AdminAccInfoCampaignSearchCondition l_adminCampaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();
        l_adminCampaignSearchCondition.setCampaignCondition(
            l_campaignSearchCondition,
            null,
            l_strInstitutionCode,
            l_strRegistTypes);

        //２） 検索条件文字列を作成する。
        //[create検索条件文字列()に指定する引数]
        //キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト
        String l_strSearchCondition
            = this.createSearchCondition(l_adminCampaignSearchCondition);

        //３） 検索データコンテナを作成する。
        //[create検索データコンテナ()に指定する引数]
        //キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト
        Object[] l_strSearchContainers
            = this.createSearchContainers(l_adminCampaignSearchCondition);

        //３） 総レコード件数を取得する。
        //３-１） 検索条件に「商品コード」が含まれていない場合
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            if ((l_adminCampaignSearchCondition.itemCodes == null)
                || (l_adminCampaignSearchCondition.itemCodes.length <= 0))
            {
                //３-１-１） QueryProcessor#doGetCountQuery()メソッドをコールする。
                //[doGetCountQuery()にセットするパラメータ]
                //arg0：　@手数料割引キャンペーン条件マスタRowType
                //arg1：　@this.create検索条件文字列() の戻り値
                //arg2：　@this.create検索データコンテナ()の戻り値
                int l_intResultCnt;
                l_intResultCnt = l_queryProcessor.doGetCountQuery(
                    CommCampaignCondMstRow.TYPE,
                    l_strSearchCondition,
                    l_strSearchContainers);
                //３-１-２） QueryProcessor#doGetCountQuery()の戻り値を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_intResultCnt;
            }

            //３-２） 検索条件に「商品コード」が含まれている場合
            else
            {
                //３-２-１） QueryProcessor#oFindAllQuery()メソッドをコールする。
                //[doFindAllQuery()にセットするパラメータ]
                //arg0：　@手数料割引キャンペーン条件マスタRowType
                //arg1：　@this.create検索条件文字列() の戻り値
                //arg2：　@null
                //arg3：　@null
                //arg4：　@this.create検索データコンテナ()の戻り値
                //arg5：  手数料割引商品マスタRowType
                RowType[] l_commCampaignProductMstRowTypes = new RowType[1];
                l_commCampaignProductMstRowTypes[0] = CommCampaignProductMstRow.TYPE;
                List l_lisCampaignConditionMasters = l_queryProcessor.doFindAllQuery(
                    CommCampaignCondMstRow.TYPE,
                    l_strSearchCondition,
                    null,
                    null,
                    l_strSearchContainers,
                    l_commCampaignProductMstRowTypes);

                //３-２-２） QueryProcessor#doFindAllQuery()メソッドの戻り値の長さを返却する。
                log.exiting(STR_METHOD_NAME);
                return l_lisCampaignConditionMasters.size();
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
    }

    /**
     * (insertキャンペーン条件)<BR>
     * 手数料割引キャンペーン条件マスタレコード及び<BR>
     * 手数料割引キャンペーン商品マスタレコードの登録を行う。<BR>
     * <BR>
     * <BR>
     * １） 手数料割引キャンペーン条件マスタの登録を行う。<BR>
     * <BR>
     * １-１） （引数）登録情報.登録タイプ != 0 の場合
     *   １-１-１） 拡張アカウントマネージャを利用して顧客行を取得する。<BR>
     *   [get顧客()にセットするパラメータ] <BR>
     *    　@arg0：　@（引数）登録情報.証券会社コード<BR>
     *  　@  arg1：　@（引数）登録情報.部店コード<BR>
     *  　@  arg2：　@（引数）登録情報.顧客コード<BR>
     *    １-１-２） １－１-１）の戻り値から7桁の顧客コードを取得する。<BR>
     *  １-２） 手数料割引キャンペーン条件マスタ行オブジェクトを作成し、以下を設定する。<BR>
     * <BR>
     *     手数料割引キャンペーン条件マスタ行.手数料割引キャンペーン条件ID<BR>
     *  = 手数料割引キャンペーン条件マスタDao.newPkValue()の戻り値<BR>
     *     手数料割引キャンペーン条件マスタ行.手数料割引キャンペーン名称<BR>
     *  = （引数）登録情報.手数料割引キャンペーン名称<BR>
     *     手数料割引キャンペーン条件マスタ行.証券会社コード<BR>
     *  = （引数）登録情報.証券会社コード<BR>
     *     手数料割引キャンペーン条件マスタ行.部店コード<BR>
     *  = （引数）登録情報.部店コード<BR>
     *     手数料割引キャンペーン条件マスタ行.顧客コード<BR>
     *  = １-２）で取得した顧客コード<BR>
     *     手数料割引キャンペーン条件マスタ行.顧客名称<BR>
     *  = （引数）登録情報.顧客名称<BR>
     *     手数料割引キャンペーン条件マスタ行.口座開設経過期間（月）<BR>
     *  = （引数）登録情報.口座開設経過期間（月）<BR>
     *     手数料割引キャンペーン条件マスタ行.口座開設経過期間（日）<BR>
     *  = （引数）登録情報.口座開設経過期間（日）<BR>
     *     手数料割引キャンペーン条件マスタ行.対象期間From<BR>
     *  = （引数）登録情報.対象期間From<BR>
     *     手数料割引キャンペーン条件マスタ行.対象期間To<BR>
     *  = （引数）登録情報.対象期間To<BR>
     *     手数料割引キャンペーン条件マスタ行.顧客徴収率<BR>
     *  = （引数）登録情報.徴収率<BR>
     *     手数料割引キャンペーン条件マスタ行.扱者コード<BR>
     *  = （引数）登録情報.扱者コード<BR>
     *     手数料割引キャンペーン条件マスタ行.口座開設区分<BR>
     *  = （引数）登録情報.口座開設区分<BR>
     *     手数料割引キャンペーン条件マスタ行.口座開設日From<BR>
     *  = （引数）登録情報.口座開設日From<BR>
     *     手数料割引キャンペーン条件マスタ行.口座開設日To<BR>
     *  = （引数）登録情報.口座開設日To<BR>
     *     手数料割引キャンペーン条件マスタ行.登録タイプ<BR>
     *  = （引数）登録情報.登録タイプ<BR>
     *     手数料割引キャンペーン条件マスタ行.処理区分<BR>
     *  = "0"<BR>
     *     手数料割引キャンペーン条件マスタ行.更新者コード<BR>
     *  = （引数）更新者コード<BR>
     *     手数料割引キャンペーン条件マスタ行.作成日時<BR>
     *  = 現在日時<BR>
     *     手数料割引キャンペーン条件マスタ行.更新日時<BR>
     *  = 現在日時<BR>
     * <BR>
     *       （※個別顧客指定変更、口座開設条件指定変更、それぞれの登録内容についてはDB更新仕様を参照願います。）<BR>
     * <BR>
     * <BR>
     *   １-３） QueryProcessor#doInsertQuery()メソッドをコールする。<BR>
     * <BR>
     *       [doInsertQuery()にセットするパラメータ]  <BR>
     *    　@　@ arg0：　@１-１）で作成した行オブジェクト<BR>
     * <BR>
     *   １-４） QueryProcessor#doInsertQuery()の戻り値を取得する。<BR>
     * <BR>
     * <BR>
     * ２） 手数料割引キャンペーン商品マスタの登録を行う。<BR>
     * <BR>
     *   ２-１） （引数）登録情報.商品コードの配列長回数以下を繰り返す。<BR>
     * <BR>
     *     ２-１-１） 手数料割引キャンペーン商品マスタ行オブジェクトを作成し設定を行う。 <BR>
     * <BR>
     *      手数料割引キャンペーン商品マスタ行.手数料割引キャンペーン条件ID<BR>
     *  = （１-３）の戻り値）手数料割引キャンペーン<BR>
     *  条件マスタ主キーオブジェクト.手数料キャンペーン条件ID <BR>
     *      手数料割引キャンペーン商品マスタ行.商品コード<BR>
     *  = （引数）登録情報.商品コード[0…]<BR>
     *      手数料割引キャンペーン商品マスタ行.更新者コード<BR>
     *  = （引数）更新者コード<BR>
     *      手数料割引キャンペーン商品マスタ行.作成日時<BR>
     *  = 現在日時<BR>
     *      手数料割引キャンペーン商品マスタ行.更新日時<BR>
     *  = 現在日時<BR>
     * <BR>
     *     ２-１-２） QueryProcessor#doInsertQuery()メソッドをコールする。 <BR>
     * <BR>
     *       [doInsertQuery()にセットするパラメータ]  <BR>
     *    　@　@ arg0：　@２-１-１）で作成した行オブジェクト<BR>
     * @@param l_registInfo - (登録情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * <BR>
     * @@param l_strUpdaterCode - 更新者コード<BR>
     * @@roseuid 45AF08C700A2
     */
    public void insertCampaignCondition(WEB3AccInfoCampaignInfo l_registInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME
            = "insertCampaignCondition(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_registInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１） 手数料割引キャンペーン条件マスタの登録を行う。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //１-１） （引数）登録情報.登録タイプ != 0 の場合
        //  １-１-１） 拡張アカウントマネージャを利用して顧客行を取得する。<BR>
        //    [get顧客()にセットするパラメータ]
        //      arg0：　@（引数）登録情報.証券会社コード
        //      arg1：　@（引数）登録情報.部店コード
        //      arg2：　@（引数）登録情報.顧客コード

        String l_strAccountCode = null;
        if (!WEB3AccInfoRegistTypeDef.ACCOPEN_CONDITION.equals(l_registInfo.registType))
        {
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            MainAccount l_mainAccount = null;
            if (l_registInfo.accountCode != null)
            {
                l_mainAccount =
                    l_accMgr.getMainAccount(
                        l_registInfo.institutionCode,
                        l_registInfo.branchCode,
                        l_registInfo.accountCode.substring(0, 6));
            }
        //１-１-２） １－１-１）の戻り値から7桁の顧客コードを取得する。
            l_strAccountCode = l_mainAccount.getAccountCode();
        }

        //１-２） 手数料割引キャンペーン条件マスタ行オブジェクトを作成し、以下を設定する。
        CommCampaignCondMstParams l_commCampaignCondMstParams
            = new CommCampaignCondMstParams();
        //手数料割引キャンペーン条件マスタ行.手数料割引キャンペーン条件ID
        //= 手数料割引キャンペーン条件マスタDao.newPkValue()の戻り値
        try
        {
            l_commCampaignCondMstParams.setCampaignId(CommCampaignCondMstDao.newPkValue());
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        //手数料割引キャンペーン条件マスタ行.手数料割引キャンペーン名称
        //= （引数）登録情報.手数料割引キャンペーン名称
        l_commCampaignCondMstParams.setCommCampaignName(l_registInfo.campaignName);
        //手数料割引キャンペーン条件マスタ行.証券会社コード = （引数）登録情報.証券会社コード
        l_commCampaignCondMstParams.setInstitutionCode(l_registInfo.institutionCode);
        //手数料割引キャンペーン条件マスタ行.部店コード = （引数）登録情報.部店コード
        l_commCampaignCondMstParams.setBranchCode(l_registInfo.branchCode);
        //手数料割引キャンペーン条件マスタ行.顧客コード = １-１-２）で取得した顧客コード
        l_commCampaignCondMstParams.setAccountCode(l_strAccountCode);
        //手数料割引キャンペーン条件マスタ行.顧客名称 = （引数）登録情報.顧客名称
        l_commCampaignCondMstParams.setFamilyName(l_registInfo.accountName);
        //手数料割引キャンペーン条件マスタ行.口座開設経過期間（月） = （引数）登録情報.口座開設経過期間（月）
        l_commCampaignCondMstParams.setAccOpenPassMonth(l_registInfo.accopenPassPeriodMonth);
        //手数料割引キャンペーン条件マスタ行.口座開設経過期間（日） = （引数）登録情報.口座開設経過期間（日）
        l_commCampaignCondMstParams.setAccOpenPassDate(l_registInfo.accopenPassPeriodDay);
        //手数料割引キャンペーン条件マスタ行.対象期間From = （引数）登録情報.対象期間From
        l_commCampaignCondMstParams.setAppliStartDate(l_registInfo.targetPeriodFrom);
        //手数料割引キャンペーン条件マスタ行.対象期間To = （引数）登録情報.対象期間To
        l_commCampaignCondMstParams.setAppliEndDate(l_registInfo.targetPeriodTo);
        //手数料割引キャンペーン条件マスタ行.顧客徴収率 = （引数）登録情報.徴収率
        l_commCampaignCondMstParams.setAccountChargeRatio(Double.parseDouble(l_registInfo.collectRate));
        //手数料割引キャンペーン条件マスタ行.扱者コード = （引数）登録情報.扱者コード
        l_commCampaignCondMstParams.setSonarTraderCode(l_registInfo.traderCode);
        //手数料割引キャンペーン条件マスタ行.口座開設区分 = （引数）登録情報.口座開設区分
        l_commCampaignCondMstParams.setAccOpenKindDiv(l_registInfo.accountOpenDiv);
        //手数料割引キャンペーン条件マスタ行.口座開設日From = （引数）登録情報.口座開設日From
        l_commCampaignCondMstParams.setAccOpenDateFrom(l_registInfo.accountOpenDateFrom);
        //手数料割引キャンペーン条件マスタ行.口座開設日To = （引数）登録情報.口座開設日To
        l_commCampaignCondMstParams.setAccOpenDateTo(l_registInfo.accountOpenDateTo);
        //手数料割引キャンペーン条件マスタ行.登録タイプ = （引数）登録情報.登録タイプ
        l_commCampaignCondMstParams.setRegistType(l_registInfo.registType);
        //手数料割引キャンペーン条件マスタ行.処理区分 = "0"
        l_commCampaignCondMstParams.setStatus(WEB3StatusDef.NOT_DEAL);
        //手数料割引キャンペーン条件マスタ行.更新者コード = （引数）更新者コード
        l_commCampaignCondMstParams.setLastUpdater(l_strUpdaterCode);
        //手数料割引キャンペーン条件マスタ行.作成日時 = 現在日時
        l_commCampaignCondMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //手数料割引キャンペーン条件マスタ行.更新日時 = 現在日時
        l_commCampaignCondMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //（※個別顧客指定変更、口座開設条件指定変更、それぞれの登録内容についてはDB更新仕様を参照願います。）

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //１-３） QueryProcessor#doInsertQuery()メソッドをコールする。
            //[doInsertQuery()にセットするパラメータ]
            //arg0：　@１-１）で作成した行オブジェクト
            //１-４） QueryProcessor#doInsertQuery()の戻り値を取得する。
            Object l_objInsertQuery
                = l_queryProcessor.doInsertQuery(l_commCampaignCondMstParams);

            //２） 手数料割引キャンペーン商品マスタの登録を行う。
            //２-１） （引数）登録情報.商品コードの配列長回数以下を繰り返す。
            for (int i = 0; i < l_registInfo.itemCode.length; i++)
            {
                //２-１-１）  手数料割引キャンペーン商品マスタ行オブジェクトを作成し設定を行う。
                CommCampaignProductMstParams l_commCampaignProductMstParams = new CommCampaignProductMstParams();
                //手数料割引キャンペーン商品マスタ行.手数料割引キャンペーン条件ID
                //= （１-３）の戻り値）手数料割引キャンペーン条件マスタ主キーオブジェクト.手数料キャンペーン条件ID
                l_commCampaignProductMstParams.setCampaignId(((CommCampaignCondMstPK)l_objInsertQuery).campaign_id);
                //手数料割引キャンペーン商品マスタ行.商品コード = （引数）登録情報.商品コード[0…]
                l_commCampaignProductMstParams.setCommProductCode(l_registInfo.itemCode[i]);
                //手数料割引キャンペーン商品マスタ行.更新者コード = （引数）更新者コード
                l_commCampaignProductMstParams.setLastUpdater(l_strUpdaterCode);
                //手数料割引キャンペーン商品マスタ行.作成日時 = 現在日時
                l_commCampaignProductMstParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //手数料割引キャンペーン商品マスタ行.更新日時 = 現在日時
                l_commCampaignProductMstParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                //２-１-２）  QueryProcessor#doInsertQuery()メソッドをコールする。
                //[doInsertQuery()にセットするパラメータ]
                //arg0：　@２-１-１）で作成した行オブジェクト
                l_queryProcessor.doInsertQuery(l_commCampaignProductMstParams);
            }
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (is変更情報)<BR>
     * 変更後情報と変更前のデータを比較し、<BR>
     * <BR>
     * ①@ 変更不可情報が変更されている場合 <BR>
     *      OR 変更可能情報に変更がない場合はFALSEを返却する。<BR>
     * ② 変更不可情報に変更が無い場合 <BR>
     *      AND 変更可能情報に変更がある場合はTRUEを返却する。<BR>
     * <BR>
     * １） 手数料割引キャンペーン条件マスタより変更前データを取得する。<BR>
     *     this.getキャンペーン条件()をコールする。<BR>
     * <BR>
     *     [getキャンペーン条件()に指定する引数]<BR>
     *     手数料割引キャンペーン条件ID： （引数）更新情報.手数料割引キャンペーン条件ID<BR>
     * <BR>
     * ２） １） で取得した変更前データと、（引数）変更後情報の内容を比較する。<BR>
     * <BR>
     *   ２-１） 変更不可情報のチェックを行う。<BR>
     *           以下項目が 変更前データ と 変更後情報 で一項目でも差異がある場合は、<BR>
     * FALSEを返却する。<BR>
     * <BR>
     * 　@　@　@　@・手数料割引キャンペーン条件ID<BR>
     * 　@　@　@　@・手数料割引キャンペーン名称<BR>
     * 　@　@　@　@・証券会社コード<BR>
     * 　@　@　@　@・部店コード<BR>
     * 　@　@　@　@・顧客コード<BR>
     * 　@　@　@　@・顧客名称<BR>
     * 　@　@　@　@・扱者コード<BR>
     * 　@　@　@　@・口座開設区分<BR>
     * 　@　@　@　@・登録タイプ<BR>
     * 　@　@　@　@・削除フラグ<BR>
     * 　@　@　@　@・処理区分<BR>
     * 　@　@　@　@・商品コード[]  ※要素単位で比較（順不同）<BR>
     * <BR>
     *   ２-２） 変更可情報のチェックを行う。<BR>
     *           以下項目が 変更前データ と 変更後情報 で全項目差異がない場合は、<BR>
     * FALSEを返却する。<BR>
     * <BR>
     * 　@　@　@　@・口座開設経過期間（月）<BR>
     * 　@　@　@　@・口座開設経過期間（日）<BR>
     * 　@　@　@　@・対象期間From<BR>
     * 　@　@　@　@・対象期間To<BR>
     * 　@　@　@　@・顧客徴収率<BR>
     * 　@　@　@　@・口座開設日From<BR>
     * 　@　@　@　@・口座開設日To<BR>
     * <BR>
     * ３） ２）のチェックでいずれもFALSEに該当しなかった場合、<BR>
     * TRUEを返却する。<BR>
     * @@param l_changeAfterInfo - (変更後情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * @@return Boolean
     * @@throws WEB3BaseException
     * @@roseuid 45AD82380367
     */
    public boolean isChangeInfo(WEB3AccInfoCampaignInfo l_changeAfterInfo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeInfo(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１） 手数料割引キャンペーン条件マスタより変更前データを取得する。
        //this.getキャンペーン条件()をコールする。
        //[getキャンペーン条件()に指定する引数]
        //手数料割引キャンペーン条件ID： （引数）更新情報.手数料割引キャンペーン条件ID
        WEB3AccInfoCampaignInfo l_changeBeforeInfo
            = this.getCampaignCondition(l_changeAfterInfo.campaignId);

        String l_strAccountCode = l_changeBeforeInfo.accountCode;
        if (l_strAccountCode != null)
        {
            l_strAccountCode = l_changeBeforeInfo.accountCode.substring(0, 6);
        }
        //２） １） で取得した変更前データと、（引数）変更後情報の内容を比較する。
        //２-１） 変更不可情報のチェックを行う。
        //以下項目が 変更前データ と 変更後情報 で一項目でも差異がある場合は、FALSEを返却する。
        //・手数料割引キャンペーン条件ID
        //・手数料割引キャンペーン名称
        //・証券会社コード
        //・部店コード
        //・顧客コード
        //・顧客名称
        //・扱者コード
        //・口座開設区分
        //・登録タイプ
        //・削除フラグ
        //・処理区分
        //・商品コード[]  ※要素単位で比較（順不同）
        if ((!this.isEquals(l_changeAfterInfo.campaignId, l_changeBeforeInfo.campaignId)) ||
            (!this.isEquals(l_changeAfterInfo.campaignName, l_changeBeforeInfo.campaignName)) ||
            (!this.isEquals(l_changeAfterInfo.institutionCode, l_changeBeforeInfo.institutionCode)) ||
            (!this.isEquals(l_changeAfterInfo.branchCode, l_changeBeforeInfo.branchCode)) ||
            (!this.isEquals(l_changeAfterInfo.accountCode, l_strAccountCode)) ||
            (!this.isEquals(l_changeAfterInfo.accountName, l_changeBeforeInfo.accountName)) ||
            (!this.isEquals(l_changeAfterInfo.traderCode, l_changeBeforeInfo.traderCode)) ||
            (!this.isEquals(l_changeAfterInfo.accountOpenDiv, l_changeBeforeInfo.accountOpenDiv)) ||
            (!this.isEquals(l_changeAfterInfo.registType, l_changeBeforeInfo.registType)) ||
            (!this.isEquals(l_changeAfterInfo.deleteFlag, l_changeBeforeInfo.deleteFlag)) ||
            (!this.isEquals(l_changeAfterInfo.transactionDiv, l_changeBeforeInfo.transactionDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        if ((l_changeAfterInfo.itemCode != null) && (l_changeBeforeInfo.itemCode == null) ||
            (l_changeAfterInfo.itemCode == null) && (l_changeBeforeInfo.itemCode != null) ||
            ((l_changeAfterInfo.itemCode != null && (l_changeBeforeInfo.itemCode != null) &&
            (l_changeAfterInfo.itemCode.length != l_changeBeforeInfo.itemCode.length))))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２-２） 変更可情報のチェックを行う。
        //以下項目が 変更前データ と 変更後情報 で全項目差異がない場合は、FALSEを返却する。
        //・口座開設経過期間（月）
        //・口座開設経過期間（日）
        //・対象期間From
        //・対象期間To
        //・顧客徴収率
        //・口座開設日From
        //・口座開設日To
        if (this.isEquals(l_changeAfterInfo.accopenPassPeriodMonth, l_changeBeforeInfo.accopenPassPeriodMonth) &&
            this.isEquals(l_changeAfterInfo.accopenPassPeriodDay, l_changeBeforeInfo.accopenPassPeriodDay) &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodFrom, l_changeBeforeInfo.targetPeriodFrom) == 0 &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodTo, l_changeBeforeInfo.targetPeriodTo) == 0 &&
            this.isEquals(l_changeAfterInfo.collectRate, l_changeBeforeInfo.collectRate) &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_changeBeforeInfo.accountOpenDateFrom) == 0 &&
            WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateTo, l_changeBeforeInfo.accountOpenDateTo) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //３） ２）のチェックでいずれもFALSEに該当しなかった場合、TRUEを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (updateキャンペーン条件)<BR>
     * 手数料割引キャンペーン条件マスタレコードの更新を行う。<BR>
     * <BR>
     * １） 更新対処レコードの削除フラグチェックを行う。<BR>
     * <BR>
     *      [this.validate削除フラグ()に指定する引数]<BR>
     *        手数料割引キャンペーン条件ID： （引数）変更後情報.手数料割引キャンペーン条件ID<BR>
     *  <BR>
     * ２） Mapオブジェクトを作成し、以下を設定する。<BR>
     * <BR>
     *     コラム名： "acc_open_pass_month"  / 値： （引数）変更後情報.口座開設経過期間（月）<BR>
     *     コラム名： "acc_open_pass_date"   / 値： （引数）変更後情報.口座開設経過期間（日）<BR>
     *     コラム名： "appli_start_date"     / 値： （引数）変更後情報.対象期間From<BR>
     *     コラム名： "appli_end_date"       / 値： （引数）変更後情報.対象期間To<BR>
     *     コラム名： "account_charge_ratio" / 値： （引数）変更後情報.徴収率<BR>
     *     コラム名： "acc_open_date_from "  / 値： （引数）変更後情報.口座開設日From<BR>
     *     コラム名： "lacc_open_date_to"        / 値： （引数）変更後情報.口座開設日To<BR>
     *     コラム名： "status"           / 値： "0"<BR>
     *     コラム名： "last_updater"     / 値： （引数）更新者コード<BR>
     *     コラム名： "last_updated_timestamp"   / 値： 現在日時<BR>
     * <BR>
     * ３） QueryProcessor#doUpdateQuery()メソッドをコールする。<BR>
     * <BR>
     *       [doUpdateQuery()にセットするパラメータ]  <BR>
     *    　@　@ arg0：　@（引数）手数料割引キャンペーン条件ID<BR>
     * を基にした手数料割引キャンペーン条件マスタのPrimaryKeyオブジェクト<BR>
     *         arg1： ２）で作成したMapオブジェクト<BR>
     * @@param l_changeAfterInfo - (変更後情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * <BR>
     * @@param l_strUpdaterCode - 更新者コード<BR>
     * @@roseuid 45AD7DD500B3
     */
    public void updateCampaignCondition(WEB3AccInfoCampaignInfo l_changeAfterInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateCampaignCondition(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１） 更新対処レコードの削除フラグチェックを行う。
        //[this.validate削除フラグ()に指定する引数]
        //手数料割引キャンペーン条件ID： （引数）変更後情報.手数料割引キャンペーン条件ID
        this.validateDeleteFlag(l_changeAfterInfo.campaignId);

        //２） Mapオブジェクトを作成し、以下を設定する。
        //コラム名： "acc_open_pass_month" / 値： （引数）変更後情報.口座開設経過期間（月）
        //コラム名： "acc_open_pass_date" / 値： （引数）変更後情報.口座開設経過期間（日）
        //コラム名： "appli_start_date" / 値： （引数）変更後情報.対象期間From
        //コラム名： "appli_end_date" / 値： （引数）変更後情報.対象期間To
        //コラム名： "account_charge_ratio" / 値： （引数）変更後情報.徴収率
        //コラム名： "acc_open_date_from " / 値： （引数）変更後情報.口座開設日From
        //コラム名： "acc_open_date_to" / 値： （引数）変更後情報.口座開設日To
        //コラム名： "status" / 値： "0"
        //コラム名： "last_updater" / 値： （引数）更新者コード
        //コラム名： "last_updated_timestamp" / 値： 現在日時
        Map l_mapSearchCondition = new HashMap();
        l_mapSearchCondition.put("acc_open_pass_month", l_changeAfterInfo.accopenPassPeriodMonth);
        l_mapSearchCondition.put("acc_open_pass_date", l_changeAfterInfo.accopenPassPeriodDay);
        l_mapSearchCondition.put("appli_start_date", l_changeAfterInfo.targetPeriodFrom);
        l_mapSearchCondition.put("appli_end_date", l_changeAfterInfo.targetPeriodTo);
        l_mapSearchCondition.put("account_charge_ratio", l_changeAfterInfo.collectRate);
        l_mapSearchCondition.put("acc_open_date_from", l_changeAfterInfo.accountOpenDateFrom);
        l_mapSearchCondition.put("acc_open_date_to", l_changeAfterInfo.accountOpenDateTo);
        l_mapSearchCondition.put("status", WEB3StatusDef.NOT_DEAL);
        l_mapSearchCondition.put("last_updater", l_strUpdaterCode);
        l_mapSearchCondition.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

        //３） QueryProcessor#doUpdateQuery()メソッドをコールする。
        //[doUpdateQuery()にセットするパラメータ]
        //arg0：　@（引数）手数料割引キャンペーン条件IDを基にした手数料割引キャンペーン条件マスタのPrimaryKeyオブジェクト
        //arg1： ２）で作成したMapオブジェクト
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                new CommCampaignCondMstPK(Long.parseLong(l_changeAfterInfo.campaignId)),
                l_mapSearchCondition);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate対象期間)<BR>
     * 修正対象のキャンペーンが現在、キャンペーン期間中かどうかチェックを行う。<BR>
     * 修正対象の対象期間、または修正後の対象期間が過去の場合、例外をスローする。<BR>
     * <BR>
     * ①@変更前の対象期間Fromと変更前の対象期間Toの間に現在日付が含まれている場合<BR>
     *  （キャンペーン割引適用中の場合）は、<BR>
     *   1：警告有を、含まれていない場合は0：警告なしを返却する。<BR>
     * ②変更前の対象期間To、口座開設日To＋口座開設経過期間(月)＋口座開設経過期間（日）が過去の場合、<BR>
     * 例外をスローする。<BR>
     * ③ 口座開設日From及び口座開設日Toが変更されている場合、
     *   変更後の口座開設日From < 現在日付 及び変更後の口座開設日To < 現在日付の場合例外を生成する。
     * ④ キャンペーン実施中の情報で対象期間From、及び口座開設日Fromが変更されている場合は、 <BR>
     * 例外をスローする。<BR>
     * ⑤変更後の対象期間To、口座開設日Toチェック（validate入力対象期間()メソッドコール）<BR>
     * <BR>
     * <BR>
     * １） 手数料割引キャンペーン条件マスタより変更前データを取得する。<BR>
     *      戻り値に"0"をセットする。
     *     this.getキャンペーン条件()をコールする。<BR>
     * <BR>
     *     [getキャンペーン条件()に指定する引数]<BR>
     *     手数料割引キャンペーン条件ID： （引数）変更後情報.手数料割引キャンペーン条件ID<BR>
     * <BR>
     * <BR>
     * ２） 変更可能判定基準日の取得を行う。<BR>
     *   ２-１） 現在時刻 < 当日営業日17時の場合、変更可能判定基準日 = 翌々営業日<BR>
     * <BR>
     *   ２-２） 現在時刻 >= 当日営業日17時の場合、変更可能判定基準日 = 翌翌々営業日<BR>
     * <BR>
     * ３） １）で取得した、手数料割引キャンペーン条件マスタ行に於いて以下の処理を行う。 <BR>
     * <BR>
     *   ３-１） 手数料割引キャンペーン条件マスタ行.口座開設経過期間（月） == null &&  <BR>
     *           手数料割引キャンペーン条件マスタ行.口座開設経過期間（日） == null の場合、 <BR>
     * <BR>
     *     ３-１-１） 対象期間To != null の時、手数料割引キャンペーン条件マスタ行.対象期間To < 変更可能判定基準日の場合、 <BR>
     *                『過去期間のデータは変更・削除できません』例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02735<BR>
     * <BR>
     *     ３-１-２） 対象期間From != null && 対象期間To != null の時、 <BR>
     *                 手数料割引キャンペーン条件マスタ行.対象期間From <= 現在日付 && <BR>
     *                 手数料割引キャンペーン条件マスタ行.対象期間To >= 現在日付の場合、戻り値に"1"をセットする。 <BR>
     * <BR>
     *     ３-１-３） 対象期間From == null  && 対象期間To != null の時、 <BR>
     *                 手数料割引キャンペーン条件マスタ行.対象期間To >= 現在日付の場合、戻り値に"1"をセットする。 <BR>
     * <BR>
     *     ３-１-４） 対象期間From != null && 対象期間To == null の時、 <BR>
     *                 手数料割引キャンペーン条件マスタ行.対象期間From <= 現在日付の場合、戻り値に"1"をセットする。 <BR>
     * <BR>
     *     ３-１-５） 対象期間From == null && 対象期間To == null の場合、戻り値に"1"をセットする。 <BR>
     * <BR>
     * <BR>
     *   ３-２） 手数料割引キャンペーン条件マスタ行.口座開設経過期間（月） != null &&<BR>
     *           手数料割引キャンペーン条件マスタ行.口座開設経過期間（日） != null の場合、<BR>
     * <BR>
     *     ３-２-１） 手数料割引キャンペーン条件マスタ行.口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を<BR>
     *                 加算した日付 < 変更可能判定基準日の場合、『過去期間のデータは変更・削除できません』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02735<BR>
     * <BR>
     *     ３-２-２） 手数料割引キャンペーン条件マスタ行.口座開設日From <= 現在日付 && <BR>
     *                 手数料割引キャンペーン条件マスタ行.口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を<BR>
     *                 加算した日付 >= 現在日付の場合、戻り値に"1"をセットする。<BR>
     * <BR>
     * <BR>
     * ４） （引数）更新処理フラグ ==  "1"（更新処理） の時、以下の処理を行う。<BR>
     *   ４-１）  変更後情報.口座開設日To != null && <BR>
     *            変更前情報.口座開設日To != 変更後情報.口座開設日To && <BR>
     *            変更後情報.口座開設日To < 現在日付の場合、『口座開設日Toが過去日付です。』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02736<BR>
     * <BR>
     *   ４-２）  変更前情報.口座開設日From != null && <BR>
     * 	          変更前情報.口座開設日From != 変更後情報.口座開設日From && <BR>
     * 	          変更前情報.口座開設日From < 現在日付の場合、 <BR>
     * 	         『口座開設日Fromが過去日付のデータは変更できません。』例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02736<BR>
     * <BR>
     *   ４-３）  変更後情報.口座開設日From != null &&<BR>
     *     変更前情報.口座開設日From != 変更後情報.口座開設日Fromの場合、<BR>
     * 
     *     ４-３-１） 変更後口座開設日From < 現在日付の場合<BR>
     *     「口座開設日From は現在より過去日付には設定できません」例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02742<BR>
     * <BR>
     *    ４-４） 戻り値 == "1" の場合、対象期間From、及び口座開設日Fromのチェックを行う。<BR>
     * <BR>
     *      ４-４-１） 変更前対象期間From != 変更後対象期間Fromの場合、<BR>
     *    「キャンペーン実施中の対象期間From 及び 口座開設日Fromの変更はできません」<BR>
     *     例外をスローする。<BR>
     * <BR>
     *      ４-４-２） 変更前口座開設日From != 変更後口座開設日Fromの場合、<BR>
     *    「キャンペーン実施中の対象期間From 及び 口座開設日Fromの変更はできません」<BR>
     *     例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02734<BR>
     * <BR>
     *    ４-５）  入力値の対象期間チェックを行う。<BR>
     *            [this.validate入力対象期間チェック()に指定する引数] <BR>
     *              変更後情報：（引数）変更後情報 <BR>
     *              更新処理フラグ：（引数）更新処理フラグ <BR>
     * <BR>
     * <BR>
     * ５） ３）に当てはまらず、４）が正常終了した場合、戻り値を返却する。 <BR>
     * @@param l_changeAfterInfo - (変更後情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * @@param updateFlag - 更新処理フラグ
     * @@return String
     * @@roseuid 45B4779E01E5
     */
    public String validateTargetPeriod(WEB3AccInfoCampaignInfo l_changeAfterInfo, String updateFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTargetPeriod(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        //１） 手数料割引キャンペーン条件マスタより変更前データを取得する。
        //this.getキャンペーン条件()をコールする。
        //[getキャンペーン条件()に指定する引数]
        //手数料割引キャンペーン条件ID： （引数）変更後情報.手数料割引キャンペーン条件ID
        WEB3AccInfoCampaignInfo l_campaignInfo
            = this.getCampaignCondition(l_changeAfterInfo.campaignId);

        //２） 変更可能判定基準日の取得を行う。
        //  ２-１） 現在時刻 < 当日営業日17時の場合、変更可能判定基準日 = 翌々営業日
        //  ２-２） 現在時刻 >= 当日営業日17時の場合、変更可能判定基準日 = 翌翌々営業日

        Date l_judgeBaseDate = null;
        l_judgeBaseDate = this.getJudgeBaseDate(BIZDATE_T2);

        //３） １）で取得した、手数料割引キャンペーン条件マスタ行に於いて以下の処理を行う。
        //戻り値に"0"をセットする。
        String l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.NO_WARNING;
        Date l_currentTimestamp = GtlUtils.getSystemTimestamp();
        //３-１） 手数料割引キャンペーン条件マスタ行.口座開設経過期間（月） == null &&
        //手数料割引キャンペーン条件マスタ行.口座開設経過期間（日） == null の場合、
        if ((l_campaignInfo.accopenPassPeriodMonth == null)
            && (l_campaignInfo.accopenPassPeriodDay == null))
        {
            //３-１-１） 対象期間To != null の時、手数料割引キャンペーン条件マスタ行.対象期間To < 変更可能判定基準日の場合、
            //『過去期間のデータは変更・削除できません』例外をスローする。
            if ((l_campaignInfo.targetPeriodTo != null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodTo, l_judgeBaseDate) < 0))
            {
                log.debug("過去期間のデータは変更・削除できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02735,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "過去期間データ変更・削除不可");
            }

            //３-１-２） 対象期間From != null && 対象期間To != null の時、
            //手数料割引キャンペーン条件マスタ行.対象期間From <= 現在日付 &&
            //手数料割引キャンペーン条件マスタ行.対象期間To >= 現在日付の場合、戻り値に"1"をセットする。
            if ((l_campaignInfo.targetPeriodFrom != null) && (l_campaignInfo.targetPeriodTo != null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodFrom, l_currentTimestamp) <= 0)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodTo, l_currentTimestamp) >= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }

            //３-１-３） 対象期間From == null  && 対象期間To != null の時、
            //手数料割引キャンペーン条件マスタ行.対象期間To >= 現在日付の場合、戻り値に"1"をセットする。
            if ((l_campaignInfo.targetPeriodFrom == null) && (l_campaignInfo.targetPeriodTo != null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodTo, l_currentTimestamp) >= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }

            //３-１-４） 対象期間From != null && 対象期間To == null の時、
            //手数料割引キャンペーン条件マスタ行.対象期間From <= 現在日付の場合、戻り値に"1"をセットする。
            if ((l_campaignInfo.targetPeriodFrom != null) && (l_campaignInfo.targetPeriodTo == null)
                && (WEB3DateUtility.compareToDay(l_campaignInfo.targetPeriodFrom, l_currentTimestamp) <= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }

            //３-１-５） 対象期間From == null && 対象期間To == null の場合、戻り値に"1"をセットする。
            if ((l_campaignInfo.targetPeriodFrom == null) && (l_campaignInfo.targetPeriodTo == null))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }
        }

        //  ３-２） 手数料割引キャンペーン条件マスタ行.口座開設経過期間（月） != null &&
        //手数料割引キャンペーン条件マスタ行.口座開設経過期間（日） != null の場合、
        if ((l_campaignInfo.accopenPassPeriodMonth != null) && (l_campaignInfo.accopenPassPeriodDay != null))
        {
            //    ３-２-１） 手数料割引キャンペーン条件マスタ行.口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を
            //加算した日付 < 変更可能判定基準日の場合、『過去期間のデータは変更・削除できません』例外をスローする。
            if (WEB3DateUtility.compareToDay(
                WEB3DateUtility.addDay(
                WEB3DateUtility.addMonth(
                l_campaignInfo.accountOpenDateTo,
                Integer.parseInt(l_campaignInfo.accopenPassPeriodMonth)),
                Integer.parseInt(l_campaignInfo.accopenPassPeriodDay)),
                l_judgeBaseDate) < 0)
            {
                log.debug("過去期間のデータは変更・削除できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02735,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "過去期間データ変更・削除不可");
            }

            //    ３-２-２） 手数料割引キャンペーン条件マスタ行.口座開設日From <= 現在日付 &&
            //手数料割引キャンペーン条件マスタ行.口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を
            //加算した日付 >= 現在日付の場合、戻り値に"1"をセットする。
            if ((WEB3DateUtility.compareToDay(l_campaignInfo.accountOpenDateFrom, l_currentTimestamp) <= 0)
                && (WEB3DateUtility.compareToDay(
                WEB3DateUtility.addDay(
                WEB3DateUtility.addMonth(
                l_campaignInfo.accountOpenDateTo,
                Integer.parseInt(l_campaignInfo.accopenPassPeriodMonth)),
                Integer.parseInt(l_campaignInfo.accopenPassPeriodDay)),
                l_currentTimestamp) >= 0))
            {
                l_returnFlag = WEB3AccInfoTargetPeriodCheckDef.WARNING;
            }
        }

        //４） （引数）更新処理フラグ ==  "1"（更新処理） の時、以下の処理を行う。
        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(updateFlag))
        {
	        //４-１）  変更後情報.口座開設日To != null &&
	        //変更前情報.口座開設日To != 変更後情報.口座開設日To &&
	        //変更後情報.口座開設日To < 現在日付の場合、『口座開設日Toが過去日付です。』例外をスローする。
	        if (l_changeAfterInfo.accountOpenDateTo != null
	            && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateTo, l_campaignInfo.accountOpenDateTo) != 0
	            && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateTo, l_currentTimestamp) < 0)
	        {
	            log.debug("口座開設日To設定値エラー");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02736,
	                this.getClass().getName() + STR_METHOD_NAME,
	                "口座開設日Toが過去日付です。");
	        }
 
	        //４-２）  変更前情報.口座開設日From != null &&
	        //変更前情報.口座開設日From != 変更後情報.口座開設日From &&
	        //変更前情報.口座開設日From < 現在日付の場合、
	        //『口座開設日Fromが過去日付のデータは変更できません。』例外をスローする。
	        if (l_campaignInfo.accountOpenDateFrom != null
		        && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_campaignInfo.accountOpenDateFrom) != 0
		        && WEB3DateUtility.compareToDay(l_campaignInfo.accountOpenDateFrom, l_currentTimestamp) < 0)
	        {
	        	log.debug("口座開設日From過去日エラー");
	            log.exiting(STR_METHOD_NAME);
	            throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02744,
	                this.getClass().getName() + STR_METHOD_NAME,
	                "口座開設日Fromが過去日付のデータは変更できません。");
	        }
	        
	        //４-３）  変更後情報.口座開設日From != null &&
	        //変更前情報.口座開設日From != 変更後情報.口座開設日Fromの場合
	        if (l_changeAfterInfo.accountOpenDateFrom != null
			    && WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_campaignInfo.accountOpenDateFrom) != 0)
	        {
	        	//４-３-１） 変更後口座開設日From < 現在日付の場合
	        	//「口座開設日From は現在より過去日付には設定できません」例外をスローする。
	        	if (WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_currentTimestamp) < 0)
	        	{
		            log.debug("口座開設日From設定値エラー");
		            log.exiting(STR_METHOD_NAME);
		            throw new WEB3BusinessLayerException(
		                WEB3ErrorCatalog.BUSINESS_ERROR_02742,
		                this.getClass().getName() + STR_METHOD_NAME,
		                "口座開設日From は現在より過去日付には設定できません。");	        		
	        	}
	        }
	        
			//４-４）戻り値 == "1" の場合、対象期間From、及び口座開設日Fromのチェックを行う。
			//  ４-４-１） 変更前対象期間From != 変更後対象期間Fromの場合、
			//      「キャンペーン実施中の対象期間From 及び 口座開設日Fromの変更はできません」例外をスローする。
			//  ４-４-２） 変更前口座開設日From != 変更後口座開設日Fromの場合、
			//      「キャンペーン実施中の対象期間From 及び 口座開設日Fromの変更はできません」例外をスローする。
			if (WEB3AccInfoTargetPeriodCheckDef.WARNING.equals(l_returnFlag))
			{
				if (WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodFrom, l_campaignInfo.targetPeriodFrom) != 0
					|| WEB3DateUtility.compareToDay(l_changeAfterInfo.accountOpenDateFrom, l_campaignInfo.accountOpenDateFrom) != 0)
				{
					log.debug("キャンペーン実施中の対象期間From 及び 口座開設日Fromの変更はできません。");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_02734,
						this.getClass().getName() + STR_METHOD_NAME,
						"キャンペーン実施中開始日付変更不可。");
				}
			}
	
	        //４-５）  入力値の対象期間チェックを行う。
	        //   [this.validate入力対象期間チェック()に指定する引数]
	        //      変更後情報：（引数）変更後情報
	        //      更新処理フラグ：（引数）更新処理フラグ
	        if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(updateFlag))
	        {
	            this.validateInputTargetPeriod(l_changeAfterInfo, updateFlag);
	        }
		}

        //５） ３）に当てはまらず、４）が正常終了した場合、戻り値を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_returnFlag;
    }

    /**
     * (validate入力対象期間チェック)<BR>
     * 入力値の対象期間が適用開始日より過去の場合、例外をスローする。<BR>
     * 入力値の期間指定To <= 口座開設日Toの場合、例外をスローする。
     * １） 適用開始日の取得を行う。<BR>
     *   １-１） 現在時刻 < 当日営業日17：00：00の場合、適用開始日 = 翌々営業日<BR>
     *   １-２） 現在時刻 >= 当日営業日17：00：00の場合、適用開始日 = 翌翌々営業日<BR>
     * <BR>
     * ２） （引数）変更後情報 に於いて以下の処理を行う。<BR>
     * <BR>
     *   ２-１） （引数）変更後情報.口座開設経過期間（月） != null && <BR>
     *           （引数）変更後情報.口座開設経過期間（日） != null の場合<BR>
     * <BR>
     *     ２-１-１） （引数）更新処理フラグ == "1"（更新処理）の場合、<BR>
     *                 （引数）変更後情報.口座開設日Toに（引数）変更後情報.口座開設経過期間(月)と<BR>
     *                 （引数）変更後情報.口座開設経過期間（日）を加算した日付 < 適用開始日 の時、<BR>
     *                 『口座開設日Toと口座開設経過期間を足した日付が適用開始日より過去の日付です。』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02737<BR>
     *     ２-１-２） （引数）更新処理フラグ == "0"（登録処理）の場合、<BR>
     *        ２-１-２-１）         （引数）変更後情報.口座開設日To < 適用開始日 の時、<BR>
     *                 『口座開設日Toが適用開始日より過去の日付です。』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02738<BR>
     *        ２-１-２-２） （引数）変更後情報. 口座開設日From != null の場合、<BR>
     *          （引数）変更後情報.口座開設日Fromに（引数）変更後情報.口座開設経過期間(月)と<BR>
     *          （引数）変更後情報.口座開設経過期間（日）を加算した日付 < 適用開始日 の時、<BR>
     *          『口座開設日Fromと口座開設経過期間を足した日付が適用開始日以前の日付です。』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02739<BR>
     * <BR>
     *   ２-２） （引数）変更後情報.対象期間To != null の場合<BR>
     * <BR>
     *     ２-２-１）  （引数）変更後情報.対象期間To < 適用開始日の場合、
     *                『期間指定Toが適用開始日より過去の日付です。』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02740<BR>     * <BR>
     *     ２-２-２） （引数）変更後情報. 口座開設日To != null の時、<BR>
     *          （引数）変更後情報.対象期間To <= （引数）変更後情報.口座開設日To の場合、<BR>
     *          『期間指定Toが口座開設日To以前の日付です。』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02741<BR>
     *
     * @@param l_changeAfterInfo - (変更後情報)<BR>
     * 手数料割引キャンペーン条件情報オブジェクト<BR>
     * @@param updateFlag - 更新処理フラグ
     * @@throws WEB3BaseException
     */
    public void validateInputTargetPeriod(WEB3AccInfoCampaignInfo l_changeAfterInfo, String updateFlag)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateInputTargetPeriod(WEB3AccInfoCampaignInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_changeAfterInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // １） 適用開始日の取得を行う。
        //  １-１） 現在時刻 < 当日営業日17：00：00の場合、適用開始日 = 翌々営業日
        //          現在時刻 >= 当日営業日17：00：00の場合、適用開始日 = 翌翌々営業日
        Date l_judgeBaseDate = this.getJudgeBaseDate(BIZDATE_T2);

        // ２） （引数）変更後情報 に於いて以下の処理を行う。
        //  ２-１） （引数）変更後情報.口座開設経過期間（月） != null &&
        //     （引数）変更後情報.口座開設経過期間（日） != null の場合

        if (l_changeAfterInfo.accopenPassPeriodMonth != null
             && l_changeAfterInfo.accopenPassPeriodDay != null)
        {
            //２-１-１）（引数）更新処理フラグ == "1"（更新処理）の場合
            //（引数）変更後情報.口座開設日Toに（引数）変更後情報.口座開設経過期間(月)と
            //（引数）変更後情報.口座開設経過期間（日）を加算した日付 < 適用開始日 の時、
            //『口座開設日Toと口座開設経過期間を足した日付が適用開始日より過去の日付です。』例外をスローする。
            if (WEB3AccInfoUpdateFlagDef.UPDATE.equals(updateFlag))
            {
                if(WEB3DateUtility.compareToDay(WEB3DateUtility.addDay(
                    WEB3DateUtility.addMonth(l_changeAfterInfo.accountOpenDateTo,
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodMonth)),
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodDay)),
                    l_judgeBaseDate) < 0)
                {
                    log.debug("口座開設日To設定値エラー");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02737,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "口座開設日Toと口座開設経過期間を足した日付が適用開始日より過去の日付です。");
                }
            }
            //２-１-２）（引数）更新処理フラグ == "0"（登録処理）の場合、
            if (WEB3AccInfoUpdateFlagDef.LOGIN.equals(updateFlag))
            {
                //２-１-２-１）（引数）変更後情報.口座開設日To < 適用開始日 の時、
                //『口座開設日Toが適用開始日より過去の日付です。』例外をスローする。            	
                if(WEB3DateUtility.compareToDay(
                     l_changeAfterInfo.accountOpenDateTo,l_judgeBaseDate) < 0)
                {
                    log.debug("口座開設日To設定値エラー");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02738,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "口座開設日Toが適用開始日より過去の日付です。");
                }
                //２-１-２-２） （引数）変更後情報. 口座開設日From != null の場合、
                //（引数）変更後情報.口座開設日Fromに（引数）変更後情報.口座開設経過期間(月)と
                //（引数）変更後情報.口座開設経過期間（日）を加算した日付 < 適用開始日 の時、
                //『口座開設日Fromと口座開設経過期間を足した日付が適用開始日以前の日付です。』例外をスローする。
                if(WEB3DateUtility.compareToDay(WEB3DateUtility.addDay(
                    WEB3DateUtility.addMonth(l_changeAfterInfo.accountOpenDateFrom,
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodMonth)),
                    Integer.parseInt(l_changeAfterInfo.accopenPassPeriodDay)),
                    l_judgeBaseDate) < 0)
                {
                    log.debug("口座開設日From設定値エラー");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02739,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "口座開設日Fromと口座開設経過期間を足した日付が適用開始日以前の日付です。");
                }
            }
        }
        //２-２） （引数）変更後情報.口座開設経過期間（月） == null &&
        //     （引数）変更後情報.口座開設経過期間（日） == null の場合
        //     （引数）変更後情報.対象期間To != null の場合
        else if (l_changeAfterInfo.accopenPassPeriodMonth == null
            && l_changeAfterInfo.accopenPassPeriodDay == null
            && l_changeAfterInfo.targetPeriodTo != null)
        {
            //２-２-１）（引数）変更後情報.対象期間To < 適用開始日の場合、『期間指定Toが適用開始日より過去の日付です。』例外をスローする。
            if (WEB3DateUtility.compareToDay(l_changeAfterInfo.targetPeriodTo, l_judgeBaseDate) < 0)
            {
                log.debug("期間指定To設定値エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02740,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "期間指定Toが適用開始日より過去の日付です。");
            }
            //２-２-２） （引数）変更後情報. 口座開設日To != null の時、
            //（引数）変更後情報.対象期間To <= （引数）変更後情報.口座開設日To の場合、
            //『期間指定Toが口座開設日To以前の日付です。』例外をスローする。
            if (WEB3DateUtility.compareToDay(
                 l_changeAfterInfo.targetPeriodTo, l_changeAfterInfo.accountOpenDateTo) <= 0)
            {
                log.debug("期間指定To設定値エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02741,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "期間指定Toが口座開設日To以前の日付です。");
            }
        }
    }

    /**
     * (validate削除フラグ)<BR>
     * （引数）手数料割引キャンペーン条件ID と 削除フラグ=１（削除済み）を条件として<BR>
     * 手数料キャンペーン条件マスタよりレコード件数を取得する。<BR>
     * 取得された件数 > 0 の場合、『キャンペーンは既に削除されています』例外をスローする。<BR>
     * <BR>
     * １） キャンペーン検索条件オブジェクトを生成する。<BR>
     * <BR>
     *     １-１） キャンペーン検索条件コンストラクタを使用し、キャンペーン検索条件オブジェクトを生成する。<BR>
     * <BR>
     *     １-２） 検索条件の設定を行う。<BR>
     *   <BR>
     *       [set削除フラグ()に指定する引数]<BR>
     *         削除フラグ： "1"<BR>
     *       [set手数料割引キャンペーン条件ID()に指定する引数]<BR>
     *         手数料割引キャンペーン条件ID： （引数）手数料割引キャンペーン条件ID<BR>
     * <BR>
     * ２） 手数料割引キャンペーン条件マスタよりレコード件数を取得する。<BR>
     * <BR>
     *   ２-１） 検索条件文字列を作成する。<BR>
     * <BR>
     *      [this.create検索条件文字列()に指定する引数]<BR>
     *        キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     *   ２-２） 検索データコンテナを作成する。<BR>
     * <BR>
     *      [this.create検索データコンテナ()に指定する引数]<BR>
     *        キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト<BR>
     * <BR>
     *   ２-３） QueryProcessor#doGetCountQuery)メソッドをコールする。<BR>
     * <BR>
     *      [doGetCountQuery()に指定する引数]  <BR>
     *    　@  arg0： 手数料割引キャンペーン条件マスタRowType<BR>
     *   　@　@ arg1：　@this.create検索条件文字列() の戻り値<BR>
     * 　@  　@ arg2：　@this.create検索データコンテナ()の戻り値 <BR>
     * <BR>
     * <BR>
     * ３） ２）の戻り値 > 0 の場合、『キャンペーンは既に削除されています』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00989<BR>
     * @@param l_strCampaignId - 手数料割引キャンペーン条件ID<BR>
     * @@roseuid 45B99D7D0245
     */
    protected void validateDeleteFlag(String l_strCampaignId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDeleteFlag(String)";
        log.entering(STR_METHOD_NAME);

        //１） キャンペーン検索条件オブジェクトを生成する。
        //１-１） キャンペーン検索条件コンストラクタを使用し、キャンペーン検索条件オブジェクトを生成する。
        WEB3AdminAccInfoCampaignSearchCondition l_campaignSearchCondition
            = new WEB3AdminAccInfoCampaignSearchCondition();

        //１-２） 検索条件の設定を行う。
        //[set削除フラグ()に指定する引数]
        //削除フラグ： "1"
        //[set手数料割引キャンペーン条件ID()に指定する引数]
        //手数料割引キャンペーン条件ID： （引数）手数料割引キャンペーン条件ID
        l_campaignSearchCondition.setDeleteFlag(WEB3PvInfoDeleteFlagDef.DELETE_YES);
        l_campaignSearchCondition.setCommissionCampaignConditionId(l_strCampaignId);

        //２） 手数料割引キャンペーン条件マスタよりレコード件数を取得する。
        //２-１） 検索条件文字列を作成する。
        //[this.create検索条件文字列()に指定する引数]
        //キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト
        String l_strSearchCondition
            = this.createSearchCondition(l_campaignSearchCondition);

        //２-２） 検索データコンテナを作成する。
        //[this.create検索データコンテナ()に指定する引数]
        //キャンペーン検索項目：１） で作成したキャンペーン検索条件オブジェクト
        Object[] l_strSearchContainers
            = this.createSearchContainers(l_campaignSearchCondition);

        //２-３） QueryProcessor#doGetCountQuery)メソッドをコールする。
        //[doGetCountQuery()に指定する引数]
        //arg0： 手数料割引キャンペーン条件マスタRowType
        //arg1：　@this.create検索条件文字列() の戻り値
        //arg2：　@this.create検索データコンテナ()の戻り値
        int l_intResultCnt;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_intResultCnt = l_queryProcessor.doGetCountQuery(
                CommCampaignCondMstRow.TYPE,
                l_strSearchCondition,
                l_strSearchContainers);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //３） ２）の戻り値 > 0 の場合、『キャンペーンは既に削除されています』例外をスローする。
        if (l_intResultCnt > 0)
        {
            log.debug("キャンペーンは既に削除されています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02727,
                this.getClass().getName() + STR_METHOD_NAME,
                "キャンペーンは既に削除されています。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45C08B5303A9
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        return null;
    }

    /**
     * 日付型データを"YYYYMMDD"フォーマットで文字列に型変換する。
     * @@param l_dat 日付型データ
     * @@return 変換後の文字列を返す。
     */
    public String formatDate(Date l_dat)
    {
        return WEB3DateUtility.formatDate(l_dat, YYYYMMDD_DATE_FORMAT);
    }

    /**
     * @@param l_strFirst
     * @@param l_strSecond
     * @@return boolean
     */
    private boolean isEquals(String l_strFirst, String l_strSecond)
    {
        if (l_strFirst == null)
        {
            if (l_strSecond == null)
            {
                return true;
            }
            return false;
        }
        else if (l_strSecond == null)
        {
            return false;
        }
        return l_strFirst.equals(l_strSecond);
    }

    /**
     * 適用開始日取得
     * 現在日時を当日営業日17：00と比較し、以前であれば引数を返し、
     * 以降であれば、引数の翌営業日（引数翌営業日の場合、翌々営業日）を返す。
     * @@param l_nextBizDate - 営業日当日17:00以前の場合の営業基準日
     * @@return 判定用営業日
     */
    private Date getJudgeBaseDate(int l_nextBizDate) throws WEB3BaseException
    {
        // 現在日時取得
        Timestamp l_currentTimestamp = GtlUtils.getSystemTimestamp();
        Calendar l_currentCalendar =Calendar.getInstance();
        l_currentCalendar.setTime(l_currentTimestamp);
        // 当日営業日取得（現在日時が非営業日の場合次の営業日を取得する。）
        Calendar l_bizDateCalendar = Calendar.getInstance();
        l_bizDateCalendar.setTime(WEB3GentradeUtils.getBizDate(l_currentTimestamp));

        // 当日営業日17:00作成
        l_bizDateCalendar.clear(Calendar.HOUR_OF_DAY);
        l_bizDateCalendar.clear(Calendar.HOUR);
        l_bizDateCalendar.clear(Calendar.MINUTE);
        l_bizDateCalendar.clear(Calendar.SECOND);
        l_bizDateCalendar.clear(Calendar.MILLISECOND);
        l_bizDateCalendar.add(Calendar.HOUR_OF_DAY, 17);
        l_bizDateCalendar.add(Calendar.MINUTE, 0);
        l_bizDateCalendar.add(Calendar.SECOND, 0);
        l_bizDateCalendar.add(Calendar.MILLISECOND, 0);
        // 現在時刻 < 当日営業日17：00：00の場合、適用開始日 = l_nextBizDate
        //現在時刻 >= 当日営業日17：00：00の場合、適用開始日 = l_nextBizDate + 1
        Date l_judgeDate = null;
        if (l_currentCalendar.before(l_bizDateCalendar))
        {
            l_judgeDate = WEB3GentradeUtils.getBizDate(l_currentTimestamp, l_nextBizDate);
        }
        else
        {
            l_judgeDate = WEB3GentradeUtils.getBizDate(l_currentTimestamp, ++l_nextBizDate);
        }
        return l_judgeDate;
    }
}
@
