head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����v�Z�T�[�r�X(WEB3IfoDepositCalcServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/10/26 �R�c�@@��i(FLJ) �V�K�쐬
Revesion History : 2007/06/28 hijikata(SRA) �[��Ή� ���f��No.057, No.081
Revision History : 2007/10/18 k.yamashita(SRA)  ���捞�v��No.021
*/
package webbroker3.ifodeposit;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3IfodepositNonCalcSqProductDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����v�Z�T�[�r�X)<BR>
 * �؋����v�Z�T�[�r�X�N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositCalcServiceImpl implements WEB3IfoDepositCalcService
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositCalcServiceImpl.class);

    /**
     * (�؋����v�Z�T�[�r�X)<BR>
     * 
     * �R���X�g���N�^�B<BR>
     * @@roseuid 41136054020C
     */
    public WEB3IfoDepositCalcServiceImpl()
    {

    }

    /**
     * (get�؋����v�Z)<BR>
     * 
     * �؋����v�Z�𐶐����A�ԋp����B<BR>
     * 
     * �P�j�@@this.create�؋����v�Z����(����.�⏕����)���R�[�����A�؋����v�Z�����𐶐���
     * ��B<BR>
     * 
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�⏕�����F�@@����.�⏕����<BR>
     * 
     * �Q�j�@@�؋����v�Z�I�u�W�F�N�g(�⏕�����A �؋����v�Z����)�𐶐�����B<BR>
     * 
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�⏕�����F�@@����.�⏕����<BR>
     * �@@�@@�@@�؋����v�Z�����F�@@�P�j�Ő��������؋����v�Z����<BR>
     * 
     * �R�j�@@�Q�j�Ő��������؋����v�Z�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_subAcount - (�⏕����)<BR>
     * 
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     * @@roseuid 410F6434019F
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getIfoDepositCalc(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_condition = createIfoDepositCalcCondition(l_subAccount);
            WEB3IfoDepositCalc l_ifoCalc = new WEB3IfoDepositCalc(l_subAccount, l_condition);
            
            log.exiting(STR_METHOD_NAME);
            return l_ifoCalc;

        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (get�؋����v�Z)<BR>
     * 
     * ����̒��������f���ꂽ�؋����v�Z�𐶐����A�ԋp����B<BR>
     * �i�V�K���]�̓`�F�b�N���Ɏg�p�j<BR>
     * 
     * �P�j�@@this.create�؋����v�Z����(�⏕����)���R�[�����A�؋����v�Z�����𐶐�����B<
     * BR>
     * 
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�⏕�����F�@@����.�⏕����<BR>
     * 
     * �Q�j�@@�؋����v�Z�I�u�W�F�N�g(�⏕�����A �؋����v�Z�����A 
     * �敨OP���������e[])�𐶐�����B<BR>
     * 
     * �@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�⏕�����F�@@����.�⏕����<BR>
     * �@@�@@�@@�؋����v�Z�����F�@@�P�j�Ő��������؋����v�Z����<BR>
     * �@@�@@�@@�敨OP���������e�F�@@����.�敨OP���������e<BR>
     * 
     * �R�j�@@�Q�j�Ő��������؋����v�Z�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * 
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_ifoNewOrderSpec - �敨OP���������e�B
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     * @@roseuid 4113628D00B4
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getIfoDepositCalc(WEB3GentradeSubAccount, WEB3IfoNewOrderSpec)";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3IfoDepositCalcCondition l_condition = createIfoDepositCalcCondition(l_subAccount);
            WEB3IfoDepositCalc l_ifoCalc = new WEB3IfoDepositCalc(
                    l_subAccount, l_condition, l_ifoNewOrderSpec);

            log.exiting(STR_METHOD_NAME);
            return l_ifoCalc;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            log.error(baseRunEx.getMessage(), baseRunEx);
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (create�؋����v�Z����)<BR>
     * 
     * �؋����v�Z�������쐬����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�T�[�r�X�jcreate�؋����v�Z�����v�Q�ƁB<BR>
     * 
     * �y�g�pDB�z<BR>
     * �E�]�͏����e�[�u��<BR>
     * �E�،���Ѓe�[�u��<BR>
     * �E���X�e�[�u��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * 
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalcCondition
     * @@roseuid 41122F6F0378
     */
    public WEB3IfoDepositCalcCondition createIfoDepositCalcCondition(WEB3GentradeSubAccount l_subAccount) 
     {

        WEB3IfoDepositCalcCondition l_condition = new WEB3IfoDepositCalcCondition();
        l_condition.calcBizDate();
        l_condition.calcIfoDepositCalcBaseDate();
        //�؋����s�����[�����M�σt���O���Z�b�g
        boolean l_blnIfoDepositMail  = isIfoDepositMailFlag(l_subAccount);
        l_condition.setIfoDepositMailFlag(l_blnIfoDepositMail);
        //���Z�l�����M�σt���O���Z�b�g
        boolean l_blnQuickReport  = isQuickReportReceived(l_subAccount);
        l_condition.setQuickReportReceivedFlag(l_blnQuickReport);
        //�[����{�t���O���Z�b�g
        boolean l_blnEveningSession  = isEveningSession(l_subAccount);
        l_condition.setEveningSessionFlag(l_blnEveningSession);

        l_condition.createIfoDepositCalcConditionPerIndexList(l_subAccount);
        double l_dblPreBizDateIfoDepositLackCharge =
            getPreBizDateIfoDepositLackCharge(l_subAccount, l_condition.getPreBizDate());
        l_condition.setPreBizDateInfoDepositLackCharge(l_dblPreBizDateIfoDepositLackCharge);

        double l_dblCurrentBizDateIfoDepositLackCharge =
            getCurrentBizDateIfoDepositLackCharge(l_subAccount, l_condition.getCurrentBizDate());
        l_condition.setCurrentBizIfoDepositLackCharge(l_dblCurrentBizDateIfoDepositLackCharge);

        boolean l_blnNewOpenTradingPowerAvailable = isNewOpenTradingPowerAvailable(l_subAccount);
        l_condition.setNewOpenTradingPowerAvailableFlag(l_blnNewOpenTradingPowerAvailable);

        //�؋����s���z��Ǘ��t���O���Z�b�g
        boolean l_lackChargeNonManagement = isLackChargeNonManagement(l_subAccount);
        l_condition.setLackChargeNonManagementFlag(l_lackChargeNonManagement);
        
        //�؋���SQ�������|�W�V������v����Z�b�g
        boolean IfodepositNonCalcSqProductFlag = isIfodepositNonCalcSqProductFlag(l_subAccount);
        l_condition.setIfodepositNonCalcSqProductFlag(IfodepositNonCalcSqProductFlag);


        InstitutionRow l_inst = (InstitutionRow)l_subAccount.getInstitution().getDataSourceObject();
        l_condition.setRealPriceIfoDepositCalcFlag(toBoolean(l_inst.getIfoRealPriceCalcDiv()));
        l_condition.setSimpleSPANCalcFlag(toBoolean(l_inst.getSimpleSpanCalcDiv()));
        l_condition.setSpanTroubleFlag(toBoolean(l_inst.getSpanTroubleDiv()));
        l_condition.setSPANFactor(l_inst.getSpanFactor());
        l_condition.setSPANFactorRed(l_inst.getSpanFactorRed());
        l_condition.setTransferPowerFactor(l_inst.getTransferPowerFactor());

        BranchRow l_branch = (BranchRow)l_subAccount.getWeb3GenBranch().getDataSourceObject();
        l_condition.setMinIfoDeposit(l_branch.getMinIfoDeposit());

        //���X�ʏ؋����v�Z�������Z�b�g        
        BranchPreferencesParams[] l_params =
            WEB3IfoDepositPersistentDataManager.getBranchPreferencesParamsList(
                l_branch.getBranchId());
        if (l_params != null)
        {
            for (int i = 0; i < l_params.length; i++)
            {
                l_condition.addCalcConditionPerBranch(l_params[i].getName(), l_params[i].getValue());
            }
        }

        log.debug("Created CalcCondition=" + l_condition.toString());

        return l_condition;

    }

    // private methods ---------------------------------------------------------    

    /**
     * (is�V�K���]�͉\)<BR>
     * 
     * �Y���ڋq���V�K���]�͉\�ł��邩�𔻒肷��B<BR>
     * �V�K���]�͂��\�ł���ꍇ��true���A�s�ł���ꍇ��false��ԋp����B<BR>
     * 
     * �P�j�@@�]�͏���Params�̎擾<BR>
     * �@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�]�͏���Params( )<BR>
     * 
     * �@@[�����̐ݒ�]<BR>
     * �@@����ID�F�@@����.�⏕����.����ID<BR>
     * 
     * �Q�j�@@�؋��������J�ݍς݂̏ꍇ<BR>
     * �@@(����.�⏕����.�⏕�����^�C�v == �h�����I�v�V�����������(�敨�؋���)�h)<BR>
     * 
     * �@@�]�͏����e�[�u��Params.�����~�敪 == �h����\�h�A���A<BR>
     * �@@�]�͏����e�[�u��Params..�敨OP�V�K���]�͋敪 == 
     * �h�]�͉h�Ȃ�΁Atrue��ԋp����B<BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * 
     * �R�j�@@�؋����������J�݂̏ꍇ<BR>
     * �@@(����.�⏕����.�⏕�����^�C�v != �h�����I�v�V�����������(�敨�؋���)�h)<BR>
     * 
     * �@@�]�͏���Params.�����~�敪 == �h����\�h�A���A<BR>
     * �@@�]�͏���Params..���̑����i���t�]�͋敪 == 
     * �h�]�͉h�Ȃ�΁Atrue��ԋp����B<BR>
     *   �ȊO�Afalse��ԋp����B<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g�B
     * @@return boolean
     * @@roseuid 411C8BE90029
     */
    private boolean isNewOpenTradingPowerAvailable(WEB3GentradeSubAccount l_subAccount)
    {
        boolean l_blnResult = false;
        TradingpowerCalcConditionParams l_condition =
            WEB3IfoDepositPersistentDataManager.getTradingpowerCalcConditionParams(
                l_subAccount.getAccountId());
        if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            if (!toBoolean(l_condition.getTradingStop())
                && !toBoolean(l_condition.getIfoOpenPositionStop()))
            {
                l_blnResult = true;
            }
        }
        else
        {
            if (!toBoolean(l_condition.getTradingStop())
                && !toBoolean(l_condition.getOtherTradingStop()))
            {
                l_blnResult = true;
            }
        }
        return l_blnResult;
    }

    /**
     * (is�؋����s�����[�����M��)<BR>
     * <BR>
     * �Y����Е��X��T+0�̏؋����s�����[�����M�ςł��邩�𔻒肷��B<BR>
     * �؋����s�����[�����M�ςł���ꍇ��true���A�����M�ł���ꍇ��false��ԋp����B<BR>
     * <BR>
     * �P�j�@@�v���Z�X�Ǘ�Params�̎擾<BR>
     * <BR>
     * �@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�v���Z�X�Ǘ�Params( )<BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�@@�v���Z�XID�F�@@�h0001�h(�؋����s���m��)<BR>
     * �@@�@@�،���ЃR�[�h�F�@@����.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.���X�R�[�h<BR>
     * <BR>
     * �Q�j�@@�؋����s�����[�����M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)�Atrue��ԋp����B<BR>
     * <BR>
     * �R�j�@@�؋����s�����[�������M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))�Afalse��ԋp����B<BR>
     * <BR>
     * 
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g�B
     * @@return boolean
     */
    private boolean isIfoDepositMailFlag(WEB3GentradeSubAccount l_subAccount)
    {
        //�v���Z�XID
        String l_strProcessId = "0001";
        //�،���ЃR�[�h
        String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h
        String l_strBranCode = l_subAccount.getWeb3GenBranch().getBranchCode();

        //�v���Z�X�Ǘ�Params�̎擾
        ProcessManagementParams l_params =
            WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                l_strProcessId,
                l_strInstCode,
                l_strBranCode);
        
        //�؋����s�����[�������M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))
        if(l_params == null)
        {
            //false��ԋp����B
            return false;
        }
        //�؋����s�����[�����M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)
        else
        {        
            //true��ԋp����B
            return true;
        }
    }

    /**
     * (get�O���؋����s���z)<BR>
     * 
     * �Y���ڋq�̑O���؋����s���z���擾����B<BR>
     * 
     * �P�j�@@�؋���Params�̎擾<BR>
     * �@@�@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�؋���Params( )�ɂ��A<BR>
     * �@@�@@�c�Ɠ�[T-1]�ɏ؋����s�����[���Ƃ��đ��M���ꂽ�f�[�^���擾����B<BR>
     * 
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@����.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.���X�R�[�h<BR>
     * �@@�@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount( ).�����R�[�h<BR>
     * �@@�@@�@@�v�Z���F�@@����.�c�Ɠ�[T-1]<BR>
     * 
     * �Q�j�@@�c�Ɠ�[T-1]�ɏ؋����s�����������Ă��Ȃ��ꍇ(get�؋���Params( )�̖߂�l == null)�A
     * �@@�@@�@@0��ԋp����B<BR>
     * 
     * �R�j�@@�c�Ɠ�[T+0]�̏؋����s�����[�������M�̏ꍇ(��L�ȊO�j�A<BR>
     * �@@�@@�@@�؋���Params.������n�z�����̐�Βl(Abs(������n�z����))��ԋp����B<BR>
     * 
     * �@@
     * @@param l_subAccount - (�⏕����)<BR>
     * 
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_datPreBizDate - �c�Ɠ�[T-1]�B
     * @@return double
     * @@roseuid 4132E785027C
     */
    private double getPreBizDateIfoDepositLackCharge(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datPreBizDate)
    {
        IfoDepositParams l_ifoDeposit = getIfoDepositParams(l_subAccount, l_datPreBizDate);
        if (l_ifoDeposit != null)
        {
            return Math.abs(l_ifoDeposit.getNetAmoutCash());
        }
        else
        {
            return 0.0;
        }
    }

    /**
     * (get�����؋����s���z)<BR>
     * 
     * �Y���ڋq�̓����؋����s���z���擾����B<BR>
     * 
     * �P�j�@@�؋���Params�̎擾<BR>
     * �@@�@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�؋���Params( )�ɂ��A<BR>
     * �@@�@@�c�Ɠ�[T+0]�ɏ؋����s�����[���Ƃ��đ��M���ꂽ�f�[�^���擾����B<BR>
     * 
     * �@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@����.�⏕����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�@@����.�⏕����.get����X.���X�R�[�h<BR>
     * �@@�@@�@@�ڋq�R�[�h�F�@@����.�⏕����.getMainAccount( ).�����R�[�h<BR>
     * �@@�@@�@@�v�Z���F�@@����.�c�Ɠ�[T+0]<BR>
     * 
     * �Q�j�@@�c�Ɠ�[T+0]�ɏ؋����s�����������Ă��Ȃ�(�܂��́A�c�Ɠ�[T+0]�̏؋����s����?
     * ���������M)�̏ꍇ<BR>
     * �@@�@@�@@(get�؋���Params( )�̖߂�l == null)�A<BR>
     * �@@�@@�@@0��ԋp����B<BR>
     * 
     * �R�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�؋���Params.������n�z�����̐�Βl(Abs(������n�z����))��ԋp����B<BR>
     * 
     * �@@
     * @@param l_subAccount - (�⏕����)<BR>
     * 
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_datCurrentDate - �c�Ɠ�[T+0]�B
     * @@return double
     * @@roseuid 41402B190246
     */
    private double getCurrentBizDateIfoDepositLackCharge(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datCurrentBizDate)
    {
        IfoDepositParams l_ifoDeposit = getIfoDepositParams(l_subAccount, l_datCurrentBizDate);
        if (l_ifoDeposit != null)
        {
            return Math.abs(l_ifoDeposit.getNetAmoutCash());
        }
        else
        {
            return 0.0;
        }
    }

    private boolean toBoolean(String source)
    {
        return WEB3EnforcementDef.ENFORCEMENT.equals(source) ? true : false;
    }

    private IfoDepositParams getIfoDepositParams(
        WEB3GentradeSubAccount l_subAccount,
        Date l_datBizDate)
    {
        return WEB3IfoDepositPersistentDataManager.getIfoDepositParams(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getWeb3GenBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_datBizDate);
    }

   /**
     * (is���Z�l�����M��)<BR>
     *
     * ���Z�l�����M�ςł��邩�𔻒肷��B<BR> 
     * ��M�ςł���ꍇ��true���A��M�ςłȂ��ꍇ��false��ԋp����B<BR> 
     *
     *�P�j�@@�v���Z�X�Ǘ�Params�̎擾 <BR>
     *    �@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�v���Z�X�Ǘ�Params( ) <BR>
     *    �@@[�����̐ݒ�] <BR>
     *    �@@�v���Z�XID�F�@@�h0008�h(���Z�l�����M) <BR>
     *    �@@�،���ЃR�[�h�F�@@����.�⏕����.�،���ЃR�[�h <BR>
     *    �@@���X�R�[�h�F�@@����.�⏕����.get����X().���X�R�[�h <BR>
     *
     * �Q�j�@@��M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)�Atrue��ԋp����B <BR>
     *
     * �R�j�@@���Z�l���񖢎�M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))�Afalse��ԋp����B<BR> 
     *
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g�B
     * @@return boolean 
     */
    private boolean isQuickReportReceived(WEB3GentradeSubAccount l_subAccount)
    {
        //�v���Z�XID
        String l_strProcessId = "0008";
        //�،���ЃR�[�h
        String l_strInstCode = l_subAccount.getInstitution().getInstitutionCode();
        //���X�R�[�h
         String l_strBranCode = l_subAccount.getWeb3GenBranch().getBranchCode();

         //�v���Z�X�Ǘ�Params�̎擾
         ProcessManagementParams l_params =
         WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                   l_strProcessId,
                   l_strInstCode,
                   l_strBranCode);

        //���Z�l���񖢎�M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))
        if(l_params == null)
        {
            //false��ԋp����B
            return false;
        }
        //��M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)
        else
        {
            //true��ԋp����B
            return true;
        }
    }

   /**
     * (is�[����{)<BR>
     * �[����{�ł��邩�𔻒肷��B 
     * �[����{�ł���ꍇ��true���A�[�ꖢ���{�̏ꍇ��false��ԋp����B 
     *
     * �P�j�@@���X�I�u�W�F�N�g�̎擾 
       *     �@@����.�⏕����.get����X()�@@ 
     *
     * �Q�j�@@�[����{���� 
     *     �@@�P�j�Ŏ擾�������X.is�[����{()�̖߂�l��ԋp����B 
     *     �@@ [is�[����{()�̈����ݒ�] 
     *     �@@�����^�C�v�F�@@�h�敨�I�v�V�����h 
     *
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g�B
     * @@return boolean 
     */
    private boolean isEveningSession(WEB3GentradeSubAccount l_subAccount) 
    {

        //���X�I�u�W�F�N�g
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        //���X.is�[����{()�̖߂�l��ԋp����B
        //[is�[����{()�̈����ݒ�] 
        //�����^�C�v�F�@@�h�敨�I�v�V�����h
        boolean l_blnEveningSession = false;
        try
        {
            l_blnEveningSession = l_branch.isEveningSessionEnforcemented(ProductTypeEnum.IFO);
        }
        catch(WEB3BaseException be)
        {
            log.error(be.getMessage(), be);
            throw new WEB3BaseRuntimeException(
                be.getErrorInfo(),
                be.getErrorMethod(),
                be.getErrorMessage(),
                be.getException());
        }
        return l_blnEveningSession;
    }

   /**
     * (is�؋����s���z��Ǘ�)<BR>
     *
     * �؋����s���z���Ǘ����邩�𔻒肷��B<BR> 
     * �؋����s���z���Ǘ�����ꍇ��false���A�Ǘ����Ȃ��ꍇ��true��ԋp����B<BR> 
     *
     * �P�j�@@���X�I�u�W�F�N�g�̎擾 <BR>
     *     �@@����.�⏕����.get����X()<BR>
     *
     * �Q�j�@@�P�j�Ŏ擾�������X.is�؋����s���z��Ǘ�()�̖߂�l��ԋp����B <BR>
     *
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g�B
     * @@return boolean 
     */
    private boolean isLackChargeNonManagement(WEB3GentradeSubAccount l_subAccount)
    {
        //���X�I�u�W�F�N�g
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        
        boolean l_blnManagement = false;
        try
        {
            l_blnManagement = l_branch.isIfodepositLackchargeNonManagement();
        }
        catch(WEB3BaseException be)
        {
            log.error(be.getMessage(), be);
            throw new WEB3BaseRuntimeException(
                be.getErrorInfo(),
                be.getErrorMethod(),
                be.getErrorMessage(),
                be.getException());
        }
        return l_blnManagement;
    }


    /**
      * (is�؋���SQ�������|�W�V������v��)<BR>
      * 
      * SQ���������|�W�V�����Ƃ��Čv�シ�邩�𔻒肷��B<BR>
      * 
      * �v�サ�Ȃ��ꍇ��true���A�v�シ��ꍇ��false��ԋp����B<BR> 
      * ���v�シ�遁false�A�v�サ�Ȃ���true�Ȃ̂Œ��ӂ��邱��<BR>
      * 
      * �P�j�@@���X�I�u�W�F�N�g�̎擾<BR>
      * �@@
      *   ����.�⏕����.get����X()�@@<BR>
      * 
      * �Q�j�@@�؋���SQ�������|�W�V������v��̔���<BR> 
      *   �P�j�Ŏ擾�������X����u�؋���SQ�������|�W�V������v��v����������B<BR>
      * 
      *   �Q�j-�P�@@�u�v�サ�Ȃ��v�ꍇ�Atrue��ԋp����B<BR>   
      *   �Q�j-�Q�@@��L�ȊO�̏ꍇ�Atrue��ԋp����B<BR>    
      *
      * @@param l_subAccount - �⏕�����I�u�W�F�N�g�B
      * @@return boolean 
      */
     private boolean isIfodepositNonCalcSqProductFlag(WEB3GentradeSubAccount l_subAccount)
     {
         // ���X�I�u�W�F�N�g�̎擾
         WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
         
         BranchPreferencesRow l_branchPreferencesRow = null;
         try
         {
             // �؋���SQ�������|�W�V������v�������
             l_branchPreferencesRow =
                 BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                     l_branch.getBranchId(),
                     WEB3BranchPreferencesNameDef.IFODEPOSIT_NON_CALC_SQ_PRODUCT,
                     1);
         }
         catch (DataNetworkException l_ex)
         {
             log.error(l_ex.getMessage(), l_ex);
             throw new WEB3BaseRuntimeException
                 (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName(),
                 l_ex.getMessage(),
                 l_ex);
         }
         catch (DataQueryException l_ex)
         {
             log.error(l_ex.getMessage(), l_ex);
             throw new WEB3BaseRuntimeException
                 (WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName(),
                 l_ex.getMessage(),
                 l_ex);
         }
         
         // �u�v�サ�Ȃ��v�ꍇ�Atrue��ԋp����B
         if (l_branchPreferencesRow != null && 
               WEB3IfodepositNonCalcSqProductDef.NON_CALC.equals(l_branchPreferencesRow.getValue()))
         {
             return true;
         }
         // ��L�ȊO�̏ꍇ�Afalse��ԋp����B
         else
         {
             return false;
         }
     }
}
@
