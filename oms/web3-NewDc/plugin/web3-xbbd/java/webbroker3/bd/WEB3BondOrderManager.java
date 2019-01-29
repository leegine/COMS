head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���������}�l�[�W��(WEB3BondOrderManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����(���u) �V�K�쐬
                      : 2006/09/30 �����F (���u) ���f�� 095,102
                      : 2006/10/08 �����F (���u) ���f�� 111,115
                      : 2006/10/10 �����F (���u) ���f�� 123
                      : 2006/10/10 ꎉ�   (���u) ���f�� 093
                      : 2006/10/10 �����F (���u) ���f�� 129,130
Revesion History : 2007/7/25 ���g (���u) �d�l�ύX�E���f��No.219,230,235,245
Revesion History : 2007/7/27 ���g (���u) �d�l�ύX�E���f��No.249
Revesion History : 2007/8/07 ���g (���u) �d�l�ύX�E���f��No.250
Revesion History : 2007/8/16 �����q (���u) �d�l�ύX�E���f��No.252
*/

package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.bd.data.BondOrderAcceptActionRow;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AutoExecDivDef;
import webbroker3.common.define.WEB3BondAssetCheckDef;
import webbroker3.common.define.WEB3BondBranchRecruitLimitBranchCodeDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BondOrderLockUseDivDef;
import webbroker3.common.define.WEB3BondOrderSettleDivDef;
import webbroker3.common.define.WEB3HostSendDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondOrderManagerImpl;



/**
 * (�g���������}�l�[�W��)<BR>
 * �g���������}�l�[�W���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */
public class WEB3BondOrderManager extends BondOrderManagerImpl
{   
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
    	WEB3LogUtility.getInstance(WEB3BondOrderManager.class);
    /**
     * @@roseuid 44E33621033C
     */
    public WEB3BondOrderManager() 
    {
     
    }
    
    /**
     * (validate����\���)<BR>
     * ������\�ł��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j����.�������P��.get�������敪 == ����ς̏ꍇ�A<BR>
     * �@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00675<BR>
     * <BR>
     * �Q�j����.�������P��.get�������敪() == ����<BR>
     * �@@�@@���@@<BR>
     * �@@�@@�������P��.get��n��() < ������ԊǗ�.get������()�̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02534<BR>
     * <BR>
     * �R�j�����X�ʏ����𐶐�����B<BR>
     * �@@[����]<BR>
     * �@@�@@�@@���XID�F�������P��.get���XID'<BR>
     * <BR>
     * �@@�R�|�P�j�����X�ʏ���.get�������b�N�g�p�敪����'�������b�N�敪���g�p����'�̏ꍇ�A<BR>
     * �@@�@@�@@�@@����.�������P��.get�������敪���������<BR>
     * �@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@����.�������P��.get�������b�N�敪�������b�N���̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02535<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44B745ED033B
     */
    public void validateCancelPossibleStatus(BondOrderUnit l_bondOrderUnit) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateCancelPossibleStatus(BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j����.�������P��.get�������敪 == ����ς̏ꍇ�A 
        //��O���X���[����B        
        BondOrderUnitRow l_bondOrderUnitRow = 
        	(BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Date l_datOrderUnitDeliveryDate = l_bondOrderUnitRow.getDeliveryDate();
        
        if (WEB3BondOrderExecStatusDef.CANCELED.equals(l_bondOrderUnitRow.getOrderExecStatus()))
        {
            log.debug("�w��̒��������Ɏ���ς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00675,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��̒��������Ɏ���ς݂ł��B");
        }      
        else if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()) 
    		&&  WEB3DateUtility.compareToDay(l_datOrderUnitDeliveryDate, l_datBizDate) < 0 )
        {
    	    //�Q�j����.�������P��.get�������敪() == ���� 
    	    // ���@@ 
    		//�������P��.get��n��() < ������ԊǗ�.get������()�̏ꍇ�A 
    		//��O���X���[����B        	
            log.debug("������\���Ԃ��߂��܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02534,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "������\���Ԃ��߂��܂����B");
        }

        //�R�j�����X�ʏ����𐶐�����B 
        //[����] 
        //�@@���XID�F�������P��.get���XID
        BondBranchConditionRow l_bondBranchConditionRow = null;
    	try
    	{
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_bondOrderUnit.getBranchId());
    	}
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
		//�R�|�P�j�����X�ʏ���.get�������b�N�g�p�敪����'�������b�N�敪���g�p����'�̏ꍇ�A 
		//����.�������P��.get�������敪��������� 
		//���� 
		//����.�������P��.get�������b�N�敪�������b�N���̏ꍇ�A 
		//��O���X���[����B 
        if (l_bondBranchConditionRow != null)
        {
        	if (WEB3BondOrderLockUseDivDef.EXCEPT.equals(
                l_bondBranchConditionRow.getOrderLockUseDiv()))
        	{
        		if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnitRow.getOrderExecStatus()) 
        			&& WEB3LockStatusDef.LOCKING.equals(l_bondOrderUnitRow.getLockStatus()))
        		{
	                log.debug("�������b�N���ł��B�������b�N�������Ă��瑀�삵�Ă��������B");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_02535,
	                    this.getClass().getName() + "." + STR_METHOD_NAME,
	                    "�������b�N���ł��B�������b�N�������Ă��瑀�삵�Ă��������B");
        		}
        	}
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �iget�������P�ʃ��X�g�j<BR>
     * �C�ӏ������w�肵�āA�������P�ʃe�[�u�����������A<BR>
     * �������ʂ��g�������P�ʂ̃��X�g�ɕϊ����ĕԂ��B<BR>
     * <BR>
     * [�p�����[�^]<BR>
     * ����������: ����������WHERE������<BR>
     * �����f�[�^�R���e�i:  ���������̐ݒ�l<BR>
     * @@param l_strQueryString - (����������)<BR>
     * DAO�ɐݒ肷�錟��������WHERE������<BR>
     * @@param l_objQueryDataContainers - (�����f�[�^�R���e�i)<BR>
     * ���������̒l�̔z��<BR>
     * @@return List
     * @@throws WEB3BaseException 
     * @@roseuid 44BC441300BF
     */
    public List getBondOrderUnitList(String l_strQueryString, Object[] l_objQueryDataContainers) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBondOrderUnitList(String, Object[])";
        log.entering(STR_METHOD_NAME);
        List l_lisQuerys = null;
        
        //�C�ӏ������w�肵�āA�������P�ʃe�[�u�����������A
        //�������ʂ��g�������P�ʂ̃��X�g�ɕϊ����ĕԂ��B 
        try
        {
    		QueryProcessor l_qp = Processors.getDefaultProcessor();
    		l_lisQuerys = l_qp.doFindAllQuery(
				BondOrderUnitRow.TYPE, 
				l_strQueryString,
				null,
				l_objQueryDataContainers);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisQuerys;
    }
    
    /**
     * (validate�i����/���t�j����)<BR>
     * validate����/���t����<BR>
     * <BR>
     * �V�[�P���X�}�uvalidate�i����/���t�j�����v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_strDealDiv - (����敪)<BR>
     * ����敪<BR>
     * @@param l_strSettlementDiv - (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44C470ED0324
     */
    public void validateRecruitOrBuyOrder(SubAccount l_subAccount, 
		WEB3BondProduct l_bondProduct, String l_strDealDiv, 
		String l_strSettlementDiv, double l_dblOrderQuantity) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateRecruitOrBuyOrder(SubAccount, WEB3BondProduct, String, String, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //1.1.validate�O���،������J��(SubAccount, ������)
		//�ڋq���O���،��������J�݂��Ă��邩�`�F�b�N����B 
		//[validate�O���،������J��()�ɓn������] 
		//�@@�⏕�����F�@@����.�⏕���� 
		//�@@�������F�@@����.������
        this.validateFeqAccountOpen(l_subAccount, l_bondProduct);
        
        //1.2.validate�ڋq�戵�\����(������, String)
        //�ڋq�戵�\�����`�F�b�N�A����\�`�F�b�N���s�Ȃ��B 
		//[validate�ڋq�戵�\����()�ɓn������] 
		//�@@�������F�@@����.������ 
		//�@@����敪�F�@@����.����敪
        this.validateAccountHandlingPossibleProduct(l_bondProduct, l_strDealDiv);
        
        //1.3.validate����(double, ������)
		//�z�ʋ��z�`�F�b�N���s�Ȃ��B 
		//[validate����()�ɓn������] 
		//�@@���ʁF�@@����.�������� 
		//�@@�������F�@@����.������
        this.validateQuantity(l_dblOrderQuantity, l_bondProduct);
        
        //1.4.validate���ϋ敪(String, ������)
		//���ϋ敪���`�F�b�N����B 
		//[validate���ϋ敪()�ɓn������] 
		//�@@���ϋ敪�F�@@����.���ϋ敪 
		//�@@�������F�@@����.������
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        l_validationsCheck.validateSettlementDiv(l_strSettlementDiv, l_bondProduct);

        //1.5.get�������敪( )
        //�����̎������敪���擾����B
        String l_strAutoExecDiv = l_bondProduct.getAutoExecDiv();
        
        //1.6.�����򏈗���get�������敪()�̖߂�l == "�������"�̏ꍇ
        if (WEB3AutoExecDivDef.AUTO_EXECUTE.equals(l_strAutoExecDiv))
        {
        	//1.6.1.validate�������g(�،����, ������, String)
			//�������ʂ��������g���ł��邩�ǂ����`�F�b�N����B 
			//[validate�������g()�ɓn������] 
			//�@@�،���ЁF�@@����.�⏕����.getInstitution()�̖߂�l 
			//�@@�������F�@@����.������ 
			//�@@�������ʁF�@@����.��������
        	this.validateAutoExecLimit(
    			l_subAccount.getInstitution(),
    			l_bondProduct, 
    			WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity));
        }
              
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������g)<BR>
     * �������ʂ��������g�͈͓̔��ł��邩�`�F�b�N���s���B<BR> 
     * <BR>
     * �i* �������R���ʃ`�F�b�N.validate�������g()�ɈϏ�����B�j <BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_strOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CDA7B10161
     */
    public void validateAutoExecLimit(Institution l_institution,
		WEB3BondProduct l_bondProduct, String l_strOrderQuantity) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateAutoExecLimit(Institution, WEB3BondProduct, String)";
        log.entering(STR_METHOD_NAME);
        
        //�i* �������R���ʃ`�F�b�N.validate�������g()�ɈϏ�����B�j
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck =
        	new WEB3BondOrderManagerReusableValidationsCheck();
        l_validationsCheck.validateAutoExecLimit(l_institution, l_bondProduct, l_strOrderQuantity);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update���������b�N�敪)<BR>
     * �������P�ʃe�[�u���́u�������b�N�敪�v���X�V����B<BR>
     * <BR>
     * �P�j�������P�ʂɊY������DB���R�[�h�ɑ΂��āA�ȉ��̗���X�V����B<BR>
     * �@@�������b�N�敪������.�������b�N�敪<BR>
     * �@@�X�V���t�@@�@@�@@�����ݓ���<BR>
     * @@param l_strOrderLockDiv - (�������b�N�敪)<BR>
     * �������b�N�敪<BR>
     * @@param l_bondOrderUnit - (�g���������P��)<BR>
     * �g���������P��<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44C56C7902D1
     */
	public void updateBondOrderLockStatus(String l_strOrderLockDiv,
		WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
        	" updateBondOrderLockStatus(String, WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);

        //�P�j�������P�ʂɊY������DB���R�[�h�ɑ΂��āA�ȉ��̗���X�V����B
        try
        {
        	BondOrderUnitRow l_orderUnitRow = 
        		BondOrderUnitDao.findRowByOrderUnitId(l_bondOrderUnit.getOrderUnitId());
        	BondOrderUnitParams l_orderUnitParams = 
        		new BondOrderUnitParams(l_orderUnitRow);
        	
        	//�������b�N�敪������.�������b�N�敪
        	l_orderUnitParams.setLockStatus(l_strOrderLockDiv);
        	//�X�V���t�@@�@@�@@�����ݓ���
        	l_orderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        	
        	QueryProcessor l_qp = Processors.getDefaultProcessor();
        	l_qp.doUpdateQuery(l_orderUnitParams);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�������P��By����ID)<BR>
     * �����̒���ID�ɊY������g���������P�ʂ�ԋp����<BR>
     * <BR>
     * �P�j�@@�����I�u�W�F�N�g�擾 <BR>
     * �@@getOrder(�����h�c)�ɂĒ����I�u�W�F�N�g���擾����B <BR>
     * �@@�����h�c�ɊY�����钍���P�ʂ����݂��Ȃ��ꍇ�͗�O���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02011<BR>
     * <BR>
     * �Q�j�@@�����P�ʎ擾 <BR>
     * �@@����.getOrderUnits()�ɂĒ����P�ʂ̔z����擾����B <BR>
     * �@@�����h�c�ɊY�����钍���P�ʂ��Q���ȏ゠�����ꍇ<BR>
     * �i�z��̗v�f�����P�łȂ��ꍇ�j�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02012<BR>
     * <BR>
     * �@@�擾���������P�ʂ�ԋp����B <BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@return webbroker3.bd.WEB3BondOrderUnit
     * @@throws WEB3BaseException 
     * @@roseuid 44C6CBBA019F
     */
    public WEB3BondOrderUnit getBondOrderUnitByOrderId(
		long l_lngOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getBondOrderUnitByOrderId(long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����I�u�W�F�N�g�擾
        Order l_order = null;
        try
        {
        	//getOrder(�����h�c)�ɂĒ����I�u�W�F�N�g���擾����B
        	l_order = getOrder(l_lngOrderId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����h�c�ɊY�����钍���P�ʂ����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02011,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�Q�j�@@�����P�ʎ擾 
        //����.getOrderUnits()�ɂĒ����P�ʂ̔z����擾����B
        OrderUnit[] l_orderUnits = l_order.getOrderUnits();
        if (l_orderUnits == null || l_orderUnits.length != 1)
        {
            log.debug("�����h�c�ɊY�����钍���P�ʂ��Q���ȏ゠�����̂ŁA�G���[�ɂȂ�܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02012,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����h�c�ɊY�����钍���P�ʂ��Q���ȏ゠�����̂ŁA�G���[�ɂȂ�܂��B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return (WEB3BondOrderUnit)l_orderUnits[0];
    }
    
    /**
     * (get���������ώc��)<BR>
     * ��������ŐV�̖��ώc���l���擾�B<BR>
     * <BR>
     * �P�j���v���_�N�g�}�l�[�W���[��get������()���Ăяo���A�������I�u�W�F�N�g���擾<BR>
     * �@@[����]<BR>
     * �@@�،���ЁF����.�،����<BR>
     * �@@�����R�[�h(WEB3)�F����.�����R�[�h(WEB3)<BR>
     * <BR>
     * �Q�j�������I�u�W�F�N�g.get���ώc��()�ŗ݌v�l���擾<BR>
     * <BR>
     * �R�j�������P�ʃe�[�u�����瓖�����̎������c���̍��v�l���擾<BR>
     * �R�|�P�j�N�G���v���Z�b�T���擾����B <BR>
     *�@@�@@Processors.getAccountProcessor(account_id) <BR>
     *�@@�@@[����] <BR>
     *     account_id�F0L <BR>
     *�@@�@@���Ӂj���̃A�J�E���g���܂߂Č�������̂ŁAgetDefaultProcessor�͎g�p���Ȃ��B<BR>
     * �R�|2�j�������P�ʃe�[�u�����������A�������ʍs�I�u�W�F�N�g���X�g���擾 �B<BR> 
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@����ID = �擾�����������I�u�W�F�N�g.getProductID()<BR>
     * �@@�@@�@@host���M�敪 = '�����M'<BR>
     * �@@�@@�@@�������敪 =  �@@'1�F�������'<BR>
     * �@@�@@�@@������ʁ@@= '�����t'<BR>
     * <BR>
     * �S�j�擾���������P�ʍs�I�u�W�F�N�g���X�g��蒍���̖��ώc���̍��v�l���v�Z<BR>
     * �S�|�P�j�擾�����������ʂ̍������P�ʍs�I�u�W�F�N�g���X�g�ɑ΂��A<BR>
     * ���L�̏������J��Ԃ��čs���B<BR>
     * �@@�@@�@@�����P�ʍs�I�u�W�F�N�g.get�������敪<>'�����'�̏ꍇ<BR>
     * �@@�@@�@@�ϐ�.A += �����P�ʍs�I�u�W�F�N�g.get��������()<BR>
     * �@@�@@�@@���̏ꍇ<BR>
     * �@@�@@�@@�@@�ϐ�.B += �����P�ʍs�I�u�W�F�N�g.get��萔��()<BR>
     * �S�|�Q�j�����̖��ώc���̍��v�l���v�Z<BR>
     * �@@�@@�@@�ϐ�.C = �ϐ�.A - �ϐ�.B<BR>
     * <BR>
     * �T�j�@@�@@�Q�j�̗݌v�l�@@�{�@@�S�j�̕ϐ�.C ��Ԃ��B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)<BR>
     * @@return double
     * @@throws WEB3BaseException 
     * @@roseuid 44C808B602C2
     */
    public double getBondProductExecutedBalance(
		Institution l_institution, String l_strProductCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" getBondProductExecutedBalance(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j���v���_�N�g�}�l�[�W���[��get������()���Ăяo���A�������I�u�W�F�N�g���擾
        //[����]
        //�،���ЁF����.�،����
        //�����R�[�h(WEB3)�F����.�����R�[�h(WEB3)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_productManager = 
        	(WEB3BondProductManager) l_finApp.getTradingModule(
    			ProductTypeEnum.BOND).getProductManager();
        
        WEB3BondProduct l_bondProduct;
		try
		{
			l_bondProduct = (WEB3BondProduct) l_productManager.getBondProduct(l_institution, l_strProductCode);
		}
		catch (NotFoundException l_ex)
		{
			log.error("__error in �������I�u�W�F�N�g���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
		}
        
        //�Q�j�������I�u�W�F�N�g.get���ώc��()�ŗ݌v�l���擾
        double l_dblAutoExecAmount = l_bondProduct.getAutoExecAmount();
        
        //�R�j�������P�ʃe�[�u�����瓖�����̎������c���̍��v�l���擾
        //�R�|�P�j�N�G���v���Z�b�T���擾����B 
        //�@@Processors.getAccountProcessor(account_id) 
        //�@@[����] 
        //   account_id�F0L 
        //�@@���Ӂj���̃A�J�E���g���܂߂Č�������̂ŁAgetDefaultProcessor�͎g�p���Ȃ��B
        
        QueryProcessor l_qp = null;
        try
        {
            l_qp = Processors.getAccountProcessor(0L);
        } 
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        //�R�|2 �j�������P�ʃe�[�u�����������A�������ʍs�I�u�W�F�N�g���X�g���擾 �B
        //   �m���������n
        //   ����ID = �擾�����������I�u�W�F�N�g.getProductID()
        //   host���M�敪 = '�����M'
        //   �������敪 =  �@@'1�F�������'
        //   ������ʁ@@= '�����t'
        StringBuffer l_sbQuery = new StringBuffer();
        l_sbQuery.append(" product_id = ?");
        l_sbQuery.append(" and host_send_div = ?");
        l_sbQuery.append(" and auto_exec_div = ?");
        l_sbQuery.append(" and order_type = ? ");

        List l_lisResult = null;
        Object[] l_objQuerys = 
        	new Object[]{
        		new Long(l_bondProduct.getProductId()),
        		WEB3HostSendDivDef.UNSEND,
        		WEB3AutoExecDivDef.AUTO_EXECUTE,
        		new Long(OrderTypeEnum.BOND_BUY.intValue())};
        try
        {
        	l_lisResult = l_qp.doFindAllQuery(
    			BondOrderUnitRow.TYPE,
    			l_sbQuery.toString(),
    			null,
    			l_objQuerys);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        double l_dblA = 0.0D;
        double l_dblB = 0.0D;
        double l_dblTotal = 0.0D;
        //�S�j�擾���������P�ʍs�I�u�W�F�N�g���X�g��蒍���̖��ώc���̍��v�l���v�Z 
        if (l_lisResult != null && !l_lisResult.isEmpty())
        {
	        for (int i = 0; i < l_lisResult.size(); i++)
	        {
	        	//�S�|�P�j�擾�����������ʂ̍������P�ʍs�I�u�W�F�N�g���X�g�ɑ΂��A
	        	BondOrderUnitRow l_orderUnitRow = (BondOrderUnitRow) l_lisResult.get(i);
	        	WEB3BondOrderUnit l_orderUnit = new WEB3BondOrderUnit(l_orderUnitRow);
				//�����P�ʍs�I�u�W�F�N�g.get�������敪<>'�����'�̏ꍇ 
	        	if (!WEB3BondOrderExecStatusDef.CANCELED.equals(l_orderUnit.getOrderExecStatus()))
	        	{
	        		//���L�̏������J��Ԃ��čs���B�ϐ�.A += �����P�ʍs�I�u�W�F�N�g.get��������() 
	        		l_dblA += l_orderUnitRow.getQuantity();
	        	}
	        	else
	        	{
	    			//���̏ꍇ 
	    			// �ϐ�.B += �����P�ʍs�I�u�W�F�N�g.get��萔��() 
	        		l_dblB += l_orderUnitRow.getExecutedQuantity();
	        	}
	        }
	        
			//�S�|�Q�j�����̖��ώc���̍��v�l���v�Z 
			//�@@�@@�ϐ�.C = �ϐ�.A - �ϐ�.B 
	        l_dblTotal = l_dblA - l_dblB;
        }
        
        //�T�j�@@�@@�Q�j�̗݌v�l�@@�{�@@�S�j�̕ϐ�.C ��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_dblAutoExecAmount + l_dblTotal;
    }
    
    /**
     * (validate���V�K����)<BR>
     * validate���V�K����<BR>
     * <BR>
     * �V�[�P���X�}�uvalidate���V�K�����v���Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_bondNewOrderSpec - (�g�����V�K�������e)<BR>
     * �g�����V�K�������e<BR>
     * @@param l_bondExecutDateInfo - (���������)<BR>
     * ���������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CB1E6B0393
     */
    public void validateBondNewOrder(SubAccount l_subAccount, 
		WEB3BondNewOrderSpec l_bondNewOrderSpec, 
		WEB3BondExecuteDateInfo l_bondExecutDateInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateBondNewOrder(SubAccount, WEB3BondNewOrderSpec, " 
        	+ "WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_bondNewOrderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //1.1.getInstance( )
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        
        //1.2.validate����\�ڋq(SubAccount)
		//����\�ڋq�ł��邩�`�F�b�N���� 
		//[validate����\�ڋq()�̈���] 
		//�⏕�����F����.�⏕����
        WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
        
        OrderValidationResult l_validationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }
        
        //1.3.getQuantity( )
        double l_dblQuantity = l_bondNewOrderSpec.getQuantity();
        
        //1.4.getInstitution( )
        Institution l_institution = l_subAccount.getInstitution();
        
        //1.5.getProductCode( )
        String l_strProductCode = l_bondNewOrderSpec.getProductCode();
        
        //1.6.get������(Institution, String)
        //�������I�u�W�F�N�g���擾 
		//[get������(�،���� : Institution, �����R�[�h(WEB3) : String)�̈���] 
		//�،���ЁF�⏕����.getInstitution()�̖߂�l 
		//�����R�[�h(WEB3)�F�擾���������R�[�h(WEB3) 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_productManager = 
        	(WEB3BondProductManager) l_finApp.getTradingModule(
    			ProductTypeEnum.BOND).getProductManager();
        WEB3BondProduct l_bondProduct = null;
        try
        {
	        l_bondProduct = 
	        	(WEB3BondProduct) l_productManager.getBondProduct(l_institution, l_strProductCode);
        }
        catch(NotFoundException l_ex)
        {
			log.error("__error in �������I�u�W�F�N�g���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        //1.7.validate�O���،������J��(SubAccount, ������)
		//�O���،������J�݂��`�F�b�N���� 
		//[����] 
		//�⏕�����F����.�⏕���� 
		//�������Fget������
        this.validateFeqAccountOpen(l_subAccount, l_bondProduct);
        
        //1.8.get���ϋ敪( )
        String l_strSettlementDiv = l_bondNewOrderSpec.getSettlementDiv();
        
        //1.9.validate���ϋ敪(String, ������)
		//���ϋ敪���`�F�b�N���� 
		//[����] 
		//���ϋ敪�Fget���ϋ敪 
		//�������Fget������
        l_validationsCheck.validateSettlementDiv(l_strSettlementDiv, l_bondProduct);
        
        //1.10.isSellOrder( )
        boolean l_blnSellOrder = l_bondNewOrderSpec.isSellOrder();
        
        //1.11.���p�����̏ꍇ�iisSellOrder( ) == true�j
        if (l_blnSellOrder)
        {
        	//1.11.1.get����X( )
        	Branch l_branch = l_subAccount.getMainAccount().getBranch();
        	
        	//1.11.2 �����X�ʏ���
            //[����] 
            //���XID�F get����X.get���XID
            BondBranchConditionRow l_bondBranchConditionRow = null;
        	try
        	{
                l_bondBranchConditionRow = 
                    BondBranchConditionDao.findRowByPk(l_branch.getBranchId());
        	}
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);             
            }
            
            //1.11.3get�ۗL���Y�`�F�b�N�敪
            //1.11.4 get�ۗL���Y�`�F�b�N�敪�I���@@'�`�F�b�N���Ȃ�' �̏ꍇ
            if (l_bondBranchConditionRow == null ||
        		WEB3BondAssetCheckDef.DEFAULT.equals(l_bondBranchConditionRow.getAssetCheckDiv()))
            {
            	//1.11.4.1validate���p�\����(�⏕����, �g�����V�K�������e, ������)
				//���p�\���ʂł��邩�`�F�b�N 
				//[validate���p�\����()�̈���] 
				//�⏕�����F����.�⏕���� 
				//�g�����V�K�������e�F����.�g�����V�K�������e 
				//�������Fget������
            	l_validationsCheck.validateTransferedPossibleQuantity(
        			l_subAccount, l_bondNewOrderSpec, l_bondProduct);
            }
        }
        else
        {
        	//1.12.���t�����̏ꍇ�iisSellOrder( ) == false�j
        	//1.12.1get�������敪( )   	
        	//1.12.2.get�������敪�����������̏ꍇ
        	if (WEB3AutoExecDivDef.AUTO_EXECUTE.equals(l_bondProduct.getAutoExecDiv()))
        	{
        		//1.12.2.1.validate�������g(�،����, ������, String)
				//�������g�̃`�F�b�N 
				//[validate�������g()�̈���] 
				//�،���ЁFgetInstitution 
				//�������Fget������ 
				//�������ʁFgetQuantity
        		l_validationsCheck.validateAutoExecLimit(
    				l_institution, l_bondProduct, WEB3StringTypeUtility.formatNumber(l_dblQuantity));
        	}
        }
		log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���\���)<BR>
     * validate���\���<BR>
     * <BR>
     * �P�j����.�������P��.get�������敪��������ς̏ꍇ�A<BR>
     * �@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00675<BR>
     * <BR>
     * �Q�j����.�������P��.get�������敪��������<BR>
     * �@@�@@����<BR>
     * �@@����.�������P��.get��������������ԊǗ�.get�������̏ꍇ�A<BR>
     * �@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02632<BR>
     * <BR>
     * �R�j�����X�ʏ����𐶐�����B<BR>
     * �@@ [����] <BR>
     * �@@�@@�@@���XID�F�������P��.get���XID <BR>
     * �@@�R�|�P�j�����X�ʏ���.get�������b�N�g�p�敪����'�������b�N�敪���g�p����'�̏ꍇ�A<BR>
     * �@@�@@�@@�@@����.�������P��.get�������敪���������<BR>
     * �@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@����.�������P��.get�������b�N�敪�������b�N�������̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02633<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CB430A010D
     */
    public void validateExecutePossibleStatus(
		BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateExecutePossibleStatus(BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        BondOrderUnitRow l_orderUnitRow = 
        	(BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
        //�P�j����.�������P��.get�������敪��������ς̏ꍇ�A 
        // ��O���X���[����B 
        WEB3BondOrderUnit l_orderUnit = new WEB3BondOrderUnit(l_orderUnitRow);
    	if (WEB3BondOrderExecStatusDef.CANCELED.equals(l_orderUnit.getOrderExecStatus()) )
        {
            log.debug("�w��̒��������Ɏ���ς݂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00675,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��̒��������Ɏ���ς݂ł��B");
        }
        
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Date l_datOrderUnitBizDate =
        	WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
        
		//�Q�j����.�������P��.get�������敪�������� 
		// ���� 
		//����.�������P��.get��������������ԊǗ�.get�������̏ꍇ�A 
		//��O���X���[����B
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus())
    		&& WEB3DateUtility.compareToDay(l_datOrderUnitBizDate, l_datBizDate) < 0)
        {
            log.debug("���ύX�\���Ԃ��߂��܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02632,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ύX�\���Ԃ��߂��܂����B");
        }
        
        //�R�j�����X�ʏ����𐶐�����B 
        //[����] 
        //�@@���XID�F�������P��.get���XID
        BondBranchConditionRow l_bondBranchConditionRow = null;
    	try
    	{
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_bondOrderUnit.getBranchId());
    	}
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
		//�R�|�P�j�����X�ʏ���.get�������b�N�g�p�敪����'�������b�N�敪���g�p����'�̏ꍇ�A
		//����.�������P��.get�������敪��������� 
		//���� 
		//����.�������P��.get�������b�N�敪�������b�N�������̏ꍇ�A 
		//��O���X���[����B 
        if (l_bondBranchConditionRow != null)
        {
        	if (WEB3BondOrderLockUseDivDef.EXCEPT.equals(l_bondBranchConditionRow.getOrderLockUseDiv())
    			&& WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus())
    			&& WEB3LockStatusDef.RELEASING.equals(l_orderUnit.getLockStatus()))
        	{
                log.debug("�������b�N�������ł��B�������b�N���Ă��瑀�삵�Ă��������B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02633,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������b�N�������ł��B�������b�N���Ă��瑀�삵�Ă��������B");
        	}
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate����)<BR>
     * ���ʃ`�F�b�N���s���B <BR>
     * <BR>
     * �i* �������R���ʃ`�F�b�N.validate����()�ɈϏ�����B�j <BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2B14602DF
     */
    public void validateQuantity(double l_dblOrderQuantity, WEB3BondProduct l_bondProduct) 
    	throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateQuantity(double, WEB3BondProduct) " ;
        log.entering(STR_METHOD_NAME);
        
        //�i* �������R���ʃ`�F�b�N.validate����()�ɈϏ�����B�j
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        
        l_validationsCheck.validateQuantity(l_dblOrderQuantity, l_bondProduct);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create���������)<BR>
     * �����������쐬����B<BR>
     * <BR>
     * �P�j�������擾����B<BR>
     * �@@�@@[����.������.get����()�ɓn������]<BR>
     * �@@�@@�@@�������F�@@����.������<BR>
     * <BR>
     * �Q�j��n�����擾����B<BR>
     * �@@�@@[����.������.get��n��()�ɓn������]<BR>
     * �@@�@@�@@�����F�@@�擾��������<BR>
     * �@@�@@�@@��������ʔ���F�@@����.��������ʔ���<BR>
     * <BR>
     *�R�j���������擾����B  <BR>
     *�@@�@@[����.������.get������()�ɓn������]  <BR>
     *�@@�@@�@@�����F�@@�擾��������  <BR>
     *�@@�@@�@@��������ʔ���F�@@����.��������ʔ���  <BR>
     *�@@�@@�@@���ϋ敪�F�@@����.���ϋ敪  <BR>
     *�@@�@@�@@���X�F�@@����.���X <BR>
     *<BR>
     * �S�j����.������.is�O����()�̖߂�l == true �̏ꍇ<BR>
     * <BR>
     * �@@�@@�S�|�P�j���n���������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@[����.������.get���n������()�ɓn������]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������F�@@����.������<BR>
     * <BR>
     * �@@�@@�S�|�Q�j���n�������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@[����.������.get���n����()�ɓn������]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���n�������F�@@�擾�������n������<BR>
     * <BR>
     * �@@�@@�S�|�R�j���n��n�����擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@[����.������.get���n��n��()�ɓn������]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���n�����F�@@�擾�������n����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���F�@@����.��������ʔ���<BR>
     * <BR>
     * �S�j���������𐶐����A<BR>
     * �擾�������t���ƈ����̔����������ꂼ��Z�b�g���ĕԋp����B<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϋ敪
     * @@param l_branch - (���X)<BR>
     * ���X
     * @@return WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException 
     * @@roseuid 44D2B33102F4
     */
    public WEB3BondExecuteDateInfo createBondExecutionDateInfo(Date l_datBizDate, 
		WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
		WEB3BondProduct l_bondProduct, String l_strSettleDiv, Branch l_branch) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" createBondExecutionDateInfo(Date, WEB3BondOrderTypeJudge, WEB3BondProduct, String, Branch) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();
        
		//�P�j�������擾����B 
		//[����.������.get����()�ɓn������] 
		//�������F�@@����.������ 
        Date l_datExecDate = l_bondProduct.getExecDate(l_datBizDate);
        l_bondExecuteDateInfo.setExecuteDate(l_datExecDate);
        
		//�Q�j��n�����擾����B 
		//[����.������.get��n��()�ɓn������] 
		//�����F�@@�擾�������� 
		//��������ʔ���F�@@����.��������ʔ��� 
        Date l_datDeliveryDate = 
        	l_bondProduct.getDeliveryDate(l_datExecDate, l_bondOrderTypeJudge);
        l_bondExecuteDateInfo.setDeliveryDate(l_datDeliveryDate);
        
        //�R�j���������擾����B 
        // [����.������.get������()�ɓn������] 
        //  �����F�@@�擾�������� 
        //  ��������ʔ���F�@@����.��������ʔ��� 
        //  ���ϋ敪�F�@@����.���ϋ敪 
        //  ���X�F�@@����.���X 
        Date l_datPaymentDate = 
            l_bondProduct.getPaymentDate(
                l_datExecDate, l_bondOrderTypeJudge, l_strSettleDiv, l_branch);
        l_bondExecuteDateInfo.setPaymentDate(l_datPaymentDate);
        
		//�S�j����.������.is�O����()�̖߂�l == true �̏ꍇ 
        if (l_bondProduct.isForeignBond())
        {
			//�S�|�P�j���n���������擾����B 
			//[����.������.get���n������()�ɓn������] 
			//�������F�@@����.������ 
        	Date l_datForeignBizDate = l_bondProduct.getForeignBizDate(l_datBizDate);
        	l_bondExecuteDateInfo.setForeignBizDate(l_datForeignBizDate);
        	
			//�S�|�Q�j���n�������擾����B 
			//[����.������.get���n����()�ɓn������] 
			//���n�������F�@@�擾�������n������ 
        	Date l_datForeignExecDate = 
        		l_bondProduct.getForeignExecDate(l_datForeignBizDate);
        	l_bondExecuteDateInfo.setForeignExecuteDate(l_datForeignExecDate);
        	
			//�S�|�R�j���n��n�����擾����B 
			//[����.������.get���n��n��()�ɓn������] 
			//���n�����F�@@�擾�������n���� 
			//��������ʔ���F�@@����.��������ʔ��� 
        	Date LdatForeignDeliveryDate = 
        		l_bondProduct.getForeignDeliveryDate(
    				l_datForeignExecDate, l_bondOrderTypeJudge);
        	l_bondExecuteDateInfo.setForeignDeliveryDate(LdatForeignDeliveryDate);
        }
        
		//�T�j���������𐶐����A�擾����
        //���t���ƈ����̔����������ꂼ��Z�b�g���ĕԋp����B
        l_bondExecuteDateInfo.setBizDate(l_datBizDate);
        
        log.exiting(STR_METHOD_NAME);
    	return l_bondExecuteDateInfo;
    }
    
    /**
     * (validate�O���،������J��)<BR>
     * �ڋq���O���،��������J�݂��Ă��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j����.������.is�O����()�̖߂�l == true �̏ꍇ�A�ȉ��̏������s�Ȃ��B<BR>
     * <BR>
     * �@@�P�|�P�j����.�⏕����.getMainAccount()���R�[�����āA�ڋq���擾����B<BR>
     * <BR>
     * �@@�P�|�Q�j�ڋq.is�O���،������J��()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�u�O���،��������J�݃G���[�v<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_01341<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2DFC7030C
     */
    public void validateFeqAccountOpen(
		SubAccount l_subAccount, WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateFeqAccountOpen(SubAccount, l_subAccount, WEB3BondProduct) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j����.������.is�O����()�̖߂�l == true �̏ꍇ�A�ȉ��̏������s�Ȃ��B
        if (l_bondProduct.isForeignBond())
        {
    		//�P�|�P�j����.�⏕����.getMainAccount()���R�[�����āA�ڋq���擾����B
        	WEB3GentradeMainAccount l_mainAccount = 
        		(WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        	
        	//�P�|�Q�j�ڋq.is�O���،������J��()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        	//�u�O���،��������J�݃G���[�v
        	if (!l_mainAccount.isForeignAccountOpen())
        	{
                log.debug("���Y�ڋq�͊O���،������J�݂Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���Y�ڋq�͊O���،������J�݂Ȃ��B");
        	}
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������b�N�敪�X�V�\���)<BR>
     * �������b�N�敪���X�V�\�ł��邩�`�F�b�N����<BR>
     * <BR>
     * �P�j�������敪�𔻒�<BR>
     * �@@�@@�P�|�P�j�g���������P�ʂ��璍�����敪���擾����B<BR>
     * �@@�@@�P�|�Q�j�������敪�I�������̏ꍇ�A<BR>
     * �@@�@@�G���[�u�Ώے����͊��ɖ����ł͂���܂���B�v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02619<BR>
     * <BR>
     *�Q�j���������b�N�g�p�敪���`�F�b�N <BR>
     *�@@�@@�Q�|�P�j�����X�ʏ����𐶐�����B <BR>
     *     �@@�@@�@@�@@[����] <BR>
     *    �@@�@@ �@@�@@���XID�F�g���������P��.get���XID <BR>
     *�@@�@@�Q�|�Q�j�����X�ʏ���.get�������b�N�g�p�敪�I��'�������b�N�敪���g�p����'�̏ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�G���[�u�������b�N�敪���g�p���镔�X�ł͂���܂���B�v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02620<BR>
     * @@param l_bondOrderUnit - (�g���������P��)<BR>
     * �g���������P��<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D4778E0157
     */
    public void validateOrderLockDivUpdatePossibleStatus(
		WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
    	final String STR_METHOD_NAME =
        	" validateOrderLockDivUpdatePossibleStatus(WEB3BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�������敪�𔻒�
        //�P�|�P�j�g���������P�ʂ��璍�����敪���擾����B
        //�P�|�Q�j�������敪�I�������̏ꍇ�A
        //�G���[�u�Ώے����͊��ɖ����ł͂���܂���B�v���X���[����B
        if (!WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()))
        {
            log.debug("�Ώے����͊��ɖ����ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02619,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ώے����͊��ɖ����ł͂���܂���B");
        }
        
        //�Q�j���������b�N�g�p�敪���`�F�b�N 
        //�@@�Q�|�P�j�����X�ʏ����𐶐�����B 
        //   �@@�@@�@@�@@[����] 
        //  �@@�@@ �@@�@@���XID�F�g���������P��.get���XID
        BondBranchConditionRow l_bondBranchConditionRow = null;
    	try
    	{
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_bondOrderUnit.getBranchId());
    	}
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        
        //�Q�|�Q�j�����X�ʏ���.get�������b�N�g�p�敪�I��'�������b�N�敪���g�p����'�̏ꍇ�A
        //�G���[�u�������b�N�敪���g�p���镔�X�ł͂���܂���B�v���X���[����B
        if (l_bondBranchConditionRow == null || 
    		WEB3BondOrderLockUseDivDef.DEFAULT.equals(l_bondBranchConditionRow.getOrderLockUseDiv()))
        {
        	log.debug("�������b�N�敪���g�p���镔�X�ł͂���܂���B");
        	log.exiting(STR_METHOD_NAME);
        	throw new WEB3BusinessLayerException(
    			WEB3ErrorCatalog.BUSINESS_ERROR_02620,
    			this.getClass().getName() + "." + STR_METHOD_NAME,
        	"�������b�N�敪���g�p���镔�X�ł͂���܂���B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�������P��)<BR>
     * get�������P��<BR>
     * <BR>
     * getOrderUnit(long)�̃I�[�o�[���C�h���\�b�h<BR>
     * <BR>
     * �P�j�@@����.�����P��ID�����Ƃɍ������P��Row�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@this.toOrderUnit()�̖߂�l��Ԃ��B <BR>
     * �@@[toOrderUnit()�Ɏw�肷�����] <BR>
     * �@@�@@�擾�����������P��Row�I�u�W�F�N�g<BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �����P��ID<BR>
     * @@roseuid 44D485A202D0
     */
    public OrderUnit getOrderUnit(long l_lngOrderUnitId)
    {
        final String STR_METHOD_NAME = " getBondOrderUnit(long) " ;
        log.entering(STR_METHOD_NAME);
        
        try
		{
        	//getOrderUnit(long)�̃I�[�o�[���C�h���\�b�h
        	//�P�j�@@����.�����P��ID�����Ƃɍ������P��Row�I�u�W�F�N�g���擾����B
        	BondOrderUnit l_orderUnit = (BondOrderUnit) super.getOrderUnit(l_lngOrderUnitId);
        	BondOrderUnitRow l_orderUnitRow = 
        		(BondOrderUnitRow)l_orderUnit.getDataSourceObject();
        	
        	//�Q�j�@@this.toOrderUnit()�̖߂�l��Ԃ��B
        	//[toOrderUnit()�Ɏw�肷�����] 
        	//�擾�����������P��Row�I�u�W�F�N�g
            log.exiting(STR_METHOD_NAME);
        	return toOrderUnit(l_orderUnitRow);
		}
        catch (NotFoundException l_ex)
        {
            log.error("__error in �������P��Row�I�u�W�F�N�g���擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (to�������P��)<BR>
     * to�������P��<BR>
     * <BR>
     * toOrderUnit(Row)�̃I�[�o�[���C�h<BR>
     * <BR>
     * ����.�s�I�u�W�F�N�g�����ƂɊg���������P�ʃI�u�W�F�N�g�𐶐����A�ԋp����B<BR>
     * @@param l_row - (�s�I�u�W�F�N�g)<BR>
     * �s�I�u�W�F�N�g<BR>
     * @@return OrderUnit
     * @@roseuid 44D485B302B1
     */
    public OrderUnit toOrderUnit(Row l_row) 
    {
        final String STR_METHOD_NAME = " toOrderUnit(Row) " ;
        log.entering(STR_METHOD_NAME);
        
        //toOrderUnit(Row)�̃I�[�o�[���C�h
        //����.�s�I�u�W�F�N�g�����ƂɊg���������P�ʃI�u�W�F�N�g�𐶐����A�ԋp����B
        WEB3BondOrderUnit l_bondOrderUnit = new WEB3BondOrderUnit((BondOrderUnitRow)l_row);
        
        log.exiting(STR_METHOD_NAME);
        return l_bondOrderUnit;
    }
    
    /**
     * (validate��������\���)<BR>
     * ����������\���`�F�b�N���s���B<BR> 
     * <BR>
     * �i* �������R���ʃ`�F�b�N.validate��������\���()�ɈϏ�����B�j <BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D6C46403B8
     */
    public void validateOrderCancelPossibleStatus(
		WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
        	" validateOrderCancelPossibleStatus(WEB3BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        //�i* �������R���ʃ`�F�b�N.validate��������\���()�ɈϏ�����B�j
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
        	new WEB3BondOrderManagerReusableValidationsCheck();
        
        l_validationsCheck.validateTransferedPossibleDays(l_bondOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�ڋq�戵�\����)<BR>
     * �ڋq�����Ɏ戵�\�Ȗ������ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.������.is�ڋq�戵�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@��ڋq�戵�s�����G���[�B�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02661<BR>
     * <BR>
     * �Q�j����.����敪�@@==�@@����@@�̏ꍇ<BR>
     * �@@����.������.is����\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�����s�����G���[�B�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02612<BR>
     * <BR>
     * �R�j����.����敪�@@==�@@���t�@@�̏ꍇ<BR>
     * �@@����.������.is���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@����t�s�����G���[�B�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02613<BR>
     * <BR>
     * �S�j����.����敪�@@==�@@���p�@@�̏ꍇ<BR>
     * �@@����.������.is���p�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@����p�s�����G���[�B�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02614<BR>
     * �@@
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_strDealDiv - (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * 1�F�@@����<BR>
     * 2�F�@@���t<BR>
     * 3�F�@@���p<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D843A00160
     */
    public void validateAccountHandlingPossibleProduct(
		WEB3BondProduct l_bondProduct, String l_strDealDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateAccountHandlingPossibleProduct(WEB3BondProduct, String) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j����.������.is�ڋq�戵�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (!l_bondProduct.isCustomerTradePossible())
        {
            log.debug("�ڋq�戵�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02661,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�戵�s�����G���[�B");
        }
        
        //�Q�j����.����敪�@@==�@@����@@�̏ꍇ
        //����.������.is����\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (WEB3BondDealDivDef.RECRUIT.equals(l_strDealDiv))
        {
        	if (!l_bondProduct.isRecruitPossible())
            {
                log.debug("����s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02612,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����s�����G���[�B");
            }
        }
        
        //�R�j����.����敪�@@==�@@���t�@@�̏ꍇ
        //����.������.is���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (WEB3BondDealDivDef.BUY.equals(l_strDealDiv))
        {
        	if (!l_bondProduct.isBuyPossible())
            {
                log.debug("���t�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02613,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���t�s�����G���[�B");
            }
        }
        
        //�S�j����.����敪�@@==�@@���p�@@�̏ꍇ
        //����.������.is���p�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (WEB3BondDealDivDef.SELL.equals(l_strDealDiv))
        {
        	if (!l_bondProduct.isSellPossible())
            {
                log.debug("���p�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
            		WEB3ErrorCatalog.BUSINESS_ERROR_02614,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���p�s�����G���[�B");
            }
        }     
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���������)<BR>
     * ��������ʃ`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�������P��.get�������ʁ@@���@@����.��萔�ʂ̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00300<BR>
     * �Q�j�������P��.�������敪�����ρ@@<BR>
     * �@@�@@�@@���@@�@@<BR>
     * �@@�@@�������P��.������ʔ���.is���p��true�̏ꍇ�A���p�\���ʂ��`�F�b�N����B�@@<BR>
     * �@@�Q�|�P�j����.��萔�ʁ������ϐ��ʂ̏ꍇ�A������������return����B�@@<BR>
     * �@@�Q�|�Q�j�����X�ʏ����e�[�u������Y�����R�[�h���擾����B�@@<BR>
     * �@@�Q�|�R�j�擾���������X�ʏ���.get�ۗL���Y�`�F�b�N�敪�i�j�̖߂�l = �h�`�F�b�N���Ȃ��h�̏ꍇ�A������������return����B�@@<BR>
     * �@@�Q�|�S�j�������R���ʃ`�F�b�N.validate���p�\���ʂ��Ăԁ@@<BR>
     * �@@�@@�@@�@@�@@[����]�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�⏕�����F�g���A�J�E���g�}�l�[�W��.get�⏕����(�������P��.����ID�A�������P��.�⏕����ID)�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�g�����V�K�������e�F�g�����V�K�������e.create�g�����V�K�������e()�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[����]�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�I�y���[�^�F null�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���F �������P��.get��������ʔ���@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����R�[�h(WEB3)�F ������.�����R�[�h(WEB3)�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���ʁF ��萔�ʁ@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�P���F �������P��.���P���@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ŋ敪�F �������P��.�ŋ敪�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��n���F �������P��.��n���@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���ϋ敪�F �������P��.���ϋ敪�@@<BR>
     * �@@�@@�@@�@@�@@�@@�@@�������F���v���_�N�g�}�l�[�W��.get������(�������P��.����ID)�@@<BR>
     * @@param l_dblOrderQuantity - (��萔��)<BR>
     * ��������<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D9D485025D
     */
    public void validateExecuteMaxQuantity(
		double l_dblOrderQuantity, WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
        	" validateExecuteMaxQuantity(double, WEB3BondOrderUnit) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�������P��.get�������ʁ@@���@@����.��萔�ʂ̏ꍇ�A
        //    ��O���X���[����B
        if (l_bondOrderUnit.getQuantity() < l_dblOrderQuantity)
        {
            log.debug("��萔�ʂ��������ʂ𒴂��Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��萔�ʂ��������ʂ𒴂��Ă��܂��B");
        }
        //�Q�j�������P��.�������敪������
        //      ����  
        //    �������P��.������ʔ���.is���p��true�̏ꍇ�A���p�\���ʂ��`�F�b�N����B
        WEB3BondOrderTypeJudge l_orderTypeJudge = l_bondOrderUnit.getBondOrderTypeJudge();
        
        if(WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus())
           && l_orderTypeJudge.isSellOrder())
        {

            //  �Q�|�P�j����.��萔�ʁ������ϐ��ʂ̏ꍇ�A������������return����B
            if(l_dblOrderQuantity <= l_bondOrderUnit.getExecutedQuantity())
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }
            double l_dblSellCheckQuantity = l_dblOrderQuantity - l_bondOrderUnit.getExecutedQuantity();

            try
            {
                //  �Q�|�Q�j�����X�ʏ����e�[�u������Y�����R�[�h���擾����B
                WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_bondOrderUnit.getBranchId());

                //  �Q�|�R�j�擾���������X�ʏ���.get�ۗL���Y�`�F�b�N�敪�i�j�̖߂�l = �h�`�F�b�N���Ȃ��h�̏ꍇ�A������������return����B
                if (WEB3BondAssetCheckDef.EXCEPT.equals(l_branchCondition.getAssetCheckDiv()))
                {
                    log.exiting(STR_METHOD_NAME);
                    return;
                }

            }
            catch (WEB3BaseException l_ex)
            {
                log.error("__error in ���X�p�v���t�@@�����X�e�[�u�����烌�R�[�h���擾__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //  �Q�|�S�j�������R���ʃ`�F�b�N.validate���p�\���ʂ��Ă�
            //          [����]
            //              �⏕�����F�g���A�J�E���g�}�l�[�W��.get�⏕����(�������P��.����ID�A�������P��.�⏕����ID)
            //              �g�����V�K�������e�F�g�����V�K�������e.create�g�����V�K�������e()
            //                                              [����]
            //                                                �I�y���[�^�F null
            //                                                ��������ʔ���F �������P��.get��������ʔ���
            //                                                �����R�[�h(WEB3)�F ������.�����R�[�h(WEB3)
            //                                                ���ʁF �V�K���͐��� - ����.��萔��
            //                                                �P���F �������P��.���P��
            //                                                �ŋ敪�F �������P��.�ŋ敪
            //                                                ��n���F �������P��.��n��
            //                                                ���ϋ敪�F �������P��.���ϋ敪
            //              �������F���v���_�N�g�}�l�[�W��.get������(�������P��.����ID)        
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_web3GentradeAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                        l_bondOrderUnit.getAccountId(), l_bondOrderUnit.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in �g���A�J�E���g�}�l�[�W������ڋq���擾__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            WEB3BondProductManager l_bondProductManager = 
                (WEB3BondProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getProductManager();
            WEB3BondProduct l_bondProduct = 
                (WEB3BondProduct) l_bondProductManager.getBondProduct(l_bondOrderUnit.getProductId());
            
            WEB3BondNewOrderSpec l_bondNewOrderSpec =
                WEB3BondNewOrderSpec.createBondNewOrderSpec(
                null,
                l_orderTypeJudge,
                l_bondProduct.getProductCode(),
                l_dblSellCheckQuantity,
                l_bondOrderUnit.getExecutedPrice(),
                l_bondOrderUnit.getTaxType(),
                l_bondOrderUnit.getDeliveryDate(),
                l_bondOrderUnit.getSettlementDiv());
            
            WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
                new WEB3BondOrderManagerReusableValidationsCheck();
            
            l_validationsCheck.validateTransferedPossibleQuantity(l_subAccount, l_bondNewOrderSpec, l_bondProduct);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�Ǘ��Ҏ戵�\����)<BR>
     * �Ǘ��Ҍ����Ɏ戵�\�Ȗ������ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.������.is�Ǘ��Ҏ戵�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@��戵�s�����G���[�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00362<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44DC00C70277
     */
    public void validateAdminTradingPossibleProduct(WEB3BondProduct l_bondProduct)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateAdminTradingPossibleProduct(WEB3BondProduct) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�Ǘ��Ҍ����Ɏ戵�\�Ȗ������ǂ������`�F�b�N����B
        //�P�j����.������.is�Ǘ��Ҏ戵�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (!l_bondProduct.isAdminTradePossible())
        {
            log.debug("�戵�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵�s�����G���[�B");
        }       
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���p����)<BR>
     * validate���p����<BR>
     * <BR>
     * �V�[�P���X�}�uvalidate���p�����v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����
     * @@param l_bondProduct - (������)<BR>
     * ������
     * @@param l_bondNewOrderSpec - (�g�����V�K�������e)<BR>
     * �g�����V�K�������e
     * @@throws WEB3BaseException 
     */
    public void validateSellOrder(
        SubAccount l_subAccount,
        WEB3BondProduct l_bondProduct,
        WEB3BondNewOrderSpec l_bondNewOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateSellOrder(SubAccount, WEB3BondProduct, WEB3BondNewOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondNewOrderSpec == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //1.1)validate�O���،������J��()
        this.validateFeqAccountOpen(l_subAccount, l_bondProduct);
        
        //1.2)validate�ڋq�戵�\����()
        this.validateAccountHandlingPossibleProduct(
            l_bondProduct, WEB3BondDealDivDef.SELL);
        
        //1.3) validate����()
        this.validateQuantity(l_bondNewOrderSpec.getQuantity(), l_bondProduct);
        
        //1.4)validate���ϋ敪()
        WEB3BondOrderManagerReusableValidationsCheck l_validationsCheck = 
            new WEB3BondOrderManagerReusableValidationsCheck();
        l_validationsCheck.validateSettlementDiv(
            l_bondNewOrderSpec.getSettlementDiv(), l_bondProduct);
        
        //1.5)�����X�ʏ����e�[�u������Y�����R�[�h���擾����B 
        long l_lngBranchId = l_subAccount.getMainAccount().getBranch().getBranchId();
        
        BondBranchConditionRow l_bondBranchConditionRow = null;
        try
        {
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_lngBranchId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6)�����򏈗����擾���������X�ʏ���.get�ۗL���Y�`�F�b�N�敪�i�j�̖߂�l != �h�`�F�b�N���Ȃ��h�̏ꍇ
        if (l_bondBranchConditionRow == null || 
            !WEB3BondAssetCheckDef.EXCEPT.equals(l_bondBranchConditionRow.getAssetCheckDiv()))
        {
            //1.6.1)validate���p�\����()
            l_validationsCheck.validateTransferedPossibleQuantity(
                l_subAccount, l_bondNewOrderSpec, l_bondProduct);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�בփ��[�g)<BR>
     * validate�בփ��[�g <BR>
     * <BR>
     * �P�j����.������.is�O�݌�()����true <BR>
     * �@@�@@���� <BR>
     * �@@ ����.�בփ��[�g����null�̏ꍇ <BR>
     * �@@ ��O���X���[���� <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02643<BR>
     * �Q�j����.������.is�O�݌�()����false <BR>
     *�@@�@@���� <BR>
     *�@@ ����.�בփ��[�g !��null�̏ꍇ <BR>
     *�@@ ��O���X���[���� <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02678<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_strFxRate - (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     * @@throws WEB3BaseException 
     */
    public void validateFxRate(WEB3BondProduct l_bondProduct, String l_strFxRate) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
        	" validateFxRate(WEB3BondProduct, String) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //�P�j����.������.is�O�݌�()����true ����
        //����.�בփ��[�g����null�̏ꍇ,��O���X���[����
        if (l_bondProduct.isForeignCurrency() 
            && WEB3StringTypeUtility.isEmpty(l_strFxRate))
        {
            log.debug("�בփ��[�g�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02643,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�בփ��[�g�����w��ł��B");
        }
        
        //�Q�j����.������.is�O�݌�()����false 
        //�@@���� 
        // ����.�בփ��[�g !��null�̏ꍇ 
        // ��O���X���[���� 
        if (!l_bondProduct.isForeignCurrency() 
            && !WEB3StringTypeUtility.isEmpty(l_strFxRate))
        {
            log.debug("���������g�O�݌��h�ȊO�̏ꍇ�́A�בփ��[�g��null�ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02678,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������g�O�݌��h�ȊO�̏ꍇ�́A�בփ��[�g��null�ȊO�̒l�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�בփ��[�g)<BR>
     *�בփ��[�g���擾����B <BR>
     *<BR>
     *�P�j���͈בփ��[�g != null and <BR>
     *�@@�@@���͈בփ��[�g != 0�@@�@@�̏ꍇ�A <BR>
     *�@@�@@�@@���͈בփ��[�g��return����B<BR>
     *2�j�i��������ʔ���.is���咍�� == true or  <BR>
     *�@@ ��������ʔ���.is���t���� == true�j and  <BR>
     *�@@�@@���ϋ敪 == "�O��" and <BR>
     *�@@�@@������.�d�����̈בփ��[�g �� null �̏ꍇ�A <BR>
     *<BR>
     *�@@�@@�@@������.�d�����̈בփ��[�g��return����B <BR>
     *<BR>
     *3�j��L�ȊO�̏ꍇ�A <BR>
     *<BR>
     *�@@�@@3�|�P�j������.get�ʉ�()�ɂ��i���ʁj�ʉ݂��擾����B <BR>
     *<BR>
     *�@@�@@�R�|�Q�j�i���ʁj�ʉ�.get�בփ��[�g()�ɂ��擾�����בփ��[�g��return����B <BR>
     *<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@[����] <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@is���t�F�@@��������ʔ���.is���咍�� == true or  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���.is���t���� == true �̏ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@true ���Z�b�g����B <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���.is���p���� == true �̏ꍇ�A <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@false ���Z�b�g����B <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@is���v�Z�F�@@is���v�Z <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���͈בփ��[�g�F�@@���͈בփ��[�g<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_orderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * @@param l_bdFxRate - (���͈בփ��[�g)<BR>
     * ���͈בփ��[�g<BR>
     * @@param l_blnIsExecuteCalc - (is���v�Z)<BR>
     * is���v�Z<BR>
     * <BR>
     * true�F�@@���v�Z<BR>
     * false�F�@@�T�Z�v�Z<BR>
     * @@throws WEB3BaseException
     * @@return BigDecimal
     */
    public BigDecimal  getFxRate(
        WEB3BondProduct l_bondProduct,
        WEB3BondOrderTypeJudge l_orderTypeJudge, 
        String l_strSettleDiv,
        BigDecimal l_bdFxRate,
        boolean l_blnIsExecuteCalc) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getFxRate(WEB3BondProduct,WEB3BondOrderTypeJudge,String,BigDecimal,boolean) " ;
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_orderTypeJudge == null || l_bdFxRate == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
    
        //�P�j���͈בփ��[�g != null and 
        //�@@���͈בփ��[�g != 0�@@�@@�̏ꍇ�A 
        //�@@�@@���͈בփ��[�g��return����B
        if (l_bdFxRate != null && !(new BigDecimal("0")).equals(l_bdFxRate))
        {
            log.exiting(STR_METHOD_NAME);
            return l_bdFxRate;
        }
    
        //2�j�i��������ʔ���.is���咍�� == true or  
        //��������ʔ���.is���t���� == true�j and  
        //���ϋ敪 == "�O��" and 
        //������.�d�����̈בփ��[�g �� null �̏ꍇ
        //������.�d�����̈בփ��[�g��return����B
        BondProductRow l_productRow = (BondProductRow) l_bondProduct.getDataSourceObject();
        if ((l_orderTypeJudge.isRecruitOrder() || l_orderTypeJudge.isBuyOrder())
            && WEB3BondOrderSettleDivDef.FOREIGN_CURRENCY.equals(l_strSettleDiv)
            && !l_productRow.getBuyingFxRateIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal(String.valueOf(l_bondProduct.getBuyingFxRate()));
        }
        //3�|�P�j������.get�ʉ�()�ɂ��i���ʁj�ʉ݂��擾����B
        WEB3GentradeCurrency l_gentradeCurrency = l_bondProduct.getCurrency();
        
        //3�|�Q�j�i���ʁj�ʉ�.get�בփ��[�g()�ɂ��擾�����בփ��[�g��return����B
        double l_dblFxRate = l_bdFxRate.doubleValue();
        boolean l_blnIsBuyOrder = false;
        
        //[����] 
        //is���t�F�@@��������ʔ���.is���咍�� == true or  
        //�@@�@@�@@�@@�@@  ��������ʔ���.is���t���� == true �̏ꍇ�A 
        //�@@�@@�@@�@@�@@�@@true ���Z�b�g����B 
        //�@@�@@�@@�@@�@@�@@��������ʔ���.is���p���� == true �̏ꍇ�A 
        //�@@�@@�@@�@@�@@�@@false ���Z�b�g����B 
        //is���v�Z�F�@@is���v�Z 
        //���͈בփ��[�g�F�@@���͈בփ��[�g
        if (l_orderTypeJudge.isRecruitOrder()  
            || l_orderTypeJudge.isBuyOrder() )
        {
            l_blnIsBuyOrder = true;
        }
        else if (l_orderTypeJudge.isSellOrder())
        {
            l_blnIsBuyOrder = false;
        }
        double l_dblExchangeRate =  
            l_gentradeCurrency.getExchangeRate(l_blnIsBuyOrder, l_blnIsExecuteCalc, l_dblFxRate);
    
        log.exiting(STR_METHOD_NAME);
        return new  BigDecimal(String.valueOf(l_dblExchangeRate));
    }
    
    /**
     * (get�������Ώے���)<BR>
     * get�������Ώے��� <BR>
     * <BR>
     * �������ΏۂƂȂ钍���P�ʂ̈ꗗ���擾����B<BR>
     * �P�j�@@�������P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �@@�N�G���v���Z�b�T���g�p���A�ȉ��̏����ɍ��v���钍���P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * �@@�i�󒍓����̏����w��j <BR>
     * �@@�@@���XID in �g���A�J�E���g�}�l�[�W��.get�،����(����.�،���ЃR�[�h).getBranches()<BR>
     * �@@�@@�̂����ꂩ�̕��XID<BR>
     * <BR>
     * �@@�@@����.from����ID <= ����ID<BR>
     * �@@�@@����ID <= ����.to����ID<BR>
     * �@@�@@�������敪 == "�����" <BR>
     * �@@�@@�������敪 == "�������"<BR>
     * �@@�@@������ == �Ɩ����t(*1)<BR>
     * �@@�@@(*1)�Ɩ����t�́AGtlUtils.getTradingSystem( ).getBizDate( ) �Ŏ擾����B<BR>
     * <BR>
     * �Q�j�@@�������ʂ�ԋp����B<BR>
     * @@param l_strInstitutitonCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_lngFromAccountId - (from����ID)<BR>
     * from����ID<BR>
     * @@param l_lngToAccountId - (to����ID)<BR>
     * to����ID<BR>
     * @@return WEB3BondOrderUnit[]
     */
    public WEB3BondOrderUnit[] getAutoExecuteOrder(String l_strInstitutitonCode, 
    	long l_lngFromAccountId, 
    	long l_lngToAccountId) 
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
        	" getAutoExecuteOrder(String, long, long) " ;
        log.entering(STR_METHOD_NAME);
        
        int l_intCount = 0;
        List l_lisQuerys = null;
        String l_strQueryString = "";
        WEB3BondOrderUnit[] l_bondOrderUnits = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);  
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();  

        try
        {
        	//�N�G���v���Z�b�T���g�p���A
        	//�ȉ��̏����ɍ��v���钍���P�ʃI�u�W�F�N�g��S�Ď擾����B
        	//���XID
            Branch[] l_branches = 
            	l_accountManager.getInstitution(l_strInstitutitonCode).getBranches();   
            Object[] l_objQueryDataContainers = null;
            if (l_branches != null && l_branches.length != 0)
            {
                l_objQueryDataContainers = new Object[l_branches.length + 5];
                l_intCount = l_branches.length;
                l_strQueryString = " branch_id in ( ";
				for (int i = 0; i < l_intCount; i++)
				{
				    if (i == l_intCount - 1)
				    {
				        l_strQueryString = l_strQueryString + " ? ";
				        l_objQueryDataContainers[i] = new Long(l_branches[i].getBranchId());
				    }
				    else
				    {
				        l_strQueryString = l_strQueryString + " ?, ";
				        l_objQueryDataContainers[i] = new Long(l_branches[i].getBranchId());
				    }
                }
                
                l_strQueryString = l_strQueryString + " ) ";
            }
            else
            {
                l_objQueryDataContainers = new Object[5];
            }

            l_strQueryString = l_strQueryString + " and account_id >= ? ";
            l_strQueryString = l_strQueryString + " and account_id <= ? ";
            l_strQueryString = l_strQueryString + " and order_exec_status = ? ";
            l_strQueryString = l_strQueryString + " and auto_exec_div = ? ";
            l_strQueryString = l_strQueryString + " and biz_date = ? ";
            
            //from����ID
            l_objQueryDataContainers[l_intCount] = 
                new Long(l_lngFromAccountId);
            
            //to����ID
            l_objQueryDataContainers[l_intCount + 1] = 
                new Long(l_lngToAccountId);
            
            //�������敪
            l_objQueryDataContainers[l_intCount + 2] =
                WEB3BondOrderExecStatusDef.UNEXECUTED;
            
            //�������敪
            l_objQueryDataContainers[l_intCount + 3] = 
                WEB3AutoExecDivDef.AUTO_EXECUTE;
            
			//������
			l_objQueryDataContainers[l_intCount + 4] = 
			    WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            
            String l_strSortKey = " received_date_time ASC ";
            
    		QueryProcessor l_qp = Processors.getDefaultProcessor();
    		l_lisQuerys = l_qp.doFindAllQuery(
				BondOrderUnitRow.TYPE, 
				l_strQueryString,
				l_strSortKey, 
				null,
				l_objQueryDataContainers);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);             
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // �������ʂ�ԋp����B
        if (l_lisQuerys != null && !l_lisQuerys.isEmpty())
        {
        	int l_intSize = l_lisQuerys.size();
        	l_bondOrderUnits = new WEB3BondOrderUnit[l_intSize];
        	
        	for (int i = 0; i < l_intSize; i++)
        	{
        		BondOrderUnitRow l_bondOrderUnitRow = 
                    (BondOrderUnitRow)l_lisQuerys.get(i);
                
        		WEB3BondOrderUnit l_bondOrderUnit = 
                    new WEB3BondOrderUnit(l_bondOrderUnitRow);
                
        		l_bondOrderUnits[i] = l_bondOrderUnit;
        	}
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_bondOrderUnits;
    }
    
    /**
     * (validate�P��)<BR>
     * validate�P��<BR>
     *   no operation
     *<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_strExecPrice - (�P��)<BR>
     * �P��<BR>
     * @@throws WEB3BaseException
     */
    public void validateExecPrice(WEB3BondProduct l_bondProduct, String l_strExecPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecPrice(WEB3BondProduct, String) " ;
        log.entering(STR_METHOD_NAME);
        
        //no operation
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc�������������z���v)<BR>
     * �����������̒������z���v���Z�o����B<BR>
     * <BR>
     * ���O���܂ł̒������z���擾�B<BR>
     * <BR>
     * �@@�P�j��������t�����e�[�u�����������A<BR>
     * �@@�@@�@@���߂̒�����t���t�̍�������t����Row���擾����B<BR>
     * <BR>
     * �@@�@@�@@[��������]<BR>
     * �@@�@@�@@�@@�@@����ID = ����.����ID<BR>
     * �@@�@@�@@�@@�@@and �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@and ���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@and ������t���t =<BR>
     * �@@�@@�@@�@@�@@�@@�@@ ( select max(������t���t) from ��������t�����e�[�u��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@where ����ID = ����.����ID<BR>
     *           �@@   �@@      and �،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     *           �@@   �@@      and ���X�R�[�h = ����.���X�R�[�h )<BR>
     * <BR>
     * �@@�Q�j�擾������������t����.�������z�݌v��<BR>
     * �@@�@@�@@�O���܂ł̒������z�Ƃ��āA�������z���v�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@���j�擾�ł��Ȃ������ꍇ�A0���Z�b�g����B<BR>
     * <BR>
     * ���������̒������z�Ɠ����ɉߓ�������ꂽ�������z���擾�B<BR>
     * <BR>
     * �@@�R�j����.���X�R�[�h �� "�S���X" �̏ꍇ�A<BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.get���X()���R�[�����A���X���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h�@@�F�@@����.�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@���X�R�[�h�@@�F�@@����.���X�R�[�h<BR>
     * <BR>
     * �@@�S�j�����̉��咍���Ɠ����ɉߓ�������ꂽ�������擾�B<BR>
     * �@@�@@�@@�������P�ʃe�[�u�����������A<BR>
     * �@@�@@�@@�������P�ʍs�̃��X�g���擾����B<BR>
     * <BR>
     * �@@�@@�S�|�P�j��������t����Row���擾�ł����ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̌��������Ō�������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�m���������n<BR>
     * �@@�@@�@@�@@�@@�@@����ID = ����.����ID<BR>
     * �@@�@@�@@�@@�@@�@@and���XID = �擾�������XID(*1)<BR>
     * �@@�@@�@@�@@�@@�@@and�@@������ʁ@@= �f���������咍���f<BR>
     * �@@�@@�@@�@@�@@�@@and (( �����L����ԁ@@= �f�I�[�v���f<BR>
     *         �@@�@@�@@�@@�@@�@@�@@and  �󒍓����@@�� ��������t����.������t���t(*2))<BR>
     * �@@�@@�@@�@@�@@�@@or ( �����L����� = �f�N���[�Y�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and ������� = �f�����ρi��������j�f<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and �󒍓����@@���@@��������t����.������t���t(*2)<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@and �X�V���t�@@���@@��������t����.������t���t(*2)))<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*1)����.���X�R�[�h �� "�S���X" �̏ꍇ�A���XID�̌���������ǉ�����B<BR>
     * �@@�@@�@@�@@�@@(*2)�N���������̂ݔ�r�B<BR>
     * <BR>
     * �@@�@@�S�|�Q�j��������t����Row���擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�ȉ��̌��������Ō�������B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�m���������n<BR>
     * �@@�@@�@@�@@�@@�@@����ID = ����.����ID<BR>
     * �@@�@@�@@�@@�@@�@@and���XID = �擾�������XID(*1)<BR>
     * �@@�@@�@@�@@�@@�@@and�@@������ʁ@@= �f���������咍���f<BR>
     * �@@�@@�@@�@@�@@�@@and ( �����L����ԁ@@= �f�I�[�v���f<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*1)����.���X�R�[�h �� "�S���X" �̏ꍇ�A���XID�̌���������ǉ�����B<BR>
     * <BR>
     * �@@�T�j�������P�ʍs�̃��X�g�̗v�f�����ȉ��̏�����LOOP���A<BR>
     * �@@�@@�������z���v���Z�o����B<BR>
     * <BR>
     * �@@�@@�T�|�P�j�������P�ʍs.�����L����ԁ@@= �f�I�[�v���f�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������z���v�ɍ������P�ʍs.�������ʂ����Z�i+�j����B<BR>
     * <BR>
     * �@@�@@�T�|�Q�j�������P�ʍs.�����L����ԁ@@= �f�N���[�Y�f�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�������z���v����������P�ʍs.�������ʂ����Z�i-�j����B<BR>
     * <BR>
     * �U�j�Z�o�����������z���v��ԋp����B<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcBondDomesticOrderAmountTotal(
        long l_lngProductId,
        String l_strInstitutionCode,
        String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " calcBondDomesticOrderAmountTotal(long, String, String)";
        log.entering(STR_METHOD_NAME);

        double l_dblOrderAmountTotalCnt = 0.0D;

        Long l_productId = new Long(l_lngProductId);
        //���O���܂ł̒������z���擾�B
        List l_lisQuerys = null;
        BondOrderAcceptActionRow l_bondOrderAcceptActionRow = null;
        try
        {
            //�P�j��������t�����e�[�u�����������A
            //���߂̒�����t���t�̍�������t����Row���擾����B
            //[��������]
            //����ID = ����.����ID
            //and �،���ЃR�[�h = ����.�،���ЃR�[�h
            //and ���X�R�[�h = ����.���X�R�[�h
            //and ������t���t = ( select max(������t���t) from ��������t�����e�[�u��
            //where ����ID = ����.����ID
            //and �،���ЃR�[�h = ����.�،���ЃR�[�h
            //and ���X�R�[�h = ����.���X�R�[�h )
            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append(" product_id = ? ");
            l_sbQueryString.append(" and institution_code = ? ");
            l_sbQueryString.append(" and branch_code = ? ");
            l_sbQueryString.append(
                " and order_accept_date = (select max(order_accept_date) from bond_order_accept_action ");
            l_sbQueryString.append(" where product_id = ? ");
            l_sbQueryString.append(" and institution_code = ? ");
            l_sbQueryString.append(" and branch_code = ? )");
            log.debug("l_sbQueryString==" + l_sbQueryString.toString());

            Object[] l_queryDataContainers = {l_productId, l_strInstitutionCode, l_strBranchCode,
                l_productId, l_strInstitutionCode, l_strBranchCode};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisQuerys = l_queryProcessor.doFindAllQuery(
                BondOrderAcceptActionRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainers);

            //�Q�j�擾������������t����.�������z�݌v��
            //�O���܂ł̒������z�Ƃ��āA�������z���v�ɃZ�b�g����B
            //���j�擾�ł��Ȃ������ꍇ�A0���Z�b�g����B
            if (!l_lisQuerys.isEmpty())
            {
                l_bondOrderAcceptActionRow = (BondOrderAcceptActionRow)l_lisQuerys.get(0);
                l_dblOrderAmountTotalCnt = l_bondOrderAcceptActionRow.getTotalOrderAmount();
            }
            else
            {
                l_dblOrderAmountTotalCnt = 0;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //���������̒������z�Ɠ����ɉߓ�������ꂽ�������z���擾�B
        //�R�j����.���X�R�[�h �� "�S���X" �̏ꍇ�A
        //�g���A�J�E���g�}�l�[�W��.get���X()���R�[�����A���X���擾����B
        //[����]
        //�،���ЃR�[�h�@@�F�@@����.�،���ЃR�[�h
        //���X�R�[�h�@@�F�@@����.���X�R�[�h
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        long l_lngBranchId = 0L;
        if (!WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_strBranchCode))
        {
            try
            {
                Institution l_institution =
                    l_gentradeAccountManager.getInstitution(l_strInstitutionCode);
                Branch l_branch = l_gentradeAccountManager.getBranch(l_institution, l_strBranchCode);
                l_lngBranchId = l_branch.getBranchId();
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        Long l_branchId = new Long(l_lngBranchId);

        //�S�j�����̉��咍���Ɠ����ɉߓ�������ꂽ�������擾�B
        //�������P�ʃe�[�u�����������A
        //�������P�ʍs�̃��X�g���擾����B
        List l_lisBondOrderUnitRows = null;
        try
        {
            //�m���������n
            StringBuffer l_sbQueryString = new StringBuffer();
            List l_lisValues = new ArrayList();
            Object[] l_queryDataContainers = null;

            if (l_bondOrderAcceptActionRow != null)
            {
                //����ID = ����.����ID
                l_sbQueryString.append(" product_id = ? ");
                l_lisValues.add(l_productId);

                //and���XID = �擾�������XID(*1)
                //(*1)����.���X�R�[�h �� "�S���X" �̏ꍇ�A���XID�̌���������ǉ�����B
                if (!WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_strBranchCode))
                {
                    l_sbQueryString.append(" and branch_id = ? ");
                    l_lisValues.add(l_branchId);
                }

                //and�@@������ʁ@@= �f���������咍���f
                l_sbQueryString.append(" and order_type = ? ");
                l_lisValues.add(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);

                //and (( �����L����ԁ@@= �f�I�[�v���f
                l_sbQueryString.append(" and (( order_open_status = ? ");
                l_lisValues.add(OrderOpenStatusEnum.OPEN);

                Timestamp l_tsOrderAcceptDate =
                    l_bondOrderAcceptActionRow.getOrderAcceptDate();
                Date l_datOrderAcceptDate = WEB3DateUtility.toDay(l_tsOrderAcceptDate);
                String l_strOrderAcceptDate =
                    WEB3DateUtility.formatDate(l_datOrderAcceptDate, WEB3GentradeTimeDef.DATE_SPLIT_YMD);

                //and  �󒍓����@@�� ��������t����.������t���t(*2))
                //(*2)�N���������̂ݔ�r�B
                l_sbQueryString.append(" and to_char(received_date_time, 'yyyy/MM/dd')> ? ) ");
                l_lisValues.add(l_strOrderAcceptDate);

                //or ( �����L����� = �f�N���[�Y�f
                l_sbQueryString.append(" or ( order_open_status = ? ");
                l_lisValues.add(OrderOpenStatusEnum.CLOSED);

                //and ������� = �f�����ρi��������j�f
                l_sbQueryString.append(" and order_status = ? ");
                l_lisValues.add(OrderStatusEnum.CANCELLED);

                //and �󒍓����@@���@@��������t����.������t���t(*2)
                //(*2)�N���������̂ݔ�r�B
                l_sbQueryString.append(" and to_char(received_date_time, 'yyyy/MM/dd') <= ? ");
                l_lisValues.add(l_strOrderAcceptDate);

                //and �X�V���t�@@���@@��������t����.������t���t(*2)))
                //(*2)�N���������̂ݔ�r�B
                l_sbQueryString.append(" and to_char(last_updated_timestamp, 'yyyy/MM/dd') > ? ))");
                l_lisValues.add(l_strOrderAcceptDate);

                l_queryDataContainers = new Object[l_lisValues.size()];
                l_lisValues.toArray(l_queryDataContainers);
            }
            else
            {
                //�S�|�P�j��������t����Row���擾�ł��Ȃ������ꍇ�A
                //�ȉ��̌��������Ō�������B
                //����ID = ����.����ID
                l_sbQueryString.append(" product_id = ? ");
                l_lisValues.add(l_productId);

                //and���XID = �擾�������XID(*1)
                //(*1)����.���X�R�[�h �� "�S���X" �̏ꍇ�A���XID�̌���������ǉ�����B
                if (!WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH.equals(l_strBranchCode))
                {
                    l_sbQueryString.append(" and branch_id = ? ");
                    l_lisValues.add(l_branchId);
                }

                //and�@@������ʁ@@= �f���������咍���f
                l_sbQueryString.append(" and order_type = ? ");
                l_lisValues.add(OrderTypeEnum.DOMESTIC_BOND_RECRUIT);

                //and �����L����ԁ@@= �f�I�[�v���f
                l_sbQueryString.append(" and order_open_status = ? ");
                l_lisValues.add(OrderOpenStatusEnum.OPEN);

                l_queryDataContainers = new Object[l_lisValues.size()];
                l_lisValues.toArray(l_queryDataContainers);
            }
            log.debug("l_sbQueryString==" + l_sbQueryString.toString());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisBondOrderUnitRows = l_queryProcessor.doFindAllQuery(
                BondOrderUnitRow.TYPE,
                l_sbQueryString.toString(),
                l_queryDataContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�T�j�������P�ʍs�̃��X�g�̗v�f�����ȉ��̏�����LOOP���A
        //�������z���v���Z�o����B
        BigDecimal l_bdOrderAmountTotalCnt =
            new BigDecimal(String.valueOf(l_dblOrderAmountTotalCnt));

        Iterator l_iteratorBondOrderUnitRow = l_lisBondOrderUnitRows.iterator();
        while (l_iteratorBondOrderUnitRow.hasNext())
        {
            BondOrderUnitRow l_bondOrderUnitRow =
                (BondOrderUnitRow)l_iteratorBondOrderUnitRow.next();
            OrderOpenStatusEnum l_orderOpenStatusEnum =
                l_bondOrderUnitRow.getOrderOpenStatus();

            //�������P�ʍs.��������
            double l_dblQuantity = l_bondOrderUnitRow.getQuantity();

            BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));

            //�T�|�P�j�������P�ʍs.�����L����ԁ@@= �f�I�[�v���f�̏ꍇ�A
            //�������z���v�ɍ������P�ʍs.�������ʂ����Z�i+�j����B
            if (OrderOpenStatusEnum.OPEN.equals(l_orderOpenStatusEnum))
            {
                l_bdOrderAmountTotalCnt = l_bdOrderAmountTotalCnt.add(l_bdQuantity);
            }

            //�T�|�Q�j�������P�ʍs.�����L����ԁ@@= �f�N���[�Y�f�̏ꍇ�A
            //�������z���v����������P�ʍs.�������ʂ����Z�i-�j����B
            if (OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatusEnum))
            {
                l_bdOrderAmountTotalCnt = l_bdOrderAmountTotalCnt.subtract(l_bdQuantity);
            }
        }

        l_dblOrderAmountTotalCnt = l_bdOrderAmountTotalCnt.doubleValue();

        //�U�j�Z�o�����������z���v��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblOrderAmountTotalCnt;
    }

    /**
     * (validate���������咍��)<BR>
     * validate���������咍��<BR>
     * <BR>
     * �V�[�P���X�}�uvalidate���������咍���v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@throws WEB3BaseException
     */
    public void validateBondDomesticApplyOrder(
        SubAccount l_subAccount,
        WEB3BondProduct l_bondProduct,
        double l_dblOrderQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateBondDomesticApplyOrder(SubAccount, WEB3BondProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //validate�@@�l�ڋq
        this.validateCorporationAccount(l_subAccount, l_bondProduct);

        //validate�ڋq�戵�\����<������>
        this.validateAccountHandlingPossibleProductBondDomestic(
            l_bondProduct, WEB3BondDealDivDef.RECRUIT);

        //validate����
        this.validateQuantity(l_dblOrderQuantity, l_bondProduct);

        //validate����������g
        WEB3BondOrderManagerReusableValidationsCheck l_bondOrderManagerReusableValidationsCheck =
            new WEB3BondOrderManagerReusableValidationsCheck();
        //����]
        //���XID�F����.�⏕����.getMainAccountRow().getBranchId()
        //�������F����.������
        //�������ʁF����.��������

        MainAccountRow l_mainAccountRow =
            (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

        l_bondOrderManagerReusableValidationsCheck.validateDomesticBondRecruitLimit(
            l_mainAccountRow.getBranchId(),
            l_bondProduct,
            l_dblOrderQuantity);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�ڋq�戵�\����<������>)<BR>
     * �ڋq�����Ɏ戵�\�Ȗ������ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.������.is�ڋq�戵�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@��ڋq�戵�s�����G���[�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02661<BR>
     * <BR>
     * �Q�j����.����敪�@@==�@@����@@�̏ꍇ<BR>
     * �@@����.������.is����������\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�����s�����G���[�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02612<BR>
     * <BR>
     * �R�j����.����敪�@@==�@@���t�@@�̏ꍇ<BR>
     * �@@����.������.is���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@����t�s�����G���[�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02613<BR>
     * <BR>
     * �S�j����.����敪�@@==�@@���p�@@�̏ꍇ<BR>
     * �@@����.������.is���p�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@����p�s�����G���[�<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02614<BR>
     * <BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_strDealDiv - (���)<BR>
     * ���<BR>
     * @@throws WEB3BaseException
     */
    public void validateAccountHandlingPossibleProductBondDomestic(
        WEB3BondProduct l_bondProduct, String l_strDealDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateAccountHandlingPossibleProductBondDomestic(WEB3BondProduct, String)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j����.������.is�ڋq�戵�\()�̖߂�l == false �̏ꍇ�A��O���X���[����
        if (!l_bondProduct.isCustomerTradePossible())
        {
            log.debug("�ڋq�戵�s�����G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02661,
                this.getClass().getName() + STR_METHOD_NAME,
                "�ڋq�戵�s�����G���[�B");
        }

        //�Q�j����.����敪�@@==�@@����@@�̏ꍇ
        //����.������.is����������\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (WEB3BondDealDivDef.RECRUIT.equals(l_strDealDiv))
        {
            if (!l_bondProduct.isBondDomesticApplyPossible())
            {
                log.debug("����s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02612,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����s�����G���[�B");
            }
        }

        //�R�j����.����敪�@@==�@@���t�@@�̏ꍇ
        //����.������.is���t�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (WEB3BondDealDivDef.BUY.equals(l_strDealDiv))
        {
            if (!l_bondProduct.isBuyPossible())
            {
                log.debug("���t�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02613,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���t�s�����G���[�B");
            }
        }

        //�S�j����.����敪�@@==�@@���p�@@�̏ꍇ
        //����.������.is���p�\()�̖߂�l == false �̏ꍇ�A��O���X���[����B
        if (WEB3BondDealDivDef.SELL.equals(l_strDealDiv))
        {
            if (!l_bondProduct.isSellPossible())
            {
                log.debug("���p�s�����G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02614,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���p�s�����G���[�B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�������������)<BR>
     * �����������쐬����B<BR>
     * <BR>
     * �P�j���������C���X�^���X�𐶐�<BR>
     * <BR>
     * �Q�j���������.�������փZ�b�g����B<BR>
     * �@@�@@[���������.set������()�ɓn������]<BR>
     * �@@�@@�@@�������F�@@������.get������������()<BR>
     * <BR>
     * �R�j���������.�����փZ�b�g����B<BR>
     * �@@�@@[���������.set����()�ɓn������]<BR>
     * �@@�@@�@@�����F�@@������.get������������()<BR>
     * <BR>
     * �S�j���������.��n���փZ�b�g����B<BR>
     * �@@�@@[���������.set��n��()�ɓn������]<BR>
     * �@@�@@�@@��n���F�@@������.get��n��()<BR>
     * <BR>
     * �T�j���������.�������փZ�b�g����B<BR>
     * �@@�@@[���������.set������()�ɓn������]<BR>
     * �@@�@@�@@�������F�@@������.get��n��()<BR>
     * <BR>
     * �U�j����������ԋp����B<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@return WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException
     */
    public WEB3BondExecuteDateInfo createBondDomesticExecutionDateInfo(
        WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createBondDomesticExecutionDateInfo(WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���������C���X�^���X�𐶐�
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = new WEB3BondExecuteDateInfo();

        //���������.�������փZ�b�g����B
        //[���������.set������()�ɓn������]
        //�������F�@@������.get������������()
        l_bondExecuteDateInfo.setBizDate(l_bondProduct.getBondDomesticBizDate());

        //���������.�����փZ�b�g����B
        //[���������.set����()�ɓn������]
        //�����F�@@������.get������������()
        l_bondExecuteDateInfo.setExecuteDate(l_bondProduct.getBondDomesticBizDate());

        //���������.��n���փZ�b�g����B
        //[���������.set��n��()�ɓn������]
        //��n���F�@@������.get��n��()
        l_bondExecuteDateInfo.setDeliveryDate(l_bondProduct.getDeliveryDate());

        //���������.�������փZ�b�g����B
        //[���������.set������()�ɓn������]
        //�������F�@@������.get��n��()
        l_bondExecuteDateInfo.setPaymentDate(l_bondProduct.getDeliveryDate());

        log.exiting(STR_METHOD_NAME);
        return l_bondExecuteDateInfo;
    }

    /**
     * (validate�@@�l�ڋq)<BR>
     * �ڋq���@@�l���ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.�⏕����.getMainAccount()���R�[�����āA�ڋq���擾����B<BR>
     * <BR>
     * �Q�j�ڋq.is�@@�l()�̖߂�l == true<BR>
     * �@@������.������.is�l��������() == true �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�u�@@�l�ڋq�G���[�v<BR>
     * �@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_02884<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException
     */
    public void validateCorporationAccount(
        SubAccount l_subAccount, WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateCorporationAccount(SubAccount, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        //����.�⏕����.getMainAccount()���R�[�����āA�ڋq���擾����B
        MainAccount l_mainAccount = l_subAccount.getMainAccount();

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

        //�ڋq.is�@@�l()�̖߂�l == true
        //������.������.is�l��������() == true �̏ꍇ�A��O���X���[����B
        //�u�@@�l�ڋq�G���[�v
        if (l_gentradeMainAccount.isCorporation()
            && l_bondProduct.isIndividualGovernmentBond())
        {
            log.debug("�@@�l�ڋq�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02884,
                this.getClass().getName() + STR_METHOD_NAME,
                "�@@�l�ڋq�G���[�B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
