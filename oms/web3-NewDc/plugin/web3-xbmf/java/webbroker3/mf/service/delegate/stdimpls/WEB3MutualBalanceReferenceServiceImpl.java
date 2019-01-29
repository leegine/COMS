head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.41.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�c���Ɖ�T�[�r�X�����N���X(WEB3MutualBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ������ (���u) �V�K�쐬
Revesion History : 2005/10/23 ���� (���u) �t�B�f���e�B�Ή�  
Revesion History : 2006/05/15 �юu��(���u) �d�l�ύX�i���f��) :411,415
Revesion History : 2007/02/03 �����F (���u) �d�l�ύX�E���f��522
Revesion History : 2007/02/16 �đo�g (���u) �d�l�ύX�E���f��541
Revesion History : 2007/03/26 ����� (���u) �d�l�ύX�E���f��552
Revesion History : 2007/04/09 ������ (���u) �d�l�ύX�E���f��558
Revesion History : 2008/04/21 ���u�� (���u) �d�l�ύX�E���f��595
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundAsset;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualClientRequestService;
import webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck;
import webbroker3.mf.WEB3MutualFundPositionManager;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.WEB3MutualFundTradingTimeManagement;
import webbroker3.mf.define.WEB3MFAccountDivDef;
import webbroker3.mf.define.WEB3MFEstimatedPriceCurrencyCodeDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.mf.define.WEB3RemarkDivDef;
import webbroker3.mf.message.WEB3MutualBalanceReferenceComparator;
import webbroker3.mf.message.WEB3MutualBalanceReferenceDetailUnit;
import webbroker3.mf.message.WEB3MutualBalanceReferenceRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse;
import webbroker3.mf.message.WEB3MutualProductCategoryUnit;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mf.service.delegate.WEB3MutualBalanceReferenceService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�c���Ɖ�T�[�r�XImpl)<BR>
 * ���M�c���Ɖ�T�[�r�X�����N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceServiceImpl extends WEB3MutualClientRequestService implements WEB3MutualBalanceReferenceService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceServiceImpl.class);
   
   /**
    * ���M�c���Ɖ�����s���B<BR>
    * <BR>
    * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
    * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
    * <BR>
    * �����M�c���Ɖ�c�����v���N�G�X�g�̏ꍇ<BR>
    * �@@this.get�c�����v()���\�b�h���R�[������B<BR>
    * <BR>
    * �����M�c���Ɖ�N�G�X�g�̏ꍇ<BR>
    * �@@this.get�c���Ɖ�()���\�b�h���R�[������B<BR>
    * @@param l_request - ���N�G�X�g
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@throws WEB3BaseException
    * @@roseuid 41AD8E4E0243
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
       log.entering(STR_METHOD_NAME);

       if (l_request == null)
       {
           log.debug(" __parameter_error__");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       WEB3GenResponse l_response = null;
       if (l_request instanceof WEB3MutualBalanceReferenceRequest)
       {
           //this.get�c���Ɖ�()
           l_response =
               this.getBalanceReference((WEB3MutualBalanceReferenceRequest) l_request);
       }
       else if (l_request instanceof WEB3MutualBalanceReferenceTotalRequest)
       {
           //this.get�c�����v()
           l_response =
               this.getBalanceReferenceTotal((WEB3MutualBalanceReferenceTotalRequest) l_request);
       }
       else
       {
           log.debug(
               STR_METHOD_NAME
                   + " __Request "
                   + " WEB3MutualBalanceReferenceRequest "
                   + " �� WEB3MutualBalanceReferenceTotalRequest�ȊO�ł���");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80018,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (get�c���Ɖ�)<BR>
    * ���M�c���Ɖ�����s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u(���M)get�c���Ɖ�v�Q��
    * @@param l_request - ���M�c���Ɖ�N�G�X�g�I�u�W�F�N�g
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceResponse
    * @@throws WEB3BaseException
    * @@roseuid 41AD8E4C031E
    */
   protected WEB3MutualBalanceReferenceResponse getBalanceReference(
       WEB3MutualBalanceReferenceRequest l_request) 
           throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "getBalanceReference()";
       log.entering(STR_METHOD_NAME);
       
       if (l_request == null)
       {
           log.debug(" __parameter_error__");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       // 1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
       l_request.validate();
       
       //���M�E�O��MMF�\���敪
       //��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���
       if (l_request.mutualFrgnMmfDisplayDiv == null)
       {
           l_request.mutualFrgnMmfDisplayDiv =
               WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
       }

       // 1.2 validate������t�\
       WEB3MutualFundTradingTimeManagement.validateOrderAccept();
       
       // 1.3 �⏕�����I�u�W�F�N�g���擾����
       SubAccount l_subAccount = this.getSubAccount();
       
       //�g�����M�|�W�V�����}�l�[�W�����擾����
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3MutualFundPositionManager l_web3MfPositionMgr =
           (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
               ProductTypeEnum.MUTUAL_FUND).getPositionManager();
       
       // 1.4 �ۗL���Y�����ꗗ���擾���� 
       //  [����] 
       //    �⏕�����F�@@get�⏕����()�̖߂�l
       //    ���M��O��MMF�\���敪�F�@@���N�G�X�g.���M��O��MMF�\���敪
       List l_lisAsserts = l_web3MfPositionMgr.getAssets(
           l_subAccount, l_request.mutualFrgnMmfDisplayDiv);
       
       // 1.5 ArrayList�𐶐�����B
       List l_lisBalanceReferenceDetailUnits = new Vector();       

       int l_intAssertsLength = 0;
       if(l_lisAsserts != null)
       {
           l_intAssertsLength = l_lisAsserts.size();
       }
       
       // �@@�|�g�����M�����}�l�[�W�����擾����
       WEB3MutualFundProductManager l_productManager =
           (WEB3MutualFundProductManager) l_finApp.getTradingModule(
               ProductTypeEnum.MUTUAL_FUND).getProductManager();
       
       WEB3MutualFundOrderManagerReusableValidationsCheck l_validationsCheck = 
           (WEB3MutualFundOrderManagerReusableValidationsCheck) 
               MutualFundProductTypeOrderManagerReusableValidations.getInstance();
       
       // 1.6 getAssets()�̖߂�l�̗v�f(=�ۗL���YParams)����Loop���� 
       for(int i = 0; i < l_intAssertsLength; i++)
       {
           MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAsserts.get(i);
           AssetRow l_assetRow = (AssetRow) l_mfAsset.getDataSourceObject();
           
           // 1.6.1 ����ID���擾����
           long l_lngProductId = l_assetRow.getProductId();

           WEB3MutualFundProduct l_mfProduct = null;
           try
           {
               // 1.6.2 �����I�u�W�F�N�g���擾����  
               // [����] 
               // ����ID�F�@@�����Ώۂۗ̕L���YParams.getProductId()
               Product l_product = 
                   l_productManager.getProduct(l_lngProductId);
               
               // 1.6.3 �g�����M�������擾����B
               // [����] 
               // ����Row�F�@@getProduct()�̖߂�l
               l_mfProduct = 
                   (WEB3MutualFundProduct) l_productManager.toProduct(
                       (MutualFundProductRow) l_product.getDataSourceObject());
           }
           catch (NotFoundException l_ex)
           {
               log.error("__NotFoundException__ �����I�u�W�F�N�g���擾�ł��Ȃ�!");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
           }  
           
           // 1.6.4 ������ԊǗ��̖����R�[�h��ύX����B 
           // �m�����n 
           //  �����R�[�h�F�@@to����()�̖߂�l.�����R�[�h
           WEB3MutualFundTradingTimeManagement.resetProductCode(
               l_mfProduct.getProductCode());
           
           // 1.6.5 ��t�����A���t���[�����ăZ�b�g����B
           WEB3MutualFundTradingTimeManagement.setTimestamp();
           
           // 1.6.6 ���\�c���������擾����B 
           // [�����n 
           // �⏕�����F�@@get�⏕����()�̖߂�l 
           // �g�����M�����F�@@to����()�̖߂�l
           //���YID�F�@@getAssets()�Ŏ擾���������Ώۂۗ̕L���Y�e�[�u��Params.get���YID
           double l_dblSellPossiblePositionQty = 
		       l_web3MfPositionMgr.calcSellPossiblePositionQty(
                   l_subAccount, 
                   l_mfProduct, 
                   l_mfAsset.getAssetId() + "");           
           
           // 1.6.7 ��񒆊T�Z�������擾����B 
           // [�����n 
			//�⏕�����F�@@get�⏕����()�̖߂�l 
			//�g�����M�����F�@@to����()�̖߂�l 
			//�ŋ敪�F�@@getAssets()�Ŏ擾���������Ώۂۗ̕L���Y�e�[�u��Params.get�ŋ敪 
			//�ۗL���Y:�F�@@getAssets()�Ŏ擾���������Ώۂۗ̕L���Y
           double l_dblSellingEstimatedQty = 
		       l_web3MfPositionMgr.calcSellingEstimatedQty(
		           l_subAccount, l_mfProduct, l_mfAsset.getTaxType(), l_mfAsset);
           
           // 1.6.8 ���M�^�C�v���擾����B
           MutualFundTypeEnum l_mfTypeEnum = l_mfProduct.getMutualFundType();
           
           // 1.6.9 ������t���؎��Ԃ��擾����B
           String l_strOrderCloseTime = 
               WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
           
           // 1.6.10 ���M�c���Ɖ�׃C���X�^���X�𐶐�����B
           WEB3MutualBalanceReferenceDetailUnit l_mfBalanceReferenceDetailUnit = 
               new WEB3MutualBalanceReferenceDetailUnit();
           
           // 1.6.11 ���M�c���Ɖ�ׂɈȉ��̃v���p�e�B���Z�b�g����B
           //�Q�l����z
           String l_strConstantValue = null;
           //�抷���P�ʌ���
           String l_strSwitchingUnitQty = null;
           //�抷���Œ����
           String l_strSwitchingMinQty = null;
           //�抷���P�ʋ��z
           String l_strSwitchingUnitAmt = null;
           //�抷���Œ���z
           String l_strSwitchingMinAmt = null;
           //���M����.is�O��MMF()==false�̏ꍇ
           if (!l_mfProduct.isFrgnMmf())
           {
               //�g�����M����.get��񉿊z
               l_strConstantValue = WEB3StringTypeUtility.formatNumber(l_mfProduct.getSellValue());
               //�g�����M����.get�P�ʌ����i�抷�j
               l_strSwitchingUnitQty = l_mfProduct.getSwitchingUnitQty() + "";
               //�g�����M����.get�Œ�����i�抷�j
               l_strSwitchingMinQty = l_mfProduct.getSwitchingMinQty() + "";
               //�g�����M����.get�P�ʋ��z�i�抷�j
               l_strSwitchingUnitAmt = l_mfProduct.getSwitchingUnitAmt() + "";
               //�g�����M����.get�Œ���z�i�抷�j
               l_strSwitchingMinAmt = l_mfProduct.getSwitchingMinAmt() + "";
           }

           // ID�@@���@@�����Ώۂۗ̕L���YParams.�ۗL���YID
           l_mfBalanceReferenceDetailUnit.id = l_assetRow.getAssetId() + "";
           
		   // �����R�[�h�@@���@@���M����.getProductId()
		   l_mfBalanceReferenceDetailUnit.mutualProductId = l_mfProduct.getProductId() + "";
           
           // �����R�[�h�@@���@@���M����.getProductCode()
           l_mfBalanceReferenceDetailUnit.mutualProductCode = l_mfProduct.getProductCode();
           
           // �������@@���@@���M����.get������()
           l_mfBalanceReferenceDetailUnit.mutualProductName = l_mfProduct.getMutualProductName();
           
           //�����敪 = �����Ώۂۗ̕L���YParams.�ŋ敪 == "���"�̏ꍇ�A"0�F���"���Z�b�g�B
           //�@@�@@�@@�����Ώۂۗ̕L���YParams.�ŋ敪 == "����"�܂���"������������򒥎�"�̏ꍇ�A"1�F����"���Z�b�g�B
           //�@@�@@�@@�ȊO�A"2�F���̑�"���Z�b�g�B
           if(TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
           {
               l_mfBalanceReferenceDetailUnit.taxType = WEB3MFAccountDivDef.NORMAL;
           }
           else if (TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType())
               || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetRow.getTaxType()))
           {
               l_mfBalanceReferenceDetailUnit.taxType = WEB3MFAccountDivDef.SPECIAL;
           }
           else
           {
               l_mfBalanceReferenceDetailUnit.taxType = WEB3MFAccountDivDef.OTHER;
           }
           
           // ���\�����@@���@@calc���\�c������()�̖߂�l
           l_mfBalanceReferenceDetailUnit.sellableQty = 
               WEB3StringTypeUtility.formatNumber(l_dblSellPossiblePositionQty);
           
           // ��񒆊T�Z�����@@���@@calc��񒆊T�Z����()�̖߂�l
           l_mfBalanceReferenceDetailUnit.sellingEstimatedQty = 
               WEB3StringTypeUtility.formatNumber(l_dblSellingEstimatedQty);
           
           // �Q�l����z�ʉ݃R�[�h	���@@���M����.get�ʉ݃R�[�h()
           l_mfBalanceReferenceDetailUnit.constantValueCurrencyCode = 
               l_mfProduct.getCurrencyCode();

           // �Q�l����z ���M����.is�O��MMF()==true�̏ꍇ�Anull�B
           l_mfBalanceReferenceDetailUnit.constantValue = l_strConstantValue;

           // ����z�K�p��	���@@���M����.get����z�K�p��()
           l_mfBalanceReferenceDetailUnit.constantValueAppDate = 
               l_mfProduct.getConstantValueAppDate();
           
           if(((MutualFundTypeEnum.OTHER.equals(l_mfTypeEnum) ||
               MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum)) && 
              TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
              || l_mfProduct.isFrgnMmf())
           {
           	   // [�igetMatualFundType()�̖߂�l == "���̑�" or "���O"�j 
               // and �����Ώۂۗ̕L���YParams.�ŋ敪 == "���"�̏ꍇ] �܂���
               //�@@[�i���M����.is�O��MMF() == true�j]
               // �ʌ��{�@@���@@null
               l_mfBalanceReferenceDetailUnit.indivPrincipal = null;
           }
           else
           {
               // �ʌ��{�@@���@@�ۗL���Y�e�[�u��Params.getBookValue()
               // �i�����_�ȉ��l�̌ܓ��j
			   l_mfBalanceReferenceDetailUnit.indivPrincipal = 
				   WEB3StringTypeUtility.formatNumber(
				       new BigDecimal(Double.toString(
				           l_assetRow.getBookValue())).setScale(
				               0,BigDecimal.ROUND_HALF_UP).doubleValue());
           }
           
		   // �]���z�@@���@@�g�����M�|�W�V�����}�l�[�W��.calc�]���z()
		   double l_dblMarketValue = 
			   l_web3MfPositionMgr.calcMarketValue(
                   l_subAccount, l_mfProduct, l_mfAsset.getAssetId() + "");
           
		   l_mfBalanceReferenceDetailUnit.marketValue = 
			   WEB3StringTypeUtility.formatNumber(l_dblMarketValue);
		      
           if(((MutualFundTypeEnum.OTHER.equals(l_mfTypeEnum) ||
               MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum)) && 
              TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
              || l_mfProduct.isFrgnMmf())
           {
               // [�igetMatualFundType()�̖߂�l == "���̑�" or "���O"�j 
               // and �����Ώۂۗ̕L���YParams.�ŋ敪 == "���"�̏ꍇ]�܂���
               //�@@[�i���M����.is�O��MMF() == true�j]
               // �]�����v�@@���@@null
               l_mfBalanceReferenceDetailUnit.appraisalProfitLoss = null;
           }
           else
           {
               // ��L�ȊO
               // �]�����v�@@���@@�g�����M�|�W�V�����}�l�[�W��.calc�]�����v()
               // [calc�]���z()�Acalc�]�����v()�ɃZ�b�g����p�����[�^]
               //   �⏕�����F�@@get�⏕����()�̖߂�l
               //   �g�����M�����F�@@to����()�̖߂�l

               double l_dblAppraisalProfitLoss = 
                   l_web3MfPositionMgr.calcAppraisalProfitLoss(
                       l_subAccount, l_mfProduct, l_mfAsset.getAssetId() + "");
               
               l_mfBalanceReferenceDetailUnit.appraisalProfitLoss = 
                   WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitLoss);
           }

           // ��񎞒P�ʌ����@@���@@���M����.get�P�ʌ���(���)()
           l_mfBalanceReferenceDetailUnit.sellUnitQty = l_mfProduct.getSellUnitQty() + "";
           
           // ��񎞍Œ�����@@���@@���M����.get�Œ����(���)()
           l_mfBalanceReferenceDetailUnit.sellMinQty = l_mfProduct.getSellMinQty() + "";
           
           // ��񎞒P�ʋ��z�@@���@@���M����.get�P�ʋ��z(���)()
           l_mfBalanceReferenceDetailUnit.sellUnitAmt = l_mfProduct.getSellUnitAmt() + "";
           
           // ��񎞍Œ���z�@@���@@���M����.get�Œ���z(���)()
           l_mfBalanceReferenceDetailUnit.sellMinAmt = l_mfProduct.getSellMinAmt() + "";

           // �抷���P�ʌ��� ���M����.is�O��MMF()==true�̏ꍇ�Anull�B
           l_mfBalanceReferenceDetailUnit.switchingUnitQty = l_strSwitchingUnitQty;

           // �抷���Œ���� ���M����.is�O��MMF()==true�̏ꍇ�Anull�B
           l_mfBalanceReferenceDetailUnit.switchingMinQty = l_strSwitchingMinQty;

           // �抷���P�ʋ��z ���M����.is�O��MMF()==true�̏ꍇ�Anull�B
           l_mfBalanceReferenceDetailUnit.switchingUnitAmt = l_strSwitchingUnitAmt;

           // �抷���Œ���z ���M����.is�O��MMF()==true�̏ꍇ�Anull�B
           l_mfBalanceReferenceDetailUnit.switchingMinAmt = l_strSwitchingMinAmt;

           // ������t���؎��ԁ@@���@@
           // get������t���؎���()�̖߂�l�̂P�b��̎���"HHMMSS"��"HH:MM"�ɕҏW���ăZ�b�g�B
           log.debug("l_strOrderCloseTime" + l_strOrderCloseTime);
           
           Date l_datOrderCloseTime = 
               WEB3DateUtility.getDate(l_strOrderCloseTime, "HHmmss");
           l_strOrderCloseTime = 
               WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(l_datOrderCloseTime, 1L),"HHmmss"); 
           l_strOrderCloseTime =  l_strOrderCloseTime.substring(0, 2)
               + WEB3GentradeTimeDef.STR_COLON
               + l_strOrderCloseTime.substring(2, 4);

           l_mfBalanceReferenceDetailUnit.orderCloseTime = l_strOrderCloseTime;
           
           // ���\�敪�@@���@@null
           l_mfBalanceReferenceDetailUnit.sellPossType = null;
           
           // ����\�敪�@@���@@null
           l_mfBalanceReferenceDetailUnit.buyPossType = null;
           
           // �抷�\�敪�@@���@@null
           l_mfBalanceReferenceDetailUnit.switchingPossType = null;
           
           // ���t�\�敪�@@���@@null
           l_mfBalanceReferenceDetailUnit.buyPosDiv = null;
           
           //���M�����J�e�S���[�R�[�h   ���@@���M����.get�J�e�S���[�R�[�h()
           MutualFundProductRow l_mfProductRow = 
               (MutualFundProductRow)l_mfProduct.getDataSourceObject();
           l_mfBalanceReferenceDetailUnit.categoryCode = l_mfProductRow.getCategoryCode();

           //�E�~�]����z
           //�@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ �܂���
           //�@@      ���M����.is�O��MMF()��true�̏ꍇ
           //�@@�@@�@@�@@null���Z�b�g����B
           //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
           //�@@�@@�@@�@@�g�����M����.get�~�]����z(WEB3MFProcessDivDef.���)���Z�b�g����B
           if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mfProduct.getCurrencyCode())
               || l_mfProduct.isFrgnMmf())
           {
               l_mfBalanceReferenceDetailUnit.yenConstantValue = null;
           }
           else
           {
               l_mfBalanceReferenceDetailUnit.yenConstantValue = 
                   l_mfProduct.getYenConstantValue(WEB3MFProcessDivDef.SELL);
           }   

           //�Q�l���[�g
           //  1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ
           //�@@�@@�@@ null���Z�b�g����B
           //�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
           //      2-1) ���M����.is�O��MMF()��true�̏ꍇ
           //�@@�@@�@@   ���M����.get�O��MMF�בփ��[�g()�̖߂�l�O��MMF�בփ��[�gParams��TTB
           //�@@�@@�@@   ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
           //      2-2) ���M����.is�O��MMF()��false�̏ꍇ
           //�@@�@@�@@   ���M����.get�בփ��[�g()�̖߂�l�בփ��[�gParams��TTB / ���בփ��[�g�v�Z�P��
           //�@@�@@   ���Z�b�g����B�i������R�ʂŎl�̌ܓ��j
           if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mfProduct.getCurrencyCode()))
           {
               l_mfBalanceReferenceDetailUnit.referenceRate = null;
           }
           else
           {
               if (l_mfProduct.isFrgnMmf())
               {
                   BigDecimal l_bdTtBuyingRate =
                       new BigDecimal(l_mfProduct.getFrgnMmfExchangeRate().getTtBuyingRate() + "");
                   l_mfBalanceReferenceDetailUnit.referenceRate =
                       WEB3StringTypeUtility.formatNumber(
                           l_bdTtBuyingRate.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
               }
               else
               {
                   BigDecimal l_bdTtBuyingRate = 
                       new BigDecimal(l_mfProduct.getExchangeRate().getTtBuyingRate() + "");
                   BigDecimal l_bdExchangeCalcUnit = 
                       new BigDecimal(l_mfProduct.getExchangeRate().getExchangeCalcUnit() + "");
                   l_mfBalanceReferenceDetailUnit.referenceRate =
                       WEB3StringTypeUtility.formatNumber(
                           l_bdTtBuyingRate.divide(
                               l_bdExchangeCalcUnit,
                               2,
                               BigDecimal.ROUND_HALF_UP).doubleValue());
               }
           }

           //�Q�l���[�g�m���
           // �@@�@@1�j���M����.get�ʉ݃R�[�h( )��T0�̏ꍇ
           //�@@�@@�@@ null���Z�b�g����B
           //�@@�@@2�j���M����.get�ʉ݃R�[�h( )��T0�łȂ��ꍇ
           //       2-1) ���M����.is�O��MMF �� true�̏ꍇ            
           //            ���M����.get�O��MMF�בփ��[�g()�̖߂�l
           //            �O��MMF�בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B 
           //       2-2) ���M����.is�O��MMF �� false�̏ꍇ
           //            ���M����.get�בփ��[�g()�̖߂�l
           //            �בփ��[�gParams�̈בփ��[�g�m������Z�b�g����B
           if (WEB3MFEstimatedPriceCurrencyCodeDef.T0.equals(l_mfProduct.getCurrencyCode())) 
           {
               l_mfBalanceReferenceDetailUnit.referenceRateFixedDay = null;
           }
           else
           {
               if (l_mfProduct.isFrgnMmf())
               {
                   l_mfBalanceReferenceDetailUnit.referenceRateFixedDay =
                       l_mfProduct.getFrgnMmfExchangeRate().getExecTimestamp();
               }
               else
               {
                   l_mfBalanceReferenceDetailUnit.referenceRateFixedDay =
                       l_mfProduct.getExchangeRate().getExecTimestamp();
               }
           }

           //�������z��
           //  1) ���M����.is�O��MMF() == true�̏ꍇ�A�g�����M�|�W�V�����}�l�[�W��.get�������z��()�̖߂�l���Z�b�g�B
           //      [get�������z���̈���]
           //      ���YID:�����Ώۂۗ̕L���YParams.�ۗL���YID
           //      �g�����M����:�擾�������M�����I�u�W�F�N�g
           //  2) ���M����.is�O��MMF() == false�̏ꍇ�Anull���Z�b�g�B
           if (l_mfProduct.isFrgnMmf())
           {
               l_mfBalanceReferenceDetailUnit.unreceivedDistribution =
                   WEB3StringTypeUtility.formatNumber(
                       l_web3MfPositionMgr.getUnreceiveDist(
                           l_mfAsset.getAssetId() + "",
                           l_mfProduct));
           }
           else
           {
               l_mfBalanceReferenceDetailUnit.unreceivedDistribution = null;
           }

           //�@@�O��MMF�t���O  ��  ���M����.is�O��MMF()
           l_mfBalanceReferenceDetailUnit.frgnMmfFlag = l_mfProduct.isFrgnMmf();

           //�@@��񎞊O�ݒP�ʋ��z ���@@���M����.get�O�ݒP�ʋ��z(���)()
           if (!l_mfProductRow.getFrgnSellUnitAmtIsNull())
           {
               l_mfBalanceReferenceDetailUnit.sellFrgnUnitAmt =
                   l_mfProduct.getFrgnSellUnitAmt() + "";
           }
           //�@@��񎞊O�ݍŒ���z ���@@���M����.get�O�ݍŒ���z(���)()
           if (!l_mfProductRow.getFrgnSellMinAmtIsNull())
           {
               l_mfBalanceReferenceDetailUnit.sellFrgnMinAmt =
                   l_mfProduct.getFrgnSellMinAmt() + "";
           }

           //�w����@@�i���j = ���M����.get�w����@@(���)
           l_mfBalanceReferenceDetailUnit.sellSelectable =
               l_mfProduct.getSellSelectable();

           //�w����@@�i�抷�j = ���M����.get�w����@@(�抷)
           l_mfBalanceReferenceDetailUnit.switchingSelectable =
               l_mfProduct.getSwitchingSelectable();

           //1.6.12 ( * �e�\�敪�Z�b�g)====================start==================
           // ���t�\�`�F�b�N
           // 1.1 calc���\�c������()�̖߂�l == 0�̏ꍇ
           if(l_dblSellPossiblePositionQty == 0)
           {
               // 1.1.1 �ȉ��̋敪��"�S�����"���Z�b�g����B
               // �E���M�c���Ɖ��.���\�敪
               l_mfBalanceReferenceDetailUnit.sellPossType = WEB3RemarkDivDef.All_SELLING;
               
               // �E���M�c���Ɖ��.����\�敪
               l_mfBalanceReferenceDetailUnit.buyPossType = WEB3RemarkDivDef.All_SELLING;
               
               // �E���M�c���Ɖ��.�抷�\�敪
               l_mfBalanceReferenceDetailUnit.switchingPossType = WEB3RemarkDivDef.All_SELLING;
           }
           
           try
           {
               // 1.2 validate�ً}��~(�g�����M����, String) 
               // [����] 
               // �g�����M�����F�@@to����()�̖߂�l 
               // �����敪�F�@@"���"
               l_validationsCheck.validateEmergencyStop(
                   l_mfProduct, 
                   WEB3ProcessDivDef.SELL);
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("���ً}��~�G���[ " + l_ex);
               
               // 1.3 ����validate�ً}��~()����O���X���[�����ꍇ
               // 1.3.1 �ȉ��̋敪��"�ً}��~��"���Z�b�g����B
               // �E���M�c���Ɖ��.���\�敪
               l_mfBalanceReferenceDetailUnit.sellPossType = WEB3RemarkDivDef.EMERGENCY_STOP;
               
               // �E���M�c���Ɖ��.����\�敪
               l_mfBalanceReferenceDetailUnit.buyPossType = WEB3RemarkDivDef.EMERGENCY_STOP;
           }
           
           try
           {
               // 1.4 �����̏抷�ً}��~�`�F�b�N�����{����B 
               // [����] 
               // �g�����M�����F�@@to����()�̖߂�l 
               // �����敪�F�@@"�抷" 
               l_validationsCheck.validateEmergencyStop(
                   l_mfProduct, 
                   WEB3ProcessDivDef.SWITCHING);
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("�抷�ً}��~�G���[ " + l_ex);
               
               // 1.5 �抷��validate�ً}��~()����O���X���[�����ꍇ
               // 1.5.1 �ȉ��̋敪��"�ً}��~��"���Z�b�g����B
               // �@@�E���M�c���Ɖ��.�抷�\�敪
               l_mfBalanceReferenceDetailUnit.switchingPossType = WEB3RemarkDivDef.EMERGENCY_STOP;
           }

           // 1.6 ������t�g�����U�N�V�������ăZ�b�g����B 
           // [����] 
           // ������t�g�����U�N�V�����F�@@"���t(�V�K����)"
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
           
           try
           {
               // 1.7 validate������t�\
               WEB3MutualFundTradingTimeManagement.validateOrderAccept();
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("������t�\�G���[" + l_ex);
               // 1.8 validate������t�\()����O���X���[�����ꍇ
               // 1.8.1 �ȉ��̋敪��"��t���ԊO"���Z�b�g����B
               // �E���M�c���Ɖ��.���\�敪
               l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
               
               // �E���M�c���Ɖ��.����\�敪
               l_mfBalanceReferenceDetailUnit.buyPossType = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
               
               // �E���M�c���Ɖ��.�抷�\�敪
               l_mfBalanceReferenceDetailUnit.switchingPossType = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
           }
           
           // 1.9 ���M���������擾����
           Date l_datMfOrderBizDate = 
               WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                      
           //1.10 is���抷�\(Date)
           // [����] 
           // �������F�@@�@@
           //     �����������{�i�Ή������܂ł̎b��Ή�
           //     �擾�����g�����M����.getProductId��3303910181800�܂���3303911181800�̏ꍇ
           //�@@       ������ԊǗ�.get������()�̖߂�l
           //     ����ȊO�̏ꍇ
           //�@@       get���M������()�̖߂�l
           Date l_datArgIsSellSwitchingPossible = null;
           if (l_lngProductId == 3303910181800L || l_lngProductId == 3303911181800L)
           {
        	   l_datArgIsSellSwitchingPossible = WEB3GentradeTradingTimeManagement.getOrderBizDate();
           }
           else
           {
        	   l_datArgIsSellSwitchingPossible = l_datMfOrderBizDate;
           }
           boolean l_blnIsSellSwitchingPossible = 
               l_mfProduct.isSellSwitchingPossible(l_datArgIsSellSwitchingPossible);
           
           //���M����.is�������M()    
           boolean l_blnIsDomesticFund = l_mfProduct.isDomesticFund();

           //�g�����M����.is�O�����M()
           boolean l_blnIsForeignFund = l_mfProduct.isForeignFund();

           //�g�����M����.isFWF()
           boolean l_blnIsFWF = l_mfProduct.isFWF();

           // 1.11  (*5)is���抷�\()�̖߂�l  == false�̏ꍇ 
           if(!l_blnIsSellSwitchingPossible)
           {
               // 1.11.1 is���抷�\()�̖߂�l  == false�̏ꍇ
               // �ȉ��̋敪��"�����~��"���Z�b�g����
               // �E���M�c���Ɖ��.���\�敪
               l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.HANDLING_DISABLE;
               
               // �E���M�c���Ɖ��.�抷�\�敪
               l_mfBalanceReferenceDetailUnit.switchingPossType = 
                   WEB3RemarkDivDef.HANDLING_DISABLE;
               
               // �E���M�c���Ɖ��.����\�敪
			   l_mfBalanceReferenceDetailUnit.buyPossType = 
				   WEB3RemarkDivDef.HANDLING_DISABLE;
           }
           // 1.12 (*6)(*5)�ȊO AND �g�����M����.is�������M()�̖߂�l == true �̏ꍇ
           else if(l_blnIsSellSwitchingPossible && l_blnIsDomesticFund)
           {
               // 1.12 (*5)�ȊO�̏ꍇ
               // 1.12.1 ���搿�����\�ł��邩���ʂ���B 
               // [����] 
               // �������F�@@get���M������()�̖߂�l 
               // �⏕�����F�@@get�⏕����()�̖߂�l 
               // �g�����M�����F�@@to����()�̖߂�l
               boolean l_blnIsBuyPossible = l_validationsCheck.isBuyingRequestPossible(
                   l_datMfOrderBizDate, 
                   l_subAccount, 
                   l_mfProduct);
               
               if(!l_blnIsBuyPossible)
               {
                   // 1.12.2 is���搿���\()�̖߂�l == false�̏ꍇ
                   // �ȉ��̋敪��"�����~��"���Z�b�g����                   
                   // �E���M�c���Ɖ��.����\�敪
                   l_mfBalanceReferenceDetailUnit.buyPossType = 
                       WEB3RemarkDivDef.HANDLING_DISABLE;
               }
           }
           //(*8)(*5)�ȊO AND (�g�����M����.is�O�����M()�̖߂�l == true OR 
           // �g�����M����.isFWF()�̖߂�l == true )�̏ꍇ
           else if (l_blnIsForeignFund || l_blnIsFWF)
           {
               // 1.13.1�ȉ��̋敪��"����s��(���t��~��)"���Z�b�g����
               // �E���M�c���Ɖ��.���\�敪
			   l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.HANDLING_DISABLE;
           }

           //(*9)(*5)�ȊO AND (�g�����M����.is�O��MMF()�̖߂�l == true)�̏ꍇ
           else if (l_mfProduct.isFrgnMmf())
           {
               l_mfBalanceReferenceDetailUnit.buyPossType =
                   WEB3RemarkDivDef.HANDLING_DISABLE;
           }

		   // 1.13 1.14 �抷�\�`�F�b�N
		   // is�抷�\(�@@)���R�[�����A�߂�l��false�̏ꍇ
		   // �抷�\�敪��"�戵�s��"���Z�b�g����B
		   log.debug("���M����.is�抷�\(�@@) = " + l_mfProduct.isSwitchingAble());
		   if(!l_mfProduct.isSwitchingAble())
		   {
			   l_mfBalanceReferenceDetailUnit.switchingPossType = 
			   	   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
		   }
           
           // 1.15 is�V�X�e���戵( ) Web�V�Ŏ戵�\�ȓ��M���������ʂ���B
           boolean l_blnIsSystemHandle = l_mfProduct.isSystemHandling();
           
           //1.16  (*8)is�V�X�e���戵() == false�̏ꍇ
           if(!l_blnIsSystemHandle)
           {
			   log.debug("�V�X�e���戵�s��");
               // 1.16.1 is�V�X�e���戵() == false�̏ꍇ
               // �ȉ��̋敪��"�戵�s��"���Z�b�g����B
               // �E���M�c���Ɖ��.���\�敪
               l_mfBalanceReferenceDetailUnit.sellPossType =
                   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
               
               // �E���M�c���Ɖ��.����\�敪
               l_mfBalanceReferenceDetailUnit.buyPossType = 
                   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
               
               // �E���M�c���Ɖ��.�抷�\�敪
               l_mfBalanceReferenceDetailUnit.switchingPossType = 
                   WEB3RemarkDivDef.HANDLING_WEB_DISABLE;
           }
           
           //1.17 ���M���������t�\�����ʂ���B 
           // [����] 
           // �������F�@@get���M������()�̖߂�l
           boolean l_blnAcquiredPossible = 
               l_mfProduct.isAcquiredPossible(l_datMfOrderBizDate);
           
           //1.18 ���M�����ɔ��t���������邩���ʂ���B
           boolean l_blnAcquiredDeregExistence = 
               l_mfProduct.isAcquiredDeregExistence();
           
           // 1.19 is���t�\() == false �܂��� is���t�����L��() == true�̏ꍇ
           if(!l_blnAcquiredPossible || l_blnAcquiredDeregExistence)
           {
               // 1.19.1 �ȉ��̋敪��"���t��~��"���Z�b�g����B
               // �E���M�c���Ɖ��.���t�\�敪
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.HANDLING_DISABLE;
           }
           
           // 1.20 ������t�g�����U�N�V�������ăZ�b�g����B 
           // [����] 
           // ������t�g�����U�N�V�����F�@@"���t(�V�K����)"
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
           
           try
           {
               // 1.21 validate������t�\
               WEB3MutualFundTradingTimeManagement.validateOrderAccept();
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("������t�\�G���[" + l_ex);
               // 1.22  (*10)validate������t�\()����O���X���[�����ꍇ
               // 1.22.1 �ȉ��̋敪��"��t���ԊO"���Z�b�g����B
               // �E���M�c���Ɖ��.���t�\�敪
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
           }
 
           try
           {
               // 1.23  �����̔��t�ً}��~�`�F�b�N�����{����B 
               // [����] 
               // �g�����M�����F�@@to����()�̖߂�l 
               // �����敪�F�@@"���t"  
               l_validationsCheck.validateEmergencyStop(
                       l_mfProduct, 
                       WEB3ProcessDivDef.BUY);
           }
           catch(WEB3BaseException l_ex)
           {
               //1.24  (*11)validate�ً}��~()����O���X���[�����ꍇ
               log.debug("���t�ً}��~�G���[ " + l_ex);
               
               // 1.24.1 validate�ً}��~()����O���X���[�����ꍇ
               // �ȉ��̋敪��"�ً}��~��"���Z�b�g����B
               // �@@�E���M�c���Ɖ��.���t�\�敪
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.EMERGENCY_STOP;
           }
           
           //1.25 is��W�\(Date)(�g�����M����::is��W�\)
           //���M��������W�\�����ʂ���B 

           //[����] 
           //�������F�@@get���M������()�̖߂�l
           boolean l_blnIsRecruitPoss = 
               l_mfProduct.isRecruitPossible(l_datMfOrderBizDate);
           
           //1.26 (*12)is��W�\() == true �̏ꍇ
           if (l_blnIsRecruitPoss)
           {
               //1.26.1 (*)�v���p�e�B�Z�b�g
               //(*)�ȉ��̋敪��"��W���Ԓ�"���Z�b�g����B
               //�E���M�c���Ɖ��.���t�\�敪
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.RECRUIT_BETWEEN;
           }
           //1.27 reset������t�g�����U�N�V����(������t�g�����U�N�V���� : String)
           //������t�g�����U�N�V�������ăZ�b�g����B 
           //[����] 
           //������t�g�����U�N�V�����F�@@"��W" 
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.RECRUIT);
           
           //1.28 validate������t�\( )
           //1.29 (*13)validate������t�\()����O���X���[�����ꍇ
           try
           {
               WEB3MutualFundTradingTimeManagement.validateOrderAccept();
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("������t�\�G���[" + l_ex);
               //1.29.1 (*)�v���p�e�B�Z�b�g
               // (*)�ȉ��̋敪��"��t���ԊO"���Z�b�g����B
               //�E���M�c���Ɖ��.���t�\�敪
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.OUT_TRADINGTIME_ORDER_STOP;
           }
           //1.30 validate�ً}��~(�g�����M����, String)
           //�����̕�W�ً}��~�`�F�b�N�����{����B 
           //[����] 
           //�g�����M�����F�@@to����()�̖߂�l 
           //�����敪�F�@@"��W" 
           try
           {
               l_validationsCheck.validateEmergencyStop(
                   l_mfProduct, 
                   WEB3ProcessDivDef.RECRUIT);          
           }
           catch(WEB3BaseException l_ex)
           {
               log.debug("���t�ً}��~�G���[ " + l_ex);  
               //1.31 (*14)validate�ً}��~()����O���X���[�����ꍇ
               //1.31.1  (*)�v���p�e�B�Z�b�g
               //(*)�ȉ��̋敪��"�ً}��~��"���Z�b�g����B
               //�@@�E���M�c���Ɖ��.���t�\�敪 
               
               l_mfBalanceReferenceDetailUnit.buyPosDiv = 
                   WEB3RemarkDivDef.EMERGENCY_STOP;
           }
           
           //1.32 reset������t�g�����U�N�V����(������t�g�����U�N�V����)
           //������t�g�����U�N�V�������ăZ�b�g����B 
           //[����] 
           //������t�g�����U�N�V�����F�@@"�Ɖ�"
           WEB3MutualFundTradingTimeManagement.resetOrderAcceptTransaction(
               WEB3OrderAccTransactionDef.REFERENCE);
           
           //1.33 is�V�X�e���戵( )
           l_blnIsSystemHandle = l_mfProduct.isSystemHandling();           
           
		   //1.34 (*15)is�V�X�e���戵() == false�̏ꍇ
		   if(!l_blnIsSystemHandle)
		   {
			   log.debug("�V�X�e���戵�s��");
               //1.34.1 (*)�ȉ��̋敪��"�戵�s��"���Z�b�g����B
			   //�E���M�c���Ɖ��.���t�\�敪
			   l_mfBalanceReferenceDetailUnit.buyPosDiv =
			      WEB3RemarkDivDef.HANDLING_WEB_DISABLE; 
		   }          
           //�e�\�敪�̃Z�b�g���s���B=====================end===================
           
           //1.6.13
           l_lisBalanceReferenceDetailUnits.add(l_mfBalanceReferenceDetailUnit);
       }
       
       // 1.7 ���M�c���Ɖ�ׂ̔z��𐶐�����B
       int l_intListSize = l_lisBalanceReferenceDetailUnits.size();
       WEB3MutualBalanceReferenceDetailUnit[] l_arrBalanceReferenceDetailUnits = 
           new WEB3MutualBalanceReferenceDetailUnit[l_intListSize];
       l_lisBalanceReferenceDetailUnits.toArray(l_arrBalanceReferenceDetailUnits);
       
       // 1.8 �쐬�����c���Ɖ�ׂ��\�[�g����B 
       // [����] 
       //     ���M�c���Ɖ�ׁF�@@toArray()�̖߂�l 
       //     �\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
       this.sortBalanceReferenceDetailUnit(
           l_arrBalanceReferenceDetailUnits,
           l_request.sortKeys);
       
       //1.9 get���M�����J�e�S���[���X�g(String)
       //���M�����J�e�S���[�R�[�h����������B 
       //[����] 
       //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
       List l_lisProductCategory = 
           l_productManager.getMutualFundProductCategoryList(
               l_subAccount.getInstitution().getInstitutionCode()
               );
       
       //1.10 create���M�����J�e�S���[�ꗗ(List)
       //���M�����J�e�S���[�ꗗ���쐬����B
       //[����] 
       //�����J�e�S���[�ꗗ�F get���M�����J�e�S���[���X�g()�̖߂�l 
       WEB3MutualProductCategoryUnit[] l_mfProductCategoryUnits =
           l_productManager.createMutualFundProductCategoryList(
               l_lisProductCategory);       
       
       // 1.11 ���X�|���X�f�[�^�𐶐�����B
       WEB3MutualBalanceReferenceResponse l_response = 
           (WEB3MutualBalanceReferenceResponse) l_request.createResponse();
       
       // 1.12 ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
       
       int l_intPageSize = Integer.parseInt(l_request.pageSize);
       int l_intPageIndex = Integer.parseInt(l_request.pageIndex);  
       
       WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
           l_arrBalanceReferenceDetailUnits, 
           l_intPageIndex, 
           l_intPageSize);
       
       // ���X�|���X.�c���Ɖ�� �� �\���Ώۍs�̓��M�c���Ɖ�ׂ̔z��
       l_response.balanceReference = 
           (WEB3MutualBalanceReferenceDetailUnit[]) l_pageIndexInfo.getArrayReturned(
               WEB3MutualBalanceReferenceDetailUnit.class);
       
       // ���X�|���X.���y�[�W�� �� �����R�[�h�� / ���N�G�X�g�f�[�^.�y�[�W���\���s��
       // 			�@@�@@		�@@���v�Z���ʂ͏����_�ȉ���ʂ�؂�グ�������l�Ƃ���B
       l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
           
       // ���X�|���X.�����R�[�h�� �� toArray()�̖߂�l.length
       l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + ""; 
       
       // ���X�|���X.�\���y�[�W�ԍ� �� toIndex /�@@���N�G�X�g�f�[�^.�y�[�W���\���s��
       // 		�@@�@@		�@@���v�Z���ʂ͏����_�ȉ���ʂ�؂�グ�������l�Ƃ���B
       l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
       
       //���X�|���X.���M�����J�e�S���[�ꗗ ��create���M�����J�e�S���[�ꗗ()�̖߂�l
       l_response.categoryList = l_mfProductCategoryUnits;
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (get�c�����v)<BR>
    * ���M�c�����v�������s���B<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �u(���M)get�c�����v�v�Q��
    * @@param l_request - ���M�c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse
    * @@throws WEB3BaseException
    * @@roseuid 41AD8E4C033D
    */
   protected WEB3MutualBalanceReferenceTotalResponse getBalanceReferenceTotal(
           WEB3MutualBalanceReferenceTotalRequest l_request) 
               throws WEB3BaseException 
   {
       final String STR_METHOD_NAME = "getBalanceReferenceTotal()";
       log.entering(STR_METHOD_NAME);
       
       if (l_request == null)
       {
           log.debug(" __parameter_error__");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       //���M�E�O��MMF�\���敪
       //��null�̏ꍇ�A�u0:���M�̂݁v�Ƃ���
       if (l_request.mutualFrgnMmfDisplayDiv == null)
       {
           l_request.mutualFrgnMmfDisplayDiv =
               WEB3MutualFrgnMmfDisplayDivDef.MUTUAL_FUND;
       }

       //�P�j�@@validate������t�\
       WEB3MutualFundTradingTimeManagement.validateOrderAccept();
       
       // 2) �⏕�����I�u�W�F�N�g���擾����
       SubAccount l_subAccount = this.getSubAccount();
       
       //�g�����M�|�W�V�����}�l�[�W�����擾����
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3MutualFundPositionManager l_web3MfPositionMgr =
           (WEB3MutualFundPositionManager) l_finApp.getTradingModule(
                   ProductTypeEnum.MUTUAL_FUND).getPositionManager();
       
       // 3)�ۗL���Y�����ꗗ���擾���� 
       //  [����] 
       //    �⏕�����F�@@get�⏕����()�̖߂�l 
       //    ���M��O��MMF�\���敪�F�@@���N�G�X�g.���M��O��MMF�\���敪
       List l_lisAsserts = l_web3MfPositionMgr.getAssets(
               l_subAccount,
               l_request.mutualFrgnMmfDisplayDiv);
       
       // 4) ���X�|���X�f�[�^�𐶐�����B
       WEB3MutualBalanceReferenceTotalResponse l_response = 
           (WEB3MutualBalanceReferenceTotalResponse) l_request.createResponse();
       
       // 5) getAssets()�̖߂�l�̗v�f(=�ۗL���YParams)����Loop����
       int l_intAssertsLength = 0;
       if(l_lisAsserts != null)
       {
           l_intAssertsLength = l_lisAsserts.size();
       }
       
       // �@@�|�g�����M�����}�l�[�W�����擾����
       WEB3MutualFundProductManager l_productManager =
           (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                   ProductTypeEnum.MUTUAL_FUND).getProductManager();
       
       // ���X�|���X�f�[�^.��ʌ����]���z���v
       double l_dblNormalAccountTotalAsset = 
           Double.parseDouble(l_response.normalAccountTotalAsset);
       // ���X�|���X�f�[�^.��ʌ����]�����v���v
       double l_dblNormalAccountTotalAppraisalProfitLoss = 
           Double.parseDouble(l_response.normalAccountTotalAssetProfitLoss);
       // ���X�|���X�f�[�^.��������]���z���v
       double l_dblCapitalGainTotalAsset = 
           Double.parseDouble(l_response.capitalGainTotalAsset);
       // ���X�|���X�f�[�^.��������]�����v���v
       double l_dblCapitalGainTotalAppraisalProfitLoss = 
           Double.parseDouble(l_response.capitalGainTotalAssetProfitLoss);
       // ���X�|���X�f�[�^.�O��MMF�]���z���v
       double l_dblFrgnMmfTotalAsset =
           Double.parseDouble(l_response.frgnMmfTotalAsset);
       
       for(int i = 0; i < l_intAssertsLength; i++)
       {
           MutualFundAsset l_mfAsset = (MutualFundAsset) l_lisAsserts.get(i);
           AssetRow l_assetRow = (AssetRow) l_mfAsset.getDataSourceObject();

           WEB3MutualFundProduct l_mfProduct = null;
           try
           {
               // 5 -1 )�����I�u�W�F�N�g���擾���� 
               // [����] 
               // ����ID�F�@@�����Ώۂۗ̕L���YParams.getProductId()
               Product l_product = 
                   l_productManager.getProduct(l_assetRow.getProductId());
               
               // 5 - 2) �g�����M�������擾����B 
               // [����] 
               // ����Row�F�@@getProduct()�̖߂�l 
               l_mfProduct = 
                   (WEB3MutualFundProduct) l_productManager.toProduct(
                       (MutualFundProductRow) l_product.getDataSourceObject());
           }
           catch (NotFoundException l_ex)
           {
               log.error("__NotFoundException__ �����I�u�W�F�N�g���擾�ł��Ȃ�!");
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   this.getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
           } 
           
           // 5 - 3) ���M�^�C�v���擾����
           MutualFundTypeEnum l_mfTypeEnum = l_mfProduct.getMutualFundType();
           
           // 5 - 4) �]���z���擾����B 
           // [����] 
           // �⏕�����F�@@get�⏕����()�̖߂�l 
           // �g�����M�����F�@@to����()�̖߂�l
           double l_dblMarketValue = 
               l_web3MfPositionMgr.calcMarketValue(l_subAccount, l_mfProduct,l_mfAsset.getAssetId() + "");

           //(*)�]�����v�Z�o�����ΏۊO�`�F�b�N
		   //�ȉ��̂����ꂩ�ɊY������ꍇ�A
		   //  �]�����v�͌v�Z���Ȃ��̂ŁAcalc�]�����v()���R�[�������A
		   //  �]�����v�̌v�Z���ʂ� 0 ��ݒ肷��B
		   //    �@@�g�����M����.is�O��MMF()==false����
		   //     getMatualFundType()�̖߂�l == "���O" ����
		   //     �����Ώۂۗ̕L���YParams.�ŋ敪 == "���"�̏ꍇ�B
		   //    �A�g�����M����.is�O��MMF()==true�̏ꍇ�B
           double l_dblAppraisalProfitLoss = 0;
           if (l_mfProduct.isFrgnMmf()
               || (MutualFundTypeEnum.FOREIGN.equals(l_mfTypeEnum)
               && TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType())))
           {
               l_dblAppraisalProfitLoss = 0;
           }
           else
           {
               //[����]
               //�⏕�����F�@@get�⏕����()�̖߂�l
               //�g�����M�����F�@@to����()�̖߂�l 
               //���YID�F�@@getAssets()�Ŏ擾���������Ώۂۗ̕L���Y�e�[�u��Params.get���YID
               l_dblAppraisalProfitLoss =
                   l_web3MfPositionMgr.calcAppraisalProfitLoss(
                       l_subAccount, l_mfProduct, l_assetRow.getAssetId() + "");
           }

           //�g�����M����.is�O��MMF()==false�̏ꍇ
           if (!l_mfProduct.isFrgnMmf())
           {
               // 5- 7 ) )�����Ώۂۗ̕L���YParams.�ŋ敪 == "���"�̏ꍇ
               if(TaxTypeEnum.NORMAL.equals(l_assetRow.getTaxType()))
               {
                   // 5 - 7 - 1 ) ��ʌ����]���z���v�A��ʌ����]�����v���v�ɉ��Z
                   // ���X�|���X�f�[�^.��ʌ����]���z���v += calc�]���z()�̖߂�l
                   // ���X�|���X�f�[�^.��ʌ����]�����v���v += calc�]�����v()�̖߂�l
                   l_dblNormalAccountTotalAsset += l_dblMarketValue;
                   l_dblNormalAccountTotalAppraisalProfitLoss += l_dblAppraisalProfitLoss;
               }
               else
               {
                   // 5 - 7 - 2) �����Ώۂۗ̕L���YParams.�ŋ敪 == ("����" or "������������򒥎�")�̏ꍇ
                   if(TaxTypeEnum.SPECIAL.equals(l_assetRow.getTaxType()) || 
                           TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_assetRow.getTaxType()))
                   {
                       // 5 - 7 - 1 ) ��ʌ����]���z���v�A��ʌ����]�����v���v�ɉ��Z
                       // ���X�|���X�f�[�^.��������]���z���v += calc�]���z()�̖߂�l
                       // ���X�|���X�f�[�^.��������]�����v���v += calc�]�����v()�̖߂�l
                       l_dblCapitalGainTotalAsset += l_dblMarketValue;
                       l_dblCapitalGainTotalAppraisalProfitLoss += l_dblAppraisalProfitLoss;
                   }
               }
           }
           else
           {
               //�O��MMF�]���z���v�ɉ��Z
               //���X�|���X�f�[�^.�O��MMF�]���z���v += calc�]���z()�̖߂�l
               l_dblFrgnMmfTotalAsset += l_dblMarketValue;
           }
       }
       
       // ���X�|���X�f�[�^.��ʌ����]���z���v
       l_response.normalAccountTotalAsset =
           WEB3StringTypeUtility.formatNumber(l_dblNormalAccountTotalAsset) ;
       //  ���X�|���X�f�[�^.��ʌ����]�����v���v
       l_response.normalAccountTotalAssetProfitLoss = 
           WEB3StringTypeUtility.formatNumber(l_dblNormalAccountTotalAppraisalProfitLoss);
       // ���X�|���X�f�[�^.��������]���z���v
       l_response.capitalGainTotalAsset = 
           WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTotalAsset);
       // ���X�|���X�f�[�^.��������]�����v���v
       l_response.capitalGainTotalAssetProfitLoss = 
           WEB3StringTypeUtility.formatNumber(l_dblCapitalGainTotalAppraisalProfitLoss);
       // ���X�|���X�f�[�^.�O��MMF�]���z���v
       l_response.frgnMmfTotalAsset =
           WEB3StringTypeUtility.formatNumber(l_dblFrgnMmfTotalAsset);
       
       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (sort�c���Ɖ��)<BR>
    * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â���<BR>
    * ���M�c���Ɖ�ׂ̃\�[�g���s���B <BR>
    * <BR>
    * �P)�@@ArrayList���쐬 <BR>
    * <BR>
    * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
    * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ�����<BR>
    *       ��r���ڂ�Comparator�𐶐����A<BR>
    * �@@�@@�@@ArrayList�ɒǉ�����B <BR>
    * <BR>
    * �@@�@@�@@�@@���M�c���Ɖ�Comparator�𐶐�����B<BR>
    * <BR>
    * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] <BR>
    * �@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��<BR>
    * �@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[����<BR>
    * <BR>
    * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B<BR>
    * <BR>
    * �R)�\�[�g<BR>
    * �@@WEB3ArraysUtility.sort()���\�b�h���R�[������B<BR>
    * <BR>
    * �@@[sort()�ɃZ�b�g����p�����[�^]<BR>
    * �@@�@@obj�F�@@�p�����[�^.���M�c���Ɖ��<BR>
    * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l<BR>
    * @@param l_balanceReferenceDetailUnit - ���M�c���Ɖ�ׂ̔z��
    * 
    * @@param l_sortKey - �\�[�g�L�[
    * @@roseuid 41AD8EE0036C
    */
   protected void sortBalanceReferenceDetailUnit(
           WEB3MutualBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnit, 
           WEB3MutualSortKey[] l_sortKey) 
   {
       final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit()";
       log.entering(STR_METHOD_NAME);
       
       if (l_sortKey == null)
       {
           log.debug(" �p�����[�^�l��NULL ");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       // �P)�@@ArrayList���쐬 
       List l_lisComparator = new Vector();

       // �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
       for(int i = 0; i < l_sortKey.length; i++)
       {
           // �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ�����
           //       ��r���ڂ�Comparator�𐶐����A
           // �@@�@@�@@ArrayList�ɒǉ�����B 
           // �@@�@@�@@�@@���M�c���Ɖ�Comparator�𐶐�����B
           // �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] 
           // �@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��
           // �@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[����
           String l_strKeyItem = l_sortKey[i].keyItem;
           String l_strAscDesc = l_sortKey[i].ascDesc;
           WEB3MutualBalanceReferenceComparator l_comparator = 
               new WEB3MutualBalanceReferenceComparator(l_strAscDesc, l_strKeyItem);
           
           // �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B
           l_lisComparator.add(l_comparator);
       }
       
       // �R)�\�[�g
       // �@@WEB3ArraysUtility.sort()���\�b�h���R�[������B
       // �@@[sort()�ɃZ�b�g����p�����[�^]
       // �@@�@@obj�F�@@�p�����[�^.���M�c���Ɖ��
       // �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l
       Comparator[] l_comparators = new Comparator[l_lisComparator.size()];
       l_lisComparator.toArray(l_comparators);
       WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit, l_comparators);
   }
}
@
