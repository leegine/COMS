head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�ɒʒmUnitServiceImpl(WEB3AioOutputNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 ��O�� (���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import webbroker3.aio.WEB3AioProductManager;
import webbroker3.aio.define.WEB3HostTransferPaymentStatusDef;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CustdyDiv;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3AioHostSpsecCommodityDef;
import webbroker3.common.define.WEB3CancelDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

/**
 * (�o�ɒʒmUnitServiceImpl) <BR>
 * �o�ɒʒmUnitService�����N���X <BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor( <BR>
 * TransactionalInterceptor.TX_CREATE_NEW)���w�肷��B<BR>
 * <BR>
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyUnitServiceImpl implements WEB3AioOutputNotifyUnitService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyUnitServiceImpl.class); 
    
    /**
     * (notify�o�ɒʒm)<BR>
     * �o�ɒʒm�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o�ɒʒm�jnotify�o�ɒʒm�v �Q��<BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strCustodyDiv - �ۊǋ敪
     * @@param l_strSpecificDiv - ��������敪
     * @@param l_lngQuantity - ����
     * @@param l_strCommodityDiv - ���i�敪
     * @@param l_strCancelDiv - ����敪
     * @@return String
     * @@roseuid 4157961901F5
     */
    public String notifyOutputNotify(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        String l_strProductCode, 
        String l_strCustodyDiv, 
        String l_strSpecificDiv, 
        Long l_lngQuantity,
	    String l_strCommodityDiv,
	    String l_strCancelDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyOutputNotify(" +
            "String, String, String, String, String, String, Long)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 InstitutionImpl(String)

        //�،���ЃI�u�W�F�N�g���擾����B 
        //[����] 
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        
        WEB3GentradeInstitution l_institution = null;
        try
        {
            l_institution = new WEB3GentradeInstitution(l_strInstitutionCode);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.2 get����(ProductTypeEnum, String, Institution)
        //�������擾����B 

        //[����] 
        //�����^�C�v�F 1�i�����j 
        //�����R�[�h�F ����.�����R�[�h 
        //�،���ЁF �،���ЃI�u�W�F�N�g
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.AIO);
        
        WEB3AioProductManager l_productManager = 
            (WEB3AioProductManager)l_tradingModule.getProductManager();
        
        Product l_product = l_productManager.getProduct(
            ProductTypeEnum.EQUITY, 
            l_strProductCode, 
            l_institution);
        
        //1.3 MainAccountImpl(long, String, String)
        //�ڋq�I�u�W�F�N�g���擾����B  

        //[����]  
        //�،����ID�F �،����.getInstitutionId()�̖߂�l  
        //���X�R�[�h�F ����.���X�R�[�h  
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h 
        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(
                l_institution.getInstitutionId(), 
                l_strBranchCode, 
                l_strAccountCode);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME + " __Error[DB�T�[�o�Ƃ̒ʐM�Ɏ��s����]__" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���" , l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.4 is�M�p�����J��(�ٍϋ敪 : String)
        //�⏕�������擾����B  

		//�M�p�������J�݂��Ă��邩�̃`�F�b�N���s���B 
		
		//[����] 
		//�ٍϋ敪�F 0�i�w��Ȃ��j 
        
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        
        SubAccount l_subAccount = null;
        try
        {
            MainAccount l_mainAccount = 
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(), l_strBranchCode, l_strAccountCode);
			WEB3GentradeMainAccount l_gentradeMainAcc = 
				(WEB3GentradeMainAccount)l_mainAccount;
            //1.5 is�M�p�����J��( ) == true�̏ꍇ
            if (l_gentradeMainAcc.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
				//1.5.1 getSubAccount(SubAccountTypeEnum)
				//�⏕�������擾����B  

				//[����]  
				//�⏕�����^�C�v�F�@@SubAccountTypeEnum.�����M�p��������i�ۏ؋��j
				l_subAccount = l_gentradeMainAcc.getSubAccount(
					SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            //1.6 is�M�p�����J��( ) == false�̏ꍇ
            else
            {
				//1.6.1 getSubAccount(SubAccountTypeEnum)
				//�⏕�������擾����B  

				//[����]  
				//�⏕�����^�C�v�F�@@SubAccountTypeEnum.SubAccountTypeEnum.������������i�a����j
				l_subAccount = l_gentradeMainAcc.getSubAccount(
					SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�،���ЃC���X�^���X�𐶐�����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.7 is�o�ɑΏۃf�[�^ (String �ۊǋ敪)
        //�o�ɑΏۃf�[�^�̕ۊǋ敪�̃`�F�b�N���s���B
        //[����] �ۊǋ敪�F ����.�ۊǋ敪
         
         boolean l_blnOutputObjData = this.isOutputObjData(l_strCustodyDiv);
       
        //1.8 is�o�ɑΏۃf�[�^�̌��ʂ��ufalse�v�̏ꍇ�A
        //�u�Q�i�o�ɑΏۃf�[�^�ΏۊO�v��ԋp����B
        if (!l_blnOutputObjData)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT;
        }
        
        //1.9 is���i�敪(String ���i�敪)
        //�o�ɑΏۃf�[�^�̏��i�敪�̃`�F�b�N���s���B
        //[����] ���i�敪�F ����.���i�敪
        
        boolean l_blnCommodityDiv = this.isCommodityDiv(l_strCommodityDiv);
        
		//1.10 is���i�敪()�̌��ʂ��ufalse�v�̏ꍇ�A
		//�u�Q�i�o�ɑΏۃf�[�^�ΏۊO�v��ԋp����B
		if (!l_blnCommodityDiv)
		{
			log.exiting(STR_METHOD_NAME);
			return WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT;
		}
		
		//1.11 is����敪(String ����敪)
		//�o�ɑΏۃf�[�^�̏��i�敪�̃`�F�b�N���s���B
		//[����] ����敪�F ����.����敪
        
		boolean l_blnCancelDiv = this.isCancelDiv(l_strCancelDiv);
        
		//1.12 is����敪()�̌��ʂ��ufalse�v�̏ꍇ�A
		//�u�Q�i�o�ɑΏۃf�[�^�ΏۊO�v��ԋp����B
		if (!l_blnCancelDiv)
		{
			log.exiting(STR_METHOD_NAME);
			return WEB3HostTransferPaymentStatusDef.DEAL_EXCEPT_OBJECT;
		}
                
        //1.13 update�ۗL���Y(Long, Long, Long, String, String, Long)

        //�ۗL���Y�e�[�u�����X�V����B  

        //[����]  
        //����ID�F�⏕����.getMainAccount().����ID 
        //�⏕����ID�F�⏕����.�⏕����ID 
        //����ID�F�iget����()�̖߂�l�j.����ID 
        //�ŋ敪�F����.��������敪 
        //�~�j���敪�F0�idefault�j 
        //���ʁF����.����
        
        boolean l_blnUpdateAsset = this.updateAsset(
            new Long(l_subAccount.getMainAccount().getAccountId()), 
            new Long(l_subAccount.getSubAccountId()), 
            new Long(l_product.getProductId()), 
            l_strSpecificDiv, 
            WEB3MiniStockDivDef.DEFAULT_MINI_STOCK, 
            l_lngQuantity);

        //1.14 update�ۗL���Y�i�j�̖߂�l���A�utrue�v�̏ꍇ�́A
        //�u�P�F�i�����ρj�v��ԋp����B
        //update�ۗL���Y�i�j�̖߂�l���A�ufalse�v�̏ꍇ�́A
        //�u�R�F�i�Y������ۗL���Y�Ȃ��j�v��ԋp����B
        
        if (l_blnUpdateAsset)
        {
            log.debug("update�ۗL���Y�i�j�̖߂�l���A�utrue�v�̏ꍇ");
            log.debug("notify�o�ɒʒm = " + WEB3HostTransferPaymentStatusDef.DEALT);
            
            log.exiting(STR_METHOD_NAME);
            return WEB3HostTransferPaymentStatusDef.DEALT;
        }
        else
        {
            log.debug("update�ۗL���Y�i�j�̖߂�l���A�ufalse�v�̏ꍇ");
            log.debug("notify�o�ɒʒm = " + WEB3HostTransferPaymentStatusDef.NO_ASSET);
            
            log.exiting(STR_METHOD_NAME);
            return WEB3HostTransferPaymentStatusDef.NO_ASSET;
        }
    }

    /**
     * (is�o�ɑΏۃf�[�^)<BR>
     * �o�ɑΏۃf�[�^�̕ۊǋ敪�̃`�F�b�N���s���B<BR>
     * <BR>
     *�P�j�ۊǋ敪�`�F�b�N 
     *�@@�i�P�j����.�ۊǋ敪=�h8�i�@@�\�j�h or �h1�i�W���ۊǁh�̏ꍇ�́A�utrue�v��ԋp����B<BR>
     *�@@�i�Q�j��L�ɓ��Ă͂܂�Ȃ��ꍇ�A�ufalse�v��ԋp����B<BR>
     * <BR>
     * @@param l_strCustodyDiv - �ۊǋ敪
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    protected boolean isOutputObjData(String l_strCustodyDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOutputObjData(String l_strCustodyDiv)";
        log.entering(STR_METHOD_NAME);
    
        //�P�j�o�ɑΏۃf�[�^�`�F�b�N 
        //�i�P�j����.�ۊǋ敪=�h8�i�@@�\�j�h or �h1�i�W���ۊǁj�h�̏ꍇ�́A 
        //�utrue�v��ԋp����B 
        if (WEB3CustdyDiv.INSTITUTE_SAVE.equals(l_strCustodyDiv) 
           || WEB3CustdyDiv.INTERNAL_SAVE.equals(l_strCustodyDiv))
        {
            log.debug("is�o�ɑΏۃf�[�^ = " + true);
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //�i�Q�j��L�ɓ��Ă͂܂�Ȃ��ꍇ�A�ufalse�v��ԋp����B
        else
        {
            log.debug("is�o�ɑΏۃf�[�^ = " + false);
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (update�ۗL���Y)<BR>
     * �ۗL���Y�̐��ʂƔ��t�s�\�������X�V����B<BR>  
     * <BR>
     * �P�j�ŋ敪�̐ݒ� <BR>
     * �@@  ����.��������敪 = "�O�i��ʁj"�̏ꍇ�A"�P�F�i��ʁj"��ݒ� <BR>
     * <BR>
     * �@@�@@����,��������敪 = "�P�i����j"�̏ꍇ�A"�Q�F�i����j"��ݒ� <BR>
     * �@@�@@����.��������敪 �� "�O�i��ʁj" or  "�P�i����j"�łȂ��ꍇ�́A <BR>
     * �@@�@@�ufalse�v��ԋp����B <BR>
     * <BR>
     * �Q�j�ΏۂƂȂ�ۗL���Y���R�[�h���擾����B  <BR>
     * <BR>
     * [�擾����]  <BR>
     *  ����ID = ����.����ID  <BR>
     *  �⏕����ID = ����.�⏕����ID <BR>
     *  ����ID = ����.����ID  <BR>
     *  �ŋ敪 = �P�j�Őݒ肵���ŋ敪 <BR>
     *  �~�j���敪 = ����.�~�j���敪  <BR>
     * <BR>
     * �R�j�ۗL���Y���R�[�h���X�V�i�o�^�j����B  <BR>
     * <BR>
     * �R�|�P�j�ۗL���Y���R�[�h���擾�ł��Ȃ������ꍇ  <BR>
     *        �ufalse�v��ԋp����B <BR>
     * <BR>
     * �R�|�Q�j�ۗL���Y���R�[�h���擾�ł����ꍇ  <BR>
     * <BR>
     *        �ۗL���Y���R�[�h���X�V���A�utrue�v��ԋp����B <BR>
     *        �ȉ��̍��ڂƒl��DB�X�V���s���B <BR>
     * <BR>
     * �R�|�Q�|�P�j�ۗL���Y.���� �� ����.���ʂ̏ꍇ <BR>
     * <BR>
     *          �ۗL���Y.���� �@@�@@�@@�@@�@@�@@= �ۗL���Y.���� - ����.���� <BR>
     *          �ۗL���Y.���t�s�\���� = �ۗL���Y.���t�s�\���� + ����.���� <BR>
     *          �ۗL���Y.�X�V���t = ���ݎ��� <BR>
     *
     * �R�|�Q�|�P�j�ۗL���Y.���� �� ����.���ʂ̏ꍇ <BR>
     *
     *          �ۗL���Y.���� �@@�@@�@@�@@�@@�@@= 0 <BR>
     *          �ۗL���Y.���t�s�\���� = �ۗL���Y.���t�s�\���� + �ۗL���Y.���ʁi���P�j <BR>
     *          �ۗL���Y.�X�V���t = ���ݎ��� <BR>
     * <BR>
     * �i���P�j�ۗL���Y.���ʂ́A�擾�������ʁi�����ʁj�Ƃ���B <BR>
     * <BR>
     * @@param l_lngAccountId - ����ID
     * @@param l_lngSubAccountId - �⏕����ID
     * @@param l_lngProductId - ����ID
     * @@param l_strTaxType - �ŋ敪
     * @@param l_strMiniStockDiv - �~�j���敪
     * @@param l_lngQuantity - ����
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4157977900AD
     */
    protected boolean updateAsset(
        Long l_lngAccountId, 
        Long l_lngSubAccountId, 
        Long l_lngProductId, 
        String l_strTaxType, 
        String l_strMiniStockDiv, 
        Long l_lngQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateAsset(" +
            "Long, Long, Long, String, String, Long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ŋ敪�̐ݒ� 
        // ����.��������敪 = "�O�i��ʁj"�̏ꍇ�A"�P�F�i��ʁj"��ݒ� 
        // ����,��������敪 = "�P�i����j"�̏ꍇ�A"�Q�F�i����j"��ݒ� 
        // ����.��������敪 �� "�O�i��ʁj" or  "�P�i����j"�łȂ��ꍇ�́A 
        //�ufalse�v��ԋp����B 
        
        TaxTypeEnum l_taxType = null;
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
        {
            l_taxType = TaxTypeEnum.NORMAL;
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType)) 
        {
            l_taxType = TaxTypeEnum.SPECIAL;
        }
        
        if (!(WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType) || 
            WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType)))
        {
            log.debug("����.��������敪 �� '�O�i��ʁj' or  '�P�i����j'�łȂ��ꍇ");
            log.debug("update�ۗL���Y = " + false);
            
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�ΏۂƂȂ�ۗL���Y���R�[�h���擾����B  

        //[�擾����]  
        // ����ID = ����.����ID  
        // �⏕����ID = ����.�⏕����ID  
        // ����ID = ����.����ID  
        // �ŋ敪 = �P�j�Őݒ肵���ŋ敪 
        // �~�j���敪 = ����.�~�j���敪
        List l_lisAssetRows = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisAssetRows = l_queryProcessor.doFindAllQuery(
                AssetRow.TYPE,
                "account_id = ? and sub_account_id = ? and " +
                "product_id = ? and tax_type = ? and mini_stock_div = ?",
                null,
                new Object[] {
                    l_lngAccountId, 
                    l_lngSubAccountId, 
                    l_lngProductId, 
                    l_taxType, 
                    l_strMiniStockDiv
                    });
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�R�j�ۗL���Y���R�[�h���X�V�i�o�^�j����B  

        //�R�|�P�j�ۗL���Y���R�[�h���擾�ł��Ȃ������ꍇ  
        //       �ufalse�v��ԋp����B 
        if (l_lisAssetRows == null || l_lisAssetRows.size() == 0)
        {
            log.debug("�ۗL���Y���R�[�h���擾�ł��Ȃ������ꍇ");
            log.debug("update�ۗL���Y = " + false);
            
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�R�|�Q�j�ۗL���Y���R�[�h���擾�ł����ꍇ  
        //       �ۗL���Y���R�[�h���X�V���A�utrue�v��ԋp����B 
        //       �ȉ��̍��ڂƒl��DB�X�V���s���B 

        //�@@�@@�@@ �ۗL���Y.���� �@@�@@�@@�@@�@@�@@= �ۗL���Y.���� - ����.���� 
        //�@@�@@�@@ �ۗL���Y.���t�s�\���� = �ۗL���Y.���t�s�\���� + ����.����  
        //�@@�@@�@@ �ۗL���Y.�X�V���t       = ���ݎ��� 
        
        AssetRow l_assetRow = null;
        AssetParams l_assetParams = null;
        
        if (l_lisAssetRows.size() == 1)
        {            
            log.debug("�ۗL���Y���R�[�h���擾Size() = " + l_lisAssetRows.size());
            l_assetRow = (AssetRow)l_lisAssetRows.get(0);
            l_assetParams = new AssetParams(l_assetRow);
            
            log.debug("�ۗL���Y���R�[�h = " + l_assetParams);

            double l_dblQuangtity = Double.parseDouble(l_lngQuantity.toString());
            
			//�R�|�Q�|�P�j�ۗL���Y.���� �� ����.���ʂ̏ꍇ 
            if(Double.compare(l_assetParams.getQuantity(),l_dblQuangtity) >= 0)
            {
                //�ۗL���Y.���� = �ۗL���Y.���� - ����.���� 
                l_assetParams.setQuantity( l_assetParams.getQuantity() - l_dblQuangtity);
                //�ۗL���Y.���t�s�\���� = �ۗL���Y.���t�s�\���� + ����.����
                l_assetParams.setQuantityCannotSell(l_assetParams.getQuantityCannotSell() + l_dblQuangtity);
            }
            else
            {
				//�R�|�Q�|�Q�j�ۗL���Y.���� �� ����.���ʂ̏ꍇ
                //�ۗL���Y.���t�s�\���� = �ۗL���Y.���t�s�\���� + �ۗL���Y.����
                l_assetParams.setQuantityCannotSell(l_assetParams.getQuantityCannotSell() + l_assetParams.getQuantity());
                //�ۗL���Y.����= 0
                l_assetParams.setQuantity(0);
            }

            //�ۗL���Y.�X�V���t = ���ݎ���
            l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                
                // do update
                l_queryProcessor.doUpdateQuery(l_assetParams);
            }
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }           
        }
        
        log.debug("�ۗL���Y���R�[�h���擾�ł����ꍇ�ۗL���Y���R�[�h���X�V");
        log.debug("update�ۗL���Y = " + true);
        
        log.exiting(STR_METHOD_NAME);
        return true;        
    }
    
	/**
	 * (is���i�敪)<BR>
	 * �o�ɑΏۃf�[�^�̏��i�敪�̃`�F�b�N���s���B<BR>
	 * <BR>
	 *�P�j���i�敪�`�F�b�N 
	 *�@@�i�P�j����.���i�敪=�h1�i�����j�h�̏ꍇ�́A�utrue�v��ԋp����B<BR>
	 *�@@�i�Q�j��L�ɓ��Ă͂܂�Ȃ��ꍇ�A�ufalse�v��ԋp����B<BR>
	 * <BR>
	 * @@param l_strCommodityDiv - ���i�敪
	 * @@return boolean
	 * @@throws WEB3BaseException
	 * @@roseuid 4157977900AD
	 */
	protected boolean isCommodityDiv(String l_strCommodityDiv) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCommodityDiv(String l_strCommodityDiv)";
		log.entering(STR_METHOD_NAME);
    
		//�P�j���i�敪�`�F�b�N 
		//�i�P�j����.���i�敪=�h1�i�����j�h�̏ꍇ�́A�utrue�v��ԋp����B 
		if (WEB3AioHostSpsecCommodityDef.EQUITY.equals(l_strCommodityDiv))
		{
			log.debug("is���i�敪 = " + true);
			log.exiting(STR_METHOD_NAME);
			return true;
		}
		//�i�Q�j��L�ɓ��Ă͂܂�Ȃ��ꍇ�A�ufalse�v��ԋp����B
		else
		{
			log.debug("is���i�敪 = " + false);
			log.exiting(STR_METHOD_NAME);
			return false;
		}
	}
	
	/**
	 * (is����敪)<BR>
	 * �o�ɑΏۃf�[�^�̎���敪�̃`�F�b�N���s���B<BR>
	 * <BR>
	 *�P�j���i�敪�`�F�b�N 
	 *�@@�i�P�j����.���i�敪=�h-�i����j�h�̏ꍇ�́A�ufalse�v��ԋp����B<BR>
	 *�@@�i�Q�j��L�ɓ��Ă͂܂�Ȃ��ꍇ�A�utrue�v��ԋp����B<BR>
	 * <BR>
	 * @@param l_strCancelDiv - ����敪
	 * @@return boolean
	 * @@throws WEB3BaseException
	 * @@roseuid 4157977900AD
	 */
	protected boolean isCancelDiv(String l_strCancelDiv) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCancelDiv(String l_strCancelDiv)";
		log.entering(STR_METHOD_NAME);
    
		//�P�j����敪�`�F�b�N 
		//�i�P�j����.����敪=�h-�i����j�h�̏ꍇ�́A�ufalse�v��ԋp����B 
		if (l_strCancelDiv == null || l_strCancelDiv.length() == 0 )
		{
			log.debug("is����敪 = " + true);
			log.exiting(STR_METHOD_NAME);
			return true;
		}
		else if (WEB3CancelDef.CANCLE.equals(l_strCancelDiv))
		{
			log.debug("is����敪 = " + false);
			log.exiting(STR_METHOD_NAME);
			return false;
		}
		else
		{
			//�i�Q�j��L�ɓ��Ă͂܂�Ȃ��ꍇ�A�utrue�v��ԋp����B
			log.debug("is����敪 = " + true);
			log.exiting(STR_METHOD_NAME);
			return true;
		}
	}
}
@
