head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.01.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\���}�l�[�W��Impl(WEB3PointApplyManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/30 ���w�� (���u) �V�K�쐬
*/
package webbroker3.point;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApplyAcceptDivDef;
import webbroker3.common.define.WEB3ApplyCancelDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.data.PointAdjustParams;
import webbroker3.point.data.PointAdjustRow;
import webbroker3.point.data.PointApplyDao;
import webbroker3.point.data.PointApplyParams;
import webbroker3.point.data.PointApplyRow;
import webbroker3.point.data.PointTermDao;
import webbroker3.point.data.PointTermRow;
import webbroker3.point.data.PointTotalDao;
import webbroker3.point.data.PointTotalRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�|�C���g�\���}�l�[�W��Impl)<BR>
 * �|�C���g�\���}�l�[�W�������N���X<BR>
 * 
 * @@author ���w��
 * @@version 1.0 
 */
public class WEB3PointApplyManagerImpl implements WEB3PointApplyManager 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PointApplyManagerImpl.class);
    
    /**
     * (get�\��)<BR>
     * �\���I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * ����.�\��ID�ɊY������|�C���g�\���I�u�W�F�N�g���擾����B<BR>
     * @@param l_lngApplyId - (�\��ID)<BR>
     * �\��ID<BR>
     * 
     * @@return webbroker3.point.WEB3PointApply
     * @@roseuid 419C8D8501EB
     */
    public WEB3PointApply getApply(long l_lngApplyId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getApply(long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            PointApplyRow l_row = PointApplyDao.findRowByApplyId(l_lngApplyId);//DataNetworkException,DataQueryException
            
            if (l_row == null)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            PointApplyParams l_pointApplyParams = new PointApplyParams(l_row);
            
            WEB3PointApply l_pointApply = new WEB3PointApply(l_pointApplyParams);
            
            log.exiting(STR_METHOD_NAME);
            
            return l_pointApply;
        
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�\��)<BR>
     * �Y���ڋq�̉ߋ�7���̐\���f�[�^���擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����Ń|�C���g�\���e�[�u�����烌�R�[�h��\�������̏����Ŏ擾����B<BR>
     * <BR>
     * [�擾����]<BR>
     *    �،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     *    ���X�R�[�h = ����.���X�R�[�h and<BR>
     *    �����R�[�h = ����.�ڋq�R�[�h and<BR>
     *    ( (�\����t���� = null and �\��������� = null) or<BR>
     *       ( �\����t���� = null and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
     *       ( �\��������� = null and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
     *       ( �\����t���� > �\��������� and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
     *       ( �\����t���� < �\��������� and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) )<BR>
     * <BR>
     * �Q�j�擾�������R�[�h��z��ɂ��āA�ԋp����B<BR>
     *    �������̌���0���������ꍇ�́A�v�f0�̔z���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return webbroker3.point.data.PointApplyParams
     * @@roseuid 419DC5E10142
     */
    public PointApplyParams[] getApply(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getApply(String, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {      
            List l_lisRecords = null;
            
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
            
            Date l_date = WEB3DateUtility.addDay(l_tsSystemTime, -7);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;
            
            //[�擾����]<BR>
            //�،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
            //���X�R�[�h = ����.���X�R�[�h and<BR>
            //�����R�[�h = ����.�ڋq�R�[�h and<BR>
            //( (�\����t���� = null and �\��������� = null) or<BR>
            //( �\����t���� = null and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
            //( �\��������� = null and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
            //( �\����t���� > �\��������� and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
            //( �\����t���� < �\��������� and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) )<BR>

            l_sbWhere = "institution_code = ? and branch_code = ? and account_code = ? and " + 
                " ((apply_accept_timestamp IS NULL  and apply_cancel_timestamp IS NULL) or " +
                " (apply_accept_timestamp IS NULL and apply_cancel_timestamp >= ?) or " +
                " (apply_cancel_timestamp IS NULL and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp > apply_cancel_timestamp and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp < apply_cancel_timestamp and apply_cancel_timestamp >= ?))";               
                
            l_objWhere = new Object[]{l_strInstitutionCode, l_strBranchCode, 
                l_strAccountCode, l_date, l_date, l_date, l_date};          
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_sbWhere,
                " apply_timestamp asc ", 
                "",
                l_objWhere);//DataQueryException, DataNetworkException
                
            //�擾�������R�[�h��z��ɂ��āA�ԋp����B
            //�����̌���0���������ꍇ�́A�v�f0�̔z���ԋp����B            
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            } 
            
            PointApplyParams[] l_params = new PointApplyParams[l_intRecords];
            
            for (int i = 0; i < l_intRecords; i++)
            {
                l_params[i] = new PointApplyParams((PointApplyRow)l_lisRecords.get(i));
            }
            
            return l_params;      
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�\������)<BR>
     * �����̏،���ЃR�[�h�A�i�i�ԍ��ɊY������i�i�̃|�C���g�\�������Ă��錏�����擾����B<BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�|�C���g�\���e�[�u������������B<BR>
     * <BR>
     * ���������F<BR>
     *    �،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
     *    �i�i�ԍ� = ����.�i�i�ԍ� and<BR>
     *    ( (�\����t���� = null and �\��������� = null) or<BR>
     *       ( �\����t���� = null and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
     *       ( �\��������� = null and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
     *       ( �\����t���� > �\��������� and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
     *       ( �\����t���� < �\��������� and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) )<BR>
     * <BR>
     * �Q�j�������ʂ̃��R�[�h����ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strPremiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * 
     * @@return long
     * @@roseuid 41934895035A
     */
    public long getApplyNumber(String l_strInstitutionCode, String l_strPremiumNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getApplyNumber(String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {      
            List l_lisRecords = null;
            
            Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
            
            Date l_date = WEB3DateUtility.addDay(l_tsSystemTime, -7);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;
            
            //���������F<BR>
            // �،���ЃR�[�h = ����.�،���ЃR�[�h and<BR>
            //  �i�i�ԍ� = ����.�i�i�ԍ� and<BR>
            // ( (�\����t���� = null and �\��������� = null) or<BR>
            // ( �\����t���� = null and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
            // ( �\��������� = null and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
            // ( �\����t���� > �\��������� and �\����t���� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) or<BR>
            // ( �\����t���� < �\��������� and �\��������� >= <BR>(TradingSystem.getSystemTimestamp() - 7��) ) )<BR>

            l_sbWhere = "institution_code = ? and premium_no = ? and " + 
                " ((apply_accept_timestamp IS NULL and apply_cancel_timestamp IS NULL) or " +
                " (apply_accept_timestamp IS NULL and apply_cancel_timestamp >= ?) or " +
                " (apply_cancel_timestamp IS NULL and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp > apply_cancel_timestamp and apply_accept_timestamp >= ?) or " +
                " (apply_accept_timestamp < apply_cancel_timestamp and apply_cancel_timestamp >= ?))";               
                
            l_objWhere = new Object[]{l_strInstitutionCode, l_strPremiumNo, 
                l_date, l_date, l_date, l_date};        
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_sbWhere,
                l_objWhere);//DataQueryException, DataNetworkException
                
            //�Q�j�������ʂ̃��R�[�h����ԋp����B
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_intRecords;      
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get���p�\�|�C���g)<BR>
     * ���p�\�|�C���g���擾����B<BR>
     * <BR>
     * this.get���p�\�|�C���g()���R�[������B<BR>
     * <BR>
     * [get���p�\�|�C���g�ɃZ�b�g�������]<BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * ���X�R�[�h�F ����.���X�R�[�h<BR>
     * �ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
     * ��������\���F null<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return long
     * @@roseuid 41AF149B0196
     */
    public long getUsePossiblePoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getUsePossiblePoint(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngPossiblePoint = this.getUsePossiblePoint(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode, null);
        
        log.exiting(STR_METHOD_NAME);
            
        return l_lngPossiblePoint;
    }
    
    /**
     * (get���p�\�|�C���g)<BR>
     * ���p�\�|�C���g���擾����B<BR>
     * �i��������\���Ή��j<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_cancelReleaseApply - (��������\��)<BR>
     * ��������\��̐\���f�[�^<BR>
     * 
     * @@return long
     * @@roseuid 419465ED01C5
     */
    protected long getUsePossiblePoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointApply l_cancelReleaseApply) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUsePossiblePoint(String, String, String, WEB3PointApply)";
        log.entering(STR_METHOD_NAME);
        
        //�@@ �L�����������擾����B
        //get�L��������()���R�[������B
        //[get�L��������()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //�Ώی��F ���ݎ�������擾�����N���iYYYYMM�j        
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();       
        String l_strSystemDate = WEB3DateUtility.formatDate(l_tsSystemTime, "yyyyMM");        
        String l_strValidYearMonth = this.getValidTermMon(l_strInstitutionCode, l_strSystemDate);
        
        if (l_strValidYearMonth == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("now Year Mon:" + l_strSystemDate);
        log.debug("�L��������:" + l_strValidYearMonth);
        
        Date l_date = WEB3DateUtility.getDate(l_strValidYearMonth, "yyyyMM");
        Date l_systemDate = WEB3DateUtility.getDate(l_strSystemDate, "yyyyMM");
        
        String l_strValidYear = WEB3DateUtility.formatDate(l_date, "yyyy"); 
        String l_strValidMonth = WEB3DateUtility.formatDate(l_date, "MM"); 
        
        String l_strSystemYear = WEB3DateUtility.formatDate(l_systemDate, "yyyy"); 
        String l_strSystemMonth = WEB3DateUtility.formatDate(l_systemDate, "MM"); 
            
        //�A   �L�����������獡���܂ł̎c�|�C���g�̍��v�l���Z�o����B
        //�L�����������獡���܂ł̃��[�v�������s���B
        //1)  �Y���N���̎c�|�C���g���擾����B
        //get�c�|�C���g()���R�[������B
        //[get�c�|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F ���[�v�̗v�f�̔N���iYYYYMM�j
        int l_intYear = Integer.parseInt(l_strSystemYear) - Integer.parseInt(l_strValidYear);
        int l_intMonth = Integer.parseInt(l_strSystemMonth) - Integer.parseInt(l_strValidMonth);
        
        int l_intSum = l_intYear * 12 + l_intMonth + 1;
        
        long l_lngRemainedPointSum = 0;
        
        String l_strDateByDate = l_strValidYearMonth;
        for (int i = 0; i < l_intSum; i++)
        {
            log.debug("sum:" + l_intSum);
            log.debug("��" + l_strDateByDate);
            
            long l_lngRemainedPoint = this.getRemainedPoint(l_strInstitutionCode, 
                l_strBranchCode, l_strAccountCode, l_strDateByDate);
            
            //2)  �擾�����|�C���g���W�v����B
            //�W�v���ʂ�(A)�Ƃ���B
            l_lngRemainedPointSum = l_lngRemainedPointSum + l_lngRemainedPoint;
            log.debug("�W�v����:" + l_lngRemainedPointSum);
            
            Date l_dateCal =  WEB3DateUtility.getDate(l_strDateByDate, "yyyyMM");
            if (l_dateCal == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_dateCal);
            
            l_cal.add(Calendar.MONTH, 1);
            
            Date l_nextDate = new Date(l_cal.getTimeInMillis());
            
            l_strDateByDate = WEB3DateUtility.formatDate(l_nextDate, "yyyyMM");
        }       
        
        //�B   �L��������̈������σ|�C���g���擾����B
        //get�������σ|�C���g()���R�[������B
        //[get�������σ|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //��������\���F ����.��������\��
        //���߂�l��(B)�Ƃ���B
        long l_lngNotWithdrawnPoint = this.getNotWithdrawnPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_cancelReleaseApply); 
        log.debug("�L��������̈������σ|�C���g" + l_lngNotWithdrawnPoint);       

        //�C   ���p�\�|�C���g���Z�o���A�ԋp����B
        //���p�\�|�C���g = (A) - (B)        
        long l_lngPossiblePoint = l_lngRemainedPointSum - l_lngNotWithdrawnPoint;
        log.debug("l_lngRemainedPointSum - l_lngNotWithdrawnPoint");
        log.debug("���p�\�|�C���g:" + l_lngRemainedPointSum + " - " + l_lngNotWithdrawnPoint + " = " + l_lngPossiblePoint);

        log.exiting(STR_METHOD_NAME);
        return l_lngPossiblePoint;
    }
    
    /**
     * (get�������Ӄ|�C���g)<BR>
     * �������Ӄ|�C���g���擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return long
     * @@roseuid 419D76BC0326
     */
    public long getExpirationAttentionPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExpirationAttentionPoint(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h���󂯎��A�Z�o�����������Ӄ|�C���g��ԋp����B

        //�@@   �L�����������擾����B
        //get�L��������()���R�[������B
        //[get�L��������()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //�Ώی��F ���ݎ�������擾�����N���iYYYYMM�j
        //���߂�l��(A)�Ƃ���B
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();       
        String l_strSystemDate = WEB3DateUtility.formatDate(l_tsSystemTime, "yyyyMM"); 
        
        String l_strMonth = this.getValidTermMon(l_strInstitutionCode, l_strSystemDate);
        
        if (l_strMonth == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("�L��������:" + l_strMonth);
        
        //�A   �L���������̎c�|�C���g���Z�o����B
        //get�c�|�C���g()���R�[������B
        //[get�c�|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F (A)
        //���߂�l��(B)�Ƃ���B
        long l_lngRemainedPoint = this.getRemainedPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strMonth);
        log.debug("�L���������̎c�|�C���g:" + l_lngRemainedPoint);
        
        //�B   �c�|�C���g�̔���
        //�E   (B) �� 0 �̏ꍇ�A�������Ӄ|�C���g = 0 �Ƃ��ĕԋp����B
        //�E   (B) �� 0 �̏ꍇ�A�ȍ~����������B
        
        if (l_lngRemainedPoint <= 0)
        {
            log.debug("(B) �� 0 ");
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        log.debug("(B) �� 0");

        //�C   �L��������̈������σ|�C���g���擾����B
        //get�������σ|�C���g()���R�[������B
        //[get�������σ|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //��������\���F null
        //���߂�l��(C)�Ƃ���B
        long l_lngNotWithdrawnPoint = this.getNotWithdrawnPoint(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode, null);
            
        log.debug("�L��������̈������σ|�C���g:" + l_lngNotWithdrawnPoint);
        
        //�D   �������Ӄ|�C���g���m�肵�A�ԋp����B
        //�������Ӄ|�C���g = (B) - (C)
        //���������Ӄ|�C���g < 0 �̏ꍇ�A0��ԋp����B
        long l_lngvoidPoint = l_lngRemainedPoint - l_lngNotWithdrawnPoint;
        
        if (l_lngvoidPoint < 0)
        {
            log.debug("�������Ӄ|�C���g < 0 ");
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        log.debug("�������Ӄ|�C���g = (B) - (C):" + l_lngRemainedPoint + " - " + l_lngNotWithdrawnPoint);

        log.exiting(STR_METHOD_NAME);
        return l_lngvoidPoint;
    }
    
    /**
     * (get�\���|�C���g)<BR>
     * ����.�Ώی��ɐ\�����s�����|�C���g�̍��v���擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �����ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return long
     * @@roseuid 41AEE81E0226
     */
    public long getApplyPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException
    {       
        final String STR_METHOD_NAME = " getApplyPoint(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A
        //�Ώی��iYYYYMM�j���󂯎��A�Z�o�����Ώی��ɐ\�����s�����|�C���g�̍��v��ԋp����B

        //�@@   �|�C���g�\���e�[�u������A�ȉ��̏����Ń��R�[�h���擾����B
        //[�擾����]
        //�،���ЃR�[�h �� ����.�،���ЃR�[�h and
        //���X�R�[�h �� ����.���X�R�[�h and
        //�����R�[�h �� ����.�ڋq�R�[�h and
        //�\������敪 �� 0�i������ρj and
        //�\������ �� (A) and
        //�\������ �� (B)
        //��(A)�F ����.�Ώی���1����0:00
        //  (B)�F ����.�Ώی��̗�����1����0:00
        try
        {      
            log.debug("�Ώی�:" + l_strObjectMonth); 
            
            List l_lisRecords = null;
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;
            
            Date l_date = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            if (l_date == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            // ����.�Ώی���1����0:00
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_date);
            
            l_cal.add(Calendar.MONTH, 1);
            
            //����.�Ώی��̗�����1����0:00
            String l_strNextDate = WEB3DateUtility.formatDate(new Date(l_cal.getTimeInMillis()), "yyyyMM");
            Date l_nextDate = WEB3DateUtility.getDate(l_strNextDate, "yyyyMM");            
            
            l_sbWhere = "institution_code = ? and branch_code = ? and " + 
                " account_code = ? and apply_cancel_div = ? and " +
                " apply_timestamp >= ? and apply_timestamp < ? " ;
               
            l_objWhere = new Object[]{l_strInstitutionCode, l_strBranchCode, 
                l_strAccountCode, WEB3ApplyCancelDivDef.NOT_CANCELED, l_date, l_nextDate};        
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_sbWhere,
                l_objWhere);//DataQueryException, DataNetworkException
                
            //�A   �擾�������R�[�h�̎g�p�|�C���g���W�v���A�ԋp����B
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            }      
            
            int l_intPoint = 0;
            for (int i = 0; i <l_intRecords; i++)
            {
                PointApplyRow l_row = (PointApplyRow)l_lisRecords.get(i);
                log.debug("No" + i + ":" + l_row.getUsedPoint());
                l_intPoint = l_intPoint + l_row.getUsedPoint();
            }
            
            log.debug("totle:" + l_intPoint);     
       
            log.exiting(STR_METHOD_NAME);
            return l_intPoint;      
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�c�|�C���g)<BR>
     * �Ώی��̎c�|�C���g���擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �����ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return long
     * @@roseuid 41AEFCBA034B
     */
    protected long getRemainedPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRemainedPoint(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A�Ώی��iYYYYMM�j���󂯎��A�Z�o�����Ώی��̎c�|�C���g��ԋp����B
        
        try
        {   
            log.debug("�Ώی�:" + l_strObjectMonth);
            
            //�@@   �ȉ��̏����ŁA�Ώی��̃|�C���g���v�e�[�u���̃��R�[�h���擾����B
            //[�擾����]
            //�،���ЃR�[�h �� ����.�،���ЃR�[�h
            //���X�R�[�h �� ����.���X�R�[�h
            //�����R�[�h �� ����.�ڋq�R�[�h
            //�N�� �� ����.�Ώی� 
            PointTotalRow l_row = PointTotalDao.findRowByInstitutionCodeBranchCodeAccountCodePeriod(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_strObjectMonth);//DataQueryException, DataNetworkException
                
            //�A   �Ώی��̖��W�v�����|�C���g���擾����B
            //get���W�v�����|�C���g()���R�[������B
            //[get���W�v�����|�C���g()�ɃZ�b�g�������]
            //�،���ЃR�[�h�F ����.�،���ЃR�[�h
            //���X�R�[�h�F ����.���X�R�[�h
            //�ڋq�R�[�h�F ����.�ڋq�R�[�h
            //�Ώی��F ����.�Ώی�
            //���߂�l��(A)�Ƃ���B
            long l_lngNotTotalAdjustPoint = this.getNotTotalAdjustPoint(l_strInstitutionCode, 
                l_strBranchCode, l_strAccountCode, l_strObjectMonth);
            log.debug("�Ώی��̖��W�v�����|�C���g:" + l_lngNotTotalAdjustPoint);
        
            //�B   �c�|�C���g���Z�o����B
            //1)  ���v���R�[�h���擾�ł��Ȃ������ꍇ
            //   (A) �� 0 �̏ꍇ�A�c�|�C���g �� (A)
            //   (A) �� 0 �̏ꍇ�A�c�|�C���g �� 0
            //2)  ���v���R�[�h���擾�ł����ꍇ
            //�����|�C���g���v �� ���R�[�h.���v�����|�C���g �{ (A) �Ƃ���ƁA
            //   �����|�C���g���v �� 0 �̏ꍇ
            //�c�|�C���g �� ���R�[�h.���v�l���|�C���g �{ �����|�C���g���v
            //�| ���R�[�h.�����m��\���|�C���g �| ���R�[�h.�����m�蒲���|�C���g
            //   �����|�C���g���v �� 0 �̏ꍇ
            //�c�|�C���g �� ���R�[�h.���v�l���|�C���g �| ���R�[�h.�����m��\���|�C���g
            //�| ���R�[�h.�����m�蒲���|�C���g
        
            //�����|�C���g���v
            long l_lngTotalPoint = 0; 
        
            //�c�|�C���g
            long l_lngRemainedPoint = 0;
        
            //1)  ���v���R�[�h���擾�ł��Ȃ������ꍇ
            if (l_row == null)
            {
                if (l_lngNotTotalAdjustPoint > 0)
                {
                    l_lngRemainedPoint = l_lngNotTotalAdjustPoint;                
                }
                else
                {
                    l_lngRemainedPoint = 0;
                }
            }
            //2)  ���v���R�[�h���擾�ł����ꍇ
            else
            {                   
                l_lngTotalPoint = l_row.getTotalAdjustPoint() + l_lngNotTotalAdjustPoint;
                log.debug("l_row.getTotalAdjustPoint() + l_lngNotTotalAdjustPoint:");
                log.debug(l_row.getTotalAdjustPoint() + " + " + l_lngNotTotalAdjustPoint);
                log.debug("l_lngTotalPoint:" + l_lngTotalPoint);
                if (l_lngTotalPoint > 0)
                {
                    log.debug("l_lngTotalPoint > 0");
                    l_lngRemainedPoint = l_row.getTotalGetPoint() + l_lngTotalPoint - 
                        l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint();
                    log.debug("l_row.getTotalGetPoint() + l_lngTotalPoint - " +
                        "l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint()");
                    log.debug(l_row.getTotalGetPoint() + " + " + l_lngTotalPoint + "- " +
                        l_row.getWithdrawnApplyPoint() + " - " + l_row.getWithdrawnAdjustPoint());
                    log.debug("���v���R�[�h:" + l_lngRemainedPoint);
                }
                else
                {
                    log.debug("l_lngTotalPoint < 0");
                    l_lngRemainedPoint = l_row.getTotalGetPoint() - 
                        l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint();
                    log.debug("l_row.getTotalGetPoint() - " +
                        "l_row.getWithdrawnApplyPoint() - l_row.getWithdrawnAdjustPoint()");
                    log.debug(l_row.getTotalGetPoint() + "- " +
                        l_row.getWithdrawnApplyPoint() + " - " + l_row.getWithdrawnAdjustPoint());
                    log.debug("���v���R�[�h:" + l_lngRemainedPoint);
                }
            }

            //�C �Z�o�����c�|�C���g��ԋp����B
            log.exiting(STR_METHOD_NAME);
            
            return l_lngRemainedPoint;    
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
    }
    
    /**
     * (get�����J�z�|�C���g)<BR>
     * �Ώی����猩���������̈������ς̃|�C���g�̍��v���擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �����ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@param l_cancelReleaseApply - (��������\��)<BR>
     * ��������\��̐\���f�[�^<BR>
     * 
     * @@param l_lngPreMonCarryoverPoint - (�O���J�z�|�C���g)<BR>
     * �O���J�z�|�C���g<BR>
     * 
     * @@return long
     * @@roseuid 41AF0551003E
     */
    protected long getWithdrawnCarryOverPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth, WEB3PointApply l_cancelReleaseApply, long l_lngPreMonCarryoverPoint) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getWithdrawnCarryOverPoint(String, String, String, String, WEB3PointApply, long)";
        log.entering(STR_METHOD_NAME);
        
        log.debug("ObjectMonth:" + l_strObjectMonth);
        
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A�Ώی��iYYYYMM�j�A
        //�\���̎���������l������ꍇ�͂��̐\���f�[�^�A
        //�Ώی��̑O���̌J�z�|�C���g���l������K�v������ꍇ�͂��̃|�C���g���󂯎��A
        //�Z�o�����Ώی����猩���������̈����J�z�|�C���g�̍��v��ԋp����B

        //�@@   ���������擾����B
        //get������()���R�[������B
        //[get������()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //�Ώی��F ����.�Ώی�
        //���߂�l��(A)�Ƃ���B        
        String l_strInvalidMon = this.getInvalidMon(l_strInstitutionCode, l_strObjectMonth);
        
        if (l_strInvalidMon == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.debug("������:" + l_strInvalidMon);

        //�A   �������̎c�|�C���g���擾����B
        //get�c�|�C���g()���R�[������B
        //[get�c�|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F (A)
        //���߂�l��(B)�Ƃ���B        
        long l_lngRemainedPoint = this.getRemainedPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strInvalidMon);
        log.debug("�������̎c�|�C���g:" + l_lngRemainedPoint);

        //�B   �Ώی��̑O���̐\���|�C���g���擾����B
        //get�\���|�C���g()���R�[������B
        //[get�\���|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F ����.�Ώی���1�����O�̔N���iYYYYMM�j
        //���߂�l��(C)�Ƃ���B
        Date l_date = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
        if (l_date == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        Calendar l_cal = new GregorianCalendar();
            
        l_cal.setTime(l_date);
            
        l_cal.add(Calendar.MONTH, -1);
            
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        
        //�Ώی��F ����.�Ώی���1�����O�̔N���iYYYYMM)
        String l_strMonth = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM");      
        
        long l_lngApplyPoint = getApplyPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strMonth);                
        log.debug("�Ώی��̑O��(" + l_strMonth + ")�̐\���|�C���g:" + l_lngApplyPoint);

        //�C   ����.��������\�� != null and ����.��������\��.�\�������̔N�������iYYYYMM�j = 
        //����.�Ώی���1�����O�̔N���iYYYYMM�j �̏ꍇ
        //(C) = (C) + ����.��������\��.�g�p�|�C���g �Ƃ���B      
        if (l_cancelReleaseApply != null)
        {
            log.debug("��������\�� != null");
            String l_strApplyMonth = WEB3DateUtility.formatDate(l_cancelReleaseApply.getApplyTimestamp(), "yyyyMM"); 
            if (l_strMonth == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            if (l_strMonth.equals(l_strApplyMonth))
            {
                log.debug("��������\��.�\�������̔N�������iYYYYMM�j= ����.�Ώی���1�����O�̔N���iYYYYMM�j �̏ꍇ");
                PointApplyParams l_params = new PointApplyParams((PointApplyParams)l_cancelReleaseApply.getDataSourceObject());
                
                if (l_params == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                
                l_lngApplyPoint = l_lngApplyPoint + l_params.getUsedPoint();
                
                log.debug("�Ώی��̑O���̐\���|�C���gchanged:" + l_lngApplyPoint);

            }
        }


        //�D   �Ώی��̑O���̖��W�v�����|�C���g���擾����B
        //get���W�v�����|�C���g()���R�[������B
        //[get���W�v�����|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F ����.�Ώی���1�����O�̔N���iYYYYMM�j
        //���߂�l��(D)�Ƃ���B
        long l_lngAdjustPoint = getNotTotalAdjustPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strMonth);
        log.debug("�Ώی��̑O��(" + l_strMonth + ")�̖��W�v�����|�C���g:" + l_lngAdjustPoint);            


        //�E   �b��J�z�|�C���g���Z�o����B
        //�E   (D) �� 0 �̏ꍇ�A�b��J�z�|�C���g = ����.�O���J�z�|�C���g + (C) - (D)
        //�E   (D) �� 0 �̏ꍇ�A�b��J�z�|�C���g = ����.�O���J�z�|�C���g + (C)
        //���b��J�z�|�C���g = (E) �Ƃ���B
        long l_lngOverPoint = 0;
        if (l_lngAdjustPoint < 0)
        {
            log.debug("(D) �� 0 �̏ꍇ");
            //(D) �� 0 �̏ꍇ�A�b��J�z�|�C���g = ����.�O���J�z�|�C���g + (C) - (D)
            l_lngOverPoint = l_lngPreMonCarryoverPoint + l_lngApplyPoint - l_lngAdjustPoint;
            log.debug("l_lngPreMonCarryoverPoint + l_lngApplyPoint - l_lngAdjustPoint:" + 
                l_lngPreMonCarryoverPoint + " + "+ l_lngApplyPoint + " - " + l_lngAdjustPoint);
            log.debug("�b��J�z�|�C���g:" + l_lngOverPoint);
        }
        else
        {
            log.debug("(D) �� 0 �̏ꍇ");
            //(D) �� 0 �̏ꍇ�A�b��J�z�|�C���g = ����.�O���J�z�|�C���g + (C)
            l_lngOverPoint = l_lngPreMonCarryoverPoint + l_lngApplyPoint;
            log.debug("�b��J�z�|�C���g:" + l_lngOverPoint);
        }

        //�F   �����J�z�|�C���g���Z�o���A�ԋp����B
        //(B) > 0 and (E) > 0 �̏ꍇ
        //�E   (B) �� (E) �̏ꍇ�A�����J�z�|�C���g = (E) - (B)
        //�E   (B) �� (E) �̏ꍇ�A�����J�z�|�C���g = 0
        //����ȊO�̏ꍇ
        //�����J�z�|�C���g = (E)
        long l_lngPoint = 0;
        if (l_lngRemainedPoint > 0 && l_lngOverPoint > 0)
        {
            log.debug("(B) > 0 and (E) > 0 �̏ꍇ");
            //(B) �� (E) �̏ꍇ�A�����J�z�|�C���g = (E) - (B)
            if (l_lngRemainedPoint < l_lngOverPoint)
            {
                log.debug("(B) �� (E) �̏ꍇ");
                l_lngPoint = l_lngOverPoint -  l_lngRemainedPoint;
                
                log.debug("l_lngOverPoint:" + l_lngOverPoint + " - l_lngRemainedPoint:" + l_lngRemainedPoint);
                
                log.debug("�����J�z�|�C���g:" + l_lngPoint);
            }
        }
        else
        {
            log.debug("!((B) > 0 and (E) > 0) �̏ꍇ");
            l_lngPoint = l_lngOverPoint;
            log.debug("l_lngOverPoint:" + l_lngOverPoint);
                
            log.debug("�����J�z�|�C���g:" + l_lngPoint);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lngPoint;
    }
    
    /**
     * (get�������σ|�C���g)<BR>
     * �L��������������ς̃|�C���g�̍��v���擾����B<BR>
     * <BR>
     * this.get�������σ|�C���g()���R�[������B<BR>
     * <BR>
     * [get�������σ|�C���g()�ɃZ�b�g�������]<BR>
     * �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     * ���X�R�[�h�F ����.���X�R�[�h<BR>
     * �ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
     * ��������\���F null<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)_<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@return long
     * @@roseuid 41AFE9590131
     */
    public long getNotWithdrawnPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNotWithdrawnPoint(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngPoint = this.getNotWithdrawnPoint(
            l_strInstitutionCode, l_strBranchCode, l_strAccountCode, null);
        
        log.exiting(STR_METHOD_NAME);
        return l_lngPoint;
    }
    
    /**
     * (get�������σ|�C���g)<BR>
     * �L��������������ς̃|�C���g�̍��v���擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_cancelReleaseApply - (��������\��)<BR>
     * ��������\��̐\���f�[�^<BR>
     * 
     * @@return long
     * @@roseuid 41AEF7D30207
     */
    protected long getNotWithdrawnPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointApply l_cancelReleaseApply) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNotWithdrawnPoint(String, String, String, WEB3PointApply)";
        log.entering(STR_METHOD_NAME);
        
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A�\���̎���������l������ꍇ�͂��̐\���f�[�^���󂯎��A�Z�o�����L��������������ς̃|�C���g�̍��v��ԋp����B

        //�@@   �挎��Ώی��Ƃ��Ď������̈����J�z�|�C���g���擾����B
        //get�����J�z�|�C���g()���R�[������B
        //[get�����J�z�|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F ���ݎ�����1�����O�̔N���iYYYYMM�j
        //��������\���F ����.��������\��
        //�O���J�z�|�C���g�F 0
        //���߂�l��(A)�Ƃ���B
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        Calendar l_cal = new GregorianCalendar();            
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.MONTH, -1);           
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());               
        String l_strDate = WEB3DateUtility.formatDate(l_tsDate, "yyyyMM"); 
        
        long l_lngPoint = this.getWithdrawnCarryOverPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strDate, l_cancelReleaseApply, 0);
            
        log.debug("�挎��Ώی��Ƃ��Ď������̈����J�z�|�C���g���擾����:" + l_lngPoint);
        
        //�A   ������Ώی��Ƃ��Ď������̈����J�z�|�C���g���擾����B
        //get�����J�z�|�C���g()���R�[������B
        //[get�����J�z�|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F ���ݎ�������擾�����N���iYYYYMM�j
        //��������\���F ����.��������\��
        //�O���J�z�|�C���g�F (A)
        //���߂�l��(B)�Ƃ���B             
        String l_strSystemDate = WEB3DateUtility.formatDate(l_tsSystemTime, "yyyyMM"); 
        
        long l_lngThisPoint = this.getWithdrawnCarryOverPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strSystemDate, l_cancelReleaseApply, l_lngPoint);
            
        log.debug("������Ώی��Ƃ��Ď������̈����J�z�|�C���g���擾����:" + l_lngThisPoint);

        //�B   �����̐\���|�C���g���擾����B
        //get�\���|�C���g()���R�[������B
        //[get�\���|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F ���ݎ�������擾�����N���iYYYYMM�j
        //���߂�l��(C)�Ƃ���B
        long l_lngApplyPoint = this.getApplyPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strSystemDate);
            
        log.debug("����(" + l_strSystemDate + ")�̐\���|�C���g:" + l_lngApplyPoint);

        //�C   ����.��������\�� != null and ����.��������\��.�\�������̔N�������iYYYYMM�j = �����̔N���iYYYYMM�j �̏ꍇ
        //(C) = (C) + ����.��������\��.�g�p�|�C���g �Ƃ���B
        if (l_cancelReleaseApply != null)
        {
            log.debug("����.��������\�� != null");
            String l_strApplyMonth = WEB3DateUtility.formatDate(l_cancelReleaseApply.getApplyTimestamp(), "yyyyMM");
            
            if (l_strSystemDate == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            if (l_strSystemDate.equals(l_strApplyMonth))
            {
                log.debug("����.��������\��.�\�������̔N�������iYYYYMM�j = �����̔N���iYYYYMM�j �̏ꍇ");
                PointApplyParams l_params = new PointApplyParams((PointApplyParams)l_cancelReleaseApply.getDataSourceObject());
                if (l_params == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                l_lngApplyPoint = l_lngApplyPoint + l_params.getUsedPoint();
                
                log.debug("����(" + l_strSystemDate + ")�̐\���|�C���gchanged:" + l_lngApplyPoint);
            }   
        }
   
        
        //�D   �����̖��W�v�����|�C���g���擾����B
        //get���W�v�����|�C���g()���R�[������B
        //[get���W�v�����|�C���g()�ɃZ�b�g�������]
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h
        //���X�R�[�h�F ����.���X�R�[�h
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h
        //�Ώی��F ���ݎ�������擾�����N���iYYYYMM�j
        //���߂�l��(D)�Ƃ���B
        long l_lngAdjustPoint = this.getNotTotalAdjustPoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_strSystemDate);    
            
        log.debug("����(" + l_strSystemDate +")�̖��W�v�����|�C���g���擾����:" + l_lngAdjustPoint);
            
        //�E   �������σ|�C���g���Z�o���A�ԋp����B
        //�E   (D) �� 0 �̏ꍇ�A�������σ|�C���g = (B) + (C) - (D)
        //�E   (D) �� 0 �̏ꍇ�A�������σ|�C���g = (B) + (C)
        long l_lngNotWithdrawnPoint = 0;
        if (l_lngAdjustPoint < 0)
        {
            log.debug("(D) �� 0 �̏ꍇ");
            l_lngNotWithdrawnPoint = l_lngThisPoint + l_lngApplyPoint - l_lngAdjustPoint;
            log.debug("l_lngThisPoint + l_lngApplyPoint - l_lngAdjustPoint");
            log.debug(l_lngThisPoint + " + " + l_lngApplyPoint + " - " + l_lngAdjustPoint);
            log.debug("�������σ|�C���g:" + l_lngNotWithdrawnPoint);
        }
        else
        {
            log.debug("(D) �� 0 �̏ꍇ");
            l_lngNotWithdrawnPoint = l_lngThisPoint + l_lngApplyPoint;
            log.debug("l_lngThisPoint + l_lngApplyPoin");
            log.debug(l_lngThisPoint + " + " + l_lngApplyPoint);
            log.debug("�������σ|�C���g:" + l_lngNotWithdrawnPoint);
        }
        
        log.exiting(STR_METHOD_NAME);

        return l_lngNotWithdrawnPoint;
    }
    
    /**
     * (get���W�v�����|�C���g)<BR>
     * �Ώی��̖��W�v�̒����|�C���g�̍��v���擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �����ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return long
     * @@roseuid 4199CD72002E
     */
    public long getNotTotalAdjustPoint(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strObjectMonth) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getNotTotalAdjustPoint(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A�Ώی��iYYYYMM�j���󂯎��A
        //�Z�o�����Ώی��̖��W�v�����|�C���g�̍��v��ԋp����B

        //�@@   �|�C���g�����e�[�u������A�ȉ��̏����Ń��R�[�h���擾����B
        //[�擾����]
        //�،���ЃR�[�h �� ����.�،���ЃR�[�h and
        //���X�R�[�h �� ����.���X�R�[�h and
        //�����R�[�h �� ����.�ڋq�R�[�h and
        //�쐬���� �� (A) and
        //�쐬���� �� (B)
        //��(A)�F ����.�Ώی���1����0:00
        //  (B)�F ����.�Ώی��̗�����1����0:00
        try
        {      
            log.debug("�Ώی�:" + l_strObjectMonth);
            
            List l_lisRecords = null;

            Calendar l_cal = new GregorianCalendar();
            
            // ����.�Ώی���1����0:00
            Date l_date = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            
            if (l_date == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_cal.setTime(l_date);
            
            l_cal.add(Calendar.MONTH, 1);
            
            //����.�Ώی��̗�����1����0:00
            Date l_nextDate = new Date(l_cal.getTimeInMillis());
                                    
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();    
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            String l_sbWhere = null;
            Object[] l_objWhere = null;

            l_sbWhere = "institution_code = ? and branch_code = ? and " + 
                " account_code = ? and created_timestamp >= ? and created_timestamp < ? ";
                                
            l_objWhere = new Object[]{l_strInstitutionCode, l_strBranchCode, l_strAccountCode, 
                l_date, l_nextDate};        
    
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                PointAdjustRow.TYPE,
                l_sbWhere,
                l_objWhere);//DataQueryException, DataNetworkException
            
            //�Z�o�����Ώی��̖��W�v�����|�C���g�̍��v��ԋp����B
            int l_intRecords = 0;
            if(l_lisRecords != null)
            {
                l_intRecords = l_lisRecords.size();
            }      
            
            int l_intPoint = 0;
            for (int i = 0; i <l_intRecords; i++)
            {
                PointAdjustRow l_row = (PointAdjustRow)l_lisRecords.get(i);
                l_intPoint = l_intPoint + l_row.getAdjustPoint();
                log.debug("No" + i + ":" + l_intPoint);
            }
            
            log.debug("totle:" + l_intPoint);
            log.exiting(STR_METHOD_NAME);
            
            return l_intPoint;      
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�L��������)<BR>
     * �L�����������擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �Z�o�ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return String
     * @@roseuid 41AEF123012C
     */
    public String getValidTermMon(String l_strInstitutionCode, String l_strObjectMonth) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getValidTermMon(String, String)";
        log.entering(STR_METHOD_NAME);
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A�Ώی��iYYYYMM�j���󂯎��A
        //�Ώی��̗L����������ԋp����B

        try
        {   
            //�@@   �|�C���g�L�������e�[�u������A�ȉ��̏����Ń��R�[�h���擾����B
            //[�擾����]
            //�،���ЃR�[�h �� ����.�،���ЃR�[�h
            
            PointTermRow l_row = PointTermDao.findRowByInstitutionCode(l_strInstitutionCode);//DataQueryException, DataNetworkException
            
            if (l_row == null)
            {
                return null;
            }
            
            String l_strValidity = l_row.getTermOfValidity();
            
            //�A   ����.�Ώی��́i�|�C���g�L�������e�[�u��.�L�������|�P�j
            //�����O�̔N���iYYYYMM�j���Z�o���A�ԋp����B
            
            int l_intValidity = Integer.parseInt(l_strValidity) - 1;//NumberFormatException     
            
            Date l_objectMonth = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            if (l_objectMonth == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_objectMonth);
            
            l_cal.add(Calendar.MONTH, -l_intValidity);
            
            Date l_date = new Date(l_cal.getTimeInMillis());
            
            String l_strValidityNew = WEB3DateUtility.formatDate(l_date, "yyyyMM");
            
            log.exiting(STR_METHOD_NAME);
            
            return l_strValidityNew;      
        }
        catch (NumberFormatException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get������)<BR>
     * ���������擾����B<BR>
     * <BR>
     * �����̏ڍׂ́A�v�Z�����Q�ƁB<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strObjectMonth - (�Ώی�)<BR>
     * �Z�o�ΏۂƂȂ�N���iYYYYMM�j<BR>
     * 
     * @@return String
     * @@roseuid 41AEF2BE0226
     */
    protected String getInvalidMon(String l_strInstitutionCode, String l_strObjectMonth) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInvalidMon(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //[���\�b�h�T�v]
        //�����ɂď،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A
        //�Ώی��iYYYYMM�j���󂯎��A�Ώی��̎�������ԋp����B

        //�@@   �|�C���g�L�������e�[�u������A�ȉ��̏����Ń��R�[�h���擾����B
        //[�擾����]
        //�،���ЃR�[�h �� ����.�،���ЃR�[�h    
        
        //�A ����.�Ώی��́i�|�C���g�L�������e�[�u��.�L�������j�����O�̔N���iYYYYMM�j���Z�o���A�ԋp����B
        try
        {      
            PointTermRow l_row = PointTermDao.findRowByInstitutionCode(l_strInstitutionCode);//DataQueryException, DataNetworkException
            
            if (l_row == null)
            {
                return null;
            }
            
            String l_strTermOfValidity = l_row.getTermOfValidity();
            
            int l_intValidityMon = Integer.parseInt(l_strTermOfValidity);//NumberFormatException            
            
            Date l_objectMonth = WEB3DateUtility.getDate(l_strObjectMonth, "yyyyMM");
            if (l_objectMonth == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            Calendar l_cal = new GregorianCalendar();
            l_cal.setTime(l_objectMonth);  
            
            l_cal.add(Calendar.MONTH, -l_intValidityMon);
            
            Date l_nextDate = new Date(l_cal.getTimeInMillis());
            
            String l_strInvalidMon = WEB3DateUtility.formatDate(l_nextDate, "yyyyMM");
            
            log.exiting(STR_METHOD_NAME);
            
            return l_strInvalidMon;
        }
        catch (NumberFormatException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate�����|�C���g)<BR>
     * �����|�C���g�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�������`�F�b�N<BR>
     * <BR>
     * �P�|�P�j<BR>
     * <BR>
     *    ����.�����|�C���g = null or<BR>
     *    ����.�����|�C���g = 0 or<BR>
     *    ����.�����|�C���g != ����<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01719<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01720<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01721<BR>
     * <BR>
     * �P�|�Q�j����.�����|�C���g > 0 �̂Ƃ�<BR>
     * <BR>
     *    ����.�����|�C���g.length() > 8<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01722<BR>
     * <BR>
     * �P�|�R�j����.�����|�C���g < 0 �̂Ƃ�<BR>
     * <BR>
     *    ����.�����|�C���g.length() > 9 �i�˃}�C�i�X���܂߂�length�j<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01722<BR>
     * <BR>
     * �Q�j������|�C���g�̃`�F�b�N<BR>
     * <BR>
     *    ����.���p�\�|�C���g + ����.�����|�C���g < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01724<BR>
     * @@param l_strAdjustPoint - (�����|�C���g)<BR>
     * �����|�C���g<BR>
     * 
     * @@param l_lngUsePossiblePoint - (���p�\�|�C���g)<BR>
     * ���p�\�|�C���g<BR>
     * @@roseuid 419468D500FA
     */
    public void validateAdjustPoint(String l_strAdjustPoint, long l_lngUsePossiblePoint) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAdjustPoint(String, long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�������`�F�b�N<BR>
        //�P�|�P�j<BR>
        //   ����.�����|�C���g = null or<BR>
        //   ����.�����|�C���g = 0 or<BR>
        //   ����.�����|�C���g != ����<BR>
        //   �̏ꍇ�A��O���X���[����B<BR>
        //        class: WEB3BusinessLayerException<BR>
        //        tag:   BUSINESS_ERROR_01719<BR>
        //        class: WEB3BusinessLayerException<BR>
        //        tag:   BUSINESS_ERROR_01720<BR>
        //        class: WEB3BusinessLayerException<BR>
        //        tag:   BUSINESS_ERROR_01721<BR>
        if (l_strAdjustPoint == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01719,
                getClass().getName() + STR_METHOD_NAME,
                " ����.�����|�C���g = null �̏ꍇ�A��O���X���[����B");
        }
        
        if (!WEB3StringTypeUtility.isNumber(l_strAdjustPoint))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01721,
                getClass().getName() + STR_METHOD_NAME,
                " ����.�����|�C���g != ���� �̏ꍇ�A��O���X���[����B");  
        }
        
        if (new Long(l_strAdjustPoint).intValue() == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01720,
                getClass().getName() + STR_METHOD_NAME,
                " ����.�����|�C���g = 0 �̏ꍇ�A��O���X���[����B"); 
        }
        
        //�P�|�Q�j����.�����|�C���g > 0 �̂Ƃ�<BR>
        //    ����.�����|�C���g.length() > 8<BR>
        //    �̏ꍇ�A��O���X���[����B<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01722<BR>
        if (new Long(l_strAdjustPoint).intValue() > 0 && WEB3StringTypeUtility.getByteLength(l_strAdjustPoint) > 8)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01722,
                getClass().getName() + STR_METHOD_NAME,
                " �����|�C���g�̌������������z���Ă��܂��B");
        }
        
        // �P�|�R�j����.�����|�C���g < 0 �̂Ƃ�<BR>
        // <BR>
        //    ����.�����|�C���g.length() > 9 �i�˃}�C�i�X���܂߂�length�j<BR>
        // <BR>
        //    �̏ꍇ�A��O���X���[����B<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01722<BR>
        if (new Long(l_strAdjustPoint).intValue() < 0 && WEB3StringTypeUtility.getByteLength(l_strAdjustPoint) > 9)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01722,
                getClass().getName() + STR_METHOD_NAME,
                " �����|�C���g�̌������������z���Ă��܂��B");
        }
        
        // �Q�j������|�C���g�̃`�F�b�N<BR>
        // <BR>
        //    ����.���p�\�|�C���g + ����.�����|�C���g < 0<BR>
        // <BR>
        //    �̏ꍇ�A��O���X���[����B<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01724<BR>
        long l_lngPoint = l_lngUsePossiblePoint + new Long(l_strAdjustPoint).intValue();
        if (l_lngPoint < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01724,
                getClass().getName() + STR_METHOD_NAME,
                " ����.���p�\�|�C���g + ����.�����|�C���g < 0 " +
                " �̏ꍇ�A��O���X���[����B");
        }
               
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�|�C���g�]��)<BR>
     * �|�C���g�]�͂̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j���p�\�|�C���g�̎擾<BR>
     * <BR>
     *    this.get���p�\�|�C���g()���R�[������B<BR>
     * <BR>
     *    [get���p�\�|�C���g()�ɃZ�b�g�������]<BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *    ���X�R�[�h�F ����.���X�R�[�h<BR>
     *    �ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
     * <BR>
     * �Q�j�|�C���g�]�͂̃`�F�b�N<BR>
     * <BR>
     *    �P�j�̌��� < ����.�|�C���g�i�i.get�K�v�|�C���g()�̖߂�l<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01725<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_premium - (�i�i)<BR>
     * �i�i�I�u�W�F�N�g<BR>
     * @@roseuid 419D767D0374
     */
    public void validatePointPower(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, WEB3PointPremium l_premium) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePointPower(String, String, String, WEB3PointPremium)";
        log.entering(STR_METHOD_NAME);
        
        if (l_premium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �P�j���p�\�|�C���g�̎擾<BR>
        //    this.get���p�\�|�C���g()���R�[������B<BR>
        //    [get���p�\�|�C���g()�ɃZ�b�g�������]<BR>
        //    �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
        //    ���X�R�[�h�F ����.���X�R�[�h<BR>
        //    �ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
        long l_lngPossiblePoint = this.getUsePossiblePoint(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode);
        
        // �Q�j�|�C���g�]�͂̃`�F�b�N<BR>
        //    �P�j�̌��� < ����.�|�C���g�i�i.get�K�v�|�C���g()�̖߂�l<BR>
        //    �̏ꍇ�A��O���X���[����B<BR>
        //         class: WEB3BusinessLayerException<BR>
        //         tag:   BUSINESS_ERROR_01725<BR>
        if (l_lngPossiblePoint < l_premium.getRequiredPoint())
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01970,
                getClass().getName() + STR_METHOD_NAME,
                " ���p�\�|�C���g< ����.�|�C���g�i�i.get�K�v�|�C���g()�̖߂�l " +
                " �̏ꍇ�A��O���X���[����B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�|�C���g�]��)<BR>
     * �\���̎�������Ń|�C���g�\�����L���ɂȂ������̃|�C���g�]�͂̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j���p�\�|�C���g�̎Z�o<BR>
     * <BR>
     *    this.get���p�\�|�C���g()���R�[������B<BR>
     * <BR>
     *    [get���p�\�|�C���g�ɃZ�b�g�������]<BR>
     *    �،���ЃR�[�h�F ����.�\���f�[�^.�،���ЃR�[�h<BR>
     *    ���X�R�[�h�F ����.�\���f�[�^.���X�R�[�h<BR>
     *    �ڋq�R�[�h�F ����.�\���f�[�^.�����R�[�h<BR>
     *    ��������\���F ����.�\���f�[�^<BR>
     * <BR>
     *    ���߂�l��(A)�Ƃ���B<BR>
     * <BR>
     * �R�j�|�C���g�]�̓`�F�b�N<BR>
     * <BR>
     *    (A) < 0<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01725<BR>
     * <BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * @@roseuid 419D8739028A
     */
    public void validatePointPower(WEB3PointApply l_applyData) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePointPower(WEB3PointApply)";
        log.entering(STR_METHOD_NAME);
        if (l_applyData == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �P�j���p�\�|�C���g�̎Z�o<BR>
        //    this.get���p�\�|�C���g()���R�[������B<BR>
        //    [get���p�\�|�C���g�ɃZ�b�g�������]<BR>
        //    �،���ЃR�[�h�F ����.�\���f�[�^.�،���ЃR�[�h<BR>
        //    ���X�R�[�h�F ����.�\���f�[�^.���X�R�[�h<BR>
        //    �ڋq�R�[�h�F ����.�\���f�[�^.�����R�[�h<BR>
        //    ��������\���F ����.�\���f�[�^<BR>
        //    ���߂�l��(A)�Ƃ���B
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        long l_lngPossiblePoint = getUsePossiblePoint(l_params.getInstitutionCode(), 
            l_applyData.getBranchCode(), l_applyData.getAccountCode(), l_applyData);
        
        //�R�j�|�C���g�]�̓`�F�b�N
        if (l_lngPossiblePoint < 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01725,
                getClass().getName() + STR_METHOD_NAME,
                " ���p�\�|�C���g < 0 " +
                " �̏ꍇ�A��O���X���[����B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (saveNew����)<BR>
     * �����f�[�^��DB�ɓo�^����B<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�����f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
     *    �쐬���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �R�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�����e�[�u���ɍs��}���iinsert�j����B <BR>
     * <BR>
     * @@param l_adjustData - (�����f�[�^)<BR>
     * �|�C���g�����I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419479C7007D
     */
    public void saveNewAdjust(WEB3PointAdjust l_adjustData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewAdjust(WEB3PointAdjust, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_adjustData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�s�I�u�W�F�N�g�擾
        //����.�����f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B
        PointAdjustParams l_params = (PointAdjustParams)l_adjustData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B             
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //�쐬���� = TradingSystem.getSystemTimestamp()
        l_params.setCreatedTimestamp(l_tsSystemTime);
        
        //�X�V���� = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //�X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h
        String l_strAdministratorCode = l_admin.getAdministratorCode();        
        l_params.setLastUpdater(l_strAdministratorCode);
        
        //�R�jDB�X�V
        //�s�I�u�W�F�N�g�̓��e�Ń|�C���g�����e�[�u���ɍs��}���iinsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doInsertQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (saveNew�\��)<BR>
     * �\���f�[�^��DB�ɓo�^����B<BR>
     * <BR>
     * �P�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�擾�����s�I�u�W�F�N�g�̒l���Z�b�g����ĂȂ����ڂɒl���Z�b�g����B<BR>
     *    DB�X�V�d�l�u�|�C���g�����\��_�|�C���g�\���e�[�u���v�Q�� <BR>
     * <BR>
     * �R�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�\���e�[�u���ɍs��}���iinsert�j����B<BR>
     * <BR>
     * @@param l_appyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * 
     * @@param l_trader - (�㗝���͎�)<BR>
     * �㗝���͎҃I�u�W�F�N�g<BR>
     * @@roseuid 41A451E10111
     */
    public void saveNewApply(WEB3PointApply l_appyData, Trader l_trader) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewApply(WEB3PointApply, Trader)";
        log.entering(STR_METHOD_NAME);
        
        if (l_appyData == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�s�I�u�W�F�N�g�擾
        //����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B
        PointApplyParams l_params = (PointApplyParams)l_appyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B          
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //�\���o�H�敪 = �Z�b�V��������擾���������o�H�敪
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
                
        if (l_opLoginSec == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME); 
        }
                
        //�����o�H�敪
        String l_orderRootDiv =
            l_opLoginSec.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
                
        l_params.setApplyRootDiv(l_orderRootDiv);
        
        //�\������ = TradingSystem.getSystemTimestamp()
        l_params.setApplyTimestamp(l_tsSystemTime);
        
        //�X�V���� = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //�\����t�敪 0�F�\���ݎ�
        l_params.setApplyAcceptDiv(WEB3ApplyAcceptDivDef.APPLYING);
        
        //�\������敪 0�F�������
        l_params.setApplyCancelDiv(WEB3ApplyCancelDivDef.NOT_CANCELED);
        
        //���҃R�[�h        
        //�X�V�҃R�[�h
        if (l_trader == null)
        {
            //���҃R�[�h=null �̏ꍇ�A�����R�[�h
            l_params.setLastUpdater(l_appyData.getAccountCode());
        }
        
        if (l_trader != null)
        {
            //�㗝���͂̏ꍇ�A�㗝���͎�.getTraderCode()
            l_params.setTraderCode(l_trader.getTraderCode());
            
            //���҃R�[�h!=null �̏ꍇ�A���҃R�[�h
            l_params.setLastUpdater(l_trader.getTraderCode());
        }
        
        //�R�jDB�X�V
        //�s�I�u�W�F�N�g�̓��e�Ń|�C���g�����e�[�u���ɍs��}���iinsert�j����B
        try
        {
            //�Q�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B
            //�\��ID�i�����̔ԁj
            l_params.setApplyId(PointApplyDao.newPkValue());//DataNetworkException,DataQueryException
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doInsertQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save�\����t)<BR>
     * �\����t��Ԃ�DB���X�V����B<BR>
     * <BR>
     * �P�j�X�V�p�̍s�I�u�W�F�N�g����<BR>
     *    ����.�\���f�[�^.createForUpdateParams()���R�[������B<BR>
     * <BR>
     * �Q�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
     *    �\����t�敪 = 1�i�،���Ў�t�ς݁j<BR>
     *    �\����t�� = ����.�Ǘ��҂̃��O�C��ID<BR>
     *    �\����t���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �S�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�\���e�[�u�����X�V�iupdate�j����B <BR>
     * <BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419C90410093
     */
    public void saveApplyAccept(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveApplyAccept(WEB3PointApply, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_applyData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �P�j�X�V�p�̍s�I�u�W�F�N�g����<BR>
        //    ����.�\���f�[�^.createForUpdateParams()���R�[������B<BR>
        l_applyData.createForUpdateParams();
        
        // �Q�j�s�I�u�W�F�N�g�擾 <BR>
        //    ����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
        //    �\����t�敪 = 1�i�،���Ў�t�ς݁j<BR>
        l_params.setApplyAcceptDiv(WEB3ApplyAcceptDivDef.INSTITUTION_ACCEPTED);
        
        //�\����t�� = ����.�Ǘ��҂̃��O�C��ID<BR>
        l_params.setApplyAcceptUser(new Long(l_admin.getLoginId()).toString());
        
        //�\����t���� = TradingSystem.getSystemTimestamp() <BR>
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_params.setApplyAcceptTimestamp(l_tsSystemTime);
        
        //�X�V���� = TradingSystem.getSystemTimestamp() <BR>
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //�X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // �S�jDB�X�V <BR>
        //    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�\���e�[�u�����X�V�iupdate�j����B <BR>
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save�\�����)<BR>
     * �\�������Ԃ�DB���X�V����B<BR>
     * <BR>
     * �P�j�X�V�p�̍s�I�u�W�F�N�g����<BR>
     *    ����.�\���f�[�^.createForUpdateParams()���R�[������B<BR>
     * <BR>
     * �Q�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
     *    �\������敪 = 1�i����ς݁j<BR>
     *    �\������� = ����.�Ǘ��҂̃��O�C��ID<BR>
     *    �\��������� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �S�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�\���e�[�u�����X�V�iupdate�j����B <BR>
     * <BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419D631501E6
     */
    public void saveApplyCancel(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveApplyCancel(WEB3PointApply, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_applyData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �P�j�X�V�p�̍s�I�u�W�F�N�g����<BR>
        //    ����.�\���f�[�^.createForUpdateParams()���R�[������B<BR>
        l_applyData.createForUpdateParams();
        
        // �Q�j�s�I�u�W�F�N�g�擾 <BR>
        //    ����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
        //    �\����t�敪 = 1�i�،���Ў�t�ς݁j<BR>
        l_params.setApplyCancelDiv(WEB3ApplyCancelDivDef.CANCELED);
        
        //�\������� = ����.�Ǘ��҂̃��O�C��ID
        l_params.setApplyCancelUser(new Long(l_admin.getLoginId()).toString());
        
        //�\��������� = TradingSystem.getSystemTimestamp() <BR>
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_params.setApplyCancelTimestamp(l_tsSystemTime);
        
        //�X�V���� = TradingSystem.getSystemTimestamp() <BR>
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //�X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // �S�jDB�X�V <BR>
        //�s�I�u�W�F�N�g�̓��e�Ń|�C���g�\���e�[�u�����X�V�iupdate�j����B>
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_params == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save�\���������)<BR>
     * �\�����������Ԃ�DB���X�V����B<BR>
     * <BR>
     * �P�j�X�V�p�̍s�I�u�W�F�N�g����<BR>
     *    ����.�\���f�[�^.createForUpdateParams()���R�[������B<BR>
     * <BR>
     * �Q�j�s�I�u�W�F�N�g�擾 <BR>
     *    ����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
     *    �\������敪 = 0�i������ρj<BR>
     *    �\������� = ����.�Ǘ��҂̃��O�C��ID<BR>
     *    �\��������� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V���� = TradingSystem.getSystemTimestamp() <BR>
     *    �X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
     * <BR>
     * �S�jDB�X�V <BR>
     *    �s�I�u�W�F�N�g�̓��e�Ń|�C���g�\���e�[�u�����X�V�iupdate�j����B <BR>
     * <BR>
     * @@param l_applyData - (�\���f�[�^)<BR>
     * �|�C���g�\���I�u�W�F�N�g<BR>
     * 
     * @@param l_admin - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@roseuid 419D74860142
     */
    public void saveApplyCancelRelease(WEB3PointApply l_applyData, WEB3Administrator l_admin) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveApplyCancelRelease(WEB3PointApply, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);
        
        if (l_applyData == null || l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �P�j�X�V�p�̍s�I�u�W�F�N�g����<BR>
        //    ����.�\���f�[�^.createForUpdateParams()���R�[������B<BR>
        l_applyData.createForUpdateParams();
        
        // �Q�j�s�I�u�W�F�N�g�擾 <BR>
        //    ����.�\���f�[�^.getDataSourceObject()�ɂčs�I�u�W�F�N�g���擾����B <BR>
        PointApplyParams l_params = (PointApplyParams)l_applyData.getDataSourceObject();
        if (l_params == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // �R�j�擾�����s�I�u�W�F�N�g�Ɉȉ��̒ʂ�A���ڂ̏����l���Z�b�g����B <BR>
        //�\������敪 = 0�i������ρj<BR>
        l_params.setApplyCancelDiv(WEB3ApplyCancelDivDef.NOT_CANCELED);
        
        //�\������� = ����.�Ǘ��҂̃��O�C��ID
        l_params.setApplyCancelUser(new Long(l_admin.getLoginId()).toString());
        
        //�\��������� = TradingSystem.getSystemTimestamp() <BR>
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        l_params.setApplyCancelTimestamp(l_tsSystemTime);
        
        //�X�V���� = TradingSystem.getSystemTimestamp() <BR>
        l_params.setLastUpdatedTimestamp(l_tsSystemTime);
        
        //�X�V�҃R�[�h = ����.�Ǘ��҂̊Ǘ��҃R�[�h<BR>
        l_params.setLastUpdater(l_admin.getAdministratorCode());

        // �S�jDB�X�V <BR>
        //�s�I�u�W�F�N�g�̓��e�Ń|�C���g�\���e�[�u�����X�V�iupdate�j����B>
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException
            if (l_queryProcessor == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_queryProcessor.doUpdateQuery(l_params);//DataNetworkException,DataQueryException
        }
        catch (DataNetworkException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            //DB�A�N�Z�X�����s�̏ꍇ
            log.error(getClass().getName() + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            //��O���X���[����
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�\���\)<BR>
     * �|�C���g�\���\���ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�i�i�I�u�W�F�N�g�̎擾<BR>
     * <BR>
     *    �|�C���g���i�}�l�[�W��Impl.get�i�i()���R�[������B<BR>
     * <BR>
     *    [get�i�i()�ɃZ�b�g�������]<BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *    �i�i�ԍ��F ����.�i�i�ԍ�<BR>
     * <BR>
     * �Q�j�i�i�̒񋟊��ԃ`�F�b�N<BR>
     * <BR>
     *    ���L�̏����𖞂����Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01726<BR>
     * <BR>
     *    �i�i.�񋟊J�n���� �� ���ݓ��� �� �i�i.�񋟏I������<BR>
     * <BR>
     * �R�j�|�C���g�]�̓`�F�b�N<BR>
     * <BR>
     *    this.validate�|�C���g�]��()���R�[������B<BR>
     * <BR>
     *    [get�i�i()�ɃZ�b�g�������]<BR>
     *    �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *    ���X�R�[�h�F ����.���X�R�[�h<BR>
     *    �ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
     *    �i�i�F �i�i�I�u�W�F�N�g<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * 
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * 
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * 
     * @@param l_strPrimiumNo - (�i�i�ԍ�)<BR>
     * �i�i�ԍ�<BR>
     * @@roseuid 41A6E44C001A
     */
    public void validateApplyPossible(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode, String l_strPrimiumNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateApplyPossible(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�i�i�I�u�W�F�N�g�̎擾
        //�|�C���g���i�}�l�[�W��Impl.get�i�i()���R�[������B
        //[get�i�i()�ɃZ�b�g�������]<BR>
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
        //�i�i�ԍ��F ����.�i�i�ԍ�<BR>
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME); 
        }
            
        WEB3PointPremium l_pointPremium = l_manager.getPremium(
            l_strInstitutionCode, l_strPrimiumNo);
            
        if (l_pointPremium == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�Q�j�i�i�̒񋟊��ԃ`�F�b�N
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        
        //�i�i.�񋟊J�n����
        Timestamp l_tsStartDateTime = new Timestamp(l_pointPremium.getStartDateTime().getTime());
        
        //�i�i.�񋟏I������
        Timestamp l_tsEndDateTime = new Timestamp(l_pointPremium.getEndDateTime().getTime());
        
        //���L�̏����𖞂����Ȃ��ꍇ�A��O���X���[����B
        //�i�i.�񋟊J�n���� �� ���ݓ��� �� �i�i.�񋟏I������
        int l_intComparaeOne = WEB3DateUtility.compareToSecond(l_tsSystemTime, l_tsEndDateTime);
        int l_intComparaeTwo = WEB3DateUtility.compareToSecond(l_tsSystemTime, l_tsStartDateTime);
        if (!(l_intComparaeOne < 0 && l_intComparaeTwo >= 0))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01726,
                getClass().getName() + STR_METHOD_NAME,
                " �i�i.�񋟏I�����������ݓ���< �i�i.�񋟊J�n����" +
                " �̏ꍇ�A��O���X���[����B");
        }
        
        //�R�j�|�C���g�]�̓`�F�b�N
        //this.validate�|�C���g�]��()���R�[������B
        //[get�i�i()�ɃZ�b�g�������]<BR>
        //�،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
        //���X�R�[�h�F ����.���X�R�[�h<BR>
        //�ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
        //�i�i�F �i�i�I�u�W�F�N�g<BR>
        this.validatePointPower(l_strInstitutionCode, 
            l_strBranchCode, l_strAccountCode, l_pointPremium);
        
        log.exiting(STR_METHOD_NAME);       
    }
}
@
