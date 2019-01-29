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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��Еʌ��ϋ@@�֏���(WEB3AioCompanySettleInstitutionConditions)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/10/22 ��O�� (���u) ���r���[ 
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
 * (��Еʌ��ϋ@@�֏���)<BR>
 * ��Еʌ��ϋ@@�֏����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioCompanySettleInstitutionConditions
    implements BusinessObject
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCompanySettleInstitutionConditions.class);

    /**
     * (��Еʌ��ϋ@@�֏���Row)<BR>
     * ��Еʌ��ϋ@@�֏����s�I�u�W�F�N�g
     */
    private CompBankConditionParams compBankConditionParams;

    /**
     * (��Еʌ��ϋ@@�֏���)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �������L�[�ɍs�I�u�W�F�N�g���擾���A�C���X�^���X�𐶐�����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
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
                log.debug("�e�[�u���ɊY������f�[�^������܂��� ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (get����������z�i1��������j)<BR>
     * 1��������̏���������z���擾����B
     * @@return double
     * @@roseuid 40E28C450392
     */
    public double getMaxAmountDaily()
    {
        return this.compBankConditionParams.getMaxAmountDaily();
    }

    /**
     * (get����������z�i1�񓖂���j)<BR>
     * 1�񓖂���̏���������z���擾����B
     * @@return double
     * @@roseuid 40E28C6501CD
     */
    public double getMaxAmountOnce()
    {
        return this.compBankConditionParams.getMaxAmountOnce();
    }

    /**
     * (get�����������z�i1�񓖂���j)<BR>
     * 1�񓖂���̉����������z���擾����B
     * @@return double
     * @@roseuid 40E28C7C0373
     */
    public double getMinAmountOnce()
    {
        return this.compBankConditionParams.getMinAmountOnce();
    }

    /**
     * (get�P�ʓ������z)<BR>
     * �P�ʓ������z���擾����B
     * @@return double
     * @@roseuid 40E28C8801AE
     */
    public double getAmountUnit()
    {
        return this.compBankConditionParams.getAmountUnit();
    }

    /**
     * (get����񐔁i1��������j)<BR>
     * 1��������̓�������񐔂��擾����B
     * @@return long
     * @@roseuid 40E28C940111
     */
    public long getMaxCount()
    {
        return this.compBankConditionParams.getMaxCount();
    }

    /**
     * (get�����XID)<BR>
     * �����XID���擾����B
     * @@return String
     * @@roseuid 4118A0C0037D
     */
    public String getShopId()
    {
        return this.compBankConditionParams.getShopId();
    }

    /**
     * (get�A�N�Z�X�L�[)<BR>
     * �A�N�Z�X�L�[���擾����B
     * @@return String
     * @@roseuid 4118A2B30002
     */
    public String getAccessKey()
    {
        return this.compBankConditionParams.getAccessKey();
    }

    /**
     * (get���؎���)<BR>
     * ���؎��Ԃ��擾����B
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
