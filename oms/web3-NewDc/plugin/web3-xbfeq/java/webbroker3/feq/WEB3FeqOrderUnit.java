head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������P��(WEB3FeqOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���U (���u) �V�K�쐬
                   2005/07/27 䈋��@@(���u) ���r���[
Revesion History : 2007/08/13 �ؕk (���u) ���f���@@No.355�Ή�
Revesion History : 2008/01/16 �đo�g(���u) ���f��No.372
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.466
*/

package webbroker3.feq;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.feq.data.FeqOrderChangeStatusDao;
import webbroker3.feq.data.FeqOrderChangeStatusRow;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqExpirationStatusDef;
import webbroker3.feq.define.WEB3FeqOrderAcceptTypeDef;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�O�����������P��)<BR>
 * �O�����������P��<BR>
 *
 * @@author ���U(���u)
 * @@version 1.0
 */
public class WEB3FeqOrderUnit extends FeqOrderUnitImpl
{
    /**
     * ���O���[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderUnit.class);

    /**
     * �R���X�g���N�^<BR>
     */
    protected WEB3FeqOrderUnit(long l_orderUnitId)
        throws DataQueryException, DataNetworkException
    {
        super(l_orderUnitId);
    }

    /**
     * �R���X�g���N�^<BR>
     */
    protected WEB3FeqOrderUnit(FeqOrderUnitRow row)
    {
        super(row);
    }

    /**
     * (isHOST����)<BR>
     * HOST������͂��ꂽ�������𔻒肷��B<BR>
     * <BR>
     * �ithis.�����P�ʍs.�����o�H�敪 == 9�FHOST�j<BR>
     * �̏ꍇtrue�A�ȊO��false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 428D6DE9006D
     */
    public boolean isHOSTOrder()
    {
        return (WEB3OrderRootDivDef.HOST.equals(
            this.m_row.getOrderRootDiv())) ? true : false;
    }

    /**
     * (get���ŏI�ʔ�)<BR>
     * ���ŏI�ʔԂ��擾����B<BR>
     * <BR>
     * this.�����P�ʍs.���ŏI�ʔ� ��ԋp����B<BR>
     * @@return int
     * @@roseuid 429291AD004B
     */
    public int getLastExecutionSerialNo()
    {
        return this.m_row.getLastExecutionSerialNo();
    }

    /**
     * (get�⏕����)<BR>
     * �⏕�������擾����B<BR>
     * <BR>
     * this.getSubAccountId()�ɊY������⏕������ԋp����B<BR>
     * @@return WEB3GentradeSubAccount
     * @@throws WEB3BaseException
     * @@roseuid 4292A9190321
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                this.getAccountId(),
                this.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }

    /**
     * (get�s��)<BR>
     * �s����擾����B<BR>
     * <BR>
     * this.�����P�ʍs.�s��ID�ɊY������s��I�u�W�F�N�g��ԋp����B<BR>
     * @@return WEB3GentradeMarket
     * @@throws WEB3BaseException
     * @@roseuid 4292A9650275
     */
    public WEB3GentradeMarket getMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(this.m_row.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * (get�ʉ�)<BR>
     * �ʉ݂��擾����B<BR>
     * <BR>
     * this.getProduct().get�ʉ�()��ԋp����B<BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 4292AB7A0014
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        return ((WEB3FeqProduct)this.getProduct()).getCurrency();
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���擾����B<BR>
     * <BR>
     * this.�����P�ʍs.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4292C54F015C
     */
    public String getInstitutionCode()
    {
        return this.m_row.getInstitutionCode();
    }

    /**
     * (get���X�R�[�h)<BR>
     * ���X�R�[�h���擾����B<BR>
     * <BR>
     * this.getBranchId()�ɊY�����镔�X.getBranchCode()��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4292C5F20091
     */
    public String getBranchCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchCode()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(this.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_branch.getBranchCode();
    }

    /**
     * (get�����R�[�h)<BR>
     * �����R�[�h���擾����B<BR>
     * <BR>
     * this.getMainAccount().getAccountCode()��ԋp����B<BR>
     * @@return String
     * @@roseuid 4292C64302B4
     */
    public String getAccountCode()
    {
        return this.getMainAccount().getAccountCode();
    }

    /**
     * (get�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * this.getMainAccount()��ԋp����B<BR>
     * @@return WEB3GentradeMainAccount
     * @@roseuid 42943CF902D7
     */
    public MainAccount getMainAccount()
    {
        return (WEB3GentradeMainAccount)super.getMainAccount();
    }

    /**
     * (get�^�p�R�[�h)<BR>
     * �^�p�R�[�h���擾����B<BR>
     * <BR>
     * this.�����P�ʍs.�^�p�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 42A5663101EB
     */
    public String getOrderEmpCode()
    {
        return this.m_row.getOrderEmpCode();
    }

    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * this.�����P�ʍs.��������ԋp����B<BR>
     * @@return String
     * @@roseuid 42A5663101EB
     */
    public String getBizDate()
    {
        return this.m_row.getBizDate();
    }

    /**
     * (is�w�l)<BR>
     * �iisLimitOrder�j<BR>
     * <BR>
     * �w�l�����擾����B<BR>
     * <BR>
     * �ithis.getLimitPrice() == 0�j�̏ꍇfalse�A�ȊOtrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4292AC400014
     */
    public boolean isLimitOrder()
    {
        return (this.getLimitPrice() == 0) ? false : true;
    }

    /**
     * (is���t)<BR>
     * ���t���𔻒肷��B<BR>
     * <BR>
     * this.getSide() == �h�����h�̏ꍇtrue�A�ȊOfalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4292BFB602C3
     */
    public boolean isBuy()
    {
        return (SideEnum.BUY.equals(this.getSide())) ? true : false;
    }

    /**
     * (is�~�݌���)<BR>
     * �~�݌��ς��𔻒肷��B<BR>
     * <BR>
     * this.�����P�ʍs.���ϋ敪 == �h0�F�~�݌��ρh�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 42A57036014E
     */
    public boolean isJpySettle()
    {
        return (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(
            this.m_row.getSettleDiv())) ? true : false;
    }

    /**
     * (get��t�敪)<BR>
     * �����̒����P�ʂ̎�t�敪��ԋp����B<BR>
     * <BR>
     * �ԋp�l<BR>
     * 0�F��t����<BR>
     * 1�F��t��<BR>
     * 2�F��t�G���[<BR>
     * <BR>
     * �P�j�@@��t�敪����<BR>
     * �@@[�@@�����P��.�s�ꂩ��m�F�ς̐���==NaN�̏ꍇ]<BR>
     * �@@�@@[�A�p�����[�^.�����P��.�������=="�������s(�V�K����)"�̏ꍇ]<BR>
     * �@@�@@�@@"��t�G���["��ԋp����B<BR>
     * <BR>
     * �@@�@@[�A�ȊO�̏ꍇ]<BR>
     * �@@�@@�i�����Ɏ��s���Ă��Ȃ��ꍇ�j<BR>
     * �@@�@@�@@"��t����"��ԋp����B<BR>
     * <BR>
     * �@@[�@@�ȊO�̏ꍇ]<BR>
     * �@@�i�s�ꂩ��m�F�ς݂̐��ʂɒl�������Ă���ꍇ�j<BR>
     * �@@�@@"��t��"��ԋp����B<BR>
     * @@return String
     * @@roseuid 42A52F5B018B
     */
    private String getAcceptDiv()
    {
        final String STR_METHOD_NAME = "getAcceptDiv()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@��t�敪����
        String l_strReturn;
        if (this.m_row.getConfirmedQuantityIsNull())
        {
            if (OrderStatusEnum.NOT_ORDERED.equals(this.m_row.getOrderStatus()))
            {
                l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR;
            }
            else
            {
                l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NAN;
            }
        }
        else
        {
            l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NOT_NAN;
        }

        log.debug(STR_METHOD_NAME + ".get��t�敪 : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get����ԋ敪)<BR>
     * ����ԋ敪��Ԃ��B<BR>
     * <BR>
     * �ԋp�l<BR>
     * 0�F�����<BR>
     * 1�F�ꕔ����<BR>
     * 2�F�S������<BR>
     * 3�F��菈�����i�ꕔ�����j<BR>
     * 4�F��菈�����i�S�������j<BR>
     * <BR>
     * �P�j�@@�����P�ʂ�艼���t���O���擾����B<BR>
     * <BR>
     * �Q�j�@@�����t���O���h0�FDEFAULT�h�̏ꍇ<BR>
     * <BR>
     * �@@�Q?�P�j�@@�ꕔ�����ithis.isPartiallyExecuted( ) == true�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h�ꕔ�����h��ԋp����B<BR>
     * <BR>
     * �@@�Q?�Q�j�@@�S�������ithis.isFullyExecuted( ) == true�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h�S�������h��ԋp����B<BR>
     * <BR>
     * �R�j�@@�����t���O���h1�F�����h�̏ꍇ<BR>
     * <BR>
     * �@@�R?�P�j�@@�ꕔ�����ithis.isPartiallyExecuted( ) == true�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h��菈�����i�ꕔ�����j�h��ԋp����B<BR>
     * <BR>
     * �@@�R?�Q�j�@@�S�������ithis.isFullyExecuted( ) == true�j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�h��菈�����i�S�������j�h��ԋp����B<BR>
     * <BR>
     * �S�j�@@��L�ȊO�̏ꍇ�A�h�����h��ԋp����B<BR>
     * @@return String
     * @@roseuid 42A52F5B018D
     */
    public String getExecStatusDiv()
    {
        final String STR_METHOD_NAME = "getExecStatusDiv()";
        log.entering(STR_METHOD_NAME);
        
        String l_strReturn = null;
        // �����t���O���h0�FDEFAULT�h�̏ꍇ
        if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(this.m_row.getTemporaryExecutionFlag()))
        {
            if (this.isPartiallyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE;
            }
            else if (this.isFullyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE;
            }            
        }
        // �����t���O���h1�F�����h�̏ꍇ
        else if (WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC.equals(this.m_row.getTemporaryExecutionFlag()))
        {
            if (this.isPartiallyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE;
            }
            else if (this.isFullyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE;
            }
        }
        
        // ��L�ȊO�̏ꍇ�A�h�����h��ԋp����B
        if (l_strReturn == null)
        {
            l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
        }
        
        log.debug(STR_METHOD_NAME + ".get����ԋ敪 : " + l_strReturn); 
        log.exiting(STR_METHOD_NAME);
        
        return l_strReturn;

    }

    /**
     * (get������ԋ敪)<BR>
     * ������ԋ敪��Ԃ��B<BR>
     * <BR>
     * �߂�l�̒�����ԋ敪�F<BR>
     * 0�F���̑�<BR>
     * 1�F��t�ρi�V�K�����j 2�F�������i�V�K�����j <BR>
     * 3�F�����ρi�V�K�����j 6�F�������s�i�V�K�����j<BR>
     * 7�F��t�ρi�ύX�����j 8�F�������i�ύX�����j<BR>
     * 10�F�����ρi�ύX�����j 11�F�������s�i�ύX�����j<BR>
     * 12�F��t�ρi��������j 13�F�������i��������j<BR>
     * 14�F�����ρi��������j 15�F�������s�i��������j<BR>
     * 20�F�ꕔ���� 21�F�S������ 22�F����<BR>
     * 50�F�J�z�� 51�F�J�z���s<BR>
     * <BR>
     * �P�j�@@�O�����������}�l�[�W��.is�J�z�����P��(this)�̖߂�l == true�A<BR>
     * �@@�@@�@@���@@this.������� == ACCEPTED<BR>
     * �@@�@@�@@�i��t�ρi�V�K�����j�j�̏ꍇ�A"�J�z��"��Ԃ��B<BR>
     * <BR>
     * �Q�j�@@�O�����������}�l�[�W��.is�o����܂Œ����P��(this)<BR>
     * �@@�@@�@@�̖߂�l == true�A<BR>
     * �@@�@@�@@���@@this.�����������t���Ɩ����t<BR>
     *      �iGtlUtils.getTradingSystem().getBizDate()�j�A<BR>
     * �@@�@@�@@���@@this.�����L����� ==CLOSED�i�N���[�Y�j�A<BR>
     * �@@�@@�@@���@@this.�����敪 == EXPIRED�i�����ρj�A<BR>
     * �@@�@@�@@���@@this.�����G���[���R�R�[�h != "0000�F����"<BR>
     * �@@�@@�@@�̏ꍇ�A"�J�z���s"��Ԃ��B<BR>
     * <BR>
     * �R�j�@@this.isUnexecuted( ) == true�A<BR>
     * �@@�@@�@@���@@this.�����L����� == CLOSED�i�N���[�Y�j�A<BR>
     * �@@�@@�@@���@@this.�����敪 == INVALIDATED_BY_MKT<BR>
     *      �i�}�[�P�b�g���ہj�̏ꍇ�A<BR>
     * �@@�@@�@@"�S������"��Ԃ��B<BR>
     * <BR>
     * �S�j�@@this.isPartiallyExecuted( ) == true�A<BR>
     * �@@�@@�@@���@@this.�����L����� == CLOSED�i�N���[�Y�j�A<BR>
     * �@@�@@�@@���@@this.�����敪 == INVALIDATED_BY_MKT<BR>
     *     �i�}�[�P�b�g���ہj�̏ꍇ�A<BR>
     * �@@�@@�@@"�ꕔ����"��Ԃ��B<BR>
     * <BR>
     * �T�j�@@this.�����L����� == CLOSED�i�N���[�Y�j�A<BR>
     * �@@�@@�@@���@@this.�����敪 == EXPIRED�i�����ρj�̏ꍇ�A"����"��Ԃ��B<BR>
     * �@@�@@�@@���o���I���ʒm�Œ��������������ꍇ<BR>
     * <BR>
     * �U�j�@@��L�ȊO�̏ꍇ�́Athis.�������.intValue�𕶎���ŕԂ��B<BR>
     * @@return String
     * @@roseuid 42A52F5B018F
     */
    public String getOrderStatusDiv()
    {
        final String STR_METHOD_NAME = "getOrderStatusDiv()";
        log.entering(STR_METHOD_NAME);

        String l_strReturn;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager =
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        boolean l_blnIsCarryOverOrderUnit = l_orderManager.isCarryOverOrderUnit(this);
        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(this);
        Timestamp l_datBizDate = new Timestamp (GtlUtils.getTradingSystem().getBizDate().getTime());
        OrderStatusEnum l_orderStsEnum = this.getOrderStatus();
        OrderOpenStatusEnum l_ordOpenStsEnum = this.getOrderOpenStatus();
        OrderExpirationStatusEnum l_ordExpStsEnum = this.getExpirationStatus();
        
        // �P�j�@@�O�����������}�l�[�W��.is�J�z�����P��(this)�̖߂�l == true�A
        // �@@�@@�@@���@@this.������� == ACCEPTED�i��t�ρi�V�K�����j�j�̏ꍇ�A"�J�z��"��Ԃ��B
        if (l_blnIsCarryOverOrderUnit &&
            OrderStatusEnum.ACCEPTED.equals(l_orderStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.TRANSFERED;
        }

        // �Q�j�@@�O�����������}�l�[�W��.is�o����܂Œ����P��(this)�̖߂�l == true�A
        // �@@�@@�@@���@@this.�����������t���Ɩ����t�iGtlUtils.getTradingSystem().getBizDate()�j�A
        // �@@�@@�@@���@@this.�����L����� ==CLOSED�i�N���[�Y�j�A
        // �@@�@@�@@���@@this.�����敪 == EXPIRED�i�����ρj�A
        // �@@�@@�@@���@@this.�����G���[���R�R�[�h != "0000�F����"�̏ꍇ�A"�J�z���s"��Ԃ��B
        else if (l_blnIsCarriedOrderUnit &&
            WEB3DateUtility.compareToDay(this.m_row.getExpirationDate(), l_datBizDate) >= 0 &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.EXPIRED.equals(l_ordExpStsEnum) &&
            !WEB3ErrorReasonCodeDef.NORMAL.equals(this.m_row.getErrorReasonCode()))
        {
            l_strReturn = WEB3OrderStatusDef.NOT_TRANSFERED;
        }

        // �R�j�@@this.isUnexecuted( ) == true�A
        // �@@�@@�@@���@@this.�����L����� == CLOSED�i�N���[�Y�j�A
        // �@@�@@�@@���@@this.�����敪 == INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A
        // �@@�@@�@@"�S������"��Ԃ��B
        else if (this.isUnexecuted() &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.FULL_INAFFECTED;
        }

        // �S�j�@@this.isPartiallyExecuted( ) == true�A
        // �@@�@@�@@���@@this.�����L����� == CLOSED�i�N���[�Y�j�A
        // �@@�@@�@@���@@this.�����敪 == INVALIDATED_BY_MKT�i�}�[�P�b�g���ہj�̏ꍇ�A
        // �@@�@@�@@"�ꕔ����"��Ԃ��B
        else if (this.isPartiallyExecuted() &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.PART_INAFFECTED;
        }

        // �T�j�@@this.�����L����� == CLOSED�i�N���[�Y�j�A
        // �@@�@@�@@���@@this.�����敪 == EXPIRED�i�����ρj�̏ꍇ�A"����"��Ԃ��B
        // �@@�@@�@@���o���I���ʒm�Œ��������������ꍇ
        else if (OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.EXPIRED.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.CLOSED;
        }

        // �U�j�@@��L�ȊO�̏ꍇ�́Athis.�������.intValue�𕶎���ŕԂ��B
        else
        {
            l_strReturn = new Integer(l_orderStsEnum.intValue()).toString();
        }

        log.debug(STR_METHOD_NAME + ".get������ԋ敪 : " + l_strReturn);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get�����󋵋敪)<BR>
     * �����󋵋敪��ԋp����B<BR>
     * <BR>
     * �߂�l�̏����󋵋敪�F<BR>
     * ���R�[�h�l�̓��b�Z�[�W��`�t�H���_�ȉ���<BR>
     * �uү���ޒ�`��_�O������(����).xls�v�̏����󋵋敪��`���Q�ƁB<BR>
     * <BR>
     * �P�j�@@this.get��t�敪()���R�[�����A<BR>
     * �@@��t�敪���擾����B<BR>
     * <BR>
     * �Q�j�@@this.get����ԋ敪()���R�[�����A<BR>
     * �@@���敪���擾����B<BR>
     * <BR>
     * �R�j�@@�����敪�𔻒肷��B<BR>
     * �@@�@@this.get������ԋ敪()���R�[������B<BR>
     * �@@�@@�@@�Ethis.get������ԋ敪() == "�ꕔ����"�̏ꍇ�́A"1�F�ꕔ����"�B<BR>
     * �@@�@@�@@�Ethis.get������ԋ敪() == "�S������"�̏ꍇ�́A"2�F�S������"�B<BR>
     * �@@�@@�@@�E��L�ȊO�̏ꍇ�́A"0�F�����Ȃ�"�B<BR>
     * <BR>
     * �S�j�@@�����󋵋敪�ԋp<BR>
     * �@@[�P�j�Ŏ擾������t�敪 == "��t�G���["�̏ꍇ]<BR>
     * �@@�@@"��t�G���["�������敪�Ƃ��ĕԂ��B<BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�P�j�̖߂�l<BR>
     * �@@�@@+ �Q�j�̖߂�l<BR>
     * �@@�@@+ �R�j�̔��茋��<BR>
     * �@@�@@+ this.���������E����敪��ԋp����B<BR>
     * �@@���ꗗ�ɑ��݂��Ȃ��g�ݍ��킹�ł����̂܂ܕԋp����B<BR>
     * @@return String
     * @@roseuid 42A52F5B01AA
     */
    public String getTransactionStateType()
    {
        final String STR_METHOD_NAME = "getTransactionStateType()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@this.get��t�敪()���R�[�����A��t�敪���擾����B
        String l_strAcceptDiv = this.getAcceptDiv();

        // �Q�j�@@this.get����ԋ敪()���R�[�����A���敪���擾����B
        String l_strExecStatusDiv = this.getExecStatusDiv();

        // �R�j�@@�����敪�𔻒肷��B
        // this.get������ԋ敪()���R�[������B
        String l_strOrderStatusDiv = this.getOrderStatusDiv();
        String l_strExpStsDiv;

        //this.get������ԋ敪() == "�ꕔ����"�̏ꍇ�́A"1�F�ꕔ����"�B
        if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_strOrderStatusDiv))
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_ONE_COMPLETE;
        }

        //this.get������ԋ敪() == "�S������"�̏ꍇ�́A"2�F�S������"�B
        else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_strOrderStatusDiv))
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_ALL_COMPLETE;
        }

        //��L�ȊO�̏ꍇ�́A"0�F�����Ȃ�"�B
        else
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE;
        }

        // �S�j�@@�����󋵋敪�ԋp
        // [�P�j�Ŏ擾������t�敪 == "��t�G���["�̏ꍇ]
        String l_strReturn;
        if (WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR.equals(l_strAcceptDiv))
        {
            l_strReturn =
                WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR +
                WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE +
                WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE +
                WEB3ModifyCancelTypeDef.INITIAL_VALUE;
        }
        
        // [��L�ȊO�̏ꍇ]
        else
        {
            l_strReturn =
                l_strAcceptDiv +
                l_strExecStatusDiv +
                l_strExpStsDiv +
                this.m_row.getModifyCancelType();
        }

        log.debug(STR_METHOD_NAME + ".get�����󋵋敪 : " + l_strReturn);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (is������t�\)<BR>
     * �w��̕ύX���t�敪���w��\���𔻒肷��B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����敪�擾<BR>
     * �@@this.getExpirationStatus()�ɂĎ����敪���擾����B<BR>
     * <BR>
     * �Q�j�@@�����敪�̔�r<BR>
     * �@@�i�����敪 == 3�F�}�[�P�b�g���ہj<BR>
     * �̏ꍇ�A�����̎���i�ύX���t�敪 == �h03�F������t�ώ���h�j<BR>
     * �ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@���i�����敪 != 3�F�}�[�P�b�g���ہj�̏ꍇ�A�R�j�ȍ~�̏������s���B<BR>
     * <BR>
     * �R�j�@@������Ԏ擾<BR>
     * �@@this.getOrderStatus()�ɂĒ�����Ԃ��擾����B<BR>
     * <BR>
     * �S�j�@@��Ԃ̔�r<BR>
     * �@@���@@�i������� == 2:�������i�V�K�����j10:�����ρi�ύX���� �ǎ��ԑсj�j�̏ꍇ<BR>
     * �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@01�F������t��<BR>
     * �@@�@@02�F������t�G���[<BR>
     * �@@�@@31�F�o����<BR>
     * <BR>
     * �@@���@@�i������� == 13:�������i��������j�j�̏ꍇ<BR>
     * �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@21�F�����<BR>
     * �@@�@@22�F����G���[<BR>
     * �@@�@@31�F�o����<BR>
     * <BR>
     * �@@���@@�i������� == 8:�������i�ύX�����j�j�̏ꍇ<BR>
     * �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@11�F������<BR>
     * �@@�@@12�F�����G���[<BR>
     * �@@�@@31�F�o����<BR>
     * <BR>
     * �@@���@@�i������� == 3:�����ρi�V�K�����j Or 10:�����ρi�ύX�����j�j�̏ꍇ<BR>
     * �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@03�F������t�ώ��<BR>
     * �@@�@@31�F�o����<BR>
     * <BR>
     * �@@���@@�i������� == 14:�����ρi��������j�j�̏ꍇ<BR>
     * �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@03�F������t�ώ��<BR>
     * <BR>
     * �@@���@@�i������� == 11:�������s�i�ύX�����j Or<BR>
     * �@@�@@ 15:�������s�i��������j�j�̏ꍇ<BR>
     * �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A<BR>
     * �@@�@@�ȊO��false��ԋp����B<BR>
     * <BR>
     * �@@�@@31�F�o����<BR>
     * <BR>
     * �@@�� �ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@param l_strAfterChangeAcceptDiv - (�ύX���t�敪)<BR>
     * �ύX���t�敪<BR>
     * <BR>
     * 01�F������t��<BR>
     * 02�F������t�G���[<BR>
     * 03�F������t�ώ��<BR>
     * <BR>
     * 11�F������<BR>
     * 12�F�����G���[<BR>
     * <BR>
     * 21�F�����<BR>
     * 22�F����G���[<BR>
     * <BR>
     * 31�F�o����<BR>
     *
     * @@return boolean
     * @@roseuid 42A560A003B0
     */
    public boolean isOrderAcceptPoss(String l_strAfterChangeAcceptDiv)
    {
        final String STR_METHOD_NAME = "isOrderAcceptPoss(String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�����敪�擾
        OrderExpirationStatusEnum l_ordExpStsEnum = this.getExpirationStatus();

        // �Q�j�@@�����敪�̔�r
        boolean l_blnReturn = false;
        // �i�����敪 == 3�F�}�[�P�b�g���ہj
        if (OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            // �����̎���i�ύX���t�敪 == �h03�F������t�ώ���h�j�ł����true�A�ȊO��false��ԋp����B
            l_blnReturn = WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv);

            log.debug(STR_METHOD_NAME + ".is������t�\ : " + l_blnReturn);
            if (!l_blnReturn)
            {
                log.error(STR_METHOD_NAME + ".is������t�\��false�̏ꍇ");
                log.error(STR_METHOD_NAME + "�����敪 == 3�F�}�[�P�b�g���ۂ̏ꍇ");
                log.error(STR_METHOD_NAME + "��t�敪:" + l_strAfterChangeAcceptDiv);
            }
            log.exiting(STR_METHOD_NAME);
            return l_blnReturn;
        }

        // �R�j�@@������Ԏ擾
        OrderStatusEnum l_ordStsEnum = this.getOrderStatus();
        boolean l_confirmedPrice = this.isConfirmedPriceMarketOrder();

        // �S�j�@@��Ԃ̔�r
        // ���@@�i������� == 2:�������i�V�K�����j���́@@10:�����ρi�ύX���� �ǎ��ԑсj�j�̏ꍇ
        if (OrderStatusEnum.ORDERING.equals(l_ordStsEnum) ||
        (OrderStatusEnum.MODIFIED.equals(l_ordStsEnum) && l_confirmedPrice))
        {
            // �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B
            // �@@�@@01�F������t��
            // �@@�@@02�F������t�G���[
            // �@@�@@31�F�o����
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // ���@@�i������� == 13:�������i��������j�j�̏ꍇ
        else if (OrderStatusEnum.CANCELLING.equals(l_ordStsEnum))
        {
            // �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B
            // �@@�@@21�F�����
            // �@@�@@22�F����G���[
            // �@@�@@31�F�o����
            l_blnReturn =
                WEB3FeqAcceptTypeDef.CANCEL.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // ���@@�i������� == 8:�������i�ύX�����j�j�̏ꍇ
        else if (OrderStatusEnum.MODIFYING.equals(l_ordStsEnum))
        {
            // �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B
            // �@@�@@11�F������
            // �@@�@@12�F�����G���[
            // �@@�@@31�F�o����
            l_blnReturn =
                WEB3FeqAcceptTypeDef.CHANGED.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // �@@���@@�i������� == 3:�����ρi�V�K�����j Or 10:�����ρi�ύX�����j�j�̏ꍇ
        else if (OrderStatusEnum.ORDERED.equals(l_ordStsEnum) ||
            (OrderStatusEnum.MODIFIED.equals(l_ordStsEnum) && !l_confirmedPrice))
        {
            // �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B
            // �@@�@@03�F������t�ώ��
            // �@@�@@31�F�o����
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // �@@���@@�i������� == 14:�����ρi��������j�j�̏ꍇ
        else if (OrderStatusEnum.CANCELLED.equals(l_ordStsEnum))
        {
            // �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B
            // �@@�@@03�F������t�ώ��
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv);
        }

        // ���@@�i������� == 11:�������s�i�ύX�����j Or 15:�������s�i��������j�j�̏ꍇ
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_ordStsEnum) ||
            OrderStatusEnum.NOT_CANCELLED.equals(l_ordStsEnum))
        {
            // �@@�@@�ύX���t�敪���ȉ��̉��ꂩ�ł����true�A�ȊO��false��ԋp����B
            // �@@�@@31�F�o����
            l_blnReturn = WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // �� �ȊO�̏ꍇ�Afalse��ԋp����B

        log.debug(STR_METHOD_NAME + ".is������t�\ : " + l_blnReturn);

        if (!l_blnReturn)
        {
            log.error(STR_METHOD_NAME + ".is������t�\��false�̏ꍇ");
            log.error(STR_METHOD_NAME + "�������:" + l_ordStsEnum);
            log.error(STR_METHOD_NAME + "��t�敪:" + l_strAfterChangeAcceptDiv);
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * (is��������)<BR>
     * �����������𔻒肷��B<BR>
     * <BR>
     * �ȉ��̏����ŊO�������󋵃e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ����ID = this.����ID<BR>
     *    �V�K����ID = this.����ID<BR>
     * <BR>
     * ���R�[�h���擾�ł����ꍇ�� true ���A<BR>
     * �擾�ł��Ȃ������ꍇ�� false ��ԋp����B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 42A94C1A030B
     */
    public boolean isChangeOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeOrder()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderChangeStatusRow l_row = FeqOrderChangeStatusDao.findRowByAccountIdNewOrderId(
                this.getAccountId(),
                new Long(this.getOrderId()));
            

            // ���R�[�h���擾�ł����ꍇ�� true ���A�擾�ł��Ȃ������ꍇ�� false ��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return (l_row == null) ? false : true;
        }
        catch (DataFindException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (is�o���I��)<BR>
     * �o���I�������ς݂��𔻒肷��B<BR>
     * <BR>
     * �i�����P�ʍs.�o���I���������� == null�j�̏ꍇ�Afalse<BR>
     * �i�����P�ʍs.�o���I���������� != null�j�̏ꍇ�Atrue<BR>
     * �����ꂼ��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 42AFDEA903A8
     */
    public boolean isExecEnd()
    {
        return (this.m_row.getExecEndTimestamp() == null) ? false : true;
    }
    
    /**
     * (get���SEQ)<BR>
     * ���SEQ���擾����B<BR>
     * <BR>
     * this�D�����P�ʍs�D���SEQ��ԋp����B<BR>
     * <BR>
     * @@return String
     */
    public String getExecutionSeqNo()
    {
        if (this.m_row.getExecutionSeqNoIsNull())
        {
            return null;
        }

        return String.valueOf(this.m_row.getExecutionSeqNo());
    }
}
@
