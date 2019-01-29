head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMainAccount.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq(WEB3GentradeMainAccount.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 羐� (���u) �V�K�쐬
Revesion History : 2004/10/09 �Г� (���u) getDisplayAccountCode()��ǉ�
Revesion History : 2005/07/11 �Г� (���u) is�����~����()�ɊO�����W�b�N��ǉ�
Revesion History : 2005/09/26 �Г� (���u) is���M�萔������()��ǉ�
Revesion History : 2005/09/26 �Г� (���u) is���M��ې�()��ǉ�
Revesion History : 2005/10/07 �Г� (���u) is�@@�l()��ǉ�
Revesion History : 2005/11/22 �Г� (���u) is���M�萔������()���폜
Revesion History : 2005/11/22 �Г� (���u) is���M��ې�()���폜
Revesion History : 2005/12/08 �Г� (���u) is�ݓ������J��()��ǉ�
Revesion History : 2006/06/14 ������ (���u) �d�l�ύX�E���f��No.196��Ή�
Revesion History : 2006/09/19 �h�C (���u) �d�l�ύX�E���f��No.211��Ή�
Revesion History : 2007/03/09 �h�C (���u) �d�l�ύX�E���f��No.223��Ή�
Revesion History : 2007/09/12 �h�C (���u) �d�l�ύX�E���f��No.268��Ή�
Revesion History : 2007/12/11 �h�C (���u) �d�l�ύX�E���f��No.295��Ή�
Revesion History : 2007/12/17 �đo�g (���u) �d�l�ύX ���f��No.287,No.298,No.302
Revesion History : 2008/01/26 �h�C (���u) �d�l�ύX�E���f��No.316��Ή�
Revesion History : 2008/10/06 ������ (���u) �d�l�ύX�E���f��No.335��Ή�
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Participant;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3AccountProductOrderStopDivDef;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3CfdAccOpenDivDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ForeignSecAccOpenDiv;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3OrgDepositDivDef;
import webbroker3.common.define.WEB3PTSAccOpenDivDef;
import webbroker3.common.define.WEB3ProductIdDef;
import webbroker3.common.define.WEB3QuoteTypeDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.common.define.WEB3SexDef;
import webbroker3.gentrade.data.AccountProductOrderStopRow;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.gentrade.define.WEB3GentradeEquityMarginDivDef;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ڋq) <BR>
 * �ڋq�̑�������\������B<BR>
 * xTrade��MainAcount���g�������N���X�B<BR>
 * <BR>
 * @@author �{���@@�瑐(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl
 */
public class WEB3GentradeMainAccount extends MainAccountImpl
{

    /**
     * �����R�[�h <BR>
     */
    private String accountCode;

    /**
     * (�،���ЃR�[�h) <BR>
     */
    private String institutionCode;

    /**
     * (���X�R�[�h) <BR>
     */
    private String branchCode;

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMainAccount.class);

    /**
     * �ڋqRow�I�u�W�F�N�g�B<BR>
     */
    private MainAccountRow accountRow;

    /**
     * ���X�I�u�W�F�N�g�B<BR>
     */
    private Branch branch;

    /**
     * �⏕�����I�u�W�F�N�g�̔z��B<BR>
     */
    private SubAccount[] subAccounts;

    /**
     * �֌W�҃I�u�W�F�N�g�̔z��B<BR>
     */
    private Participant[] participants;

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * @@param l_lngInstId �،����ID
     * @@param l_strBranchCode ���X�R�[�h
     * @@param l_strAccountCode �����R�[�h
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQL�̎��s�Ɏ��s�����ꍇ
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DB�T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     * @@roseuid 403496F0022B
     */
    public WEB3GentradeMainAccount(
        long l_lngInstId,
        String l_strBranchCode,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngInstId, l_strBranchCode, l_strAccountCode);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
        
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR> 
     * @@param l_lngInstId �،����ID
     * @@param l_lngBranchId ���XID
     * @@param l_strAccountCode �����R�[�h
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQL�̎��s�Ɏ��s�����ꍇ
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DB�T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     * @@roseuid 403496F000E3
     */
    public WEB3GentradeMainAccount(
        long l_lngInstId,
        long l_lngBranchId,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngInstId, l_lngBranchId, l_strAccountCode);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR> 
     * @@param l_row �ڋqRow�I�u�W�F�N�g
     * @@roseuid 403496F000D3
     */
    public WEB3GentradeMainAccount(MainAccountRow l_row)
    {
        super(l_row);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
    }

    /**
     * �R���X�g���N�^�B<BR>
     * <BR> 
     * @@param l_accountId �ڋqID
     * @@param l_lngAccountLd
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 403496EF0382
     */
    public WEB3GentradeMainAccount(long l_lngAccountLd)
        throws DataQueryException, DataNetworkException
    {
        super(l_lngAccountLd);
        this.accountRow = (MainAccountRow)this.getDataSourceObject();
        this.accountCode = this.accountRow.getAccountCode();
        this.institutionCode = this.accountRow.getInstitutionCode();
    }

    /**
     * ���I�u�W�F�N�g�Ɋ֘A���镔�X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR> 
     * @@return ���X�I�u�W�F�N�g
     * @@roseuid 403496F00363
     */
    public Branch getBranch() 
    {
        final String STR_METHOD_NAME = "getBranch()";
        
        if (branch == null)
        {
            if (this.accountRow == null)
            {
                 this.accountRow = (MainAccountRow) this.getDataSourceObject();
            }
            long l_lngBranchId = accountRow.getBranchId();
            try
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                AccountManager l_accountMgr = l_finApp.getAccountManager();
                branch = l_accountMgr.getBranch(l_lngBranchId);
                return branch;
            }
            catch (NotFoundException nfe)
            {
                String s =
                    "Could not obtain branch  for id : "
                        + l_lngBranchId
                        + ", for account  with id "
                        + getAccountId();
                log.error(s, nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
        }
        else
        {
            return branch;
        }

    }

    /**
     * �^����ꂽ���������ƂɌڋqRow�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * <BR> 
     * @@param l_lngInstId �،����ID
     * @@param l_lngBranchId ���XID
     * @@param l_strAccountCode �����R�[�h
     * @@return �ڋqRow�I�u�W�F�N�g
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQL�̎��s�Ɏ��s�����ꍇ
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DB�T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     * @@roseuid 403496F003A2
     */
    public static MainAccountRow getMainAccountRow(
        long l_lngInstId,
        long l_lngBranchId,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "getMainAccountRow(long, long, String)";
        
        try
        {
            MainAccountRow l_row =
                MainAccountDao.findRowByInstitutionIdBranchIdAccountCode(
                    l_lngInstId,
                    l_lngBranchId,
                    l_strAccountCode);
            if (l_row != null)
            {
                return l_row;
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting account for instId,branchId,accountCode="
                    + l_lngInstId
                    + ','
                    + l_lngBranchId
                    + ","
                    + l_strAccountCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        throw new DataFindException(
            "No main_account row found for instId,branchId,accountCode="
                + l_lngInstId
                + ','
                + l_lngBranchId
                + ","
                + l_strAccountCode);
    }

    /**
     * �^����ꂽ���������ƂɌڋqRow�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * <BR>
     * @@param l_lngInstId �،����ID
     * @@param l_strBranchCode ���X�R�[�h
     * @@param l_strAccountCode �����R�[�h
     * @@return �ڋqRow�I�u�W�F�N�g
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException 
     * SQL�̎��s�Ɏ��s�����ꍇ
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException 
     * DB�T�[�o�Ƃ̐ڑ��Ɏ��s�����ꍇ
     * @@roseuid 403496F10111
     */
    public static MainAccountRow getMainAccountRow(
        long l_lngInstId,
        String l_strBranchCode,
        String l_strAccountCode)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "getMainAccountRow(long, String, String)";
        
        try
        {
            MainAccountRow l_row =
                MainAccountDao.findRowByInstitutionIdBranchCodeAccountCode(
                    l_lngInstId,
                    l_strBranchCode,
                    l_strAccountCode);
            if (l_row != null)
            {
                return l_row;
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting account for instId,branchCode,accountCode="
                    + l_lngInstId
                    + ','
                    + l_strBranchCode
                    + ","
                    + l_strAccountCode;
            log.error(msg, de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        throw new DataFindException(
            "No main_account row found for instId,branchCode,accountCode="
                + l_lngInstId
                + ','
                + l_strBranchCode
                + ","
                + l_strAccountCode);
    }

    /**
     * ���I�u�W�F�N�g�Ɋ֘A����⏕�����I�u�W�F�N�g�̔z���Ԃ��B<BR>
     * <BR> 
     * @@return �⏕�����I�u�W�F�N�g�̔z��
     * @@roseuid 4042EDB8039F
     */
    public SubAccount[] getSubAccounts()
    {
        final String STR_METHOD_NAME = "getSubAccounts()";
        
        if (subAccounts == null)
        {
            try
            {
                subAccounts = WEB3GentradeSubAccount.getSubAccounts(this);
            }
            catch (DataQueryException de)
            {
                log.error(
                    "Exception while getting SubAccounts for account_id = "
                        + getAccountId(),
                    de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            catch (DataNetworkException de)
            {
                log.error(
                    "Exception while getting SubAccounts for account_id = "
                        + getAccountId(),
                    de);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
        }
        return subAccounts;
    }

//    /**
//     * ���I�u�W�F�N�g�Ɋ֘A����֌W�҃I�u�W�F�N�g�̔z���Ԃ��B<BR>
//     * <BR> 
//     * @@return �֌W�҃I�u�W�F�N�g�̔z��
//     * @@roseuid 4042EDB803BE
//     */
//    public Participant[] getParticipants()
//    {
//        final String STR_METHOD_NAME = "getParticipants()";
//        
//        if (participants == null)
//        {
//            try
//            {
//                participants = ParticipantImpl.getParticipants(this);
//            }
//            catch (DataQueryException de)
//            {
//                log.error(
//                    "Exception while getting Participant rows from participant table for account id :"
//                        + getAccountId(),
//                    de);
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
//                    de.getMessage(),
//                    de);
//            }
//            catch (DataNetworkException de)
//            {
//                log.error(
//                    "Exception while getting Participant rows from participant table for account id :"
//                        + getAccountId(),
//                    de);
//                throw new WEB3BaseRuntimeException(
//                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                    WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
//                    de.getMessage(),
//                    de);
//            }
//        }
//        return participants;
//    }

    /**
     * (get��n���ŋ敪) <BR>
     * �ڋq�́A�����̎�n���ɂ�����ŋ敪�iTaxTypeEnum�j��ԋp����B<BR>
     *  <BR>
     * ����ƂȂ�V�X�e�����t���擾����B<BR>
     * �iGtlUtils.getTradingSystem().getSystemTimestamp()�j<BR>
     *  <BR>
     * ����.��n���Ǝ擾���������YYYY�̒l�ɂ��A�ȉ��̔�����s���B<BR>
     *  <BR>
     * ������.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j<BR>
     * �Ethis.�ŋ敪 ��Ԃ��B<BR>
     *  <BR>
     * ������.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j<BR>
     * �Ethis.�ŋ敪�i���N�j ��Ԃ��B<BR>
     *  <BR>
     * ������.��n���̓��t��YYYY�������YYYY�̏ꍇ<BR>
     * �E��O��throw����B<BR>
     *   class�FWEB3BusinessLayerException <BR>
     *   tag�F  BUSINESS_ERROR_00065 <BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��) <BR>
     *    �������.getDailyDeliveryDate() <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum
     * @@throws webbroker3.common.WEB3BusinessLayerException
     * @@roseuid 4074AAB101DC
     */
    public TaxTypeEnum getDeliveryDateTaxType(Date l_datDeliveryDate)
        throws WEB3BusinessLayerException
    {
        TaxTypeEnum l_taxType;
        
        final String STR_METHOD_NAME = "getDeliveryDateTaxType(Date)";
        log.entering(STR_METHOD_NAME);
        
        //����ƂȂ�V�X�e�����t���擾����
        Date l_datBaseDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        String l_strBaseDate = l_format.format(l_datBaseDate);
        String l_strDeliveryDate = l_format.format(l_datDeliveryDate);

        //����.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j 
        //this.�ŋ敪 ��Ԃ��B
        if (l_strDeliveryDate.compareTo(l_strBaseDate) == 0)
        {
            l_taxType = accountRow.getTaxType();
        }
        //����.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j 
        //this.�ŋ敪�i���N�j ��Ԃ��B
        else if (l_strDeliveryDate.compareTo(l_strBaseDate) > 0)
        {            
            l_taxType = accountRow.getTaxTypeNext();
        }
        //����.��n���̓��t��YYYY�������YYYY�̏ꍇ 
        //��O��throw����B
        else
        {
            log.info(STR_METHOD_NAME + " �F ��n���̓��t��YYYY�������YYYY�̏ꍇ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "��n�� = " + l_datDeliveryDate + " , ��� = " + l_datBaseDate);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_taxType;
    }
      
    /**
     * (is���A���ڋq)<BR>
     * ���A��������\������ڋq���ǂ����̔��ʂ�����B<BR>
     * 
     * this.�����擾�敪 = �h���A���q�h�̏ꍇtrue���A<BR>
     * this.�����擾�敪 = �h�f�B���C�q�h�̏ꍇfalse��ԋp����B<BR>
     * 
     * @@return boolean
     */
    public boolean isRealCustomer() 
    {
        if (WEB3QuoteTypeDef.REAL_CUSTOMER.equals(this.accountRow.getQuotoType())) 
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * (is��������J��) <BR>
     * �ڋq���A�����̎�n���ɁA����������J�݂��Ă��邩�`�F�b�N����B<BR> 
     * ����������J�݂��Ă���ꍇ��true���A�J�݂��Ă��Ȃ��ꍇ��false���A<BR> 
     * ���ꂼ��ԋp����B<BR> 
     *  <BR>
     * �P�j�@@GtlUtils.getTradingSystem().getSystemTimestamp( )�ɂ��A<BR> 
     *  �V�X�e�����t���擾����B<BR> 
     *  <BR>
     * �Q�j�@@����.��n���ƂP�j�Ŏ擾�����V�X�e�����t��YYYY�̒l�ɂ��A<BR>
     *  �ȉ��̔�����s���B <BR>
     *  <BR>
     * -------------------------------------------------------<BR> 
     * ������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ <BR>
     *  <BR> 
     *   �Ethis.�ŋ敪��TaxTypeEnum.SPECIAL�i����j <BR> 
     *     �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁A<BR>
     *     true��Ԃ��B<BR> 
     *   �Ethis.�ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁Afalse��Ԃ��B<BR> 
     *   �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR> 
     *      �N���X���FWEB3BusinessLayerException  <BR>
     *      �^�O�FBUSINESS_ERROR_00064 <BR>
     *  <BR>
     * ----------------------------------------------------------<BR> 
     * ������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ <BR> 
     *   �Ethis.�ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����j<BR> 
     *     �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁A<BR>
     *     true��Ԃ��B<BR> 
     *   �Ethis.�ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A<BR>
     *     false��Ԃ��B 
     *   �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     *      �N���X���FWEB3BusinessLayerException  <BR>
     *      �^�O�FBUSINESS_ERROR_00064 <BR> 
     *  <BR>
     * ----------------------------------------------------------<BR> 
     * ������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ <BR> 
     *   �E��O��throw����B 
     *      �N���X���FWEB3BusinessLayerException  <BR>
     *      �^�O�FBUSINESS_ERROR_00065 <BR>
     *  <BR>
     * @@param l_deliveryDate - (��n��) <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(
        Date l_deliveryDate,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(Date,SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@GtlUtils.getTradingSystem().getSystemTimestamp( )�ɂ��A
        //�V�X�e�����t���擾����
        Date l_datSystemDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        //get �V�X�e�����t��YYYY
        String l_strSystemDate = l_format.format(l_datSystemDate);
        //get ��n���̓��t��YYYY
        String l_strDeliveryDate = l_format.format(l_deliveryDate);
        
        //get�ŋ敪
        TaxTypeEnum l_taxType = accountRow.getTaxType();
        //get�ŋ敪�i���N�j
        TaxTypeEnum l_taxTypeNext = accountRow.getTaxTypeNext();
        
        boolean l_isSpecialAccountEstablished = false;
        //������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ
        if (l_strDeliveryDate.compareTo(l_strSystemDate) == 0)
        {
            //this.�ŋ敪��TaxTypeEnum.SPECIAL�i����j 
            //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B
            //this.�ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁Afalse��Ԃ��B
            //��L�ȊO�̏ꍇ�́A��O��throw����B
            if (TaxTypeEnum.SPECIAL.equals(l_taxType) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.info(
                    STR_METHOD_NAME + " �F �ŋ敪 = " + l_taxType);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                    "�ŋ敪 = " + l_taxType);
             }
            
        } 
        //������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ
        if (l_strDeliveryDate.compareTo(l_strSystemDate) > 0)
        {
            //this.�ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����)
            //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B
            //this.�ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A
            //false��Ԃ��B 
            //��L�ȊO�̏ꍇ�́A��O��throw����B     
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeNext) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.info(
                    STR_METHOD_NAME + " �F �ŋ敪�i���N�j = " + l_taxTypeNext);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                    "�ŋ敪�i���N�j = " + l_taxTypeNext);
            }  
        }
        //������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ
        if (l_strDeliveryDate.compareTo(l_strSystemDate) < 0)
        {
            //��O��throw����B
            log.info(STR_METHOD_NAME 
                + " �F ����.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "��n�� = " + l_strDeliveryDate + " , �V�X�e�����t = " + l_strSystemDate);
        }
        
        log.exiting(STR_METHOD_NAME);    
        return l_isSpecialAccountEstablished;
        
    }
    
    /**
     * (is��������J��) <BR>
     *  <BR>
     * �ڋq���A����ɑΉ������n���ɁA����������J�݂��Ă��邩<BR>
     * �`�F�b�N����B�i�`�F�b�N�̍ۂ͌��������������l�����A��n���Ƃ��Ăs�{�R�A<BR>
     * �s�{�S�̗����̓��t�����������J�ݗL���𔻒肷��B�j���������<BR>
     * �J�݂��Ă���ꍇ��true���A�J�݂��Ă��Ȃ��ꍇ��false���A���ꂼ��ԋp����B<BR> 
     *  <BR>
     * �P�j�@@GtlUtils.getTradingSystem().getSystemTimestamp( )�ɂ��A<BR> 
     *  ��t�������擾����B<BR> 
     *  <BR> 
     *  ���擾������t�������u����v�Ƃ���B<BR> 
     *  <BR>
     * �Q�j�@@�s�{�R�̓��t�̎擾 <BR>
     *  ����́A�R�c�Ɠ���̓��t���擾����B<BR> 
     *  <BR>
     * �R�j�@@�s�{�S�̓��t�̎擾 <BR>
     *  ����́A�S�c�Ɠ���̓��t���擾����B<BR> 
     *  <BR>
     * �S�j�@@�Q�j�ƂR�j�̓��t��YYYY�̒l�ɂ��A�ȉ��̔�����s���B<BR> 
     *  <BR>
     * ------------------------------------------------------------------<BR> 
     * ���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY�Ɠ������ꍇ<BR>
     *    �i�����N�ł���j <BR>
     *   �Ethis.�ŋ敪��TaxTypeEnum.SPECIAL�i����j<BR> 
     *     �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B<BR> 
     *   �Ethis.�ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁Afalse��Ԃ��B<BR>  
     *   �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR>  
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR>
     *  <BR> 
     * ------------------------------------------------------------------<BR>
     * ���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY��薢���̏ꍇ<BR>
     *   �i�����N�ł���j<BR> 
     *  �Ethis.�ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����j <BR> 
     *    �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�̏ꍇ�́Atrue��Ԃ��B<BR> 
     *  �Ethis.�ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�̏ꍇ�́Afalse��Ԃ��B<BR> 
     *  �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * -------------------------------------------------------------------<BR>
     * ���i�s�{�R�̓��t��YYYY�������YYYY�j�A <BR>
     *     ���i�s�{�S�̓��t��YYYY�j�������YYYY�j�̏ꍇ <BR> 
     *    �i���s�{�R�͍��N�A�s�{�S�͎��N�ł���j<BR> 
     *  �Ethis.�ŋ敪�Athis.�ŋ敪�i���N�j����TaxTypeEnum.NORMAL�i��ʁj�� <BR>
     *    ����ꍇ�́Afalse��Ԃ��B<BR> 
     *  �Ethis.�ŋ敪�܂���this.�ŋ敪�i���N�j���ATaxTypeEnum.SPECIAL�i����j<BR> 
     *    �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�̏ꍇ�́Atrue��Ԃ��B<BR> 
     *  �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * --------------------------------------------------------------------<BR> 
     * ����L�ȊO�̏ꍇ <BR>
     *  �E������ɂ����Ă͂܂�Ȃ��ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00136  <BR>
     *  <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(SubAccount)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@GtlUtils.getTradingSystem().getSystemTimestamp( )�ɂ��A
        //��t�������擾����B
        //���擾������t�������u����v�Ƃ���
        Date l_datStandardDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        // get �����YYYY
        String l_strStandardYear = l_format.format(l_datStandardDate);
        
        // get �c�Ɠ��v�Z
        WEB3GentradeBizDate l_dateCalc =
            new WEB3GentradeBizDate(new Timestamp(l_datStandardDate.getTime()));
        
        //�Q�j�s�{�R�̓��t�̎擾 
        //    ����́A�R�c�Ɠ���̓��t���擾����B
        Date l_datThreeDaysAfter = l_dateCalc.roll(3);
        String l_strT3Year = l_format.format(l_datThreeDaysAfter);
        //�R)�s�{�S�̓��t�̎擾 
        //����́A�S�c�Ɠ���̓��t���擾����B
        Date l_datFourDaysAfter = l_dateCalc.roll(4);
        String l_strT4Year = l_format.format(l_datFourDaysAfter);
        
        //get�ŋ敪
        TaxTypeEnum l_taxType = accountRow.getTaxType();
        //get�ŋ敪�i���N�j
        TaxTypeEnum l_taxTypeNext = accountRow.getTaxTypeNext();
        
        boolean l_isSpecialAccountEstablished = false;
        //���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY�Ɠ������ꍇ
        //�i�����N�ł���j
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) == 0))
        {
            //this.�ŋ敪��TaxTypeEnum.SPECIAL�i����j
            //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B
            //this.�ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁Afalse��Ԃ��B
            //��L�ȊO�̏ꍇ�́A��O��throw����
            if (TaxTypeEnum.SPECIAL.equals(l_taxType)
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.error(STR_METHOD_NAME + "�F�ŋ敪 = " + l_taxType);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                      + "." + STR_METHOD_NAME,
                    "�ŋ敪 = " + l_taxType);
            }
            return l_isSpecialAccountEstablished;
        }
        //���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY��薢���̏ꍇ
        //�i�����N�ł���j
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) > 0))
        {
            //this.�ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����j 
            //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�̏ꍇ�́Atrue��Ԃ��B
            //this.�ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�̏ꍇ�́Afalse��Ԃ��B
            //��L�ȊO�̏ꍇ�́A��O��throw����
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeNext) 
                || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = true;
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = false;
            }
            else
            {
                log.error(
                    STR_METHOD_NAME + "�F�ŋ敪�i���N�j = " + l_taxTypeNext);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                    "�ŋ敪�i���N�j = " + l_taxTypeNext);
            }
            return l_isSpecialAccountEstablished;
        }
        //���i�s�{�R�̓��t��YYYY�������YYYY�j�A 
        // ���i�s�{�S�̓��t��YYYY�j�������YYYY�j�̏ꍇ 
        //�i���s�{�R�͍��N�A�s�{�S�͎��N�ł���j
        if ((l_strT3Year.compareTo(l_strStandardYear) == 0)
            && (l_strT4Year.compareTo(l_strStandardYear) > 0))
        {
            //this.�ŋ敪�Athis.�ŋ敪�i���N�j����TaxTypeEnum.NORMAL�i��ʁj�� 
            //����ꍇ�́Afalse��Ԃ��B
            //this.�ŋ敪�܂���this.�ŋ敪�i���N�j���ATaxTypeEnum.SPECIAL�i����j
            //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�̏ꍇ�́Atrue��Ԃ��B
            //��L�ȊO�̏ꍇ�́A��O��throw����B
            if (l_taxType.equals(l_taxTypeNext) && TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_isSpecialAccountEstablished = false;
            }
            else if(TaxTypeEnum.SPECIAL.equals(l_taxType)
                     ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType)
                     ||TaxTypeEnum.SPECIAL.equals(l_taxTypeNext)
                     ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeNext))
            {
                l_isSpecialAccountEstablished = true;
            }
            else
            {
                log.error(STR_METHOD_NAME + "�F�ŋ敪 = " + l_taxType + " �A�ŋ敪�i���N�j = " + l_taxTypeNext);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                    WEB3GentradeMainAccount.class.getName()
                        + "." + STR_METHOD_NAME,
                   "�ŋ敪 = " + l_taxType + " �A�ŋ敪�i���N�j = " + l_taxTypeNext);
            }
            return l_isSpecialAccountEstablished;    
        }
        //����L�ȊO�̏ꍇ 
        //������ɂ����Ă͂܂�Ȃ��ꍇ�́A��O��throw����
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00136,
            WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
            "��� = " + l_strStandardYear + " �AT+3 = " + l_strT3Year + " �AT+4 = " + l_strT4Year);
  
    }
    
    /**
     * (is��������J��) <BR>
     *  <BR>
     * �ڋq���A�����̎�n���ɁA�����w��̕⏕�����A<BR>
     * �ŋ敪�^�C�v�ɑ΂�����������J�݂��Ă��邩�`�F�b�N����B<BR>
     * ����������J�݂��Ă���ꍇ��true���A�J�݂��Ă��Ȃ�<BR>
     * �ꍇ��false���A���ꂼ��ԋp����B<BR>
     *  <BR>
     * �P�j�@@����.�ŋ敪�^�C�v��"��������"�̏ꍇ�́A<BR> 
     *  this.is��������J��(��n��, �⏕����)��delegate���A<BR>
     *  �������I������B<BR> 
     *  �ȊO�A�ȉ��̏������s���B<BR> 
     *  <BR>
     * �Q�j�@@GtlUtils.getTradingSystem().getSystemTimestamp( )�ɂ��A<BR>
     *  �V�X�e�����t���擾����B<BR>
     *  <BR> 
     * �R�j�@@����.��n���ƂQ�j�Ŏ擾�����V�X�e�����t��YYYY�̒l�ɂ��A<BR>
     *  �ȉ��̔�����s���B<BR>
     *  <BR>
     * --------------------------------------------------------<BR> 
     * ������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ <BR> 
     *  <BR>
     *  ����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ�́A<BR> 
     *  �Ethis.�M�p����ŋ敪��TaxTypeEnum.SPECIAL�i����j<BR> 
     *   �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁A<BR>
     *   true��Ԃ��B<BR> 
     *  �Ethis.�M�p����ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A<BR>
     *    false��Ԃ��B <BR>
     *  �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * --------------------------------------------------------<BR>
     * ������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ <BR>
     *  <BR>
     *  ����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ�́A<BR> 
     *  �Ethis.�M�p����ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����j <BR>
     *    �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁A <BR>
     *    true��Ԃ��B <BR>
     *  �Ethis.�M�p����ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�� <BR>
     *    ����΁Afalse��Ԃ��B 
     *  �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR> 
     *  <BR>
     * --------------------------------------------------------<BR> 
     * ������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ <BR>
     *  �E��O��throw����B<BR> 
     *      �N���X���FWEB3BusinessLayerException  <BR>
     *      �^�O�FBUSINESS_ERROR_00065 <BR>
     *  <BR>
     * @@param l_deliveryDate - ��n�� <BR>
     * @@param l_subAccount - �⏕���� <BR>
     * @@param l_strTaxTypeDivDef - �ŋ敪�^�C�v <BR>
     *     (WEB3GentradeEquityMarginDivDef�ɂĒ�`) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(
        Date l_deliveryDate,
        SubAccount l_subAccount,
        String l_strTaxTypeDivDef)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(Date, SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����.�ŋ敪�^�C�v��"��������"�̏ꍇ�́A
        //this.is��������J��(��n��, �⏕����)��delegate���A
        //�������I������B
        if(WEB3GentradeEquityMarginDivDef.EQUITY.equals(l_strTaxTypeDivDef))
        {
            return this.isSpecialAccountEstablished(l_deliveryDate,l_subAccount);
        }
        
        //�Q�j�@@�V�X�e�����t���擾����B
        Date l_datSystemDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        //get �V�X�e�����t��YYYY
        String l_strSystemDate = l_format.format(l_datSystemDate);
        //get ��n���̓��t��YYYY
        String l_strDeliveryDate = l_format.format(l_deliveryDate);
        
        //get�M�p����ŋ敪
        TaxTypeEnum l_marginTaxType = accountRow.getMarginTaxType();
        //get�M�p����ŋ敪�i���N�j
        TaxTypeEnum l_marginTaxTypeNext = accountRow.getMarginTaxTypeNext();
             
        boolean l_isSpecialAccountEstablished = false;
        // ������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ
        if (l_strDeliveryDate.compareTo(l_strSystemDate) == 0)
        {
            // ����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.�M�p����ŋ敪��TaxTypeEnum.SPECIAL�i����j
                //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁A
                //true��Ԃ��B
                //this.�M�p����ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A
                //false��Ԃ��B 
                //��L�ȊO�̏ꍇ�́A��O��throw����B
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxType) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.info(
                        STR_METHOD_NAME + " �F �M�p����ŋ敪 = " + l_marginTaxType);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "�M�p����ŋ敪 = " + l_marginTaxType);
                 }
            }
        }
        //������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ
        if (l_strDeliveryDate.compareTo(l_strSystemDate) > 0)
        {
            // ����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.�M�p����ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����j 
                //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁A 
                //true��Ԃ��B 
                //this.�M�p����ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�� 
                //����΁Afalse��Ԃ��B 
                //��L�ȊO�̏ꍇ�́A��O��throw����
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxTypeNext) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.info(
                        STR_METHOD_NAME + "�F �M�p����ŋ敪�i���N�j = " + l_marginTaxTypeNext);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "�M�p����ŋ敪�i���N�j = " + l_marginTaxTypeNext);
                }
                
            }
        }
        //������.��n���̓��t��YYYY���V�X�e�����t��YYYY�̏ꍇ 
        if (l_strDeliveryDate.compareTo(l_strSystemDate) < 0)
        {
            //��O��throw����B
            log.info(STR_METHOD_NAME + " �F ��n���̓��t���V�X�e�����t�̏ꍇ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "��n�� = " + l_strDeliveryDate + " , �V�X�e�����t = " + l_strSystemDate);
        }
        
        log.exiting(STR_METHOD_NAME);    
        return l_isSpecialAccountEstablished;
    }
    
    /**
     * (is��������J��) <BR>
     *  <BR>
     *�ڋq���A����ɑΉ������n���ɁA�����w��̕⏕�����A<BR>
     *�ŋ敪�^�C�v�ɑ΂�����������J�݂��Ă��邩�`�F�b�N����B<BR>
     *�i�`�F�b�N�̍ۂ͌��������������l�����A��n���Ƃ��Ăs�{�R�A<BR>
     *�s�{�S�̗����̓��t�����������J�ݗL���𔻒肷��B�j<BR>
     *����������J�݂��Ă���ꍇ��true���A�J�݂��Ă��Ȃ��ꍇ��false���A<BR>
     *���ꂼ��ԋp����B <BR>
     *  <BR>
     * �P�j�@@����.�ŋ敪�^�C�v��"��������"�̏ꍇ�́A<BR> 
     *  this.is��������J��(�⏕����)��delegate���A�������I������B<BR> 
     *  �ȊO�A�ȉ��̏������s���B<BR> 
     *  <BR>
     * �Q�j�@@GtlUtils.getTradingSystem().getSystemTimestamp( )�ɂ��A<BR> 
     *  ��t�������擾����B<BR> 
     *  <BR> 
     *  ���擾������t�������u����v�Ƃ���B<BR> 
     *  <BR> 
     * �R�j�@@�s�{�R�̓��t�̎擾 <BR> 
     *  ����́A�R�c�Ɠ���̓��t���擾����B<BR>  
     *  <BR> 
     * �S�j�@@�s�{�S�̓��t�̎擾 <BR> 
     *  ����́A�S�c�Ɠ���̓��t���擾����B<BR>  
     *  <BR> 
     * �T�j�@@�R�j�ƂS�j�̓��t��YYYY�̒l�ɂ��A�ȉ��̔�����s���B<BR>  
     *  <BR>
     * --------------------------------------------------------------<BR>
     * ���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY�Ɠ������ꍇ<BR>
     *  �i�����N�ł���j<BR> 
     *  ����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ�́A<BR> 
     *  �Ethis.�M�p����ŋ敪��TaxTypeEnum.SPECIAL�i����j<BR> 
     *    �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B<BR> 
     *  �Ethis.�M�p����ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A<BR>
     *    false��Ԃ��B<BR> 
     *  �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * -------------------------------------------------------------<BR> 
     * ���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY��薢���̏ꍇ<BR>
     *  �i�����N�ł���j <BR>
     *  ����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ�́A<BR> 
     *  �Ethis.�M�p����ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����j <BR> 
     *    �܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B<BR> 
     *  �Ethis.�M�p����ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A<BR>
     *    false��Ԃ��B 
     *  �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * --------------------------------------------------------------<BR> 
     * ���i�s�{�R�̓��t��YYYY�������YYYY�j�A <BR>
     *  ���i�s�{�S�̓��t��YYYY�j�������YYYY�j�̏ꍇ <BR> 
     *  �i���s�{�R�͍��N�A�s�{�S�͎��N�ł���j <BR>
     *  ����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ�́A<BR> 
     *  �Ethis.�M�p����ŋ敪�Athis.�M�p����ŋ敪�i���N�j���� <BR>
     *    TaxTypeEnum.NORMAL�i��ʁj�ł���ꍇ�́Afalse��Ԃ��B <BR>
     *  �Ethis.�M�p����ŋ敪�܂���this.�M�p����ŋ敪�i���N�j���A<BR>
     *    TaxTypeEnum.SPECIAL�i����j�܂���SPECIAL_WITHHOLD<BR>
     *    �i���肩���򒥎��j�̏ꍇ�́Atrue��Ԃ��B<BR> 
     *  �E��L�ȊO�̏ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00064  <BR>
     *  <BR>
     * ---------------------------------------------------------------<BR> 
     * ����L�ȊO�̏ꍇ <BR>
     *  �E������ɂ����Ă͂܂�Ȃ��ꍇ�́A��O��throw����B<BR> 
     *         class�FWEB3BusinessLayerException  <BR>
     *         tag�F  BUSINESS_ERROR_00136  <BR>
     *  <BR> 
     * @@param l_subAccount - (�⏕����) <BR>
     * @@param l_strTaxTypeDivDef - (�ŋ敪�^�C�v) <BR>
     *     (WEB3GentradeEquityMarginDivDef�ɂĒ�`) <BR>
     *  <BR>
     * @@throws WEB3BaseException <BR>
     */
    public boolean isSpecialAccountEstablished(SubAccount l_subAccount,String l_strTaxTypeDivDef) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSpecialAccountEstablished(SubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����.�ŋ敪�^�C�v��"��������"�̏ꍇ�́A
        // this.is��������J��(�⏕����)��delegate���A�������I������B
        if(WEB3GentradeEquityMarginDivDef.EQUITY.equals(l_strTaxTypeDivDef))
        {
            return this.isSpecialAccountEstablished(l_subAccount);
        }
        
        //�Q�j�@@GtlUtils.getTradingSystem().getSystemTimestamp( )�ɂ��A
        //��t�������擾����B
        //���擾������t�������u����v�Ƃ���B
        Date l_datStandardDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        // get �����YYYY
        String l_strStandardYear = l_format.format(l_datStandardDate);
        
        // get �c�Ɠ��v�Z
        WEB3GentradeBizDate l_dateCalc =
            new WEB3GentradeBizDate(new Timestamp(l_datStandardDate.getTime()));
        
        //�R�j�s�{�R�̓��t�̎擾 
        //    ����́A�R�c�Ɠ���̓��t���擾����B
        Date l_datThreeDaysAfter = l_dateCalc.roll(3);
        String l_strT3Year = l_format.format(l_datThreeDaysAfter);
        //�S)�s�{�S�̓��t�̎擾 
        //����́A�S�c�Ɠ���̓��t���擾����B
        Date l_datFourDaysAfter = l_dateCalc.roll(4);
        String l_strT4Year = l_format.format(l_datFourDaysAfter);
        
        //get�M�p����ŋ敪
        TaxTypeEnum l_marginTaxType = accountRow.getMarginTaxType();
        //get�M�p����ŋ敪�i���N�j
        TaxTypeEnum l_marginTaxTypeNext = accountRow.getMarginTaxTypeNext();
        
        boolean l_isSpecialAccountEstablished = false;
        //���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY�Ɠ������ꍇ
        // �i�����N�ł���j
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) == 0))
        {
            //����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.�M�p����ŋ敪��TaxTypeEnum.SPECIAL�i����j
                //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B
                //this.�M�p����ŋ敪��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A
                //false��Ԃ��B
                //��L�ȊO�̏ꍇ�́A��O��throw����
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxType) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.info(
                        STR_METHOD_NAME + " �F �M�p����ŋ敪 = " + l_marginTaxType);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "�M�p����ŋ敪 = " + l_marginTaxType);
                 }
            }
            return l_isSpecialAccountEstablished;
        }
        //���s�{�R�̓��t��YYYY�A�s�{�S�̓��t��YYYY���Ɋ����YYYY��薢���̏ꍇ
        //�i�����N�ł���j
        if ((l_strT3Year.compareTo(l_strT4Year) == 0)
            && (l_strT3Year.compareTo(l_strStandardYear) > 0))
        {
            //����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.�M�p����ŋ敪�i���N�j��TaxTypeEnum.SPECIAL�i����j 
                //�܂���SPECIAL_WITHHOLD�i���肩���򒥎��j�ł���΁Atrue��Ԃ��B
                //this.�M�p����ŋ敪�i���N�j��TaxTypeEnum.NORMAL�i��ʁj�ł���΁A
                //false��Ԃ��B 
                //��L�ȊO�̏ꍇ�́A��O��throw����B
                if (TaxTypeEnum.SPECIAL.equals(l_marginTaxTypeNext) 
                    || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else if (TaxTypeEnum.NORMAL.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else
                {
                    log.debug(
                        STR_METHOD_NAME + "�F �M�p����ŋ敪�i���N�j = " + l_marginTaxTypeNext);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "�M�p����ŋ敪�i���N�j = " + l_marginTaxTypeNext);
                }
            }
            return l_isSpecialAccountEstablished;
        }
        //���i�s�{�R�̓��t��YYYY�������YYYY�j�A
        // ���i�s�{�S�̓��t��YYYY�j�������YYYY�j�̏ꍇ�i���s�{�R�͍��N�A�s�{�S�͎��N�ł���j
        if ((l_strT3Year.compareTo(l_strStandardYear) == 0)
            && (l_strT4Year.compareTo(l_strStandardYear) > 0))
        {
            //����.�ŋ敪�^�C�v��"�M�p���"�̏ꍇ
            if(WEB3GentradeEquityMarginDivDef.MARGIN.equals(l_strTaxTypeDivDef))
            {
                //this.�M�p����ŋ敪�Athis.�M�p����ŋ敪�i���N�j����
                //TaxTypeEnum.NORMAL�i��ʁj�ł���ꍇ�́Afalse��Ԃ��B
                //this.�M�p����ŋ敪�܂���this.�M�p����ŋ敪�i���N�j���A
                //TaxTypeEnum.SPECIAL�i����j�܂���SPECIAL_WITHHOLD 
                //�i���肩���򒥎��j�̏ꍇ�́Atrue��Ԃ��B
                //��L�ȊO�̏ꍇ�́A��O��throw����
                if (l_marginTaxType.equals(l_marginTaxTypeNext) && TaxTypeEnum.NORMAL.equals(l_marginTaxType))
                {
                    l_isSpecialAccountEstablished = false;
                }
                else if(TaxTypeEnum.SPECIAL.equals(l_marginTaxType)
                         ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxType)
                         ||TaxTypeEnum.SPECIAL.equals(l_marginTaxTypeNext)
                         ||TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_marginTaxTypeNext))
                {
                    l_isSpecialAccountEstablished = true;
                }
                else
                {
                    log.info(STR_METHOD_NAME 
                        + "�F �M�p����ŋ敪 = " 
                        + l_marginTaxType 
                        + " �A�M�p����ŋ敪�i���N�j = " 
                        + l_marginTaxTypeNext);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00064,
                        WEB3GentradeMainAccount.class.getName()
                            + "." + STR_METHOD_NAME,
                        "�M�p����ŋ敪 = " + l_marginTaxType + " �A�M�p����ŋ敪�i���N�j = " + l_marginTaxTypeNext);
                }
            }
            return l_isSpecialAccountEstablished;                
        }
        
        //����L�ȊO�̏ꍇ 
        //������ɂ����Ă͂܂�Ȃ��ꍇ�́A��O��throw����B
        throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00136,
            WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
            "��� = " + l_strStandardYear + " , T+3 = " + l_strT3Year + " , T+4 = " + l_strT4Year);
    }
    
    /**
     * (is�M�p�����J��) <BR>
     *  <BR>
     * ���Y�ڋq���A�w��̐M�p����������J�݂��Ă��邩�ǂ����𔻒肷��B<BR>
     *  <BR>
     * �������ٍ̕ϋ敪���h�w��Ȃ��h�̏ꍇ <BR>
     *    �|this.���x�M�p��������J�݋敪���h�����J�݁h�A<BR>
     *       �܂��� this.��ʐM�p��������J�݋敪���h�����J�݁h <BR>
     *       �̏ꍇ�̂݁Atrue��Ԃ��B <BR>
     *    �|�ȊO�Afalse��Ԃ��B <BR>
     *  <BR>
     * �������ٍ̕ϋ敪���h���x�M�p�h�̏ꍇ <BR>
     *    �|this.���x�M�p��������J�݋敪���h�����J�݁h <BR>
     *       �̏ꍇ��true��Ԃ��B <BR>
     *    �|�ȊO�Afalse��Ԃ��B<BR>
     *  <BR>
     * �������ٍ̕ϋ敪���h��ʐM�p�h�̏ꍇ <BR>
     *    �|this.��ʐM�p��������J�݋敪���h�����J�݁h <BR>
     *       �̏ꍇ��true��Ԃ��B<BR>
     *    �|�ȊO�Afalse��Ԃ��B <BR> 
     *  <BR>
     * �������ٍ̕ϋ敪����L�ȊO�̏ꍇ <BR>
     *    �|��O��throw����B <BR>
     *        class    : WEB3BaseRuntimeException<BR>
     *        tag      : SYSTEM_ERROR_80017<BR>
     *  <BR>
     * @@param l_strRepaymentType - (�ٍϋ敪) <BR>
     *   (WEB3GentradeRepaymentDivDef�ɂĒ�`) <BR>
     */
    public boolean isMarginAccountEstablished(String l_strRepaymentType)
    {
        final String STR_METHOD_NAME = "isMarginAccountEstablished(String)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_isMarginAccountEstablished = false;
        //�������ٍ̕ϋ敪���h�w��Ȃ��h�̏ꍇ
        if(WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
        {
            //this.���x�M�p��������J�݋敪���h�����J�݁h�A
            //�܂��� this.��ʐM�p��������J�݋敪���h�����J�݁h 
            //�̏ꍇ�̂݁Atrue��Ԃ�
            if(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginGenAccOpenDiv())
             || WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginSysAccOpenDiv()))
            {
                l_isMarginAccountEstablished = true;
            }
            else
            {
                l_isMarginAccountEstablished = false;
            }
        }
        //�������ٍ̕ϋ敪���h���x�M�p�h�̏ꍇ 
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
        {
            //this.���x�M�p��������J�݋敪���h�����J�݁h �̏ꍇ��true��Ԃ��B 
            if(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginSysAccOpenDiv()))
            {
                l_isMarginAccountEstablished = true;
            }
            else
            {
                l_isMarginAccountEstablished = false;
            }
        }
        //�������ٍ̕ϋ敪���h��ʐM�p�h�̏ꍇ 
        else if(WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
        {
            //this.��ʐM�p��������J�݋敪���h�����J�݁h�̏ꍇ��true��Ԃ�
            if(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN.equals(this.accountRow.getMarginGenAccOpenDiv()))
            {
                l_isMarginAccountEstablished = true;
            }
            else
            {
                l_isMarginAccountEstablished = false;
            }
            
        }
        //�������ٍ̕ϋ敪����L�ȊO�̏ꍇ
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ٍ̕ϋ敪 = " + l_strRepaymentType);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isMarginAccountEstablished;
    }
    
    /**
     * (is�a��،��]����) <BR>
     *  <BR>
     * �ڋq���a��،��]�����ڋq���ǂ����𔻕ʂ���B <BR>
     * [�߂�l] true�F�@@���{ false�F�@@�����{ <BR>
     *  <BR>
     * �P�jDB���� <BR>
     * �ȉ��̏����ŗ]�͏����e�[�u���������� <BR> 
     * �a��،��]�����敪���擾����B <BR> 
     *  <BR>
     * [����] <BR> 
     * ����ID = this.getAccountId() <BR>
     * <BR> 
     * �Q�j�،���ЃI�u�W�F�N�g���擾����B<BR> 
     * �،���� = this.getInstitution() <BR> 
     *  <BR>
     * �R�j�a��،��]�������ǂ������ʂ���B<BR>
     *  <BR> 
     * [�،����.�a��،��]���� = �h�����{�h�̏ꍇ] <BR>
     * false��ԋp����B <BR>
     *  <BR>
     * [�،����.�a��،��]���� = �h���{�h�@@���� <BR>
     * �a��،��]�����敪 = �h�����{�h�̏ꍇ] <BR>
     * false��ԋp����B<BR> 
     *  <BR>
     * [�،����.�a��،��]���� = �h���{�h�@@���� <BR> 
     * �a��،��]�����敪 = �h���{�h�̏ꍇ] <BR>
     * true��ԋp����B <BR>
     *  <BR>
     * @@throws WEB3SystemLayerException
     */
    public boolean isAssetEvaluation() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "isAssetEvaluation()";
            
        //�P�jDB���� 
        //�ȉ��̏����ŗ]�͏����e�[�u���������� 
        //�a��،��]�����敪���擾����B
        TradingpowerCalcConditionRow l_tradingpowerCalcConditionRow = null;
        try
        {
            l_tradingpowerCalcConditionRow = 
                TradingpowerCalcConditionDao.findRowByAccountId(this.getAccountId());
        }
        catch(DataException de)
        {
            WEB3SystemLayerException l_wse = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de
                );
            log.error(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }
        if(l_tradingpowerCalcConditionRow == null)
        {
            WEB3SystemLayerException l_wse = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�]�͏����e�[�u������������(����ID = " + this.getAccountId() + ")"
                );
            log.debug(STR_METHOD_NAME,l_wse);
            throw l_wse;
        }
        
        //�Q�j�،���ЃI�u�W�F�N�g���擾����
        Institution l_institution = this.getInstitution();
        InstitutionRow l_institutionRow  = (InstitutionRow)l_institution.getDataSourceObject();
        
        //�R�j�a��،��]�������ǂ������ʂ���B
        boolean l_isAssetEvaluation = false;
        //�،����.�a��،��]���� = �h���{�h
        if(WEB3EnforcementDef.ENFORCEMENT.equals(l_institutionRow.getAssetEvaluation()))
        {
            //�a��،��]�����敪 = �h���{�h
            if(WEB3EnforcementDef.ENFORCEMENT.equals(l_tradingpowerCalcConditionRow.getAssetEvaluationDiv()))
            {
                l_isAssetEvaluation = true;
            }
        }
        
        return l_isAssetEvaluation;                    

    }
    
    /**
     * (get�U������Z�@@��) <BR>
     *  <BR>
     * �ڋq�Ɋ֘A����U������Z�@@�փI�u�W�F�N�g���擾����B<BR>
     *  <BR>
     * �P�j�ȉ��̍��ڂ��擾����B<BR>
     *  <BR>
     * �،���ЃR�[�h�F this.getInstitution().getInstitutionCode()�̖߂�l <BR>
     * ���X�R�[�h�F this.getBranch().getBranchCode()�̖߂�l<BR>
     * �ڋq�R�[�h�F this.�ڋq�R�[�h<BR>
     * <BR>
     * �Q�j�P�j�Ŏ擾�������ڂ������Ƃ��āA�U������Z�@@�փI�u�W�F�N�g��<BR>
     * �擾���ԋp����B<BR>
     *   �w��敪�F 'A'<BR>
     *  <BR>
     * @@return WEB3GentradeTransferedFinInstitution - �U������Z�@@��<BR>
     * @@throws WEB3BaseException <BR>
     */
    public WEB3GentradeTransferedFinInstitution getTransferedFinInstitution()
        throws WEB3BaseException
    {
        return new WEB3GentradeTransferedFinInstitution(
            this.getInstitution().getInstitutionCode(),
            this.getBranch().getBranchCode(),
            this.accountCode,
            "A");
    }
    
    /**
     * (get�U����o�^�敪) <BR>
     *  <BR>
     * this.�U����i��s�����j�o�^�敪��ԋp����B<BR>
     *  <BR>
     * @@return String - �U����o�^�敪<BR>
     */
    public String getTransferedRegistDiv()
    {
        return this.accountRow.getBankAccountRegi();
    }
    
    /**
     * (get�\���ڋq�R�[�h) <BR>
     *  <BR>
     * �ڋq�R�[�h�i�\���p�j���擾����B <BR>
     *  <BR>
     * this.�ڋq�}�X�^Params.�ڋq�R�[�h�̍�6byte��ԋp����B <BR>
     *  <BR>
     * @@return String - �\���p�ڋq�R�[�h<BR>
     */
    public String getDisplayAccountCode()
    {
        String l_strAccountCode = this.accountRow.getAccountCode();
        return l_strAccountCode.substring(0,6);
    }
    
    /**
     * (get�ڋq�\����) <BR>
     *  <BR>
     * �ڋq�����擾����B <BR>
     *  <BR>
     * �u�� + �h�@@�h + ���v�̃t�H�[�}�b�g�ŕԋp����B <BR>
     *  <BR>
     * �P�j�@@�ڋq���擾 <BR> 
     * getNameDetails()�ɂāAPersonNameDetails�I�u�W�F�N�g���擾����B<BR> 
     *  <BR>
     * �Q�j�@@�ڋq����\���p�ɕҏW����B<BR> 
     * PersonNameDetails�I�u�W�F�N�g���A<BR> 
     * ���igetFamilyName()�j�A���igetGivenName()�j���擾���A <BR>
     * �\���p�ɕҏW�i���Ɩ��̊ԂɑS�pSpace��}���j���ĕԋp����B <BR>
     *  <BR>
     *     PersonNameDetail.getFamilyName() + �h�@@�h  <BR>
     * + PersonNameDetail.getGivenName()<BR> 
     *  <BR>
     * ��PersonNameDetail.getGivenName()�̖߂�l��null�̏ꍇ�́A<BR>
     * PersonNameDetail.getFamilyName()�̖߂�l��ԋp����B<BR>
     *  <BR>
     * @@return String - �\���p�ڋq��<BR>
     */
    public String getDisplayAccountName()
    {
        //�P�j�@@�ڋq���擾
        PersonNameDetails l_personNameDetails = this.getNameDetails();
        
        //�Q�j�@@�ڋq����\���p�ɕҏW����
        String l_strName; 
        if (l_personNameDetails.getGivenName() != null)
        {
            l_strName =
                l_personNameDetails.getFamilyName()
                    + "�@@"
                    + l_personNameDetails.getGivenName();
        }
        else
        {
            l_strName = l_personNameDetails.getFamilyName();
        }
        return l_strName;
    }
    

    /**
     * (get���O�C���h�c)<BR>
     *  <BR>
     * �ڋq�ɊY�����郍�O�C���h�c���擾����B<BR>
     *  <BR>
     * ���O�C���e�[�u�����������A�Y�����郍�O�C���s���擾����B<BR> 
     *  <BR>
     * [��������]<BR>
     * ���O�C��.�����h�c = this.getAccountId()<BR>
     * �擾�������O�C��.���O�C���h�c��ԋp����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ�A�܂��́A�������擾�ł����ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * @@return long - ���O�C���h�c<BR>
     */
    public long getLoginId()
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getLoginId()";
        log.entering(STR_METHOD_NAME);
        
        long l_lngLoginId = 0;
        long l_lngAccountId = this.getAccountId();
        
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            List l_lisRecords = null;

            //[��������]
            //���O�C��.�����h�c = this.getAccountId()
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    LoginRow.TYPE,
                    "account_id = ? ",
                    new Object[] { "" + l_lngAccountId });

            //�Y���s�����݂��Ȃ��ꍇ�A�܂��́A�������擾�ł����ꍇ�͗�O���X���[����B
            if(l_lisRecords.size() == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if(l_lisRecords.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            LoginRow l_loginRow = (LoginRow)l_lisRecords.get(0);
            
            l_lngLoginId = l_loginRow.getLoginId();
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lngLoginId;
    }   
    
    /**
     * (get��n���M�p����ŋ敪)<BR>
     *  <BR>
     * �ڋq�́A�����̎�n���ɂ�����M�p����ŋ敪�iTaxTypeEnum�j��ԋp����B<BR>
     *  <BR>
     * ����ƂȂ�V�X�e�����t���擾����B<BR>
     * �iGtlUtils.getTradingSystem().getSystemTimestamp( )�j<BR>
     *  <BR>
     * ����.��n���Ǝ擾���������YYYY�̒l�ɂ��A�ȉ��̔�����s���B<BR>
     *  <BR>
     * ������.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j<BR>
     * �Ethis.�M�p����ŋ敪 ��Ԃ��B<BR>
     *  <BR>
     * ������.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j<BR>
     * �Ethis.�M�p����ŋ敪�i���N�j ��Ԃ��B<BR>
     *  <BR>
     * ������.��n���̓��t��YYYY�������YYYY�̏ꍇ<BR>
     * �E��O��throw����B<BR>
     *  class�FWEB3BusinessLayerException <BR>
     *  tag�F  BUSINESS_ERROR_00065 <BR>
     * <BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     *     �������.getDailyDeliveryDate()<BR>
     * @@return TaxTypeEnum <BR>
     * @@throws webbroker3.common.WEB3BusinessLayerException<BR>
     */
    public TaxTypeEnum getDeliveryDateMarginTaxType(Date l_datDeliveryDate)
        throws WEB3BusinessLayerException
    {
        TaxTypeEnum l_marginTaxType;
        
        final String STR_METHOD_NAME = "getDeliveryDateMarginTaxType(Date)";
        log.entering(STR_METHOD_NAME);
        
        //����ƂȂ�V�X�e�����t���擾����
        Date l_datBaseDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YEAR);
        
        //����.�����YYYY�̒l
        String l_strBaseYear = l_format.format(l_datBaseDate);
        //����.��n����YYYY�̒l
        String l_strDeliveryYear = l_format.format(l_datDeliveryDate);
        
        if (l_strDeliveryYear.compareTo(l_strBaseYear) == 0)
        {
            // ������.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j
            // �Ethis.�M�p����ŋ敪 ��Ԃ��B
            l_marginTaxType = accountRow.getMarginTaxType();
        }
        else if (l_strDeliveryYear.compareTo(l_strBaseYear) > 0)
        {
            // ������.��n���̓��t��YYYY�������YYYY�̏ꍇ�i�����N�ł���j
            // �Ethis.�M�p����ŋ敪�i���N�j ��Ԃ��B
            l_marginTaxType = accountRow.getMarginTaxTypeNext();
        }
        else
        {
            // ������.��n���̓��t��YYYY�������YYYY�̏ꍇ
            // �E��O��throw����B
            log.info(STR_METHOD_NAME + " �F ��n���̓��t��YYYY�������YYYY�̏ꍇ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00065,
                WEB3GentradeMainAccount.class.getName()
                    + "." + STR_METHOD_NAME,
                "��n�� = " + l_datDeliveryDate + " , ��� = " + l_datBaseDate);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_marginTaxType;
    }
  
    /**
     * (is�敨OP�����J��)<BR>
     *  <BR>
     * �ڋq���A�w�肳�ꂽ�敨�^�I�v�V�����敪�� <BR>
     * �Y������������J�݂��Ă��邩���ʂ���B<BR>
     * �J�݂��Ă���ꍇ��true�A�ȊOfalse��ԋp����B<BR>
     *  <BR>
     * �P�j�����J�݋敪�`�F�b�N <BR>
     *  <BR>
     * [�p�����[�^.�敨�^�I�v�V�����敪==�h�I�v�V�����h�̏ꍇ] <BR>
     * �ȉ��̍��ڂ̉��ꂩ���hOP�����J�݁h�܂��́h�敨OP�����J�݁h <BR>
     * �ɂȂ��Ă���΁Atrue��ԋp����B <BR>
     * ���ׂĂ̍��ڂ��hDEFAULT�i�����Ȃ��j�h�܂��́h�敨�����J�݁h�� <BR>
     * �����false��ԋp����B <BR>
     *  <BR>
     *   this.�敨OP�����J�݋敪�i���؁j <BR>
     *   this.�敨OP�����J�݋敪�i��؁j <BR> 
     *   this.�敨OP�����J�݋敪�i���؁j <BR> 
     *  <BR>
     * [�p�����[�^.�敨�^�I�v�V�����敪==�h�敨�h�̏ꍇ] <BR>
     * �ȉ��̍��ڂ̉��ꂩ���h�敨�����J�݁h�܂��́h�敨OP�����J�݁h <BR>
     * �ɂȂ��Ă���΁Atrue��ԋp����B<BR>
     * ���ׂĂ̍��ڂ��hDEFAULT�i�����Ȃ��j�h�܂��́hOP�����J�݁h�� <BR>
     * �����false��ԋp����B <BR>
     *  <BR>
     *   this.�敨OP�����J�݋敪�i���؁j <BR>
     *   this.�敨OP�����J�݋敪�i��؁j <BR>
     *   this.�敨OP�����J�݋敪�i���؁j <BR>
     *  <BR>
     * @@param l_strFuturesOptionDiv - (�敨�^�I�v�V�����敪)
     *    1�F�@@�敨  2�F�@@�I�v�V����
     * @@return boolean 
     */
    public boolean isIfoAccountOpen(String l_strFuturesOptionDiv)
    {
        final String STR_METHOD_NAME = "isIfoAccountOpen(String)";
        log.entering(STR_METHOD_NAME);
        
        if ((!WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuturesOptionDiv))
            && (!WEB3FuturesOptionDivDef.OPTION.equals(l_strFuturesOptionDiv)))
        {
            log.error( "�敨�^�I�v�V�����敪 = " + l_strFuturesOptionDiv);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�敨�^�I�v�V�����敪 = " + l_strFuturesOptionDiv);
        }
        
        //get �敨OP�����J�݋敪�i���؁j
        String l_strIfoAccOpenDivTokyo = this.accountRow.getIfoAccOpenDivTokyo();
        //get �敨OP�����J�݋敪�i��؁j
        String l_strIfoAccOpenDivOsaka = this.accountRow.getIfoAccOpenDivOsaka();
        //get �敨OP�����J�݋敪�i���؁j
        String l_strIfoAccOpenDivNagoya = this.accountRow.getIfoAccOpenDivNagoya();
        
        boolean l_isIfoAccountOpen = false;
        //[�p�����[�^.�敨�^�I�v�V�����敪==�h�I�v�V�����h�̏ꍇ]
        if(WEB3FuturesOptionDivDef.OPTION.equals(l_strFuturesOptionDiv))
        {
            if(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
            {
                l_isIfoAccountOpen = true;
            }
        }
        //[�p�����[�^.�敨�^�I�v�V�����敪==�h�敨�h�̏ꍇ] 
        else
        {
            if(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
             ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
            {
                l_isIfoAccountOpen = true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isIfoAccountOpen;
    }
   
    /**
     * (getOP��������^�C�v)<BR>
     *  <BR>
     * �ڋq�̃I�v�V������������𔻕ʂ��A�Y���̕⏕�����^�C�v <BR>
     * �iSubAccountTypeEnum�j��ԋp����B�I�v�V�����������J�� <BR>
     * �̏ꍇ�́A��O��throw����B <BR>
     *  <BR>
     * �P�j�@@�؋��������J�ݍς̏ꍇ<BR>
     * �ڋq.�敨OP�����J�݋敪�i���؁j==3�F�h�敨OP�����J�݁hor<BR>
     * �ڋq.�敨OP�����J�݋敪�i��؁j==3�F�h�敨OP�����J�݁hor<BR>
     * �ڋq.�敨OP�����J�݋敪�i���؁j==3�F�h�敨OP�����J�݁h <BR>
     * <BR>
     * SubAccountTypeEnum.�����I�v�V�����������(�敨�؋���)(=7)<BR>
     * ��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�؋����������J�݂̏ꍇ<BR>
     * �ڋq.�敨OP�����J�݋敪�i���؁j==1�F�hOP�����J�݁hor<BR>
     * �ڋq.�敨OP�����J�݋敪�i��؁j==1�F�hOP�����J�݁hor<BR>
     * �ڋq.�敨OP�����J�݋敪�i���؁j==1�F�hOP�����J�݁h<BR>  
     * <BR>
     * SubAccountTypeEnum.�����������(�a���)(=1)�@@��ԋp����B<BR>
     * <BR>
     * �R�j�@@�I�v�V�����������J�݂̏ꍇ(�P�j�A�Q�j�ȊO)<BR>
     * <BR>
     * �u�I�v�V�����������J�݃G���[�v�̗�O��throw����B<BR>
     *      class�FWEB3BusinessLayerException  <BR>
     *      tag�F  BUSINESS_ERROR_00283  <BR>
     *  <BR>
     * @@return SubAccountTypeEnum
     * @@throws WEB3BusinessLayerException
     */
    public SubAccountTypeEnum getOpSubAccountType()
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "getOPSubAccountType()";
        log.entering(STR_METHOD_NAME);
        
        //get �敨OP�����J�݋敪�i���؁j
        String l_strIfoAccOpenDivTokyo = this.accountRow.getIfoAccOpenDivTokyo();
        //get �敨OP�����J�݋敪�i��؁j
        String l_strIfoAccOpenDivOsaka = this.accountRow.getIfoAccOpenDivOsaka();
        //get �敨OP�����J�݋敪�i���؁j
        String l_strIfoAccOpenDivNagoya = this.accountRow.getIfoAccOpenDivNagoya();
        
        if(WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
         ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
         ||WEB3FutureOpAccountDef.FUTURE_OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
        {
            //�P�j�@@�؋��������J�ݍς̏ꍇ 
            //(�敨�؋���)(=7)�@@��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
        }
        else if(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivTokyo)
         ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivOsaka)
         ||WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH.equals(l_strIfoAccOpenDivNagoya))
        {
            //�Q�j�@@�؋����������J�݂̏ꍇ
            //SubAccountTypeEnum.�����������(�a���)(=1)�@@��ԋp����
            log.exiting(STR_METHOD_NAME);
            return SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;

        }
        else
        {
            // �R�j�@@�I�v�V�����������J�݂̏ꍇ(�P�j�A�Q�j�ȊO) 
            //�u�I�v�V�����������J�݃G���[�v�̗�O��throw����B
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00283,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (is�����~����)<BR>
     *  <BR>
     * �ڋq�E�����ʎ����~�`�F�b�N�B<BR>
     * �|�ڋq���A�����Ŏw�肳�ꂽ�����{����ɂ��āA<BR>
     * �����~���ł��邩�ǂ�����Ԃ��B<BR>
     * �iDB���C�A�E�g�u�ڋq�����ʎ����~�e�[�u���d�l.xls�v�Q�Ɓj<BR>
     *  <BR>
     * �P�j�@@�`�F�b�N���{�L���̔���<BR>
     *  <BR>
     * ������������ł���ꍇ(*)�́A�ȍ~�̏����𑱍s����B<BR>
     * ������������ł��Ȃ��ꍇ�́A����������false�i�������~�����łȂ��j��<BR>
     * return����B<BR>
     *  <BR>
     * (*) ������������ł���ꍇ<BR>
     * ������ԊǗ�.get������()�Ő���ɔ��������擾�ł���<BR>
     * �i��O���X���[����Ȃ��j�ꍇ�́A������������ł���Ɣ��f����B<BR>
     * ������ԊǗ�.get������()�Ŕ��������擾�ł��Ȃ�<BR>
     * �i��O���X���[�����j�ꍇ�́A������������ł��Ȃ��Ɣ��f����B<BR>
     *  <BR>
     * �Q�j�@@DB���� <BR>
     * �ڋq�����ʎ����~�e�[�u�����ȉ��̏����Ō�������B<BR>
     *  <BR>
     *    [����] <BR>
     * �،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * ���XID = this.���XID<BR>
     * ����ID = this.����ID<BR>
     * ����ID in �i"�S����", �����̖���ID�j<BR>
     * �K�p�J�n�N���� ���� ������ԊǗ�.get������(*1)<BR>
     * �K�p�I���N���� ���� ������ԊǗ�.get������(*1)<BR>
     *  <BR>
     * �R�j�@@�w�����̒�~�����ǂ����̔��� <BR>
     * �擾�����f�[�^�̂����ꂩ�ŁA�Y����������~����(*1)�� <BR>
     * "��~��"�̏ꍇ�́Atrue��Ԃ��B��L�ȊO�A�܂��͊Y���f�[�^�Ȃ�<BR>
     * �̏ꍇ�́Afalse��Ԃ��B<BR>
     *  <BR>
     * (*1)�Y����������~���ڂ́A�����́u������ʁv���ȉ��̂悤��<BR>
     * ���肷��B<BR>
     *  <BR>
     * "����������"�F�@@�����������~<BR>
     * "����������"�F�@@�����������~<BR>
     * "�V�K��������"�F�@@���V�K�������~<BR>
     * "�V�K��������"�F�@@���V�K�������~<BR>
     * "�����ԍϒ���"�F�@@�����ԍρi���ԍρj�����~<BR>
     * "�����ԍϒ���"�F�@@�����ԍρi���ԍρj�����~<BR>
     * "��������"�F�@@���������~<BR>
     * "���n����"�F�@@���n�����~<BR>
     * "�����~�j��������"�F�@@���~�j�������~<BR>
     * "�����~�j��������"�F�@@���~�j�������~<BR>
     * "�O������"�F�@@�����������~<BR>
     * "�O������"�F�@@�����������~<BR> 
     *  <BR>   
     * @@param l_lngProductId (����ID)
     * @@param l_orderTypeEnum (�������)
     * @@throws WEB3SystemLayerException
     */
    public boolean isTradeStopProduct(
        long l_lngProductId,
        OrderTypeEnum l_orderTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeStopProduct(long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        final long ALL_PRODUCT = 0;
        
        //�P�j�@@�`�F�b�N���{�L���̔���
        //������������ł���ꍇ(*)�́A�ȍ~�̏����𑱍s����B
        //������������ł��Ȃ��ꍇ�́A����������false�i�������~�����łȂ��j��return����B
        Date l_bizDate = null;
        try
        {
            l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        catch(WEB3SystemLayerException wse)
        {
            l_bizDate = null;
        }
        if(l_bizDate == null)
        {
            return false;
        }
               
        //�Q�j �ڋq�����ʎ����~�e�[�u�����ȉ��̏����Ō�������B
        //[����] 
        //�،���ЃR�[�h = this.�،���ЃR�[�h
        //���XID = this.���XID
        //����ID = this.����ID
        //����ID in �i"�S����", �����̖���ID�j
        //�K�p�J�n�N���� ���� ������ԊǗ�.get������(void)
        //�K�p�I���N���� ���� ������ԊǗ�.get������(void)
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_id = ? ");
        l_sbWhere.append(" and account_id = ? ");
        l_sbWhere.append(" and ( product_id = ?  or product_id = ? )");
        Object[] l_objWhere = {
            this.institutionCode, 
            new Long(this.accountRow.getBranchId()),
            new Long(this.accountRow.getAccountId()),
            new Long(ALL_PRODUCT),
            new Long(l_lngProductId)
        };
        List l_lstRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                AccountProductOrderStopRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        int l_intSize = l_lstRecords.size();
        AccountProductOrderStopRow l_accountProductOrderStopRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            AccountProductOrderStopRow l_tmpRow = (AccountProductOrderStopRow)l_lstRecords.get(i);
            if((WEB3DateUtility.compareToDay(l_tmpRow.getApplyStartDate(),l_bizDate) <= 0) 
            &&(WEB3DateUtility.compareToDay(l_bizDate,l_tmpRow.getApplyEndDate()) <= 0))
            {
                l_accountProductOrderStopRow = l_tmpRow;
                break;
            }
        }
        if (l_accountProductOrderStopRow == null)
        {
            log.debug("�ڋq�����ʎ����~�e�[�u�������F ���� = 0");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�R�j�@@�w�����̒�~�����ǂ����̔��� 
        //�擾�����f�[�^�̂����ꂩ�ŁA�Y����������~����(*1)�� 
        //"��~��"�̏ꍇ�́Atrue��Ԃ��B��L�ȊO�A�܂��͊Y���f�[�^�Ȃ��̏ꍇ�́Afalse��Ԃ�
        boolean l_isTradeStopProduct = false;
        if(OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum))
        {
            //"����������"�F�@@�����������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivBuyCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.EQUITY_SELL.equals(l_orderTypeEnum))
        {
            // "����������"�F�@@�����������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivSellCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MARGIN_LONG.equals(l_orderTypeEnum))
        {
            //"�V�K��������"�F�@@���V�K�������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivLongMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            //"�V�K��������"�F�@@���V�K�������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivShortMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderTypeEnum))
        {
            //"�����ԍϒ���"�F�@@�����ԍρi���ԍρj�����~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivLongCloseMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            //"�����ԍϒ���"�F�@@�����ԍρi���ԍρj�����~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivShortCloseMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderTypeEnum))
        {
            //"��������"�F�@@���������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivLongSwapMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderTypeEnum))
        {
            //"���n����"�F�@@���n�����~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivShortSwapMargin()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderTypeEnum))
        {
            // "�����~�j��������"�F�@@���~�j�������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivBuyMiniStock()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderTypeEnum))
        {
            //"�����~�j��������"�F�@@���~�j�������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopDivSellMiniStock()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.FEQ_BUY.equals(l_orderTypeEnum))
        {
            //"�O������"�F�@@�����������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivBuyCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        else if(OrderTypeEnum.FEQ_SELL.equals(l_orderTypeEnum))
        {
            //"�O������"�F�@@�����������~
            if(WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_accountProductOrderStopRow.getStopTradeDivSellCash()))
            {
                l_isTradeStopProduct = true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_isTradeStopProduct;
          
    }
    
    /**
     * (is�O���،������J��)<BR>
     *  <BR>
     * ���Y�ڋq���A�O���،��������J�݂��Ă��邩�ǂ����𔻒肷��B<BR>
     *  <BR>
     * this.�O���،������J�݋敪��"�����J��"�̏ꍇ�́A<BR>
     * true��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     */
    public boolean isForeignAccountOpen()
    {
        return WEB3ForeignSecAccOpenDiv.OPEN.equals(
            this.accountRow.getForeignSecAccOpenDiv());
    }

    /**
     * (is�@@�l)<BR>
     * �@@�l�ڋq���ǂ����̔��ʂ�����B<BR>
     * <BR>
     * this.���� == �h�@@�l�h �̏ꍇtrue���A <BR>
     * this.���� != �h�@@�l�h �̏ꍇfalse��ԋp����B <BR>
     * @@return boolean
     */
    public boolean isCorporation()
    {
        return WEB3SexDef.CORPORATE.equals(this.accountRow.getSex());
    }
    
    /**
     * (is�ݓ������J��)<BR>
     *  <BR>
     * ���Y�ڋq���A�ݓ��������J�݂��Ă��邩�ǂ����𔻒肷��B<BR>
     *  <BR>
     * this.�ݓ������J�݋敪��"�����J��"�̏ꍇ�́A<BR>
     * true��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     */
    public boolean isRuitoAccountOpen()
    {
        return WEB3CumulativeAccountDivDef.ESTABLISH.equals(
            this.accountRow.getRuitoAccOpenDiv());
    }

    /**
     * (get�ڋq�s)<BR>
     *  <BR>
     * this.�ڋqRow�I�u�W�F�N�g��ԋp����B<BR>
     *  <BR>
     * @@return MainAccountRow
     */
    public MainAccountRow getMainAccountRow()
    {
        return this.accountRow;
    }

    /**
     * (get�ڋq)<BR>
     *  <BR>
     * �istatic ���\�b�h�j  <BR>
     * �w��ɊY������u�ڋq�}�X�^�I�u�W�F�N�g�v��List���擾����B  <BR>
     * <BR>
     * �P�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �u�ڋq�}�X�^�s�I�u�W�F�N�g�v��List���擾����B  <BR>
     * <BR>
     * �@@�@@[doFindAllQuery()�Ɏw�肷�����]  <BR>
     * �@@�@@rowType�F�@@�ڋq�}�X�^Row.TYPE  <BR>
     * �@@�@@where�F�@@��������������  <BR>
     * �@@�@@orderBy�F�@@�\�[�g����  <BR>
     * �@@�@@conditions�F�@@null  <BR>
     * �@@�@@bindVars�F�@@���������f�[�^�R���e�i  <BR>
     * <BR>
     * �Q�j�@@�������ʂ̍s�I�u�W�F�N�g�Łu�ڋq�}�X�^�I�u�W�F�N�g�v�𐶐����A<BR>
     * List�ŕԋp����B <BR>
     *  <BR>
     * @@param l_strWhere (��������������)
     * @@param l_bindVars (���������f�[�^�R���e�i)
     * @@param l_strOrderBy (�\�[�g����)
     * @@return List
     * @@throws WEB3BaseException
     */
    public static List getMainAccount(
        String l_strWhere,
        String[] l_bindVars,
        String l_strOrderBy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstReturn = new ArrayList();
        List l_lstRecords = null;
        try
        {
            //QueryProcessor.doFindAllQuery( )�ɂ��A�u�ڋq�}�X�^�s�I�u�W�F�N�g�v��List���擾����B
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lstRecords = l_qp.doFindAllQuery(
                MainAccountRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_bindVars);
        }
        catch (DataException l_de)
        {
            log.error(STR_METHOD_NAME, l_de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMainAccount.class.getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        //�������ʂ̍s�I�u�W�F�N�g�Łu�ڋq�}�X�^�I�u�W�F�N�g�v�𐶐����A List�ŕԋp����B
        WEB3GentradeMainAccount[] l_gentradeMainAccounts = 
            new WEB3GentradeMainAccount[l_lstRecords.size()];
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_lstRecords.get(i);
            l_gentradeMainAccounts[i] = new WEB3GentradeMainAccount(l_mainAccountRow);
            l_lstReturn.add(l_gentradeMainAccounts[i]);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lstReturn;
    }

    /**
     * (is�~�ݐU����i��s�����j�o�^)<BR>
     *  <BR>
     * ���Y�ڋq���A�U����i��s�����j�ɉ~�ݓo�^���Ă��邩�ǂ����𔻒肷��B <BR>
     * <BR>
     * this.�U����i��s�����j�o�^��"1�F�~�݂̂ݓo�^��"or"3�F�����o�^��"�̏ꍇ�́Atrue��ԋp����B <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     *  <BR>
     * @@return boolean
     */
    public boolean isJapaneseCurrencyBankAccountRegi()
    {
        return WEB3BankAccountRegiDef.ALREADY_REGISTER.equals(
            this.accountRow.getBankAccountRegi())
            || WEB3BankAccountRegiDef.BOTH_REGISTERED.equals(
            this.accountRow.getBankAccountRegi());
    }

    /**
     * (is�O�ݐU����i��s�����j�o�^)<BR>
     *  <BR>
     * ���Y�ڋq���A�U����i��s�����j�ɊO�ݓo�^���Ă��邩�ǂ����𔻒肷��B <BR>
     * <BR>
     * this.�U����i��s�����j�o�^��"2�F�O�݂̂ݓo�^��"or"3�F�����o�^��"�̏ꍇ�́Atrue��ԋp����B <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     *  <BR>
     * @@return boolean
     */
    public boolean isForeignCurrencyBankAccountRegi()
    {
        return WEB3BankAccountRegiDef.ONLY_FOREIGN_CURRENCY_REGISTERED.equals(
            this.accountRow.getBankAccountRegi())
            || WEB3BankAccountRegiDef.BOTH_REGISTERED.equals(
            this.accountRow.getBankAccountRegi());
    }

    /**
     * (is�،��S�ۃ��[�������J��)<BR>
     *  <BR>
     * �،��S�ۃ��[�������J�݌ڋq�`�F�b�N�B <BR>
     * <BR>
     * ���Y�ڋq���،��S�ۃ��[���������J�ݍςł����true�A <BR>
     * ���J�݂ł����false��ԋp����B <BR>
     * <BR>
     * �P�j �ڋq�s.�،��S�ۃ��[���敪 == "1"(�J��) �̏ꍇ <BR>
     * <BR>
     *   �P-�P�j �����S�ۃ��[�������e�[�u���̓��Y�s���擾����B <BR>
     * <BR>
     *       [��������] <BR>
     *         �E����ID�F this.����ID <BR>
     *         �E�J�ݏ󋵁F "2"(����) <BR>
     * <BR>
     *   �P-�Q�j �P-�P�j �̖߂�lList�̒��� > 0 �̏ꍇ�Atrue��ԋp����B <BR>
     * <BR>
     *   �P-�R�j �P-�P�j �̖߂�lList�̒��� = 0 �̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �Q�j �ڋq�s.�،��S�ۃ��[���敪 != "1" �̏ꍇ�Afalse��ԋp����B <BR>
     *  <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public boolean isSecuredLoanAccountOpen() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMainAccount()";
        log.entering(STR_METHOD_NAME);

        //�ڋq�s.�،��S�ۃ��[���敪 == "1"(�J��) �̏ꍇ
        if (WEB3SecuredLoanSecAccOpenDivDef.OPEN.equals(
            this.accountRow.getSecuredLoanSecAccOpenDiv()))
        {
            //[��������]
            //����ID�F this.����ID
            //�J�ݏ󋵁F "2"(����)
            List l_lisReturns = null;
            String l_strWhere = " account_id = ? and account_open_status = ? ";
            List l_lisValues = new ArrayList();
            l_lisValues.add(new Long(this.accountRow.getAccountId()));
            l_lisValues.add(WEB3AccountOpenStatusDef.PROMISE);
            Object[] l_objValues = l_lisValues.toArray();

            //�����S�ۃ��[�������e�[�u���̓��Y�s���擾����
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisReturns = l_queryProcessor.doFindAllQuery(
                    StockSecuredLoanRow.TYPE,
                    l_strWhere,
                    l_objValues);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }

            if (l_lisReturns.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //�ڋq�s.�،��S�ۃ��[���敪 != "1" �̏ꍇ
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (is���ʌ�t)<BR>
     * <BR>
     * ���ʂ���t�ς݂��ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA���ʌ�t�Ǘ��e�[�u������������B<BR>
     * <BR>
     * �@@�@@[����]<BR> 
     * �@@�@@����ID = this.getAccountId()�̖߂�l<BR>
     * �@@�@@���ʋ敪 = ����.���ʋ敪<BR>
     * �@@�@@�����R�[�h = ����.�����R�[�h<BR>
     * �@@�@@���ʌ�t�� �� ���ݓ��t�̓��t����<BR>
     * �@@�@@�폜�t���O = �h�L���h<BR>
     * <BR>
     * �Q�j�e�[�u�����烌�R�[�h���擾�ł����ꍇ�Atrue��ԋp����B<BR>
     * �@@�@@�擾�ł��Ȃ������ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@param l_documentDivision ���ʋ敪�i��ʃR�[�h�j 
     * @@param l_productCode �����R�[�h
     * @@return boolean
     */
    public boolean isDocumentDelivery(String l_documentDivision, String l_productCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDocumentDelivery(String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intSize;
        Date l_datBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        
        // �P�j�ȉ��̏����ŁA���ʌ�t�Ǘ��e�[�u������������B 
        // [����] 
        // ����ID = this.getAccountId()�̖߂�l
        // ���ʋ敪 = ����.���ʋ敪
        // �����R�[�h = ����.�����R�[�h
        // ���ʌ�t�� �� ���ݓ��t�̓��t����
        // �폜�t���O = �h�L���h
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and document_div = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and delivery_date <= to_date(?, 'yyyyMMdd') ");
        l_sbWhere.append(" and delete_flag = ? ");
        
        Object[] l_objWhere = {
            String.valueOf(this.getAccountId()),
            l_documentDivision,
            l_productCode,
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            BooleanEnum.FALSE
        };
        
        List l_lisRecords = null;
        QueryProcessor l_queryProcessor;
		try 
        {
			l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = 
                l_queryProcessor.doFindAllQuery(
                    DocDeliveryManagementRow.TYPE, 
                    l_sbWhere.toString(), 
                    l_objWhere); 

		} 
        catch (DataException e) 
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);

		} 
        
        l_intSize = l_lisRecords.size();
        
        // �Q�j�e�[�u�����烌�R�[�h���擾�ł����ꍇ�Atrue��ԋp����B
        // �擾�ł��Ȃ������ꍇ�Afalse��ԋp����B
        if (l_intSize < 1) 
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
    }

    /**
     * (isPTS�����J��)<BR>
     * ���Y�ڋq���APTS�������J�݂��Ă��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * this.PTS�����J�݋敪��"�����J��"�̏ꍇ�́Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isPTSAccountOpen()
    {
        final String STR_METHOD_NAME = "isPTSAccountOpen()";
        log.entering(STR_METHOD_NAME);

        String l_strPtsAccOpenDiv = this.accountRow.getPtsAccOpenDiv();
        if (WEB3PTSAccOpenDivDef.ACCOUNT_OPEN.equals(l_strPtsAccOpenDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isPTS�����~����)<BR>
     * �ڋq�E�����ʎ����~�`�F�b�N�B<BR>
     * �|�ڋq���A�����Ŏw�肳�ꂽ�����{����ɂ��āA�����~���ł��邩�ǂ�����Ԃ��B<BR>
     * �@@�iDB���C�A�E�g�u�ڋq�����ʎ����~�e�[�u���d�l.xls�v�Q�Ɓj<BR>
     * <BR>
     * �P�j�@@�`�F�b�N���{�L���̔���<BR>
     * <BR>
     * �@@������������ł���ꍇ(*)�́A�ȍ~�̏����𑱍s����B<BR>
     * �@@������������ł��Ȃ��ꍇ�́A����������false�i�������~�����łȂ��j��return����B<BR>
     * <BR>
     * �@@(*) ������������ł���ꍇ<BR>
     * �@@������ԊǗ�.getPTS������()�Ő���ɔ��������擾�ł���i��O���X���[����Ȃ��j�ꍇ�́A<BR>
     * �@@������������ł���Ɣ��f����<BR>
     * �@@������ԊǗ�.getPTS������()�Ŕ��������擾�ł��Ȃ��i��O���X���[�����j�ꍇ��<BR>
     * �@@������������ł��Ȃ��Ɣ��f����B<BR>
     * <BR>
     * �Q�j�@@DB����<BR>
     * �@@�ڋq�����ʎ����~�e�[�u�����ȉ��̏����Ō�������B<BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = this.�،���ЃR�[�h<BR>
     * �@@���XID = this.���XID<BR>
     * �@@����ID = this.����ID<BR>
     * �@@����ID in �i"�S����", �����̖���ID�j<BR>
     * �@@�K�p�J�n�N���� <= ������(*1)<BR>
     * �@@�K�p�I���N���� >= ������(*1)<BR>
     * <BR>
     * �R�j�@@�w�����̒�~�����ǂ����̔���<BR>
     * �@@�擾�����f�[�^�̂����ꂩ�ŁA�Y����������~����(*1)��"��~��"�̏ꍇ�́A<BR>
     * �@@true��Ԃ��B<BR>
     * �@@��L�ȊO�A�܂��͊Y���f�[�^�Ȃ��̏ꍇ�́Afalse��Ԃ��B<BR>
     * <BR>
     * (*1)�Y����������~���ڂ́A�����́u������ʁv���ȉ��̂悤�ɓ��肷��B<BR>
     * <BR>
     * "����������"�F�@@�����������~<BR>
     * "����������"�F�@@�����������~<BR>
     * <BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isPTSTradeStopProduct(long l_lngProductId, OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSTradeStopProduct(long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�`�F�b�N���{�L���̔���
        Date l_datPTSOrderBizDate = null;
        try
        {
            //������������ł���ꍇ(*)�́A�ȍ~�̏����𑱍s����B
            l_datPTSOrderBizDate = WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            l_datPTSOrderBizDate = null;
        }

        if (l_datPTSOrderBizDate == null)
        {
            //������������ł��Ȃ��ꍇ�́A����������false�i�������~�����łȂ��j��return����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //DB����
        //�ڋq�����ʎ����~�e�[�u�����ȉ��̏����Ō�������B
        //[����]
        //�@@�،���ЃR�[�h = this.�،���ЃR�[�h
        //�@@���XID = this.���XID
        //�@@����ID = this.����ID
        //�@@����ID in �i"�S����", �����̖���ID�j
        //�@@�K�p�J�n�N���� <= ������(*1)
        //�@@�K�p�I���N���� >= ������(*1)
        StringBuffer l_sbSql = new StringBuffer();
        l_sbSql.append(" institution_code = ? ");
        l_sbSql.append(" and branch_id = ? ");
        l_sbSql.append(" and account_id = ? ");
        l_sbSql.append(" and product_id in (?, ?) ");

        Object[] l_sqlValues = new Object[]{
            this.institutionCode,
            new Long(this.accountRow.getBranchId()),
            new Long(this.accountRow.getAccountId()),
            new Long(WEB3ProductIdDef.ALL_PRODUCT),
            new Long(l_lngProductId)};

        List l_lisResults;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisResults = l_processor.doFindAllQuery(
                AccountProductOrderStopRow.TYPE,
                l_sbSql.toString(),
                l_sqlValues);
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

        AccountProductOrderStopRow l_orderStopRow = null;
        int l_intCnt = l_lisResults.size();
        for (int i = 0; i < l_intCnt; i++)
        {
            l_orderStopRow = (AccountProductOrderStopRow)l_lisResults.get(i);
            if (WEB3DateUtility.compareToDay(l_orderStopRow.getApplyStartDate(), l_datPTSOrderBizDate) <= 0
                && WEB3DateUtility.compareToDay(l_datPTSOrderBizDate, l_orderStopRow.getApplyEndDate()) <= 0)
            {
                //�擾�����f�[�^�̂����ꂩ�ŁA�Y����������~����(*1)��"��~��"�̏ꍇ�́A
                //true��Ԃ��B
                if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
                {
                    if (WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_orderStopRow.getStopTradeDivBuyCash()))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return true;
                    }
                }

                if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
                {
                    if (WEB3AccountProductOrderStopDivDef.STOP_TRADE.equals(l_orderStopRow.getStopTradeDivSellCash()))
                    {
                        log.exiting(STR_METHOD_NAME);
                        return true;
                    }
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�ېU�i�@@�\�a���j����)<BR>
     *  <BR>
     * ���Y�ڋq���A�ېU�i�@@�\�a���j�ɓ��ӂ��Ă��邩�ǂ����𔻒肷��B <BR>
     * <BR>
     * this.�ېU�敪�i�@@�\�a���j��"����"�̏ꍇ�́Atrue��ԋp����B <BR>
     * �ȊO�Afalse��ԋp����B <BR>
     *  <BR>
     * @@return boolean
     */
    public boolean isOrgDepositAgree()
    {
        return WEB3OrgDepositDivDef.AGREE.equals(
            this.accountRow.getOrgDepositDiv());
    }

    /**
     * (isCFD�����J��)<BR>
     * ���Y�ڋq���ACFD�������J�݂��Ă��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * this.CFD�����J�݋敪��"�����J��"�̏ꍇ�́A true ��ԋp����B<BR>
     * ����ȊO�� false ��ԋp����B<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isCFDAccountOpen()
    {
        final String STR_METHOD_NAME = "isCFDAccountOpen()";
        log.entering(STR_METHOD_NAME);

        String l_strCfdAccOpenDiv = this.accountRow.getCfdAccOpenDiv();
        if (WEB3CfdAccOpenDivDef.ACCOUNT_OPEN.equals(l_strCfdAccOpenDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
}@
