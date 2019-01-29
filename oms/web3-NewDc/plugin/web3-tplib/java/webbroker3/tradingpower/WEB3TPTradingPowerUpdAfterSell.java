head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpdAfterSell.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���t��]�͍X�V(WEB3TPTradingPowerUpdAfterSell.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower;

import java.util.List;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterSell;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorNewSellOrder;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���t��]�͍X�V)
 * ���]�͍X�V�N���X�̊g���N���X
 * ���������t����/�M�p���n�����̎���]�̓`�F�b�N���Ɏg�p����B
 */
public class WEB3TPTradingPowerUpdAfterSell extends WEB3TPTradingPowerUpd
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerUpdAfterSell.class);

    /**
     * (�f�t�H���g�R���X�g���N�^)
     */
    public WEB3TPTradingPowerUpdAfterSell()
    {
        super();
    }

    /**
     * (�R���X�g���N�^)
     * 
     * ���V�[�P���X�}�u(���t��]�͍X�V)�R���X�g���N�^�v�Q��
     * 
     * @@param l_lngAccountId - (����ID)
     * @@param l_blnMarginFlag - (�M�p�ڋq�t���O)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_lngOrderProductId - (��������ID)
     */
    public WEB3TPTradingPowerUpdAfterSell(
        long l_lngAccountId,
        boolean l_blnMarginFlag,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        long l_lngOrderProductId)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerUpdAfterSell(long, boolean, WEB3TPCalcCondition, WEB3TPNewOrderSpec[], long)";
        log.entering(STR_METHOD_NAME);

        /*
         * �ڋq�����I�u�W�F�N�g����       
         */
        WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(l_lngAccountId, l_blnMarginFlag);

        this.accountInfo = l_accountInfo;
        this.calcCondition = l_calcCondition;
        this.newOrderSpecs = l_newOrderSpecs;

        /*
         * �������������ꗗ���擾
         */
        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance()
            .getTodaysEquityOrders(l_accountInfo, l_calcCondition);
        /*
         * ���ʏ��I�u�W�F�N�g�쐬
         */
        WEB3TPContractInfo l_contractInfo = WEB3TPContractInfo.create(
            this.accountInfo,
            this.calcCondition,
            this.newOrderSpecs);
        l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);

        //���ʏ�񃍁[�h
        l_contractInfo.loadContractInfo();
        this.contractInfo = l_contractInfo;

        /*
         * �،��]���I�u�W�F�N�g�쐬
         */
        WEB3TPSecurityValuation l_securityValuation = WEB3TPSecurityValuation.create(
            this.accountInfo,
            this.calcCondition,
            this.newOrderSpecs,
            l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //�������Ə،��]���z���[�h
        l_securityValuation.doSecurityValuationPerProductLoad();
        this.securityValuation = l_securityValuation;

        /*
         * ���t�㑍�����I�u�W�F�N�g�쐬
         */
        WEB3TPCashValuationAfterSell l_cashValuation = WEB3TPCashValuationAfterSell.createWEB3TPCashValuationAfterSell(
            this.accountInfo,
            this.calcCondition,
            this.newOrderSpecs,
            l_lngOrderProductId);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //���t�㑍�������[�h
        l_cashValuation.loadAll();
        this.cashValuation = l_cashValuation;

        /*
         * �������σI�u�W�F�N�g�𐶐�
         */
        this.settlement = new WEB3TPSettlement(
            this.cashValuation,
            this.securityValuation,
            this.calcCondition);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (reSet���񔄕t������������)
     * 
     * ���񔄕t������������̓��e��ϊ�����B
     * 
     * ���V�[�P���X�}�u(���t��]�͍X�V)reSet���񔄕t������������v�Q��
     * 
     * @@param l_dblOrderQuantity - (��������)
     */
    public void reSetWEB3TPTransactionReflectorNewSellOrder(double l_dblOrderQuantity)
    {
        final String STR_METHOD_NAME = "reSetWEB3TPTransactionReflectorNewSellOrder(double)";
        log.entering(STR_METHOD_NAME);

        /*
         * ������ꗗ<����>���擾����B
         */
        List l_todayTransactions = this.cashValuation.getTransactionAmount()
            .getTodaysTransactions();

        /*
         * LOOP�����F������ꗗ<����>�̗v�f����
         */
        for(int index = 0; index < l_todayTransactions.size(); index++)
        {
            //�v�f�I�u�W�F�N�g���擾����B
            Object l_element = l_todayTransactions.get(index);

            //�v�f�I�u�W�F�N�g���A���񔄕t������������N���X�̏ꍇ
            if(l_element instanceof WEB3TPTransactionReflectorNewSellOrder)
            {
                //�v�f�I�u�W�F�N�g�����񔄕t������������N���X�ɃL���X�g����B
                WEB3TPTransactionReflectorNewSellOrder l_reflector = (WEB3TPTransactionReflectorNewSellOrder) l_element;

                //�����P�����擾
                double l_price = l_reflector.getPrice();

                /*
                 * �g���ϑ���h���v�Z����B
                 * 
                 * [�v�Z��]
                 * �g���ϑ���h = �擾���������P�� �~ ����.��������
                 */
                double l_dblExecutedAmount = l_price * l_dblOrderQuantity;

                l_reflector.setExecutedQuantity(l_dblOrderQuantity);
                l_reflector.setExecutedAmount(l_dblExecutedAmount);

                //LOOP������蔲����
                break;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
