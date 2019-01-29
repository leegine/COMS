head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料割引キャンペーン登録顧客照会サービス実装クラス
                       (WEB3AdminAccInfoCampaignRegistAccListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 孟亜南 (中訊) 新規作成
Revision History : 2007/02/01 孟亜南 (中訊) モデルNo.166
Revision History : 2007/02/01 孟亜南 (中訊) モデルNo.176
Revision History : 2007/03/05 山田 (SCS) モデルNo.198
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.accountinfo.data.CommCampaignAccHistoryRow;
import webbroker3.accountinfo.data.CommCampaignProductMstParams;
import webbroker3.accountinfo.data.CommCampaignProductMstRow;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignRegistAccountInfo;
import webbroker3.accountinfo.message.WEB3AccInfoSortKey;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignRegistAccListResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 手数料割引キャンペーン登録顧客照会サービス実装クラス<BR>
 * @@author 孟亜南 
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListServiceImpl implements WEB3AdminAccInfoCampaignRegistAccListService
{
    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoCampaignRegistAccListServiceImpl.class);

    /**
     * @@roseuid 45C08B5501C5
     */
    public WEB3AdminAccInfoCampaignRegistAccListServiceImpl()
    {

    }

    /**
     * 手数料割引キャンペーン登録顧客照会処理を実施する。<BR>
     * <BR>
     * １）リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     *  ■ 引数のリクエストデータが、<BR>
     * 管管理者お客様情報手数料割引キャンペーン登録顧客照会ﾘｸｴｽﾄの場合<BR> 
     * ・ get照会画面()をコールする。<BR>
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException 
     * @@roseuid 45ADF02103A4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3AdminAccInfoCampaignRegistAccListRequest)
        {
            //get照会画面
            l_response = 
                this.getListScreen((WEB3AdminAccInfoCampaignRegistAccListRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 検索条件文字列を編集する。 <BR>
     * <BR>
     * １） 戻り値生成 <BR>
     * ・戻り値の検索条件文字列インスタンス（：StringBuffer）を生成する。<BR>
     * <BR>
     * ２） 証券会社コード条件追加<BR>
     * ・証券会社コード条件を追加する。 <BR>
     * 　@　@ " institution_code = ? " <BR>
     * <BR>
     * ３） 商品コード条件追加<BR>
     * ・パラメータ.リクエストデータ.商品コード != nullの場合、<BR>
     * 商品コード条件を追加する。 <BR>
     * <BR>
     * "and campaign_id in <BR>
     * ( select campaign_id from<BR>
     * comm_campaign_product_mst where comm_product_code = ? )" <BR>
     * <BR>
     * ４） キャンペーン名称条件追加 <BR>
     * ・パラメータ.リクエストデータ.キャンペーン名称 != nullの場合、<BR>
     * キャンペーン名称条件を追加する。 <BR>
     * 　@　@" and comm_campaign_name like ? " <BR>
     * <BR>
     * <BR>
     * ５） 部店コード条件追加 <BR>
     * ・パラメータ.リクエストデータ.部店コード != nullの場合、<BR>
     * 部店コード条件を追加する。 <BR>
     * 　@　@" and branch_code = ? " <BR>
     * <BR>
     * ６） 顧客コード条件追加 <BR>
     * ・パラメータ.リクエストデータ.顧客コード != nullの場合、<BR>
     * 顧客コード条件を追加する。 <BR>
     * 　@　@" and account_code like ? " <BR>
     * <BR>
     * ７） 扱者コード条件追加 <BR>
     * ・パラメータ.リクエストデータ.扱者コード != nullの場合、<BR>
     * 扱者コード条件を追加する。 <BR>
     * 　@　@" and sonar_trader_code = ? " <BR>
     * <BR>
     * ８） 口座開設区分条件追加 <BR>
     * ・パラメータ.リクエストデータ.口座開設区分 != nullの場合、<BR>
     * 口座開設区分条件を追加する。 <BR>
     * 　@　@" and acc_open_kind_div = ? " <BR>
     * <BR>
     * ９） 徴収率条件追加 <BR>
     * ・パラメータ.リクエストデータ.徴収率 != null の場合、<BR>
     * 徴収率条件を追加する。 <BR>
     * 　@　@" and account_charge_ratio = ? " <BR>
     * <BR>
     * １０）　@対象期間From条件追加 <BR>
     * ・パラメータ.リクエストデータ.対象日 != nullの場合、<BR>
     * 対象期間From条件を追加する。 <BR>
     * 　@　@" and ( appli_start_date <= ? or appli_start_date is null )" <BR>
     * <BR>
     * １１） 対象期間To条件追加 <BR>
     * ・パラメータ.リクエストデータ.対象日 != nullの場合、<BR>
     * 対象期間To条件を追加する。 <BR>
     *     " and ( appli_end_date >= ? or appli_end_date is null )" <BR>
     * <BR>
     * １２） 登録タイプ条件追加 <BR>
     * ・パラメータ.リクエストデータ.登録タイプ != nullの場合、<BR>
     * 登録タイプ条件を追加する。 <BR>
     * 　@　@ " and regist_type = ? " <BR>
     * <BR>
     * １３） 有効区分条件追加 <BR>
     * ・パラメータ.リクエストデータ.有効区分 != nullの場合、<BR>
     * 有効区分条件を追加する。 <BR>
     * 　@　@" and valid_div = ? " <BR>
     * <BR>
     * １４）検索条件文字列インスタンス.toString() を返却する <BR>
     * <BR>
     * 
     * @@param l_request -
     *            (リクエストデータ)<BR>
     *            管理者お客様情報手数料割引キャンペーン<BR>
     *            登録顧客照会リクエストオブジェクト<BR>
     * @@return String
     * @@roseuid 45ADF2F003E3
     */
    protected String createSearchCondition(WEB3AdminAccInfoCampaignRegistAccListRequest l_request)
    {
        final String STR_METHOD_NAME = ".createSearchCondition(WEB3AdminAccInfoCampaignRegistAccListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１） 戻り値生成 
        //・戻り値の検索条件文字列インスタンス（：StringBuffer）を生成する。
        StringBuffer l_sbSearchCondition = new StringBuffer();
        
        //２） 証券会社コード条件追加
        //・証券会社コード条件を追加する。 
        //" institution_code = ? " 
        l_sbSearchCondition.append(" institution_code = ? ");
        
        //３） 商品コード条件追加
        //・パラメータ.リクエストデータ.商品コード != nullの場合
        //商品コード条件を追加する。
        if (l_request.itemCode != null)
        {
            //"and campaign_id in ( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )"
            l_sbSearchCondition.append("and campaign_id in " +
                    "( select campaign_id from comm_campaign_product_mst where comm_product_code = ? )");
        }
        
        //４） キャンペーン名称条件追加
        //・パラメータ.リクエストデータ.キャンペーン名称 != nullの場合、
        //キャンペーン名称条件を追加する。 
        if (l_request.campaignName != null)
        {
            //" and comm_campaign_name like ? "
            l_sbSearchCondition.append(" and comm_campaign_name like ? ");
        }
        
        //５） 部店コード条件追加
        //・パラメータ.リクエストデータ.部店コード != nullの場合、
        //部店コード条件を追加する。 
        if (l_request.branchCode != null)
        {
            //" and branch_code = ? "
            l_sbSearchCondition.append(" and branch_code = ? ");
        }
        
        //６） 顧客コード条件追加 
        //・パラメータ.リクエストデータ.顧客コード != nullの場合、
        //顧客コード条件を追加する。 
        if (l_request.accountCode != null)
        {
            //" and account_code like ? "
            l_sbSearchCondition.append(" and account_code like ? ");
        }
        
        //７） 扱者コード条件追加
        //・パラメータ.リクエストデータ.扱者コード != nullの場合、
        //扱者コード条件を追加する。
        if (l_request.traderCode != null)
        {
            //" and sonar_trader_code = ? "
            l_sbSearchCondition.append(" and sonar_trader_code = ? ");
        }
        
        //８） 口座開設区分条件追加 
        //・パラメータ.リクエストデータ.口座開設区分 != nullの場合、
        //口座開設区分条件を追加する。
        if (l_request.accountOpenDiv != null)
        {
            //" and acc_open_kind_div = ? " 
            l_sbSearchCondition.append(" and acc_open_kind_div = ? ");
        }
        
        //９） 徴収率条件追加
        //・パラメータ.リクエストデータ.徴収率 != null の場合、
        //徴収率条件を追加する。 
        if (l_request.collectRate != null)
        {
            //" and account_charge_ratio = ? "
            l_sbSearchCondition.append(" and account_charge_ratio = ? ");
        }
        
        //１０）　@対象期間From条件追加
        //・パラメータ.リクエストデータ.対象日 != nullの場合、
        //対象期間From条件を追加する。
        if (l_request.targetDate != null)
        {
            //" and ( appli_start_date <= ? or appli_start_date is null ) "
            l_sbSearchCondition.append(" and ( appli_start_date <= ? or appli_start_date is null ) ");
        }
        
        //１１） 対象期間To条件追加 
        //・パラメータ.リクエストデータ.対象日 != nullの場合
        //対象期間To条件を追加する。
        if (l_request.targetDate != null)
        {
            //" and ( appli_end_date >= ? or appli_end_date is null ) "
            l_sbSearchCondition.append(" and ( appli_end_date >= ? or appli_end_date is null ) ");
        }
        
        //１２） 登録タイプ条件追加 
        //・パラメータ.リクエストデータ.登録タイプ != nullの場合、
        //登録タイプ条件を追加する。 
        if (l_request.registType != null)
        {
            //" and regist_type = ? "
            l_sbSearchCondition.append(" and regist_type = ? ");
        }
        
        //１３） 有効区分条件追加
        //・パラメータ.リクエストデータ.有効区分 != nullの場合、
        //有効区分条件を追加する。 
        if (l_request.activeDiv != null)
        {
            //" and valid_div = ? "
            l_sbSearchCondition.append(" and valid_div = ? ");
        }
        
        //１４）検索条件文字列インスタンス.toString() を返却する
        log.exiting(STR_METHOD_NAME);
        return l_sbSearchCondition.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 検索条件データコンテナを編集する。 <BR>
     * <BR>
     * １） 戻り値生成 <BR>
     * ・戻り値編集用インスタンス（：ArrayList）を生成する。<BR>
     * <BR>
     * ２） 証券会社コード条件追加 <BR>
     * ・検索条件データコンテナインスタンスに、証券会社コードを追加する。<BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.証券会社コード <BR>
     * <BR>
     * ３） 商品コード条件追加 <BR>
     * ・パラメータ.リクエストデータ.商品コード != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに商品コード条件を追加する。<BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.商品コード<BR>
     * <BR>
     * ４） キャンペーン名称条件追加 <BR>
     * ・パラメータ.リクエストデータ.キャンペーン名称 != nullの場合、<BR>
     * 検索条件データコンテナインスタンスにキャンペーン名称条件を追加する。<BR>
     * ※あいまい(前後方一致)検索<BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * "%" + パラメータ.リクエストデータ.キャンペーン名称 + "%"<BR>
     * <BR>
     * ５） 部店コード条件追加 <BR>
     * ・パラメータ.リクエストデータ.部店コード != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに部店コード条件を追加する。<BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.キャンペーン検索項目.部店コード<BR>
     * <BR>
     * ６） 顧客コード条件追加 <BR>
     * ・パラメータ.リクエストデータ.顧客コード != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに顧客コード条件を追加する。<BR>
     * ※前方一致検索<BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.顧客コード + "%"<BR>
     * <BR>
     * ７） 扱者コード条件追加 <BR>
     * ・パラメータ.リクエストデータ.扱者コード != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに扱者コード条件を追加する。<BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.扱者コード <BR>
     * <BR>
     * ８） 口座開設区分条件追加 <BR>
     * ・パラメータ.リクエストデータ.口座開設区分 != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに口座開設区分条件を追加する。<BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.口座開設区分<BR>
     * <BR>
     * ９） 徴収率条件追加 <BR>
     * ・パラメータ.リクエストデータ.徴収率 != null の場合、<BR>
     * 検索条件データコンテナインスタンスに徴収率条件を追加する。 <BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.徴収率<BR>
     * <BR>
     * １０） 対象期間From条件追加 <BR>
     * ・パラメータ.リクエストデータ.対象日 != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに対象期間From条件を追加する。 <BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.対象日<BR>
     * <BR>
     * １１） 対象期間To条件追加  <BR>
     * ・パラメータ.リクエストデータ.対象日 != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに対象期間To条件を追加する。 <BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.対象日<BR>
     * <BR>
     * １２） 登録タイプ条件追加 <BR>
     * ・パラメータ.リクエストデータ.登録タイプ != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに登録タイプ条件を追加する。 <BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.登録タイプ<BR>
     * <BR>
     * １３）　@有効区分条件追加  <BR>
     * ・パラメータ.リクエストデータ.有効区分 != nullの場合、<BR>
     * 検索条件データコンテナインスタンスに有効区分条件を追加する。 <BR>
     * <BR>
     * [add()に指定する引数] <BR>
     * パラメータ.リクエストデータ.有効区分<BR>
     * <BR>
     * １４）　@配列を返却  <BR>
     * ・戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。<BR>
     * <BR>
     * @@param l_request -　@<BR>
     *            (リクエストデータ)<BR>
     *            管理者お客様情報手数料割引キャンペーン<BR>
     *            登録顧客照会リクエストオブジェクト<BR>
     * @@param l_strInstitutionCode - <BR>
     *            証券会社コード<BR>
     * @@return String[]
     * @@roseuid 45ADF3080191
     */
    protected Object[] createSearchConditionContainers(WEB3AdminAccInfoCampaignRegistAccListRequest l_request,
            String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME =
            ".createSearchConditionContainers(WEB3AdminAccInfoCampaignRegistAccListRequest, String)";
        log.entering(STR_METHOD_NAME);
        
        //１） 戻り値生成 
        //・戻り値編集用インスタンス（：ArrayList）を生成する。
        List l_lisArrSearchConditionList = new ArrayList();
        
        //２） 証券会社コード条件追加 
        //・検索条件データコンテナインスタンスに、証券会社コードを追加する。
        //[add()に指定する引数]
        //パラメータ.証券会社コード 
        l_lisArrSearchConditionList.add(l_strInstitutionCode);
        
        //３） 商品コード条件追加
        //・パラメータ.リクエストデータ.商品コード != nullの場合、
        //検索条件データコンテナインスタンスに商品コード条件を追加する。
        if (l_request.itemCode != null)
        {
            //[add()に指定する引数]
            //パラメータ.リクエストデータ.商品コード
            l_lisArrSearchConditionList.add(l_request.itemCode);
        }
        
        //４） キャンペーン名称条件追加
        //・パラメータ.リクエストデータ.キャンペーン名称 != nullの場合、
        //検索条件データコンテナインスタンスにキャンペーン名称条件を追加する。
        //※あいまい(前後方一致)検索
        if (l_request.campaignName != null)
        {
            //[add()に指定する引数] 
            //"%" + パラメータ.リクエストデータ.キャンペーン名称 + "%"
            l_lisArrSearchConditionList.add("%" + l_request.campaignName + "%");
        }
        
        //５） 部店コード条件追加 
        //・パラメータ.リクエストデータ.部店コード != nullの場合、
        //検索条件データコンテナインスタンスに部店コード条件を追加する。
        if (l_request.branchCode != null)
        {
            //[add()に指定する引数] 
            //パラメータ.リクエストデータ.キャンペーン検索項目.部店コード
            l_lisArrSearchConditionList.add(l_request.branchCode);
        }
        
        //６） 顧客コード条件追加 
        //・パラメータ.リクエストデータ.顧客コード != nullの場合、
        //検索条件データコンテナインスタンスに顧客コード条件を追加する。
        //※前方一致検索
        if (l_request.accountCode != null)
        {
            //[add()に指定する引数]
            //パラメータ.リクエストデータ.顧客コード + "%"
            l_lisArrSearchConditionList.add(l_request.accountCode + "%");
        }
        
        //７） 扱者コード条件追加 
        //・パラメータ.リクエストデータ.扱者コード != nullの場合、
        //検索条件データコンテナインスタンスに扱者コード条件を追加する。
        if (l_request.traderCode != null)
        {
            //[add()に指定する引数] 
            //パラメータ.リクエストデータ.扱者コード 
            l_lisArrSearchConditionList.add(l_request.traderCode);
        }
        
        //８） 口座開設区分条件追加 
        //・パラメータ.リクエストデータ.口座開設区分 != nullの場合、
        //検索条件データコンテナインスタンスに口座開設区分条件を追加する。
        if (l_request.accountOpenDiv != null)
        {
            //[add()に指定する引数]
            //パラメータ.リクエストデータ.口座開設区分
            l_lisArrSearchConditionList.add(l_request.accountOpenDiv);
        }
        
        //９） 徴収率条件追加
        //・パラメータ.リクエストデータ.徴収率 != null の場合、
        //検索条件データコンテナインスタンスに徴収率条件を追加する。
        if (l_request.collectRate != null)
        {
            //[add()に指定する引数] 
            //パラメータ.リクエストデータ.徴収率
            l_lisArrSearchConditionList.add(l_request.collectRate);
        }
        
        //１０） 対象期間From条件追加
        //・パラメータ.リクエストデータ.対象日 != nullの場合、
        //検索条件データコンテナインスタンスに対象期間From条件を追加する。
        if (l_request.targetDate != null)
        {
            //[add()に指定する引数] 
            //パラメータ.リクエストデータ.対象日
            l_lisArrSearchConditionList.add(l_request.targetDate);
        }
        
        //１１） 対象期間To条件追加 
        //・パラメータ.リクエストデータ.対象日 != nullの場合、
        //検索条件データコンテナインスタンスに対象期間To条件を追加する。
        if (l_request.targetDate != null)
        {
            //[add()に指定する引数]
            //パラメータ.リクエストデータ.対象日
            l_lisArrSearchConditionList.add(l_request.targetDate);
        }
        
        //１２） 登録タイプ条件追加 
        //・パラメータ.リクエストデータ.登録タイプ != nullの場合、
        //検索条件データコンテナインスタンスに登録タイプ条件を追加する。 
        if (l_request.registType != null)
        {
            //[add()に指定する引数]
            //パラメータ.リクエストデータ.登録タイプ
            l_lisArrSearchConditionList.add(l_request.registType);
        }
        
        //１３）　@有効区分条件追加 
        //・パラメータ.リクエストデータ.有効区分 != nullの場合、
        //検索条件データコンテナインスタンスに有効区分条件を追加する。
        if (l_request.activeDiv != null)
        {
            //[add()に指定する引数]
            //パラメータ.リクエストデータ.有効区分
            l_lisArrSearchConditionList.add(l_request.activeDiv);
        }
        
        //１４）　@配列を返却  
        //・戻り値編集用インスタンス（：ArrayList）.toArray()を返却する。
        Object[] l_strArrSearchCondition = new Object[l_lisArrSearchConditionList.size()];
        l_lisArrSearchConditionList.toArray(l_strArrSearchCondition);
        
        log.exiting(STR_METHOD_NAME);
        return l_strArrSearchCondition;
    }

    /**
     * (createソート条件文字列)<BR>
     * ソート条件文字列を編集する。<BR>
     * ・テーブル列物理名を使用し、対応するソート条件文字列（order by句）を編集する。<BR>
     * <BR>
     * １） 戻り値生成 <BR>
     * ・戻り値のソート条件文字列インスタンス（：StringBuffer）を生成する。 <BR>
     * <BR>
     * ２） パラメータ.ソートキーの要素数分以下の処理を繰り返し、ソート条件文字列を作成する。 <BR>
     * <BR>
     * ２－１）ソートキー.キー項目に対応するテーブル列物理名をソート条件文字列に追加する。 <BR>
     * 　@　@ - ソートキー.キー項目 = 部店コードの場合、<BR>
     * 手数料割引キャンペーン顧客履歴.部店コード<BR>
     * 　@　@ - ソートキー.キー項目 = 顧客コードの場合、<BR>
     * 手数料割引キャンペーン顧客履歴.顧客コード<BR>
     * 　@　@ - ソートキー.キー項目 = 扱者コードの場合、<BR>
     * 手数料割引キャンペーン顧客履歴.扱者コード<BR>
     * 　@　@ - ソートキー.キー項目 = 徴収率の場合、<BR>
     * 手数料割引キャンペーン顧客履歴.徴収率<BR>
     * 　@　@ - ソートキー.キー項目 = 対象期間Fromの場合、<BR>
     * 手数料割引キャンペーン顧客履歴.対象期間From<BR>
     * 　@　@ - ソートキー.キー項目 = 対象期間Toの場合、<BR>
     * 手数料割引キャンペーン顧客履歴.対象期間To<BR>
     * <BR>
     * ２－２）ソートキー.昇順／降順に対応するソート順序<BR>
     * (asc or desc)をソート条件文字列に追加する。 <BR>
     * <BR>
     * ３） 作成したソート条件文字列インスタンス.toString() を返却する。 <BR>
     * @@param l_sortKey - <BR>
     *            (ソートキー)<BR>
     *            ソート条件の配列<BR>
     * @@return String
     * @@roseuid 45ADF317002A
     */
    protected String createSortSearchCondition(WEB3AccInfoSortKey l_sortKey[])
    {
        final String STR_METHOD_NAME =
            ".createSortSearchCondition(WEB3AccInfoSortKey)";
        log.entering(STR_METHOD_NAME);
        
        //１） 戻り値生成
        //・戻り値のソート条件文字列インスタンス（：StringBuffer）を生成する。 
        StringBuffer l_sbSortSearchCondition = new StringBuffer();
        
        Map l_map = new HashMap();
        //ソートキー.キー項目 = 部店コード
        l_map.put(WEB3AccInfoKeyItemDef.BRANCH_CODE, CommCampaignAccHistoryParams.PTYPE + ".branch_code");
        //ソートキー.キー項目 = 顧客コード
        l_map.put(WEB3AccInfoKeyItemDef.ACCOUNT_CODE, CommCampaignAccHistoryParams.PTYPE + ".account_code");
        //ソートキー.キー項目 = 扱者コード
        l_map.put(WEB3AccInfoKeyItemDef.TRADER_CODE, CommCampaignAccHistoryParams.PTYPE + ".sonar_trader_code");
        //ソートキー.キー項目 = 徴収率
        l_map.put(WEB3AccInfoKeyItemDef.COLLECT_RATE, CommCampaignAccHistoryParams.PTYPE + ".account_charge_ratio");
        //ソートキー.キー項目 = 対象期間From
        l_map.put(WEB3AccInfoKeyItemDef.TARGETPERIOD_FROM, CommCampaignAccHistoryParams.PTYPE + ".appli_start_date");
        //ソートキー.キー項目 = 対象期間To
        l_map.put(WEB3AccInfoKeyItemDef.TARGETPERIOD_TO, CommCampaignAccHistoryParams.PTYPE + ".appli_end_date");

        String l_strAscDescDef = null;
        
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strAscDescDef = WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc) ? " ASC " : " DESC ";

            if (l_map.containsKey(l_sortKey[i].keyItem))
            {
                l_sbSortSearchCondition.append(l_map.get(l_sortKey[i].keyItem));
                l_sbSortSearchCondition.append(l_strAscDescDef);
            }
            
            if (i < (l_sortKey.length - 1))
            {
                l_sbSortSearchCondition.append(", ");
            }

        }
        
        //３） 作成したソート条件文字列インスタンス.toString() を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbSortSearchCondition.toString();
    }

    /**
     * (get照会画面)<BR>
     * 手数料割引キャンペーン顧客照会画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図 「get照会画面」 参照<BR>
     * @@param l_request -　@<BR>
     *            (リクエストデータ)<BR>
     *            管理者お客様情報手数料割引キャンペーン<BR>
     *            登録顧客照会リクエストオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignRegistAccListResponse
     * @@throws WEB3BaseException 
     * @@throws DataException 
     * @@roseuid 45B5A5D80149
     */
    public WEB3AdminAccInfoCampaignRegistAccListResponse getListScreen(
            WEB3AdminAccInfoCampaignRegistAccListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getListScreen(WEB3AdminAccInfoCampaignRegistAccListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate( )
        l_request.validate();
        
        //ログイン情報インスタンス
        WEB3Administrator l_administrator = null;
        
        //ログイン情報取得
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //validate権限チェック()
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN,
            false);
        
        //リクエストデータ.部店コード != null　@の場合、
        if (l_request.branchCode != null)
        {
            //Validate部店権限()を行う。
            l_administrator.validateBranchPermission(l_request.branchCode);
        }
        
        //証券会社コードを取得する。 
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //create検索条件文字列
        String l_strSearchCondition = this.createSearchCondition(l_request);
        
        //検検索条件データコンテナ
        Object[] l_strSearchConditionContainers = this.createSearchConditionContainers(l_request,l_strInstitutionCode);
        
        //createソート条件文字列
        String l_strSortSearchCondition = this.createSortSearchCondition(l_request.sortKeys);
        
        //get手数料割引キャンペーン顧客履歴情報
        List l_lisAccInfoCampaignActionInfo = this.getAccInfoCampaignActionInfo(
                l_strSearchCondition,l_strSearchConditionContainers,l_strSortSearchCondition,l_request.itemCode);
        
        //手数料割引キャンペーン登録顧客情報List
        List l_lisAccInfoCampaignRegistAccountInfo = new ArrayList();
        
        //createResponse( )
        WEB3AdminAccInfoCampaignRegistAccListResponse l_response = 
           (WEB3AdminAccInfoCampaignRegistAccListResponse)l_request.createResponse();
        
        //get手数料割引キャンペーン顧客履歴情報()の戻り値が、以下の時に処理を行う。
        if (l_lisAccInfoCampaignActionInfo == null
                || l_lisAccInfoCampaignActionInfo.size() == 0)
        {
            l_response.totalPages = "1";
            l_response.totalRecords = "0";
            l_response.pageIndex = "1";
            l_response.registAccountInfo = null;
        }
        else
        {
            //get手数料割引キャンペーン顧客履歴情報()の戻り値のうち、
            //表示対象行（fromIndex ～ toIndex）の間Loop処理を実施する。
            //[表示対象行（fromIndex，toIndex）の計算]
            //ページ内表示行数
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            //要求ページ番号
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            
            WEB3PageIndexInfo l_pageIndexInfo = new 
                WEB3PageIndexInfo(l_lisAccInfoCampaignActionInfo,l_intPageIndex,l_intPageSize);
            
            CommCampaignAccHistoryRow[] l_commCampaignAccHistoryRows = 
                (CommCampaignAccHistoryRow[])l_pageIndexInfo.getArrayReturned(CommCampaignAccHistoryRow.class);
            
            for (int i = 0; i < l_commCampaignAccHistoryRows.length; i++)
            {
                //手数料割引キャンペーン顧客履歴情報
                CommCampaignAccHistoryRow l_commAccHistoryRow = 
                    l_commCampaignAccHistoryRows[i];
                
                //商品コードを取得する。
                String[] l_strItemCode;
                
                l_strItemCode = this.getItemCode(l_commAccHistoryRow.getCampaignId());

                //手数料割引キャンペーン登録顧客情報
                WEB3AccInfoCampaignRegistAccountInfo l_registAccountInfo = new WEB3AccInfoCampaignRegistAccountInfo();
                
                //手数料割引キャンペーン登録顧客情報.手数料キャンペーン条件ID　@=　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].手数料割引キャンペーン条件ID
                l_registAccountInfo.campaignId = "" + l_commAccHistoryRow.getCampaignId();
                
                //手数料割引キャンペーン登録顧客情報.商品コード = get商品コード()の戻り値
                l_registAccountInfo.itemCode = l_strItemCode;
                
                //手数料割引キャンペーン登録顧客情報.キャンペーン名称 =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].手数料割引キャンペーン名称
                l_registAccountInfo.campaignName = l_commAccHistoryRow.getCommCampaignName();
                
                //手数料割引キャンペーン登録顧客情報.部店コード  =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].部店コード
                l_registAccountInfo.branchCode = l_commAccHistoryRow.getBranchCode();
                
                //手数料割引キャンペーン登録顧客情報.顧客コード =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].顧客コード(※1)
                //(※1)表示用に「get手数料割引キャンペーン顧客履歴情報()[index].顧客コード(7桁)」の1桁～6桁目をセットする。
                l_registAccountInfo.accountCode = l_commAccHistoryRow.getAccountCode().substring(0,6);
                
                //手数料割引キャンペーン登録顧客情報.顧客名 =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].顧客名称
                l_registAccountInfo.accountName = l_commAccHistoryRow.getFamilyName();
                
                //手数料割引キャンペーン登録顧客情報.扱者コード =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].扱者コード
                l_registAccountInfo.traderCode = l_commAccHistoryRow.getSonarTraderCode();
                
                //手数料割引キャンペーン登録顧客情報.口座開設区分 =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].口座開設区分
                l_registAccountInfo.accountOpenDiv = l_commAccHistoryRow.getAccOpenKindDiv();
                
                //手数料割引キャンペーン登録顧客情報.徴収率 =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].徴収率
                l_registAccountInfo.collectRate = WEB3StringTypeUtility.formatNumber(
                    l_commAccHistoryRow.getAccountChargeRatio());
                
                //手数料割引キャンペーン登録顧客情報.対象期間From =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].対象期間From
                l_registAccountInfo.targetPeriodFrom = l_commAccHistoryRow.getAppliStartDate();
                
                //手数料割引キャンペーン登録顧客情報.対象期間To =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].対象期間To
                l_registAccountInfo.targetPeriodTo = l_commAccHistoryRow.getAppliEndDate();
                
                //手数料割引キャンペーン登録顧客情報.登録タイプ =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].登録タイプ
                l_registAccountInfo.registType = l_commAccHistoryRow.getRegistType();
                
                //手数料割引キャンペーン登録顧客情報.有効区分 =　@
                //      get手数料割引キャンペーン顧客履歴情報()[index].有効区分
                l_registAccountInfo.activeDiv = l_commAccHistoryRow.getValidDiv();
                
                //ArrayListに「手数料割引キャンペーン登録顧客情報」を追加する。 
                l_lisAccInfoCampaignRegistAccountInfo.add(l_registAccountInfo);
            }
            
            //レスポンスデータ.総ページ数 = 総レコード数 / リクエストデータ.ページ内表示行数 (※1)
            //(※1)計算結果は小数点以下1位を切り上げた整数値とする。
            l_response.totalPages = Integer.toString(l_pageIndexInfo.getTotalPages());

            //レスポンスデータ.総レコード数 = get手数料割引キャンペーン登録顧客情報().size()
            l_response.totalRecords = Integer.toString(l_pageIndexInfo.getTotalRecords());
            
            //レスポンスデータ.表示ページ番号 = toIndex / リクエストデータ.ページ内表示行数 (※1)
            //(※1)計算結果は小数点以下1位を切り上げた整数値とする。
            l_response.pageIndex = Integer.toString(l_pageIndexInfo.getPageIndex());
            
            
            WEB3AccInfoCampaignRegistAccountInfo[] l_accInfoCampaignRegistAccountInfo = 
                new WEB3AccInfoCampaignRegistAccountInfo[l_lisAccInfoCampaignRegistAccountInfo.size()]; 
            
            l_lisAccInfoCampaignRegistAccountInfo.toArray(l_accInfoCampaignRegistAccountInfo);
            
            //レスポンスデータ.手数料割引キャンペーン登録顧客情報 = toArray()の戻り値
            l_response.registAccountInfo = l_accInfoCampaignRegistAccountInfo;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get手数料割引キャンペーン顧客履歴情報)<BR>
     * 条件に該当する「手数料割引キャンペーン顧客履歴オブジェクト」のListを取得する。 <BR>
     * <BR>
     * １） QueryProcessor.doFindAllQuery( )により、<BR>
     * 「手数料割引キャンペーン顧客履歴オブジェクト」のListを取得する。 <BR>
     * <BR>
     * [doFindAllQuery()に指定する引数] <BR>
     * rowType：　@手数料割引キャンペーン顧客履歴RowType <BR>
     * where：　@パラメータ.検索条件文字列<BR>
     * orderBy：　@パラメータ.ソート条件文字列<BR>
     * conditions： null <BR>
     * bindVars：　@パラメータ.検索条件データコンテナ<BR>
     * subQueryRowTypes：　@手数料割引商品マスタRowType(*1) <BR>
     * <BR>
     * (*1)<BR>
     * パラメータ.商品コードの有無により、使用するQueryProcessor.doFindAllQuery( )を変更する <BR>
     *  ・ パラメータ.商品コード != null の場合には、副問合せを使用している為、"subQueryRowTypes"を設定する。<BR>
     *   　@　@　@⇒ doFindAllQuery( RowType rowType, String where, String orderBy, <BR>
     *    　@                                String conditions, Object[] bindVars, RowType[] subQueryRowTypes ) <BR>
     *  ・ パラメータ.商品コード = null の場合には、設定しない。<BR>
     *  　@     ⇒ doFindAllQuery( RowType rowType, String where, String orderBy, <BR>
     *                                      String conditions, Object[] bindVars ) <BR>
     * ２） 検索結果の行オブジェクトで<BR>
     * 「手数料割引キャンペーン顧客履歴オブジェクト」を生成し、Listで返却する。 <BR>
     * @@param l_strSearchCondition -　@<BR>
     *            検索条件文字列<BR>
     * @@param l_strSearchConditionContainers -　@<BR>
     *            検索条件データコンテナ<BR>
     * @@param l_sortCondition -　@<BR>
     *            ソート条件文字列<BR>
     * @@param l_strItemCode -　@<BR>
     *            商品コード <BR>
     * @@return List
     * @@roseuid 45B5B270038B
     */
    protected List getAccInfoCampaignActionInfo(String l_strSearchCondition, Object[] l_strSearchConditionContainers,
        String l_sortCondition, String l_strItemCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getAccInfoCampaignActionInfo(String,String[],String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisAccInfoCampaignActionInfoList = new ArrayList();  
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            RowType[] l_rowType = {CommCampaignProductMstRow.TYPE};
            if (l_strItemCode != null)
            {
                //パラメータ.商品コード != null の場合には、副問合せを使用している為、"subQueryRowTypes"を設定する
                l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                    l_strSearchCondition,
                    l_sortCondition,
                    null,
                    l_strSearchConditionContainers,
                    l_rowType);
            }
            else
            {
                //・ パラメータ.商品コード = null の場合には、設定しない。
                l_lisAccInfoCampaignActionInfoList = l_qp.doFindAllQuery(CommCampaignAccHistoryRow.TYPE,
                    l_strSearchCondition,
                    l_sortCondition,
                    null,
                    l_strSearchConditionContainers);
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAccInfoCampaignActionInfoList;
    }

    /**
     * (get商品コード)<BR>
     * 手数料割引キャンペーン条件IDに紐付く、商品コードを全て取得する。<BR>
     * <BR>
     * １） 『手数料割引キャンペーン商品マスタ』を以下の条件で検索<BR>
     * （※レコードが取得出来ない場合は例外とする） <BR>
     * <BR>
     * [検索条件] <BR> 
     * ・ 手数料割引キャンペーン条件ID = パラメータ.手数料割引キャンペーン条件ID<BR>
     * <BR>
     * [ソート条件] <BR> 
     * ・ 商品コード（昇順）<BR>
     * <BR>
     * ２） 戻り値（商品コードの配列）を作成<BR>
     * <BR> ・ 「商品コード配列」を生成する。<BR> 
     * ・ １）の検索結果を「商品コード配列」に追加する。<BR>
     * （※複数件取得出来た場合は、件数分処理を行う）<BR>
     * <BR>
     * 商品コード配列 = １）の検索結果.商品コード<BR>
     * <BR>
     * ３） 「商品コード配列」を返却する。<BR>
     * @@param l_lngCampaignId -　@<BR>
     *            手数料割引キャンペーン条件ID<BR>
     * @@return String[]
     * @@throws WEB3BaseException 
     * @@throws DataNetworkException 
     * @@throws DataQueryException 
     * @@roseuid 45B6DC8703D0
     */
    protected String[] getItemCode(long l_lngCampaignId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getItemCode(long)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisCommCampaignProduct = new ArrayList();  
        
        // 抽出条件文字列の生成
        StringBuffer l_sbWhere = new StringBuffer();
        
        l_sbWhere.append(" campaign_id = ? ");
        
        // 商品コード昇順指定
        String l_strSort = "comm_product_code asc";
        
        // 抽出条件コンテナの生成
        Object[] l_objWhere =
            {
                new Long(l_lngCampaignId)
            };
        
        try
        {
            QueryProcessor l_qp = Processors.getDefaultProcessor();

            // キューテーブルを検索する。 
            l_lisCommCampaignProduct = l_qp.doFindAllQuery(CommCampaignProductMstRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisCommCampaignProduct == null 
            || l_lisCommCampaignProduct.size() == 0)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,  
                this.getClass().getName()  + STR_METHOD_NAME);
        }

        //戻り値（商品コードの配列）を作成
        List l_lisCommProductCode = new ArrayList();  
        
        for (int i = 0;i < l_lisCommCampaignProduct.size(); i++)
        {
            CommCampaignProductMstParams l_commCampaignProductRow = 
                (CommCampaignProductMstParams)l_lisCommCampaignProduct.get(i);
            
            l_lisCommProductCode.add(l_commCampaignProductRow.getCommProductCode());
        }
        
        String[] l_strProductCode = new String[l_lisCommProductCode.size()];
        l_lisCommProductCode.toArray(l_strProductCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_strProductCode;
    }

}
@
