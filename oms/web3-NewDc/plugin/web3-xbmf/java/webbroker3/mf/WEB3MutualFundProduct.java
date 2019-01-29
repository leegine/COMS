head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����M�����N���X(WEB3MutualFundProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 ������ (���u) �V�K�쐬
Revesion History : 2004/12/06 ������ (���u) �c�Ή�
Revesion History : 2005/10/18 ��O�� (���u) �t�B�f���e�B�Ή�
Revesion History : 2006/03/08 ��� (SRA) �d�l�ύX�i���f���j�F399
Revesion History : 2006/03/08 �ʉ� (SRA) �d�l�ύX�i���f���j�F401
Revesion History : 2006/05/15 ���� (���u) �d�l�ύX�i���f���j�F411
Revesion History : 2006/06/26 �юu�� (���u) �d�l�ύX�i���f���j:417
Revesion History : 2006/09/11 ���� �d�l�ύX�E���f��491
Revesion History : 2006/10/12 �����F (���u) ���f��498
Revesion History : 2006/10/25 �����F (���u) ���f��513
Revesion History : 2007/02/05 ������ (���u) ���f��529,538
Revesion History : 2007/04/09 ��іQ (���u) ���f�� 555
Revesion History : 2007/10/15 ���^�] (���u) ���f�� 583
*/
package webbroker3.mf;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3FixedBuyPossibleDivDef;
import webbroker3.common.define.WEB3PerferenceMoneyDivDef;
import webbroker3.common.define.WEB3StockTypeBondTypeDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3UnitTypeProductDivDef;
import webbroker3.gentrade.data.ExchangeRateDao;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.FrgnMmfExchangeRateDao;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFPlowbackProductDivDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * �g�����M�����N���X
 *
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3MutualFundProduct extends MutualFundProductImpl
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProduct.class);

    /**
     * (�g�����M����)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * super(����Row)���R�[������B<BR>
     * @@param l_productRow - ����Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8EBD03B9
     */
    public WEB3MutualFundProduct(ProductRow l_productRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * (�g�����M����)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * super(���M����Row)���R�[������B<BR>
     * @@param l_mutualFundProductRow - ���M����Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8EBD0399
     */
    public WEB3MutualFundProduct(MutualFundProductRow l_mutualFundProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundProductRow);
    }

    /**
     * (is�抷�\)<BR>
     * ���������抷�\�Ȗ����ł���� true ���A�����łȂ���� false ��Ԃ��B<BR>
     * <BR>
     * �P�j�@@���M����Params�I�u�W�F�N�g�̎擾�B<BR>
     * �@@this.getDataSourceObject()���R�[�����āA<BR>
     * ���M����Params�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@���M����Params.get�抷�\�O���[�vIDIsNull()�̖߂�l�� true<BR>
     * �@@�̏ꍇ�� false���A�����łȂ���� true ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40ADA4600157
     */
    public boolean isSwitchingAble()
    {
        final String STR_METHOD_NAME = "isSwitchingAble()";
        log.entering(STR_METHOD_NAME);

        //���M����Params�I�u�W�F�N�g�̎擾
        MutualFundProductParams l_mutualFundProductParams =
            new MutualFundProductParams(
                (MutualFundProductRow) this.getDataSourceObject());

        //���M����Params.get�抷�\�O���[�vIDIsNull()�̖߂̔��f
        if (l_mutualFundProductParams.getSwtPossibleGroupIdIsNull())
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
     * (get�抷�\�������X�g)<BR>
     * �抷�\�Ȗ����̃��X�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@���M����Params�I�u�W�F�N�g�̎擾�B<BR>
     * �@@this.getDataSourceObject()���R�[�����āA<BR>
     * ���M����Params�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@���M����Params.get�抷�\�O���[�vIDIsNull()�̖߂�l�� true<BR>
     * �@@�̏ꍇ�� null ��Ԃ��B<BR>
     * <BR>
     * �R�j�@@���M����Params.get�抷�\�O���[�vIDIsNull()�̖߂�l�� false<BR>
     * �@@�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�R�|�P�j�@@���M�����}�X�^�[�e�[�u�����������A<BR>
     * �抷�\�Ȗ����̓��M����Params�I�u�W�F�N�g��List���擾����B<BR>
     * �@@�@@�m���������n<BR>
     * �@@�@@�@@�抷�\�O���[�vID = <BR>
     *          ���M����Params.get�抷�\�O���[�vID()�̖߂�l and<BR>
     *�@@�@@�@@ �،���ЃR�[�h = ���M����Params.get�،���ЃR�[�h()�̖߂�l�@@and <BR>
     * �@@�@@�@@AND ����ID != ���M����Params.get����ID()�̖߂�l and<BR>
     * �@@�@@�@@�ē��������敪 = ���M����Params.get�ē��������敪()�̖߂�l and<BR>
     * �@@�@@�@@���t�J�n�� <= ���ݓ���<BR>
     * �@@�@@�m�\�[�g�����n<BR>
     * �@@�@@�@@�\�����ʂ̏����������R�[�h�̏��� <BR>
     * <BR>
     * �@@�R�|�Q�j�@@���M����Params�I�u�W�F�N�g��List�̌������ȉ��̏������s���B<BR>
     * �@@�@@�|�g�����M�����I�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@�m�R���X�g���N�^�ɓn���p�����^�n<BR>
     * �@@�@@�@@�@@����Row�F ���M����Params<BR>
     * �@@�@@�|���������g�����M����.is�V�X�e���戵()���R�[������B<BR>
     * �@@�@@�@@is�V�X�e���戵()�� false ��Ԃ��ꍇ�͈ȉ��̏����������Ȃ�Ȃ��B<BR>
     * �@@�@@�@@�`�F�b�N�G���[�̏ꍇ�͈ȉ��̏������s��Ȃ��B<BR>
     * �@@�@@�|���������g�����M�������A�g�����M������List�ɒǉ�����B<BR>
     * <BR>
     * �@@�R�|�R�j�@@�g�����M������List�����^�[������B<BR>
     * <BR>
     * �@@�@@�@@
     * @@return List
     * @@roseuid 40AD9EFD0251
     */
    public List getSwitchingAbleProductList()
                 throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSwitchingAbleProductList(Timestamp l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        List rtnList = new Vector();

        //�P�j�@@���M����Params�I�u�W�F�N�g�̎擾�B
        MutualFundProductParams l_mutualFundProductParams =
            new MutualFundProductParams(
                (MutualFundProductRow) this.getDataSourceObject());

        //�Q�j�@@���M����Params.get�抷�\�O���[�vIDIsNull()
        if (l_mutualFundProductParams.getSwtPossibleGroupIdIsNull())
        {
            return null;
        }
        else
        {
            //�R�j�@@���M����Params.get�抷�\�O���[�vIDIsNull()�̖߂�l�� false�̏ꍇ
            String l_whereClause =
                " swt_possible_group_id = ? and institution_code = ? and product_id <> ? " +
                " and plowback_product_div = ? and buy_start_date <= ? ";

            //���ݓ��t�̎擾
            //ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

            Object l_bindVars[] =
                {
                    new Long(l_mutualFundProductParams.getSwtPossibleGroupId()),
                    l_mutualFundProductParams.getInstitutionCode(),
                    new Long(l_mutualFundProductParams.getProductId()),
                    l_mutualFundProductParams.getPlowbackProductDiv(),
                    l_tmsSystemTimestamp
                };

            String l_strSort = " indication_ranking asc, product_code asc";

            List l_lisRows = new Vector();

            try
            {
                //�R�|�P�j�@@���M�����}�X�^�[�e�[�u����������
                QueryProcessor l_QueryProcessor =
                    Processors.getDefaultProcessor();
                l_lisRows =
                    l_QueryProcessor.doFindAllQuery(
                        MutualFundProductRow.TYPE,
                        l_whereClause,
                        l_strSort,
                        null,
                        l_bindVars);
                if (l_lisRows != null && l_lisRows.size() != 0)
                {
                    //�R�|�Q�j�@@���M����Params�I�u�W�F�N�g��List�̌������ȉ��̏������s��
                    int n = l_lisRows.size();
                    WEB3MutualFundOrderManagerReusableValidationsCheck l_mfOrderManagerCheck =
                        (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
                    for (int i = 0; i < n; i++)
                    {
                        //�|�g�����M�����I�u�W�F�N�g�𐶐�����
                        MutualFundProductParams l_mutualFundProductParamsTemp =
                            new MutualFundProductParams(
                                (MutualFundProductRow) l_lisRows.get(i));
                        WEB3MutualFundProduct l_web3MutualFundProduct =
                            new WEB3MutualFundProduct(l_mutualFundProductParamsTemp);
                        log.debug("ProductCode" + i + " = " + l_web3MutualFundProduct.getProductCode());

                        //�|���������g�����M����.is�V�X�e���戵()���R�[������
                        if (l_web3MutualFundProduct.isSystemHandling())
                        {
                            //�|���������g�����M�������A�g�����M������List�ɒǉ�����
                            rtnList.add(l_web3MutualFundProduct);
                        }
                    }
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search table MutualFundProduct");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(" DB�ւ̃A�N�Z�X�Ɏ��s���܂��� when search table MutualFundProduct");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);

            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }

        //�R�|�R�j�@@�g�����M������List�����^�[������
        return rtnList;
    }

    /**
     * (get������)<BR>
     * ��������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get������()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9CF7035B
     */
    public String getMutualProductName()
    {
        String l_strMutualProductName =
            ((MutualFundProductRow) this.getDataSourceObject()).getStandardName();
        return l_strMutualProductName;
    }

    /**
     * (get���t����z)<BR>
     * ���t����z��Ԃ��B<BR>
     * <BR>
     *    this.getDataSourceObject().get���t����z()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return double
     * @@roseuid 40AD9D3C00CB
     */
    public double getConstantValue()
    {
        double l_dblConstantValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyConstantValue();

        return l_dblConstantValue;
    }

    /**
     * (get��񉿊z)<BR>
     * ��񉿊z��Ԃ��B<BR>
     * <BR>
     * �@@this.getDataSourceObject().get��񉿊z()�̖߂�l��ԋp����B <BR>
     * <BR>
     * @@return double
     * @@roseuid 40AD9D6F009C
     */
    public double getSellValue()
    {
        double l_dblSellValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellConstantValue();

        return l_dblSellValue;
    }

    /**
     * (get�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�ʉ݃R�[�h()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40AD9EBB036A
     */
    public String getCurrencyCode()
    {
        String l_strCurrencyCode =
            ((MutualFundProductRow) this.getDataSourceObject()).getCurrencyCode();
        return l_strCurrencyCode;
    }

    /**
     * (get����z�v�Z�P��)<BR>
     * ����z�v�Z�P�ʂ�Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get����z�v�Z�P��()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40ADB1C00213
     */
    public double getConstantValueCalcUnit()
    {
        double l_dblgetConstantValueCalcUnit =
            ((MutualFundProductRow) this.getDataSourceObject()).getConstantValueCalcUnit();
        return l_dblgetConstantValueCalcUnit;
    }

    /**
     * (get���ρi���t�j)<BR>
     * ���ρi���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ρi���t�j()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB26D0128
     */
    public String getAcquiredSettlement()
    {
        String l_strAcquiredSettlement =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuySettlementDiv();
        return l_strAcquiredSettlement;
    }

    /**
     * (get���ρi���j)<BR>
     * ���ρi���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ρi���j()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB28F008C
     */
    public String getSellSettlement()
    {
        String l_strSellSettlement =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSettlementDiv();
        return l_strSellSettlement;
    }

    /**
     * (get��n���@@)<BR>
     * ��n���@@��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��n���@@()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB39203D8
     */
    public String getDeliveryDiv()
    {
        String l_strDeliveryDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getDeliveryMethod();
        return l_strDeliveryDiv;
    }

    /**
     * (get�w����@@�i���t�j)<BR>
     * �w����@@�i���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�w����@@�i���t�j()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB3BA034B
     */
    public String getBuySelectable()
    {
        String l_strBuySelectable =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuySpecityDiv();
        return l_strBuySelectable;
    }

    /**
     * (get�w����@@�i���j)<BR>
     * �w����@@�i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�w����@@�i���j()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB3DD0138
     */
    public String getSellSelectable()
    {
        String l_strSellSelectable =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSpecifyDiv();
        return l_strSellSelectable;
    }

    /**
     * (get�w����@@�i�抷�j)<BR>
     * �w����@@�i�抷�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�w����@@�i�抷�j()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB3F9003E
     */
    public String getSwitchingSelectable()
    {
        String l_strSwitchingSelectable =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtSpecifyDiv();
        return l_strSwitchingSelectable;
    }

//    /**
//     * (get�������@@)<BR>
//     * �������@@��Ԃ��B<BR>
//     * <BR>
//     * this.getDataSourceObject().get�������@@()�̖߂�l��Ԃ��B<BR>
//     * @@return String
//     * @@roseuid 40ADB41E02FD
//     */
//    public String getSellBuyDiv()
//    {
//        String l_strSellBuyDiv =
//            ((MutualFundProductRow) this.getDataSourceObject()).getDeliveryMethod();
//        return l_strSellBuyDiv;
//    }

    /**
     * (get�����^�E���^)<BR>
     * �����^�E���^��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�����^�E���^()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB44D033C
     */
    public String getStockTypeBondType()
    {
        String l_strStockTypeBondType =
            ((MutualFundProductRow) this.getDataSourceObject()).getStockTypeBondType();
        return l_strStockTypeBondType;
    }

    /**
     * (get�_��^�E��Ќ^)<BR>
     * �_��^�E��Ќ^��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�_��^�E��Ќ^()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB4730399
     */
    public String getContractTypeCompanyType()
    {
        String l_strContractTypeCompanyType =
            ((MutualFundProductRow) this.getDataSourceObject()).getContractInstitutionType();
        return l_strContractTypeCompanyType;
    }

    /**
     * (is�抷�D���Ώ�)<BR>
     * ���������抷�D���Ώۂ��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.getDataSourceObject().get�D�����敪()�̖߂�l���h0�F���җD���s�h<BR>
     * �̏ꍇ�� false ���A����ȊO�̏ꍇ�� true ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40ADB49C00DA
     */
    public boolean isSwitchingPerferenceObject()
    {
        final String STR_METHOD_NAME = "isSwitchingPerferenceObject()";
        log.entering(STR_METHOD_NAME);

        if (WEB3PerferenceMoneyDivDef.REDEMPTION_PREFERENCE_IMPOSSIBLE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getPerferenceMoneyDiv()))
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
     * (get���͒P��)<BR>
     * ���͒P�ʂ�Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���͒P��()�̖߂�l��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40ADB54D0222
     */
    public String getInputUnit()
    {
        String l_strInputUnit =
            ((MutualFundProductRow) this.getDataSourceObject()).getInputUnit();
        return l_strInputUnit;
    }

    /**
     * (get�Œ�����i�V�K���t�j)<BR>
     * �Œ�����i�V�K���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ�����i�V�K���t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB56F02BF
     */
    public long getNewBuyMinQty()
    {
        long l_lngNewBuyMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyMinQty();
        return l_lngNewBuyMinQty;
    }

    /**
     * (get�Œ�����i�ǉ����t�j)<BR>
     * �Œ�����i�ǉ����t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ�����i�ǉ����t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB59B0399
     */
    public long getAddBuyMinQty()
    {
        long l_lngAddBuyMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyMinQty();
        return l_lngAddBuyMinQty;
    }

    /**
     * (get�Œ�����i���j)<BR>
     * �Œ�����i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ�����i���j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB59E02CE
     */
    public long getSellMinQty()
    {
        long l_lngSellMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellMinQty();
        return l_lngSellMinQty;
    }

    /**
     * (get�Œ�����i�抷�j)<BR>
     * �Œ�����i�抷�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ�����i�抷�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB5A1009C
     */
    public long getSwitchingMinQty()
    {
        long l_lngSwitchingMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtMinQty();
        return l_lngSwitchingMinQty;
    }

    /**
     * (get�P�ʌ����i�V�K���t�j)<BR>
     * �P�ʌ����i�V�K���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʌ����i�V�K���t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB6150222
     */
    public long getNewBuyUnitQty()
    {
        long l_lngNewBuyUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyUnitQty();
        return l_lngNewBuyUnitQty;
    }

    /**
     * (get�P�ʌ����i�ǉ����t�j)<BR>
     * �P�ʌ����i�ǉ����t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʌ����i�ǉ����t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB6150223
     */
    public long getAddBuyUnitQty()
    {
        long l_lngAddBuyUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyUnitQty();
        return l_lngAddBuyUnitQty;
    }

    /**
     * (get�P�ʌ����i���j)<BR>
     * �P�ʌ����i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʌ����i���j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB6150232
     */
    public long getSellUnitQty()
    {
        long l_lngSellUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellUnitQty();
        return l_lngSellUnitQty;
    }

    /**
     * (get�P�ʌ����i�抷�j)<BR>
     * �P�ʌ����i�抷�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʌ����i�抷�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB6150233
     */
    public long getSwitchingUnitQty()
    {
        long l_lngSwitchingUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtUnitQty();
        return l_lngSwitchingUnitQty;
    }

    /**
     * (get�Œ���z�i�V�K���t�j)<BR>
     * �Œ���z�i�V�K���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ���z�i�V�K���t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0261
     */
    public long getNewBuyMinAmt()
    {
        long l_lngNewBuyMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyMinAmt();
        return l_lngNewBuyMinAmt;
    }

    /**
     * (get�Œ���z�i�ǉ����t�j)<BR>
     * �Œ���z�i�ǉ����t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ���z�i�ǉ����t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0262
     */
    public long getAddBuyMinAmt()
    {
        long l_lngAddBuyMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyMinAmt();
        return l_lngAddBuyMinAmt;
    }

    /**
     * (get�Œ���z�i���j)<BR>
     * �Œ���z�i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ���z�i���j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0263
     */
    public long getSellMinAmt()
    {
        long l_lngSellMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellMinAmt();
        return l_lngSellMinAmt;
    }

    /**
     * (get�Œ���z�i�抷�j)<BR>
     * �Œ���z�i�抷�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ���z�i�抷�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0270
     */
    public long getSwitchingMinAmt()
    {
        long l_lngSwitchingMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtMinAmt();
        return l_lngSwitchingMinAmt;
    }

    /**
     * (get�P�ʋ��z�i�V�K���t�j)<BR>
     * �P�ʋ��z�i�V�K���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʋ��z�i�V�K���t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0271
     */
    public long getNewBuyUnitAmt()
    {
        long l_lngNewBuyUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyUnitAmt();
        return l_lngNewBuyUnitAmt;
    }

    /**
     * (get�P�ʋ��z�i�ǉ����t�j)<BR>
     * �P�ʋ��z�i�ǉ����t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʋ��z�i�ǉ����t�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0272
     */
    public long getAddBuyUnitAmt()
    {
        long l_lngAddBuyUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyUnitAmt();
        return l_lngAddBuyUnitAmt;
    }

    /**
     * (get�P�ʋ��z�i���j)<BR>
     * �P�ʋ��z�i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʋ��z�i���j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0280
     */
    public long getSellUnitAmt()
    {
        long l_lngSellUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellUnitAmt();
        return l_lngSellUnitAmt;
    }

    /**
     * (get�P�ʋ��z�i�抷�j)<BR>
     * �P�ʋ��z�i�抷�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʋ��z�i�抷�j()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB67C0281
     */
    public long getSwitchingUnitAmt()
    {
        long l_lngSwitchingUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtUnitAmt();
        return l_lngSwitchingUnitAmt;
    }

    /**
     * (is�V�X�e���戵)<BR>
     * ��������WEBBROKER�V�Ŏ�舵���邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * this.getDataSourceObject().get�V�X�e���戵�t���O()�̖߂�l��<BR>
     * �h1�FWEBBROKER�V�Ŏ�舵���h�̏ꍇ�� true ���A����ȊO��<BR>
     * �ꍇ�� false ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40B46AA301D4
     */
    public boolean isSystemHandling()
    {
        final String STR_METHOD_NAME = "isSystemHandling()";
        log.entering(STR_METHOD_NAME);

        if (WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getSystemHandlingDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is���t�����L��)<BR>
     * �������ɔ��t�������ۂ����Ă��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * this.getDataSourceObject().get���t�����敪( )�̖߂�l��<BR>
     * "0�F���t�\"�̏ꍇ�Afalse��ԋp����B<BR>
     * this.getDataSourceObject().get���t�����敪( )�̖߂�l��<BR>
     * "1�F�抷���t�̂݉\"�̏ꍇ�Atrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40DB85A00399
     */
    public boolean isAcquiredDeregExistence()
    {
        final String STR_METHOD_NAME = "isAcquiredDeregExistence()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get���t�����敪( )�̖߂�l��"0�F���t�\"�̏ꍇ
        if (WEB3BuyLimitDivDef.BUY_POSSIBLE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getBuyLimitDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //this.getDataSourceObject().get���t�����敪( )�̖߂�l��"1�F�抷���t�̂݉\"�̏ꍇ
        if (WEB3BuyLimitDivDef.ONLY_SWITCHING_BUY_POSSIBLE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getBuyLimitDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //���̑�
        log.debug("�\�����Ȃ��Ɩ��G���[���������܂���");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00010,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            "�\�����Ȃ��Ɩ��G���[���������܂���");
    }

    /**
     * (get�Q�l����z)<BR>
     * �Q�l����z��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Q�l����z()�̖߂�l��Ԃ��B<BR>
     * @@return double
     * @@roseuid 40CE78CA0018
     */
    public double getRefConstantValue()
    {
        double l_dblRefConstantValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getReferenceConstantValue();
        return l_dblRefConstantValue;
    }

    /**
     * (get����z�K�p��)<BR>
     * ����z�K�p����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get����z�K�p��()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40CE7CE800E4
     */
    public Timestamp getConstantValueAppDate()
    {
        Timestamp l_ConstantValueAppDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getConstantValueAppDate();
        return l_ConstantValueAppDate;
    }

    /**
     * (get�ݒ��)<BR>
     * �ݒ����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�ݒ��()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40E93A7B03C7
     */
    public Timestamp getSettingDate()
    {
        Timestamp l_SettingDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getSettingDate();
        return l_SettingDate;
    }

    /**
     * (get���ғ�)<BR>
     * ���ғ���Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ғ�()�̖߂�l��Ԃ��B<BR>
     * @@return Timestamp
     * @@roseuid 40E93AB9033A
     */
    public Timestamp getRefundDate()
    {
        Timestamp l_RefundDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRedemptionDate();
        return l_RefundDate;
    }

    /**
     * (is���t�\)<BR>
     * �����������ݓ��t�Ǝw�肳�ꂽ�������ɔ��t�\�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���ݓ��t�̎擾
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A<BR>
     * �@@���ݓ������擾����B<BR>
     * <BR>
     * �Q�j�@@���t�J�n�����擾����B<BR>
     * �@@this.getDataSourceObject().get���t�J�n��()���R�[�����āA<BR>
     * ���t�J�n�����擾����B<BR>
     * <BR>
     * �R�j�@@���t�I�������擾����B<BR>
     * �@@this.getDataSourceObject().get���t�I����()���R�[�����āA<BR>
     * ���t�I�������擾����B<BR>
     * <BR>
     * �S�j�@@���t�J�n���Ɣ��t�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * �T�j�@@���t�I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����<BR>
     * <BR>
     * �U�j�ȉ��̏����ɍ��v����ꍇ�� true ���A�����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * �@@�@@���t�J�n�� �� ���ݓ��� ���� ������ �� ���t�I����<BR>
     * <BR>
     * @@param l_datOrderBizDate - ������
     * @@return boolean
     * @@roseuid 40CE889F0299
     */
    public boolean isAcquiredPossible(Date l_datOrderBizDate)
    {
        final String STR_METHOD_NAME =
            "isAcquiredPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���ݓ��t�̎擾
        //ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

        //�Q�j�@@���t�J�n�����擾����
        Timestamp l_tmsBuyStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyStartDate();

        //�R�j�@@���t�I�������擾����
        Timestamp l_tmsBuyEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyEndDate();

        //�S�j�@@���t�J�n���Ɣ��t�I�������ݒ肳��Ă��Ȃ��ꍇ
        if (l_tmsBuyStartDate == null || l_tmsBuyEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�T�j�@@���t�I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strEndDate = l_dateFormat.format(l_tmsBuyEndDate);


        //�U�j ���t�J�n�� �� ���ݓ��� ���@@������ �� ���t�I����
        if (l_tmsSystemTimestamp.compareTo(l_tmsBuyStartDate) >= 0
            && l_strEndDate.compareTo(l_strOrderBizDate) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is���抷�\)<BR>
     * ���������w�肳�ꂽ�������ɉ��܂��͏抷���\�����`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@���抷�J�n�����擾����B<BR>
     * �@@this.getDataSourceObject().get���抷�J�n��()���R�[�����āA<BR>
     * ���抷�J�n�����擾����B<BR>
     * <BR>
     * �Q�j�@@���抷�I�������擾����B<BR>
     * �@@this.getDataSourceObject().get���抷�I����()���R�[�����āA<BR>
     * ���抷�I�������擾����B<BR>
     * <BR>
     * �R�j�@@���抷�J�n���Ɖ��抷�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * �S�j�@@���抷�J�n���A���抷�I�����A����.��������<BR>
     * YYYYMMDD�`���̕�����ɕϊ�����B<BR>
     * <BR>
     * �T�j�@@�S�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A<BR>
     * �����łȂ��ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * �@@�@@���抷�J�n�� �� ������ �� ���抷�I����<BR>
     * <BR>
     * @@param l_datOrderBizDate - ������
     * @@return boolean
     * @@roseuid 40CE8C8D0151
     */
    public boolean isSellSwitchingPossible(Date l_datOrderBizDate)
    {
        final String STR_METHOD_NAME =
            "isSellSwitchingPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        if (l_datOrderBizDate == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^��Null�ł���");
        }

        //�P�j�@@���抷�J�n�����擾����
        Timestamp l_SellSwtStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSwtStartDate();

        //�Q�j�@@���抷�I�������擾����
        Timestamp l_SellSwtEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSwtEndDate();

        //�R�j�@@���抷�J�n���Ɖ��抷�I�������ݒ肳��Ă��Ȃ��ꍇ
        if (l_SellSwtStartDate == null || l_SellSwtEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�S�j�@@���抷�J�n���A���抷�I�����A����.�������� YYYYMMDD�`���̕�����ɕϊ�����
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strSellSwtStartDate = l_dateFormat.format(l_SellSwtStartDate);
        String l_strSellSwtEndDate = l_dateFormat.format(l_SellSwtEndDate);

        //�T�j���抷�J�n�� �� ������ �� ���抷�I����
        if (l_strOrderBizDate.compareTo(l_strSellSwtStartDate) >= 0
            && l_strSellSwtEndDate.compareTo(l_strOrderBizDate) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�����^)<BR>
     * �������������^���ǂ����𔻒肷��B <BR>
     * <BR>
     * this.getDataSourceObject().get�����^�E���^()�̖߂�l�� <BR>
     * �h1�F�����^�h�̏ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B<BR>
     * @@return boolean
     * @@roseuid 40B46AA301D4
     */
    public boolean isStockType()
    {
        final String STR_METHOD_NAME = "isStockType()";
        log.entering(STR_METHOD_NAME);

        if (WEB3StockTypeBondTypeDef.STOCK_TYPE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getStockTypeBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get���搿���J�n��)<BR>
     * ���搿���J�n����Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���搿���J�n��()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB6150222
     */
    public Timestamp getBuyClaimStartDate()
    {
        Timestamp l_datBuyClaimStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyClaimStartDate();
        return l_datBuyClaimStartDate;
    }

    /**
     * (get���搿���I����)<BR>
     * ���搿���I������Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���搿���I����()�̖߂�l��Ԃ��B<BR>
     * @@return long
     * @@roseuid 40ADB6150222
     */
    public Timestamp getBuyClaimEndDate()
    {
        Timestamp l_datBuyClaimEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyClaimEndDate();
        return l_datBuyClaimEndDate;
    }

    /**
     * (get��W���z)<BR>
     * ��W���z��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��W���z()�̖߂�l��ԋp����B <BR>
     * <BR>
     * @@return double
     * @@roseuid 40ADB6150222
     */
    public double getRecruitConstantValue()
    {
        final String STR_METHOD_NAME = " getRecruitConstantValue()";
        log.entering(STR_METHOD_NAME);

        double l_dblRecruitConstantValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitConstantValue();

        log.exiting(STR_METHOD_NAME);
        return l_dblRecruitConstantValue;
    }


    /**
     * (get���ρi��W�j)<BR>
     * ���ρi��W�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���ρi��W�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitSettlementDiv()
    {
        final String STR_METHOD_NAME = " getRecruitSettlementDiv()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get���ρi��W�j()�̖߂�l��Ԃ��B
        String l_strRecruitSettlementDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitSettlementDiv();
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitSettlementDiv;
    }

    /**
     * (get�Œ�����i��W�j)<BR>
     * �Œ�����i��W�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�Œ�����i��W�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitMinQty()
    {
        final String STR_METHOD_NAME = " getRecruitMinQty()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get�Œ�����i��W�j()�̖߂�l��Ԃ��B
        String l_strRecruitMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitMinQty() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitMinQty;
    }

    /**
     * (get�P�ʌ����i��W�j)<BR>
     * �P�ʌ����i��W�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʌ����i��W�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitUnitQty()
    {
        final String STR_METHOD_NAME = " getRecruitUnitQty()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get�P�ʌ����i��W�j()�̖߂�l��Ԃ��B
        String l_strRecruitUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitUnitQty() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitUnitQty;
    }

    /**
     * (get�Œ���z�i��W�j)<BR>
     * �Œ���z�i��W�j��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�Œ���z�i��W�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitMinAmt()
    {
        final String STR_METHOD_NAME = " getRecruitMinAmt()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get�Œ���z�i��W�j()�̖߂�l��Ԃ��B
        String l_strRecruitMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitMinAmt() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitMinAmt;
    }

    /**
     * (get�P�ʋ��z�i��W�j)<BR>
     * �P�ʋ��z�i��W�j��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get�P�ʋ��z�i��W�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitUnitAmt()
    {
        final String STR_METHOD_NAME = " getRecruitUnitAmt";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get�P�ʋ��z�i��W�j()�̖߂�l��Ԃ��B
        String l_strRecruitUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitUnitAmt() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitUnitAmt;
    }

    /**
     * (is��W�\) <BR>
     * ���������w�肳�ꂽ�������ɕ�W�\�����`�F�b�N����B <BR>
     * <BR>
     *�P�j�@@���ݓ������擾����B  <BR>
     *�@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A<BR>
     *���ݓ������擾����B<BR>
     *<BR>
     *�Q�j�@@��W�J�n�����擾����B  <BR>
     *�@@this.getDataSourceObject().get��W�J�n��()���R�[�����āA<BR>
     *��W�J�n�����擾����B  <BR>
     *<BR>
     *�R�j�@@��W�I�������擾����B  <BR>
     *�@@this.getDataSourceObject().get��W�I����()���R�[�����āA<BR>
     *��W�I�������擾����B  <BR>
     *<BR>
     *�S�j�@@��W�J�n�����邢�͕�W�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ��B  <BR>
     *<BR>
     *�T�j�@@��W�I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����B  <BR>
     *<BR>
     *�U�j�@@�T�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A<BR>
     *�����łȂ��ꍇ��false ��Ԃ��B  <BR>
     *<BR>
     *�@@�@@��W�J�n�� �� ���ݓ��� �@@���@@������ �� ��W�I���� <BR>
     * <BR>
     * @@param l_datOrderBizDate - ������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40ADB6150222
     */
    public boolean isRecruitPossible(Date l_datOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " isRecruitPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        if (l_datOrderBizDate == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�����[�^��Null�ł���");
        }

        //1���ݓ��t�̎擾
        //ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A���ݓ������擾����B
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

        //2�j�@@��W�J�n�����擾����B
        //this.getDataSourceObject().get��W�J�n��()���R�[�����āA��W�J�n�����擾����B
        Timestamp l_tsRecruitStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitStartDate();
        //3�j�@@��W�I�������擾����B
        //this.getDataSourceObject().get��W�I����()���R�[�����āA��W�I�������擾����B
        Timestamp l_tsRecruitEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitEndDate();
        //4�j�@@��W�J�n�����邢�͕�W�I�������ݒ肳��Ă��Ȃ��ꍇ�� false ��Ԃ�
        if (l_tsRecruitStartDate == null || l_tsRecruitEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //5�j�@@��W�I�����A����.��������YYYYMMDD�`���̕�����ɕϊ�����B
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strRecruitEndDate = l_dateFormat.format(l_tsRecruitEndDate);

        //6�j�@@5�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A
        //  �����łȂ��ꍇ�� false ��Ԃ��B
        //  ��W�J�n�� �� ���ݓ��� �@@���@@������ �� ��W�I����
        if (l_tmsSystemTimestamp.compareTo(l_tsRecruitStartDate) >= 0
            && l_strRecruitEndDate.compareTo(l_strOrderBizDate) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get��W�J�n��)<BR>
     * ��W�J�n����Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get��W�J�n��()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return Timestamp
     * @@roseuid 40ADB6150222
     */
    public Timestamp getRecruitStartDate()
    {
        final String STR_METHOD_NAME = " getRecruitStartDate()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get��W�J�n��()�̖߂�l��Ԃ��B
        Timestamp l_tsRecruitStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitStartDate();
        log.exiting(STR_METHOD_NAME);
        return l_tsRecruitStartDate;
    }

    /**
     * (get��W�I����)<BR>
     * ��W�I������Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get��W�I����()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return Timestamp
     * @@roseuid 40ADB6150222
     */
    public Timestamp getRecruitEndDate()
    {
        final String STR_METHOD_NAME = " getRecruitEndDate()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get��W�I����()�̖߂�l��Ԃ��B
        Timestamp l_tsRecruitEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitEndDate();
        log.exiting(STR_METHOD_NAME);
        return l_tsRecruitEndDate;
    }

    /**
     * (get�בփ��[�g)<BR>
     * �בփ��[�g�e�[�u���̍s�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�ȉ��̏����ňבփ��[�g�e�[�u�����烌�R�[�h���擾����B<BR>
     *  [����] <BR>
     * �،���ЃR�[�h = this.getInstitution().getInstitutionCode() �̖߂�l <BR>
     * �ʉ݃R�[�h = this.get�ʉ݃R�[�h() �̖߂�l <BR>
     * �Q�j�擾�������R�[�h�̍s�I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * @@return �בփ��[�gParams
     * @@throws WEB3BaseException
     * @@roseuid 40ADB6150222
     */
    public ExchangeRateParams getExchangeRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExchangeRate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̏����ňבփ��[�g�e�[�u�����烌�R�[�h���擾����B
        //�،���ЃR�[�h = this.getInstitution().getInstitutionCode() �̖߂�l
        String l_strInstitutionCode = this.getInstitution().getInstitutionCode();
        //�ʉ݃R�[�h = this.get�ʉ݃R�[�h() �̖߂�l
        String l_strCurrencyCode = this.getCurrencyCode();

        ExchangeRateParams l_exchangeRateParams = null;
        try
        {
            ExchangeRateRow l_exchangeRateRow =
                ExchangeRateDao.findRowByPk(
                    l_strInstitutionCode, l_strCurrencyCode);
            l_exchangeRateParams =
                new ExchangeRateParams(l_exchangeRateRow);
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

        //�Q�j�擾�������R�[�h�̍s�I�u�W�F�N�g��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_exchangeRateParams;
    }

    /**
     * (is�O�����M)<BR>
     * ���������O�����M���ǂ����𔻒肷��B<BR>
     * <BR>
     * �ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B<BR>
     *  this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h���O�h <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isForeignFund()
    {
        final String STR_METHOD_NAME = " isForeignFund()";
        log.entering(STR_METHOD_NAME);
        //�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B
        //this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h���O�h
        MutualFundTypeEnum l_mutualFundType =
            ((MutualFundProductRow) this.getDataSourceObject()).getFundType();

        if (MutualFundTypeEnum.FOREIGN.equals(l_mutualFundType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�O�݌����M)<BR>
     * ���������O�݌����M���ǂ����𔻒肷��B<BR>
     * <BR>
     * �ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B <BR>
     * <BR>
     * this.get�ʉ݃R�[�h()�̖߂�l != �h�~�h ����<BR>
     * this.is�O��MMF = false <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isForeignCurencyFund()
    {
        final String STR_METHOD_NAME = "isForeignCurencyFund()";
        log.entering(STR_METHOD_NAME);
        //�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B
        //this.get�ʉ݃R�[�h()�̖߂�l != �h�~�h

        String l_strCurrencyCode = this.getCurrencyCode();

        //this.get�ʉ݃R�[�h()�̖߂�l != �h�~�h ����
        //this.is�O��MMF = false
        if (!WEB3MFOrderQuantityType.EN.equals(l_strCurrencyCode)
            && !this.isFrgnMmf())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�w����@@�i��W�j)<BR>
     * �w����@@�i��W�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�w����@@�i��W�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitSpecityDiv()
    {
        final String STR_METHOD_NAME = "getRecruitSpecityDiv()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get�w����@@�i��W�j()�̖߂�l��Ԃ��B
        String l_strRecruitSpecityDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitSpecityDiv();
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitSpecityDiv;
    }

    /**
     * (isFWF)<BR>
     * ��������FWF�i�t�B�f���e�B�E���[���h�E�t�@@���Y�j���ǂ����𔻒肷��B <BR>
     * <BR>
     * �ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h���̑��h <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isFWF()
    {
        final String STR_METHOD_NAME = "isFWF()";
        log.entering(STR_METHOD_NAME);

        MutualFundTypeEnum l_fundType =
            ((MutualFundProductRow) this.getDataSourceObject()).getFundType();

        //�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B
        //this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h���̑��h
        if (MutualFundTypeEnum.OTHER.equals(l_fundType))
        {
            log.debug("get���M�^�C�v()�̖߂�l == �h���̑��h");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("get���M�^�C�v()�̖߂�l != �h���̑��h");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is�������M)<BR>
     * ���������������M���ǂ����𔻒肷��B<BR>
     * <BR>
     * �ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B<BR>
     *  this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h�����h <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isDomesticFund()
    {
        final String STR_METHOD_NAME = " isDomesticFund()";
        log.entering(STR_METHOD_NAME);
        //�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B
        //this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h�����h
        MutualFundTypeEnum l_mutualFundType =
            ((MutualFundProductRow) this.getDataSourceObject()).getFundType();

        if (MutualFundTypeEnum.DOMESTIC.equals(l_mutualFundType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�ē�������)<BR>
     * ���������ē����������ǂ����𔻒肷��B<BR>
     * <BR>
     * �ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B<BR>
     *  this.getDataSourceObject().get�ē��������敪()�̖߂�l == �h�ē����R�[�X�����h <BR>
     * <BR>
     * @@return boolean
     * @@roseuid
     */
    public boolean isPlowbackProduct()
    {
        final String STR_METHOD_NAME = " isPlowbackProduct()";
        log.entering(STR_METHOD_NAME);
        //�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B
        //this.getDataSourceObject().get�ē��������敪()�̖߂�l == �h�ē����R�[�X�����h
        String l_strPlowbackProductDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getPlowbackProductDiv();

        if (l_strPlowbackProductDiv.equals(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�~�]����z)<BR>
     * �~�]����z���擾���� <BR>
     * <BR>
     * �P�jthis.get�בփ��[�g()�ɂĈבփ��[�gParams�擾����B <BR>
     * <BR>
     * �Q�j-�P�@@�����F�����敪�����t�̏ꍇ <BR>
     *�@@�@@�@@�@@�@@this.get���t����z() �~ �בփ��[�gParams��TTS�@@���@@<BR>
     *�@@�@@�@@�@@�@@�בփ��[�gParams�̈בփ��[�g�v�Z�P�� (�����_�ȉ��l�̌ܓ�)<BR>
     *�@@�@@�@@�@@�@@��Ԃ��B <BR>
     * <BR>
     * �Q�j-�Q�@@�����F�����敪�����̏ꍇ <BR>
     *�@@�@@�@@�@@�@@this.get��񉿊z() �~ �בփ��[�gParams��TTB�@@���@@<BR>
     *�@@�@@�@@�@@�@@�בփ��[�gParams�̈בփ��[�g�v�Z�P�ʁ@@ (�����_�ȉ��l�̌ܓ�)<BR>
     *�@@�@@�@@�@@�@@��Ԃ��B <BR>
     * @@param  l_strStatus - (�����敪)
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getYenConstantValue(String l_strStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getYenConstantValue(String)";
        log.entering(STR_METHOD_NAME);

        //�P�jthis.get�בփ��[�g()�ɂĈבփ��[�gParams�擾����B
        ExchangeRateParams l_exchangeRateParams = this.getExchangeRate();
        BigDecimal l_bdYenConstantValue = null;

        //�Q�j-�P�@@�����F�����敪�����t�̏ꍇ
        //�@@�@@�@@�@@�@@this.get���t����z() �~ �בփ��[�gParams��TTS�@@
        //�@@�@@�@@�@@�@@���@@�בփ��[�gParams�̈בփ��[�g�v�Z�P�� (�����_�ȉ��l�̌ܓ�)
        //�@@�@@�@@�@@�@@��Ԃ��B
        if(WEB3MFProcessDivDef.BUY.equals(l_strStatus))
        {
            BigDecimal l_bdTtSellRate =
                new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
            BigDecimal l_bdExchangeCalcUnit =
                new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            BigDecimal l_bdConstantValue =
                new BigDecimal(Double.toString(this.getConstantValue()));

            l_bdYenConstantValue =
                l_bdConstantValue.multiply(l_bdTtSellRate).divide(
                    l_bdExchangeCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
        }

        //�Q�j-�Q�@@�����F�����敪�����̏ꍇ
        //�@@�@@�@@�@@�@@this.get��񉿊z() �~ �בփ��[�gParams��TTB�@@���@@
        //�@@�@@�@@�@@�@@�בփ��[�gParams�̈בփ��[�g�v�Z�P�ʁ@@ (�����_�ȉ��l�̌ܓ�)
        //�@@�@@�@@�@@�@@��Ԃ��B
        else if(WEB3MFProcessDivDef.SELL.equals(l_strStatus))
        {
            BigDecimal l_bdTtBuyRate =
                new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
            BigDecimal l_bdExchangeCalcUnit =
                new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            BigDecimal l_bdSellValue =
                new BigDecimal(Double.toString(this.getSellValue()));

            l_bdYenConstantValue =
                l_bdSellValue.multiply(l_bdTtBuyRate).divide(
                    l_bdExchangeCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdYenConstantValue.toString();
    }

    /**
     * (is�莞��z���t�\)<BR>
     * ���������莞��z�\�������ǂ����𔻒肷��B<BR>
     * <BR>
     * this.getDataSourceObject().get�莞��z���t�\�敪( )�̖߂�l�� <BR>
     * "1�F�莞��z���t�\"�̏ꍇ�Atrue��ԋp����B <BR>
     * ����ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isFixedBuyPossible()
    {
        final String STR_METHOD_NAME = " isFixedBuyPossible()";
        log.entering(STR_METHOD_NAME);

        //���������莞��z�\�������ǂ����𔻒肷��
        // "1�F�莞��z���t�\"�̏ꍇ�Atrue��ԋp����B
        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();

        if (WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE.equals(
            l_fundProductRow.getFixedBuyPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //����ȊO�̏ꍇ�Afalse��ԋp����
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get��W�J�n���iSONAR�j)<BR>
     * ��W�J�n���iSONAR�j��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get��W�J�n���iSONAR�j()�̖߂�l��Ԃ��B<BR>
     */
    public Timestamp getApplyAbleStartDateSonar()
    {
        final String STR_METHOD_NAME = " getApplyAbleStartDateSonar()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();

        Timestamp l_tsApplyAbleStartDateSonar = l_fundProductRow.getRecruitStartDateSonar();

        log.exiting(STR_METHOD_NAME);
        return l_tsApplyAbleStartDateSonar;
    }

    /**
     * (get��W�I�����iSONAR�j)<BR>
     * ��W�I�����iSONAR�j��Ԃ��B <BR>
     * <BR>
     * this.getDataSourceObject().get��W�I�����iSONAR�j()�̖߂�l��Ԃ��B<BR>
     */
    public Timestamp getApplyAbleEndDateSonar()
    {
        final String STR_METHOD_NAME = " getApplyAbleEndDateSonar()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();

        Timestamp l_tsApplyAbleEndDateSonar = l_fundProductRow.getRecruitEndDateSonar();

        log.exiting(STR_METHOD_NAME);
        return l_tsApplyAbleEndDateSonar;
    }

    /**
     * (is������������) <BR>
     *�����������������������ǂ����𔻒肷��B <BR>
     *<BR>
     *�P�j�������������敪���擾����B <BR>
     *�@@ this.getDataSourceObject().get�������������敪() <BR>
     *<BR>
     *�Q�@@�ȉ��̇@@�`�C�̏ꍇ��true��Ԃ��B����ȊO�̏ꍇ��false��Ԃ��B <BR>
     *<BR>
     *�@@�@@ �u�����D������ʁ������M���������v�@@���@@�u�������������敪�����t�̂݁v <BR>
     *�@@�A �u�����D������ʁ������M���������v�@@���@@�u�������������敪�������v <BR>
     *�@@�B �u�����D������ʁ������M���������v�@@���@@�u�������������敪�����̂݁v <BR>
     *�@@�C �u�����D������ʁ������M���������v�@@���@@�u�������������敪�������v<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * <BR>
     * 201�F�����M��������<BR>
     * 202�F�����M��������<BR>
     * @@return boolean
     */
    public boolean isUnitTypeProduct(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME = " isUnitTypeProduct(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //�P�j�������������敪���擾����B
        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();
        String l_strUnitTypeProductDiv = l_fundProductRow.getUnitTypeProductDiv();

        //�Q�@@�ȉ��̇@@�`�C�̏ꍇ��true��Ԃ��B����ȊO�̏ꍇ��false��Ԃ��B
        //�@@ �u�����D������ʁ������M���������v�@@���@@�u�������������敪�����t�̂݁v
        //�A �u�����D������ʁ������M���������v�@@���@@�u�������������敪�������v
        //�B �u�����D������ʁ������M���������v�@@���@@�u�������������敪�����̂݁v
        //�C �u�����D������ʁ������M���������v�@@���@@�u�������������敪�������v
        boolean l_blnIsUnitTypeProduct = false;
        if ((OrderTypeEnum.MF_BUY.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.BUY.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        
        if ((OrderTypeEnum.MF_BUY.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.BOTH.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        
        if ((OrderTypeEnum.MF_SELL.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.OF_A_CONTRACT.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        
        if ((OrderTypeEnum.MF_SELL.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.BOTH.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnIsUnitTypeProduct;
    }
    
    /**
     *(is��W�\(SONAR))<BR>
     *���ݓ����ɂ����āA����������W�\(SONAR)�ł��邩���`�F�b�N����B <BR> 
     *<BR>
     *�P�j�@@���ݓ������擾����B  <BR>
     *�@@�@@�@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A<BR>
     * �@@�@@�@@���ݓ������擾����B  <BR>
     *<BR>
     *�Q�j�@@��W�J�n��(SONAR)���擾����B  <BR>
     *�@@this.get��W�J�n���iSONAR�j���R�[�����āA��W�J�n��(SONAR)���擾����B  <BR>
     *<BR>
     *�R�j�@@��W�I����(SONAR)���擾����B  <BR>
     *�@@this.get��W�I�����iSONAR�j���R�[�����āA��W�I����(SONAR)���擾����B  <BR>
     *<BR>
     *�S�j�@@��W�J�n��(SONAR)���邢�͕�W�I����(SONAR)��null�̏ꍇ�� false ��Ԃ��B  <BR>
     *<BR>
     *�T�j�@@��W�J�n��(SONAR)�A��W�I����(SONAR)�A���ݓ�����YYYYMMDD�`���̕�����ɕϊ�����B<BR>
     *<BR>
     *�U�j�@@�T�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A<BR>
     * �@@�@@�@@�@@�����łȂ��ꍇ��false ��Ԃ��B  <BR>
     *<BR>
     *�@@�@@��W�J�n��(SONAR)(YYYYMMDD) �� ���ݓ���(YYYYMMDD) �@@���@@<BR>
     * �@@�@@�@@�@@���ݓ���(YYYYMMDD) �� ��W�I����(SONAR)(YYYYMMDD)<BR>
     * @@return boolean
     */
    public boolean isRecruitPossibleSonar() 
    {
        final String STR_METHOD_NAME = " isRecruitPossibleSonar()";
        log.entering(STR_METHOD_NAME);
        
        //1���ݓ��t�̎擾
        //ThreadLocalSystemAttributesRegistry.getAttribute()���R�[�����A
        //���ݓ������擾����B
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
        
        //�Q�j�@@��W�J�n��(SONAR)���擾����B
        //�@@this.get��W�J�n���iSONAR�j���R�[�����āA��W�J�n��(SONAR)���擾����B
        Timestamp l_tsRecruitStartDateSonar = this.getApplyAbleStartDateSonar();
        
        //�R�j�@@��W�I����(SONAR)���擾����B  
        //�@@this.get��W�I�����iSONAR�j���R�[�����āA��W�I����(SONAR)���擾����B
        Timestamp l_tsRecruitEndtDateSonar = this.getApplyAbleEndDateSonar();
        
        //�S�j�@@��W�J�n��(SONAR)���邢�͕�W�I����(SONAR)��null�̏ꍇ�� false ��Ԃ��B
        if (l_tsRecruitStartDateSonar == null || l_tsRecruitEndtDateSonar == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�T�j�@@��W�J�n��(SONAR)�A��W�I����(SONAR)�A���ݓ�����YYYYMMDD�`���̕�����ɕϊ�����B
        String l_strTsRecruitStartDateSonar =
            WEB3DateUtility.formatDate(l_tsRecruitStartDateSonar, "yyyyMMdd");
        String l_strTsRecruitEndtDateSonar =
            WEB3DateUtility.formatDate(l_tsRecruitEndtDateSonar, "yyyyMMdd");
        String l_strTmsSystemTimestamp =
            WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        
        //�U�j�@@�T�j�ŕϊ����������񂪈ȉ��̏����ɍ��v����ꍇ�� true ���A
        //�����łȂ��ꍇ��false ��Ԃ��B
        if (l_strTmsSystemTimestamp.compareTo(l_strTsRecruitStartDateSonar) >= 0
            && l_strTsRecruitEndtDateSonar.compareTo(l_strTmsSystemTimestamp) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     *(is�O��MMF)<BR>
     * ���������O��MMF���ǂ����𔻒肷��B<BR>
     * <BR>
     * �ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h�O��MMF�h<BR>
     * @@return boolean
     */
    public boolean isFrgnMmf()
    {
        final String STR_METHOD_NAME = "isFrgnMmf()";
        log.entering(STR_METHOD_NAME);

        //�ȉ��̏����ƈ�v����ꍇ�� true ���A����ȊO�̏ꍇ�� false ��Ԃ��B
        //this.getDataSourceObject().get���M�^�C�v()�̖߂�l == �h�O��MMF�h
        boolean l_blnIsFrgnMmf = false;
        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();
        if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_fundProductRow.getFundType()))
        {
            l_blnIsFrgnMmf = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsFrgnMmf;
    }

    /**
     *(get�O��MMF�בփ��[�g)<BR>
     * �O��MMF�בփ��[�g�e�[�u���̍s�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŊO��MMF�בփ��[�g�e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = this.getInstitution().getInstitutionCode() �̖߂�l <BR>
     * �@@�ʉ݃R�[�h = this.get�ʉ݃R�[�h() �̖߂�l <BR>
     * <BR>
     * �Q�j�擾�������R�[�h�̍s�I�u�W�F�N�g��ԋp����B<BR>
     * @@return �O��MMF�בփ��[�gParams <BR>
     * @@throws WEB3BaseException 
     */
    public FrgnMmfExchangeRateParams getFrgnMmfExchangeRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFrgnMmfExchangeRate()";
        log.entering(STR_METHOD_NAME);

        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = null;
        String l_strInstitutionCode = null;
        String l_strCurrencyCode = null;
        try
        {
            //�،���ЃR�[�h = this.getInstitution().getInstitutionCode()
            l_strInstitutionCode = this.getInstitution().getInstitutionCode();

            //�ʉ݃R�[�h = this.get�ʉ݃R�[�h()
            l_strCurrencyCode = this.getCurrencyCode();

            l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams(
                FrgnMmfExchangeRateDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strCurrencyCode));
        }
        catch (DataFindException l_ex)
        {
            log.error("�Y���f�[�^�Ȃ�", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
        
        log.exiting(STR_METHOD_NAME);
        return l_frgnMmfExchangeRateParams;
    }

    /**
     * (get�O�ݍŒ���z�i�V�K���t�j)<BR>
     * �O�ݍŒ���z�i�V�K���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�O�ݍŒ���z�i�V�K���t�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnNewBuyMinAmt()
    {
        final String STR_METHOD_NAME = " getFrgnNewBuyMinAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get�O�ݍŒ���z�i�V�K���t�j()�̖߂�l��Ԃ��B
        long l_lngFrgnNewBuyMinAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnNewBuyMinAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnNewBuyMinAmt;
    }

    /**
     * (get�O�ݍŒ���z�i�ǉ����t�j)<BR>
     * �O�ݍŒ���z�i�ǉ����t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�O�ݍŒ���z�i�ǉ����t�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnAddBuyMinAmt()
    {
        final String STR_METHOD_NAME = " getFrgnAddBuyMinAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get�O�ݍŒ���z�i�ǉ����t�j()�̖߂�l��Ԃ��B
        long l_lngFrgnAddBuyMinAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnAddBuyMinAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnAddBuyMinAmt;
    }

    /**
     * (get�O�ݍŒ���z�i���j)<BR>
     * �O�ݍŒ���z�i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�O�ݍŒ���z�i���j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnSellMinAmt()
    {
        final String STR_METHOD_NAME = " getFrgnSellMinAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get�O�ݍŒ���z�i���j()�̖߂�l��Ԃ��B
        long l_lngFrgnSellMinAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnSellMinAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnSellMinAmt;
    }

    /**
     * (get�O�ݒP�ʋ��z�i�V�K���t�j)<BR>
     * �O�ݒP�ʋ��z�i�V�K���t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�O�ݒP�ʋ��z�i�V�K���t�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnNewBuyUnitAmt()
    {
        final String STR_METHOD_NAME = " getFrgnNewBuyUnitAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get�O�ݒP�ʋ��z�i�V�K���t�j()�̖߂�l��Ԃ��B
        long l_lngFrgnNewBuyUnitAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnNewBuyUnitAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnNewBuyUnitAmt;
    }

    /**
     * (get�O�ݒP�ʋ��z�i�ǉ����t�j)<BR>
     * �O�ݒP�ʋ��z�i�ǉ����t�j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�O�ݒP�ʋ��z�i�ǉ����t�j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnAddBuyUnitAmt()
    {
        final String STR_METHOD_NAME = " getFrgnAddBuyUnitAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get�O�ݒP�ʋ��z�i�ǉ����t�j()�̖߂�l��Ԃ��B
        long l_lngFrgnAddBuyUnitAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnAddBuyUnitAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnAddBuyUnitAmt;
    }

    /**
     * (get�O�ݒP�ʋ��z�i���j)<BR>
     * �O�ݒP�ʋ��z�i���j��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�O�ݒP�ʋ��z�i���j()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnSellUnitAmt()
    {
        final String STR_METHOD_NAME = " getFrgnSellUnitAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get�O�ݒP�ʋ��z�i�ǉ����t�j()�̖߂�l��Ԃ��B
        long l_lngFrgnSellUnitAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnSellUnitAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnSellUnitAmt;
    }

    /**
     * (get��W�萔���敪)<BR>
     * ��W�萔���敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get��W�萔���敪�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     */
    public String getRecruitCommissionDiv()
    {
        final String STR_METHOD_NAME = " getRecruitCommissionDiv()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get��W�萔���敪�̖߂�l��Ԃ��B
        String l_strApplyCommissionDiv =
            ((MutualFundProductRow)this.getDataSourceObject()).getRecruitCommissionDiv();

        log.exiting(STR_METHOD_NAME);
        return l_strApplyCommissionDiv;
    }

    /**
     * (get�I�[�v���E�N���[�Y�敪)<BR>
     * �I�[�v���E�N���[�Y�敪��Ԃ��B<BR>
     * <BR>
     * this.getDataSourceObject().get�I�[�v���E�N���[�Y�敪()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@return String
     */
    public String getOpenCloseDiv()
    {
        final String STR_METHOD_NAME = " getOpenCloseDiv()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get�I�[�v���E�N���[�Y�敪()�̖߂�l��Ԃ��B
        String l_strOpenCloseDiv =
            ((MutualFundProductRow)this.getDataSourceObject()).getOpenCloseDiv();

        log.exiting(STR_METHOD_NAME);
        return l_strOpenCloseDiv;
    }
}
@
