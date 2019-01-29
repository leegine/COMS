head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.32.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֓��̓T�[�r�XImpl(WEB3AioSecurityTransferInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 ���z (���u) �V�K�쐬 
                   2005/02/16 ���� (���u) �c�Ή�
                   2006/10/26 �����q (���u) �d�l�ύX�E���f��664
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.aio.WEB3AioBizLogicProvider;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.define.WEB3AioTransferSortkeyDef;
import webbroker3.aio.message.WEB3AioChangePossQuantityComparator;
import webbroker3.aio.message.WEB3AioDepositDivComparator;
import webbroker3.aio.message.WEB3AioInstrumentsTypeComparator;
import webbroker3.aio.message.WEB3AioMarketValueComparator;
import webbroker3.aio.message.WEB3AioProductCodeComparator;
import webbroker3.aio.message.WEB3AioProductNameComparator;
import webbroker3.aio.message.WEB3AioSecurityTransferInputRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferInputResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferListRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferListResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferProductCodeNameUnit;
import webbroker3.aio.message.WEB3AioSecurityTransferSortKeyUnit;
import webbroker3.aio.message.WEB3AioSecurityTransferUnit;
import webbroker3.aio.message.WEB3AioTaxTypeComparator;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DailyAssetParams;
import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�،��U�֓��̓T�[�r�XImpl)<BR>
 * �،��U�֓��̓T�[�r�X�����N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferInputServiceImpl extends WEB3ClientRequestService implements WEB3AioSecurityTransferInputService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferInputServiceImpl.class);  
    
    /**
     * �،��U�֓��̓T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��create�ꗗ���()�A�܂���create���͉��()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41576F0E034B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��create�ꗗ���()�A�܂���create���͉��()���\�b�h���R�[������B
        if (l_request instanceof WEB3AioSecurityTransferListRequest)
        {
            l_response = 
                createListScreen((WEB3AioSecurityTransferListRequest)l_request);   
        }
        else if (l_request instanceof WEB3AioSecurityTransferInputRequest)
        {
            l_response =
                createInputScreen((WEB3AioSecurityTransferInputRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create�ꗗ���)<BR>
     * �ꗗ��ʂ̕\���p�f�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�،��U�֓���)create�ꗗ��ʁv �Q��
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(�،��U�փT�[�r�X���f��) / �،��U�֓��� �v<BR>
     *          : �،��U�֓��� / create�ꗗ���
     *  1.5 is�M�p�����J��(String)
     *  �A�C�e���̒�`
     *  �M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B 
     *  [����] 
     *  �ٍϋ敪�F 0�i�w��Ȃ�)
     *  �߂�l=false �̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00747<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferListResponse
     * @@throws WEB3BaseException
     * @@roseuid 41576F640231
     */
    protected WEB3AioSecurityTransferListResponse createListScreen(WEB3AioSecurityTransferListRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createListScreen(WEB3AioSecurityTransferListRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 �ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.5 �M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B 
        //[����] 
        //�ٍϋ敪�F 0�i�w��Ȃ��j 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is�M�p�����J��(String)�̖߂�l=false �̏ꍇ�A��O���X���[����B
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is�M�p�����J��(String)�̖߂�l=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is�M�p�����J��(String)�̖߂�l=false");            
        }
        
        //1.6 �擾�����̕�����𐶐�����B 
        //[����] 
        //�����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v 
        String l_strInstrumentType = l_request.instrumentsType;
        //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
        String l_strProductCode = l_request.productCode;
        //�a��敪�F ���N�G�X�g�f�[�^.�a��敪 
        String l_strDepositDiv = l_request.depositDiv;
        
        String l_strCond = this.createGetCondCharacterString(
            l_strInstrumentType,
            l_strProductCode,
            l_strDepositDiv);
            
        //1.7 �擾�����ɃZ�b�g����f�[�^�̔z��𐶐�����B 
        //[����] 
        //�����F �ڋq�I�u�W�F�N�g 
        //�����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v 
        //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
        //�a��敪�F ���N�G�X�g�f�[�^.�a��敪 
        Object[] l_objArrayCondData = this.createGetCondDataContainer(
            l_mainAccount,
            l_strInstrumentType,
            l_strProductCode,
            l_strDepositDiv);        

        List l_lisDailyAssetRows; 
        try
        {
            //1.8 �N�G���v���Z�b�T���擾����B
            //throw datafindException and networkException         
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            
            //1.9 ��n���ʕۗL���Y�e�[�u������A�����ɍ��v���郌�R�[�h���擾����B 
            //[����] 
            //Row�^�C�v�F ��n���ʕۗL���YRow.TYPE 
            //Where�F create�擾����������()�̖߂�l 
            //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l 
            
            //throw dataqueryException and networkException    
            l_lisDailyAssetRows =
                l_processor.doFindAllQuery(
                    DailyAssetRow.TYPE,
                    l_strCond,
                    l_objArrayCondData);

        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }  
        
        
        //1.10 ���ArrayList�𐶐�����
        List l_lisSecurityTransferUnit = new ArrayList();
        
        //1.11 �擾�������R�[�h���Ƃ�LOOP    
        //get the iterator of the RowList
        int l_intSize = 0;
        if (l_lisDailyAssetRows != null )
        {
            l_intSize = l_lisDailyAssetRows.size();
        }
        
        for (int i = 0; i < l_intSize; i++)
        {
            DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAssetRows.get(i);            
            
            // test log
            log.debug("�擾�������R�[�h......NO. " + i);
            log.debug("l_dailyAssetRow = " + l_dailyAssetRow);
                        
            //(*1)select the record of Product under the condition (DailyAssetRow.productId)
            ProductRow l_productRow;
            try
            {
                l_productRow = ProductDao.findRowByPk(l_dailyAssetRow.getProductId());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DataQueryException", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, 
                    l_ex.getMessage(), 
                    l_ex);            
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DataNetworkException", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, 
                    l_ex.getMessage(), 
                    l_ex);
            }  
            
            //1.11.1 (*2) (*1)�Ŏ擾��������.��p�|�� != 0 �̏ꍇ
            if (l_productRow.getMarginRatio() != 0)
            {
                //1.11.1.1 �a��،����׃I�u�W�F�N�g�𐶐�����B 
                //[����] 
                //�ۗL���Y�F ��n���ʕۗL���YParams
                DailyAssetParams l_dailyAssetParams = 
                    new DailyAssetParams(l_dailyAssetRow);
             
                WEB3AioSecurityTransferUnit l_newAioSecurityTransferUnit =
                    createInstitutionBondDetails(l_dailyAssetParams);
                
                //1.11.1.2 ArrayList�ɗa��،����׃I�u�W�F�N�g��ǉ�����B 
                //[����] 
                //arg0�F �a��،����׃I�u�W�F�N�g 
                int l_intTempSize = l_lisSecurityTransferUnit.size();
                    
                //�a��،�����.���� == 0 �̏ꍇ���A�X�L�b�v����B      
                if (l_newAioSecurityTransferUnit.changePossQuantity.equals("0"))
                {
                    continue;
                }
               
                //���i�^�C�v�A�����R�[�h�A�����敪�A�a��敪����v����
                //���ׂ����ɑ��݂���ꍇ�́A�X�L�b�v����B
                //get the iterator of the new List --���ArrayList  
                if (l_intTempSize == 0)
                {
                    //�ǉ�
                    l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit); 
                    continue;
                }
                
                boolean l_blnMark = false;
                for (int j = 0; j < l_intTempSize; j++)
                {
                    WEB3AioSecurityTransferUnit l_unit = 
                        (WEB3AioSecurityTransferUnit)l_lisSecurityTransferUnit.get(j);
                    
                    if (l_newAioSecurityTransferUnit.taxType == null || l_unit.taxType == null)
                    {
                        if (l_newAioSecurityTransferUnit.taxType == null && l_unit.taxType == null)
                        {
                            if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                                l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                                l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                            {
                                l_blnMark = true;
                                break;        
                            }
                        }
                    }
                    else
                    {
                        //compare 
                        if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                            l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                            l_newAioSecurityTransferUnit.taxType.equals(l_unit.taxType) &&
                            l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                        {
                            l_blnMark = true;
                            break;        
                        }                         
                    } 
                }         
                if (l_blnMark == false)
                {
                    l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit);                                                          
                }                               
            }
        }

        //1.12 doFindAllQuery(Row�^�C�v : RowType, Where : String, ���X�g : Object[])
        //�����P�ʃe�[�u������A�����ɍ��v���郌�R�[�h���擾����B 
        //[����] 
        //Row�^�C�v�F �����P��Row.TYPE 
        //Where�F create�擾����������()�̖߂�l 
        //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l 
        List l_lisAioOrderUnitRows; 
        try
        {            
            QueryProcessor l_processor = Processors.getDefaultProcessor();            
            //throw dataqueryException and networkException    
            l_lisAioOrderUnitRows =
                l_processor.doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strCond,
                    l_objArrayCondData);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException in select__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, 
                l_ex.getMessage(), 
                l_ex);
        }  
        
        int l_intOrderUnitSize = 0;
        if (l_lisAioOrderUnitRows != null )
        {
            l_intOrderUnitSize = l_lisAioOrderUnitRows.size();
        }
        
        //1.13 �擾�������R�[�h���Ƃ�LOOP
        for (int i = 0; i < l_intOrderUnitSize; i++)
        {
            boolean l_blnSameProduct = false;
            AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);            
            for (int j = 0; j < l_intSize; j++)
            {
				DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAssetRows.get(j);
				if (l_aioOrderUnitRow.getProductId() == l_dailyAssetRow.getProductId() &&
					l_aioOrderUnitRow.getSubAccountId() == l_dailyAssetRow.getSubAccountId() &&
					l_aioOrderUnitRow.getTaxType() == l_dailyAssetRow.getTaxType())
                {
                    l_blnSameProduct = true;
                    break;
                }               
            }
            //1.13.1 �擾������n���ʕۗL���Y�̃��R�[�h�ɓ���̖���ID�ƕ⏕����ID�Ɛŋ敪�����݂��Ȃ��ꍇ
            if (!l_blnSameProduct)
            {                        
                ProductRow l_productRow;
                try
                {
                    l_productRow = ProductDao.findRowByPk(l_aioOrderUnitRow.getProductId());
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DataQueryException", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName, 
                        l_ex.getMessage(), 
                        l_ex);            
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DataNetworkException", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + l_strMethodName, 
                        l_ex.getMessage(), 
                        l_ex);
                }  
                
                //1.13.1.1 (*4) (*3)�Ŏ擾��������.��p�|�� != 0 �̏ꍇ
                if (l_productRow.getMarginRatio() != 0)
                {
                    //1.13.1.1.1 create�a��،����׃I�u�W�F�N�g�𐶐�����B 
                    //[����] 
                    //�ۗL���Y�F �����P��Params 
                    AioOrderUnitParams l_aioOrderUnitParams = 
                        new AioOrderUnitParams(l_aioOrderUnitRow);
                 
                    WEB3AioSecurityTransferUnit l_newAioSecurityTransferUnit =
                        createInstitutionBondDetails(l_aioOrderUnitParams);
                    
                    //1.13.1.1.2 ArrayList�ɗa��،����׃I�u�W�F�N�g��ǉ�����B 
                    //[����] 
                    //arg0�F �a��،����׃I�u�W�F�N�g                     
                    int l_intTempSize = l_lisSecurityTransferUnit.size();
                        
                    //�a��،�����.���� == 0 �̏ꍇ���A�X�L�b�v����B      
                    if (l_newAioSecurityTransferUnit.changePossQuantity.equals("0"))
                    {
                        continue;
                    }
                   
                    //���i�^�C�v�A�����R�[�h�A�����敪�A�a��敪����v����
                    //���ׂ����ɑ��݂���ꍇ�́A�X�L�b�v����B
                    //get the iterator of the new List --���ArrayList  
                    if (l_intTempSize == 0)
                    {
                        //�ǉ�
                        l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit); 
                        continue;
                    }
                    
                    boolean l_blnMark = false;
                    for (int j = 0; j < l_intTempSize; j++)
                    {
                        WEB3AioSecurityTransferUnit l_unit = 
                            (WEB3AioSecurityTransferUnit)l_lisSecurityTransferUnit.get(j);
                        
                        if (l_newAioSecurityTransferUnit.taxType == null || l_unit.taxType == null)
                        {
                            if (l_newAioSecurityTransferUnit.taxType == null && l_unit.taxType == null)
                            {
                                if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                                    l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                                    l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                                {
                                    l_blnMark = true;
                                    break;        
                                }
                            }
                        }
                        else
                        {
                            //compare 
                            if (l_newAioSecurityTransferUnit.instrumentsType.equals(l_unit.instrumentsType) &&
                                l_newAioSecurityTransferUnit.productCode.equals(l_unit.productCode) &&
                                l_newAioSecurityTransferUnit.taxType.equals(l_unit.taxType) &&
                                l_newAioSecurityTransferUnit.depositDiv.equals(l_unit.depositDiv))
                            {
                                l_blnMark = true;
                                break;        
                            }                         
                        } 
                    }         
                    if (l_blnMark == false)
                    {
                        l_lisSecurityTransferUnit.add(l_newAioSecurityTransferUnit);                                                          
                    }           
                }
            }
        }

        //1.14 �z����擾����B 
        WEB3AioSecurityTransferUnit[] l_arrayAioSecurityTransferUnit =
            new WEB3AioSecurityTransferUnit[l_lisSecurityTransferUnit.size()];
        //toArray
        l_lisSecurityTransferUnit.toArray(l_arrayAioSecurityTransferUnit);

        //1.15 �a��،����ׂ̔z����\�[�g����B 
        //[����] 
        //���ׁF �a��،����ׂ̔z�� 
        //�\�[�g�L�[�F ���N�G�X�g�f�[�^.�\�[�g�L�[ 
        WEB3AioSecurityTransferUnit[] l_web3AioSecurityTransferUnit = 
            this.sortInstitutionBondDetails(
                l_arrayAioSecurityTransferUnit,
                l_request.sortKeys);

        //1.16  get�\�����ׁA�\���Ώۍs�ifromIndex�`toIndex�j�̃I�u�W�F�N�g�z����w�肷��B
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);    
 
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(l_web3AioSecurityTransferUnit, l_intPageIndex, l_intPageSize);
        l_web3AioSecurityTransferUnit = 
            (WEB3AioSecurityTransferUnit[])l_pageIndexInfo.getArrayReturned(WEB3AioSecurityTransferUnit.class);
   
            
        //1.17 �ۗL�������ׂ̔z����擾����B 
        WEB3AioSecurityTransferProductCodeNameUnit[] l_arraySecTraProductCodeNameUnit =
            this.getProductCodeNameDetails();
            
        //log for test
        log.debug("�ۗL�������ׂ̔z�� : " + l_arraySecTraProductCodeNameUnit);    
            
        //1.18 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AioSecurityTransferListResponse l_response = 
            (WEB3AioSecurityTransferListResponse)l_request.createResponse();
            
        //1.19 �v���p�e�B�Z�b�g
        //���X�|���X.�a��،��ꗗ = �،��U�֓��̓T�[�r�XImpl.get�\������()�̖߂�l
        l_response.securityTransfer = l_web3AioSecurityTransferUnit;
        
        //���X�|���X.�\���y�[�W�ԍ� = toIndex / ���N�G�X�g�f�[�^.�y�[�W���e�\���s��  �������_�ȉ��؂�グ
        l_response.pageIndex = Integer.toString(l_pageIndexInfo.getPageIndex());
        
        //���X�|���X.���y�[�W�� = �i�a��،����ׁm�n.length()�̖߂�l�j / ���N�G�X�g�f�[�^.�y�[�W���e�\���s��  �������_�ȉ��؂�グ
        l_response.totalPages = Integer.toString(l_pageIndexInfo.getTotalPages());
         
        //���X�|���X.�����R�[�h�� = �a��،����ׁm�n.length()�̖߂�l
        l_response.totalRecords = Integer.toString(l_pageIndexInfo.getTotalRecords());
        
        //���X�|���X.�����ꗗ = �،��U�֓��̓T�[�r�XImpl.get�ۗL��������()�̖߂�l
        l_response.productCodeNames = l_arraySecTraProductCodeNameUnit;
               
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create���͉��)<BR>
     * ���͉�ʂ̕\���p�f�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�،��U�֓���)create���͉�ʁv �Q��
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�u(�،��U�փT�[�r�X���f��) / �،��U�֓��� �v<BR>
     *          : �،��U�֓��� / create���͉��
     *  1.5 is�M�p�����J��(String)
     *  �A�C�e���̒�`
     *  �M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B 
     *  [����] 
     *  �ٍϋ敪�F 0�i�w��Ȃ�)
     *  �߂�l=false �̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00747<BR>
     * <BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41576F6D02CE
     */
    protected WEB3AioSecurityTransferInputResponse createInputScreen(WEB3AioSecurityTransferInputRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = 
            "createInputScreen(WEB3AioSecurityTransferInputRequest l_request)";
        log.entering(l_strMethodName);

        //1.1 ���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 �⏕�����I�u�W�F�N�g���擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j 
        SubAccount l_subAccount = 
            this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        
        //1.3 validate����(SubAccount)
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //FinApp, TradingModule, AioOrderManager
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager)l_tradingModule.getOrderManager();
        
        l_orderManager.validateOrder(l_subAccount);
        
        //1.4 �ڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //1.5 �M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B 
        //[����] 
        //�ٍϋ敪�F 0�i�w��Ȃ��j 
        boolean l_booisMarginAccountEstablished = 
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //is�M�p�����J��(String)�̖߂�l=false �̏ꍇ�A��O���X���[����B
        //   class: WEB3BusinessLayerException
        //     tag: BUSINESS_ERROR_00747
        if (l_booisMarginAccountEstablished == false)
        {
            log.debug("is�M�p�����J��(String)�̖߂�l=false");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                this.getClass().getName() + "." + l_strMethodName,
                "is�M�p�����J��(String)�̖߂�l=false");            
        }
        
        //1.6 ���������擾����B 
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.7 �،��U�֒�����1���̏���񐔂𒴂��ĂȂ������`�F�b�N����B 
        //[����] 
        //�⏕�����F get�⏕����()�̖߂�l 
        //�������F get������()�̖߂�l 
        l_orderManager.validateInstitutionTransferPossibleCount(
            l_subAccount,
            l_datOrderBizDate);
            
        //1.8 �����I�u�W�F�N�g���擾����B 
        //[����] 
        //a> �����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v 
        String l_strInstrumentType = l_request.instrumentsType;
        
        ProductTypeEnum l_enumInstrumentType;      
        if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.EQUITY;
        }
        else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.BOND;
        }
        else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strInstrumentType)) 
        {
            l_enumInstrumentType = ProductTypeEnum.MUTUAL_FUND;
        }
        else 
        {
            l_enumInstrumentType = ProductTypeEnum.FOREIGN_EQUITY;
        }
        
        //b> �����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
        String l_strProductCode = l_request.productCode;
        //c> �،���ЁF �⏕����.getInsutitution()�̖߂�l 
        Institution l_institution = l_subAccount.getInstitution(); 
        
        //d> AIO�v���_�N�g�}�l�[�W�����擾 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
        //e> getProduct
        Product l_product = l_productManager.getProduct(
            l_enumInstrumentType, 
            l_strProductCode, 
            l_institution);
        
        //1.9 �U�։\���ʂ��Z�o����B 
        //[����] 
        //a> ����ID�F �iget�⏕����()�̖߂�l�j.getAccountId()�̖߂�l 
        long l_lngAccountId = l_subAccount.getAccountId();
        
        //b> �����^�C�v�F ���N�G�X�g�f�[�^.���i�^�C�v ---l_enumInstrumentType in 1.8
        
        //c> ����ID�F �iget����()�̖߂�l�j.����ID 
        long l_lngProductId = l_product.getProductId();
        
        //d> �ŋ敪�F 
        TaxTypeEnum l_taxTypeEnum;
        //���N�G�X�g�f�[�^.�����敪=null�̏ꍇ�F 0�i���̑��j
        if (l_request.taxType == null)
        {
            l_taxTypeEnum = TaxTypeEnum.UNDEFINED;            
        }
        //���N�G�X�g�f�[�^.�����敪=�h��ʁh�̏ꍇ�F 1�i��ʁj 
        else if(WEB3TaxTypeSpecialDef.NORMAL.equals(l_request.taxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;    
        }
        //���N�G�X�g�f�[�^.�����敪=�h����h�̏ꍇ�F 2�i����j
        else
        {            
            l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        //e> �a��敪�F ���N�G�X�g�f�[�^.�U�֌��a��敪 
        String l_strDepositDiv = l_request.depositDiv;
        
        //get WEB3AioBizLogicProvider
        WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
            (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        // test log
        log.debug("���N�G�X�g�f�[�^.�U�֌��a��敪 = " + l_strDepositDiv);
        
        double l_dblTransPossibleAmount = 
            l_web3AioBizLogicProvider.calcTransPossibleAmount(
                l_lngAccountId,
                l_enumInstrumentType,
                l_lngProductId,
                l_taxTypeEnum,
                l_strDepositDiv);

        // test log
        log.debug("�U�։\���ʂ��Z�o = " + l_dblTransPossibleAmount);
        
        //1.10 �،��]���z���Z�o����B 
        //[����] 
        //����ID�F�iget����()�̖߂�l�j.����ID 
        //�a��敪�F���N�G�X�g�f�[�^.�U�֌��a��敪 
        //���ʁFcalc�U�։\����()�̖߂�l 
        double l_dblStockEvalueAmount = 
            l_web3AioBizLogicProvider.calcStockEvalueAmount(
                l_lngProductId,
                l_strDepositDiv,
                l_dblTransPossibleAmount);
            
        // test log
        log.debug(" �،��]���z���Z�o = " + l_dblStockEvalueAmount);

        //1.11 ���X�|���X�f�[�^�𐶐�����B 
        WEB3AioSecurityTransferInputResponse l_response =
            (WEB3AioSecurityTransferInputResponse)l_request.createResponse();
            
        //1.12 �v���p�e�B���Z�b�g����B
        //���X�|���X.������ = (get����()�̖߂�l).������
        l_response.productName = l_product.getStandardName();
        //���X�|���X.�U�։\���� = ���o���v�Z�T�[�r�X.calc�U�։\����()�̖߂�l
        l_response.changePossQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblTransPossibleAmount);
        //���X�|���X.�]���z = ���o���v�Z�T�[�r�X.calc�،��]
        l_response.marketValue = 
            WEB3StringTypeUtility.formatNumber(l_dblStockEvalueAmount);
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get�ۗL��������)<BR>
     * �ۗL�������ׂ̔z��𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�،��U�֓��́jget�ۗL�������ׁv �Q��
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferProductCodeNameUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4163BB8403CB
     */
     protected WEB3AioSecurityTransferProductCodeNameUnit[] getProductCodeNameDetails() 
        throws WEB3BaseException
     {
         String l_strMethodName = "getProductCodeNameDetails()";
         log.entering(l_strMethodName);

         //1.1 �a��������I�u�W�F�N�g���擾����B 
         //[����] 
         //�⏕�����^�C�v�F 1�i�a��������j 
         SubAccount l_subAccount = 
             this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

         //1.2 �ۏ؋������I�u�W�F�N�g���擾����B 
         //[����] 
         //�⏕�����^�C�v�F 2�i�ۏ؋������j 
         SubAccount l_marginSubAccount = 
             this.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);

         List l_lisDailyAssetRows;    
         try
         {
             //1.3 getDefaultProcessor()
             //throw datafindException and networkException
             QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

             //1.4 ��n���ʕۗL���Y�e�[�u������A�ȉ��̏����̃��R�[�h���擾����B  
             //[����] 
             //Row�^�C�v�F ��n���ʕۗL���YRow.TYPE 
             //Where�F "account_id=? and (sub_accout_id=? or sub_account_id=?) and 
             //        (product_type>=? and product_type<=?) and mini_stock_div=?" 
             //���X�g�F �ȉ��̍��ڂ̃��X�g 
             //�a�������.getAccountId()�̖߂�l
             long l_lngAccountId = l_subAccount.getAccountId(); 
             log.debug("AccountId= " + l_lngAccountId);

             //�a�������.getSubAccountId()�̖߂�l 
             long l_lngSubAccountId = l_subAccount.getSubAccountId();
             log.debug("l_lngSubAccountId= " + l_lngSubAccountId);

             //�ۏ؋�����.getSubAccountId()�̖߂�l 
             long l_marginSubAccountId = l_marginSubAccount.getSubAccountId();
             log.debug("l_marginSubAccountId= " + l_marginSubAccountId);

             //�����^�C�v.���� 
             ProductTypeEnum l_productTypeEquity = ProductTypeEnum.EQUITY;

             //�����^�C�v.���M 
             ProductTypeEnum l_productTypeMutualFund = ProductTypeEnum.MUTUAL_FUND;

             //0�iDEFAULT�j 
             String l_miniStockType = WEB3MiniStockDivDef.DEFAULT_MINI_STOCK;

             String l_strWhere = 
                 " account_id = ? and (sub_account_id = ? or sub_account_id = ?) and " +
                 "(product_type >= ? and product_type <= ?) and mini_stock_div = ? ";
             Object[] l_arrayWhere = {
                 new Long(l_lngAccountId),
                 new Long(l_lngSubAccountId),
                 new Long(l_marginSubAccountId),
                 Integer.toString(l_productTypeEquity.intValue()),
                 Integer.toString(l_productTypeMutualFund.intValue()),
				 l_miniStockType};

             //throw dataqueryException and networkException    
             l_lisDailyAssetRows = 
                 l_queryProcessor.doFindAllQuery(
                         DailyAssetRow.TYPE,
                         l_strWhere,
                         l_arrayWhere);
         }
         catch (DataFindException l_ex)
         {
             log.error("__DataFindException in select__", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);
         }
         catch (DataQueryException l_ex)
         {
             log.error("__DataQueryException in select__", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);            
         }
         catch (DataNetworkException l_ex)
         {
             log.error("DataNetworkException in select", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);
         } 

         //1.5 ���ArrayList�𐶐�����B 
         List l_lisSecurityTranProCodeNameUnits = new ArrayList();

         //FinApp, TradingModule
         FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
         TradingModule l_tradingModule = 
             l_finApp.getTradingModule(ProductTypeEnum.AIO);

         //AIO�v���_�N�g�}�l�[�W�����擾 
         WEB3AioProductManager l_productManager = 
             (WEB3AioProductManager)l_tradingModule.getProductManager();

         int l_intSize = 0;
         if (l_lisDailyAssetRows != null)
         {
             l_intSize = l_lisDailyAssetRows.size();     
         }

         //1.6 �擾������n���ʕۗL���Y���R�[�h����LOOP 
         for (int i = 0; i < l_intSize; i++)      
         {
             //1.6.1 get����(ProductTypeEnum, long)
             //[����] 
             //a> �����^�C�v(int)�F ��n���ʕۗL���YParams.�����^�C�v
             ProductTypeEnum l_productTypeEnum = 
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductType();

             //b> ����ID�F ��n���ʕۗL���YParams.����ID 
             long l_lngProductId = 
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductId();

             Product l_product = l_productManager.getProduct(
                     l_productTypeEnum,
                     l_lngProductId);

             //2005-2-16==========huang-jian=========��Q�[U00869==========Start

             //1.6.2�擾��������.��p�|�� != 0 �̏ꍇ
             if (l_product.getMarginRatio() != 0)
             {
                 //1.6.2.1get�⏕����(, )
                 //�⏕�������擾����B 
                 //[����] 
                 //����ID�F ��n���ʕۗL���YParams.����ID 
                 //�⏕����ID�F ��n���ʕۗL���YParams.�⏕����ID
                 long l_lngAccountId =
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getAccountId();    
                 long l_lngSubAccountId = 
                     ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getSubAccountId();
                 WEB3GentradeAccountManager l_accountManager =
                     (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                 try
                 {
                     SubAccount l_subGentAccount =
                         l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);

                     //1.6.2.2 getSubAccountType( )�⏕�����^�C�v���擾����B
                     SubAccountTypeEnum l_subAccountType = l_subGentAccount.getSubAccountType(); 

                     //1.6.2.3 calc�U�։\����(long, ProductTypeEnum, long, TaxTypeEnum, String)
                     //�U�։\���ʂ��Z�o����B 
                     //[����] 
                     //����ID�F ��n���ʕۗL���YParams.����ID 
                     //�����^�C�v�F ��n���ʕۗL���YParams.�����^�C�v 
                     //����ID�F ��n���ʕۗL���YParams.����ID 
                     //�ŋ敪�F ��n���ʕۗL���YParams.�ŋ敪 
                     //�a��敪�F 
                     //�⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j 
                     //�⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j 
                     WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                         (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
                     String l_strDepositDiv = null;
                     if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountType))
                     {
                         l_strDepositDiv = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;
                     }
                     else
                     {
                         if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountType))
                         {
                             l_strDepositDiv = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;
                         }
                     }   
                     double l_dblTransPossibleAmount = 
                         l_web3AioBizLogicProvider.calcTransPossibleAmount(
                             l_lngAccountId,
                             ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductType(),
                             l_lngProductId,
                             ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getTaxType(),
                             l_strDepositDiv);

                     //1.6.2.4 �U�։\���� > 0 �̏ꍇ
                     if (l_dblTransPossibleAmount > 0)
                     {
                         //1.6.2.4.1 get����(ProductTypeEnum, long)
                         //�����I�u�W�F�N�g���擾����B 
                         //[����] 
                         //�����^�C�v�F ��n���ʕۗL���YParams.�����^�C�v 
                         //����ID�F ��n���ʕۗL���YParams.����ID 
                         Product l_products = l_productManager.getProduct(
                             ((DailyAssetRow)l_lisDailyAssetRows.get(i)).getProductType(),
                             l_lngProductId);

                         //1.6.2.4.2 �،��U�֕ۗL��������( )    
                         //�ۗL�������ׂ̃C���X�^���X�𐶐�����B 
                         WEB3AioSecurityTransferProductCodeNameUnit l_securityTranProCodeNameUnit = 
                             new WEB3AioSecurityTransferProductCodeNameUnit();

                         //1.6.2.4.3 �v���p�e�B�Z�b�g    
                         //�ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B

                         //a> �ۗL��������.���i�^�C�v = �����I�u�W�F�N�g.�����^�C�v
                         l_securityTranProCodeNameUnit.instrumentsType = 
                             String.valueOf(l_products.getProductType().intValue());

                         //b> �ۗL��������.�����R�[�h = �i�ȉ��̂Ƃ���j
                         //���i�^�C�v == �h���h �̏ꍇ�A�����I�u�W�F�N�g.�񍆃R�[�h(SONAR) + �����I�u�W�F�N�g.�����R�[�h(SONAR)�̏�4��
                         //���i�^�C�v != �h���h �̏ꍇ�A�����I�u�W�F�N�g.�����R�[�h
                         if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                         {
                             l_securityTranProCodeNameUnit.productCode =
                                 ((EqtypeProductRow)l_products.getDataSourceObject()).getProductCode();    
                         }
                         else if(ProductTypeEnum.BOND.equals(l_productTypeEnum))
                         {
                             // �����I�u�W�F�N�g.�����R�[�h(SONAR)�̏�4��
                             String l_strHostProductCode = ((BondProductRow)
                                 l_products.getDataSourceObject()).getHostProductCode().substring(0, 4);
                             //�����I�u�W�F�N�g.�񍆃R�[�h(SONAR)
                             String l_strHostIssueCode = ((BondProductRow)
                                 l_products.getDataSourceObject()).getHostProductIssueCode();
                             
                             l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                             
                             l_securityTranProCodeNameUnit.productCode = l_strHostProductCode;
                         }
                         else if(ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
                         {
                             l_securityTranProCodeNameUnit.productCode =
                                 ((MutualFundProductRow)l_products.getDataSourceObject()).getProductCode();
                         }
                         else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
                         {
                             l_securityTranProCodeNameUnit.productCode =
                                 ((FeqProductRow)l_products.getDataSourceObject()).getProductCode();
                         }

                         //c> �ۗL��������.������ = �����I�u�W�F�N�g.������
                         l_securityTranProCodeNameUnit.productName = 
                             l_products.getStandardName();

                         //1.6.2.4.4 add(arg0 : Object)
                         //ArrayList�ɕۗL�������׃I�u�W�F�N�g��ǉ�����B 
                         //[����] 
                         //arg0�F �ۗL�������׃I�u�W�F�N�g
                         int l_intTranProCodeSize = 0;
                         if (l_lisSecurityTranProCodeNameUnits != null)
                         {
                             l_intTranProCodeSize = l_lisSecurityTranProCodeNameUnits.size();     
                         }
                         //���ł�ArrayList�ɑ��݂�������ɂ��ẮA�X�L�b�v����B
                         boolean l_booMark = true;     

                         for (int j = 0; j < l_intTranProCodeSize; j++)
                         {
                             if (((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).productCode
                             .equals(l_securityTranProCodeNameUnit.productCode) && 
                             ((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).instrumentsType
                             .equals(l_securityTranProCodeNameUnit.instrumentsType))
                             {
                                 l_booMark = false;
                                 break;    
                             }                        
                         }                  
                         if (l_booMark)
                         {
                             l_lisSecurityTranProCodeNameUnits.add(l_securityTranProCodeNameUnit);        
                         }                                      
                     }     
                 }
                 catch (NotFoundException l_ex)
                 {
                     log.debug("__NotFoundException__", l_ex);
                     //��O���X���[����
                     log.exiting(l_strMethodName);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         getClass().getName() + "." + l_strMethodName,
                         l_ex.getMessage(),
                         l_ex);
                 }   
             }
        }

         //1.7 doFindAllQuery(Row�^�C�v : RowType, Where : String, ���X�g : Object[])
         //�����P�ʃe�[�u������A�ȉ��̏����̃��R�[�h���擾����B 
         //[����] 
         //����ID = �a�������.getAccountId()�̖߂�l and 
         //�i�⏕����ID = �a�������.getSubAccountId()�̖߂�l or �ۏ؋�����.getSubAccountId()�̖߂�l�j and 
         //�i�����^�C�v >= 1�F���� and �����^�C�v <= 3�F���M�j 
         //[����] 
         //Row�^�C�v�F �����P��Row.TYPE 
         //Where�F "account_id=? and (sub_account_id=? or sub_account_id=?) and (product_type>=? and product_type<=?) and mini_stock_div=?" 
         //���X�g�F �ȉ��̍��ڂ̃��X�g 
         //�a�������.getAccountId()�̖߂�l 
         //�a�������.getSubAccountId()�̖߂�l 
         //�ۏ؋�����.getSubAccountId()�̖߂�l 
         //�����^�C�v.���� 
         //�����^�C�v.���M 
         //0�iDEFAULT�j 
         List l_lisAioOrderUnitRows; 
         try
         {
             String l_strWhere = 
                 " account_id = ? and (sub_account_id = ? or sub_account_id = ? ) and " +
                 "(product_type >= ? and product_type <= ?) and mini_stock_div = ? ";
             
             long l_lngAccountId = l_subAccount.getAccountId();
             long l_lngSubAccountId = l_subAccount.getSubAccountId();
             long l_lngMarginSubAccountId = l_marginSubAccount.getSubAccountId();
                          
             Object[] l_arrayWhere = {
                 new Long(l_lngAccountId),
                 new Long(l_lngSubAccountId),
                 new Long(l_lngMarginSubAccountId),
                 ProductTypeEnum.EQUITY,
                 ProductTypeEnum.MUTUAL_FUND,
                 BooleanEnum.FALSE };
    
             QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
             //throw dataqueryException and networkException   

             l_lisAioOrderUnitRows = 
                 l_queryProcessor.doFindAllQuery(
                     AioOrderUnitRow.TYPE,
                     l_strWhere,
                     l_arrayWhere);
         }         
         catch (DataQueryException l_ex)
         {
             log.error("__DataQueryException in select__", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);            
         }
         catch (DataNetworkException l_ex)
         {
             log.error("DataNetworkException in select", l_ex);
             throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                     this.getClass().getName() + "." + l_strMethodName, 
                     l_ex.getMessage(), 
                     l_ex);
         } 
         int l_intOrderUnitSize = 0;
         if (l_lisAioOrderUnitRows != null )
         {
             l_intOrderUnitSize = l_lisAioOrderUnitRows.size();
         }
         
         long[] l_lngProductIds = new long[l_intSize];
         
         for (int k = 0; k < l_intSize; k++)
         {
             DailyAssetRow l_dailyAssetRow = (DailyAssetRow)l_lisDailyAssetRows.get(k);
             l_lngProductIds[k] = l_dailyAssetRow.getProductId();
         }
         
         //1.8 �擾�������R�[�h���Ƃ�LOOP
         for (int i = 0; i < l_intOrderUnitSize; i++)
         {
             boolean l_blnSameProductId = false;
             AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow)l_lisAioOrderUnitRows.get(i);            
             for (int j = 0; j < l_intSize; j++)
             {
                 if (l_aioOrderUnitRow.getProductId() == l_lngProductIds[j])
                 {
                     l_blnSameProductId = true;
                     break;
                 }               
             }
             //1.8.1 �擾������n���ʕۗL���Y�̃��R�[�h�ɓ���̖���ID�����݂��Ȃ��ꍇ
             if (!l_blnSameProductId)
             {             
                 //1.8.1.1 �����I�u�W�F�N�g���擾����B 
                 //[����] 
                 //arg0�F �����P��Params.����ID 
                 ProductRow l_productRow;
                 try
                 {
                     l_productRow = ProductDao.findRowByPk(l_aioOrderUnitRow.getProductId());
                 }
                 catch (DataQueryException l_ex)
                 {
                     log.error("DataQueryException", l_ex);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + l_strMethodName, 
                         l_ex.getMessage(), 
                         l_ex);            
                 }
                 catch (DataNetworkException l_ex)
                 {
                     log.error("DataNetworkException", l_ex);
                     throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                         this.getClass().getName() + "." + l_strMethodName, 
                         l_ex.getMessage(), 
                         l_ex);
                 }  
                 
                 //1.8.1.2 �擾��������.��p�|�� != 0 �̏ꍇ
                 if (l_productRow.getMarginRatio() != 0)
                 {
                     //1.8.1.2.1 get�⏕����(����ID : , �⏕����ID : )
                     //�⏕�������擾����B
                     //[����] 
                     //����ID�F �����P��Params.����ID 
                     //�⏕����ID�F �����P��Params.�⏕����ID  
                     long l_lngAccountId = l_aioOrderUnitRow.getAccountId();    
                     long l_lngSubAccountId = l_aioOrderUnitRow.getSubAccountId();
                     WEB3GentradeAccountManager l_accountManager =
                         (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                     
                     SubAccount l_subGentAccount = null;
                     try
                     {
                         l_subGentAccount =
                             l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);

                     }
                     catch (NotFoundException l_ex)
                     {
                         log.debug("__NotFoundException__", l_ex);
                         //��O���X���[����
                         log.exiting(l_strMethodName);
                         throw new WEB3SystemLayerException(
                             WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                             getClass().getName() + "." + l_strMethodName,
                             l_ex.getMessage(),
                             l_ex);
                     }        
                     //1.8.1.2.2 �⏕�����^�C�v���擾����B
                     SubAccountTypeEnum l_subAccountType = l_subGentAccount.getSubAccountType(); 
                     
                     //1.8.1.2.3 �U�։\���ʂ��Z�o����B 
                     //[����] 
                     //����ID�F �����P��Params.����ID 
                     //�����^�C�v�F �����P��Params.�����^�C�v 
                     //����ID�F �����P��Params.����ID 
                     //�ŋ敪�F �����P��Params.�ŋ敪 
                     //�a��敪�F 
                     //�⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j 
                     //�⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j
                     String l_strDepositDiv = null;
                     if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountType))
                     {
                         l_strDepositDiv = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;
                     }
                     else
                     {
                         if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountType))
                         {
                             l_strDepositDiv = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;
                         }
                     }   
                     WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                         (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
                     
                     double l_dblTransPossibleAmount = 
                         l_web3AioBizLogicProvider.calcTransPossibleAmount(
                             l_lngAccountId,
                             l_aioOrderUnitRow.getProductType(),
                             l_aioOrderUnitRow.getProductId(),
                             l_aioOrderUnitRow.getTaxType(),
                             l_strDepositDiv);
                     
                     //1.8.1.2.4 �擾�������� > 0 �̏ꍇ
                     if (l_dblTransPossibleAmount > 0)
                     {
                         //1.8.1.2.4.1 �����I�u�W�F�N�g���擾����B 
                         //[����] 
                         //�����^�C�v�F �����P��Params.�����^�C�v 
                         //����ID�F �����P��Params.����ID 
                         Product l_product = 
                             l_productManager.getProduct(
                                 l_aioOrderUnitRow.getProductType(),  
                                 l_aioOrderUnitRow.getProductId()); 
                         
                         //1.8.1.2.4.2 �،��U�֕ۗL��������( )    
                         //�ۗL�������ׂ̃C���X�^���X�𐶐�����B 
                         WEB3AioSecurityTransferProductCodeNameUnit l_securityTranProCodeNameUnit = 
                             new WEB3AioSecurityTransferProductCodeNameUnit();
                         
                         //1.8.1.2.4.3 �v���p�e�B�Z�b�g
                         //�ۗL��������.���i�^�C�v = �����I�u�W�F�N�g.�����^�C�v
                         //�ۗL��������.�����R�[�h = �i�ȉ��̂Ƃ���j
                         //  ���i�^�C�v = �h���h �̏ꍇ�A����.�񍆃R�[�h(SONAR) + ����.�����R�[�h(SONAR)�̏�4��
                         //  ���i�^�C�v != �h���h �̏ꍇ�A����.getProductCode()�̖߂�l
                         //�ۗL��������.������ = �����I�u�W�F�N�g.������
                         
                         //a> �ۗL��������.���i�^�C�v = �����I�u�W�F�N�g.�����^�C�v
                         l_securityTranProCodeNameUnit.instrumentsType = 
                             String.valueOf(l_product.getProductType().intValue());
                         
                         ProductTypeEnum l_productTypeEnum = l_aioOrderUnitRow.getProductType();   
                         if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
                         {
                             //���i�^�C�v 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 String.valueOf(ProductTypeEnum.EQUITY.intValue());
                             //�����R�[�h
                             l_securityTranProCodeNameUnit.productCode =
                                 ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
                         }
                         //���i�^�C�v = �h���h �̏ꍇ�A����.�񍆃R�[�h(SONAR) + ����.�����R�[�h(SONAR)�̏�4��
                         else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
                         {
                             //���i�^�C�v 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 String.valueOf(ProductTypeEnum.BOND.intValue());
                             //����.�����R�[�h(SONAR)�̏�4��      
                             String l_strHostProductCode = ((BondProductRow)
                                 l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                             //����.�񍆃R�[�h(SONAR)
                             String l_strHostIssueCode = ((BondProductRow)
                                 l_product.getDataSourceObject()).getHostProductIssueCode();
                             l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                             
                             l_securityTranProCodeNameUnit.productCode = l_strHostProductCode;
                         }
                         else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
                         {
                             //���i�^�C�v 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 String.valueOf(ProductTypeEnum.MUTUAL_FUND.intValue());
                             //�����R�[�h
                             l_securityTranProCodeNameUnit.productCode =
                                 ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();
                         }
                         else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
                         {
                             //���i�^�C�v 
                             l_securityTranProCodeNameUnit.instrumentsType = 
                                 
                                 String.valueOf(ProductTypeEnum.FOREIGN_EQUITY.intValue());
                             //�����R�[�h
                             l_securityTranProCodeNameUnit.productCode =
                                 ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();
                         }
                         //�ۗL��������.������ = �����I�u�W�F�N�g.������
                         l_securityTranProCodeNameUnit.productName = 
                             l_product.getStandardName();

                         //1.8.1.2.4.4 add(arg0 : Object)
                         //ArrayList�ɕۗL�������׃I�u�W�F�N�g��ǉ�����B 
                         //[����] 
                         //arg0�F �ۗL�������׃I�u�W�F�N�g
                         int l_intTranProCodeSize = 0;
                         if (l_lisSecurityTranProCodeNameUnits != null)
                         {
                             l_intTranProCodeSize = l_lisSecurityTranProCodeNameUnits.size();     
                         }
                         //���ł�ArrayList�ɑ��݂�������ɂ��ẮA�X�L�b�v����B
                         boolean l_booMark = true;     

                         for (int j = 0; j < l_intTranProCodeSize; j++)
                         {
                             if (((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).productCode
                                 .equals(l_securityTranProCodeNameUnit.productCode) && 
                                 ((WEB3AioSecurityTransferProductCodeNameUnit)l_lisSecurityTranProCodeNameUnits.get(j)).instrumentsType
                                 .equals(l_securityTranProCodeNameUnit.instrumentsType))
                             {
                                 l_booMark = false;
                                 break;    
                             }                        
                         }                  
                         if (l_booMark)
                         {
                             l_lisSecurityTranProCodeNameUnits.add(l_securityTranProCodeNameUnit);        
                         }                                      
                     }                    
                 }
             }
         }
         //1.9 toArray
         WEB3AioSecurityTransferProductCodeNameUnit[] l_arraySecurityTranProCodeNameUnits = 
             new WEB3AioSecurityTransferProductCodeNameUnit[l_lisSecurityTranProCodeNameUnits.size()];

         l_lisSecurityTranProCodeNameUnits.toArray(l_arraySecurityTranProCodeNameUnits);

         //1.10 ������Comparator�C���X�^���X�𐶐�����B 
         //[����] 
         //orderBy�F "A"�i�����j 
         WEB3AioProductNameComparator l_web3ProductNameComparator = 
             new WEB3AioProductNameComparator(WEB3AscDescDef.ASC);

         Comparator[] l_productNameComparators = {l_web3ProductNameComparator};
         //1.11 �ۗL�������ׂ�������ɂď����Ƀ\�[�g����B 
         //[����] 
         //�ۗL�������ׁF �ۗL��������[] 
         //com�F ������Comparator 
         WEB3ArraysUtility.sort(
             l_arraySecurityTranProCodeNameUnits,
             l_productNameComparators);

         log.exiting(l_strMethodName);

         return l_arraySecurityTranProCodeNameUnits;
    }
    
    /**
     * (create�擾����������)<BR>
     * ���N�G�X�g�f�[�^����A�f�[�^�擾����������𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j����ID��������<BR>
     * <BR>
     *   ����������F "account_id=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�j�⏕����ID��������<BR>
     * <BR>
     *   ����.�a��敪 != 0�i�w��Ȃ��j�̏ꍇ<BR>
     * <BR>
     *   ����������F " and sub_account_id=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�j�����^�C�v��������<BR>
     * <BR>
     *   ����.�����^�C�v == null �̏ꍇ<BR> 
     * <BR>
     *   ����������F " and product_type>=? and product_type<=?" 
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR> 
     * <BR>
     *   ����.�����^�C�v != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and product_type=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�j����ID��������<BR>
     * <BR>
     *   ����.�����R�[�h != null �̏ꍇ<BR>
     * <BR>
     *   ����������F " and product_id=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �U�j�~�j���敪��������<BR>
     * <BR>
     *   ����������F " and mini_stock_div=?"<BR> 
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>  
     * �V�j���������������ԋp����B  
     * @@param l_strProductType - �����^�C�v
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strBondDiv - �a��敪
     * @@return String
     * @@roseuid 41649A4902D9
     */
    protected String createGetCondCharacterString(
        String l_strProductType, 
        String l_strProductCode, 
        String l_strBondDiv) 
    {
        String l_strMethodName = 
            "createGetCondCharacterString(String l_strProductType, String l_strProductCode, String l_strBondDiv)";
        log.entering(l_strMethodName);
        
        //�P�j��̕�����𐶐�����B
        StringBuffer l_strBufWhere = new StringBuffer();
        
        //�Q�j����ID��������        
        //  ����������F "account_id=?"        
        //  ��L��������P�j�̕�����̖����ɒǉ�����B
        l_strBufWhere.append(" account_id = ? ");

        //�R�j�⏕����ID��������
        //  ����.�a��敪 != 0�i�w��Ȃ��j �̏ꍇ
        //  ����������F " and sub_account_id=?"
        //  ��L��������P�j�̕�����̖����ɒǉ�����B
        if (!WEB3AioDepositTypeDivDef.DEFAULT.equals(l_strBondDiv))
        {
            l_strBufWhere.append(" and sub_account_id = ? ");    
        }

        //�S�j�����^�C�v��������
        //  ����.�����^�C�v == null �̏ꍇ 
        //  ����������F " and product_type>=? and product_type<=?" 
        //  ��L��������P�j�̕�����̖����ɒǉ�����B 

        //  ����.�����^�C�v != null �̏ꍇ
        //  ����������F " and product_type=?"
        //  ��L��������P�j�̕�����̖����ɒǉ�����B
        if (l_strProductType == null)
        {
            l_strBufWhere.append(" and product_type >= ? and product_type <= ?");
        }
        else
        {
            l_strBufWhere.append(" and product_type = ? ");    
        }        

        //�T�j����ID��������
        //  ����.�����R�[�h != null �̏ꍇ
        //  ����������F " and product_id=?"
        //  ��L��������P�j�̕�����̖����ɒǉ�����B
        if (l_strProductCode != null)
        {
            l_strBufWhere.append(" and product_id = ? ");    
        }   
        
        //�U�j�~�j���敪��������
        //  ����������F " and mini_stock_div=?" 
        //  ��L��������P�j�̕�����̖����ɒǉ�����B
        l_strBufWhere.append(" and mini_stock_div=?");
        
        log.exiting(l_strMethodName);
        
        //�V�j���������������ԋp����B
        return l_strBufWhere.toString();
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j����ID��������<BR>
     * <BR>
     *   ����.����.getAccountId()�̖߂�l���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �R�j�⏕����ID��������<BR>
     * <BR>
     * �R�|�P�j����.�a��敪 = 1�i�ی�j �̏ꍇ<BR>
     * <BR>
     *   ����.����.getSubAccount(�⏕�����^�C�v).getSubAccountId()<BR>
     *       �̖߂�l���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *   [getSubAccount�ɓn������]<BR>
     *   �⏕�����^�C�v = 1�i�a����j<BR>
     * <BR>
     * �R�|�Q�j����.�a��敪 = 2�i��p�j �̏ꍇ<BR>
     * <BR>
     *   ����.����.getSubAccount(�⏕�����^�C�v).getSubAccountId()<BR>
     *       �̖߂�l���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *   [getSubAccount�ɓn������]<BR>
     *   �⏕�����^�C�v = 2�i�ۏ؋��j<BR>
     * <BR>
     * �S�j�����^�C�v��������<BR>
     * <BR>
     *   ����.�����^�C�v == null �̏ꍇ<BR> 
     * <BR>
     *   1�i�����j�A3�i���M�j���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *   ����.�����^�C�v != null �̏ꍇ<BR>
     * <BR>
     *   ����.�����^�C�v���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �T�j����ID��������<BR>
     * <BR>
     *   ����.�����R�[�h != null �̏ꍇ<BR>
     * <BR>
     *   AIO�v���_�N�g�}�l�[�W��.get����(�����^�C�v, �����R�[�h, �،����).getProductId()<BR>
     *   �̖߂�l���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     *   [get�����ɓn������]<BR>
     *   �����^�C�v = ����.�����^�C�v<BR>
     *   �����R�[�h = ����.�����R�[�h<BR>
     *   �،���� = ����.����.getInstitution()�̖߂�l<BR>
     * <BR>
     * �U�j�~�j���敪��������
     * <BR>
     *   0�iDEFAULT�j���P�j�̃��X�g�ɒǉ�����B<BR> 
     * <BR> 
     * �V�j���X�g����z����擾���A�ԋp����B
     * @@param l_mainAccount - �����I�u�W�F�N�g
     * @@param l_strProductType - �����^�C�v
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strBondDiv - �a��敪
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 41649A6A01CF
     */
    protected Object[] createGetCondDataContainer(
        MainAccount l_mainAccount, 
        String l_strProductType, 
        String l_strProductCode, 
        String l_strBondDiv) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "createGetCondDataContainer(MainAccount l_mainAccount, String l_strProductType, String l_strProductCode, String l_strBondDiv)";
        log.entering(l_strMethodName);
        
        //�P�j���ArrayList�𐶐�����B
        List l_lisBindVars = new ArrayList();
        
        //�Q�j����ID��������
        //  ����.����.getAccountId()�̖߂�l���P�j�̃��X�g�ɒǉ�����B
        long l_lngAccountId = l_mainAccount.getAccountId();
        l_lisBindVars.add(new Long(l_lngAccountId));

        //�R�j�⏕����ID��������
        //get subAccountId
        long l_subAccountId;
        
        //�R�|�P�j����.�a��敪 = 1�i�ی�j �̏ꍇ
        //  ����.����.getSubAccount(�⏕�����^�C�v).getSubAccountId()
        //      �̖߂�l���P�j�̃��X�g�ɒǉ�����B
        //  [getSubAccount�ɓn������]
        //  �⏕�����^�C�v = 1�i�a����j
        if (WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(l_strBondDiv))
        {
            try
            {
                l_subAccountId = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT).getSubAccountId();
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in getSubAccount(�a���)__", l_ex);
                log.exiting(l_strMethodName);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);            
            }
        }

        //�R�|�Q�j����.�a��敪 = 2�i��p�j �̏ꍇ
        //  ����.����.getSubAccount(�⏕�����^�C�v).getSubAccountId()
        //      �̖߂�l���P�j�̃��X�g�ɒǉ�����B
        //  [getSubAccount�ɓn������]
        //  �⏕�����^�C�v = 2�i�ۏ؋��j
        else if (WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(l_strBondDiv))
        {
            try
            {
                l_subAccountId = 
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT).getSubAccountId();
                l_lisBindVars.add(new Long(l_subAccountId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("__error in getSubAccount(�ۏ؋�)__", l_ex);
                log.exiting(l_strMethodName);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName,
                    l_ex.getMessage(),
                    l_ex);            
            }
        }        

        //�S�j�����^�C�v��������
        //����.�����^�C�v == null �̏ꍇ
        //1�i�����j�A3�i���M�j���P�j�̃��X�g�ɒǉ�����B
        
        //  ����.�����^�C�v != null �̏ꍇ
        //  ����.�����^�C�v���P�j�̃��X�g�ɒǉ�����B
        if (l_strProductType == null)
        {
            l_lisBindVars.add(ProductTypeEnum.EQUITY);
            l_lisBindVars.add(ProductTypeEnum.MUTUAL_FUND);
        }
        else
        {
            l_lisBindVars.add(l_strProductType);    
        } 

        //�T�j����ID��������
        //  ����.�����R�[�h != null �̏ꍇ
        //  AIO�v���_�N�g�}�l�[�W��.get����(�����^�C�v, �����R�[�h, �،����).getProductId()
        //  �̖߂�l���P�j�̃��X�g�ɒǉ�����B
        //  [get�����ɓn������]
        //  �����^�C�v = ����.�����^�C�v
        //  �����R�[�h = ����.�����R�[�h
        //  �،���� = ����.����.getInstitution()�̖߂�l      
        
        if (l_strProductCode != null)
        {
            //a> FinApp, TradingModule
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
            //b> AIO�v���_�N�g�}�l�[�W�����擾 
            WEB3AioProductManager l_productManager = 
                (WEB3AioProductManager)l_tradingModule.getProductManager();
                
            //productType
            ProductTypeEnum l_productTypeEnum;
        
            if (ProductTypeEnum.EQUITY.intValue() == Integer.parseInt(l_strProductType)) 
            {
                l_productTypeEnum = ProductTypeEnum.EQUITY;
            }
            else if (ProductTypeEnum.BOND.intValue() == Integer.parseInt(l_strProductType)) 
            {
                l_productTypeEnum = ProductTypeEnum.BOND;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.intValue() == Integer.parseInt(l_strProductType)) 
            {
                l_productTypeEnum = ProductTypeEnum.MUTUAL_FUND;
            }
            else 
            {
                l_productTypeEnum = ProductTypeEnum.FOREIGN_EQUITY;
            }
            
            //getInstitution
            Institution l_institution = l_mainAccount.getInstitution();
                
            //c> getProductId
            Product l_product = l_productManager.getProduct(
                l_productTypeEnum,
                l_strProductCode,
                l_institution);  
            long l_lngProductId = l_product.getProductId();    
            
                
            l_lisBindVars.add(new Long(l_lngProductId));      
        }

        //�U�j�~�j���敪��������
        //0�iDEFAULT�j���P�j�̃��X�g�ɒǉ�����B
        l_lisBindVars.add(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);

        //�V�j���X�g����z����擾���A�ԋp����B
        Object[] l_bindVars = new Object[l_lisBindVars.size()];
        l_lisBindVars.toArray(l_bindVars);
        
        log.exiting(l_strMethodName);
        
        return l_bindVars;
    }
    
    /**
     * (create�a��،�����)<BR>
     * �a��،����׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�،��U�֓��́jcreate�a��،����ׁv �Q��
     * @@param l_params - �ۗL���Y�f�[�^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit
     * @@throws WEB3BaseException
     * @@roseuid 4164C4B80385
     */
    protected WEB3AioSecurityTransferUnit createInstitutionBondDetails(DailyAssetParams l_params) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createInstitutionBondDetails(DailyAssetParams l_params)";
        log.entering(l_strMethodName);
        
        //1.1 ��̗a��،����׃C���X�^���X�𐶐�����B 
        WEB3AioSecurityTransferUnit l_web3AioSecurityTransferUnit =
            new WEB3AioSecurityTransferUnit();
            
        //1.2 �����I�u�W�F�N�g���擾����B 
        //[����] 
        //�����^�C�v�F ����.�ۗL���Y.�����^�C�v 
        //����ID�F ����.�ۗL���Y.����ID 
        
        //a> FinApp, TradingModule
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //b> AIO�v���_�N�g�}�l�[�W�����擾 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //c> productType
        ProductTypeEnum l_productTypeEnum = l_params.getProductType();   
        //d> productId
        long l_lngProductId = l_params.getProductId();     
                
        //e> getProduct
        Product l_product = 
            l_productManager.getProduct(l_productTypeEnum, l_lngProductId); 
        
        //1.3 �⏕�������擾����B 
        //[����] 
        //����ID�F ����.�ۗL���Y.����ID 
        //�⏕����ID�F ����.�ۗL���Y.�⏕����ID 
        
        //a> WEB3GentradeAccountManager
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //c> accountId 
        long l_lngAccountId = l_params.getAccountId();
        //d> subAccountId (SubAccountTypeEnum)
        long l_lngSubAccountId = l_params.getSubAccountId();
        
        //getsubaccount
        try
        {
            //throw NotFoundException
            SubAccount l_subAccount = 
                l_gentradeAccountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
                
            //1.4 �⏕�����^�C�v���擾����B
            SubAccountTypeEnum l_subAccountTypeEnum = l_subAccount.getSubAccountType();
            
            //1.5 calc�U�։\����  
            //[����] 
            //����ID�F ����.�ۗL���Y.����ID --l_lngAccountId
            //�����^�C�v�F ����.�ۗL���Y.�����^�C�v --l_productTypeEnum
            //����ID�F ����.�ۗL���Y.����ID --l_lngProductId
            //�ŋ敪�F ����.�ۗL���Y.�ŋ敪 
            //�a��敪�F 
            //�⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j 
            //�⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j 

            //a> get WEB3AioBizLogicProvider
            WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            //b> �ŋ敪
            TaxTypeEnum l_taxTypeEnum = l_params.getTaxType();
            
            //c> �a���
            String l_strDepositTypeDivDef = null;
            
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;    
            }
            else if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;    
            }
            
            //d> calc�U�։\���� 
            double l_dblTransPossibleAmount = 
                l_web3AioBizLogicProvider.calcTransPossibleAmount(
                    l_lngAccountId,
                    l_productTypeEnum,
                    l_lngProductId,
                    l_taxTypeEnum,
                    l_strDepositTypeDivDef);
                
            //1.6 �،��]���z���Z�o����B 
            //[����] 
            //����ID�F ����.�ۗL���Y.����ID --l_lngProductId
            //�a��敪�F --l_strDepositTypeDivDef
            //�⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j 
            //�⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j 
            //���ʁF calc�U�։\����()�̖߂�l --l_dblTransPossibleAmount

            double l_strStockEvalueAmount = 
                l_web3AioBizLogicProvider.calcStockEvalueAmount(
                    l_lngProductId,
                    l_strDepositTypeDivDef,
                    l_dblTransPossibleAmount);
                    
            //1.7 �ȉ��̂悤�Ƀv���p�e�B���Z�b�g����B
            //�a��،�����.���i�^�C�v = ����.�ۗL���Y.�����^�C�v --l_productTypeEnum
            //�a��،�����.�����R�[�h = �i�ȉ��̂Ƃ���j
            //  ���i�^�C�v = �h���h �̏ꍇ�A����.�񍆃R�[�h(SONAR) + ����.�����R�[�h(SONAR)�̏�4��
            //  ���i�^�C�v != �h���h �̏ꍇ�A����.getProductCode()�̖߂�l
            //�a��،�����.������ = ����.getProductName()�̖߂�l
            //�a��،�����.�����敪 = �i�ȉ��̂Ƃ���j
            //     ����.�ۗL���Y.�ŋ敪 = 0�i���̑��j �̏ꍇ�Anull
            //     ����.�ۗL���Y.�ŋ敪 = 1�i��ʁj �̏ꍇ�A0�i��ʁj
            //     ����.�ۗL���Y.�ŋ敪 = 2�i����j or 3�i���肩���򒥎��j �̏ꍇ�A1�i����j
            //�a��،�����.���� = ���o���v�Z�T�[�r�X.calc�U�։\����()�̖߂�l --l_dblTransPossibleAmount
            //�a��،�����.�]���z = ���o���v�Z�T�[�r�X.calc�،��]���z()�̖߂�l --l_strStockEvalueAmount
            //�a��،�����.�a��敪 = �i�ȉ��̂Ƃ���j--l_strDepositTypeDivDef
            //     �⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j
            //     �⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j
            
            //a> �a��،�����.���i�^�C�v                       
            //b> �a��،�����.�����R�[�h
            if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.EQUITY.intValue());
                //�����R�[�h
                l_web3AioSecurityTransferUnit.productCode =
                    ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            //���i�^�C�v = �h���h �̏ꍇ�A����.�񍆃R�[�h(SONAR) + ����.�����R�[�h(SONAR)�̏�4��
            else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.BOND.intValue());
                //����.�����R�[�h(SONAR)�̏�4��      
                String l_strHostProductCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                //����.�񍆃R�[�h(SONAR)
                String l_strHostIssueCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductIssueCode();
                l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                
                l_web3AioSecurityTransferUnit.productCode = l_strHostProductCode;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.MUTUAL_FUND.intValue());
                //�����R�[�h
                l_web3AioSecurityTransferUnit.productCode =
                    ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    
                    String.valueOf(ProductTypeEnum.FOREIGN_EQUITY.intValue());
                //�����R�[�h
                l_web3AioSecurityTransferUnit.productCode =
                    ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            
            //c> �a��،�����.������
            l_web3AioSecurityTransferUnit.productName = l_product.getStandardName();
            
            //d> �a��،�����.�����敪
            if (TaxTypeEnum.UNDEFINED.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = null;            
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.NORMAL;            
            }
            else 
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.SPECIAL;            
            }

            //e> �a��،�����.����
            l_web3AioSecurityTransferUnit.changePossQuantity = WEB3StringTypeUtility.formatNumber(l_dblTransPossibleAmount);
            
            //f> �a��،�����.�]���z
            l_web3AioSecurityTransferUnit.marketValue = WEB3StringTypeUtility.formatNumber(l_strStockEvalueAmount) ;
            
            //g> �a��،�����.�a��敪
            l_web3AioSecurityTransferUnit.depositDiv = l_strDepositTypeDivDef;            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in getSubAccount__", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);            
        }
        
        log.exiting(l_strMethodName);
        
        return l_web3AioSecurityTransferUnit;
    }
    
    /**
     * (create�a��،�����)<BR>
     * �a��،����׃I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�،��U�֓��́jcreate�a��،����ׂQ�v �Q��
     * @@param l_params - �����P�ʃf�[�^
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit
     * @@throws WEB3BaseException
     * @@roseuid 4164C4B80385
     */
    protected WEB3AioSecurityTransferUnit createInstitutionBondDetails(AioOrderUnitParams l_params) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createInstitutionBondDetails(AioOrderUnitParams l_params)";
        log.entering(l_strMethodName);
        
        //1.1 ��̗a��،����׃C���X�^���X�𐶐�����B 
        WEB3AioSecurityTransferUnit l_web3AioSecurityTransferUnit =
            new WEB3AioSecurityTransferUnit();
            
        //1.2 �����I�u�W�F�N�g���擾����B 
        //[����] 
        //�����^�C�v�F ����.�����P��.�����^�C�v 
        //����ID�F ����.�����P��.����ID 

        //a> FinApp, TradingModule
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        //b> AIO�v���_�N�g�}�l�[�W�����擾 
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
            
        //c> productType
        ProductTypeEnum l_productTypeEnum = l_params.getProductType();   
        //d> productId
        long l_lngProductId = l_params.getProductId();     
                
        //e> getProduct
        Product l_product = 
            l_productManager.getProduct(l_productTypeEnum, l_lngProductId); 
        
        //1.3 �⏕�������擾����B 
        //[����] 
        //����ID�F ����.�����P��.����ID 
        //�⏕����ID�F ����.�����P��.�⏕����ID 
        
        //a> WEB3GentradeAccountManager
        WEB3GentradeAccountManager l_gentradeAccountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        //c> accountId 
        long l_lngAccountId = l_params.getAccountId();
        //d> subAccountId (SubAccountTypeEnum)
        long l_lngSubAccountId = l_params.getSubAccountId();
        
        //getsubaccount
        try
        {
            //throw NotFoundException
            SubAccount l_subAccount = 
                l_gentradeAccountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
                
            //1.4 �⏕�����^�C�v���擾����B
            SubAccountTypeEnum l_subAccountTypeEnum = l_subAccount.getSubAccountType();
            
            //1.5 calc�U�։\����  
            //[����] 
            //����ID�F ����.�����P��.����ID 
            //�����^�C�v�F ����.�����P��.�����^�C�v 
            //����ID�F ����.�����P��.����ID 
            //�ŋ敪�F ����.�����P��.�ŋ敪 
            //�a��敪�F 
            //�⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j 
            //�⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j  

            //a> get WEB3AioBizLogicProvider
            WEB3AioBizLogicProvider l_web3AioBizLogicProvider = 
                (WEB3AioBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
            //b> �ŋ敪
            TaxTypeEnum l_taxTypeEnum = l_params.getTaxType();
            
            //c> �a���
            String l_strDepositTypeDivDef = null;
            
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.SAFE_DEPOSIT;    
            }
            else if (SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_strDepositTypeDivDef = WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY;    
            }
            
            //d> calc�U�։\���� 
            double l_dblTransPossibleAmount = 
                l_web3AioBizLogicProvider.calcTransPossibleAmount(
                    l_lngAccountId,
                    l_productTypeEnum,
                    l_lngProductId,
                    l_taxTypeEnum,
                    l_strDepositTypeDivDef);
                
            //1.6 �،��]���z���Z�o����B 
            //[����] 
            //����ID�F ����.�����P��.����ID 
            //�ŋ敪�F ����.�����P��.�ŋ敪 
            //�a��敪�F 
            //�⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j 
            //�⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j 
            //���ʁF calc�U�։\����()�̖߂�l 

            double l_strStockEvalueAmount = 
                l_web3AioBizLogicProvider.calcStockEvalueAmount(
                    l_lngProductId,
                    l_strDepositTypeDivDef,
                    l_dblTransPossibleAmount);
                    
            //1.7 �ȉ��̂悤�Ƀv���p�e�B���Z�b�g����B
            //�a��،�����.���i�^�C�v = ����.�����P��.�����^�C�v --l_productTypeEnum
            //�a��،�����.�����R�[�h = �i�ȉ��̂Ƃ���j
            //  ���i�^�C�v = �h���h �̏ꍇ�A����.�񍆃R�[�h(SONAR) + ����.�����R�[�h(SONAR)�̏�4��
            //  ���i�^�C�v != �h���h �̏ꍇ�A����.getProductCode()�̖߂�l
            //�a��،�����.������ = ����.getProductName()�̖߂�l
            //�a��،�����.�����敪 = �i�ȉ��̂Ƃ���j
            //     ����.�����P��.�ŋ敪 = 0�i���̑��j �̏ꍇ�Anull
            //     ����.�����P��.�ŋ敪 = 1�i��ʁj �̏ꍇ�A0�i��ʁj
            //     ����.�����P��.�ŋ敪 = 2�i����j or 3�i���肩���򒥎��j �̏ꍇ�A1�i����j
            //�a��،�����.���� = ���o���v�Z�T�[�r�X.calc�U�։\����()�̖߂�l --l_dblTransPossibleAmount
            //�a��،�����.�]���z = ���o���v�Z�T�[�r�X.calc�،��]���z()�̖߂�l --l_strStockEvalueAmount
            //�a��،�����.�a��敪 = �i�ȉ��̂Ƃ���j--l_strDepositTypeDivDef
            //     �⏕�����^�C�v = 1�i�a����j �̏ꍇ�A1�i�ی�j
            //     �⏕�����^�C�v = 2�i�ۏ؋��j �̏ꍇ�A2�i��p�j
            
            //a> �a��،�����.���i�^�C�v                       
            //b> �a��،�����.�����R�[�h
            if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.EQUITY.intValue());
                //�����R�[�h
                l_web3AioSecurityTransferUnit.productCode =
                    ((EqtypeProductRow)l_product.getDataSourceObject()).getProductCode();    
            }
            //���i�^�C�v = �h���h �̏ꍇ�A����.�񍆃R�[�h + ����.�����R�[�h�̏�4��
            else if (ProductTypeEnum.BOND.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.BOND.intValue());
                //����.�����R�[�h(SONAR)�̏�4��      
                String l_strHostProductCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductCode().substring(0, 4);
                //����.�񍆃R�[�h(SONAR)
                String l_strHostIssueCode = ((BondProductRow)
                    l_product.getDataSourceObject()).getHostProductIssueCode();
                l_strHostProductCode = l_strHostIssueCode + l_strHostProductCode;
                
                l_web3AioSecurityTransferUnit.productCode = l_strHostProductCode;
            }
            else if (ProductTypeEnum.MUTUAL_FUND.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    String.valueOf(ProductTypeEnum.MUTUAL_FUND.intValue());
                //�����R�[�h
                l_web3AioSecurityTransferUnit.productCode =
                    ((MutualFundProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
            {
                //���i�^�C�v 
                l_web3AioSecurityTransferUnit.instrumentsType = 
                    
                    String.valueOf(ProductTypeEnum.FOREIGN_EQUITY.intValue());
                //�����R�[�h
                l_web3AioSecurityTransferUnit.productCode =
                    ((FeqProductRow)l_product.getDataSourceObject()).getProductCode();
            }
            
            //c> �a��،�����.������
            l_web3AioSecurityTransferUnit.productName = l_product.getStandardName();
            
            //d> �a��،�����.�����敪
            if (TaxTypeEnum.UNDEFINED.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = null;            
            }
            else if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.NORMAL;            
            }
            else 
            {
                l_web3AioSecurityTransferUnit.taxType = WEB3AccountDivDef.SPECIAL;            
            }

            //e> �a��،�����.����
            l_web3AioSecurityTransferUnit.changePossQuantity = WEB3StringTypeUtility.formatNumber(l_dblTransPossibleAmount);
            
            //f> �a��،�����.�]���z
            l_web3AioSecurityTransferUnit.marketValue = WEB3StringTypeUtility.formatNumber(l_strStockEvalueAmount) ;
            
            //g> �a��،�����.�a��敪
            l_web3AioSecurityTransferUnit.depositDiv = l_strDepositTypeDivDef;            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in getSubAccount__", l_ex);
            log.exiting(l_strMethodName);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName,
                l_ex.getMessage(),
                l_ex);            
        }
        
        log.exiting(l_strMethodName);
        
        return l_web3AioSecurityTransferUnit;
    }
    
    /**
     * (sort�a��،�����)<BR>
     * �a��،����ׂ̔z����\�[�g�L�[�ɏ]���ă\�[�g����B<BR>
     * <BR>
     * �P�j���ArrayList�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�j�\�[�g�L�[�̔z��̊e�v�f�ɂ��Ĉȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j�\�[�g�L�[.�L�[���� = "���i�^�C�v�h�̏ꍇ<BR>
     * <BR>
     *   �E���i�^�C�vComparator�𐶐�����B<BR>
     *     ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B<BR>
     * <BR>
     *   �E��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �Q�|�Q�j�\�[�g�L�[.�L�[���� = "�����R�[�h�h�̏ꍇ<BR>
     * <BR>
     *   �E�����R�[�hComparator�𐶐�����B<BR>
     *     ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B<BR>
     * <BR>
     *   �E��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �Q�|�R�j�\�[�g�L�[.�L�[���� = "�����敪�h�̏ꍇ<BR>
     * <BR>
     *   �E�����敪Comparator�𐶐�����B<BR>
     *     ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B<BR>
     * <BR>
     *   �E��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �Q�|�S�j�\�[�g�L�[.�L�[���� = "���ʁh�̏ꍇ<BR>
     * <BR>
     *   �E����Comparator�𐶐�����B<BR>
     *     ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B<BR>
     * <BR>
     *   �E��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �Q�|�T�j�\�[�g�L�[.�L�[���� = "�]���z�h�̏ꍇ<BR>
     * <BR>
     *   �E�]���zComparator�𐶐�����B<BR>
     *     ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B<BR>
     * <BR>
     *   �E��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �Q�|�U�j�\�[�g�L�[.�L�[���� = "�a��敪�h�̏ꍇ<BR>
     * <BR>
     *   �E�a��敪Comparator�𐶐�����B<BR>
     *     ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B<BR>
     * <BR>
     *   �E��������Comparator��List�ɒǉ�����B<BR>
     * <BR>
     * �R�jArrayList����Comparator�̔z����擾����B<BR>
     * <BR>
     * �S�jComparator�̔z�񏇂̃\�[�g�������s���B<BR>
     *    WEB3ArraysUtility.sort(����.����, Comparator[]) <BR>
     * <BR>
     * �T�j�\�[�g���ꂽ���ׂ̔z���ԋp����B
     * @@param l_securityTransferUnit - �a��،����ׂ̔z��
     * @@param l_securityTransferSortKeyUnit - �،��U�փ\�[�g�L�[�̔z��
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit[]
     * @@roseuid 4164C67303C3
     */
    protected WEB3AioSecurityTransferUnit[] sortInstitutionBondDetails(
        WEB3AioSecurityTransferUnit[] l_securityTransferUnit, 
        WEB3AioSecurityTransferSortKeyUnit[] l_securityTransferSortKeyUnit) 
    {
        String l_strMethodName = 
            "sortInstitutionBondDetails(WEB3AioSecurityTransferUnit[] l_securityTransferUnit, WEB3AioSecurityTransferSortKeyUnit[] l_securityTransferSortKeyUnit)";
        log.entering(l_strMethodName);
        
        //�P�j���ArrayList�C���X�^���X�𐶐�����B 
        List l_lisSecurityTransferUnit = new ArrayList();

        //�Q�j�\�[�g�L�[�̔z��̊e�v�f�ɂ��Ĉȉ��̏������s���B
        if (l_securityTransferSortKeyUnit == null)
        {
            log.debug("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);            
        }
        
        for (int i = 0; i < l_securityTransferSortKeyUnit.length; i++)
        {
            //�Q�|�P�j�\�[�g�L�[.�L�[���� = "���i�^�C�v�h�̏ꍇ
            //  �E���i�^�C�vComparator�𐶐�����B
            //    ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B
            //  �E��������Comparator��List�ɒǉ�����B
            if (WEB3AioTransferSortkeyDef.INSTRUMENTS_TYPE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioInstrumentsTypeComparator l_instrumentsTypeComparator =
                    new WEB3AioInstrumentsTypeComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_instrumentsTypeComparator);
            }
 
            //�Q�|�Q�j�\�[�g�L�[.�L�[���� = "�����R�[�h�h�̏ꍇ
            //  �E�����R�[�hComparator�𐶐�����B
            //    ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B
            //  �E��������Comparator��List�ɒǉ�����B
            if (WEB3AioTransferSortkeyDef.PRODUCT_CODE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioProductCodeComparator l_productCodeComparator =
                    new WEB3AioProductCodeComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_productCodeComparator);    
            }            

            //�Q�|�R�j�\�[�g�L�[.�L�[���� = "�����敪�h�̏ꍇ
            //  �E�����敪Comparator�𐶐�����B
            //    ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B
            //  �E��������Comparator��List�ɒǉ�����B
            if (WEB3AioTransferSortkeyDef.TAX_TYPE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioTaxTypeComparator l_taxTypeComparator =
                    new WEB3AioTaxTypeComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_taxTypeComparator);    
            }

            //�Q�|�S�j�\�[�g�L�[.�L�[���� = "���ʁh�̏ꍇ
            //  �E����Comparator�𐶐�����B
            //    ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B
            //  �E��������Comparator��List�ɒǉ�����B
            if (WEB3AioTransferSortkeyDef.CHANGE_POSS_QUANTITY.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioChangePossQuantityComparator l_changePossQuantityComparator =
                    new WEB3AioChangePossQuantityComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_changePossQuantityComparator);    
            }

            //�Q�|�T�j�\�[�g�L�[.�L�[���� = "�]���z�h�̏ꍇ
            //  �E�]���zComparator�𐶐�����B
            //    ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B
            //  �E��������Comparator��List�ɒǉ�����B
            if (WEB3AioTransferSortkeyDef.MARKET_VALUE.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioMarketValueComparator l_marketValueComparator =
                    new WEB3AioMarketValueComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_marketValueComparator);    
            }

            //�Q�|�U�j�\�[�g�L�[.�L�[���� = "�a��敪�h�̏ꍇ
            //  �E�a��敪Comparator�𐶐�����B
            //    ����.orderBy�ɂ́A�\�[�g�L�[.����/�~����ݒ肷��B
            //  �E��������Comparator��List�ɒǉ�����B
            if (WEB3AioTransferSortkeyDef.DEPOSIT_DIV.equals(l_securityTransferSortKeyUnit[i].keyItem))
            {
                WEB3AioDepositDivComparator l_depositDivComparator =
                    new WEB3AioDepositDivComparator(l_securityTransferSortKeyUnit[i].ascDesc);
                //list.add    
                l_lisSecurityTransferUnit.add(l_depositDivComparator);    
            }            
        }

        //�R�jArrayList����Comparator�̔z����擾����B
        Comparator[] l_comparator = 
            new Comparator[l_lisSecurityTransferUnit.size()];
        //toArray
        l_lisSecurityTransferUnit.toArray(l_comparator); 

        //�S�jComparator�̔z�񏇂̃\�[�g�������s���B
        //   WEB3ArraysUtility.sort(����.����, Comparator[]) 
        WEB3ArraysUtility.sort(l_securityTransferUnit, l_comparator);

        //�T�j�\�[�g���ꂽ���ׂ̔z���ԋp����B        
        
        log.exiting(l_strMethodName);
        
        return l_securityTransferUnit;
    }
    
    /**
     * (get�\������)<BR>
     * �a��،����ׂ̔z�񂩂�v���y�[�W���ɕ\������閾�ׂ̔z����擾���ĕԋp����B<BR>
     * <BR>
     * �P�j���ArrayList�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�a��،�����[����.fromIndex]����a��،����ׁm����.toIndex]�̗v�f���A<BR>
     *     �P�j��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�jArrayList����z����擾����B<BR>
     * <BR>
     * �S�j���������z���ԋp����B
     * @@param l_securityTransferUnit - �a��،����ׂ̔z��
     * @@param l_intFromIndex - �\���Ώۂ̊J�n�ʒu�̃C���f�b�N�X�ԍ�
     * @@param l_intToIndex - �\���Ώۂ̏I���ʒu�̃C���f�b�N�X�ԍ�
     * @@return webbroker3.aio.message.WEB3AioSecurityTransferUnit[]
     * @@roseuid 4164C9CC026C
     */
    protected WEB3AioSecurityTransferUnit[] getIndicationDetails(WEB3AioSecurityTransferUnit[] l_securityTransferUnit, int l_intFromIndex, int l_intToIndex) 
    {        
        return null;
    }
}
@
