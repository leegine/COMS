head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTypeOrderManagerReusableValidations.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������R���ʃ`�F�b�N(WEB3FeqTypeOrderManagerReusableValidations.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  鰊](���u) �V�K�쐬
Revesion History : 2005/07/28  ������(���u) ���r���[
Revesion History : 2006/09/14  ꎉ�(���u) ���r���[
Revesion History : 2006/10/12  ���G��(���u) �o�O3063�̑Ή�
Revesion History : 2006/10/30  �����(���u) �d�l�ύX���f��296
Revesion History : 2006/12/04  �� �r(���u) �d�l�ύX���f��308
Revesion History : 2007/03/15  ꎉ�(���u) �d�l�ύX���f��347
Revesion History : 2008/01/17  �đo�g(���u) ����No.007�A���f��378�A���f��372
Revesion History : 2008/02/01  �g�C�� (���u) ���f�� No.390
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.466
*/

package webbroker3.feq;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerErrorCatalog;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqAssetImpl;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenDef;
import webbroker3.common.define.WEB3ChangeableTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3MarketPreferencesValueDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.feq.data.FCashBalanceRow;
import webbroker3.feq.data.FeqTickValuesMstRow;
import webbroker3.feq.util.WEB3FeqDateUtility;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�����������R���ʃ`�F�b�N)<BR>
 * �O�����������R���ʃ`�F�b�N<BR>
 * 
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqTypeOrderManagerReusableValidations extends FeqProductTypeOrderManagerReusableValidations 
{
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    protected static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqTypeOrderManagerReusableValidations.class);
        
    /**
     * @@roseuid 42CE39EC00BB
     */
    public WEB3FeqTypeOrderManagerReusableValidations() 
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
        //�O�����������R���ʃ`�F�b�N.setInstance()���R�[������
        FeqProductTypeOrderManagerReusableValidations.setInstance(
            new WEB3FeqTypeOrderManagerReusableValidations());
    }
    
    /**
     * (validate�O�����������J��)<BR>
     * �ڋq���O�������������J�݂��Ă��邩���`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g�擾 <BR>
     * �@@�⏕����.getMainAccount()�ɂČڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�����J�݋敪�`�F�b�N <BR>
     * �@@�ڋq���O�������������J�݂��Ă��Ȃ��ꍇ<BR>
     * �@@�i�ڋq.�ڋqParams.�O���،������J�݋敪 !=  1�F�J�݁j�A<BR>
     * �@@��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01944<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@throws WEB3BaseException 
     * @@roseuid 428093EC01CD
     */
    public void validateFeqAccountEstablish(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateFeqAccountEstablish(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�ڋq�I�u�W�F�N�g���擾����
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount) l_subAccount.getMainAccount();
        MainAccountRow l_row = (MainAccountRow)l_mainAccount.getDataSourceObject();        
        String l_strForeignSecAccOpenDiv = l_row.getForeignSecAccOpenDiv();
        
        //�ڋq.�ڋqParams.�O���،������J�݋敪 !=  1�F�J�ݗ�O���X���[����
        if (!(WEB3AccountOpenDef.OPEN.equals(l_strForeignSecAccOpenDiv)))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01944.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01944,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
                            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������)<BR>
     * �ivalidateTradedProduct�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@this.validate�������()���R�[������B<BR>
     * <BR>
     * �@@[validate�������()�Ɏw�肷�����]<BR>
     * �@@�@@�O�����������F�@@�p�����[�^.�O����������<BR>
     * �@@�@@�s��F�@@�p�����[�^.�s��<BR>
     * <BR>
     * �Q�j�@@����K���`�F�b�N<BR>
     * �@@�@@�i�O�������������.is����K��(is������) == true�j�̏ꍇ�A<BR>
     * �@@�@@����K�����Ɣ��肵��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02087<BR>
     * @@param l_feqProduct - (�O����������)<BR>
     * �O�����������I�u�W�F�N�g
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g
     * @@param l_blnIsBuyOrder - �iis�������j<BR>
     * ���������̔���<BR>
     * <BR>
     * true�F��<BR>
     * false�F��
     * 
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 42805E6800D3
     */
    public TradedProduct validateTradedProduct(FeqProduct l_feqProduct,
        Market l_market, boolean l_blnIsBuyOrder) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateTradedProduct(FeqProduct, Market, boolean)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqProduct == null || l_market == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //this.validate�������()���R�[������
        WEB3FeqTradedProduct l_feqTradedProduct = (WEB3FeqTradedProduct) this.validateTradedProduct(l_feqProduct, l_market);

        //�O�������������.is����K��(is������)
        boolean l_blnIsTradingSuspended = l_feqTradedProduct.isTradingSuspended(l_blnIsBuyOrder);
        if(l_blnIsTradingSuspended)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02087.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02087,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        
        log.exiting(STR_METHOD_NAME); 
        return l_feqTradedProduct;
    }
    
    /**
     * (validate�������)<BR>
     * �ivalidateTradedProduct�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validateTradedProduct()���R�[�����A<BR>
     * �@@�@@�O��������������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�|�O����������������擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@��������Ȃ��̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02088<BR>
     * �@@�@@�|OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LISTED<BR>
     * �@@�@@���X���[���ꂽ�ꍇ�A<BR>
     * �@@�@@�@@����̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02089<BR>
     * <BR>
     * �Q�j�@@�����ԃ`�F�b�N<BR>
     * �@@�@@�i�O�������������.is�����ԓ�() == false�j�̏ꍇ�A<BR>
     * �@@�@@����\�łȂ��Ɣ��肵��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02090<BR>
     * @@param l_feqProduct - (�O����������)<BR>
     * �O�����������I�u�W�F�N�g
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g
     * @@return TradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 42C20D1A006B
     */
    public TradedProduct validateTradedProduct(FeqProduct l_feqProduct,
        Market l_market) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateTradedProduct(FeqProduct, Market)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_feqProduct == null || l_market == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        WEB3FeqTradedProduct l_feqTradedProduct = null;
        try
        {
            l_feqTradedProduct = (WEB3FeqTradedProduct) super.validateTradedProduct(l_feqProduct, l_market);
        }
        //OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LISTED���X���[���ꂽ�ꍇ����̗�O���X���[����
        
        catch (OrderValidationException l_ex)
        {
            if (OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LISTED.equals(
                    l_ex.getValidationResult().getProcessingResult().getErrorInfo()))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02089.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02089, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(),
                    l_ex);
            }
            else
            {
                log.error(l_ex.getMessage());
            }
        }
        //�O����������������擾�ł��Ȃ������ꍇ ��������Ȃ��̗�O���X���[����
        if (l_feqTradedProduct == null)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02088.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088, 
                getClass().getName() + "." + STR_METHOD_NAME);
        }                               
        boolean l_blnIsOpen = l_feqTradedProduct.isOpen();
        //�i�O�������������.is�����ԓ�() == false�j�̏ꍇ����\�łȂ��Ɣ��肵��O���X���[����
        if (!l_blnIsOpen)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02090.getErrorMessage());
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02090,
                getClass().getName() + "." + STR_METHOD_NAME);
        }                     
        log.exiting(STR_METHOD_NAME);    
        
        return l_feqTradedProduct;
    }
    
    /**
     * (validate�O������)<BR>
     * �ivalidateFeqProduct�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@super.validateFeqProduct()���R�[�����A<BR>
     * �@@�@@�O�����������I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�|�O�������������擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�����Ȃ��̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00391<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h
     * @@return FeqProduct
     * @@throws OrderValidationException
     * @@roseuid 428061B201FC
     */
    public FeqProduct validateFeqProduct(Institution l_institution,
        String l_strProductCode) throws OrderValidationException 
    {
        final String STR_METHOD_NAME = " validateFeqProduct(Institution, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            //�O�����������I�u�W�F�N�g���擾����
            l_feqProduct = (WEB3FeqProduct) super.validateFeqProduct(l_institution, l_strProductCode);
        }
        catch (OrderValidationException l_ex)
        {
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_02142);
        }

        //�O�������������擾�ł��Ȃ������ꍇ�A�O�������������擾�ł��Ȃ��̗�O���X���[����
        if (l_feqProduct == null)
        {
            log.debug("�O�������������擾�ł��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_02142);
        }
        log.exiting(STR_METHOD_NAME);
        return l_feqProduct;
    }
    
    /**
     * (validate�����P��)<BR>
     * �ivalidatePrice�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@�w�l�^���s�`�F�b�N<BR>
     * �@@�|�iis���s���� == true && �����P�� != 0�j�̏ꍇ�A<BR>
     * �@@���s�����ŒP�����w�肳�ꂽ�Ɣ��肵��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02091
     * <BR>
     * �@@�|�iis���s���� == false && �����P�� == 0�j�̏ꍇ�A<BR>
     * �@@�w�l�����ŒP�������w��Ɣ��肵��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02092<BR>
     * <BR>
     * ���@@�Q�j�ȍ~�̏����́A�w�l�����̏ꍇ<BR>
     * �@@�iis���s���� == false�j�̂ݎ��{�B<BR>
     * <BR>
     * �Q�j�@@�����P���T�C�Y�`�F�b�N<BR>
     * �@@�������U���C�������T���͈͓̔��łȂ��ꍇ�A<BR>
     * �@@�T�C�Y���s���Ɣ��肵��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02093<BR>
     * <BR>
     * �R�j�@@�Ēl�`�F�b�N<BR>
     * �@@this.validate�Ēl()���R�[������B<BR>
     * <BR>
     * �@@[validate�Ēl()�Ɏw�肷�����]<BR>
     * �@@�s��R�[�h�F�@@�������.getMarket().getMarketCode()<BR>
     * �@@�����P���F�@@�����P��<BR>
     * <BR>
     * �S�j�@@�l���`�F�b�N<BR>
     * �@@this.validate�l��()���R�[������B<BR>
     * <BR>
     * �@@[validate�l��()�Ɏw�肷�����]<BR>
     * �@@�O��������������F�@@�������<BR>
     * �@@�����P���F�@@�����P��<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * �O��������������I�u�W�F�N�g
     * 
     * @@param l_dblOrderPrice - (�����P��)<BR>
     * �����P��
     * @@param l_blnIsMarketOrder - �iis���s�����j<BR>
     * ���s�������𔻒肷��B<BR>
     * <BR>
     * true�F���s����<BR>
     * false�F�w�l����
     * 
     * @@throws OrderValidationException
     * @@roseuid 4280712003A2
     */
    public void validatePrice(TradedProduct l_tradedProduct,
        double l_dblOrderPrice, boolean l_blnIsMarketOrder) throws OrderValidationException 
    {
        final String STR_METHOD_NAME = 
                " validatePrice(TradedProduct, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tradedProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j�@@�w�l�^���s�`�F�b�N 
        //  �|�iis���s���� == true && �����P�� != 0�j�̏ꍇ�A���s�����ŒP�����w�肳�ꂽ�Ɣ��肵��O���X���[����B 
        //  �|�iis���s���� == false && �����P�� == 0�j�̏ꍇ�A�w�l�����ŒP�������w��Ɣ��肵��O���X���[����B
        try
        {
            if (l_blnIsMarketOrder && l_dblOrderPrice != 0)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02091.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02091,
                        getClass().getName() + "." + STR_METHOD_NAME);
            }
            if (!l_blnIsMarketOrder && l_dblOrderPrice == 0)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02092.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02092,
                    getClass().getName() + "." + STR_METHOD_NAME);   
            }    
            //���@@�Q�j�ȍ~�̏����́A�w�l�����̏ꍇ�iis���s���� == false�j�̂ݎ��{�B 
            //
            //�Q�j�@@�����P���T�C�Y�`�F�b�N 
            if (!l_blnIsMarketOrder)
            {
                //this.validate�Ēl()���R�[������B 
                String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
                try
                {
                    //�R�j�@@�Ēl�`�F�b�N
                    this.validateTickValue(l_strMarketCode, l_dblOrderPrice);
                    //�S�j�@@�l���`�F�b�N
                    this.validatePriceRange((FeqTradedProduct)l_tradedProduct, l_dblOrderPrice);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.debug(l_ex.getErrorInfo().getErrorMessage());
                    throw new OrderValidationException(l_ex.getErrorInfo());
                }
            }
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getErrorInfo().getErrorMessage());
            throw new OrderValidationException(l_ex.getErrorInfo());
        }   
         
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��������)<BR>
     * �ivalidateQuantity�j<BR>
     * <BR>
     * �P�j�@@���������T�C�Y�`�F�b�N <BR>
     * �@@�P�|�P�j�@@�s��v���t�@@�����X�e�[�u����ǂ�Ń`�F�b�N���������B <BR>
     * �@@�@@�@@�@@�s��v���t�@@�����X�e�[�u�����ȉ��̃L�[�ifindRowByP��()�Ɏw�肷������j<BR>
     * �@@�@@�@@�@@�œǂݑ��݂��郌�R�[�h���l������B<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�@@�@@�@@�@@�@@�@@�@@�F�s��.getMarketid() <BR>
     * �@@�@@�@@�@@�@@�@@�v���t�@@�����X���ږ��Ffeq.order.quantity.size <BR>
     * �@@�@@�@@�@@�@@�@@���ږ��A�ԁ@@�@@�@@�@@�F�@@���t�̏ꍇ�i����.is������ == true�j�@@�F�@@1<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t�̏ꍇ�i����.is������ == false�j�@@�F�@@2<BR>
     * <BR>
     *  �@@�P�|�Q�j�@@���R�[�h�����݂��Ȃ��ꍇ�A�ȉ��̃`�F�b�N���s���B<BR> 
     *  �@@�@@�@@�@@�@@�X���ȓ��̐����l�łȂ��ꍇ�A�T�C�Y���s���Ɣ��肵��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00903<BR>
     *  <BR>
     *  �@@�P�|�R�j�@@���R�[�h�����݂���ꍇ�A�ȉ��̃`�F�b�N���s���B <BR>
     *  �@@�@@�@@�@@�@@�v���t�@@�����X�̒l�̌����ȓ��̐����l�Ŗ����ꍇ�A�T�C�Y���s���Ɣ��肵��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00903<BR>
     * <BR>
     * �Q�j�@@�Œᒍ�������`�F�b�N <BR>
     * �@@�Q�|�P�j�@@�Œᒍ���������擾����B <BR>
     * �@@�@@�|���t�̏ꍇ�iis������ == true�j�ABR> 
     *    �O�������������.getBuyOrderMinimumQuantity() ���Œᒍ�������Ƃ���B<BR> 
     *�@@ �@@�|���t�̏ꍇ�iis������ == false�j�ABR> 
     *    �O�������������.getSellOrderMinimumQuantity() ���Œᒍ�������Ƃ���B<BR> 
     *  <BR>
     *�@@ �Q�|�Q�j�@@�Œᒍ�������ȏ�̃`�F�b�N <BR>
     *�@@�@@ �i�Œᒍ�������@@> ���������j�̏ꍇ�ABR> 
     *      �����������Œᒍ�����������ł���Ɣ��肵�A��O���X���[����B <BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:  BUSINESS_ERROR_02094<BR>
     *  <BR>
     * �R�j�@@�����P�ʃ`�F�b�N <BR>
     *�@@ �R�|�P�j�@@�����P�ʂ��擾���� <BR>
     *�@@�@@ �|���t�����̏ꍇ�iis������ == true�j�ABR> 
     *     �O�������������.get���t�P��()�@@�𔄔��P�ʂƂ���B <BR>
     *�@@�@@ �|���t�����̏ꍇ�iis������ == false�j�ABR> 
     *     �O�������������.get���t�P��()�@@�𔄔��P�ʂƂ���B <BR>
     *  <BR>
     * �@@�R�|�Q�j�@@�����P�ʂ̐����{���̃`�F�b�N <BR>
     *�@@ �@@�i���������������P�� != 0�j�̏ꍇ�ABR> 
     *      ���������������P�ʂ̐����{�łȂ��Ɣ��肵�A��O���X���[����B <BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:  BUSINESS_ERROR_00708<BR>
     * <BR>   
     * �S�j�@@�ő咍�������`�F�b�N <BR>
     * �@@�S�|�P�j�@@�s��v���t�@@�����X�e�[�u����ǂ�Ń`�F�b�N�Ώۂ̎s�ꂩ�ۂ�����B<BR>
     * �@@�@@�@@�s��v���t�@@�����X�e�[�u�����ȉ��̃L�[�ifindRowByP��()�Ɏw�肷������j<BR>
     * �@@�@@�@@�œǂݑ��݂��郌�R�[�h���l������B<BR>
     * �@@�@@�@@�@@�@@�@@�s��ID�@@�@@�@@�@@�@@�@@�@@�F�s��.getMarketid()<BR>
     * �@@�@@�@@�@@�@@�@@�v���t�@@�����X���ږ��Ffeq.order.quantity.limit <BR>
     * �@@�@@�@@�@@�@@�@@���ږ��A�ԁ@@�@@�@@�@@�F�@@���t�̏ꍇ�i����.is������ == true�j�@@�F�@@1<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���t�̏ꍇ�i����.is������ == false�j�@@�F�@@2<BR>
     * <BR>
     * �@@�S�|�Q�j�@@���R�[�h�����݂��Ȃ��ꍇ�A�ȉ��̏������s�킸�ɏI������B<BR>
     * <BR>
     * �@@�S�|�R�j�@@���R�[�h�����݂���ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�����������v���t�@@�����X�̒l�~�����P�ʁi��L�R�|�P�@@�Ŋl���������́j�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�����������ő咍�������𒴂���Ɣ��肵�A��O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:  BUSINESS_ERROR_00160<BR>
     * @@param l_feqTradedProduct - (�O�������������)<BR>
     * �O��������������I�u�W�F�N�g
     * @@param l_dblOrderNumber - (��������)<BR>
     * ��������
     * @@param l_blnIsBuyOrder - �iis�������j<BR>
     * ���������̔���<BR>
     * <BR>
     * true�F��<BR>
     * false�F��
     * @@throws OrderValidationException 
     * 
     * @@throws OrderValidationException
     * @@roseuid 42807125024A
     */
    public void validateQuantity(FeqTradedProduct l_feqTradedProduct,
        double l_dblOrderNumber, boolean l_blnIsBuyOrder) throws OrderValidationException
    {

        final String STR_METHOD_NAME = 
            " validateQuantity(FeqTradedProduct, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        MarketPreferencesRow l_row = null;
        
        if (l_feqTradedProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        // �P�j�@@���������T�C�Y�`�F�b�N
        // �@@�P�|�P�j�@@�s��v���t�@@�����X�e�[�u����ǂ�Ń`�F�b�N���������B
        //�@@�@@�@@�@@�s��v���t�@@�����X�e�[�u�����ȉ��̃L�[�ifindRowByP��()�Ɏw�肷������j
        //        �œǂݑ��݂��郌�R�[�h���l������B
        //�@@�@@�@@�@@�@@�@@�s��ID�@@�@@�@@�@@�@@�@@�@@�F�s��.getMarketid()
        //�@@�@@�@@�@@�@@�@@�v���t�@@�����X���ږ��Ffeq.order.quantity.size
        //            ���ږ��A�ԁ@@�@@�@@�@@�F�@@���t�̏ꍇ�i����.is������ == true�j�@@�F�@@1 
        //�@@�@@ �@@                          ���t�̏ꍇ�i����.is������ == false�j�@@�F�@@2 

        long l_lngMarketId = l_feqTradedProduct.getMarket().getMarketId();
        String l_strPreferencesName = WEB3MarketPreferencesNameDef.FEQ_ORDER_QUANTITY_SIZE;
        int l_intSerialNumber = 0;
        if (l_blnIsBuyOrder == true)
        {
            l_intSerialNumber = 1;
        }
        else if (l_blnIsBuyOrder == false)
        {
            l_intSerialNumber = 2;
        }

        try
        {
            l_row = MarketPreferencesDao.findRowByPk(
                l_lngMarketId,
                l_strPreferencesName,
                l_intSerialNumber);
        }
        catch (DataFindException l_ex)
        {
            log.debug("Error in DataFindException..");
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

        String l_strOrderNumber = WEB3StringTypeUtility.formatNumber(l_dblOrderNumber);
        int l_intIntegerLength = WEB3StringTypeUtility.getIntegerDigits(l_strOrderNumber);
        //�@@�P�|�Q�j�@@���R�[�h�����݂��Ȃ��ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�@@�@@�@@�@@�X���ȓ��̐����l�łȂ��ꍇ�A�T�C�Y���s���Ɣ��肵��O���X���[����B
        if (l_row == null)
        {
            if (!(WEB3StringTypeUtility.isInteger(l_strOrderNumber)) || l_intIntegerLength > 9)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00903.getErrorMessage());
                //��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00903);
            }
        }

        //�@@�P�|�R�j�@@���R�[�h�����݂���ꍇ�A�ȉ��̃`�F�b�N���s���B
        //�@@�@@�@@�@@�@@�v���t�@@�����X�̒l�̌����ȓ��̐����l�Ŗ����ꍇ�A�T�C�Y���s���Ɣ��肵��O���X���[����B
        else
        {
            if (!(WEB3StringTypeUtility.isInteger(l_strOrderNumber)) ||
                l_intIntegerLength > Integer.parseInt(l_row.getValue()))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00903.getErrorMessage());
                //��O���X���[����
                log.exiting(STR_METHOD_NAME);
                throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00903);
            }
        }

        //�Q�j�@@�Œᒍ�������`�F�b�N 
        //�Q�|�P�j�@@�Œᒍ���������擾����B
        double l_dblQuantity = 0D;
        //�|���t�̏ꍇ�iis������ == true�j�A�O�������������.getBuyOrderMinimumQuantity() ���Œᒍ�������Ƃ���
        if (l_blnIsBuyOrder)
        {
            l_dblQuantity = l_feqTradedProduct.getBuyOrderMinimumQuantity();
        }
        //�|���t�̏ꍇ�iis������ == false�j�A�O�������������.getSellOrderMinimumQuantity() ���Œᒍ�������Ƃ���B 
        else
        {
            l_dblQuantity = l_feqTradedProduct.getSellOrderMinimumQuantity();
        }
        //�@@�Q�|�Q�j�@@�Œᒍ�������ȏ�̃`�F�b�N 
        //�i�Œᒍ�������@@> ���������j�̏ꍇ�A�����������Œᒍ�����������ł���Ɣ��肵�A��O���X���[����B
        if (l_dblQuantity > l_dblOrderNumber)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02094.getErrorMessage());
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_02094);
        }        
        //�R�j�@@�����P�ʃ`�F�b�N 
        // �R�|�P�j�@@�����P�ʂ��擾���� 
        double l_dblLotSize = 0D;
        if (l_blnIsBuyOrder)
        {
            //�|���t�����̏ꍇ�iis������ == true�j�A�O�������������.get���t�P��()�@@�𔄔��P�ʂƂ���B
            l_dblLotSize = ((WEB3FeqTradedProduct)l_feqTradedProduct).getBuyOrderLotSize();
        }
        else
        {
            //�|���t�����̏ꍇ�iis������ == false�j�A�O�������������.get���t�P��()�@@�𔄔��P�ʂƂ���B
            l_dblLotSize = ((WEB3FeqTradedProduct)l_feqTradedProduct).getSellOrderLotSize();
        }
        //�@@�R�|�Q�j�@@�����P�ʂ̐����{���̃`�F�b�N 
        //�@@�i���������������P�� != 0�j�̏ꍇ�A���������������P�ʂ̐����{�łȂ��Ɣ��肵�A��O���X���[����B
        if (!WEB3FeqOrderUtility.isRemainderZero(l_dblOrderNumber, l_dblLotSize))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00708.getErrorMessage());
            //��O���X���[����
            log.exiting(STR_METHOD_NAME);
            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00708);

        }
        
        //�S�j�@@�ő咍�������`�F�b�N
        //  �S�|�P�j�@@�s��v���t�@@�����X�e�[�u����ǂ�Ń`�F�b�N�Ώۂ̎s�ꂩ�ۂ�����B
        // �@@�@@�@@�s��v���t�@@�����X�e�[�u�����ȉ��̃L�[�ifindRowByP��()�Ɏw�肷������j
        // �@@�@@�@@�œǂݑ��݂��郌�R�[�h���l������B
        // �@@�@@�@@�@@�@@�@@�s��ID�@@�@@�@@�@@�@@�@@�@@�F�s��.getMarketid()
        // �@@�@@�@@�@@�@@�@@�v���t�@@�����X���ږ��Ffeq.order.quantity.limit 
        //            ���ږ��A�ԁ@@�@@�@@�@@�F�@@���t�̏ꍇ�i����.is������ == true�j�@@�F�@@1 
        //�@@�@@ �@@                          ���t�̏ꍇ�i����.is������ == false�j�@@�F�@@2 
        String l_strName = WEB3MarketPreferencesNameDef.FEQ_ORDER_QUANTITY_LIMIT;
        
        try
		{
			l_row = MarketPreferencesDao.findRowByPk(
				l_lngMarketId,
				l_strName,
                l_intSerialNumber);
		}
        catch (DataFindException l_ex)
        {
            log.debug("Error in DataFindException..");
            l_row = null;
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
        // �S�|�R�j�@@���R�[�h�����݂���ꍇ�A�ȉ��̃`�F�b�N���s���B
        if (l_row != null)
        {
        	//�����������v���t�@@�����X�̒l�~�����P�ʁi��L�R�|�P�@@�Ŋl���������́j�̏ꍇ
            //�����������ő咍�������𒴂���Ɣ��肵�A��O���X���[����B
        	BigDecimal l_bdValue = new BigDecimal(l_row.getValue());
        	BigDecimal l_bdLotSize = new BigDecimal(l_dblLotSize);
        	
        	BigDecimal l_bdTemp = l_bdValue.multiply(l_bdLotSize);
        	
        	BigDecimal l_bdOrderNumber = new BigDecimal(l_dblOrderNumber);
        	
        	if (l_bdOrderNumber.compareTo(l_bdTemp) > 0)
        	{            
        		log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00160.getErrorMessage());
	            //��O���X���[����
	            log.exiting(STR_METHOD_NAME);
	            throw new OrderValidationException(WEB3ErrorCatalog.BUSINESS_ERROR_00160);
        	}
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    
    /**
     * (validate�Ēl)<BR>
     * �ivalidateTickValue�j<BR>
     * �Ēl�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���ݒl�擾 <BR>
     * �@@�O���Ēl�e�[�u�����ȉ��̏����œǂ݁A�Y���s.���ݒl���擾����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�O���Ēl�e�[�u��.�s��R�[�h = �s��R�[�h And<BR>
     * �@@�O���Ēl�e�[�u��.�����l�@@< �����P�� And<BR>
     * �@@�O���Ēl�e�[�u��.����l�@@>= �����P��<BR>
     * <BR>
     * �Q�j�@@�Ēl�`�F�b�N<BR>
     * �@@�����P�������ݒl�Ŋ���؂��l�����`�F�b�N����B <BR>
     * <BR>
     * �@@�i�����P���^���ݒl�j�������łȂ��ꍇ�A<BR> 
     * �@@�Ēl�G���[�̗�O���X���[����B<BR> 
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00148<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h
     * @@param l_dblOrderPrice - (�����P��)<BR>
     * �����P��
     * @@throws WEB3BaseException
     * @@roseuid 428074510112
     */
    public void validateTickValue(String l_strMarketCode, double l_dblOrderPrice) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateTickValue(String, double)";
        log.entering(STR_METHOD_NAME);
        
        QueryProcessor l_qp = null;
        List l_lisFeqTickValuesMst = null;
        try
        {            
            String l_strWhere = " market_code = ?  and low_price < ?  and cap_price >= ? ";
            Object[] l_bindValues = {
                l_strMarketCode,
                new Double(l_dblOrderPrice),
                new Double(l_dblOrderPrice)};
            
           
            l_qp = Processors.getDefaultProcessor();
            
            l_lisFeqTickValuesMst = l_qp.doFindAllQuery(
                FeqTickValuesMstRow.TYPE, 
                l_strWhere, 
                l_bindValues);
        }
        catch (DataFindException l_ex)
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
        double l_dblTickValue = 0D;
        if (l_lisFeqTickValuesMst == null || l_lisFeqTickValuesMst.isEmpty())
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        if (l_lisFeqTickValuesMst.size() > 1)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80004.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        
        FeqTickValuesMstRow l_feqTickValuesMstRow = (FeqTickValuesMstRow) l_lisFeqTickValuesMst.get(0);
        l_dblTickValue = l_feqTickValuesMstRow.getTickValue();
        
        if(!WEB3FeqOrderUtility.isRemainderZero(l_dblOrderPrice, l_dblTickValue))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00148.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00148,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�l��)<BR>
     * �ivalidatePriceRange�j<BR>
     * <BR>
     * ***�i�I�l�]�����ڒǉ��Ή��Č��ɂ��A�I�l���ݒ肳��Ă��܂����߁j<BR>
     * �@@�@@�@@�@@�ȉ��̓��e�͑S�ăR�����g�A�E�g�Ƃ���***<BR>
     * <BR>
     * �P�j�@@�I�l�擾<BR>
     * �@@�O�������������.get�I�l()�ɂďI�l���擾����B<BR>
     * �@@�I�l���擾�ł��Ȃ������ꍇ�iNaN�܂���0�j�A<BR>
     * �@@�ȍ~�̏������s�킸�ɏI������B<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�擾<BR>
     * �@@�O�������������.getMarket().getMarketCode()<BR>
     * �@@�ɂĎs��R�[�h���擾����B<BR>
     * <BR>
     * �R�j�@@�l����l�v�Z<BR>
     * �@@this.calc�l���␳�l()�ɂāA���ݒl�Ŋ���؂��l�ɂ����I�l<BR>
     * �@@�i�l����l�j���擾����B<BR>
     * <BR>
     * �@@[calc�l���␳�l()�Ɏw�肷�����]<BR>
     * �@@�s��R�[�h�F�@@�s��R�[�h<BR>
     * �@@��l�F�@@�I�l<BR>
     * <BR>
     * �S�j�@@�����l���擾<BR>
     * �@@�O���l���e�[�u�����ȉ��̏����œǂ݁A�擾�s.�l�����擾����B<BR>
     * �@@�i�����l���j<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�O�������l���e�[�u��.�s��R�[�h = �Q�j�Ŏ擾�����s��R�[�h And<BR>
     * �@@�O�������l���e�[�u��.�����l�@@<= �R�j�Ŏ擾���������l�� And<BR>
     * �@@�O�������l���e�[�u��.����l�@@> �R�j�Ŏ擾���������l��<BR>
     * <BR>
     * �T�j�@@�l������v�Z<BR>
     * �@@this.calc�l���␳�l()�ɂāA���ݒl�Ŋ���؂��l�ɂ���<BR>
     * �@@�l��������擾����B<BR>
     * �@@<BR>
     * �@@[calc�l���␳�l()�Ɏw�肷�����]<BR>
     * �@@�s��R�[�h�F�@@�s��R�[�h<BR>
     * �@@��l�F�@@�i�l����l�@@�{�@@�����l���j�̌v�Z����<BR>
     * <BR>
     * �@@���l����l�F�@@�R�j�̌v�Z����<BR>
     * �@@�������l���F�@@�S�j�̌v�Z����<BR>
     * <BR>
     * �U�j�@@�l�������v�Z<BR>
     * �@@this.calc�l���␳�l()�ɂāA���ݒl�Ŋ���؂��l�ɂ���<BR>
     * �@@�l���������擾����B<BR>
     * �@@<BR>
     * �@@[calc�l���␳�l()�Ɏw�肷�����]<BR>
     * �@@�s��R�[�h�F�@@�s��R�[�h<BR>
     * �@@��l�F�@@�i�l����l�@@�|�@@�����l���j�̌v�Z����<BR>
     * <BR>
     * �@@���l����l�F�@@�R�j�̌v�Z����<BR>
     * �@@�������l���F�@@�S�j�̌v�Z����<BR>
     * <BR>
     * �V�j�@@�l���͈͓̔������`�F�b�N����<BR>
     * <BR>
     * �@@�i�l������ <= �����P�� <= �l������j�łȂ��ꍇ�A<BR>
     * �@@�l���͈͓̔��łȂ��Ɣ��肵�A��O���X���[����B <BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00031<BR>
     * <BR>
     * �@@���l������F�@@�T�j�̌v�Z����<BR>
     * �@@���l�������F�@@�U�j�̌v�Z����<BR>
     * @@param l_feqTradedProduct - (�O�������������)<BR>
     * �O��������������I�u�W�F�N�g
     * @@param l_dblOrderPrice - (�����P��)<BR>
     * �����P��
     * @@throws WEB3BaseException
     * @@roseuid 428076850102
     */
    public void validatePriceRange(FeqTradedProduct l_feqTradedProduct,
        double l_dblOrderPrice) throws WEB3BaseException 
    {
//        final String STR_METHOD_NAME = 
//            " validatePriceRange(FeqTradedProduct, double)";
//        log.entering(STR_METHOD_NAME);
//        
//        if (l_feqTradedProduct == null)
//        {
//            log.debug(" �p�����[�^�l��NULL ");
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
//                this.getClass().getName() + "." + STR_METHOD_NAME, 
//                "�p�����[�^�l��NULL");
//        }
//        // �P�j�@@�I�l�擾 
//        //  �O�������������.get�I�l()�ɂďI�l���擾����B 
//        //  �I�l���擾�ł��Ȃ������ꍇ�iNaN�܂���0�j�A�ȍ~�̏������s�킸�ɏI������B 
//        double l_dblLastClosingPrice = ((WEB3FeqTradedProduct)l_feqTradedProduct).getLastClosingPrice();
//        if (Double.isNaN(l_dblLastClosingPrice) || l_dblLastClosingPrice == 0)
//        {
//            log.debug("return");
//            return;
//        }
//        
//        // �Q�j�@@�s��R�[�h�擾 
//        //   �O�������������.getMarket().getMarketCode()�ɂĎs��R�[�h���擾����B
//        String l_strMarketCode = l_feqTradedProduct.getMarket().getMarketCode();
//        
//        // �R�j�@@�l����l�v�Z 
//        //   this.calc�l���␳�l()�ɂāA���ݒl�Ŋ���؂��l�ɂ����I�l�i�l����l�j���擾����B 
//        BigDecimal l_bdCalcRangeRevisionValue =
//            new BigDecimal(String.valueOf(this.calcRangeRevisionValue(l_strMarketCode, l_dblLastClosingPrice)));
//        
//        //�S�j�@@�����l���擾 
//        // �O���l���e�[�u�����ȉ��̏����œǂ݁A�擾�s.�l�����擾����B�i�����l���j 
//        //
//        // [����]  
//        //  �O�������l���e�[�u��.�s��R�[�h = �Q�j�Ŏ擾�����s��R�[�h And 
//        //  �O�������l���e�[�u��.�����l�@@<= �R�j�Ŏ擾���������l�� And 
//        //  �O�������l���e�[�u��.����l�@@> �R�j�Ŏ擾���������l��
//        QueryProcessor l_qp = null;
//        List l_lisFeqLimitPriceRangeMst = null;
//        try
//        {
//            String l_strWhere = " market_code = ?  and low_price <= ?  and cap_price > ? ";
//                       
//            Object[] l_bindValues = {
//                l_strMarketCode,
//                l_bdCalcRangeRevisionValue,
//                l_bdCalcRangeRevisionValue};
//            
//            l_qp = Processors.getDefaultProcessor();
//            
//            l_lisFeqLimitPriceRangeMst = 
//                l_qp.doFindAllQuery(FeqLimitPriceRangeMstRow.TYPE,l_strWhere, l_bindValues);
//        }
//        catch (DataFindException l_ex)
//        {
//            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
//                this.getClass().getName() + "." + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        if (l_lisFeqLimitPriceRangeMst == null || l_lisFeqLimitPriceRangeMst.isEmpty())
//        {
//            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                getClass().getName() + "." + STR_METHOD_NAME);   
//        }
//        if (l_lisFeqLimitPriceRangeMst.size() > 1)
//        {
//            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80004.getErrorMessage());
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
//                getClass().getName() + "." + STR_METHOD_NAME);   
//        }
//
//        FeqLimitPriceRangeMstRow l_feqLimitPriceRangeMstRow = 
//            (FeqLimitPriceRangeMstRow)l_lisFeqLimitPriceRangeMst.get(0);
//        BigDecimal l_bdRange =
//            new BigDecimal(String.valueOf(l_feqLimitPriceRangeMstRow.getRange()));
//        
//        //�T�j�@@�l������v�Z 
//        // this.calc�l���␳�l()�ɂāA���ݒl�Ŋ���؂��l�ɂ����l��������擾����B 
//        //�@@ 
//        // [calc�l���␳�l()�Ɏw�肷�����] 
//        // �s��R�[�h�F�@@�s��R�[�h 
//        // ��l�F�@@�i�l����l�@@�{�@@�����l���j�̌v�Z���� 
//        //
//        // ���l����l�F�@@�R�j�̌v�Z���� 
//        // �������l���F�@@�S�j�̌v�Z����
//
//        double l_dblCapPrice = 
//            this.calcRangeRevisionValue(l_strMarketCode, l_bdCalcRangeRevisionValue.add(l_bdRange).doubleValue());
//         
//        //�U�j�@@�l�������v�Z 
//        // this.calc�l���␳�l()�ɂāA���ݒl�Ŋ���؂��l�ɂ����l���������擾����B 
//        //�@@ 
//        // [calc�l���␳�l()�Ɏw�肷�����] 
//        // �s��R�[�h�F�@@�s��R�[�h 
//        // ��l�F�@@�i�l����l�@@�|�@@�����l���j�̌v�Z���� 
//        //
//        // ���l����l�F�@@�R�j�̌v�Z���� 
//        // �������l���F�@@�S�j�̌v�Z���� 
//
//        double l_dblLowPrice = 
//            this.calcRangeRevisionValue(l_strMarketCode, l_bdCalcRangeRevisionValue.subtract(l_bdRange).doubleValue());
//
//        //�V�j�@@�l���͈͓̔������`�F�b�N���� 
//        //
//        // �i�l������ <= �����P�� <= �l������j�łȂ��ꍇ�A�l���͈͓̔��łȂ��Ɣ��肵�A��O���X���[����B  
//        //
//        //  ���l������F�@@�T�j�̌v�Z���� 
//        //  ���l�������F�@@�U�j�̌v�Z����
//        if (!(l_dblOrderPrice >= l_dblLowPrice && l_dblOrderPrice <= l_dblCapPrice))
//        {
//            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00031.getErrorMessage());
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BusinessLayerException(
//                WEB3ErrorCatalog.BUSINESS_ERROR_00031,
//                getClass().getName() + "." + STR_METHOD_NAME);               
//        } 
//        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�l���␳�l)<BR>
     * �l���␳�l�i�����ݒl�Ŋ���؂��悤�ɕ␳�����P���l�j���v�Z����B<BR>
     * <BR>
     * �P�j�@@���ݒl�擾<BR>
     * �@@�O���Ēl�e�[�u�����ȉ��̏����œǂ݁A�Y���s.���ݒl�C<BR>
     * �@@�������������擾����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�O���Ēl�e�[�u��.�s��R�[�h = �s��R�[�h And<BR>
     * �@@�O���Ēl�e�[�u��.�����l�@@< ��l And<BR>
     * �@@�O���Ēl�e�[�u��.����l�@@>= ��l<BR>
     * <BR>
     * �Q�j�@@��l��␳���Ēl����l���v�Z����<BR>
     * <BR>
     * ���i�i��l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ <BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B <BR>
     * <BR>
     * �@@[�v�Z��] <BR>
     * �@@�i��l�^���ݒl(*1)�j�~���ݒl <BR>
     * �@@(*1)�@@���Z�̌v�Z���ʂ̏��������������؂�グ�B <BR>
     * <BR>
     * ����L�ȊO�̏ꍇ <BR>
     * �@@�ȉ��̌v�Z���ʂ�ԋp����B <BR>
     * <BR>
     * �@@[�v�Z��] <BR>
     * �@@�i��l�^���ݒl(*2)�j�~���ݒl <BR>
     * �@@(*2)�@@���Z�̌v�Z���ʂ����������������؂�̂āB <BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h
     * @@param l_dblBasicValue - (��l)<BR>
     * ��l
     * @@return Double
     * @@throws WEB3BaseException 
     * @@roseuid 428079F20170
     */
    protected double calcRangeRevisionValue(String l_strMarketCode,
        double l_dblBasicValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " calcRangeRevisionValue(String, double)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���ݒl�擾 
        // �O���Ēl�e�[�u�����ȉ��̏����œǂ݁A�Y���s.���ݒl�C�������������擾����B 
        //
        // [����]  
        // �O���Ēl�e�[�u��.�s��R�[�h = �s��R�[�h And 
        // �O���Ēl�e�[�u��.�����l�@@< ��l And 
        // �O���Ēl�e�[�u��.����l�@@>= ��l 
        QueryProcessor l_qp = null;
        List l_lisFeqTickValuesMst = null;
        try
        {
            String l_strWhere = " market_code = ?  and low_price < ?  and cap_price >= ? ";

            Object[] l_bindValues = {
                l_strMarketCode,
                new Double(l_dblBasicValue),
                new Double(l_dblBasicValue)};

            l_qp = Processors.getDefaultProcessor();
            
            l_lisFeqTickValuesMst = l_qp.doFindAllQuery(FeqTickValuesMstRow.TYPE, l_strWhere, l_bindValues);
        }
        catch (DataFindException l_ex)
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
        
        if (l_lisFeqTickValuesMst == null || l_lisFeqTickValuesMst.isEmpty())
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        if (l_lisFeqTickValuesMst.size() > 1)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80004.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                getClass().getName() + "." + STR_METHOD_NAME);   
        }
        
        int l_intScale = 0;

        FeqTickValuesMstRow l_feqTickValuesMstRow = (FeqTickValuesMstRow) l_lisFeqTickValuesMst.get(0);
        //���ݒl
        BigDecimal l_bdTickValue =
            new BigDecimal(String.valueOf(l_feqTickValuesMstRow.getTickValue()));
        //����������
        l_intScale = l_feqTickValuesMstRow.getScale();
        
        //�Q�j�@@��l��␳���Ēl����l���v�Z���� 
        BigDecimal l_bdBasicValue = new BigDecimal(String.valueOf(l_dblBasicValue));
        double l_dblBasePrice = l_dblBasicValue; //��l
        
        if (WEB3FeqOrderUtility.getRemainder(l_dblBasicValue ,l_bdTickValue.doubleValue())
            >= l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_EVEN).doubleValue())
        {
            //���Z�̌v�Z���ʂ̏��������������؂�グ
            l_dblBasePrice =
                l_bdBasicValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        else
        {
            //���Z�̌v�Z���ʂ����������������؂�̂�
            l_dblBasePrice =
                l_bdBasicValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }
    
    /**
     * (validate�O�݌���)<BR>
     * �ڋq���w�肵���ʉ݂Ō��ς��\���ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�O�������������J�݂���Ă��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     *     this.validate�O�����������J��()���R�[������B<BR>
     * <BR>
     *     [����]<BR>
     *     �⏕�����F ����.�⏕����<BR>
     * <BR>
     * �Q�j�ȉ��̏����ŁA�O�݌ڋq����c�����烌�R�[�h���擾����B<BR>
     * <BR>
     *    [��������]<BR>
     *    ����ID�F ����.�⏕����.getAccountId()�̖߂�l<BR>
     *    �⏕����ID�F ����.�⏕����.getSubAccountId()�̖߂�l<BR>
     *    �ʉ݃R�[�h�F ����.�ʉ݃R�[�h<BR>
     * <BR>
     *    ���R�[�h���擾�ł��Ȃ������ꍇ�́A�u�O�݌��ϕs�\�v<BR>
     * �@@�@@�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02095<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h
     * @@throws WEB3BaseException 
     * @@roseuid 428C5B4802A6
     */
    public void validateFcSettle(WEB3GentradeSubAccount l_subAccount,
        String l_strCurrencyCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateFcSettle(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j�O�������������J�݂���Ă��邩�ǂ������`�F�b�N����B 
        
        //this.validate�O�����������J��()���R�[������B
        this.validateFeqAccountEstablish(l_subAccount); 
        //�Q�j�ȉ��̏����ŁA�O�݌ڋq����c�����烌�R�[�h���擾����B 

        //   [��������] 
        //   ����ID�F ����.�⏕����.getAccountId()�̖߂�l 
        //   �⏕����ID�F ����.�⏕����.getSubAccountId()�̖߂�l 
        //   �ʉ݃R�[�h�F ����.�ʉ݃R�[�h
        
        QueryProcessor l_qp = null;
        List l_lisFCashBalance = null;
        try
        {
            String l_strWhere = " account_id = ?  and sub_account_id = ?  and currency_code = ? ";
            Object[] l_bindValues = {
                new Long(l_subAccount.getAccountId()),
                new Long(l_subAccount.getSubAccountId()),
                l_strCurrencyCode};

            l_qp = Processors.getDefaultProcessor();
            
            l_lisFCashBalance = l_qp.doFindAllQuery(FCashBalanceRow.TYPE, l_strWhere, l_bindValues);
        }
        catch (DataFindException l_ex)
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
        //���R�[�h���擾�ł��Ȃ������ꍇ�́A�u�O�݌��ϕs�\�v�̗�O���X���[����
        if(l_lisFCashBalance == null || l_lisFCashBalance.isEmpty())
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02095.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02095,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��������J��)<BR>
     * ��������̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j����.��������3�c�Ɠ�����Z�o����B<BR>
     * <BR>
     * �Q�j�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *    ����.�⏕����.getMainAccount()���R�[������B<BR>
     * <BR>
     * �R�j��������̊J�݃`�F�b�N���s���B<BR>
     * <BR>
     *    �ڋq.is��������J��()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    ��n���F �P�j�ŎZ�o�������t<BR>
     *    �⏕�����F ����.�⏕����<BR>
     * <BR>
     *    �߂�l == false �̏ꍇ�A�u����������J�݁v�̗�O���X���[����B<BR>
     *   class: WEB<BR>
     *   tag:  BUSINESS_ERROR_02096<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_datBizDate - (������)<BR>
     * ������
     * @@throws WEB3BaseException 
     * @@roseuid 428C6CBD00A2
     */
    public void validateSpecialAccountEstablish(WEB3GentradeSubAccount l_subAccount,
        Date l_datBizDate) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = 
            " validateSpecialAccountEstablish(WEB3GentradeSubAccount, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j����.��������3�c�Ɠ�����Z�o����B 
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strMarketCode = l_context.getMarketCode();
        
        WEB3GentradeBizDate l_date = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));                
        Date l_datThreeDateLater = l_date.roll(3);
        //�Q�j�ڋq�I�u�W�F�N�g���擾����B 
        //   ����.�⏕����.getMainAccount()���R�[������B 
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        
        //�R�j��������̊J�݃`�F�b�N���s���B 
        //
        //   �ڋq.is��������J��()���R�[������B 
        boolean l_blnIsSpecialAccountEstablished = l_mainAccount.isSpecialAccountEstablished(l_datThreeDateLater, l_subAccount);
        if (!l_blnIsSpecialAccountEstablished)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02096.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02096,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�ב֓o�^)<BR>
     * �בփ��[�g���o�^����Ă��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�ʉ݃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *    �O����������.get�ʉ�()���R�[������B<BR>
     * <BR>
     * �Q�j�בփ��[�g���擾����B<BR>
     * <BR>
     *    �ʉ�.get�בփ��[�g()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    is���t�F ����.is���t<BR>
     *    is���v�Z�F ����.is���<BR>
     *    ���͈בփ��[�g�F 0<BR>
     * <BR>
     * �R�j�擾�������[�g == 0 �̏ꍇ�A<BR>
     *    �u�בփ��[�g���o�^�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02097<BR>
     * @@param l_feqProduct - (�O����������)<BR>
     * �O�����������I�u�W�F�N�g
     * 
     * @@param l_blnIsBuy - (is���t)<BR>
     * ���t�������ǂ����̔���<BR>
     * <BR>
     * true�F ���t<BR>
     * false�F ���t<BR>
     * 
     * 
     * @@param l_blnIsExecuted - (is���)<BR>
     * ��肩�ǂ����̔���<BR>
     * <BR>
     * true�F ���<BR>
     * false�F ����<BR>
     * @@roseuid 428C6ED50054
     */
    public void validateRateRegistration(WEB3FeqProduct l_feqProduct,
        boolean l_blnIsBuy, boolean l_blnIsExecuted) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateRateRegistration(WEB3FeqProduct, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqProduct == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        
        //�P�j�ʉ݃I�u�W�F�N�g���擾����B 
        //   �O����������.get�ʉ�()���R�[������B 
        WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();
        
        //�Q�j�בփ��[�g���擾����B 
        //   �ʉ�.get�בփ��[�g()���R�[������B 
        double l_dlbFxRate = l_genCurrency.getExchangeRate(l_blnIsBuy, l_blnIsExecuted, 0);

        //�R�j�擾�������[�g  0 �̏ꍇ�A 
        //   �u�בփ��[�g���o�^�v�̗�O���X���[����B
        if (l_dlbFxRate == 0)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02097.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02097,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate����)<BR>
     * �����̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�c�Ɠ��`�F�b�N<BR>
     * �@@�������c�Ɠ��łȂ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02149<BR>
     * <BR>
     * �Q�j�@@�������`�F�b�N<BR>
     * �@@�����P��.getExecutions()�ɂāA�֘A������I�u�W�F�N�g���擾����B<BR>
     * �@@���[0].getExecutionTimestamp()�̓��t�������A<BR>
     * �@@�����̖����ƈ�v���Ȃ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02098<BR>
     * 
     * �@@
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��
     * @@param l_datExecutionDate - (����)<BR>
     * ����
     * @@throws WEB3BaseException 
     * @@roseuid 4292A1270227
     */
    public void validateExecutionDate(WEB3FeqOrderUnit l_orderUnit,
        Date l_datExecutionDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecutionDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        if(l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j�@@�c�Ɠ��`�F�b�N 
        //  �������c�Ɠ��łȂ��ꍇ�A��O���X���[����B
        boolean l_blnIsFeqBizDate = WEB3FeqDateUtility.isFeqBizDate(new Timestamp(l_datExecutionDate.getTime()));
        if (!l_blnIsFeqBizDate)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02149.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02149,
                this.getClass().getName() + "." + STR_METHOD_NAME); 
        }

        //�Q�j�@@�������`�F�b�N 
        //  �����P��.getExecutions()�ɂāA�֘A������I�u�W�F�N�g���擾����B 
        //  ���[0].getExecutionTimestamp()�̓��t�������A�����̖����ƈ�v���Ȃ��ꍇ�A��O���X���[����B
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
        
        if (l_orderExecutions == null || l_orderExecutions.length == 0)
        {
            return;
        }
        int l_intFlay = WEB3DateUtility.compareToDay(l_orderExecutions[0].getExecutionTimestamp(), l_datExecutionDate);
        if (l_intFlay != 0)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02098.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02098,
                this.getClass().getName()  + "." + STR_METHOD_NAME); 
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���n��n��)<BR>
     * ���n��n�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����茻�n��n���擾<BR>
     * �@@�����P��.getExecutions()�ɂāA�������擾����B<BR>
     * <BR>
     * �Q�j�@@���n��n���擾<BR>
     * �@@�擾�����e���̌��n��n���ƈ����̌��n��n�����r���A<BR>
     * �@@��ł��s��v������Η�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02099<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��
     * @@param l_datFDeliveryDate - (���n��n��)<BR>
     * ���n��n��
     * @@throws WEB3BaseException 
     * @@roseuid 42B6678C029F
     */
    public void validateFDeliveryDate(WEB3FeqOrderUnit l_orderUnit,
        Date l_datFDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateFDeliveryDate(WEB3FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j�@@����茻�n��n���擾 
        //  �����P��.getExecutions()�ɂāA�������擾����B
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
         
        //�Q�j�@@���n��n���擾 
        //  �擾�����e���̌��n��n���ƈ����̌��n��n�����r���A��ł��s��v������Η�O���X���[����B 
        for (int i = 0; i < l_orderExecutions.length; i++)
        {
            int l_intFlay = WEB3DateUtility.compareToDay(l_orderExecutions[i].getDeliveryDate(), l_datFDeliveryDate);
            if (l_intFlay != 0)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02099.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02099,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��萔��)<BR>
     * ��萔�ʂ��`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����萔�ʎ擾<BR>
     * �@@�����P��.getExecutedQuantity()�ɂāA����萔�ʂ��擾����B<BR>
     * <BR>
     * �Q�j�@@�������ʎ擾<BR>
     * �@@�����P��.getConfirmedQuantity()�ɂāA�s�ꂩ��m�F�ς̐��ʂ��擾����B<BR>
     * <BR>
     * �R�j�@@���ʔ�r<BR>
     * �@@��萔�ʂ��������ʂ𒴉߂��Ă���ꍇ�i����萔�ʁ{��萔�� > <BR>
     * �s�ꂩ��m�F�ς̐��ʁj�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00300<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��
     * @@param l_dblExecutedQuantity - (��萔��)<BR>
     * ��萔�ʁi����j
     * @@roseuid 4292A13000C0
     */
    public void validateExecutedQuantity(WEB3FeqOrderUnit l_orderUnit,
        double l_dblExecutedQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecutedQuantity(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j�@@����萔�ʎ擾 
        //  �����P��.getExecutedQuantity()�ɂāA����萔�ʂ��擾����B 
        double l_dblExeQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExeQuantity))
        {
            l_dblExeQuantity = 0.0D;
        }
        //�Q�j�@@�������ʎ擾 
        //  �����P��.getConfirmedQuantity()�ɂāA�s�ꂩ��m�F�ς̐��ʂ��擾����B 
        double l_dblQuantity = l_orderUnit.getConfirmedQuantity();
        //�R�j�@@���ʔ�r 
        //  ��萔�ʂ��������ʂ𒴉߂��Ă���ꍇ�i����萔�ʁ{��萔�� > �s�ꂩ��m�F�ς̐��ʁj�A��O���X���[����B 
        if (l_dblExeQuantity + l_dblExecutedQuantity > l_dblQuantity)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00300.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00300,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���P��)<BR>
     * ���P�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�����P���擾<BR>
     * �@@�����P��.getConfirmedLimitPrice()�ɂāA�����P�����擾����B<BR>
     * �@@�i�����P�� == 0�j�̏ꍇ�A�������I������B�ireturn;�j<BR>
     * <BR>
     * �Q�j�@@������ʎ擾<BR>
     * �@@�����P��.getOrderType()�ɂāA������ʂ��擾����B<BR>
     * <BR>
     * �R�j�@@�P����r<BR>
     * �@@�@@�|�������̏ꍇ�i�����P��.getOrderType() == <BR>
     * �@@�@@�������.701�F�O�������j<BR>
     * �@@�@@�@@�i�����P��.getConfirmedLimitPrice() < ���P���j�̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_02100<BR>
     * �@@�@@<BR>
     * �@@�@@�|�������̏ꍇ�i�����P��.getOrderType() == <BR>
     * �@@�@@�������.702�F�O������j<BR>
     * �@@�@@�@@�i�����P��.getConfirmedLimitPrice() > ���P���j�̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:    BUSINESS_ERROR_02101<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��
     * @@param l_dblExecutedPrice - (���P��)<BR>
     * ���P��
     * @@throws WEB3BaseException 
     * @@roseuid 42C39E5C00EF
     */
    public void validateExecutedPrice(WEB3FeqOrderUnit l_orderUnit,
        double l_dblExecutedPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateExecutedPrice(WEB3FeqOrderUnit, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        
        //�P�j�@@�����P���擾 
        // �����P��.getConfirmedLimitPrice()�ɂāA�������ʂ��擾����B 
        //�i�����P�� == 0�j�̏ꍇ�A�������I������B�ireturn;�j 
        double l_dblLimitPrice = l_orderUnit.getConfirmedPrice();
        if (l_dblLimitPrice == 0)
        {
            log.debug("return");
            return;
        }
         
        //�Q�j�@@������ʎ擾 
        // �����P��.getOrderCateg()�ɂāA������ʂ��擾����B 
        //�R�j�@@�P����r 
        // �������̏ꍇ�i�����P��.getOrderCateg() == �������.701�F�O�������j 
        //�i�����P��.getConfirmedLimitPrice() < ���P���j�̏ꍇ�A��O���X���[����B
        OrderTypeEnum l_orderTypeEnum = l_orderUnit.getOrderType(); 
        if (OrderTypeEnum.FEQ_BUY.equals(l_orderTypeEnum) && l_dblLimitPrice < l_dblExecutedPrice)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02100.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02100,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        //�|�������̏ꍇ�i�����P��.getOrderCateg() == �������.702�F�O������j 
        //�i�����P��.getConfirmedLimitPrice() > ���P���j�̏ꍇ�A��O���X���[����B 
        else if(OrderTypeEnum.FEQ_SELL.equals(l_orderTypeEnum) && l_dblLimitPrice > l_dblExecutedPrice)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02101.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02101,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���������\���)<BR>
     * �������\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * this.validate��������\���(�p�����[�^.����ID)�ɈϏ�(delegate)����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@throws WEB3BaseException 
     * @@roseuid 4295FBF40109
     */
    public void validateOrderChangePossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateOrderChangePossibleStatus(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate��������\���(�p�����[�^.����ID)�ɈϏ�(delegate)����B 
        this.validateOrderCancelPossibleStatus(l_lngOrderId);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��������\���)<BR>
     * ������\�Ȓ�����Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * �P�j�����P�ʃI�u�W�F�N�g�̎擾<BR>
     * <BR>
     *    �O�����������}�l�[�W��.getOrderUnits()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    ����ID�F ����.����ID<BR>
     * <BR>
     *    �擾�����z��̍ŏ��̗v�f���擾����B<BR>
     * <BR>
     * �Q�j�����L����Ԃ̃`�F�b�N<BR>
     * <BR>
     *    �����P��.�����L����� != �hOPEN�h<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00858<BR>
     * <BR>
     * �R�j������ԁA���[�����M�󋵂̃`�F�b�N<BR>
     * <BR>
     * �s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B<BR>
     * <BR>
     * �R�|�P�j�s��J�ǎ��ԑсi������ԊǗ�.<BR>
     * �@@�@@is�s��J�ǎ��ԑ�( )== true�j�̏ꍇ<BR>
     * <BR>
     *    ������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A<BR>
     * �@@��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00155<BR>
     * <BR>
     *       ACCEPTED(*)<BR>
     *       CANCEL_ACCEPTED<BR>
     *       CANCELLING<BR>
     *       MODIFY_ACCEPTED<BR>
     *       MODIFYING<BR>
     *       MODIFIED���s�ꂩ��m�F�ς̐���=null<BR>
     *       ORDERING<BR>
     * <BR>
     *       (*)�����P��.�������� == "�t�w�l"�̏ꍇ�́A����\�Ƃ���B<BR>
     * <BR>
     * �R�|�Q�j�s��ǎ��ԑсi������ԊǗ�.<BR>
     * �@@is�s��J�ǎ��ԑ�( ) == false�j�̏ꍇ<BR>
     * <BR>
     *    ������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A<BR>
     * �@@��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00155<BR>
     * <BR>
     *       CANCEL_ACCEPTED<BR>
     *       CANCELLING<BR>
     *       MODIFY_ACCEPTED<BR>
     *       MODIFYING<BR>
     *       ORDERING<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@throws WEB3BaseException 
     * @@roseuid 42A7A8C50285
     */
    public void validateOrderCancelPossibleStatus(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateOrderCancelPossibleStatus(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�����P�ʃI�u�W�F�N�g�̎擾 
        //
        //   �O�����������}�l�[�W��.getOrderUnits()���R�[������B 
        //
        //   [����] 
        //   ����ID�F ����.����ID 
        //
        //   �擾�����z��̍ŏ��̗v�f���擾����B 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        OrderUnit[] l_orderUnits = l_feqOrderManager.getOrderUnits(l_lngOrderId);
        if (l_orderUnits == null)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];
        boolean l_confirmedPrice = l_orderUnit.isConfirmedPriceMarketOrder();
                        
        //�Q�j�����L����Ԃ̃`�F�b�N 
        //
        //   �����P��.�����L����� != �hOPEN�h 
        //
        //   �̏ꍇ�A��O���X���[����B 
        if (!(OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus())))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00858.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00858,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        //�R�j������ԁA���[�����M�󋵂̃`�F�b�N 
        //�s��J�ǁ^�ǂɂ���Ĉȉ��̒ʂ�`�F�b�N���s���B 
        
        //�R�|�P�j�s��J�ǎ��ԑсi������ԊǗ�.is�g���K���s( ) == true�j�̏ꍇ 
        //   ������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A��O���X���[����B 
        //
        //      ACCEPTED(*) 
        //      CANCEL_ACCEPTED 
        //      CANCELLING 
        //      MODIFY_ACCEPTED 
        //      MODIFYING 
        //      ORDERING 
        //
        //      (*)�����P��.�������� == "�t�w�l"�̏ꍇ�́A����\�Ƃ���B
        OrderStatusEnum l_orderStatusEnum = l_orderUnit.getOrderStatus(); 
        if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null))
        {
            if (OrderStatusEnum.ACCEPTED.equals(l_orderStatusEnum) && 
                !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(
                ((FeqOrderUnitRow)l_orderUnit.getDataSourceObject()).getOrderConditionType())
                || (OrderStatusEnum.MODIFIED.equals(l_orderStatusEnum) && l_confirmedPrice)
                || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.CANCELLING.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum)
                || OrderStatusEnum.ORDERING.equals(l_orderStatusEnum))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02203.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02203,
                    this.getClass().getName() + "." + STR_METHOD_NAME);    
            }
        }  
        //�R�|�Q�j�s��ǎ��ԑсi������ԊǗ�.is�g���K���s( ) == false�j�̏ꍇ 
        //
        //   ������Ԃ��ȉ��̂����ꂩ�ɊY������ꍇ�͎���s�Ƃ��A��O���X���[����B 
        //
        //      CANCEL_ACCEPTED 
        //      CANCELLING 
        //      MODIFY_ACCEPTED 
        //      MODIFYING 
        //      ORDERING 
        else
        {
            if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.CANCELLING.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum)
                || OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum)
                || OrderStatusEnum.ORDERING.equals(l_orderStatusEnum))
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02203.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02203,
                    this.getClass().getName() + "." + STR_METHOD_NAME);    
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�s��)<BR>
     * �s��̃`�F�b�N���s���B<BR>
     * <BR>
     * �s��.�����~ == �h��~���h�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01747<BR>
     * @@param l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g
     * @@throws WEB3BaseException 
     * @@roseuid 429608960241
     */
    public void validateMarket(WEB3GentradeMarket l_market) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateMarket(WEB3GentradeMarket)";
        log.entering(STR_METHOD_NAME);
        
        if(l_market == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�s��.�����~ == �h��~���h�̏ꍇ�A��O���X���[����B 
        MarketRow l_row = (MarketRow) l_market.getDataSourceObject();
        
        if (WEB3SuspensionDef.SUSPENSION.equals(l_row.getSuspension()))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01747.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01747,
                this.getClass().getName() + "." + STR_METHOD_NAME);    
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�������e)<BR>
     * �������͒l���Ó��ł��邩���`�F�b�N����B<BR>
     * <BR>
     * �P�j���ʂ̃`�F�b�N<BR>
     * <BR>
     *    isChange����()���R�[�����A<BR>
     *    ���ʂɒ��������������𔻒�A�������ʂ̑Ó������`�F�b�N����B<BR>
     * <BR>
     *    [isChange����()�Ɏw�肷�����]<BR>
     *    �����P�ʁF �����P��<BR>
     *    �������ʁF ��������<BR>
     * <BR>
     * �Q�j�����P���̔���<BR>
     * <BR>
     *    isChange�P��()���R�[�����A<BR>
     *    �����P���ɒ��������������𔻒肷��B<BR>
     * <BR>
     *    [isChange�P��()�Ɏw�肷�����]<BR>
     *    �����P�ʁF �����P��<BR>
     *    �����P���F �����P�� <BR>
     * <BR>
     * 3�j���s�����̔���<BR>
     * <BR>
     *    isChange���s����()���R�[�����A<BR>
     *    ���s�����ɒ��������������𔻒肷��B<BR>
     * <BR>
     *    [isChange���s����()�Ɏw�肷�����]<BR>
     *    �����P�ʁF �����P��<BR>
     *    �������s�����F �������s����<BR>
     * <BR
     * �S�j���������`�F�b�N�i���������s�̎s��̂݁j<BR>
     *   �S�|�P�j<BR>
     * �����P��.�s��h�c�ɊY������s��I�u�W�F�N�g���擾����B<BR> 
     *   �S�|2�j<BR>
     * �擾�����s��.���������\�敪==�h�������ړ��������s�h and<BR>
     * �i�i �P�j�̌��� == true and �Q�j�̌��� == true �j <BR>
     * or �i �P�j�̌��� == true and �R�j�̌��� == true �j�j<BR>
     * �̏ꍇ�A��O���X���[����B    <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00687<BR>    
>
     * �T�j������������������Ă��Ȃ����Ƃ̃`�F�b�N<BR>
     * <BR>
     *    isChange��������()���R�[�����A���������ɒ��������������𔻒肷��B<BR>
     * <BR>
     *    [isChange��������()�Ɏw�肷�����]<BR>
     *    �����P�ʁF �����P��<BR>
     *    �������������F ��������<BR>
     * <BR>
     * �U�j�o����܂Œ�������������Ă��Ȃ����Ƃ̃`�F�b�N<BR>
     * <BR>
     *    �������������ɂ��ā� <BR>
     *     ����.�����P��.���񒍕��̒����P��ID == null �̏ꍇ�A�������� <BR>
     *     ����.�����P��.���񒍕��̒����P��ID != null �̏ꍇ�A�o����܂Œ���<BR>
     *    ��������̒����ɂ��ā�<BR>
     *     ����.���������敪 == �h��������h �̏ꍇ�A�������� <BR>
     *     ����.���������敪 == �h�o����܂Œ����h �̏ꍇ�A�o����܂Œ���<BR> 
     *    �������ƒ�����̒��������敪�i�o����܂Œ������ǂ����j<BR>
     *    ����v���Ȃ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02102<BR>
     * <BR>
     * �V�j�i�����ς݂́j�t�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N<BR>
     * <BR>
     *    isChange�t�w�l����()���R�[�����A<BR>
     *    �t�w�l�����ɒ��������������𔻒肷��B<BR>
     * <BR>
     *    [isChange�t�w�l����()�Ɏw�肷�����]<BR>
     *    �����P�ʁF �����P��<BR>
     *    �������������F ��������<BR>
     *    ���������������Z�q�F ���������������Z�q<BR>
     *    �������������P���F �������������P��<BR>
     * <BR>
     * �W�jW�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N<BR>
     * <BR>
     *    isChangeW�w�l����()���R�[�����A<BR>
     *    W�w�l�����ɒ��������������𔻒肷��B<BR>
     * <BR>
     *    [isChangeW�w�l����()�Ɏw�肷�����]<BR>
     *    �����P�ʁF �����P��<BR>
     *    �������������F ������������<BR>
     *    ���������������Z�q�F ���������������Z�q<BR>
     *    �������������P���F �������������P��<BR>
     *    �����iW�w�l�j�����w�l�F �����iW�w�l�j�����w�l<BR>
     * <BR>
     * �X�j�����L����������������Ă��Ȃ����Ƃ̃`�F�b�N<BR>
     * <BR>
     *    isChange�����L������()���R�[�����A <BR>
     *    �����L�������ɒ��������������𔻒肷��B <BR>
     * <BR>
     *    [isChange�����L������()�Ɏw�肷�����]<BR>
     *    �����P�ʁF �����P��<BR>
     *    ���������L�������F ���������L������<BR>
     * <BR>
     * �P�O�j�����������Ă��邩�̃`�F�b�N<BR>
     * <BR>
     *    isChange����()�AisChange�P��()�A<BR>
     *    isChange���s����()�AisChange�t�w�l����()�A<BR>
     *    isChangeW�w�l����()�AisChange�����L������()<BR>
     * <BR>
     *    �̂��ׂĂ�false��ԋp�����ꍇ�A<BR> 
     *    �������������牽����������Ă��Ȃ��Ɣ��f���A<BR>
     *    �u�������͂Ȃ��v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02103<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_dblChangeQuantity - (��������)<BR>
     * ��������
     * 
     * @@param l_dblChangePrice - (�����P��)<BR>
     * �����P��
     * 
     * @@param l_strChangeExecCondType - (�������s����)<BR>
     * �������s����
     * 
     * @@param l_strChangeOrderExpirationDiv - ( �������������敪)<BR>
     * �������������敪 
     * 
     * @@param l_datChangeExpirationDate - (���������L������)<BR>
     * ���������L������
     * 
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������
     * 
     * @@param l_strOrderCondOperator - (���������������Z�q)<BR>
     * ���������������Z�q
     * 
     * @@param l_dblChangeOrderConditionTypePrice - (�������������P��)<BR>
     * �������������P��
     * 
     * @@param l_dblChangeWLimitPrice - (�����iW�w�l�j�����w�l)<BR>
     * �����iW�w�l�j�����w�l
     * @@throws WEB3BaseException 
     * @@roseuid 42974965002F
     */
    public void validateChangeSpec(FeqOrderUnit l_orderUnit,
        double l_dblChangeQuantity, 
        double l_dblChangePrice, 
        String l_strChangeExecCondType,
        String l_strChangeOrderExpirationDiv, 
        Date l_datChangeExpirationDate, 
        String l_strOrderConditionType, 
        String l_strOrderCondOperator, 
        double l_dblChangeOrderConditionTypePrice, 
        double l_dblChangeWLimitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangeSpec()";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j���ʂ̃`�F�b�N 
        //
        //   isChange����()���R�[�����A 
        //   ���ʂɒ��������������𔻒�A�������ʂ̑Ó������`�F�b�N����B 
        //
        //   [isChange����()�Ɏw�肷�����] 
        //   �����P�ʁF �����P�� 
        //   �������ʁF �������� 
        boolean l_blnIsQuantity = this.isChangeQuantity(l_orderUnit, l_dblChangeQuantity);
        //�Q�j�����P���̔��� 
        //
        //   isChange�P��()���R�[�����A 
        //   �����P���ɒ��������������𔻒肷��B 
        //
        //   [isChange�P��()�Ɏw�肷�����] 
        //   �����P�ʁF �����P�� 
        //    �����P���F �����P�� 
        boolean l_blnIsPrice = this.isChangePrice(l_orderUnit, l_dblChangePrice);
        //�R�j���s�����̔��� 
        //
        //   isChange���s����()���R�[�����A 
        //   ���s�����ɒ��������������𔻒肷��B 
        //
        //   [isChange���s����()�Ɏw�肷�����] 
        //   �����P�ʁF �����P�� 
        //   �������s�����F �������s���� 
        boolean l_blnIsConditionType =
            this.isChangeExecutionConditionType(l_orderUnit, l_strChangeExecCondType);
        //�S�j���������`�F�b�N�i���������s�̎s��̂݁j 
        //
        //�S�|�P�j 
        //   �����P��.�s��h�c�ɊY������s��I�u�W�F�N�g���擾����B         
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeMarket l_market = null;
        FeqOrderUnitRow l_feqOrderUnitRow = null;
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();  
        
        l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        try
        {

            l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_feqOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_nfe)
        {
            log.debug(WEB3ErrorCatalog.SYSTEM_ERROR_80005.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_nfe.getMessage(),
               l_nfe);
        }                                    

        //�S�|�Q�j 
        //   �擾�����s��.���������\�敪==�h�������ړ��������s�h and 
        //   �i�i �P�j�̌��� == true and �Q�j�̌��� == true �j or �i �P�j�̌��� == true and �R�j�̌��� == true �j�j 
        //
        //   �̏ꍇ�A��O���X���[����B 
        MarketRow l_marketRow = (MarketRow) l_market.getDataSourceObject();
        if ((WEB3ChangeableTypeDef.CANNOT_CHANGE.equals(l_marketRow.getChangeableType())) && 
                ((l_blnIsQuantity && l_blnIsPrice) || (l_blnIsQuantity && l_blnIsConditionType)))

        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00687.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00687,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�T�j������������������Ă��Ȃ����Ƃ̃`�F�b�N 
        //
        //   isChange��������()���R�[�����A���������ɒ��������������𔻒肷��B 
        //
        //   [isChange��������()�Ɏw�肷�����] 
        //   �����P�ʁF �����P�� 
        //   �������������F �������� 
        this.isChangeOrderConditionType(l_orderUnit, l_strOrderConditionType);
        //�U�j���������敪����������Ă��Ȃ����Ƃ̃`�F�b�N 
        //
        //   �������������ɂ��ā� 
        //      ����.�����P��.���񒍕��̒����P��ID == null �̏ꍇ�A�������� 
        //      ����.�����P��.���񒍕��̒����P��ID != null �̏ꍇ�A�o����܂Œ��� 
        //
        //   ��������̒����ɂ��ā� 
        //      ����.���������敪 == �h��������h �̏ꍇ�A�������� 
        //      ����.���������敪 == �h�o����܂Œ����h �̏ꍇ�A�o����܂Œ��� 
        //
        //   �������ƒ�����̒��������敪�i�o����܂Œ������ǂ����j����v���Ȃ��ꍇ�A��O���X���[����B 
        if((l_feqOrderUnitRow.getFirstOrderUnitIdIsNull() && WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strChangeOrderExpirationDiv))
            || (!l_feqOrderUnitRow.getFirstOrderUnitIdIsNull() && WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_strChangeOrderExpirationDiv)))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02102.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02102,
                this.getClass().getName() + "." + STR_METHOD_NAME);        
        }
        //�V�j�i�����ς݂́j�t�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N 
        //
        //   isChange�t�w�l����()���R�[�����A 
        //   �t�w�l�����ɒ��������������𔻒肷��B 
        //
        //   [isChange�t�w�l����()�Ɏw�肷�����] 
        //   �����P�ʁF �����P�� 
        //   �������������F �������� 
        //   ���������������Z�q�F ���������������Z�q 
        //   �������������P���F �������������P�� 
        boolean l_blnIsStopCond =
            this.isChangeStopCond(l_orderUnit, l_strOrderConditionType, l_strOrderCondOperator, l_dblChangeOrderConditionTypePrice);            
        //�W�jW�w�l��������������Ă��Ȃ����Ƃ̃`�F�b�N 
        //
        //   isChangeW�w�l����()���R�[�����A 
        //   W�w�l�����ɒ��������������𔻒肷��B 
        //
        //   [isChangeW�w�l����()�Ɏw�肷�����] 
        //   �����P�ʁF �����P�� 
        //   �������������F ������������ 
        //   ���������������Z�q�F ���������������Z�q 
        //   �������������P���F �������������P�� 
        //   �����iW�w�l�j�����w�l�F �����iW�w�l�j�����w�l 
        boolean l_blnIsWLimitPriceCond = this.isChangeWLimitPriceCond(l_orderUnit, 
            l_strOrderConditionType, 
            l_strOrderCondOperator, 
            l_dblChangeOrderConditionTypePrice,
            l_dblChangeWLimitPrice);
        //�X�j�����L����������������Ă��Ȃ����Ƃ̃`�F�b�N 
        //
        //   isChange�����L������()���R�[�����A  
        //   �����L�������ɒ��������������𔻒肷��B  
        //
        //   [isChange�����L������()�Ɏw�肷�����] 
        //   �����P�ʁF �����P�� 
        //   ���������L�������F ���������L������ 
        boolean l_blnIsOrderExpirationDate =
            this.isChangeOrderExpirationDate(l_orderUnit, l_datChangeExpirationDate);
        //�P�O�j�����������Ă��邩�̃`�F�b�N 
        //
        //   isChange����()�AisChange�P��()�A 
        //   isChange���s����()�AisChange�t�w�l����()�A 
        //   isChangeW�w�l����()�AisChange�����L������() 
        //
        //   �̂��ׂĂ�false��ԋp�����ꍇ�A  
        //   �������������牽����������Ă��Ȃ��Ɣ��f���A 
        //   �u�������͂Ȃ��v�̗�O���X���[����B 
        if (!l_blnIsQuantity && !l_blnIsPrice && !l_blnIsConditionType &&
            !l_blnIsStopCond  && !l_blnIsWLimitPriceCond && !l_blnIsOrderExpirationDate)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02103.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02103,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (isChange����)<BR>
     * ���ʂɒ����������Ă��邩���肷��B<BR>
     * <BR>
     * �P�j�����P��.getQuantity == �������� �̏ꍇ<BR>
     * <BR>
     *    false��ԋp����B<BR>
     * <BR>
     * �Q�j�ȊO�̏ꍇ<BR>
     * <BR>
     *    �ȉ��̃`�F�b�N���s���Atrue��ԋp����B<BR>
     * <BR>
     *    �|�i��萔��(*1)�@@���@@�������ʁj�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_00142<BR>
     * <BR>
     *    �|�i��������(*2)�@@���@@�������ʁj�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00143<BR>
     * <BR>
     * <BR>
     *    (*1) ��萔��<BR>
     *    �����P��.getExecutedQuantity()<BR>
     * <BR>
     *    (*2) ��������<BR>
     *    �����P��.getQuantity()<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_dblChangeQuantity - (��������)<BR>
     * ��������
     * 
     * @@return Boolean
     * @@roseuid 42974DA900AC
     */
    protected boolean isChangeQuantity(FeqOrderUnit l_orderUnit,
        double l_dblChangeQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isChangeQuantity(FeqOrderUnit l_orderUnit, double l_dblChangeQuantity)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�P�j�����P��.getQuantity == �������� �̏ꍇ 
        //   false��ԋp����B
        double l_dblQuantity = l_orderUnit.getQuantity(); 
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0;
        }
        
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0;
        }
        if (l_dblQuantity == l_dblChangeQuantity)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //�Q�j�ȊO�̏ꍇ 
        //
        //   �ȉ��̃`�F�b�N���s���Atrue��ԋp����B 
        //
        //   �|�i��萔��(*1)�@@���@@�������ʁj�̏ꍇ�A��O���X���[����B 
        //   �|�i��������(*2)�@@���@@�������ʁj�̏ꍇ�A��O���X���[����B 
        //
        //   (*1) ��萔�� 
        //   �����P��.getExecutedQuantity()     
        //
        //   (*2) �������� 
        //   �����P��.getQuantity() 
        else 
        {
            if (l_dblExecutedQuantity > l_dblChangeQuantity)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02104.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00142,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if(l_dblQuantity < l_dblChangeQuantity)
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02105.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00143,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
   
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }        
    }
    
    /**
     * (isChange�P��)<BR>
     * �P���ɒ����������Ă��邩���肷��B<BR>
     * <BR>
     * �����P��.getLimitPrice == �����P�� �̏ꍇ�Afalse��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_dblChangePrice - (�����P��)<BR>
     * �����P��
     * 
     * @@return Boolean
     * @@roseuid 42974EA702DE
     */
    protected boolean isChangePrice(FeqOrderUnit l_orderUnit, double l_dblChangePrice) 
    {
        final String STR_METHOD_NAME = 
            " isChangePrice(FeqOrderUnit l_orderUnit, double l_dblChangePrice)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        //�����P��.getLimitPrice == �����P�� �̏ꍇ�Afalse��ԋp����B 
        //�ȊO�Atrue��ԋp����B
        double l_dblLimitPrice = l_orderUnit.getLimitPrice();
        if (Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0;
        }
        if (l_dblLimitPrice == l_dblChangePrice)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }   
    }
    
    /**
     * (isChange���s����)<BR>
     * ���s�����ɒ����������Ă��邩���肷��B<BR>
     * <BR>
     * �����P��.���s���� == �������s���� �̏ꍇ�Afalse��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_strChangeExecCondType - (�������s����)<BR>
     * �������s����
     * 
     * @@return Boolean
     * @@throws WEB3BaseException 
     * @@roseuid 42974ED5038A
     */
    protected boolean isChangeExecutionConditionType(FeqOrderUnit l_orderUnit,
        String l_strChangeExecCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isChangeExecutionConditionType(FeqOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //�����P��.���s���� == �������s���� �̏ꍇ�Afalse��ԋp����B 
        //�ȊO�Atrue��ԋp����B
        
        FeqExecutionConditionType l_execCondType 
                        = l_orderUnit.getExecutionConditionType();
        String l_strExecCondType = new Integer(l_execCondType.intValue()).toString();
        
        if (l_strExecCondType.equals(l_strChangeExecCondType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        } 
        
    }
    
    /**
     * (isChange��������)<BR>
     * ���������ɒ����������Ă��邩���肷��B<BR>
     * <BR>
     * �����P��.�������� == ������������ �̏ꍇ�Afalse��ԋp����B<BR>
     * �ȊO�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02106<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_strChangeOrderCondType - (������������)<BR>
     * ������������
     * 
     * @@return boolean
     * @@throws WEB3BaseException 
     * @@roseuid 42974F47035B
     */
    protected boolean isChangeOrderConditionType(FeqOrderUnit l_orderUnit,
        String l_strChangeOrderCondType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isChangeOrderConditionType(FeqOrderUnit l_orderUnit, String l_strChangeOrderCondType)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //�����P��.�������� == ������������ �̏ꍇ�Afalse��ԋp����B 
        //�ȊO�A��O���X���[����B 
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();

        if (l_strChangeOrderCondType.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;            
        }
        else
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02106.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02106,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
               
    }
    
    /**
     * (isChange�t�w�l����)<BR>
     * �t�w�l�̏����i�����������Z�q�A���������P���j<BR>
     * �@@�ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.������������ != �h�t�w�l�h�̏ꍇ�́A<BR>
     * �@@�ȍ~�̏����͍s�킸true��Ԃ��B<BR>
     * <BR>
     * �Q�j����.������������ == �h�t�w�l�h�̏ꍇ<BR>
     * <BR>
     *    �����ς݂̋t�w�l�����i�����P��.<BR>
     * �@@�s�ꂩ��m�F�ς݂̐��� != Double.NaN�j�̏ꍇ�A <BR>
     * <BR>
     *    �����P��.�������� == ������������ and<BR>
     *    �����P��.�����������Z�q == ���������������Z�q and<BR>
     *    �����P��.�t�w�l��l == �������������P��<BR>
     * <BR>
     *    ��L�����ɂ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02107<BR>
     * <BR>
     * �R�j<BR>
     *    �����P��.�������� == ������������ and<BR>
     *    �����P��.�����������Z�q == ���������������Z�q and<BR>
     *    �����P��.�t�w�l��l == �������������P��<BR>
     * <BR>
     *    �̏ꍇ�Afalse��ԋp����B<BR>
     *    �ȊO�́Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_strChangeOrderCondType - (������������)<BR>
     * ������������
     * 
     * @@param l_strChangeOrderCondOperator - (���������������Z�q)<BR>
     * ���������������Z�q
     * 
     * @@param l_dblChangeOrderCondPrice - (�������������P��)<BR>
     * �������������P��
     * 
     * @@return Boolean
     * @@roseuid 42974FA401D5
     */
    protected boolean isChangeStopCond(FeqOrderUnit l_orderUnit,
        String l_strChangeOrderCondType,
        String l_strChangeOrderCondOperator,
        double l_dblChangeOrderCondPrice) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = 
            " isChangeStopCond(FeqOrderUnit, String, String, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        //�P�j����.������������ != �h�t�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸true��Ԃ��B 
        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strChangeOrderCondType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;   
        }
        //�Q�j����.������������ == �h�t�w�l�h�̏ꍇ 
        //
        //   �����ς݂̋t�w�l�����i�����P��.�s�ꂩ��m�F�ς݂̐��� != Double.NaN�j�̏ꍇ�A  
        //
        //   �����P��.�������� == ������������ and 
        //   �����P��.�����������Z�q == ���������������Z�q and 
        //   �����P��.�t�w�l��l == �������������P�� 
        //
        //   ��L�����ɂ��Ă͂܂�Ȃ��ꍇ�͗�O���X���[����B
        else 
        {
            if (!Double.isNaN(l_orderUnit.getConfirmedQuantity()))
            {
                if (!(l_strChangeOrderCondType.equals(l_feqOrderUnitRow.getOrderConditionType()) &&
                    l_strChangeOrderCondOperator.equals(l_feqOrderUnitRow.getOrderCondOperator()) &&
                    l_feqOrderUnitRow.getStopOrderPrice() == l_dblChangeOrderCondPrice))
                {
                    log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02107.getErrorMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02107,
                        this.getClass().getName() + "." + STR_METHOD_NAME);   
                }
            }
        }
  
        //
        //�R�j 
        //   �����P��.�������� == ������������ and 
        //   �����P��.�����������Z�q == ���������������Z�q and 
        //   �����P��.�t�w�l��l == �������������P�� 
        //
        //   �̏ꍇ�Afalse��ԋp����B 
        //   �ȊO�́Atrue��ԋp����B 
        if (l_strChangeOrderCondType.equals(l_feqOrderUnitRow.getOrderConditionType()) &&
            l_strChangeOrderCondOperator.equals(l_feqOrderUnitRow.getOrderCondOperator()) &&
              l_feqOrderUnitRow.getStopOrderPrice() == l_dblChangeOrderCondPrice)
        {
            log.exiting(STR_METHOD_NAME);
            return false;                
        }
        else 
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }     
    }
    
    /**
     * (isChangeW�w�l����)<BR>
     * W�w�l�̏����i�����������Z�q�A���������P���A<BR>
     * �@@�iW�w�l�j�����w�l�j�ɒ����������������`�F�b�N����B<BR>
     * <BR>
     * �P�j����.������������ != �hW�w�l�h�̏ꍇ�́A<BR>
     * �@@�ȍ~�̏����͍s�킸true��Ԃ��B<BR>
     * <BR>
     * �Q�j����.������������ == �hW�w�l�h�̏ꍇ<BR>
     * <BR>
     *    �����P��.�������� == ������������ and<BR>
     *    �����P��.�����������Z�q == ���������������Z�q and<BR>
     *    �����P��.�t�w�l��l == �������������P��<BR>
     *    �����P��.�iW�w�l�j�����w�l == �����iW�w�l�j�����w�l<BR>
     * <BR>
     *    �̏ꍇ�Afalse��ԋp����B<BR>
     *    �ȊO�́Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_strChangeBizCondType - (������������)<BR>
     * ������������
     * 
     * @@param l_strChangeBizCondOperation - (���������������Z�q)<BR>
     * ���������������Z�q
     * 
     * @@param l_dblChangeBizCondPrice - (�������������P��)<BR>
     * �������������P��
     * 
     * @@param l_dblWLimitPrice - (�����iW�w�l�j�����w�l)<BR>
     * �����iW�w�l�j�����w�l
     * 
     * @@return Boolean
     * @@roseuid 429752140252
     */
    protected boolean isChangeWLimitPriceCond(FeqOrderUnit l_orderUnit,
        String l_strChangeBizCondType,
        String l_strChangeBizCondOperation, 
        double l_dblChangeBizCondPrice, 
        double l_dblWLimitPrice) 
    {
        final String STR_METHOD_NAME = 
            " isChangeWLimitPriceCond(FeqOrderUnit, String, String, double, double)";
        log.entering(STR_METHOD_NAME);
       
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        //�P�j����.������������ != �hW�w�l�h�̏ꍇ�́A�ȍ~�̏����͍s�킸true��Ԃ��B 
        if (!(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strChangeBizCondType)))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //�Q�j����.������������ == �hW�w�l�h�̏ꍇ 
        //
        //   �����P��.�������� == ������������ and 
        //   �����P��.�����������Z�q == ���������������Z�q and 
        //   �����P��.�t�w�l��l == �������������P�� 
        //   �����P��.�iW�w�l�j�����w�l == �����iW�w�l�j�����w�l 
        //
        //   �̏ꍇ�Afalse��ԋp����B 
        //   �ȊO�́Atrue��ԋp����B 
        else
        {
            if (l_strChangeBizCondType.equals(l_feqOrderUnitRow.getOrderConditionType()) &&
                    l_strChangeBizCondOperation.equals(l_feqOrderUnitRow.getOrderCondOperator()) &&
                    l_feqOrderUnitRow.getStopOrderPrice() == l_dblChangeBizCondPrice &&
                    l_feqOrderUnitRow.getWLimitPrice() == l_dblWLimitPrice)
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
    }
    
    /**
     * (isChange�����L������)<BR>
     * �����L�������i�����������j�ɒ����������Ă��邩���肷��B<BR>
     * <BR>
     * �����P��.�����������t == ���������L������ �̏ꍇ�A<BR>
     * �@@false��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g
     * 
     * @@param l_datChangeOrderExpirationDate - (���������L������)<BR>
     * ���������L������
     * 
     * @@return Boolean
     * @@roseuid 429752B40252
     */
    protected boolean isChangeOrderExpirationDate(FeqOrderUnit l_orderUnit, Date l_datChangeOrderExpirationDate) 
    {
        final String STR_METHOD_NAME = 
            " isChangeOrderExpirationDate(FeqOrderUnit, Date)";
        log.entering(STR_METHOD_NAME);
        
        if(l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }
        
        //�����P��.�����������t == ���������L������ �̏ꍇ�Afalse��ԋp����B 
        //�ȊO�Atrue��ԋp����B
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow) l_orderUnit.getDataSourceObject();
        int l_intCompare = WEB3DateUtility.compareToDay(l_feqOrderUnitRow.getExpirationDate(), l_datChangeOrderExpirationDate);
        if (l_intCompare == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }
    
    /**
     * (validate�ڋq�����ʎ����~)<BR>
     * �ڋq�����ʎ����~�`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�����̕⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�ڋq.is�����~����( )���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    ����ID�F ����.����ID<BR>
     *    ������ʁF ����.�������<BR>
     * <BR>
     *    �߂�l == true �̏ꍇ�́u�ڋq�͎w������̊Y�������~���v<BR>
     * �@@�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01357<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID
     * 
     * @@param l_orderType - (�������)<BR>
     * �������
     * @@throws WEB3BaseException 
     * @@roseuid 429E863A0175
     */
    public void validateAccountProductTradedStop(SubAccount l_subAccount, 
        long l_lngProductId,
        OrderTypeEnum l_orderType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateAccountProductTradedStop(SubAccount, long, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l��NULL");
        }

        //�P�j�����̕⏕�����I�u�W�F�N�g���A�ڋq�I�u�W�F�N�g���擾����B 
        //
        //�Q�j�ڋq.is�����~����( )���R�[������B 
        //
        //   [����] 
        //   ����ID�F ����.����ID 
        //   ������ʁF ����.������� 
        //
        //   �߂�l == true �̏ꍇ�́u�ڋq�͎w������̊Y�������~���v�̗�O���X���[����B
        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        boolean l_blnIsTradeStopProduct = l_account.isTradeStopProduct(l_lngProductId, l_orderType);
         
        if (l_blnIsTradeStopProduct)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01357.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01357,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�戵�\�s��)<BR>
     * ��Е��X�̎戵�\�s�ꂩ���`�F�b�N����B <BR>
     * <BR>
     * �P�j�i���X�s���.�O���j�戵�����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h<BR>
     * �@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �Q�j���������I�u�W�F�N�g.is�戵�\()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[is�戵�\()�Ɏw�肷�����]<BR>
     * �@@�@@�����^�C�v�F�@@ProductTypeEnum.�O������<BR>
     * <BR>
     * �R�j�Q�j�̖߂�l == false�̏ꍇ�A<BR>
     * �@@�u�s��戵�s�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02108<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h
     * @@throws WEB3BaseException 
     * @@roseuid 429EF02D00D7
     */
    public void validateHandlingPossibleMarket(String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strMarketCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateHandlingPossibleMarket(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�i���X�s���.�O���j�戵�����I�u�W�F�N�g�𐶐�����B 
        //
        //[�R���X�g���N�^�Ɏw�肷�����] 
        //�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h 
        //���X�R�[�h�F�@@�p�����[�^.���X�R�[�h 
        //�s��R�[�h�F�@@�p�����[�^.�s��R�[�h 
        WEB3GentradeFeqBranchMarketDealtCond l_cond =
            new WEB3GentradeFeqBranchMarketDealtCond(l_strInstitutionCode, l_strBranchCode, l_strMarketCode);
        
        //�Q�j���������I�u�W�F�N�g.is�戵�\()���\�b�h���R�[������B 
        //
        //[is�戵�\()�Ɏw�肷�����] 
        //�����^�C�v�F�@@ProductTypeEnum.�O������ 
        boolean l_blnIsHandlingPossible = l_cond.isHandlingPossible(ProductTypeEnum.FOREIGN_EQUITY);
        
        //�R�j�Q�j�̖߂�l == false�̏ꍇ�A 
        //�u�s��戵�s�v�̗�O���X���[����B 
        if (!l_blnIsHandlingPossible)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02108.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02108,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc��l)<BR>
     * ���Z�l�̒l�����ݒl�Ŋ���؂��l�ɂȂ�悤�Ɍv�Z����B<BR>
     * <BR>
     * ���i�i���Z�l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ<BR>
     *    �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     *    [�v�Z��]<BR>
     *    �i���Z�l�^���ݒl(*1)�j�~���ݒl<BR>
     *    (*1)���Z�̌v�Z���ʂ�؂�グ�B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     *    �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     *    [�v�Z��]<BR>
     *    �i���Z�l�^���ݒl(*2)�j�~���ݒl<BR>
     *    (*2)���Z�̌v�Z���ʂ�؂�̂āB<BR>
     * <BR>
     * @@param l_dblLiquidationPrice - (���Z�l)<BR>
     * ���Z�l
     * 
     * @@param l_dblTickValue - (���ݒl)<BR>
     * ���ݒl
     * 
     * @@return double
     * @@throws WEB3BaseException 
     * @@roseuid 42A8249001E3
     */
    public double calcBasePrice(double l_dblLiquidationPrice, double l_dblTickValue) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " calcBasePrice(double, double)";
        log.entering(STR_METHOD_NAME);
        
        //���i�i���Z�l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ 
        //   �ȉ��̌v�Z���ʂ�ԋp����B 
        //
        //   [�v�Z��] 
        //   �i���Z�l�^���ݒl(*1)�j�~���ݒl 
        //   (*1)���Z�̌v�Z���ʂ�؂�グ�B 
        BigDecimal l_bdLiquidationPrice = new BigDecimal(String.valueOf(l_dblLiquidationPrice));
        BigDecimal l_bdTickValue = new BigDecimal(String.valueOf(l_dblTickValue));
        double l_dblBasePrice = 0D; //��l
        if (WEB3FeqOrderUtility.getRemainder(l_dblLiquidationPrice, l_dblTickValue)
            >= l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_EVEN).doubleValue())
        {
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();
        }
        //����L�ȊO�̏ꍇ 
        //   �ȉ��̌v�Z���ʂ�ԋp����B 
        //
        //   [�v�Z��] 
        //   �i���Z�l�^���ݒl(*2)�j�~���ݒl 
        //   (*2)���Z�̌v�Z���ʂ�؂�̂āB 

        else
        {
            l_dblBasePrice =
                l_bdLiquidationPrice.divide(l_bdTickValue, 0, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }
    
    /**
     * (calc�l�����)<BR>
     * �Ēl�P�ʂ̒l������l���v�Z����B<BR>
     * <BR>
     * �P�j�l������l�v�Z<BR>
     * <BR>
     *    �l������l = ����.��l�{����.�����l��<BR>
     * <BR>
     * �Q�j���ݒl�擾<BR>
     * <BR>
     *    �O�������v���_�N�g�}�l�[�W��.get���ݒl()�ɂč��ݒl���擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    �O�����������F ����.�O����������<BR>
     *    �P���F �P�j�ŎZ�o�����l������l<BR>
     * <BR>
     * �R�j�Ēl�P�ʂ̒l������l�v�Z<BR>
     * <BR>
     * ���i�l������l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ<BR>
     * <BR>
     *    �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     *    [�v�Z��]<BR>
     *    �i�l������l�^���ݒl(*1)�j�~���ݒl<BR>
     *    (*1)���Z�̌v�Z���ʂ̏��������������؂�グ<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * <BR>
     *    �ȉ��̌v�Z���ʂ�ԋp����B<BR>
     * <BR>
     *    [�v�Z��]<BR>
     *    �i�l������l�^���ݒl(*2)�j�~���ݒl<BR>
     *    (*2)���Z�̌v�Z���ʂ̏��������������؂�̂�<BR>
     * @@param l_dblBasicValue - (��l)<BR>
     * ��l
     * 
     * @@param l_dblDeregulatedPriceRange - (�����l��)<BR>
     * �����l��
     * 
     * @@param l_feqProduct - (�O����������)<BR>
     * �O�����������I�u�W�F�N�g
     * 
     * @@return double
     * @@throws WEB3BaseException 
     * @@roseuid 42A8252802CD
     */
    public double calcRangeCap(double l_dblBasicValue,
        double l_dblDeregulatedPriceRange, WEB3FeqProduct l_feqProduct)throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " calcRangeCap(double, double, WEB3FeqProduct)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�l������l�v�Z 
        //
        //   �l������l = ����.��l�{����.�����l�� 
        BigDecimal l_bdCapValue =
            new BigDecimal(String.valueOf(l_dblBasicValue)).add(new BigDecimal(String.valueOf(l_dblDeregulatedPriceRange)));  
        
        //�Q�j���ݒl�擾 
        //
        //   �O�������v���_�N�g�}�l�[�W��.get���ݒl()�ɂč��ݒl���擾����B 
        //
        //   [����] 
        //   �O�����������F ����.�O���������� 
        //   �P���F �P�j�ŎZ�o�����l������l 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_manager = (WEB3FeqProductManager) l_tradingModule.getProductManager();
        BigDecimal l_bdTickValue =
            new BigDecimal(String.valueOf(l_manager.getTickValue(l_feqProduct, l_bdCapValue.doubleValue())));
        
        //�R�j�Ēl�P�ʂ̒l������l�v�Z 
        //
        //���i�l������l�^���ݒl�j�̗]��@@>=�@@���ݒl�^2�j�̏ꍇ 
        //
        //   �ȉ��̌v�Z���ʂ�ԋp����B 
        //
        //   [�v�Z��] 
        //   �i�l������l�^���ݒl(*1)�j�~���ݒl 
        //   (*1)���Z�̌v�Z���ʂ̏��������������؂�グ 
        //
        int l_intScale =  l_feqProduct.getCurrency().getScale();
        double l_dblBasePrice = l_bdCapValue.doubleValue(); //��l
        
        if (WEB3FeqOrderUtility.getRemainder(l_bdCapValue.doubleValue(), l_bdTickValue.doubleValue())
            >= l_bdTickValue.divide(new BigDecimal("2"), 10, BigDecimal.ROUND_HALF_EVEN).doubleValue())
        {
            l_dblBasePrice =
                l_bdCapValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_CEILING).multiply(l_bdTickValue).doubleValue();                        
        }
        //����L�ȊO�̏ꍇ 
        //
        //   �ȉ��̌v�Z���ʂ�ԋp����B 
        //
        //   [�v�Z��] 
        //   �i�l������l�^���ݒl(*2)�j�~���ݒl 
        //   (*2)���Z�̌v�Z���ʂ̏��������������؂�̂� 
        else
        {
            l_dblBasePrice =
                l_bdCapValue.divide(l_bdTickValue, l_intScale, BigDecimal.ROUND_FLOOR).multiply(l_bdTickValue).doubleValue();
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblBasePrice;
    }
    
    /**
     * (validate���t�\����)<BR>
     * ���t�ɏ\���Ȑ��ʂ̎��Y�����L���Ă��邩���`�F�b�N����B<BR>
     * <BR>
     * �P�j�ۗL���Y�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     *    �O�������|�W�V�����}�l�[�W��.get�ۗL���Y()���R�[������B<BR>
     * <BR>
     *    [����]<BR>
     *    ����ID�F �⏕����.getAccountId()�̖߂�l<BR>
     *    �⏕����ID�F �⏕����.getSubAccountId()�̖߂�l<BR>
     *    ����ID�F ����.����ID<BR>
     *    �ŋ敪�F ����.�ŋ敪<BR>
     * <BR>
     * �Q�j���b�N�����ʂ��擾����B<BR>
     * <BR>
     *    �ۗL���Y.getLockedQuantity()���R�[������B<BR>
     * <BR>
     * �R�j�ȉ��̏�������������ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02109<BR>
     * <BR>
     *   �i�ۗL���Y�I�u�W�F�N�g.���ʁ|���b�N�����ʁj �� ����.����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_lngProcutId - (����ID)<BR>
     * ����ID
     * 
     * @@param l_dblQuantity - (����)<BR>
     * ��������
     * 
     * @@param l_taxType - (�ŋ敪)<BR>
     * �ŋ敪
     * @@throws WEB3BaseException 
     * @@roseuid 42BA59500128
     */
    public void validateSellPossQuantity(WEB3GentradeSubAccount l_subAccount,
        long l_lngProcutId,
        double l_dblQuantity,
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateSellPossQuantity(WEB3GentradeSubAccount, String, double, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //���t�ɏ\���Ȑ��ʂ̎��Y�����L���Ă��邩���`�F�b�N����B 
        //
        //�P�j�ۗL���Y�I�u�W�F�N�g���擾����B 
        //
        //   �O�������|�W�V�����}�l�[�W��.get�ۗL���Y()���R�[������B 
        //
        //   [����] 
        //   ����ID�F �⏕����.getAccountId()�̖߂�l 
        //   �⏕����ID�F �⏕����.getSubAccountId()�̖߂�l 
        //   ����ID�F ����.����ID 
        //   �ŋ敪�F ����.�ŋ敪
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_positionManager =
            (WEB3FeqPositionManager) l_tradingModule.getPositionManager();
        FeqAssetImpl l_asset =  
            (FeqAssetImpl)l_positionManager.getAsset(l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(), l_lngProcutId, l_taxType);

        //�Q�j���b�N�����ʂ��擾����B 
        //
        //   �ۗL���Y.getLockedQuantity()���R�[������B 
        double l_dblLockedQuantity = l_asset.getLockedQuantity();
        //�R�j�ȉ��̏�������������ꍇ�A��O���X���[����B 
        //
        //  �i�ۗL���Y�I�u�W�F�N�g.���ʁ|���b�N�����ʁj �� ����.���� 
        double l_dblQuant = l_asset.getQuantity();
        if (l_dblQuant - l_dblLockedQuantity < l_dblQuantity)
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_02109.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02109,
                this.getClass().getName() + "." + STR_METHOD_NAME);      
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��������)<BR>
     * �ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     *�E���s�����`�F�b�N<BR>
     *�E���������`�F�b�N<BR>
     *�E�o����܂Œ����戵�̃`�F�b�N<BR>
     *�E�o����܂Œ����L�������̃`�F�b�N<BR>
     * <BR>
     *�P�j�戵�\�����������擾����B<BR>
     * <BR>
     * [�R���X�g���N�^�ɃZ�b�g�������]<BR>
     * �،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR> 
     * �����^�C�v�F�@@�h�O�������h<BR>
     * �敨�^�I�v�V�����敪�F�@@DEFAULT<BR> 
     * �M�p����敪�F�@@DEFAULT<BR>
     * �s��R�[�h�F�@@�����̎s��R�[�h<BR>
     * <BR>
     * �Q�j���s�����̃`�F�b�N<BR>
     * <BR>
     * �戵�\��������.is�戵�\���s����()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * ���s�����F ����.���s����<BR>
     * <BR>
     * ���߂�l��false�̏ꍇ�A�u���s�����`�F�b�N�G���[�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00150<BR> 
     * <BR> 
     * �R�j���������̃`�F�b�N<BR>
     * <BR> 
     * �戵�\��������.is�戵�\��������()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * ���������F ����.��������<BR>
     * <BR>
     *  ���߂�l��false�̏ꍇ�A�u���������`�F�b�N�G���[�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00151<BR> 
     * <BR>
     * �S�j�o����܂Œ����`�F�b�N<BR>
     * <BR>
     * ����.is�o����܂Œ��� == true �̏ꍇ�̂݁A�ȉ������s����B<BR>
     * <BR>
     * �S�|�P�j�o����܂Œ����戵�\�`�F�b�N<BR>
     * <BR>
     * �戵�\��������.is�o����܂Œ����戵�\()���R�[������B<BR>
     * <BR>
     * ���߂�l��false�̏ꍇ�A�u�o����܂Œ����戵�`�F�b�N�G���[�v�̗�O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00704<BR>  
     * �S�|�Q�j�o����܂Œ����L�������`�F�b�N<BR>
     * <BR>
     * �戵�\��������.is�o����܂Œ����\��()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * ������.������������ == null �̏ꍇ<BR>
     * �����������F ����.����������<BR>
     * ������.������ != null �̏ꍇ<BR>
     * �����������F ����.����������<BR>
     * �������������F ����.������������<BR>
     * <BR>
     * ���߂�l��false�̏ꍇ�A�u�o����܂Œ����L�������`�F�b�N�G���[�v�̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01815<BR>  
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * 
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h
     * 
     * @@param l_datOldOrderBizDate - (����������)<BR>
     * ������������
     * 
     * @@param l_datExpirationDate - (����������)<BR>
     * ����������
     * 
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������
     * 
     * @@param l_executionConditionType - (���s����)<BR>
     * ���s����
     * 
     * @@param l_blnIsCarriedOrder - (is�o����܂Œ���)<BR>
     * is�o����܂Œ���
     * @@throws WEB3BaseException 
     */
    public void validateOrderCondition(String l_strInstitutionCode,
        String l_strMarketCode, 
        Date l_datOldOrderBizDate, 
        Date l_datExpirationDate, 
        String l_strOrderConditionType, 
        FeqExecutionConditionType l_executionConditionType, 
        boolean l_blnIsCarriedOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateOrderCondition(String, String, Date, Date, String, FeqExecutionConditionTypes, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�戵�\�����������擾����B 
        //
        //   [�R���X�g���N�^�ɃZ�b�g�������] 
        //   �،���ЃR�[�h�F�@@����.�،���ЃR�[�h  
        //   �����^�C�v�F�@@�h�O�������h 
        //   �敨�^�I�v�V�����敪�F�@@DEFAULT  
        //   �M�p����敪�F�@@DEFAULT 
        //   �s��R�[�h�F�@@�����̎s��R�[�h 
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);
        //�Q�j���s�����̃`�F�b�N 
        //
        //   �戵�\��������.is�戵�\���s����()���R�[������B 
        //
        //   [����] 
        //   ���s�����F ����.���s���� 
        //
        //   ���߂�l��false�̏ꍇ�A�u���s�����`�F�b�N�G���[�v�̗�O���X���[����B 
        if (!l_handlingOrderCond.isHandlingPossibleExecCond(l_executionConditionType))
        {
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00150.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00150,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�R�j���������̃`�F�b�N 
        //
        //   �戵�\��������.is�戵�\��������()���R�[������B 
        //
        //   [����] 
        //   ���������F ����.�������� 
        //
        //   ���߂�l��false�̏ꍇ�A�u���������`�F�b�N�G���[�v�̗�O���X���[����B 
        if (!l_handlingOrderCond.isHandlingPossibleOrderCond(l_strOrderConditionType))
        {            
            log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00151.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00151,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�S�j�o����܂Œ����`�F�b�N 
        //   ����.is�o����܂Œ��� == true �̏ꍇ�̂݁A�ȉ������s����B 
        //
        //�S�|�P�j�o����܂Œ����戵�\�`�F�b�N 
        //
        //   �戵�\��������.is�o����܂Œ����戵�\()���R�[������B 
        //
        //   ���߂�l��false�̏ꍇ�A�u�o����܂Œ����戵�`�F�b�N�G���[�v�̗�O���X���[����B 
        if (l_blnIsCarriedOrder)
        {
            if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleHandling())
            {
                log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_00704.getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00704,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //�S�|�Q�j�o����܂Œ����L�������`�F�b�N 
            //
            //   �戵�\��������.is�o����܂Œ����\��()���R�[������B 
            //
            //   [����] 
            //   ������.������������ == null �̏ꍇ 
            //      �����������F ����.���������� 
            //   ������.������������ != null �̏ꍇ 
            //      �����������F ����.���������� 
            //      �������������F ����.������������ 
            //
            //   ���߂�l��false�̏ꍇ�A�u�o����܂Œ����L�������`�F�b�N�G���[�v�̗�O���X���[����B 

            if (l_datOldOrderBizDate != null)
            {
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate, l_datOldOrderBizDate))
                {
                    log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01815.getErrorMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            else
            {
                if (!l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(l_datExpirationDate))
                {
                    log.debug(WEB3ErrorCatalog.BUSINESS_ERROR_01815.getErrorMessage());
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01815,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }            
            }
        }                
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�����\�s��)<BR>
     * �������\�Ȏs��ł��邩�ǂ������`�F�b�N����B <BR>
     * <BR>
     * �P�j�Y�����钍���P�ʂ��擾����B <BR>
     * <BR>
     *    �O�����������}�l�[�W��.get�����P��ByOrderId()���R�[������B <BR>
     * <BR>
     *    [����] <BR>
     *    ����ID�F ����.����ID <BR>
     * <BR>
     * �Q�j�s��p�v���t�@@�����X����ȉ��̏����Ń��R�[�h���擾����B <BR>
     * <BR>
     *    [����] <BR>
     *    �s��ID = �P�j�Ŏ擾���������P��.�s��ID <BR>
     *    �v���t�@@�����X���ږ� = "feq.order.change" <BR>
     *    ���ږ��A�� = 1 <BR>
     * �R�j�v���t�@@�����X�̒l�̃`�F�b�N <BR>
     * <BR>
     *   �Q�j�Ŏ擾�������R�[�h�̃v���t�@@�����X�̒l == "false"�i�����s�j �̏ꍇ�A <BR>
     *    ���������s�s��Ƃ��ė�O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02204<BR>  
     * @@l_lngOrderId -(����ID)
     * @@throws WEB3BaseException
     */
    public void validateChangePossMarket(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateChangePossMarket(long l_lngOrderId)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        //�P�j�Y�����钍���P�ʂ��擾����B
        //�O�����������}�l�[�W��.get�����P��ByOrderId()���R�[������B 
        FeqOrderUnit l_orderUnit = l_feqOrderManager.getOrderUnitByOrderId(l_lngOrderId);
        FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        //�Q�j�s��p�v���t�@@�����X����ȉ��̏����Ń��R�[�h���擾����B
        //[����] 
        //�s��ID = �P�j�Ŏ擾���������P��.�s��ID 
        //�v���t�@@�����X���ږ� = "feq.order.change" 
        //���ږ��A�� = 1 
        QueryProcessor l_qp = null;
        List l_lisMarketPreferences = null;
        Long l_lngNameSerialNo = new Long(1);
        try
        {
            String l_strWhere = " market_id = ?  and name = ?  and name_serial_no = ? ";
            Object[] l_bindValues = {
                new Long(l_orderUnitRow.getMarketId()),
                WEB3MarketPreferencesNameDef.FEQ_ORDER_CHANGE,
                l_lngNameSerialNo};

            l_qp = Processors.getDefaultProcessor();
            
            l_lisMarketPreferences = l_qp.doFindAllQuery(MarketPreferencesRow.TYPE, l_strWhere, l_bindValues);
        }
        catch (DataFindException l_ex)
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
        if (!(l_lisMarketPreferences == null || l_lisMarketPreferences.isEmpty()))
        {         
            MarketPreferencesRow l_row = (MarketPreferencesRow)l_lisMarketPreferences.get(0);
            //�R�j�v���t�@@�����X�̒l�̃`�F�b�N
            //�Q�j�Ŏ擾�������R�[�h�̃v���t�@@�����X�̒l == "false"�i�����s�j �̏ꍇ�A 
            //���������s�s��Ƃ��ė�O���X���[����B
            if (WEB3MarketPreferencesValueDef.UNABLE.equals(l_row.getValue()))
            {
                log.debug("���������s�̎s��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02204,
                    this.getClass().getName() + "." + STR_METHOD_NAME); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�����בփ��[�g)<BR>
     * �����̖��בփ��[�g���o�^����Ă��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@(����)�ʉ݃I�u�W�F�N�g���擾����B<BR>
     * �@@����.�O�����������P��.get�ʉ�()���R�[������B<BR>
     * <BR>
     * �Q�j�@@���ݓ��t(yyyymmdd)�Ɩ��ב֍X�V���t(yyyymmdd)���r����B<BR>
     * <BR>
     * �@@�@@�����P��.get�ʉ�().���ב֍X�V���t�@@���@@���ݓ��t<BR>
     * �@@�A�����P��.get�ʉ�().���ב֍X�V���t�@@���@@���ݓ��t<BR>
     * <BR>
     * �@@�@@�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�A�̏ꍇ�́Afalse��Ԃ��B<BR>
     * <BR>
     * �@@��<BR>
     * �@@���ݓ��t�FGtlUtils.getSystemTimeStamp()�̔N��������<BR>
     * <BR>
     * @@param l_orderUnit - �O�����������P��<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean validateDayExchange(WEB3FeqOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateDayExchange(WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        
        // �P�j�@@(����)�ʉ݃I�u�W�F�N�g���擾����B
        // ����.�O�����������P��.get�ʉ�()���R�[������B
        WEB3GentradeCurrency l_currency = l_orderUnit.getCurrency();
        
        // �Q�j�@@���ݓ��t(yyyymmdd)�Ɩ��ב֍X�V���t(yyyymmdd)���r����B
        // �@@�����P��.get�ʉ�().���ב֍X�V���t�@@���@@���ݓ��t
        // �A�����P��.get�ʉ�().���ב֍X�V���t�@@���@@���ݓ��t
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        GenCurrencyRow l_genCurrencyRow = 
            (GenCurrencyRow)l_currency.getDataSourceObject();
        Timestamp l_tsExecRateUpdateTimestamp = 
            l_genCurrencyRow.getExecRateUpdateTimestamp();

        if (WEB3DateUtility.compareToDay(l_tsSystemTimestamp, l_tsExecRateUpdateTimestamp) == 0)
        {
            // �@@�̏ꍇ�́Atrue��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            // �A�̏ꍇ�́Afalse��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}@
