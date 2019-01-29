head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.36.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAccountCodeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設顧客コード採番サービスImpl(WEB3AccOpenAccountCodeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/15 徐宏偉 (中訊) 新規作成
                   2006/10/18 徐宏偉 (中訊) （ＤＢ更新仕様）の008修正
                   2006/12/25 鈴木 (SCS) （モデル）119修正
                   2007/01/12 岡安 (SCS) （モデル）120修正
                   2007/01/26 岡崎 (SCS) （モデル）121修正
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import webbroker3.gentrade.WEB3GentradeCheckDigitalUtility;
import webbroker3.accountopen.data.AccOpenAccountCodeDao;
import webbroker3.accountopen.data.AccOpenAccountCodeParams;
import webbroker3.accountopen.data.AccOpenAccountCodeRow;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.service.delegate.WEB3AccOpenAccountCodeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設顧客コード採番サービスImpl)<BR>
 * 口座開設顧客コード採番サービス実装クラス<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenAccountCodeServiceImpl implements WEB3AccOpenAccountCodeService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenAccountCodeServiceImpl.class);


    /**
     * (get新規顧客コード)<BR>
     * 口座開設見込客の顧客コードを自動採番する。<BR>
     * <BR>
     * １）顧客コードを取得する。<BR>
     * <BR>
     * １－１）　@口座開設顧客コードテーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@口座開設顧客コード.証券会社コード = 引数.証券会社コード<BR>
     * 　@　@口座開設顧客コード.部店コード = 引数.部店コード<BR>
     * 　@　@口座開設顧客コード.顧客区分 = 引数.顧客区分<BR>
     * <BR>
     * 　@－該当データがない場合は、以下の例外をスローする。<BR>
     * 　@　@「自動採番のデータが取得できませんでした。」<BR>
     * <BR>
     * １－２）　@顧客コード現在値を取得する。<BR>
     * <BR>
     * 　@顧客コード現在値 = 口座開設顧客コード.顧客コード現在値<BR>
     * <BR>
     * １－３）　@顧客コード最大値を取得する。<BR>
     * <BR>
     * 　@顧客コード最大値 = 口座開設顧客コード.顧客コード最大値<BR>
     * <BR>
     * １－４）　@新規顧客コードを採番する。<BR>
     * <BR>
     * 　@新規顧客コード = 顧客コード現在値 + 1<BR>
     * <BR>
     * １－５）　@新規顧客コードが最大値を超えていないかチェックする。<BR>
     * 　@新規顧客コード > 口座開設顧客コード.顧客コード最大値　@の場合、以下の例外をスローする。<BR>
     * 　@　@「最大値を超えたため、自動採番できませんでした。<BR>
     * <BR>
     * ２）口座開設見込客データを取得する。<BR>
     * <BR>
     * ２－１）　@口座開設見込客を以下の条件で検索する<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@口座開設見込客.証券会社コード = 引数.証券会社コード<BR>
     * 　@　@口座開設見込客.部店コード = 引数.部店コード<BR>
     * 　@　@口座開設見込客.口座コード => 新規顧客コード<BR>
     * 　@　@口座開設見込客.口座コード <= 口座開設顧客コード.顧客コード最大値<BR>
     * <BR>
     * 　@　@[並べ替え]<BR>
     * 　@　@口座開設見込客．口座コードの昇順<BR>
     * <BR>
     * 　@　@取得結果を、口座開設見込客リストにセットする。<BR>
     * <BR>
     * ２－２）　@口座開設見込客データ件数を取得する。<BR>
     * <BR>
     * ３）顧客マスタデータを取得する<BR>
     * <BR>
     * ３－１）　@新規顧客コードのチェックデジットを取得し(*1)、7桁の新規顧客コードを設定する。<BR>
     * <BR>
     * ３－２）　@顧客コード最大値のチェックデジットを取得し(*1)、7桁の顧客コード最大値を設定する。<BR>
     * <BR>
     * ３－３）　@顧客マスタテーブルを以下の条件で検索する<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@顧客マスタ.証券会社コード = 引数.証券会社コード<BR>
     * 　@　@　@顧客マスタ.部店コード = 引数.部店コード<BR>
     * 　@　@　@顧客マスタ.口座コード => 7桁の新規顧客コード<BR>
     * 　@　@　@顧客マスタ.口座コード <= 7桁の顧客コード最大値<BR>
     * <BR>
     * 　@　@　@[並べ替え]<BR>
     * 　@　@　@顧客マスタ．口座コードの昇順<BR>
     * <BR>
     * 　@　@取得結果を、顧客マスタリストにセットする。<BR>
     * <BR>
     * ３－４）　@顧客マスタデータ件数を取得する<BR>
     * <BR>
     * ４）新規顧客コードを検索する<BR>
     * <BR>
     * ４－１）　@新規顧客コードが最大値を超えていないかチェックする。<BR>
     * －新規顧客コード > 顧客コード最大値　@の場合、以下の例外をスローする。<BR>
     * 　@　@「最大値を超えたため、自動採番できませんでした。<BR>
     * <BR>
     * ４－２）　@新規顧客コードが口座開設見込客データに存在するかをチェックする。<BR>
     * <BR>
     * ４－２－１）　@口座開設見込客データ件数が0件の場合<BR>
     * <BR>
     * 　@　@口座開設見込客データ = "0"<BR>
     * <BR>
     * ４－２－２）　@口座開設見込客データ件数が存在する場合<BR>
     * <BR>
     * 　@　@口座開設見込客データ = 口座開設見込客リスト m 番目.get顧客コード<BR>
     * <BR>
     * ４－２－３）　@新規顧客コード = 口座開設見込客データの場合、以下の値をセットする。<BR>
     * <BR>
     * 　@　@新規顧客コード = 新規顧客コード + 1<BR>
     * 　@　@口座開設見込客リスト m 番目 = 口座開設見込客リスト m + 1 番目<BR>
     * 　@　@口座開設見込客重複チェックフラグ = false<BR>
     * <BR>
     * ４－３）　@口座開設見込客重複チェックフラグ = true あるいは、<BR>
     * 口座開設見込客リスト m 番目が口座開設見込客データ件数より大きい場合、<BR>
     * 新規顧客コードが顧客マスタに存在するかチェックする。<BR>
     * <BR>
     * 　@　@以下の条件に該当する場合、４－３）の処理を繰り返す。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@顧客マスタリスト n 番目が顧客マスタデータ件数よりも小さい　@かつ、<BR>
     * 　@　@　@顧客マスタ重複チェックフラグ = true　@かつ、<BR>
     * 　@　@　@顧客マスタ顧客コード大小チェックフラグ = true<BR>
     * <BR>
     * ４－３－１）　@新規顧客コードのチェックデジットを取得し(*1)、7桁の新規顧客コードを設定する。<BR>
     * <BR>
     * ４－３－２）　@顧客マスタデータ件数が0件の場合<BR>
     * <BR>
     * 　@　@顧客マスタデータ = "0"<BR>
     * <BR>
     * ４－３－３）　@顧客マスタデータ件数が存在する場合<BR>
     * <BR>
     * 　@　@顧客マスタデータ = 顧客マスタリスト n 番目.get顧客コード<BR>
     * ４－３－４）　@新規顧客コード < 顧客マスタデータ の場合、以下の値をセットする。<BR>
     * 　@　@　@顧客マスタ顧客コード大小チェックフラグ = false<BR>
     * <BR>
     * ４－３－５）　@新規顧客コード = 顧客マスタデータ の場合、以下の値をセットする。<BR>
     * 　@　@　@新規顧客コード = 新規顧客コード + 1<BR>
     * 　@　@　@顧客マスタ重複チェックフラグ = false<BR>
     * <BR>
     * ４－３－６）　@顧客マスタリスト n 番目の設定<BR>
     * 　@　@　@顧客マスタリスト n 番目 = 顧客マスタリスト n 番目 + 1<BR>
     * <BR>
     * 　@　@以下の条件に該当する場合、４）の処理を繰り返す。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@(口座開設見込客重複チェックフラグ = false あるいは、顧客マスタ重複チェックフラグ = false)　@かつ、<BR>
     * 　@　@　@顧客マスタ顧客コード大小チェックフラグ = true<BR>
     * <BR>
     * ５）　@口座開設顧客コードテーブルを更新する<BR>
     * <BR>
     * ５－１）　@４）で取得したデータを更新対象とする<BR>
     * 　@口座開設顧客コード.顧客コード現在値には、新規顧客コードをセットする<BR>
     * 　@更新内容は、DB更新仕様「口座開設顧客コードテーブル_更新仕様.xls」を参照<BR>
     * <BR>
     * ６）新規顧客コードを返却する。<BR>
     * <BR>
     * (*1)チェックデジット取得メソッド<BR>
     * <BR>
     * getチェックデジット（口座コード：String）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strAccountDiv - (顧客区分)<BR>
     * 値は、DBレイアウト「口座開設顧客コード」を参照。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getNewAccountCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getNewAccountCode(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            int l_no1 = 0;
            int l_no2 = 0;
            int l_lstDataSize1 = 0;
            int l_lstDataSize2 = 0;
            boolean l_blnNoLength1 = true;
            boolean l_blnNoLength2 = true;
            boolean l_blnNoLength3 = true;
            String l_strNewAccountCode = null;
            String l_strMaxAccountCode = null;
            String l_lstData1 = null;
            String l_lstData2 = null;
            String l_strMainCheckDigital = null;
            String l_strMainAccountCodeAddCheckDigital = null;

            QueryProcessor l_processer = Processors.getDefaultProcessor();

            // １）口座開設見込客の顧客コードを自動採番する。
            // １－１）　@顧客コードレコードを取得する。
            // 　@口座開設顧客コードテーブルを以下の条件で検索する。
            // 　@　@[条件]
            // 　@　@口座開設顧客コード.証券会社コード = 引数.証券会社コード
            // 　@　@口座開設顧客コード.部店コード = 引数.部店コード
            // 　@　@口座開設顧客コード.顧客区分 = 引数.顧客区分
            StringBuffer l_sbWhereNewAccountCode = new StringBuffer();
            l_sbWhereNewAccountCode.append(" institution_code = ? ");
            l_sbWhereNewAccountCode.append(" and branch_code = ? ");
            l_sbWhereNewAccountCode.append(" and account_div = ? ");

            Object[] l_objWhereValuesNewAccountCode = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountDiv};

            List l_lisAccOpenAccountCodeRows = new ArrayList();
            l_lisAccOpenAccountCodeRows = l_processer.doFindAllQuery(
                AccOpenAccountCodeRow.TYPE,
                l_sbWhereNewAccountCode.toString(),
                "for update",
                l_objWhereValuesNewAccountCode);

            // 　@－該当データがない場合は、以下の例外をスローする。
            // 　@　@「自動採番のデータが取得できませんでした。」
            if (l_lisAccOpenAccountCodeRows == null || l_lisAccOpenAccountCodeRows.size() == 0)
            {
                log.debug("自動採番のデータが取得できませんでした。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02608,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "自動採番のデータが取得できませんでした。");
            }

            AccOpenAccountCodeRow l_row = (AccOpenAccountCodeRow) l_lisAccOpenAccountCodeRows.get(0);

            // １－２）　@顧客コード現在値を取得する。
            // 　@顧客コード現在値 = 口座開設顧客コード.顧客コード現在値
            AccOpenAccountCodeParams l_params = new AccOpenAccountCodeParams(l_row);
            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
                Double.parseDouble(l_row.getLastAccountCode()));
            // １－３）　@顧客コード最大値を取得する。
            // 　@顧客コード最大値 = 口座開設顧客コード.顧客コード最大値
            l_strMaxAccountCode = l_row.getMaxAccountCode();
            // １－４）　@新規顧客コードを採番する。
            // 　@新規顧客コード = 顧客コード現在値 + 1
            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
                Double.parseDouble(l_strNewAccountCode) + 1);
            // １－５）　@新規顧客コードが最大値を超えていないかチェックする。
            // 　@新規顧客コード > 口座開設顧客コード.顧客コード最大値　@の場合、以下の例外をスローする。
            // 　@「最大値を超えたため、自動採番できませんでした。
            if (Double.parseDouble(l_strNewAccountCode) > Double.parseDouble(l_strMaxAccountCode))
            {
                log.debug("最大値を超えたため、自動採番できませんでした。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02609,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "最大値を超えたため、自動採番できませんでした。");
            }

            // ２）口座開設見込客データを取得する。
            // ２－１）　@口座開設見込客を以下の条件で検索する。
            // 　@[条件]
            // 　@口座開設見込客.証券会社コード = 引数.証券会社コード
            // 　@口座開設見込客.部店コード = 引数.部店コード
            // 　@口座開設見込客.口座コード => 新規顧客コード
            // 　@口座開設見込客.口座コード <= 口座開設顧客コード.顧客コード最大値
            StringBuffer l_sbWhere2 = new StringBuffer();
            l_sbWhere2.append(" institution_code = ? ");
            l_sbWhere2.append(" and branch_code = ? ");
            l_sbWhere2.append(" and account_code between ? ");
            l_sbWhere2.append(" and ? ");
            // 　@[並べ替え]
            // 　@口座開設見込客．口座コードの昇順
            StringBuffer l_strSortCond2 = new StringBuffer();
            l_strSortCond2.append("account_code asc");

            Object[] l_objWhereValue2s = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strNewAccountCode,
                l_strMaxAccountCode
                };
            // 　@取得結果を、口座開設見込客リストにセットする。
            List l_lisExpAccountOpenRows = new ArrayList();
            l_lisExpAccountOpenRows = l_processer.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_sbWhere2.toString(),
                l_strSortCond2.toString(),
                null,
                l_objWhereValue2s);
            // ２－２）　@口座開設見込客データ件数を取得する。
            l_lstDataSize2 = l_lisExpAccountOpenRows.size();

            // ３）顧客マスタデータを取得する
            // ３－１）　@新規顧客コードのチェックデジットを取得し(*1)、7桁の新規顧客コードを設定する。
            String l_strNewCheckDigital =
                WEB3GentradeCheckDigitalUtility.getCheckDigital(l_strNewAccountCode);
            String l_strNewAccountCodeAddCheckDigital = l_strNewAccountCode + l_strNewCheckDigital;
            // ３－２）　@顧客コード最大値のチェックデジットを取得し(*1)、7桁の顧客コード最大値を設定する。
            String l_strMaxCheckDigital =
                WEB3GentradeCheckDigitalUtility.getCheckDigital(l_strMaxAccountCode);
            String l_strMaxAccountCodeAddCheckDigital = l_strMaxAccountCode + l_strMaxCheckDigital;
            // ３－３）　@顧客マスタテーブルを以下の条件で検索する
            // 　@[条件]
            // 　@顧客マスタ.証券会社コード = 引数.証券会社コード
            // 　@顧客マスタ.部店コード = 引数.部店コード
            // 　@顧客マスタ.口座コード => 7桁の新規顧客コード
            // 　@顧客マスタ.口座コード <= 7桁の顧客コード最大値
            StringBuffer l_sbWhere1 = new StringBuffer();
            l_sbWhere1.append(" institution_code = ? ");
            l_sbWhere1.append(" and branch_code = ? ");
            l_sbWhere1.append(" and account_code between ? ");
            l_sbWhere1.append(" and ? ");
            // 　@[並べ替え]
            // 　@顧客マスタ．口座コードの昇順
            StringBuffer l_strSortCond1 = new StringBuffer();
            l_strSortCond1.append("account_code asc");

            Object[] l_objWhereValue1s = {
                l_strInstitutionCode,
                l_strBranchCode,
                l_strNewAccountCodeAddCheckDigital,
                l_strMaxAccountCodeAddCheckDigital};
            // 取得結果を、顧客マスタリストにセットする。
            List l_lisMainAccountRows = new ArrayList();
            l_lisMainAccountRows = l_processer.doFindAllQuery(
                MainAccountRow.TYPE,
                l_sbWhere1.toString(),
                l_strSortCond1.toString(),
                null,
                l_objWhereValue1s);
            // ３－４）　@顧客マスタデータ件数を取得する
            l_lstDataSize1 = l_lisMainAccountRows.size();

            // ４）　@新規顧客コードを検索する
            do
            {
                l_blnNoLength1 = true;
                l_blnNoLength2 = true;
                // ４－１）　@新規顧客コードが最大値を超えていないかチェックする。
                // －新規顧客コード > 顧客コード最大値　@の場合、以下の例外をスローする。
                //  最大値を超えたため、自動採番できませんでした。
                if (Double.parseDouble(l_strNewAccountCode) > Double.parseDouble(l_strMaxAccountCode))
                {
                    log.debug("最大値を超えたため、自動採番できませんでした。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02609,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "最大値を超えたため、自動採番できませんでした。");
                }
                // ４－２）　@新規顧客コードが口座開設見込客データに存在するかをチェックする。
                // ４－２－１）　@口座開設見込客データ件数が0件の場合
				if (l_lisExpAccountOpenRows == null || l_lstDataSize2 == 0)
				{
					l_lstData2 = "0";
				}
                // ４－２－２）　@口座開設見込客データ件数が存在する場合
				if (l_no2 < l_lstDataSize2)
				{
					// 口座開設見込客データ = 口座開設見込客リスト m 番目.get顧客コード
					ExpAccountOpenRow exp_row = (ExpAccountOpenRow) l_lisExpAccountOpenRows.get(l_no2);
					l_lstData2 = exp_row.getAccountCode();
				}
                // ４－２－３）　@新規顧客コード = 口座開設見込客データの場合、以下の値をセットする。
				if (Double.parseDouble(l_strNewAccountCode) == Double.parseDouble(l_lstData2))
				{
                    // 新規顧客コード = 新規顧客コード + 1
                    // 口座開設見込客リスト m 番目 = 口座開設見込客リスト m + 1 番目
                    // 口座開設見込客重複チェックフラグ = false
		            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
		                Double.parseDouble(l_strNewAccountCode) + 1);
					l_no2 = l_no2 + 1;
					l_blnNoLength2 = false;
				}
                // ４－３）　@口座開設見込客重複チェックフラグ = true あるいは、
                // 口座開設見込客リスト m 番目が口座開設見込客データ件数より大きい場合、
                // 新規顧客コードが顧客マスタに存在するかチェックする。
				if (l_blnNoLength2 || l_no2 >= l_lstDataSize2)
				{
                    // 以下の条件に該当する場合、４－３）の処理を繰り返す。
                    // [条件]
                    // 顧客マスタリスト n 番目が顧客マスタデータ件数よりも小さい　@かつ、
                    // 顧客マスタ重複チェックフラグ = true　@かつ、
                    // 顧客マスタ顧客コード大小チェックフラグ = true
					while (l_no1 < l_lstDataSize1 && l_blnNoLength1 && l_blnNoLength3)	
					{
                        // ４－３－１）　@新規顧客コードのチェックデジットを取得し(*1)、7桁の新規顧客コードを設定する。
						l_strMainCheckDigital = WEB3GentradeCheckDigitalUtility.getCheckDigital(l_strNewAccountCode);
						l_strMainAccountCodeAddCheckDigital = l_strNewAccountCode + l_strMainCheckDigital;

                        // ４－３－２）　@顧客マスタデータ件数が0件の場合
						if (l_lisMainAccountRows == null || l_lstDataSize1 == 0)
						{
							l_lstData1 = "0";
                        // ４－３－３）　@顧客マスタデータ件数が存在する場合
						} else {
                            // 顧客マスタデータ = 顧客マスタリスト n 番目.get顧客コード
							MainAccountRow main_row = (MainAccountRow) l_lisMainAccountRows.get(l_no1);
							l_lstData1 = main_row.getAccountCode();
						}
                        // ４－３－４）　@新規顧客コード < 顧客マスタデータ の場合、以下の値をセットする。
						if (Double.parseDouble(l_lstData1) > Double.parseDouble(l_strMainAccountCodeAddCheckDigital)) {
                            // 顧客マスタ顧客コード大小チェックフラグ = false
							l_blnNoLength3 = false;
						}
                        // ４－３－５）　@新規顧客コード = 顧客マスタデータ の場合、以下の値をセットする。
						if (Double.parseDouble(l_lstData1) == Double.parseDouble(l_strMainAccountCodeAddCheckDigital))
						{
                            // 新規顧客コード = 新規顧客コード + 1
                            // 顧客マスタ重複チェックフラグ = false
							l_blnNoLength1 = false;
				            l_strNewAccountCode = WEB3StringTypeUtility.formatNumber(
				                Double.parseDouble(l_strNewAccountCode) + 1);
						}
                        // ４－３－６）　@顧客マスタリスト n 番目の設定
                        // 顧客マスタリスト n 番目 = 顧客マスタリスト n 番目 + 1
						l_no1 = l_no1 + 1;
					}
				}
			}
            // 以下の条件に該当する場合、４）の処理を繰り返す。
            // [条件]
            // (口座開設見込客重複チェックフラグ = false あるいは、顧客マスタ重複チェックフラグ = false)　@かつ、
            // 顧客マスタ顧客コード大小チェックフラグ = true
			while ((!l_blnNoLength2 || !l_blnNoLength1) && l_blnNoLength3);

            // ５）　@口座開設顧客コードテーブルを更新する
            // ５－１）　@４）で取得したデータを更新対象とする
            // 　@口座開設顧客コード.顧客コード現在値には、新規顧客コードをセットする
            // 　@更新内容は、DB更新仕様「口座開設顧客コードテーブル_更新仕様.xls」を参照
            l_params.setLastAccountCode(l_strNewAccountCode);
            l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_processer.doUpdateQuery(l_params);//DataNetworkException, DataQueryException

            // ６）新規顧客コードを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_strNewAccountCode;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
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
    }
}
@
