head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinInstitutionBank.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 金融機@関（銀行）マスタ(WEB3AccOpenFinInstitutionBank)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountopen;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.FinInstitutionBankParams;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.util.WEB3LogUtility;


/**
 * (金融機@関（銀行）マスタ)<BR>
 * 金融機@関（銀行）マスタ<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenFinInstitutionBank implements BusinessObject
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenFinInstitutionBank.class);

    /**
     * (金融機@関（銀行）マスタ行)<BR>
     * 金融機@関（銀行）マスタ行<BR>
     * <BR>
     * ※ 金融機@関（銀行）マスタParamsクラスはDDLより自動生成する。<BR>
     */
    private FinInstitutionBankParams finInstitutionBankParams;

    /**
     * (金融機@関（銀行）マスタ)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@オブジェクト生成<BR>
     * 　@金融機@関（銀行）マスタオブジェクトを生成する。<BR>
     * <BR>
     * ２）　@金融機@関（銀行）マスタ行プロパティのセット<BR>
     * 　@金融機@関（銀行）マスタ行を生成したオブジェクトのプロパティにセットし、<BR>
     * 返却する。<BR>
     * <BR>
     * ※ 金融機@関（銀行）マスタParamsクラスはDDLより自動生成する。<BR>
     * @@param l_finInstitutionBankParams - 金融機@関（銀行）マスタ行オブジェクト<BR>
     * <BR>
     * ※ 金融機@関（銀行）マスタParamsクラスはDDLより自動生成する。<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenFinInstitutionBank
     * @@roseuid 41A172A402E1
     */
    public WEB3AccOpenFinInstitutionBank(FinInstitutionBankParams l_finInstitutionBankParams)
    {
        this.finInstitutionBankParams = l_finInstitutionBankParams;
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.金融機@関（銀行）マスタ行を返却する。 <BR>
     * @@return Object
     * @@roseuid 418722010245
     */
    public Object getDataSourceObject()
    {
        return this.finInstitutionBankParams;
    }

    /**
     * (get銀行名)<BR>
     * 銀行名を取得する。<BR>
     * <BR>
     * this.金融機@関（銀行）マスタ行.銀行名（漢字）を返却する。<BR>
     * @@return String
     * @@roseuid 41A171E600FC
     */
    public String getFinInstitutionName()
    {
        return this.finInstitutionBankParams.getFinInstitutionName();
    }

    /**
     * (get銀行名（カナ）)<BR>
     * 銀行名（カナ）を取得する。<BR>
     * <BR>
     * this.金融機@関（銀行）マスタ行.銀行名（カナ）を返却する。<BR>
     * @@return String
     * @@roseuid 41A1721803DB
     */
    public String getFinInstitutionNameKana()
    {
        return this.finInstitutionBankParams.getFinInstitutionNameKana();
    }

    /**
     * (get銀行コード)<BR>
     * 銀行コードを取得する。<BR>
     * <BR>
     * this.金融機@関（銀行）マスタ行.銀行コードを返却する。<BR>
     * @@return String
     * @@roseuid 41A1724703BB
     */
    public String getFinInstitutionCode()
    {
        return this.finInstitutionBankParams.getFinInstitutionCode();
    }

    /**
     * (get支店名)<BR>
     * 支店名を取得する。<BR>
     * <BR>
     * this.金融機@関（銀行）マスタ行.支店名（漢字）を返却する。<BR>
     * @@return String
     * @@roseuid 41A1722A02E1
     */
    public String getFinBranchName()
    {
        return this.finInstitutionBankParams.getFinBranchName();
    }

    /**
     * (get支店名（カナ）)<BR>
     * 支店名（カナ）を取得する。<BR>
     * <BR>
     * this.金融機@関（銀行）マスタ行.支店名（カナ）を返却する。<BR>
     * @@return String
     * @@roseuid 41A1722A0300
     */
    public String getFinBranchNameKana()
    {
        return this.finInstitutionBankParams.getFinBranchNameKana();
    }

    /**
     * (get支店コード)<BR>
     * 支店コードを取得する。<BR>
     * <BR>
     * this.金融機@関（銀行）マスタ行.支店コードを返却する。<BR>
     * @@return String
     * @@roseuid 41A17259007F
     */
    public String getFinBranchCode()
    {
        return this.finInstitutionBankParams.getFinBranchCode();
    }

    /**
     * (get銀行)<BR>
     * （static メソッド）<BR>
     * キーワードに該当する金融機@関（銀行）マスタオブジェクトを配列にて取得する。<BR>
     * <BR>
     * 以下の条件で、金融機@関（銀行）マスタテーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@金融機@関（銀行）マスタ.銀行名（カナ） like 銀行名キーワード + "%"<BR>
     * 　@※（銀行名キーワード == null）の場合は条件なし。<BR>
     * <BR>
     * 　@[取得順]<BR>
     * 　@銀行コード 昇順（：asc）<BR>
     * <BR>
     * 取得した行オブジェクトのうち、銀行名(漢字)が重複しているものを削除する<BR>
     * （銀行名(漢字)がUniqueになるように）。 <BR>
     * 重複を削除した結果の各行オブジェクトについて、金融機@関（銀行）マスタ<BR>
     * オブジェクトを生成し、配列で返却する。<BR>
     * @@param l_strFinInstitutionNameKeyword - 銀行名キーワード
     * @@return webbroker3.accountopen.WEB3AccOpenFinInstitutionBank[]
     * @@roseuid 418721F7017A
     */
    public static WEB3AccOpenFinInstitutionBank[] getFinInstitution(String l_strFinInstitutionNameKeyword)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinInstitution(String)";
        log.entering(STR_METHOD_NAME);

        //以下の条件で、金融機@関（銀行）マスタテーブルを検索する。
        //　@[条件]
        //金融機@関（銀行）マスタ.銀行名（カナ） like 銀行名キーワード + "%"
        //※（銀行名キーワード == null）の場合は条件なし。
        //[取得順]
        //銀行コード 昇順（：asc）

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strQueryString = null;
            Object[] l_queryDataContainer = null;

            if (l_strFinInstitutionNameKeyword != null && !"".equals(l_strFinInstitutionNameKeyword))
            {
                l_strQueryString = "fin_institution_name_kana like ?";
                l_queryDataContainer = new Object[]{l_strFinInstitutionNameKeyword + "%" };
            }

            String l_strOrderBy = "fin_institution_code asc";

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE,
                l_strQueryString,
                l_strOrderBy,
                null,
                l_queryDataContainer
            );

        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        int l_intSize = l_lisRecords.size();

        //取得した行オブジェクトのうち、銀行名(漢字)が重複しているものを削除する
        //（銀行名(漢字)がUniqueになるように）。 
        Map l_uniqueMap = new Hashtable();
        List l_lisFinInstitutionBanks = new Vector();

        for (int i = 0; i < l_intSize; i++)
        {
            FinInstitutionBankParams l_finInstitutionBankParams = (FinInstitutionBankParams)l_lisRecords.get(i);
            String l_strFinInstitutionName = l_finInstitutionBankParams.getFinInstitutionName();
            if (l_uniqueMap.containsKey(l_strFinInstitutionName))
            {
                continue;
            }

            //重複を削除した結果の各行オブジェクトについて、
            //金融機@関（銀行）マスタオブジェクトを生成し、配列で返却する。
            l_lisFinInstitutionBanks.add(new WEB3AccOpenFinInstitutionBank(l_finInstitutionBankParams));
            l_uniqueMap.put(l_strFinInstitutionName, "1");
        }

        WEB3AccOpenFinInstitutionBank[] l_finInstitutionBanks =
            new WEB3AccOpenFinInstitutionBank[l_lisFinInstitutionBanks.size()];
        l_lisFinInstitutionBanks.toArray(l_finInstitutionBanks);

        log.exiting(STR_METHOD_NAME);

        return l_finInstitutionBanks;
    }

    /**
     * (get支店)<BR>
     * （static メソッド）<BR>
     * 指定銀行，キーワードに該当する金融機@関（銀行）マスタオブジェクトを<BR>
     * 配列にて取得する。<BR>
     * <BR>
     * 以下の条件で、金融機@関（銀行）マスタテーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@金融機@関（銀行）マスタ.銀行名（漢字） = 銀行名（漢字） And <BR>
     * 　@金融機@関（銀行）マスタ.支店名（カナ） like 支店名キーワード + "%"<BR>
     * 　@※（銀行名キーワード == null）の場合は条件なし。<BR>
     * <BR>
     * 　@[取得順]<BR>
     * 　@支店名（カナ） 昇順（：asc）<BR>
     * <BR>
     * 取得した行オブジェクトを指定し、金融機@関（銀行）マスタオブジェクトを生成し、<BR>
     * 配列で返却する。<BR>
     * @@param l_strFinInstitutionName - 銀行名（漢字）
     * @@param l_strFinBranchNameKeyword - 支店名キーワード
     * @@return webbroker3.accountopen.WEB3AccOpenFinInstitutionBank[]
     * @@roseuid 41A1737F03AC
     */
    public static WEB3AccOpenFinInstitutionBank[] getFinBranch(String l_strFinInstitutionName, String l_strFinBranchNameKeyword)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getFinBranch(String, String)";
        log.entering(STR_METHOD_NAME);

        //以下の条件で、金融機@関（銀行）マスタテーブルを検索する。
        //　@[条件]
        //金融機@関（銀行）マスタ.銀行名（漢字） = 銀行名（漢字） And
        //金融機@関（銀行）マスタ.支店名（カナ） like 支店名キーワード + "%"
        //※（銀行名キーワード == null）の場合は条件なし。
        //[取得順]
        //支店名（カナ） 昇順（：asc）

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_strQueryString = "fin_institution_name = ?";
            Object[] l_queryDataContainer = null;

            if (l_strFinBranchNameKeyword != null && !"".equals(l_strFinBranchNameKeyword))
            {
                l_strQueryString += " and fin_branch_name_kana like ?";
                l_queryDataContainer = new Object[]{l_strFinInstitutionName, l_strFinBranchNameKeyword + "%"};
            }
            else
            {
                l_queryDataContainer = new Object[]{l_strFinInstitutionName};
            }

            String l_strOrderBy = "fin_branch_name_kana asc";

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FinInstitutionBankRow.TYPE,
                l_strQueryString,
                l_strOrderBy,
                null,
                l_queryDataContainer
            );
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        int l_intSize = l_lisRecords.size();

        //取得した行オブジェクトを指定し、金融機@関（銀行）マスタオブジェクトを生成し、配列で返却する。
        WEB3AccOpenFinInstitutionBank[] l_finInstitutionBanks =
            new WEB3AccOpenFinInstitutionBank[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            FinInstitutionBankParams l_finInstitutionBankParams = (FinInstitutionBankParams)l_lisRecords.get(i);
            l_finInstitutionBanks[i] = new WEB3AccOpenFinInstitutionBank(l_finInstitutionBankParams);
        }

        log.exiting(STR_METHOD_NAME);

        return l_finInstitutionBanks;
    }
}
@
