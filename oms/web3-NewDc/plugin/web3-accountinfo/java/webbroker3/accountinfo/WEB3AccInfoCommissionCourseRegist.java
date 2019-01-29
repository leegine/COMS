head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseRegist.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ϑ��萔���R�[�X�ύX�\��(WEB3AccInfoCommissionCourseRegist)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 ����� (���u) �V�K�쐬
                   2006/06/30 ������ (���u) �d�l�ύX�Ǘ�No.112
*/

package webbroker3.accountinfo;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.data.CommissionCourseRegistDao;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.CommissionCourseRegistRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ϑ��萔���R�[�X�ύX�\��)<BR>
 * �ϑ��萔���R�[�X�ύX�\���N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseRegist implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseRegist.class);

    /**
     * (�ϑ��萔���R�[�X�ύX�\���s)<BR>
     * �ϑ��萔���R�[�X�ύX�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �ϑ��萔���R�[�X�ύX�\��Params�N���X��DDL��莩����������B<BR>
     */
    private CommissionCourseRegistParams commissionCourseRegistParams;

    /**
     * (�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �w�肵���ϑ��萔���R�[�X�ύX�\���h�c�ɊY������s��<BR>
     * �ϑ��萔���R�[�X�ύX�\���e�[�u����茟������B <BR>
     * �������ʂ̈ϑ��萔���R�[�X�ύX�\���s�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     * @@param l_lngCommissionCourseRegistID - �ϑ��萔���R�[�X�ύX�\���h�c
     *
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist
     * @@roseuid 413EBFFF037F
     */
    public WEB3AccInfoCommissionCourseRegist(long l_lngCommissionCourseRegistID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3AccInfoCommissionCourseRegist(long)";
        log.entering(STR_METHOD_NAME);

        //�،���ЃR�[�h�C�萔�����i�R�[�h�C�萔���R�[�X�R�[�h�ɊY������s���A
        //�ϑ��萔���R�[�X�}�X�^�e�[�u����茟������B
        try
        {
            CommissionCourseRegistRow l_commissionCourseRegistRow =
                CommissionCourseRegistDao.findRowByPk(l_lngCommissionCourseRegistID);

            this.commissionCourseRegistParams = (CommissionCourseRegistParams)l_commissionCourseRegistRow;

        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �s�w��s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g���A�C���X�^���X�𐶐�����B <BR>
     * @@param l_commissionCourseRegistParams - �ϑ��萔���R�[�X�ύX�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * ���@@�ϑ��萔���R�[�X�ύX�\��Params��DDL��莩����������B<BR>
     * @@roseuid 413D999E0057
     */
    public WEB3AccInfoCommissionCourseRegist(CommissionCourseRegistParams l_commissionCourseRegistParams)
    {
        this.commissionCourseRegistParams = l_commissionCourseRegistParams;
    }

    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.�ϑ��萔���R�[�X�ύX�\���s��ԋp����B <BR>
     * @@return Object
     * @@roseuid 413D6DBA0151
     */
    public Object getDataSourceObject()
    {
        return this.commissionCourseRegistParams;
    }

    /**
     * �@@this.�ϑ��萔���R�[�X�ύX�\���s���R�s�[���āA�������e�̕ʃC���X�^���X<BR>
     * ���쐬����iclone�j�B <BR>
     * �쐬�����R�s�[�����g��this.�ϑ��萔���R�[�X�ύX�\���s�ɃZ�b�g����B <BR>
     * @@roseuid 413D97E40364
     */
    public void createForUpdateParams()
    {
        CommissionCourseRegistParams l_params = new CommissionCourseRegistParams(this.commissionCourseRegistParams);
        this.commissionCourseRegistParams = l_params;
    }

    /**
     * (get���X)<BR>
     * ���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * this.�ϑ��萔���ύX�\���s.���X�h�c�ɊY�����镔�X�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GentradeBranch
     * @@roseuid 413E8E68017C
     */
    public WEB3GentradeBranch getBranch() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranch()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngBranchId = this.commissionCourseRegistParams.getBranchId();
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
     * (get�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * this.�ϑ��萔���ύX�\���s.�����h�c�ɊY������ڋq�I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GentradeMainAccount
     * @@roseuid 413E8E7A0312
     */
    public WEB3GentradeMainAccount getMainAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMainAccount()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngAccountId = this.commissionCourseRegistParams.getAccountId();
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
     * (get�萔�����i�R�[�h)<BR>
     * �萔�����i�R�[�h���擾����B<BR>
     * <BR>
     * this.�ϑ��萔���ύX�\���s.�萔�����i�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41511B3001D2
     */
    public String getCommissionProductCode()
    {
        return this.commissionCourseRegistParams.getCommProductCode();
    }

    /**
     * (get�萔���R�[�X�R�[�h)<BR>
     * �萔���R�[�X�R�[�h���擾����B<BR>
     * <BR>
     * this.�ϑ��萔���ύX�\���s.�萔���R�[�X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 413E8EF70082
     */
    public String getCommissionCourseCode()
    {
        return this.commissionCourseRegistParams.getCommissionCourseDiv();
    }

    /**
     * (get�\����)<BR>
     * �\�������擾����B<BR>
     * <BR>
     * this.�ϑ��萔���ύX�\���s.�\�������̓��t�����iYYYYMMDD�j��ԋp����B<BR>
     * @@return Date
     * @@roseuid 413E8F430285
     */
    public Date getRegistDate()
    {
        return WEB3DateUtility.toDay(this.commissionCourseRegistParams.getRegistTimestamp());
    }

    /**
     * (get�K�p�J�n��)<BR>
     * �K�p�J�n�����擾����B<BR>
     * <BR>
     * this.�ϑ��萔���ύX�\���s.�K�p�J�n�����̓��t�����iYYYYMMDD�j��ԋp����B<BR>
     * @@return Date
     * @@roseuid 413E8F710063
     */
    public Date getAppliStartDate()
    {
        return WEB3DateUtility.toDay(this.commissionCourseRegistParams.getAppliStartDatetime());
    }

    /**
     * (get�K�p�I����)<BR>
     * �K�p�I�������擾����B<BR>
     * <BR>
     * this.�ϑ��萔���ύX�\���s.�K�p�I�������̓��t�����iYYYYMMDD�j��ԋp����B<BR>
     * @@return Date
     * @@roseuid 413E8F84014D
     */
    public Date getAppliEndDate()
    {
        return WEB3DateUtility.toDay(this.commissionCourseRegistParams.getAppliEndDatetime());
    }

    /**
     * (is����\)<BR>
     * ����\���𔻒肷��B<BR>
     * <BR>
     * �P�j�@@�폜�ς݂��̔���<BR>
     * �@@�|�ithis.�ϑ��萔���ύX�\���s.�폜�t���O == BooleanEnum.TRUE�j�̏ꍇ�A<BR>
     * false��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���ؓ����ɂ�锻��<BR>
     * �@@�|�ithis.�ϑ��萔���ύX�\���s.�ύX�\�����ؓ��� > ��������(*1)�j�̏ꍇtrue�C<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �@@(*1) ��������<BR>
     * �@@TradingSystem.getSystemTimestamp()<BR>
     * @@return boolean
     * @@roseuid 413DA8BD0345
     */
    public boolean isCancelPossible()
    {
        //�폜�ς݂��̔���
        BooleanEnum l_deleteFlag = this.commissionCourseRegistParams.getDeleteFlag();

        //�ithis.�ϑ��萔���ύX�\���s.�폜�t���O == BooleanEnum.TRUE�j�̏ꍇ�Afalse��ԋp����B
        if (BooleanEnum.TRUE.equals(l_deleteFlag))
        {
            return false;
        }

        //���ؓ����ɂ�锻��

        //��������
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();
        //�ύX�\�����ؓ���
        Date l_datRegistEndDate = this.commissionCourseRegistParams.getRegistEndTimestamp();

        //�ithis.�ϑ��萔���ύX�\���s.�ύX�\�����ؓ��� > ��������(*1)�j�̏ꍇtrue
        if (WEB3DateUtility.compare(l_datRegistEndDate, l_datProcessDate) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * (is�_�E�����[�h��)<BR>
     * �_�E�����[�h�ς��𔻒肷��B<BR>
     * <BR>
     * �ithis.�ϑ��萔���ύX�\���s.�_�E�����[�h�σt���O == BooleanEnum.TRUE�j�̏ꍇtrue�C<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 413DA971021C
     */
    public boolean isDownloaded()
    {
        BooleanEnum l_downloadFlag = this.commissionCourseRegistParams.getDownloadFlag();

        if (BooleanEnum.TRUE.equals(l_downloadFlag))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (createNew�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �ϑ��萔���R�[�X�ύX�\���V�K�s�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g����<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@���ϑ��萔���R�[�X�ύX�\��Params��DDL��莩����������B<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B <BR>
     * �@@�P�j�Ő��������ϑ��萔���R�[�X�ύX�\��Params�I�u�W�F�N�g�̃v���p�e�B�ɁA<BR>
     * �ȉ��̒ʂ�Z�b�g���s���B <BR>
     * <BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�ϑ��萔���R�[�X�ύX�\���h�c = <BR>
     * �V�K�̔�(*1)<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�،���ЃR�[�h = <BR>
     * �ϑ��萔���R�[�X�}�X�^.get�،���ЃR�[�h()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.���XID = <BR>
     * �ڋq.getBranch().getBranchId()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.����ID = <BR>
     * �ڋq.getAccountId()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�萔�����i�R�[�h = <BR>
     * �ϑ��萔���R�[�X�}�X�^.get�萔�����i�R�[�h()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�K�p�J�n���� = <BR>
     * �ϑ��萔���R�[�X�}�X�^.get�K�p�J�n����()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�K�p�I������ = <BR>
     * �ϑ��萔���R�[�X�}�X�^.get�K�p�I������()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�萔���R�[�X�R�[�h = <BR>
     * �ϑ��萔���R�[�X�}�X�^.get�萔���R�[�X�R�[�h()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�\������ = <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�ύX�\�����ؓ��� = <BR>
     * �ϑ��萔���R�[�X�}�X�^.get�ύX�\�����ؓ���()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�_�E�����[�h�σt���O = <BR>
     * BooleanEnum.FALSE<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�폜�t���O = <BR>
     * BooleanEnum.FALSE<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�X�V�҃R�[�h = <BR>
     * �ڋq.getAccountCode()<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�쐬���� = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��Params.�X�V���� = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * �@@(*1) �ϑ��萔���R�[�X�ύX�\���h�c�̐V�K�̔� <BR>
     * �@@�ϑ��萔���R�[�X�ύX�\��DAO.newPkValue()���\�b�h�ɂĎ擾����B <BR>
     * �@@�� �ϑ��萔���R�[�X�ύX�\��DAO�N���X��DDL��莩����������B <BR>
     * <BR>
     * �R�j�@@�ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�ԋp<BR>
     * �@@�s�I�u�W�F�N�g���w�肵�A�ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�𐶐����ԋp����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\���s�F�@@�i�Q�j�Ő��������s�I�u�W�F�N�g�j<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     *
     * @@param l_commissionCourseMaster - �ϑ��萔���R�[�X�}�X�^�I�u�W�F�N�g
     *
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist
     * @@roseuid 413D991B01ED
     */
    public static WEB3AccInfoCommissionCourseRegist createNewCommissionCourseRegist(WEB3GentradeMainAccount l_mainAccount, WEB3AccInfoCommissionCourseMaster l_commissionCourseMaster) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createNewCommissionCourseRegist(WEB3GentradeMainAccount, WEB3AccInfoCommissionCourseMaster)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_commissionCourseMaster == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount + "[l_commissionCourseMaster] = " + l_commissionCourseMaster
                );
        }

        CommissionCourseRegistParams l_params = new CommissionCourseRegistParams();

        //��������
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();

        long l_lngNewId;
        try
        {
            l_lngNewId = CommissionCourseRegistDao.newPkValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�ϑ��萔���R�[�X�ύX�\��Params.�ϑ��萔���R�[�X�ύX�\���h�c = �V�K�̔�(*1)
        l_params.setCommissionCourseRegistId(l_lngNewId);

        //�ϑ��萔���R�[�X�ύX�\��Params.�،���ЃR�[�h = �ϑ��萔���R�[�X�}�X�^.get�،���ЃR�[�h()
        l_params.setInstitutionCode(l_commissionCourseMaster.getInstitutionCode());

        //�ϑ��萔���R�[�X�ύX�\��Params.���XID = �ڋq.getBranch().getBranchId()
        l_params.setBranchId(l_mainAccount.getBranch().getBranchId());

        //�ϑ��萔���R�[�X�ύX�\��Params.����ID = �ڋq.getAccountId()
        l_params.setAccountId(l_mainAccount.getAccountId());

        //�ϑ��萔���R�[�X�ύX�\��Params.�萔�����i�R�[�h = �ϑ��萔���R�[�X�}�X�^.get�萔�����i�R�[�h()
        l_params.setCommProductCode(l_commissionCourseMaster.getCommissionProductCode());

        //�ϑ��萔���R�[�X�ύX�\��Params.�K�p�J�n���� = �ϑ��萔���R�[�X�}�X�^.get�K�p�J�n����()
        l_params.setAppliStartDatetime(l_commissionCourseMaster.getAppliStartTimestamp());

        //�ϑ��萔���R�[�X�ύX�\��Params.�K�p�I������ = �ϑ��萔���R�[�X�}�X�^.get�K�p�I������()
        l_params.setAppliEndDatetime(l_commissionCourseMaster.getAppliEndTimestamp());

        //�ϑ��萔���R�[�X�ύX�\��Params.�萔���R�[�X�R�[�h = �ϑ��萔���R�[�X�}�X�^.get�萔���R�[�X�R�[�h()
        l_params.setCommissionCourseDiv(l_commissionCourseMaster.getCommissionCourseCode());

        //�ϑ��萔���R�[�X�ύX�\��Params.�\������ = TradingSystem.getSystemTimestamp()
        l_params.setRegistTimestamp(l_datProcessDate);

        //�ϑ��萔���R�[�X�ύX�\��Params.�ύX�\�����ؓ��� = �ϑ��萔���R�[�X�}�X�^.get�ύX�\�����ؓ���()
        l_params.setRegistEndTimestamp(l_commissionCourseMaster.getRegistEndTimestamp());

        //�ϑ��萔���R�[�X�ύX�\��Params.�_�E�����[�h�σt���O = BooleanEnum.FALSE
        l_params.setDownloadFlag(BooleanEnum.FALSE);

        //�ϑ��萔���R�[�X�ύX�\��Params.�폜�t���O = BooleanEnum.FALSE
        l_params.setDeleteFlag(BooleanEnum.FALSE);

        //�ϑ��萔���R�[�X�ύX�\��Params.�X�V�҃R�[�h = �ڋq.getAccountCode()
        l_params.setLastUpdater(l_mainAccount.getAccountCode());

        //�ϑ��萔���R�[�X�ύX�\��Params.�쐬���� = TradingSystem.getSystemTimestamp()
        l_params.setCreatedTimestamp(l_datProcessDate);

        //�ϑ��萔���R�[�X�ύX�\��Params.�X�V���� = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_datProcessDate);

        log.exiting(STR_METHOD_NAME);

        //�s�I�u�W�F�N�g���w�肵�A�ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�𐶐����ԋp����B
        return new WEB3AccInfoCommissionCourseRegist(l_params);
    }

    /**
     * (get�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �ڋq�ɊY�����銔���ϑ��萔���R�[�X�ύX�\�����擾����B<BR>
     * <BR>
     * �P�j�@@�ϑ��萔���R�[�X�ύX�\���e�[�u������<BR>
     * �@@�ȉ��̏����ŁA�ϑ��萔���R�[�X�ύX�\���e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And<BR>
     * �@@���XID = �ڋq.getBranch().getBranchId() And<BR>
     * �@@����ID = �ڋq.getAccountId() And<BR>
     * �@@�萔�����i�R�[�h = �萔�����i�R�[�h And<BR>
     * �@@�K�p�J�n���� > ��������(*1) And<BR>
     * �@@�폜�t���O = BooleanEnum.FALSE<BR>
     * <BR>
     * �@@[�擾��]<BR>
     *�@@�K�p�J�n�����@@�����i�Fasc�j<BR>
     * <BR>
     * �@@(*1) ��������<BR>
     * �@@TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �Q�j�@@�ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�ԋp<BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ��āA�ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g<BR>
     * �𐶐����z��ŕԋp����B<BR>
     * �@@�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     *
     * @@param l_strCommissionProductCode - �萔�����i�R�[�h
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist[]
     * @@roseuid 413DA53E0141
     */
    public static WEB3AccInfoCommissionCourseRegist[] getCommissionCourseRegist(WEB3GentradeMainAccount l_mainAccount, String l_strCommissionProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommissionCourseRegist(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }

        List l_lisRecords = null;

        try
        {

            //�ȉ��̏����ŁA�ϑ��萔���R�[�X�ύX�\���e�[�u������������B

            //[����]
            //�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() And
            //���XID = �ڋq.getBranch().getBranchId() And
            //����ID = �ڋq.getAccountId() And
            //�萔�����i�R�[�h = �萔�����i�R�[�h And
            //�K�p�J�n���� > ��������(*1) And
            //�폜�t���O = BooleanEnum.FALSE

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //��������
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ?");
            l_sbQueryString.append(" and branch_id = ?");
            l_sbQueryString.append(" and account_id = ?");
            l_sbQueryString.append(" and comm_product_code = ?");
            l_sbQueryString.append(" and appli_start_datetime > ?");
            l_sbQueryString.append(" and delete_flag = ?");


            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionCode(),
                "" + l_mainAccount.getBranch().getBranchId(),
                "" + l_mainAccount.getAccountId(),
                l_strCommissionProductCode,
                l_datProcessDate,
                BooleanEnum.FALSE
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseRegistRow.TYPE,
                l_sbQueryString.toString(),
                "appli_start_datetime ASC",
                null,
                l_queryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists =
            new WEB3AccInfoCommissionCourseRegist[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_commissionCourseRegists[i] =
                new WEB3AccInfoCommissionCourseRegist((CommissionCourseRegistParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseRegists;
    }

    /**
     * (get�ϑ��萔���R�[�X�ύX�\��)<BR>
     * �istatic ���\�b�h�j<BR>
     * �w��ɊY������ϑ��萔���ύX�\���I�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �P�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�ϑ��萔���ύX�\���s<BR>
     * �I�u�W�F�N�g��List���擾����B <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����]<BR>
     * �@@�@@rowType�F�@@�ϑ��萔���ύX�\��Row.TYPE<BR>
     * �@@�@@where�F�@@��������������<BR>
     * �@@�@@orderBy�F�@@�\�[�g����<BR>
     * �@@�@@conditions�F�@@null<BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i<BR>
     * <BR>
     * �Q�j�@@�������ʂ̍s�I�u�W�F�N�g�ňϑ��萔���ύX�\���I�u�W�F�N�g�𐶐����A<BR>
     * List�ŕԋp����B <BR>
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return List
     * @@roseuid 415111250145
     */
    public static List getCommissionCourseRegist(String l_strQueryString, String[] l_queryContainer, String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommissionCourseRegist(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {

            //QueryProcessor.doFindAllQuery( )�ɂ��A�ϑ��萔���ύX�\���s�I�u�W�F�N�g��List���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseRegistRow.TYPE,
                l_strQueryString,
                l_strSortCond,
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
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        List l_lisCommissionCourseRegists = new Vector();

        for (int i = 0; i < l_intSize; i++)
        {
            l_lisCommissionCourseRegists.add(new WEB3AccInfoCommissionCourseRegist((CommissionCourseRegistParams)l_lisRecords.get(i)));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisCommissionCourseRegists;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * this.�ϑ��萔���R�[�X�}�X�^�s.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String <BR>
     * @@roseuid 413DA9DF0193<BR>
     */
    public String getInstitutionCode()
    {
        return this.commissionCourseRegistParams.getInstitutionCode();
    }
}
@
