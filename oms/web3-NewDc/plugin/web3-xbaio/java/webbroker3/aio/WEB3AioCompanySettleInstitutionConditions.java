head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCompanySettleInstitutionConditions.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 会社別決済機@関条件(WEB3AioCompanySettleInstitutionConditions)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 于美麗 (中訊) 新規作成
                   2004/10/22 韋念瓊 (中訊) レビュー 
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.aio.data.CompBankConditionDao;
import webbroker3.aio.data.CompBankConditionParams;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (会社別決済機@関条件)<BR>
 * 会社別決済機@関条件クラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AioCompanySettleInstitutionConditions
    implements BusinessObject
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCompanySettleInstitutionConditions.class);

    /**
     * (会社別決済機@関条件Row)<BR>
     * 会社別決済機@関条件行オブジェクト
     */
    private CompBankConditionParams compBankConditionParams;

    /**
     * (会社別決済機@関条件)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数をキーに行オブジェクトを取得し、インスタンスを生成する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_strBranchCode - (部店コード)
     * @@param l_strPaySchemeId - (決済機@関ID)
     * @@return WEB3AioCompanySettleInstitutionConditions
     * @@throws WEB3BaseException
     * @@roseuid 40E28C370121
     */
    public WEB3AioCompanySettleInstitutionConditions(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strPaySchemeId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "WEB3AioCompanySettleInstitutionConditions("
                + "String l_strInstitutionCode, "
                + "String l_strBranchCode, "
                + "String l_strPaySchemeId)";
        log.entering(STR_METHOD_NAME);

        try
        {
            CompBankConditionRow l_compBankConditionRow =
                CompBankConditionDao.findRowByInstitutionCodeBranchCodePaySchemeId(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strPaySchemeId);
                    
            if (l_compBankConditionRow != null)
            {
                this.compBankConditionParams =
                    (CompBankConditionParams) l_compBankConditionRow;
            }
            else
            {
                log.debug("テーブルに該当するデータがありません ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
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
     * (get上限入金金額（1日当たり）)<BR>
     * 1日当たりの上限入金金額を取得する。
     * @@return double
     * @@roseuid 40E28C450392
     */
    public double getMaxAmountDaily()
    {
        return this.compBankConditionParams.getMaxAmountDaily();
    }

    /**
     * (get上限入金金額（1回当たり）)<BR>
     * 1回当たりの上限入金金額を取得する。
     * @@return double
     * @@roseuid 40E28C6501CD
     */
    public double getMaxAmountOnce()
    {
        return this.compBankConditionParams.getMaxAmountOnce();
    }

    /**
     * (get下限入金金額（1回当たり）)<BR>
     * 1回当たりの下限入金金額を取得する。
     * @@return double
     * @@roseuid 40E28C7C0373
     */
    public double getMinAmountOnce()
    {
        return this.compBankConditionParams.getMinAmountOnce();
    }

    /**
     * (get単位入金金額)<BR>
     * 単位入金金額を取得する。
     * @@return double
     * @@roseuid 40E28C8801AE
     */
    public double getAmountUnit()
    {
        return this.compBankConditionParams.getAmountUnit();
    }

    /**
     * (get上限回数（1日当たり）)<BR>
     * 1日当たりの入金上限回数を取得する。
     * @@return long
     * @@roseuid 40E28C940111
     */
    public long getMaxCount()
    {
        return this.compBankConditionParams.getMaxCount();
    }

    /**
     * (get加盟店ID)<BR>
     * 加盟店IDを取得する。
     * @@return String
     * @@roseuid 4118A0C0037D
     */
    public String getShopId()
    {
        return this.compBankConditionParams.getShopId();
    }

    /**
     * (getアクセスキー)<BR>
     * アクセスキーを取得する。
     * @@return String
     * @@roseuid 4118A2B30002
     */
    public String getAccessKey()
    {
        return this.compBankConditionParams.getAccessKey();
    }

    /**
     * (get締切時間)<BR>
     * 締切時間を取得する。
     * @@return Date
     * @@roseuid 4125EBFA0130
     */
    public String getLimitTime()
    {
        return this.compBankConditionParams.getLimitTime();
    }

    /**
     * @@return Object
     * @@roseuid 415A72A40266
     */
    public Object getDataSourceObject()
    {
        return this.compBankConditionParams;
    }
}
@
