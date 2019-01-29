head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\���}�l�[�W��(WEB3IpoOrderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
              001: 2004/10/14 ���� (���u) �Ή�����  �R�[�h�`�F�b�N�w�E����(IPOV1.0-20040928�x�[�X)
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>039,040
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>050
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>070
Revision History : 2005/08/19 ��c(SRA) ���捞�Č�IPO-No.76�i�p�t�H�[�}���X���P�j
Revision History : 2006/09/07 ���G��(���u) ���f��  No.155
Revision History : 2007/07/19 ��іQ(���u) ���f��  No.174,176
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResultHolder;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ActionSendStatusDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3IpoLimitQuantityCheckDef;
import webbroker3.common.define.WEB3IpoOfferTradingpowerCheckDef;
import webbroker3.common.define.WEB3IpoOrderAcceptStatusDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.common.define.WEB3SendMailStatusDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.ipo.data.IpoBookbuildingOrderActionDao;
import webbroker3.ipo.data.IpoBookbuildingOrderActionParams;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoOrderDao;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO�\���}�l�[�W���N���X�B
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IpoOrderManagerImpl implements OrderManager
{
    /**
    * ���O���[�e�B���e�B<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderManagerImpl.class);

    /**
     * (�����`�F�b�N)
     */
    public WEB3IpoOrderValidator orderValidator = new WEB3IpoOrderValidator();

    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_lngArg0 - (arg0)
     * @@return Order
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC0129
     */
    public Order getOrder(long l_lngArg0) throws NotFoundException
    {
        return null;
    }

    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_arg0 - (arg0)
     * @@return Order
     * @@roseuid 40BFFDBC012B
     */
    public Order toOrder(Row l_arg0)
    {
        return null;
    }

    /**
     * (getIPO�\��)<BR>
     * �igetOrderUnit�̎����j<BR>
     * <BR><BR>
     * �w�肵��IPO�\���h�c�ɊY������s��IPO�\���e�[�u����茟������B
     * �������ʂ�IPO�\���s�I�u�W�F�N�g�������Ɏw�肵�āAIPO�\���I�u�W�F�N�g��<BR>��������B<BR>
     * ���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * [�R���X�g���N�^�Ɏw�肷�����]<BR>
     * IPO�\���s�I�u�W�F�N�g
     * @@param l_lngIpoOrderId - IPO�\���h�c
     * @@return OrderUnit
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC012D
     */
    public OrderUnit getOrderUnit(long l_lngIpoOrderId) throws NotFoundException
    {
        final String STR_METHOD_NAME = " getOrderUnit(long)";
        
        log.entering(STR_METHOD_NAME);
        
        log.debug("IpoOrder Id: " + l_lngIpoOrderId);
        
        OrderUnit l_orderUnit = null;
        try
        {
            //�w�肵��IPO�\���h�c�ɊY������s��IPO�\���e�[�u����茟������B
            IpoOrderRow l_orderRow;

            l_orderRow = IpoOrderDao.findRowByIpoOrderId(l_lngIpoOrderId);
            //findRowByPk(l_lngIpoOrderId);
            if(l_orderRow == null)
            {
                log.error(getClass().getName() + STR_METHOD_NAME);
                throw new NotFoundException("�Y������IPO�����s�����݂��Ȃ�");
            }

            log.debug("IpoOrderRow: " + l_orderRow);
            
            //�������ʂ�IPO�\���s�I�u�W�F�N�g�������Ɏw�肵�āAIPO�\���I�u�W�F�N�g�𐶐�����B
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            l_orderUnit = l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().toOrderUnit(l_orderRow);
            
        }
//        catch (DataFindException l_ex)
//        {
//            log.error(getClass().getName() + STR_METHOD_NAME);
//            throw new NotFoundException("�Y������IPO�����s�����݂��Ȃ�");
//        }
        catch (DataQueryException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        log.exiting(STR_METHOD_NAME);
        
        //���������I�u�W�F�N�g��ԋp����B
        return l_orderUnit;
    }

    /**
     * (getIPO�\��)<BR>
     * �igetOrderUnits�j<BR>
     * <BR>
     * �����̕⏕�����^IPO�o�^�敪�ɊY������IPO����������IPO�\���I�u�W�F�N�g��<BR>�擾����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@���J���̂P�����O�擾<BR>
     * �@@���ݓ���(*1)�̂P�����O��Timestamp���擾����B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()�ɂĎ擾����B<BR>
     * <BR>
     * �Q�j�@@IPO�����h�c�擾<BR>
     * �@@�ȉ��̏����ŁAIPO�����e�[�u�����������AIPO�����s���擾����B<BR>
     * <BR>
     *   [��������]<BR>
     *   �iIPO�����e�[�u��.IPO�o�^�敪 = ������IPO�o�^�敪 And <BR>
     *     IPO�����e�[�u��.�폜�t���O = BooleanEnum.FALSE And <BR>
     *   �iIPO�����e�[�u��.���J�� > �P�j�Ŏ擾����Timestamp Or IPO�����e�[�u��.���J�� = null�j�j<BR> 
     * <BR>
     *   [�擾��]<BR>
     *    ���J���@@�~���i�Fdesc�j�C<BR>
     *  �@@�����R�[�h�@@�����i�Fasc�j<BR> 
     * <BR>
     * �R�j�@@IPO�\���s����<BR>
     * �@@�ԋp�l�i�[�p��ArrayList�𐶐�����inew ArrayList()�j�B<BR>
     * �@@�Q�j�Ŏ擾����IPO�����s���ɁA�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@--- LOOP ---<BR>
     * �@@�R�|�P�j�@@IPO�\���擾<BR>
     * �@@�@@�ȉ��̏����ɂāAIPO�\���s���擾����B<BR>
     * �@@�s���擾�ł��Ȃ������ꍇ�́A�ȍ~�̏������X�L�b�v����B�icontinue; �j<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@IPO�\��.���X�h�c = �⏕����.get����X().getBranchId()<BR>
     * �@@�@@IPO�\��.�����h�c = �⏕����.getAccountId()<BR>
     * �@@�@@IPO�\��.�⏕�����h�c = �⏕����.getSubAccountId()<BR>
     * �@@�@@IPO�\��.IPO�����h�c = IPO�����s.IPO�����h�c[index]<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�ԋp�l�i�[�pArrayList�ɒǉ�<BR>
     * �@@�@@this.toIPO�\��()���\�b�h�𗘗p���AIPO�\���I�u�W�F�N�g���쐬���A<BR>ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@[toIPO�\��()�Ɏw�肷�����]<BR>
     * �@@�@@�R�|�P�j�Ŏ擾����IPO�\���s<BR>
     * <BR>
     * �@@--- LOOP ---<BR>
     * <BR>
     * �S�j�@@�ԋp�l����<BR>
     * �@@�R�j�ō쐬�����ԋp�l�i�[�pArrayList���AtoArray()�ɂĔz��ɕϊ����ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_strIpoRegDiv - IPO�o�^�敪<BR>
     * <BR>
     * �@@1�F�V�K���<BR>
     * �@@2�F�����<BR>
     * �@@�� DB���C�A�E�g�uIPO�����e�[�u���v�Q�ƁB<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40DBF7BF026E
     */
    public WEB3IpoOrderImpl[] getOrderUnits(SubAccount l_subAccount, String l_strIpoRegDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnits(SubAccount, String)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            //�P�j�@@���J���̂P�����O�擾
            //(*1) ���ݓ���
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            
            Timestamp l_tsSystemTs = l_tradingSystem.getSystemTimestamp();

            //�Q�j�@@IPO�����h�c�擾
            String l_strWhere = " ipo_regist_div = ?" 
                                    + " and delete_flag = ?" 
                                    + " and (public_offering_date > add_months(to_date(?" 
                                    + ", 'yyyy/mm/dd'), ?) "
                                    + " or public_offering_date is null) ";
            Object[] l_objBinds = new Object[]{
                    l_strIpoRegDiv,
                    new Integer(BooleanEnum.IntValues.FALSE),
                    WEB3DateUtility.formatDate(l_tsSystemTs, "yyyy/MM/dd"),
                    new Integer(-1)
                };              
            log.debug("OneMonthAgo: " + "add_months(to_date('" + WEB3DateUtility.formatDate(l_tsSystemTs, "yyyy/MM/dd") + "', 'yyyy/mm/dd'), -1)");

            String l_strOrderBy = " public_offering_date desc, product_code asc";
            
            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //DataQueryException            
            List l_lisProductRows =
                    l_queryProcessor.doFindAllQuery(IpoProductRow.TYPE, 
                                                    l_strWhere,
                                                    l_strOrderBy,
                                                    null,
                                                    l_objBinds);
                                                                    
            //List to Array
            int l_intProductRowsCnt = l_lisProductRows.size();
            
            log.debug("Found " + l_intProductRowsCnt + " Product Rows");
            
            IpoProductRow[] l_ipoProductRows = new IpoProductRow[l_intProductRowsCnt];
            
            l_lisProductRows.toArray(l_ipoProductRows);

//            //Comparator[]�Fcom[0] = new IPO����.���J��Comparatorr(�~���i�Fdesc�j)     
//            Comparator[] l_comparator = new Comparator[1];
//            l_comparator[0] = new WEB3IpoProductPublicOfferingDateComparator(WEB3AscDescDef.DESC);
//            
//            //[sort�Ɏw�肷�����]
//            WEB3ArraysUtility.sort(l_ipoProductRows, l_comparator);

            //�R�j�@@IPO�\���s����
            List l_lisOrderRows = new ArrayList();

            for (int i = 0; i < l_intProductRowsCnt; i++)
            {
                log.debug("IpoProductRow " + (i + 1) + ": " + l_ipoProductRows[i]);
                
                //DataNetworkException, DataFindException, DataQueryException
                IpoOrderRow l_ipoOrderRow =
                    IpoOrderDao.findRowByIpoProductIdBranchIdAccountIdSubAccountId(
                        l_ipoProductRows[i].getIpoProductId(),
                        ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId(),
                        l_subAccount.getAccountId(),
                        l_subAccount.getSubAccountId());
                
                log.debug("IpoOrderRow " + (i + 1) + ": " + l_ipoOrderRow);
                if(l_ipoOrderRow != null)
                {
                    l_lisOrderRows.add(this.toOrderUnit(l_ipoOrderRow));
                }
            }

            //�S�j�@@�ԋp�l����
            //  �R�j�ō쐬�����ԋp�l�i�[�pArrayList���AtoArray()�ɂĔz��ɕϊ����ԋp����B
            l_ipoOrder = new WEB3IpoOrderImpl[l_lisOrderRows.size()];
            
            l_lisOrderRows.toArray(l_ipoOrder);
        }
//        catch (DataFindException l_ex)
//        {
//            //DB�A�N�Z�X�����s�̏ꍇ
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //��O���X���[����
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }

    /**
     * (getIPO�\��)<BR>
     * �igetOrderUnits�j<BR>
     * <BR>
     * ������IPO����������IPO�\���I�u�W�F�N�g�ŁA�u�b�N�r���f�B���O���Ԓ���<BR>
     * �V�K�\�����ꂽ���̂��擾����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO�\���s�I�u�W�F�N�g�擾<BR>
     * �@@�ȉ��̏�����IPO�\���s����������B<BR>
     * �s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@IPO�\��.IPO�����h�c = IPO����.IPO�����s.IPO�����h�c<BR>
     * �@@IPO�\��.�쐬���� < IPO����.IPO�����s.�u�b�N�r���f�B���O�I������<BR>
     * <BR>
     * �Q�j�@@IPO�\���I�u�W�F�N�g����<BR>
     * �@@�P�j�Ŏ擾�����eIPO�\���s�iIPO�\��Params�j�ɂ��āAthis.toIPO�\��()<BR>
     * ���\�b�h�𗘗p���AIPO�\���I�u�W�F�N�g���쐬���AArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[toIPO�\��()�Ɏw�肷�����]<BR>
     * �@@�P�j�Ŏ擾����IPO�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * �R�j�@@�z��쐬<BR>
     * �@@�Q�j��ArrayList��toArray()�ɂĔz��ɕϊ����ԋp����B<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40E366C30173
     */
    public WEB3IpoOrderImpl[] getOrderUnits(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnits(WEB3IpoProductImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoProduct == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            //�P�j�@@IPO�\���s�I�u�W�F�N�g�擾
            StringBuffer l_sbWhere = new StringBuffer();

            //[����]<BR>
            l_sbWhere.append(" ipo_product_id = ? and created_timestamp < ? ");

            log.debug("IpoProductId: " + l_ipoProduct.getProductId());
            log.debug("BookbuildingEndDatetime: " + ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime());
            
            Object[] l_ipoOrderWhere =
                {
                    Long.toString(l_ipoProduct.getProductId()),
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime() };

            //DataNetworkException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //IPO�\���s������
            //DataQueryException, DataNetworkException
            List l_lisOrderRowList =
                l_queryProcessor.doFindAllQuery(
                    IpoOrderRow.TYPE, 
                    l_sbWhere.toString(),
                    " bookbuilding_created_timestamp asc ",
                    null, 
                    l_ipoOrderWhere
                    );

            List l_lisArrayList = new ArrayList();

            int l_intOrderRowCnt = l_lisOrderRowList.size();

            log.debug("Found " + l_intOrderRowCnt + " Rows");
            
            //�Y���s�����݂��Ȃ��ꍇ��null��ԋp����B
            if (l_intOrderRowCnt == 0)
            {        
                log.exiting(STR_METHOD_NAME);
        
                return null;
            }

            //�Q�j�@@IPO�\���I�u�W�F�N�g����
            for (int i = 0; i < l_intOrderRowCnt; i++)
            {
                log.debug("IpoOrderRow " + (l_intOrderRowCnt + 1) + ": " + (IpoOrderRow)l_lisOrderRowList.get(i));
                //�P�j�Ŏ擾�����eIPO�\���s�iIPO�\��Params�j�ɂ��āAthis.toIPO�\��()
                OrderUnit l_orderUnit = this.toOrderUnit((IpoOrderRow)l_lisOrderRowList.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            int l_intArrayListCnt = l_lisArrayList.size();
            
            //�R�j�@@�z��쐬
            l_ipoOrder = new WEB3IpoOrderImpl[l_intArrayListCnt];
            
            l_lisArrayList.toArray(l_ipoOrder);

        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }

    /**
     * (getIPO�\��)<BR>
     * �igetOrderUnits�j<BR>
     * <BR>
     * ������IPO����������IPO�\���I�u�W�F�N�g�ŁA�u�b�N�r���f�B���O���Ԓ���<BR>
     * �V�K�\�����ꂽ���̊��A�����̕��X�R�[�h�A�ڋq�R�[�hfrom�|to�A�V�K�\������from�|to<BR>
     * �͈͓̔���IPO�\���I�u�W�F�N�g���擾����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO�\���s�I�u�W�F�N�g�擾<BR>
     * �@@�ȉ��̏�����IPO�\���s����������B<BR>
     * �s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@IPO�\��.IPO����ID = ����.IPO����.getProductId()<BR>
     * �@@IPO�\��.�쐬���� < ����.IPO����.�u�b�N�r���f�B���O�I������<BR>
     * �@@IPO�\��.���XID = ����.IPO�����ɕR�Â����X�̕��XID<BR>
     * �@@����.�ڋq�R�[�hfrom <= IPO�\��.����ID.�ڋq�R�[�h <= ����.�ڋq�R�[�hto<BR>
     * �@@����.�V�K�\������from <= IPO�\��.�V�K�\������ < ����.�V�K�\������to<BR>
     * <BR>
     * �Q�j�@@IPO�\���I�u�W�F�N�g����<BR>
     * �@@�P�j�Ŏ擾�����eIPO�\���s�iIPO�\��Params�j�ɂ��āAthis.toIPO�\��()<BR>
     * ���\�b�h�𗘗p���AIPO�\���I�u�W�F�N�g���쐬���AArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[toIPO�\��()�Ɏw�肷�����]<BR>
     * �@@�P�j�Ŏ擾����IPO�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * �R�j�@@�z��쐬<BR>
     * �@@�Q�j��ArrayList��toArray()�ɂĔz��ɕϊ����ԋp����B<BR>
     * <BR>
     * @@param l_ipoProduct         - (IPO����)<BR>
     * @@param l_strBranchCodes     - (���X[])<BR>
     * @@param l_strAccountCodeFrom - (�ڋq�R�[�hfrom)<BR>
     * @@param l_strAccountCodeTo   - (�ڋq�R�[�hto)<BR>
     * @@param l_dteBbCreatedTimestampFrom - (�V�K�\������from)<BR>
     * @@param l_dteBbCreatedTimestampTo   - (�V�K�\������to)<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     */
    public WEB3IpoOrderImpl[] getOrderUnits(
        WEB3IpoProductImpl l_ipoProduct,
        String[] l_strBranchCodes,
        String l_strAccountCodeFrom,
        String l_strAccountCodeTo,
        Date l_dteBbCreatedTimestampFrom,
        Date l_dteBbCreatedTimestampTo
    ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnits(WEB3IpoProductImpl, String[], String, String, String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoProduct == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        if (l_strBranchCodes == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }

        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            // IPO�\���s�I�u�W�F�N�g�擾
            // ����������ݒ�
            StringBuffer l_sbWhere = new StringBuffer();

            // ����ID���쐬����
            l_sbWhere.append(" ipo_product_id = ? and created_timestamp < ? ");

            // ���XID  
            if(l_strBranchCodes.length > 0)
            {
                // ����.���X�R�[�h.length() == 1 �̏ꍇ 
                // " and branch_id=?"���̕�����ɒǉ�����B
                l_sbWhere.append(" and ( branch_id = ? ");

                // ����.���X�R�[�h.length() > 1 �̏ꍇ 
                // " and (branch_id=? or branch_id=? or ... or branch_id=?)"
                // �𕶎���ɒǉ�����B 
                // ��"branch_id=?"�̐��́A���X�R�[�h�̗v�f���Ɠ���
                for(int i = 1; i < l_strBranchCodes.length; i++)
                {
                    l_sbWhere.append(" or branch_id = ? ");
                }

                l_sbWhere.append(" ) ");
            }

            // �ڋq�R�[�hfrom 
            // ����.�ڋq�R�[�hfrom != null �̏ꍇ 
            // " and substr(account_id, 9, 6) >=?"�𕶎���ɒǉ�����B
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeFrom))
            {
                l_sbWhere.append(" and substr(account_id, 9, 6) >= ? ");
            }

            // �ڋq�R�[�hto 
            // ����.�ڋq�R�[�hto != null �̏ꍇ 
            // " and substr(account_id, 9, 6) <=?"�𕶎���ɒǉ�����B
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeTo))
            {
                l_sbWhere.append(" and substr(account_id, 9, 6) <= ? ");
            }

            // �V�K�\������from 
            // ����.�V�K�\������from != null �̏ꍇ 
            // " and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') >=?"�𕶎���ɒǉ�����B
            if(l_dteBbCreatedTimestampFrom != null)
            {
                l_sbWhere.append(" and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') >= ? ");
            }

            // �V�K�\������to 
            // ����.�V�K�\������to != null �̏ꍇ 
            // " and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') <?"�𕶎���ɒǉ�����B
            if(l_dteBbCreatedTimestampTo != null)
            {
                l_sbWhere.append(" and to_char(bookbuilding_created_timestamp, 'YYYYMMDDHH24MISS') < ? ");
            }

            log.debug("************** the StringWhere = " + l_sbWhere);

            // �����f�[�^�R���e�i�ݒ�
            List l_ipoOrderWhere = new Vector();

            // ����ID���u�b�N�r���f�B���O�I������  
            l_ipoOrderWhere.add(Long.toString(l_ipoProduct.getProductId()));
            l_ipoOrderWhere.add(((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime());

            // ���XID
            // ����.���X�R�[�h�̊e�v�f�𕔓XID�ɕϊ���AList�ɒǉ�����B
            for(int i = 0; i < l_strBranchCodes.length; i++)
            {
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                Institution l_institution = l_ipoProduct.getInstitution();
                try
                {
                    Branch l_branch = l_finApp.getAccountManager().getBranch(l_institution, l_strBranchCodes[i]);
                    l_ipoOrderWhere.add(Long.toString(l_branch.getBranchId()));   
                }
                catch(NotFoundException l_ex)
                {
                    // ����.���X�R�[�h�ɊY�����郌�R�[�h�����݂��Ȃ��ꍇ
                    log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
                    // ��O���X���[����
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                        this.getClass().getName() + "." + STR_METHOD_NAME
                    );
                }
            }

            // �ڋq�R�[�hfrom
            // ����.�ڋq�R�[�hfrom != null �̏ꍇ
            // ����.�ڋq�R�[�hfrom��List�ɒǉ�����B
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeFrom))
            {
                l_ipoOrderWhere.add(l_strAccountCodeFrom);   
            }

            // �ڋq�R�[�hto
            // ����.�ڋq�R�[�hto != null �̏ꍇ
            // ����.�ڋq�R�[�hto��List�ɒǉ�����B
            if(!WEB3StringTypeUtility.isEmpty(l_strAccountCodeTo))
            {
                l_ipoOrderWhere.add(l_strAccountCodeTo);   
            }

            // �V�K�\������from
            // ����.�V�K�\������from != null �̏ꍇ
            // ����.�V�K�\������from��List�ɒǉ�����B
            if(l_dteBbCreatedTimestampFrom != null)
            {
                l_ipoOrderWhere.add(WEB3DateUtility.formatDate(l_dteBbCreatedTimestampFrom, "yyyyMMddHHmmss"));   
            }

            // �V�K�\������to
            // ����.�V�K�\������to != null �̏ꍇ
            // ����.�V�K�\������to��List�ɒǉ�����B
            if(l_dteBbCreatedTimestampTo != null)
            {
                l_ipoOrderWhere.add(WEB3DateUtility.formatDate(l_dteBbCreatedTimestampTo, "yyyyMMddHHmmss"));
            }

            //log for test
            for (int i = 0; i < l_ipoOrderWhere.size(); i++)
            {
                log.debug("************* Value[" + i + "]= " + l_ipoOrderWhere.get(i));
            }

            //DataNetworkException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            // IPO�\���s������
            //DataQueryException, DataNetworkException
            List l_lisOrderRowList =
                l_queryProcessor.doFindAllQuery(
                    IpoOrderRow.TYPE, 
                    l_sbWhere.toString(),
                    " bookbuilding_created_timestamp asc ",
                    null, 
                    l_ipoOrderWhere.toArray()
                );

            List l_lisArrayList = new ArrayList();

            int l_intOrderRowCnt = l_lisOrderRowList.size();

            log.debug("Found " + l_intOrderRowCnt + " Rows");

            // �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B
            if (l_intOrderRowCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);

                return null;
            }

            // IPO�\���I�u�W�F�N�g����
            for (int i = 0; i < l_intOrderRowCnt; i++)
            {
                log.debug("IpoOrderRow " + (l_intOrderRowCnt + 1) + ": " + (IpoOrderRow)l_lisOrderRowList.get(i));
                // �擾�����eIPO�\���s�iIPO�\��Params�j�ɂ��āAthis.toIPO�\��()
                OrderUnit l_orderUnit = this.toOrderUnit((IpoOrderRow)l_lisOrderRowList.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            int l_intArrayListCnt = l_lisArrayList.size();

            // �z��쐬
            l_ipoOrder = new WEB3IpoOrderImpl[l_intArrayListCnt];

            l_lisArrayList.toArray(l_ipoOrder);

        }
        catch (DataQueryException l_ex)
        {
            // DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            // ��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            // DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            // ��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);

        return l_ipoOrder;
    }

    /**
     * (getIPO�\��)<BR>
     * �igetOrderUnit�j<BR>
     * <BR>
     * �����̕⏕�����AIPO�����ɊY������IPO�\���I�u�W�F�N�g���擾����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO�\���s�I�u�W�F�N�g�擾<BR>
     * �@@�ȉ��̏�����IPO�\���s����������B<BR>
     * �s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@IPO�\��.IPO�����h�c = IPO�����h�c<BR>
     * �@@IPO�\��.���X�h�c = �⏕����.get����X().getBranchId()<BR>
     * �@@IPO�\��.�����h�c = �⏕����.getAccountId()<BR>
     * �@@IPO�\��.�⏕�����h�c = �⏕����.getSubAccountId()<BR>
     * <BR>
     * �Q�j�@@IPO�\���I�u�W�F�N�g����<BR>
     * �@@this.toIPO�\��()���\�b�h�ɂāAIPO�\���I�u�W�F�N�g���쐬���ԋp����B<BR>
     * <BR>
     * �@@[toIPO�\��()�Ɏw�肷�����]<BR>
     * �@@�P�j�Ŏ擾����IPO�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_lngIpoProductId - IPO�����h�c
     * @@return webbroker3.ipo.WEB3IpoOrderImpl
     * @@roseuid 40D2C54501CC
     */
    public WEB3IpoOrderImpl getOrderUnit(SubAccount l_subAccount, long l_lngIpoProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnit(SubAccount, long)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl l_ipoOrder = null;

        try
        {
            //�P�j�@@IPO�\���s�I�u�W�F�N�g�擾
            //DataNetworkException, DataFindException, DataQueryException
            IpoOrderRow l_orderRow =
                IpoOrderDao.findRowByIpoProductIdBranchIdAccountIdSubAccountId(
                    l_lngIpoProductId,
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId(),
                    l_subAccount.getAccountId(),
                    l_subAccount.getSubAccountId());

            log.debug("IpoOrderRow: " + l_orderRow);
            
            //�s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B
            if (l_orderRow == null)
            {
                return null;
            }

            //�Q�j�@@IPO�\���I�u�W�F�N�g����
            l_ipoOrder = (WEB3IpoOrderImpl)this.toOrderUnit(l_orderRow);
        }
//        catch (DataFindException l_ex)
//        {
//            //DB�A�N�Z�X�����s�̏ꍇ
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //��O���X���[����
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //IPO�\���I�u�W�F�N�g���쐬���ԋp����B
        return l_ipoOrder;
    }

    /**
     * (get�L��IPO�\��For���I)<BR>
     * �igetOpenOrderUnits�j<BR>
     * <BR>
     * ������IPO�����ɊY������IPO�\���I�u�W�F�N�g�ŁA�L���ȃf�[�^���擾����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO�����I�u�W�F�N�g�擾<BR>
     * �@@IPO�����h�c�ɊY������IPO�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[IPO�����R���X�g���N�^�̈���]<BR>
     * �@@IPO�����h�c�F�@@IPO�����h�c<BR>
     * <BR>
     * �Q�j�@@IPO�\���s�I�u�W�F�N�g�擾<BR>
     * �@@�ȉ��̏�����IPO�\���s����������B<BR>
     * �s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@IPO�\��.IPO�����h�c = IPO�����h�c And<BR>
     * �@@IPO�\��.IPO�V�K�\������ < IPO����.IPO�����s.�u�b�N�r���f�B���O�I����<BR>�� And<BR>
     * �@@IPO�\��.�u�b�N�r���f�B���O�\����� != OrderStatusEnum.CANCELLED<BR>
     * �i����j<BR>
     * <BR>
     * �R�j�@@IPO�\���I�u�W�F�N�g����<BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ��āA<BR>
     * �@@this.toIPO�\��()���\�b�h�ɂāAIPO�\���I�u�W�F�N�g���쐬���z��ɂĕԋp����B<BR>
     * <BR>
     * �@@[toIPO�\��()�Ɏw�肷�����]<BR>
     * �@@�Q�j�Ŏ擾����IPO�\���s�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@param l_lngIpoProductId - IPO�����h�c
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40EB5E8A021A
     */
    public WEB3IpoOrderImpl[] getOpenOrderUnits(long l_lngIpoProductId, String l_strOrderBy, boolean l_blnNewLot) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenOrderUnits(long,String,boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            //�P�j�@@IPO�����I�u�W�F�N�g�擾
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngIpoProductId);

            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //[����]
            List l_lisOrderRow = new ArrayList();
            StringBuffer l_sbWhere = new StringBuffer();     
            if(l_blnNewLot)
            {                
                l_sbWhere.append(" ipo_product_id = ? and ");
                l_sbWhere.append(" bookbuilding_created_timestamp < ? and ");
                l_sbWhere.append(" order_status != ? ");

                Object[] l_ipoOrderWhere =
                    {   Long.toString(l_lngIpoProductId),
                        ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime(),
                        OrderStatusEnum.CANCELLED };
                        
                //�Q�j�@@IPO�\���s�I�u�W�F�N�g�擾
                //DataQueryException
                l_lisOrderRow =
                    l_queryProcessor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_ipoOrderWhere
                        );

            }
            else
            {
                l_sbWhere.append(" ipo_product_id = ? and ");
                l_sbWhere.append(" bookbuilding_created_timestamp < ? and ");
                l_sbWhere.append(" order_status != ? and ");   
                l_sbWhere.append(" lot_result = ? and ") ;
                l_sbWhere.append(" lot_result_retry = ? ") ;
                
                Object[] l_ipoOrderWhere =
                    {   Long.toString(l_lngIpoProductId),
                        ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime(),
                        OrderStatusEnum.CANCELLED,
                        WEB3LotResultDef.SUPPLEMENT,
                        WEB3LotResultRetryDef.DEFAULT}; 
                        
                //�Q�j�@@IPO�\���s�I�u�W�F�N�g�擾
                //DataQueryException
                l_lisOrderRow =
                    l_queryProcessor.doFindAllQuery(
                        IpoOrderRow.TYPE,
                        l_sbWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_ipoOrderWhere
                        );
            }



            int l_intOrderRowSize = l_lisOrderRow.size();

            log.debug("Found " + l_intOrderRowSize + " IpoOrderRows");
            //�s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B
            if (l_intOrderRowSize == 0)
            {        
                log.exiting(STR_METHOD_NAME);
        
                return null;
            }

            List l_lisArrayList = new ArrayList();

            //�R�j�@@IPO�\���I�u�W�F�N�g����
            for (int i = 0; i < l_intOrderRowSize; i++)
            {
                log.debug("Loop " + (i+1) + ": " + (IpoOrderRow)l_lisOrderRow.get(i));
                
                OrderUnit l_orderUnit =
                    this.toOrderUnit((IpoOrderRow)l_lisOrderRow.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            l_ipoOrder = new WEB3IpoOrderImpl[l_intOrderRowSize];
            
            l_lisArrayList.toArray(l_ipoOrder);
        }
        catch (NotFoundException l_ex)
        {
            //�I�v�V�������f�[�^��������܂���B
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }



    /**
     * (get�L��IPO�\��)<BR>
     * �igetOpenOrderUnits�j<BR>
     * <BR>
     * ������IPO�����ɊY������IPO�\���I�u�W�F�N�g�ŁA�L���ȃf�[�^���擾����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO�����I�u�W�F�N�g�擾<BR>
     * �@@IPO�����h�c�ɊY������IPO�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[IPO�����R���X�g���N�^�̈���]<BR>
     * �@@IPO�����h�c�F�@@IPO�����h�c<BR>
     * <BR>
     * �Q�j�@@IPO�\���s�I�u�W�F�N�g�擾<BR>
     * �@@�ȉ��̏�����IPO�\���s����������B<BR>
     * �s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@IPO�\��.IPO�����h�c = IPO�����h�c And<BR>
     * �@@IPO�\��.IPO�V�K�\������ < IPO����.IPO�����s.�u�b�N�r���f�B���O�I����<BR>�� And<BR>
     * �@@IPO�\��.�u�b�N�r���f�B���O�\����� != OrderStatusEnum.CANCELLED<BR>
     * �i����j<BR>
     * <BR>
     * �R�j�@@IPO�\���I�u�W�F�N�g����<BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ��āA<BR>
     * �@@this.toIPO�\��()���\�b�h�ɂāAIPO�\���I�u�W�F�N�g���쐬���z��ɂĕԋp����B<BR>
     * <BR>
     * �@@[toIPO�\��()�Ɏw�肷�����]<BR>
     * �@@�Q�j�Ŏ擾����IPO�\���s�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@param l_lngIpoProductId - IPO�����h�c
     * @@return webbroker3.ipo.WEB3IpoOrderImpl[]
     * @@roseuid 40EB5E8A021A
     */
    public WEB3IpoOrderImpl[] getOpenOrderUnits(long l_lngIpoProductId, String l_strOrderBy) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenOrderUnits(long)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoOrderImpl[] l_ipoOrder = null;

        try
        {
            //�P�j�@@IPO�����I�u�W�F�N�g�擾
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngIpoProductId);
            
            //[����]
            StringBuffer l_sbWhere = new StringBuffer();

            l_sbWhere.append(" ipo_product_id = ? and ");
            l_sbWhere.append(" bookbuilding_created_timestamp < ? and ");
            l_sbWhere.append(" order_status != ? ");

            Object[] l_ipoOrderWhere =
                {
                    Long.toString(l_lngIpoProductId),
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getBookbuildingEndDatetime(),
                    OrderStatusEnum.CANCELLED };

            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //�Q�j�@@IPO�\���s�I�u�W�F�N�g�擾
            //DataQueryException
            List l_lisOrderRow =
                l_queryProcessor.doFindAllQuery(
                    IpoOrderRow.TYPE,
                    l_sbWhere.toString(),
                    l_strOrderBy,
                    null,
                    l_ipoOrderWhere
                    );

            int l_intOrderRowSize = l_lisOrderRow.size();

            log.debug("Found " + l_intOrderRowSize + " IpoOrderRows");
            //�s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B
            if (l_intOrderRowSize == 0)
            {        
                log.exiting(STR_METHOD_NAME);
        
                return null;
            }

            List l_lisArrayList = new ArrayList();

            //�R�j�@@IPO�\���I�u�W�F�N�g����
            for (int i = 0; i < l_intOrderRowSize; i++)
            {
                log.debug("Loop " + (i+1) + ": " + (IpoOrderRow)l_lisOrderRow.get(i));
                
                OrderUnit l_orderUnit =
                    this.toOrderUnit((IpoOrderRow)l_lisOrderRow.get(i));

                l_lisArrayList.add(l_orderUnit);
            }

            l_ipoOrder = new WEB3IpoOrderImpl[l_intOrderRowSize];
            
            l_lisArrayList.toArray(l_ipoOrder);
        }
        catch (NotFoundException l_ex)
        {
            //�I�v�V�������f�[�^��������܂���B
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }


    /**
     * (get�����u�b�N�r���f�B���O�\������)<BR>
     * �igetCloseOrderActions�j<BR>
     * <BR>
     * ������IPO�����ɊY������u�b�N�r���f�B���O�\�������I�u�W�F�N�g�ŁA������<BR>�f�[�^���擾����B<BR>
     * �Y���s�����݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@IPO�����I�u�W�F�N�g�擾<BR>
     * �@@IPO�����h�c�ɊY������IPO�����I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[IPO�����R���X�g���N�^�̈���]<BR>
     * �@@IPO�����h�c�F�@@IPO�����h�c<BR>
     * <BR>
     * �Q�j�@@�u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g�擾<BR>
     * �@@�ȉ��̏����Ńu�b�N�r���f�B���O�\�������s����������B<BR>
     * �s���擾�ł��Ȃ������ꍇ��null��ԋp���A�������I������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�u�b�N�r���f�B���O�\������.IPO�����h�c = IPO�����h�c<BR>
     * �@@�u�b�N�r���f�B���O�\������.�폜�t���O = BooleanEnum.TRUE�i�h�폜�h�j<BR>
     * <BR>
     * �R�j�@@�u�b�N�r���f�B���O�\�������I�u�W�F�N�g����<BR>
     * �@@�擾�����e�s�I�u�W�F�N�g�ɂ��āA<BR>
     * �@@this.to�u�b�N�r���f�B���O�\������()���\�b�h�ɂāA�u�b�N�r���f�B���O�\������<BR>�I�u�W�F�N�g���쐬���z��ɂĕԋp����B<
     * BR>
     * <BR>
     * �@@[to�u�b�N�r���f�B���O�\������()�Ɏw�肷�����]<BR>
     * �@@�Q�j�Ŏ擾�����u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@param l_lngIpoProductId - IPO�����h�c
     * @@return WEB3IpoBookbuildingOrderAction[]
     * @@roseuid 40EE77C5026B
     */
    public WEB3IpoBookbuildingOrderActionImpl[] getInvalidOrderActions(long l_lngIpoProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInvalidOrderActions(long)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IpoBookbuildingOrderActionImpl[] l_orderAction = null;
        
        try
        {
            //??�P�j�@@IPO�����I�u�W�F�N�g�擾?? Unused            
            //FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            //WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngIpoProductId);
            
            //�Q�j�@@�u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g�擾
            String l_strOrderActionWhere = " ipo_product_id = ? and delete_flag = ? ";

            Object[] l_orderActionWhere = { Long.toString(l_lngIpoProductId), BooleanEnum.TRUE };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisOrderAction =
                l_queryProcessor.doFindAllQuery(
                    IpoBookbuildingOrderActionRow.TYPE,
                    l_strOrderActionWhere,
                    " substr(branch_id, 3, 3) asc, substr(account_id, 9, 6) asc, created_timestamp asc",
                    null, 
                    l_orderActionWhere);

            List l_lisToOrderAction = new ArrayList();

            int l_intSize = l_lisOrderAction.size();


            log.debug("Found " + l_intSize + " IpoBbOrderActionRows");
            
            if(l_intSize == 0)
            {
                return null;    
            }
            
            for (int i = 0; i < l_intSize; i++)
            {
                log.debug("Loop " + (i+1) + ": " + (Row)l_lisOrderAction.get(i));
                
                OrderAction l_loopOrderAction = this.toOrderAction((Row)l_lisOrderAction.get(i));
                l_lisToOrderAction.add(l_loopOrderAction);
            }

            l_orderAction = new WEB3IpoBookbuildingOrderActionImpl[l_intSize];
            
            l_lisToOrderAction.toArray(l_orderAction);
        }
//        catch (NotFoundException l_ex)
//        {
//            //�I�v�V�������f�[�^��������܂���B
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //��O���X���[����
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }        
        
        log.exiting(STR_METHOD_NAME);
        
        return l_orderAction;
    }

    /**
     * (toIPO�\��)<BR>
     * �itoOrderUnit�̎����j<BR>
     * <BR>
     * �w���IPO�\���s�I�u�W�F�N�g����IPO�\���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * IPO�\���s�I�u�W�F�N�g�������Ɏw�肵�āAIPO�\���I�u�W�F�N�g�𐶐�����B<BR>
     * ���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * [�R���X�g���N�^�Ɏw�肷�����]<BR>
     * IPO�\���s�I�u�W�F�N�g<BR>
     * @@param l_ipoOrderParams - (IPO�\��Params)<BR>
     * IPO�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * �� IPO�\��Params�N���X��DDL��莩����������B
     * 
     * @@return OrderUnit
     * @@roseuid 40BFFDBC012F
     */
    public OrderUnit toOrderUnit(Row l_ipoOrderParams)
    {
        WEB3IpoOrderImpl l_ipoOrder = new WEB3IpoOrderImpl(l_ipoOrderParams);

        return l_ipoOrder;
    }

    /**
     * (get�u�b�N�r���f�B���O�\������)<BR>
     * �igetOrderAction�̎����j<BR>
     * <BR>
     * �P�j�@@�u�b�N�r���f�B���O�����s�擾<BR>
     * �@@�w�肵���u�b�N�r���f�B���O�\�������h�c�ɊY������s���u�b�N�r���f�B���O<BR>
     * �\�������e�[�u����茟������B<BR>
     * <BR>
     * �Q�j�@@�u�b�N�r���f�B���O�\�������I�u�W�F�N�g�쐬<BR>
     * �@@�P�j�Ŏ擾�����e�u�b�N�r���f�B���O�\�������s�i�u�b�N�r���f�B���O<BR>
     * �\������Params�j�ɂ��āAthis.to�u�b�N�r���f�B���O�\������()���\�b�h��<BR>
     * ���p���A�u�b�N�r���f�B���O�\�������I�u�W�F�N�g<BR>
     * <BR>
     * ���쐬����B<BR>
     * <BR>
     * �@@[to�u�b�N�r���f�B���O�\������()�Ɏw�肷�����]<BR>
     * �@@�P�j�Ŏ擾�����u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g<BR>
     * <BR>
     * �R�j�@@�ԋp�l�쐬<BR>
     * �@@�쐬�����u�b�N�r���f�B���O�\��������ԋp����B<BR>
     * @@param l_lngBookbuildingOrderActionId - �u�b�N�r���f�B���O�\�������h�c
     * @@return OrderAction
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC0131
     */
    public OrderAction getOrderAction(long l_lngBookbuildingOrderActionId) throws NotFoundException
    {
        final String STR_METHOD_NAME = " getOrderAction(long)";
        
        log.entering(STR_METHOD_NAME);
        
        OrderAction l_orderAction = null;
        
        try
        {
            //�P�j�@@�u�b�N�r���f�B���O�����s�擾
            //DataFindException, DataQueryException, DataNetworkException
            IpoBookbuildingOrderActionRow l_orderActionRow =
                IpoBookbuildingOrderActionDao.findRowByPk(l_lngBookbuildingOrderActionId);

            //�Q�j�@@�u�b�N�r���f�B���O�\�������I�u�W�F�N�g�쐬
            l_orderAction = this.toOrderAction(l_orderActionRow);
        }
//        catch (DataFindException l_ex)
//        {
//            log.error(getClass().getName() + STR_METHOD_NAME);
//            throw new NotFoundException("�Y������IPO�����s�����݂��Ȃ�");
//        }
        catch (DataQueryException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new NotFoundException("�Y������IPO�����s�����݂��Ȃ�");
        }
        catch (DataNetworkException l_ex)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            throw new NotFoundException("�Y������IPO�����s�����݂��Ȃ�");
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //�R�j�@@�ԋp�l�쐬
        return l_orderAction;
    }

    /**
     * (to�u�b�N�r���f�B���O�\������)<BR>
     * �itoOrderAction�̎����j<BR>
     * <BR>
     * �w��̃u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g����u�b�N�r���f�B���O<BR>
     * �\�������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g�������Ɏw�肵�āA�u�b�N�r���f�B���O<BR>
     * �\�������I�u�W�F�N�g�𐶐�����B<BR>
     * ���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * [�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g<BR>
     * @@param l_bookbuildingOrderActionParams - (�u�b�N�r���f�B���O�\������Params)<BR>
     * �u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g<BR>
     * <BR>
     * �� �u�b�N�r���f�B���O�\������Params�N���X��DDL��莩����������B
     * 
     * @@return OrderAction
     * @@roseuid 40BFFDBC0133
     */
    public OrderAction toOrderAction(Row l_bookbuildingOrderActionParams)
    {
        WEB3IpoBookbuildingOrderActionImpl l_orderAction =
            new WEB3IpoBookbuildingOrderActionImpl(l_bookbuildingOrderActionParams);

        return l_orderAction;
    }

    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_lngArg0 - (arg0)
     * @@return OrderExecution
     * @@throws NotFoundException
     * @@roseuid 40BFFDBC0139
     */
    public OrderExecution getOrderExecution(long l_lngArg0) throws NotFoundException
    {
        return null;
    }

    /**
     * �i���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_arg0 - (arg0)
     * @@return OrderExecution
     * @@roseuid 40BFFDBC013B
     */
    public OrderExecution toOrderExecution(Row l_arg0)
    {
        return null;
    }

    /**
     * �i���g�p�j
     * @@param l_arg0 - (arg0)
     * @@roseuid 40BFFDBC0178
     */
    public void overrideOrderValidator(OrderValidator l_arg0)
    {

    }

    /**
     * �igetOrderValidator�̎����j<BR>
     * <BR>
     * this.�����`�F�b�N��ԋp����B
     * @@return OrderValidator
     * @@roseuid 40BFFDBC0177
     */
    public OrderValidator getOrderValidator()
    {
        return this.orderValidator;
    }

    /**
     * (createNewIPO�\��)<BR>
     * �����̓��e���A�V�K�\����Ԃ�IPO�\���I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �P�j�@@IPO�\���s���擾����B<BR>
     * �@@this.getIPO�\��(long)�ɂāAIPO�\���̊����s���擾����B<BR>
     * <BR>
     * �@@[getIPO�\��()�̈���]<BR>
     * �@@IPO�\���h�c�F�@@������IPO�\���h�c<BR>
     * <BR>
     * �@@�|�s���擾�ł��Ȃ������ꍇ�A�P�|�P�j�`�P�|�R�j�̎葱���ɂ�IPO�\���s��<BR>�V�K�쐬����B<BR>
     * �@@�|�s���擾�ł����ꍇ�A�擾����IPO�\��.getDataSourceObject()�ɂ�<BR>IPO�\���s���擾����B<BR>
     * <BR>
     * �@@�P�|�P�j�@@IPO�\���s�𐶐�����B<BR>
     * �@@IPO�\��Params�I�u�W�F�N�g�𐶐�����B<BR>
     * �@@��IPO�\��Params�N���X��DDL��莩�������B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@IPO�\���s�̊e���ڂɏ����l���Z�b�g����B<BR>
     * �@@�@@�|���t�^�iDate�j�A������^�iString�j�̏����l �F null<BR>
     * �@@�@@�|���l�^�iDouble,double,Long,long,Integer,int�j�̏����l �F 0<BR>
     * Q&A WEB3-IPO-A-�e�s-0037<BR>
     * �@@�@@�|���l�^�iDouble,double,Long,long,Integer,int�j�ŁANotNull���ڂ̏����l �F 0<BR>
     * �@@�@@�|���l�^�iDouble,double,Long,long,Integer,int�j�ŁANotNull���ڈȊO�̏����l �F null<BR>
     * <BR>
     * �@@�P�|�R�j�@@�ȉ��̍��ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@IPO�\���s.IPO�\���h�c = ������IPO�\���h�c<BR>
     * �@@�@@IPO�\���s.IPO�����h�c = �u�b�N�r���f�B���O�\�����e.get�����h�c()<BR>
     * �@@�@@IPO�\���s.���X�h�c = �⏕����.get����X.getBranchId()<BR>
     * �@@�@@IPO�\���s.�����h�c = �⏕����.getAccountId()<BR>
     * �@@�@@IPO�\���s.�⏕�����h�c = �⏕����.getSubAccountId()<BR>
     * �@@�@@IPO�\���s.�\�������ŏI�ԍ� = 0<BR>
     * �@@�@@IPO�\���s.�쐬���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B<BR>
     * �@@�P�j�Ŏ擾����IPO�\���s�I�u�W�F�N�g�̃v���p�e�B�ɁA�ȉ��̒ʂ�Z�b�g���s���B<BR>
     * <BR>
     * �@@IPO�\���s.�\�������ŏI�ԍ� = �iIPO�\���s.�\�������ŏI�ԍ��{�P�j<BR>
     * �@@IPO�\���s.���� = �u�b�N�r���f�B���O�\�����e.get����()<BR>
     * �@@IPO�\���s.�w�l = �u�b�N�r���f�B���O�\�����e.get�w�l()<BR>
     * �@@IPO�\���s.�u�b�N�r���f�B���O�\����� = OrderStatusEnum.ORDERED<BR>�@@�i�V�K�j<BR>
     * �@@IPO�\���s.IPO�\���L����� = OrderOpenStatusEnum.OPEN�@@<BR>
     * �i�L���j<BR>
     * �@@IPO�\���s.�V�K�\������ = TradingSystem.getSystemTimestamp()<BR>
     * �@@IPO�\���s.���I���� = �h0�FDEFAULT�i�����I�j�h<BR>
     * �@@IPO�\���s.���I���ʁi�J��j = �h0�FDEFAULT�i�����I�j�h<BR>
     * �@@IPO�\���s.�d�q���C�����M�X�e�C�^�X = �h0�FDEFAULT�i�����l�j�h<BR>
     * 
     *   �d�l�ύX�Ǘ��䒠-���f��032<BR>
     * �@@IPO�\���s.�w���\���敪 = �h0�FDEFAULT�i�����l�j�h<BR>
     *
     * �@@IPO�\���s.��t��� = �h0�FDEFAULT�i�����l�j�h<BR>
     *   IPO�\���s.�����ID = �u�b�N�r���f�B���O�\�����e.getTrader().getTraderId() <BR>
     *   IPO�\���s.�����o�H�敪 = (*1) 
     * �@@IPO�\���s.�X�V�҃R�[�h = (*2)<BR>
     * �@@IPO�\���s.�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * �@@<BR>
     *   (*1)�@@�����o�H�敪 <BR>
     *   �Z�b�V�����v���p�e�B��胍�O�C���`���l�����擾����B<BR>
     *   @@see OpLoginSecurityService.getSessionProperty(LOGIN_CHANNEL) <BR> 
     *   <BR>
     * �@@(*2)�@@�X�V�҃R�[�h<BR>
     * �@@�i�u�b�N�r���f�B���O�\�����e.getTrader() == null�j�̏ꍇ<BR>
     * �@@�@@�|�⏕����.getMainAccount().getAccountCode()<BR>
     * �@@�ȊO�̏ꍇ<BR>
     * �@@�@@�|�u�b�N�r���f�B���O�\�����e.getTrader().getTraderCode()<BR>
     * <BR>
     * �S�j�@@IPO�\���I�u�W�F�N�g����<BR>
     * �@@�P�j�`�R�j�ō쐬����IPO�\���s�I�u�W�F�N�g�ɂāAIPO�\���I�u�W�F�N�g�𐶐���<BR>�ԋp����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�̈���]<BR>
     * �@@IPO�\���s�I�u�W�F�N�g�F�@@IPO�\���s�I�u�W�F�N�g<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_bookbuidingNewOrderSpec - (�u�b�N�r���f�B���O�\�����e)<BR>
     * �u�b�N�r���f�B���O�\�����e�I�u�W�F�N�g
     * @@param l_lngIpoOrderId - IPO�\���h�c<BR>
     * <BR>
     * �V�K�̔Ԃ����l���w�肷��B
     * 
     * @@return WEB3IpoOrderImpl
     * @@roseuid 40D8E4B702B7
     */
    protected WEB3IpoOrderImpl createNewIpoOrder(
        SubAccount l_subAccount,
        NewOrderSpec l_bookbuidingNewOrderSpec,
        long l_lngIpoOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createNewIpoOrder(SubAccount, NewOrderSpec, long)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuidingNewOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3IpoOrderImpl l_ipoOrder = null;

//        try
//        {
            //�u�b�N�r���f�B���O�\�����e�I�u�W�F�N�g
            WEB3IpoBookbuildingNewOrderSpec l_bbOrderSpec =
                (WEB3IpoBookbuildingNewOrderSpec)l_bookbuidingNewOrderSpec;
                
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            
            //�P�j�@@IPO�\���s���擾����B
            //NotFoundException
            OrderUnit l_orderUnit;
            
            try
            {
                l_orderUnit = this.getOrderUnit(l_lngIpoOrderId);
            }
            catch (NotFoundException l_ex)
            {
                l_orderUnit = null;
            }
            
            IpoOrderParams l_orderParams = null;

            if (l_orderUnit == null)
            {
                //�|�s���擾�ł��Ȃ������ꍇ�A�P�|�P�j�`�P�|�R�j�̎葱���ɂ�IPO�\���s��<BR>�V�K�쐬����B

                log.debug("Not Existing OrderUnit for IpoOrderId: " + l_lngIpoOrderId + " IPO�\���s�𐶐�����");
                
                //�P�|�P�j�@@IPO�\���s�𐶐�����B
                l_orderParams = new IpoOrderParams();

                //�P�|�Q�j�@@IPO�\���s�̊e���ڂɏ����l���Z�b�g����B
                
                //Q&A WEB3-IPO-A-�e�s-0037
                //  �|���l�^�iDouble,double,Long,long,Integer,int�j�ŁANotNull���ڂ̏����l �F 0
                //  �|���l�^�iDouble,double,Long,long,Integer,int�j�ŁANotNull���ڈȊO�̏����l �F null
                
                l_orderParams.setIpoOrderId(0);               //IPO�\���h�c
                l_orderParams.setIpoProductId(0);             //IPO�����h�c
                l_orderParams.setBranchId(0);                 //���X�h�c
                l_orderParams.setAccountId(0);                //�����h�c
                l_orderParams.setSubAccountId(0);             //�⏕�����h�c
                l_orderParams.setLastOrderActionSerialNo(0);  //�\�������ŏI�ԍ�
                l_orderParams.setQuantity(0);                 //����
                l_orderParams.setLimitPrice(0);               //�w�l
                l_orderParams.setOrderStatus(OrderStatusEnum.UNDEFINED);     //�u�b�N�r���f�B���O�\�����
                l_orderParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);   //IPO�\���L�����
                
                l_orderParams.setPrice(null);                //�v�Z�P��
                l_orderParams.setCurrentPrice(null);         //��l�i�����j
                l_orderParams.setBookbuildingPrice(null);    //�\�������z
                l_orderParams.setBookbuildingCreatedTimestamp(null);  //�V�K�\������
                l_orderParams.setElectedQuantity(null);      //���I����
                l_orderParams.setLotResult(null);            //���I����
                l_orderParams.setLotResultRetry(null);       //���I���ʁi�J��j
                l_orderParams.setSubstitutePriority(null);   //�D�揇��
                l_orderParams.setSendMailStatus(null);       //�d�q���C�����M�X�e�C�^�X
                l_orderParams.setOfferingDiv(null);          //�w���\���敪
                l_orderParams.setAcceptStatus(null);         //��t���
                l_orderParams.setApplicationQuantity(null);  //�w���\������
                l_orderParams.setPayAmount(null);            //�w���\�����
                l_orderParams.setTaxType(null);   //�ŋ敪
                l_orderParams.setOfferingTimestamp(null);    //�w���\���^���ޓ���
                l_orderParams.setTraderId(null);             //����҂h�c
                l_orderParams.setOrderRootDiv(null);         //�����o�H�敪
                l_orderParams.setLastUpdater(null);          //�X�V�҃R�[�h
                l_orderParams.setCreatedTimestamp(null);     //�쐬����
                l_orderParams.setLastUpdatedTimestamp(null); //�X�V����

                //�P�|�R�j�@@���ڂɒl���Z�b�g����B
                l_orderParams.setIpoOrderId(l_lngIpoOrderId);
                l_orderParams.setIpoProductId(l_bbOrderSpec.getProductId());
                l_orderParams.setBranchId(
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId());
                l_orderParams.setAccountId(l_subAccount.getAccountId());
                l_orderParams.setSubAccountId(l_subAccount.getSubAccountId());
                l_orderParams.setLastOrderActionSerialNo(0);
                l_orderParams.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
                
                // ���I�ԍ����擾
                String strLotNumber = this.getLotNumber();
                
                // ���I�ԍ����Z�b�g
                l_orderParams.setLotNumber(strLotNumber);

            }
            else
            {
                log.debug("Found OrderUnit for IpoOrderId: " + l_lngIpoOrderId  + " IPO�\���s���擾����");
                //�|�s���擾�ł����ꍇ�A�擾����IPO�\��.getDataSourceObject()�ɂ�<BR>IPO�\���s���擾����B
                IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_orderUnit.getDataSourceObject();
                
                l_orderParams = new IpoOrderParams(l_ipoOrderRow);
            }

            //�Q�j�@@�s�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
            l_orderParams.setLastOrderActionSerialNo(l_orderParams.last_order_action_serial_no + 1);
            l_orderParams.setQuantity(l_bbOrderSpec.getQuantity());
            l_orderParams.setLimitPrice(l_bbOrderSpec.getLimitPrice());
            l_orderParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_orderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            l_orderParams.setBookbuildingCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
            l_orderParams.setLotResult(WEB3LotResultDef.DEFAULT);
            l_orderParams.setLotResultRetry(WEB3LotResultRetryDef.DEFAULT);
            l_orderParams.setSendMailStatus(WEB3SendMailStatusDef.DEFAULT);
            
            // �d�l�ύX�Ǘ��䒠-���f��032<BR>
            // IPO�\���s.�w���\���敪 = �h0�FDEFAULT�i�����l�j�h<BR>
            l_orderParams.setOfferingDiv(WEB3OfferingDivDef.DEFAULT);
            
            l_orderParams.setAcceptStatus(WEB3IpoOrderAcceptStatusDef.DEFAULT);
                        
            // �戵��ID�̃Z�b�g
            if (l_bbOrderSpec.getTrader() != null)
            {
                l_orderParams.setTraderId(l_bbOrderSpec.getTrader().getTraderId() );
            }


            OpLoginSecurityService l_opLoginSec =
                        (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            l_orderParams.setOrderRootDiv(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV) );

            //(*1)�@@�X�V�҃R�[�h
            if (l_bbOrderSpec.getTrader() == null)
            {
                log.debug("LastUpdater is a Customer");
                l_orderParams.setLastUpdater(l_subAccount.getMainAccount().getAccountCode());
            }
            else
            {
                log.debug("LasterUpdater is a Trader");
                l_orderParams.setLastUpdater(l_bbOrderSpec.getTrader().getTraderCode());
            }

            l_orderParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

            //�S�j�@@IPO�\���I�u�W�F�N�g����
            //NotFoundException
            //�R�[�h�`�F�b�N�w�E����(IPOV1.0-20040928�x�[�X) No. 3
            l_ipoOrder =
                (WEB3IpoOrderImpl)this.toOrderUnit(l_orderParams);
            
