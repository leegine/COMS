head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenExpAccountOpen.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�݌����q(WEB3AccOpenExpAccountOpen)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 ����� (���u) �V�K�쐬
                   2006/06/09 �юu�� (���u) �d�l�ύX ���f��048,050
                   2006/06/23 �����iSCS�j�d�l�ύX�E���f��070
                   2006/07/10 ���� (���u) �d�l�ύX ���f��026 �c�a�X�V�d�l007
                   2006/08/14 ���r (���u) �d�l�ύX ���f��090
                   2006/11/30 �����q (���u) �d�l�ύX ���f��116
Revision History : 2007/11/23 ���g (���u) �d�l�ύX ���f�� No.149
Revision History : 2008/12/12 ���m�a (���u) �d�l�ύX ���f�� No.158
Revision History : 2009/02/16 �đo�g (���u) �d�l�ύX �c�a���C�A�E�g No.052
Revision History : 2009/03/16 �đo�g (���u) �d�l�ύX �c�a���C�A�E�g No.051
Revision History : 2009/08/13 �đo�g (���u) �d�l�ύX ���f��No.170,No.179 �c�a�X�V�d�lNo.041,No.042,No.049,No.050
Revision History : 2009/09/09 �����F (���u) �d�l�ύX �c�a���C�A�E�g No.065 No.066
Revision History : 2009/09/15 �����F (���u) �d�l�ύX ���f�� No.214
Revision History : 2010/02/21 �����F (���u) �d�l�ύX ���f�� No.220
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.data.AccOpenItemMasterRow;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.data.ExpAccountOpenDao;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.YCustomerParams;
import webbroker3.accountopen.data.YCustomerRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenAnnualIncomeDivValueDef;
import webbroker3.accountopen.define.WEB3AccOpenInvestmentExperienceDef;
import webbroker3.accountopen.define.WEB3AccOpenUpdateItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenStatusDivDef;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AppliMotivatDivDef;
import webbroker3.common.define.WEB3CheckDivDef;
import webbroker3.common.define.WEB3CompanyFormationDivDef;
import webbroker3.common.define.WEB3DuplicationDivDef;
import webbroker3.common.define.WEB3ExperienceDivDef;
import webbroker3.common.define.WEB3FamilyRelationshipDef;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.define.WEB3FundBudgetDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3IdConfirmDocDivDef;
import webbroker3.common.define.WEB3InvestPurposeDivDef;
import webbroker3.common.define.WEB3ItemCheckModeDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MoneyAmountDivDef;
import webbroker3.common.define.WEB3OccupationDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PrintFlagDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3ReviewCodeDef;
import webbroker3.common.define.WEB3SexDef;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TransCommissionDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.gentrade.data.EraRow;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����J�݌����q)<BR>
 * �����J�݌����q<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccOpenExpAccountOpen implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenExpAccountOpen.class);

    /**
     * (�����J�݌����q�s)<BR>
     * �����J�݌����q�s<BR>
     * <BR>
     * �� �����J�݌����qParams�N���X��DDL��莩����������B<BR>
     */
    private ExpAccountOpenParams expAccountOpenParams;

    /**
     * (�����J�ݓ`�[�쐬�X�e�[�^�X)<BR>
     * �e�`�[�̍쐬�X�e�[�^�X�z��B<BR>
     */
    private WEB3AccOpenVoucherCreatedStatus[] accOpenVoucherCreatedStatuses;

    /**
     * (Y�q�}�X�^�s)<BR>
     * Y�q�}�X�^�s<BR> 
     * <BR>
     * �� Y�q�}�X�^Params�N���X��DDL��莩����������B<BR> 
     */
    private YCustomerParams yCustomerParams;
    
    /**
     * (�����J�ݐR���҂���񃊃X�g)<BR>
     * �����J�ݐR���҂���񃊃X�g<BR>
     */
    private ArrayList accOpenJudgeWaitingInfoList;
    
    /**
     * (�����J�݌����q)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����J�݌����q�𐶐�����B<BR>
     * <BR>
     * �����P�A�܂��͏����Q�̓��e�ŁA�����J�݌����q�e�[�u������������B<BR>
     * <BR>
     * �@@[�����P]�@@�����X�R�[�h���w��i���X�R�[�h == null�j�̏ꍇ<BR>
     * �@@�����J�݌����q.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�����J�݌����q.�����R�[�h = �����R�[�h<BR>
     * <BR>
     * �@@[�����Q]�@@�����X�R�[�h�w��i���X�R�[�h != null�j�̏ꍇ<BR>
     * �@@�����J�݌����q.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�����J�݌����q.���X�R�[�h = ���X�R�[�h<BR>
     * �@@�����J�݌����q.�����R�[�h = �����R�[�h<BR>
     * <BR>
     * �������ʂ��Q���ȏ㑶�݂���ꍇ�́A�ڋq���d�����ēo�^����Ă����<BR>
     * ���肵��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01313<BR>
     * �������ʂɈ�v����s�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �������ʂ��P���̏ꍇ�A�������ʂ̌����J�݌����q�s�I�u�W�F�N�g��<BR>
     * �����Ɏw�肵�āA�R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �����R�[�h
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 419C3469032F
     */
    public WEB3AccOpenExpAccountOpen(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenExpAccountOpen(String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�����P�A�܂��͏����Q�̓��e�ŁA�����J�݌����q�e�[�u������������B

            //[�����P]�@@�����X�R�[�h���w��i���X�R�[�h == null�j�̏ꍇ
            //�����J�݌����q.�،���ЃR�[�h = �،���ЃR�[�h
            //�����J�݌����q.�����R�[�h = �����R�[�h

            //[�����Q]�@@�����X�R�[�h�w��i���X�R�[�h != null�j�̏ꍇ
            //�����J�݌����q.�،���ЃR�[�h = �،���ЃR�[�h
            //�����J�݌����q.���X�R�[�h = ���X�R�[�h
            //�����J�݌����q.�����R�[�h = �����R�[�h

            String l_strQueryString = null;
            Object[] l_objQueryDataContainer = null;

            if (l_strBranchCode != null)
            {
                l_strQueryString = "institution_code = ? and branch_code = ? and account_code = ?";
                l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strBranchCode, l_strAccountCode};
            }
            else
            {
                l_strQueryString = "institution_code = ? and account_code = ?";
                l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strAccountCode};
            }

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer
            );
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
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

        //�������ʂɈ�v����s�����݂��Ȃ��ꍇ�́Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            throw new NotFoundException("�������ʂɈ�v����s�����݂��Ȃ�");
        }


        //�������ʂ��Q���ȏ㑶�݂���ꍇ�́A
        //�ڋq���d�����ēo�^����Ă���Ɣ��肵��O���X���[����B
        if (l_lisRecords.size() >= 2)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������ʂ��Q���ȏ㑶�݂���");
        }

        ExpAccountOpenParams l_expAccountOpenParams = (ExpAccountOpenParams)l_lisRecords.get(0);
        init(l_expAccountOpenParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�����J�݌����q)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �����J�݌����q�𐶐�����B<BR>
     * <BR>
     * �ȉ��̏����Ō����J�݌����q�e�[�u������������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����J�݌����q.�،���ЃR�[�h = �،���ЃR�[�h<BR>
     * �@@�����J�݌����q.���ʃR�[�h = ���ʃR�[�h<BR>
     * <BR>
     * �������ʂ̌����J�݌����q�s�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 41919DEA0256
     */
    public WEB3AccOpenExpAccountOpen(String l_strInstitutionCode, String l_strRequestNumber)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenExpAccountOpen(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�ȉ��̏����Ō����J�݌����q�e�[�u������������B
            //[����]
            //�����J�݌����q.�،���ЃR�[�h = �،���ЃR�[�h
            //�����J�݌����q.���ʃR�[�h = ���ʃR�[�h

            ExpAccountOpenParams l_expAccountOpenParams = (ExpAccountOpenParams)ExpAccountOpenDao.findRowByPk(
                l_strInstitutionCode,
                l_strRequestNumber);

            init(l_expAccountOpenParams);

        }
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("�������ʂɈ�v����s�����݂��Ȃ�");
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
     * (�����J�݌����q)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �P�j�@@�I�u�W�F�N�g����<BR>
     * �@@�����J�݌����q�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�����J�ݓ`�[�쐬�X�e�[�^�X�v���p�e�B�̃Z�b�g<BR>
     * �@@�����J�ݓ`�[�쐬�X�e�[�^�X.get�����J�ݓ`�[�쐬�X�e�[�^�X()�̖߂�l��<BR>
     * ���������I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �@@[get�����J�ݓ`�[�쐬�X�e�[�^�X()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h�F�@@�����J�݌����s.�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h�F�@@�����J�݌����s.���ʃR�[�h<BR>
     * <BR>
     * �Q�j�@@�����J�݌����q�s�v���p�e�B�̃Z�b�g<BR>
     * �@@�����J�݌����s�𐶐������I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g���A�ԋp����B<BR>
     * <BR>
     * �� �����J�݌����qParams�N���X��DDL��莩����������B<BR>
     * @@param l_expAccountOpenParams - �����J�݌����q�s<BR>
     * <BR>
     * �� �����J�݌����qParams�N���X��DDL��莩����������B<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 41919DAA0302
     */
    public WEB3AccOpenExpAccountOpen(ExpAccountOpenParams l_expAccountOpenParams)
        throws WEB3BaseException
    {
        init(l_expAccountOpenParams);
    }

    /**
     * (�����J�݌����q)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �����J�݌����q�𐶐�����B <BR>
     * �ȉ��̏����Ō����J�݌����q�e�[�u������������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�����J�݌����q.�،���ЃR�[�h = �،���ЃR�[�h <BR>
     * �@@����.���X�R�[�h != null�̏ꍇ�́Aand�ŉ��L��ǉ� <BR>
     * �@@�����J�݌����q.���X�R�[�h = ���X�R�[�h <BR>
     * �@@����.���ʃR�[�h != null�̏ꍇ�́Aand�ŉ��L��ǉ� <BR>
     * �@@�����J�݌����q.���ʃR�[�h = ���ʃR�[�h <BR>
     * �@@����.�����R�[�h != null�̏ꍇ�́Aand�ŉ��L��ǉ� <BR>
     * �@@�����J�݌����q.�����R�[�h = �����R�[�h <BR>
     * <BR>
     * <BR>
     * �������ʂ��Q���ȏ㑶�݂���ꍇ�́A�ڋq���d�����ēo�^����Ă���Ɣ��肵��O���X���[����B <BR>
     * �������ʂɈ�v����s�����݂��Ȃ��ꍇ�́Anull��ԋp����B <BR>
     * <BR>
     * �������ʂ��P���̏ꍇ�A�������ʂ̌����J�݌����q�s�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �R���X�g���N�^���R�[������B <BR>
     * �R���X�g���N�^�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_strAccountCode - �����R�[�h
     * @@return WEB3AccOpenExpAccountOpen
     * @@throws WEB3BaseException, NotFoundException
     */
    public WEB3AccOpenExpAccountOpen(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strAccountCode)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenExpAccountOpen(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strQueryString = null;

            Object[] l_objQueryDataContainer = null;
            List l_lisQueryDataContainer = new ArrayList();

            l_strQueryString = "institution_code = ? ";
            l_lisQueryDataContainer.add(l_strInstitutionCode);

            //����.���X�R�[�h != null�̏ꍇ�́Aand�ŉ��L��ǉ� �����J�݌����q.���X�R�[�h = ���X�R�[�h
            if (l_strBranchCode != null)
            {
                l_strQueryString = l_strQueryString + " and branch_code = ? ";
                l_lisQueryDataContainer.add(l_strBranchCode);
            }
            //����.���ʃR�[�h != null�̏ꍇ�́Aand�ŉ��L��ǉ� �����J�݌����q.���ʃR�[�h = ���ʃR�[�h
            if (l_strRequestNumber != null)
            {
                l_strQueryString = l_strQueryString + " and acc_open_request_number = ? ";
                l_lisQueryDataContainer.add(l_strRequestNumber);
            }
            //����.�����R�[�h != null�̏ꍇ�́Aand�ŉ��L��ǉ� �����J�݌����q.�����R�[�h = �����R�[�h
            if (l_strAccountCode != null)
            {
                l_strQueryString = l_strQueryString + " and account_code = ? ";
                l_lisQueryDataContainer.add(l_strAccountCode);
            }

            l_objQueryDataContainer = new Object[l_lisQueryDataContainer.size()];
            l_lisQueryDataContainer.toArray(l_objQueryDataContainer);
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
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

        //�������ʂɈ�v����s�����݂��Ȃ��ꍇ�́Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            throw new NotFoundException("�������ʂɈ�v����s�����݂��Ȃ�");
        }

        //�������ʂ��Q���ȏ㑶�݂���ꍇ�́A
        //�ڋq���d�����ēo�^����Ă���Ɣ��肵��O���X���[����B
        if (l_lisRecords.size() >= 2)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                this.getClass().getName() + STR_METHOD_NAME,
                "�������ʂ��Q���ȏ㑶�݂���");
        }

        ExpAccountOpenParams l_expAccountOpenParams = (ExpAccountOpenParams)l_lisRecords.get(0);
        init(l_expAccountOpenParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�`�[�X�e�[�^�X)<BR>
     * �e�����J�ݓ`�[�̃X�e�[�^�X���擾����B<BR>
     * <BR>
     * this.�����J�ݓ`�[�X�e�[�^�X[]��ԋp����B<BR>
     * @@return webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus[]
     * @@roseuid 419196370081
     */
    public WEB3AccOpenVoucherCreatedStatus[] getVoucherStatus()
    {
        return this.accOpenVoucherCreatedStatuses;
    }

    /**
     * (get�����J�ݏ󋵋敪)<BR>
     * �����J�ݏ󋵋敪���擾����B<BR>
     * <BR>
     * �����J�ݏ󋵋敪<BR>
     * �@@0�F�@@DEFAULT�i���J�݁j<BR>
     * �@@1�F�@@�J�ݒ�<BR>
     * �@@2�F�@@�G���[����<BR>
     * �@@3�F�@@�J�ݍ�<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����ς̔���<BR>
     * �@@�����J�݊����ρithis.�����J�݌����q�s.�����o�^�� != null�j�̏ꍇ�A<BR>
     * �h�J�ݍρh��ԋp����B<BR>
     * <BR>
     * �Q�j�@@���J�݁C�G���[�����C������̔���<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�`�[�쐬�X�e�[�^�X�R�[�h�擾<BR>
     * �@@�@@�ithis.�����J�ݓ`�[�X�e�[�^�X[] == null�j�̏ꍇ�A<BR>
     * �hDEFAULT�i���J�݁j�h��ԋp����B<BR>
     * �@@�@@this.�����J�ݓ`�[�X�e�[�^�X[]�e�v�f�̓`�[�쐬�X�e�[�^�X��<BR>
     * �z��Ŏ擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�X�e�[�^�X����<BR>
     * �@@�@@�擾�����`�[�쐬�X�e�[�^�X�R�[�h�ɂ��āA�ȉ��̔�����s���B<BR>
     * <BR>
     * �@@�@@�|���ׂĂ̗v�f�̓`�[�쐬�X�e�[�^�X���hDEFAULT�i�`�[���쐬�j�h�̏ꍇ�A<BR>
     * �hDEFAULT�i���J�݁j�h��ԋp����B<BR>
     * �@@�@@�|�`�[�쐬�X�e�[�^�X�Ɂh���M�G���[�h�C�܂��́h��M�G���[�h��<BR>
     * �P�ł��܂܂��ꍇ�A�h�G���[�����h��ԋp����B<BR>
     * �@@�@@�|��L�ȊO�A�h�J�ݒ��h��ԋp����B<BR>
     * @@return String
     * @@roseuid 419C6F580022
     */
    public String getAccountOpenStatusDiv()
    {
        final String STR_METHOD_NAME = " getAccountOpenStatusDiv()";
        log.entering(STR_METHOD_NAME);


        //�����ς̔���
        //�����J�݊����ρithis.�����J�݌����q�s.�����o�^�� != null�j�̏ꍇ�A�h�J�ݍρh��ԋp����B
        if (this.expAccountOpenParams.getAccountOpenDate() != null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenStatusDivDef.OPEN_COMPLETE;
        }

        //�ithis.�����J�ݓ`�[�X�e�[�^�X[] == null�j�̏ꍇ�A�hDEFAULT�i���J�݁j�h��ԋp����B
        if (this.accOpenVoucherCreatedStatuses == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenStatusDivDef.DEFAULT;
        }

        boolean l_blnIsAllDefault = true;

        for (int i = 0; i < this.accOpenVoucherCreatedStatuses.length; i++)
        {
            String l_strVoucherStatus = accOpenVoucherCreatedStatuses[i].getVoucherStatus();
            if (!WEB3VoucherStatusDef.DEFAULT.equals(l_strVoucherStatus))
            {
                l_blnIsAllDefault = false;
            }

            //�`�[�쐬�X�e�[�^�X�Ɂh���M�G���[�h�C�܂��́h��M�G���[�h���P�ł��܂܂��ꍇ�A�h�G���[�����h��ԋp����B
            if (WEB3VoucherStatusDef.SEND_ERROR.equals(l_strVoucherStatus)
                || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_strVoucherStatus))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountOpenStatusDivDef.ERROR;
            }
        }

        if (l_blnIsAllDefault)
        {
            //���ׂĂ̗v�f�̓`�[�쐬�X�e�[�^�X���hDEFAULT�i�`�[���쐬�j�h�̏ꍇ�A�hDEFAULT�i���J�݁j�h��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenStatusDivDef.DEFAULT;
        }

        //��L�ȊO�A�h�J�ݒ��h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenStatusDivDef.OPENING;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4192CD5C015F
     */
    public String getInstitutionCode()
    {
        return this.expAccountOpenParams.getInstitutionCode();
    }

    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.���X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4193443902D5
     */
    public String getBranchCode()
    {
        return this.expAccountOpenParams.getBranchCode();
    }

    /**
     * (get�����R�[�h)<BR>
     * �����R�[�h���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�����R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41945E0C035D
     */
    public String getAccountCode()
    {
        return this.expAccountOpenParams.getAccountCode();
    }

    /**
     * (get�����敪)<BR>
     * �����敪���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�����敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 4194624900FC
     */
    public String getAccountDiv()
    {
        return this.expAccountOpenParams.getAccountDiv();
    }

    /**
     * (get���ʃR�[�h)<BR>
     * ���ʃR�[�h���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.���ʃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4192CE200065
     */
    public String getRequestNumber()
    {
        return this.expAccountOpenParams.getAccOpenRequestNumber();
    }

    /**
     * (get���ڒl)<BR>
     * this.�����J�݌����q�s���A�����̗񕨗������������ڂ̒l���擾���ԋp����B<BR>
     * @@param l_strColumnName - �񕨗���<BR>
     * <BR>
     * �������J�݌����q�e�[�u���̗񕨗���<BR>
     *
     * @@return Object
     * @@roseuid 4192D56A0314
     */
    public Object getItemValue(String l_strColumnName)
    {
        return this.expAccountOpenParams.getColumn(l_strColumnName);
    }

    /**
     * (is�`�[�쐬�\)<BR>
     * �`�[�쐬���\�ȏ�Ԃ��𔻒肷��B<BR>
     * <BR>
     * �����J�݊����ρithis.�����J�݌����q�s.�����o�^�� != null�j�̏ꍇ�A<BR>
     * false��ԋp����B<BR>
     * �ȊO�̏ꍇ�A�ȉ��̔�����s���B<BR>
     * <BR>
     * �� ONLINE���ԑт̏ꍇ�i������ԊǗ�.is�g���K���s() == true�j<BR>
     * <BR>
     * �@@�@@�����J�ݓ`�[�쐬�X�e�[�^�X.get�`�[�쐬�X�e�[�^�X()���ȉ���<BR>
     * ���ꂩ�ł���ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@DEFAULT�i�`�[���쐬�j<BR>
     * �@@�@@�@@���M�G���[<BR>
     * �@@�@@�@@��M�G���[<BR>
     * <BR>
     * �@@�@@�A���A�o���M�ۗ����C�쐬�ρp�̏ꍇ�A�ڋq�o�^�iG11�j�`�[��<BR>
     * �X�e�[�^�X���擾(*1)�A<BR>
     * �@@�@@�ڋq�o�^�iG11�j�`�[�̃X�e�[�^�X���A�oDEFAULT�i�`�[���쐬�j�C<BR>
     * ���M�G���[�C��M�G���[�p��<BR>
     * �@@�@@���ꂩ�������ꍇ�́Atrue��ԋp����B<BR>
     * <BR>
     * �@@�@@�ȊO�́Afalse��ԋp����B<BR>
     * <BR>
     * �@@�@@(*1) �ڋq�o�^�iG11�j�`�[�̃X�e�[�^�X�̎擾<BR>
     * <BR>
     * �@@�@@this.�����J�ݓ`�[�쐬�X�e�[�^�X[]���A�i�`�[�R�[�h == �ڋq�o�^�j<BR>
     * �ł���ŏ��̗v�f���擾����B<BR>
     * �@@�@@�擾�����v�f�̓`�[�쐬�X�e�[�^�X���ڋq�o�^�iG11�j�`�[��<BR>
     * �X�e�[�^�X�Ƃ���B<BR>
     * �@@�@@�A���A�i�`�[�R�[�h == �ڋq�o�^�j�̗v�f���P�����Ȃ������ꍇ�́A<BR>
     * DEFAULT�i�`�[���쐬�j�Ƃ���B<BR>
     * <BR>
     * �� OFFLINE���ԑт̏ꍇ�i������ԊǗ�.is�g���K���s() == false�j<BR>
     * <BR>
     * �@@�@@�����J�ݓ`�[�쐬�X�e�[�^�X.get�`�[�쐬�X�e�[�^�X()���ȉ���<BR>
     * ���ꂩ�ł���ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@DEFAULT�i�`�[���쐬�j<BR>
     * �@@�@@�@@�쐬��<BR>
     * �@@�@@�@@���M�ۗ���<BR>
     * �@@�@@�@@���M�G���[<BR>
     * �@@�@@�@@��M�G���[<BR>
     * <BR>
     * �@@�@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_accOpenVoucherCreatedStatus - �����J�ݓ`�[�쐬�X�e�[�^�X
     * @@return boolean
     * @@roseuid 419331D0020A
     */
    public boolean isVoucherCreatedPossible(WEB3AccOpenVoucherCreatedStatus l_accOpenVoucherCreatedStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isVoucherCreatedPossible(WEB3AccOpenVoucherCreatedStatus)";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenVoucherCreatedStatus == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (this.expAccountOpenParams != null && this.expAccountOpenParams.getAccountOpenDate() != null)
        {
            log.debug("�����J�݊����ρithis.�����J�݌����q�s.�����o�^�� != null�j�̏ꍇ�Afalse��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            log.debug("ONLINE���ԑт̏ꍇ�i������ԊǗ�.is�g���K���s() == true�j");
            
            if (WEB3VoucherStatusDef.DEFAULT.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus()))
            {
                log.debug("�����J�ݓ`�[�쐬�X�e�[�^�X.get�`�[�쐬�X�e�[�^�X()��(DEFAULT�i�`�[���쐬�j, ���M�G���[, ��M�G���[)�̉��ꂩ�ł���ꍇ�Atrue��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else if (WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_RESERVING.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus()))
            {
                //�i�`�[�R�[�h == �ڋq�o�^�j�̗v�f���P�����Ȃ������ꍇ�́ADEFAULT�i�`�[���쐬�j�Ƃ���B
                String l_strRegVoucherStatus = WEB3VoucherStatusDef.DEFAULT;

                if (this.accOpenVoucherCreatedStatuses != null)
                {
                    int l_intLength = this.accOpenVoucherCreatedStatuses.length;
                    
                    //this.�����J�ݓ`�[�쐬�X�e�[�^�X[]���A�i�`�[�R�[�h == �ڋq�o�^�j�ł���ŏ��̗v�f���擾����B 
                    //�擾�����v�f�̓`�[�쐬�X�e�[�^�X���ڋq�o�^�iG11�j�`�[�̃X�e�[�^�X�Ƃ���B
                    for (int i = 0; i < l_intLength; i++)
                    {
                        if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST.equals(
                            ((AccOpenVoucherStatusRow) this.accOpenVoucherCreatedStatuses[i].getDataSourceObject()).getRequestCode()))
                        {
                            log.debug("�`�[�R�[�h == �ڋq�o�^");
                            l_strRegVoucherStatus = this.accOpenVoucherCreatedStatuses[i].getVoucherStatus();
                            break;
                        }
                    }
                }
                
                if (WEB3VoucherStatusDef.DEFAULT.equals(l_strRegVoucherStatus)
                        || WEB3VoucherStatusDef.SEND_ERROR.equals(l_strRegVoucherStatus)
                        || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_strRegVoucherStatus))
                {
                    log.debug("�ڋq�o�^�iG11�j�`�[�̃X�e�[�^�X���A�oDEFAULT�i�`�[���쐬�j�C���M�G���[�C��M�G���[�p�̉��ꂩ�������ꍇ�́Atrue��ԋp����B");
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            
            log.debug("false��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.debug("OFFLINE���ԑт̏ꍇ�i������ԊǗ�.is�g���K���s() == false�j");
            
            if (WEB3VoucherStatusDef.DEFAULT.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_RESERVING.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus()))
            {
                log.debug("�����J�ݓ`�[�쐬�X�e�[�^�X.get�`�[�쐬�X�e�[�^�X()��(DEFAULT�i�`�[���쐬�j,�쐬��,���M�ۗ���,���M�G���[,��M�G���[)�̉��ꂩ�ł���ꍇ�Atrue��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.debug("false��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
    }

    /**
     * (is�`�[�쐬�\)<BR>
     * �����J�݌����q�Ɋ֘A����`�[�ɂ��āA���ׂč쐬�s�ł����false�A<BR>
     * �ǂꂩ�P�ł��쐬�ł����true��ԋp����B<BR>
     * <BR>
     * �P�j  �����J�ݐR���敪���� <BR>
     *   �P�|�P�j �R���敪���u�R���҂��v�u�۔F�v�ithis.get�����J�ݐR���敪() == 0 OR 2�j�̏ꍇ�A <BR>
     *            false��ԋp����B <BR>
     * <BR>
     *   �P�|�Q�j  �R���敪���u�F�v�ithis.get�����J�ݐR���敪() == 1�j�̏ꍇ�A <BR>
     *             this.�����J�ݓ`�[�X�e�[�^�X�̓`�[�쐬�X�e�[�^�X��ɑ��݂���l�� <BR>
     *             (�u�`�[���쐬�v�A�u�쐬�ρv�A�u���M�ۗ����v�̂�)  && 
	 *		 	   (�u�쐬�ρv���� > 0 �̏ꍇ�A�܂��́u���M�ۗ����v���� > 0 )�̏ꍇ <BR>
     *             false��ԋp����B <BR>
     * <BR>
     * <BR>
     * �Q�j  �e�`�[�쐬�۔��� <BR>
     * this.�����J�ݓ`�[�쐬�X�e�[�^�X[]�̊e�v�f���ɁA<BR>
     * this.is�`�[�쐬�\(.�����J�ݓ`�[�쐬�X�e�[�^�X[index])���R�[������B<BR>
     * �R�[���������ʂ̖߂�l���A���ׂ�false�������ꍇ��false��ԋp����B<BR>
     * �P���ł�true�ł���΁Atrue��ԋp����B<BR>
     * <BR>
     * �A���A�ithis.�����J�ݓ`�[�쐬�X�e�[�^�X[] == null�j�̏ꍇ�Atrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 419337500045
     */
    public boolean isVoucherCreatedPossible() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isVoucherCreatedPossible()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j  �����J�ݐR���敪���� 
		//�P�|�P�j �R���敪���u�R���҂��v�u�۔F�v�ithis.get�����J�ݐR���敪() == 0 OR 2�j�̏ꍇ�A 
		//		 false��ԋp����B
		String l_strAccountOpenStatusJudgeDiv = this.getAccountOpenStatusJudgeDiv();
        
        if (WEB3CheckDivDef.CHECK_WAITING.equals(l_strAccountOpenStatusJudgeDiv)
            || WEB3CheckDivDef.DISAGREE.equals(l_strAccountOpenStatusJudgeDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
		//�P�|�Q�j  �R���敪���u�F�v�ithis.get�����J�ݐR���敪() == 1�j�̏ꍇ�A 
		//		   this.�����J�ݓ`�[�X�e�[�^�X�̓`�[�쐬�X�e�[�^�X��ɑ��݂���l�� 
		//		   (�u�`�[���쐬�v�A�u�쐬�ρv�A�u���M�ۗ����v�̂�)  && 
		//		   (�u�쐬�ρv���� > 0 �̏ꍇ�A�܂��́u���M�ۗ����v���� > 0 )�̏ꍇ�A 
		//		   false��ԋp����B
        if (WEB3CheckDivDef.AGREE.equals(l_strAccountOpenStatusJudgeDiv))
        {
			if (this.accOpenVoucherCreatedStatuses != null)
			{
				int l_intLength = this.accOpenVoucherCreatedStatuses.length;
            	boolean l_statusCheckFlag = true;
            	// �쐬�ϓ`�[�J�E���^
            	int l_intCreateComplete = 0;
            	// ���M�ۗ����J�E���^
				int l_intSendReserving = 0;
				for (int i = 0; i < l_intLength; i++)
				{
					String l_strVaucherStatus = 
					    this.accOpenVoucherCreatedStatuses[i].getVoucherStatus();

                    // �u�`�[���쐬�v�u�쐬�ρv�u���M�ۗ����v�ȊO�� �Q�j�e�`�[�쐬�۔��� ��
					if (!WEB3VoucherStatusDef.DEFAULT.equals(l_strVaucherStatus) 
					       && !WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_strVaucherStatus)
					       && !WEB3VoucherStatusDef.SEND_RESERVING.equals(l_strVaucherStatus))
					{
						l_statusCheckFlag = false;
						break;
					}
					// �u�쐬�ρv�X�e�[�^�X�J�E���g
					if (WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_strVaucherStatus))
					{
						l_intCreateComplete++;
					}
					// �u���M�ۗ����v�X�e�[�^�X�J�E���g
					if (WEB3VoucherStatusDef.SEND_RESERVING.equals(l_strVaucherStatus))
					{
						l_intSendReserving++;
					}

				}
				if ((l_statusCheckFlag && l_intCreateComplete > 0) || 
								(l_statusCheckFlag && l_intSendReserving > 0) )
				{
					log.exiting(STR_METHOD_NAME);
					return false;
				}
			}
        }
        
        //�Q�j  �e�`�[�쐬�۔���
        if (this.accOpenVoucherCreatedStatuses == null 
                  || this.accOpenVoucherCreatedStatuses.length == 0)
        {
            log.debug("�ithis.�����J�ݓ`�[�쐬�X�e�[�^�X[] == null�j�̏ꍇ�Atrue��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (this.accOpenVoucherCreatedStatuses != null)
        {
            int l_intLength = this.accOpenVoucherCreatedStatuses.length;
            
            for (int i = 0; i < l_intLength; i++)
            {
                if (this.isVoucherCreatedPossible(this.accOpenVoucherCreatedStatuses[i]))
                {
                    log.debug("�P���ł�true�ł���΁Atrue��ԋp����B");
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�X�V�\)<BR>
     * �����J�݌����q�Ɋ֘A����`�[�ɂ��āA���ׂĖ��쐬�ł����true�A<BR>
     * �ȊO��false��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����J�ݍϔ���<BR>
     * �@@�����J�݊����ρithis.�����J�݌����q�s.�����o�^�� != null�j�̏ꍇ�A<BR>
     * �@@false��ԋp����B<BR>
     * <BR>
     * �Q�j  �����J�ݏ󋵐R���敪����<BR> 
     *   �@@�@@�R���敪���u�R�����v�u�p���v<BR>
     *   �@@�ithis.get�����J�ݏ󋵐R���敪() == "4" OR "5"�j�̏ꍇ�A<BR>
     *   �@@�@@false��ԋp����B<BR> 
     * <BR>
     * �R�j�@@�`�[�쐬�X�e�[�^�X�`�F�b�N<BR> 
     * <BR>
     * �@@���@@�ithis.�����J�ݓ`�[�쐬�X�e�[�^�X[] == null�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@���@@this.�����J�ݓ`�[�쐬�X�e�[�^�X[]�e�v�f�ɂ��āA<BR>
     * �@@�@@�@@get�`�[�쐬�X�e�[�^�X()�ɂē`�[�쐬�X�e�[�^�X���擾����B<BR>
     * �@@�@@�@@���ׂĂ̓`�[�쐬�X�e�[�^�X��DEFAULT�i�`�[���쐬�j�̏ꍇ�A<BR>
     * �@@�@@�@@true��ԋp����B<BR>
     * �@@���@@�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     * @@roseuid 419338C40007
     */
    public boolean isUpdatedPossible() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isUpdatedPossible()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����J�ݍϔ���
        if (this.expAccountOpenParams != null && this.expAccountOpenParams.getAccountOpenDate() != null)
        {
            log.debug("�����J�݊����ρithis.�����J�݌����q�s.�����o�^�� != null�j�̏ꍇ�Afalse��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j  �����J�ݏ󋵐R���敪���� 
        //�R���敪���u�R�����v�u�p���v�̏ꍇ
        if (WEB3AccountOpenStatusDivDef.JUDGEING.equals(this.getAccountOpenStatusCheckDiv())
            || WEB3AccountOpenStatusDivDef.REJECTION.equals(this.getAccountOpenStatusCheckDiv())) 
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�R�j�@@�`�[�쐬�X�e�[�^�X�`�F�b�N
        if (this.accOpenVoucherCreatedStatuses == null || this.accOpenVoucherCreatedStatuses.length == 0)
        {
            log.debug("�ithis.�����J�ݓ`�[�쐬�X�e�[�^�X[] == null�j�̏ꍇ�Atrue��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            int l_intLength = this.accOpenVoucherCreatedStatuses.length;
            int l_intStatusCnt = 0;
            for (int i = 0; i < l_intLength; i++)
            {
                if (WEB3VoucherStatusDef.DEFAULT.equals(this.accOpenVoucherCreatedStatuses[i].getVoucherStatus()))
                {
                    l_intStatusCnt += 1;
                }
                else
                {
                    log.debug("false��ԋp����B");
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            if (l_intLength != 0 && l_intLength == l_intStatusCnt)
            {
                log.debug("���ׂĂ̓`�[�쐬�X�e�[�^�X��DEFAULT�i�`�[���쐬�j�̏ꍇ�Atrue��ԋp����B");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            
            log.debug("false��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is����\)<BR>
     * <BR>
     * �����J�݌����q�Ɋ֘A����`�[�ɂ��āA���ׂč쐬�\�ł����true�A�ǂꂩ�P�ł��s�ł����false��ԋp����B<BR>
     * <BR>
     * this.�����J�ݓ`�[�쐬�X�e�[�^�X[]�̊e�v�f���ɁA<BR>
     * this.is�`�[�쐬�\(.�����J�ݓ`�[�쐬�X�e�[�^�X[index])���R�[������B<BR>
     * �R�[���������ʂ̖߂�l���A���ׂ�true�������ꍇ��true��ԋp����B<BR>
     * �P���ł�false�ł���΁Afalse��ԋp����B<BR>
     * <BR>
     * �A���A�ithis.�����J�ݓ`�[�쐬�X�e�[�^�X[] == null�j�̏ꍇ�A<BR>
     * �܂��́A�ithis.�����J�ݓ`�[�쐬�X�e�[�^�X[] �����ׂ� 0:DEFAULT�j�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �܂��Afalse�̏ꍇ�ňȉ��̏����ɓ��Ă͂܂�ꍇ��true��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�� OFFLINE���ԑт̏ꍇ�i������ԊǗ�.is�s��J�ǎ��ԑ�() == false�j<BR>
     * �@@�@@�ڋq�o�^�iG11�j�`�[�̃X�e�[�^�X(*1)���h���M�ρh��<BR>
     * �@@�@@���A�ڋq�o�^�iG11�j�`�[�ȊO�̓`�[�X�e�[�^�X�����ׂĈȉ��̉��ꂩ�̏ꍇ�B<BR>
     * <BR>
     * �@@�@@�@@DEFAULT�i�`�[���쐬�j<BR>
     * �@@�@@�@@�쐬��<BR>
     * �@@�@@�@@���M�ۗ���<BR>
     * �@@�@@�@@���M�G���[<BR>
     * �@@�@@�@@��M�G���[<BR>
     * <BR>
     * �@@�@@(*1) �ڋq�o�^�iG11�j�`�[�̃X�e�[�^�X�̎擾<BR>
     * <BR>
     * �@@�@@this.�����J�ݓ`�[�쐬�X�e�[�^�X[]���A�i�`�[�R�[�h == �ڋq�o�^�j�ł���ŏ��̗v�f���擾����B<BR>
     * �@@�@@�擾�����v�f�̓`�[�쐬�X�e�[�^�X���ڋq�o�^�iG11�j�`�[�̃X�e�[�^�X�Ƃ���B<BR>
     * �@@�@@�A���A�i�`�[�R�[�h == �ڋq�o�^�j�̗v�f���P�����Ȃ������ꍇ�́ADEFAULT�i�`�[���쐬�j�Ƃ���B<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 419C1A6303AC
     */
    public boolean isCanceledPossible() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCanceledPossible()";
        log.entering(STR_METHOD_NAME);

        if (this.accOpenVoucherCreatedStatuses == null || 
            this.accOpenVoucherCreatedStatuses.length == 0)
        {
            log.debug("�`�[�X�e�[�^�X���Ȃ��ꍇ�i�`�[���쐬���Ă��Ȃ��ꍇ�j�A" +
                "[����s��]��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        int l_intLength = this.accOpenVoucherCreatedStatuses.length;
        int l_intFalseCount = 0;
        int l_intDefaultCount = 0;
        String l_strAccRegStatus = WEB3VoucherStatusDef.DEFAULT;
        
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3AccOpenVoucherCreatedStatus l_status =
                accOpenVoucherCreatedStatuses[i];
            
            if (WEB3VoucherStatusDef.DEFAULT.equals(
                l_status.getVoucherStatus()))
            {
                l_intDefaultCount ++;
            }
            
            AccOpenVoucherStatusRow l_statusRow =
                (AccOpenVoucherStatusRow)l_status.getDataSourceObject();
            String l_strCode = l_statusRow.getRequestCode();
            
            if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() &&
                WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST.equals(l_strCode))
            {
                l_strAccRegStatus = l_status.getVoucherStatus();
            }

            if (!this.isVoucherCreatedPossible(l_status))
            {
                l_intFalseCount ++;
            }
        }
        
        if (l_intLength == l_intDefaultCount)
        {
            log.debug("�`�[�X�e�[�^�X�����ׂ�Default�̏ꍇ�i�`�[����ς̏ꍇ�j�A" +
                "[����s��]��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        if (l_intFalseCount == 1 && 
            WEB3VoucherStatusDef.SEND_COMPLETE.equals(l_strAccRegStatus)) {
            log.debug("OFFLINE && �ڋq�o�^�iG11�j�����M�ρA���̃X�e�[�^�X��true�̏ꍇ�A" +
                "[����\]��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (l_intFalseCount == 0) {
            log.debug("���ׂĂ̓`�[�X�e�[�^�X��true�̏ꍇ�A" +
                "[����\]��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return true;
        } else {
            log.debug("�`�[�X�e�[�^�X�ɂP�ł�false���܂܂��ꍇ�A" +
                "[����s��]��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
    }

    /**
     * (validate�����J�ݐ\�����)<BR>
     * �����J�ݐ\���f�[�^�̓��̓`�F�b�N���s���B<BR>
     * <BR>
     * �e�ЃJ�X�^�}�C�Y������΁A�����J�ݍ��ڃ}�X�^�e�[�u����<BR>
     * �w�肳�ꂽ�`�F�b�N���s���B<BR>
     * �e�ЃJ�X�^�}�C�Y���Ȃ���΁ADB���C�A�E�g �u�����J�݌����q�e�[�u��.xls�v��<BR>
     * �L�q���ꂽ�f�t�H���g�`�F�b�N���s���B<BR>
     * <BR>
     * this.�����J�݌����q�s�̃`�F�b�N�Ώۍ���(*1)���ׂĂɂ��āA<BR>
     * �ȉ��P�j�`�S�j�̃`�F�b�N���s���B<BR>
     * <BR>
     * �������A�ȉ��̏����̏ꍇ�A�P�j�`�S�j�̃`�F�b�N�͍s��Ȃ��B<BR> 
     * �`�F�b�N�Ώۍ���(*1) = �����R�[�h(account_code) <BR>
     * ���A�`�F�b�N�^�C�v = 3�F�`�[�쐬 <BR>
     * ���A�ڋq�R�[�h�����̔ԃt���O = 1�F�����̔Ԃ��s��<BR> 
     * <BR>
     * ��L�̏����𖞂����ꍇ�A�`�F�b�N�Ώۍ���(*1)�̒l�̖����̓`�F�b�N���s���B <BR>
     * ���͂��������ꍇ�A�u�����̔Ԏw�莞�ɁA�ڋq�R�[�h�����͂���Ă��܂��B�v�̗�O���X���[���� <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02527<BR>
     * <BR>
     * �P�j�@@�����J�ݍ��ڃ}�X�^����<BR>
     * �@@�����J�ݍ��ڃ}�X�^�𐶐�����B<BR>
     * �@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈����@@]<BR>
     * �@@�،���ЃR�[�h�F�@@get�،���ЃR�[�h()<BR>
     * �@@���X�R�[�h�F�@@get���X�R�[�h()<BR>
     * �@@�����敪�F�@@get�����敪()<BR>
     * �@@�`�F�b�N�^�C�v�F�@@�`�F�b�N�^�C�v<BR>
     * �@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈����A]<BR>
     * �@@�،���ЃR�[�h�F�@@get�،���ЃR�[�h()<BR>
     * �@@���X�R�[�h�F�@@�h000�h<BR>
     * �@@�����敪�F�@@get�����敪()<BR>
     * �@@�`�F�b�N�^�C�v�F�@@�`�F�b�N�^�C�v<BR>
     * �@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)<BR>
     * <BR>
     * �@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f���A<BR>
     * �@@�����J�ݍ��ڃ}�X�^.getDefault���ڃ}�X�^()�ɂăC���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[getDefault���ڃ}�X�^()�Ɏw�肷�����]<BR>
     * �@@�K�{���ڃt���O�F�@@<BR>
     * �`�F�b�N�Ώۍ���(*1)���yNull�z���ڂł����BooleanEnum.FALSE�A<BR>
     * �@@�yNotNull�z���ڂł���΁ABooleanEnum.TRUE���w�肷��B<BR>
     * �@@���ڍő咷�F�@@�`�F�b�N�Ώۍ���(*1)�́ySIZE�z<BR>
     * �@@���ڃ`�F�b�N�����F�@@�`�F�b�N�Ώۍ���(*1)�́y���ڃ`�F�b�N�����iWEB3�f�t�H���g�j�z<BR>
     * <BR>
     * �Q�j�@@�K�{���ڃ`�F�b�N<BR>
     * �@@�P�j�Ő������������J�ݍ��ڃ}�X�^.validate�K�{����()���R�[������B<BR>
     * �@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01309<BR>
     *          �ǉ�������: [���ږ�]<BR>
     * <BR>
     * �@@[validate�K�{����()�Ɏw�肷�����]<BR>
     * �@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l<BR>
     * <BR>
     * �R�j�@@�f�[�^�����O�X�`�F�b�N<BR>
     * �@@�P�j�Ő������������J�ݍ��ڃ}�X�^.validate�����O�X()���R�[������B<BR>
     * �@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01310<BR>
     *          �ǉ�������: [���ږ�]<BR>
     * <BR>
     * �@@[validate�����O�X()�Ɏw�肷�����]<BR>
     * �@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l<BR>
     * <BR>
     * �S�j�@@�L���l�`�F�b�N<BR>
     * �@@���@@�L���R�[�h�l�`�F�b�N�̏ꍇ�i�����J�ݍ��ڃ}�X�^.is�L���R�[�h�`�F�b�N() == true�j<BR>
     * <BR>
     * �@@�@@�����J�ݍ��ڑ����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�����@@��null���ԋp���ꂽ�ꍇ�́A�����A�Ő�������B<BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�̈����@@]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@get�،���ЃR�[�h()<BR>
     * �@@�@@���X�R�[�h�F�@@get���X�R�[�h()<BR>
     * �@@�@@�����敪�F�@@get�����敪()<BR>
     * �@@�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)<BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�̈����A]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@get�،���ЃR�[�h()<BR>
     * �@@�@@���X�R�[�h�F�@@�h000�h<BR>
     * �@@�@@�����敪�F�@@get�����敪()<BR>
     * �@@�@@���ڕ������F�@@�`�F�b�N�Ώۍ���(*1)<BR>
     * <BR>
     * �@@�@@�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^��<BR>
     * �Ȃ��Ɣ��f��WEB3�f�t�H���g�`�F�b�N���s���B<BR>
     * �@@�@@�`�F�b�N�Ώۍ���(*1)�̒l�����݂���R�[�h�l�łȂ���΁A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01311<BR>
     *          �ǉ�������: [���ږ�]<BR>
     * �@@�@@���`�F�b�N�Ώۍ���(*1)�́y�����i�L���R�[�h�A�Ӗ��j�z�Q�ƁB<BR>
     * <BR>
     * �@@�@@�I�u�W�F�N�g�������ł����ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^������Ɣ��肵�A<BR>
     * �@@�@@�����J�ݍ��ڑ���.validate�L���R�[�h�l()���R�[������B<BR>
     * Q&A:WEB3-ACCOUNTOPEN-A-UT-0023
     * �@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01738<BR>
     *          �ǉ�������: [���ږ�]<BR>
     * <BR>
     * �@@�@@[validate�L���R�[�h�l()�Ɏw�肷�����]<BR>
     * �@@�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l<BR>
     * <BR>
     * �@@���@@�L���R�[�h�l�`�F�b�N�ȊO�̏ꍇ�i�����J�ݍ��ڃ}�X�^.is�L���R�[�h�`�F�b�N() == false�j<BR>
     * �@@�@@�P�j�Ő������������J�ݍ��ڃ}�X�^.validate�L���l()���R�[������B<BR>
     * �@@�@@false���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01312<BR>
     *          �ǉ�������: [���ږ�]<BR>
     * <BR>
     * �@@�@@[validate�L���l()�Ɏw�肷�����]<BR>
     * �@@�@@���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l<BR>
     * <BR>
     * <BR>
     * (*1) �`�F�b�N�Ώۍ���<BR>
     * �@@DB���C�A�E�g �u�����J�݌����q�e�[�u��.xls�v���́y���ڃ`�F�b�N�����z���<BR>
     * �L�ڂ����鍀�ڂ��ΏہB<BR>
     * @@param l_strValidateType - �`�F�b�N�^�C�v<BR>
     * <BR>
     * 0�FDEFAULT�i�ڋq�\���j�@@<BR>
     * 1�F�Ǘ��Ґ\���@@<BR>
     * 2�F�\���X�V�@@<BR>
     * 3�F�`�[�쐬<BR>
     * 4�F�����J�݃A�b�v���[�h<BR>
     * @@param l_strAccountCodeAutoFlag - �`�F�b�N�^�C�v<BR>
     * <BR>
     * 1�F�@@�����̔Ԃ��s�� <BR>
     * 0 �܂��� null�F�@@�����̔Ԃ��s��Ȃ�<BR>
     * @@roseuid 4194616501F6
     */
    public void validateAccountOpenRegistInfo(String l_strValidateType, String l_strAccountCodeAutoFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAccountOpenRegistInfo(String,String)";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strBranchCode = this.getBranchCode();
        String l_strAccountDiv = this.getAccountDiv();
        String l_strAccountCode = this.getAccountCode();

        WEB3Crypt l_crypt = new WEB3Crypt();
        MethodSets l_methodSets = new MethodSets();
        Iterator l_checkItems = l_methodSets.methodMap.keySet().iterator();
        
        //�P�j�@@�����J�ݍ��ڃ}�X�^���� 
        String l_strCheckItem = null;
        ItemCheckMethod l_itemCheckMethod = null;
        WEB3AccOpenItemMaster l_accOpenItemMaster = null;
        Object l_checkItemValue = null;
        boolean initialPasswordCheckFlag = false;
        
        while (l_checkItems.hasNext())
        {
            //�`�F�b�N�Ώۍ���(*1)
            l_strCheckItem = (String)l_checkItems.next();
            
            //�������A�ȉ��̏����̏ꍇ�A�P�j�`�S�j�̃`�F�b�N�͍s��Ȃ��B
            // �`�F�b�N�Ώۍ���(*1) = �����R�[�h(account_code)
            // ���A�`�F�b�N�^�C�v = 3�F�`�[�쐬 
            // ���A�ڋq�R�[�h�����̔ԃt���O = 1�F�����̔Ԃ��s�� 
            // ��L�̏����𖞂����ꍇ�A�`�F�b�N�Ώۍ���(*1)�̒l�̖����̓`�F�b�N���s���B 
            // ���͂��������ꍇ�A�u�����̔Ԏw�莞�ɁA�ڋq�R�[�h�����͂���Ă��܂��B�v�̗�O���X���[���� 
            if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE.equals(l_strCheckItem)
                && WEB3ValidateTypeDef.VOUCHER_CREATED.equals(l_strValidateType)
                && WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_strAccountCodeAutoFlag))
            {
                if (!WEB3StringTypeUtility.isEmpty((String)expAccountOpenParams.getColumn(l_strCheckItem)))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02527,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�����̔Ԏw�莞�ɁA�ڋq�R�[�h�����͂���Ă��܂��B");  
                }
            }
            else
	        {
	            l_itemCheckMethod = (ItemCheckMethod) l_methodSets.methodMap.get(l_strCheckItem);
	            
	            log.debug("�`�F�b�N�Ώۍ���(*1) ===================== " + l_strCheckItem);
	            
	            boolean l_blnItemFoundFlag = false;
	            if (l_itemCheckMethod.customizing)
	            {
	                try
	                {
	                    //�R���X�g���N�^�̈���1] 
	                    log.debug("[�R���X�g���N�^�̈���1]");
	                    l_accOpenItemMaster = new WEB3AccOpenItemMaster(l_strInstitutionCode,
	                        l_strBranchCode, l_strAccountDiv, l_strValidateType, l_strCheckItem);
	                    l_blnItemFoundFlag = true;
	                }
	                catch (NotFoundException l_ex)
	                {
	                    l_blnItemFoundFlag = false;
	                }
	                
	                if (!l_blnItemFoundFlag)
	                {
	                    try
	                    {
	                        //[�R���X�g���N�^�̈���2] 
	                        log.debug("[�R���X�g���N�^�̈���2]");
	                        l_accOpenItemMaster = new WEB3AccOpenItemMaster(l_strInstitutionCode, "000",
	                            l_strAccountDiv, l_strValidateType, l_strCheckItem);
	                        l_blnItemFoundFlag = true;
	                    }
	                    catch (NotFoundException l_nfe)
	                    {
	                        if ("initial_password".equals(l_strCheckItem))
	                        {
	                            initialPasswordCheckFlag = true;
	                        }
	                        
	                        if (l_itemCheckMethod.itemCheckMode == null)
	                        {
	                            //�J�X�^�}�C�Y���Ȃ� && ���ڃ`�F�b�N���� == null
	                            log.debug("�J�X�^�}�C�Y���Ȃ� && ���ڃ`�F�b�N���� == null");                            
	                            continue;
	                        }
	                        l_blnItemFoundFlag = false;
	                    }
	                }
	            }
	            
	            if (!l_itemCheckMethod.customizing || !l_blnItemFoundFlag)
	            {
	                //�����J�ݍ��ڃ}�X�^.getDefault���ڃ}�X�^()�ɂăC���X�^���X�𐶐�����B
	                log.debug("�����@@�C�A�ŊY���f�[�^���Ȃ��ꍇ�́A�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ��Ɣ��f���A�����J�ݍ��ڃ}�X�^.getDefault���ڃ}�X�^()�ɂăC���X�^���X�𐶐�����B");
	                l_accOpenItemMaster = WEB3AccOpenItemMaster.getDefaultAccOpenItemMaster(
	                    l_itemCheckMethod.necessaryItemFlag, l_itemCheckMethod.size,
	                    l_itemCheckMethod.itemCheckMode);
	            }
	
	            //�G���[���X�|���X�ŕԋp���鍀�ږ��̎擾
	            AccOpenItemMasterRow l_itemMasterRow = (AccOpenItemMasterRow)l_accOpenItemMaster.getDataSourceObject();
	            String l_columnName = l_itemMasterRow.getItemName();
	            if (l_columnName == null || l_columnName.trim().length() == 0) 
	            {
	                l_columnName = l_itemCheckMethod.columnName;
	            }
	            
	            //���ڒl�F�@@�`�F�b�N�Ώۍ���(*1)�̒l
	            l_checkItemValue = expAccountOpenParams.getColumn(l_strCheckItem);
	            
	            if ("initial_password".equals(l_strCheckItem))
	            {
	                l_checkItemValue = l_crypt.decrypt((String) l_checkItemValue);
	            }
	            
	            //�Q�j�@@�K�{���ڃ`�F�b�N 
	            if (!l_accOpenItemMaster.validateNecessaryItem(l_checkItemValue))
	            {
	                log.debug("�P�j�Ő������������J�ݍ��ڃ}�X�^.validate�K�{����()���R�[������Bfalse���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
	                    this.getClass().getName() + STR_METHOD_NAME,
	                    l_columnName);
	            }
	            
	            //�R�j�@@�f�[�^�����O�X�`�F�b�N
	            if (!l_accOpenItemMaster.validateLength(l_checkItemValue))
	            {
	                log.debug("�P�j�Ő������������J�ݍ��ڃ}�X�^.validate�����O�X()���R�[������Bfalse���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01310,
	                    this.getClass().getName() + STR_METHOD_NAME,
	                    l_columnName);
	            }
	            
	            //�S�j�@@�L���l�`�F�b�N 
	            if (l_accOpenItemMaster.isValidCodeCheck())
	            {
	                log.debug("���@@�L���R�[�h�l�`�F�b�N�̏ꍇ�i�����J�ݍ��ڃ}�X�^.is�L���R�[�h�`�F�b�N() == true�j");
	                
	                WEB3AccOpenItemAttribute l_itemAttribute = null;
	                
	                try
	                {
	                    log.debug("[�R���X�g���N�^�̈���1] ");
	                    l_itemAttribute = new WEB3AccOpenItemAttribute(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strCheckItem);
	                }
	                catch (NotFoundException l_ex)
	                {
	                    try
	                    {
	                        log.debug("[�R���X�g���N�^�̈���2] ");
	                        l_itemAttribute = new WEB3AccOpenItemAttribute(l_strInstitutionCode, "000", l_strAccountDiv, l_strCheckItem);
	                    }
	                    catch (NotFoundException l_nfe)
	                    {
	                        if (!isValidCodeValue(l_strCheckItem, l_checkItemValue))
	                        {
	                            log.debug("�`�F�b�N�Ώۍ���(*1)�̒l�����݂���R�[�h�l�łȂ���΁A��O���X���[����B");
	                            log.exiting(STR_METHOD_NAME);
	                            throw new WEB3SystemLayerException(
	                                WEB3ErrorCatalog.BUSINESS_ERROR_01311,
	                                this.getClass().getName() + STR_METHOD_NAME,
	                                l_columnName);
	                        }
	                    }
	                }
	                //Q&A:WEB3-ACCOUNTOPEN-A-UT-0023:��O�̓X���[����܂��B
	                if (l_itemAttribute != null && !l_itemAttribute.validateValidCodeValue(l_checkItemValue == null ? null : l_checkItemValue.toString()))
	                {
	                    log.debug("�����J�ݍ��ڑ���.validate�L���R�[�h�l()���R�[������Bfalse���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3SystemLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01738,
	                        this.getClass().getName() + STR_METHOD_NAME,
	                        l_columnName);
	                }
	            }
	            else
	            {
	                if (!l_accOpenItemMaster.validateValidValue(l_checkItemValue))
	                {
	                    log.debug("�P�j�Ő������������J�ݍ��ڃ}�X�^.validate�L���l()���R�[������Bfalse���ԋp���ꂽ�ꍇ�́A�Ή������O���X���[����B");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3SystemLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01312,
	                        this.getClass().getName() + STR_METHOD_NAME,
	                        l_columnName);
	                }
	            }
	     
	        
	        if (initialPasswordCheckFlag)
	        {
	            //�����p�X���[�h�`�F�b�N
	            OpLoginSecurityService l_opLoginSec =
	                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
	        
	            long l_lngLoginId = 0;
	            try
	            {
	                l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException
	                if (l_lngLoginId != 0)
	                {
	                    WEB3GentradeAccountManager l_gentradeAccountManager = 
	                        (WEB3GentradeAccountManager)GtlUtils.getAccountManager(); 
	                
	                    Branch l_branch = null;
	                    try
	                    {
	                        l_branch = l_gentradeAccountManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode);//NotFoundException
	                    }
	                    catch(NotFoundException l_nfd)
	                    {  
	                        log.error(getClass().getName() + STR_METHOD_NAME, l_nfd);
	                        log.exiting(STR_METHOD_NAME);
	                        throw new WEB3SystemLayerException(
	                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
	                            this.getClass().getName() + STR_METHOD_NAME);  
	                    }
	                
	                    BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
	
	                    OpLoginAdminService l_opLoginAdminService =
	                                   (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
	
	                    Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_branchRow.getAccountTypeId());
	
	                    // checkCode(int, int, String, String)
	
	                    int l_intCodeMin = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH));
	                    int l_intCodeMax = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH));
	                    String l_strChkMode = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
	                    String l_strCodeString = (String) expAccountOpenParams.getColumn("initial_password");
	                    if (l_strCodeString != null) {
	                        int l_intChckcd = WEB3PasswordUtility.checkCode(l_intCodeMin, l_intCodeMax, l_strChkMode, l_crypt.decrypt(l_strCodeString));
	                     
	                        //�p�X���[�h�����񂪕s���ȏꍇ�icheckCode() != CHECK_NORMAL�j�A��O���X���[����
	                        switch (l_intChckcd)
	                        {
	                            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
	                                throw new WEB3BusinessLayerException(
	                                    WEB3ErrorCatalog.BUSINESS_ERROR_01915,
	                                    getClass().getName() + STR_METHOD_NAME);
	                        
	                            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
	                                throw new WEB3BusinessLayerException(
	                                    WEB3ErrorCatalog.BUSINESS_ERROR_01916,
	                                    getClass().getName() + STR_METHOD_NAME);
	                        
	                            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
	                                throw new WEB3BusinessLayerException(
	                                    WEB3ErrorCatalog.BUSINESS_ERROR_01914,
	                                    getClass().getName() + STR_METHOD_NAME);
	                        }
	                    }
	                }
	            }
	            catch (IllegalSessionStateException l_e)
	            {
	                //�Ǘ��҈ȊO
	            }
	        }
	        
	        //���N���� �����`�F�b�N
	        String l_strEraBorn = (String) expAccountOpenParams.getColumn("era_born");
	        String l_strBornDate = (String) expAccountOpenParams.getColumn("born_date");
	        
	        if (l_strEraBorn != null && l_strBornDate != null)
	        {
                Date l_datOnCalendar = WEB3GentradeEra.toDate(l_strEraBorn, l_strBornDate);
	            if (l_datOnCalendar == null)
	            {
	                log.debug("���N�����N�� = " + l_strEraBorn + ", ���N���� = " + l_strBornDate);
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01312,
	                    this.getClass().getName() + STR_METHOD_NAME,
	                    "���N����");
	            }
	        }
	    } 
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�d���ڋq)<BR>
     * ����ڋq���d�����ēo�^����Ă��Ȃ����`�F�b�N����B<BR> 
     * ���ɓo�^����Ă���ꍇ�A�ȉ��Q�̏������s���B <BR>
     * �@@�x������ԋp����B <BR>
     * �A���X�v���t�@@�����X�̒l>1�̏ꍇ�����J�ݐR���҂���񃊃X�g�֏d���ڋq����ۑ�����B<BR> 
     * (set�����J�ݐR���҂���񃊃X�g()) <BR>
     * <BR>
     * <BR>
     * �P)�@@�d���ڋq�`�F�b�N(�����J�݌����q�s.�����敪��"�l�A�J�E���g"�̏ꍇ)<BR> 
     * �@@�P�|�P�j�ȉ���5���ڂ��S��NULL�łȂ��ꍇ�̂݁A <BR>
     * �@@�ȍ~�̏��������{����B <BR>
     * �@@�@@�Ethis.get�،���ЃR�[�h()�̖߂�l<BR> 
     * �@@�@@�E�����J�݌����q�s.���N�����N�� <BR>
     * �@@�@@�E�����J�݌����q�s.���N���� <BR>
     * �@@�@@�E�����J�݌����q�s.�ڋq��(�J�i) <BR>
     * �@@�@@�E�����J�݌����q�s.�ڋq��(�J�i) <BR>
     * <BR>
     * �@@�P�|�Q�j�@@�d���ڋq�x���̔���i�����q�j<BR> 
     * �@@�@@�y�����J�݌����q�e�[�u���z���ȉ��̏����Ō�������B<BR> 
     * <BR>
     * �@@�@@[����]<BR> 
     * �@@�@@�@@�،���ЃR�[�h=this.get�،���ЃR�[�h() AND<BR> 
     * �@@�@@�@@���N�����N��=�����J�݌����q�s.���N�����N�� AND <BR>
     * �@@�@@�@@���N����=�����J�݌����q�s.���N���� AND <BR>
     * �@@�@@�@@�ڋq���i�J�i�j��=�����J�݌����q�s.�ڋq���i�J�i�j AND<BR>  
     * �@@�@@�@@�ڋq���i�J�i�j��=�����J�݌����q�s.�ڋq���i�J�i�j AND  <BR>
     * �@@�@@�@@�����R�[�h IS NOT NULL AND <BR>
     * �@@�@@�@@���ʃR�[�h !=�����J�݌����q�s.���ʃR�[�h <BR>
     *       �����o�^�� IS NULL <BR>
     * <BR>
     * �@@�@@�P�|�Q�|�P�j  �P�|�Q�j�ɉ����� �Y���s�����݂���ꍇ<BR> 
     * �@@�@@�@@�P�|�Q�|�P�|�P�j�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A<BR>  
     *       �@@�@@�@@�@@�@@�@@�@@�@@�@@�d���ڋq����ۑ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(set�����J�ݐR���҂���񃊃X�g()�j�B<BR> 
     *              <BR>
     * �@@�@@�@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�R����ʁF"2" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d���敪�F"1" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d�����X�R�[�h�F�����q�e�[�u��.���X�R�[�h<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�d���ڋq�R�[�h�F�����q�e�[�u��.�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     * �@@�P�|�R�j�@@�d���ڋq�x���̔���i�ڋq�}�X�^�j<BR> 
     * �@@�@@�P�|�R�|�P�j�@@�J�i�ϊ� <BR>
     * �@@�@@�@@this.�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B<BR> 
     * �@@�@@�@@this.�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B <BR>
     * �@@�@@�@@�iWEB3StringTypeUtility#toUpperWbyteKana()���g�p����j <BR>
     * <BR>
     * �@@�@@�P�|�R�|�Q�j�@@�ڋq�}�X�^�s�擾<BR> 
     * �@@�@@�@@�y�ڋq�}�X�^�z���ȉ��̏����Ō�������B<BR> 
     * <BR>
     * �@@�@@�@@[����]<BR> 
     * �@@�@@�@@�@@�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR> 
     * �@@�@@�@@�@@���N�����N�� = �����J�݌����q�s.���N�����N�� And <BR>
     * �@@�@@�@@�@@���N���� = �����J�݌����q�s.���N���� And <BR>
     * �@@�@@�@@�@@���O�i�c���P�j���ڋq���i�J�i�j�Ƃ��Ďg�p like �f%�����J�݌����q�s.�ڋq���i�J�i�j%�f And <BR>
     * �@@�@@�@@�@@���O�i�c���P�j���ڋq���i�J�i�j�Ƃ��Ďg�p like �f%�����J�݌����q�s.�ڋq���i�J�i�j%�f <BR>
     * <BR>
     * �@@�@@�@@�� �ڋq���i�J�i�j�C�ڋq���i�J�i�j�́A�P�|�R�|�P�j�ŕϊ����������� <BR>
     * <BR>
     * �@@�@@�P�|�R�|�R�j�@@�ڋq������<BR> 
     * �@@�@@�@@�Y���s�����݂����ꍇ�A�ȉ��̏������s���B <BR>
     * �@@�@@�@@�|�ڋq�}�X�^.���O�i�c���P�j��������A�S�pSpace�����C���pSpace�������폜����B<BR> 
     * �@@�@@�@@�|�����J�݌����q�s.�ڋq���i�J�i�j�C�ڋq���i�J�i�j��A��<BR>
     * �@@�@@�@@�@@�i�ڋq���i�J�i�j+�ڋq���i�J�i�j�j���A<BR> 
     * �@@�@@�@@�@@Space���폜�����ڋq�}�X�^.���O�i�c���P�j������Ɣ�r����B <BR>
     * �@@�@@ <BR>
     * �@@�@@�P�|�R�|�S�j�@@�P�|�R�|�R�j�ɉ����Ēl����v���Ă���ꍇ<BR> 
     * �@@�@@�@@�P�|�R�|�S�|�P�j�@@�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�d���ڋq����ۑ�����B(set�����J�ݐR���҂���񃊃X�g()�j�B<BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�@@�@@�R����ʁF"1" <BR>
     * �@@�@@�@@�@@�@@�@@�d���敪�F"1" <BR>
     * �@@�@@�@@�@@�@@�@@�d�����X�R�[�h�F�ڋq�}�X�^.���X�R�[�h<BR> 
     * �@@�@@�@@�@@�@@�@@�d���ڋq�R�[�h�F�ڋq�}�X�^.�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull<BR> 
     * �@@�@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     * <BR>
     * �@@�P�|�S�j�@@�d���ڋq�x���̔���i�S���X�ڋq�}�X�^�j<BR> 
     * �@@�@@�y�ڋq�}�X�^�i�S���X���j�z���ȉ��̏����Ō�������B<BR> 
     * <BR>
     * �@@�@@�P�|�S�|�P�j�@@�J�i�ϊ�<BR> 
     * �@@�@@�@@this.�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B<BR> 
     * �@@�@@�@@this.�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B <BR>
     * �@@�@@�@@�iWEB3StringTypeUtility#toUpperWbyteKana()���g�p����j <BR>
     * <BR>
     * �@@�@@�P�|�S�|�Q�j�@@���X�e�[�u����蕔�X�R�[�h�̃��X�g���擾�B<BR> 
     * �@@�@@�@@�ڋq�}�X�^�Ƃ̏d���������ׁA���X�e�[�u���ɑ��݂���w�肵��<BR>
     * �@@�@@�@@�،���Ђ̕��X�R�[�h�̃��X�g��<BR> 
     * �@@�@@�@@�����J�ݏd���`�F�b�N���[�e�B���e�B.get���X�R�[�h���X�g<BR>
     * �@@�@@�@@(this.get�،���ЃR�[�h())�����s���擾����B<BR> 
     * <BR>
     * �@@�@@�P�|�S�|�R�j�@@�ڋq�}�X�^�i�S���X���j�s�擾<BR>  
     * �@@�@@�@@�y�ڋq�}�X�^�i�S���X���j�z���ȉ��̏����Ō�������B<BR>  
     * <BR>
     * �@@�@@  �P�|�S�|�R�|�P�j �@@�P�|�S�|�Q�j�ł̖߂�l��null�ł͂Ȃ��ꍇ<BR> 
     * <BR>
     * �@@�@@�@@  [����]<BR>  
     * �@@�@@�@@�@@�@@�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>  
     * �@@�@@�@@�@@�@@���X�R�[�h  NOT IN ( �P�|�S�|�Q�j�Ŏ擾�������X�R�[�h���X�g ) And <BR>
     * �@@�@@�@@�@@�@@���N�����N�� = �����J�݌����q�s.���N�����N�� And  <BR>
     * �@@�@@�@@�@@�@@���N���� = �����J�݌����q�s.���N���� And  <BR>
     * �@@�@@�@@�@@�@@�ڋq���i�J�i�j like �f%�����J�݌����q�s.�ڋq���i�J�i�j%�f And<BR>  
     * �@@�@@�@@�@@�@@�ڋq���i�J�i�j like �f%�����J�݌����q�s.�ڋq���i�J�i�j%�f  <BR>
     * <BR>
     * �@@�@@�@@    �� �ڋq���i�J�i�j�C�ڋq���i�J�i�j�́A�P�|�S�|�P�j�ŕϊ�����������<BR>  
     * <BR>
     * �@@�@@  �P�|�S�|�R�|�Q�j �@@�P�|�S�|�Q�j�ł̖߂�l��null�̏ꍇ<BR> 
     * <BR>
     * �@@�@@�@@  [����]<BR>  
     * �@@�@@�@@�@@�@@�،���ЃR�[�h = this.get�،���ЃR�[�h() And<BR>  
     * �@@�@@�@@�@@�@@���N�����N�� = �����J�݌����q�s.���N�����N�� And  <BR>
     * �@@�@@�@@�@@�@@���N���� = �����J�݌����q�s.���N���� And  <BR>
     * �@@�@@�@@�@@�@@�ڋq���i�J�i�j like �f%�����J�݌����q�s.�ڋq���i�J�i�j%�f And<BR>  
     * �@@�@@�@@�@@�@@�ڋq���i�J�i�j like �f%�����J�݌����q�s.�ڋq���i�J�i�j%�f  <BR>
     * <BR>
     * �@@�@@�@@    �� �ڋq���i�J�i�j�C�ڋq���i�J�i�j�́A�P�|�S�|�P�j�ŕϊ�����������<BR>  
     * <BR>
     * �@@�@@�P�|�S�|�S�j�@@�ڋq������<BR>  
     * �@@�@@�@@�Y���s�����݂����ꍇ�A�ȉ��̏������s���B<BR>  
     * �@@�@@�@@�|�ڋq�}�X�^�i�S���X�j.�ڋq���i�J�i�j��������A�S�pSpace�����C<BR>
     * �@@�@@�@@���pSpace�������폜����B<BR>  
     * �@@�@@�@@�|�����J�݌����q�s.�ڋq���i�J�i�j�C�ڋq���i�J�i�j��A��<BR>
     * �@@�@@�@@�i�ڋq���i�J�i�j+�ڋq���i�J�i�j�j���A<BR>  
     * �@@�@@�@@Space���폜�����ڋq�}�X�^�i�S���X�j.�ڋq���i�J�i�j������Ɣ�r����B  <BR>
     * <BR>
     * �@@�@@�P�|�S�|�T�j�@@�P�|�S�|�S�j �ɉ����Ēl����v���Ă���ꍇ<BR> 
     * �@@�@@�@@�P�|�S�|�T�|�P�j�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�d���ڋq����ۑ�����B(set�����J�ݐR���҂���񃊃X�g()�j�B<BR> 
     *              <BR>
     * �@@�@@�@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�R����ʁF"1" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d���敪�F"1" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d�����X�R�[�h�F�ڋq�}�X�^�i�S���X���j.���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d���ڋq�R�[�h�F�ڋq�}�X�^�i�S���X���j.�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     *�@@�@@�@@�@@�@@�@@ �@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     * <BR>
     * �Q)�@@�d���ڋq�`�F�b�N(�����J�݌����q�s.�����敪��"�@@�l�A�J�E���g"�̏ꍇ)<BR> 
     * <BR>
     * �@@(�@@�l�̏ꍇ�A�ڋq��(�J�i)�A�ڋq��(�J�i)�ɂ́A<BR>
     * �@@����ɕs�v�ȕ������܂܂��ꍇ������̂ŕҏW���s��)<BR> 
     * <BR>
     * �@@�Q�|�P�j�@@�ڋq��(�J�i)�A�ڋq��(�J�i)���画��ɕs�v�ȕ������폜����B<BR> 
     * �@@�@@�@@�|this.�����J�݌����q�s.�ڋq��(�J�i)�A�ڋq��(�J�i)���擾����B <BR>
     * �@@�@@�@@�|�ڋq��(�J�i)�E�ڋq��(�J�i)����A�ݗ��`�ԋ敪(��)�A�S�pSpace�����A<BR>
     * �@@�@@�@@���pSpace�������폜����B<BR> 
     * �@@�@@�@@�@@�iWEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[]���g�p����j<BR> 
     * <BR>
     * �@@�Q�|�Q�j�@@�d���ڋq�x���̔���i�����q�j <BR>
     * �@@�@@�@@�y�����J�݌����q�e�[�u���z���ȉ��̏����Ō�������B<BR> 
     * <BR>
     * �@@ �Q�|�Q�|�P�j�@@�|�ڋq��(�J�i)�A�ڋq��(�J�i)�́A�ݗ��`�ԋ敪(��)�A�S�p�E���pSpace���� <BR>
     *                 �s�v������replace()�֐��ŁA�폜���� <BR>
     *                 �|�ڋq�����A��\�Ґ����A����ӔC�Ґ����ɂ��ẮA�_���a( or )���g�p <BR>
     * <BR>
     * �@@�@@�@@[����] <BR>
     * �@@�@@�@@�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR>
     * �@@�@@�@@(replace(�ڋq��(�J�i),�w�s�v�����x) || replace(�ڋq��(�J�i),�w�s�v�����x)�@@�E�E�E(*1)<BR> 
     * �@@�@@�@@�@@�@@= �����J�݌����q�s.�ڋq���i�J�i�j + �����J�݌����q�s.�ڋq���i�J�i�j Or�@@�E�E�E(*2)<BR> 
     * �@@�@@�@@�e�Њg�����ځi�e�L�X�g�V�j || �e�Њg�����ځi�e�L�X�g�W�j <BR>
     * �@@�@@�@@�@@�@@= �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�V�j <BR>
     * �@@�@@�@@�@@�@@�@@+ �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�W�j Or<BR> 
     * �@@�@@�@@�e�Њg�����ځi�e�L�X�g�X�j || �e�Њg�����ځi�e�L�X�g�P�O�j<BR> 
     * �@@�@@�@@�@@�@@= �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�X�j <BR>
     * �@@�@@�@@�@@�@@�@@+ �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�P�O�j�j And <BR>
     *�@@�@@�@@ ���ʃR�[�h != �����J�݌����q�s.���ʃR�[�h And <BR>
     *�@@�@@�@@ �����R�[�h IS NOT NULL <BR>
     * <BR>
     * �@@�@@�@@(*1WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[]��<BR> 
     * �@@�@@�@@�@@�v�f�������[�v�������s���Areplace�֐��ɂ��A����s�v�����̍폜���s���B<BR> 
     * �@@�@@�@@(*2)�ڋq��(�J�i)�C�ڋq��(�J�i)�́A�Q�|�P�j�ŕҏW���������� <BR>
     * <BR>
     * �@@�@@(��)����ɕs�v�ȉ�Аݗ��`�ԋ敪�����͈ȉ���6�i�S�đS�p�啶���j <BR>
     * �@@�@@�@@�@@"�J�j" <BR>
     * �@@�@@�@@�@@"�i�J" <BR>
     * �@@�@@�@@�@@"���j" <BR>
     * �@@�@@�@@�@@"�i��" <BR>
     * �@@�@@�@@�@@"�V�j" <BR>
     * �@@�@@�@@�@@"�i�V" <BR>
     * <BR>
     * �@@ �Q�|�Q�|�Q�j�@@�Q�|�Q�|�P�j�ɉ����ĊY���s�����݂���ꍇ <BR>
     * �@@   �Q�|�Q�|�Q�|�P�j�@@�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�d���ڋq����ۑ�����B(set�����J�ݐR���҂���񃊃X�g()�j�B<BR> 
     *              <BR>
     * �@@�@@�@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�R����ʁF"2" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d���敪�F"1" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d�����X�R�[�h�F�����q�e�[�u��.���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�d���ڋq�R�[�h�F�����q�e�[�u��.�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     * <BR>
     * �@@�Q�|�R�j�@@�d���ڋq�x���̔���i�������}�X�^�j  <BR>
     * �@@�@@�Q�|�R�|�P�j�@@�Q�|�P�j�ŕҏW�����ڋq��(�J�i)�A�ڋq��(�J�i)��啶���ɕϊ�����B<BR> 
     * �@@�@@�@@�|�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B  <BR>
     * �@@�@@�@@�|�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B  <BR>
     * �@@�@@�@@�iWEB3StringTypeUtility#toUpperWbyteKana()���g�p����j <BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j�@@�������}�X�^�s�擾 <BR>
     * �@@�@@�@@�y�������}�X�^�z���ȉ��̏����Ō�������B <BR>
     * <BR>
     * �@@�@@�@@�@@�|�@@�l��(�J�i)�́A�ݗ��`�ԋ敪(��)�A<BR>
     * �@@�@@�@@�@@�S�p�E���pSpace���̕s�v������replace()�֐��ŁA�폜����<BR> 
     * �@@�@@�@@�@@�|�@@�l���A��\�Ґ����A����ӔC�Ґ����ɂ��ẮA�_���a( or )���g�p <BR>
     * <BR>
     * �@@�@@�@@[����]  <BR>
     * �@@�@@�@@�،���ЃR�[�h = this.get�،���ЃR�[�h() And <BR> 
     * �@@�@@�@@(replace(�@@�l��(�J�i),�w�s�v�����x)�@@�E�E�E(*3) <BR>
     * �@@�@@�@@�@@�@@= �����J�݌����q�s.�ڋq���i�J�i�j + �����J�݌����q�s.�ڋq���i�J�i�j<BR>
     * �@@�@@�@@�@@�@@�@@ Or�@@�E�E�E(*4)<BR> 
     * �@@�@@�@@��\�Җ��i�J�i�j�� || ��\�Җ��i�J�i�j�� <BR>
     * �@@�@@�@@�@@�@@= �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�V�j<BR>
     * �@@�@@�@@�@@�@@�@@ + �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�W�j Or<BR>  
     * �@@�@@�@@����ӔC�Җ��i�J�i�j�� || ����ӔC�Җ��i�J�i�j�� <BR>
     * �@@�@@�@@�@@�@@= �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�X�j<BR>
     * �@@�@@�@@�@@�@@�@@ + �����J�݌����q�s.�e�Њg�����ځi�e�L�X�g�P�O�j�j<BR> 
     * <BR>
     * �@@�@@�@@(*3WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[]�� <BR>
     * �@@�@@�@@�@@�v�f�������[�v�������s���Areplace�֐��ɂ��A����s�v�����̍폜���s���B<BR> 
     * �@@�@@�@@(*4)�ڋq��(�J�i)�C�ڋq��(�J�i)�́A�Q�|�R�|�P�j�ŕϊ�����������  <BR>
     * <BR>
     * �@@  �Q�|�R�|�R�j�@@�Q�|�R�|�Q�j�ɉ����ĊY���s�����݂���ꍇ <BR>
     * �@@    �Q�|�R�|�R�|�P�j�@@�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�d���ڋq����ۑ�����B(set�����J�ݐR���҂���񃊃X�g()�j�B<BR> 
     *              <BR>
     * �@@�@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�@@�@@�R����ʁF"1" <BR>
     * �@@�@@�@@�@@�@@�@@�d���敪�F"1" <BR>
     * �@@�@@�@@�@@�@@�@@�d�����X�R�[�h�F�������}�X�^.���X�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�d���ڋq�R�[�h�F�������}�X�^.�ڋq�R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     * <BR>
     * �R�j  �߂�l <BR>
     * <BR>
     * �@@�R�|�P�j�@@��L�A�P�|�Q�|�P�j�A�P�|�R�|�S�j�A�P�|�S�|�T�j�A�Q�|�Q�|�Q�j�A�Q�|�R�|�R�j�� <BR>
     * �@@�@@�@@�@@�@@�@@�����ꂩ�ɉ����ĊY���s�����݂����ꍇ�A�x�����b�Z�[�W��ԋp����B<BR> 
     * �@@�@@�@@�@@�@@�@@�uWARNING_60001�F�@@�ڋq�d���o�^�̉\������B�v  <BR>
     * �@@�@@�@@�@@�@@�@@�@@tag:WARNING_60001<BR>
     * <BR>
     * �@@�R�|�Q�j�@@��L�A�P�|�Q�|�P�j�A�P�|�R�|�S�j�A�P�|�S�|�T�j�A�Q�|�Q�|�Q�j�A�Q�|�R�|�R�j��<BR> 
     * �@@�@@�@@�@@�@@�@@�S�Ăɉ����ĊY���s�����݂��Ȃ��ꍇ�Anull��ԋp����B<BR>
     * @@param l_intBranchPreference - (���X�v���t�@@�����X)<BR>
     * ���X�v���t�@@�����X�l<BR> 
     * <BR>
     * [�ݒ�l] <BR>
     * 0�F�`�F�b�N�s�v-�A���[�g�\����-�R���҂�UPDATE�� <BR>
     * 1�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�� <BR>
     * 2�F�`�F�b�N���s-�A���[�g�\����-�R���҂�UPDATE�L <BR>
     * 3�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�L <BR>    
     * @@return String
     * @@roseuid 419453DB0169
     */
    public String validateDuplicateAccount(int l_intBranchPreference) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateAccount()";
        log.entering(STR_METHOD_NAME);
      
        boolean l_blnDupulicateFlag = false;
        
        try
        {
            String l_strInstitutionCode = this.getInstitutionCode();
       
            String l_strEraBorn = this.expAccountOpenParams.getEraBorn();
            String l_strBornDate = this.expAccountOpenParams.getBornDate();
            String l_strFamilyName = this.expAccountOpenParams.getFamilyNameAlt1();
            String l_strGivenName = this.expAccountOpenParams.getGivenNameAlt1();            
            String l_strAccountDiv = this.expAccountOpenParams.getAccountDiv();                     
            
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_strAccountDiv))
            { 
                //�P)�@@�d���ڋq�`�F�b�N(�����J�݌����q�s.�����敪��"�l�A�J�E���g"�̏ꍇ)
                //�P�|�P�j�ȉ���5���ڂ��S��NULL�łȂ��ꍇ�̂݁A�ȍ~�̏��������{����B
                //�Ethis.get�،���ЃR�[�h()�̖߂�l
                //�E�����J�݌����q�s.���N�����N��
                //�E�����J�݌����q�s.���N����
                //�E�����J�݌����q�s.�ڋq��(�J�i)
                //�E�����J�݌����q�s.�ڋq��(�J�i) 
                
                if (l_strInstitutionCode == null || l_strEraBorn == null || 
                    l_strBornDate == null || l_strFamilyName == null || 
                    l_strGivenName == null) 
                {
                    return null;
                }
                           
                //�P�|�Q�j�d���ڋq�x���̔���i�����q�j 
                StringBuffer l_sbWhere = new StringBuffer();
                List l_lisDataBind = new ArrayList();

                l_sbWhere.append("institution_code = ?");
                l_lisDataBind.add(l_strInstitutionCode);            
                        
                l_sbWhere.append(" and era_born = ?");
                l_lisDataBind.add(l_strEraBorn);
                        
                l_sbWhere.append(" and born_date = ?");
                l_lisDataBind.add(l_strBornDate);
            
                l_sbWhere.append(" and family_name_alt1 = ? and given_name_alt1 = ?");
                l_lisDataBind.add(l_strFamilyName);
                l_lisDataBind.add(l_strGivenName);
            
                l_sbWhere.append(" and account_code is not null");
                
                l_sbWhere.append(" and acc_open_request_number <> ?");
                l_lisDataBind.add(this.getRequestNumber());

				//�d�l�ύX20060623SCSInomata-->
				l_sbWhere.append(" and account_open_date is null");
				//<--�d�l�ύX20060623SCSInomata
            
                Object[] l_objExpAccOpen = new Object[l_lisDataBind.size()];
                l_lisDataBind.toArray(l_objExpAccOpen);
            
                List l_lisExpAccOpenRow = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenRow.TYPE, l_sbWhere.toString(), l_objExpAccOpen);

                //�P�|�Q�|�P�j  �P�|�Q�j�ɉ����� �Y���s�����݂���ꍇ 
                if (l_lisExpAccOpenRow != null && l_lisExpAccOpenRow.size() > 0)
                {
                    l_blnDupulicateFlag = true;
                    
                    //�P�|�Q�|�P�|�P�j  �i�����j���X�v���t�@@�����X > 1�̏ꍇ�A
                    //�d���ڋq����ۑ�����B 
                    if (l_intBranchPreference > 1)
                    {
                        for (int i = 0; i < l_lisExpAccOpenRow.size(); i++)
                        {
							ExpAccountOpenRow l_row = (ExpAccountOpenRow)l_lisExpAccOpenRow.get(i);
							this.setAccOpenJudgeWaitingInfoList(
								WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP, 
								WEB3DuplicationDivDef.NAME_BORN_DATE,
								l_row.getBranchCode(),
								l_row.getAccountCode(),
								null,
								null,
								null,
								null);
                        }
                    }
                }

                //�P�|�R�j�@@�d���ڋq�x���̔���i�ڋq�}�X�^�j 
                //�P�|�R�|�P�j�@@�J�i�ϊ�
                String l_strFamilyNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strFamilyName);

                String l_strGivenNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strGivenName);

                //�P�|�R�|�Q�j�@@�ڋq�}�X�^�s�擾
                StringBuffer l_sbMainAccountWhereAgain = new StringBuffer();
                List l_lisMainAccountAgain = new ArrayList();
            
                l_sbMainAccountWhereAgain.append("institution_code = ?");
                l_lisMainAccountAgain.add(l_strInstitutionCode);
            
                l_sbMainAccountWhereAgain.append(" and era_born = ?");
                l_lisMainAccountAgain.add(l_strEraBorn);
                    
                l_sbMainAccountWhereAgain.append(" and born_date = ?");
                l_lisMainAccountAgain.add(l_strBornDate);
                
                l_sbMainAccountWhereAgain.append(" and family_name_alt1 like ? and family_name_alt1 like ?");
                l_lisMainAccountAgain.add("%" + l_strFamilyNameAlt + "%");
                l_lisMainAccountAgain.add("%" + l_strGivenNameAlt + "%");
            
                Object[] l_objMainAccountAgain = new Object[l_lisMainAccountAgain.size()];
                l_lisMainAccountAgain.toArray(l_objMainAccountAgain);
            
                List l_lisMainAccountRowAgain = Processors.getDefaultProcessor().doFindAllQuery(
                    MainAccountRow.TYPE, l_sbMainAccountWhereAgain.toString(), l_objMainAccountAgain);
            
                //�P�|�R�|�R�j�@@�ڋq������ 
                if (l_lisMainAccountRowAgain != null && l_lisMainAccountRowAgain.size() > 0)
                {
                    int l_intLength = l_lisMainAccountRowAgain.size();
                    String l_strFamilyNameAlt1 = null;
                    StringBuffer l_sbFamilyNameAlt1 = new StringBuffer();
                    for (int i = 0; i < l_intLength; i++)
                    {
                        MainAccountRow l_row = (MainAccountRow)l_lisMainAccountRowAgain.get(i);
                        l_strFamilyNameAlt1 = l_row.getFamilyNameAlt1();
                        int l_intNameLength = l_strFamilyNameAlt1.length();

                        for (int j = 0; j < l_intNameLength; j++)
                        {
                            if (Character.isWhitespace(l_strFamilyNameAlt1.charAt(j)))
                            {
                                continue;
                            }
                            l_sbFamilyNameAlt1.append(l_strFamilyNameAlt1.charAt(j));
                        }

                        //�P�|�R�|�S�j�@@�P�|�R�|�R�j�ɉ����Ēl����v���Ă���ꍇ
                        if (l_sbFamilyNameAlt1.toString().equals(l_strFamilyNameAlt + l_strGivenNameAlt))
                        {
                            l_blnDupulicateFlag = true;
                            
                            //�P�|�R�|�S�|�P�j�@@�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A
                            //�d���ڋq����ۑ�����B
                            if (l_intBranchPreference > 1)
                            {
                                this.setAccOpenJudgeWaitingInfoList(
                                    WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
                                    WEB3DuplicationDivDef.NAME_BORN_DATE,
                                    l_row.getBranchCode(),
                                    l_row.getAccountCode(),
                                    null,
                                    null,
                                    null,
                                    null);
                            }
                        }
                    }
                }
                
                //�P�|�S�j�@@�d���ڋq�x���̔���i�S���X�ڋq�}�X�^�j
                //�P�|�S�|�P�j�@@�J�i�ϊ�
                
                //�P�|�S�|�Q�j�@@���X�e�[�u����蕔�X�R�[�h�̃��X�g���擾�B 
                String l_strBranchCodeList = 
                    WEB3AccOpenDuplicationCheckUtility.getBranchCodeList(this.getInstitutionCode());
                
                //�@@�@@�P�|�S�|�R�j�@@�ڋq�}�X�^�i�S���X���j�s�擾  
                //�@@�@@�@@�y�ڋq�}�X�^�i�S���X���j�z���ȉ��̏����Ō�������B  
                StringBuffer l_sbMainAccountAllQueryString = new StringBuffer();
                List l_lisMainAccountAllContainer = new ArrayList(); 
                
                l_sbMainAccountAllQueryString.append("comp_code = ?");
                l_lisMainAccountAllContainer.add(l_strInstitutionCode);
                
                if (l_strBranchCodeList != null) 
                {
                    String[] l_strBranchCodes = l_strBranchCodeList.split(",");
                    StringBuffer l_sbBranchCode = new StringBuffer();
                    l_sbBranchCode.append(" and br_code not in (");
                    
                    for (int i= 0; i < l_strBranchCodes.length; i++) 
                    {
                        l_sbBranchCode.append("?,");
                        l_lisMainAccountAllContainer.add(l_strBranchCodes[i]);
                    }
                    
                    l_sbMainAccountAllQueryString.append(l_sbBranchCode.substring(0,l_sbBranchCode.length() - 1));
                    l_sbMainAccountAllQueryString.append(")");
                    
                }
                
                l_sbMainAccountAllQueryString.append(" and era_born = ?");
                l_lisMainAccountAllContainer.add(l_strEraBorn);
                
                l_sbMainAccountAllQueryString.append(" and born_date = ?");
				//�d�l�ύX20060623SCSInomata-->
				if (l_strBornDate.length() == 6 )
				{ 
					l_lisMainAccountAllContainer.add("00" + l_strBornDate);
				} 
				else
				{
					l_lisMainAccountAllContainer.add(l_strBornDate);
				}
				//<--�d�l�ύX20060623SCSInomata                
                l_sbMainAccountAllQueryString.append(" and family_name_alt1 like ? and family_name_alt1 like ?");
                l_lisMainAccountAllContainer.add("%" + l_strFamilyNameAlt + "%");
                l_lisMainAccountAllContainer.add("%" + l_strGivenNameAlt + "%");
                
                Object[] l_objMainAccountAll = new Object[l_lisMainAccountAllContainer.size()];
                l_lisMainAccountAllContainer.toArray(l_objMainAccountAll);
                
                List l_lisMainAccountAllRow = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        MainAccountAllRow.TYPE, 
                        l_sbMainAccountAllQueryString.toString(), 
                        l_objMainAccountAll);
                
                //�@@�@@�P�|�S�|�S�j�@@�ڋq������  
                //�@@�@@�@@�Y���s�����݂����ꍇ�A�ȉ��̏������s���B  
                //�@@�@@�@@�|�ڋq�}�X�^�i�S���X�j.�ڋq���i�J�i�j��������A�S�pSpace�����C���pSpace�������폜����B  
                //�@@�@@�@@�|�����J�݌����q�s.�ڋq���i�J�i�j�C�ڋq���i�J�i�j��A���i�ڋq���i�J�i�j+�ڋq���i�J�i�j�j���A  
                //�@@�@@�@@�@@Space���폜�����ڋq�}�X�^�i�S���X�j.�ڋq���i�J�i�j������Ɣ�r����B    
                if (l_lisMainAccountAllRow != null && l_lisMainAccountAllRow.size() != 0) 
                {
                    int l_intLength = l_lisMainAccountAllRow.size();
                    String l_strFamilyNameAlt1 = null;
                    StringBuffer l_sbFamilyNameAlt1 = new StringBuffer();
                    for (int i = 0; i < l_intLength; i++)
                    {
                        MainAccountAllRow l_row = (MainAccountAllRow)l_lisMainAccountAllRow.get(i);
                        l_strFamilyNameAlt1 = l_row.getFamilyNameAlt1();
                        int l_intNameLength = l_strFamilyNameAlt1.length();

                        for (int j = 0; j < l_intNameLength; j++)
                        {
                            if (Character.isWhitespace(l_strFamilyNameAlt1.charAt(j)))
                            {
                                continue;
                            }
                            l_sbFamilyNameAlt1.append(l_strFamilyNameAlt1.charAt(j));
                        }
             
                        if (l_sbFamilyNameAlt1.toString().equals(l_strFamilyNameAlt + l_strGivenNameAlt))
                        {
                            l_blnDupulicateFlag = true;
                            
                            //�@@�@@�P�|�S�|�T�j�@@�P�|�S�|�S�|�R�j �ɉ����Ēl����v���Ă���ꍇ 
                            //      �P�|�S�|�T�|�P�j�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A 
                            if (l_intBranchPreference > 1)
                            {
                                this.setAccOpenJudgeWaitingInfoList(
                                    WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
                                    WEB3DuplicationDivDef.NAME_BORN_DATE,
                                    l_row.getBrCode(),
                                    l_row.getCustCode(),
                                    null,
                                    null,
                                    null,
                                    null);
                            }
                        }
                    }
                }        
            }  
            
            //�Q)�@@�d���ڋq�`�F�b�N(�����J�݌����q�s.�����敪��"�@@�l�A�J�E���g"�̏ꍇ)
            else if(WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_strAccountDiv))
            {           

                StringBuffer l_sbWhereExpAccOpen = new StringBuffer();
                List l_lisDataBindExpAccOpen = new ArrayList();

                //�Q�|�P�j�@@�ڋq��(�J�i)�A�ڋq��(�J�i)���画��ɕs�v�ȕ������폜����B
                //�|this.�����J�݌����q�s.�ڋq��(�J�i)�A�ڋq��(�J�i)���擾����B
                String l_strExtItemText7 = changeStr(this.expAccountOpenParams.getExtItemText7());
                String l_strExtItemText8 = changeStr(this.expAccountOpenParams.getExtItemText8());
                String l_strExtItemText9 = changeStr(this.expAccountOpenParams.getExtItemText9());
                String l_strExtItemText10 = changeStr(this.expAccountOpenParams.getExtItemText10());
                
                int l_intComformDivLength = WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST.length;

                //�����q�s.�ڋq��(�J�i)����s�v�������S���p�X�y�[�X���폜����
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_strFamilyName = l_strFamilyName.replaceAll(WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i], "");
                    l_strGivenName = l_strGivenName.replaceAll(WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i], "");
                }

                //�Q�|�Q�j�@@�d���ڋq�x���̔���i�����q�j 
                l_sbWhereExpAccOpen.append("institution_code = ?");
                l_lisDataBindExpAccOpen.add(l_strInstitutionCode);        

                //�@@�l���� or ��\�Ґ��� or ����ӔC�Ґ����̏d����������start
                l_sbWhereExpAccOpen.append(" and (");

                //�����qTBL.�ڋq��(�J�i)
                l_sbWhereExpAccOpen.append("(");
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append("replace(");
                }

                l_sbWhereExpAccOpen.append("family_name_alt1");

                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append(",'" + WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i] + "')");
                }
                l_sbWhereExpAccOpen.append(")");

                //�����qTBL.�ڋq��(�J�i)
                l_sbWhereExpAccOpen.append(" || (");
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append("replace(");
                }

                l_sbWhereExpAccOpen.append("given_name_alt1");

                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append(",'" + WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i] + "')");
                }
                l_sbWhereExpAccOpen.append(") = ?");
                l_lisDataBindExpAccOpen.add(l_strFamilyName + l_strGivenName);

                l_sbWhereExpAccOpen.append(" or (ext_item_text7 || ext_item_text8) = ?");
                l_lisDataBindExpAccOpen.add(l_strExtItemText7 + l_strExtItemText8);

                l_sbWhereExpAccOpen.append(" or (ext_item_text9 || ext_item_text10) = ?");
                l_lisDataBindExpAccOpen.add(l_strExtItemText9 + l_strExtItemText10);

                //�@@�l���� or ��\�Ґ��� or ����ӔC�Ґ����̏d����������end
                l_sbWhereExpAccOpen.append(")");

                l_sbWhereExpAccOpen.append(" and acc_open_request_number <> ?");            
                l_lisDataBindExpAccOpen.add(this.getRequestNumber());
                                
                l_sbWhereExpAccOpen.append(" and account_code is not null");
               
                Object[] l_objExpAccOpen = new Object[l_lisDataBindExpAccOpen.size()];
                l_lisDataBindExpAccOpen.toArray(l_objExpAccOpen);

                List l_lisExpAccOpenRow = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenRow.TYPE, l_sbWhereExpAccOpen.toString(), l_objExpAccOpen);
                    
                // �Q�|�Q�|�Q�j�@@�Q�|�Q�|�P�j�ɉ����ĊY���s�����݂���ꍇ 
                if (l_lisExpAccOpenRow != null && l_lisExpAccOpenRow.size() > 0)
                {
                    l_blnDupulicateFlag = true;
                    
                    //  �Q�|�Q�|�Q�|�P�j�@@�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A 
                    if (l_intBranchPreference > 1)
                    {
                        for (int i = 0; i < l_lisExpAccOpenRow.size(); i++) 
                        {
                            ExpAccountOpenRow l_row = 
                                (ExpAccountOpenRow)l_lisExpAccOpenRow.get(i);
                            this.setAccOpenJudgeWaitingInfoList(
                                WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP, 
                                WEB3DuplicationDivDef.NAME_BORN_DATE,
                                l_row.getBranchCode(),
                                l_row.getAccountCode(),
                                null,
                                null,
                                null,
                                null);
                        }
                    }
                }

                //�Q�|�R�j�@@�d���ڋq�x���̔���i�������}�X�^�j 
                //�Q�|�R�|�P�j�@@�Q�|�P�j�ŕҏW�����ڋq��(�J�i)�A�ڋq��(�J�i)��啶���ɕϊ�����B
                String l_strFamilyNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strFamilyName);
                String l_strGivenNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strGivenName);

                //�Q�|�R�|�Q�j�@@�������}�X�^�s�擾
                StringBuffer l_sbWhereAccInfoMst = new StringBuffer();
                List l_lisDataBindAccInfoMst = new ArrayList();

                l_sbWhereAccInfoMst.append("institution_code = ?");
                l_lisDataBindAccInfoMst.add(l_strInstitutionCode);

                //�@@�l���� or ��\�Ґ��� or ����ӔC�Ґ����̏d����������start
                l_sbWhereAccInfoMst.append(" and (");

                //�������}�X�^TBL.�ڋq��(�@@�l��)�J�i
                l_sbWhereAccInfoMst.append("(");
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereAccInfoMst.append("replace(");
                }

                l_sbWhereAccInfoMst.append("family_name_alt1");

                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereAccInfoMst.append(",'" + WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i] + "')");
                }
                l_sbWhereAccInfoMst.append(") = ?");
                l_lisDataBindAccInfoMst.add(l_strFamilyNameAlt + l_strGivenNameAlt);

                l_sbWhereAccInfoMst.append(" or (represent_family_name_alt1 || represent_given_name_alt1) = ?");
                l_lisDataBindAccInfoMst.add(l_strExtItemText7 + l_strExtItemText8);

                l_sbWhereAccInfoMst.append(" or (director_family_name_alt1 || director_given_name_alt1) = ?");
                l_lisDataBindAccInfoMst.add(l_strExtItemText9 + l_strExtItemText10);

                //�@@�l���� or ��\�Ґ��� or ����ӔC�Ґ����̏d����������end
                l_sbWhereAccInfoMst.append(")");

                Object[] l_objAccountInfoMstAgain = new Object[l_lisDataBindAccInfoMst.size()];
                l_lisDataBindAccInfoMst.toArray(l_objAccountInfoMstAgain);
            
                List l_lisAccountInfoMstRow = Processors.getDefaultProcessor().doFindAllQuery(
                    AccountInfoMstRow.TYPE, l_sbWhereAccInfoMst.toString(), l_objAccountInfoMstAgain);                                         

                // �Q�|�R�|�R�j�@@�Q�|�R�|�Q�j�ɉ����ĊY���s�����݂���ꍇ 
                if (l_lisAccountInfoMstRow != null && l_lisAccountInfoMstRow.size() > 0)
                {
                    l_blnDupulicateFlag = true;
                    
                    //�Q�|�R�|�R�|�P�j�@@�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A
                    if (l_intBranchPreference > 1)
                    {
                        for (int i = 0; i < l_lisAccountInfoMstRow.size(); i++) 
                        {
                            AccountInfoMstRow l_row = 
                                (AccountInfoMstRow) l_lisAccountInfoMstRow.get(i);
                            this.setAccOpenJudgeWaitingInfoList(
                                WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
                                WEB3DuplicationDivDef.NAME_BORN_DATE,
                                l_row.getBranchCode(),
                                l_row.getAccountCode(),
                                null,
                                null,
                                null,
                                null);
                        }
                    }         
                }
            }
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
        
        //�R�j  �߂�l 
        //�@@�R�|�P�j�@@��L�A�P�|�Q�|�P�j�A�P�|�R�|�S�j�A�P�|�S�|�T�j�A�Q�|�Q�|�Q�j�A�Q�|�R�|�R�j�� 
        //             �����ꂩ�ɉ����ĊY���s�����݂����ꍇ�A�x�����b�Z�[�W��ԋp����B 
        //�@@          �uWARNING_60001�F�@@�ڋq�d���o�^�̉\������B�v 
        if (l_blnDupulicateFlag) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60001.getErrorCode();
        }
        
        //�R�|�Q�j��L�A�S�Ăɉ����ĊY���s�����݂��Ȃ��ꍇ�Anull��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (validate�x�q)<BR>
     * �\���ڋq���x�q�ɍڂ��Ă��Ȃ����`�F�b�N����B<BR>
     * ���ɓo�^����Ă���ꍇ�A�ȉ��Q�̏������s���B <BR>
     * �@@�x������ԋp����B <BR>
     * �A���X�v���t�@@�����X�̒l>1�̏ꍇ�����J�ݐR���҂���񃊃X�g�֏d���ڋq����ۑ�����B<BR> 
     * (set�����J�ݐR���҂���񃊃X�g()) <BR>
     * <BR>
     * �P)�ȉ���5���ڂ��S��NULL�łȂ��ꍇ�̂݁A<BR> 
�@@   *    �ȍ~�̏��������{����B<BR> 
�@@�@@ * �E�����J�݌����q�s.�،���ЃR�[�h <BR>
�@@�@@ * �E�����J�݌����q�s.���N�����N�� <BR>
�@@�@@ * �E�����J�݌����q�s.���N���� <BR>
�@@�@@ * �E�����J�݌����q�s.�ڋq��(�J�i) <BR>
�@@�@@ * �E�����J�݌����q�s.�ڋq��(�J�i) <BR>
     * <BR>
     * �Q�j�J�i�ϊ���A������A��<BR>
     * �@@this.�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B<BR>
     * �@@this.�����J�݌����q�s.�ڋq���i�J�i�j��啶���J�i�ɕϊ�����B<BR>
     * �@@�iWEB3StringTypeUtility#toUpperWbyteKana()���g�p����j<BR>
     * �@@�J�i�ϊ����this.�����J�݌����q�s.�ڋq���i�J�i�j��<BR> 
     * �@@this.�����J�݌����q�s.�ڋq���i�J�i�j�𕶎���A������B<BR>
     * <BR>
     * �R�j�@@�x�q�}�X�^����<BR>
     * �@@�ȉ��̏����ŁA�x�q�}�X�^�e�[�u������������B<BR>
     * �@@�Y���s�����݂��Ȃ��ꍇ�́Anull��ԋp���������I������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�x�q�}�X�^.�،���ЃR�[�h = �����J�݌����q�s.�،���ЃR�[�h And<BR>
     * �@@�x�q�}�X�^.���N�����N�� = �����J�݌����q�s.���N�����N�� And<BR>
     * �@@�x�q�}�X�^.���N���� = �����J�݌����q�s.���N���� And<BR>
     * �@@�i�x�q�}�X�^.�ڋq���i�J�i�j����"�@@"," "�������j = �Q�j�ŕϊ�����������<BR> 
     * <BR>
     * �S�j  �R�j�ɉ����ĊY���s�����݂���ꍇ<BR> 
     * <BR>
     * �@@�S�|�P�j  �i�����j���X�v���t�@@�����X > 1�̏ꍇY�q�����擾���A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�d���ڋq����ۑ�����B<BR> 
     * <BR>
     * �@@  �S�|�P�|�P�j  this.Y�q�}�X�^�s�ɁA�Y���s���Z�b�g����B<BR> 
     * <BR>
     * �@@  �S�|�P�|�Q�j  �d���ڋq�����擾�E�ۑ�����B(set�����J�ݐR���҂���񃊃X�g()�j�B<BR> 
     *              <BR>
     *             [set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����] <BR>
     *               �R����ʁF"3" <BR>
     *               �d���敪�Fnull <BR>
     *               �d�����X�R�[�h:null <BR>
     *               �d���ڋq�R�[�h:null <BR>
     *               Y�qID�FY�q�}�X�^�s.Y�qID <BR>
     *               �Ǘ����X�R�[�h�FY�q�}�X�^�s.�Ǘ����X�R�[�h <BR>
     *               �Ɩ��敪�FY�q�}�X�^�s.�Ɩ��敪 <BR>
     *               �Ǘ�No�D�FY�q�}�X�^�s.�Ǘ�No�D <BR>
     *               <BR>
     * �@@�@@�@@�@@�@@�@@�@@�i��Y�qID�y�ъǗ�No�D��Long�I�u�W�F�N�g�ɕϊ��j<BR> 
     * <BR>
     * <BR>
     * �@@�S�|�Q�j�uWARNING_60002�F�@@Y�q�̉\������v��ԋp�B<BR> 
     *          tag:   WARNING_60002<BR>
     * @@param l_intBranchPreference - (���X�v���t�@@�����X)<BR>
     * ���X�v���t�@@�����X�l<BR> 
     * <BR>
     * [�ݒ�l] <BR>
     * 0�F�`�F�b�N�s�v-�A���[�g�\����-�R���҂�UPDATE�� <BR>
     * 1�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�� <BR>
     * 2�F�`�F�b�N���s-�A���[�g�\����-�R���҂�UPDATE�L <BR>
     * 3�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�L <BR>
     * @@return String
     * @@roseuid 41945D1B00BE
     */
    public String validateYellowAccount(int l_intBranchPreference) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateYellowAccount()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P)�ȉ���5���ڂ��S��NULL�łȂ��ꍇ�̂݁A�ȍ~�̏��������{����B
            //�E�����J�݌����q�s.�،���ЃR�[�h
            //�E�����J�݌����q�s.���N�����N��
            //�E�����J�݌����q�s.���N����
            //�E�����J�݌����q�s.�ڋq��(�J�i)
            //�E�����J�݌����q�s.�ڋq��(�J�i)
            String l_strInstitutionCode = this.expAccountOpenParams.getInstitutionCode();
            String l_strEraBorn = this.expAccountOpenParams.getEraBorn();
            String l_strBornDate = this.expAccountOpenParams.getBornDate();
            String l_strFamilyNameAlt1 = this.expAccountOpenParams.getFamilyNameAlt1();
            String l_strGivenNameAlt1 = this.expAccountOpenParams.getGivenNameAlt1();
            
            if (l_strInstitutionCode == null || l_strEraBorn == null || 
                l_strBornDate == null || l_strFamilyNameAlt1 == null ||
                l_strGivenNameAlt1 == null)
            {
                return null;
            }
            
            //�Q�j �J�i�ϊ� ������A�� 
            String l_strFamilyNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strFamilyNameAlt1);

            String l_strGivenNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strGivenNameAlt1);

            //�J�i�ϊ����this.�����J�݌����q�s.�ڋq���i�J�i�j�� 
            //this.�����J�݌����q�s.�ڋq���i�J�i�j�𕶎���A������
            String l_strNameAlt = l_strFamilyNameAlt.concat(l_strGivenNameAlt);
            
            //�R�j �x�q�}�X�^����
            StringBuffer l_sbWhere = new StringBuffer();
            List l_lisDataBind = new ArrayList();
            
            l_sbWhere.append("institution_code = ?");
            l_lisDataBind.add(l_strInstitutionCode);
            
            l_sbWhere.append(" and era_born = ?");
            l_lisDataBind.add(l_strEraBorn);
   
            l_sbWhere.append(" and born_date = ?");
            l_lisDataBind.add(l_strBornDate);
        
            l_sbWhere.append(" and replace(replace(name_kana, ' ', ''), '�@@', '') = ?");
            l_lisDataBind.add(l_strNameAlt);
            
            Object[] l_obj = new Object[l_lisDataBind.size()];
            l_lisDataBind.toArray(l_obj);
        
            List l_lisRow = Processors.getDefaultProcessor().doFindAllQuery(
                YCustomerRow.TYPE, l_sbWhere.toString(), l_obj);
            
            if (l_lisRow == null || l_lisRow.size() == 0)
            {
                log.debug("�Y���s�����݂��Ȃ��ꍇ�́Anull��ԋp���������I������B");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //�S�j  �R�j�ɉ����ĊY���s�����݂���ꍇ
            else
            {
                //�S�|�P�j  �i�����j���X�v���t�@@�����X > 1�̏ꍇY�q�����擾���A
                //�d���ڋq����ۑ�����B
                if (l_intBranchPreference > 1) 
                {    
                    for (int i = 0 ; i < l_lisRow.size(); i++)
                    {
                        //�S�|�P�|�P�j  this.Y�q�}�X�^�s�ɁA�Y���s���Z�b�g����B
                        this.yCustomerParams = 
                            (YCustomerParams) l_lisRow.get(i);
                        
                        //�S�|�P�|�Q�j  �d���ڋq�����擾�E�ۑ�����B(set�����J�ݐR���҂���񃊃X�g()�j�B
                        this.setAccOpenJudgeWaitingInfoList(
                            WEB3ReviewCodeDef.YELLOW_ACCOUNT_CHECK, 
                            null,
                            null,
                            null,
                            new Long(this.yCustomerParams.getYCustomerId()),
                            this.getControlBranchCode(),
                            this.getOperationDiv(),
                            new Long(this.yCustomerParams.getControlNumber()));                      
                    }

                }
                
                //�S�|�Q�j�uWARNING_60002�F�@@Y�q�̉\������v��ԋp�B 
                log.exiting(STR_METHOD_NAME);
                return WEB3ErrorCatalog.WARNING_60002.getErrorCode();
                
            }
        }
        catch (DataFindException l_ex)
        {
            //�R�j �x�q�}�X�^����
            log.debug("�Y���s�����݂��Ȃ��ꍇ�́Anull��ԋp���������I������B");
            log.exiting(STR_METHOD_NAME);
            return null;
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
    }
    
    /**
     * (validate���Z�@@��)<BR>
     * ���Z�@@�ցi��s�j�}�X�^���������A�Y���s�����邩���`�F�b�N����B<BR>
     * <BR>
     * this.�����J�݌����q�s.��s�R�[�h�Cthis.�����J�݌����q�s.�x�X�R�[�h��<BR>
     * ������null�ł���΁A���Y�����͎��{���Ȃ��B�ireturn;�j<BR>
     * <BR>
     * �ȉ��̏����ŋ��Z�@@�ցi��s�j�}�X�^�e�[�u������������B<BR>
     * �Y���s�����݂��Ȃ��ꍇ�́A���݂��Ȃ����Z�@@�ւƔ��肵�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01314<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@���Z�@@�ցi��s�j�}�X�^.��s�R�[�h = this.�����J�݌����q�s.��s�R�[�h And<BR>
     * �@@���Z�@@�ցi��s�j�}�X�^.�x�X�R�[�h = this.�����J�݌����q�s.�x�X�R�[�h<BR>
     * @@roseuid 41946025035D
     */
    public void validateFinInstitution() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateFinInstitution()";
        log.entering(STR_METHOD_NAME);
        
        if (this.expAccountOpenParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            if (this.expAccountOpenParams.getFinInstitutionCode() == null
                || this.expAccountOpenParams.getFinBranchCode() == null)
            {
                log.debug("������null�ł���΁A���Y�����͎��{���Ȃ��B�ireturn;�j");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        
            FinInstitutionBankDao.findRowByPk(this.expAccountOpenParams.getFinInstitutionCode(), 
                this.expAccountOpenParams.getFinBranchCode());
        }
        catch (DataFindException l_ex)
        {
            log.error("�Y���s�����݂��Ȃ��ꍇ�́A���݂��Ȃ����Z�@@�ւƔ��肵�A��O���X���[����B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y���s�����݂��Ȃ��ꍇ�́A���݂��Ȃ����Z�@@�ւƔ��肵�A��O���X���[����B"
                + "��s�R�[�h: " + this.expAccountOpenParams.getFinInstitutionCode()
                + " �x�X�R�[�h: " + this.expAccountOpenParams.getFinBranchCode());
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
     * (validate�ڋq���T�C�Y)<BR>
     * �ڋq���̃T�C�Y���`�[���ڏ�̍ő�l���z���Ă��Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�J�i�ϊ�<BR>
     * �@@this.�����J�݌����q�s.�ڋq���i�J�i�j�𔼊p�łɕϊ�����B<BR>
     * �@@this.�����J�݌����q�s.�ڋq���i�J�i�j�𔼊p�J�i�ɕϊ�����B<BR>
     * �@@�iWEB3StringTypeUtility#to1byteKana()���g�p����j<BR>
     * <BR>
     * �Q�j�@@�J�i�̃`�F�b�N<BR>
     * �@@this.�����J�݌����q�s.�ڋq���i�J�i�j + �f�@@�f + <BR>
     * this.�����J�݌����q�s.�ڋq���i�J�i�j<BR>
     * �@@�̕�������9�������傫���ꍇ�isize > 19�j�A�x�����b�Z�[�W�R�[�h��ԋp����B<BR>
     * <BR>
     * �@@�� �ڋq���i�J�i�j�C�ڋq���i�J�i�j�́A�P�j�ŕϊ�����������<BR>
     * �@@�x�����b�Z�[�W�R�[�h�F�@@�ڋq�������T�C�Y�����߂��Ă��邽�߁A<BR>
     * �`�[��؂�̂Ă���B<BR>
     *          tag:   WARNING_60003<BR>
     * <BR>
     * �R�j�@@�����̃`�F�b�N<BR>
     * �@@this.�����J�݌����q�s.�ڋq���i�����j + �f�@@�f + <BR>
     * this.�����J�݌����q�s.�ڋq���i�����j<BR>
     * �@@�̕�������9�������傫���ꍇ�isize > 9�j�A�x�����b�Z�[�W�R�[�h��ԋp����B<BR>
     * <BR>
     * �@@�x�����b�Z�[�W�R�[�h�F�@@�ڋq�������T�C�Y�����߂��Ă��邽�߁A<BR>
     * �`�[��؂�̂Ă���B<BR>
     *          tag:   WARNING_60003<BR>
     * @@return String
     * @@roseuid 4194755C0244
     */
    public String validateAccountNameSize()
    {
        final String STR_METHOD_NAME = " validateAccountNameSize()";
        log.entering(STR_METHOD_NAME);

        if (this.expAccountOpenParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�@@�J�i�ϊ�
        String l_strNameAlt = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getFamilyNameAlt1())
            + " " + WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getGivenNameAlt1());
        
        String l_strName = this.expAccountOpenParams.getFamilyName()
            + " " + this.expAccountOpenParams.getGivenName();
        
        //�Q�j�@@�J�i�̃`�F�b�N
        if (l_strNameAlt.length() > 19)
        {
            log.debug("�Q�j�@@�J�i�̃`�F�b�N:�x�����b�Z�[�W�R�[�h�F�@@�ڋq�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60003.getErrorCode();
        }
        
        //�R�j�@@�����̃`�F�b�N
        if (l_strName.length() > 9)
        {
            log.debug("�R�j�@@�����̃`�F�b�N:�x�����b�Z�[�W�R�[�h�F�@@�ڋq�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B");
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60003.getErrorCode();
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (validate�Z���T�C�Y)<BR>
     * �Z��������̃T�C�Y���`�[���ڏ�̍ő�l���z���Ă��Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�J�i�ϊ�<BR>
     * �@@this.�����J�݌����q�s.�Z���P�i�J�i�j�𔼊p�łɕϊ�����B<BR>
     * �@@this.�����J�݌����q�s.�Z���Q�i�J�i�j�𔼊p�J�i�ɕϊ�����B<BR>
     * �@@this.�����J�݌����q�s.�Z���R�i�J�i�j�𔼊p�J�i�ɕϊ�����B<BR>
     * �@@�iWEB3StringTypeUtility#to1byteKana()���g�p����j<BR>
     * <BR>
     * �Q�j�@@�ԋp�lList�i�FArrayList�j���� <BR>
     * �@@�ԋp�lList�i�FArrayList�j�𐶐�����B <BR>
     * <BR>
     * �R�j�@@�T�C�Y��r <BR>
     * �@@���p�łɕϊ������Z���P�i�J�i�j���A�`�[���ڃT�C�Y�����傫���ꍇ�isize > 30�j�A<BR>
     * �x�����b�Z�[�W�R�[�h(*1)��ԋp�lList�ɒǉ�����B <BR>
     * �@@���p�łɕϊ������Z���Q�i�J�i�j���A�`�[���ڃT�C�Y�����傫���ꍇ�isize > 30�j�A<BR>
     * �x�����b�Z�[�W�R�[�h(*2)��ԋp�lList�ɒǉ�����B <BR>
     * �@@���p�łɕϊ������Z���R�i�J�i�j���A�`�[���ڃT�C�Y�����傫���ꍇ�isize > 30�j�A<BR>
     * �x�����b�Z�[�W�R�[�h(*3)��ԋp�lList�ɒǉ�����B <BR>
     * �@@this.�����J�݌����q�s.�Z���P���`�[���ڃT�C�Y�����傫���ꍇ�isize > 32�j�A<BR>
     * �x�����b�Z�[�W�R�[�h(*4)��ԋp�lList�ɒǉ�����B <BR>
     * �@@this.�����J�݌����q�s.�Z���Q���`�[���ڃT�C�Y�����傫���ꍇ�isize > 32�j�A<BR>
     * �x�����b�Z�[�W�R�[�h(*5)��ԋp�lList�ɒǉ�����B <BR>
     * �@@this.�����J�݌����q�s.�Z���R���`�[���ڃT�C�Y�����傫���ꍇ�isize > 32�j�A<BR>
     * �x�����b�Z�[�W�R�[�h(*6)��ԋp�lList�ɒǉ�����B <BR>
     * <BR>
     * �@@�x�����b�Z�[�W�R�[�h(*1)�F�@@�Z���P�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B <BR>
     *          tag:   WARNING_60004<BR>
     * �@@�x�����b�Z�[�W�R�[�h(*2)�F�@@�Z���Q�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B <BR>
     *          tag:   WARNING_60005<BR>
     * �@@�x�����b�Z�[�W�R�[�h(*3)�F�@@�Z���R�i�J�i�j�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B <BR>
     *          tag:   WARNING_60006<BR>
     * �@@�x�����b�Z�[�W�R�[�h(*4)�F�@@�Z���P�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B <BR>
     *          tag:   WARNING_60007<BR>
     * �@@�x�����b�Z�[�W�R�[�h(*5)�F�@@�Z���Q�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B <BR>
     *          tag:   WARNING_60008<BR>
     * �@@�x�����b�Z�[�W�R�[�h(*6)�F�@@�Z���R�����T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B <BR>
     *          tag:   WARNING_60009<BR>
     * <BR>
     * �S�j�@@�S�̃T�C�Y��r <BR>
�@@   * ���p�łɕϊ������Z���P�i�J�i�j�{�Z���Q�i�J�i�j�{�Z���R�i�J�i�j�̍��v�T�C�Y��68�������傫���ꍇ�isize > 68�j�A<BR> 
     * �x�����b�Z�[�W�R�[�h(*7)��ԋpList�ɒǉ�����B<BR>
     * <BR>
�@@   * �x�����b�Z�[�W�R�[�h(*7)�F�@@�Z�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B<BR>
     *        tag:   WARNING_60010<BR>
     * <BR>
     * 5�j�@@�x�����b�Z�[�W�R�[�h�ԋp <BR>
     * �@@�i�ԋp�lList�i�FArrayList�j.size() == 0�j�̏ꍇ�Anull��ԋp����B <BR>
     * �@@�ȊO�A�ԋp�lList�i�FArrayList�j.toArray()��ԋp����B <BR>
     * @@return String[]
     * @@roseuid 419477DE0215
     */
    public String[] validateAddressSize()
    {
        final String STR_METHOD_NAME = " validateAddressSize()";
        log.entering(STR_METHOD_NAME);
        
        if (this.expAccountOpenParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�@@�J�i�ϊ�
        String l_straddressLine1Kana = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getAddressLine1Kana());
        String l_straddressLine2Kana = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getAddressLine2Kana());
        String l_straddressLine3Kana = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getAddressLine3Kana());
        
        //�Q�j�@@�ԋp�lList�i�FArrayList�j���� 
        List l_lisWarningInfo = new ArrayList();
        
        //�R�j�@@�T�C�Y��r 
        if (l_straddressLine1Kana != null && l_straddressLine1Kana.length() > 30)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60004.getErrorCode());
        }
        
        if (l_straddressLine2Kana != null && l_straddressLine2Kana.length() > 30)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60005.getErrorCode());
        }
        
        if (l_straddressLine3Kana != null && l_straddressLine3Kana.length() > 30)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60006.getErrorCode());
        }
        
        if (this.expAccountOpenParams.getAddressLine1() != null && this.expAccountOpenParams.getAddressLine1().getBytes().length > 32)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60007.getErrorCode());
        }
        
        if (this.expAccountOpenParams.getAddressLine2() != null && this.expAccountOpenParams.getAddressLine2().getBytes().length > 32)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60008.getErrorCode());
        }
        
        if (this.expAccountOpenParams.getAddressLine3() != null && this.expAccountOpenParams.getAddressLine3().getBytes().length > 32)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60009.getErrorCode());
        }
        
        //�S�j�@@�S�̃T�C�Y��r 
        StringBuffer l_all = new StringBuffer();
        if (l_straddressLine1Kana != null)
        {
            l_all.append(l_straddressLine1Kana);
        }
        if (l_straddressLine2Kana != null)
        {
            l_all.append(l_straddressLine2Kana);
        }
        if (l_straddressLine3Kana != null)
        {
            l_all.append(l_straddressLine3Kana);
        }
        
        String l_strAll = l_all.toString();
        
        if (l_strAll.length() > 68)
        {
            //�x�����b�Z�[�W�R�[�h(*7)�F�@@�Z�������T�C�Y�����߂��Ă��邽�߁A�`�[��؂�̂Ă���B
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60010.getErrorCode());
        }
        
        // 5�j�@@�x�����b�Z�[�W�R�[�h�ԋp 
        if (l_lisWarningInfo.size() == 0)
        {
            log.debug("�i�ԋp�lList�i�FArrayList�j.size() == 0�j�̏ꍇ�Anull��ԋp����B");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.debug("�ȊO�A�ԋp�lList�i�FArrayList�j.toArray()��ԋp����B");
            String[] l_strWarnings = new String[l_lisWarningInfo.size()];
            l_lisWarningInfo.toArray(l_strWarnings);
            
            log.exiting(STR_METHOD_NAME);
            return l_strWarnings;
        }
    }

    /**
     * (saveNew�����J�݌����q)<BR>
     * �����J�݌����q�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �����J�݌����q�s�I�u�W�F�N�g�擾 <BR>
     * �@@�����J�݌����q.getDataSourceObject()�ɂČ����J�݌����q�s���擾����B <BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B <BR>
     * �@@�����J�݌����q�s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u�����J�݌����qDB�X�V�iInsert�j�d�l.xls�v�Q��<BR>
     * <BR>
     * �R�j DB�X�V <BR>
     * �@@�����J�݌����q�s�I�u�W�F�N�g�̓��e�ŁA�����J�݌����q�e�[�u����<BR>
     * �X�V�iinsert�j����B <BR>
     * @@param l_strValidateType - �`�F�b�N�^�C�v<BR>
     * <BR>
     * 0�FDEFAULT�i�ڋq�\���j<BR>
     * 1�F�Ǘ��Ґ\��<BR>
     * 2�F�\���X�V<BR>
     * 3�F�`�[�쐬<BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@param l_strCreatorCode - �쐬�҃R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h<BR>
     * <BR>
     * ���V�K�̔Ԃ����l<BR>
     * @@roseuid 4194856D035D
     */
    public void saveNewExpAccountOpen(String l_strValidateType, String l_strUpdaterCode, String l_strCreatorCode, String l_strRequestNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewExpAccountOpen(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams((ExpAccountOpenRow)this.getDataSourceObject());

            String l_strInstitutionCode = l_expAccountOpenParams.getInstitutionCode();
            String l_strBranchCode = l_expAccountOpenParams.getBranchCode();
            String l_strAccountDiv = l_expAccountOpenParams.getAccountDiv();
            
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            long l_lngBranchId = l_accountManager.getBranch(l_institution, l_strBranchCode).getBranchId();
            
            //�،����ID:�،���ЃR�[�h�ɊY������،����ID�B
            l_expAccountOpenParams.setInstitutionId(l_institution.getInstitutionId());
            
            //���X�h�c:�،���ЁC���X�R�[�h�ɊY�����镔�XID�B
            l_expAccountOpenParams.setBranchId(l_lngBranchId);
            
            //���ʃR�[�h:���ʃR�[�h�i��get�V�K���ʃR�[�h()�ɂĎ擾�������ʃR�[�h�j
            l_expAccountOpenParams.setAccOpenRequestNumber(l_strRequestNumber);
            
            //�����o�^��:null
            l_expAccountOpenParams.setAccountOpenDate(null);
            
            //�U�֋敪
            //�i�U�����s��� != null�j�̏ꍇ�A�U�֋敪�Ɂu1�F��s�U���v���Z�b�g����B
            //�i�X�֐U�֏�� != null�j�̏ꍇ�A�U�֋敪�Ɂu5�F�X���U���v���Z�b�g����B
            WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService) Services.getService(WEB3AccOpenInfoCreatedService.class);
            WEB3AccOpenApplyInfo l_applyInfo = l_service.toAccOpenApplyInfo(this);
            if (l_applyInfo.transferBankInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.BANK_TRANSFER); 
            }
            else if (l_applyInfo.postalTransferInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.POST_TRANSFER); 
            }
            
            //���������t���O:�����o��.���������̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.TRUE);
            }
            
            //�M�p����t���O:�����o��.�M�p����̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivMargin()))
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.TRUE);
            }
            
            //���t���O:�����o��.���̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivBond()))
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.TRUE);
            }
            
            //�]���Ѝt���O:�����o��.�]���Ѝ̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivWt()))
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.TRUE);
            }            
            
            //�����M���i�����j�t���O:�����o��.�����M���i�����j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundSk()))
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.TRUE);
            }            
            
            //�����M���i���Ѝj�t���O:�����o��.�����M���i���Ѝj�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundBd()))
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.TRUE);
            }            
            
            //�敨�E�I�v�V�����t���O:�����o��.�����M���i�敨�E�I�v�V�����j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFo()))
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.TRUE);
            }            
            
            //�O���،��t���O:�����o��.�����M���i�O���،��j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.TRUE);
            }            
            
            //���̑��t���O:�����o��.�����M���i���̑��j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEtc()))
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.TRUE);
            }            

            //Q&A: WEB3-ACCOUNTOPEN-A-UT-0024 
            boolean l_blnIsCustomized = false;
            
            //�o���N���i���j:�������o��.�����������v�Z����B
            //�o���N���i���j:�������o��.�����������v�Z����B
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "experience_div_equity");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "experience_div_equity");
                
                l_expAccountOpenParams.setExperienceFrom(l_strRanges[0]);
                l_expAccountOpenParams.setExperienceTo(l_strRanges[1]);
            }
            else
            {
                String l_strExperienceDivEquity = l_expAccountOpenParams.getExperienceDivEquity();
                
                String l_strExperienceFrom = null;
                String l_strExperienceTo = null;
                
                if (WEB3ExperienceDivDef.WITHOUT.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.ONE_YEAR;
                }
                else if (WEB3ExperienceDivDef.TWO_TO_THREE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.TWO_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.THREE_YEARS;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FOUR_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                }
                else if (WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                    l_strExperienceTo = null;
                }
                
                l_expAccountOpenParams.setExperienceFrom(l_strExperienceFrom);
                l_expAccountOpenParams.setExperienceTo(l_strExperienceTo);
            }
            
            //�N���i���j:���N���敪���v�Z����B
            //�N���i���j:���N���敪���v�Z����B
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "annual_income_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "annual_income_div");
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAnnualIncomeDiv());
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strIncomes[1]);
            }
            
            //���Z���Y�i���j:�����Z���Y�敪���v�Z����B
            //���Z���Y�i���j:�����Z���Y�敪���v�Z����B
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "asset_value_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "asset_value_div");
                
                l_expAccountOpenParams.setAssetValueFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAssetValueDiv());
                
                l_expAccountOpenParams.setAssetValueFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strIncomes[1]);
            }
            
            //�X�V�҃R�[�h:
            l_expAccountOpenParams.setLastUpdater(l_strUpdaterCode);           
            
            //�쐬����:��������
            l_expAccountOpenParams.setCreatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //�X�V����:��������
            l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());                                    
            
            //�쐬�҃R�[�h
            l_expAccountOpenParams.setCreator(l_strCreatorCode);
            
            //��p�U��������ԍ�:null
            l_expAccountOpenParams.setExclusiveUseAccountNo(null);
            
            //HULFT���M����:null
            l_expAccountOpenParams.setSendTimestamp(null);

            Processors.getDefaultProcessor().doInsertQuery(l_expAccountOpenParams);

            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
    }

    /**
     * (save�����J�݌����q)<BR>
     * �����J�݌����q�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �����J�݌����q�s�I�u�W�F�N�g�擾 <BR>
     * �@@�����J�݌����q.getDataSourceObject()�ɂČ����J�݌����q�s���擾����B <BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B <BR>
     * �@@�����J�݌����q�s�̓��̓f�[�^�ȊO�̍��ڂɒl���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l�u�����J�݌����qDB�X�V�iUpdate�j�d�l.xls�v�Q��<BR>
     * <BR>
     * �R�j DB�X�V <BR>
     * �@@�����J�݌����q�s�I�u�W�F�N�g�̓��e�ŁA�����J�݌����q�e�[�u����<BR>
     * �X�V�iupdate�j����B <BR>
     * @@param l_strValidateType - �`�F�b�N�^�C�v<BR>
     * <BR>
     * 0�FDEFAULT�i�ڋq�\���j<BR>
     * 1�F�Ǘ��Ґ\��<BR>
     * 2�F�\���X�V<BR>
     * 3�F�`�[�쐬<BR>
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 4194858B0179
     */
    public void saveExpAccountOpen(String l_strValidateType, String l_strUpdaterCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveExpAccountOpen(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams((ExpAccountOpenRow)this.getDataSourceObject());

            String l_strInstitutionCode = l_expAccountOpenParams.getInstitutionCode();
            String l_strAccOpenRequestNumber = l_expAccountOpenParams.getAccOpenRequestNumber();
            
            String l_strQueryString = "institution_code = ? and acc_open_request_number = ?";
            Object[] l_queryDataContainer = new Object[]{l_strInstitutionCode, l_strAccOpenRequestNumber};
            
            List l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(ExpAccountOpenRow.TYPE, l_strQueryString, l_queryDataContainer);

            if (l_lisRecords != null && l_lisRecords.size() > 0)
            {
                //�����l�V�[�g
                ExpAccountOpenRow l_expAccountOpenRowInDB = (ExpAccountOpenRow) l_lisRecords.get(0);
                l_expAccountOpenParams.setInstitutionId(l_expAccountOpenRowInDB.getInstitutionId());
                l_expAccountOpenParams.setOrderDiv(l_expAccountOpenRowInDB.getOrderDiv());
                l_expAccountOpenParams.setAccountOpenDate(l_expAccountOpenRowInDB.getAccountOpenDate());
                l_expAccountOpenParams.setCreatedTimestamp(l_expAccountOpenRowInDB.getCreatedTimestamp());
                l_expAccountOpenParams.setCreator(l_expAccountOpenRowInDB.getCreator());
                l_expAccountOpenParams.setExclusiveUseAccountNo(l_expAccountOpenRowInDB.getExclusiveUseAccountNo());
                l_expAccountOpenParams.setSendTimestamp(l_expAccountOpenRowInDB.getSendTimestamp());
            }

            String l_strBranchCode = l_expAccountOpenParams.getBranchCode();
            String l_strAccountDiv = l_expAccountOpenParams.getAccountDiv();
            
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            long l_lngBranchId = l_accountManager.getBranch(
                l_accountManager.getInstitution(l_strInstitutionCode),
                l_strBranchCode).getBranchId();
            
            //���X�h�c:�،���ЁC���X�R�[�h�ɊY�����镔�XID�B
            l_expAccountOpenParams.setBranchId(l_lngBranchId);
            
            //�U�֋敪
            //�i�U�����s��� != null�j�̏ꍇ�A�U�֋敪�Ɂu1�F��s�U���v���Z�b�g����B
            //�i�X�֐U�֏�� != null�j�̏ꍇ�A�U�֋敪�Ɂu5�F�X���U���v���Z�b�g����B
            WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService) Services.getService(WEB3AccOpenInfoCreatedService.class);
            WEB3AccOpenApplyInfo l_applyInfo = l_service.toAccOpenApplyInfo(this);
            if (l_applyInfo.transferBankInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.BANK_TRANSFER); 
            }
            else if (l_applyInfo.postalTransferInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.POST_TRANSFER); 
            }
            
            //���������t���O:�����o��.���������̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.TRUE);
            }
            
            //�M�p����t���O:�����o��.�M�p����̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivMargin()))
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.TRUE);
            }
            
            //���t���O:�����o��.���̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivBond()))
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.TRUE);
            }
            
            //�]���Ѝt���O:�����o��.�]���Ѝ̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivWt()))
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.TRUE);
            }            
            
            //�����M���i�����j�t���O:�����o��.�����M���i�����j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundSk()))
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.TRUE);
            }            
            
            //�����M���i���Ѝj�t���O:�����o��.�����M���i���Ѝj�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundBd()))
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.TRUE);
            }            
            
            //�敨�E�I�v�V�����t���O:�����o��.�����M���i�敨�E�I�v�V�����j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFo()))
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.TRUE);
            }            
            
            //�O���،��t���O:�����o��.�����M���i�O���،��j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.TRUE);
            }            
            
            //���̑��t���O:�����o��.�����M���i���̑��j�̃R�[�h�l�ɑΉ������l�B
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEtc()))
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.TRUE);
            }            
            
            //Q&A: WEB3-ACCOUNTOPEN-A-UT-0024 
            boolean l_blnIsCustomized = false;
            
            //�o���N���i���j:�������o��.�����������v�Z����B
            //�o���N���i���j:�������o��.�����������v�Z����B
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "experience_div_equity");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "experience_div_equity");
                
                l_expAccountOpenParams.setExperienceFrom(l_strRanges[0]);
                l_expAccountOpenParams.setExperienceTo(l_strRanges[1]);
            }
            else
            {
                String l_strExperienceDivEquity = l_expAccountOpenParams.getExperienceDivEquity();
                
                String l_strExperienceFrom = null;
                String l_strExperienceTo = null;
                
                if (WEB3ExperienceDivDef.WITHOUT.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.ONE_YEAR;
                }
                else if (WEB3ExperienceDivDef.TWO_TO_THREE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.TWO_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.THREE_YEARS;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FOUR_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                }
                else if (WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                    l_strExperienceTo = null;
                }
                
                l_expAccountOpenParams.setExperienceFrom(l_strExperienceFrom);
                l_expAccountOpenParams.setExperienceTo(l_strExperienceTo);
            }
            
            //�N���i���j:���N���敪���v�Z����B
            //�N���i���j:���N���敪���v�Z����B
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "annual_income_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "annual_income_div");
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAnnualIncomeDiv());
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strIncomes[1]);
            }
            
            //���Z���Y�i���j:�����Z���Y�敪���v�Z����B
            //���Z���Y�i���j:�����Z���Y�敪���v�Z����B
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "asset_value_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "asset_value_div");
                
                l_expAccountOpenParams.setAssetValueFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAssetValueDiv());
                
                l_expAccountOpenParams.setAssetValueFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strIncomes[1]);
            }
            
            //�X�V�҃R�[�h:�Ǘ���.�Ǘ��҃R�[�h�B
            l_expAccountOpenParams.setLastUpdater(l_strUpdaterCode);
            
            //�X�V����:��������
            l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            Processors.getDefaultProcessor().doUpdateQuery(l_expAccountOpenParams);
            
            //U01059
            this.expAccountOpenParams = l_expAccountOpenParams;
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
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
    }

    /**
     * (get�����J�݌����q)<BR>
     * �istatic ���\�b�h�j<BR>
     * �w��ɊY����������J�݌����q�I�u�W�F�N�g��List���擾����B <BR>
     * <BR>
     * �P�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����J�݌����q�s<BR>
     * �I�u�W�F�N�g��List���擾����B <BR>
     * �@@�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00398<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����] <BR>
     * �@@�@@rowType�F�@@�����J�݌����qRow.TYPE <BR>
     * �@@�@@where�F�@@�������������� <BR>
     * �@@�@@orderBy�F�@@�\�[�g���� <BR>
     * �@@�@@conditions�F�@@null <BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i <BR>
     * <BR>
     * �Q�j�@@�ԋp�lList�i�FArrayList�j����<BR>
     * �@@�ԋp�lList�i�FArrayList�j�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�ǉ���������<BR>
     * �@@�P�j�Ŏ擾�����e�v�f�ɂ��āA�R�|�P�j�`�R�|�U�j�����{����B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�����J�݌����q�I�u�W�F�N�g����<BR>
     * �@@�@@�Ώۗv�f�i�F�����J�݌����qParams�j���w�肵�A�����J�݌����q<BR>
     * �I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@[�R���X�g���N�^�̈���]<BR>
     * �@@�@@�����J�݌����q�s�F�@@�i�Ώۗv�f�j<BR>
     * <BR>
     * �@@�R�|�Q)�@@�`�[�X�e�[�^�X�擾<BR>
     * �@@�@@�����J�݌����q.get�`�[�X�e�[�^�X()�ɂāA�����J�ݓ`�[�쐬�X�e�[�^�X��<BR>
     * �z����擾����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@SONAR���M���i���j�̔���<BR>
     * �@@���@@SONAR���M���i���j�Ɏw�肪����ꍇ�iSONAR���M���i���j != null�j�̂�<BR>
     * <BR>
     * �@@�@@�擾�����e�`�[�X�e�[�^�X(**1)�̂����A�ȉ��̏����ɓ��Ă͂܂�v�f��<BR>
     * �P�����݂��Ȃ��ꍇ�A<BR>
     * �@@�@@���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@SONAR���M���i���j < �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M����<BR>
     * <BR>
     * �@@�@@(**1)�@@�e�`�[�X�e�[�^�X<BR>
     * �@@�@@�����Łi�����J�ݏ󋵋敪 == �h�G���[�����h�j���w�肳��Ă���ꍇ�́A<BR>
     * �@@�@@�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h���M�G���[�h�܂��́A
     * �@@�@@�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h��M�G���[�h<BR>
     * �@@�@@�̓`�[�쐬�X�e�[�^�X�s�݂̂�ΏۂƂ���B<BR>
     * �@@�@@�ȊO�Aget�`�[�X�e�[�^�X()�ɂĎ擾�ł������ׂĂ̍s���ΏہB<BR>
     * <BR>
     * �@@�R�|�S�j�@@SONAR���M���i���j�̔���<BR>
     * �@@���@@SONAR���M���i���j�Ɏw�肪����ꍇ�iSONAR���M���i���j != null�j�̂�<BR>
     * <BR>
     * �@@�@@�擾�����e�`�[�X�e�[�^�X(**1)�̂����A�ȉ��̏����ɓ��Ă͂܂�v�f��<BR>
     * �P�����݂��Ȃ��ꍇ�A<BR>
     * �@@�@@���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@SONAR���M���i���j > �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M����<BR>
     * <BR>
     * �@@�R�|�T�j�@@�����J�ݏ󋵋敪�̔���<BR>
     * �@@���@@�����J�ݏ󋵋敪�Ɏw�肪����ꍇ�i�����̌����J�ݏ󋵋敪 != null�j�̂�<BR>
     * <BR>
     * �@@�@@�����J�ݏ󋵋敪���w��̒l�ƈႤ�ꍇ<BR>
     * �i�����J�݌����q.get�����J�ݏ󋵋敪 != �����J�ݏ󋵋敪�j�A<BR>
     * �@@�@@���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B<BR>
     * <BR>
     * �@@�R�|�U�j�@@�ԋp�lList�i�FArrayList�j�ɒǉ�<BR>
     * �@@�@@�R�|�P�j�Ő������������J�݌����q�I�u�W�F�N�g���A<BR>
     * �ԋp�lList�i�FArrayList�j�ɒǉ��iadd()�j����B<BR>
     * <BR>
     * �S�j�@@�ԋp�lList�i�FArrayList�j��ԋp����B<BR>
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@param l_datSonarSendDateFrom - SONAR���M���i���j
     * @@param l_datSonarSendDateTo - SONAR���M���i���j
     * @@param l_strAccountOpenStatusDiv - �����J�ݏ󋵋敪<BR>
     * <BR>
     * �@@0�F�@@DEFAULT�i���J�݁j<BR>
     * �@@1�F�@@�J�ݒ�<BR>
     * �@@2�F�@@�G���[����<BR>
     * �@@3�F�@@�J�ݍ�<BR>
     *
     * @@return List
     * @@roseuid 419C6107039D
     */
    public static List getExpAccountOpen(String l_strQueryString, String[] l_queryContainer, String l_strSortCond, Date l_datSonarSendDateFrom, Date l_datSonarSendDateTo, String l_strAccountOpenStatusDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExpAccountOpen(String, String[], String, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����J�݌����q�s�I�u�W�F�N�g��List���擾����B
            List l_lisRow = Processors.getDefaultProcessor().doFindAllQuery(ExpAccountOpenRow.TYPE,
                l_strQueryString, l_strSortCond, null, l_queryContainer);
            
            if (l_lisRow == null || l_lisRow.size() == 0)
            {
                log.debug("�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                    "�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
            }
            
            //�Q�j�@@�ԋp�lList�i�FArrayList�j���� 
            List l_lisReturnList = new ArrayList();
            
            //�R�j�@@�ǉ���������
            int l_intLength = l_lisRow.size();
            //�P�j�Ŏ擾�����e�v�f�ɂ��āA�R�|�P�j�`�R�|�U�j�����{����B
            for (int i = 0; i < l_intLength; i++)
            {
                //�R�|�P�j�@@�����J�݌����q�I�u�W�F�N�g���� 
                WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                    new ExpAccountOpenParams((ExpAccountOpenRow) l_lisRow.get(i)));
                
                //�R�|�Q)�@@�`�[�X�e�[�^�X�擾 
                WEB3AccOpenVoucherCreatedStatus[] l_vocherCreatedStatus = l_accOpenExpAccountOpen.getVoucherStatus();                

                //�R�|�R�j�@@SONAR���M���i���j�̔��� 
                //���@@SONAR���M���i���j�Ɏw�肪����ꍇ�iSONAR���M���i���j != null�j�̂�
                if (l_datSonarSendDateFrom != null)
                {
                    log.debug("���@@SONAR���M���i���j�Ɏw�肪����ꍇ�iSONAR���M���i���j != null�j�̂�");
                    int l_intCount = 0;
                    
                    if (l_vocherCreatedStatus == null)
                    {
                        continue;
                    }
                    
                    int l_intStatusLength = l_vocherCreatedStatus.length;
                    
                    for (int j = 0; j < l_intStatusLength; j++)
                    {
                        Timestamp l_tsSendTimestamp = ((AccOpenVoucherStatusRow) l_vocherCreatedStatus[j].getDataSourceObject()).getSendTimestamp();
                        
                        if (l_tsSendTimestamp == null)
                        {
                            continue;
                        }
                        //�����Łi�����J�ݏ󋵋敪 == �h�G���[�����h�j���w�肳��Ă���ꍇ��
                        if (WEB3AccountOpenStatusDivDef.ERROR.equals(l_strAccountOpenStatusDiv))
                        {
                            log.debug("�����Łi�����J�ݏ󋵋敪 == �h�G���[�����h�j���w�肳��Ă���ꍇ��");
                            //�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h���M�G���[�h�܂��́A 
                            //�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h��M�G���[�h 
                            if (WEB3VoucherStatusDef.SEND_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()) || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()))
                            {
                                log.debug("�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h���M�G���[�h�܂��́A�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h��M�G���[�h");
                                //SONAR���M���i���j > �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M����
                                //QA:WEB3-ACCOUNTOPEN-A-FT-0042
                                if (WEB3DateUtility.compareToSecond(l_datSonarSendDateFrom, l_tsSendTimestamp) <= 0)
                                {
                                    log.debug("SONAR���M���i���j < �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M����");
                                    l_intCount = l_intCount + 1;
                                }
                            }
                        }
                        else
                        {
                            log.debug("�����Łi�����J�ݏ󋵋敪 != �h�G���[�����h�j���w�肳��Ă���ꍇ��");
                            //SONAR���M���i���j > �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M����
                            if (WEB3DateUtility.compareToSecond(l_datSonarSendDateFrom, l_tsSendTimestamp) <= 0)
                            {
                                log.debug("SONAR���M���i���j < �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M����");
                                l_intCount = l_intCount + 1;
                            }
                        }
                    }
                    
                    //�擾�����e�`�[�X�e�[�^�X(**1)�̂����A�ȉ��̏����ɓ��Ă͂܂�v�f���P�����݂��Ȃ��ꍇ�A 
                    //���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B 
                    if (l_intCount == 0)
                    {
                        log.debug("�擾�����e�`�[�X�e�[�^�X(**1)�̂����A�ȉ��̏����ɓ��Ă͂܂�v�f���P�����݂��Ȃ��ꍇ�A���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B");
                        continue;
                    }
                }
                
                //�R�|�S�j�@@SONAR���M���i���j�̔��� 
                if (l_datSonarSendDateTo != null)
                {
                    log.debug("�R�|�S�j�@@SONAR���M���i���j!= null");
                    int l_intCount = 0;
                    
                    if (l_vocherCreatedStatus == null)              
                    {               
                        continue;               
                    }               
                                
                    int l_intStatusLength = l_vocherCreatedStatus.length;               

                    
                    for (int j = 0; j < l_intStatusLength; j++)
                    {
                        Timestamp l_tsSendTimestamp = ((AccOpenVoucherStatusRow) l_vocherCreatedStatus[j].getDataSourceObject()).getSendTimestamp();
                        
                        if (l_tsSendTimestamp == null)
                        {
                            continue;
                        }
                        //�����Łi�����J�ݏ󋵋敪 == �h�G���[�����h�j���w�肳��Ă���ꍇ��
                        if (WEB3AccountOpenStatusDivDef.ERROR.equals(l_strAccountOpenStatusDiv))
                        {
                            log.debug("�����Łi�����J�ݏ󋵋敪 == �h�G���[�����h�j���w�肳��Ă���ꍇ��");
                            //�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h���M�G���[�h�܂��́A 
                            //�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h��M�G���[�h 
                            if (WEB3VoucherStatusDef.SEND_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()) || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()))
                            {
                                log.debug("�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h���M�G���[�h�܂��́A�����J�ݓ`�[�쐬�X�e�[�^�X[index].�`�[�쐬�X�e�[�^�X == �h��M�G���[�h");
                                //SONAR���M���i���j < �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M���� 
                                if (l_datSonarSendDateTo.compareTo(l_tsSendTimestamp) >= 0)
                                {
                                    log.debug("SONAR���M���i���j > �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M���� ");
                                    l_intCount = l_intCount + 1;
                                }
                            }
                        }
                        else
                        {
                            //SONAR���M���i���j < �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M���� 
                            if (WEB3DateUtility.compareToSecond(l_datSonarSendDateTo, l_tsSendTimestamp) >= 0)
                            {
                                log.debug("SONAR���M���i���j > �����J�ݓ`�[�쐬�X�e�[�^�X[index].���M����");
                                l_intCount = l_intCount + 1;
                            }
                        }
                    }
                    
                    //�擾�����e�`�[�X�e�[�^�X(**1)�̂����A�ȉ��̏����ɓ��Ă͂܂�v�f���P�����݂��Ȃ��ꍇ�A 
                    //���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B 
                    if (l_intCount == 0)
                    {
                        log.debug("�擾�����e�`�[�X�e�[�^�X(**1)�̂����A�ȉ��̏����ɓ��Ă͂܂�v�f���P�����݂��Ȃ��ꍇ�A���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B");
                        continue;
                    }
                }

                //�R�|�T�j�@@�����J�ݏ󋵋敪�̔��� 
                if (l_strAccountOpenStatusDiv != null)
                {
                    if (!l_strAccountOpenStatusDiv.equals(l_accOpenExpAccountOpen.getAccountOpenStatusDiv()))
                    {
                        log.debug("�����J�ݏ󋵋敪���w��̒l�ƈႤ�ꍇ�i�����J�݌����q.get�����J�ݏ󋵋敪 != �����J�ݏ󋵋敪�j�A���Y�v�f�ɂ��āA�ȍ~�̏��������{���Ȃ��icontinue;�j�B");
                        continue;
                    }
                }
                
                //�R�|�U�j�@@�ԋp�lList�i�FArrayList�j�ɒǉ�
                log.debug("�R�|�U�j�@@�ԋp�lList�i�FArrayList�j�ɒǉ�");
                l_lisReturnList.add(l_accOpenExpAccountOpen);
            }
            
            //�S�j�@@�ԋp�lList�i�FArrayList�j��ԋp����B 
            log.exiting(STR_METHOD_NAME);
            return l_lisReturnList;
        }
        catch (DataFindException l_ex)
        {
            log.debug("�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                "�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�����J�݌����q�`�[)<BR>
     * �istatic ���\�b�h�j<BR>
     *  (�󋵖⍇���ꗗ�p���\�b�h)<BR>
     * �w��ɊY����������J�݌����q�I�u�W�F�N�g��List���擾����B <BR>
     * <BR>
     * �P�j�@@SONAR���M��(��)�ASONAR���M��(��)��萔�ɒ�`����B<BR>
     * <BR>
     * �@@�@@    final String SONAR_DATE_FROM = "SONAR_DATE_FROM"<BR>
     *         final String SONAR_DATE_TO = "SONAR_DATE_TO"<BR>
     * <BR>
     * �Q�j�@@����.�����J�ݏ� != null�̏ꍇ�A���������ɒǉ�����B<BR>
     * �@@�Q�|�P�j�@@�����J�ݏ󋵋敪�F�h�J�ݍρh�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@��������������<BR>
     * �@@�@@�@@�@@�@@�@@" and account_open_date is not null "<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����J�ݏ󋵋敪�F�h���J�݁h�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@��������������<BR>
     * �@@�@@�@@�@@�@@�@@" and acc_open_request_number not in "<BR>
     *             + "(select distinct exp_account_open.acc_open_request_number from acc_open_voucher_status b "<BR>
     *             + "where exp_account_open.acc_open_request_number = b.acc_open_request_number and b.voucher_status > ?"<BR>
     *             + " and b.institution_code = ? )"<BR>
     * �@@�@@�@@�@@�@@�@@<BR>
     * �@@�@@�@@�@@�@@���������f�[�^<BR>
     * �@@�@@�@@�@@�@@�@@�`�[�쐬�X�e�[�^�X�F�O�u���J�݁v<BR>
     * �@@�@@�@@�@@�@@�@@����.�f�[�^�R���e�i[0]<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�����J�ݏ󋵋敪�F�h�J�ݒ��h�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@��������������<BR>
     *             " and account_open_date is null "<BR>
     *             + " and acc_open_request_number in "<BR>
     *             + "(select distinct acc_open_request_number from acc_open_voucher_status "<BR>
     *             + "where voucher_status not in (?, ?, ?) "<BR>
     *             + "and institution_code = ? "<BR>
     *             + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")"<BR>
     * �@@�@@�@@�@@�@@���������f�[�^<BR>
     * �@@�@@�@@�@@�@@�@@�`�[�쐬�X�e�[�^�X�F�O�u���J�݁v<BR>
     * �@@�@@�@@�@@�@@�@@�`�[�쐬�X�e�[�^�X�F�T�u���M�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@�`�[�쐬�X�e�[�^�X�F�U�u��M�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@����.�f�[�^�R���e�i[0]<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�����J�ݏ󋵋敪�F�h�G���[�����h�̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@��������������<BR>
     * �@@�@@�@@�@@�@@�@@" and acc_open_request_number in "<BR>
     * �@@�@@�@@�@@�@@�@@+ " (select distinct acc_open_request_number from acc_open_voucher_status "<BR>
     *             + "where voucher_status in (?, ?) "<BR>
     *             + "and institution_code = ? "<BR>
     *             + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")"<BR>
     * �@@�@@�@@�@@�@@���������f�[�^<BR>
     * �@@�@@�@@�@@�@@�@@�`�[�쐬�X�e�[�^�X�F�T�u���M�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@�`�[�쐬�X�e�[�^�X�F�U�u��M�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@����.�f�[�^�R���e�i[0]<BR>
     * <BR>
     * �R�j�@@����.SONAR���M��(��) != null�̏ꍇ�A���������ɒǉ�����B<BR>
     * �@@�R�|�P�j�@@SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[�����̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���������������SONAR_DATE_FROM��<BR>
     * �@@�@@�@@�@@�@@�@@" and send_timestamp >= ?"<BR>
     * �@@�@@�@@�@@�@@�@@�ɒu������B<BR>
     * �@@�R�|�Q�j�@@SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == null or ���J�� or �J�ݍς̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��������������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@" and acc_open_request_number in "<BR>
     * �@@�@@�@@�@@�@@�@@+ "(select acc_open_request_number from acc_open_voucher_status "<BR>
     * �@@�@@�@@�@@�@@�@@+ "where send_timestamp >= ? )"<BR>
     * �@@�R�|�R�j�@@�R�|�P�j�A�R�|�Q�j�̎��A<BR>
     * �@@�@@�@@�@@�@@�@@���������f�[�^�Ɉ���.SONAR���M���i���j��ǉ�����B<BR>
     * �@@�R�|�S�j�@@SONAR���M��(��) == null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[�����̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���������������SONAR_DATE_FROM���󕶎�""�ɒu������B<BR>
     * <BR>
     * �S�j�@@����.SONAR���M��(��) != null�̏ꍇ�A���������ɒǉ�����B<BR>
     * �@@�S�|�P�j�@@SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[�����̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���������������SONAR_DATE_TO��<BR>
     * �@@�@@�@@�@@�@@�@@" and send_timestamp < ?"<BR>
     * �@@�@@�@@�@@�@@�@@�ɒu������B<BR>
     * �@@�S�|�Q�j�@@SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == null or ���J�� or �J�ݍς̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��������������ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@" and acc_open_request_number in "<BR>
     * �@@�@@�@@�@@�@@�@@+ "(select acc_open_request_number from acc_open_voucher_status "<BR>
     * �@@�@@�@@�@@�@@�@@+ "where send_timestamp < ? )"<BR>
     * �@@�S�|�S�j�@@�S�|�P�j�A�S�|�Q�j�̎��A<BR>
     * �@@�@@�@@�@@�@@�@@���������f�[�^�Ɉ���.SONAR���M���i���j�̗�����ǉ�����B<BR>
     * �@@�S�|�S�j�@@SONAR���M��(��) == null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[�����̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@���������������SONAR_DATE_TO���󕶎�""�ɒu������B<BR>
     * <BR>
     * �T�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����J�݌����q�s�I�u�W�F�N�g��ListPage���擾����B <BR>
     * �@@�T�|�P�j�����J�݌����q�s�I�u�W�F�N�g��ListPage���擾����B<BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����] <BR>
     * �@@�@@rowType�F�@@�����J�݌����qRow.TYPE <BR>
     * �@@�@@where�F�@@�������������� <BR>
     * �@@�@@orderBy�F�@@�\�[�g���� <BR>
     * �@@�@@conditions�F�@@null <BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i<BR>
     * �@@�@@pageSize�F�@@�y�[�W���\���s��<BR>
     * �@@�@@pageNumber�F�@@�v���y�[�W�ԍ�<BR>
     * <BR>
     * �@@�T�|�Q�j�Y���f�[�^���Ȃ��ꍇ�A�����J�݌����q�s�I�u�W�F�N�g��ListPage���擾����<BR>
     * �@@�@@�@@�@@�@@���N�G�X�g.�v���y�[�W�ԍ����ŏI�y�[�W�𒴂����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�����J�݌����q�s�I�u�W�F�N�g�̍ŏI�y�[�W��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@[doFindAllQuery()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@rowType�F�@@�����J�݌����qRow.TYPE <BR>
     * �@@�@@�@@�@@�@@�@@�@@where�F�@@�������������� <BR>
     * �@@�@@�@@�@@�@@�@@�@@orderBy�F�@@�\�[�g���� <BR>
     * �@@�@@�@@�@@�@@�@@�@@conditions�F�@@null <BR>
     * �@@�@@�@@�@@�@@�@@�@@bindVars�F�@@���������f�[�^�R���e�i<BR>
     * �@@�@@�@@�@@�@@�@@�@@pageSize�F�@@�y�[�W���\���s��<BR>
     * �@@�@@�@@�@@�@@�@@�@@pageNumber�F�@@�T�|�P�j�Ŏ擾�����ŏI�y�[�W<BR>
     * <BR>
     * �@@���Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �U�j�@@�ԋp�lListPage��ԋp����B<BR>
     * @@param l_intPageIndex - �v���y�[�W�ԍ�
     * @@param l_intPageSize - �y�[�W���\���s��
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@param l_datSonarSendDateFrom - SONAR���M���i���j
     * @@param l_datSonarSendDateTo - SONAR���M���i���j
     * @@param l_strAccountOpenStatusDiv - �����J�ݏ󋵋敪
     * <BR>
     * �@@0�F�@@DEFAULT�i���J�݁j<BR>
     * �@@1�F�@@�J�ݒ�<BR>
     * �@@2�F�@@�G���[����<BR>
     * �@@3�F�@@�J�ݍ�<BR>
     *
     * @@return List
     */
    public static ListPage getExpAccountOpenVoucher(int l_intPageIndex, int l_intPageSize, String l_strQueryString, String[] l_queryContainer, String l_strSortCond, Date l_datSonarSendDateFrom, Date l_datSonarSendDateTo, String l_strAccountOpenStatusDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExpAccountOpenVoucher(int, int, String, String[], String, Date, Date, String)";
        log.entering(STR_METHOD_NAME);
        final String SONAR_DATE_FROM = "SONAR_DATE_FROM";
        final String SONAR_DATE_TO = "SONAR_DATE_TO";
        
        ArrayList l_lstBind = new ArrayList();
        for (int i = 0; i < l_queryContainer.length; i++)
        {
            l_lstBind.add(l_queryContainer[i]);
        }
        

        //�p�����[�^.�����J�ݏ󋵋敪 != null�̏ꍇ�A���������ɒǉ�����B
        if (l_strAccountOpenStatusDiv != null)
        {
            if (WEB3AccountOpenStatusDivDef.OPEN_COMPLETE.equals(l_strAccountOpenStatusDiv))
            {
                //�����J�ݏ󋵋敪�F"�J�ݍ�"
                l_strQueryString += " and account_open_date is not null ";
            }
            else if (WEB3AccountOpenStatusDivDef.DEFAULT.equals(l_strAccountOpenStatusDiv))
            {
                //�����J�ݏ󋵋敪�F"���J��"
                l_strQueryString += " and acc_open_request_number not in "
                    + "(select distinct exp_account_open.acc_open_request_number from acc_open_voucher_status b "
                    + "where exp_account_open.acc_open_request_number = b.acc_open_request_number and b.voucher_status > ? "
                    + " and b.institution_code = ? )";
                    
                l_lstBind.add(WEB3VoucherStatusDef.DEFAULT);
                l_lstBind.add(l_queryContainer[0]);
            }
            else if (WEB3AccountOpenStatusDivDef.OPENING.equals(l_strAccountOpenStatusDiv))
            {
                //�����J�ݏ󋵋敪�F"�J�ݒ�"
                l_strQueryString += " and account_open_date is null "
                    + " and acc_open_request_number in "
                    + "(select distinct acc_open_request_number from acc_open_voucher_status "
                    + "where voucher_status not in (?, ?, ?) "
                    + "and institution_code = ? "
                    + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")";

                l_lstBind.add(WEB3VoucherStatusDef.DEFAULT);
                l_lstBind.add(WEB3VoucherStatusDef.SEND_ERROR);
                l_lstBind.add(WEB3VoucherStatusDef.RECEIVE_ERROR);
                l_lstBind.add(l_queryContainer[0]);
            }
            else
            {
                //�����J�ݏ󋵋敪�F"�G���[����"
                l_strQueryString += " and acc_open_request_number in "
                    + " (select distinct acc_open_request_number from acc_open_voucher_status "
                    + "where voucher_status in (?, ?) "
                    + "and institution_code = ? "
                    + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")";

                l_lstBind.add(WEB3VoucherStatusDef.SEND_ERROR);
                l_lstBind.add(WEB3VoucherStatusDef.RECEIVE_ERROR);
                l_lstBind.add(l_queryContainer[0]);
            }
        }

        // SONAR���M��(��)�����������ɒǉ�����B
        if (l_datSonarSendDateFrom != null)
        {
            if (l_strQueryString.indexOf(SONAR_DATE_FROM) > 0)
            {
                //SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[����
                l_strQueryString = l_strQueryString.replaceAll(SONAR_DATE_FROM, " and send_timestamp >= ?");
            }
            else
            {
                //SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == null or ���J�� or �J�ݍ�
                l_strQueryString += " and acc_open_request_number in (select acc_open_request_number from acc_open_voucher_status where send_timestamp >= ?)";
            }
            
            //SONAR���M��(��)��ǉ�����B
            l_lstBind.add(l_datSonarSendDateFrom);
        }
        else
        {
            if (l_strQueryString.indexOf(SONAR_DATE_FROM) > 0)
            {
                //SONAR���M��(��) == null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[����
                //��������������
                l_strQueryString = l_strQueryString.replaceAll("SONAR_DATE_FROM", "");
            }
        }

        //SONAR���M��(��)�����������ɒǉ�����B
        //���p�����[�^.SONAR���M��(��)�̗���������ΏۂƂ���B
        if (l_datSonarSendDateTo != null)
        {
            if (l_strQueryString.indexOf(SONAR_DATE_TO) > 0)
            {
                //SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[����
                l_strQueryString = l_strQueryString.replaceAll(SONAR_DATE_TO, " and send_timestamp < ? ");
            }
            else
            {
                //SONAR���M��(��) != null ���A�����J�ݏ󋵋敪 == null or ���J�� or �J�ݍ�
                l_strQueryString += " and acc_open_request_number in (select acc_open_request_number from acc_open_voucher_status where send_timestamp < ?)";
            }
            
            //SONAR���M��(��)�̗�����ǉ�����B
            Date l_datSonarSendNextDate = WEB3DateUtility.addDay(l_datSonarSendDateTo, 1);
            l_lstBind.add(l_datSonarSendNextDate);
        }
        else
        {
            if (l_strQueryString.indexOf(SONAR_DATE_TO) > 0)
            {
                //SONAR���M��(��) == null ���A�����J�ݏ󋵋敪 == �J�ݒ� or �G���[����
                //��������������
                l_strQueryString = l_strQueryString.replaceAll("SONAR_DATE_TO", "");
            }
        }

        
        //pageNumber��0����n�܂�ׁA���N�G�X�g.�v���y�[�W�ԍ���ϊ�����B
        int l_intPageNumber = l_intPageIndex - 1;
        
        try
        {
            log.debug("�����J�݌����q�s�I�u�W�F�N�g��List���擾��������");

            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            //�����J�݌����q�s�I�u�W�F�N�g��List���擾����B
            ListPage l_lisRow = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_lstBind.toArray(),
                l_intPageSize,
                l_intPageNumber);

            log.debug("List�擾�����܂Ł@@�擾�����q�s�����F " + l_lisRow.size());
            
            if (l_lisRow == null || l_lisRow.size() == 0)
            {
                if (l_lisRow.totalSize() != 0
                    && l_lisRow.totalPages() < l_intPageIndex)
                {
                    //�ŏI�y�[�W�\���p�v���y�[�W�ԍ�
                    int l_intLastPageIndex = l_lisRow.totalPages() - 1;

                    //���N�G�X�g.�v���y�[�W�ԍ����ŏI�y�[�W�𒴂����ꍇ�A
                    //�����J�݌����q�s�I�u�W�F�N�g�̍ŏI�y�[�W��List���擾����B
                    l_lisRow = l_queryProcessor.doFindAllQuery(
                        ExpAccountOpenRow.TYPE,
                        l_strQueryString,
                        l_strSortCond,
                        null,
                        l_lstBind.toArray(),
                        l_intPageSize,
                        l_intLastPageIndex);
                    
                    log.debug("�ŏI�y�[�W�����q�s�����F " + l_lisRow.size());
                    
                    if (l_lisRow == null || l_lisRow.size() == 0)
                    {
                        //�����q�ɊY���f�[�^���Ȃ��ꍇ�A��O���X���[����B
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                            WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                            "�����q�ɊY���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
                    }
                }
                else
                {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                    "�����q�ɊY���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
                }
            }

            //�ԋp�lPageList��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_lisRow; 

        }
        catch (DataFindException l_ex)
        {
            log.debug("�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                "�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }


    /**
     * @@return Object
     * @@roseuid 41B45E6A01A5
     */
    public Object getDataSourceObject()
    {
        return this.expAccountOpenParams;
    }

    /**
     * �����J�݌����q�I�u�W�F�N�g������������B<BR>
     * <BR>
     * �P�j�@@�����J�ݓ`�[�쐬�X�e�[�^�X�v���p�e�B�̃Z�b�g<BR>
     * �@@�����J�ݓ`�[�쐬�X�e�[�^�X.get�����J�ݓ`�[�쐬�X�e�[�^�X()�̖߂�l��<BR>
     * ���������I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �@@[get�����J�ݓ`�[�쐬�X�e�[�^�X()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h�F�@@�����J�݌����s.�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h�F�@@�����J�݌����s.���ʃR�[�h<BR>
     * <BR>
     * �Q�j�@@�����J�݌����q�s�v���p�e�B�̃Z�b�g<BR>
     * �@@�����J�݌����s�𐶐������I�u�W�F�N�g�̃v���p�e�B�ɃZ�b�g���A�ԋp����B<BR>
     * <BR>
     * �� �����J�݌����qParams�N���X��DDL��莩����������B<BR>
     * @@param l_expAccountOpenParams - �����J�݌����q�s<BR>
     * <BR>
     * �� �����J�݌����qParams�N���X��DDL��莩����������B<BR>
     *
     */
    private void init(ExpAccountOpenParams l_expAccountOpenParams)
        throws WEB3BaseException
    {
        this.expAccountOpenParams = l_expAccountOpenParams;
        this.accOpenVoucherCreatedStatuses = WEB3AccOpenVoucherCreatedStatus.getAccOpenVoucherCreatedStatus(
            l_expAccountOpenParams.getInstitutionCode(),        //�،���ЃR�[�h
            l_expAccountOpenParams.getAccOpenRequestNumber()    //���ʃR�[�h
            );
    }

    /**
     * �e�ЃJ�X�^�}�C�Y�̔��f
     */
    private boolean isCustomized(String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountDiv, String l_strValidateType, String l_strCheckItem) throws WEB3BaseException
    {
        try
        {
            new WEB3AccOpenItemMaster(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv,
                l_strValidateType, l_strCheckItem);
        }
        catch (NotFoundException l_ex)
        {
            try
            {
                new WEB3AccOpenItemMaster(l_strInstitutionCode, "000", l_strAccountDiv,
                    l_strValidateType, l_strCheckItem);
            }
            catch (NotFoundException l_nfe)
            {
                log.debug("�e�ЃJ�X�^�}�C�Y�f�[�^���Ȃ�����");
                return false;
            }
        }

        log.debug("�e�ЃJ�X�^�}�C�Y�f�[�^������");
        return true;
    }

    /**
     * �J�X�^�}�C�Y�l�̊l��
     */
    private String[] getCustomizedValues(String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountDiv, String l_strCheckItem) throws WEB3BaseException
    {
        try
        {
            WEB3AccOpenItemAttribute l_itemAttribute = new WEB3AccOpenItemAttribute(
                l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strCheckItem);
            
            Object l_objCheckItemValue = expAccountOpenParams.getColumn(l_strCheckItem);

            if (l_objCheckItemValue != null)
            {
                Double l_rangeFrom = l_itemAttribute.getRangeFrom(l_objCheckItemValue.toString());
                Double l_rangeTo =  l_itemAttribute.getRangeTo(l_objCheckItemValue.toString());
                
                return new String[] { l_rangeFrom == null ? null : WEB3StringTypeUtility.formatNumber(l_rangeFrom.doubleValue()),
                    l_rangeTo == null ? null : WEB3StringTypeUtility.formatNumber(l_rangeTo.doubleValue()) };
            }
        }
        catch (NotFoundException l_ex)
        {
            if (l_strBranchCode.equals("000")) {
                return new String[] { null, null };
            } else {
                return getCustomizedValues(l_strInstitutionCode, "000",
                        l_strAccountDiv, l_strCheckItem);
            }
        }
        
        return new String[] { null, null };
    }

    /**
     * ��J�X�^�}�C�Y�l�̊l��
     */
    private String[] getFinancialValues(String l_strExperienceDivEquity)
    {
        String l_strFrom = null;
        String l_strTo = null;
        
        if (WEB3MoneyAmountDivDef.DEFAULT.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.NO_ANSWERD;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.NO_ANSWERD;
        }
        else if (WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.NO_ANSWERD;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.THREE_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.THREE_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.FIVE_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.FIVE_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.TEN_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.TEN_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.THIRTY_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.THIRTY_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.FIFTY_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.FIFTY_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.ONE_HUNDRED_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.ONE_HUNDRED_MILLION_YUAN;
            l_strTo = null;
        }
        
        return new String[]{l_strFrom, l_strTo};
    }
    
    /**
     * WEB3�f�t�H���g�`�F�b�N�̃`�F�b�N�Ώۍ��ڂ̗L���l�`�F�b�N 
     */
    private boolean isValidCodeValue(String l_strCheckItem, Object l_checkItemValue)
    throws WEB3BaseException
    {
        ValidCodeValueSets l_validcodeValueSets = new ValidCodeValueSets();
        Iterator l_checkItems = l_validcodeValueSets.validCodeValueMap.keySet().iterator();
        String l_strCheckItemsHolder = null;

        if (l_checkItemValue == null)
        {
            return true;
        }
        
        while (l_checkItems.hasNext())
        {
            l_strCheckItemsHolder = (String) l_checkItems.next();
            if (l_strCheckItemsHolder.equals(l_strCheckItem))
            {
                if (!(l_checkItemValue instanceof String))
                {
                    //�s���m�̗L���l,��String�ތ^
                    return false;
                }
                else
                {
                    String[] l_strValidCodeValues = (String[]) l_validcodeValueSets.validCodeValueMap.get(l_strCheckItemsHolder);
                    int l_intLength = l_strValidCodeValues.length;
                    
                    for (int i = 0; i < l_intLength; i++)
                    {
                        if (l_strValidCodeValues[i].equals(l_checkItemValue))
                        {
                            return true;
                        }
                    }
                    
                    //�s���m�̗L���l
                    return false;
                }
            }
        }
        
        return false;
    }
    
    /**
     * �`�F�b�N�Ώۍ���(�L����������)<BR>
     */
    private class ValidCodeValueSets
    {
        public Map validCodeValueMap;
        
        public ValidCodeValueSets() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "ValidCodeValueSets()";
            log.entering(STR_METHOD_NAME);

            validCodeValueMap = new HashMap();
            validCodeValueMap.put("sex", new String[] { WEB3SexDef.CORPORATE, WEB3SexDef.MALE,
                WEB3SexDef.UNKNOW, WEB3SexDef.WOMAN });

            List l_lisResults = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults = l_queryProcessor.doFindAllQuery(EraRow.TYPE);
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

            String[] l_strEras = null;
            if (l_lisResults.isEmpty())
            {
                l_strEras = new String[]{};
            }

            Set l_resultSet = new HashSet();
            for (int i = 0; i < l_lisResults.size(); i++)
            {
                EraRow l_eraRow = (EraRow)l_lisResults.get(i);
                l_resultSet.add(l_eraRow.getJapaneseEraDiv() + "");
            }

            if (l_resultSet.size() != 0)
            {
                l_strEras = new String[l_resultSet.size()];
                l_resultSet.toArray(l_strEras);
            }

            validCodeValueMap.put("era_born", l_strEras);
            validCodeValueMap.put("occupation_div", new String[] {
                WEB3OccupationDivDef.AGRICULTURE_FORESTRY_FISHERY, WEB3OccupationDivDef.DOCTOR,
                WEB3OccupationDivDef.INDEPENDENT_BUSINESS,
                WEB3OccupationDivDef.JOBLESSNESS_HOUSEWIFE,
                WEB3OccupationDivDef.JOBLESSNESS_MANDATORY_RETIREMENT,
                WEB3OccupationDivDef.JOBLESSNESS_OTHER, WEB3OccupationDivDef.JOBLESSNESS_STUDENT,
                WEB3OccupationDivDef.LAWYER_ACCOUNTANT,
                WEB3OccupationDivDef.LISTED_COMPANY_MANAGER,
                WEB3OccupationDivDef.LISTED_COMPANY_OFFICER,
                WEB3OccupationDivDef.LISTED_COMPANY_STAFF, WEB3OccupationDivDef.OTHER,
                WEB3OccupationDivDef.OTHER_SALARY_EARNER });
            validCodeValueMap.put("family_relationship", new String[] {
                WEB3FamilyRelationshipDef.CHILD, WEB3FamilyRelationshipDef.IDENTICAL_PERSON,
                WEB3FamilyRelationshipDef.MATE, WEB3FamilyRelationshipDef.OTHER });
            validCodeValueMap.put("householder_occupation_div", new String[] {
                WEB3OccupationDivDef.AGRICULTURE_FORESTRY_FISHERY, WEB3OccupationDivDef.DOCTOR,
                WEB3OccupationDivDef.INDEPENDENT_BUSINESS,
                WEB3OccupationDivDef.JOBLESSNESS_HOUSEWIFE,
                WEB3OccupationDivDef.JOBLESSNESS_MANDATORY_RETIREMENT,
                WEB3OccupationDivDef.JOBLESSNESS_OTHER, WEB3OccupationDivDef.JOBLESSNESS_STUDENT,
                WEB3OccupationDivDef.LAWYER_ACCOUNTANT,
                WEB3OccupationDivDef.LISTED_COMPANY_MANAGER,
                WEB3OccupationDivDef.LISTED_COMPANY_OFFICER,
                WEB3OccupationDivDef.LISTED_COMPANY_STAFF, WEB3OccupationDivDef.OTHER,
                WEB3OccupationDivDef.OTHER_SALARY_EARNER });
            validCodeValueMap.put("resident", new String[] { WEB3ResidentDef.NON_RESIDENT,
                WEB3ResidentDef.RESIDENT, WEB3ResidentDef.SPE_NON_RESIDENT });
            validCodeValueMap.put("transfer_div", new String[] { WEB3TransferDivDef.BANK_TRANSFER,
                WEB3TransferDivDef.POSTAL_TRANSFER });
            validCodeValueMap.put("fin_save_div", new String[] {
                WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE, WEB3FinSaveDivDef.GENERAL_FIN_SAVE,
                WEB3FinSaveDivDef.OTHER, WEB3FinSaveDivDef.SAVING_FIN_SAVE });
            validCodeValueMap.put("trans_commission", new String[] { WEB3TransCommissionDef.OTHER,
                WEB3TransCommissionDef.OTHER_TRADE, WEB3TransCommissionDef.SAME_TRADE,
                WEB3TransCommissionDef.SAME_TRADE_STORE_CHARGE,
                WEB3TransCommissionDef.SAME_TRADE_STORE_FREE });
            validCodeValueMap.put("experience_div_equity", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_margin", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_bond", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_wt", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_fund_sk", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_fund_bd", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_fo", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_f_equity", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_etc", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("invest_purpose_div", new String[] {
                WEB3InvestPurposeDivDef.LONG_TERM_PURPOSE,
                WEB3InvestPurposeDivDef.MEDIUM_TERM_PURPOSE,
                WEB3InvestPurposeDivDef.SHORT_TERM_PURPOSE });
            validCodeValueMap.put("appli_motivat_div", new String[] {
                WEB3AppliMotivatDivDef.HOMEPAGE, WEB3AppliMotivatDivDef.MAGAZINE,
                WEB3AppliMotivatDivDef.NEWSPAPER, WEB3AppliMotivatDivDef.OTHER,
                WEB3AppliMotivatDivDef.SEMINAR, WEB3AppliMotivatDivDef.TV });
            validCodeValueMap.put("annual_income_div", new String[] {
                WEB3MoneyAmountDivDef.DEFAULT,
                WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION,
                WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION,
                WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION,
                WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION,
                WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION });
            validCodeValueMap.put("asset_value_div", new String[] { WEB3MoneyAmountDivDef.DEFAULT,
                WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION,
                WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION,
                WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION,
                WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION,
                WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION });
            validCodeValueMap.put("fund_budget_amount_div", new String[] {
                WEB3MoneyAmountDivDef.DEFAULT,
                WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION,
                WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION,
                WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION,
                WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION,
                WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION });
            validCodeValueMap.put("fund_budget_div", new String[] {
                WEB3FundBudgetDivDef.BORROWED_FUNDS, WEB3FundBudgetDivDef.OTHER,
                WEB3FundBudgetDivDef.RETIREMENT_ALLOWANCE, WEB3FundBudgetDivDef.SURPLUS_FUNDS });
            validCodeValueMap.put("id_confirm_doc_div", new String[] {
                WEB3IdConfirmDocDivDef.ALIEN_REGISTRATION_CERTIFICATE,
                WEB3IdConfirmDocDivDef.DRIVING_LICENSE,
                WEB3IdConfirmDocDivDef.HEALTH_INSURANCE_CARD, WEB3IdConfirmDocDivDef.OTHER,
                WEB3IdConfirmDocDivDef.RESIDENT_CARD, WEB3IdConfirmDocDivDef.SEAL_CERTIFICATE });
            validCodeValueMap.put("special_acc", new String[] { WEB3TaxTypeDivDef.NORMAL,
                WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE, WEB3TaxTypeDivDef.SPECIAL_SOURCE });
            validCodeValueMap.put("special_acc_margin", new String[] { WEB3TaxTypeDivDef.NORMAL,
                WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE, WEB3TaxTypeDivDef.SPECIAL_SOURCE });
            
            //�c�a���C�A�E�g No.026
            //�i���O���j�a���敪
            //1�F���ʗa���@@2�F�����a���@@3�F���̑��@@4�F���~�a��
            validCodeValueMap.put("stk_fin_save_div", new String[] {
                    WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE, WEB3FinSaveDivDef.GENERAL_FIN_SAVE,
                    WEB3FinSaveDivDef.OTHER, WEB3FinSaveDivDef.SAVING_FIN_SAVE });   
            //�c�a���C�A�E�g No.029
            validCodeValueMap.put("foreign_save_div", new String[] {
                    WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE, WEB3FinSaveDivDef.GENERAL_FIN_SAVE});  

            validCodeValueMap.put("print_flag", new String[] {
                WEB3PrintFlagDef.DEFAULT,
                WEB3PrintFlagDef.ENABLE_PRINT,
                WEB3PrintFlagDef.PRINT_COMPLETE});
        }
    }
    
    /**
     * getDefault���ڃ}�X�^()�Ɏw�肷�����<BR>
     */
    private class ItemCheckMethod
    {
        /**
         * ��<BR>
         */
        public String columnName;
        
        /**
         * �K�{���ڃt���O<BR>
         * �yNull�z���ڂł����BooleanEnum.FALSE<BR>
         * �yNotNull�z���ڂł���΁ABooleanEnum.TRUE���w�肷��B<BR>
         */
        public BooleanEnum necessaryItemFlag;
        
        /**
         * ���ڍő咷<BR>
         * �ySIZE�z<BR>
         */
        public int size;
        
        /**
         * �y���ڃ`�F�b�N�����iWEB3�f�t�H���g�j�z <BR>
         */
        public String itemCheckMode;
        
        /**
         * �J�X�^�}�C�Y <BR>
         */
        public boolean customizing;
    }
    
    /**
     * �`�F�b�N�Ώۍ���<BR>
     */
    private class MethodSets
    {
        public Map methodMap;
        
        /**
         * �`�F�b�N�Ώۍ���<BR>
         * �R���X�g���N�^�B<BR>
         */
        public MethodSets()
        {
            methodMap = new HashMap();
            ItemCheckMethod l_itemCheckMethod1 = new ItemCheckMethod();
            l_itemCheckMethod1.columnName = "���X�R�[�h";
            l_itemCheckMethod1.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod1.size = 3;
            l_itemCheckMethod1.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod1.customizing = true;
            methodMap.put("branch_code", l_itemCheckMethod1);
            ItemCheckMethod l_itemCheckMethod2 = new ItemCheckMethod();
            l_itemCheckMethod2.columnName = "�����R�[�h";
            l_itemCheckMethod2.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod2.size = 7;
            l_itemCheckMethod2.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod2.customizing = true;
            methodMap.put("account_code", l_itemCheckMethod2);
            ItemCheckMethod l_itemCheckMethod3 = new ItemCheckMethod();
            l_itemCheckMethod3.columnName = "���҃R�[�h�iSONAR�j";
            l_itemCheckMethod3.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod3.size = 5;
            l_itemCheckMethod3.itemCheckMode = null;
            l_itemCheckMethod3.customizing = true;
            methodMap.put("sonar_trader_code", l_itemCheckMethod3);
            ItemCheckMethod l_itemCheckMethod4 = new ItemCheckMethod();
            l_itemCheckMethod4.columnName = "���������t���O";
            l_itemCheckMethod4.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod4.size = 1;
            l_itemCheckMethod4.itemCheckMode = null;
            l_itemCheckMethod4.customizing = true;
            methodMap.put("ex_account_flag", l_itemCheckMethod4);
            ItemCheckMethod l_itemCheckMethod5 = new ItemCheckMethod();
            l_itemCheckMethod5.columnName = "�����������X��";
            l_itemCheckMethod5.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod5.size = 32;
            l_itemCheckMethod5.itemCheckMode = null;
            l_itemCheckMethod5.customizing = true;
            methodMap.put("ex_branch_name", l_itemCheckMethod5);
            ItemCheckMethod l_itemCheckMethod6 = new ItemCheckMethod();
            l_itemCheckMethod6.columnName = "���������R�[�h";
            l_itemCheckMethod6.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod6.size = 7;
            l_itemCheckMethod6.itemCheckMode = null;
            l_itemCheckMethod6.customizing = true;
            methodMap.put("ex_account_code", l_itemCheckMethod6);
            ItemCheckMethod l_itemCheckMethod7 = new ItemCheckMethod();
            l_itemCheckMethod7.columnName = "�����敪";
            l_itemCheckMethod7.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod7.size = 1;
            l_itemCheckMethod7.itemCheckMode = null;
            l_itemCheckMethod7.customizing = true;
            methodMap.put("account_div", l_itemCheckMethod7);
            ItemCheckMethod l_itemCheckMethod8 = new ItemCheckMethod();
            l_itemCheckMethod8.columnName = "���͋敪";
            l_itemCheckMethod8.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod8.size = 1;
            l_itemCheckMethod8.itemCheckMode = null;
            l_itemCheckMethod8.customizing = true;
            methodMap.put("order_div", l_itemCheckMethod8);
            ItemCheckMethod l_itemCheckMethod9 = new ItemCheckMethod();
            l_itemCheckMethod9.columnName = "������������";
            l_itemCheckMethod9.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod9.size = 0;
            l_itemCheckMethod9.itemCheckMode = null;
            l_itemCheckMethod9.customizing = true;
            methodMap.put("infomation_claim_datetime", l_itemCheckMethod9);
            ItemCheckMethod l_itemCheckMethod10 = new ItemCheckMethod();
            l_itemCheckMethod10.columnName = "�����o�^��";
            l_itemCheckMethod10.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod10.size = 0;
            l_itemCheckMethod10.itemCheckMode = null;
            l_itemCheckMethod10.customizing = true;
            methodMap.put("account_open_date", l_itemCheckMethod10);
            ItemCheckMethod l_itemCheckMethod11 = new ItemCheckMethod();
            l_itemCheckMethod11.columnName = "�����p�X���[�h";
            l_itemCheckMethod11.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod11.size = 32;
            l_itemCheckMethod11.itemCheckMode = null;
            l_itemCheckMethod11.customizing = true;
            methodMap.put("initial_password", l_itemCheckMethod11);
            ItemCheckMethod l_itemCheckMethod12 = new ItemCheckMethod();
            l_itemCheckMethod12.columnName = "�ڋq���i�����j";
            l_itemCheckMethod12.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod12.size = 40;
            l_itemCheckMethod12.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod12.customizing = true;
            methodMap.put("family_name", l_itemCheckMethod12);
            ItemCheckMethod l_itemCheckMethod13 = new ItemCheckMethod();
            l_itemCheckMethod13.columnName = "�ڋq���i�����j";
            l_itemCheckMethod13.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod13.size = 40;
            l_itemCheckMethod13.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod13.customizing = true;
            methodMap.put("given_name", l_itemCheckMethod13);
            ItemCheckMethod l_itemCheckMethod14 = new ItemCheckMethod();
            l_itemCheckMethod14.columnName = "�ڋq���i�J�i�j";
            l_itemCheckMethod14.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod14.size = 40;
            l_itemCheckMethod14.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod14.customizing = true;
            methodMap.put("family_name_alt1", l_itemCheckMethod14);
            ItemCheckMethod l_itemCheckMethod15 = new ItemCheckMethod();
            l_itemCheckMethod15.columnName = "�ڋq���i�J�i�j";
            l_itemCheckMethod15.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod15.size = 40;
            l_itemCheckMethod15.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod15.customizing = true;
            methodMap.put("given_name_alt1", l_itemCheckMethod15);
            ItemCheckMethod l_itemCheckMethod16 = new ItemCheckMethod();
            l_itemCheckMethod16.columnName = "����";
            l_itemCheckMethod16.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod16.size = 1;
            l_itemCheckMethod16.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod16.customizing = true;
            methodMap.put("sex", l_itemCheckMethod16);
            ItemCheckMethod l_itemCheckMethod17 = new ItemCheckMethod();
            l_itemCheckMethod17.columnName = "���N�����N��";
            l_itemCheckMethod17.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod17.size = 1;
            l_itemCheckMethod17.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod17.customizing = true;
            methodMap.put("era_born", l_itemCheckMethod17);
            ItemCheckMethod l_itemCheckMethod18 = new ItemCheckMethod();
            l_itemCheckMethod18.columnName = "email�A�h���X";
            l_itemCheckMethod18.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod18.size = 100;
            l_itemCheckMethod18.itemCheckMode = WEB3ItemCheckModeDef.MAIL_ADDRESS;
            l_itemCheckMethod18.customizing = true;
            methodMap.put("email_address", l_itemCheckMethod18);
            ItemCheckMethod l_itemCheckMethod19 = new ItemCheckMethod();
            l_itemCheckMethod19.columnName = "email�A�h���X�P";
            l_itemCheckMethod19.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod19.size = 100;
            l_itemCheckMethod19.itemCheckMode = WEB3ItemCheckModeDef.MAIL_ADDRESS;
            l_itemCheckMethod19.customizing = true;
            methodMap.put("email_address_alt1", l_itemCheckMethod19);
            ItemCheckMethod l_itemCheckMethod20 = new ItemCheckMethod();
            l_itemCheckMethod20.columnName = "�X�֔ԍ�";
            l_itemCheckMethod20.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod20.size = 7;
            l_itemCheckMethod20.itemCheckMode = WEB3ItemCheckModeDef.ZIP_CODE;
            l_itemCheckMethod20.customizing = true;
            methodMap.put("zip_code", l_itemCheckMethod20);
            ItemCheckMethod l_itemCheckMethod21 = new ItemCheckMethod();
            l_itemCheckMethod21.columnName = "�Z���P";
            l_itemCheckMethod21.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod21.size = 34;
            l_itemCheckMethod21.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod21.customizing = true;
            methodMap.put("address_line1", l_itemCheckMethod21);
            ItemCheckMethod l_itemCheckMethod22 = new ItemCheckMethod();
            l_itemCheckMethod22.columnName = "�Z���Q";
            l_itemCheckMethod22.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod22.size = 34;
            l_itemCheckMethod22.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod22.customizing = true;
            methodMap.put("address_line2", l_itemCheckMethod22);
            ItemCheckMethod l_itemCheckMethod23 = new ItemCheckMethod();
            l_itemCheckMethod23.columnName = "�Z���R";
            l_itemCheckMethod23.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod23.size = 34;
            l_itemCheckMethod23.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod23.customizing = true;
            methodMap.put("address_line3", l_itemCheckMethod23);
            ItemCheckMethod l_itemCheckMethod24 = new ItemCheckMethod();
            l_itemCheckMethod24.columnName = "�Z���P�i�J�i�j";
            l_itemCheckMethod24.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod24.size = 60;
            l_itemCheckMethod24.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod24.customizing = true;
            methodMap.put("address_line1_kana", l_itemCheckMethod24);
            ItemCheckMethod l_itemCheckMethod25 = new ItemCheckMethod();
            l_itemCheckMethod25.columnName = "�Z���Q�i�J�i�j";
            l_itemCheckMethod25.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod25.size = 60;
            l_itemCheckMethod25.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod25.customizing = true;
            methodMap.put("address_line2_kana", l_itemCheckMethod25);
            ItemCheckMethod l_itemCheckMethod26 = new ItemCheckMethod();
            l_itemCheckMethod26.columnName = "�Z���R�i�J�i�j";
            l_itemCheckMethod26.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod26.size = 60;
            l_itemCheckMethod26.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod26.customizing = true;
            methodMap.put("address_line3_kana", l_itemCheckMethod26);
            ItemCheckMethod l_itemCheckMethod27 = new ItemCheckMethod();
            l_itemCheckMethod27.columnName = "�d�b�ԍ�";
            l_itemCheckMethod27.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod27.size = 16;
            l_itemCheckMethod27.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod27.customizing = true;
            methodMap.put("telephone", l_itemCheckMethod27);
            ItemCheckMethod l_itemCheckMethod28 = new ItemCheckMethod();
            l_itemCheckMethod28.columnName = "�A����d�b�ԍ��i�g�сj";
            l_itemCheckMethod28.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod28.size = 16;
            l_itemCheckMethod28.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod28.customizing = true;
            methodMap.put("mobile", l_itemCheckMethod28);
            ItemCheckMethod l_itemCheckMethod29 = new ItemCheckMethod();
            l_itemCheckMethod29.columnName = "�e�`�w�ԍ�";
            l_itemCheckMethod29.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod29.size = 16;
            l_itemCheckMethod29.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod29.customizing = true;
            methodMap.put("fax", l_itemCheckMethod29);
            ItemCheckMethod l_itemCheckMethod30 = new ItemCheckMethod();
            l_itemCheckMethod30.columnName = "�E�Ƌ敪";
            l_itemCheckMethod30.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod30.size = 2;
            l_itemCheckMethod30.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod30.customizing = true;
            methodMap.put("occupation_div", l_itemCheckMethod30);
            ItemCheckMethod l_itemCheckMethod31 = new ItemCheckMethod();
            l_itemCheckMethod31.columnName = "�Ζ��於��";
            l_itemCheckMethod31.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod31.size = 50;
            l_itemCheckMethod31.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod31.customizing = true;
            methodMap.put("office", l_itemCheckMethod31);
            ItemCheckMethod l_itemCheckMethod32 = new ItemCheckMethod();
            l_itemCheckMethod32.columnName = "�Ζ���X�֔ԍ�";
            l_itemCheckMethod32.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod32.size = 7;
            l_itemCheckMethod32.itemCheckMode = WEB3ItemCheckModeDef.ZIP_CODE;
            l_itemCheckMethod32.customizing = true;
            methodMap.put("office_zip_code", l_itemCheckMethod32);
            ItemCheckMethod l_itemCheckMethod33 = new ItemCheckMethod();
            l_itemCheckMethod33.columnName = "�Ζ���Z��";
            l_itemCheckMethod33.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod33.size = 100;
            l_itemCheckMethod33.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod33.customizing = true;
            methodMap.put("office_address", l_itemCheckMethod33);
            ItemCheckMethod l_itemCheckMethod34 = new ItemCheckMethod();
            l_itemCheckMethod34.columnName = "�Ζ���d�b�ԍ�";
            l_itemCheckMethod34.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod34.size = 16;
            l_itemCheckMethod34.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod34.customizing = true;
            methodMap.put("office_telephone", l_itemCheckMethod34);
            ItemCheckMethod l_itemCheckMethod35 = new ItemCheckMethod();
            l_itemCheckMethod35.columnName = "�Ζ���FAX�ԍ�";
            l_itemCheckMethod35.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod35.size = 16;
            l_itemCheckMethod35.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod35.customizing = true;
            methodMap.put("office_fax", l_itemCheckMethod35);
            ItemCheckMethod l_itemCheckMethod36 = new ItemCheckMethod();
            l_itemCheckMethod36.columnName = "��������";
            l_itemCheckMethod36.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod36.size = 50;
            l_itemCheckMethod36.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod36.customizing = true;
            methodMap.put("department", l_itemCheckMethod36);
            ItemCheckMethod l_itemCheckMethod37 = new ItemCheckMethod();
            l_itemCheckMethod37.columnName = "��E";
            l_itemCheckMethod37.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod37.size = 36;
            l_itemCheckMethod37.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod37.customizing = true;
            methodMap.put("post", l_itemCheckMethod37);
            ItemCheckMethod l_itemCheckMethod38 = new ItemCheckMethod();
            l_itemCheckMethod38.columnName = "�A����Z��";
            l_itemCheckMethod38.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod38.size = 100;
            l_itemCheckMethod38.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod38.customizing = true;
            methodMap.put("contact_address", l_itemCheckMethod38);
            ItemCheckMethod l_itemCheckMethod39 = new ItemCheckMethod();
            l_itemCheckMethod39.columnName = "�A����d�b�ԍ�";
            l_itemCheckMethod39.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod39.size = 16;
            l_itemCheckMethod39.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod39.customizing = true;
            methodMap.put("contact_telephone", l_itemCheckMethod39);
            ItemCheckMethod l_itemCheckMethod40 = new ItemCheckMethod();
            l_itemCheckMethod40.columnName = "�����敪";
            l_itemCheckMethod40.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod40.size = 1;
            l_itemCheckMethod40.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod40.customizing = true;
            methodMap.put("family_relationship", l_itemCheckMethod40);
            ItemCheckMethod l_itemCheckMethod41 = new ItemCheckMethod();
            l_itemCheckMethod41.columnName = "�����敪�i���̑��j";
            l_itemCheckMethod41.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod41.size = 40;
            l_itemCheckMethod41.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod41.customizing = true;
            methodMap.put("family_relationship_etc", l_itemCheckMethod41);
            ItemCheckMethod l_itemCheckMethod42 = new ItemCheckMethod();
            l_itemCheckMethod42.columnName = "���ю喼�i�����j";
            l_itemCheckMethod42.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod42.size = 40;
            l_itemCheckMethod42.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod42.customizing = true;
            methodMap.put("householder", l_itemCheckMethod42);
            ItemCheckMethod l_itemCheckMethod43 = new ItemCheckMethod();
            l_itemCheckMethod43.columnName = "���ю喼�i�J�i�j";
            l_itemCheckMethod43.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod43.size = 40;
            l_itemCheckMethod43.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod43.customizing = true;
            methodMap.put("householder_kana", l_itemCheckMethod43);
            ItemCheckMethod l_itemCheckMethod44 = new ItemCheckMethod();
            l_itemCheckMethod44.columnName = "���ю�E�Ƌ敪";
            l_itemCheckMethod44.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod44.size = 2;
            l_itemCheckMethod44.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod44.customizing = true;
            methodMap.put("householder_occupation_div", l_itemCheckMethod44);
            ItemCheckMethod l_itemCheckMethod45 = new ItemCheckMethod();
            l_itemCheckMethod45.columnName = "���ю�Ζ���";
            l_itemCheckMethod45.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod45.size = 50;
            l_itemCheckMethod45.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod45.customizing = true;
            methodMap.put("householder_office", l_itemCheckMethod45);
            ItemCheckMethod l_itemCheckMethod46 = new ItemCheckMethod();
            l_itemCheckMethod46.columnName = "���ю�Ζ���Z��";
            l_itemCheckMethod46.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod46.size = 100;
            l_itemCheckMethod46.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod46.customizing = true;
            methodMap.put("householder_office_address", l_itemCheckMethod46);
            ItemCheckMethod l_itemCheckMethod47 = new ItemCheckMethod();
            l_itemCheckMethod47.columnName = "���ю及������";
            l_itemCheckMethod47.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod47.size = 50;
            l_itemCheckMethod47.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod47.customizing = true;
            methodMap.put("householder_department", l_itemCheckMethod47);
            ItemCheckMethod l_itemCheckMethod48 = new ItemCheckMethod();
            l_itemCheckMethod48.columnName = "���ю�Ζ���d�b�ԍ�";
            l_itemCheckMethod48.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod48.size = 16;
            l_itemCheckMethod48.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod48.customizing = true;
            methodMap.put("householder_office_tel", l_itemCheckMethod48);
            ItemCheckMethod l_itemCheckMethod49 = new ItemCheckMethod();
            l_itemCheckMethod49.columnName = "���ю�Ζ���FAX�ԍ�";
            l_itemCheckMethod49.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod49.size = 16;
            l_itemCheckMethod49.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod49.customizing = true;
            methodMap.put("householder_office_fax", l_itemCheckMethod49);
            ItemCheckMethod l_itemCheckMethod50 = new ItemCheckMethod();
            l_itemCheckMethod50.columnName = "���ю��E";
            l_itemCheckMethod50.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod50.size = 36;
            l_itemCheckMethod50.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod50.customizing = true;
            methodMap.put("householder_post", l_itemCheckMethod50);
            ItemCheckMethod l_itemCheckMethod51 = new ItemCheckMethod();
            l_itemCheckMethod51.columnName = "���Z�^�񋏏Z�敪";
            l_itemCheckMethod51.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod51.size = 1;
            l_itemCheckMethod51.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod51.customizing = true;
            methodMap.put("resident", l_itemCheckMethod51);
            ItemCheckMethod l_itemCheckMethod52 = new ItemCheckMethod();
            l_itemCheckMethod52.columnName = "�U�֋敪";
            l_itemCheckMethod52.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod52.size = 1;
            l_itemCheckMethod52.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod52.customizing = true;
            methodMap.put("transfer_div", l_itemCheckMethod52);
            ItemCheckMethod l_itemCheckMethod53 = new ItemCheckMethod();
            l_itemCheckMethod53.columnName = "��s�R�[�h";
            l_itemCheckMethod53.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod53.size = 4;
            l_itemCheckMethod53.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod53.customizing = true;
            methodMap.put("fin_institution_code", l_itemCheckMethod53);
            ItemCheckMethod l_itemCheckMethod54 = new ItemCheckMethod();
            l_itemCheckMethod54.columnName = "��s��";
            l_itemCheckMethod54.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod54.size = 32;
            l_itemCheckMethod54.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod54.customizing = true;
            methodMap.put("fin_institution_name", l_itemCheckMethod54);
            ItemCheckMethod l_itemCheckMethod55 = new ItemCheckMethod();
            l_itemCheckMethod55.columnName = "�x�X�R�[�h";
            l_itemCheckMethod55.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod55.size = 5;
            l_itemCheckMethod55.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod55.customizing = true;
            methodMap.put("fin_branch_code", l_itemCheckMethod55);
            ItemCheckMethod l_itemCheckMethod56 = new ItemCheckMethod();
            l_itemCheckMethod56.columnName = "�x�X��";
            l_itemCheckMethod56.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod56.size = 32;
            l_itemCheckMethod56.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod56.customizing = true;
            methodMap.put("fin_branch_name", l_itemCheckMethod56);
            ItemCheckMethod l_itemCheckMethod57 = new ItemCheckMethod();
            l_itemCheckMethod57.columnName = "�a���敪";
            l_itemCheckMethod57.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod57.size = 1;
            l_itemCheckMethod57.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod57.customizing = true;
            methodMap.put("fin_save_div", l_itemCheckMethod57);
            ItemCheckMethod l_itemCheckMethod58 = new ItemCheckMethod();
            l_itemCheckMethod58.columnName = "�����ԍ�";
            l_itemCheckMethod58.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod58.size = 8;
            l_itemCheckMethod58.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod58.customizing = true;
            methodMap.put("fin_account_no", l_itemCheckMethod58);
            ItemCheckMethod l_itemCheckMethod59 = new ItemCheckMethod();
            l_itemCheckMethod59.columnName = "�ʒ��L��";
            l_itemCheckMethod59.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod59.size = 5;
            l_itemCheckMethod59.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod59.customizing = true;
            methodMap.put("postal_save_code", l_itemCheckMethod59);
            ItemCheckMethod l_itemCheckMethod60 = new ItemCheckMethod();
            l_itemCheckMethod60.columnName = "�ʒ��ԍ�";
            l_itemCheckMethod60.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod60.size = 8;
            l_itemCheckMethod60.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod60.customizing = true;
            methodMap.put("postal_save_no", l_itemCheckMethod60);
            ItemCheckMethod l_itemCheckMethod61 = new ItemCheckMethod();
            l_itemCheckMethod61.columnName = "�������`�l";
            l_itemCheckMethod61.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod61.size = 60;
            l_itemCheckMethod61.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod61.customizing = true;
            methodMap.put("fin_account_name", l_itemCheckMethod61);
            ItemCheckMethod l_itemCheckMethod62 = new ItemCheckMethod();
            l_itemCheckMethod62.columnName = "�U�֎萔���敪";
            l_itemCheckMethod62.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod62.size = 1;
            l_itemCheckMethod62.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod62.customizing = true;
            methodMap.put("trans_commission", l_itemCheckMethod62);
            ItemCheckMethod l_itemCheckMethod63 = new ItemCheckMethod();
            l_itemCheckMethod63.columnName = "��������";
            l_itemCheckMethod63.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod63.size = 1;
            l_itemCheckMethod63.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod63.customizing = true;
            methodMap.put("experience_div_equity", l_itemCheckMethod63);
            ItemCheckMethod l_itemCheckMethod64 = new ItemCheckMethod();
            l_itemCheckMethod64.columnName = "�M�p���";
            l_itemCheckMethod64.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod64.size = 1;
            l_itemCheckMethod64.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod64.customizing = true;
            methodMap.put("experience_div_margin", l_itemCheckMethod64);
            ItemCheckMethod l_itemCheckMethod65 = new ItemCheckMethod();
            l_itemCheckMethod65.columnName = "��";
            l_itemCheckMethod65.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod65.size = 1;
            l_itemCheckMethod65.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod65.customizing = true;
            methodMap.put("experience_div_bond", l_itemCheckMethod65);
            ItemCheckMethod l_itemCheckMethod66 = new ItemCheckMethod();
            l_itemCheckMethod66.columnName = "�]���Ѝ�";
            l_itemCheckMethod66.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod66.size = 1;
            l_itemCheckMethod66.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod66.customizing = true;
            methodMap.put("experience_div_wt", l_itemCheckMethod66);
            ItemCheckMethod l_itemCheckMethod67 = new ItemCheckMethod();
            l_itemCheckMethod67.columnName = "�����M���i�����j";
            l_itemCheckMethod67.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod67.size = 1;
            l_itemCheckMethod67.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod67.customizing = true;
            methodMap.put("experience_div_fund_sk", l_itemCheckMethod67);
            ItemCheckMethod l_itemCheckMethod68 = new ItemCheckMethod();
            l_itemCheckMethod68.columnName = "�����M���i���Ѝj";
            l_itemCheckMethod68.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod68.size = 1;
            l_itemCheckMethod68.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod68.customizing = true;
            methodMap.put("experience_div_fund_bd", l_itemCheckMethod68);
            ItemCheckMethod l_itemCheckMethod69 = new ItemCheckMethod();
            l_itemCheckMethod69.columnName = "�敨�E�I�v�V����";
            l_itemCheckMethod69.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod69.size = 1;
            l_itemCheckMethod69.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod69.customizing = true;
            methodMap.put("experience_div_fo", l_itemCheckMethod69);
            ItemCheckMethod l_itemCheckMethod70 = new ItemCheckMethod();
            l_itemCheckMethod70.columnName = "�O���،�";
            l_itemCheckMethod70.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod70.size = 1;
            l_itemCheckMethod70.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod70.customizing = true;
            methodMap.put("experience_div_f_equity", l_itemCheckMethod70);
            ItemCheckMethod l_itemCheckMethod71 = new ItemCheckMethod();
            l_itemCheckMethod71.columnName = "���̑�";
            l_itemCheckMethod71.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod71.size = 1;
            l_itemCheckMethod71.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod71.customizing = true;
            methodMap.put("experience_div_etc", l_itemCheckMethod71);
            ItemCheckMethod l_itemCheckMethod72 = new ItemCheckMethod();
            l_itemCheckMethod72.columnName = "���������t���O";
            l_itemCheckMethod72.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod72.size = 1;
            l_itemCheckMethod72.itemCheckMode = null;
            l_itemCheckMethod72.customizing = true;
            methodMap.put("experience_flag_equity", l_itemCheckMethod72);
            ItemCheckMethod l_itemCheckMethod73 = new ItemCheckMethod();
            l_itemCheckMethod73.columnName = "�M�p����t���O";
            l_itemCheckMethod73.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod73.size = 1;
            l_itemCheckMethod73.itemCheckMode = null;
            l_itemCheckMethod73.customizing = true;
            methodMap.put("experience_flag_margin", l_itemCheckMethod73);
            ItemCheckMethod l_itemCheckMethod74 = new ItemCheckMethod();
            l_itemCheckMethod74.columnName = "���t���O";
            l_itemCheckMethod74.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod74.size = 1;
            l_itemCheckMethod74.itemCheckMode = null;
            l_itemCheckMethod74.customizing = true;
            methodMap.put("experience_flag_bond", l_itemCheckMethod74);
            ItemCheckMethod l_itemCheckMethod75 = new ItemCheckMethod();
            l_itemCheckMethod75.columnName = "�]���Ѝt���O";
            l_itemCheckMethod75.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod75.size = 1;
            l_itemCheckMethod75.itemCheckMode = null;
            l_itemCheckMethod75.customizing = true;
            methodMap.put("experience_flag_wt", l_itemCheckMethod75);
            ItemCheckMethod l_itemCheckMethod76 = new ItemCheckMethod();
            l_itemCheckMethod76.columnName = "�����M���i�����j�t���O";
            l_itemCheckMethod76.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod76.size = 1;
            l_itemCheckMethod76.itemCheckMode = null;
            l_itemCheckMethod76.customizing = true;
            methodMap.put("experience_flag_fund_sk", l_itemCheckMethod76);
            ItemCheckMethod l_itemCheckMethod77 = new ItemCheckMethod();
            l_itemCheckMethod77.columnName = "�����M���i���Ѝj�t���O";
            l_itemCheckMethod77.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod77.size = 1;
            l_itemCheckMethod77.itemCheckMode = null;
            l_itemCheckMethod77.customizing = true;
            methodMap.put("experience_flag_fund_bd", l_itemCheckMethod77);
            ItemCheckMethod l_itemCheckMethod78 = new ItemCheckMethod();
            l_itemCheckMethod78.columnName = "�敨�E�I�v�V�����t���O";
            l_itemCheckMethod78.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod78.size = 1;
            l_itemCheckMethod78.itemCheckMode = null;
            l_itemCheckMethod78.customizing = true;
            methodMap.put("experience_flag_fo", l_itemCheckMethod78);
            ItemCheckMethod l_itemCheckMethod79 = new ItemCheckMethod();
            l_itemCheckMethod79.columnName = "�O���،��t���O";
            l_itemCheckMethod79.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod79.size = 1;
            l_itemCheckMethod79.itemCheckMode = null;
            l_itemCheckMethod79.customizing = true;
            methodMap.put("experience_flag_f_equity", l_itemCheckMethod79);
            ItemCheckMethod l_itemCheckMethod80 = new ItemCheckMethod();
            l_itemCheckMethod80.columnName = "���̑��t���O";
            l_itemCheckMethod80.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod80.size = 1;
            l_itemCheckMethod80.itemCheckMode = null;
            l_itemCheckMethod80.customizing = true;
            methodMap.put("experience_flag_etc", l_itemCheckMethod80);
            ItemCheckMethod l_itemCheckMethod81 = new ItemCheckMethod();
            l_itemCheckMethod81.columnName = "�o���N���i���j";
            l_itemCheckMethod81.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod81.size = 2;
            l_itemCheckMethod81.itemCheckMode = null;
            l_itemCheckMethod81.customizing = true;
            methodMap.put("experience_from", l_itemCheckMethod81);
            ItemCheckMethod l_itemCheckMethod82 = new ItemCheckMethod();
            l_itemCheckMethod82.columnName = "�o���N���i���j";
            l_itemCheckMethod82.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod82.size = 2;
            l_itemCheckMethod82.itemCheckMode = null;
            l_itemCheckMethod82.customizing = true;
            methodMap.put("experience_to", l_itemCheckMethod82);
            ItemCheckMethod l_itemCheckMethod83 = new ItemCheckMethod();
            l_itemCheckMethod83.columnName = "���������t���O";
            l_itemCheckMethod83.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod83.size = 1;
            l_itemCheckMethod83.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod83.customizing = true;
            methodMap.put("interest_flag_equity", l_itemCheckMethod83);
            ItemCheckMethod l_itemCheckMethod84 = new ItemCheckMethod();
            l_itemCheckMethod84.columnName = "�����~�j�����t���O";
            l_itemCheckMethod84.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod84.size = 1;
            l_itemCheckMethod84.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod84.customizing = true;
            methodMap.put("interest_flag_ministock", l_itemCheckMethod84);
            ItemCheckMethod l_itemCheckMethod85 = new ItemCheckMethod();
            l_itemCheckMethod85.columnName = "�M�p����t���O";
            l_itemCheckMethod85.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod85.size = 1;
            l_itemCheckMethod85.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod85.customizing = true;
            methodMap.put("interest_flag_margin", l_itemCheckMethod85);
            ItemCheckMethod l_itemCheckMethod86 = new ItemCheckMethod();
            l_itemCheckMethod86.columnName = "���t���O";
            l_itemCheckMethod86.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod86.size = 1;
            l_itemCheckMethod86.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod86.customizing = true;
            methodMap.put("interest_flag_bond", l_itemCheckMethod86);
            ItemCheckMethod l_itemCheckMethod87 = new ItemCheckMethod();
            l_itemCheckMethod87.columnName = "�����M���t���O";
            l_itemCheckMethod87.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod87.size = 1;
            l_itemCheckMethod87.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod87.customizing = true;
            methodMap.put("interest_flag_fund", l_itemCheckMethod87);
            ItemCheckMethod l_itemCheckMethod88 = new ItemCheckMethod();
            l_itemCheckMethod88.columnName = "�敨�E�I�v�V�����t���O";
            l_itemCheckMethod88.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod88.size = 1;
            l_itemCheckMethod88.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod88.customizing = true;
            methodMap.put("interest_flag_fo", l_itemCheckMethod88);
            ItemCheckMethod l_itemCheckMethod89 = new ItemCheckMethod();
            l_itemCheckMethod89.columnName = "�O���،��t���O";
            l_itemCheckMethod89.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod89.size = 1;
            l_itemCheckMethod89.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod89.customizing = true;
            methodMap.put("interest_flag_f_equity", l_itemCheckMethod89);
            ItemCheckMethod l_itemCheckMethod90 = new ItemCheckMethod();
            l_itemCheckMethod90.columnName = "���̑��t���O";
            l_itemCheckMethod90.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod90.size = 1;
            l_itemCheckMethod90.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod90.customizing = true;
            methodMap.put("interest_flag_etc", l_itemCheckMethod90);
            ItemCheckMethod l_itemCheckMethod91 = new ItemCheckMethod();
            l_itemCheckMethod91.columnName = "�����ړI�敪";
            l_itemCheckMethod91.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod91.size = 1;
            l_itemCheckMethod91.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod91.customizing = true;
            methodMap.put("invest_purpose_div", l_itemCheckMethod91);
            ItemCheckMethod l_itemCheckMethod92 = new ItemCheckMethod();
            l_itemCheckMethod92.columnName = "������@@�敪";
            l_itemCheckMethod92.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod92.size = 1;
            l_itemCheckMethod92.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod92.customizing = true;
            methodMap.put("appli_motivat_div", l_itemCheckMethod92);
            ItemCheckMethod l_itemCheckMethod93 = new ItemCheckMethod();
            l_itemCheckMethod93.columnName = "�N���敪";
            l_itemCheckMethod93.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod93.size = 1;
            l_itemCheckMethod93.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod93.customizing = true;
            methodMap.put("annual_income_div", l_itemCheckMethod93);
            ItemCheckMethod l_itemCheckMethod94 = new ItemCheckMethod();
            l_itemCheckMethod94.columnName = "�N���i���j";
            l_itemCheckMethod94.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod94.size = 6;
            l_itemCheckMethod94.itemCheckMode = null;
            l_itemCheckMethod94.customizing = true;
            methodMap.put("annual_income_from", l_itemCheckMethod94);
            ItemCheckMethod l_itemCheckMethod95 = new ItemCheckMethod();
            l_itemCheckMethod95.columnName = "�N���i���j";
            l_itemCheckMethod95.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod95.size = 6;
            l_itemCheckMethod95.itemCheckMode = null;
            l_itemCheckMethod95.customizing = true;
            methodMap.put("annual_income_to", l_itemCheckMethod95);
            ItemCheckMethod l_itemCheckMethod96 = new ItemCheckMethod();
            l_itemCheckMethod96.columnName = "���Z���Y�敪";
            l_itemCheckMethod96.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod96.size = 1;
            l_itemCheckMethod96.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod96.customizing = true;
            methodMap.put("asset_value_div", l_itemCheckMethod96);
            ItemCheckMethod l_itemCheckMethod97 = new ItemCheckMethod();
            l_itemCheckMethod97.columnName = "���Z���Y�i���j";
            l_itemCheckMethod97.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod97.size = 6;
            l_itemCheckMethod97.itemCheckMode = null;
            l_itemCheckMethod97.customizing = true;
            methodMap.put("asset_value_from", l_itemCheckMethod97);
            ItemCheckMethod l_itemCheckMethod98 = new ItemCheckMethod();
            l_itemCheckMethod98.columnName = "���Z���Y�i���j";
            l_itemCheckMethod98.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod98.size = 6;
            l_itemCheckMethod98.itemCheckMode = null;
            l_itemCheckMethod98.customizing = true;
            methodMap.put("asset_value_to", l_itemCheckMethod98);
            ItemCheckMethod l_itemCheckMethod99 = new ItemCheckMethod();
            l_itemCheckMethod99.columnName = "�^�p�\��z";
            l_itemCheckMethod99.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod99.size = 1;
            l_itemCheckMethod99.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod99.customizing = true;
            methodMap.put("fund_budget_amount_div", l_itemCheckMethod99);
            ItemCheckMethod l_itemCheckMethod100 = new ItemCheckMethod();
            l_itemCheckMethod100.columnName = "�����̐��i";
            l_itemCheckMethod100.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod100.size = 1;
            l_itemCheckMethod100.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod100.customizing = true;
            methodMap.put("fund_budget_div", l_itemCheckMethod100);
            ItemCheckMethod l_itemCheckMethod101 = new ItemCheckMethod();
            l_itemCheckMethod101.columnName = "�����̐��i�i���̑��j";
            l_itemCheckMethod101.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod101.size = 40;
            l_itemCheckMethod101.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod101.customizing = true;
            methodMap.put("fund_budget_etc", l_itemCheckMethod101);
            ItemCheckMethod l_itemCheckMethod102 = new ItemCheckMethod();
            l_itemCheckMethod102.columnName = "�����p�敪";
            l_itemCheckMethod102.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod102.size = 1;
            l_itemCheckMethod102.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod102.customizing = true;
            methodMap.put("id_confirm_flag", l_itemCheckMethod102);
            ItemCheckMethod l_itemCheckMethod103 = new ItemCheckMethod();
            l_itemCheckMethod103.columnName = "�{�l�m�F���ދ敪";
            l_itemCheckMethod103.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod103.size = 2;
            l_itemCheckMethod103.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod103.customizing = true;
            methodMap.put("id_confirm_doc_div", l_itemCheckMethod103);
            ItemCheckMethod l_itemCheckMethod104 = new ItemCheckMethod();
            l_itemCheckMethod104.columnName = "�{�l�m�F���ދ敪�i���̑��j";
            l_itemCheckMethod104.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod104.size = 40;
            l_itemCheckMethod104.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod104.customizing = true;
            methodMap.put("id_confirm_doc_etc", l_itemCheckMethod104);
            ItemCheckMethod l_itemCheckMethod105 = new ItemCheckMethod();
            l_itemCheckMethod105.columnName = "��������敪";
            l_itemCheckMethod105.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod105.size = 1;
            l_itemCheckMethod105.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod105.customizing = true;
            methodMap.put("special_acc", l_itemCheckMethod105);
            ItemCheckMethod l_itemCheckMethod106 = new ItemCheckMethod();
            l_itemCheckMethod106.columnName = "�M�p�����������敪";
            l_itemCheckMethod106.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod106.size = 1;
            l_itemCheckMethod106.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod106.customizing = true;
            methodMap.put("special_acc_margin", l_itemCheckMethod106);
            ItemCheckMethod l_itemCheckMethod107 = new ItemCheckMethod();
            l_itemCheckMethod107.columnName = "�����ғo�^�t���O";
            l_itemCheckMethod107.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod107.size = 1;
            l_itemCheckMethod107.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod107.customizing = true;
            methodMap.put("insider_flag", l_itemCheckMethod107);
            ItemCheckMethod l_itemCheckMethod108 = new ItemCheckMethod();
            l_itemCheckMethod108.columnName = "�����Җ�����";
            l_itemCheckMethod108.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod108.size = 50;
            l_itemCheckMethod108.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod108.customizing = true;
            methodMap.put("product_name", l_itemCheckMethod108);
            ItemCheckMethod l_itemCheckMethod109 = new ItemCheckMethod();
            l_itemCheckMethod109.columnName = "���t��X�֔ԍ�";
            l_itemCheckMethod109.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod109.size = 7;
            l_itemCheckMethod109.itemCheckMode = WEB3ItemCheckModeDef.ZIP_CODE;
            l_itemCheckMethod109.customizing = true;
            methodMap.put("send_zip_code", l_itemCheckMethod109);
            ItemCheckMethod l_itemCheckMethod110 = new ItemCheckMethod();
            l_itemCheckMethod110.columnName = "���t��Z���P";
            l_itemCheckMethod110.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod110.size = 34;
            l_itemCheckMethod110.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod110.customizing = true;
            methodMap.put("send_address_line1", l_itemCheckMethod110);
            ItemCheckMethod l_itemCheckMethod111 = new ItemCheckMethod();
            l_itemCheckMethod111.columnName = "���t��Z���Q";
            l_itemCheckMethod111.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod111.size = 34;
            l_itemCheckMethod111.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod111.customizing = true;
            methodMap.put("send_address_line2", l_itemCheckMethod111);
            ItemCheckMethod l_itemCheckMethod112 = new ItemCheckMethod();
            l_itemCheckMethod112.columnName = "���t��Z���R";
            l_itemCheckMethod112.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod112.size = 34;
            l_itemCheckMethod112.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod112.customizing = true;
            methodMap.put("send_address_line3", l_itemCheckMethod112);
            ItemCheckMethod l_itemCheckMethod113 = new ItemCheckMethod();
            l_itemCheckMethod113.columnName = "�e�Њg�����ځi�敪�P�j";
            l_itemCheckMethod113.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod113.size = 4;
            l_itemCheckMethod113.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod113.customizing = true;
            methodMap.put("ext_item_div1", l_itemCheckMethod113);
            ItemCheckMethod l_itemCheckMethod114 = new ItemCheckMethod();
            l_itemCheckMethod114.columnName = "�e�Њg�����ځi�敪�Q�j";
            l_itemCheckMethod114.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod114.size = 4;
            l_itemCheckMethod114.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod114.customizing = true;
            methodMap.put("ext_item_div2", l_itemCheckMethod114);
            ItemCheckMethod l_itemCheckMethod115 = new ItemCheckMethod();
            l_itemCheckMethod115.columnName = "�e�Њg�����ځi�敪�R�j";
            l_itemCheckMethod115.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod115.size = 4;
            l_itemCheckMethod115.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod115.customizing = true;
            methodMap.put("ext_item_div3", l_itemCheckMethod115);
            ItemCheckMethod l_itemCheckMethod116 = new ItemCheckMethod();
            l_itemCheckMethod116.columnName = "�e�Њg�����ځi�敪�S�j";
            l_itemCheckMethod116.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod116.size = 4;
            l_itemCheckMethod116.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod116.customizing = true;
            methodMap.put("ext_item_div4", l_itemCheckMethod116);
            ItemCheckMethod l_itemCheckMethod117 = new ItemCheckMethod();
            l_itemCheckMethod117.columnName = "�e�Њg�����ځi�敪�T�j";
            l_itemCheckMethod117.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod117.size = 4;
            l_itemCheckMethod117.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod117.customizing = true;
            methodMap.put("ext_item_div5", l_itemCheckMethod117);
            ItemCheckMethod l_itemCheckMethod118 = new ItemCheckMethod();
            l_itemCheckMethod118.columnName = "�e�Њg�����ځi�敪�U�j";
            l_itemCheckMethod118.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod118.size = 4;
            l_itemCheckMethod118.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod118.customizing = true;
            methodMap.put("ext_item_div6", l_itemCheckMethod118);
            ItemCheckMethod l_itemCheckMethod119 = new ItemCheckMethod();
            l_itemCheckMethod119.columnName = "�e�Њg�����ځi�敪�V�j";
            l_itemCheckMethod119.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod119.size = 4;
            l_itemCheckMethod119.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod119.customizing = true;
            methodMap.put("ext_item_div7", l_itemCheckMethod119);
            ItemCheckMethod l_itemCheckMethod120 = new ItemCheckMethod();
            l_itemCheckMethod120.columnName = "�e�Њg�����ځi�敪�W�j";
            l_itemCheckMethod120.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod120.size = 4;
            l_itemCheckMethod120.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod120.customizing = true;
            methodMap.put("ext_item_div8", l_itemCheckMethod120);
            ItemCheckMethod l_itemCheckMethod121 = new ItemCheckMethod();
            l_itemCheckMethod121.columnName = "�e�Њg�����ځi�敪�X�j";
            l_itemCheckMethod121.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod121.size = 4;
            l_itemCheckMethod121.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod121.customizing = true;
            methodMap.put("ext_item_div9", l_itemCheckMethod121);
            ItemCheckMethod l_itemCheckMethod122 = new ItemCheckMethod();
            l_itemCheckMethod122.columnName = "�e�Њg�����ځi�敪�P�O�j";
            l_itemCheckMethod122.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod122.size = 4;
            l_itemCheckMethod122.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod122.customizing = true;
            methodMap.put("ext_item_div10", l_itemCheckMethod122);
            ItemCheckMethod l_itemCheckMethod123 = new ItemCheckMethod();
            l_itemCheckMethod123.columnName = "�e�Њg�����ځi�t���O1�j";
            l_itemCheckMethod123.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod123.size = 1;
            l_itemCheckMethod123.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod123.customizing = true;
            methodMap.put("ext_item_flag1", l_itemCheckMethod123);
            ItemCheckMethod l_itemCheckMethod124 = new ItemCheckMethod();
            l_itemCheckMethod124.columnName = "�e�Њg�����ځi�t���O2�j";
            l_itemCheckMethod124.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod124.size = 1;
            l_itemCheckMethod124.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod124.customizing = true;
            methodMap.put("ext_item_flag2", l_itemCheckMethod124);
            ItemCheckMethod l_itemCheckMethod125 = new ItemCheckMethod();
            l_itemCheckMethod125.columnName = "�e�Њg�����ځi�t���O3�j";
            l_itemCheckMethod125.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod125.size = 1;
            l_itemCheckMethod125.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod125.customizing = true;
            methodMap.put("ext_item_flag3", l_itemCheckMethod125);
            ItemCheckMethod l_itemCheckMethod126 = new ItemCheckMethod();
            l_itemCheckMethod126.columnName = "�e�Њg�����ځi�t���O4�j";
            l_itemCheckMethod126.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod126.size = 1;
            l_itemCheckMethod126.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod126.customizing = true;
            methodMap.put("ext_item_flag4", l_itemCheckMethod126);
            ItemCheckMethod l_itemCheckMethod127 = new ItemCheckMethod();
            l_itemCheckMethod127.columnName = "�e�Њg�����ځi�t���O5�j";
            l_itemCheckMethod127.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod127.size = 1;
            l_itemCheckMethod127.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod127.customizing = true;
            methodMap.put("ext_item_flag5", l_itemCheckMethod127);
            ItemCheckMethod l_itemCheckMethod128 = new ItemCheckMethod();
            l_itemCheckMethod128.columnName = "�e�Њg�����ځi�t���O�U�j";
            l_itemCheckMethod128.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod128.size = 1;
            l_itemCheckMethod128.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod128.customizing = true;
            methodMap.put("ext_item_flag6", l_itemCheckMethod128);
            ItemCheckMethod l_itemCheckMethod129 = new ItemCheckMethod();
            l_itemCheckMethod129.columnName = "�e�Њg�����ځi�t���O�V�j";
            l_itemCheckMethod129.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod129.size = 1;
            l_itemCheckMethod129.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod129.customizing = true;
            methodMap.put("ext_item_flag7", l_itemCheckMethod129);
            ItemCheckMethod l_itemCheckMethod130 = new ItemCheckMethod();
            l_itemCheckMethod130.columnName = "�e�Њg�����ځi�t���O�W�j";
            l_itemCheckMethod130.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod130.size = 1;
            l_itemCheckMethod130.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod130.customizing = true;
            methodMap.put("ext_item_flag8", l_itemCheckMethod130);
            ItemCheckMethod l_itemCheckMethod131 = new ItemCheckMethod();
            l_itemCheckMethod131.columnName = "�e�Њg�����ځi�t���O�X�j";
            l_itemCheckMethod131.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod131.size = 1;
            l_itemCheckMethod131.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod131.customizing = true;
            methodMap.put("ext_item_flag9", l_itemCheckMethod131);
            ItemCheckMethod l_itemCheckMethod132 = new ItemCheckMethod();
            l_itemCheckMethod132.columnName = "�e�Њg�����ځi�t���O�P�O�j";
            l_itemCheckMethod132.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod132.size = 1;
            l_itemCheckMethod132.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod132.customizing = true;
            methodMap.put("ext_item_flag10", l_itemCheckMethod132);
            ItemCheckMethod l_itemCheckMethod133 = new ItemCheckMethod();
            l_itemCheckMethod133.columnName = "�e�Њg�����ځi�e�L�X�g�P�j";
            l_itemCheckMethod133.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod133.size = 50;
            l_itemCheckMethod133.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod133.customizing = true;
            methodMap.put("ext_item_text1", l_itemCheckMethod133);
            ItemCheckMethod l_itemCheckMethod134 = new ItemCheckMethod();
            l_itemCheckMethod134.columnName = "�e�Њg�����ځi�e�L�X�g�Q�j";
            l_itemCheckMethod134.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod134.size = 50;
            l_itemCheckMethod134.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod134.customizing = true;
            methodMap.put("ext_item_text2", l_itemCheckMethod134);
            ItemCheckMethod l_itemCheckMethod135 = new ItemCheckMethod();
            l_itemCheckMethod135.columnName = "�e�Њg�����ځi�e�L�X�g�R�j";
            l_itemCheckMethod135.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod135.size = 50;
            l_itemCheckMethod135.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod135.customizing = true;
            methodMap.put("ext_item_text3", l_itemCheckMethod135);
            ItemCheckMethod l_itemCheckMethod136 = new ItemCheckMethod();
            l_itemCheckMethod136.columnName = "�e�Њg�����ځi�e�L�X�g�S�j";
            l_itemCheckMethod136.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod136.size = 50;
            l_itemCheckMethod136.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod136.customizing = true;
            methodMap.put("ext_item_text4", l_itemCheckMethod136);
            ItemCheckMethod l_itemCheckMethod137 = new ItemCheckMethod();
            l_itemCheckMethod137.columnName = "�e�Њg�����ځi�e�L�X�g�T�j";
            l_itemCheckMethod137.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod137.size = 50;
            l_itemCheckMethod137.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod137.customizing = true;
            methodMap.put("ext_item_text5", l_itemCheckMethod137);
            ItemCheckMethod l_itemCheckMethod138 = new ItemCheckMethod();
            l_itemCheckMethod138.columnName = "�e�Њg�����ځi�e�L�X�g�U�j";
            l_itemCheckMethod138.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod138.size = 50;
            l_itemCheckMethod138.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod138.customizing = true;
            methodMap.put("ext_item_text6", l_itemCheckMethod138);
            ItemCheckMethod l_itemCheckMethod139 = new ItemCheckMethod();
            l_itemCheckMethod139.columnName = "�e�Њg�����ځi�e�L�X�g�V�j";
            l_itemCheckMethod139.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod139.size = 50;
            l_itemCheckMethod139.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod139.customizing = true;
            methodMap.put("ext_item_text7", l_itemCheckMethod139);
            ItemCheckMethod l_itemCheckMethod140 = new ItemCheckMethod();
            l_itemCheckMethod140.columnName = "�e�Њg�����ځi�e�L�X�g�W�j";
            l_itemCheckMethod140.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod140.size = 50;
            l_itemCheckMethod140.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod140.customizing = true;
            methodMap.put("ext_item_text8", l_itemCheckMethod140);
            ItemCheckMethod l_itemCheckMethod141 = new ItemCheckMethod();
            l_itemCheckMethod141.columnName = "�e�Њg�����ځi�e�L�X�g�X�j";
            l_itemCheckMethod141.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod141.size = 50;
            l_itemCheckMethod141.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod141.customizing = true;
            methodMap.put("ext_item_text9", l_itemCheckMethod141);
            ItemCheckMethod l_itemCheckMethod142 = new ItemCheckMethod();
            l_itemCheckMethod142.columnName = "�e�Њg�����ځi�e�L�X�g�P�O�j";
            l_itemCheckMethod142.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod142.size = 50;
            l_itemCheckMethod142.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod142.customizing = true;
            methodMap.put("ext_item_text10", l_itemCheckMethod142);
            //�c�a���C�A�E�g No.010
            ItemCheckMethod l_itemCheckMethod143 = new ItemCheckMethod();
            l_itemCheckMethod143.columnName = "���N����";
            l_itemCheckMethod143.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod143.size = 6;
            l_itemCheckMethod143.itemCheckMode = WEB3ItemCheckModeDef.AGE;
            l_itemCheckMethod143.customizing = true;
            methodMap.put("born_date", l_itemCheckMethod143);
            
            //�c�a���C�A�E�g No.026
            ItemCheckMethod l_itemCheckMethod144 = new ItemCheckMethod();
            l_itemCheckMethod144.columnName = "�ڋq�������̍쐬�敪";
            l_itemCheckMethod144.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod144.size = 1;
            l_itemCheckMethod144.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod144.customizing = true;
            methodMap.put("real_name_voucher_div", l_itemCheckMethod144);
            
            ItemCheckMethod l_itemCheckMethod145 = new ItemCheckMethod();
            l_itemCheckMethod145.columnName = "�ڋq�������̂P";
            l_itemCheckMethod145.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod145.size = 40;
            l_itemCheckMethod145.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod145.customizing = true;
            methodMap.put("real_name1", l_itemCheckMethod145);
            
            ItemCheckMethod l_itemCheckMethod146 = new ItemCheckMethod();
            l_itemCheckMethod146.columnName = "�ڋq�������̂Q";
            l_itemCheckMethod146.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod146.size = 40;
            l_itemCheckMethod146.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod146.customizing = true;
            methodMap.put("real_name2", l_itemCheckMethod146);
            
            ItemCheckMethod l_itemCheckMethod147 = new ItemCheckMethod();
            l_itemCheckMethod147.columnName = "�i�����ҁj�쐬�敪";
            l_itemCheckMethod147.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod147.size = 1;
            l_itemCheckMethod147.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod147.customizing = true;
            methodMap.put("insider_voucher_div", l_itemCheckMethod147);
            
            ItemCheckMethod l_itemCheckMethod148 = new ItemCheckMethod();
            l_itemCheckMethod148.columnName = "�i�����ҁj�����R�[�h";
            l_itemCheckMethod148.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod148.size = 5;
            l_itemCheckMethod148.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod148.customizing = true;
            methodMap.put("insider_product_code", l_itemCheckMethod148);
            
            ItemCheckMethod l_itemCheckMethod149 = new ItemCheckMethod();
            l_itemCheckMethod149.columnName = "�i�����ҁj�֌W�敪";
            l_itemCheckMethod149.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod149.size = 1;
            l_itemCheckMethod149.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod149.customizing = true;
            methodMap.put("insider_relation_div", l_itemCheckMethod149);
            
            ItemCheckMethod l_itemCheckMethod150 = new ItemCheckMethod();
            l_itemCheckMethod150.columnName = "�i�����ҁj������";
            l_itemCheckMethod150.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod150.size = 18;
            l_itemCheckMethod150.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod150.customizing = true;
            methodMap.put("insider_officer_name", l_itemCheckMethod150);
            
            ItemCheckMethod l_itemCheckMethod151 = new ItemCheckMethod();
            l_itemCheckMethod151.columnName = "�i�����ҁj��E���R�[�h";
            l_itemCheckMethod151.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod151.size = 2;
            l_itemCheckMethod151.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod151.customizing = true;
            methodMap.put("insider_post_code", l_itemCheckMethod151);
            
            ItemCheckMethod l_itemCheckMethod152 = new ItemCheckMethod();
            l_itemCheckMethod152.columnName = "�i�����ҁj��E��";
            l_itemCheckMethod152.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod152.size = 20;
            l_itemCheckMethod152.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod152.customizing = true;
            methodMap.put("insider_post_name", l_itemCheckMethod152);
            
            ItemCheckMethod l_itemCheckMethod153 = new ItemCheckMethod();
            l_itemCheckMethod153.columnName = "�i�f�o�j�쐬�敪";
            l_itemCheckMethod153.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod153.size = 1;
            l_itemCheckMethod153.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod153.customizing = true;
            methodMap.put("gp_voucher_div", l_itemCheckMethod153);
            
            ItemCheckMethod l_itemCheckMethod154 = new ItemCheckMethod();
            l_itemCheckMethod154.columnName = "�i�f�o�j�R�[�X";
            l_itemCheckMethod154.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod154.size = 1;
            l_itemCheckMethod154.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod154.customizing = true;
            methodMap.put("gp_course", l_itemCheckMethod154);
            
            ItemCheckMethod l_itemCheckMethod155 = new ItemCheckMethod();
            l_itemCheckMethod155.columnName = "�i�f�o�j�v����";
            l_itemCheckMethod155.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod155.size = 1;
            l_itemCheckMethod155.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod155.customizing = true;
            methodMap.put("gp_plan", l_itemCheckMethod155);
            
            ItemCheckMethod l_itemCheckMethod156 = new ItemCheckMethod();
            l_itemCheckMethod156.columnName = "�i�f�o�j�ڕW�z";
            l_itemCheckMethod156.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod156.size = 4;
            l_itemCheckMethod156.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod156.customizing = true;
            methodMap.put("gp_target_figure", l_itemCheckMethod156);
            
            ItemCheckMethod l_itemCheckMethod157 = new ItemCheckMethod();
            l_itemCheckMethod157.columnName = "�i�f�o�j�ڕW�N";
            l_itemCheckMethod157.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod157.size = 2;
            l_itemCheckMethod157.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod157.customizing = true;
            methodMap.put("gp_target_year", l_itemCheckMethod157);
            
            ItemCheckMethod l_itemCheckMethod158 = new ItemCheckMethod();
            l_itemCheckMethod158.columnName = "�i�f�o�j�ڕW��";
            l_itemCheckMethod158.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod158.size = 2;
            l_itemCheckMethod158.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod158.customizing = true;
            methodMap.put("gp_target_month", l_itemCheckMethod158);
            
            ItemCheckMethod l_itemCheckMethod159 = new ItemCheckMethod();
            l_itemCheckMethod159.columnName = "�i�f�o�j�ϗ��z";
            l_itemCheckMethod159.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod159.size = 7;
            l_itemCheckMethod159.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod159.customizing = true;
            methodMap.put("gp_installment_figure", l_itemCheckMethod159);
            
            ItemCheckMethod l_itemCheckMethod160 = new ItemCheckMethod();
            l_itemCheckMethod160.columnName = "�i�f�o�j��������";
            l_itemCheckMethod160.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod160.size = 1;
            l_itemCheckMethod160.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod160.customizing = true;
            methodMap.put("gp_deposit_cycle", l_itemCheckMethod160);
            
            ItemCheckMethod l_itemCheckMethod161 = new ItemCheckMethod();
            l_itemCheckMethod161.columnName = "�i�f�o�j��n�o�H";
            l_itemCheckMethod161.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod161.size = 1;
            l_itemCheckMethod161.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod161.customizing = true;
            methodMap.put("gp_payment_root", l_itemCheckMethod161);
            
            ItemCheckMethod l_itemCheckMethod162 = new ItemCheckMethod();
            l_itemCheckMethod162.columnName = "�i�f�o�j�ē����敪";
            l_itemCheckMethod162.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod162.size = 1;
            l_itemCheckMethod162.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod162.customizing = true;
            methodMap.put("gp_reinvest_div", l_itemCheckMethod162);
            
            ItemCheckMethod l_itemCheckMethod163 = new ItemCheckMethod();
            l_itemCheckMethod163.columnName = "�i�f�o�j�ŋ敪";
            l_itemCheckMethod163.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod163.size = 1;
            l_itemCheckMethod163.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod163.customizing = true;
            methodMap.put("gp_tax_div", l_itemCheckMethod163);
            
            ItemCheckMethod l_itemCheckMethod164 = new ItemCheckMethod();
            l_itemCheckMethod164.columnName = "�i�f�o�j�i�D�j���x�z";
            l_itemCheckMethod164.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod164.size = 3;
            l_itemCheckMethod164.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod164.customizing = true;
            methodMap.put("gp_taxfree_limit", l_itemCheckMethod164);
            
            ItemCheckMethod l_itemCheckMethod165 = new ItemCheckMethod();
            l_itemCheckMethod165.columnName = "�i�f�o�j�i���D�j���x�z";
            l_itemCheckMethod165.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod165.size = 3;
            l_itemCheckMethod165.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod165.customizing = true;
            methodMap.put("gp_special_taxfree_limit", l_itemCheckMethod165);
            
            ItemCheckMethod l_itemCheckMethod166 = new ItemCheckMethod();
            l_itemCheckMethod166.columnName = "�i�f�o�j�����E�v";
            l_itemCheckMethod166.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod166.size = 1;
            l_itemCheckMethod166.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod166.customizing = true;
            methodMap.put("gp_subscr_summary", l_itemCheckMethod166);
            
            ItemCheckMethod l_itemCheckMethod167 = new ItemCheckMethod();
            l_itemCheckMethod167.columnName = "�i�f�o�j�����R�[�h";
            l_itemCheckMethod167.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod167.size = 1;
            l_itemCheckMethod167.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod167.customizing = true;
            methodMap.put("gp_product_code", l_itemCheckMethod167);
            
            ItemCheckMethod l_itemCheckMethod168 = new ItemCheckMethod();
            l_itemCheckMethod168.columnName = "�i�f�o�j�S�ۋq";
            l_itemCheckMethod168.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod168.size = 1;
            l_itemCheckMethod168.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod168.customizing = true;
            methodMap.put("gp_mortgage_customer", l_itemCheckMethod168);
            
            ItemCheckMethod l_itemCheckMethod169 = new ItemCheckMethod();
            l_itemCheckMethod169.columnName = "�i�f�o�j�~�b�N�X�q";
            l_itemCheckMethod169.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod169.size = 1;
            l_itemCheckMethod169.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod169.customizing = true;
            methodMap.put("gp_mix_customer", l_itemCheckMethod169);
            
            ItemCheckMethod l_itemCheckMethod170 = new ItemCheckMethod();
            l_itemCheckMethod170.columnName = "�i�f�o�j�_��";
            l_itemCheckMethod170.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod170.size = 1;
            l_itemCheckMethod170.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod170.customizing = true;
            methodMap.put("gp_contract", l_itemCheckMethod170);
            
            ItemCheckMethod l_itemCheckMethod171 = new ItemCheckMethod();
            l_itemCheckMethod171.columnName = "�i���O���j�쐬�敪";
            l_itemCheckMethod171.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod171.size = 1;
            l_itemCheckMethod171.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod171.customizing = true;
            methodMap.put("stk_voucher_div", l_itemCheckMethod171);
            
            ItemCheckMethod l_itemCheckMethod172 = new ItemCheckMethod();
            l_itemCheckMethod172.columnName = "�i���O���j���n";
            l_itemCheckMethod172.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod172.size = 1;
            l_itemCheckMethod172.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod172.customizing = true;
            methodMap.put("stk_taxation_tran_div", l_itemCheckMethod172);
            
            ItemCheckMethod l_itemCheckMethod173 = new ItemCheckMethod();
            l_itemCheckMethod173.columnName = "�i���O���j�Z���i�J�i�j";
            l_itemCheckMethod173.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod173.size = 70;
            l_itemCheckMethod173.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod173.customizing = true;
            methodMap.put("stk_address_line_kana", l_itemCheckMethod173);
            
            ItemCheckMethod l_itemCheckMethod174 = new ItemCheckMethod();
            l_itemCheckMethod174.columnName = "�i���O���j����";
            l_itemCheckMethod174.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod174.size = 1;
            l_itemCheckMethod174.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod174.customizing = true;
            methodMap.put("stk_transfer_div", l_itemCheckMethod174);
            
            ItemCheckMethod l_itemCheckMethod175 = new ItemCheckMethod();
            l_itemCheckMethod175.columnName = "�i���O���j��s�R�[�h";
            l_itemCheckMethod175.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod175.size = 4;
            l_itemCheckMethod175.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod175.customizing = true;
            methodMap.put("stk_fin_institution_code", l_itemCheckMethod175);
            
            ItemCheckMethod l_itemCheckMethod176 = new ItemCheckMethod();
            l_itemCheckMethod176.columnName = "�i���O���j�x�X�R�[�h";
            l_itemCheckMethod176.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod176.size = 3;
            l_itemCheckMethod176.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod176.customizing = true;
            methodMap.put("stk_fin_branch_code", l_itemCheckMethod176);
            
            ItemCheckMethod l_itemCheckMethod177 = new ItemCheckMethod();
            l_itemCheckMethod177.columnName = "�i���O���j�a���敪";
            l_itemCheckMethod177.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod177.size = 1;
            l_itemCheckMethod177.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod177.customizing = true;
            methodMap.put("stk_fin_save_div", l_itemCheckMethod177);
            
            ItemCheckMethod l_itemCheckMethod178 = new ItemCheckMethod();
            l_itemCheckMethod178.columnName = "�i���O���j�����ԍ�";
            l_itemCheckMethod178.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod178.size = 7;
            l_itemCheckMethod178.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod178.customizing = true;
            methodMap.put("stk_fin_account_no", l_itemCheckMethod178);
            
            ItemCheckMethod l_itemCheckMethod179 = new ItemCheckMethod();
            l_itemCheckMethod179.columnName = "����ƈ��҃R�[�h";
            l_itemCheckMethod179.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod179.size = 5;
            l_itemCheckMethod179.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod179.customizing = true;
            methodMap.put("brokerage_trader_code", l_itemCheckMethod179);
            
            ItemCheckMethod l_itemCheckMethod180 = new ItemCheckMethod();
            l_itemCheckMethod180.columnName = "�e�Њg�����ځi�敪1�P�j";
            l_itemCheckMethod180.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod180.size = 10;
            l_itemCheckMethod180.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod180.customizing = true;
            methodMap.put("ext_item_div11", l_itemCheckMethod180);
            
            ItemCheckMethod l_itemCheckMethod181 = new ItemCheckMethod();
            l_itemCheckMethod181.columnName = "�e�Њg�����ځi�敪�P2�j";
            l_itemCheckMethod181.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod181.size = 10;
            l_itemCheckMethod181.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod181.customizing = true;
            methodMap.put("ext_item_div12", l_itemCheckMethod181);
            
            ItemCheckMethod l_itemCheckMethod182 = new ItemCheckMethod();
            l_itemCheckMethod182.columnName = "�e�Њg�����ځi�敪�P3�j";
            l_itemCheckMethod182.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod182.size = 10;
            l_itemCheckMethod182.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod182.customizing = true;
            methodMap.put("ext_item_div13", l_itemCheckMethod182);
            
            ItemCheckMethod l_itemCheckMethod183 = new ItemCheckMethod();
            l_itemCheckMethod183.columnName = "�e�Њg�����ځi�敪�P4�j";
            l_itemCheckMethod183.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod183.size = 10;
            l_itemCheckMethod183.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod183.customizing = true;
            methodMap.put("ext_item_div14", l_itemCheckMethod183);
            
            ItemCheckMethod l_itemCheckMethod184 = new ItemCheckMethod();
            l_itemCheckMethod184.columnName = "�e�Њg�����ځi�敪�P5�j";
            l_itemCheckMethod184.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod184.size = 10;
            l_itemCheckMethod184.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod184.customizing = true;
            methodMap.put("ext_item_div15", l_itemCheckMethod184);
            
            ItemCheckMethod l_itemCheckMethod185 = new ItemCheckMethod();
            l_itemCheckMethod185.columnName = "�����ԍ��i�O�݁j";
            l_itemCheckMethod185.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod185.size = 20;
            l_itemCheckMethod185.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod185.customizing = true;
            methodMap.put("foreign_account_no", l_itemCheckMethod185);
            
            ItemCheckMethod l_itemCheckMethod186 = new ItemCheckMethod();
            l_itemCheckMethod186.columnName = "�������`�l�i�O�݁j";
            l_itemCheckMethod186.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod186.size = 60;
            l_itemCheckMethod186.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod186.customizing = true;
            methodMap.put("foreign_account_name", l_itemCheckMethod186);
            
            ItemCheckMethod l_itemCheckMethod187 = new ItemCheckMethod();
            l_itemCheckMethod187.columnName = "�������`�l�p���i�O�݁j";
            l_itemCheckMethod187.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod187.size = 30;
            l_itemCheckMethod187.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod187.customizing = true;
            methodMap.put("foreign_account_name_eng", l_itemCheckMethod187);
            
            ItemCheckMethod l_itemCheckMethod188 = new ItemCheckMethod();
            l_itemCheckMethod188.columnName = "�a���敪�i�O�݁j";
            l_itemCheckMethod188.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod188.size = 1;
            l_itemCheckMethod188.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod188.customizing = true;
            methodMap.put("foreign_save_div", l_itemCheckMethod188);

            ItemCheckMethod l_itemCheckMethod189 = new ItemCheckMethod();
            l_itemCheckMethod189.columnName = "�폜�t���O";
            l_itemCheckMethod189.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod189.size = 1;
            l_itemCheckMethod189.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod189.customizing = true;
            methodMap.put("delete_flag", l_itemCheckMethod189);

            ItemCheckMethod l_itemCheckMethod190 = new ItemCheckMethod();
            l_itemCheckMethod190.columnName = "����t���O";
            l_itemCheckMethod190.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod190.size = 1;
            l_itemCheckMethod190.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod190.customizing = true;
            methodMap.put("print_flag", l_itemCheckMethod190);

            ItemCheckMethod l_itemCheckMethod191 = new ItemCheckMethod();
            l_itemCheckMethod191.columnName = "��̃t���O";
            l_itemCheckMethod191.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod191.size = 1;
            l_itemCheckMethod191.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod191.customizing = true;
            methodMap.put("receipt_flag", l_itemCheckMethod191);

            ItemCheckMethod l_itemCheckMethod192 = new ItemCheckMethod();
            l_itemCheckMethod192.columnName = "�����t���O";
            l_itemCheckMethod192.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod192.size = 1;
            l_itemCheckMethod192.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod192.customizing = true;
            methodMap.put("agreement_flag", l_itemCheckMethod192);

            ItemCheckMethod l_itemCheckMethod193 = new ItemCheckMethod();
            l_itemCheckMethod193.columnName = "�O���l�t���O";
            l_itemCheckMethod193.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod193.size = 1;
            l_itemCheckMethod193.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod193.customizing = true;
            methodMap.put("foreign_flag", l_itemCheckMethod193);

            ItemCheckMethod l_itemCheckMethod194 = new ItemCheckMethod();
            l_itemCheckMethod194.columnName = "�t���K�i1";
            l_itemCheckMethod194.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod194.size = 120;
            l_itemCheckMethod194.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod194.customizing = true;
            methodMap.put("agency_acc_name_kana1", l_itemCheckMethod194);

            ItemCheckMethod l_itemCheckMethod195 = new ItemCheckMethod();
            l_itemCheckMethod195.columnName = "�t���K�i2";
            l_itemCheckMethod195.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod195.size = 120;
            l_itemCheckMethod195.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod195.customizing = true;
            methodMap.put("agency_acc_name_kana2", l_itemCheckMethod195);

            ItemCheckMethod l_itemCheckMethod196 = new ItemCheckMethod();
            l_itemCheckMethod196.columnName = "����1";
            l_itemCheckMethod196.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod196.size = 120;
            l_itemCheckMethod196.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod196.customizing = true;
            methodMap.put("agency_acc_name1", l_itemCheckMethod196);

            ItemCheckMethod l_itemCheckMethod197 = new ItemCheckMethod();
            l_itemCheckMethod197.columnName = "����2";
            l_itemCheckMethod197.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod197.size = 60;
            l_itemCheckMethod197.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod197.customizing = true;
            methodMap.put("agency_acc_name2", l_itemCheckMethod197);

            ItemCheckMethod l_itemCheckMethod198 = new ItemCheckMethod();
            l_itemCheckMethod198.columnName = "�Z��1";
            l_itemCheckMethod198.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod198.size = 96;
            l_itemCheckMethod198.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod198.customizing = true;
            methodMap.put("agency_address_line1", l_itemCheckMethod198);

            ItemCheckMethod l_itemCheckMethod199 = new ItemCheckMethod();
            l_itemCheckMethod199.columnName = "�Z��2";
            l_itemCheckMethod199.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod199.size = 48;
            l_itemCheckMethod199.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod199.customizing = true;
            methodMap.put("agency_address_line2", l_itemCheckMethod199);

            ItemCheckMethod l_itemCheckMethod200 = new ItemCheckMethod();
            l_itemCheckMethod200.columnName = "��\�҂̖�E";
            l_itemCheckMethod200.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod200.size = 40;
            l_itemCheckMethod200.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod200.customizing = true;
            methodMap.put("agency_rep_post", l_itemCheckMethod200);

            ItemCheckMethod l_itemCheckMethod201 = new ItemCheckMethod();
            l_itemCheckMethod201.columnName = "��\�҂̃t���K�i1";
            l_itemCheckMethod201.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod201.size = 120;
            l_itemCheckMethod201.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod201.customizing = true;
            methodMap.put("agency_rep_name_kana1", l_itemCheckMethod201);

            ItemCheckMethod l_itemCheckMethod202 = new ItemCheckMethod();
            l_itemCheckMethod202.columnName = "��\�҂̃t���K�i2";
            l_itemCheckMethod202.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod202.size = 120;
            l_itemCheckMethod202.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod202.customizing = true;
            methodMap.put("agency_rep_name_kana2", l_itemCheckMethod202);

            ItemCheckMethod l_itemCheckMethod203 = new ItemCheckMethod();
            l_itemCheckMethod203.columnName = "��\�҂̎���1";
            l_itemCheckMethod203.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod203.size = 60;
            l_itemCheckMethod203.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod203.customizing = true;
            methodMap.put("agency_rep_name1", l_itemCheckMethod203);

            ItemCheckMethod l_itemCheckMethod204 = new ItemCheckMethod();
            l_itemCheckMethod204.columnName = "��\�҂̎���2";
            l_itemCheckMethod204.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod204.size = 60;
            l_itemCheckMethod204.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod204.customizing = true;
            methodMap.put("agency_rep_name2", l_itemCheckMethod204);
        }
    }

    private String trimWhitespace(String l_str)
    {
        final String STR_METHOD_NAME = " trimWhitespace(String)";
        log.entering(STR_METHOD_NAME);
               
        StringBuffer l_sbStr = new StringBuffer();
        String l_str1 = null;
        int l_intNameLength = l_str.length();

        for (int j = 0; j < l_intNameLength; j++)
        {
            if (Character.isWhitespace(l_str.charAt(j)))
            {
                continue;
            }
            l_sbStr.append(l_str.charAt(j));
        }
        l_str1 = l_sbStr.toString();
        
        log.exiting(STR_METHOD_NAME);
        return l_str1;
    }
    
    /**
     * (getemail�A�h���X)<BR>
     * email�A�h���X���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.email�A�h���X��ԋp����B<BR> 
     * @@return String
     */
    public String getEmailAddress()
    {
        return this.expAccountOpenParams.getEmailAddress();   
    }
    
    /**
     * (getY�qID )<BR>
     * Y�qID���擾����B<BR> 
     * <BR>
     * this.Y�q�}�X�^�s.Y�qID��ԋp����B<BR>
     * @@return String
     */
    public String getYCustomerId()
    {
        return String.valueOf(this.yCustomerParams.getYCustomerId());       
    }
    
    /**
     * (getY�q�Ǘ�No)<BR>
     * Y�q�Ǘ�No���擾����B<BR> 
     * <BR>
     * this.Y�q�}�X�^�s.�Ǘ�No��ԋp����B<BR>
     * @@return String
     */
    public String getControlNumber()
    {
        return String.valueOf(this.yCustomerParams.getControlNumber());    
    }
    
    /**
     * (getY�q�Ǘ����X�R�[�h)<BR>
     * Y�q�Ǘ����X�R�[�h���擾����B<BR> 
     * <BR>
     * this.Y�q�}�X�^�s.�Ǘ����X�R�[�h��ԋp����B<BR>
     * @@return String
     */
    public String getControlBranchCode()
    {
        return this.yCustomerParams.getControlBranchCode();     
    }
    
    /**
     * (getY�q�Ɩ��敪)<BR>
     * Y�q�Ɩ��敪���擾����B<BR> 
     * <BR>
     * this.Y�q�}�X�^�s.�Ɩ��敪��ԋp����B<BR>
     * @@return String
     */
    public String getOperationDiv()
    {
        return this.yCustomerParams.getOperationDiv();
    }
    
    /**
     * (get�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.�ڋq���i�J�i�j��ԋp����B<BR> 
     * @@return String
     */
    public String getFamilyNameAlt1()
    {
        return this.expAccountOpenParams.getFamilyNameAlt1();    
    }
    
    /**
     * (get�ڋq���i�J�i�j)<BR>
     * �ڋq���i�J�i�j���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.�ڋq���i�J�i�j��ԋp����B<BR> 
     * @@return String
     */
    public String getGivenNameAlt1()
    {
        return this.expAccountOpenParams.getGivenNameAlt1();    
    }
    
    /**
     * (get�Z���P)<BR>
     * �Z���P���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.�Z���P��ԋp����B<BR> 
     * @@return String
     */
    public String getAddressLine1()
    {
        return this.expAccountOpenParams.getAddressLine1();   
    }
    
    /**
     * (get�Z���Q)<BR>
     * �Z���Q���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.�Z���Q��ԋp����B<BR> 
     * @@return String
     */
    public String getAddressLine2()
    {
        return this.expAccountOpenParams.getAddressLine2();     
    }
    
    /**
     * (get�Z���R)<BR>
     * �Z���R���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.�Z���R��ԋp����B<BR> 
     * @@return String
     */
    public String getAddressLine3()
    {
        return this.expAccountOpenParams.getAddressLine3();       
    }
    
    /**
     * (get���N����)<BR>
     * ���N�������擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.���N������ԋp����B<BR> 
     * @@return String
     */
    public String getBornDate()
    {
        return this.expAccountOpenParams.getBornDate();        
    }
    
    /**
     * (get���N�����N��)<BR>
     * ���N�����N�����擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.���N�����N����ԋp����B<BR> 
     * @@return String
     */
    public String getEraBorn()
    {
        return this.expAccountOpenParams.getEraBorn();   
    }
    
    /**
     * (get�d�b�ԍ�)<BR>
     * �d�b�ԍ����擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.�d�b�ԍ���ԋp����B <BR>
     * @@return String
     */
    public String getTelephone()
    {
        return this.expAccountOpenParams.getTelephone();    
    }
    
    /**
     * (get�A����d�b�ԍ��i�g�сj)<BR>
     * �A����d�b�ԍ��i�g�сj���擾����B<BR> 
     * <BR>
     * this.�����J�݌����q�s.�A����d�b�ԍ��i�g�сj��ԋp����B<BR>
     * @@return String 
     */
    public String getMobile()
    {
        return this.expAccountOpenParams.getMobile();       
    }
    
	/**
	 * (getFAX�ԍ�)<BR>
	 * FAX�ԍ����擾����B<BR> 
	 * <BR>
	 * this.�����J�݌����q�s.FAX�ԍ���ԋp����B <BR>
	 * @@return String
	 */
	public String getFax()
	{
		return this.expAccountOpenParams.getFax();    
	}
    
	/**
	 * (get���ю�Ζ���d�b�ԍ�)<BR>
	 * ���ю�Ζ���d�b�ԍ����擾����B<BR> 
	 * <BR>
	 * this.�����J�݌����q�s.���ю�Ζ���d�b�ԍ���ԋp����B<BR>
	 * @@return String 
	 */
	public String getHouseholder_Office_Tel()
	{
		return this.expAccountOpenParams.getHouseholderOfficeTel();       
	}
    
    /**
     * (validate�ڋq�R�[�h)<BR>
     * ����ڋq���d�����ēo�^����Ă��Ȃ����`�F�b�N����B<BR> 
     * <BR>
     * <BR>
     * �P�j�@@�����R�[�h�d���`�F�b�N�i�����q�j <BR>
     * �@@�ȉ��̏����Ō����J�݌����q�e�[�u������������B <BR>
     * �Y���s�����݂��A�Y���s�̎��ʃR�[�h��this.get���ʃR�[�h()�ƈقȂ�ꍇ�A<BR> 
     * �ڋq���d�����ēo�^����Ă���Ɣ��肵��O���X���[����B <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01313<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�����J�݌����q.�،���ЃR�[�h = this.get�،���ЃR�[�h()<BR> 
     * �@@�����J�݌����q.���X�R�[�h = this.get���X�R�[�h() <BR>
     * �@@�����J�݌����q.�����R�[�h = this.get�����R�[�h()<BR>
     * <BR>
     * �Q�j�@@�����R�[�h�d���`�F�b�N�i�ڋq�}�X�^�j <BR>
     * �@@�ȉ��̏����Ōڋq�}�X�^�e�[�u������������B <BR>
     * �Y���s�����݂��A�Y���s�̌����o�^����null�łȂ��A���A�Y���s�̌����o�^�����������ȊO�̏ꍇ�A<BR>
     * �ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR> 
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01313<BR>
     * <BR>         
     * �@@[����] <BR>
     * �@@�ڋq�}�X�^.�،���ЃR�[�h = this.get�،���ЃR�[�h() <BR>
     * �@@�ڋq�}�X�^.���X�R�[�h = this.get���X�R�[�h() <BR>
     * �@@�ڋq�}�X�^.�����R�[�h like this.get�����R�[�h() + '%' <BR>
     * @@throws WEB3BaseException 
     */
    public void validateAccountCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountCode()";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strBranchCode = this.getBranchCode();
        String l_strAccountCode = this.getAccountCode();
        try 
        {
            //�P�j�@@�����R�[�h�d���`�F�b�N�i�����q�j 
            String l_strWhere = null;
            Object[] l_obj = null;
            
            if (l_strAccountCode != null && l_strAccountCode.length() != 0)
            {
                l_strWhere = 
                    "institution_code = ? and branch_code = ? and account_code = ? and acc_open_request_number <> ?";
                l_obj = 
                    new Object[]{ l_strInstitutionCode, l_strBranchCode, l_strAccountCode, this.getRequestNumber() };
    
                List l_lisRow = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        ExpAccountOpenRow.TYPE, 
                        l_strWhere, l_obj);
    
                if (l_lisRow != null && l_lisRow.size() > 0)
                {
                    log.debug("�ڋq���d�����ēo�^����Ă���Ɣ��肵��O���X���[����B");
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�ڋq���d�����ēo�^����Ă���Ɣ��肵��O���X���[����B");
                }
                
                //�Q�j �����R�[�h�d���`�F�b�N�i�ڋq�}�X�^�j
                String l_strMainAccountWhere = 
                    " institution_code = ? and branch_code = ? and account_code like ? ";
    
                Object[] l_objMainAccount = 
                    { l_strInstitutionCode, l_strBranchCode, l_strAccountCode + "%" };
                
                List l_lisMainAccountRow = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        MainAccountRow.TYPE, 
                        l_strMainAccountWhere, 
                        l_objMainAccount);

                // �Y���s�����݂��A�Y���s�̌����o�^����null�łȂ��A���A
                // �Y���s�̌����o�^�����������ȊO�̏ꍇ�A
                Date l_datProcessDate = GtlUtils.getSystemTimestamp();

                if (l_lisMainAccountRow != null && l_lisMainAccountRow.size() > 0)
                {
                    int l_intLength = l_lisMainAccountRow.size();
                    for (int i = 0; i < l_intLength; i++)
                    {
                        Date l_datAccountOpenDate =
                            ((MainAccountRow) l_lisMainAccountRow.get(i)).getAccountOpenDate();
                        if ( l_datAccountOpenDate != null
                            && WEB3DateUtility.compareToDay(l_datAccountOpenDate, l_datProcessDate) != 0)
                        {
                            log.debug("�ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B");
    
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                                this.getClass().getName() + STR_METHOD_NAME,
                                "�Y���s�̌����o�^����null�łȂ��ꍇ" + "�ڋq���d�����ēo�^����Ă���Ɣ��肵��O���X���[����B");
                        }
                    }
                }
            }
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
     * (get�����J�ݐR���҂���񃊃X�g)<BR>
     * �����J�ݐR���҂���񃊃X�g���擾����B<BR>
     * @@return ArrayList 
     */
    public ArrayList getAccOpenJudgeWaitingInfoList()
    {
        return this.accOpenJudgeWaitingInfoList;      
    }
    
    /**
     * (set�����J�ݐR���҂���񃊃X�g)<BR>
     * �����J�ݐR���҂���񃊃X�g�ɐR���҂�����ǉ�����B<BR> 
     * <BR>
     * �P�j�����J�ݐR���҂�Params�I�u�W�F�N�g�𐶐����v���p�e�B��ݒ肷��B<BR> 
     * <BR>
     * �،���ЃR�[�h�Fthis.get�،���ЃR�[�h()<BR>  
     * ���ʃR�[�h�Fthis.get���ʃR�[�h() <BR>
     * �ʔԁF�����J�ݐR���҂���񃊃X�g�̒���+1<BR> 
     * ���X�R�[�h�Fnull <BR>
     * �ڋq�R�[�h�Fnull <BR>
     * �R����ʁF�i�����j�R����� <BR>
     * �d���敪�F�i�����j�d���敪 <BR>
     * �d���Ώە��X�R�[�h�F�i�����j�d�����X�R�[�h <BR>
     * �d���Ώیڋq�R�[�h�F�i�����j�d���ڋq�R�[�h (��6��)<BR>
     * Y�q���EY�qID�F�i�����jY�qID <BR>
     * Y�q���E�Ǘ����X�R�[�h�F�i�����j�Ǘ����X�R�[�h <BR>
     * Y�q���E�Ɩ��敪�F�i�����j�Ɩ��敪 <BR>
     * Y�q���E�Ǘ�No�D�F�i�����j�Ǘ�No�D <BR>
     * �R���敪�F"0" <BR>
     * �R���҃R�[�h�Fnull <BR>
     * <BR>
     * �Q�j�����J�ݐR���҂�Params�I�u�W�F�N�g�������J�ݐR���҂���񃊃X�g�֒ǉ�����B<BR>
     * @@param l_strReviewCode - (�R�����)<BR>
     * �R����ʁB<BR>
     * @@param l_strDuplicateDiv - (�d���敪)<BR>
     * �d���敪�B<BR>
     * @@param l_strDuplicateBranchCode - (�d�����X�R�[�h)<BR>
     * �d�����X�R�[�h�B<BR>
     * @@param l_strDuplicateAccountCode - (�d���ڋq�R�[�h)<BR>
     * �d���ڋq�R�[�h�B<BR>
     * @@param l_yCustomerId - (Y�qID)<BR>
     * Y�q���EY�qID�B<BR>
     * @@param l_strControlBranchCode - (�Ǘ����X�R�[�h)<BR>
     * Y�q���E�Ǘ����X�R�[�h�B<BR>
     * @@param l_strOperationDiv - (�Ɩ��敪)<BR>
     * Y�q���E�Ɩ��敪�B<BR>
     * @@param l_controlNumber - (�Ǘ�No.)<BR>
     * Y�q���E�Ǘ�No.<BR>
     */
    private void setAccOpenJudgeWaitingInfoList(
        String l_strReviewCode,
        String l_strDuplicateDiv,
        String l_strDuplicateBranchCode,
        String l_strDuplicateAccountCode,
        Long l_yCustomerId,
        String l_strControlBranchCode,
        String l_strOperationDiv,
        Long l_controlNumber)
    {
        final String STR_METHOD_NAME = 
            "setAccOpenValidationWaitingInfoList(String,String,String,String,Long,String,Long)";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenJudgeWaitingInfoList == null) 
        {
            this.accOpenJudgeWaitingInfoList = new ArrayList();
        }
        
        //�P�j�����J�ݐR���҂�Params�I�u�W�F�N�g�𐶐����v���p�e�B��ݒ肷��B
        AccOpenWaitingParams l_waitingParams = new AccOpenWaitingParams();
        
        //�،���ЃR�[�h�Fthis.get�،���ЃR�[�h()
        l_waitingParams.setInstitutionCode(this.getInstitutionCode());
        
        //���ʃR�[�h�Fthis.get���ʃR�[�h() 
        l_waitingParams.setAccOpenRequestNumber(this.getRequestNumber());
        
        //�ʔԁF�����J�ݐR���҂���񃊃X�g�̒���+1 
        l_waitingParams.setSerialNo(String.valueOf(this.accOpenJudgeWaitingInfoList.size() + 1));
        
        //���X�R�[�h�Fnull 
        l_waitingParams.setBranchCode(null);
        
        //�ڋq�R�[�h�Fnull 
        l_waitingParams.setAccountCode(null);
        
        //�R����ʁF�i�����j�R����� 
        l_waitingParams.setReviewCode(l_strReviewCode);
        
        //�d���敪�F�i�����j�d���敪 
        l_waitingParams.setDuplicationDiv(l_strDuplicateDiv);
        
        //�d���Ώە��X�R�[�h�F�i�����j�d�����X�R�[�h 
        l_waitingParams.setDiploBranchCode(l_strDuplicateBranchCode);
        
        //�d���Ώیڋq�R�[�h�F�i�����j�d���ڋq�R�[�h 
        if (l_strDuplicateAccountCode != null && l_strDuplicateAccountCode.length() > 6)
        {
			l_waitingParams.setDiploAccountCode(l_strDuplicateAccountCode.substring(0, 6));
        }
        else
        {
			l_waitingParams.setDiploAccountCode(l_strDuplicateAccountCode);
        }
        
        //Y�q���EY�qID�F�i�����jY�qID 
        l_waitingParams.setYCustomerId(l_yCustomerId);
        
        //Y�q���E�Ǘ����X�R�[�h�F�i�����j�Ǘ����X�R�[�h 
        l_waitingParams.setControlBranchCode(l_strControlBranchCode);
        
        //Y�q���E�Ɩ��敪�F�i�����j�Ɩ��敪 
        l_waitingParams.setOperationDiv(l_strOperationDiv);
        
        //Y�q���E�Ǘ�No�D�F�i�����j�Ǘ�No�D 
        if (l_controlNumber != null) 
        {
            l_waitingParams.setControlNumber(l_controlNumber.intValue());   
        }
        
        //�R���敪�F"0"
        l_waitingParams.setCheckDiv(WEB3CheckDivDef.CHECK_WAITING);
        
        //�R���҃R�[�h�Fnull 
        l_waitingParams.setCheckerCode(null);
        
        //�Q�j�����J�ݐR���҂�Params�I�u�W�F�N�g�������J�ݐR���҂���񃊃X�g�֒ǉ�����B
        this.accOpenJudgeWaitingInfoList.add(l_waitingParams);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�d�b�ԍ�)<BR>
     * ���͂����d�b�ԍ����g�ѓd�b�ԍ����o�^����Ă��Ȃ����`�F�b�N����B<BR> 
     * ���ɓo�^����Ă���ꍇ�A�ȉ��Q�̏������s���B <BR>
     * �@@�x������ԋp����B <BR>
     * �A���X�v���t�@@�����X�̒l>1�̏ꍇ�����J�ݐR���҂���񃊃X�g�֏d���ڋq����ۑ�����B<BR> 
     * (set�����J�ݐR���҂���񃊃X�g()) <BR>
     * <BR>
     * �P�j �߂�l�pArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j �d�b�ԍ��̏d���m�F���s���B<BR> 
     * �@@�Q�|�P�j �����J�ݓd�b�ԍ��d���`�F�b�N�N���X�𐶐�����B<BR> 
     * <BR>
     * �@@�@@[�����J�݋��ʃT�[�r�X#to�����J�ݓd�b�ԍ��d���`�F�b�N()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�ڋq�R�[�h�Fthis.�����R�[�h()�̖߂�l <BR>
     * �@@�@@�@@���ʃR�[�h�Fthis.���ʃR�[�h()�̖߂�l <BR>
     * �@@�@@�@@�،���ЃR�[�h�Fthis.get�،���ЃR�[�h()�̖߂�l<BR> 
     *        <BR>
     *   �Q�|�Q�j �����q�d�b�ԍ�������̐ݒ�<BR>
     *            (20060728����0E�d�l�B��Ђɂ�茟����ɕύX������ꍇ�͗v�C��)<BR>
     * <BR>
     *     �Q�|�Q�|�P�j �i�����j�����敪���l�̏ꍇ<BR>
     *  <BR>
     *          [�����J�ݓd�b�ԍ��d���`�F�b�N#set�����q�d�b�ԍ�������()�Ɏw�肷�����]<BR>
     *            �d�b�ԍ�������F���� ��"mobile"��v�f�Ƃ���String�z��<BR>
     * <BR>
     *     �Q�|�Q�|�Q�j �i�����j�����敪���@@�l�̏ꍇ<BR>
     * <BR>
     *          [�����J�ݓd�b�ԍ��d���`�F�b�N#set�����q�d�b�ԍ�������()�Ɏw�肷�����]<BR>
     *            �d�b�ԍ�������F����3 ��"mobile","fax","householder_office_tel"��v�f�Ƃ���String�z��<BR>
     * <BR>
     *   �Q�|�R�j �d�b�ԍ��̏d���`�F�b�N���s���B<BR>
     * 
     *      [�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�Ɏw�肷�����]<BR>
     *         �d�b�ԍ��Fthis.get�d�b�ԍ�()�̖߂�l<BR>
     * <BR>
     *   �Q�|�S�j �d���d�b�ԍ������݂���i�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�̖߂�l�̒���>0�j�ꍇ�A<BR>
     *     �Q�|�S�|�P�j �i�����j���X�v���t�@@�����X > 1�̏ꍇ�A�d���ڋq����ۑ�����B<BR>
     *               (set�����J�ݐR���҂���񃊃X�g()�j�B
     * <BR>
     *        �Q�|�S�|�P�|�P�j �R����ʂ̔���<BR>
     *           �����J�ݓd�b�ԍ��d���`�F�b�N#get�e�[�u����()�̖߂�l��<BR>
     *           �����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_EXP_ACC_OPEN�̏ꍇ�A"2"<BR>
     *           ����ȊO�̏ꍇ��"1"��ݒ肷��B<BR>
     * <BR>
     *        �Q�|�S�|�Q�|�Q�j �d���ڋq����ۑ�<BR>
     * <BR>
     *           [set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����]<BR>
     *             �R����ʁF �Q�|�R�|�P�j �̔���l<BR>
     *             �d���敪�F"3"<BR>
     *             �d�����X�R�[�h:�����J�ݓd�b�ԍ��d���`�F�b�N#get���X�R�[�h()�̖߂�l<BR>
     *             �d���ڋq�R�[�h:�����J�ݓd�b�ԍ��d���`�F�b�N#get�ڋq�R�[�h()�̖߂�l<BR>
     *             Y�qID�Fnull<BR>
     *             �Ǘ����X�R�[�h�Fnull<BR>
     *             �Ɩ��敪�Fnull<BR>
     *             �Ǘ�No�D�Fnull<BR>
     * <BR>
     *   �Q�|�T�j   �Q�|�S�j�ɂ����ďd���d�b�ԍ������݂���ꍇ�A�uWARNING_60012�F�d�b�ԍ��d���̉\������v�A<BR>
     *              �̌x�����b�Z�[�W��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * <BR>
     * �R�j �g�ѓd�b�ԍ��̏d���m�F���s���B<BR> 
     * �@@�R�|�P�j �g�ѓd�b�ԍ��̏d���`�F�b�N���s���B<BR> 
     * <BR>
     * �@@�@@[�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�d�b�ԍ��Fthis.get�A����d�b�ԍ��i�g�сj()�̖߂�l <BR>
     * <BR>
     * �@@�R�|�Q�j �d���d�b�ԍ������݂���<BR>
     * �@@�i�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�̖߂�l�̒���>0�j�ꍇ�A<BR> 
     * �@@�@@�R�|�Q�|�P�j �i�����j���X�v���t�@@�����X > 1�̏ꍇ�A�d���ڋq����ۑ�����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@(set�����J�ݐR���҂���񃊃X�g()�j�B <BR>
     * <BR>
     * �@@�@@�@@�R�|�Q�|�P�|�P�j �R����ʂ̔��� <BR>
     * �@@�@@�@@�@@�����J�ݓd�b�ԍ��d���`�F�b�N#get�e�[�u����()�̖߂�l�� <BR>
     * �@@�@@�@@�@@�����J�ݏd���`�F�b�N���[�e�B���e�B.MST_NM_EXP_ACC_OPEN�̏ꍇ�A"2"<BR> 
     * �@@�@@�@@�@@����ȊO�̏ꍇ��"1"��ݒ肷��B <BR>
     * <BR>
     * �@@�@@�@@�R�|�Q�|�Q�|�Q�j �d���ڋq����ۑ�<BR> 
     *              <BR>
     * �@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@�@@�R����ʁF �R�|�Q�|�P�j �̔���l <BR>
     * �@@�@@�@@�@@�@@�d���敪�F"3" <BR>
     * �@@�@@�@@�@@�@@�d�����X�R�[�h:�����J�ݓd�b�ԍ��d���`�F�b�N#get���X�R�[�h()�̖߂�l<BR> 
     * �@@�@@�@@�@@�@@�d���ڋq�R�[�h:�����J�ݓd�b�ԍ��d���`�F�b�N#get�ڋq�R�[�h()�̖߂�l <BR>
     * �@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull<BR> 
     * �@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     * �@@�@@�@@�@@�@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     *   �R�|�R�j  �R�|�Q�j�ɂ����ďd���d�b�ԍ������݂���ꍇ�A<BR>
     *   �@@�@@�@@�uWARNING_60013�F�@@�g�ѓd�b�ԍ��d���̉\������v�A<BR> 
     * �@@�@@�@@�@@�@@�̌x�����b�Z�[�W��ArrayList�ɒǉ�����B<BR> 
     * 
     * <BR>
     * �S�j ArrayList��ԋp����B<BR>
     * @@param l_intBranchPreference - (���X�v���t�@@�����X)<BR>
     * @@param l_strAccountDiv - (�����敪)
     * ���X�v���t�@@�����X�l <BR>
     * <BR>
     * [�ݒ�l] <BR>
     * 0�F�`�F�b�N�s�v-�A���[�g�\����-�R���҂�UPDATE�� <BR>
     * 1�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�� <BR>
     * 2�F�`�F�b�N���s-�A���[�g�\����-�R���҂�UPDATE�L <BR>
     * 3�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�L<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException 
     */
    public ArrayList validateTelephone(int l_intBranchPreference, String l_strAccountDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTelephone(int)";
        log.entering(STR_METHOD_NAME);
       
        //�P�j �߂�l�pArrayList�𐶐�����B 
        ArrayList l_lisWarnMsg = new ArrayList();
        
        //�Q�j �d�b�ԍ��̏d���m�F���s���B 
        //  �Q�|�P�j �����J�ݓd�b�ԍ��d���`�F�b�N�N���X�𐶐�����B 
        WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
            Services.getService(WEB3AccOpenInfoCreatedService.class); 
        WEB3AccOpenTelNumberDuplicationCheck l_accOpenTelNumberDuplicationCheck = 
            l_service.toAccOpenTelNumberDuplicationCheck(
                this.getAccountCode(),
                this.getRequestNumber(),
                this.getInstitutionCode());
        
		//  �Q�|�Q�j �����q�d�b�ԍ�������̐ݒ�
		//		     (20060728����0E�d�l�B��Ђɂ�茟����ɕύX������ꍇ�͗v�C��)
		if (WEB3AccOpenDuplicationCheckUtility.EXP_ACC_OPEN_ACCOUNT_DIV_INDV.equals(l_strAccountDiv))
		{
			//    �Q�|�Q�|�P�j �i�����j�����敪���l�̏ꍇ
			String[] l_strRow = {"mobile"};
			l_accOpenTelNumberDuplicationCheck.setExpOpenAccExqRow(l_strRow);
		}
		else if (WEB3AccOpenDuplicationCheckUtility.EXP_ACC_OPEN_ACCOUNT_DIV_COP.equals(l_strAccountDiv))
		{
			//    �Q�|�Q�|�Q�j �i�����j�����敪���@@�l�̏ꍇ
			String[] l_strRow = {"mobile","fax","householder_office_tel"};
			l_accOpenTelNumberDuplicationCheck.setExpOpenAccExqRow(l_strRow);			
		}
        
        //   �Q�|�R�j �d�b�ԍ��̏d���`�F�b�N���s���B 
        //      [�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�Ɏw�肷�����] 
        //        �d�b�ԍ��Fthis.get�d�b�ԍ�()�̖߂�l 
        Object[] l_objDuplicatedTels = 
            l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(this.getTelephone());
        
        //   �Q�|�S�j �d���d�b�ԍ������݂���ꍇ�A
        if (l_objDuplicatedTels != null && l_objDuplicatedTels.length > 0)
        {                 
            //�Q�|�S�|�P�j �i�����j���X�v���t�@@�����X > 1�̏ꍇ�A�d���ڋq����ۑ�����
            if (l_intBranchPreference > 1) 
            {
                int l_intLength = l_objDuplicatedTels.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    String l_strTableName = 
                        l_accOpenTelNumberDuplicationCheck.getTableName(l_objDuplicatedTels[i]);
                    String l_strAccountCode = 
                        l_accOpenTelNumberDuplicationCheck.getAccountCode(l_objDuplicatedTels[i]);
                    String l_strBranchCode = 
                        l_accOpenTelNumberDuplicationCheck.getBranchCode(l_objDuplicatedTels[i]);
                    
                    //�Q�|�S�|�P�|�P�j �R����ʂ̔��� 
                    String l_strReviewCode = null;
                    if (WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN.equals(l_strTableName)) 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP;
                    }
                    else 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN;
                    }

                    //�Q�|�S�|�Q�|�Q�j �d���ڋq����ۑ� 
                    //(set�����J�ݐR���҂���񃊃X�g()�j�B
                    this.setAccOpenJudgeWaitingInfoList(
                        l_strReviewCode, 
                        WEB3DuplicationDivDef.TELEPHONE_NO,
                        l_strBranchCode,
                        l_strAccountCode,
                        null,
                        null,
                        null,
                        null);
                }
            }
        
            //   �Q�|�T�j   �Q�|�S�j�ɂ����ďd���d�b�ԍ������݂���ꍇ�A 
            //  �uWARNING_60012�F�@@�d�b�ԍ��d���̉\������v�̌x�����b�Z�[�W��ArrayList�ɒǉ�����B 
            l_lisWarnMsg.add(WEB3ErrorCatalog.WARNING_60012.getErrorCode());
        }
        
        //�R�j �g�ѓd�b�ԍ��̏d���m�F���s���B 
        //  �R�|�P�j �g�ѓd�b�ԍ��̏d���`�F�b�N���s���B 
		ArrayList l_objDupliMobileList=new ArrayList();
		//    [�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�Ɏw�肷�����] 
		//      �d�b�ԍ��Fthis.get�A����d�b�ԍ��i�g�сj()�̖߂�l 
		Object[] l_tempArray = 
				l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(this.getMobile());
		if (l_tempArray != null) l_objDupliMobileList.addAll(Arrays.asList(l_tempArray));
		
		if (WEB3AccOpenDuplicationCheckUtility.EXP_ACC_OPEN_ACCOUNT_DIV_COP.equals(l_strAccountDiv))
		{
			//    �R�|�P�|�P�j �i�����j�����敪���@@�l�̏ꍇ�A�ȉ�������ǉ�
			//		     (20060728����0E�d�l�B��Ђɂ�茟����ɕύX������ꍇ�͗v�C��)
			
			//    [�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�Ɏw�肷�����] 
			//      �d�b�ԍ��Fthis.getFAX�ԍ�()�̖߂�l 
			l_tempArray = l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(this.getFax());
			if (l_tempArray != null) l_objDupliMobileList.addAll(Arrays.asList(l_tempArray));
			//    [�����J�ݓd�b�ԍ��d���`�F�b�N#get�d���ڋq���()�Ɏw�肷�����] 
			//      �d�b�ԍ��Fthis.get���ю�Ζ���d�b�ԍ�()�̖߂�l 
			l_tempArray = l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(
					this.getHouseholder_Office_Tel());
			if (l_tempArray != null) l_objDupliMobileList.addAll(Arrays.asList(l_tempArray));
		}
        
		Object[] l_objDuplicatedMobiles=l_objDupliMobileList.toArray();

        //  �R�|�Q�j �d���d�b�ԍ������݂���ꍇ�A
        if (l_objDuplicatedMobiles != null && l_objDuplicatedMobiles.length > 0) 
        {             
            //�R�|�Q�|�P�j �i�����j���X�v���t�@@�����X > 1�̏ꍇ�A�d���ڋq����ۑ�����B
            if (l_intBranchPreference > 1)
            {
                for (int i = 0; i < l_objDuplicatedMobiles.length; i++)
                {
                    String l_strTableName = 
                        l_accOpenTelNumberDuplicationCheck.getTableName(l_objDuplicatedMobiles[i]);
                    
                    String l_strAccountCode = 
                        l_accOpenTelNumberDuplicationCheck.getAccountCode(l_objDuplicatedMobiles[i]);
                    String l_strBranchCode = 
                        l_accOpenTelNumberDuplicationCheck.getBranchCode(l_objDuplicatedMobiles[i]);
                    
                    //�R�|�Q�|�P�|�P�j �R����ʂ̔���
                    String l_strReviewCode = null;
                    if (WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN.equals(l_strTableName)) 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP;
                    }
                    else 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN;
                    }

                    //�R�|�Q�|�Q�|�Q�j �d���ڋq����ۑ�  
                    //(set�����J�ݐR���҂���񃊃X�g()�j�B
                    this.setAccOpenJudgeWaitingInfoList(
                        l_strReviewCode, 
                        WEB3DuplicationDivDef.TELEPHONE_NO,
                        l_strBranchCode,
                        l_strAccountCode,
                        null,
                        null,
                        null,
                        null);
                }
            }
        
            //  �R�|�R�j  �R�|�Q�j�ɂ����ďd���d�b�ԍ������݂���ꍇ�A 
            l_lisWarnMsg.add(WEB3ErrorCatalog.WARNING_60013.getErrorCode());
        }
        
        //�S�j ArrayList��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_lisWarnMsg;         
    }
    

	/**
	 * (get�����J�ݐR���敪)<BR>
	 * �����J�ݐR���҂��e�[�u����ŏ،���ЃR�[�h�A���ʃR�[�h���L�[�Ƃ��� <BR>
     * ���R�[�h���������A�R���敪���擾���ԋp����B <BR>
     * ���R�[�h�����݂��Ȃ��ꍇ��null��ԋp����B <BR>
	 * <BR>
	 * <BR>
	 * (1) �����J�ݏ��쐬�T�[�r�X.to�����J�ݐR���҂�()<BR>
	 * �@@�@@��������J�ݐR���҂��I�u�W�F�N�g�𐶐�����B<BR> 
	 * <BR>
	 * (2) this.get���ʃR�[�h()�������Ƃ��A<BR>
	 * �@@�@@�����J�ݐR���҂��e�[�u�����Y���ڋq�̃��R�[�h���擾����B<BR> 
	 * �@@�@@�@@(2-1) �擾�������ʃR�[�h�𒷂��P��String�z��̗v�f�ɐݒ肷��B<BR>
	 * �@@�@@�@@(2-2)�����J�ݐR���҂�.select�R���Ώۈꗗ(get�،���ЃR�[�h(), (2-1)�ō쐬����String�z��)<BR>  
	 * <BR>
	 * (3) �����J�ݐR���҂����R�[�h�����݂���ꍇ�A�i�����J�ݐR���҂�.get�����J�ݐR���҂��s��() > 0 �j<BR> 
	 *      �擾�����s��1���R�[�h�ڂ̐R���敪���擾�i�����J�ݐR���҂�.get�R���敪(0)�j���A�ԋp����B <BR>
	 * <BR>
	 * (4) �����J�ݐR���҂����R�[�h�����݂��Ȃ��ꍇ <BR>
	 *     �i�����J�ݐR���҂�.get�����J�ݐR���҂��s��() == 0 �j�Anull��ԋp����B<BR>
	 * <BR>
	 * <BR>
     * @@return String                 
     * @@throws WEB3BaseException 
 	 */
    public String getAccountOpenStatusJudgeDiv() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "getAccountOpenStatusJudgeDiv()";
		log.entering(STR_METHOD_NAME);
       
		//(1) �����J�ݏ��쐬�T�[�r�X.to�����J�ݐR���҂�()����
		//�@@�@@�����J�ݐR���҂��I�u�W�F�N�g�𐶐�����B 
		WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
		Services.getService(WEB3AccOpenInfoCreatedService.class); 
        
		WEB3AccOpenJudgeWaiting l_accOpenJudgeWaiting = 
			     l_service.toAccOpenJudgeWaiting();
    
		//(2) this.get���ʃR�[�h()�������Ƃ��A
		//�����J�ݐR���҂��e�[�u�����Y���ڋq�̃��R�[�h���擾����B 
		//(2-1) �擾�������ʃR�[�h�𒷂��P��String�z��̗v�f�ɐݒ肷��B
		String[] l_strRequestNumbers = new String[1];
		l_strRequestNumbers[0] = this.getRequestNumber();
        
		//(2-2)�����J�ݐR���҂�.select�R���Ώۈꗗ(get�،���ЃR�[�h, (2-1)�ō쐬����String�z��)
		l_accOpenJudgeWaiting.selectJudgeObjectList(
				  this.getInstitutionCode(), l_strRequestNumbers);
        
		//(3) �����J�ݐR���҂����R�[�h�����݂���ꍇ�A�i�����J�ݐR���҂�.get�����J�ݐR���҂��s��() > 0 �j 
		//�擾�����s��1���R�[�h�ڂ̐R���敪���擾�i�����J�ݐR���҂�.get�R���敪(0)�j���A�ԋp����B 
		//(4) �����J�ݐR���҂����R�[�h�����݂��Ȃ��ꍇ 
		//	�i�����J�ݐR���҂�.get�����J�ݐR���҂��s��() == 0 �j�Anull��ԋp����B
		int l_intRecord = l_accOpenJudgeWaiting.getAccOpenWaitingParamsNumber();
        
		if (l_intRecord > 0) 
		{
			log.exiting(STR_METHOD_NAME);
			return l_accOpenJudgeWaiting.getCheckDiv(0);
		}
		else 
		{
			log.exiting(STR_METHOD_NAME);
			return null;  
		}
    }
    
    
    
    /**
     * (get�����J�ݏ󋵐R���敪)<BR>
     * �����J�ݏ󋵂𔻒肷��B<BR>
     * <BR>
     * [�߂�l]<BR>
     * �@@0�F�@@DEFAULT�i���J��)<BR>
     * �@@1�F�@@�J�ݒ�<BR>
     * �@@2�F�@@�G���[����<BR>
     * �@@3�F�@@�J�ݍ�<BR>
     * �@@4�F�@@�R����<BR>
     * �@@5�F�@@�p��<BR>
     * <BR>
     * <BR>
     * (1) �R���҂����R�[�h�������ithis.�����J�ݐR���敪()�j�擾�����R���敪�ɂ��ȉ��������s���B <BR> 
     * <BR>
     * �E�R���敪�� "0"�̏ꍇ��"4"��ԋp����i�R���҂��j�B <BR> 
     * �E�R���敪�� "2"�̏ꍇ��"5"��ԋp����i�p���j�B <BR> 
     * �E�R���敪��"1" or null �̏ꍇ�ˌ����J�݌����q.�����J�ݏ󋵋敪()�̖߂�l��ԋp����B<BR> 
     * @@return String                 
     * @@throws WEB3BaseException 
     */
    public String getAccountOpenStatusCheckDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountOpenStatusJudgeDiv()";
        log.entering(STR_METHOD_NAME);
       
            String l_strCheckDiv = getAccountOpenStatusJudgeDiv();
            
            //(1) �R���҂����R�[�h�������ithis.�����J�ݐR���敪()�j�擾�����R���敪�ɂ��ȉ��������s���B 
            //     �E�R���敪�� "0"�̏ꍇ��"4"��ԋp����i�R���҂��j�B 
            //     �E�R���敪�� "2"�̏ꍇ��"5"��ԋp����i�p���j�B 
            //     �E�R���敪��"1"���� 
            //       �R���敪��"1" or null �̏ꍇ�ˌ����J�݌����q.�����J�ݏ󋵋敪()�̖߂�l��ԋp����B 
 
            if (WEB3CheckDivDef.CHECK_WAITING.equals(l_strCheckDiv)) 
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountOpenStatusDivDef.JUDGEING;
            }
            else if (WEB3CheckDivDef.DISAGREE.equals(l_strCheckDiv)) 
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountOpenStatusDivDef.REJECTION;
            } 
            else
            {
                log.exiting(STR_METHOD_NAME);
                return this.getAccountOpenStatusDiv();  
            }
    }

    /**
     * (validate���[���A�h���X )<BR>
     * ���͂������[���A�h���X���o�^����Ă��Ȃ����`�F�b�N����B<BR> 
     * ���ɓo�^����Ă���ꍇ�A�ȉ��Q�̏������s���B<BR> 
     * �@@�x������ԋp����B <BR>
     * �A���X�v���t�@@�����X�̒l>1�̏ꍇ�����J�ݐR���҂���񃊃X�g�֏d���ڋq����ۑ�����B<BR> 
     * (set�����J�ݐR���҂���񃊃X�g()) <BR>
     * <BR>
     * �P�j �ڋq�}�X�^�Ƀ��[���A�h���X�����݂��邩�`�F�b�N����B<BR> 
     * �@@�P�|�P�j webbroker3.gentrade.���[���A�h���X�d���`�F�b�N�N���X���g�p����B<BR> 
     * <BR>
     * �@@�@@[���[���A�h���X�d���`�F�b�N.get�d���A�h���X()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@���[���A�h���X�Fthis.getemail�A�h���X()�̖߂�l <BR>
     * �@@�@@�@@�ڋq�R�[�h�Fthis.�����R�[�h()�̖߂�l <BR>
     * �@@�@@�@@���X�R�[�h�Fthis.get���X�R�[�h()�̖߂�l <BR>
     * �@@�@@�@@�،���ЃR�[�h�Fthis.get�،���ЃR�[�h()�̖߂�l <BR>
     * <BR>
     *   �P�|�Q�j �d���A�h���X�����݂���i���[���A�h���X�d���`�F�b�N.get�d���A�h���X()<BR>
     *   �@@�@@�@@�@@�̖߂�l�̒���>0�j�ꍇ <BR>
     *     �P�|�Q�|�P�j �i�����j���X�v���t�@@�����X > 1�̏ꍇ�A�d���ڋq����ۑ�����B<BR> 
     *                   (set�����J�ݐR���҂���񃊃X�g()�j�B <BR>
     *              <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�R����ʁF"1" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�d���敪�F"2" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�d�����X�R�[�h:���[���A�h���X�d���`�F�b�N.get���X�R�[�h()�̖߂�l<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�d���ڋq�R�[�h:���[���A�h���X�d���`�F�b�N.get�ڋq�R�[�h()�̖߂�l <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     * <BR>
     * �Q�j �����q�t�@@�C���Ƀ��[���A�h���X�����݂��邩�`�F�b�N���� <BR>
     * �@@�Q�|�P�j �����J�݃��[���A�h���X�d���`�F�b�N�N���X�𐶐�����B<BR> 
     * <BR>
     * �@@�@@[�����J�݋��ʃT�[�r�X#to�����J�݃��[���A�h���X�d���`�F�b�N()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�ڋq�R�[�h�Fthis.�����R�[�h()�̖߂�l <BR>
     * �@@�@@�@@���ʃR�[�h�Fthis.���ʃR�[�h()�̖߂�l <BR>
     * �@@�@@�@@�،���ЃR�[�h�Fthis.get�،���ЃR�[�h()�̖߂�l<BR> 
     *        <BR>
     * �@@�Q�|�Q�j �d�����[���A�h���X�̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@�i�����J�݃��[���A�h���X�d���`�F�b�N#get�d���ڋq���()�j<BR> 
     * <BR>
     * �@@�@@[�����J�݃��[���A�h���X�d���`�F�b�N#get�d���ڋq���()�Ɏw�肷�����] <BR>
     * �@@�@@�@@���[���A�h���X�Fthis.getemail�A�h���X()�̖߂�l <BR>
     * <BR>
     * �@@�Q�|�R�j �d���A�h���X�����݂���i�����J�݃��[���A�h���X�d���`�F�b�N.get�d���ڋq���()<BR>
     *   �@@�@@�@@�@@�@@�̖߂�l�̒���>0�j�ꍇ <BR>
     * �@@�@@�Q�|�R�|�P�j �i�����j���X�v���t�@@�����X > 1�̏ꍇ�A�d���ڋq����ۑ�����B<BR> 
     *                  (set�����J�ݐR���҂���񃊃X�g()�j�B <BR>
     *              <BR>
     * �@@�@@�@@�@@�@@�@@�@@[set�����J�ݐR���҂���񃊃X�g()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�R����ʁF"2" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�d���敪�F"2" <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�d�����X�R�[�h:�����J�݃��[���A�h���X�d���`�F�b�N#get���X�R�[�h()�̖߂�l<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�d���ڋq�R�[�h:�����J�݃��[���A�h���X�d���`�F�b�N#get�ڋq�R�[�h()�̖߂�l<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@Y�qID�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�Ǘ����X�R�[�h�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�Ɩ��敪�Fnull <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�Ǘ�No�D�Fnull <BR>
     * <BR>
     * �R�j �߂�l <BR>
     * <BR>
     *   �R�|�P�j  �P�|�Q�j�A�Q�|�R�j�̂��Âꂩ�ɉ����ďd���A�h���X�����݂����ꍇ�A<BR> 
     * �@@�@@�@@�uWARNING_60011�F�@@���[���A�h���X�d���̉\������v��ԋp����B <BR>
     * <BR>
     *   �R�|�Q�j  �P�|�Q�j�A�Q�|�R�j�̑S�Ăɉ����āA�d���A�h���X�����݂��Ȃ������ꍇ�A<BR>
     *   �@@�@@null��ԋp����B<BR>
     * @@param l_intBranchPreference - (���X�v���t�@@�����X)<BR>
     * ���X�v���t�@@�����X�l <BR>
     * <BR>
     * [�ݒ�l] <BR>
     * 0�F�`�F�b�N�s�v-�A���[�g�\����-�R���҂�UPDATE��<BR> 
     * 1�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE��<BR>
     * 2�F�`�F�b�N���s-�A���[�g�\����-�R���҂�UPDATE�L<BR> 
     * 3�F�`�F�b�N���s-�A���[�g�\���L-�R���҂�UPDATE�L<BR>
     * @@return String 
     * @@throws WEB3BaseException 
     */
    public String validateMailAddress(int l_intBranchPreference) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMailAddress(int)";
        log.entering(STR_METHOD_NAME);
       
        //�P�j �ڋq�}�X�^�Ƀ��[���A�h���X�����݂��邩�`�F�b�N����B
        // �P�|�P�j���[���A�h���X�d���`�F�b�N.get�d���A�h���X()
			//�d�l�ύX20060623SCSInomata-->
//	        Object[] l_objDuplicatedAddresses =
//            WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
//                this.getEmailAddress(),
//                this.getAccountCode(),
//                this.getBranchCode(),
//                this.getInstitutionCode());
			Object[] l_objDupliAdd =
			WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
				this.getEmailAddress(),
				null,
				this.getBranchCode(),
				this.getInstitutionCode());
			//<--�d�l�ύX20060623SCSInomata
		       
        boolean l_blnDuplicateFlag = false;
        
        String l_strAccountCode = null;
        String l_strBranchCode = null;
        
        //  �P�|�Q�j �d���A�h���X�����݂���ꍇ 
        if (l_objDupliAdd != null && l_objDupliAdd.length > 0) 
        {   
			//�d�l�ύX20060623SCSInomata-->
			//���[���A�h���X�d���`�F�b�N�N���X�֌ڋq�R�[�h���S��null�œn������
			//�ڋq�}�X�^�ɑ΂��錟���������،���ЃR�[�h&&���[���A�h���X�ƂȂ�A<BR>
			//�⍇������񂪊܂܂��\��������̂Ŗ⍇�����d�����̍폜���s���B
			Object[] l_objDuplicatedAddresses = 
				WEB3GentradeMailAddressDuplicationCheck.removeDuplicationInfo(
				l_objDupliAdd, 
				this.getAccountCode(), 
				this.getBranchCode());
			//�߂�l��null�Ŗ����ꍇ�������s
			if (l_objDuplicatedAddresses != null)
			{
			//<--�d�l�ύX20060623SCSInomata
				l_blnDuplicateFlag = true;
	            
				if (l_intBranchPreference > 1) 
				{         
					for (int i = 0; i < l_objDuplicatedAddresses.length; i++)
					{
						if (l_objDuplicatedAddresses[i] != null) 
						{
							l_strAccountCode = 
								WEB3GentradeMailAddressDuplicationCheck.getAccountCode(
									l_objDuplicatedAddresses[i]);
							l_strBranchCode = 
								WEB3GentradeMailAddressDuplicationCheck.getBranchCode(
									l_objDuplicatedAddresses[i]);
						}
						//set�����J�ݐR���҂���񃊃X�g()
						this.setAccOpenJudgeWaitingInfoList(
							WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
							WEB3DuplicationDivDef.MAIL_ADDRESS,
							l_strBranchCode,
							l_strAccountCode,
							null,
							null,
							null,
							null);
					}
				}
			}
        }
        
        //�Q�j �����q�t�@@�C���Ƀ��[���A�h���X�����݂��邩�`�F�b�N���� 
        //  �Q�|�P�j �����J�݃��[���A�h���X�d���`�F�b�N�N���X�𐶐�����B 
        WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
            Services.getService(WEB3AccOpenInfoCreatedService.class); 
        WEB3AccOpenMailAddressDuplicationCheck l_accOpenMailAddressDuplicationCheck =
            l_service.toAccOpenMailAddressDuplicationCheck(
                this.getAccountCode(),
                this.getRequestNumber(),
                this.getInstitutionCode());
        
        //  �Q�|�Q�j �d�����[���A�h���X�̃`�F�b�N���s���B�i�����J�݃��[���A�h���X�d���`�F�b�N#get�d���ڋq���()�j 
        Object[] l_objDuplicatedMailAddress = 
            l_accOpenMailAddressDuplicationCheck.getDuplicationAccountInfo(this.getEmailAddress());
        
        //  �Q�|�R�j �d���A�h���X�����݂���ꍇ 
        if (l_objDuplicatedMailAddress != null && l_objDuplicatedMailAddress.length > 0) 
        {
            l_blnDuplicateFlag = true;
            
            // �Q�|�R�|�P�j�i�����j���X�v���t�@@�����X > 1�̏ꍇ�A�d���ڋq����ۑ�����B 
            if (l_intBranchPreference > 1) 
            {
                for (int i = 0; i < l_objDuplicatedMailAddress.length; i++)
                {       
                    if (l_objDuplicatedMailAddress[i] != null) 
                    {
                        l_strAccountCode = 
                            WEB3GentradeMailAddressDuplicationCheck.getAccountCode(
                            	l_objDuplicatedMailAddress[i]);
                        l_strBranchCode = 
                            WEB3GentradeMailAddressDuplicationCheck.getBranchCode(
                            	l_objDuplicatedMailAddress[i]);
                    }
            
                    //set�����J�ݐR���҂���񃊃X�g()
                    this.setAccOpenJudgeWaitingInfoList(
                        WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP, 
                        WEB3DuplicationDivDef.MAIL_ADDRESS,
                        l_strBranchCode,
                        l_strAccountCode,
                        null,
                        null,
                        null,
                        null);
                }
            }     
        }
     
        //�R�j �߂�l 
        //  �R�|�P�j  �P�|�Q�j�A�Q�|�R�j�̂��Âꂩ�ɉ����ďd���A�h���X�����݂����ꍇ�A 
        //     �uWARNING_60011�F�@@���[���A�h���X�d���̉\������v��ԋp����B 
        if (l_blnDuplicateFlag)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60011.getErrorCode();
        }
        
        //  �R�|�Q�j  �P�|�Q�j�A�Q�|�R�j�̑S�Ăɉ����āA�d���A�h���X�����݂��Ȃ������ꍇ�Anull��ԋp����B 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (set�����R�[�h)<BR>
     * �����R�[�h���Z�b�g����B<BR>
     * this.�����J�݌����q�s.�����R�[�h�Ɉ���.�����R�[�h���Z�b�g���� <BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h�B<BR>
     */
    public void setAccountCode(String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(String)";
        log.entering(STR_METHOD_NAME);
        this.expAccountOpenParams.setAccountCode(l_strAccountCode);   
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete�����J�݌����q)<BR>
     * �����J�݌����q�e�[�u���̃��R�[�h�𕨗��폜����B<BR>
     * <BR>
     * �P�j�@@�����J�݌����q�s�I�u�W�F�N�g�擾<BR>
     * �@@�����J�݌����q.getDataSourceObject()�ɂČ����J�݌����q�s���擾����B<BR>
     * <BR>
     * �Q�j�@@DB�폜<BR>
     * �@@�����J�݌����q�e�[�u���������J�݌����q�s�I�u�W�F�N�g���폜�idelete�j����B<BR>
     * @@throws WEB3BaseException
     */
    public void deleteAccOpenExpAccountOpen() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteAccOpenExpAccountOpen()";
        log.entering(STR_METHOD_NAME);

        //�P�j�����J�݌����q�s�I�u�W�F�N�g�擾
        //�@@�@@�����J�݌����q.getDataSourceObject()�ɂČ����J�݌����q�s���擾����B
        ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.getDataSourceObject();

        try
        {
            //�Q�jDB�폜
            //�@@�@@�����J�݌����q�e�[�u���������J�݌����q�s�I�u�W�F�N�g���폜�idelete�j����B
            WEB3DataAccessUtility.deleteRow(l_expAccountOpenRow);
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�폜�\)<BR>
     * �����J�݌����q�f�[�^���폜�\�𔻒肷��B<BR>
     * <BR>
     * ���@@�����J�ݖ��J�݁ithis.get�����J�ݏ󋵋敪() �� 0�F�@@DEFAULT�i���J�݁j�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isDeletePossible()
    {
        final String STR_METHOD_NAME = "isDeletePossible()";
        log.entering(STR_METHOD_NAME);

        // �����J�ݖ��J�݁ithis.get�����J�ݏ󋵋敪() �� 0�F�@@DEFAULT�i���J�݁j�j�̏ꍇ�Atrue��ԋp����B
        // �ȊO�̏ꍇ�Afalse��ԋp����B
        if (WEB3AccountOpenStatusDivDef.DEFAULT.equals(this.getAccountOpenStatusDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get�ڋq���i�����j)<BR>
     * �ڋq���i�����j���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�ڋq���i�����j��ԋp����B<BR>
     * @@return String
     */
    public String getAccountFamilyName()
    {
        return this.expAccountOpenParams.getFamilyName();
    }

    /**
     * (get�ڋq���i�����j)<BR>
     * �ڋq���i�����j���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�ڋq���i�����j��ԋp����B<BR>
     * @@return String
     */
    public String getAccountName()
    {
        return this.expAccountOpenParams.getGivenName();
    }

    /**
     * (get�Z���P�i�J�i�j)<BR>
     * �Z���P�i�J�i�j���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�Z���P�i�J�i�j��ԋp����B<BR>
     * @@return String
     */
    public String getAddressKana1()
    {
        return this.expAccountOpenParams.getAddressLine1Kana();
    }

    /**
     * (get�Z���Q�i�J�i�j)<BR>
     * �Z���Q�i�J�i�j���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�Z���Q�i�J�i�j��ԋp����B<BR>
     * @@return String
     */
    public String getAddressKana2()
    {
        return this.expAccountOpenParams.getAddressLine2Kana();
    }

    /**
     * (get�Z���R�i�J�i�j)<BR>
     * �Z���R�i�J�i�j���擾����B<BR>
     * <BR>
     * this.�����J�݌����q�s.�Z���R�i�J�i�j��ԋp����B<BR>
     * @@return String
     */
    public String getAddressKana3()
    {
        return this.expAccountOpenParams.getAddressLine3Kana();
    }

    /**
     * (is�폜�ς�)<BR>
     * �����J�݌����q�f�[�^���폜�ς��ǂ����𔻒肷��B <BR>
     * <BR>
     * �P�j�����J�݌����q���擾����B<BR>
     * �@@�ȉ��̏����Ō����J�݌����q�e�[�u������������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = this.�����J�݌����q�s.�،���ЃR�[�h<BR>
     * �@@���ʃR�[�h =this.�����J�݌����q�s.���ʃR�[�h<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A�G���[���X���[����B<BR>
     * <BR>
     * �Q�j�擾���������J�݌����q�s.�폜�t���O���u1�FTRUE/�����i�폜�j�v�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�ȊO�̏ꍇ�Afalse��ԋp����B <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDeleted() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDeleted()";
        log.entering(STR_METHOD_NAME);

        ExpAccountOpenRow l_expAccountOpenRow = null;
        try
        {
            //[����]
            //�،���ЃR�[�h = this.�����J�݌����q�s.�،���ЃR�[�h
            //���ʃR�[�h =this.�����J�݌����q�s.���ʃR�[�h
            l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk(
                    this.expAccountOpenParams.getInstitutionCode(),
                    this.expAccountOpenParams.getAccOpenRequestNumber());
        }
        catch (DataFindException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " �����J�݌����q��null�ł���B");   
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

        //�Q�j�擾���������J�݌����q�s.�폜�t���O���u1�FTRUE/�����i�폜�j�v�̏ꍇ�Atrue��ԋp����
        if (BooleanEnum.TRUE.equals(l_expAccountOpenRow.getDeleteFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (save�����J�݌����q)<BR>
     * �����J�݌����q�e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j �����J�݌����q�s�I�u�W�F�N�g�擾<BR>
     * �@@�����J�݌����q.getDataSourceObject()�ɂČ����J�݌����q�s���擾����B<BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B<BR>
     * �@@�Q�|�P�j ����.�X�V���ڂ��u1�F����t���O�v�̏ꍇ�A<BR>
     * �@@�@@�����J�݌����q�s.����t���O�Ɉ���.�X�V�l���Z�b�g����B<BR>
     * �@@�Q�|�Q�j ����.�X�V���ڂ��u2�F��̃t���O�v�̏ꍇ�A<BR>
     * �@@�@@�����J�݌����q�s.��̃t���O�Ɉ���.�X�V�l���Z�b�g����B<BR>
     * �@@�Q�|�R�j ����.�X�V���ڂ��u3�F�폜�t���O�v�̏ꍇ�A<BR>
     * �@@�@@�����J�݌����q�s.�폜�t���O�Ɉ���.�X�V�l�A<BR>
     * �@@�@@�����J�݌����q�s.�폜�����Ɍ��ݓ������Z�b�g����B<BR>
     * �@@DB�X�V�d�l�u�����J�݌����qDB�X�V�iUpdate�j�d�l.xls�v�Q��<BR>
     * <BR>
     * �R�j DB�X�V<BR>
     * �@@�����J�݌����q�s�I�u�W�F�N�g�̓��e�ŁA�����J�݌����q�e�[�u�����X�V�iupdate�j����B<BR>
     * @@param l_strUpdateItem - (�X�V����)<BR>
     * �X�V����<BR>
     * @@param l_strUpdateValue - (�X�V�l)<BR>
     * �X�V�l<BR>
     * @@param l_strUpdateCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void saveExpAccountOpen(
        String l_strUpdateItem,
        String l_strUpdateValue,
        String l_strUpdateCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveExpAccountOpen(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �����J�݌����q�s�I�u�W�F�N�g�擾
        //�����J�݌����q.getDataSourceObject()�ɂČ����J�݌����q�s���擾����B
        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)this.getDataSourceObject();
        BooleanEnum l_booleanEnum =
            (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                BooleanEnum.class, Integer.parseInt(l_strUpdateValue));
        //�Q�j �X�V�����Z�b�g����B
        //DB�X�V�d�l�u�����J�݌����qDB�X�V�iUpdate�j�d�l.xls�v�Q��
        //�Q�|�P�j ����.�X�V���ڂ��u1�F����t���O�v�̏ꍇ�A
        //�����J�݌����q�s.����t���O�Ɉ���.�X�V�l���Z�b�g����B
        if (WEB3AccOpenUpdateItemDef.PRINT_CHANGE.equals(l_strUpdateItem))
        {
            l_expAccountOpenParams.setPrintFlag(l_strUpdateValue);
        }
        //�Q�|�Q�j ����.�X�V���ڂ��u2�F��̃t���O�v�̏ꍇ�A
        //�����J�݌����q�s.��̃t���O�Ɉ���.�X�V�l���Z�b�g����B
        else if (WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE.equals(l_strUpdateItem))
        {
            l_expAccountOpenParams.setReceiptFlag(l_booleanEnum);
        }
        //�Q�|�R�j ����.�X�V���ڂ��u3�F�폜�t���O�v�̏ꍇ�A
        //�����J�݌����q�s.�폜�t���O�Ɉ���.�X�V�l�A
        //�����J�݌����q�s.�폜�����Ɍ��ݓ������Z�b�g����B
        else if (WEB3AccOpenUpdateItemDef.DELETE_CHANGE.equals(l_strUpdateItem))
        {
            l_expAccountOpenParams.setDeleteFlag(l_booleanEnum);
            //�폜����:���ݓ���
            if (BooleanEnum.TRUE.equals(l_booleanEnum))
            {
                l_expAccountOpenParams.setDeleteTimestamp(GtlUtils.getSystemTimestamp());
            }
            else
            {
                l_expAccountOpenParams.setDeleteTimestamp(null);
            }
        }

        //�X�V�҃R�[�h:�Ǘ���.�Ǘ��҃R�[�h
        l_expAccountOpenParams.setLastUpdater(l_strUpdateCode);

        //�X�V����:���ݓ���
        l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //�R�j DB�X�V
        //�����J�݌����q�s�I�u�W�F�N�g�̓��e�ŁA�����J�݌����q�e�[�u�����X�V�iupdate�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_expAccountOpenParams);
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
     * ������̕ϊ�
     * @@param l_str
     * @@return
     */
    private String changeStr(String l_str)
    {
    	if(l_str == null)
    	{
    		l_str = "";
    	}
    	return l_str;
    }
}@
