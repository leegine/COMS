head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLAccountOpenUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SL�����J��UnitServiceImpl(WEB3AioSLAccountOpenUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 �Ӑ� (���u) �V�K�쐬 ���f��No.763
Revision History : 2007/09/19 �Ӑ� (���u) �d�l�ύX ���f��No.778 , ���f��No.781 , ���f��No.783 , ���f��No.786 , ���f��No.788
Revision History : 2007/11/09 �Ӑ� (���u) �d�l�ύX ���f��No.823
Revision History : 2007/12/12 �����q (���u) �d�l�ύX ���f��No.826
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import webbroker3.aio.define.WEB3SystemPreferenceDef;
import webbroker3.aio.message.WEB3SLAccountBaseInfoUnit;
import webbroker3.aio.message.WEB3SLAccountOpenApplyRequest;
import webbroker3.aio.message.WEB3SLAccountOpenApplyResponse;
import webbroker3.aio.message.WEB3SLAccountOpenRequest;
import webbroker3.aio.message.WEB3SLAccountOpenResponse;
import webbroker3.aio.service.delegate.WEB3AioSLAccountOpenUnitService;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanDataControlService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CommSerialNumbersPK;
import webbroker3.gentrade.data.CommSerialNumbersRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (SL�����J��UnitServiceImpl)<BR>
 * SL�����J��UnitService�����N���X<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AioSLAccountOpenUnitServiceImpl extends WEB3ClientRequestService
    implements WEB3AioSLAccountOpenUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLAccountOpenUnitServiceImpl.class);

    /**
     * @@roseuid 46F0D5800186
     */
    public WEB3AioSLAccountOpenUnitServiceImpl()
    {

    }

    /**
     * SL�����J�݃T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * �@@�EvalidateSL�����J��()<BR>
     * �@@�EsubmitSL�����J��()<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE06120347
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        // validateSL�����J��
        if (l_request instanceof WEB3SLAccountOpenApplyRequest)
        {
            l_response =
                this.validateSLAccountOpen(
                    (WEB3SLAccountOpenApplyRequest)l_request);
        }

        // submitSL�����J��
        else if (l_request instanceof WEB3SLAccountOpenRequest)
        {
            l_response =
                this.submitSLAccountOpen(
                    (WEB3SLAccountOpenRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateSL�����J��)<BR>
     * SL�����J�݂̈˗��������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidateSL�����J�݁v�Q�ƁB<BR>
     * <BR>
     * ==================================================<BR>
     * is�@@�l( )<BR>
     * �@@�l�q�ł��邩���ʂ��s���B<BR>
     * is�@@�l() == true �̏ꍇ<BR>
     * ��O���X���[����B<BR>
     * �y�i�@@�l�j�����J�ݕs�G���[�z<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_02934<BR>
     * ==================================================<BR>
     * is�a��،��]����( )<BR>
     * �a��،��]���������{���Ă��邩���ʂ���B<BR>
     * is�a��،��]����() == true �̏ꍇ<BR>
     * ��O���X���[����B<BR>
     * �y�i�a��،��]�����j�����J�ݕs�G���[�z<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_02935<BR>
     * ==================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return WEB3SLAccountOpenApplyResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE3362007D
     */
    protected WEB3SLAccountOpenApplyResponse validateSLAccountOpen(
        WEB3SLAccountOpenApplyRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " validateSLAccountOpen(WEB3SLAccountOpenApplyRequest)";
        log.entering(STR_METHOD_NAME);

        // validate������t�\
        // ��t���ԃ`�F�b�N/�V�X�e��������~�`�F�b�N���s���B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        // [����]
        // ����ID�F�@@�ԍϕK�v�z�f�[�^Params.����ID
        // �⏕�����^�C�v�F 1�i������������i�a����j�j
        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        // [����]
        // �،���ЃR�[�h�F�⏕����.�،���ЃR�[�h
        // ���X�R�[�h�F�⏕����.get����X().getBranchCode()
        // �����R�[�h�F�⏕����.getMainAccount().getAccountCode()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode());

        //validate����\�ڋq
        //[����]
        //�ڋq�Fget�ڋq()�Ŏ擾�����ڋq�I�u�W�F�N�g
        //�������F���ݓ���
        //�����t���O�F"1"�i�Œ�j
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_gentradeMainAccount,
                GtlUtils.getSystemTimestamp(),
                1 + "");

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͗�O���X���[����"
                + l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

		// get�ڋq�s( )
		// �ڋqParams���擾����B
		MainAccountRow l_mainAccountRow =
			l_gentradeMainAccount.getMainAccountRow();

		//�P�j�p�����[�^.�⏕��������擾�����ڋq�I�u�W�F�N�g.���Z�^�񋏏Z�敪
		//���u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv�̏ꍇ�́A��O���X���[����B
		if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()) ||
			WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02979,
				this.getClass().getName() + STR_METHOD_NAME,
				"�񋏏Z�҂͐\���ނ��Ƃ��ł��܂���B");
		}

        // is�@@�l( )
        // �@@�l�q�ł��邩���ʂ��s���B
        // is�@@�l() == true �̏ꍇ
        if (l_gentradeMainAccount.isCorporation())
        {
            // ��O���X���[����B
            // �y�i�@@�l�j�����J�ݕs�G���[�z
            log.debug("�i�@@�l�j�����J�ݕs�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02934,
                this.getClass().getName() + STR_METHOD_NAME,
                "�i�@@�l�j�����J�ݕs�G���[�B");
        }

        // is�a��،��]����( )
        // �a��،��]���������{���Ă��邩���ʂ���B
        // is�a��،��]����() == true �̏ꍇ
        if (l_gentradeMainAccount.isAssetEvaluation())
        {
            // ��O���X���[����B
            // �y�i�a��،��]�����j�����J�ݕs�G���[�z
            log.debug("�i�a��،��]�����j�����J�ݕs�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02935,
                this.getClass().getName() + STR_METHOD_NAME,
                "�i�a��،��]�����j�����J�ݕs�G���[�B");
        }

        // is�M�p�����J��(�ٍϋ敪 : String)
        // �M�p�����J�݋敪���擾����B
        // [����]
        // �ٍϋ敪�F�h�w��Ȃ��h
        boolean l_blnIsMarginAccountOpen =
            l_gentradeMainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT);

        // is�敨OP�����J��(�敨�^�I�v�V�����敪 : String)
        // �敨�����J�݋敪���擾����B
        // [����]
        // �敨/�I�v�V�����敪�F�h1�h(�敨)
        // [�߂�l]
        // �敨�����J�݋敪
        boolean l_blnIsFuturesAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);

        // is�敨OP�����J��(�敨�^�I�v�V�����敪 : String)
        // �I�v�V���������J�݋敪���擾����B
        // [����]
        // �敨/�I�v�V�����敪�F�h2�h(�I�v�V����)
        // [�߂�l]
        // �I�v�V���������J�݋敪
        boolean l_blnIsOptionAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);

        // validate�����J�݉\(boolean, boolean, boolean)
        //�m�����n
        // �M�p�����J�݋敪�Fis�M�p�����J��()�̖߂�l
        // �敨�����J�݋敪�Fis�敨OP�����J��(�敨)�̖߂�l
        // �I�v�V���������J�݋敪�Fis�敨OP�����J��(�I�v�V����)�̖߂�l
        this.validateAccOpenPossible(
            l_blnIsMarginAccountOpen,
            l_blnIsFuturesAccountOpen,
            l_blnIsOptionAccountOpen);

        //validate�N����l(SubAccount, String, long)
        //[����]
        //�⏕�����Fget�⏕�����̖߂�l
        //�v���t�@@�����X���F�hsl_lowlimit.age.check�h
        //�v���t�@@�����X�̘A�ԁF 1
        WEB3GentradeBranch l_gentradeBranch =
            (WEB3GentradeBranch)l_gentradeSubAccount.getMainAccount().getBranch();
        l_gentradeBranch.validateAgeLowLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK,
            1);

        //validate�N�����l(SubAccount, String, long)
        //[����]
        //�⏕�����Fget�⏕�����̖߂�l
        //�v���t�@@�����X���F�hsl_highlimit.age.check�h
        //�v���t�@@�����X�̘A�ԁF 1
        l_gentradeBranch.validateAgeHighLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK,
            1);

        // createResponse( )
        WEB3SLAccountOpenApplyResponse l_response =
            (WEB3SLAccountOpenApplyResponse)l_request.createResponse();

        // �v���p�e�B�Z�b�g
        // �ȉ��̒ʂ�A�v���p�e�B��ݒ肷��B
        WEB3SLAccountBaseInfoUnit l_slAccountBaseInfoUnit =
            new WEB3SLAccountBaseInfoUnit();

        // �ڋq��(�J�i) = get�ڋq�s.�ڋq��(�J�i)
        l_slAccountBaseInfoUnit.accountNameKana =
            l_mainAccountRow.getFamilyNameAlt1();

        // �ڋq��(����) = get�ڋq�s.�ڋq��(����)
        l_slAccountBaseInfoUnit.accountName = l_mainAccountRow.getFamilyName();

        // ���N�����N�� = get�ڋq�s.���N�����N��
        l_slAccountBaseInfoUnit.eraBorn = l_mainAccountRow.getEraBorn();

        // ���N���� = get�ڋq�s.���N����
        l_slAccountBaseInfoUnit.bornDate = l_mainAccountRow.getBornDate();

        // ���� = get�ڋq�s.����
        l_slAccountBaseInfoUnit.sex = l_mainAccountRow.getSex();

        // �X�֔ԍ� = get�ڋq�s.�X�֔ԍ�
        l_slAccountBaseInfoUnit.zipCode = l_mainAccountRow.getZipCode();

        // �Z���P(����) = get�ڋq�s.�Z���P(����)
        l_slAccountBaseInfoUnit.address1 = l_mainAccountRow.getAddressLine1();

        // �Z���Q(����) = get�ڋq�s.�Z���Q(����)
        l_slAccountBaseInfoUnit.address2 = l_mainAccountRow.getAddressLine2();

        // �Z���R(����) = get�ڋq�s.�Z���R(����)
        l_slAccountBaseInfoUnit.address3 = l_mainAccountRow.getAddressLine3();

        // email�A�h���X = get�ڋq�s.email�A�h���X
        l_slAccountBaseInfoUnit.mailAddress = l_mainAccountRow.getEmailAddress();

        // (*)���X�|���X�Z�b�g
        // �ȉ������X�|���X�ɃZ�b�g����B
        // ��L�Ńv���p�e�B�Z�b�g���s�����ڋq��{���
        l_response.accountBaseInfo = l_slAccountBaseInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitSL�����J��)<BR>
     * SL�����J�݂��s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmitSL�����J�݁v�Q�ƁB<BR>
     * <BR>
     * ==================================================<BR>
     * is�@@�l( )<BR>
     * �@@�l�q�ł��邩���ʂ��s���B<BR>
     * is�@@�l() == true �̏ꍇ<BR>
     * ��O���X���[����B<BR>
     * �y�i�@@�l�j�����J�ݕs�G���[�z<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_02934<BR>
     * ==================================================<BR>
     * is�a��،��]����( )<BR>
     * �a��،��]���������{���Ă��邩���ʂ���B<BR>
     * is�a��،��]����() == true �̏ꍇ<BR>
     * ��O���X���[����B<BR>
     * �y�i�a��،��]�����j�����J�ݕs�G���[�z<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@:�@@BUSINESS_ERROR_02935<BR>
     * ==================================================<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * <BR>
     * @@return WEB3SLAccountOpenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE3362007B
     */
    protected WEB3SLAccountOpenResponse submitSLAccountOpen(
        WEB3SLAccountOpenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME =
            " submitSLAccountOpen(WEB3SLAccountOpenRequest)";
        log.entering(STR_METHOD_NAME);

        // 1 validate������t�\( )
        // ��t���ԃ`�F�b�N/�V�X�e��������~�`�F�b�N���s���B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
        // �⏕�����I�u�W�F�N�g���擾����B
        // [����]
        // ����ID�F�@@�ԍϕK�v�z�f�[�^Params.����ID
        // �⏕�����^�C�v�F 1�i������������i�a����j�j
        WEB3GentradeSubAccount l_gentradeSubAccount =
            (WEB3GentradeSubAccount)this.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        // [����]
        // �،���ЃR�[�h�F�⏕����.�،���ЃR�[�h
        // ���X�R�[�h�F�⏕����.get����X().getBranchCode()
        // �����R�[�h�F�⏕����.getMainAccount().getAccountCode()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        // �،���ЃR�[�h
        String l_strInstitutionCode =
            l_gentradeSubAccount.getInstitution().getInstitutionCode();

        WEB3GentradeMainAccount l_gentradeMainAccount =
            l_gentradeAccountManager.getMainAccount(
                l_strInstitutionCode,
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode());

        //validate����\�ڋq(�ڋq, Timestamp, String)
        //[����]
        //�ڋq�Fget�ڋq()�Ŏ擾�����ڋq�I�u�W�F�N�g
        //�������F���ݓ���
        //�����t���O�F"1"�i�Œ�j
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateAccountForTrading(
                l_gentradeMainAccount,
                GtlUtils.getSystemTimestamp(),
                1 + "");

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����"
                + l_orderValidationResult.getProcessingResult().getErrorInfo());
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

		// get�ڋq�s( )
		// �ڋqParams���擾����B
		MainAccountRow l_mainAccountRow =
			l_gentradeMainAccount.getMainAccountRow();
		MainAccountParams l_mainAccountParams =
			new MainAccountParams(l_mainAccountRow);

		//�P�j�p�����[�^.�⏕��������擾�����ڋq�I�u�W�F�N�g.���Z�^�񋏏Z�敪
		//���u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv�̏ꍇ�́A��O���X���[����B
		if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()) ||
			WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
		{
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02979,
				this.getClass().getName() + STR_METHOD_NAME,
				"�񋏏Z�҂͐\���ނ��Ƃ��ł��܂���B");
		}

        //  is�@@�l( )
        // �@@�l�q�ł��邩���ʂ��s���B
        // is�@@�l() == true �̏ꍇ
        if (l_gentradeMainAccount.isCorporation())
        {
            // ��O���X���[����B
            // �y�i�@@�l�j�����J�ݕs�G���[�z
            log.debug("�i�@@�l�j�����J�ݕs�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02934,
                this.getClass().getName() + STR_METHOD_NAME,
                "�i�@@�l�j�����J�ݕs�G���[�B");
        }

        // is�a��،��]����( )
        // �a��،��]���������{���Ă��邩���ʂ���B
        // is�a��،��]����() == true �̏ꍇ
        if (l_gentradeMainAccount.isAssetEvaluation())
        {
            // ��O���X���[����B
            // �y�i�a��،��]�����j�����J�ݕs�G���[�z
            log.debug("�i�a��،��]�����j�����J�ݕs�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02935,
                this.getClass().getName() + STR_METHOD_NAME,
                "�i�a��،��]�����j�����J�ݕs�G���[�B");
        }

        // is�M�p�����J��(�ٍϋ敪 : String)
        // �M�p�����J�݋敪���擾����B
        // [����]
        // �ٍϋ敪�F�h�w��Ȃ��h
        boolean l_blnIsMarginAccountOpen =
            l_gentradeMainAccount.isMarginAccountEstablished(
                WEB3GentradeRepaymentDivDef.DEFAULT);

        // is�敨OP�����J��(�敨�^�I�v�V�����敪 : String)
        // �敨�����J�݋敪���擾����B
        // [����]
        // �敨/�I�v�V�����敪�F�h1�h(�敨)
        // [�߂�l]
        // �敨�����J�݋敪
        boolean l_blnIsFuturesAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(
                WEB3FuturesOptionDivDef.FUTURES);

        // is�敨OP�����J��(�敨�^�I�v�V�����敪 : String)
        // �I�v�V���������J�݋敪���擾����B
        // [����]
        // �敨/�I�v�V�����敪�F�h2�h(�I�v�V����)
        // [�߂�l]
        // �I�v�V���������J�݋敪
        boolean l_blnIsOptionAccountOpen =
            l_gentradeMainAccount.isIfoAccountOpen(
                WEB3FuturesOptionDivDef.OPTION);

        // validate�����J�݉\(boolean, boolean, boolean)
        //�m�����n
        // �M�p�����J�݋敪�Fis�M�p�����J��()�̖߂�l
        // �敨�����J�݋敪�Fis�敨OP�����J��(�敨)�̖߂�l
        // �I�v�V���������J�݋敪�Fis�敨OP�����J��(�I�v�V����)�̖߂�l
        this.validateAccOpenPossible(
            l_blnIsMarginAccountOpen,
            l_blnIsFuturesAccountOpen,
            l_blnIsOptionAccountOpen);

        //validate�N����l(SubAccount, String, long)
        //[����]
        //�⏕�����Fget�⏕�����̖߂�l
        //�v���t�@@�����X���F�hsl_lowlimit.age.check�h
        //�v���t�@@�����X�̘A�ԁF 1
        WEB3GentradeBranch l_gentradeBranch =
            (WEB3GentradeBranch)l_gentradeSubAccount.getMainAccount().getBranch();
        l_gentradeBranch.validateAgeLowLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK,
            1);

        //validate�N�����l(SubAccount, String, long)
        //[����]
        //�⏕�����Fget�⏕�����̖߂�l
        //�v���t�@@�����X���F�hsl_highlimit.age.check�h
        //�v���t�@@�����X�̘A�ԁF 1
        l_gentradeBranch.validateAgeHighLimit(
            l_gentradeSubAccount,
            WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK,
            1);

        // get�����S�ۃ��[���ڋq���(����ID : long, ���t : Timestamp)
        // [����]
        // ����ID�Fget�ڋq�s().����ID
        // ���t�F���ݓ���(YYYYMMDD)
        WEB3AioSecuredLoanDataControlService l_aioSecuredLoanDataControlService =
            (WEB3AioSecuredLoanDataControlService)Services.getService(
                WEB3AioSecuredLoanDataControlService.class);

        Date l_datCurrentDay = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        Timestamp l_tsCurrentDay = new Timestamp(l_datCurrentDay.getTime());

        List l_lisStockSecuredLoanAccInfos =
            l_aioSecuredLoanDataControlService.getStockSecuredLoanAccInfo(
                l_mainAccountRow.getAccountId(),
                l_tsCurrentDay);

        // �X�g�b�N���[�������ԍ�
        String l_strStockLoanAccount = null;

        // get�����S�ۃ��[���ڋq���() == null �̏ꍇ
        if (l_lisStockSecuredLoanAccInfos == null)
        {
            // get�V���A���i���o�[(String, String)
            // �����Ŏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V���A���i���o�[�e�[�u������擾����B
            // [����]
            // �،���ЃR�[�h�F�⏕����.�،���ЃR�[�h
            // �ݒ薼�́F�@@"stock_secured_loan"
            String l_strSerialNumber =
                this.getSerialNumber(
                    l_strInstitutionCode,
                    StockSecuredLoanRow.TYPE.getTableName());

            // create�X�g�b�N���[�������ԍ�(String)
            // �X�g�b�N���[�������ԍ����Z�o����B
            //�m�����n
            // �V���A���i���o�[�Fget�V���A���i���o�[()�̖߂�l
            l_strStockLoanAccount =
                this.createStockLoanAccountNo(l_strSerialNumber);

            // insert�����S�ۃ��[��(�X�g�b�N���[�������ԍ� : String, �ڋqParams : �ڋqParams)
            // [����]
            // �X�g�b�N���[�������ԍ��Fcreate�X�g�b�N���[�������ԍ�()�̖߂�l
            // �ڋqParams�Fget�ڋq�s()�̖߂�l
            l_aioSecuredLoanDataControlService.insertStockSecuredLoan(
                l_strStockLoanAccount,
                l_mainAccountParams);

            // update�̔ԃe�[�u��(�،���ЃR�[�h : String, �̔ԍ��ږ� : String,
            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�V���A���i���o�[ : String)
            // �m�����n
            // �،���ЃR�[�h�F�⏕����.�،���ЃR�[�h
            // �̔ԍ��ږ��F�hstock_secured_loan�h
            // �V���A���i���o�[�Fcreate�X�g�b�N���[�������ԍ�()�̖߂�l�̏�9��
            // ��j�X�g�b�N���[�������ԍ� = 0712180056 �̏ꍇ
            // �����œn���̂� 071218005
            l_aioSecuredLoanDataControlService.updateCommSerialNumbers(
                l_strInstitutionCode,
                StockSecuredLoanRow.TYPE.getTableName(),
                l_strStockLoanAccount.substring(0, 9));
        }

        // get�v���t�@@�����X(String)
        // �����Ŏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B
        // [����]
        // �ݒ薼�́F�@@"46_CREDIT_URL"
        String l_strPreference = this.getPreference(WEB3SystemPreferenceDef.CREDIT_URL);

        // createResponse( )
        WEB3SLAccountOpenResponse l_response =
            (WEB3SLAccountOpenResponse)l_request.createResponse();

        // (*)�v���p�e�B�Z�b�g
        // �ȉ��̒ʂ�A�v���p�e�B��ݒ肷��B
        WEB3SLAccountBaseInfoUnit l_slAccountBaseInfoUnit =
            new WEB3SLAccountBaseInfoUnit();

        // �ڋq��(�J�i) = get�ڋq�s.�ڋq��(�J�i)
        l_slAccountBaseInfoUnit.accountNameKana =
            l_mainAccountRow.getFamilyNameAlt1();

        // �ڋq��(����) = get�ڋq�s.�ڋq��(����)
        l_slAccountBaseInfoUnit.accountName = l_mainAccountRow.getFamilyName();

        // ���N�����N�� = get�ڋq�s.���N�����N��
        l_slAccountBaseInfoUnit.eraBorn = l_mainAccountRow.getEraBorn();

        // ���N���� = get�ڋq�s.���N����
        l_slAccountBaseInfoUnit.bornDate = l_mainAccountRow.getBornDate();

        // ���� = get�ڋq�s.����
        l_slAccountBaseInfoUnit.sex = l_mainAccountRow.getSex();

        // �X�֔ԍ� = get�ڋq�s.�X�֔ԍ�
        l_slAccountBaseInfoUnit.zipCode = l_mainAccountRow.getZipCode();

        // �Z���P(����) = get�ڋq�s.�Z���P(����)
        l_slAccountBaseInfoUnit.address1 = l_mainAccountRow.getAddressLine1();

        // �Z���Q(����) = get�ڋq�s.�Z���Q(����)
        l_slAccountBaseInfoUnit.address2 = l_mainAccountRow.getAddressLine2();

        // �Z���R(����) = get�ڋq�s.�Z���R(����)
        l_slAccountBaseInfoUnit.address3 = l_mainAccountRow.getAddressLine3();

        // email�A�h���X = get�ڋq�s.email�A�h���X
        l_slAccountBaseInfoUnit.mailAddress = l_mainAccountRow.getEmailAddress();

        // (*)���X�|���X�Z�b�g
        // �ȉ��̒ʂ�A�v���p�e�B��ݒ肷��B

        // �X�g�b�N���[�������ԍ�
        // �iget�����S�ۃ��[���ڋq���() == null �̏ꍇ�j
        if (l_lisStockSecuredLoanAccInfos == null)
        {
            // �X�g�b�N���[�������ԍ� = create�X�g�b�N���[�������ԍ�()�̖߂�l
            l_response.stockLoanAccount = l_strStockLoanAccount;
        }
        else
        {
            // �iget�����S�ۃ��[���ڋq���() != null �̏ꍇ�j
            // �X�g�b�N���[�������ԍ� = get�����S�ۃ��[���ڋq���().�X�g�b�N���[�������ԍ�
            l_response.stockLoanAccount =
                ((StockSecuredLoanRow)l_lisStockSecuredLoanAccInfos.get(0)).getStockLoanAccountCode();
        }

        // �O���ڑ�URL = get�v���t�@@�����X()�̖߂�l
        l_response.url = l_strPreference;

        // �ڋq��{��� = ��L�Ńv���p�e�B�Z�b�g���s�����ڋq��{���
        l_response.accountBaseInfo = l_slAccountBaseInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�����J�݉\)<BR>
     * �،��S�ۃ��[���̌����J�݂��\���`�F�b�N���s���B<BR>
     * ���肷��v�f��<BR>
     * is�M�p�����J��()<BR>
     * is�敨OP�����J��(�敨)<BR>
     * is�敨OP�����J��(OP)<BR>
     * ��3�̃��\�b�h�ł���B<BR>
     * <BR>
     * <BR>
     * 1) �iis�M�p�����J��() == true &&<BR>
     * �@@�@@is�敨OP�����J��(�敨)==false && is�敨OP�����J��(OP)==false�j �̏ꍇ<BR>
     * �@@�@@�y�i�M�p�j�����J�ݕs�G���[�z<BR>
     * <BR>
     * 2) �iis�M�p�����J��() == false &&<BR>
     * �@@�@@�iis�敨OP�����J��(�敨)==true || is�敨OP�����J��(OP)==true�j�j �̏ꍇ<BR>
     * �@@�@@�y�i��OP�j�����J�ݕs�G���[�z<BR>
     * <BR>
     * 3) �iis�M�p�����J��() == true &&<BR>
     * �@@�@@�iis�敨OP�����J��(�敨)==true || is�敨OP�����J��(OP)==true�j�j �̏ꍇ<BR>
     * �@@�@@�y�i�M�p�A��OP�j�����J�ݕs�G���[�z<BR>
     * <BR>
     * @@param l_blnIsMarginAccountOpenDiv - (�M�p�����J�݋敪)<BR>
     * �M�p�����J�݋敪<BR>
     * @@param l_blnIsFuturesAccountOpenDiv - (�敨�����J�݋敪)<BR>
     * �敨�����J�݋敪<BR>
     * @@param l_blnIsOptionAccountOpenDiv - (�I�v�V���������J�݋敪)<BR>
     * �I�v�V���������J�݋敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46D26A8C01D7
     */
    private void validateAccOpenPossible(
        boolean l_blnIsMarginAccountOpenDiv,
        boolean l_blnIsFuturesAccountOpenDiv,
        boolean l_blnIsOptionAccountOpenDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateConAccOpenPossible(boolean, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_blnIsMarginAccountOpenDiv)
        {
            //1) �iis�M�p�����J��() == true &&
            // �@@�@@is�敨OP�����J��(�敨)==false && is�敨OP�����J��(OP)==false�j �̏ꍇ
            //�y�i�M�p�j�����J�ݕs�G���[�z
            if (!(l_blnIsFuturesAccountOpenDiv || l_blnIsOptionAccountOpenDiv))
            {
                log.debug("�i�M�p�j�����J�ݕs�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02936,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�i�M�p�j�����J�ݕs�G���[");
            }

            //�iis�M�p�����J��() == true &&
            // �@@�@@�iis�敨OP�����J��(�敨)==true || is�敨OP�����J��(OP)==true�j�j �̏ꍇ
            // �@@�@@�y�i�M�p�A��OP�j�����J�ݕs�G���[�z
            if (l_blnIsFuturesAccountOpenDiv || l_blnIsOptionAccountOpenDiv)
            {
                log.debug("�i�M�p�A��OP�j�����J�ݕs�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02938,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�i�M�p�A��OP�j�����J�ݕs�G���[");
            }
        }
        else
        {
            //2) �iis�M�p�����J��() == false &&
            // �@@�@@�iis�敨OP�����J��(�敨)==true || is�敨OP�����J��(OP)==true�j�j �̏ꍇ
            // �@@�@@�y�i��OP�j�����J�ݕs�G���[�z
            if (l_blnIsFuturesAccountOpenDiv || l_blnIsOptionAccountOpenDiv)
            {
                log.debug("�i��OP�j�����J�ݕs�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02937,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�i��OP�j�����J�ݕs�G���[");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�v���t�@@�����X)<BR>
     * �����Ŏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B<BR>
     * <BR>
     * �P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B <BR>
     * <BR>
     * �@@[�擾����] <BR>
     * �@@���́i���ϐ����j = �i�����j�ݒ薼�� <BR>
     * <BR>
     * �Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B <BR>
     * <BR>
     * @@param l_strName - (�ݒ薼��)<BR>
     * �ݒ薼��<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46D3E69D03E6
     */
    private String getPreference(String l_strName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPreference(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B
        //[�擾����]
        //���́i���ϐ����j = �i�����j�ݒ薼��
        SystemPreferencesRow l_systemPreferencerow;
        String l_strValue = null;
        try
        {
            l_systemPreferencerow = (SystemPreferencesRow)SystemPreferencesDao.findRowByName(l_strName);

            if (l_systemPreferencerow != null)
            {
                l_strValue = l_systemPreferencerow.getValue();
            }
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

    /**
     * (get�V���A���i���o�[)<BR>
     * �����Ŏw�肳�ꂽ�̔ԍ��ږ��̍̔ԃR�[�h���̔ԃe�[�u������擾����B<BR>
     * <BR>
     * �P�j�̔ԃe�[�u������ȉ��̏����Ń��R�[�h���擾����B<BR>
     * <BR>
     * �@@[�擾����]<BR>
     * �@@�،���ЃR�[�h=�i�����j�،���ЃR�[�h<BR>
     * �@@�̔ԍ��ږ�=�i�����j�̔ԍ��ږ�<BR>
     * <BR>
     * �Q�j�擾�����̔ԃe�[�u���̃��R�[�h�̐ݒ�l��ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strSerialNumberName - (�̔ԍ��ږ�)<BR>
     * �ݒ薼��<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46D3EF580278
     */
    private String getSerialNumber(
        String l_strInstitutionCode,
        String l_strSerialNumberName) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSerialNumber(String, String)";
        log.entering(STR_METHOD_NAME);

        //�@@[�擾����]
        //�،���ЃR�[�h = �i�����j�،���ЃR�[�h
        //�̔ԍ��ږ� = �i�����j�̔ԍ��ږ�
        CommSerialNumbersPK l_commSerialNumbersPK =
            new CommSerialNumbersPK(l_strInstitutionCode, l_strSerialNumberName);

        CommSerialNumbersRow l_commSerialNumbersRow = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_commSerialNumbersRow =
                (CommSerialNumbersRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_commSerialNumbersPK);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�擾�����̔ԃe�[�u���̃��R�[�h�̐ݒ�l��ԋp����B
        String l_strSerialNumber = null;
        if (l_commSerialNumbersRow != null)
        {
            l_strSerialNumber = l_commSerialNumbersRow.getSerialNumber();
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSerialNumber;
    }

    /**
     * (create�X�g�b�N���[�������ԍ�)<BR>
     * �X�g�b�N���[�������ԍ��𐶐�����B<BR>
     * <BR>
     * �ڍׂ�<BR>
     * �V�[�P���X�}�ucreate�X�g�b�N���[�������ԍ��v���Q��<BR>
     * <BR>
     * @@param l_strSerialNumber - (�V���A���i���o�[)<BR>
     * �V���A���i���o�[<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 46D3F51D0199
     */
    private String createStockLoanAccountNo(String l_strSerialNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createStockLoanAccount(String)";
        log.entering(STR_METHOD_NAME);

        if (l_strSerialNumber == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //���ݓ��t(YYMMDD)��int�^�ɕϊ�����B(YY�͐���񌅁j
        String l_strNowTime = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyMMdd");
        int l_intNowTime = Integer.parseInt(l_strNowTime);

        //����.�V���A���i���o�[��int�^�ɕϊ�����B
        int l_intSerialNumber = Integer.parseInt(l_strSerialNumber);
        int l_intSerialNumberTemp = Integer.parseInt(l_strSerialNumber.substring(0, 6));

        //new�V���A���i���o�[
        String l_strNewSerialNumber;
        int l_intSerialNumberLength = l_strSerialNumber.length();
        //int�^�̌��ݓ��t(YYMMDD) <= int�^�̈���.�V���A���i���o�[�̏�6�� �̏ꍇ
        if (l_intNowTime <= l_intSerialNumberTemp)
        {
            //new�V���A���i���o�[ = ����.�V���A���i���o�[ + 1
            l_strNewSerialNumber = String.valueOf(l_intSerialNumber + 1);

            int l_intNewSerialNumberLength = l_strNewSerialNumber.length();
            int l_intLengthTemp = l_intSerialNumberLength - l_intNewSerialNumberLength;
            String l_strZero = "";
            for (int i = 0; i < l_intLengthTemp; i++)
            {
                l_strZero = l_strZero + "0";
            }

            l_strNewSerialNumber = l_strZero + l_strNewSerialNumber;
        }
        else
        {
            //��L�ȊO�̏ꍇ
            //new�V���A���i���o�[ =  YYMMDD001
            //�iYYMMDD�͌��ݓ��t�iYY�͐���񌅁j
            //�i001�͐��l�j
            l_strNewSerialNumber = l_strNowTime + "001";
        }

        //�X�g�b�N���[�������ԍ�����
        //10���̃X�g�b�N���[�������ԍ��𐶐�����B
        char[] l_serialNumbers = l_strNewSerialNumber.toCharArray();
        int l_intCount = 0;
        //�@@ new�V���A���i���o�[9���̔ԍ���S�ĉ��Z����B
        // y+y+m+m+d+d+x+x+x
        for (int i = 0; i < l_serialNumbers.length; i++)
        {
            l_intCount = l_intCount + Integer.parseInt(String.valueOf(l_serialNumbers[i]));
        }

        //�A �@@�Ōv�Z�������l�̉�1�����擾����B
        String l_strCount = String.valueOf(l_intCount);
        int l_intCluntLength = l_strCount.length();
        String l_strCountRight = l_strCount.substring(l_intCluntLength - 1, l_intCluntLength);

        //�B �`�F�b�N�f�W�b�g = 10 - �A�Ŏ擾����1���̐��l
        int l_intFlag = 10 - Integer.parseInt(l_strCountRight);
        //�i�A��0 �̏ꍇ�A�`�F�b�N�f�W�b�g�� 0 �Ƃ���B�j
        if (l_intFlag == 10)
        {
            l_intFlag = 0;
        }

        //yymmddxxx = ��L�Ŏ擾����new�V���A���i���o�[
        //c = �`�F�b�N�f�W�b�g
        String l_strStockLoanAccount = l_strNewSerialNumber + String.valueOf(l_intFlag);

        //�X�g�b�N���[�������ԍ���ԋp
        log.exiting(STR_METHOD_NAME);
        return l_strStockLoanAccount;
    }
}
@
