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
filename	WEB3BondOrderManagerReusableValidationsCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������R���ʃ`�F�b�N(WEB3BondOrderManagerReusableValidationsCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ����� (���u) �V�K�쐬
                 : 2006/10/12 ꎉ�   (���u) WEB�V�J���W���̌������̑Ή��inewBigDecimal�����j
Revesion History : 2007/7/25 ���g (���u) �d�l�ύX�E���f��No.223
Revesion History : 2007/7/26 ������ (���u) �d�l�ύX�E���f��No.232
*/

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondProductTypeOrderManagerReusableValidations;

import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondBranchRecruitLimitBranchCodeDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�������R���ʃ`�F�b�N)<BR>
 * �������R���ʃ`�F�b�N�N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0 
 */
public class WEB3BondOrderManagerReusableValidationsCheck extends BondProductTypeOrderManagerReusableValidations
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderManagerReusableValidationsCheck.class);       

    /**
     * @@roseuid 44E336220119
     */
    public WEB3BondOrderManagerReusableValidationsCheck() 
    {
     
    }
    
    /**
     * �X�[�p�[�N���X�Ɏ��g�̃C���X�^���X��o�^����B <BR>
     * <BR>
     * �i�v���O�C�����������ɃR�[�������j <BR>
     * <BR>
     * --- <BR>
     * super.setInstance(this); <BR>
     * --- <BR>
     * @@roseuid 4280923D020C
     */
    public static void register() 
    {
        //�������R���ʃ`�F�b�N.setInstance()���R�[������
        WEB3BondOrderManagerReusableValidationsCheck.setInstance(
            new WEB3BondOrderManagerReusableValidationsCheck());
    }
    
    /**
     * (validate���p�\����)<BR>
     * validate���p�\����<BR>
     * <BR>
     * �P�j���|�W�V�����}�l�[�W��.get�ۗL���Y(����ID, �⏕����ID, ����ID ,�ŋ敪)<BR>
     * �@@���ۗL���Y���擾����B<BR>
     * �@@[����] <BR>
     * �@@�@@����ID�@@�@@�@@�F����.�⏕����.get����ID <BR>
     * �@@�@@�⏕����ID�@@�F����.�⏕����.get�⏕����ID <BR>
     * �@@�@@����ID�@@�@@�@@�F����.������.get����ID <BR>
     * �@@�@@�ŋ敪�@@�@@�@@�F����.�g�����V�K�������e.get�ŋ敪<BR> 
     * <BR>
     * �Q�j���p�\���ʁ��ۗL���Y.get���ʁ|�ۗL���Y.get���b�N������<BR>
     * <BR>
     * �R�j�g�����V�K�������e.get����()�������p�\���ʂł��邱�Ƃ��`�F�b�N����B<BR>
     * �@@��L�ȊO�̏ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_01803<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_bondNewOrderSpec - (�g�����V�K�������e)<BR>
     * �g�����V�K�������e<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CC8096008C
     */
    public void validateTransferedPossibleQuantity(
        SubAccount l_subAccount, 
        WEB3BondNewOrderSpec l_bondNewOrderSpec,
        WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateTransferedPossibleQuantity(SubAccount, WEB3BondNewOrderSpec, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
       
        if (l_bondNewOrderSpec == null || l_bondProduct == null || l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }
        
        //�P�j���|�W�V�����}�l�[�W��.get�ۗL���Y(����ID, �⏕����ID, ����ID ,�ŋ敪)
        //���ۗL���Y���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = l_bondPositionManager.getAsset(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(), 
            l_bondProduct.getProductId(), 
            l_bondNewOrderSpec.getTaxType());
        
        //�Q�j���p�\���ʁ�get�ۗL���Y.���ʁ|�ۗL���Y.get���b�N������
        double l_dblQuantity = 0;
        if (l_asset != null)
        {    
            l_dblQuantity = 
                new BigDecimal(String.valueOf(l_asset.getQuantity())).subtract(
                    new BigDecimal(String.valueOf(l_asset.getLockedQuantity()))).doubleValue();
        }
        
        //�R�j�g�����V�K�������e.get����()�������p�\���ʂł��邱�Ƃ��`�F�b�N����B
        if (new BigDecimal(String.valueOf(l_bondNewOrderSpec.getQuantity())).compareTo(
        	new BigDecimal(String.valueOf(l_dblQuantity))) == 1)
        {
            log.debug("�c���s���G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�c���s���G���[�B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���ϋ敪)<BR>
     * �w�肵�����ϋ敪�̌��ς��\�ł��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j����.���ϋ敪 == �O�� �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@����.������.is�O�݌�()�̖߂�l == false�@@�̏ꍇ�A<BR>
     * �@@��O���X���[����B<BR>
     * �@@���u�w��̌��ϕ��@@�ł͎���ł��܂���v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02095<BR>
     * @@param l_strSettlementDiv - (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2E4140195
     */
    public void validateSettlementDiv(String l_strSettlementDiv, WEB3BondProduct l_bondProduct)
        throws WEB3BaseException 
          
    {
        final String STR_METHOD_NAME = "validateSettlementDiv(String, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }
        
        //�P�j����.���ϋ敪 == �O�� �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettlementDiv))
        {
            if (!l_bondProduct.isForeignCurrency())
            {
                log.debug("�O�݌��ϕs�\�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02095,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�O�݌��ϕs�\�G���[�B");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate����)<BR>
     * ���������ʂ�validate�`�F�b�N<BR>
     * <BR>
     * �P�j����.���ʁ@@�����@@����.������.�Œ�z�ʂł��邱�ƁB<BR>
     * �@@��L�ȊO�̏ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02541<BR>
     * <BR>
     * �Q�j����.���ʁ@@�����@@����.������.�ō��z�ʂł��邱�ƁB�B(*)<BR>
     * �@@��L�ȊO�̏ꍇ�́A��O���X���[����B<BR>
     * �@@(*)����.������.�ō��z�ʂ�null�łȂ��ꍇ�̂݃`�F�b�N����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02542<BR>
     * <BR>
     * �R�j����.���ʂ�����.������.get�\���P�ʂ̐����{�ł��邱�ƁB<BR>
     * �@@��L�ȊO�̏ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02543<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2F5DA0109
     */
    public void validateQuantity(double l_dblOrderQuantity, WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validateQuantity(double, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }
        
        //�P�j����.���ʁ@@�����@@����.������.�Œ�z�ʂł��邱�ƁB
        //��L�ȊO�̏ꍇ�́A��O���X���[����B
        if (new BigDecimal(String.valueOf(l_dblOrderQuantity)).compareTo(
            new BigDecimal(String.valueOf(l_bondProduct.getMinFaceAmount()))) == -1)
        {
            log.debug("�z�ʋ��z���Œ�z�ʂ�菬�����ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02541,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z���Œ�z�ʂ�菬�����ł��B");
        }
        
        //�Q�j����.���ʁ@@�����@@����.������.�ō��z�ʂł��邱�ƁB
        //��L�ȊO�̏ꍇ�́A��O���X���[����B
        //(*)����.������.�ō��z�ʂ�null�łȂ��ꍇ�̂݃`�F�b�N����B
        BondProductRow l_bondProductRow = 
            (BondProductRow)l_bondProduct.getDataSourceObject();
        if (!l_bondProductRow.getMaxFaceAmountIsNull())
        {
            if (new BigDecimal(String.valueOf(l_dblOrderQuantity)).compareTo(
                new BigDecimal(String.valueOf(l_bondProduct.getMaxFaceAmount()))) == 1)
            {
                log.debug("�z�ʋ��z���ō��z�ʂ𒴂��Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02542,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�z�ʋ��z���ō��z�ʂ𒴂��Ă��܂��B");
            }
        }
        
        //�R�j����.���ʂ�����.������.get�\���P�ʂ̐����{�ł��邱�ƁB
        //��L�ȊO�̏ꍇ�́A��O���X���[����B
        BigDecimal l_bdOrderQuantity = new BigDecimal(String.valueOf(l_dblOrderQuantity));
        BigDecimal l_bdTradeUnit = new BigDecimal(String.valueOf(l_bondProduct.getTradeUnit()));
        BigDecimal l_bdZero = new BigDecimal("0");
        if (l_bdZero.compareTo(
            l_bdOrderQuantity.subtract(
                l_bdOrderQuantity.divide(
                    l_bdTradeUnit, 0,
                    BigDecimal.ROUND_DOWN).multiply(l_bdTradeUnit))) != 0)
        {
            log.debug("�z�ʋ��z���\���P�ʂ̐����{�ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02543,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z���\���P�ʂ̐����{�ł͂���܂���B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������g)<BR>
     * �������ʂ��������g�͈͓̔��ł��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�g���������}�l�[�W��.get���������ώc�����R�[�����āA<BR>
     * �@@�@@���ώc�����擾����B<BR>
     * <BR>
     * �@@�@@[get���������ώc��()�ɓn������]<BR>
     * �@@�@@�@@�@@�،���ЁF�@@����.�،����<BR>
     * �@@�@@�@@�@@�����R�[�h�iWEB3)�F�@@����.������.�����R�[�h(WEB3)<BR>
     * <BR>
     * �Q�j����.�������� == 0 �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@����.������.�������g�@@-�@@�P�j�Ŏ擾�������ώc�� <= 0 �̏ꍇ�A<BR>
     * �@@�@@��O�F�u���łɎ������g�ɒB���Ă��܂��B�v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02544<BR>
     * <BR>
     * �R�j�Q�j�ȊO�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@����.������.�������g�@@-�@@�P�j�Ŏ擾�������ώc���@@-�@@����.�������ʁ@@���@@0�@@�̏ꍇ�A<BR>
     * �@@�@@��O�F�u�������g�𒴉߂��Ă��܂��B�v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02545<BR>
     * @@param l_institution - (�،����)<BR>
     * �،����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_strOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2F77700CA
     */
    public void validateAutoExecLimit(
        Institution l_institution,
        WEB3BondProduct l_bondProduct, 
        String l_strOrderQuantity) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAutoExecLimit(Institution, WEB3BondProduct, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }
        
        //�P�j�g���������}�l�[�W��.get���������ώc�����R�[�����āA
        //���ώc�����擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        double l_dblBalance = 
            l_bondOrderManager.getBondProductExecutedBalance(l_institution, l_bondProduct.getProductCode());
        
        //�Q�j����.�������� == 0 �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        BigDecimal l_bdZero = new BigDecimal("0");
        if (new BigDecimal(l_strOrderQuantity).compareTo(l_bdZero) == 0)
        {
            if (new BigDecimal(String.valueOf(l_bondProduct.getAutoExecLimit())).subtract(
                new BigDecimal(String.valueOf(l_dblBalance))).doubleValue() <= 0)
            {
                log.debug("���łɎ������g�ɒB���Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02544,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���łɎ������g�ɒB���Ă��܂��B");
            }
        }
        //�R�j�Q�j�ȊO�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        else
        {
            if (new BigDecimal(String.valueOf(l_bondProduct.getAutoExecLimit())).subtract(
                new BigDecimal(String.valueOf(l_dblBalance))).subtract(
                    new BigDecimal(l_strOrderQuantity)).doubleValue() < 0)
            {
                log.debug("�������g�𒴉߂��Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02545,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������g�𒴉߂��Ă��܂��B");
            }      
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���p�\��)<BR>
     * ���p�\�����ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j������.getIssueDate()>���������.get�������̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_02618<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D9567E00CB
     */
    public void validateSellPossibleDate(
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = "validateSellPossibleDate(WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondExecuteDateInfo == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }
        
        //�P�j������.getIssueDate()>���������.get�������̏ꍇ�A
        //��O���X���[����B
        if (WEB3DateUtility.compare(l_bondProduct.getIssueDate(), l_bondExecuteDateInfo.getBizDate()) > 0)
        {
            log.debug("���s���O�ɔ��p���邱�Ƃ͂ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02618,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���s���O�ɔ��p���邱�Ƃ͂ł��܂���B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��������\���)<BR>
     * ����������\�ȏ�Ԃł��邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j����.�������P��.�������敪 == "����" ���́A"�����"�̏ꍇ�A <BR>
     * �@@�@@��O�u���̒����͎���ł��܂���v���X���[����B <BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00155<BR>
     * <BR>
     * �Q�j����.�������P��.�������b�N�敪 == "���b�N��"�̏ꍇ�A<BR>
     * �@@�@@��O�u���̒����͎���ł��܂���v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00155<BR>
     * �R�j����.�������P��.���^�C�v == "�O����"�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�R�|�P�j����.�������P��.������ �� ������ԊǗ�.get������()�̖߂�l �̏ꍇ�A<BR>
     * �@@�@@��O�u���̒����͎���ł��܂���v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00155<BR>
     * <BR>
     * �S�j����.�������P��.���^�C�v �� "�O����"�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@�@@�S�|�P�j����.�������P��.������ �� ������ԊǗ�.get������()�̖߂�l �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@��O�u���̒����͎���ł��܂���v���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag: BUSINESS_ERROR_00155<BR>
     * <BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44DBF46B00A1
     */
    public void validateTransferedPossibleDays(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateTransferedPossibleDays(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");
        }
        
        //�P�j����.�������P��.�������敪 == "����" ���́A"�����"�̏ꍇ�A 
        //��O���X���[����B
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()) || 
            WEB3BondOrderExecStatusDef.CANCELED.equals(l_bondOrderUnit.getOrderExecStatus()))
        {
            log.debug("�Y������������s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������������s�ł��B");
        }

        //�Q�j����.�������P��.�������b�N�敪 == "���b�N��"�̏ꍇ�A
        //��O���X���[����B
        String l_strLockStatus = l_bondOrderUnit.getLockStatus() + "";
        if (WEB3LockStatusDef.LOCKING.equals(l_strLockStatus))
        {
            log.debug("�Y������������s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������������s�ł��B");
        }

        //�R�j����.�������P��.���^�C�v == "�O����"�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        //�R�|�P�j����.�������P��.������ �� get������()�̖߂�l �̏ꍇ�A
        //��O�u���̒����͎���ł��܂���v���X���[����B
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            if (WEB3DateUtility.compareToDay(l_datBizDate,
                WEB3DateUtility.getDate(l_bondOrderUnitRow.getBizDate(), "yyyyMMdd")) != 0)
            {
                log.debug("�Y������������s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y������������s�ł��B");
            }
        }

        //�S�j����.�������P��.���^�C�v �� "�O����"�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        //�S�|�P�j����.�������P��.������ �� ������ԊǗ�.get������()�̖߂�l �̏ꍇ�A
        //��O�u���̒����͎���ł��܂���v���X���[����B
        if (!BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            if (WEB3DateUtility.compareToDay(l_datBizDate,
                WEB3DateUtility.getDate(l_bondOrderUnitRow.getBizDate(), "yyyyMMdd")) > 0)
            {
                log.debug("�Y������������s�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y������������s�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����������g)<BR>
     * �������ʂ�����������g�͈͓̔��ł��邩�ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�����X�ʏ����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@[�R���X�g���N�^�ւ̈���]<BR>
     * �@@���XID�F����.���XID <BR>
     * <BR>
     * �Q�j�����X�ʏ����I�u�W�F�N�g.get����g���X�ʊǗ��敪��<BR>
     * �@@�@@�u1�F���X�ʊǗ�����v�̏ꍇ<BR>
     * <BR>
     * �@@�Q�|�P�j���v���_�N�g�}�l�[�W��.create���������X�ʉ���g��񃁃\�b�h<BR>
     * �@@���R�[������B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@����ID�F����.������.����ID<BR>
     * �@@�@@�@@�،���ЃR�[�h�F����.������.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F����.���XID����擾�������X�I�u�W�F�N�g.���X�R�[�h<BR>
     * <BR>
     * �R�j�����X�ʏ����I�u�W�F�N�g.get����g���X�ʊǗ��敪��<BR>
     * �@@�@@�u0�F���X�ʊǗ����Ȃ��v�̏ꍇ<BR>
     * <BR>
     * �@@�R�|�P�j���v���_�N�g�}�l�[�W��.create���������X�ʉ���g��񃁃\�b�h<BR>
     * �@@���R�[������B<BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@����ID�F����.������.����ID<BR>
     * �@@�@@�@@�،���ЃR�[�h�F����.������.�،���ЃR�[�h<BR>
     * �@@�@@�@@���X�R�[�h�F�"---":�S���X�<BR>
     * <BR>
     * �S�j����.�������� == 0 �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@���������X�ʉ���g���[0].WEB3����g - <BR>
     * �@@�@@���������X�ʉ���g���[0].�������z���v<= 0 �̏ꍇ�A<BR>
     * �@@��O�F�u���łɉ���g�ɒB���Ă��܂��B�v���X���[����B<BR>
     * <BR>
     * �T�j�S�j�ȊO�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR>
     * <BR>
     * �@@���������X�ʉ���g���[0].WEB3����g - <BR>
     * �@@�@@���������X�ʉ���g���[0].�������z���v - ����.�������� < 0 �̏ꍇ�A<BR>
     * �@@��O�F�u����g�𒴉߂��Ă��܂��B�v���X���[����B <BR>
     * <BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_dblQuantity - (��������)<BR>
     * ��������<BR>
     * @@throws WEB3BaseException
     */
    public void validateDomesticBondRecruitLimit(
        long l_lngBranchId,
        WEB3BondProduct l_bondProduct,
        double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDomesticBondRecruitLimit(long, WEB3BondProduct, double)";
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

        //�P�j�����X�ʏ����I�u�W�F�N�g�𐶐�����B
        WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_lngBranchId);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_branchCode = null;
        //�Q�j�����X�ʏ����I�u�W�F�N�g.get����g���X�ʊǗ��敪���u1�F���X�ʊǗ�����v�̏ꍇ
        if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT.equals(
            l_branchCondition.getBranchRecruitLimitDiv()))
        {
            //���X�R�[�h�F����.���XID����擾�������X�I�u�W�F�N�g.���X�R�[�h
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                l_branchCode = l_accountManager.getBranch(l_lngBranchId).getBranchCode();
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //�R�j�����X�ʏ����I�u�W�F�N�g.get����g���X�ʊǗ��敪���u0�F���X�ʊǗ����Ȃ��v�̏ꍇ
        if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT.equals(
            l_branchCondition.getBranchRecruitLimitDiv()))
        {
            //���X�R�[�h�F�"---":�S���X�
            l_branchCode = WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH;
        }

        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos = null;
        if (l_branchCode != null)
        {
            //���v���_�N�g�}�l�[�W��.create���������X�ʉ���g��񃁃\�b�h���R�[������B
            //  [����]
            //      ����ID�F����.������.����ID
            //      �،���ЃR�[�h�F����.������.�،���ЃR�[�h
            //      ���X�R�[�h�F����.���XID����擾�������X�I�u�W�F�N�g.���X�R�[�h
            WEB3BondProductManager l_bondProductManager =
                (WEB3BondProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getProductManager();
            l_bondDomesticBranchRecruitLimitInfos =
                l_bondProductManager.createAdminBondDomesticRecruitLimitInfo(
                    l_bondProduct.getProductId(),
                    l_bondProduct.getInstitution().getInstitutionCode(),
                    l_branchCode);
        }

        //�S�j����.�������� == 0 �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        //���������X�ʉ���g���[0].WEB3����g - ���������X�ʉ���g���[0].�������z���v <= 0 �̏ꍇ�A
        //��O�F�u���łɉ���g�ɒB���Ă��܂��B�v���X���[����B
        if (l_bondDomesticBranchRecruitLimitInfos.length == 0)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���");
        }
        BigDecimal l_bdZero = new BigDecimal("0");
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);
        BigDecimal l_bdWeb3RecruitLimit = new BigDecimal(l_bondDomesticBranchRecruitLimitInfos[0].web3RecruitLimit);
        BigDecimal l_bdOrderAmountTotal = new BigDecimal(l_bondDomesticBranchRecruitLimitInfos[0].orderAmountTotal);
        if (l_bdQuantity.compareTo(l_bdZero) == 0)
        {
            if (l_bdWeb3RecruitLimit.subtract(
                l_bdOrderAmountTotal).compareTo(l_bdZero) <= 0)
            {
                log.debug("���łɉ���g�ɒB���Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02888,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���łɉ���g�ɒB���Ă��܂��B");
            }
        }
        //�T�j�S�j�ȊO�̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
        //���������X�ʉ���g���[0].WEB3����g - ���������X�ʉ���g���[0].�������z���v - ����.�������� < 0 �̏ꍇ�A
        //��O�F�u����g�𒴉߂��Ă��܂��B�v���X���[����B
        else
        {
            if (l_bdWeb3RecruitLimit.subtract(
                l_bdOrderAmountTotal).subtract(l_bdQuantity).compareTo(l_bdZero) < 0)
            {
                log.debug("����g�𒴉߂��Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02889,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����g�𒴉߂��Ă��܂��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
