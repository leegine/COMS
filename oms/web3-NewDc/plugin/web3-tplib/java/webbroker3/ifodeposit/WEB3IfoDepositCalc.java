head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalc.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����v�Z(WEB3IfoDepositCalc.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 nakazato(ACT) �V�K�쐬
Revesion History : 2007/07/06 hijikata(SRA) �[��Ή��@@
                      ���f��No.060,No.063,No.065,No.072,No.075,No.078,No.079,No.088,No.092,No.93         
                      �v�Z����No.022,No.023,No.025,No.027,No.028,No.029,No.032
Revesion History : 2007/08/02 hijikata(SRA) �[��Ή��@@���f��No.095, No.101, No.103, No.106
Revesion History : 2007/08/07 k.yamashita(SRA) �[��Ή��@@U03038, U03039, U03040, U03042
Revesion History : 2007/08/10 k.yamashita(SRA) �[��Ή��@@�v�Z���� No.039
Revision History : 2007/08/13 k.yamashita(SRA) �[��Ή� U03048,U03049
Revision History : 2007/10/18 k.yamashita(SRA)  ���捞�v��No.014,021
*/
package webbroker3.ifodeposit;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSpanConnectService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTotalContractSpec;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.ifodeposit.define.WEB3IfoDepositBranchPrefNameDef;
import webbroker3.ifodeposit.define.WEB3IfoDepositPriceScanMultiplyDef;
import webbroker3.ifodeposit.define.WEB3IfoDepositUnderlyingProductCodeDef;
import webbroker3.ifodeposit.define.WEB3IfoPositionBalanceTypeDef;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�؋����v�Z)
 * �؋����v�Z���s���N���X�B
 */
public class WEB3IfoDepositCalc
{

    private static final WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalc.class);

    /**
     * (�⏕����)
     */
    private WEB3GentradeSubAccount subAccount;

    /**
     * (�؋����v�Z����)
     */
    private WEB3IfoDepositCalcCondition ifoDepositCalcCondition;

    /**
     * (�������P���ʐ敨OP���ʏW�v�ꗗ)
     */
    private WEB3IfoSummaryContractPerProductContractPrice ifoSummaryContractPerProductContractPriceList[];

    /**
     * (�����ʐ敨OP���ʏW�v�ꗗ)
     */
    private WEB3IfoSummaryContractPerProduct ifoSummaryContractPerProductList[];

    /**
     * (�w���ʐ敨OP���ʏW�v�ꗗ)
     */
    private WEB3IfoSummaryContractPerIndex ifoSummaryContractPerIndexList[];

    /**
     * (�敨OP�ڋq�ړ�����)
     */
    private WEB3IfoCustomerTransfer ifoCustomerTransfer;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3IfoDepositCalc()
    {

    }

    /**
     * (�؋����v�Z)<BR>
     * 
     * �V�K���]�̓`�F�b�N���Ɏg�p����R���X�g���N�^�B<BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�j�R���X�g���N�^�i�V�K���]�̓`�F�b�N���j�v�Q�ƁB<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_ifoDepositCalcCondition - (�؋����v�Z����)<BR>
     * �؋����v�Z�����I�u�W�F�N�g�B<BR>
     * @@param l_ifoNewOrderSpec - �敨OP���������e�B
     */
    public WEB3IfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition,
        WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
        throws WEB3SystemLayerException, WEB3BaseException
    {
        //this.�⏕�����ɁA����.�⏕�������Z�b�g
        this.subAccount = l_subAccount;

        //this,.�؋����v�Z�����ɁA����.�؋����v�Z�������Z�b�g
        this.ifoDepositCalcCondition = l_ifoDepositCalcCondition;

        //this.create�敨OP�ۗL���ʏ��ꗗ()���R�[��
        WEB3IfoContract[] l_ifoContract = this.createIfoContractList();

        //this.create�敨OP�����V�K���������ꗗ()���R�[��
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrder =
            this.createIfoTodayOpenOrderList(l_ifoNewOrderSpec);

        //this.create�敨OP�ڋq�ړ�����()���R�[��
        this.createIfoCustomerTransfer(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create�������Params�ꗗ()���R�[��
        IfoTradedProductParams[] l_ifoTradedProductParams =
            this.createTradedProductParamsList(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create�敨OP�������ꗗ()���R�[��
        WEB3IfoProduct[] l_ifoProduct = this.createIfoProductList(l_ifoTradedProductParams);

        //this.create�������P���ʐ敨OP���ʏW�v�ꗗ()���R�[��
        this.createIfoSummaryContractPerProductContractPriceList(l_ifoContract, l_ifoProduct);

        //this.create�����ʐ敨OP���ʏW�v�ꗗ()���R�[��
        this.createIfoSummaryContractPerProductList(
            l_ifoContract,
            l_ifoTodayOpenOrder,
            l_ifoProduct);

        //this.create�w���ʐ敨OP���ʏW�v�ꗗ()���R�[��()
        this.createIfoSummaryContractPerIndexList(l_ifoContract, l_ifoTodayOpenOrder, l_ifoProduct);

        //���O�o��
        this.printLog();
        //---------------Debug Log Add Start
        if (l_ifoContract == null) {
            log.debug("ifoContract == null");
        } else {
            for (int i = 0; i < l_ifoContract.length; i++){
                log.debug("ifoContract[" + i +"]" + l_ifoContract[i].toString());      	
            }
        }
        if (l_ifoProduct == null) {
            log.debug("ifoProduct == null");
        } else {
            for (int i = 0; i < l_ifoProduct.length; i++){
                log.debug("ifoProduct[" + i +"]" + l_ifoProduct[i].toString());      	
            }
        }
        if (l_ifoTodayOpenOrder == null) {
            log.debug("ifoTodayOpenOrder == null");
        } else {
            for (int i = 0; i < l_ifoTodayOpenOrder.length; i++){
                log.debug("ifoTodayOpenOrder[" + i +"]" + l_ifoTodayOpenOrder[i].toString());      	
            }
        }
        //---------------Debug Log Add End
    }

    /**
     * (�؋����v�Z)<BR>
     * �R���X�g���N�^�B<BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�j�R���X�g���N�^�v�Q�ƁB<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param ifoDepositCalcCondition - (�؋����v�Z����)<BR>
     * �؋����v�Z�����I�u�W�F�N�g�B<BR>
     */
    public WEB3IfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
        throws WEB3SystemLayerException, WEB3BaseException
    {
        //this.�⏕�����ɁA����.�⏕�������Z�b�g
        this.subAccount = l_subAccount;

        //this,.�؋����v�Z�����ɁA����.�؋����v�Z�������Z�b�g
        this.ifoDepositCalcCondition = l_ifoDepositCalcCondition;

        //this.create�敨OP�ۗL���ʏ��ꗗ()���R�[��
        WEB3IfoContract[] l_ifoContract = this.createIfoContractList();

        //this.create�敨OP�����V�K���������ꗗ()���R�[��
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrder = this.createIfoTodayOpenOrderList();

        //this.create�敨OP�ڋq�ړ�����()���R�[��
        this.createIfoCustomerTransfer(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create�������Params�ꗗ()���R�[��
        IfoTradedProductParams[] l_ifoTradedProductParams =
            this.createTradedProductParamsList(l_ifoContract, l_ifoTodayOpenOrder);

        //this.create�敨OP�������ꗗ()���R�[��
        WEB3IfoProduct[] l_ifoProduct = this.createIfoProductList(l_ifoTradedProductParams);

        //this.create�������P���ʐ敨OP���ʏW�v�ꗗ()���R�[��
        this.createIfoSummaryContractPerProductContractPriceList(l_ifoContract, l_ifoProduct);

        //this.create�����ʐ敨OP���ʏW�v�ꗗ()���R�[��
        this.createIfoSummaryContractPerProductList(
            l_ifoContract,
            l_ifoTodayOpenOrder,
            l_ifoProduct);

        //this.create�w���ʐ敨OP���ʏW�v�ꗗ()���R�[��()
        this.createIfoSummaryContractPerIndexList(l_ifoContract, l_ifoTodayOpenOrder, l_ifoProduct);

        //���O�o��
        this.printLog();
        //---------------Debug Log Add Start
        if (l_ifoContract == null) {
            log.debug("ifoContract == null");
        } else {
            for (int i = 0; i < l_ifoContract.length; i++){
                log.debug("ifoContract[" + i +"]" + l_ifoContract[i].toString());      	
            }
        }
        if (l_ifoProduct == null) {
            log.debug("ifoProduct == null");
        } else {
            for (int i = 0; i < l_ifoProduct.length; i++){
                log.debug("ifoProduct[" + i +"]" + l_ifoProduct[i].toString());      	
            }
        }
        if (l_ifoTodayOpenOrder == null) {
            log.debug("ifoTodayOpenOrder == null");
        } else {
            for (int i = 0; i < l_ifoTodayOpenOrder.length; i++){
                log.debug("ifoTodayOpenOrder[" + i +"]" + l_ifoTodayOpenOrder[i].toString());      	
            }
        }
        //---------------Debug Log Add End
    }

    /**
     * (create�敨OP�����V�K���������ꗗ)<BR>
     * 
     * �敨OP�����V�K���������̈ꗗ���쐬����B<BR>
     * ���Y���̐敨OP�����V�K�����������݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�敨OP�����V�K���������ꗗ�v�Q�ƁB<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder[]
     */
    private WEB3IfoTodayOpenOrder[] createIfoTodayOpenOrderList()
    {
        //�敨OP�����V�K�����������i�[����ArrayList�𐶐�����B
        ArrayList l_ifoTodayOpenOrders = new ArrayList();

        //����ID���擾����
        long l_lngAccountId = this.subAccount.getAccountId();
        //�⏕����ID���擾����
        long l_lngSubAccountId = this.subAccount.getSubAccountId();
        //�c�Ɠ�[T+0]���擾����B
        Date l_datCurrentBizDate = this.ifoDepositCalcCondition.getCurrentBizDate();

        //�����V�K�������P��Params�ꗗ���擾����
        IfoOrderUnitParams[] l_ifoOrderUnitParams =
            WEB3IfoDepositPersistentDataManager.getTodayOpenOrderUnitParamsList(
                l_lngAccountId,
                l_lngSubAccountId,
                l_datCurrentBizDate);

        //�擾���������V�K�������P��Params == null�̎�
        if (l_ifoOrderUnitParams == null)
        {
            //null��ԋp����
            return null;
        }

        //�擾���������V�K�������P��Params�ꗗ�̗v�f����LOOP����
        for (int index = 0; index < l_ifoOrderUnitParams.length; index++)
        {
            //�擾���������V�K�������P��Params�ꗗ���敨OP�����V�K�����𐶐�����
            WEB3IfoTodayOpenOrder l_ifoCurTodayOpenOrder =
                WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams[index]);

            //���������敨OP�����V�K������ArrayList�Ɋi�[����
            l_ifoTodayOpenOrders.add(l_ifoCurTodayOpenOrder);
        }

        //�敨OP�����V�K������ArrayList��z��ɕϊ����ĕԋp����
        WEB3IfoTodayOpenOrder[] l_returns = new WEB3IfoTodayOpenOrder[l_ifoTodayOpenOrders.size()];
        l_returns = (WEB3IfoTodayOpenOrder[])l_ifoTodayOpenOrders.toArray(l_returns);
        return l_returns;
    }

    /**
     * (create�敨OP�����V�K���������ꗗ)<BR>
     * 
     * ���񒍕�(����.�敨OP���������e)���܂񂾐敨OP�����V�K���������̈ꗗ���쐬����<BR>
     * 
     * �i�V�K���]�̓`�F�b�N���Ɏg�p�j<BR>
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�敨OP�����V�K���������ꗗ�i�V�K���]�̓`�F�b�N���j�v�Q�ƁB<BR>
     * 
     * @@param l_ifoNewOrderSpec - �敨OP���������e�B
     * @@return webbroker3.ifodeposit.WEB3IfoTodayOpenOrder[]
     */
    private WEB3IfoTodayOpenOrder[] createIfoTodayOpenOrderList(WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
    {
        //�敨OP�����V�K�����������i�[����HashMap�𐶐�����
        HashMap l_ifoTodayOpenOrders = new HashMap();

        //����ID���擾����
        long l_lngAccountId = this.subAccount.getAccountId();
        //�⏕����ID���擾����
        long l_lngSubAccountId = this.subAccount.getSubAccountId();
        //�c�Ɠ�[T+0]���擾����B
        Date l_datCurrentBizDate = this.ifoDepositCalcCondition.getCurrentBizDate();

        //�����V�K�������P��Params�ꗗ���擾����B
        IfoOrderUnitParams[] l_ifoOrderUnitParams =
            WEB3IfoDepositPersistentDataManager.getTodayOpenOrderUnitParamsList(
                l_lngAccountId,
                l_lngSubAccountId,
                l_datCurrentBizDate);

        //�擾���������V�K�������P��Params�ꗗ != null�̎�
        if (l_ifoOrderUnitParams != null)
        {
            //�擾���������V�K�������P��Params�ꗗ�̗v�f����LOOP����
            for (int index = 0; index < l_ifoOrderUnitParams.length; index++)
            {
                //�擾���������V�K�������P��Params�ꗗ���敨OP�����V�K�����𐶐�����
                WEB3IfoTodayOpenOrder l_ifoCurTodayOpenOrder =
                    WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoOrderUnitParams[index]);

                //���������敨OP�����V�K������HashMap�Ɋi�[����
                l_ifoTodayOpenOrders.put(
                    Long.toString(l_ifoOrderUnitParams[index].getOrderUnitId()),
                    l_ifoCurTodayOpenOrder);
            }
        }

        //�敨OP���������e����敨OP�����V�K�����������쐬����B 
        WEB3IfoTodayOpenOrder l_ifoTodayOpenOrder =
            WEB3IfoTodayOpenOrder.getIfoTodayOpenOrder(l_ifoNewOrderSpec);

        //HashMap�ɐ��������敨OP�����V�K����������ǉ�����B
        l_ifoTodayOpenOrders.put(Long.toString(l_ifoNewOrderSpec.orderUnitId), l_ifoTodayOpenOrder);

        //�敨OP�����V�K������ArrayList��z��ɕϊ����ĕԋp����
        WEB3IfoTodayOpenOrder[] l_returns = new WEB3IfoTodayOpenOrder[l_ifoTodayOpenOrders.size()];
        l_returns = (WEB3IfoTodayOpenOrder[])l_ifoTodayOpenOrders.values().toArray(l_returns);
        return l_returns;
    }

    /**
     * (create�敨OP�ۗL���ʏ��ꗗ)<BR>
     * 
     * �敨OP�ۗL���ʏ��̈ꗗ���쐬����B<BR>
     * ���Y���̐敨OP���ʂ����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�敨OP�ۗL���ʏ��ꗗ�v�Q�ƁB<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoContract[]
     */
    private WEB3IfoContract[] createIfoContractList()
    {
        //�敨OP�ۗL���ʏ����i�[����HashMap�𐶐�����B
        HashMap l_ifoContracts = new HashMap();

        //����ID���擾����
        long l_lngAccountId = this.subAccount.getAccountId();
        //�⏕�������擾����
        long l_lngSubAccountId = this.subAccount.getSubAccountId();

        //����Params�ꗗ���擾����
        IfoContractParams[] l_ifoContractParams =
            WEB3IfoDepositPersistentDataManager.getContractParamsList(
                l_lngAccountId,
                l_lngSubAccountId);

        //�擾��������Params�ꗗ==null�̎�
        if (l_ifoContractParams == null)
        {
            //null��ԋp����
            return null;
        }

        //get�c�Ɠ�[T+0]
        Date l_datCurrentBizDate = this.ifoDepositCalcCondition.getCurrentBizDate();

        //�擾��������Params�ꗗ�̗v�f����LOOP����
        for (int index = 0; index < l_ifoContractParams.length; index++)
        {
            //����Params�ꗗ���敨OP�ۗL���ʏ��𐶐�����
            WEB3IfoContract l_ifoCurContract =
                WEB3IfoContract.getIfoContract(l_ifoContractParams[index], l_datCurrentBizDate);

            //���������敨OP�ۗL���ʏ���HashMap�Ɋi�[���� 
            l_ifoContracts.put(
                Long.toString(l_ifoContractParams[index].getContractId()),
                l_ifoCurContract);
        }

        //�g�����U�N�V����Params�ꗗ���擾����B
        IfoFinTransactionParams[] l_finTransactionParams =
            WEB3IfoDepositPersistentDataManager.getFinTransactionParamsList(
                l_lngAccountId,
                l_lngSubAccountId,
                l_datCurrentBizDate);

        //�擾�����g�����U�N�V����Params != null�̏ꍇ
        if (l_finTransactionParams != null)
        {
            //(�J�[�\��)�g�����U�N�V����Params
            IfoFinTransactionParams l_curFinTransaction = null;
            //�擾�����g�����U�N�V����Params�ꗗ�̗v�f����LOOP����
            for (int index = 0; index < l_finTransactionParams.length; index++)
            {
                //(�J�[�\��)�g�����U�N�V����Params���擾����B
                l_curFinTransaction = l_finTransactionParams[index];

                //HashMap���A�敨OP�ۗL���ʏ������o��
                WEB3IfoContract l_ifoContract =
                    (WEB3IfoContract)l_ifoContracts.get(
                        Long.toString(l_curFinTransaction.getContractId()));

                //�����P��ID�ɂЂ��Â������P�ʂ��擾����B 
                IfoOrderUnitParams l_ifoOrderUnitParams = 
                WEB3IfoDepositPersistentDataManager.getIfoOrderUnitParams(
                    l_curFinTransaction.getOrderUnitId());

                //�敨OP�ۗL���ʏ��.���ʐ��ʂ���萔�ʂ����Z����
                l_ifoContract.subtractQuantity(
                    l_curFinTransaction.getFinTransactionCateg(),
                    l_curFinTransaction.getQuantity(),
                    l_ifoOrderUnitParams.getSessionType());

                //�敨OP�ۗL���ʏ��ɁA�g�����U�N�V����Params��ǉ�����        
                l_ifoContract.addIfoFinTransaction(l_curFinTransaction);

                //HashMap�ɁA�敨OP�ۗL���ʏ����i�[����
                l_ifoContracts.put(
                    Long.toString(l_curFinTransaction.getContractId()),
                    l_ifoContract);
            }
        }

        //�敨OP�ۗL���ʏ���HashMap��z��ɕϊ����ĕԋp����
        WEB3IfoContract[] l_returns = new WEB3IfoContract[l_ifoContracts.size()];
        l_returns = (WEB3IfoContract[])l_ifoContracts.values().toArray(l_returns);       
        return l_returns;
    }

    /**
     * (create�敨OP�ڋq�ړ�����)<BR>
     * 
     * �敨OP�ڋq�ٓ����ׂ̍쐬�A�v���p�e�B�̐ݒ���s���A
     * this.�敨OP�ڋq�ړ����ׂɃZ�b�g����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�敨OP�ڋq�ړ����ׁv�v�Q�ƁB<BR>
     * 
     * @@param l_ifoContractList - (�敨OP�ۗL���ʏ��ꗗ)<BR>
     * �敨OP�ۗL���ʏ��̈ꗗ�B<BR>
     * �Y������敨OP���ʂ����݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - (�敨OP�����V�K���������ꗗ)<BR>
     * �敨OP�����V�K���������̈ꗗ�B<BR>
     * �敨OP�����V�K�����������݂��Ȃ��ꍇ��null�B<BR>
     */
    private void createIfoCustomerTransfer(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList)
    {
        //�敨OP�ڋq�ړ����׃I�u�W�F�N�g�𐶐�����
        WEB3IfoCustomerTransfer l_ifoCustomerTransfer = new WEB3IfoCustomerTransfer();

        /*
         * �m��c��[T+0..2]���擾����
         */
        //�m��c��[T+0..2]
        double[] l_cashBalance = new double[3];

        //�ڋq����c���}�X�^���Params���擾����
        TpCashBalanceParams l_cashBalanceParams =
            WEB3IfoDepositPersistentDataManager.getTpCashBalanceParams(
                subAccount.getAccountId(),
                subAccount.getSubAccountId());

        //�ڋq����c���}�X�^���Params�̖߂�l��NULL�̏ꍇ
        if(l_cashBalanceParams == null)
        {
            //�m��c��[T+0..2]�ɒl(=0)���Z�b�g����            
            l_cashBalance[0] = 0;
            l_cashBalance[1] = 0;
            l_cashBalance[2] = 0;
        }
        //�ȊO�̏ꍇ
        else
        {
            //�m��c��[T+0..2]�ɒl���Z�b�g����            
            l_cashBalance[0] = l_cashBalanceParams.getCashBalance0();
            l_cashBalance[1] = l_cashBalanceParams.getCashBalance1();
            l_cashBalance[2] = l_cashBalanceParams.getCashBalance2();
        }

        //�c��[T+0..2]���A�敨OP�ڋq�ړ����ׂɃZ�b�g����B
        l_ifoCustomerTransfer.setBalances(l_cashBalance);

        //�c�Ɠ�[T+0]���擾����
        Date l_datBizDate0 = this.ifoDepositCalcCondition.getCurrentBizDate();
        //�c�Ɠ�[T+1]���擾����
        Date l_datBizDate1 = this.ifoDepositCalcCondition.getNextBizDate();
        //�c�Ɠ�[T+2]���擾����
        Date l_datBizDate2 = this.ifoDepositCalcCondition.getNext2BizDate();

        //�����U�֒����P��Params�ꗗ���擾����
        AioOrderUnitParams[] l_aioOrderUnitParams =
            WEB3IfoDepositPersistentDataManager.getTodayAioOrderUnitParamsList(
                subAccount.getAccountId(),
                subAccount.getSubAccountId(),
                l_datBizDate0);

        //�����U�֒����P��Params�ꗗ��null�̎�
        if (l_aioOrderUnitParams != null)
        {
            //�����U�֒����P��Params�ꗗ�̗v�f����LOOP����
            for (int index = 0; index < l_aioOrderUnitParams.length; index++)
            {
                //�敨OP�ڋq�ړ����ׂɓ����z�����Z����
                l_ifoCustomerTransfer.addCashinAmount(
                    l_aioOrderUnitParams[index].getOrderType(),
                    l_aioOrderUnitParams[index].getQuantity(),
                    l_aioOrderUnitParams[index].getDeliveryDate(),
                    l_datBizDate0);

                //�敨OP�ڋq�ړ����ׂɏo���z�����Z����
                l_ifoCustomerTransfer.addCashoutAmount(
                    l_aioOrderUnitParams[index].getOrderType(),
                    l_aioOrderUnitParams[index].getQuantity(),
                    l_aioOrderUnitParams[index].getDeliveryDate(),
                    l_datBizDate0);
            }
            //�U�֊z���Z�b�g����
            l_ifoCustomerTransfer.setCurrentBizDateTransferAmount(
                l_ifoCustomerTransfer.getCurrentBizDateCashinAmount() - 
                l_ifoCustomerTransfer.getCurrentBizDateCashoutAmount());
                
            l_ifoCustomerTransfer.setNextBizDateTransferAmount(
                l_ifoCustomerTransfer.getNextBizDateCashinAmount() - 
                l_ifoCustomerTransfer.getNextBizDateCashoutAmount());
        }

        //����.�敨OP�ۗL���ʏ��ꗗ��null�̎�
        if (l_ifoContractList != null)
        {
            //�敨OP�ۗL���ʏ��ꗗ�̗v�f����LOOP����
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //�敨���ϑ��v[T+1]���擾����
                double l_dblFuturesCloseProfitLoss1 =
                    l_ifoContractList[index].getNextBizDateFuturesCloseProfitLoss(l_datBizDate1);
                //�I�v�V������n���[T+1]���擾����
                double l_dblOptionNetAmount1 =
                    l_ifoContractList[index].getNextBizDateOptionNetAmount(l_datBizDate1);

                //�敨OP�ڋq�ړ����ׂɁA�敨���ϑ��v[T+1]�����Z����
                l_ifoCustomerTransfer.addNextBizDateFuturesCloseProfitLoss(
                    l_dblFuturesCloseProfitLoss1);
                //�敨OP�ڋq�ړ����ׂɁA�I�v�V������n���[T+1]�����Z����
                l_ifoCustomerTransfer.addNextBizDateOptionNetAmount(l_dblOptionNetAmount1);

                //�敨���ϑ��v[T+2]���擾����
                double l_dblFuturesCloseProfitLoss2 =
                    l_ifoContractList[index].getNext2BizDateFuturesCloseProfitLoss(l_datBizDate2);
                //�I�v�V������n���[T+2]���擾����
                double l_dblOptionNetAmount2 =
                    l_ifoContractList[index].getNext2BizDateOptionNetAmount(l_datBizDate2);

                //�敨OP�ڋq�ړ����ׂɁA�敨���ϑ��v[T+2]�����Z����
                l_ifoCustomerTransfer.addNext2BizDateFuturesCloseProfitLoss(
                    l_dblFuturesCloseProfitLoss2);
                //�敨OP�ڋq�ړ����ׂɁA�I�v�V������n���[T+2]�����Z����
                l_ifoCustomerTransfer.addNext2BizDateOptionNetAmount(l_dblOptionNetAmount2);
            }
        }

        //����.�敨OP�����V�K���������ꗗ��null�̎�
        if (l_ifoTodayOpenOrderList != null)
        {
            //�敨OP�����V�K���������ꗗ�̗v�f����LOOP����
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //�I�v�V�����̎�
                if (l_ifoTodayOpenOrderList[index].isFutures() == false)
                {
                    //������n������擾����
                    double l_dblCurrentBizDateNetAmount =
                        this.getCurrentBizDateNetAmount(
                            l_ifoTodayOpenOrderList[index].getOrderUnitId(),
                            l_ifoContractList,
                            l_datBizDate0);

                    //�敨OP�����V�K�����������A������n��������Z����
                    l_ifoTodayOpenOrderList[index].subtractOptionEstimatedNetAmount(
                        l_dblCurrentBizDateNetAmount);
                }

                //�I�v�V���������T�Z��n���[T+1]���擾���� 
                double l_dblEstimatedNetAmount1 =
                    l_ifoTodayOpenOrderList[index].getOptionBuyEstimatedNetAmount(l_datBizDate1);
                //�I�v�V���������T�Z��n���[T+2]���擾���� 
                double l_dblEstimatedNetAmount2 =
                    l_ifoTodayOpenOrderList[index].getOptionBuyEstimatedNetAmount(l_datBizDate2);

                //�敨OP�ڋq�ړ����ׂɁA�I�v�V���������T�Z��n���[T+1]�����Z����
                l_ifoCustomerTransfer.addNextBizDateOptionBuyEstimatedNetAmount(
                    l_dblEstimatedNetAmount1);
                //�敨OP�ڋq�ړ����ׂɁA�I�v�V���������T�Z��n���[T+2]�����Z����
                l_ifoCustomerTransfer.addNext2BizDateOptionBuyEstimatedNetAmount(
                    l_dblEstimatedNetAmount2);
            }
        }

        //�쐬�����敨OP�ڋq�ړ����ׂ��v���p�e�B�ɃZ�b�g����B
        this.setIfoCustomerTransfer(l_ifoCustomerTransfer);
    }

    /**
     * (create�������Params�ꗗ)<BR>
     * 
     * �Y���ڋq�ۗ̕L/�������Ă���敨OP�������Params�̔z����쐬����B<BR>
     * 
     * �P�j�@@�ۗL/�������Ă���敨OP���������݂��Ȃ��ꍇ<BR>
     * �@@����.�敨OP�ۗL���ʏ��ꗗ == null�A���A<BR>
     * �@@����.�敨OP�����V�K���������ꗗ == null�̏ꍇ�Anull��ԋp���ďI������B<BR>
     * 
     * �Q�j�@@�������Params�ꗗ���i�[����HashMap�𐶐�����B<BR>
     * 
     * �R�j�@@����.�敨OP�ۗL���ʏ��ꗗ != null�̏ꍇ�̂݁A�敨OP�ۗL���ʏ��ꗗ���Ƃ�Loop�������s���B<BR>
     * 
     * �@@�R�|�P�j�@@����ID�Ǝs��ID�̑g�ݍ��킹��HashMap�ɒǉ���(*)�̏ꍇ�A���̗v�f�ɏ������ڍs����(continue;)<BR>
     * �@@�@@�@@�@@�@@�@@(*)HashMap.containsKey(key)�@@== true<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@key�F�@@�敨OP�ۗL���ʏ��.����ID�Ɛ敨OP�ۗL���ʏ��.�s��ID�̕�����A���l<BR>
     * 
     * �@@�R�|�Q�j�@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�������Params()�ɂ��������Params���擾����B<BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@����ID�F�@@�敨OP�ۗL���ʏ��.����ID<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�F�@@�敨OP�ۗL���ʏ��.�s��ID<BR>
     * 
     * �@@�R�|�R�j�@@HashMap.put( )�ɂĎ������Params��ǉ�����B<BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@key�F�@@�敨OP�ۗL���ʏ��.����ID�Ɛ敨OP�ۗL���ʏ��.�s��ID�̕�����A���l<BR>
     * �@@�@@�@@�@@�@@�@@value�F�@@�擾�����������Params<BR>
     * 
     * �S�j�@@����.�敨OP�����V�K���������ꗗ != null�̏ꍇ�̂݁A�敨OP�V�K���������ꗗ���Ƃ�Loop�������s���B<BR>
     * 
     * �@@�S�|�P�j�@@����ID�Ǝs��ID�̑g�ݍ��킹��HashMap�ɒǉ���(*)�̏ꍇ�A���̗v�f�ɏ������ڍs����(continue;)<BR>
     * 
     * �@@�@@�@@�@@�@@�@@(*)HashMap.containsKey(key)�@@== true<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@key�F�@@�敨OP�����V�K���������.����ID�Ɛ敨OP�����V�K���������.�s��ID�̕�����A���l<BR>
     * 
     * �@@�S�|�Q�j�@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�������Params()�ɂ��������Params���擾����B<BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@����ID�F�@@�敨OP�����V�K���������.����ID<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�F�@@�敨OP�����V�K���������.�s��ID<BR>
     * 
     * �@@�S�|�R�j�@@HashMap.put( )�ɂĎ������Params��ǉ�����B<BR>
     * 
     * �@@�@@�@@�@@�@@�@@[�����̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@key�F�@@�敨OP�����V�K���������.����ID�Ɛ敨OP�����V�K���������.�s��ID�̕�����A���l<BR>
     * �@@�@@�@@�@@�@@�@@value�F�@@�擾�����������Params<BR>
     * 
     * �T�j�@@HashMap��z�񉻂��Đ敨OP�������Params�̔z���ԋp����B<BR>
     * �@@
     * @@param l_ifoContractList - (�敨OP�ۗL���ʏ��̈ꗗ)<BR>
     * �Y������敨OP���ʂ����݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - �敨OP�����V�K���������̈ꗗ�B(BR>
     * �Y������敨OP�����V�K�����������݂��Ȃ��ꍇ��null�B(BR>
     * 
     * @@return IfoTradedProductParams[]
     */
    private IfoTradedProductParams[] createTradedProductParamsList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList)
    {

        //����.�敨OP�ۗL���ʏ��ꗗ == null�A���A����.�敨OP�����V�K���������ꗗ == null�̎�
        if (l_ifoContractList == null && l_ifoTodayOpenOrderList == null)
        {
            //null��ԋp����B 
            return null;
        }

        //�������Params�ꗗ���i�[����HashMap�𐶐�����B
        HashMap l_ifoTradedProducts = new HashMap();

        //����.�敨OP�ۗL���ʏ��ꗗ != null�̎�
        if (l_ifoContractList != null)
        {
            //(�J�[�\��)�敨OP�ۗL���ʏ��
            WEB3IfoContract l_curIfoContract = null;

            //����.�敨OP�ۗL���ʏ��ꗗ�̗v�f����LOOP����
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //(�J�[�\��)�敨OP�ۗL���ʏ����擾����
                l_curIfoContract = l_ifoContractList[index];

                //HashMap��Key(����ID+�s��ID)���쐬����B
                String l_strCurKey =
                    Long.toString(l_curIfoContract.productId)
                        + Long.toString(l_curIfoContract.marketId);
                //Key��HashMap�ɒǉ����ς݂̎�
                if (l_ifoTradedProducts.containsKey(l_strCurKey) == false)
                {
                    //�������Params���擾����
                    IfoTradedProductParams l_ifoTradedProductParams =
                        WEB3IfoDepositPersistentDataManager.getTradedProductParams(
                            l_curIfoContract.productId,
                            l_curIfoContract.marketId);

                    //HashMap�ɁA�������Params��ǉ�����B 
                    l_ifoTradedProducts.put(l_strCurKey, l_ifoTradedProductParams);
                }
                
            }
        }

        //����.�敨OP�����V�K���������ꗗ != null�̎�
        if (l_ifoTodayOpenOrderList != null)
        {
            //(�J�[�\��)�敨OP�V�K�����������擾����
            WEB3IfoTodayOpenOrder l_curIfoTodayOpenOrder = null;

            //����.�敨OP�V�K���������ꗗ�̗v�f����LOOP����
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //(�J�[�\��)�敨OP�V�K�����������擾����
                l_curIfoTodayOpenOrder = l_ifoTodayOpenOrderList[index];

                //HashMap��Key(����ID+�s��ID)���쐬����B
                String l_strCurKey =
                    Long.toString(l_curIfoTodayOpenOrder.productId)
                        + Long.toString(l_curIfoTodayOpenOrder.marketId);

                //Key��HashMap�ɒǉ����ς̎�
                if (l_ifoTradedProducts.containsKey(l_strCurKey) == false)
                {
                    //�������Params���擾����B 
                    IfoTradedProductParams l_ifoTradedProductParams =
                        WEB3IfoDepositPersistentDataManager.getTradedProductParams(
                            l_curIfoTodayOpenOrder.productId,
                            l_curIfoTodayOpenOrder.marketId);

                    //HashMap�Ɏ������Params��ǉ����� 
                    l_ifoTradedProducts.put(l_strCurKey, l_ifoTradedProductParams);
                }
            }
        }

        //HashMap��z�񉻂��Đ敨OP�������Params�̔z���ԋp����
        IfoTradedProductParams[] l_returns = new IfoTradedProductParams[l_ifoTradedProducts.size()];
        l_returns = (IfoTradedProductParams[])l_ifoTradedProducts.values().toArray(l_returns);
        return l_returns;
    }

    /**
     * (create�敨OP�������ꗗ)<BR>
     * 
     * �敨OP�������̈ꗗ���쐬����B<BR>
     * ���ۗL/�������Ă���敨OP���������݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�敨OP�������ꗗ�v�Q�ƁB<BR>
     * 
     * @@param l_ifoTradedProductParams - (�������Params�ꗗ)<BR>
     * �������Params�̔z��B<BR>
     * ���敨OP���ʂ�ۗL���Ă��Ȃ��A���A�����V�K�����������݂��Ȃ��ꍇ�́Anull�B<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoProduct[]
     */
    private WEB3IfoProduct[] createIfoProductList(IfoTradedProductParams[] l_ifoTradedProductParams)
        throws WEB3SystemLayerException
    {
        //����.�������Params�ꗗ==null�̎�
        if (l_ifoTradedProductParams == null)
        {
            //null��ԋp����
            return null;
        }

        //�敨OP���������i�[����ArrayList�𐶐�����
        ArrayList l_ifoProducts = new ArrayList();
        //���A�������؋����v�Z���{�t���O���擾����
        boolean l_flgRealPrice = this.ifoDepositCalcCondition.isRealPriceIfoDepositCalc();

        //����J�����_�R���e�L�X�g�����X���b�h���[�J������擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //��t���ԋ敪���擾����
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //�����R�[�h���擾����
        String l_strProductCode = l_clendarContext.getProductCode();

        //(�J�[�\��)�������Params
        IfoTradedProductParams l_curIfoTradedProductParams = null;
        //(�J�[�\��)����Params
        IfoProductParams l_curIfoProductParams = null;

        //����.�������Params�ꗗ�̗v�f����LOOP����
        for (int index = 0; index < l_ifoTradedProductParams.length; index++)
        {
            //(�J�[�\��)�������Params���擾����
            l_curIfoTradedProductParams = l_ifoTradedProductParams[index];
            //(�J�[�\��)����Params���擾����
            l_curIfoProductParams =
                WEB3IfoDepositPersistentDataManager.getProductParams(
                    l_curIfoTradedProductParams.getProductId());

            //����J�����_�R���e�L�X�g�Ɍ����Y�����R�[�h���Z�b�g����
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_curIfoProductParams.getUnderlyingProductCode());
            //����J�����_�R���e�L�X�g�Ɏ�t���ԋ敪���Z�b�g����B
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(
                WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
                
            //����ԑуt���O���擾����B
            boolean l_blnSession = WEB3GentradeTradingTimeManagement.isSessionTimeZone();           
            //�[�ꎞ�ԑуt���O���擾����B
            boolean l_blnEvening = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();


            //�s��J�ǎ��ԑ�(����)�̎�
            if (l_blnSession && !l_blnEvening)
            {
                //�敨OP�������𐶐�����B
                WEB3IfoProduct l_ifoProduct =
                    WEB3IfoProduct.getOnSessionIfoProduct(
                        l_curIfoProductParams,
                        l_curIfoTradedProductParams,
                        l_flgRealPrice);
                //���������敨OP��������ArrayList�ɒǉ�����
                l_ifoProducts.add(l_ifoProduct);
            }
             //�s��J�ǎ��ԑ�(�[��)�̎�
            else if (l_blnSession && l_blnEvening)
            {

                //���c�Ɠ����擾
                Date l_datOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();              
                WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderDate .getTime()));
                l_datOrderDate = WEB3DateUtility.toDay(l_gentradeBizDate.roll(1));

                //��������ꎞParams���擾����
                IfoTradedProductUpdqParams l_tradedProductUpdqParams =
                    WEB3IfoDepositPersistentDataManager.getTradedProductUpdqParams(
                        l_curIfoProductParams.getProductId(),
                        l_curIfoTradedProductParams.getMarketId(),
                        l_curIfoProductParams.getInstitutionCode(),
                        l_datOrderDate);
               
                WEB3IfoProduct l_ifoProduct =
                    WEB3IfoProduct.getOnEveningSessionIfoProduct(
                        l_curIfoProductParams,
                        l_tradedProductUpdqParams,
                        l_flgRealPrice,
                        this.ifoDepositCalcCondition.isIfoDepositMailFlag(),
                        l_curIfoTradedProductParams);
                //���������敨OP��������ArrayList�ɒǉ�����
                l_ifoProducts.add(l_ifoProduct);
            }
             //�s��J�ǎ��ԑшȊO
            else
            {
                //������(������擾����)
                Date l_datOrderDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();              
                //�[�ꎞ�ԑт̏ꍇ�͉c�Ɠ��v�Z.calc�c�Ɠ��ŗ��c�Ɠ����擾
                if (l_blnEvening){
                    WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderDate .getTime()));
                    //���c�Ɠ�
                    l_datOrderDate = WEB3DateUtility.toDay(l_gentradeBizDate.roll(1));
                }                                      
                //�敨OP�������𐶐�����
                WEB3IfoProduct l_ifoProduct =
                    WEB3IfoProduct.getOffSessionIfoProduct(
                        l_curIfoProductParams,
                        l_curIfoTradedProductParams,
                        l_datOrderDate);

                //���������敨OP��������ArrayList�ɒǉ�����
                l_ifoProducts.add(l_ifoProduct);
            }
        }

        //����J�����_�R���e�L�X�g��������Ԃɖ߂�
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);

        //�敨OP��������ArrayList��z��ɕϊ����ĕԋp����
        WEB3IfoProduct[] l_returns = new WEB3IfoProduct[l_ifoProducts.size()];
        l_returns = (WEB3IfoProduct[])l_ifoProducts.toArray(l_returns);
        return l_returns;
    }

    /**
     * (create�������P���ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * �������P���ʐ敨OP���ʏW�v�̈ꗗ�̍쐬�A�v���p�e�B�̐ݒ���s���A
     * this.�������P���ʐ敨OP���ʏW�v�ꗗ�ɃZ�b�g����B<BR>
     * ���ۗL���Ă���敨OP���������݂��Ȃ��ꍇ��null���Z�b�g����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�������P���ʐ敨OP���ʏW�v�ꗗ�v�Q�ƁB<BR>
     * 
     * @@param l_ifoContractList - (�敨OP�ۗL���ʏ��ꗗ)<BR>
     * �敨OP�ۗL���ʏ��̈ꗗ�B<BR>
     * �Y������敨OP���ʂ����݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_ifoProductList - (�敨OP�������ꗗ)<BR>
     * �敨OP�������̈ꗗ�B<BR>
     * �ۗL/�������Ă���敨OP���������݂��Ȃ��ꍇ��nul���B<BR>
     */
    private void createIfoSummaryContractPerProductContractPriceList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoProduct[] l_ifoProductList)
    {
        //�敨OP�ۗL���ʏ�񂪑��݂��Ȃ��ꍇ
        if (l_ifoContractList == null)
        {
            //this.�������P���ʐ敨OP���ʏW�v�ꗗ��null����
            this.setIfoSummaryContractPerProductContractPriceList(null);
            //�����I��
            return;
        }

        //�������P���ʐ敨OP���ʏW�v���i�[����HashMap�𐶐�����
        HashMap l_ifoSummarys = new HashMap();
        //�؋����v�Z������擾����        
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //�����R�[�h���擾���ĕۑ�����
        String l_strProductCode = l_clendarContext.getProductCode();

        //(�J�[�\��)�敨OP�ۗL���ʏ��
        WEB3IfoContract l_curIfoContract = null;

        //�敨OP�ۗL���ʏ��ꗗ�̗v�f����LOOP����
        for (int index = 0; index < l_ifoContractList.length; index++)
        {
            //(�J�[�\��)�敨OP�ۗL���ʏ����擾����
            l_curIfoContract = l_ifoContractList[index];

            //����Params���擾����B 
            IfoProductParams l_ifoProductParams =
                WEB3IfoDepositPersistentDataManager.getProductParams(
                    l_curIfoContract.productId);

            //����J�����_�R���e�L�X�g�ɏ؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get����Params�̖߂�l.�����Y�����R�[�h���Z�b�g����
            WEB3GentradeTradingTimeManagement.resetProductCode(
                l_ifoProductParams.underlying_product_code);
                 
            //HashMap��Key(����ID+���P��)���쐬����B
            String l_strKey =
                Long.toString(l_curIfoContract.productId)
                    + Double.toString(l_curIfoContract.contractPrice);

            //HashMap�Ɋ���Key�����݂��鎞
            if (l_ifoSummarys.containsKey(l_strKey) == true)
            {
                //HashMap���������P���ʐ敨OP���ʏW�v�I�u�W�F�N�g���擾����B
                WEB3IfoSummaryContractPerProductContractPrice l_ifoSummary =
                    (WEB3IfoSummaryContractPerProductContractPrice)l_ifoSummarys.get(l_strKey);

                //�����ʂ����Z����
                l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);                   

                //�����ʁ��؋����s�����m�聄��ǉ�����
                l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);

                //�������̏ꍇ�A���������ʂ����Z����
                if(l_curIfoContract.isTodayContract(this.ifoDepositCalcCondition))
                {
                    l_ifoSummary.addTodayQuantity(l_curIfoContract.isBuy(), l_curIfoContract.quantity);
                }

                //HashMap�ɖ������P���ʐ敨OP���ʏW�v�I�u�W�F�N�g���i�[����
                l_ifoSummarys.put(l_strKey, l_ifoSummary);
            }
            //HashMap��Key�����݂��Ȃ���
            else
            {
                //�w������̐敨OP�������ꗗ���擾����
                WEB3IfoProduct[] l_specifiedIfoProductList =
                    this.getIfoProductList(l_curIfoContract.productId, l_ifoProductList);

                //�������P���ʐ敨OP���ʏW�v�𐶐�����
                WEB3IfoSummaryContractPerProductContractPrice l_ifoSummary =
                    WEB3IfoSummaryContractPerProductContractPrice.create();

                //����ID���Z�b�g����
                l_ifoSummary.setProductId(l_curIfoContract.productId);
                //�����R�[�h���Z�b�g����
                l_ifoSummary.setProductCode(l_specifiedIfoProductList[0].productCode);
                //�敨�I�v�V�������i�敪���Z�b�g����
                l_ifoSummary.setIfoDerivativeType(
                    l_specifiedIfoProductList[0].ifoDerivativeType);
                //���P�����Z�b�g����
                l_ifoSummary.setContractPrice(l_curIfoContract.contractPrice);
                //�������Z�b�g����
                l_ifoSummary.setCurrentPrice(l_specifiedIfoProductList[0].currentPrice);
                //�������Z�l �Z�b�g����
                l_ifoSummary.setCurrentBizDateLiquidationPrice(l_specifiedIfoProductList[0].currentBizDateLiquidationPrice);
                //�w���搔���Z�b�g����
                l_ifoSummary.setUnitSize(l_specifiedIfoProductList[0].unitSize);

                //�����ʂ����Z����
                l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);                   

                //�����ʁ��؋����s�����m�聄��ǉ�����
                l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);
                   
                //�������̏ꍇ�A���������ʂ����Z����
                if(l_curIfoContract.isTodayContract(this.ifoDepositCalcCondition))
                {
                    l_ifoSummary.addTodayQuantity(l_curIfoContract.isBuy(), l_curIfoContract.quantity);
                }
                    
                //�]�����v�v�Z�敪���Z�b�g����
                l_ifoSummary.setProfitLossCalcType(
                    this.ifoDepositCalcCondition.getCalcConditionPerBranch(
                        WEB3IfoDepositBranchPrefNameDef.IFO_DEPOSIT_PROFITLOSS_CALC));

                //HashMap�ɖ������P���ʐ敨OP���ʏW�v�I�u�W�F�N�g���i�[����
                l_ifoSummarys.put(l_strKey, l_ifoSummary);
            }

        }
        
        //����J�����_�R���e�L�X�g��������Ԃɖ߂�
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //���ύό��ʂ݂̂������ꍇ
        if (l_ifoSummarys.isEmpty() == true)
        {
            //this.�������P���ʐ敨OP���ʏW�v�ꗗ��null����
            this.setIfoSummaryContractPerProductContractPriceList(null);
        }
        //����ȊO
        else
        {

            //HashMap��z��ɕϊ�����
            WEB3IfoSummaryContractPerProductContractPrice[] l_list =
                new WEB3IfoSummaryContractPerProductContractPrice[l_ifoSummarys.size()];
            l_list =
                (WEB3IfoSummaryContractPerProductContractPrice[])l_ifoSummarys.values().toArray(
                    l_list);

            //this.�������P���ʐ敨OP���ʏW�v�ꗗ�ɁA�z���������
            this.setIfoSummaryContractPerProductContractPriceList(l_list);
        }
    }

    /**
     * (create�����ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * �����ʐ敨OP���ʏW�v�̈ꗗ�̍쐬�A�v���p�e�B�̐ݒ���s���A<BR>
     * this.�����ʐ敨OP���ʏW�v�ꗗ�ɃZ�b�g����B<BR>
     * ���ۗL/�������Ă���敨OP���������݂��Ȃ��ꍇ��null���Z�b�g����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�����ʐ敨OP���ʏW�v�ꗗ�v�Q�ƁB<BR>
     * 
     * @@param l_ifoContractList - (�敨OP�ۗL���ʏ��ꗗ)<BR>
     * �敨OP�ۗL���ʏ��̈ꗗ�B<BR>
     * �Y������敨OP���ʂ����݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - (�敨OP�����V�K���������ꗗ)<BR>
     * �Y������敨OP�����V�K�����������݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_ifoProductList - (�敨OP�������̈ꗗ�B)<BR>
     * �ۗL/�������Ă���敨OP���������݂��Ȃ��ꍇ��nul���B<BR>
     */
    private void createIfoSummaryContractPerProductList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList,
        WEB3IfoProduct[] l_ifoProductList)
    {
        //�ۗL/�����敨OP���������݂��Ȃ��ꍇ
        if (l_ifoProductList == null)
        {
            //this.�����ʐ敨OP���ʏW�v�ꗗ��null���Z�b�g����
            this.setIfoSummaryContractPerProductList(null);
            return;
        }

        //�����ʐ敨OP���ʏW�v���i�[����HashMap�𐶐�����
        HashMap l_ifoSummarys = new HashMap();

        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //�����R�[�h���擾���ĕۑ�����
        String l_strProductCode = l_clendarContext.getProductCode();

        //�敨OP�ۗL���ʏ�񂪑��݂��鎞
        if (l_ifoContractList != null)
        {
            //(�J�[�\��)�敨OP�ۗL���ʏ��
            WEB3IfoContract l_curIfoContract = null;

            //�敨OP�ۗL���ʏ��̗v�f����LOOP����
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //(�J�[�\��)�敨OP�ۗL���ʏ����擾����
                l_curIfoContract = l_ifoContractList[index];

                //HashMap��Key(������ID)
                String l_strKey = Long.toString(l_curIfoContract.productId);

                //HashMap�Ɋ���Key�����݂��鎞
                if (l_ifoSummarys.containsKey(l_strKey) == true)
                {
                    //HashMap�������ʐ敨OP���ʏW�v�����o��
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        (WEB3IfoSummaryContractPerProduct)l_ifoSummarys.get(l_strKey);

                    //�w������̐敨OP�������ꗗ���擾����
                    WEB3IfoProduct[] l_specifiedIfoProductList =
                        this.getIfoProductList(l_ifoSummary.getProductId(), l_ifoProductList);
                         
                    //����J�����_�R���e�L�X�g�ɐ敨OP�������.�����Y�����R�[�h���Z�b�g����
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_specifiedIfoProductList[0].getUnderlyingProductCode());
                             
                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle)
                    {
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }

                    //�����ʂ�ǉ�����
                    l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);
                    //�����ʁ��؋����s�����m�聄��ǉ�����
                    l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);

                    //HasMap�ɖ����ʐ敨OP���ʏW�v���i�[����
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
                //HashMap��Key�����݂��Ȃ���
                else
                {
                    //�w������̐敨OP�������ꗗ���擾����
                    WEB3IfoProduct[] l_specifiedIfoProductList =
                        this.getIfoProductList(l_curIfoContract.productId, l_ifoProductList);

                    //�����ʐ敨OP���ʏW�v�𐶐�����
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        WEB3IfoSummaryContractPerProduct.create();
                    //����ID���Z�b�g
                    l_ifoSummary.setProductId(l_curIfoContract.productId);
                    //�����R�[�h���Z�b�g
                    l_ifoSummary.setProductCode(l_specifiedIfoProductList[0].productCode);
                    //�敨OP���i�敪���Z�b�g
                    l_ifoSummary.setIfoDerivativeType(
                        l_specifiedIfoProductList[0].ifoDerivativeType);
                    //�������Z�b�g
                    l_ifoSummary.setCurrentPrice(l_specifiedIfoProductList[0].currentPrice);
                    //�������Z�l �Z�b�g����
                    l_ifoSummary.setCurrentBizDateLiquidationPrice(l_specifiedIfoProductList[0].currentBizDateLiquidationPrice);						
                    //�w���搔���Z�b�g
                    l_ifoSummary.setUnitSize(l_specifiedIfoProductList[0].unitSize);


                    //����J�����_�R���e�L�X�g�ɐ敨OP�������.�����Y�����R�[�h���Z�b�g����
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_specifiedIfoProductList[0].getUnderlyingProductCode());
                             
                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle){
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }
                    
                    //�����ʂ�ǉ�����
                    l_ifoSummary.addQuantity(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity);

                    //�����ʁ��؋����s�����m�聄��ǉ�����
                    l_ifoSummary.addQuantityTemp(
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp);

                    //HasMap�ɖ����ʐ敨OP���ʏW�v���i�[����
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
                
            }
        }

        //����J�����_�R���e�L�X�g��������Ԃɖ߂�
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //�c�Ɠ�[T+0]
        Date l_datBizDate0 = this.ifoDepositCalcCondition.getCurrentBizDate();
        //�c�Ɠ�[T+1]
        Date l_datBizDate1 = this.ifoDepositCalcCondition.getNextBizDate();

        //�敨OP�����V�K��������񂪑��݂��鎞
        if (l_ifoTodayOpenOrderList != null)
        {
            //(�J�[�\��)�敨OP�����V�K���������
            WEB3IfoTodayOpenOrder l_curIfoTodayOpenOrder = null;

            //�敨OP�����V�K���������̗v�f����LOOP����
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //�敨OP�����V�K�����������擾����
                l_curIfoTodayOpenOrder = l_ifoTodayOpenOrderList[index];

                //HashMap��Key(������ID)
                String l_strKey = Long.toString(l_curIfoTodayOpenOrder.productId);

                //HashMap��Key�����݂��鎞                
                if (l_ifoSummarys.containsKey(l_strKey) == true)
                {
                    //HashMap��������ʐ敨OP���ʏW�v���擾����
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        (WEB3IfoSummaryContractPerProduct)l_ifoSummarys.get(l_strKey);

                    //�������ʂ�ǉ�����
                    l_ifoSummary.addOrderQuantity(
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMap�ɖ����ʐ敨OP���ʏW�v���i�[����
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
                //HashMap��Key�����݂��Ȃ���
                else
                {
                    //�w������̐敨OP�������ꗗ���擾����
                    WEB3IfoProduct[] l_specifiedIfoProductList =
                        this.getIfoProductList(l_curIfoTodayOpenOrder.productId, l_ifoProductList);

                    //�����ʐ敨OP���ʏW�v�𐶐�����
                    WEB3IfoSummaryContractPerProduct l_ifoSummary =
                        WEB3IfoSummaryContractPerProduct.create();

                    //����ID���Z�b�g
                    l_ifoSummary.setProductId(l_curIfoTodayOpenOrder.productId);
                    //�����R�[�h���Z�b�g
                    l_ifoSummary.setProductCode(l_specifiedIfoProductList[0].productCode);
                    //�敨OP���i�敪���Z�b�g
                    l_ifoSummary.setIfoDerivativeType(
                        l_specifiedIfoProductList[0].ifoDerivativeType);
                    //�������Z�b�g
                    l_ifoSummary.setCurrentPrice(l_specifiedIfoProductList[0].currentPrice);
                    //�w���搔���Z�b�g
                    l_ifoSummary.setUnitSize(l_specifiedIfoProductList[0].unitSize);
                    //�������ʂ�ǉ�����
                    l_ifoSummary.addOrderQuantity(
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMap�ɖ����ʐ敨OP���ʏW�v���i�[����
                    l_ifoSummarys.put(l_strKey, l_ifoSummary);
                }
            }
        }

        //HashMap����̎�
        if (l_ifoSummarys.isEmpty() == true)
        {
            //this.�����ʐ敨OP���ʏW�v�ꗗ��null���Z�b�g
            this.setIfoSummaryContractPerProductList(null);
        }
        //�ȊO�̎�
        else
        {
            //HashMap��z��ɕϊ�����
            WEB3IfoSummaryContractPerProduct[] l_list =
                new WEB3IfoSummaryContractPerProduct[l_ifoSummarys.size()];
            l_list = (WEB3IfoSummaryContractPerProduct[])l_ifoSummarys.values().toArray(l_list);

            //this.�����ʐ敨OP���ʏW�v�ꗗ�ɔz����Z�b�g����
            this.setIfoSummaryContractPerProductList(l_list);
        }
    }

    /**
     * (create�w���ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * �w����OP���ʏW�v�̈ꗗ�̍쐬�A�v���p�e�B�̐ݒ���s���A<BR>
     * this.�w���ʐ敨OP���ʏW�v�ꗗ�ɃZ�b�g����B<BR>
     * ���ۗL/�������Ă���敨OP���������݂��Ȃ��ꍇ��null���Z�b�g����B<BR>
     * 
     * �V�[�P���X�}<BR>
     * �u�i�؋����v�Z�jcreate�w���ʐ敨OP���ʏW�v�ꗗ�v�Q�ƁB<BR>
     * 
     * @@param l_ifoContractList - (�敨OP�ۗL���ʏ��ꗗ)<BR>
     * �敨OP�ۗL���ʏ��̈ꗗ�B<BR>
     * �Y������敨OP���ʂ����݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_ifoTodayOpenOrderList - (�敨OP�����V�K���������ꗗ)<BR>
     * �敨OP�����V�K���������̈ꗗ�B<BR>
     * �Y������敨OP�����V�K�����������݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_ifoProductList - (�敨OP�������ꗗ)<BR>
     * @@throws WEB3BaseException<BR>
     * 
     * �敨OP�������̈ꗗ�B<BR>
     * �ۗL/�������Ă���敨OP���������݂��Ȃ��ꍇ��nul���B<BR>
     */
    private void createIfoSummaryContractPerIndexList(
        WEB3IfoContract[] l_ifoContractList,
        WEB3IfoTodayOpenOrder[] l_ifoTodayOpenOrderList,
        WEB3IfoProduct[] l_ifoProductList) throws WEB3BaseException
    {
        //�ۗL/�����敨OP���������݂��Ȃ���
        if (l_ifoProductList == null)
        {
            //this.�w���ʐ敨OP���ʏW�v�ꗗ��null���Z�b�g����
            this.setIfoSummaryContractPerIndexList(null);
            //�����I��
            return;
        }

        //�����ʐ敨OP���ʏW�v���i�[����HashMap�𐶐�����
        HashMap l_ifoSummarys = new HashMap();

        // ����J�����_�R���e�L�X�g���擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                   WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        //�����R�[�h���擾���ĕۑ�����
        String l_strProductCode = l_clendarContext.getProductCode();

        //�敨OP�ۗL���ʏ�񂪑��݂��鎞
        if (l_ifoContractList != null)
        {
            //(�J�[�\��)�敨OP�ۗL���ʏ��
            WEB3IfoContract l_curIfoContract = null;
            //(�J�[�\��)�w������̐敨OP�������ꗗ
            WEB3IfoProduct[] l_curSpecifiedIfoProductList = null;
            //(�J�[�\��)�����Y�����R�[�h(=Key)
            String l_strCurUnderlyingProductCode = null;

            //�敨OP�ۗL���ʏ��̗v�f����LOOP����
            for (int index = 0; index < l_ifoContractList.length; index++)
            {
                //(�J�[�\��)�敨OP�ۗL���ʏ����擾����
                l_curIfoContract = l_ifoContractList[index];

                //(�J�[�\��)�w������̐敨OP�������ꗗ���擾����
                l_curSpecifiedIfoProductList =
                    this.getIfoProductList(l_curIfoContract.productId, l_ifoProductList);
                    
                // �Y���������A�|�W�V�����Ƃ��ėL���ł��邩���肷��B
                // �L���łȂ��ꍇ�́Acontiune����B
                if ( !l_curSpecifiedIfoProductList[0].isPosition(ifoDepositCalcCondition) )
                {
                    continue;
                }
    
                    
                //(�J�[�\��)�����Y�����R�[�h(=Key)���擾����B
                l_strCurUnderlyingProductCode =
                    l_curSpecifiedIfoProductList[0].underlyingProductCode;

                //HashMap��Key�����݂��鎞
                if (l_ifoSummarys.containsKey(l_strCurUnderlyingProductCode) == true)
                {
                    //HashMap���w���ʐ敨OP���ʏW�v���擾����
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        (WEB3IfoSummaryContractPerIndex)l_ifoSummarys.get(
                            l_strCurUnderlyingProductCode);
                                
                    //����J�����_�R���e�L�X�g�Ɍ����Y�����R�[�h���Z�b�g����
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_strCurUnderlyingProductCode);

                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle){
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }
                             
                    //�����ʂ�ǉ�����
                    l_ifoSummary.addContractQuantity(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity
                        );
                    //�����ʁ��؋����s�����m�聄��ǉ�����
                    l_ifoSummary.addContractQuantityTemp(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp
                        );

                    //HashMap�Ɏw���ʐ敨OP���ʏW�v���i�[����
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
                //HashMap��Key�����݂��Ȃ���
                else
                {
                    //�w���ʐ敨OP���ʏW�v�𐶐�����
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        WEB3IfoSummaryContractPerIndex.create();

                    //�؋����v�Z�������K��؋������擾����
                    double l_dblPerUnit =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnit(
                            l_strCurUnderlyingProductCode);
                    //�؋����v�Z�������K��؋������b�h���擾����
                    double l_dblPerUnitRed =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnitRed(
                            l_strCurUnderlyingProductCode);

                    //�����Y�����R�[�h���Z�b�g
                    l_ifoSummary.setTargetProductCode(l_strCurUnderlyingProductCode);
                    //�K��؋������Z�b�g
                    l_ifoSummary.setIfoDepositPerUnit(l_dblPerUnit);
                    //�K��؋������b�h���Z�b�g
                    l_ifoSummary.setIfoDepositPerUnitRed(l_dblPerUnitRed);
                        
                    //�K��؋������؋����s�����m�聄�F�@@�؋����v�Z����.get�K��؋������؋����s�����m�聄( )���Z�b�g
                    double l_dblDeposit = 
                         this.ifoDepositCalcCondition.getIfoDepositPerUnitTemp(l_strCurUnderlyingProductCode);
                    l_ifoSummary.setIfoDepositPerUnitTemp(l_dblDeposit);
                        
                    //����J�����_�R���e�L�X�g�Ɍ����Y�����R�[�h���Z�b�g����
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                         l_strCurUnderlyingProductCode);

                    Date l_datBizDate = null;                
                    try{
                        l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();                
                    } catch (WEB3SystemLayerException sle){
                        log.error(sle.getMessage(), sle);
                        throw new WEB3BaseRuntimeException(sle.getErrorInfo(), 
                            sle.getErrorMethod(), sle.getErrorMessage(), sle.getException());                       
                    }
                        
                    //�����ʂ�ǉ�����
                    l_ifoSummary.addContractQuantity(
                    l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantity
                        );
                    //�����ʁ��؋����s�����m�聄��ǉ�����
                    l_ifoSummary.addContractQuantityTemp(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoContract.isBuy(),
                        l_curIfoContract.quantityTemp
                        );

                    //HashMap�Ɏw���ʐ敨OP���ʏW�v���i�[����
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
            }
        }

        //����J�����_�R���e�L�X�g��������Ԃɖ߂�
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //�c�Ɠ�[T+0]
        Date l_datBizDate0 = this.ifoDepositCalcCondition.getCurrentBizDate();
        //�c�Ɠ�[T+1]
        Date l_datBizDate1 = this.ifoDepositCalcCondition.getNextBizDate();

        ////�敨OP�����V�K��������񂪑��݂��鎞
        if (l_ifoTodayOpenOrderList != null)
        {
            //(�J�[�\��)�敨OP�ۗL���ʏ����擾����
            WEB3IfoTodayOpenOrder l_curIfoTodayOpenOrder = null;
            //(�J�[�\��)�w������̐敨OP�������ꗗ
            WEB3IfoProduct[] l_curSpecifiedIfoProductList = null;
            //(�J�[�\��)�����Y�����R�[�h(=Key)
            String l_strCurUnderlyingProductCode = null;

            //�敨OP�����V�K���������̗v�f����LOOP����
            for (int index = 0; index < l_ifoTodayOpenOrderList.length; index++)
            {
                //(�J�[�\��)�敨OP�ۗL���ʏ����擾����
                l_curIfoTodayOpenOrder = l_ifoTodayOpenOrderList[index];
                //(�J�[�\��)�w������̐敨OP�������ꗗ���擾����
                l_curSpecifiedIfoProductList =
                    this.getIfoProductList(l_curIfoTodayOpenOrder.productId, l_ifoProductList);
                //(�J�[�\��)�����Y�����R�[�h(=Key)���擾����B
                l_strCurUnderlyingProductCode =
                    l_curSpecifiedIfoProductList[0].underlyingProductCode;

                //HashMap��Key���ǉ��ς݂̎�
                if (l_ifoSummarys.containsKey(l_strCurUnderlyingProductCode) == true)
                {
                    //HashMap���w���ʐ敨OP���ʏW�v���擾����
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        (WEB3IfoSummaryContractPerIndex)l_ifoSummarys.get(
                            l_strCurUnderlyingProductCode);

                    //�������ʂ����Z����
                    l_ifoSummary.addOrderQuantity(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMap�Ɏw���ʐ敨OP���ʏW�v��ǉ�����
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
                //HashMap��Key���ǉ����ς݂̎�
                else
                {
                    //�w���ʐ敨OP���ʏW�v�𐶐�����
                    WEB3IfoSummaryContractPerIndex l_ifoSummary =
                        WEB3IfoSummaryContractPerIndex.create();

                    //�؋����v�Z�������K��؋������擾����
                    double l_dblPerUnit =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnit(
                            l_strCurUnderlyingProductCode);
                    //�؋����v�Z�������K��؋������b�h���擾����
                    double l_dblPerUnitRed =
                        this.ifoDepositCalcCondition.getIfoDepositPerUnitRed(
                            l_strCurUnderlyingProductCode);

                    //�����Y�����R�[�h���Z�b�g
                    l_ifoSummary.setTargetProductCode(l_strCurUnderlyingProductCode);
                    //�K��؋������Z�b�g
                    l_ifoSummary.setIfoDepositPerUnit(l_dblPerUnit);
                    //�K��؋������b�h���Z�b�g
                    l_ifoSummary.setIfoDepositPerUnitRed(l_dblPerUnitRed);

                    //�K��؋������؋����s�����m�聄�F�@@�؋����v�Z����.get�K��؋������؋����s�����m�聄( )���Z�b�g
                    double l_dblDeposit = 
                         this.ifoDepositCalcCondition.getIfoDepositPerUnitTemp(l_strCurUnderlyingProductCode);
                    l_ifoSummary.setIfoDepositPerUnitTemp(l_dblDeposit);

                    //�������ʂ����Z����
                    l_ifoSummary.addOrderQuantity(
                        l_curSpecifiedIfoProductList[0].ifoDerivativeType,
                        l_curIfoTodayOpenOrder.isBuy(),
                        l_curIfoTodayOpenOrder.orderBizDate,
                        l_datBizDate0,
                        l_datBizDate1,
                        l_curIfoTodayOpenOrder.quantity);

                    //HashMap�Ɏw���ʐ敨OP���ʏW�v���i�[����
                    l_ifoSummarys.put(l_strCurUnderlyingProductCode, l_ifoSummary);
                }
            }
        }

        //HashMap����̎�
        if (l_ifoSummarys.isEmpty() == true)
        {
            //this.�w���ʐ敨OP���ʏW�v�ꗗ��null���Z�b�g
            this.setIfoSummaryContractPerIndexList(null);
        }
        //�ȊO�̎�
        else
        {
            //HashMap��z��ɕϊ�����
            WEB3IfoSummaryContractPerIndex[] l_list =
                new WEB3IfoSummaryContractPerIndex[l_ifoSummarys.size()];
            l_list = (WEB3IfoSummaryContractPerIndex[])l_ifoSummarys.values().toArray(l_list);

            //this.�w���ʐ敨OP���ʏW�v�ꗗ�ɔz����Z�b�g����
            this.setIfoSummaryContractPerIndexList(l_list);
        }
    }

    /**
     * (set�⏕����)<BR>
     * 
     * ����.�⏕������this.�⏕�����ɃZ�b�g����B<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     */
    private void setSubAccount(WEB3GentradeSubAccount l_subAccount)
    {
        this.subAccount = l_subAccount;
    }

    /**
     * (set�؋����v�Z����)<BR>
     * 
     * ����.�؋����v�Z������this.�؋����v�Z�����ɃZ�b�g����B<BR>
     * @@param l_ifoDepositCalcCondition - (�؋����v�Z����)<BR>
     * 
     * �؋����v�Z�����I�u�W�F�N�g�B<BR>
     * @@roseuid 41254D420282
     */
    private void setIfoDepositCalcCondition(WEB3IfoDepositCalcCondition l_ifoDepositCalcCondition)
    {
        this.ifoDepositCalcCondition = l_ifoDepositCalcCondition;
    }

    /**
     * (set�敨OP�ڋq�ړ�����)<BR>
     * 
     * ����.�敨OP�ڋq�ړ����ׂ�this.�敨OP�ڋq�ړ����ׂɃZ�b�g����B<BR>
     * 
     * @@param l_ifoCustomerTransfer - (�敨OP�ڋq�ړ�����)<BR>
     */
    private void setIfoCustomerTransfer(WEB3IfoCustomerTransfer l_ifoCustomerTransfer)
    {
        this.ifoCustomerTransfer = l_ifoCustomerTransfer;
    }

    /**
     * (set�������P���ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * ����.�������P���ʐ敨OP���ʏW�v�ꗗ��this.�������P���ʐ敨OP���ʏW�v�ꗗ�ɃZ�b�g����B<BR>
     * 
     * @@param l_ifoSummaryontractPerProductContractPriceList - (�������P���ʐ敨OP���ʏW�v�ꗗ)<BR>
     */
    private void setIfoSummaryContractPerProductContractPriceList(WEB3IfoSummaryContractPerProductContractPrice[] l_ifoSummaryontractPerProductContractPriceList)
    {
        this.ifoSummaryContractPerProductContractPriceList =
            l_ifoSummaryontractPerProductContractPriceList;
    }

    /**
     * (set�����ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * ����.�����ʐ敨OP���ʏW�v�ꗗ��this.�����ʐ敨OP���ʏW�v�ꗗ�ɃZ�b�g����B<BR>
     * 
     * @@param l_ifoSummaryContractPerProductList - (�����ʐ敨OP���ʏW�v�ꗗ)<BR>
     */
    private void setIfoSummaryContractPerProductList(WEB3IfoSummaryContractPerProduct[] l_ifoSummaryContractPerProductList)
    {
        this.ifoSummaryContractPerProductList = l_ifoSummaryContractPerProductList;
    }

    /**
     * (set�w���ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * ����.�w���ʐ敨OP���ʏW�v�ꗗ��this.�w���ʐ敨OP���ʏW�v�ꗗ�ɃZ�b�g����B<BR>
     * 
     * @@param l_ifoSummaryContractPerIndexList - (�w���ʐ敨OP���ʏW�v�ꗗ)<BR>
     */
    private void setIfoSummaryContractPerIndexList(WEB3IfoSummaryContractPerIndex[] l_ifoSummaryContractPerIndexList)
    {
        this.ifoSummaryContractPerIndexList = l_ifoSummaryContractPerIndexList;
    }

    /**
     * (get�敨OP�������ꗗ)<BR>
     * 
     * ����ID�ɑΉ�����敨OP�������̈ꗗ��ԋp����B<BR>
     * ������ID�ɑΉ�����敨OP������񂪑��݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * 
     * ����.�敨OP�������ꗗ�v�f���Ƃ�Loop�������s���A<BR>
     * �敨OP�������ꗗ.����ID == ����.����ID�ƂȂ�敨OP�������I�u�W�F�N�g�̔z���ԋp����B<BR>
     * 
     * @@param l_lngProductId - ����ID
     * @@param l_ifoProductList - �敨OP�������̈ꗗ
     * @@return webbroker3.ifodeposit.WEB3IfoProduct[]
     */
    private WEB3IfoProduct[] getIfoProductList(
        long l_lngProductId,
        WEB3IfoProduct[] l_ifoProductList)
    {
        //�敨OP������񂪑��݂��Ȃ���
        if (l_ifoProductList == null)
        {
            //null��ԋp����
            return null;
        }

        //�敨OP���������i�[����ArrayList�𐶐�����
        ArrayList l_ifoProducts = new ArrayList();

        //(�J�[�\��)�敨OP�������
        WEB3IfoProduct l_curIfoProduct = null;

        //�敨OP�������̗v�f����LOOP����
        for (int index = 0; index < l_ifoProductList.length; index++)
        {
            //(�J�[�\��)�敨OP���������擾����B
            l_curIfoProduct = l_ifoProductList[index];

            //�敨OP�������.����ID == ����.����ID�̎�
            if (l_curIfoProduct.productId == l_lngProductId)
            {
                //�敨OP��������ArrayList�Ɋi�[����
                l_ifoProducts.add(l_curIfoProduct);
            }
        }

        //ArrayList�̗v�f����̎�
        if (l_ifoProducts.isEmpty() == true)
        {
            //null��ԋp����
            return null;
        }
        //ArrayList�̗v�f����łȂ���
        else
        {
            //ArrayList��z��ɕϊ�����
            WEB3IfoProduct[] l_returns = new WEB3IfoProduct[l_ifoProducts.size()];
            l_returns = (WEB3IfoProduct[])l_ifoProducts.toArray(l_returns);

            //�z���ԋp����
            return l_returns;
        }
    }

    /**
     * (get������n���)<BR>
     * 
     * �����ɑΉ����铖���̎�n�����ԋp����B
     * (�����ɑΉ����铖���̎�n��������݂��Ȃ��ꍇ�́A0)
     * 
     * �P�j�@@�߂�l�ƂȂ��n������i�[����ϐ����쐬
     * �@@�@@�@@double ��n��� = 0
     * 
     * �Q�j�@@����.�敨OP�ۗL���ʏ��ꗗ�v�f���Ƃ�Loop����
     * �@@�@@�@@������.�敨OP�ۗL���ʏ��ꗗ��null�̏ꍇ�́A0��ԋp����B
     * 
     * �@@�@@�@@�Q�|�P�j�@@�敨OP�ۗL���ʏ��.get��n���( )�ɂ���n������擾����B
     * 
     * �@@�@@�@@[�����̐ݒ�]
     * �@@�@@�@@�@@�E�����P��ID�F�@@����.�����P��ID
     * �@@�@@�@@�@@�E�����F�@@����.����
     * 
     * �@@�@@�@@�Q�|�Q�j�@@��n��� = ��n��� + �敨OP�ۗL���ʏ��.get��n���( )�̖߂�l
     * 
     * �R�j�@@��n�����ԋp����B�@@
     * 
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * @@param l_ifoContractList - (�敨OP�ۗL���ʏ��ꗗ)<BR>
     * �敨OP�ۗL���ʏ��̈ꗗ�B<BR>
     * �Y������敨OP���ʂ����݂��Ȃ��ꍇ��null�B<BR>
     * 
     * @@param l_datOpenDate - (����)<BR>
     * 
     * @@return double
     */
    private double getCurrentBizDateNetAmount(
        long l_lngOrderUnitId,
        WEB3IfoContract[] l_ifoContractList,
        Date l_datOpenDate)
    {
        //�敨OP�ۗL���ʏ�񂪑��݂��Ȃ���
        if (l_ifoContractList == null)
        {
            //��n���(=0)��ԋp����
            return 0;
        }

        //��n���
        double l_dblNetAmount = 0;

        //(�J�[�\��)�敨OP�ۗL���ʏ��
        WEB3IfoContract l_curIfoContract = null;

        //�敨OP�ۗL���ʏ��̗v�f����LOOP����
        for (int index = 0; index < l_ifoContractList.length; index++)
        {
            //(�J�[�\��)�敨OP�ۗL���ʏ����擾
            l_curIfoContract = l_ifoContractList[index];

            //��n��� = ��n��� + �敨OP�ۗL���ʏ��.get��n���()�̖߂�l
            l_dblNetAmount =
                l_dblNetAmount + l_curIfoContract.getNetAmount(l_lngOrderUnitId, l_datOpenDate);
        }

        //��n�����ԋp����B
        return l_dblNetAmount;
    }

    /**
     * (get�⏕����)<BR>
     * 
     * this.�⏕������ԋp����B<BR>
     * 
     * @@return WEB3GentradeSubAccount
     */
    public WEB3GentradeSubAccount getSubAccount()
    {
        return this.subAccount;
    }

    /**
     * (get�؋����v�Z����)<BR>
     * 
     * this.�؋����v�Z������ԋp����B<BR>
     * 
     * @@return WEB3IfoDepositCalcCondition
     */
    public WEB3IfoDepositCalcCondition getIfoDepositCalcCondition()
    {
        return this.ifoDepositCalcCondition;
    }

    /**
     * (get�敨OP�ڋq�ړ�����)<BR>
     * 
     * this.�敨OP�ڋq�ړ����ׂ�ԋp����B<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoCustomerTransfer
     */
    public WEB3IfoCustomerTransfer getIfoCustomerTransfer()
    {
        return this.ifoCustomerTransfer;
    }

    /**
     * (get�������P���ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * this.�������P���ʐ敨OP���ʏW�v�ꗗ��ԋp����B<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProductContractPrice[]
     */
    public WEB3IfoSummaryContractPerProductContractPrice[] getIfoSummaryContractPerProductContractPriceList()
    {
        return this.ifoSummaryContractPerProductContractPriceList;
    }

    /**
     * (get�����ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * this.�����ʐ敨OP���ʏW�v�ꗗ��ԋp����B<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProduct[]
     */
    public WEB3IfoSummaryContractPerProduct[] getIfoSummaryContractPerProductList()
    {
        return this.ifoSummaryContractPerProductList;
    }

    /**
     * (get�w���ʐ敨OP���ʏW�v�ꗗ)<BR>
     * 
     * this.�w���ʐ敨OP���ʏW�v�ꗗ��ԋp����B<BR>
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex[]
     */
    public WEB3IfoSummaryContractPerIndex[] getIfoSummaryContractPerIndexList()
    {
        return this.ifoSummaryContractPerIndexList;
    }

    /**
     * (getSPAN�؋���)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�uSPAN�؋����v��ԋp����B<BR>
     * 
     * �P�j�@@SPAN�g�p�s�̏ꍇ(this.get�؋����v�Z����().isSPAN�g�p�\() == false)�A-1��ԋp����B<BR>
     * 
     * �Q�j�@@�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp����B<BR>
     * 
     * �R�j�@@����/���������݂��Ȃ��ꍇ(this..�����ʐ敨OP���ʏW�v�ꗗ == null)�A0��ԋp����B<BR>
     * 
     * �S�j�@@PC-SPAN�g�p�̂��߂ɕK�v�Ȍ��ʏW�v�ꗗ���i�[����ArrayList���쐬����B<BR>
     * 
     * �T�j�@@this.�����ʐ敨OP���ʏW�v�ꗗ�v�f���Ƃ�Loop�����B<BR>
     * 
     * �@@�@@�@@�T�|�P�j�@@���ʏW�vSpec�𐶐�����B<BR>
     * 
     * �@@�@@�@@�@@[�R���X�g���N�^�̐ݒ�]<BR>
     * �@@�@@�@@�@@�@@�E�����R�[�h�F�@@�����ʐ敨OP���ʏW�v�ꗗ.get�����R�[�h()<BR>
     * �@@�@@�@@�@@�@@�E�������ʁF�@@�����ʐ敨OP���ʏW�v�ꗗ.calc��������(����.�w���)<BR>
     * �@@�@@�@@�@@�@@�E�������ʁF�@@�����ʐ敨OP���ʏW�v�ꗗ.calc��������(����.�w���)<BR>
     * 
     * �@@�@@�@@�T�|�Q�j�@@�����������ʏW�vSpec��ArrayList�ɒǉ�����B<BR>
     * 
     * �U�j�@@ArrayList��z�񉻂��A���ʏW�vSpec�̔z����擾����B<BR>
     * 
     * �V�j�@@SPAN�؋������擾����B<BR>
     * 
     * �@@�@@�@@SPAN�؋��� = SPAN�ڑ��T�[�r�XImpl.getSPAN�؋���()<BR>
     * �@@�@@
     * �@@�@@�@@[�����̐ݒ�]<BR>
     *          �E�⏕�����F�@@this.�⏕����<BR>
     *          �E���ʏW�v�ꗗ�F�@@�擾�������ʏW�vSpec�̔z��<BR>
     * 
     * �W�j�@@�擾����SPAN�؋�����ԋp����B<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double getSPANIfoDeposit(int l_intReservedDate)
    {
        //SPAN�g�p�s�̏ꍇ(this.get�؋����v�Z����().isSPAN�g�p�\() == false)
        if (this.ifoDepositCalcCondition.isSPANUsable() == false)
        {
            //-1��ԋp����B
            return -1;
        }

        //�w�����0�A1�A2�łȂ���
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //SPAN�؋���(=0)��ԋp����
            return 0;
        }
        //���ʂ����݂��Ȃ��ꍇ
        if (this.ifoSummaryContractPerProductList == null)
        {
            //SPAN�؋���(=0)��ԋp����
            return 0;
        }

        //SPAN�؋���
        double l_dblSPAN = 0;

        //���ʏW�vSpec���i�[����ArrayList�𐶐�����
        ArrayList l_contractSpecs = new ArrayList();

        //(�J�[�\��)�����ʐ敨OP���ʏW�v���擾����
        WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

        //�����ʐ敨OP���ʏW�v�̗v�f����LOOP����
        for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
        {
            //(�J�[�\��)�����ʐ敨OP���ʏW�v���擾����
            l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

            //�����R�[�h���擾����
            String l_strCurProductCode = l_curIfoSummary.getProductCode();
            //�������ʂ��擾����
            double l_dblCurMarginLongNum =
                l_curIfoSummary.calcBuyContractQuantity(l_intReservedDate);
            //�������ʂ��擾����
            double l_dblCurMarginShortNum =
                l_curIfoSummary.calcSellContractQuantity(l_intReservedDate);

            //���ʏW�vSpec�𐶐�����
            WEB3GentradeTotalContractSpec l_curContractSpec =
                new WEB3GentradeTotalContractSpec(
                    l_strCurProductCode,
                    l_dblCurMarginLongNum,
                    l_dblCurMarginShortNum);

            //�����������ʏW�vSpec��ArrayList�ɒǉ�����
            l_contractSpecs.add(l_curContractSpec);
        }

        //ArrayList��z��ɕϊ�����
        WEB3GentradeTotalContractSpec[] l_contractSpecList =
            new WEB3GentradeTotalContractSpec[l_contractSpecs.size()];

        l_contractSpecList =
            (WEB3GentradeTotalContractSpec[])l_contractSpecs.toArray(l_contractSpecList);

        // SPAN�؋����T�[�r�X�I�u�W�F�N�g���擾����B
        WEB3GentradeSpanConnectService l_service =
            (WEB3GentradeSpanConnectService)Services.getService(
                WEB3GentradeSpanConnectService.class);

        //SPAN�؋������擾����
        l_dblSPAN = l_service.callSpanIfoDeposit(this.subAccount, l_contractSpecList);

        //�擾����SPAN�؋�����ԋp����
        return l_dblSPAN;
    }

    /**
     * (get���������z)<BR>
     * 
     * �u���������z�v��ԋp����B<BR>
     * 
     * this.get�؋����v�Z����( ).get�O���؋����s���z( )�̒l��ԋp����B<BR>
     * 
     * @@return double
     */
    public double getCurrentBizDateDemandAmount()
    {
        return this.ifoDepositCalcCondition.getPreBizDateIfoDepositLackCharge();
    }

    /**
     * (calc�؋����c��)<BR>
     * 
     * �u�؋����c���v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositBalance()
    {
        //������擾����
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //�؋����c��(���)���擾����B
        double l_dblIfoDepositBalance = this.calcIfoDepositBalance(l_intBaseDate);

        //�؋����c��(���)��ԋp����
        return l_dblIfoDepositBalance;
    }

    /**
     * (calc�؋����c��)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�؋����c���v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositBalance(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //�؋����c��(=0)��ԋp����
            return 0;
        }

        //�؋����c��(n) = �c��(n) + �U�֊z(n) + �敨���ϑ��v(n) + �I�v�V������n���(n) + �I�v�V���������T�Z��n���(n)
        double l_dblIfoDepositBalance =
            this.ifoCustomerTransfer.getBalance(l_intReservedDate)
                + this.ifoCustomerTransfer.getTransferAmount(l_intReservedDate)
                + this.ifoCustomerTransfer.getFuturesCloseProfitLoss(l_intReservedDate)
                + this.ifoCustomerTransfer.getOptionNetAmount(l_intReservedDate)
                + this.ifoCustomerTransfer.getOptionBuyEstimatedNetAmount(l_intReservedDate);

        //�؋����c����ԋp����
        return l_dblIfoDepositBalance;
    }


	/**
	 * (calc�؋����c��<�؋����s�����m��> (�w���))<BR>
	 * 
	 * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�؋����c��<�؋����s�����m��>(n)�v��ԋp����B<BR>
	 * 
	 * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
	 * 
	 * @@param l_intReservedDate - (�w���)<BR>
	 * 0�A1�A2�̂����ꂩ�̒l�B<BR>
	 * 
	 * @@return double
	 */
	public double calcIfoDepositBalanceTemp(int l_intReservedDate)
	{
		//�w�����0�A1�A2�ȊO�̎�
		if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
			&& l_intReservedDate != WEB3IfoReservedDateDef.T_1
			&& l_intReservedDate != WEB3IfoReservedDateDef.T_2)
		{
			//�؋����c��(=0)��ԋp����
			return 0;
		}

		//�؋����c��(n) <�؋����s�����m��> = �c��(n) + �U�֊z[T+0] + �敨���ϑ��v(n) + �I�v�V������n���(n)
		double l_dblIfoDepositBalanceTemp =
			this.ifoCustomerTransfer.getBalance(l_intReservedDate)
				+ this.ifoCustomerTransfer.getCurrentBizDateTransferAmount()
				+ this.ifoCustomerTransfer.getFuturesCloseProfitLoss(l_intReservedDate)
				+ this.ifoCustomerTransfer.getOptionNetAmount(l_intReservedDate);

		//�؋����c����ԋp����
		return l_dblIfoDepositBalanceTemp;
	}


    /**
     * (calc����؋����c��)<BR>
     * 
     * �u����؋����c���v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcReceiptIfoDepositBalance()
    {
        //������擾����
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //����؋����c��(���)���擾����B
        double l_dblReceiptIfoDepositBalance = this.calcReceiptIfoDepositBalance(l_intBaseDate);

        //����؋����c��(���)��ԋp����
        return l_dblReceiptIfoDepositBalance;
    }

    /**
     * (calc����؋����c��)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����؋����c���v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcReceiptIfoDepositBalance(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //����؋����c��(=0)��ԋp����
            return 0;
        }

        //����؋����c��(n)�@@=�@@�؋����c��(n)�@@+�@@�敨�]�����v
        double l_dblReceiptIfoDepositBalance =
            this.calcIfoDepositBalance(l_intReservedDate) + this.calcFuturesAppraisalProfitLoss();

        //����؋����c��(n)��ԋp����
        return l_dblReceiptIfoDepositBalance;
    }

    /**
     * (calc�؋������v�z)<BR>
     * 
     * �u�؋������v�z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmount()
    {
        //������擾����
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //�؋������v�z(���)���擾����B
        double l_dblIfoDepositRequiredAmount = this.calcIfoDepositRequiredAmount(l_intBaseDate);

        //�؋������v�z(���)��ԋp����
        return l_dblIfoDepositRequiredAmount;
    }

    /**
     * (calc�؋������v�z)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�؋������v�z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmount(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //�؋������v�z(=0)��ԋp����
            return 0;
        }

        /*
         * �؋������v�z(n)���v�Z����
         */

        //�؋������v�z(n)
        double l_dblIfoDepositRequiredAmount = 0;

        //SPAN�؋����̗p�̎�
        if (this.ifoDepositCalcCondition.isSPANUsable() == true)
        {
            //�؋������v�z(n)�@@=�@@(SPAN�؋���(n) �| �l�b�g�I�v�V�������l���z(n)) �~ SPAN�W��
            BigDecimal l_bdSPANIfoDeposit =
                new BigDecimal(this.getSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));
            BigDecimal l_bdSPANFactor =
                new BigDecimal(this.ifoDepositCalcCondition.getSPANFactor());

            BigDecimal l_bdIfoDepositRequiredAmount =
                l_bdSPANIfoDeposit.subtract(l_bdNetOption).multiply(l_bdSPANFactor);

            //�����_�ȉ��؂�グ�������s��
            l_bdIfoDepositRequiredAmount =
                l_bdIfoDepositRequiredAmount.setScale(0, BigDecimal.ROUND_CEILING);

            l_dblIfoDepositRequiredAmount = l_bdIfoDepositRequiredAmount.doubleValue();
        }
        //SPAN�؋����s�̗p�̎�
        else
        {
            //�؋������v�z(n)�@@=�@@�Ȉ�SPAN�؋���(n) �| �l�b�g�I�v�V�������l���z(n)
            BigDecimal l_bdSimpleSPANIfoDeposit =
                new BigDecimal(this.calcSimpleSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));

            l_dblIfoDepositRequiredAmount =
                l_bdSimpleSPANIfoDeposit.subtract(l_bdNetOption).doubleValue();
        }

        //�v�Z�����؋������v�z(n)��ԋp����
        return l_dblIfoDepositRequiredAmount;
    }

    /**
     * (calc�؋������v�z���b�h)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�؋������v�z���b�h�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmountRed(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //�؋������v�z(=0)��ԋp����
            return 0;
        }

        /*
         * �؋������v�z���b�h(n)���v�Z����
         */

        //�؋������v�z���b�h(n)
        double l_dblIfoDepositRequiredAmountRed = 0;

        //SPAN�؋����̗p�̎�
        if (this.ifoDepositCalcCondition.isSPANUsable() == true)
        {
            //�؋������v�z���b�h(n)�@@=�@@(SPAN�؋���(n) �| �l�b�g�I�v�V�������l���z(n)) �~ SPAN�W�����b�h
            BigDecimal l_bdSPANIfoDeposit =
                new BigDecimal(this.getSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));
            BigDecimal l_bdSPANFactorRed =
                new BigDecimal(this.ifoDepositCalcCondition.getSPANFactorRed());

            BigDecimal l_bdIfoDepositRequiredAmountRed =
                l_bdSPANIfoDeposit.subtract(l_bdNetOption).multiply(l_bdSPANFactorRed);

            //�����_�ȉ��؂�グ�������s��
            l_bdIfoDepositRequiredAmountRed =
                l_bdIfoDepositRequiredAmountRed.setScale(0, BigDecimal.ROUND_CEILING);

            l_dblIfoDepositRequiredAmountRed = l_bdIfoDepositRequiredAmountRed.doubleValue();
        }
        //SPAN�؋����s�̗p�̎�
        else
        {
            //�؋������v�z���b�h(n)�@@=�@@�Ȉ�SPAN�؋������b�h(n) �| �l�b�g�I�v�V�������l���z(n)
            BigDecimal l_bdSimpleSPANIfoDepositRed =
                new BigDecimal(this.calcSimpleSPANIfoDepositRed(l_intReservedDate));
            BigDecimal l_bdNetOption =
                new BigDecimal(this.calcNetOptionTotalAmount(l_intReservedDate));

            l_dblIfoDepositRequiredAmountRed =
                l_bdSimpleSPANIfoDepositRed.subtract(l_bdNetOption).doubleValue();
        }

        //�v�Z�����؋������v�z���b�h(n)��ԋp����
        return l_dblIfoDepositRequiredAmountRed;
    }

    /**
     * (calc�؋����]�͊z)<BR>
     * 
     * �u�؋����]�͊z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositTradingPowerAmount()
    {
        //������擾����
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //�؋����]�͊z(���)���擾����B
        double l_dblIfoDepositTradingPowerAmount =
            this.calcIfoDepositTradingPowerAmount(l_intBaseDate);

        //�؋����]�͊z(���)��ԋp����
        return l_dblIfoDepositTradingPowerAmount;
    }

    /**
     * (calc�؋����]�͊z)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�؋����]�͊z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double
     */
    public double calcIfoDepositTradingPowerAmount(int l_intReservedDate)
    {
        //�w�����1�A2 �ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //�؋����]�͊z(=0)��ԋp����
            return 0;
        }

        /*
         * �؋����]�͊z(n)���v�Z����
         */
        //�؋����]�͊z(n)
        double l_dblTradingPowerAmount = 0;

        //����؋����c��(n)
        double l_dblReceiptIfoDeposit = this.calcReceiptIfoDepositBalance(l_intReservedDate);
        //�K�v�Œ�ۏ؋�
        double l_dblMinIfoDeposit = this.ifoDepositCalcCondition.getMinIfoDeposit();
        //�������z
        double l_dblNonPayAmount = this.calcNonPayAmount();

        //�敨OP�V�K���K���ڋq�A�܂��́A�؋����s���ڋq�̏ꍇ
        if (this.ifoDepositCalcCondition.isNewOpenTradingPowerAvailable() == false
            || l_dblReceiptIfoDeposit < l_dblMinIfoDeposit
            || l_dblNonPayAmount > 0)
        {
            //�؋����]�͊z(n)��0��������
            l_dblTradingPowerAmount = 0;
        }
        //�ȊO�̏ꍇ
        else
        {
            //�؋����]�͊z(n)�@@=�@@ ����؋����c��(n) �| �؋������v�z(n)
            l_dblTradingPowerAmount =
                l_dblReceiptIfoDeposit - this.calcIfoDepositRequiredAmount(l_intReservedDate);
        }

        //�v�Z�����؋����]�͊z(n)��ԋp����
        return l_dblTradingPowerAmount;
    }

    /**
     * (calc�؋����U�֗]�͊z)<BR>
     * 
     * �u�؋����U�֗]�͊z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositTransferableAmount()
    {
        //�������z���擾����
        double l_dblNonPayAmount = this.calcNonPayAmount();

        //�������z��0���傫����
        if (l_dblNonPayAmount > 0)
        {
            //0��ԋp����
            return 0;
        }

        //����J�����_�R���e�L�X�g���X���b�h���[�J������擾����B
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //��t���ԋ敪���擾����
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //�����R�[�h���擾����
        String l_strProductCode = l_clendarContext.getProductCode();
        
        //��t���ԋ敪��ݒ肷��
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.MARGIN_TRANSFER);
        WEB3GentradeTradingTimeManagement.resetProductCode(WEB3ProductCodeDef.DEFAULT);
       
        //�؋����U�֗]�͊z
        double l_dblTransferableAmount0 = 0;
        double l_dblTransferableAmount1 = 0;
        double l_dblTransferableAmount2 = 0;
        double l_dblTransferableAmount = 0;
        
        try
        { 
            // ������
            Date l_datOrderBizDate =
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
            // �c�Ɠ�T+0
            Date l_datBaseDate = this.ifoDepositCalcCondition.getCurrentBizDate();
            
            l_dblTransferableAmount1 =
                this.calcIfoDepositTransferableAmount(WEB3IfoReservedDateDef.T_1);
            l_dblTransferableAmount2 =
                this.calcIfoDepositTransferableAmount(WEB3IfoReservedDateDef.T_2);
            
            //�U�ւ̔�������T+0�̏ꍇ    
            if (WEB3DateUtility.compareToDay(l_datOrderBizDate, l_datBaseDate) == 0)
            {
                //�؋����U�֗]�͊z = Min(�؋����U�֗]�͊z(T+0), �؋����U�֗]�͊z(T+1), (�؋����U�֗]�͊z(T+2))
                l_dblTransferableAmount0 =
                    this.calcIfoDepositTransferableAmount(WEB3IfoReservedDateDef.T_0);
                l_dblTransferableAmount = Math.min(l_dblTransferableAmount0, l_dblTransferableAmount1);
                l_dblTransferableAmount = Math.min(l_dblTransferableAmount, l_dblTransferableAmount2);
            }
            //�U�ւ̔�������T+1�̏ꍇ
            else
            {
                //�؋����U�֗]�͊z = Min(�؋����U�֗]�͊z(T+1), (�؋����U�֗]�͊z(T+2))
                l_dblTransferableAmount = Math.min(l_dblTransferableAmount1, l_dblTransferableAmount2);
            }
        }
        catch (WEB3SystemLayerException sle)
        {
            log.error(sle.getMessage(), sle);
            throw new WEB3BaseRuntimeException(
                sle.getErrorInfo(),
                sle.getErrorMethod(),
                sle.getErrorMessage(),
                sle.getException());
        }

        //�؋����U�֗]�͊z�����ł��������A�؋����U�֗]�͊z��0��������
        l_dblTransferableAmount = Math.max(0, l_dblTransferableAmount);
        
        //����J�����_�R���e�L�X�g��������Ԃɖ߂�
        WEB3GentradeTradingTimeManagement.resetTradingTimeType(l_strTradingTimeType);
        WEB3GentradeTradingTimeManagement.resetProductCode(l_strProductCode);

        //�v�Z�����؋����U�֗]�͊z��ԋp����
        return l_dblTransferableAmount;
    }

    /**
     * (calc�؋����U�֗]�͊z)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�؋����U�֗]�͊z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositTransferableAmount(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //�؋����U�֗]�͊z(n)�@@=�@@ ����؋����c��(n) �| �؋������v�z(n) �~ �U�֗]�͌W��
        BigDecimal l_bdReceiptIfoDepositBalance =
            new BigDecimal(this.calcReceiptIfoDepositBalance(l_intReservedDate));
        BigDecimal l_bdRequiredAmount =
            new BigDecimal(this.calcIfoDepositRequiredAmount(l_intReservedDate));
        BigDecimal l_bdTransferPowerFactor =
            new BigDecimal(Double.toString(this.ifoDepositCalcCondition.getTransferPowerFactor()));

        BigDecimal l_bdTransferableAmount =
            l_bdReceiptIfoDepositBalance.subtract(
                l_bdRequiredAmount.multiply(l_bdTransferPowerFactor));

        //�����_�ȉ��؎̂ď������s��
        l_bdTransferableAmount = l_bdTransferableAmount.setScale(0, BigDecimal.ROUND_DOWN);
        double l_dblTransferableAmount = l_bdTransferableAmount.doubleValue();

        //�v�Z�����؋����U�֗]�͊z(n)��ԋp����B
        return l_dblTransferableAmount;
    }

    /**
     * (calc���|�W�V�����\����)<BR>
     * 
     * �����Ŏw�肳�ꂽ�����Y�����R�[�h�́u���|�W�V�����\���ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@return double
     */
    public double calcPossibleBuyQty(String l_strUnderlyingProductCode)
    {
        //������擾����
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //���|�W�V�����\����(���)���擾����B
        double l_dblBuyQty = this.calcPossibleBuyQty(l_strUnderlyingProductCode, l_intBaseDate);

        //���|�W�V�����\����(���)��ԋp����
        return l_dblBuyQty;
    }

    /**
     * (calc���|�W�V�����\����)<BR>
     * 
     * �����Ŏw�肳�ꂽ�����Y�����R�[�h�̎w���(=n)�́u���|�W�V�����\���ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@param l_intReservedDate - (�w��)<BR>
     * 1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleBuyQty(String l_strUnderlyingProductCode, int l_intReservedDate)
    {
        //�w�����1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        /*
         * ���|�W�V�������z�A���|�W�V�������z���v�Z����B
         */
        //���|�W�V�������z(n)
        double l_dblBuyAmt = 0;
        //���|�W�V�������z(n)
        double l_dblSellAmt = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �A�܂��́A�؋����v�Z.calc�؋����]�͊z(n) < 0 �̏ꍇ
        //(����/����(*)�����݂��Ȃ��A�܂��́A�؋����]�͊z���}�C�i�X�̏ꍇ)
        if (this.ifoSummaryContractPerIndexList == null
            || this.calcIfoDepositTradingPowerAmount(l_intReservedDate) < 0)
        {
            //���|�W�V�������z(n) =  0
            l_dblBuyAmt = 0;
            //���|�W�V�������z(n) =  0
            l_dblSellAmt = 0;
        }
        //�ȊO�̏ꍇ
        else
        {
            //���|�W�V�������z(n) =  �؋����v�Z.calc���|�W�V�������z(n)
            l_dblBuyAmt = this.calcBuyContractAmt(l_intReservedDate);
            //���|�W�V�������z(n) =  �؋����v�Z.calc���|�W�V�������z(n)
            l_dblSellAmt = this.calcSellContractAmt(l_intReservedDate);
        }

        /*
         * ���|�W�V�����\����(n)���v�Z����B
         */
        //���|�W�V�����\����(n)
        double l_dblPossibleBuyQty = 0;

        //���|�W�V�����\����(n) =  (�؋����]�͊z(n) + Max(0, ���|�W�V�������z(n) - ���|�W�V�������z(n)) / �K��؋���
        //�v�Z���ʂ�0�ȏ�̏ꍇ�A�����_�ȉ��؂�̂āA0�����̏ꍇ�A�����_�ȉ��؂�グ�Ƃ���
        double l_dblTmp =
            this.calcIfoDepositTradingPowerAmount(l_intReservedDate)
                + Math.max(0, l_dblSellAmt - l_dblBuyAmt);
        double l_dblPerUnit =
            this.ifoDepositCalcCondition.getIfoDepositPerUnit(l_strUnderlyingProductCode);

        BigDecimal l_bdTmp = new BigDecimal(Double.toString(l_dblTmp));
        BigDecimal l_bdPerUnit = new BigDecimal(Double.toString(l_dblPerUnit));

        l_dblPossibleBuyQty = l_bdTmp.divide(l_bdPerUnit, 0, BigDecimal.ROUND_FLOOR).doubleValue();

        //�v�Z�������|�W�V�����\����(n)��ԋp����
        return l_dblPossibleBuyQty;
    }

    /**
     * (calc���|�W�V�����\����)<BR>
     * 
     * �����Ŏw�肳�ꂽ�����Y�����R�[�h�́u���|�W�V�����\���ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@return double
     */
    public double calcPossibleSellQty(String l_strUnderlyingProductCode)
    {
        //������擾����
        int l_intBaseDate = this.ifoDepositCalcCondition.getIfoDepositBaseDate();

        //���|�W�V�����\����(���)���擾����B
        double l_dblSellQty = this.calcPossibleSellQty(l_strUnderlyingProductCode, l_intBaseDate);

        //���|�W�V�����\����(���)��ԋp����
        return l_dblSellQty;
    }

    /**
     * (calc���|�W�V�����\����)<BR>
     * 
     * �����Ŏw�肳�ꂽ�����Y�����R�[�h�̎w���(=n)�́u���|�W�V�����\���ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_strUnderlyingProductCode - �����Y�����R�[�h
     * @@param l_intReservedDate - (�w���)<BR>
     * 1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleSellQty(String l_strUnderlyingProductCode, int l_intReservedDate)
    {
        //�w�����1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        /*
         * ���|�W�V�������z�A���|�W�V�������z���v�Z����B
         */
        //���|�W�V�������z(n)
        double l_dblBuyAmt = 0;
        //���|�W�V�������z(n)
        double l_dblSellAmt = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �A�܂��́A�؋����v�Z.calc�؋����]�͊z(n) < 0 �̏ꍇ
        //(����/����(*)�����݂��Ȃ��A�܂��́A�؋����]�͊z���}�C�i�X�̏ꍇ)
        if (this.ifoSummaryContractPerIndexList == null
            || this.calcIfoDepositTradingPowerAmount(l_intReservedDate) < 0)
        {
            //���|�W�V�������z(n) =  0
            l_dblBuyAmt = 0;
            //���|�W�V�������z(n) =  0
            l_dblSellAmt = 0;
        }
        //�ȊO�̏ꍇ
        else
        {
            //���|�W�V�������z(n) =  �؋����v�Z.calc���|�W�V�������z(n)
            l_dblBuyAmt = this.calcBuyContractAmt(l_intReservedDate);
            //���|�W�V�������z(n) =  �؋����v�Z.calc���|�W�V�������z(n)
            l_dblSellAmt = this.calcSellContractAmt(l_intReservedDate);
        }

        /*
         * ���|�W�V�����\����(n)���v�Z����B
         */
        //���|�W�V�����\����(n)
        double l_dblPossibleSellQty = 0;

        //���|�W�V�����\����(n) =  (�؋����]�͊z(n) + Max(0, ���|�W�V�������z(n) - ���|�W�V�������z(n)) / �K��؋���
        //�v�Z���ʂ�0�ȏ�̏ꍇ�A�����_�ȉ��؂�̂āA0�����̏ꍇ�A�����_�ȉ��؂�グ�Ƃ���
        double l_dblTmp =
            this.calcIfoDepositTradingPowerAmount(l_intReservedDate)
                + Math.max(0, l_dblBuyAmt - l_dblSellAmt);
        double l_dblPerUnit =
            this.ifoDepositCalcCondition.getIfoDepositPerUnit(l_strUnderlyingProductCode);

        BigDecimal l_bdTmp = new BigDecimal(Double.toString(l_dblTmp));
        BigDecimal l_bdPerUnit = new BigDecimal(Double.toString(l_dblPerUnit));

        l_dblPossibleSellQty = l_bdTmp.divide(l_bdPerUnit, 0, BigDecimal.ROUND_FLOOR).doubleValue();

        //�v�Z�������|�W�V�����\����(n)��ԋp����
        return l_dblPossibleSellQty;
    }

    /**
     * (calc�������z)<BR>
     * 
     * �u�������z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcNonPayAmount()
    {
        //�������z
        double l_dblNonPayAmount = 0;
  
        //�ma�D�؋����v�Z.�؋����v�Z����.is�؋����s���z��Ǘ��@@==�@@true�̏ꍇ�n
        //�i�؋����s���z��Ǘ���Ёj
        if (this.ifoDepositCalcCondition.isLackChargeNonManagement()){
            //�������z =  0
            return 0;                        
        }
      
        //�m���D�؋����v�Z.�؋����v�Z����.is�؋����s���z��Ǘ��@@==�@@false�̏ꍇ�n
        //�i�؋����s���z�Ǘ���Ёj
        //�����̏؋����s���m�莞�Ԃ̏ꍇ
        // [���|�P. �؋����v�Z.�؋����v�Z����.is�؋����s�����[�����M�� == true �̏ꍇ]
        if(this.ifoDepositCalcCondition.isIfoDepositMailFlag() == true)
        {
           //���������z
            double l_dblDemandAmount1 = this.calcNextBizDateDemandAmount();
            //�����z(T+1)
            double l_dblNextBizDateCashinAmount = this.ifoCustomerTransfer.getNextBizDateCashinAmount();
            
            //�������z =  Max(0, ���������z - �����z(T+1))
            l_dblNonPayAmount =
                Math.max(
                    0,
                    l_dblDemandAmount1 - l_dblNextBizDateCashinAmount);
        }
        //�؋����s�����v�Z���ԑт̏ꍇ�@@
        //  [b-2.�؋����v�Z.�؋����v�Z����.is���Z�l�����M�ρ@@== true �@@���@@
        //  �؋����v�Z.�؋����v�Z����.is�؋����s�����[�����M�ρ@@==�@@false�̏ꍇ]
        else if (this.ifoDepositCalcCondition.isQuickReportReceived() && 
                  !this.ifoDepositCalcCondition.isIfoDepositMailFlag())
        {
            //�؋����s���z�����v�Z��(T+1)
            double l_dblLackChargeTemp = this.calcIfoDepositLackChargeTemp(WEB3IfoReservedDateDef.T_1) ;
            //�����z(T+1)
            double l_dblNextBizDateCashinAmount = this.ifoCustomerTransfer.getNextBizDateCashinAmount();

            //�������z =  Max(0, �؋����s���z�����v�Z��(T+1) - �����z(T+1))
            l_dblNonPayAmount =
                Math.max(
                    0,
                    l_dblLackChargeTemp - l_dblNextBizDateCashinAmount);
        }
        // �����̏؋����s�����v�Z�O���ԑт̏ꍇ
        else
        {
            //���������z
            double l_dblDemandAmount0 = this.getCurrentBizDateDemandAmount();
            //�����z(T+0)
            double l_dblCurrentBizDateCashinAmount = this.ifoCustomerTransfer.getCurrentBizDateCashinAmount();

            //�������z =  Max(0, ���������z - �����z(T+0))
            l_dblNonPayAmount =
                Math.max(
                    0,
                    l_dblDemandAmount0 - l_dblCurrentBizDateCashinAmount);
        }

        //�v�Z�����������z��ԋp����
        return l_dblNonPayAmount;
    }

    /**
     * (calc���������z)<BR>
     * 
     * �u���������z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcNextBizDateDemandAmount()
    {
        //���������z
        double l_dblDemandAmount = 0;

        //�����؋����s���z
        double l_dblLackCharge0 =
            this.ifoDepositCalcCondition.getCurrentBizDateIfoDepositLackCharge();
        //�؋����c��(T+1)
        double l_dblIfoDepBalance = this.calcIfoDepositBalance(WEB3IfoReservedDateDef.T_1);
        //�敨�]�����v
        double l_dblFuturesAppProLos = this.calcFuturesAppraisalProfitLoss();
        //�؋������v�z(T+1)
        double l_dblRequiredAmount = this.calcIfoDepositRequiredAmount(WEB3IfoReservedDateDef.T_1);

        //�����؋����s����(�؋����c��(T+1) + �敨�]�����v) �| �؋������v�z(T+1)
        double l_dblLackCharge1 = l_dblIfoDepBalance + l_dblFuturesAppProLos - l_dblRequiredAmount;

        //[a.�����؋����s���z > 0 �̏ꍇ]
        // (�����Ɋm�肵���؋����s�����������Ă���ꍇ)
        if (l_dblLackCharge0 > 0)
        {
            //���������z = �����s���z
            l_dblDemandAmount = l_dblLackCharge0;

        }
        //[b.�؋����v�Z.�؋����v�Z����.is�؋����s�����[�����M�ρ@@==�@@true�@@����
        //   �����؋����s���z == 0�̏ꍇ]
        // (�����ɏ؋����s�����������Ă��Ȃ��ꍇ)
        else if (this.ifoDepositCalcCondition.isIfoDepositMailFlag() && 
                   l_dblLackCharge0 == 0)
        {
            //���������z = 0
       	    l_dblDemandAmount = 0;
        }
        //[c.�؋����v�Z.�؋����v�Z����.is���Z�l�����M�ρ@@== true �@@���@@
        //   �؋����v�Z.�؋����v�Z����.is�؋����s�����[�����M�ρ@@==�@@false�̏ꍇ]
        // (�؋����s�����v�Z���ԑт̏ꍇ)
        else if (this.ifoDepositCalcCondition.isQuickReportReceived() && 
                  !this.ifoDepositCalcCondition.isIfoDepositMailFlag())
        {
            //���������z = �؋����s���z�����v�Z��(T+1)
            l_dblDemandAmount = this.calcIfoDepositLackChargeTemp(WEB3IfoReservedDateDef.T_1);

        }
        //[d.(�؋����c��(T+1) + �敨�]�����v) - �؋������v�z(T+1) < 0 �̏ꍇ]
        // (�����Ɍv�Z�l�ɂ��؋����s�����������Ă���ꍇ)
        else if (l_dblLackCharge1 < 0)
        {
            //���������z = Abs((�؋����c��(T+1) + �敨�]�����v) - �؋������v�z(T+1))
            l_dblDemandAmount = Math.abs(l_dblLackCharge1);
        }
        //[e.(�؋����c��(T+1) + �敨�]�����v) - �؋������v�z(T+1) �� 0 �̏ꍇ] 
        // (�����Ɍv�Z�l�ɂ��؋����s�����������Ă��Ȃ��ꍇ)
        else
        {
            //���������z = 0
            l_dblDemandAmount = 0;
        }

        //�v�Z�������������z��ԋp����
        return l_dblDemandAmount;
    }

    /**
     * (calc�敨�]�����v)<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcFuturesAppraisalProfitLoss()
    {
        //�敨�]�����v
        double l_dblProfitLoss = 0;

        //�敨�]�����v�@@=�@@�敨�����]�����v + �敨�����]�����v
        l_dblProfitLoss =
            this.calcFuturesBuyAppraisalProfitLoss() + this.calcFuturesSellAppraisalProfitLoss();

        //�v�Z�����敨�]�����v��ԋp����        
        return l_dblProfitLoss;
    }

    /**
     * (calc�敨�����]�����v)<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcFuturesBuyAppraisalProfitLoss()
    {
        //�敨�����]�����v
        double l_dblBuyProfitLoss = 0;

        /*
         * �敨�����]�����v���v�Z����B
         */

        //���ʂ����݂��Ȃ���
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //�敨�����]�����v��0��������
            l_dblBuyProfitLoss = 0;
        }
        //���ʂ����݂��鎞
        else
        {
            //(�J�[�\��)�������P���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //�������P���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(�J�[�\��)�������P���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //�敨�����]�����v��Sum(�������P���ʐ敨�����]�����v)
                l_dblBuyProfitLoss =
                    l_dblBuyProfitLoss + l_curIfoSummary.calcFuturesBuyContractProfitLoss();
            }
        }

        //�v�Z�����敨�����]�����v��ԋp����        
        return l_dblBuyProfitLoss;
    }

    /**
     * (calc�敨�����]�����v)<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcFuturesSellAppraisalProfitLoss()
    {
        //�敨�����]�����v
        double l_dblSellProfitLoss = 0;

        /*
         * �敨�����]�����v���v�Z����B
         */

        //���ʂ����݂��Ȃ���
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //�敨�����]�����v��0��������
            l_dblSellProfitLoss = 0;
        }
        //���ʂ����݂��鎞
        else
        {
            //(�J�[�\��)�������P���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //�������P���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(�J�[�\��)�������P���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //�敨�����]�����v��Sum(�������P���ʐ敨�����]�����v)
                l_dblSellProfitLoss =
                    l_dblSellProfitLoss + l_curIfoSummary.calcFuturesSellContractProfitLoss();
            }
        }

        //�v�Z�����敨�����]�����v��ԋp����        
        return l_dblSellProfitLoss;
    }

    /**
     * (calc�I�v�V�����]�����v)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�I�v�V�����]�����v�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcOptionAppraisalProfitLoss()
    {
        //�I�v�V�����]�����v
        double l_dblProfitLoss = 0;

        //�I�v�V�����]�����v�@@=�@@�I�v�V���������]�����v + �I�v�V���������]�����v
        l_dblProfitLoss =
            this.calcOptionBuyAppraisalProfitLoss() + this.calcOptionSellAppraisalProfitLoss();

        //�v�Z�����I�v�V�����]�����v��ԋp����        
        return l_dblProfitLoss;
    }

    /**
     * (calc�I�v�V���������]�����v)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�I�v�V���������]�����v�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcOptionBuyAppraisalProfitLoss()
    {
        //�I�v�V���������]�����v
        double l_dblBuyProfitLoss = 0;

        /*
         * �I�v�V���������]�����v���v�Z����B
         */

        //���ʂ����݂��Ȃ���
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //�I�v�V���������]�����v��0��������
            l_dblBuyProfitLoss = 0;
        }
        //���ʂ����݂��鎞
        else
        {
            //(�J�[�\��)�������P���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //�������P���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(�J�[�\��)�������P���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //�I�v�V���������]�����v��Sum(�������P���ʃI�v�V���������]�����v)
                l_dblBuyProfitLoss =
                    l_dblBuyProfitLoss + l_curIfoSummary.calcOptionBuyContractProfitLoss();
            }
        }

        //�v�Z�����I�v�V���������]�����v��ԋp����        
        return l_dblBuyProfitLoss;
    }

    /**
     * (calc�I�v�V���������]�����v)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�I�v�V���������]�����v�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcOptionSellAppraisalProfitLoss()
    {
        //�I�v�V���������]�����v
        double l_dblSellProfitLoss = 0;

        /*
         * �I�v�V���������]�����v���v�Z����B
         */

        //���ʂ����݂��Ȃ���
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //�I�v�V���������]�����v��0��������
            l_dblSellProfitLoss = 0;
        }
        //���ʂ����݂��鎞
        else
        {
            //(�J�[�\��)�������P���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //�������P���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //(�J�[�\��)�������P���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //�I�v�V���������]�����v��Sum(�������P���ʃI�v�V���������]�����v)
                l_dblSellProfitLoss =
                    l_dblSellProfitLoss + l_curIfoSummary.calcOptionSellContractProfitLoss();
            }
        }

        //�v�Z�����I�v�V���������]�����v��ԋp����        
        return l_dblSellProfitLoss;
    }

    /**
     * (calc�|�W�V�����o�����X)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�|�W�V�����o�����X�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoPositionBalance
     */
    public WEB3IfoPositionBalance calcPositionBalance(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //null��ԋp����
            return null;
        }

        //�|�W�V�����o�����X
        double l_dblBalance = 0;
        BigDecimal l_bdBalance = new BigDecimal(String.valueOf(l_dblBalance));
        String l_strType = null;
        //���|�W�V��������(n)
        double l_dblBuyQty = this.calcBuyContractQty(l_intReservedDate);
        BigDecimal l_bdBuyQty = new BigDecimal(String.valueOf(l_dblBuyQty));
        //���|�W�V��������(n)
        double l_dblSellQty = this.calcSellContractQty(l_intReservedDate);
        BigDecimal l_bdSellQty = new BigDecimal(String.valueOf(l_dblSellQty));

        //���|�W�V��������(n)��0�A���|�W�V��������(n)��0�̎�
        if (l_dblBuyQty == 0 && l_dblSellQty == 0)
        {
            //null��ԋp����
            return null;
        }
        //�����߂̎�
        else if (l_dblBuyQty > l_dblSellQty)
        {
            //�|�W�V�����o�����X(n)�����|�W�V��������(n)�|���|�W�V��������(n)
            l_bdBalance = l_bdBuyQty.subtract(l_bdSellQty);
            //�|�W�V�����o�����X�敪(n)�� �g�����߁h
            l_strType = WEB3IfoPositionBalanceTypeDef.BUY_OVER;
        }
        //�����߂̎�
        else if (l_dblSellQty > l_dblBuyQty)
        {
            //�|�W�V�����o�����X(n)�����|�W�V��������(n)�|���|�W�V��������(n)�@@
            l_bdBalance = l_bdSellQty.subtract(l_bdBuyQty);
            //�|�W�V�����o�����X�敪(n)���g�����߁h
            l_strType = WEB3IfoPositionBalanceTypeDef.SELL_OVER;
        }
        //�ȊO�̎�(���|�W�V��������(n)�����|�W�V��������(n))
        else
        {
            //�|�W�V�����o�����X(n)���O
            l_dblBalance = 0;
            //�|�W�V�����o�����X�敪(n)���g�j���[�g�����h
            l_strType = WEB3IfoPositionBalanceTypeDef.NEUTRAL;
        }

        //�敨OP�|�W�V�����o�����X�𐶐����ԋp����
        l_dblBalance = l_bdBalance.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
        return new WEB3IfoPositionBalance(l_dblBalance, l_strType);
    }

    /**
     * (calc�l�b�g�I�v�V�������l���z)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�l�b�g�I�v�V�������l���z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcNetOptionTotalAmount(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //�l�b�g�I�v�V�������l���z(n)��Min(0, ���I�v�V�������l���z(n)�|���I�v�V�������l���z(n))
        double l_dblNetOption =
            Math.min(
                0,
                this.calcOptionBuyTotalAmount(l_intReservedDate)
                    - this.calcOptionSellTotalAmount(l_intReservedDate));

        //�v�Z�����l�b�g�I�v�V�������l���z(n)��ԋp����
        return l_dblNetOption;
    }

    /**
     * (calc���I�v�V�������l���z)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���I�v�V�������l���z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionBuyTotalAmount(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //���I�v�V�������l���z
        double l_dblBuyNetOption = 0;

        //���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerProductList == null)
        {
            //���I�v�V�������l���z��0����
            l_dblBuyNetOption = 0;
        }
        //���ʁ^���������݂��鎞
        else
        {
            //(�J�[�\��)�����ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //�����ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //(�J�[�\��)�����ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //���I�v�V�������l���z��Sum(�����ʔ��I�v�V�������l(n))
                l_dblBuyNetOption =
                    l_dblBuyNetOption + l_curIfoSummary.calcBuyOptionValue(l_intReservedDate);
            }
        }

        //�v�Z�������I�v�V�������l���z��ԋp����
        return l_dblBuyNetOption;
    }

    /**
     * (calc���I�v�V�������l���z)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���I�v�V�������l���z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionSellTotalAmount(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //���I�v�V�������l���z
        double l_dblSellNetOption = 0;

        //���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerProductList == null)
        {
            //���I�v�V�������l���z��0����
            l_dblSellNetOption = 0;
        }
        //���ʁ^���������݂��鎞
        else
        {
            //(�J�[�\��)�����ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //�����ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //(�J�[�\��)�����ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //���I�v�V�������l���z��Sum(�����ʔ��I�v�V�������l(n))
                l_dblSellNetOption =
                    l_dblSellNetOption + l_curIfoSummary.calcSellOptionValue(l_intReservedDate);
            }
        }

        //�v�Z�������I�v�V�������l���z��ԋp����
        return l_dblSellNetOption;
    }

    /**
     * (calc�Ȉ�SPAN�؋���)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�Ȉ�SPAN�؋����v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSimpleSPANIfoDeposit(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //�Ȉ�SPAN�؋���
        double l_dblSimpleSPAN = 0;

        //�I�v�V���������������A���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //�Ȉ�SPAN�؋�����0����
            l_dblSimpleSPAN = 0;
        }
        //�I�v�V���������������A���ʁ^���������݂��鎞
        else
        {
            //�Ȉ�SPAN�؋�����Max(���|�W�V�������z(n), ���|�W�V�������z(n))
            l_dblSimpleSPAN =
                Math.max(
                    this.calcBuyContractAmt(l_intReservedDate),
                    this.calcSellContractAmt(l_intReservedDate));

        }

        //�v�Z�����Ȉ�SPAN�؋�����ԋp����
        return l_dblSimpleSPAN;
    }

    /**
     * (calc�Ȉ�SPAN�؋������b�h)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�Ȉ�SPAN�؋������b�h�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSimpleSPANIfoDepositRed(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //�Ȉ�SPAN�؋������b�h
        double l_dblSimpleSPANRed = 0;

        //�I�v�V���������������A���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //�Ȉ�SPAN�؋������b�h��0����
            l_dblSimpleSPANRed = 0;
        }
        //�I�v�V���������������A���ʁ^���������݂��鎞
        else
        {
            //�Ȉ�SPAN�؋������b�h(n)�@@=�@@Max(���|�W�V�������z���b�h(n), ���|�W�V�������z���b�h(n))
            l_dblSimpleSPANRed =
                Math.max(
                    this.calcBuyContractAmtRed(l_intReservedDate),
                    this.calcSellContractAmtRed(l_intReservedDate));
        }

        //�v�Z�����Ȉ�SPAN�؋������b�h��ԋp����
        return l_dblSimpleSPANRed;
    }

    /**
     * (calc���|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyContractQty(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //���|�W�V��������
        double l_dblBuyQty = 0;
        BigDecimal l_bdBuyQty;
        BigDecimal l_bdPositionQty;
        
        //�I�v�V���������������A���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������ʂ�0����
            l_dblBuyQty = 0;
        }
        //�I�v�V���������������A���ʁ^���������݂��鎞
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������ʁ�Sum(�w���ʔ��|�W�V��������(n))
                l_bdBuyQty = new BigDecimal(String.valueOf(l_dblBuyQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcBuyContractQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdBuyQty = l_bdBuyQty.add(l_bdPositionQty);
                l_dblBuyQty = l_bdBuyQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
                
            }
        }

        //�v�Z�������|�W�V�������ʂ�ԋp����
        return l_dblBuyQty;
    }

    /**
     * (calc���|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellContractQty(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //���|�W�V��������
        double l_dblSellQty = 0;
        BigDecimal l_bdSellQty;
        BigDecimal l_bdPositionQty;

        //�I�v�V���������������A���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������ʂ�0����
            l_dblSellQty = 0;
        }
        //�I�v�V���������������A���ʁ^���������݂��鎞
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������ʁ�Sum(�w���ʔ��|�W�V��������(n))
                l_bdSellQty = new BigDecimal(String.valueOf(l_dblSellQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcSellContractQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdSellQty = l_bdSellQty.add(l_bdPositionQty);
                l_dblSellQty = l_bdSellQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
            }
        }

        //�v�Z�������|�W�V�������ʂ�ԋp����
        return l_dblSellQty;
    }

    /**
     * (calc���������|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���������|�W�V�������ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyOrderQty(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //���������|�W�V��������
        double l_dblBuyQty = 0;
        BigDecimal l_bdBuyQty;
        BigDecimal l_bdPositionQty;

        //�I�v�V���������������A���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������ʂ�0����
            l_dblBuyQty = 0;
        }
        //�I�v�V���������������A���ʁ^���������݂��鎞
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���������|�W�V�������ʁ�Sum(�w���ʒ��������|�W�V��������(n))
                l_bdBuyQty = new BigDecimal(String.valueOf(l_dblBuyQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcBuyOrderQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdBuyQty = l_bdBuyQty.add(l_bdPositionQty);
                l_dblBuyQty = l_bdBuyQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
            }
        }

        //�v�Z�������������|�W�V�������ʂ�ԋp����
        return l_dblBuyQty;
    }

    /**
     * (calc���������|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���������|�W�V�������ʁv��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellOrderQty(int l_intReservedDate)
    {
        //�w�����0�A1�A2�ȊO�̎�
        if (l_intReservedDate != WEB3IfoReservedDateDef.T_0
            && l_intReservedDate != WEB3IfoReservedDateDef.T_1
            && l_intReservedDate != WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����
            return 0;
        }

        //���������|�W�V��������
        double l_dblSellQty = 0;
        BigDecimal l_bdSellQty;
        BigDecimal l_bdPositionQty;

        //�I�v�V���������������������A���ʁ^���������݂��Ȃ���
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //���������|�W�V�������ʂ�0����
            l_dblSellQty = 0;
        }
        //�I�v�V���������������������A���ʁ^���������݂��鎞
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���������|�W�V�������ʁ�Sum(�w���ʒ��������|�W�V��������(n))
                l_bdSellQty = new BigDecimal(String.valueOf(l_dblSellQty));
                l_bdPositionQty =
                    new BigDecimal(
                        String.valueOf(
                            this.calcPositionQuantity(
                                l_curIfoSummary.calcSellOrderQty(
                                    l_intReservedDate),
                                l_curIfoSummary.getTargetProductCode())));
                l_bdSellQty = l_bdSellQty.add(l_bdPositionQty);
                l_dblSellQty = l_bdSellQty.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
            }
        }

        //�v�Z�������������|�W�V�������ʂ�ԋp����
        return l_dblSellQty;
    }

    /**
     * (calc���|�W�V�������z)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������z�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyContractAmt(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * ���|�W�V�������z(n)���v�Z����B
         */
        double l_dblContractAmt = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        //(����/����(*)�����݂��Ȃ��ꍇ)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������z(n)�@@=�@@0
            l_dblContractAmt = 0;
        }
        //�ȊO�̏ꍇ
        //(����/����(*)�����݂���ꍇ�A�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ�v�f���Ƃ�Loop�������s���A���a�����߂�)
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������z(n)�@@=�@@Sum(�w���ʔ��|�W�V�������z(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleBuyAmt(l_intReservedDate);
            }
        }

        /*
         * �v�Z�������|�W�V�������z(n)��ԋp����B
         */
        return l_dblContractAmt;
    }

    /**
     * (calc���|�W�V�������z)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������z�v��ԋp����B
     *  <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellContractAmt(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * ���|�W�V�������z(n)���v�Z����B
         */
        double l_dblContractAmt = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        //(����/����(*)�����݂��Ȃ��ꍇ)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������z(n)�@@=�@@0
            l_dblContractAmt = 0;
        }
        //�ȊO�̏ꍇ
        //(����/����(*)�����݂���ꍇ�A�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ�v�f���Ƃ�Loop�������s���A���a�����߂�)
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������z(n)�@@=�@@Sum(�w���ʔ��|�W�V�������z(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleSellAmt(l_intReservedDate);
            }
        }

        /*
         * �v�Z�������|�W�V�������z(n)��ԋp����B
         */
        return l_dblContractAmt;
    }

    /**
     * (calc���|�W�V�������z���b�h)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������z���b�h�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyContractAmtRed(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * ���|�W�V�������z���b�h(n)���v�Z����B
         */
        double l_dblContractAmt = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        //(����/����(*)�����݂��Ȃ��ꍇ)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������z���b�h(n)�@@=�@@0
            l_dblContractAmt = 0;
        }
        //�ȊO�̏ꍇ
        //(����/����(*)�����݂���ꍇ�A�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ�v�f���Ƃ�Loop�������s���A���a�����߂�)
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������z���b�h(n)�@@=�@@Sum(�w���ʔ��|�W�V�������z���b�h(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleBuyAmtRed(l_intReservedDate);
            }
        }

        /*
         * �v�Z�������|�W�V�������z���b�h(n)��ԋp����B
         */
        return l_dblContractAmt;
    }

    /**
     * (calc���|�W�V�������z���b�h)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������z���b�h�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellContractAmtRed(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * ���|�W�V�������z���b�h(n)���v�Z����B
         */
        double l_dblContractAmt = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        //(����/����(*)�����݂��Ȃ��ꍇ)
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������z���b�h(n)�@@=�@@0
            l_dblContractAmt = 0;
        }
        //�ȊO�̏ꍇ
        //(����/����(*)�����݂���ꍇ�A�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ�v�f���Ƃ�Loop�������s���A���a�����߂�)
        else
        {
            //(�J�[�\��)�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //(�J�[�\��)�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������z���b�h(n)�@@=�@@Sum(�w���ʔ��|�W�V�������z���b�h(n))
                l_dblContractAmt = l_dblContractAmt + l_curIfoSummary.calcPossibleSellAmtRed(l_intReservedDate);
            }
        }

        /*
         * �v�Z�������|�W�V�������z���b�h(n)��ԋp����B
         */
        return l_dblContractAmt;
    }
    
    /**
     * (print���O)<BR>
     * 
     * ���O�o�͂��s���B
     * �V�[�P���X�}�u�i�؋����v�Z�jprint���O�v�v�Q�ƁB
     */
    private void printLog()
    {
        if(!log.ison()) return;
        //info���O�o�̓��b�Z�[�W
        StringBuffer l_infoMessage = new StringBuffer();
        //debug���O�o�̓��b�Z�[�W
        StringBuffer l_debugMessage = new StringBuffer();

        /*
         * �擾����
         */
        //01.�c�Ɠ�(n)
        String l_strBizDate;
        //02.�؋����c��(n)
        String l_strDepBalance;
        //03.�U�֊z(n)
        String l_strTransferAmt;
        //04.�敨���ϑ��v(n)        
        String l_strFuturesCloseProLos;
        //05.�I�v�V������n���(n)  
        String l_strOpNetAmt;
        //06.�I�v�V���������T�Z��n���(n)
        String l_strOpBuyEstNetAmt;
        //07.�敨�]�����v
        String l_strFuturesAppProLos;
        //08.�敨�����]�����v    
        String l_strFuturesBuyAppProLos;
        //09.�敨�����]�����v     
        String l_strFuturesSellAppProLos;
        //10.����؋����c��(n)      
        String l_strRecDepBalance;
        //11.���|�W�V��������(n)
        String l_strBuyContQty;
        //12.���������|�W�V��������(n)
        String l_strBuyOrderQty;
        //13.���|�W�V��������(n)
        String l_strSellContQty;
        //14.���������|�W�V��������(n)
        String l_strSellOrderQty;
        //15.�|�W�V�����o�����X(n)
        String l_strPositionBalance;
        //16.�|�W�V�����o�����X�敪(n)
        String l_strPositionBalanceType;
        //17.SPAN�؋���(n)          
        String l_strSpanIfoDep;
        double l_dblSpanIfoDep;
        //18.�Ȉ�SPAN�؋���(n)
        String l_strSimpleSPANIfoDeposit;
        //19.�l�b�g�I�v�V�������l���z
        String l_strNetOptionAmt;
        //20.���I�v�V�������l���z(n)
        String l_strOpBuyTotalAmt;
        //21.���I�v�V�������l���z(n)
        String l_strOpSellTotalAmt;
        //22.�؋������v�z(n)        
        String l_strIfoDepReqAmt;
        //23.�؋����]�͊z(n)        
        String l_strIfoDepTPAmt;
        //24.�؋����U�֗]�͊z(n)
        String l_strIfoDepTransferableAmtInt;
        //25.�؋����U�֗]�͊z
        String l_strIfoDepTransferableAmt;
        //26.�I�v�V�����]�����v
        String l_strOpAppProLos;
        //27.�I�v�V���������]�����v
        String l_strOpBuyAppProLos;
        //28.�I�v�V���������]�����v
        String l_strOpSellAppProLos;
        //29.�{�������z
        String l_strCurDemandAmt;
        //30.���������z
        String l_strNextDemandAmt;
        //31.�������z
        String l_strNonPayAmt;
        //32.���|�W�V�������z(n)
        String l_strBuyContractAmt;
        //33.���|�W�V�������z(n)
        String l_strSellContractAmt;
        //34.�敨�]�����v���؋����s�����m�聄
        String l_strFuturesProfitLossTemp;
        //35.�敨�����]�����v���؋����s�����m�聄
        String l_strFuturesBuyProfitLossTemp;
        //36.�敨�����]�����v���؋����s�����m�聄
        String l_strFuturesSellProfitLossTemp;
        //37.����؋����c�����؋����s�����m�聄(n)
        String l_strReceiptIfoDepositBalanceTemp;      
        //38.�Ȉ�SPAN�؋������؋����s�����m�聄(n)
        String l_strSimpleSPANIfoDepositTemp;
        //39.�l�b�g�I�v�V�������l���z���؋����s�����m�聄�in�j
        String l_strNetOptionTotalAmountTemp;
        //40.���I�v�V�������l���z���؋����s�����m�聄�in�j
        String l_strOptionBuyTotalAmountTemp;
        //41.���I�v�V�������l���z���؋����s�����m�聄�in�j
        String l_strOptionSellTotalAmountTemp;
        //42.�؋������v�z���؋����s�����m�聄(n)
        String l_strIfoDepositRequiredAmountTemp;
        //43.���|�W�V�������z���؋����s�����m�聄�in�j
        String l_strBuyContractAmtTemp;
        //44.���|�W�V�������z���؋����s�����m�聄�in�j
        String l_strSellContractAmtTemp;
        //45.���������z���[�ꁄ
        String l_strNextDemandAmountEvening;
        //46.���X�������z
        String l_strNext2BizDateDemandAmount;
        //47.�؋����s���z�����v�Z���in�j
        String l_strIfoDepositLackChargeTemp;

        /*
         * info���O�o�̓��b�Z�[�W���쐬����B 
         * �i���j�J���}��؂�ŕ�����A��
         */
        //�w���(=0�`2)��LOOP����
        for (int index = 0; index <= 2; index++)
        {
            //01.�c�Ɠ�(n)
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            l_strBizDate = formatter.format(this.ifoDepositCalcCondition.getBizDate(index));
            //02.�؋����c��(n)
            l_strDepBalance = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositBalance(index));
            //03.�U�֊z(n)
            l_strTransferAmt = WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getTransferAmount(index));
            //04.�敨���ϑ��v(n)
            l_strFuturesCloseProLos =
                WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getFuturesCloseProfitLoss(index));
            //05.�I�v�V������n���(n)
            l_strOpNetAmt = WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getOptionNetAmount(index));
            //06.�I�v�V���������T�Z��n���(n)
            l_strOpBuyEstNetAmt =
                WEB3StringTypeUtility.formatNumber(this.ifoCustomerTransfer.getOptionBuyEstimatedNetAmount(index));
            //07.�敨�]�����v
            l_strFuturesAppProLos = WEB3StringTypeUtility.formatNumber(this.calcFuturesAppraisalProfitLoss());
            //08.�敨�����]�����v
            l_strFuturesBuyAppProLos = WEB3StringTypeUtility.formatNumber(this.calcFuturesBuyAppraisalProfitLoss());
            //09.�敨�����]�����v
            l_strFuturesSellAppProLos = WEB3StringTypeUtility.formatNumber(this.calcFuturesSellAppraisalProfitLoss());
            //10.����؋����c��(n)
            l_strRecDepBalance = WEB3StringTypeUtility.formatNumber(this.calcReceiptIfoDepositBalance(index));
            //11.���|�W�V��������(n)
            l_strBuyContQty = WEB3StringTypeUtility.formatNumber(this.calcBuyContractQty(index));
            //12.���������|�W�V��������(n)
            l_strBuyOrderQty = WEB3StringTypeUtility.formatNumber(this.calcBuyOrderQty(index));
            //13.���|�W�V��������(n)
            l_strSellContQty = WEB3StringTypeUtility.formatNumber(this.calcSellContractQty(index));
            //14.���������|�W�V��������(n)
            l_strSellOrderQty = WEB3StringTypeUtility.formatNumber(this.calcSellOrderQty(index));
            //15.�|�W�V�����o�����X(n)�A16.�|�W�V�����o�����X�敪(n)
            WEB3IfoPositionBalance l_positionBalance = this.calcPositionBalance(index);
            if (l_positionBalance == null)
            {
                l_strPositionBalance = "-";
                l_strPositionBalanceType = "-";
            }
            else
            {
                l_strPositionBalance = WEB3StringTypeUtility.formatNumber(l_positionBalance.positionBalance);
                l_strPositionBalanceType = l_positionBalance.positionBalanceType;
            }
            //17.SPAN�؋���(n)
            l_dblSpanIfoDep = this.getSPANIfoDeposit(index);
            if (l_dblSpanIfoDep == -1)
            {
                l_strSpanIfoDep = "-";
            }
            else
            {
                l_strSpanIfoDep = WEB3StringTypeUtility.formatNumber(l_dblSpanIfoDep);
            }
            //18.�Ȉ�SPAN�؋���(n)
            l_strSimpleSPANIfoDeposit = WEB3StringTypeUtility.formatNumber(this.calcSimpleSPANIfoDeposit(index));
            //19.�l�b�g�I�v�V�������l���z(n)
            l_strNetOptionAmt = WEB3StringTypeUtility.formatNumber(this.calcNetOptionTotalAmount(index));
            //20.���I�v�V�������l���z(n)
            l_strOpBuyTotalAmt = WEB3StringTypeUtility.formatNumber(this.calcOptionBuyTotalAmount(index));
            //21.���I�v�V�������l���z(n)
            l_strOpSellTotalAmt = WEB3StringTypeUtility.formatNumber(this.calcOptionSellTotalAmount(index));
            //22.�؋������v�z(n)
            l_strIfoDepReqAmt = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositRequiredAmount(index));
            //23.�؋����]�͊z(n)
            l_strIfoDepTPAmt = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositTradingPowerAmount(index));
            //24.�؋����U�֗]�͊z(n)
            l_strIfoDepTransferableAmtInt =
                WEB3StringTypeUtility.formatNumber(this.calcIfoDepositTransferableAmount(index));
            //25.�؋����U�֗]�͊z
            l_strIfoDepTransferableAmt = WEB3StringTypeUtility.formatNumber(this.calcIfoDepositTransferableAmount());
            //26.�I�v�V�����]�����v
            l_strOpAppProLos = WEB3StringTypeUtility.formatNumber(this.calcOptionAppraisalProfitLoss());
            //27.�I�v�V���������]�����v
            l_strOpBuyAppProLos = WEB3StringTypeUtility.formatNumber(this.calcOptionBuyAppraisalProfitLoss());
            //28.�I�v�V���������]�����v
            l_strOpSellAppProLos = WEB3StringTypeUtility.formatNumber(this.calcOptionSellAppraisalProfitLoss());
            //29.�{�������z
            l_strCurDemandAmt = WEB3StringTypeUtility.formatNumber(this.getCurrentBizDateDemandAmount());
            //30.���������z
            l_strNextDemandAmt = WEB3StringTypeUtility.formatNumber(this.calcNextBizDateDemandAmount());
            //31.�������z
            l_strNonPayAmt = WEB3StringTypeUtility.formatNumber(this.calcNonPayAmount());
            //32.���|�W�V�������z(n)
            l_strBuyContractAmt = WEB3StringTypeUtility.formatNumber(this.calcBuyContractAmt(index));
            //33.���|�W�V�������z
            l_strSellContractAmt = WEB3StringTypeUtility.formatNumber(this.calcSellContractAmt(index));
            //34.�敨�]�����v���؋����s�����m�聄
            l_strFuturesProfitLossTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcFuturesAppraisalProfitLossTemp());    
            //35.�敨�����]�����v���؋����s�����m�聄
            l_strFuturesBuyProfitLossTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcFuturesBuyAppraisalProfitLossTemp());    
            //36.�敨�����]�����v���؋����s�����m�聄
            l_strFuturesSellProfitLossTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcFuturesSellAppraisalProfitLossTemp());    
            //37.����؋����c�����؋����s�����m�聄(n)
            l_strReceiptIfoDepositBalanceTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcReceiptIfoDepositBalanceTemp(index));      
            //38.�Ȉ�SPAN�؋������؋����s�����m�聄(n)
            l_strSimpleSPANIfoDepositTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcSimpleSPANIfoDepositTemp(index));    
            //39.�l�b�g�I�v�V�������l���z���؋����s�����m�聄�in�j
            l_strNetOptionTotalAmountTemp =
                WEB3StringTypeUtility.formatNumber(this.calcNetOptionTotalAmountTemp(index));    
            //40.���I�v�V�������l���z���؋����s�����m�聄�in�j
            l_strOptionBuyTotalAmountTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcOptionBuyTotalAmountTemp(index));    
            //41.���I�v�V�������l���z���؋����s�����m�聄�in�j
            l_strOptionSellTotalAmountTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcOptionSellTotalAmountTemp(index));    
            //42.�؋������v�z���؋����s�����m�聄(n)
            l_strIfoDepositRequiredAmountTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcIfoDepositRequiredAmountTemp(index));
            //43.���|�W�V�������z���؋����s�����m�聄�in�j
            l_strBuyContractAmtTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcBuyContractAmtTemp(index));    
            //44.���|�W�V�������z���؋����s�����m�聄�in�j
            l_strSellContractAmtTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcSellContractAmtTemp(index));    
            //45.���������z���[�ꁄ
            l_strNextDemandAmountEvening = 
                WEB3StringTypeUtility.formatNumber(this.calcNextBizDateDemandAmountEvening());    
            //46.���X�������z
            l_strNext2BizDateDemandAmount = 
                WEB3StringTypeUtility.formatNumber(this.calcNext2BizDateDemandAmount());    
            //47.�؋����s���z�����v�Z���in�j
            l_strIfoDepositLackChargeTemp = 
                WEB3StringTypeUtility.formatNumber(this.calcIfoDepositLackChargeTemp(index));    

            /*
             * info���O�o�̓��b�Z�[�W�̘A��
             * 
             * �؋����c��(n), �U�֊z(n), �敨���ϑ��v(n), �I�v�V������n���(n), �敨�]�����v,
             * �敨�����]�����v, �敨�����]�����v, ����؋����c��(n), SPAN�؋���(n), �l�b�g�I�v�V�������l���z
             * �؋������v�z(n), �؋����]�͊z(n),���������z
             */
            l_infoMessage.append(l_strDepBalance).append(",");
            l_infoMessage.append(l_strTransferAmt).append(",");
            l_infoMessage.append(l_strFuturesCloseProLos).append(",");
            l_infoMessage.append(l_strOpNetAmt).append(",");
            l_infoMessage.append(l_strFuturesAppProLos).append(",");
            l_infoMessage.append(l_strFuturesBuyAppProLos).append(",");
            l_infoMessage.append(l_strFuturesSellAppProLos).append(",");
            l_infoMessage.append(l_strRecDepBalance).append(",");
            l_infoMessage.append(l_strSpanIfoDep).append(",");
            l_infoMessage.append(l_strNetOptionAmt).append(",");
            l_infoMessage.append(l_strIfoDepReqAmt).append(",");
            l_infoMessage.append(l_strIfoDepTPAmt).append(",");
            l_infoMessage.append(l_strNextDemandAmt);
            if (index != 2)
            {
                l_infoMessage.append(",");
            }

            /*
             * debug���O�o�̓��b�Z�[�W�̘A��
             * �c�Ɠ�(n), �؋����c��(n), �U�֊z(n), �敨���ϑ��v(n), �I�v�V������n���(n), �I�v�V���������T�Z��n���(n),
             * �敨�]�����v, �敨�����]�����v, �敨�����]�����v, ����؋����c��(n), ���|�W�V��������(n),
             * ���������|�W�V��������(n), ���|�W�V��������(n), ���������|�W�V��������(n), �|�W�V�����o�����X(n),
             * �|�W�V�����o�����X(n), SPAN�؋���(n), �Ȉ�SPAN�؋���(n), �l�b�g�I�v�V�������l���z,
             * ���I�v�V�������l���z(n), ���I�v�V�������l���z(n), �؋������v�z(n), �؋����]�͊z(n), �؋����U�֗]�͊z(n),
             * �؋����U�֗]�͊z,�I�v�V�����]�����v,�I�v�V���������]�����v, �I�v�V���������]�����v, �{�������z, ���������z,�������z,
             * ���|�W�V�������z�in�j,���|�W�V�������z�in�j
             * �敨�]�����v���؋����s�����m�聄,
             * �敨�����]�����v���؋����s�����m�聄, �敨�����]�����v���؋����s�����m�聄,
             * ����؋����c�����؋����s�����m�聄(n),
             * �Ȉ�SPAN�؋������؋����s�����m�聄(n),
             * �l�b�g�I�v�V�������l���z���؋����s�����m�聄�in�j,
             * ���I�v�V�������l���z���؋����s�����m�聄�in�j,
             * ���I�v�V�������l���z���؋����s�����m�聄�in�j,
�@@�@@�@@�@@�@@�@@ * �؋������v�z���؋����s�����m�聄(n),
             * ���|�W�V�������z���؋����s�����m�聄�in�j,
             * ���|�W�V�������z���؋����s�����m�聄�in�j,
             * ���������z���[�ꁄ, ���X�������z,
             * �؋����s���z�����v�Z���in)
             */
            l_debugMessage.append(l_strBizDate).append(",");
            l_debugMessage.append(l_strDepBalance).append(",");
            l_debugMessage.append(l_strTransferAmt).append(",");
            l_debugMessage.append(l_strFuturesCloseProLos).append(",");
            l_debugMessage.append(l_strOpNetAmt).append(",");
            l_debugMessage.append(l_strOpBuyEstNetAmt).append(",");
            l_debugMessage.append(l_strFuturesAppProLos).append(",");
            l_debugMessage.append(l_strFuturesBuyAppProLos).append(",");
            l_debugMessage.append(l_strFuturesSellAppProLos).append(",");
            l_debugMessage.append(l_strRecDepBalance).append(",");
            l_debugMessage.append(l_strBuyContQty).append(",");
            l_debugMessage.append(l_strBuyOrderQty).append(",");
            l_debugMessage.append(l_strSellContQty).append(",");
            l_debugMessage.append(l_strSellOrderQty).append(",");
            l_debugMessage.append(l_strPositionBalance).append(",");
            l_debugMessage.append(l_strPositionBalanceType).append(",");
            l_debugMessage.append(l_strSpanIfoDep).append(",");
            l_debugMessage.append(l_strSimpleSPANIfoDeposit).append(",");
            l_debugMessage.append(l_strNetOptionAmt).append(",");
            l_debugMessage.append(l_strOpBuyTotalAmt).append(",");
            l_debugMessage.append(l_strOpSellTotalAmt).append(",");
            l_debugMessage.append(l_strIfoDepReqAmt).append(",");
            l_debugMessage.append(l_strIfoDepTPAmt).append(",");
            l_debugMessage.append(l_strIfoDepTransferableAmtInt).append(",");
            l_debugMessage.append(l_strIfoDepTransferableAmt).append(",");
            l_debugMessage.append(l_strOpAppProLos).append(",");
            l_debugMessage.append(l_strOpBuyAppProLos).append(",");
            l_debugMessage.append(l_strOpSellAppProLos).append(",");
            l_debugMessage.append(l_strCurDemandAmt).append(",");
            l_debugMessage.append(l_strNextDemandAmt).append(",");
            l_debugMessage.append(l_strNonPayAmt).append(",");
            l_debugMessage.append(l_strBuyContractAmt).append(",");
            l_debugMessage.append(l_strSellContractAmt).append(",");
            l_debugMessage.append(l_strFuturesProfitLossTemp).append(",");
            l_debugMessage.append(l_strFuturesBuyProfitLossTemp).append(",");
            l_debugMessage.append(l_strFuturesSellProfitLossTemp).append(",");
            l_debugMessage.append(l_strReceiptIfoDepositBalanceTemp).append(",");      
            l_debugMessage.append(l_strSimpleSPANIfoDepositTemp).append(",");
            l_debugMessage.append(l_strNetOptionTotalAmountTemp).append(",");
            l_debugMessage.append(l_strOptionBuyTotalAmountTemp).append(",");
            l_debugMessage.append(l_strOptionSellTotalAmountTemp).append(",");
            l_debugMessage.append(l_strIfoDepositRequiredAmountTemp).append(",");
            l_debugMessage.append(l_strBuyContractAmtTemp).append(",");
            l_debugMessage.append(l_strSellContractAmtTemp).append(",");
            l_debugMessage.append(l_strNextDemandAmountEvening).append(",");
            l_debugMessage.append(l_strNext2BizDateDemandAmount).append(",");
            l_debugMessage.append(l_strIfoDepositLackChargeTemp);
 
            if (index != 2)
            {
                l_debugMessage.append(",");
            }
        }

        // info���O�o��
        log.info(l_infoMessage.toString());
        // debug���O�o��
        log.debug(l_debugMessage.toString());

    }

    /**
     * (calc�|�W�V��������)<BR>
     * <BR>
     * �����̌����Y�����R�[�h�̃v���C�X�X�L�����ɑ�������|�W�V������ԋp����B <BR>
     * <BR>
     * 1�j����.�����Y�����R�[�h == �~�j���o225�̏ꍇ <BR>
     * �@@[����.���ʁ~�@@0.1]��ԋp����B <BR>
     *  <BR>
     * 2)�@@1)�ȊO�̏ꍇ <BR>
     * �@@[����.����]�����̂܂ܕԋp����B <BR>
     * <BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_strUnderlyingProductCode - (�����Y�����R�[�h)<BR>
     * �����Y�����R�[�h<BR>
     * @@return double
     */
    private double calcPositionQuantity(double l_dblQuantity , String l_strUnderlyingProductCode)
    {
          if(l_strUnderlyingProductCode.equals(WEB3IfoDepositUnderlyingProductCodeDef.MINI_NIKKEI_225)) 
          { 
                BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
                BigDecimal l_bdMultiply = new BigDecimal(String.valueOf(WEB3IfoDepositPriceScanMultiplyDef.MINI));
                l_bdQuantity = l_bdQuantity.multiply(l_bdMultiply);
                l_dblQuantity = l_bdQuantity.setScale(1,BigDecimal.ROUND_UNNECESSARY).doubleValue();
          }
          return   l_dblQuantity;    
    }

    /**
     * (calc����؋����c�����؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u����؋����c�����؋����s�����m�聄�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcReceiptIfoDepositBalanceTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //����؋����c�����؋����s�����m�聄(n)�@@=�@@
        //    �؋����v�Z.calc�؋����c�����؋����s�����m�聄(n) + �؋����v�Z.calc�敨�]�����v���؋����s�����m�聄
        double l_dblReceiptIfoDepositBalanceTemp =
            this.calcIfoDepositBalanceTemp(l_intReservedDate) + 
            this.calcFuturesAppraisalProfitLossTemp();

        return l_dblReceiptIfoDepositBalanceTemp;
    }

    /**
     * (calc�؋������v�z���؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�؋������v�z�����؋����s�����m�聄�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositRequiredAmountTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //�؋������v�z���؋����s�����m�聄(n)
        double l_dblIfoDepositRequiredAmountTemp = 0;

        //[a.�؋����v�Z.�؋����v�Z����.isSPAN�g�p�\() == true �̏ꍇ]
        //�iSPAN�؋����̗p��Ђ̏ꍇ�j
        if (this.ifoDepositCalcCondition.isSPANUsable())
        {
            //�؋������v�z���؋����s�����m�聄(n) = (SPAN�؋���(n) �| �l�b�g�I�v�V�������l���z���؋����s�����m�聄(n)) �~ SPAN�W��
            BigDecimal l_bdSPANIfoDeposit =
                new BigDecimal(this.getSPANIfoDeposit(l_intReservedDate));
            BigDecimal l_bdNetOptionTemp =
                new BigDecimal(this.calcNetOptionTotalAmountTemp(l_intReservedDate));
            BigDecimal l_bdSPANFactor =
                new BigDecimal(this.ifoDepositCalcCondition.getSPANFactor());

            BigDecimal l_bdIfoDepositRequiredAmount =
                l_bdSPANIfoDeposit.subtract(l_bdNetOptionTemp).multiply(l_bdSPANFactor);

            //�����_�ȉ��؂�グ�Ƃ���
            l_bdIfoDepositRequiredAmount =
                l_bdIfoDepositRequiredAmount.setScale(0, BigDecimal.ROUND_CEILING);

            l_dblIfoDepositRequiredAmountTemp = l_bdIfoDepositRequiredAmount.doubleValue();
        }
        // [b.�؋����v�Z.�؋����v�Z����.isSPAN�g�p�\() == false �̏ꍇ]
        //�iSPAN�؋�����̗p��ЁA�܂��́ASPAN�̗p��Ђ�SPAN���g���u���̏ꍇ�j
        else
        {
            //�؋������v�z���؋����s�����m�聄(n)�@@=�@@
            //   �Ȉ�SPAN�؋������؋����s�����m�聄(n) �| �l�b�g�I�v�V�������l���z���؋����s�����m�聄(n)
            BigDecimal l_bdSimpleSPANIfoDeposit =
                new BigDecimal(this.calcSimpleSPANIfoDepositTemp(l_intReservedDate));
            BigDecimal l_bdNetOptionTemp =
                new BigDecimal(this.calcNetOptionTotalAmountTemp(l_intReservedDate));

            l_dblIfoDepositRequiredAmountTemp =
                l_bdSimpleSPANIfoDeposit.subtract(l_bdNetOptionTemp).doubleValue();
        }

        return l_dblIfoDepositRequiredAmountTemp;
    }

    /**
     * (calc�Ȉ�SPAN�؋������؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�Ȉ�SPAN�؋������؋����s�����m�聄�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSimpleSPANIfoDepositTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //�Ȉ�SPAN�؋������؋����s�����m�聄
        double l_dblSimpleSPANTemp = 0;
        //[a.�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ]
        //(����/����(*)�����݂��Ȃ��ꍇ)
        if (this.ifoSummaryContractPerIndexList == null)
        {
            //�Ȉ�SPAN�؋������؋����s�����m�聄��0����
            l_dblSimpleSPANTemp = 0;
        }
        //[b. �ȊO�̏ꍇ]
        else
        {
            //�Ȉ�SPAN�؋������؋����s�����m�聄 (n)�@@=�@@
            //     Max(���|�W�V�������z���؋����s�����m�聄 (n), ���|�W�V�������z���؋����s�����m�聄 (n))          
            l_dblSimpleSPANTemp =
                Math.max(
                    this.calcBuyContractAmtTemp(l_intReservedDate),
                    this.calcSellContractAmtTemp(l_intReservedDate));
        }
        return l_dblSimpleSPANTemp;
    }

    /**
     * (calc�敨�]�����v���؋����s�����m�聄)<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcFuturesAppraisalProfitLossTemp()
    {
        //�敨�]�����v���؋����s�����m�聄�@@=�@@�敨�����]�����v���؋����s�����m�聄 + �敨�����]�����v���؋����s�����m�聄
        double l_dblProfitLossTemp =
            this.calcFuturesBuyAppraisalProfitLossTemp() + this.calcFuturesSellAppraisalProfitLossTemp();

        //�v�Z�����敨�]�����v��ԋp����        
        return l_dblProfitLossTemp;
    }

    /**
     * (calc�敨�����]�����v���؋����s�����m�聄)<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcFuturesBuyAppraisalProfitLossTemp()
    {
        //�敨�����]�����v���؋����s�����m�聄
        double l_dblBuyProfitLossTemp = 0;

        //[a.�؋����v�Z.�������P���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ]
        //(���ʂ����݂��Ȃ��ꍇ)
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //�敨�����]�����v���؋����s�����m�聄��0��������
            l_dblBuyProfitLossTemp = 0;
        }
        //[b. �ȊO�̏ꍇ]
        //(���ʂ����݂���ꍇ�A�؋����v�Z.�������P���ʐ敨OP���ʏW�v�ꗗ�v�f���Ƃ�Loop�������s���A���a�����߂�)
        else
        {
            //�������P���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //�������P���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //�������P���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //�敨�����]�����v���؋����s�����m�聄��Sum(�������P���ʐ敨�����]�����v���؋����s�����m�聄)
                l_dblBuyProfitLossTemp =
                    l_dblBuyProfitLossTemp + l_curIfoSummary.calcFuturesBuyContractProfitLossTemp();
            }
        }

        return l_dblBuyProfitLossTemp;
    }

    /**
     * (calc�敨�����]�����v���؋����s�����m�聄)<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcFuturesSellAppraisalProfitLossTemp()
    {
        //�敨�����]�����v���؋����s�����m�聄
        double l_dblSellProfitLossTemp = 0;

        //[a.�؋����v�Z.�������P���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ]
        //(���ʂ����݂��Ȃ��ꍇ)
        if (this.ifoSummaryContractPerProductContractPriceList == null)
        {
            //�敨�����]�����v���؋����s�����m�聄��0��������
            l_dblSellProfitLossTemp = 0;
        }
        //[b. �ȊO�̏ꍇ]
        //(���ʂ����݂���ꍇ�A�؋����v�Z.�������P���ʐ敨OP���ʏW�v�ꗗ�v�f���Ƃ�Loop�������s���A���a�����߂�)
        else
        {
            //�������P���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProductContractPrice l_curIfoSummary = null;

            //�������P���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0;
                index < this.ifoSummaryContractPerProductContractPriceList.length;
                index++)
            {
                //�������P���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductContractPriceList[index];

                //�敨�����]�����v���؋����s�����m�聄��Sum(�������P���ʐ敨�����]�����v���؋����s�����m�聄)
                l_dblSellProfitLossTemp =
                    l_dblSellProfitLossTemp + l_curIfoSummary.calcFuturesSellContractProfitLossTemp();
            }
        }

        return l_dblSellProfitLossTemp;
    }

   /**
     * (calc�l�b�g�I�v�V�������l���z���؋����s�����m�聄 )<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�l�b�g�I�v�V�������l���z���؋����s�����m�聄 �v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcNetOptionTotalAmountTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
             return 0;
        }

        //�l�b�g�I�v�V�������l���z���؋����s�����m�聄(n)��
        //    Min(0, ���I�v�V�������l���z���؋����s�����m�聄(n)�|���I�v�V�������l���z���؋����s�����m�聄(n))
        double l_dblNetOptionTemp =
            Math.min(
                0,
                this.calcOptionBuyTotalAmountTemp(l_intReservedDate)
                    - this.calcOptionSellTotalAmountTemp(l_intReservedDate));

        return l_dblNetOptionTemp;
    }

    /**
     * (calc���I�v�V�������l���z���؋����s�����m�聄 )<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���I�v�V�������l���z���؋����s�����m�聄 �v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionBuyTotalAmountTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }
        
        //���I�v�V�������l���z���؋����s�����m�聄 
        double l_dblBuyNetOptionTemp = 0;

        //�؋����v�Z.�����ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        if (this.ifoSummaryContractPerProductList == null)
        {
            //���I�v�V�������l���z���؋����s�����m�聄 ��0����
            l_dblBuyNetOptionTemp = 0;
        }
        else
        {
            //�����ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //�����ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //�����ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //���I�v�V�������l���z���؋����s�����m�聄 ��Sum(�����ʔ��I�v�V�������l���؋����s�����m�聄 (n))
                l_dblBuyNetOptionTemp =
                    l_dblBuyNetOptionTemp + l_curIfoSummary.calcBuyOptionValueTemp(l_intReservedDate);
            }
        }
        return l_dblBuyNetOptionTemp;
    }

    /**
     * (calc���I�v�V�������l���z���؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���I�v�V�������l���z���؋����s�����m�聄�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionSellTotalAmountTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        //���I�v�V�������l���z���؋����s�����m�聄
        double l_dblSellNetOptionTemp = 0;

        //�؋����v�Z.�����ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        if (this.ifoSummaryContractPerProductList == null)
        {
            //���I�v�V�������l���z���؋����s�����m�聄��0����
            l_dblSellNetOptionTemp = 0;
        }
        else
        {
            //�����ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerProduct l_curIfoSummary = null;

            //�����ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerProductList.length; index++)
            {
                //�����ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerProductList[index];

                //���I�v�V�������l���z���؋����s�����m�聄��Sum(�����ʔ��I�v�V�������l���؋����s�����m�聄(n))
                l_dblSellNetOptionTemp =
                    l_dblSellNetOptionTemp + l_curIfoSummary.calcSellOptionValueTemp(l_intReservedDate);
            }
        }
        return l_dblSellNetOptionTemp;
    }

    /**
     * (calc���|�W�V�������z���؋����s�����m�聄)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������z���؋����s�����m�聄�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyContractAmtTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
             return 0;
        }

        double l_dblContractAmtTemp = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������z���؋����s�����m�聄(n)�@@=�@@0
            l_dblContractAmtTemp = 0;
        }
        else
        {
           //�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������z���؋����s�����m�聄(n)�@@=�@@Sum(�w���ʔ��|�W�V�������z���؋����s�����m�聄(n))
                l_dblContractAmtTemp = l_dblContractAmtTemp + l_curIfoSummary.calcPossibleBuyAmtTemp(l_intReservedDate);
            }
        }
        return l_dblContractAmtTemp;
    }

    /**
     * (calc���|�W�V�������z���؋����s�����m�聄)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u���|�W�V�������z���؋����s�����m�聄�v��ԋp����B
     *  <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellContractAmtTemp(int l_intReservedDate)
    {

        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        double l_dblContractAmtTemp = 0;

        //�؋����v�Z.�w���ʐ敨OP���ʏW�v�ꗗ == null �̏ꍇ
        if(this.ifoSummaryContractPerIndexList == null)
        {
            //���|�W�V�������z���؋����s�����m�聄(n)�@@=�@@0
            l_dblContractAmtTemp = 0;
        }
        else
        {
            //�w���ʐ敨OP���ʏW�v
            WEB3IfoSummaryContractPerIndex l_curIfoSummary = null;

            //�w���ʐ敨OP���ʏW�v�̗v�f����LOOP����
            for (int index = 0; index < this.ifoSummaryContractPerIndexList.length; index++)
            {
                //�w���ʐ敨OP���ʏW�v���擾����
                l_curIfoSummary = this.ifoSummaryContractPerIndexList[index];

                //���|�W�V�������z���؋����s�����m�聄(n)�@@=�@@Sum(�w���ʔ��|�W�V�������z���؋����s�����m�聄(n))
                l_dblContractAmtTemp = l_dblContractAmtTemp + l_curIfoSummary.calcPossibleSellAmtTemp(l_intReservedDate);
            }
        }
        return l_dblContractAmtTemp;
    }

   /**
     * (calc�؋����s���z�����v�Z�� )<BR>
     * 
     * �[����{��Ђ̏ꍇ�A���Z�l���񂩂�؋����s���z���v�Z����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcIfoDepositLackChargeTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0 
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            return 0;
        }

        // �؋����s���z�����v�Z��
        double l_dblIfoDepositLackChargeTemp = 0;
        // ����؋����c�����؋����s�����m�聄(n) �E�E�E �؋����v�Z. calc����؋����c�����؋����s�����m�聄(n)
        double l_dblBalanceTemp = this.calcReceiptIfoDepositBalanceTemp(l_intReservedDate);
        // �؋������v�z���؋����s�����m�聄(n) �E�E�E�؋����v�Z. calc�؋������v�z���؋����s�����m�聄(n)
        double l_dblRequiredAmountTemp = this.calcIfoDepositRequiredAmountTemp(l_intReservedDate);
       
        //[a.�؋����v�Z.�؋����v�Z����.is���Z�l�����M�� == true ����
        //    �؋����v�Z.�؋����v�Z����.is�؋����s�����[�����M�� == false �̏ꍇ]
        //   �@@(�؋����s�����v�Z���ԑт̏ꍇ)
        if ( this.ifoDepositCalcCondition.isQuickReportReceived() && 
             !this.ifoDepositCalcCondition.isIfoDepositMailFlag())
        {
            //[a-1.�@@����؋����c�����؋����s�����m�聄(n)-�؋������v�z���؋����s�����m�聄(n)�j���O�@@�̏ꍇ�n
            if((l_dblBalanceTemp - l_dblRequiredAmountTemp) < 0 )
            {
                //  �؋����s���z�����v�Z��(n) = 
                //        A�����i�i����؋����c�����؋����s�����m�聄(n)�@@-�@@�؋������v�z���؋����s�����m�聄(n)�j�j
                l_dblIfoDepositLackChargeTemp  = Math.abs(l_dblBalanceTemp - l_dblRequiredAmountTemp);
            }
            else
            {
                //�؋����s���z�����v�Z�� = �O
                l_dblIfoDepositLackChargeTemp = 0;
            }
        }
        //�mb.�@@a�ȊO�̏ꍇ�n
        else
        {
            //�؋����s���z�����v�Z�� = �O
            l_dblIfoDepositLackChargeTemp = 0;
        }

        return l_dblIfoDepositLackChargeTemp;
    }
    
   /**
     * (calc���X�������z)<BR>
     * 
     * �u���X�������z�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcNext2BizDateDemandAmount()
    {
        //���X�������z
        double l_dblDemandAmount = 0;
        //����؋����c���iT+2)    �؋����v�Z. calc����؋����c���i2)
        double l_dblBalance  = this.calcReceiptIfoDepositBalance(WEB3IfoReservedDateDef.T_2);
        //�؋������v�z�iT+2)   �؋����v�Z. calc�؋������v�z�i2�j
        double l_dblRequiredAmount = this.calcIfoDepositRequiredAmount(WEB3IfoReservedDateDef.T_2);

        //�ma. ����؋����c���iT+2) - �؋������v�z�iT+2�j���O�@@�̏ꍇ�n�n
        if ((l_dblBalance - l_dblRequiredAmount) < 0 )
        {
            //���X�������z =  Abs(����؋����c���iT+2�@@- �؋������v�z�iT+2�j)
            l_dblDemandAmount = Math.abs(l_dblBalance - l_dblRequiredAmount);
        }
        //�mb. �ȊO�̏ꍇ�n
         else {
            //���X�������z =  0
            l_dblDemandAmount = 0;
        }

        return l_dblDemandAmount;
    }

   /**
     * (calc���������z���[�ꁄ)<BR>
     * 
     * �ucalc���������z���[�ꁄ�v��ԋp����B<BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * 
     * @@return double
     */
    public double calcNextBizDateDemandAmountEvening()
    {
        //���������z���[�ꁄ       
        double l_dblDemandAmount = 0;
        //����؋����c���iT+1)    �؋����v�Z. calc����؋����c���i1)
        double l_dblBalance  = this.calcReceiptIfoDepositBalance(WEB3IfoReservedDateDef.T_1);
        //�؋������v�z�iT+1)   �؋����v�Z. calc�؋������v�z�i1�j
        double l_dblRequiredAmount = this.calcIfoDepositRequiredAmount(WEB3IfoReservedDateDef.T_1);

        //�ma. ����؋����c���iT+1�j- �؋������v�z�iT+1�j���O �̏ꍇ�n
        if ((l_dblBalance - l_dblRequiredAmount) < 0 )
        {
            //���������z���[�ꁄ =  Abs(����؋����c���iT+1�j-�@@�؋������v�z�iT+1�j)
            l_dblDemandAmount = Math.abs(l_dblBalance - l_dblRequiredAmount);
        }
         //�mb. �ȊO�̏ꍇ�n
        else {
            //���������z���[�ꁄ =  0
           l_dblDemandAmount = 0;
        }

        return l_dblDemandAmount;
    }


    
}
@
