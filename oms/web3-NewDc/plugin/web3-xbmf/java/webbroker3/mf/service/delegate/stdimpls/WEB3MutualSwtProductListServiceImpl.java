head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.43.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwtProductListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�抷������ꗗ�Ɖ�T�[�r�XImpl(WEB3MutualSwtProductListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ��O�� (���u) �V�K�쐬                   
Revesion History : 2007/04/06 ������ (���u) ����005
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.message.WEB3MutualBuyProductGroup;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.message.WEB3MutualSwTargetListRequest;
import webbroker3.mf.message.WEB3MutualSwTargetListResponse;
import webbroker3.mf.service.delegate.WEB3MutualSwtProductListService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3Toolkit;

/**
 * ���M�抷������ꗗ�Ɖ�T�[�r�XImpl
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSwtProductListServiceImpl extends 
    WEB3MutualClientRequestService implements WEB3MutualSwtProductListService 
{    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwtProductListServiceImpl.class);
        
    /**
     * ���M�抷������ꗗ�Ɖ�T�[�r�X�������s���B <BR>
     * <BR>
     * �V�[�P���X�}�u�i���M�j�抷������ꗗ�v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR> 
     *  1.11 is�V�X�e���戵( )<BR>
     *      �߂�l == false �̏ꍇ,��O(�戵�s�����G���[)���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00362<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR> 
     *  1.12  is���抷�\(Date)<BR>
     *      �߂�l == false �̏ꍇ,��O(����s�����G���[)���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00363<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.13  is�抷�\( )<BR>
     *      �߂�l == false �̏ꍇ,��O(�抷�s�����G���[)���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00375<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.15 calc���\�c������(SubAccount, �g�����M����, String)<BR>
     *      �߂�l == 0 �̏ꍇ,��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02270<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.16 get�抷�\�������X�g(Timestamp)<BR>
     *      �߂�l == null  or �߂�l.size == 0 �̏ꍇ ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01364<BR>
     * ==========================================================<BR>
     * <BR>
     * =========================================================<BR> 
     *  1.17.2 get�\���Ώۖ������X�g(List, String[])<BR>
     *      �߂�l == null  or �߂�l.size == 0 �̏ꍇ ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01364<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40B2ED11023F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3MutualSwTargetListRequest l_swtListRequest = null;
        
        if (l_request instanceof WEB3MutualSwTargetListRequest)
        {
            l_swtListRequest = (WEB3MutualSwTargetListRequest) l_request;
        }
        else
        {
            log.debug(STR_METHOD_NAME + " __Error[���͒l���s���ł�]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
        //1.1 ���͓��e�`�F�b�N 
        l_swtListRequest.validate();

        // --�ڋq�ʎ����~�����`�F�b�N 
        //1.2 this.get�⏕����( )���R�[�����A�⏕�����I�u�W�F�N�g���擾����B
        SubAccount l_subAccount = this.getSubAccount();
        
        //1.3 getAsset(���YID : long)
        //�ۗL���Y�I�u�W�F�N�g���擾����B 
        //[����] 
        //���YID�F ���N�G�X�g.���YID 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_mfPositionManager =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        AssetRow l_assetRow = null;
        try
        {
            Asset l_asset = 
                l_mfPositionManager.getAsset(
                    Long.parseLong(l_swtListRequest.id));
            l_assetRow = (AssetRow)l_asset.getDataSourceObject();
            
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�ۗL���Y�Y���f�[�^�Ȃ��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���Y�Y���f�[�^�Ȃ��B");
        }
        
        //1.4 getProduct(����ID : long)
        //�����I�u�W�F�N�g���擾����B 
        //[����] 
        //����ID�F �ۗL���Y.����ID        
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        
        Product l_product = null;
        try
        {
            l_product = l_mfProductManager.getProduct(l_assetRow.getProductId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        
        //1.5 to����(Row)(�g�����M�����}�l�[�W��::to����)
        //���M�����I�u�W�F�N�g���擾����B 
        //[����] 
        //Row�I�u�W�F�N�g�F getProduct()�̖߂�l����擾����Row�I�u�W�F�N�g         
        Row l_row = (Row)l_product.getDataSourceObject();
        
        WEB3MutualFundProduct l_mfProduct = (WEB3MutualFundProduct)
            l_mfProductManager.toProduct(l_row);
        
        //1.6 reset�����R�[�h(�����R�[�h : String)
        //����J�����_�R���e�L�X�g�̖����R�[�h���X�V����B 
        //[����] 
        //�����R�[�h�F ���M����.getProductCode()�̖߂�l
        WEB3MutualFundTradingTimeManagement.resetProductCode(
            l_mfProduct.getProductCode());
        
        //1.7 ��t�����A���t���[�����Z�b�g����B
        WEB3MutualFundTradingTimeManagement.setTimestamp();
        
        //1.8 validate������t�\( )
        //  �ȉ��̃`�F�b�N���s���B   
        //  ��t���ԃ`�F�b�N 
        //  �V�X�e����~�`�F�b�N 
        WEB3MutualFundTradingTimeManagement.validateOrderAccept();
        
        //1.9 get���M������( )(���M������ԊǗ�::get���M������)
        //���������擾����B
        Timestamp l_datBizDate = new Timestamp(
            WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate().getTime());
        
        //1.10 validate����\�ڋq(�ڋq : �ڋq, ������ : Timestamp)
        //����\�Ȍڋq���ǂ������`�F�b�N����B 

        //[����] 
        //�ڋq�F �⏕����.getMainAccount()�̖߂�l 
        //�������F get���M������()�̖߂�l
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        
        WEB3GentradeMainAccount l_genMainAccount = 
            (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        
        OrderValidationResult l_validationResult =  
            l_orderValidator.validateAccountForTrading(
                l_genMainAccount,
                l_datBizDate);
        
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.11 is�V�X�e���戵( )
        boolean l_blnIsSystemHandling = l_mfProduct.isSystemHandling();
        
        //�߂�l == false �̏ꍇ,��O(�戵�s�����G���[)���X���[����B
        if (!l_blnIsSystemHandling)
        {
            log.debug("�戵�s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00362,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�戵�s�����G���[�B");
        }
        
        //1.12  is���抷�\(Date)
        boolean l_blnIsSellSwitchingPoss = 
            l_mfProduct.isSellSwitchingPossible(l_datBizDate);
        
        //�߂�l == false �̏ꍇ,��O(����s�����G���[)���X���[����B
        if (!l_blnIsSellSwitchingPoss)
        {
            log.debug("����s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00363,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����s�����G���[�B");
        }
        
        //1.13  is�抷�\( )
        boolean l_blnIsSwitchingAble = 
            l_mfProduct.isSwitchingAble();
        //�߂�l == false �̏ꍇ,��O(�抷�s�����G���[)���X���[����B
        if (!l_blnIsSwitchingAble)
        {
            log.debug("�抷�s�����G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00375,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�抷�s�����G���[�B");
        }
        
        //1.14 validate�ً}��~(�g�����M����, String)
        //�抷���������ً}��~����Ă��邩�ǂ������`�F�b�N����B 
        //[����] 
        //�g�����M�����F ���M�����I�u�W�F�N�g 
        //�����敪�F �g�抷�h 
        WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
            (WEB3MutualFundOrderManagerReusableValidationsCheck)
                MutualFundProductTypeOrderManagerReusableValidations.getInstance();

        l_validationsCheck.validateEmergencyStop(
            l_mfProduct, WEB3ProcessDivDef.SWITCHING);       
        
        //1.15 calc���\�c������(SubAccount, �g�����M����, String)
        //�抷�\�c���������擾����B 

        //�m�����n 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        //�g�����M�����F ���M�����I�u�W�F�N�g 
        //���YID�F ���N�G�X�g.���YID 
        double l_dblSellPossQty = 
            l_mfPositionManager.calcSellPossiblePositionQty(
                l_subAccount, l_mfProduct, l_swtListRequest.id);
        
        //�߂�l == 0 �̏ꍇ,��O���X���[����B
        if (l_dblSellPossQty == 0)
        {
            log.debug("�抷�\�c�������s���G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02270,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�抷�\�c�������s���G���[�B");
        }
        
        //1.16 get�抷�\�������X�g()
        //�抷�\�Ȗ����̃��X�g���擾����B 
        List l_lisSwtPossProduct = 
            l_mfProduct.getSwitchingAbleProductList();
        
        // �߂�l == null  or �߂�l.size == 0 �̏ꍇ ��O���X���[����B
        if (l_lisSwtPossProduct == null || l_lisSwtPossProduct.size() == 0)
        {
            log.debug("�抷�\�����Ȃ��G���[�B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01364,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�抷�\�����Ȃ��G���[�B");
        }
        
        List l_lisObjProduct = null;
        
        //1.17  (*1)���N�G�X�g.���M�����J�e�S���[�R�[�h != null �̏ꍇ
        if (l_swtListRequest.categoryCode != null)
        {
            //1.17.1 get�\���ΏۃJ�e�S���[�R�[�h(String, String)
            //�\���ΏۂƂȂ铊�M�����J�e�S���[�R�[�h�̔z����擾����B 
            //[����] 
            //�،���ЃR�[�h�F �ڋq.getInstitution().getInstitutionCode()�̖߂�l 
            //�J�e�S���[�R�[�h�F ���N�G�X�g.���M�����J�e�S���[�R�[�h 
            String[] l_strCategoryCodes = 
                this.getObjCategoryCode(
                    l_genMainAccount.getInstitution().getInstitutionCode(), 
                    l_swtListRequest.categoryCode);
            
            //1.17.2 get�\���Ώۖ������X�g(List, String[])
            //�ꗗ�̕\���ΏۂƂȂ�����̃��X�g���擾����B 
            //[����] 
            //�抷�\�����F get�抷�\�������X�g()�̖߂�l 
            //�J�e�S���[�R�[�h�F get�\���ΏۃJ�e�S���[�R�[�h()�̖߂�l 
            l_lisObjProduct = 
                this.getObjProductList(l_lisSwtPossProduct, l_strCategoryCodes);
            
            //�߂�l == null  or �߂�l.size == 0 �̏ꍇ ��O���X���[����B
            if (l_lisObjProduct == null || l_lisObjProduct.size() == 0)
            {
                log.debug("�抷�\�����Ȃ��G���[�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01364,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�抷�\�����Ȃ��G���[�B");
            }
        }          
        
        //1.18 WEB3PageIndexInfo(l_list : List, l_intRequestPageIndex : int, l_intRequestPageSize : int)
        //�y�[�W���O�����I�u�W�F�N�g�𐶐�����B 

        //[����] 
        //l_list�F �i�ȉ��̂Ƃ���j 
        //   ���N�G�X�g.���M�����J�e�S���[�R�[�h != null �̏ꍇ�Aget�\���Ώۖ������X�g()�̖߂�l 
        //   ���N�G�X�g.���M�����J�e�S���[�R�[�h == null �̏ꍇ�Aget�抷�\�������X�g()�̖߂�l 
        //l_intRequestPageIndex�F ���N�G�X�g.�v���y�[�W�ԍ� 
        //l_intRequestPageSize�F ���N�G�X�g.�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_swtListRequest.pageSize);
        int l_intPageIndex = Integer.parseInt(l_swtListRequest.pageIndex);  
        
        WEB3PageIndexInfo l_pageIndexInfo = null;
        
        if (l_swtListRequest.categoryCode != null)
        {
            log.debug("���N�G�X�g.���M�����J�e�S���[�R�[�h != null �̏ꍇ");
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisObjProduct, 
                l_intPageIndex, 
                l_intPageSize);            
        }
        else
        {
            log.debug("���N�G�X�g.���M�����J�e�S���[�R�[�h == null �̏ꍇ");
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisSwtPossProduct, 
                l_intPageIndex, 
                l_intPageSize);            
        }
        
        List l_lisBuyProdcutGroup = new Vector();
        
        //1.19 getArrayReturned( )
        //�\���Ώۃy�[�W�ɊY������I�u�W�F�N�g�z����擾����B 
        WEB3MutualFundProduct[] l_mfArrProducts = 
            (WEB3MutualFundProduct[]) l_pageIndexInfo.getArrayReturned(
                WEB3MutualFundProduct.class);
        
        log.debug("getArrayReturned()�̖߂�l��size = " + l_mfArrProducts.length);
        
        //1.20 reset������t�g�����U�N�V����(String)
        //����J�����_�R���e�L�X�g�̒�����t�g�����U�N�V�������X�V����B
		//[����]
		//������t�g�����U�N�V�����F ������t�g�����U�N�V����.�h���t�h
		WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
				WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);

        //1.21 (*2)getArrayReturned()�̖߂�l�̊e�v�f�ɂ���Loop����
        for (int i = 0; i < l_mfArrProducts.length; i++)
        {
			//1.21.1 reset�����R�[�h(�����R�[�h : String)
			//����J�����_�R���e�L�X�g�̖����R�[�h���X�V����B 

			//[����] 
			//�����R�[�h�F ���M����.getProductCode()�̖߂�l 
			WEB3MutualFundTradingTimeManagement.resetProductCode(l_mfArrProducts[i].getProductCode());

			//1.21.2 setTimestamp( )
			//��t�����A���t���[�����Z�b�g����B 
			WEB3MutualFundTradingTimeManagement.setTimestamp();
            
            //1.21.3 ���M���t�����ꗗ�s�̃C���X�^���X�𐶐�����B 
            WEB3MutualBuyProductGroup l_mfBuyProdcutGroup = new WEB3MutualBuyProductGroup();
            MutualFundProductRow l_mfProductRow = (MutualFundProductRow)
                l_mfArrProducts[i].getDataSourceObject();
            
            //1.21.4 (*3)�v���p�e�B�Z�b�g
            //(*3)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

            //ID          = ���M����.getProductId()�̖߂�l
            l_mfBuyProdcutGroup.id = l_mfArrProducts[i].getProductId() + "";
            
            //�����R�[�h           = ���M����.getProductCode()�̖߂�l
            l_mfBuyProdcutGroup.mutualProductCode = l_mfArrProducts[i].getProductCode();
            
            //������         = ���M����.get������()�̖߂�l
			l_mfBuyProdcutGroup.mutualProductName = l_mfArrProducts[i].getMutualProductName();
            
            //���M�����J�e�S���[�R�[�h    = ���M����.get�J�e�S���[�R�[�h()�̖߂�l
            l_mfBuyProdcutGroup.categoryCode = l_mfProductRow.getCategoryCode();
            
            //���t����z�ʉ݃R�[�h = ���M����.get�ʉ݃R�[�h()�̖߂�l
            l_mfBuyProdcutGroup.constantValueCurrencyCode = l_mfArrProducts[i].getCurrencyCode();
            
            //���t����z      = ���M����.get���t����z()�̖߂�l
            l_mfBuyProdcutGroup.constantValue = l_mfArrProducts[i].getConstantValue() + "";
            
            //���t����z�K�p��   = ���M����.get����z�K�p��()�̖߂�l
            l_mfBuyProdcutGroup.constantValueAppDate = l_mfArrProducts[i].getConstantValueAppDate();
            
            //�V�K���t���P�ʌ���   = ���M����.get�P�ʌ���(�V�K���t)()�̖߂�l
            l_mfBuyProdcutGroup.newBuyUnitQty = l_mfArrProducts[i].getNewBuyUnitQty() + "";
            
            //�V�K���t���Œ����   = ���M����.get�Œ����(�V�K���t)()�̖߂�l
            l_mfBuyProdcutGroup.newBuyMinQty = l_mfArrProducts[i].getNewBuyMinQty() + "";
            
            //�V�K���t���P�ʋ��z   = ���M����.get�P�ʋ��z(�V�K���t)()�̖߂�l
            l_mfBuyProdcutGroup.newBuyUnitAmt = l_mfArrProducts[i].getNewBuyUnitAmt() + "";
            
            //�V�K���t���Œ���z   = ���M����.get�Œ���z(�V�K���t)()�̖߂�l
            l_mfBuyProdcutGroup.newBuyMinAmt = l_mfArrProducts[i].getNewBuyMinAmt() + "";
            
            //�ǉ����t���P�ʌ���   = ���M����.get�P�ʌ���(�ǉ����t)()�̖߂�l
            l_mfBuyProdcutGroup.addBuyUnitQty = l_mfArrProducts[i].getAddBuyUnitQty() + "";
            
            //�ǉ����t���Œ����   = ���M����.get�Œ����(�ǉ����t)()�̖߂�l
            l_mfBuyProdcutGroup.addBuyMinQty = l_mfArrProducts[i].getAddBuyMinQty() + "";
            
            //�ǉ����t���P�ʋ��z   = ���M����.get�P�ʋ��z(�ǉ����t)()�̖߂�l
            l_mfBuyProdcutGroup.addBuyUnitAmt = l_mfArrProducts[i].getAddBuyUnitAmt() + "";
            
            //�ǉ����t���Œ���z   = ���M����.get�Œ���z(�ǉ����t)()�̖߂�l
            l_mfBuyProdcutGroup.addBuyMinAmt = l_mfArrProducts[i].getAddBuyMinAmt() + "";
            
            //������t���؎���        = ���M������ԊǗ�.get������t����()���R�[�����A
            //               �߂��ꂽ�l�̂P�b��̎���"HHMMSS"��"HH:MM"�ɕҏW���ăZ�b�g����B
            String l_strOrderCloseTime = 
                WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
            Date l_datOrderCloseTime = 
                WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
            l_strOrderCloseTime = 
                WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss");  
            l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
                + WEB3GentradeTimeDef.STR_COLON
                + l_strOrderCloseTime.substring(2, 4);            
            
            l_mfBuyProdcutGroup.orderCloseTime = l_strOrderCloseTime;
            
            //���l�敪            = null
            l_mfBuyProdcutGroup.noteType = null;
            
            //1.21.5 is���t�\(Date)(�g�����M����::is���t�\)
            //���Y���������t�\���ǂ����𔻒肷��B 

            //[����] 
            //�������F get���M������()�̖߂�l 
            boolean l_blnIsBuyPoss = 
                l_mfArrProducts[i].isAcquiredPossible(l_datBizDate);
            
            //�߂�l == false �̏ꍇ�A�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
            //���M���t�����ꗗ�s.���l�敪 = �h���t��~���h
            if (!l_blnIsBuyPoss)
            {
                l_mfBuyProdcutGroup.noteType = WEB3RemarkDivDef.HANDLING_DISABLE;
            }
            
            //1.21.6 validate������t�\( )
            //���Y��������t�\���ǂ������`�F�b�N����B 
            try
            {
                WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            }
            catch(WEB3BaseException l_ex)
            {
                //��O���X���[���ꂽ�ꍇ�A�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
                //���M���t�����ꗗ�s.���l�敪 = �h��t���ԊO�h
                l_mfBuyProdcutGroup.noteType = WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
            }
            
            //1.21.7 validate�ً}��~(�g�����M����, String)
            //���Y�������ً}��~����Ă��邩�ǂ������`�F�b�N����B 

            //[����] 
            //�g�����M�����F ���M�����I�u�W�F�N�g 
            //�����敪�F �g���t�h 
            try
            {
                l_validationsCheck.validateEmergencyStop(l_mfArrProducts[i], WEB3ProcessDivDef.BUY);
            }
            catch(WEB3BaseException l_ex)
            {
                //��O���X���[���ꂽ�ꍇ�A�ȉ��̂Ƃ���Ƀv���p�e�B���Z�b�g����B
                //���M���t�����ꗗ�s.���l�敪 = �h�ً}��~���h
                l_mfBuyProdcutGroup.noteType = WEB3RemarkDivDef.EMERGENCY_STOP;
            }
            l_lisBuyProdcutGroup.add(l_mfBuyProdcutGroup);
        }
        
        int l_intListSize = l_lisBuyProdcutGroup.size();
        
        WEB3MutualBuyProductGroup[] l_mfBuyProductGroups = 
            new WEB3MutualBuyProductGroup[l_intListSize];
        
        l_lisBuyProdcutGroup.toArray(l_mfBuyProductGroups);
        
        //1.22 get���M�����J�e�S���[���X�g(String)
        //���M�����J�e�S���[�R�[�h����������B 

        //[����] 
        //�،���ЃR�[�h�F �ڋq.getInstitution().getInstitutionCode()�̖߂�l 
        List l_lisProductCategory = 
            l_mfProductManager.getMutualFundProductCategoryList(
                l_genMainAccount.getInstitution().getInstitutionCode());        
        
        //1.23 create���M�����J�e�S���[�ꗗ(List)
        //���M�����J�e�S���[�ꗗ���쐬����B 

        //[����] 
        //�����J�e�S���[�ꗗ�F get���M�����J�e�S���[���X�g()�̖߂�l 
        WEB3MutualProductCategoryUnit[] l_productCategoryUnits = 
            l_mfProductManager.createMutualFundProductCategoryList(
                l_lisProductCategory);
        
        //1.24 getPageIndex( ) �\���y�[�W�ԍ����擾����B 
        String l_strPageIndex = l_pageIndexInfo.getPageIndex() + "";

        //1.25 getTotalPages( ) ���y�[�W�����擾����B
        String l_strTotalPages = l_pageIndexInfo.getTotalPages() + ""; 
        
        //1.26 getTotalRecords( ) �����R�[�h�����擾����B
        String l_strTotalRecords = l_pageIndexInfo.getTotalRecords() + ""; 
        
        //1.27 createResponse( )
        //���X�|���X�f�[�^�𐶐�����B
        WEB3MutualSwTargetListResponse l_response = 
            (WEB3MutualSwTargetListResponse) l_request.createResponse();
        
        //1.28 (*4)�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

        //���M�����J�e�S���[�ꗗ = create���M�����J�e�S���[�ꗗ()�̖߂�l
        l_response.categoryList = l_productCategoryUnits;
        
        //�\���y�[�W�ԍ�     = getPageIndex()�̖߂�l
        l_response.pageIndex = l_strPageIndex; 
        
        //���y�[�W��       = getTotalPages()�̖߂�l
        l_response.totalPages = l_strTotalPages; 
        
        //�����R�[�h��      = getTotalRecords()�̖߂�l
        l_response.totalRecords = l_strTotalRecords;
        
        //�抷������ꗗ     = ���M���t�����ꗗ�s�̔z��
        l_response.switchingProductGroups = l_mfBuyProductGroups;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�\���ΏۃJ�e�S���[�R�[�h)<BR>
     * �ꗗ�ɕ\���ΏۂƂȂ�J�e�S���[�R�[�h�̔z����擾����B <BR>
     * <BR>
     * �P�j�w�肳�ꂽ���M�����J�e�S���[�R�[�h�̃J�e�S���[�ƃ����N���鉺�ʂ� <BR>
     * �J�e�S���[���擾����B <BR>
     * <BR>
     *    �g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g()���R�[������B<BR>
     * <BR>
     *    [����] <BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h <BR>
     *    �J�e�S���[�R�[�h�F ����.�J�e�S���[�R�[�h <BR>
     * <BR>
     * �Q�j�擾�����J�e�S���[�̃��X�g����J�e�S���[�R�[�h�̔z��𐶐�����B <BR>
     * <BR>
     * �R�j���������J�e�S���[�R�[�h�̔z��Ɉ���.�J�e�S���[�R�[�h��ǉ����A<BR>
     * ���̔z���ԋp����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strCategoryCode - �J�e�S���[�R�[�h
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 40BDAA350378
     */
    protected String[] getObjCategoryCode(
        String l_strInstitutionCode, 
        String l_strCategoryCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getObjCategoryCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�w�肳�ꂽ���M�����J�e�S���[�R�[�h�̃J�e�S���[�ƃ����N���鉺�ʂ̃J�e�S���[���擾����B 
        //�g�����M�����}�l�[�W��.get���ʓ��M�����J�e�S���[���X�g()���R�[������B 

        //[����] 
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h 
        //�J�e�S���[�R�[�h�F ����.�J�e�S���[�R�[�h 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3MutualFundProductManager l_mfProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        
        List l_lisLowMfProductCategory = 
            l_mfProductManager.getLowMutualFundProductCategoryList(
                l_strInstitutionCode, l_strCategoryCode);
        
        //�Q�j�擾�����J�e�S���[�̃��X�g����J�e�S���[�R�[�h�̔z��𐶐�����B 
        List l_lisCategoryCodes = new Vector();
        if (l_lisLowMfProductCategory != null)
        {            
            for (int i = 0; i < l_lisLowMfProductCategory.size(); i++)
            {
                MutualFundProductCategoryRow l_mfProductCategoryRow = 
                    (MutualFundProductCategoryRow)l_lisLowMfProductCategory.get(i);
                
                l_lisCategoryCodes.add(l_mfProductCategoryRow.getCategoryCode());
            }
        }
        
        //�R�j���������J�e�S���[�R�[�h�̔z��Ɉ���.�J�e�S���[�R�[�h��ǉ����A���̔z���ԋp����B
        l_lisCategoryCodes.add(l_strCategoryCode);
        
        String[] l_strCategoryCodes = new String[l_lisCategoryCodes.size()];
        l_lisCategoryCodes.toArray(l_strCategoryCodes);
       
        log.exiting(STR_METHOD_NAME);
        return l_strCategoryCodes;
    }
    
    /**
     * (get�\���Ώۖ������X�g)<BR>
     * �ꗗ�̕\���ΏۂƂȂ�����̃��X�g���擾����B <BR>
     * <BR>
     * �P�j��̃��X�g�𐶐�����B <BR>
     * <BR>
     * �Q�j�抷�\�����̃��X�g�̊e�v�f�ɂ��āA�ȉ��̏������s���B <BR>
     * <BR>
     * �Q�|�P�j����.�J�e�S���[�R�[�h�Ɠ����R�[�h������.�J�e�S���[�R�[�h��<BR>
     *      �z��ɑ��݂��邩���`�F�b�N����B <BR>
     * <BR>
     * �Q�|�Q�j���݂���ꍇ�́A�P�j�Ő����������X�g�ɒǉ�����B <BR>
     * <BR>
     * �R�j�������ꂽ���X�g��ԋp����B <BR>
     * <BR>
     * @@param l_lisSwtPossProduct - �抷�\����
     * @@param l_strCategoryCode - �J�e�S���[�R�[�h
     * @@return List
     * @@roseuid 40BDAA350378
     */
    protected List getObjProductList(
        List l_lisSwtPossProduct, String[] l_strCategoryCode)
    {
        String STR_METHOD_NAME = "getObjProductList(List, String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisSwtPossProduct == null || l_strCategoryCode == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j��̃��X�g�𐶐�����B 
        List l_lisObjProduct = new Vector();

        //�Q�j�抷�\�����̃��X�g�̊e�v�f�ɂ��āA�ȉ��̏������s���B     
        for (int i = 0; i < l_lisSwtPossProduct.size(); i++)
        {
            WEB3MutualFundProduct l_mfSwtProduct = 
                (WEB3MutualFundProduct)l_lisSwtPossProduct.get(i);
            
            MutualFundProductRow l_mfProductRow = (MutualFundProductRow)l_mfSwtProduct.getDataSourceObject();
            
            String l_strSwtCategoryCode = l_mfProductRow.getCategoryCode();
            
            //�Q�|�P�j����.�J�e�S���[�R�[�h�Ɠ����R�[�h������.�J�e�S���[�R�[�h�̔z��ɑ��݂��邩���`�F�b�N����B             
            if (WEB3Toolkit.contain(l_strCategoryCode, l_strSwtCategoryCode))
            {
                //�Q�|�Q�j���݂���ꍇ�́A�P�j�Ő����������X�g�ɒǉ�����B 
                l_lisObjProduct.add(l_mfSwtProduct);
            }
        }
        //�R�j�������ꂽ���X�g��ԋp����B         
        
        log.exiting(STR_METHOD_NAME);
        return l_lisObjProduct;
    }
}
@
