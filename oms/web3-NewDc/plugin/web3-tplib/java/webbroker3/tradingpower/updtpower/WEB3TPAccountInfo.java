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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �ڋq�����N���X(WEB3TPAccountInfo.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/08/02 �R�c�@@��i(FLJ) �V�K�쐬
 *                  2004/08/04 �� ((FLJ)) ���������C��
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
 * (�ڋq����) <BR>
 * �]�͌v�Z���g�p����ڋq������\������B
 */
public class WEB3TPAccountInfo
{

    /**
     * ����ID <BR>
     */
    private long accountId;

    /**
     * �⏕����ID���X�g <BR>
     */
    private Hashtable subAccountIds;

    /**
     * �،����ID <BR>
     */
    private long institutionId;

    /**
     * �،���ЃR�[�h <BR>
     */
    private String institutionCode;

    /**
     * �x�XID <BR>
     */
    private long branchId;

    /**
     * �x�X�R�[�h <BR>
     */
    private String branchCode;

    /**
     * �M�p�ڋq�t���O <BR>
     */
    private boolean marginCustFlag;
    
    /**
     * �ŋ敪
     */
    private Hashtable taxTypes;

	

    /**
     * ����.���t���_�Ōڋq������������򒥎�����̏ꍇ
     * true, ����ȊO�̏ꍇfalse��Ԃ��B
     * @@param l_datDate
     */

    
    /**
     * �ڋq�����R���X�g���N�^
     * @@roseuid 4104E18B022D
     */
    public WEB3TPAccountInfo()
    {

    }

    /**
     * (get����ID)<BR>
     * <BR>
     * ����ID���擾����B<BR>
     * @@return long
     * @@roseuid 40CFED5402FC
     */
    public long getAccountId()
    {
        return accountId;
    }

    /**
     * (set����ID)<BR>
     * <BR>
     * ����ID���Z�b�g����B<BR>
     * @@param l_lngAccountId - ����ID
     * @@roseuid 40CFED57030C
     */
    public void setAccountId(long l_lngAccountId)
    {
        accountId = l_lngAccountId;
    }

    /**
     * (get�⏕����ID)<BR>
     * <BR>
     * �⏕�����^�C�v�L�[�ɂ��ĕ⏕����ID���擾����B<BR>
     * @@param l_subAccountType- �⏕�����^�C�v<BR>
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
     * (get�⏕����ID)<BR>
     * <BR>
     * �M�p�ڋq�t���O�L�[�ɂ��ĕ⏕����ID���擾����B<BR>
     * �M�p�ڋq�̏ꍇ�A
     * EQUITY_MARGIN_SUB_ACCOUNT��Ԃ��B
     * �����ڋq�̏ꍇ�A
     * EQUITY_SUB_ACCOUNT��Ԃ��B<BR>
     * @@param l_bMarginCustFlag - �M�p�ڋq�t���O<BR>
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
     * (hold�⏕����)<BR>
     * <BR>
     * �⏕�����^�C�v�L�[�ɂ��ĕ⏕�����ۗL�`�F�b�N�B<BR>
     * @@param l_subAccountType- �⏕�����^�C�v
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
     * (get�⏕����ID���X�g)<BR>
     * �⏕����ID���X�g��Ԃ��B<BR>
     * @@return Hashtable
     * @@roseuid 4110977E01C9
     */
    public Hashtable getSubAccountIds()
    {
        return this.subAccountIds;
    }

    /**
     * (set�⏕����ID���X�g)<BR>
     * <BR>
     * �⏕����ID���X�g���Z�b�g����B<BR>
     * @@param l_subAccountIds - �⏕����ID���X�g
     * @@roseuid 4100B7D50012
     */
    public void setSubAccountIds(Hashtable l_subAccountIds)
    {
        subAccountIds = l_subAccountIds;
    }

    /**
     * ����.Key�Ŏw�肳���ŋ敪��Ԃ��B
     * 
     * @@param taxTypeKey
     * @@return
     */
    public TaxTypeEnum getTaxType(String l_strKey)
    {
    	return (TaxTypeEnum)taxTypes.get(l_strKey);    	    	
    }

