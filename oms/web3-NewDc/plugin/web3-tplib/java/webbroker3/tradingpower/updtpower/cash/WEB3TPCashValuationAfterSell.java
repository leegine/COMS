head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashValuationAfterSell.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���t�㑍����(WEB3TPCashValuationAfterSell.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower.updtpower.cash;

import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (���t�㑍����)
 * ���������N���X�̊g���N���X
 */
public class WEB3TPCashValuationAfterSell extends WEB3TPCashValuation
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPCashValuationAfterSell.class);

    /**
     * (�f�t�H���g�R���X�g���N�^)
     */
    public WEB3TPCashValuationAfterSell()
    {
        super();
    }

    /**
     * (static���\�b�h)(create���t�㑍����)
     * 
     * ���t�㑍�����I�u�W�F�N�g�𐶐����ԋp����B
     * 
     * �P�j���t�㑍�����I�u�W�F�N�g�𐶐�����B
     * �@@-�f�t�H���g�R���X�g���N�^���R�[��
     * 
     * �Q�j�ڋq�������Z�b�g
     * �@@�@@-���t�㑍�����I�u�W�F�N�g.set�ڋq����()���R�[��
     * 
     * �@@�@@[����]�@@
     * �@@�@@�@@�ڋq�����F����.�ڋq����
     * �@@
     * �R�j�v�Z�������Z�b�g
     * �@@�@@-���t�㑍�����I�u�W�F�N�g.set�]�͌v�Z�������R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@�]�͌v�Z�����F����.�]�͌v�Z����
     * 
     * �S�j���������e���Z�b�g
     * �@@�@@-���t�㑍�����I�u�W�F�N�g.set���������e()���R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@���������e�F����.���������e
     * 
     * �T�j�a������Z�b�g
     * �@@�T�|�P�j�a����I�u�W�F�N�g�𐶐�����B
     * �@@�@@-�a���.create�a���()���R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@�������F�P�j�Ő����������t�㑍�����I�u�W�F�N�g
     * 
     * �@@�T�|�Q�j�a������Z�b�g����
     * �@@�@@-���t�㑍�����I�u�W�F�N�gset�a���()���R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@�a����F�T�|�Q�j�Ő��������a����I�u�W�F�N�g
     * 
     * �U�j���������Z�b�g
     * �@@�U�|�P�j���t��������I�u�W�F�N�g�𐶐�����B
     * �@@�@@-���t�������.create���t�������()���R�[��
     * �@@
     * �@@�@@[����]
     * �@@�@@�@@�������F�P�j�Ő����������t�㑍�����I�u�W�F�N�g
     * �@@�@@�@@��������ID�F����.��������ID
     * 
     * �@@�U�|�Q�j���������Z�b�g����B
     * �@@�@@-���t�㑍�����I�u�W�F�N�g.set������()���R�[��
     * �@@
     * �@@�@@[����]
     * �@@�@@�@@�������F�U�|�P�j�Ő����������t��������I�u�W�F�N�g
     * 
     * �V�j�S�������Z�b�g
     * �@@�V�|�P�j�S�����I�u�W�F�N�g�𐶐�����B
     * �@@�@@-�S����.create�S����()���R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@�������F�P�j�Ő����������t�㑍�����I�u�W�F�N�g
     * 
     * �@@�V�|�Q�j�S�������Z�b�g����B
     * �@@�@@-���t�㑍�����I�u�W�F�N�g.set�S����()���R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@�S�����F�V�|�P�j�Ő��������S�����I�u�W�F�N�g
     * �@@
     * �W�j�����������t�㑍�����I�u�W�F�N�g�̕ԋp����B
     * 
     * �@@[�ԋp�l]
     * �@@�@@�����������t�㑍�����I�u�W�F�N�g
     * 
     * @@param l_accountInfo - (�ڋq����)
     * @@param l_calcCondition - (�v�Z����)
     * @@param l_newOrderSpecs - (���������e)
     * @@param l_lngOrderProductId - (��������ID)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterSell
     */
    public static WEB3TPCashValuationAfterSell createWEB3TPCashValuationAfterSell(
        WEB3TPAccountInfo l_accountInfo,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs,
        long l_lngOrderProductId)
    {
        final String STR_METHOD_NAME = "createWEB3TPCashValuationAfterSell(WEB3TPAccountInfo, WEB3TPCalcCondition, WEB3TPNewOrderSpec[], long)";
        log.entering(STR_METHOD_NAME);

        /*
         * ���t�㑍�����I�u�W�F�N�g�𐶐�����B
         */
        WEB3TPCashValuationAfterSell l_instance = new WEB3TPCashValuationAfterSell();

        //�ڋq�������Z�b�g
        l_instance.setAccountInfo(l_accountInfo);
        //�v�Z�������Z�b�g
        l_instance.setCalcCondition(l_calcCondition);
        //���������e���Z�b�g
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        //���������Z�b�g
        l_instance.setTransactionAmount(WEB3TPTransactionAmountAfterSell.createWEB3TPTransactionAmountAfterSell(
            l_instance,
            l_lngOrderProductId));
        //�a������Z�b�g
        l_instance.setCashBalance(WEB3TPCashBalance.create(l_instance));
        //�S�������Z�b�g
        l_instance.setRestraint(WEB3TPRestraint.create(l_instance));

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }
}@
