head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ��N���X(WEB3IfoDepositPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/19 �R�c�@@��i(FLJ) �V�K�쐬
 Revesion History : 2007/06/28 hijikata(SRA) �[��Ή� ���f��No.073
 */
package webbroker3.ifodeposit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.IfoDepositDao;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.ProcessManagementDao;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionDao;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.tradingpower.data.TpCashBalanceDao;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�)
 * �؋����v�Z�ɗp����f�[�^�\�[�X�ւ̃A�N�Z�X���Ǘ�����N���X�B
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositPersistentDataManager
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositPersistentDataManager.class);

    /**
     * (get����Params�ꗗ)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * ����Params�̈ꗗ���擾����B<BR>
     * �i�V�K��������ɂ�������ꂽ���R�[�h�͏���)<BR>
     * 
     * ���ʃe�[�u��(ifo_contract)�������̏����Ō����������ʂ�ԋp����B<BR>
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B<BR>
     * 
     * [��������]<BR>
     * ���ʃe�[�u��.����ID�@@==�@@����.����ID<BR>
     * ���ʃe�[�u��.�⏕����ID�@@==�@@����.�⏕����ID<BR>
     * ���ʃe�[�u��.���ʌ����ʁ@@!=�@@0<BR>
     * @@param l_lngAccountId - (����ID)
     * @@param l_lngSubAccountId - (�⏕����ID)�B
     * @@return IfoContractParams[]
     * @@roseuid 411234A70109
     */
    public static IfoContractParams[] getContractParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId)
    {

        IfoContractParams[] l_results = null;
        String l_strWhere = "account_id=? and sub_account_id=? and original_quantity<>?";
        Object[] l_objBindVars =
            { new Long(l_lngAccountId), new Long(l_lngSubAccountId), new Long(0)};
        try
        {

            log.debug(
                "Finding IfoContractRows where="
                    + l_strWhere
                    + ", bindVars="
                    + objectsToString(l_objBindVars));

            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IfoContractRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new IfoContractParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (IfoContractParams)l_list.get(i);
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getContractParamsList(long, long)",
                de);
        }

        return l_results;

    }

    /**
     * (get�g�����U�N�V����Params�ꗗ)�B<BR>
     * <BR>
     * �istatic���\�b�h�j<BR>
     * �g�����U�N�V�����i������薾�ׁjParams�̈ꗗ���擾����B <BR>
     * �g�����U�N�V�����i������薾�ׁj�e�[�u��(ifo_fin_transaction)�������̏����Ō����������ʂ�ԋp����B <BR>
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B <BR>
     * <BR>
     * [��������]<BR> 
     * �g�����U�N�V�����i������薾�ׁj.����ID�@@==�@@����.����ID<BR> 
     * �g�����U�N�V�����i������薾�ׁj.�⏕����ID�@@==�@@����.�⏕����ID<BR> 
     * �g�����U�N�V�����i������薾�ׁj.�g�����U�N�V�����������t(yyyymmdd) == ����.������(yyyymmdd)<BR> 
     * �g�����U�N�V�����i������薾�ׁj.�폜�t���O == false <BR>
     * <BR>
     * ���g�����U�N�V�����������t�ɂ��Ă͓��t�����܂�(����������������)����v����f�[�^���擾����B<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)�B
     * @@param l_lngSubAccountId - (�⏕����ID)�B
     * @@param l_datBizDate - (������)<BR>
     * @@return IfoFinTransactionParams[]
     */
    public static IfoFinTransactionParams[] getFinTransactionParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId,
        Date l_datBizDate)
    {

        IfoFinTransactionParams[] l_results = null;
        String l_strWhere =
            "account_id=? and sub_account_id=? and TRUNC(fin_transaction_timestamp) = ? AND delete_flag=?";
        Object[] l_objBindVars =
            new Object[] {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                l_datBizDate,
                BooleanEnum.FALSE };

        try
        {
            log.debug(
                "Finding IfoFinTransactionRows where="
                    + l_strWhere
                    + ", bindVars="
                    + objectsToString(l_objBindVars));

            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IfoFinTransactionRow.TYPE,
                    l_strWhere,
                    l_objBindVars);

            if (l_list != null && l_list.size() > 0)
            {
                l_results = new IfoFinTransactionParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (IfoFinTransactionParams)l_list.get(i);
                }
            }

        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getFinTransactionParamsList(long, long)",
                de);
        }

        return l_results;

    }

    /**
     * (get�����V�K�������P��Params�ꗗ)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����̐V�K���̒����P�ʃ��R�[�h�̈ꗗ���擾����B<BR>
     * 
     * �����P�ʃe�[�u��(ifo_order_unit)�������̏����Ō����������ʂ�ԋp����B<BR>
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B<BR>
     * 
     * [��������]<BR>
     * �����P�ʃe�[�u��.����ID�@@==�@@����.����ID<BR>
     * �����P�ʃe�[�u��.�⏕����ID�@@==�@@����.�⏕����ID<BR>
     * �����P�ʃe�[�u��.�����J�e�S�� in (�h�敨�V�K�������h�A�hOP�V�K�������h)<BR>
     * �����P�ʃe�[�u��.�����L����� == �h�I�[�v���h<BR>
     * �����P�ʃe�[�u��.�������@@>=�@@����.������<BR>
     * @@param l_lngAccountId - (����ID)
     * @@param l_lngSubAccountId - (�⏕����ID)
     * @@param l_datBizDate - (������)<BR>
     * 
     * �c�Ɠ�[T+0]���w�肷��B<BR>
     * @@return IfoOrderUnitParams[]
     * @@roseuid 4112370E00A3
     */
    public static IfoOrderUnitParams[] getTodayOpenOrderUnitParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId,
        Date l_datBizDate)
    {

        IfoOrderUnitParams[] l_results = null;
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        String l_strWhere =
            "account_id=? and sub_account_id=? and order_categ in (?,?) and order_open_status=? and biz_date>=?";
        Object[] l_objBindVars =
            new Object[] {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderCategEnum.IDX_FUTURES_OPEN,
                OrderCategEnum.IDX_OPTIONS_OPEN,
                OrderOpenStatusEnum.OPEN,
                l_strBizDate };

        try
        {

            log.debug(
                "Finding IfoDepositOrderUnitRows. where="
                    + l_strWhere
                    + ", bindVars="
                    + objectsToString(l_objBindVars));

            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new IfoOrderUnitParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (IfoOrderUnitParams)l_list.get(i);
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTodayOpenOrderUnitParamsList(long, long, Date)",
                de);
        }

        return l_results;

    }

    /**
     * �iget�����U�֒����P��Params�ꗗ�j<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����ɐU�֒������������o���̒����P��Params�̈ꗗ���擾����B<BR>
     * 
     * 
     * ���o���̒����P�ʃe�[�u��(aio_order_unit)�������̏����Ō����������ʂ�ԋp����B<B
     * R>
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B<BR>
     * 
     * [��������]<BR>
     * �����P�ʃe�[�u��.����ID�@@==�@@����.����ID<BR>
     * �����P�ʃe�[�u��.�⏕����ID�@@==�@@����.�⏕����ID<BR>
     * �����P�ʃe�[�u��.������� in 
     * �i�h�U�֒����i�a������犔��؋����h�A�h�U�֒����i����؋�������a����j�h�j<BR>
     * �����P�ʃe�[�u��.������� in 
     * (�h��t��(�V�K����)�h�A�h������(�V�K����)�h�A�h������(�V�K����)�h)<BR>
     * �����P�ʃe�[�u��.�������@@>=�@@����.������<BR>
     * @@param l_lngAccountId - (����ID)
     * @@param l_lngSubAccountId - (�⏕����ID)
     * @@param l_datBizDate - (������)<BR>
     * 
     * �c�Ɠ�[T+0]���w�肷��B<BR>
     * @@return AioOrderUnitParams[]
     * @@roseuid 4112DDAB01C5
     */
    public static AioOrderUnitParams[] getTodayAioOrderUnitParamsList(
        long l_lngAccountId,
        long l_lngSubAccountId,
        Date l_datBizDate)
    {
        AioOrderUnitParams[] l_results = null;
        String l_strBizDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        String l_strWhere =
            "account_id=? and sub_account_id=? and order_type in (?,?) and order_status in (?,?,?) and biz_date>=?";
        Object[] l_objBindVars =
            new Object[] {
                new Long(l_lngAccountId),
                new Long(l_lngSubAccountId),
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN,
                OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT,
                OrderStatusEnum.ACCEPTED,
                OrderStatusEnum.ORDERING,
                OrderStatusEnum.ORDERED,
                l_strBizDate };
        try
        {
            log.debug(
                "Finding AioOrderUnitParams. where="
                    + l_strWhere
                    + ",bindVars="
                    + objectsToString(l_objBindVars));
            List l_list =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AioOrderUnitRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new AioOrderUnitParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (AioOrderUnitParams)l_list.get(i);
                }
            }
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTodayAioOrderUnitParamsList(long, long, Date)",
                de);
        }

        return l_results;
    }

    /**
     * (get����Params)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����Ŏw�肳�ꂽ�����Ɉ�v����敨OP����Params���擾����B<BR>
     * 
     * �敨OP�����e�[�u��(ifo_product)�������̏����Ō����������ʂ�ԋp����B<BR>
     * 
     * [��������]<BR>
     * �敨OP�����e�[�u��.����ID == ����.����ID<BR>
     * @@param l_lngProductId - (�����h�c)
     * @@return IfoProductParams
     * @@roseuid 41255D0D0198
     */
    public static IfoProductParams getProductParams(long l_lngProductId)
    {
        try
        {
            return (IfoProductParams)IfoProductDao.findRowByProductId(l_lngProductId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getProductParams(long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getProductParams(long)",
                de);
        }
    }

    /**
     * (get�������Params)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����Ŏw�肳�ꂽ�����Ɉ�v����敨OP�������Params���擾����B<BR>
     * 
     * �敨OP��������}�X�^(ifo_traded_product)�������̏����Ō����������ʂ�ԋp����B<B
     * R>
     * 
     * [��������]<BR>
     * �敨OP��������}�X�^.����ID == ����.����ID<BR>
     * �敨OP��������}�X�^.�s��ID == ����.�s��ID<BR>
     * @@param l_lngProductId - (�����h�c)
     * @@param l_lngMarketId - (�s��ID)
     * @@return IfoTradedProductParams
     * @@roseuid 41255F3F03CA
     */
    public static IfoTradedProductParams getTradedProductParams(
        long l_lngProductId,
        long l_lngMarketId)
    {
        try
        {
            return (IfoTradedProductParams)IfoTradedProductDao.findRowByProductIdMarketId(
                l_lngProductId,
                l_lngMarketId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTradedProductParams(long, long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTradedProductParams(long, long)",
                de);
        }
    }

    /**
     * (get�������Params)<BR>
     * �istatic���\�b�h�j<BR>
     * �����Ŏw�肳�ꂽ�����Ɉ�v����敨OP�������Params���擾����B(�L�����w��)<BR>
     * 
     * �敨OP��������}�X�^(ifo_traded_product)�������̏����Ō����������ʂ�ԋp����B<B
     * R>
     * 
     * [��������]<BR>
     * �敨OP��������}�X�^.����ID == ����.����ID<BR>
     * �敨OP��������}�X�^.�s��ID == ����.�s��ID<BR>
     * �敨OP��������}�X�^.�L���� == ����.�L����<BR>
     * @@param l_lngProductId - (�����h�c)
     * @@param l_lngMarketId - (�s��ID)
     * @@param l_datValidDate - (�L����)
     * @@return IfoTradedProductParams
     * @@roseuid 4125601D031E
     */
    public static IfoTradedProductParams getTradedProductParams(
        long l_lngProductId,
        long l_lngMarketId,
        Date l_datValidDate)
    {
        IfoTradedProductParams l_tp = getTradedProductParams(l_lngProductId, l_lngMarketId);
        String l_strValidDate =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd").format(l_datValidDate);
        if (l_tp != null && l_strValidDate.equals(l_tp.getValidForBizDate()))
        {
            return l_tp;
        }
        else
        {
            return null;
        }
    }

    /**
     * (get��������ꎞParams)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����Ŏw�肳�ꂽ�����Ɉ�v����敨OP��������ꎞParams���擾����B<BR>
     * 
     * �敨OP��������}�X�^(�ꎞ�e�[�u��)(ifo_traded_product_updq)�������̏����Ō�����?
     * ���ʂ�ԋp����B<BR>
     * 
     * [��������]<BR>
     * �敨OP��������}�X�^(�ꎞ�e�[�u��).����ID == ����.����ID<BR>
     * �敨OP��������}�X�^(�ꎞ�e�[�u��).�s��ID == ����.�s��ID<BR>
     * �敨OP��������}�X�^(�ꎞ�e�[�u��).�،���ЃR�[�h == ����.�،���ЃR�[�h<BR>
     * �敨OP��������}�X�^(�ꎞ�e�[�u��).�L���� == ����.�L����
     * @@param l_lngProductId - (�����h�c)
     * @@param l_lngMarketId - (�s��ID)
     * @@param l_stInstitutionCode - (�،���ЃR�[�h)
     * @@param l_datValidDate - (�L����)
     * @@return IfoTradedProductUpdqParams
     * @@roseuid 4125610802B1
     */
    public static IfoTradedProductUpdqParams getTradedProductUpdqParams(
        long l_lngProductId,
        long l_lngMarketId,
        String l_strInstitutionCode,
        Date l_datValidDate)
    {
        String l_strValidForBizDate =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd").format(l_datValidDate);
        try
        {
            return (
                IfoTradedProductUpdqParams)IfoTradedProductUpdqDao
                    .findRowByValidForBizDateInstitutionCodeMarketIdProductId(
                l_strValidForBizDate,
                l_strInstitutionCode,
                l_lngMarketId,
                l_lngProductId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTradedProductUpdqParams(long, long, String Date)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTradedProductUpdqParams(long, long, String Date)",
                de);
        }
    }

    /**
     * (get�]�͏���Params)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����Ŏw�肳�ꂽ�����Ɉ�v����]�͏���Params���擾����B<BR>
     * 
     * �]�͏����e�[�u��(tradingpower_calc_condition)�������̏����Ō����������ʂ�ԋp��?
     * 
     * 
     * �B<BR>
     * 
     * [��������]<BR>
     * �]�͏����e�[�u��.����ID == ����.����ID<BR>
     * @@param l_lngAccountId - (����ID)
     * @@return webbroker3.gentrade.data.TradingpowerCalcConditionParams
     * @@roseuid 4132E4790376
     */
    public static TradingpowerCalcConditionParams getTradingpowerCalcConditionParams(long l_lngAccountId)
    {
        try
        {
            log.debug("Finding TradingpowerCalcConditionRow by account_id=" + l_lngAccountId);
            return (
                TradingpowerCalcConditionParams)TradingpowerCalcConditionDao.findRowByAccountId(
                l_lngAccountId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTradingPowerCalcConditionParams(long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTradingPowerCalcConditionParams(long)",
                de);
        }
    }

    /**
     * (get�؋���Params)<BR>
     * 
     * �istatic���\�b�h�j<BR>
     * �����Ŏw�肳�ꂽ�����Ɉ�v����؋���Params���擾����B<BR>
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B<BR>
     * 
     * �؋����e�[�u��(ifo_deposit)�������̏����Ō����������ʂ�ԋp����B<BR>
     * 
     * [��������]<BR>
     * �؋����e�[�u��.�،���ЃR�[�h == ����.�،���ЃR�[�h<BR>
     * �؋����e�[�u��.���X�R�[�h == ����.���X�R�[�h<BR>
     * �؋����e�[�u��.�ڋq�R�[�h == ����.�ڋq�R�[�h<BR>
     * �؋����e�[�u��.�v�Z�� == ����.�v�Z��<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - �ڋq�R�[�h�B
     * @@param l_datEstimateDate - �v�Z���B
     * @@return webbroker3.gentrade.data.IfoDepositParams
     * @@roseuid 4132E52201C0
     */
    public static IfoDepositParams getIfoDepositParams(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode,
        Date l_datEstimateDate)
    {

        Timestamp l_datCalcDate = null;
        if (l_datEstimateDate instanceof Timestamp)
        {
            l_datCalcDate = (Timestamp)l_datEstimateDate;
        }
        else
        {
            l_datCalcDate = new Timestamp(l_datEstimateDate.getTime());
        }

        try
        {
            return (
                IfoDepositParams)IfoDepositDao
                    .findRowByInstitutionCodeBranchCodeAccountCodeCalcDate(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_datCalcDate);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getIfoDepositParams(String, String, String, Date)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getIfoDepositParams(String, String, String, Date)",
                de);
        }
    }

    /**
     * (get�ڋq����c���}�X�^���Params)
     * 
     * �istatic���\�b�h�j
     * �����Ŏw�肳�ꂽ�����Ɉ�v����ڋq����c���}�X�^���Params���擾����B
     * 
     * �ڋq����c��(�}�X�^���)�e�[�u��(tp_cash_balance)�������̏����Ō����������ʂ�ԋp����B
     * 
     * [��������]
     * �ڋq����c��(�}�X�^���)�e�[�u��.����ID == ����.����ID
     * �ڋq����c��(�}�X�^���)�e�[�u��.�⏕����ID == ����.�⏕����ID
     * 
     * @@param l_lngAccountId ����ID
     * @@param l_lngSubAccountId �⏕����ID
     * @@return �ڋq����c���}�X�^���Params
     */
    public static TpCashBalanceParams getTpCashBalanceParams(
        long l_lngAccountId,
        long l_lngSubAccountId)
    {
        try
        {
            return (TpCashBalanceParams)TpCashBalanceDao.findRowByAccountIdSubAccountId(
                l_lngAccountId,
                l_lngSubAccountId);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTpCashBalanceParams(long, long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTpCashBalanceParams(long, long)",
                de);
        }
    }

    /**
     * (get�v���Z�X�Ǘ�Params)
     * �istatic���\�b�h�j
     * �����Ŏw�肳�ꂽ�����Ɉ�v����v���Z�X�Ǘ�Params���擾����B
     * 
     * �v���Z�X�Ǘ��e�[�u��(process_management)�������̏����Ō����������ʂ�ԋp����B
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B
     * [��������]
     * �v���Z�X�Ǘ��e�[�u��.�v���Z�XID == ����.�v���Z�XID
     * �v���Z�X�Ǘ��e�[�u��.��ЃR�[�h == ����.�،���ЃR�[�h
     * �v���Z�X�Ǘ��e�[�u��.���X�R�[�h == ����.���X�R�[�h
     * 
     * @@param l_strProcessId �v���Z�XID
     * @@param l_strInstCode �،���ЃR�[�h
     * @@param l_strBranCode ���X�R�[�h
     * @@return �v���Z�X�Ǘ�Params
     */
    public static ProcessManagementParams getProcessManagementParams(
        String l_strProcessId,
        String l_strInstCode,
        String l_strBranCode)
    {
        try
        {
            return (
                ProcessManagementParams)ProcessManagementDao
                    .findRowByProcessIdInstitutionCodeBranchCode(
                l_strProcessId,
                l_strInstCode,
                l_strBranCode);
        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getTpCashBalanceParams(long, long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getTpCashBalanceParams(long, long)",
                de);
        }
    }
    
    /**
     * (get���X�p�v���t�@@�����XParams�ꗗ)
     * 
     * �istatic���\�b�h�j
     * �����Ŏw�肳�ꂽ�����Ɉ�v���镔�X�p�v���t�@@�����XParams�̈ꗗ���擾����B 
     * 
     * ���X�p�v���t�@@�����X�e�[�u��(branch_preferences)�������̏����Ō����������ʂ�ԋp����B 
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B 
     * 
     * [��������]
     * ���X�p�v���t�@@�����X�e�[�u��.���XID == ����.���XID
     * 
     * @@param l_lngBranchId ���XID
     * @@return ���X�p�v���t�@@�����XParams
     */
    public static BranchPreferencesParams[] getBranchPreferencesParamsList(
        long l_lngBranchId)
    {
    
        BranchPreferencesParams[] l_results = null;
        try
        {
            List l_list = BranchPreferencesDao.findRowsByBranchId(l_lngBranchId);
            
            if (l_list != null && l_list.size() > 0)
            {
                l_results = new BranchPreferencesParams[l_list.size()];
                for (int i = 0; i < l_list.size(); i++)
                {
                    l_results[i] = (BranchPreferencesParams)l_list.get(i);
                }
            }
            
            return l_results;
            
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getBranchPreferencesParamsList(long)",
                de);
        }
    }
    
    /**
     * (get�����P��Params)
     * 
     * �istatic���\�b�h�j
     * �Y���̒����P�ʃ��R�[�h���擾����B 
     * 
     * �����P�ʃe�[�u��(ifo_order_unit)�������̏����Ō����������ʂ�ԋp����B
     * ���Y�����R�[�h�����݂��Ȃ��ꍇ�ɂ́Anull��ԋp����B 
     * 
     * [��������]
     * �����P�ʃe�[�u��.�����P��ID�@@==�@@����.�����P��ID
     * 
     * @@param l_lngOrderUnitId   �����P��ID
     * @@return Ifo�����P��Params
     */
    public static IfoOrderUnitParams getIfoOrderUnitParams(
        long l_lngOrderUnitId)
    {
    
        try
        {
            return (IfoOrderUnitParams) IfoOrderUnitDao.findRowByOrderUnitId(
            l_lngOrderUnitId);

        }
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                "getIfoOrderUnitParams(long)",
                dfe);
        }
        catch (DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "getIfoOrderUnitParams(long)",
                de);
        }
    }
    
    private static String objectsToString(Object[] l_objBindVars)
    {

        StringBuffer l_sb = new StringBuffer();
        if (l_objBindVars != null)
        {
            for (int i = 0; i < l_objBindVars.length; i++)
            {
                if (i > 0)
                {
                    l_sb.append(",");
                }
                l_sb.append("[").append(i).append("]=");
                l_sb.append(String.valueOf(l_objBindVars[i]));
            }
        }
        return l_sb.toString();
    }

}
@
