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
filename	WEB3FeqBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        :  �O�������v�Z�T�[�r�X(WEB3FeqBizLogicProvider.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/18 ���I (���u) �V�K�쐬
                   2005/07/27 鰊](���u) ���r���[
Revesion History : 2007/11/07 �����q(���u) ����No.005
Revesion History : 2008/01/14 �đo�g(���u) ���f��No.369�A���f��No.372
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i�v�Z�����jNo.12
Revesion History : 2008/11/12 ���O(���u) ���f��No.492�ANo.495�ANo.497�A�v�Z����No.13
Revesion History : 2010/01/11 �����F(���u) ���f��No.528
Revesion History : 2010/10/14 ��V��(���u) ���f��No.556, No.557, No.558, �v�Z����No.015
*/

package webbroker3.feq;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqAsset;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3ChangeRoundDivDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.feq.data.ForeignCostParams;
import webbroker3.feq.data.ForeignCostRow;
import webbroker3.feq.data.SpecialProductForeignCostParams;
import webbroker3.feq.define.WEB3FeqCostDivDef;
import webbroker3.feq.define.WEB3FeqRoundDivDef;
import webbroker3.feq.util.WEB3FeqOrderUtility;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.EquityCommAccountCondMstDao;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.GenCurrencyDao;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������v�Z�T�[�r�X) <BR>
 * �O�������v�Z�T�[�r�X <BR>
 * <BR>                    
 * @@ author ���I <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3FeqBizLogicProvider
    extends WEB3GentradeBizLogicProvider 
    implements GlobalBizLogicProvider, FeqBizLogicProvider

{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqBizLogicProvider.class); 

    /**
     * @@roseuid 42D0D01B037A
     */
    public WEB3FeqBizLogicProvider()
    {

    }
    
    /**
     * (calcExecutionAmount) <BR>
     * �i���g�p�j <BR>
     * 0.0��ԋp����B
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@param arg4
     * @@return double
     * @@roseuid 42806C3901FC
     */
    public double calcExecutionAmount(
        ProductTypeEnum arg0,
        long arg1,
        double arg2,
        double arg3,
        double arg4)
    {
        return 0.0;
    }

    /**
     * (calcSalesTax) <BR>
     * �i���g�p�j <BR>
     * 0.0��ԋp����B <BR>
     * @@param arg0
     * @@return double
     * @@roseuid 42806C40020C
     */
    public double calcSalesTax(double arg0)
    {
        return 0.0;
    }

    /**
     * (checkTradingPower) <BR>
     * �i���g�p�j <BR>
     * null��ԋp����B <BR>
     * @@param arg0
     * @@param arg1
     * @@return OrderValidationResult
     * @@roseuid 42806C40021C
     */
    public OrderValidationResult checkTradingPower(
        SubAccount arg0,
        OrderSpec arg1)
    {
        return null;
    }

    /**
     * (calc�������) <BR>
     * �icalcExecutionAmount�j <BR>
     * <BR>
     * �����~�P����ԋp����B
     * @@param l_dblStockQuantity - (����)
     * @@param l_dblPrice - (�P��) <BR>
     * �P���i�O�݁j
     * @@return double
     * @@roseuid 4281B30801B4
     */
    public double calcExecutionAmount(
        double l_dblStockQuantity, 
        double l_dblPrice)
    {
        String STR_METHOD_NAME = "calcExecutionAmount(double, double)";
        log.entering(STR_METHOD_NAME);
        
        if ((l_dblStockQuantity < 0) || (l_dblPrice < 0))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "���� = " + l_dblStockQuantity);
        }
        
        //�����~�P����ԋp����B
        double l_dblExecutionAmount =
            new BigDecimal(String.valueOf(l_dblStockQuantity))
            .multiply(new BigDecimal(String.valueOf(l_dblPrice)))
            .doubleValue();

        log.debug("�P���F[" + l_dblPrice + "]");
        log.debug("�����F[" + l_dblStockQuantity + "]");
        log.debug("������� �� ���� �~ �P��   �F ["+ l_dblExecutionAmount+ "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblExecutionAmount;
    }

    /**
     * (calc���n���o��)
     * �C�O���o��i���n�萔���C���n����ŁC���̑��R�X�g�P�C���̑��R�X�g�Q�j�� <BR>
     * ���n���Z������v�Z����B<BR>
     * <BR>
     * �P�j�@@�������擾<BR>
     * �@@��������w��inull�j�̏ꍇ�A<BR>
     *   ������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B<BR>
     * <BR>
     * �Q�j�@@�C�O���o��}�X�^�擾<BR>
     * �@@get�C�O���o��}�X�^()�ɂāA�C�O���o��}�X�^�s�iParams�j���擾����B<BR>
     * <BR>
     * �@@[get�C�O���o��}�X�^()�Ɏw�肷�����] <BR>
     * �@@����ID�F�@@����ID<BR>
     * �@@�،���ЃR�[�h�F�@@�،���ЃR�[�h <BR>
     * �@@�s��R�[�h�F�@@�s��R�[�h <BR>
     * �@@��������F�@@��������i�O�݁j <BR>
     * �@@����F�@@��� <BR>
     * �@@���o��敪�F�@@null <BR>
     * �@@�����敪�Fis���t == true �̏ꍇ�A�h�����h���Z�b�g�A�ȊO�h����h���Z�b�g����B<BR>
     *  <BR>
     * �R�j�@@�C�O���o��v�Z <BR>
     * �@@���n���o��I�u�W�F�N�g�𐶐����A<BR>
     * �@@�e�v�Z���ʂ�Ή�����v���p�e�B�ɃZ�b�g����B <BR>
     *  <BR>
     * �@@���@@���n�萔���i���o��敪 == �h���n�萔���h�j <BR>
     * �@@�@@[�v�Z��] <BR>
     * �@@�@@���n�萔�� = ��������i�O�݁j�~������(*1)�{�t�����z(*2) <BR>
     * �@@�@@�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4) <BR>
     * �@@�@@�ɂĊۂߏ������s���j <BR>
     *  <BR>
     * �@@���@@���n����Łi���o��敪 == �h���n����Łh�j <BR>
     * �@@�@@[�v�Z��] <BR>
     * �@@�@@���n����� = ��������i�O�݁j�~������(*1)�{�t�����z(*2) <BR>
     * �@@�@@�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4) <BR>
     * �@@�@@�ɂĊۂߏ������s���j <BR>
     *  <BR>
     * �@@���@@���̑��R�X�g�P�i���o��敪 == �h���̑��R�X�g�P�h�j <BR>
     * �@@�@@[�v�Z��] <BR>
     * �@@�@@���̑��R�X�g�P = ��������i�O�݁j�~������(*1)�{�t�����z(*2) <BR>
     * �@@�@@�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4) <BR>
     * �@@�@@�ɂĊۂߏ������s���j <BR>
     *  <BR>
     * �@@���@@���̑��R�X�g�Q�i���o��敪 == �h���̑��R�X�g�Q�h�j <BR>
     * �@@�@@[�v�Z��] <BR>
     * �@@�@@���̑��R�X�g�Q = ��������i�O�݁j�~������(*1)�{�t�����z(*2) <BR>
     * �@@�@@�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4) <BR>
     * �@@�@@�ɂĊۂߏ������s���j <BR>
     *  <BR>
     * �@@(*1)�@@������ <BR>
     * �@@(*2)�@@�t�����z <BR>
     * �@@(*3)�@@���������� <BR>
     * �@@(*4)�@@�v�Z���ʊۂߕ��� <BR>
     * �@@�Q�j�Ŏ擾�����C�O���o��}�X�^�s�̂����A<BR>
     * �@@�@@���o��敪�ɊY������s���擾����B <BR>
     * �@@�@@�Ή����鏔�o��敪�̍s���Ȃ��ꍇ�́A�v�Z��0�Ƃ���B <BR>
     *  <BR>
     * �S�j�@@���n���Z����v�Z <BR>
     * �@@�ȉ��̌v�Z���ʂ����n���o��.���n���Z����ɃZ�b�g����B <BR>
     *  <BR>
     * �@@���@@���̏ꍇ�iis���t == true�j <BR>
     * �@@�@@���n���Z��� = ��������i�O�݁j�{���n�萔���{���n����Ł{ <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���̑��R�X�g�P�{���̑��R�X�g�Q <BR>
     *  <BR>
     * �@@���@@���̏ꍇ�iis���t == false�j <BR>
     * �@@�@@���n���Z��� = ��������i�O�݁j�|���n�萔���|���n����Ł| <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���̑��R�X�g�P�|���̑��R�X�g�Q <BR>
     *  <BR>
     * �T�j�@@�v�Z���ʕԋp <BR>
     * �@@���n���o��I�u�W�F�N�g��ԋp����B <BR>
     * @@param l_lngProductId - (����ID)
     * @@param l_strOffshoreProductCode - (���n�����R�[�h)
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strMarketCode - (�s��R�[�h)
     * @@param l_dblTradePriceFc - (��������i�O�݁j)
     * @@param l_datBaseDate - (���) <BR>
     * null�̏ꍇ�A���������g�p����B
     * @@param l_blnIsBuy - (is���t) <BR>
     * ���t���̔��� <BR>
     *  <BR>
     * true�F�� <BR>
     * false�F�� <BR>
     * @@return webbroker3.feq.WEB3FeqBalanceCost
     * @@roseuid 4282ECBC00FD
     */
    public WEB3FeqForeignCost calcForeignCost(
        Long l_lngProductId,
        String l_strOffshoreProductCode,
        String l_strInstitutionCode,
        String l_strMarketCode,
        double l_dblTradePriceFc,
        Date l_datBaseDate,
        boolean l_blnIsBuy) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcForeignCost(Long, String, String, String, double, Date, boolean)";
        log.entering(STR_METHOD_NAME);

        //�P�j�������擾 
        //��������w��inull�j�̏ꍇ�A 
        //������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        //�Q�j�C�O���o��}�X�^�擾
        //get�C�O���o��}�X�^()�ɂāA�C�O���o��}�X�^�s�iParams�j���擾����B
        //[get�C�O���o��}�X�^()�Ɏw�肷�����]
        //����ID�F�@@����ID
        //���n�����R�[�h�F�@@���n�����R�[�h
        //�،���ЃR�[�h�F�@@�،���ЃR�[�h
        //�s��R�[�h�F�@@�s��R�[�h 
        //��������F�@@��������i�O�݁j
        //����F�@@��� 
        //���o��敪�F�@@null 
        //�����敪�Fis���t == true �̏ꍇ�A�h�����h���Z�b�g�A�ȊO�h����h���Z�b�g����B
        String l_strSideDiv;
        if (l_blnIsBuy)
        {
            l_strSideDiv = WEB3BuySellTypeDef.BUY;
        }
        else
        {
            l_strSideDiv = WEB3BuySellTypeDef.SELL;
        }

        ForeignCostParams[] l_foreignCostParams = 
            this.getForeignCost(
                l_lngProductId,
                l_strOffshoreProductCode,
                l_strInstitutionCode,
                l_strMarketCode,
                l_dblTradePriceFc,
                l_datOrderBizDate,
                null,
                l_strSideDiv);

        //�R�j�C�O���o��v�Z
        //���n���o��I�u�W�F�N�g�𐶐���
        WEB3FeqForeignCost l_feqForeignCost = new WEB3FeqForeignCost();

        //�e�v�Z���ʂ�Ή�����v���p�e�B�ɃZ�b�g����B
        //���n�萔���i���o��敪 == �h���n�萔���h�j
        //[�v�Z��] 
        //���n�萔�� = ��������i�O�݁j�~������(*1)�{�t�����z(*2) 
        //�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4)�ɂĊۂߏ������s���j
        //���n����Łi���o��敪 == �h���n����Łh�j 
        //[�v�Z��] 
        //���n����� = ��������i�O�݁j�~������(*1)�{�t�����z(*2) 
        //�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4)�ɂĊۂߏ������s���j 
        //���̑��R�X�g�P�i���o��敪 == �h���̑��R�X�g�P�h�j 
        //[�v�Z��] 
        //���̑��R�X�g�P = ��������i�O�݁j�~������(*1)�{�t�����z(*2)
        //�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4)�ɂĊۂߏ������s���j
        //���̑��R�X�g�Q�i���o��敪 == �h���̑��R�X�g�Q�h�j
        //[�v�Z��] 
        //���̑��R�X�g�Q = ��������i�O�݁j�~������(*1)�{�t�����z(*2)
        //�i�v�Z���ʂ́A����������(*3)�������A�v�Z���ʊۂߕ���(*4)�ɂĊۂߏ������s���j
        //(*1)�@@������ 
        //(*2)�@@�t�����z 
        //(*3)�@@���������� 
        //(*4)�@@�v�Z���ʊۂߕ��� 
        //�Q�j�Ŏ擾�����C�O���o��}�X�^�s�̂����A
        //���o��敪�ɊY������s���擾����B
        //�Ή����鏔�o��敪�̍s���Ȃ��ꍇ�́A�v�Z��0�Ƃ���B
        BigDecimal l_bdTradePriceFc = new BigDecimal(String.valueOf(l_dblTradePriceFc));
        BigDecimal l_bdCommisionRate;
        BigDecimal l_bdAddAmount;
        BigDecimal l_bdPrice;
        double l_dblResult = 0.0D;
        for (int i = 0; i < l_foreignCostParams.length; i++)
        {
            l_bdCommisionRate = new BigDecimal(String.valueOf(l_foreignCostParams[i].getCommisionRate()));
            l_bdAddAmount = new BigDecimal(String.valueOf(l_foreignCostParams[i].getAddAmount()));
            l_bdPrice = l_bdTradePriceFc.multiply(l_bdCommisionRate)
                        .divide(new BigDecimal("100"), 10, BigDecimal.ROUND_HALF_EVEN)
                        .add(l_bdAddAmount);
            l_dblResult = l_bdPrice.doubleValue();
            int l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdPrice.toString());
            int l_intScale = l_foreignCostParams[i].getScale();
            if (l_intDigits > l_intScale)
            {
                if (WEB3FeqRoundDivDef.ROUND.equals(l_foreignCostParams[i].getRoundDiv()))
                {
                    l_dblResult = WEB3FeqOrderUtility.getCutOutValue(
                        l_bdPrice.doubleValue(),
                        l_intScale,
                        WEB3FeqOrderUtility.ROUND);
                }
                else if (WEB3FeqRoundDivDef.CUTOFF.equals(l_foreignCostParams[i].getRoundDiv()))
                {
                    l_dblResult = WEB3FeqOrderUtility.getCutOutValue(
                        l_bdPrice.doubleValue(),
                        l_intScale,
                        WEB3FeqOrderUtility.CUTOFF);
                }
                else if (WEB3FeqRoundDivDef.CUT_UP.equals(l_foreignCostParams[i].getRoundDiv()))
                {
                    l_dblResult = WEB3FeqOrderUtility.getCutOutValue(
                        l_bdPrice.doubleValue(),
                        l_intScale,
                        WEB3FeqOrderUtility.CEIL);
                }
            }
            
            if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("���n�萔���i�O�݁F[" + l_dblResult + "]");
                l_feqForeignCost.setForeignCommissionFee(l_dblResult);

            }
            else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("���n����Łi�O�݁F[" + l_dblResult + "]");
                l_feqForeignCost.setForeignTax(l_dblResult);

            }
            else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("���̑��R�X�g�P�i�O�݁F[" + l_dblResult + "]");
                l_feqForeignCost.setForeignFeeExt1(l_dblResult);

            }
            else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(l_foreignCostParams[i].getCostDiv()))
            {
                log.debug("���̑��R�X�g�Q�i�O�݁F[" + l_dblResult + "]");
                l_feqForeignCost.setForeignFeeExt2(l_dblResult);

            }
        }

        //�S�j���n���Z����v�Z�ȉ��̌v�Z���ʂ����n���o��.���n���Z����ɃZ�b�g����B
        //���̏ꍇ�iis���t == true�j
        //���n���Z��� = ��������i�O�݁j�{���n�萔���{���n����Ł{���̑��R�X�g�P�{���̑��R�X�g�Q 
        if (l_blnIsBuy)
        {
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTradePriceFc, 
                    l_feqForeignCost.getForeignCommissionFee());
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignTax());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt1());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt2());                     
            l_feqForeignCost.setBalanceAmountFc(l_dblTemp);
        }
        //���̏ꍇ�iis���t == false�j 
        //���n���Z��� = ��������i�O�݁j�|���n�萔���|���n����Ł|���̑��R�X�g�P�|���̑��R�X�g�Q 
        else
        {
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTradePriceFc, 
                    l_feqForeignCost.getForeignCommissionFee());
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignTax());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt1());                     
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    l_dblTemp, 
                    l_feqForeignCost.getForeignFeeExt2());                     
            l_feqForeignCost.setBalanceAmountFc(l_dblTemp);
        }

        log.exiting(STR_METHOD_NAME);
        //�T�j�v�Z���ʕԋp���n���o��I�u�W�F�N�g��ԋp����B
        return l_feqForeignCost;
    }

    /**
     * (calc�S���������) <BR>
     * �S������������v�Z����B <BR>
     *  <BR>
     * �P�j�@@��Е��X���i�e�[�u������ <BR>
     * �@@�ȉ��̏����ŉ�Е��X���i�e�[�u�����������A�����S�������擾����B <BR>
     *  <BR>
     * �@@[����] <BR>
     * �@@���X�h�c = ���X�h�c And <BR>
     * �@@�萔�����i�R�[�h = this.get�萔�����i�R�[�h() <BR>
     *  <BR>
     * �Q�j�@@�S������������v�Z����B  <BR>
     *  <BR>
     * �@@�S��������� = ���n���Z����i�~�݁j�~�����S����  <BR>
     *  <BR>
     * @@param l_lngBranchId - ���X�h�c
     * @@param l_dblBalanceAmount - ���n���Z����i�~�݁j
     * @@return double
     * @@roseuid 42832E9700DD
     */
    public double calcRestraintAmount(long l_lngBranchId, double l_dblBalanceAmount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcRestraintAmount(long, double)";
        log.entering(STR_METHOD_NAME);

        //�P�j��Е��X���i�e�[�u������
        //�ȉ��̏����ŉ�Е��X���i�e�[�u�����������A�����S�������擾����B 
        //[����]
        //���X�h�c = ���X�h�c And �萔�����i�R�[�h = this.get�萔�����i�R�[�h() 
        try
        {
            InstBranchProductRow l_instBranchProductRow =
                (InstBranchProductRow)InstBranchProductDao.findRowByBranchIdCommissionProductCode(
                    l_lngBranchId,
                    this.getCommissionProductCode());

            //�S��������� = ���n���Z����i�~�݁j�~�����S����
            double l_dblPremiumRestraintRate = 0.0D;
            if (l_instBranchProductRow != null)
            {
                l_dblPremiumRestraintRate = 
                    l_instBranchProductRow.getPremiumRestraintRate();
            }
            double l_dblRestraintAmount = 
                l_dblBalanceAmount * l_dblPremiumRestraintRate;
            log.debug("���n���Z����i�~�݁F[" + l_dblBalanceAmount + "]");
            log.debug("�����S�����F[" + l_dblPremiumRestraintRate + "]");
            log.debug("�S��������� = ���n���Z����i�~�݁j�~�����S����   �F [" 
                + l_dblRestraintAmount + "]");

            log.exiting(STR_METHOD_NAME);
            return l_dblRestraintAmount;
        }
        catch (DataFindException l_ex)
        {
            log.error("�f�[�^�s�����G���[: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() +  "." + STR_METHOD_NAME,
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
    }

    /**
     * (calc�ϑ��萔��) <BR>
     * �icalcCommission�̃I�[�o�[���[�h�j <BR>
     *  <BR>
     * �P�j�@@�������擾 <BR>
     * �@@��������w��inull�j�̏ꍇ�A <BR>
     * �@@������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B <BR>
     *  <BR>
     * �Q�j�@@�萔���I�u�W�F�N�g�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B  <BR>
     *  <BR>
     * �@@�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode() <BR>
     * �@@���XID�F �⏕����.get����X().BranchId() <BR>
     * �@@�萔�����i�R�[�h�F this.get�萔�����i�R�[�h() <BR>
     * �@@����R�[�h�iSONAR�j�F�@@this.get����R�[�h�iSONAR�j() <BR>
     * �@@�������F�@@������ <BR>
     * �@@�ٍϋ敪�F�@@�ٍϋ敪.00�F���̑�  <BR>
     * �@@�����`���l���F �����`���l�� <BR>
     * �@@is�w�l�F is�w�l���� <BR>
     * �@@�s��R�[�h�iSONAR�j�F�@@�s��.�s��R�[�h�iSONAR�j <BR>
     * �@@���o��v�Z�p����F�@@���n���Z����i�~�݁j <BR>
     *  <BR>
     * �R�j�@@�萔���v�Z <BR>
     * �@@this.calc�ϑ��萔��(�萔���C�⏕����)�ɈϏ��ideligate�j����B <BR>
     *  <BR>
     * �S�j�@@�萔���I�u�W�F�N�g�ԋp <BR>
     * �@@�萔���I�u�W�F�N�g��ԋp����B <BR>
     * @@param l_subAccount - (�⏕����) 
     * �⏕�����I�u�W�F�N�g
     * @@param l_market - (�s��) 
     * �s��I�u�W�F�N�g
     * @@param l_datBaseDate - (���) 
     * null�̏ꍇ�A���������g�p����B
     * @@param l_blnIsBuy - is���t
     * ���t���̔���<BR>
     * <BR>
     * true�F��<BR>
     * false�F�� <BR>
     * @@param l_blnIsLimitOrder - �iisLimitOrder�j
     * �w�l�������𔻒肷��B <BR>
     *  <BR>
     * true�F�w�l���� <BR>
     * false�F���s���� <BR>
     * @@param l_dblBalanceAmount - (���n���Z����i�~�݁j)
     * @@param l_strOrderChannel - �����`���l��
     * @@return WEB3GentradeCommission
     * @@roseuid 42830455015A
     */
    public WEB3GentradeCommission calcCommission(
        SubAccount l_subAccount,
        Market l_market,
        Date l_datBaseDate,
        boolean l_blnIsBuy,
        boolean l_blnIsLimitOrder,
        double l_dblBalanceAmount,
        String l_strOrderChannel) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcCommission(SubAccount, Market, Date, boolean, double, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_market == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //�P�j�@@�������擾 
        //    �@@��������w��inull�j�̏ꍇ�A 
        //    �@@������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        //�Q�j�@@�萔���I�u�W�F�N�g�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B 

        WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();

        // �@@�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode() 
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        // �@@���XID�F �⏕����.get����X().BranchId() 
        l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());

        // �@@�萔�����i�R�[�h�F this.get�萔�����i�R�[�h()
        l_gentradeCommission.setCommissionProductCode(this.getCommissionProductCode());

        // �@@����R�[�h�iSONAR�j�F�@@this.get����R�[�h�iSONAR�j() 
        l_gentradeCommission.setSonarTradedCode(this.getSonarTradedCode(l_blnIsBuy));

        // �@@�������F�@@������ 
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        // �@@�ٍϋ敪�F�@@�ٍϋ敪.00�F���̑�  
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
        
        // �@@�����`���l���F �����`���l�� 
        l_gentradeCommission.setOrderChannel(l_strOrderChannel);

        // �@@is�w�l�F is�w�l���� 
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitOrder);

        // �@@�s��R�[�h�iSONAR�j�F�@@�s��.�s��R�[�h�iSONAR�j 
        MarketRow l_marketRow = (MarketRow) (l_market.getDataSourceObject());       
        l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());

        // �@@���o��v�Z�p����F�@@���n���Z����i�~�݁j 
        l_gentradeCommission.setExpensesCalcAmount(l_dblBalanceAmount);
        
        //�R�j�@@�萔���v�Z 
        //this.calc�ϑ��萔��(�萔���C�⏕����)�ɈϏ��ideligate�j����B 
        this.calcCommission(l_gentradeCommission,l_subAccount);
        
        //�S�j�@@�萔���I�u�W�F�N�g�ԋp
        log.exiting(STR_METHOD_NAME);
        return l_gentradeCommission;
    }

    /**
     * (calc�����) <BR>
     * �icalcSalesTax�̃I�[�o�[���[�h�j <BR>
     * �w����z�ɑ΂������ł�ԋp����B <BR>
     *  <BR>
     * super.calc�����()�ɈϏ��ideligate�j����B <BR>
     *  <BR>
     * @@see WEB3GentradeBizLogicProvider.calcSalesTax( <BR>
     * �@@double, Timestamp, SubAccount) <BR>
     * @@param l_dblAmount - (���z) <BR>
     * ����ł��Z�o����Ώۂ̋��z
     * @@param l_datBaseDate - (���) <BR>
     * ����ł��Z�o������ <BR>
     * �����̏ꍇ�͔������ɂ�����B
     * @@param l_subAccount - (�⏕����)
     * @@return double
     * @@roseuid 428879CA0109
     */
    public double calcSalesTax(
        double l_dblAmount, 
        Date l_datBaseDate, 
        SubAccount l_subAccount) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcSalesTax(double, Date, SubAccount)";
        log.entering(STR_METHOD_NAME);

        Timestamp l_tsBaseDate = new Timestamp(l_datBaseDate.getTime());

        double l_dblSalesTax = 
            super.calcSalesTax(l_dblAmount, l_tsBaseDate, l_subAccount);

        log.exiting(STR_METHOD_NAME);
        return l_dblSalesTax;
    }

    /**
     * (calc���n�v��) <BR>
     * ���n�v�ł��v�Z����B <BR>
     *  <BR>
     * ���n�v�łɂO��ݒ肵�ă��^�[��
     * �i�O�������̔[�ł͐\���̂݁j
     ****************�ȉ��R�����g�A�E�g051201******************
     * �����v�Z�T�[�r�X.calc���n�v��()�ɈϏ��ideligate�j����B <BR>
     *  <BR>
     * �@@[�����v�Z�T�[�r�X.calc���n�v��()�Ɏw�肷�����] <BR>
     * �@@�⏕�����F�@@�⏕���� <BR>
     * �@@�ŋ敪�F�@@�ŋ敪 <BR>
     * �@@���z�F�@@��������i�~�݁j <BR>
     * �@@����F�@@��n�� <BR>
     * �@@�ڋq�ŋ敪�F�@@(**1) <BR>
     *  <BR>
     * (**1) �ڋq�ŋ敪�̎擾 <BR>
     * �@@�ڋq.get��n���ŋ敪() <BR>
     * �@@�i�ڋq�́A�A�J�E���g�}�l�[�W��.get�ڋq(�⏕����.getAccountId()) <BR>
     * �@@�ɂĎ擾����j <BR>
     *  <BR>
     * @@see WEB3EquityBizLogicProvider.calcCapitalGainTax(  <BR>
     * �@@SubAccount,TaxTypeEnum,double,Timestamp,TaxTypeEnum) <BR>
     ****************�ȏ�R�����g�A�E�g051201******************
     * @@param l_subAccount - (�⏕����) 
     * �⏕�����B
     * @@param l_taxType - (�ŋ敪) 
     * �ŋ敪�B
     * @@param l_dblTradeAmount - ��������i�~�݁j
     * @@param l_datDeliveryDate - (��n��) 
     * ���n�v�ł��Z�o�������i=��n���j
     * @@return double
     * @@roseuid 4281B6030251
     */
    public double calcCapitalGainTax(
        WEB3GentradeSubAccount l_subAccount,
        TaxTypeEnum l_taxType,
        double l_dblTradeAmount,
        Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "calcCapitalGainTax(WEB3GentradeSubAccount, TaxTypeEnum, double, Date)";
        log.entering(STR_METHOD_NAME);

        return 0;
     /*
        if ((l_subAccount == null) || (l_datDeliveryDate == null))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider = 
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        try
        {
            //�ڋq�́A�A�J�E���g�}�l�[�W��.get�ڋq(�⏕����.getAccountId()
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_subAccount.getAccountId());
            Timestamp l_timBaseDate = 
                new Timestamp(l_datDeliveryDate.getTime());
            double l_dblCainTax = 
                l_bizLogicProvider.calcCapitalGainTax(
                    l_subAccount, 
                    l_taxType, 
                    l_dblTradeAmount, 
                    l_timBaseDate, 
                    l_mainAccount.getDeliveryDateTaxType(l_datDeliveryDate));

            log.exiting(STR_METHOD_NAME);
            return l_dblCainTax;
        }
        catch (NotFoundException l_ex)
        {
            log.error("�f�[�^�s�����G���[: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName() +
                "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
     */   
    }

    /**
     * (calc���n���v) <BR>
     * ���n�v�ł��v�Z����B <BR>
     *  <BR>
     * �����v�Z�T�[�r�X.calc���n���v()�ɈϏ��ideligate�j����B <BR>
     *  <BR>
     * �@@[calc���n���v()�Ɏw�肷�����] <BR>
     * �@@���z�F�@@��������i�~�݁j <BR>
     * �@@�����ʁF�@@������ <BR>
     * �@@�����h�c�F�@@�����h�c <BR>
     * �@@�⏕�����F�@@�⏕���� <BR>
     * �@@�ŋ敪�F�@@�ŋ敪 <BR>
     *  <BR>
     * @@see WEB3EquityBizLogicProvider.calcCapitaGain( <BR>
     * double, double, long, SubAccount, TaxTypeEnum) <BR>
     * @@param l_dblTradeAmount - ��������i�~�݁j
     * @@param l_dblSellStockQuantity - (������)
     * @@param l_lngProductId - �����h�c
     * @@param l_subAccount - (�⏕����)
     * @@param l_taxType - (�ŋ敪)
     * @@return double
     * @@roseuid 42887ECD01A5
     */
    public double calcCapitalProfitLoss(
        double l_dblTradeAmount,
        double l_dblSellStockQuantity,
        long l_lngProductId,
        WEB3GentradeSubAccount l_subAccount,
        TaxTypeEnum l_taxType) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcCapitalProfitLoss(double, double, long, WEB3GentradeSubAccount, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);

        if ((l_dblSellStockQuantity < 0) || (l_subAccount == null))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider = 
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

        //�����v�Z�T�[�r�X.calc���n���v()�ɈϏ��ideligate�j����B
        double l_dblCainTax = 
            l_bizLogicProvider.calcCapitaGain(
                l_dblTradeAmount, 
                l_dblSellStockQuantity, 
                l_lngProductId, 
                l_subAccount, 
                l_taxType);

        log.exiting(STR_METHOD_NAME);
        return l_dblCainTax;
    }

    /**
     * (calc�O�݊��Z) <BR>
     * �icalcForignCCYAmount�j <BR>
     * �w����z���w�背�[�g�ŊO�݊��Z���s���B <BR>
     *  <BR>
     * ���z�i�~�݁j�����[�g�̌v�Z���ʂ�ԋp����B <BR>
     * �i�v�Z���ʂ́A�����������������O�݊��Z�ۂߕ����Ŋۂߏ������s���j <BR>
     * @@param l_dblAmount - (���z�i�~�݁j) 
     * ���z�i�~�݁j
     * @@param l_dblRate - ���[�g
     * @@param l_intScale - (����������) 
     * ����������
     * @@param l_strChangeFccyRoundDiv - (�O�݊��Z�ۂߕ���) 
     * �O�݊��Z�ۂߕ��� <BR>
     *  <BR>
     * 0�F�l�̌ܓ��@@1�F�؎́@@2�F�؏� <BR>
     * @@return double
     * @@roseuid 4281B35F028F
     */
    public double calcForeignCCYAmount(
        double l_dblAmount,
        double l_dblRate,
        int l_intScale,
        String l_strChangeFccyRoundDiv) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcForeignCCYAmount(double, double, int, String)";
        log.entering(STR_METHOD_NAME);

        //���z�i�~�݁j�����[�g
        BigDecimal l_bdAmount = new BigDecimal(String.valueOf(l_dblAmount));
        BigDecimal l_bdForignCCYAmount =
            l_bdAmount.divide(new BigDecimal(String.valueOf(l_dblRate)), 10, BigDecimal.ROUND_HALF_EVEN);
        double l_dblForignCCYAmount = l_bdForignCCYAmount.doubleValue();
        if (l_dblAmount < 0.0D)
        {
            l_dblForignCCYAmount *= -1.0D;
        }

        if ((Double.isInfinite(l_dblForignCCYAmount)) || 
            (Double.isNaN(l_dblForignCCYAmount)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        double l_dblReturnAmount = l_dblForignCCYAmount;
        int l_intDigits = 
            WEB3StringTypeUtility.getFractionDigits(
                new Double(l_dblForignCCYAmount).toString());

        if (l_intDigits > l_intScale)
        {
            //1�F�؎�
            if (WEB3FeqRoundDivDef.CUTOFF.equals(l_strChangeFccyRoundDiv))
            {
                l_dblReturnAmount = 
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblForignCCYAmount,
                        l_intScale,
                        WEB3FeqOrderUtility.CUTOFF);    
            }
            //2�F�؏�
            else if (WEB3FeqRoundDivDef.CUT_UP.equals(l_strChangeFccyRoundDiv))
            {
                l_dblReturnAmount = 
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblForignCCYAmount,
                        l_intScale,
                        WEB3FeqOrderUtility.CEIL);    
            }
            //0�F�l�̌ܓ�
            else
            {
                l_dblReturnAmount = 
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblForignCCYAmount,
                        l_intScale,
                        WEB3FeqOrderUtility.ROUND);    
            }
        }
        if (l_dblAmount < 0.0D)
        {
            l_dblReturnAmount *= -1.0D;
        }

        log.debug("���z�i�~�݁j�F[" + l_dblAmount + "]");
        log.debug("���[�g�F[" + l_dblRate + "]");
        log.debug("calc�O�݊��Z �� ���z�i�~�݁j�����[�g �F [" + 
            l_dblReturnAmount + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblReturnAmount; 
    }

    /**
     * (calc�~�݊��Z) <BR>
     * �icalcJPYAmount�j <BR>
     * �w����z���w�背�[�g�ŖM�݊��Z���s���B <BR>
     *  <BR>
     * ���z�i�O�݁j�~���[�g�̌v�Z���ʂ�ԋp����B <BR>
     * �i�v�Z���ʂ́A���������~�݊��Z�ۂߕ����Ŋۂߏ������s���j <BR>
     * @@param l_dblForeignAmount - ���z�i�O�݁j
     * @@param l_dblRate - ���[�g
     * @@param l_strChangeFccyRoundDiv - (�~�݊��Z�ۂߕ���) <BR>
     * �~�݊��Z�ۂߕ��� <BR>
     *  <BR>
     * 0�F�l�̌ܓ��@@1�F�؎́@@2�F�؏� <BR>
     * @@return double
     * @@roseuid 4281B3B20241
     */
    public double calcJPYAmount(
        double l_dblForeignAmount, 
        double l_dblRate, 
        String l_strChangeFccyRoundDiv)
    {
        String STR_METHOD_NAME = "calcJPYAmount(double, double, String)";
        log.entering(STR_METHOD_NAME);
        
        //���z�i�O�݁j�~���[�g
        BigDecimal l_bdJPYAmount =
            new BigDecimal(String.valueOf(l_dblForeignAmount)).multiply(new BigDecimal(String.valueOf(l_dblRate)));
        double l_dblJPYAmount = l_bdJPYAmount.doubleValue();
        if (l_dblForeignAmount < 0.0D)
        {
            l_dblJPYAmount *= -1.0D;
        }
        double l_dblReturnJPYAmount = l_dblJPYAmount;
        
        //1�F�؎�
        if (WEB3FeqRoundDivDef.CUTOFF.equals(l_strChangeFccyRoundDiv))
        {
            l_dblReturnJPYAmount = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblJPYAmount,
                    0,
                    WEB3FeqOrderUtility.CUTOFF);    
        }
        //2�F�؏�
        else if (WEB3FeqRoundDivDef.CUT_UP.equals(l_strChangeFccyRoundDiv))
        {
            l_dblReturnJPYAmount = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblJPYAmount,
                    0,
                    WEB3FeqOrderUtility.CEIL);    
        }
        //0�F�l�̌ܓ�
        else
        {
            l_dblReturnJPYAmount = 
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblJPYAmount,
                    0,
                    WEB3FeqOrderUtility.ROUND);    
        }
        if (l_dblForeignAmount < 0.0D)
        {
            l_dblReturnJPYAmount *= -1.0D;
        }
        
        log.debug("���z�i�O�݁j�F[" + l_dblForeignAmount + "]");
        log.debug("���[�g�F[" + l_dblRate + "]");
        log.debug("calc�O�݊��Z �� ���z�i�O�݁j�~���[�g �F [" + 
            l_dblReturnJPYAmount + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblReturnJPYAmount; 
    } 

    /**
     * (get�萔�����i�R�[�h) <BR>
     * �萔�����i�R�[�h.40�F�O�������@@��ԋp����B
     * @@return String
     * @@roseuid 4283169E02A3
     */
    public String getCommissionProductCode()
    {
        String l_strCommissionProductCode = 
            WEB3CommisionProductCodeDef.FOREIGN_EQITY;
        return l_strCommissionProductCode;        
    }

    /**
     * (get����R�[�h�iSONAR�j) <BR>
     * <BR>
     * ����.is���t == true �̏ꍇ�A����R�[�h�iSONAR�j.11�F���ʊ�����ԋp����B<BR> 
     * ����.is���t == false �̏ꍇ�A����R�[�h�iSONAR�j.91�F���t��ԋp����B<BR>
     * <BR>
     * @@param l_blnIsBuy is���t
     * ���t�������ǂ����̔���t���O<BR>
     * <BR>
     * true�F ���t����<BR>
     * false�F ���t����<BR> 
     * @@return String ����R�[�h�iSONAR�j
     * @@roseuid 428316FB0080
     */
    public String getSonarTradedCode(boolean l_blnIsBuy)
    {
        if (l_blnIsBuy)
        {
            return WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        else
        {
            return WEB3TransactionTypeSONARDef.SELL;
        }
    }

    /**
     * (get�C�O���o��}�X�^) <BR>
     * �C�O���o��}�X�^���擾����B <BR>
     *  <BR>
     * �P�j�@@�������擾 <BR>
     * �@@��������w��inull�j�̏ꍇ�A <BR>
     * �@@������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B <BR>
     *  <BR>
     * �Q�j�@@�C�O���o��}�X�^���� <BR>
     * �@@�ȉ��̏����ɂāA�C�O���o��}�X�^�e�[�u�����������A <BR>
     * �@@�擾�����C�O���o��}�X�^�s�I�u�W�F�N�g��z��B <BR>
     *  <BR>
     * �@@[�i���o��敪 == null�j�̏ꍇ�̏���] <BR>
     * �@@�C�O���o��}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And�@@ <BR>
     * �@@�C�O���o��}�X�^.�s��R�[�h = �s��R�[�h And�@@ <BR>
     * �@@�C�O���o��}�X�^.�E�v�J�n�N���� <= ��� And <BR>
     * �@@�C�O���o��}�X�^.�E�v�I���N���� >= ��� And <BR>
     * �@@�C�O���o��}�X�^.��������iFROM�j < ������� And <BR>
     * �@@�C�O���o��}�X�^.��������iTO�j >= ������� <BR>
     * �@@�C�O���o��}�X�^.�����敪 = �����敪<BR>
     *  <BR>
     * �@@[�i���o��敪 != null�j�̏ꍇ�̏���] <BR>
     * �@@�C�O���o��}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And�@@<BR>
     * �@@�C�O���o��}�X�^.�s��R�[�h = �s��R�[�h And�@@<BR>
     * �@@�C�O���o��}�X�^.�E�v�J�n�N���� <= ��� And <BR>
     * �@@�C�O���o��}�X�^.�E�v�I���N���� >= ��� And <BR>
     * �@@�C�O���o��}�X�^.��������iFROM�j < ������� And <BR>
     * �@@�C�O���o��}�X�^.��������iTO�j >= ������� And <BR>
     * �@@�C�O���o��}�X�^.���o��敪 = ���o��敪 <BR>
     * �@@�C�O���o��}�X�^.�����敪 = �����敪<BR>
     *  <BR>
     *  ���ꏔ�o��敪�̃��R�[�h�������s�擾���ꂽ�ꍇ�͗�O���X���[����B<BR>
     *  ���u����敪�̕����s�̃��R�[�h���I������܂����v<BR>
     *  class:WEB3BusinessLayerException<BR>
     *  tag:BUSINESS_ERROR_03209<BR>
     *  <BR>
     * �R�j�@@��������p�C�O���o��}�X�^����<BR>
     * �@@�ȉ��̏����ɂāA��������p�C�O���o��}�X�^�e�[�u�����������A<BR>
     * �擾������������p�C�O���o��}�X�^�s�I�u�W�F�N�g��z��B <BR>
     *  <BR>
     * �@@[�i���o��敪 == null�j�̏ꍇ�̏���]<BR>
     * �@@��������p�C�O���o��}�X�^.����ID = ����ID And<BR>
     * �@@��������p�C�O���o��}�X�^.���n�����R�[�h = ���n�����R�[�h And<BR>
     * �@@��������p�C�O���o��}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@��������p�C�O���o��}�X�^.�s��R�[�h = �s��R�[�h And<BR>
     * �@@��������p�C�O���o��}�X�^.�E�v�J�n�N���� <= ��� And<BR>
     * �@@��������p�C�O���o��}�X�^.�E�v�I���N���� >= ��� And<BR>
     * �@@��������p�C�O���o��}�X�^.��������iFROM�j < ������� And<BR>
     * �@@��������p�C�O���o��}�X�^.��������iTO�j >= ������� And<BR>
     * �@@��������p�C�O���o��}�X�^.�����敪 = �����敪<BR>
     *  <BR>
     * �@@[�i���o��敪 != null�j�̏ꍇ�̏���]<BR>
     * �@@��������p�C�O���o��}�X�^.����ID = ����ID And<BR>
     * �@@��������p�C�O���o��}�X�^.���n�����R�[�h = ���n�����R�[�h And<BR>
     * �@@��������p�C�O���o��}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@��������p�C�O���o��}�X�^.�s��R�[�h = �s��R�[�h And<BR>
     * �@@��������p�C�O���o��}�X�^.�E�v�J�n�N���� <= ��� And<BR>
     * �@@��������p�C�O���o��}�X�^.�E�v�I���N���� >= ��� And<BR>
     * �@@��������p�C�O���o��}�X�^.��������iFROM�j < ������� And<BR>
     * �@@��������p�C�O���o��}�X�^.��������iTO�j >= ������� And<BR>
     * �@@��������p�C�O���o��}�X�^.���o��敪 = ���o��敪 And<BR>
     * �@@��������p�C�O���o��}�X�^.�����敪 = �����敪<BR>
     *  <BR>
     *  ���ꏔ�o��敪�̃��R�[�h�������s�擾���ꂽ�ꍇ�͗�O���X���[����B<BR>
     *  ���u����敪�̕����s�̃��R�[�h���I������܂����v<BR>
     *  class:WEB3BusinessLayerException<BR>
     *  tag:BUSINESS_ERROR_03209<BR>
     *  <BR>
     * �S�j��L�R�j�ɂē�������p�C�O���o��}�X�^�s�̔z����擾���Ȃ��ꍇ�A<BR>
     * �Q�j�擾�����C�O���o��}�X�^�s�̔z���ԋp����B<BR>
     *  <BR>
     * �T�j��L�R�j�ɂē�������p�C�O���o��}�X�^�s�̔z����擾�����ꍇ�A<BR>
     * �e��������p�C�O���o��}�X�^�s��Loop�������s���B<BR>
     *  <BR>
     * �@@�T�|�P�j��������p�C�O���o��}�X�^�s.���o��敪��<BR>
     * �Q�j�擾�����z��̊e�C�O���o��}�X�^�s.���o��敪�ƈ�v����ꍇ�A�ȉ��̑�����s���B<BR> 
     *  <BR>
     * �@@�T�|�Q�j�u����ID�v�A�u���n�����R�[�h�v�ȊO�̃t�B�[���h�A<BR>
     * �C�O���o��}�X�^�s���������p�C�O���o��}�X�^�s�ɕt���ւ���B<BR>
     *  <BR>
     * �U�j�t���ւ����̊C�O���o��}�X�^�s�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_lngProductId - ����ID
     * @@param l_strOffshoreProductCode - ���n�����R�[�h
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strMarketCode - �s��R�[�h
     * @@param l_dblForeignTradeAmount - ��������i�O�݁j
     * @@param l_datBaseDate - (���) <BR>
     * null�̏ꍇ�A���������g�p����B
     * @@param l_strCostDiv - (���o��敪) <BR>
     * ���o��敪�B���ׂĎ擾����ꍇ�Anull���w��B <BR>
     *  <BR>
     * 01�F�@@���n�萔�� <BR>
     * 02�F�@@���n�����  <BR>
     * 03�F�@@���̑����n�R�X�g�P <BR>
     * 04�F�@@���̑����n�R�X�g�Q <BR>
     * @@param l_strSideDiv - (�����敪)<BR>
     * �����敪<BR>
     * �P�F����<BR>
     * �Q�F����<BR>
     * @@return webbroker3.feq.data.ForeignCostParams[]
     * @@throws WEB3BaseException
     * @@roseuid 4282F0510032
     */
    public ForeignCostParams[] getForeignCost(
        Long l_lngProductId,
        String l_strOffshoreProductCode,
        String l_strInstitutionCode,
        String l_strMarketCode,
        double l_dblForeignTradeAmount,
        Date l_datBaseDate,
        String l_strCostDiv,
        String l_strSideDiv)
        throws WEB3BaseException
    {
            
        String STR_METHOD_NAME = "getForeignCost(Long, String, String, String, double, Date, String, String)";
        log.entering(STR_METHOD_NAME);
      
        //�P�j�������擾 
        //��������w��inull�j�̏ꍇ�A 
        //������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        try
        {
            //�Q�j�C�O���o��}�X�^����
            //�ȉ��̏����ɂāA�C�O���o��}�X�^�e�[�u�����������A�擾�����C�O���o��}�X�^�s�I�u�W�F�N�g��z��B
            List l_lstreturnForeignCost = null;
            Object[] l_strBindValue = null;
            //�f�[�^���m
            QueryProcessor processor = Processors.getDefaultProcessor();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //�،���ЃR�[�h
            l_sbWhere.append(" and market_code = ? ");  //�s��R�[�h
            l_sbWhere.append(" and to_char(appli_start_date,'yyyyMMdd') <= ? "); //�E�v�J�n�N����
            l_sbWhere.append(" and to_char(appli_end_date,'yyyyMMdd') >= ? ");   //�E�v�I���N����
            l_sbWhere.append(" and amount_from < ? ");  //��������iFROM�j
            l_sbWhere.append(" and amount_to >= ? ");   //��������iTO�j

            if (l_strCostDiv == null)
            {
                //[�i���o��敪 == null�j�̏ꍇ�̏���] 
                //�C�O���o��}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And�@@
                //�C�O���o��}�X�^.�s��R�[�h = �s��R�[�h And�@@ 
                //�C�O���o��}�X�^.�E�v�J�n�N���� <= ��� And 
                //�C�O���o��}�X�^.�E�v�I���N���� >= ��� And 
                //�C�O���o��}�X�^.��������iFROM�j < ������� And 
                //�C�O���o��}�X�^.��������iTO�j >= ������� 
                //�C�O���o��}�X�^.�����敪 = �����敪
                //�P�j�@@�C�O���o��}�X�^�e�[�u������
                //�����敪
                l_sbWhere.append(" and side_div = ? ");

                l_strBindValue = new Object[7];
                l_strBindValue[0] = l_strInstitutionCode;
                l_strBindValue[1] = l_strMarketCode;
                l_strBindValue[2] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[3] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[4] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[5] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[6] = l_strSideDiv;
            }
            else
            {
                //[�i���o��敪 != null�j�̏ꍇ�̏���] 
                //�C�O���o��}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And�@@
                //�C�O���o��}�X�^.�s��R�[�h = �s��R�[�h And�@@
                //�C�O���o��}�X�^.�E�v�J�n�N���� <= ��� And 
                //�C�O���o��}�X�^.�E�v�I���N���� >= ��� And 
                //�C�O���o��}�X�^.��������iFROM�j < ������� And 
                //�C�O���o��}�X�^.��������iTO�j >= ������� And 
                //�C�O���o��}�X�^.���o��敪 = ���o��敪 
                //�C�O���o��}�X�^.�����敪 = �����敪
                //�P�j�C�O���o��}�X�^�e�[�u������
                l_sbWhere.append(" and cost_div = ? ");   //���o��敪
                //�����敪
                l_sbWhere.append(" and side_div = ? ");

                l_strBindValue = new Object[8];
                l_strBindValue[0] = l_strInstitutionCode;
                l_strBindValue[1] = l_strMarketCode;
                l_strBindValue[2] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[3] = 
                    WEB3DateUtility.formatDate(l_datOrderBizDate, "yyyyMMdd");
                l_strBindValue[4] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[5] = new Double (l_dblForeignTradeAmount); 
                l_strBindValue[6] = l_strCostDiv;
                l_strBindValue[7] = l_strSideDiv;
            }

            l_lstreturnForeignCost = 
                processor.doFindAllQuery(
                    ForeignCostRow.TYPE,
                    l_sbWhere.toString(),
                    l_strBindValue);

            int l_intForeignCostSize = l_lstreturnForeignCost.size(); 

            ForeignCostParams[] l_foreignCostParams =
                new ForeignCostParams[l_intForeignCostSize];
            for(int i = 0; i < l_intForeignCostSize; i++)
            {
                l_foreignCostParams[i] = new ForeignCostParams((ForeignCostRow)l_lstreturnForeignCost.get(i));
            }

            // ���ꏔ�o��敪�̃��R�[�h�������s�擾���ꂽ�ꍇ�͗�O���X���[����B
            // ���u����敪�̕����s�̃��R�[�h���I������܂����v
            int l_intCounter01 = 0;
            int l_intCounter02 = 0;
            int l_intCounter03 = 0;
            int l_intCounter04 = 0;

            for (int i = 0; i < l_intForeignCostSize; i++)
            {
                if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter01++;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter02++;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter03++;
                }
                else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(
                    l_foreignCostParams[i].getCostDiv()))
                {
                    l_intCounter04++;
                }

                if (l_intCounter01 > 1 || l_intCounter02 >1 || l_intCounter03 > 1 || l_intCounter04 > 1)
                {
                    log.error("����敪�̕����s�̃��R�[�h���I������܂���");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03209,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "����敪�̕����s�̃��R�[�h���I������܂���");
                }
            }

            // �R�j��������p�C�O���o��}�X�^����
            // �ȉ��̏����ɂāA��������p�C�O���o��}�X�^�e�[�u�����������A
            // �擾������������p�C�O���o��}�X�^�s�I�u�W�F�N�g��z��B 
            List l_lstProductForeignCost = null;
            Object[] l_arrayBindValue = null;
            StringBuffer l_sbQuery = new StringBuffer();

            // ��������p�C�O���o��}�X�^.����ID = ����ID And
            // ��������p�C�O���o��}�X�^.���n�����R�[�h = ���n�����R�[�h And
            // ��������p�C�O���o��}�X�^.�،���ЃR�[�h = �،���ЃR�[�h And
            // ��������p�C�O���o��}�X�^.�s��R�[�h = �s��R�[�h And
            // ��������p�C�O���o��}�X�^.�E�v�J�n�N���� <= ��� And
            // ��������p�C�O���o��}�X�^.�E�v�I���N���� >= ��� And
            // ��������p�C�O���o��}�X�^.��������iFROM�j < ������� And
            // ��������p�C�O���o��}�X�^.��������iTO�j >= ������� And
            l_sbQuery.append("product_id = ?");
            l_sbQuery.append(" and offshore_product_code = ?");
            l_sbQuery.append(" and institution_code = ?");
            l_sbQuery.append(" and market_code = ?");
            l_sbQuery.append(" and to_char(appli_start_date, 'yyyyMMdd') <= ?");
            l_sbQuery.append(" and to_char(appli_end_date, 'yyyyMMdd') >= ?");
            l_sbQuery.append(" and amount_from < ?");
            l_sbQuery.append(" and amount_to >= ?");

            if (l_strCostDiv == null)
            {
                // [�i���o��敪 == null�j�̏ꍇ�̏���]
                // ��������p�C�O���o��}�X�^.�����敪 = �����敪
                l_sbQuery.append(" and side_div = ?");

                l_arrayBindValue = new Object[9];
                l_arrayBindValue[0] = l_lngProductId;
                l_arrayBindValue[1] = l_strOffshoreProductCode;
                l_arrayBindValue[2] = l_strInstitutionCode;
                l_arrayBindValue[3] = l_strMarketCode;
                l_arrayBindValue[4] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[5] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[6] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[7] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[8] = l_strSideDiv;
            }
            else
            {
                // [�i���o��敪 != null�j�̏ꍇ�̏���]
                // ��������p�C�O���o��}�X�^.���o��敪 = ���o��敪 And
                // ��������p�C�O���o��}�X�^.�����敪 = �����敪
                l_sbQuery.append(" and cost_div = ?");
                l_sbQuery.append(" and side_div = ?");

                l_arrayBindValue = new Object[10];
                l_arrayBindValue[0] = l_lngProductId;
                l_arrayBindValue[1] = l_strOffshoreProductCode;
                l_arrayBindValue[2] = l_strInstitutionCode;
                l_arrayBindValue[3] = l_strMarketCode;
                l_arrayBindValue[4] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[5] = WEB3DateUtility.formatDate(l_datOrderBizDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                l_arrayBindValue[6] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[7] = new Double(l_dblForeignTradeAmount);
                l_arrayBindValue[8] = l_strCostDiv;
                l_arrayBindValue[9] = l_strSideDiv;
            }

            l_lstProductForeignCost =
                processor.doFindAllQuery(
                    SpecialProductForeignCostParams.TYPE,
                    l_sbQuery.toString(),
                    l_arrayBindValue);

            if (l_lstProductForeignCost.isEmpty())
            {
                // �S�j��L�R�j�ɂē�������p�C�O���o��}�X�^�s�̔z����擾���Ȃ��ꍇ�A
                //     �Q�j�擾�����C�O���o��}�X�^�s�̔z���ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_foreignCostParams;
            }
            else
            {
                // ���ꏔ�o��敪�̃��R�[�h�������s�擾���ꂽ�ꍇ�͗�O���X���[����B
                // ���u����敪�̕����s�̃��R�[�h���I������܂����v
                l_intCounter01 = 0;
                l_intCounter02 = 0;
                l_intCounter03 = 0;
                l_intCounter04 = 0;

                for (int i = 0; i < l_lstProductForeignCost.size(); i++)
                {
                    SpecialProductForeignCostParams l_productForeignCostParams =
                        (SpecialProductForeignCostParams)l_lstProductForeignCost.get(i);
                    
                    if (WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter01++;
                    }
                    else if (WEB3FeqCostDivDef.FOREIGN_TAX.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter02++;
                    }
                    else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT1.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter03++;
                    }
                    else if (WEB3FeqCostDivDef.FOREIGN_FEE_EXT2.equals(
                        l_productForeignCostParams.getCostDiv()))
                    {
                        l_intCounter04++;
                    }

                    if (l_intCounter01 > 1 || l_intCounter02 >1 || l_intCounter03 > 1 || l_intCounter04 > 1)
                    {
                        log.error("����敪�̕����s�̃��R�[�h���I������܂���");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_03209,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            "����敪�̕����s�̃��R�[�h���I������܂���");
                    }
                }

                // �T�j��L�R�j�ɂē�������p�C�O���o��}�X�^�s�̔z����擾�����ꍇ�A
                //     �e��������p�C�O���o��}�X�^�s��Loop�������s���B
                for (int i = 0; i < l_lstProductForeignCost.size(); i++)
                {
                    SpecialProductForeignCostParams l_productForeignCostParams =
                        (SpecialProductForeignCostParams)l_lstProductForeignCost.get(i);

                    for (int j = 0; j < l_foreignCostParams.length; j++)
                    {
                        // �T�|�P�j��������p�C�O���o��}�X�^.���o��敪�͂Q�j�擾����
                        //     �z��̊e�C�O���o��}�X�^�s.���o��敪�ƈ�v����ꍇ�A�ȉ��̑�����s���B
                        // �T�|�Q�j�u����ID�v�ȊO�̃t�B�[���h�A�C�O���o��}�X�^�s���������p�C�O���o��}�X�^�s�ɕt���ւ���B
                        if (l_productForeignCostParams.getCostDiv().equals(
                            l_foreignCostParams[j].getCostDiv()))
                        {
                            l_foreignCostParams[j].setAppliStartDate(
                                l_productForeignCostParams.getAppliStartDate());
                            l_foreignCostParams[j].setAppliEndDate(
                                l_productForeignCostParams.getAppliEndDate());
                            l_foreignCostParams[j].setAmountFrom(
                                l_productForeignCostParams.getAmountFrom());
                            l_foreignCostParams[j].setAmountTo(
                                l_productForeignCostParams.getAmountTo());
                            l_foreignCostParams[j].setCommisionRate(
                                l_productForeignCostParams.getCommisionRate());
                            l_foreignCostParams[j].setAddAmount(
                                l_productForeignCostParams.getAddAmount());
                            l_foreignCostParams[j].setScale(
                                l_productForeignCostParams.getScale());
                            l_foreignCostParams[j].setRoundDiv(
                                l_productForeignCostParams.getRoundDiv());
                            l_foreignCostParams[j].setLastUpdater(
                                l_productForeignCostParams.getLastUpdater());
                            l_foreignCostParams[j].setCreatedTimestamp(
                                l_productForeignCostParams.getCreatedTimestamp());
                            l_foreignCostParams[j].setLastUpdatedTimestamp(
                                l_productForeignCostParams.getLastUpdatedTimestamp());
                        }
                    }
                }

                // �U�j�t���ւ����̊C�O���o��}�X�^�s�̔z���ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_foreignCostParams;
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
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
   
    /**
     * (calc�O���������z) <BR>
     * �O���������z�v�Z���s���B <BR>
     *  <BR>
     * �@@�E���n�萔�� <BR>
     * �@@�E���n����� <BR>
     * �@@�E���̑��R�X�g�P <BR>
     * �@@�E���̑��R�X�g�Q <BR>
     * �@@�E���n���Z��� <BR>
     * �@@�E���n���Z����i�~�݁j <BR>
     * �@@�E�ϑ��萔�� <BR>
     * �@@�E�ϑ��萔���i�O�݁j <BR>
     * �@@�E�ϑ��萔������� <BR>
     * �@@�E�ϑ��萔������Łi�O�݁j <BR>
     * �@@�E��n��� <BR>
     * �@@�E��n����i�O�݁j <BR>
     *  <BR>
     * �P�j�@@�������擾 <BR>
     * �@@��������w��inull�j�̏ꍇ�A������ԊǗ�.get������() <BR>
     * �@@�ɂĔ��������擾���A����Ƃ���B <BR>
     *  <BR>
     * �Q�j�@@�萔���I�u�W�F�N�g�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B<BR>
     *  <BR>
     * �@@�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode() <BR>
     * �@@���XID�F �⏕����.get����X().BranchId() <BR>
     * �@@�萔�����i�R�[�h�F this.get�萔�����i�R�[�h() <BR>
     * �@@����R�[�h�iSONAR�j�F�@@this.get����R�[�h�iSONAR�j() <BR>
     * �@@�������F�@@������ <BR>
     * �@@�ٍϋ敪�F�@@�ٍϋ敪.00�F���̑�  <BR>
     * �@@�����`���l���F �����`���l�� <BR>
     * �@@is�w�l�F is�w�l���� <BR>
     * �@@�s��R�[�h�iSONAR�j�F�@@�s��.�s��R�[�h�iSONAR�j <BR>
     *  <BR>
     * �R�j�@@�O���������z�v�Z <BR>
     * �@@this.calc�O���������z(�萔��, �⏕����, �O����������, �s��,  <BR>
     * �@@���, ��������i�O�݁j, �בփ��[�g, is���t, is���v�Z, is�w�l)��<BR>
     * �@@�Ϗ��ideligate�j����B <BR>
     *  <BR>
     * �S�j�@@�O���������z�v�Z���ʃI�u�W�F�N�g�ԋp <BR>
     * �@@�O���������z�v�Z���ʃI�u�W�F�N�g��ԋp����B <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_feqProduct - �O����������
     * @@param l_market - �s��
     * @@param l_datBaseDate - ���
     * @@param l_dblTradePriceFc - ��������i�O�݁j
     * @@param l_dblFxRate - �בփ��[�g
     * �i�� 0�w��̏ꍇ�́A�ʉ݃e�[�u���̈בփ��[�g�j
     * @@param l_blnIsBuy - (is���t) 
     * ���t���̔��� <BR>
     *  <BR>
     * true�F�� <BR>
     * false�F�� <BR>
     * @@param l_blnIsExecCalc - (is���v�Z)
     * ���v�Z���̔��� <BR>
     *  <BR>
     * true�F���v�Z <BR>
     * false�F�T�Z�v�Z <BR>
     * @@param l_blnIsLimitPrice - (is�w�l)
     * �w�l���̔��� <BR>
     *  <BR>
     * true�F�w�l <BR>
     * false�F���s <BR>
     * @@param l_strOrderChannel �����`���l��
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42898CEF00EA
     */
    public WEB3FeqAmountCalcResult calcFeqAmount(
        WEB3GentradeSubAccount l_subAccount,
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        Date l_datExecDate,
        double l_dblTradePriceFc,
        double l_dblFxRate,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc,
        boolean l_blnIsLimitPrice,
        String l_strOrderChannel) throws WEB3BaseException
    {
        
        String STR_METHOD_NAME = 
            "calcFeqAmount(WEB3GentradeSubAccount, WEB3FeqProduct, " + 
            "WEB3GentradeMarket, Date, double, double, boolean, " + 
            "boolean, boolean, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_feqProduct == null || l_market == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //�P�j�������擾 
        //��������w��inull�j�̏ꍇ�A 
        //������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }

        log.debug("�������F[" + l_datOrderBizDate + "]");
        log.debug("�����F[" + l_datExecDate + "]");
        //�Q�j�萔���I�u�W�F�N�g�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B  

        WEB3GentradeCommission l_gentradeCommission 
            = new WEB3GentradeCommission();

        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode() 
        l_gentradeCommission.setInstitutionCode(
            l_subAccount.getInstitution().getInstitutionCode());

        //���XID�F �⏕����.get����X().BranchId() 
        l_gentradeCommission.setBranchId(
            l_subAccount.getMainAccount().getBranch().getBranchId());

        //�萔�����i�R�[�h�F this.get�萔�����i�R�[�h()
        l_gentradeCommission.setCommissionProductCode(
            this.getCommissionProductCode());

        //����R�[�h�iSONAR�j�F�@@this.get����R�[�h�iSONAR�j()
        l_gentradeCommission.setSonarTradedCode(this.getSonarTradedCode(l_blnIsBuy));

        //�������F�@@������
        l_gentradeCommission.setOrderBizDate(
            new Timestamp(l_datOrderBizDate.getTime()));

        //�ٍϋ敪�F�@@�ٍϋ敪.00�F���̑�
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);

        //�����`���l���F �����`���l��
        l_gentradeCommission.setOrderChannel(l_strOrderChannel);

        //is�w�l�F is�w�l����
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);

        //�s��R�[�h�iSONAR�j�F�@@�s��.�s��R�[�h�iSONAR�j 
        MarketRow l_marketRow = (MarketRow) (l_market.getDataSourceObject());       
        l_gentradeCommission.setSonarMarketCode(
            l_marketRow.getSonarMarketCode());

        //�R�j�O���������z�v�Z
        //this.calc�O���������z(�萔��, �⏕����, �O����������, �s��, 
        //���, ��������i�O�݁j, �בփ��[�g, is���t, is���v�Z, is�w�l)��
        //�Ϗ��ideligate�j����B
        WEB3FeqAmountCalcResult l_calcResult = 
            this.calcFeqAmount(
                l_gentradeCommission,
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datExecDate,
                l_dblTradePriceFc,
                l_dblFxRate,
                l_blnIsBuy,
                l_blnIsExecCalc,
                l_blnIsLimitPrice);

        //�S�j�O���������z�v�Z���ʃI�u�W�F�N�g�ԋp
        //�O���������z�v�Z���ʃI�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (�O���������z) <BR>
     * �O���������z�v�Z���s���B <BR>
     *  <BR>
     * �@@�E���n�萔�� <BR>
     * �@@�E���n����� <BR>
     * �@@�E���̑��R�X�g�P <BR>
     * �@@�E���̑��R�X�g�Q <BR>
     * �@@�E���n���Z��� <BR>
     * �@@�E���n���Z����i�~�݁j <BR>
     * �@@�E�ϑ��萔�� <BR>
     * �@@�E�ϑ��萔���i�O�݁j <BR>
     * �@@�E�ϑ��萔������� <BR>
     * �@@�E�ϑ��萔������Łi�O�݁j <BR>
     * �@@�E��n��� <BR>
     * �@@�E��n����i�O�݁j <BR>
     *  <BR>
     * �V�[�P���X�} <BR>
     * �u�i�O���v�Z�jcalc�O���������z�v�Q�� <BR>
     * @@param l_commission - (�萔��) 
     * �萔���I�u�W�F�N�g
     * @@param l_subAccount - �⏕����
     * @@param l_feqProduct - �O����������
     * @@param l_market - �s��
     * @@param l_datBaseDate - ���
     * @@param l_dblTradePriceFc - ��������i�O�݁j
     * @@param l_dblFxRate - (�בփ��[�g) 
     * �i�� 0�w��̏ꍇ�́A�ʉ݃e�[�u���̈בփ��[�g�j
     * @@param l_blnIsBuy - (is���t)
     * ���t���̔��� <BR>
     *  <BR>
     * true�F�� <BR>
     * false�F�� <BR>
     * @@param l_blnIsExecCalc - (is���v�Z)
     * ���v�Z���̔��� <BR>
     *  <BR>
     * true�F���v�Z <BR>
     * false�F�T�Z�v�Z <BR>
     * @@param l_blnIsLimitPrice - (is�w�l)
     * �w�l���̔��� <BR>
     *  <BR>
     * true�F�w�l <BR>
     * false�F���s <BR>
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42A5359F026F
     */
    protected WEB3FeqAmountCalcResult calcFeqAmount(
        WEB3GentradeCommission l_commission,
        WEB3GentradeSubAccount l_subAccount,
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        double l_dblTradePriceFc,
        double l_dblFxRate,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc,
        boolean l_blnIsLimitPrice) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcFeqAmount(WEB3GentradeCommission, " +
            "WEB3GentradeSubAccount, WEB3FeqProduct, WEB3GentradeMarket, " +
            "Date, double, double, boolean, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_feqProduct == null || l_market == null || l_commission == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 �v�Z���ʃI�u�W�F�N�g�𐶐�����B 
        WEB3FeqAmountCalcResult l_calcResult = new WEB3FeqAmountCalcResult();

        //1.2 ��������i�O�݁j���Z�b�g����B 
        l_calcResult.setTradePriceFc(l_dblTradePriceFc);

        //1.3 �ʉ݃I�u�W�F�N�g���擾����B 
        WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();

        //1.4 �בփ��[�g���擾����B 
        double l_dblNewFxRate = 
            l_genCurrency.getExchangeRate(l_blnIsBuy, l_blnIsExecCalc, l_dblFxRate);
        log.debug("�בփ��[�g = " + l_dblNewFxRate);

        //1.5 �~�݊��Z�ۂߕ������擾���� 
        String l_strJPYRoundDiv = l_genCurrency.getChangeJpyRoundDiv();
        log.debug("�~�݊��Z�ۂߕ��� = " + l_strJPYRoundDiv);

        //1.6 ���n��������i�~�݁j���v�Z����B 
        double l_dblJPYAmount = 
            this.calcJPYAmount(l_dblTradePriceFc, l_dblNewFxRate, l_strJPYRoundDiv);
        log.debug("���n��������i�~�݁j = " + l_dblJPYAmount);
        
        //1.7 ��������i�~�݁j���Z�b�g����B
        l_calcResult.setTradePrice(l_dblJPYAmount);
        
        //1.8 get�،���ЃR�[�h( )
        String l_strInstitutionCod = l_genCurrency.getInstitutionCode();
        
        //1.9 �C�O���o��C���n���Z������擾����B 
        WEB3FeqForeignCost l_foreignCost = 
            this.calcForeignCost(
                new Long(l_feqProduct.getProductId()),
                l_feqProduct.getOffshoreProductCode(),
                l_strInstitutionCod, 
                l_market.getMarketCode(), 
                l_dblTradePriceFc, 
                l_datBaseDate, 
                l_blnIsBuy);
            
        //1.10 ���n���o����Z�b�g����B 
        l_calcResult.setForeignCost(l_foreignCost);
        
        //1.11 ���n���Z������擾����B
        double l_dblBalanceAmount = l_calcResult.getBalanceAmountFc();
        log.debug("���n���Z��� = " + l_dblBalanceAmount);
        
        //     ���n���Z������O�ȉ��̏ꍇ�G���[�Ƃ���B
        if (l_dblBalanceAmount <= 0)
        {
            throw new WEB3BusinessLayerException(
            WEB3ErrorCatalog.BUSINESS_ERROR_02295,
            this.getClass() + "." + STR_METHOD_NAME,
            "���n���t��������n���o���������Ă��܂��B");
        }
        
        //1.12 ���n���Z����i�~�݁j���v�Z����B 
        double l_dblJPYBalanceAmount = 
            this.calcJPYAmount(l_dblBalanceAmount, l_dblNewFxRate, l_strJPYRoundDiv);
        log.debug("���n���Z����i�~�݁j = " + l_dblJPYBalanceAmount);
        
        //1.13 ���n���Z����i�~�݁j���Z�b�g����B
        l_calcResult.setBalanceAmount(l_dblJPYBalanceAmount);
        
        //1.14 ���o��v�Z�p������Z�b�g����B
        l_commission.setExpensesCalcAmount(l_dblJPYBalanceAmount);
        
        //1.15 �ϑ��萔�����v�Z����B 
        this.calcCommission(l_commission, l_subAccount);
        
        //1.16 �ϑ��萔�����擾����B
        double l_dblCommission = l_commission.getCommission();
        log.debug("�ϑ��萔�� = " + l_dblCommission);
        
        //1.17 �ϑ��萔�����Z�b�g����B 
        l_calcResult.setCommissionFee(l_dblCommission);
        
        //1.18 �萔���m�����擾����
        String  l_strCommissionNo = l_commission.getCommissionNo();
        log.debug("�萔���m�� = " + l_strCommissionNo);
        
        //1.19 �萔���m�����Z�b�g����B 
        l_calcResult.setCommissionNumber(l_strCommissionNo);

        //1.20 get�萔��No�}��
        String l_strCommissionBranchNumber = l_commission.getCommissionRevNo();

        //1.21 set�萔��No�}��(String)
        l_calcResult.setCommissionBranchNumber(l_strCommissionBranchNumber);
        log.debug("�萔��No�}�� = " + l_strCommissionBranchNumber);
        
        //1.22 �O�݊��Z�ۂߕ������擾����
        String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
        log.debug("�O�݊��Z�ۂߕ��� = " + l_strFCCYRoundDiv);
        
        //1.23 �������������擾����B
        int l_intScale = l_genCurrency.getScale();
        log.debug("���������� = " + l_intScale);
        
        //1.24 �ϑ��萔���i�O�݁j���v�Z����B 
        double l_dblForeignCCYAmount = 
            this.calcForeignCCYAmount(
                l_dblCommission, 
                l_dblNewFxRate, 
                l_intScale, 
                l_strFCCYRoundDiv);
        log.debug("�ϑ��萔���i�O�݁j = " + l_dblForeignCCYAmount);

        //1.25 �ϑ��萔���i�O�݁j���Z�b�g����B 
        l_calcResult.setCommissionFeeFc(l_dblForeignCCYAmount);

        //1.26 �ϑ��萔������ł��v�Z����B 
        double l_dblSalesTax = 
            this.calcSalesTax(l_dblCommission, l_datBaseDate, l_subAccount);
        log.debug("�ϑ��萔������� = " + l_dblSalesTax);

        //1.27 �ϑ��萔������ł��Z�b�g����B
        l_calcResult.setCommissionFeeTax(l_dblSalesTax);

        //1.28 �ϑ��萔������Łi�O�݁j���v�Z����B 
        double l_dblForeignSalesTax = 
            this.calcForeignCCYAmount(
                l_dblSalesTax, 
                l_dblNewFxRate,
                l_intScale, 
                l_strFCCYRoundDiv);
        log.debug("�ϑ��萔������Łi�O�݁j = " + l_dblForeignSalesTax);

        //1.29 �ϑ��萔������Łi�O�݁j���Z�b�g����B 
        l_calcResult.setCommissionFeeTaxFc(l_dblForeignSalesTax);
        //��n����v�Z���Z�b�g����B 
        //[set��n���()�Ɏw�肷�����] 
        //�����iis���t==true�j�̏ꍇ�A�ȉ��̌v�Z���� 
        //�@@���n���Z����i�~�݁j�{�ϑ��萔���{�ϑ��萔������� 
        //�����iis���t==false�j�̏ꍇ�A�ȉ��̌v�Z���� 
        //�@@���n���Z����i�~�݁j�|�ϑ��萔���|�ϑ��萔�������
        
        //[set��n����i�O�݁j()�Ɏw�肷�����] 
        //�����t�iis���t==true�j�̏ꍇ�A�ȉ��̌v�Z���� 
        // ���Z����i�O�݁j�{�ϑ��萔���i�O�݁j�{�ϑ��萔������Łi�O�݁j 
        //�����t�iis���t==false�j�̏ꍇ�A�ȉ��̌v�Z���� 
        //���Z����i�O�݁j�|�ϑ��萔���i�O�݁j�|�ϑ��萔������Łi�O�݁j

        if (l_blnIsBuy)
        {
            //1.30 set��n���(double)
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    WEB3FeqOrderUtility.decimalPlus(
                        l_dblJPYBalanceAmount, 
                        l_dblCommission), 
                    l_dblSalesTax);
            l_calcResult.setNetAmount(l_dblTemp);
            //1.31 set��n����i�O�݁j(double)
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalPlus(
                    WEB3FeqOrderUtility.decimalPlus(
                        l_dblBalanceAmount, 
                        l_dblForeignCCYAmount), 
                    l_dblForeignSalesTax);
            l_calcResult.setNetAmountFc(l_dblTemp);
        }
        else
        {
            //1.30 set��n���(double)
            double l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    WEB3FeqOrderUtility.decimalMinus(
                        l_dblJPYBalanceAmount, 
                        l_dblCommission), 
                    l_dblSalesTax);
            l_calcResult.setNetAmount(l_dblTemp);
            //1.31 set��n����i�O�݁j(double)
            l_dblTemp = 
                WEB3FeqOrderUtility.decimalMinus(
                    WEB3FeqOrderUtility.decimalMinus(
                        l_dblBalanceAmount, 
                        l_dblForeignCCYAmount), 
                    l_dblForeignSalesTax);
            l_calcResult.setNetAmountFc(l_dblTemp);
        }
        log.debug("��n��� = " + l_calcResult.getNetAmount());
        log.debug("��n����i�O�݁j = " + l_calcResult.getNetAmountFc());

        double l_dblChargeRatio =
            this.getChargeRatio(
                l_commission.getInstitutionCode(),
                l_commission.getBranchId(),
                l_subAccount.getAccountId(),
                l_commission.getOrderBizDate());
        l_calcResult.setChargeRatio(l_dblChargeRatio);
        log.debug("������ = " + l_calcResult.getChargeRatio());
        
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (�ő���v�f�擾) <BR>
     * �ő���v�f�A�m����(n)�n��������i�O�݁j�A<BR>
     * ����������v�A��������i�O�݁j���v���擾�B <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s)
     * @@param l_currency �ʉ�
     * @@param l_dblTradePrices�m����(n)�n�������
     * @@param l_dblTradePriceFcs�m����(n)�n��������i�O�݁j
     * @@param l_dblTotals ����������v
     * index0 �~�݁@@index1 �O��
     * @@return int �ő���v�f
     * @@exception WEB3BaseException
     */
    public int calcMaxExecIndex(
        FeqFinTransactionParams[] l_feqFinTransactionParams,
        WEB3GentradeCurrency l_currency,
        double[] l_dblTradePrices,
        double[] l_dblTradePriceFcs,
        double[] l_dblTotals) 
        throws WEB3BaseException, DataQueryException, DataNetworkException
    {
        String STR_METHOD_NAME = 
            "calcMaxExecIndex(FeqFinTransactionParams[], WEB3GentradeCurrency, double[], double[], double[])";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null || 
            l_feqFinTransactionParams.length ==0 ||
            l_dblTotals == null ||
            l_dblTotals.length != 2 ||
            l_dblTradePriceFcs  == null ||
            l_dblTradePrices  == null ||
            l_dblTradePriceFcs.length != l_feqFinTransactionParams.length ||
            l_dblTradePrices.length != l_feqFinTransactionParams.length)             
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        double l_dblMaxTradePriceFc = 0.0D;
        double l_dblMaxPrice = 0.0D;
        long l_lngMaxExecSerialNo = 0L;
        Timestamp l_lngMaxExecTimestamp = null;
        Timestamp l_tsMaxFinTransactionTimestamp = null;
        
        int l_intMax = 0;

        l_dblTotals[0] = 0.0D;
        l_dblTotals[1] = 0.0D;

        for (int i = 0; i < l_feqFinTransactionParams.length; i++)
        {
            //�������
            double l_dblTradePrice = 0.0D;
            //�P��
            double l_dblPrice = l_feqFinTransactionParams[i].getPrice();

            //��������i�O�݁j
            double l_dblTradePriceFc =
                l_feqFinTransactionParams[i].getQuantity() * l_dblPrice;
            BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblTradePriceFc);
            int l_intDecimalPlace = l_currency.getScale();
            l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            l_dblTradePriceFc = l_bdTradePriceFc.doubleValue();
            l_dblTradePrice = 
                this.calcJPYAmount(
                    l_dblTradePriceFc,
                    l_feqFinTransactionParams[i].getFxRate(),
                    l_currency.getChangeJpyRoundDiv());

            //�m����(n)�n�������
            l_dblTradePrices[i] = l_dblTradePrice;
            //�m����(n)�n��������i�O�݁j
            l_dblTradePriceFcs[i] = l_dblTradePriceFc;

            long l_lngExecutionId = 
                l_feqFinTransactionParams[i].getOrderExecutionId();
            
            WEB3FeqOrderExecution l_orderExecution = null;
            
            //���I�u�W�F�N�g�����݂���
            boolean l_isFoundData = true;

            try
            {
                l_orderExecution = new WEB3FeqOrderExecution(l_lngExecutionId); 
            }
            catch (DataFindException e)
            {
                l_isFoundData = false;
            }
            
            if (l_orderExecution == null)
            {
                l_isFoundData = false;
            }
            //���ʔ�
            long l_lngExecSerialNo = 0L;
            //������
            Timestamp l_tsExecTimestamp = null; 
            //�g�����U�N�V����(������薾��)�s.�g�����U�N�V������������
            Timestamp l_tsFinTransactionTimestamp = null;
            
            if (l_isFoundData)
            {
                l_lngExecSerialNo = l_orderExecution.getExecutionSerialNo();
                l_tsExecTimestamp = l_orderExecution.getExecutionTimestamp();
            }
            else
            {
                l_tsFinTransactionTimestamp =
                    l_feqFinTransactionParams[i].getFinTransactionTimestamp();
            }
            
            log.debug("������� = " + l_dblTradePrice);
            log.debug("��������i�O�݁j = " + l_dblTradePriceFc);

            //�A�@@�ő���v�f�̔���
            //����.�g�����U�N�V�����i������薾�ׁj�s[]�̊e�v�f�̂����A�ȉ��̏����𖞂������̂��ő���Ƃ���B
            //�i�ő���̔���j
            //�|�@@��������i�@@�Ōv�Z����[�����in�j]��������j���ő�̖����ő���Ƃ���B
            //�|�@@��������i�@@�Ōv�Z����[�����in�j]��������j�����z�̏ꍇ�A
            //���P���i�g�����U�N�V�����i������薾�ׁj�s.���P���j�����������ő���Ƃ���B
            //�|�@@���P�������z�̏ꍇ�A
            //���i�g�����U�N�V�����i������薾�ׁj�s.getOrderExecutionId()��
            //�Y��������I�u�W�F�N�g�j.��菇�ԍ��A�܂��͖��������傫�������ő���Ƃ���B
            //�����I�u�W�F�N�g�����݂��Ȃ��ꍇ�́A
            //�g�����U�N�V����(������薾��)�s.�g�����U�N�V���������������傫���ق���
            //�ő���Ƃ���B
            if (l_dblTradePriceFc > l_dblMaxTradePriceFc)
            {
                l_dblMaxTradePriceFc = l_dblTradePriceFc;
                l_dblMaxPrice = l_dblPrice;
                l_lngMaxExecSerialNo = l_lngExecSerialNo;
                l_lngMaxExecTimestamp = l_tsExecTimestamp;
                l_tsMaxFinTransactionTimestamp = null;
                    
                l_intMax = i;
            }
            else if (l_dblTradePriceFc == l_dblMaxTradePriceFc)
            {
                if (l_dblPrice > l_dblMaxPrice)
                {
                    l_dblMaxTradePriceFc = l_dblTradePriceFc;
                    l_dblMaxPrice = l_dblPrice;
                    l_lngMaxExecSerialNo = l_lngExecSerialNo;
                    l_lngMaxExecTimestamp = l_tsExecTimestamp;
                    l_tsMaxFinTransactionTimestamp = null;

                    l_intMax = i;
                }
                else if (l_dblMaxPrice == l_dblPrice)
                {
                    //���I�u�W�F�N�g�����݂���ꍇ
                    if (l_isFoundData)
                    {
                        if (l_lngExecSerialNo > l_lngMaxExecSerialNo)
                        {
                            l_dblMaxTradePriceFc = l_dblTradePriceFc;
                            l_dblMaxPrice = l_dblPrice;
                            l_lngMaxExecSerialNo = l_lngExecSerialNo;
                            l_lngMaxExecTimestamp = l_tsExecTimestamp;
                            l_tsMaxFinTransactionTimestamp = null;
                    
                            l_intMax = i;
                        }
                        else if (l_lngExecSerialNo == l_lngMaxExecSerialNo)
                        {
                            if (WEB3DateUtility.compareToSecond(l_tsExecTimestamp, l_lngMaxExecTimestamp) > 0)
                            {
                                l_dblMaxTradePriceFc = l_dblTradePriceFc;
                                l_dblMaxPrice = l_dblPrice;
                                l_lngMaxExecSerialNo = l_lngExecSerialNo;
                                l_lngMaxExecTimestamp = l_tsExecTimestamp;
                                l_tsMaxFinTransactionTimestamp = null;
                                
                                l_intMax = i;
                            }
                        }
                    }
                    else
                    {
                        //�g�����U�N�V���������������傫���ق����ő���Ƃ���
                        if (WEB3DateUtility.compareToSecond(l_tsFinTransactionTimestamp, l_tsMaxFinTransactionTimestamp) > 0)
                        {
                            l_dblMaxTradePriceFc = l_dblTradePriceFc;
                            l_dblMaxPrice = l_dblPrice;
                            l_lngMaxExecSerialNo = 0;
                            l_lngMaxExecTimestamp = null;
                            l_tsMaxFinTransactionTimestamp = l_tsFinTransactionTimestamp;
                            
                            l_intMax = i;
                        }
                    }
                }
            }

            //�B�@@[���@@�v]����������v�Z����
            //�@@�̌v�Z���ʂ̍��v�l��[���@@�v]��������Ƃ���B
            l_dblTotals[0] = 
                WEB3FeqOrderUtility.decimalPlus(l_dblTotals[0], l_dblTradePrice);
            l_dblTotals[1] = 
                WEB3FeqOrderUtility.decimalPlus(l_dblTotals[1], l_dblTradePriceFc);
        }

        log.exiting(STR_METHOD_NAME);
        return l_intMax;
    }

    /**
     * (calc�O���������z���v)<BR>
     * �O���������z���v���擾�B<BR>
     * @@param l_feqFinTransactionParams �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g
     * @@param l_feqProduct �O����������
     * @@param l_orderManager �����}�l�[�W��
     * @@param l_accountManager �g���A�J�E���g�}�l�[�W��
     * @@param l_market �s��
     * @@param l_datBaseDate ���
     * @@return l_dblTradePriceFcTotal ��������i�O�݁j���v
     * @@exception WEB3BaseException
     */
    public WEB3FeqAmountCalcResult calcFeqAmountTotal(
        FeqFinTransactionParams l_feqFinTransactionParams,
        WEB3FeqProduct l_feqProduct,
        WEB3FeqOrderManager l_orderManager,
        WEB3GentradeAccountManager l_accountManager,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        Date l_ExecDate,
        double l_dblTradePriceFcTotal 
        ) throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = 
            "calcFeqAmountTotal(FeqFinTransactionParams, WEB3FeqProduct, " + 
            "WEB3FeqOrderManager, WEB3GentradeAccountManager, WEB3GentradeMarket, Date, double)";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null || 
            l_feqProduct == null ||
            l_orderManager == null ||
            l_accountManager == null ||
            l_ExecDate == null ||
            l_market == null)             
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�C�@@�m���@@ �v�n�O���������z�i���o��j���v�Z����
        //�O�������v�Z�T�[�r�X.calc�O���������z()�ɂČv�Z�����l��[���@@�v]�v�Z���ʂƂ���B
        //[calc�O���������z()�Ɏw�肷�����]
        //�⏕�����F�@@�g�����U�N�V�����i������薾�ׁj�s.getSubAccountId()�ɊY������⏕����
        long l_lngSubAccountId = 
            l_feqFinTransactionParams.getSubAccountId();
        long l_lngAccountId = l_feqFinTransactionParams.getAccountId();
        WEB3GentradeSubAccount l_subAccount = 
            (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);

        //��������i�O�݁j�F�@@�A[�� �v]�������
        //�בփ��[�g�F�@@0�i�� 0�w��̏ꍇ�́A�ʉ݃e�[�u���̈בփ��[�g�j
        //is���t�F�@@�g�����U�N�V�����i������薾�ׁj�s.getFinTransactionType() == �h�O�������h�̏ꍇtrue�B�ȊO�Afalse�B
        boolean l_blnIsBuy = false;
        if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionParams.getFinTransactionType()))
        {
            l_blnIsBuy = true;
        }
            
        //is���v�Z�F�@@true
        //is�w�l�F�@@�i�����P��(**).getLimitPrice() == 0�j�̏ꍇfalse�A�ȊOtrue�B
        //(**)�g�����U�N�V�����i������薾�ׁj�s.getOrderUnitId()�ɊY�����钍���P�ʁB
        OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_feqFinTransactionParams.getOrderUnitId());
        FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
        boolean l_blnIsPrice = true;
        if (l_orderUnitRow.getLimitPrice() == 0.0D)
        {
            l_blnIsPrice = false;
        }
        double l_dbFxRate = l_feqFinTransactionParams.getFxRate();
        WEB3FeqAmountCalcResult l_calcResult = 
            this.calcFeqAmount(
                l_subAccount, 
                l_feqProduct, 
                l_market,
                l_datBaseDate, 
                l_ExecDate,
                l_dblTradePriceFcTotal, 
                l_dbFxRate, 
                l_blnIsBuy, 
                true, 
                l_blnIsPrice, 
                l_orderUnitRow.getOrderChanel());

        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (�m����(n)�n�e���o��C��n���) <BR>
     * �ő���v�f�ȊO�̗v�f�ɂ��āA�m����(n)�n�e���o��C��n����A���v�l���v�Z����<BR>
     * @@param l_intMax �ő���v�f 
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_dblTradePrices �m����(n)�n�������
     * @@param l_dblTradePriceFcs �m����(n)�n��������i�O�݁j
     * @@param l_calcResult �O���������z�i���o��j���v
     * @@param l_dblTradePriceFcTotal ��������i�O�݁j���v
     * @@param l_intDecimalPlace �ʉ�.����������
     * @@param l_feqFinTransactionParams �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̔z��
     * @@param l_feqProduct �O����������
     * @@param l_datBaseDate ���
     * @@param l_amountTotals �e���o��C��n����̍��v�̔z��
     * �v�f0 ���n�萔�����v<BR>
     * �v�f1 ���n����ō��v<BR>
     * �v�f2 ���̑��R�X�g�P���v<BR>
     * �v�f3 ���̑��R�X�g2���v<BR>
     * �v�f4 ���n���Z������v<BR>
     * �v�f5 ���n���Z����i�~�݁j���v<BR>
     * �v�f6 �ϑ��萔�����v<BR>
     * �v�f7 �ϑ��萔���i�O�݁j���v<BR>
     * �v�f8 �ϑ��萔������ō��v<BR>
     * �v�f9 �ϑ��萔������Łi�O�݁j���v<BR>
     * @@return WEB3FeqAmountCalcResult[]
     * @@exception WEB3BaseException
     */
    public WEB3FeqAmountCalcResult[] calcAllAmount(
        int l_intMax,
        String l_strMarketCode,
        double[] l_dblTradePrices,
        double[] l_dblTradePriceFcs,
        WEB3FeqAmountCalcResult l_calcResult,
        double l_dblTradePriceFcTotal,
        int l_intDecimalPlace,
        FeqFinTransactionParams[] l_feqFinTransactionParams,
        WEB3FeqProduct l_feqProduct,
        Date l_ExecDate,
        double[] l_amountTotals) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcAllAmount(int, String, double[], double[], " +
            "WEB3FeqAmountCalcResult, double, int, WEB3FeqCurrency, " +
            "FeqFinTransactionParams[], WEB3FeqProduct, Date, double[])";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null ||
            l_feqFinTransactionParams.length == 0 ||
            l_feqProduct == null ||
            l_dblTradePrices == null ||
            l_dblTradePrices.length != l_feqFinTransactionParams.length ||
            l_dblTradePriceFcs == null ||
            l_dblTradePriceFcs.length != l_feqFinTransactionParams.length ||
            l_calcResult == null ||
            l_ExecDate == null ||
            l_amountTotals == null ||
            l_amountTotals.length != 10)             
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        int l_intSize = l_feqFinTransactionParams.length;

        WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails = 
            new WEB3FeqAmountCalcResult[l_intSize];

        for (int i = 0; i < 10; i++)
        {
            l_amountTotals[i] = 0D;
        }
        
        BigDecimal l_bdTradePriceFcTotal = new BigDecimal(l_dblTradePriceFcTotal);
        for (int i = 0; i < l_intSize; i++)
        {
            if (i == l_intMax)
            {
                continue;
            }

            l_feqAmountCalcResultDetails[i] = new WEB3FeqAmountCalcResult();
            WEB3FeqForeignCost l_foreignCost = new WEB3FeqForeignCost();
            l_feqAmountCalcResultDetails[i].setForeignCost(l_foreignCost);
            
            BigDecimal l_bdScale =
                new BigDecimal(l_dblTradePriceFcs[i]).divide(l_bdTradePriceFcTotal, 10, BigDecimal.ROUND_HALF_EVEN);
            
            boolean l_blnIsBuy =
                FinTransactionType.EQTYPE_FEQ_BUY.equals(
                    l_feqFinTransactionParams[i].getFinTransactionType());
            double l_dbFxRate = l_feqFinTransactionParams[i].getFxRate();
            
            WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();
            double l_dblFxRate = 
                l_genCurrency.getExchangeRate(l_blnIsBuy, true, l_dbFxRate);
            
            //�D�@@�ő���v�f�ȊO�̗v�f�ɂ��āA�m����(n)�n�e���o��C��n������v�Z����
            //�g�����U�N�V�����i������薾�ׁj�s�̊e�v�f�ɂ��āA
            //�e���o��(*1)�����߂�B�ő���v�f�i�A�Ŕ���j�́A���̏������X�L�b�v����B
            //�m����(n)�n�e���o��(*1)�v�Z��(*2)
            //�m����(n)�n�e���o��m���@@�v�n�e���o��@@�~�@@�m����(n)�n��������@@�^�@@�m���@@�v�n�������
            // (*1)�@@�e���o��
            //�E���n�萔��
            //�E���n�����
            //�E���̑��R�X�g�P
            //�E���̑��R�X�g�Q
            //�E�ϑ��萔��
            //�E�ϑ��萔���i�O�݁j
            //�E�ϑ��萔�������
            //�E�ϑ��萔������Łi�O�݁j
            //(*2)�@@�v�Z���ʂ͗L�����������؎́B

            //���n�萔���@@�ˊC�O���o��}�X�^.����������
            //�O�������v�Z�T�[�r�X.get�C�O���o��}�X�^()�ɂĎ擾����B
            String l_strSideDiv;
            if (l_blnIsBuy)
            {
                l_strSideDiv = WEB3BuySellTypeDef.BUY;
            }
            else
            {
                l_strSideDiv = WEB3BuySellTypeDef.SELL;
            }
            ForeignCostParams[] l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode, 
                    l_dblTradePriceFcs[i], 
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_COMMISSION_FEE,
                    l_strSideDiv);
            BigDecimal l_bdForignCommissionFee =
                new BigDecimal(l_calcResult.getForignCommissionFee()).multiply(l_bdScale);
            int l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForignCommissionFee.toString());
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]���n�萔�����v�l�isum�j�����߂�B
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForignCommissionFee = 
                        l_bdForignCommissionFee.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForignCommissionFee = l_bdForignCommissionFee.doubleValue();
            l_amountTotals[0] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[0], l_dblForignCommissionFee);
            l_feqAmountCalcResultDetails[i].setForeignCommissionFee(l_dblForignCommissionFee);
            log.debug("���n�萔�� = " + l_dblForignCommissionFee);

            //���n����Ł@@�ˊC�O���o��}�X�^.����������
            l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode, 
                    l_dblTradePriceFcs[i], 
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_TAX,
                    l_strSideDiv);
            BigDecimal l_bdForeignTax =
                new BigDecimal(l_calcResult.getForeignTax()).multiply(l_bdScale);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForeignTax.toString());
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]���n����ō��v�l�isum�j�����߂�B
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForeignTax = 
                        l_bdForeignTax.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForeignTax = l_bdForeignTax.doubleValue();
            l_amountTotals[1] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[1], l_dblForeignTax);
            l_feqAmountCalcResultDetails[i].setForeignTax(l_dblForeignTax);
            log.debug("���n����� = " + l_dblForeignTax);

            //���̑��R�X�g�P�@@�ˊC�O���o��}�X�^.����������
            l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode, 
                    l_dblTradePriceFcs[i], 
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_FEE_EXT1,
                    l_strSideDiv);
            BigDecimal l_bdForeignFeeExt1 = 
                new BigDecimal(l_calcResult.getForeignFeeExt1()).multiply(l_bdScale);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForeignFeeExt1.toString());
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]���̑��R�X�g�P���v�l�isum�j�����߂�B
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForeignFeeExt1 = 
                        l_bdForeignFeeExt1.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForeignFeeExt1 = l_bdForeignFeeExt1.doubleValue();
            l_amountTotals[2] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[2], l_dblForeignFeeExt1);
            l_feqAmountCalcResultDetails[i].setForeignFeeExt1(l_dblForeignFeeExt1);
            log.debug("���̑��R�X�g�P = " + l_dblForeignFeeExt1);
                
            //���̑��R�X�g�Q�@@�ˊC�O���o��}�X�^.����������
            l_foreignCostParams = 
                this.getForeignCost(
                    new Long(l_feqProduct.getProductId()),
                    l_feqProduct.getOffshoreProductCode(),
                    l_feqProduct.getInstitution().getInstitutionCode(), 
                    l_strMarketCode,
                    l_dblTradePriceFcs[i],
                    l_ExecDate,
                    WEB3FeqCostDivDef.FOREIGN_FEE_EXT2,
                    l_strSideDiv);
            BigDecimal l_bdForeignFeeExt2 = 
                new BigDecimal(l_calcResult.getForeignFeeExt2()).multiply(l_bdScale);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdForeignFeeExt2.toString());
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]���̑��R�X�g�Q���v�l�isum�j�����߂�B
            if (l_foreignCostParams != null && l_foreignCostParams.length != 0)
            {
                int l_intScale = l_foreignCostParams[0].getScale();
                if (l_intDigits > l_intScale)
                {
                    l_bdForeignFeeExt2 = 
                        l_bdForeignFeeExt2.setScale(l_intScale, BigDecimal.ROUND_DOWN);
                }
            }
            double l_dblForeignFeeExt2 = l_bdForeignFeeExt2.doubleValue();
            l_amountTotals[3] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[3], l_dblForeignFeeExt2);
            l_feqAmountCalcResultDetails[i].setForeignFeeExt2(l_dblForeignFeeExt2);
            log.debug("���̑��R�X�g�Q = " + l_dblForeignFeeExt2);

            //���n���Z���
            double l_dblBalanceAmountFc = 0.0D;
            if (l_blnIsBuy)
            {
                l_dblBalanceAmountFc = new BigDecimal(String.valueOf(l_dblTradePriceFcs[i]))
                    .add(new BigDecimal(String.valueOf(l_dblForignCommissionFee)))
                    .add(new BigDecimal(String.valueOf(l_dblForeignTax)))
                    .add(new BigDecimal(String.valueOf(l_dblForeignFeeExt1)))
                    .add(new BigDecimal(String.valueOf(l_dblForeignFeeExt2))).doubleValue();
            }
            else
            {
                l_dblBalanceAmountFc = new BigDecimal(String.valueOf(l_dblTradePriceFcs[i]))
                    .subtract(new BigDecimal(String.valueOf(l_dblForignCommissionFee)))
                    .subtract(new BigDecimal(String.valueOf(l_dblForeignTax)))
                    .subtract(new BigDecimal(String.valueOf(l_dblForeignFeeExt1)))
                    .subtract(new BigDecimal(String.valueOf(l_dblForeignFeeExt2))).doubleValue();
            }
            l_amountTotals[4] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[4], l_dblBalanceAmountFc);
            l_feqAmountCalcResultDetails[i].setBalanceAmountFc(l_dblBalanceAmountFc);
            log.debug("���n���Z��� = " + l_dblBalanceAmountFc);

            //���n���Z����i�~�݁j
            double l_dblBalanceAmount =
                this.calcJPYAmount(
                    l_dblBalanceAmountFc,
                    l_dblFxRate,
                    WEB3FeqRoundDivDef.CUTOFF);
            l_amountTotals[5] = 
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[5], l_dblBalanceAmount);
            l_feqAmountCalcResultDetails[i].setBalanceAmount(l_dblBalanceAmount);
            log.debug("���n���Z����i�~�݁j = " + l_dblBalanceAmount);

            //�ϑ��萔��
            BigDecimal l_bdCommissionFee = 
                new BigDecimal(l_calcResult.getCommissionFee()).multiply(l_bdScale);
            l_bdCommissionFee = 
                l_bdCommissionFee.setScale(0, BigDecimal.ROUND_DOWN);
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]�ϑ��萔�����v�l�isum�j�����߂�B
            double l_dblCommissionFee = l_bdCommissionFee.doubleValue();
            l_amountTotals[6] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[6], l_dblCommissionFee);
            l_feqAmountCalcResultDetails[i].setCommissionFee(l_dblCommissionFee);
            log.debug("�ϑ��萔�� = " + l_dblCommissionFee);

            //�O�݊��Z�ۂߕ������擾����
            String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
            log.debug("�O�݊��Z�ۂߕ��� = " + l_strFCCYRoundDiv);

            //�������������擾����B
            int l_intScale = l_genCurrency.getScale();
            log.debug("���������� = " + l_intScale);

            //�ϑ��萔���i�O�݁j���v�Z����B 
            double l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_dblCommissionFee, 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);

            BigDecimal l_bdCommissionFeeFc = new BigDecimal(l_dblForeignCCYAmount);
            l_intDigits = WEB3StringTypeUtility.getFractionDigits(l_bdCommissionFeeFc.toString());
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]�ϑ��萔���i�O�݁j���v�l�isum�j�����߂�B
            if (l_intDigits > l_intDecimalPlace)
            {
                l_bdCommissionFeeFc = 
                    l_bdCommissionFeeFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
            }
            double l_dblCommissionFeeFc = l_bdCommissionFeeFc.doubleValue();
            l_amountTotals[7] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[7], l_dblCommissionFeeFc);
            l_feqAmountCalcResultDetails[i].setCommissionFeeFc(l_dblCommissionFeeFc);
            log.debug("�ϑ��萔���i�O�݁j = " + l_dblCommissionFeeFc);

            //�ϑ��萔������Ł@@�ˏ������Ȃ��i�~�����؎̂āj
            BigDecimal l_bdCommissionFeeTax = 
                new BigDecimal(l_calcResult.getCommisionFeeTax()).multiply(l_bdScale);
            l_bdCommissionFeeTax = 
                l_bdCommissionFeeTax.setScale(0, BigDecimal.ROUND_DOWN);
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]�ϑ��萔������ō��v�l�isum�j�����߂�B
            double l_dblCommissionFeeTax = l_bdCommissionFeeTax.doubleValue();
            l_amountTotals[8] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[8], l_dblCommissionFeeTax); 
            l_feqAmountCalcResultDetails[i].setCommissionFeeTax(l_dblCommissionFeeTax);
            log.debug("�ϑ��萔������� = " + l_dblCommissionFeeTax);

            //�ϑ��萔������Łi�O�݁j���v�Z����B 
            l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_dblCommissionFeeTax, 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);

            BigDecimal l_bdCommissionFeeTaxFc = new BigDecimal(l_dblForeignCCYAmount);
            l_intDigits = 
                WEB3StringTypeUtility.getFractionDigits(l_bdCommissionFeeTaxFc.toString());
            //�ő���v�f�i�A�Ŕ���j�ȊO��[�����in�j]�ϑ��萔������Łi�O�݁j���v�l�isum�j�����߂�B
            if (l_intDigits > l_intDecimalPlace)
            {
                l_bdCommissionFeeTaxFc = 
                    l_bdCommissionFeeTaxFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
            }
            double l_dblCommissionFeeTaxFc = l_bdCommissionFeeTaxFc.doubleValue();
            l_amountTotals[9] =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[9], l_dblCommissionFeeTaxFc);
            l_feqAmountCalcResultDetails[i].setCommissionFeeTaxFc(l_dblCommissionFeeTaxFc);
            log.debug("�ϑ��萔������Łi�O�݁j = " + l_dblCommissionFeeTaxFc);

            //�m����(n)�n�������
            l_feqAmountCalcResultDetails[i].setTradePrice(l_dblTradePrices[i]);
            log.debug("������� = " + l_dblTradePrices[i]);
            //�m����(n)�n��������i�O�݁j
            l_feqAmountCalcResultDetails[i].setTradePriceFc(l_dblTradePriceFcs[i]);
            log.debug("��������i�O�݁j = " + l_dblTradePriceFcs[i]);

            //�m����(n)�n��n������m����(n)�n��������}(*3)�@@�i�m����(n)�n�ϑ��萔���@@�{�m����(n)�n�ϑ��萔������Łj
            //(*2)�@@�v�Z���ʂ͗L�����������؎́B
            //(*3) ���t�̏ꍇ�{�A���t�̏ꍇ�|
            //�m����(n)�n��n����i�O�݁j���m����(n)�n��������i�O�݁j�}(*3)�i�m����(n)�n�ϑ��萔���i�O�݁j�{�m����(n)�n�ϑ��萔������Łi�O�݁j�j
            double l_dblNetAmount = 0.0D;
            double l_dblNetAmountFc = 0.0D;
            BigDecimal l_bdBalanceAmountFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[i].getBalanceAmountFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            l_bdCommissionFeeFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[i].getCommissionFeeFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            l_bdCommissionFeeTaxFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[i].getCommisionFeeTaxFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            if (l_blnIsBuy)
            {
                //��n���
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[i].getBalanceAmount() + 
                    l_feqAmountCalcResultDetails[i].getCommissionFee() +
                    l_feqAmountCalcResultDetails[i].getCommisionFeeTax();

                //��n����i�O�݁j
                l_bdBalanceAmountFc = l_bdCommissionFeeFc.add(l_bdBalanceAmountFc);
                l_bdBalanceAmountFc = l_bdCommissionFeeTaxFc.add(l_bdBalanceAmountFc);
                l_intDigits = WEB3StringTypeUtility.getFractionDigits(l_bdBalanceAmountFc.toString());
                if (l_intDigits > l_intDecimalPlace)
                {
                    l_bdBalanceAmountFc = 
                        l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
                }
                l_dblNetAmountFc = l_bdBalanceAmountFc.doubleValue();
            }
            else
            {
                //��n���
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[i].getBalanceAmount() - 
                    (l_feqAmountCalcResultDetails[i].getCommissionFee() +
                    l_feqAmountCalcResultDetails[i].getCommisionFeeTax());
                //��n����i�O�݁j
                l_bdCommissionFeeFc = l_bdCommissionFeeFc.add(l_bdCommissionFeeTaxFc);
                l_bdBalanceAmountFc = l_bdBalanceAmountFc.subtract(l_bdCommissionFeeFc);
                l_intDigits = WEB3StringTypeUtility.getFractionDigits(l_bdBalanceAmountFc.toString());
                if (l_intDigits > l_intDecimalPlace)
                {
                    l_bdBalanceAmountFc = 
                         l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
                }
                l_dblNetAmountFc = l_bdBalanceAmountFc.doubleValue();
            }

            l_dblNetAmount =
                WEB3FeqOrderUtility.getCutOutValue(
                    l_dblNetAmount,
                    0,
                    WEB3FeqOrderUtility.CUTOFF);

            l_intDigits = WEB3StringTypeUtility.getFractionDigits(new Double(l_dblNetAmountFc).toString());
            if (l_intDigits > l_intDecimalPlace)
            {
                l_dblNetAmountFc =
                    WEB3FeqOrderUtility.getCutOutValue(
                        l_dblNetAmountFc,
                        l_intDecimalPlace,
                        WEB3FeqOrderUtility.CUTOFF);
            }
            l_feqAmountCalcResultDetails[i].setNetAmount(l_dblNetAmount);
            l_feqAmountCalcResultDetails[i].setNetAmountFc(l_dblNetAmountFc);
            log.debug("��n��� = " + l_dblNetAmount);
            log.debug("��n����i�O�݁j = " + l_dblNetAmountFc);
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqAmountCalcResultDetails;
    }

    /**
     * (calc�O���������z�i���j) <BR>
     * �O���������z���v�Z���s���B <BR>
     *  <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�O�������j.doc  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�P.�萔�����v�Z�i������j�v�Q�ƁB <BR>
     * @@param l_feqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�s) 
     * �g�����U�N�V�����i������薾�ׁj�s�I�u�W�F�N�g�̔z��
     * @@return webbroker3.feq.WEB3FeqAmountCalcResultFactor
     * @@throws WEB3BaseException
     * @@roseuid 4289CA2A01E4
     */
    public WEB3FeqAmountCalcResultFactor calcFeqAmountFactor(
        FeqFinTransactionParams[] l_feqFinTransactionParams)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "calcFeqAmountFactor(FeqFinTransactionParams[])";
        log.entering(STR_METHOD_NAME);

        if (l_feqFinTransactionParams == null || l_feqFinTransactionParams.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        int l_intSize = l_feqFinTransactionParams.length;

        try
        {
            //�O�����������F�@@�g�����U�N�V�����i������薾�ׁj�s.getProductId()�ɊY������O����������
            WEB3FeqProduct l_feqProduct = 
                (WEB3FeqProduct)l_productManager.getProduct(l_feqFinTransactionParams[0].getProductId());
            WEB3GentradeCurrency l_currency = l_feqProduct.getCurrency();
            WEB3FeqAmountCalcResult[] l_feqAmountCalcResultDetails = 
                new WEB3FeqAmountCalcResult[l_intSize];
            
            double[] l_dblTradePrices = new double[l_intSize];
            double[] l_dblTradePriceFcs = new double[l_intSize];
            double[] l_dblTotals = new double[2];
             //�@@�@@[�����in�j]����������v�Z����
            //����.�g�����U�N�V�����i������薾�ׁj�s[]�̊e�v�f�ɂ��āA
            //��������i*1�j���v�Z��[�����in�j]��������Ƃ���B
            //[�����in�j]��n�́A�z���index�B
            //(*1)�@@��������v�Z��
            //�@@�g�����U�N�V�����i������薾�ׁj�s.���P�� �~ �g�����U�N�V�����i������薾�ׁj�s.��萔��
            //�A�@@�ő���v�f�̔���
            //�B�@@[���@@�v]����������v�Z����
            int l_intMax = 
                calcMaxExecIndex(
                    l_feqFinTransactionParams,
                    l_currency,
                    l_dblTradePrices,
                    l_dblTradePriceFcs,
                    l_dblTotals);

            double l_dblTradePriceFcTotal = l_dblTotals[1];

            //�s��F�@@�g�����U�N�V�����i������薾�ׁj�s.getMarketId()�ɊY������s��
            WEB3GentradeMarket l_market = 
                (WEB3GentradeMarket)l_finObjectManager.getMarket(l_feqFinTransactionParams[0].getMarketId());
            
            //����F�@@���(*).�������̓��t�����i�����ԕ�����00:00:00�j
            //(*)�g�����U�N�V�����i������薾�ׁj�s.getOrderExecutionId()�ɊY��������B
            //�Y���f�[�^�Ȃ��̏ꍇ�́A�g�����U�N�V����(������薾��)�s.�g�����U�N�V���������������Z�b�g�B
            Date l_ExecDate = 
                GtlUtils.truncateDate(l_feqFinTransactionParams[0].getFinTransactionTimestamp());
            OrderExecution l_orderExecution = null;
            Date l_datExecutionDate = null;
            try
            {
                l_orderExecution = 
                    l_orderManager.getOrderExecution(l_feqFinTransactionParams[0].getOrderExecutionId());
                l_datExecutionDate = l_orderExecution.getExecutionTimestamp();
                if (l_orderExecution == null)
                {
                    l_ExecDate = GtlUtils.truncateDate(l_datExecutionDate);
                }
            }
            catch (NotFoundException e)
            {
                log.debug("��背�R�[�h���擾���Ȃ�");                
            }
            Date l_datBaseDate = WEB3DateUtility.getDate(l_feqFinTransactionParams[0].getBizDate(),"yyyyMMdd");
            
            //�C�@@�m���@@ �v�n�O���������z�i���o��j���v�Z����
            WEB3FeqAmountCalcResult l_calcResult = 
                calcFeqAmountTotal(
                    l_feqFinTransactionParams[0],
                    l_feqProduct,
                    l_orderManager,
                    l_accountManager,
                    l_market,
                    l_datBaseDate,
                    l_ExecDate,
                    l_dblTradePriceFcTotal); 
                
            double[] l_amountTotals = new double[10];
            int l_intDecimalPlace = l_currency.getScale();
            
            //�D�@@�ő���v�f�ȊO�̗v�f�ɂ��āA�m����(n)�n�e���o��C��n������v�Z����
            //�E�@@�ő���v�f�ȊO�́m����(n)�n�e���o��ɂ��āA���v�l���v�Z����             
            l_feqAmountCalcResultDetails = calcAllAmount(
                l_intMax,
                l_market.getMarketCode(),
                l_dblTradePrices,
                l_dblTradePriceFcs,
                l_calcResult,
                l_dblTradePriceFcTotal,
                l_intDecimalPlace,
                l_feqFinTransactionParams,
                l_feqProduct,
                l_ExecDate,
                l_amountTotals);

            //�F �ő���v�f�i�A�Ŕ���j��[�����in�j]�e���o��C��n������v�Z����
            //�[�����񂹂邽�߁A���̌v�Z���ōő���̈ϑ��萔���A����ł��v�Z����B
            //[����(n)]�e���o�� ���@@[���@@ �v]�e���o��i�D�Ōv�Z�j�@@�|�@@�e���o��̍��v�i�F�Ōv�Z�j
            l_feqAmountCalcResultDetails[l_intMax] = 
                new WEB3FeqAmountCalcResult();
            WEB3FeqForeignCost l_foreignCost = new WEB3FeqForeignCost();
            l_feqAmountCalcResultDetails[l_intMax].setForeignCost(l_foreignCost);

            //���n�萔�����v
            l_feqAmountCalcResultDetails[l_intMax].setForeignCommissionFee(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForignCommissionFee(),
                    l_amountTotals[0]));
            log.debug("�ő���v�f�̌��n�萔�����v = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForignCommissionFee());
            //���n�����
            l_feqAmountCalcResultDetails[l_intMax].setForeignTax(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForeignTax(),
                    l_amountTotals[1]));
            log.debug("�ő���v�f�̌��n����� = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForeignTax());
            //���̑��R�X�g1
            l_feqAmountCalcResultDetails[l_intMax].setForeignFeeExt1(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForeignFeeExt1(),
                    l_amountTotals[2]));
            log.debug("�ő���v�f�̂��̑��R�X�g1 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt1());
            //���̑��R�X�g2
            l_feqAmountCalcResultDetails[l_intMax].setForeignFeeExt2(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getForeignFeeExt2(),
                    l_amountTotals[3]));
            log.debug("�ő���v�f�̂��̑��R�X�g2 = " + 
                l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt2());
            //���n���Z���
            double l_dblMaxBalanceAmountFc = 0.0D;
            boolean l_blnIsBuy =
                FinTransactionType.EQTYPE_FEQ_BUY.equals(
                    l_feqFinTransactionParams[l_intMax].getFinTransactionType());
            if (l_blnIsBuy)
            {
                l_dblMaxBalanceAmountFc =
                      new BigDecimal(String.valueOf(l_dblTradePriceFcs[l_intMax]))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForignCommissionFee())))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignTax())))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt1())))
                      .add(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt2()))).doubleValue();
            }
            else
            {
                l_dblMaxBalanceAmountFc =
                      new BigDecimal(String.valueOf(l_dblTradePriceFcs[l_intMax]))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForignCommissionFee())))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignTax())))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt1())))
                      .subtract(new BigDecimal(String.valueOf(l_feqAmountCalcResultDetails[l_intMax].getForeignFeeExt2()))).doubleValue();
            }
            l_feqAmountCalcResultDetails[l_intMax].setBalanceAmountFc(l_dblMaxBalanceAmountFc);
            log.debug("�ő���v�f�̌��n���Z��� = " + 
                l_feqAmountCalcResultDetails[l_intMax].getBalanceAmountFc());
            //���n���Z����i�~�݁j
            WEB3GentradeCurrency l_genCurrency = l_feqProduct.getCurrency();

            double l_dblTransactionFxRate = l_feqFinTransactionParams[0].getFxRate();
            double l_dblFxRate = 
                l_genCurrency.getExchangeRate(l_blnIsBuy, true, l_dblTransactionFxRate);
            double l_dblTotalBalanceAmountFc =
                WEB3FeqOrderUtility.decimalPlus(l_amountTotals[4], l_dblMaxBalanceAmountFc);
            double l_dblTotalBalanceAmount =
                this.calcJPYAmount(
                    l_dblTotalBalanceAmountFc,
                    l_dblFxRate,
                    WEB3FeqRoundDivDef.CUTOFF);
            l_feqAmountCalcResultDetails[l_intMax].setBalanceAmount(
                WEB3FeqOrderUtility.decimalMinus(l_dblTotalBalanceAmount,
                    l_amountTotals[5]));
            log.debug("�ő���v�f�̌��n���Z����i�~�݁j = " + 
                l_feqAmountCalcResultDetails[l_intMax].getBalanceAmount());
            //�ϑ��萔��
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFee(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getCommissionFee(),
                    l_amountTotals[6]));
            log.debug("�ő���v�f�̈ϑ��萔�� = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommissionFee());

            //�O�݊��Z�ۂߕ������擾����
            String l_strFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
            log.debug("�O�݊��Z�ۂߕ��� = " + l_strFCCYRoundDiv);

            //�������������擾����B
            int l_intScale = l_genCurrency.getScale();
            log.debug("���������� = " + l_intScale);

            //�ϑ��萔���i�O�݁j�����Z����B 
            double l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_feqAmountCalcResultDetails[l_intMax].getCommissionFee(), 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);
            //�ϑ��萔���i�O�݁j
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFeeFc(l_dblForeignCCYAmount);
            log.debug("�ő���v�f�̈ϑ��萔���i�O�݁j = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommissionFeeFc());
            //�ϑ��萔�������
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFeeTax(
                WEB3FeqOrderUtility.decimalMinus(l_calcResult.getCommisionFeeTax(),
                    l_amountTotals[8]));
            log.debug("�ő���v�f�̈ϑ��萔������� = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax());

            //�ϑ��萔������ł��O�݂Ɋ��Z����B 
            l_dblForeignCCYAmount = 
                this.calcForeignCCYAmount(
                    l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax(), 
                    l_dblFxRate, 
                    l_intScale, 
                    l_strFCCYRoundDiv);
            //�ϑ��萔������Łi�O�݁j
            l_feqAmountCalcResultDetails[l_intMax].setCommissionFeeTaxFc(l_dblForeignCCYAmount);
            log.debug("�ő���v�f�̈ϑ��萔������Łi�O�݁j = " + 
                l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTaxFc());
            //�������
            l_feqAmountCalcResultDetails[l_intMax].setTradePrice(l_dblTradePrices[l_intMax]);
            log.debug("�ő���v�f�̔������ = " + l_dblTradePrices[l_intMax]);
            //��������i�O�݁j
            l_feqAmountCalcResultDetails[l_intMax].setTradePriceFc(l_dblTradePriceFcs[l_intMax]);
            log.debug("�ő���v�f�̔�������i�O�݁j = " + l_dblTradePriceFcs[l_intMax]);

            //�m����(n)�n��n������m����(n)�n��������}(*3)�@@�i�m����(n)�n�ϑ��萔���@@�{�m����(n)�n�ϑ��萔������Łj
            //(*2)�@@�v�Z���ʂ͗L�����������؎́B
            //(*3) ���t�̏ꍇ�{�A���t�̏ꍇ�|
            //�m����(n)�n��n����i�O�݁j���m����(n)�n��������i�O�݁j�}(*3)�i�m����(n)�n�ϑ��萔���i�O�݁j�{�m����(n)�n�ϑ��萔������Łi�O�݁j�j
            //(*2)�@@�v�Z���ʂ͗L�����������؎́B
            //(*3) ���t�̏ꍇ�{�A���t�̏ꍇ�|
            double l_dblNetAmount = 0.0D;
            double l_dblNetAmountFc = 0.0D;
            BigDecimal l_bdBalanceAmountFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[l_intMax].getBalanceAmountFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal l_bdCommissionFeeFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[l_intMax].getCommissionFeeFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal l_bdCommissionFeeTaxFc = 
                new BigDecimal(l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTaxFc()).
                setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            if (l_blnIsBuy)
            {
                //��n���
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[l_intMax].getBalanceAmount() + 
                    l_feqAmountCalcResultDetails[l_intMax].getCommissionFee() +
                    l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax();
                //��n����i�O�݁j
                l_bdBalanceAmountFc = l_bdCommissionFeeFc.add(l_bdBalanceAmountFc);
                l_bdBalanceAmountFc = l_bdCommissionFeeTaxFc.add(l_bdBalanceAmountFc);
            }
            else
            {
                //��n���
                l_dblNetAmount =
                    l_feqAmountCalcResultDetails[l_intMax].getBalanceAmount() - 
                    (l_feqAmountCalcResultDetails[l_intMax].getCommissionFee() +
                    l_feqAmountCalcResultDetails[l_intMax].getCommisionFeeTax());
                //��n����i�O�݁j
                l_bdCommissionFeeFc = l_bdCommissionFeeFc.add(l_bdCommissionFeeTaxFc);
                l_bdBalanceAmountFc = l_bdBalanceAmountFc.subtract(l_bdCommissionFeeFc);
            }
            
            BigDecimal l_bdNetAmount = new BigDecimal(l_dblNetAmount);
            l_bdNetAmount = l_bdNetAmount.setScale(0, BigDecimal.ROUND_HALF_EVEN);
            l_dblNetAmount = l_bdNetAmount.doubleValue();

            int l_intDigits =
                WEB3StringTypeUtility.getFractionDigits(
                    new Double(
                        l_feqAmountCalcResultDetails[l_intMax].getBalanceAmountFc()).toString());
            if (l_intDigits > l_intDecimalPlace)
            {
                l_bdBalanceAmountFc =
                l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_DOWN);
            }
            else
            {
                l_bdBalanceAmountFc =
                l_bdBalanceAmountFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
            }
            l_dblNetAmountFc = l_bdBalanceAmountFc.doubleValue();
            l_feqAmountCalcResultDetails[l_intMax].setNetAmount(l_dblNetAmount);
            l_feqAmountCalcResultDetails[l_intMax].setNetAmountFc(l_dblNetAmountFc);
            log.debug("�ő���v�f�̎�n��� = " + 
                l_feqAmountCalcResultDetails[l_intMax].getNetAmount());
            log.debug("�ő���v�f�̎�n����i�O�݁j = " + 
                l_feqAmountCalcResultDetails[l_intMax].getNetAmountFc());

            WEB3FeqAmountCalcResultFactor l_resultFactor = 
                new WEB3FeqAmountCalcResultFactor(l_feqAmountCalcResultDetails, l_calcResult);
            log.exiting(STR_METHOD_NAME);
            return l_resultFactor;
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + 
                STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_dqe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + 
                STR_METHOD_NAME, l_dqe.getMessage(), l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + 
                STR_METHOD_NAME, l_dne.getMessage(), l_dne);
        }
    }

    /**
     * (calc�뉿�P��) <BR>
     * �뉿�P�����v�Z����B <BR>
     *  <BR>
     * �P�j�ۗL���Y�I�u�W�F�N�g���擾����B <BR>
     *  <BR>
     *    �O�������|�W�V�����}�l�[�W��.get�ۗL���Y()���R�[������B <BR>
     *  <BR>
     *    [����] <BR>
     *    ����ID�F ����.�⏕����.����ID <BR>
     *    �⏕����ID�F ����.�⏕����.�⏕����ID <BR>
     *    ����ID�F ����.����ID <BR>
     *    �ŋ敪�F ����.�ŋ敪 <BR>
     *  <BR>
     *    ���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00204 <BR>
     *  <BR>
     * �Q�j�뉿�P�������߁A�v�Z���ʂ�ԋp����B <BR>
     *  <BR>
     *    �뉿�P�����ۗL���Y.�뉿�i�뉿�P���v�Z�p�j ��  <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�ۗL���Y.���ʁi�뉿�P���v�Z�p�j <BR>
     *  <BR>
     *    ���v�Z���ʂ��A�����́u�~�����L�������v�܂ł̌����ɁA <BR>
     * �@@�@@�@@�l�̌ܓ��ɂ��ۂ߂�B <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_lngProductId - (����ID)
     * @@param l_taxType - (�ŋ敪)
     * @@param l_lngValidGirder - (�~�����L������)
     * @@return BigDecimal
     * @@roseuid 4294634F0203
     */
    public BigDecimal calcBookValuePrice(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngProductId,
        TaxTypeEnum l_taxType,
        long l_lngValidGirder) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcBookValuePrice(WEB3GentradeSubAccount, long, TaxTypeEnum, long)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lngValidGirder < 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "�����́u�~�����L�������v = " + l_lngValidGirder);
        }
        if (l_subAccount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�ۗL���Y�I�u�W�F�N�g���擾����B
        //    �O�������|�W�V�����}�l�[�W��.get�ۗL���Y()���R�[������B
        //    [����] 
        //    ����ID�F ����.�⏕����.����ID 
        //    �⏕����ID�F ����.�⏕����.�⏕����ID 
        //    ����ID�F ����.����ID 
        //    �ŋ敪�F ����.�ŋ敪 
        //    ���Y���f�[�^�Ȃ��̏ꍇ�́A��O���X���[����B 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqPositionManager l_positionManager = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        
        // get �ۗL���Y
        FeqAsset l_asset =
            (FeqAsset) l_positionManager.getAsset(
                l_subAccount.getAccountId(),
                l_subAccount.getSubAccountId(),
                l_lngProductId,
                l_taxType);
        
        if (l_asset == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass() + "." + STR_METHOD_NAME,
                "�ۗL���Y�e�[�u���ɊY������f�[�^������܂���");
        }
        //�뉿�P�������߁A�v�Z���ʂ�ԋp����B
        AssetRow l_assetRow =
            (AssetRow)l_asset.getDataSourceObject();

        //get �ۗL���Y.���ʁi�뉿�P���v�Z�p�j
        double l_dblTotalQuantity = l_assetRow.getQuantityForBookValue();
        BigDecimal l_bdTotalQutity =
            new BigDecimal(String.valueOf(l_dblTotalQuantity));
        if (l_dblTotalQuantity == 0.0D)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass() + "." + STR_METHOD_NAME,
                "�ۗL���Y�e�[�u��.���ʁi�뉿�P���v�Z�p�j��0���ݒ肳��Ă��܂�");
        }
        //get �ۗL���Y.�뉿�i�뉿�P���v�Z�p�j
        double l_dbBookValue = l_assetRow.getBookValue();
        BigDecimal l_bdBookValue =
            new BigDecimal(String.valueOf(l_dbBookValue));

        //�뉿�P�����ۗL���Y.�뉿���ۗL���Y.����
        //�v�Z���ʂ��A�����́u�~�����L�������v�܂ł̌����ɁA�l�̌ܓ��ɂ��ۂ߂�B
        BigDecimal l_bdBookPrice =
            l_bdBookValue.divide(
                l_bdTotalQutity,
                (int)l_lngValidGirder,
                BigDecimal.ROUND_HALF_UP);

        log.debug("�ۗL���Y.�뉿�F[" + l_dbBookValue + "]");
        log.debug("�ۗL���Y.���ʁF[" + l_dblTotalQuantity + "]");

        log.exiting(STR_METHOD_NAME);
        return l_bdBookPrice;
    }

    /**
     * (calc�T�Z�뉿�P��) <BR>
     * �T�Z�뉿�P�����v�Z����B <BR>
     *  <BR>
     * this.calc�뉿�P��()���R�[������B <BR>
     *  <BR>
     * [����] <BR>
     * �⏕�����F ����.�⏕���� <BR>
     * ����ID�F ����.����ID <BR>
     * �ŋ敪�F ����.�ŋ敪 <BR>
     * �~�����L�������F 5 <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g
     * 
     * @@param l_lngProductId - (����ID)
     * 
     * @@param l_taxType - (�ŋ敪)
     * 
     * @@return BigDecimal
     * @@roseuid 4294650403E7
     */
    public BigDecimal calcEstimatedBookValuePrice(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngProductId,
        TaxTypeEnum l_taxType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcEstimatedBookValuePrice(WEB3GentradeSubAccount, long, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        BigDecimal l_dbEstimatedBookPrice =
            this.calcBookValuePrice(l_subAccount, l_lngProductId, l_taxType, 5);
        
        log.exiting(STR_METHOD_NAME);
        return l_dbEstimatedBookPrice;
    }

    /**
     * (calc�T�Z�뉿�P��) <BR>
     * �����̒l���T�Z�뉿�P�����v�Z����B <BR>
     *  <BR>
     * �p�����[�^.�뉿 �� �p�����[�^.���ʂ�ԋp����B <BR>
     * ���v�Z���ʂ́A�ۂߏ���(�����_19�ʂ��l�̌ܓ�)����B<BR>
     * @@param l_dblBookValue - (�뉿)
     * @@param l_dblQuantity - (����)
     * @@return BigDecimal
     * @@roseuid 42AD1D3B031B
     */
    public BigDecimal calcEstimatedBookValuePrice(
        double l_dblBookValue, 
        double l_dblQuantity)
    {
        String STR_METHOD_NAME = "calcEstimatedBookValuePrice(double, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_dblQuantity < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass() + "." + STR_METHOD_NAME,
                "���� = " + l_dblQuantity);
        }

        if (l_dblQuantity == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return new BigDecimal("0");
        }

        BigDecimal l_bdBookValue = new BigDecimal(String.valueOf(l_dblBookValue));
        BigDecimal l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));

        //�����_19�ʂ��l�̌ܓ�
        BigDecimal l_bdResult =
            l_bdBookValue.divide(l_bdQuantity, 18, BigDecimal.ROUND_HALF_UP);

        log.exiting(STR_METHOD_NAME);
        return l_bdResult;
    }

    /**
     * (calc�����O���������z) <BR>
     * ������̊O���������z�v�Z���s���B <BR>
     *  <BR>
     * �@@�E���n�萔�� <BR>
     * �@@�E���n����� <BR>
     * �@@�E���̑��R�X�g�P <BR>
     * �@@�E���̑��R�X�g�Q <BR>
     * �@@�E���n���Z��� <BR>
     * �@@�E���n���Z����i�~�݁j <BR>
     * �@@�E�ϑ��萔�� <BR>
     * �@@�E�ϑ��萔���i�O�݁j <BR>
     * �@@�E�ϑ��萔������� <BR>
     * �@@�E�ϑ��萔������Łi�O�݁j <BR>
     * �@@�E��n��� <BR>
     * �@@�E��n����i�O�݁j <BR>
     *  <BR>
     * �P�j�@@�������擾 <BR>
     * �@@��������w��inull�j�̏ꍇ�A������ԊǗ�.get������() <BR>
     *   �ɂĔ��������擾���A����Ƃ���B <BR>
     *  <BR>
     * �Q�j�@@�萔���I�u�W�F�N�g�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B  <BR>
     *  <BR>
     * �@@�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode() <BR>
     * �@@���XID�F �⏕����.get����X().BranchId() <BR>
     * �@@�萔�����i�R�[�h�F this.get�萔�����i�R�[�h() <BR>
     * �@@����R�[�h�iSONAR�j�F�@@this.get����R�[�h�iSONAR�j() <BR>
     * �@@�������F�@@������ <BR>
     * �@@�ٍϋ敪�F�@@�ٍϋ敪.00�F���̑�  <BR>
     * �@@�����`���l���F �����`���l�� <BR>
     * �@@is�w�l�F is�w�l���� <BR>
     * �@@�s��R�[�h�iSONAR�j�F�@@�s��.�s��R�[�h�iSONAR�j <BR>
     * �@@�����������`���l���F�@@�����P��.���񒍕��̒����`���l�� <BR>
     * �@@�������萔��No�F�@@�����P��.���񒍕��̎萔��No <BR>
     * �@@�������萔��No�}�ԁF�@@�����P��.���񒍕��̎萔��No�}�� <BR>
     *  <BR>
     * �R�j�@@��������i�O�݁j�ɖ�蕪�����Z <BR>
     *  <BR>
     * �@@�@@��������i�O�݁j�F <BR>
     *     ����.��������i�O�݁j + �����P��.���v�����z <BR>
     *  <BR>
     * �S�j�@@�O���������z�v�Z <BR>
     * �@@this.calc�O���������z(�萔��, �⏕����, �O����������, �s��,  <BR>
     * �@@���, ��������i�O�݁j(*), �בփ��[�g, is���t, is���v�Z, is�w�l)�� <BR>
     * �@@�Ϗ��ideligate�j����B <BR>
     *  <BR>
     *   (*)�R�j�ŎZ�o�������� <BR>
     *  <BR>
     * �T�j�@@�O���������z�v�Z���ʃI�u�W�F�N�g�ԋp <BR>
     * �@@�O���������z�v�Z���ʃI�u�W�F�N�g��ԋp����B <BR>
     * @@param l_subAccount - (�⏕����)
     * @@param l_feqProduct - (�O����������)
     * @@param l_market - (�s��)
     * @@param l_datBaseDate - (���)
     * @@param l_dblTradePriceFc - (��������i�O�݁j)
     * @@param l_dblFxRate - (�בփ��[�g) 
     * �i�� 0�w��̏ꍇ�́A�ʉ݃e�[�u���̈בփ��[�g�j
     * @@param l_blnIsBuy - (is���t) 
     * ���t���̔��� <BR>
     *  <BR>
     * true�F�� <BR>
     * false�F�� <BR>
     * @@param l_blnIsExecCalc - (is���v�Z)
     * ���v�Z���̔��� <BR>
     *  <BR>
     * true�F���v�Z <BR>
     * false�F�T�Z�v�Z <BR>
     * @@param l_blnIsLimitPrice - (is�w�l) 
     * �w�l���̔��� <BR>
     *  <BR>
     * true�F�w�l <BR>
     * false�F���s <BR>
     * @@param l_feqOrderUnit - (�����P��) 
     * �����P�ʃI�u�W�F�N�g
     * @@return webbroker3.feq.WEB3FeqAmountCalcResult
     * @@roseuid 42A538FE03A8
     */
    public WEB3FeqAmountCalcResult calcChangeFeqAmount(
        WEB3GentradeSubAccount l_subAccount,
        WEB3FeqProduct l_feqProduct,
        WEB3GentradeMarket l_market,
        Date l_datBaseDate,
        double l_dblTradePriceFc,
        double l_dblFxRate,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc,
        boolean l_blnIsLimitPrice,
        FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcChangeFeqAmount(WEB3GentradeSubAccount, WEB3FeqProduct, " +
            "WEB3GentradeMarket, Date, double, double, boolean, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_feqProduct == null || 
            l_market == null || l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�������擾 
        //��������w��inull�j�̏ꍇ�A 
        //������ԊǗ�.get������()�ɂĔ��������擾���A����Ƃ���B 
        Date l_datOrderBizDate = l_datBaseDate;
        if (l_datBaseDate == null)
        {
            l_datOrderBizDate = 
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        
        //�Q�j�萔���I�u�W�F�N�g�𐶐����A�v���p�e�B�Ɉȉ��̒ʂ�Z�b�g���s���B

        WEB3GentradeCommission l_gentradeCommission = 
            new WEB3GentradeCommission();

        //�،���ЃR�[�h�F �⏕����.getInstitution().getInstitutionCode()
        l_gentradeCommission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //���XID�F �⏕����.get����X().BranchId()
        l_gentradeCommission.setBranchId(l_subAccount.getMainAccount().getBranch().getBranchId());

        //�萔�����i�R�[�h�F this.get�萔�����i�R�[�h()
        l_gentradeCommission.setCommissionProductCode(this.getCommissionProductCode());

        //����R�[�h�iSONAR�j�F�@@this.get����R�[�h�iSONAR�j(is���t)
        l_gentradeCommission.setSonarTradedCode(this.getSonarTradedCode(l_blnIsBuy));

        //�������F�@@������
        l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        //�ٍϋ敪�F�@@�ٍϋ敪.00�F���̑� 
        l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
        
        FeqOrderUnitRow l_orderUnitRow = 
            (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        
        //�����`���l���F �����`���l��
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(
            OpLoginSecurityService.class);
        String l_strorderChannel = WEB3SessionAttributeDef.ORDER_CHANNEL;
        l_gentradeCommission.setOrderChannel(l_opLoginSec.getSessionProperty(l_strorderChannel));
        
        //is�w�l�F is�w�l����
        l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);

        //�s��R�[�h�iSONAR�j�F�@@�s��.�s��R�[�h�iSONAR�j 
        MarketRow l_marketRow = (MarketRow) (l_market.getDataSourceObject());       
        l_gentradeCommission.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        
        //�����������`���l���F�@@�����P��.���񒍕��̒����`���l��
        l_gentradeCommission.setOrgOrderChannel(l_orderUnitRow.getOrderChanel());
        
        //�������萔��No�F�@@�����P��.���񒍕��̎萔��No 
        l_gentradeCommission.setOrgCommissionNo(l_orderUnitRow.getCommTblNo());
        
        //�������萔��No�}�ԁF�@@�����P��.���񒍕��̎萔��No�}�� 
        l_gentradeCommission.setOrgCommissionRevNo(l_orderUnitRow.getCommTblSubNo());
        
        //�R�j��������i�O�݁j�ɖ�蕪�����Z
        // ��������i�O�݁j�F
        // ����.��������i�O�݁j + �����P��.���v�����z 
        double l_dblTolTradePriceFc = 
            WEB3FeqOrderUtility.decimalPlus(l_dblTradePriceFc, l_orderUnitRow.getExecutedAmount()); 
        
        //�S�j�O���������z�v�Z 
        //this.calc�O���������z(�萔��, �⏕����, �O����������, �s��, 
        //���, ��������i�O�݁j(*), �בփ��[�g, is���t, is���v�Z, is�w�l)��
        //�Ϗ��ideligate�j����B
        //(*)�R�j�ŎZ�o�������� 
        WEB3FeqAmountCalcResult l_calcResult = 
            this.calcFeqAmount(
                l_gentradeCommission,
                l_subAccount,
                l_feqProduct,
                l_market,
                l_datOrderBizDate,
                l_dblTolTradePriceFc,
                l_dblFxRate,
                l_blnIsBuy,
                l_blnIsExecCalc,
                l_blnIsLimitPrice);
        
        //�T�j�@@�O���������z�v�Z���ʃI�u�W�F�N�g�ԋp 
        //�O���������z�v�Z���ʃI�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (calc�O�݊��Z)<BR>
     * �w����z���A�w������ɊY������ʉ݃��[�g�ŊO�݊��Z���s���B<BR>
     * <BR>
     * this.calc�O�݊��Z (double ���z�i�~�݁j, double ���[�g, int ����������, String �O�݊��Z�ۂߕ���)��delegate����B
     * @@param l_dblAmount - ���z�i�~�݁j
     * @@param l_dblRate - �בփ��[�g<BR>
     * ���ʉ݃e�[�u���̈בփ��[�g���g�p����ꍇ��0���w�肷��B
     * @@param l_lngProductId - ����ID
     * @@param l_blnIsBuy - is���t<BR>
     * ���t���̔���<BR>
     * <BR>
     * true�F��<BR>
     * false�F��<BR>
     * @@param l_blnIsExecCalc - is���v�Z<BR>
     * ���v�Z���̔���<BR>
     * <BR>
     * true�F���v�Z<BR>
     * false�F�T�Z�v�Z<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcForeignCCYAmount(
        double l_dblAmount,
        double l_dblRate,
        long l_lngProductId,
        boolean l_blnIsBuy,
        boolean l_blnIsExecCalc)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcForeignCCYAmount(double, double, long, boolean, boolean)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3FeqProductManager l_productManager =
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        WEB3FeqProduct l_product = l_productManager.getFeqProduct(l_lngProductId);
        WEB3GentradeCurrency l_genCurrency = l_product.getCurrency();
        double l_dblFxRate = 
            l_genCurrency.getExchangeRate(l_blnIsBuy, l_blnIsExecCalc, l_dblRate);
        log.debug("�בփ��[�g = " + l_dblFxRate);
        int l_intScale = l_genCurrency.getScale();
        log.debug("���������� = " + l_intScale);
        String l_dblFCCYRoundDiv = l_genCurrency.getChangeFCcyRoundDiv();
        log.debug("�O�݊��Z�ۂߕ��� = " + l_dblFCCYRoundDiv);
        double l_dblForeignCCYAmount = 
            this.calcForeignCCYAmount(l_dblAmount, l_dblFxRate, l_intScale, l_dblFCCYRoundDiv);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblForeignCCYAmount;
    }
    
    /**
     * (get������)<BR>
     * �萔���v�Z�Ŏg�p����ϑ��萔���ڋq�����o�^�}�X�^�[.�ڋq���������擾����B<BR>
     * �擾���@@�͋@@�\��`���u���ʏ����i���o��v�Z�j�v���Q�ƁB<BR>
     * ���萔�����i�R�[�h�́h�O�������h�Œ�B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_lngBranchId - ���XID
     * @@param l_lngAccountId - ����ID
     * @@param l_tsOrderBizDate - ������
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getChargeRatio(
        String l_strInstitutionCode,
        long l_lngBranchId,
        long l_lngAccountId,
        Timestamp l_tsOrderBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChargeRatio(String, long, long, Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        EquityCommAccountCondMstRow l_equityCommAccountCondMstRow = null;
        String l_strOrderBizDate =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_tsOrderBizDate);
        try
        {
            l_equityCommAccountCondMstRow =
                EquityCommAccountCondMstDao.findRowByPk(
                    l_strInstitutionCode,
                    l_lngBranchId,
                    l_lngAccountId,
                    WEB3CommisionProductCodeDef.FOREIGN_EQITY,
                    l_strOrderBizDate);
        }
        catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        double l_dblChargeRatio = l_equityCommAccountCondMstRow.getAccountChargeRatio();
        log.debug("�ϑ��萔���ڋq�����o�^�}�X�^�[�F�ڋq�������F[" + l_dblChargeRatio + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_dblChargeRatio;
    }

    /**
     * (calc�O�݊��Z)<BR>
     * �icalcForignCCYAmount�j<BR>
     * �w����z���w�背�[�g�ŊO�݊��Z���s���B<BR>
     * <BR>
     * ���z�i�~�݁j�����[�g�̌v�Z���ʂ�ԋp����B<BR>
     * �i�v�Z���ʂ́A�����������������O�݊��Z�ۂߕ����Ŋۂߏ������s���j<BR>
     * @@param l_bdAmount - (���z�i�~�݁j)<BR>
     * �����_���܂ދ��z�i�~�݁j<BR>
     * @@param l_dblRate - (���[�g)<BR>
     * ���[�g<BR>
     * @@param l_intScale - (����������)<BR>
     * ����������<BR>
     * @@param l_strChangeFccyRoundDiv - (�O�݊��Z�ۂߕ���)<BR>
     * �O�݊��Z�ۂߕ���<BR>
     * 0�F�l�̌ܓ��@@1�F�؎́@@2�F�؏�<BR>
     * @@return BigDecimal
     * @@throws WEB3BaseException
     */
    public BigDecimal calcForeignCCYAmount(
        BigDecimal l_bdAmount,
        double l_dblRate,
        int l_intScale,
        String l_strChangeFccyRoundDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcForeignCCYAmount(BigDecimal, double, int, String)";
        log.entering(STR_METHOD_NAME);

        if (l_dblRate == 0D)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        BigDecimal l_bdRate = new BigDecimal(String.valueOf(l_dblRate));
        BigDecimal l_bdResult = null;

        //0�F�l�̌ܓ�
        if (WEB3ChangeRoundDivDef.ROUNDING_OFF.equals(l_strChangeFccyRoundDiv))
        {
            l_bdResult =
                l_bdAmount.divide(l_bdRate, l_intScale, BigDecimal.ROUND_HALF_UP);
        }
        //1�F�؎�
        else if (WEB3ChangeRoundDivDef.CUTTING_OFF.equals(l_strChangeFccyRoundDiv))
        {
            l_bdResult =
                l_bdAmount.divide(l_bdRate, l_intScale, BigDecimal.ROUND_DOWN);
        }
        //2�F�؏�
        else if (WEB3ChangeRoundDivDef.RAISING_TO_A_UNIT.equals(l_strChangeFccyRoundDiv))
        {
            l_bdResult =
                l_bdAmount.divide(l_bdRate, l_intScale, BigDecimal.ROUND_UP);
        }
        else
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdResult;
    }

    /**
     * (get�l�b�e�B���O�בփ��[�g)<BR>
     * �l�b�e�B���O�בփ��[�g���擾����B <BR>
     * <BR>
     * �P�j�@@�l�b�e�B���O�בփ��[�g���i�[����HashMap�𐶐�����B <BR>
     * <BR>
     * �Q�j�@@����.�g�����U�N�V�����i������薾�ׁj�̗v�f����Loop���� �B <BR>
     * <BR>
     * �Q�|�P�j�ʉ݃R�[�h���A���n���Z����i�O�݁j���W�v <BR>
     * �ʉ݃R�[�h����������ݻ޸��݁i������薾�ׁj�s.���n���Z����i�O�݁j���W�v����B <BR>
     * �Q�|�P�|�P�j������薾�׍s.�g�����U�N�V�����^�C�v==701�F�O�������̏ꍇ <BR>
     * ���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j�{��������薾�׍s.���n���Z����i�O�݁j <BR>
     * �����F1,2,3...�� <BR>
     * �Q�|�P�|�Q�j������薾�׍s.�g�����U�N�V�����^�C�v==702�F�O������̏ꍇ <BR>
     * ���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j�{��������薾�׍s.���n���Z����i�O�݁j <BR>
     * �����F1,2,3...�� <BR>
     * <BR>
     * �R�j�ʉ݃R�[�h���l�b�e�B���O�בփ��[�g�̌v�Z <BR>
     * �@@�Q�j�擾�����ʉ݃R�[�h���̗v�f����Loop���� <BR>
     * �@@�R�|�P�j���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j == 0�@@�܂��́@@���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j == 0�̏ꍇ�A <BR>
     * �@@HashMap�ɓ��Y�ʉ݃R�[�h�̃l�b�e�B���O�בփ��[�g��ݒ肵�Ȃ��B <BR>
     * �@@�R�|�Q�j��L�ȊO�̏ꍇ�A <BR>
     * �@@���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j - ���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j �� �O�̏ꍇ�A <BR>
     * �@@�l�b�e�B���O�בփ��[�g�i�ʉ݃R�[�h���j���i���ʁj�ʉ�.get���t���בփ��[�g( ) <BR>
     * �@@�ȊO�̏ꍇ�A <BR>
     * �@@�l�b�e�B���O�בփ��[�g�i�ʉ݃R�[�h���j���i���ʁj�ʉ�.get���t���בփ��[�g( ) <BR>
     * �@@�c�c�c <BR>
     * �@@�����F1,2,3...�� <BR>
     * <BR>
     * �S�jHashMap�Ƀl�b�e�B���O�בփ��[�g��ݒ肷��B <BR>
     * key�@@�@@�@@�@@�@@�@@�@@value <BR>
     * �ʉ݃R�[�h�@@�@@�@@�l�b�e�B���O�בփ��[�g <BR>
     * ---------- ----------------------------- <BR>
     * �ʉ݃R�[�h�P�@@�@@�l�b�e�B���O�בփ��[�g�i�ʉ݃R�[�h�P�j <BR>
     * �ʉ݃R�[�h�Q�@@�@@�l�b�e�B���O�בփ��[�g�i�ʉ݃R�[�h�Q�j <BR>
     * �c�@@�@@�@@�@@�@@�@@�@@�@@�c <BR>
     * �ʉ݃R�[�h���@@�@@�l�b�e�B���O�בփ��[�g�i�ʉ݃R�[�h���j <BR>
     * @@param l_lisFeqFinTransactionParams - (�g�����U�N�V�����i������薾�ׁj�sList)<BR>
     * �g�����U�N�V�����i������薾�ׁj�sList<BR>
     * @@return HashMap
     */
    public HashMap getNettingExchangeRate(List l_lisFeqFinTransactionParams) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =  "getNettingExchangeRate(List)";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisFeqFinTransactionParams == null
            || l_lisFeqFinTransactionParams.size() == 0)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        HashMap l_hmNettingExchangeRate = new HashMap();
        HashMap l_hmBalanceAmountFcBuy = new HashMap();
        HashMap l_hmBalanceAmountFcSell = new HashMap();
        HashSet l_hsCurrencyCode = new HashSet();
        Iterator l_iterator = l_lisFeqFinTransactionParams.iterator();
        while (l_iterator.hasNext())
        {
            FeqFinTransactionRow l_feqFinTransactionRow = (FeqFinTransactionRow)l_iterator.next();
            String l_strCurrencyCode = l_feqFinTransactionRow.getCurrencyCode();
            BigDecimal l_bdBalanceAmountFc = new BigDecimal(Double.toString(l_feqFinTransactionRow.getBalanceAmountFc()));
            //�Q�|�P�|�P�j������薾�׍s.�g�����U�N�V�����^�C�v==701�F�O�������̏ꍇ
            if (FinTransactionType.EQTYPE_FEQ_BUY.equals(l_feqFinTransactionRow.getFinTransactionType()))
            {
                //���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j�{��������薾�׍s.���n���Z����i�O�݁j
                if (l_hmBalanceAmountFcBuy.containsKey(l_strCurrencyCode))
                {
                    double l_dblBalanceAmountFcBuy = ((Double)l_hmBalanceAmountFcBuy.get(l_strCurrencyCode)).doubleValue();
                    l_hmBalanceAmountFcBuy.put(l_strCurrencyCode,
                        new Double(l_bdBalanceAmountFc.add(new BigDecimal(Double.toString(l_dblBalanceAmountFcBuy))).doubleValue()));
                }
                else
                {
                    l_hmBalanceAmountFcBuy.put(l_strCurrencyCode, new Double(l_feqFinTransactionRow.getBalanceAmountFc()));
                }
            }
            //�Q�|�P�|�Q�j������薾�׍s.�g�����U�N�V�����^�C�v==702�F�O������̏ꍇ
            else if (FinTransactionType.EQTYPE_FEQ_SELL.equals(l_feqFinTransactionRow.getFinTransactionType()))
            {
                //���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j�{��������薾�׍s.���n���Z����i�O�݁j
                if (l_hmBalanceAmountFcSell.containsKey(l_strCurrencyCode))
                {
                    double l_dblBalanceAmountFcSell = ((Double)l_hmBalanceAmountFcSell.get(l_strCurrencyCode)).doubleValue();
                    l_hmBalanceAmountFcSell.put(l_strCurrencyCode,
                        new Double(l_bdBalanceAmountFc.add(new BigDecimal(Double.toString(l_dblBalanceAmountFcSell))).doubleValue()));
                }
                else
                {
                    l_hmBalanceAmountFcSell.put(l_strCurrencyCode, new Double(l_feqFinTransactionRow.getBalanceAmountFc()));
                }
            }
            l_hsCurrencyCode.add(l_strCurrencyCode);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        long l_lngAccountId = ((FeqFinTransactionRow)l_lisFeqFinTransactionParams.get(0)).getAccountId();
        long l_lngSubAccountId = ((FeqFinTransactionRow)l_lisFeqFinTransactionParams.get(0)).getSubAccountId();
        WEB3GentradeSubAccount l_subAccount = null;
        Institution l_institution = null;
        String l_strInstitutionCode = "";
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(l_lngAccountId, l_lngSubAccountId);
            l_institution = (Institution)l_subAccount.getInstitution();
            l_strInstitutionCode = l_institution.getInstitutionCode();
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

        //�R�j�ʉ݃R�[�h���l�b�e�B���O�בփ��[�g�̌v�Z
        Iterator l_iteratorCurrencyCode = l_hsCurrencyCode.iterator();
        while(l_iteratorCurrencyCode.hasNext())
        {
            String l_strCurrencyCode = (String)l_iteratorCurrencyCode.next();
            if (l_hmBalanceAmountFcBuy.containsKey(l_strCurrencyCode)
                && l_hmBalanceAmountFcSell.containsKey(l_strCurrencyCode))
            {
                GenCurrencyRow l_currencyRow = null;
                try
                {
                    l_currencyRow =
                        GenCurrencyDao.findRowByPk(l_strInstitutionCode, l_strCurrencyCode);
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
                //�i���ʁj�ʉ݂��擾����
                WEB3GentradeCurrency l_gentradeCurrency =
                    new WEB3GentradeCurrency(new GenCurrencyParams(l_currencyRow));
                //�i���ʁj�ʉ�.get���t���בփ��[�g( )
                double l_dblSellExecRate = l_gentradeCurrency.getSellExecRate();
                //�i���ʁj�ʉ�.get���t���בփ��[�g( )
                double l_dblBuyExecRate = l_gentradeCurrency.getBuyExecRate();

                double l_dblBalanceAmountFcBuy = ((Double)l_hmBalanceAmountFcBuy.get(l_strCurrencyCode)).doubleValue();
                double l_dblBalanceAmountFcSell = ((Double)l_hmBalanceAmountFcSell.get(l_strCurrencyCode)).doubleValue();
                BigDecimal l_bdBalanceAmountFcBuy = new BigDecimal(Double.toString(l_dblBalanceAmountFcBuy));
                BigDecimal l_bdBalanceAmountFcSell = new BigDecimal(Double.toString(l_dblBalanceAmountFcSell));
                //���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j - ���t���n���Z����i�O�݁j�i�ʉ݃R�[�h���j �� �O�̏ꍇ
                if (l_bdBalanceAmountFcSell.subtract(l_bdBalanceAmountFcBuy).doubleValue() > 0)
                {
                    l_hmNettingExchangeRate.put(l_strCurrencyCode, new Double(l_dblSellExecRate));
                }
                else
                {
                    l_hmNettingExchangeRate.put(l_strCurrencyCode, new Double(l_dblBuyExecRate));
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_hmNettingExchangeRate;
    }
    
    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCommission(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution)
     */
    public double calcCommission(OrderExecution arg0)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcCapitalGainTax(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum, double)
     */
    public double calcCapitalGainTax(SubAccount arg0, TaxTypeEnum arg1, double arg2)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider#calcExecutionAmount(com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum, long, double, double, com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum)
     */
    public double calcExecutionAmount(ProductTypeEnum arg0, long arg1, double arg2, double arg3, QuantityTypeEnum arg4)
    {
        // TODO Auto-generated method stub
        return 0;
    }
}
@
