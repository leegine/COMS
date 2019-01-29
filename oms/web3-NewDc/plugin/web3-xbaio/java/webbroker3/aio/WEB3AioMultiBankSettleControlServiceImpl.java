head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.29.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankSettleControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �}���`�o���N���ϐ���T�[�r�XImpl(WEB3AioMultiBankSettleControlServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
Revesion History : 2004/10/22 ��O�� (���u) ���r���[ 
Revesion History : 2006/04/13 �юu�� (���u) �d�l�ύX�E���f��527
Revesion History : 2006/04/26 WeiXin (���u) �d�l�ύX�E���f��542
Revesion History : 2006/05/07 �����iSCS�j�@@�d�l�ύX�E���f��551
Revesion History : 2007/06/19 �đo�g (���u)  �d�l�ύX�E���f�� No.727
Revesion History : 2007/07/28 �Ј��� (���u)  �d�l�ύX�E���f�� No.740
*/
package webbroker3.aio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.BankOrderRequestParams;
import webbroker3.aio.data.BankOrderRequestRow;
import webbroker3.aio.data.BankSettleResultResponseParams;
import webbroker3.aio.data.BankSettleResultResponseRow;
import webbroker3.aio.data.BankSettleStartRequestParams;
import webbroker3.aio.data.BankSettleStartRequestRow;
import webbroker3.aio.data.CompBankCareerManagementRow;
import webbroker3.aio.data.CompBankConditionRow;
import webbroker3.aio.data.CooperateBankMasterRow;
import webbroker3.aio.data.HostCashTransOrderAcceptRow;
import webbroker3.aio.define.WEB3AioTelegramFormatDef;
import webbroker3.aio.define.WEB3AioTelegramHttpRequestDef;
import webbroker3.aio.define.WEB3AioTelegramReturnCodeDef;
import webbroker3.aio.message.WEB3AioPrSessionUnit;
import webbroker3.aio.message.WEB3AioSelectSettleInstitutionUnit;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CareerDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3InputDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3ServiceDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�}���`�o���N���ϐ���T�[�r�XImpl)<BR>
 * �}���`�o���N���ϐ���T�[�r�X�����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AioMultiBankSettleControlServiceImpl
    implements WEB3AioMultiBankSettleControlService
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioMultiBankSettleControlServiceImpl.class);

    /**
     * @@roseuid 415A48C80022
     */
    public WEB3AioMultiBankSettleControlServiceImpl()
    {

    }

    /**
     * (get�I�����ϋ@@�֖���)<BR>
     * �Y������،���ЁA���X�Ŏ戵���Ă��錈�ϋ@@�ւ̈ꗗ�Ǝ�t�󋵂��擾����B<BR> 
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�I�����C�������jget�I�����ϋ@@�֖��ׁv  �Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strCareerDiv - (�L�����A�敪)<BR>
     * 2 : ���o�C���ȊO <BR>
     * 4 : i-mode <BR>
     * 5 : vodafone <BR>
     * 6 : au<BR>
     * @@return WEB3AioSelectSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 40F20CC8032E
     */
    public WEB3AioSelectSettleInstitutionUnit[] getSelectPaySchemeDetails(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strCareerDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getSelectPaySchemeDetails("
                + "String l_strInstitutionCode, "
                + "String l_strBranchCode,"
                + "String l_strCareerDiv)";
        log.entering(STR_METHOD_NAME);

        List l_lisCompBankCareerManagement = null;
        try
        {
            // 1) QueryProcessor�̎擾
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�����p
            String l_strWhere = "institution_code = ? and branch_code = ? and career_div = ? and mg_flg = ?";
            Object[] l_objWhere = { l_strInstitutionCode, l_strBranchCode, l_strCareerDiv, WEB3DealtDef.CAN_DEALT};

            // 2) ��������
            //[����] 
            //Row�^�C�v�F ��Еʋ��Z�@@�փL�����A�戵�s�I�u�W�F�N�g.TYPE 
            //Where�����F �hinsutitution_code=? and branch_code=?�h and career_div=?�h 
            //orderBy�F �hpay_scheme_id�h 
            //condition�F null 
            //���X�g�F Where�����ɃZ�b�g����l�̃��X�g(=[����.�،���ЃR�[�h, ����.���X�R�[�h,����.�L�����A�敪]) 
            l_lisCompBankCareerManagement =
                l_queryProcessor.doFindAllQuery(
                    CompBankCareerManagementRow.TYPE,
                    l_strWhere,
                    " pay_scheme_id ",
                    null,
                    l_objWhere);
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

        // 3) List�̐���
        List l_lisAioSelectSettleInstitutionUnit = new Vector();

        int l_intLength = 0;
        if (l_lisCompBankCareerManagement != null && l_lisCompBankCareerManagement.size() != 0)
        {
            l_intLength = l_lisCompBankCareerManagement.size();
        }

        // 4) List�̑S����loop
        for (int i = 0; i < l_intLength; i++)
        {
            CompBankCareerManagementRow l_compBankCareerManagementRow = 
                (CompBankCareerManagementRow) l_lisCompBankCareerManagement.get(i);

            // 4 - 1) �I�����ϋ@@�֖��׃C���X�^���X�𐶐�����
            WEB3AioSelectSettleInstitutionUnit l_aioSelectSettleInstitutionUnit =
                new WEB3AioSelectSettleInstitutionUnit();
            WEB3AioSettleInstitution l_aioSettleInstitution = null;

            // 4 - 2)��g���ϋ@@�փC���X�^���X�𐶐�����
            l_aioSettleInstitution =
                 new WEB3AioSettleInstitution(
                         l_compBankCareerManagementRow.getPaySchemeId());

            // 4 - 3)���ϋ@@�ւ���t���ԓ����ǂ������`�F�b�N����
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_orderManager =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();

            boolean l_blnFlag = false;

            l_blnFlag =
                l_orderManager.validatePaySchemeAcceptPossible(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_compBankCareerManagementRow.getPaySchemeId());

            // 4 - 4) ��g���ϋ@@�֖��ׂ𐶐����A�ǉ�����
            l_aioSelectSettleInstitutionUnit.paySchemeId =
                l_compBankCareerManagementRow.getPaySchemeId();
            l_aioSelectSettleInstitutionUnit.paySchemeName =
                l_aioSettleInstitution.getName();
            if (l_blnFlag)
            {
                l_aioSelectSettleInstitutionUnit.receptionStatus = WEB3ServiceDivDef.INT_ACCEPT;
            }
            else
            {
                l_aioSelectSettleInstitutionUnit.receptionStatus = WEB3ServiceDivDef.STOPPING;
            }
            l_lisAioSelectSettleInstitutionUnit.add(
                l_aioSelectSettleInstitutionUnit);
        }

        WEB3AioSelectSettleInstitutionUnit[] l_arrayAioSelectSettleInstitutionUnit =
            null;

        // toArray()
        l_arrayAioSelectSettleInstitutionUnit =
            new WEB3AioSelectSettleInstitutionUnit[l_lisAioSelectSettleInstitutionUnit.size()];
        l_lisAioSelectSettleInstitutionUnit.toArray(
            l_arrayAioSelectSettleInstitutionUnit);

        log.exiting(STR_METHOD_NAME);
        return l_arrayAioSelectSettleInstitutionUnit;
    }

    /**
     * (get��g���ϋ@@�֖���)<BR>
     * �Y������،���Ў戵���Ă��錈�ϋ@@�ւ̈ꗗ���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�I�����C�������jget��g���ϋ@@�֖��ׁv  �Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@return WEB3AioSettleInstitutionUnit
     * @@throws WEB3BaseException<BR>
     * @@roseuid 410A11B70137
     */
    public WEB3AioSettleInstitutionUnit[] getAffiliatedPaySchemeDetails(String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getAffiliatedPaySchemeDetails(" 
                + "String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisCompBankCondition = null;
        try
        {
            // 1) QueryProcessor�̎擾
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�����p
            String l_strWhere = " institution_code = ? ";
            Object[] l_objWhere = { l_strInstitutionCode };

            // 2) ��������
            l_lisCompBankCondition =
                l_queryProcessor.doFindAllQuery(
                    CompBankConditionRow.TYPE,
                    l_strWhere,
                    l_objWhere);
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

        // 3) List�̐���
        List l_lisAioSettleInstitutionUnit = new Vector();

        int l_intLength = 0;
        if (l_lisCompBankCondition != null && l_lisCompBankCondition.size() != 0)
        {
            l_intLength = l_lisCompBankCondition.size();
        }

        // 4) List�̑S����loop
        for (int i = 0; i < l_intLength; i++)
        {
            CompBankConditionRow l_compBankConditionRow = 
                (CompBankConditionRow) l_lisCompBankCondition.get(i);

            // 4 - 1) ��g���ϋ@@�֖��׃C���X�^���X�𐶐�����
            WEB3AioSettleInstitutionUnit l_aioSettleInstitutionUnit =
                new WEB3AioSettleInstitutionUnit();
            WEB3AioSettleInstitution l_aioSettleInstitution = null;

            // 4 - 2)��g���ϋ@@�փC���X�^���X�𐶐�����
            l_aioSettleInstitution =
                new WEB3AioSettleInstitution(
                    l_compBankConditionRow.getPaySchemeId());

            // 4 - 3)��g���ϋ@@�֖��ׂ́@@�������݂��邩�ǂ������f����
            
            l_aioSettleInstitutionUnit.paySchemeId =
                    l_compBankConditionRow.getPaySchemeId();
            l_aioSettleInstitutionUnit.paySchemeName =
                l_aioSettleInstitution.getName();

            if(!this.contain(
                l_lisAioSettleInstitutionUnit, l_aioSettleInstitutionUnit))
            {
                l_lisAioSettleInstitutionUnit.add(l_aioSettleInstitutionUnit);    
            }
        }

        WEB3AioSettleInstitutionUnit[] l_arrayAioSettleInstitutionUnit = null;

        // toArray()
        l_arrayAioSettleInstitutionUnit =
            new WEB3AioSettleInstitutionUnit[l_lisAioSettleInstitutionUnit.size()];
        l_lisAioSettleInstitutionUnit.toArray(
            l_arrayAioSettleInstitutionUnit);

        log.exiting(STR_METHOD_NAME);
        return l_arrayAioSettleInstitutionUnit;
    }

    /**
     * (get���o����)<BR>
     * �I�����C�����o���̏󋵂��擾����B<BR>
     * <BR>
     * �P�j�،���ЃR�[�h���擾����B<BR>
     *    ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     * <BR>
     * �Q�j���X�R�[�h���擾����B<BR>
     *    ����.�⏕����.get����X().getBranchCode()<BR>
     * <BR>
     * �R�j�ڋq�R�[�h���擾����B<BR>
     *    ����.�⏕����.getMainAccout().getAccountCode()<BR>
     * <BR>
     * �S�j���Z�@@�֘A�g���o���󋵃C���X�^���X�𐶐�����B<BR>
     *    ���Z�@@�֘A�g���o����(�،���ЃR�[�h, ���X�R�[�h, ���ʃR�[�h)<BR>
     * <BR>
     *    [����]<BR>
     *    �،���ЃR�[�h�F �P�j�Ŏ擾�����R�[�h<BR>
     *    ���X�R�[�h�F �Q�j�Ŏ擾�����R�[�h<BR>
     *    ���ʃR�[�h�F ����.���ʃR�[�h<BR>
     * <BR>
     * �T�j���o���`�[��t�L���[�e�[�u������A�ȉ��̏����̃f�[�^���擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    �f�[�^�R�[�h = "GI80C"<BR>
     *    �،���ЃR�[�h = [�P�j�Ŏ擾�����R�[�h]<BR>
     *    ���X�R�[�h = [�Q�j�Ŏ擾�����R�[�h]<BR>
     *    �ڋq�R�[�h = [�Q�j�Ŏ擾�����R�[�h]<BR>
     *    ���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �S�j���Z�@@�֘A�g���o���󋵂����t���O�Ɠ��o���`�[��t�L���[��<BR>
     * ���t���O�ƈ����̒�����Ԃ���<BR>
     *    ���݂̓��o���󋵂𔻒肵�A���̌��ʂ�ԋp����B<BR>
     * <BR>
     *    �ڍׂ́A�u���o���X�e�[�^�X�\���\.xls�v �Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * @@param l_orderStatus - (�������)<BR>
     * @@param l_strOrderCancleStatus - (��������敪)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 410600780252
     */
    public String getCashTransSituation(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber,
        OrderStatusEnum l_orderStatus,
        String l_strOrderCancleStatus) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getCashTransSituation("
                + "SubAccount l_subAccount, "
                + "String l_strOrderRequestNumber, "
                + "OrderTypeEnum l_orderStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�،���ЃR�[�h���擾����
        //    ����.�⏕����.getInstitution().getInstitutionCode()
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();

        // �Q�j���X�R�[�h���擾����
        //    ����.�⏕����.get����X().getBranchCode()
        String l_strBranchCode =
            l_subAccount.getMainAccount().getBranch().getBranchCode();

        // �R�j�ڋq�R�[�h���擾����
        //    ����.�⏕����.getMainAccout().getAccountCode()
        String l_strAccountCode =
            l_subAccount.getMainAccount().getAccountCode();

        // �S�j���Z�@@�֘A�g���o���󋵃C���X�^���X�𐶐�����
        //    ���Z�@@�֘A�g���o����(�،���ЃR�[�h, ���X�R�[�h, ���ʃR�[�h)
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus = null;

        l_aioFinInstitutionCashTransStatus =
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber);
        
        BankCashTransferStatusRow l_bankCashTransferStatusRow = 
            (BankCashTransferStatusRow) l_aioFinInstitutionCashTransStatus.getDataSourceObject();        

        // �T�j���o���`�[��t�L���[�e�[�u������A�ȉ��̏����̃f�[�^���擾����
        //    [����]
        //    �f�[�^�R�[�h = "GI80C"
        //    �،���ЃR�[�h = [�P�j�Ŏ擾�����R�[�h]
        //    ���X�R�[�h = [�Q�j�Ŏ擾�����R�[�h]
        //    �ڋq�R�[�h = [�Q�j�Ŏ擾�����R�[�h]
        //    ���ʃR�[�h = ����.���ʃR�[�h
        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_whereClause =
                " request_code = ? "
                    + "and institution_code = ? "
                    + "and branch_code = ? "
                    + "and account_code = ? "
                    + "and order_request_number = ? ";
            Object[] l_bindVars =
                {
                    WEB3HostRequestCodeDef.AIO_SLIP_ACCEPT,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strOrderRequestNumber };

            l_lisRows =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransOrderAcceptRow.TYPE,
                    l_whereClause,
                    l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(
                " DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search table HostCashTransOrderAccept",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(
                " DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search table HostCashTransOrderAccept",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

        HostCashTransOrderAcceptRow l_hostCashTransOrderAcceptRow = null;
        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            l_hostCashTransOrderAcceptRow = 
                (HostCashTransOrderAcceptRow) l_lisRows.get(0);
        }
        
        String l_strKeyTableStatus = null;
        String l_strTransactionStatus = l_bankCashTransferStatusRow.getTransactionStatus();
        String l_strOrderStatusFlag = l_bankCashTransferStatusRow.getOrderStatusFlag();
        String l_strStartStatusFlg = l_bankCashTransferStatusRow.getStartStatusFlag();
        String l_strResultStatusFlag = l_bankCashTransferStatusRow.getResultStatusFlag();
        
        if (l_hostCashTransOrderAcceptRow != null)
        {
            l_strKeyTableStatus = l_hostCashTransOrderAcceptRow.getStatus();
        }

        // �S�j���Z�@@�֘A�g���o���󋵂����t���O�Ɠ��o���`�[��t�L���[��
        // ���t���O�ƈ����̒�����Ԃ���
        //    ���݂̓��o���󋵂𔻒肵�A���̌��ʂ�ԋp����B
        log.debug("l_strTransactionStatus = " + l_strTransactionStatus);
        log.debug("l_strOrderStatusFlag = " + l_strOrderStatusFlag);
        log.debug("l_strStartStatusFlg = " + l_strStartStatusFlg);
        log.debug("l_strResultStatusFlag = " + l_strResultStatusFlag);
        log.debug("l_orderStatus = " + l_orderStatus);
        log.debug("l_strOrderCancleStatus = " + l_strOrderCancleStatus);
        log.debug("l_strKeyTableStatus = " + l_strKeyTableStatus);
        
        WEB3AioMutilBankStatusUtility l_statusUtility = new WEB3AioMutilBankStatusUtility();
        String l_strCashTransSituation =
            l_statusUtility.getStatus(
                l_strTransactionStatus,
                l_strOrderStatusFlag,
                l_strStartStatusFlg,
                l_strResultStatusFlag,
                (l_orderStatus == null) ? null : l_orderStatus.intValue() + "",
                l_strOrderCancleStatus,
                l_strKeyTableStatus);

        log.exiting(STR_METHOD_NAME);
        return l_strCashTransSituation;
    }

    /**
     * (get���Z�@@�֖�)<BR>
     * �I�����C�������̌��ϋ@@�ւ̖��̂��擾����B<BR>
     * <BR>
     * �P�j�،���ЃR�[�h���擾����B<BR>
     *    ����.�⏕����.getInstitution().getInstitutionCode()<BR>
     * <BR>
     * �Q�j���X�R�[�h���擾����B<BR>
     *    ����.�⏕����.get����X().getBranchCode()<BR>
     * <BR>
     * �R�j���Z�@@�֘A�g���o���󋵃C���X�^���X�𐶐�����B<BR>
     *    ���Z�@@�֘A�g���o����(�،���ЃR�[�h, ���X�R�[�h, ���ʃR�[�h)<BR>
     * <BR>
     *    [����]<BR>
     *    �،���ЃR�[�h�F �P�j�Ŏ擾�����R�[�h<BR>
     *    ���X�R�[�h�F �Q�j�Ŏ擾�����R�[�h<BR>
     *    ���ʃR�[�h�F ����.���ʃR�[�h<BR>
     * <BR>
     * �S�j��g���ϋ@@�փC���X�^���X�𐶐�����B<BR>
     *    ��g���ϋ@@�ցi���ϋ@@��ID�j<BR>
     * <BR>
     *    [����]<BR>
     *    ���ϋ@@��ID�F �R�j�Ŏ擾�������Z�@@�֘A�g���o����.���ϋ@@��ID<BR>
     * <BR>
     * �T�j��g���ϋ@@��.���̂�ԋp����B <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 410602C6039A
     */
    public String getFinInstitutionName(
        SubAccount l_subAccount,
        String l_strOrderRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getFinInstitutionName("
                + "SubAccount l_subAccount, "
                + "String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�،���ЃR�[�h���擾����
        //    ����.�⏕����.getInstitution().getInstitutionCode()
        String l_strInstitutionCode =
            l_subAccount.getInstitution().getInstitutionCode();

        // �Q�j���X�R�[�h���擾����
        //    ����.�⏕����.get����X().getBranchCode()
        String l_strBranchCode =
            l_subAccount.getMainAccount().getBranch().getBranchCode();

        // �R�j���Z�@@�֘A�g���o���󋵃C���X�^���X�𐶐�����
        //    ���Z�@@�֘A�g���o����(�،���ЃR�[�h, ���X�R�[�h, ���ʃR�[�h)
        WEB3AioFinInstitutionCashTransStatus l_aioFinInstitutionCashTransStatus = null;

        l_aioFinInstitutionCashTransStatus =
            new WEB3AioFinInstitutionCashTransStatus(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strOrderRequestNumber);

        // �S�j��g���ϋ@@�փC���X�^���X�𐶐�����
        //    ��g���ϋ@@�ցi���ϋ@@��ID�j
        WEB3AioSettleInstitution l_aioSettleInstitution = null;

        l_aioSettleInstitution = new WEB3AioSettleInstitution(
            ((BankCashTransferStatusRow)
                 l_aioFinInstitutionCashTransStatus.getDataSourceObject()).getPaySchemeId());

        // �T�j��g���ϋ@@��.���̂�ԋp����B         
        CooperateBankMasterRow l_cooperateBandMasterRow = 
            (CooperateBankMasterRow)l_aioSettleInstitution.getDataSourceObject();
            
        String l_strFinInstitutionName = l_cooperateBandMasterRow.getShortName();
            
        log.exiting(STR_METHOD_NAME);
        return l_strFinInstitutionName;
    }

    /**
     * (create���ψ˗�URL)<BR>
     * ���ψ˗��̍ۂɕԋp���郊�_�C���N�gURL�̕�����𐶐�����B<BR>
     * <BR>
     * �P�j�����XID�̎擾<BR>
     * <BR>
     *�@@�P�|�P�j�Z�b�V�������.�����o�H�敪���擾 <BR>
     *<BR>
     *�@@�P�|�Q�jthis.get�L�����A�敪�i�����o�H�敪�j�ŁA�L�����A�敪���擾����B<BR> 
     *<BR>
     *�@@�P�|�R�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B<BR> 
     * <BR>
     *    �m�R���X�g���N�^�̈����n<BR>
     *    �،���ЃR�[�h = �⏕����.getInstitution().getInsutitutionCode()<BR>
     *    ���X�R�[�h = �⏕����.get����X().getBranchCode()<BR>
     *   �L�����A�敪 = get�L�����A�敪�i�j�Ŏ擾�����l<BR> 
     *<BR>
     *�@@�P�|�S�j�����XID���擾����B<BR> 
     *�@@�@@�L�����A�ʉ����XID.get�����XID() <BR>
     * <BR>
     * �Q�jURL������̐���<BR>
     * �Q�|�P�j�p�����[�^������̐���<BR> 
     *  <BR> 
     *  protocolVersion='V1.0'<BR> 
     *  linked_1=�⏕����.getInstitution().getInsutitutionCode() <BR>
     *   + �⏕����.get����X().getBranchCode() + ����.���ʃR�[�h 
     *  shopId=�m�P�j�Ŏ擾���������XID�n<BR> 
     *  date=�X���b�h����擾�������ݎ��� <BR>
     * cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]<BR>
     * errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]<BR>
     * &ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]<BR>
     * pSId=����.���ϋ@@��ID <BR>
     * prsid=[*1] <BR>
     * praid=[*2] <BR>
     * praarsid=[*3] <BR>
     * prssid=[*4] <BR>
     * prdpdv=[*5] <BR>
     * apsid=[*6] <BR>
     * cpy=[*7] <BR>
     * btn=[*8] <BR>
     * acc=[*9] <BR>
     * req=[*10]  <BR>
     * rdiv=[*11] <BR>
     * web3Request=OrderDemand <BR>
     * <BR>
     * ��1 URL�F �L�����A�ʉ����XID.get���^�[��URL()�Ŏ擾�����l<BR>
     * <BR>
     * *1�F PR�w�ێ����.�Z�b�V�����L�[ <BR>
     * *2�F PR�w�ێ����.�A�v���P�[�V����ID <BR>
     * *3�F PR�w�ێ����.�Đ����T�[�r�XID <BR>
     * *4�F PR�w�ێ����.SSID <BR>
     * *5�F PR�w�ێ����.�\���敪 <BR>
     * *6�F AP�w�Z�b�V����ID <BR>
     * *7�F �،���ЃR�[�h�i �⏕����.getInstitution().getInsutitutionCode() �j<BR> 
     * *8�F ���X�R�[�h�i �⏕����.get����X().getBranchCode() �j <BR>
     * *9�F �ڋq�R�[�h�i �⏕����.getMainAccount().getAccountCode() �j <BR>
     * *10�F ���ʃR�[�h�i ����.���ʃR�[�h �j <BR>
     * *11�F�����o�H�敪�i�Z�b�V�������.�����o�H�敪 �j<BR>
     * <BR>
     * �Q�|�Q�jURL������̐���<BR> 
     * <BR>
     * �m����PF��URL�n?�m�Q�|�P�j�Ő��������p�����[�^������n<BR>
     * <BR>
     * ������PF��URL�F <BR>
     *     �L�����A�ʉ����XID.get����URL()�Ŏ擾�����l 
     * <BR>
     * @@param  l_prSessionUnit - (PR�w�ێ����)<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)<BR>
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4116E7350335
     */
    public String createSettlementRequestURL(
        WEB3AioPrSessionUnit l_prSessionUnit,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strOrderRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "createSettlementRequestURL("
                + "String l_strPRSessionId, "
                + "SubAccount l_subAccount, "
                + "String l_strPaySchemeId, "
                + "String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�����XID�̎擾
        WEB3AioCareerShopId l_aioCareerShopId = null;

        //�P�|�P�j�Z�b�V�������.�����o�H�敪���擾
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
        String l_strOrderRootDiv = l_opLoginSecurityService.getSessionProperty(
            WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        
        //�P�|�Q�jthis.get�L�����A�敪�i�����o�H�敪�j�ŁA�L�����A�敪���擾����B
        String l_strCareerDiv = getCareerDiv(l_strOrderRootDiv);
        
        //�P�|�R�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B        
        try
        {
            l_aioCareerShopId = new WEB3AioCareerShopId(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_strCareerDiv);
        } 
        catch (NotFoundException l_ex) 
        {
            log.error("Error In  createSettlementRequestURL()", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �P�|�S�j�����XID���擾����
        String l_strShopId =
            l_aioCareerShopId.getShopId();

        //=========remain zhou-yong NO.1 begin ==========
        
        // �Q�jURL������̐���     
        
        //��1 URL�F �L�����A�ʉ����XID.get���^�[��URL()�Ŏ擾�����l 
        String l_strURL_WEB3 = l_aioCareerShopId.getReturnURL();

        //*1�F PR�w�ێ����.�Z�b�V�����L�[
        String l_strwolfSession = l_prSessionUnit.wolfSession;
        
        //*2�F PR�w�ێ����.�A�v���P�[�V����ID 
        String l_strwolfAid = l_prSessionUnit.wolfAid;
        
        //*3�F PR�w�ێ����.�Đ����T�[�r�XID 
        String l_strregetServiceId = l_prSessionUnit.regetServiceId;
        
        //*4�F PR�w�ێ����.SSID
        String l_strWolfSsid = l_prSessionUnit.wolfSsid;
        
        //*5�F PR�w�ێ����.�\���敪
        String l_strDisplayDiv = l_prSessionUnit.displayDiv;
        
        //*6�F AP�w�Z�b�V����ID 
        //APSessionID�̎擾
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strAPSessionID =
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.SESSION_ID);
        
        //*7�F �،���ЃR�[�h�i �⏕����.getInstitution().getInsutitutionCode() �j
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        
        //*8�F ���X�R�[�h�i �⏕����.get����X().getBranchCode() �j
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        
        //*9�F �ڋq�R�[�h�i �⏕����.getMainAccount().getAccountCode() �j 
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        
        //*10�F ���ʃR�[�h�i ����.���ʃR�[�h �j         
        //*11�F�����o�H�敪�i�Z�b�V�������.�����o�H�敪 �j
        
        StringBuffer l_sbURL = new StringBuffer();

        // �Q�|�P�j�p�����[�^������̐���
        // protocolVersion=V1.0
        l_sbURL.append(WEB3AioTelegramFormatDef.protocolVersion).append("=")
            .append(WEB3AioTelegramHttpRequestDef.V1DOT0).append("&");

        // linked_1=�⏕����.getInstitution().getInsutitutionCode() + 
        // �⏕����.get����X().getBranchCode() + ����.���ʃR�[�h
        l_sbURL.append(WEB3AioTelegramFormatDef.linked_1).append("=");

        l_sbURL.append(l_subAccount.getInstitution().getInstitutionCode());

        l_sbURL.append(l_subAccount.getMainAccount().getBranch().getBranchCode());

        l_sbURL.append(l_strOrderRequestNumber).append("&");        

        // shopId=�m�P�j�Ŏ擾���������XID�n
        l_sbURL.append(WEB3AioTelegramFormatDef.shopId)
            .append("=").append(l_strShopId).append("&");

        // date=�X���b�h����擾�������ݎ���
        l_sbURL.append(WEB3AioTelegramFormatDef.date).append("=") 
            .append(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHHmmss")) 
            .append("&");

        //cancelURL=[URL]?io_rturl=2&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10]  
        l_sbURL.append(WEB3AioTelegramFormatDef.cancelURL).append("=");
        StringBuffer l_sbCancelURL = new StringBuffer();
        l_sbCancelURL.append(l_strURL_WEB3);
//            .append("?").append(WEB3AioTelegramFormatDef.io_rturl).append("=")
//            .append(WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS2).append("&") 
//            .append(WEB3AioTelegramFormatDef.wolfSessionKey).append("=").append(l_strwolfSession).append("&")
//            .append(WEB3AioTelegramFormatDef.aa_aid).append("=").append(l_strwolfAid).append("&")
//            .append(WEB3AioTelegramFormatDef.aa_rsid).append("=").append(l_strregetServiceId).append("&")
//            .append(WEB3AioTelegramFormatDef.ssid).append("=").append(l_strWolfSsid).append("&")
//            .append(WEB3AioTelegramFormatDef.aa_dpdv).append("=").append(l_strDisplayDiv).append("&")
//            .append(WEB3AioTelegramFormatDef.apsid).append("=").append(l_strAPSessionID).append("&")
//            .append(WEB3AioTelegramFormatDef.cpy).append("=").append(l_strInstitutionCode).append("&")
//            .append(WEB3AioTelegramFormatDef.btn).append("=").append(l_strBranchCode).append("&")
//            .append(WEB3AioTelegramFormatDef.acc).append("=").append(l_strAccountCode).append("&")
//            .append(WEB3AioTelegramFormatDef.req).append("=").append(l_strOrderRequestNumber);
        l_sbURL.append(URLEncoder.encode(l_sbCancelURL.toString())).append("&");

        //errorURL=[URL]?io_rturl=1&wolfSessionKey=[*1]&aa_aid=[*2]&aa_rsid=[*3]
        //&ssid=[*4]&aa_dpdv=[*5]&apsid=[*6]&cpy=[*7]&btn=[*8]&acc=[*9]&req=[*10] 
        l_sbURL.append(WEB3AioTelegramFormatDef.errorURL).append("=");
        StringBuffer l_sbErrorURL = new StringBuffer();
        l_sbErrorURL.append(l_strURL_WEB3).append("?")
            .append(WEB3AioTelegramFormatDef.io_rturl).append("=")
            .append(WEB3AioTelegramHttpRequestDef.IO_RTURL_ADDRESS1).append("&")
            .append(WEB3AioTelegramFormatDef.wolfSessionKey).append("=").append(l_strwolfSession).append("&")
            .append(WEB3AioTelegramFormatDef.aa_aid).append("=").append(l_strwolfAid).append("&")
            .append(WEB3AioTelegramFormatDef.aa_rsid).append("=").append(l_strregetServiceId).append("&")
            .append(WEB3AioTelegramFormatDef.ssid).append("=").append(l_strWolfSsid).append("&")
            .append(WEB3AioTelegramFormatDef.aa_dpdv).append("=").append(l_strDisplayDiv).append("&")
            .append(WEB3AioTelegramFormatDef.apsid).append("=").append(l_strAPSessionID).append("&")
            .append(WEB3AioTelegramFormatDef.cpy).append("=").append(l_strInstitutionCode).append("&")
            .append(WEB3AioTelegramFormatDef.btn).append("=").append(l_strBranchCode).append("&")
            .append(WEB3AioTelegramFormatDef.acc).append("=").append(l_strAccountCode).append("&")
            .append(WEB3AioTelegramFormatDef.req).append("=").append(l_strOrderRequestNumber);
        l_sbURL.append(URLEncoder.encode(l_sbErrorURL.toString())).append("&");

        //pSId=����.���ϋ@@��ID 
        l_sbURL.append(WEB3AioTelegramFormatDef.pSId).append("=").append(l_strPaySchemeId).append("&");

        //prsid=[*1] 
        l_sbURL.append(WEB3AioTelegramFormatDef.prsid).append("=").append(l_strwolfSession).append("&");

        //praid=[*2] 
        l_sbURL.append(WEB3AioTelegramFormatDef.praid).append("=").append(l_strwolfAid).append("&");

        //praarsid=[*3]
        l_sbURL.append(WEB3AioTelegramFormatDef.praarsid).append("=").append(l_strregetServiceId).append("&");

        //prssid=[*4] 
        l_sbURL.append(WEB3AioTelegramFormatDef.prssid).append("=").append(l_strWolfSsid).append("&");

        //prdpdv=[*5] 
        l_sbURL.append(WEB3AioTelegramFormatDef.prdpdv).append("=").append(l_strDisplayDiv).append("&");
        
        //apsid=[*6] 
        l_sbURL.append(WEB3AioTelegramFormatDef.apsid).append("=").append(l_strAPSessionID).append("&");

        //cpy=[*7] 
        l_sbURL.append(WEB3AioTelegramFormatDef.cpy).append("=").append(l_strInstitutionCode).append("&");

        //btn=[*8]
        l_sbURL.append(WEB3AioTelegramFormatDef.btn).append("=").append(l_strBranchCode).append("&");

        //acc=[*9] 
        l_sbURL.append(WEB3AioTelegramFormatDef.acc).append("=").append(l_strAccountCode).append("&");

        //req=[*10] 
        l_sbURL.append(WEB3AioTelegramFormatDef.req).append("=").append(l_strOrderRequestNumber).append("&");
        
        //rdiv=[*11]
        l_sbURL.append(WEB3AioTelegramFormatDef.rdiv).append("=").append(l_strOrderRootDiv).append("&");

        //=========remain zhou-yong NO.1 end ==========

        // web3Request=OrderDemand
        l_sbURL.append(WEB3AioTelegramFormatDef.web3Request).append("=")
            .append(WEB3AioTelegramHttpRequestDef.ORDERDEMAND);

        // �Q�|�Q�jURL������̐���
        //    �m����PF��URL�n?�m�Q�|�P�j�Ő��������p�����[�^������n
        //������PF��URL�F 
        //�L�����A�ʉ����XID.get����URL()�Ŏ擾�����l 
        String l_strPfUrl = l_aioCareerShopId.getPfURL();
        
        String l_strReturnURL = l_strPfUrl + "?" + l_sbURL.toString();
        
        log.exiting(STR_METHOD_NAME);
        return l_strReturnURL;
    }

    /**
     * (insert���o����)<BR>
     * ���ψ˗��̃X�e�[�^�X�ł̋��Z�@@�֘A�g���o���󋵃e�[�u���̃��R�[�h��ǉ�����B<BR>
     * <BR>
     * �P�j���Z�@@�֘A�g���o����Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^���Z�b�g����B<BR>
     * <BR>
     *   �ڍׂ́ADB�X�V�d�l�u���ψ˗�_���Z�@@�֘A�g���o���󋵃e�[�u��.xls�v<BR>�Q��<BR>
     * <BR>
     * �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����B<BR>
     * <BR>
     *   �m�����n<BR>
     *   ���Z�@@�֘A�g���o����Params�C���X�^���X<BR>
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strPaySchemeId - (���ϋ@@��ID)
     * @@param l_strNetAmount - (���z)<BR>
     * �������z<BR>
     * @@param l_datBizDate - (������)
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 40EA4347019F
     */
    public void insertCashTransSituation(
        Trader l_trader,
        SubAccount l_subAccount,
        String l_strPaySchemeId,
        String l_strNetAmount,
        Date l_datBizDate,
        String l_strOrderRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertCashTransSituation("
                + "Trader l_trader, "
                + "SubAccount l_subAccount, "
                + "String l_strPaySchemeId, "
                + "String l_strNetAmount, "
                + "Date l_datBizDate, "
                + "String l_strOrderRequestNumber)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_datBizDate == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j���Z�@@�֘A�g���o����Params�C���X�^���X�𐶐�����
        BankCashTransferStatusParams l_bankCashTransferStatusParams =
            new BankCashTransferStatusParams();

        // �Q�j�p�����[�^���Z�b�g����
        //   �ڍׂ́ADB�X�V�d�l�u���ψ˗�_���Z�@@�֘A�g���o���󋵃e�[�u��.xls�v�Q��
        l_bankCashTransferStatusParams.setInstitutionCode(
            l_subAccount.getInstitution().getInstitutionCode());
        l_bankCashTransferStatusParams.setBranchCode(
            l_subAccount.getMainAccount().getBranch().getBranchCode());
        l_bankCashTransferStatusParams.setAccountCode(
            l_subAccount.getMainAccount().getAccountCode());
        l_bankCashTransferStatusParams.setOrderRequestNumber(
            l_strOrderRequestNumber);
        l_bankCashTransferStatusParams.setPaySchemeId(l_strPaySchemeId);
        l_bankCashTransferStatusParams.setAmount(new Long(l_strNetAmount));
        l_bankCashTransferStatusParams.setOrderDateTime(
            GtlUtils.getSystemTimestamp());
        l_bankCashTransferStatusParams.setDeliveryScheduledDate(
            new Timestamp(l_datBizDate.getTime()));
        l_bankCashTransferStatusParams.setBaseDate(
            new Timestamp(l_datBizDate.getTime()));
            
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        String l_strInputDiv = null;        
        
        if(WEB3OrderRootDivDef.CALLCENTER.equals(l_strOrderRootDiv))
        {
            l_strInputDiv = WEB3InputDivDef.CALLCENTER;
        }
        else
        {
            if(WEB3OrderRootDivDef.PC.equals(l_strOrderRootDiv))
            {
                l_strInputDiv = WEB3InputDivDef.PC;
            }
            else
            {
                if(WEB3OrderRootDivDef.SLINGSHOT.equals(l_strOrderRootDiv))
                {
                    l_strInputDiv = WEB3InputDivDef.SLINGSHOT;
                }
                else
                {
                    if(WEB3OrderRootDivDef.I_MODE.equals(l_strOrderRootDiv))
                    {
                        l_strInputDiv = WEB3InputDivDef.I_MODE;
                    }
                    else
                    {
                        if(WEB3OrderRootDivDef.VODAFONE.equals(l_strOrderRootDiv))
                        {
                            l_strInputDiv = WEB3InputDivDef.J_PHONE;
                        }
                        else
                        {
                            if(WEB3OrderRootDivDef.AU.equals(l_strOrderRootDiv))
                            {
                                l_strInputDiv = WEB3InputDivDef.EZ_WEB;
                            }
                            else
                            {
                                if(WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
                                {
                                    l_strInputDiv = WEB3InputDivDef.HOST;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        l_bankCashTransferStatusParams.setInputDiv(l_strInputDiv);
        l_bankCashTransferStatusParams.setComondebiCaptureDate(null);
        l_bankCashTransferStatusParams.setCenterPayId(null);
        l_bankCashTransferStatusParams.setOrderStatusFlag(WEB3OrderStatusFlagDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setOrderRequestTime(null);
        l_bankCashTransferStatusParams.setOrderResponseTime(null);
        l_bankCashTransferStatusParams.setStartStatusFlag(WEB3StartStatusFlgDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setStartRequestTime(null);
        l_bankCashTransferStatusParams.setStartResponseTime(null);
        l_bankCashTransferStatusParams.setResultStatusFlag(WEB3ResultStatusFlagDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setResultRequestTime(null);
        l_bankCashTransferStatusParams.setResultResponseTime(null);
        l_bankCashTransferStatusParams.setTransactionStatus(WEB3TransactionStatusDef.NOT_DEAL);
        l_bankCashTransferStatusParams.setTransactionTime(null);
        l_bankCashTransferStatusParams.setBatchFlag(null);
        l_bankCashTransferStatusParams.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());
            
        String l_strLoginID = l_opLoginSec.getLoginId() + "";
        l_bankCashTransferStatusParams.setLastUpdateUser(l_strLoginID);

        // �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����
        try
        {
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.insertRow(l_bankCashTransferStatusParams);
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
     * (validate��M�d��)<BR>
     * ��M�d���f�[�^�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�d�����s���̃����[�g�A�h���X�`�F�b�N<BR>
     * <BR>
     * �P�|�P�j�V�X�e�����ɕێ�����}���`�o���N�̃����[�g�A�h���X���擾����B <BR>
     * <BR>
     * this.get�v���t�@@�����X()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.��M�d���f�[�^.get("cpy")�̖߂�l <BR>
     * �ݒ薼�́F �h_REMOTE_ADDRESS�h <BR>
     * <BR>
     * �P�|�Q�j����.��M�d���f�[�^.get("remoteAdd")�̖߂�l�ƂP�|�P�j�Ŏ擾�����l����v���Ȃ��ꍇ�A <BR>
     * "ERROR"��Ԃ��B <BR>
     * <BR>
     * �Q�j�Z�b�V����ID�`�F�b�N<BR>
     * <BR>
     *   ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.apsid)�̖߂�l��ID�Ƃ���Z�b�V������<BR>
     * ���݃`�F�b�N���s���B<BR>
     *   ���݂��Ȃ��ꍇ�A���łɃZ�b�V�������I�����Ă���ꍇ�́A<BR>
     * "ERROR"��Ԃ��B
     * <BR>
     * �Q�|�P�jLoginSession�e�[�u������A�Y���Z�b�V�����̃��R�[�h���擾����B<BR>
     * <BR>
     *      LoginSessionDao.findRowByPk�il_session_id)<BR>
     * <BR>
     *      [�����n<BR>
     *      l_session_id�F ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.apsid)�̖߂�l<BR>
     * <BR>
     * �Q�|�Q�j���R�[�h���擾�ł��Ȃ����� or <BR>
     * LoginSessionParams.getExpairationDateIsSet() = true �̏ꍇ�́A<BR>
     * "ERROR"��Ԃ��B<BR>
     * <BR>
     * �R�j�^�C���A�E�g�`�F�b�N <BR>
     * <BR>
     * �V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�����݂��Ȃ��ꍇ��"ERROR"��Ԃ��B<BR> 
     * �V�X�e���^�C���X�^���v������Z�@@�֘A�g���o���󋵃e�[�u��.�X�V���t��r�� <BR>
     * �^�C���A�E�g�l�𒴂��Ă���ꍇ�́A"ERROR"��Ԃ��B <BR>
     * <BR>
     * �R�|�P�j���Z�@@�֘A�g���o���󋵃e�[�u�����X�V�������擾 <BR>
     * <BR>
     * �R�|�Q�j�V�X�e���v���t�@@�����X�e�[�u�����A�^�C���A�E�g�l���擾����B <BR>
     * <BR>
     * this.get�v���t�@@�����X()���R�[������B <BR>
     * <BR>
     * [����] <BR>
     * �،���ЃR�[�h�F ����.��M�d���f�[�^.get("cpy")�̖߂�l <BR>
     * �ݒ薼�́F �h_AIO_TIMEOUT�h <BR>
     * <BR>
     * �R�|�R�j�V�X�e���v���t�@@�����X��背�R�[�h���擾�o���Ȃ����� or <BR>
     * �V�X�e���^�C���X�^���v �| ���Z�@@�֓��o���󋵃e�[�u��.�X�V���t > <BR>v
     *      XX_AIO_TIOMEOUT �̏ꍇ�́A"ERROR"��Ԃ��B<BR> 
     * <BR>
     * �� XX_AIO_TIOMEOUT�͕b�P�ʂŐݒ�B <BR>
     * �V�X�e���^�C���X�^���v �| ���Z�@@�֓��o���󋵃e�[�u��.�X�V���t�͕b�P�ʂł̔�r�Ƃ���B <BR>
     * <BR>
     * �S�j�d���_�u��`�F�b�N<BR>
     * <BR>
     * �S�|�P�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = OrderDemand �̏ꍇ<BR>
     * <BR>
     * �������v���e�[�u���ɓ������e�̓d�����Ȃ������`�F�b�N����B<BR>
     * �������e�̓d�����������ꍇ�́A"ERROR"��Ԃ��B<BR>
     * <BR>
     * �m���������n<BR>
     * �@@�����ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)�̖߂�l<BR>
     * �@@.com�f�r�b�g���ώ���ԍ� = ����.��M�d���f�[�^<BR>
     * �@@.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l<BR>
     * <BR>
     * �S�|�Q�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = SettleStart �̏ꍇ<BR>
     * <BR>
     * ���ϊJ�n�v���e�[�u���ɓ������e�̓d�����Ȃ������`�F�b�N����B<BR>
     * �������e�̓d�����������ꍇ�́A"ERROR"��Ԃ��B<BR>
     * <BR>
     * �m���������n<BR>
     * �@@�����ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)�̖߂�l<BR>
     * �@@.com�f�r�b�g���ώ���ԍ� = ����.��M�d���f�[�^<BR>
     * �@@.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l<BR>
     * <BR>
     * �S�|�R�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = SettleResult �̏ꍇ<BR>
     * <BR>
     * �@@���ό��ʒʒm�e�[�u���ɓ������e�̓d�����Ȃ������`�F�b�N����B<BR>
     * �@@�������e�̓d�����������ꍇ�́A"ERROR"��Ԃ��B<BR>
     * <BR>
     * �m���������n<BR>
     * �@@�����ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)�̖߂�l<BR>
     * �@@.com�f�r�b�g���ώ���ԍ� = ����.��M�d���f�[�^<BR>
     * �@@.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l<BR>
     * <BR>
     * �T�j�����XID�A�A�N�Z�X�L�[�̃`�F�b�N<BR>
     * <BR>
     * �T�|�P�j��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����B<BR>
     * <BR>
     *   �m�R���X�g���N�^�̈����n<BR>
     *   �،���ЃR�[�h�F ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.cpy)�̖߂�l<BR>
     *   ���X�R�[�h�F ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.btn)�̖߂�l<BR>
     *   ���ϋ@@��ID = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.paySchemeId)�̖߂�l<BR>
     * <BR>
     *�T�|�Q�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B <BR> 
     * <BR>
     *  �m�R���X�g���N�^�̈����n <BR> 
     *  �،���ЃR�[�h�F ����.��M�d���f�[�^.get("cpy")�̖߂�l <BR> 
     *  ���X�R�[�h�F ����.��M�d���f�[�^.get("btn")�̖߂�l  <BR>
     *  �L�����A�敪 = this.get�L�����A�敪�i����.��M�d���f�[�^.get("rdiv")�j�̖߂�l <BR> 
     * <BR>
     *  �L�����A�ʉ����XID.get�����XID()�̖߂�l != ����.��M�d���f�[�^.get("shopId")�̖߂�l �̏ꍇ�A <BR>
     *   "ERROR"��Ԃ��B<BR>
     * <BR>
     * �T�|�R�j<BR>
     *   ��Еʌ��ϋ@@�֏���.get�A�N�Z�X�L�[()�̖߂�l != <BR>
     * ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.accessKey)�̖߂�l �̏ꍇ�A<BR>
     *   "NG"��Ԃ��B<BR>
     * <BR>
     * �U�j�����ԍ��A���ϋ@@��ID�A���������̃`�F�b�N<BR>
     * <BR>
     * �U�|�P�j<BR>
     *   ���o����.�،���ЃR�[�h+���o����.���X�R�[�h+<BR>
     * ���o����.���ʃR�[�h != ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)��<BR>
     * �߂�l<BR>
     *   �̏ꍇ�A"ERROR"��Ԃ��B<BR>
     * <BR>
     * �U�|�Q�j<BR>
     *   ���o����.���ϋ@@��ID != <BR>
     * ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.paySchemeId)�̖߂�l ��<BR>
     * �ꍇ�A"ERROR"��Ԃ��B<BR>
     * <BR>
     * �U�|�R�j<BR>
     *   ���o����.�������� != ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.date)��<BR>
     * �߂�l �̏�<BR>���A"ERROR"��Ԃ��B<BR>
     * <BR>
     * �V�j.com�f�r�b�g���ώ���ԍ��̃`�F�b�N<BR>
     * <BR>
     * �V�|�P�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = <BR>
     * SettleStart or ����.��M�d���f�[�^.get("web3Request")�̖߂�l = <BR>
     * SettleResult �̏ꍇ<BR>
     * <BR>
     *   ���o����..com�f�r�b�g���ώ���ԍ� != 
     *   ����.��M�d���f�[<BR>.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l �̏ꍇ�A�hNG"��Ԃ��B<BR>
     * <BR>
     * �W�j������Ԃ̃`�F�b�N<BR>
     * <BR>
     * �W�|�P�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = <BR>
     * SettleStart ��<BR>�ꍇ<BR>
     * <BR>
     *   ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.payStatus)�̖߂�l !=<BR>
     *  "START" �̏ꍇ�A�hNG"��Ԃ��B<BR>
     * <BR>
     * �X�j���z�̃`�F�b�N<BR> 
     * <BR>
     * �X�|�P�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = SettleResult and <BR>
     * ����.��M�d���f�[�^.get("payStatus")�̖߂�l != "ERROR" �̏ꍇ <BR>
     * ���o����.���z != ����.��M�d���f�[�^.get("amount")�̖߂�l �̏ꍇ�A�hNG"��Ԃ��B <BR>
     * <BR>
     * �P�O�j�v���g�R���o�[�W�����̃`�F�b�N<BR>
     * <BR>
     *   ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.protocolVersion)�̖߂�l != <BR>
     * "V1.0" �̏ꍇ�A�hERROR"��Ԃ��B<BR>
     * <BR>
     * �P�P�j"OK"��Ԃ��B<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)<BR>
     * @@param l_bankCashTransferStatusParams - (���o����)<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g<BR>
     * @@return String
     * @@roseuid 4117412202F7
     */
    public String validateReceiptTelegram(
        HashMap l_receiptTelegramData,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
    {
        String STR_METHOD_NAME =
            "validateReceiptTelegram("
                + "HashMap l_receiptTelegramData, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null || l_cashTransStatus == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        BankCashTransferStatusRow l_bankCashTransferStatusRow = 
            (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();    

        // �P�j�d�����s���̃����[�g�A�h���X�`�F�b�N
        //   ����.��M�d���f�[�^.get("remoteAdd")�̖߂�l�ƃV�X�e������
        // ��`����Ă���}���`�o���N�̃����[�g�A�h���X����v���Ȃ��ꍇ�A
        //   "ERROR"��Ԃ��B        
//        if(!WEB3AioTelegramHttpRequestDef.REMOTE_ADDRESS.equals(
//            l_receiptTelegramData.get(WEB3AioTelegramFormatDef.remoteAdd)))
//        {
//            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
//            log.error("�A�N�Z�X�s���G���[");
//            log.error("From Address : " + l_receiptTelegramData.get(WEB3AioTelegramFormatDef.remoteAdd).toString());
//            log.exiting(STR_METHOD_NAME);
//            return WEB3AioTelegramReturnCodeDef.ERROR;
//        }

        //�P�|�P�j�V�X�e�����ɕێ�����}���`�o���N�̃����[�g�A�h���X���擾����B 
        //this.get�v���t�@@�����X()���R�[������B 
        //[����] 
        //�،���ЃR�[�h�F ����.��M�d���f�[�^.get("cpy")�̖߂�l 
        //�ݒ薼�́F �h_REMOTE_ADDRESS�h
        /*
        String l_strInstitutionCode = (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy);
        String l_strPreference = null;
        try
        {
            l_strPreference = this.getPreference(
                l_strInstitutionCode, 
                WEB3AioTelegramHttpRequestDef.REMOTE_ADDRESS);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�|�Q�j����.��M�d���f�[�^.get("remoteAdd")�̖߂�l�ƂP�|�P�j�Ŏ擾�����l����v���Ȃ��ꍇ�A 
        //"ERROR"��Ԃ��B 
        String l_strRemoteAdd = 
            (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.remoteAdd);
        if (!l_strRemoteAdd.equals(l_strPreference))
        {
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        */
        
        // �Q�j�Z�b�V����ID�`�F�b�N
        //   ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.apsid)�̖߂�l��ID�Ƃ���Z�b�V�����̑��݃`�F�b�N���s��
        //   ���݂��Ȃ��ꍇ�A���łɃZ�b�V�������I�����Ă���ꍇ�́A
        // "ERROR"��Ԃ��B
        long l_strSessionID =
            Long.parseLong((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.apsid));

        try
        {
            // �Q�|�P�jLoginSession�e�[�u������A�Y���Z�b�V�����̃��R�[�h���擾����B
            //      LoginSessionDao.findRowByPk�il_session_id)
            //      [�����n
            //      l_session_id�F ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.apsid)�̖߂�l
            LoginSessionRow l_LoginSessionRow =
                LoginSessionDao.findRowByPk(l_strSessionID);             
            
            Date l_datExpirationDate = l_LoginSessionRow.getExpirationDate();
            Date l_datSystemDate = GtlUtils.getSystemTimestamp();
            
            // �Q�|�Q�j���R�[�h���擾�ł��Ȃ����� or 
            // LoginSessionParams.getExpairationDate() < �V�X�e���^�C���X�^���v �̏ꍇ�́A 
            // "ERROR"��Ԃ��B
            if (l_LoginSessionRow == null 
                || l_datSystemDate.compareTo(l_datExpirationDate) >= 0)
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                log.debug("session_id = " + l_strSessionID);
                log.debug("sessionRow = " + l_LoginSessionRow);
                log.debug("expairationDate = " + l_datExpirationDate);
                log.debug("systemDate = " + l_datSystemDate);
                log.error("�Z�b�V�����G���[");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.ERROR;
            }
        }
        catch (DataFindException l_ex)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
            log.error("�Z�b�V�����G���[ ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        //�R�j�^�C���A�E�g�`�F�b�N 
        //�R�|�P�j���Z�@@�֘A�g���o���󋵃e�[�u�����X�V�������擾
        Date l_datUpdTimestamp = l_bankCashTransferStatusRow.getLastUpdateTimestamp();
        
        //�R�|�Q�j�V�X�e���v���t�@@�����X�e�[�u�����A�^�C���A�E�g�l���擾����B 
        //this.get�v���t�@@�����X()���R�[������B 
        //[����] 
        //�،���ЃR�[�h�F ����.��M�d���f�[�^.get("cpy")�̖߂�l 
        //�ݒ薼�́F �h_AIO_TIMEOUT�h 
        String l_strTimeoutValue = null;
        try
        {
            l_strTimeoutValue = this.getPreference(
                (String)l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy), 
                WEB3AioTelegramHttpRequestDef.AIO_TIMEOUT);        
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
			log.exiting(STR_METHOD_NAME);
			return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        
        //�R�|�R�j�V�X�e���v���t�@@�����X��背�R�[�h���擾�o���Ȃ����� or 
        //�V�X�e���^�C���X�^���v �| ���Z�@@�֓��o���󋵃e�[�u��.�X�V���t > 
        //      XX_AIO_TIOMEOUT �̏ꍇ�́A"ERROR"��Ԃ��B 
        //�� XX_AIO_TIOMEOUT�͕b�P�ʂŐݒ�B 
        //�V�X�e���^�C���X�^���v �| ���Z�@@�֓��o���󋵃e�[�u��.�X�V���t�͕b�P�ʂł̔�r�Ƃ���B
        
        long l_lngDateRange = 
            GtlUtils.getSystemTimestamp().getTime() - l_datUpdTimestamp.getTime();
        
        long l_lngTimeoutValueSec = Long.parseLong(l_strTimeoutValue);
        long l_lngTimeoutValue = l_lngTimeoutValueSec * 1000L;
        
        if (WEB3StringTypeUtility.isEmpty(l_strTimeoutValue) || 
                (l_lngDateRange > l_lngTimeoutValue))
        {
			log.debug("NOTICE : --> --> --> --> --> --> Into Case 19");
			log.debug("dateRange = " + l_lngDateRange);
			log.debug("timeoutValue = " + l_lngTimeoutValue);
			log.error("�^�C���A�E�g�G���[");
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }        
        
        // �S�j�d���_�u��`�F�b�N
        try
        {
            // QueryProcessor�̎擾
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();

            //����.��M�d���f�[�^.get("web3Request")�̖߂�l = OrderDemand �̏ꍇ
            if (WEB3AioTelegramHttpRequestDef.ORDERDEMAND.equals(
                l_receiptTelegramData.get(WEB3AioTelegramFormatDef.web3Request)))
            {
                //�����p
                String l_strWhere = "linked_1 = ? and center_pay_id = ? ";

                //�m���������n
                //�����ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)�̖߂�l
                //.com�f�r�b�g���ώ���ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l
                Object[] l_wheres =
                {
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1),
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)
                };

                // ��������
                List l_lisRows =
                    l_queryProcessor.doFindAllQuery(
                        BankOrderRequestRow.TYPE,
                        l_strWhere,
                        l_wheres);

                //�������v���e�[�u���ɓ������e�̓d�����Ȃ������`�F�b�N����B
                //�������e�̓d�����������ꍇ�́A"ERROR"��Ԃ��B
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    log.debug("�d���_�u��G���[");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }
            //����.��M�d���f�[�^.get("web3Request")�̖߂�l = SettleStart �̏ꍇ
            else if (WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(
                l_receiptTelegramData.get(WEB3AioTelegramFormatDef.web3Request)))
            {
                //�����p
                String l_strWhere = "linked_1 = ? and center_pay_id = ? ";

                //�m���������n
                //�����ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)�̖߂�l
                //.com�f�r�b�g���ώ���ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l
                Object[] l_wheres =
                {
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1),
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)
                };

                // ��������
                List l_lisRows =
                    l_queryProcessor.doFindAllQuery(
                        BankSettleStartRequestRow.TYPE,
                        l_strWhere,
                        l_wheres);

                //���ϊJ�n�v���e�[�u���ɓ������e�̓d�����Ȃ������`�F�b�N����B
                //�������e�̓d�����������ꍇ�́A"ERROR"��Ԃ��B
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    log.debug("�d���_�u��G���[");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }
            //����.��M�d���f�[�^.get("web3Request")�̖߂�l = SettleResult �̏ꍇ
            else if (WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(
                l_receiptTelegramData.get(WEB3AioTelegramFormatDef.web3Request)))
            {
                //�����p
                String l_strWhere = "linked_1 = ? and center_pay_id = ? ";

                //�m���������n
                //�����ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)�̖߂�l
                //.com�f�r�b�g���ώ���ԍ� = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l
                Object[] l_wheres =
                {
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1),
                    l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)
                };

                // ��������
                List l_lisRows =
                    l_queryProcessor.doFindAllQuery(
                        BankSettleResultResponseRow.TYPE,
                        l_strWhere,
                        l_wheres);

                //���ό��ʒʒm�e�[�u���ɓ������e�̓d�����Ȃ������`�F�b�N����B
                //�������e�̓d�����������ꍇ�́A"ERROR"��Ԃ��B
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    log.debug("�d���_�u��G���[");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.ERROR;
                }
            }

        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // �T�j�����XID�A�A�N�Z�X�L�[�̃`�F�b�N
        WEB3AioCompanySettleInstitutionConditions l_aioCompanySettleInstitutionConditions =
            null;
        try
        {
            // �T�|�P�j��Еʌ��ϋ@@�֏����C���X�^���X�𐶐�����B
            //   �m�R���X�g���N�^�̈����n
            //   �،���ЃR�[�h�F ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.cpy)�̖߂�l
            //   ���X�R�[�h�F ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.btn)�̖߂�l
            //   ���ϋ@@��ID = ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.paySchemeId)�̖߂�l
            l_aioCompanySettleInstitutionConditions =
                new WEB3AioCompanySettleInstitutionConditions(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 8");
            log.debug("��g���ϋ@@�փC���X�^���X�𐶐��ł��Ȃ�", l_ex);
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        //�T�|�Q�j�L�����A�ʉ����XID�C���X�^���X�𐶐�����B 
        //
        //  �m�R���X�g���N�^�̈����n 
        //  �،���ЃR�[�h�F ����.��M�d���f�[�^.get("cpy")�̖߂�l 
        //  ���X�R�[�h�F ����.��M�d���f�[�^.get("btn")�̖߂�l 
        //  �L�����A�敪 = this.get�L�����A�敪�i����.��M�d���f�[�^.get("rdiv")�j�̖߂�l 
        //
        //  �L�����A�ʉ����XID.get�����XID()�̖߂�l != ����.��M�d���f�[�^.get("shopId")�̖߂�l �̏ꍇ�A 
        //  "ERROR"��Ԃ��B 
        WEB3AioCareerShopId l_aioCareerShopIds = null;            
        try {
            l_aioCareerShopIds =
                new WEB3AioCareerShopId(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.cpy),
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.btn),
//                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv));
                    this.getCareerDiv((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.rdiv)));
        } catch (WEB3BaseException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        } catch (NotFoundException l_ex) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }
            if (!l_aioCareerShopIds.getShopId().equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId)))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 9");
                log.error("�s���d���G���[(�����XID�̕s��v)");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.ERROR;
            }

            // �T�|�R�j
            //   ��Еʌ��ϋ@@�֏���.get�A�N�Z�X�L�[()�̖߂�l != 
            // ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.accessKey)�̖߂�l �̏ꍇ�A
            //   "NG"��Ԃ��B
            if (!l_aioCompanySettleInstitutionConditions.getAccessKey().equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey)))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 10");
                log.error("�s���d���G���[(�A�N�Z�X�L�[�̕s��v)");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.NG;
            }

        // �U�j�����ԍ��A���ϋ@@��ID�A���������̃`�F�b�N
        // �U�|�P�j
        //    ���o����.�،���ЃR�[�h+���o����.���X�R�[�h+
        // ���o����.���ʃR�[�h != ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.linked_1)��
        // �߂�l�̏ꍇ�A"ERROR"��Ԃ��B
        if (!(l_bankCashTransferStatusRow.getInstitutionCode()
                 + l_bankCashTransferStatusRow.getBranchCode()
                 + l_bankCashTransferStatusRow.getOrderRequestNumber()).equals(
                     (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 11");
            log.error("�s���d���G���[(��v���钍���ԍ����V�X�e�����ɑ��݂��Ȃ��ꍇ)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // �U�|�Q�j
        //   ���o����.���ϋ@@��ID != 
        // ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.paySchemeId)�̖߂�l ��
        // �ꍇ�A"ERROR"��Ԃ��B
        if (!l_bankCashTransferStatusRow.getPaySchemeId().equals(
                (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 12");
            log.error("�s���d���G���[(���ϋ@@��ID���s��v)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // �U�|�R�j
        //   ���o����.�������� != ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.date)��
        // �߂�l �̏�<BR>���A"ERROR"��Ԃ��B
        String l_strDate = WEB3DateUtility.formatDate(l_bankCashTransferStatusRow.getOrderDateTime(),"yyyyMMddHHmmss");
        if (!l_strDate.equals((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 13");
            log.error("�s���d���G���[(�����������s��v)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // �V�j.com�f�r�b�g���ώ���ԍ��̃`�F�b�N
        // �V�|�P�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = 
        // SettleStart or ����.��M�d���f�[�^.get("web3Request")�̖߂�l = 
        // SettleResult �̏ꍇ
        //   ���o����..com�f�r�b�g���ώ���ԍ� != 
        //   ����.��M�d���f�[.get(WEB3AioTelegramFormatDef.centerPayId)�̖߂�l �̏ꍇ�A�hNG"��Ԃ��B
        if ((WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(
                (String) l_receiptTelegramData.get(
                    WEB3AioTelegramFormatDef.web3Request)))
            || (WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(
                    (String) l_receiptTelegramData.get(
                        WEB3AioTelegramFormatDef.web3Request))))
        {
            if(l_bankCashTransferStatusRow.getCenterPayId() == null )
            {
                if((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId) != null)
                {
                    log.debug("NOTICE : --> --> --> --> --> --> Into Case 18");
                    log.error("�s���d���G���[(.com�f�r�b�g���ώ���ԍ����s��v)");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.NG;                    
                }
            }
            else
            {
                if(!l_bankCashTransferStatusRow.getCenterPayId().equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId)))
                {
                    log.debug("NOTICE : --> --> --> --> --> --> Into Case 14");
                    log.error("�s���d���G���[(.com�f�r�b�g���ώ���ԍ����s��v)");
                    log.exiting(STR_METHOD_NAME);
                    return WEB3AioTelegramReturnCodeDef.NG;
                }
            }
        }

        // �W�j������Ԃ̃`�F�b�N
        // �W�|�P�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = 
        // SettleStart �̏ꍇ
        //    ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.payStatus)�̖߂�l !=
        //   "START" �̏ꍇ�A�hNG"��Ԃ��B
        if ((WEB3AioTelegramHttpRequestDef.SETTLE_START.equals(
                (String) l_receiptTelegramData.get(
                    WEB3AioTelegramFormatDef.web3Request)))
            && (!WEB3AioTelegramHttpRequestDef.START.equals(
                    (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus))))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 15");
            log.error("�s���d���G���[(������Ԃ��s��)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.NG;
        }

        // �X�j���z�̃`�F�b�N
        // �X�|�P�j����.��M�d���f�[�^.get("web3Request")�̖߂�l = SettleResult and 
        // ����.��M�d���f�[�^.get("payStatus")�̖߂�l != "ERROR" �̏ꍇ 

        if ((WEB3AioTelegramHttpRequestDef.SETTLE_RESULT.equals(
                (String) l_receiptTelegramData.get(
                    WEB3AioTelegramFormatDef.web3Request)))
            && (!WEB3AioTelegramHttpRequestDef.ERROR.equals(
                (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus))))
        {
            // ���o����.���z != ����.��M�d���f�[�^.get("amount")�̖߂�l �̏ꍇ�A�hNG"��Ԃ��B 
            long l_lngAmount = 
                Long.parseLong((String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.amount));
                
            if (l_bankCashTransferStatusRow.getAmount() != l_lngAmount)
            {
                log.debug("NOTICE : --> --> --> --> --> --> ���z���s��");
                log.error("�s���d���G���[(���z���s��)");
                log.exiting(STR_METHOD_NAME);
                return WEB3AioTelegramReturnCodeDef.NG;
            }
        }
        
        // �P�O�j�v���g�R���o�[�W�����̃`�F�b�N<BR>
        //   ����.��M�d���f�[�^.get(WEB3AioTelegramFormatDef.protocolVersion)�̖߂�l != <BR>
        // "V1.0" �̏ꍇ�A�hERROR"��Ԃ��B<BR>
        if (!WEB3AioTelegramHttpRequestDef.V1DOT0.equals(
                (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion)))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 16");
            log.error("�s���d���G���[(�v���g�R���o�[�W�������s��)");
            log.exiting(STR_METHOD_NAME);
            return WEB3AioTelegramReturnCodeDef.ERROR;
        }

        // �P�P�j"OK"��Ԃ��B

        log.debug("NOTICE : --> --> --> --> --> --> Into Case 17");
        log.exiting(STR_METHOD_NAME);
        return WEB3AioTelegramReturnCodeDef.OK;
    }

    /**
     * (create���M�d��)<BR>
     * ���M�d���𐶐�����B<BR>
     * <BR>
     * �P�j���X�|���X�f�[�^��ContentType��ݒ肷��B<BR>
     *    ���X�|���X�f�[�^.setContentType("text/plain")<BR>
     * <BR>
     * �Q�j���X�|���X�f�[�^����AWriter���擾����B<BR>
     * <BR>
     * �R�j���X�|���X�̃��b�Z�[�W�{�f�B�Ɉȉ��̕�������o�͂���B<BR>
     * <BR>
     *    "&lt;SHOPMSG&gt;"<BR>
     * <BR>
     * �S�j����.���M�d���f�[�^�̑S�v�f�����X�|���X�̃��b�Z�[�W�{�f�B�ɏo�͂���B<BR>
     * <BR>
     * �T�j���X�|���X�̃��b�Z�[�W�{�f�B�Ɉȉ��̕�������o�͂���B<BR>
     * <BR>
     *    "&lt;/SHOPMSG&gt;"<BR>
     * <BR>
     * �U�jWriter���N���[�Y����B<BR>
     * @@param l_response - (���X�|���X�f�[�^)<BR>
     * @@param l_strSendTelegramData - (���M�d���f�[�^)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411741540141
     */
    public void createSendTelegram(
        HttpServletResponse l_response,
        String[] l_strSendTelegramData) throws IOException
    {
        String STR_METHOD_NAME =
            "createSendTelegram("
                + "HttpServletResponse l_response, "
                + "String[] l_strSendTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_response == null || l_strSendTelegramData == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j���X�|���X�f�[�^��ContentType��ݒ肷��
        //    ���X�|���X�f�[�^.setContentType("text/plain")
        l_response.setContentType(WEB3AioTelegramHttpRequestDef.ContentType);

        PrintWriter l_printWriter = null;

        // �Q�j���X�|���X�f�[�^����AWriter���擾����
        l_printWriter = l_response.getWriter();

        //�R�j���X�|���X�̃��b�Z�[�W�{�f�B��"<SHOPMSG>"���o�͂���
        l_printWriter.println(WEB3AioTelegramHttpRequestDef.SHOPMSG_START);

        //�S�j����.���M�d���f�[�^�̑S�v�f�����X�|���X�̃��b�Z�[�W�{�f�B�ɏo�͂���
        for (int i = 0; i < l_strSendTelegramData.length; i++)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
            log.debug("l_strSendTelegramData[" + i + "] = " + l_strSendTelegramData[i]);
            l_printWriter.println(l_strSendTelegramData[i]);
        }

        // �T�j���X�|���X�̃��b�Z�[�W�{�f�B��"</SHOPMSG>"���o�͂���
        l_printWriter.println(WEB3AioTelegramHttpRequestDef.SHOPMSG_END);

        // �U�jWriter���N���[�Y����
        l_printWriter.close();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (insert�������v��)<BR>
     * �������v���e�[�u���Ƀ��R�[�h��ǉ�����B<BR>
     * <BR>
     * �P�j�������v��Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �ڍׂ́ADB�X�V�d�l �u�����v����t_�������v���e�[�u��.xls�v �Q��<BR>
     * <BR>
     * �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����B<BR>
     * <BR>
     *   �m�����n<BR>
     *   �������v��Params�C���X�^���X<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41174288027A
     */
    public void insertOrderInfoRequire(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertOrderInfoRequire(HashMap l_receiptTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j�������v��Params�C���X�^���X�𐶐�����
        BankOrderRequestParams l_orderInfoRequireParams =
            new BankOrderRequestParams();

        // �Q�j�p�����[�^���Z�b�g����
        // �ڍׂ́ADB�X�V�d�l �u�����v����t_�������v���e�[�u��.xls�v�Q��
        l_orderInfoRequireParams.setCreatedTimestamp(
            WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),
                "yyyyMMddHHmmss"));
        l_orderInfoRequireParams.setProtocolVersion(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion));
        l_orderInfoRequireParams.setLinked1(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1));
        l_orderInfoRequireParams.setShopId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId));
        l_orderInfoRequireParams.setOrderDateTime(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date));
        l_orderInfoRequireParams.setCenterPayId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        l_orderInfoRequireParams.setPaySchemeId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        l_orderInfoRequireParams.setAccessKey(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey));

        // �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����
        try
        {
            log.debug("l_orderInfoRequireParams = " + l_orderInfoRequireParams);
            WEB3DataAccessUtility.insertRow(l_orderInfoRequireParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (insert���ϊJ�n�v��)<BR>
     * ���ϊJ�n�v���e�[�u���Ƀ��R�[�h��ǉ�����B<BR>
     * <BR>
     * �P�j���ϊJ�n�v��Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �ڍׂ́ADB�X�V�d�l �u���ϊJ�n��t_���ϊJ�n�v���e�[�u��.xls�v �Q��<BR>
     * <BR>
     * �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����B<BR>
     * <BR>
     *   �m�����n<BR>
     *   ���ϊJ�n�v��Params�C���X�^���X<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 411742AE021C
     */
    public void insertSettleStartRequire(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertSettleStartRequire(HashMap l_receiptTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j���ϊJ�n�v��Params�C���X�^���X�𐶐�����
        BankSettleStartRequestParams l_bankSettleStartRequestParams =
            new BankSettleStartRequestParams();

        // �Q�j�p�����[�^���Z�b�g����
        // �ڍׂ́ADB�X�V�d�l �u���ϊJ�n��t_���ϊJ�n�v���e�[�u��.xls�v�Q��
        l_bankSettleStartRequestParams.setCreatedTimestamp(
            WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),
                "yyyyMMddHHmmss"));
        l_bankSettleStartRequestParams.setProtocolVersion(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion));
        l_bankSettleStartRequestParams.setLinked1(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1));
        l_bankSettleStartRequestParams.setShopId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId));
        l_bankSettleStartRequestParams.setOrderDateTime(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date));
        l_bankSettleStartRequestParams.setCenterPayId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        l_bankSettleStartRequestParams.setPaySchemeId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        l_bankSettleStartRequestParams.setAccessKey(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey));
        l_bankSettleStartRequestParams.setPayStatus(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus));

        // �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����
        try
        {
            log.debug("l_bankSettleStartRequestParams = " + l_bankSettleStartRequestParams);
            WEB3DataAccessUtility.insertRow(l_bankSettleStartRequestParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (insert���ό��ʒʒm)<BR>
     * ���ό��ʒʒm�e�[�u���Ƀ��R�[�h��ǉ�����B<BR>
     * <BR>
     * �P�j���ό��ʒʒmParams�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �ڍׂ́ADB�X�V�d�l �u���ό��ʒʒm_���ό��ʒʒm�e�[�u��.xls�v �Q��<BR>
     * <BR>
     * �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����B<BR>
     * <BR>
     *   �m�����n<BR>
     *   ���ό��ʒʒmParams�C���X�^���X<BR>
     * @@param l_receiptTelegramData - (��M�d���f�[�^)
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119F7BC0244
     */
    public void insertSettleResultNotify(HashMap l_receiptTelegramData) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertSettleResultNotify(HashMap l_receiptTelegramData)";
        log.entering(STR_METHOD_NAME);

        if (l_receiptTelegramData == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j���ό��ʒʒmParams�C���X�^���X�𐶐�����
        BankSettleResultResponseParams l_bankSettleResultResponseParams =
            new BankSettleResultResponseParams();

        // �Q�j�p�����[�^���Z�b�g���� 
        // �ڍׂ́ADB�X�V�d�l �u���ό��ʒʒm_���ό��ʒʒm�e�[�u��.xls�v�Q��
        l_bankSettleResultResponseParams.setCreatedTimestamp(
            WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(),
                "yyyyMMddHHmmss"));
        l_bankSettleResultResponseParams.setProtocolVersion(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.protocolVersion));
        l_bankSettleResultResponseParams.setLinked1(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.linked_1));
        l_bankSettleResultResponseParams.setShopId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.shopId));
        l_bankSettleResultResponseParams.setOrderDateTime(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.date));
        l_bankSettleResultResponseParams.setCenterPayId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.centerPayId));
        l_bankSettleResultResponseParams.setComondebiSalesSlip(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiSalesSlip));
        l_bankSettleResultResponseParams.setComondebiAuthDate(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiAuthDate));
        l_bankSettleResultResponseParams.setPayStatus(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.payStatus));
        l_bankSettleResultResponseParams.setComondebiAuthresCode(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiAuthresCode));
        l_bankSettleResultResponseParams.setComondebiCaptureDate(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.ComOndebiCaptureDate));
        l_bankSettleResultResponseParams.setAmount(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.amount));
        l_bankSettleResultResponseParams.setPaySchemeId(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.paySchemeId));
        l_bankSettleResultResponseParams.setAccessKey(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.accessKey));
        l_bankSettleResultResponseParams.setDescription(
            (String) l_receiptTelegramData.get(WEB3AioTelegramFormatDef.description));

        // �R�jWEB3DataAccessUtility.insertRow()�ɂāA���R�[�h��ǉ�����
        try
        {
            log.debug("l_bankSettleResultResponseParams = " + l_bankSettleResultResponseParams);
            WEB3DataAccessUtility.insertRow(l_bankSettleResultResponseParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (update�����v����t���)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕���t���̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j.com�f�r�b�g���ώ���ԍ�<BR>
     *    ����.���o����.set.com�f�r�b�g���ώ���ԍ�()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ����..com�f�r�b�g���ώ���ԍ�<BR>
     * <BR>
     * �P�|�Q�j�������ԁi�����v���j<BR>
     *    ����.���o����.set�������ԁi�����v���j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j����.returnCode = "OK" �̏ꍇ<BR>
     * <BR>
     * �P�|�R�|�P�j����FLAG�i�����j<BR>
     *    ����.���o����.set����FLAG�i�����j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '1'�i�v����M�j<BR>
     * <BR>
     * �P�|�S�j����.returnCode = "NG" �̏ꍇ<BR>
     * <BR>
     * �P�|�S�|�P�j����FLAG�i�����j<BR>
     *    ����.���o����.set����FLAG�i�����j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�v���G���[NG�j<BR>
     * <BR>
     * �P�|�S�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�S�|�R�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�T�j����.returnCode = "ERROR" �̏ꍇ<BR>
     * <BR>
     * �P�|�T�|�P�j����FLAG�i�����j<BR>
     *    ����.���o����.set����FLAG�i�����j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '4'�i�v���G���[ERROR�j<BR>
     * <BR>
     * �P�|�T�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�T�|�R�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�U�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_strReturnCode - (��M�d���̃`�F�b�N����)<BR>
     * @@param l_strComDebitNumber - (.com�f�r�b�g���ώ���ԍ�)<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g
     * @@roseuid 4117476E0047
     */
    public void updateOrderRequireAccept(
        String l_strReturnCode,
        String l_strComDebitNumber,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateOrderRequireAccept("
                + "String l_strReturnCode, "
                + "String l_strComDebitNumber, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j.com�f�r�b�g���ώ���ԍ�<BR>
        //    ����.���o����.set.com�f�r�b�g���ώ���ԍ�()
        l_cashTransStatus.setComDebitNumber(l_strComDebitNumber);

        // �P�|�Q�j�������ԁi�����v���j
        //    ����.���o����.set�������ԁi�����v���j()
        l_cashTransStatus.setOrderRequestTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�R�j����.returnCode = "OK" �̏ꍇ
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            // �P�|�R�|�P�j����FLAG�i�����j
            //    ����.���o����.set����FLAG�i�����j()
            l_cashTransStatus.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_RECEIPT);
        }
        else
        {
            //�P�|�S�j����.returnCode = "NG" �̏ꍇ
            if (WEB3AioTelegramReturnCodeDef.NG.equals(l_strReturnCode))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
                // �P�|�S�|�P�j����FLAG�i�����j
                //    ����.���o����.set����FLAG�i�����j()
                l_cashTransStatus.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_ERROR_NG);

                // �P�|�S�|�Q�j�����敪
                //    ����.���o����.set�����敪()
                l_cashTransStatus.setStatus(WEB3TransactionStatusDef.ERROR);

                // �P�|�S�|�R�j��������
                //    ����.���o����.set��������()
                l_cashTransStatus.setTransactionTime(
                    GtlUtils.getSystemTimestamp());
            }
            else
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                //�P�|�T�j����.returnCode = "ERROR" �̏ꍇ
                if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
                {
                    // �P�|�T�|�P�j����FLAG�i�����j
                    //    ����.���o����.set����FLAG�i�����j()  
                    l_cashTransStatus.setOrderStatusFlag(WEB3OrderStatusFlagDef.REPUEST_ERROR_ERROR);

                    // �P�|�T�|�Q�j�����敪
                    //    ����.���o����.set�����敪()  
                    l_cashTransStatus.setStatus(WEB3TransactionStatusDef.ERROR);

                    // �P�|�T�|�R�j��������
                    //    ����.���o����.set��������()  
                    l_cashTransStatus.setTransactionTime(
                        GtlUtils.getSystemTimestamp());
                }
            }
        }

        // �P�|�U�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update�����v���������)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕���t�������̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j����FLAG�i�����j<BR>
     *    ����.���o����.set����FLAG�i�����j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '2'�i�������M�j<BR>
     * <BR>
     * �P�|�Q�j�������ԁi���������j<BR>
     *    ����.���o����.set�������ԁi���������j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g
     * @@roseuid 41197ED502EA
     */
    public void updateOrderRequireResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateOrderRequireResponse("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j����FLAG�i�����j
        //    ����.���o����.set����FLAG�i�����j()
        l_cashTransStatus.setOrderStatusFlag(
            WEB3OrderStatusFlagDef.RESPONSE_SEND);

        // �P�|�Q�j�������ԁi���������j
        //    ����.���o����.set�������ԁi���������j()
        l_cashTransStatus.setOrderResponseTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�R�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update�����v�����~���)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u���𒍕���t���~���̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j����FLAG�i���ϊJ�n�j<BR>
     *    ����.���o����.set����FLAG�i���ϊJ�n�j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '5'�i�L�����Z���j<BR>
     * <BR>
     * �P�|�Q�j�������ԁi���ϊJ�n�v���j<BR>
     *    ����.���o����.set�������ԁi���ϊJ�n�v���j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�S�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�T�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g
     * @@roseuid 4119AE1B005F
     */
    public void updateOrderRequireDiscontinuation(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateOrderRequireDiscontinuation("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j����FLAG�i���ϊJ�n�j
        //    ����.���o����.set����FLAG�i���ϊJ�n�j()
        l_cashTransStatus.setStartStatusFlg(
            WEB3StartStatusFlgDef.CANCEL);

        // �P�|�Q�j�������ԁi���ϊJ�n�v���j
        //    ����.���o����.set�������ԁi���ϊJ�n�v���j()
        l_cashTransStatus.setStartRequestTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�R�j�����敪
        //    ����.���o����.set�����敪()
        l_cashTransStatus.setStatus(
            WEB3TransactionStatusDef.ERROR);

        // �P�|�S�j��������
        //    ����.���o����.set��������()
        l_cashTransStatus.setTransactionTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�T�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update���ϊJ�n��t���)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊJ�n��t���̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j�������ԁi���ϊJ�n�v���j<BR>
     *    ����.���o����.set�������ԁi���ϊJ�n�v���j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�Q�j����.returnCode = "OK" �̏ꍇ<BR>
     * <BR>
     * �P�|�Q�|�P�j����FLAG�i���ϊJ�n�j<BR>
     *    ����.���o����.set����FLAG�i���ϊJ�n�j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '1'�i�v����M�j<BR>
     * <BR>
     * �P�|�R�j����.returnCode = "NG" �̏ꍇ<BR>
     * <BR>
     * �P�|�R�|�P�j����FLAG�i���ϊJ�n�j<BR>
     *    ����.���o����.set����FLAG�i���ϊJ�n�j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�v���G���[NG�j<BR>
     * <BR>
     * �P�|�R�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�R�|�R�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�S�j����.returnCode = "ERROR" �̏ꍇ<BR>
     * <BR>
     * �P�|�S�|�P�j����FLAG�i���ϊJ�n�j<BR>
     *    ����.���o����.set����FLAG�i���ϊJ�n�j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '4'�i�v���G���[ERROR�j<BR>
     * <BR>
     * �P�|�S�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�S�|�R�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�T�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_strReturnCode - (��M�d���̃`�F�b�N����)<BR>
     * @@param l_cashTransSituation - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g<BR>
     * @@roseuid 4117476E0067
     */
    public void updateSettleStartAccept(
        String l_strReturnCode,
        WEB3AioFinInstitutionCashTransStatus l_cashTransSituation) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleStartAccept("
                + "String l_strReturnCode, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransSituation == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j�������ԁi���ϊJ�n�v���j
        //    ����.���o����.set�������ԁi���ϊJ�n�v���j()
        l_cashTransSituation.setStartRequestTime(GtlUtils.getSystemTimestamp());

        // �P�|�Q�j����.returnCode = "OK" �̏ꍇ<BR>
        if (WEB3AioTelegramReturnCodeDef.OK.equals(l_strReturnCode))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            // �P�|�Q�|�P�j����FLAG�i���ϊJ�n�j
            //    ����.���o����.set����FLAG�i���ϊJ�n�j()
            l_cashTransSituation.setStartStatusFlg(
                WEB3StartStatusFlgDef.REPUEST_RECEIPT);
        }
        else
        {
            //�P�|�R�j����.returnCode = "NG" �̏ꍇ
            if (WEB3AioTelegramReturnCodeDef.NG.equals(l_strReturnCode))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
                // �P�|�R�|�P�j����FLAG�i���ϊJ�n�j
                //    ����.���o����.set����FLAG�i���ϊJ�n�j()
                l_cashTransSituation.setStartStatusFlg(
                    WEB3StartStatusFlgDef.REPUEST_ERROR_NG);

                // �P�|�R�|�Q�j�����敪
                //    ����.���o����.set�����敪()
                l_cashTransSituation.setStatus(WEB3TransactionStatusDef.ERROR);

                // �P�|�R�|�R�j��������
                //    ����.���o����.set��������()
                l_cashTransSituation.setTransactionTime(
                    GtlUtils.getSystemTimestamp());
            }
            else
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                //�P�|�S�j����.returnCode = "ERROR" �̏ꍇ
                if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
                {
                    // �P�|�S�|�P�j����FLAG�i���ϊJ�n�j
                    //    ����.���o����.set����FLAG�i���ϊJ�n�j()
                    l_cashTransSituation.setStartStatusFlg(
                        WEB3StartStatusFlgDef.REPUEST_ERROR_ERROR);

                    // �P�|�S�|�Q�j�����敪
                    //    ����.���o����.set�����敪()
                    l_cashTransSituation.setStatus(
                        WEB3TransactionStatusDef.ERROR);

                    // �P�|�S�|�R�j��������
                    //    ����.���o����.set��������()
                    l_cashTransSituation.setTransactionTime(
                        GtlUtils.getSystemTimestamp());
                }
            }
        }

        // �P�|�T�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransSituation.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransSituation.getDataSourceObject();
                
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update���ϊJ�n�������)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊJ�n�������̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j����FLAG�i���ϊJ�n�j<BR>
     *    ����.���o����.set����FLAG�i���ϊJ�n�j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '2'�i�������M�j<BR>
     * <BR>
     * �P�|�Q�j�������ԁi���ϊJ�n�����j<BR>
     *    ����.���o����.set�������ԁi���ϊJ�n�����j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g<BR>
     * @@roseuid 4119D059034D
     */
    public void updateSettleStartResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleStartResponse("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j����FLAG�i���ϊJ�n�j
        //    ����.���o����.set����FLAG�i���ϊJ�n�j()
        l_cashTransStatus.setStartStatusFlg(
            WEB3StartStatusFlgDef.RESPONSE_SEND);

        // �P�|�Q�j�������ԁi���ϊJ�n�����j
        //    ����.���o����.set�������ԁi���ϊJ�n�����j()
        l_cashTransStatus.setStartResponseTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�R�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update���ό��ʒʒm���)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʒʒm���̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j�������ԁi���ό��ʒʒm�j<BR>
     *    ����.���o����.set�������ԁi���ό��ʒʒm�j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�Q�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j����.returnCode = "COMPLETE" �̏ꍇ<BR>
     * <BR>
     * �P�|�R�|�P�j����FLAG�i���ό��ʁj<BR>
     *    ����.���o����.set����FLAG�i���ό��ʁj()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '1'�i�v����M�j<BR>
     * <BR>
     * �P�|�R�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '1'�iOK�j<BR>
     * <BR>
     * �P�|�R�|�R�j��n�\���<BR>
     *    ����.���o����.set��n�\���()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ����.��n�\���<BR>
     * <BR>
     * �P�|�R�|�S�j�U�������\���<BR>
     *    ����.���o����.set�U�������\���()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ����.�U�������\���<BR>
     * <BR>
     * �P�|�S�j����.returnCode = "FAIL" �̏ꍇ<BR>
     * <BR>
     * �P�|�S�|�P�j����FLAG�i���ό��ʁj<BR>
     *    ����.���o����.set����FLAG�i���ό��ʁj()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�ʒm�G���[FAIL�j<BR>
     * <BR>
     * �P�|�S�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '2'�iNG�j<BR>
     * <BR>
     * �P�|�T�j����.returnCode = "ERROR" �̏ꍇ<BR>
     * <BR>
     * �P�|�T�|�P�j����FLAG�i���ό��ʁj<BR>
     *    ����.���o����.set����FLAG�i���ό��ʁj()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '4'�i�ʒm�G���[ERROR�j<BR>
     * <BR>
     * �P�|�T�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�U�j����.returnCode = "NG" �̏ꍇ<BR>
     * <BR>
     * �P�|�U�|�P�j����FLAG�i���ό��ʁj<BR>
     *    ����.���o����.set����FLAG�i���ό��ʁj()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '5'�i�����G���[�j<BR>
     * <BR>
     * �P�|�U�|�Q�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�V�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_strReturnCode - (��M�d���̃`�F�b�N����)
     * @@param l_datDeliveryScheduledDate - (��n�\���)
     * @@param l_datComondebiCaptureDate - (�U�������\���)
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o���󋵃I�u�W�F�N�g
     * @@roseuid 4119F7830234
     */
    public void updateSettleResultNotify(
        String l_strReturnCode,
        Date l_datDeliveryScheduledDate,
        Date l_datComondebiCaptureDate,
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleResultNotify("
                + "String l_strReturnCode, "
                + "Date l_datDeliveryScheduledDate, "
                + "Date l_datComondebiCaptureDate, "
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j�������ԁi���ό��ʒʒm�j
        //    ����.���o����.set�������ԁi���ό��ʒʒm�j()
        l_cashTransStatus.setResultRequestTime(GtlUtils.getSystemTimestamp());

        // �P�|�Q�j��������
        //    ����.���o����.set��������()
        l_cashTransStatus.setTransactionTime(GtlUtils.getSystemTimestamp());

        // �P�|�R�j����.returnCode = "COMPLETE" �̏ꍇ
        if (WEB3AioTelegramReturnCodeDef.COMPLETE.equals(l_strReturnCode))
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            // �P�|�R�|�P�j����FLAG�i���ό��ʁj
            //    ����.���o����.set����FLAG�i���ό��ʁj()
            l_cashTransStatus.setResultStatusFlag(
                WEB3ResultStatusFlagDef.NOTIFY_RECEIPT);

            // �P�|�R�|�Q�j�����敪
            //    ����.���o����.set�����敪()
            l_cashTransStatus.setStatus(WEB3TransactionStatusDef.OK);

            // �P�|�R�|�R�j��n�\���
            //    ����.���o����.set��n�\���()           
            ((BankCashTransferStatusParams) 
               l_cashTransStatus.getDataSourceObject()).setDeliveryScheduledDate(
                 l_datDeliveryScheduledDate);

            // �P�|�R�|�S�j�U�������\���<BR>
            //    ����.���o����.set�U�������\���()
            ((BankCashTransferStatusParams)
                l_cashTransStatus.getDataSourceObject()).setComondebiCaptureDate(
                    l_datComondebiCaptureDate);
        }
        else
        {
            //�P�|�S�j����.returnCode = "FAIL" �̏ꍇ
            if (WEB3AioTelegramReturnCodeDef.FAIL.equals(l_strReturnCode))
            {
                log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
                // �P�|�S�|�P�j����FLAG�i���ό��ʁj
                //    ����.���o����.set����FLAG�i���ό��ʁj()
                l_cashTransStatus.setResultStatusFlag(
                    WEB3ResultStatusFlagDef.NOTIFY_ERROR_FAIL);

                // �P�|�S�|�Q�j�����敪
                //    ����.���o����.set�����敪()>
                l_cashTransStatus.setStatus(WEB3TransactionStatusDef.NG);
            }
            else
            {
                // �P�|�T�j����.returnCode = "ERROR" �̏ꍇ
                if (WEB3AioTelegramReturnCodeDef.ERROR.equals(l_strReturnCode))
                {
                    log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
                    // �P�|�T�|�P�j����FLAG�i���ό��ʁj
                    //    ����.���o����.set����FLAG�i���ό��ʁj()
                    l_cashTransStatus.setResultStatusFlag(
                        WEB3ResultStatusFlagDef.NOTIFY_ERROR_ERROR);

                    // �P�|�T�|�Q�j�����敪
                    //    ����.���o����.set�����敪()>
                    l_cashTransStatus.setStatus(WEB3TransactionStatusDef.ERROR);
                }
                else
                {
                    //�P�|�U�j����.returnCode = "NG" �̏ꍇ
                    if (WEB3AioTelegramReturnCodeDef.NG.equals(l_strReturnCode))
                    {
                        log.debug("NOTICE : --> --> --> --> --> --> Into Case 5");
                        // �P�|�U�|�P�j����FLAG�i���ό��ʁj
                        //    ����.���o����.set����FLAG�i���ό��ʁj()
                        l_cashTransStatus.setResultStatusFlag(
                            WEB3ResultStatusFlagDef.RESPONSE_ERROR_TELEGRAM);

                        // �P�|�U�|�Q�j�����敪
                        //    ����.���o����.set�����敪()>
                        l_cashTransStatus.setStatus(
                            WEB3TransactionStatusDef.ERROR);
                    }
                }
            }
        }

        // �P�|�V�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update���ό��ʉ������)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ό��ʉ������̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j����FLAG�i���ό��ʁj<BR>
     *    ����.���o����.set����FLAG�i���ό��ʁj()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '2'�i�������M�j<BR>
     * <BR>
     * �P�|�Q�j�������ԁi���ό��ʉ����j<BR>
     *    ����.���o����.set�������ԁi���ό��ʉ����j()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * <BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 4119F7830282
     */
    public void updateSettleResultResponse(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleResultResponse("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j����FLAG�i���ό��ʁj
        //    ����.���o����.set����FLAG�i���ό��ʁj()
        l_cashTransStatus.setResultStatusFlag(
            WEB3ResultStatusFlagDef.RESPONSE_SEND);

        // �P�|�Q�j�������ԁi���ό��ʉ����j
        //    ����.���o����.set�������ԁi���ό��ʉ����j()
        l_cashTransStatus.setResultResponseTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�R�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update���ϊ������)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������ϊ������i�G���[�j�̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '1'�iOK�j<BR>
     * <BR>
     * �P�|�Q�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 411B18C4007F
     */
    public void updateSettleComplete(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleComplete("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j�����敪
        //    ����.���o����.set�����敪()
        l_cashTransStatus.setStatus(
            WEB3TransactionStatusDef.OK);

        // �P�|�Q�j��������
        //    ����.���o����.set��������()
        l_cashTransStatus.setTransactionTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�R�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
            
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (update���σG���[���)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u�������σG���[���̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����B<BR>
     * <BR>
     * �P�|�P�j�����敪<BR>
     *    ����.���o����.set�����敪()<BR>
     * <BR>
     *    �m�����n<BR>
     *     '3'�i�G���[�j<BR>
     * <BR>
     * �P�|�Q�j��������<BR>
     *    ����.���o����.set��������()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �P�|�R�j�ŏI�X�V����<BR>
     *    ����.���o����.set�ŏI�X�V����()<BR>
     * <BR>
     *    �m�����n<BR>
     *     ���ݎ����i�V�X�e���^�C���X�^���v�j<BR>
     * <BR>
     * �Q�j���R�[�h���X�V����B<BR>
     *    WEB3DataAccessUtility.updateRow()<BR>
     * <BR>
     *    [����]<BR>
     *    ����.���o����.getDataSourceObject()�̖߂�l<BR>
     * @@param l_cashTransStatus - (���o����)<BR>
     * @@throws WEB3BaseException<BR>
     * ���Z�@@�֘A�g���o����Params�I�u�W�F�N�g
     * @@roseuid 411B32690264
     */
    public void updateSettleError(
        WEB3AioFinInstitutionCashTransStatus l_cashTransStatus) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateSettleError("
                + "WEB3AioFinInstitutionCashTransStatus l_cashTransStatus)";
        log.entering(STR_METHOD_NAME);

        if (l_cashTransStatus == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // �P�j����.���o���󋵂ɁA�p�����[�^���Z�b�g����
        // �P�|�P�j�����敪
        //    ����.���o����.set�����敪()
        l_cashTransStatus.setStatus(
            WEB3TransactionStatusDef.ERROR);

        // �P�|�Q�j��������
        //    ����.���o����.set��������()
        l_cashTransStatus.setTransactionTime(
            GtlUtils.getSystemTimestamp());

        // �P�|�R�j�ŏI�X�V����
        //    ����.���o����.set�ŏI�X�V����()
        l_cashTransStatus.setLastUpdateTimestamp(
            GtlUtils.getSystemTimestamp());

        // �Q�j���R�[�h���X�V����
        //    WEB3DataAccessUtility.updateRow()
        try
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_cashTransStatus.getDataSourceObject();
            BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                (BankCashTransferStatusParams) l_bankCashTransferStatusRow;
                
            log.debug("l_bankCashTransferStatusParams = " + l_bankCashTransferStatusParams);
            WEB3DataAccessUtility.updateRow(l_bankCashTransferStatusParams);
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
     * (get���b�Z�[�W�R�[�h)<BR>
     * ���b�Z�[�W�R�[�h���擾����B<BR>
     * <BR>
     * �P�j�����P�ʃe�[�u������A�ȉ��̏����̃f�[�^���擾����B<BR>
     * <BR>
     *    �m�����n<BR>
     *    ���ʃR�[�h = ����.���ʃR�[�h<BR>
     *    .com�f�r�b�g���ώ���ԍ� = ����..com�f�r�b�g���ώ���ԍ�<BR>
     * <BR>
     * �Q�j���o���`�[��t�L���[�e�[�u������A�ȉ��̏����̃f�[�^���擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    �f�[�^�R�[�h = "GI80C"<BR>
     *    �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     *    ���X�R�[�h = ����.���X�R�[�h<BR>
     *    �ڋq�R�[�h = ����.�ڋq�R�[�h<BR>
     *    ���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �R�j�����P�ʂ����t���O�Ɠ��o���`�[��t�L���[�����t���O�ƈ����̃t���O����<BR>
     *    ���݂̓��o���󋵂𔻒肵�A���̌��ʂ�ԋp����B<BR>
     * <BR>
     *    �ڍׂ́A�u���o���X�e�[�^�X�\���\.xls�v �Q��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)
     * @@param l_strComDebitNumber - (.com�f�r�b�g���ώ���ԍ�)
     * @@param l_strStatus - (�����敪)
     * @@param l_strOrderStatusFlag - (����FLAG�i�����j)
     * @@param l_strStartStatusFlg - (����FLAG�i���ϊJ�n�j)
     * @@param l_strResultStatusFlag - (����FLAG�i���ό��ʁj)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41184FB001F6
     */
    public String getMessageCode(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        String l_strOrderRequestNumber,
        String l_strComDebitNumber,
        String l_strStatus,
        String l_strOrderStatusFlag,
        String l_strStartStatusFlg,
        String l_strResultStatusFlag) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getMessageCode("
                + "String l_strInstitutionCode, "
                + "String l_strBranchCode, "
                + "String l_strAccountCode, "
                + "String l_strOrderRequestNumber, "
                + "String l_strComDebitNumber, "
                + "String l_strStatus, "
                + "String l_strOrderStatusFlag, "
                + "String l_strStartStatusFlg, "
                + "String l_strResultStatusFlag)";
        log.entering(STR_METHOD_NAME);

        // �P�j�����P�ʃe�[�u������A�ȉ��̏����̃f�[�^���擾����B
        //    �m�����n
        //    ���ʃR�[�h = ����.���ʃR�[�h
        //    .com�f�r�b�g���ώ���ԍ� = ����..com�f�r�b�g���ώ���ԍ�
        List l_lisOrderRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_whereClause =
                " order_request_number = ? " + "and com_debit_number = ? ";
            Object[] l_bindVars = { l_strOrderRequestNumber, l_strComDebitNumber };

            l_lisOrderRows =
                l_queryProcessor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_whereClause,
                    l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(
                " DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search table AioOrderUnit",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(
                " DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search table AioOrderUnit",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

        OrderStatusEnum l_orderStatus = null;
        String l_strCancelType = null;
        if (l_lisOrderRows != null && l_lisOrderRows.size() != 0)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 2");
            AioOrderUnitRow l_aioOrderUnitRow = 
                (AioOrderUnitRow) l_lisOrderRows.get(0);
            l_orderStatus = l_aioOrderUnitRow.getOrderStatus();
            l_strCancelType = l_aioOrderUnitRow.getCancelType();
        }

        // �Q�j���o���`�[��t�L���[�e�[�u������A�ȉ��̏����̃f�[�^���擾����B
        //    [����]<BR>
        //    �f�[�^�R�[�h = "GI80C"
        //    �،���ЃR�[�h = ����.�،���ЃR�[�h
        //    ���X�R�[�h = ����.���X�R�[�h
        //    �ڋq�R�[�h = ����.�ڋq�R�[�h
        //    ���ʃR�[�h = ����.���ʃR�[�h
        List l_lisRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            String l_whereClause =
                " request_code = ? "
                    + "and institution_code = ? "
                    + "and branch_code = ? "
                    + "and account_code = ? "
                    + "and order_request_number = ? ";
            Object[] l_bindVars =
                {
                    WEB3HostRequestCodeDef.AIO_SLIP_ACCEPT,
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strOrderRequestNumber };

            l_lisRows =
                l_queryProcessor.doFindAllQuery(
                    HostCashTransOrderAcceptRow.TYPE,
                    l_whereClause,
                    l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(
                " DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(
                " DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }

        String l_strKeyTableStatus = null;
        if (l_lisRows != null && l_lisRows.size() != 0)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 3");
            HostCashTransOrderAcceptRow l_hostCashTransOrderAcceptRow = 
                (HostCashTransOrderAcceptRow) l_lisRows.get(0);
            l_strKeyTableStatus = l_hostCashTransOrderAcceptRow.getStatus();
        }

        // �R�j�����P�ʂ����t���O�Ɠ��o���`�[��t�L���[�����t���O�ƈ����̃t���O����<BR>
        //    ���݂̓��o���󋵂𔻒肵�A���̌��ʂ�ԋp����B<BR>
        log.debug("l_strStatus = " + l_strStatus);
        log.debug("l_strOrderStatusFlag = " + l_strOrderStatusFlag);
        log.debug("l_strStartStatusFlg = " + l_strStartStatusFlg);
        log.debug("l_strResultStatusFlag = " + l_strResultStatusFlag);
        log.debug("l_orderStatus = " + l_orderStatus);
        log.debug("l_strCancelType = " + l_strCancelType);
        log.debug("l_strKeyTableStatus = " + l_strKeyTableStatus);
        
        WEB3AioMutilBankStatusUtility l_statusUtility = new WEB3AioMutilBankStatusUtility();
        String l_strMessageCode =
            l_statusUtility.getStatus(
                l_strStatus,
                l_strOrderStatusFlag,
                l_strStartStatusFlg,
                l_strResultStatusFlag,
                (l_orderStatus == null) ? null : l_orderStatus.intValue() + "",
                l_strCancelType,
                l_strKeyTableStatus);

        log.exiting(STR_METHOD_NAME);
        return l_strMessageCode;
    }

    /**
     * (createHashMapFrom��M�d��)<BR>
     * ��M�d������AHashMap�𐶐�����B<BR>
     * <BR>
     * �P�j���HashMap�𐶐�����B<BR>
     * <BR>
     * �Q�j���N�G�X�g�f�[�^�̃��b�Z�[�W�{�f�B����A1�s�ǂݍ��ށB<BR>
     * <BR>
     * �Q�|�P�j���̍s�̓��e�� <SHOPMSG> �������� </SHOPMSG> ��<BR>
     * �ꍇ�́A�������Ȃ��B<BR>
     * <BR>
     * �Q�|�Q�j���̍s�̓��e���m�L�[�n=�m�l�n�̌`���̏ꍇ�A<BR>
     * HashMap�ɓo�^����B<BR>
     * <BR>
     * �Q�|�R�j���̍s�̓��e����L2�p�^�[���ȊO�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BaseRuntimeException<BR>
     *         tag:   BUSINESS_ERROR_00830<BR>
     * <BR>
     * �R�j���ׂĂ̍s�ɂ��āA�Q�j�̏������s���B<BR>
     * <BR>
     * �S�j���N�G�X�g�f�[�^.getRemoteAddr()�̖߂�l���L�["remoteAdd"��<BR>
     *     HashMap<BR>�ɓo�^����B<BR>
     * <BR>
     * �T�j�������ꂽHashMap��ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return HashMap
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4118884E0225
     */
    public HashMap createHashMapFromReceiptTelegram(HttpServletRequest l_request) 
        throws IOException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "createHashMapFromReceiptTelegram("
                + "HttpServletRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 1");
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j���HashMap�𐶐�����B
        HashMap l_hashMap = new HashMap();

        //�Q�j���N�G�X�g�f�[�^����L�[�ƒl���擾����B
        String l_strKey = null;
        String l_strValue = null;
        for (Enumeration l_enumeration = l_request.getParameterNames(); l_enumeration.hasMoreElements();) {
            log.debug("NOTICE : --> --> --> --> --> --> Into Case 4");
            l_strKey = l_enumeration.nextElement().toString();
            l_strValue = l_request.getParameter(l_strKey);
            log.debug("l_strKey = " + l_strKey);
            log.debug("l_strValue = " + l_strValue);
            l_hashMap.put(l_strKey, l_strValue);
        }

        //�S�j���N�G�X�g�f�[�^.getRemoteAddr()�̖߂�l���L�["remoteAdd"��
        //     HashMap<BR>�ɓo�^����
        l_hashMap.put(WEB3AioTelegramFormatDef.remoteAdd, l_request.getRemoteAddr());

        //�T�j�������ꂽHashMap��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_hashMap;
    }
    
    public boolean contain(List l_lisAioSettleInstitutionUnit, 
        WEB3AioSettleInstitutionUnit l_aioSettleInstitutionUnit)
    {
        if(l_lisAioSettleInstitutionUnit == null 
            || l_lisAioSettleInstitutionUnit.size() == 0)
        {
            return false;
        }
        if(l_aioSettleInstitutionUnit == null)
        {
            return true;
        }
        
        for(int i = 0; i<l_lisAioSettleInstitutionUnit.size(); i++)
        {
            WEB3AioSettleInstitutionUnit l_temp = 
                (WEB3AioSettleInstitutionUnit) l_lisAioSettleInstitutionUnit.get(i);
            
            if(l_temp.paySchemeId.equals(l_aioSettleInstitutionUnit.paySchemeId))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * ������ƃI�u�W�F�N�g���r���܂��B<BR>
     * �����Ƃ�null�̏ꍇ��true��Ԃ��܂��B<BR>
     * @@param l_str ��r�Ώۂ̕�����
     * @@param l_obj ��r�Ώۂ̃I�u�W�F�N�g
     * @@return ��v����ꍇ��true,�����łȂ��ꍇ��false
     */
    private boolean compare(
        String l_str,
        Object l_obj)
    {
        if (l_obj == null)
        {
            if (l_str == null)
            {
                return true;
            }
            else
            {
                return false; 
            }
        }
        else
        {
            if (l_obj.toString().equals(l_str))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    /**
     * (get�v���t�@@�����X)<BR>
     * �p�����[�^�Ɏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���I�����C�������v���t�@@�����X�e�[�u������擾����B<BR> 
     * <BR>
     * �p�����[�^�Ɏw�肳�ꂽ�ݒ薼�̂̐ݒ�l���V�X�e���v���t�@@�����X�e�[�u������擾����B<BR> 
     * <BR>
     * �P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B <BR>
     * <BR>
     * [�擾����] <BR>
     * ���� = ����.�،���ЃR�[�h + ����.�ݒ薼�� <BR>
     * <BR>
     * �Q�j�擾�����V�X�e���v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strName - (�ݒ薼��)
     * @@return String
     * @@throws WEB3BaseException<BR>
     * @@roseuid 4119AD83011B
     */
    public String getPreference(
        String l_strInstitutionCode,
        String l_strName)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getPreferences(String l_strInstitutionCode, String l_strName)";
        log.entering(STR_METHOD_NAME);
        
        SystemPreferencesRow l_systemPreferencesRow = null;
        try
        {
            //�P�j�V�X�e���v���t�@@�����X�e�[�u������ȉ��̏����Ń��R�[�h���擾����B 
            //[�擾����] 
            //���� = ����.�،���ЃR�[�h + ����.�ݒ薼�� 
            l_systemPreferencesRow = SystemPreferencesDao.findRowByPk(
                    l_strInstitutionCode + l_strName);
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
        
        //�Q�j�擾�����I�����C�������v���t�@@�����X�e�[�u���̃��R�[�h�̐ݒ�l��ԋp����B
        return l_systemPreferencesRow.getValue();
    }
    
    /**
     *  (get�L�����A�敪)<BR>
     * �����o�H�敪����L�����A�敪���擾���A�l��ϊ�����B<BR> 
     * <BR>
     * �P�j�@@����:�����o�H�敪 == WEB3OrderRootDivDef.I_MODE�̏ꍇ�A<BR> 
     * �@@�@@�@@�L�����A�敪�F4�ii-mode�j��ԋp����B<BR> 
     * <BR>
     * �Q�j�@@����:�����o�H�敪 == WEB3OrderRootDivDef.VODAFONE�̏ꍇ�A<BR> 
     * �@@�@@�@@�L�����A�敪�F5�ivodafone�j��ԋp����B <BR>
     * <BR>
     * �R�j�@@����:�����o�H�敪 == WEB3OrderRootDivDef.AU�̏ꍇ�A<BR> 
     * �@@�@@�@@�L�����A�敪�F6�iau�j��ԋp����B<BR> 
     * <BR>
     * �S�j�@@1�j�`�R�j�ȊO�̏ꍇ�A�L�����A�敪�F2�i���o�C���ȊO�j��ԋp����B<BR>
     * <BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪) 
     * 1�Fcall <BR>
     * 2�FPC <BR>
     * 3�F�X�����O�V���b�g <BR>
     * 4�Fi-mode <BR>
     * 5�Fvodafone <BR>
     * 6�Gau<BR> 
     * 7�F�X�����O�V���b�g�i�����j <BR>
     * 9�FHOST <BR>
     * A�F�Ǘ��� <BR>
     * B�F�ۏ؋������U�փo�b�`<BR>
     * @@return String
     */
    public String getCareerDiv(String l_strOrderRootDiv) 
    {
        String STR_METHOD_NAME =
            "getCareerDiv(String l_strOrderRootDiv)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����:�����o�H�敪 == WEB3OrderRootDivDef.I_MODE�̏ꍇ�A
        if (WEB3OrderRootDivDef.I_MODE.equals(l_strOrderRootDiv)) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CareerDivDef.I_MODE;
        }
        
        //�Q�j�@@����:�����o�H�敪 == WEB3OrderRootDivDef.VODAFONE�̏ꍇ
        else if (WEB3OrderRootDivDef.VODAFONE.equals(l_strOrderRootDiv)) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CareerDivDef.VODAFONE;   
        }
        
        //�R�j�@@����:�����o�H�敪 == WEB3OrderRootDivDef.AU�̏ꍇ
        else if (WEB3OrderRootDivDef.AU.equals(l_strOrderRootDiv)) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3CareerDivDef.AU;    
        }
        
        //�S�j�@@1�j�`�R�j�ȊO�̏ꍇ�A�L�����A�敪�F2�i���o�C���ȊO�j��ԋp����
        else 
        {
            log.exiting(STR_METHOD_NAME);
            return  WEB3CareerDivDef.MOBILE_OTHER_PC;   
        }
        
    }
}
@
