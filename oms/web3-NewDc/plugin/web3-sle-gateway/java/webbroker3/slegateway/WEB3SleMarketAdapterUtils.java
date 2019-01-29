head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleMarketAdapterUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleMarketAdapterUtils�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 �� �V�K�쐬
 Revision History : 2006/05/31 ��(FLJ)�@@SLE�ڑ���ԃ`�F�b�N�@@�\�Ή��ACircuiteBraker�t���O�ݒ�@@�\�Ή�
 Revision History : 2006/06/08 ��(FLJ)�@@�\�[�X����
 */
package webbroker3.slegateway;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesPK;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.slebase.data.SleConnStatusChangesRow;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleConnectionStatusEnum;
import webbroker3.slebase.utils.SleConstants;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SehkSleRequestPreparer;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SeszSleRequestPreparer;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SeshSleRequestPreparer;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleRequestPreparer;

/**
 * SLE���M�����Ɋւ��郆�[�e�B���e�B�@@�\��񋟂���N���X
 */
public class WEB3SleMarketAdapterUtils
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static final WEB3LogUtility m_log =WEB3LogUtility.getInstance(WEB3SleMarketAdapterUtils.class);
    
    /**
     * DB�v���Z�b�T
     */
    private static WEB3SleProcessors m_wsp = new WEB3SleProcessorsImpl();
    
    /**
     * �R���X�g���N�^
     */
    private WEB3SleMarketAdapterUtils() {
        ;
    }
    
    /**
     * DB�v���Z�b�T�ݒ�
     * @@param wsp�@@DB�v���Z�b�T�C���X�^���X
     */
    public static void setWeb3SleProcessors(WEB3SleProcessors wsp)
    {
        m_wsp = wsp;
    }

    //xBlocks�s��R�[�h����GlID�̂փ}�b�s���O
    private static HashMap m_xblockmarketcode2glid = new HashMap();

    // SEND_Q�X���b�h���~���邩�J�n���邩���䂷��t���O
    private static final String SYSTEMPREF_NAME = "GenMultiThreadedRowQProc.sle_send_q";

    static
    {
        //�s��̑Ή�����GLID�}�b�s���O(���`�s��)
        m_xblockmarketcode2glid.put(
            SleConstants.Markets.SEHK.MARKET_CODE,
            SleConstants.Markets.SEHK.GLID);
            
        //�s��̑Ή�����GLID�}�b�s���O(�[�Z���s��)
        m_xblockmarketcode2glid.put(
            SleConstants.Markets.SESZ.MARKET_CODE,
            SleConstants.Markets.SESZ.GLID);            

        //�s��̑Ή�����GLID�}�b�s���O(��C�s��)
        m_xblockmarketcode2glid.put(
            SleConstants.Markets.SESH.MARKET_CODE,
            SleConstants.Markets.SESH.GLID);
    }
    
    
    //���V���Z���s��Ή�2007/10/23
    //�s��ʂ�WEB3SleRequestPreparer�I�u�W�F�N�g���L���b�V������B
    private static Map instances = Collections.synchronizedMap(new HashMap());
    
    static
    {
        instances.put(SleConstants.Markets.SEHK.MARKET_CODE,WEB3SehkSleRequestPreparer.getInstance());
        instances.put(SleConstants.Markets.SESZ.MARKET_CODE,WEB3SeszSleRequestPreparer.getInstance());
        instances.put(SleConstants.Markets.SESH.MARKET_CODE,WEB3SeshSleRequestPreparer.getInstance());
    }
    
    /**
     * �s��ʂ�SLE���M���������I�u�W�F�N�g���擾����
     * 
     * @@param marketCode�@@xtrade�s��R�[�h
     * 
     * @@return WEB3SleRequestPreparer �s��ʂ̏��������I�u�W�F�N�g
     */
    public static WEB3SleRequestPreparer getSleRequestPreparer(String marketCode)
    {
        return (WEB3SleRequestPreparer)instances.get(marketCode);
    }
    //���V���Z���s��Ή�2007/10/23
    
    /**
     *  �������M������O��
     *  ���ݎ����͎s��̎�����ԑтł��邩�̊m�F�����܂��B
     * 
     * @@return �s��̎�����ԑтł��邩�Ɣ��f���ꂽ�ꍇ��true�A�����łȂ����false
     *          ���ӁF������O����������ꍇ�A�s��̎�����ԑтł͂Ȃ��Ɣ��f����
     */
    public static boolean isOk2SendOrdersOnAvailableMarketTimeZone()
    {

        m_log.entering("isOk2SendOrders(SleSendQRow)");

        //web3-gentrade ��WEB3GentradeTradingTimeManagement�Ŏ���
        boolean l_blnIsTradeOpenTimeZone = false;
        try {
            l_blnIsTradeOpenTimeZone =
//                WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
                //2006/11/30�Ŕ���������Q�ɑΉ����邽�ߎg�pAPI��ύX���܂����B
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);
        } catch (WEB3BaseException wbe) {
            m_log.error(wbe.getMessage(), wbe);
            m_log.exiting("isOk2SendOrders(SleSendQRow)");
            return false;
        }

        m_log.exiting("isOk2SendOrders(SleSendQRow)");
        return l_blnIsTradeOpenTimeZone;
    }

