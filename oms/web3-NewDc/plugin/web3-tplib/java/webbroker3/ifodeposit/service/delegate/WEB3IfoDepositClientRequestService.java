head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋����N���C�A���g���N�G�X�g�T�[�r�X�N���X(WEB3IfoDepositClientRequestService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * �؋����N���C�A���g���N�G�X�g�T�[�r�X(�؋����p)�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public abstract class WEB3IfoDepositClientRequestService
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositClientRequestService.class);

    /**
     * @@roseuid 4186F5FA0302
     */
    public WEB3IfoDepositClientRequestService()
    {

    }

    /**
     * (get�⏕����)<BR>
     * �igetSubAccount�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B<BR>
     * <BR>
     * �Q�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�⏕����.getMainAccount( )<BR>
     * <BR>
     * �R�j�⏕�����^�C�v�̔���<BR>
     * <BR>
     * �@@�擾�����ڋq�I�u�W�F�N�g�̈ȉ��̍��ڂɂ��A�����J�݋敪�𔻒肷��B<BR>
     * <BR>
     * �E�敨OP�����J�݋敪<BR>
     * �@@�ڋq.�敨OP�����J�݋敪(���؁j <BR>
     * �@@�ڋq.�敨OP�����J�݋敪(��؁j <BR>
     * �@@�ڋq.�敨OP�����J�݋敪(���؁j <BR>
     * <BR>
     * �@@���敨OP�������J�݂̏ꍇ<BR>
     * �@@�@@(�敨OP�����J�݋敪��3���ڂ��S�āhDEFAULT(�����Ȃ�)�h)<BR>
     * <BR>
     * �@@�@@�u�敨OP�������J�݃G���[�v�̗�O��throw����B<BR>
     * <BR>
     * �@@���؋��������J�ݍς̏ꍇ<BR>
     * �@@�@@(�敨OP�����J�݋敪��3���ڂ̉��ꂩ���h�敨OP�����J�݁h�A�܂��́A�h�敨�����J
     * �݁h)<BR>
     * <BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.getSubAccount( 
     * )�ɂāA�Y���ڋq�̐敨�I�v�V��������p�⏕�����I�u�W�F�N�g���擾���A���^�[������E
     * <BR>
     * <BR>
     * �@@�@@�@@[getSubAccount����]<BR>
     * �@@�@@�@@accountId�F�@@�⏕����.����ID<BR>
     * �@@�@@�@@subAccountType�F�@@SubAccountTypeEnum.�����������(�敨�؋���) <BR>
     * <BR>
     * �@@���؋����������J�݂̏ꍇ(��L�ȊO)<BR>
     * <BR>
     * �@@�@@null�����^�[������B<BR>
     * @@return webbroker3.gentrade.WEB3GentradeSubAccount
     * @@roseuid 41452DF1018C
     */
    public WEB3GentradeSubAccount getSubAccount()
    {
        // OpLoginSecurityService����AccountId���擾
        OpLoginSecurityService l_loginService =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        long l_lngAccountId = l_loginService.getAccountId();
        
        try
        {
            // �擾����AccountId���MainAccount���擾
            AccountManager l_accMgr = GtlUtils.getAccountManager();
            MainAccount l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            if (!isFuturesOptionAccountOpen(l_mainAccount))
            {
                // �敨OP�������J�݂̏ꍇ
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01294,
                    "getSubAccount()");

            } else if (isIfoDepositAccountOpen(l_mainAccount))
            {
                // �؋��������J�ݍς̏ꍇ
                return (WEB3GentradeSubAccount) l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            } else
            {
                // �؋����������J�݁i��L�ȊO�j�̏ꍇ
                return null;
            }
        } catch (NotFoundException nfe)
        {
            log.error(nfe.getMessage(), nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                "getSubAccount()",
                nfe);
        }
    }

    /**
     * is�敨OP�����J�ݍ�<BR>
     * �敨OP�����J�݋敪�i���؁j�A�敨OP�����J�݋敪�i��؁j�A�敨OP�����J�݋敪�i���؁j��
     * �����ꂩ���敨OP�����J�ݍς̏ꍇ<code>true</code>�A
     * ����ȊO�̏ꍇ<code>false</code>��Ԃ��B<BR>
     * 
     * @@param l_mainAccount �ڋq
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isFuturesOptionAccountOpen(MainAccount l_mainAccount)
    {
        MainAccountRow l_maRow =
            (MainAccountRow) l_mainAccount.getDataSourceObject();
        if (isFuturesOptionAccountOpen(l_maRow.getIfoAccOpenDivTokyo())
            || isFuturesOptionAccountOpen(l_maRow.getIfoAccOpenDivOsaka())
            || isFuturesOptionAccountOpen(l_maRow.getIfoAccOpenDivNagoya()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * is�敨OP�����J�ݍ�<BR>
     * �敨OP�����J�݋敪���uDEFAULT�i�����Ȃ��j�v�̏ꍇ<code>false</code>�A
     * ����ȊO�̏ꍇ<code>true</code>��Ԃ��B<BR>
     * 
     * @@param l_strIfoAccountOpenDiv�@@�敨OP�����J�݋敪
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isFuturesOptionAccountOpen(String l_strIfoAccountOpenDiv)
    {
        if (WEB3FutureOpAccountDef.DEFAULT.equals(l_strIfoAccountOpenDiv))
        {
            return false;
        } else
        {
            return true;
        }
    }

    /**
     * is�؋��������J�ݍ�<BR>
     * �敨OP�����J�݋敪�i���؁j�A�敨OP�����J�݋敪�i��؁j�A�敨OP�����J�݋敪�i���؁j��
     * �����ꂩ���؋��������J�ݍς̏ꍇ<code>true</code>�A
     * ����ȊO�̏ꍇ<code>false</code>��Ԃ��B<BR>
     * 
     * @@param l_mainAccount �ڋq
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isIfoDepositAccountOpen(MainAccount l_mainAccount)
    {
        MainAccountRow l_maRow =
            (MainAccountRow) l_mainAccount.getDataSourceObject();
        if (isIfoDepositAccountOpen(l_maRow.getIfoAccOpenDivTokyo())
            || isIfoDepositAccountOpen(l_maRow.getIfoAccOpenDivOsaka())
            || isIfoDepositAccountOpen(l_maRow.getIfoAccOpenDivNagoya()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * is�؋��������J�ݍ�<BR>
     * �敨OP�����J�݋敪���u�敨OP�����J�݁v�܂��́u�敨�����J�݁v�̏ꍇ<code>true</code>�A
     * ����ȊO�̏ꍇ<code>false</code>��Ԃ��B<BR>
     * 
     * @@param l_strIfoAccountOpenDiv�@@�敨OP�����J�݋敪
     * @@see webbroker3.common.define.WEB3FutureOpAccountDef 
     */
    private boolean isIfoDepositAccountOpen(String l_strIfoAccountOpenDiv)
    {
        if (WEB3FutureOpAccountDef
            .FUTURE_ACCOUNT_ESTABLISH
            .equals(l_strIfoAccountOpenDiv)
            || WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(
                l_strIfoAccountOpenDiv))
        {
            return true;
        } else
        {
            return false;
        }
    }

}
@
