head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderValidator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�����`�F�b�N(WEB3IpoOrderValidator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �ė� (���u) �V�K�쐬
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>036
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>054
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>069
Revesion History : 2006/01/26 �s�p�i���u�j�d�l�ύX�E���f��118
Revesion History : 2006/09/07 ���G�́i���u�j�d�l�ύX�E���f��155
Revesion History : 2007/07/19 ��іQ (���u) ���f��  No.175
*/


package webbroker3.ipo;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ProvisionalValueDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * IPO�����`�F�b�N�N���X<BR>
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3IpoOrderValidator extends WEB3GentradeOrderValidator implements OrderValidator
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderValidator.class);
    
    /**
     * @@roseuid 4112FBCD019A
     */
    public WEB3IpoOrderValidator() 
    {
     
    }
    
    /**
     * (validate����)<BR>
     * (validateQuantity)<BR>
     * ���ʂ̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@0�܂��̓}�C�i�X�l�̃`�F�b�N<BR>
     * �@@�����̐��ʂ�0�܂��̓}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00531<BR>
     * <BR>
     * �Q�j�@@�P�ʐ��ʃ`�F�b�N<BR>
     * �@@�����̐��ʂ��A�w���\���P��(*1)�̐����{�łȂ��ꍇ<BR>
     * �i���� % �w���\���P�� != 0�j�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00532<BR>
     * <BR>
     * (*1) �w���\���P�ʂ̎擾<BR>
     * IPO����.IPO�����s.�w���\���P��<BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * @@param l_dblQuantity - ����<BR>
     * @@roseuid 40D79A4503A4
     */
    public void validateQuantity(WEB3IpoProductImpl l_ipoProduct, double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateQuantity(WEB3IpoProductImpl, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        if (l_dblQuantity <= 0)  //0�܂��̓}�C�i�X�l�̃`�F�b�N
        {
            log.exiting(STR_METHOD_NAME);
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00531, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        double l_dblUnit = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getLotSize();
        
        log.debug("UnitQuantity = !" + (l_dblQuantity % l_dblUnit));
        if (l_dblQuantity % l_dblUnit != 0)  //�P�ʐ��ʃ`�F�b�N
        {
            log.exiting(STR_METHOD_NAME);
            //��O
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00532, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�P��)<BR>
     * �ivalidatePrice�j<BR>
     * <BR>
     * �P�j�@@���s�̔���<BR>
     * �@@�����̒P����0�̏ꍇ�A���s�Ɣ��肵�A�ȍ~�̏��������{�����������I������B<BR>
     * <BR>
     * �Q�j�@@�������敪�ɂ�郌���W�`�F�b�N<BR>
     * �@@�� �������敪(*1) == �h�����i�i�~�j�h�̏ꍇ<BR>
     * �@@�@@�|�����̒P���̃T�C�Y��8byte���傫���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00533<BR>
     * �@@�@@�|�����̒P���������l�łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00534<BR>
     * <BR>
     * �@@�� �������敪(*1) == �h�f�B�X�J�E���g���i���j�h�̏ꍇ<BR>
     * �@@�@@�|�����̒P���̃T�C�Y��������2���^������2���ȓ��̃����W�łȂ��ꍇ�A<BR>��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00535<BR>
     * <BR>
     * (*2) �������敪<BR>
     * IPO����.IPO�����s.�������敪<BR>
     * <BR>
     * �R�j�@@����������^�����l�̃`�F�b�N<BR>
     * �@@�����̒P�����ȉ��̏����Ɉ�v���Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@�����������l(*2) <= �P�� <= ����������l(*3)<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00536<BR>
     * <BR>
     * (*2) �����������l<BR>
     * IPO����.IPO�����s.�����������l<BR>
     * (*3) ����������l<BR>
     * IPO����.IPO�����s.����������l<BR>
     * <BR>
     * �R�j�@@���݂̃`�F�b�N<BR>
     * �@@�����̒P�����A���ݒP�ʂɂȂ��Ă��邩���`�F�b�N����B<BR>
     * �@@�ȉ��̏����Ɉ�v���Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@(�P�� - �����������l(*2)) % ����(*4) == 0<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00537<BR>
     * <BR>
     * (*4) ���݂̎擾<BR>
     * IPO����.IPO�����s.����
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * 
     * @@param l_dblUnitPrice - �P��<BR>
     * �@@���s�̏ꍇ0�B
     * @@roseuid 40D79B750327
     */
    public void validatePrice(WEB3IpoProductImpl l_ipoProduct, double l_dblUnitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePrice(WEB3IpoProductImpl, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        if (l_dblUnitPrice == 0)  //���s�̔���
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            String l_strDiv = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalValueDiv();
            
            //�������敪 == �h�����i�i�~�j�h�̏ꍇ
            if ((WEB3ProvisionalValueDivDef.TRUE_VALUE).equals(l_strDiv))
            {
                String l_str = WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
                
                if (WEB3StringTypeUtility.getByteLength(l_str) > 8)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00533, this.getClass().getName() + STR_METHOD_NAME);
                }
                
                if (!(Math.floor(l_dblUnitPrice) == l_dblUnitPrice))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00534, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            //�������敪 == �h�f�B�X�J�E���g���i���j�h�̏ꍇ
            if ((WEB3ProvisionalValueDivDef.DISCOUNT_RATIO).equals(l_strDiv))
            {
                String l_str = WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
                int l_dbltmp1 = WEB3StringTypeUtility.getIntegerDigits(l_str);
                int l_dbltmp2 = WEB3StringTypeUtility.getFractionDigits(l_str);
                
                if (l_dbltmp1 > 2 || l_dbltmp2 > 2)
                {
                    log.debug("������ = " + l_dbltmp1);
                    log.debug("������ = " + l_dbltmp2);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00535, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            
            //����������^�����l�̃`�F�b�N
            double l_dblMiniValue = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMinValue();
            double l_dblMaxValue = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMaxValue();
            if (l_dblUnitPrice < l_dblMiniValue || l_dblUnitPrice > l_dblMaxValue)
            {
                log.debug("MiniValue = " + l_dblMiniValue);
                log.debug("MaxValue = " + l_dblMaxValue);
                log.debug("UnitPrice = " + l_dblUnitPrice);
                log.exiting(STR_METHOD_NAME);
                //��O
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00536, this.getClass().getName() + STR_METHOD_NAME);                
            }
            //���������_�̊ۂߌ덷�ɂ�锻��~�X�̉��
			BigDecimal bd = new BigDecimal("100");
			BigDecimal l_dblMiniValueD = new BigDecimal(l_dblMiniValue).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(bd);
			BigDecimal l_dblUnitPriceD = new BigDecimal(l_dblUnitPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(bd);
            double l_dblTick = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getTickValue();
			BigDecimal l_dblTickD = new BigDecimal(l_dblTick).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(bd);
			long l_dblMiniValuel = l_dblMiniValueD.longValue();
			long l_dblUnitPricel = l_dblUnitPriceD.longValue();
			long l_dblTickl      = l_dblTickD.longValue();
			if (l_dblTickl == 0)
			{
				log.exiting(STR_METHOD_NAME);
				//��O
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00537, this.getClass().getName() + STR_METHOD_NAME);                
			}
            long l_dblTmp = (l_dblUnitPricel - l_dblMiniValuel) % l_dblTickl;
            if (l_dblTmp != 0)
            {
                log.debug("(l_dblUnitPrice - l_dblMiniValue) % l_dblTick = " + l_dblTmp);
                log.exiting(STR_METHOD_NAME);
                //��O
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00537, this.getClass().getName() + STR_METHOD_NAME);                
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��d�\��)<BR>
     * ��d�\���`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@IPO�\���}�l�[�W��.get�L��IPO�\��()�ɂāA������IPO�\���s���擾����B<BR>
     * �擾�ł��Ȃ��ꍇ�igetIPO�\��() == null�j�A�������I���ireturn;�j����B<BR>
     * <BR>
     * [getIPO�\��()�Ɏw�肷�����]<BR>
     * �⏕�����F�@@�⏕����<BR>
     * IPO�����h�c�F�@@IPO����.IPO�����h�c<BR>
     * <BR>
     * �Q�j�@@��d�\������<BR>
     * �@@�擾����IPO�\��������łȂ��ꍇ�iIPO�\��.get�擾�s�u�b�N�r���f�B���O<BR>�\�����() != 
     * OrderStatusEnum.CANCELLED�i����j�j�A<BR>
     * �Y���ڋq�͊��Ƀu�b�N�r���f�B���O�\���ςł���Ɣ��肵�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00538<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * @@roseuid 40D7A02F0375
     */
    public void validateDuplicateOrder(SubAccount l_subAccount, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateOrder(SubAccount, WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        long l_lngIpoProductId = l_ipoProduct.getProductId();
        
        WEB3IpoOrderImpl l_ipoOrder = l_ipoOrderManagerImpl.getOrderUnit(l_subAccount, l_lngIpoProductId);
        
        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //��d�\������
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder).getDataSourceObject());
            OrderStatusEnum l_orderStatus = l_ipoOrderRow.getOrderStatus();
            if (l_orderStatus != null && l_orderStatus != OrderStatusEnum.CANCELLED)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00538, this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�u�b�N�r���f�B���O����)<BR>
     * IPO�������u�b�N�r���f�B���O�戵���ł��邱�Ƃ��`�F�b�N����B<BR>
     * <BR>
     * �|�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@IPO����.is�u�b�N�r���f�B���O�\���\() == ture<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00539<BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * @@roseuid 40D7A34302BA
     */
    public void validateBookbuildingProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBookBuildingProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�u�b�N�r���f�B���O�����̃`�F�b�N
        if (!l_ipoProduct.isBookbuildingOrderPossible())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00539, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    /**
//     * (validate�ژ_��������)<BR>
//     * �ژ_�����d�q��t�ς����`�F�b�N����B<BR>
//     * <BR>
//     * �|�h�L�������g�V�X�e���ڑ�I/F�T�[�r�X���擾���Ais�ژ_��������()���R�[������B<BR>
//     * <BR>
//     * �@@[is�ژ_��������()�Ɏw�肷�����]<BR>
//     * �@@�ڋq�F�@@�⏕����.getMainAccount()<BR>
//     * �@@�����^�C�v�F�@@IPO����.get�����^�C�v<BR>
//     * �@@�����R�[�h�F�@@IPO����.IPO�����s.�����R�[�h<BR>
//     * <BR>
//     * �|�iis�ژ_��������() == false�j�̏ꍇ�A�ژ_�����d�q��t���ςƔ��肵<BR>��O���X���[����B<BR>
//     *         class: WEB3BusinessLayerException<BR>
//     *         tag:   BUSINESS_ERROR_00540<BR>
//     * @@param l_subAccount - (�⏕����)<BR>
//     * �⏕�����I�u�W�F�N�g
//     * @@param l_ipoProduct - (IPO����)<BR>
//     * IPO�����I�u�W�F�N�g
//     * @@roseuid 40D7BA9C0181
//     */
//    public void validateProspectusExistingRead(SubAccount l_subAccount, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
//    {
//        final String STR_METHOD_NAME = " validateProspectusExistingRead(SubAccount, WEB3IpoProductImpl)";
//        log.entering(STR_METHOD_NAME);
//        
//        if (l_subAccount ==null || l_ipoProduct == null)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
//        }
//        
//        //�ژ_�����d�q��t�ς̃`�F�b�N
//        WEB3GentradeDocumentSystemConnectService l_service = (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//        
//        MainAccount l_MainAccount = l_subAccount.getMainAccount();
//        if (!(l_MainAccount instanceof WEB3GentradeMainAccount))
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
//        }
//        else
//        {
//            WEB3GentradeMainAccount l_web3MainAccount = (WEB3GentradeMainAccount)l_MainAccount;
//            ProductTypeEnum l_productTypeEnum = l_ipoProduct.getProductType();
//            String l_strProductCode = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProductCode();
//        
//            if (!l_service.isProspectusAccept(l_web3MainAccount, l_productTypeEnum, l_strProductCode))
//            {
//                log.exiting(STR_METHOD_NAME);
//                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00540, this.getClass().getName() + STR_METHOD_NAME);
//            }
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    /**
     * (validate��d�\���E����)<BR>
     * ��d�w���\���^���ނ̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@������IPO�\���I�u�W�F�N�g���ȉ��̏�Ԃł���΁A��d�\���^�܂��͎��ނ�<BR>���f���A��O���X���[����B<BR>
     * <BR>
     * �@@[Error�̏���]<BR>
     * �@@�iIPO�\��.is����() == true�j Or <BR>
     * �@@�iIPO�\��.is�w���\��() == true�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00541<BR>
     * @@param l_ipoOrder - (IPO�\��)<BR>
     * IPO�\���I�u�W�F�N�g
     * @@roseuid 40DB9C140012
     */
    public void validateDuplicateAppDecline(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateAppDecline(WEB3IpoOrderImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //��d�w���\���^���ނ̃`�F�b�N
        if (l_ipoOrder.isDecline() || l_ipoOrder.isOffered())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00541, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�w���\���\����)<BR>
     * IPO�������w���\�����Ԓ��ł��邱�Ƃ��`�F�b�N����B<BR>
     * <BR>
     * �|�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@IPO����.is�w���\���\() == ture<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00542<BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * @@roseuid 40DBB5970287
     */
    public void validateOfferPossibleProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOfferPossibleProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //IPO�������w���\�����Ԓ��̃`�F�b�N
        if (!l_ipoProduct.isOfferPossible())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00542, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���މ\����)<BR>
     * IPO���������މ\���Ԓ��i�V�K���I�I���`�w���\���I���j�ł��邱�Ƃ�<BR>�`�F�b�N����B<BR>
     * <BR>
     * �|�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     * <BR>
     * �@@[����������]<BR>
     * �@@IPO����.is���މ\() == ture<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00543<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@roseuid 40DBB65E00C5
     */
    public void validateDeclinePossibleProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDeclinePossibleProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //PO���������މ\���Ԓ��̃`�F�b�N
        if (!l_ipoProduct.isDeclinePossible())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00543, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�w���\���E���މ\)<BR>
     * ���I���Ă��Ȃ����Ƃ��`�F�b�N����B<BR>
     * <BR>
     * �|�ȉ��̏����ɓ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B<BR>
     * �@@[����������]<BR>
     * �@@IPO�\��.is�w���\���E���މ\() == ture<BR>
     * @@param l_ipoOrder - (IPO�\��)<BR>
     * IPO�\���I�u�W�F�N�g
     * @@roseuid 40DBB7FB0037
     */
    public void validateOfferDeclinePossible(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOfferDeclinePossible(WEB3IpoOrderImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (!l_ipoOrder.isOfferDeclinePossible())
        {
            String l_strMessage = "�Y��IPO�\���͓��I�^�⌇���I�łȂ��ꍇ�A���邢�́A�Y���ڋq�͌J�㒊�I�ŗ��I���Ă���ꍇ�A�w���\���^���މ\���s�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00544,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������)<BR>
     * �w��̐ŋ敪����������̏ꍇ�A�Y���ڋq������������J�݂��Ă��邩<BR>�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�ŋ敪����<BR>
     * �@@�����̐ŋ敪 == TaxTypeEnum.�h��ʁh�̏ꍇ�A<BR>
     * �ȉ��̃`�F�b�N���s�킸�������I������B<BR>
     * <BR>
     * �Q�j�@@�ڋq�̐ŋ敪����<BR>
     * �@@�����̕⏕����.getMainAccount()�ɂČڋq�I�u�W�F�N�g���擾����B<BR>
     * �擾�����ڋq.is��������J��() == false�̏ꍇ�A<BR>
     * �Y���ڋq�͓���������J�݂��Ă��Ȃ��Ɣ��肵�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00545<BR>
     * <BR>
     * [is��������J��()�Ɏw�肷�����]<BR>
     * ��n���F�@@�����̎�n��<BR>
     * �⏕�����F�@@�����̕⏕����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_taxType - �ŋ敪
     * @@param l_datDeliveryDate - ��n��
     * @@roseuid 40DBC9230137
     */
    public void validateSpecialAccount(SubAccount l_subAccount, TaxTypeEnum l_taxType, Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSpecialAccount(SubAccount, TaxTypeEnum, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (l_taxType == TaxTypeEnum.NORMAL)  //�ŋ敪����
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //�ڋq�̐ŋ敪����
            MainAccount l_MainAccount = l_subAccount.getMainAccount();
            if (!(l_MainAccount instanceof WEB3GentradeMainAccount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                WEB3GentradeMainAccount l_web3MainAccount = (WEB3GentradeMainAccount)l_MainAccount;
                if (!l_web3MainAccount.isSpecialAccountEstablished(l_datDeliveryDate, l_subAccount))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00026, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�w���\������)<BR>
     * �w���\�����ʂ̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@0�܂��̓}�C�i�X�l�̃`�F�b�N<BR>
     * �Q�j�@@�P�ʐ��ʃ`�F�b�N<BR>
     * �@@this.validate����()���R�[������B<BR>
     * <BR>
     * �@@[validate����()�Ɏw�肷�����]<BR>
     * �@@IPO�����F�@@IPO����<BR>
     * �@@���ʁF�@@�w���\������<BR>
     * <BR>
     * �R�j�@@���I���ʂƂ̔�r<BR>
     * �@@���I���ʂ��傫���l���w���\�����ʂɎw�肳��Ă����ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �@@[Error����]<BR>
     * �@@�w���\������ > ���I����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00546<BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * 
     * @@param l_dblApplicationQuantity - �w���\������
     * @@param l_dblElectedQuantity - ���I����
     * @@roseuid 40DBCE7601A3
     */
    public void validateApplicationQuantity(WEB3IpoProductImpl l_ipoProduct, double l_dblApplicationQuantity, double l_dblElectedQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateApplicationQuantity(WEB3IpoProductImpl, double, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�ʐ��ʃ`�F�b�N
        this.validateQuantity(l_ipoProduct, l_dblApplicationQuantity);
        
        //���I���ʂƂ̔�r
        if (l_dblApplicationQuantity > l_dblElectedQuantity)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00546, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���Z��)<BR>
     * ���Z�҃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�p�����[�^.�⏕��������擾�����ڋq�I�u�W�F�N�g.���Z�^�񋏏Z�敪<BR>
     * ���u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv�̏ꍇ�́A��O���X���[����B<BR>
     * 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02344<BR>
     * 
     * @@param l_subAccount - (�⏕����)<BR>
     * @@throws WEB3BaseException
     */
    public void validateResident(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateResident(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        WEB3GentradeMainAccount l_mainAccount = null;        
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount) 
                l_accountMgr.getMainAccount(l_subAccount.getAccountId());//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ڋq�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        MainAccountRow l_row = (MainAccountRow) l_mainAccount.getDataSourceObject();
        
        //�P�j�p�����[�^.�⏕��������擾�����ڋq�I�u�W�F�N�g.���Z�^�񋏏Z�敪 
        //���u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv�̏ꍇ�́A��O���X���[����B
        if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_row.getResident()) || 
            WEB3ResidentDef.NON_RESIDENT.equals(l_row.getResident()))
        {
            log.debug("���Z�^�񋏏Z�敪:�u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02344,
                this.getClass().getName() + STR_METHOD_NAME,
                "���Z�^�񋏏Z�敪:�u1�F���ʔ񋏏Z�ҁv or �u2�F�񋏏Z�ҁv");
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (validate����\������)<BR>
     * �\������������u���吔�� + ���o���ʁv�͈͓̔��ł��邩 <BR>
     * �`�F�b�N���s���B<BR> 
     * <BR>
     * �P�j�@@����:IPO����������吔�ʂƔ��o���ʂ��擾 <BR>
     * <BR>
     * �Q�j�@@�u���吔�� + ���o���ʁv < ����:���� �̏ꍇ�A��O���X���[����B<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g<BR>
     * @@param l_dblQuantity - �\������<BR>
     * @@throws WEB3BaseException
     */
    public void validateMaxDemandProductCount(
        WEB3IpoProductImpl l_ipoProduct, 
        double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMaxDemandProductCount(WEB3IpoProductImpl, Double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �\������������u���吔�� + ���o���ʁv�͈͓̔��ł��邩 
        // �`�F�b�N���s���B
        // �P�j�@@����:IPO����������吔�ʂƔ��o���ʂ��擾 
        //���吔�ʂ��擾
        double l_dblPublicOfferingQuantity = 
            ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getPublicOfferingQuantity(); 
        
        //���o���ʂ��擾 
        double l_dblPublicSalesQuantity = 
            ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getPublicSalesQuantity(); 
        
        // �Q�j�@@�u���吔�� + ���o���ʁv < ����:���� �̏ꍇ�A��O���X���[����B
        double l_dblAnswer = l_dblPublicOfferingQuantity + l_dblPublicSalesQuantity;        
        
        if (l_dblAnswer < l_dblQuantity)
        {
            log.debug("�\������������u���吔�� + ���o���ʁv�͈̔͊O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02645, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�w���\�����)<BR>
     * �w���\��������`�F�b�N����B<BR>
     * <BR>
     * �P�j����]�̓T�[�r�X.get���̑����i���t�\�z�`0�␳�����`()���R�[�����A<BR>
     * �@@�@@���̑����i���t�\�z�i0�␳�����j���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�⏕�����F ����.�⏕����<BR>
     * �@@��n���F ����.IPO����.IPO�����s.���J��<BR>
     * <BR>
     * �Q�j�w���\��������`�F�b�N����B<BR>
     * <BR>
     * �@@�Q�|�P�j����.IPO�\��.is���I��()���R�[�����A���I�҂��ۂ��𔻒肷��B<BR>
     * <BR>
     * �@@�Q�|�Q�j���I�ҁiis���I��() == true�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j���̑����i���t�\�z�i0�␳�����j < 0 �̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02878       <BR>
     * <BR>
     * �@@�Q�|�R�j���̑��̏ꍇ<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�P�j�w���\��������Z�o����B<BR>
     * <BR>
     * �@@�@@�@@�w���\����� = ����.�w���\������ * ����.IPO����.IPO�����s.���J���i<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�Q�j���̑����i���t�\�z�i0�␳�����j < �w���\����� �̏ꍇ�A<BR>
     * �@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02879       <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_ipoProduct - IPO����<BR>
     * IPO����<BR>
     * @@param l_ipoOrder - IPO�\��<BR>
     * IPO�\��<BR>
     * @@param l_dblApplicationQuantity - (�w���\������)<BR>
     * �w���\������<BR>
     * @@throws WEB3BaseException
     */
    public void validatePayAmount(
        SubAccount l_subAccount,
        WEB3IpoProductImpl l_ipoProduct,
        WEB3IpoOrderImpl l_ipoOrder,
        double l_dblApplicationQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validatePayAmount(SubAccount, WEB3IpoProductImpl, WEB3IpoOrderImpl, double)";
        log.entering(STR_METHOD_NAME);

        if (l_ipoProduct == null || l_ipoOrder == null || l_subAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j����]�̓T�[�r�X.get���̑����i���t�\�z�`0�␳�����`()���R�[�����A
        //���̑����i���t�\�z�i0�␳�����j���擾����B
        //[����]
        //�⏕�����F ����.�⏕����
        //��n���F TradingSystem.getBizDate()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_trdSys = l_finApp.getTradingSystem();

        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        double l_dblOtherTradingPower = l_tPTradingPowerService.getOtherTradingPowerForCheck(
            (WEB3GentradeSubAccount)l_subAccount,
            l_trdSys.getBizDate());

        //�Q�j�w���\��������`�F�b�N����B
        //�Q�|�P�j����.IPO�\��.is���I��()���R�[�����A���I�҂��ۂ��𔻒肷��B
        boolean l_blnIsElected = l_ipoOrder.isElected();

        //�Q�|�Q�j���I�ҁiis���I��() == true�j�̏ꍇ
        if (l_blnIsElected)
        {
            //�Q�|�Q�|�P�j���̑����i���t�\�z�i0�␳�����j < 0 �̏ꍇ�A��O���X���[����B
            if (l_dblOtherTradingPower < 0)
            {
                log.debug("���̑����i���t�\�z�i0�␳�����j��0��菬�����l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02878,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���̑����i���t�\�z�i0�␳�����j��0��菬�����l�ł��B");
            }
        }
        //�Q�|�R�j���̑��̏ꍇ
        else
        {
            //�Q�|�R�|�P�j�w���\��������Z�o����B
            //�w���\����� = ����.�w���\������ * ����.IPO����.IPO�����s.���J���i
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();

            BigDecimal l_bdApplicationQuantity =
                new BigDecimal(String.valueOf(l_dblApplicationQuantity));

            BigDecimal l_bdPublicPrice =
                new BigDecimal(String.valueOf(l_ipoProductRow.getPublicPrice()));

            double l_dblPayAmount = (l_bdApplicationQuantity.multiply(l_bdPublicPrice)).doubleValue();

            //�Q�|�R�|�Q�j���̑����i���t�\�z�i0�␳�����j < �w���\����� �̏ꍇ�A��O���X���[����B
            if (l_dblOtherTradingPower < l_dblPayAmount)
            {
                log.debug("���̑����i���t�\�z�i0�␳�����j���w���\�������菬�����ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02879,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���̑����i���t�\�z�i0�␳�����j���w���\�������菬�����ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /* (�� Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator#validateTradedProductForTrading(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum, com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct)
     */
    public OrderValidationResult validateTradedProductForTrading(SubAccount arg0, OrderTypeEnum arg1, TradedProduct arg2)
    {
        return null;
    }

    /* (�� Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator#validateMarket(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum, com.fitechlabs.xtrade.plugin.tc.gentrade.Market)
     */
    public OrderValidationResult validateMarket(OrderTypeEnum arg0, Market arg1)
    {
        return null;
    }
}
@
