head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeInfoRegist.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�єԍ��E�Ζ�����ύX�\��(WEB3AccInfoMobileOfficeInfoRegist)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 ����� (���u) �V�K�쐬
Revesion History : 2006/02/23 ������ (���u) ���f��No.086
Revesion History : 2006/03/20 ������ (���u) ���f��No.098
Revesion History : 2006/03/20 ������ (���u) ���f��No.099
Revesion History : 2006/06/30 ������ (���u) �d�l�ύX�Ǘ�No.115
Revesion History : 2006/07/20 �������I(SCS) get����m�F���t���O�C��(��������ǉ�)�Ή�
Revesion History : 2006/10/9  ꎉ�   (���u) ���f��No.124
Revesion History : 2006/10/30 ꎉ�   (���u) ���f��No.139
Revesion History : 2006/12/14 ���� (���u) ���f��No.154�A156
Revesion History : 2007/01/19 �����q (���u) ���f��No.160
*/

package webbroker3.accountinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.data.MobileOfficeInfoRegistDao;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistParams;
import webbroker3.accountinfo.data.MobileOfficeInfoRegistRow;
import webbroker3.accountinfo.define.WEB3AccInfoContactPriorityDef;
import webbroker3.accountinfo.define.WEB3JudgmentResultDivDef;
import webbroker3.accountinfo.define.WEB3RegistStateDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeChangeAccount;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3DecisionDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g�єԍ��E�Ζ�����ύX�\��)<BR>
 * �g�єԍ��E�Ζ�����ύX�\���N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoMobileOfficeInfoRegist implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeInfoRegist.class);

    /**
     * (�g�єԍ��E�Ζ�����ύX�\���s)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �g�єԍ��E�Ζ�����ύX�\��Params�N���X��DDL��莩����������B<BR>
     */
    private MobileOfficeInfoRegistParams mobileOfficeInfoRegistParams;

    /**
     * (�g�єԍ��E�Ζ�����ύX�\��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �s�w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B <BR>
     * @@param l_mobileOfficeInfoRegistParams - �g�єԍ��E�Ζ�����ύX�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * ���@@�g�єԍ��E�Ζ�����ύX�\��Params��DDL��莩����������B<BR>
     */
    public WEB3AccInfoMobileOfficeInfoRegist(MobileOfficeInfoRegistParams l_mobileOfficeInfoRegistParams)
    {
        this.mobileOfficeInfoRegistParams = l_mobileOfficeInfoRegistParams;
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 413D6E910141
     */
    public Object getDataSourceObject()
    {
        return this.mobileOfficeInfoRegistParams;
    }

    /**
     * �@@this.�g�єԍ��E�Ζ�����ύX�\���s���R�s�[���āA�������e��<BR>
     * �ʃC���X�^���X���쐬����iclone�j�B <BR>
     * �쐬�����R�s�[�����g��this.�g�єԍ��E�Ζ�����ύX�\���s�ɃZ�b�g����B <BR>
     * @@roseuid 413D6E910161
     */
    public void createForUpdateParams()
    {
        MobileOfficeInfoRegistParams l_params = new MobileOfficeInfoRegistParams(this.mobileOfficeInfoRegistParams);
        this.mobileOfficeInfoRegistParams = l_params;
    }

    /**
     * (is����m�F��)<BR>
     * �Ǘ��҂�����m�F�����𔻒肷��B<BR>
     * <BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.����m�F���t���O == <BR>
     * BooleanEnum.TRUE�̏ꍇtrue�A<BR>
     * �ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4141669901BA
     */
    public boolean isDeciding()
    {
        BooleanEnum l_decisionFlag = this.mobileOfficeInfoRegistParams.getDecisionFlag();

        if (BooleanEnum.TRUE.equals(l_decisionFlag))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (set����)<BR>
     * �{�I�u�W�F�N�g�ɔ�������Z�b�g����B<BR>
     * <BR>
     * this.�g�єԍ��E�Ζ�����ύX�\��Params�I�u�W�F�N�g�̃v���p�e�B�ɁA<BR>
     * �ȉ��̒ʂ�Z�b�g���s���B <BR>
     * <BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.����m�F���t���O = <BR>
     * BooleanEnum.FALSE
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.���茋�� = <BR>
     * �����̔��茋��<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.����҃R�[�h = <BR>
     * �����̊Ǘ��҃R�[�h<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.������� = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�폜�t���O = <BR>
     * BooleanEnum.TRUE<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�X�V�҃R�[�h = <BR>
     * �X�V�҃R�[�h<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�X�V���� = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * @@param l_strAdministratorCode - �Ǘ��҃R�[�h
     *
     * @@param l_strDecision - ���茋��<BR>
     * <BR>
     * 1�F�@@���F<BR>
     * 2�F�@@�s��<BR>
     * @@roseuid 414930F202CA
     */
    public void setDecision(String l_strAdministratorCode, String l_strDecision)
    {
        final String STR_METHOD_NAME = " setDecision(String, String)";
        log.entering(STR_METHOD_NAME);

        //��������
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();

        //�g�єԍ��E�Ζ�����ύX�\��Params.����m�F���t���O = BooleanEnum.FALSE
        this.mobileOfficeInfoRegistParams.setDecisionFlag(BooleanEnum.FALSE);

        //�g�єԍ��E�Ζ�����ύX�\��Params.���茋�� = �����̔��茋��
        this.mobileOfficeInfoRegistParams.setDecision(Integer.parseInt(l_strDecision));

        //�g�єԍ��E�Ζ�����ύX�\��Params.����҃R�[�h = �����̊Ǘ��҃R�[�h
        this.mobileOfficeInfoRegistParams.setDecisionUpdater(l_strAdministratorCode);

        //�g�єԍ��E�Ζ�����ύX�\��Params.������� = TradingSystem.getSystemTimestamp()
        this.mobileOfficeInfoRegistParams.setDecisionTimestamp(l_datProcessDate);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�폜�t���O = BooleanEnum.TRUE
        this.mobileOfficeInfoRegistParams.setDeleteFlag(BooleanEnum.TRUE);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�X�V�҃R�[�h = �X�V�҃R�[�h
        this.mobileOfficeInfoRegistParams.setLastUpdater(l_strAdministratorCode);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�X�V���� = TradingSystem.getSystemTimestamp()
        this.mobileOfficeInfoRegistParams.setLastUpdatedTimestamp(l_datProcessDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ڋq)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.�����h�c�ɊY������ڋq�I�u�W�F�N�g<BR>
     * ���擾����B<BR>
     * @@return WEB3GentradeMainAccount
     * @@roseuid 414A85B0033D
     */
    public WEB3GentradeMainAccount getMainAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMainAccount()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngAccountId = this.mobileOfficeInfoRegistParams.getAccountId();
        WEB3GentradeMainAccount l_mainAccount = null;

        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_mainAccount;
    }

    /**
     * (get���X)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.���X�h�c�ɊY�����镔�X�I�u�W�F�N�g<BR>
     * ���擾����B<BR>
     * @@return WEB3GentradeBranch
     * @@roseuid 414A862101B6
     */
    public WEB3GentradeBranch getBranch() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranch()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngBranchId = this.mobileOfficeInfoRegistParams.getBranchId();
        WEB3GentradeBranch l_branch = null;

        try
        {
            l_branch = (WEB3GentradeBranch)l_accountMgr.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_branch;
    }

    /**
     * (get�\����)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.�쐬�����̓��t�����iYYYYMMDD�j<BR>
     * ��ԋp����B<BR>
     * @@return Date
     * @@roseuid 414A86550187
     */
    public Date getRegistDate()
    {
        return WEB3DateUtility.toDay(this.mobileOfficeInfoRegistParams.getCreatedTimestamp());
    }

    /**
     * (get�����)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.��������̓��t�����iYYYYMMDD�j<BR>
     * ��ԋp����B<BR>
     * @@return Date
     * @@roseuid 414A868A036C
     */
    public Date getDecisionDate()
    {
        return WEB3DateUtility.toDay(this.mobileOfficeInfoRegistParams.getDecisionTimestamp());
    }

    /**
     * (get�\���󋵋敪)<BR>
     * �\���󋵋敪���擾����B<BR>
     * <BR>
     * �ȉ��̒ʂ�A�\���󋵋敪��ԋp����B<BR>
     * �@@�i���茋�ʁ�1 == 0�FDEFAULT && ����m�F���t���O��2 == <BR>
     * BooleanEnum.FALSE�j�̏ꍇ�A�h����҂��h<BR>
     * �@@�i���茋�ʁ�1 == 0�FDEFAULT && ����m�F���t���O��2 == <BR>
     * BooleanEnum.TRUE�j�̏ꍇ�A�h����҂��i�m�F���j�h<BR>
     * �@@�i.���茋�ʁ�1 != 0�FDEFAULT�j�̏ꍇ�A�h����ς݁h<BR>
     * <BR>
     * �@@��1 ���茋�ʁ@@�Fthis.�g�єԍ��E�Ζ�����ύX�\���s.���茋��<BR>
     * �@@��2 ����m�F���t���O�@@�Fthis.�g�єԍ��E�Ζ�����ύX�\���s.����m�F���t���O<BR>
     * @@return String
     * @@roseuid 414A86A60149
     */
    public String getRegistStateDiv()
    {
        final String STR_METHOD_NAME = " getRegistStateDiv()";
        log.entering(STR_METHOD_NAME);

        //���茋��
        int l_intDecision = this.mobileOfficeInfoRegistParams.getDecision();
        //����m�F���t���O
        BooleanEnum l_decisionFlag = this.mobileOfficeInfoRegistParams.getDecisionFlag();

        String l_strRegistStateDiv = null;

        if (l_intDecision == 0)
        {
            if (BooleanEnum.FALSE.equals(l_decisionFlag))
            {
                //�i���茋�ʁ�1 == 0�FDEFAULT && ����m�F���t���O��2 == BooleanEnum.FALSE�j�̏ꍇ�A�h����҂��h
                l_strRegistStateDiv = WEB3RegistStateDivDef.WAIT_DECISION;

            }
            else if (BooleanEnum.TRUE.equals(l_decisionFlag))
            {
                //�i���茋�ʁ�1 == 0�FDEFAULT && ����m�F���t���O��2 == BooleanEnum.TRUE�j�̏ꍇ�A�h����҂��i�m�F���j�h
                l_strRegistStateDiv = WEB3RegistStateDivDef.WAIT_DECISION_CONFIRMING;
            }
            else
            {
                log.error("�f�[�^�s�����G���[: ����m�F���t���O = " + l_decisionFlag);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME
                    );
            }
        }
        else
        {
            //�i.���茋�ʁ�1 != 0�FDEFAULT�j�̏ꍇ�A�h����ς݁h
            l_strRegistStateDiv = WEB3RegistStateDivDef.DECISION_COMPLETE;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strRegistStateDiv;
    }

    /**
     * (createNew�g�єԍ��E�Ζ�����ύX�\��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �g�єԍ��E�Ζ�����ύX�\���V�K�s�𐶐�����B
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g����<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@���g�єԍ��E�Ζ�����ύX�\��Params��DDL��莩����������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B <BR>
     * �@@�P�j�Ő��������g�єԍ��E�Ζ�����ύX�\��Params�I�u�W�F�N�g��<BR>
     * �v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B <BR>
     * <BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�g�єԍ��E�Ζ�����ύX�\���h�c =<BR>
     *  �V�K�̔�(*1)<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�،���ЃR�[�h = <BR>
     * �ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.���XID = <BR>
     *  �ڋq.getBranch().getBranchId()<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.����ID = <BR>
     * �ڋq.getAccountId()<BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����R�[�h = <BR>
     * �ڋq.getAccountCode()<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�A����d�b�ԍ��i�g�сj�@@= <BR>
     * �g�єԍ��E�Ζ�����.�g�єԍ�<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ��於�́@@= <BR>
     * �g�єԍ��E�Ζ�����.�Ζ��於��<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ���X�֔ԍ��@@= <BR>
     * �g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ�<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ���Z���@@= <BR>
     * �g�єԍ��E�Ζ�����.�Ζ���Z��<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ���d�b�ԍ��@@= <BR>
     * �g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ�<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.��E�@@= <BR>
     * �g�єԍ��E�Ζ�����.��E��<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.����m�F���t���O = <BR>
     * BooleanEnum.FALSE<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.���茋�� = <BR>
     * ���茋��.0�FDEFAULT<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.����҃R�[�h = null<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.������� = null<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�폜�t���O = <BR>
     * BooleanEnum.FALSE<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�X�V�҃R�[�h = <BR>
     * �X�V�҃R�[�h
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�쐬���� = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��Params.�X�V���� = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�A����D�揇�� 1�� = <BR>
     * �g�єԍ��E�Ζ�����.�A����D�揇�� 1�� <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�A����D�揇�� 2�� = <BR>
     * �g�єԍ��E�Ζ�����.�A����D�揇�� 2�� <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�A����D�揇�� 3�� = <BR>
     * �g�єԍ��E�Ζ�����.�A����D�揇�� 3�� <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�ڋq�������̂P = �g�єԍ��E�Ζ�����.�������̂P<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.�ڋq�������̂Q = �g�єԍ��E�Ζ�����.�������̂Q<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.�E�Ƌ敪 = �g�єԍ��E�Ζ�����.�E��  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.���� = �g�єԍ��E�Ζ�����.����  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.���� = �g�єԍ��E�Ζ�����.����  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.���Ђ��̑� = �g�єԍ��E�Ζ�����.���Ђ��̑� <BR> 
     * �g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��<BR> 
     * �g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.��\�Ҍ� = �g�єԍ��E�Ζ�����.��\�Ҍ�  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�����j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��<BR> 
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�����j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��<BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��<BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��<BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҁ@@�������� = �g�єԍ��E�Ζ�����.����ӔC�ҏ�������  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҁ@@��E = �g�єԍ��E�Ζ�����.����ӔC�Җ�E <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�җX�֔ԍ� = �g�єԍ��E�Ζ�����.�����ӔC�җX�֔ԍ�<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҏZ���P = �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���P  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҏZ���Q = �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���Q  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҏZ���R = �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���R  <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Ґ��N�����@@�N�� = �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N�����N��<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Ґ��N���� = �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N����<BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�҉�В��ʔԍ� = �g�єԍ��E�Ζ�����.�����ӔC�҉�В��ʔԍ�<BR>  
     * �g�єԍ��E�Ζ�����ύX�\��Params.���̑��A����i�g�сA����j = �g�єԍ��E�Ζ�����.���̑��̘A����i�g�сA����j<BR> 
     * �g�єԍ��E�Ζ�����ύX�\��Params.FAX�ԍ� = �g�єԍ��E�Ζ�����.FAX�ԍ� <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�N�� = �g�єԍ��E�Ζ�����.�N�� <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.���Z���Y�z = �g�єԍ��E�Ζ�����.���Z���Y�z <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�^�p�\��z = �g�єԍ��E�Ζ�����.�^�p�\��z <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����ړI = �g�єԍ��E�Ζ�����.�����ړI <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����\����� = �g�єԍ��E�Ζ�����.�����\����� <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�P�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�Q�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�R�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�R�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�S�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�S�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�T�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�T�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�U�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�U�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�V�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�V�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�W�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�W�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�X�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�X�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�P�j = �g�єԍ��E�Ζ�����.�����o���i�P�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�Q�j = �g�єԍ��E�Ζ�����.�����o���i�Q�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�R�j = �g�єԍ��E�Ζ�����.�����o���i�R�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�S�j = �g�єԍ��E�Ζ�����.�����o���i�S�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�T�j = �g�єԍ��E�Ζ�����.�����o���i�T�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�U�j = �g�єԍ��E�Ζ�����.�����o���i�U�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�V�j = �g�єԍ��E�Ζ�����.�����o���i�V�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�W�j = �g�єԍ��E�Ζ�����.�����o���i�W�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�X�j = �g�єԍ��E�Ζ�����.�����o���i�X�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���i�P�O�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�P�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�Q�j = �g�єԍ��E�Ζ�����.����̎�ށi�Q�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�R�j = �g�єԍ��E�Ζ�����.����̎�ށi�R�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�S�j = �g�єԍ��E�Ζ�����.����̎�ށi�S�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�T�j = �g�єԍ��E�Ζ�����.����̎�ށi�T�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�U�j = �g�єԍ��E�Ζ�����.����̎�ށi�U�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�V�j = �g�єԍ��E�Ζ�����.����̎�ށi�V�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�W�j = �g�єԍ��E�Ζ�����.����̎�ށi�W�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�X�j = �g�єԍ��E�Ζ�����.����̎�ށi�X�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�P�O�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�O�j <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����J�݂̓��@@ = �g�єԍ��E�Ζ�����.�����J�݂̓��@@ <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�����J�݂̓��@@�̏ڍ� = �g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.���ݗ��p���Ă���،���� = �g�єԍ��E�Ζ�����.���ݗ��p���Ă���،����<BR> 
     * �g�єԍ��E�Ζ�����ύX�\��Params.�C���^�[�l�b�g����敪 = �g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 <BR>
     * �g�єԍ��E�Ζ�����ύX�\��Params.�Љ�x�X = �g�єԍ��E�Ζ�����.�Љ�x�X <BR>
     * <BR>
     * �@@(*1) �g�єԍ��E�Ζ�����ύX�\���h�c�̐V�K�̔� <BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\��DAO.newPkValue()���\�b�h�ɂĎ擾����B <BR>
     * �@@�� �g�єԍ��E�Ζ�����ύX�\��DAO�N���X��DDL��莩����������B <BR>
     * <BR>
     * �R�j�@@�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g�ԋp<BR>
     * �@@�s�I�u�W�F�N�g���w�肵�A�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g��<BR>
     * �������ԋp����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@�g�єԍ��E�Ζ�����ύX�\���s�F�@@�i�Q�j�Ő��������s�I�u�W�F�N�g�j<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     *
     * @@param l_mobileOfficeInfo - �g�єԍ��E�Ζ����񃁃b�Z�[�W
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 413FF1EF01F7
     */
    public static WEB3AccInfoMobileOfficeInfoRegist createNewMobileOfficeInfoRegist(WEB3GentradeMainAccount l_mainAccount, WEB3AccInfoMobileOfficeInfo l_mobileOfficeInfo, String l_strUpdaterCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewMobileOfficeInfoRegist(WEB3GentradeMainAccount, WEB3AccInfoMobileOfficeInfo, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_mobileOfficeInfo == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount + "[l_mobileOfficeInfo] = " + l_mobileOfficeInfo
                );
        }

        MobileOfficeInfoRegistParams l_params = new MobileOfficeInfoRegistParams();

        //��������
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();

        long l_lngNewId;
        try
        {
            l_lngNewId = MobileOfficeInfoRegistDao.newPkValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�g�єԍ��E�Ζ�����ύX�\��Params.�g�єԍ��E�Ζ�����ύX�\���h�c = �V�K�̔�(*1)
        l_params.setMobileOfficeInfoRegistId(l_lngNewId);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode()
        l_params.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());

        //�g�єԍ��E�Ζ�����ύX�\��Params.���XID = �ڋq.getBranch().getBranchId()
        l_params.setBranchId(l_mainAccount.getBranch().getBranchId());
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ID = �ڋq.getAccountId()
        l_params.setAccountId(l_mainAccount.getAccountId());
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.�����R�[�h = �ڋq.getAccountCode()
        l_params.setAccountCode(l_mainAccount.getAccountCode());

        //�g�єԍ��E�Ζ�����ύX�\��Params.�A����d�b�ԍ��i�g�сj�@@= �g�єԍ��E�Ζ�����.�g�єԍ�
        l_params.setMobile(l_mobileOfficeInfo.mobileTelephone);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ��於�́@@= �g�єԍ��E�Ζ�����.�Ζ��於��
        l_params.setOffice(l_mobileOfficeInfo.officeName);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ���X�֔ԍ��@@= �g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ�
        l_params.setOfficeZipCode(l_mobileOfficeInfo.officeZipCode);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ���Z���@@= �g�єԍ��E�Ζ�����.�Ζ���Z��
        l_params.setOfficeAddress(l_mobileOfficeInfo.officeAdress);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�Ζ���d�b�ԍ��@@= �g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ�
        l_params.setOfficeTelephone(l_mobileOfficeInfo.officeTelephone);

        //�g�єԍ��E�Ζ�����ύX�\��Params.��E�@@= �g�єԍ��E�Ζ�����.��E��
        l_params.setPost(l_mobileOfficeInfo.position);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�\���҃R�[�h = �X�V�҃R�[�h
        //Wait QA:WEB3-ACCOUNTINFO-A-DD-0011 - zhang-bn
        l_params.setRegistUpdater(l_strUpdaterCode);

        //�g�єԍ��E�Ζ�����ύX�\��Params.����m�F���t���O = BooleanEnum.FALSE
        l_params.setDecisionFlag(BooleanEnum.FALSE);

        //�g�єԍ��E�Ζ�����ύX�\��Params.���茋�� = ���茋��.0�FDEFAULT
        l_params.setDecision(Integer.parseInt(WEB3DecisionDef.DEFAULT));

        //�g�єԍ��E�Ζ�����ύX�\��Params.����҃R�[�h = null
        l_params.setDecisionUpdater(null);

        //�g�єԍ��E�Ζ�����ύX�\��Params.������� = null
        l_params.setDecisionTimestamp(null);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�폜�t���O = BooleanEnum.FALSE
        l_params.setDeleteFlag(BooleanEnum.FALSE);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�X�V�҃R�[�h = �X�V�҃R�[�h
        l_params.setLastUpdater(l_strUpdaterCode);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�쐬���� = TradingSystem.getSystemTimestamp()
        l_params.setCreatedTimestamp(l_datProcessDate);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�X�V���� = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_datProcessDate);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.�A����D�揇�� 1�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 1�� 
        l_params.setContactPriority1(l_mobileOfficeInfo.contactPriority1);

        //�g�єԍ��E�Ζ�����ύX�\��Params.�A����D�揇�� 2�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 2�� 
        l_params.setContactPriority2(l_mobileOfficeInfo.contactPriority2);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.�A����D�揇�� 3�� = �g�єԍ��E�Ζ�����.�A����D�揇�� 3�� 
        l_params.setContactPriority3(l_mobileOfficeInfo.contactPriority3);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.�ڋq�������̂P = �g�єԍ��E�Ζ�����.�������̂P  
        l_params.setRealName1(l_mobileOfficeInfo.accountRealName1);     
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.�ڋq�������̂Q = �g�єԍ��E�Ζ�����.�������̂Q
        l_params.setRealName2(l_mobileOfficeInfo.accountRealName2);   
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.�E�Ƌ敪 = �g�єԍ��E�Ζ�����.�E��
        l_params.setOccupationDiv(l_mobileOfficeInfo.occupationDiv);  
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.���� = �g�єԍ��E�Ζ�����.����
        l_params.setDepartment(l_mobileOfficeInfo.department); 
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.���� = �g�єԍ��E�Ζ�����.����
        l_params.setNationality(l_mobileOfficeInfo.nationality); 
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.���Ђ��̑� = �g�єԍ��E�Ζ�����.���Ђ��̑�
        l_params.setNationalityOther(l_mobileOfficeInfo.nationalityOther);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��
        l_params.setRepresentFamilyName(l_mobileOfficeInfo.representFamilyName);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�����j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�����j��
        l_params.setRepresentGivenName(l_mobileOfficeInfo.representName);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��
        l_params.setRepresentFamilyNameAlt1(l_mobileOfficeInfo.representFamilyNameKana);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.��\�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j��
        l_params.setRepresentGivenNameAlt1(l_mobileOfficeInfo.representNameKana);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.��\�Ҍ� = �g�єԍ��E�Ζ�����.��\�Ҍ�
        l_params.setRepresentPower(l_mobileOfficeInfo.representPower);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�����j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��
        l_params.setDirectorFamilyName(l_mobileOfficeInfo.directorFamilyName);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�����j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�����j��
        l_params.setDirectorGivenName(l_mobileOfficeInfo.directorName);    
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��
        l_params.setDirectorFamilyNameAlt1(l_mobileOfficeInfo.directorFamilyNameKana);   
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Җ��i�J�i�j�� = �g�єԍ��E�Ζ�����.�����ӔC�Җ��i�J�i�j��
        l_params.setDirectorGivenNameAlt1(l_mobileOfficeInfo.directorNameKana);   
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҁ@@�������� = �g�єԍ��E�Ζ�����.����ӔC�ҏ�������
        l_params.setDirectorDepartment(l_mobileOfficeInfo.directorDepartment); 
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҁ@@��E = �g�єԍ��E�Ζ�����.����ӔC�Җ�E
        l_params.setDirectorPost(l_mobileOfficeInfo.directorPosition); 
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�җX�֔ԍ� = �g�єԍ��E�Ζ�����.�����ӔC�җX�֔ԍ�  
        l_params.setDirectorZipCode(l_mobileOfficeInfo.directorZipCode); 
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҏZ���P = �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���P
        l_params.setDirectorAddress1(l_mobileOfficeInfo.directorAddress1); 
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҏZ���Q = �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���Q
        l_params.setDirectorAddress2(l_mobileOfficeInfo.directorAddress2); 
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�ҏZ���R = �g�єԍ��E�Ζ�����.�����ӔC�ҏZ���R
        l_params.setDirectorAddress3(l_mobileOfficeInfo.directorAddress3);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Ґ��N�����@@�N�� = �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N�����N��
        l_params.setDirectorEraBorn(l_mobileOfficeInfo.directorEraBorn);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�Ґ��N���� = �g�єԍ��E�Ζ�����.�����ӔC�Ґ��N����
        l_params.setDirectorBornDate(l_mobileOfficeInfo.directorBornDate);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.����ӔC�҉�В��ʔԍ� = �g�єԍ��E�Ζ�����.�����ӔC�҉�В��ʔԍ�
        l_params.setDirectorCorpTelephone(l_mobileOfficeInfo.directorCorpDirect);
        
        //�g�єԍ��E�Ζ�����ύX�\��Params.���̑��A����i�g�сA����j = �g�єԍ��E�Ζ�����.���̑��̘A����i�g�сA����j
        l_params.setOtherContact(l_mobileOfficeInfo.directorOtherContact);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.FAX�ԍ� = �g�єԍ��E�Ζ�����.FAX�ԍ� 
        l_params.setFax(l_mobileOfficeInfo.faxTelephone);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�N�� = �g�єԍ��E�Ζ�����.�N�� 
        l_params.setAnnualIncomeDiv(l_mobileOfficeInfo.annualIncomeDiv);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.���Z���Y�z = �g�єԍ��E�Ζ�����.���Z���Y�z 
        l_params.setAssetValueDiv(l_mobileOfficeInfo.assetValueDiv);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�^�p�\��z = �g�єԍ��E�Ζ�����.�^�p�\��z 
        l_params.setFundBudgetAmountDiv(l_mobileOfficeInfo.fundBundgetAmountDiv);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����ړI = �g�єԍ��E�Ζ�����.�����ړI 
        l_params.setInvestPurposeDiv(l_mobileOfficeInfo.investPurposeDiv);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����\����� = �g�єԍ��E�Ζ�����.�����\����� 
        l_params.setInvestPlanPeriodDiv(l_mobileOfficeInfo.investPlanPeriodDiv);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�P�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�j  
        Integer l_intexperienceFlag1 = null;
        if (l_mobileOfficeInfo.experienceFlag1 != null)
        {
            l_intexperienceFlag1 = new Integer(l_mobileOfficeInfo.experienceFlag1);
        }
        l_params.setExperienceFlag1(l_intexperienceFlag1);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�Q�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j
        Integer l_intexperienceFlag2 = null;
        if (l_mobileOfficeInfo.experienceFlag2 != null)
        {
            l_intexperienceFlag2 = new Integer(l_mobileOfficeInfo.experienceFlag2);
        }
        l_params.setExperienceFlag2(l_intexperienceFlag2);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�R�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�R�j
        Integer l_intexperienceFlag3 = null;
        if (l_mobileOfficeInfo.experienceFlag3 != null)
        {
            l_intexperienceFlag3 = new Integer(l_mobileOfficeInfo.experienceFlag3);
        }
        l_params.setExperienceFlag3(l_intexperienceFlag3);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�S�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�S�j
        Integer l_intexperienceFlag4 = null;
        if (l_mobileOfficeInfo.experienceFlag4 != null)
        {
            l_intexperienceFlag4 = new Integer(l_mobileOfficeInfo.experienceFlag4);
        }
        l_params.setExperienceFlag4(l_intexperienceFlag4);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�T�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�T�j
        Integer l_intexperienceFlag5 = null;
        if (l_mobileOfficeInfo.experienceFlag5 != null)
        {
            l_intexperienceFlag5 = new Integer(l_mobileOfficeInfo.experienceFlag5);
        }
        l_params.setExperienceFlag5(l_intexperienceFlag5);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�U�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�U�j
        Integer l_intexperienceFlag6 = null;
        if (l_mobileOfficeInfo.experienceFlag6 != null)
        {
            l_intexperienceFlag6 = new Integer(l_mobileOfficeInfo.experienceFlag6);
        }
        l_params.setExperienceFlag6(l_intexperienceFlag6);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�V�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�V�j
        Integer l_intexperienceFlag7 = null;
        if (l_mobileOfficeInfo.experienceFlag7 != null)
        {
            l_intexperienceFlag7 = new Integer(l_mobileOfficeInfo.experienceFlag7);
        }
        l_params.setExperienceFlag7(l_intexperienceFlag7);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�W�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�W�j
        Integer l_intexperienceFlag8 = null;
        if (l_mobileOfficeInfo.experienceFlag8 != null)
        {
            l_intexperienceFlag8 = new Integer(l_mobileOfficeInfo.experienceFlag8);
        }
        l_params.setExperienceFlag8(l_intexperienceFlag8);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�X�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�X�j
        Integer l_intexperienceFlag9 = null;
        if (l_mobileOfficeInfo.experienceFlag9 != null)
        {
            l_intexperienceFlag9 = new Integer(l_mobileOfficeInfo.experienceFlag9);
        }
        l_params.setExperienceFlag9(l_intexperienceFlag9);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���̗L���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j
        Integer l_intexperienceFlag10 = null;
        if (l_mobileOfficeInfo.experienceFlag10 != null)
        {
            l_intexperienceFlag10 = new Integer(l_mobileOfficeInfo.experienceFlag10);
        }
        l_params.setExperienceFlag10(l_intexperienceFlag10);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�P�j = �g�єԍ��E�Ζ�����.�����o���i�P�j
        l_params.setExperienceDiv1(l_mobileOfficeInfo.experienceDiv1);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�Q�j = �g�єԍ��E�Ζ�����.�����o���i�Q�j 
        l_params.setExperienceDiv2(l_mobileOfficeInfo.experienceDiv2);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�R�j = �g�єԍ��E�Ζ�����.�����o���i�R�j 
        l_params.setExperienceDiv3(l_mobileOfficeInfo.experienceDiv3);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�S�j = �g�єԍ��E�Ζ�����.�����o���i�S�j 
        l_params.setExperienceDiv4(l_mobileOfficeInfo.experienceDiv4);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�T�j = �g�єԍ��E�Ζ�����.�����o���i�T�j 
        l_params.setExperienceDiv5(l_mobileOfficeInfo.experienceDiv5);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�U�j = �g�єԍ��E�Ζ�����.�����o���i�U�j
        l_params.setExperienceDiv6(l_mobileOfficeInfo.experienceDiv6);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�V�j = �g�єԍ��E�Ζ�����.�����o���i�V�j
        l_params.setExperienceDiv7(l_mobileOfficeInfo.experienceDiv7);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�W�j = �g�єԍ��E�Ζ�����.�����o���i�W�j
        l_params.setExperienceDiv8(l_mobileOfficeInfo.experienceDiv8);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�X�j = �g�єԍ��E�Ζ�����.�����o���i�X�j
        l_params.setExperienceDiv9(l_mobileOfficeInfo.experienceDiv9);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.�����o���i�P�O�j = �g�єԍ��E�Ζ�����.�����o���i�P�O�j
        l_params.setExperienceDiv10(l_mobileOfficeInfo.experienceDiv10);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�P�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�j
        Integer l_intInterest1 = null;
        if (l_mobileOfficeInfo.interest1 != null)
        {
            l_intInterest1 = new Integer(l_mobileOfficeInfo.interest1);
        } 
        l_params.setInterestFlag1(l_intInterest1);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�Q�j = �g�єԍ��E�Ζ�����.����̎�ށi�Q�j
        Integer l_intInterest2 = null;
        if (l_mobileOfficeInfo.interest2 != null)
        {
            l_intInterest2 = new Integer(l_mobileOfficeInfo.interest2);
        } 
        l_params.setInterestFlag2(l_intInterest2);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�R�j = �g�єԍ��E�Ζ�����.����̎�ށi�R�j 
        Integer l_intInterest3 = null;
        if (l_mobileOfficeInfo.interest3 != null)
        {
            l_intInterest3 = new Integer(l_mobileOfficeInfo.interest3);
        } 
        l_params.setInterestFlag3(l_intInterest3);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�S�j = �g�єԍ��E�Ζ�����.����̎�ށi�S�j
        Integer l_intInterest4 = null;
        if (l_mobileOfficeInfo.interest4 != null)
        {
            l_intInterest4 = new Integer(l_mobileOfficeInfo.interest4);
        } 
        l_params.setInterestFlag4(l_intInterest4);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�T�j = �g�єԍ��E�Ζ�����.����̎�ށi�T�j
        Integer l_intInterest5 = null;
        if (l_mobileOfficeInfo.interest5 != null)
        {
            l_intInterest5 = new Integer(l_mobileOfficeInfo.interest5);
        } 
        l_params.setInterestFlag5(l_intInterest5);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�U�j = �g�єԍ��E�Ζ�����.����̎�ށi�U�j
        Integer l_intInterest6 = null;
        if (l_mobileOfficeInfo.interest6 != null)
        {
            l_intInterest6 = new Integer(l_mobileOfficeInfo.interest6);
        } 
        l_params.setInterestFlag6(l_intInterest6);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�V�j = �g�єԍ��E�Ζ�����.����̎�ށi�V�j
        Integer l_intInteres7 = null;
        if (l_mobileOfficeInfo.interest7 != null)
        {
            l_intInteres7 = new Integer(l_mobileOfficeInfo.interest7);
        } 
        l_params.setInterestFlag7(l_intInteres7);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�W�j = �g�єԍ��E�Ζ�����.����̎�ށi�W�j
        Integer l_intInteres8 = null;
        if (l_mobileOfficeInfo.interest8 != null)
        {
            l_intInteres8 = new Integer(l_mobileOfficeInfo.interest8);
        } 
        l_params.setInterestFlag8(l_intInteres8);
        
		// �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�X�j = �g�єԍ��E�Ζ�����.����̎�ށi�X�j
        Integer l_intInteres9 = null;
        if (l_mobileOfficeInfo.interest9 != null)
        {
            l_intInteres9 = new Integer(l_mobileOfficeInfo.interest9);
        } 
        l_params.setInterestFlag9(l_intInteres9);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.����̎�ށi�P�O�j = �g�єԍ��E�Ζ�����.����̎�ށi�P�O�j
        Integer l_intInteres10 = null;
        if (l_mobileOfficeInfo.interest10 != null)
        {
            l_intInteres10 = new Integer(l_mobileOfficeInfo.interest10);
        } 
        l_params.setInterestFlag10(l_intInteres10);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.�����J�݂̓��@@ = �g�єԍ��E�Ζ�����.�����J�݂̓��@@
        l_params.setAppliMotivatDiv(l_mobileOfficeInfo.appliMotivatDiv);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.�����J�݂̓��@@�̏ڍ� = �g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ�
        l_params.setAppliMotivatDivDetail(l_mobileOfficeInfo.appliMotivatDetail);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.���ݗ��p���Ă���،���� = �g�єԍ��E�Ζ�����.���ݗ��p���Ă���،����
        l_params.setUseInstitutionDiv(l_mobileOfficeInfo.useInstitutionDiv);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.�C���^�[�l�b�g����敪 = �g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪
        l_params.setInternetTradeDiv(l_mobileOfficeInfo.internetTradeDiv);
        
		//  �g�єԍ��E�Ζ�����ύX�\��Params.�Љ�x�X = �g�єԍ��E�Ζ�����.�Љ�x�X 

        l_params.setIntroduceBranchCode(l_mobileOfficeInfo.introduceBranch);
        
        log.exiting(STR_METHOD_NAME);

        //�s�I�u�W�F�N�g���w�肵�A�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g�𐶐����ԋp����B
        return new WEB3AccInfoMobileOfficeInfoRegist(l_params);

    }

    /**
     * (get�g�єԍ��E�Ζ�����ύX�\��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �ڋq�ɊY������g�єԍ��E�Ζ�����ύX�\�����擾����B<BR>
     * <BR>
     * �P�j�@@�g�єԍ��E�Ζ�����ύX�\���e�[�u������<BR>
     * �@@�ȉ��̏����ŁA�g�єԍ��E�Ζ�����ύX�\���e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@���XID = �ڋq.getBranch().getBranchId() And<BR>
     * �@@����ID = �ڋq.getAccountId() And<BR>
     * �@@�폜�t���O = BooleanEnum.FALSE<BR>
     * <BR>
     * �Q�j�@@�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g�ԋp<BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ��āA�g�єԍ��E�Ζ�����ύX�\��<BR>
     * �I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * �@@�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * �@@�s���������擾�ł����ꍇ�́A�f�[�^�s�����Ɣ��肵�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01169<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     *
     * @@return webbroker3.accountinfo.WEB3AccInfoMobileOfficeInfoRegist
     * @@roseuid 413FF1EF01FA
     */
    public static WEB3AccInfoMobileOfficeInfoRegist getMobileOfficeInfoRegist(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMobileOfficeInfoRegist(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }

        List l_lisRecords = null;

        try
        {

            //�ȉ��̏����ŁA�g�єԍ��E�Ζ�����ύX�\���e�[�u������������B

            //[����]
            //[����]
            //�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
            //���XID = �ڋq.getBranch().getBranchId() And
            //����ID = �ڋq.getAccountId() And
            //�폜�t���O = BooleanEnum.FALSE

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ?");
            l_sbQueryString.append(" and branch_id = ?");
            l_sbQueryString.append(" and account_id = ?");
            l_sbQueryString.append(" and delete_flag = ?");


            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionCode(),
                "" + l_mainAccount.getBranch().getBranchId(),
                "" + l_mainAccount.getAccountId(),
                BooleanEnum.FALSE
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MobileOfficeInfoRegistRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            //�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B
            return null;
        }
        else if (l_intSize > 1)
        {
            //�s���������擾�ł����ꍇ�́A�f�[�^�s�����Ɣ��肵�A��O���X���[����B
            log.error("�f�[�^�s�����G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01169,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME
                );
        }

        WEB3AccInfoMobileOfficeInfoRegist l_mobileOfficeInfoRegist =
            new WEB3AccInfoMobileOfficeInfoRegist((MobileOfficeInfoRegistParams)l_lisRecords.get(0));

        log.exiting(STR_METHOD_NAME);
        return l_mobileOfficeInfoRegist;
    }

    /**
     * (get�g�єԍ��E�Ζ�����ύX�\��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �w��ɊY������g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �P�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �g�єԍ��E�Ζ�����ύX�\���s�I�u�W�F�N�g��List���擾����B <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>
     *�@@�@@   rowType�F�@@�g�єԍ��E�Ζ�����ύX�\��Row.TYPE<BR>
     *�@@�@@   where�F�@@��������������<BR>
     *�@@�@@   bindVars�F�@@���������f�[�^�R���e�i<BR>
     *�@@�@@   orderBy�F�@@�\�[�g����<BR>
     *      conditions: null<BR>
     * <BR>
     * �Q�j�@@�������ʂ̍s�I�u�W�F�N�g�Ōg�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g�𐶐����AList�ŕԋp����B<BR> 
     *�@@�A���A�����ς̍s�I�u�W�F�N�g�̏ꍇ�A�ԋp�lList�Ɋ܂߂Ȃ��B<BR>
     *<BR>
     *�@@[�����ς̔���]<BR>
     *�@@�폜�t���O == BooleanEnum.TRUE && ���茋�� == 0�@@<BR>
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_sortCondition - sort����
     * @@return List
     * @@roseuid 4149783D02EA
     */
    public static List getMobileOfficeInfoRegist(String l_strQueryString, String[] l_queryContainer, String l_sortCondition)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMobileOfficeInfoRegist(String, String[])";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {

            //QueryProcessor.doFindAllQuery( )�ɂ��A�ϑ��萔���ύX�\���s�I�u�W�F�N�g��List���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                MobileOfficeInfoRegistRow.TYPE,
                l_strQueryString,
                l_sortCondition,
				null,
                l_queryContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        List l_lisMobileOfficeInfoRegists = new Vector();

        for (int i = 0; i < l_intSize; i++)
        {
            MobileOfficeInfoRegistParams l_params = (MobileOfficeInfoRegistParams)l_lisRecords.get(i);
            if (BooleanEnum.TRUE.equals(l_params.getDeleteFlag()) && (l_params.getDecision() == 0))
            {
                
            }
            else
            {
                l_lisMobileOfficeInfoRegists.add(new WEB3AccInfoMobileOfficeInfoRegist(l_params));
            }

        }

        log.exiting(STR_METHOD_NAME);
        return l_lisMobileOfficeInfoRegists;

    }
    
    /**
     * (get�������)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.���������ԋp����B<BR>
     * @@return Date
     */
    public Date getDecisionTime()
    {        
        return this.mobileOfficeInfoRegistParams.getDecisionTimestamp();
    }
    
    /**
     * (get�\������)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.�쐬������ԋp����B<BR>
     *<BR>
     * @@return Date 
     */
    public Date getRegistTime()
    {
        return this.mobileOfficeInfoRegistParams.created_timestamp;
    }
    
    /**
     * (is�A����D�揇��)<BR>
     * (static ���\�b�h)<BR>
     * �A����D�揇�ʂ̐������`�F�b�N���s���B<BR>
     * �i���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s�� �j<BR>
     * <BR>
     * 1�D�A����D�揇�ʂ�1�ʁ`3�ʂŏd�����Ă���ꍇ�́A��O���X���[����B<BR>
     * �i���A����D�揇�� = "0�i�Ȃ��j"�̏d���͏����j<BR>
     * <BR>
     * �@@�@@��BUSINESS_ERROR_02400<BR>
     * �@@�@@�˘A����D�揇��1�ʁ`3�ʂɏd�����Ă���A���悪����܂��B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02400<BR>
     * <BR>
     * <BR>
     * 2�D�����^�C�v = �@@�l�̎��i3�j<BR>
     * �i�ڋq.getAccountType() = MainAccountTypeEnum.CORPORATE_ACCOUNT�j<BR>
     * <BR>
     * �@@2-1�j������<BR>
     * �@@�������Č��ɂď������L��<BR>
     * <BR>
     * <BR>
     * 3�D�����^�C�v = �l�̎��i0�C1�C2�j<BR>
     * �i�ڋq.getAccountType() != MainAccountTypeEnum.CORPORATE_ACCOUNT�j<BR>
     * <BR>
     * �@@3-1�j�A����D�揇�ʂ�1�ʁ`3�ʂɁA"3�i�g�сE���̑��j"���I������Ă���̂ɁA<BR>
     * �@@�u�g�єԍ��v��"������"�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@��BUSINESS_ERROR_02409<BR>
     * �@@�@@�ˌg�єԍ��E���̑��A���悪�����͂̂��߁A�A����D�揇�ʂɁh�g�сE���̑��h��I�����鎖�͏o���܂���B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02409<BR>
     * <BR>
     * �@@3-2�j�A����D�揇�ʂ�1�ʁ`3�ʂɁA"2�i�Ζ���j"���I������Ă���̂ɁA<BR>
     * �@@�u�Ζ���d�b�ԍ��v��"������"�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@��BUSINESS_ERROR_02410<BR>
     * �@@�@@�ˋΖ���d�b�ԍ��������͂̂��߁A�A����D�揇�ʂɁh�Ζ���h��I�����鎖�͏o���܂���B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02410<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g<BR>
     * @@param l_changedMobileOfficeInfo - �g�єԍ��E�Ζ�����<BR>
     * @@throws WEB3BaseException
     */
    public static void isContactPriority(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3AccInfoMobileOfficeInfo l_changedMobileOfficeInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isContactPriority(WEB3GentradeMainAccount, WEB3AccInfoMobileOfficeInfo)";
        log.entering(STR_METHOD_NAME);
        
        //�A����D�揇�ʂ̐������`�F�b�N���s���B
        // �i���͂�����ꍇ�́A�ȉ��̃`�F�b�N���s�� �j
        //1�D�A����D�揇�ʂ�1�ʁ`3�ʂŏd�����Ă���ꍇ�́A��O���X���[����B
        // �i���A����D�揇�� = "0�i�Ȃ��j"�̏d���͏����j        
        boolean l_blnIsContactPriority = false;
        if (l_changedMobileOfficeInfo.contactPriority1 != null)
        {
            if (!WEB3AccInfoContactPriorityDef.DEFAULT.equals(l_changedMobileOfficeInfo.contactPriority1) 
                && (l_changedMobileOfficeInfo.contactPriority1.equals(l_changedMobileOfficeInfo.contactPriority2) 
                || l_changedMobileOfficeInfo.contactPriority1.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                l_blnIsContactPriority = true;
            }            
        }
        if (l_changedMobileOfficeInfo.contactPriority2 != null)
        {
            if (!WEB3AccInfoContactPriorityDef.DEFAULT.equals(l_changedMobileOfficeInfo.contactPriority2) 
                    && l_changedMobileOfficeInfo.contactPriority2.equals(l_changedMobileOfficeInfo.contactPriority3))

                {
                    l_blnIsContactPriority = true;
                }   
        }
        
        if (l_blnIsContactPriority)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�A����D�揇��1�ʁ`3�ʂɏd�����Ă���A���悪����܂��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02400,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "�A����D�揇��1�ʁ`3�ʂɏd�����Ă���A���悪����܂��B");
        }
        
        //2�D�����^�C�v = �@@�l�̎��i3�j
        // �i�ڋq.getAccountType() = MainAccountTypeEnum.CORPORATE_ACCOUNT�j
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
        {
            //2-1�j������
            // �@@�������Č��ɂď������L��
        }
        
        //3�D�����^�C�v = �l�̎��i0�C1�C2�j
        // �i�ڋq.getAccountType() != MainAccountTypeEnum.CORPORATE_ACCOUNT�j
        if (!MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
        {
            //3-1�j�A����D�揇�ʂ�1�ʁ`3�ʂɁA"3�i�g�сE���̑��j"���I������Ă���̂ɁA
            // �@@�u�g�єԍ��v��"������"�̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(l_changedMobileOfficeInfo.mobileTelephone)
                && (WEB3AccInfoContactPriorityDef.MOBILE_OTHER_CONTACT.equals(l_changedMobileOfficeInfo.contactPriority1) 
                || WEB3AccInfoContactPriorityDef.MOBILE_OTHER_CONTACT.equals(l_changedMobileOfficeInfo.contactPriority2) 
                || WEB3AccInfoContactPriorityDef.MOBILE_OTHER_CONTACT.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("�g�єԍ��E���̑��A���悪�����͂̂��߁A�A����D�揇�ʂɁh�g�сE���̑��h��I�����鎖�͏o���܂���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02409,
                    WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,                    
                    "�g�єԍ��E���̑��A���悪�����͂̂��߁A�A����D�揇�ʂɁh�g�сE���̑��h��I�����鎖�͏o���܂���B");
            }
           
            //3-2�j�A����D�揇�ʂ�1�ʁ`3�ʂɁA"2�i�Ζ���j"���I������Ă���̂ɁA
            // �@@�u�Ζ���d�b�ԍ��v��"������"�̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(l_changedMobileOfficeInfo.officeTelephone)
                && (WEB3AccInfoContactPriorityDef.OFFICE_TRADER_DUTY.equals(l_changedMobileOfficeInfo.contactPriority1) 
                || WEB3AccInfoContactPriorityDef.OFFICE_TRADER_DUTY.equals(l_changedMobileOfficeInfo.contactPriority2) 
                || WEB3AccInfoContactPriorityDef.OFFICE_TRADER_DUTY.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("�Ζ���d�b�ԍ��������͂̂��߁A�A����D�揇�ʂɁh�Ζ���h��I�����鎖�͏o���܂���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02410,
                    WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,                    
                    "�Ζ���d�b�ԍ��������͂̂��߁A�A����D�揇�ʂɁh�Ζ���h��I�����鎖�͏o���܂���B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get����m�F���t���O)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.����m�F���t���O��ԋp����B<BR>
     * @@return String
     */
    public String getDecisionFlag()
    {
        if(this.mobileOfficeInfoRegistParams != null
           && this.mobileOfficeInfoRegistParams.getDecisionFlag() != null)
        {
            return this.mobileOfficeInfoRegistParams.getDecisionFlag().intValue() + "";
        }
        else
        {        
            return null;
        }
    }
    
    /**
     * (get���茋��)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.���茋�ʂ�ԋp����B<BR>
     * @@return String
     */
    public String getDecision()
    {
        if(this.mobileOfficeInfoRegistParams != null)
        {
            return String.valueOf(this.mobileOfficeInfoRegistParams.getDecision());
        }
        else
        { 
            return null;
        }
    }
    
    /**
     * (get�폜�t���O)<BR>
     * this.�g�єԍ��E�Ζ�����ύX�\���s.�폜�t���O��ԋp����B<BR>
     * @@return String
     */
    public String getDeleteFlag() 
    {
        if(this.mobileOfficeInfoRegistParams != null)
        {
            return this.mobileOfficeInfoRegistParams.getDeleteFlag().intValue() + "";
        }
        else
        { 
            return null;
        }
    }
    
    /**
     * (is�ύX����)<BR>
     * �istatic ���\�b�h�j <BR>
     * �ύX���ڂ̗L���`�F�b�N���s���B  <BR>
     * <BR>
     * �P�j �i�����j�ύX����̑S�Ă̍��ړ��e��  <BR>
     *      �i�����j�ύX�O���̑S�Ă̍��ړ��e��  <BR>
     *      �ЂƂł����ق�����ꍇ�A�ύX���ڗL�Ƃ���  <BR>
     *      TRUE��ԋp����B  <BR>
     * �Q�j �i�����j�ύX����̑S�Ă̍��ړ��e��  <BR>
     *      �i�����j�ύX�O���̑S�Ă̍��ړ��e��  <BR>
     *      ���S�Ɉ�v����ꍇ�A�ύX���ږ��Ƃ���  <BR>
     *      FALSE��ԋp����B<BR>
     * <BR>     
     * @@param l_preMobileOfficeInfo - (�ύX�O���)<BR>
     * �ύX�O���<BR>
     * @@param l_changedMobileOfficeInfo - (�ύX����)<BR>
     * �ύX����<BR>
     * @@return boolean
     */
    public static boolean isChangedItem(
        WEB3AccInfoMobileOfficeInfo l_preMobileOfficeInfo, 
        WEB3AccInfoMobileOfficeInfo l_changedMobileOfficeInfo)
    {
        final String STR_METHOD_NAME = " isChangedItem(WEB3AccInfoMobileOfficeInfo, WEB3AccInfoMobileOfficeInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_preMobileOfficeInfo == null)
        {
            log.error("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoMobileOfficeInfoRegist.class.getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B"
                );
        }
        
        if (l_changedMobileOfficeInfo == null)
        {
            return true;
        }
        //�i�����j�ύX����̑S�Ă̍��ړ��e���i�����j�ύX�O���̑S�Ă̍��ړ��e�ƂЂƂł����ق�����ꍇ�A�ύX���ڗL�Ƃ���
        // TRUE��ԋp����B 

        //��������1
        if (l_preMobileOfficeInfo.accountRealName1 != null)
        {
            if (!(l_preMobileOfficeInfo.accountRealName1.equals(l_changedMobileOfficeInfo.accountRealName1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.accountRealName1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //��������2
        if (l_preMobileOfficeInfo.accountRealName2 != null)
        {
            if (!(l_preMobileOfficeInfo.accountRealName2.equals(l_changedMobileOfficeInfo.accountRealName2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.accountRealName2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�N��
        if (l_preMobileOfficeInfo.annualIncomeDiv != null)
        {
            if (!(l_preMobileOfficeInfo.annualIncomeDiv.equals(l_changedMobileOfficeInfo.annualIncomeDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.annualIncomeDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����J�݂̓��@@�̏ڍ�
        if (l_preMobileOfficeInfo.appliMotivatDetail != null)
        {
            if (!(l_preMobileOfficeInfo.appliMotivatDetail.equals(l_changedMobileOfficeInfo.appliMotivatDetail)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.appliMotivatDetail != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����J�݂̓��@@
        if (l_preMobileOfficeInfo.appliMotivatDiv != null)
        {
            if (!(l_preMobileOfficeInfo.appliMotivatDiv.equals(l_changedMobileOfficeInfo.appliMotivatDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.appliMotivatDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //���Z���Y�z
        if (l_preMobileOfficeInfo.assetValueDiv != null)
        {
            if (!(l_preMobileOfficeInfo.assetValueDiv.equals(l_changedMobileOfficeInfo.assetValueDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.assetValueDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�A����D�揇�ʂP��
        if (l_preMobileOfficeInfo.contactPriority1 != null)
        {
            if (!(l_preMobileOfficeInfo.contactPriority1.equals(l_changedMobileOfficeInfo.contactPriority1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.contactPriority1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�A����D�揇�ʂQ��
        if (l_preMobileOfficeInfo.contactPriority2 != null)
        {
            if (!(l_preMobileOfficeInfo.contactPriority2.equals(l_changedMobileOfficeInfo.contactPriority2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.contactPriority2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�A����D�揇�ʂR��
        if (l_preMobileOfficeInfo.contactPriority3 != null)
        {
            if (!(l_preMobileOfficeInfo.contactPriority3.equals(l_changedMobileOfficeInfo.contactPriority3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.contactPriority3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����
        if (l_preMobileOfficeInfo.department != null)
        {
            if (!(l_preMobileOfficeInfo.department.equals(l_changedMobileOfficeInfo.department)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.department != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�ҏZ��1
        if (l_preMobileOfficeInfo.directorAddress1 != null)
        {
            if (!(l_preMobileOfficeInfo.directorAddress1.equals(l_changedMobileOfficeInfo.directorAddress1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorAddress1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�ҏZ��2
        if (l_preMobileOfficeInfo.directorAddress2 != null)
        {
            if (!(l_preMobileOfficeInfo.directorAddress2.equals(l_changedMobileOfficeInfo.directorAddress2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorAddress2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�ҏZ��3
        if (l_preMobileOfficeInfo.directorAddress3 != null)
        {
            if (!(l_preMobileOfficeInfo.directorAddress3.equals(l_changedMobileOfficeInfo.directorAddress3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorAddress3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�Ґ��N����
        if (l_preMobileOfficeInfo.directorBornDate != null)
        {
            if (!(l_preMobileOfficeInfo.directorBornDate.equals(l_changedMobileOfficeInfo.directorBornDate)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorBornDate != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�҉�В��ʔԍ�
        if (l_preMobileOfficeInfo.directorCorpDirect != null)
        {
            if (!(l_preMobileOfficeInfo.directorCorpDirect.equals(l_changedMobileOfficeInfo.directorCorpDirect)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorCorpDirect != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�ҏ�������
        if (l_preMobileOfficeInfo.directorDepartment != null)
        {
            if (!(l_preMobileOfficeInfo.directorDepartment.equals(l_changedMobileOfficeInfo.directorDepartment)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorDepartment != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�Ґ��N�����N��
        if (l_preMobileOfficeInfo.directorEraBorn != null)
        {
            if (!(l_preMobileOfficeInfo.directorEraBorn.equals(l_changedMobileOfficeInfo.directorEraBorn)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorEraBorn != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�Җ��i�����j��
        if (l_preMobileOfficeInfo.directorFamilyName != null)
        {
            if (!(l_preMobileOfficeInfo.directorFamilyName.equals(l_changedMobileOfficeInfo.directorFamilyName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorFamilyName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�Җ��i�J�i�j��
        if (l_preMobileOfficeInfo.directorFamilyNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.directorFamilyNameKana.equals(l_changedMobileOfficeInfo.directorFamilyNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorFamilyNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�Җ��i�����j��
        if (l_preMobileOfficeInfo.directorName != null)
        {
            if (!(l_preMobileOfficeInfo.directorName.equals(l_changedMobileOfficeInfo.directorName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�Җ��i�J�i�j��
        if (l_preMobileOfficeInfo.directorNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.directorNameKana.equals(l_changedMobileOfficeInfo.directorNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //���̑��A����(�g�сA���)
        if (l_preMobileOfficeInfo.directorOtherContact != null)
        {
            if (!(l_preMobileOfficeInfo.directorOtherContact.equals(l_changedMobileOfficeInfo.directorOtherContact)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorOtherContact != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�Җ�E
        if (l_preMobileOfficeInfo.directorPosition != null)
        {
            if (!(l_preMobileOfficeInfo.directorPosition.equals(l_changedMobileOfficeInfo.directorPosition)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorPosition != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����ӔC�җX�֔ԍ�
        if (l_preMobileOfficeInfo.directorZipCode != null)
        {
            if (!(l_preMobileOfficeInfo.directorZipCode.equals(l_changedMobileOfficeInfo.directorZipCode)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.directorZipCode != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�P�j
        if (l_preMobileOfficeInfo.experienceDiv1 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv1.equals(l_changedMobileOfficeInfo.experienceDiv1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�Q�j
        if (l_preMobileOfficeInfo.experienceDiv2 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv2.equals(l_changedMobileOfficeInfo.experienceDiv2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�R�j
        if (l_preMobileOfficeInfo.experienceDiv3 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv3.equals(l_changedMobileOfficeInfo.experienceDiv3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�S�j
        if (l_preMobileOfficeInfo.experienceDiv4 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv4.equals(l_changedMobileOfficeInfo.experienceDiv4)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv4 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�T�j
        if (l_preMobileOfficeInfo.experienceDiv5 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv5.equals(l_changedMobileOfficeInfo.experienceDiv5)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv5 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�U�j
        if (l_preMobileOfficeInfo.experienceDiv6 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv6.equals(l_changedMobileOfficeInfo.experienceDiv6)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv6 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�V�j
        if (l_preMobileOfficeInfo.experienceDiv7 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv7.equals(l_changedMobileOfficeInfo.experienceDiv7)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv7 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�W�j
        if (l_preMobileOfficeInfo.experienceDiv8 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv8.equals(l_changedMobileOfficeInfo.experienceDiv8)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv8 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�X�j
        if (l_preMobileOfficeInfo.experienceDiv9 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv9.equals(l_changedMobileOfficeInfo.experienceDiv9)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv9 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���i�P�O�j
        if (l_preMobileOfficeInfo.experienceDiv10 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceDiv10.equals(l_changedMobileOfficeInfo.experienceDiv10)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceDiv10 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�P�j
        if (l_preMobileOfficeInfo.experienceFlag1 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag1.equals(l_changedMobileOfficeInfo.experienceFlag1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�Q�j
        if (l_preMobileOfficeInfo.experienceFlag2 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag2.equals(l_changedMobileOfficeInfo.experienceFlag2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�R�j
        if (l_preMobileOfficeInfo.experienceFlag3 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag3.equals(l_changedMobileOfficeInfo.experienceFlag3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�S�j
        if (l_preMobileOfficeInfo.experienceFlag4 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag4.equals(l_changedMobileOfficeInfo.experienceFlag4)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag4 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�T�j
        if (l_preMobileOfficeInfo.experienceFlag5 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag5.equals(l_changedMobileOfficeInfo.experienceFlag5)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag5 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�U�j
        if (l_preMobileOfficeInfo.experienceFlag6 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag6.equals(l_changedMobileOfficeInfo.experienceFlag6)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag6 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�V�j
        if (l_preMobileOfficeInfo.experienceFlag7 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag7.equals(l_changedMobileOfficeInfo.experienceFlag7)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag7 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�W�j
        if (l_preMobileOfficeInfo.experienceFlag8 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag8.equals(l_changedMobileOfficeInfo.experienceFlag8)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag8 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�X�j
        if (l_preMobileOfficeInfo.experienceFlag9 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag9.equals(l_changedMobileOfficeInfo.experienceFlag9)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag9 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����o���̗L���i�P�O�j
        if (l_preMobileOfficeInfo.experienceFlag10 != null)
        {
            if (!(l_preMobileOfficeInfo.experienceFlag10.equals(l_changedMobileOfficeInfo.experienceFlag10)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.experienceFlag10 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //FAX�ԍ�
        if (l_preMobileOfficeInfo.faxTelephone != null)
        {
            if (!(l_preMobileOfficeInfo.faxTelephone.equals(l_changedMobileOfficeInfo.faxTelephone)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.faxTelephone != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�^�p�\��z
        if (l_preMobileOfficeInfo.fundBundgetAmountDiv != null)
        {
            if (!(l_preMobileOfficeInfo.fundBundgetAmountDiv.equals(l_changedMobileOfficeInfo.fundBundgetAmountDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.fundBundgetAmountDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�P�j
        if (l_preMobileOfficeInfo.interest1 != null)
        {
            if (!(l_preMobileOfficeInfo.interest1.equals(l_changedMobileOfficeInfo.interest1)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest1 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�Q�j
        if (l_preMobileOfficeInfo.interest2 != null)
        {
            if (!(l_preMobileOfficeInfo.interest2.equals(l_changedMobileOfficeInfo.interest2)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest2 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�R�j
        if (l_preMobileOfficeInfo.interest3 != null)
        {
            if (!(l_preMobileOfficeInfo.interest3.equals(l_changedMobileOfficeInfo.interest3)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest3 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�S�j
        if (l_preMobileOfficeInfo.interest4 != null)
        {
            if (!(l_preMobileOfficeInfo.interest4.equals(l_changedMobileOfficeInfo.interest4)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest4 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�T�j
        if (l_preMobileOfficeInfo.interest5 != null)
        {
            if (!(l_preMobileOfficeInfo.interest5.equals(l_changedMobileOfficeInfo.interest5)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest5 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�U�j
        if (l_preMobileOfficeInfo.interest6 != null)
        {
            if (!(l_preMobileOfficeInfo.interest6.equals(l_changedMobileOfficeInfo.interest6)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest6 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�V�j
        if (l_preMobileOfficeInfo.interest7 != null)
        {
            if (!(l_preMobileOfficeInfo.interest7.equals(l_changedMobileOfficeInfo.interest7)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest7 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�W�j
        if (l_preMobileOfficeInfo.interest8 != null)
        {
            if (!(l_preMobileOfficeInfo.interest8.equals(l_changedMobileOfficeInfo.interest8)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest8 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�X�j
        if (l_preMobileOfficeInfo.interest9 != null)
        {
            if (!(l_preMobileOfficeInfo.interest9.equals(l_changedMobileOfficeInfo.interest9)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest9 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����̎�ށi�P�O�j
        if (l_preMobileOfficeInfo.interest10 != null)
        {
            if (!(l_preMobileOfficeInfo.interest10.equals(l_changedMobileOfficeInfo.interest10)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.interest10 != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�C���^�[�l�b�g����敪
        if (l_preMobileOfficeInfo.internetTradeDiv != null)
        {
            if (!(l_preMobileOfficeInfo.internetTradeDiv.equals(l_changedMobileOfficeInfo.internetTradeDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.internetTradeDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�Љ�x�X
        if (l_preMobileOfficeInfo.introduceBranch != null)
        {
            if (!(l_preMobileOfficeInfo.introduceBranch.equals(l_changedMobileOfficeInfo.introduceBranch)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.introduceBranch != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����\�����
        if (l_preMobileOfficeInfo.investPlanPeriodDiv != null)
        {
            if (!(l_preMobileOfficeInfo.investPlanPeriodDiv.equals(l_changedMobileOfficeInfo.investPlanPeriodDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.investPlanPeriodDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����ړI
        if (l_preMobileOfficeInfo.investPurposeDiv != null)
        {
            if (!(l_preMobileOfficeInfo.investPurposeDiv.equals(l_changedMobileOfficeInfo.investPurposeDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.investPurposeDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�g�єԍ�
        if (l_preMobileOfficeInfo.mobileTelephone != null)
        {
            if (!(l_preMobileOfficeInfo.mobileTelephone.equals(l_changedMobileOfficeInfo.mobileTelephone)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.mobileTelephone != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //����
        if (l_preMobileOfficeInfo.nationality != null)
        {
            if (!(l_preMobileOfficeInfo.nationality.equals(l_changedMobileOfficeInfo.nationality)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.nationality != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //���Ђ��̑�
        if (l_preMobileOfficeInfo.nationalityOther != null)
        {
            if (!(l_preMobileOfficeInfo.nationalityOther.equals(l_changedMobileOfficeInfo.nationalityOther)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.nationalityOther != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�E��
        if (l_preMobileOfficeInfo.occupationDiv != null)
        {
            if (!(l_preMobileOfficeInfo.occupationDiv.equals(l_changedMobileOfficeInfo.occupationDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.occupationDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�Ζ���Z��
        if (l_preMobileOfficeInfo.officeAdress != null)
        {
            if (!(l_preMobileOfficeInfo.officeAdress.equals(l_changedMobileOfficeInfo.officeAdress)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeAdress != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�Ζ��於��
        if (l_preMobileOfficeInfo.officeName != null)
        {
            if (!(l_preMobileOfficeInfo.officeName.equals(l_changedMobileOfficeInfo.officeName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�Ζ���d�b�ԍ�
        if (l_preMobileOfficeInfo.officeTelephone != null)
        {
            if (!(l_preMobileOfficeInfo.officeTelephone.equals(l_changedMobileOfficeInfo.officeTelephone)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeTelephone != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�Ζ���X�֔ԍ�
        if (l_preMobileOfficeInfo.officeZipCode != null)
        {
            if (!(l_preMobileOfficeInfo.officeZipCode.equals(l_changedMobileOfficeInfo.officeZipCode)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.officeZipCode != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //��E��
        if (l_preMobileOfficeInfo.position != null)
        {
            if (!(l_preMobileOfficeInfo.position.equals(l_changedMobileOfficeInfo.position)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.position != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //��\�Җ��i�����j��
        if (l_preMobileOfficeInfo.representFamilyName != null)
        {
            if (!(l_preMobileOfficeInfo.representFamilyName.equals(l_changedMobileOfficeInfo.representFamilyName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representFamilyName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //��\�Җ��i�J�i�j��
        if (l_preMobileOfficeInfo.representFamilyNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.representFamilyNameKana.equals(l_changedMobileOfficeInfo.representFamilyNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representFamilyNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //��\�Җ��i�����j��
        if (l_preMobileOfficeInfo.representName != null)
        {
            if (!(l_preMobileOfficeInfo.representName.equals(l_changedMobileOfficeInfo.representName)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representName != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //��\�Җ��i�J�i�j��
        if (l_preMobileOfficeInfo.representNameKana != null)
        {
            if (!(l_preMobileOfficeInfo.representNameKana.equals(l_changedMobileOfficeInfo.representNameKana)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representNameKana != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //��\�Ҍ�
        if (l_preMobileOfficeInfo.representPower != null)
        {
            if (!(l_preMobileOfficeInfo.representPower.equals(l_changedMobileOfficeInfo.representPower)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.representPower != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //���ݗ��p���Ă���،����
        if (l_preMobileOfficeInfo.useInstitutionDiv != null)
        {
            if (!(l_preMobileOfficeInfo.useInstitutionDiv.equals(l_changedMobileOfficeInfo.useInstitutionDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        else
        {
            if (l_changedMobileOfficeInfo.useInstitutionDiv != null)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        //�����j�ύX����̑S�Ă̍��ړ��e���i�����j�ύX�O���̑S�Ă̍��ړ��e�Ɗ��S�Ɉ�v����ꍇ�A�ύX���ږ��Ƃ���
        //FALSE��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�ύX�O�\�����)<BR>
     * �ڋq�I�u�W�F�N�g���A�ύX�O�̌g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j�@@this.get�ύX�\���ڋq�i�j�ŁA�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g���擾�B <BR>
     * <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s�I�u�W�F�N�g���擾����B  <BR>
     * <BR>
     * �R�j�@@�������}�X�^�I�u�W�F�N�g���擾����B   <BR>
     * �@@�@@�@@�������}�X�^.get�������}�X�^()�ɂāA�������}�X�^�I�u�W�F�N�g���擾����B  <BR>
     * <BR>
     * �S�j�@@�g�єԍ��E�Ζ�����I�u�W�F�N�g�𐶐�����B  <BR>
     * <BR>
     * �T�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B  <BR>
     * �@@�g�єԍ��E�Ζ�����.�g�єԍ� = �ڋq�s.�A����d�b�ԍ��i�g�сj  <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ��於�� = �ڋq�s.�Ζ��於��  <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ� = �ڋq�s.�Ζ���X�֔ԍ�  <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���Z�� = �ڋq�s.�Ζ���Z��  <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ� = �ڋq�s.�Ζ���d�b�ԍ�  <BR>
     * �@@�g�єԍ��E�Ζ�����.��E�� = �ڋq�s.��E  <BR>
     * <BR>
     * �@@*get�������}�X�^�i�j != null�@@�̏ꍇ�A�ȉ��ɃZ�b�g <BR>
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇��1�� = �������}�X�^.�A����D�揇��1��  <BR>
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇��2�� = �������}�X�^.�A����D�揇��2��  <BR>
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇��3�� = �������}�X�^.�A����D�揇��3�ʁ@@  <BR>
     * �@@�g�єԍ��E�Ζ�����.���� = �������}�X�^.����  <BR>
     * �@@�g�єԍ��E�Ζ�����.�������̂P = �������}�X�^.�ڋq�������̂P   <BR>
     * �@@�g�єԍ��E�Ζ�����.�������̂Q = �������}�X�^.�ڋq�������̂Q   <BR>
     * �@@�g�єԍ��E�Ζ�����.�E�� = �������}�X�^.�E�Ƌ敪   <BR>
     * �@@�g�єԍ��E�Ζ�����.���� = �������}�X�^.����   <BR>
     * �@@�g�єԍ��E�Ζ�����.���Ђ��̑� = �������}�X�^.���Ђ��̑�  <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Ҍ� = �������}�X�^.��\�Ҍ�   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҁ@@�������� = �������}�X�^.����ӔC�ҏ�������   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E = �������}�X�^.����ӔC�Җ�E   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ� = �������}�X�^.�����ӔC�җX�֔ԍ�   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���P = �������}�X�^.�����ӔC�ҏZ���P   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q = �������}�X�^.�����ӔC�ҏZ���Q   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���R = �������}�X�^.�����ӔC�ҏZ���R   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = �������}�X�^.�����ӔC�Ґ��N�����N��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = �������}�X�^.�����ӔC�Ґ��N����   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = �������}�X�^.�����ӔC�҉�В��ʔԍ�   <BR>
     * �@@�g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j = �������}�X�^.���̑��̘A����i�g�сA����j   <BR>
     * �@@�g�єԍ��E�Ζ�����.FAX�ԍ� = �������}�X�^.FAX�ԍ�  <BR>
     * �@@�g�єԍ��E�Ζ�����.�N�� = �������}�X�^.�N��  <BR>
     * �@@�g�єԍ��E�Ζ�����.���Z���Y�z = �������}�X�^.���Z���Y�z  <BR>
     * �@@�g�єԍ��E�Ζ�����.�^�p�\��z = �������}�X�^.�^�p�\��z  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����ړI = �������}�X�^.�����ړI  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����\����� = �������}�X�^.�����\�����  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�P�j = �������}�X�^.�����o���̗L���i�P�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = �������}�X�^.�����o���̗L���i�Q�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = �������}�X�^.�����o���̗L���i�R�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = �������}�X�^.�����o���̗L���i�S�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = �������}�X�^.�����o���̗L���i�T�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = �������}�X�^.�����o���̗L���i�U�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = �������}�X�^.�����o���̗L���i�V�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = �������}�X�^.�����o���̗L���i�W�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = �������}�X�^.�����o���̗L���i�X�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = �������}�X�^.�����o���̗L���i�P�O�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�P�j = �������}�X�^.�����o���i�P�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�Q�j = �������}�X�^.�����o���i�Q�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�R�j = �������}�X�^.�����o���i�R�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�S�j = �������}�X�^.�����o���i�S�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�T�j = �������}�X�^.�����o���i�T�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�U�j = �������}�X�^.�����o���i�U�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�V�j = �������}�X�^.�����o���i�V�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�W�j = �������}�X�^.�����o���i�W�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�X�j = �������}�X�^.�����o���i�X�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�P�O�j = �������}�X�^.�����o���i�P�O�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�P�j = �������}�X�^.����̎�ށi�P�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = �������}�X�^.����̎�ށi�Q�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�R�j = �������}�X�^.����̎�ށi�R�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�S�j = �������}�X�^.����̎�ށi�S�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�T�j = �������}�X�^.����̎�ށi�T�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�U�j = �������}�X�^.����̎�ށi�U�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�V�j = �������}�X�^.����̎�ށi�V�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�W�j = �������}�X�^.����̎�ށi�W�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�X�j = �������}�X�^.����̎�ށi�X�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = �������}�X�^.����̎�ށi�P�O�j  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = �������}�X�^.�����J�݂̓��@@  <BR>
     * �@@�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = �������}�X�^.�����J�݂̓��@@�̏ڍ�  <BR>
     * �@@�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = �������}�X�^.���ݗ��p���Ă���،����  <BR>
     * �@@�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = �������}�X�^.�C���^�[�l�b�g����敪  <BR>
     * �@@�g�єԍ��E�Ζ�����.�Љ�x�X = �������}�X�^.�Љ�x�X  <BR>
     * <BR>
     * �@@*get�������}�X�^�i�j == null�@@�̏ꍇ�A��L���ڂ�null���Z�b�g <BR>
     * <BR>
     * �U�j�@@�P�j�Ŏ擾�����I�u�W�F�N�g.�Ζ����� = ���������g�єԍ��E�Ζ�����I�u�W�F�N�g <BR>
     * <BR>
     * �V�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g��ԋp <BR>
     * @@param l_mainAccount - (�ڋq�I�u�W�F�N�g)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@return WEB3AccInfoMobileOfficeChangeAccount
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoMobileOfficeChangeAccount getBeforeChangeInfo(
        WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBeforeChangeInfo(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }

        //�P�j�@@this.get�ύX�\���ڋq�i�j�ŁA�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g���擾�B
        WEB3AccInfoMobileOfficeChangeAccount l_changeAccount = this.getChangeRegistAccount();

        //�Q�j�@@�ڋq.getDataSourceObject()�ɂČڋq�s�I�u�W�F�N�g���擾����B
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

        //�R�j�@@�������}�X�^�I�u�W�F�N�g���擾����B
        //�������}�X�^.get�������}�X�^()�ɂāA�������}�X�^�I�u�W�F�N�g���擾����B
        WEB3AccInfoMaster l_accInfoMaster = WEB3AccInfoMaster.getAccInfoMaster(l_mainAccount);

        //�S�j�@@�g�єԍ��E�Ζ�����I�u�W�F�N�g�𐶐�����B
        WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();

        //�T�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //�g�єԍ��E�Ζ�����.�g�єԍ� = �ڋq�s.�A����d�b�ԍ��i�g�сj
        l_accInfoMobileOfficeInfo.mobileTelephone = l_mainAccountRow.getMobile();

        //�g�єԍ��E�Ζ�����.�Ζ��於�� = �ڋq�s.�Ζ��於��
        l_accInfoMobileOfficeInfo.officeName = l_mainAccountRow.getOffice();

        //�g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ� = �ڋq�s.�Ζ���X�֔ԍ�
        l_accInfoMobileOfficeInfo.officeZipCode = l_mainAccountRow.getOfficeZipCode();

        //�g�єԍ��E�Ζ�����.�Ζ���Z�� = �ڋq�s.�Ζ���Z��
        l_accInfoMobileOfficeInfo.officeAdress = l_mainAccountRow.getOfficeAddress();

        //�g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ� = �ڋq�s.�Ζ���d�b�ԍ�
        l_accInfoMobileOfficeInfo.officeTelephone = l_mainAccountRow.getOfficeTelephone();

        //�g�єԍ��E�Ζ�����.��E�� = �ڋq�s.��E
        l_accInfoMobileOfficeInfo.position = l_mainAccountRow.getPost();

        //*get�������}�X�^�i�j != null�@@�̏ꍇ�A�ȉ��ɃZ�b�g
        if (l_accInfoMaster != null)
        {
            AccountInfoMstRow l_accountInfoMstRow =
                (AccountInfoMstRow)l_accInfoMaster.getDataSourceObject();
            //�g�єԍ��E�Ζ�����.�A����D�揇��1�� = �������}�X�^.�A����D�揇��1��
            l_accInfoMobileOfficeInfo.contactPriority1 = l_accountInfoMstRow.getContactPriority1();

            //�g�єԍ��E�Ζ�����.�A����D�揇��2�� = �������}�X�^.�A����D�揇��2��
            l_accInfoMobileOfficeInfo.contactPriority2 = l_accountInfoMstRow.getContactPriority2();

            //�g�єԍ��E�Ζ�����.�A����D�揇��3�� = �������}�X�^.�A����D�揇��3��
            l_accInfoMobileOfficeInfo.contactPriority3 = l_accountInfoMstRow.getContactPriority3();

            //�g�єԍ��E�Ζ�����.���� = �������}�X�^.����
            l_accInfoMobileOfficeInfo.department = l_accountInfoMstRow.getDepartment();

            //�g�єԍ��E�Ζ�����.�������̂P = �������}�X�^.�ڋq�������̂P
            l_accInfoMobileOfficeInfo.accountRealName1 = l_accountInfoMstRow.getRealName1();

            //�g�єԍ��E�Ζ�����.�������̂Q = �������}�X�^.�ڋq�������̂Q
            l_accInfoMobileOfficeInfo.accountRealName2 = l_accountInfoMstRow.getRealName2();

            //�g�єԍ��E�Ζ�����.�E�� = �������}�X�^.�E�Ƌ敪
            l_accInfoMobileOfficeInfo.occupationDiv = l_accountInfoMstRow.getOccupationDiv();

            //�g�єԍ��E�Ζ�����.���� = �������}�X�^.����
            l_accInfoMobileOfficeInfo.nationality = l_accountInfoMstRow.getNationality();

            //�g�єԍ��E�Ζ�����.���Ђ��̑� = �������}�X�^.���Ђ��̑�
            l_accInfoMobileOfficeInfo.nationalityOther = l_accountInfoMstRow.getNationalityOther();

            //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��
            l_accInfoMobileOfficeInfo.representFamilyName = l_accountInfoMstRow.getRepresentFamilyName();

            //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = �������}�X�^.��\�Җ��i�����j��
            l_accInfoMobileOfficeInfo.representName = l_accountInfoMstRow.getRepresentGivenName();

            //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��
            l_accInfoMobileOfficeInfo.representFamilyNameKana =
                l_accountInfoMstRow.getRepresentFamilyNameAlt1();

            //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = �������}�X�^.��\�Җ��i�J�i�j��
            l_accInfoMobileOfficeInfo.representNameKana =
                l_accountInfoMstRow.getRepresentGivenNameAlt1();

            //�g�єԍ��E�Ζ�����.��\�Ҍ� = �������}�X�^.��\�Ҍ�
            l_accInfoMobileOfficeInfo.representPower = l_accountInfoMstRow.getRepresentPower();

            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��
            l_accInfoMobileOfficeInfo.directorFamilyName = l_accountInfoMstRow.getDirectorFamilyName();

            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = �������}�X�^.�����ӔC�Җ��i�����j��
            l_accInfoMobileOfficeInfo.directorName = l_accountInfoMstRow.getDirectorGivenName();

            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��
            l_accInfoMobileOfficeInfo.directorFamilyNameKana =
                l_accountInfoMstRow.getDirectorFamilyNameAlt1();

            //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = �������}�X�^.�����ӔC�Җ��i�J�i�j��
            l_accInfoMobileOfficeInfo.directorNameKana = l_accountInfoMstRow.getDirectorGivenNameAlt1();

            //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@�������� = �������}�X�^.����ӔC�ҏ�������
            l_accInfoMobileOfficeInfo.directorDepartment = l_accountInfoMstRow.getDirectorDepartment();

            //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E = �������}�X�^.����ӔC�Җ�E
            l_accInfoMobileOfficeInfo.directorPosition = l_accountInfoMstRow.getDirectorPost();

            //�g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ� = �������}�X�^.�����ӔC�җX�֔ԍ�
            l_accInfoMobileOfficeInfo.directorZipCode = l_accountInfoMstRow.getDirectorZipCode();

            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���P = �������}�X�^.�����ӔC�ҏZ���P
            l_accInfoMobileOfficeInfo.directorAddress1 = l_accountInfoMstRow.getDirectorAddress1();

            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q = �������}�X�^.�����ӔC�ҏZ���Q
            l_accInfoMobileOfficeInfo.directorAddress2 = l_accountInfoMstRow.getDirectorAddress2();

            //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���R = �������}�X�^.�����ӔC�ҏZ���R
            l_accInfoMobileOfficeInfo.directorAddress3 = l_accountInfoMstRow.getDirectorAddress3();

            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = �������}�X�^.�����ӔC�Ґ��N�����N��
            l_accInfoMobileOfficeInfo.directorEraBorn = l_accountInfoMstRow.getDirectorEraBorn();

            //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = �������}�X�^.�����ӔC�Ґ��N����
            l_accInfoMobileOfficeInfo.directorBornDate = l_accountInfoMstRow.getDirectorBornDate();

            //�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = �������}�X�^.�����ӔC�҉�В��ʔԍ�
            l_accInfoMobileOfficeInfo.directorCorpDirect = l_accountInfoMstRow.getDirectorCorpTelephone();

            //�g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j = �������}�X�^.���̑��̘A����i�g�сA����j
            l_accInfoMobileOfficeInfo.directorOtherContact = l_accountInfoMstRow.getOtherContact();

            //�g�єԍ��E�Ζ�����.FAX�ԍ� = �������}�X�^.FAX�ԍ�
            l_accInfoMobileOfficeInfo.faxTelephone = l_accountInfoMstRow.getFax();

            //�g�єԍ��E�Ζ�����.�N�� = �������}�X�^.�N��
            l_accInfoMobileOfficeInfo.annualIncomeDiv = l_accountInfoMstRow.getAnnualIncomeDiv();

            //�g�єԍ��E�Ζ�����.���Z���Y�z = �������}�X�^.���Z���Y�z
            l_accInfoMobileOfficeInfo.assetValueDiv = l_accountInfoMstRow.getAssetValueDiv();

            //�g�єԍ��E�Ζ�����.�^�p�\��z = �������}�X�^.�^�p�\��z
            l_accInfoMobileOfficeInfo.fundBundgetAmountDiv = l_accountInfoMstRow.getFundBudgetAmountDiv();

            //�g�єԍ��E�Ζ�����.�����ړI = �������}�X�^.�����ړI
            l_accInfoMobileOfficeInfo.investPurposeDiv = l_accountInfoMstRow.getInvestPurposeDiv();

            //�g�єԍ��E�Ζ�����.�����\����� = �������}�X�^.�����\�����
            l_accInfoMobileOfficeInfo.investPlanPeriodDiv = l_accountInfoMstRow.getInvestPlanPeriodDiv();

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�j = �������}�X�^.�����o���̗L���i�P�j
            if (!l_accountInfoMstRow.getExperienceFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag1 = l_accountInfoMstRow.getExperienceFlag1() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = �������}�X�^.�����o���̗L���i�Q�j
            if (!l_accountInfoMstRow.getExperienceFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag2 = l_accountInfoMstRow.getExperienceFlag2() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = �������}�X�^.�����o���̗L���i�R�j
            if (!l_accountInfoMstRow.getExperienceFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag3 = l_accountInfoMstRow.getExperienceFlag3() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = �������}�X�^.�����o���̗L���i�S�j
            if (!l_accountInfoMstRow.getExperienceFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag4 = l_accountInfoMstRow.getExperienceFlag4() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = �������}�X�^.�����o���̗L���i�T�j
            if (!l_accountInfoMstRow.getExperienceFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag5 = l_accountInfoMstRow.getExperienceFlag5() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = �������}�X�^.�����o���̗L���i�U�j
            if (!l_accountInfoMstRow.getExperienceFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag6 = l_accountInfoMstRow.getExperienceFlag6() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = �������}�X�^.�����o���̗L���i�V�j
            if (!l_accountInfoMstRow.getExperienceFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag7 = l_accountInfoMstRow.getExperienceFlag7() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = �������}�X�^.�����o���̗L���i�W�j
            if (!l_accountInfoMstRow.getExperienceFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag8 = l_accountInfoMstRow.getExperienceFlag8() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = �������}�X�^.�����o���̗L���i�X�j
            if (!l_accountInfoMstRow.getExperienceFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag9 = l_accountInfoMstRow.getExperienceFlag9() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = �������}�X�^.�����o���̗L���i�P�O�j
            if (!l_accountInfoMstRow.getExperienceFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.experienceFlag10 = l_accountInfoMstRow.getExperienceFlag10() + "";
            }

            //�g�єԍ��E�Ζ�����.�����o���i�P�j = �������}�X�^.�����o���i�P�j
            l_accInfoMobileOfficeInfo.experienceDiv1 = l_accountInfoMstRow.getExperienceDiv1();

            //�g�єԍ��E�Ζ�����.�����o���i�Q�j = �������}�X�^.�����o���i�Q�j
            l_accInfoMobileOfficeInfo.experienceDiv2 = l_accountInfoMstRow.getExperienceDiv2();

            //�g�єԍ��E�Ζ�����.�����o���i�R�j = �������}�X�^.�����o���i�R�j
            l_accInfoMobileOfficeInfo.experienceDiv3 = l_accountInfoMstRow.getExperienceDiv3();

            //�g�єԍ��E�Ζ�����.�����o���i�S�j = �������}�X�^.�����o���i�S�j
            l_accInfoMobileOfficeInfo.experienceDiv4 = l_accountInfoMstRow.getExperienceDiv4();

            //�g�єԍ��E�Ζ�����.�����o���i�T�j = �������}�X�^.�����o���i�T�j
            l_accInfoMobileOfficeInfo.experienceDiv5 = l_accountInfoMstRow.getExperienceDiv5();

            //�g�єԍ��E�Ζ�����.�����o���i�U�j = �������}�X�^.�����o���i�U�j
            l_accInfoMobileOfficeInfo.experienceDiv6 = l_accountInfoMstRow.getExperienceDiv6();

            //�g�єԍ��E�Ζ�����.�����o���i�V�j = �������}�X�^.�����o���i�V�j
            l_accInfoMobileOfficeInfo.experienceDiv7 = l_accountInfoMstRow.getExperienceDiv7();

            //�g�єԍ��E�Ζ�����.�����o���i�W�j = �������}�X�^.�����o���i�W�j
            l_accInfoMobileOfficeInfo.experienceDiv8 = l_accountInfoMstRow.getExperienceDiv8();

            //�g�єԍ��E�Ζ�����.�����o���i�X�j = �������}�X�^.�����o���i�X�j
            l_accInfoMobileOfficeInfo.experienceDiv9 = l_accountInfoMstRow.getExperienceDiv9();

            //�g�єԍ��E�Ζ�����.�����o���i�P�O�j = �������}�X�^.�����o���i�P�O�j
            l_accInfoMobileOfficeInfo.experienceDiv10 = l_accountInfoMstRow.getExperienceDiv10();

            //�g�єԍ��E�Ζ�����.����̎�ށi�P�j = �������}�X�^.����̎�ށi�P�j
            if (!l_accountInfoMstRow.getInterestFlag1IsNull())
            {
                l_accInfoMobileOfficeInfo.interest1 = l_accountInfoMstRow.getInterestFlag1() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = �������}�X�^.����̎�ށi�Q�j
            if (!l_accountInfoMstRow.getInterestFlag2IsNull())
            {
                l_accInfoMobileOfficeInfo.interest2 = l_accountInfoMstRow.getInterestFlag2() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�R�j = �������}�X�^.����̎�ށi�R�j
            if (!l_accountInfoMstRow.getInterestFlag3IsNull())
            {
                l_accInfoMobileOfficeInfo.interest3 = l_accountInfoMstRow.getInterestFlag3() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�S�j = �������}�X�^.����̎�ށi�S�j
            if (!l_accountInfoMstRow.getInterestFlag4IsNull())
            {
                l_accInfoMobileOfficeInfo.interest4 = l_accountInfoMstRow.getInterestFlag4() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�T�j = �������}�X�^.����̎�ށi�T�j
            if (!l_accountInfoMstRow.getInterestFlag5IsNull())
            {
                l_accInfoMobileOfficeInfo.interest5 = l_accountInfoMstRow.getInterestFlag5() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�U�j = �������}�X�^.����̎�ށi�U�j
            if (!l_accountInfoMstRow.getInterestFlag6IsNull())
            {
                l_accInfoMobileOfficeInfo.interest6 = l_accountInfoMstRow.getInterestFlag6() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�V�j = �������}�X�^.����̎�ށi�V�j
            if (!l_accountInfoMstRow.getInterestFlag7IsNull())
            {
                l_accInfoMobileOfficeInfo.interest7 = l_accountInfoMstRow.getInterestFlag7() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�W�j = �������}�X�^.����̎�ށi�W�j
            if (!l_accountInfoMstRow.getInterestFlag8IsNull())
            {
                l_accInfoMobileOfficeInfo.interest8 = l_accountInfoMstRow.getInterestFlag8() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�X�j = �������}�X�^.����̎�ށi�X�j
            if (!l_accountInfoMstRow.getInterestFlag9IsNull())
            {
                l_accInfoMobileOfficeInfo.interest9 = l_accountInfoMstRow.getInterestFlag9() + "";
            }

            //�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = �������}�X�^.����̎�ށi�P�O�j
            if (!l_accountInfoMstRow.getInterestFlag10IsNull())
            {
                l_accInfoMobileOfficeInfo.interest10 = l_accountInfoMstRow.getInterestFlag10() + "";
            }

            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = �������}�X�^.�����J�݂̓��@@
            l_accInfoMobileOfficeInfo.appliMotivatDiv = l_accountInfoMstRow.getAppliMotivatDiv();

            //�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = �������}�X�^.�����J�݂̓��@@�̏ڍ�
            l_accInfoMobileOfficeInfo.appliMotivatDetail = l_accountInfoMstRow.getAppliMotivatDivDetail();

            //�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = �������}�X�^.���ݗ��p���Ă���،����
            l_accInfoMobileOfficeInfo.useInstitutionDiv = l_accountInfoMstRow.getUseInstitutionDiv();

            //�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = �������}�X�^.�C���^�[�l�b�g����敪
            l_accInfoMobileOfficeInfo.internetTradeDiv = l_accountInfoMstRow.getInternetTradeDiv();

            //�g�єԍ��E�Ζ�����.�Љ�x�X = �������}�X�^.�Љ�x�X
            l_accInfoMobileOfficeInfo.introduceBranch = l_accountInfoMstRow.getIntroduceBranchCode();
        }

        //�U�j�@@�P�j�Ŏ擾�����I�u�W�F�N�g.�Ζ����� = ���������g�єԍ��E�Ζ�����I�u�W�F�N�g
        l_changeAccount.mobileOfficeInfo = l_accInfoMobileOfficeInfo;

        //�V�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_changeAccount;
    }

    /**
     * (get�ύX��\�����)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g���A�ύX��̌g�єԍ��E�Ζ�����ύX�\��<BR>
     * �ڋq�I�u�W�F�N�g�𐶐����A�ԋp����B <BR>
     * <BR>
     * <BR>
     * �P�j�@@this.get�ύX�\���ڋq�i�j�ŁA�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g���擾�B <BR>
     * <BR>
     * �Q�j�@@�g�єԍ��E�Ζ�����I�u�W�F�N�g�𐶐�����B  <BR>
     * <BR>
     * �R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B <BR>
     * �@@�g�єԍ��E�Ζ�����.�g�єԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����d�b�ԍ��i�g�сj <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ��於�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ��於�� <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ���X�֔ԍ� <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���Z�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ���Z�� <BR>
     * �@@�g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ���d�b�ԍ� <BR>
     * �@@�g�єԍ��E�Ζ�����.��E�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��E <BR>
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇�� 1�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����D�揇�� 1�� <BR>
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇�� 2�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����D�揇�� 2�� <BR>
     * �@@�g�єԍ��E�Ζ�����.�A����D�揇�� 3�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����D�揇�� 3�� <BR>
     * �@@�g�єԍ��E�Ζ�����.�ڋq�������̂P = this.�g�єԍ��E�Ζ�����ύX�\���s.�������̂P   <BR>
     * �@@�g�єԍ��E�Ζ�����.�ڋq�������̂Q = this.�g�єԍ��E�Ζ�����ύX�\���s.�������̂Q   <BR>
     * �@@�g�єԍ��E�Ζ�����.�E�Ƌ敪 = this.�g�єԍ��E�Ζ�����ύX�\���s.�E��   <BR>
     * �@@�g�єԍ��E�Ζ�����.���� = this.�g�єԍ��E�Ζ�����ύX�\���s.����   <BR>
     * �@@�g�єԍ��E�Ζ�����.���� = this.�g�єԍ��E�Ζ�����ύX�\���s.����   <BR>
     * �@@�g�єԍ��E�Ζ�����.���Ђ��̑� = this.�g�єԍ��E�Ζ�����ύX�\���s.���Ђ��̑�   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.��\�Ҍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Ҍ�   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�����j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�J�i�j��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҁ@@�������� = this.�g�єԍ��E�Ζ�����ύX�\���s.����ӔC�ҏ�������   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E = this.�g�єԍ��E�Ζ�����ύX�\���s.����ӔC�Җ�E   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�җX�֔ԍ�   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���P = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�ҏZ���P   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�ҏZ���Q   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�ҏZ���R = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�ҏZ���R   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Ґ��N�����N��   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Ґ��N����   <BR>
     * �@@�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�҉�В��ʔԍ�   <BR>
     * �@@�g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j = this.�g�єԍ��E�Ζ�����ύX�\���s.���̑��̘A����i�g�сA����j   <BR>
     * �@@�g�єԍ��E�Ζ�����.FAX�ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.FAX�ԍ� <BR>
     * �@@�g�єԍ��E�Ζ�����.�N�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�N�� <BR>
     * �@@�g�єԍ��E�Ζ�����.���Z���Y�z = this.�g�єԍ��E�Ζ�����ύX�\���s.���Z���Y�z <BR>
     * �@@�g�єԍ��E�Ζ�����.�^�p�\��z = this.�g�єԍ��E�Ζ�����ύX�\���s.�^�p�\��z <BR>
     * �@@�g�єԍ��E�Ζ�����.�����ړI = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ړI <BR>
     * �@@�g�єԍ��E�Ζ�����.�����\����� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����\����� <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�P�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�P�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�Q�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�R�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�S�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�T�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�U�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�V�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�W�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�X�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�P�O�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�P�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�P�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�Q�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�Q�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�R�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�R�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�S�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�S�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�T�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�T�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�U�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�U�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�V�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�V�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�W�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�W�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�X�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�X�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����o���i�P�O�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�P�O�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�P�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�P�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�Q�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�R�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�R�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�S�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�S�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�T�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�T�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�U�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�U�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�V�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�V�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�W�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�W�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�X�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�X�j <BR>
     * �@@�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�P�O�j <BR>
     * �@@�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = this.�g�єԍ��E�Ζ�����ύX�\���s.�����J�݂̓��@@ <BR>
     * �@@�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����J�݂̓��@@�̏ڍ� <BR>
     * �@@�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = this.�g�єԍ��E�Ζ�����ύX�\���s.���ݗ��p���Ă���،���� <BR>
     * �@@�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = this.�g�єԍ��E�Ζ�����ύX�\���s.�C���^�[�l�b�g����敪 <BR>
     * �@@�g�єԍ��E�Ζ�����.�Љ�x�X = this.�g�єԍ��E�Ζ�����ύX�\���s.�Љ�x�X <BR>
     * <BR>
     * <BR>
     * �S�j�@@�P�j�Ő��������I�u�W�F�N�g.�Ζ����� = ���������g�єԍ��E�Ζ�����I�u�W�F�N�g <BR>
     * <BR>
     * �T�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g��ԋp�B<BR>
     * @@return WEB3AccInfoMobileOfficeChangeAccount
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoMobileOfficeChangeAccount getChangedChangeInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getChangedChangeInfo()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.get�ύX�\���ڋq�i�j�ŁA�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g���擾�B
        WEB3AccInfoMobileOfficeChangeAccount l_changeAccount = this.getChangeRegistAccount();

        //�Q�j�@@�g�єԍ��E�Ζ�����I�u�W�F�N�g�𐶐�����B
        WEB3AccInfoMobileOfficeInfo l_accInfoMobileOfficeInfo = new WEB3AccInfoMobileOfficeInfo();

        //�R�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //�g�єԍ��E�Ζ�����.�g�єԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����d�b�ԍ��i�g�сj
        l_accInfoMobileOfficeInfo.mobileTelephone = this.mobileOfficeInfoRegistParams.getMobile();

        //�g�єԍ��E�Ζ�����.�Ζ��於�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ��於��
        l_accInfoMobileOfficeInfo.officeName = this.mobileOfficeInfoRegistParams.getOffice();

        //�g�єԍ��E�Ζ�����.�Ζ���X�֔ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ���X�֔ԍ�
        l_accInfoMobileOfficeInfo.officeZipCode = this.mobileOfficeInfoRegistParams.getOfficeZipCode();

        //�g�єԍ��E�Ζ�����.�Ζ���Z�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ���Z��
        l_accInfoMobileOfficeInfo.officeAdress = this.mobileOfficeInfoRegistParams.getOfficeAddress();

        //�g�єԍ��E�Ζ�����.�Ζ���d�b�ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�Ζ���d�b�ԍ�
        l_accInfoMobileOfficeInfo.officeTelephone = this.mobileOfficeInfoRegistParams.getOfficeTelephone();

        //�g�єԍ��E�Ζ�����.��E�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��E
        l_accInfoMobileOfficeInfo.position = this.mobileOfficeInfoRegistParams.getPost();

        //�g�єԍ��E�Ζ�����.�A����D�揇�� 1�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����D�揇�� 1��
        l_accInfoMobileOfficeInfo.contactPriority1 = this.mobileOfficeInfoRegistParams.getContactPriority1();

        //�g�єԍ��E�Ζ�����.�A����D�揇�� 2�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����D�揇�� 2��
        l_accInfoMobileOfficeInfo.contactPriority2 = this.mobileOfficeInfoRegistParams.getContactPriority2();

        //�g�єԍ��E�Ζ�����.�A����D�揇�� 3�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�A����D�揇�� 3��
        l_accInfoMobileOfficeInfo.contactPriority3 = this.mobileOfficeInfoRegistParams.getContactPriority3();

        //�g�єԍ��E�Ζ�����.�ڋq�������̂P = this.�g�єԍ��E�Ζ�����ύX�\���s.�������̂P
        l_accInfoMobileOfficeInfo.accountRealName1 = this.mobileOfficeInfoRegistParams.getRealName1();

        //�g�єԍ��E�Ζ�����.�ڋq�������̂Q = this.�g�єԍ��E�Ζ�����ύX�\���s.�������̂Q
        l_accInfoMobileOfficeInfo.accountRealName2 = this.mobileOfficeInfoRegistParams.getRealName2();

        //�g�єԍ��E�Ζ�����.�E�Ƌ敪 = this.�g�єԍ��E�Ζ�����ύX�\���s.�E��
        l_accInfoMobileOfficeInfo.occupationDiv = this.mobileOfficeInfoRegistParams.getOccupationDiv();

        //�g�єԍ��E�Ζ�����.���� = this.�g�єԍ��E�Ζ�����ύX�\���s.����
        l_accInfoMobileOfficeInfo.department = this.mobileOfficeInfoRegistParams.getDepartment();

        //�g�єԍ��E�Ζ�����.���� = this.�g�єԍ��E�Ζ�����ύX�\���s.����
        l_accInfoMobileOfficeInfo.nationality = this.mobileOfficeInfoRegistParams.getNationality();

        //�g�єԍ��E�Ζ�����.���Ђ��̑� = this.�g�єԍ��E�Ζ�����ύX�\���s.���Ђ��̑�
        l_accInfoMobileOfficeInfo.nationalityOther = this.mobileOfficeInfoRegistParams.getNationalityOther();

        //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�����j��
        l_accInfoMobileOfficeInfo.representFamilyName =
            this.mobileOfficeInfoRegistParams.getRepresentFamilyName();

        //�g�єԍ��E�Ζ�����.��\�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�����j��
        l_accInfoMobileOfficeInfo.representName = this.mobileOfficeInfoRegistParams.getRepresentGivenName();

        //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�J�i�j��
        l_accInfoMobileOfficeInfo.representFamilyNameKana =
            this.mobileOfficeInfoRegistParams.getRepresentFamilyNameAlt1();

        //�g�єԍ��E�Ζ�����.��\�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Җ��i�J�i�j��
        l_accInfoMobileOfficeInfo.representNameKana =
            this.mobileOfficeInfoRegistParams.getRepresentGivenNameAlt1();

        //�g�єԍ��E�Ζ�����.��\�Ҍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.��\�Ҍ�
        l_accInfoMobileOfficeInfo.representPower = this.mobileOfficeInfoRegistParams.getRepresentPower();

        //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�����j��
        l_accInfoMobileOfficeInfo.directorFamilyName =
            this.mobileOfficeInfoRegistParams.getDirectorFamilyName();

        //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�����j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�����j��
        l_accInfoMobileOfficeInfo.directorName = this.mobileOfficeInfoRegistParams.getDirectorGivenName();

        //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�J�i�j��
        l_accInfoMobileOfficeInfo.directorFamilyNameKana =
            this.mobileOfficeInfoRegistParams.getDirectorFamilyNameAlt1();

        //�g�єԍ��E�Ζ�����.����ӔC�Җ��i�J�i�j�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Җ��i�J�i�j��
        l_accInfoMobileOfficeInfo.directorNameKana =
            this.mobileOfficeInfoRegistParams.getDirectorGivenNameAlt1();

        //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@�������� = this.�g�єԍ��E�Ζ�����ύX�\���s.����ӔC�ҏ�������
        l_accInfoMobileOfficeInfo.directorDepartment =
            this.mobileOfficeInfoRegistParams.getDirectorDepartment();

        //�g�єԍ��E�Ζ�����.����ӔC�ҁ@@��E = this.�g�єԍ��E�Ζ�����ύX�\���s.����ӔC�Җ�E
        l_accInfoMobileOfficeInfo.directorPosition = this.mobileOfficeInfoRegistParams.getDirectorPost();

        //�g�єԍ��E�Ζ�����.����ӔC�җX�֔ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�җX�֔ԍ�
        l_accInfoMobileOfficeInfo.directorZipCode = this.mobileOfficeInfoRegistParams.getDirectorZipCode();

        //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���P = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�ҏZ���P
        l_accInfoMobileOfficeInfo.directorAddress1 = this.mobileOfficeInfoRegistParams.getDirectorAddress1();

        //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���Q = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�ҏZ���Q
        l_accInfoMobileOfficeInfo.directorAddress2 = this.mobileOfficeInfoRegistParams.getDirectorAddress2();

        //�g�єԍ��E�Ζ�����.����ӔC�ҏZ���R = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�ҏZ���R
        l_accInfoMobileOfficeInfo.directorAddress3 = this.mobileOfficeInfoRegistParams.getDirectorAddress3();

        //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N�����@@�N�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Ґ��N�����N��
        l_accInfoMobileOfficeInfo.directorEraBorn = this.mobileOfficeInfoRegistParams.getDirectorEraBorn();

        //�g�єԍ��E�Ζ�����.����ӔC�Ґ��N���� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�Ґ��N����
        l_accInfoMobileOfficeInfo.directorBornDate = this.mobileOfficeInfoRegistParams.getDirectorBornDate();

        //�g�єԍ��E�Ζ�����.����ӔC�҉�В��ʔԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ӔC�҉�В��ʔԍ�
        l_accInfoMobileOfficeInfo.directorCorpDirect =
            this.mobileOfficeInfoRegistParams.getDirectorCorpTelephone();

        //�g�єԍ��E�Ζ�����.���̑��A����i�g�сA����j = this.�g�єԍ��E�Ζ�����ύX�\���s.���̑��̘A����i�g�сA����j
        l_accInfoMobileOfficeInfo.directorOtherContact = this.mobileOfficeInfoRegistParams.getOtherContact();

        //�g�єԍ��E�Ζ�����.FAX�ԍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.FAX�ԍ�
        l_accInfoMobileOfficeInfo.faxTelephone = this.mobileOfficeInfoRegistParams.getFax();

        //�g�єԍ��E�Ζ�����.�N�� = this.�g�єԍ��E�Ζ�����ύX�\���s.�N��
        l_accInfoMobileOfficeInfo.annualIncomeDiv = this.mobileOfficeInfoRegistParams.getAnnualIncomeDiv();

        //�g�єԍ��E�Ζ�����.���Z���Y�z = this.�g�єԍ��E�Ζ�����ύX�\���s.���Z���Y�z
        l_accInfoMobileOfficeInfo.assetValueDiv = this.mobileOfficeInfoRegistParams.getAssetValueDiv();

        //�g�єԍ��E�Ζ�����.�^�p�\��z = this.�g�єԍ��E�Ζ�����ύX�\���s.�^�p�\��z
        l_accInfoMobileOfficeInfo.fundBundgetAmountDiv =
            this.mobileOfficeInfoRegistParams.getFundBudgetAmountDiv();

        //�g�єԍ��E�Ζ�����.�����ړI = this.�g�єԍ��E�Ζ�����ύX�\���s.�����ړI
        l_accInfoMobileOfficeInfo.investPurposeDiv = this.mobileOfficeInfoRegistParams.getInvestPurposeDiv();

        //�g�єԍ��E�Ζ�����.�����\����� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����\�����
        l_accInfoMobileOfficeInfo.investPlanPeriodDiv =
            this.mobileOfficeInfoRegistParams.getInvestPlanPeriodDiv();

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�P�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag1IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag1 = this.mobileOfficeInfoRegistParams.getExperienceFlag1() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�Q�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�Q�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag2IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag2 = this.mobileOfficeInfoRegistParams.getExperienceFlag2() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�R�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�R�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag3IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag3 = this.mobileOfficeInfoRegistParams.getExperienceFlag3() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�S�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�S�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag4IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag4 = this.mobileOfficeInfoRegistParams.getExperienceFlag4() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�T�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�T�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag5IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag5 = this.mobileOfficeInfoRegistParams.getExperienceFlag5() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�U�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�U�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag6IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag6 = this.mobileOfficeInfoRegistParams.getExperienceFlag6() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�V�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�V�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag7IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag7 = this.mobileOfficeInfoRegistParams.getExperienceFlag7() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�W�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�W�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag8IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag8 = this.mobileOfficeInfoRegistParams.getExperienceFlag8() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�X�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�X�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag9IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag9 = this.mobileOfficeInfoRegistParams.getExperienceFlag9() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���̗L���i�P�O�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���̗L���i�P�O�j
        if (!this.mobileOfficeInfoRegistParams.getExperienceFlag10IsNull())
        {
            l_accInfoMobileOfficeInfo.experienceFlag10 =
                this.mobileOfficeInfoRegistParams.getExperienceFlag10() + "";
        }

        //�g�єԍ��E�Ζ�����.�����o���i�P�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�P�j
        l_accInfoMobileOfficeInfo.experienceDiv1 = this.mobileOfficeInfoRegistParams.getExperienceDiv1();

        //�g�єԍ��E�Ζ�����.�����o���i�Q�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�Q�j
        l_accInfoMobileOfficeInfo.experienceDiv2 = this.mobileOfficeInfoRegistParams.getExperienceDiv2();

        //�g�єԍ��E�Ζ�����.�����o���i�R�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�R�j
        l_accInfoMobileOfficeInfo.experienceDiv3 = this.mobileOfficeInfoRegistParams.getExperienceDiv3();

        //�g�єԍ��E�Ζ�����.�����o���i�S�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�S�j
        l_accInfoMobileOfficeInfo.experienceDiv4 = this.mobileOfficeInfoRegistParams.getExperienceDiv4();

        //�g�єԍ��E�Ζ�����.�����o���i�T�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�T�j
        l_accInfoMobileOfficeInfo.experienceDiv5 = this.mobileOfficeInfoRegistParams.getExperienceDiv5();

        //�g�єԍ��E�Ζ�����.�����o���i�U�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�U�j
        l_accInfoMobileOfficeInfo.experienceDiv6 = this.mobileOfficeInfoRegistParams.getExperienceDiv6();

        //�g�єԍ��E�Ζ�����.�����o���i�V�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�V�j
        l_accInfoMobileOfficeInfo.experienceDiv7 = this.mobileOfficeInfoRegistParams.getExperienceDiv7();

        //�g�єԍ��E�Ζ�����.�����o���i�W�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�W�j
        l_accInfoMobileOfficeInfo.experienceDiv8 = this.mobileOfficeInfoRegistParams.getExperienceDiv8();

        //�g�єԍ��E�Ζ�����.�����o���i�X�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�X�j
        l_accInfoMobileOfficeInfo.experienceDiv9 = this.mobileOfficeInfoRegistParams.getExperienceDiv9();

        //�g�єԍ��E�Ζ�����.�����o���i�P�O�j = this.�g�єԍ��E�Ζ�����ύX�\���s.�����o���i�P�O�j
        l_accInfoMobileOfficeInfo.experienceDiv10 = this.mobileOfficeInfoRegistParams.getExperienceDiv10();

        //�g�єԍ��E�Ζ�����.����̎�ށi�P�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�P�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag1IsNull())
        {
            l_accInfoMobileOfficeInfo.interest1 = this.mobileOfficeInfoRegistParams.getInterestFlag1() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�Q�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�Q�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag2IsNull())
        {
            l_accInfoMobileOfficeInfo.interest2 = this.mobileOfficeInfoRegistParams.getInterestFlag2() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�R�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�R�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag3IsNull())
        {
            l_accInfoMobileOfficeInfo.interest3 = this.mobileOfficeInfoRegistParams.getInterestFlag3() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�S�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�S�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag4IsNull())
        {
            l_accInfoMobileOfficeInfo.interest4 = this.mobileOfficeInfoRegistParams.getInterestFlag4() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�T�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�T�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag5IsNull())
        {
            l_accInfoMobileOfficeInfo.interest5 = this.mobileOfficeInfoRegistParams.getInterestFlag5() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�U�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�U�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag6IsNull())
        {
            l_accInfoMobileOfficeInfo.interest6 = this.mobileOfficeInfoRegistParams.getInterestFlag6() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�V�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�V�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag7IsNull())
        {
            l_accInfoMobileOfficeInfo.interest7 = this.mobileOfficeInfoRegistParams.getInterestFlag7() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�W�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�W�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag8IsNull())
        {
            l_accInfoMobileOfficeInfo.interest8 = this.mobileOfficeInfoRegistParams.getInterestFlag8() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�X�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�X�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag9IsNull())
        {
            l_accInfoMobileOfficeInfo.interest9 = this.mobileOfficeInfoRegistParams.getInterestFlag9() + "";
        }

        //�g�єԍ��E�Ζ�����.����̎�ށi�P�O�j = this.�g�єԍ��E�Ζ�����ύX�\���s.����̎�ށi�P�O�j
        if (!this.mobileOfficeInfoRegistParams.getInterestFlag10IsNull())
        {
            l_accInfoMobileOfficeInfo.interest10 = this.mobileOfficeInfoRegistParams.getInterestFlag10() + "";
        }

        //�g�єԍ��E�Ζ�����.�����J�݂̓��@@ = this.�g�єԍ��E�Ζ�����ύX�\���s.�����J�݂̓��@@
        l_accInfoMobileOfficeInfo.appliMotivatDiv = this.mobileOfficeInfoRegistParams.getAppliMotivatDiv();

        //�g�єԍ��E�Ζ�����.�����J�݂̓��@@�̏ڍ� = this.�g�єԍ��E�Ζ�����ύX�\���s.�����J�݂̓��@@�̏ڍ�
        l_accInfoMobileOfficeInfo.appliMotivatDetail = this.mobileOfficeInfoRegistParams.getAppliMotivatDivDetail();

        //�g�єԍ��E�Ζ�����.���ݗ��p���Ă���،���� = this.�g�єԍ��E�Ζ�����ύX�\���s.���ݗ��p���Ă���،����
        l_accInfoMobileOfficeInfo.useInstitutionDiv = this.mobileOfficeInfoRegistParams.getUseInstitutionDiv();

        //�g�єԍ��E�Ζ�����.�C���^�[�l�b�g����敪 = this.�g�єԍ��E�Ζ�����ύX�\���s.�C���^�[�l�b�g����敪
        l_accInfoMobileOfficeInfo.internetTradeDiv = this.mobileOfficeInfoRegistParams.getInternetTradeDiv();

        //�g�єԍ��E�Ζ�����.�Љ�x�X = this.�g�єԍ��E�Ζ�����ύX�\���s.�Љ�x�X
        l_accInfoMobileOfficeInfo.introduceBranch = this.mobileOfficeInfoRegistParams.getIntroduceBranchCode();

        //�S�j�@@�P�j�Ő��������I�u�W�F�N�g.�Ζ����� = ���������g�єԍ��E�Ζ�����I�u�W�F�N�g
        l_changeAccount.mobileOfficeInfo = l_accInfoMobileOfficeInfo;

        //�T�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g��ԋp�B
        log.exiting(STR_METHOD_NAME);
        return l_changeAccount;
    }

    /**
     * (get�����敪���List)<BR>
     * �����̌����敪�ƍ��v����������List�𐶐����A�ԋp����B <BR>
     * <BR>
     * �P�j�@@ArrayList�I�u�W�F�N�g�̐��� <BR>
     * <BR>
     * �Q�j�@@�g�єԍ��E�Ζ�����ύX�\��List�̗v�f���ALoop�������s���B <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g.get�ڋq�i�j�ŃI�u�W�F�N�g���擾 <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����敪 = 0�F�l�����̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�ڋq�I�u�W�F�N�gis�@@�l�i�j = false �ł���Όg�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g��add() <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�����敪 = 1�F�@@�l�����̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�ڋq�I�u�W�F�N�gis�@@�l�i�j = true �ł���Όg�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g��add() <BR>
     * <BR>
     * �R�j�@@ArrayList�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lisAccInfoMobileOfficeInfoRegist - (�g�єԍ��E�Ζ�����ύX�\��List)<BR>
     * �g�єԍ��E�Ζ�����ύX�\��List�B
     * @@param l_strAccInfoTaxType - (�����敪)<BR>
     * �����敪 <BR>
     * <BR>
     * 0 �F �l���� <BR>
     * 1 �F �@@�l����
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getAccountTypeInfoList(
        List l_lisAccInfoMobileOfficeInfoRegist,
        String l_strAccInfoTaxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getAccountTypeInfoList(List, String)";
        log.entering(STR_METHOD_NAME);

        if (l_lisAccInfoMobileOfficeInfoRegist == null || l_lisAccInfoMobileOfficeInfoRegist.isEmpty())
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME,
                "[l_lisAccInfoMobileOfficeInfoRegist] = " + l_lisAccInfoMobileOfficeInfoRegist
                );
        }

        //�P�j�@@ArrayList�I�u�W�F�N�g�̐���
        List l_lisAccInfoTaxTypes = new ArrayList();

        //�Q�j�@@�g�єԍ��E�Ζ�����ύX�\��List�̗v�f���ALoop�������s���B
        int l_intIndex = l_lisAccInfoMobileOfficeInfoRegist.size();
        for (int i = 0; i < l_intIndex; i++)
        {
            WEB3AccInfoMobileOfficeInfoRegist l_accInfoMobileOfficeInfoRegist =
                (WEB3AccInfoMobileOfficeInfoRegist)l_lisAccInfoMobileOfficeInfoRegist.get(i);
            //�Q�|�P�j�@@�g�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g.get�ڋq�i�j�ŃI�u�W�F�N�g���擾
            WEB3GentradeMainAccount l_mainAccount =
                l_accInfoMobileOfficeInfoRegist.getMainAccount();

            //�Q�|�Q�j�@@�����敪 = 0�F�l�����̏ꍇ�A
            //�ڋq�I�u�W�F�N�gis�@@�l�i�j = false �ł���Όg�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g��add()
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_strAccInfoTaxType))
            {
                if (!l_mainAccount.isCorporation())
                {
                    l_lisAccInfoTaxTypes.add(l_accInfoMobileOfficeInfoRegist);
                }
            }

            //�Q�|�R�j�@@�����敪 = 1�F�@@�l�����̏ꍇ�A
            //�ڋq�I�u�W�F�N�gis�@@�l�i�j = true �ł���Όg�єԍ��E�Ζ�����ύX�\���I�u�W�F�N�g��add()
            else if (WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_strAccInfoTaxType))
            {
                if (l_mainAccount.isCorporation())
                {
                    l_lisAccInfoTaxTypes.add(l_accInfoMobileOfficeInfoRegist);
                }
            }
        }

        //�R�j�@@ArrayList�I�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisAccInfoTaxTypes;
    }

    /**
     * (get�ύX�\���ڋq)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g�𐶐����A�l��ԋp����B <BR>
     * <BR>
     * �P�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g�𐶐� <BR>
     * <BR>
     * �Q�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B  <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.�\���� = this.get�\������() <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.���X�R�[�h = this.get���X().getBranchCode() <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.�ڋq�R�[�h =�@@this.get�ڋq().get�\���ڋq�R�[�h() <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.�ڋq�� = this.get�ڋq().get�ڋq�\����() <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.�\���҃R�[�h = this.�g�єԍ��E�Ζ�����ύX�\���s.�\���҃R�[�h <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.����� = this.get�������() <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.����҃R�[�h =�@@this.�g�єԍ��E�Ζ�����ύX�\���s.����҃R�[�h <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.�\���󋵋敪 = this.get�\���󋵋敪() <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.���茋�� = this.�g�єԍ��E�Ζ�����ύX�\���s.���茋�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@== 0�F"DEFAULT"�̏ꍇ�Anull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A�g�єԍ��E�Ζ�����ύX�\��.���茋�� <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.�����敪 = this.get�ڋq().is�@@�l() = true �̏ꍇ�A1�F"�@@�l����" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ = this.get�ڋq().is�@@�l() = false �̏ꍇ�A0�F"�l����" <BR>
     * �@@�@@�@@�g�єԍ��E�Ζ�����ύX�\���ڋq.��t���� =�@@this.�g�єԍ��E�Ζ�����ύX�\���s.��t����<BR>
     * <BR>
     * <BR>
     * �R�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g��ԋp <BR>
     * @@return WEB3AccInfoMobileOfficeChangeAccount
     * @@throws WEB3BaseException
     */
    public WEB3AccInfoMobileOfficeChangeAccount getChangeRegistAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getChangeRegistAccount()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g�𐶐�
        WEB3AccInfoMobileOfficeChangeAccount l_changeAccount =
            new WEB3AccInfoMobileOfficeChangeAccount();

        //�Q�j�@@�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //�g�єԍ��E�Ζ�����ύX�\���ڋq.�\���� = this.get�\������()
        l_changeAccount.applyDate = this.getRegistTime();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.���X�R�[�h = this.get���X().getBranchCode()
        l_changeAccount.branchCode = this.getBranch().getBranchCode();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.�ڋq�R�[�h =�@@this.get�ڋq().get�\���ڋq�R�[�h()
        l_changeAccount.accountCode = this.getMainAccount().getDisplayAccountCode();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.�ڋq�� = this.get�ڋq().get�ڋq�\����()
        l_changeAccount.accountName = this.getMainAccount().getDisplayAccountName();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.�\���҃R�[�h =
        //       this.�g�єԍ��E�Ζ�����ύX�\���s.�\���҃R�[�h
        l_changeAccount.updaterCode = this.mobileOfficeInfoRegistParams.getRegistUpdater();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.����� = this.get�������()
        l_changeAccount.judgementDate = this.getDecisionTime();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.����҃R�[�h =
        //       this.�g�єԍ��E�Ζ�����ύX�\���s.����҃R�[�h
        l_changeAccount.judgeCode = this.mobileOfficeInfoRegistParams.getDecisionUpdater();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.�\���󋵋敪 = this.get�\���󋵋敪()
        l_changeAccount.applyStateDiv = this.getRegistStateDiv();

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.���茋�� =
        //       this.�g�єԍ��E�Ζ�����ύX�\���s.���茋�� == 0�F"DEFAULT"�̏ꍇ�Anull
        //       �ȊO�A�g�єԍ��E�Ζ�����ύX�\��.���茋��
        if (WEB3JudgmentResultDivDef.DEFAULT.equals(this.mobileOfficeInfoRegistParams.getDecision() + ""))
        {
            l_changeAccount.judgmentResultDiv = null;
        }
        else
        {
            l_changeAccount.judgmentResultDiv = this.mobileOfficeInfoRegistParams.getDecision() + "";
        }

        //�g�єԍ��E�Ζ�����ύX�\���ڋq.�����敪 =
        //       this.get�ڋq().is�@@�l() = true �̏ꍇ�A1�F"�@@�l����"
        //       = this.get�ڋq().is�@@�l() = false �̏ꍇ�A0�F"�l����"
        if (this.getMainAccount().isCorporation())
        {
            l_changeAccount.accountType = WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT;
        }
        else
        {
            l_changeAccount.accountType = WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT;
        }
        
        //�g�єԍ��E�Ζ�����ύX�\���ڋq.��t���� =�@@this.�g�єԍ��E�Ζ�����ύX�\���s.��t����
        l_changeAccount.acceptedResult = mobileOfficeInfoRegistParams.getAcceptStatus();

        //�R�j�@@�g�єԍ��E�Ζ�����ύX�\���ڋq�I�u�W�F�N�g��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_changeAccount;
    }
}
@
