head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���v�Z�T�[�r�X(WEB3BondBizLogicProvider.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  ꎉ� (���u) �V�K�쐬
                    2006/10/08  �юu�� (���u) �d�l�ύX �v�Z����013
                    2006/10/12  ���G�� (���u) WEB�V�J���W���̌������̑Ή��inew BigDecimal�����j
 Revesion History : 2007/07/23  �Ӑ� (���u) �d�l�ύX���f��228 �A�v�Z����016
 Revesion History : 2007/08/29  �đo�g(���u) �v�Z����017
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.data.BondProductCouponRow;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccruedInterestCalcTypeDef;
import webbroker3.common.define.WEB3BaseDateDivDef;
import webbroker3.common.define.WEB3BaseDaysDivDef;
import webbroker3.common.define.WEB3BondCategCodeDef;
import webbroker3.common.define.WEB3CalcBaseFormDef;
import webbroker3.common.define.WEB3ChangeRoundDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3ElapsedDaysDivDef;
import webbroker3.common.define.WEB3RoundDivDef;
import webbroker3.common.define.WEB3YearlyInterestPaymentsDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���v�Z�T�[�r�X)<BR>
 * ���v�Z�T�[�r�X<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3BondBizLogicProvider extends WEB3GentradeBizLogicProvider
    implements GlobalBizLogicProvider, BondBizLogicProvider
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBizLogicProvider.class);
    
    /**
     * @@roseuid 44E352DD008C
     */
    public WEB3BondBizLogicProvider() 
    {
     
    }
    
    /**
     * (calc�~�݊��Z)<BR>
     * �w����z���w�背�[�g�ŖM�݊��Z���s���B <BR>
     * <BR>
     * �P�j�v�Z�Ɏg�p����בփ��[�g���擾����B<BR>
     * <BR>
     * �@@�P�|�P�j�����̈בփ��[�g�@@!=�@@NULL�̏ꍇ<BR>
     * �@@�בփ��[�g�@@=�@@�����̈בփ��[�g<BR>
     * <BR>
     * �@@�P�|�Q�j�����̈בփ��[�g�@@==�@@NULL�̏ꍇ<BR>
     * �@@�בփ��[�g�@@=�@@�����̒ʉ݃I�u�W�F�N�g.get�בփ��[�g( )<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@is���t�F������is���t<BR>
     * �@@is���v�Z�F������is���v�Z<BR>
     * �@@���͈בփ��[�g�F0<BR>
     * <BR>�@@
     * �Q�j���z�i�~�݁j�@@=�@@�����̋��z�@@*�@@�P�j�Ŏ擾�����בփ��[�g<BR>
     * <BR>
     * �R�j�[���������s�Ȃ��B<BR>
     * �@@�Q�j�̌v�Z���ʂ̏������������̒ʉ݃I�u�W�F�N�g.�~�݊��Z�ۂߕ����Ŋۂߏ������s�Ȃ��B<BR>
     * <BR>
     * �S�j�R�j�̌v�Z���ʂ�ԋp����B<BR>
     * @@param l_bdForeignAmount - (���z�i�O�݁j)<BR>
     * ���z�i�O�݁j<BR>
     * @@param l_currency - (�ʉ�)<BR>
     * �ʉ݃I�u�W�F�N�g<BR>
     * @@param l_blnIsBuy - (is���t)<BR>
     * ���t���̔��� <BR>
     * <BR>
     * true�F�� <BR>
     * false�F�� <BR>
     * @@param l_blnIsExecuteCalc - (is���v�Z)<BR>
     * ���v�Z���̔��� <BR>
     * <BR>
     * true�F���v�Z <BR>
     * false�F�T�Z�v�Z <BR>
     * @@param l_bdFxRate - (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     * @@return BigDecimal
     * @@roseuid 44BDCE6002C8
     */
    public BigDecimal calcJPYAmount(BigDecimal l_bdForeignAmount, 
        WEB3GentradeCurrency l_currency, 
        boolean l_blnIsBuy, 
        boolean l_blnIsExecuteCalc, 
        BigDecimal l_bdFxRate) 
    {
        
        final String STR_METHOD_NAME = " calcJPYAmount(BigDecimal,  " + 
        "WEB3GentradeCurrency, boolean, boolean, BigDecimal)";
        log.entering(STR_METHOD_NAME);       
        
        if (l_bdForeignAmount == null || l_currency == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�@@�P�|�P�j�����̈בփ��[�g�@@!=�@@NULL�̏ꍇ
        // �@@�בփ��[�g�@@=�@@�����̈בփ��[�g
        BigDecimal l_bdRate = null;
        BigDecimal l_bdEstimatePrice = null;
        if (l_bdFxRate != null)
        {
            l_bdRate = l_bdFxRate;
        }
        //�@@�P�|�Q�j�����̈בփ��[�g�@@==�@@NULL�̏ꍇ
        //�@@�בփ��[�g�@@=�@@�����̒ʉ݃I�u�W�F�N�g.get�בփ��[�g( )        
        else
        {
            l_bdRate = new BigDecimal(String.valueOf(l_currency.getExchangeRate(l_blnIsBuy, l_blnIsExecuteCalc, 0)));
        }
        
        // �Q�j���z�i�~�݁j�@@=�@@�����̋��z�@@*�@@�P�j�Ŏ擾�����בփ��[�g
        l_bdEstimatePrice = l_bdForeignAmount.multiply(l_bdRate);
        
        // �R�j�[���������s�Ȃ��B
        // �@@�Q�j�̌v�Z���ʂ̏������������̒ʉ݃I�u�W�F�N�g.�~�݊��Z�ۂߕ����Ŋۂߏ������s�Ȃ��B
        l_bdEstimatePrice = 
            new BigDecimal(String.valueOf(l_bdEstimatePrice.setScale(
                0, this.getRoundingMode(l_currency.getChangeJpyRoundDiv()))));
        
        // �S�j�R�j�̌v�Z���ʂ�ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatePrice;

    }
    
    /**
     * (calc��n���)<BR>
     * calc��n���<BR>
     * <BR>
     * �P�j����n����v�Z���ʂ𐶐�����B<BR>
     * �@@����n����v�Z����.���ʁ�����.����<BR>
     * �@@����n����v�Z����.�P��������.�����P��<BR>
     * �@@����n����v�Z����.�בփ��[�g������.�בփ��[�g<BR>
     * �@@����n����v�Z����.��������ʔ��聁����.��������ʔ���<BR>
     * <BR>
     * �Q�jthis.calc�������(����n����v�Z����, ������)���Ă�<BR>
     * �@@[����]<BR>
     *  �@@����n����v�Z���ʁF������������n����v�Z����<BR>
     * �@@ �������F����.������<BR>
     * <BR>
     * �R�jthis.calc�o�ߗ��q�i����n����v�Z����, ������, ���������j���Ă�<BR>
     * �@@[����]<BR>
     *    ����n����v�Z���ʁF������������n����v�Z����<BR>
     * �@@ �������F����.������<BR>
     * �@@ ���������F����.���������<BR>
     * <BR>
     * �S�jthis.calc��n���(����n����v�Z����, ������)���Ă�<BR>
     * �@@[����]<BR>
     *    ����n����v�Z���ʁF������������n����v�Z����<BR>
     * �@@ �������F����.������<BR>
     * <BR>
     * �T�j����n����v�Z���ʂ�Ԃ�<BR>
     * @@param l_bondOrderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_bdQuantity - (����)<BR>
     * ����<BR>
     * @@param l_bdBondOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_bdFxRate - (�בփ��[�g)<BR>
     * �בփ��[�g<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_bondExecuteDateInfo - (���������)
     * ���������
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@throws WEB3BaseException 
     * @@roseuid 44C822B603C8
     */
    public WEB3BondEstimatedPriceCalcResult calcEstimatedPrice(
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        BigDecimal l_bdQuantity,
        BigDecimal l_bdBondOrderUnit, 
        BigDecimal l_bdFxRate, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcEstimatedPrice(WEB3BondOrderTypeJudge,  "
            + "BigDecimal, "
            + "BigDecimal, " 
            + "BigDecimal, "
            + "WEB3BondProduct, "
            + "WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);

        
        //�P�j����n����v�Z���ʂ𐶐�����B
        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult = 
            new WEB3BondEstimatedPriceCalcResult();
        
        //����n����v�Z����.���ʁ�����.����
        l_bondEstimatedPriceCalcResult.setQuantity(l_bdQuantity);
        
        //����n����v�Z����.�P��������.�����P��
        l_bondEstimatedPriceCalcResult.setPrice(l_bdBondOrderUnit);
        
        //����n����v�Z����.�בփ��[�g������.�בփ��[�g
        l_bondEstimatedPriceCalcResult.setFxRate(l_bdFxRate);
        
        //����n����v�Z����.��������ʔ��聁����.��������ʔ���
        l_bondEstimatedPriceCalcResult.setBondOrderTypeJudge(l_bondOrderTypeJudge);
        
        //�Q�jthis.calc�������(����n����v�Z����, ������)���Ă�
        this.calcTradingPrice(l_bondEstimatedPriceCalcResult, l_bondProduct);
        
        //�R�jthis.calc�o�ߗ��q�i����n����v�Z����, ������, ���������j���Ă�
        this.calcAccruedInterest(l_bondEstimatedPriceCalcResult, l_bondProduct, l_bondExecuteDateInfo);
        
        //�S�jthis.calc��n���(����n����v�Z����, ������)���Ă�
        this.calcEstimatedPrice(l_bondEstimatedPriceCalcResult, l_bondProduct);
        
        //�T�j����n����v�Z���ʂ�Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_bondEstimatedPriceCalcResult;
    }
    
    /**
     * (calc�T�Z�]���z)<BR>
     * ���T�Z�]���z�v�Z���ʂ��쐬����B<BR>
     * <BR>
     * �P�j���T�Z�]���z�v�Z���ʃI�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j���T�Z�]���z�v�Z����.�]���P���Ɉ����̍�����.���p�P�����Z�b�g����B<BR>
     * <BR>
     * �R�j�O�݌����i�����̍�����.is�O�݌�==true�j�̏ꍇ�A<BR>
     * �@@�R�|�P�j�ʉ݃I�u�W�F�N�g���擾����B�ʉ݃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�i���ʁj�ʉ݂̃R���X�g���N�^���R�[���B<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@�،���ЃR�[�h�F�����̕⏕����.�،���ЃR�[�h<BR>
     * �@@�ʉ݃R�[�h�F�����̍�����.�ʉ݃R�[�h<BR>
     * <BR>
     * �R�|�Q�jthis.calc�T�Z�]���z(�O��)( )���R�[���B<BR>
     * �@@�m�����n <BR>
     * �@@���ʁF�����̐��� <BR>
     * �@@�������F�����̍����� <BR>
     * �@@�ʉ݁F�R�|�P�j�Ŏ擾�����ʉ݃I�u�W�F�N�g <BR>
     * <BR>
     * �@@�R�|�R�j�R�|�Q�j�̌v�Z���ʂ����T�Z�]���z�v�Z����.�T�Z�]���z�i�O�݁j�փZ�b�g����B<BR>
     * <BR>
     * �@@�R�|�S�jthis.calc�~�݊��Z( )<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@���z�F�S�|�P�j�̌v�Z����<BR>
     * �@@�ʉ݁F�R�j�Ŏ擾�����ʉ݃I�u�W�F�N�g<BR>
     * �@@is���t�F������is���t<BR>
     * �@@is���v�Z�F������is���v�Z<BR>
     *   �בփ��[�g�FNULL
     * <BR>
     * �@@�R�|�T�j�R�|�S�j�̌v�Z���ʂ����T�Z�]���z�v�Z����.�T�Z�]���z�i�~�݁j�փZ�b�g����B<BR>
     * <BR>�@@
     * �S�j�O�݌����łȂ��i�����̍�����.is�O�݌�==false�j�̏ꍇ�A<BR>
     * <BR>
     * �@@�S�|�P�jthis.calc�T�Z�]���z(�~��)( )���R�[���B<BR>
     * <BR>
     * �@@�m�����n<BR>
     * �@@���ʁF�����̐���<BR>
     * �@@�������F�����̍�����<BR>
     * <BR>
     * �@@�S�|�Q�j�S�|�P�j�̌v�Z���ʂ����T�Z�]���z�v�Z����.�T�Z�]���z�i�~�݁j�փZ�b�g����B<BR>
     * <BR>
     * �@@�S�|�R�j���T�Z�]���z�v�Z����.�T�Z�]���z�i�O�݁j��NULL���Z�b�g����B<BR>
     * <BR>
     * �T�j�����������T�Z�]���z�v�Z���ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_blnIsBuy - (is���t)<BR>
     * ���t���ǂ�������<BR>
     * <BR>
     * ���t�Ftrue<BR>
     * ���p�Ffalse<BR>
     * @@param l_blnIsExecuteCalc - (is���v�Z)<BR>
     * ���v�Z���̔��� <BR>
     * <BR>
     * true�F���v�Z <BR>
     * false�F�T�Z�v�Z <BR>
     * @@return WEB3BondEstimatedAssetCalcResult
     * @@throws WEB3BaseException 
     * @@roseuid 44C95BE50071
     */
    public WEB3BondEstimatedAssetCalcResult calcEstimatedAsset(
        SubAccount l_subAccount, 
        double l_dblQuantity, 
        WEB3BondProduct l_bondProduct, 
        boolean l_blnIsBuy, 
        boolean l_blnIsExecuteCalc) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " calcBondEstimatedAsset(SubAccount, double, WEB3BondProduct, boolean, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        BigDecimal l_bdForeignEstimatedAsset = null;
        BigDecimal l_bdEstimatedAsset = null;
        BigDecimal l_bdJPYAmount = null;
        
        //�P�j���T�Z�]���z�v�Z���ʃI�u�W�F�N�g�𐶐�����B
        WEB3BondEstimatedAssetCalcResult l_bondEstimatedAssetCalcResult = 
            new WEB3BondEstimatedAssetCalcResult();
        
        //�Q�j���T�Z�]���z�v�Z����.�]���P���Ɉ����̍�����.���p�P�����Z�b�g����B 
        l_bondEstimatedAssetCalcResult.setEstimatedPrice(
            new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
        
        //�R�j�R�j�O�݌����i�����̍�����.is�O�݌�==true�j�̏ꍇ
        if (l_bondProduct.isForeignCurrency())
        {
            //�R�|�P�j�ʉ݃I�u�W�F�N�g���擾����B             
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            //�R�|�Q�jthis.calc�T�Z�]���z(�O��)( )���R�[���B 
            l_bdForeignEstimatedAsset = 
                this.calcForeignEstimatedAsset(l_dblQuantity, l_bondProduct, l_currency);
            
            //�R�|�R�j�R�|�Q�j�̌v�Z���ʂ����T�Z�]���z�v�Z����.�T�Z�]���z�i�O�݁j�փZ�b�g����B
            l_bondEstimatedAssetCalcResult.setForeignEstimatedAsset(l_bdForeignEstimatedAsset);
            
            //�R�|�S�jthis.calc�~�݊��Z( ) 
            l_bdJPYAmount = 
                this.calcJPYAmount(l_bdForeignEstimatedAsset, 
                    l_currency, 
                    l_blnIsBuy, 
                    l_blnIsExecuteCalc, 
                    null);
            
            //�R�|�T�j�R�|�S�j�̌v�Z���ʂ����T�Z�]���z�v�Z����.�T�Z�]���z�i�~�݁j�փZ�b�g����B 
            l_bondEstimatedAssetCalcResult.setEstimatedAsset(l_bdJPYAmount);
        }   
        //�S�j�O�݌����łȂ��i�����̍�����.is�O�݌�==false�j�̏ꍇ�A  
        else
        {
            //�S�|�P�jthis.calc�T�Z�]���z(�~��)( )���R�[���B 
            l_bdEstimatedAsset =  
                this.calcEstimatedAsset(l_dblQuantity, l_bondProduct);
           
            //�S�|�Q�j�S�|�P�j�̌v�Z���ʂ����T�Z�]���z�v�Z����.�T�Z�]���z�i�~�݁j�փZ�b�g����B 
            l_bondEstimatedAssetCalcResult.setEstimatedAsset(l_bdEstimatedAsset);
           
            //�S�|�R�j���T�Z�]���z�v�Z����.�T�Z�]���z�i�O�݁j��NULL���Z�b�g����B 
            l_bondEstimatedAssetCalcResult.setForeignEstimatedAsset(null);
        }
           
            //�T�j�����������T�Z�]���z�v�Z���ʃI�u�W�F�N�g��ԋp����B 
            log.exiting(STR_METHOD_NAME);
            return l_bondEstimatedAssetCalcResult;
    }
    
    /**
     * (calc�T�Z�]���z�i�~�݁j)<BR>
     * �T�Z�]���z�i�~�݁j���v�Z����B<BR>
     * <BR>
     * �P�j����.������.is�O����()�̖߂�l == true�̏ꍇ�A<BR>
     * �T�Z�]���z�i�~�݁j������.���� * ����.������.���p�P�� / ����.������.�P�ʊz��<BR>
     * <BR>
     * ���v�Z���ʂ͏����_�ȉ��؂�̂ď������s�Ȃ��B<BR>
     * <BR>
     * �Q�j����.������.is�O����()�̖߂�l == false�̏ꍇ�A<BR>
     * �@@�T�Z�]���z�i�~�݁j���i����.���� * 1000�j * ����.������.���p�P�� / ����.������.�P�ʊz��<BR>
     * <BR>
     * ���v�Z���ʂ͏����_�ȉ��؂�̂ď������s�Ȃ��B<BR>
     *  <BR>
     * �R�j�v�Z���ʂ�ԋp����B  <BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@return BicDecimal
     * @@throws WEB3BaseException 
     * @@roseuid 44C95CD9018B
     */
    protected BigDecimal calcEstimatedAsset(
        double l_dblQuantity, 
        WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " calcEstimatedAsset(double, WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        BigDecimal l_bdEstimate = null;
        BigDecimal l_bdQuantity = null;
        BigDecimal l_bdTemp1 = null;
        BigDecimal l_bdTemp2 = null;
        BigDecimal l_bdTemp3 = null;
        
        
        //�P�j����.������.is�O����()�̖߂�l == true�̏ꍇ�A
        if (l_bondProduct.isForeignBond())
        {
            //�T�Z�]���z�i�~�݁j������.���� * ����.������.���p�P�� / ����.������.�P�ʊz��
            //�v�Z���ʂ͏����_�ȉ��؂�̂ď������s�Ȃ��B
            l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
            l_bdTemp1 = l_bdQuantity.multiply(
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
            l_bdTemp2 = l_bdTemp1.divide(
                new BigDecimal(String.valueOf(l_bondProduct.getParValue())), 0, BigDecimal.ROUND_DOWN);
            l_bdEstimate = l_bdTemp2;
        }
        // �Q�j����.������.is�O����()�̖߂�l == false�̏ꍇ�A
        else
        {
            //�T�Z�]���z�i�~�݁j���i����.���� * 1000�j * ����.������.���p�P�� / ����.������.�P�ʊz��
            //�v�Z���ʂ͏����_�ȉ��؂�̂ď������s�Ȃ��B
            l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
            l_bdTemp1 = l_bdQuantity.multiply(new BigDecimal("1000"));
            l_bdTemp2 = l_bdTemp1.multiply(
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
            l_bdTemp3 = l_bdTemp2.divide(
                new BigDecimal(String.valueOf(l_bondProduct.getParValue())), 0, BigDecimal.ROUND_DOWN);
            l_bdEstimate = l_bdTemp3;
        }

        // �R�j�v�Z���ʂ�ԋp����B  
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimate;

    }
    
    /**
     * (calc�T�Z�]���z�i�O�݁j)<BR>
     * �T�Z�]���z�i�O�݁j���v�Z����B<BR>
     * <BR>
     * �P�j�T�Z�]���z�i�O�݁j������.���� * ����.������.���p�P�� / ����.������.�P�ʊz��<BR>
     * <BR>
     * �Q�j�[���������s�Ȃ��B<BR>
     * �@@�e�ʉ݂̉��ʒʉݒP�ʁi�����������j�ȉ����O�݊��Z�ۂߕ����Ŋۂߏ������s�Ȃ��B<BR>
     * <BR>
     * �R�j�Q�j�̌v�Z���ʂ�ԋp����B<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_currency - (�ʉ�)<BR>
     * �ʉ݃I�u�W�F�N�g<BR>
     * @@return BicDecimal
     * @@throws WEB3BaseException 
     * @@roseuid 44C95D6C00CF
     */
    protected BigDecimal calcForeignEstimatedAsset(double l_dblQuantity, 
        WEB3BondProduct l_bondProduct, 
        WEB3GentradeCurrency l_currency) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcForeignEstimatedAsset(double, "+
        "WEB3BondProduct, "+
        "WEB3GentradeCurrency ) ";
        
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_currency == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        BigDecimal l_bdForeignEstimate = null;
        
        // �P�j�T�Z�]���z�i�O�݁j������.���� * ����.������.���p�P�� / ����.������.�P�ʊz��
        // �Q�j�[���������s�Ȃ��B
        // �@@�e�ʉ݂̉��ʒʉݒP�ʁi�����������j�ȉ����O�݊��Z�ۂߕ����Ŋۂߏ������s�Ȃ��B
        BigDecimal l_bdQuantity = 
            new BigDecimal(String.valueOf(l_dblQuantity));
        BigDecimal l_bdTemp = 
            l_bdQuantity.multiply(new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
        l_bdForeignEstimate = 
            l_bdTemp.divide(new BigDecimal(String.valueOf(l_bondProduct.getParValue())),
                l_currency.getScale(), this.getRoundingMode(l_currency.getChangeFCcyRoundDiv()));
       
        // �R�j�Q�j�̌v�Z���ʂ�ԋp����B  
        log.exiting(STR_METHOD_NAME);
        return l_bdForeignEstimate;
    }
    
    /**
     * (calc�o�ߗ��q)<BR>
     * �o�ߗ��q���v�Z����B<BR>
     * <BR>
     * �����̍���n����v�Z����.�o�ߗ��q�i�~�݁j�A�o�ߗ��q�i�O�݁j�Ɍv�Z���ʂ��Z�b�g����B<BR>
     * <BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc <BR>
     * �P�Dcalc�o�ߗ��q()�@@��Q�ƁB<BR>
     * <BR>
     * @@param l_bondPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CDBC1D02CC
     */
    protected void calcAccruedInterest(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcAccruedInterest(WEB3BondEstimatedPriceCalcResult, " + 
            "WEB3BondProduct, WEB3BondExecuteDateInfo) ";
        log.entering(STR_METHOD_NAME);
        
        //�����̍�����.is�O����()�@@==�@@true�@@�̏ꍇ
        if (l_bondProduct.isForeignBond())
        {
            //this.calc�o�ߗ��q(�O����)()�@@���R�[���B
            this.calcAccruedInterestForForeignBond(
                l_bondPriceCalcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
            //�����̍�����.is�O�݌�()�@@==�@@false�@@�̏ꍇ
            //�����̎�n����v�Z����.�o�ߗ��q(�O��)��NULL���Z�b�g�B
            if(!l_bondProduct.isForeignCurrency())
            {
                l_bondPriceCalcResult.setForeignAccruedInterest(null);
            }
        }
        
        //�����̍�����.is�O����()�@@==�@@false�@@�̏ꍇ
        else
        {
            //this.calc�o�ߗ��q(������)()���R�[���B
            this.calcAccruedInterestForDomesticBond(
                l_bondPriceCalcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
      
    /**
     * (calc�������)<BR>
     * calc�������<BR>
     * <BR>
     * ����n����v�Z���ʂ��C���^�[�t�F�[�X�Ƃ��A����������v�Z����<BR>
     * <BR>
     * �P�jthis.calc��������i����, �P��, �������j���R�[�����āA����������v�Z<BR>
     * �@@ [����]<BR>
     * �@@�@@�@@���ʁF����.����n����v�Z����.����<BR>
     * �@@�@@�@@�@@�P���F����.����n����v�Z����.�P��<BR>
     * �@@�@@�@@�@@�������F����.������<BR>
     * <BR>
     * �Q�j������.is�O�݌�����false�̏ꍇ <BR>
     * �@@����n����v�Z����.�������(�~��)�@@=�@@�P)�Ōv�Z������������̒l<BR>
     * <BR>
     * �R) ������.is�O�݌�����true�̏ꍇ <BR>
     * �@@�@@�@@�R�|�P�j����n����v�Z����.�������(�O��) = �P)�Ōv�Z������������̒l<BR>
     * �@@�@@�@@�R�|�Q�j�i���ʁj�ʉ�.�i���ʁj�ʉ݁i�،���ЃR�[�h�A�ʉ݃R�[�h�j�Łi���ʁj�ʉ݂��擾����B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h��������.get�،����().get�،���ЃR�[�h()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ʉ݃R�[�h��������.get�ʉ݃R�[�h()<BR>
     * �@@�@@�@@�R�|�R�j�������(�O��)���~�݊��Z����<BR>
     * �@@�@@�@@�Ethis.�~�݊��Z()�R�[�����āA�~�݂̔���������v�Z����<BR>
     * �@@�@@�@@ �@@[����]<BR>
     * �@@�@@�@@�@@�@@     ���z(�O��)�F�P�j�Ōv�Z�����������<BR>
     * �@@�@@�@@�@@�@@     �ʉ݁F(����)�ʉ݃I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�@@     is���t�Ftrue<BR>
     * �@@�@@�@@�@@�@@     is���v�Z�Ftrue<BR>
     * �@@�@@�@@�@@�@@     �בփ��[�g�F����n����v�Z����.�בփ��[�g<BR>
     * �@@�@@�@@�R�|�S�j����n����v�Z����.�������(�~��)�@@= �R�|�R�j�Ŏ擾�����~�݊��Z��̔������<BR>
     * @@param l_bondPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D09D250167
     */
    protected void calcTradingPrice(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcTradePrice(WEB3BondEstimatedPriceCalcResult, WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_bondPriceCalcResult == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�P�jthis.calc��������i����, �P��, �������j���R�[�����āA����������v�Z
        //   ���ʁF����.����n����v�Z����.����<BR>
        //�@@ �P���F����.����n����v�Z����.�P��<BR>
        // �@@�������F����.������<BR>
        BigDecimal l_bdTradePrice = 
            this.calcTradingPrice(l_bondPriceCalcResult.getQuantity(), l_bondPriceCalcResult.getPrice(), l_bondProduct);
        
        //�Q�j������.is�O�݌�����false�̏ꍇ
        //    ����n����v�Z����.�������(�~��)�@@=�@@�P)�Ōv�Z������������̒l
        if (!l_bondProduct.isForeignCurrency())
        {
            l_bondPriceCalcResult.setTradingPrice(l_bdTradePrice);
        }
        
        //�R)������.is�O�݌�����true�̏ꍇ
        else
        {
            // �R�|�P�j����n����v�Z����.�������(�O��) = �P)�Ōv�Z������������̒l
            l_bondPriceCalcResult.setForeignTradePrice(l_bdTradePrice);
            
            // �R�|�Q�j�i���ʁj�ʉ�.�i���ʁj�ʉ݁i�،���ЃR�[�h�A�ʉ݃R�[�h�j�Łi���ʁj�ʉ݂��擾����B
            //�@@�@@�@@�@@[����]
            //�@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h��������.get�،����().get�،���ЃR�[�h()
            //�@@�@@�@@�@@�@@�@@�@@�ʉ݃R�[�h��������.get�ʉ݃R�[�h()
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_bondProduct.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            // �R�|�R�j�������(�O��)���~�݊��Z����
            BigDecimal l_bdJPYAmount = 
                this.calcJPYAmount(l_bdTradePrice, l_currency, true, true, l_bondPriceCalcResult.getFxRate());
            
            // �@@�R�|�S�j����n����v�Z����.�������(�~��)�@@= �R�|�R�j�Ŏ擾�����~�݊��Z��̔������ 
            l_bondPriceCalcResult.setTradingPrice(l_bdJPYAmount);
        }
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (calc��n���)<BR>
     * calc��n���<BR>
     * <BR>
     * �P�j��n����i�~�݁j���v�Z����B<BR>
     * �@@�P�|�P�j����n����v�Z����.calc��n����i�~�݁j���Ă�<BR>
     * �@@�P�|�Q�j����n����v�Z����.calc��n����i�~�݁j������n����v�Z����.��n����i�~�݁j�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�j������.is�O�݌�����true�̏ꍇ <BR>
     * �@@�Q�|�P�j����n����v�Z����.calc��n����i�O�݁j���Ă�<BR>
     * �@@�Q�|�Q�j����n����v�Z����.calc��n����i�O�݁j������n����v�Z����.��n����i�O�݁j�ɃZ�b�g����B<BR>
     * @@param l_bondPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D0A2A30157
     */
    protected void calcEstimatedPrice(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " calcEstimatedPrice(WEB3BondEstimatedPriceCalcResult) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
       
        //�P�j��n����i�~�݁j���v�Z����B
        //�@@�P�|�P�j����n����v�Z����.calc��n����i�~�݁j���Ă�
        BigDecimal l_bdEstimatedPrice = l_bondPriceCalcResult.calcEstimatedPrice();
        
        //�P�|�Q�j����n����v�Z����.calc��n����i�~�݁j������n����v�Z����.��n����i�~�݁j�ɃZ�b�g����B
        l_bondPriceCalcResult.setEstimatedPrice(l_bdEstimatedPrice);
        
        //�Q�j������.is�O�݌�����true�̏ꍇ
        if (l_bondProduct.isForeignCurrency())
        {
            //�Q�|�P�j����n����v�Z����.calc��n����i�O�݁j���Ă�
            BigDecimal l_bdForeignEstimatedPrice = l_bondPriceCalcResult.calcForeignEstimatedPrice();
            
            //�Q�|�Q�j����n����v�Z����.calc��n����i�O�݁j������n����v�Z����.��n����i�O�݁j�ɃZ�b�g����B
            l_bondPriceCalcResult.setForeignEstimatedPrice(l_bdForeignEstimatedPrice);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�������)<BR>
     * ����������v�Z���A�v�Z�������ʂ�ԋp<BR>
     * <BR>
     * �P�j�P�ʊz�ʂ��擾<BR>
     * �@@�P�|�P�j������.getParValue()<BR>
     * �@@�P�|�Q�j������.getParValue()��BigDecimal�ɂ���<BR>
     * <BR>
     * �Q�j������.is�O�݌�����false�̏ꍇ  <BR>
     * �@@�Q�|�P�j��������i�~�݁j���v�Z����<BR>
     * �@@�@@�@@�@@��������i�~�݁j������.���� * ����.�P�� / �P�ʊz��<BR>
     * �@@�@@�@@�@@�������_�ȉ��l�̌ܓ�<BR>
     * �@@�Q�|�Q�j�v�Z�����������(�~��)��ԋp <BR>
     * <BR>
     * �R�j������.is�O�݌�����true�̏ꍇ<BR>
     * �@@�R�|�P�j�i���ʁj�ʉ�.�i���ʁj�ʉ݁i�،���ЃR�[�h�A�ʉ݃R�[�h�j�Łi���ʁj�ʉ݂��擾����B<BR>
     * �@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�@@�،���ЃR�[�h��������.get�،����().get�،���ЃR�[�h()<BR>
     * �@@�@@�@@�@@�@@�@@�@@�ʉ݃R�[�h��������.get�ʉ݃R�[�h()<BR>
     * �@@�R�|�Q�j��������i�O�݁j���v�Z����<BR>
     * �@@�@@�@@�@@��������i�O�݁j������ * �P�� / �P�ʊz��<BR>
     * �@@�@@�@@�@@�@@�i�v�Z���ʂ́A�i���ʁj�ʉ݂̏����������������A�i���ʁj�ʉ݂̊O�݊��Z�ۂߕ����ɂĊۂߏ������s���j<BR>
     * <BR>
     *   �R�|�R�j�v�Z����������(�O��)��ԋp<BR>
     * @@param l_quantity - (����)<BR>
     * ����<BR>
     * @@param l_price - (�P��)<BR>
     * �P��<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@return BigDecimal
     * @@throws WEB3BaseException 
     * @@roseuid 44D42266017E
     */
    public BigDecimal calcTradingPrice(BigDecimal l_quantity, BigDecimal l_price, WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcTradingPrice(BigDecimal, BigDecimal, WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //�P�j�P�ʊz�ʂ��擾
        //�P�|�P�j������.getParValue()
        double l_dblParValue= l_bondProduct.getParValue();
        BigDecimal l_bdParValue = new BigDecimal(String.valueOf(l_dblParValue));
        BigDecimal l_bdTradePrice = null; 
        
        //�Q�j������.is�O�݌�����false�̏ꍇ
        if (!l_bondProduct.isForeignCurrency())
        {
            //�Q�|�P�j��������i�~�݁j���v�Z����
            //      ��������i�~�݁j������.���� * ����.�P�� / �P�ʊz��
            // �����_�ȉ��l�̌ܓ�
            l_bdTradePrice = l_quantity.multiply(l_price);
            l_bdTradePrice = l_bdTradePrice.divide(l_bdParValue, 0, BigDecimal.ROUND_HALF_UP);
        }    
        
        // �R�j������.is�O�݌�����true�̏ꍇ
        if (l_bondProduct.isForeignCurrency())
        {
            //�R�|�P�j�i���ʁj�ʉ�.�i���ʁj�ʉ݁i�،���ЃR�[�h�A�ʉ݃R�[�h�j�Łi���ʁj�ʉ݂��擾����B
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_bondProduct.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            //�R�|�Q�j��������i�O�݁j���v�Z����
            //        ��������i�O�݁j������ * �P�� / �P�ʊz��
            //�i�v�Z���ʂ́A�i���ʁj�ʉ݂̏����������������A�i���ʁj�ʉ݂̊O�݊��Z�ۂߕ����ɂĊۂߏ������s���j
            l_bdTradePrice = l_quantity.multiply(l_price);
            l_bdTradePrice = l_bdTradePrice.divide(l_bdParValue,
                l_currency.getScale(), this.getRoundingMode(l_currency.getChangeFCcyRoundDiv()));
        }    
          log.exiting(STR_METHOD_NAME);
        return l_bdTradePrice;
    }
    
    /**
     * (calc�o�ߗ��q)<BR>
     * �o�ߗ��q���v�Z����B<BR>
     * <BR>
     * (calcAcrruedInterest�̃I�[�o�[���C�h)<BR>
     * <BR>
     * return 0;<BR>
     * @@param l_lngBondProductId - (������ID)<BR>
     * ������ID<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_dblTaxRate - (�ŗ�)<BR>
     * �ŗ�<BR>
     * @@return double
     * @@roseuid 44D71F5D0241
     */
    public double calcAcrruedInterest(long l_lngBondProductId, 
        Date l_datDeliveryDate, 
        double l_dblQuantity, 
        double l_dblTaxRate) 
    {
        return 0;
    }
    
    /**
     * (calc�����)<BR>
     * ��������v�Z����B<BR>
     * <BR>
     * (calcExecutionAmount�̃I�[�o�[���C�h)<BR>
     * <BR>
     * return 0;<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_dblExecutedPrice - (���P��)<BR>
     * ���P��<BR>
     * @@param l_dblExecutedQuantity - (��萔��)<BR>
     * ��萔��<BR>
     * @@param l_quantityType - (���ʃ^�C�v)<BR>
     * ���ʃ^�C�v<BR>
     * @@return double
     * @@roseuid 44D7204201B6
     */
    public double calcExecutionAmount(ProductTypeEnum l_productType, 
        long l_lngProductId, 
        double l_dblExecutedPrice, 
        double l_dblExecutedQuantity, 
        QuantityTypeEnum l_quantityType) 
    {
        return 0;
    }
    
    /**
     * (check����]��)<BR>
     * �]�̓`�F�b�N������B<BR>
     * <BR>
     * (checkTradingPower�̃I�[�o�[���C�h)<BR>
     * <BR>
     * return OrderValidationResult.VALIDATION_OK_RESULT;<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_orderSpec - (�������e)<BR>
     * �������e<BR>
     * @@return OrderValidationResult
     * @@roseuid 44D720C50187
     */
    public OrderValidationResult checkTradingPower(SubAccount l_subAccount, OrderSpec l_orderSpec) 
    {
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    public double calcCommission(OrderExecution arg0)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public double calcCapitalGainTax(SubAccount arg0, TaxTypeEnum arg1, double arg2)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * (get�ۂ߃��[�h)<BR>
     * �����̊ۂߕ����ɑΉ�����BigDecimal�̊ۂ߃��[�h��ԋp����B<BR>
     * <BR>
     * @@param l_strDealType �ۂߕ���
     * <BR>  
     * @@return �ۂ߃��[�h
     */
    public int getRoundingMode(String l_strDealType)
    {   
        if (WEB3ChangeRoundDivDef.CUTTING_OFF.equals(l_strDealType))
        {
            return BigDecimal.ROUND_FLOOR;   
        }
        else if (WEB3ChangeRoundDivDef.RAISING_TO_A_UNIT.equals(l_strDealType))
        {
            return BigDecimal.ROUND_CEILING;
        }
        else if (WEB3ChangeRoundDivDef.ROUNDING_OFF.equals(l_strDealType))
        {
            return BigDecimal.ROUND_HALF_UP;
        }
        else
        {
            return BigDecimal.ROUND_UNNECESSARY;
        }
    }    
    /**
     * (calc�o�ߗ��q(�O����))<BR>
     * �O�����̏ꍇ�Acalc�o�ߗ��q()����R�[�������B<BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc<BR>
     * �Q�Dcalc�o�ߗ��q(�O����)()�@@��Q�ƁB<BR>
     * @@param l_bondPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F2585303D8
     */
    protected void calcAccruedInterestForForeignBond(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcAccruedInterestForForeignBond("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, WEB3BondExecuteDateInfo) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //(1)�@@������ʂɂ��o�ߗ��q�v�Z���{����
        //�����̎�n����v�Z����.��������ʔ���.is���咍���@@==�@@true�@@�̏ꍇ
        if (l_bondPriceCalcResult.getBondOrderTypeJudge().isRecruitOrder())
        {
            //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO���Z�b�g���Areturn�B
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //(2)�@@���t�^�C�v�ɂ��o�ߗ��q�v�Z���{����
        //�����̍�����.���t�^�C�v�@@==�@@�g�����h�@@�̏ꍇ
        if (CouponTypeEnum.ZERO_COUPON.equals(l_bondProduct.getCouponType()))
        {
            //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO���Z�b�g���Areturn�B
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //(3)�@@�}�X�^�`�F�b�N�ɂ��o�ߗ��q�v�Z���{����
        //�E�����̍�����.���ғ��@@���@@�����̍�����.���s���@@�̏ꍇ
        //�E�����̍�����.���ғ��@@���@@�����̍�����.�o�ߗ��q�N�Z���@@�̏ꍇ
        if (WEB3DateUtility.compareToDay(
                l_bondProduct.getMaturityDate(), 
                l_bondProduct.getIssueDate()) <= 0
            || WEB3DateUtility.compareToDay(
                l_bondProduct.getMaturityDate(), 
                l_bondProduct.getAccruedInterestStartDay()) <= 0)
        {
            //��L�m��������n�̂����ꂩ�ɂ��Ă͂܂�ꍇ��
            //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
            //�����̎�n����v�Z����.�x���敪�ꗗ��"�o�ߗ��q�v�Z�s�\"��ǉ����Areturn�B
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.addWarningDiv(
                WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //(4)�@@�������ɂ��o�ߗ��q�v�Z�x������@@(�����̍�����.is�o�ߗ��q�v�Z�x������())
        //�����̍�����.is�o�ߗ��q�v�Z�x������()�̖߂�l��false�̏ꍇ�A
        if (!l_bondProduct.isAccruedInterestCalcWarningJudge())
        {
            //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�����m�łȂ��\���h��ǉ�����B
            l_bondPriceCalcResult.addWarningDiv(
                WEB3BondWarningDivDef.ACCRUED_INTEREST_WRONG_POSSIBLE);
        }
        
        //(5)�@@�o�ߗ��q�v�Z�����擾
        //�����̍�����.�o�ߗ��q�v�Z�^�C�v���L�[�Ƃ��āA
        //�o�ߗ��q�v�Z�����e�[�u������o�ߗ��q�v�Z�������擾����B
        //(�o�ߗ��q�v�Z�����N���X�̃R���X�g���N�^���擾)
        String l_strAccruedInterestCalcType = 
            l_bondProduct.getAccruedInterestCalcType();
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition = 
            new WEB3BondAccruedInterestCalcCondition(l_strAccruedInterestCalcType);
        
        //(6)�@@�o�ߗ��q�v�Z�K�{�������ڂ̎Z�o
        //(6-1)�@@����Z�o�@@(this.calc���())�@@
        Date l_datBaseDate = this.calcBaseDate(
            l_bondExecuteDateInfo, 
            l_accruedInterestCalcCondition, 
            l_bondProduct,
            l_bondPriceCalcResult);
        
        List l_lisWarningDivList = l_bondPriceCalcResult.getWarningDivList();
        if (l_lisWarningDivList != null && l_lisWarningDivList.contains(
            WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION))
        {
            return;
        }
        
        //(6-2)�@@�O�񗘕���/���񗘕����Z�o�@@(this.calc������())
        WEB3BondInterestPaymentDay l_interestPaymentDay = this.calcInterestPaymentDay(
            l_bondPriceCalcResult, 
            l_bondProduct, 
            l_datBaseDate);
        
        l_lisWarningDivList = l_bondPriceCalcResult.getWarningDivList();
        if (l_lisWarningDivList != null && l_lisWarningDivList.contains(
            WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION))
        {
            return;
        }
        
        //(6-3)�@@������Z�o�@@(this.calc�����())
        this.calcBaseDays(
            l_bondPriceCalcResult, 
            l_bondProduct, 
            l_accruedInterestCalcCondition, 
            l_datBaseDate,
            l_interestPaymentDay.getPreInterestPaymentDay(),
            l_interestPaymentDay.getNextInterestPaymentDay());
        
        l_lisWarningDivList = l_bondPriceCalcResult.getWarningDivList();
        if (l_lisWarningDivList != null && l_lisWarningDivList.contains(
            WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION))
        {
            return;
        }
        
        //(6-4)�@@�o�ߓ����Z�o�@@(this.calc�o�ߓ���())�@@���o�ߓ���MAX�`�F�b�N���s�Ȃ��B
        this.calcElapsedDays(
            l_bondPriceCalcResult, 
            l_accruedInterestCalcCondition, 
            l_datBaseDate,
            l_interestPaymentDay.getPreInterestPaymentDay());
        
        //(7)�@@�o�ߗ��q�v�Z
        //�o�ߗ��q
        BigDecimal l_bdAccruedInterest = null;
        BigDecimal l_bdAccruedInterestWork = null;

        //�P��
        BigDecimal l_bdPrice = null;
        BigDecimal l_bdPriceWork = null;
                
        //�����̎�n����v�Z����.����
        BigDecimal l_bdQuantity = l_bondPriceCalcResult.getQuantity();
        
        //�����̎�n����v�Z����.�o�ߓ���
        BigDecimal l_bdElapsedDays = 
            new BigDecimal(String.valueOf(l_bondPriceCalcResult.getElapsedDays().intValue()));
        
        //�����̎�n����v�Z����.�����
        BigDecimal l_bdCalcBaseDays = 
            new BigDecimal(String.valueOf(l_bondPriceCalcResult.getCalcBaseDays().intValue()));
        
        BigDecimal l_bdHundred = new BigDecimal("100");
        
        //�o�ߗ��q�v�Z����.���z���������敪 
        String l_strAmountRoundDiv = l_accruedInterestCalcCondition.getAmountRoundDiv();
        
        //�o�ߗ��q�v�Z����.�P�ʖ��������敪
        String l_strUnitRoundDiv = l_accruedInterestCalcCondition.getUnitRoundDiv();
        
        //�����̍������D�ʉ݃R�[�h
        int l_intPriceScale = 0;
        if (WEB3GentradeCurrencyCodeDef.JPY.equals(l_bondProduct.getCurrencyCode()))
        {
            l_intPriceScale = 0;
        }
        else
        {
            l_intPriceScale = 2;
        }
        
        //�P������
        int l_intUnitPriceScale = l_accruedInterestCalcCondition.getUnitPriceScale();
        
        //����
        BigDecimal l_bdCoupon = l_interestPaymentDay.getCoupon();
        
        //(7-1)�@@���z�D������@@((5)�Ŏ擾�����o�ߗ��q�v�Z�����̌v�Z��敪��"���z�D�����"�̏ꍇ)
        if (WEB3CalcBaseFormDef.AMOUNT_PRIORITY_FORM.equals(l_accruedInterestCalcCondition.getCalcBaseForm())) 
        {
            //�o�ߗ��q�@@=�@@ �����̎�n����v�Z����.���ʁ@@�~
            //(�@@(6-2)�Ŏ擾���������@@�^�@@100�@@)�@@�~�@@
            //(�����̎�n����v�Z����.�o�ߓ����@@�^�@@�����̎�n����v�Z����.�����)
            l_bdAccruedInterestWork = 
                l_bdQuantity.multiply(
                    l_bdCoupon.divide(l_bdHundred, 10, BigDecimal.ROUND_HALF_UP)).multiply(
                        l_bdElapsedDays.divide(l_bdCalcBaseDays, 10, BigDecimal.ROUND_HALF_UP));
            l_bdAccruedInterest = 
                getCutOutValueForAccruedInterest(l_bdAccruedInterestWork, l_intPriceScale, l_strAmountRoundDiv);
        }
        
        //(7-2)�@@�P���D������@@((5)�Ŏ擾�����o�ߗ��q�v�Z�����̌v�Z��敪��"�P���D�����"�̏ꍇ)
        else if (WEB3CalcBaseFormDef.UNIT_PRICE_PRIORITY_FORM.equals(l_accruedInterestCalcCondition.getCalcBaseForm()))
        {
            //�P���@@=�@@(6-2)�Ŏ擾���������~(�����̎�n����v�Z����.�o�ߓ����^�����̎�n����v�Z����.�����)
            l_bdPriceWork = 
                l_bdCoupon.multiply(
                    l_bdElapsedDays.divide(
                        l_bdCalcBaseDays, 10, BigDecimal.ROUND_HALF_UP));
            l_bdPrice = 
                getCutOutValueForAccruedInterest(l_bdPriceWork, l_intUnitPriceScale, l_strUnitRoundDiv);
            
            //�o�ߗ��q�@@=�@@(�Z�o�����P���^100)�~�����̎�n����v�Z����.����
            l_bdAccruedInterestWork = 
                l_bdPrice.divide(
                    l_bdHundred, 10, BigDecimal.ROUND_HALF_UP).multiply(
                        l_bdQuantity);
            l_bdAccruedInterest = 
                getCutOutValueForAccruedInterest(l_bdAccruedInterestWork, l_intPriceScale, l_strUnitRoundDiv);
        }
        
        //(8)   �o�ߗ��qMAX�`�F�b�N
        //�@@�t���[�e�B���O���[�g�i�����̍�����.��ʃR�[�h�@@!=�@@�g�O�t���[�e�B���O���[�g�h)
        //�@@&& (6-2)�Ŏ擾�������񗘕����@@<�@@(6-1)�Ŏ擾��������@@<�@@(6-2)�Ŏ擾�����ŏI������
        // &&�@@�����̍�����.�N�ԗ����񐔁@@!=�@@�g�s��h�̏ꍇ
        int l_intYearlyInterestPayments = 
            l_bondProduct.getYearlyInterestPayments();
        BigDecimal l_bdYearlyInterestPayments = 
            new BigDecimal(String.valueOf(l_intYearlyInterestPayments));
        BigDecimal l_bdOne = new BigDecimal("1");
        
        boolean l_blnFlag = true;
        if (l_interestPaymentDay.getFirstInterestPaymentDay() != null
            && l_interestPaymentDay.getFinalInterestPaymentDay() != null)
        {
            if (!(WEB3DateUtility.compareToDay(
                    l_interestPaymentDay.getFirstInterestPaymentDay(), l_datBaseDate) < 0 
                && WEB3DateUtility.compareToDay(
                    l_datBaseDate, l_interestPaymentDay.getFinalInterestPaymentDay()) < 0))
            {
                l_blnFlag = false;
            }
        }
        if (!WEB3BondCategCodeDef.FLOATING_RATE_BOND.equals(l_bondProduct.getBondCategCode()) 
            && l_blnFlag
            && l_intYearlyInterestPayments != Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME))
        {
            BigDecimal l_bdAccruedInterestMax = null;
            BigDecimal l_bdAccruedInterestMaxWork = null;
            
            //(8-1)�@@���z�D������@@((5)�Ŏ擾�����o�ߗ��q�v�Z�����̌v�Z��敪��"���z�D�����"�̏ꍇ)
            if (WEB3CalcBaseFormDef.AMOUNT_PRIORITY_FORM.equals(
                l_accruedInterestCalcCondition.getCalcBaseForm()))
            {
                //�����̍�����.�N�ԗ����񐔂�ZERO�̏ꍇ
                if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT))
                {
                    //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                    //�����̎�n����v�Z����.�x���敪�ꗗ��"�o�ߗ��q�v�Z�s�\"��ǉ����Areturn�B
                    l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.addWarningDiv(
                        WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                
                //�o�ߗ��qMAX�@@=�@@ �����̎�n����v�Z����.���ʁ@@�~�@@
                //(�@@(6-2)�Ŏ擾���������@@�^�@@100)�@@�~�@@
                //(�@@1�@@�^�@@�����̍�����.�N�ԗ�����(*��1)�@@)
                l_bdAccruedInterestMaxWork = 
                    l_bdQuantity.multiply(
                        l_bdCoupon.divide(l_bdHundred, 10, BigDecimal.ROUND_HALF_UP)).multiply(
                            l_bdOne.divide(
                                l_bdYearlyInterestPayments, 10, BigDecimal.ROUND_HALF_UP));
                l_bdAccruedInterestMax = 
                    getCutOutValueForAccruedInterest(l_bdAccruedInterestMaxWork, l_intPriceScale, l_strAmountRoundDiv);
            }
            //(8-2)�@@�P���D������@@((5)�Ŏ擾�����o�ߗ��q�v�Z�����̌v�Z��敪��"�P���D�����"�̏ꍇ)
            else if (WEB3CalcBaseFormDef.UNIT_PRICE_PRIORITY_FORM.equals(
                l_accruedInterestCalcCondition.getCalcBaseForm()))
            {
                //�����̍�����.�N�ԗ����񐔂�ZERO�̏ꍇ
                if (l_intYearlyInterestPayments == 
                    Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT))
                {
                    //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                    //�����̎�n����v�Z����.�x���敪�ꗗ��"�o�ߗ��q�v�Z�s�\"��ǉ����Areturn�B
                    l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.addWarningDiv(
                        WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                
                //�P���@@=�@@(6-2)�Ŏ擾���������@@�~�@@(1�^�����̍�����.�N�ԗ�����(*��1))
                l_bdPriceWork = 
                    l_bdCoupon.multiply(
                        l_bdOne.divide(
                            l_bdYearlyInterestPayments, 10, BigDecimal.ROUND_HALF_UP));
                l_bdPrice = 
                    getCutOutValueForAccruedInterest(l_bdPriceWork, l_intUnitPriceScale, l_strUnitRoundDiv);
                
                //�o�ߗ��qMAX�@@=�@@(�Z�o�����P���^100)�~�����̎�n����v�Z����.����
                l_bdAccruedInterestMaxWork = 
                    l_bdPrice.divide(
                        l_bdHundred, 10, BigDecimal.ROUND_HALF_UP).multiply(
                            l_bdQuantity);
                l_bdAccruedInterestMax = 
                    getCutOutValueForAccruedInterest(l_bdAccruedInterestMaxWork, l_intPriceScale, l_strUnitRoundDiv);
            }
            
            //(8-3)�@@�o�ߗ��qMAX�`�F�b�N
            //     (7)�ŎZ�o�����o�ߗ��q�@@>�@@�Z�o�����o�ߗ��qMAX�@@�̏ꍇ�A�@@
            if (l_bdAccruedInterest.compareTo(l_bdAccruedInterestMax) > 0)
            {
                //�o�ߗ��q�@@=�@@�Z�o�����o�ߗ��qMAX
                l_bdAccruedInterest = l_bdAccruedInterestMax;
            }
        }
        
        //(9)�@@�v�Z���ʃZ�b�g
        //(9-1)�@@�����̍�����.is�O�݌�()�@@==�@@true�@@�̏ꍇ
        if (l_bondProduct.isForeignCurrency())
        {
            //�����̎�n����v�Z����.�o�ߗ��q(�O��)�@@=�@@�Z�o�����o�ߗ��q
            l_bondPriceCalcResult.setForeignAccruedInterest(l_bdAccruedInterest);
            
            //�����̎�n����v�Z����.�o�ߗ��q(�~��)�@@=�@@this.calc�~�݊��Z()�̖߂�l
            
            //(����)�ʉ�
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_bondProduct.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            BigDecimal l_bdJPYAmount =
                this.calcJPYAmount(
                    l_bdAccruedInterest, l_currency, true, true, l_bondPriceCalcResult.getFxRate());
            l_bondPriceCalcResult.setAccruedInterest(l_bdJPYAmount);
        }
        
        //(9-2)�@@�����̍�����.is�O�݌�()�@@==�@@false�@@�̏ꍇ
        else
        {
            //�����̎�n����v�Z����.�o�ߗ��q(�O��)�@@=�@@NULL
            l_bondPriceCalcResult.setForeignAccruedInterest(null);
            
            //�����̎�n����v�Z����.�o�ߗ��q(�~��)�@@=�@@�Z�o�����o�ߗ��q
            l_bondPriceCalcResult.setAccruedInterest(l_bdAccruedInterest);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc���)<BR>
     * �o�ߗ��q�v�Z�K�{�������ڂł��������Z�o����B<BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc<BR>
     * �R�Dcalc���()�@@��Q�ƁB<BR>
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������<BR>
     * @@param l_accruedInterestCalcCondition - (�o�ߗ��q�v�Z����)<BR>
     * �o�ߗ��q�v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_bondPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����<BR>
     * @@return Date
     * @@throws WEB3BaseException 
     * @@roseuid 44F25934006D
     */
    protected Date calcBaseDate(
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo,
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition, 
        WEB3BondProduct l_bondProduct,
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcBaseDate("
            + "WEB3BondExecuteDateInfo, WEB3BondAccruedInterestCalcCondition, "
            + "WEB3BondProduct, WEB3BondEstimatedPriceCalcResult)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondExecuteDateInfo == null || l_bondProduct == null 
            || l_accruedInterestCalcCondition == null || l_bondPriceCalcResult == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(1)�@@������Z�o����B
        //(1-1)�@@�o�ߗ��q�v�Z����.����敪�@@==�@@"��n��"�@@�̏ꍇ�@@ 
        String l_strBaseDateDiv = 
            l_accruedInterestCalcCondition.getBaseDateDiv();
        Date l_datBaseDate = null;
        if (WEB3BaseDateDivDef.DELIVERY_DATE.equals(l_strBaseDateDiv))
        {
            //����@@=�@@�����̍��������.���n��n��
            l_datBaseDate = l_bondExecuteDateInfo.getForeignDeliveryDate();
        }
        
        //(1-2)�@@�o�ߗ��q�v�Z����.����敪�@@==�@@"����"�@@�̏ꍇ
        else if (WEB3BaseDateDivDef.EXECUTE_DATE.equals(l_strBaseDateDiv))
        {
            //����@@=�@@�����̍��������.���n����
            l_datBaseDate = l_bondExecuteDateInfo.getForeignExecuteDate();
        }
        
        //(2)�@@���t�`�F�b�N���s�Ȃ��B
        //
        //[�������]
        //�E(1)�ŎZ�o��������@@<�@@�����̍�����.���s���@@�̏ꍇ  
        //�E(1)�ŎZ�o��������@@<�@@�����̍�����.�o�ߗ��q�N�Z���@@�̏ꍇ
        //�E(1)�ŎZ�o��������@@���@@�����̍�����.���ғ��@@�̏ꍇ
        //
        BondProductRow l_bondProductRow = (BondProductRow)l_bondProduct.getDataSourceObject();
        if (WEB3DateUtility.compareToDay(l_datBaseDate, l_bondProductRow.getIssueDate()) < 0
            || WEB3DateUtility.compareToDay(l_datBaseDate, l_bondProduct.getAccruedInterestStartDay()) < 0
            || WEB3DateUtility.compareToDay(l_datBaseDate, l_bondProductRow.getMaturityDate()) >= 0)
        {
            //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
            //�����̎�n����v�Z����.�x���敪�ꗗ��"�o�ߗ��q�v�Z�s�\"��ǉ����Areturn
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.addWarningDiv(
                WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
            return null;
        }
        
        //(3)�@@(1)�ŎZ�o���������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_datBaseDate;
    }
    
    /**
     * (calc������)<BR>
     * �o�ߗ��q�v�Z�K�{�������ڂł��闘�������Z�o����B<BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc<BR>
     * �S�Dcalc������()�@@��Q�ƁB<BR>
     * @@param l_bondPriceCalcResult - (��n����v�Z����)<BR>
     * ��n����v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
     * @@return InterestPaymentDay
     * @@throws WEB3BaseException 
     * @@roseuid 44F259F00251
     */
    protected WEB3BondInterestPaymentDay calcInterestPaymentDay(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        Date l_datBaseDate) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcInterestPaymentDay("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, Date) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_bondPriceCalcResult == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(0) �������N���X�̃C���X�^���X�𐶐�����B
        WEB3BondInterestPaymentDay l_interestPaymentDay = 
            new WEB3BondInterestPaymentDay();
        
        //(1)�@@�����̍�����.���t�^�C�v�@@==�@@�g�Œ藘�t�h�@@�̏ꍇ
        if (CouponTypeEnum.COUPON.equals(l_bondProduct.getCouponType()))
        {
            //(1-1)�@@�������`�F�b�N
            String l_strZeroForMonthDay = "0000";
            String l_strZeroForMonth = "00";
            String l_strZeroForDay = "00";
            String l_strInterestPaymentDay1 = 
                l_bondProduct.getInterestPaymentDay1();
            String l_strInterestPaymentDay2 = 
                l_bondProduct.getInterestPaymentDay2();
            
            if (l_strZeroForMonthDay.equals(l_strInterestPaymentDay1)
                || (l_strZeroForMonth.equals(l_strInterestPaymentDay1.substring(0, 2))
                    && !l_strZeroForDay.equals(l_strInterestPaymentDay1.substring(2)))
                || (l_strZeroForMonth.equals(l_strInterestPaymentDay2.substring(0, 2))
                    && !l_strZeroForDay.equals(l_strInterestPaymentDay2.substring(2)))
                || (!l_strZeroForMonth.equals(l_strInterestPaymentDay1.substring(0, 2))
                    && l_strZeroForDay.equals(l_strInterestPaymentDay1.substring(2)))
                || (!l_strZeroForMonth.equals(l_strInterestPaymentDay2.substring(0, 2))
                    && l_strZeroForDay.equals(l_strInterestPaymentDay2.substring(2))))
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�v�Z�s�\�h��ǉ����Areturn�B
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //(1-2)�@@����(���P��)���Z�o����B
            //�����@@=�@@�@@12�@@�^�@@�����̍�����.�N�ԗ�����
            //�E�����̍�����.�N�ԗ����񐔁@@==�@@ZERO�@@�̏ꍇ
            //�E12�@@���@@(�����̍�����.�N�ԗ�����)�@@!=�@@ZERO�̏ꍇ
            int l_intYearlyInterestPayments = l_bondProduct.getYearlyInterestPayments();
            if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT))
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�v�Z�s�\�h��ǉ����Areturn�B
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            BigDecimal l_bdtwelve = new BigDecimal("12");
            BigDecimal l_bdYearlyInterestPayments = 
                new BigDecimal(String.valueOf(l_intYearlyInterestPayments));
            BigDecimal l_bdPeriod = 
                l_bdtwelve.divide(l_bdYearlyInterestPayments, 0, BigDecimal.ROUND_DOWN);
            if (l_bdtwelve.compareTo(l_bdPeriod.multiply(l_bdYearlyInterestPayments)) != 0)
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�v�Z�s�\�h��ǉ����Areturn�B
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //(1-3)�@@���񗘕������Z�o����B
            //�o�ߗ��q�N�Z��
            Date l_datAccruedInterestStartDay = 
                l_bondProduct.getAccruedInterestStartDay();
            String l_strAccruedInterestStartDay = 
                WEB3DateUtility.formatDate(l_datAccruedInterestStartDay, "yyyyMMdd");
            
            //(1-3-1)�@@���񗘕����@@=�@@�����̍�����.�o�ߗ��q�N�Z���̔N(YYYY)�@@
            //                      +�@@�����̍�����.�������P(MMDD)
            String l_strFirstInterestPaymentDay = 
                l_strAccruedInterestStartDay.substring(0, 4) + l_strInterestPaymentDay1;
            
            //(1-3-2)�@@���t�������s�Ȃ��B(this.calc���t����())
            String l_strFirstInterestPaymentDayAdjusted = 
                this.calcDateAdjustment(l_strFirstInterestPaymentDay, l_bondProduct);
            Date l_datFirstInterestPaymentDayAdjusted = 
                WEB3DateUtility.getDate(l_strFirstInterestPaymentDayAdjusted, "yyyyMMdd");
            //(1-3-3)�@@�����̍�����.�o�ߗ��q�N�Z�� (YYYYMMDD)�@@���@@���񗘕����@@�̏ꍇ
            while (WEB3DateUtility.compareToDay(
                l_datAccruedInterestStartDay, 
                l_datFirstInterestPaymentDayAdjusted) >= 0)
            {
                log.debug("�o�ߗ��q�N�Z�������񗘕����̏ꍇ ���������Z����B");
                l_datFirstInterestPaymentDayAdjusted = 
                    getAdjustedDate(
                        l_datFirstInterestPaymentDayAdjusted,
                        l_bdPeriod.intValue(),
                        l_bondProduct);
            }
            
            //(1-3-4)�@@�Z�o�������񗘕����𗘕���.���񗘕����ɃZ�b�g����B
            l_interestPaymentDay.setFirstInterestPaymentDay(l_datFirstInterestPaymentDayAdjusted);
            
            //(1-4)�@@�ŏI���������Z�o����B
            //(1-4-1)�@@�ŏI�������@@=�@@(�@@�����̍�����.���ғ��̔N(YYYY)�@@+�@@
            //�����̍�����.�������P(MMDD)�@@)��1�N���Z����(ex1)���t�B
            //���ғ�
            Date l_datMaturityDate = l_bondProduct.getMaturityDate();
            String l_strMaturityDate = 
                WEB3DateUtility.formatDate(l_datMaturityDate, "yyyyMMdd");
            
            int l_intMaturityDateYear = 
                Integer.parseInt(l_strMaturityDate.substring(0, 4));
            String l_strFinalInterestPaymentDay = 
                l_intMaturityDateYear + 1 + l_strInterestPaymentDay1;
            
            //(1-4-2)�@@���t�������s�Ȃ��B(this.calc���t����())
            String l_strFinalInterestPaymentDayAdjusted = 
                this.calcDateAdjustment(
                    l_strFinalInterestPaymentDay, 
                    l_bondProduct);
            Date l_datFinalInterestPaymentDayAdjusted = 
                WEB3DateUtility.getDate(l_strFinalInterestPaymentDayAdjusted, "yyyyMMdd");
            
            //(1-4-3)�@@�����̍�����.���ғ� (YYYYMMDD)�@@���@@�ŏI�������@@�̏ꍇ
            while (WEB3DateUtility.compareToDay(
                l_datMaturityDate, l_datFinalInterestPaymentDayAdjusted) <= 0)
            {
                log.debug("���ғ����ŏI�������̏ꍇ ���������Z����B");
                l_datFinalInterestPaymentDayAdjusted = 
                    getAdjustedDate(
                        l_datFinalInterestPaymentDayAdjusted,
                        -l_bdPeriod.intValue(),
                        l_bondProduct);
            }
            
            //(1-4-4)�@@�Z�o�����ŏI�������𗘕���.�ŏI�������ɃZ�b�g����B
            l_interestPaymentDay.setFinalInterestPaymentDay(l_datFinalInterestPaymentDayAdjusted);
            
            //(1-5)�@@�O�񗘕���/���񗘕������Z�o����B
            //(1-5-1)�@@�����̊�� (YYYYMMDD)�@@<�@@(1-3)�ŎZ�o�������񗘕����@@�̏ꍇ
            if (WEB3DateUtility.compareToDay(l_datBaseDate, l_datFirstInterestPaymentDayAdjusted) < 0)
            {
                //�O�񗘕����@@=�@@�@@�����̍�����.�o�ߗ��q�N�Z��(YYYYMMDD)
                l_interestPaymentDay.setPreInterestPaymentDay(l_datAccruedInterestStartDay);
                
                //���񗘕����@@=�@@�@@(1-3)�ŎZ�o�������񗘕���
                l_interestPaymentDay.setNextInterestPaymentDay(l_datFirstInterestPaymentDayAdjusted);
            }
            
            //(1-5-2)   �����̊�� (YYYYMMDD)�@@<�@@(1-4)�ŎZ�o�����ŏI�������@@�̏ꍇ
            else if (WEB3DateUtility.compareToDay(l_datBaseDate, l_datFinalInterestPaymentDayAdjusted) < 0)
            {
                //(1-5-2-1)�@@������work�@@=�@@�@@
                //�����̊���̔N(YYYY)�@@+�@@�����̍�����.�������P(MMDD)
                String l_strBaseDate = WEB3DateUtility.formatDate(l_datBaseDate, "yyyyMMdd");
                String l_strInterestPaymentDayWork = 
                    l_strBaseDate.substring(0, 4) + l_strInterestPaymentDay1;
                
                //(1-5-2-2)�@@���t�������s�Ȃ��B(this.calc���t����())
                String l_strInterestPaymentDayWorkAdjusted = 
                    this.calcDateAdjustment(
                        l_strInterestPaymentDayWork, 
                        l_bondProduct);
                Date l_datInterestPaymentDayWorkAdjusted = 
                    WEB3DateUtility.getDate(l_strInterestPaymentDayWorkAdjusted, "yyyyMMdd");
                //(1-5-2-3)�@@�����̊���@@���@@������work�@@�̏ꍇ
                while (WEB3DateUtility.compareToDay(
                    l_datBaseDate, l_datInterestPaymentDayWorkAdjusted) >= 0)
                {
                    l_datInterestPaymentDayWorkAdjusted = 
                        getAdjustedDate(
                            l_datInterestPaymentDayWorkAdjusted,
                            l_bdPeriod.intValue(),
                            l_bondProduct);
                }
                
                //(1-5-2-4)�@@���񗘕����̃Z�b�g
                l_interestPaymentDay.setNextInterestPaymentDay(l_datInterestPaymentDayWorkAdjusted);
                
                //(1-5-2-5)�@@�����̊���@@>�@@������work�@@�ƂȂ�܂ňȉ��������J�Ԃ��B
                while (WEB3DateUtility.compareToDay(
                        l_datBaseDate, l_datInterestPaymentDayWorkAdjusted) < 0)
                {
                    l_datInterestPaymentDayWorkAdjusted = 
                        getAdjustedDate(
                            l_datInterestPaymentDayWorkAdjusted,
                            -l_bdPeriod.intValue(),
                            l_bondProduct);
                }
                
                //(1-5-2-6)�@@�O�񗘕����@@=�@@������work
                l_interestPaymentDay.setPreInterestPaymentDay(l_datInterestPaymentDayWorkAdjusted);
            }
            
            //(1-5-3)�@@(1-4)�ŎZ�o�����ŏI�������@@���@@�����̊���@@�̏ꍇ
            else
            {
                //�O�񗘕����@@=�@@�@@(1-4)�ŎZ�o�����ŏI������
                l_interestPaymentDay.setPreInterestPaymentDay(l_datFinalInterestPaymentDayAdjusted);
                
                //���񗘕����@@=�@@�@@�����̍�����.���ғ� (YYYYMMDD)
                l_interestPaymentDay.setNextInterestPaymentDay(l_datMaturityDate);
            }
            
            //(1-6)�@@�������Z�b�g����B
            //   �����@@=�@@�����̍�����.����
            l_interestPaymentDay.setCoupon(
                new BigDecimal(String.valueOf(l_bondProduct.getCoupon())));
        }
        
        //(2)�@@�����̍�����.���t�^�C�v�@@==�@@�g�ϓ����t�h�@@�̏ꍇ
        else if (CouponTypeEnum.FLOATING_COUPON.equals(l_bondProduct.getCouponType()))
        {
            //(2-1)�@@�����������e�[�u�����烌�R�[�h���擾����B
            String l_strWhere = "product_id = ? ";
            Object[] l_objWhereValues = {new Long(l_bondProduct.getProductId())};
            String l_strOrderBy = "interest_payment_day asc";
            List l_lisRecords = null;
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_lisRecords = l_processor.doFindAllQuery(
                    BondProductCouponRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_objWhereValues);
            }
            catch (DataQueryException l_dqex)
            {
                log.error(STR_METHOD_NAME, l_dqex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error(STR_METHOD_NAME, l_dnex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            //�E�f�[�^���擾�o���Ȃ��ꍇ
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�v�Z�s�\�h��ǉ����Areturn�B
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //�E����������[n].�������@@���@@�����̊���̏ꍇ
            int l_intLength = l_lisRecords.size();
            BondProductCouponRow l_bondProductCouponEndRow = 
                (BondProductCouponRow)l_lisRecords.get(l_intLength - 1);
            if (WEB3DateUtility.compareToDay(
                l_bondProductCouponEndRow.getInterestPaymentDay(), 
                l_datBaseDate) <= 0)
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�v�Z�s�\�h��ǉ����Areturn�B
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            
            //�����̍�����.�o�ߗ��q�N�Z��(YYYYMMDD)
            Date l_datAccruedInterestStartDay = 
                l_bondProduct.getAccruedInterestStartDay();
            Date l_datInterestPaymentDay = null;
            int l_intIndex = 0;
            for (l_intIndex = 0; l_intIndex < l_intLength; l_intIndex++)
            {
                BondProductCouponRow l_bondProductCouponRow = 
                    (BondProductCouponRow)l_lisRecords.get(l_intIndex);
                l_datInterestPaymentDay = 
                    l_bondProductCouponRow.getInterestPaymentDay();
                
                //(2-2)�@@�O�񗘕���/���񗘕������Z�o����B
                //(2-2-1)�@@�����̍�����.�o�ߗ��q�N�Z��(YYYYMMDD)�@@���@@
                //         ����������[0].�������@@�̏ꍇ
                if (WEB3DateUtility.compareToDay(
                    l_datAccruedInterestStartDay,
                    l_datInterestPaymentDay) < 0)
                {
                    break;
                }
            }  
            
            BondProductCouponRow l_bondProductCouponRow = 
                (BondProductCouponRow)l_lisRecords.get(l_intIndex);
            l_datInterestPaymentDay = 
                l_bondProductCouponRow.getInterestPaymentDay();
            
            //(2-2-2)�@@�����̊���@@<�@@����������[i].�������@@�̏ꍇ
            if (WEB3DateUtility.compareToDay(l_datBaseDate, l_datInterestPaymentDay) < 0)
            {
                //�����̑O�񗘕����@@=�@@�@@�����̍�����.�o�ߗ��q�N�Z��(YYYYMMDD)
                l_interestPaymentDay.setPreInterestPaymentDay(l_datAccruedInterestStartDay);
               
                //�����̎��񗘕����@@=�@@�@@����������[i].������
                l_interestPaymentDay.setNextInterestPaymentDay(l_datInterestPaymentDay);
            }
            
            //(2-2-3)�@@����������[i].�������@@���@@�����̊���@@�̏ꍇ
            else
            {
                //(2-2-3-1)�@@����������[i].�������@@>�@@�����̊���@@�ƂȂ�܂ňȉ��������J�Ԃ��B
                while (WEB3DateUtility.compareToDay(l_datBaseDate, l_datInterestPaymentDay) >= 0)
                {
                    l_intIndex++;
                    l_bondProductCouponRow = 
                        (BondProductCouponRow)l_lisRecords.get(l_intIndex);
                    l_datInterestPaymentDay = 
                        l_bondProductCouponRow.getInterestPaymentDay();
                }
                
                //(2-2-3-2)�@@�O�񗘕���/���񗘕������Z�b�g����B
                //�O�񗘕����@@=�@@�@@����������[i-1].������
                BondProductCouponRow l_bondProductCouponRowPre = 
                    (BondProductCouponRow)l_lisRecords.get(l_intIndex - 1);
                l_interestPaymentDay.setPreInterestPaymentDay(
                        l_bondProductCouponRowPre.getInterestPaymentDay());
                
                //���񗘕����@@=�@@�@@����������[i].������
                l_interestPaymentDay.setNextInterestPaymentDay(l_datInterestPaymentDay);
                 
            }
            
            //(2-3)�@@�������Z�b�g����B
            //�����̗����@@=�@@����������[i].�����@@
            l_interestPaymentDay.setCoupon(
                new BigDecimal(String.valueOf(l_bondProductCouponRow.getCoupon())));
            
            //������ZERO�̏ꍇ
            if (l_interestPaymentDay.getCoupon().doubleValue() == 0D)
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�v�Z�s�\�h��ǉ����Areturn�B
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                return null;
            }
        }
        
        //(3) �������N���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_interestPaymentDay;
    }
    
    /**
     * (calc�����)<BR>
     * �o�ߗ��q�v�Z�K�{�������ڂł����������Z�o����B<BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc<BR>
     * �T�Dcalc�����()�@@��Q�ƁB<BR>
     * @@param l_bondPriceCalcResult - (��n����v�Z����)<BR>
     * ��n����v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_accruedInterestCalcCondition - (�o�ߗ��q�v�Z����)<BR>
     * �o�ߗ��q�v�Z����<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
     * @@param l_datPreInterestPaymentDay - (�O�񗘕���)<BR>
     * �O�񗘕���<BR>
     * @@param l_datNextInterestPaymentDay - (���񗘕���)<BR>
     * ���񗘕���<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F25AD901D4
     */
    protected void calcBaseDays(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition, 
        Date l_datBaseDate, 
        Date l_datPreInterestPaymentDay, 
        Date l_datNextInterestPaymentDay) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcBaseDays("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, " 
            + "WEB3BondAccruedInterestCalcCondition, Date, Date, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_accruedInterestCalcCondition == null
            || l_bondPriceCalcResult == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(1)�@@�o�ߗ��q�v�Z����.������敪�@@==�@@"�����360��"�@@�̏ꍇ�A
        int l_intBaseDays = 0;
        if (WEB3BaseDaysDivDef.BASE_DAYS_360.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            l_intBaseDays = 360;
        }
        
        //(2)   �o�ߗ��q�v�Z����.������敪�@@==�@@"�����365��"�@@�̏ꍇ�A
        else if (WEB3BaseDaysDivDef.BASE_DAYS_365.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            l_intBaseDays = 365;
        }
        //(3)�@@�o�ߗ��q�v�Z����.������敪�@@==�@@"�[�N�l��"�@@�̏ꍇ
        else if (WEB3BaseDaysDivDef.LEAP_YEAR_CONSIDERATION.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            //(3-1)�@@�������Ԃɉ[��������ꍇ
            //((�����̑O�񗘕����@@<�@@2�������@@���@@�����̎��񗘕���)�@@and�@@
            //(2�������@@==�@@29��(�[�N)))
            //������@@=�@@366
            l_intBaseDays = 365;
            Date l_datFebEnd = getFebEndDate(l_datPreInterestPaymentDay);
            String l_strFebEnd = WEB3DateUtility.formatDate(l_datFebEnd, "yyyyMMdd");
            int l_intFebEndDay = Integer.parseInt(l_strFebEnd.substring(6));
            if (WEB3DateUtility.compareToDay(l_datPreInterestPaymentDay, l_datFebEnd) < 0
                && WEB3DateUtility.compareToDay(l_datFebEnd, l_datNextInterestPaymentDay) <= 0
                && l_intFebEndDay == 29)
            {
                l_intBaseDays = 366;
            }
            else
            {
                l_datFebEnd = getFebEndDate(l_datNextInterestPaymentDay);
                l_strFebEnd = WEB3DateUtility.formatDate(l_datFebEnd, "yyyyMMdd");
                l_intFebEndDay = Integer.parseInt(l_strFebEnd.substring(6));
                if (WEB3DateUtility.compareToDay(l_datPreInterestPaymentDay, l_datFebEnd) < 0
                    && WEB3DateUtility.compareToDay(l_datFebEnd, l_datNextInterestPaymentDay) <= 0
                    && l_intFebEndDay == 29)
                {
                    l_intBaseDays = 366;
                }
            }
        }
        
        //(4)�@@�o�ߗ��q�v�Z����.������敪�@@==�@@"�������ԍl��"�@@�̏ꍇ
        else if (WEB3BaseDaysDivDef.PAYMENT_PERIOD_CONSIDERATION.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            //(4-1) �@@(�o�ߗ��q�v�Z����.�o�ߗ��q�v�Z�^�C�v�@@==�@@"�I�[�X�g�����A����")�@@and
            //(�����̑O�񗘕����@@==�@@�����̍�����.�o�ߗ��q�N�Z��)�̏ꍇ
            if (WEB3AccruedInterestCalcTypeDef.AUSTRALIA_FORM.equals(
                    l_accruedInterestCalcCondition.getAccruedInterestCalcType())
                && WEB3DateUtility.compareToDay(
                    l_datPreInterestPaymentDay, 
                    l_bondProduct.getAccruedInterestStartDay()) == 0)
            {
                //������@@=�@@365
                l_intBaseDays = 365;
            }
            //(4-2) (4-1)�@@�ȊO�̏ꍇ
            else 
            {
                //(*1)�����̍�����.�N�ԗ����񐔂�ZERO�@@�܂���"�s��"�̏ꍇ  
                int l_intYearlyInterestPayments = l_bondProduct.getYearlyInterestPayments();
                if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT) 
                    || l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME))
                {
                    //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                    //�����̎�n����v�Z����.�x���敪�ꗗ��"�o�ߗ��q�v�Z�s�\"��ǉ����Areturn
                    l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.addWarningDiv(
                        WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                
                //������@@=�@@(�����̎��񗘕����@@-�@@�����̑O�񗘕���) �~�@@�����̍�����.�N�ԗ�����(*1)
                int l_intDays = 
                    getDaysNum(l_datNextInterestPaymentDay, l_datPreInterestPaymentDay);
                l_intBaseDays = l_intDays * l_intYearlyInterestPayments;
            }
        }
        //(5)�@@�o�ߗ��q�v�Z����.������敪�@@==�@@"�����񐔍l��"�@@�̏ꍇ
        else if (WEB3BaseDaysDivDef.PAYMENT_FREQUENCY_CONSIDERATION.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            //(*1)�����̍�����.�N�ԗ����񐔂�ZERO�@@�܂���"�s��"�̏ꍇ  
            int l_intYearlyInterestPayments = l_bondProduct.getYearlyInterestPayments();
            if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT) 
                || l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME))
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ��"�o�ߗ��q�v�Z�s�\"��ǉ����Areturn
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            BigDecimal l_bdYearlyInterestPayments = 
                new BigDecimal(String.valueOf(l_intYearlyInterestPayments));
            
            //������@@=�@@360�@@�^�@@�����̍�����.�N�ԗ�����(*1)
            BigDecimal l_bdDaysForYear = new BigDecimal("360");
            BigDecimal l_bdBaseDays = 
                l_bdDaysForYear.divide(
                    l_bdYearlyInterestPayments, 0, BigDecimal.ROUND_DOWN);
            
            //�@@�E360�@@%�@@�����̍�����.�N�ԗ����񐔁@@!=�@@ZERO�̏ꍇ
            if (l_bdDaysForYear.compareTo(l_bdYearlyInterestPayments.multiply(l_bdBaseDays)) != 0)
            {
                //�����̎�n����v�Z����.�o�ߗ��q(�O��)�A�o�ߗ��q(�~��)��ZERO�A
                //�����̎�n����v�Z����.�x���敪�ꗗ�Ɂg�o�ߗ��q�v�Z�s�\�h��ǉ����Areturn�B
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return;
            }
            l_intBaseDays = l_bdBaseDays.intValue();
        }
       
        //(6)�@@�o�ߗ��q�v�Z����.������敪�@@==�@@"����[�N"�@@�̏ꍇ
        else if (WEB3BaseDaysDivDef.BASE_DATE_LEAP_YEAR.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            String l_strBaseDateYear = 
                WEB3DateUtility.formatDate(l_datBaseDate, "yyyyMMdd").substring(0, 4);
            GregorianCalendar l_gregorianCalendar = new GregorianCalendar();
            if (l_gregorianCalendar.isLeapYear(Integer.parseInt(l_strBaseDateYear)))
            {
                //(6-1)�@@������[�N�̏ꍇ�A������@@=�@@366
                l_intBaseDays = 366;
            }
            else
            {
                //(6-2)�@@������[�N�łȂ��ꍇ�A������@@=�@@365
                l_intBaseDays = 365;
            }
        }
        
        //(7)�@@�Z�o����������������̎�n����v�Z����.������ɃZ�b�g����B
        l_bondPriceCalcResult.setCalcBaseDays(new Integer(l_intBaseDays));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�o�ߓ���)<BR>
     * �o�ߗ��q�v�Z�K�{�������ڂł���o�ߓ������Z�o����B<BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc<BR>
     * �U�Dcalc�o�ߓ���()�@@��Q�ƁB<BR>
     * @@param l_bondPriceCalcResult - (��n����v�Z����)<BR>
     * ��n����v�Z����<BR>
     * @@param l_accruedInterestCalcCondition - (�o�ߗ��q�v�Z����)<BR>
     * �o�ߗ��q�v�Z����<BR>
     * @@param l_datBaseDate - (���)<BR>
     * ���<BR>
     * @@param l_datPreInterestPaymentDay - (�O�񗘕���)<BR>
     * �O�񗘕���<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F25B35004E
     */
    protected void calcElapsedDays(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult,
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition, 
        Date l_datBaseDate, 
        Date l_datPreInterestPaymentDay) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcElapsedDays("
            + "WEB3BondEstimatedPriceCalcResult, " 
            + "WEB3BondAccruedInterestCalcCondition, Date, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null || l_accruedInterestCalcCondition == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(1)�@@�o�ߓ������Z�o����B
        //(1-1)�@@�o�ߗ��q�v�Z����.�o�ߓ����敪�@@==�@@"����x�[�X�В["�@@�̏ꍇ
        int l_intElapsedDays = 0;
        if (WEB3ElapsedDaysDivDef.CALENDAR_BASE_END.equals(
            l_accruedInterestCalcCondition.getElapsedDaysDiv()))
        {
            //�o�ߓ����@@=�@@(�����̊���@@-�@@�����̑O�񗘕���)
            l_intElapsedDays = 
                getDaysNum(l_datBaseDate, l_datPreInterestPaymentDay);
        }
        
        //(1-2)�@@�o�ߗ��q�v�Z����.�o�ߓ����敪�@@==�@@"����x�[�X���["�@@�̏ꍇ
        else if (WEB3ElapsedDaysDivDef.CALENDAR_BASE_BOTH.equals(
            l_accruedInterestCalcCondition.getElapsedDaysDiv()))
        {
            //�o�ߓ����@@=�@@(�����̊���@@-�@@�����̑O�񗘕���)�@@�{�@@1
            l_intElapsedDays = 
                getDaysNum(l_datBaseDate, l_datPreInterestPaymentDay) + 1;
        }
        
        //(1-3)�@@�o�ߗ��q�v�Z����.�o�ߓ����敪�@@==�@@�@@"�ꃖ��30���В["�@@or�@@
        //"�ꃖ��30�����[MAX30��"�@@or�@@
        //"�ꃖ��30���В[����30��"�@@or�@@
        //"�ꃖ��30���В[MAX30��"�@@�̏ꍇ
        if (WEB3ElapsedDaysDivDef.A_MONTH_30_END.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
            || WEB3ElapsedDaysDivDef.A_MONTH_30_BOTH_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
            || WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
            || WEB3ElapsedDaysDivDef.A_MONTH_30_END_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv()))
        {
            Date l_datFebEnd = this.getFebEndDate(l_datPreInterestPaymentDay);
            
            //(1-3-1)�@@�@@�O�񗘕����̌��̓����̌v�Z
            //�����̑O�񗘕����̌��̑S�̓���
            int l_intPreInterestPaymentMonthDays = 0;
            if (!((WEB3ElapsedDaysDivDef.A_MONTH_30_END.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
                    || WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv()))
                && WEB3DateUtility.compareToDay(l_datPreInterestPaymentDay, l_datFebEnd) == 0))
            {
                l_intPreInterestPaymentMonthDays = 30;
            }
            
            //�@@�O�񗘕����̌��̓����@@=�@@�����̑O�񗘕����̌��̑S�̓����@@-�@@�����̑O�񗘕���(DD)
            String l_strPreInterestPaymentDay = 
                WEB3DateUtility.formatDate(l_datPreInterestPaymentDay, "yyyyMMdd");
            int l_intPreInterestPaymentDayForMonth = 
                Integer.parseInt(l_strPreInterestPaymentDay.substring(6));
            int l_intPreInterestPaymentDays = 
                l_intPreInterestPaymentMonthDays - l_intPreInterestPaymentDayForMonth;
            //(1-3-1-2)�@@�@@�̌v�Z���ʁ@@<�@@ZERO�@@�̏ꍇ�A�@@�@@�@@=�@@ZERO
            if (l_intPreInterestPaymentDays < 0)
            {
                l_intPreInterestPaymentDays = 0;
            }
            
            //(1-3-1-3)�@@�����̊���̔N��(YYYYMM)�@@=�@@�����̑O�񗘕����̔N��(YYYYMM)�@@�̏ꍇ�A�@@�@@�@@=�@@ZERO
            String l_strBaseDate = WEB3DateUtility.formatDate(l_datBaseDate, "yyyyMMdd");
            if (l_strBaseDate.substring(0, 6).compareTo(
                l_strPreInterestPaymentDay.substring(0, 6)) == 0)
            {
                l_intPreInterestPaymentDays = 0;
            }
            
            //(1-3-2)�@@�A�O�񗘕����̎��̌��������̑O���܂ł̓����̌v�Z
            
            //�N�������@@=�@@(�@@�����̊���̔N(YYYY)�@@-�@@�����̑O�񗘕����̔N(YYYY)�@@)�@@�~�@@12
            int l_intBaseDateDayForYear = Integer.parseInt(l_strBaseDate.substring(0, 4));
            int l_intYears = 
                (l_intBaseDateDayForYear
                - Integer.parseInt(l_strPreInterestPaymentDay.substring(0, 4))) * 12;
            
            //�A�O�񗘕����̎��̌��������̑O���܂ł̓����@@=�@@
            //(�����̊���̌�(MM)�@@+�@@�@@�N������(*1)�@@-�@@�����̑O�񗘕����̌�(MM)�@@-�@@1)�@@�~�@@30
            int l_intBaseDateMonth = 
                Integer.parseInt(l_strBaseDate.substring(4, 6));
            int l_intPreInterestPaymentDayMonth = 
                Integer.parseInt(l_strPreInterestPaymentDay.substring(4, 6));
            int l_intDays = 
                (l_intBaseDateMonth + l_intYears - l_intPreInterestPaymentDayMonth - 1) * 30;
            
            //(1-3-2-1)�@@�A�̌v�Z���ʁ@@<�@@ZERO�@@�̏ꍇ�A�@@�A�@@=�@@ZERO
            if (l_intDays < 0)
            {
                l_intDays = 0;
            }
            
            //(1-3-3)�@@�B����̌��̓����̌v�Z���@@
            //�B����̌��̓����@@=�@@�����̊���̓�(DD)
            //(1-3-3-1)�@@(�o�ߗ��q�v�Z����.�o�ߓ����敪�@@==�@@�@@"�ꃖ��30�����[MAX30��"�@@or�@@
            //"�ꃖ��30���В[MAX30��"�@@or
            //"�ꃖ��30���В[����30��"�@@)�@@and 
            //(�����̊���̓�(DD)�@@==�@@31�@@)�@@�̏ꍇ�A
            int l_intBaseDateMonthDays = 0;
            Date l_datFebEndForBaseDate = this.getFebEndDate(l_datBaseDate);
            int l_intBaseDateDayForMonth = Integer.parseInt(l_strBaseDate.substring(6));
            if ((WEB3ElapsedDaysDivDef.A_MONTH_30_BOTH_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
                    || WEB3ElapsedDaysDivDef.A_MONTH_30_END_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
                    || WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv()))
                && l_intBaseDateDayForMonth == 31)
            {
                //�B����̌��̓����@@=�@@30
                l_intBaseDateMonthDays = 30;
            }
            
            //(1-3-3-2)�@@(�o�ߗ��q�v�Z����.�o�ߓ����敪�@@==�@@�@@"�ꃖ��30���В[����30��"�@@)�@@and 
            //(�����̊���@@==�@@2�������@@)�@@�̏ꍇ�A
            else if (WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(
                    l_accruedInterestCalcCondition.getElapsedDaysDiv())
                && WEB3DateUtility.compareToDay(l_datBaseDate, l_datFebEndForBaseDate) == 0)
            {
                l_intBaseDateMonthDays = 30;
            }

            //(1-3-3-3)�@@��L�ȊO�͇B�̓J�����_�[�ǂ���̓����B
            else 
            {
                l_intBaseDateMonthDays = l_intBaseDateDayForMonth;
            }
            
            //(1-3-3-4)�@@�����̊���̔N��(YYYYMM)�@@==�@@�����̑O�񗘕����̔N��(YYYYMM)�@@�̏ꍇ
            //           �B�@@=�@@��L�Őݒ肵���B�̒l�@@?�@@�����̑O�񗘕����̓���(DD)
            if (l_strBaseDate.substring(0, 6).compareTo(l_strPreInterestPaymentDay.substring(0, 6)) == 0)
            {
                l_intBaseDateMonthDays = 
                    l_intBaseDateMonthDays - l_intPreInterestPaymentDayForMonth;
            }
            
            //(1-3-3-5)�@@�B�̌v�Z���ʁ@@<�@@ZERO�@@�̏ꍇ�A�@@�B�@@=�@@ZERO
            if(l_intBaseDateMonthDays < 0)
            {
                l_intBaseDateMonthDays = 0;
            }
            

            //�o�ߓ����@@=�@@�@@�O�񗘕����̌��̓����@@+
            //�A�O�񗘕����̎��̌��������̑O���܂ł̓����@@+
            //�B����̌��̓���
            l_intElapsedDays =
                l_intPreInterestPaymentDays + l_intDays + l_intBaseDateMonthDays;

            //(1-3-4)�@@�o�ߗ��q�v�Z����.�o�ߓ����敪�@@==�@@�@@"�ꃖ��30�����[MAX30��"�@@�̏ꍇ
            if (WEB3ElapsedDaysDivDef.A_MONTH_30_BOTH_MAX_30.equals(
                l_accruedInterestCalcCondition.getElapsedDaysDiv()))
            {
                //�o�ߓ����@@=�@@�Z�o�����o�ߓ����@@+�@@1
                l_intElapsedDays = l_intElapsedDays + 1;
            }
        }
       
        //(2)   �o�ߓ���MAX����
        //   (1)�ŎZ�o�����o�ߓ����@@>�@@�����̎�n����v�Z����.������@@�̏ꍇ�A
        int l_intCalcBaseDays = l_bondPriceCalcResult.getCalcBaseDays().intValue();
        if (l_intElapsedDays > l_intCalcBaseDays)
        {
            //�o�ߓ����@@=�@@�����̎�n����v�Z����.�����
            l_intElapsedDays = l_intCalcBaseDays;
        }
        
        //(3)   �Z�o�����o�ߓ����������̎�n����v�Z����.�o�ߓ����ɃZ�b�g����B
        l_bondPriceCalcResult.setElapsedDays(new Integer(l_intElapsedDays));
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc���t����)<BR>
     * �o�ߗ��q�v�Z�K�{�������ڂł��闘�������Z�o����ׂ̓��t�������s�Ȃ��B<BR>
     * �߂�l��String(���t(YYYYMMDD))�B<BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc<BR>
     * �V�Dcalc���t����()�@@��Q�ƁB<BR>
     * @@param l_strDate - (���t)<BR>
     * ���t(YYYYMMDD)<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@return String
     * @@throws WEB3BaseException 
     * @@roseuid 44F25BA60109
     */
    protected String calcDateAdjustment(String l_strDate, WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcDateAdjustment(String, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strDate == null || l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //(1)�@@���t(YYYYMMDD)�@@=�@@�����̓��t
        String l_strDateRet = l_strDate;
        
        //(2)�@@���t�̓�(DD)�@@!=�@@�����̍�����.�������P�̓�(DD)�̏ꍇ�A
        String l_strDayForInterestPaymentDay1 = 
            l_bondProduct.getInterestPaymentDay1().substring(2);
        String l_strDayForDateRet = l_strDateRet.substring(6);
        String l_strYearMonthForDateRet = l_strDateRet.substring(0, 6);
        if (l_strDayForDateRet.compareTo(l_strDayForInterestPaymentDay1) != 0)
        {
            //�@@�@@�@@���t�̓�(DD)�@@=�@@�����̍�����.�������P�̓�(DD)
            l_strDayForDateRet = l_strDayForInterestPaymentDay1;
        }
        
        Calendar l_calendar = Calendar.getInstance();
        Date l_datRet = WEB3DateUtility.getDate(l_strYearMonthForDateRet, "yyyyMM");
        l_calendar.setTime(l_datRet);
        int l_intMaxDayOfMonth = l_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //(3)�@@�����̓��t�̓�(DD)�@@==�@@"99"�̏ꍇ�A
        //���t�̓�(DD)�@@=�@@���t�̌�(MM)�@@�̖���
        if ("99".equals(l_strDate.substring(6))) 
        {
            l_strDayForDateRet = l_intMaxDayOfMonth + "";
        }
        
        //(4)�@@�����̓��t�̌�(MM)�@@==�@@�����̍�����.�������Q�̌�(MM)�̏ꍇ�A
        //���t�̓�(DD)�@@=�@@�����̍�����.�������Q�̓�(DD)�B
        String l_strMonthForInterestPaymentDay2 = 
            l_bondProduct.getInterestPaymentDay2().substring(0, 2);
        String l_strDayForInterestPaymentDay2 = 
            l_bondProduct.getInterestPaymentDay2().substring(2);
        String l_strMonth = l_strDate.substring(4, 6);
        if (l_strMonth.compareTo(l_strMonthForInterestPaymentDay2) == 0)
        {
            l_strDayForDateRet = l_strDayForInterestPaymentDay2;
        }
        l_strDateRet = l_strYearMonthForDateRet + l_strDayForDateRet;
        
        //(5)�@@���t(YYYYMMDD)���J�����_�[��ɂȂ��ꍇ(��)
        //���t�̓�(DD)�@@=�@@���t�̌�(MM)�@@�̖���
        if (Integer.parseInt(l_strDayForDateRet) > l_intMaxDayOfMonth) 
        {
            l_strDateRet = l_strYearMonthForDateRet + l_intMaxDayOfMonth;
        }
        
        //(6)�@@���t(YYYYMMDD)��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strDateRet;
    }
    
    /**
     * (calc�o�ߗ��q(������))<BR>
     * �������̏ꍇ�Acalc�o�ߗ��q()����R�[�������B<BR>
     * <BR>
     * �����̍���n����v�Z����.�o�ߗ��q(�O��),�o�ߗ��q(�~��)��ZERO<BR>
     * ���Z�b�g����B<BR>
     * @@param l_bondPriceCalcResult - (����n����v�Z����)<BR>
     * ����n����v�Z����<BR>
     * @@param l_bondProduct - (������)<BR>
     * �������I�u�W�F�N�g<BR>
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F2B0210203
     */
    protected void calcAccruedInterestForDomesticBond(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcAccruedInterestForDomesticBond(" 
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�o�ߗ��q(�~��)
        l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
        
        //�o�ߗ��q(�O��)
        l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get the days number bewteen two date
     * @@param l_date1
     * @@param l_date2
     * @@return int
     */
    private int getDaysNum(Date l_date1, Date l_date2)
    {
        return (int)((l_date1.getTime() - l_date2.getTime())/86400000);
    }
    
    /**
     * ���t�𒲐�����
     * @@param l_datForAdjusted - (����������t)
     * @@param l_intPeriod - (����)
     * @@param l_bondProduct - (������)
     * @@return Date
     * @@throws WEB3BaseException
     */
    private Date getAdjustedDate(
        Date l_datForAdjusted, 
        int l_intPeriod,
        WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        
        //�����i���P�ʁj�����Z����B
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datForAdjusted);
        l_calendar.add(Calendar.MONTH, l_intPeriod);
        l_datForAdjusted = l_calendar.getTime();
        
        String l_strForAdjusted =
            WEB3DateUtility.formatDate(l_datForAdjusted, "yyyyMMdd");
         
        //���t�������s�Ȃ��B(this.calc���t����())
        l_strForAdjusted = 
            this.calcDateAdjustment(
                l_strForAdjusted, 
                l_bondProduct);
         
        return  WEB3DateUtility.getDate(l_strForAdjusted, "yyyyMMdd");
    }
    
    /**
     * get2������
     * @@param l_date ���t
     * @@return�@@Date
     */
    private Date getFebEndDate(Date l_date)
    {
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_date);
        l_calendar.set(Calendar.DAY_OF_MONTH, 1);
        l_calendar.set(Calendar.MONTH, 1);
        int l_intActualMaxDayOfMonth = 
            l_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        l_calendar.set(Calendar.DAY_OF_MONTH, l_intActualMaxDayOfMonth);
        return l_calendar.getTime();
    }
    
    /**
     * �ۂߏ���
     * @@param l_bdValueForCut �ۂ߂����l
     * @@param l_intSacle �L������
     * @@param l_strRoundDiv �ۂߕ���
     * @@return BigDecimal
     */
    private BigDecimal getCutOutValueForAccruedInterest(
        BigDecimal l_bdValueForCut, 
        int l_intSacle, 
        String l_strRoundDiv)
    {
        BigDecimal l_bdResult = null;
        //  1:�؂�̂�
        if (WEB3RoundDivDef.CUTTING_OFF.equals(l_strRoundDiv))
        {
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_DOWN);
        }
        //�@@2:�l�̌ܓ�
        else if (WEB3RoundDivDef.ROUNDING_OFF.equals(l_strRoundDiv))
        {
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_HALF_UP);
        }
        //  3:�؂�グ
        else if (WEB3RoundDivDef.RAISING_TO_A_UNIT.equals(l_strRoundDiv))
        {
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_CEILING);
        }
        //  4:�X�C�X�������
        else if (WEB3RoundDivDef.SWITZERLAND_SPECIALITY_FORM.equals(l_strRoundDiv))
        {
            l_bdValueForCut = l_bdValueForCut.movePointLeft(1);
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_DOWN);
            double l_dblOneFourth = 0.25D;
            double l_dblThreeFourth = 0.75D;
            double l_dblForCut = 
                l_bdValueForCut.subtract(l_bdResult).movePointRight(l_intSacle).doubleValue();
            BigDecimal l_bdForCut = new BigDecimal("0"); 
            if (l_dblForCut > 0D 
                && l_dblForCut <= l_dblOneFourth)
            {
                l_bdForCut = new BigDecimal("0");
            }
            else if (l_dblForCut > l_dblOneFourth
                && l_dblForCut <= l_dblThreeFourth)
            {
                l_bdForCut = new BigDecimal("0.5").movePointLeft(l_intSacle);
            }
            else if (l_dblForCut > l_dblThreeFourth
                && l_dblForCut < 1)
            {
                l_bdForCut = new BigDecimal("1").movePointLeft(l_intSacle);
            }
            l_bdResult = l_bdResult.add(l_bdForCut).movePointRight(1);
        }
        return l_bdResult;
    }    

    /**
     * (calc���񗘎q�����z)<BR>
     * ���񗘎q�����z�v�Z���s���B <BR>
     * �l�������E���厞�̂݌v�Z����B <BR>
     * <BR>
     * ���{�݌v�v�Z�����i���j.doc <BR>
     * �W�Dcalc���񗘎q�����z()�@@��Q�ƁB<BR>
     * <BR>
     * @@param l_calcResult - (��n����v�Z����)<BR>
     * ��n����v�Z����<BR>
     * @@param l_product - (������)<BR>
     * ������<BR>
     * @@param l_executeDateInfo - (���������)<BR>
     * ���������<BR>
     * @@throws WEB3BaseException
     */
    protected void calcInitialInterestAdjustAmount(
        WEB3BondEstimatedPriceCalcResult l_calcResult,
        WEB3BondProduct l_product,
        WEB3BondExecuteDateInfo l_executeDateInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcInitialInterestAdjustAmount("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_calcResult == null || l_product == null || l_executeDateInfo == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //(1) ����ŗ��̎擾
        //�ŗ��e�[�u����荑�����̌��򒥎����̒l���擾����B
        //(1-1) �ŗ��I�u�W�F�N�g�̐���������B
        //�R���X�g���N�^�̈���( ����.������.�،���ЃR�[�h�A
        //                     70: ���������򒥎����A
        //                     ����.���������.get�������j
        String l_strInstitutionCode = l_product.getInstitution().getInstitutionCode();
        Timestamp l_tsBizDate = new Timestamp(l_executeDateInfo.getBizDate().getTime());

        WEB3GentradeTaxRate l_taxTate =
            new WEB3GentradeTaxRate(
                l_strInstitutionCode,
                WEB3DutyTypeDef.DOMESTIC_BOND_WITHHOLDING_TAX,
                l_tsBizDate);

        //  (1-2) ����ŗ� = �ŗ��I�u�W�F�N�g.get�ŗ�()
        //�@@�@@�@@�@@�@@���p�[�Z���g�̒l�Ŏ擾�����B20%�̏ꍇ�A�h20�h�ƂȂ�B
        double l_dblTaxRate = l_taxTate.getTaxRate();

        //(2) �o�ߓ����̎Z�o
        //�����̏��񗘕������Z�o���A���s�����珉�񗘕����̂U�����O�̍����Z�o����B
        // (2-1) this.calc���������R�[�����A�������I�u�W�F�N�g���擾����B
        //  [calc�������ւ̈���]
        //   ��n����v�Z���ʁF����.��n����v�Z����
        //   �������F����.������
        //   ����F����.������.���s��
        WEB3BondInterestPaymentDay l_interesetPaymentDay =
            this.calcInterestPaymentDay(
                l_calcResult,
                l_product,
                l_product.getIssueDate());

        //(2-2) ���񗘕�����6�����O(YYYYMM) = �������I�u�W�F�N�g.get���񗘕���(YYYYMM) - 6����
        Date l_datFirstInterestPaymentDay = l_interesetPaymentDay.getFirstInterestPaymentDay();
        String l_strPaymentDay =
            WEB3DateUtility.formatDate(l_datFirstInterestPaymentDay,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Date l_datBeforeSixMonth =
            WEB3DateUtility.addMonth(
                WEB3DateUtility.getDate(l_strPaymentDay.substring(0, 6),
                    WEB3GentradeTimeDef.DATE_FORMAT_YM), -6);
        String l_strBeforeSixMonth =
            WEB3DateUtility.formatDate(l_datBeforeSixMonth, WEB3GentradeTimeDef.DATE_FORMAT_YM);

        //(2-3) ���񗘕�����6�����O(DD)     = �������I�u�W�F�N�g.get���񗘕���(DD)
        String l_strDay = l_strPaymentDay.substring(6);

        //(2-4) ������̏��񗘕�����6�����O = this.calc���t����()�̖߂�l
        //      [calc���t�����ւ̈���]
        //      ���t : ���񗘕�����6�����O(YYYYMMDD)
        //      ������ �F ����.������
        String l_strChangedDay = this.calcDateAdjustment(
            l_strBeforeSixMonth + l_strDay,
            l_product);

        //(2-5) �o�ߓ��� = ����.������.���s�� - ������̏��񗘕�����6�����O
        Date l_datChangedDay =
            WEB3DateUtility.getDate(l_strChangedDay, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        int l_intDays = this.getDaysNum(l_product.getIssueDate(), l_datChangedDay);

        //(3) �P�ʌo�ߗ��q�̎Z�o(�����_�ȉ�8���ڈȍ~�؎̂�)
        // �P�ʌo�ߗ��q = ����.������.get����() �~
        //  ( 1 - ( (1)�Ŏ擾��������ŗ� �~ 0.01) ) �~ (2)�ŎZ�o�����o�ߓ���  �^ 365
        BigDecimal l_bdResultHundred =
            new BigDecimal(String.valueOf(l_product.getCoupon()));

        BigDecimal l_bdResultTemp =
            (new BigDecimal("1")).subtract(
                (new BigDecimal("0.01")).multiply(
                    new BigDecimal(String.valueOf(l_dblTaxRate))));

        BigDecimal l_dbResultTemMul =
            (l_bdResultHundred.multiply(l_bdResultTemp)).multiply(
                new BigDecimal(l_intDays + ""));

        BigDecimal l_dbInterestAdjust =
            l_dbResultTemMul.divide(
                new BigDecimal("365"), 7, BigDecimal.ROUND_DOWN);

        //(4) ���񗘎q�����z�̎Z�o�i�����_�ȉ��؎̂āj
        // ���񗘎q�����z = �P�ʌo�ߗ��q �~ ����.��n����v�Z����.���� �^ 100
        BigDecimal l_bdPaymentAdjust =
            (l_dbInterestAdjust.multiply(l_calcResult.getQuantity())).divide(
                new BigDecimal("100"), 0, BigDecimal.ROUND_DOWN);

        //(5) ����.��n����v�Z����.�o�ߗ��q(�~��)��(4)�̌v�Z���ʂ��Z�b�g����B
        l_calcResult.setAccruedInterest(l_bdPaymentAdjust);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc��������n���)<BR>
     * calc��������n��� <BR>
     * <BR>
     * �P�j����n����v�Z���ʂ𐶐�����B <BR>
     * �@@����n����v�Z����.���ʁ�����.���� <BR>
     * �@@����n����v�Z����.�P��������.������.���t�P�� <BR>
     * �@@����n����v�Z����.��������ʔ��聁����.��������ʔ��� <BR>
     * <BR>
     * �Q�jthis.calc�������(����n����v�Z����, ������)���Ă� <BR>
     * �@@[����] <BR>
     *    ����n����v�Z���ʁF������������n����v�Z���� <BR>
     * �@@ �������F����.������ <BR>
     * <BR>
     * �R-�P�j������.is�l��������() == true�̏ꍇ <BR>
     *   this.calc���񗘎q�����z�i����n����v�Z����, ������, ���������j���Ă� <BR>
     * �@@  [����] <BR>
     *      ����n����v�Z���ʁF������������n����v�Z���� <BR>
     * �@@   �������F����.������ <BR>
     * �@@   ���������F����.��������� <BR>
     * <BR>
     * �R�|�Q�j ����ȊO�̏ꍇ�A <BR>
     *   this.calc�o�ߗ��q�i����n����v�Z����, ������, ���������j���Ă� <BR>
     * �@@  [����] <BR>
     *      ����n����v�Z���ʁF������������n����v�Z���� <BR>
     * �@@   �������F����.������ <BR>
     * �@@   ���������F����.��������� <BR>
     * <BR>
     * �S�jthis.calc��n���(����n����v�Z����, ������)���Ă� <BR>
     * �@@[����] <BR>
     *    ����n����v�Z���ʁF������������n����v�Z���� <BR>
     * �@@ �������F����.������ <BR>
     * <BR>
     * �T�j����n����v�Z���ʂ�Ԃ�<BR>
     * <BR>
     * @@param l_orderTypeJudge - (��������ʔ���)<BR>
     * ��������ʔ���<BR>
     * @@param l_dbQuantity - (����)<BR>
     * ����<BR>
     * @@param l_bondProduct - (������)<BR>
     * ������<BR>
     * @@param l_bondExecuteDateInfo - (���������)<BR>
     * ���������<BR>
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@throws WEB3BaseException
     */
    public WEB3BondEstimatedPriceCalcResult calcBondDomesticEstimatedPrice(
        WEB3BondOrderTypeJudge l_orderTypeJudge,
        BigDecimal l_dbQuantity,
        WEB3BondProduct l_bondProduct,
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcBondDomesticEstimatedPrice("
            + "WEB3BondOrderTypeJudge, BigDecimal, WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j����n����v�Z���ʂ𐶐�����B
        WEB3BondEstimatedPriceCalcResult l_calcResult =
            new WEB3BondEstimatedPriceCalcResult();

        //����n����v�Z����.���ʁ�����.����
        l_calcResult.setQuantity(l_dbQuantity);

        //����n����v�Z����.�P��������.������.���t�P��
        l_calcResult.setPrice(
            new BigDecimal(String.valueOf(l_bondProduct.getBuyPrice())));

        //����n����v�Z����.��������ʔ��聁����.��������ʔ���
        l_calcResult.setBondOrderTypeJudge(l_orderTypeJudge);

        //�Q�jthis.calc�������(����n����v�Z����, ������)���Ă�
        //[����]
        // ����n����v�Z���ʁF������������n����v�Z����
        // �������F����.������
        this.calcTradingPrice(l_calcResult, l_bondProduct);

        //�R-�P�j������.is�l��������() == true�̏ꍇ
        //this.calc���񗘎q�����z�i����n����v�Z����, ������, ���������j���Ă�
        //[����]
        // ����n����v�Z���ʁF������������n����v�Z����
        // �������F����.������
        // ���������F����.���������
        if (l_bondProduct.isIndividualGovernmentBond())
        {
            this.calcInitialInterestAdjustAmount(
                l_calcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
        }
        else
        {
            //�R�|�Q�j ����ȊO�̏ꍇ�A
            //this.calc�o�ߗ��q�i����n����v�Z����, ������, ���������j���Ă�
            //[����]
            // ����n����v�Z���ʁF������������n����v�Z����
            // �������F����.������
            // ���������F����.���������
            this.calcAccruedInterest(
                l_calcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
        }

        //�S�jthis.calc��n���(����n����v�Z����, ������)���Ă�
        //[����]
        // ����n����v�Z���ʁF������������n����v�Z����
        // �������F����.������
        this.calcEstimatedPrice(l_calcResult, l_bondProduct);

        //�T�j����n����v�Z���ʂ�Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }
}
@
