head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoPositionManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�|�W�V�����}�l�[�W��(WEB3IfoPositionManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �����(���u) �V�K�쐬
Revesion History : 2007/06/08 �����F(���u) ���f��645�A651�A691
Revesion History : 2007/06/21 ����(���u) ���f��723�A727
Revesion History : 2007/06/29 ����(���u) ���f��753
Revesion History : 2008/02/20 ��іQ(���u) �敨�EOP�s��Ή�
Revesion History : 2008/03/13 ����(���u) ���f��824
Revesion History : 2008/04/08 ��іQ(���u) OP�ԍψꗗ�̉��P�Ή�
Revesion History : 2008/08/19 ���z(���u) IFO�����_�Ή�
*/

package webbroker3.ifo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Contract;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FilterQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoClosingContractSpecImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoPositionManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoPositionManagerHelper.WEB3IfoPersistentDataManager;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.ifo.define.WEB3IfoSettlementStatusDef;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨OP�|�W�V�����}�l�[�W��)<BR>
 * �敨OP�|�W�V�����}�l�[�W���N���X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3IfoPositionManagerImpl extends IfoPositionManagerImpl
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoPositionManagerImpl.class);

    /**
     * @@roseuid 40C0885002DE
     */
    public WEB3IfoPositionManagerImpl()
    {
        m_helper = null;
        m_tradingType = null;
        m_tradingType = ProductTypeEnum.IFO;
        m_helper = new WEB3IfoPositionManagerHelper(m_tradingType);
    }

    /**
     * (create�����R�[�h����from����)<BR>
     * <BR>
     * �w������̕ێ�����w�茈�Ϗ�Ԍ���(is������)�̖����R�[�h<BR>
     * �Ɩ������̈ꗗ���擾����B<BR>
     * (�敨�^�I�v�V�����敪��null�̏ꍇ�́A�敨�^�I�v�V���������̈ꗗ<BR>
     * ���擾)<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * (1)���ʈꗗ�擾���̃\�[�g�L�[�̍쐬<BR>
     * �\�[�g�L�[ = "����ID ASC"<BR>
     * �\�[�g�L�[���p�����[�^��DefaultSortKeySpec���쐬<BR>
     * <BR>
     * (2)���ʈꗗ���擾<BR>
     * getConstracts(�p�����[�^.�⏕�����ADefaultSortKeySpec�A<BR>
     * 6(�敨�I�v�V�����̖����^�C�v)) <BR>
     * <BR>
     * <BR>
     * (3)TreeMap���쐬<BR>
     * <BR>
     * (4)���ʂ̗v�f����Loop����<BR>
     * ��getContracts()�̕Ԃ�l��NULL�A�܂��́A�Ԃ�l�̃��X�g�̗v�f����0�̏ꍇ��NULL��ԋp����<BR>
     * �@@(4-1)�p�����[�^.�敨�^�I�v�V�����敪 == null�̏ꍇ�@@�܂���<BR>
     * �@@�@@�@@�@@is�Ώی���(����[�C���f�b�N�X�ԍ�], 
     * �p�����[�^.�敨�^�I�v�V�����敪)��<BR>
     * �@@�@@�@@�@@true�̏ꍇ�́A(4-2)�ȍ~�̏������s��<BR>
     * <BR>
     * �@@(4-2)�����ό��ʂ̂ݑΏ�(is������==true)�̏ꍇ<BR>
     * �@@�@@�@@get���Ϗ��(����)�̕Ԃ�l��"�V�K�������"�܂���"�O���ȑO���ύ�"�܂���<BR>
     * "�������ύ�"�ȊO�̏ꍇ�A(4-4)���s���B<BR>
     * <BR>
     * �@@(4-3)�����ό��ʈȊO���Ώ�(is������==false)�̏ꍇ<BR>
     * �@@�@@�@@get���Ϗ��(����)�̕Ԃ�l��"�V�K�������"�܂���"�O���ȑO���ύ�"<BR>
     * �ȊO�̏ꍇ�A(4-4)���s���B<BR>
     * <BR>
     * �@@(4-4)�����R�[�h����і������̎擾<BR>
     * �@@�@@����ID = ����.get����ID()<BR>
     * �@@�@@�敨OP���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID)<BR>
     * �@@�@@�����R�[�h = �敨OP����.get�����R�[�h()<BR>
     * �@@�@@������ = �敨OP����.get������()<BR>
     * <BR>
     * �@@  �����w���敨�I�v�V���������R�[�h���̃I�u�W�F�N�g�𐶐�<BR>
     * <BR>
     * �@@�@@�����w���敨�I�v�V���������R�[�h����.�����R�[�h = �����R�[�h<BR>
     * �@@�@@�����w���敨�I�v�V���������R�[�h����.������ = ������<BR>
     * �@@�@@TreeMap.put(�����R�[�h�A�����w���敨�I�v�V���������R�[�h����)<BR>
     * <BR>
     * (5)TreeMap.values().toArray()�̌��ʂ�ԋp<BR>
     * �@@�@@��TreeMap�̃T�C�Y��0�̏ꍇ��NULL��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_blnIsUnSettlement - (is������)<BR>
     * <BR>
     * �Ώی��ʂ̔���B<BR>
     * �����ό��ʁA���ϒ����ʂ݂̂��Ώۂ̏ꍇtrue�A�����ό��ʁA<BR>
     * ���ϒ����ʂɉ����������ύό��ʂ��܂ޏꍇfalse�B<BR>
     * <BR>
     * @@param l_strFuturesOptionDivision - (�敨�^�I�v�V�����敪)<BR>
     * <BR>
     * �擾�Ώی��ʂ̐敨�^�I�v�V�����敪<BR>
     * 1�F �敨<BR>
     * 2�F �I�v�V����<BR>
     * ��null�̏ꍇ�͐敨�ƃI�v�V���������̃f�[�^��ԋp<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407B994000F9
     */
    public WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeNameFromContract(WEB3GentradeSubAccount l_subAccount, boolean l_blnIsUnSettlement, String l_strFuturesOptionDivision)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductCodeNameFromContract";
        log.entering(STR_METHOD_NAME);

        // (1)���ʈꗗ�擾���̃\�[�g�L�[�̍쐬
        // �\�[�g�L�[ = "����ID ASC"
        DefaultSortKeySpec l_sortKeySpec = new DefaultSortKeySpec("product_id ASC");

        // (2)���ʈꗗ���擾
        String[] l_strProductType = { Integer.toString(ProductTypeEnum.IFO.intValue())};
        FilterQueryParamsSpec l_filterQueryParamsSpec = new FilterQueryParamsSpec("product_type=?", l_strProductType);
        List l_lisContracts = getContracts(l_subAccount, l_filterQueryParamsSpec, l_sortKeySpec);

        // (3)TreeMap���쐬
        TreeMap l_tmUnSettlementContract = new TreeMap();

        // (4)���ʂ̗v�f����Loop����
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {
            //getContracts()�̕Ԃ�l��NULL�A�܂��͕Ԃ�l�̃��X�g�̗v�f����0�̏ꍇ��NULL��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        for (int i = 0; i < l_lisContracts.size(); i++)
        {
            WEB3IfoContractImpl l_ifocontractImpl = (WEB3IfoContractImpl) l_lisContracts.get(i);

            // temp flag
            boolean l_blnFlag = false;

            // is�Ώی���(����[�C���f�b�N�X�ԍ�], �p�����[�^.�敨�^�I�v�V�����敪)
            boolean l_blnIsObjectContract = isObjectContract(l_ifocontractImpl, l_strFuturesOptionDivision);

            int l_intSettlementStatus = getSettlementStatus(l_ifocontractImpl);

            // (4-1)�p�����[�^.�敨�^�I�v�V�����敪 == null�̏ꍇ �܂���
            //      l_blnIsObjectContract��true�̏ꍇ
            if (l_strFuturesOptionDivision == null || l_blnIsObjectContract)
            {
                if (l_blnIsUnSettlement)
                {
                    // (4-2)�����ό��ʂ̂ݑΏ�(is������==true)�̏ꍇ
                    //  get���Ϗ��(����)�̕Ԃ�l���V�K��������A�O���ȑO���ύρA
                    //  �܂���"�������ύ�"�ȊO�̏ꍇ�A(4-4)���s��
                    if (l_intSettlementStatus != WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE
                            && l_intSettlementStatus != WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED 
                            && l_intSettlementStatus != WEB3IfoSettlementStatusDef.SETTLED)
                    {
                        log.debug("�����ό��ʂ̂ݑΏہF get���Ϗ��(����)�̕Ԃ�l��" +
                            "�u�V�K��������v�A�u�O���ȑO���ύρv�A�u�������ύψȊO�̏ꍇ�v");

                        l_blnFlag = true;
                    }
                }
                else
                {
                    // (4-3)�����ό��ʈȊO���Ώ�(is������==false)�̏ꍇ<BR>
                    // get���Ϗ��(����)�̕Ԃ�l���V�K��������A�O���ȑO���ύψȊO�̏ꍇ
                    if (l_intSettlementStatus !=WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE 
                            && l_intSettlementStatus != WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED)
                    {
                        log.debug("�����ό��ʈȊO���ΏہF get���Ϗ��(����)�̕Ԃ�l��" +
                            "�u�V�K��������v�A�u�O���ȑO���ύψȊO�̏ꍇ�v");

                        l_blnFlag = true;
                    }
                }

            }
            // (4-4)�����R�[�h����і������̎擾
            if (l_blnFlag)
            {
                try
                {
                    // ����ID = ����.get����ID()
                    IfoContractParams l_ifoContractParams = new IfoContractParams((IfoContractRow) l_ifocontractImpl.getDataSourceObject());

                    long l_lngProductId = l_ifoContractParams.getProductId();

                    // �敨OP���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID)
                    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                    TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                    WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
                    Product l_product = l_productManager.getProduct(l_lngProductId);
                    // �����R�[�h = �敨OP����.get�����R�[�h()
                    IfoProductParams l_porductParams = new IfoProductParams((IfoProductRow) l_product.getDataSourceObject());
                    String l_strProductCode = l_porductParams.getProductCode();
                    // ������ = �敨OP����.get������()
                    String l_strStandName = l_porductParams.getStandardName();
           
                    //�L�[����(�����R�[�h)�����X�g�ɒǉ��ςłȂ��ꍇ�̂݁A
                    //�����R�[�h���̃N���X�𐶐����A�����R�[�h�Ɩ��������Z�b�g���A�ǉ�����
                    if (!l_tmUnSettlementContract.containsKey(l_strProductCode))
                    {
                        WEB3FuturesOptionsProductCodeNameUnit l_ProductCodeNameUnit = new WEB3FuturesOptionsProductCodeNameUnit();
                        l_ProductCodeNameUnit.productCode = l_strProductCode;
                        l_ProductCodeNameUnit.productName = l_strStandName;
                        
                        // TreeMap.put(�����R�[�h�A�����w���敨�I�v�V���������R�[�h����)
                        l_tmUnSettlementContract.put(l_strProductCode, l_ProductCodeNameUnit);
                    }
                }
                catch (NotFoundException l_ex)
                {
                    log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
                }
            }
        }

        // (5)TreeMap.values().toArray()�̌��ʂ�ԋp
        int l_tmSize = l_tmUnSettlementContract.size();
        if (l_tmSize == 0)
        {
            //TreeMap�̃T�C�Y��0�̏ꍇ��NULL��ԋp
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3FuturesOptionsProductCodeNameUnit[] l_returnValues = new WEB3FuturesOptionsProductCodeNameUnit[l_tmSize];      
        l_tmUnSettlementContract.values().toArray(l_returnValues);

        log.exiting(STR_METHOD_NAME);
        return l_returnValues;
    }

    /**
     * (createOP���ʏƉ��)<BR>
     * <BR>
     * �����w���I�v�V�������ʏƉ��ʂɕ\�����錚�ʏƉ�ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * �u�i�敨OP�c���jcreateOP���ʏƉ�ׁv�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_strFutureOptionDivision - (�敨�^�I�v�V�����敪)<BR>
     * <BR>
     * 1�F�敨<BR>
     * 2�F�I�v�V����<BR>
     * @@param l_strDesSettlementStatus - (�w�茈�Ϗ��)<BR>
     * <BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * null�F�w��Ȃ� <BR>
     * 0�F���ύ�<BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     * @@param l_strSearchConditionString - ��������������<BR>
     * @@param l_strSearchConditionDataContainer - (���������f�[�^�R���e�i)<BR>
     * <BR>
     * ��������<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceUnit[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4075477303CC
     */
    public WEB3OptionsContractReferenceUnit[] createOptionsContractReferenceUnits(
        WEB3GentradeSubAccount l_subAccount,
        String l_strFuturesOptionDivision,
        String l_strDesSettlementStatus,
        String l_strSearchCondition,
        String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionsContractReferenceUnits";
        log.entering(STR_METHOD_NAME);

        // 1.  get���ʈꗗ(�⏕����, ProductTypeEnum, String, String[])
        List l_lisContracts = getContracts(l_subAccount, ProductTypeEnum.IFO, l_strSearchCondition, l_strSearchConditionDataContainer);

        // 2.�߂�l����
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {

            // 3. ���ʂ̗v�f����0�̏ꍇnull��ԋp
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // 4.���ʏƉ�ׂ��i�[���錚�ʏƉ�׃��X�g�𐶐�����B
        ArrayList l_lisContractReferenceUnits = new ArrayList();

        for (int i = 0; i < l_lisContracts.size(); i++)
        {
            try
            {
                IfoContractRow l_ifoContractRow = (IfoContractRow) l_lisContracts.get(i);
                WEB3IfoContractImpl l_ifocontractImpl = new WEB3IfoContractImpl(l_ifoContractRow);

                log.debug("(WEB3IfoContractImpl)l_lisContracts.get(" + i + ") = " + l_ifocontractImpl);

                // 5.is�Ώی���(�敨OP����, String)
                boolean l_blnFlag = isObjectContract(l_ifocontractImpl, l_strFuturesOptionDivision);
                if (!l_blnFlag)
                {

                    log.debug("is�Ώی���(�敨OP����, String)��false�̏ꍇ continue");
                    continue;
                }
                // 6.����ID���擾����B
                long l_lngProductId = l_ifoContractRow.getProductId();

                // 7.�敨OP���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
                WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);

                // 8.�����R�[�h = �敨OP����.get�����Y�����R�[�h()   
                String l_strProductCode = l_product.getUnderlyingProductCode();

                // 9.reset�����R�[�h(String)
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);
                // 10.get���Ϗ��(�敨OP����)
                int l_intSettlementStatus = getSettlementStatus(l_ifocontractImpl);

                switch (l_intSettlementStatus)
                {
                    // 11."�r�ύ�"�̏ꍇ�̂�
                    case WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED :
                    case WEB3IfoSettlementStatusDef.SETTLED :
                        log.debug("createOP���ύό��ʏƉ��( ArrayList, �敨OP����)");

                        // 12.createOP���ύό��ʏƉ��(ArrayList, �敨OP����)
                        createOptionSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;
                        // 13."���r��"�̏ꍇ�̂�
                    case WEB3IfoSettlementStatusDef.UNSETTLED :
                        log.debug("createOP�����ό��ʏƉ��(ArrayList,  �敨OP����");

                        // 14.createOP�����ό��ʏƉ��(ArrayList, �敨OP����)
                        createOptionUnSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;
                        // 15."�r�ϒ�"�̏ꍇ�̂�
                    case WEB3IfoSettlementStatusDef.SETTLING :
                        // --- Test Log
                        log.debug("createOP���ϒ����ʏƉ��(ArrayList, �敨OP����");

                        // 16.createOP���ϒ����ʏƉ��(ArrayList, �敨OP����)
                        createOptionSettlingContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;
                        // get���Ϗ�Ԃ̕Ԃ�l��
                        // ("�����ςƌ��ϒ�"�A"���ύςƖ�����"�A
                        //  "���ύςƖ����ςƌ��ϒ�"�A"���ύςƌ��ϒ�"�j
                    case WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING:
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED:
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING:
                    case WEB3IfoSettlementStatusDef.SETTLED_SETTLING:

                        // 18.createOP�������ʏƉ��(ArrayList,  �敨OP����, int)
                        createMultiOptionContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl, l_intSettlementStatus);
                        break;
                }

            }
            catch (NotFoundException l_ex)
            {
                log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);

            }
        }
        // 19.�w�茈�Ϗ�Ԃ�����ꍇ
        if (l_strDesSettlementStatus != null)
        {

            // 20.get�w�茈�Ϗ�Ԍ��ʖ���(String, ArrayList)
            getSpecSettlementStatusContractDetails(l_strDesSettlementStatus, l_lisContractReferenceUnits);


            // 21.toArray( ) and returns
            WEB3OptionsContractReferenceUnit[] l_lisReturn = new WEB3OptionsContractReferenceUnit[l_lisContractReferenceUnits.size()];
            l_lisContractReferenceUnits.toArray(l_lisReturn);

            log.exiting(STR_METHOD_NAME);
            return l_lisReturn;
        }

        // 22.toArray( ) and returns
        WEB3OptionsContractReferenceUnit[] l_lisReturn = new WEB3OptionsContractReferenceUnit[l_lisContractReferenceUnits.size()];
        l_lisContractReferenceUnits.toArray(l_lisReturn);

        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
    }

    /**
     * (createOP���ύό��ʏƉ��)<BR>
     * <BR>
     * �������ύς�1���ׂ��쐬����B�i�I�v�V�����j<BR>
     * <BR>
     * �u�i�敨OP�c���jcreateOP���ύό��ʏƉ�ׁv�Q�ƁB<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DF31202B7
     */
    public void createOptionSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionSettledContractInquiryDetails";
        log.entering(STR_METHOD_NAME);

        // 1.�����w���I�v�V�������ʏƉ�ׂ𐶐�����B
        WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();

        // 2.getContractId( ) --- ����ID���擾����
        long l_lngContractId = l_ifoContract.getContractId();
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // 3.get�����ԍϖ�萔��(long, Date)
        double l_dblOrderQuantity = getDayClosingContractExecutionCnt(l_lngContractId, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        log.debug("l_lngContractId = " + l_lngContractId);
        log.debug("l_dblOrderQuantity = " + l_dblOrderQuantity);

        // 4.get�g�����U�N�V����(long, EQTYPE_IDX_OPTIONS_CLOSE , Date)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager = (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();

        FinTransactionCateg l_transactionCategory = FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE;

        List l_lisTransactions =
            l_finTransactionManager.getTransactions(l_lngContractId, l_transactionCategory, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        BigDecimal l_bdNetAmountTotal = new BigDecimal("0");
        BigDecimal l_bdSetupFeeTotal = new BigDecimal("0");
        for (int i = 0; i < l_lisTransactions.size(); i++)
        {
            IfoFinTransactionRow l_transactionRow = (IfoFinTransactionRow) l_lisTransactions.get(i);

            BigDecimal l_bdNetAmount = new BigDecimal(l_transactionRow.getNetAmount() + "");
            BigDecimal l_bdImportedSetupFee = new BigDecimal(l_transactionRow.getImportedSetupFee() + "");
            BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(l_transactionRow.getImportedSetupFeeTax() + "");

            // 5. getNetAmount()
            l_bdNetAmountTotal = l_bdNetAmountTotal.add(l_bdNetAmount);
            // 6.getImportedSetupFee()
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFee);
            // 7.getImportedSetupFeeTax()
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFeeTax);
        }

        try
        {
            // ����ID���擾����B
            long l_lngProductId = l_ifoContractParams.getProductId();
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            // �敨OP����������擾����
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductParams.getTargetMarketId());

            // 8.�v���p�e�B�Z�b�g
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            l_contractReferenceUnit.opProductCode = l_porductParams.getProductCode();
            l_contractReferenceUnit.opProductName = l_porductParams.getStandardName();
            l_contractReferenceUnit.targetProductCode = l_porductParams.getUnderlyingProductCode();
            l_contractReferenceUnit.delivaryMonth = l_porductParams.getMonthOfDelivery();

            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }


            l_contractReferenceUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_porductParams.getStrikePrice());

            // get MarketCode
            long l_lngMardetId = l_porductParams.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            
            l_contractReferenceUnit.contractType = String.valueOf(l_ifoContractParams.getContractType().intValue());

            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());

            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);

            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());

            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLEMENT_END;

            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblOrderQuantity));

            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_bdSetupFeeTotal.doubleValue());

            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());

            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_bdNetAmountTotal.doubleValue());
            l_contractReferenceUnit.sessionType =
                ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

            // 9.add(arg0 : Object)
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            try
            {
                //���O�`�F�b�N
                Long.parseLong(l_contractReferenceUnit.contractCommission);
            }
            catch (NumberFormatException l_nfe)
            {
                StringBuffer l_sb = new StringBuffer(STR_METHOD_NAME);
                l_sb.append(" contract=" + l_ifoContract.getDataSourceObject());
                l_sb.append(", transactions=" + l_lisTransactions);
                l_sb.append(", setupFeeTotal=" + l_bdSetupFeeTotal);
                log.error(l_sb.toString(), l_nfe);
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (createOP�����ό��ʏƉ��)<BR>
     * <BR>
     * �����ς�1���ׂ��쐬����B�i�I�v�V�����j<BR>
     * <BR>
     * �u�i�敨OP�c���jcreateOP�����ό��ʏƉ�ׁv�Q�ƁB<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DF356019E
     */
    public void createOptionUnSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionUnSettledContractInquiryDetails";
        log.entering(STR_METHOD_NAME);

        // �����w���I�v�V�������ʏƉ�ׂ𐶐�����B
        WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();
        
        // ���ϒ����ʂ��擾����B
        double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();

        // �����ϐ��ʂ��擾����B
        BigDecimal l_bdQuantity = new BigDecimal(l_ifoContract.getQuantity() + "");
        BigDecimal l_bdLockedQuantity = new BigDecimal(l_dblLockedQuantity + "");
        double l_dblQuantity = l_bdQuantity.subtract(l_bdLockedQuantity).doubleValue();
        
        // �������擾����B
        double l_dblContractcurrentPrice = getContractCurrentPrice(l_ifoContract);

        // �]�����v���擾����B
        double l_dblEvaluateIncome = l_ifoContract.getEvaluateIncome(l_dblContractcurrentPrice, l_dblQuantity);
       
        // ���ϒ��̌��萔�����擾����B
        double l_dblLockedContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);

        // ���ϒ��̌��萔������ł��擾����B
        double l_dblLockedContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
            
        // ���萔����(�����ρ{���ϒ��̌��萔��)�|���ϒ��̌��萔��
        BigDecimal l_bdSetupFee = new BigDecimal(l_ifoContract.getSetupFee() + "");
        BigDecimal l_bdLockedContractCommission = new BigDecimal(l_dblLockedContractCommission + "");

        BigDecimal l_bdContractCommission = l_bdSetupFee.subtract(l_bdLockedContractCommission);

        // ���萔������Ł�(�����ρ{���ϒ��̌��萔�������)�|���ϒ��̌��萔�������
        BigDecimal l_bdSetupFeeTax = new BigDecimal(l_ifoContract.getSetupFeeTax() + "");
        BigDecimal l_bdLockedContractCommissionTax = new BigDecimal(l_dblLockedContractCommissionTax + "");
        
        BigDecimal l_bdContractCommissionTax = l_bdSetupFeeTax.subtract(l_bdLockedContractCommissionTax);
        
        // ���萔���{���萔�������
        BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
        
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();
        try
        {
            // ����ID���擾����B
            long l_lngProductId = l_ifoContractParams.getProductId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            // �敨OP����������擾����
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductParams.getTargetMarketId());

            // 8.�v���p�e�B�Z�b�g
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            l_contractReferenceUnit.opProductCode = l_porductParams.getProductCode();
            l_contractReferenceUnit.opProductName = l_porductParams.getStandardName();
            l_contractReferenceUnit.targetProductCode = l_porductParams.getUnderlyingProductCode();
            l_contractReferenceUnit.delivaryMonth = l_porductParams.getMonthOfDelivery();


            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }

            l_contractReferenceUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_porductParams.getStrikePrice());

            // get MarketCode
            long l_lngMardetId = l_porductParams.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            l_contractReferenceUnit.contractType = String.valueOf(l_ifoContractParams.getContractType().intValue());

            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());

            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());

            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED;

            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblQuantity));

            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_bdCost.doubleValue());

            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());

            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            
            BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
            l_contractReferenceUnit.incomeCost =
                WEB3StringTypeUtility.formatNumber((l_bdEvaluateIncome.subtract(l_bdCost)).doubleValue());

            l_contractReferenceUnit.sessionType =
                ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            try
            {
                //���O�`�F�b�N
                Long.parseLong(l_contractReferenceUnit.contractCommission);
            }
            catch (NumberFormatException l_nfe)
            {
                StringBuffer l_sb = new StringBuffer(STR_METHOD_NAME);
                l_sb.append(" contract=" + l_ifoContract.getDataSourceObject());
                l_sb.append(", lockedQuantity=" + l_dblLockedQuantity);
                l_sb.append(", lockedContractCommission=" + l_dblLockedContractCommission);
                l_sb.append(", lockedContractCommissionTax=" + l_dblLockedContractCommissionTax);
                l_sb.append(", contractCommission=" + l_bdContractCommission);
                l_sb.append(", contractCommissionTax=" + l_bdContractCommissionTax);
                l_sb.append(", cost=" + l_bdCost);
                log.error(l_sb.toString(), l_nfe);
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (createOP���ϒ����ʏƉ��)<BR>
     * <BR>
     * ���ϒ���1���ׂ��쐬����B�i�I�v�V�����j<BR>
     * <BR>
     * �u�i�敨OP�c���jcreateOP���ϒ����ʏƉ�ׁv�Q�ƁB<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DF37101BD
     */
    public void createOptionSettlingContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOptionSettlingContractInquiryDetails";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

            // 1.�����w���I�v�V�������ʏƉ�ׂ𐶐�����B
            WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();

            // 2.���ϒ��������ʂ��擾����B
            double l_dblLockedQuality = l_ifoContract.getLockedQuantity();

            // 3.����ID���擾����
            long l_lngContractId = l_ifoContract.getContractId();

            // 4.get�ԍώw����(����ID)
            IfoClosingContractSpec[] l_lisContractSpecs = this.getClosingContractSpecs(l_lngContractId);

            IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();
            BigDecimal l_bdEvaluateIncome = new BigDecimal("0");

            // 5.�������擾����B
            double l_dblContractcurrentPrice = this.getContractCurrentPrice(l_ifoContract);

            if (l_lisContractSpecs != null)
            {
                for (int i = 0; i < l_lisContractSpecs.length; i++)
                {
                    // 6.�s��m�F�ϕԍϐ��ʂ��擾����
                    //�s��m�F�ϕԍϐ��� == null�̏ꍇ�A�������ʂƂ��ĕԍϒ������ʂ��g�p����
                    IfoClosingContractSpecRow l_specRow =
                        (IfoClosingContractSpecRow) l_lisContractSpecs[i].getDataSourceObject();
                    double l_dblQuantity = 0D;
                    if (!l_specRow.getConfirmedQuantityIsNull())
                    {
                        //�������ʁF���ʕԍώw����.�s��m�F�ϕԍϐ���
                        l_dblQuantity = l_specRow.getConfirmedQuantity();
                    }
                    else
                    {
                        //�������ʁF���ʕԍώw����.�ԍϒ�������
                        l_dblQuantity = l_specRow.getQuantity();
                    }

                    //�ԍϖ�萔�ʂ��擾����B
                    double l_dblExecutedQuantity = l_lisContractSpecs[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0D;
                    }

                    //�����P��ID���擾����B
                    long l_lngOrderUintId = l_lisContractSpecs[i].getOrderUnitId();

                    // �����P�ʂ��擾����B
                    IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUintId);

                    //�����L����Ԃ��擾����B
                    OrderOpenStatusEnum l_statusEnum = l_orderUnit.getOrderOpenStatus();

                    //�ΏۊO�����`�F�b�N
                    //�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�́A�ΏۊO�����Ƃ��Ď��̗v�f�ɏ������ڍs����B
                    //�E�敨OP�ԍώw����.getConfirmedQuantity() == 0
                    //�E�����P��.getOrderOpenStatusEnum == "�N���[�Y"
                    if (l_dblQuantity == 0 || OrderOpenStatusEnum.CLOSED.equals(l_statusEnum))
                    {
                        continue;
                    }

                    BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity + "");
                    BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity + "");
                    double l_dblSettlementCnt = l_bdQuantity.subtract(l_bdExecutedQuantity).doubleValue();
                    // �]�����v���擾����B
                    double l_dblIncome = l_ifoContract.getEvaluateIncome(
                        l_dblContractcurrentPrice,
                        l_dblSettlementCnt);

                    BigDecimal l_bdIncome = new BigDecimal(l_dblIncome + "");

                    l_bdEvaluateIncome = l_bdEvaluateIncome.add(l_bdIncome);
                }
            }

            // 10.get���萔��(double)
            double l_dblContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommission))
            {
                l_dblContractCommission = 0D;
            }

            // 11.get���萔�������(double)
            double l_dblContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommissionTax))
            {
                l_dblContractCommissionTax = 0D;
            }

            //getSessionType( )
            IfoContractRow l_ifoContractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
            String l_strSessionType = l_ifoContractRow.getSessionType();

            // ���萔���{���萔�������
            BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
            BigDecimal l_bdContractCommissionTax = new BigDecimal(l_dblContractCommissionTax + "");
            BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);

            // ����ID���擾����B
            long l_lngProductId = l_ifoContractParams.getProductId();

            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            // �敨OP����������擾����
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductParams.getTargetMarketId());

            // �v���p�e�B�Z�b�g
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            l_contractReferenceUnit.opProductCode = l_porductParams.getProductCode();
            l_contractReferenceUnit.opProductName = l_porductParams.getStandardName();
            l_contractReferenceUnit.targetProductCode = l_porductParams.getUnderlyingProductCode();
            l_contractReferenceUnit.delivaryMonth = l_porductParams.getMonthOfDelivery();
            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_porductParams.getDerivativeType()))
            {
                l_contractReferenceUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }

            l_contractReferenceUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_porductParams.getStrikePrice());

            // get MarketCode
            long l_lngMardetId = l_porductParams.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            
            l_contractReferenceUnit.contractType = String.valueOf(l_ifoContractParams.getContractType().intValue());

            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());

            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuality);

            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());

            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLING;

            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblLockedQuality));

            l_contractReferenceUnit.contractCommission =
                WEB3StringTypeUtility.formatNumber(l_bdCost.doubleValue());

            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());

            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_bdEvaluateIncome.doubleValue());
            
            l_contractReferenceUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                (l_bdEvaluateIncome.subtract(l_bdCost)).doubleValue());

            l_contractReferenceUnit.sessionType = l_strSessionType;

            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            try
            {
                //���O�`�F�b�N
                Long.parseLong(l_contractReferenceUnit.contractCommission);
            }
            catch (NumberFormatException l_nfe)
            {
                StringBuffer l_sb = new StringBuffer(STR_METHOD_NAME);
                l_sb.append(" contract=" + l_ifoContract.getDataSourceObject());
                l_sb.append(", lockedQuantity=" + l_dblLockedQuality);
                l_sb.append(", contractCommission=" + l_dblContractCommission);
                l_sb.append(", contractCommissionTax=" + l_dblContractCommissionTax);
                l_sb.append(", cost=" + l_bdCost);
                log.error(l_sb.toString(), l_nfe);
            }

            log.exiting(STR_METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (createOP�������ʏƉ��)<BR>
     * <BR>
     * 1�̌��ʂŕ����̌��ʏƉ�ׂ��쐬����ꍇ�̏������s���B�i�I�v�V�����j<BR>
     * <BR>
     * �����̌��Ϗ�Ԃɂ��ƂÂ��A<BR>
     * createOP���ύό��ʏƉ��<BR>
     * createOP�����ό��ʏƉ��<BR>
     * createOP���ϒ����ʏƉ��<BR>
     * ���\�b�h�̂����ꂩ���R�[������B<BR>
     * <BR>
     * (1)���Ϗ�ԁF3�̏ꍇ<BR>
     *   �����ςƌ��ϒ���2���ׂ��쐬����B<BR>
     *  <BR>
     * �@@createOP�����ό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   createOP���ϒ����ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * <BR>
     * (2)���Ϗ�ԁF4�̏ꍇ<BR>
     *   ���ύςƖ����ς�2���ׂ��쐬����B<BR>
     * <BR>
     *   createOP���ύό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   createOP�����ό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * <BR>
     * (3)���Ϗ�ԁF5�̏ꍇ<BR>
     *   ���ύςƖ����ςƌ��ϒ���3���ׂ��쐬����B<BR>
     * <BR>
     *   createOP���ύό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   createOP�����ό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   createOP���ϒ����ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * <BR>
     * (4)���Ϗ�ԁF6�̏ꍇ<BR>
     *   ���ύςƌ��ϒ���2���ׂ��쐬����B<BR>
     *   create�敨���ύό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   create�敨���ϒ����ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * 
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@param l_intSettlementStatus - (���Ϗ��)<BR>
     * <BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * 3 �F�����ςƌ��ϒ�<BR> 
     * 4 �F���ύςƖ����� <BR>
     * 5 �F���ύςƖ����ςƌ��ϒ�<BR> 
     * 6 �F���ύςƌ��ϒ�<BR> 
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4075477303E3
     */
    public void createMultiOptionContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract, int l_intSettlementStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createMultiOptionContractInquiryDetails";
        log.entering(STR_METHOD_NAME);

        // �����̌��Ϗ�Ԃɂ��ƂÂ�
        // createOP���ύό��ʏƉ��
        // createOP�����ό��ʏƉ��
        // createOP���ϒ����ʏƉ��
        // ���\�b�h�̂����ꂩ���R�[������B
        switch (l_intSettlementStatus)
        {
            case 3 :
                // (1)���Ϗ�ԁF3�̏ꍇ
                // �����ςƌ��ϒ���2���ׂ��쐬����B

                // createOP�����ό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP���ϒ����ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;
            case 4 :
                // (2)���Ϗ�ԁF4�̏ꍇ
                // ���ύςƖ����ς�2���ׂ��쐬����B


                // createOP���ύό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP�����ό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);

                break;
            case 5 :
                // (3)���Ϗ�ԁF5�̏ꍇ
                // ���ύςƖ����ςƌ��ϒ���3���ׂ��쐬����B


                // createOP���ύό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP�����ό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP���ϒ����ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            case 6 :
                // (4)���Ϗ�ԁF6�̏ꍇ
                //���ύςƌ��ϒ���2���ׂ��쐬����

                // createOP���ύό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // createOP���ϒ����ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createOptionSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            default :
                log.error("Error In Method: " + STR_METHOD_NAME);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�敨���ʏƉ��)<BR>
     * <BR>
     * �����w���敨���ʏƉ��ʂɕ\�����錚�ʏƉ�ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * �u�i�敨OP�c���jcreate�敨���ʏƉ�ׁv�Q�ƁB<BR>
     * @@param l_subAccount - �⏕����
     * @@param l_strFutureOptionDivision - (�敨�^�I�v�V�����敪)<BR>
     * <BR>
     * 1�F�敨<BR>
     * 2�F�I�v�V����<BR>
     * @@param l_strDesSettlementStatus - (�w�茈�Ϗ��)<BR>
     * <BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * null�F�w��Ȃ� <BR>
     * 0�F���ύ�<BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     * 
     * @@param l_strSearchConditionString - ��������������
     * @@param l_strSearchConditionDataContainer - (���������f�[�^�R���e�i)<BR>
     * <BR>
     * ��������<BR>
     * @@return �����w���敨���ʏƉ��[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FA3502CB
     */

    public WEB3FuturesContractReferenceUnit[] createFuturesContractInquiryDetails(
        WEB3GentradeSubAccount l_subAccount,
        String l_strFuturesOptionDivision,
        String l_strDesSettlementStatus,
        String l_strSearchConditionString,
        String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesContractInquiryDetails()";
        log.entering(METHOD_NAME);


        // 1.  get���ʈꗗ(�⏕����, ProductTypeEnum, String, String[])
        List l_lisContracts = getContracts(l_subAccount, ProductTypeEnum.IFO, l_strSearchConditionString, l_strSearchConditionDataContainer);

        // 2.�߂�l����
        if (l_lisContracts == null || l_lisContracts.size() == 0)
        {

            // 3. return null
            log.exiting(METHOD_NAME);
            return null;
        }

        // 4.���ʏƉ�ׂ��i�[���錚�ʏƉ�׃��X�g�𐶐�����B
        ArrayList l_lisContractReferenceUnits = new ArrayList();

        for (int i = 0; i < l_lisContracts.size(); i++)
        {
            try
            {
                IfoContractParams l_ifocontractParams = (IfoContractParams) l_lisContracts.get(i);
                WEB3IfoContractImpl l_ifocontractImpl = new WEB3IfoContractImpl(l_ifocontractParams);

                // 5.is�Ώی���(�敨OP����, String)
                boolean l_blnFlag = isObjectContract(l_ifocontractImpl, l_strFuturesOptionDivision);

                if (!l_blnFlag)
                {
                    //is�Ώی��ʂ̕Ԃ�l��false�̏ꍇ�́A�ȍ~��Loop�������͎��{���Ȃ�
                    continue;
                }

                // 6.����ID���擾����B
                long l_lngProductId = l_ifocontractParams.getProductId();

                // 7.�敨OP���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID)
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
                WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);

                // 8.�����R�[�h = �敨OP����.get�����Y�����R�[�h()                           
                String l_strProductCode = l_product.getUnderlyingProductCode();

                // 9.reset�����R�[�h(String)
                WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

                // 10.get���Ϗ��(�敨OP����)
                int l_intSettlementStatus = getSettlementStatus(l_ifocontractImpl);

                switch (l_intSettlementStatus)
                {
                    // 11 "�r�ύ�"�̏ꍇ�̂�
                    case WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED :
                    case WEB3IfoSettlementStatusDef.SETTLED :

                        log.debug("create�敨���ύό��ʏƉ��( ArrayList, �敨OP����)");

                        // 11.1 create�敨���ύό��ʏƉ��(ArrayList, �敨OP����)
                        createFuturesSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;

                        // 12 "���r��"�̏ꍇ�̂�
                    case WEB3IfoSettlementStatusDef.UNSETTLED:

                        log.debug("create�敨�����ό��ʏƉ��(ArrayList,  �敨OP����");

                        // 12.1 create�敨�����ό��ʏƉ��(ArrayList, �敨OP����)
                        createFuturesUnSettledContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;

                        // 13 "�r�ϒ�"�̏ꍇ�̂�
                    case WEB3IfoSettlementStatusDef.SETTLING :

                        log.debug("create�敨���ϒ����ʏƉ��(ArrayList, �敨OP����");

                        // 13.1 create�敨���ϒ����ʏƉ��(ArrayList, �敨OP����)
                        createFuturesSettlingContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl);
                        break;

                        // 14 get���Ϗ�Ԃ̕Ԃ�l��
                        //�i"�����ςƌ��ϒ�"�A"���ύςƖ�����"�A"���ύςƖ����ςƌ��ϒ�"�A"���ύςƌ��ϒ�"�j
                    case WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING :
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED :
                    case WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING :
                    case WEB3IfoSettlementStatusDef.SETTLED_SETTLING :

                        log.debug("create�敨�������ʏƉ��(ArrayList, �敨OP����, int");

                        // 14.1 create�敨�������ʏƉ��(ArrayList,  �敨OP����, int)
                        createMultiFuturesContractInquiryDetails(l_lisContractReferenceUnits, l_ifocontractImpl, l_intSettlementStatus);
                        break;

                }

            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);

            }
        }

        // 15.���Ϗ�ԂɎw�肪����ꍇ�i�w�茈�Ϗ�� != null�j
        // �w�茈�Ϗ�Ԃ̗v�f�݂̂��擾����B
        if (l_strDesSettlementStatus != null)
        {

            //16.get�w�茈�Ϗ�Ԍ��ʖ���(String, ArrayList)

            getSpecSettlementStatusContractDetails(l_strDesSettlementStatus, l_lisContractReferenceUnits);

            //17.toArray( ) and returns
            WEB3FuturesContractReferenceUnit[] l_lisReturn = new WEB3FuturesContractReferenceUnit[l_lisContractReferenceUnits.size()];
            l_lisContractReferenceUnits.toArray(l_lisReturn);

            log.exiting(METHOD_NAME);
            return l_lisReturn;
        }

        // 18.toArray( ) and returns
        WEB3FuturesContractReferenceUnit[] l_lisReturn = new WEB3FuturesContractReferenceUnit[l_lisContractReferenceUnits.size()];
        l_lisContractReferenceUnits.toArray(l_lisReturn);

        log.exiting(METHOD_NAME);
        return l_lisReturn;
    }

    /**
     * (create�敨���ύό��ʏƉ��)<BR>
     * <BR>
     * �������ύς�1���ׂ��쐬����B�i�敨�j<BR>
     * <BR>
     * �u�i�敨OP�c���jcreate�敨���ύό��ʏƉ�ׁv�Q�ƁB<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502CB
     */

    public void createFuturesSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesSettledContractInquiryDetails(ArrayList, WEB3IfoContractImpl)";
        log.entering(METHOD_NAME);

        // 1.�����w���敨���ʏƉ�ׂ𐶐�����B
        WEB3FuturesContractReferenceUnit l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit();

        // 2.getContractId( ) --- ����ID���擾����
        long l_lngContractId = l_ifoContract.getContractId();
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // 3.get�����ԍϖ�萔��(long, Date)      
        double l_dblOrderQuantity = getDayClosingContractExecutionCnt(l_lngContractId, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        // 4.get�g�����U�N�V����(long, "�ԍώ��" , Date)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoFinTransactionManagerImpl l_finTransactionManager = (WEB3IfoFinTransactionManagerImpl) l_tradingModule.getFinTransactionManager();

        FinTransactionCateg l_transactionCategory = FinTransactionCateg.EQTYPE_IDX_FUTURES_CLOSE;

        List l_lisTransactions =
            l_finTransactionManager.getTransactions(l_lngContractId, l_transactionCategory, (Date) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        double l_dblNetAmountTotal = 0;
        BigDecimal l_bdNetAmountTotal = new BigDecimal("0");
        double l_dblSetupFeeTotal = 0;
        BigDecimal l_bdSetupFeeTotal = new BigDecimal("0");
        for (int i = 0; i < l_lisTransactions.size(); i++)
        {
            IfoFinTransactionRow l_transactionRow = (IfoFinTransactionRow) l_lisTransactions.get(i);

            // 5. getNetAmount()
            BigDecimal l_bdNetAmount = new BigDecimal(l_transactionRow.getNetAmount() + "");
            l_bdNetAmountTotal = l_bdNetAmountTotal.add(l_bdNetAmount);
            l_dblNetAmountTotal = l_bdNetAmountTotal.doubleValue();
            // 6.getImportedSetupFee()
            BigDecimal l_bdImportedSetupFee = new BigDecimal(l_transactionRow.getImportedSetupFee() + "");
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFee);
            l_dblSetupFeeTotal = l_bdSetupFeeTotal.doubleValue();
            // 7.getImportedSetupFeeTax()
            BigDecimal l_bdImportedSetupFeeTax = new BigDecimal(l_transactionRow.getImportedSetupFeeTax() + "");
            l_bdSetupFeeTotal = l_bdSetupFeeTotal.add(l_bdImportedSetupFeeTax);
            l_dblSetupFeeTotal = l_bdSetupFeeTotal.doubleValue();
        }

        try
        {
            // ����ID���擾����B
            long l_lngProductId = l_ifoContractParams.getProductId();
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_porductRow = (IfoProductRow) l_product.getDataSourceObject();
            // �敨OP����������擾����
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductRow.getTargetMarketId());

            //ID
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            //�����R�[�h
            l_contractReferenceUnit.futProductCode = l_porductRow.getProductCode();
            //������
            l_contractReferenceUnit.futProductName = l_porductRow.getStandardName();
            //�w�����
            l_contractReferenceUnit.targetProductCode = l_porductRow.getUnderlyingProductCode();
            //����
            l_contractReferenceUnit.delivaryMonth = l_porductRow.getMonthOfDelivery();
            //����s��
            long l_lngMardetId = l_porductRow.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            //���敪
            l_contractReferenceUnit.contractType = "" + l_ifoContractParams.getContractType().intValue();
            //����      
            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());
            //������
            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
            //���P��
            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());
            //���Ϗ�ԋ敪
            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLEMENT_END;
            //�������z
            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblOrderQuantity));
            //���萔��
            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblSetupFeeTotal);
            //����ŏI��
            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());
            //���v
            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblNetAmountTotal);
            //����.����敪
            l_contractReferenceUnit.sessionType = l_ifoContractParams.getSessionType();

            // 9.add(arg0 : Object)
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            log.exiting(METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (create�敨�����ό��ʏƉ��)<BR>
     * <BR>
     * �����ς�1���ׂ��쐬����B�i�敨�j<BR>
     * <BR>
     * �u�i�敨OP�c���jcreate�敨�����ό��ʏƉ�ׁv�Q�ƁB<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502CE
     */

    public void createFuturesUnSettledContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesUnSettledContractInquiryDetails(ArrayList, WEB3IfoContractImpl)";
        log.entering(METHOD_NAME);

        // �����w���敨���ʏƉ�ׂ𐶐�����B
        WEB3FuturesContractReferenceUnit l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit();

        // ���ϒ����ʂ��擾����B
        double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();

        // �����ϐ��ʂ��擾����B
        double l_dblQuantity = l_ifoContract.getQuantity() - l_dblLockedQuantity;
        
        // �������擾����B
        double l_dblContractcurrentPrice = getContractCurrentPrice(l_ifoContract);

        // �]�����v���擾����B
        double l_dblEvaluateIncome = l_ifoContract.getEvaluateIncome(l_dblContractcurrentPrice, l_dblQuantity);
        BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
       
        // ���ϒ��̌��萔�����擾����B
        double l_dblLockedContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);
        BigDecimal l_bdLockedContractCommission = new BigDecimal(l_dblLockedContractCommission + "");

        // ���ϒ��̌��萔������ł��擾����B
        double l_dblLockedContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
        BigDecimal l_bdLockedContractCommissionTax = new BigDecimal(l_dblLockedContractCommissionTax + "");
            
        // ���萔����(�����ρ{���ϒ��̌��萔��)�|���ϒ��̌��萔��
        BigDecimal l_bdSetupFee = new BigDecimal(l_ifoContract.getSetupFee() + "");
        BigDecimal l_bdContractCommission = l_bdSetupFee.subtract(l_bdLockedContractCommission);

        // ���萔������Ł�(�����ρ{���ϒ��̌��萔�������)�|���ϒ��̌��萔�������
        BigDecimal l_bdSetupFeeTax = new BigDecimal(l_ifoContract.getSetupFeeTax() + "");
        BigDecimal l_bdContractCommissionTax = l_bdSetupFeeTax.subtract(l_bdLockedContractCommissionTax);
        
        // ���萔���{���萔�������
        BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
        double l_dblCost = l_bdCost.doubleValue();

        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();
        try
        {
            // ����ID���擾����B
            long l_lngProductId = l_ifoContractParams.getProductId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_porductRow = (IfoProductRow) l_product.getDataSourceObject();
            //�敨OP����������擾����
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductRow.getTargetMarketId());

            //ID
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            //�����R�[�h
            l_contractReferenceUnit.futProductCode = l_porductRow.getProductCode();
            //������
            l_contractReferenceUnit.futProductName = l_porductRow.getStandardName();
            //�w�����
            l_contractReferenceUnit.targetProductCode = l_porductRow.getUnderlyingProductCode();
            //����
            l_contractReferenceUnit.delivaryMonth = l_porductRow.getMonthOfDelivery();
            //����s��
            long l_lngMardetId = l_porductRow.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            //���敪
            l_contractReferenceUnit.contractType = "" + l_ifoContractParams.getContractType().intValue();
            //����      
            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());
            //������
            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
            //���P��
            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());
            //���Ϗ�ԋ敪
            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED;
            //�������z
            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblQuantity));
            //���萔��
            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
            //����ŏI��
            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());
            //���v
            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            //���v(���o��j
            l_contractReferenceUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdEvaluateIncome.subtract(l_bdCost).doubleValue());
            //����.����敪
            l_contractReferenceUnit.sessionType = l_ifoContractParams.getSessionType();
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            log.exiting(METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (create�敨���ϒ����ʏƉ��)<BR>
     * <BR>
     * ���ϒ���1���ׂ��쐬����B�i�敨�j<BR>
     * <BR>
     * �u�i�敨OP�c���jcreate�敨���ϒ����ʏƉ�ׁv�Q�ƁB<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502DB
     */

    public void createFuturesSettlingContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String METHOD_NAME = "createFuturesSettlingContractInquiryDetails(ArrayList, WEB3IfoContractImpl)";
        log.entering(METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            // 1.�����w���敨���ʏƉ�ׂ𐶐�����B
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit = new WEB3FuturesContractReferenceUnit();

            // 2.���ϒ��������ʂ��擾����
            double l_dblLockedQuality = l_ifoContract.getLockedQuantity();

            // 3.����ID���擾����
            long l_lngContractId = l_ifoContract.getContractId();

            IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

            // 4.get�ԍώw����(����ID)
            IfoClosingContractSpec[] l_lisContractSpecs = this.getClosingContractSpecs(l_lngContractId);
            double l_dblEvaluateIncome = 0;
            BigDecimal l_bdEvaluateIncome = new BigDecimal("0");

            // 5.�������擾����B
            double l_dblContractcurrentPrice = this.getContractCurrentPrice(l_ifoContract);

            if (l_lisContractSpecs != null)
            {
                for (int i = 0; i < l_lisContractSpecs.length; i++)
                {
                    // 6.�s��m�F�ϕԍϐ��ʂ��擾����
                    //�s��m�F�ϕԍϐ��� == null�̏ꍇ�A�������ʂƂ��ĕԍϒ������ʂ��g�p����
                    IfoClosingContractSpecRow l_specRow =
                        (IfoClosingContractSpecRow) l_lisContractSpecs[i].getDataSourceObject();
                    double l_dblQuantity = 0D;
                    if (!l_specRow.getConfirmedQuantityIsNull())
                    {
                        //�������ʁF���ʕԍώw����.�s��m�F�ϕԍϐ���
                        l_dblQuantity = l_specRow.getConfirmedQuantity();
                    }
                    else
                    {
                        //�������ʁF���ʕԍώw����.�ԍϒ�������
                        l_dblQuantity = l_specRow.getQuantity();
                    }

                    //�ԍϖ�萔�ʂ��擾����B
                    double l_dblExecutedQuantity = l_lisContractSpecs[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0D;
                    }

                    //�����P��ID���擾����B
                    long l_lngOrderUintId = l_lisContractSpecs[i].getOrderUnitId();

                    // �����P�ʂ��擾����B
                    IfoOrderUnit l_orderUnit = (IfoOrderUnit) l_orderMgr.getOrderUnit(l_lngOrderUintId);

                    //�����L����Ԃ��擾����B
                    OrderOpenStatusEnum l_statusEnum = l_orderUnit.getOrderOpenStatus();

                    //�ΏۊO�����`�F�b�N
                    //�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�́A�ΏۊO�����Ƃ��Ď��̗v�f�ɏ������ڍs����B
                    //�E�敨OP�ԍώw����.getConfirmedQuantity() == 0
                    //�E�����P��.getOrderOpenStatusEnum == "�N���[�Y"
                    if (l_dblQuantity == 0 || OrderOpenStatusEnum.CLOSED.equals(l_statusEnum))
                    {
                        continue;
                    }

                    // �]�����v���擾����B
                    double l_dblContractEvaluateIncome =
                        l_ifoContract.getEvaluateIncome(
                            l_dblContractcurrentPrice,
                            l_dblQuantity - l_dblExecutedQuantity);

                    l_bdEvaluateIncome =
                        l_bdEvaluateIncome.add(new BigDecimal(l_dblContractEvaluateIncome + ""));

                    l_dblEvaluateIncome = l_bdEvaluateIncome.doubleValue();
                }
            }

            // 10.get���萔��(double)
            double l_dblContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommission))
            {
                l_dblContractCommission = 0D;
            }

            // 11.get���萔�������(double)
            double l_dblContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuality);

            if (Double.isNaN(l_dblContractCommissionTax))
            {
                l_dblContractCommissionTax = 0D;
            }
            
            // ���萔���{���萔�������
            BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
            BigDecimal l_bdContractCommissionTax = new BigDecimal(l_dblContractCommissionTax + "");
            BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
            double l_dblCost = l_bdCost.doubleValue();

            // ����ID���擾����B
            long l_lngProductId = l_ifoContractParams.getProductId();

            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            WEB3IfoProductImpl l_product = (WEB3IfoProductImpl) l_productManager.getProduct(l_lngProductId);
            IfoProductRow l_porductRow = (IfoProductRow) l_product.getDataSourceObject();
            //�敨OP����������擾����
            WEB3IfoTradedProductImpl l_tradedproduct = (WEB3IfoTradedProductImpl) l_productManager.getTradedProduct(l_lngProductId, l_porductRow.getTargetMarketId());

            //ID
            l_contractReferenceUnit.id = "" + l_ifoContractParams.getContractId();
            //�����R�[�h
            l_contractReferenceUnit.futProductCode = l_porductRow.getProductCode();
            //������
            l_contractReferenceUnit.futProductName = l_porductRow.getStandardName();
            //�w�����
            l_contractReferenceUnit.targetProductCode = l_porductRow.getUnderlyingProductCode();
            //����
            l_contractReferenceUnit.delivaryMonth = l_porductRow.getMonthOfDelivery();
            //����s��
            long l_lngMardetId = l_porductRow.getTargetMarketId();
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            String l_strMarketCode = l_finOjbectManager.getMarket(l_lngMardetId).getMarketCode();
            l_contractReferenceUnit.marketCode = l_strMarketCode;
            //���敪
            l_contractReferenceUnit.contractType = "" + l_ifoContractParams.getContractType().intValue();
            //����      
            l_contractReferenceUnit.openDate = WEB3DateUtility.toDay(l_ifoContractParams.getOpenDate());
            //������
            l_contractReferenceUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuality);
            //���P��
            l_contractReferenceUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContractParams.getContractPrice());
            //���Ϗ�ԋ敪
            l_contractReferenceUnit.settlementState = WEB3IfoSettlementStateDef.SETTLING;
            //�������z
            l_contractReferenceUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractExecutedAmount(l_dblLockedQuality));
            //���萔��
            l_contractReferenceUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
            //����ŏI��
            l_contractReferenceUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedproduct.getLastTradingDate());
            //���v
            l_contractReferenceUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);
            //���v�i���o��j
            l_contractReferenceUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdEvaluateIncome.subtract(l_bdCost).doubleValue());
            //����.����敪
            l_contractReferenceUnit.sessionType = l_ifoContractParams.getSessionType();
            l_lisContractInquiryDetails.add(l_contractReferenceUnit);

            log.exiting(METHOD_NAME);
            return;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + METHOD_NAME, l_ex.getMessage(), l_ex);
        }

    }

    /**
     * (create�敨�������ʏƉ��)<BR>
     * <BR>
     * 1�̌��ʂŕ����̌��ʏƉ�ׂ��쐬����ꍇ�̏������s���B�i�敨�j<BR>
     * <BR>
     * �����̌��Ϗ�Ԃɂ��ƂÂ��A<BR>
     * create�敨���ύό��ʏƉ��<BR>
     * create�敨�����ό��ʏƉ��<BR>
     * create�敨���ϒ����ʏƉ��<BR>
     * ���\�b�h�̂����ꂩ���R�[������B<BR>
     * <BR>
     * (1)���Ϗ�ԁF3�̏ꍇ<BR>
     *   �����ςƌ��ϒ���2���ׂ��쐬����B<BR>
     *   <BR>
     * �@@create�敨�����ό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   create�敨���ϒ����ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * <BR>
     * (2)���Ϗ�ԁF4�̏ꍇ<BR>
     *   ���ύςƖ����ς�2���ׂ��쐬����B<BR>
     * <BR>
     *   create�敨���ύό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   create�敨�����ό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * <BR>
     * (3)���Ϗ�ԁF5�̏ꍇ<BR>
     *   ���ύςƖ����ςƌ��ϒ���3���ׂ��쐬����B<BR>
     * <BR>
     *   create�敨���ύό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   create�敨�����ό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   create�敨���ϒ����ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * <BR>
     * (4)���Ϗ�ԁF6�̏ꍇ<BR>
     *   ���ύςƌ��ϒ���2���ׂ��쐬����B<BR>
     *   create�敨���ύό��ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     *   create�敨���ϒ����ʏƉ��(�p�����[�^.���ʏƉ�׃��X�g�A�p�����[�^.�敨OP����)<BR>
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �쐬�������ׂ��i�[���郊�X�g�B<BR>
     * @@param l_ifoContract - (�敨OP����)<BR>
     * <BR>
     * ���ׂ��쐬���錚�ʁB<BR>
     * @@param l_intSettlementStatus - (���Ϗ��)<BR>
     * <BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * 3 �F�����ςƌ��ϒ�<BR> 
     * 4 �F���ύςƖ����� <BR>
     * 5 �F���ύςƖ����ςƌ��ϒ�<BR> 
     * 6 �F���ύςƌ��ϒ�<BR> 
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A2FAC502EA
     */

    public void createMultiFuturesContractInquiryDetails(ArrayList l_lisContractInquiryDetails, WEB3IfoContractImpl l_ifoContract, int l_intSettlementStatus) throws WEB3BaseException
    {
        final String METHOD_NAME = "createMultiFuturesContractInquiryDetails(ArrayList, WEB3IfoContractImpl, int)";
        log.entering(METHOD_NAME);

        // �����̌��Ϗ�Ԃɂ��ƂÂ�
        // create�敨���ύό��ʏƉ��
        // create�敨�����ό��ʏƉ��
        // create�敨���ϒ����ʏƉ��
        // ���\�b�h�̂����ꂩ���R�[������B
        switch (l_intSettlementStatus)
        {
            case 3 :
                // (1)���Ϗ�ԁF3�̏ꍇ
                // �����ςƌ��ϒ���2���ׂ��쐬����B


                // create�敨�����ό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create�敨���ϒ����ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            case 4 :
                // (2)���Ϗ�ԁF4�̏ꍇ
                // ���ύςƖ����ς�2���ׂ��쐬����B


                // create�敨���ύό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create�敨�����ό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);

                break;

            case 5 :
                // (3)���Ϗ�ԁF5�̏ꍇ
                // ���ύςƖ����ςƌ��ϒ���3���ׂ��쐬����B


                // create�敨���ύό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create�敨�����ό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesUnSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create�敨���ϒ����ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            case 6 :
                //(4)���Ϗ�ԁF6�̏ꍇ<BR>
                //���ύςƌ��ϒ���2���ׂ��쐬����B


                // create�敨���ύό��ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesSettledContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                // create�敨���ϒ����ʏƉ��(���ʏƉ�׃��X�g�A�敨OP����)
                createFuturesSettlingContractInquiryDetails(l_lisContractInquiryDetails, l_ifoContract);
                break;

            default :
                log.error("Error In Method: " + METHOD_NAME);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + "." + METHOD_NAME);
        }
        log.exiting(METHOD_NAME);

    }

    /**
     * (is�Ώی���)<BR>
     * <BR>
     * �w�茚�ʂ��Ώی���(�敨�܂��̓I�v�V����)�ł��邩�𔻒肷��B<BR>
     * <BR>
     * (1)����ID���擾����<BR>
     * �p�����[�^.����.get����ID()<BR>
     * <BR>
     * (2)�敨OP�������擾����<BR>
     * �敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID�Flong)<BR>
     * <BR>
     * (3)�敨�I�v�V�����敪���擾����<BR>
     * �敨OP����.get�敨�^�I�v�V�����敪()<BR>
     * <BR>
     * (4)�Ώی��ʃ`�F�b�N���s��<BR>
     * (3)�Ŏ擾�����敨�^�I�v�V�����敪��<BR>
     * �p�����[�^.�敨�^�I�v�V�����敪�ƈ�v����ꍇ�ɂ�true���A<BR>
     * ��v���Ȃ��ꍇ�ɂ�false��Ԃ�<BR>
     * @@param l_ifoContract - �敨OP����
     * @@param l_strFutureOptionDivision - (�敨�^�I�v�V�����敪)<BR>
     * <BR>
     * 1�F �敨<BR>
     * 2�F �I�v�V����<BR>
     * @@return boolean
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407549B402BA
     */
    protected boolean isObjectContract(WEB3IfoContractImpl l_ifoContract, String l_strFutureOptionDivision) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isObjectContract";
        log.entering(STR_METHOD_NAME);

        // ���ʂ��擾����
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // (1)����ID���擾����
        long l_lngProductId = l_ifoContractParams.getProductId();

        log.debug(" *** ����ID l_lngProductId = " + l_lngProductId);

        try
        {
            // (2)�敨OP�������擾����
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            ProductManager l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            Product l_product = l_productManager.getProduct(l_lngProductId);

            // (3)�敨�I�v�V�����敪���擾����
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            String l_strFutureOptionDiv = l_porductParams.getFutureOptionDiv();

            log.debug(" *** �敨�I�v�V�����敪 l_strFutureOptionDiv = " + l_strFutureOptionDiv);

            // (4)�Ώی��ʃ`�F�b�N���s��
            if (l_strFutureOptionDiv.equals(l_strFutureOptionDivision))
            {

                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("Error In Method: " + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get���Ϗ��)<BR>
     * <BR>
     * ���ʂ̌��Ϗ�Ԃ𔻒肷��B<BR>
     * <BR>
     * �u�i�敨OP�c���jget���Ϗ�ԁv�Q��<BR>
     * @@param l_ifoContract - �敨OP����
     * @@return int
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40754ABC0175
     */
    public int getSettlementStatus(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSettlementStatus";
        log.entering(STR_METHOD_NAME);

        // ���ʂ��擾����
        IfoContractParams l_ifoContractParams = (IfoContractParams) l_ifoContract.getDataSourceObject();

        // 1.����ID���擾����B
        long l_lngContractId = l_ifoContract.getContractId();

        String l_timestamp = WEB3DateUtility.formatDate(l_ifoContractParams.getLastUpdatedTimestamp(), "yyyyMMdd"); 
        // 3.getOriginalQuantity( ) --- ���ʌ����ʂ��擾����B
        double l_dblOriginalQuantity = l_ifoContract.getOriginalQuantity();
        // 4.getQuantity( ) --- ���ʐ��ʂ��擾����B
        double l_dblQuantity = l_ifoContract.getQuantity();
        // 5.getLockedQuantity( ) --- ���ϒ��������ʂ��擾����B
        double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();

        Timestamp l_dteCurrentDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        String l_CurrentDate = WEB3DateUtility.formatDate(l_dteCurrentDate, "yyyyMMdd");

        if (GtlUtils.Double.isZero(l_dblOriginalQuantity) && GtlUtils.Double.isZero(l_dblQuantity))
        {
            //���ʌ�����==0�A�����ʐ���==0�̏ꍇ�A-2�i�V�K��������j��ԋp����B
            log.debug(" *** ���Ϗ�Ԕ���: -2�V�K�������");
            return WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE;
        }

        // ���Ϗ�Ԕ���
        // �O��
        if (l_timestamp.compareTo(l_CurrentDate) < 0 && GtlUtils.Double.isZero(l_dblQuantity))
        {
            log.debug(" *** -1���Ϗ�Ԕ���: �O���ȑO���ύ�");

            log.exiting(STR_METHOD_NAME);
            return WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED;
        }

        if (l_timestamp.compareTo(l_CurrentDate) == 0 && GtlUtils.Double.isZero(l_dblQuantity))             
        {
            log.debug(" *** ���Ϗ�Ԕ���: 0�������ύ�");

            log.exiting(STR_METHOD_NAME);
            return WEB3IfoSettlementStatusDef.SETTLED;
        }
        // 6.get�����ԍϖ�萔��(long, Date) 
        // --- �w�茚�ʂ̓����ԍϖ�萔�ʂ��擾����B

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        double l_dblContractExecutionCnt = getDayClosingContractExecutionCnt(l_lngContractId, l_processTime);
        if (Double.isNaN(l_dblContractExecutionCnt))
        {
            l_dblContractExecutionCnt = 0;
        }
        log.debug(" *** get�����ԍϖ�萔��: �����ԍϖ�萔�� = " + l_dblContractExecutionCnt);

        if (l_dblLockedQuantity == 0)
        {

            if (l_dblContractExecutionCnt == 0)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug(" *** ���Ϗ�Ԕ���: 1������");
                return WEB3IfoSettlementStatusDef.UNSETTLED;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                log.debug(" *** ���Ϗ�Ԕ���: 4���ύςƖ�����");
                return WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED;
            }
        }

        if (l_dblLockedQuantity >= 1)
        {
            //2. ���ϒ��������ʁ�1�̏ꍇ
            if (l_dblContractExecutionCnt == 0)
            {
                //2-1. �����ԍϖ�萔��==0�̏ꍇ
                if (GtlUtils.Double.isEqual(l_dblLockedQuantity, l_dblQuantity))
                {
                    //2-1-1. ���ϒ���������==���ʐ��ʂ̏ꍇ�A2�i���ϒ��j��ԋp����B
                    log.debug("  *** ���Ϗ�Ԕ���: 2���ϒ�");
                    return WEB3IfoSettlementStatusDef.SETTLING;
                }
                else
                {
                    //2-1-2. 2-1-1�ȊO�̏ꍇ�A3�i�����ςƌ��ϒ��j��ԋp����B
                    log.debug("  *** ���Ϗ�Ԕ���: 3�����ςƌ��ϒ�");
                    return WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING;
                }
            }
            else if (l_dblContractExecutionCnt >= 1)
            {
                //2-2. �����ԍϖ�萔�ʁ�1�̏ꍇ
                if (GtlUtils.Double.isEqual(l_dblLockedQuantity, l_dblQuantity))
                {
                    //2-2-1. ���ϒ���������==���ʐ��ʂ̏ꍇ�A6�i���ύςƌ��ϒ��j��ԋp����B
                    log.debug("  *** ���Ϗ�Ԕ���: 6���ύςƌ��ϒ�");
                    return WEB3IfoSettlementStatusDef.SETTLED_SETTLING;
                }
                else
                {
                    //2-2-2. 2-2-1�ȊO�̏ꍇ�A5�i���ύςƖ����ςƌ��ϒ��j��ԋp����B
                    log.debug("  *** ���Ϗ�Ԕ���: 5���ύςƖ����ςƌ��ϒ�");
                    return WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING;
                }
            }
        }

        log.error("Error In Method: " + STR_METHOD_NAME);
        throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, this.getClass().getName() + STR_METHOD_NAME);
    }

    /**
     * (get�w�茈�Ϗ�Ԍ��ʖ���)<BR>
     * 
     * <BR>
     * �w�茈�Ϗ�Ԃ̌��ʂ݂̂̌��ʏƉ�ׂ̈ꗗ���擾����B<BR>
     * <BR>
     * (1)���ʏƉ�׈ꗗ���Ƃ�Loop����<BR>
     * <BR>
     *   (1-1)���ʏƉ�ׂ̌��Ϗ�ԋ敪���擾<BR>
     * <BR>
     *   (1-2)�p�����[�^.�w�茈�Ϗ�Ԃ�(1-1)�̌��Ϗ�ԋ敪���r���A<BR>
     *         ��v���Ȃ������ꍇ�́A���ʏƉ�ׂ����ʏƉ�׈ꗗ����<BR>
     *         �폜����<BR>
     * <BR>
     *  (2)�w�茈�Ϗ�Ԃ݂̂̌��ʏƉ�׈ꗗ��ԋp����<BR>
     * <BR>
     * ��(1)�̌��ʁA���ʏƉ�׈ꗗ�̃T�C�Y��0�ɂȂ����ꍇ�ɂ́A<BR>
     * NULL��ԋp����<BR>
     * @@param l_strSpecSettlementStatus - (�w�茈�Ϗ��)<BR>
     * <BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * 0�F���ύ�<BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     * 
     * @@param l_lisContractInquiryDetails - (���ʏƉ�׃��X�g)<BR>
     * <BR>
     * �����w���I�v�V�������ʏƉ�ׂ̈ꗗ���i�[���ꂽ���X�g<BR>
     * @@return ArrayList
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407E7FE602AC
     */
    protected ArrayList getSpecSettlementStatusContractDetails(String l_strSpecSettlementStatus, ArrayList l_lisContractInquiryDetails) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSpecSettlementStatusContractDetails";
        log.entering(STR_METHOD_NAME);


        if (l_lisContractInquiryDetails == null || l_strSpecSettlementStatus == null)
        {
            String l_strMessage = "�p�����[�^���ʏƉ�׃��X�g�̒l�s���I";
            log.error(l_strMessage);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName(), STR_METHOD_NAME);
        }
        if (l_lisContractInquiryDetails.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // (1)���ʏƉ�׈ꗗ���Ƃ�Loop����
        ListIterator l_iterator = l_lisContractInquiryDetails.listIterator();
        while (l_iterator.hasNext())
        {
            String l_strState = null;
            Object l_objUnit = l_iterator.next(); 
            if (l_objUnit instanceof WEB3OptionsContractReferenceUnit)
            {
                WEB3OptionsContractReferenceUnit l_ContractUnit = (WEB3OptionsContractReferenceUnit) l_objUnit;
                //(1-1)���ʏƉ�ׂ̌��Ϗ�ԋ敪���擾
                l_strState = l_ContractUnit.settlementState;
            }
            else
            {
                WEB3FuturesContractReferenceUnit l_ContractUnit = (WEB3FuturesContractReferenceUnit) l_objUnit;
                //(1-1)���ʏƉ�ׂ̌��Ϗ�ԋ敪���擾
                l_strState = l_ContractUnit.settlementState;
            }

            // (1-2)�p�����[�^.�w�茈�Ϗ�Ԃ�(1-1)�̌��Ϗ�ԋ敪���r��
            // ��v���Ȃ������ꍇ�́A���ʏƉ�ׂ����ʏƉ�׈ꗗ����폜����

            if (!l_strState.equals(l_strSpecSettlementStatus))
            {

                l_iterator.remove();
            }
        }
        // (2)�w�茈�Ϗ�Ԃ݂̂̌��ʏƉ�׈ꗗ��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_lisContractInquiryDetails;
    }

    /**
     * (get���ʈꗗ)<BR>
     * <BR>
     * �igetContracts�̃I�[�o�[���[�h�j<BR>
     * �w������Ɉ�v����敨OP���ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����<BR>
     * <BR>
     * (2)���������ǉ�<BR>
     * �@@�@@(2-1�j�p�����[�^.��������������ɁA����ID�A�⏕����ID�A<BR>
     *     �����^�C�v��ǉ���������������������쐬<BR>
     * <BR>
     * �@@�@@�������������� ��<BR>
     * �@@�@@"account_id = ? and sub_account_id = �H and product_type = ?"
     *     �{ �p�����[�^.��������������<BR>
     * <BR>
     *     (2-2)������z��𐶐����A����ID�A�⏕����ID�A�����^�C�v�A<BR>
     *     �p�����[�^.���������f�[�^�R���e�i�̏��ɃZ�b�g����B<BR>
     * <BR>
     * ������ID�A�⏕����ID�̓p�����[�^�̕⏕�����I�u�W�F�N�g���擾�A<BR>
     *    �����^�C�v�̓p�����[�^.�����^�C�v���ݒ肷��B<BR>
     * <BR>
     * (3)QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �敨OP���ʃI�u�W�F�N�g��List���擾����<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,�敨OP����Row.TYPE<BR>
     *                            (2-1)�̌�������������,<BR>
     *                            (2-2)�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * (4)�������ʂ�ԋp����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * <BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_productType - �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F����<BR>
     * 2�F��<BR>
     * 3�F�����M��<BR>
     * 4�F�O����<BR>
     * 5�F����<BR>
     * 6�F�敨�I�v�V����<BR>
     * @@param l_strSearchConditionString - �������� ������
     * @@param l_strSearchConditionDataContainer - (���������f�[�^�R���e�i)<BR>
     * <BR>
     * ��������<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A1D5001F3
     */
    protected List getContracts(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType, String l_strSearchConditionString, String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts";
        log.entering(STR_METHOD_NAME);

        // (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����
        List l_lisReturnValue = new ArrayList();

        // (2)���������ǉ�
        // (2-1�j����������������쐬
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? ";
    
        Object[] l_objWhereValue = null;
        // (2-2)������z��𐶐�
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_productType.intValue())};

        if ((l_strSearchConditionString != null && !l_strSearchConditionString.equals("")) && l_strSearchConditionDataContainer != null)
        {
            l_strWhere += l_strSearchConditionString;

            l_objWhereValue = new Object[l_strSearchConditionDataContainer.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchConditionDataContainer, 0, l_objWhereValue, 3, l_strSearchConditionDataContainer.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        // (3)QueryProcessor.doFindAllQuery( )�ɂ��A
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoContractRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "���ʃe�[�u��������  error";
            log.error(l_strMessage, l_ex);

            //throw new WEB3SystemLayerException
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "���ʃe�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }
    
    /**
     * (get���ʈꗗ)<BR>
     * <BR>
     * �igetContracts�̃I�[�o�[���[�h�j<BR>
     * �w������Ɉ�v����敨OP���ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����B<BR>
     * <BR>
     * (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����<BR>
     * <BR>
     * (2)���������ǉ�<BR>
     * �@@�@@(2-1�j�p�����[�^.��������������ɁA����ID�A�⏕����ID�A<BR>
     *     �����^�C�v��ǉ���������������������쐬<BR>
     * <BR>
     * �@@�@@�������������� ��<BR>
     * �@@�@@"account_id = ? and sub_account_id = �H and product_type = ?"
     *     �{ �p�����[�^.��������������<BR>
     * <BR>
     *     (2-2)������z��𐶐����A����ID�A�⏕����ID�A�����^�C�v�A<BR>
     *     �p�����[�^.���������f�[�^�R���e�i�̏��ɃZ�b�g����B<BR>
     * <BR>
     * ������ID�A�⏕����ID�̓p�����[�^�̕⏕�����I�u�W�F�N�g���擾�A<BR>
     *    �����^�C�v�̓p�����[�^.�����^�C�v���ݒ肷��B<BR>
     * <BR>
     * (3)QueryProcessor.doFindAllQuery( )�ɂ��A<BR>
     * �敨OP���ʃI�u�W�F�N�g��List���擾����<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,�敨OP����Row.TYPE<BR>
     *                            (2-1)�̌�������������,<BR>
     *                            (2-2)�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * (4)�������ʂ�ԋp����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * <BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_productType - �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F����<BR>
     * 2�F��<BR>
     * 3�F�����M��<BR>
     * 4�F�O����<BR>
     * 5�F����<BR>
     * 6�F�敨�I�v�V����<BR>
     * @@param l_strSearchConditionString - �������� ������
     * @@param l_strOrderBy - �\�[�g����������
     * @@param l_strSearchConditionDataContainer - (���������f�[�^�R���e�i)<BR>
     * <BR>
     * ��������<BR>
     * @@return List
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A1D5001F3
     */
    protected List getContracts(WEB3GentradeSubAccount l_subAccount, ProductTypeEnum l_productType, String l_strSearchConditionString, String l_strOrderBy, String[] l_strSearchConditionDataContainer)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContracts";
        log.entering(STR_METHOD_NAME);

        // (1)�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����
        List l_lisReturnValue = new ArrayList();

        // (2)���������ǉ�
        // (2-1�j����������������쐬
        String l_strWhere = "account_id = ? and sub_account_id = ? and product_type = ? ";
  
        Object[] l_objWhereValue = null;
        // (2-2)������z��𐶐���
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        Object[] l_objWhereValue1 = { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(l_productType.intValue())};

        if ((l_strSearchConditionString != null && !l_strSearchConditionString.equals("")) && l_strSearchConditionDataContainer != null)
        {

            l_strWhere += l_strSearchConditionString;

            l_objWhereValue = new Object[l_strSearchConditionDataContainer.length + 3];
            System.arraycopy(l_objWhereValue1, 0, l_objWhereValue, 0, 3);

            System.arraycopy(l_strSearchConditionDataContainer, 0, l_objWhereValue, 3, l_strSearchConditionDataContainer.length);

        }
        else
        {
            l_objWhereValue = new Object[3];
            l_objWhereValue[0] = new Long(l_lngAccountId);
            l_objWhereValue[1] = new Long(l_lngSubAccountId);
            l_objWhereValue[2] = new Long(l_productType.intValue());
        }

        // (3)QueryProcessor.doFindAllQuery( )�ɂ��A
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoContractRow.TYPE, l_strWhere, l_strOrderBy, null, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "���ʃe�[�u��������  error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "���ʃe�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisReturnValue;
    }

    /**
     * (get���ʎ���)<BR>
     * <BR>
     * �w�茚�ʂ̖����̎������擾����B<BR>
     * <BR>
     * ���L�菇�Ŏ擾����������ԋp����B<BR>
     * <BR>
     * (1)�s��ID�A����ID�̎擾<BR>
     * �s��ID = �p�����[�^.�敨OP����.get�s��ID()<BR>
     * ����ID = �p�����[�^.�敨OP����.get����ID()<BR>
     * <BR>
     * (2)�����R�[�h�̎擾<BR>
     * �敨OP���� = �敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID)<BR>
     * �����R�[�h = �敨OP����.get�����R�[�h()<BR>
     * <BR>
     * (3)�����Z�b�g���肨��ю����̎擾<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(CURRENT_PRICE)��Hashtable���擾<BR>
     * 
     * �@@(3-1)�Y�������̎������Z�b�g����Ă���ꍇ(Hashtable.get(�����R�[�h)��NULL�łȂ��ꍇ)<BR>
     * <BR>
     *         ���� = Hashtable.get(�����R�[�h)<BR>
     * 
     * �@@(3-2)�Y�������̎������Z�b�g����Ă��Ȃ��ꍇ(Hashtable.get(�����R�[�h)��NULL�̏ꍇ)<BR>
     * <BR>
     * �@@�@@(3-2-1)�����̎擾<BR>
     * �@@�@@�@@�@@�@@�@@�@@�敨OP������� = �敨OP�v���_�N�g�}�l�[�W��.getTradedProduct(����ID�A�s��ID�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@���� = �敨OP�v���_�N�g�}�l�[�W��.get����(�敨OP�������)<BR>
     * �@@�@@<BR>
     * �@@�@@(3-2-2)�����̒ǉ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@�擾����Hashtable�ɊY�������R�[�h�Ǝ�����ǉ�<BR>
     * �@@�@@�@@�@@�@@�@@�@@Hashtable.put�i�����R�[�h�A ����)<BR>
     * <BR>
     * �@@�@@(3-2-3)�����̃Z�b�g<BR>
     * �@@�@@�@@�@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ������Z�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@(3-2-2)�Ŏ�����ǉ�����Hashtable<BR>
     * <BR>
     * (4)������ԋp<BR>
     * <BR>
     * �����Y���\�b�h���g�p����ꍇ�́A�e�T�[�r�X�C���^�Z�v�^��onCall�ɂĎ����̃Z�b�g����(*)�A<BR>
     *   onReturn�����onThrowable���\�b�h���ɂĎ����̃N���A�������s������<BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�ThreadLocal�Ɏ����̕ϐ����Z�b�g����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE<BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@Hashtable(�V�K�ɍ쐬����Hashtable)<BR>�@@�@@�@@�@@
     * @@param l_ifoContract - �敨OP����
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A61C401E3
     */
    public double getContractCurrentPrice(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractCurrentPrice";
        log.entering(STR_METHOD_NAME);

        log.debug("contractId = " + l_ifoContract.getContractId());

        //�s��ID�A����ID�̎擾
        long l_lngMarketId = l_ifoContract.getMarketId();
        long l_lngProductId = l_ifoContract.getProduct().getProductId();

        try
        {
            //�����R�[�h�̎擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl) l_tradingModule.getProductManager();
            Product l_product = l_productManager.getProduct(l_lngProductId);

            //�����R�[�h = �敨OP����.get�����R�[�h()
            IfoProductParams l_porductParams = (IfoProductParams) l_product.getDataSourceObject();
            String l_strProductCode = l_porductParams.getProductCode();

            //�����Z�b�g���肨��ю����̎擾
            double l_dblCurrentPrice = 0;

            Hashtable l_htCurrentPrices = 
                (Hashtable) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE);

            //�Y�������̎������Z�b�g����Ă���ꍇ
            if (l_htCurrentPrices != null && l_htCurrentPrices.containsKey(l_strProductCode))
            {
                //�����̎擾
                Double l_currentPrice = (Double)l_htCurrentPrices.get(l_strProductCode);
                l_dblCurrentPrice = l_currentPrice.doubleValue();
            }
            else
            {
                if (l_htCurrentPrices == null)
                {
                    l_htCurrentPrices = new Hashtable();
                }
                WEB3IfoTradedProductImpl l_tradeProduct = null;
                l_tradeProduct = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(l_lngProductId, l_lngMarketId);
                //�����̎擾
                try{
                    l_dblCurrentPrice = l_productManager.getCurrentPrice(l_tradeProduct);
                }
                catch(WEB3BusinessLayerException l_ble)
                {
                    if(WEB3ErrorCatalog.BUSINESS_ERROR_01997.equals(l_ble.getErrorInfo()))
                    {
                        l_dblCurrentPrice = 0;
                    }
                    else
                    {
                        throw new WEB3BusinessLayerException(
                            l_ble.getErrorInfo(),
                            this.getClass().getName() + STR_METHOD_NAME,
                            l_ble.getErrorMessage(),
                            l_ble);
                    }
                }
                //�����̒ǉ�
                l_htCurrentPrices.put(l_strProductCode, new Double(l_dblCurrentPrice));
                //�����̃Z�b�g
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE, l_htCurrentPrices);
            }
            log.exiting(STR_METHOD_NAME);
            return l_dblCurrentPrice;
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

    }

    /**
     * (get�����ԍϖ�萔��)<BR>
     * <BR>
     * �w�茚�ʂ̓����ԍϖ�萔�ʂ��擾����B<BR>
     * <BR>
     * (1)�����̕ԍώw����ꗗ���擾<BR>
     * get�ԍώw����(�p�����[�^.����ID�A�p�����[�^.�X�V���t)���R�[��<BR>
     * <BR>
     * (2)�����ԍϖ�萔�擾����<BR>
     * �@@(2-1) (1)�̕Ԃ�l��NULL�ł������ꍇ�A0��ԋp����<BR>
     * <BR>
     * �@@(2-2) (2-1)�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�@@(2-2-1) �ԍώw����v�f�����Ƃ�Loop�������s���A<BR>
     * �@@�@@�@@�@@          �ԍώw����.get�ԍϖ�萔��()�̒l�����Z���Ă���<BR>
     * <BR>
     * �@@�@@�@@�@@ (2-2-2) �ԍϖ�萔�ʂ̍��v�l��ԋp����<BR>
     * @@param l_lngContractID - ����ID
     * @@param l_datLastUpdatedTimestamp - (�X�V���t)<BR>
     * <BR>
     * �����̓��t<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return double
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407DEBB50259
     */
    protected double getDayClosingContractExecutionCnt(long l_lngContractID, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDayClosingContractExecutionCnt";
        log.entering(STR_METHOD_NAME);

        // (1)�����̕ԍώw����ꗗ���擾
        IfoClosingContractSpec[] l_closingContractSpecs = getClosingContractSpecs(l_lngContractID, l_datLastUpdatedTimestamp);
        // (2)�����ԍϖ�萔�擾����
        // (2-1) (1)�̕Ԃ�l��NULL�ł������ꍇ�A0��ԋp����
        if (l_closingContractSpecs == null)
        {
            log.debug("�����̕ԍώw����ꗗ���擾�̌��ʂ�Null�A 0��ԋp���� !!!");

            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        // (2-2) (2-1)�ȊO�̏ꍇ
        // (2-2-1) �ԍώw����v�f�����Ƃ�Loop�������s��
        //     --- �ԍώw����.get�ԍϖ�萔��()�̒l�����Z���Ă���
        BigDecimal l_bdExecutedQuantity = new BigDecimal("0");
        for (int i = 0; i < l_closingContractSpecs.length; i++)
        {
            log.debug("�ԍώw����.get�ԍϖ�萔��(" + i + ")�̒l = " + l_closingContractSpecs[i].getExecutedQuantity());

            BigDecimal l_bdQuantity =
                new BigDecimal(l_closingContractSpecs[i].getExecutedQuantity() + "");

            l_bdExecutedQuantity = l_bdExecutedQuantity.add(l_bdQuantity);
        }

        log.debug("Output parm: �ԍϖ�萔�ʂ̍��v�l�ԍϖ�萔�ʂ̍��v�l = " + l_bdExecutedQuantity);

        // (2-2-2) �ԍϖ�萔�ʂ̍��v�l��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_bdExecutedQuantity.doubleValue();
    }

    /**
     * (get�ԍώw����)<BR>
     * <BR>
     * �igetClosingContractSpecs�j<BR>
     * �w�肵������ID�ƍX�V���t�ɊY�����錚�ʕԍώw����̈ꗗ���擾����B<BR>
     * <BR>
     * (1)���ʕԍώw����e�[�u������<BR>
     * �ȉ��̏����Ō��ʕԍώw����e�[�u������������<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = �p�����[�^.����ID<BR>
     * �X�V���t = �p�����[�^.�X�V���t�Ɠ������t<BR>
     * <BR>
     * (2)(1)�̎擾���ʂ̈ꗗ��ԋp����<BR>
     * ���Y������f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����<BR>
     * @@param l_lngContractID - ����ID
     * @@param l_datLastUpdatedTimestamp - (�X�V���t)<BR>
     * <BR>
     * YYYYMMDD<BR>
     * @@return webbroker3.ifo.WEB3IfoClosingContractSpecImpl[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4075F6E702CB
     */
    public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID, Date l_datLastUpdatedTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingContractSpecs";
        log.entering(STR_METHOD_NAME);



        // (1)���ʕԍώw����e�[�u������
        List l_lisReturnValue = new ArrayList();
        // [��������]:
        
        String l_strWhere = " contract_id = ? and to_char(last_updated_timestamp,'yyyyMMdd') = ? ";

        String l_strLastUpdatedTime = WEB3DateUtility.formatDate(l_datLastUpdatedTimestamp, "yyyyMMdd");
        Object[] l_objWhereValue = { new Long(l_lngContractID), l_strLastUpdatedTime };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoClosingContractSpecRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        // (2)(1)�̎擾���ʂ̈ꗗ��ԋp����
        if (l_lisReturnValue.size() == 0)
        {
            log.debug("���ʕԍώw����e�[�u����������0��");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        IfoClosingContractSpec[] l_closingcontractspec = new IfoClosingContractSpec[l_lisReturnValue.size()];

        ArrayList l_lisReturnList = new ArrayList();
        for (int i = 0; i < l_lisReturnValue.size(); i++)
        {
            IfoClosingContractSpecRow l_row = (IfoClosingContractSpecParams) l_lisReturnValue.get(i);
            WEB3IfoClosingContractSpecImpl l_impl = new WEB3IfoClosingContractSpecImpl(l_row);
            l_lisReturnList.add(i, l_impl);
        }

        l_lisReturnList.toArray(l_closingcontractspec);


        log.exiting(STR_METHOD_NAME);
        return l_closingcontractspec;
    }

    /**
     * (get�ԍώw����)<BR>
     * <BR>
     * �igetClosingContractSpecs�j<BR>
     * �w�肵������ID�ɊY�����錚�ʕԍώw����̈ꗗ���擾����<BR>
     * <BR>
     * (1)���ʕԍώw����e�[�u������<BR>
     * �ȉ��̏����Ō��ʕԍώw����e�[�u������������<BR>
     * <BR>
     * [��������]<BR>
     * ����ID = ����.����ID<BR>
     * <BR>
     * (2)(1)�̎擾���ʂ̈ꗗ��ԋp����<BR>
     * ���Y������f�[�^�����݂��Ȃ��ꍇ�ɂ�NULL��ԋp����<BR>
     * @@param l_lngContractID - ����ID
     * @@return webbroker3.ifo.WEB3IfoClosingContractSpecImpl[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407A6F52006C
     */
    public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getClosingContractSpecs";
        log.entering(STR_METHOD_NAME);

        log.debug("Input Parm: ����ID l_lngContractID = " + l_lngContractID);

        // (1)���ʕԍώw����e�[�u������
        List l_lisReturnValue = new ArrayList();
        // [��������]:
        String l_strWhere = " contract_id = ? ";
        String[] l_strWhereValue = { "" + l_lngContractID };
        try
        {

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoClosingContractSpecRow.TYPE, l_strWhere, l_strWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        // (2)(1)�̎擾���ʂ̈ꗗ��ԋp����
        if (l_lisReturnValue.size() == 0)
        {
            log.debug("���ʕԍώw����e�[�u����������0��");

            log.exiting(STR_METHOD_NAME);
            return null;
        }
        IfoClosingContractSpec[] l_closingcontractspec = new IfoClosingContractSpec[l_lisReturnValue.size()];
        ArrayList l_lisReturnList = new ArrayList();
        for (int i = 0; i < l_lisReturnValue.size(); i++)
        {
            IfoClosingContractSpecRow l_row = (IfoClosingContractSpecParams) l_lisReturnValue.get(i);
            WEB3IfoClosingContractSpecImpl l_impl = new WEB3IfoClosingContractSpecImpl(l_row);
            l_lisReturnList.add(i, l_impl);
        }
        l_lisReturnList.toArray(l_closingcontractspec);

        log.exiting(STR_METHOD_NAME);
        return l_closingcontractspec;
    }

    /**
     * (update�g�����U�N�V����)<BR>
     * <BR>
     * �萔�����v�Z�i������j�����{���A�g�����U�N�V�����f�[�^���X�V����B<BR>
     * <BR>
     * �敨OP�|�W�V�����w���p�[.update�g�����U�N�V����()�ɏ������Ϗ�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�c���jupdate�g�����U�N�V�����v�Q�ƁB<BR>
     * @@param l_lngOrderUnitID - �����P�ʂh�c
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4099BCF300B1
     */
    public void updateTransaction(long l_lngOrderUnitID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction";
        log.entering(STR_METHOD_NAME);

        log.debug("Input Parm: �����P�ʂh�c l_lngOrderUnitID = " + l_lngOrderUnitID);

        try
        {

            log.debug("�敨OP�|�W�V�����w���p�[.update�g�����U�N�V����()�ɏ������Ϗ����� with " + ProductTypeEnum.IFO);

            WEB3IfoPositionManagerHelper l_positionHelper = new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO);
            l_positionHelper.updateTransaction(l_lngOrderUnitID);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("Error in Method: " + STR_METHOD_NAME, l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (adjust�ԍώw����)<BR>
     * �ԍώw����̐��ʂ𒲐�����B <BR>
     * <BR>
     * ���̗l�ɕԍώw����̕ԍϐ��ʂ𒲐�����B <BR>
     * �|���ʌ��̏ꍇ�A�ԍϘA�ԁF�偨���̏��ɁA�������ʕ��� <BR>
     * �@@�ԍϊ������ʂ̂����̖���萔�ʂ��猸�Z���Ă����B <BR>
     * �|���ʑ��̏ꍇ�A�ԍϘA�ԁF������̏��ɁA�������ʕ��� <BR>
     * �@@�����O�̕ԍϊ������ʁi�s��m�F�ϕԍϐ��ʁj������l�Ƃ��� <BR>
     * �@@�������̕ԍϊ������ʁi�ԍϒ������ʁj�ɉ��Z���Ă����B <BR>
     * <BR>
     * �P�j�@@�������ʂ��v�Z����B <BR>
     * �������� = �i�����O���ʁ@@�|�@@�����㐔�ʁj <BR>
     * <BR>
     * �Q�j�@@�ԍώw����Ǎ� <BR>
     * �@@�ԍώw����e�[�u�����s���b�N�I�v�V�����ifor update�j�ɂēǂ݁A<BR>
     *   �ԍώw����s�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * [��������] <BR>
     * �ԍώw����.�����h�c = �����h�c <BR>
     * �ԍώw����.�⏕�����h�c = �⏕�����h�c <BR>
     * �ԍώw����.�����h�c = �����h�c <BR>
     * �ԍώw����.�����P�ʂh�c = �����P�ʂh�c <BR>
     * <BR>
     * ���擾�� ��� �ԍϘA�Ԃ̍~���iasc�j <BR>
     * <BR>
     * �R�j�@@�擾�����ԍώw����̕ԍϒ������ʂ𒲐�����B <BR>
     * <BR>
     * �R�|�P�j�@@�������ʁ��O�̏ꍇ�i�ԍϐ��ʂ̊����Ă����炷�ꍇ�j <BR>
     * �@@�������ʂ�ԍώw����̖���萔�ʂɊ��蓖�Ă�B <BR>
     * �@@�擾�����ԍώw����s�I�u�W�F�N�g�ɑS�đ΂��ԍϘA�Ԃ̍~���Ɉȉ��������s���B <BR>
     * �@@�i���z��̌ォ��j <BR>
     * <BR>
     * �@@�R�|�P�|�P�j�@@����萔�ʎZ�o <BR>
     * �@@�@@����萔�� = �ԍώw����.�ԍϒ������� �| �ԍώw����.�ԍϖ�萔�� <BR>
     * <BR>
     * �@@�R�|�P�|�Q�j�@@�������ʊ��蓖�� <BR>
     * �@@�@@[�i����萔�� >= �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�E�i�ԍώw����.�ԍϒ������� �| �������ʁj�̒l��ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@�E������̕ԍώw����s�I�u�W�F�N�g�̓��e��DB���X�V�iupdate�j����B <BR>
     * �@@�@@�@@��LOOP�������I������B �i�������ʂ̊����Ċ����j <BR>
     * <BR>
     * �@@�@@[�i����萔�� �� �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�E�ԍώw����.�ԍϖ�萔�ʂ�ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@�E�������� = �������� - ����萔�� <BR>
     * �@@�@@�@@�E������̕ԍώw����s�I�u�W�F�N�g�̓��e��DB���X�V�iupdate�j����B <BR>
     * <BR>
     * �R�|�Q�j�����łȂ��ꍇ�i�ԍϐ��ʂ̊����Ă𑝂₷�ꍇ�j <BR>
     * �@@�s��m�F�ϕԍϐ��ʂ�����Ƃ��ĕԍϒ������ʂɒ������ʂ����蓖�Ă�B <BR>
     * <BR>
     * �@@�R�|�Q�|�P�j�������ʂ̕����𔽓]������B <BR>
     * <BR>
     * �@@�擾�����ԍώw����s�I�u�W�F�N�g�ɑS�đ΂��ԍϘA�Ԃ̏����Ɉȉ��������s���B <BR>
     * �@@�i���z��̑O����j <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�|�P�j�@@�����\���ʂ��Z�o���� <BR>
     * �@@�@@�@@�����\���ʁ��ԍώw����.�s��m�F�ϕԍϐ��� �| �ԍώw����.�ԍϒ������� <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�|�Q�j�����s�\�ȏꍇ�i�����\���ʁ��O�̏ꍇ�j <BR>
     * �@@�@@�@@���̕ԍώw����̏����� <BR>
     * <BR>
     * �@@�@@�R�|�Q�|�Q�|�R�j�@@�ԍϒ������ʂɒ������ʂ������Ă� <BR>
     * �@@�@@�@@[�i�����\���� �� �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�@@�E�i�ԍώw����.�ԍϒ������� �{ �������ʁj��ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@�@@�E������̕ԍώw����s�I�u�W�F�N�g�̓��e��DB���X�V�iupdate�j����B <BR>
     * �@@�@@�@@�@@��LOOP�������I������B �i�������ʂ̊����Ċ����j <BR>
     * <BR>
     * �@@�@@�@@[�i�����\���� �� �������ʁj�̏ꍇ] <BR>
     * �@@�@@�@@�@@�E�i�ԍώw����.�ԍϒ������� �{ �����\���ʁj��ԍώw����.�ԍϒ������ʂɃZ�b�g����B <BR>
     * �@@�@@�@@�@@�E�������� = �������� - �����\���� <BR>
     * �@@�@@�@@�@@�E������̕ԍώw����s�I�u�W�F�N�g�̓��e��DB���X�V�iupdate�j����B <BR>
     * <BR>
     * @@param l_lngAccountID - �����h�c
     * @@param l_lngSubAccountID - �⏕����ID
     * @@param l_lngOrderID - ����ID
     * @@param l_lngOrderUnitID - �����P��ID
     * @@param l_dblCountBeforeChanged - �����O����
     * @@param l_dblCountAfterChanged - �����㐔��
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40A06D790105
     */
    public void adjustClosingContractSpecs(long l_lngAccountID, long l_lngSubAccountID, long l_lngOrderID, long l_lngOrderUnitID, double l_dblCountBeforeChanged, double l_dblCountAfterChanged)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "adjustClosingContractSpecs";
        log.entering(STR_METHOD_NAME);

        try
        {

            // �������ʂ��v�Z����B
            double l_dblAdjustCount = l_dblCountBeforeChanged - l_dblCountAfterChanged;

            log.debug("�������ʂ��v�Z����: �������� =�i�����O���ʁ|�����㐔�ʁj= " + l_dblAdjustCount);

            // �ԍώw����Ǎ�
            List l_lisReturnValue = new ArrayList();
            String l_strWhere = " account_id = ? and sub_account_id = ? ";
            l_strWhere += "and order_id = ? and order_unit_id = ? ";
            String l_strOrderBy = "closing_serial_no asc";
            String[] l_strWhereValue = { "" + l_lngAccountID, "" + l_lngSubAccountID, "" + l_lngOrderID, "" + l_lngOrderUnitID };

            // doFindAllQuery
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturnValue = l_queryProcessor.doFindAllQuery(IfoClosingContractSpecRow.TYPE, l_strWhere, l_strOrderBy, null, l_strWhereValue);

            IfoClosingContractSpecParams l_rows = new IfoClosingContractSpecParams(); 
            
            //�ԍϐ��ʂ̊����Ă����炷�ꍇ
            if(l_dblAdjustCount >= 0)
            {

				for (int i = l_lisReturnValue.size() - 1; i >= 0  ; i--)
	            {
	                l_rows = new IfoClosingContractSpecParams((IfoClosingContractSpecRow) l_lisReturnValue.get(i));
	
	                double l_dblExecuteQuantity = l_rows.getExecutedQuantity();
	                double l_dblQuantity = l_rows.getQuantity();
	                double l_dblNotQuality = l_dblQuantity - l_dblExecuteQuantity;
	
	                if (l_dblNotQuality >= l_dblAdjustCount)
	                {
	                    l_rows.setQuantity(l_dblQuantity - l_dblAdjustCount);
						l_queryProcessor.doUpdateQuery(l_rows);	
	                    break;
	                }
	                else if(l_dblNotQuality < l_dblAdjustCount)
	                {
	                    l_rows.setQuantity(l_dblExecuteQuantity);
	                    l_dblAdjustCount = l_dblAdjustCount - l_dblNotQuality;
						l_queryProcessor.doUpdateQuery(l_rows); 
	                }
					
	            }
			}
			//�ԍϐ��ʂ̊����Ă𑝂₷�ꍇ
			else
			{
				l_dblAdjustCount = - l_dblAdjustCount;
				for (int i = 0; i < l_lisReturnValue.size(); i++)
				{
					l_rows = new IfoClosingContractSpecParams((IfoClosingContractSpecRow) l_lisReturnValue.get(i));
                        
					double l_dblConfirmedQuantity = l_rows.getConfirmedQuantity();
					double l_dblQuantity = l_rows.getQuantity();
					double l_dblAssignableQuantity = l_dblConfirmedQuantity - l_dblQuantity;
                    
					if (l_dblAssignableQuantity <= 0) continue;
                    
					if (l_dblAssignableQuantity >= l_dblAdjustCount)
					{
						l_rows.setQuantity(l_dblQuantity + l_dblAdjustCount);
						l_queryProcessor.doUpdateQuery(l_rows);
						break;
					}
					else
					{
						l_rows.setQuantity(l_dblQuantity + l_dblAssignableQuantity);
						l_dblAdjustCount = l_dblAdjustCount - l_dblAssignableQuantity;
						l_queryProcessor.doUpdateQuery(l_rows);
					}
				}
			}
     
        }
        catch (DataQueryException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u�������� error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            String l_strMessage = "���ʕԍώw����e�[�u��������  error";
            log.error(l_strMessage, l_ex);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * ���ʃe�[�u���̃��R�[�h����ԍό��ʂ̔z����쐬���A�ԋp����B<BR>
     *�V�[�P���X�}<BR>
     *�u�i�敨OP�c���jcreate�ԍό��ʈꗗ�v�Q�ƁB<BR>
     *<BR>
     * @@param l_subAccount
     * @@param l_contractTypeEnum
     * @@param l_lngMarketId
     * @@param l_lngProductId
     * @@return WEB3FuturesOptionsCloseMarginContractUnit[]
     * @@throws webbroker3.common.WEB3BaseException
     */
    public WEB3FuturesOptionsCloseMarginContractUnit[] createSettleContracts(WEB3GentradeSubAccount l_subAccount, ContractTypeEnum l_contractTypeEnum, long l_lngMarketId, long l_lngProductId)
        throws WEB3BaseException
    {
        final String StrMethodName = "createSettleContracts";
        log.entering(StrMethodName);

        //��������������̍쐬�ȉ��Ɏ��������Ō���������������쐬����B
        String l_strWhere = "and market_id = ? and contract_type = ? and product_id = ?";

        //���������f�[�^�R���e�i�̍쐬�ȉ��Ɏ��������Ō��������f�[�^�R���e�i(String�̔z��)���쐬����B
        //��String�łȂ��f�[�^�́AString�ɕϊ����ăZ�b�g����B
        String[] l_strWhereValue = { Long.toString(l_lngMarketId), Integer.toString(l_contractTypeEnum.intValue()), Long.toString(l_lngProductId)};

        //(*)�\�[�g����(order by��)������̍쐬
        String l_strOrderBy = null;
        if (ContractTypeEnum.LONG.equals(l_contractTypeEnum))
        {
            l_strOrderBy = "open_date asc, delivery_date asc, contract_price asc";
        }
        else if (ContractTypeEnum.SHORT.equals(l_contractTypeEnum))
        {
            l_strOrderBy = "open_date asc, delivery_date asc, contract_price desc";
        }

        List l_lstContracts = new ArrayList();

        //�\�[�g���ꂽ���ʃI�u�W�F�N�g�̈ꗗ���擾����B 
        l_lstContracts = this.getContracts(l_subAccount, ProductTypeEnum.IFO, l_strWhere, l_strOrderBy, l_strWhereValue);

        //�Y�����ʂ����݂��Ȃ��ꍇ�́A�u�Y���f�[�^�Ȃ��v�̗�O��throw����B
        if (l_lstContracts == null || l_lstContracts.size() == 0)
        {
            String l_strMessage = "���ʃe�[�u��������  error";
            log.error(l_strMessage);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + StrMethodName, l_strMessage);
        }

        //�ԍό��ʂ��i�[����ׂ�ArrayList�𐶐�����B
        List l_lstContractUnit = new ArrayList();

        //�擾�������ʃI�u�W�F�N�g������Loop����
        for (int i = 0; i < l_lstContracts.size(); i++)
        {
            IfoContractRow l_contractRow = (IfoContractRow) l_lstContracts.get(i);
            WEB3IfoContractImpl l_contractImpl = new WEB3IfoContractImpl(l_contractRow);

            //���ʐ��ʂ��擾����B
            double l_dblQuantity = l_contractImpl.getQuantity();

            //���ϒ��������ʂ��擾����B
            double l_dblLockedQuantity = l_contractImpl.getLockedQuantity();

            if (l_dblQuantity > 0 && l_dblQuantity != l_dblLockedQuantity)
            {
                //�ԍό��ʃI�u�W�F�N�g�𐶐�����B
                WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();

                //�ԍό��ʂɈȉ��̃v���p�e�B���Z�b�g����B                
                l_contractUnit.id = "" + l_contractImpl.getContractId();
                l_contractUnit.contractOrderQuantity = null;
                l_contractUnit.settlePriority = null;

                //�쐬�����ԍό��ʂ�ArrayList�ɒǉ�����B 
                l_lstContractUnit.add(l_contractUnit);
            }
        }

        if (l_lstContractUnit == null || l_lstContractUnit.size() == 0)
        {
            String l_strMessage = "�ԍό��ʂ�null�̒l�܂��́A�v�f�����O�ł���ꍇ�̃G���[";
            log.error(l_strMessage);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00178, this.getClass().getName() + StrMethodName, l_strMessage);
        }
        WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[l_lstContractUnit.size()];

        //�ԍό��ʂ̔z��𐶐�����B
        l_lstContractUnit.toArray(l_contractUnits);

        return l_contractUnits;
    }

    public Contract toContract(Row row)
    {
        log.debug("toContract to WEB3IfoContractImpl:" + row);
        return new WEB3IfoContractImpl((IfoContractRow) row);
    }

    public class WEB3IfoClosingContractSpecImpl extends IfoClosingContractSpecImpl
    {
        public WEB3IfoClosingContractSpecImpl(IfoClosingContractSpecRow row)
        {
            super(row);
        }
    }


    /**
     * (create�敨OP�c���Ɖ��)<BR>
     * ���ʂɊY������c���Ɖ�ׂ̈ꗗ���쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�敨OP�c��)create�敨OP�c���Ɖ�ׁv<BR>
     * �Q��<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_strFuOpDiv - (�敨�^�I�v�V�����敪)<BR>
     * 1�F�敨<BR>
     * 2�F�I�v�V����<BR>
     * @@param l_strSettlementStatus - (�w�茈�Ϗ��)<BR>
     * ���L�̂����ꂩ�B<BR>
     * <BR>
     * null�F�w��Ȃ� <BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     * 
     * @@param l_strQueryString - ��������������
     * @@param l_strQueryContainers - ���������f�[�^�R���e�i
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit[]
     * @@roseuid 41AC326201F4
     */
    public WEB3FuturesOptionsDetailUnit[] createIfoBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, String l_strFuOpDiv, String l_strSettlementStatus, String l_strQueryString, String[] l_strQueryContainers) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "createIfoBalanceReferenceDetailUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_strFuOpDiv == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        List l_lstContracts = new ArrayList();
        //�w������Ɉ�v���錚�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B
        l_lstContracts = this.getContracts((WEB3GentradeSubAccount)l_subAccount,ProductTypeEnum.IFO,l_strQueryString,l_strQueryContainers);
            
        //�c���Ɖ�ׂ��i�[����ArrayList�𐶐�����B
        List l_lstReferenceDetailUnit = new ArrayList();
            
        //(*)get���ʈꗗ()�̖߂�l�̗v�f(=���ʃI�u�W�F�N�g)����Loop����
        int l_intContractsLength = 0;
        if (l_lstContracts != null)
        {
            l_intContractsLength = l_lstContracts.size();
        }
                
        WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = null;     
        for (int i = 0; i < l_intContractsLength; i++)
        {
            IfoContractRow l_contractRow = (IfoContractRow)l_lstContracts.get(i);
            WEB3IfoContractImpl l_contractImpl = new WEB3IfoContractImpl(l_contractRow);
            //�w�茚�ʂ��Ώی���(�敨�܂��̓I�v�V����)�ł��邩�𔻒肷��B
            if (!this.isObjectContract(l_contractImpl,l_strFuOpDiv))
            {
                continue;
            }
            //�敨OP�������擾����B
            WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();
            //�����R�[�h�̃��Z�b�g�������s���B
            WEB3GentradeTradingTimeManagement.resetProductCode(l_productImpl.getUnderlyingProductCode());
            //���ʂ̌��Ϗ�Ԃ𔻒肷��B
            int l_intStatus = this.getSettlementStatus(l_contractImpl);
                
            //get���Ϗ��()�̖߂�l == ("�V�K�������" or"�O���ȑO���ύ�" or
            //"���ύ�")�̏ꍇ�A���̗v�f�֏������ȍ~����B(continue)
            if (WEB3IfoSettlementStatusDef.OPENCONTRACT_EXECUTE_CANCLE == l_intStatus
                     || WEB3IfoSettlementStatusDef.BEFORE_YESTODAY_SETTLED == l_intStatus 
                     || WEB3IfoSettlementStatusDef.SETTLED == l_intStatus)
            {
                continue;
            }
                
            //(*)�p�����[�^.�w�茈�Ϗ�� != null�̏ꍇ
            if (l_strSettlementStatus != null)
            {
                 //(*1)�w�茈�Ϗ�ԃ`�F�b�N�ȉ��̏����ɊY������ꍇ�A���̗v�f�֏������ڍs����B(continue)
                if (WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(l_strSettlementStatus))
                {
                    //�@@["������"�̏ꍇ]get���Ϗ��()�̖߂�l != ("���ύ�" or "���ύςƖ�����" or"���ύςƖ����ςƌ��ϒ�" or "�����ςƌ��ϒ�")�ł��邱�ƁB
                    if (WEB3IfoSettlementStatusDef.UNSETTLED != l_intStatus 
                            && WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING !=l_intStatus 
                            && WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED != l_intStatus 
                            && WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING != l_intStatus)
                    {
                        continue;
                    }
                }

                    
                //["���ϒ�"�̏ꍇ]get���Ϗ��()�̖߂�l != ("���ϒ�" or "���ύςƌ��ϒ�" or
                //"���ύςƖ����ςƌ��ϒ�" or "�����ςƌ��ϒ�")�ł���
                if (WEB3IfoSettlementStateDef.SETTLING.equals(l_strSettlementStatus))
                {
                    if (WEB3IfoSettlementStatusDef.SETTLING != l_intStatus 
                            && WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING != l_intStatus
                            && WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING != l_intStatus
                            && WEB3IfoSettlementStatusDef.SETTLED_SETTLING != l_intStatus)
                    {
                        continue;
                    }
                }               
                
            }
            if (l_strSettlementStatus == null || WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(l_strSettlementStatus))
            {
                //(*2)�����ϖ��ׂ̍쐬
                if (WEB3IfoSettlementStatusDef.UNSETTLED == l_intStatus 
                        || WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING ==l_intStatus 
                        || WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED == l_intStatus 
                        || WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING == l_intStatus)
                {
                    //�����ς�1���ׂ��쐬����B
                    l_referenceDetailUnit = this.createIfoUnSettledBalanceReferenceDetailUnit(l_subAccount,l_contractImpl);
                    //ArrayList�Ɏc���Ɖ�ׂ�ǉ�����B
                    l_lstReferenceDetailUnit.add(l_referenceDetailUnit);
                }
            }
            if (l_strSettlementStatus == null || WEB3IfoSettlementStateDef.SETTLING.equals(l_strSettlementStatus))
            {
                //(*3)���ϒ����ׂ̍쐬
                if (WEB3IfoSettlementStatusDef.SETTLING == l_intStatus 
                        || WEB3IfoSettlementStatusDef.UNSETTLED_SETTLING == l_intStatus
                        || WEB3IfoSettlementStatusDef.SETTLED_UNSETTLED_SETTLING == l_intStatus
                        || WEB3IfoSettlementStatusDef.SETTLED_SETTLING == l_intStatus)
                {
                    //���ϒ���1���ׂ��쐬����B
                    l_referenceDetailUnit = this.createIfoSettlingBalanceReferenceDetailUnit(l_subAccount,l_contractImpl);
                    //ArrayList�Ɏc���Ɖ�ׂ�ǉ�����B
                    l_lstReferenceDetailUnit.add(l_referenceDetailUnit);
                }
            }
                        
        }
            
        //�c���Ɖ�ׂ̔z���ԋp����B
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[l_lstReferenceDetailUnit.size()];
        l_lstReferenceDetailUnit.toArray(l_detailUnit);
            
        //toArray()�̖߂�l.length == 0�̏ꍇ�Anull��ԋp����B
        if (l_detailUnit.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_detailUnit;
    }
   
    /**
     * (create�敨OP���ϒ��c���Ɖ��)<BR>
     * ���ϒ��̎c���Ɖ�ׂ��쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�敨OP�c��)create�敨OP���ϒ��c���Ɖ�ׁv<BR>
     * �Q��<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_ifoContract - �敨OP����
     * @@return WEB3FuturesOptionsDetailUnit
     * @@roseuid 41AC32620271
     */
    public WEB3FuturesOptionsDetailUnit createIfoSettlingBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoBalanceReferenceDetailUnit()";
        log.entering(STR_METHOD_NAME);
        //�敨OP�c���Ɖ�׃C���X�^���X�𐶐�����B
        if (l_subAccount == null || l_ifoContract == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = new WEB3FuturesOptionsDetailUnit();
        try
        {
            //�敨OP�������擾����B
            WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_ifoContract.getProduct();
            //�敨OP����������擾����B
            WEB3IfoTradedProductImpl l_tradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoContract.getTradedProduct();
            //�w�茚�ʂ̎��������擾����B
            WEB3IfoProductQuote l_currentInfo = this.getContractCurrentInfo((WEB3GentradeSubAccount)l_subAccount,l_ifoContract);
            //���ϒ��������ʂ��擾����B
            double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();
            //����������擾����B
            double l_dblExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblLockedQuantity);
            //���萔�����擾����B
            double l_dblCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);
            BigDecimal l_bdCommission = new BigDecimal(l_dblCommission + "");
            //���萔������ł��擾����B
            double l_dblConsumptionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
            BigDecimal l_bdConsumptionTax = new BigDecimal(l_dblConsumptionTax + "");
            //���萔���{���萔�������
            BigDecimal l_bdCost = l_bdCommission.add(l_bdConsumptionTax);
            double l_dblCost = l_bdCost.doubleValue();
            
            IfoContractRow l_contractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
            //����ID���擾����B
            long l_lngContractId = l_contractRow.getContractId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            //�w�茚�ʂɊY������ԍώw����̈ꗗ���擾����B
            IfoClosingContractSpec[] l_contractSpec = this.getClosingContractSpecs(l_lngContractId);
            
            double l_dblIncome = 0D;
            BigDecimal l_bdIncome = new BigDecimal("0");
            
            if (l_contractSpec != null)
            {
                int l_intContractSpecLength = l_contractSpec.length;
                //�擾�����ԍώw����v�f���Ƃ�Loop����
                for (int i = 0; i < l_intContractSpecLength; i++)
                {
                    //�����P��ID���擾����B
                    long l_lngOrderUnitId = l_contractSpec[i].getOrderUnitId();
                    //�����P�ʂ��擾����B
                    IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
                    OrderOpenStatusEnum l_openStatus = l_orderUnit.getOrderOpenStatus();
                    
                    //�s��m�F�ϕԍϐ��ʂ��擾����
                    //�s��m�F�ϕԍϐ��� == null�̏ꍇ�A�������ʂƂ��ĕԍϒ������ʂ��g�p����
                    IfoClosingContractSpecRow l_specRow =
                        (IfoClosingContractSpecRow) l_contractSpec[i].getDataSourceObject();
                    double l_dblQuantity = 0D;
                    if (!l_specRow.getConfirmedQuantityIsNull())
                    {
                        //�������ʁF���ʕԍώw����.�s��m�F�ϕԍϐ���
                        l_dblQuantity = l_specRow.getConfirmedQuantity();
                    }
                    else
                    {
                        //�������ʁF���ʕԍώw����.�ԍϒ�������
                        l_dblQuantity = l_specRow.getQuantity();
                    }

                    //�ΏۊO�����`�F�b�N
                    //�ȉ��̂����ꂩ�ɓ��Ă͂܂�ꍇ�́A�ΏۊO�����Ƃ��Ď��̗v�f�ɏ������ڍs����B
                    //�E�敨OP�ԍώw����.getConfirmedQuantity() == 0
                    //�E�����P��.getOrderOpenStatusEnum == "�N���[�Y"
                    
                    if (l_dblQuantity == 0 || OrderOpenStatusEnum.CLOSED.equals(l_openStatus))
                    {
                        continue;
                    }

                    double l_dblPrice = 0;

                    //�������擾����
                    if (l_currentInfo != null)
                    {
                        l_dblPrice = l_currentInfo.getCurrentPrice();
                    }

                    double l_dblExecutedQuantity = l_contractSpec[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0;
                    }

                    //�]�����v���擾����B
                    double l_dblEvaluateIncome =
                        l_ifoContract.getEvaluateIncome(
                            l_dblPrice,
                            l_dblQuantity - l_dblExecutedQuantity);

                    l_bdIncome =
                        l_bdIncome.add(new BigDecimal(l_dblEvaluateIncome + ""));

                    l_dblIncome = l_bdIncome.doubleValue();
                }
                
                //�c���Ɖ��.ID �� �敨OP����.getContractId()
                l_referenceDetailUnit.id = ""+ l_lngContractId;
                //�c���Ɖ��.�����R�[�h �� �敨OP����.get�����R�[�h()
                l_referenceDetailUnit.productCode = l_productImpl.getProductCode();
                IfoProductRow l_productRow = (IfoProductRow) l_productImpl.getDataSourceObject();
                //�c���Ɖ��.������ �� �敨OP����.get������()
                l_referenceDetailUnit.productName = l_productRow.getStandardName();
                //�c���Ɖ��.�w����� �� �敨OP����.get�����Y�����R�[�h()
                l_referenceDetailUnit.targetProductCode = l_productRow.getUnderlyingProductCode();
                //�c���Ɖ��.���� �� �敨OP����.get����()
                l_referenceDetailUnit.delivaryMonth = l_productRow.getMonthOfDelivery();
                //�c���Ɖ��.�I�v�V�������i�敪  ���@@�敨OP����.get�敨�I�v�V�������i()
                String l_strProductType = null;
                IfoDerivativeTypeEnum l_delivativeTypeEnum = l_productRow.getDerivativeType();
                if (IfoDerivativeTypeEnum.FUTURES.equals(l_delivativeTypeEnum))
                {
                    l_strProductType = WEB3IfoProductTypeDef.FUTURES;
                }
                else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_delivativeTypeEnum))
                {
                    l_strProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_delivativeTypeEnum))
                {
                    l_strProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                }
                l_referenceDetailUnit.opProductType = l_strProductType;
                
                //�c���Ɖ��.�s�g���i       ���@@�敨OP����.get�s�g���i()
                l_referenceDetailUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_productRow.getStrikePrice());
                //�c���Ɖ��.����s��       ���@@�敨OP����.�s��ID�ɊY������s��R�[�h
                WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoContract.getMarketId());
                l_referenceDetailUnit.marketCode = l_market.getMarketCode();
                //�c���Ɖ��.���敪        ���@@�敨OP����.get���敪() 
                if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
                {
                    l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
                }
                else
                {
                    l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
                }
                //�c���Ɖ��.����         ���@@�敨OP����.getOpenDate()
                l_referenceDetailUnit.openDate = WEB3DateUtility.toDay(l_contractRow.getOpenDate());
                //�c���Ɖ��.������        ���@@getQuantity()�̖߂�l - getLockedQuantity()�̖߂�l
                l_referenceDetailUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblLockedQuantity);
                //�c���Ɖ��.���P��        ���@@�敨OP����.getContractPrice() 
                l_referenceDetailUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractPrice());
                //�c���Ɖ��.���Ϗ�ԋ敪       ���@@"2�F���ϒ�"
                l_referenceDetailUnit.settlementState = WEB3IfoSettlementStateDef.SETTLING;
                //�c���Ɖ��.�������z      ���@@get�������z()�̖߂�l
                l_referenceDetailUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
                //�c���Ɖ��.���萔��       ���@@get���萔��()�̖߂�l + get���萔�������()�̖߂�l
                l_referenceDetailUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
                //�c���Ɖ��.����ŏI��      ���@@�敨OP�������.get�����ŏI��()
                l_referenceDetailUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedProductImpl.getLastTradingDate());
                if (l_currentInfo != null)
                {
                    //�c���Ɖ��.����         ���@@get���ʎ������()�̖߂�l.����
                    l_referenceDetailUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_currentInfo.getCurrentPrice());
                    //�c���Ɖ��.�O����        ���@@get���ʎ������()�̖߂�l.�O����
                    l_referenceDetailUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_currentInfo.getComparedPreviousDay());
                    //�c���Ɖ��.�����擾����     ���@@get���ʎ������()�̖߂�l.�����擾����
                    l_referenceDetailUnit.currentPriceTime = WEB3DateUtility.formatDate(l_currentInfo.getCurrentPriceTime(),"HH:mm");
                    //�c���Ɖ��.���v         ���@@get�]�����v()�̖߂�l
                    l_referenceDetailUnit.income = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                    //�c���Ɖ��.���v(���o�)   ���@@get�]�����v(���o�)�̖߂�l
                    l_referenceDetailUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdCost).doubleValue());
                }
                else
                {
                    //�c���Ɖ��.����         ���@@null
                    l_referenceDetailUnit.currentPrice = null;
                    //�c���Ɖ��.�O����        ���@@null
                    l_referenceDetailUnit.comparedPreviousDay = null;
                    //�c���Ɖ��.�����擾����     ���@@null
                    l_referenceDetailUnit.currentPriceTime = null;
                    //�c���Ɖ��.���v         ���@@null
                    l_referenceDetailUnit.income = null;
                    //�c���Ɖ��.���v(���o�)   ���@@null
                    l_referenceDetailUnit.incomeCost = null;
                }
                //�c���Ɖ��.����敪   ���@@�敨OP����.����敪
                l_referenceDetailUnit.sessionType = l_contractRow.getSessionType();
                log.exiting(STR_METHOD_NAME);

            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }    
        return l_referenceDetailUnit;
    }
   
    /**
     * (create�敨OP�����ώc���Ɖ��)<BR>
     * �����ς̎c���Ɖ�ׂ��쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�敨OP�c��)create�敨OP�����ώc���Ɖ�ׁv<BR>
     * �Q��<BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_ifoContract - �敨OP����
     * @@return WEB3FuturesOptionsDetailUnit
     * @@roseuid 41AC32620261
     */
    public WEB3FuturesOptionsDetailUnit createIfoUnSettledBalanceReferenceDetailUnit(WEB3GentradeSubAccount l_subAccount, WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoUnSettledBalanceReferenceDetailUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ifoContract == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //�敨OP�c���Ɖ�׃C���X�^���X�𐶐�����B
        WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = new WEB3FuturesOptionsDetailUnit();
        try
        {
            //�敨OP�������擾����B
            WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_ifoContract.getProduct();
            //�敨OP����������擾����B
            WEB3IfoTradedProductImpl l_tradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoContract.getTradedProduct();
            //�w�茚�ʂ̎��������擾����B
            WEB3IfoProductQuote l_currentInfo = this.getContractCurrentInfo(l_subAccount,l_ifoContract);
            //���ʐ��ʂ��擾����B
            double l_dblQuantity = l_ifoContract.getQuantity();
            //���ϒ��������ʂ��擾����B
            double l_dblLockedQuantity = l_ifoContract.getLockedQuantity();
            //����������擾����B
            double l_dblExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblQuantity - l_dblLockedQuantity);
            // ���ϒ��̌��萔�����擾����B
            double l_dblLockedContractCommission = l_ifoContract.getContractCommission(l_dblLockedQuantity);
            BigDecimal l_bdLockedContractCommission = new BigDecimal(l_dblLockedContractCommission + "");
            // ���ϒ��̌��萔������ł��擾����B
            double l_dblLockedContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(l_dblLockedQuantity);
            BigDecimal l_bdLockedContractCommissionTax = new BigDecimal(l_dblLockedContractCommissionTax + "");
            // ���萔����(�����ρ{���ϒ��̌��萔��)�|���ϒ��̌��萔��
            BigDecimal l_bdSetupFee = new BigDecimal(l_ifoContract.getSetupFee() + "");
            BigDecimal l_bdContractCommission = l_bdSetupFee.subtract(l_bdLockedContractCommission);
            // ���萔������Ł�(�����ρ{���ϒ��̌��萔�������)�|���ϒ��̌��萔�������
            BigDecimal l_bdSetupFeeTax = new BigDecimal(l_ifoContract.getSetupFeeTax() + "");
            BigDecimal l_bdContractCommissionTax = l_bdSetupFeeTax.subtract(l_bdLockedContractCommissionTax);
            // ���萔���{���萔�������
            BigDecimal l_bdCost = l_bdContractCommission.add(l_bdContractCommissionTax);
            double l_dblCost = l_bdCost.doubleValue();
            
            IfoContractRow l_contractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
            //����ID���擾����B
            long l_lngContractId = l_contractRow.getContractId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            double l_dblIncome = 0D;
            BigDecimal l_bdIncome = new BigDecimal("0");
              
            //�]�����v���擾����B
            if (l_currentInfo != null)
            {
                double l_dblEvaluateIncome =
                    l_ifoContract.getEvaluateIncome(
                        l_currentInfo.getCurrentPrice(),
                        l_dblQuantity - l_dblLockedQuantity);

                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");

                l_bdIncome = l_bdIncome.add(l_bdEvaluateIncome);
                l_dblIncome = l_bdIncome.doubleValue();
            }
            else
            {
                double l_dblEvaluateIncome =
                    l_ifoContract.getEvaluateIncome(
                        0,
                        l_dblQuantity - l_dblLockedQuantity);

                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");

                l_bdIncome = l_bdIncome.add(l_bdEvaluateIncome);
                l_dblIncome = l_bdIncome.doubleValue();           
            }

            
            //�c���Ɖ��.ID �� �敨OP����.getContractId()
            l_referenceDetailUnit.id = ""+ l_lngContractId;
            //�c���Ɖ��.�����R�[�h �� �敨OP����.get�����R�[�h()
            l_referenceDetailUnit.productCode = l_productImpl.getProductCode();
            IfoProductRow l_productRow = (IfoProductRow) l_productImpl.getDataSourceObject();
            //�c���Ɖ��.������ �� �敨OP����.get������()
            l_referenceDetailUnit.productName = l_productRow.getStandardName();
            //�c���Ɖ��.�w����� �� �敨OP����.get�����Y�����R�[�h()
            l_referenceDetailUnit.targetProductCode = l_productRow.getUnderlyingProductCode();
            //�c���Ɖ��.���� �� �敨OP����.get����()
            l_referenceDetailUnit.delivaryMonth = l_productRow.getMonthOfDelivery();
            //�c���Ɖ��.�I�v�V�������i�敪  ���@@�敨OP����.get�敨�I�v�V�������i()
            String l_strProductType = null;
            IfoDerivativeTypeEnum l_delivativeTypeEnum = l_productRow.getDerivativeType();
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_delivativeTypeEnum))
            {
                l_strProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_delivativeTypeEnum))
            {
                l_strProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_delivativeTypeEnum))
            {
                l_strProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            l_referenceDetailUnit.opProductType = l_strProductType;
            
            //�c���Ɖ��.�s�g���i       ���@@�敨OP����.get�s�g���i()
            l_referenceDetailUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_productRow.getStrikePrice());
            //�c���Ɖ��.����s��       ���@@�敨OP����.�s��ID�ɊY������s��R�[�h
            WEB3GentradeFinObjectManager l_finOjbectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoContract.getMarketId());
            l_referenceDetailUnit.marketCode = l_market.getMarketCode();
            //�c���Ɖ��.���敪        ���@@�敨OP����.get���敪() 
            if (ContractTypeEnum.LONG.equals(l_contractRow.getContractType()))
            {
                l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_BUY;
            }
            else
            {
                l_referenceDetailUnit.contractType = WEB3IfoContractTypeDef.OPEN_SELL;
            }
            //�c���Ɖ��.����         ���@@�敨OP����.getOpenDate()
            l_referenceDetailUnit.openDate = WEB3DateUtility.toDay(l_contractRow.getOpenDate());
            //�c���Ɖ��.������        ���@@getQuantity()�̖߂�l - getLockedQuantity()�̖߂�l
            l_referenceDetailUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity - l_dblLockedQuantity);
            //�c���Ɖ��.���P��        ���@@�敨OP����.getContractPrice() 
            l_referenceDetailUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractPrice());
            //�c���Ɖ��.���Ϗ�ԋ敪       ���@@"1�F������"
            l_referenceDetailUnit.settlementState = WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED;
            //�c���Ɖ��.�������z      ���@@get�������z()�̖߂�l
            l_referenceDetailUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_dblExecutedAmount);
            //�c���Ɖ��.���萔��       ���@@get���萔��()�̖߂�l + get���萔�������()�̖߂�l
            l_referenceDetailUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblCost);
            //�c���Ɖ��.����ŏI��      ���@@�敨OP�������.get�����ŏI��()
            l_referenceDetailUnit.lastTradingDate = WEB3DateUtility.toDay(l_tradedProductImpl.getLastTradingDate());            
            if (l_currentInfo != null)
            {
                //�c���Ɖ��.����         ���@@get���ʎ������()�̖߂�l.����
                l_referenceDetailUnit.currentPrice = WEB3StringTypeUtility.formatNumber(l_currentInfo.getCurrentPrice());
                //�c���Ɖ��.�O����        ���@@get���ʎ������()�̖߂�l.�O����
                l_referenceDetailUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_currentInfo.getComparedPreviousDay());
                //�c���Ɖ��.�����擾����     ���@@get���ʎ������()�̖߂�l.�����擾����
                l_referenceDetailUnit.currentPriceTime = WEB3DateUtility.formatDate(l_currentInfo.getCurrentPriceTime(),"HH:mm");
                //�c���Ɖ��.���v         ���@@get�]�����v()�̖߂�l
                l_referenceDetailUnit.income = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                //�c���Ɖ��.���v(���o�)   ���@@get�]�����v(���o�)�̖߂�l
                l_referenceDetailUnit.incomeCost = WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdCost).doubleValue());
            }
            else
            {
                //�c���Ɖ��.����         ���@@null
                l_referenceDetailUnit.currentPrice = null;
                //�c���Ɖ��.�O����        ���@@null
                l_referenceDetailUnit.comparedPreviousDay = null;
                //�c���Ɖ��.�����擾����     ���@@null
                l_referenceDetailUnit.currentPriceTime = null;
                //�c���Ɖ��.���v         ���@@null
                l_referenceDetailUnit.income = null;
                //�c���Ɖ��.���v(���o�)   ���@@null
                l_referenceDetailUnit.incomeCost = null;
            }
            //�c���Ɖ��.����敪   ���@@�敨OP����.����敪
            l_referenceDetailUnit.sessionType = l_contractRow.getSessionType();

            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + "." + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }    
        return l_referenceDetailUnit;
    }
   
    /**
     * (get���ʎ������)<BR>
     * �w�茚�ʂ̖����̎������(�����A�����擾���ԂȂ�)���擾����B<BR>
     * <BR>
     * ���L�菇�Ŏ擾������������ԋp����B<BR>
     * <BR>
     * �P�j�����R�[�h�̎擾<BR>
     * �@@�敨OP���� = �p�����[�^.�敨OP����.getProduct()<BR>
     * �@@�����R�[�h = �敨OP����.get�����R�[�h()<BR>
     * <BR>
     * �Q�j�������Z�b�g���肨��ю������̎擾<BR>
     * �@@ThreadLocalSystemAttributesRegistry.<BR>
     * �@@getAttribute(CURRENT_PRICE_INFO)��Hashtable���擾<BR>
     * <BR>
     * �@@�Q�|�P�j�Y�������̎�����񂪃Z�b�g����Ă���ꍇ<BR>
     * �@@�@@�@@(Hashtable.get(�����R�[�h) != null�̏ꍇ)<BR>
     * <BR>
     *         ������� = Hashtable.get(�����R�[�h)<BR>
     * <BR>
     * �@@�Q�|�Q�j�Y�������̎�����񂪃Z�b�g����Ă��Ȃ��ꍇ<BR>
     * �@@�@@�@@(Hashtable.get(�����R�[�h) == null�̏ꍇ)<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�������̎擾<BR>
     * �@@�@@�@@�@@�敨OP������� = �p�����[�^.�敨OP����.getTradedProduct()<BR>
     * �@@�@@�@@�@@������� = �敨OP�������.get�������(�p�����[�^.�⏕����)<BR>
     * �@@�@@<BR>
     * �@@�@@�Q�|�Q�|�Q�j�������̒ǉ�<BR>
     * �@@�@@�@@�@@�擾����Hashtable�ɊY�������R�[�h�Ǝ�������ǉ�<BR>
     * �@@�@@�@@�@@Hashtable.put�i�����R�[�h�A �Q�|�Q�|�P�j�ɂĎ擾�����������)<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�R�j�����̃Z�b�g<BR>
     * �@@�@@�@@�@@ThreadLocalSystemAttributesRegistry.setAttribute()�ɂĎ��������Z�b�g��
     * ��<BR>
     * �@@�@@�@@�@@�ݒ�L�[�F�@@CURRENT_PRICE_INFO<BR>
     * �@@�@@�@@�@@�l�F�@@�Q�|�Q�|�Q�j�ɂĎ�������ǉ�����Hashtable<BR>
     * <BR>
     * �R�j�擾������������ԋp<BR>
     * <BR>
     * �����Y���\�b�h���g�p����ꍇ�́A�e�T�[�r�X�C���^�Z�v�^��<BR>
     * onCall�ɂĎ������̃Z�b�g����(*)�A<BR>
     * �@@onReturn()�����onThrowable()���\�b�h���ɂĎ������̃N���A�������s������<BR>
     * <BR>
     * (*)ThreadLocalSystemAttributesRegistry.setAttribute()�ɂ�<BR>
     * �@@ThreadLocal�Ɏ������̕ϐ����Z�b�g����<BR>
     * �@@�ݒ�L�[�F�@@CURRENT_PRICE_INFO<BR>
     * �@@�l�F�@@Hashtable(�V�K�ɍ쐬����Hashtable)<BR>
     * <BR>
     * @@param l_subAccount - �⏕�����I�u�W�F�N�g
     * @@param l_ifoContract - �敨OP����
     * @@return webbroker3.ifo.WEB3IfoCurrentInfo
     * @@roseuid 41AC32620290
     */
    public WEB3IfoProductQuote getContractCurrentInfo(WEB3GentradeSubAccount l_subAccount, WEB3IfoContractImpl l_ifoContract)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getContractCurrentInfo()";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ifoContract == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        //�敨OP�������擾����B
        WEB3IfoProductImpl l_productImpl = (WEB3IfoProductImpl)l_ifoContract.getProduct();
        //�����R�[�h���擾����B
        String l_strProductCode = l_productImpl.getProductCode();
        
        // �Q�j�������Z�b�g���肨��ю������̎擾
        WEB3IfoProductQuote l_ifoCurrentInfo = null;

        Hashtable l_htCurrentPrices = 
            (Hashtable) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO);

        //(�Y�������̎�����񂪃Z�b�g����Ă���ꍇ(Hashtable.get(�����R�[�h) != null�̏ꍇ)
        if (l_htCurrentPrices != null && l_htCurrentPrices.containsKey(l_strProductCode))
        {
            //������� = Hashtable.get(�����R�[�h)
            l_ifoCurrentInfo = (WEB3IfoProductQuote)l_htCurrentPrices.get(l_strProductCode);
        }
        //�Y�������̎�����񂪃Z�b�g����Ă��Ȃ��ꍇ(Hashtable.get(�����R�[�h) == null�̏ꍇ)
        else
            // (3-2)�Y�������̎������Z�b�g����Ă��Ȃ��ꍇ
            {
            // (3-2-1)�����̎擾
            if (l_htCurrentPrices == null)
            {
                l_htCurrentPrices = new Hashtable();
            }

            WEB3IfoTradedProductImpl l_tradeProduct = null;
            //�敨OP������� = �p�����[�^.�敨OP����.getTradedProduct()
            l_tradeProduct = (WEB3IfoTradedProductImpl) l_ifoContract.getTradedProduct();
            //������� = �敨OP�������.get�������(�p�����[�^.�⏕����
            l_ifoCurrentInfo = l_tradeProduct.getCurrentInfo(l_subAccount);
            
            if (l_ifoCurrentInfo != null)
            {
                // �������̒ǉ�
                l_htCurrentPrices.put(l_strProductCode, l_ifoCurrentInfo);
                // �����̃Z�b�g
                ThreadLocalSystemAttributesRegistry.setAttribute(WEB3IfoAttributeNameDef.CURRENT_PRICE_INFO, l_htCurrentPrices);
            }
        }
        // �R�j�擾������������ԋp
        log.exiting(STR_METHOD_NAME);
        return l_ifoCurrentInfo;
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �����P��ID�ɊY������ԍό��ʂ̃G���g�����쐬����B<BR>
     *�i�V�[�P���X�}�j�u�i�敨OP�c���jcreate�ԍό��ʃG���g���v�Q��<BR>
     *<BR>
     *=============================================== <BR>
     *�V�[�P���X�} : �i�V�[�P���X�}�j�u�i�敨OP�c���jcreate�ԍό��ʃG���g���v <BR>
     *��̈ʒu     : 1.2 �����P�ʂɊY������ԍώw����̔z����擾����B�A<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�擾�ł��Ȃ������ꍇ�́A�u�Y���f�[�^�Ȃ��v�̗�O��throw����B <BR>
     *class        : WEB3BusinessLayerException <BR>
     *tag          : SYSTEM_ERROR_80005 <BR>
     * =============================================== <BR>
     * <BR>
     * @@param l_lngOrderUnitId - �����P��Id
     * @@return SettleContractEntry[]
     * @@roseuid 4010AF2C0227
     */
    public SettleContractEntry[] createSettleContractEntry(long l_lngOrderUnitId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createSettleContractEntry(long)";
        log.entering(STR_METHOD_NAME);

        //1.1 getOrderUnit(�����P��ID : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = 
            (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        OrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.2 getContractsToClose()
        IfoContractSettleOrderUnit l_ifoContractSettleOrderUnit = 
            (IfoContractSettleOrderUnit)l_orderUnit;
        IfoClosingContractSpec[] l_contractSpecs = 
            l_ifoContractSettleOrderUnit.getContractsToClose();

        //�����P�ʂɊY������ԍώw����̔z����擾����B
        //�擾�ł��Ȃ������ꍇ�́A�u�Y���f�[�^�Ȃ��v�̗�O��throw����B
        if (l_contractSpecs == null || l_contractSpecs.length == 0)
        {
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 ArrayList()
        List l_list = new ArrayList(); 

        //1.4 �ԍώw����̗v�f��LOOP
        for (int i = 0; i < l_contractSpecs.length; i++)
        {
            //1.4.1 getContractId( )(�敨OP�ԍώw����::getContractId)
            long l_lngContractId = l_contractSpecs[i].getContractId();

            //1.4.2 getQuantity( )(�敨OP�ԍώw����::getQuantity)
            double l_dblQuantity = l_contractSpecs[i].getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0;
            }
            
            //1.4.3 SettleContractEntry(long, double)(SettleContractEntry::SettleContractEntry)
            //  [�R���X�g���N�^�̈���] 
            //  ���ʂh�c�F�@@getContractId()�̖߂�l 
            //  ���ʁF�@@getQuantity()�̖߂�l
            SettleContractEntry l_contractEntry = new SettleContractEntry(
                l_lngContractId,
                l_dblQuantity);
                
            //1.4.4 add(arg0 : int, arg1 : Object)
            l_list.add(l_contractEntry);
        }
        
        //1.5 toArray()
        SettleContractEntry[] l_settleContractEntrys = new SettleContractEntry[l_contractSpecs.length];
        l_list.toArray(l_settleContractEntrys);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractEntrys;
    }
    
    
	/**
	  * (update���b�N������)<BR>
	  * <BR>
	  * �iupdateLockedQuantity�̃I�[�o�[���C�h�j<BR>
	  * <BR>
	  * ���b�N�����ʂ��X�V����B<BR>
	  * <BR>
	  * @@param l_lngOrderUnitId - �����P��ID<BR>
	  * @@param l_contract - ����<BR>
	  * @@param l_dblLockedQtyToBeAdjusted - ���b�N��<BR>
	  * @@throws com.fitechlabs.xtrade.kernel.data.DataException
	  * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException
	  */
	public void updateLockedQuantity(long l_lngOrderUnitId, Contract l_contract, double l_dblLockedQtyToBeAdjusted)
		throws RuntimeSystemException
	{
		super.updateLockedQuantity(l_lngOrderUnitId, l_contract, l_dblLockedQtyToBeAdjusted);
		
		if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(l_dblLockedQtyToBeAdjusted))
		{
			return;
		}
			
		//ThreadLocal�ɐݒ肳��Ă���V�X�e���������Ԃ��擾����			
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
			
		//�V�X�e���������Ԃ��ݒ肳��Ă���ꍇ�̂݁A�X�V���t�̍X�V���s��
		if(l_realTimestamp != null)
		{
			long contractId = l_contract.getContractId();
			IfoLockedContractDetailsParams lockedContractParams = null;
			try{
    			QueryProcessor qp = Processors.getDefaultProcessor();
				IfoLockedContractDetailsRow lockedContractRow = IfoLockedContractDetailsDao.findRowByPk(contractId);
				lockedContractParams = new IfoLockedContractDetailsParams(lockedContractRow);
    			//�X�V���t��ݒ肷��
    			lockedContractParams.setLastUpdatedTimestamp(l_realTimestamp);
				qp.doUpdateQuery(lockedContractParams);
   			}
			catch(DataException de)
			{
				String msg = "DataException while updating Ifo_locked_contract_details for assetId:" + contractId;
				throw new RuntimeSystemException(msg, de);
			}
		}
	}

    /**
      * (get����ListBy�����P��)<BR>
      * <BR>
      *�@@�i�w�肳�ꂽ�����f�[�^�ɑ΂���A���ʃf�[�^��S�Ď擾���A�j<BR>
      * �@@����Params��List���쐬���ĕԂ��B<BR>
      * <BR>
      * �@@�敨OP�|�W�V�����w���p�[.�敨OP�X�V�f�[�^�}�l�[�W��.<BR>
      * �@@get����ListBy�����P��(�����̒���ID)��delegate����B<BR>
      * <BR>
      *
      * @@param l_lngOrderId - ����ID<BR>
      * (����ID)<BR>
      * @@return List
      * @@throws WEB3BaseException
      */
    public List getContractListByOrderUnit(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractListByOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        WEB3IfoPersistentDataManager l_persistentDataManager = 
            new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO).new WEB3IfoPersistentDataManager();

        log.exiting(STR_METHOD_NAME);
        return l_persistentDataManager.getContractListByOrderUnit(l_lngOrderId);
    }

    /**
     * (get������薾��ListBy�����P��Plus����)<BR>
     * <BR>
     * �@@�w�肳�ꂽ�����f�[�^�{���ʃf�[�^�ɑ΂���A<BR>
     * �@@������v�Z�ΏۂƂȂ������薾��Params��List���擾����B<BR>
     * <BR>
     * �敨OP�|�W�V�����w���p�[.�敨OP�X�V�f�[�^�}�l�[�W��.<BR>
     * get������薾��ListBy�����P��Plus����()��delegate����B<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@param l_lngContractId - (����ID)<BR>
     * ����ID
     * @@return List
     * @@throws WEB3BaseException
     */
    public List getTransactionsListByOrderUnitPlusContract(long l_lngOrderUnitId, long l_lngContractId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTransactionsListByOrderUnitPlusContract(long, long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoPersistentDataManager l_persistentDataManager = 
            new WEB3IfoPositionManagerHelper(ProductTypeEnum.IFO).new WEB3IfoPersistentDataManager();

        log.exiting(STR_METHOD_NAME);
        return l_persistentDataManager.getTransactionsListByOrderUnitPlusContract(
            l_lngOrderUnitId, l_lngContractId);
    }
}
@