//        }
//        catch (NotFoundException l_ex)
//        {
//            //�I�v�V�������f�[�^��������܂���B
//            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
//            //��O���X���[����
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + STR_METHOD_NAME);
//        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_ipoOrder;
    }

    /**
     * (saveIPO�\��)<BR>
     * �P�j IPO�\���s�I�u�W�F�N�g�擾<BR>
     * �@@IPO�\��.getDataSourceObject()�ɂ�IPO�\���s���擾����B<BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B<BR>
     * �@@IPO�\���s�̈ȉ��̍��ڂɁA�l���Z�b�g����B<BR>
     * <BR>
     *   IPO�\���s.�����ID = (*1)
     *   IPO�\���s.�����o�H�敪 = (*2) 
     * �@@IPO�\���s.�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     *   (*1)�@@�����ID
     *   �Z�b�V��������胍�O�C��ID���擾���A�Y�����鈵�҂̈���ID���Z�b�g����B<BR>
     *   ���O�C��ID�ɊY�����鈵�҂��擾�ł��Ȃ��ꍇ�Anull���Z�b�g����B<BR>
     *   @@see OpLoginSecurityService.getLoginId() <BR>
     *   @@see FinObjectManager.getTraderByLoginId()<BR>
     *   (*2)�@@�����o�H�敪<BR>
     *   �Z�b�V�����v���p�e�B��胍�O�C���`���l�����擾����B<BR>
     *   @@see OpLoginSecurityService.getSessionProperty(LOGIN_CHANNEL) <BR>
     * <BR>
     * �R�j DB�X�V<BR>
     * �@@IPO�\���s�I�u�W�F�N�g�̓��e�ŁAIPO�\���e�[�u�����X�V�iupdate�j����B
     *   @@throws WEB3BaseException
     *   @@param l_ipoOrder - (IPO�\��)<BR>
     *   IPO�\���I�u�W�F�N�g
     *   @@roseuid 40CEAFC70336
     */
    public void saveIpoOrder(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveIpoOrder(WEB3IpoOrderImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoOrder == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�j IPO�\���s�I�u�W�F�N�g�擾
            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();

            //�Q�j �X�V�����Z�b�g����B
                
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            
            try
            {
                Trader l_trdTrader = l_finApp.getFinObjectManager().getTraderByLoginId(l_opLoginSec.getLoginId());
                l_ipoOrderParams.setTraderId(l_trdTrader.getTraderId());
            }
            catch(NotFoundException nf_ex)
            {
                l_ipoOrderParams.setTraderId(null);
            }
            
            
            l_ipoOrderParams.setOrderRootDiv(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV) );            
 
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                        
            l_ipoOrderParams.setLastUpdatedTimestamp(l_tradingSystem.getSystemTimestamp());

            log.debug("SystemTimestamp: " + l_tradingSystem.getSystemTimestamp());
            
            //�R�j DB�X�V
            //DataNetworkException, DataFindException
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //DataQueryException
            l_queryProcessor.doUpdateQuery(l_ipoOrderParams);  
                  
            log.exiting(STR_METHOD_NAME);
        
        }
        catch (DataFindException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (saveNew�u�b�N�r���f�B���O�\������)<BR>
     * IPO�\���̓��e���A�u�b�N�r���f�B���O�\���������쐬�ADB��insert���s���B<BR>
     * <BR>
     * �P�j IPO�\���s�I�u�W�F�N�g�擾<BR>
     * �@@IPO�\��.getDataSourceObject()�ɂ�IPO�\���s���擾����B<BR>
     * <BR>
     * �Q�j �X�V�����Z�b�g����B<BR>
     * �@@�u�b�N�r���f�B���O�\�������s�̍��ڂɁA�l���Z�b�g����B<BR>
     * <BR>
     * �@@�Z�b�g������e�́A<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�ޯ���ً���_�u�b�N�r���f�B���O�\�������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * �R�j DB�X�V<BR>
     * �@@�u�b�N�r���f�B���O�\�������s�I�u�W�F�N�g�̓��e�ŁA�u�b�N�r���f�B���O<BR>
     * �\�������e�[�u�����X�V�iinsert�j����B<BR>
     * @@throws WEB3BaseException
     * @@param l_ipoOrder - (IPO�\��)<BR>
     * IPO�\���I�u�W�F�N�g
     * @@roseuid 40CEB11803C3
     */
    public void saveNewIpoBookbuildingOrderAction(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewIpoBookbuildingOrderAction(WEB3IpoOrderImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoOrder == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�j IPO�\���s�I�u�W�F�N�g�擾
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

            IpoBookbuildingOrderActionParams l_bbOrderActionParams =
                new IpoBookbuildingOrderActionParams();

            //�Q�j �X�V�����Z�b�g����B
            //DataNetworkException, DataQueryException
            l_bbOrderActionParams.setBookbuildingOrderActionId(IpoBookbuildingOrderActionDao.newPkValue());         //�u�b�N�r���f�B���O�\�������h�c
            l_bbOrderActionParams.setIpoOrderId(l_ipoOrderParams.getIpoOrderId());                  //IPO�\���h�c
            l_bbOrderActionParams.setIpoProductId(l_ipoOrderParams.getIpoProductId());              //IPO�����h�c
            l_bbOrderActionParams.setBranchId(l_ipoOrderParams.getBranchId());                      //���X�h�c
            l_bbOrderActionParams.setAccountId(l_ipoOrderParams.getAccountId());                    //�����h�c
            l_bbOrderActionParams.setSubAccountId(l_ipoOrderParams.getSubAccountId());              //�⏕�����h�c
            l_bbOrderActionParams.setOrderActionSerialNo(l_ipoOrderParams.getLastOrderActionSerialNo());  //�\������ԍ�
            l_bbOrderActionParams.setQuantity(l_ipoOrderParams.getQuantity());                      //����
            l_bbOrderActionParams.setLimitPrice(l_ipoOrderParams.getLimitPrice());                  //�w�l
            l_bbOrderActionParams.setOrderStatus(l_ipoOrderParams.getOrderStatus());                //�u�b�N�r���f�B���O�\�����
            l_bbOrderActionParams.setOrderOpenStatus(l_ipoOrderParams.getOrderOpenStatus());        //IPO�\���L�����
            
            if(l_ipoOrderParams.getPriceIsNull())
            {
                l_bbOrderActionParams.setPrice(null);
            }
            else
            {
                l_bbOrderActionParams.setPrice(l_ipoOrderParams.getPrice());                            //�v�Z�P��
            }
            
            if(l_ipoOrderParams.getCurrentPriceIsNull())
            {
                l_bbOrderActionParams.setCurrentPrice(null);
            }
            else
            {
                l_bbOrderActionParams.setCurrentPrice(l_ipoOrderParams.getCurrentPrice());              //��l�i�����j
            }

            if(l_ipoOrderParams.getBookbuildingPriceIsNull())
            {
                l_bbOrderActionParams.setBookbuildingPrice(null);
            }
            else
            {
                l_bbOrderActionParams.setBookbuildingPrice(l_ipoOrderParams.getBookbuildingPrice());    //�\�������z
            }

            l_bbOrderActionParams.setBookbuildingCreatedTimestamp(l_ipoOrderParams.getBookbuildingCreatedTimestamp());  //�V�K�\������
            if(l_ipoOrderParams.getTraderIdIsNull())
            {
                l_bbOrderActionParams.setTraderId(null);
            }
            else
            {
                l_bbOrderActionParams.setTraderId(l_ipoOrderParams.getTraderId());//�����ID           
            }
            
            l_bbOrderActionParams.setOrderRootDiv(l_ipoOrderParams.getOrderRootDiv());//�����o�H�敪            
            l_bbOrderActionParams.setLastUpdater(l_ipoOrderParams.getLastUpdater());                //�X�V�҃R�[�h
            l_bbOrderActionParams.setDeleteFlag(BooleanEnum.FALSE);                                 //�폜�t���O
            l_bbOrderActionParams.setCreatedTimestamp(l_ipoOrderParams.getLastUpdatedTimestamp());      //�쐬����
            l_bbOrderActionParams.setLastUpdatedTimestamp(l_ipoOrderParams.getLastUpdatedTimestamp());//�X�V����
            l_bbOrderActionParams.setActionSendStatus(WEB3ActionSendStatusDef.DEFAULT);
            log.debug("New BbOrderActionId is " + l_bbOrderActionParams.getBookbuildingOrderActionId());
            //�R�j DB�X�V
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //DataQueryException, DataNetworkException
            l_queryProcessor.doInsertQuery(l_bbOrderActionParams); 
                   
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (saveNewIPO�\��)<BR>
     * �w�肳�ꂽIPO�\���I�u�W�F�N�g�̓��e�ŁAIPO�\���e�[�u�����X�V�iinsert�j����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g�擾<BR>
     * �@@����.IPO�\��.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@DB�X�V<BR>
     * �@@���@@��������ϐ\�������݂��Ȃ��ꍇ�iIPO�\���s.�쐬���� ==<BR>
     *  IPO�\���s.�V�K�\�������j�A<BR>
     * �@@�@@�@@�@@�|�擾�����s�I�u�W�F�N�g�̓��e��IPO�����e�[�u���ɍs��}���iinsert�j<BR>����B<BR>
     * <BR>
     * �@@���@@��������ϐ\�������݂���ꍇ�iIPO�\���s.�쐬���� != <BR>
     * IPO�\���s.�V�K�\�������j�A<BR>
     * �@@�@@�@@�@@�|this.saveIPO�\��()�ɂĊ����s���X�V����B<BR>
     * <BR>
     * �@@�@@�@@�@@[saveIPO�\��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@IPO�\���F�@@IPO�\��<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@param l_ipoOrder - (IPO�\��)<BR>
     * IPO�\���I�u�W�F�N�g
     * @@roseuid 40D9043003A1
     */
    public void saveNewIpoOrder(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewIpoOrder(WEB3IpoOrderImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoOrder == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            //�P�j�@@�s�I�u�W�F�N�g�擾
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
            
            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

            //�Q�j�@@DB�X�V

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor(); //DataNetworkException, DataFindException

            log.debug("CreatedTimestamp: " + l_ipoOrderParams.getCreatedTimestamp());
            log.debug("BookbuildingCreatedTimestamp: " + l_ipoOrderParams.getBookbuildingCreatedTimestamp());
            
            if (l_ipoOrderParams.getCreatedTimestamp().equals(l_ipoOrderParams.getBookbuildingCreatedTimestamp()))
            {
                log.debug("InsertOrder");
                l_queryProcessor.doInsertQuery(l_ipoOrderParams); //DataQueryException, DataNetworkException
            }
            else if (!l_ipoOrderParams.getCreatedTimestamp().equals(l_ipoOrderParams.getBookbuildingCreatedTimestamp()))
            {
                log.debug("SaveOrder");
                this.saveIpoOrder(l_ipoOrder);
            }
        }
        catch (DataFindException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (getNewIPO�\���h�c)<BR>
     * IPO�\���h�c���擾����B<BR>
     * <BR>
     * �P�j�@@�����h�c�̎擾<BR>
     * �@@�ȉ��̏����ɂāAIPO�\���e�[�u������������B<BR>
     * �s���擾�ł����ꍇ�́A�Y���s��IPO�\���h�c��ԋp����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@IPO�\��.IPO�����h�c =�@@IPO����.getIPO�����h�c() And<BR>
     * �@@IPO�\��.���X�h�c =�@@�⏕����.get����X().getBranchId() And<BR>
     * �@@IPO�\��.�����h�c =�@@�⏕����.getAccountId() And<BR>
     * �@@IPO�\��.�⏕�����h�c =�@@�⏕����.getSubAccountId()<BR>
     * <BR>
     * �Q�j�@@IPO�\���h�c�V�K�̔�<BR>
     * �@@�P�j�ōs���擾�ł��Ȃ������ꍇ�A<BR>
     * IPO�\���h�c��V�K�̔�(*1)���ԋp����B<BR>
     * <BR>
     * (*1) IPO�\���h�c�̐V�K�̔�<BR>
     * �@@IPO�\��DAO.newPkValue()���\�b�h�ɂĎ擾����B<BR>
     * �@@�� IPO�\��DAO�N���X��DDL��莩����������B<BR>
     * @@throws WEB3BaseException
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * 
     * @@return long
     * @@roseuid 40D7C6E402E9
     */
    protected long getNewIpoOrderId(SubAccount l_subAccount, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNewIpoOrderId(SubAccount, WEB3IpoProductImpl)";
        
        log.entering(STR_METHOD_NAME);
        
        long l_lngOrderId = 0;
        
        try
        {
            //�P�j�@@�����h�c�̎擾
            //DataNetworkException, DataFindException, DataQueryException
            IpoOrderRow l_orderRow =
                IpoOrderDao.findRowByIpoProductIdBranchIdAccountIdSubAccountId(
                    l_ipoProduct.getProductId(),
                    ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch().getBranchId(),
                    l_subAccount.getAccountId(),
                    l_subAccount.getSubAccountId());
            
            if(l_orderRow != null)
            {
                log.debug("Found a existing OrderId: " + l_orderRow.getIpoOrderId());
                return l_orderRow.getIpoOrderId();
            }
            else
            {
                //�Q�j�@@IPO�\���h�c�V�K�̔�
                l_lngOrderId = IpoOrderDao.newPkValue();
                log.debug("A New OrderId Created Which is " + l_lngOrderId);
            }
        }
        catch (DataFindException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lngOrderId;
    }

    /**
     * (calc�\�������z)<BR>
     * �\�������z���v�Z����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jcalc�\�������z�v�Q�ƁB
     * @@throws WEB3BaseException
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * @@param l_dblLimitPrice - �w�l<BR>
     * �� ���s�̏ꍇ0
     * 
     * @@param l_dblQuantity - ����
     * @@param l_dblCurrentPrice - ��l�i�����j
     * @@return WEB3IpoBookbuildingPriceCalcResult
     * @@roseuid 40D7D6640068
     */
    public WEB3IpoBookbuildingPriceCalcResult calcBookbuildingPrice(
        MainAccount l_mainAccount,
        WEB3IpoProductImpl l_ipoProduct,
        double l_dblLimitPrice,
        double l_dblQuantity,
        double l_dblCurrentPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcBookbuildingPrice(MainAccount, WEB3IpoProductImpl, double, double, double)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_ipoProduct == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("Parameters: START");
        log.debug("�w�l: " + l_dblLimitPrice);
        log.debug("����: " + l_dblQuantity);
        log.debug("��l�i�����j: " + l_dblCurrentPrice);
        log.debug("Parameters: END");
        
        if(Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0D;
        }        
        if(Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }        
        if(Double.isNaN(l_dblCurrentPrice))
        {
            l_dblCurrentPrice = 0D;
        }
        
        //1.1.�v�Z���ʂ��C���X�^���X������B
        WEB3IpoBookbuildingPriceCalcResult l_equivalentPriceCalcResult = new WEB3IpoBookbuildingPriceCalcResult();
        
        //1.2.is�f�B�X�J�E���g����( )
        boolean l_blnIsDiscountHandling = l_ipoProduct.isDiscountHandling();
        
        if(l_blnIsDiscountHandling) //1.3.(*1) �f�B�X�J�E���g�����̏ꍇ
        {
            if(l_dblCurrentPrice == 0) //1.3.1.��l���w��
            {
                log.debug("1.3.1.��l���w��");
                
                log.debug("mainaccount: " + l_mainAccount.getAccountId());
                log.debug("product id: " + l_ipoProduct.getProductId());
                l_dblCurrentPrice = l_ipoProduct.getCurrentPrice(l_mainAccount, l_ipoProduct); //1.3.1.1.get����

                log.debug("1.3.1.1.get����: " + l_dblCurrentPrice);
                
                if(Double.isNaN(l_dblCurrentPrice))
                {
                    l_dblCurrentPrice = 0D;
                }
                //1.3.2.set��l�i����)
                l_equivalentPriceCalcResult.setCurrentPrice(l_dblCurrentPrice);
            }
            else
            {
                log.debug("1.3.2.��l�w��");
                
                l_equivalentPriceCalcResult.setCurrentPrice(l_dblCurrentPrice);
            }
            
            //1.3.3.set�v�Z�P��
            //1.3.4.set�v�Z�P���i�����i�j
            if(l_dblLimitPrice == 0)
            {
                log.debug("���s�̏ꍇ: (l_dblLimitPrice == 0)");
                
                double l_dblProvisionalMinValue =
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMinValue(); //�����������l
                
                log.debug("�����������l: " + l_dblProvisionalMinValue);
                
                if(Double.isNaN(l_dblProvisionalMinValue))
                {
                    l_dblProvisionalMinValue = 0D;
                }
                
                log.debug("1.3.3.set�v�Z�P��: " + l_dblProvisionalMinValue);
                
                l_equivalentPriceCalcResult.setPrice(l_dblProvisionalMinValue);
                
                log.debug("1.3.4.set�v�Z�P���i�����i�j: " + (l_dblCurrentPrice * (100 - l_dblProvisionalMinValue) / 100));
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(
                    l_dblCurrentPrice * (100 - l_dblProvisionalMinValue) / 100);
            }
            else
            {
                log.debug("���s�̏ꍇ: (l_dblLimitPrice != 0)");
                l_equivalentPriceCalcResult.setPrice(l_dblLimitPrice);
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(
                    l_dblCurrentPrice * (100 - l_dblLimitPrice) / 100);
            }
            
        }
        else if(!l_blnIsDiscountHandling) //1.4.�����i�̏ꍇ
        {
            //1.4.1.set�v�Z�P��
            //1.4.2.set�v�Z�P���i�����i�j
            if(l_dblLimitPrice == 0)
            {
                double l_dblProvisionalMaxValue =
                    ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMaxValue(); //����������l
                
                log.debug("����������l: " + l_dblProvisionalMaxValue);
                if(Double.isNaN(l_dblProvisionalMaxValue))
                {
                    l_dblProvisionalMaxValue = 0D;
                }
                
                l_equivalentPriceCalcResult.setPrice(l_dblProvisionalMaxValue);
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(l_dblProvisionalMaxValue);
            }
            else
            {
                log.debug("LimitPrice: " + l_dblLimitPrice);
                l_equivalentPriceCalcResult.setPrice(l_dblLimitPrice);
                
                l_equivalentPriceCalcResult.setCalcUnitPriceReal(l_dblLimitPrice);
            }
            
            //Q&A:WEB3-IPO-A-FT-0055
            l_equivalentPriceCalcResult.setCurrentPrice(Double.NaN);
        }
        
        //1.5.get�v�Z�P���i�����i�j
        double l_dblCalcUnitPriceTruePrice = l_equivalentPriceCalcResult.getCalcUnitPriceReal();
        
        log.debug("1.5.get�v�Z�P���i�����i�j: " + l_dblCalcUnitPriceTruePrice);
        
        if(Double.isNaN(l_dblCalcUnitPriceTruePrice))
        {
            l_dblCalcUnitPriceTruePrice = 0D;
        }
        
        //1.6.set�\�������z
        log.debug("1.6.set�\�������z: " + (l_dblQuantity * l_dblCalcUnitPriceTruePrice));
        l_equivalentPriceCalcResult.setBookbuildingPrice(l_dblQuantity * l_dblCalcUnitPriceTruePrice);
        
        //1.7 validate�\�������z
        l_equivalentPriceCalcResult.validateBookbuildingPrice(); //WEB3BaseException
        
        log.exiting(STR_METHOD_NAME);
        
        return l_equivalentPriceCalcResult;
    }

    /**
     * (validate�u�b�N�r���f�B���O�\��)<BR>
     * �ivalidateNewOrder�̎����j<BR>
     * <BR>
     * �u�b�N�r���f�B���O�\�����͐R�������{����B<BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jvalidate�u�b�N�r���f�B���O�\���v�Q�ƁB
     * @@param l_subAccount - (�⏕����)<BR>
     * �������I�u�W�F�N�g
     * @@param l_productType - �����^�C�v
     * @@param l_bookbuildingNewOrderSpec - (�u�b�N�r���f�B���O�\�����e)<BR>
     * �u�b�N�r���f�B���O�\�����e�I�u�W�F�N�g
     * @@return NewOrderValidationResult
     * @@roseuid 40BFFDBC013D
     */
    public NewOrderValidationResult validateNewOrder(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        NewOrderSpec l_bookbuildingNewOrderSpec)
    {
        final String STR_METHOD_NAME = " validateNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_bookbuildingNewOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        NewOrderValidationResult l_newOrderValidationResult = null;
        
        try
        {
            //�u�b�N�r���f�B���O�\�����e�I�u�W�F�N�g
            WEB3IpoBookbuildingNewOrderSpec l_ipoBookbuildingOrderSpec =
                (WEB3IpoBookbuildingNewOrderSpec)l_bookbuildingNewOrderSpec;
            
            //1.1.getOrderValidator( )
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.validate����\�ڋq
            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);

            log.debug("OrderValidationResult: " + l_orderValidationResult.getProcessingResult().toString());
            
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.exiting(STR_METHOD_NAME);
                return new NewOrderValidationResult(l_orderValidationResult.getProcessingResult());
            }
            
            //1.3.get�����h�c
            long l_lngProductId = l_ipoBookbuildingOrderSpec.getProductId();

            log.debug("1.3.get�����h�c: " + l_lngProductId);
            
            //1.4.IPO����
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct =
                (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngProductId);
            
            //1.5.validate�u�b�N�r���f�B���O����
            //WEB3BaseException
            log.debug("1.5.validate�u�b�N�r���f�B���O����");
            l_orderValidator.validateBookbuildingProduct(l_ipoProduct);
            
            //1.6.validate��d�\��
            log.debug("1.6.validate��d�\��");
            l_orderValidator.validateDuplicateOrder(l_subAccount, l_ipoProduct);
            
            //1.7.get����
            double l_dblQuantity = l_ipoBookbuildingOrderSpec.getQuantity();
            
            log.debug("1.7.get����: " + l_dblQuantity);
            
            //1.8.validate����
            l_orderValidator.validateQuantity(l_ipoProduct, l_dblQuantity);
            
            //1.9.get�w�l
            double l_dblLimitPrice = l_ipoBookbuildingOrderSpec.getLimitPrice();
            
            log.debug("1.9.get�w�l��: " + l_dblLimitPrice);
            
            //1.10.validate�P��
            log.debug("1.10.validate�P��");
            l_orderValidator.validatePrice(l_ipoProduct, l_dblLimitPrice);
            
            //1.11.is����\�������`�F�b�N
            //����\�������`�F�b�N�����{���邩�𔻒肷��B 
            //[����] 
            //�⏕���� : �⏕�����I�u�W�F�N�g
            log.debug("1.11.is����\�������`�F�b�N");
            boolean l_blnReturn = this.isMaxDemandProductCountCheck(l_subAccount);
            
            //1.12.is����\�������`�F�b�N�i�j�̖߂�l == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnReturn)
            {
                //1.12.1validate����\������(IPO����, double)
                log.debug("1.12.1validate����\������(IPO����, double)");
                l_orderValidator.validateMaxDemandProductCount(l_ipoProduct, l_dblQuantity);
            }
            
            //1.13.getNewIPO�\���h�c
            long l_lngNewIpoOrderId = this.getNewIpoOrderId(l_subAccount, l_ipoProduct);
            
            log.debug("1.13.getNewIPO�\���h�c: " + l_lngNewIpoOrderId);
            
            //1.14.NewOrderValidationResult
            l_newOrderValidationResult =
                new NewOrderValidationResult(
                    l_orderValidationResult.getProcessingResult(),
                    l_lngNewIpoOrderId);
        }
        catch (NotFoundException l_ex)
        {
        	//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
//            log.error(getClass().getName() + STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + STR_METHOD_NAME
//            );
        }

        log.exiting(STR_METHOD_NAME);
        return l_newOrderValidationResult;
    }

    /**
     * (submit�u�b�N�r���f�B���O�\��)<BR>
     * �isubmitNewOrder�̎����j<BR>
     * <BR>
     * �u�b�N�r���f�B���O�\����o�^����B<BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jsubmit�u�b�N�r���f�B���O�\���v�Q�ƁB
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - �����^�C�v
     * @@param l_bookbuildingNewOrderSpec - (�u�b�N�r���f�B���O�\�����e)<BR>
     * �u�b�N�r���f�B���O�\�����e�I�u�W�F�N�g
     * @@param l_lngIpoOrderId - IPO�\���h�c<BR>
     * <BR>
     * �V�K�̔Ԃ����l���w�肷��B
     * 
     * @@param l_strTradedPassword - ����p�X���[�h
     * @@param l_blnIsSkipOrderSpecValidation - (isSkip�\�����e�R��)<BR>
     * �\�����e�R���ivalidate�j���X�L�b�v���邩�𔻒肷��t���O�B<BR>
     * <BR>
     * �X�L�b�v����ꍇtrue�A�R�������{����ꍇfalse���w�肷��B
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40BFFDBC0141
     */
    public OrderSubmissionResult submitNewOrder(
        SubAccount l_subAccount,
        ProductTypeEnum l_productType,
        NewOrderSpec l_bookbuildingNewOrderSpec,
        long l_lngIpoOrderId,
        String l_strTradedPassword,
        boolean l_blnIsSkipOrderSpecValidation)
    {
        final String STR_METHOD_NAME = " submitNewOrder(SubAccount, ProductTypeEnum, NewOrderSpec, long, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuildingNewOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_orderValidationResult = null;
        
        try
        {
            WEB3IpoBookbuildingNewOrderSpec l_ipoBbOrderSpec =
                (WEB3IpoBookbuildingNewOrderSpec)l_bookbuildingNewOrderSpec;

            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.getTrader
            Trader l_trader = l_bookbuildingNewOrderSpec.getTrader();

            //1.3.validate����p�X���[�h
            log.debug("1.3.validate����p�X���[�h");
            
            l_orderValidationResult =
                l_orderValidator.validateTradingPassword(
                    l_trader,
                    l_subAccount,
                    l_strTradedPassword);
            
            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate����p�X���[�h Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
            }

            //1.4.isSkip�\�����e�R��
            log.debug("1.4.isSkip�\�����e�R��: " + !l_blnIsSkipOrderSpecValidation);
            
            if (!l_blnIsSkipOrderSpecValidation)
            {
                //1.4.1.validate�u�b�N�r���f�B���O�\��
                log.debug("1.4.1.validate�u�b�N�r���f�B���O�\��");
                
                l_orderValidationResult =
                    this.validateNewOrder(l_subAccount, l_productType, l_bookbuildingNewOrderSpec);
                    
                if(l_orderValidationResult.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validate�u�b�N�r���f�B���O�\�� Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
                }
            }

            //1.5.createNewIPO�\��
            WEB3IpoOrderImpl l_ipoOrder =
                this.createNewIpoOrder(l_subAccount, l_bookbuildingNewOrderSpec, l_lngIpoOrderId);
            log.debug("NewIpoOrderId: " + l_ipoOrder.getOrderId());

            //1.5.1.IPO�\��
            //IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();

            //1.6.get�w�l
            double l_dblLimitPrice = l_ipoBbOrderSpec.getLimitPrice();
            log.debug("1.6.get�w�l: " + l_dblLimitPrice);

            //1.7.get����
            double l_dblQuantity = l_ipoBbOrderSpec.getQuantity();
            log.debug("1.7.get����: " + l_dblQuantity);

            //1.8.get�����h�c
            long l_lngProductId = l_ipoBbOrderSpec.getProductId();
            log.debug("1.8.get�����h�c: " + l_lngProductId);

            //1.9.get��l�i�����j
            double l_dblBasePrice = l_ipoBbOrderSpec.getCurrentPrice();
            log.debug("1.9.get��l�i�����j: " + l_dblBasePrice);

            //1.10.IPO����
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoProductImpl l_ipoProduct =
                (WEB3IpoProductImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getProductManager().getProduct(l_lngProductId);
            
            //1.11.calc�\�������z
            log.debug("1.11.calc�\�������z");
            //WEB3BaseException
            WEB3IpoBookbuildingPriceCalcResult l_calcResult = this.calcBookbuildingPrice(
                l_subAccount.getMainAccount(),
                l_ipoProduct,
                l_dblLimitPrice,
                l_dblQuantity,
                l_dblBasePrice);
            
            //1.12.set�\�������z�v�Z����
            l_ipoOrder.setBookbuildingPriceCalcResult(l_calcResult);
            
            //1.13.saveNewIPO�\��
            log.debug("1.13.saveNewIPO�\��");
            this.saveNewIpoOrder(l_ipoOrder);
            
            //1.14.saveNew�u�b�N�r���f�B���O�\������
            log.debug("1.14.saveNew�u�b�N�r���f�B���O�\������");
            this.saveNewIpoBookbuildingOrderAction(l_ipoOrder);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));

        }
        
        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
    }

    /**
     * (validate�u�b�N�r���f�B���O����)<BR>
     * �ivalidateChangeOrder�̎����j<BR>
     * <BR>
     * �u�b�N�r���f�B���O�������͐R�������{����B<BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jvalidate�u�b�N�r���f�B���O�����v�Q�ƁB<BR>
     * <BR>
     * ========================================================<BR>
     *  �V�[�P���X�}:�iIPO�����jvalidate�u�b�N�r���f�B���O����  <BR>
     *  1.13.�������e�`�F�b�N<BR>
     * �@@���ʁA�P���ɒ������Ȃ��ꍇ���́A��O���X���[����B<BR>
     *   <BR>
�@@   *  ���@@�������Ȃ��ꍇ�̔���<BR>
�@@   *�i�u�b�N�r���f�B���O�������e.get����() == IPO�\��.get����() &&<BR>
�@@�@@ * �u�b�N�r���f�B���O�������e.get�w�l() == IPO�\��.get�w�l()�j<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00595<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_bookbuildingChangeOrderSpec - (�u�b�N�r���f�B���O�������e)<BR>
     * �u�b�N�r���f�B���O�������e�I�u�W�F�N�g
     * @@return OrderValidationResult
     * @@roseuid 40BFFDBC0148
     */
    public OrderValidationResult validateChangeOrder(
        SubAccount l_subAccount,
        ChangeOrderSpec l_bookbuildingChangeOrderSpec)
    {
        final String STR_METHOD_NAME = " validateChangeOrder(SubAccount, ChangeOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_bookbuildingChangeOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_resultHolder = null;
        
        try
        {
            //�u�b�N�r���f�B���O�������e�I�u�W�F�N�g
            WEB3IpoBookbuildingChangeOrderSpec l_ipoBbChangeSpec = (WEB3IpoBookbuildingChangeOrderSpec)l_bookbuildingChangeOrderSpec;
            
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.validate����\�ڋq
            l_resultHolder = l_orderValidator.validateSubAccountForTrading(l_subAccount);
            
            if(l_resultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate����\�ڋq Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(l_resultHolder.getProcessingResult());
            }
        
            //1.3.getOrderId
            long l_lngOrderId = l_bookbuildingChangeOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);
        
            //1.4.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.getIPO����
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.6.validate�u�b�N�r���f�B���O����
            //WEB3BaseException
            log.debug("1.6.validate�u�b�N�r���f�B���O����");
            l_orderValidator.validateBookbuildingProduct(l_ipoProduct);
            
            //1.7.get����
            double l_dblQuantity = l_ipoBbChangeSpec.getQuantity();
            log.debug("1.7.get����" + l_dblQuantity);
            
            //1.8.validate����
            log.debug("1.8.validate����");
            l_orderValidator.validateQuantity(l_ipoProduct, l_dblQuantity);
            
            //1.9.is����\�������`�F�b�N
            //����\�������`�F�b�N�����{���邩�𔻒肷��B 
            //[����] 
            //�⏕���� : �⏕�����I�u�W�F�N�g
            log.debug("1.9.is����\�������`�F�b�N");
            boolean l_blnReturn = this.isMaxDemandProductCountCheck(l_subAccount);
            
            //1.10.is����\�������`�F�b�N�i�j�̖߂�l == true �̏ꍇ�A�ȉ��̏��������s
            if (l_blnReturn)
            {
                //1.10.1validate����\������(IPO����, double)
                log.debug("1.12.1validate����\������(IPO����, double)");
                l_orderValidator.validateMaxDemandProductCount(l_ipoProduct, l_dblQuantity);
            }
            
            //1.11.get�w�l
            double l_dblLimitPrice = l_ipoBbChangeSpec.getLimitPrice();
            log.debug("1.9.get�w�l" + l_dblLimitPrice);
            
            //1.12.validate�P��
            log.debug("1.10.validate�P��");
            l_orderValidator.validatePrice(l_ipoProduct, l_dblLimitPrice);
            
            //1.13.get����
            double l_dblOrderQuantity = l_ipoOrder.getQuantity();
            log.debug("1.11.get����" + l_dblOrderQuantity);
            
            //1.14.get�w�l
            double l_dblOrderLimitPrice = l_ipoOrder.getLimitPrice();
            log.debug("1.12.get�w�l" + l_dblOrderLimitPrice);
            
            //1.15.�������e�`�F�b�N
            log.debug("1.13.�������e�`�F�b�N");
            if(l_dblQuantity == l_dblOrderQuantity && l_dblLimitPrice == l_dblOrderLimitPrice)
            {
                log.debug("1.13.�������e�`�F�b�N Failed");
                log.error(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00595,
                    getClass().getName() + "validateChangeOrder");
            }
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        //1.16.OrderValidationResult
        log.exiting(STR_METHOD_NAME);
        return new OrderValidationResult(l_resultHolder.getProcessingResult());
    }

    /**
     * (submit�u�b�N�r���f�B���O����)<BR>
     * �isubmitChangeOrder�̎����j<BR>
     * <BR>
     * �u�b�N�r���f�B���O������o�^����B<BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jsubmit�u�b�N�r���f�B���O�����v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_bookbuildingChangeOrderSpec - (�u�b�N�r���f�B���O�������e)<BR>
     * �u�b�N�r���f�B���O�������e�I�u�W�F�N�g
     * @@param l_strTradedPassword - ����p�X���[�h
     * @@param l_blnIsSkipChangeSpecValidation - (isSkip�������e�R��)<BR>
     * �������e�R���ivalidate�j���X�L�b�v���邩�𔻒肷��t���O�B<BR>
     * <BR>
     * �X�L�b�v����ꍇtrue�A�R�������{����ꍇfalse���w�肷��B
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40BFFDBC014B
     */
    public OrderSubmissionResult submitChangeOrder(
        SubAccount l_subAccount,
        ChangeOrderSpec l_bookbuildingChangeOrderSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipChangeSpecValidation)
    {
        final String STR_METHOD_NAME = " submitChangeOrder(SubAccount, ChangeOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuildingChangeOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_orderValidationResult = null;

        try
        {
            //�u�b�N�r���f�B���O�������e�I�u�W�F�N�g
            WEB3IpoBookbuildingChangeOrderSpec l_bbChangeSpec =
                (WEB3IpoBookbuildingChangeOrderSpec)l_bookbuildingChangeOrderSpec;

            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.get�����
            Trader l_trader = l_bbChangeSpec.getTrader();

            //1.3.validate����p�X���[�h
            l_orderValidationResult =
                l_orderValidator.validateTradingPassword(
                    l_trader,
                    l_subAccount,
                    l_strTradedPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate����p�X���[�h Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
            }

            //1.4.isSkip�\�����e�R�� == false
            if (!l_blnIsSkipChangeSpecValidation)
            {
                //1.4.1.validate�u�b�N�r���f�B���O����
                log.debug("1.4.1.validate�u�b�N�r���f�B���O����");
                l_orderValidationResult = 
                    this.validateChangeOrder(l_subAccount, l_bookbuildingChangeOrderSpec);
                
                if(l_orderValidationResult.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validate�u�b�N�r���f�B���O���� Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
                }
            }

            //1.5.getOrderId
            long l_lngOrderId = l_bbChangeSpec.getOrderId();
            log.debug("1.5.getOrderId" + l_lngOrderId);

            //1.6.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);

            l_ipoOrder.createForUpdateParams();
            
            //1.7.getDataSourceObject
//            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);

            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();
            
            //1.8.get�����
            //l_trader = l_bbChangeSpec.getTrader();
            
            //1.9.get����
            double l_dblQuantity = l_bbChangeSpec.getQuantity();
            log.debug("1.9.get����" + l_dblQuantity);
            
            //1.10.get�w�l
            double l_dblLimitPrice = l_bbChangeSpec.getLimitPrice();
            log.debug("1.10.get�w�l" + l_dblLimitPrice);
            
            //1.11.get��l�i����)
            double l_dblBasePrice = l_bbChangeSpec.getCurrentPrice();
            log.debug("1.11.get��l�i����)" + l_dblBasePrice);
            
            //�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
//            l_ipoOrder.createForUpdateParams();
                        
            //1.12.IPO�\���s�iIPO�\��Params�j�Ƀv���p�e�B�Z�b�g
            l_ipoOrderParams.setLastOrderActionSerialNo(l_ipoOrderParams.getLastOrderActionSerialNo() + 1);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            l_ipoOrderParams.setQuantity(l_dblQuantity);
            l_ipoOrderParams.setLimitPrice(l_dblLimitPrice);
            if(l_trader == null)
            {
                log.debug("LastUpdate is a customer");
                l_ipoOrderParams.setLastUpdater(l_subAccount.getMainAccount().getAccountCode());
            }
            else
            {
                log.debug("LastUpdate is a trader");
                l_ipoOrderParams.setLastUpdater(l_trader.getTraderCode());
            }
            
            //1.13.calc�\�������z
            //WEB3BaseException
            WEB3IpoBookbuildingPriceCalcResult l_calcResult =
                this.calcBookbuildingPrice(
                    l_subAccount.getMainAccount(),
                    (WEB3IpoProductImpl)l_ipoOrder.getProduct(),
                    l_dblLimitPrice,
                    l_dblQuantity,
                    l_dblBasePrice);
            
            //1.14.set�\�������z�v�Z����
            l_ipoOrder.setBookbuildingPriceCalcResult(l_calcResult);
            
            //1.15.saveIPO�\��
            //WEB3BaseException
            this.saveIpoOrder(l_ipoOrder);
            
            //1.16.saveNew�u�b�N�r���f�B���O�\������
            //WEB3BaseException
            this.saveNewIpoBookbuildingOrderAction(l_ipoOrder);
                       
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
     
        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
    }

    /**
     * (validate�u�b�N�r���f�B���O���)<BR>
     * �ivalidateCancelOrder�̎����j<BR>
     * <BR>
     * �u�b�N�r���f�B���O����R�����s���B<BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jvalidate�u�b�N�r���f�B���O����v�Q�ƁB
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_bookbuildingCancelOrderSpec - (�u�b�N�r���f�B���O������e)<BR>
     * �u�b�N�r���f�B���O������e�I�u�W�F�N�g<BR>
     * @@return OrderValidationResult
     * @@roseuid 40BFFDBC0150
     */
    public OrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_bookbuildingCancelOrderSpec)
    {
        final String STR_METHOD_NAME = " validateCancelOrder(SubAccount, CancelOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_bookbuildingCancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_resultHolder = null;
        
        try
        {
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.validate����\�ڋq
            l_resultHolder = l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
            if(l_resultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate����\�ڋq Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(l_resultHolder.getProcessingResult());
            }
            //1.3.getOrderId
            long l_lngOrderId = l_bookbuildingCancelOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);
        
            //1.4.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.getIPO����
            WEB3IpoProductImpl l_product = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //??1.6.getIPO�����h�c ?? Unused
            //long l_lngProductId = l_product.getProductId();
            
            //1.7.validate�u�b�N�r���f�B���O����
            //WEB3BaseException
            log.debug("1.7.validate�u�b�N�r���f�B���O����");
            l_orderValidator.validateBookbuildingProduct(l_product);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }
        
        //1.8.OrderValidationResult
        log.exiting(STR_METHOD_NAME);
        return new OrderValidationResult(l_resultHolder.getProcessingResult());
    }

    /**
     * (submit�u�b�N�r���f�B���O���)<BR>
     * �isubmitCancelOrder�̎����j<BR>
     * <BR>
     * �u�b�N�r���f�B���O�����o�^����B<BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jsubmit�u�b�N�r���f�B���O����v�Q�ƁB
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_bookbuildingCancelOrderSpec - (�u�b�N�r���f�B���O������e)<BR>
     * �u�b�N�r���f�B���O������e�I�u�W�F�N�g
     * @@param l_strTradedPassword - ����p�X���[�h
     * 
     * @@param l_blnIsSkipCancelSpecValidation - (isSkip������e�R��)<BR>
     * ������e�R���ivalidate�j���X�L�b�v���邩�𔻒肷��t���O�B<BR>
     * <BR>
     * �X�L�b�v����ꍇtrue�A�R�������{����ꍇfalse���w�肷��B
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40BFFDBC0153
     */
    public OrderSubmissionResult submitCancelOrder(
        SubAccount l_subAccount,
        CancelOrderSpec l_bookbuildingCancelOrderSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipCancelSpecValidation)
    {
        final String STR_METHOD_NAME = " submitCancelOrder(SubAccount, CancelOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_bookbuildingCancelOrderSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processingResultHolder = null;
        
        try
        {
            //�u�b�N�r���f�B���O������e�I�u�W�F�N�g
            WEB3IpoBookbuildingCancelOrderSpec l_bbCancelOrderSpec =
                (WEB3IpoBookbuildingCancelOrderSpec)l_bookbuildingCancelOrderSpec;
        
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.get�����
            Trader l_trader = l_bbCancelOrderSpec.getTrader();
        
            //1.3.validate����p�X���[�h
            l_processingResultHolder =
                l_orderValidator.validateTradingPassword(l_trader, l_subAccount, l_strTradedPassword);
                
            if(l_processingResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate����p�X���[�h Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
            }
        
            //1.4.isSkip�\�����e�R�� == false
            if (!l_blnIsSkipCancelSpecValidation)
            {
                //1.4.1.validate�u�b�N�r���f�B���O���
                log.debug("1.4.1.validate�u�b�N�r���f�B���O���");
                OrderValidationResult l_orderValidationResult = this.validateCancelOrder(l_subAccount, l_bbCancelOrderSpec);
                
                if(l_orderValidationResult.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validate�u�b�N�r���f�B���O��� Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_orderValidationResult.getProcessingResult());
                }
            }
        
            //1.5.getOrderId
            long l_lngOrderId = l_bbCancelOrderSpec.getOrderId();
            log.debug("1.5.getOrderId: " + l_lngOrderId);
        
            //1.6.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            l_ipoOrder.createForUpdateParams();
            
            //1.7.getDataSourceObject
//            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)l_ipoOrder.getDataSourceObject();
//            
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams(l_ipoOrderRow);  
            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();  
            
            //1.8.get�����
            //l_trader = l_bbCancelOrderSpec.getTrader();
            
            //�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
//            l_ipoOrder.createForUpdateParams();
            
            //1.9.IPO�\���s�iIPO�\��Params�j�Ƀv���p�e�B�Z�b�g
            l_ipoOrderParams.setLastOrderActionSerialNo(l_ipoOrderParams.getLastOrderActionSerialNo() + 1);
            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            if(l_trader == null)
            {
                log.debug("LastUpdater is a customer");
                l_ipoOrderParams.setLastUpdater(l_subAccount.getMainAccount().getAccountCode());
            }
            else
            {
                log.debug("LastUpdater is a trader");
                l_ipoOrderParams.setLastUpdater(l_trader.getTraderCode());
            }
            
            //NotFoundException
            WEB3IpoOrderImpl l_newIpoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().toOrderUnit(l_ipoOrderParams);
            
            //1.10.saveIPO�\��
            log.debug("1.10.saveIPO�\��");
            this.saveIpoOrder(l_newIpoOrder);

            //1.11.saveNew�u�b�N�r���f�B���O�\������
            //WEB3BaseException
            log.debug("1.11.saveNew�u�b�N�r���f�B���O�\������");
            this.saveNewIpoBookbuildingOrderAction(l_newIpoOrder);
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  END
        }
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
    }

    /**
     * (validate�w���\��)<BR>
     * �w���\���R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jvalidate�w���\���v�Q�ƁB
     * @@throws WEB3BaseException
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_offerSpec - (�w���\�����e)<BR>
     * �w���\�����e�I�u�W�F�N�g
     * @@return OrderValidationResult
     * @@roseuid 40DBC2B50004
     */
    public OrderValidationResult validateOffer(
        SubAccount l_subAccount,
        ChangeOrderSpec l_offerSpec)
    {
        final String STR_METHOD_NAME = " validateOffer(SubAccount, WEB3IpoChangeOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_offerSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processoringResultHolder = null;
        
        try
        {
            WEB3IpoChangeOrderSpec l_changeOrderSpec = (WEB3IpoChangeOrderSpec)l_offerSpec;
            
            //1.1.getOrderValidator
            WEB3IpoOrderValidator l_orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();
        
            //1.2.validate����\�ڋq
            l_processoringResultHolder =
                l_orderValidator.validateSubAccountForTrading(l_subAccount);
                
            if(l_processoringResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate����\�ڋq Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderValidationResult(l_processoringResultHolder.getProcessingResult());
            }
                    
            //1.3.getOrderId
            long l_lngOrderId = l_changeOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);
        
            //1.4.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotFoundException
            WEB3IpoOrderImpl l_ipoOrder = (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.validate�w���\���E���މ\
            //WEB3BaseException
            log.debug("1.5.validate�w���\���E���މ\");
            l_orderValidator.validateOfferDeclinePossible(l_ipoOrder);
            
            //1.6.getIPO����
            WEB3IpoProductImpl l_ipoProduct = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.7.validate�w���\���\����
            log.debug("1.7.validate�w���\���\����");
            l_orderValidator.validateOfferPossibleProduct(l_ipoProduct);
            
            //1.8.validate��d�\���E����
            log.debug("1.8.validate��d�\���E����");
            l_orderValidator.validateDuplicateAppDecline(l_ipoOrder);
            
            //1.9.get�ŋ敪
            TaxTypeEnum l_taxTypeEnum = l_changeOrderSpec.getTaxType();
            log.debug("1.9.get�ŋ敪: " + l_taxTypeEnum.toString());
            
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();
            //1.10.validate�������
            log.debug("1.10.validate�������");
            l_orderValidator.validateSpecialAccount(
                l_subAccount,
                l_taxTypeEnum,
                l_ipoProductRow.getPublicOfferingDate());
            
            //1.11.get�w���\������
            double l_dblOfferQuantity = l_changeOrderSpec.getApplicationQuantity();
            log.debug("1.11.get�w���\������: " + l_dblOfferQuantity);
            
            //1.12.validate�w���\������
            log.debug("1.12.validate�w���\������");
            l_orderValidator.validateApplicationQuantity(
                l_ipoProduct,
                l_dblOfferQuantity,
                ((IpoOrderRow)l_ipoOrder.getDataSourceObject()).getElectedQuantity());

            //get���XID
            long l_lngBranchId = l_ipoOrder.getBranchId();

            // is�w���\������`�F�b�N(long)
            //���XID�F get���XID()�̖߂�l
            boolean l_blnIsPayAmount = this.isPayAmountCheck(l_lngBranchId);

            //�w���\��������`�F�b�N����ꍇ
            if (l_blnIsPayAmount)
            {
                //validate�w���\�����(�⏕����, IPO����, IPO�\��, double)
                //[����]
                // �⏕�����F ����.�⏕����
                // IPO�����F getIPO����()�̖߂�l
                // IPO�\���F IPO�\��()�̖߂�l
                // �w���\�����ʁF get�w���\������()�̖߂�l
                l_orderValidator.validatePayAmount(
                    l_subAccount,
                    l_ipoProduct,
                    l_ipoOrder,
                    l_dblOfferQuantity);
            }
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  END
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderValidationResult(l_processoringResultHolder.getProcessingResult());
    }

    /**
     * (submit�w���\��)<BR>
     * IPO�\���ɍw���\�����X�V����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jsubmit�w���\���v�Q�ƁB
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_offerSpec - (�w���\�����e)<BR>
     * �w���\�����e�I�u�W�F�N�g
     * @@param l_strTradedPassword - ����p�X���[�h
     * 
     * @@param l_blnIsSkipOfferSpecValidation - (isSkip�w���\�����e�R��)<BR>
     * �w���\�����e�R���ivalidate�j���X�L�b�v���邩�𔻒肷��t���O�B<BR>
     * <BR>
     * �X�L�b�v����ꍇtrue�A�R�������{����ꍇfalse���w�肷��B
     * @@throws WEB3BaseException
     * @@return OrderSubmissionResult
     * @@roseuid 40DBC2B50014
     */
    public OrderSubmissionResult submitOffer(
        SubAccount l_subAccount,
        ChangeOrderSpec l_offerSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipOfferSpecValidation)
    {
        final String STR_METHOD_NAME = " submitOffer(SubAccount, WEB3IpoChangeOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_offerSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processingResultHolder = null;
        
        try
        {
            WEB3IpoChangeOrderSpec l_changeOrderSpec = (WEB3IpoChangeOrderSpec)l_offerSpec;
            
            //1.1.getOrderValidator
            orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.get�����
            Trader l_trader = l_changeOrderSpec.getTrader();

            //1.3.validate����p�X���[�h
            l_processingResultHolder =
                orderValidator.validateTradingPassword(l_trader, l_subAccount, l_strTradedPassword);

            if (l_processingResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate����p�X���[�h Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
            }

            //1.4.isSkip�w���\�����e�R�� == false
            if (!l_blnIsSkipOfferSpecValidation)
            {
                //1.4.1.validate�w���\��
                log.debug(".4.1.validate�w���\��");
                l_processingResultHolder = this.validateOffer(l_subAccount, l_changeOrderSpec);

                if (l_processingResultHolder.getProcessingResult().isFailedResult())
                {
                    log.debug("1.4.1.validate�w���\�� Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
                }
            }

            //1.5.getOrderId
            long l_lngOrderId = l_changeOrderSpec.getOrderId();
            log.debug("1.5.getOrderId: " + l_lngOrderId);

            //1.6.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.7.get�w���\������
            double l_dblOfferQuantity = l_changeOrderSpec.getApplicationQuantity();
            log.debug("1.7.get�w���\������: " + l_dblOfferQuantity);
            
            //1.8.get�ŋ敪
            TaxTypeEnum l_taxTypeEnum = l_changeOrderSpec.getTaxType();
            log.debug("1.8.get�ŋ敪: " + l_taxTypeEnum.toString());
            
            //�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
            l_ipoOrder.createForUpdateParams();
            
            //1.9.�w���\��
            log.debug("1.9.�w���\��");
            l_ipoOrder.offer(l_trader, l_dblOfferQuantity, l_taxTypeEnum);
            
            //1.10.saveIPO�\��
            log.debug("1.10.saveIPO�\��");
            //WEB3BaseException
            this.saveIpoOrder(l_ipoOrder);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));

        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
    }

    /**
     * (validate����)<BR>
     * ���ސR�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jvalidate���ށv�Q�ƁB
     * @@throws WEB3BaseException
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_declineSpec - (���ޓ��e)<BR>
     * ���ޓ��e�I�u�W�F�N�g
     * @@return OrderValidationResult
     * @@roseuid 40DBDF8F0359
     */
    public OrderValidationResult validateDecline(
        SubAccount l_subAccount,
        CancelOrderSpec l_declineSpec)
    {
        final String STR_METHOD_NAME = " validateDecline(SubAccount, WEB3IpoCancelOrderSpec)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_declineSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        OrderValidationResult l_orderValidationResult = null;
        
        try
        {
            WEB3IpoCancelOrderSpec l_cancelOrderSpec = (WEB3IpoCancelOrderSpec)l_declineSpec;
            
            //1.1.getOrderValidator
            orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.validate����\�ڋq
            l_orderValidationResult = orderValidator.validateSubAccountForTrading(l_subAccount);
            
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("1.2.validate����\�ڋq Failed");
                log.exiting(STR_METHOD_NAME);
                return l_orderValidationResult;
            }
            
            //1.3.getOrderId
            long l_lngOrderId = l_cancelOrderSpec.getOrderId();
            log.debug("1.3.getOrderId: " + l_lngOrderId);

            //1.4.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);
            
            //1.5.validate�w���\���E���މ\
            log.debug("1.5.validate�w���\���E���މ\");
            orderValidator.validateOfferDeclinePossible(l_ipoOrder);
            
            //1.6.getIPO����
            WEB3IpoProductImpl l_ipoProoduct = (WEB3IpoProductImpl)l_ipoOrder.getProduct();
            
            //1.7.validate���މ\����
            log.debug("1.7.validate���މ\����");
            orderValidator.validateDeclinePossibleProduct(l_ipoProoduct);
            
            //1.8.validate��d�\���E����
            log.debug("1.8.validate��d�\���E����");
            orderValidator.validateDuplicateAppDecline(l_ipoOrder);
            
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderValidationResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderValidationResult;
    }

    /**
     * (submit����)<BR>
     * IPO�\���Ɏ��ނ��X�V����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iIPO�����jsubmit���ށv�Q�ƁB
     * @@throws WEB3BaseException
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_declineSpec - (���ޓ��e)<BR>
     * ���ޓ��e�I�u�W�F�N�g
     * @@param l_strTradedPassword - ����p�X���[�h
     * 
     * @@param l_blnIsSkipDeclineValidation - (isSkip���ސR��)<BR>
     * ���ސR���ivalidate�j���X�L�b�v���邩�𔻒肷��t���O�B<BR>
     * <BR>
     * �X�L�b�v����ꍇtrue�A�R�������{����ꍇfalse���w�肷��B
     * 
     * @@return OrderSubmissionResult
     * @@roseuid 40DBDF8F0369
     */
    public OrderSubmissionResult submitDecline(
        SubAccount l_subAccount,
        WEB3IpoCancelOrderSpec l_declineSpec,
        String l_strTradedPassword,
        boolean l_blnIsSkipDeclineValidation)
    {
        final String STR_METHOD_NAME = " submitDecline(SubAccount, WEB3IpoCancelOrderSpec, String, boolean)";
        
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null || l_declineSpec == null)
        {
            log.error(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        ProcessingResultHolder l_processingResultHolder = null;
        
        try
        {
            WEB3IpoCancelOrderSpec l_cancelOrderSpec = (WEB3IpoCancelOrderSpec)l_declineSpec;
            
            //1.1.getOrderValidator
            orderValidator = (WEB3IpoOrderValidator)this.getOrderValidator();

            //1.2.get�����
            Trader l_trader = l_cancelOrderSpec.getTrader();

            //1.3.validate����p�X���[�h
            l_processingResultHolder = orderValidator.validateTradingPassword(l_trader, l_subAccount, l_strTradedPassword);

            if (l_processingResultHolder.getProcessingResult().isFailedResult())
            {
                log.debug("1.3.validate����p�X���[�h Failed");
                log.exiting(STR_METHOD_NAME);
                return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
            }

            //1.4.isSkip���ސR�� == false
            if (!l_blnIsSkipDeclineValidation)
            {
                //1.4.1.validate����
                log.debug("1.4.1.validate����");
                l_processingResultHolder = this.validateDecline(l_subAccount, l_cancelOrderSpec);

                if (l_processingResultHolder.getProcessingResult().isFailedResult())
                {     
                    log.debug("1.4.1.validate���� Failed");
                    log.exiting(STR_METHOD_NAME);
                    return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
                }
            }

            //1.5.getOrderId
            long l_lngOrderId = l_cancelOrderSpec.getOrderId();
            log.debug("1.5.getOrderId: " + l_lngOrderId);

            //1.6.IPO�\��
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //NotInstalledException NotFoundException
            WEB3IpoOrderImpl l_ipoOrder =
                (WEB3IpoOrderImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager().getOrderUnit(l_lngOrderId);

            //�X�V�p�̍s�I�u�W�F�N�g�𐶐�����B
            l_ipoOrder.createForUpdateParams(); 
            
            //1.7.����
            log.debug("1.7.����");
            l_ipoOrder.decline(l_trader);
            
            //1.8.saveIPO�\��
            log.debug("1.8.saveIPO�\��");
            this.saveIpoOrder(l_ipoOrder);
        }
        catch (NotFoundException l_ex)
        {
			//2004/12/10 U00446 NotFoundExcepttion�̗�O�����C���@@���@@SRA  START
			log.exiting(STR_METHOD_NAME);
			return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderSubmissionResult(ProcessingResult.newFailedResultInstance(l_ex.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        return new OrderSubmissionResult(l_processingResultHolder.getProcessingResult());
    }
    
    /**
     * (get���I�ԍ�)<BR>
     * IPO�\�����ɒ��I�ԍ��i�����j�𐶐����A�ԋp����B<BR>
     * <BR>
     * @@throws WEB3BaseException<BR>
     * @@return lotNumber String
     * @@roseuid 40DBDF8F0369
     */
    private String getLotNumber() {
        
        // �P�j���I�ԍ���������
        int lotNumberFigure = 10;
        
        int lotNumber = 0;

        // �Q�j���I�ԍ���������
        Random random = new Random();

        // �R�j�����i�[�z��𐶐�
        int[] randomNumbers = new int[lotNumberFigure];
                
        int numberCount = 0;
        
        // �S�j���I�ԍ������̃T�C�Y���ALoop�������s���B
        while(numberCount < lotNumberFigure){
            // �S�|�P�j�����̐���
            int number = random.nextInt(10);
            
            boolean checkFlag = true;

            // �S�|�Q�j�������i�[
            randomNumbers[numberCount] = number;

            numberCount++;
        }

        int i = 0;
        
        StringBuffer strLotNumber = new StringBuffer();
        // �T�j�����i�[�z��̗v�f��append����B
        while(i < randomNumbers.length){
            
            strLotNumber.append(Integer.toString(randomNumbers[i]));

            i++;                        
        }
        // �U�j�����������I�ԍ���String�^�ɕϊ����A�ԋp����B
        return strLotNumber.toString();
    }
    
    
    /**
     * (is����\�������`�F�b�N)<BR>
     * ����\�������`�F�b�N�����{���邩�𔻒肷��B<BR> 
     * <BR>
     * [�߂�l] <BR>
     * true�F �`�F�b�N�v <BR>
     * false�F �`�F�b�N�s�v <BR>
     * <BR>
     * �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B <BR>
     * <BR>
     * [����] <BR>
     * ���XID = ����:�⏕�����I�u�W�F�N�g����擾�������XID <BR>
     * �v���t�@@�����X�� = "ipo.limit.quantity.check" <BR>
     * �v���t�@@�����X���̘A�� = 1 <BR>
     * <BR>
     * �擾�������R�[�h.�v���t�@@�����X�̒l == �h�`�F�b�N���{�h �̏ꍇ�Atrue ��ԋp����B<BR> 
     * <BR>
     * ����ȊO�̏ꍇ�́Afalse��ԋp����B<BR> 
     * �����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     */
    public boolean isMaxDemandProductCountCheck(SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMaxDemandProductCountCheck(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        // �ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B 
        // [����] 
        // ���XID = ����:�⏕�����I�u�W�F�N�g����擾�������XID 
        // �v���t�@@�����X�� = "ipo.limit.quantity.check" 
        // �v���t�@@�����X���̘A�� = 1 
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            WEB3GentradeBranch l_genBranch = 
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch();
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(              
                l_genBranch.getBranchId(),
                WEB3BranchPreferencesNameDef.IPO_LIMIT_QUANTITY_CHECK,
                1);
        } 
        catch (DataNetworkException l_nex) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_nex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nex.getMessage(),
                l_nex);
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
        
        // �擾�������R�[�h.�v���t�@@�����X�̒l == �h�\�������`�F�b�N�v�h �̏ꍇ�Atrue ��ԋp����B 
        if (l_branchReferencesRow != null && WEB3IpoLimitQuantityCheckDef.CHECK_QUANTITY.equals(
                l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        // ����ȊO�̏ꍇ�́Afalse��ԋp����B 
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        log.exiting(STR_METHOD_NAME);
        return false;     
    }

    /**
     * (is�w���\������`�F�b�N)<BR>
     * �w���\��������`�F�b�N���邩�ۂ��𔻒肷��B<BR>
     * <BR>
     * [�߂�l] <BR>
     * true�F �`�F�b�N����B<BR>
     * false�F �`�F�b�N���Ȃ��B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * [����] <BR>
     * �@@���XID = ����.���XID<BR>
     * �@@�v���t�@@�����X�� = "ipo.offer.tradingpower.check"�i<BR>
     * �@@IPO�w���\���]�̓`�F�b�N�j<BR>
     * �@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l = 1�F�`�F�b�N���� �̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     * <BR>
     * �R�j����ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * �@@�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID<BR>
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isPayAmountCheck(long l_lngBranchId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPayAmountCheck(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̏����ŁA���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾����B
        //[����]
        //���XID = ����.���XID
        //�v���t�@@�����X�� = "ipo.offer.tradingpower.check"�iIPO�w���\���]�̓`�F�b�N�j
        //�v���t�@@�����X���̘A�� = 1
        BranchPreferencesRow l_branchReferencesRow = null;
        try
        {
            l_branchReferencesRow = BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                l_lngBranchId,
                WEB3BranchPreferencesNameDef.IPO_OFFER_TRADINGPOWER_CHECK,
                1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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

        // �Q�j�擾�������R�[�h.�v���t�@@�����X�̒l = 1�F�`�F�b�N���� �̏ꍇ�Atrue��ԋp����B
        if (l_branchReferencesRow != null
            && WEB3IpoOfferTradingpowerCheckDef.CHECK.equals(l_branchReferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //����ȊO�̏ꍇ�́Afalse��ԋp����B
        //�����R�[�h���擾�ł��Ȃ������ꍇ���܂ށB
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
