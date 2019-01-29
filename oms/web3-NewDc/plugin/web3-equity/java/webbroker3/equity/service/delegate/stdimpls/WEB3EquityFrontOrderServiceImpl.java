head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityFrontOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : ���������T�[�r�XImpl(WEB3EquityFrontOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/14 �������F (SRA) �V�K�쐬
Revesion History : 2007/12/17 �����F ���f�� 1243 1265
Revesion History : 2008/12/05 ������ ���f�� 1325
Revesion History : 2009/05/21 �����F ���f�� 1329 1330
Revesion History : 2010/08/09 �ᏼ�G�K ���f�� 1354
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3FrontOrderExchangeCodeDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

/**
 * (���������T�[�r�XImpl)<BR>
 * <BR>
 * ���������T�[�r�X�����N���X�B<BR>
 * @@version 1.0
 */
public class WEB3EquityFrontOrderServiceImpl implements WEB3EquityFrontOrderService
{
    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityFrontOrderServiceImpl.class);
    
    /**
     * (get�����o�H�敪)<BR>
     * �����̊�����������I�u�W�F�N�g�����A�����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̎���R�[�h�iSONAR�j=="�������n"�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�@@�����̎���R�[�h�iSONAR�j=="����O����"�̏ꍇ�́A"SONAR���n"��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�����̎���R�[�h�iSONAR�j����L�ȊO�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@this.get�t�����g�����V�X�e���敪()�ɂ��A<BR>
     * �@@�@@�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̊����������.getMarket().�s��R�[�h<BR>
     * �@@�@@�@@�X�����J�敪�F�@@�����̊����������.�X�����J�敪<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�j�@@������ؑ�.get�L��������ؑ�()�ɂ��A�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��������ؑ�.get�L��������ؑ�()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̏،���ЃR�[�h<BR>
     * �@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̊����������.getMarket().�s��R�[�h<BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�Q�j�ŋ��߂��t�����g�����V�X�e���敪<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �S�j�@@�R�j�Ŗ߂�l != null�̏ꍇ�́A�߂�l.�����o�H�敪 ��ԋp����B<BR>
     * �@@�@@�@@�R�j�Ŗ߂�l == null�̏ꍇ�́A�u�����o�H�֑ؑΏۂȂ��v�̗�O��throw����B<BR>
     * @@param l_tradedProduct - (�����������)<BR>
     * ������������I�u�W�F�N�g�B
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�B
     * @@param l_strSonarTradedCode - (����R�[�h�iSONAR�j)<BR>
     * ����R�[�h�iSONAR�j�B<BR>
     * <BR>
     * 11�F���ʊ���<BR>
     * 16�F����O����<BR>
     * 51�F�M�p��<BR>
     * 52�F�M�p�ԍ�<BR>
     * 53�F�������n<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getSubmitOrderRouteDiv(
        EqTypeTradedProduct l_tradedProduct,
        String l_strInstitutionCode,
        String l_strSonarTradedCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubmitOrderRouteDiv(EqTypeTradedProduct, String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3TransactionTypeSONARDef.SWAP_CONTRACT.equals(l_strSonarTradedCode))
        {
            return null;
        }
        else if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode))
        {
            return WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION;
        }
        
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        String l_strMarketCode = l_tradedProduct.getMarket().getMarketCode();
        String l_strFrontOrderSystemCode =
            this.getFrontOrderSystemCode(
                l_strMarketCode,
                l_tradedProductRow.getOpenOtcDiv());
        
        WEB3GentradeOrderSwitching l_orderSwitching =
            WEB3GentradeOrderSwitching.getOnOrderSwitching(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                l_strMarketCode,
                l_strFrontOrderSystemCode);
        
        log.exiting(STR_METHOD_NAME);
        if (l_orderSwitching != null)
        {
            OrderSwitchingRow l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();
            return l_orderSwitchingRow.getSubmitOrderRouteDiv();
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02216,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h:" + l_strInstitutionCode
                + " �s��R�[�h:" + l_strMarketCode
                + " �t�����g�����V�X�e���敪:" + l_strFrontOrderSystemCode);
        }
    }
    
    /**
     * (get��������������o�H�敪)<BR>
     * �����̒�������Ώۂ̒����P�ʃI�u�W�F�N�g���A�����\�Ȕ����o�H�敪���擾���ԋp����B<BR>
     * �|�����Ƃ��āA���ݗL���Ȕ����o�H��ԋp����B<BR>
     * �|�����o�H�ύX�s�̌o�H��ʂ��Ĕ������������̏ꍇ�́A�������̌o�H��ԋp����B<BR>
     * �|�������n�̏ꍇ�́A�������̌o�H��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̎���R�[�h�iSONAR�j=="�������n"�̏ꍇ�́A<BR>
     * �@@�@@�@@�����̒����P��.�����o�H�敪��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̒����P��.�����o�H�敪 != "������~"�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�ȉ��̏������s���B<BR>
     * �@@�@@�@@�������̒����P��.�����o�H�敪=="������~"�̏ꍇ�́A<BR>
     * �@@�@@�@@�������o�H�ύX�ۂ̃`�F�b�N�͍s��Ȃ��B<BR>
     * <BR>
     * �Q�|�P�j�@@this.get�t�����g�����V�X�e���敪()�ɂ��A<BR>
     * �@@�@@�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.getMarket().�s��R�[�h<BR>
     * �@@�@@�@@�X�����J�敪�F�@@�����̒����P��.getTradedProduct().�X�����J�敪<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@����������Ɍo�H�̕ύX���\�Ȕ����o�H���ǂ������`�F�b�N����B<BR>
     * �@@�@@�@@�ύX���s�Ȕ����o�H�̏ꍇ�́A�������̔����o�H��ԋp����B<BR>
     * <BR>
     * �Q�|�Q�|�P�j�@@�������̔����o�H�敪���g�p��������ؑփN���X�̃C���X�^���X���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��������ؑ�()�F�R���X�g���N�^�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̒����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.getMarket().�s��R�[�h<BR>
     * �@@�@@�@@�����o�H�敪�F�@@�����̒����P��.�����o�H�敪<BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�Q�|�P�j�ŋ��߂��t�����g�����V�X�e���敪<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�|�Q�j�@@�ȉ��̏����̂����ꂩ�ɊY������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�����̒����P��.�����o�H�敪��ԋp����B<BR>
     * <BR>
     * �@@�@@�E�擾�����C���X�^���X.��������\�t���O == "�s��"�̏ꍇ<BR>
     * �@@�@@�E�擾�����C���X�^���X.isSONAR() == true�̏ꍇ<BR>
     * �@@�@@�E�Y�����锭����ؑփf�[�^�����݂��Ȃ��ꍇ<BR>
     * �@@�@@�@@�@@�i�����^����Ώۂ̒����P�ʂ��A�t�����g�����Ή��s��ɑ΂��钍���ŁA<BR>
     * �@@�@@�@@�@@�@@���@@SONAR���͒����̏ꍇ�́A�Y�����锭����ؑփf�[�^�����݂��Ȃ��B<BR>
     * �@@�@@�@@�@@�@@�iSONAR���͒����̏ꍇ�A�����o�H�敪�ɂ�SONAR�̌o�H���ݒ肳��Ă��邽�߁j�j<BR>
     * <BR>
     * �R�j�@@������ؑ�.get�L��������ؑ�()�ɂ��A�L���Ȕ�����ؑփI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��������ؑ�.get�L��������ؑ�()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̒����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.getMarket().�s��R�[�h<BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�Q�|�P�j�ŋ��߂��t�����g�����V�X�e���敪<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �S�j�@@�R�j�Ŗ߂�l != null�̏ꍇ�́A�߂�l.�����o�H�敪 ��ԋp����B<BR>
     * �@@�@@�@@�R�j�Ŗ߂�l == null�̏ꍇ�́A�u�����o�H�֑ؑΏۂȂ��v�̗�O��throw����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeSubmitOrderRouteDiv(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeSubmitOrderRouteDiv(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strSonarTradedCode = l_orderUnitRow.getSonarTradedCode();
        if (WEB3TransactionTypeSONARDef.SWAP_CONTRACT.equals(l_strSonarTradedCode))
        {
            log.exiting(STR_METHOD_NAME);
        	return l_orderUnitRow.getSubmitOrderRouteDiv();
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_strSubmitOrderRouteDiv = l_orderUnitRow.getSubmitOrderRouteDiv();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        String l_strMarketCode = null;
        try
        {
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        EqTypeTradedProduct l_tradedProduct =
            (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        String l_strFrontOrderSystemCode =
            this.getFrontOrderSystemCode(
                l_strMarketCode,
                l_tradedProductRow.getOpenOtcDiv());
        
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strInstitutionCode = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            l_strInstitutionCode = l_branchRow.getInstitutionCode();
        }
        catch (NotFoundException l_nfe)
        {
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        WEB3GentradeOrderSwitching l_orderSwitching = null;
        OrderSwitchingRow l_orderSwitchingRow = null;
        if (!WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_strSubmitOrderRouteDiv))
        {
            try
            {
                l_orderSwitching =
                    new WEB3GentradeOrderSwitching(
                        l_strInstitutionCode,
                        ProductTypeEnum.EQUITY,
                        l_strMarketCode,
                        l_strSubmitOrderRouteDiv,
                        l_strFrontOrderSystemCode);
            }
            catch (WEB3SystemLayerException l_sle)
            {
                if (!l_sle.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005))
                {
                    throw l_sle;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return l_strSubmitOrderRouteDiv;
                }
            }

            l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();
            if (WEB3ChangeCancelEnableFlag.DISABLE.equals(l_orderSwitchingRow.getChangeCancelEnableFlag()))
            {
                log.exiting(STR_METHOD_NAME);
                return l_strSubmitOrderRouteDiv;
            }
            if (l_orderSwitching.isSONAR())
            {
                log.exiting(STR_METHOD_NAME);
                return l_strSubmitOrderRouteDiv;
            }
        }
        
        l_orderSwitching =
            WEB3GentradeOrderSwitching.getOnOrderSwitching(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                l_strMarketCode,
                l_strFrontOrderSystemCode);
        
        log.exiting(STR_METHOD_NAME);
        if (l_orderSwitching != null)
        {
            l_orderSwitchingRow =
                (OrderSwitchingRow)l_orderSwitching.getDataSourceObject();
            return l_orderSwitchingRow.getSubmitOrderRouteDiv();
        }
		else
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02216,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�،���ЃR�[�h:" + l_strInstitutionCode
				+ " �s��R�[�h:" + l_strMarketCode
				+ " �t�����g�����V�X�e���敪:" + l_strFrontOrderSystemCode);
		}
    }
    
    /**
     * (get������ؑ�)<BR>
     * �w��̒����P�ʂɍ��v���锭����ؑփI�u�W�F�N�g���擾���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.�����o�H�敪 == "9�F������~"�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@this.get�t�����g�����V�X�e���敪()�ɂāA�t�����g�����V�X�e���敪���擾����B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��get�t�����g�����V�X�e���敪()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�@@�X�����J�敪�F�@@�����̒����P��.getTradedProduct().�X�����J�敪<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �R�j�@@������ؑփN���X���C���X�^���X������B<BR>
     * <BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * �@@�@@�@@��������ؑփN���X�F�R���X�g���N�^�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�����̒����P��.���XID�ɊY�����镔�X.�،���ЃR�[�h<BR>
     * �@@�@@�@@�����^�C�v�F�@@"����"<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�����̒����P��.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�@@�����o�H�敪�F�@@�����̒����P��.�����o�H�敪<BR>
     * �@@�@@�@@�t�����g�����V�X�e���敪�F�@@�Q�j�Ŏ擾�����t�����g�����V�X�e���敪<BR>
     * �@@�@@�@@------------------------------------------------<BR>
     * <BR>
     * �S�j�@@������ؑփN���X�̃C���X�^���X��ԋp����B<BR>
     *      �R�j�ŊY������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return ������ؑ�
     * @@throws WEB3BaseException
     */
    public WEB3GentradeOrderSwitching getOrderSwitching(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderSwitching(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_orderUnitRow.getSubmitOrderRouteDiv()))
        {
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        String l_strMarketCode = null;
        try
        {
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            l_strMarketCode = l_market.getMarketCode();
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        EqtypeTradedProductRow l_tradedProductRow = null;
        try
        {
			l_tradedProductRow =
				(EqtypeTradedProductRow)l_orderUnit.getTradedProduct().getDataSourceObject();        	
        }
        catch (RuntimeSystemException l_rse)
        {
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01966,
				this.getClass().getName() + STR_METHOD_NAME,
				l_rse.getMessage(),
				l_rse);
        	
        }
		
        String l_strFrontOrderSystemCode =
            this.getFrontOrderSystemCode(
                l_strMarketCode,
                l_tradedProductRow.getOpenOtcDiv());
        
        WEB3GentradeOrderSwitching l_orderSwitching = null;
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        String l_strInstitutionCode = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            l_strInstitutionCode = l_branchRow.getInstitutionCode();
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        try
        {
            l_orderSwitching =
                new WEB3GentradeOrderSwitching(
                    l_strInstitutionCode,
                    ProductTypeEnum.EQUITY,
                    l_strMarketCode,
                    l_orderUnitRow.getSubmitOrderRouteDiv(),
                    l_strFrontOrderSystemCode);
        }
        catch (WEB3SystemLayerException l_sle)
        {
            if (!l_sle.getErrorInfo().equals(WEB3ErrorCatalog.SYSTEM_ERROR_80005))
            {
                throw l_sle;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderSwitching;
    }
    
    /**
     * (get�t�����g�����V�X�e���敪)<BR>
     * �����̎s��R�[�h�y�ѓX�����J�敪���A�t�����g�����V�X�e���敪���擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̎s��R�[�h == �i"����" or "����"�j�̏ꍇ�́A<BR>
     * �@@�@@"���؁A����"��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̎s��R�[�h == "���"�̏ꍇ�́A"���"��ԋp����B<BR>
     * <BR>
     * �R�j�@@�����̎s��R�[�h == "NNM"�̏ꍇ�́A"�w���N���X"��ԋp����B<BR>
     * <BR>
     * �S�j�@@�����̎s��R�[�h == "JASDAQ"�̏ꍇ�A"�w���N���X"��ԋp����B <BR>
     * <BR>
     * �T�j�@@�����̎s��R�[�h����L������ł��Ȃ��ꍇ�́A"���̑�"��ԋp����B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * Web�V�̎s��R�[�h�B
     * @@param l_strOpenOtcDiv - (�X�����J�敪)<BR>
     * �X�����J�敪�B<BR>
     * �i0�FDEFAULT�@@1�F�o�^�@@3�F�}�[�P�b�g���C�N�����j 
     * @@return String
     */
    public String getFrontOrderSystemCode(
        String l_strMarketCode,
        String l_strOpenOtcDiv)
    {
        final String STR_METHOD_NAME = "getFrontOrderSystemCode(String, String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strFrontOrderSystemCode = null;
        if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode) ||
            WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.TOKYO_NAGOYA;
        }
        else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK;
        }
        else if (WEB3MarketCodeDef.NNM.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.HERCULES;
        }
        else if (WEB3MarketCodeDef.JASDAQ.equals(l_strMarketCode))
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.HERCULES;
        }
        else
        {
            l_strFrontOrderSystemCode = WEB3FrontOrderSystemCodeDef.OTHERS;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strFrontOrderSystemCode;
    }
    
    /**
     * (get�t�����g����������敪�R�[�h )<BR>
     * �����̎s��R�[�h���A�t�����g����������敪�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̎s��R�[�h��"1"�i���؁j �̏ꍇ<BR>
     * �@@�@@�����̎s��R�[�h�̒l�����̂܂ܕԂ��B<BR>
     * <BR>
     * �Q�j�@@�����̎s��R�[�h��"2"�i��؁j �̏ꍇ<BR>
     * �@@�@@�����̎s��R�[�h�̒l�����̂܂ܕԂ��B<BR>
     * <BR>
     * �R�j�@@�����̎s��R�[�h��"3"�i���؁j �̏ꍇ<BR>
     * �@@�@@�����̎s��R�[�h�̒l�����̂܂ܕԂ��B<BR>
     * <BR>
     * �S�j�@@�����̎s��R�[�h��"9"�iNNM�j�@@or�@@"10"�iJASDAQ�j�̏ꍇ<BR>
     * �@@�@@"2"�i��؁j��Ԃ��B<BR>
     * <BR>
     * �T�j�@@�����̎s��R�[�h����L�ȊO�̏ꍇ<BR>
     * �@@�@@null��Ԃ��B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * Web�V�̎s��R�[�h�B
     * @@return String
     */
    public String getFrontOrderExchangeCode(String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "getFrontOrderExchangeCode(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strFrontOrderExchangeCode;
        if (WEB3MarketCodeDef.TOKYO.equals(l_strMarketCode))
        {
            l_strFrontOrderExchangeCode = l_strMarketCode;
        }
        else if (WEB3MarketCodeDef.OSAKA.equals(l_strMarketCode))
        {
            l_strFrontOrderExchangeCode = l_strMarketCode;
        }
        else if (WEB3MarketCodeDef.NAGOYA.equals(l_strMarketCode))
        {
            l_strFrontOrderExchangeCode = l_strMarketCode;
        }
        else if (WEB3MarketCodeDef.NNM.equals(l_strMarketCode) || WEB3MarketCodeDef.JASDAQ.equals(l_strMarketCode))
        {
            l_strFrontOrderExchangeCode =
                WEB3FrontOrderExchangeCodeDef.OSAKA_SECURITIES_EXCHANGE;
        }
        else
        {
            l_strFrontOrderExchangeCode = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strFrontOrderExchangeCode;
    }
    
    /**
     * (get�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�Г��������ځv�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����̒����P��.getBranch( )�ɂĎ擾����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̕������ԋp����B<BR>
     * <BR>
     * �@@�@@�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{"999"<BR>
     * <BR>
     * �@@�@@���X�I�u�W�F�N�g����擾�F�@@�،����ID�A���X�R�[�h<BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g����擾�F�@@�����^�C�v�A���ʃR�[�h�A����Rev.<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getCorpCode(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCorpCode(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        StringBuffer l_strCorpCode = new StringBuffer();
        l_strCorpCode.append(Long.toString(l_branchRow.getInstitutionId()));
        l_strCorpCode.append(l_branchRow.getBranchCode());
        l_strCorpCode.append(Integer.toString(l_orderUnitRow.getProductType().intValue()));
        l_strCorpCode.append(l_orderUnitRow.getOrderRequestNumber());
        l_strCorpCode.append(l_orderUnitRow.getOrderRev());
        l_strCorpCode.append("999");
        
        log.exiting(STR_METHOD_NAME);
        return l_strCorpCode.toString();
    }
    
    /**
     * (get�i������j�Г���������)<BR>
     * �����̒����P�ʃI�u�W�F�N�g���A�����Ɏg�p����u�i������j�Г��������ځv�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@���X�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�����̒����P��.getBranch( )�ɂĎ擾����B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̕������ԋp����B<BR>
     * <BR>
     * �@@�@@�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{�s�ꂩ��m�F�ς̒���Rev.�{"999"<BR>
     * <BR>
     * �@@�@@���X�I�u�W�F�N�g����擾�F�@@�،����ID�A���X�R�[�h<BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g����擾�F�@@�����^�C�v�A���ʃR�[�h�A�s�ꂩ��m�F�ς̒���Rev.<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getOrgCorpCode(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrgCorpCode(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        StringBuffer l_strCorpCode = new StringBuffer();
        l_strCorpCode.append(Long.toString(l_branchRow.getInstitutionId()));
        l_strCorpCode.append(l_branchRow.getBranchCode());
        l_strCorpCode.append(Integer.toString(l_orderUnitRow.getProductType().intValue()));
        l_strCorpCode.append(l_orderUnitRow.getOrderRequestNumber());
        l_strCorpCode.append(l_orderUnitRow.getConfirmedOrderRev());
        l_strCorpCode.append("999");
        
        log.exiting(STR_METHOD_NAME);
        return l_strCorpCode.toString();
    }
    
    /**
     * (get����Rev�J�n�ʒuIN�Г���������)<BR>
     * �Г��������ڂ̕����񒆂́A����Rev.�J�n�ʒu��Ԃ��B<BR>
     * <BR>
     * �J�n�ʒu�Ƃ��āA16��Ԃ��B<BR>
     * <BR>
     * �Г��������ڂ̃R�[�h�̌n�F<BR>
     * �،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{"999"<BR>
     * @@return int
     */
    public int getIndexOfOrderRevInCorpCode()
    {
        final String STR_METHOD_NAME = "getIndexOfOrderRevInCorpCode()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return 16;
    }
    
    /**
     * (get����Rev����)<BR>
     * ����Rev.�̌�����Ԃ��B<BR>
     * <BR>
     * �����Ƃ��āA2��Ԃ��B<BR>
     * @@return int
     */
    public int getFigureOfOrderRev()
    {
        final String STR_METHOD_NAME = "getFigureOfOrderRev()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return 2;
    }
    
    /**
     * (get����������Rev)<BR>
     * �����̒����㒍���P�ʃI�u�W�F�N�g���A<BR>
     * �������ɒ����P�ʃe�[�u��.����Rev�ɐݒ肷�镶������擾���Ԃ��B<BR>
     * -------------------<BR>
     * �������P�ʃe�[�u��.����Rev.�ݒ�l�ɂ���<BR>
     * �@@�|���s�����������܂ށA�ڋq�̒����I�y���[�V�����̃J�E���^�B�s��֑�������̉񐔂��ݒ肳���B<BR>
     * �@@�|�����܂Ŏs��֑�������̉񐔂݂̂��Ǘ�����K�v�����邽�߁A<BR>
     * �@@�@@�@@�ȉ��̃P�[�X�ɊY����������̏ꍇ�̓J�E���g�A�b�v���Ă͂����Ȃ��B<BR>
     * �@@�@@�@@�i�P�j�s�ꖢ���M�����ɑ΂��������<BR>
     * �@@�@@�@@�i�Q�j�������e���s��ʒm�s�v�ȏꍇ<BR>
     * -------------------<BR>
     * <BR>
     * �P�j�@@�s��ʒm�s�v�̒���(*1)�̏ꍇ�́A�����̒����P��.����Rev.�����̂܂ܕԋp����B<BR>
     * <BR>
     * �@@�@@(*1)�s��ʒm�s�v�̒���<BR>
     * �@@�@@�@@�@@�g�����������}�l�[�W��.is�����s��ʒm�v(�����̒����P��)==false�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�s��ʒm�s�v�̒����Ɣ��肷��B<BR>
     * <BR>
     * �Q�j�@@�s��ǎ��ԑ�(*2) �܂��� �s�ꖢ���M�����i*3)�̏ꍇ�́A<BR>
     * �@@�@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B<BR>
     * <BR>
     * �@@�@@(*2)�s��ǎ��ԑ�<BR>
     * �@@�@@�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�s��ǎ��ԑтƔ��肷��B<BR>
     * <BR>
     * �@@�@@(*3)�s�ꖢ���M����<BR>
     * �@@�@@�@@�@@�����̒����P��.�s�ꂩ��m�F�ς̐��� == NaN�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�s�ꖢ���M�����Ɣ��肷��B<BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ�́A�ȉ��̒ʂ�Ƃ���B<BR>
     * �@@�@@�@@�E�߂�l�̕�����́Athis.get����Rev����()�̌����Œ�i�擪��0���߁j�Ƃ���B<BR>
     * �@@�@@�@@�E�����𒴂���l�ƂȂ����ꍇ�́A�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B<BR>
     * <BR>
     * �R�|�P�j�@@�������������̎��ԑ�(*4)�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�����̒����P��.����Rev.�Ɂ{�P�����������ԋp����B<BR>
     * <BR>
     * �@@�@@(*4)������������<BR>
     * �@@�@@�@@�@@������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�������������̎��ԑтƔ��肷��B<BR>
     * <BR>
     * �R�|�Q�j�@@��L�ȊO�̏ꍇ�́A������������L���[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �����̒����㒍���P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h = �����̒����㒍���P��.getBranch().���X�R�[�h<BR>
     * �@@�@@���@@���ʃR�[�h = �����̒����㒍���P��.���ʃR�[�h<BR>
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*5) =  �����̒����㒍���P��.����Rev.<BR>
     * �@@�@@���@@�����敪 = "������"<BR>
     * <BR>
     * �@@�@@(*5)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@���������T�[�r�X.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��<BR>
     * �@@�@@�@@�@@�擾���w�肷��B<BR>
     * <BR>
     * �R�|�R�j�@@�������̒���Rev.�̒l�����߂�B<BR>
     * <BR>
     * �@@�@@���R�|�Q�j�ŊY�����郌�R�[�h�����݂��Ȃ��ꍇ<BR>
     * �@@�@@�@@�@@�����̒����P��.����Rev.�Ɂ{�P�����l��ԋp����B<BR>
     * <BR>
     * �@@�@@���R�|�Q�j�ŊY�����郌�R�[�h�����݂���ꍇ<BR>
     * �@@�@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B<BR>
     * @@param l_orderUnit - (�����㒍���P��)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B<BR>
     * �ixTrade�W�����ڂɁA������̒l���ݒ肳��Ă���I�u�W�F�N�g�j<BR>
     * ���X�V�C���^�Z�v�^.mutate()��������R�[������邱�Ƃ�O��Ƃ��Ă���B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getChangeOrderRev(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeOrderRev(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderRev = l_orderUnitRow.getOrderRev();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        if (!l_orderManager.isChangeMarketNotify(l_orderUnit))
        {
            return l_strOrderRev;
        }
        
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() ||
            l_orderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            return l_strOrderRev;
        }
        
        if (!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            int l_intOrderRev = Integer.parseInt(l_strOrderRev);
            l_intOrderRev += 1;
            l_strOrderRev = Integer.toString(l_intOrderRev);
            int l_intFigureOfOrderRev = this.getFigureOfOrderRev();
            int l_intLength = l_strOrderRev.length();
            if (l_intLength > l_intFigureOfOrderRev)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            int l_intSize = l_intFigureOfOrderRev - l_intLength;
            StringBuffer l_sbOrderRev = new StringBuffer();
            for (int i = 0;i < l_intSize;i++)
            {
                l_sbOrderRev.append("0");
            }
            l_sbOrderRev.append(l_strOrderRev);
            
            log.exiting(STR_METHOD_NAME);
            return l_sbOrderRev.toString();
        }
        else
        {
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            Branch l_branch = null;
            try
            {
                l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            Object[] l_objWhere =
            {
                l_branchRow.getInstitutionCode(),
                l_branchRow.getBranchCode(),
                l_orderUnitRow.getOrderRequestNumber(),
                l_orderUnitRow.getOrderRev(),
                WEB3FrontOrderStatusDef.NOT_DEAL
            };
            int l_intIndex = this.getIndexOfOrderRevInCorpCode();
            int l_intFigure = this.getFigureOfOrderRev();
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append("institution_code = ?");
            l_strWhere.append(" and branch_code = ?");
            l_strWhere.append(" and order_request_number = ?");
            l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
            l_strWhere.append(" and status = ?");
            List l_lisSearchResult = null;
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_lisSearchResult = l_processor.doFindAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_strWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataException l_de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            int l_intListSize = 0;
            if (l_lisSearchResult != null)
            {
                l_intListSize = l_lisSearchResult.size();
            }
            if (l_intListSize == 0)
            {
                int l_lngOrderRev = Integer.parseInt(l_strOrderRev);
                l_lngOrderRev += 1;
                l_strOrderRev = Integer.toString(l_lngOrderRev);
                int l_intFigureOfOrderRev = this.getFigureOfOrderRev();
                int l_intLength = l_strOrderRev.length();
                if (l_intLength > l_intFigureOfOrderRev)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                int l_intSize = l_intFigureOfOrderRev - l_intLength;
                StringBuffer l_sbOrderRev = new StringBuffer();
                for (int i = 0;i < l_intSize;i++)
                {
                    l_sbOrderRev.append("0");
                }
                l_sbOrderRev.append(l_strOrderRev);
            
                log.exiting(STR_METHOD_NAME);
                return l_sbOrderRev.toString();
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_strOrderRev;
            }
        }
    }
    
    /**
     * (get������MQ�f�[�^�R�[�h)<BR>
     * �����̔����o�H�敪���A�������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̔����o�H�敪=="9�F������~"�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̔����o�H�敪==�i"SONAR���n"�A"SONAR���n"�j�̂����ꂩ�̏ꍇ�́A<BR>
     * �@@�@@�@@"AI801T"��ԋp����B<BR>
     * <BR>
     * �R�j�@@�����̔����o�H�敪����L�ȊO�̏ꍇ�́A"AI8X2T"��ԋp����B<BR>
     * @@param l_strSubmitOrderRouteDiv - (�����o�H�敪)<BR>
     * �����o�H�敪�B
     * @@return String
     */
    public String getOrderMQDataCode(String l_strSubmitOrderRouteDiv)
    {
        final String STR_METHOD_NAME = "getOrderRequestCode(String)";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_strSubmitOrderRouteDiv))
        {
            return null;
        }
        else if (WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION.equals(l_strSubmitOrderRouteDiv) ||
                  WEB3SubmitOrderRouteDivDef.SONAR_SUB_FACTION.equals(l_strSubmitOrderRouteDiv))
        {
            return "AI801T";
        }
        else
        {
            return "AI8X2T";
        }
    }
    
    /**
     * (get���������MQ�f�[�^�R�[�h)<BR>
     * �����̔����o�H�敪���A����������Ɏg�p����MQ�f�[�^�R�[�h�ݒ�p�������<BR>
     * �擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̔����o�H�敪=="9�F������~"�̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̔����o�H�敪==�i"SONAR���n"�A"SONAR���n"�j�̂����ꂩ�̏ꍇ�́A<BR>
     * �@@�@@�@@"AI802T"��ԋp����B<BR>
     * <BR>
     * �R�j�@@�����̔����o�H�敪����L�ȊO�̏ꍇ�́A"AI8X2T"��ԋp����B<BR>
     * @@param l_strSubmitOrderRouteDiv - (�����o�H�敪)<BR>
     * �����o�H�敪�B
     * @@return String
     */
    public String getChangeCancelMQDataCode(String l_strSubmitOrderRouteDiv)
    {
        final String STR_METHOD_NAME = "getChangeCancelRequestCode(String)";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);
        if (WEB3SubmitOrderRouteDivDef.ORDER_STOP.equals(l_strSubmitOrderRouteDiv))
        {
            return null;
        }
        else if (WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION.equals(l_strSubmitOrderRouteDiv) ||
                  WEB3SubmitOrderRouteDivDef.SONAR_SUB_FACTION.equals(l_strSubmitOrderRouteDiv))
        {
            return "AI802T";
        }
        else
        {
            return "AI8X2T";
        }
    }
    
    /**
     * (is�s��ʒm������IN�x�e���ԑ�)<BR>
     * ��������x�e���Ԓ��̎��ԑтɂ����āA<BR>
     * �w��̒����Ɋ֌W����f�[�^�i�����A������܂ށj���s��ɒʒm���ł��邩�ǂ�����<BR>
     * ������������L���[�e�[�u���̃f�[�^��蔻�肵�Ԃ��B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����̂����ꂩ�ɍ��v����ꍇ��false��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�() == false �̏ꍇ�i��������^��c�Ɠ��j<BR>
     * �@@�@@�@@������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ�i���ꒆ�Ŏ�����͎�����j<BR>
     * <BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�������s�ꂩ��̖߂��҂��Ă����Ԃ̏ꍇ(*1)�̂݁A<BR>
     * �@@�@@�@@������������L���[�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �����̒����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h = �����̒����P��.getBranch().���X�R�[�h<BR>
     * �@@�@@���@@���ʃR�[�h = �����̒����P��.���ʃR�[�h<BR>
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*2) = �����̒����P��.����Rev.<BR>
     * �@@�@@���@@�����敪 != "0�F������"<BR>
     * <BR>
     * �@@�@@(*1)�������s�ꂩ��̖߂��҂��Ă����Ԃ̏ꍇ<BR>
     * �@@�@@�@@�@@�����̒����P��.������Ԃ��ȉ��̂����ꂩ�ɍ��v����ꍇ�A<BR>
     * �@@�@@�@@�@@�������s�ꂩ��̖߂��҂��Ă����Ԃł���Ɣ��肷��B<BR>
     * �@@�@@�@@�@@---------------------------------<BR>
     * �@@�@@�@@�@@�@@�@@ACCEPTED<BR>
     * �@@�@@�@@�@@�@@�@@MODIFY_ACCEPTED<BR>
     * �@@�@@�@@�@@�@@�@@ORDERING<BR>
     * �@@�@@�@@�@@---------------------------------<BR>
     * <BR>
     * �@@�@@(*2)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@this.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��<BR>
     * �@@�@@�@@�@@�擾���w�肷��B<BR>
     * <BR>
     * �R�j�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Afalse��ԋp����B<BR>
     * �@@�@@�@@�Y���f�[�^�����݂���ꍇ�́Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isMarketNotifyingOrderInBreakTimeZone(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMarketNotifyingOrderInBreakTimeZone(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() ||
            !WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        OrderStatusEnum l_orderStatus = l_orderUnit.getOrderStatus();
        if (!OrderStatusEnum.ACCEPTED.equals(l_orderStatus) &&
            !OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) &&
            !OrderStatusEnum.ORDERING.equals(l_orderStatus))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };
        int l_intIndex = this.getIndexOfOrderRevInCorpCode();
        int l_intFigure = this.getFigureOfOrderRev();
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code = ?");
        l_strWhere.append(" and branch_code = ?");
        l_strWhere.append(" and order_request_number = ?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
        l_strWhere.append(" and status != ?");
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                HostEqtypeOrderAllRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        int l_intListSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intListSize = l_lisSearchResult.size();
        }
        if (l_intListSize == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * (lock������������L���[)<BR>
     * �w��̒����P�ʂ̊�����������L���[�f�[�^�ɋ��L���b�N��������B<BR>
     * <BR>
     * �P�j�@@�����̒����P�ʃI�u�W�F�N�g���w�肵�A<BR>
     * �@@�@@�@@������������L���[TransactionCallback�N���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@��������TransactionCallback�N���X���w�肵�A<BR>
     * �@@�@@�@@QueryProcessor.doTransaction()�����s����B<BR>
     * �@@�@@�@@�i�g�����U�N�V���������F TX_JOIN_EXISTING�j<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    public void lockHostEqtypeOrderAll(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "lockHostEqtypeOrderAll(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityOrderAllTransactionCallback l_transactionCallback =
            new WEB3EquityOrderAllTransactionCallback(l_orderUnit);
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doTransaction(
                QueryProcessor.TX_JOIN_EXISTING,
                l_transactionCallback);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (get������������L���[)<BR>
     * �w��̒����P�ʂɊY�����銔����������L���[���擾���Ԃ��B<BR>
     * �Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �P�j�@@������������L���[�e�[�u�����A�ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h = �����̒����P��.getBranch().�،���ЃR�[�h<BR>
     * �@@�@@���@@���X�R�[�h = �����̒����P��.getBranch().���X�R�[�h<BR>
     * �@@�@@���@@���ʃR�[�h = �����̒����P��.���ʃR�[�h<BR>
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*1) =  �����̒����P��.����Rev.<BR>
     * �@@�@@���@@�����敪 = "������"<BR>
     * <BR>
     * �@@�@@(*1)�J�n�ʒu�A������<BR>
     * �@@�@@�@@�@@this.get����Rev�J�n�ʒuIN�Г���������()�Aget����Rev����()��<BR>
     * �@@�@@�@@�@@�擾���w�肷��B<BR>
     * <BR>
     * �Q�j�@@�擾����������������L���[Params��ԋp����B<BR>
     * �@@�@@�@@�Y������f�[�^�����݂��Ȃ��ꍇ�́Anull��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return HostEqtypeOrderAllParams
     * @@throws WEB3BaseException
     */
    public HostEqtypeOrderAllParams getHostEqtypeOrderAll(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHostEqtypeOrderAll(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(l_orderUnitRow.getBranchId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };
        int l_intIndex = this.getIndexOfOrderRevInCorpCode();
        int l_intFigure = this.getFigureOfOrderRev();
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code = ?");
        l_strWhere.append(" and branch_code = ?");
        l_strWhere.append(" and order_request_number = ?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ") = ?");
        l_strWhere.append(" and status = ?");
        List l_lisSearchResult = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisSearchResult = l_processor.doFindAllQuery(
                HostEqtypeOrderAllRow.TYPE,
                l_strWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        HostEqtypeOrderAllParams l_params = null;
        int l_intListSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intListSize = l_lisSearchResult.size();
        }
        if (l_intListSize > 0)
        {
            l_params = (HostEqtypeOrderAllParams)l_lisSearchResult.get(0);
        }
        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
    
    /**
     * (update������������L���[AT��t���ԊO)<BR>
     * �O���t���ԊO�������Ĕ������邽�߂̃L���[�f�[�^�X�V���s���B<BR>
     * <BR>
     * �P�j�@@������is��� == true�i����j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏����ɍ��v���郌�R�[�h.�����敪��"������"�ɍX�V����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����敪 = "���"<BR>
     * �@@�@@���@@�Г��������� = this.get�i������j�Г���������(�����̒����P�ʁi�X�V�O�j)�̖߂�l<BR>
     * �@@�@@���@@�S���������敪 = "�S�����ȊO"<BR>
     * <BR>
     * �Q�j�@@������is��� == false�i����ȊO�j�̏ꍇ�A<BR>
     * �@@�@@�ȉ��̏����ɍ��v���郌�R�[�h���X�V����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@����敪 = "����ȊO"<BR>
     * �@@�@@���@@�Г��������� = this.get�Г���������(�����̒����P�ʁi�X�V�O�j)�̖߂�l<BR>
     * �@@�@@���@@�S���������敪 = "�S�����ȊO"<BR>
     * <BR>
     * �@@�@@[�X�V�Ώۍ���]<BR>
     * �@@�@@�@@�Г��������ځF�@@this.get�Г���������(�����̒����P�ʁi�X�V��j)�̖߂�l<BR>
     * �@@�@@�@@�����敪�F�@@"������"<BR>
     * @@param l_orderUnitAfter - (�����P�ʁi�X�V��j)<BR>
     * �X�V��̒����P�ʃI�u�W�F�N�g�B
     * @@param l_orderUnitBefore - (�����P�ʁi�X�V�O�j)<BR>
     * �X�V�O�̒����P�ʃI�u�W�F�N�g�B
     * @@param l_blnIsCancel - (is���)<BR>
     * ������ǂ����𔻒肷��t���O�B<BR>
     * �itrue�F����Afalse�F����ȊO�j
     * @@throws WEB3BaseException
     */
    public void updateHostEqtypeOrderAllAtAcceptOvertime(
        EqTypeOrderUnit l_orderUnitAfter,
        EqTypeOrderUnit l_orderUnitBefore,
        boolean l_blnIsCancel)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateHostEqtypeOrderAllAtAcceptOvertime(EqTypeOrderUnit, EqTypeOrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            if (l_blnIsCancel)
            {
                String l_strWhere =
                    "cancel_div=? and corp_code=? and all_order_change_div=?";
                Object[] l_objWhere =
                {
                    WEB3CancelDivDef.CANCEL,
                    this.getOrgCorpCode(l_orderUnitBefore),
                    WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
                };
                HashMap l_map = new HashMap();
                l_map.put("status", WEB3FrontOrderStatusDef.NOT_DEAL);
                l_processor.doUpdateAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_strWhere,
                    l_objWhere,
                    l_map);
            }
            else
            {
                String l_strWhere =
                    "cancel_div=? and corp_code=? and all_order_change_div=?";
                Object[] l_objWhere =
                {
                    WEB3CancelDivDef.EXCEPT_CANCEL,
                    this.getCorpCode(l_orderUnitBefore),
                    WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE
                };
                HashMap l_map = new HashMap();
                l_map.put("corp_code", this.getCorpCode(l_orderUnitAfter));
                l_map.put("status", WEB3FrontOrderStatusDef.NOT_DEAL);
                l_processor.doUpdateAllQuery(
                    HostEqtypeOrderAllRow.TYPE,
                    l_strWhere,
                    l_objWhere,
                    l_map);
            }
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getNext����Rev)<BR>
     * �����̒���Rev��1���Z�����l��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̒���Rev��1���Z����B<BR>
     * <BR>
     * �Q�j�@@����Rev�̌������擾����B<BR>
     * �@@�@@this.get����Rev����()<BR>
     * <BR>
     * �R�j�@@�Z�o��������Rev������Rev�̌����𒴂����ꍇ�́A
     * �@@�@@�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00746<BR>
     * <BR>
     * @@param l_strOrderRev - (����Rev)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@return String
     * @@throws WEB3BusinessLayerException
     */
    public String getNextOrderRev(String l_strOrderRev) throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "getNextOrderRev(String)";
        log.entering(STR_METHOD_NAME);
        
        String l_strNextOrderRev = "";
        int l_intNextOrderRev = Integer.parseInt(l_strOrderRev) + 1;
        l_strNextOrderRev = Integer.toString(l_intNextOrderRev);
        
        int l_intMaxOrderRevLen = this.getFigureOfOrderRev();
        int l_intNextOrderRevLen = l_strNextOrderRev.length();
        if (l_intNextOrderRevLen > l_intMaxOrderRevLen)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        StringBuffer l_strBufNextOrderRev = new StringBuffer();
        for (int i = l_intNextOrderRevLen; i < l_intMaxOrderRevLen; i++)
        {
            l_strBufNextOrderRev.append("0");
        }
        l_strBufNextOrderRev.append(l_strNextOrderRev);
            
        log.exiting(STR_METHOD_NAME);
        return l_strBufNextOrderRev.toString();
    }

    /**
     * (getPTS����������Rev)<BR>
     * �����̒����㒍���P�ʃI�u�W�F�N�g���A  <BR>
     * �������ɒ����P�ʃe�[�u��.����Rev�ɐݒ肷�镶������擾���Ԃ��B  <BR>
     * �iPTS�����̏ꍇ�ɃR�[�������B�j <BR>
     * <BR>
     * -------------------  <BR>
     * �������P�ʃe�[�u��.����Rev.�ݒ�l�ɂ���  <BR>
     * �@@�|���s�����������܂ށA�ڋq�̒����I�y���[�V�����̃J�E���^�B<BR>
     * �@@�@@�s��֑�������̉񐔂��ݒ肳���B   <BR>
     * �@@�|�����܂Ŏs��֑�������̉񐔂݂̂��Ǘ�����K�v�����邽�߁A  <BR>
     * �@@�@@�@@�ȉ��̃P�[�X�ɊY����������̏ꍇ�̓J�E���g�A�b�v���Ă͂����Ȃ��B  <BR>
     * �@@�@@�@@���s�ꖢ���M�����ɑ΂��������  <BR>
     * -------------------  <BR>
     * <BR>
     * �s��J�ǁ^�ǎ��ԑтɂ���āA�ȉ��̏������s���B <BR>
     * <BR>
     * �@@�|[�s��J�ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j]  <BR>
     * <BR>
     * �@@�@@�����̒����P��.����Rev.�Ɂ{�P�����������ԋp����B  <BR>
     * �@@�@@�@@�E�߂�l�̕�����́Athis.get����Rev����()�̌����Œ�i�擪��0���߁j�Ƃ���B<BR>
     * �@@�@@�@@�E�����𒴂���l�ƂȂ����ꍇ�́A<BR>
     * �@@�@@�@@�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B  <BR>
     * �@@�@@�@@class:  WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02185<BR>
     * <BR>
     * �@@�|[�s��ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j]  <BR>
     * <BR>
     * �@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B<BR>
     * @@param l_orderUnit - (�����㒍���P��)<BR>
     * ������̒����P�ʃI�u�W�F�N�g�B  <BR>
     * �ixTrade�W�����ڂɁA������̒l���ݒ肳��Ă���I�u�W�F�N�g�j<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPTSChangeOrderRev(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPTSChangeOrderRev(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        String l_strPTSChangeOrderRev = null;

        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderRev = l_orderUnitRow.getOrderRev();

        //�s��J�ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true�j
        boolean l_blnIsTradeOpenTimeZone = WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone();
        if (l_blnIsTradeOpenTimeZone)
        {
            int l_intOrderRev = Integer.parseInt(l_strOrderRev);
            int l_intPTSChangeOrderRev = l_intOrderRev + 1;
            String l_strChangeOrderRev = Integer.toString(l_intPTSChangeOrderRev);
            int l_intFigureOfOrderRev = this.getFigureOfOrderRev();
            //�����𒴂���l�ƂȂ����ꍇ�́A
            //�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����B
            if (l_strChangeOrderRev.length() > l_intFigureOfOrderRev)
            {
                log.debug("����Rev.�̒l���ő包���𒴉߁B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02185,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����Rev.�̒l���ő包���𒴉߁B");
            }

            int l_intSize = l_intFigureOfOrderRev - l_strChangeOrderRev.length();
            StringBuffer l_sbOrderRev = new StringBuffer();
            for (int i = 0; i < l_intSize; i++)
            {
                l_sbOrderRev.append("0");
            }
            l_sbOrderRev.append(l_strChangeOrderRev);

            l_strPTSChangeOrderRev = l_sbOrderRev.toString();
        }
        //�s��ǎ��ԑсiPTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false�j
        else
        {
            l_strPTSChangeOrderRev = l_strOrderRev;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPTSChangeOrderRev;
    }
}
@
