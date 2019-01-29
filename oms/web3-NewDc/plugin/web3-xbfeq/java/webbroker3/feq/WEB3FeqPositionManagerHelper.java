head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqPositionManagerHelper.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������|�W�V�����w���p�[(WEB3FeqPositionManagerHelper.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/14 ������(���u) �V�K�쐬
                   2005/07/26 �����F(���u) ���r���[
Revesion History : 2007/11/07 �����q(���u) �d�l�ύX���f��No.358
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 20010/01/11 �����F(���u) ���f��No.529 No.530 No.534 No.535 No.537 No.540 �c�a�X�V�d�l No.104-116
Revesion History : 2010/03/05 ���g (���u)�y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.543
Revesion History : 2010/09/08 ��V�� (���u)�y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.545
Revesion History : 2010/09/10 �����C(���u) ���f��No.546
Revesion History : 2010/09/14 �Ԑi(���u) ���f��No.551 No.552 No.554
*/
package webbroker3.feq;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransaction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransactionManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqPositionManagerHelper;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;




/**
 * (�O�������|�W�V�����w���p�[) <BR>
 * �O�������|�W�V�����w���p�[<BR>
 * <BR>
 * @@ author ������ <BR>
 * @@ version 1.0<BR>
 */
public class WEB3FeqPositionManagerHelper extends FeqPositionManagerHelper 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FeqPositionManagerHelper.class);
    
    /**
     * @@roseuid 42CE39E80222
     */
    public WEB3FeqPositionManagerHelper(ProductTypeEnum tradingModuleType) 
    {
        super(tradingModuleType);
    }
    
    public PersistentDataManager getPersistenceManager()
    {
        return new WEB3FeqUpdateDataManager();
    }
    
    /**
     * (applyTo�ۗL���Y�c��) <BR>
     * �iapplyToAssetPosition�̃I�[�o�[���C�h�j <BR>
     * �ۗL���Y���X�V����B <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�O���c���japplyTo�ۗL���Y�c���v�Q�ƁB <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@return List
     * @@throws DataException
     * @@roseuid 4288262A00C6
     */
    protected List applyToAssetPosition(FeqFinTransactionParams l_feqFinTransactionParams) 
        throws DataException
    {
        final String STR_METHOD_NAME = "applyToAssetPosition(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        if (l_feqFinTransactionParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //get�ڋq(����ID : )
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMarket l_market = null;
        boolean l_blnIsDayTradeAdoption = false;
        boolean l_blnIsDayTradeMarket = false;
        try
        {
            l_market = (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_feqFinTransactionParams.getMarketId()));
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqFinTransactionParams.getAccountId());
            WEB3GentradeInstitution l_institution =
                (WEB3GentradeInstitution)l_mainAccount.getInstitution();

            try
            {
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.2�g�����U�N�V�����^�C�v���擾����B 
        FinTransactionType l_transactionType = l_feqFinTransactionParams.getFinTransactionType();

        // �iis���v�����̗p() == false�@@or�@@is���v��s��() == false�j�@@���@@���̏ꍇ(getFinTransactionType() == �O����)
        if ((!l_blnIsDayTradeAdoption || !l_blnIsDayTradeMarket) && FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
        {
            return null;
        }

        //���̏ꍇ�igetFinTransactionType( ) == �O�����j �A���o���ʒm���i���j
        Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG);
        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType) && l_obj != null && l_obj.equals(BooleanEnum.TRUE))
        {
            boolean l_blnDayExchange = false;

            l_blnDayExchange = ((Boolean)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG)).booleanValue();
            if (!l_blnDayExchange)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }

        //1.1�O�������X�V�f�[�^�}�l�[�W�����擾����B 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();

        //1.4�ۗL���Y���擾����B 
        AssetParams l_asserParams = l_dataManager.getAsset(l_feqFinTransactionParams);
        
		//�ۗL���Y�ɊY������f�[�^���Ȃ��A���@@���t(getFinTransactionType() == �O����)�̏ꍇ�A��O���X���[����B
		if (l_asserParams == null && FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
        {
		  log.error("�ۗL���Y�Y���f�[�^�Ȃ��B");
		  throw new WEB3BaseRuntimeException(
		  WEB3ErrorCatalog.BUSINESS_ERROR_00204,
		  this.getClass().getName() + "." + STR_METHOD_NAME);
		}

        //���̏ꍇ�igetFinTransactionType() == �O�����j
        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
        {
            //�c�����Ȃ��iget�ۗL���Y()�̖߂�l == null�j�ꍇ
            if (l_asserParams == null)
            {
                l_asserParams = new AssetParams();
                //�i�����̔ԁj
                l_asserParams.setAssetId(AssetDao.newPkValue());
                //�g�����U�N�V�����i������薾�ׁj�s.�����h�c
                l_asserParams.setAccountId(l_feqFinTransactionParams.getAccountId());
                //�g�����U�N�V�����i������薾�ׁj�s.�⏕�����h�c
                l_asserParams.setSubAccountId(l_feqFinTransactionParams.getSubAccountId());
                //�g�����U�N�V�����i������薾�ׁj�s.�����^�C�v
                l_asserParams.setProductType(l_feqFinTransactionParams.getProductType());
                //�g�����U�N�V�����i������薾�ׁj�s.��萔��
                l_asserParams.setQuantity(l_feqFinTransactionParams.getQuantity());
                //0
                l_asserParams.setQuantityCannotSell(0);
                //�g�����U�N�V�����i������薾�ׁj�s.��萔��
                l_asserParams.setQuantityForBookValue(l_feqFinTransactionParams.getQuantity());
                //�g�����U�N�V�����i������薾�ׁj�s.��n����~�i-1�j
                l_asserParams.setBookValue(l_feqFinTransactionParams.getNetAmount() * (-1));
                //0
                l_asserParams.setSetupFee(0);
                //0
                l_asserParams.setSetupFeeTax(0);
                //0
                l_asserParams.setManagementFee(0);
                //0
                l_asserParams.setManagementFeeTax(0);
                //�g�����U�N�V�����i������薾�ׁj�s.�����h�c
                l_asserParams.setProductId(l_feqFinTransactionParams.getProductId());
                //�g�����U�N�V�����i������薾�ׁj�s.�ŋ敪
                l_asserParams.setTaxType(l_feqFinTransactionParams.getTaxType());
                //0�FDEFAULT�i�~�j���łȂ��j
                l_asserParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                //0
                l_asserParams.setProfitDistribution(0);
                //0
                l_asserParams.setCountBeforePenalty(0);
                //���ݓ���
                l_asserParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                //���ݓ���
                l_asserParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //saveNewAsset(arg0 : AssetParams)
                l_dataManager.saveNewAsset(l_asserParams);
            }
            //�ۗL���Y�����ɑ��݂���ꍇ�iget�ۗL���Y()�̖߂�l != null�j�̏ꍇ
            else
            {
                BigDecimal l_bdQuantity = new BigDecimal(Double.toString(l_feqFinTransactionParams.getQuantity()));

                //����   �i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��萔��
                BigDecimal l_bdAssetQuantity = new BigDecimal(Double.toString(l_asserParams.getQuantity()));
                l_asserParams.setQuantity(l_bdAssetQuantity.add(l_bdQuantity).doubleValue());

                //���ʁi�뉿�P���v�Z�p�j    �i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��萔��
                BigDecimal l_bdQuantityForBookValue = new BigDecimal(Double.toString(l_asserParams.getQuantityForBookValue()));
                l_asserParams.setQuantityForBookValue(l_bdQuantityForBookValue.add(l_bdQuantity).doubleValue());

                //�뉿�i�뉿�P���v�Z�p�j
                //�i�ŋ敪 == "���"�j &&
                //�@@�i�����l.���ʁi�뉿�P���v�Z�p�j > 0�j &&
                //�@@�i�����l.�뉿�i�뉿�P���v�Z�p�j == 0�j �̏ꍇ�A�i�����l�j�B
                //�ȊO�A�i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��n����~�i-1�j�B
                //�i����ʌ����ł����Ă��A�뉿���ݒ肳��Ă���ꍇ�͍X�V�ΏۂƂ���j
                if (!(TaxTypeEnum.NORMAL.equals(l_asserParams.getTaxType())
                    && l_asserParams.getQuantityForBookValue() > 0
                    && GtlUtils.Double.isZero(l_asserParams.getBookValue())))
                {
                    //�i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��n����~�i-1�j
                    l_asserParams.setBookValue(l_asserParams.getBookValue() +
                        l_feqFinTransactionParams.getNetAmount() * -1);
                }
                l_asserParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_dataManager.updateAssetByTrans(l_asserParams); 
            }
        }
        
        //1.5���̏ꍇ�igetFinTransactionType() == �O�����j
        if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
        {
            //1.5.1�ۗL���Y�s�igetAsset()�̖߂�l�j�ɒl���Z�b�g����B 
            //�y��Trade�z�⑫����.DB�X�V\\11.�Ǘ��ҁE�o������ 
            //�u�O���o��_�ۗL���Y�e�[�u���d�l.xls#�O���o��_�ۗL���Y DB�X�V�i�B��Update�j�v 
            //�Q�ƁB
        
			//asset�̐��� >= ��萔�ʂ̃`�F�b�N
			if(l_asserParams.getQuantity() >= l_feqFinTransactionParams.getQuantity()){      
            	//�i�����l�j- �g�����U�N�V�����i������薾�ׁj�s.��萔��
            	l_asserParams.setQuantity(l_asserParams.getQuantity() - l_feqFinTransactionParams.getQuantity());
			}else{
				log.error("�ۗL���Y�c���ʃ`�F�b�N�G���[�B");
				throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01931,
				this.getClass().getName() + "." + STR_METHOD_NAME);
			}
            //�X�V���t = ���ݓ���
            l_asserParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //1.5.2�ۗL���Y�s���X�V���� 
            l_dataManager.updateAssetByTrans(l_asserParams); 
        }
        //1.6�ۗL���Y�h�c���擾����B 
        long l_lngAssetId = l_asserParams.getAssetId();
        
        //1.7�ۗL���Y�h�c���Z�b�g����B 
        l_feqFinTransactionParams.setAssetId(l_lngAssetId);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (applyToAssetUnitPosition)<BR>
     * �i���g�p�j <BR>
     * null��ԋp����B <BR>
     *  <BR>
     * ���뉿�P���Z�o�p�e�[�u���iASSET_UNIT�F�擾�P�ʕۗL���Y�j <BR>
     * �X�V�v���Z�X <BR>
     * @@param l_feqFinTransactionParams
     * @@param l_assetParams
     * @@return ListResourceBundle
     * @@throws DataException
     * @@roseuid 4288402D0394
     */
    protected List applyToAssetUnitPosition(FeqFinTransactionParams l_feqFinTransactionParams, AssetParams l_assetParams) throws DataException
    {
     return null;
    }
    
    /**
     * (process���c���X�V) <BR>
     * �iprocessCashBasedOrderExecution�̃I�[�o�[���C�h�j <BR>
     * �O�������c���X�V�������s���B <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�O���c���jprocess���c���X�V�v�Q�ƁB <BR>
     * @@param l_feqOrderExecution - (���I�u�W�F�N�g)
     * @@throws DataException
     * @@roseuid 428826850385
     */
    protected void processCashBasedOrderExecution(FeqOrderExecution l_feqOrderExecution) throws DataException 
    {
        final String STR_METHOD_NAME = "processCashBasedOrderExecution(FeqOrderExecution)";    
        log.entering(STR_METHOD_NAME);
        if (l_feqOrderExecution == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1�g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�𐶐�����B 
        FeqFinTransactionParams l_transactionParams = new FeqFinTransactionParams();
        
        //1.2�g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�ɏ����l���Z�b�g����B 
        this.setMarketOrderedTransDefaultValues(l_transactionParams);
        
        //1.3�������g�����U�N�V�����ɃZ�b�g����B 
        this.setExecutionInfoToMarketOrderedTrans(l_transactionParams, l_feqOrderExecution);
        
        //1.4applyTo�ۗL���Y�c��
        this.applyToAssetPosition(l_transactionParams);
        
        //1.5�O�������X�V�f�[�^�}�l�[�W�����擾����B 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        
        //1.6�g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g��DB�ɍX�V����B 
        l_dataManager.saveNewFinTransaction(l_transactionParams);
        
        //1.7�ڋq���薾�ׁC�⏕�������X�V����B 
        this.notifyGtl(l_transactionParams);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify�ڋq����) <BR>
     * �inotifyGtl�̃I�[�o�[���C�h�j <BR>
     * �ڋq���薾�ׁC�⏕�������X�V����B <BR>
     *  <BR>
     * �P�j�@@�ڋq���薾�ׁi�FGeneralizedFinTransaction�j�I�u�W�F�N�g�𐶐�����B <BR>
     *  <BR>
     * �@@[�R���X�g���N�^�̈���] <BR>
     * �@@�g�����U�N�V�����h�c�F�@@<BR>
     * �@@�@@�@@�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V�����h�c <BR>
     * �@@�����h�c�F�@@�g�����U�N�V�����i������薾�ׁj�s.�����h�c <BR>
     * �@@�⏕�����h�c�F�@@�g�����U�N�V�����i������薾�ׁj�s.�⏕�����h�c <BR>
     * �@@��n���F�@@�g�����U�N�V�����i������薾�ׁj�s.��n�� <BR>
     * �@@�g�����U�N�V�����^�C�v�F�@@ <BR>
     * �@@�@@�@@�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V�����^�C�v  <BR>
     * �@@��n���z�F�@@�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V�����h�c <BR>
     * �@@description�F�@@�gFeqFinTransaction.  <BR>
     * productId(product_id),marketId(market_id),quantity(quantity),price(price)�h  <BR>
     * �@@�g���[�f�B���O���W���[�����F�@@getTradingModule().getTradingModuleName() <BR>
     * �@@�g�����U�N�V�������������F<BR>
     * �@@�@@�@@�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V������������ <BR>
     *  <BR>
     * �Q�j�@@�f�s�k�֒ʒm����B <BR>
     * �@@GeneralizedFinTransactionManager.notifyTransaction()���R�[������B <BR>
     *  <BR>
     * �@@[notifyTransaction()�Ɏw�肷�����] <BR>
     * �@@�ڋq���薾�ׁF�@@�P�j�Ő��������ڋq���薾�� <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@roseuid 42882B0B0104
     */
    protected void notifyGtl(FeqFinTransactionParams l_feqFinTransactionParams)
    {
        final String STR_METHOD_NAME = "notifyGtl(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        if (l_feqFinTransactionParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�@@�ڋq���薾�ׁi�FGeneralizedFinTransaction�j�I�u�W�F�N�g�𐶐�����B
        //�g�����U�N�V�����h�c�F�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V�����h�c
        long l_lngTransactionId = l_feqFinTransactionParams.getFinTransactionId();        
        log.debug("�g�����U�N�V�����h�c= " + l_lngTransactionId);
        
        //�����h�c�F�@@�g�����U�N�V�����i������薾�ׁj�s.�����h�c
        long l_lngAccountId = l_feqFinTransactionParams.getAccountId();
        log.debug("�����h�c= " + l_lngAccountId);
        
        //�⏕�����h�c�F�@@�g�����U�N�V�����i������薾�ׁj�s.�⏕�����h�c
        long l_lngSubAccountId = l_feqFinTransactionParams.getSubAccountId();
        log.debug("�⏕�����h�c= " + l_lngSubAccountId);
        
        //��n���z�F�@@�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V�����h�c
        double l_dblNetAmount  = l_feqFinTransactionParams.getNetAmount();
        log.debug("��n���z= " + l_dblNetAmount);
        
        //��n���F�@@�g�����U�N�V�����i������薾�ׁj�s.��n��
        Timestamp l_timDeliveryDate = l_feqFinTransactionParams.getDeliveryDate();
        
        //�g�����U�N�V�����^�C�v�F�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V�����^�C�v
        FinTransactionType l_finTransactionType = l_feqFinTransactionParams.getFinTransactionType();
        
        //description�F
        String l_strDescription = 
            "FeqFinTransaction. productId(" + l_feqFinTransactionParams.getProductId() + ")," +
                "marketId(" + l_feqFinTransactionParams.getMarketId() + ")," +
                "quantity(" + l_feqFinTransactionParams.getQuantity() + ")," +
                "price(" + l_feqFinTransactionParams.getPrice() + ")" ;
        
        //�g���[�f�B���O���W���[�����F�@@getTradingModule().getTradingModuleName()
        String l_strTradingModule = getTradingModule().getTradingModuleName();
        log.debug("�g���[�f�B���O���W���[����= " + l_strTradingModule);
        
        //�g�����U�N�V�������������F�g�����U�N�V�����i������薾�ׁj�s.�g�����U�N�V������������ 
        Timestamp l_timTransactionDate = l_feqFinTransactionParams.getFinTransactionTimestamp();
        GeneralizedFinTransaction l_gFinTransacton = 
            new GeneralizedFinTransaction(
                l_lngTransactionId, 
                l_lngAccountId, 
                l_lngSubAccountId, 
                l_timDeliveryDate, 
                l_finTransactionType, 
                l_dblNetAmount, 
                l_strDescription, 
                l_strTradingModule, 
                l_timTransactionDate);
        
        //�Q�j�@@�f�s�k�֒ʒm����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        GeneralizedFinTransactionManager l_transactionManager = l_finApp.getGeneralizedFinTransactionManager(); 
        
        l_transactionManager.notifyTransaction(l_gFinTransacton);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����To�g�����U�N�V����) <BR>
     * �isetExecutionInfoToMarketOrderedTrans�̃I�[�o�[���C�h�j<BR>
     * �������g�����U�N�V�����ɃZ�b�g����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O���c���jset�����To�g�����U�N�V�����v�Q�ƁB<BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s)<BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@param l_feqOrderExecution - (���) <BR>
     * ���I�u�W�F�N�g
     * @@param l_feqOrderUnitRow - (�����P�ʍs) <BR>
     * �����P�ʍs�I�u�W�F�N�g
     * @@roseuid 4288325A0172
     */
    protected void setExecutionInfoToMarketOrderedTrans(
        FeqFinTransactionParams l_feqFinTransactionParams, 
        FeqOrderExecution l_feqOrderExecution, 
        FeqOrderUnitRow l_feqOrderUnitRow)
    {
        final String STR_METHOD_NAME = 
            "setExecutionInfoToMarketOrderedTrans(FeqFinTransactionParams,FeqOrderExecution,FeqOrderUnitRow)";    
        log.entering(STR_METHOD_NAME);
        
        if (l_feqFinTransactionParams == null || l_feqOrderExecution == null || l_feqOrderUnitRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqProductManager l_productManager = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        try
        {
            //1.1super�N���X�̏������R�[�����A�������g�����U�N�V�����s�ɃZ�b�g����B 
            super.setExecutionInfoToMarketOrderedTrans(l_feqFinTransactionParams, l_feqOrderExecution, l_feqOrderUnitRow); 
            
            //1.2��萔�ʂ��擾����
            double l_dblExecutionQuantity = l_feqOrderExecution.getExecutionQuantity();
            if (Double.isNaN(l_dblExecutionQuantity))
            {
                l_dblExecutionQuantity = 0.0D;
            }
            log.debug("��萔��= " + l_dblExecutionQuantity);
            
            //1.3���P�����擾����
            double l_dblExecutionPrice = l_feqOrderExecution.getExecutionPrice();
            if (Double.isNaN(l_dblExecutionPrice))
            {
                l_dblExecutionPrice = 0.0D;
            }
            log.debug("���P��= " + l_dblExecutionPrice);
            
			//1.6�����h�c���擾����B
			long l_lngProductId = l_feqFinTransactionParams.getProductId();
			log.debug("�����h�c= " + l_lngProductId);
			
			//�@@getProductId()�ɊY������O����������
			WEB3FeqProduct l_feqProduct = l_productManager.getFeqProduct(l_lngProductId);
			
			//  get�ʉ�()
			WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            
            //1.4����������v�Z����B
            double l_dblExecutionAmount = 
                l_bizlogicProvider.calcExecutionAmount(l_dblExecutionQuantity, l_dblExecutionPrice);
			BigDecimal l_bdExecutionAmount = new BigDecimal(l_dblExecutionAmount);
			int l_intDecimalPlace = l_currency.getScale();
			l_bdExecutionAmount = l_bdExecutionAmount.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
			l_dblExecutionAmount = l_bdExecutionAmount.doubleValue();
            log.debug("��������̊ۂ߂�����= " + l_dblExecutionAmount);
            
            //1.5�⏕�����h�c���擾����B
            long l_lngSubAccountId = l_feqFinTransactionParams.getSubAccountId();
            log.debug("�⏕�����h�c= " + l_lngSubAccountId);
            
            //1.7�s��h�c���擾����B
            long l_lngMarketId = l_feqFinTransactionParams.getMarketId();
            log.debug("�s��h�c= " + l_lngMarketId);
            
            //1.8�g�����U�N�V�����^�C�v���擾����B
            FinTransactionType l_transactionType = l_feqFinTransactionParams.getFinTransactionType();
            
            //1.9�בփ��[�g���擾����B
            double l_dblFxRate = l_feqFinTransactionParams.getFxRate();
            log.debug("�בփ��[�g= " + l_dblFxRate);
            
            //1.10���������擾����B
            Date l_datExecutionTime = l_feqOrderExecution.getExecutionTimestamp();
            log.debug("������= " + l_datExecutionTime);
            
            //getSubAccountId()�ɊY������⏕����
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                    l_feqFinTransactionParams.getAccountId(), 
                    l_lngSubAccountId);
            
            //�s��F�@@getMarketId()�ɊY������s��
            WEB3GentradeMarket l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_lngMarketId);
            
            //����F�����P�ʁD������
            Date l_datBaseDate = WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(), "yyyyMMdd");   
            
            //is���t�FgetFinTransactionType() == �h�O�������h�̏ꍇtrue�B�ȊO�Afalse�B
            boolean l_blnIsBuy = false;           
            if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
            {
                l_blnIsBuy = true;
            }
            
            //is�w�l�F�����P�ʍs.�w�l == 0�̏ꍇfalse�B�ȊO�Atrue�B
            boolean l_blnIsPrice = false;
            if (l_feqOrderUnitRow.getLimitPrice() != 0)
            {
                l_blnIsPrice = true;
            }
            
            //1.11�O���������z�v�Z���s���B
            WEB3FeqAmountCalcResult l_amountCalcResult = l_bizlogicProvider.calcFeqAmount(
                l_subAccount, 
                l_feqProduct, 
                l_market, 
                l_datBaseDate, 
                l_datExecutionTime,
                l_dblExecutionAmount, 
                l_dblFxRate, 
                l_blnIsBuy, 
                true,
                l_blnIsPrice,
                l_feqOrderUnitRow.getOrderChanel());
            
            double l_dblprofitLoss = 0.0D;
            double l_dblcapitalGainTax = 0.0D;
            double l_dblProfitLossFc = 0.0D;
            double l_dblCapitalGainTaxFc = 0.0D;
            //1.12���̏ꍇ�i��ݻ޸�������=="�O������"�j�A���n���v�^���n�v�ł��v�Z����
            if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
            {
                //1.122�ŋ敪���擾����B
                TaxTypeEnum l_taxType = l_feqFinTransactionParams.getTaxType();
                
                //1.123��n�����擾����B
                Date l_datDeliveryDate = l_feqFinTransactionParams.getDeliveryDate();
                log.debug("��n��= " + l_datDeliveryDate);
                
                //1.124���n���v���v�Z����B
                l_dblprofitLoss = l_bizlogicProvider.calcCapitalProfitLoss(
                    l_amountCalcResult.getNetAmount(),
                    l_dblExecutionQuantity, 
                    l_lngProductId, 
                    l_subAccount, 
                    l_taxType);
                log.debug("���n���v= " + l_dblprofitLoss);
                
                //1.125���n�v�ł��v�Z����B
                l_dblcapitalGainTax = l_bizlogicProvider.calcCapitalGainTax(
                    l_subAccount,
                    l_taxType, 
                    l_dblprofitLoss, 
                    l_datDeliveryDate);
                log.debug("���n�v��= " + l_dblcapitalGainTax);
                
                l_dblProfitLossFc =
                    l_bizlogicProvider.calcForeignCCYAmount(
                        l_dblprofitLoss,
                        l_dblFxRate,
                        l_feqProduct.getProductId(),
                        false,
                        true);
                log.debug("���n�v���z�i�O�݁j= " + l_dblProfitLossFc);
                
                l_dblCapitalGainTaxFc =
                    l_bizlogicProvider.calcForeignCCYAmount(
                        l_dblcapitalGainTax,
                        l_dblFxRate,
                        l_feqProduct.getProductId(),
                        false,
                        true);
                log.debug("���n�v�Ŋz�i�O�݁j= " + l_dblCapitalGainTaxFc);
            }
            
            //1.13�s�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B
            //(*) �v���p�e�B�Z�b�g
            //�y��Trade�z�⑫����.DB�X�V\\11.�Ǘ��ҁE�o������
            //�u�O���o��_�g�����U�N�V�����i������薾�ׁj�d�l.xls#�O���o��_������薾��DB�X�V�i������j�v
            //�Q�ƁB
            FeqOrderExecutionRow l_executionRow = 
                (FeqOrderExecutionRow)l_feqOrderExecution.getDataSourceObject();
            
            //�g�����U�N�V������������ = ���.������
            l_feqFinTransactionParams.setFinTransactionTimestamp(l_executionRow.getExecTimestamp());
            
            //���.���ϋ敪
            l_feqFinTransactionParams.setSettleDiv(l_executionRow.getSettleDiv());
            
            //���.������
            l_feqFinTransactionParams.setBizDate(l_executionRow.getBizDate());
            
            //�����i��ݻ޸�������=�h�O�������h�j�̏ꍇ�A�ȉ��̌v�Z����*) ��n����~�i-1�j
            if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_transactionType))
            {
                l_feqFinTransactionParams.setNetAmount((l_amountCalcResult.getNetAmount() * -1));
                //�����i��ݻ޸�������=�h�O�������h�j�̏ꍇ�A�ȉ��̌v�Z����*) ��n����i�O�݁j�~�i-1�j
                l_feqFinTransactionParams.setNetAmountFc((l_amountCalcResult.getNetAmountFc() * -1));
                //�����i��ݻ޸�������=�h�O�������h�j�̏ꍇ�A�O�B
                l_feqFinTransactionParams.setCapitalGain(0.0D);
                //�����i��ݻ޸�������=�h�O�������h�j�̏ꍇ�A�O�B
                l_feqFinTransactionParams.setCapitalGainTax(0.0D);
                
                l_feqFinTransactionParams.setCapitalGainFc(0.0D);
                l_feqFinTransactionParams.setCapitalGainTaxFc(0.0D);
            }
            //�����i��ݻ޸�������=�h�O������h�j�̏ꍇ�A*)��n���
            else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_transactionType))
            {
                l_feqFinTransactionParams.setNetAmount(l_amountCalcResult.getNetAmount());
                //�����i��ݻ޸�������=�h�O������h�j�̏ꍇ�A*) ��n����i�O�݁j
                l_feqFinTransactionParams.setNetAmountFc(l_amountCalcResult.getNetAmountFc());
                //�����i��ݻ޸�������=�h�O������h�j�̏ꍇ�A���n���v�icalc���n���v()�̖߂�l�j�B
                l_feqFinTransactionParams.setCapitalGain(l_dblprofitLoss);
                //�����i��ݻ޸�������=�h�O������h�j�̏ꍇ�A���n�v�Łicalc���n�v��()�̖߂�l�j�B
                l_feqFinTransactionParams.setCapitalGainTax(l_dblcapitalGainTax);
                
                l_feqFinTransactionParams.setCapitalGainFc(l_dblProfitLossFc);
                l_feqFinTransactionParams.setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
            }
            
            //*�j�@@�ϑ��萔��
            l_feqFinTransactionParams.setCommissionFee(l_amountCalcResult.getCommissionFee());
            //*�j�@@�ϑ��萔�������
            l_feqFinTransactionParams.setCommissionFeeTax(l_amountCalcResult.getCommisionFeeTax());
            //*�j�@@�o�^No
            l_feqFinTransactionParams.setRegNo(
                l_amountCalcResult.getCommissionNumber() + l_amountCalcResult.getCommissionBranchNumber());
            //*�j�@@������
            l_feqFinTransactionParams.setChargeRatio(l_amountCalcResult.getChargeRatio());
            //*�j�@@���n���Z����i�~�݁j
            l_feqFinTransactionParams.setBalanceAmount(l_amountCalcResult.getBalanceAmount());
            //*�j�@@�ϑ��萔���i�O�݁j
            l_feqFinTransactionParams.setCommissionFeeFc(l_amountCalcResult.getCommissionFeeFc());
            //*�j�@@�ϑ��萔������Łi�O�݁j
            l_feqFinTransactionParams.setCommissionFeeTaxFc(l_amountCalcResult.getCommisionFeeTaxFc());
            //*�j�@@���n���Z����i�O�݁j
            l_feqFinTransactionParams.setBalanceAmountFc(l_amountCalcResult.getBalanceAmountFc());
            //*�j�@@���n�萔��
            l_feqFinTransactionParams.setForeignCommissionFee(l_amountCalcResult.getForignCommissionFee());
            //*�j�@@���n�����
            l_feqFinTransactionParams.setForeignTax(l_amountCalcResult.getForeignTax());
            //*�j�@@���̑��R�X�g�P
            l_feqFinTransactionParams.setForeignFeeExt1(l_amountCalcResult.getForeignFeeExt1());
            //*�j�@@���̑��R�X�g�Q
            l_feqFinTransactionParams.setForeignFeeExt2(l_amountCalcResult.getForeignFeeExt2());
            //���.���o�H�敪
            l_feqFinTransactionParams.setOrderExecRouteDiv(l_executionRow.getOrderExecRouteDiv());
            //���.�X�V�҃R�[�h
            l_feqFinTransactionParams.setLastUpdater(l_executionRow.getLastUpdater());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName()+  "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

    }
    
    /**
     * (create�g�����U�N�V�����s) <BR>
     * �����P�ʍs�^���s���A�g�����U�N�V�����s�I�u�W�F�N�g�𐶐�����B <BR>
     *  <BR>
     * �f�t�H���g�R���X�g���N�^�ɂăg�����U�N�V�����i������薾�ׁj�s <BR>
     * �I�u�W�F�N�g�𐶐����A <BR>
     * this.set�����To�g�����U�N�V����() <BR>
     * �ɂăg�����U�N�V�����s�I�u�W�F�N�g�ɒl���Z�b�g����B <BR>
     *  <BR>
     * [set�����To�g�����U�N�V����()�Ɏw�肷�����] <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�F�@@���������I�u�W�F�N�g <BR>
     * ���F�@@��� <BR>
     * �����P�ʍs�F�@@�����P�ʍs <BR>
     * @@param l_feqOrderExecution - (���) <BR>
     * ���I�u�W�F�N�g
     * @@param l_feqOrderUnitRow - (�����P�ʍs) <BR>
     * �����P�ʍs�I�u�W�F�N�g
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams
     * @@throws WEB3BaseException
     * @@roseuid 42B25DBB01AF
     */
    public FeqFinTransactionParams createTransactionParams(
        FeqOrderExecution l_feqOrderExecution, 
        FeqOrderUnitRow l_feqOrderUnitRow) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTransactionParams(FeqOrderExecution,FeqOrderUnitRow)";    
        log.entering(STR_METHOD_NAME);
        
        //�����P�ʍs�^���s���A�g�����U�N�V�����s�I�u�W�F�N�g�𐶐�����B
        FeqFinTransactionParams l_finTransactionParams = new FeqFinTransactionParams();
        this.setExecutionInfoToMarketOrderedTrans(l_finTransactionParams, l_feqOrderExecution, l_feqOrderUnitRow);
        
        log.exiting(STR_METHOD_NAME);
        return l_finTransactionParams;
    }
    
    /**
     * (update�g�����U�N�V����) <BR>
     * �萔�����v�Z�i������j�����{���A�g�����U�N�V�����f�[�^���X�V����B  <BR>
     *  <BR>
     * �V�[�P���X�}  <BR>
     * �u�i�O���c���jupdate�g�����U�N�V�����v�Q�ƁB  <BR>
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)
     * @@param l_blnIsCancel - (is���) <BR>
     * ��������̔��� <BR>
     *  <BR>
     * ture�F����� <BR>
     * false�F��� <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4288361E022D
     */
    public void updateTransaction(long l_lngOrderUnitId, boolean l_blnIsCancel) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long,boolean)";    
        log.entering(STR_METHOD_NAME);
        
        //�O�������X�V�f�[�^�}�l�[�W�����擾����B 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        try
        {
            //1.1���꒍���Ɋ֘A��������̎�����薾�ׂ����X�g�Ŏ擾����B  
            List l_lisForOrderUnit = l_dataManager.getFinTransactionForOrderUnit(l_lngOrderUnitId);
            
            //1.2�Y���f�[�^�Ȃ��A�܂��͐V�K���iis��� == false�j && ������̏ꍇ�iget������薾��ForOrderUnit().size() == 1�j�A
            //   �������I������B
            int l_intOrderUnitSize = 0;
            if (l_lisForOrderUnit!= null && !l_lisForOrderUnit.isEmpty())
            {
                l_intOrderUnitSize = l_lisForOrderUnit.size();
            }

            if (l_intOrderUnitSize == 0)
            {
                return;
            }
            if (!l_blnIsCancel && l_intOrderUnitSize == 1)
            {
                return;
            }
            
            if (l_lisForOrderUnit == null || l_lisForOrderUnit.isEmpty())
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //1.3�O���������z���v�Z���s���B
            FeqFinTransactionParams[] l_fransactionParams = new FeqFinTransactionParams[l_intOrderUnitSize];
            l_lisForOrderUnit.toArray(l_fransactionParams);
            
            WEB3FeqAmountCalcResultFactor l_calcResultFactor = l_bizlogicProvider.calcFeqAmountFactor(l_fransactionParams);
            //1.4�O�������v�Z���ʁi���v�j���擾����B
            WEB3FeqAmountCalcResult l_calcResult = l_calcResultFactor.getFeqAmountCalcResultTotal();
            
            //1.5������薾��List�iget������薾��ForOrderUnit()�̖߂�l�j�e�v�f����LOOP���� 
            for (int i = 0; i < l_intOrderUnitSize; i++)
            {
                //�X�V�O�̃g�����U�N�V�����i������薾�ׁj�s.��n������擾����B
                double l_dblNetAmountBeforeUpdate = l_fransactionParams[i].getNetAmount();

                //1.5.1�ϑ��萔�����擾����B 
                double l_dblCommisionFee = l_calcResultFactor.getCommisionFee(i);
                log.debug("�ϑ��萔��= " + l_dblCommisionFee);
                
                //1.5.2�ϑ��萔������ł��擾����B 
                double l_dblCommisionFeeTax = l_calcResultFactor.getCommisionFeeTax(i);
                log.debug("�ϑ��萔�������= " + l_dblCommisionFeeTax);
                
                //1.5.3���n���Z����i�~�݁j���擾����B 
                double l_dblBalanceAmount = l_calcResultFactor.getBalanceAmount(i);
                log.debug("���n���Z����i�~�݁j= " + l_dblBalanceAmount);
                
                //1.5.4get�ϑ��萔���i�O�݁j(
                double l_dblCommisionFeeFc = l_calcResultFactor.getCommisionFeeFc(i);
                log.debug("�ϑ��萔���i�O�݁j= " + l_dblCommisionFeeFc);
                
                //1.5.5get�ϑ��萔������Łi�O�݁j(
                double l_dblCommisionFeeTaxFc = l_calcResultFactor.getCommisionFeeTaxFc(i);
                log.debug("�ϑ��萔������Łi�O�݁j= " + l_dblCommisionFeeTaxFc);
                
                //1.5.6���n���Z������擾����B 
                double l_dblBalanceAmountFc = l_calcResultFactor.getBalanceAmountFc(i);
                log.debug("���n���Z��� = " + l_dblBalanceAmountFc);
                
                //1.5.7���n�萔�����擾����B 
                double l_dblForeignCommissionFee = l_calcResultFactor.getForeignCommissionFee(i);
                log.debug("���n�萔�� = " + l_dblForeignCommissionFee);
                
                //1.5.8���n����ł��擾����B
                double l_dblForeignTax = l_calcResultFactor.getForeignTax(i);
                log.debug("���n����� = " + l_dblForeignTax);
                
                //1.5.9���̑��R�X�g�P���擾����B 
                double l_dblForeignFeeExt1 = l_calcResultFactor.getForeignFeeExt1(i);
                log.debug("���̑��R�X�g�P = " + l_dblForeignFeeExt1);
                
                //1.5.10���̑��R�X�g�Q���擾����B 
                double l_dblForeignFeeExt2 = l_calcResultFactor.getForeignFeeExt2(i);
                log.debug("���̑��R�X�g�Q = " + l_dblForeignFeeExt2);
                
                double l_dblNetAmount = l_calcResultFactor.getNetAmount(i);
                log.debug("��n��� = " + l_dblNetAmount);
                double l_dblNetAmountFc = l_calcResultFactor.getNetAmountFc(i);
                log.debug("��n����i�O�݁j = " + l_dblNetAmountFc);
                
                //1.5.11�g�����U�N�V�����^�C�v���擾����B 
                FinTransactionType l_finTransactionType = l_fransactionParams[i].getFinTransactionType(); 
                
                //���n���v
                double l_dblProfitLoss = 0.0D;
                //���n�v��
                double l_dblCapitalGainTax = 0.0D;
                //���n�v���z�i�O�݁j
                double l_dblProfitLossFc = 0.0D;
                //���n�v�Ŋz�i�O�݁j
                double l_dblCapitalGainTaxFc = 0.0D;
                
                //�⏕�����h�c���擾����B
                long l_lngSubAccountId = l_fransactionParams[i].getSubAccountId();
                log.debug("�⏕�����h�c = " + l_lngSubAccountId);

                //getSubAccountId()�ɊY������⏕����
                WEB3GentradeSubAccount l_subAccount =
                    (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                        l_fransactionParams[i].getAccountId(), 
                        l_lngSubAccountId);

                //1.5.12���̏ꍇ�i��ݻ޸�������=="�O������"�j�A���n���v�^���n�v�ł��v�Z����
                if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                {
                    //1.5.12.2��萔�ʂ��擾����B
                    double l_dblQuantity = l_fransactionParams[i].getQuantity();
                    log.debug("��萔�� = " + l_dblQuantity);
                    
                    //1.5.12.32�����h�c���擾����B
                    long l_lngProductId = l_fransactionParams[i].getProductId();
                    log.debug("�����h�c = " + l_lngProductId);
                    

                    
                    //1.5.12.5�ŋ敪���擾����B
                    TaxTypeEnum l_taxTypeEnum = l_fransactionParams[i].getTaxType();
                    
                    //1.5.12.6���n���v���v�Z����B 
                    l_dblProfitLoss = 
                        l_bizlogicProvider.calcCapitalProfitLoss(
                            l_dblNetAmount, 
                            l_dblQuantity, 
                            l_lngProductId, 
                            l_subAccount, 
                            l_taxTypeEnum);
                    log.debug("���n���v = " + l_dblProfitLoss);
                    
                    //1.5.12.7��n�����擾����B
                    Date l_datDeliveryDate = l_fransactionParams[i].getDeliveryDate();
                    log.debug("��n�� = " + l_datDeliveryDate);
                    
                    //1.5.12.8���n�v�ł��v�Z����B 
                    l_dblCapitalGainTax = 
                        l_bizlogicProvider.calcCapitalGainTax(l_subAccount, l_taxTypeEnum, l_dblProfitLoss, l_datDeliveryDate);
                    log.debug("���n�v�� = " + l_dblCapitalGainTax);

                    // calc�O�݊��Z(double, double, long, boolean, boolean)
                    // ���n�v���z�i�O�݁j���v�Z����B
                    // ���z�i�~�݁j�F�@@calc���n���v()
                    // �בփ��[�g�F�@@������薾�׃g�����U�N�V�������擾�����בփ��[�g
                    // ����ID�F�@@getProductId()
                    // is���t�F�@@false
                    // is���v�Z�F�@@true
                    double l_dbFxRate = l_fransactionParams[i].getFxRate();
                    l_dblProfitLossFc =
                        l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblProfitLoss,
                            l_dbFxRate,
                            l_lngProductId,
                            false,
                            true);
                    log.debug("���n�v���z�i�O�݁j= " + l_dblProfitLossFc);

                    // calc�O�݊��Z(double, double, long, boolean, boolean)
                    // ���n�v���z�i�O�݁j���v�Z����B
                    // ���z�i�~�݁j�F�@@calc���n�v��()
                    // �בփ��[�g�F�@@������薾�׃g�����U�N�V�������擾�����בփ��[�g
                    // ����ID�F�@@getProductId()
                    // is���t�F�@@false
                    // is���v�Z�F�@@true
                    l_dblCapitalGainTaxFc =
                        l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblCapitalGainTax,
                            l_dbFxRate,
                            l_lngProductId,
                            false,
                            true);
                    log.debug("���n�v�Ŋz�i�O�݁j= " + l_dblCapitalGainTaxFc);
                }
                
                //1.5.13�s�I�u�W�F�N�g�v���p�e�B�ɒl���Z�b�g����B
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
                {
                    l_fransactionParams[i].setNetAmount(l_dblNetAmount * -1);
                    l_fransactionParams[i].setNetAmountFc(l_dblNetAmountFc * -1);
                    l_fransactionParams[i].setCapitalGain(0);
                    l_fransactionParams[i].setCapitalGainTax(0);
                    l_fransactionParams[i].setCapitalGainFc(0);
                    l_fransactionParams[i].setCapitalGainTaxFc(0);
                }

                else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                {
                    l_fransactionParams[i].setNetAmount(l_dblNetAmount);
                    l_fransactionParams[i].setNetAmountFc(l_dblNetAmountFc);
                    l_fransactionParams[i].setCapitalGain(l_dblProfitLoss);
                    l_fransactionParams[i].setCapitalGainTax(l_dblCapitalGainTax);
                    l_fransactionParams[i].setCapitalGainFc(l_dblProfitLossFc);
                    l_fransactionParams[i].setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
                }
                
                //�������̈ϑ��萔��
                l_fransactionParams[i].setCommissionFee(l_dblCommisionFee);
                //�������̈ϑ��萔�������
                l_fransactionParams[i].setCommissionFeeTax(l_dblCommisionFeeTax);
                //�������̓o�^No
                l_fransactionParams[i].setRegNo(
                    l_calcResult.getCommissionNumber() + l_calcResult.getCommissionBranchNumber());
                //�������̒�����
                l_fransactionParams[i].setChargeRatio(l_calcResult.getChargeRatio());
                //�������̌��n���Z����i�~�݁j
                l_fransactionParams[i].setBalanceAmount(l_dblBalanceAmount);
                //�������̈ϑ��萔���i�O�݁j
                l_fransactionParams[i].setCommissionFeeFc(l_dblCommisionFeeFc);
                //�������̈ϑ��萔������Łi�O�݁j
                l_fransactionParams[i].setCommissionFeeTaxFc(l_dblCommisionFeeTaxFc);
                //�������̌��n���Z����i�O�݁j
                l_fransactionParams[i].setBalanceAmountFc(l_dblBalanceAmountFc);
                //�������̌��n�萔��
                l_fransactionParams[i].setForeignCommissionFee(l_dblForeignCommissionFee);
                //�������̌��n�����
                l_fransactionParams[i].setForeignTax(l_dblForeignTax);
                //�������̂��̑��R�X�g�P
                l_fransactionParams[i].setForeignFeeExt1(l_dblForeignFeeExt1);
                //�������̂��̑��R�X�g�Q
                l_fransactionParams[i].setForeignFeeExt2(l_dblForeignFeeExt2);
                //�����P��.�X�V�҃R�[�h
                OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitId);
                FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_fransactionParams[i].setLastUpdater(l_orderUnitRow.getLastUpdater());
                //���ݓ���
                l_fransactionParams[i].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                //1.5.14�g�����U�N�V�����i������薾�ׁj���X�V����B 
                Map l_mapValues = new HashMap();
                //��n��� 
                l_mapValues.put("net_amount", new BigDecimal(l_fransactionParams[i].getNetAmount()));
                //��n����i�O�݁j 
                l_mapValues.put("net_amount_fc", new BigDecimal(l_fransactionParams[i].getNetAmountFc()));
                //�ϑ��萔�� 
                l_mapValues.put("commission_fee", new BigDecimal(l_fransactionParams[i].getCommissionFee()));
                //�ϑ��萔������� 
                l_mapValues.put("commission_fee_tax", new BigDecimal(l_fransactionParams[i].getCommissionFeeTax()));
                //�o�^No
                l_mapValues.put("reg_no", l_fransactionParams[i].getRegNo());
                //������
                l_mapValues.put("charge_ratio", new BigDecimal(l_calcResult.getChargeRatio()));
                //���n���Z����i�~�݁j 
                l_mapValues.put("balance_amount", new BigDecimal(l_fransactionParams[i].getBalanceAmount()));
                //�ϑ��萔���i�O�݁j 
                l_mapValues.put("commission_fee_fc", new BigDecimal(l_fransactionParams[i].getCommissionFeeFc()));
                //�ϑ��萔������Łi�O�݁j 
                l_mapValues.put("commission_fee_tax_fc", new BigDecimal(l_fransactionParams[i].getCommissionFeeTaxFc()));
                //���n���Z����i�O�݁j 
                l_mapValues.put("balance_amount_fc", new BigDecimal(l_fransactionParams[i].getBalanceAmountFc()));
                //���n�萔�� 
                l_mapValues.put("foreign_commission_fee", new BigDecimal(l_fransactionParams[i].getForeignCommissionFee()));
                //���n����� 
                l_mapValues.put("foreign_tax", new BigDecimal(l_fransactionParams[i].getForeignTax()));
                //���̑��R�X�g�P 
                l_mapValues.put("foreign_fee_ext1", new BigDecimal(l_fransactionParams[i].getForeignFeeExt1()));
                //���̑��R�X�g�Q 
                l_mapValues.put("foreign_fee_ext2", new BigDecimal(l_fransactionParams[i].getForeignFeeExt2()));
                //���n�v���z 
                l_mapValues.put("capital_gain", new BigDecimal(l_fransactionParams[i].getCapitalGain()));
                //���n�v�Ŋz 
                l_mapValues.put("capital_gain_tax", new BigDecimal(l_fransactionParams[i].getCapitalGainTax()));
                //���n�v���z�i�O�݁j
                l_mapValues.put("capital_gain_fc", new BigDecimal(l_fransactionParams[i].getCapitalGainFc()));
                //���n�v�Ŋz�i�O�݁j
                l_mapValues.put("capital_gain_tax_fc", new BigDecimal(l_fransactionParams[i].getCapitalGainTaxFc()));
                //�X�V�҃R�[�h 
                l_mapValues.put("last_updater", l_fransactionParams[i].getLastUpdater());
                //�X�V���t 
                l_mapValues.put("last_updated_timestamp", l_fransactionParams[i].getLastUpdatedTimestamp());
                
                l_dataManager.updateFinTransaction(l_fransactionParams[i], l_mapValues);
                //1.5.15�ڋq���薾�ׁC�⏕�������X�V����B 
                this.notifyGtl(l_fransactionParams[i]);

                boolean l_blnIsDayTradeAdoption = false;
                boolean l_blnIsDayTradeMarket = false;
                WEB3GentradeMarket l_market =
                    (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_fransactionParams[i].getMarketId()));
                WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

                Object l_obj1 = ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3ThreadLocalSystemAttributePathDef.NETTING_EXCHANGE_FLAG);

                //���t�i�g�����U�N�V�����i������薾�ׁj�s.��ݻ޸�������==�h�O�������h�j
                //����is���v�����̗p()==true ���@@is���v��s��()==true�̏ꍇ
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType)
                    &&( (l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket) || (l_obj1 != null && l_obj1.equals(BooleanEnum.TRUE))))
                {
                    //�o���ʒm��
                    Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_CHECK_FLAG);
                    if (l_obj != null && l_obj.equals(BooleanEnum.TRUE))
                    {
                        boolean l_blnDayExchange = false;

                        l_blnDayExchange = ((Boolean)ThreadLocalSystemAttributesRegistry.getAttribute(
                            WEB3ThreadLocalSystemAttributePathDef.DAY_EXCHANGE_REGISTRATION_FLAG)).booleanValue();
                        if (!l_blnDayExchange)
                        {
                            log.exiting(STR_METHOD_NAME);
                            return;
                        }
                    }

                    //�ۗL���Y�X�V�i�뉿�X�V�p�j(long, double, double)
                    this.assetUpdateNettingExchangeRateAdoption(
                        l_fransactionParams[i].getAssetId(),
                        l_dblNetAmountBeforeUpdate,
                        l_fransactionParams[i].getNetAmount());
                }
            }
  
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

    }
    
    /**
     * (update�g�����U�N�V����) <BR>
     * �ꊇ�ΏۂƂȂ�g�����U�N�V�����ɂ��āA <BR>
     * �萔�����v�Z�i������j�����{���A�g�����U�N�V�����f�[�^���X�V����B  <BR>
     *  <BR>
     * �V�[�P���X�}  <BR>
     * �u�i�O���c���jupdate�g�����U�N�V�����i�ꊇ�j�v�Q�ƁB  <BR>
     * @@param l_lngOrderUnitIds - (�����P�ʂh�c�̈ꗗ)
     * @@param l_datBaseDate - (���)
     * @@param l_blnIsNetting - (is�l�b�e�B���O����)
     * @@throws WEB3BaseException 
     * @@roseuid 42B672ED0219
     */
    public void updateTransaction(
        long[] l_lngOrderUnitIds,
        Date l_datBaseDate,
        boolean l_blnIsNetting) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTransaction(long[], Date, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lngOrderUnitIds == null || l_lngOrderUnitIds.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�O�������X�V�f�[�^�}�l�[�W�����擾����B 
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        
        try
        {
            //1.1�萔���ꊇ�ΏۂƂȂ�����̎�����薾�ׂ����X�g�Ŏ擾����B
            List l_lisLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIds);
            //1.2�Y���f�[�^�Ȃ� �܂��� ������̏ꍇ�iget������薾��For�ꊇ�Ώ�().size() == 1�j�A�������I������B
            if (l_lisLumpObj == null || l_lisLumpObj.isEmpty() || l_lisLumpObj.size() == 1 )
            {
                return;
            }
            //1.3�ꊇ�ΏۂƂȂ�g�����U�N�V�������i�[����ArrayList�𐶐�����B
            List listObj = new ArrayList();
            //1.4get������薾��For�ꊇ�Ώ�()�̖߂�l�̗v�f����Loop����
            for (int i = 0; i < l_lisLumpObj.size(); i++)
            {
                //1.4.1add(arg0 : Object)
                FeqFinTransactionRow l_finTransactionRow = (FeqFinTransactionRow) l_lisLumpObj.get(i);
                log.debug("FinTransactionId = " + l_finTransactionRow.getFinTransactionId());
                log.debug("OrderUnitId = " + l_finTransactionRow.getOrderUnitId());
                listObj.add(l_finTransactionRow);
                
                //1.4.2(*)�ȉ��̏����̂����ꂩ�ɊY������ꍇ�A�萔���ꊇ���������{����B
                //�@@���ݏ������Ă���v�f���Ō�̗v�f�̏ꍇ
                //�A���̗v�f(index + 1)�ƁA����̗v�f�ɂ��āA�ꊇ�ΏۃL�[(*1)��
                //�قȂ����ꍇ
                boolean l_blnIsNextFactor = false;
                if ((i + 1) < l_lisLumpObj.size())
                {
                    FeqFinTransactionRow l_finTransactionRowNext = (FeqFinTransactionRow) l_lisLumpObj.get(i + 1);
                    //(*1)�ꊇ�ΏۃL�[�E�E�E�ȉ��̒l�𕶎���A���������́B
                    //�g�����U�N�V����.����ID + �g�����U�N�V����.�g�����U�N�V�����^�C�v
                    //+  �g�����U�N�V����.���ϋ敪 + �g�����U�N�V����.������
                    StringBuffer l_strLumpObj = new StringBuffer();
                    l_strLumpObj.append(l_finTransactionRow.getProductId());
                    l_strLumpObj.append(l_finTransactionRow.getFinTransactionType().toString());
                    l_strLumpObj.append(l_finTransactionRow.getSettleDiv());
                    l_strLumpObj.append(l_finTransactionRow.getBizDate());
    
                    StringBuffer l_strLumpObjNext = new StringBuffer();
                    l_strLumpObjNext.append(l_finTransactionRowNext.getProductId());
                    l_strLumpObjNext.append(l_finTransactionRowNext.getFinTransactionType().toString());
                    l_strLumpObjNext.append(l_finTransactionRowNext.getSettleDiv());
                    l_strLumpObjNext.append(l_finTransactionRowNext.getBizDate());
                    
                    if(!(l_strLumpObj.toString().equals(l_strLumpObjNext.toString())))
                    {
                        l_blnIsNextFactor = true;
                    }
                }
                boolean l_blnIsLastFactor = false; 
                if ((i + 1) == l_lisLumpObj.size())
                {
                    l_blnIsLastFactor = true;
                }
                
                if (l_blnIsLastFactor || l_blnIsNextFactor)
                {
                    //1.4.2.1���꒍���`�F�b�N
                    boolean l_blnIsOrderUnit = false;
                    
                    Map l_mapOrderUnitId = new HashMap();
                    for (int k = 0; k < listObj.size(); k++)
                    {
                        FeqFinTransactionRow l_row = (FeqFinTransactionRow)listObj.get(k);
                        Long l_lngOrderUnitId = new Long(l_row.getOrderUnitId());
                        l_mapOrderUnitId.put(l_lngOrderUnitId, l_lngOrderUnitId);
                    }
                    if (l_mapOrderUnitId.size() == 1)
                    {
                        l_blnIsOrderUnit = true;
                    }
    
                    
                    if (l_blnIsOrderUnit)
                    {
                        listObj.clear();
                        continue;
                    }
                    //1.4.2.2�ꊇ�ΏۂƂȂ�g�����U�N�V�����i������薾�ׁj�̔z��𐶐�����B
                    FeqFinTransactionParams[] l_fransactionParams = new FeqFinTransactionParams[listObj.size()];
                    listObj.toArray(l_fransactionParams);
                    //1.4.2.3�O���������z���v�Z���s���B 
                    WEB3FeqAmountCalcResultFactor l_resultFactor = l_bizlogicProvider.calcFeqAmountFactor(l_fransactionParams);
                    //1.4.2.4�O�������v�Z���ʁi���v�j���擾����B
                    WEB3FeqAmountCalcResult l_calcResult = l_resultFactor.getFeqAmountCalcResultTotal();
                                        
                    //1.4.2.5toArray()�̖߂�l�e�v�f����LOOP����
                    for (int j = 0; j < l_fransactionParams.length; j++)
                    {
                        double l_dblNetAmountBeforeUpdate = l_fransactionParams[j].getNetAmount();
                        //1.4.2.5.1�ϑ��萔�����擾����B 
                        double l_dblCommisionFee = l_resultFactor.getCommisionFee(j);
                        log.debug("�ϑ��萔�� = " + l_dblCommisionFee);
                        //1.4.2.5.2�ϑ��萔������ł��擾����B 
                        double l_dblCommisionFeeTax = l_resultFactor.getCommisionFeeTax(j);
                        log.debug("�ϑ��萔������� = " + l_dblCommisionFeeTax);
                        //1.4.2.5.3���n���Z����i�~�݁j���擾����B 
                        double l_dblBalanceAmount = l_resultFactor.getBalanceAmount(j);
                        log.debug("���n���Z����i�~�݁j = " + l_dblBalanceAmount);
                        //1.4.2.5.4get�ϑ��萔���i�O�݁j(
                        double l_dblCommisionFeeFc = l_resultFactor.getCommisionFeeFc(j);
                        log.debug("�ϑ��萔���i�O�݁j = " + l_dblCommisionFeeFc);
                        //1.4.2.5.5get�ϑ��萔������Łi�O�݁j(
                        double l_dblCommisionFeeTaxFc = l_resultFactor.getCommisionFeeTaxFc(j);
                        log.debug("�ϑ��萔������Łi�O�݁j = " + l_dblCommisionFeeTaxFc);
                        //1.4.2.5.6���n���Z������擾����B 
                        double l_dblBalanceAmountFc = l_resultFactor.getBalanceAmountFc(j);
                        log.debug("���n���Z��� = " + l_dblBalanceAmountFc);
                        //1.4.2.5.7���n�萔�����擾����B 
                        double l_dblForeignCommissionFee = l_resultFactor.getForeignCommissionFee(j);
                        log.debug("���n�萔�� = " + l_dblForeignCommissionFee);
                        //1.4.2.5.8���n����ł��擾����B
                        double l_dblForeignTax = l_resultFactor.getForeignTax(j);
                        log.debug("���n����� = " + l_dblForeignTax);
                        //1.4.2.5.9���̑��R�X�g�P���擾����B 
                        double l_dblForeignFeeExt1 = l_resultFactor.getForeignFeeExt1(j);
                        log.debug("���̑��R�X�g�P = " + l_dblForeignFeeExt1);
                        //1.4.2.5.10���̑��R�X�g�Q���擾����B 
                        double l_dblForeignFeeExt2 = l_resultFactor.getForeignFeeExt2(j);
                        log.debug("���̑��R�X�g�Q = " + l_dblForeignFeeExt2);
                        double l_dblNetAmount = l_resultFactor.getNetAmount(j);
                        log.debug("��n��� = " + l_dblNetAmount);
                        double l_dblNetAmountFc = l_resultFactor.getNetAmountFc(j);
                        log.debug("��n����i�O�݁j = " + l_dblNetAmountFc);
                        //1.4.2.5.11�g�����U�N�V�����^�C�v���擾����B 
                        FinTransactionType l_finTransactionType = l_fransactionParams[j].getFinTransactionType();
    
                        double l_dblProfitLoss = 0.0D;
                        double l_dblCapitalGainTax = 0.0D;
                        double l_dblProfitLossFc = 0.0D;
                        double l_dblCapitalGainTaxFc = 0.0D;
                        //�⏕�����h�c���擾����B
                        long l_lngSubAccountId = l_fransactionParams[j].getSubAccountId();
                        log.debug("�⏕�����h�c = " + l_lngSubAccountId);
                        //getSubAccountId()�ɊY������⏕����
                        WEB3GentradeSubAccount l_subAccount = 
                            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                                l_fransactionParams[j].getAccountId(), 
                                l_lngSubAccountId);
                        //1.4.2.5.12���̏ꍇ�i��ݻ޸�������=="�O������"�j�A���n���v�^���n�v�ł��v�Z����
                        if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                        {
                            //1.4.2.5.12.2��萔�ʂ��擾����B
                            double l_dblQuantity = l_fransactionParams[j].getQuantity();
                            log.debug("��萔�� = " + l_dblQuantity);
                            //1.4.2.5.12.3�����h�c���擾����B
                            long l_lngProductId = l_fransactionParams[j].getProductId();
                            log.debug("�����h�c = " + l_lngProductId);

                            //1.4.2.5.12.5�ŋ敪���擾����B
                            TaxTypeEnum l_taxTypeEnum = l_fransactionParams[j].getTaxType();
                            //1.4.2.5.12.6���n���v���v�Z����B 
                            l_dblProfitLoss = 
                                l_bizlogicProvider.calcCapitalProfitLoss(
                                    l_dblNetAmount, 
                                    l_dblQuantity, 
                                    l_lngProductId, 
                                    l_subAccount, 
                                    l_taxTypeEnum);
                            log.debug("���n���v = " + l_dblProfitLoss);
                            //1.4.2.5.12.7��n�����擾����B
                            Date l_datDeliveryDate = l_fransactionParams[j].getDeliveryDate();
                            log.debug("��n�� = " + l_datDeliveryDate);
                            //1.4.2.5.12.8���n�v�ł��v�Z����B 
                            l_dblCapitalGainTax = 
                                l_bizlogicProvider.calcCapitalGainTax(l_subAccount, l_taxTypeEnum, l_dblProfitLoss, l_datDeliveryDate);
                            log.debug("���n�v�� = " + l_dblCapitalGainTax);
                            double l_dbFxRate = l_fransactionParams[j].getFxRate();
                            l_dblProfitLossFc =
                                l_bizlogicProvider.calcForeignCCYAmount(
                                    l_dblProfitLoss,
                                    l_dbFxRate,
                                    l_lngProductId,
                                    false,
                                    true);
                            log.debug("���n�v���z�i�O�݁j= " + l_dblProfitLossFc);
                
                            l_dblCapitalGainTaxFc =
                                l_bizlogicProvider.calcForeignCCYAmount(
                                    l_dblCapitalGainTax,
                                    l_dbFxRate,
                                    l_lngProductId,
                                    false,
                                    true);
                            log.debug("���n�v�Ŋz�i�O�݁j= " + l_dblCapitalGainTaxFc);
                        }
                        
                        //1.4.2.5.13(*) �v���p�e�B�Z�b�g
                        //�y��Trade�z�⑫����.DB�X�V\\20.�i�ǁj�o���I��
                        //�u�O���o���I��_�g�����U�N�V�����i������薾�ׁj�d�l.xls�v�Q�ƁB

                        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
                        {
                            l_fransactionParams[j].setNetAmount(l_dblNetAmount * -1);
                            l_fransactionParams[j].setNetAmountFc(l_dblNetAmountFc * -1);
                            l_fransactionParams[j].setCapitalGain(0);
                            l_fransactionParams[j].setCapitalGainTax(0);
                            l_fransactionParams[j].setCapitalGainFc(0);
                            l_fransactionParams[j].setCapitalGainTaxFc(0);
                        }

                        else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_finTransactionType))
                        {
                            l_fransactionParams[j].setNetAmount(l_dblNetAmount);
                            l_fransactionParams[j].setNetAmountFc(l_dblNetAmountFc);
                            l_fransactionParams[j].setCapitalGain(l_dblProfitLoss);
                            l_fransactionParams[j].setCapitalGainTax(l_dblCapitalGainTax);
                            l_fransactionParams[j].setCapitalGainFc(l_dblProfitLossFc);
                            l_fransactionParams[j].setCapitalGainTaxFc(l_dblCapitalGainTaxFc);
                        }
                        
                        //�������̈ϑ��萔��
                        l_fransactionParams[j].setCommissionFee(l_dblCommisionFee);
                        //�������̈ϑ��萔�������
                        l_fransactionParams[j].setCommissionFeeTax(l_dblCommisionFeeTax);
                        //�������̓o�^No
                        l_fransactionParams[j].setRegNo(
                            l_calcResult.getCommissionNumber() + l_calcResult.getCommissionBranchNumber());
                        //�������̒�����
                        l_fransactionParams[j].setChargeRatio(l_calcResult.getChargeRatio());
                        //�������̌��n���Z����i�~�݁j
                        l_fransactionParams[j].setBalanceAmount(l_dblBalanceAmount);
                        //�������̈ϑ��萔���i�O�݁j
                        l_fransactionParams[j].setCommissionFeeFc(l_dblCommisionFeeFc);
                        //�������̈ϑ��萔������Łi�O�݁j
                        l_fransactionParams[j].setCommissionFeeTaxFc(l_dblCommisionFeeTaxFc);
                        //�������̌��n���Z����i�O�݁j
                        l_fransactionParams[j].setBalanceAmountFc(l_dblBalanceAmountFc);
                        //�������̌��n�萔��
                        l_fransactionParams[j].setForeignCommissionFee(l_dblForeignCommissionFee);
                        //�������̌��n�����
                        l_fransactionParams[j].setForeignTax(l_dblForeignTax);
                        //�������̂��̑��R�X�g�P
                        l_fransactionParams[j].setForeignFeeExt1(l_dblForeignFeeExt1);
                        //�������̂��̑��R�X�g�Q
                        l_fransactionParams[j].setForeignFeeExt2(l_dblForeignFeeExt2);
                        //���o�H�敪
                        //�Z�b�V������胍�O�C���h�c���擾�A���O�C���h�c�ɊY������Ǘ���.�Ǘ��҃R�[�h�B
                        //���O�C���h�c���擾�ł��Ȃ��ꍇ�́Anull�B.�X�V�҃R�[�h                        
                        try
                        {
                            OpLoginSecurityService l_opLoginSecurityService =
                                (OpLoginSecurityService)Services.getService(
                                OpLoginSecurityService.class);
                            
                            l_opLoginSecurityService.getLoginId();
                                                        
                            WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
                            l_fransactionParams[j].setLastUpdater(l_administrator.getAdministratorCode());
                        }
                        catch (IllegalSessionStateException l_ex)
                        {
                            l_fransactionParams[j].setLastUpdater(null);
                        }
                        //���ݓ���
                        l_fransactionParams[j].setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        
                        //1.4.2.5.14�g�����U�N�V�����i������薾�ׁj���X�V����B 
                        Map l_mapValues = new HashMap();
                        //��n��� 
                        l_mapValues.put("net_amount", new BigDecimal(l_fransactionParams[j].getNetAmount()));
                        //��n����i�O�݁j 
                        l_mapValues.put("net_amount_fc", new BigDecimal(l_fransactionParams[j].getNetAmountFc()));
                        //�ϑ��萔�� 
                        l_mapValues.put("commission_fee", new BigDecimal(l_fransactionParams[j].getCommissionFee()));
                        //�ϑ��萔������� 
                        l_mapValues.put("commission_fee_tax", new BigDecimal(l_fransactionParams[j].getCommissionFeeTax()));
                        //�o�^No
                        l_mapValues.put("reg_no", l_fransactionParams[j].getRegNo());
                        //������
                        l_mapValues.put("charge_ratio", new BigDecimal(l_calcResult.getChargeRatio()));
                        //���n���Z����i�~�݁j 
                        l_mapValues.put("balance_amount", new BigDecimal(l_fransactionParams[j].getBalanceAmount()));
                        //�ϑ��萔���i�O�݁j 
                        l_mapValues.put("commission_fee_fc", new BigDecimal(l_fransactionParams[j].getCommissionFeeFc()));
                        //�ϑ��萔������Łi�O�݁j 
                        l_mapValues.put("commission_fee_tax_fc", new BigDecimal(l_fransactionParams[j].getCommissionFeeTaxFc()));
                        //���n���Z����i�O�݁j 
                        l_mapValues.put("balance_amount_fc", new BigDecimal(l_fransactionParams[j].getBalanceAmountFc()));
                        //���n�萔�� 
                        l_mapValues.put("foreign_commission_fee", new BigDecimal(l_fransactionParams[j].getForeignCommissionFee()));
                        //���n����� 
                        l_mapValues.put("foreign_tax", new BigDecimal(l_fransactionParams[j].getForeignTax()));
                        //���̑��R�X�g�P 
                        l_mapValues.put("foreign_fee_ext1", new BigDecimal(l_fransactionParams[j].getForeignFeeExt1()));
                        //���̑��R�X�g�Q 
                        l_mapValues.put("foreign_fee_ext2", new BigDecimal(l_fransactionParams[j].getForeignFeeExt2()));
                        //���n�v���z 
                        l_mapValues.put("capital_gain", new BigDecimal(l_fransactionParams[j].getCapitalGain()));
                        //���n�v�Ŋz 
                        l_mapValues.put("capital_gain_tax", new BigDecimal(l_fransactionParams[j].getCapitalGainTax()));
                        //���n�v���z�i�O�݁j
                        l_mapValues.put("capital_gain_fc", new BigDecimal(l_fransactionParams[j].getCapitalGainFc()));
                        //���n�v�Ŋz�i�O�݁j
                        l_mapValues.put("capital_gain_tax_fc", new BigDecimal(l_fransactionParams[j].getCapitalGainTaxFc()));
                        //�X�V�҃R�[�h 
                        l_mapValues.put("last_updater", l_fransactionParams[j].getLastUpdater());
                        //�X�V���t 
                        l_mapValues.put("last_updated_timestamp", l_fransactionParams[j].getLastUpdatedTimestamp());
                        
                        l_dataManager.updateFinTransaction(l_fransactionParams[j], l_mapValues);
                        //1.4.2.5.15�ڋq���薾�ׁC�⏕�������X�V����B 
                        this.notifyGtl(l_fransactionParams[j]);

                        boolean l_blnIsDayTradeAdoption = false;
                        boolean l_blnIsDayTradeMarket = false;
                        WEB3GentradeMarket l_market =
                            (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_fransactionParams[j].getMarketId()));
                        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();
                        l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                        l_blnIsDayTradeMarket = l_market.isDayTradeMarket();

                        // ���t�i�g�����U�N�V�����i������薾�ׁj�s.��ݻ޸�������==�h�O�������h�j�̏ꍇ
                        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_finTransactionType))
                        {
                            // is�l�b�e�B���O����==true || (is�l�b�e�B���O����==false ����
                            // is���v�����̗p()==true ���@@is���v��s��()==true)�̏ꍇ
                            if (l_blnIsNetting || (l_blnIsNetting == false
                                && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
                            {
                                //�ۗL���Y�X�V�i�뉿�X�V�p�j(long, double, double)
                                this.assetUpdateNettingExchangeRateAdoption(
                                    l_fransactionParams[j].getAssetId(),
                                    l_dblNetAmountBeforeUpdate,
                                    l_fransactionParams[j].getNetAmount());
                            }
                        }
                    }

                    //1.4.2.6toArray()�̖߂�l.�����P��ID�̂����A���j�[�N�Ȓ����P��ID�S�Ăɂ���Loop����
                    for (int k = 0; k < listObj.size(); k++)
                    {
                        FeqFinTransactionRow l_transactionRow = (FeqFinTransactionRow) listObj.get(k);
                        //1.4.2.6.1�萔���ꊇ�Čv�Z�̌��ʂ𒍕��P�ʁA���������ɍX�V����B 
                        WEB3FeqOrderUnit l_orderUnit = (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_transactionRow.getOrderUnitId());
                        l_datBaseDate = l_transactionRow.getFinTransactionTimestamp();
                        l_orderManager.updateEstimatedPrice(l_orderUnit, l_datBaseDate);
                    }
                    //1.4.2.7ArrayList�̗v�f���N���A����B
                    listObj.clear();
                }
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

    }
    
    /**
     * (adjust�ۗL���Y�i����j) <BR>
     * ������v�Z��̌덷���A�ۗL���Y.�뉿 <BR>
     * �i�뉿�P���v�Z�p�j�ɕ␳����B <BR>
     *  <BR>
     * �P�j�@@���Y�h�c�ɊY������ۗL���Y�s�I�u�W�F�N�g���擾����B  <BR>
     *  <BR>
     * �Q�j�@@�ۗL���Y�s.�뉿�i�뉿�P���v�Z�p�j �Ɉȉ��̌v�Z���ʂ��Z�b�g����B  <BR>
     *  <BR>
     * �@@�뉿�i�뉿�P���v�Z�p�j�F�@@�i�����l�j�|���O���v�{���㍇�v  <BR>
     *  <BR>
     * �R�j�@@�g���f�[�^�}�l�[�W���[.updateAssetByTrans()���R�[�����A <BR>
     * �ۗL���Y�e�[�u���ɕۑ�����B  <BR>
     *  <BR>
     * �@@[updateAssetByTrans()�Ɏw�肷�����] <BR>
     * �@@�ۗL���Y�s�F�@@�ۗL���Y�s <BR>
     * @@param l_lngAssetId - (�ۗL���Y�h�c) <BR>
     * �ۗL���Y�h�c
     * @@param l_dblFactorBeforeTotal - (���O���v) <BR>
     * ���O���v
     * @@param l_dblFactorAfterCount - (���㍇�v) <BR>
     * ���㍇�v
     * @@throws WEB3BaseException 
     * @@roseuid 428ACD0B01D6
     */
    protected void adjustAsset(long l_lngAssetId, double l_dblFactorBeforeTotal, double l_dblFactorAfterCount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "adjustAsset(long,double,double)";    
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqPositionManager l_positionManager = (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
            //�P�j�@@���Y�h�c�ɊY������ۗL���Y�s�I�u�W�F�N�g���擾����B            
            FeqAssetImpl l_feqAssetImpl = (FeqAssetImpl)l_positionManager.getAsset(l_lngAssetId);
            
            if (l_feqAssetImpl == null)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B: ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�Q�j�@@�ۗL���Y�s.�뉿�i�뉿�P���v�Z�p�j �Ɉȉ��̌v�Z���ʂ��Z�b�g����B
            AssetRow l_assetRow = (AssetRow)l_feqAssetImpl.getDataSourceObject();
            
            double l_dblBookValue = 
                l_assetRow.getBookValue() - l_dblFactorBeforeTotal + l_dblFactorAfterCount;
            
            AssetParams l_assetParams = new AssetParams(l_assetRow);
            
            l_assetParams.setBookValue(l_dblBookValue);
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
                (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
            
            //�R�j�@@�g���f�[�^�}�l�[�W���[.updateAssetByTrans()���R�[�����A�ۗL���Y�e�[�u���ɕۑ�����B
            l_dataManager.updateAssetByTrans(l_assetParams);
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
            
        }   
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
    }
    
    /**
     * (reverseAssetUnitPositionByTrans) <BR>
     * �i���g�p�j <BR>
     * ����������return���� <BR>
     *  <BR>
     * ���뉿�P���Z�o�p�e�[�u���iASSET_UNIT�F�擾�P�ʕۗL���Y�j <BR>
     * �X�V�v���Z�X <BR>
     * @@param l_feqFinTransactionParams
     * @@param l_sideEnum
     * @@param l_assetParams
     * @@throws DataException
     * @@roseuid 4292D36802F2
     */
    protected void reverseAssetUnitPositionByTrans(FeqFinTransactionParams l_feqFinTransactionParams, SideEnum l_sideEnum, AssetParams l_assetParams) throws DataException
    {
     
    }
    
    /**
     * (reverse�ۗL���Y�c��) <BR>
     * �ireverseAssetParamsByMarketTradedTrans�̃I�[�o�[���C�h�j <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̖�������e���A <BR>
     * �ۗL���Y�s�ɒl���Z�b�g����B <BR>
     *  <BR>
     * �Z�b�g������e�́A�ȉ����Q�ƁB <BR>
     *  <BR>
     * (*) ���̏ꍇ�igetFinTransactionType() == �O�����j <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V\\13.�Ǘ��ҁE�o������� <BR>
     * �@@�u�O���o�����_�ۗL���Y�e�[�u���d�l.xls�� <BR>
     *   �O���o�����_�ۗL���Y DB�X�V�i�A��Update�j�v <BR>
     * @@param l_assetParams - (�ۗL���Y�s) <BR>
     * �ۗL���Y�s�I�u�W�F�N�g
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@throws DataException
     * @@roseuid 4292D41000FE
     */
    protected void reverseAssetParamsByMarketTradedTrans(
        AssetParams l_assetParams, 
        FeqFinTransactionParams l_feqFinTransactionParams) throws DataException
    {
        final String STR_METHOD_NAME = "reverseAssetParamsByMarketTradedTrans(AssetParams, FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        
        if (l_feqFinTransactionParams == null || l_assetParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //�g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̖�������e���A�ۗL���Y�s�ɒl���Z�b�g����B
        //(*) ���̏ꍇ�igetFinTransactionType() == �O�����j
        if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_feqFinTransactionParams.getFinTransactionType()))
        {
            //�i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��萔��
            l_assetParams.setQuantity(l_assetParams.getQuantity() + l_feqFinTransactionParams.getQuantity());
            //���ݓ���
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        }
        //�����t�@@�����v��̗p��Ё@@�����v�����s��̏ꍇ�iupdate�j
        else if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionParams.getFinTransactionType()))
        {
            //�i�����l�j- �g�����U�N�V�����i������薾�ׁj�s.��萔��
            l_assetParams.setQuantity(l_assetParams.getQuantity() - l_feqFinTransactionParams.getQuantity());
            //�i�����l�j- �g�����U�N�V�����i������薾�ׁj�s.��萔��
            l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() - l_feqFinTransactionParams.getQuantity());
            //�뉿�i�뉿�P���v�Z�p�j
            //�i�ŋ敪 == "���"�j &&
            //�@@�i�����l.���ʁi�뉿�P���v�Z�p�j > 0�j &&
            //�@@�i�����l.�뉿�i�뉿�P���v�Z�p�j == 0�j �̏ꍇ�A�i�����l�j�B
            //�ȊO�A�i�����l�j �| ������Ώۂ̃g�����U�N�V�����i������薾�ׁj�s.��n����~�i-1�j�B
            //�i����ʌ����ł����Ă��A�뉿���ݒ肳��Ă���ꍇ�͍X�V�ΏۂƂ���j
            if (!(TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
                && l_assetParams.getQuantityForBookValue() > 0
                && GtlUtils.Double.isZero(l_assetParams.getBookValue())))
            {
                //�i�����l�j- �g�����U�N�V�����i������薾�ׁj�s.��n����~�i-1�j
                l_assetParams.setBookValue(l_assetParams.getBookValue() - l_feqFinTransactionParams.getNetAmount() * -1);
            }
            //���ݓ���
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        }
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (applyTo�ۗL���Y�c��Deligate) <BR>
     * �ۗL���Y���X�V����B <BR>
     *  <BR>
     * this.applyTo�ۗL���Y�c��()�ɈϏ��ideligate�j����B <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@return List
     * @@throws DataException
     * @@roseuid 42B7B1EF001F
     */
    public List applyToAssetBalanceDeligate(FeqFinTransactionParams l_feqFinTransactionParams) 
        throws DataException
    {
        //this.applyTo�ۗL���Y�c��()�ɈϏ��ideligate�j����B
        final String STR_METHOD_NAME = "applyToAssetBalanceDeligate(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        
        List l_lisAssetPosition = this.applyToAssetPosition(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAssetPosition;
    }
    
    /**
     * (notify�ڋq����Deligate) <BR>
     * �ڋq���薾�ׁC�⏕�������X�V����B <BR>
     *  <BR>
     * this.notify�ڋq����()�ɈϏ��ideligate�j����B <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@roseuid 42B7B2F9007D
     */
    public void notifyCashDeligate(FeqFinTransactionParams l_feqFinTransactionParams)
    {
        //this.notify�ڋq����()�ɈϏ��ideligate�j����B
        final String STR_METHOD_NAME = "notifyCashDeligate(FeqFinTransactionParams)";    
        log.entering(STR_METHOD_NAME);
        
        this.notifyGtl(l_feqFinTransactionParams);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�ۗL���Y) <BR>
     * �ۗL���Y���X�V����B <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�O���c���jupdate�ۗL���Y�v�Q�ƁB <BR>
     * @@param l_feqOrderUnit - (�O�����������P��) <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4288262A00C6
     */
    protected void updateAsset(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAsset(WEB3FeqOrderUnit)";    
        log.entering(STR_METHOD_NAME);
        
        //1.1is���t()
        boolean l_isBuy = l_feqOrderUnit.isBuy();
        
        //1.2(*)���t����(is���t() == false)�̏ꍇ
        if (!l_isBuy)
        {
            //1.2.1(*)return
            return;
        }
        
        //1.3isUnexecuted()
        boolean l_isUnexecuted = l_feqOrderUnit.isUnexecuted();
        
        //1.4.(*)�����(isUnexecuted() == true)�̏ꍇ
        if (l_isUnexecuted)
        {
            //1.4.1(*)return
            return;            
        }
        
        //1.5get�g�����U�N�V����(�����P�� : �O�����������P��)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(
            ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqFinTransactionManager l_finTransactionManager = 
            (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
        
        List l_lisTransaction = 
            l_finTransactionManager.getTransactions(l_feqOrderUnit);
        
        log.debug("get�g�����U�N�V����()�̖߂�l�̗v�f�� = " + l_lisTransaction.size());
        
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        
        FeqFinTransactionParams l_feqFinTransactionParams = null;
        AssetParams l_assetParams = null;
        try
        {
            //1.6(*)get�g�����U�N�V����()�̖߂�l�̗v�f���ALoop����
            for(int i = 0; i < l_lisTransaction.size(); i++)
            {
                //1.6.1get�ۗL���Y(�g�����U�N�V�����i������薾�ׁj�s : FeqFinTransactionParams)
                l_feqFinTransactionParams = (FeqFinTransactionParams)l_lisTransaction.get(i);
                l_assetParams = l_dataManager.getAsset(l_feqFinTransactionParams);
                
                //1.6.2(*)�c��������(get�ۗL���Y()�̖߂�l == null�̏ꍇ
                if (l_assetParams == null)
                {
                    //1.6.2.1AssetPrams()
                    l_assetParams = new AssetParams();
                    
                    //1.6.2.2(*)�v���p�e�B�Z�b�g
                    //�y��Trade�z�⑫����.DB�X�V\\16.(��)�o���I�� 
                    //�u�O���o���I��_�ۗL���Y�e�[�u���d�l.xls#�O���o���I��_�ۗL���Y DB�X�V�i�@@��Insert�j�v 
                    //�Q�ƁB
                    //�g�����U�N�V�����i������薾�ׁj�s.�����h�c
                    l_assetParams.setAccountId(l_feqFinTransactionParams.getAccountId());
                    
                    //�g�����U�N�V�����i������薾�ׁj�s.�⏕�����h�c
                    l_assetParams.setSubAccountId(l_feqFinTransactionParams.getSubAccountId());
                    
                    //�g�����U�N�V�����i������薾�ׁj�s.�����^�C�v
                    l_assetParams.setProductType(l_feqFinTransactionParams.getProductType());
                    
                    //�g�����U�N�V�����i������薾�ׁj�s.��萔��
                    l_assetParams.setQuantity(l_feqFinTransactionParams.getQuantity());
                    
                    //���t�s�\���� = 0
                    l_assetParams.setQuantityCannotSell(0.0D);
                    
                    //�g�����U�N�V�����i������薾�ׁj�s.��萔��
                    l_assetParams.setQuantityForBookValue(l_feqFinTransactionParams.getQuantity());
                    
                    //�g�����U�N�V�����i������薾�ׁj�s.��n����~�i-1�j
                    l_assetParams.setBookValue((l_feqFinTransactionParams.getNetAmount() * -1));
                    
                    //���͕뉿�P�� = null
                    l_assetParams.setInputBookValue(null);
                    
                    //�뉿�P�����͓��� = null
                    l_assetParams.setInputTimestamp(null);
                    
                    //���`������ = 0
                    l_assetParams.setSetupFee(0.0D);
                    
                    //���`����������� = 0
                    l_assetParams.setSetupFeeTax(0.0D);
                    
                    //�����Ǘ��� = 0
                    l_assetParams.setManagementFee(0.0D);
                    
                    //�����Ǘ������� = 0
                    l_assetParams.setManagementFeeTax(0.0D);
                    
                    //�g�����U�N�V�����i������薾�ׁj�s.�����h�c
                    l_assetParams.setProductId(l_feqFinTransactionParams.getProductId());
                    
                    //�g�����U�N�V�����i������薾�ׁj�s.�ŋ敪
                    l_assetParams.setTaxType(l_feqFinTransactionParams.getTaxType());
                    
                    //0�FDEFAULT�i�~�j���łȂ��j
                    l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
                    
                    //���z�� = 0
                    l_assetParams.setProfitDistribution(0.0D);
                    
                    //30�����o�ߎc������ = 0
                    l_assetParams.setCountBeforePenalty(0.0D);
                    
                    //�쐬���t = ���ݓ���
                    l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //�X�V���t = ���ݓ���
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //1.6.2.3saveNewAsset(arg0 : AssetPrams)
                    l_dataManager.saveNewAsset(l_assetParams);
                }
                //1.6.3(*)�ۗL���Y�����ɑ��݂���ꍇ(get�ۗL���Y != null)�̏ꍇ
                else
                {
                    //1.6.3.1(*)�v���p�e�B�Z�b�g
                    //�y��Trade�z�⑫����.DB�X�V\\16.(��)�o���I�� 
                    //�u�O���o���I��_�ۗL���Y�e�[�u���d�l.xls#�O���o���I��_�ۗL���Y DB�X�V�i�A��Update�j�v 
                    //�Q�ƁB
                    //�i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��萔��
                    l_assetParams.setQuantity(l_assetParams.getQuantity() +
                        l_feqFinTransactionParams.getQuantity());
                    
                    //�i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��萔��
                    l_assetParams.setQuantityForBookValue(l_assetParams.getQuantityForBookValue() +
                        l_feqFinTransactionParams.getQuantity());
                    
                    //�ŋ敪 == "���"�����ʁi�뉿�P���v�Z�p�j> 0���뉿�i�뉿�P���v�Z�p�j== 0�̏ꍇ�͉������Ȃ�
                    if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType()) &&
                        l_assetParams.getQuantityForBookValue() > 0 &&
                        l_assetParams.getBookValue() == 0)
                    {
                        ;
                    }
                    //�Y�����Ȃ��ꍇ�͍X�V
                    else
                    {
                        //�i�����l�j�{�g�����U�N�V�����i������薾�ׁj�s.��n����~�i-1�j
                        l_assetParams.setBookValue(l_assetParams.getBookValue() +
                            l_feqFinTransactionParams.getNetAmount() * -1);
                    }
                    
                    //�X�V���t = ���ݓ���
                    l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    //1.6.3.2updateAssetByTrans(arg0 : AssetPrams)
                    l_dataManager.updateAssetByTrans(l_assetParams);
                }
                
                //1.6.4getAssetId()
                long l_assetId = l_assetParams.getAssetId();
                
                //1.6.5updateFinTransaction(arg0 : FeqFinTransaction arg1 : Map)
                //�y��Trade�z�⑫����.DB�X�V\\16.(��)�o���I�� 
                //�u�O���o���I��_�g�����U�N�V�����i������薾�ׁj�d�l.xls#
                //�O���o���I��_������薾�� DB�X�V�i�ۗL���Y�쐬��j�v�Q�ƁB
                Map l_mapValues = new HashMap();
                
                //�ۗL���YParams.���YID
                l_mapValues.put("asset_id", new Long(l_assetId));
                
                //�X�V���t = ���ݓ���
                l_mapValues.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                          
                l_dataManager.updateFinTransaction(l_feqFinTransactionParams, l_mapValues);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
                
        } 
        catch (DataNetworkException l_ex)        
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (�O�������X�V�f�[�^�}�l�[�W��) <BR>
     * �O�������X�V�f�[�^�}�l�[�W��
     */
    public class WEB3FeqUpdateDataManager extends FeqPositionManagerHelper.PersistentDataManager 
    {
        
        /**
         * @@roseuid 42D0DD5F02FD
         */
        public WEB3FeqUpdateDataManager() 
        {
         
        }
        
        /**
         * (get�ۗL���Y) <BR>
         * �igetAsset()�̃I�[�o�[���C�h�j <BR>
         * �ۗL���Y�s�I�u�W�F�N�g���擾����B <BR>
         *  <BR>
         * �ȉ��̏����ŕۗL���Y�e�[�u�����������A <BR>
         * �Y���s�I�u�W�F�N�g��ԋp����B <BR>
         *  <BR>
         * �@@[����] <BR>
         * �@@�ۗL���Y.�����h�c = �g�����U�N�V�����i������薾�ׁj�s.�����h�c And <BR>
         * �@@�ۗL���Y.�⏕�����h�c =  <BR>
         * �@@�@@�@@�@@�g�����U�N�V�����i������薾�ׁj�s.�⏕�����h�c And <BR>
         * �@@�ۗL���Y.�����h�c = �g�����U�N�V�����i������薾�ׁj�s.�����h�c And <BR>
         * �@@�ۗL���Y.�ŋ敪 = �g�����U�N�V�����i������薾�ׁj�s.�ŋ敪 <BR>
         *  <BR>
         * �Y���s���Ȃ��ꍇ�́Anull��ԋp����B <BR>
         * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s)<BR>
         * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
         * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams
         * @@throws DataQueryException
         * @@throws DataNetworkException
         * @@roseuid 428854D600B7
         */
        public AssetParams getAsset(FeqFinTransactionParams l_feqFinTransactionParams)throws DataQueryException, DataNetworkException 
        {
            final String STR_METHOD_NAME = "getAsset(FeqFinTransactionParams)";    
            log.entering(STR_METHOD_NAME);
            if (l_feqFinTransactionParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            List l_lisReturnAsset = null;
            
            //�f�[�^���m
            QueryProcessor l_processor = null;
            l_processor = Processors.getDefaultProcessor();
            
            //�P�j�@@�g�����U�N�V�����e�[�u������
            String l_strWhere = " account_id = ? and sub_account_id = ? and product_id = ? and tax_type = ? ";
            Object[] l_strBindValue = new Object[4];
            l_strBindValue[0] = new Long(l_feqFinTransactionParams.getAccountId());
            l_strBindValue[1] = new Long(l_feqFinTransactionParams.getSubAccountId());
            l_strBindValue[2] = new Long(l_feqFinTransactionParams.getProductId());
            l_strBindValue[3] = l_feqFinTransactionParams.getTaxType();            
            l_lisReturnAsset = l_processor.doFindAllQuery(AssetRow.TYPE,
                l_strWhere, l_strBindValue);

            AssetParams l_assetParams = null;
            if (l_lisReturnAsset != null && !l_lisReturnAsset.isEmpty())
            {
                l_assetParams = (AssetParams)l_lisReturnAsset.get(0);
            }
            log.exiting(STR_METHOD_NAME);  
            return l_assetParams;
        }
        
        /**
         * (get������薾��ForOrderUnit) <BR>
         * �igetFinTransactionForOrderUnit�j <BR>
         * ���꒍���Ɋ֘A��������̎�����薾�ׂ����X�g�Ŏ擾����B  <BR>
         *  <BR>
         * �g�����U�N�V�����i������薾�ׁj�e�[�u�����ȉ��̏����ōs���������A <BR>
         * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̃��X�g���擾����B  <BR>
         * ���R�[�h��������Ȃ������ꍇ�́A�T�C�Y0��List��ԋp����B  <BR>
         *  <BR>
         * [��������]  <BR>
         * �g�����U�N�V�����i������薾�ׁj.�����P�ʂh�c =  <BR>
         * �@@�@@�@@�����̒����P�ʂh�c And <BR>
         * �g�����U�N�V����.�폜�t���O = BooleanEnum.FALSE And <BR>
         * @@param l_lngOrderUnitId - (�����P�ʂh�c)
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 428AAD4B0253
         */
        public List getFinTransactionForOrderUnit(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionForOrderUnit(long)";    
            log.entering(STR_METHOD_NAME);

            List l_lisReturnTransaction = null;
            
            //�f�[�^���m
            QueryProcessor l_processor = null;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                //�P�j�@@�g�����U�N�V�����e�[�u������
                String l_strWhere = " order_unit_id = ? and delete_flag = ? ";
                Object[] l_strBindValue = new Object[2];
                l_strBindValue[0] = new Long(l_lngOrderUnitId);
                l_strBindValue[1] = BooleanEnum.FALSE;
                            
                l_lisReturnTransaction = l_processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                    l_strWhere, l_strBindValue);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            }        
            log.exiting(STR_METHOD_NAME);  
            return l_lisReturnTransaction;
        }
        
        /**
         * (get������薾��ForOrderUnit�i�����́j) <BR>
         *  <BR>
         * �����Ɋ֘A��������̎�����薾�ׂŁA <BR>
         * �����͂̍s�����X�g�Ŏ擾����B  <BR>
         *  <BR>
         * �g�����U�N�V�����i������薾�ׁj�e�[�u�����ȉ��̏����ōs���������A <BR>
         * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̃��X�g���擾����B  <BR>
         * ���R�[�h��������Ȃ������ꍇ�́A�T�C�Y0��List��ԋp����B  <BR>
         *  <BR>
         * [��������]  <BR>
         * �g�����U�N�V�����i������薾�ׁj.�����P�ʂh�c = �����̒����P�ʂh�c And <BR>
         * �g�����U�N�V����.�폜�t���O = BooleanEnum.FALSE And <BR>
         * �g�����U�N�V����.���o�H�敪 == ���o�H�敪.�h9�F�����́h <BR>
         * @@param l_lngOrderUnitId - (�����P�ʂh�c) <BR>
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 42B28CE303DC
         */
        public List getFinTransactionForOrderUnitExecInput(long l_lngOrderUnitId) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionForOrderUnitExecInput(long)";    
            log.entering(STR_METHOD_NAME);

            List l_lisReturnTransaction = null;
            
            //�f�[�^���m
            QueryProcessor l_processor = null;
            try
            {
                l_processor = Processors.getDefaultProcessor();
                //�P�j�@@�g�����U�N�V�����e�[�u������
                String l_strWhere = " order_unit_id = ? and delete_flag = ? and order_exec_route_div = ? ";
                Object[] l_strBindValue = new Object[3];
                l_strBindValue[0] = new Long(l_lngOrderUnitId);
                l_strBindValue[1] = BooleanEnum.FALSE;
                l_strBindValue[2] = WEB3FeqOrderExecRouteDivDef.ORDER_AND_EXEC_INPUT;
                            
                l_lisReturnTransaction = l_processor.doFindAllQuery(FeqFinTransactionRow.TYPE,
                    l_strWhere, l_strBindValue);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            }        
            log.exiting(STR_METHOD_NAME);  
            return l_lisReturnTransaction;
        }
        
        /**
         * (get������薾��For�ꊇ�Ώ�) <BR>
         *  <BR>
         * �萔���ꊇ�ΏۂƂȂ�����̎�����薾�ׂ� <BR>
         * ���X�g�Ŏ擾����B <BR>
         *  <BR>
         * �P�j�@@�g�����U�N�V�����i������薾�ׁj�e�[�u�����ȉ� <BR>
         *       �̏����ōs���������A <BR>
         * �@@�g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̃��X�g���擾����B  <BR>
         * �@@���R�[�h��������Ȃ������ꍇ�́A�T�C�Y0��List��ԋp����B  <BR>
         *  <BR>
         * [��������]  <BR>
         * �����P�ʂh�c in (�����̒����P��ID�ꗗ) And <BR>
         * �폜�t���O = BooleanEnum.FALSE <BR>
         *  <BR>
         * ���������ʂ́A�ȉ��̍��ڂŏ����\�[�g���A�擾���邱�ƁB <BR>
         * �@@�@@����ID <BR>
         * �@@�A������ <BR>
         * �@@�B�g�����U�N�V�����^�C�v <BR>
         * �@@�C�ŋ敪 <BR>
         * �@@�D���ϋ敪 <BR>
         * @@param l_lngOrderUnitIds - (�����P��ID�ꗗ) <BR>
         * �����P��ID�̈ꗗ
         * @@return List
         * @@throws WEB3BaseException
         * @@roseuid 42B673C60034
         */
        public List getFinTransactionForLumpObj(long[] l_lngOrderUnitIds) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getFinTransactionForLumpObj(long)";    
            log.entering(STR_METHOD_NAME);
            
            if (l_lngOrderUnitIds == null || l_lngOrderUnitIds.length == 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            List l_lisReturnTransaction = null;
            
            //�f�[�^���m
            QueryProcessor l_processor = null;
            try
            {
                l_processor = Processors.getDefaultProcessor();

                //��̕�����𐶐�����B<BR>
                StringBuffer l_sbQueryString = new StringBuffer();
                //�P�j�@@�g�����U�N�V�����e�[�u������                
                String l_strSubCond = null;

                for (int i = 0; i < l_lngOrderUnitIds.length; i++)
                {
                    if (l_strSubCond == null)
                    {
                        l_strSubCond = "?";
                    }
                    else
                    {
                        l_strSubCond += ", ?";
                    }
                }
            
                l_sbQueryString.append("order_unit_id in(" + l_strSubCond + ")");
                
                l_sbQueryString.append("and delete_flag = ? ");
                List l_lisQueryContainer = new ArrayList();

                for (int i = 0; i < l_lngOrderUnitIds.length; i++)
                {
                    l_lisQueryContainer.add(new Long(l_lngOrderUnitIds[i]));
                }
               
                l_lisQueryContainer.add(BooleanEnum.FALSE);
                Object[] l_queryContainer = new Object[l_lisQueryContainer.size()];
                l_lisQueryContainer.toArray(l_queryContainer);
                String l_strOrderBy = "product_id,biz_date,fin_transaction_type,settle_div";
                
                //�������ʂ́A�ȉ��̍��ڂŏ����\�[�g���A�擾���邱�ƁB
                l_lisReturnTransaction = 
                    l_processor.doFindAllQuery(
                        FeqFinTransactionRow.TYPE,
                        l_sbQueryString.toString(), 
                        l_strOrderBy,  
                        null, 
                        l_queryContainer);

            } 
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                
            }        
            log.exiting(STR_METHOD_NAME);  
            return l_lisReturnTransaction;
        }
        
        /**
         * �A�C�e���̒�`<BR>
         * �X�V���ڂ�Map�ɃZ�b�g����B <BR>
         * �isetUpdateAssetAttributes�̃I�[�o�[���C�h���\�b�h�j <BR>
         * <BR>
         * ����.�ۗL���Y�s�̈ȉ��̍��ڂ�Map�ɒǉ�����B <BR>
         * <BR>
         * ����  �L�[�F"quantity", �l�F����.�ۗL���Y�s.���� <BR>
         * ���ʁi�뉿�P���v�Z�p�j  �L�[�F"quantity_for_book_value", �l�F����.�ۗL���Y�s.���ʁi�뉿�P���v�Z�p�j <BR>
         * �뉿�i�뉿�P���v�Z�p�j  �L�[�F"book_value", �l�F����.�ۗL���Y�s.�뉿�i�뉿�P���v�Z�p�j <BR>
         * ���`������  �L�[�F"setup_fee", �l�F����.�ۗL���Y�s.���`������ <BR>
         * ���`�����������  �L�[�F"setup_fee_tax", �l�F����.�ۗL���Y�s.���`����������� <BR>
         * �����Ǘ���  �L�[�F"management_fee", �l�F����.�ۗL���Y�s.�����Ǘ��� <BR>
         * �����Ǘ�������  �L�[�F"management_fee_tax", �l�F����.�ۗL���Y�s.�����Ǘ������� <BR>
         * �X�V���t  �L�[�F"last_updated_timestamp", �l�F����.�ۗL���Y�s.�X�V���t <BR>
         *  @@param l_asserParams - (�ۗL���Y) <BR>
         */
        public void setUpdateAssetAttributes (AssetParams l_asserParams, Map l_mapValue)
        {
            final String STR_METHOD_NAME = "setUpdateAssetAttributes (AssetParams l_assetParamse, Map l_mapValue)";    
            log.entering(STR_METHOD_NAME);
            
            if (l_asserParams == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }                        
            
            //����  �L�[�F"quantity", �l�F����.�ۗL���Y�s.����
            l_mapValue.put("quantity", new BigDecimal(l_asserParams.getQuantity()));
            
            //���ʁi�뉿�P���v�Z�p�j  �L�[�F"quantity_for_book_value", �l�F����.�ۗL���Y�s.���ʁi�뉿�P���v�Z�p�j                
            l_mapValue.put("quantity_for_book_value", new BigDecimal(l_asserParams.getQuantityForBookValue()));
            
            //�뉿�i�뉿�P���v�Z�p�j  �L�[�F"book_value", �l�F����.�ۗL���Y�s.�뉿�i�뉿�P���v�Z�p�j
            l_mapValue.put("book_value", new BigDecimal(l_asserParams.getBookValue()));
            
            //���`������  �L�[�F"setup_fee", �l�F����.�ۗL���Y�s.���`������
            l_mapValue.put("setup_fee", new BigDecimal(l_asserParams.getSetupFee()));
            
            //���`�����������  �L�[�F"setup_fee_tax", �l�F����.�ۗL���Y�s.���`����������� 
            l_mapValue.put("setup_fee_tax", new BigDecimal(l_asserParams.getSetupFeeTax()));
            
            //�����Ǘ���  �L�[�F"management_fee", �l�F����.�ۗL���Y�s.�����Ǘ���
            l_mapValue.put("management_fee", new BigDecimal(l_asserParams.getManagementFee()));
            
            //�����Ǘ�������  �L�[�F"management_fee_tax", �l�F����.�ۗL���Y�s.�����Ǘ�������
            l_mapValue.put("management_fee_tax", new BigDecimal(l_asserParams.getManagementFeeTax()));
            
            //�X�V���t  �L�[�F"last_updated_timestamp", �l�F����.�ۗL���Y�s.�X�V���t
            l_mapValue.put("last_updated_timestamp", l_asserParams.getLastUpdatedTimestamp());
            
        }
    }
    
    /**
     * (reverse�ۗL���YByTrans) <BR>
     * �ireverseAssetPositionByTrans�̃I�[�o�[���C�h�j<BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̖�������e���A�ۗL���Y�s�ɒl���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@������Ώۂۗ̕L���Y���擾����B<BR>
     * �@@getPersistenceManager().getAsset(trans.getAssetId())��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.side == SideEnum.BUY ���@@<BR>
     * �i�،����.is���v�����̗p()��false�@@or�@@�s��.is���v��s��()��false�j�̏ꍇ <BR>
     * �@@�@@�|�������Ȃ��B <BR>
     * �@@�@@�����t�@@���i���v�薢�̗p��Ё@@or�@@<BR>
     * ���v�����s��łȂ��j�̏ꍇ�A�ۗL���Y�͍쐬����Ȃ��B <BR>
     * <BR>
     * �R�j�@@�p�����[�^.side == SideEnum.SELL�@@���� <BR>
     * (�p�����[�^.side == SideEnum.BUY�@@���� is���v�����̗p()��true�@@<BR>
     * ���@@is���v��s��()�@@���@@true)�̏ꍇ <BR>
     * �@@�R�|�P�j�@@�P�j�̖߂�l == null�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@������̓��e��ۗL���Y�ɃZ�b�g����B<BR>
     * �@@�@@reverse�ۗL���Y�c��(�P�j�̖߂�l, �p�����[�^.trans)��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�ۗL���Y���X�V����B<BR>
     * �@@�@@getPersistenceManager().updateAssetByTrans(�P�j�̖߂�l)��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �S�j�@@�ۗL���Y�P�ʂ��X�V����B<BR>
     * �@@reverseAssetUnitPositionByTrans(<BR>
     * �@@�@@�p�����[�^.trans, �p�����[�^.side, �P�j�̖߂�l)��<BR>
     * �@@�R�[������B<BR>
     * �@@����L���\�b�h�͋�������Ă���ׁA�������Ȃ��B<BR>
     * <BR>
     * @@param trans - (�g�����U�N�V�����i������薾�ׁj�s) <BR>
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@param side - (SideEnum) <BR>
     * SideEnum
     * @@throws DataException, RuntimeSystemException
     */
    protected void reverseAssetPositionByTrans(FeqFinTransactionParams trans, SideEnum side)
        throws DataException, RuntimeSystemException
    {
        final String STR_METHOD_NAME =  "reverseAssetPositionByTrans(FeqFinTransactionParams, SideEnum)";
        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMarket l_market = null;

        WEB3GentradeMainAccount l_mainAccount = null;
        boolean l_blnIsDayTradeAdoption = false;
        boolean l_blnIsDayTradeMarket = false;
        try
        {
            l_market = (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(trans.getMarketId()));
            l_mainAccount =
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(trans.getAccountId());
            WEB3GentradeInstitution l_institution = 
                (WEB3GentradeInstitution)l_mainAccount.getInstitution();

            try
            {
                l_blnIsDayTradeAdoption = l_institution.isDayTradeAdoption();
                l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        AssetParams asset = getPersistenceManager().getAsset(trans.getAssetId());
        //�p�����[�^.side == SideEnum.BUY ���@@
        //�i�،����.is���v�����̗p()��false�@@or�@@�s��.is���v��s��()��false�j�̏ꍇ
        
        if (side == SideEnum.BUY  && (!l_blnIsDayTradeAdoption || !l_blnIsDayTradeMarket))
        {
//            if(asset == null)
//            {
//                String msg = "reverseAssetPositionByTrans: acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), transType(" + trans.getFinTransactionType() + "), product(" + trans.getProductId() + "), price(" + trans.getPrice() + "), quantity(" + trans.getQuantity() + ")";
//                log.error(msg);
//                throw new RuntimeSystemException(msg);
//            }
//            if(asset.getQuantity() < trans.getQuantity())
//            {
//                String msg = "reverseAssetPositionByTrans: Too little Asset for acct(" + trans.getAccountId() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + "), asset(" + asset.getQuantity() + ") < trans(" + trans.getQuantity() + ")";
//                log.error(msg);
//                throw new RuntimeSystemException(msg);
//            }
//            reverseAssetParamsByMarketTradedTrans(asset, trans);
//            getPersistenceManager().updateAssetByTrans(asset);
        } else
        //�R�j�@@�p�����[�^.side == SideEnum.SELL�@@���� 
        //(�p�����[�^.side == SideEnum.BUY�@@���� is���v�����̗p()��true�@@���@@is���v��s��()�@@���@@true)�̏ꍇ
        if(side == SideEnum.SELL || (side == SideEnum.BUY && l_blnIsDayTradeAdoption && l_blnIsDayTradeMarket))
        {
            if(asset == null)
            {
                String msg = "reverseAssetPositionByTrans: No Asset for acct(" + trans.getAccountId() + "), transType(" + trans.getFinTransactionType() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + ")";
                log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            reverseAssetParamsByMarketTradedTrans(asset, trans);
            getPersistenceManager().updateAssetByTrans(asset);
        }
        reverseAssetUnitPositionByTrans(trans, side, asset);
    }

    /**
     * (�ۗL���Y�X�V�i�뉿�X�V�p�j)<BR>
     * �m�����T�v�n <BR>
     * �P�j���YID���ۗL���YParam���擾����B <BR>
     * ���ۗL���Y�ɊY������f�[�^���Ȃ��A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag �@@:   BUSINESS_ERROR_00204<BR>
     * <BR>
     * �Q�j�ۗL���YParams.�ŋ敪��"��ʌ���"���� <BR>
     * �@@�@@�ۗL���YParam.���ʁi�뉿�P���v�Z�p�j��0 ���� <BR>
     * �@@�@@�ۗL���YParam.�뉿�i�뉿�P���v�Z�p�j��0�̏ꍇ�A <BR>
     * �@@�@@��������������return����B <BR>
     * <BR>
     * �R�j��L�ȊO�̏ꍇ <BR>
     * <BR>
     * �@@�R�|�P�j�ۗL���YParam�뉿�i�뉿�P���v�Z�p�j �̒l���X�V����B <BR>
     * �@@�@@�@@���X�V��뉿 = �X�V�O�뉿�|�i�X�V�O�j��n����~�i-1�j�{�i�X�V��j��n��� �~�i-1�j<BR>
     * <BR>
     * �@@�R�|�Q�j�X�V���ꂽ�ۗL���YParams��ۗL���Y�e�[�u���ɕۑ�����B <BR>
     * �@@�@@�@@���O�������X�V�f�[�^�}�l�[�W��.updateAssetByTrans()���R�[�� <BR>
     * �@@�R�|�R�j�X�V���ԂɌ��ݓ����iGtlUtils.getSystemTimestamp()�j���ݒ肳���B<BR>
     * @@param l_lngAssetID - (���YID)<BR>
     * @@param l_dblBeforeDeliveryPrice - (�X�V�O�̎�n���)<BR>
     * @@param l_dblAfterDeliveryPrice - (�X�V��̎�n���)<BR>
     * @@throws WEB3BaseException
     */
    public void assetUpdateNettingExchangeRateAdoption(
        long l_lngAssetID, double l_dblBeforeDeliveryPrice, double l_dblAfterDeliveryPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "assetUpdateNettingExchangeRateAdoption(long, double, double)";
        log.entering(STR_METHOD_NAME);

        AssetParams l_assetParams = null;
        try
        {
            l_assetParams = new AssetParams(AssetDao.findRowByPk(l_lngAssetID));
        }
        catch (DataFindException l_ex)
        {
            log.error("�ۗL���Y�Y���f�[�^�Ȃ��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)        
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �Q�j�ۗL���YParams.�ŋ敪��"��ʌ���"����
        //�ۗL���YParam.���ʁi�뉿�P���v�Z�p�j��0 ����
        //�ۗL���YParam.�뉿�i�뉿�P���v�Z�p�j��0�̏ꍇ
        if (TaxTypeEnum.NORMAL.equals(l_assetParams.getTaxType())
            && l_assetParams.getQuantityForBookValue() >0
            && GtlUtils.Double.isZero(l_assetParams.getBookValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //�X�V��뉿 = �X�V�O�뉿�|�i�X�V�O�j��n����~�i-1�j�{�i�X�V��j��n��� �~�i-1�j
            BigDecimal l_bdBeforeDeliveryPrice = new BigDecimal(Double.toString(l_dblBeforeDeliveryPrice * -1));
            BigDecimal l_bdAfterDeliveryPrice = new BigDecimal(Double.toString(l_dblAfterDeliveryPrice * -1));
            BigDecimal l_bdBeforeBookValue = new BigDecimal(Double.toString(l_assetParams.getBookValue()));
            BigDecimal l_bdAfterBookValue =
                l_bdBeforeBookValue.subtract(l_bdBeforeDeliveryPrice).add(l_bdAfterDeliveryPrice);
            l_assetParams.setBookValue(l_bdAfterBookValue.doubleValue());
            //���O�������X�V�f�[�^�}�l�[�W��.updateAssetByTrans()���R�[��
            WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager = 
                (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
            //�X�V���ԂɌ��ݓ����iGtlUtils.getSystemTimestamp()�j
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            try
            {
                l_dataManager.updateAssetByTrans(l_assetParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            } 
            catch (DataNetworkException l_ex)        
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }  
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update�g�����U�N�V�����i�l�b�e�B���O�̗p�j)<BR>
     * �l�b�e�B���O�̗p��ЂƂȂ�g�����U�N�V�����ɂ��āA <BR>
     * �l�b�e�B���O�בփ��[�g���g�p���A�g�����U�N�V�����f�[�^���X�V����B <BR>
     *      <BR>
     * �V�[�P���X�} <BR>
     * �u�i�O���c���jupdate�g�����U�N�V�����i�l�b�e�B���O�̗p�j�v�Q�ƁB<BR>
     * @@param l_lngOrderUnitIDs - (�����P�ʂh�c�ꗗ)<BR>
     * @@param l_datDate - (���)<BR>
     */
    public void updateTransactionNettingAdoption(long[] l_lngOrderUnitIDs, Date l_datDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "updateTransactionNettingAdoption(long[], Date)";
        log.entering(STR_METHOD_NAME);

        //get������薾��For�ꊇ�Ώ�(long[])
        WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager l_dataManager =
            (WEB3FeqPositionManagerHelper.WEB3FeqUpdateDataManager)this.getPersistenceManager();
        List l_lisFinTransactionForLumpObj = l_dataManager.getFinTransactionForLumpObj(l_lngOrderUnitIDs);

        if (l_lisFinTransactionForLumpObj == null || l_lisFinTransactionForLumpObj.size() <= 1)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //get�l�b�e�B���O�בփ��[�g(List)
        //[����]
        //�g�����U�N�V�����i������薾�ׁj�sList�F�@@get������薾��For�ꊇ�Ώ�()�̖߂�l
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqProductManager l_productManager = (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3FeqOrderManager l_orderManager = (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();

        WEB3FeqBizLogicProvider l_bizlogicProvider = (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        HashMap l_hmNettingExchangeRate = l_bizlogicProvider.getNettingExchangeRate(l_lisFinTransactionForLumpObj);

        //get�l�b�e�B���O�בփ��[�g(�j�̖߂�l�Anull���ԋp���ꂽ�ꍇ�́A�������I������B
        if (l_hmNettingExchangeRate == null || l_hmNettingExchangeRate.keySet().size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�����P�ʂh�c��ArrayList�𐶐�����
        ArrayList l_lisOrderUnitIDs = new ArrayList();

        //get������薾��For�ꊇ�Ώ�()�̖߂�l�̗v�f����Loop����
        Iterator l_iterator = l_lisFinTransactionForLumpObj.iterator();
        while (l_iterator.hasNext())
        {
            FeqFinTransactionRow l_feqFinTransactionRow = (FeqFinTransactionRow)l_iterator.next();
            String l_strCurrencyCode = l_feqFinTransactionRow.getCurrencyCode();
            double l_dblFxRate = 0;
            if (l_hmNettingExchangeRate.containsKey(l_strCurrencyCode) && !l_feqFinTransactionRow.getOrderUnitIdIsNull())
            {
                long l_lngOrderUnitId = l_feqFinTransactionRow.getOrderUnitId();
                if (!l_lisOrderUnitIDs.contains(new Long(l_lngOrderUnitId)))
                {
                    l_lisOrderUnitIDs.add(new Long(l_lngOrderUnitId));
                }
                FeqOrderExecutionRow l_feqOrderExecutionRow = null;
                FeqOrderExecutionParams l_feqOrderExecutionParams = null;
                FeqFinTransactionParams l_feqFinTransactionParams = null;
                try
                {
                    l_dblFxRate = ((Double)l_hmNettingExchangeRate.get(l_strCurrencyCode)).doubleValue();
                    //��ݻ޸��݁i������薾�ׁj�e�[�u���ɕR�Â��A���e�[�u����S�Ď擾����
                    l_feqOrderExecutionRow =
                        FeqOrderExecutionDao.findRowByPk(l_feqFinTransactionRow.getOrderExecutionId());
                    l_feqOrderExecutionParams = new FeqOrderExecutionParams(l_feqOrderExecutionRow);
                    l_feqOrderExecutionParams.setFxRate(l_dblFxRate);
                    l_feqOrderExecutionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                    l_queryProcesser.doUpdateQuery(l_feqOrderExecutionParams);

                    //�u�l�b�e�B���O�בփ��[�g�i�ʉ݃R�[�h�j�v����ݻ޸��݁i������薾�ׁj�s�̓K�p�בփ��[�g�ɐݒ肷��
                    l_feqFinTransactionParams = new FeqFinTransactionParams(l_feqFinTransactionRow);
                    l_feqFinTransactionParams.setFxRate(l_dblFxRate);
                }
                catch (DataFindException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                catch (DataNetworkException l_ex)        
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //calc�������(double, double)
                double l_dblExecutionAmount =
                    l_bizlogicProvider.calcExecutionAmount(l_feqFinTransactionRow.getQuantity(), l_feqFinTransactionRow.getPrice());

                WEB3GentradeSubAccount l_subAccount = null;
                WEB3FeqProduct l_product = null;
                WEB3FeqOrderUnit l_orderUnit = null;
                WEB3GentradeMarket l_market = null;
                boolean l_blnIsLimitPrice = false;
                boolean l_blnIsBuy = false;
                try
                {
                    l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_feqFinTransactionRow.getAccountId(),
                        l_feqFinTransactionRow.getSubAccountId());
                    l_product = (WEB3FeqProduct)l_productManager.getProduct(l_feqFinTransactionRow.getProductId());
 
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_feqFinTransactionRow.getMarketId());

                    l_orderUnit = (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                    l_blnIsBuy = true;
                }
                FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                if (GtlUtils.Double.isZero(l_orderUnit.getLimitPrice()))
                {
                    l_blnIsLimitPrice = true;
                }
                //calc�O���������z
                //�⏕�����F
                //�@@getSubAccountId()�ɊY������⏕����
                //�@@�i��AccountManager.getSubAccount()�ɂĎ擾�j
                //�O�����������F
                //�@@getProductId()�ɊY������O����������
                //�@@�i��FeqProductManager.getProduct()�ɂĎ擾�j
                //�s��F�@@getMarketId()�ɊY������s��
                //�@@�i��FinObjectManager.getMarket()�ɂĎ擾�j
                //����F�@@���
                //�����F�@@getFinTransactionTimestamp()
                //��������F�@@calc�������()
                //�בփ��[�g�F�@@�O�������v�Z�T�[�r�X.get�l�b�e�B���O�בփ��[�g()��
                //�߂�l.get(��ݻ޸��݁i������薾�ׁj�s�̒ʉ݃R�[�h)�̖߂�l
                //is���t�F
                //�@@getFinTransactionType() == �h�O�������h�̏ꍇtrue�B�ȊO�Afalse�B
                //is���v�Z�F�@@false
                //is�w�l�F
                //�@@�����Ώۂ̒����P��ID�ɊY�����钍���P�ʍs.�w�l == 0�̏ꍇfalse�B�ȊO�Atrue�B
                //�����`���l���F�@@�����Ώۂ̒����P��ID�ɊY�����钍���P�ʍs.���񒍕��̒����`���l��
                WEB3FeqAmountCalcResult l_feqAmountCalcResult =l_bizlogicProvider.calcFeqAmount(
                    l_subAccount,
                    l_product,
                    l_market,
                    l_datDate,
                    l_feqFinTransactionRow.getFinTransactionTimestamp(),
                    l_dblExecutionAmount,
                    l_dblFxRate,
                    l_blnIsBuy,
                    false,
                    l_blnIsLimitPrice,
                    l_feqOrderUnitRow.getOrderChanel());

                //�v���p�e�B�Z�b�g
                //�O���o���I��_������薾��DB�X�V�i�l�b�e�B���O�ב֍̗p�j
                HashMap l_hmFeqFinTransaction = new HashMap();

                //�����i��ݻ޸�������=�h�O�������h�j�̏ꍇ�A�ȉ��̌v�Z����*) ��n����~�i-1�j
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                    //��n���
                    l_feqFinTransactionParams.setNetAmount(l_feqAmountCalcResult.getNetAmount() * (-1));
                    l_hmFeqFinTransaction.put("net_amount", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmount())));
                    //��n����i�O�݁j
                    l_feqFinTransactionParams.setNetAmountFc(l_feqAmountCalcResult.getNetAmountFc() * (-1));
                    l_hmFeqFinTransaction.put("net_amount_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmountFc())));
                    //���n�v���z
                    l_feqFinTransactionParams.setCapitalGain(0);
                    l_hmFeqFinTransaction.put("capital_gain", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGain())));
                    //���n�v�Ŋz
                    l_feqFinTransactionParams.setCapitalGainTax(0);
                    l_hmFeqFinTransaction.put("capital_gain_tax", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTax())));
                    
                    //���n�v���z�i�O�݁j
                    l_feqFinTransactionParams.setCapitalGainFc(0);
                    l_hmFeqFinTransaction.put("capital_gain_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainFc())));
                    //���n�v�Ŋz�i�O�݁j
                    l_feqFinTransactionParams.setCapitalGainTaxFc(0);
                    l_hmFeqFinTransaction.put("capital_gain_tax_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTaxFc())));
                }
                //���̏ꍇ�i��ݻ޸�������=="�O������"�j�A���n���v�^���n�v�ł��v�Z����
                else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                        //calc���n���v(double, double, long, �⏕����, TaxTypeEnum)
                        //��������i�~�݁j�F�@@calc�O���������z().get��n���()
                        // �������F�@@��萔�ʁi���.getExecutedQuantity()�j
                        //�����h�c�F�@@getProductId()
                        //�⏕�����F�@@getSubAccountId()�ɊY������⏕����
                        // �ŋ敪�F�@@getTaxType()
                        double l_dblCapitalProfitLoss = l_bizlogicProvider.calcCapitalProfitLoss(
                            l_feqAmountCalcResult.getNetAmount(),
                            l_feqOrderExecutionParams.getExecQuantity(),
                            l_feqFinTransactionRow.getProductId(),
                            l_subAccount,
                            l_feqFinTransactionRow.getTaxType());
                        //calc���n�v��(�⏕����, TaxTypeEnum, double, Date)
                        //�⏕�����F�@@getSubAccountId()�ɊY������⏕����
                        //�ŋ敪�F�@@getTaxType()
                        //��������i�~�݁j�F�@@calc���n���v()
                        //����F�@@��n���igetDeliveryDate()�j
                        double l_dblCapitalGainTax = l_bizlogicProvider.calcCapitalGainTax(
                            l_subAccount,
                            l_feqFinTransactionRow.getTaxType(),
                            l_dblCapitalProfitLoss,
                            l_feqFinTransactionRow.getDeliveryDate());
                        //calc�O�݊��Z
                        //[calc�O�݊��Z()�Ɏw�肷�����]
                        //���z�i�~�݁j�F�@@calc���n���v()
                        //�בփ��[�g�F�@@getFxRate()
                        //����ID�F�@@getProductId()
                        //is���t�F�@@false
                        //is���v�Z�F�@@false
                        double l_dblForeignCCYAmountCapitalProfitLoss = l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblCapitalProfitLoss,
                            l_feqFinTransactionParams.getFxRate(),
                            l_feqFinTransactionParams.getProductId(),
                            false,
                            false);
                        //calc�O�݊��Z
                        //[calc�O�݊��Z()�Ɏw�肷�����]
                        //���z�i�~�݁j�F�@@calc���n�v��()
                        //�בփ��[�g�F�@@getFxRate()
                        //����ID�F�@@getProductId()
                        //is���t�F�@@false
                        //is���v�Z�F�@@false
                        double l_dblForeignCCYAmountCapitalGainTax = l_bizlogicProvider.calcForeignCCYAmount(
                            l_dblCapitalGainTax,
                            l_feqFinTransactionParams.getFxRate(),
                            l_feqFinTransactionParams.getProductId(),
                            false,
                            false);

                        //��n���
                        l_feqFinTransactionParams.setNetAmount(l_feqAmountCalcResult.getNetAmount());
                        l_hmFeqFinTransaction.put("net_amount", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmount())));
                        //��n����i�O�݁j
                        l_feqFinTransactionParams.setNetAmountFc(l_feqAmountCalcResult.getNetAmountFc());
                        l_hmFeqFinTransaction.put("net_amount_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getNetAmountFc())));
                        //���n�v���z
                        l_feqFinTransactionParams.setCapitalGain(l_dblCapitalProfitLoss);
                        l_hmFeqFinTransaction.put("capital_gain", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGain())));
                        //���n�v�Ŋz
                        l_feqFinTransactionParams.setCapitalGainTax(l_dblCapitalGainTax);
                        l_hmFeqFinTransaction.put("capital_gain_tax", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTax())));
                        
                        //���n�v���z�i�O�݁j
                        l_feqFinTransactionParams.setCapitalGainFc(l_dblForeignCCYAmountCapitalProfitLoss);
                        l_hmFeqFinTransaction.put("capital_gain_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainFc())));
                        //���n�v�Ŋz�i�O�݁j
                        l_feqFinTransactionParams.setCapitalGainTaxFc(l_dblForeignCCYAmountCapitalGainTax);
                        l_hmFeqFinTransaction.put("capital_gain_tax_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCapitalGainTaxFc())));
                }

                //�K�p�בփ��[�g
                l_hmFeqFinTransaction.put("fx_rate", new BigDecimal(Double.toString(l_feqFinTransactionParams.getFxRate())));
                //�ϑ��萔��
                l_feqFinTransactionParams.setCommissionFee(l_feqAmountCalcResult.getCommissionFee());
                l_hmFeqFinTransaction.put("commission_fee", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFee())));
                //�ϑ��萔�������
                l_feqFinTransactionParams.setCommissionFeeTax(l_feqAmountCalcResult.getCommisionFeeTax());
                l_hmFeqFinTransaction.put("commission_fee_tax", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFeeTax())));
                //���n���Z����i�~�݁j
                l_feqFinTransactionParams.setBalanceAmount(l_feqAmountCalcResult.getBalanceAmount());
                l_hmFeqFinTransaction.put("balance_amount", new BigDecimal(Double.toString(l_feqFinTransactionParams.getBalanceAmount())));
                //�ϑ��萔���i�O�݁j
                l_feqFinTransactionParams.setCommissionFeeFc(l_feqAmountCalcResult.getCommissionFeeFc());
                l_hmFeqFinTransaction.put("commission_fee_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFeeFc())));
                //�ϑ��萔������Łi�O�݁j
                l_feqFinTransactionParams.setCommissionFeeTaxFc(l_feqAmountCalcResult.getCommisionFeeTaxFc());
                l_hmFeqFinTransaction.put("commission_fee_tax_fc", new BigDecimal(Double.toString(l_feqFinTransactionParams.getCommissionFeeTaxFc())));
                //�X�V�҃R�[�h
                try
                {
                    OpLoginSecurityService l_opLoginSecurityService =
                        (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                    l_opLoginSecurityService.getLoginId();
                    WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
                    l_feqFinTransactionParams.setLastUpdater(l_administrator.getAdministratorCode());
                }
                catch (IllegalSessionStateException l_ex)
                {
                    l_feqFinTransactionParams.setLastUpdater(null);
                }
                l_hmFeqFinTransaction.put("last_updater", l_feqFinTransactionParams.getLastUpdater());
                //�X�V���t
                l_feqFinTransactionParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                l_hmFeqFinTransaction.put("last_updated_timestamp", l_feqFinTransactionParams.getLastUpdatedTimestamp());

                //updateFinTransaction
                try
                {
                    l_dataManager.updateFinTransaction(l_feqFinTransactionParams, l_hmFeqFinTransaction);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //notify�ڋq����(�g�����U�N�V�����i������薾�ׁj�s : FeqFinTransactionParams)
                this.notifyGtl(l_feqFinTransactionParams);

                //���t�i�g�����U�N�V�����i������薾�ׁj�s.��ݻ޸�������==�h�O�������h�j�̏ꍇ
                if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
                {
                    //�ۗL���Y�X�V�i�뉿�X�V�p�j(long, double, double)
                    //[�ۗL���Y�X�V�i�l�b�e�B���O�ב֍̗p�j�Ɏw�肷�����]
                    //���YID�F�@@getAssetId( )�̖߂�l
                    //�X�V�O�̎�n����F
                    //�@@�@@�@@�X�V�O�̃g�����U�N�V�����i������薾�ׁj�s.��n������擾����B
                    //�X�V��̎�n����F
                    //�@@�@@�@@�X�V��̃g�����U�N�V�����i������薾�ׁj�s.��n������擾����B
                    this.assetUpdateNettingExchangeRateAdoption(
                        l_feqFinTransactionRow.getAssetId(),
                        l_feqFinTransactionRow.getNetAmount(),
                        l_feqFinTransactionParams.getNetAmount());
                }
            }
        }

        //�i�����P��ID�j�̗v�f����Loop����
        int l_intSize = l_lisOrderUnitIDs.size();

        //ArrayList( )�̖߂�l�i�����P��ID�̈ꗗ�j
        long[] l_lngOrderUnitIdList = new long[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            long l_lngOrderUnitId = ((Long)l_lisOrderUnitIDs.get(i)).longValue();

            l_lngOrderUnitIdList[i] = l_lngOrderUnitId;

            //update�g�����U�N�V����(long, boolean)
            //�@@�����P�ʂh�c�F�@@ �v�f�����̒����P��ID
            //�@@is����F�@@false
            this.updateTransaction(l_lngOrderUnitId, false);

            WEB3FeqOrderUnit l_feqOrderUnit = null;
            try
            {
                l_feqOrderUnit = (WEB3FeqOrderUnit) l_orderManager.getOrderUnit(l_lngOrderUnitId);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B",
                    l_ex);
            }
            WEB3FeqFinTransactionManager l_finTransaction =
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            List l_lstTransaction = l_finTransaction.getTransactions(l_feqOrderUnit);
            // update�T�Z��n���(�����P�� : �O�����������P��, ���� : Date)
            //[����]
            //�����P�ʁF�@@�����Ώۂ̒����P��ID�ɊY�����钍���P��
            //�����F�@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����()�̖߂�l
            //�ł���g�����U�N�V�����i������薾�ׁj�s��[0]�s��.getFinTransactionTimestamp
            l_orderManager.updateEstimatedPrice(
                l_feqOrderUnit, ((FeqFinTransactionRow)l_lstTransaction.get(0)).getFinTransactionTimestamp());

            //update���v�����z�i�~�j(�����P��ID : long)
            l_orderManager.updateExecutedAmountYen(l_lngOrderUnitId);

            //update�l�b�e�B���O�����X�V���t(�����P��ID : Long)
            //�����̐ݒ�d�l�͈ȉ��̒ʂ�
            //�@@�����P��ID�F�@@�p�����[�^.�����P��ID�ꗗ.[i]
            l_orderManager.updateNettingOrderLastUpdatedTimestamp(
                new Long(l_lngOrderUnitId));//WEB3BaseException
        }

        //is�l�b�e�B���O����
        boolean l_blnIsNetting = true;

        //update�g�����U�N�V����(long[], Date, boolean)
        //[����]
        //�����P��ID�ꗗ�F�@@ArrayList( )�̖߂�l�i�����P��ID�̈ꗗ�j
        //����F�@@�p�����[�^.������
        //is�l�b�e�B���O�����F�@@true
        this.updateTransaction(l_lngOrderUnitIdList, l_datDate, l_blnIsNetting);

        //ArrayList( )�̖߂�l�i�����P�ʁj�Ōڋq�̈ꗗ���擾���A�v�f����Loop�������s���B
        for (int j = 0; j < l_intSize; j++)
        {
            long l_lngOrderUnitId = ((Long)l_lisOrderUnitIDs.get(j)).longValue();
            log.debug("�����P�ʂh�c = " + l_lngOrderUnitId);

            //�ڋq
            WEB3GentradeMainAccount l_mainAccount = null;

            //�⏕����
            WEB3GentradeSubAccount l_subAccount = null;

            //�O�����������P��
            WEB3FeqOrderUnit l_feqOrderUnit = null;

            try
            {
                //ArrayList( )�̖߂�l�i�����P�ʁj�Ōڋq�̈ꗗ���擾��
                l_feqOrderUnit =
                    (WEB3FeqOrderUnit)l_orderManager.getOrderUnit(l_lngOrderUnitId);

                l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                        l_feqOrderUnit.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //is�M�p�����J��(�ٍϋ敪 : String)
            boolean l_blnIsMarginAccountEstablished =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
            log.debug("is�M�p�����J�� = " + l_blnIsMarginAccountEstablished);

            //getSubAccount(arg0 : SubAccountTypeEnum)
            try
            {
                //[is�M�p�����J��()�̖߂�l == true�̏ꍇ]
                if (l_blnIsMarginAccountEstablished)
                {
                    //SubAccountTypeEnum.�����M�p����������Z�b�g�B
                    l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);//NotFoundException
                }
                //[��L�ȊO�̏ꍇ]
                else
                {
                    //SubAccountTypeEnum.��������������Z�b�g�B
                    l_subAccount = (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException
                }
            }
            catch (NotFoundException l_ex)
            {
                log.debug("�⏕����������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�⏕����������܂���B",
                    l_ex);
            }

            WEB3TPTradingPowerReCalcService l_tPTradingPowerReCalcServiceImpl =
                (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);

            if (l_tPTradingPowerReCalcServiceImpl == null)
            {
                log.debug("�]�͍Čv�Z�T�[�r�XImpl�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�]�͍Čv�Z�T�[�r�XImpl�����݂��Ȃ��B");
            }

            //�]�͍Čv�Z(�⏕���� : �⏕����)
            //[����]
            //�⏕�����F�@@getSubAccount()�̖߂�l
            l_tPTradingPowerReCalcServiceImpl.reCalcTradingPower(l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
