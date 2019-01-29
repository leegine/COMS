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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Z�@@�ցi��s�j�}�X�^(WEB3AccOpenFinInstitutionBank)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 ����� (���u) �V�K�쐬
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
 * (���Z�@@�ցi��s�j�}�X�^)<BR>
 * ���Z�@@�ցi��s�j�}�X�^<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccOpenFinInstitutionBank implements BusinessObject
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenFinInstitutionBank.class);

    /**
     * (���Z�@@�ցi��s�j�}�X�^�s)<BR>
     * ���Z�@@�ցi��s�j�}�X�^�s<BR>
     * <BR>
     * �� ���Z�@@�ցi��s�j�}�X�^Params�N���X��DDL��莩����������B<BR>
     */
    private FinInstitutionBankParams finInstitutionBankParams;

    /**
     * (���Z�@@�ցi��s�j�}�X�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@�I�u�W�F�N�g����<BR>
     * �@@���Z�@@�ցi��s�j�}�X�^�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���Z�@@�ցi��s�j�}�X�^�s�v���p�e�B�̃Z�b�g<BR>
     * �@@���Z�@@�ցi��s�j�}�X�^�s�𐶐������I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g���A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �� ���Z�@@�ցi��s�j�}�X�^Params�N���X��DDL��莩����������B<BR>
     * @@param l_finInstitutionBankParams - ���Z�@@�ցi��s�j�}�X�^�s�I�u�W�F�N�g<BR>
     * <BR>
     * �� ���Z�@@�ցi��s�j�}�X�^Params�N���X��DDL��莩����������B<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenFinInstitutionBank
     * @@roseuid 41A172A402E1
     */
    public WEB3AccOpenFinInstitutionBank(FinInstitutionBankParams l_finInstitutionBankParams)
    {
        this.finInstitutionBankParams = l_finInstitutionBankParams;
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.���Z�@@�ցi��s�j�}�X�^�s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 418722010245
     */
    public Object getDataSourceObject()
    {
        return this.finInstitutionBankParams;
    }

    /**
     * (get��s��)<BR>
     * ��s�����擾����B<BR>
     * <BR>
     * this.���Z�@@�ցi��s�j�}�X�^�s.��s���i�����j��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A171E600FC
     */
    public String getFinInstitutionName()
    {
        return this.finInstitutionBankParams.getFinInstitutionName();
    }

    /**
     * (get��s���i�J�i�j)<BR>
     * ��s���i�J�i�j���擾����B<BR>
     * <BR>
     * this.���Z�@@�ցi��s�j�}�X�^�s.��s���i�J�i�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A1721803DB
     */
    public String getFinInstitutionNameKana()
    {
        return this.finInstitutionBankParams.getFinInstitutionNameKana();
    }

    /**
     * (get��s�R�[�h)<BR>
     * ��s�R�[�h���擾����B<BR>
     * <BR>
     * this.���Z�@@�ցi��s�j�}�X�^�s.��s�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A1724703BB
     */
    public String getFinInstitutionCode()
    {
        return this.finInstitutionBankParams.getFinInstitutionCode();
    }

    /**
     * (get�x�X��)<BR>
     * �x�X�����擾����B<BR>
     * <BR>
     * this.���Z�@@�ցi��s�j�}�X�^�s.�x�X���i�����j��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A1722A02E1
     */
    public String getFinBranchName()
    {
        return this.finInstitutionBankParams.getFinBranchName();
    }

    /**
     * (get�x�X���i�J�i�j)<BR>
     * �x�X���i�J�i�j���擾����B<BR>
     * <BR>
     * this.���Z�@@�ցi��s�j�}�X�^�s.�x�X���i�J�i�j��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A1722A0300
     */
    public String getFinBranchNameKana()
    {
        return this.finInstitutionBankParams.getFinBranchNameKana();
    }

    /**
     * (get�x�X�R�[�h)<BR>
     * �x�X�R�[�h���擾����B<BR>
     * <BR>
     * this.���Z�@@�ցi��s�j�}�X�^�s.�x�X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A17259007F
     */
    public String getFinBranchCode()
    {
        return this.finInstitutionBankParams.getFinBranchCode();
    }

    /**
     * (get��s)<BR>
     * �istatic ���\�b�h�j<BR>
     * �L�[���[�h�ɊY��������Z�@@�ցi��s�j�}�X�^�I�u�W�F�N�g��z��ɂĎ擾����B<BR>
     * <BR>
     * �ȉ��̏����ŁA���Z�@@�ցi��s�j�}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@���Z�@@�ցi��s�j�}�X�^.��s���i�J�i�j like ��s���L�[���[�h + "%"<BR>
     * �@@���i��s���L�[���[�h == null�j�̏ꍇ�͏����Ȃ��B<BR>
     * <BR>
     * �@@[�擾��]<BR>
     * �@@��s�R�[�h �����i�Fasc�j<BR>
     * <BR>
     * �擾�����s�I�u�W�F�N�g�̂����A��s��(����)���d�����Ă�����̂��폜����<BR>
     * �i��s��(����)��Unique�ɂȂ�悤�Ɂj�B <BR>
     * �d�����폜�������ʂ̊e�s�I�u�W�F�N�g�ɂ��āA���Z�@@�ցi��s�j�}�X�^<BR>
     * �I�u�W�F�N�g�𐶐����A�z��ŕԋp����B<BR>
     * @@param l_strFinInstitutionNameKeyword - ��s���L�[���[�h
     * @@return webbroker3.accountopen.WEB3AccOpenFinInstitutionBank[]
     * @@roseuid 418721F7017A
     */
    public static WEB3AccOpenFinInstitutionBank[] getFinInstitution(String l_strFinInstitutionNameKeyword)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getFinInstitution(String)";
        log.entering(STR_METHOD_NAME);

        //�ȉ��̏����ŁA���Z�@@�ցi��s�j�}�X�^�e�[�u������������B
        //�@@[����]
        //���Z�@@�ցi��s�j�}�X�^.��s���i�J�i�j like ��s���L�[���[�h + "%"
        //���i��s���L�[���[�h == null�j�̏ꍇ�͏����Ȃ��B
        //[�擾��]
        //��s�R�[�h �����i�Fasc�j

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
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

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

        //�擾�����s�I�u�W�F�N�g�̂����A��s��(����)���d�����Ă�����̂��폜����
        //�i��s��(����)��Unique�ɂȂ�悤�Ɂj�B 
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

            //�d�����폜�������ʂ̊e�s�I�u�W�F�N�g�ɂ��āA
            //���Z�@@�ցi��s�j�}�X�^�I�u�W�F�N�g�𐶐����A�z��ŕԋp����B
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
     * (get�x�X)<BR>
     * �istatic ���\�b�h�j<BR>
     * �w���s�C�L�[���[�h�ɊY��������Z�@@�ցi��s�j�}�X�^�I�u�W�F�N�g��<BR>
     * �z��ɂĎ擾����B<BR>
     * <BR>
     * �ȉ��̏����ŁA���Z�@@�ցi��s�j�}�X�^�e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@���Z�@@�ցi��s�j�}�X�^.��s���i�����j = ��s���i�����j And <BR>
     * �@@���Z�@@�ցi��s�j�}�X�^.�x�X���i�J�i�j like �x�X���L�[���[�h + "%"<BR>
     * �@@���i��s���L�[���[�h == null�j�̏ꍇ�͏����Ȃ��B<BR>
     * <BR>
     * �@@[�擾��]<BR>
     * �@@�x�X���i�J�i�j �����i�Fasc�j<BR>
     * <BR>
     * �擾�����s�I�u�W�F�N�g���w�肵�A���Z�@@�ցi��s�j�}�X�^�I�u�W�F�N�g�𐶐����A<BR>
     * �z��ŕԋp����B<BR>
     * @@param l_strFinInstitutionName - ��s���i�����j
     * @@param l_strFinBranchNameKeyword - �x�X���L�[���[�h
     * @@return webbroker3.accountopen.WEB3AccOpenFinInstitutionBank[]
     * @@roseuid 41A1737F03AC
     */
    public static WEB3AccOpenFinInstitutionBank[] getFinBranch(String l_strFinInstitutionName, String l_strFinBranchNameKeyword)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getFinBranch(String, String)";
        log.entering(STR_METHOD_NAME);

        //�ȉ��̏����ŁA���Z�@@�ցi��s�j�}�X�^�e�[�u������������B
        //�@@[����]
        //���Z�@@�ցi��s�j�}�X�^.��s���i�����j = ��s���i�����j And
        //���Z�@@�ցi��s�j�}�X�^.�x�X���i�J�i�j like �x�X���L�[���[�h + "%"
        //���i��s���L�[���[�h == null�j�̏ꍇ�͏����Ȃ��B
        //[�擾��]
        //�x�X���i�J�i�j �����i�Fasc�j

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
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenFinInstitutionBank.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

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

        //�擾�����s�I�u�W�F�N�g���w�肵�A���Z�@@�ցi��s�j�}�X�^�I�u�W�F�N�g�𐶐����A�z��ŕԋp����B
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