    /**
     * �ŋ敪�̃��X�g��Ԃ��B
     * 
     * @@param taxTypeKey
     * @@return
     */
    public Hashtable getTaxTypes()
    {
    	return taxTypes;    	    	
    }
    
    /**
     * ����������򒥎�����ł����true,
     * ����ȊO��false��Ԃ��B
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
     * �ŋ敪�̃��X�g���Z�b�g����B
     * @@param l_strKey
     * @@param l_taxType
     */
    public void setTaxTypes(Hashtable l_taxTypes)
    {
    	taxTypes = l_taxTypes;    	
    }
    
    
    /**
     * (get�،����ID)<BR>
     * <BR>
     * �،����ID���擾����B<BR>
     * @@return long
     * @@roseuid 4100727D026F
     */
    public long getInstitutionId()
    {
        return institutionId;
    }

    /**
     * (set�،����ID)<BR>
     * <BR>
     * �،����ID���Z�b�g����B<BR>
     * @@param l_lngInstitutionId - �،����ID
     * @@roseuid 4100727D028E
     */
    public void setInstitutionId(long l_lngInstitutionId)
    {
        institutionId = l_lngInstitutionId;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 40B4472E0226
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * (set�،���ЃR�[�h)<BR>
     * <BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@roseuid 40B4472E0246
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        institutionCode = l_strInstitutionCode;
    }

    /**
     * (get���XID)<BR>
     * <BR>
     * ���XID���擾����B<BR>
     * @@return long
     * @@roseuid 410072A200E9
     */
    public long getBranchId()
    {
        return branchId;
    }

    /**
     * (set���XID)<BR>
     * <BR>
     * ���XID���Z�b�g����B<BR>
     * @@param l_lngBranchId - ���XID
     * @@roseuid 410072A20108
     */
    public void setBranchId(long l_lngBranchId)
    {
        branchId = l_lngBranchId;
    }

    /**
     * (get���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 40B44742039D
     */
    public String getBranchCode()
    {
        return branchCode;
    }

    /**
     * (set���X�R�[�h)<BR>
     * <BR>
     * ���X�R�[�h���Z�b�g����B<BR>
     * @@param l_strBranchCode - ���X�R�[�h
     * @@roseuid 40B4474203BD
     */
    public void setBranchCode(String l_strBranchCode)
    {
        branchCode = l_strBranchCode;
    }

    /**
     * (is�M�p�ڋq)<BR>
     * �M�p�ڋq�t���O��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40F37D8A00CA
     */
    public boolean isMarginCustomer()
    {
        return marginCustFlag;
    }

    /**
     * (set�M�p�ڋq�t���O)<BR>
     * �M�p�ڋq�t���O����ݒ肷��B<BR>
     * @@param l_blnMarginCustFlag- �M�p�ڋq�t���O
     * @@roseuid 411092370213
     */
    public void setMarginCustFlag(boolean l_blnMarginCustFlag)
    {
        this.marginCustFlag = l_blnMarginCustFlag;
    }

    /**
     * (get�a��敪)<BR>
     * <BR>
     * �⏕����ID�ɂ��A�a��敪���擾����B<BR>
     * �P�j�⏕����ID�ɂ��A�⏕�����^�C�v����肷��B<BR>
     * �Q�j�a��敪��Ԃ�<BR>
     * �Q�|�P�j�⏕�����^�C�v��EQUITY_MARGIN_SUB_ACCOUNT�̏ꍇ�A��p��Ԃ��B<BR>
     * �Q�|�Q�j���̂��̏ꍇ�A�ی��Ԃ�<BR>
     * @@param l_lngSubAccountId - �⏕����ID
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
     * (create�ڋq����())
     * �ڋq�����쐬�B
     * @@param l_lngAccountId - ����ID
     * @@param l_blnMarginFlag- �M�p�ڋq�t���O
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

    /*�@@�I�u�W�F�N�g���r����iequals�̃I�[�o�[���C�h�j
     * @@param   ��r���WEB3TPAccountInfo�I�u�W�F�N�g
     * @@return  �I�u�W�F�N�g�����ꍇ-true
     *          ���̂��ꍇ-false
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
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B
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
