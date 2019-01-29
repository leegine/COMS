head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitServiceImpl(WEB3AdminAccOpenDataTransferUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 �И���(���u) �V�K�쐬 ���f�� 181 �c�a�X�V�d�l�@@047�C048
Revision History : 2009/08/26 �И���(���u) ���f�� 190
Revision History : 2009/08/31 ���g(���u) ���f�� 200,201,203
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenTempDao;
import webbroker3.accountopen.data.ExpAccountOpenTempPK;
import webbroker3.accountopen.data.ExpAccountOpenTempParams;
import webbroker3.accountopen.data.ExpAccountOpenTempRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitServiceImpl)<BR>
 * �Ǘ��Ҍ����J�݃f�[�^�ڊ�UnitService�����N���X<BR>
 * �i�g�����U�N�V��������=TX_CREATE_NEW�j<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferUnitServiceImpl implements WEB3AdminAccOpenDataTransferUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferUnitServiceImpl.class);

    /**
     * @@roseuid 4A8A0835007C
     */
    public WEB3AdminAccOpenDataTransferUnitServiceImpl()
    {
    }

    /**
     * �����J�݈ڊǂ̏������s���B<BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�����J�݁i�f�[�^�ڊǈꌏ�jprocess�v �Q�� <BR>
     * @@param l_expAccountOpen - (�����J�݌����q)<BR>
     * �����J�݌����q<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A8278EF02BF
     */
    public void process(WEB3AccOpenExpAccountOpen l_expAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "process(WEB3AccOpenExpAccountOpen,String,String,String) ";
        log.entering(STR_METHOD_NAME);

        //�����敪
        String l_strProcessDiv = WEB3StatusDef.DEALT;
        //�G���[���
        String l_strErrorMessage = null;
        boolean l_blnIsCheckError = false;
        try
        {
            //�w�肳�ꂽ�،���ЃR�[�h�A���X�R�[�h�����X�e�[�u���ɑ��݂��邩���`�F�b�N����B
            validateBranch(l_expAccountOpen.getBranchCode(), l_expAccountOpen.getInstitutionCode());

            //�����J�ݐ\���f�[�^�̓��̓`�F�b�N���s���B 
            //�`�F�b�N�^�C�v�F�@@3�F�`�[�쐬 
            //�ڋq�R�[�h�����̔ԃt���O�F�@@�����̔Ԃ��s��Ȃ�
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.VOUCHER_CREATED,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);

            //����ڋq���d�����ēo�^����Ă��Ȃ����`�F�b�N����B
            l_expAccountOpen.validateAccountCode();

            //���Z�@@�ցi��s�j�}�X�^���������A�Y���s�����邩���`�F�b�N����B
            l_expAccountOpen.validateFinInstitution();
        }
        catch (WEB3BaseException l_ex)
        {
            //�ȏ�̃`�F�b�N�ŁA�G���[�������̏ꍇ
            log.error("�ȏ�̃`�F�b�N�ŁA�G���[�������̏ꍇ", l_ex);

            l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
            l_strErrorMessage = l_ex.getErrorInfo().getErrorMessage();
            l_blnIsCheckError = true;
        }

        if (!l_blnIsCheckError)
        {
            boolean l_blnIsUpdateError = false;
            //�ȏ�̃`�F�b�N�ŁA�G���[���������Ȃ��ꍇ�A�����J�݌����q�f�[�^���X�V����
            try
            {
                QueryProcessor l_queryProcesser =
                    Processors.getDefaultProcessor();

                ExpAccountOpenParams l_expAccountOpenParams =
                    (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
                l_queryProcesser.doInsertQuery(l_expAccountOpenParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
                l_strErrorMessage = "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B";
                l_blnIsUpdateError = true;
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
                l_strErrorMessage = "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B";
                l_blnIsUpdateError = true;
            }

            if (!l_blnIsUpdateError)
            {
                try
                {
                    // �V�[�P���X�}�Ɉȏ�̏����ŃG���[���������Ȃ��ꍇ�A�ȉ��̏������s��
                    //�w��̌����J�݌����q�̏����A�����J�ݓ`�[���쐬����B
                    WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
                        new WEB3AccOpenVoucherCreatedServiceImpl();
                    l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
                }
                catch (WEB3BaseException l_ex)
                {
                    //�����J�݌����q�f�[�^���X�V�ŁA�G���[�������̏ꍇ
                    log.error("�����J�ݓ`�[���쐬����ŁA�G���[�������̏ꍇ", l_ex);

                    l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
                    l_strErrorMessage = l_ex.getErrorInfo().getErrorMessage();
                }
            }
        }

        //���Y�����J�݌����q�ɑ΂��Č����J�݌����q�ꎞ�f�[�^���X�V����B
        //�،���ЃR�[�h�F�@@�����J�݌����q.get�،���ЃR�[�h()
        //���ʃR�[�h�F�@@�����J�݌����q.get���ʃR�[�h()
        //�����敪�F
        //�V�[�P���X�}�Ɉȏ�̏����ŃG���[����������ꍇ�A"9:�G���["�ƍX�V����B
        //�ȊO�̏ꍇ�A"1:������"�ƍX�V����B
        //�G���[���F
        //�V�[�P���X�}�Ɉȏ�̏����ŃG���[����������ꍇ�A�G���[���b�Z�[�W���i�[����B
        //�i*�j�G���[���̒�����200�o�C�g�𒴂���ꍇ�A201�ȏ�̓��e��؎̂Ă�B
        if (l_strErrorMessage != null && l_strErrorMessage.getBytes().length > 200)
        {
            l_strErrorMessage = new String(l_strErrorMessage.getBytes(), 0, 200);
        }

        this.updateAccOpenExpAccountOpenTemp(l_expAccountOpen.getInstitutionCode(),
            l_expAccountOpen.getRequestNumber(),
            l_strProcessDiv,
            l_strErrorMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���X)<BR>
     * �w�肳�ꂽ�،���ЃR�[�h�A���X�R�[�h�����X�e�[�u���ɑ��݂��邩���`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���X�c�`�n�𗘗p���ĕ��X�e�[�u������������B<BR>
     *  �@@�@@�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̗�O���X���[����B<BR>
     *  �@@�@@�u�Y�����X�f�[�^�Ȃ��v<BR>
     * �@@�@@�@@�@@class      :  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag      :  BUSINESS_ERROR_01386<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A827CE201A5
     */
    private void validateBranch(String l_strBranchCode, String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBranch(String,String) ";
        log.entering(STR_METHOD_NAME);

    	//�w�肳�ꂽ�،���ЃR�[�h�A���X�R�[�h�����X�e�[�u���ɑ��݂��邩���`�F�b�N����B
    	//���X�c�`�n�𗘗p���ĕ��X�e�[�u������������B
    	//�|�Y���f�[�^���Ȃ��ꍇ�́A�ȉ��̗�O���X���[����B
    	//�u�Y�����X�f�[�^�Ȃ��v
        BranchRow l_branchRow = null;
		try
        {
		    l_branchRow = BranchDao.findRowByInstitutionCodeBranchCode(
                l_strInstitutionCode,
                l_strBranchCode);

            if (l_branchRow == null)
            {
                log.debug("�Y�����X�f�[�^�Ȃ��B");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����X�f�[�^�Ȃ��B");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
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
     * (update�����J�݌����q�ꎞ)<BR>
     * ���Y�����J�݌����q�ɑ΂��Č����J�݌����q�ꎞ�f�[�^���X�V����B<BR>
     * <BR>
     * �P�j�@@�����J�݌����q�ꎞparams���擾����B<BR>
     * ���������F<BR>
     * �@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h�F����.���ʃR�[�h<BR>
     * <BR>
     * �Q�j�@@�ȉ��̃t�B�[���h���X�V����B<BR>
     * �@@�X�V�҃R�[�h�F�Ǘ��҂���Ǘ��҃R�[�h<BR>
     * �@@�X�V�����F��������<BR>
     * �@@�����J�݌����q�ꎞ.�����敪�F����.�����敪<BR>
     * �@@�����J�݌����q�ꎞ.�G���[���F����.�G���[���<BR>
     * <BR>
     * �@@DB�X�V�d�l�u�����J�݌����q�ꎞ�iUpdate�j�d�l.xls�v�Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@param l_strProcessDiv - (�����敪)<BR>
     * �����敪<BR>
     * @@param l_strErrorInfo - (�G���[���)<BR>
     * �G���[���<BR>
     * @@throws WEB3BaseException
     * @@return Void
     * @@roseuid 4A8287A100BB
     */
    private void updateAccOpenExpAccountOpenTemp(
        String l_strInstitutionCode,
        String l_strRequestNumber,
        String l_strProcessDiv,
        String l_strErrorInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "updateAccOpenExpAccountOpenTemp(String, String, String, String,String) ";
        log.entering(STR_METHOD_NAME);

        ExpAccountOpenTempRow l_expAccountOpenTempRow = null;

        try
        {
            //�����J�݌����q�ꎞparams���擾����B
            l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    l_strInstitutionCode,
                    l_strRequestNumber));
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�ȉ��̃t�B�[���h���X�V����B
        ExpAccountOpenTempParams l_expAccountOpenTemParams =
            new ExpAccountOpenTempParams(l_expAccountOpenTempRow);

        //�X�V�҃R�[�h�F�Ǘ��҂���Ǘ��҃R�[�h
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_expAccountOpenTemParams.setLastUpdater(l_admin.getAdministratorCode());

        //�X�V�����F��������
        l_expAccountOpenTemParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //�����J�݌����q�ꎞ.�����敪�F����.�����敪
        l_expAccountOpenTemParams.setStatus(l_strProcessDiv);

        //�����J�݌����q�ꎞ.�G���[���F����.�G���[���
        l_expAccountOpenTemParams.setErrorInfo(l_strErrorInfo);

        try
        {
            WEB3DataAccessUtility.updateRow(l_expAccountOpenTemParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
