head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecutedMailSendServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP��胁�[�����M�T�[�r�XImpl(WEB3IfoExecutedMailSendServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 ����� (���u) �V�K�쐬
                          001: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
Revesion History : 2008/08/18 ���� (���u) IFO�����_�Ή�
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3ResendStatusDef;
import webbroker3.common.define.WEB3TaxationDivDef;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.WEB3IfoFinTransactionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.IfoOrderExecSendMailParams;
import webbroker3.ifo.data.IfoOrderExecSendMailRow;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;



/**
 * (�敨OP��胁�[�����M�T�[�r�XImpl)<BR>
 * <BR>
 * ��胁�[�����M�T�[�r�X�����N���X<BR>
 * <BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_JOIN_EXISTING�j<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3IfoExecutedMailSendServiceImpl implements WEB3IfoExecutedMailSendService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoExecutedMailSendServiceImpl.class);

    /**
     * @@roseuid 40C0753103A9
     */
    public WEB3IfoExecutedMailSendServiceImpl()
    {

    }

    /**
     * �敨OP��胁�[�����M�������s���B<BR>
     * <BR>
     * ����.�����P�ʂ𔻒肵�A�ȉ��̂����ꂩ�̃��[���f�[�^���쐬��<BR>
     * �敨OP��胁�[�����M�e�[�u���ɍs���쐬����B<BR>
     * <BR>
     * �P�j�@@���i�����j���[���f�[�^�쐬<BR>
     * �@@�������R�R�[�h��null�łȂ��ꍇ�Acreate�������[��()�ɂĎ������[�����쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����P�ʁF�@@�����P�ʃI�u�W�F�N�g<BR>
     * �@@�������R�R�[�h�F�@@�������R�R�[�h<BR>
     * <BR>
     * �@@�i�������R�R�[�h == null�j�̏ꍇ�Acreate��胁�[��()�ɂĖ�胁�[�����쐬����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�����P�ʁF�@@�����P�ʃI�u�W�F�N�g<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_strCloseReasonCode - �������R�R�[�h�B<BR>
     * ���̏ꍇ��null���w�肷��B<BR>
     * @@roseuid 408483E40028
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strCloseReasonCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sendMailProcess(OrderUnit l_orderUnit, String l_strCloseReasonCode)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_strCloseReasonCode != null)   //�������R�R�[�h��null�łȂ��ꍇ
            {
                //create�������[��()�ɂĎ������[�����쐬����B
                createCloseMail(l_orderUnit, l_strCloseReasonCode);
            }
            else    //�������R�R�[�h == null
            {
                //create��胁�[��()�ɂĖ�胁�[�����쐬����B
                createExecutedMail(l_orderUnit);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        catch (DataFindException l_ex)
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

    /**
     * (create��胁�[��)<BR>
     * <BR>
     * ��胁�[���s��DB��insert����B<BR>
     * <BR>
     * �ȉ��̒ʂ�A���敪�ɉ����Đ敨OP��胁�[�����M�e�[�u���ɍs��}������B<BR>
     * <BR>
     *  �� �����i�����P��.isUnexecuted() == true�j�j�̏ꍇ<BR>
     * �@@�@@�@@�|��胁�[���s�I�u�W�F�N�g���쐬���AinsertMail()�ɂ�DB�ɑ}������B<BR>
     * <BR>
     * �@@�@@����胁�[���s�I�u�W�F�N�g�̕ҏW���e�́A<BR>
     * �@@�@@�u����_�敨OP��胁�[�����M�e�[�u��.xls�v<BR>
     *  �@@[�i�敨OPҲّ��M�j����胁�[��]�V�[�g���Q�ƁB<BR>
     * <BR>
     *  �� �S�����i�����P��.isFullyExecuted() == true�j�܂��́A<BR>
     * �ꕔ���i�����P��.isPartiallyExecuted() == true�̏ꍇ<BR>
     * �@@�@@�@@�|��胁�[���s�I�u�W�F�N�g���쐬���AinsertMail()�ɂ�DB�ɑ}������B<BR>
     * <BR>
     * �@@�@@����胁�[���s�I�u�W�F�N�g�̕ҏW���e�́A<BR>
     * �@@�@@�u����_�敨OP��胁�[�����M�e�[�u��.xls�v<BR>
     *  �@@[�i�敨OPҲّ��M�j��胁�[��]�V�[�g���Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@roseuid 408483E4002B
     */
    protected void createExecutedMail(OrderUnit l_orderUnit) throws WEB3BaseException, DataQueryException, DataNetworkException, NotFoundException
    {
        final String STR_METHOD_NAME = " createExecutedMail(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        AccountManager l_accountMgr = l_finApp.getAccountManager();
        Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
        MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

        WEB3IfoFinTransactionManagerImpl l_finTransactionMgr = (WEB3IfoFinTransactionManagerImpl)l_tradingModule.getFinTransactionManager();

        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
        Market l_market = l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());  //throw NotFoundException

        //Product l_product = l_orderUnit.getProduct();
        WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(l_orderUnitRow.getProductId());
        IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();

		//�o���ʒm����ThreadLocal�ɐݒ肳��Ă���V�X�e���������Ԃ��擾����
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute
				(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
		
		if(l_realTimestamp == null)
		{
			l_realTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
		}
				  
        if (l_orderUnit.isUnexecuted())     //�����i�����P��.isUnexecuted() == true�j�j�̏ꍇ
        {
            //��胁�[���s�I�u�W�F�N�g���쐬
            IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams();
			
            //�i�敨OPҲّ��M�j����胁�[��

            l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //�،���ЃR�[�h
            l_params.setBranchCode(l_branch.getBranchCode());                                       //���X�R�[�h
            l_params.setAccountCode(l_mainAccount.getAccountCode());                                //�ڋq�R�[�h

            l_params.setFutureOptionDiv(l_productRow.getFutureOptionDiv());                         //�敨�^�I�v�V�����敪
            l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //���ʃR�[�h
            l_params.setOrderExecStatus(WEB3OrderExecStatusDef.UNEXECUTED);                         //���敪(�O�F�����)
            
            //�i�����P��.���������ŏI�ʔԁ@@== ��������.��������ԍ��j�̒��������̒��������h�c
            IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingModule.getOrderManager();
            l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            int l_length = 0;
            
            if (l_orderActions != null)
            {
                l_length = l_orderActions.length; 
            }
            
            for (int i = 0; i < l_length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo() == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_params.setOrderActionId(l_orderActions[i].getOrderActionId());         //���������h�c
                    break;
                }
            }

            l_params.setProductCode(l_productRow.getProductCode());                                 //�����R�[�h
            l_params.setMarketCode(l_market.getMarketCode());                                       //�s��R�[�h
            l_params.setOrderType(l_orderUnit.getOrderType());                                      //�������

            //�ېŋ敪
            if ((OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())))//�����P��.�����J�e�S�� == �h�V�K�������h�̏ꍇ
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.IRRELEVENT);   //0�F���֌W
            }
            else if((OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))) //�����P��.�����J�e�S�� == �h�ԍϒ����h�̏ꍇ
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT); //1�F�\������
            }

            l_params.setReceivedDateTime(l_orderUnitRow.getReceivedDateTime());                       //�󒍓���
            l_params.setQuantity(l_orderUnitRow.getQuantity());   //��������
			l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());   //�w�l
			l_params.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType().intValue());   //���s����
			
          
			l_params.setExecutedQuantity(0D);                                        //��萔��
            l_params.setDelivalyAmount(0D);              //��n���

            l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                                  //�d�q���[�����M�X�e�C�^�X
            l_params.setResendStatus(WEB3ResendStatusDef.DEFAULT);                                  //�đ��敪
            l_params.setDeleteFlag(BooleanEnum.FALSE);                                              //�폜�t���O

            l_params.setCreatedTimestamp(l_realTimestamp);                                            //�w������
            l_params.setLastUpdatedTimestamp(l_realTimestamp);                                        //�X�V����

            //insertMail()�ɂ�DB�ɑ}������B
            insertMail(l_params);
        }
        else if (l_orderUnit.isFullyExecuted() || l_orderUnit.isPartiallyExecuted())    //�S�����܂��́A�ꕔ���̏ꍇ
        {
            //��胁�[���s�I�u�W�F�N�g���쐬
            IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams();

            //�i�敨OPҲّ��M�j��胁�[��

            l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //�،���ЃR�[�h
            l_params.setBranchCode(l_branch.getBranchCode());                                       //���X�R�[�h
            l_params.setAccountCode(l_mainAccount.getAccountCode());                                //�ڋq�R�[�h


            l_params.setFutureOptionDiv(l_productRow.getFutureOptionDiv());                         //�敨�^�I�v�V�����敪
            l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //���ʃR�[�h
            
            //�������P��.�������==�h�����ρi�ύX�����j�h or �h�����ρi��������j�h�̏ꍇ�F�h���ρh
            if (OrderStatusEnum.MODIFIED.equals(l_orderUnit.getOrderStatus()) || OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.EXECUTED);   //�Q�F����
            }
            //�����P��.isUnexecuted()==true�̏ꍇ�́A�h�����h
            else if (l_orderUnit.isUnexecuted())
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.UNEXECUTED);   //�Q�F�����
            }
            //�����P��.isPartiallyExecuted()==true�̏ꍇ�́A�h�ꕔ���h
            else if (l_orderUnit.isPartiallyExecuted()) //�ꕔ���
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.PARTIALLY_EXECUTED);   //�P�F�ꕔ���
            }
            //�����P��.isFullyExecuted()==true�̏ꍇ�́A�h���ρh
            else if (l_orderUnit.isFullyExecuted())  //�S�����
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.EXECUTED);  //�Q�F����
            }

            //�i�����P��.���������ŏI�ʔԁ@@== ��������.��������ԍ��j�̒��������̒��������h�c
            IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingModule.getOrderManager();
                        l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            for (int i = 0; i < l_orderActions.length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo() == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_params.setOrderActionId(l_orderActions[i].getOrderActionId());         //���������h�c
                    break;
                }
            }

            l_params.setProductCode(l_productRow.getProductCode());                                 //�����R�[�h
            l_params.setMarketCode(l_market.getMarketCode());                                       //�s��R�[�h
            l_params.setOrderType(l_orderUnit.getOrderType());

            //�ېŋ敪
            if ((OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())))//�����P��.�����J�e�S�� == �h�V�K�������h�̏ꍇ
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.IRRELEVENT);   //0�F���֌W
            }
            else if((OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))) //�����P��.�����J�e�S�� == �h�ԍϒ����h�̏ꍇ
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT); //1�F�\������
            }

            l_params.setReceivedDateTime(l_orderUnitRow.getReceivedDateTime());                       //�󒍓���
            l_params.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType().intValue());   //���s����
            
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }
            l_params.setExecutedQuantity(l_dblExecutedQuantity);                        //��萔��

            double l_dblNetAmount = l_finTransactionMgr.getNetAmount(l_orderUnit);

            BigDecimal l_bdNetAmount = new BigDecimal(l_dblNetAmount + "");
            //�����P��.������� == "OP�V�K��������" or "OP�������ԍϒ���"�̏ꍇ
            if (l_orderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN) ||
                l_orderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE))
            {
                //��n���z���v�l�̕����𔽓]������
                l_bdNetAmount = l_bdNetAmount.negate();
            }

            l_params.setDelivalyAmount(l_bdNetAmount.doubleValue());              //��n���

            l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                                  //�d�q���[�����M�X�e�C�^�X
            l_params.setResendStatus(WEB3ResendStatusDef.DEFAULT);                                  //�đ��敪

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_params.setEmailAddress(l_mainAccountRow.getEmailAddress());                           //email�A�h���X

            l_params.setDeleteFlag(BooleanEnum.FALSE);                                              //�폜�t���O
            
			l_params.setCreatedTimestamp(l_realTimestamp);                                            //�w������
			l_params.setLastUpdatedTimestamp(l_realTimestamp);                                        //�X�V����

            //insertMail()�ɂ�DB�ɑ}������B
            insertMail(l_params);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (create�������[��)<BR>
     * <BR>
     * �������[���s�i�،���Ј��āj���쐬���ԋp����B<BR>
     * <BR>
     * �P�j�@@�������[���s�i�،���Ј��āj���쐬����B<BR>
     * <BR>
     * �ҏW���e�́A<BR>
     * �u����_�敨OP��胁�[�����M�e�[�u��.xls�v<BR>
     *  [�i�敨OPҲّ��M�j�������[��]�V�[�g���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�������[���s��DB�ɑ}������B<BR>
     * �@@�쐬�����敨OP��胁�[�����M�s�I�u�W�F�N�g�������Ɏw�肵�A<BR>
     * insertMail()���\�b�h���R�[������B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_strCloseReasonCode - �������R�R�[�h�B<BR>
     * ���̏ꍇ��null���w�肷��B<BR>
     * @@roseuid 408483E40037
     */
    protected void createCloseMail(OrderUnit l_orderUnit, String l_strCloseReasonCode) throws WEB3BaseException, NotFoundException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = " createCloseMail(OrderUnit l_orderUnit, String l_strCloseReasonCode)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        AccountManager l_accountMgr = l_finApp.getAccountManager();
        Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
        MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
        Market l_market = l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());  //throw NotFoundException

        Product l_product = l_orderUnit.getProduct();
        IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();

        Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();

        //�������[���s�i�،���Ј��āj���쐬����
        IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams();

        l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //�،���ЃR�[�h
        l_params.setBranchCode(l_branch.getBranchCode());                                       //���X�R�[�h
        l_params.setAccountCode(l_mainAccount.getAccountCode());                                //�ڋq�R�[�h

        l_params.setFutureOptionDiv(l_productRow.getFutureOptionDiv());                         //�敨�^�I�v�V�����敪
        l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //���ʃR�[�h
        l_params.setOrderExecStatus(WEB3OrderExecStatusDef.CLOSE);                              //���敪(�O�F�����)

        //�i�����P��.���������ŏI�ʔԁ@@== ��������.��������ԍ��j�̒��������̒��������h�c
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
        
        for (int i = 0; i < l_orderActions.length; i++)
        {
            if (l_orderActions[i].getOrderActionSerialNo() == l_orderUnitRow.getLastOrderActionSerialNo())
            {
                l_params.setOrderActionId(l_orderActions[i].getOrderActionId());         //���������h�c
                break;
            }
        }

        l_params.setProductCode(l_productRow.getProductCode());                                 //�����R�[�h
        l_params.setMarketCode(l_market.getMarketCode());                                       //�s��R�[�h
        l_params.setOrderType(l_orderUnit.getOrderType());                                      //�������

        //�ېŋ敪
        
        if((OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())))//�����P��.�����J�e�S�� == �h�V�K�������h�̏ꍇ
        {
            l_params.setTaxationDiv(WEB3TaxationDivDef.IRRELEVENT);   //0�F���֌W
        }
        else if((OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))) //�����P��.�����J�e�S�� == �h�ԍϒ����h�̏ꍇ
        {
            l_params.setTaxationDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT); //1�F�\������
        }

        l_params.setReceivedDateTime(l_orderUnitRow.getReceivedDateTime());                       //�󒍓���

        //��������= �����P��.�������ʁ@@�|�@@�����P��.��萔��
        double l_dblQuantity = l_orderUnit.getQuantity();
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        double l_dblLimitPrice = l_orderUnit.getLimitPrice();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0D;
        }
        if (Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0D;
        }
        
        double l_dblExecQuantity = l_dblQuantity - l_dblExecutedQuantity;
        l_params.setQuantity(l_dblExecQuantity);                                                    //��������
        l_params.setLimitPrice(l_dblLimitPrice);                                    //�w�l
               
        l_params.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType().intValue());   //���s����
        l_params.setExecutedQuantity(0);                                                        //��萔��
        l_params.setDelivalyAmount(0);                                                          //��n���

        l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                                  //�d�q���[�����M�X�e�C�^�X
        l_params.setResendStatus(WEB3ResendStatusDef.DEFAULT);                                  //�đ��敪
        l_params.setReasonCode(l_strCloseReasonCode);                                           //�������R�R�[�h
        l_params.setDeleteFlag(BooleanEnum.FALSE);                                              //�폜�t���O

        l_params.setCreatedTimestamp(l_currentTime);                                            //�w������
        l_params.setLastUpdatedTimestamp(l_currentTime);                                        //�X�V����

        //insertMail()�ɂ�DB�ɑ}������B
        insertMail(l_params);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �敨OP��胁�[�����M�e�[�u���Ɉ����̓��e�ōs��}������B
     * @@param l_ifoExecSendMail - (�敨OP���d�q���[�����M���R�[�h)<BR>
     * <BR>
     * �敨OP��胁�[�����M�s�I�u�W�F�N�g<BR>
     * @@roseuid 408483E4003A
     */
    protected void insertMail(IfoOrderExecSendMailParams l_ifoExecSendMail) throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = " insertMail(IfoOrderExecSendMailParams l_ifoExecSendMail)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

        //�sinsert
        l_processor.doInsertQuery(l_ifoExecSendMail);    //throw DataFindException, DataQueryException, DataNetworkException

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �����̒����P�ʂɊY�������胁�[���i�܂��́A�������[���j�𖳌��ɂ���B<BR>
     *
     * �@@�敨OP��胁�[�����M�e�[�u���̈ȉ��̏����ɓ��Ă͂܂�s��<BR>
     * �u����敪==BooleanEnum.TRUE�i�����j�v���X�V����B<BR>
     * <BR>
     * [����]<BR>
     * �敨OP��胁�[�����M�e�[�u��.�،���ЃR�[�h = <BR>
     * �@@�����P��.���X�h�c�ɊY�����镔�X�̏،���ЃR�[�h<BR>
     * �敨OP��胁�[�����M�e�[�u��.���X�R�[�h = <BR>
     * �@@�����P��.���X�h�c�ɊY�����镔�X�̕��X�R�[�h<BR>
     * �敨OP��胁�[�����M�e�[�u��.�����R�[�h = <BR>
     * �@@�����P��.�����h�c�ɊY������ڋq�̌����R�[�h<BR>
     * �敨OP��胁�[�����M�e�[�u��.���ʃR�[�h = <BR>
     * �@@�����P��.���ʃR�[�h<BR>
     * <BR>
     * �X�V���e�́A<BR>
     * �u����_�敨OP��胁�[�����M�e�[�u��.xls�v<BR>
     *  [�i�敨OPҲّ��M�j���]�V�[�g���Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@roseuid 408C9C3F0028
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoSendMail(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");         //�،���ЃR�[�h
            l_sbWhere.append(" and branch_code = ? ");          //���X�R�[�h
            l_sbWhere.append(" and account_code = ? ");         //�����R�[�h
            l_sbWhere.append(" and order_request_number = ? "); //���ʃR�[�h

            Object[] l_objWhere = {
                l_branch.getInstitution().getInstitutionCode(), //�،���ЃR�[�h
                l_branch.getBranchCode(),                       //���X�R�[�h
                l_mainAccount.getAccountCode(),                 //�����R�[�h
                l_orderUnitRow.getOrderRequestNumber()          //���ʃR�[�h
                };

            List l_lstRecords = l_processor.doFindAllQuery(
                IfoOrderExecSendMailRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere
                );

            int l_intSize = l_lstRecords.size();
            
			//�o���ʒm����ThreadLocal�ɐݒ肳��Ă���V�X�e���������Ԃ��擾����
			Timestamp l_realTimestamp = 
				(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute
					(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
					
			if(l_realTimestamp == null)
			{
				l_realTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
			}

            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            l_orderUnit = l_optionOrderManagerImpl.getOrderUnit(l_orderUnit.getOrderUnitId());
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            long l_lngOrderActionID = 0L; 
            for (int i = 0; i < l_orderActions.length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo() == l_ifoOrderUnitRow.getLastOrderActionSerialNo()) {
                    l_lngOrderActionID = l_orderActions[i].getOrderActionId();
                    break;
                }
            }
            
            for (int i = 0; i < l_intSize; i++)
            {
                IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams((IfoOrderExecSendMailRow)l_lstRecords.get(i));
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteQuery(l_params.getPrimaryKey());
                
                l_params.setOrderActionId(l_lngOrderActionID);     //���������h�c
                l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                      //�d�q���[�����M�X�e�C�^�X
                l_params.setSendProcessDateTime(null);                                     //�d�q���[�����M����
                l_params.setDeleteFlag(BooleanEnum.TRUE);                                   //�폜�t���O
	            l_params.setLastUpdatedTimestamp(l_realTimestamp);                            //�X�V����
                
                l_processor.doInsertQuery(l_params);  //throw DataQueryException, DataNetworkException                
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[�B", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
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
