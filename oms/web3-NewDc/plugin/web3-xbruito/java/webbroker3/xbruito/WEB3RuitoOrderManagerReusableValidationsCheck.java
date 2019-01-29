head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderManagerReusableValidationsCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������R���ʃ`�F�b�N�N���X(WEB3RuitoOrderManagerReusableValidationsCheck)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ��O�� (���u) �V�K�쐬
                   2004/12/02 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito;


import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3StopDef;
import webbroker3.common.define.WEB3TradedDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �ݓ������R���ʃ`�F�b�N�N���X<BR>
 */
public class WEB3RuitoOrderManagerReusableValidationsCheck
    extends RuitoProductTypeOrderManagerReusableValidations
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3RuitoOrderManagerReusableValidationsCheck.class);

    String l_strMessage;

    /**
     * �ڋq���ݓ�����������J�݂��Ă��邩���`�F�b�N����B<BR>
     * ����.�⏕����.getMainAccount().getDataSourceObject()<BR>
     *     .get�ݓ������J�݋敪()<BR>
     *     �̖߂�l���h1�F�ݓ��������J�݁h�̏ꍇ�͗�O���X���[����B<BR>
     *     �G���[�^�O�FBUSINESS_ERROR_00256<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406B741200CE
     */
    public void validateRuitoTradedAccountEstablish(SubAccount l_subAccount)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateRuitoTradedAccountEstablish(" +
            "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
    
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        log.entering(STR_METHOD_NAME);
        log.debug("AccountId = " + l_subAccount.getAccountId());
        
        //get�ݓ������J�݋敪()
        MainAccountRow l_mainAccountRow =
            (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
        if (WEB3CumulativeAccountDivDef.NOT_ESTABLISH.equals(
            l_mainAccountRow.getRuitoAccOpenDiv())) 
        {
            log.debug("�ݐϓ����������J�݂Ȃ��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00256,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ݐϓ����������J�݂Ȃ��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidateTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * ����\�����̃`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�g���ݓ�����������擾����B <BR>
     * <BR>
     * �@@�|�g���ݓ������}�l�[�W�����擾����B<BR>
     * �@@�|�g���ݓ������}�l�[�W��.get�ݓ��������()���R�[������<BR>
     *      �g���ݓ�����������擾����B<BR>
     * �@@�@@�@@�mget�ݓ���������ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@�،���ЁF ����.�g���ݓ�����.getInstitution()<BR>
     * �@@�@@�@@�@@�����R�[�h�F ����.�g���ݓ�����.getProductCode()<BR>
     * �@@�|�擾�ł��Ȃ��ꍇ�́A��O���X���[����B <BR>
     *  �@@�@@�G���[�^�O�F<BR>BUSINESS_ERROR_00250 <BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException <BR>
     * <BR>
     * �Q�j�@@�����~�`�F�b�N <BR>
     *  �|����.is���t��true�̏ꍇ�i���t�̏ꍇ�j�A�g���ݓ�����.get���t�\()=false <BR>
     *      �ł���Η�O���X���[����B <BR>
     *  �|����.is���t��false�̏ꍇ�i���̏ꍇ�j�A�g���ݓ�����.get���\()=false <BR>
     *      �ł���Η�O���X���[����B <BR>
     * <BR>
     * �R�j�@@����K���`�F�b�N<BR>
     * �@@�|����.is���t��true�̏ꍇ�i���t�̏ꍇ�j�A<BR>
     *         �g���ݓ��������.get���t��~()�̒l���h<BR>
     *          1�F��~���h�ł���Η�O���X���[����B <BR>
     * �@@�|����.is���t��false�̏ꍇ�i���̏ꍇ�j�A<BR>
     *        �g���ݓ��������.get����~()�̒l���h<BR>
     *          1�F��~���h�ł���Η�O���X���[����B<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00251<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �S�j�@@�擾�����g���ݓ����������Ԃ��B<BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@param l_isBuy - ���t�̏ꍇ�� true ���A���̏ꍇ�� false ���w�肷��<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoTradedProductImpl
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406BBED3026C
     */
    public WEB3RuitoTradedProduct validateTradedProduct(
        WEB3RuitoProduct l_ruitoProduct,
        boolean isBuy)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedProduct(" +
            "WEB3RuitoProduct l_ruitoProduct, boolean l_isBuy)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        WEB3RuitoTradedProduct l_ruitoTradeProduct = null;
        try
        {
            //�P�j�@@�g���ݓ�����������擾����B
            //�|�g���ݓ������}�l�[�W�����擾����B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);   
            WEB3RuitoProductManager l_ruitoProductManager =   
               (WEB3RuitoProductManager) l_finApp.getTradingModule(
                       ProductTypeEnum.RUITO).getProductManager();
            
            log.debug("InstitutionCode = " + 
                    l_ruitoProduct.getInstitution().getInstitutionCode());
            log.debug("ProductCode = " + l_ruitoProduct.getProductCode());

            l_ruitoTradeProduct = (WEB3RuitoTradedProduct)
                 l_ruitoProductManager.getRuitoTradedProduct(
                    l_ruitoProduct.getInstitution(),
                    l_ruitoProduct.getProductCode());
                       
            //�Q�j�@@�����~�`�F�b�N
            //�|����.is���t��true�̏ꍇ�i���t�̏ꍇ�j�A�g���ݓ�����.get���t�\()=false 
            //�ł���Η�O���X���[����B 
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //WEB3-XBRUITO-REMAIN-A-CD-0004
            log.debug("is���t = " + isBuy);
            
            if (isBuy)
            {
                if (!l_ruitoProduct.isBuyPossible(l_datBizDate))
                {
                    log.debug("�����~�`�F�b�N�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00389,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            //�|����.is���t��false�̏ꍇ�i���̏ꍇ�j�A�g���ݓ�����.get���\()=false 
            //�ł���Η�O���X���[����B 
            else 
            {
                if (!l_ruitoProduct.isSellPossible(l_datBizDate))
                {
                    log.debug("�����~�`�F�b�N�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00389,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            
            //�R�j����K���`�F�b�N
            if (isBuy)
            {
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradeProduct.getBuyStop()))
                {
                    //�|����.is���t��true�̏ꍇ�i���t�̏ꍇ�j�A�g���ݓ��������.get���t��~()�̒l��
                    //�h1�F��~���h�ł���Η�O���X���[����B  
                    log.debug("����K���`�F�b�N�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00251,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���t�̏ꍇ, �g���ݓ��������.get���t��~()�̒l���h1�F��~���h��");                    
                }
            }
            else 
            {
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradeProduct.getSellStop()))
                {
                    //�|����.is���t��false�̏ꍇ�i���̏ꍇ�j�A�g���ݓ��������.get����~()�̒l��
                    //�h1�F��~���h�ł���Η�O���X���[����B
                    log.debug("����K���`�F�b�N�G���[");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00251,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "���̏ꍇ, �g���ݓ��������.get����~()�̒l���h1�F��~���h��");                    
                }
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
                
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�S�j�@@�擾�����g���ݓ����������Ԃ��B  
        log.exiting(STR_METHOD_NAME);
        return l_ruitoTradeProduct;
    }

    /**
     * (validate������z)
     * �������z��������z�͈͓̔����`�F�b�N���s���B<BR>
     * <BR>
     * ������z����@@���@@����.�������z�@@���@@������z����<BR>
     * <BR>
     * �P�j����`�F�b�N���s��<BR>
     * �@@�|������z����̎擾<BR>
     * �@@�@@�@@(*) ����.is���t�̒l��true�̏ꍇ�A
     *           ����.�g���ݓ�����.get���t������z()�̖߂�l���g�p�B<BR>
     * �@@�@@�@@(*) ����.is���t�̒l��false�̏ꍇ�A
     *           ����.�g���ݓ�����.get��������z()�̖߂�l���g�p�B<BR>
     * �@@�|������z����@@���@@����.�������z�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�i������z����G���[�j<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00252<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �Q�j�����`�F�b�N���s��<BR>
     * �@@�|������z�����̎擾<BR>
     * �@@�@@�@@(*) ����.is���t�̒l��true�̏ꍇ�A<BR>
     *          ����.�g���ݓ�����.get���t�������z()�̖߂�l���g�p�B<BR>
     * �@@�@@�@@(*) ����.is���t�̒l��false�̏ꍇ�A<BR>
     *          ����.�g���ݓ�����.get��񉺌����z()�̖߂�l���g�p�B<BR>
     * �@@�|������z�����@@���@@����.�������z�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�i������z�����G���[�j<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00253<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * @@param l_tradedPrice - ���͂��ꂽ�������z<BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@param isBuy - ���t�̏ꍇ��true���A���̏ꍇ��false���w�肷��B<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406BC0030192
     */
    public void validateTradedPrice(
        double l_dbTradedPrice,
        WEB3RuitoProduct l_ruitoProduct,
        boolean isBuy)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedPrice(double l_tradedPrice, " +
            "WEB3RuitoProduct l_ruitoProduct, boolean isBuy)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //�P�j����`�F�b�N���s��
        double l_dblMaxPrice;
        if (isBuy)
        {
            l_dblMaxPrice = l_ruitoProduct.getBuyMaxPrice();
        }
        else
        {
            l_dblMaxPrice = l_ruitoProduct.getSellMaxPrice();
        }
        if (l_dblMaxPrice < l_dbTradedPrice)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00252,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�Q�j�����`�F�b�N���s��
        double l_dblMinPrice;
        if (isBuy)
        {
            l_dblMinPrice = l_ruitoProduct.getBuyMinPrice();
        }
        else
        {
            l_dblMinPrice = l_ruitoProduct.getSellMinPrice();
        }
        if (l_dblMinPrice > l_dbTradedPrice)
        {
            log.debug("������z�����@@���@@����.�������z�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00253,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���꒍�����̔��������ɒ�������Ă��Ȃ����`�F�b�N���A<BR>
     *     ���������������ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �P�j�@@�������̎擾<BR>
     * �@@�|������ԊǗ�.get������()���R�[�����āA���������擾����B<BR>
     * �@@�|�擾������������yyyyMMdd�`���̕�����ɕϊ�����B<BR>
     * <BR>
     * �Q�j�@@����.�����敪�̒l���h1�F���t�h�̏ꍇ<BR>
     * �@@�ȉ��̏����ŗݓ������P�ʃe�[�u�����������A<BR>
     *     �Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00254<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId() AND<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId() AND<BR>
     * �@@�@@�@@����ID = ����.�g���ݓ�����.getProductId()�@@AND<BR>
     * �@@�@@�@@������� = OrderTypeEnum.RUITO_SELL AND<BR>
     * �@@�@@�@@(������� = OrderStatusEnum.��t�ρi�V�K�����j OR<BR>
     * �@@�@@�@@�@@������� = OrderStatusEnum.�����ρi�V�K�����j) AND<BR>
     * �@@�@@�@@������ = �擾�����������iyyyyMMdd�`���j AND<BR>
     * �@@�@@�@@�ݓ��^�C�v = ����.�ݓ��^�C�v<BR>
     * <BR>
     * �R�j�@@����.�����敪�̒l���h3�F�ꕔ���h�̏ꍇ<BR>
     * �@@�|�ȉ��̏����ŗݓ������P�ʃe�[�u�����������A<BR>
     *        �Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00254<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId() AND<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId() AND<BR>
     * �@@�@@�@@����ID = ����.�g���ݓ�����.getProductId()�@@AND<BR>
     * �@@�@@�@@������� = OrderTypeEnum.RUITO_BUY AND<BR>
     * �@@�@@�@@(������� = OrderStatusEnum.��t�ρi�V�K�����j OR<BR>
     * �@@�@@�@@�@@������� = OrderStatusEnum.�����ρi�V�K�����j) AND<BR>
     * �@@�@@�@@������ = �擾�����������iyyyyMMdd�`���j AND<BR>
     * �@@�@@�@@�ݓ��^�C�v = ����.�ݓ��^�C�v<BR>
     * <BR>
     * �@@�|�ȉ��̏����ŗݓ������P�ʃe�[�u�����������A<BR>
     *         �Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00254<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId() AND<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId() AND<BR>
     * �@@�@@�@@����ID = ����.�g���ݓ�����.getProductId()�@@AND<BR>
     * �@@�@@�@@������� = OrderTypeEnum.RUITO_SELL AND<BR>
     * �@@�@@�@@(������� = OrderStatusEnum.��t�ρi�V�K�����j OR<BR>
     * �@@�@@�@@�@@������� = OrderStatusEnum.�����ρi�V�K�����j) AND<BR>
     * �@@�@@�@@������ = �擾�����������iyyyyMMdd�`���j AND<BR>
     * �@@�@@�@@�ݓ��^�C�v = ����.�ݓ��^�C�v AND<BR>
     * �@@�@@�@@�ݓ����敪 = �h2�F�S���h<BR>
     * <BR>
     * �S�j�@@����.�����敪�̒l���h2�F�S�����h�̏ꍇ<BR>
     * �@@�|�ȉ��̏����ŗݓ������P�ʃe�[�u�����������A<BR>
     *      �Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00254<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId() AND<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId() AND<BR>
     * �@@�@@�@@����ID = ����.�g���ݓ�����.getProductId()�@@AND<BR>
     * �@@�@@�@@������� = OrderTypeEnum.RUITO_BUY AND<BR>
     * �@@�@@�@@(������� = OrderStatusEnum.��t�ρi�V�K�����j OR<BR>
     * �@@�@@�@@�@@������� = OrderStatusEnum.�����ρi�V�K�����j) AND<BR>
     * �@@�@@�@@������ = �擾�����������iyyyyMMdd�`���j AND<BR>
     * �@@�@@�@@�ݓ��^�C�v = ����.�ݓ��^�C�v<BR>
     * <BR>
     * �@@�|�ȉ��̏����ŗݓ������P�ʃe�[�u�����������A<BR>
     *      �Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B<BR>
     *  �@@�@@�G���[�^�O�FBUSINESS_ERROR_00254<BR>
     *  �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId() AND<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId() AND<BR>
     * �@@�@@�@@����ID = ����.�g���ݓ�����.getProductId()�@@AND<BR>
     * �@@�@@�@@������� = OrderTypeEnum.RUITO_SELL AND<BR>
     * �@@�@@�@@(������� = OrderStatusEnum.��t�ρi�V�K�����j OR<BR>
     * �@@�@@�@@�@@������� = OrderStatusEnum.�����ρi�V�K�����j) AND<BR>
     * �@@�@@�@@������ = �擾�����������iyyyyMMdd�`���j AND<BR>
     * �@@�@@�@@�ݓ��^�C�v = ����.�ݓ��^�C�v<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_strTradedDiv - �����敪<BR>
     * <BR>
     * 1�F���t<BR>
     * 2�F�S�����<BR>
     * 3�F�ꕔ���<BR>
     * <BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406BD19D00E6
     */
    public void validateSameOrderDateTrade(
        SubAccount l_subAccount,
        String l_strTradedDiv,
        WEB3RuitoProduct l_ruitoProduct)
        throws OrderValidationException, WEB3BaseException    
    {

        String STR_METHOD_NAME =
            "validateTradedPrice(double l_tradedPrice, " +
            "WEB3RuitoProduct l_ruitoProduct, boolean isBuy)";
        log.entering(STR_METHOD_NAME);
            
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        try
        {             
            //�P�j�@@�������̎擾
            String l_strBizDate = WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd");
            RuitoTypeEnum l_ruitoType = l_ruitoProduct.getRuitoType();     
            
            String l_strWhereClause = "";
            
            // �Q�j�@@����.�����敪�̒l���h1�F���t�h�̏ꍇ
            if (WEB3TradedDivDef.BUY.equals(l_strTradedDiv))
            {
                //����.�����敪�̒l���h1�F���t�h�̏ꍇ                
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND "
                        + "product_id = ? AND order_type = ? AND "
                        + "(order_status = ? OR order_status = ?) AND "
                        + "biz_date = ? AND ruito_type = ?";
                log.debug("SQLWhere1 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_SELL.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);

                Object l_bindVars1[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_SELL,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType};

                //DataNetworkException
                List l_lisRows =                    
                    Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_bindVars1);
                    
                if (l_lisRows.size() != 0)
                {
                    log.debug("���꒍�����̔��������ɒ�������Ă��܂�");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "���꒍�����̔��������ɒ�������Ă��܂�");
                }
            }
            //�R�j�@@����.�����敪�̒l���h3�F�ꕔ���h�̏ꍇ
            else if (WEB3TradedDivDef.PARTIALLY_SELL.equals(l_strTradedDiv))
            {
                //�m���������n1
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND "
                        + "product_id = ? AND order_type = ? AND "
                        + "(order_status = ? OR order_status = ?) AND "
                        + "biz_date = ? AND ruito_type = ?";
                        
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_BUY.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);
                    
                Object l_bindVars2[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_BUY,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType};

                List l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars2);
                
                if (l_lisRows.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "���꒍�����̔��������ɒ�������Ă��܂�");
                }
                //�m���������n2
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND " +
                         "product_id = ? AND order_type = ? AND " +
                         "(order_status = ? OR order_status = ?) AND " +
                         "biz_date = ? AND ruito_type = ? AND " +
                         "gp_sell_div = ?";
                         
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_SELL.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType +
                    " AND gp_sell_div = " + WEB3SellDivDef.ALL_DESIGNATE);
                    
                l_lisRows = null;
                Object l_bindVars3[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_SELL,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType,
                        WEB3SellDivDef.ALL_DESIGNATE };

                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars3);
                if (l_lisRows.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���꒍�����̔��������ɒ�������Ă��܂�");
                }
            }
            //�S�j�@@����.�����敪�̒l���h2�F�S�����h�̏ꍇ
            else if (WEB3TradedDivDef.ALL_SELL.equals(l_strTradedDiv))
            {
                //�m���������n1
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND"
                        + " product_id = ? AND order_type = ? AND"
                        + " (order_status = ? OR order_status = ?) AND"
                        + " biz_date = ? AND ruito_type = ?";
               
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_BUY.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);
                    
                Object l_bindVars2[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_BUY,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType};

                List l_lisRowsAllSell1 =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars2);
                if (l_lisRowsAllSell1.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���꒍�����̔��������ɒ�������Ă��܂�");
                }
                //�m���������n2
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND"
                        + " product_id = ? AND order_type = ? AND"
                        + " (order_status = ? OR order_status = ?) AND"
                        + " biz_date = ? AND ruito_type = ?"; 
                        
                        
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_SELL.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);
                    
                List l_lisRowsAllSell2 = null;
                Object l_bindVars3[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_SELL,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType };                      

                l_lisRowsAllSell2 =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars3);
                if (l_lisRowsAllSell2.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���꒍�����̔��������ɒ�������Ă��܂�");
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ݐϓ��������̊J�ݏ󋵂��`�F�b�N���邽�߂ɁA<BR>
     *      �w�肳�ꂽ�����̎c���t�@@�C���Ƀ��R�[�h�����邩�`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * �@@�|�g���ݓ��|�W�V�����}�l�[�W�����擾����B<BR>
     * �@@�|�g���ݓ��|�W�V�����}�l�[�W��.getAsset()���R�[������B<BR>
     * �@@�@@�m.getAsset�ɓn���p�����[�^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�����F ����.�g���ݓ�����<BR>
     * �@@�|getAsset()����O���X���[�����ꍇ�́A��O���X���[����B<BR>
     * �G���[�^�O�FBUSINESS_ERROR_00204<BR>
     * �X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407E5DDA03A9
     */
    public void validateRuitoAccount(SubAccount l_subAccount, 
        WEB3RuitoProduct l_ruitoProduct)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateRuitoAccount(" +
            "SubAccount l_subAccount, WEB3RuitoProduct l_ruitoProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3RuitoPositionManager l_web3RuitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();

            log.debug("l_subAccount.getSubAccountId() = " + l_subAccount.getSubAccountId());
            log.debug("l_ruitoProduct.getProductId() = " + l_ruitoProduct.getProductId());
            
            l_web3RuitoPositionManager.getAsset(l_subAccount, l_ruitoProduct);

        }
        catch (NotFoundException l_ex)
        {
            log.error("�Y������ۗL���Y���s����");                
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ڋq�̐U�����s�����o�^������Ă��邩�`�F�b�N����B<BR>
     * <BR>
     * ����.�⏕����.getMainAccount().getDataSourseObject().getBankAccountRegi()<BR>
     *   �̖߂�l���h0�F���o�^�h�̏ꍇ�́A��O���X���[����B<BR> 
     *  �i�U�����s�����G���[�j<BR>
     * �G���[�^�O�FBUSINESS_ERROR_00366<BR>
     * �X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407E86C2031C
     */
    public void validateBankTransferAccountInsert(SubAccount l_subAccount)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateBankTransferAccountInsert(" +
            "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        MainAccount l_mainAccount = 
            l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow) l_mainAccount.getDataSourceObject();
        String l_strBankAccountRegi = l_mainAccountRow.getBankAccountRegi();

        log.debug("�⏕���� = " + l_subAccount.getAccountId());
        log.debug("�U�����s���� = " + l_strBankAccountRegi);

        if (WEB3BankAccountRegiDef.NOT_REGISTER.equals(l_strBankAccountRegi))
        {
            log.debug("�U�����s�����G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00366,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�U�����s�����G���[");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �������ʂ����\�c���i�ݓ��ۗL���Y����.���\�c���j��<BR>
     *      �����Ă��Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���\�c���擾<BR>
     * �@@�w������̉��\�c�����Z�o����B<BR>
     * <BR>
     * �@@�|�g���ݓ��|�W�V�����}�l�[�W���[.getAseet���R�[�����āA<BR>
     *        �w������ۗ̕L���Y�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�mgetAsset�ɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�����F ����.�g���ݓ�����<BR>
     * �@@�@@getAsset()����O���X���[�����ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@�|�ݓ��ۗL���Y���׃I�u�W�F�N�g�𐶐����A<BR>
     *        �ۗL���Y�̏���ݒ肷��B<BR>
     * �@@�@@�m�ݓ��ۗL���Y���׃I�u�W�F�N�g�ɐݒ肷��l�n<BR>
     * �@@�@@�@@�@@����ID�F �ۗL���Y�I�u�W�F�N�g.getProduct().<BR>
     *            getProductId()�̖߂�l<BR>
     * �@@�@@�@@�@@�c���F �ۗL���Y�I�u�W�F�N�g.getQuantity()�̖߂�l<BR>
     * �@@�@@�@@�@@30�����o�ߎc�������F
     *                �ۗL���Y�I�u�W�F�N�g.getDataSourceObject().<BR>
     *                         get30�����o�ߎc�������̖߂�l<BR>
     * <BR>
     * �@@�|�ݓ��g���|�W�V�����}�l�[�W��.get�ݓ��ۗL���Y����()���R�[�����A<BR>
     * �@@�@@�����P�ʂۗ̕L���Y���ׂ��擾����B<BR>
     * �@@�@@�mget�ݓ��ۗL���Y���ׂɓn���p�����^�n<BR>
     * �@@�@@�@@�⏕�����F ����.�⏕����<BR>
     * �@@�@@�@@�ݓ��ۗL���Y���ׁF ���������ݓ��ۗL���Y����<BR>
     *       2004-06-18 modify,the third param delete
     * �@@�@@�@@���������t�ۃt���O�F<BR>
     * �@@�@@�@@(*) ����.�g���ݓ�����.getRuitoType()�̒l��<BR>
     *               RuitoTypeEnum.�������t�@@���h�̏ꍇ�́h<BR>
     *            2�F�������t�@@���h�h���擾����B<BR>
     * �@@�@@�@@(*) ����.�g���ݓ�����.getRuitoType()�̒l��<BR>
     *                  RuitoTypeEnum.MMF�̏ꍇ�́h<BR>
     *                3�FMMF�h���擾����B<BR>
     * <BR>
     * �Q�j�@@���\�c���Z�o<BR>
     * �@@�|�g���ݓ��|�W�V�����}�l�[�W��.calc���\�c��()���R�[�����A<BR>
     *              ���\�c�����擾����B<BR>
     * �@@�@@�mcalc���\�c���ɓn���p�����^�n<BR>
     * �@@�@@�@@�ݓ��ۗL���Y���ׁF ���������ݓ��ۗL���Y����<BR>
     * <BR>
     * �R�j�@@���\�c���`�F�b�N<BR>
     * �@@�@@�S�����ȊO�̏ꍇ�A�������ʂ����\���`�F�b�N����B<BR>
     * �@@�@@�i����.�w����@@��1�j<BR>
     * <BR>
     * �@@�|�擾�������\�c���@@���@@����.�������ʂ̏ꍇ�́A<BR>
     *            �`�F�b�N�G���[�Ƃ���O���X���[����B<BR>
     * �i���\�c�����߃G���[�j<BR>
     * �G���[�^�O�FBUSINESS_ERROR_00258<BR> 
     * �X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@param l_dblOrderQuantity - ��������<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407F2F6603DC
     */
    public void validateSellPossibleBalance(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dblOrderQuantity)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateSellPossibleBalance(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct, " +
            "double l_dblOrderQuantity)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ruitoProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        //���\�c���擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3RuitoPositionManager l_web3RuitoPositionManager =
            (WEB3RuitoPositionManager) l_finApp
                .getTradingModule(ProductTypeEnum.RUITO)
                .getPositionManager();

        double l_dblSellPossibleBalance =
            l_web3RuitoPositionManager.getSellPossibleBalance(l_subAccount, l_ruitoProduct);
        
        // �Q�j�@@���\�c���`�F�b�N
        if (l_dblSellPossibleBalance < l_dblOrderQuantity)
        {
            log.debug("���\�c�����߃G���[");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00258,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "���\�c�����߃G���[");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �w�肳�ꂽ�������������\���̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ݓ������P��.�������ƌ��݂̔����������������`�F�b�N����B<BR>
     * <BR>
     * �@@�|����.�ݓ������P��.getDataSourceObject()���R�[�����A<BR>
     *         �ݓ������P��Params�I�u�W�F�N�g���擾����B<BR>
     * �@@�|�ݓ������P��Params.getBizDate()���R�[�����A<BR>
     *          �ݓ������P�ʂ̔��������擾����B<BR>
     * �@@�|������ԊǗ�.get������()���R�[�����A���݂̔��������擾����B<BR>
     * �@@�|�ݓ������P�ʂ̔������ƌ��݂̔��������قȂ�ꍇ��<BR>
     *          ��O���X���[����B<BR>
     * �G���[�^�O�FBUSINESS_ERROR_00259<BR>
     * �X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �Q�j�@@�g���K���s�\�Ȏ��ԑт̏ꍇ�A�w�肳�ꂽ�����̒�����Ԃ��h<BR>
     *           3�F�����ρi�V�K�����j�h�ɂȂ��Ă��邩�`�F�b�N����B<BR>
     * �@@�|������ԊǗ�.is�g���K���s()���R�[�����A<BR>
     *         �߂�l��true�̏ꍇ�͈ȍ~�̏������s���B<BR>
     * �@@�@@�mis�g���K���s�ɓn���p�����^�n<BR>
     * �@@�@@�@@���������F �h0�FDEFAULT�h<BR>
     * �@@�|�ݓ������P��Params.getOrderStatus()�̖߂�l��<BR>
     *          OrderStatusEnum.�����ρi�V�K�����j�łȂ��ꍇ�A<BR>
     *         ��O���X���[����B<BR>
     * �G���[�^�O�FBUSINESS_ERROR_00260<BR>
     * �X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �R�j�@@�w�肳�ꂽ���������Ɏ�������Ă��Ȃ����`�F�b�N����B<BR>
     * �@@�|�ݓ������P��Params.getOrderStatus()�̖߂�l��<BR>
     *        �ȉ��̒l�̉��ꂩ�Ɠ������ꍇ�A<BR>
     *          ��O���X���[����B<BR>
     * �@@�@@�@@OrderStatusEnum.��t�ρi��������j<BR>
     * �@@�@@�@@OrderStatusEnum.�����ρi��������j<BR>
     *      OrderStatusEnum.�������s�i��������j<BR>
     * �G���[�^�O�FBUSINESS_ERROR_00261<BR>
     * �X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �S�j�@@�w�肳�ꂽ�����̒�����Ԃ��h6�F�������s�i�V�K�����j�h�̏ꍇ�A��O���X���[����B<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40834ABA0172
     */
    public void validateCancelPossible(RuitoOrderUnit l_ruitoOrderUnit)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "validateCancelPossible(RuitoOrderUnit l_ruitoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //1�j�ݓ������P��.�������ƌ��݂̔����������������`�F�b�N����B
        RuitoOrderUnitParams l_ruitoOrderUnitParams =
            ((RuitoOrderUnitParams) l_ruitoOrderUnit.getDataSourceObject());
        //�ݓ������P�ʂ̔��������擾����
        String l_strRuitoBizDate = l_ruitoOrderUnitParams.getBizDate();
        //���݂̔��������擾����
        
        String l_strNowBizDate = WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd");

        log.debug("�ݓ������P�ʂ̔�����: " + l_strRuitoBizDate);
        log.debug("���݂̔�����: " + l_strNowBizDate);
        
        if (!l_strRuitoBizDate.equals(l_strNowBizDate))
        {
            log.debug("�ݓ������P�ʂ̔����� != ���݂̔�����");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00259,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�ݓ������P�ʂ̔������ƌ��݂̔��������قȂ�ꍇ");
        }
        
		//2�jSONAR����̒����ɂ��Ă͎���s�Ƃ���B 
		//  �ݓ������P��Params.get�����o�H�敪()�̖߂�l���uHOST�v�̏ꍇ�͗�O���X���[����B
		if(WEB3OrderRootDivDef.HOST.equals(l_ruitoOrderUnitParams.getOrderRootDiv()))
		{
			log.debug("SONAR������͂��ꂽ�����̏ꍇ�A��O���X���[����");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00155,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"SONAR������͂��ꂽ����");
		}
		
        //3�j�g���K���s�\�Ȏ��ԑт̏ꍇ
        boolean l_blnIsSubmit = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
            WEB3OrderingConditionDef.DEFAULT);
        log.debug("l_blnIsSubmit = " + l_blnIsSubmit);
        if (l_blnIsSubmit)
        {
            log.debug("isSubmitMarketTrigger = true");
            log.debug("OrderStatus = " + l_ruitoOrderUnitParams.getOrderStatus());
            if (!OrderStatusEnum.ORDERED.equals(
                l_ruitoOrderUnitParams.getOrderStatus()))
            {
                log.debug("OrderStatus != 3");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00260,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�ݓ������P��Params.getOrderStatus()�̖߂�l��" +
                    "OrderStatusEnum.�����ρi�V�K�����j�łȂ��ꍇ");
            }
        }
        //4�j�w�肳�ꂽ���������Ɏ�������Ă��Ȃ����`�F�b�N����B
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_ruitoOrderUnitParams.getOrderStatus())
            || OrderStatusEnum.CANCELLED.equals(l_ruitoOrderUnitParams.getOrderStatus())
            || OrderStatusEnum.NOT_CANCELLED.equals(l_ruitoOrderUnitParams.getOrderStatus()))
        {
            log.debug("OrderStatus = 12 || OrderStatus = 14 || OrderStatus = 15");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00261,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�w�肳�ꂽ���������Ɏ�������Ă��܂�");
        }
        if (OrderStatusEnum.NOT_ORDERED.equals(l_ruitoOrderUnitParams.getOrderStatus()))
            {
                log.debug("OrderStatus = 6");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00261,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�w�肳�ꂽ���������Ɏ�������Ă��܂�");
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * �S����񂪎w�肳��Ă���ꍇ�A<BR>
     * ���ɗݓ���񒍕���������Ă��Ȃ����`�F�b�N���A<BR>
     * �����������o�������O���X���[����B<BR>
     * <BR>
     * �P�j���̒����i�ۗL���Y�e�[�u���̐��ʂɌv�コ��Ă��Ȃ������j<BR>
     *     �����邩�A�ݓ������P�ʃe�[�u�����������A<BR>
     *     �Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B<BR>
     *     �i��񒍕��σG���[�j<BR>
     * �G���[�^�O�FBUSINESS_ERROR_00262<BR>
     * �X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@����ID = ����.�⏕����.getAccountId() AND<BR>
     * �@@�@@�@@�⏕����ID = ����.�⏕����.getSubAccountId() AND<BR>
     * �@@�@@�@@����ID = ����.�g���ݓ�����.getProductId()�@@AND<BR>
     * �@@�@@�@@������� = OrderTypeEnum.RUITO_SELL AND<BR>
     * �@@�@@�@@(������� = OrderStatusEnum.��t�ρi�V�K�����j OR<BR>
     * �@@�@@�@@�@@������� = OrderStatusEnum.�����ρi�V�K�����j) AND<BR>
     * �@@�@@�@@�c���v��t���O = 0  AND<BR>
     * �@@�@@�@@�ݓ��^�C�v = ����.�g���ݓ�����.getRuitoType()<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A0397E02DC
     */
    public void validateAllSell(
        SubAccount l_subAccount, WEB3RuitoProduct l_ruitoProduct)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateAllSell(SubAccount l_subAccount, WEB3RuitoProduct l_ruitoProduct)";
        log.entering(STR_METHOD_NAME);
    
        if (l_subAccount == null || l_ruitoProduct == null)
        {
            log.error("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        String l_strWhereClause =
            "account_id = ? AND sub_account_id = ? AND "
                + "product_id = ? AND order_type = ? AND "
                + "(order_status = ? OR order_status = ?) AND "
                + "balance_add_flag = ? AND ruito_type = ?";
                
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        long l_lngProdcutId = l_ruitoProduct.getProductId();
        RuitoTypeEnum l_ruitoType = l_ruitoProduct.getRuitoType();
        
        //===================
        log.debug("SQLWhere = " + 
            "account_id = 266255550025 AND sub_account_id = 26625555002501 AND "
            + "product_id = 9001999999999 AND order_type = 502 AND "
            + "(order_status = 1 OR order_status = 2) AND "
            + "balance_add_flag = 0 AND ruito_type = 1");
        //====================
        
        log.debug("SQLWhere = "
            + "account_id = " + l_lngAccountId + " AND sub_account_id = " + l_lngSubAccountId  
            + " AND product_id = " + l_lngProdcutId + " AND order_type = " + OrderTypeEnum.RUITO_SELL  
            + " AND (order_status = " + OrderStatusEnum.ACCEPTED + "OR order_status = " 
            + OrderStatusEnum.ORDERED + ") AND balance_add_flag = 0 "
            + "AND ruito_type = " + l_ruitoType);
       
        try
        {
            Object l_bindVars[] =
                {
                    new Long(l_lngAccountId),
                    new Long(l_lngSubAccountId),
                    new Long(l_lngProdcutId),          
                    OrderTypeEnum.RUITO_SELL,
                    OrderStatusEnum.ACCEPTED,
                    OrderStatusEnum.ORDERED,
                    "0",
                    l_ruitoType};
            
            List l_lisRows = null;
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);
            
            if (l_lisRows.size() != 0)
            {
                log.debug("�Y�����郌�R�[�h���������ꍇ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00262,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�Y�����郌�R�[�h���������ꍇ");
            }
            log.debug("l_lisRows.size = " + l_lisRows.size());

        }
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ����.�w����@@������.�g���ݓ������Őݒ肳��Ă���w����@@��<BR>
     * ���v���Ă��邩�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@����.�g���ݓ�����.get�w����@@�i���t�j()�̖߂�l���h<BR>
     *           3�F���z�w��h�̏ꍇ�A<BR>
     * �@@�@@����.�w����@@�̒l���h4�F�����h�ł���Η�O���X���[����B<BR>
     * �@@�@@�G���[�^�O�FBUSINESS_ERROR_00248<BR>
     * �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * <BR>
     * �Q�j�@@����.�g���ݓ�����.get�w����@@�i���t�j()�̖߂�l���h<BR>
     *        4�F�����w��h�̏ꍇ�A<BR>
     * �@@�@@����.�w����@@�̒l���h3�F���z�h�ł���Η�O���X���[����B<BR>
     * �@@�@@�G���[�^�O�FBUSINESS_ERROR_00248<BR>
     * �@@�@@�X���[���ׂ���O�N���X�FWEB3BusinessLayerException<BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40C07246024E
     */
    public void validateDesignateMethod(
        WEB3RuitoProduct l_ruitoProduct,
        String l_strDesignateMethod)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateDesignateMethod(" +
            "WEB3RuitoProduct l_ruitoProduct, String l_strDesignateMethod)";
        log.entering(STR_METHOD_NAME);
    
        if (l_ruitoProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        log.debug("l_ruitoProduct.getProductCode()" + l_ruitoProduct.getProductCode());
        log.debug("����.�g���ݓ�����.get�w����@@�i���t�j = " + 
                l_ruitoProduct.getPaymentMethodBuy());
        log.debug("����.�w����@@ = " + l_strDesignateMethod);
        
        //�P�j�@@����.�g���ݓ�����.get�w����@@�i���t�j()�̖߂�l���h3�F���z�w��h�̏ꍇ�A
        //     ����.�w����@@�̒l���h4�F�����h�ł���Η�O���X���[����B
        if (WEB3DesignateMethodDef.AMOUNT.equals(
            l_ruitoProduct.getPaymentMethodBuy()) && 
            (WEB3DesignateMethodDef.NUMBER.equals(
            l_strDesignateMethod)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00248,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ݐϓ����̎w����@@�����v�Ȃ�");
        }
        //�Q�j�@@����.�g���ݓ�����.get�w����@@�i���t�j()�̖߂�l���h4�F�����w��h�̏ꍇ�A
        //     ����.�w����@@�̒l���h3�F���z�h�ł���Η�O���X���[����B 
        if (WEB3DesignateMethodDef.NUMBER.equals(
            l_ruitoProduct.getPaymentMethodBuy()) && 
            WEB3DesignateMethodDef.AMOUNT.equals(
            l_strDesignateMethod))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00248,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ݐϓ����̎w����@@�����v�Ȃ�");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate��������<BR>
     * <BR>
     * �񂪎w�肳��Ă���ꍇ�A������ɈقȂ�w����@@��<BR>
     * �ݓ���񒍕���������Ă��Ȃ����`�F�b�N���A<BR>
     * �����������o�������O���X���[����B<BR>
     * <BR>
     * �P�j���̒����i�ۗL���Y�e�[�u���̐��ʂɌv�コ��Ă��Ȃ������j�����邩�A<BR>
     *     �ݓ������P�ʃe�[�u�����������A�Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B<BR> 
     * <BR>
     * �m���������n <BR>
     * ����ID = ����.�⏕����.getAccountId() AND<BR>
     * �⏕����ID = ����.�⏕����.getSubAccountId() AND<BR>
     * ����ID = ����.�g���ݓ�����.getProductId()�@@AND<BR>
     * ������� = OrderTypeEnum.RUITO_SELL AND<BR>
     * (������� = OrderStatusEnum.��t�ρi�V�K�����j OR<BR>
     * ������� = OrderStatusEnum.�����ρi�V�K�����j) AND<BR>
     * �c���v��t���O = 0 AND<BR>
     * �ݓ��^�C�v = ����.�g���ݓ�����.getRuitoType()�@@AND<BR>
     * �������ʃ^�C�v != �w����@@�@@(��)<BR>
     * <BR>
     * (��)����.�w����@@=3�i���z�j�̏ꍇ�A�w����@@�́u2�v�Ō���<BR>
     *                  4�i�����j�̏ꍇ�A�w����@@�́u1�v�Ō���<BR>
     *<BR>
     * @@param l_subAccount - �⏕����<BR>
     * @@param l_ruitoProduct - �g���ݓ�����<BR>
     * @@param l_strDesignateMethod - �w����@@<BR>
     * @@throws WEB3BaseException
     */
    public void validateSameOrderDateSell(
    		SubAccount l_subAccount,
			WEB3RuitoProduct l_ruitoProduct,
			String l_strDesignateMethod)
			throws WEB3BaseException
	{
		String STR_METHOD_NAME ="validateSameOrderDateSell";
		log.entering(STR_METHOD_NAME);
		
		if (l_subAccount == null)
		{
			log.debug(" �p�����[�^�l��NULL ");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"�p�����[�^�l��NULL");
		}
		
		QuantityTypeEnum l_QuantityTypeEnum = null;
		// �w����@@ = 3�i���z�j�̏ꍇ�A�w����@@�́u2�v
		if(l_strDesignateMethod.equals(WEB3SellDivDef.MONEY_DESIGNATE))
		{
			l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
		}
		// �w����@@ = 4�i�����j�̏ꍇ�A�w����@@�́u1�v
		else if(l_strDesignateMethod.equals(WEB3SellDivDef.COUNT_DESIGNATE))
		{
			l_QuantityTypeEnum = QuantityTypeEnum.QUANTITY;
		}
		
		// ��������
		String l_strWhereClause =
			"account_id = ? AND sub_account_id = ? AND "
				+ "product_id = ? AND order_type = ? AND "
				+ "(order_status = ? OR order_status = ?) AND "
				+ "balance_add_flag = ? AND ruito_type = ? AND "
				+ "quantity_type != ?";
                        
		log.debug("SQLWhere = account_id = " + l_subAccount.getAccountId() + 
			" AND sub_account_id = " + l_subAccount.getSubAccountId() + 
			" AND product_id = " + l_ruitoProduct.getProductId() + 
			" AND order_type = " + OrderTypeEnum.RUITO_SELL + 
			" AND (order_status = " + OrderStatusEnum.ACCEPTED +
			" OR order_status = " + OrderStatusEnum.ORDERED +")"+ 
			" AND balance_add_flag = " + "0" + 
			" AND ruito_type = " + l_ruitoProduct.getRuitoType()+
			" AND quantity_type != "+ l_QuantityTypeEnum);
                    
		Object l_bindVars[] =
			{
				new Long(l_subAccount.getAccountId()),
				new Long(l_subAccount.getSubAccountId()),
				new Long(l_ruitoProduct.getProductId()),
				OrderTypeEnum.RUITO_SELL,
				OrderStatusEnum.ACCEPTED,
				OrderStatusEnum.ORDERED,
				"0",
				l_ruitoProduct.getRuitoType(),
				new Long(l_QuantityTypeEnum.intValue())};
		
		// �ݓ������P�ʃe�[�u��������		
		List l_lisRows = null;
		try
		{
			l_lisRows =
				Processors.getDefaultProcessor().doFindAllQuery(
					RuitoOrderUnitRow.TYPE,
					l_strWhereClause,
					l_bindVars);
		}
		catch (DataQueryException l_ex)
		{
			log.debug("__DataQueryException__", l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch(DataNetworkException l_ex)
		{
			log.debug("__DataNetworkException__", l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
         
		// �Y�����郌�R�[�h���������ꍇ�͗�O���X���[����B      
		if (l_lisRows.size() != 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02010,
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"���ꔭ�����Ɍ����w����Ƌ��z�w����̗����̒����͂ł��܂���B");
		}
		log.exiting(STR_METHOD_NAME);
	}

    public static void register()
    {
        WEB3RuitoOrderManagerReusableValidationsCheck.setInstance(
            new WEB3RuitoOrderManagerReusableValidationsCheck());
    }

}
@
