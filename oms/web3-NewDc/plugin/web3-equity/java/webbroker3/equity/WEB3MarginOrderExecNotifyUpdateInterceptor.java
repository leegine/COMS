head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderExecNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�o���ʒm�X�V�C���^�Z�v�^(WEB3MarginExecNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
                   2006/11/03 �đo�g (���u) �c�a�X�V�d�lNo.181,���f��No.1040
                   2006/11/28 �đo�g (���u) ���f��No.1072,�c�a�X�V�d�lNo.190
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�o���ʒm�X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �o���ʒm���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �iEqTypeOrderManagerPersistenceEventInterceptor�̎����j<BR>
 * <BR>
 * ���f�[�^�̍X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �����������e�[�u���i�w�b�_�j�ɂ��ẮA<BR>
 * ��xTrade�W�������ł̍X�V�̂݁B�iDB�X�V�d�l�̃J�X�^�}�C�Y�s�v�j<BR>
 * ���������������e�[�u���ɂ��ẮA<BR>
 * ���X�[�p�[�N���X�u�X�V�C�x���g�C���^�Z�v�^.�X�V�l�ݒ�(��������)�v�ɂčX�V����B
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOrderExecNotifyUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderExecNotifyUpdateInterceptor.class);
    
    /**
     * (������)<BR>
     * �������B<BR>
     */
    private Date execTimestamp;
    
    /**
     * (���P��)<BR>
     * ���P���B<BR>
     */
    private double executedPrice;
    
    /**
     * (��芔��)<BR>
     * ��芔���B<BR>
     */
    private double execQuantity;
    
    /**
     * (�M�p�o���ʒm�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * @@param l_dateExecutedTimestamp ������<BR>
     *�y�����o���ʒm�L���[�e�[�u���z�������B
     * @@param l_dblExecutedPrice ���P��<BR>
     * ���P���B
     * @@param l_dblExecQuantity ��芔��<BR>
     * ��芔���B<BR>
     * @@return WEB3MarginOrderExecNotifyUpdateInterceptor
     */
    public WEB3MarginOrderExecNotifyUpdateInterceptor(
        Date l_dateExecutedTimestamp,
        double l_dblExecutedPrice, 
        double l_dblExecQuantity) 
    {
        this.execTimestamp      = l_dateExecutedTimestamp;
        this.executedPrice      = l_dblExecutedPrice;
        this.execQuantity       = l_dblExecQuantity;
    }
    
    /**
     * �i�X�V�l�ݒ�j�B<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@������i�����̏�����OrderManagerPersistenceContext.UNDO_EXECUTION�j<BR>
     * �@@�@@�@@�̏ꍇ�́A���������ɏ������I������B<BR>
     * <BR>
     * �Q�j�@@�J�X�^�}�C�Y�ł̒l�Z�b�g���s���B<BR>
     * <BR>
     * �Q�|�P�j�@@���Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����̊������Params.�����P��ID�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�|�Q�j�@@�������Params�̃v���p�e�B�ݒ�d�l���J�X�^�}�C�Y����B<BR>
     * <BR>
     * �@@�@@�@@���N���X�̃v���p�e�B�A�y�тQ�|�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g���A<BR>
     * �@@�@@�@@�������Params�̃v���p�e�B��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�@@�X�V���e��DB�ݒ�_���u�i���j�M�p�o���ʒm_���e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �R�j�@@�����̊������Params��ԋp����B<BR>
     * <BR>
     * @@param l_updateType �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B
     * @@param l_process ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderExecParams �������Params<BR>
     * ������肪�ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderExecutionParams
     * @@roseuid 40C8EBAD00C9
     */
    public EqtypeOrderExecutionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderExecutionParams l_orderExecParams) 
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderExecutionParams)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@������i�����̏�����OrderManagerPersistenceContext.UNDO_EXECUTION�j
        //�@@�@@�@@�̏ꍇ�́A���������ɏ������I������B<BR>
        if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_process))
        {
            return l_orderExecParams;
        }
        
        //�Q�j�@@�J�X�^�}�C�Y�ł̒l�Z�b�g���s��
        //�Q�|�P�j�@@���Ώۂ̒����P�ʃI�u�W�F�N�g���擾����
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager = (EqTypeOrderManager) l_tradingMod.getOrderManager();               

        OrderUnit l_orderUnit = null;
        EqtypeOrderUnitRow l_orderUnitRow = null;
        try
        {
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                l_orderExecParams.getOrderUnitId());
            l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {   
            log.error(STR_METHOD_NAME,l_nfe);   
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), l_nfe);
        }

        //�Q�|�Q�j�@@�������Params�̃v���p�e�B�ݒ�d�l���J�X�^�}�C�Y����
        //��n�����Z�b�g
        l_orderExecParams.setDeliveryDate(l_orderUnitRow.getDeliveryDate());
        
        //���������Z�b�g
        l_orderExecParams.setExecTimestamp(this.execTimestamp);
        
        //���������Z�b�g
        l_orderExecParams.setBizDate(l_orderUnitRow.getBizDate());
        
        //���ʃR�[�h���Z�b�g
        l_orderExecParams.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());

        log.exiting(STR_METHOD_NAME);
        return l_orderExecParams;
    }    

    /**
     * �i�X�V�l�ݒ�j�B<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�g�����ڃZ�b�g<BR>
     * <BR>
     * �@@�e�v���p�e�B����A<BR>
     * �@@�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�X�V���e�́A�ȉ���DB�ݒ�_�����Q�ƁB<BR>
     * �@@�E�u�i���j�M�p�o���ʒm_���������P�ʃe�[�u��.xls�v<BR>
     * �@@�E�u�i������j�M�p�o���ʒm_���������P�ʃe�[�u��.xls�v<BR>
     * <BR>
     * �Q�j�@@�T�Z��n������Čv�Z���A�p�����[�^.�����P��Params�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�|�P�j�ăI�[�v��(��)�������f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��" �̏ꍇ<BR>
     *�@@�i���X�V�O.�����L�����=="�N���[�Y" ���X�V��.�����L�����=="�I�[�v��"�̏ꍇ�A�ăI�[�v���j<BR>
     * <BR>
     *�@@�T�Z��n������Čv�Z����B<BR>
     * <BR>
     *�@@�Q�|�P�|�P�j�⏕�������擾����B<BR>
     *�@@�@@�g���A�J�E���g�}�l�[�W��.get�⏕�������R�[�����A�⏕�������擾����B<BR>
     *�@@�@@[����]<BR>
     *�@@�@@�@@�����̒����P��Params.����ID<BR>
     *�@@�@@�@@�����̒����P��Params.�⏕����ID<BR>
     * <BR>
     *�@@�Q�|�P�|�Q�j�X�g�b�v�����������̊T�Z��n������Čv�Z����B<BR>
     *�@@�@@�g�����������}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B<BR>
     *�@@�@@[����]<BR>
     *�@@�@@�@@�����P��<BR>
     *�@@�@@�@@�Q�|�P�|�P�j�Ŏ擾�����⏕����<BR>
     * <BR>
     * �Q�|�Q�jget�X�g�b�v�����������T�Z����v�Z����()�I��null�̏ꍇ<BR>
     * <BR>
     *�@@�@@�@@�@@�ȉ��̒l�𒍕��P��Params�ɃZ�b�g���A�����P��Param��ԋp����B<BR>
     * <BR>
     *�@@�@@�@@�@@�����P��Params.{�T�Z��n���,�s�ꂩ��m�F�ς݂̊T�Z��n���}�ɁA<BR>
     *�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�T�Z��n������Z�b�g����B<BR>
     * <BR>
     *�@@�@@�@@�@@�����P��Params.�o�����P���A�s�ꂩ��m�F�ς̒����P���p�ɁA<BR>
     *�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �Q�|�R�j�Q�|�Q�j�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�|�R�|�P�j�@@�萔���I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@�����v�Z�T�[�r�X.create�萔��(�����̒����P��Params.����ID)���R�[������B<BR>
     * <BR>
     * �ȉ��A�����ɃZ�b�g���钍���P��Params�Ƃ��āA<BR>
     * �u�P�j�@@�g�����ڃZ�b�g�v��̒����P��Params���g�p����B<BR>
     * �i�S�����^��S�����̔��莞�����l�B�j<BR>
     * <BR>
     * �@@�Q�|�R�|�Q�j�@@�ԍϒ����̏ꍇ�́A<BR>
     * �@@�@@�S���p�v�Z�P�����擾���A�����P��Params�ɃZ�b�g����B<BR>
     * �@@�@@�i���S�����ȊO�i�����^�ꕔ���j�̂ݎ��s�B�j<BR>
     * <BR>
     * �@@�@@�g�����������}�l�[�W��.calc�S�����z�v�Z�P��()���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc�S�����z�v�Z�P��()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g<BR>
     * �@@�@@�@@������ʁF�@@�����P��Params�̓�����<BR>
     * �@@�@@�@@�w�l�F�@@�����P��Params�̓�����<BR>
     * �@@�@@�@@�iW�w�l�j�����w�l�F�@@�����P��Params�̓�����<BR>
     * �@@�@@�@@���������F�@@�����P��Params�̓�����<BR>
     * �@@�@@�@@���s�����F�@@�����P��Params�̓�����<BR>
     * �@@�@@�@@�l�i�����F�@@�����P��Params�̓�����<BR>
     * �@@�@@�@@��������F�@@�����P��.getTradedProduct()<BR>
     * �@@�@@�@@�⏕�����F�@@�����P��Params.�o����ID, �⏕����ID�p�ɊY������⏕����<BR>
     * �@@�@@�@@�m�F���P���F�@@null<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�����P��Params.�o�����P���A�s�ꂩ��m�F�ς̒����P���p�ɁA<BR>
     * �@@�@@calc�S�����z�v�Z�P��()�̖߂�l���Z�b�g����B<BR>
     * �@@�@@���萔���v�Z�����́Acalc�S�����z�v�Z�P��()���ōăZ�b�g�����B<BR>
     * <BR>
     * �ȉ��A�����J�e�S�����ɕ��򂷂�B<BR>
     * =================================================================================<BR>
     * �@@�Q�|�R�|�R�j�@@�V�K�������̏ꍇ<BR>
     * <BR>
     * �@@�@@��������Čv�Z���A�����P��Params�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�g�����������}�l�[�W��.calc�����������()���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc�����������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g<BR>
     * �@@�@@�@@�w�l�F�@@�����P��Params.�w�l <BR>
     * �@@�@@�@@�iW�w�l�j�����w�l�F�@@�����P��Params.�i�v�w�l�j�����w�l<BR>
     * �@@�@@�@@�t�w�l��l�F�@@�����P��Params.�t�w�l��l<BR>
     *�@@�@@�@@ ���s�����F�@@�����P��Params.���s����<BR>
     * �@@�@@�@@�iW�w�l�j���s�����F�@@�����P��Params.�i�v�w�l�j���s����<BR>
     *�@@�@@�@@ �l�i�����F�@@�����P��Params.�l�i����<BR>
     * �@@�@@�@@���������F�@@�����P��Params.��������<BR>
     * �@@�@@�@@�m�F���擾�����F�@@null�i�Œ�j<BR>
     * �@@�@@�@@is�X�g�b�v�����L���F�@@�g�����������}�l�[�W��.is�X�g�b�v�����L��(�����P��)�̖߂�l<BR>
     * �@@�@@�@@is�����F�@@�g�����������}�l�[�W��.is������(�����P��)�̖߂�l<BR>
     * �@@�@@�@@�⏕�����F�@@�����P��Params.�o����ID, �⏕����ID�p�ɊY������⏕����<BR>
     * �@@�@@�@@��������F�����P��.getTradedProduct()<BR>
     * �@@�@@�@@�����F�@@�����P��Params.�s�ꂩ��m�F�ς݂̐���<BR>
     * �@@�@@�@@��萔�ʁF�@@�����P��Params.��萔��<BR>
     * �@@�@@�@@���v�����z�F�@@�����P��Params.���v�����z<BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i������z�`�F�b�N�Ȃ��j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�����P��Params.�o�T�Z��n����A�s�ꂩ��m�F�ς̊T�Z��n����p�ɁA<BR>
     * �@@�@@calc�����������()�̖߂�l.get�T�Z�����()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�����P��Params.�o�����P���A�s�ꂩ��m�F�ς̒����P���p�ɁA<BR>
     * �@@�@@calc�����������()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B<BR>
     * �@@�i�������P��/�s�ꂩ��m�F�ς݂̒����P���Z�b�g�́A�S�����ȊO<BR>
     *  �@@�@@�i�����^�ꕔ���j�̂ݎ��s�B�j<BR>
     * <BR>
     * =================================================================================<BR>
     * �Q�|�R�|�S�j�@@�ԍϒ����̏ꍇ<BR>
     * <BR>
     * �@@�@@�T�Z���ϑ��v������Čv�Z���A�����P��Params�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�g�����������}�l�[�W��.calc�T�Z���ϑ��v���()���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc�T�Z���ϑ��v���()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g<BR>
     * �@@�@@�@@�w�l�F�@@�����P��Params.�����P��<BR>
     * �@@�@@�@@�⏕�����F�@@�����P��Params.�o����ID, �⏕����ID�p�ɊY������⏕����<BR>
     * �@@�@@�@@��������F�@@�����P��.getTradedProduct()<BR>
     * �@@�@@�@@���ό����G���g���F�@@�����|�W�V�����}�l�[�W��.create���ό����G���g��(<BR>
     * �@@�@@�@@�@@�@@�����P��.�����P��ID)<BR>
     * �@@�@@�@@���ʁF�@@�����P��Params.�s�ꂩ��m�F�ς݂̐���<BR>
     * �@@�@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@�@@�����萔�ʁF�@@0���Z�b�g�B�i�Œ�j<BR>
     * �@@�@@�@@�@@�@@��������^��������͒����P�ʃI�u�W�F�N�g�ɍڂ��Ă���̂ŁA�ݒ�s�v�B<BR>
     * �@@�@@�@@������P���F�@@this.���P��<BR>
     * �@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i������z�`�F�b�N�Ȃ��j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�����P��Params.�o�T�Z��n����A�s�ꂩ��m�F�ς̊T�Z��n����p�ɁA<BR>
     * �@@�@@calc�T�Z���ϑ��v���()�̖߂�l�I�u�W�F�N�g.get�T�Z���ϑ��v���()�̖߂�l���Z�b�g����B<BR>
     * <BR>
     * =================================================================================<BR>
     * �Q�|�R�|�T�j�@@�������n�����̏ꍇ<BR>
     * <BR>
     * �@@�@@�T�Z��n������Čv�Z���A�����P��Params�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�g�����������}�l�[�W��.calc�T�Z��n����i�������n�j()���R�[������B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc�T�Z��n����i�������n�j()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@���ό����G���g���F�@@�����|�W�V�����}�l�[�W��.create���ό����G���g��(<BR>
     * �@@�@@�@@�@@�@@�����P��.�����P��ID)<BR>
     * �@@�@@�@@���ʁF�@@�����P��Params.�s�ꂩ��m�F�ς݂̐���<BR>
     * �@@�@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�����P��Params.�o�T�Z��n����A�s�ꂩ��m�F�ς̊T�Z��n����p�ɁA<BR>
     * �@@�@@calc�T�Z��n����i�������n�j()�̖߂�l���Z�b�g����B<BR>
     * =================================================================================<BR>
     * <BR>
     * �R�j�@@�v���p�e�B���Z�b�g���������P��Params��ԋp����B<BR>
     * @@param l_updateType �X�V�^�C�v<BR>
     * �@@�@@�@@INSERT�܂��́AUPDATE�B<BR>
     * �@@�@@�@@OrderManagerPersistenceType�ɂĒ萔��`�B 
     * @@param l_process ����<BR>
     * �@@�@@�@@�iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams �����P��Params<BR>
     * �@@�@@�@@���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderUnitParams
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        //------------------------
        // �p�����[�^null�`�F�b�N
        //------------------------
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager = (EqTypeOrderManager) l_tradingMod.getOrderManager();               

        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
        WEB3EquityOrderManager l_eqtypeOrderManager = (WEB3EquityOrderManager)l_orderManager;
        //is�X�g�b�v�����L��
        boolean l_blnStopOrderValid = false;
        //is����
        boolean l_blnSellOrder = false;
        try
        {
            l_blnStopOrderValid = l_eqtypeOrderManager.isStopOrderValid(l_orderUnit);
            l_blnSellOrder = l_eqtypeOrderManager.isSellOrder(l_orderUnit);
        }
        catch (WEB3BaseException l_exc)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }
        //�X�V��.�����L�����
        OrderOpenStatusEnum l_orderOpenStatusAftUpdate = l_orderUnitParams.getOrderOpenStatus();
        EqTypeOrderUnit l_orderUnitBefUpdate = null;
        try
        {
            l_orderUnitBefUpdate =
                (EqTypeOrderUnit)l_eqtypeOrderManager.getOrderUnit(l_orderUnitParams.getOrderUnitId());
        }
        catch (NotFoundException l_ne)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ne.getMessage(),
                l_ne);
        }
        //�X�V�O.�����L�����
        OrderOpenStatusEnum l_orderOpenStatusBefUpdate = l_orderUnitBefUpdate.getOrderOpenStatus();
        //�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()
        String l_strWLimitEnableStatusDiv = null;
        try
        {
            l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnitBefUpdate);
        }
        catch(WEB3BaseException l_exc)
        {
            log.debug(STR_METHOD_NAME, l_exc);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_exc.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }
        // ������̏ꍇ
        if (OrderManagerPersistenceContext.UNDO_EXECUTION.equals(l_process))
        {
            //---------------------------
            //�w�l�^�s�ꂩ��m�F�ς݂̎w�l
            //---------------------------
            //�l�i������"���s�c���w�l" ����
            //��萔��==0�i���Ȃ��ƂȂ������j�̏ꍇ�F0
            //��L�ȊO�̏ꍇ�F�@@�i�����l�j
            if (
                WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER
                    .equals(l_orderUnitParams.getPriceConditionType()) &&
                l_orderUnitParams.getExecutedQuantity() == 0)
            {
                // �w�l
                l_orderUnitParams.setLimitPrice(0.0D);
                // �s�ꂩ��̊m�F�ς݂̎w�l
                l_orderUnitParams.setConfirmedPrice(0.0D);
            }

            //���~�b�g�����L��(*1)���ăI�[�v��(*2)�̏ꍇ
            //(*1)�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��"���~�b�g�����L��"�̏ꍇ�A���~�b�g�����L���B
            //�@@�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            //�@@�iEqtypeOrderUnitParams���g�����������}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            //(*2)�X�V�O.�����L�����=="�N���[�Y" ���X�V��.�����L�����=="�I�[�v��"�̏ꍇ�A�ăI�[�v���B
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv)
                && OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusBefUpdate)
                && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusAftUpdate))
            {
                //����������  �X�V�O�̔�������   �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
                //�������������Z�q  �X�V�O�̔����������Z�q    �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
                //���t�w�l��l   �X�V�O�̋t�w�l��l    �ȊO�̏ꍇ�F�i�����l�j
                if (l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }
                //���iW�w�l�j�����w�l   �X�V�O�́iW�w�l�j�����w�l     �ȊO�̏ꍇ�F�i�����l�j
                if (l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }
                //���iW�w�l�j���s����   �X�V�O�́iW�w�l�j���s����     �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
                //�iW�w�l�j���s����   null  �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setWLimitExecCondType(null);
                //��������  �@@0:DEFAULT  �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                //�����������Z�q   null  �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setOrderCondOperator(null);
                //�t�w�l��l    null  �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setStopOrderPrice(null);
                //�iW�w�l�j�����w�l     null  �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setWLimitPrice(null);
                //���N�G�X�g�^�C�v    5:����   �ȊO�̏ꍇ�F�i�����l�j
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
        }
        // ���̏ꍇ
        else
        {
            //---------------------------
            //�w�l�^�s�ꂩ��m�F�ς݂̎w�l
            //---------------------------
            //�l�i������"���s�c���w�l"
            //���� ������i��萔��==�C���^�Z�v�^�̃v���p�e�B.��芔���j
            //���� �ꕔ�o���i�����P��.isPartiallyExecuted( )==true�j�̏ꍇ�F
            //�@@�C���^�Z�v�^�̃v���p�e�B.���P���B
            //��L�ȊO�̏ꍇ�F�@@�i�����l�j
            if (WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_orderUnitParams.getPriceConditionType()) &&
                l_orderUnit.isMarketOrder() &&
                l_orderUnit.isPartiallyExecuted())
            { 
                // �w�l
                l_orderUnitParams.setLimitPrice(this.executedPrice);
                // �s�ꂩ��m�F�ς݂̎w�l
                l_orderUnitParams.setConfirmedPrice(this.executedPrice);
            }
            
            // ���������E����敪
            l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);
        }

        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
        //�Q�|�P�j�ăI�[�v��(��)�������f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪��"���~�b�g�����L��" �̏ꍇ
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusBefUpdate)
            && OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusAftUpdate)
            && WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
        {
            //�Q�|�P�|�P�j�⏕�������擾����B
            //�g���A�J�E���g�}�l�[�W��.get�⏕�������R�[�����A�⏕�������擾����B
            //[����]
            //�����̒����P��Params.����ID
            //�����̒����P��Params.�⏕����ID
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    l_accountManager.getSubAccount(
                        l_orderUnitParams.getAccountId(),
                        l_orderUnitParams.getSubAccountId());
            }
            catch (NotFoundException l_nfe)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            //�Q�|�P�|�Q�j�X�g�b�v�����������̊T�Z��n������Čv�Z����B
            //�g�����������}�l�[�W��.get�X�g�b�v�����������T�Z����v�Z����()���R�[������B
            //[����]
            //�����P��
            //�Q�|�P�|�P�j�Ŏ擾�����⏕����
            try
            {
                l_equityEstimatedPrice =
                    l_eqtypeOrderManager.getStopOrderExpireEstimatedPrice(l_orderUnit, l_subAccount);
            }
            catch(WEB3BaseException l_exc)
            {
                log.debug(STR_METHOD_NAME, l_exc);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    l_exc.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exc.getMessage(),
                    l_exc);
            }
        }

        //�Q�|�Q�jget�X�g�b�v�����������T�Z����v�Z����()�I��null�̏ꍇ
        //�ȉ��̒l�𒍕��P��Params�ɃZ�b�g���A�����P��Param��ԋp����B
        if (l_equityEstimatedPrice != null)
        {
            //�����P��Params.{�T�Z��n���,�s�ꂩ��m�F�ς݂̊T�Z��n���}�ɁA
            //get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�T�Z��n������Z�b�g����B
            l_orderUnitParams.setEstimatedPrice(l_equityEstimatedPrice.getEstimateDeliveryAmount());
            l_orderUnitParams.setConfirmedEstimatedPrice(l_equityEstimatedPrice.getEstimateDeliveryAmount());

            //�����P��Params.�o�����P���A�s�ꂩ��m�F�ς̒����P���p�ɁA
            //get�X�g�b�v�����������T�Z����v�Z����()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B
            l_orderUnitParams.setPrice(l_equityEstimatedPrice.getCalcUnitPrice());
            l_orderUnitParams.setConfirmedOrderPrice(l_equityEstimatedPrice.getCalcUnitPrice());

            log.exiting(STR_METHOD_NAME);
            return l_orderUnitParams;
        }

        // �T�Z��n����̍Čv�Z���s��
        try
        {
            WEB3EquityBizLogicProvider l_bizLogic =
                (WEB3EquityBizLogicProvider)l_tradingMod.getBizLogicProvider();
            // �萔���I�u�W�F�N�g�̍쐬
            WEB3GentradeCommission l_commission =
                l_bizLogic.createCommission(l_orderUnitParams.getOrderId());
            
            // �⏕�����̎擾
            WEB3GentradeAccountManager l_accManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accManager.getSubAccount(
                    l_orderUnitParams.getAccountId(),
		            l_orderUnitParams.getSubAccountId());
                    
            // �S���p�v�Z�P�����擾����B
            // ������� or �ꕔ���̐V�K�������A�ԍϒ����̂ݎ��s�B
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitParams.getOrderCateg()) &&
                l_orderUnit.isFullyExecuted() == false)
            {
                // calc�S�����z�v�Z�P��()
                double l_dblOrderPrice =
                    l_eqtypeOrderManager.calcPriceForRestraintAmount(
                        l_commission,
                        l_orderUnitParams.getOrderType(),
		                l_orderUnitParams.getLimitPrice(),
		                l_orderUnitParams.getWLimitPrice(),
		                l_orderUnitParams.getOrderConditionType(),
		                l_orderUnitParams.getExecutionConditionType(),
		                l_orderUnitParams.getPriceConditionType(),
                        (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
                        l_subAccount,
                        null);
                // �����P���A�s�ꂩ��m�F�ς̒����P���̃Z�b�g
                l_orderUnitParams.setPrice(l_dblOrderPrice);
                l_orderUnitParams.setConfirmedOrderPrice(l_dblOrderPrice);
            }
            
            // �T�Z��n����̍Čv�Z
            double l_dblEstimatedAmount = 0.0D;
            WEB3EquityOrderManager l_equityOrderManager = (WEB3EquityOrderManager)l_orderManager;
            
            // �V�K�������̏ꍇ
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnitParams.getOrderCateg()))
            {
                WEB3EquityEstimatedContractPrice l_contractPrice =
                    l_equityOrderManager.calcContractAmountAtOrder(
                        l_commission,
                        l_orderUnitParams.getLimitPrice(),
                        l_orderUnitParams.getWLimitPrice(),
                        l_orderUnitParams.getStopOrderPrice(),
                        l_orderUnitParams.getExecutionConditionType(),
                        l_orderUnitParams.getWLimitExecCondType(),
                        l_orderUnitParams.getPriceConditionType(),
                        l_orderUnitParams.getOrderConditionType(),
                        null,
                        l_blnStopOrderValid,
                        l_blnSellOrder,
                        l_subAccount,
                        (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
                        l_orderUnitParams.getConfirmedQuantity(),
                        l_orderUnitParams.getExecutedQuantity(),
                        l_orderUnitParams.getExecutedAmount(),
                        true);
                l_dblEstimatedAmount = l_contractPrice.getEstimatedContractPrice();
                if (!l_orderUnit.isFullyExecuted())
                {
                    double l_dblCalcUnitPrice = l_contractPrice.getCalcUnitPrice();
                    //�����P��
                    //calc�����������()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B
                    l_orderUnitParams.setPrice(l_dblCalcUnitPrice);

                    //�s�ꂩ��m�F�ς݂̒����P��
                    //calc�����������()�̖߂�l.get�v�Z�P��()�̖߂�l���Z�b�g����B
                    // �C���^�Z�v�^.�X�V�l�ݒ�()���ŋ��߂������P��
                    l_orderUnitParams.setConfirmedOrderPrice(l_dblCalcUnitPrice);
                }
            }
            // �ԍϒ����̏ꍇ
            else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnitParams.getOrderCateg()))
            {
                // ���ό����G���g���̍쐬
                WEB3EquityPositionManager l_positionManager =
                    (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
                EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries =
                    l_positionManager.createCloseMarginContractEntry(l_orderUnitParams.getOrderUnitId());
                
                WEB3EquityRealizedProfitAndLossPrice l_calcResult =
                    l_equityOrderManager.calcEstimatedRealizedProfitAndLossAmount(
                        l_commission,
	                    l_orderUnitParams.getPrice(),
	                    l_subAccount,
	                    (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct(),
	                    l_settleContractOrderEntries,
	                    l_orderUnitParams.getConfirmedQuantity(),
	                    l_orderUnit,
	                    0.0D,
	                    this.executedPrice,
	                    true);
	                    
	            l_dblEstimatedAmount = l_calcResult.getEstimatedRealizedProfitAndLossAmount();
            }
            // �������n�����̏ꍇ
            else
            {
                // ���ό����G���g���̍쐬
                WEB3EquityPositionManager l_positionManager =
                    (WEB3EquityPositionManager)l_tradingMod.getPositionManager();
                EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries =
                    l_positionManager.createCloseMarginContractEntry(l_orderUnitParams.getOrderUnitId());
                
                l_dblEstimatedAmount = l_equityOrderManager.calcEstimatedSwapPrice(
                    l_settleContractOrderEntries,
                    l_orderUnitParams.getConfirmedQuantity(),
                    l_orderUnit);
            }
            
            // �T�Z��n����A�s�ꂩ��m�F�ς̊T�Z��n����̃Z�b�g
            l_orderUnitParams.setEstimatedPrice(l_dblEstimatedAmount);
            l_orderUnitParams.setConfirmedEstimatedPrice(l_dblEstimatedAmount);
        }
        catch (WEB3BaseException l_be)
        {
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be);
        }
        catch (Exception l_ex)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }

}
@