//    /**
//     * �������M������O�Ɏs���ԃ`�F�b�N����
//     * @@param l_sendqRow SEND_Q�������b�Z�[�W
//     * @@return ���M�ł���Ɣ��f���ꂽ�ꍇ��true�A�����łȂ����false
//     * 
//     * '�蓮�X�C�b�`'�����������߁A2006/5/23�@@���C�F��(FLJ)
//     */
//    public static boolean isOk2SendOrdersOnAvailableMarketstatus(SleSendQRow l_sendqRow)
//        throws DataException 
//    {
//        final QueryProcessor l_qp;
//        final Row l_rows;
////        final SleMarketStatusRow l_row;
//        String l_strGlid;
////        SleMarketStatusPK l_statusPK;
//
//        int l_intStatus;
//        //�s���Ԃ̎擾
//        m_log.entering("isOk2SendOrders_status(SleSendQRow)");
//
//        l_strGlid =
//            (String) m_xblockmarketcode2glid.get(l_sendqRow.getMarketCode());
//        if (l_strGlid == null)
//        {
//            m_log.error(
//                WEB3SleMarketAdapterErrorCatalog
//                    .MARKET_GLID_NOT_EXISTED
//                    .toString());
//            m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//            return false;
//        }
//
//        try {
//            l_qp = m_wsp.getDefaultProcessor();
//            l_statusPK = new SleMarketStatusPK(l_strGlid);
//            l_rows = l_qp.doFindByPrimaryKeyQuery(l_statusPK);
//            l_row = (SleMarketStatusRow) l_rows;
//            l_intStatus = l_row.getStatus().intValue();
//            
//            /*
//             �s��X�e�[�^�X�Ǘ��e�[�u���𒲂ׁA
//             �s��̍ŐV��Ԃ��e�I�t���C���f
//             ���邢�f�����N�����e�̏ꍇ�A
//             �G���[���b�Z^�������O�ɏo�͂�false��Ԃ�
//             */
//            if ((l_intStatus == SleConstants.SLEMARKETSTATUS.STATUS_OFFLINE)
//                || (l_intStatus == SleConstants.SLEMARKETSTATUS.STATUS_NOLINK))
//            {
//                m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//                return false;
//            }
//            else
//            {
//                m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//                return true;
//            }
//
//        } catch (DataFindException dfe) {
//            /*�s��X�e�[�^�X�����擾�ł��Ȃ��ꍇ
//              ���M�ł��Ȃ��s���Ԃƌ��Ȃ����*/
//            m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//            
//            return false;
//        } catch (DataException ex) {
//            m_log.exiting("isOk2SendOrders_status(SleSendQRow)");
//            
//            /*�s��֑�ʖ��������𔭍s����̂�h�~���邽��
//              ��ʏ����֗�O�����̂܂܂��X���[���A��ʏ������瑗�M�𒆎~����*/
//            throw ex;
//        }
//    }
    /**
     * SLE�R�l�N�^��SLE�����G���W���Ԃ̐ڑ���Ԃ����M�\�ȏ�Ԃł��邩�`�F�b�N����B
     * 
     * @@param l_sendqRow SEND_Q�������b�Z�[�W
     * @@param l_iActiveDiv SLE�R�l�N�^�T�[�o�ł��邩�\���敪�l��(2006/9/20��DB�ύX�ɏ]���Ēǉ�)
     *         0: Active �T�[�o 1:Standby�T�[�o
     * @@return ���M�ł���Ɣ��f���ꂽ�ꍇ��true�A�����łȂ����false
     */
    public static boolean isSleConnectorOK(SleSendQRow l_sendqRow,int l_iActiveDiv)
        throws DataException 
    {
        boolean l_bResult = false;

        m_log.entering("isSleConnectorOK(SleSendQRow)");

        final String l_strWhere = "market_code = ? and sle_conn_div = ?";
        final String l_strOrderBy = "created_timestamp desc";//2007/2/5  last_updated_timestamp��created_timestamp �֕ύX
        final Object[] l_ObindVars = { l_sendqRow.getMarketCode(), new Integer(l_iActiveDiv), };

        try {
            final QueryProcessor qp = m_wsp.getDefaultProcessor();
            final List rows =
                qp.doFindAllQuery(
                    SleConnStatusChangesRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_ObindVars);
            if (rows.size() > 0)
            {
                SleConnStatusChangesRow row =
                    (SleConnStatusChangesRow) (rows.get(0));
                SleConnectionStatusEnum status = row.getNewStatus();
                /* 
                 �@@'SLE�G���W���֍Đڑ�����' �܂���'SLE�R�l�N�^����ɋN��'�̏ꍇ�A
                   SLE�ڑ��󋵂�OK�Ƃ���
                 */
                if (status.equals(SleConnectionStatusEnum.START_SUCCESS)
                    || status.equals(
                        SleConnectionStatusEnum.RECONNECTION_SUCCESS))
                {
                    l_bResult = true;
                }
            }
            m_log.exiting("isSleConnectorOK(SleSendQRow)");
        } catch (DataException de) {
            /*
             �s��֑�ʖ��������𔭍s����̂�h�~���邽��
             ��ʏ����֗�O�����̂܂܂��X���[���A��ʏ������瑗�M�𒆎~����
             */
            m_log.exiting("isSleConnectorOK(SleSendQRow)");
            throw de;
        }
        return l_bResult;
    }

    /**
     * �w�肵���C���f�b�N�X�ԍ��ɑΉ�����Object �v�f���N���X�^�z��ɍŌ�Ɉړ�����.
     * 
     * @@param cluster �N���X�^�z��
     * @@param index �C���f�b�N�X�ԍ�
     */
    public static void moveElementToEnd(Object[] l_cluster, int index)
    {

        final Object l_Otmp = l_cluster[index];
        for (int i = index + 1; i < l_cluster.length; i++)
        {
            l_cluster[i - 1] = l_cluster[i];
        }

        l_cluster[l_cluster.length - 1] = l_Otmp;
        return;
    }

    /**
     * SYSTEM_PREFERENCES �e�[�u����SEND_Q���M�X���b�h����t���O���X�V����
     *
     * @@param �s��R�[�h
     * @@param SEND_Q���M�X���b�h���J�n���邩���~���邩���䂷��t���O
     */
    public static void setSystemPreferencesStatus(final String l_markeCode, final boolean l_bStatus)
    {

        final String l_systemPrefName = SYSTEMPREF_NAME + "." + l_markeCode;
        
        try {

            final QueryProcessor l_qp = m_wsp.getDefaultProcessor();
            
            //���M�����ƕʂɑ��̒P�ƃg�����U�N�V������CircuiteBraker�t���O��ݒ肷��
            l_qp.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    new TransactionCallback()
            {
                public Object process()
                    throws
                        DataNetworkException,
                        DataQueryException,
                        DataCallbackException {

                    l_qp.lockAccount(0, true);

                    final String l_strNewValue = l_bStatus ? "true" : "false";

                    final Map l_hmChanges = new HashMap();
                    l_hmChanges.put("value", l_strNewValue);
                    l_hmChanges.put(
                        "last_updated_timestamp",
                        GtlUtils.getSystemTimestamp());
                    // �A�b�v�f�[�g
                    final int l_intUpdatedCount =
                        l_qp.doUpdateQuery(
                            new SystemPreferencesPK(l_systemPrefName),
                            l_hmChanges);
                    if (l_intUpdatedCount == 0)
                    {
                        // �C���T�[�g
                        final SystemPreferencesParams sysPrefParams =
                            new SystemPreferencesParams();
                        sysPrefParams.setName(l_systemPrefName);
                        sysPrefParams.setValue(l_strNewValue);
                        l_qp.doInsertQuery(sysPrefParams);
                    }

                    // return something
                    return null;
                }
            });
        } catch (DataException de) {

            final String msg =
                "Exception Updating system_preferences with name:"
                    + l_systemPrefName
                    + ", with value:"
                    + l_bStatus;
            m_log.error(msg, de);
            //SEND_Q���M�X���b�h�𒆎~���鎞�ADB�G���[����������ꍇ�A����t���O�X�V���s�ŁASEND_Q���M�������������~����
            throw new RuntimeException(msg, de);
        }
    }

    /**
     * SEND_Q���M�X���b�h�̐���t���O��Ԃ�.
     *
     * @@param  �s��R�[�h
     * @@return ���~���:true��Ԃ� �J�n���:false��Ԃ�
     */
    public static boolean isStopRequested(final String l_markeCode)
    {

//        final String l_strVal =
//            GtlUtils.getTradingSystem().getPreference(m_systemPrefName);
//        return !"true".equals(l_strVal);

        m_log.entering("isStopRequested");
        
        final String l_strName = SYSTEMPREF_NAME + "." + l_markeCode;
        
        final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
        if (l_strValue == null)
        {
            final String msg = l_strName
                            + " not defined in the SYSTEM_PREFERENCES with  name:"
                            + l_strName;
            m_log.warn(msg);
            m_log.exiting("isStopRequested");
            return false;
        }
        
        m_log.exiting("isStopRequested()");
        return !"true".equals(l_strValue);
        
        
    }
    
    /**
     * �s�ꑗ�M�X�P�W���[���L���t���O��Ԃ�.
     *
     * @@return �s�ꑗ�M�X�P�W���[���L��:true��Ԃ� �s�ꑗ�M�X�P�W���[������:false��Ԃ�
     *          �f�t�H���g�ݒ�:true(�s�ꑗ�M�X�P�W���[���L��)
     */
    public static boolean isMarketScheduleOnUsed()
    {

        m_log.entering("isMarketScheduleOnUsed");
        
        final String l_strName = "sle.market.sendSchedule.used";
        
        final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
        if (l_strValue == null)
        {
            final String msg =
                        "sle.market.sendSchedule.used not defined in the SYSTEM_PREFERENCES with  name:"
                            + l_strName;
            m_log.warn(msg);
            m_log.exiting("isMarketScheduleOnUsed");
            return true;
        }
        
        m_log.exiting("isMarketScheduleOnUsed");
        return "true".equals(l_strValue);
        
        
    }
    
    /**
     * WEBIII �̔������ݒ��Ԃ�.
     *
     * @@return SYSTEM_PREFERENCES�� name=system.bizdate �̐ݒ�l��Ԃ�
     */
    public static String getBizDate()
    {

        m_log.entering("getBizDate");
        
        final String l_strName = "system.bizdate";
        
        final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
        if (l_strValue == null)
        {
            final String msg =
                        "system.bizdate not defined in the SYSTEM_PREFERENCES.";
            m_log.warn(msg);
            m_log.exiting("isMarketScheduleOnUsed");
            String l_today = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date()).toString() ;
            return l_today;
        }
        
        m_log.exiting("getBizDate");
        return l_strValue;
        
        
    }    

}@
