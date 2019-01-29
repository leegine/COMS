head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�N���C�A���g���N�G�X�g�T�[�r�X(WEB3PointClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�|�C���g�N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * �|�C���g�N���C�A���g���N�G�X�g�T�[�r�X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointClientRequestService extends WEB3ClientRequestService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3PointClientRequestService.class);
    
    /**
     * (get�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * super.get�⏕����()�ɂĕ⏕�����I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * [super.get�⏕����()�Ɏw�肷�����] <BR>
     * SubAccountTypeEnum.EQUITY_SUB_ACCOUNT<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount
     * @@roseuid 418F43730108
     */
    public SubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSubAccount()";
        log.entering(STR_METHOD_NAME);

        SubAccount l_subAccount = super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * <BR>
     * �P�j�⏕�����I�u�W�F�N�g�̎擾<BR>
     * <BR>
     *    this.get�⏕����()���R�[������B<BR>
     * <BR>
     * �Q�j�⏕����.getInstitution().getInstitutionCode()�̖߂�l��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A43C50021B
     */
    public String getInstitutionCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstitutionCode()";
        log.entering(STR_METHOD_NAME);

        //�P�j�⏕�����I�u�W�F�N�g�̎擾
        SubAccount l_subAccount = this.getSubAccount();
        
        //�Q�j�⏕����.getInstitution().getInstitutionCode()�̖߂�l��ԋp����B
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        Institution institution = l_subAccount.getInstitution();
        if (institution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strInstitutionCode = institution.getInstitutionCode();

        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h���擾����B<BR>
     * <BR>
     * �P�j�⏕�����I�u�W�F�N�g�̎擾<BR>
     * <BR>
     *    this.get�⏕����()���R�[������B<BR>
     * <BR>
     * �Q�j�⏕����.get����X().getBranchCode()�̖߂�l��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A43CCF0017
     */
    public String getBranchCode() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBranchCode()";
        log.entering(STR_METHOD_NAME);

        // �P�j�⏕�����I�u�W�F�N�g�̎擾
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        
        // �Q�j�⏕����.get����X().getBranchCode()�̖߂�l��ԋp����B
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        if (l_branch == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strBranchCode = l_branch.getBranchCode();

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �P�j�⏕�����I�u�W�F�N�g�̎擾<BR>
     * <BR>
     *    this.get�⏕����()���R�[������B<BR>
     * <BR>
     * �Q�j�⏕����.getMainAccount().getAccountCode()�̖߂�l��ԋp����B<BR>
     * @@return String
     * @@roseuid 41A43CD6017F
     */
    public String getAccountCode() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getAccountCode()";
        log.entering(STR_METHOD_NAME);

        // �P�j�⏕�����I�u�W�F�N�g�̎擾
        SubAccount l_subAccount = super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        log.exiting(STR_METHOD_NAME);
        //�Q�j�⏕����.getMainAccount().getAccountCode()�̖߂�l��ԋp����B
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        if (l_mainAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        String l_strAccountCode = l_mainAccount.getAccountCode();

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }
}
@
