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
filename	WEB3AdminBondHelperServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��҃w���p�[�T�[�r�XImpl(WEB3AdminBondHelperServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 ꎉ�(���u) �V�K�쐬      
Revesion History : 2006/10/16 �����F (���u) ���f��No.106.108.111.118.126.128
Revesion History : 2007/03/09 ����� (���u) �d�l�ύX�E���f��159
Revesion History : 2007/07/25 ������ (���u) �d�l�ύX�E���f��238
Revesion History : 2007/07/25 ������ (���u) �d�l�ύX�E���f��246
Revesion History : 2009/07/24 ���g (���u) �d�l�ύX�E���f��261
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondBranchCondition;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.bd.data.CustodianRow;
import webbroker3.bd.define.WEB3BondCancelDivDef;
import webbroker3.bd.define.WEB3BondExecuteChangeButtonDivDef;
import webbroker3.bd.define.WEB3BondLockReleaseButtonDivDef;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondFxRateInfo;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BondOrderLockUseDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3PaymentDateDetDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���Ǘ��҃w���p�[�T�[�r�XImpl)<BR>
 * ���Ǘ��҃w���p�[�T�[�r�XImpl�N���X
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminBondHelperServiceImpl implements WEB3AdminBondHelperService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondHelperServiceImpl.class);
    /**
     * @@roseuid 44E336290251
     */
    
    public WEB3AdminBondHelperServiceImpl() 
    {
     
    }
    
    /**
     * (to�ڋq���)<BR>
     * �������ڋq�����쐬����<BR>
     * <BR>
     * �P�j����.�������P�ʂ�����ID���擾<BR>
     * <BR>
     * �Q�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����āA�ڋq�I�u�W�F�N�g���擾<BR>
     * �@@�@@[����]<BR>
     * �@@�@@����ID�F�擾��������ID<BR>
     * <BR>
     * �R�jthis.to�ڋq���(�ڋq)���Ă� <BR>
     * [����] <BR>
     * �ڋq�F�Q�j�Ŏ擾�����ڋq�I�u�W�F�N�g <BR>
     * <BR>
     * �S�j�ڋq���I�u�W�F�N�g��ԋp <BR>
     * @@param l_orderUnit - (�������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CAFF090399
     */
    public WEB3AdminBondAccountInfo toAccountInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " toAccountInfo(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�P�j����.�������P�ʂ�����ID���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            //�Q�j�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[�����āA�ڋq�I�u�W�F�N�g���擾
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_web3GentradeAccountManager.getMainAccount(
                    l_orderUnit.getAccountId());
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
      
        // �R�jthis.to�ڋq���(�ڋq)���Ă� 
        WEB3AdminBondAccountInfo  l_adminBondAccountInfo = 
            this.toAccountInfo(l_mainAccount);
        
        // �S�j�ڋq���I�u�W�F�N�g��ԋp
        return l_adminBondAccountInfo;
    }
    
    /**
     * (to�������)<BR>
     * ������蒍�������쐬����<BR>
     * <BR>
     * �P�j�������I�u�W�F�N�g�𐶐�<BR>
     * <BR>    
     * �Q�j���������������I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g����<BR>
     * <BR>
     * �@@�������      =����.�������P��.get�������()<BR>
     * �@@���            =����.�������P��.get���()<BR>
     * �@@���ϋ敪      =����.�������P��.get���ϋ敪()<BR>
     * �@@��������      =����.�������P��.get����() <BR>
     *   �P��            =����.�������P��.get�w�l()<BR> 
     * <BR>
     * �@@-�בփ��[�g�ݒ�<BR>
     * �@@���v���_�N�g�}�l�[�W��.get������<BR>
     * �@@�@@(����.�������P��.get����ID).is�O�݌�==true�̏ꍇ<BR>
     *      ����.�������P��.���בփ��[�g != NULL�̏ꍇ<BR>
     * �@@�@@ �בփ��[�g = ����.�����P��.get���בփ��[�g() <BR>
     *      ��L�ł͂Ȃ��A����.�������P��.��בփ��[�g != NULL�̏ꍇ <BR>
     * �@@�@@ �בփ��[�g = ����.�����P��.get��בփ��[�g()  <BR>
     * <BR>
     * �@@�ŋ敪   =����.�������P��.ge�ŋ敪()<BR>
     * <BR>
     * �R�j���������������I�u�W�F�N�g��Ԃ��B<BR>
     * @@param l_orderUnit - (�������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondOrderInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CAFDD80134
     */
    public WEB3AdminBondOrderInfo toOrderInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toOrderInfo(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�P�j�������I�u�W�F�N�g�𐶐�
        WEB3AdminBondOrderInfo l_adminBondOrderInfo = 
            new WEB3AdminBondOrderInfo();
        
        //�Q�j���������������I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g����
        //   �������      =����.�������P��.get�������()
        l_adminBondOrderInfo.tradingType = l_orderUnit.getOrderType().intValue() + "";
        
        //   ���      =����.�������P��.get���()
        l_adminBondOrderInfo.dealType = l_orderUnit.getDealType();
        
        //   ���ϋ敪   =����.�������P��.get���ϋ敪()
        l_adminBondOrderInfo.settleDiv = l_orderUnit.getSettlementDiv();
        
        // ��������   =����.�������P��.get����()  
        l_adminBondOrderInfo.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        
        //�@@�P��      =����.�������P��.get�w�l() 
        l_adminBondOrderInfo.price = 
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        
        //���v���_�N�g�}�l�[�W��.get������(����.�������P��.get����ID).is�O�݌�==true�̏ꍇ 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
         (WEB3BondProductManager) l_finApp.getTradingModule(
             ProductTypeEnum.BOND).getProductManager();
        
        long l_lngProductId = l_orderUnit.getProductId();
        
        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_orderUnit.getDataSourceObject();

        if(l_bondOrderUnitRow != null)
        {    
            //���v���_�N�g�}�l�[�W��.get������(����.�������P��.get����ID).is�O�݌�==true�̏ꍇ
            WEB3BondProduct l_bondProduct = (WEB3BondProduct) l_bondProductManager.getBondProduct(l_lngProductId);
            if (l_bondProduct != null)
            {
                boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
                
                if (l_blnIsForeignCurrency)
                {
                    //����.�������P��.get���בփ��[�gIsNull == false�̏ꍇ
                    if (!l_bondOrderUnitRow.getExecFxRateIsNull())
                    {
                        //�בփ��[�g = ����.�����P��.get���בփ��[�g()
                        l_adminBondOrderInfo.fxRate =
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecFxRate());
                    }

                    //����.�������P��.get��בփ��[�gIsNull == false�̏ꍇ
                    else if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
                    {
                        //��L�ł͂Ȃ��A����.�������P��.��בփ��[�g != NULL�̏ꍇ
                        l_adminBondOrderInfo.fxRate =
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getBaseFxRate());
                    }
                }
            }
       }
        
        //�ŋ敪   =����.�������P��.get�ŋ敪()
        l_adminBondOrderInfo.taxType = l_orderUnit.getTaxType().intValue() + "";
        
        //�R�j���������������I�u�W�F�N�g��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_adminBondOrderInfo;
    }
    
    /**
     * (to�����)<BR>
     * �������������쐬����<BR>
     * <BR>
     * �P�j���������I�u�W�F�N�g�𐶐�<BR>
     * <BR>
     * �Q�j���������擾����B<BR>
     * �@@�@@���v���_�N�g�}�l�[�W��.get������(����ID)<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�@@����ID�F�������P��.get����ID()<BR>
     * <BR>
     * �R�j���������擾����B<BR>
     * �@@�@@�@@������ԊǗ�.get������()<BR>
     * <BR>
     * �S�j�������������I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g����<BR>
     * ���e���ڂ�NULL�łȂ��ꍇ�̂݁A�Z�b�g����B <BR>
     * �������(�O��) �� �������P��.get�������(�O��)()<BR>
     * �������(�~��) �� �������P��.get�������(�~��)()<BR>
     * �o�ߗ��q(�O��) �� �������P��.get�o�ߗ��q(�O��)()<BR>
     * �o�ߗ��q(�~��) �� �������P��.get�o�ߗ��q(�~��)()<BR>
     * ��n���(�O��) �� �������P��.get��n���(�O��)()<BR>
     * ��n���(�~��) �� �������P��.get��n���(�~��)()<BR>
     * �o�ߓ����@@�@@�@@�@@ �� �������P��.get�o�ߓ���()<BR>
     * ����� �@@�@@�@@�@@�� �������P��.get�����()<BR>
     * �������敪   �� �������P��.get�������敪()<BR>
     * <BR>
     * �T�j�J�X�g�f�B�A���̐ݒ�<BR>
     * �@@�ECustodianRow�I�u�W�F�N�g���擾����<BR>
     * �@@�@@���f�[�^�}�l�[�W���T�[�r�X.get�J�X�g�f�B�A��(�،����, �J�X�g�f�B�A���R�[�h)<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЁF�g���A�J�E���g�}�l�[�W��.get���X�i�������P��.get���XID�j.get�،����()<BR>
     * �@@�@@�@@�J�X�g�f�B�A���R�[�h�F�������P��.get�J�X�g�f�B�A���R�[�h<BR>
     * �@@�E�J�X�g�f�B�A���ɒl���Z�b�g����<BR>
     * �@@�@@�J�X�g�f�B�A�������Ǘ��҃w���p�[�T�[�r�X.to�J�X�g�f�B�A��<BR>(CustodianRow)<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@CustodianRow�F�擾����CustodianRow<BR>
     * <BR>
     * �U�j��萔�ʁA���P���̐ݒ�<BR>
     * <BR>
     * �@@�U�|�P�j�������敪 == �����̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@��萔�� �@@�@@�@@���@@�������P��.get����() <BR>
     * �@@�@@�@@�@@�@@�@@���P�� �@@�@@�@@�� �������P��.get�w�l()<BR>
     * <BR>
     * �@@�U�|�Q�j�������敪 == ���ς̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@��萔�ʁ@@�@@�@@ ���@@�������P��.get��萔��() <BR>
     * �@@�@@�@@�@@�@@�@@���P�� �@@�@@�@@�� �������P��.get���P��() <BR>
     * <BR>
     * �V�j���בփ��[�g�A�����A��n���A���n�����A���n��n���A�������̐ݒ�<BR>
     * <BR>
     * �@@�V�|�P�j�������敪 == ����� ���� �������P��.���^�C�v == �O���� �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@������.is�O�݌�����true�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���בփ��[�g���������}�l�[�W��.get�בփ��[�g(������, ������ʔ���, ���ϋ敪,<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���͈בփ��[�g, is���v�Z)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������F������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���F�������P��.get��������ʔ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���ϋ敪�F�������P��.get���ϋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���͈בփ��[�g�F�@@0<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@is���v�Z�F�@@true<BR>
     * <BR>
     * �@@�@@�A�����@@�@@�@@�@@�@@ �� ������.get����(������)<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@���������擾����������<BR>
     * <BR>
     * �@@�@@�B��n�� �@@�@@�@@�@@�@@�� ������.get��n��(����, ��������ʔ���, ���ϋ敪, ���X)<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�������擾��������<BR>
     * �@@�@@�@@�@@�@@�@@��������ʔ��聁�������P��.get��������ʔ���<BR>
     * <BR>
     * �@@�@@�C������.is�O��������true�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@���n���� �@@�@@�� ������.get���n����(���n������)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���n��������������.get���n������(������)<BR>
     * �@@�@@�@@�@@�@@<BR>
     * �@@�@@�@@�@@�@@���n��n�� �@@�@@�� ������.get���n��n��(���n����, ��������ʔ���)<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���n�������擾�������n����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��������ʔ��聁�������P��.get��������ʔ���<BR>
     * <BR>
     * �@@�@@�D������ �@@�@@�@@�@@�@@�� ������.get������(����, ��������ʔ���, ���ϋ敪, ���X) <BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�������擾��������<BR>
     * �@@�@@�@@�@@�@@�@@��������ʔ��聁�������P��.get��������ʔ���<BR>
     * �@@�@@�@@�@@�@@�@@���ϋ敪���������P��.get���ϋ敪<BR>
     * �@@�@@�@@�@@�@@�@@���X���g���A�J�E���g�}�l�[�W��.get���X�i�������P��.get���XID�j<BR>
     * <BR>
     * �@@�V�|�Q�j�������敪 == ���� �܂��� ���^�C�v �� �O���� �̏ꍇ<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@���בփ��[�g �� �������P��.get���בփ��[�g() <BR>
     * �@@�@@�@@�@@�@@�@@�����@@�@@�@@�@@�@@ �� �������P��.get����()<BR>
     * �@@�@@�@@�@@�@@�@@���n���� �@@�@@�� �������P��.get���n����()<BR>
     * �@@�@@�@@�@@�@@�@@��n�� �@@�@@�@@�@@�@@�� �������P��.get��n��() <BR>
     * �@@�@@�@@�@@�@@�@@���n��n�� �@@�@@�� �������P��.get���n��n��()<BR>
     * �@@�@@�@@�@@�@@�@@�������@@�@@�@@ �@@�@@�� �������P��.get������()<BR>
     * <BR>
     * �W�j�������������I�u�W�F�N�g��ԋp�B<BR>
     * <BR>
     * @@param l_orderUnit - (�������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CAFDD80163
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toOrderExecInfo(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�P�j���������I�u�W�F�N�g�𐶐�
        WEB3AdminBondOrderExecInfo l_adminBondOrderExecInfo = 
            new WEB3AdminBondOrderExecInfo();      

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
         (WEB3BondProductManager) l_finApp.getTradingModule(
             ProductTypeEnum.BOND).getProductManager();
        
        //�Q�j���������擾����B 
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_orderUnit.getProduct().getProductId());
        
        
        log.debug("getProductCode=====" +l_bondProduct.getProductCode());
        log.debug("getProductId=====" +l_bondProduct.getProductId());

        if (l_bondProduct == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�R�j���������擾����B 
        //�@@�@@�@@������ԊǗ�.get������() 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("l_datBizDate===" + l_datBizDate.getTime());
        
        //�S�j�������������I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g���� 
        //���e���ڂ�NULL�łȂ��ꍇ�̂݁A�Z�b�g����B 
        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //�������(�O��) �� �������P��.get�������(�O��)() 
        if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_adminBondOrderExecInfo.foreignTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignTradingPrice());
        }
        
        //�������(�~��) �� �������P��.get�������(�~��)() 
        if (!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_adminBondOrderExecInfo.yenTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getTradingPrice());
        }
        
        //�o�ߗ��q(�O��) �� �������P��.get�o�ߗ��q(�O��)()
        if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_adminBondOrderExecInfo.foreignAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignAccruedInterest());
        }
        
        //�o�ߗ��q(�~��) �� �������P��.get�o�ߗ��q(�~��)() 
        if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_adminBondOrderExecInfo.yenAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getAccruedInterest());
        }
        
        //��n���(�O��) �� �������P��.get��n���(�O��)() 
        if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_adminBondOrderExecInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignEstimatedPrice());
        }
        
        //��n���(�~��) �� �������P��.get��n���(�~��)() 
        if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_adminBondOrderExecInfo.yenDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getEstimatedPrice());
        }
        
        //�o�ߓ����@@�@@�@@�@@ �� �������P��.get�o�ߓ���() 
        if (!l_bondOrderUnitRow.getElapsedDaysIsNull())
        {
            l_adminBondOrderExecInfo.elapsedDays = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getElapsedDays());
        }
        
        //����� �@@�@@�@@�@@�� �������P��.get�����() 
        if (!l_bondOrderUnitRow.getCalcBaseDaysIsNull())
        {
            l_adminBondOrderExecInfo.calcBaseDays = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getCalcBaseDays());
        }
        
        //�������敪   �� �������P��.get�������敪() 
        if (l_bondOrderUnitRow.getOrderExecStatusIsSet())
        {
            l_adminBondOrderExecInfo.executionState = l_orderUnit.getOrderExecStatus();
        }

        
        //CustodianRow�I�u�W�F�N�g���擾���� 
        WEB3BondDataManagerService l_bondDataManagerService = 
            (WEB3BondDataManagerService) Services.getService(WEB3BondDataManagerService.class);
        WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = null;
        if (l_orderUnit.getCustodianCode() != null)
        {
            CustodianRow l_custodianRow = l_bondDataManagerService.getCustodian(
                l_bondProduct.getInstitution(), 
                l_orderUnit.getCustodianCode());
            //�J�X�g�f�B�A���ɒl���Z�b�g���� 
            l_adminBondCustodianUnit = 
                this.toCustodian(l_custodianRow);
        }
        
        if (l_adminBondCustodianUnit != null)
        {
            l_adminBondOrderExecInfo.custodianInfo = l_adminBondCustodianUnit;
        }
        
        if (l_bondOrderUnitRow.getOrderExecStatusIsSet())
        {    
            //�������敪���������̏ꍇ
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {
                //��萔�� �@@�@@�@@���@@�������P��.get����()
                if (l_bondOrderUnitRow.getQuantityIsSet())
                {
                    l_adminBondOrderExecInfo.execFaceAmount =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
                }
                //���P�� �@@�@@�@@�� �������P��.get�w�l()
                if (!l_bondOrderUnitRow.getLimitPriceIsNull())
                {
                    l_adminBondOrderExecInfo.execPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
                }
                //�������P��.���^�C�v == �O���� �̏ꍇ
                if(BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
                {
                    //������.is�O�݌�����true�̏ꍇ
                    if (l_bondProduct.isForeignCurrency())
                    {
                        WEB3BondOrderManager l_bondOrderManager =
                            (WEB3BondOrderManager)l_finApp.getTradingModule(
                                ProductTypeEnum.BOND).getOrderManager();
                        //�@@�@@���בփ��[�g���������}�l�[�W��.get�בփ��[�g(������,
                        // ������ʔ���, ���ϋ敪, ���͈בփ��[�g, is���v�Z)
                        //�@@�@@[����]
                        //�@@�@@�@@�������F������
                        //�@@�@@�@@��������ʔ���F�������P��.get��������ʔ���
                        //�@@�@@�@@���ϋ敪�F�������P��.get���ϋ敪
                        //�@@�@@�@@���͈בփ��[�g�F�@@0
                        //�@@�@@�@@is���v�Z�F�@@true
                        BigDecimal l_bdExecFxRate =
                            l_bondOrderManager.getFxRate(
                                l_bondProduct,
                                l_orderUnit.getBondOrderTypeJudge(),
                                l_orderUnit.getSettlementDiv(),
                                new BigDecimal("0"),
                                true);
                         l_adminBondOrderExecInfo.execFxRate =
                             WEB3StringTypeUtility.formatNumber(l_bdExecFxRate.doubleValue());

                    }

                    //  �����@@�� ������.get����(������)
                    Date l_datExecDate = l_bondProduct.getExecDate(l_datBizDate);
                    if (l_datExecDate != null)
                    {
                        l_adminBondOrderExecInfo.domesticExecutionDate = l_datExecDate;
                    }

                    // ��n��   �� ������.get��n��(����, ��������ʔ���)
                    Date l_datDeliveryDate = l_bondProduct.getDeliveryDate(
                        l_adminBondOrderExecInfo.domesticExecutionDate,
                        l_orderUnit.getBondOrderTypeJudge());
                    if (l_datDeliveryDate != null)
                    {
                        l_adminBondOrderExecInfo.domesticDeliveryDate = l_datDeliveryDate;
                    }

                    // ������.is�O��������true�̏ꍇ
                    if (l_bondProduct.isForeignBond())
                    {
                        //���n��������������.get���n������(������)
                        Date l_datForeignBizDate = l_bondProduct.getForeignBizDate(l_datBizDate);

                        //���n���� �@@�@@�� ������.get���n����(���n������)
                        if (l_datForeignBizDate != null)
                        {
                        l_adminBondOrderExecInfo.foreignExecutionDate =
                            l_bondProduct.getForeignExecDate(
                                l_bondProduct.getForeignExecDate(l_datForeignBizDate));
                        }


                        //���n��n�� �@@�@@�� ������.get���n��n��(���n����, ��������ʔ���)
                        Date l_datForeignDeliveryDate = l_bondProduct.getForeignDeliveryDate(
                            l_adminBondOrderExecInfo.foreignExecutionDate,
                            l_orderUnit.getBondOrderTypeJudge());

                        if (l_datForeignDeliveryDate != null)
                        {
                            l_adminBondOrderExecInfo.foreignDeliveryDate = l_datForeignDeliveryDate;
                        }
                    }

                    WEB3GentradeAccountManager l_web3GentradeAccountManager =
                        (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    Branch l_branch = null;
                    try
                    {
                        l_branch = l_web3GentradeAccountManager.getBranch(l_orderUnit.getBranchId());
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    //������ �� ������.get������(����, ��������ʔ���, ���ϋ敪, ���X)
                    l_adminBondOrderExecInfo.paymentDate =
                        l_bondProduct.getPaymentDate(
                            l_adminBondOrderExecInfo.domesticExecutionDate,
                            l_orderUnit.getBondOrderTypeJudge(),
                            l_orderUnit.getSettlementDiv(),
                            l_branch);

                }
            }

            //�������敪�������ς̏ꍇ
            if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {

                //��萔�� �@@�@@�@@���@@�������P��.get��萔��()
                if (!l_bondOrderUnitRow.getExecutedQuantityIsNull())
                {
                    l_adminBondOrderExecInfo.execFaceAmount =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());
                }

                //���P�� �@@�@@�@@�� �������P��.get���P��()
                if (!l_bondOrderUnitRow.getExecutedPriceIsNull())
                {
                    l_adminBondOrderExecInfo.execPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedPrice());
                }
            }
            //�������敪 == ���� �܂��� ���^�C�v �� �O���� �̏ꍇ
            if(WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()) ||
                !BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
            {
                //���בփ��[�g �� �������P��.get���בփ��[�g()
                if (!l_bondOrderUnitRow.getExecFxRateIsNull())
                {
                    l_adminBondOrderExecInfo.execFxRate =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecFxRate());
                }

                //�����@@�@@�@@�@@�@@ �� �������P��.get����()
                if (l_orderUnit.getExecDate() != null)
                {
                    l_adminBondOrderExecInfo.domesticExecutionDate = l_orderUnit.getExecDate();
                }

                //���n���� �@@�@@�� �������P��.get���n����()
                if (l_orderUnit.getForeignExecDate() != null)
                {
                    l_adminBondOrderExecInfo.foreignExecutionDate = l_orderUnit.getForeignExecDate();
                }

                //��n�� �@@�@@�@@�@@�@@�� �������P��.get��n��()
                l_adminBondOrderExecInfo.domesticDeliveryDate = l_orderUnit.getDeliveryDate();

                //���n��n�� �@@�@@�� �������P��.get���n��n��()
                if (l_orderUnit.getForeignDeliveryDate() != null)
                {
                    l_adminBondOrderExecInfo.foreignDeliveryDate = l_orderUnit.getForeignDeliveryDate();
                }

                //�������@@�@@�@@ �@@�@@�� �������P��.get������()
                l_adminBondOrderExecInfo.paymentDate = l_orderUnit.getPaymentDate();
            }
        }
        //�T�j�������������I�u�W�F�N�g��ԋp�B
        log.exiting(STR_METHOD_NAME);
        return l_adminBondOrderExecInfo;
    }
    
    /**
     * (to�����)<BR>
     * �������������쐬����<BR>
     * <BR>
     * �P�j���������I�u�W�F�N�g�𐶐�<BR>
     * <BR>
     * �Q�j�������������I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g����<BR>
     * <BR>
     * ��萔��=����n����v�Z����.get����()<BR>
     * ���P��=����n����v�Z����.get�P��()<BR>
     * ���בփ��[�g=����n����v�Z����.get�בփ��[�g()<BR>
     * <BR>
     * ����=���������.get����()<BR>
     * ���n����=���������.get���n����()<BR>
     * ��n��=���������.get��n��()<BR>
     * ���n��n��=���������.get���n��n��()<BR>
     * ������=���������.get������() <BR>
     * <BR>
     * ��������i�O�݁j=����n����v�Z����.get��������i�O�݁j<BR>
     * ��������i�~�݁j=����n����v�Z����.get��������i�~�݁j<BR>
     * �o�ߗ��q�i�O�݁j=����n����v�Z����.get�o�ߗ��q�i�O�݁j<BR>
     * �o�ߗ��q�i�~�݁j=����n����v�Z����.get�o�ߗ��q�i�~�݁j<BR>
     * ��n����i�O�݁j=����n����v�Z����.get��n����i�O�݁j<BR>
     * ��n����i�~�݁j=����n����v�Z����.get��n����i�~�݁j<BR>
     * �o�ߓ���=����n����v�Z����.get�o�ߓ���()<BR>
     * �����=����n����v�Z����.get�����()<BR>
     * <BR>
     * �J�X�g�f�B�A��=�J�X�g�f�B�A��<BR>
     * <BR>
     * �������敪=�g���������P��.get�������敪()<BR>
     * (�g���������P�ʂ�null�̏ꍇ�����Z�b�g���Ȃ��B)<BR>
     * <BR>
     * �x���敪�ꗗ=����n����v�Z����.get�x���敪�ꗗ()<BR>
     * ��String�̔z��ɕϊ���������
     * @@param l_executeDateInfo - (���������)<BR>
     * ���������
     * @@param l_calcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����
     * @@param l_custodianUnit - (�J�X�g�f�B�A��)<BR>
     * �J�X�g�f�B�A��
     * @@param l_orderUnit - (�g���������P��)<BR>
     * �������P��
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44DAD1D40282
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(
        WEB3BondExecuteDateInfo l_executeDateInfo, 
        WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondCustodianUnit l_custodianUnit, 
        WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "toOrderExecInfo(WEB3BondExecuteDateInfo, "+
            "WEB3BondEstimatedPriceCalcResult, "+
            "WEB3AdminBondCustodianUnit, "+
            "WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_executeDateInfo == null || l_calcResult == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
                
        //�P�j���������I�u�W�F�N�g�𐶐�
        WEB3AdminBondOrderExecInfo l_adminBondOrderExecInfo = 
            new WEB3AdminBondOrderExecInfo();      
               
        //�Q�j�������������I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g���� 
        //��萔��=����n����v�Z����.get����() 
        if (l_calcResult.getQuantity() != null)
        {
            l_adminBondOrderExecInfo.execFaceAmount = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getQuantity().doubleValue());
        }
        
        //���P��=����n����v�Z����.get�P��()
        if (l_calcResult.getPrice() != null)
        {
            l_adminBondOrderExecInfo.execPrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getPrice().doubleValue());
        }

        //���בփ��[�g=����n����v�Z����.get�בփ��[�g() 
        if (l_calcResult.getFxRate() != null)
        {
            l_adminBondOrderExecInfo.execFxRate = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getFxRate().doubleValue());
        }
        
        //����=���������.get����() 
        l_adminBondOrderExecInfo.domesticExecutionDate = l_executeDateInfo.getExecuteDate();
        
        //���n����=���������.get���n����() 
        l_adminBondOrderExecInfo.foreignExecutionDate = l_executeDateInfo.getForeignExecuteDate();
        
        //��n��=���������.get��n��() 
        l_adminBondOrderExecInfo.domesticDeliveryDate = l_executeDateInfo.getDeliveryDate();
        
        //���n��n��=���������.get���n��n��() 
        l_adminBondOrderExecInfo.foreignDeliveryDate = l_executeDateInfo.getForeignDeliveryDate();
        
        //������=���������.get������() 
        l_adminBondOrderExecInfo.paymentDate = l_executeDateInfo.getPaymentDate();
        
        //��������i�O�݁j=����n����v�Z����.get��������i�O�݁j
        if (l_calcResult.getForeignTradePrice() != null)
        {
            l_adminBondOrderExecInfo.foreignTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getForeignTradePrice().doubleValue());
        }
        
        //��������i�~�݁j=����n����v�Z����.get��������i�~�݁j 
        if (l_calcResult.getTradingPrice() != null)
        {
            l_adminBondOrderExecInfo.yenTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getTradingPrice().doubleValue());
        }
        
        //�o�ߗ��q�i�O�݁j=����n����v�Z����.get�o�ߗ��q�i�O�݁j 
        if (l_calcResult.getForeignAccruedInterest() != null)
        {
            l_adminBondOrderExecInfo.foreignAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getForeignAccruedInterest().doubleValue()); 
        }
        
        //�o�ߗ��q�i�~�݁j=����n����v�Z����.get�o�ߗ��q�i�~�݁j 
        if (l_calcResult.getAccruedInterest() != null)
        {
            l_adminBondOrderExecInfo.yenAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getAccruedInterest().doubleValue());
        }
        
        //��n����i�O�݁j=����n����v�Z����.get��n����i�O�݁j
        if (l_calcResult.getForeignEstimatedPrice() != null)
        {
            l_adminBondOrderExecInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getForeignEstimatedPrice().doubleValue()); 
        }
        
        //��n����i�~�݁j=����n����v�Z����.get��n����i�~�݁j 
        if (l_calcResult.getEstimatedPrice() != null)
        {
            l_adminBondOrderExecInfo.yenDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getEstimatedPrice().doubleValue());
        }

        //�o�ߓ���=����n����v�Z����.get�o�ߓ���() 
        if (l_calcResult.getElapsedDays() != null)
        {
            l_adminBondOrderExecInfo.elapsedDays = l_calcResult.getElapsedDays().intValue() + "";
        }

        //�����=����n����v�Z����.get�����() 
        if ( l_calcResult.getCalcBaseDays() != null)
        {
            l_adminBondOrderExecInfo.calcBaseDays = l_calcResult.getCalcBaseDays().intValue() + "";
        }

        //�J�X�g�f�B�A��=�J�X�g�f�B�A�� 
        l_adminBondOrderExecInfo.custodianInfo = l_custodianUnit;

        //�g���������P�ʂ�null�̏ꍇ�����Z�b�g���Ȃ��B
        //�������敪=�g���������P��.get�������敪() 
        if ( l_orderUnit != null)
        {        
            l_adminBondOrderExecInfo.executionState = l_orderUnit.getOrderExecStatus();
        }
        
        //�x���敪�ꗗ=����n����v�Z����.get�x���敪�ꗗ()��String�̔z��ɕϊ���������
        if (l_calcResult.getWarningDivList() != null && 
            !l_calcResult.getWarningDivList().isEmpty())
        {
            String[] l_strWarningDiv = new String[l_calcResult.getWarningDivList().size()]; 
            l_calcResult.getWarningDivList().toArray(l_strWarningDiv);
            l_adminBondOrderExecInfo.warningDiv = l_strWarningDiv;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_adminBondOrderExecInfo;
    }
    
    /**
     * (to�������)<BR>
     * to�������<BR>
     * <BR>
     * �V�[�P���X�}�uto�������v�Q��
     * @@param l_bondProduct - (������)<BR>
     * ������
     * @@return webbroker3.bd.message.WEB3AdminBondProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CB02CC0154
     */
    public WEB3AdminBondProductInfo toProductInfo(WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toProductInfo(WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        WEB3AdminBondFxRateInfo l_adminBondFxRateInfo = null;
        
        //1.1 is�O�݌�( )����true�̏ꍇ
        if (l_bondProduct.isForeignCurrency())
        {
            
            //1.2.1 get�ʉ�()
            WEB3GentradeCurrency l_currency = l_bondProduct.getCurrency();
            
            if (l_currency != null)
            {
                //1.2.2 �בփ��[�g�I�u�W�F�N�g�𐶐�
                l_adminBondFxRateInfo = new WEB3AdminBondFxRateInfo();
                
                //1.2.3 �v���p�e�B�Z�b�g
                //���t��ב�=get�ʉ�.get���t��בփ��[�g() 
                double l_dblCurrentBuyBaseRate = l_currency.getBuyBaseRate();
                l_adminBondFxRateInfo.buyBaseFx = 
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentBuyBaseRate);
                
                //���p��ב�=get�ʉ�.get���t��בփ��[�g() 
                double l_dblCurrentSellBaseRate = l_currency.getSellBaseRate();
                l_adminBondFxRateInfo.sellBaseFx = 
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentSellBaseRate);
                
                //���t���ב�=get�ʉ�.get���t���בփ��[�g() 
                double l_dblCurrentBuyExecRate = l_currency.getBuyExecRate();
                l_adminBondFxRateInfo.buyExecFx = 
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentBuyExecRate);
                
                //���p���ב�=get�ʉ�.get���t���בփ��[�g()
                double l_dblCurrentSellExecRate = l_currency.getSellExecRate();       
                l_adminBondFxRateInfo.sellExecFx =
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentSellExecRate);
            }       
        }
        
        WEB3BondDataManagerService l_bondDataManagerService = 
            (WEB3BondDataManagerService) Services.getService(WEB3BondDataManagerService.class);
        
        //1.3 get�J�X�g�f�B�A��
        WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = null;
        if (l_bondProduct.getCustodianCode() != null)
        {
            CustodianRow l_custodianRow = l_bondDataManagerService.getCustodian(
                l_bondProduct.getInstitution(), 
                l_bondProduct.getCustodianCode());
            //1.4 to�J�X�g�f�B�A��
            l_adminBondCustodianUnit = this.toCustodian(l_custodianRow);
        }

        
        BondProductRow l_bondProductRow = (BondProductRow)l_bondProduct.getDataSourceObject();
        
        //1.5 �������I�u�W�F�N�g�𐶐�
        WEB3AdminBondProductInfo l_adminBondProductInfo = new WEB3AdminBondProductInfo();
        
        // �����R�[�h(WEB3)= ������.get�����R�[�h(WEB3) 
        l_adminBondProductInfo.productCode = l_bondProduct.getProductCode();
        
        //������=������.get������() 
        l_adminBondProductInfo.productName = l_bondProduct.getProductName();
        
        //���t�P��=������.get���t�P��() 
        if (!l_bondProductRow.getBuyPriceIsNull())
        {
            l_adminBondProductInfo.buyPrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        }
        
        //���p�P��=������.get���p�P��() 
        if (!l_bondProductRow.getSellPriceIsNull())
        {
            l_adminBondProductInfo.sellPrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getSellPrice());
        }
        
        //����=������.getCoupon() 
        l_adminBondProductInfo.coupon = 
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());
        
        //�ʉ݃R�[�h=������.get�ʉ݃R�[�h()
        l_adminBondProductInfo.currencyCode = l_bondProduct.getCurrencyCode();
        
        //���s��=������.getIssueDate() 
        l_adminBondProductInfo.issueDate = l_bondProduct.getIssueDate();
        
        //�N�ԗ�����=������.getYearlyInterestPayments()
        l_adminBondProductInfo.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";
        
        //������1=������.get������1() 
        l_adminBondProductInfo.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();
        
        //������2=������.get������2() 
        l_adminBondProductInfo.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();
        
        //���ғ�=������.getMaturityDate() 
        l_adminBondProductInfo.maturityDate = l_bondProduct.getMaturityDate();
        
        //�J�X�g�f�B�A�� = to�J�X�g�f�B�A��
        l_adminBondProductInfo.custodianInfo = l_adminBondCustodianUnit;
        
        //�בփ��[�g = ���������בփ��[�g�I�u�W�F�N�g 
        l_adminBondProductInfo.fxRateInfo = l_adminBondFxRateInfo; 
        
        //�d�����̈בփ��[�g=������.get�d�����̈בփ��[�g 
        if (!l_bondProductRow.getBuyingFxRateIsNull())
        {
            l_adminBondProductInfo.fxRateAtStock = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyingFxRate());
        }

        log.exiting(STR_METHOD_NAME);
        return l_adminBondProductInfo;
    }
    
    /**
     * (get�⏕����)<BR>
     * get�⏕����<BR>
     * <BR>
     * �P�j�@@�g���A�J�E���g�}�l�[�W��.get�ڋq�i�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�j���Ă�<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F����.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�@@�@@�@@�F����.���X�R�[�h<BR>
     * �@@�@@�@@�ڋq�R�[�h�@@�@@�@@�F����.�ڋq�R�[�h<BR>
     * �@@�@@���ڋq�����݂��Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01035<BR>
     * <BR>
     * �Q�j�@@���Y�q���M�p�q���ǂ������肷��B <BR>
     * <BR>
     * �@@�@@�@@�ڋq.is�M�p�����J��("0�F�w��Ȃ�")==true�̏ꍇ�͐M�p�q�ł���B <BR>
     * �@@�@@�@@�ȊO�A��M�p�q�ł���B <BR>
     * <BR>
     * �R�j�@@�g���A�J�E���g�}�l�[�W��.getSubAccount( )�ɂāA<BR>
     * �Y���ڋq�̕⏕�����I�u�W�F�N�g���擾����B <BR>
     * �@@[getSubAccount( )�̈���] <BR>
     * �@@�M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.�M�p��������iEQUITY_MARGIN_SUB_ACCOUNT�j <BR>
     * �@@��M�p�q�̏ꍇ�F�@@SubAccountTypeEnum.������������iEQUITY_SUB_ACCOUNT�j<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@return SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 44CB073C0124
     */
    public SubAccount getSubAccount(String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getSubAccount(String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        SubAccount  l_subAccount = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        
        //�P�j�@@�g���A�J�E���g�}�l�[�W��.get�ڋq�i�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�j���Ă� 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();      

        try
        {
            l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Y������ڋq�����݂��܂���B");
        }
        
        //�Q�j�@@���Y�q���M�p�q���ǂ������肷��B  
        boolean l_blnFlag = l_mainAccount.isMarginAccountEstablished(
            WEB3GentradeRepaymentDivDef.DEFAULT);  
        log.debug("l_blnFlag=======" + l_blnFlag);
        try
        {
            if(l_blnFlag)
            {
                //�@@�M�p�q�̏ꍇ�F�@@
                //SubAccountTypeEnum.�M�p��������iEQUITY_MARGIN_SUB_ACCOUNT�j  
                l_subAccount = l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                //�@@��M�p�q�̏ꍇ�F�@@
                //SubAccountTypeEnum.������������iEQUITY_SUB_ACCOUNT�j  
                l_subAccount = l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
             }
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
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
    
    /**
     * (reset��n���)<BR>
     * reset��n���<BR>
     * <BR>
     * ����n����v�Z���ʂɖ����̑Ή����ڂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�����.��������i�~�݁j�I��null�̏ꍇ�A<BR>
     * �@@����n����v�Z����.��������i�~�݁j�������.��������i�~�݁j
     * <BR>
     * �Q�j�����.�o�ߗ��q(�~��) �I��null�̏ꍇ�A<BR>
     * �@@����n����v�Z����.�o�ߗ��q(�~��)�������.�o�ߗ��q(�~��)
     * <BR>
     * �R�j����n����v�Z����.calc��n����i�~�݁j���R�[�����A��n����i�~�݁j���擾����B <BR>
     * <BR>
     * �S�j�����.��n���(�~��) != null �̏ꍇ <BR>
     *    �S�|�P�j�����.��n���(�~��)  != �R�j�Ŏ擾������n����i�~�݁j�̏ꍇ <BR>
     * �@@          ����n����v�Z����.add�x���敪(�x���敪)���R�[������B<BR>
     *             [����]�x���敪�F��n�������v���Ȃ�<BR>
     *    �S�|�Q�j����n����v�Z����.��n���(�~��)�������.��n���(�~��)<BR>
     * <BR>
     * �T�j������.is�O�݌�����true�̏ꍇ<BR>
     *    �T�|�P�j�����.��������i�O�݁j�I��null�̏ꍇ�A<BR>
     *             ����n����v�Z����.��������i�O�݁j�������.��������i�O�݁j<BR>
     * <BR>
     *    �T�|�Q�j�����.�o�ߗ��q(�O��) �I��null�̏ꍇ�A<BR>
     *             ����n����v�Z����.�o�ߗ��q(�O��)�������.�o�ߗ��q(�O��)<BR>
     *<BR>
     *    �T�|�R�j����n����v�Z����.calc��n����i�O�݁j���R�[�����A��n����i�O�݁j���擾����B<BR>
     * <BR>
     *    �T�|�S�j�����.��n���(�O��) != null �̏ꍇ<BR>
     *         �T�|�S�|�P�j�����.��n���(�O��)  !=  �T�|�R�j�Ŏ擾������n����i�O�݁j�̏ꍇ<BR>
     *                       ����n����v�Z����.add�x���敪(�x���敪)���R�[������B<BR>
     *                       [����]�x���敪�F��n�������v���Ȃ� <BR>
     *         �T�|�S�|�Q�j����n����v�Z����.��n���(�O��)�������.��n���(�O��)<BR>
     *<BR>        
     * �U�j�����.�o�ߓ����I��null�̏ꍇ�A<BR>
     *  �@@�@@����n����v�Z����.�o�ߓ����������.�o�ߓ���<BR>
     *<BR>
     * �V�j�����.������I��null�̏ꍇ�A<BR>
     *  �@@����n����v�Z����.������������.�����<BR>
     *<BR>
     * 8�j����n����v�Z���ʂ�Ԃ�<BR>    
     * @@param l_calcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����
     * @@param l_orderExecInfo - (�����)<BR>
     * �����
     * @@param l_product - (������)<BR>
     * ������
     * 
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 44CB197501AE
     */
    public WEB3BondEstimatedPriceCalcResult resetEstimatedPrice(
            WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondProduct l_product) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "resetEstimatedPrice(WEB3BondEstimatedPriceCalcResult, "+
            "WEB3AdminBondOrderExecInfo, "+
            "WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderExecInfo == null || l_calcResult == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�P�j�����.��������i�~�݁j�I��null�̏ꍇ�A 
        //    ����n����v�Z����.��������i�~�݁j�������.��������i�~�݁j 
        if (l_orderExecInfo.yenTradePrice != null)
        {
            BigDecimal l_bdYenTradePrice = 
                new BigDecimal(l_orderExecInfo.yenTradePrice);
            l_calcResult.setTradingPrice(l_bdYenTradePrice);
        }
        
        //�Q�j�����.�o�ߗ��q(�~��) �I��null�̏ꍇ�A 
        //  �@@����n����v�Z����.�o�ߗ��q(�~��)�������.�o�ߗ��q(�~��) 
        if (l_orderExecInfo.yenAccruedInterest != null)
        {
            BigDecimal l_bdYenAccruedInterest = 
                new BigDecimal(l_orderExecInfo.yenAccruedInterest);
            l_calcResult.setAccruedInterest(l_bdYenAccruedInterest);
        }
        
        //�R�j����n����v�Z����.calc��n����i�~�݁j���R�[�����A��n����i�~�݁j���擾����B
        BigDecimal l_bdEstimatedPrice = l_calcResult.calcEstimatedPrice();
        
        
        //�S�j�����.��n���(�~��) != null �̏ꍇ
        if (l_orderExecInfo.yenDeliveryPrice != null)
        {
            //�S�|�P�j�����.��n���(�~��)  != �R�j�Ŏ擾������n����i�~�݁j�̏ꍇ 
            //����n����v�Z����.add�x���敪(�x���敪)���R�[������B 
            BigDecimal l_bdYenDeliveryPrice = new BigDecimal(l_orderExecInfo.yenDeliveryPrice);
            
            if (l_bdYenDeliveryPrice.compareTo(l_bdEstimatedPrice) != 0)
            {
                //�@@[����]�x���敪�F��n�������v���Ȃ� 
                l_calcResult.addWarningDiv(WEB3BondWarningDivDef.ESTIMATED_PRICE_DIFFERENCE);  
            }     
            //�S�|�Q�j����n����v�Z����.��n���(�~��)�������.��n���(�~��)
            l_calcResult.setEstimatedPrice(l_bdYenDeliveryPrice);   
        }
        
        //�T�j������.is�O�݌�����true�̏ꍇ 
        if (l_product.isForeignCurrency())
        {
            //�T�|�P�j�����.��������i�O�݁j�I��null�̏ꍇ�A
            if (l_orderExecInfo.foreignTradePrice != null)
            {
                //����n����v�Z����.��������i�O�݁j�������.��������i�O�݁j 
                l_calcResult.setForeignTradePrice(new BigDecimal(l_orderExecInfo.foreignTradePrice));
            }
            
            //�T�|�Q�j�����.�o�ߗ��q(�O��) �I��null�̏ꍇ�A 
            if (l_orderExecInfo.foreignAccruedInterest != null)
            {
                //����n����v�Z����.��������i�O�݁j�������.��������i�O�݁j 
                l_calcResult.setForeignAccruedInterest(new BigDecimal(l_orderExecInfo.foreignAccruedInterest));
            }
            
            //�T�|�R�j����n����v�Z����.calc��n����i�O�݁j���R�[�����A��n����i�O�݁j���擾����B 
            BigDecimal l_bdForeignEstimatedPrice = l_calcResult.calcForeignEstimatedPrice();
            
            //�T�|�S�j�����.��n���(�O��) != null �̏ꍇ 
            if (l_orderExecInfo.foreignDeliveryPrice != null)
            {
                BigDecimal l_bdForeignDeliveryPrice = 
                    new BigDecimal(l_orderExecInfo.foreignDeliveryPrice);
                //�T�|�S�|�P�j�����.��n���(�O��)  !=  �T�|�R�j�Ŏ擾������n����i�O�݁j�̏ꍇ 
                //����n����v�Z����.add�x���敪(�x���敪)���R�[������B 
                if (l_bdForeignDeliveryPrice.compareTo(l_bdForeignEstimatedPrice) != 0)
                {
                    //�@@[����]�x���敪�F��n�������v���Ȃ�
                    l_calcResult.addWarningDiv(WEB3BondWarningDivDef.ESTIMATED_PRICE_DIFFERENCE); 
                }    
                //�T�|�S�|�Q�j����n����v�Z����.��n���(�O��)�������.��n���(�O��)   
                l_calcResult.setForeignEstimatedPrice(l_bdForeignDeliveryPrice);
            }
        }
        
        //�U�j�����.�o�ߓ����I��null�̏ꍇ�A 
        //  ����n����v�Z����.�o�ߓ����������.�o�ߓ��� 
        if (l_orderExecInfo.elapsedDays != null)
        {
            l_calcResult.setElapsedDays(Integer.valueOf(l_orderExecInfo.elapsedDays));
        }
        
        //�V�j�����.������I��null�̏ꍇ�A 
        //����n����v�Z����.������������.�����
        if (l_orderExecInfo.calcBaseDays != null)
        {
            l_calcResult.setCalcBaseDays(Integer.valueOf(l_orderExecInfo.calcBaseDays));
        }
        
        //8�j����n����v�Z���ʂ�Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }
    
    /**
     * (reset�������)<BR>
     * reset�������<BR>
     * <BR>
     * �P�j����.���������.����������.�����.����<BR>
     * <BR>
     * �Q�j����.�����.���n�����@@!= null�̏ꍇ<BR>
     * �@@�@@����.���������.���n����������.�����.���n����<BR>
     * <BR>
     * �R�j������������ʔ���.is���� == false �̏ꍇ<BR>
     * �@@�@@�R�|�P�j����.�����.��n���@@== null �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01079<BR>
     * <BR>
     * �@@�@@�R�|�Q�j����ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@����.���������.��n��������.�����.��n��<BR>
     * <BR>
     * �S�j����.�����.���n��n���@@!= null�̏ꍇ�A<BR>
     * �@@�@@����.���������.���n��n��������.�����.���n��n��<BR>
     * <BR>
     * �T�j����.�����.�������@@!= null�̏ꍇ�A<BR>
     * �@@�@@����.���������.������������.�����.������<BR>
     * <BR>
     * �U�j����.�����.�������@@== null�̏ꍇ�A<BR>
     * �@@�U�|�P�j�����X�ʏ����𐶐�����B
     * �@@�@@�@@�@@�@@[����]
     * �@@�@@�@@�@@�@@�@@���XID�F����.���X.���XID
     * �@@�U�|�Q�j�i�����X�ʏ���.get�������ݒ�敪����'�����'<BR>
     * �@@�@@�@@�@@�@@�܂��́@@�����X�ʏ���.get�������ݒ�敪����'�o�^���'�j<BR>
     * �@@�@@�@@�@@�@@���@@����.��������ʔ���.is���偁��true�@@�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@����.���������.������������.������.get������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�����@@�@@�@@�@@�@@�@@�@@�@@�F�@@����.���������.����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��������ʔ���F�@@����.��������ʔ���<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���ϋ敪�@@�@@�@@�@@�@@�@@�F�@@����.���ϋ敪<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���X�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�@@����.���X<BR>
     * �@@�U�|�R�j��L�ȊO�̏ꍇ�A
     * �@@�@@�@@�@@�@@�@@����.���������.������������.���������.��n��
     * �V�j����������Ԃ��B
     * @@param l_orderExecInfo - (�����)<BR>
     * �����
     * @@param l_executeDateInfo - (���������)<BR>
     * ���������
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϋ敪
     * @@param l_branch - (���X)<BR>
     * ���X
     * @@return webbroker3.bd.WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CB13390090
     */
    public WEB3BondExecuteDateInfo resetExecuteDateInfo(
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondExecuteDateInfo l_executeDateInfo,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        WEB3BondProduct l_bondProduct, String l_strSettleDiv, Branch l_branch) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "resetExecuteDateInfo(WEB3AdminBondOrderExecInfo, "+
            "WEB3BondExecuteDateInfo, " +
            "WEB3BondOrderTypeJudge, " +
            "WEB3BondProduct, " +
            "String, " +
            "Branch ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderExecInfo == null || l_executeDateInfo == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�P�j����.���������.����������.�����.���� 
        l_executeDateInfo.setExecuteDate(l_orderExecInfo.domesticExecutionDate);
        
        //�Q�j����.���������.���n����������.�����.���n���� 
        if (l_orderExecInfo.foreignExecutionDate != null)
        {
            l_executeDateInfo.setForeignExecuteDate(l_orderExecInfo.foreignExecutionDate);
        }

        //�R�j������������ʔ���.is���� == false �̏ꍇ
        //�R�|�P�j����.�����.��n���@@== null �̏ꍇ�A��O���X���[����B
        //�R�|�Q�j����ȊO�̏ꍇ�A����.���������.��n��������.�����.��n��
        if (!l_bondOrderTypeJudge.isRecruitOrder())
        {
            if (l_orderExecInfo.domesticDeliveryDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "��n�������w��ł��B");
            }
            else
            {
                l_executeDateInfo.setDeliveryDate(l_orderExecInfo.domesticDeliveryDate);
            }
        }

        //�S�j����.���������.���n��n��������.�����.���n��n�� 
        if (l_orderExecInfo.foreignDeliveryDate != null)
        {
            l_executeDateInfo.setForeignDeliveryDate(l_orderExecInfo.foreignDeliveryDate);
        }
        
        //�T�j����.�����.�������@@!= null�̏ꍇ�A 
        //����.���������.������������.�����.������
        Date l_datPaymentDate = null;
        if (l_orderExecInfo.paymentDate != null)
        {
            l_datPaymentDate = l_orderExecInfo.paymentDate;
        }
        //�U�j����.�����.�������@@== null�̏ꍇ�A 
        else
        {
            //�U�|�P�j�����X�ʏ����𐶐�����B
            //[����]
            //���XID�F����.���X.���XID
            WEB3BondBranchCondition l_branchCondition =
                new WEB3BondBranchCondition(l_branch.getBranchId());
            
            //�U�|�Q�j�i�����X�ʏ���.get�������ݒ�敪����'�����'
            //�܂��́@@�����X�ʏ���.get�������ݒ�敪����'�o�^���'�j
            //���@@����.��������ʔ���.is���偁��true�@@�̏ꍇ�A
            //����.���������.������������.������.get������
            //[����]
            //�����F����.���������.����
            //��������ʔ���F����.��������ʔ���
            //���ϋ敪�F����.���ϋ敪
            //���X�F����.���X
            if ((WEB3PaymentDateDetDivDef.EXECUTE_DATE_BASE.equals(l_branchCondition.getPaymentDateSetDiv())
                || WEB3PaymentDateDetDivDef.REGIST_DATE_BASE.equals(l_branchCondition.getPaymentDateSetDiv()))
                && l_bondOrderTypeJudge.isRecruitOrder())
            {
                l_datPaymentDate = l_bondProduct.getPaymentDate(l_executeDateInfo.getExecuteDate(),
                                                                l_bondOrderTypeJudge,
                                                                l_strSettleDiv,
                                                                l_branch);
            }
            //�U�|�R�j��L�ȊO�̏ꍇ�A
            //����.���������.������������.���������.��n��
            else
            {
                l_datPaymentDate = l_executeDateInfo.getDeliveryDate();
            }
        }
        l_executeDateInfo.setPaymentDate(l_datPaymentDate);
     
        
        //�V�j����������Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_executeDateInfo;
    }
    
    /**
     * (get�������b�N�����{�^���敪)<BR>
     * get�������b�N�����{�^���敪<BR>
     * <BR>
     * �������b�N�����{�^���敪�����肵�A�Y������l��Ԃ��B<BR>
     * <BR>
     * �P�j�߂�l�̕ϐ��Ɂu��\���v���Z�b�g����B<BR>
     * <BR>
     * �Q�j�����X�ʏ����𐶐�����B<BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@���XID�F�����P��.get���XID<BR>
     * <BR>
     * �R�j�����X�ʏ���.get�������b�N�g�p�敪����'�������b�N�敪���g�p����'�̏ꍇ<BR>
     * �@@�R�|�P�j�����P��.�������敪���������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����P��.�������b�N�敪�������b�N�������̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�߂�l�̕ϐ��Ɂu���b�N�{�^���v���Z�b�g����B<BR>
     * �@@�R�|�Q�j�����P��.�������敪���������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�����P��.�������b�N�敪�������b�N���̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�߂�l�̕ϐ��Ɂu�����{�^���v���Z�b�g����B<BR>
     * <BR>
     * �S�j�߂�l��Ԃ��B
     * @@param l_orderUnit - (�����P��)<BR>
     * �g���������P��
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D31F2D020D
     */
    public String getOrderLockButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOrderLockCancelButtonDiv(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
                
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //2) �����X�ʏ����𐶐�����B
        BondBranchConditionRow l_bondBranchConditionRow = null;
        
        String l_strLockButton = WEB3BondLockReleaseButtonDivDef.DISPLAY_NO;

        try
        {
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_orderUnit.getBranchId());
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
        
        String l_strOrderLockUseDiv = "";
        if (l_bondBranchConditionRow != null)
        {
            l_strOrderLockUseDiv = l_bondBranchConditionRow.getOrderLockUseDiv();
        }
        
        //�R�j�����X�ʏ���.get�������b�N�g�p�敪����'�������b�N�敪���g�p����'�̏ꍇ
        if (WEB3BondOrderLockUseDivDef.EXCEPT.equals(l_strOrderLockUseDiv))
        {
            // �R�|�P�j�����P��.�������敪��������� 
            //�����P��.�������b�N�敪�������b�N�������̏ꍇ�A 
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()) && 
                WEB3LockStatusDef.RELEASING.equals(l_orderUnit.getLockStatus()))
            {
                l_strLockButton = WEB3BondLockReleaseButtonDivDef.LOCK_BUTTON;
            }
            
            //  �R�|�Q�j�����P��.�������敪��������� 
            //�����P��.�������b�N�敪�������b�N���̏ꍇ�A 
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()) && 
                WEB3LockStatusDef.LOCKING.equals(l_orderUnit.getLockStatus()))
            {
                l_strLockButton = WEB3BondLockReleaseButtonDivDef.RELEAS_BUTTON;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strLockButton;
    }
    
    /**
     * (get���ύX�{�^���敪)<BR>
     * get���ύX�{�^���敪<BR>
     * <BR>
     * ���ύX�{�^���敪�����肵�A�Y������l��Ԃ��B<BR>
     * <BR>
     * �P�j�����P��.���^�C�v == �O���� �̏ꍇ�A�ȉ��̏������s�Ȃ��B<BR>
     * �@@�@@�P�|�P�j�����P��.�������敪���������̏ꍇ�A�u���{�^���v��Ԃ��B<BR>
     * <BR>
     * �@@�@@�P�|�Q�j�����P��.�������敪�������ς̏ꍇ�A�u�ύX�{�^���v��Ԃ��B<BR>
     * <BR>
     * �@@�@@�P�|�R�j��L�ȊO�̏ꍇ�A�u��\���v��Ԃ��B<BR>
     * <BR>
     * �Q�j�����P��.���^�C�v �� �O���� �̏ꍇ�A�u��\���v��Ԃ��B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �g���������P��
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D3248F0352
     */
    public String getExecuteChangButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getExecuteChangButtonDiv(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        String l_strExecuteChangButtonDiv = null;

        //�P�j�����P��.���^�C�v == �O���� �̏ꍇ�A�ȉ��̏������s�Ȃ��B
        if (BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
        {
            //�P�j�����P��.�������敪���������̏ꍇ�A�u���{�^���v��Ԃ��B 
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {
                l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.EXECUTE_BUTTON;
            }
            
            //�Q�j�����P��.�������敪�������ς̏ꍇ�A�u�ύX�{�^���v��Ԃ��B 
            else if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {
                l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.CHANGE_BUTTON;
            }
            else
            {
                //�R�j��L�ȊO�̏ꍇ�A�u��\���v��Ԃ��B
                l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.DISPLAY_NO;
            }
        }
        else
        {
            //�Q�j�����P��.���^�C�v �� �O���� �̏ꍇ�A�u��\���v��Ԃ��B
            l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.DISPLAY_NO;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strExecuteChangButtonDiv;
    }
    
    /**
     * (get����{�^���敪)<BR>
     * get����{�^���敪<BR>
     * <BR>
     * ����{�^���敪�����肵�A�Y������l��Ԃ��B<BR>
     * <BR>
     * �P�j�����P��.�������敪�I������ς̏ꍇ�A�u����{�^���v��Ԃ��B<BR>
     * <BR>
     * �Q�j��L�ȊO�̏ꍇ�A�u��\���v��Ԃ��B
     * @@param l_orderUnit - (�����P��)<BR>
     * �g���������P��
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D325C8030E
     */
    public String getCancelButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getCancelButtonDiv(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strOrderExecStatus = "";
        
        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
       
        l_strOrderExecStatus = l_orderUnit.getOrderExecStatus();
   
        String l_strLockButton;
        //�P�j�����P��.�������敪�I������ς̏ꍇ�A�u����{�^���v��Ԃ��B
        if (!WEB3BondOrderExecStatusDef.CANCELED.equals(l_strOrderExecStatus))
        {
            l_strLockButton = WEB3BondCancelDivDef.CANCEL_BUTTON;
        }
        
        //�Q�j��L�ȊO�̏ꍇ�A�u��\���v��Ԃ��B
        else
        {
            l_strLockButton = WEB3BondCancelDivDef.DISPLAY_NO;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strLockButton;
    }
    
    /**
     * (to�J�X�g�f�B�A���ꗗ)<BR>
     * to�J�X�g�f�B�A���ꗗ<BR>
     * <BR>
     * �P�j�߂�lList�𐶐�����B<BR>
     * <BR>
     * �Q�j����.�J�X�g�f�B�A�����X�g�̗v�f����Loop����B<BR>
     * �@@�Q�|�P�j�J�X�g�f�B�A�����X�g�̗v�f���J�X�g�f�B�A��Row�ɃL���X�g����B<BR>
     * �@@�Q�|�Q�jthis.to�J�X�g�f�B�A�����Ă�<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�J�X�g�f�B�A��Row�F�J�X�g�f�B�A��Row<BR>
     * �@@�Q�|�R�j�擾�����J�X�g�f�B�A����߂�lList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�߂�lList��Ԃ�
     * @@param l_lisCustodian - (�J�X�g�f�B�A�����X�g)<BR>
     * �J�X�g�f�B�A�����X�g<BR>
     * <BR>
     *  CustodianRow��List
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44D93BA302DE
     */
    public List toCustodianList(List l_lisCustodian) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toCustodianList(List)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSize = 0;
        if (l_lisCustodian != null)
        {
            l_intSize = l_lisCustodian.size();
        }
        //�P�j�߂�lList�𐶐�����B 
        List l_lisCustodianList = new ArrayList();
        
        //�Q�j����.�J�X�g�f�B�A�����X�g�̗v�f����Loop����B 
        for (int i = 0; i < l_intSize; i++)
        {
            //�Q�|�P�j�J�X�g�f�B�A�����X�g�̗v�f���J�X�g�f�B�A��Row�ɃL���X�g����B
            CustodianRow l_custodianRow = (CustodianRow) l_lisCustodian.get(i);
            
            //�Q�|�Q�jthis.to�J�X�g�f�B�A�����Ă� 
            WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = this.toCustodian(l_custodianRow);
            
            //�Q�|�R�j�擾�����J�X�g�f�B�A����߂�lList�ɒǉ�����B 
            l_lisCustodianList.add(l_adminBondCustodianUnit);           
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisCustodianList;
    }
    
    /**
     * (to�J�X�g�f�B�A��)<BR>
     * to�J�X�g�f�B�A��<BR>
     * <BR>
     * �P�j�J�X�g�f�B�A���𐶐�����B<BR>
     * <BR>
     * �Q�j�J�X�g�f�B�A��.�J�X�g�f�B�A���R�[�h������.�J�X�g�f�B�A��Row.get�J�X�g�f�B�A���R�[�h<BR>
     * �@@�@@�J�X�g�f�B�A��.�J�X�g�f�B�A�����@@�@@������.�J�X�g�f�B�A��Row.get�J�X�g�f�B�A����<BR>
     * <BR>
     * �R�j�J�X�g�f�B�A����Ԃ�
     * @@param l_row - (�J�X�g�f�B�A��Row)<BR>
     * �J�X�g�f�B�A��Row
     * 
     * @@return WEB3AdminBondCustodianUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D948D801E4
     */
    public WEB3AdminBondCustodianUnit toCustodian(CustodianRow l_row) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toCustodian(CustodianRow)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�X�g�f�B�A���𐶐�����B 
        WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = 
            new WEB3AdminBondCustodianUnit();
        
        //�J�X�g�f�B�A��.�J�X�g�f�B�A���R�[�h������.�J�X�g�f�B�A��Row.get�J�X�g�f�B�A���R�[�h 
        if (l_row != null)
        {
            l_adminBondCustodianUnit.custodianCode = l_row.getCustodianCode();
            
            //�J�X�g�f�B�A��.�J�X�g�f�B�A�����@@�@@������.�J�X�g�f�B�A��Row.get�J�X�g�f�B�A���� 
            l_adminBondCustodianUnit.custodianName = l_row.getCustodianName();
        }
        
        //�R�j�J�X�g�f�B�A����Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_adminBondCustodianUnit;
    }
    
    
    /**
     * (to�ڋq���)<BR>
     * �������ڋq�����쐬���� <BR>
     *  <BR>
     * �P�j�ڋq���I�u�W�F�N�g�𐶐�  <BR>
     *  <BR>
     * �Q�j�ڋq���I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g  <BR>
     *     ���X�R�[�h�F����.�ڋq.getBranch().get���X�R�[�h()  <BR>
     *     �ڋq�R�[�h�F����.�ڋq.get�\���ڋq�R�[�h() <BR>
     *     �ڋq���F����.�ڋq.get�ڋq�\����()  <BR>
     *  <BR>    
     * �R�j�ڋq���I�u�W�F�N�g��ԋp <BR> 
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq
     * @@return WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D94A2D01B5
     */
    public WEB3AdminBondAccountInfo toAccountInfo(MainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "toAccountInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        //�P�j�ڋq���I�u�W�F�N�g�𐶐�
        WEB3AdminBondAccountInfo l_adminBondAccountInfo = 
            new WEB3AdminBondAccountInfo(); 

        //�Q�j�ڋq���I�u�W�F�N�g�ɉ��L�̃v���p�e�B���Z�b�g
        //   ���X�R�[�h�F����.�ڋq.getBranch().get���X�R�[�h()
        l_adminBondAccountInfo.branchCode = 
            l_mainAccount.getBranch().getBranchCode();
        
        //�ڋq�R�[�h�F����.�ڋq.get�\���ڋq�R�[�h()
        MainAccountRow l_row = (MainAccountRow)l_mainAccount.getDataSourceObject();
        WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_row);
        
        l_adminBondAccountInfo.accountCode = l_gentradeMainAccount.getDisplayAccountCode();      

        //�ڋq���F����.�ڋq.get�ڋq�\����()
        l_adminBondAccountInfo.accountName = l_gentradeMainAccount.getDisplayAccountName();
        
        // �R�j�ڋq���I�u�W�F�N�g��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_adminBondAccountInfo;
    }
}@
