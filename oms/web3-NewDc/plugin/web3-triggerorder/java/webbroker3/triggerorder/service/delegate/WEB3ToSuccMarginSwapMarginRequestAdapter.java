head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�M�p����������n���N�G�X�g�A�_�v�^(WEB3ToSuccMarginSwapMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 ���(���u) �V�K�쐬
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�M�p����������n���N�G�X�g�A�_�v�^)<BR>
 * �i�A���j�M�p����������n���N�G�X�g�A�_�v�^�B<BR>
 * 
 * @@author ���
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginRequestAdapter extends WEB3MarginSwapMarginRequestAdapter 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginRequestAdapter.class);
    
    /**
     * (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g<BR>
     */
    public EqTypeOrderUnit parentOrderUnit;
    
    /**
     * @@roseuid 436ACF6301B5
     */
    public WEB3ToSuccMarginSwapMarginRequestAdapter() 
    {
     
    }
    
    /**
     * �i�A���j�M�p����������n���N�G�X�g�A�_�v�^�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�{�C���X�^���X�𐶐�������B<BR>
     * <BR>
     * �Q�j�@@�e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�A�������}�l�[�W��Impl.get�����e�����̒����P��(<BR>
     * �@@�@@���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)���R�[������B<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X�ɁA�����̃��N�G�X�g�f�[�^�A<BR>
     * �@@�@@�y�ю擾�����e�����̒����P�ʃI�u�W�F�N�g���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@�C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�I�u�W�F�N�g�B<BR>
     * @@return WEB3MarginSwapMarginRequestAdapter
     * @@roseuid 4344DC080153
     */
    public static WEB3MarginSwapMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�{�C���X�^���X�𐶐�������B
        WEB3ToSuccMarginSwapMarginRequestAdapter l_adapter = new WEB3ToSuccMarginSwapMarginRequestAdapter();
        
        //�Q�j�@@�e�����̒����P�ʃI�u�W�F�N�g���擾����B
        //�@@�@@�A�������}�l�[�W��Impl.get�����e�����̒����P��(
        //�@@�@@���N�G�X�g.�A���������ʏ��.�i�e�����j����ID)���R�[������B
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) l_request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) l_request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginSwapMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //�R�j�@@���������C���X�^���X�ɁA�����̃��N�G�X�g�f�[�^�A
        //�@@�@@�y�ю擾�����e�����̒����P�ʃI�u�W�F�N�g���Z�b�g����B
        l_adapter.request = l_request;
        l_adapter.parentOrderUnit = l_orderUnit;
        
        //�S�j�@@�C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);   
        return l_adapter;
    }
    
    /**
     * (get����)<BR>
     * �����I�u�W�F�N�g��ԋp����B<BR>
     * �i�p�����N���X�̓������\�b�h�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@this.is���Δ���() == true�̏ꍇ�A<BR>
     * �@@�A�������}�l�[�W��Impl.create����()���R�[�����A<BR>
     * �@@�߂�l��ԋp����B<BR>
     * <BR>
     * �@@[create����()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@this.�e�����̒����P��<BR>
     * �@@�@@<BR>
     * �Q�j�@@�����c�ɑ΂��錻�����n�i�P�j�ȊO�j�̏ꍇ�A<BR>
     * �@@super.get����()���R�[�����A�߂�l��ԋp����B<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 4344DD1D01C0
     */
    public WEB3EquityContract getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract() ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityContract l_contract = null;
        
        //�P�j�@@this.is���Δ���() == true�̏ꍇ�A
        //�@@�A�������}�l�[�W��Impl.create����()���R�[�����A
        //�@@�߂�l��ԋp����B
        if (this.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_orderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            l_contract = l_orderManager.createContract(this.parentOrderUnit);
        }
        //�Q�j�@@�����c�ɑ΂��錻�����n�i�P�j�ȊO�j�̏ꍇ�A
        //�@@super.get����()���R�[�����A�߂�l��ԋp����B
        else
        {
            l_contract = super.getContract();
        }
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
    
    /**
     * (is���Δ���)<BR>
     * ���Δ������ǂ������ʂ���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^.�A���������ʏ��.�A����������敪=="�������n�i�O�񒍕��j"�̏ꍇ�́A<BR>
     * true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4344DD31000B
     */
    public boolean isReversingOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isReversingOrder() ";
        log.entering(STR_METHOD_NAME);

        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (super.request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) super.request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (super.request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) super.request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginSwapMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }
        
        if (WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(
            l_succCommonInfo.succTradingType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
