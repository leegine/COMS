head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQMessageContextFactory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3MQMessageContextFactory�N���X(WEB3MQMessageContextFactory.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/02/08 �R�c�@@��i(FLJ) �V�K�쐬
                  : 2005/03/07 �R�c�@@��i(FLJ) �R���e�L�X�g��񂪐ݒ肳��Ă��Ȃ��ꍇ�̏�����ύX
                  : 2005/03/29 �R�c�@@��i(FLJ) DB�A�t�B�j�e�B���̎擾���@@��ύX
                  : 2005/04/22 �R�c�@@��i(FLJ) DB��Q���ɐ������R���e�L�X�g��񂪐ݒ肳��Ă��Ȃ������C��
                  : 2005/05/10 �R�c�@@��i(FLJ) �R���e�L�X�g��񂪐ݒ肳��Ă��Ȃ��ꍇ��OracleSID��ݒ肷��悤�ɕύX
                  : 2005/05/11 �R�c�@@��i(FLJ) �R���e�L�X�g��񂪐ݒ肳��Ă��Ȃ��ꍇ����ЃR�[�h��ݒ肷��悤�ɕύX
                  : 2005/06/03 �R�c�@@��i(FLJ) MultiPoolJndiNameLookupService��null��Ԃ����ꍇ�̏������C��
 */
package webbroker3.mqgateway.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.util.rac.ContextDrivenMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.MultiPoolDataSourceSettings;
import com.fitechlabs.xtrade.plugin.util.rac.MultiPoolJndiNameLookupService;
import com.fitechlabs.xtrade.plugin.util.rac.RoundRobinBasedMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsDao;
import com.fitechlabs.xtrade.plugin.util.rac.data.MpdsSettingsRow;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mqgateway.WEB3MQMessageContext;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.system.data.AffinityRangeBasedMapRow;
import webbroker3.system.tune.affinity.ServerTypeEnum;

/**
 * WEB3MQMessageContext�𐶐�����N���X�B 
 * 
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3MQMessageContextFactory
{
    
    /**
     * MQ���b�Z�[�W�ɐݒ肷��R���e�L�X�g�����쐬����B
     * 
     * @@param l_messageSpec MQ���b�Z�[�W���M���e
     * @@return �R���e�L�X�g���
     * @@throws WEB3SystemLayerException
     */
    WEB3MQMessageContext create(WEB3MQMessageSpec l_messageSpec) throws WEB3SystemLayerException
    {
        
        // �c�Ɠ����擾
        Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        // �ݒ肳��Ă���JNDI�����ʗp�̃R���e�L�X�g���ƒl���擾
        String l_strContextName = getContextName();
        String l_strContextValue = getContextValue();
        
        if (l_strContextName == null || l_strContextValue == null)
        {
            // �R���e�L�X�g��񂪐ݒ肳��Ă��Ȃ��ꍇ��SID�Ɖc�Ɠ��̂ݐݒ肷��
            return new WEB3MQMessageContext(
                l_messageSpec.getInstitutionCode(), 
                getDefaultOracleSid(), 
                null, 
                null, 
                l_datBizDate);
        }
        
        // �V���O���R���e�L�X�g�̏ꍇ�́AContextDrivenMultiPoolDataSource.CTX_NAME_IN_SINGLE_CONTEXT���g�p
        MultiPoolDataSourceSettings l_dataSourceSettings = 
            (MultiPoolDataSourceSettings) Services.getService(MultiPoolDataSourceSettings.class);
        if (l_dataSourceSettings.isSingleContext())
        {
            l_strContextName = ContextDrivenMultiPoolDataSource.CTX_NAME_IN_SINGLE_CONTEXT;
        }
        
        // JNDI���AOracle SID�A�T�[�oID���擾
        String l_strJndiName = getJndiName(l_strContextName, l_strContextValue);
        String l_strOracleSid = getOracleSid(l_strJndiName);
        String l_strServerId = getServerId(l_strJndiName);
        
        // �A�J�E���gID�͈̔͂��擾
        AccountIdRange l_accountIdRange = 
            getAccountIdRange(l_strContextName, l_strContextValue, l_strServerId);
        long l_lngAccountIdStart = l_accountIdRange.accountIdStart;
        long l_lngAccountIdEnd = l_accountIdRange.accountIdEnd;
        
        // ��ЃR�[�h���擾
        String l_strInstitutionCode = l_messageSpec.getInstitutionCode();

        // ��������MQ�R���e�L�X�g����Ԃ�
        return new WEB3MQMessageContext(
                l_strInstitutionCode, 
                l_strOracleSid,
                new Long(l_lngAccountIdStart), 
                new Long(l_lngAccountIdEnd), 
                l_datBizDate);
    }

    /**
     * �g�p����DB�ڑ��v�[����JNDI�����g�p����B
     * 
     * @@param l_strContextName �R���e�L�X�g��
     * @@param l_strContextValue �l
     * 
     * @@return JNDI��
     */
    private String getJndiName(String l_strContextName, String l_strContextValue)
    {
        
        String l_strJndiName = null;
        MultiPoolJndiNameLookupService l_lookupService = (MultiPoolJndiNameLookupService) Services
                .getService(MultiPoolJndiNameLookupService.class);
        l_strJndiName =
            l_lookupService.getJndiName(l_strContextName, l_strContextValue);
        
        if (l_strJndiName == null)
        {
            l_strJndiName = getRoundRobinBasedJndiName();
        }
        
        return l_strJndiName;
    }

    /**
     * RoundRobinBasedMultiPoolDataSource���g�p����f�[�^�\�[�X��JNDI�����擾����B
     * 
     * @@return JNDI��
     */
    private String getRoundRobinBasedJndiName()
    {
        RoundRobinBasedMultiPoolDataSource l_ds =
            (RoundRobinBasedMultiPoolDataSource) Services.getService(
                RoundRobinBasedMultiPoolDataSource.class);
        return l_ds.getJndiName();
    }

    /**
     * �g�p����DB�ڑ��v�[���Ƀ}�b�s���O����Ă���OracleSID���擾����B
     * 
     * @@param l_strJndiName
     *            DB�ڑ��v�[����JNDI��
     * @@return Oracle SID
     * @@throws WEB3SystemLayerException
     */
    private String getOracleSid(String l_strJndiName) throws WEB3SystemLayerException
    {
        
        try
        {
            MpdsSettingsRow l_mpdsSetting = MpdsSettingsDao.findRowByPk(
                    "db.cluster.sid", l_strJndiName);
            return l_mpdsSetting.getSettingValue();
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getSid(String)", 
                    l_dfe.getMessage(), 
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getSid(String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    
    /**
     * �R���e�L�X�g��񂪐ݒ肳��Ă��Ȃ��ꍇ�Ɏg�p�����OracleSID��
     * ���E���h���r���E���W�b�N�Ŏ擾����B
     * 
     * @@return OracleSID
     * @@throws WEB3SystemLayerException
     */
    private String getDefaultOracleSid() throws WEB3SystemLayerException
    {
        String l_strJndiName = getRoundRobinBasedJndiName();
        return getOracleSid(l_strJndiName);
    }
    
    /**
     * �g�p����DB�ڑ��v�[���̃T�[�oID���擾����B
     * 
     * @@param l_strJndiName JNDI��
     * @@return �T�[�oID
     * @@throws WEB3SystemLayerException
     */
    private String getServerId(String l_strJndiName) throws WEB3SystemLayerException
    {
        try
        {
            String l_strWhere = "setting_categ=? and setting_value=?";
            Object[] l_objBindVars = { "db.cluster.jndi_names", l_strJndiName };
            List l_rows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    MpdsSettingsRow.TYPE,
                    l_strWhere,
                    l_objBindVars);
            if (l_rows != null && l_rows.size() > 0)
            {
                MpdsSettingsRow row = (MpdsSettingsRow) l_rows.get(0);
                return row.getSettingName();
            }
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getServerId(String)"); 
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getServerId(String)", 
                    l_dfe.getMessage(), 
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getServerId(String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    
    /**
     * RANGE_BASED_MPDS_MAP�e�[�u������ȉ��̏����Ō����������R�[�h��List���擾����B<BR>
     * <BR>
     * ������������<BR>
     * CTX_NAME = �p�����[�^.�R���e�L�X�g��<BR>
     * JNDI_NAME = �p�����[�^.JNDI��<BR>
     * ���\�[�g������<BR>
     * RANGE_ORDER_NO�i�����j<BR>
     * KEY_START�i�����j <BR>
     * 
     * @@param l_strContextName �R���e�L�X�g��
     * @@param l_strServerId �T�[�oID
     * @@return �擾�������R�[�h��List
     * @@throws WEB3SystemLayerException
     */
    private List getAffinityRangeBasedMaps(String l_strContextName, String l_strServerId) throws WEB3SystemLayerException
    {
        final String l_strWhere = "ctx_name=? and server_id=? and server_type=?";
        final String l_strOrderBy = "range_order_no, key_start";
        Object[] l_objBindVars = { 
                l_strContextName, 
                l_strServerId, 
                new Integer(ServerTypeEnum.IntValues.DB) };
        try
        {
            List l_rangeBasedMpdsMaps =
                Processors.getDefaultProcessor().doFindAllQuery(
                    AffinityRangeBasedMapRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_objBindVars);
            return l_rangeBasedMpdsMaps;
        } catch (DataFindException l_dfe)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getAffinityRangeBasedMaps(String, String)", 
                    l_dfe.getMessage(),
                    l_dfe);
        } catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    getClass().getName() + ".getAffinityRangeBasedMaps(String, String)", 
                    l_de.getMessage(), 
                    l_de);
        }
    }
    
    /**
     * �A�J�E���gID�͈̔͂��擾����B
     * 
     * @@param l_strContextName �R���e�L�X�g��
     * @@param l_strContextValue �l
     * @@param l_strServerId �T�[�oID
     * @@return �A�J�E���gID�͈̔�
     * @@throws WEB3SystemLayerException
     */
    private AccountIdRange getAccountIdRange(String l_strContextName,
            String l_strContextValue, String l_strServerId)
            throws WEB3SystemLayerException
    {

        List l_rangeBasedMaps = getAffinityRangeBasedMaps(l_strContextName, l_strServerId);
        if (l_rangeBasedMaps == null || l_rangeBasedMaps.size() <= 0)
        {
            // RANGE_BASED_MPDS_MAP���R�[�h���擾�ł��Ȃ��ꍇ�͗�O���X���[
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + ".getAccountIdRange(String, String)");
        }
        
        AffinityRangeBasedMapRow[] l_rows = 
            new AffinityRangeBasedMapRow[l_rangeBasedMaps.size()];
        l_rows = (AffinityRangeBasedMapRow[]) l_rangeBasedMaps.toArray(l_rows);
        
        // RANGE_ORDER_NO�̍ŏ��l���擾
        long l_lngMinRagneOrderNo = l_rows[0].getRangeOrderNo();
        
        // �ΏۂƂȂ�A�J�E���g�̌��݂�RANGE_ORDER_NO���擾
        int l_intIndex = 0;
        long l_lngAccountId = Long.parseLong(l_strContextValue);
        long l_lngRangeOrderNo = 0L;
        for (int i = 0; i < l_rows.length; i++)
        {
            if (l_rows[i].getKeyStart() <= l_lngAccountId
                && l_lngAccountId <= l_rows[i].getKeyEnd())
            {
                l_intIndex = i;
                l_lngRangeOrderNo = l_rows[i].getRangeOrderNo();
                break;
            }
        }
        
        AccountIdRange l_range = new AccountIdRange();
        if (l_lngMinRagneOrderNo == l_lngRangeOrderNo)
        {
            // ���݂�RANGE_ORDER_NO��RANGE_ORDER_NO�̍ŏ��l�̏ꍇ
            l_range.accountIdStart = l_rows[0].getKeyStart();
            l_range.accountIdEnd = l_rows[0].getKeyEnd();
            for (int i = 0; i < l_rows.length; i++)
            {
                // ���̏ꍇ�́ARANGE_ORDER_NO�������Ō���̃��R�[�h��KEY_END��ݒ�
                if (l_lngRangeOrderNo == l_rows[i].getRangeOrderNo())
                {
                    l_range.accountIdEnd = l_rows[i].getKeyEnd();
                } else
                {
                    break;
                }
            }
            
        } else {
            // ����ȊO�̏ꍇ�́A�ΏۂƂȂ�A�J�E���g���܂ރ��R�[�h����l��ݒ�
            l_range.accountIdStart = l_rows[l_intIndex].getKeyStart();
            l_range.accountIdEnd = l_rows[l_intIndex].getKeyEnd();
        }
        
        return l_range;
        
    }
    
    /**
     * �R���e�L�X�g�����擾����B
     * 
     * @@return �R���e�L�X�g��
     */
    private String getContextName()
    {
        Object l_objContextName = ThreadLocalSystemAttributesRegistry
                .getAttribute(ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_CTX_NAME);
        if (l_objContextName != null)
        {
            return String.valueOf(l_objContextName);
        } else {
            return null;
        }
    }

    /**
     * �A�J�E���gID�܂��̓��O�C��ID���擾����B
     * 
     * @@return �A�J�E���gID�܂��̓��O�C��ID
     */
    private String getContextValue()
    {
        Object l_objContextValue = ThreadLocalSystemAttributesRegistry
                .getAttribute(ContextDrivenMultiPoolDataSource.ATTRIBUTE_NAME_FOR_KEY_VALUE);
        if (l_objContextValue != null)
        {
            return String.valueOf(l_objContextValue);
        } else {
            return null;
        }
    }
    
    /**
     * �A�J�E���gID�͈̔͂�\������N���X
     *
     * @@author Takuji Yamada (FLJ)
     * @@version 1.0
     */
    private class AccountIdRange
    {
        
        /**
         * �A�J�E���gID�i���j
         */
        long accountIdStart;

        /** 
         * �A�J�E���gID�i���j
         */
        long accountIdEnd;
        
    }

}@
