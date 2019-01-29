head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 顧客属性クラス(WEB3TPAccountInfo.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/02 山田　@卓司(FLJ) 新規作成
 *                  2004/08/04 劉 ((FLJ)) 部分実装修正
 */
package webbroker3.tradingpower.updtpower;

import java.util.Hashtable;
import java.util.Iterator;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.define.WEB3TPDepositTypeDef;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (顧客属性) <BR>
 * 余力計算時使用する顧客属性を表現する。
 */
public class WEB3TPAccountInfo
{

    /**
     * 口座ID <BR>
     */
    private long accountId;

    /**
     * 補助口座IDリスト <BR>
     */
    private Hashtable subAccountIds;

    /**
     * 証券会社ID <BR>
     */
    private long institutionId;

    /**
     * 証券会社コード <BR>
     */
    private String institutionCode;

    /**
     * 支店ID <BR>
     */
    private long branchId;

    /**
     * 支店コード <BR>
     */
    private String branchCode;

    /**
     * 信用顧客フラグ <BR>
     */
    private boolean marginCustFlag;
    
    /**
     * 税区分
     */
    private Hashtable taxTypes;

	

    /**
     * 引数.日付時点で顧客が特定口座源泉徴収ありの場合
     * true, それ以外の場合falseを返す。
     * @@param l_datDate
     */

    
    /**
     * 顧客属性コンストラクタ
     * @@roseuid 4104E18B022D
     */
    public WEB3TPAccountInfo()
    {

    }

    /**
     * (get口座ID)<BR>
     * <BR>
     * 口座IDを取得する。<BR>
     * @@return long
     * @@roseuid 40CFED5402FC
     */
    public long getAccountId()
    {
        return accountId;
    }

    /**
     * (set口座ID)<BR>
     * <BR>
     * 口座IDをセットする。<BR>
     * @@param l_lngAccountId - 口座ID
     * @@roseuid 40CFED57030C
     */
    public void setAccountId(long l_lngAccountId)
    {
        accountId = l_lngAccountId;
    }

    /**
     * (get補助口座ID)<BR>
     * <BR>
     * 補助口座タイプキーにして補助口座IDを取得する。<BR>
     * @@param l_subAccountType- 補助口座タイプ<BR>
     * @@return long
     * @@roseuid 40CFED6300E9
     */
    public long getSubAccountId(SubAccountTypeEnum l_subAccountType)
    {

        final String STR_METHOD_NAME =
            "getSubAccountId(SubAccountTypeEnum l_subAccountType)";

        if (l_subAccountType != null && subAccountIds != null)
        {
            Iterator l_iterator = subAccountIds.keySet().iterator();
            while (l_iterator.hasNext())
            {
                Long l_key = (Long) l_iterator.next();
                SubAccountTypeEnum l_subAccountTypeEnum = (SubAccountTypeEnum)
                    subAccountIds.get(l_key);

                if (l_subAccountType.equals(
                    l_subAccountTypeEnum))
                {
                    return l_key.longValue();
                }
            }
        }
        
        throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);

    }

    /**
     * (get補助口座ID)<BR>
     * <BR>
     * 信用顧客フラグキーにして補助口座IDを取得する。<BR>
     * 信用顧客の場合、
     * EQUITY_MARGIN_SUB_ACCOUNTを返す。
     * 現物顧客の場合、
     * EQUITY_SUB_ACCOUNTを返す。<BR>
     * @@param l_bMarginCustFlag - 信用顧客フラグ<BR>
     * @@return long
     * @@roseuid 40F4BD9200C8
     */
    public long getSubAccountId(boolean l_bMarginCustFlag)
    {

        if (l_bMarginCustFlag == true)
        {

            return getSubAccountId(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);

        }
        else
        {
            return getSubAccountId(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        }
    }

    /**
     * (hold補助口座)<BR>
     * <BR>
     * 補助口座タイプキーにして補助口座保有チェック。<BR>
     * @@param l_subAccountType- 補助口座タイプ
     * @@return boolean
     * @@roseuid 412414030173
     */
    public boolean holdSubAccount(SubAccountTypeEnum l_subAccountType)
    {

        final String STR_METHOD_NAME =
            "holdSubAccount(SubAccountTypeEnum l_subAccountType)";

        if (l_subAccountType != null)
        {
            if (subAccountIds == null)
            {
                return false;
            }
            Iterator l_iterator = subAccountIds.keySet().iterator();
            while (l_iterator.hasNext())
            {
                Long l_key = (Long) l_iterator.next();
                SubAccountTypeEnum l_subAccountTypeEnum = (SubAccountTypeEnum)
                    subAccountIds.get(l_key);

                if (l_subAccountType.equals(
                    l_subAccountTypeEnum))
                {
                    return true;
                }
            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        return false;
    }

    /**
     * (get補助口座IDリスト)<BR>
     * 補助口座IDリストを返す。<BR>
     * @@return Hashtable
     * @@roseuid 4110977E01C9
     */
    public Hashtable getSubAccountIds()
    {
        return this.subAccountIds;
    }

    /**
     * (set補助口座IDリスト)<BR>
     * <BR>
     * 補助口座IDリストをセットする。<BR>
     * @@param l_subAccountIds - 補助口座IDリスト
     * @@roseuid 4100B7D50012
     */
    public void setSubAccountIds(Hashtable l_subAccountIds)
    {
        subAccountIds = l_subAccountIds;
    }

    /**
     * 引数.Keyで指定される税区分を返す。
     * 
     * @@param taxTypeKey
     * @@return
     */
    public TaxTypeEnum getTaxType(String l_strKey)
    {
    	return (TaxTypeEnum)taxTypes.get(l_strKey);    	    	
    }

    /**
     * 税区分のリストを返す。
     * 
     * @@param taxTypeKey
     * @@return
     */
    public Hashtable getTaxTypes()
    {
    	return taxTypes;    	    	
    }
    
    /**
     * 特定口座源泉徴収ありであればtrue,
     * それ以外はfalseを返す。
     * @@return
     */
    public boolean isTaxSpecialWithhold(String l_strKey)
    {
    	if(taxTypes.get(l_strKey) == null)
    	{
    		return false;    		
    	}
    	return TaxTypeEnum.SPECIAL_WITHHOLD.equals(taxTypes.get(l_strKey));    	
    }

    
    /**
     * 税区分のリストをセットする。
     * @@param l_strKey
     * @@param l_taxType
     */
    public void setTaxTypes(Hashtable l_taxTypes)
    {
    	taxTypes = l_taxTypes;    	
    }
    
    
    /**
     * (get証券会社ID)<BR>
     * <BR>
     * 証券会社IDを取得する。<BR>
     * @@return long
     * @@roseuid 4100727D026F
     */
    public long getInstitutionId()
    {
        return institutionId;
    }

    /**
     * (set証券会社ID)<BR>
     * <BR>
     * 証券会社IDをセットする。<BR>
     * @@param l_lngInstitutionId - 証券会社ID
     * @@roseuid 4100727D028E
     */
    public void setInstitutionId(long l_lngInstitutionId)
    {
        institutionId = l_lngInstitutionId;
    }

    /**
     * (get証券会社コード)<BR>
     * <BR>
     * 証券会社コードを取得する。<BR>
     * @@return String
     * @@roseuid 40B4472E0226
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * (set証券会社コード)<BR>
     * <BR>
     * 証券会社コードをセットする。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@roseuid 40B4472E0246
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        institutionCode = l_strInstitutionCode;
    }

    /**
     * (get部店ID)<BR>
     * <BR>
     * 部店IDを取得する。<BR>
     * @@return long
     * @@roseuid 410072A200E9
     */
    public long getBranchId()
    {
        return branchId;
    }

    /**
     * (set部店ID)<BR>
     * <BR>
     * 部店IDをセットする。<BR>
     * @@param l_lngBranchId - 部店ID
     * @@roseuid 410072A20108
     */
    public void setBranchId(long l_lngBranchId)
    {
        branchId = l_lngBranchId;
    }

    /**
     * (get部店コード)<BR>
     * <BR>
     * 部店コードを取得する。<BR>
     * @@return String
     * @@roseuid 40B44742039D
     */
    public String getBranchCode()
    {
        return branchCode;
    }

    /**
     * (set部店コード)<BR>
     * <BR>
     * 部店コードをセットする。<BR>
     * @@param l_strBranchCode - 部店コード
     * @@roseuid 40B4474203BD
     */
    public void setBranchCode(String l_strBranchCode)
    {
        branchCode = l_strBranchCode;
    }

    /**
     * (is信用顧客)<BR>
     * 信用顧客フラグを返す。<BR>
     * @@return boolean
     * @@roseuid 40F37D8A00CA
     */
    public boolean isMarginCustomer()
    {
        return marginCustFlag;
    }

    /**
     * (set信用顧客フラグ)<BR>
     * 信用顧客フラグをを設定する。<BR>
     * @@param l_blnMarginCustFlag- 信用顧客フラグ
     * @@roseuid 411092370213
     */
    public void setMarginCustFlag(boolean l_blnMarginCustFlag)
    {
        this.marginCustFlag = l_blnMarginCustFlag;
    }

    /**
     * (get預り区分)<BR>
     * <BR>
     * 補助口座IDにより、預り区分を取得する。<BR>
     * １）補助口座IDにより、補助口座タイプを特定する。<BR>
     * ２）預り区分を返す<BR>
     * ２－１）補助口座タイプはEQUITY_MARGIN_SUB_ACCOUNTの場合、代用を返す。<BR>
     * ２－２）そのたの場合、保護を返す<BR>
     * @@param l_lngSubAccountId - 補助口座ID
     * @@return String
     * @@roseuid 410E294600F1
     */
    public String getDepositType(long l_lngSubAccountId)
    {

        final String STR_METHOD_NAME =
            "getDepositType(long l_lngSubAccountId)";

        SubAccountTypeEnum l_subAccountType = null;
        if (subAccountIds != null)
        {
            l_subAccountType = (SubAccountTypeEnum)
                subAccountIds.
                get(new Long(l_lngSubAccountId));
        }

        if (l_subAccountType == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(
            l_subAccountType))
        {
            return WEB3TPDepositTypeDef.SUBSTITUTE;
        }
        else
        {
            return WEB3TPDepositTypeDef.TRUST;
        }

    }

    /**
     * (create顧客属性())
     * 顧客属性作成。
     * @@param l_lngAccountId - 口座ID
     * @@param l_blnMarginFlag- 信用顧客フラグ
     * @@roseuid 410E063F0024
     */
    public static WEB3TPAccountInfo create(long l_lngAccountId,
                                           boolean l_blnMarginFlag)
    {

        WEB3TPAccountInfo l_accountInfo = WEB3TPPersistentDataManager.
            getInstance().
            getAccountInfo(l_lngAccountId, l_blnMarginFlag);
        return l_accountInfo;

    }

    /*　@オブジェクトを比較する（equalsのオーバーライド）
     * @@param   比較先のWEB3TPAccountInfoオブジェクト
     * @@return  オブジェクト同じ場合-true
     *          そのた場合-false
     */
    public boolean equals(Object obj)
    {
        if (obj == null || ! (obj instanceof WEB3TPAccountInfo))
        {
            return false;
        }
        else
        {
            WEB3TPAccountInfo target = (WEB3TPAccountInfo) obj;
            return (target.accountId == this.accountId &&
                    ( (target.branchCode == null && this.branchCode == null) ||
                     target.branchCode.equals(this.branchCode)) &&
                    target.branchId == this.branchId &&
                    ( (target.institutionCode == null && this.institutionCode == null) ||
                     target.institutionCode.equals(this.institutionCode)) &&
                    target.institutionId == this.institutionId &&
                    target.marginCustFlag == this.marginCustFlag &&
                    ( (target.subAccountIds == null && this.subAccountIds == null) ||
                     target.subAccountIds.equals(this.subAccountIds))
                    );
        }
    }

    /**
     * このオブジェクトの文字列表現を返す。
     */
    public String toString()
    {
        String l_strSubAccountIds = "\n";
        if (subAccountIds != null)
        {
            Iterator l_iterator = subAccountIds.keySet().iterator();
            int i = 0;
            while (l_iterator.hasNext())
            {
                Long l_key = (Long) l_iterator.next();
                SubAccountTypeEnum l_subAccountTypeEnum = (SubAccountTypeEnum)
                    subAccountIds.get(l_key);

                l_strSubAccountIds = "[" + i + "]SubAccount[" + "SubAccountId:" +
                    l_key + " " + "SubAccountType:" +
                    l_subAccountTypeEnum + "]";
                i++;
            }
        }
        return ToStringUtils
            .newToStringBuilder(this)
            .append("accountId", accountId)
            .append("subAccountIds", l_strSubAccountIds)
            .append("branchId", branchId)
            .append("branchCode", branchCode)
            .append("institutionId", institutionId)
            .append("institutionCode", institutionCode)
            .append("marginCustFlag", marginCustFlag)
            .toString();
    }

}
@
