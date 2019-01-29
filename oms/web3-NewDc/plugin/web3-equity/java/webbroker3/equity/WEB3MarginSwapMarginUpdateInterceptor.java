head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapMarginUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�������n�X�V�C���^�Z�v�^(WEB3MarginSwapMarginUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ���� (SRA) �V�K�쐬
Revesion History : 2007/04/26 �Ӑ� (���u) �c�a�X�V�d�lNo.199
*/

package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ForcedExpireType;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.define.WEB3MarginBaseNumber;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�������n�X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �������n�����o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 *�iEqTypeOrderManagerPersistenceEventInterceptor�̎����j<BR>
 * �ȉ��̃T�[�r�X���痘�p�����B<BR> 
 * �E�u�M�p����������n�T�[�r�X�v <BR>
 * �E�u�M�p����������n�����ʒm�T�[�r�X�v<BR>
 * �E�u�A�����������T�[�r�X�v
 * @@author �@@��
 * @@version 1.0
 **/
public class WEB3MarginSwapMarginUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginUpdateInterceptor.class);
    /**
     * (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B<BR>
     */
    private WEB3MarginSwapContractOrderSpec equitySwapMarginOrderSpec;
    
    /**
     * (�ٍϋ敪�iSONAR�j)�B<BR>
     * �ٍϋ敪�iSONAR�j�B<BR>
     */
    private String sonarRepaymentType;
    
    /**
     * (�T�Z��n����B)<BR>
     * �T�Z��n����B<BR>
     */
    private double estimatedPrice;
    
    /**
     * (�ٍϋ敪�B)<BR>
     * �ٍϋ敪�B <BR>
     */
    private String repaymentType;
    
    /**
     * (�ٍϊ����l�B)<BR>
     * �ٍϊ����l�B<BR>
     */
    private double repaymentNum;
    
    /**
     * (���n�v���z�B)<BR>
     * ���n�v���z�B<BR>
     */
    private double capitalGain;
    
    /**
     * (���n�v�Ŋz�B)<BR>
     * ���n�v�Ŋz�B<BR>
     */
    private double capitalGainTax;
    
    /**
     * (���񒍕��̒����`���l���B)<BR>
     * ���񒍕��̒����`���l���B<BR>
     */
    private String orderChanel;
    
    /**
     * (�����o�H�敪�B)<BR>
     * �����o�H�敪�B<BR>
     */
    private String orderRootDiv;
    
    /**
     * (���ʃR�[�h�B)<BR>
     * ���ʃR�[�h�B <BR>
     * �M�p����������n�����ʒm�T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B�ȊO�Anull���Z�b�g�B<BR>
     */
    private String orderRequestNumber;
    
    /**
     * (�󒍓����B)<BR>
     * �󒍓����B <BR>
     * �M�p����������n�����ʒm�T�[�r�X�A�A�����������T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B<BR>
     * �ȊO�Anull���Z�b�g�B<BR>
     */
    private Date receivedDateTime;
    
	/**
	 * (��n���B)<BR>
	 * ��n���B <BR>
	 * �M�p����������n�����ʒm�T�[�r�X���痘�p����ꍇ�̂݃Z�b�g���Ďg�p�B�ȊO�Anull���Z�b�g�B<BR>
	 */
	private Date deliveryDate = null;
	
    /**
     * @@roseuid 4142BBF5032D
     */
    public WEB3MarginSwapMarginUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �K�{�v���p�e�B�ݒ�`�F�b�N <BR>
     * <BR>
     * �@@���I�u�W�F�N�g�̃v���p�e�B�̂����A <BR>
     * �@@�M�p�������n�������e�A�����o�H�敪�� <BR>
     * �@@�����ꂩ�P�ł�null�̏ꍇ�́A <BR>
     * �@@�p�����[�^.�����P��Params��ԋp���A�������I������B <BR>
     * <BR>
     * �Q�j �g�����ڃZ�b�g<BR>
     * �@@�v���p�e�B����A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �@@�X�V���e�́A�ȉ���DB�ݒ�_�����Q�ƁB <BR>
     * �@@�E�u�M�p�������n_���������P�ʃe�[�u��.xls�v <BR>
     * �@@�E�u�M�p����������n�����ʒm_���������P�ʃe�[�u��.xls�v�� <BR>
     * �@@�@@�u�i�M�p����������n�����ʒm�j���������P�ʃe�[�u���v�V�[�g <BR>
     * <BR>
     * �@@�u��n���v�u�󒍓����v�u���ʃR�[�h�v�u�������v�̐ݒ�d�l�́A�ȉ��̒ʂ�ɕ��򂷂�B <BR>
     * �@@----------------------------------------------------------------------- <BR>
     *   ��n���@@�@@�@@�Fthis.get��n��( )��null�̏ꍇ�̂݁Athis.��n���v���p�e�B���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��this.get��n��( )��null�̏ꍇ�́AxTrade�W�������̂܂܂Ƃ���B<BR>
     * �@@�󒍓����@@�@@�Fthis.get�󒍓���( )��null�̏ꍇ�́AGtlUtils.getSystemTimestamp( )�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.get�󒍓���( )��null�̏ꍇ�́Athis.�󒍓����v���p�e�B���Z�b�g�B <BR>
     * �@@���ʃR�[�h�@@�@@�Fthis.get���ʃR�[�h( )��null�̏ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h( )�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.get���ʃR�[�h( )��null�̏ꍇ�́Athis.���ʃR�[�h�v���p�e�B���Z�b�g�B <BR>
     * �@@�������@@�@@�@@�@@�Fthis.�����o�H�敪��"HOST"�̏ꍇ�̂݁A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@GtlUtils.getSystemTimestamp( )��YYYYMMDD�B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A�Z�b�g�Ȃ��ixTrade�W���d�l�ʂ�j <BR>
     * �@@----------------------------------------------------------------------- <BR>
     * <BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * 
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40BEF866039C
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, OrderManagerPersistenceContext l_process, EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "EqtypeOrderUnitParams mutate";
        log.entering(STR_METHOD_NAME);
        // ------------------------------
        // �P�j�K�{�v���p�e�B�ݒ�`�F�b�N 
        // ------------------------------
        if ((this.equitySwapMarginOrderSpec == null) ||
            (this.orderRootDiv == null))
        {
            return l_orderUnitParams;
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        MainAccount l_mainAccount = null;
        try
        {    
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_orderUnitParams.getAccountId());
        }
        catch (NotFoundException l_nfe)
        {   

            log.error(STR_METHOD_NAME,l_nfe);   
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
        Market l_market = null;
        long l_lngMarketId = l_orderUnitParams.getMarketId();
        try
        {
            l_market =
                l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME,l_nfe);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,this.getClass().getName()+  "." + STR_METHOD_NAME);   
        }
        
        //�l�i����
        l_orderUnitParams.setPriceConditionType(WEB3PriceConditionDef.DEFAULT);        
        //��������
        l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
        //�����������Z�q
        l_orderUnitParams.setOrderCondOperator(null);     
        //�t�w�l��l�^�C�v
        l_orderUnitParams.setStopOrderPrice(null);
        //�iW�w�l�j�����w�l
        l_orderUnitParams.setWLimitPrice(null);
		//��n��
		if (this.getDeliveryDate() != null)
		{
			l_orderUnitParams.setDeliveryDate(this.getDeliveryDate());
		}
        //�ŋ敪�i�������n�j
        l_orderUnitParams.setSwapTaxType(equitySwapMarginOrderSpec.getSwapTaxType());
        //�ٍϋ敪
        l_orderUnitParams.setRepaymentType(this.repaymentType);
        //�ٍϊ����l
        l_orderUnitParams.setRepaymentNum((int)(this.repaymentNum));
        //�ٍϋ敪�iSONAR�j
        l_orderUnitParams.setSonarRepaymentType(this.sonarRepaymentType);

        //���񒍕��̒����`���l��
        l_orderUnitParams.setOrderChanel(this.orderChanel);
        //�����o�H�敪
        l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
                
        //�󒍓���
        if (this.getReceivedDateTime() == null)
        {   
            l_orderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        }
        else
        {
            l_orderUnitParams.setReceivedDateTime(this.receivedDateTime);   
        }
        
        //���ʃR�[�h   
        String l_strOrderRequestNumber = null;            
        if (this.orderRootDiv.equals(WEB3OrderRootDivDef.HOST)){
            l_strOrderRequestNumber = this.orderRequestNumber;
        }
        else
        {            
            String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            String l_lngInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            WEB3HostReqOrderNumberManageService l_numberService =
                (WEB3HostReqOrderNumberManageService) Services.getService(
                WEB3HostReqOrderNumberManageService.class);
            try 
            {
                l_strOrderRequestNumber =
                    l_numberService.getNewNumber(
                    l_lngInstitutionCode,
                    l_strBranchCode,
                    ProductTypeEnum.EQUITY);
            } 
            catch (WEB3BaseException l_ex) 
            {
                log.error(STR_METHOD_NAME,l_ex);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);       
            }            
        }
        l_orderUnitParams.order_request_number = l_strOrderRequestNumber;        
        log.debug(l_strOrderRequestNumber);
        
        //�������ithis�D�����o�H�敪���hHOST�h�̏ꍇ�̂݁jYYYYMMDD�`���ɕϊ����ăZ�b�g����B        
        if (this.orderRootDiv.equals(WEB3OrderRootDivDef.HOST))
        {
            l_orderUnitParams.setBizDate(
                GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(GtlUtils.getSystemTimestamp())
                );
        }
        //�����������t
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate(l_orderUnitParams.getBizDate(),"yyyyMMdd"));
        
        //�`�[No��ݒ肷��
        int l_intRequestNumberLength = l_strOrderRequestNumber.length() - 3;
        String l_strVoucherNo = WEB3MarginBaseNumber.BaseNumber + l_strOrderRequestNumber.substring(l_intRequestNumberLength);
        l_orderUnitParams.setVoucherNo(l_strVoucherNo);
        //���񒍕��̎萔��No     
        l_orderUnitParams.setCommTblNo(null);  
        //���񒍕��̎萔��No�}��
        l_orderUnitParams.setCommTblSubNo(null);
        //���҃R�[�h�iSONAR�j
        l_orderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());
        //�����P��
        l_orderUnitParams.setPrice(null);
        //�T�Z��n���
        l_orderUnitParams.setEstimatedPrice(this.estimatedPrice);
        //���n�v���z
        l_orderUnitParams.setCapitalGain(this.capitalGain);
        //���n�v�Ŋz
        l_orderUnitParams.setCapitalGainTax(this.capitalGainTax);
        //����R�[�h�iSONAR�j
        l_orderUnitParams.setSonarTradedCode(WEB3TransactionTypeSONARDef.SWAP_CONTRACT);
        //�s��R�[�h�iSONAR�j
        MarketRow l_marketRow = (MarketRow) l_market.getDataSourceObject();
        l_orderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        //�萔�����i�R�[�h
        l_orderUnitParams.setCommProductCode(null);
        //�󔄃t���O = �i�u�����N�F�ΏۊO�j
        l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);
        //���������E����敪
        l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.INITIAL_VALUE);

        //�s�ꂩ��m�F�ς݂̒����P��
        l_orderUnitParams.setConfirmedOrderPrice(null);
        //�s�ꂩ��m�F�ς݂̊T�Z��n���
        l_orderUnitParams.setConfirmedEstimatedPrice(null);
        //�s�ꂩ��m�F�ς݂̎��s����
        l_orderUnitParams.setConfirmedExecConditionType(null);
        //�s�ꂩ��m�F�ς݂̒l�i����
        l_orderUnitParams.setConfirmedPriceConditionType(null);
        //���Ϗ��� 
        l_orderUnitParams.setClosingOrderType(this.equitySwapMarginOrderSpec.getClosingOrderType());
        //�����G���[���R�R�[�h
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        //���N�G�X�g�^�C�v 
        l_orderUnitParams.setRequestType(null);
        //���񒍕��̒����P�ʂh�c
        l_orderUnitParams.setFirstOrderUnitId(null);

        l_orderUnitParams.setConfirmedPrice(null);

        //�������ϗ��R�敪
        l_orderUnitParams.setForcedSettleReasonType(null);

        //���F��ԋ敪
        l_orderUnitParams.setApproveStatusType(null);

        //���F�҃R�[�h
        l_orderUnitParams.setApproverCode(null);

        //���F�^�񏳔F����
        l_orderUnitParams.setApproveTimestamp(null);

        //�ۏ؋��ێ���
        l_orderUnitParams.setMarginMaintenanceRate(null);

        //�Ǐؔ�����
        l_orderUnitParams.setAdditionalMarginDate(null);

        //�Ǐ،o�ߓ���
        l_orderUnitParams.setAdditionalMarginAccruedDays(null);

        //���������敪
        //0�F�I�[�v���i0�FDEFAULT�j
        l_orderUnitParams.setForcedExpireType(WEB3ForcedExpireType.OPENING);

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (�M�p�������n�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * �����Ɏw�肳�ꂽ�I�u�W�F�N�g�^�l���A�����̃v���p�e�B�ɐݒ肷��B<BR>
     * @@param l_equitySwapMarginOrderSpec - �M�p�������n�������e�I�u�W�F�N�g�B<BR>
     * @@param l_strPayTypeSONAR - �ٍϋ敪�iSONAR�j�B<BR>
     * @@param l_dblEstimatedPrice - �T�Z��n����B<BR>
     * @@param l_strPayType - �ٍϋ敪�B<BR>
     * @@param l_dblRepaymentNum - �ٍϊ����l�B<BR>
     * @@param l_dblCapitalGain - ���n�v���z�B<BR>
     * @@param l_dblCapitalGainTax - ���n�v�Ŋz�B<BR>
     * @@param l_strOrderChanel - ���񒍕��̒����`���l���B<BR>
     * @@param l_strOrderRootDiv - �����o�H�敪�B<BR>
     * @@roseuid 40BEF76B0341
     */
    public WEB3MarginSwapMarginUpdateInterceptor(
        WEB3MarginSwapContractOrderSpec l_equitySwapMarginOrderSpec, 
        String l_strPayTypeSONAR, 
        double l_dblEstimatedPrice, 
        String l_strPayType, 
        double l_dblRepaymentNum, 
        double l_dblCapitalGain,
        double l_dblCapitalGainTax,
        String l_strOrderChanel,
        String l_strOrderRootDiv) 
    {
        this.equitySwapMarginOrderSpec = l_equitySwapMarginOrderSpec;
        this.capitalGainTax = l_dblCapitalGainTax;
        this.capitalGain = l_dblCapitalGain;
        this.estimatedPrice = l_dblEstimatedPrice;
        this.repaymentNum = l_dblRepaymentNum;
        this.repaymentType = l_strPayType;
        this.sonarRepaymentType = l_strPayTypeSONAR;   
        this.orderChanel = l_strOrderChanel;
        this.orderRootDiv = l_strOrderRootDiv;
    }    
    
    /**
     * (get���ʃR�[�h)<BR>
     * ���ʃR�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 40F37D140369
     */
    public String getOrderRequestNumber() 
    {
        return orderRequestNumber;
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * ���ʃR�[�h���Z�b�g����B<BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h<BR>
     * @@roseuid 40F37D14036A
     */
    public void setOrderRequestNumber(String l_strOrderRequestNumber) 
    {
        orderRequestNumber = l_strOrderRequestNumber;
    }
    
    
    /**
     * (get�󒍓���)<BR>
     * �󒍓������擾����B<BR>
     * @@return String
     * @@roseuid 40F37D140369
     */
    public Date getReceivedDateTime() 
    {
        return receivedDateTime;
    }
    
    /**
     * (set�󒍓���)<BR>
     * �󒍓������擾����B<BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h<BR>
     * @@roseuid 40F37D14036A
     */
    public void setReceivedDateTime(Date l_datReceivedDateTime) 
    {
        receivedDateTime = l_datReceivedDateTime;
    }
    
	/**
	 * (get��n��)<BR>
	 * ��n�����擾����B<BR>
	 * @@return Date
	 * @@roseuid XXXXXXXXXXX
	 */
	public Date getDeliveryDate() 
	{
		return this.deliveryDate;
	}
    
	/**
	 * (set��n��)<BR>
	 * ��n�����Z�b�g����B<BR>
	 * @@param ��n�� - ��n��
	 * @@roseuid XXXXXXXXXXX
	 */
	public void setDeliveryDate(Date l_datDeliveryDate) 
	{
		this.deliveryDate = l_datDeliveryDate;
	}
	
}
@
