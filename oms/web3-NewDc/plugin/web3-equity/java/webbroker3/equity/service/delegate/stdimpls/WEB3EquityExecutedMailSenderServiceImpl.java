head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecutedMailSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��胁�[�����M�T�[�r�X(WEB3EquityExecutedMailSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �����F(���u) �쐬
Revesion History : 2007/02/12 ������(���u) �c�a�X�V�d�l NO.194
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.data.EqtypeOrderExecSendMailParams;
import webbroker3.equity.data.EqtypeOrderExecSendMailRow;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3ResendStatusDef;
import webbroker3.common.define.WEB3StatusDef;

/**
 * �i��胁�[�����M�T�[�r�XImpl�j�B<BR>
 * <BR>
 * ��胁�[�����M�T�[�r�X�̎����N���X<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j
 * @@version 1.0
 */
public class WEB3EquityExecutedMailSenderServiceImpl
    implements WEB3EquityExecutedMailSenderService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityExecutedMailSenderServiceImpl.class);

    /**
     * @@roseuid 40B4D1F901F4
     */
    public WEB3EquityExecutedMailSenderServiceImpl()
    {

    }

    /**
     * �isendMailProcess�j<BR>
     * <BR>
     * ������胁�[�����M�������s���B<BR>
     * <BR>
     * ����.�����P�ʂ𔻒肵�A�ȉ��̂����ꂩ�̃��[���f�[�^���쐬��<BR>
     * ������胁�[�����M�e�[�u���ɍs���쐬����B<BR>
     * <BR>
     * �������A�����̏ꍇ��<BR>
     * �ڋq�}�X�^.��������胁�[�����M�t���O��<BR>
     * �u���M�v�FBooleanEnum.TRUE�v<BR>
     * �̏ꍇ�͈ȉ��������s�킸�Areturn����B<BR>
     * <BR>
     * �ꕔ���A�S�����̏ꍇ��<BR>
     * �ڋq�}�X�^.������胁�[�����M�t���O��<BR>
     * �u���M�v�FBooleanEnum.TRUE�v<BR>
     * �̏ꍇ�͈ȉ��������s�킸�Areturn����B<BR>
     * <BR>
     * �܂��A�����̓o�^�σ��R�[�h�L���m�F�t���O��true�i�L�����m�F����j�̏ꍇ�́A<BR>
     * ��L�[���d�����郌�R�[�h�����݂���ꍇ�́A�ȉ��������s�킸�Areturn����B<BR>
     * <BR>
     * �P�j�@@���i�����j���[���f�[�^�쐬<BR>
     * �@@����.�������R�R�[�h��null�̏ꍇ�Acreate�������[��()�ɂĎ������[�����쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����P�ʁF�@@�����P�ʃI�u�W�F�N�g<BR>
     * �@@�������R�R�[�h�F�@@����.�������R�R�[�h<BR>
     * <BR>
     * �@@����.�������R�R�[�h��null�̏ꍇ�Acreate��胁�[��()�ɂĖ�胁�[�����쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����P�ʁF�@@�����P�ʃI�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@���[���f�[�^�}��<BR>
     * �@@insertMail()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�P�j�̖߂�l�̊�����胁�[�����M���R�[�h<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * ���������P�ʃI�u�W�F�N�g<BR>
     * @@param l_strReasonCode - (�������R�R�[�h)<BR>
     * �������R�R�[�h�B<BR>
     * ���̏ꍇ��null���w�肷��B<BR>
     * @@param l_blnConfirmAlreadyIns - (�o�^�σ��R�[�h�L���m�F�t���O)<BR>
     * �o�^�σ��R�[�h�L���m�F�t���O�B<BR>
     * @@roseuid 403DC3D002AF
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode, boolean l_blnConfirmAlreadyIns)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "sendMailProcess(OrderUnit, String, boolean)";
        log.entering(STR_METHOD_NAME);

        // �ڋq�I�u�W�F�N�g�̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();
        MainAccount l_account = null;
        try
        {
            l_account = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());
        }
        catch (NotFoundException l_exp)
        {
            log.error("�ڋq�I�u�W�F�N�g���擾�ł��܂���F account_id = [" + l_orderUnit.getAccountId() + "]", l_exp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        MainAccountRow l_accountRow = (MainAccountRow)l_account.getDataSourceObject();
        if (l_strReasonCode == null)
        {
            if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
            {
                if (BooleanEnum.TRUE.equals(l_accountRow.getEquityOrderExeMailFlag()) == false)
                {
                    log.debug("����ID�F[" + l_orderUnit.getAccountId() +
                        "]�̌ڋq�}�X�^�[.������胁�[�����M�t���O���h���M�v�h�̂��߁A���[���𑗐M���܂���B");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
            else
            {
                if (l_orderUnit.isUnexecuted())
                {
                    if (BooleanEnum.TRUE.equals(l_accountRow.getEquityOrderUnexecMailFlag()) == false)
                    {
                        log.debug("����ID�F[" + l_orderUnit.getAccountId() +
                            "]�̌ڋq�}�X�^�[.��������胁�[�����M�t���O���h���M�v�h�̂��߁A���[���𑗐M���܂���B");
                        log.exiting(STR_METHOD_NAME);
                        return;
                    }
                }
                else
                {
                    if (BooleanEnum.TRUE.equals(l_accountRow.getEquityOrderExeMailFlag()) == false)
                    {
                        log.debug("����ID�F[" + l_orderUnit.getAccountId() +
                            "]�̌ڋq�}�X�^�[.������胁�[�����M�t���O���h���M�v�h�̂��߁A���[���𑗐M���܂���B");
                        log.exiting(STR_METHOD_NAME);
                        return;
                    }
                }
            }
        }
        
		if (l_blnConfirmAlreadyIns == true)
        {
        	//���R�[�h�����ɓo�^�ς��ǂ������m�F���A
        	//�o�^�ς̏ꍇ�́A���[���f�[�^�̓o�^���s��Ȃ��B
			EqtypeOrderUnitRow l_orderUnitRow =
				(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
			StringBuffer l_sbWhere = new StringBuffer();
			l_sbWhere.append(" institution_code = ? ");
			l_sbWhere.append(" and branch_code = ? ");
			l_sbWhere.append(" and account_code = ? ");
			l_sbWhere.append(" and order_request_number = ? ");
			if (l_strReasonCode != null)
			{
				//�������[��
				l_sbWhere.append(" and order_exec_status = ? ");
			}
			else
			{
				//��胁�[��
				l_sbWhere.append(" and order_exec_status <> ?");
			}

			try
			{
				QueryProcessor l_processor = Processors.getDefaultProcessor();
				Object[] l_objWhere =
					{l_account.getInstitution().getInstitutionCode(),
					l_account.getBranch().getBranchCode(),
					l_account.getAccountCode(),
					l_orderUnitRow.getOrderRequestNumber(),
					WEB3OrderExecStatusDef.CLOSE
				};

				List l_lstRecords =
					l_processor.doFindAllQuery(
						EqtypeOrderExecSendMailParams.TYPE,
						l_sbWhere.toString(),
						l_objWhere);
				if ((l_lstRecords != null) && (l_lstRecords.size() > 0))
				{
					log.exiting(STR_METHOD_NAME);
					return;
				}
			}
			catch (Exception l_ex)
			{
				log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(), l_ex);
			}
		}
        EqtypeOrderExecSendMailRow l_eqtypeOrderExecSendMailRow = null;
        EqtypeOrderExecSendMailParams l_orderExecSendMailParams = null;
        if (l_strReasonCode != null)
        {
            //�������R�R�[�h��null�̏ꍇ,�������[�����쐬
            l_eqtypeOrderExecSendMailRow =
                createCloseMail(l_orderUnit, l_strReasonCode);
        }
        else
        {
            //�������R�R�[�h = null�̏ꍇ,��胁�[�����쐬
            l_eqtypeOrderExecSendMailRow = createExecutedMail(l_orderUnit);
        }
        //���[���f�[�^�}��
        l_orderExecSendMailParams =
            new EqtypeOrderExecSendMailParams(l_eqtypeOrderExecSendMailRow);
        insertMail(l_orderExecSendMailParams);

        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * �isendMailProcess�j<BR>
	 * <BR>
	 * ������胁�[�����M�������s���B<BR>
	 * �i* this.sendMailProcess(�����P��, String, boolean��delegate����B�j<BR>
	 * <BR>
	 * @@param l_orderUnit - (�����P��)<BR>
     * ���������P�ʃI�u�W�F�N�g<BR>
     * @@param l_strReasonCode - (�������R�R�[�h)<BR>
     * �������R�R�[�h�B<BR>
     * ���̏ꍇ��null���w�肷��B<BR>
     */
	public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode)
		throws WEB3BaseException
	{
		String STR_METHOD_NAME = "sendMailProcess(OrderUnit, String)";
		log.entering(STR_METHOD_NAME);
		
		this.sendMailProcess(l_orderUnit, l_strReasonCode, false);
		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (create��胁�[��)<BR>
     * ��胁�[���s�i�ڋq���āj���쐬���ԋp����B<BR>
     * <BR>
     * �ҏW���e�́A<BR>
     * �u��胁�[�����M�T�[�r�X_������胁�[�����M�e�[�u��.xls�v�́u�iDB�X�V[���Ұ�]�j<BR>
     * ���Ұّ��Mð��فv�V�[�g���Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * ���������P�ʃI�u�W�F�N�g<BR>
     * @@return EqtypeOrderExecSendMailRow
     * @@throws WEB3BaseException
     * @@roseuid 413832980142
     */
    public EqtypeOrderExecSendMailRow createExecutedMail(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createExecutedMail(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (!(l_orderUnit instanceof EqTypeOrderUnit))
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //FinApp�̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        EqtypeOrderExecSendMailParams l_sendMailParams =
            new EqtypeOrderExecSendMailParams();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderExecSendMailRow l_orderExecSendMailRow = null;

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityFinTransactionManager l_finTransactionMgr =
                (WEB3EquityFinTransactionManager)l_tradingModule
                    .getFinTransactionManager();
            
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());
            MainAccount l_mainAccount =
                l_accountMgr.getMainAccount(l_orderUnit.getAccountId());
            
            //�،���ЃR�[�h
            l_sendMailParams.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //���X�R�[�h
            l_sendMailParams.setBranchCode(l_branch.getBranchCode());
            //�����R�[�h
            String l_strAccountCode = l_mainAccount.getAccountCode();
            l_sendMailParams.setAccountCode(l_strAccountCode);
            //���ʃR�[�h
            l_sendMailParams.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //�����R�[�h
            EqTypeProductImpl l_product =
                new EqTypeProductImpl(l_orderUnitRow.getProductId());
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();
            l_sendMailParams.setProductCode(l_productRow.getProductCode());
            //�s��R�[�h
            Market l_market =
                l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());
            l_sendMailParams.setMarketCode(l_market.getMarketCode());
            //���������h�c
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            int l_length = 0;

            if (l_orderActions != null)
            {
                l_length = l_orderActions.length;
            }
            for (int i = 0; i < l_length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo()
                    == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_sendMailParams.setOrderActionId(
                        l_orderActions[i].getOrderActionId());
                    break;
                }
            }
            //�ŋ敪
            l_sendMailParams.setTaxType(l_orderUnit.getTaxType());
            //�󒍓���
            l_sendMailParams.setReceivedDateTime(
                l_orderUnitRow.getReceivedDateTime());
            //���敪
            //�����P��.�������=="�����ρi��������j"�̏ꍇ�F
            //"2�F����"�i�Œ�ŃZ�b�g�j
            if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.EXECUTED);
            }
            //�����P��.isUnexecuted()==true�̏ꍇ�́A�O�F�����B
            else if (l_orderUnit.isUnexecuted())
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.UNEXECUTED);
            }
            //�����P��.isPartiallyExecuted()==true�̏ꍇ�́A�P�F�ꕔ���B
            else if (l_orderUnit.isPartiallyExecuted())
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.PARTIALLY_EXECUTED);
            }
            //�����P��.isFullyExecuted()==true�̏ꍇ�́A�Q�F���ρB
            else if (l_orderUnit.isFullyExecuted())
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.EXECUTED);
            }
            //��n���
            double l_dblNetAmount =
                l_finTransactionMgr.getNetAmountTotal((EqTypeOrderUnit)l_orderUnit);
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()) ||
                OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                l_sendMailParams.setDelivalyAmount(l_dblNetAmount * -1D);
            }
            else
            {
                l_sendMailParams.setDelivalyAmount(l_dblNetAmount);
            }
            //��萔��
            if (l_orderUnitRow.getExecutedQuantityIsNull())
            {
                l_sendMailParams.setExecutedQuantity(null);
            }
            else
            {
                l_sendMailParams.setExecutedQuantity(
                    l_orderUnitRow.getExecutedQuantity());
            }
            //�������
            l_sendMailParams.setOrderType(l_orderUnit.getOrderType());
            //����R�[�h�iSONAR�j
            l_sendMailParams.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //�d�q���[�����M�X�e�C�^�X
            l_sendMailParams.setStatus(WEB3StatusDef.NOT_DEAL);
            //�d�q���[�����M����
            l_sendMailParams.setSendProcessDateTime(null);
            //�đ��敪
            l_sendMailParams.setResendStatus(WEB3ResendStatusDef.DEFAULT);
            //�d�q���[���đ�����
            l_sendMailParams.setResendProcessDateTime(null);
            //�������R�R�[�h
            l_sendMailParams.setReasonCode(null);
            //�w�l
            l_sendMailParams.setLimitPrice(l_orderUnitRow.getLimitPrice());
            //email�A�h���X
            MainAccountRow l_accountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_sendMailParams.setEmailAddress(l_accountRow.getEmailAddress());
            //��������
            l_sendMailParams.setOrderQuantity(l_orderUnitRow.getQuantity());
            //�폜�t���O
            l_sendMailParams.setDeleteFlag(BooleanEnum.FALSE);
            //
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            //�쐬����
            l_sendMailParams.setCreatedTimestamp(l_timeStamp);
            //�X�V����
            l_sendMailParams.setLastUpdatedTimestamp(l_timeStamp);
            l_orderExecSendMailRow =
                (EqtypeOrderExecSendMailRow)l_sendMailParams;

        }
        catch (NotFoundException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderExecSendMailRow;
    }

    /**
     * (create�������[��)<BR>
     * �������[���s�i�،���Ј��āj���쐬���ԋp����B<BR>
     * <BR>
     * �ҏW���e�́A<BR>
     * �u��胁�[�����M�T�[�r�X_������胁�[�����M�e�[�u��.xls�v��<BR>
     * �u�i[����Ұ�]�j�������Ұّ��M�v�V�[�g���Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * ���������P�ʃI�u�W�F�N�g<BR>
     * @@param l_strReasonCode - (�������R�R�[�h)<BR>
     * �������R�R�[�h�B<BR>
     * ���̏ꍇ��null���w�肷��B<BR>
     * @@return EqtypeOrderExecSendMailRow
     * @@throws WEB3BaseException
     * @@roseuid 41383298019C
     */
    public EqtypeOrderExecSendMailRow createCloseMail(
        OrderUnit l_orderUnit,
        String l_strReasonCode)
        throws WEB3BaseException
    {

        String STR_METHOD_NAME =
            "createCloseMail(OrderUnit l_orderUnit,String l_strReasonCode)";
        log.entering(STR_METHOD_NAME);

        if (!(l_orderUnit instanceof EqTypeOrderUnit))
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //FinApp�̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        EqtypeOrderExecSendMailParams l_sendMailParams =
            new EqtypeOrderExecSendMailParams();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderExecSendMailRow l_orderExecSendMailRow = null;

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());
            MainAccount l_mainAccount =
                l_accountMgr.getMainAccount(l_orderUnit.getAccountId());

            //�،���ЃR�[�h
            l_sendMailParams.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //���X�R�[�h
            l_sendMailParams.setBranchCode(l_branch.getBranchCode());
            //�����R�[�h
            String l_strAccountCode = l_mainAccount.getAccountCode();
            l_sendMailParams.setAccountCode(l_strAccountCode);
            //���ʃR�[�h
            l_sendMailParams.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //�����R�[�h
            EqTypeProductImpl l_product =
                new EqTypeProductImpl(l_orderUnitRow.getProductId());
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();
            l_sendMailParams.setProductCode(l_productRow.getProductCode());
            //�s��R�[�h
            Market l_market =
                l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());
            l_sendMailParams.setMarketCode(l_market.getMarketCode());
            //���������h�c
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            int l_length = 0;

            if (l_orderActions != null)
            {
                l_length = l_orderActions.length;
            }
            for (int i = 0; i < l_length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo()
                    == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_sendMailParams.setOrderActionId(
                        l_orderActions[i].getOrderActionId());
                    break;
                }
            }
            //�ŋ敪
            l_sendMailParams.setTaxType(l_orderUnit.getTaxType());
            //�󒍓���
            l_sendMailParams.setReceivedDateTime(
                l_orderUnitRow.getReceivedDateTime());
            //���敪          
            l_sendMailParams.setOrderExecStatus(WEB3OrderExecStatusDef.CLOSE);
            //��n���
            l_sendMailParams.setDelivalyAmount(0);
            //��萔��
            l_sendMailParams.setExecutedQuantity(0);
            //�������
            l_sendMailParams.setOrderType(l_orderUnit.getOrderType());
            //����R�[�h�iSONAR�j
            l_sendMailParams.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //�d�q���[�����M�X�e�C�^�X
            l_sendMailParams.setStatus(WEB3StatusDef.NOT_DEAL);
            //�d�q���[�����M����
            l_sendMailParams.setSendProcessDateTime(null);
            //�đ��敪
            l_sendMailParams.setResendStatus(WEB3ResendStatusDef.DEFAULT);
            //�d�q���[���đ�����
            l_sendMailParams.setResendProcessDateTime(null);
            //�������R�R�[�h
            l_sendMailParams.setReasonCode(l_strReasonCode);
            //�w�l
            if(l_orderUnitRow.getLimitPriceIsNull())
            {
                l_sendMailParams.setLimitPrice(null);
            }
            else
            {
                l_sendMailParams.setLimitPrice(l_orderUnit.getLimitPrice());
            }
            
            //email�A�h���X
            l_sendMailParams.setEmailAddress(null);
            //��������
            l_sendMailParams.setOrderQuantity(l_orderUnit.getQuantity());
                       
            //�폜�t���O
            l_sendMailParams.setDeleteFlag(BooleanEnum.FALSE);
            //
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            //�쐬����
            l_sendMailParams.setCreatedTimestamp(l_timeStamp);
            //�X�V����
            l_sendMailParams.setLastUpdatedTimestamp(l_timeStamp);
            l_orderExecSendMailRow =
                (EqtypeOrderExecSendMailRow)l_sendMailParams;
        }
        catch (NotFoundException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderExecSendMailRow;

    }

    /**
     * ������胁�[�����M�e�[�u���Ɉ����̓��e�ōs��}������B<BR>
     * @@param l_orderExecSendMail - (�������d�q���[�����M���R�[�h)<BR>
     * ������胁�[�����M�e�[�u���s<BR>
     * @@throws WEB3BaseException
     * @@roseuid 403EAF5201AB
     */
    public void insertMail(EqtypeOrderExecSendMailParams l_orderExecSendMail)
        throws WEB3BaseException
    {
        //�f�[�^�}������
        String STR_METHOD_NAME = "insertMail(EqtypeOrderExecSendMailParams)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            processor.doInsertQuery(l_orderExecSendMail);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "�f�[�^�擾���G���[";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (undoSendMail)<BR>
     * �����̒����P�ʂɊY�������胁�[���i�܂��͎������[���j�𖳌��ɂ���B<BR>
     * <BR>
     * ������胁�[�����M�e�[�u���́A�ȉ��̏����ɓ��Ă͂܂�s���폜����B<BR>
     * <BR>
     * ------------------------------------------------<BR>
     * ���폜������<BR>
     * <BR>
     * ������胁�[�����M�e�[�u��.�،���ЃR�[�h = <BR>
     * �@@�����P��.���X�h�c�ɊY�����镔�X�̏،���ЃR�[�h <BR>
     * ������胁�[�����M�e�[�u��.���X�R�[�h = <BR>
     * �@@�����P��.���X�h�c�ɊY�����镔�X�̕��X�R�[�h <BR>
     * ������胁�[�����M�e�[�u��.�����R�[�h = <BR>
     * �@@�����P��.�����h�c�ɊY������ڋq�̌����R�[�h <BR>
     * ������胁�[�����M�e�[�u��.���ʃR�[�h = <BR>
     * �@@�����P��.���ʃR�[�h <BR>
     * <BR>
     * �X�V���e�́A <BR>
     * �u��胁�[�����M�T�[�r�X_������胁�[�����M�e�[�u��.xls�v <BR>
     * [�i����Ұّ��M�j���]�V�[�g���Q�ƁB <BR>
     * ------------------------------------------------<BR>
     * @@param l_orderUnit - �����P�ʁB
     * @@roseuid 40F2664D004C
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoSendMail(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        if (!(l_orderUnit instanceof EqTypeOrderUnit))
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());
            //throw NotFoundException
            MainAccount l_mainAccount =
                l_accountMgr.getMainAccount(l_orderUnit.getAccountId());
            //throw NotFoundException

            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //throw DataFindException, DataNetworkException

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //�،���ЃR�[�h
            l_sbWhere.append(" and branch_code = ? "); //���X�R�[�h
            l_sbWhere.append(" and account_code = ? "); //�����R�[�h
            l_sbWhere.append(" and order_request_number = ? "); //���ʃR�[�h

            Object[] l_objWhere =
                { l_branch.getInstitution().getInstitutionCode(), //�،���ЃR�[�h
                l_branch.getBranchCode(), //���X�R�[�h
                l_mainAccount.getAccountCode(), //�����R�[�h
                l_orderUnitRow.getOrderRequestNumber() //���ʃR�[�h
            };

            List l_lstRecords =
                l_processor.doFindAllQuery(
                    EqtypeOrderExecSendMailParams.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            int l_intSize = 0;
            if (!(l_lstRecords == null))
            {
                l_intSize = l_lstRecords.size();
            }

            for (int i = 0; i < l_intSize; i++)
            {
                EqtypeOrderExecSendMailParams l_params =
                    new EqtypeOrderExecSendMailParams(
                        (EqtypeOrderExecSendMailRow)l_lstRecords.get(i));
                l_params.setDeleteFlag(BooleanEnum.TRUE);
                //�X�V����    last_updated_timestamp
                //���ݓ���
                Timestamp l_timeStamp =
                    GtlUtils.getTradingSystem().getSystemTimestamp();
                l_params.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doUpdateQuery((EqtypeOrderExecSendMailRow)l_params);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
